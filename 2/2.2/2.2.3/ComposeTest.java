public class ComposeTest {
    
    public static void main(String[] args) {
        Wektor1D x1, x2, wynik1D;
        Wektor2D xy1, xy2, wynik2D;
        Wektor3D xyz1, xyz2, wynik3D;

        x1 = new Wektor1D(5);
        x1.show();
        x2 = new Wektor1D(7);
        x2.show();

        // Operacje na Wektor1D
        wynik1D = x1.addWektor1d(x2);
        System.out.print("suma1D = ");
        wynik1D.show();
        wynik1D = x1.subWektor1d(x2);
        System.out.print("roznica1D = ");
        wynik1D.show();
        wynik1D = x1.mulWektor1d(6);
        System.out.print("Iloczyn1D = ");
        wynik1D.show();

        // Operacje na Wektor2D
        xy1 = new Wektor2D(5, 3);
        xy1.show();
        xy2 = new Wektor2D(7, 2);
        xy2.show();

        wynik2D = xy1.addWektor2d(xy2);
        System.out.print("suma2D = ");
        wynik2D.show();
        wynik2D = xy1.subWektor2d(xy2);
        System.out.print("roznica2D = ");
        wynik2D.show();
        wynik2D = xy1.mulWektor2d(6);
        System.out.print("Iloczyn2D = ");
        wynik2D.show();

        // Operacje na Wektor3D
        xyz1 = new Wektor3D(2, 4, 5);
        xyz1.show();
        xyz2 = new Wektor3D(7, 1, -2);
        xyz2.show();

        wynik3D = xyz1.addWektor3d(xyz2);
        System.out.print("suma3D = ");
        wynik3D.show();
        wynik3D = xyz1.subWektor3d(xyz2);
        System.out.print("roznica3D = ");
        wynik3D.show();
        wynik3D = xyz1.mulWektor3d(6);
        System.out.print("Iloczyn3D = ");
        wynik3D.show();
    }
}
