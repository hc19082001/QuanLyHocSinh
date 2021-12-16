package com.example.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edt_username;
    ssssss
    EditText edt_password;
    Button btnDANGNHAP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        btnDANGNHAP = findViewById(R.id.btnDANGNHAP);

        btnDANGNHAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = edt_username.getText().toString();
                String password = edt_password.getText().toString();

                if(username.equals("admin")){
                  if(password.equals("admin")){
                      Toast.makeText(MainActivity.this, "ĐĂNG NHẬP THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(MainActivity.this,MainActivityMENU.class);
                      startActivity(intent);
                  }else{
                      edt_password.setError("SAI MẬT KHẨU");
                  }
                }else{
                    edt_username.setError("SAI TÀI KHOẢN");
                }
            }

        });

    }
}