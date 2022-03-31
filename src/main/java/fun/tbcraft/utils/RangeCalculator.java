package fun.tbcraft.utils;

import io.lumine.mythic.lib.api.util.ui.QuickNumberRange;
import io.lumine.mythic.utils.numbers.RandomDouble;

public class RangeCalculator {
    private final QuickNumberRange rangeTo;
    private double number;

    /**
     * @see {@code contains()}
     */
    public RangeCalculator() {
        this(0,100);
    }

    /**
     * @param x Start
     * @param y End
     *
     * @see {@code contains()}
     */
    public RangeCalculator(double x, double y){
        rangeTo = new QuickNumberRange(x,y);
    }

    /**
     * @param first Start
     * @param second End
     * @param randomRangeA Random Start
     * @param randomRangeB Random End
     *
     *  Converts a random number by a range, and if it falls within, do "contains()".
     * @see RangeCalculator#contains()
     */
    public RangeCalculator(double first, double second, double randomRangeA, double randomRangeB){
        rangeTo = new QuickNumberRange(first, second);

        number = setRandomRange(randomRangeA,randomRangeB).get();

    }
    private RandomDouble setRandomRange(double x, double y){
        return new RandomDouble(x, y);
    }

    public boolean contains() {
        return rangeTo.inRange(number);
    }
    public boolean contains(double x){
        return rangeTo.inRange(x);
    }
}
