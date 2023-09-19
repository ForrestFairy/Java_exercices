public class IntString {

    static int makeInt(String val) {
        if (val == null) {
            return -1;
        }
        int power = 1;
        int retval = 0;

        for (int i = val.length() - 1; i >= 0; i--) {
            char temp = val.charAt(i);
            if (i == 0 && temp == '-') {
                retval *= -1;
                return retval;
            }
            if (temp > '9' || temp < '0') {
                return -1;
            }
            int liczba = temp - '0';
            retval += liczba * power;
            power *= 10; 
        }
        return retval;
    }

    public static void main(String[] args) {
        int n = makeInt("123");
        System.out.println(n);
        int m = makeInt("-345");
        System.out.println(m);
        int o = makeInt("123o");
        System.out.println(o);
    }
}