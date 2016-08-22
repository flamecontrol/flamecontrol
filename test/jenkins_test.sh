#!/bin/sh
python3 testkey.py
echo $?
if[$? -ne 0];
then
	echo 1
	exit 1
else
	exit 0
fi
