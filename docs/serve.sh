#!/usr/bin/env bash
set -euo pipefail

DOCS_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

export NVM_DIR="$HOME/.nvm"
# shellcheck source=/dev/null
source "$NVM_DIR/nvm.sh"
nvm use 22 --silent

if ! command -v live-server &>/dev/null; then
  echo "Installing live-server..."
  npm install -g live-server
fi

echo "Serving docs at http://localhost:8080"
live-server "$DOCS_DIR/build/html" \
  --port=8080 \
  --no-browser
