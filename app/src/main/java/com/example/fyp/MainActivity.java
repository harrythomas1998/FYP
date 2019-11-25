package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText eMail, password, cPassword, firstN, lastN;

    Button buttonSignUp;
    TextView txtSignIn;

    FirebaseAuth mFirebaseAuth;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User");

        eMail = findViewById(R.id.email1);
        firstN = findViewById(R.id.firstNameBox);
        lastN = findViewById(R.id.lastNameBox);
        password = findViewById(R.id.password1);
        cPassword = findViewById(R.id.confirm_password);

        buttonSignUp = findViewById(R.id.button1);
        txtSignIn = findViewById(R.id.textView1);



        buttonSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String emailText = eMail.getText().toString();
                String passwordText = password.getText().toString();
                String cPasswordText = cPassword.getText().toString();
                final String firstNameText = firstN.getText().toString();
                final String lastNameText = lastN.getText().toString();

                if(emailText.isEmpty() || passwordText.isEmpty() || cPasswordText.isEmpty() || firstNameText.isEmpty() || lastNameText.isEmpty()){

                    Toast.makeText(getApplicationContext(),"You must fill in all fields!",Toast.LENGTH_LONG).show();
                }

                else if(!cPasswordText.equals(passwordText)){

                    Toast.makeText(getApplicationContext(),"Passwords do not match!",Toast.LENGTH_SHORT).show();
                    cPassword.requestFocus();
                }
                else if(passwordText.contains(" ")){
                    Toast.makeText(getApplicationContext(),"Password cannot have a space!",Toast.LENGTH_LONG).show();
                    password.requestFocus();
                }
                else {
                    mFirebaseAuth.createUserWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){

                                Toast.makeText(getApplicationContext(),"Sign Up Unsuccessful, Try Again",Toast.LENGTH_SHORT).show();

                            }
                            else{

                                User user = new User(firstNameText, lastNameText, emailText);

                                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser()

                                        .getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        startActivity(new Intent(MainActivity.this, HomeActivity.class));

                                    }
                                });



                            }


                        }
                    });
                }


            }
        });

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

    }


}
