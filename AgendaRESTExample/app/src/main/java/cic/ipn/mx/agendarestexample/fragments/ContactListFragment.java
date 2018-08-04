package cic.ipn.mx.agendarestexample.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cic.ipn.mx.agendarestexample.R;
import cic.ipn.mx.agendarestexample.adapters.ContactListAdapter;
import cic.ipn.mx.agendarestexample.model.ContactModel;
import cic.ipn.mx.agendarestexample.util.URLConstants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment {

    private static String TAG = ContactListFragment.class.getName();

    private ListView lvContacts;

    public static ContactListFragment newInstance() {

        Bundle args = new Bundle();

        ContactListFragment fragment = new ContactListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ContactListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        lvContacts = view.findViewById(R.id.lvContacts);

        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view,
                                    int position,
                                    long itemId) {

                ContactModel contact =
                        (ContactModel) lvContacts.getAdapter().getItem(position);

                Fragment contactDetail = ContactDetailFragment.newInstance(contact);

                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                transaction.addToBackStack("");

                transaction.replace(R.id.container, contactDetail);

                transaction.commit();

            }
        });

        ImageButton ibRefresh = view.findViewById(R.id.ibRefresh);
        ibRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consumeWS();
            }
        });

        ImageButton ibAdd = view.findViewById(R.id.ibAdd);
        ibAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment contactDetail = ContactDetailFragment.newInstance(null);

                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                transaction.addToBackStack("");

                transaction.replace(R.id.container, contactDetail);

                transaction.commit();
            }
        });

        this.consumeWS();
    }

    private void consumeWS() {

        RequestQueue queue = Volley.newRequestQueue(this.getContext());

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

                                /*List<ContactModel> contacts = parseJson(response);*/
                                Gson gson = new Gson();
                                ContactModel[] arrContacts =
                                        gson.fromJson(response.toString(),
                                                ContactModel[].class);

                                List<ContactModel> contacts = Arrays.asList(arrContacts);

                                String json = gson.toJson(contacts);

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
