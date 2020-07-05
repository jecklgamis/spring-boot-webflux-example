#!/usr/bin/env bash
for  a in $(git log --oneline | awk '{ print $1 }'); do
  echo "$a"
done