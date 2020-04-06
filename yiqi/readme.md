###错误
    Caused by: org.apache.ibatis.exceptions.PersistenceException: 
     Error querying database.  Cause: java.lang.RuntimeException: 在系统中发现了多个分页插件，请检查系统配置!
     Cause: java.lang.RuntimeException: 在系统中发现了多个分页插件，请检查系统配置!
     
 ####下面的保留一个就够了   pagehelper-spring-boot-starter这东西与<artifactId>pagehelper</artifactId>重复
     <!-- pagehelper分页插件依赖  原来的版本  pagehelper-spring-boot-starter1.2.5     现在报错  java.lang.RuntimeException: 在系统中发现了多个分页插件，请检查系统配置!-->
             <!--<dependency>
                 <groupId>com.github.pagehelper</groupId>
                 <artifactId>pagehelper-spring-boot-starter</artifactId>
                 <version>1.2.5</version>
             </dependency>-->
             <!--导入pagehelper相关依赖   现在的版本 -->
             <dependency>
                 <groupId>com.github.pagehelper</groupId>
                 <artifactId>pagehelper</artifactId>
                 <version>5.1.2</version>
             </dependency>
     <!--        <dependency>-->
     <!--            <groupId>com.github.pagehelper</groupId>-->
     <!--            <artifactId>pagehelper-spring-boot-autoconfigure</artifactId>-->
     <!--            <version>1.2.3</version>-->
     <!--        </dependency>-->
     <!--        <dependency>-->
     <!--            <groupId>com.github.pagehelper</groupId>-->
     <!--            <artifactId>pagehelper-spring-boot-starter</artifactId>-->
     <!--            <version>1.2.3</version>-->
     <!--        </dependency>-->
     
     
     
     


        
  ##部署错误
        
    E325: ATTENTION
    Found a swap file by the name "conf/.server.xml.swp"
              owned by: root   dated: Wed Jan  1 22:22:30 2020
             file name: /junhao/server/tomcat9.0/conf/server.xml
              modified: no
             user name: root   host name: izwz9iw2bqogma2tkikyi1z
            process ID: 28737 (still running)
    While opening file "conf/server.xml"
                 dated: Wed Jan  1 19:57:12 2020
    
    (1) Another program may be editing the same file.  If this is the case,
        be careful not to end up with two different instances of the same
        file when making changes.  Quit, or continue with caution.
    (2) An edit session for this file crashed.
        If this is the case, use ":recover" or "vim -r conf/server.xml"
        to recover the changes (see ":help recovery").
        If you did this already, delete the swap file "conf/.server.xml.swp"
        to avoid this message.
    
    Swap file "conf/.server.xml.swp" already exists!
    [O]pen Read-Only, (E)dit anyway, (R)ecover, (Q)uit, (A)bort:

解决 ： 多出来的.server.xml.swp删掉



一、打成war包发布到tomcat（这步已经完成，自行跳过）
1. pom.xml
去掉内嵌tomcat

`
     <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <scope>provided</scope><!-- 打成war的时候打开注释，本地开发用内嵌tomcat时加上注释 -->
    </dependency>
`

打成war

`
    <packaging>war</packaging>
`

2. 启动类
`
    @SpringBootApplication
    @ComponentScan(basePackages = "com.yzker")
    public class Application extends SpringBootServletInitializer {

        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(Application.class);
        }

        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }


    }

`

mvn clean package 将war包放到tomcat下试试吧，如果报404，参考下面
访问地址：localhost:{tomcat端口号} /重命名的war包名/xxxx

二、404解决办法
原因后续补充

1. pom.xml
`
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-legacy</artifactId>
        <version>1.1.0.RELEASE</version>
    </dependency>
`

2. 修改web.xml
复制下面的代码，修改com.yzker.test.application.Application为你自己的启动类

`
    <?xml version="1.0" encoding="UTF-8"?>
    <web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee"
     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">

      <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>com.yzker.test.application.Application</param-value>
      </context-param>

      <listener>
        <listener-class>org.springframework.boot.legacy.context.web.SpringBootContextLoaderListener</listener-class>
      </listener>

      <!--
          <filter>
              <filter-name>metricFilter</filter-name>
              <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
          </filter>

          <filter-mapping>
              <filter-name>metricFilter</filter-name>
              <url-pattern>/*</url-pattern>
          </filter-mapping>
      -->

      <servlet>
        <servlet-name>appServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
          <param-name>contextAttribute</param-name>
          <param-value>org.springframework.web.context.WebApplicationContext.ROOT</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
      </servlet>

      <servlet-mapping>
        <servlet-name>appServlet</servlet-name>
        <url-pattern>/</url-pattern>
      </servlet-mapping>

</web-app>
`

3. 到此为止，404的问题解决，如果还有问题，请评论，我们共同解决记录
mvn clean package
将target目录下生成war包放到tomcat里吧
访问地址：localhost:{tomcat端口号} /重命名的war包名/xxxx


————————————————
版权声明：本文为CSDN博主「far.liu」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/gege87417376/article/details/79204183