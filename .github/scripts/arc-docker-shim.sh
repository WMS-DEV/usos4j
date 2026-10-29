#!/usr/bin/env bash
set -euo pipefail

if [ -z "${DOCKER_HOST:-}" ] || [ -S /var/run/docker.sock ]; then
  echo "Docker works as it is, nothing to do"
  exit 0
fi

sudo mv /usr/bin/docker /usr/bin/docker.real
sudo tee /usr/bin/docker > /dev/null <<SHIM
#!/bin/bash
export DOCKER_HOST="${DOCKER_HOST}"
real=/usr/bin/docker.real

if [ "\${1:-}" != "run" ]; then
  exec "\$real" "\$@"
fi
shift

create_arguments=()
mounts=()
while [ \$# -gt 0 ]; do
  case "\$1" in
    -v|--volume) mounts+=("\$2"); shift 2 ;;
    -e|--env|-w|--workdir|--entrypoint|--name|-u|--user|--network) create_arguments+=("\$1" "\$2"); shift 2 ;;
    --rm|-i|-t|-it|-d) shift ;;
    -*) create_arguments+=("\$1"); shift ;;
    *) break ;;
  esac
done

container=\$("\$real" create "\${create_arguments[@]}" "\$@")
trap '"\$real" rm -f "\$container" > /dev/null 2>&1' EXIT

stage=\$(/usr/bin/mktemp -d)
trap '"\$real" rm -f "\$container" > /dev/null 2>&1; /usr/bin/rm -rf "\$stage"' EXIT
for mount in "\${mounts[@]}"; do
  source_path="\${mount%%:*}"
  target="\${mount#*:}"
  target="\${target%%:*}"
  /usr/bin/mkdir -p "\$stage\$(/usr/bin/dirname "\$target")"
  /usr/bin/cp -a "\$source_path" "\$stage\$target"
done
/usr/bin/tar --owner=0 --group=0 --numeric-owner -C "\$stage" -cf - . | "\$real" cp - "\$container:/"

"\$real" start -a "\$container"
SHIM
sudo chmod +x /usr/bin/docker
echo "Installed a docker wrapper for ${DOCKER_HOST}: bind mounts are copied into the container because the daemon shares no folders with the runner"
