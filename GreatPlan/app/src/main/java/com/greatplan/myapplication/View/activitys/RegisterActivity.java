package com.greatplan.myapplication.View.activitys;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.greatplan.myapplication.Contract.BaseActivity;
import com.greatplan.myapplication.Presenters.RegisterPresenter;
import com.greatplan.myapplication.R;

public class RegisterActivity extends BaseActivity<RegisterPresenter> {
    public final String TAG=RegisterActivity.class.getSimpleName();

    EditText editText3;
    EditText editText4;
    Button button2;
    EditText editText5;

    @Override
    public void iniView() {
        editText3=findViewById(R.id.editText3);
        editText4=findViewById(R.id.editText4);
        editText5=findViewById(R.id.editText5);
        button2=findViewById(R.id.button2);
    }

    @Override
    public void initData() {
        //点击注册
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //拿到输入框的 昵称 账号 密码
                String nickname = editText3.getText().toString();
                String phone = editText4.getText().toString();
                String pass = editText5.getText().toString();

                //判断拿到的值
                if(TextUtils.isEmpty(nickname)){
                    Toast.makeText(RegisterActivity.this,"昵称格式有误",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(RegisterActivity.this,"手机号格式有误",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(RegisterActivity.this,"密码格式有误",Toast.LENGTH_LONG).show();
                    return;
                }
                pre.RegisterWay(phone,nickname,pass);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public RegisterPresenter initPresenter() {
        return new RegisterPresenter(this);
    }
}
