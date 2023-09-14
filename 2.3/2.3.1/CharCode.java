public class CharCode {
    
    static void showChars(int l, int r) {
        for (int i = l; i < r; i++) {
            System.out.println("kod = " + i + " znak = " + (char) i);
        }
    }

    public static void main(String[] args) {
        showChars(1, 127);
    }
}
