#!/usr/bin/env bash

TOOLS_DIR=$(dirname $(realpath "$0"))

MAIN_POM=${TOOLS_DIR}/../../../pom.xml

function findModules() {
  grep '<module>' ${MAIN_POM} | \
  grep -v -E "maven|archunit" | \
  grep -E "common|order|booking|configuration" | \
  sed 's#\(<module>\)\|\(</module>\)##g' | \
  sed 's#\(common/\)\|\(order/\)\|\(booking/\)\|\(configuration/\)##g'
}

findModules | while read module; do

  echo "        <dependency>"
  echo "            <groupId>io.github.hex-arch-training</groupId>"
  echo "            <artifactId>${module}</artifactId>"
  echo "            <version>\${revision}</version>"
  echo "        </dependency>"
done
