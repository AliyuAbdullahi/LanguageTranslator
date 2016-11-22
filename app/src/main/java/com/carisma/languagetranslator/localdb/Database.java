package com.carisma.languagetranslator.localdb;

/**
 * Created by aliyuabdullahi on 11/22/16.
 */

public interface Database<T> {
    void create(T object);

    void delete(T object);

    void update(T object);

    T get();
}
