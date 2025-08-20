# Recipe Explorer - Android App (Kotlin, XML Views)

A modern, light-themed Android app for browsing, searching, and managing recipes with user authentication and favorites.

Features:
- User authentication (mock, email/password)
- Recipe browsing (card-based)
- Search recipes by title
- Recipe detail view with image, ingredients, and instructions
- Favorites management (DataStore persistence)
- Bottom navigation: Home, Search, Favorites, Profile

Tech:
- Kotlin (no Jetpack Compose; XML layouts)
- AndroidX, Material Components
- DataStore Preferences for session and favorites
- RecyclerView lists
- Picasso for image loading

Build:
- ./gradlew build

Note: This project uses the Declarative Gradle DSL (.dcl) with the org.gradle.experimental.android-ecosystem plugin included in settings.gradle.dcl. ViewBinding is enabled via the features block in app/build.gradle.dcl.

Run:
- :app:installDebug and launch Recipe Explorer

Configuration:
- No external backend required (mock data). If integrating a backend, add env via .env and read safely in code (do not hardcode).
