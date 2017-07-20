import name.golets.hashmap.MathUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andrii on 7/18/17.
 */


public class MathUtilTest extends Assert {

    private static final Integer TEST_ARRAY_SIZE = 1000;

    private static final int ARRAY_LENGTH = 31;
    private static final int[] VALUES = new int[ARRAY_LENGTH];

    static {
        VALUES[0] = 2;
        for (int i = 1; i < ARRAY_LENGTH; i++) {
            VALUES[i] = VALUES[i - 1] * 2;
        }
    }

    @Test
    public void logBase2() {
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            int result = MathUtils.logBase2(VALUES[i]);
            System.out.println(result);
            assertEquals(result, i + 1);
        }
    }

}
