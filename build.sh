#!/usr/bin/env bash
set -euo pipefail

# Use the bash-friendly shim to avoid executable permission issues with ./gradlew
bash "$(dirname "$0")/gradlew.sh" :recipe_app_frontend:app:assembleDebug
