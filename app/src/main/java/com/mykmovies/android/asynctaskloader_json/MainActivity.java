package com.mykmovies.android.asynctaskloader_json;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity
        implements LoaderCallbacks<JSONObject>{
    private String URLTEXT;
    private String APIKEY;

    private static final int ADDRESSLOADER_ID = 0;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView1);
        URLTEXT ="https://api.themoviedb.org/3/movie/popular?api_key=";
        APIKEY= BuildConfig.MovieDBApiKeyInfo;
        getLoaderManager().initLoader(ADDRESSLOADER_ID, null, this);

    }

    @Override
    public Loader<JSONObject> onCreateLoader(int id, Bundle args) {
        // TODO 自動生成されたメソッド・スタブ

        // 当日日付を取得

        String url = URLTEXT + APIKEY;

        return new JsonLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<JSONObject> loader, JSONObject data) {
        // TODO 自動生成されたメソッド・スタブ
        if (data != null) {

            try {

                JSONObject jsonObject=new JSONObject(data.toString());
                JSONArray jsDataList = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsDataList.length(); i++) {

                    JSONObject jsData = jsDataList.getJSONObject(i);
                    String moviePoster=jsData.getString("poster_path");

                    textView.append(jsData.toString());

                }

            } catch (JSONException e) {
                Log.d("onLoadFinished","JSONのパースに失敗しました。 JSONException=" + e);
            }


        }else{
            Log.d("onLoadFinished", "onLoadFinished error!");
        }
    }

    @Override
    public void onLoaderReset(Loader<JSONObject> loader) {
        // TODO 自動生成されたメソッド・スタブ

    }

}