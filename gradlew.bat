@echo off
REM Forwarder shim at workspace container directory to delegate to the app wrapper.
set TARGET=recipe_app_frontend\gradlew.bat

if not exist "%TARGET%" (
  echo Error: Target gradlew not found at %TARGET%
  exit /b 127
)

call "%TARGET%" %*
