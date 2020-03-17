package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import homepage.HomePageActivity;

public class LogInActivity extends AppCompatActivity {

    Button button_login;
    Button button_register;
    Button button_retrieve_password;
    EditText user_name_edit;
    EditText password_edit;
    CheckBox remember_password;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //添加沉浸效果
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //
        user_name_edit = findViewById(R.id.user_edit);
        password_edit = findViewById(R.id.password_edit);
        remember_password = findViewById(R.id.remember_password);
        button_retrieve_password = findViewById(R.id.retrieve_password);
        button_login = findViewById(R.id.log_in_button);
        button_register = findViewById(R.id.register);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences = getSharedPreferences("register_date", MODE_PRIVATE);
        boolean isRemember = sharedPreferences.getBoolean("remember_password", false);
        if(isRemember) {
            //将账号密码设置到文本框
            String user_name_date = sharedPreferences.getString("register_name", "");
            String password_date = sharedPreferences.getString("register_password", "");
            user_name_edit.setText(user_name_date);
            password_edit.setText(password_date);
            remember_password.setChecked(true);
        }
        //注册登录按钮监听器
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = user_name_edit.getText().toString();
                String password = password_edit.getText().toString();
                //登录逻辑
                String user_name_date_login = sharedPreferences.getString("register_name", "");
                String password_date_login = sharedPreferences.getString("register_password", "");
                if(user_name.equals("") || password.equals("")) {
                    Toast.makeText(LogInActivity.this, "请输入账号密码", Toast.LENGTH_SHORT).show();
                } else if (user_name.equals(user_name_date_login) && password.equals(password_date_login)){
                    editor = sharedPreferences.edit();
                    //检查复选框
                    if (remember_password.isChecked()) {
                        editor.putBoolean("remember_password", true);
                        editor.putString("register_name", user_name);
                        editor.putString("register_password", password);
                    } else {
                        //有待添加逻辑
                    }

                    editor.apply();
                    Intent intent = new Intent(LogInActivity.this, HomePageActivity.class);
                    startActivity(intent);
                    Toast.makeText(LogInActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(LogInActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //注册注册按钮监听器
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        //注册忘记密码按钮
        button_retrieve_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, RetrievePasswordActivity.class);
                startActivity(intent);
            }
        });
    }

}
