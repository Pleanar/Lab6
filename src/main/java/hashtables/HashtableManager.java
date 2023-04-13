package hashtables;

import data.HumanBeing;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Set;

/**
 * Base interface for handling the HashTable. Should be implemented for correct commands execution.
 *
 * @param <K> Type of keys in the Hashtable.
 * @param <V> Type of elements in the HashTable.
 */

public interface HashtableManager<K,V> {
    /**
     *A method that allows you to get the initialization date of the HashTable.
     * @return LocalDateTime
     */

    LocalDateTime getInitDate();

    /**
     * A method that allows you to get a copy of a Set of Keys that the HashTable contains.
     * @return Set<K>
     */
    Set<K> getKeys();

    /**
     * A method that allows you to get the name of a Key class.
     * @return String
     */

    String getKeyType();

    /**
     * A method that allows you to get a copy of a Set of Values that the HashTable contains.
     * @return Set<V>
     */
    Set<V> getValues();

    /**
     * A method that allows you to get the name of a Value class.
     * @return String
     */
    String getValueType();

    /**
     * a method for validation of the elements that currently are in the HashTable.
     */
    void validateElements();

    /**
     * A method that allows you to get HashTable.
     * @return reference to the HashTable.
     */

    Hashtable<Long, HumanBeing> getHumanBeingHashtable();

    /**
     * A method that allows you to set pre-made HashTable in the Manager.
     * @param humanBeingHashtable HashTable<K, V>
     */

    void setHumanBeingHashtable(Hashtable<K, V> humanBeingHashtable);

    /**
     * A method that allows you to add an element to the HashTable.
     * @param key A key of the value.
     * @param value A value to add.
     */
    void addElementToHashtable(K key, V value);
}
