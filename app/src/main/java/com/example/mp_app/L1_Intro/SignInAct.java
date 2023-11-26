package com.example.mp_app.L1_Intro;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mp_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInAct extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonSignIn = findViewById(R.id.buttonSignIn);

        buttonSignIn.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            signIn(email, password);
        });
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Log.d(TAG, "로그인 성공: " + user.getEmail());
                        Toast.makeText(SignInAct.this, "로그인되었습니다.", Toast.LENGTH_SHORT).show();
                        startCoupleActivity();
                    } else {
                        Log.w(TAG, "로그인 실패", task.getException());
                        Toast.makeText(SignInAct.this, "로그인에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void startCoupleActivity() {
        Intent intent = new Intent(SignInAct.this, CoupleAct.class);
        startActivity(intent);
        finish();
    }
}
