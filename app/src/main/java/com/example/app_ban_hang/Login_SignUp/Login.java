package com.example.puppystore;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mLoginBtn,mLoginggBtn;
    TextView forgotTextLink;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    //private GoogleSignInClient googleSignInClient;
//    private int RESULT_CODE_SINGIN=999;
//    private String TAG="mainTag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        GoogleSignInOptions gso = new
//                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
////
////        // Build a GoogleSignInClient with the options specified by gso.
//        googleSignInClient = GoogleSignIn.getClient(this,gso);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.loginBtn);
        //mLoginggBtn=findViewById(R.id.login_gg);
        //mCreateBtn = findViewById(R.id.createText);
        forgotTextLink = findViewById(R.id.forgotPassword);


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password Must be >= 6 Characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // authenticate the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(Login.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });

            }
        });
//        mLoginggBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signInM();
//               // startActivity(new Intent(getApplicationContext(),MainActivity.class));
//
//            }
//        });

//       // mCreateBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),Register.class));
//            }
//       // });

        forgotTextLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract the email and send reset link
                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });

                passwordResetDialog.create().show();

            }
        });





    }
//    private void signInM() {
//        Intent singInIntent = googleSignInClient.getSignInIntent();
//        startActivityForResult(singInIntent,RESULT_CODE_SINGIN);
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RESULT_CODE_SINGIN) {        //just to verify the code
//            //create a Task object and use GoogleSignInAccount from Intent and write a separate method to handle singIn Result.
//
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//    }


//    private void handleSignInResult(Task<GoogleSignInAccount> task) {
//
//        //we use try catch block because of Exception.
//        try {
//            mLoginggBtn.setVisibility(View.INVISIBLE);
//            GoogleSignInAccount account = task.getResult(ApiException.class);
//            Toast.makeText(Login.this,"Signed In successfully",Toast.LENGTH_LONG).show();
//            //SignIn successful now show authentication
//            FirebaseGoogleAuth(account);
//
//        } catch (ApiException e) {
//            e.printStackTrace();
//            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            Toast.makeText(Login.this,"SignIn Failed!!!",Toast.LENGTH_LONG).show();
//            FirebaseGoogleAuth(null);
//        }
//    }

//    private void FirebaseGoogleAuth(GoogleSignInAccount account) {
//       AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
//        //here we are checking the Authentication Credential and checking the task is successful or not and display the message
//        //based on that.
//        fAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()){
//                    Toast.makeText(Login.this,"successful",Toast.LENGTH_LONG).show();
//                    FirebaseUser firebaseUser = fAuth.getCurrentUser();
//                    UpdateUI(firebaseUser);
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                }
//                else {
//                    Toast.makeText(Login.this,"Failed!",Toast.LENGTH_LONG).show();
//                    UpdateUI(null);
//                }
//            }
//        });
//
//    }
//    private void UpdateUI(FirebaseUser fUser) {
//        mLoginggBtn.setVisibility(View.VISIBLE);
//
//        //getLastSignedInAccount returned the account
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
//        if (account !=null){
//            String personName = account.getDisplayName();
//            String personGivenName = account.getGivenName();
//            String personEmail = account.getEmail();
//            String personId = account.getId();
//
//            Toast.makeText(Login.this,personName + "  " + personEmail,Toast.LENGTH_LONG).show();
//        }
//    }

}
