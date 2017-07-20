import name.golets.hashmap.HashMapOpenAddressFixSize;
import name.golets.hashmap.HashMapOpenAddressResizable;
import name.golets.hashmap.SimpleMap;
import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * Created by andrii on 7/15/17.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HashMapTests extends Assert {

    private SimpleMap hashMap;
    private static final Integer INITIAL_MAP_SIZE = 16;
    private static final Integer TEST_ARRAY_SIZE = 1000;

    private static final Integer[] keys = new Integer[TEST_ARRAY_SIZE];
    private static final Long value[] = new Long[TEST_ARRAY_SIZE];

    static {
        keys[0] = 1;
        value[0] = 1000000000000L;
        for (int i = 1; i < TEST_ARRAY_SIZE; i++) {
            // keys[i] = keys[i - 1] * 2;
            keys[i] = keys[i - 1] + 2;
            value[i] = value[i - 1] + 1L;
        }
    }

    @Before
    public void createMap() {

        hashMap = new HashMapOpenAddressResizable();

        //hashMap = new HashMapOpenAddressFixSize(INITIAL_MAP_SIZE);
        // hashMap = new SimpleHashMapStandartImpl();
    }

//******  put  *************************************************************************

    @Test
    public void _01putWithNullKey() {
        hashMap.put(null, value[0]);
    }

    @Test
    public void _02putWithNullValue() {

        for (Integer key : keys) {
            hashMap.put(key, null);
        }
    }

    @Test
    public void _03put() {
        for (int i = 0; i < TEST_ARRAY_SIZE; i++) {
            hashMap.put(keys[i], value[i]);
        }
    }

//******  get  ***************************************************************************

    @Test
    public void _04putAndGetByPresentKey() {

        for (int i = 0; i < TEST_ARRAY_SIZE; i++) {
            hashMap.put(keys[i], value[i]);
        }
        hashMap.print();
        for (int i = 0; i < TEST_ARRAY_SIZE; i++) {
            assertEquals((long) hashMap.get(keys[i]), (long) value[i]);
        }
    }

    @Test
    public void _041putAndGetTwoSameKey() {

        hashMap.put(keys[0], value[0]);
        hashMap.put(keys[0], value[1]);
        assertEquals((long) hashMap.get(keys[0]), (long) value[1]);
        assertNotEquals((long) hashMap.get(keys[0]), (long) value[0]);
    }

    @Test
    public void _05putAndGetByAbsentKey() {

        for (int i = 1; i < TEST_ARRAY_SIZE; i++) {
            hashMap.put(keys[i], value[i]);
        }
        for (int i = 1; i < TEST_ARRAY_SIZE; i++) {
            assertNull(hashMap.get(keys[i] + 1));
        }
    }

    @Test
    public void _051putAndGetWithNullValue() {
        hashMap.put(keys[0], null);
        assertNull(hashMap.get(keys[0]));
    }

    @Test
    public void _052putAndGetWithNullKey() {
        hashMap.put(null, value[0]);
        assertEquals((long) hashMap.get(null), (long) value[0]);
    }

    @Test
    public void _06GetFromEmptyMap() {
        assertNull(hashMap.get(keys[0]));
    }

//******  size  ***************************************************************************

    @Test
    public void _07putAndSize() {

        for (int i = 0; i < TEST_ARRAY_SIZE; i++) {
            hashMap.put(keys[i], value[i]);
        }
        assertEquals((int) hashMap.size(), (int) TEST_ARRAY_SIZE);
    }

    @Test
    public void _071putAndSizeForSameKey() {


        hashMap.put(keys[0], value[0]);
        hashMap.put(keys[0], value[0]);
        hashMap.put(keys[0], value[0]);

        assertEquals((int) hashMap.size(), 1);
    }

    @Test
    public void _08SizeForEmpty() {
        assertEquals((int) hashMap.size(), 0);
    }

//******  clear  ***************************************************************************

    @Test
    public void _09Clear() {
        hashMap.put(1, 1L);
        hashMap.put(1, 1L);
        hashMap.put(2, 1L);
        hashMap.clear();
        assertEquals((int) hashMap.size(), 0);
        assertNull(hashMap.get(1));
        assertNull(hashMap.get(2));
    }

}
