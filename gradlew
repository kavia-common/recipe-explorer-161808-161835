#!/usr/bin/env bash
# Forwarder shim at workspace container directory to delegate to the app wrapper.
set -euo pipefail

TARGET="recipe_app_frontend/gradlew"
if [ ! -x "$TARGET" ]; then
  echo "Error: Target gradlew not found at $TARGET"
  exit 127
fi

exec "$TARGET" "$@"
