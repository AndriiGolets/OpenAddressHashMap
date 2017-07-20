package name.golets.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrii on 7/16/17.
 */
public class SimpleMapToHashMapAdapter implements SimpleMap {

    private Map<Integer, Long> map = new HashMap<>();

    @Override
    public void put(Integer key, Long value) {
        map.put(key, value);
    }

    @Override
    public Long get(Integer key) {
        return map.get(key);
    }

    @Override
    public Integer size() {
        return map.size();
    }

    @Override
    public void print() {

    }

    @Override
    public void clear() {
        map.clear();
    }
}
