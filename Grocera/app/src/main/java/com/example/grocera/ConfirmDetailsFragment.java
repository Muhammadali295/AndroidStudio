package com.example.grocera;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

public class ConfirmDetailsFragment extends Fragment {
    EditText userName, userContact, userAddress;
String name, contact, address;
Context context;


Button confirmbtn;
    public ConfirmDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm_details, container, false);
        userName = view.findViewById(R.id.username);
        userAddress = view.findViewById(R.id.address);
        userContact = view.findViewById(R.id.phone);
        confirmbtn = view.findViewById(R.id.confirm);
        context = container.getContext();
        popuLateUi();
        return view;}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        name = userName.getText().toString();
        contact = userContact.getText().toString();
        address = userAddress.getText().toString();
        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((name=="")||(contact=="")||(address=="")){
                    Toast.makeText(context, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Bundle bundle = new Bundle();
                    bundle.putString("Name", name);
                    bundle.putString("Contact",contact);
                    bundle.putString("Address", address);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer, PlaceOrder.class, bundle)
                            .commit();
                }
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    private void popuLateUi(){
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (account!=null){
            name=account.getDisplayName();
            userName.setText(name);
        }
        else if (currentUser!=null){
            name = currentUser.getDisplayName();
            contact = currentUser.getPhoneNumber();
            userName.setText(name);
            userContact.setText(contact);
        }
        else {
            GraphRequest request = GraphRequest.newMeRequest(accessToken,
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            try {

                                String name=object.getString("name");//use this for logout

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link");
            request.setParameters(parameters);
            request.executeAsync();
            userName.setText(name);
        }
}}