package com.carisma.languagetranslator;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by aliyuabdullahi on 11/22/16.
 */

public class LanguageTranslatorApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder().name("_language_translator").schemaVersion(1).build();
    }
}
