import model.RouteCalculator;

public class Main {
    public static void main(String[] args) {
        RouteCalculator calculator = new RouteCalculator();
        calculator.addLocation("x => u");
        calculator.addLocation("y => v");
        calculator.addLocation("w => z");
        calculator.addLocation("v => w");
        calculator.addLocation("u => x");
        calculator.addLocation("u =>");
        calculator.addLocation("z =>");
        System.out.println(calculator.calculate());
        calculator.addLocation("c =>");
        calculator.addLocation("b =>");
        calculator.addLocation("a => l");
        System.out.println(calculator.calculate());
        calculator.addLocation("t => x");
        calculator.addLocation("l =>");
        System.out.println(calculator.calculate());
    }
}
