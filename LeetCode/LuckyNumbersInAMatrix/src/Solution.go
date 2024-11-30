package main 

func luckyNumbers(matrix [][]int) []int {
	i := 0
	res := make([]int, 0)
	n := 0
	for i < len(matrix[0]) {
        maxNum := 0
		index := 0
		c := true
		for j := 0; j < len(matrix); j++ {
			if matrix[j][i] > maxNum {
				maxNum = matrix[j][i]
				index = j
				n = 1
			} else if maxNum == matrix[j][i] {
				n++
			}
		}
		for j := 0; j < len(matrix[0]); j++ {
			if matrix[index][j] < maxNum {
				res = make([]int, 0)
				c = false
				break
			}
		}
		if c {
			for j := 0; j < n; j++ {
				res = append(res, maxNum)
			}
			break
		}
		i++
	}
	return res
}
