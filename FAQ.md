FAQ

Q: CI fails with "./gradlew: No such file or directory"
A:
- Use one of the provided scripts from the workspace root:
  1) bash recipe-explorer-161808-161835/run_ci.sh
  2) bash recipe-explorer-161808-161835/bootstrap_gradle.sh :recipe_app_frontend:app:build
  3) bash recipe-explorer-161808-161835/.init/ci_gradle.sh :recipe_app_frontend:app:build

- If your CI must call "./gradlew" explicitly:
  - Ensure gradle/wrapper/gradle-wrapper.jar exists at the workspace root:
      mkdir -p gradle/wrapper
      cp recipe_app_frontend/gradle/wrapper/gradle-wrapper.jar gradle/wrapper/gradle-wrapper.jar
  - Then run:
      bash ./gradlew :recipe_app_frontend:app:build

Q: How to install the app locally?
A:
- From a machine with Android SDK:
  - Run one of the scripts above to build.
  - Install debug APK onto a connected device:
      ./gradlew :recipe_app_frontend:app:installDebug

Q: What features are included?
A:
- Authentication (mock email/password with DataStore session)
- Home (browse recipes), Search (with empty state), Favorites (persisted via DataStore), Profile (logout, About)
- Recipe detail view (image, metadata, ingredients, instructions)
- Bottom navigation; light theme with colors:
  primary #4CAF50, secondary #FFC107, accent #FF5722

Q: Any external services?
A:
- No backend; recipes are mock data. Networking is used by Picasso for images (Internet permission included).
