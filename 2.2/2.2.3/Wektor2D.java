public class Wektor2D {
    
    Wektor1D wx, wy;

    public Wektor2D() {}
    public Wektor2D(double x, double y) {
        wx = new Wektor1D(x);
        wy = new Wektor1D(y);
    }

    public Wektor2D addWektor2d(Wektor2D wek) {
        Wektor2D ans = new Wektor2D();
        ans.wx = this.wx.addWektor1d(wek.wx);
        ans.wy = this.wy.addWektor1d(wek.wy);

        return ans;
    }

    public Wektor2D subWektor2d(Wektor2D wek) {
        Wektor2D ans = new Wektor2D();
        ans.wx = this.wx.subWektor1d(wek.wx);
        ans.wy = this.wy.subWektor1d(wek.wy);

        return ans;
    }

    public Wektor2D mulWektor2d(double lambda) {
        Wektor2D ans = new Wektor2D();
        ans.wx = this.wx.mulWektor1d(lambda);
        ans.wy = this.wy.mulWektor1d(lambda);

        return ans;
    }

    public void show() {
        System.out.println("[[" + wx.x + "],[" + wy.x + "]]");
    }
}
