package com.carisma.languagetranslator.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by aliyuabdullahi on 11/22/16.
 */

public class Language extends RealmObject {
    private RealmList<Word> words;
    private String format;

    public RealmList<Word> getWords() {
        return words;
    }

    public void setWords(RealmList<Word> words) {
        this.words = words;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "{\n format: " + format + ", \n words: " + words + "\n}";
    }
}
