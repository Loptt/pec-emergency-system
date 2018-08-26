package com.pacosanchez.emergencysystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText emailTextField;
    EditText passwordTextField;

    Button bLogin;
    Button bSignUp;
    Button bFamSignUp;

    Intent mainIntent;
    Intent signUpIntent;

    DatabaseReference databaseUsers;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseUsers = FirebaseDatabase.getInstance().getReference();
        emailTextField = findViewById(R.id.emailField);
        passwordTextField = findViewById(R.id.passwordField);

        bLogin = findViewById(R.id.LogIn_Button);
        bSignUp = findViewById(R.id.SignUp_Button);
        bFamSignUp = findViewById(R.id.Family_Button);

        mainIntent = new Intent(this, MainActivity.class);
        signUpIntent = new Intent(this, SignUp.class);


        mAuth = FirebaseAuth.getInstance();

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(emailTextField.getText().toString(),passwordTextField.getText().toString());
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            startActivity(mainIntent);
        }
    }

    public void signIn(String email,String password){
        if(!validateData()){
            return;
        }


        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(mainIntent);
                        } else{
                            Toast.makeText(LoginActivity.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean validateData(){
        boolean valid = true;

        String email = emailTextField.getText().toString();
        if(TextUtils.isEmpty(email)){
            emailTextField.setError("Required");
            valid = false;
        }
        else{
            emailTextField.setError(null);
        }

        String password = passwordTextField.getText().toString();
        if(TextUtils.isEmpty(password)){
            passwordTextField.setError("Required");
            valid = false;
        }
        else{
            passwordTextField.setError(null);
        }
        return valid;
    }

}
