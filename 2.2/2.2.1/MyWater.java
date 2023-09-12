public class MyWater {
    
    public static final double L_VOL = 2,
                               M_VOL = 1,
                               S_VOL = 0.5;

    private int largeButelki;
    private int mediumButelki;
    private int smallButelki;

    public MyWater() {}
    
    public MyWater(int small, int medium, int large) {
        largeButelki = large;
        mediumButelki = medium;
        smallButelki = small;
    }

    public int getLarge() {
        return largeButelki;
    }

    public int getMedium() {
        return mediumButelki;
    }
    
    public int getSmall() {
        return smallButelki;
    }

    public double getVolume() {
        return L_VOL * largeButelki + M_VOL * mediumButelki + S_VOL * smallButelki;
    }

    void addLarge(int ile) {
        largeButelki += ile;
    }

    void addMedium(int ile) {
        mediumButelki += ile;
    }

    void addSmall(int ile) {
        smallButelki += ile;
    }
}
