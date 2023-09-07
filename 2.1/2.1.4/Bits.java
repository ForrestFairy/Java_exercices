public class Bits {
    
    static int countBits(int num) {
        int count = 0;
        int mask = 1;

        for (int i = 0; i < 32; i++) {
            if ( (num & mask) != 0 ) 
                count++;
            mask <<= 1; 
        }
        
        return count;
    }

    static String toBinaryString(int num) {
        String str = "";
        int mask = 1;

        for (int i = 0; i < 32; i++) {
            if ( (num & mask ) != 0) {
                str = "1" + str;
            } else {
                str = "0" + str;
            }
            mask <<= 1;
        }

        return str;
    }

    static String toOctal(int num) {
        String str = "";
        int shift = 3;
        int mask = 7;

        num = Math.abs(num);

        for (int i = 0; i < 32; i += shift) {
            int m = ( num & mask ) >> i;
            str = m + str;

            mask <<= shift;
        }

        return str;
    }

    static String toHexadecimal(int num) {
        String str = "";
        int shift = 4;
        int mask = 15;

        num = Math.abs(num);

        for (int i = 0; i < 32; i += shift) {
            int m = ( num & mask ) >> i;

            switch (m) {
                case 15:
                    str = "F" + str;
                    break;
                case 14:
                    str = "E" + str;
                    break;
                case 13:
                    str = "D" + str;
                    break;
                case 12:
                    str = "C" + str;
                    break;
                case 11:
                    str = "B" + str;
                    break;
                case 10:
                    str = "A" + str;
                    break;
                default:
                    str = m + str;
                    break;
            }

            mask <<= shift;
        }

        return str;
    }

    public static void main(String[] args) {
        int num = -2;

        System.out.println("Liczba jedynek w " + num + " = " + countBits(num));
        System.out.println("Binarna postac liczby " + num + " to: " + toBinaryString(num));

        int bi = Integer.MAX_VALUE;
        String bs = toBinaryString(bi);
        System.out.println(bi + " = " + bs);

        int oi = 15;
        String os = toOctal(oi);
        System.out.println(oi + " = " + os);

        int hi = 31;
        String hs = toHexadecimal(hi);
        System.out.println(hi + " = " + hs);
    }
}
