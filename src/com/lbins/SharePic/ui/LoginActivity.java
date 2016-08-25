package com.lbins.SharePic.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.lbins.SharePic.MainActivity;
import com.lbins.SharePic.R;
import com.lbins.SharePic.base.BaseActivity;

/**
 * Created by zhl on 2016/8/24.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.findViewById(R.id.regBtn).setOnClickListener(this);
        this.findViewById(R.id.btn_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.regBtn:
            {
                //注册
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
            }
                break;
            case R.id.btn_login:
            {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
                break;
        }
    }

}
