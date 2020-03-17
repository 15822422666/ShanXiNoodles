package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RetrievePasswordActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView textView;
    String question;
    String answer;
    EditText new_answer;
    EditText new_password;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember_password);
        textView = findViewById(R.id.question);
        new_answer = findViewById(R.id.answer);
        new_password = findViewById(R.id.new_password);
        button = findViewById(R.id.register_password_button);
        sharedPreferences = getSharedPreferences("register_date", MODE_PRIVATE);
        question = sharedPreferences.getString("register_question", "");
        answer = sharedPreferences.getString("register_answer", "");
        textView.setText("密保：" + question);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //改密逻辑
                if(new_answer.getText().toString().equals(answer)) {
                    if(new_password.getText().toString().equals("")) {
                        Toast.makeText(RetrievePasswordActivity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                    } else {
                        editor = getSharedPreferences("register_date",MODE_PRIVATE).edit();
                        editor.putString("register_password", new_password.getText().toString());
                        editor.apply();
                        Toast.makeText(RetrievePasswordActivity.this, "成功找回密码", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(RetrievePasswordActivity.this, "请正确输入密保答案", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
