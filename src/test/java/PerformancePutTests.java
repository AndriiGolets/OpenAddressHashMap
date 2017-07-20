import name.golets.hashmap.HashMapOpenAddressFixSize;
import name.golets.hashmap.HashMapOpenAddressResizable;
import name.golets.hashmap.SimpleMap;
import name.golets.hashmap.SimpleMapToHashMapAdapter;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by andrii on 7/20/17.
 */
public class PerformancePutTests extends Assert {

    private static final Long value = 100000000000000L;
    private static final Integer[] TEST_BLOCKS_SIZE = new Integer[]{10000, 100000, 1000000, 10000000};

    @Test
    public void performanceTests() {

        System.out.println("\nRandom Tests : \n");
        perfomanceRandomTest(new HashMapOpenAddressFixSize(TEST_BLOCKS_SIZE[3]), "FixSize ");
        perfomanceRandomTest(new HashMapOpenAddressResizable(), "Resizable ");
        perfomanceRandomTest(new SimpleMapToHashMapAdapter(), "Standard ");

        System.out.println("\nSequential Tests : \n");
        perfomanceSequentialTest(new HashMapOpenAddressFixSize(TEST_BLOCKS_SIZE[3]), "FixSize ");
        perfomanceSequentialTest(new HashMapOpenAddressResizable(), "Resizable ");
        perfomanceSequentialTest(new SimpleMapToHashMapAdapter(), "Standard ");

        System.out.println("\nPowerOf2 Tests : \n");
        perfomancePowerOf2Test(new HashMapOpenAddressFixSize(TEST_BLOCKS_SIZE[0]), "FixSize ");
        perfomancePowerOf2Test(new HashMapOpenAddressResizable(), "Resizable ");
        perfomancePowerOf2Test(new SimpleMapToHashMapAdapter(), "Standard ");
    }


    private Integer getRandomInt(Integer size) {
        return ThreadLocalRandom.current().nextInt(0, size);
    }

    private void perfomanceRandomTest(SimpleMap map, String message) {

        for (Integer blockSize : TEST_BLOCKS_SIZE) {
            Timer timer = new Timer();
            timer.start();
            for (int i = 0; i < blockSize; i++) {
                map.put(getRandomInt(blockSize), value);
            }
            timer.end();
            System.out.println(message + "Put " + blockSize + " : " + timer.getTotalTime());

            timer = new Timer();
            timer.start();
            for (int i = 0; i < blockSize; i++) {
                Long l = map.get(getRandomInt(blockSize));
            }
            timer.end();
            System.out.println(message + "Get " + blockSize + " : " + timer.getTotalTime());

            map.clear();
        }

    }

    private void perfomanceSequentialTest(SimpleMap map, String message) {


        for (Integer blockSize : TEST_BLOCKS_SIZE) {
            Timer timer = new Timer();
            timer.start();
            for (int i = 0; i < blockSize; i++) {
                map.put(i, value);
            }
            timer.end();
            System.out.println(message + "Put " + blockSize + " : " + timer.getTotalTime());

            timer = new Timer();
            timer.start();
            for (int i = 0; i < blockSize; i++) {
                Long l = map.get(i);
            }
            timer.end();
            System.out.println(message + "Get " + blockSize + " : " + timer.getTotalTime());

            map.clear();
        }

    }

    private void perfomancePowerOf2Test(SimpleMap map, String message) {
        Timer timer = new Timer();
        timer.start();
        for (int k = 0; k < 10000; k++) {
            for (int i = 2; i < 1073741824; i = i * 2) {
                    map.put(i, value);
            }
            map.clear();
        }
        timer.end();
        System.out.println(message + " : " + timer.getTotalTime());
    }
}
