package com.greatplan.myapplication.View.activitys;


import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.greatplan.myapplication.Contract.BaseActivity;
import com.greatplan.myapplication.MyApp;
import com.greatplan.myapplication.Presenters.LoginPresenter;
import com.greatplan.myapplication.R;


public class LoginActivity extends BaseActivity<LoginPresenter> {
    
    EditText editText, editText2;
    Button button;
    String phone;
    String pass;
    String RSAPass;
    String aa="MSK5wv27ZHqwZXzrumnYn2SJDEJv19+58VpKyPwrbRXylzHP8NXScXpYKOmErCBN+YB8mJ5Q64nr7XCA+RMxKRKqdUfHX7mfFAlZu48WQrAtjiCK3MS3PeZc2mnRlvkER8kKGsY8a4RUsXw1M+gezRhuPR1jEbLLm4rxmmCoaKs=";
    TextView textView;
    @Override
    public void iniView() {
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        button = findViewById(R.id.button);
        textView=findViewById(R.id.textView);
    }

    @Override
    public void initData() {
        //去注册
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        //登陆按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取输入框的 账号 密码
                phone = editText.getText().toString();
                pass = editText2.getText().toString();

                pre.RSAEncryption(pass);

                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(MyApp.application, "账号格式不正确", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(MyApp.application, "密码有误", Toast.LENGTH_LONG).show();
                    return;
                }
                Log.i("tag", phone + pass);
                pre.LoginWay(phone, aa);
            }
        });
    }
    //RSA加密密码
//    public void RsaPWDData(String json) {
//        RsaEncryption rsaEncryption = new Gson().fromJson(json, RsaEncryption.class);
//        if (rsaEncryption != null) {
//            //加密后的密码
//            RSAPass=rsaEncryption.getCipherText();
//        }
//    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }


}
