package name.golets.hashmap;

/**
 * Created by andrii on 7/16/17.
 */
public interface SimpleMap {

    void put(Integer key, Long value);

    Long get(Integer key);

    Integer size();

    void print();

    void clear();

}
