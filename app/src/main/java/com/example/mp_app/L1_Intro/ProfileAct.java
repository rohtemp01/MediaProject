package com.example.mp_app.L1_Intro;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mp_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ProfileAct extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private EditText mNameField;
    private EditText mEmailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        mNameField = findViewById(R.id.nameField);
        mEmailField = findViewById(R.id.emailField);

        // 개인 정보를 화면에 표시
        mNameField.setText(mCurrentUser.getDisplayName());
        mEmailField.setText(mCurrentUser.getEmail());

        Button updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mNameField.getText().toString();
                String email = mEmailField.getText().toString();

                updateProfile(name, email);
            }
        });
    }

    private void updateProfile(String name, String email) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

        mCurrentUser.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                            mCurrentUser.updateEmail(email)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "User email address updated.");
                                                Toast.makeText(ProfileAct.this, "Profile updated successfully.",
                                                        Toast.LENGTH_SHORT).show();
                                            } else {
                                                Log.w(TAG, "Updating email failed.", task.getException());
                                                Toast.makeText(ProfileAct.this, "Failed to update profile.",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            Log.w(TAG, "Updating profile failed.", task.getException());
                            Toast.makeText(ProfileAct.this, "Failed to update profile.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
