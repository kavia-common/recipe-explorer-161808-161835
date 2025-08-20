#!/usr/bin/env bash
set -euo pipefail

# Always invoke the bootstrapping Gradle wrapper via bash to avoid executable-bit issues
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
bash "$SCRIPT_DIR/gradlew" :recipe_app_frontend:app:build
