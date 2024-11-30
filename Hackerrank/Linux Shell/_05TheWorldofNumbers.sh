#! /bin/bash

read num1 
read num2

echo $( expr $num1 + $num2 )
echo $(( $num1-$num2 ))
echo $(( $num1*$num2 ))
echo $(( $num1/$num2 ))
