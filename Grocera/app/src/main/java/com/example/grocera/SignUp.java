package com.example.grocera;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SignUp extends AppCompatActivity {
    Button signUpbtn;
    EditText firstName, lastName, email, password, contact, repass;
    String firstNameString, lastNameString, contactString, emailString;
    TextView alreadyLogin;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signUpbtn = (Button) findViewById(R.id.signUpbtn);
        firstName = (EditText) findViewById(R.id.firstName);
        firstNameString = firstName.toString();
        lastName = (EditText) findViewById(R.id.lastName);
        lastNameString = lastName.toString();
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        repass = (EditText) findViewById(R.id.rePasword);
        contact = (EditText) findViewById(R.id.contact);
        contactString = contact.toString();
        emailString = email.getText().toString();
        alreadyLogin = (TextView) findViewById(R.id.alreadyLogin);
        setSignUpbtn();
        setAlreadyLogin();
    }

    private void setSignUpbtn() {
        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("SignUpActivity", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(firstNameString+lastNameString)
                                                    .build();
                                    assert user != null;
                                    user.updateProfile(userProfileChangeRequest);
                                    PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                                                    .setPhoneNumber(contactString)
                                                            .setTimeout(60L, TimeUnit.SECONDS)
                                                                    .setActivity(SignUp.this)
                                                                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                                                                @Override
                                                                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                                                                    user.updatePhoneNumber(phoneAuthCredential);
                                                                                    Toast.makeText(SignUp.this, "Your phone number has been verified", Toast.LENGTH_SHORT).show();
                                                                                }

                                                                                @Override
                                                                                public void onVerificationFailed(@NonNull FirebaseException e) {

                                                                                }
                                                                            })
                                                                                    .build();
                                    PhoneAuthProvider.verifyPhoneNumber(options);
                                    assert user != null;
                                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                mAuth.signOut();
                                                onBackPressed();
                                                Toast.makeText(getApplicationContext(), "Verification email sent", Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                                Toast.makeText(SignUp.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Failure", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignUp.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }
                        });
            }
        });
    }
    private void setAlreadyLogin(){
        alreadyLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent alreadyLoginIntent = new Intent (SignUp.this,LoginActivity.class);
                startActivity(alreadyLoginIntent);
            }
        });
    }
//    private void sendVerificationEmail(){
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        assert user != null;
//        user.sendEmailVerification()
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
//                            FirebaseAuth.getInstance().signOut();
//                            startActivity(new Intent(SignUp.this, LoginActivity.class));
//                            finish();
//                        }
//                        else {
//                            overridePendingTransition(0,0);
//                            finish();
//                            overridePendingTransition(0,0);
//                            startActivity(getIntent());
//                        }
//                    }
//                });
//    }
}
