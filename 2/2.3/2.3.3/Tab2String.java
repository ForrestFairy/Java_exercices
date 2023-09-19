public class Tab2String {
    
    static String makeString(char[] chars) {
        if (chars == null) {
            return null;
        }

        String s = "";

        for (char c : chars) {
            s += c;
        }
        return s;
    }

    public static void main(String[] args) {
        char [] napis = {'a', 'l', 'a', ' ', 'm', 'a', ' ', 'k', 'o', 't', 'a'};
        System.out.println(makeString(napis));
        char [] chrs = {'a', 98, '1' + '2', 10 * 10};
        System.out.println(makeString(chrs));
    }
}
