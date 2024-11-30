#include <stdbool.h>

double myPow(double x, long n) {
    if (n == 0)
        return 1;
    if (x == 1)
        return 1;
    if (x == -1)
        return n % 2 == 0 ? 1 : -1;
    bool isNegative = n < 0;
    if (n < 0)
        n = -n;
    double cache = x;
    long cacheN = 1;
    long m = 1;
    double res = x;
    while (m < n) {
        res *= cache;
        m += cacheN;
        cache *= cache;
        cacheN *= 2;
        if (m + cacheN > n) {
            cacheN = 1;
            cache = x;
        }              
    }    
    return isNegative ? 1 / res : res;
}

