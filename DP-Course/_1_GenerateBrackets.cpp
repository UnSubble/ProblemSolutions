#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>
#include <stdbool.h>
#include <ctype.h>
#include <time.h>

#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cmath>
#include <algorithm>
#include <vector>
#include <string>
#include <cstring>
#include <map>
#include <unordered_map>
#include <set>
#include <unordered_set>
#include <stack>
#include <queue>
#include <deque>
#include <list>
#include <bitset>
#include <limits>
#include <ctime>

// #define MULTIPLE

using namespace std;

#define fastio ios::sync_with_stdio(false); cin.tie(0);

#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define pb push_back
#define eb emplace_back
#define ff first
#define ss second

typedef long long int ll;
typedef unsigned long long ull;
typedef long double ld;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
typedef vector<int> vi;
typedef vector<ll> vll;
typedef vector<pii> vpii;
typedef vector<pll> vpll;

const int INF = 1e9 + 5;
const ll LINF = 1e18;
const int MOD = 1e9 + 7;

void recPrint(char *str, int i, int j, const int n) {
    if (j == n) {
        cout << str << '\n';
        return;
    }
    if (i < n) {
        str[i + j] = '(';
        ++i;
        recPrint(str, i, j, n);
    } else return;
    while (j < i) {
        str[i + j] = ')';
        ++j;
        recPrint(str, i, j, n);
    }
}

void solve(int tt, int t) {
    char buffer[50] = {0};
    int n;
    cin >> n;
    recPrint(buffer, 0, 0, n);
    // cout << "Case #" << tt+1 << ": " << result << '\n';
}

int main() {
    fastio;

    int tt = 0;
    int t = 1;

    #ifdef MULTIPLE
    cin >> t;
    #endif

    while(tt < t) {
        solve(tt, t);
        ++tt;
    }

    return 0;
}
