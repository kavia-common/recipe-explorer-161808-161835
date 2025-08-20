@echo off
REM Ensure there is an executable gradle wrapper file at the app root for CI.
REM If the actual wrapper exists here, call it; otherwise attempt self call (no-op safety).

if exist ".\gradlew.bat" (
  call ".\gradlew.bat" %*
  exit /b %ERRORLEVEL%
)

echo Error: gradlew.bat not found in current directory.
exit /b 127
