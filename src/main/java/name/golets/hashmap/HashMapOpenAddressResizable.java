package name.golets.hashmap;

/**
 * Created by andrii on 7/17/17.
 */
public class HashMapOpenAddressResizable implements SimpleMap {

    private static final int DEFAULT_INITIAL_SIZE = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.5;

    private final double loadFactor;

    private HashEntry[] table;
    private int tableLength;
    private int size;

    public HashMapOpenAddressResizable(Integer initialSize, double loadFactor) {
        this.loadFactor = loadFactor;
        this.tableLength = initialSize + 1;
        init();
    }

    public HashMapOpenAddressResizable() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.tableLength = DEFAULT_INITIAL_SIZE + 1;
        init();
    }

    private void init() {
        table = new HashEntry[tableLength];
        size = 0;
    }

    @Override
    public Long get(Integer key) {

        if (key == null) {
            return table[0].getValue();
        }

        int hash = hash(key);
        if (size == 0 || table[hash] == null) {
            return null;
        } else {
            int i = linearProbing(key, hash);
            if (table[i] == null) {
                return null;
            } else {
                return table[i].getValue();
            }
        }
    }

    @Override
    public Integer size() {
        return size;
    }

    @Override
    public void put(Integer key, Long value) {

        HashEntry entry = new HashEntry(key, value);
        if (key == null) {
            table[0] = entry;
            return;
        }

        int hash = hash(key);
        int index = linearProbing(key, hash);
        if (table[index] == null || !table[index].getKey().equals(key)) {
            size++;
        }
        table[index] = entry;

    }

    private int linearProbing(Integer key, int hash) {
        int i = hash;
        int probesCounter = 0;
        int maxClusterSize = getMaxClusterSize();

        while (table[i] != null && !table[i].getKey().equals(key)) {
            i = i < tableLength - 1 ? ++i : 1;
            probesCounter++;
            if (probesCounter > maxClusterSize && size >= loadFactor * tableLength - 1) {
                resize();
                i = hash(key);
            }
        }
        return i;
    }

    private void resize() {

        HashEntry[] tempTable = table;
        size = 0;
        tableLength = (tableLength - 1) * 2 + 1;
        table = new HashEntry[tableLength];
        for (HashEntry hashEntry : tempTable) {
            if (hashEntry != null) {
                put(hashEntry.getKey(), hashEntry.getValue());
            }
        }
    }

    private int getMaxClusterSize() {
        return MathUtils.logBase2(tableLength);
    }

    private Integer hash(Integer key) {
        return key % (tableLength - 1) + 1;
    }

    @Override
    public void print() {
        for (int i = 0; i < tableLength; i++) {
            if (table[i] == null) {
                System.out.println("  :  ");
            } else {
                System.out.println(table[i].getKey() + " : " + table[i].getValue());
            }
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

}
