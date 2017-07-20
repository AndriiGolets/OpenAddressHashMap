package name.golets.hashmap;

/**
 * Created by andrii on 7/15/17.
 */
public class HashEntry {

    private Integer key;
    private Long value;

    HashEntry(Integer key, Long value) {
        this.key = key;
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

}
