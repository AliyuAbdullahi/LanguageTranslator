package com.carisma.languagetranslator.models;

import io.realm.RealmObject;

/**
 * Created by aliyuabdullahi on 11/22/16.
 */

public class Word extends RealmObject {
    private String text;
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object obj) {
        Word word = (Word)obj;
        return word.text.equals(this.text);
    }
}
