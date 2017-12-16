package com.mykmovies.android.asynctaskloader_json;

import android.content.AsyncTaskLoader;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class JsonLoader extends AsyncTaskLoader<JSONObject> {
    HttpURLConnection httpURLConnection;
    BufferedReader bufferedReader;

    private String urlText;

    public JsonLoader(Context context,String urlText) {
        super(context);
        // TODO 自動生成されたコンストラクター・スタブ
        this.urlText = urlText;
    }

    @Override
    public JSONObject loadInBackground() {
        // TODO 自動生成されたメソッド・スタブ

        try {
            URL url=new URL(urlText);
            httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.connect();
            InputStream inputStream=httpURLConnection.getInputStream();
            bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer=new StringBuffer();
            String line="";
            while((line=bufferedReader.readLine())!=null)
            {
                buffer.append(line);
            }
            String jsonUrl=buffer.toString();
            JSONObject rootObject = new JSONObject(jsonUrl);
            return rootObject;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {

            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return null;
        }
    }

    @Override
    protected void onStartLoading() {
        // TODO 自動生成されたメソッド・スタブ
        forceLoad();
    }

}