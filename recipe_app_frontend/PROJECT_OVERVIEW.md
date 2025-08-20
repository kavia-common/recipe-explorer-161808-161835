Recipe App Frontend - Project Overview

Summary
- Android (Kotlin, XML Views, no Compose) mobile app for recipe browsing and management.
- Features: Authentication (mock), Home (browse), Search, Favorites (DataStore), Profile (logout, About), Recipe details.
- UI: Bottom navigation (Home, Search, Favorites, Profile), card-based list, light modern theme.
- Colors: primary #4CAF50, secondary #FFC107, accent #FF5722.

Architecture
- Activities:
  - SplashActivity: routes to Login or Main based on SessionStore.
  - LoginActivity: mock login (email + password).
  - MainActivity: hosts bottom navigation and fragments.
  - RecipeDetailActivity: shows recipe details and favorites toggle.
  - HelpActivity: basic help screen.
- Fragments:
  - HomeFragment, SearchFragment, FavoritesFragment, ProfileFragment.
- Data:
  - RecipeRepository: mock in-memory list.
  - SessionStore: DataStore Preferences for login email.
  - FavoritesStore: DataStore Preferences for favorites set.
- UI/helpers:
  - RecyclerView RecipeAdapter for card items.
  - About include and AboutBinder to render app version.
  - Colors constants.
  - Util: DispatchersProvider, Intents, Keys.

Build and Run (CI-friendly)
- Preferred: from workspace root
  - bash recipe-explorer-161808-161835/run_ci.sh
- Alternative:
  - bash recipe-explorer-161808-161835/bootstrap_gradle.sh :recipe_app_frontend:app:build
  - or bash recipe-explorer-161808-161835/.init/ci_gradle.sh :recipe_app_frontend:app:build

Notes
- If your CI requires ./gradlew at root, ensure gradle/wrapper/gradle-wrapper.jar exists (bootstrap script can copy it from the module), then run:
  chmod +x ./gradlew && ./gradlew :recipe_app_frontend:app:build
- Manifest relies on Gradle namespace org.example.app; do not set the package attribute in the Manifest.
- Internet permission and network security config enabled for image loading via Picasso.
