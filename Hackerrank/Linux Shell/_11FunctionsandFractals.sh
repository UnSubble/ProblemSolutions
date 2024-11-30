middle_gap=;

print_dash() {
    for (( i=0; i<$1; i++ ))
    do 
        printf "%s" "_"
    done
}

print_1() {
    printf "%s" "1"
}

print_new_line() {
    printf '%s\n' "$LS"
}

print_gap() {
    times=0
    n=1
    while (( n <= $1 ))
    do
    times=$( echo "$times+2^(6-$n)" | bc )
    (( n++ ))
    done
    for j in $( seq 1 $(( 63-$times )) )
    do
        print_dash 100
        print_new_line
    done
}

print_tail() {  
    side_gap=$(( 100-($4-1)*$2-$4 )) 
    for i in $( seq 1 $3 )
    do
        print_dash $(( $side_gap/2 ))
        for j in $( seq 2 $4 )
        do
            print_1
            print_dash $2
        done
        print_1
        print_dash $(( $side_gap-$side_gap/2))
        print_new_line
    done
}

print_head() {
    i_cpy=$2
    for i in $( seq 1 $2 )
    do
        between_gap=$( echo "2*$i_cpy-1" | bc )
        side_gap=$(( 100-$3*($middle_gap+2)-($3-1)*$between_gap ))
        print_dash $(( $side_gap/2 ))       
        for j in $( seq 2 $3 )
        do
            print_1
            print_dash $middle_gap
            print_1
            print_dash $between_gap
        done
        print_1
        print_dash $middle_gap
        print_1
        print_dash $(( $side_gap-$side_gap/2 ))
        middle_gap=$(( $middle_gap-2 ))
        print_new_line
        (( i_cpy++ ))
    done
}

read input

print_gap $input

while (( $input >= 1 ))
do
    count=$( echo "2^(5-$input)" | bc )
    middle_gap=$(( $count*2-1 ))
    number_of_forks=$( echo "2^($input-1)" | bc )
    print_head $(( 6-$input )) $count $number_of_forks
    print_tail $(( 6-$input )) $(( $count*4-1 )) $count $number_of_forks
    (( input-- ))
done
