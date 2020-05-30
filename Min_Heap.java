public class MinIntHeap {
    // variables
    private int capacity = 10;
    private int size = 0;
    // heap array
    int[] items = new int[capacity];

    // helper functions ----
    // indexes are found through a formula to avoid overhead of having node classes
    private int getLeftChildIndex(int parendIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    // check existence
    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    // get child or parent
    private int leftChild(int index) {
        return items[getLeftChildIndex(index)];
    }

    private int rightChild(int index) {
        return items[getRightChildIndex(index)];
    }

    private int parent(int index) {
        return items[getParentIndex(index)];
    }

    // swap
    private void swap(int indexOne, int indexTwo) {
        int temp = items[indexOne];
        items[indexOne] = items[indexTwo];
        items[indexTwo] = temp;
    }

    // heap capacity
    private void ensureExtraCapacity() {
        if (size == capacity) {
            items = Arrays.copyOf(items, capacity * 2);
            capacity *= 2;
        }
    }

    // peek the top (min) element :: O(1)
    public int peek() {
        if (size == 0)
            throw new IllegalStateException();
        return items[0];
    }

    // remove and return the root (min) element :: O(Log n)
    public int poll() {
        // if no elements
        if (size == 0)
            throw new IllegalStateException();
        // grab min element
        int item = items[0];
        // copy last element in array and move to first element
        items[0] = items[size - 1];
        // shrink the size
        size--;
        // adjust heap as needed
        heapifyDown();
        return item;
    }

    // add an element :: O(Log n)
    public void add(int item) {
        // check size
        ensureExtraCapacity();
        // add item
        items[size] = item;
        size++;
        // adjust heap
        heapifyUp();
    }

    // adjust heap upwards
    public void heapifyUp() {
        // start with last element in array
        int index = size - 1;
        // walk upwards through parent, swapping if needed
        while(hasParent(index) && parent(index) > items[index]){
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    //adjust heap downwards
    public void heapifyDown(){
        //start at the min element
        int index = 0;
        //walk downwards through smaller of two possible children
        while(hasLeftChild(index)){
            //find smaller child
            int smallerChildIndex = getLeftChildIndex(index);
            if(hasRightChild(index)&& rightChild(index)< leftChild(index)){
                smallerChildIndex = getRightChildIndex(index);
            }
            //if item is smaller than children, break
            if(items[index] < items[smallerChildIndex]){
                break;
            } else { //otherwise swap and continue
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }

}