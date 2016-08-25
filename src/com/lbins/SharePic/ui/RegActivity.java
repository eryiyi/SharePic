package com.lbins.SharePic.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.lbins.SharePic.R;
import com.lbins.SharePic.base.BaseActivity;

/**
 * Created by zhl on 2016/8/24.
 */
public class RegActivity extends BaseActivity implements View.OnClickListener {
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        this.findViewById(R.id.back).setOnClickListener(this);
        this.findViewById(R.id.btn_reg).setOnClickListener(this);
        title = (TextView) this.findViewById(R.id.title);
        title.setText("注册账号");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.btn_reg:
            {
                Intent intent = new Intent(RegActivity.this, RegSuccessActivity.class);
                startActivity(intent);
            }
                break;
        }
    }

}
