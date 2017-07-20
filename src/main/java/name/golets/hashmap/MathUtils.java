package name.golets.hashmap;

/**
 * Created by andrii on 7/18/17.
 */
public class MathUtils {

    public static int logBase2(int value) {
        if (value == 0) {
            return 0;
        }
        return 31 - Integer.numberOfLeadingZeros(value);
    }

}
