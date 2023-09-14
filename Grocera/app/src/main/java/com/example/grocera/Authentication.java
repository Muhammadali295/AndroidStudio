package com.example.grocera;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import org.json.JSONException;
import org.json.JSONObject;

public class Authentication {
    Context context;
    String name;
    TextView textView;
    public Authentication(Context context, TextView textView){
        this.context = context;
        this.textView = textView;
    }
   public void getGoogleAccount(){
       GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(context);
       if (googleSignInAccount!=null){
           name = googleSignInAccount.getDisplayName();
           textView.setText(name);
       }}
   public void getFacebookAccount(){
           AccessToken accessToken = AccessToken.getCurrentAccessToken();
           if (accessToken!=null){
               GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                   @Override
                   public void onCompleted(@Nullable JSONObject jsonObject, @Nullable GraphResponse graphResponse) {
                       try {
                           assert jsonObject != null;
                           name = jsonObject.getString("name");
                           textView.setText(name);
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }
               });
               Bundle parameters = new Bundle();
               parameters.putString("fields", "id,name,link");
               graphRequest.setParameters(parameters);
               graphRequest.executeAsync();
           }
       }

}
