package cic.ipn.mx.agendarestexample.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cic.ipn.mx.agendarestexample.R;
import cic.ipn.mx.agendarestexample.model.ContactModel;
import cic.ipn.mx.agendarestexample.util.URLConstants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactDetailFragment extends Fragment {

    private ContactModel contact;
    private RequestQueue queue;

    private final static String CONTACT_ARG = "CONTACT_ARG";
    private EditText etName;
    private EditText etLastname;
    private EditText etPhone;
    private EditText etMail;
    private TextInputLayout tilName;

    public static ContactDetailFragment newInstance(
            ContactModel contact
    ) {

        Bundle args = new Bundle();

        args.putSerializable(CONTACT_ARG, contact);

        ContactDetailFragment fragment = new ContactDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ContactDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = this.getArguments();
        if (args != null) {
            this.contact = (ContactModel) args.getSerializable(CONTACT_ARG);
        }

        queue = Volley.newRequestQueue(this.getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etName = view.findViewById(R.id.etName);
        etLastname = view.findViewById(R.id.etLastname);
        etPhone = view.findViewById(R.id.etPhone);
        etMail = view.findViewById(R.id.etMail);
        tilName = view.findViewById(R.id.tilName);

        Button btnDelete = view.findViewById(R.id.btnDelete);
        Button btnCreate = view.findViewById(R.id.btnCreate);

        if (this.contact != null) {

            btnCreate.setText("Actualizar!");

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteContact();
                }
            });

            etName.setText(this.contact.getName());
            etLastname.setText(this.contact.getLastname());
            etPhone.setText(this.contact.getPhone());
            etMail.setText(this.contact.getMail());

        } else {
            btnDelete.setVisibility(View.GONE);

        }

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (contact != null) {
                    saveElement(true);
                } else {
                    saveElement(false);
                }

            }
        });

    }

    private void saveElement(boolean isUpdate) {

        if (etName.getText().toString().length() == 0) {
            tilName.setError("Este campo no puede estar vacío");
            return;
        } else {
            tilName.setError(null);
        }

        try {

            ContactModel contact = new ContactModel();
            contact.setName(etName.getText().toString());
            contact.setLastname(etLastname.getText().toString());
            contact.setPhone(etPhone.getText().toString());
            contact.setMail(etMail.getText().toString());

            Gson gson = new Gson();
            String body = gson.toJson(contact);
            JSONObject jsonObject = new JSONObject(body);
//            jsonObject.put("name", etName.getText().toString());
//            jsonObject.put("lastname", etLastname.getText().toString());

            String url;

            if (isUpdate) {
                url = URLConstants.DELETE_CONTACT_URL.replace(
                        "{CONTACT_ID}",
                        this.contact.getId()
                );

            } else {
                url = URLConstants.NEW_CONTACT_URL;

            }

            JsonObjectRequest request =
                    new JsonObjectRequest(
                            isUpdate ? Request.Method.PUT : Request.Method.POST,
                            url,
                            jsonObject,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                    try {

                                        String message = "Elemento salvado exitosamente: " + response.getString("_id");

                                        Toast.makeText(getContext(),
                                                message,
                                                Toast.LENGTH_LONG).show();

                                        getFragmentManager().popBackStack();

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getContext(),
                                            "No se pudo crear el registro",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                    ) {

                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {

                            Map<String, String> headers = new HashMap<>();
                            headers.put("Authorization", URLConstants.AUTH_HEADER);

                            return headers;
                        }
                    };

            queue.add(request);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void deleteContact() {

        String url = URLConstants.DELETE_CONTACT_URL.replace(
                "{CONTACT_ID}",
                this.contact.getId()
        );

        StringRequest request =
                new StringRequest(
                        Request.Method.DELETE,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Toast.makeText(getContext(),
                                        "Elemento eliminado exitosamente",
                                        Toast.LENGTH_LONG).show();

                                getFragmentManager().popBackStack();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(getContext(),
                                        "No fue posible eliminar el contacto",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                ) {

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {

                        Map<String, String> headers = new HashMap<>();
                        headers.put("Authorization", URLConstants.AUTH_HEADER);

                        return headers;

                    }
                };

        queue.add(request);

    }
}



