public class StringHeap {
    private int _size = 0;
    private String[] _heapBase;

    Heap heap = new Heap();

    public StringHeap(int capacity) {
        _heapBase = new String[capacity];
    }

    public void insert(String item) {
        _heapBase[_size++] = item;
    }

    public String replace(String item) {
        String smallest = _heapBase[0];

        _heapBase[0] = item;
        heap.downHeapFrom();

        return smallest;
    }

    public int size() {
        return _size;
    }

    public int capacity() {
        return _heapBase.length;
    }
}
