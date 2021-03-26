package com.example.myeverytime;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.myeverytime.main.boards.freeboard.FreeBoardActivity;
import com.example.myeverytime.signIn.SignInActivity;
import com.example.myeverytime.signUp.SignUpInputFormActivity;

import static com.example.myeverytime.SharedPreference.getAttribute;

public class MainActivity extends AppCompatActivity {

    Button button1, button2;
    TextView hello_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        hello_user = findViewById(R.id.hello_user);

        button1.setOnClickListener(v -> { // 로그인 화면으로
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
        });

        button2.setOnClickListener(v -> { // 자유게시판 화면으로
            Intent intent = new Intent(MainActivity.this, FreeBoardActivity.class);
            startActivity(intent);
        });


    }
}