public class StosObj {
    private Element top;
    private int     size;

    public StosObj() {
        top = null;
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
        if (top == null) {
            System.out.println("Stack jest pusty");
            return true;
        }
        else return false;
    }

    public Object pop() {
        if (isEmpty()) {
            return null;
        }
        Element temp = top;
        top = top.next;
        size--;
        return temp.obj;
    }

    public void push(Object object) {
        top = new Element(top, object);
        size++;
        System.out.println("push: " + object);
    }

    public Object peek() {
        if (isEmpty()) return null;
        return top.obj;
    }

    public void show() {
        if (isEmpty()) {
            return;
        }
        System.out.println("Stack<" + size + "> show:");
        Element temp = top;

        while (temp != null) {
            System.out.println(temp.obj);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        Object obj;
        StosObj stos = new StosObj();

        stos.show();
        stos.push("1");
        stos.push("2");
        stos.push("3");
        stos.push("4");
        stos.push("5");
        stos.push("6");
        stos.show();

        obj = stos.peek();
        System.out.println("peek: " + obj);

        obj = stos.pop();
        System.out.println("pop: " + obj);
        obj = stos.pop();
        System.out.println("pop: " + obj);
        obj = stos.pop();
        System.out.println("pop: " + obj);

        stos.show();

        obj = stos.peek();
        System.out.println("peek: " + obj);

        obj = stos.pop();
        System.out.println("pop: " + obj);
        obj = stos.pop();
        System.out.println("pop: " + obj);
        obj = stos.pop();
        System.out.println("pop: " + obj);

        stos.show();
    }
}
