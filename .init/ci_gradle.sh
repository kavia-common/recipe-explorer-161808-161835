#!/usr/bin/env bash
set -euo pipefail

# CI entrypoint to run Gradle without relying on ./gradlew being executable.
# Usage: bash recipe-explorer-161808-161835/.init/ci_gradle.sh :recipe_app_frontend:app:build

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

# Ensure wrapper jar is set and run gradle via bootstrap
bash "$ROOT/bootstrap_gradle.sh" "$@"
