Use the following command from the workspace root to build the Android app without relying on file executable permissions:

bash ./gradlew.sh :recipe_app_frontend:app:build

If the environment requires the Gradle Wrapper JAR at the root, copy it from the module:
cp recipe_app_frontend/gradle/wrapper/gradle-wrapper.jar gradle/wrapper/gradle-wrapper.jar
