package com.lbins.SharePic.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lbins.SharePic.R;
import com.lbins.SharePic.base.BaseActivity;
import com.lbins.SharePic.base.InternetURL;
import com.lbins.SharePic.util.StringUtil;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhl on 2016/8/24.
 */
public class RegSuccessActivity extends BaseActivity implements View.OnClickListener {
    private TextView title;
    private IWXAPI api;

    public static String mobile;//注册的手机号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_success);
        mobile = getIntent().getExtras().getString("mobile");
        this.findViewById(R.id.icon_weixin).setOnClickListener(this);
        this.findViewById(R.id.back).setOnClickListener(this);
        title = (TextView) this.findViewById(R.id.title);
        title.setText("验证成功");
        api = WXAPIFactory.createWXAPI(this, InternetURL.WEIXIN_APPID, false);
        api.registerApp(InternetURL.WEIXIN_APPID);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.icon_weixin:
                // send oauth request
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                api.sendReq(req);
                break;
        }
    }


}
