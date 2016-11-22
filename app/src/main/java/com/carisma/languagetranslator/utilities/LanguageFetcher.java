package com.carisma.languagetranslator.utilities;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.carisma.languagetranslator.Constants;
import com.carisma.languagetranslator.models.Language;
import com.carisma.languagetranslator.models.Word;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by aliyuabdullahi on 11/22/16.
 */

public class LanguageFetcher {
    private static final String TAG = "com.carisma.TAG_LOG";
    Context context;

    public LanguageFetcher(Context context) {
        this.context = context;
    }

    public void getWord(String text, String fromFormat, String toFormat, final OnWordObtainedListener wordObtainedListener) {
        if (text != null && fromFormat != null && toFormat != null) {
            final Word word = new Word();
            final String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20161122T084447Z.7a58771dfb231b24.efa6915df34c5644491674c098d1f6ee2215238a&text="
                    + text + "&lang=" + fromFormat + "-" + toFormat;
            RequestQueue queue = Volley.newRequestQueue(context);
            final StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, response);
                    word.setText(response);
                    try {
                        JSONObject responseObject = new JSONObject(response);
                        JSONArray jsonArray = responseObject.getJSONArray("text");
                        if (jsonArray.length() > 0) {
                            word.setText(String.valueOf(jsonArray.get(0)));
                            wordObtainedListener.onWordObtained(word);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    wordObtainedListener.onWordObtained(word);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Error");
                    word.setText("Error");
                    wordObtainedListener.onWordObtained(word);
                }
            });

            queue.add(request);
        } else {
            Word errorWord = new Word();
            errorWord.setText("Error format not found");
            wordObtainedListener.onWordObtained(errorWord);
        }
    }

    public interface OnWordObtainedListener {
        void onWordObtained(Word word);
    }
}
