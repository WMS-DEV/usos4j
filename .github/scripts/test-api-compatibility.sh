set -euo pipefail

tool=$1
script_directory=$(cd "$(dirname "$0")" && pwd)
work_directory=$(mktemp -d)

build_fixture() {
  name=$1
  source=$2
  mkdir -p "$work_directory/$name/classes"
  printf '%s\n' "$source" > "$work_directory/$name/Sample.java"
  javac --release 17 -d "$work_directory/$name/classes" "$work_directory/$name/Sample.java"
  jar --create --file "$work_directory/$name.jar" -C "$work_directory/$name/classes" .
}

build_fixture baseline 'package pl.wmsdev.usos4j.model;
public record Sample(String id) {
  public java.util.List<String> values() { return java.util.List.of(id); }
}'
build_fixture additive 'package pl.wmsdev.usos4j.model;
public record Sample(String id) {
  public java.util.List<String> values() { return java.util.List.of(id); }
  public int length() { return id.length(); }
}'
build_fixture removed 'package pl.wmsdev.usos4j.model;
public record Sample(String id) {}'
build_fixture generic 'package pl.wmsdev.usos4j.model;
public record Sample(String id) {
  public java.util.List<Integer> values() { return java.util.List.of(id.length()); }
}'
build_fixture record 'package pl.wmsdev.usos4j.model;
public record Sample(String id, int revision) {
  public java.util.List<String> values() { return java.util.List.of(id); }
}'

for variant in baseline additive; do
  bash "$script_directory/check-api-compatibility.sh" "$tool" "$work_directory/baseline.jar" "$work_directory/$variant.jar" "$work_directory/$variant-report"
done
for variant in removed generic record; do
  if bash "$script_directory/check-api-compatibility.sh" "$tool" "$work_directory/baseline.jar" "$work_directory/$variant.jar" "$work_directory/$variant-report"; then
    echo "Compatibility gate accepted a breaking $variant change" >&2
    exit 1
  fi
  test -s "$work_directory/$variant-report/report.xml"
done
grep -q 'METHOD_RETURN_TYPE_GENERICS_CHANGED' "$work_directory/generic-report/report.xml"
echo 'Compatibility gate accepts unchanged/additive APIs and rejects removed methods, generic return changes and record constructor changes.'
