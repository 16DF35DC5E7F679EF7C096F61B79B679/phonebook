# Getting Started

## Start the spring boot application:

* Create a jar: `mvn clean install -DskipTests`
* `java -jar path/to/your/jarfile.jar fully.qualified.package.Application`

## Run APIs:
* Create contact
    * http://localhost:8081/phonebook/api/v1/contact : POST
    * Body: `{
      "number": "<number>",
      "firstName": "Harsha 1",
      "lastName": "Vardhan"
      }`
* Get list of contacts:
    * http://localhost:8081/phonebook/api/v1/contact : GET
* Get Contact details:
    * http://localhost:8081/phonebook/api/v1/contact/<id> : GET
* Update contact:
    * http://localhost:8081/phonebook/api/v1/contact/<id> : PUT
    * Body: `{
      "lastName": "New Vardhan"
      }`
* Delete Contact:
    * http://localhost:8081/phonebook/api/v1/contact/<id> : DELETE
* Search by last name:
    * http://localhost:8081/phonebook/api/v1/contact/search?lastName=Vardhan : GET
### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.4/maven-plugin/reference/html/#build-image)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/3.0.4/reference/htmlsingle/#appendix.configuration-metadata.annotation-processor)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/3.0.4/reference/htmlsingle/#web.servlet.spring-mvc.template-engines)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.4/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.4/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.0.4/reference/htmlsingle/#using.devtools)

### Guides

The following guides illustrate how to use some features concretely:

* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)


16DF35DC5E7F679EF7C096F61B79B679
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
