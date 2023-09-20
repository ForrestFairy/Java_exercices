public class KolejkaObj {
    private Element head, tail;
    private int     size;

    public KolejkaObj() {
        head = null;
        tail = null;
        size = 0;
    }

    private class Element {
        Element next;
        Object obj;

        Element (Element next, Object obj) {
            this.next = next;
            this.obj = obj;
        }
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    public void enq(Object obj) {
        if (isEmpty()) {
            head = tail = new Element(null, obj);
        } else {
            tail.next = new Element(null, obj);
            tail = tail.next;
        }
        size++;
        System.out.println("enq: " + obj);

    }

    public Object deq() {
        if (isEmpty()) {
            return null;
        }
        Element temp = head;
        head = head.next;
        size--;
        return temp.obj;
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Queue<" + size + "> show:");
        Element temp = head;

        while (temp != null) {
            System.out.print(temp.obj + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Object obj;

        KolejkaObj kolejka = new KolejkaObj();

        kolejka.show();

        kolejka.enq("1");
        kolejka.enq("2");
        kolejka.enq("3");
        kolejka.enq("4");
        kolejka.enq("5");
        kolejka.enq("6");
        kolejka.show();

        obj = kolejka.deq();
        System.out.println("deq: " + obj);
        obj = kolejka.deq();
        System.out.println("deq: " + obj);
        obj = kolejka.deq();
        System.out.println("deq: " + obj);

        kolejka.show();

        obj = kolejka.deq();
        System.out.println("deq: " + obj);
        obj = kolejka.deq();
        System.out.println("deq: " + obj);
        obj = kolejka.deq();
        System.out.println("deq: " + obj);

        kolejka.show();
    }
}
