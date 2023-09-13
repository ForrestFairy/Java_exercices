public class Wektor1D {
    
    double x;

    public Wektor1D() {}
    public Wektor1D(double x) {
        this.x = x;
    }

    public Wektor1D addWektor1d (Wektor1D wek) {
        Wektor1D ans = new Wektor1D();
        ans.x = this.x + wek.x;

        return ans;
    }

    public Wektor1D subWektor1d (Wektor1D wek) {
        Wektor1D ans = new Wektor1D();
        ans.x = this.x - wek.x;
        
        return ans;
    }

    public Wektor1D mulWektor1d (double lambda) {
        Wektor1D ans = new Wektor1D();
        ans.x = this.x * lambda;
        
        return ans;
    }

    public void show() {
        System.out.println("[" + x + "]");
    }
}
