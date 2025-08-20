#!/usr/bin/env bash
set -euo pipefail

# CI-safe build entrypoint. Always call via:
#   bash recipe-explorer-161808-161835/ci_build_android.sh
#
# It ensures the Gradle wrapper JAR is available at root, then runs the app build.

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Prepare wrapper jar and run Gradle using our bootstrap script
bash "$ROOT/bootstrap_gradle.sh" :recipe_app_frontend:app:build
