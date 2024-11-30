#! /bin/bash

read yn

if [[ ${yn,,*} = "y" ]]
then
    echo "YES"
else
    echo "NO"
fi