#!/bin/bash

text=$(cat navigation/gradle.properties | grep pom.version)
IFS='=' read -ra strarr <<< "$text"
echo "${strarr[1]}"
