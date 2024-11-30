#! /bin/bash

sum=0

read n

for (( i=0; i<$n; i++ ))
do
    read line
    sum=$(( $sum+$line ))
done

printf %.3f%n $( echo "scale=5;$sum/$n" | bc )