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

build_internal_fixture() {
  name=$1
  constructors=$2
  method=$3
  mkdir -p "$work_directory/$name/classes"
  for class in UsosUserAPI UsosServerAPI; do
    printf 'package pl.wmsdev.usos4j.client; public class %s { %s %s }\n' \
      "$class" "${constructors//CLASS/$class}" "$method" > "$work_directory/$name/$class.java"
    javac --release 17 -d "$work_directory/$name/classes" "$work_directory/$name/$class.java"
  done
  jar --create --file "$work_directory/$name.jar" -C "$work_directory/$name/classes" .
}

build_internal_fixture internal-baseline \
  'public CLASS() {} public CLASS(String id, int revision) {}' \
  'public String value() { return ""; }'
build_internal_fixture internal-candidate \
  'CLASS(String id, int revision, long module) {}' \
  'public String value() { return ""; }'
build_internal_fixture internal-broken \
  'CLASS(String id, int revision, long module) {}' ''

bash "$script_directory/check-api-compatibility.sh" "$tool" \
  "$work_directory/internal-baseline.jar" "$work_directory/internal-candidate.jar" \
  "$work_directory/internal-constructor-report"
if bash "$script_directory/check-api-compatibility.sh" "$tool" \
  "$work_directory/internal-baseline.jar" "$work_directory/internal-broken.jar" \
  "$work_directory/internal-method-report"; then
  echo 'Compatibility gate accepted a removed facade method' >&2
  exit 1
fi
grep -q 'METHOD_REMOVED' "$work_directory/internal-method-report/report.xml"
echo 'Internal constructor changes are accepted while facade method removals are rejected.'
