#!/bin/bash
>BIG_FILE.file
for (( i = 0; i < 10000000; i++ )); do
    echo i >> BIG_FILE.file
done