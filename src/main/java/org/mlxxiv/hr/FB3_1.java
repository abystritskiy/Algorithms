import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public class FB3_1 {

    public static String solution(final String m, final String f) {
        BigInteger large = new BigInteger(m);
        BigInteger small = new BigInteger(f);
        BigInteger count = ZERO;

        if (small.compareTo(large) > 0) {
            final BigInteger t = small;
            small = large;
            large = t;
        }

        while (small.compareTo(ONE) > 0) {
            final BigInteger jump = large.divide(small);
            count = count.add(jump);

            final BigInteger t = large.mod(small);
            large = small;
            small = t;
        }

        if (small.compareTo(ONE) < 0) {
            return "impossible";
        }

        return count.add(large.subtract(ONE)).toString();
    }
}