#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
MODULE_JAR="$ROOT/recipe_app_frontend/gradle/wrapper/gradle-wrapper.jar"
ROOT_JAR_DIR="$ROOT/gradle/wrapper"
ROOT_JAR="$ROOT_JAR_DIR/gradle-wrapper.jar"

mkdir -p "$ROOT_JAR_DIR"

if [[ ! -f "$ROOT_JAR" ]]; then
  if [[ -f "$MODULE_JAR" ]]; then
    cp "$MODULE_JAR" "$ROOT_JAR"
  else
    echo "Error: Module Gradle wrapper jar not found at $MODULE_JAR" 1>&2
    exit 127
  fi
fi

# Ensure gradlew script exists; if not, use bash shim
if [[ -f "$ROOT/gradlew" ]]; then
  bash "$ROOT/gradlew" "$@"
else
  bash "$ROOT/gradlew.sh" "$@"
fi
