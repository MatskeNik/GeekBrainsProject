package lesson8;

public class DoubleLinkedList implements GeekbrainsList {
    private Node head;

    @Override
    public void add(String o) {
        if (head == null) {
            head = new Node(o);
            return;
        }

        add(head, o);
    }

    private void add(Node current, String o) {
        if (current.getNext() == null) {
            current.setNext(new Node(o));
            current.getNext().setPrev(current);
            return;
        }
        add(current.getNext(), o);
    }

    @Override
    public void remove(String o) {
        if (head == null) {
            return;
        } else {
            if (head.getVal().equals(o)) {
                head = head.getNext();
                head.setPrev(null);
                return;
            }
        }

        remove(head, head.getNext(), o);
    }

    private void remove(Node prev, Node current, String o) {
        if (current == null) {
            return;
        }

        if (current.getVal().equals(o)) {
            prev.setNext(current.getNext());
            if (current.getNext() != null) {
                current.getNext().setPrev(current.getPrev());
            }
            return;
        }

        remove(current, current.getNext(), o);
    }

    private static class Node {
        private String val;
        private Node next;
        private Node prev;

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node(String val) {
            this(val, null, null);
        }

        public Node(String val, Node next, Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }


        public String getVal() {
            return val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            Node currentNode = this;
            StringBuilder builder = new StringBuilder("[");
            while (currentNode != null) {
                builder.append("{");
                if(currentNode.getPrev() == null) {
                    builder.append("prev=").append("null,");
                } else {
                    builder.append("prev=").append(currentNode.getPrev().getVal()).append(",");
                }
                builder.append("val=").append(currentNode.val).append(",");
                if(currentNode.getNext() == null) {
                    builder.append("next=").append("null");
                } else {
                    builder.append("next=").append(currentNode.getNext().getVal());
                }
                builder.append("}");
                currentNode = currentNode.next;
            }
            builder.append("]");
            return builder.toString();
        }
    }

    @Override
    public String toString() {
        return "{" +
                head +
                '}';
    }
}
