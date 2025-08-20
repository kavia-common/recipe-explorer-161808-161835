#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
MODULE_WRAPPER="$SCRIPT_DIR/recipe_app_frontend/gradlew"

# Prefer module wrapper if present
if [[ -f "$MODULE_WRAPPER" ]]; then
  exec bash "$MODULE_WRAPPER" "$@"
fi

# Fallback to root bootstrap wrapper if present
ROOT_WRAPPER="$SCRIPT_DIR/gradlew"
if [[ -f "$ROOT_WRAPPER" ]]; then
  exec bash "$ROOT_WRAPPER" "$@"
fi

echo "No Gradle wrapper found. Expected one of:
- $MODULE_WRAPPER
- $ROOT_WRAPPER" 1>&2
exit 127
