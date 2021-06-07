package com.landmarkguideapp.diiscovery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.view.View;

public class MySettings_Activity extends AppCompatActivity
{
    private EditText et_firstName, et_lastName, et_emailAddress;
    private TextView tv_resetPassword;
    private Button btn_updateInfo, btn_signOut;

    String _FirstName, _LastName, _EmailAddress;

    FirebaseAuth mAuth;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_settings);

        // database reference
        reference = FirebaseDatabase.getInstance().getReference("Users");

        et_firstName = findViewById(R.id.editText_dbFirstName);
        et_lastName = findViewById(R.id.editText_dbLastName);
        et_emailAddress = findViewById(R.id.editText_dbEmailAddress);

        ShowInfo();

        tv_resetPassword = findViewById(R.id.btn_ResetPassword);
        tv_resetPassword.setOnClickListener(v -> {
            Intent myIntent = new Intent(MySettings_Activity.this, ForgotPassword_Activity.class);
            startActivity(myIntent);
        });

        btn_signOut = findViewById(R.id.btn_SignOut);
        btn_signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();

                mAuth.signOut();
                Intent intent = new Intent(MySettings_Activity.this, Login_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                finish();
            }
        });

    }

    private void ShowInfo() {
        Intent intent = getIntent();

        _FirstName = intent.getStringExtra("FirstName");
        _LastName = intent.getStringExtra("LastName");
        _EmailAddress = intent.getStringExtra("EmailAddress");

        et_firstName.setText(_FirstName);
        et_lastName.setText(_LastName);
        et_emailAddress.setText(_EmailAddress);
    }


    private void UpdateInfo(View view) {
        if(isFirstNameChanged() || isLastNameChanged() || isEmailAddressChanged()){
            Toast.makeText(this, "User Info has been updated!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Data has not been updated.", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isFirstNameChanged() {
        if(!_FirstName.equals(et_firstName.getText().toString())){
            reference.child(_FirstName).child("FirstName").setValue(et_firstName.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isLastNameChanged() {
        if(!_LastName.equals(et_lastName.getText().toString())){
            reference.child(_LastName).child("LastName").setValue(et_lastName.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isEmailAddressChanged() {
        if(!_EmailAddress.equals(et_emailAddress.getText().toString())){
            reference.child(_EmailAddress).child("EmailAddress").setValue(et_emailAddress.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }
}
