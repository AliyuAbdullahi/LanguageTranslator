package com.carisma.languagetranslator.utilities;

import android.content.Context;
import android.util.Log;

import com.carisma.languagetranslator.R;
import com.carisma.languagetranslator.models.Language;

import java.util.Arrays;
import java.util.List;

/**
 * Created by aliyuabdullahi on 11/22/16.
 */

public class LanguageProcessor {
    Context context;
    String[] languages;
    String[] formats;
    public LanguageProcessor(Context context) {
        this.context = context;
    }
   public String extractFormat(String language) {
       languages = context.getResources().getStringArray(R.array.languages);
       formats = context.getResources().getStringArray(R.array.languageformats);
       for (int i = 0; i < languages.length; i++) {
           if(languages[i].equals(language)) {
               Log.d("SPINNER_VALUE", formats[i]);
               return formats[i];
           }
       }

       return null;
   }
}
