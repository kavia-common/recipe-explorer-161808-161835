Contributing guidelines

Build locally
- From the workspace root:
  bash recipe-explorer-161808-161835/run_ci.sh

Alternative
- Bootstrap wrapper jar and run any Gradle task:
  bash recipe-explorer-161808-161835/bootstrap_gradle.sh :recipe_app_frontend:app:assembleDebug

Troubleshooting
- If CI or your environment reports: "./gradlew: No such file or directory"
  - Ensure gradle/wrapper/gradle-wrapper.jar exists at the workspace root (the bootstrap script copies it from the module).
  - Run via bash explicitly to avoid executable-bit issues:
      bash ./gradlew :recipe_app_frontend:app:build
  - Or use the provided scripts above.

Project layout
- Android app module path: recipe_app_frontend/app
- Declarative Gradle DSL settings: recipe_app_frontend/settings.gradle.dcl
- Root settings include: settings.gradle.dcl
