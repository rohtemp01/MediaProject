package com.example.mp_app.L1_Intro;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mp_app.R;

import com.example.mp_app.util.PreferenceUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PhoneConnectAct extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference userRef;
    DatabaseReference tempRoomRef;
    DatabaseReference chatRoomRef;
    DatabaseReference veriRef;
    private EditText myNum_edit;
    private EditText friendNum_edit;
    private Button phone_Connect_btn;
    String tempkey;
    ImageButton phoneNum_img_btn;
    TextView friendPhone_text;
    String phonenumber_local;
    boolean phoneNumberCheck;
    boolean phoneNumberCheck2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_connect);
        initView();

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("user");
        tempRoomRef = database.getReference("temp");
        chatRoomRef = database.getReference("chatRoom");

        Intent intent = getIntent();
        tempkey = intent.getStringExtra("tempKey");
        loadPhoneNumberLocalDatabase();
        checkPhoneNumber();
    }

    private void initView() {
        myNum_edit = findViewById(R.id.myNum_edit);
        friendNum_edit = findViewById(R.id.friendNum_edit);
        phone_Connect_btn = findViewById(R.id.phone_Connect_btn);
        phoneNum_img_btn = findViewById(R.id.phoneNum_img_btn);
        friendPhone_text = findViewById(R.id.friendPhone_text);
    }

    public void onConnect(View view){
        searchTemp();
    }

    private void searchTemp(){
        final String myNumber = myNum_edit.getText().toString();
        final String friendNumber = friendNum_edit.getText().toString();
        if (myNumber.isEmpty() || friendNumber.isEmpty()) {
            Toast.makeText(this, "번호를 입력해주세요.", Toast.LENGTH_LONG).show();
            return;
        }
        userRef.child(tempkey).child("myPhone").setValue(myNumber);
        userRef.child(tempkey).child("friendNumber").setValue(friendNumber);

        tempRoomRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChild(friendNumber)){
                    Toast.makeText(PhoneConnectAct.this, "가진 사용자 없음", Toast.LENGTH_LONG).show();
                    dataSnapshot.child(myNumber).child("friendNumber").getRef().setValue(friendNumber);
                    dataSnapshot.child(myNumber).child("status").child("confirm").getRef().setValue("none");
                    String chatRoom = myNumber + friendNumber + "chat";
                    String photoRoom = myNumber + friendNumber + "photo";

                    userRef.child(tempkey).child("roomID").child("id").setValue(chatRoom);
                    userRef.child(tempkey).child("photoID").child("id").setValue(photoRoom);
                    PreferenceUtil.setValue(PhoneConnectAct.this, "chatroom", chatRoom);
                    PreferenceUtil.setValue(PhoneConnectAct.this, "myNum", myNumber);
                    PreferenceUtil.setValue(PhoneConnectAct.this, "photoroom", photoRoom);

                    moveToAcceptActivity(myNumber, friendNumber);

                }else{
                    Toast.makeText(PhoneConnectAct.this, "가진 사용자 있음", Toast.LENGTH_LONG).show();
                    dataSnapshot.child(friendNumber).child("status").child("confirm").getRef().setValue("yes");
                    String chatRoom = friendNumber+ myNumber + "chat";
                    String photoRoom = friendNumber + myNumber + "photo";
                    userRef.child(tempkey).child("roomID").child("id").setValue(friendNumber+ myNumber + "chat");
                    userRef.child(tempkey).child("photoID").child("id").setValue(photoRoom);
                    PreferenceUtil.setValue(PhoneConnectAct.this, "chatroom", chatRoom);
                    PreferenceUtil.setValue(PhoneConnectAct.this, "myNum", myNumber);
                    PreferenceUtil.setValue(PhoneConnectAct.this, "photoroom", photoRoom);
                    moveToProfileActivity();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(PhoneConnectAct.this, "데이터베이스 오류", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void checkPhoneNumber() {
        myNum_edit.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        myNum_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 13) {
                    phoneNumberCheck = true;
                } else {
                    phoneNumberCheck = false;
                }
            }
        });

        friendNum_edit.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        friendNum_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 13) {
                    phoneNumberCheck2 = true;
                } else {
                    phoneNumberCheck2 = false;
                }
            }
        });
    }

    public void moveToProfileActivity() {
        Intent intent = new Intent(PhoneConnectAct.this, ProfileAct.class);
        intent.putExtra("tempkey2", tempkey);
        startActivity(intent);
    }

    public void moveToAcceptActivity(String myNumber, String friendNumber) {
        Intent intent = new Intent(PhoneConnectAct.this, AcceptAct.class);
        intent.putExtra("myNumber", myNumber);
        intent.putExtra("friendNumber", friendNumber);
        intent.putExtra("tempkey2", tempkey);
        startActivity(intent);
    }

    public void loadPhoneNumberLocalDatabase() {
        phoneNum_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                friendNum_edit.setText(phonenumber_local);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Cursor cursor = getContentResolver().query(data.getData(),
                    new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    phonenumber_local = cursor.getString(0);
                }
                cursor.close();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}