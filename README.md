# Web-Application
# Quick Start

## With your IDE

1. Open the project in your IDE (e.g. IntelliJ IDEA)
2. Prepare the Database: Run  Using your IDE's "Run Java application", run `shift_manager_pro.dao.DBMigrate` (located in `src/main/java/shift_manager_pro/dao/DBMigrate.java`)
3. Using your IDE's "Run Java application", run `shift_manager_pro.App` (located in `src/main/java/shift_manager_pro/App.java`)
4. Set the project as Maven project
5. Click on Maven on left bar, and select Execute maven goal, insert `mvn flyway:migrate`
6. Go to http://localhost:7000
7. Happy Coding!

## maven

1. Install [Apache Maven](https://maven.apache.org/)
2. In your terminal, go to the root of the project. Then run the following commands
3. `mvn flyway:migrate`
4. `mvn exec:java`
5. Go to http://localhost:7000
6. Happy Coding!

## Project Structure

| Directory | Purpose |
| --- | --- |
| src/main/ | All application(app) code, including main,models, views(HTML files), controllers, SQL scripts and helpers. |
| src/main/java/shift_manager_pro/ | Core application code including main(App.java), model, view, controller and utils. | 
| src/main/java/shift_manager_pro/auth/ | Authentication(login) and access manager classes | 
| src/main/java/shift_manager_pro/controllers/ | It includes controller for parsing user request from view | 
| src/main/java/shift_manager_pro/dao/ | Dao includes simple java classes which contains JDBC logic. Dao(Data Access Object) is a design pattern. | 
| src/main/java/shift_manager_pro/models/ | Classes for managing the data model and tables | 
| src/main/java/shift_manager_pro/utils/ | Helper and util classes. Includes Views.java for mapping and population views, and PasswordGenerators helper class to encrypt the password | 
| src/main/resources| Application view files and assets such as CSS, images and database scripts | 
| src/main/resources/db.migration| Database initialisation SQL scripts for creating tables and inserting values | 
| src/main/resources/public| Applications assets such as cascading style sheets (CSS), JavaScript files, and images | 
| src/main/resources/views| View HTML pages, GUI for users and frontend folders| 
| src/test/java/shift_manager_pro/| Application tests, each test has a folder that is referring the code that is testing   | 
| docs/| Project documentation including README and Framework introduction | 
| docs/README.md| A document that includes how to make the project, FAQ, and project structure | 
| docs/FAQ.md| Frequent Asked Questions | 
| docs/Framework.md| A document that includes an intro to Javalin and Maven | 
| pom.xml| POM is Project Object Model, A Maven XML file, that contains information (such as project dependencies) about the project and configuration details used by Maven to build the project. It contains default values for most projects. | 
| README.md| Main Repository README that explains the assignment setup | 
| .gitignore| Patterns for files that should be ignored by Git. | 
| sef-donadoni.iml| IDE's environment setup file. | 
