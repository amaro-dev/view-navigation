#!/bin/bash

branch=$(git rev-parse --abbrev-ref HEAD)
echo $branch

fix=$(git rev-list --count develop..$branch)
IFS='-' read -ra strarr <<< "$branch"
version="${strarr[1]}".$fix

changes='s/pom.version\=.*/pom.version='$version'/g'
sed -i -e $changes navigation/gradle.properties

text=$(cat navigation/gradle.properties | grep pom.version)
IFS='=' read -ra strarr <<< "$text"
echo "${strarr[1]}"
