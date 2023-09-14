package com.example.grocera;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.auth.User;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    ImageView googleBtn,fbBtn;
    Button loginBtn;
Intent signInIntent;
Intent loginIntent;
CallbackManager callbackManager = null;
EditText username, pass;
FirebaseAuth signInAuth = FirebaseAuth.getInstance();
FirebaseUser currentUser = signInAuth.getCurrentUser();
TextView newAccountbtn;
    @Override
    protected void onStart() {
        loginIntent = new Intent(LoginActivity.this, MainActivity.class);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account!=null)
        {UserId.setUserId(account.getId());
            System.out.println(UserId.getUserId());
            startActivity(loginIntent);
            finish();}
        else if (currentUser!=null)
        {   UserId.setUserId(currentUser.getUid());
            startActivity(loginIntent);
            finish();}
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken!=null && !accessToken.isExpired()){
            UserId.setUserId(accessToken.getUserId());
            startActivity(loginIntent);
            finish();
        }
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        username = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.pass);
        googleBtn = (ImageView) findViewById(R.id.googleBtn);
        setGoogleBtn();
        fbBtn = (ImageView) findViewById(R.id.fbBtn);
        setFbBtn();
        loginBtn = (Button) findViewById(R.id.loginbtn);
        customSignIn();
        newAccountbtn = (TextView) findViewById(R.id.signUp);
        newAccountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newAccountIntent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(newAccountIntent);
            }
        });
    }
    private void customSignIn(){
       loginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               signInAuth.signInWithEmailAndPassword(username.getText().toString(), pass.getText().toString())
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()) {
                                   // Sign in success, update UI with the signed-in user's information
                                   Log.d("SignIn success", "signInWithEmail:success");
                                   FirebaseUser user = signInAuth.getCurrentUser();
                                   assert user != null;
                                   UserId.setUserId(user.getUid());
                                   if(user.isEmailVerified())
                                   {startActivity(loginIntent);
                                       finish();}
                                   else
                                   {FirebaseAuth.getInstance().signOut();
                                       Toast.makeText(LoginActivity.this, "Please verify your account", Toast.LENGTH_SHORT).show();}
                               } else {
                                   // If sign in fails, display a message to the user.
                                   Log.w("SignIn Failure", "signInWithEmail:failure", task.getException());
                                   Toast.makeText(LoginActivity.this, "Authentication failed.",
                                           Toast.LENGTH_SHORT).show();
                                   //updateUI(null);
           }
       }
                    })
                       .addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                           }
                       });
                }});
    }
    private void setGoogleBtn(){
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestId()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent googleSignInIntent = mGoogleSignInClient.getSignInIntent();
               startActivityForResult(googleSignInIntent, 1);
            }
        });
    }
    private void setFbBtn(){
        fbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackManager = CallbackManager.Factory.create();
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        AccessToken accessToken = AccessToken.getCurrentAccessToken();
                        assert accessToken != null;
                        UserId.setUserId(accessToken.getUserId());
                        startActivity(loginIntent);
                        finish();
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(@NonNull FacebookException e) {

                    }
                });
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile"));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (callbackManager!=null)
            callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            UserId.setUserId(account.getId());
            startActivity(loginIntent);
            finish();
            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("LoginActivity", "signInResult:failed code=" + e.getStatusCode());
        }
    }
}
