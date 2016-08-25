package com.lbins.SharePic.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.lbins.SharePic.MainActivity;
import com.lbins.SharePic.R;
import com.lbins.SharePic.base.BaseActivity;

/**
 * Created by zhl on 2016/8/24.
 */
public class RegSuccessActivity extends BaseActivity implements View.OnClickListener {
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_success);

        this.findViewById(R.id.icon_weixin).setOnClickListener(this);
        this.findViewById(R.id.back).setOnClickListener(this);
        title = (TextView) this.findViewById(R.id.title);
        title.setText("验证成功");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.icon_weixin:
                Intent intent = new Intent(RegSuccessActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

}
