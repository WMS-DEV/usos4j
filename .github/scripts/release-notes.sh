#!/usr/bin/env bash
set -euo pipefail

PUBLIC_REPO="${PUBLIC_REPO:-WMS-DEV/usos4j}"
previous="$1"
head="$2"
version="$3"

echo "# What's changed"
gh api --paginate "repos/${PUBLIC_REPO}/compare/${previous}...${head}" --jq '
  .commits[]
  | (.commit.message | split("
")[0]) as $subject
  | select($subject | test("^bump release version"; "i") | not)
  | "[\($subject)](https://github.com/'"${PUBLIC_REPO}"'/commit/\(.sha)) by \(if .author.login then "@" + .author.login else .commit.author.name end) "'
echo
echo
echo
echo "**Full Changelog**: https://github.com/${PUBLIC_REPO}/compare/${previous}...${version}"
