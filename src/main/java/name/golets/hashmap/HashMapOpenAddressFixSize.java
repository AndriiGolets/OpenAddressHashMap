package name.golets.hashmap;

/**
 * Created by andrii on 7/15/17.
 */
public class HashMapOpenAddressFixSize implements SimpleMap {

    private final int initialSize;
    private HashEntry[] table;
    private int size;

    public HashMapOpenAddressFixSize(Integer initialSize) {
        this.initialSize = initialSize + 1;
        init();
    }

    private void init() {
        table = new HashEntry[initialSize];
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

        if (size == initialSize) {
            throw new IndexOutOfBoundsException("size = " + size);
        }

        HashEntry entry = new HashEntry(key, value);

        if (key == null) {
            table[0] = entry;
            return;
        }

        int index = linearProbing(key, hash(key));
        if (table[index] == null || !table[index].getKey().equals(key)) {
            size++;
        }
        table[index] = entry;
    }

    private int linearProbing(Integer key, int hash) {
        int i = hash;

        while (table[i] != null && !table[i].getKey().equals(key)) {
            i = i < initialSize - 1 ? ++i : 1;
        }
        return i;
    }

    private Integer hash(Integer key) {
        return (key % (initialSize - 1) + 1);
    }

    @Override
    public void print() {
        for (int i = 0; i < initialSize; i++) {
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