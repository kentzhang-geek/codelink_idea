#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT_DIR"

NEW_VERSION="${1:-}"

if [[ -n "$NEW_VERSION" ]]; then
  if [[ ! "$NEW_VERSION" =~ ^[0-9]+\.[0-9]+\.[0-9]+([.-][0-9A-Za-z.-]+)?$ ]]; then
    echo "Invalid version: $NEW_VERSION"
    echo "Expected SemVer, e.g. 0.0.3 or 1.2.0-beta.1"
    exit 1
  fi

  sed -i -E "s/^pluginVersion\s*=\s*.*/pluginVersion = ${NEW_VERSION}/" gradle.properties
  echo "Updated pluginVersion to ${NEW_VERSION} in gradle.properties"
fi

echo "Packaging plugin ZIP..."
./gradlew clean buildPlugin

echo "Done. ZIP file(s):"
ls -1 build/distributions/*.zip
