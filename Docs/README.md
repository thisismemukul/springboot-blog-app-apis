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


   

