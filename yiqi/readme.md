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