#include <stdbool.h>

bool isPrime(int num) {
    if (num <= 1)
        return false;
    if (num == 2 || num == 3)
        return true;
    if (num % 2 == 0 || num % 3 == 0)
        return false;
    for (int i = 5; i * i <= num; i+=6) {
        if (num % i == 0 || num % (i + 2) == 0)
            return false;
    }
    return true;
}

int maximumPrimeDifference(int* nums, int numsSize) {
    int min = 0;
    int max = 0;
    bool first = true;
    for (int i = 0; i < numsSize; i++) {
        if (isPrime(nums[i])) {
            if (first) {
                first = false;
                min = i;
            }
            max = i;
        }
    }
    return max - min;
}


