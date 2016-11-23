package com.carisma.languagetranslator.utilities;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.carisma.languagetranslator.BuildConfig;
import com.carisma.languagetranslator.Constants;
import com.carisma.languagetranslator.models.Word;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
            final String configApiKey = BuildConfig.TRANSLATE_APIKEY;
            final Word word = new Word();
            final String url = Constants.baseTranslateUrl + "?key=" + configApiKey + "&text="
                    + text + "&lang=" + fromFormat + "-" + toFormat;
            RequestQueue queue = Volley.newRequestQueue(context);
            final StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, response);
                    word.setText(response);
                    try {
                        JSONObject responseObject = new JSONObject(response);
                        if (responseObject.has("text")) {
                            JSONArray jsonArray = responseObject.getJSONArray("text");
                            if (jsonArray.length() > 0) {
                                word.setText(String.valueOf(jsonArray.get(0)));
                                wordObtainedListener.onWordObtained(word);
                            }
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
