public class GenericLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int count;

    public GenericLinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    public void add(T data) {
        if (data == null) {
            throw new NullPointerException("Data you want to enter does not exist");
        }

        Node<T> newNode = new Node<>(data);

        if (this.isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
            this.count++;
        } else {
            tail.setNext(newNode);
            tail = newNode;
            this.count++;
        }
    }

    public void add(T data, int index) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException();
        }

        if (data == null) {
            throw new NullPointerException();
        }

        if (isEmpty()) {
            this.add(data);
            this.count++;
        } else if (index == (count - 1)) {
            this.add(data);
            this.count++;
        } else {
            int currentIndex = 0;
            Node<T> currentNode = null;
            Node<T> previousNode;
            while (index != currentIndex) {
                previousNode = currentNode;
                currentNode = previousNode.getNextNode();
                currentIndex++;
            }

        }

    }

    public T get(int index) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException();
        }

        if (this.isEmpty()) {
            return null;
        } else {
            if (index == 0) {
                return this.head.getData();
            } else {
                int currentIndex = 0;
                Node<T> currentNode = this.head;
                while (currentIndex != index) {
                    currentNode = currentNode.getNextNode();
                    currentIndex++;
                }
                return currentNode.getData();
            }
        }
    }

    public T replace(T data, int index) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException();
        }

        if (data == null) {
            throw new NullPointerException();
        }

        T replacedData;

        if (index == 0) {
            replacedData = this.head.getData();
        } else if (index == (count - 1)) {
            replacedData = this.tail.getData();
        } else {
            int currentIndex = 0;
            Node<T> currentNode = this.head;
            while (currentIndex != index) {
                currentNode = currentNode.getNextNode();
                currentIndex++;
            }
            replacedData = currentNode.getData();
            currentNode.setData(data);
        }
        return replacedData;
    }

    public T remove(int index) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException();
        }
        T removedData;

        if (count == 1) {
            removedData = this.get(0);
            removeAll();
        } else {

            int currentIndex = 0;
            Node<T> currentNode = this.head;
            Node<T> previousNode = null;
            while (currentIndex != index) {
                previousNode = currentNode;
                currentNode = currentNode.getNextNode();
                currentIndex++;
            }

            if (index == 0) {
                removedData = this.head.getData();
                Node<T> newFirstNode = this.head.getNextNode();
                this.head.setData(null);
                this.head = newFirstNode;
                count--;
            } else if (index == count - 1) {
                removedData = this.tail.getData();
                this.tail = previousNode;
                this.tail.setNext(null);
                count--;
            } else {
                removedData = currentNode.getData();
                previousNode.setNext(currentNode.getNextNode());
                currentNode.setNext(null);
                count--;
            }
        }
        return removedData;
    }

    public void removeAll() {
        this.head = null;
        this.tail = null;
        count = 0;
    }

}
