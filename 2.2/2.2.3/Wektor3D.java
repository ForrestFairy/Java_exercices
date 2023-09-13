public class Wektor3D {
    
    Wektor2D wxy;
    Wektor1D wz;

    public Wektor3D() {}
    public Wektor3D(double x, double y, double z) {
        wxy = new Wektor2D(x, y);
        wz = new Wektor1D(z);
    }

    public Wektor3D addWektor3d(Wektor3D wek) {
        Wektor3D ans = new Wektor3D();
        ans.wxy = this.wxy.addWektor2d(wek.wxy);
        ans.wz = this.wz.addWektor1d(wek.wz);

        return ans;
    }

    public Wektor3D subWektor3d(Wektor3D wek) {
        Wektor3D ans = new Wektor3D();
        ans.wxy = this.wxy.subWektor2d(wek.wxy);
        ans.wz = this.wz.subWektor1d(wek.wz);

        return ans;
    }

    public Wektor3D mulWektor3d(double lambda) {
        Wektor3D ans = new Wektor3D();
        ans.wxy = this.wxy.mulWektor2d(lambda);
        ans.wz = this.wz.mulWektor1d(lambda);

        return ans;
    }

    public void show() {
        System.out.println("[[" + wxy.wx.x + "],[" + wxy.wy.x + "],[" + wz.x + "]]");
    }
}
