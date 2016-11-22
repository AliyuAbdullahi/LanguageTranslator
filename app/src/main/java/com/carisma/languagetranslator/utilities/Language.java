package com.carisma.languagetranslator.utilities;

/**
 * Created by aliyuabdullahi on 11/22/16.
 */

public enum Language {
    ENGLISH("en");

    Language(String format){
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    String format;
}
