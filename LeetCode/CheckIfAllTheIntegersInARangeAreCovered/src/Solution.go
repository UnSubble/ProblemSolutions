package main

func isCovered(ranges [][]int, l int, r int) bool {
    arr := make([]int, 51)
	for _, n := range ranges {
        n[0] = max(l, n[0])
        n[1] = min(r, n[1])
        for i := n[0]; i <= n[1]; i++ {
            arr[i]++
        }  
	}
	for i := l; i <= r; i++ {
        if arr[i] == 0 {
            return false
        }
    }
    return true
}


