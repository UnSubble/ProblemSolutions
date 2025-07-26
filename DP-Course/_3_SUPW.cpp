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

int minimumTime(vi& nums) {
    int *dp = new int[nums.size()];
    dp[0] = nums[0];
    dp[1] = nums[1];
    dp[2] = nums[2];
    for (int i = 3; i < nums.size(); i++) {
        dp[i] = nums[i];
        int a = dp[i - 1];
        if (a >= dp[i - 2]) {
            a = dp[i - 2];
        }
        if (a >= dp[i - 3]) {
            a = dp[i - 3];
        }
        dp[i] += a;
    }
    int min = dp[nums.size() - 1];
    if (min >= dp[nums.size() - 2]) {
        min = dp[nums.size() - 2];
    }
    if (min >= dp[nums.size() - 3]) {
        min = dp[nums.size() - 3];
    }
    delete[] dp;
    return min;
}

void solve(int tt, int t) {
    int n;
    cin >> n;
    vi nums;
    int k = 0;
    while (k < n) {
        int l;
        cin >> l;
        nums.pb(l);
        k++;
    }
    cout << minimumTime(nums) << '\n';
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

