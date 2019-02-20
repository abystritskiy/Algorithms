import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solver {

    /**
     * All the fun happens here
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String dataFile = "input/hashcode/pizza/10x10.in";

        Input input = new Input(dataFile);

        PizzaSlicer ps = new PizzaSlicer(input.low, input.high, input.grid, Orientation.TOP_LEFT);
        Pizza pizza = new Pizza(ps.grid);

        pizza.printPizza();

        List<Integer> firstStartPoint = new ArrayList<>();
        firstStartPoint.add(0);
        firstStartPoint.add(0);
//        firstStartPoint.add(pizza.rows-1);
//        firstStartPoint.add(pizza.cols-1);

        List<List<Integer>> next = new ArrayList<>();
        next.add(firstStartPoint);

        ps.slice(next);
        System.out.println("Max: " + ps.max);
        System.out.println();
        for (int[] coord : ps.coordinates) {
            System.out.println(Arrays.toString(coord));
        }

        long endTime = System.currentTimeMillis();

        System.out.println("execution time: " + (endTime-startTime));
        System.out.println();

        ps.showCovered(ps.coordinates);
    }
}
