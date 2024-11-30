#! /bin/bash

read line 

printf %.3f $(echo $line | bc -l)