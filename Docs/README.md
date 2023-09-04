# Spring Blog App APIs

🌱 A Java-based Spring Boot application that provides APIs for a blog app.

## Steps to Create Spring Blog App APIs

🚀 To create a Spring Blog App APIs, you can follow these steps:

1. **Set up your development environment:**
    - Install Java Development Kit (JDK) if you don't have it installed already. ☕
    - Install an Integrated Development Environment (IDE) such as IntelliJ IDEA or Eclipse. 💻
    - Set up your project in the IDE.

2. **Create a new Spring Boot project:**
    - Use the Spring Initializer (https://start.spring.io/) or your IDE's project creation wizard to generate a new Spring Boot project. 🌱
    - Select the required dependencies such as Spring Web, Spring Data JPA, and any other dependencies you may need for your blog app.

3. **Define the project structure:**
    - Create packages for different components of your application, such as `controller`, `config`, `entity`, `exceptions`, `paylodes`, `repository`, `service`, and `utils`. 📁
    - Create necessary Java classes within these packages.

4. **Define the data model:**
    - Create Java classes to represent the entities in your blog app, such as `User`, `BlogPost`, and `Comment`. 📝
    - Annotate these classes with appropriate JPA annotations for mapping to the database tables.

5. **Set up MySQL database:**
   - Install MySQL Server on your machine if you haven't already. 🗃️
   - Launch MySQL Workbench and create a new connection to your MySQL Server. 🚀
   - Create a new Schema named `blog_app_api`. 📝

6. **Configure database connection in Spring Boot:**
   - In your Spring Boot project, open the `application.properties` or `application.yml` file. ⚙️
   - Configure the following properties to establish a connection with your MySQL database:
      - `spring.datasource.url`: Specify the connection URL for your MySQL database, including the database name, in our case `jdbc:mysql://localhost:3306/blog_app_apis`. 🌐
      - `spring.datasource.username`: Provide the username to access your MySQL database. 👤
      - `spring.datasource.password`: Enter the password associated with the username. 🔑
      - `spring.datasource.driver-class-name`: Set the driver class for MySQL, which is typically `com.mysql.cj.jdbc.Driver`. 🚗
      - Setting up env variables click on edit configuration and add `db_url=jdbc:mysql://localhost:3306/blog_app_apis;db_user=root;db_pass=yourpassword`

7. **Configure JPA in Spring Boot:**
    - In your Spring Boot project, open the `application.properties` or `application.yml` file. ⚙️
    - Configure the following properties to establish a connection with your MySQL database:
        - `spring.jpa.properties.hibernate.dialect`. 🌐
        - `spring.jpa.hibernate.ddl-auto=update`: You may check more options like `create`, `create-drop`, `validate`. 👤
        - `spring.jpa.show-sql=true`: To Show SQL queries on console. 🧑‍💻
        - `spring.jpa.properties.hibernate.format_sql=true`: To Format SQL queries on console. 🧑‍💻

8. **At this point our service is ready to up:**
    - Run the main()
   
9. **Map User entity to database tables:**
    - In your Spring Boot project, annotate your entity classes with JPA annotations like `@Entity`, `@Table`, and `@Column`. 🗺️
    - Use additional annotations like `@Id`, `@GeneratedValue`, and `@Pattern` to specify primary keys, generated values, and relationships between entities. 🔑🔄
    - Map the entity fields to the corresponding table columns using annotations such as `@Column`. 🔗

10. **Custom Password Validator:**
    - In your Spring Boot project, Start by setting up your project and including the `passay` dependency. 🛠️
    - Create a new Java class called `PasswordConstraintValidator` in utils package 📦 that implements the `ConstraintValidator` interface. This class will handle the validation logic for a single password field. Inside the class, override the `initialize()` and `isValid()` methods. 🔑🔄
    - Create another Java class called `PasswordFieldsValueMatchValidator` in utils package 📦 that also implements the `ConstraintValidator` interface. This class will handle the validation logic for matching two password fields. Similar to `PasswordConstraintValidator`, override the `initialize()` and isValid() methods. 🔗
    - Annotate your password field that needs validation with `@ValidatePassword` to trigger the password validation using the custom `PasswordConstraintValidator` class.
    - Annotate the password field that you want to validate for matching with `@PasswordValueMatch`.

11. **Implement User repository interfaces:**
    - Create repository interfaces that extend Spring Data JPA's `CrudRepository` or `JpaRepository`.
    - Define methods in the repositories for performing CRUD operations on the entities. ⚙️

12. **Implement the services and paylodes:**
    - Create user service interfaces and their implementation classes and `UserDto` in payloads.
    - Payloads are used to encapsulate and transfer data between the client and the server. 🔐
    - Implement the required business logic and use the repository interfaces for data access. 🗺️

13. **Handling Exceptions make custom `ResourceNotFoundException`:**
    - Write `updateUser` implementation in `UserServiceImpl` and handle exception for User not found by creating custom `ResourceNotFoundException`. 🙈
    - Write `getUserById`,`getUserByUsername`,`getAllUsers`,`deleteUser`. 📰

14. **Implement the controllers:**
    - Create controller classes `UserController` responsible for handling incoming requests and returning appropriate responses. 😎
    - Define methods with appropriate request mappings and annotations such as `@GetMapping`, `@PostMapping`, etc.
    - Don't forgot to create `@Autowired` for `UserService` for automatic dependency injection, where Spring handles the creation and management of the required objects and injects them into the dependent classes. 🔗

15. **Implement the API endpoints:**
    - Within the controller methods, implement the logic to handle the API endpoints `/api/users` such as user registration, etc, blog post creation, comment addition, will be created further on. 🛠️
    - Utilize the services `UserService` to perform necessary operations and return the desired responses. 📰
    - `ResponseEntity` class is used to represent the entire HTTP response, including the status code, headers, and the response body. 🗺️

16. **Exception Handling 🚦**
    - In this project, we implement a `GlobalExceptionHandler` to handle exceptions that occur across the application. 🌍
    - The `GlobalExceptionHandler` class is responsible for handling various exceptions globally. It uses the `@RestControllerAdvice` annotation to handle exceptions across all controllers. 🛠️
    - Create custom exception classes like `ResourceNotFoundException` to represent specific exception scenarios. 💥😎

17. **Model Mapper 🗾**
    - In your Spring Boot project, Start by setting up your project and including the `modelmapper` dependency. 🛠️
    - Create a `@Bean` `ModelMapper` in `BlogAppApisApplication`. 🥜
    - Use `ModelMapper` for mapping `User` to `UserDto` and vice versa. 👻

18. **Creating Category API 🙀**
    - Follow steps 11 to 15. 👻
    - Created files `Category` entity, `CategoryDto`, `CategoryRepo`, `CategoryService`, `CategoryServiceImpl` and `CategoryController`. 🍵
    - Completed the initial version of `CategoryServiceImpl`, implementing methods for CRUD operations and category management. 🎉c    - Created Post Entity.

19. **Creating POST API 📮**
    - Creating Post and Relationship in one go.
    - Edit Category Entity and User Entity to implement `OneToMany Relationship`.
    - ```java 
      @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)    private List<Post> posts = new ArrayList<>();
      ```
    - ```java 
      @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)   private List<Post> posts = new ArrayList<>();
      ```
    - Created files `Post` entity, `PostDto`, `PostResponse`, `PostRepo`, `PostService`, `PostServiceImpl` and `PostController`. 🍵
    - Creating API for `UPDATING` and `DELETING` POST. 📮

20. **Implement Pagination in API 📟**
    - Pagination is the process of dividing a large dataset into smaller, manageable chunks, or "pages," when querying an API.
    - #### Endpoint: Get All Posts
    - The API endpoint for fetching all posts should accept query parameters for pagination:
    -  ```shell 
       /posts?pageNumber={pageNumber}&pageSize={pageSize}
       ```
    - In this implementation:
      - `pageNumber`: Specifies the page number to retrieve (default is 1).
      - `pageSize`: Specifies the number of items per page (default is 5).
    - Below is a part of code from Controller
      - ```java
        @GetMapping("/user/{userId}/posts")
        public ResponseEntity<PostResponse> getPostsByUserId(
        @PathVariable UUID userId,
        @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
        @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize
        ) {
        PostResponse postResponseByUserId = this.postService.getPostsByUser(userId,pageNumber,pageSize);
        return new ResponseEntity<PostResponse>(postResponseByUserId, HttpStatus.OK);
        }
        ```
20. **Implementing Sorting 🔝**
    - In your controller class (PostController), modify the getAllPosts method to accept sorting parameters
    - Here's an example of how to make a GET request to the /posts endpoint with sorting:
      - ```shell
        GET /posts?pageNumber=1&pageSize=20&sortBy=title&sortDir=asc
        ``` 
    - Follow the same step for `getPostsByCategory` and `getPostsByUser`. 👨‍💻
21. **todo**

