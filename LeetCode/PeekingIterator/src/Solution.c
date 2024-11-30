/*
 *	struct Iterator {
 *		// Returns true if the iteration has more elements.
 *		bool (*hasNext)();
 *
 * 		// Returns the next element in the iteration.
 *		int (*next)();
 *	};
 */

struct PeekingIterator {
    struct Iterator* iterator;
    int* nextNum;
};

void next_num(struct PeekingIterator* obj);

struct PeekingIterator* Constructor(struct Iterator* iter) {
    struct PeekingIterator* piter = malloc(sizeof(struct PeekingIterator));
    piter->iterator = iter;
    piter->nextNum = NULL;
    return piter;
}

int peek(struct PeekingIterator* obj) {
    if (obj->nextNum == NULL)
        next_num(obj);
    return obj->nextNum;
}

int next(struct PeekingIterator* obj) {
    if (obj->nextNum == NULL)
        next_num(obj);
    int num = obj->nextNum;
    next_num(obj);
    return num;
}

bool hasNext(struct PeekingIterator* obj) {
    return obj->nextNum != NULL || obj->iterator->hasNext();
}

void next_num(struct PeekingIterator* obj) {
    if (obj->iterator->hasNext())
        obj->nextNum = obj->iterator->next();
    else
        obj->nextNum = NULL;
}

/**
 * Your PeekingIterator struct will be instantiated and called as such:
 * PeekingIterator* obj = peekingIteratorCreate(arr, arrSize);
 * int param_1 = peek(obj);
 * int param_2 = next(obj);
 * bool param_3 = hasNext(obj);
 * peekingIteratorFree(obj);
*/
