#!/usr/bin/env bash
set -euo pipefail

DOCS_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
OUT_HTML="$DOCS_DIR/build/html"
OUT_IMAGES="$DOCS_DIR/build/images"

# Activate Node 22 for mmdc (Mermaid CLI)
export NVM_DIR="$HOME/.nvm"
# shellcheck source=/dev/null
source "$NVM_DIR/nvm.sh"
nvm use 22 --silent

mkdir -p "$OUT_HTML" "$OUT_IMAGES"

asciidoctor \
  -r asciidoctor-diagram \
  -D "$OUT_HTML" \
  -a imagesoutdir="$OUT_IMAGES" \
  -a imagesdir="../images" \
  -a data-uri \
  "$DOCS_DIR"/*.adoc

echo "Built:"
echo "  HTML   → $OUT_HTML"
echo "  Images → $OUT_IMAGES"
