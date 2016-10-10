package com.trudle.shopwareapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RestApiHttpokActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    RecyclerView articlesList;

    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "w1sIe2gIqXQBeOErC8EXynUy4tL1vGNaRFUE6wwb";
    private final static String USERNAMAE ="admin";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=London&amp;units=metric&amp;appid=c6afdab60aa89481e297e0a4f19af055";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_api_httpok);
        Button button = (Button) findViewById(R.id.buttonCall);
        articlesList = (RecyclerView) findViewById(R.id.articles_recycler_view);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                attemptCategory();
            }
        });

    }

    private void attemptCategory() {

        /**
         * To make REST API call through Android OkHttp Library we may first need to build an instance of OkHttpClient class
         * and also an instance of Request class. Since Request class is the main class of OkHttp Library which executes
         * all the requests.
         */
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
               /* .header("Authorization","oauth2.0")
                .header("client_id","root")
                .header("client_secret","password")*/
                .url(EndPoints.BASE_URL_TEST)
                .build();
        /**
         * After this, call enqueue() method to make an asynchronous API request, and implement inside it
         * CallBack Interface Listener "Observer" since this Callback Interface has to methods onResponse()
         * that is fire once a successive response is returned from OpenWeatherMap API and onFailure()
         * that is fire once an error occurs
         */
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(RestApiHttpokActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String responseString = response.body().string();
                    /**
                     * Parse JSON response to Gson library
                     */
                    final JSONObject jsonObject = new JSONObject(responseString);
                    Gson gson = new Gson();
                    //final Address addressBean = new Gson().fromJson(jsonObject.getJSONObject(i).toString(), Address.class);
                    /**
                     * Any action involving the user interface must be done in the main or UI thread, using runOnUiThread()
                     * method will run this specified action on the UI thread.
                     */
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e(TAG, "Response: " +jsonObject.toString());
                            Toast.makeText(RestApiHttpokActivity.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
