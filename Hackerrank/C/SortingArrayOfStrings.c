#include <stdlib.h>
#include <string.h>
#include <stdio.h>


int lexicographic_sort(const char* a, const char* b) {
    size_t length_a = strlen(a);
    size_t length_b = strlen(b);

    for (size_t i = 0; i < length_a && i < length_b; i++) {
        char ac = a[i];
        char bc = b[i];
        if (ac - bc != 0) {
            return ac - bc;
        }
    }

    return length_a - length_b;
}

int lexicographic_sort_reverse(const char* a, const char* b) {
    return -lexicographic_sort(a, b);
}

int sort_by_number_of_distinct_characters(const char* a, const char* b) {
    size_t length_a = strlen(a);
    size_t length_b = strlen(b);
    int chars_a[26] = {0};
    int chars_b[26] = {0};
    int a_count = 0;
    int b_count = 0;

    for (size_t i = 0; i < length_a; i++) {
        char ac = a[i];
        if (chars_a[ac - 'a'] == 0) {
            chars_a[ac - 'a']++;
            a_count++;
        }

    }
    for (size_t i = 0; i < length_b; i++) {
        char bc = b[i];
        if (chars_b[bc - 'a'] == 0) {
            chars_b[bc - 'a']++;
            b_count++;
        }
    }
    return a_count - b_count == 0 ? lexicographic_sort(a, b) : a_count - b_count;
}

int sort_by_length(const char* a, const char* b) {
    size_t length_a = strlen(a);
    size_t length_b = strlen(b);

    return length_a - length_b == 0 ? lexicographic_sort(a, b) : length_a - length_b;
}

void string_sort(char** arr,const int len,int (*cmp_func)(const char* a, const char* b)){
    for (size_t i = 0; i < len; i++) {
        char* str = arr[i];
        int j = i - 1;
        while (j >= 0 && cmp_func(str, arr[j]) < 0) {
            char* temp = arr[j];
            arr[j] = arr[j + 1];
            arr[j + 1] = temp;
            j--;
        }
    }
}

int main(void) {
    int l = 2;
    char** arr = malloc(l * sizeof(char*));
    arr[0] = "asd";
    arr[1] = "aa";
    string_sort(arr, l, sort_by_length);
    for (size_t i = 0; i < l; i++) {
        printf("%s\n", arr[i]);
    }
    free(arr);
    return 0;
}