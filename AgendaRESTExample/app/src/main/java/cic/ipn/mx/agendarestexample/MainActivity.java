package cic.ipn.mx.agendarestexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

import cic.ipn.mx.agendarestexample.util.URLConstants;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.consumeWS();
    }

    private void consumeWS() {

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = URLConstants.ALL_CONTACTS_URL;

        JsonArrayRequest request =
                new JsonArrayRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                Log.d(TAG, "Json: " + response);

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.d(TAG, "Error: " + error.getMessage());
                                error.printStackTrace();

                            }
                        }
                ) {

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {

                        // List -> ArrayList
                        // Set -> HashSet
                        // Map -> HashMap

                        Map<String, String> headers = new HashMap<>();
                        headers.put("Authorization", URLConstants.AUTH_HEADER);

                        return headers;

                    }
                };

        queue.add(request);

    }
}
