# Workspace Root

This repository includes a recipe_app_frontend Android project using the Declarative Gradle DSL.

Building:
- From the workspace root, run: ./gradlew :recipe_app_frontend:app:build
  - A proxy wrapper script is provided at the root to delegate to the module wrapper.

Notes:
- If the environment requires the Gradle Wrapper JAR at the root, consider copying it from the module:
  cp recipe_app_frontend/gradle/wrapper/gradle-wrapper.jar gradle/wrapper/gradle-wrapper.jar
- Binary files cannot be written by this agent; use shell copy if necessary.
