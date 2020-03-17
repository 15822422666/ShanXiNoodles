package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button button;
    EditText register_name;
    EditText register_password;
    EditText register_question;
    EditText register_answer;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_name = findViewById(R.id.register_user_name);
        register_password = findViewById(R.id.register_password);
        register_question = findViewById(R.id.register_question);
        register_answer = findViewById(R.id.register_answer);
        button = findViewById(R.id.register_button);
        //数据持久化
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(register_name.getText().toString().equals("") || register_password.getText().toString().equals("") ||
                        register_question.getText().toString().equals("") || register_answer.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "请完善信息", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("register_date",MODE_PRIVATE).edit();
                    editor.putString("register_name", register_name.getText().toString());
                    editor.putString("register_password", register_password.getText().toString());
                    editor.putString("register_question", register_question.getText().toString());
                    editor.putString("register_answer", register_answer.getText().toString());
                    editor.apply();
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
