public class Counter {
    
    private int count = 1;

    public void incr() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public String toString() {
        return "" + count;
    }
}
