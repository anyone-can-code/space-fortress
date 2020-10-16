#/usr/bin/env sh

javac src/*.java src/core/*.java src/game/*.java -d build &&
cd build &&
java Main
