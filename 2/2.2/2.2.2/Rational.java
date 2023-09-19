public class Rational {
    
    private int licznik;
    private int mianownik;

    Rational(int licznik, int mianownik) throws IllegalArgumentException {
        if (mianownik == 0) {
            throw new IllegalArgumentException("zerowy mianownik");
        }
        
        this.licznik = licznik;
        this.mianownik = mianownik;
    }

    public Rational add(Rational arg) {
        int l = licznik * arg.getMianownik() + arg.getLicznik() * mianownik;
        int m = mianownik * arg.getMianownik();

        return new Rational(l, m);
    }

    public Rational mul(Rational arg) {
        int l = licznik * arg.getLicznik();
        int m = mianownik * arg.getMianownik();

        return new Rational(l, m);
    }

    public Rational sub(Rational arg) {
        int l = licznik * arg.getMianownik() - arg.getLicznik() * mianownik;
        int m = mianownik * arg.getMianownik();
        
        return new Rational(l, m);
    }

    public Rational div(Rational arg) {
        int l = licznik * arg.getMianownik();
        int m = mianownik * arg.getLicznik();
        
        return new Rational(l, m);
    }

    public boolean equals(Rational arg) {
        return (compare(this, arg) == 0);
    }

    public int compareTo(Rational arg) {
        return compare(this, arg);
    }

    private static int compare(Rational l, Rational r) {
        int subtr = l.getLicznik() * r.getMianownik() -
                        l.getMianownik() * r.getLicznik();
        int sign = (l.getMianownik() * r.getMianownik() > 0 ? 1 : -1);

        if (subtr == 0)
            return 0;
        if (subtr * sign < 0)
            return -1;
        return 1;
    }

    public String toString() {
        return licznik + "/" + mianownik;
    }

    public int getLicznik() {
        return licznik;
    }
    public int getMianownik() {
        return mianownik;
    }

    public static void main(String[] args) {
        Rational r0 = new Rational(0, 1),
                 r1 = new Rational(1, 1),
                 r2 = new Rational(1, 2),
                 r3 = new Rational(-1, 1),
                 r4, r5, r6, r7;

        System.out.println("Liczby:");
        System.out.println("r0 = " + r0);
        System.out.println("r1 = " + r1);
        System.out.println("r2 = " + r2);
        System.out.println("r3 = " + r3);

        System.out.println("Operacje:");
        r4 = r1.add(r2);
        r5 = r3.sub(r2);
        r6 = r2.mul(r3);
        r7 = r2.div(r3);

        System.out.println("r1 + r2 = " + r4);
        System.out.println("r3 - r2 = " + r5);
        System.out.println("r2 * r3 = " + r6);
        System.out.println("r2 / r3 = " + r7);

        System.out.println("Porownania:");
        System.out.println("r2 < r1 : " + (r2.compareTo(r1) == -1 ? "TAK" : "NIE"));
        System.out.println("r1 == r2 : " + r2.equals(r1));
    }

}
