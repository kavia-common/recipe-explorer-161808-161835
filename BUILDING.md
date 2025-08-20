Build instructions for this workspace

1) Preferred (wrapper shim)
- From the workspace root:
  bash ./gradlew.sh :recipe_app_frontend:app:assembleDebug

2) Alternative (module wrapper)
- From the workspace root:
  bash recipe_app_frontend/gradlew :app:assembleDebug

3) If your CI requires ./gradlew at the root:
- Copy the module wrapper jar to the root and ensure executable bit:
  mkdir -p gradle/wrapper
  cp recipe_app_frontend/gradle/wrapper/gradle-wrapper.jar gradle/wrapper/gradle-wrapper.jar
  chmod +x ./gradlew
  ./gradlew :recipe_app_frontend:app:assembleDebug

Notes:
- The Android app uses Declarative Gradle DSL. The namespace is configured in app/build.gradle.dcl (org.example.app), do not add package to Manifest.
- Launch icons are vector/adaptive XMLs; no binary PNGs are required for building.
