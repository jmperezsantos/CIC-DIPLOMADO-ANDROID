package cic.ipn.mx.agendarestexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cic.ipn.mx.agendarestexample.adapters.ContactListAdapter;
import cic.ipn.mx.agendarestexample.model.ContactModel;
import cic.ipn.mx.agendarestexample.util.URLConstants;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton ibRefresh = findViewById(R.id.ibRefresh);
        ibRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consumeWS();
            }
        });

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

                                List<ContactModel> contacts = parseJson(response);

                                updateList(contacts);

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

    private void updateList(List<ContactModel> contacts) {

        ListView lvContacts = findViewById(R.id.lvContacts);

        ContactListAdapter adapter = new ContactListAdapter(contacts);

        lvContacts.setAdapter(adapter);

    }

    private List<ContactModel> parseJson(JSONArray jsonArray) {

        List<ContactModel> contacts = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            try {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                ContactModel contact = new ContactModel();
                contact.setId(jsonObject.getString("_id"));
                contact.setName(jsonObject.getString("name"));
                contact.setLastname(jsonObject.getString("lastname"));
                contact.setPhone(jsonObject.getString("phone"));

                if (!jsonObject.isNull("mail")) {
                    contact.setMail(jsonObject.getString("mail"));
                }

                contacts.add(contact);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        Log.d(TAG, String.format("Se obtuvieron: %d registros", contacts.size()));
        Log.d(TAG, contacts.toString());

        return contacts;

    }
}
