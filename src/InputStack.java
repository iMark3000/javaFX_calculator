
public class InputStack {

    private int size = 0;
    private InputNode head = null;

    private class InputNode {
        private MathObjects element;
        private InputNode next = null;

        private InputNode(MathObjects element) {
            this.element = element;
        }

        public Object getElement() {
            return this.element;
        }

        public void setNext(InputNode next) {
            this.next = next;
        }

        public InputNode getNext() {
            return this.next;
        }
    }

    public void add(MathObjects item) {
        InputNode node = new InputNode(item);
        if (this.head != null) {
            node.setNext(this.head);
        }
        this.head = node;
        this.size += 1;
    }

    public Object pop() {
        if (this.head != null) {
            InputNode e = this.head;
            this.head = this.head.getNext();
            this.size -= 1;
            return e.getElement();
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void clearStack() {
        this.head = null;
        this.size = 0;
    }
}
