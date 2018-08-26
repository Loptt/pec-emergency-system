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

public class SignUp extends AppCompatActivity {

    Button bSignUp;

    EditText nameField;
    EditText emailField;
    EditText passwordField;
    EditText cPasswordField;

    Intent mainIntent;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        bSignUp = findViewById(R.id.joinButton);
        nameField = findViewById(R.id.takeDataName_et);
        emailField = findViewById(R.id.takeDataEmail_et);
        passwordField = findViewById(R.id.takeDataPassword_et);
        cPasswordField = findViewById(R.id.takeDateConfirmPassword_et);

        mainIntent = new Intent(this,MainActivity.class);

        mAuth = FirebaseAuth.getInstance();

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp(emailField.getText().toString(),passwordField.getText().toString());
            }
        });

    }

    public void signUp(String email,String password){
        if(!validatedata()){
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(mainIntent);
                        } else{
                            Toast.makeText(SignUp.this, "Account Creation Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    boolean validatedata(){
        boolean valid = true;

        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        String cpassword = cPasswordField.getText().toString();

        if(TextUtils.isEmpty(name)){
            nameField.setError("Required");
            valid = false;
        } else{
            nameField.setError(null);
        }

        if(TextUtils.isEmpty(email)){
            emailField.setError("Required");
            valid = false;
        } else{
            emailField.setError(null);
        }

        if(TextUtils.isEmpty(password)){
            passwordField.setError("Required");
            valid = false;
        } else{
            passwordField.setError(null);
        }

        if(!(password.equals(cpassword))){
            passwordField.setError("Passwords do not match");
            cPasswordField.setError("Passwords do not match");
            valid = false;
        }

        return valid;
    }

}
