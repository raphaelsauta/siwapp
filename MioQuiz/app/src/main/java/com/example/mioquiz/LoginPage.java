package com.example.mioquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.time.temporal.Temporal;

public class LoginPage extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 10;

    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.loginpage);


        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);

        Info.setText("No of attempts remaining: 10");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }

    //Create the Validate Function
    private void validate(String userName, String userPassword) {
        if ((userName.equals("onemillion")) && (userPassword.equals("CHF"))) {
            Intent intent = new Intent(LoginPage.this, LoginPage.class);
            startActivity(intent);
        } else {
            counter--;

            Info.setText("No of attempts remaining: " + String.valueOf(counter));

            if (counter == 0) {
                Login.setEnabled(false);
            }
        }
    }
}

