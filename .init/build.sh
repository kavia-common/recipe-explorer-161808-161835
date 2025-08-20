#!/usr/bin/env bash
set -euo pipefail

# Always execute using bash to avoid executable-permission issues on ./gradlew
REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

# Use the root gradle bootstrap/wrapper script with bash
bash "$REPO_ROOT/gradlew" :recipe_app_frontend:app:assembleDebug
