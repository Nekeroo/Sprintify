#!/bin/sh
set -e
hostport="$1"
shift
host="${hostport%%:*}"
port="${hostport##*:}"

while ! nc -z "$host" "$port"; do
  echo "Waiting for $host:$port..."
  sleep 2
done

echo "$host:$port is available. Starting application."
exec "$@"