# Simple proxy makefile to run Gradle from the repository root
ROOT := $(abspath $(dir $(lastword $(MAKEFILE_LIST)))/..)

.PHONY: build clean test assemble
build:
	@echo "Running Gradle build from repository root..."
	@cd "$(ROOT)" && ./gradlew :recipe_app_frontend:app:build

clean:
	@cd "$(ROOT)" && ./gradlew :recipe_app_frontend:clean

test:
	@cd "$(ROOT)" && ./gradlew :recipe_app_frontend:app:test

assemble:
	@cd "$(ROOT)" && ./gradlew :recipe_app_frontend:app:assembleDebug
