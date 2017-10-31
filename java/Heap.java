import java.util.Arrays;

public class Heap {
    private Node[] heap;
    private int size;
    private int height;

    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.add(new Node<Integer>(4, 4));
        heap.add(new Node<Integer>(5, 5));
        heap.add(new Node<Integer>(3, 3));
        heap.add(new Node<Integer>(2, 2));
        heap.add(new Node<Integer>(8, 8));
        heap.add(new Node<Integer>(1, 1));
        System.out.println(heap.poll().getWeight() == 8);
        System.out.println(heap.poll().getWeight() == 5);
        System.out.println(heap.poll().getWeight() == 4);
        System.out.println(heap.poll().getWeight() == 3);
        System.out.println(heap.poll().getWeight() == 2);
        System.out.println(heap.poll().getWeight() == 1);
        System.out.println(heap.poll() == null);
    }
    public Heap() {
        size = 0;
        heap = new Node[size];
        height = 0;
    }

    public void add(Node node) {
        if (checkFull(size)) {
            heap = Arrays.copyOf(heap, size * 2 + 1);
            height++;
        }
        size++;

        int lastIndex = size-1;
        heap[lastIndex] = node;
        heapUp(lastIndex);
    }

    public Node poll() {
        if (size == 0) {
            return null;
        }
        int lastIndex = size-1;
        swap(heap, lastIndex, 0);
        Node ret = heap[lastIndex];
        heap[lastIndex] = null;
        size--;
        
        heapDown(0);

        if (checkFull(size)) {
            heap = Arrays.copyOf(heap, size);
            height--;
        }
        return ret;

    }

    private void heapUp(int index) {
        if (index == 0) {
            return;
        }
        if (heap[index].getWeight() > heap[(index-1)/2].getWeight()) {
            swap(heap, index, (index-1)/2);
            heapUp((index-1)/2);
        }
    }

    private void heapDown(int index) {
        int leftChild = 2*index + 1;
        int rightChild = leftChild + 1;
        int lastIndex = size-1;
        if (lastIndex >= rightChild && heap[index].getWeight() < heap[rightChild].getWeight() && heap[leftChild].getWeight() < heap[rightChild].getWeight()) {
            swap(heap, index, rightChild);
            heapDown(rightChild);
            return;
        }
        if (lastIndex >= leftChild && heap[index].getWeight() < heap[leftChild].getWeight()) {
            swap(heap, index, leftChild);
            heapDown(leftChild);
            return;
        }
    }

    public void swap(Node[] heap, int a, int b) {
        Node temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    public boolean checkFull(int size) {
        size ++;
        while (size > 1) {
            if (size % 2 == 1) {
                return false;
            }
            size /= 2;
        }
        return true;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    @Override
    public String toString() {
        return "heap: " + Arrays.toString(heap) + ", size: " + size + ", height:" + height;
    }

    public static class Node<T> {
        private T t;
        private int weight;

        public Node(T t, int weight) {
            this.t = t;
            this.weight = weight;
        }

        public T getT() {
            return this.t;
        }

        public int getWeight() {
            return this.weight;
        }

        @Override
        public String toString() {
            return "(Node: " + t + ", weight: " + weight + ")";
        } 
    }
}