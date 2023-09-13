abstract class BottleContainer {
    
    public static final double L_VOL = 2,
                               M_VOL = 1.5,
                               S_VOL = 1;

    private int small;
    private int medium;
    private int large;
    private String containerId;

    public BottleContainer() {
        this("");
    }

    public BottleContainer(String id) {
        this.containerId = id;
    }

    public String getContainerId() {
        return containerId;
    }

    // Metody wstawiania butelek
    public void addSmall (int ile) {
        small += ile;
    }

    public void addMedium (int ile) {
        medium += ile;
    }

    public void addLarge (int ile) {
        large += ile;
    }

    // Metody pobierania informacji
    public int getSmall() {
        return small;
    }
    public int getMedium() {
        return medium;
    }
    public int getLarge() {
        return large;
    }
    public double getVolume() {
        return L_VOL * large + M_VOL * medium + S_VOL * small;
    }

    abstract String getContainerType();

    public String toString() {
        return getContainerType() + " " + containerId +
            " [ Sb = " + getSmall() + ", Mb = " + getMedium() +
            ", Lb = " + getLarge() + ", Vol = " + getVolume() + " ]";
    }
}
