package com.trudle.shopwareapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.trudle.shopwareapi.api.HttpDigestAuth;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    RecyclerView articlesList;

    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "FtvZrxsNUE2uriiATZ5xwbUJ9yKMmCIZFV6wOy5w";
    private final static String USERNAMAE ="admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

        HttpURLConnection urlConnection=null;
        try {
            URL url = new URL(EndPoints.RESTAPI);
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        urlConnection = HttpDigestAuth.tryDigestAuthentication(urlConnection,USERNAMAE,API_KEY);

        StringRequest strReq = new StringRequest(Request.Method.GET,
                urlConnection.toString() , new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "response: " + response);

                /*try {
                    JSONObject json = new JSONObject(response);

                    // check for error flag
                    if (json.getBoolean("success") == true) {

                        List<Address> articles = new ArrayList<>();

                        JSONArray articleObj = json.getJSONArray("data");
                        for (int i=0; i<articleObj.length();i++) {

                            Address addressBean = new Gson().fromJson(articleObj.getJSONObject(i).toString(), Address.class);
                            articles.add(addressBean);
                        }

                        articlesList.setAdapter(new ArticlesAdapter(articles, R.layout.list_item_articles, getApplicationContext()));


                    } else {
                        // login error - simply toast the message
                        Toast.makeText(getApplicationContext(), "" + json.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.e(TAG, "json parsing error: " + e.getMessage());
                    Toast.makeText(getApplicationContext(), "Json parse error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }*/
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                Log.e(TAG, "Volley error: " + error.getMessage() + ", code: " + networkResponse);
                Toast.makeText(getApplicationContext(), "Volley error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) ;


        //Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq);

    }
}
