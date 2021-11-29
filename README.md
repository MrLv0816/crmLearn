Crm_Day01

### 基础环境的搭建

##### pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>cn.konfan</groupId>
    <artifactId>Crm</artifactId>
    <version>1.0-SNAPSHOT</version>
    <name>Crm</name>
    <packaging>war</packaging>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.target>1.8</maven.compiler.target>
        <maven.compiler.source>1.8</maven.compiler.source>
        <junit.version>5.7.1</junit.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>

        <!--Servlet-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
            <scope>provided</scope>
        </dependency>

        <!--JSTL-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>

        <!--springmvc依赖-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>4.3.16.RELEASE</version>
        </dependency>

        <!--Spring的事务-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>4.3.16.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>4.3.16.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>4.3.16.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
            <version>4.3.16.RELEASE</version>
        </dependency>

        <!--jackson-->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.9.0</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.9.0</version>
        </dependency>
        <!--mybatis-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.3.1</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.5</version>
        </dependency>
        <!--mysql驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.9</version>
        </dependency>
        <!--连接池-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.12</version>
        </dependency>

        <!--poi-->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>3.15</version>
        </dependency>

        <!-- 文件上传 -->
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>1.3.1</version>
        </dependency>

        <!--lombok辅助工具-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.20</version>
        </dependency>


    </dependencies>

    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory><!--所在的目录-->
                <includes><!--包括目录下的.properties,.xml文件都会扫描到-->
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
        <plugins>
            <!-- 编码和编译和JDK版本 -->
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

##### web.xml

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>

  <display-name>crm</display-name>

  <!--注册spring的监听器-->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:spring/applicationContext-service.xml</param-value>
  </context-param>


  <!--注册字符集过滤器-->
  <filter>
    <filter-name>characterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>utf-8</param-value>
    </init-param>
    <init-param>
      <param-name>forceRequestEncoding</param-name>
      <param-value>true</param-value>
    </init-param>
    <init-param>
      <param-name>forceResponseEncoding</param-name>
      <param-value>true</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>characterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>

  <!--

    ServletContextListener是web组件，实例化它的是tomcat
    ContextLoader是spring容器的组件，用来初始化容器

  -->
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>

  <!--注册springmvc的中央调度器-->
  <servlet>
    <servlet-name>dispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring/applicationContext-web.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>dispatcherServlet</servlet-name>
    <url-pattern>*.do</url-pattern>
  </servlet-mapping>

  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
  </welcome-file-list>
</web-app>

```

#### Spring配置文件

##### 	applicationContext-web.xml

- 注解扫描,扫描控制层的注解,controller包下的注解

```xml
	 <!--声明组件扫描器-->
    <context:component-scan base-package="cn.konfan.crm.settings.web.controller"/>
    <context:component-scan base-package="cn.konfan.crm.workbench.web.controller"/>
    <context:component-scan base-package="cn.konfan.crm.exception"/>
```

- ​	识图解析器

  - 前缀`/WEB-INF/jsp`

  - `/WEB-INF`目录中式受保护的资源目录,除了识图解析器跳转访问,外部是无法直接访的

  - 后缀`.jsp`

  - 控制层返回的方法即可拼接,找到`jsp`页面的完整路径

    ```xml
        <!--<context:component-scan base-package="cn.konfan.crm"/>-->
        <!--声明视图解析器-->
        <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
            <property name="prefix" value="/WEB-INF/jsp" />
            <property name="suffix" value=".jsp" />
        </bean>
    ```

    

- 注解驱动

  ```xml
      <!--声明注解驱动-->
      <mvc:annotation-driven/>
  ```

- 登陆拦截器

  - 用于权限效验,除了登陆和跳转登陆页面的请求外,其余全部需要登陆才可操作

```xml
   <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/**"/>
            <mvc:exclude-mapping path="/settings/user/login.do"/>
            <bean class="cn.konfan.crm.interceptor.LoginInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>
```

- 文件上传解析器

  - 指定了文件上传的大小

  ```xml
      <!-- 配置文件上传解析器 id:必须是multipartResolver-->
      <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
          <property name="maxUploadSize" value="#{1024*1024*80}"/>
          <property name="defaultEncoding" value="utf-8"/>
      </bean>
  ```

##### applicationContext-service.xml

- 加载dao配置文件

  - 注解扫描,扫描业务层注解,`service`包下注解

  ```xml
      <!--加载dao的配置文件-->
      <import resource="applicationContext-dao.xml"/>
  
      <!--声明组件扫描器-->
      <context:component-scan base-package="cn.konfan.crm.settings.service" />
      <context:component-scan base-package="cn.konfan.crm.workbench.service" />
  ```

- 声明事务控制
  - 切面
    - 以`save`,`update`,`delete`名称开头的方法,需要进行事务控制
  - 切入点
    - 增强`service`包下方法

```xml
    <!--事务配置  声明式事务：在源代码之外，控制事务  声明事务管理器，指定完成事务的具体实体类 commit, rollback-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!--指定数据源DataSource-->
        <property name="dataSource" ref="dataSource" />
    </bean>
    <!--配置通知（切面）：指定方法使用的事务属性  id:自定义的通知名称  transaction-manager：事务管理器 -->
    <tx:advice id="myAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <!--指定方法的事务属性    name:方法名称  isolation：隔离级别  propagation：传播行为
                rollback-for：回滚的异常类， 对于自定义的异常要使用全限定名称，系统的异常类可以名称
            -->
            <tx:method name="save*" isolation="DEFAULT" propagation="REQUIRED"
                       rollback-for="cn.konfan.crm.exception.AjaxRequestException,
                       cn.konfan.crm.exception.TraditionRequestException" />
            <tx:method name="update*" isolation="DEFAULT" propagation="REQUIRED"
                       rollback-for="cn.konfan.crm.exception.AjaxRequestException,
                       cn.konfan.crm.exception.TraditionRequestException" />
            <tx:method name="delete*" isolation="DEFAULT" propagation="REQUIRED"
                       rollback-for="cn.konfan.crm.exception.AjaxRequestException,
                       cn.konfan.crm.exception.TraditionRequestException" />
            <!--除了上面的方法以外的，其他方法-->
            <tx:method name="*" propagation="SUPPORTS" read-only="true" />
        </tx:attributes>
    </tx:advice>
    <!--配置aop-->
    <aop:config>
        <!--指定切入点-->
        <aop:pointcut id="servicePt" expression="execution(* *..service..*.*(..))" />
        <!--配置增强器对象（通知+切入点）-->
        <aop:advisor advice-ref="myAdvice" pointcut-ref="servicePt" />
    </aop:config>

```

##### applicationContext-dao.xml

- 数据库连接池

  - 指定数据库属性配置文件

    - ```xml
      <context:property-placeholder location="classpath:properties/jdbc.properties" />
      ```

    - ```properties
      jdbc.url=jdbc:mysql://localhost:3306/crm?characterEncoding=UTF-8
      jdbc.username=root
      jdbc.password=root
      ```

  - url

  - username

  - password

````xml
    <!--声明DataSource-->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
        <property name="url" value="${jdbc.url}" />
        <property name="username" value="${jdbc.username}" />
        <property name="password" value="${jdbc.password}" />
    </bean>
````

- mybatis整合

  - `SqlSessionFactoryBean` 加载了 `mybatis-config.xml`配置文件

    - ```xml
      <?xml version="1.0" encoding="UTF-8" ?>
      <!DOCTYPE configuration
              PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
              "http://mybatis.org/dtd/mybatis-3-config.dtd">
      <configuration>
      
          <settings>
              <setting name="logImpl" value="STDOUT_LOGGING"/>
          </settings>
          <!--配置别名-->
          <typeAliases>
              <package name="cn.konfan.crm.settings.domain"/>
              <package name="cn.konfan.crm.workbench.domain"/>
          </typeAliases>
      
          <!--配置sql映射文件-->
          <mappers>
              <package name="cn.konfan.crm.settings.dao"/>
              <package name="cn.konfan.crm.workbench.dao"/>
          </mappers>
      
      
      </configuration>
      ```

      - 声明domain别名
      - sql日志输出

  - `MapperScannerConfigurer` 批量加载dao报下的接口,创建代理对象,交给spring容器管理

    - ````xml
          <!--声明MyBatis的扫描器-->
          <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
              <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
              <property name="basePackage" value="cn.konfan.crm.settings.dao,cn.konfan.crm.workbench.dao" />
          </bean>
      ````

