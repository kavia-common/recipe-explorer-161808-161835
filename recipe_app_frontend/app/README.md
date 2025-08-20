Recipe Explorer - App module

Features:
- Authentication (mock), Home, Search, Favorites, Profile
- Card-based recipe list with bottom navigation
- Light, modern theme with primary #4CAF50, secondary #FFC107, accent #FF5722

Build:
- From workspace root, prefer:
  bash recipe-explorer-161808-161835/ci_build_android.sh
  or
  bash recipe-explorer-161808-161835/bootstrap_gradle.sh :recipe_app_frontend:app:build

Notes:
- Manifest relies on Gradle namespace (org.example.app); no package attribute is set.
- Favorites and session use DataStore.
- Images load via Picasso.
