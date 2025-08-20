#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Ensure scripts are executable even if the environment stripped exec bits
chmod +x "$ROOT/gradlew.sh" || true
chmod +x "$ROOT/bootstrap_gradle.sh" || true
chmod +x "$ROOT/ci_build_android.sh" || true
chmod -R +x "$ROOT/recipe_app_frontend/gradlew" || true

# Run the CI-safe build that uses our bootstrap
bash "$ROOT/ci_build_android.sh"
