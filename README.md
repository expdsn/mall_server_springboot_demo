# mall_server_springboot_demo


# 1 简介

## 1.1 Demo

[在线演示](http://43.139.129.241)

用户名：account

密码：passwd

## 1.2 功能介绍

1. 基于微服务架构搭建后端工程，包含**用户服务**，**订单服务**，**商品服务**，集成**mysql plus**操作mysql

2. 基于vue2.0搭建前后端分离工程，实现web端功能如下：

   web端功能：

   1. 用户管理：基于用户的增删改查功能及页面
   2. 商品管理：基于商品的增删改查、下单功能及页面
   3. 订单管理：基于订单的增删改查功能及页面（用户做关联）
   4. 登录登出，并集成redis管理用户信息缓存

* username: vmall
* passwd: BpcTor_5866

## 1.3 技术框架

`vue `  + `springboot`  + `springsecurity` + `mybatis plus` + `redis`





# 2. 准备工作

## 2.1 pom坐标



```xml
 		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework.security/spring-security-core -->
        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
            <version>3.0.4</version>
        </dependency>

        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api -->

        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.7.14</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.2.8</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.baomidou/mybatis-plus-boot-starter -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.3</version>
        </dependency>
        <dependency>
            <groupId>com.vaadin.external.google</groupId>
            <artifactId>android-json</artifactId>
            <version>0.0.20131108.vaadin1</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-redis -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <!--    fastjson    -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.9</version>
        </dependency>

        <!--    jjwt    -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.11.2</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.11.2</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId> <!-- or jjwt-gson if Gson is preferred -->
            <version>0.11.2</version>
            <scope>runtime</scope>
        </dependency>
```

## 2.2 数据库的建立

```yaml
spring:
  datasource:
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/vmalldb?serverTimezone=UTC
      username: vmall
      password: BpcTor_58662
```



### 2.2.1 表1 -  **user **

```mysql
create table vmalldb._user
(
    id       bigint      not null primary key,
    _account varchar(50) not null,
    _uname   varchar(50) not null,
    _passwd  varchar(50) not null,
    _avatar  varchar(50) null,
    _role    smallint    not null,
    constraint _user_pk
        unique (_account)
)
    charset = utf8;


```

### 2.2.2 表2 - **goods**

````mysql
create table vmalldb._goods
(
    id     bigint      not null
        primary key,
    _gName varchar(50) not null,
    _price double      not null,
    _desc  text        null,
    _type  tinyint     not null
)
    charset = utf8;
````

### 2.2.3 表3 - **order**

````mysql	
create table vmalldb._order
(
    id         bigint   not null
        primary key,
    _gId       bigint   not null,
    _uId       bigint   null,
    _orderTime datetime null,
    constraint order_user_id__fk
        foreign key (_gId) references vmalldb._goods (id),
    constraint order_user_id_fk
        foreign key (_uId) references vmalldb._user (id)
);


````

# 3. 相关配置

## 3.1 过滤器配置

### 3.1.1 JwtAuthencationFilter

``` java
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);

            return;
        }
        String id;
        try {
            id = jwtUtil.parseToken(token).getSubject();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("抛出一个token过期异常");
            throw new RuntimeException("token非法");
        }
        LoginUser loginUser;
        try {

            loginUser = JSON.parseObject(redisTemplate.opsForValue().get("login:" + id), LoginUser.class);
        }catch (RuntimeException e) {
            throw new RuntimeException("用户登录过期");
        }
        if (Objects.isNull(loginUser)) {
            System.out.println("抛出一个未登录异常");
            throw new RuntimeException("用户未登录");
        }
        //TODO 获取权限信息封装到AuthenticationToken中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}

```

### 3.1.2 跨域过滤器

```java
package com.vmall.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Component
public class CustomCorsFilter extends CorsFilter{

    public CustomCorsFilter(CorsConfigurationSource configSource) {
        super(configSource);
    }

    @Bean
        public CorsFilter corsFilter() {
            //1.添加CORS配置信息
            CorsConfiguration config =new CorsConfiguration();
            //放行哪些原始域
            config.addAllowedOrigin("*");
            //是否发送Cookie信息
            config.setAllowCredentials(true);
            //放行哪些原始域(请求方式)
            config.addAllowedMethod("*");
            //放行哪些原始域(头部信息
            config.addAllowedHeader("*");
            //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
            config.addExposedHeader("*");

            //2.添加映射路
            UrlBasedCorsConfigurationSource configSource =new UrlBasedCorsConfigurationSource();
            configSource.registerCorsConfiguration("/**", config);

            //3.返回新的CorsFilter.
            return new CorsFilter(configSource);
        }

}

```



## 3.2 SpringSecurity配置

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //创建一个PasswordEncoder注入容器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private CustomCorsFilter customCorsFilter;
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .antMatchers(
                        "/user/login"
                        , "/user/register").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(jwtAuthenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(customCorsFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

```

# 4. 注意事项

本项目为前后端分离项目，关于前端的Vue工程请访问 [Vue项目地址]([LauraBest23331/mall_vue_manager_demo (github.com)](https://github.com/LauraBest23331/mall_vue_manager_demo))。

