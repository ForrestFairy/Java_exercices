public class PoliTest {

    public static void main(String[] args) {
        Calculate calculate;
        Figure figure;
        Rectangle rectangle;
        Circle circle;

        calculate = new Rectangle(10, 10, 20, 30);
        rectangle = (Rectangle) calculate;
        rectangle.show();
        System.out.println("" + calculate.pole());
        System.out.println("" + calculate.obwod());

        calculate = new Circle(5, 5, 10);
        circle = (Circle) calculate;
        circle.show();
        System.out.println("" + calculate.pole());
        System.out.println("" + calculate.obwod());

        figure = new Rectangle(10, 10, 20, 30);
        rectangle = (Rectangle) figure;
        rectangle.show();
        System.out.println("" + figure.pole());
        System.out.println("" + figure.obwod());

        figure = new Circle(5, 5, 10);
        circle = (Circle) figure;
        circle.show();
        System.out.println("" + figure.pole());
        System.out.println("" + figure.obwod());
    }
}
