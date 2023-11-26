package com.example.mp_app.L1_Intro;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mp_app.R;
import com.example.mp_app.util.PreferenceUtil;
import com.example.mp_app.util.VerificationUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class SignUpAct extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference userRef;

    private EditText signUp_email_edit;
    private EditText signUp_password_edit;
    private Button signUp_btn;
    String tempKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("user");

        initView();
    }

    public void signup(View view) {
        final String email = signUp_email_edit.getText().toString();
        final String password = signUp_password_edit.getText().toString();

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 회원가입이 성공적으로 완료되었을 때
                            Toast.makeText(SignUpAct.this, "확인", Toast.LENGTH_SHORT).show();
                            FirebaseUser fUser = auth.getCurrentUser();
                            PreferenceUtil.setValue(SignUpAct.this, "userEmail", email);
                            PreferenceUtil.setValue(SignUpAct.this, "password", password);

                            tempKey = email.replace(".", "_");

                            // FirebaseMessaging을 사용하여 토큰을 가져오는 작업
                            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                                @Override
                                public void onComplete(@NonNull Task<String> task) {
                                    if (task.isSuccessful()) {
                                        // 토큰 가져오기가 성공했을 때
                                        String refreshedToken = task.getResult();
                                        User user = new User(fUser.getUid(), email, refreshedToken);
                                        userRef.child(tempKey).setValue(user);

                                        // tempKey를 전달하고 PhoneConnectActivity를 시작하는 부분
                                        moveToNext(tempKey);
                                    } else {
                                        // 토큰 가져오기가 실패했을 때
                                        Toast.makeText(SignUpAct.this, "토큰 가져오기 실패", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // 회원가입이 실패했을 때
                        Toast.makeText(SignUpAct.this, "확인 안됨 : " + e.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("로그", e.toString());
                    }
                });
    }

    public void moveToNext(String tempKey) {
        Intent intent = new Intent(SignUpAct.this, PhoneConnectAct.class);
        intent.putExtra("tempKey", tempKey);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    boolean checkEmail = false;
    boolean checkPassword = false;

    private void enableSignupButton() {
        if (checkEmail && checkPassword) {
            signUp_btn.setEnabled(true);
        } else {
            signUp_btn.setEnabled(false);
        }
    }

    private void initView() {
        signUp_email_edit = findViewById(R.id.signUp_email_edit);
        signUp_email_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkEmail = VerificationUtil.isValidEmail(charSequence.toString());
                enableSignupButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        signUp_password_edit = findViewById(R.id.signUp_password_edit);
        signUp_password_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkPassword = VerificationUtil.isValidPassword(charSequence.toString());
                enableSignupButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        signUp_btn = findViewById(R.id.signUp_btn);
    }

}