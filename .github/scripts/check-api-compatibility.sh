set -euo pipefail

if [ "$#" -lt 4 ] || [ "$#" -gt 6 ]; then
  echo 'usage: check-api-compatibility.sh japicmp.jar baseline.jar candidate.jar report-directory [baseline-classpath] [candidate-classpath]' >&2
  exit 2
fi

tool=$1
baseline=$2
candidate=$3
report_directory=$4
mkdir -p "$report_directory"

arguments=(
  --old "$baseline"
  --new "$candidate"
  -a protected
  --include pl.wmsdev.usos4j
  --only-modified
  --error-on-binary-incompatibility
  --error-on-source-incompatibility
  --reproducible-report
  --html-file "$report_directory/report.html"
  --xml-file "$report_directory/report.xml"
)
if [ -n "${5:-}" ]; then
  arguments+=(--old-classpath "$5")
fi
if [ -n "${6:-}" ]; then
  arguments+=(--new-classpath "$6")
fi

if java -jar "$tool" "${arguments[@]}" > "$report_directory/report.txt" 2>&1; then
  cat "$report_directory/report.txt"
else
  result=$?
  cat "$report_directory/report.txt"
  exit "$result"
fi
