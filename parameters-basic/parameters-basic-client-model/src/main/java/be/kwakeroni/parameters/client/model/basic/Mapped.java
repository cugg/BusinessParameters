package be.kwakeroni.parameters.client.model.basic;

import be.kwakeroni.parameters.client.model.EntryType;

/**
 * Created by kwakeroni on 6/05/2016.
 */
public interface Mapped<KeyType, ET extends EntryType> extends EntryType {
    ET forKey(KeyType key);
}
