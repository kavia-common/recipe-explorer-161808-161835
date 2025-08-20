CI build entrypoints for this workspace

If your CI environment cannot run ./gradlew directly, use one of the following from the workspace root:

1) Self-bootstrapping wrapper:
   bash ./gradlew :recipe_app_frontend:app:build

2) Provided CI script:
   bash recipe-explorer-161808-161835/run_ci.sh

3) Bootstrap wrapper jar then run any task:
   bash recipe-explorer-161808-161835/bootstrap_gradle.sh :recipe_app_frontend:app:assembleDebug

4) Prepare scripts with exec bit then run CI-safe build:
   bash recipe-explorer-161808-161835/prepare_and_build.sh

Notes:
- The Android app codebase is under recipe_app_frontend/.
- The Declarative Gradle DSL settings for the Android app are configured in recipe_app_frontend/settings.gradle.dcl.
- If your CI insists on ./gradlew being present and executable at the workspace root, ensure gradle/wrapper/gradle-wrapper.jar exists (bootstrap_gradle.sh copies it from the module) and run:
  chmod +x ./gradlew && ./gradlew :recipe_app_frontend:app:build
