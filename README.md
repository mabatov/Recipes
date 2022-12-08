![image](https://user-images.githubusercontent.com/22905040/202402593-99fd44cc-e148-4a70-a718-85e7300d2062.png)

An urge to cook something special is too hard to resist sometimes. But what if you lost the recipe? Or your beloved grandma is too busy to answer a call and remind you of your favorite cake recipe? Here is a program that stores all recipes in one place. It's a multi-user web service with Spring Boot that allows storing, retrieving, updating, and deleting recipes.

**Stage 1/5: First recipe**

Implement a simple JSON API that supports adding and retrieving a recipe.

**Stage 2/5: Multiple recipes**

Improve the web service to support multiple recipes.

**Stage 3/5: Store a recipe**

Add a database to store and delete recipes.

**Stage 4/5: Sort & update**

Retrieve recipes by their category/name and update them if you need to.

**Stage 5/5: More chefs to table**

Improve the service to support registration and multiple users.

**Docker configuration:**

Add Dockerfile:
```
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} recipebook.jar
ENTRYPOINT ["java","-jar","/recipebook.jar"]
```
Pass in the ```JAR_FILE``` as part of the docker command (for Gradle):
```
docker build --build-arg JAR_FILE=build/libs/*.jar -t org.hyperskill/recipebook .
```
Then remove ```ARG``` and hardcode the JAR location:
```
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY build/libs/*.jar recipebook.jar
ENTRYPOINT ["java","-jar","/recipebook.jar"]
```
Build an image and run it:
```
docker build -t org.hyperskill/recipebook .

docker run -p 8881:8881 --name recipebook org.hyperskill/recipebook
```