package com.lbins.SharePic.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lbins.SharePic.R;
import com.lbins.SharePic.base.BaseActivity;
import com.lbins.SharePic.base.InternetURL;
import com.lbins.SharePic.ui.RegActivity;
import com.lbins.SharePic.util.StringUtil;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhl on 2016/9/1.
 */
public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private IWXAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wx_login_activity);
        api = WXAPIFactory.createWXAPI(this, InternetURL.WEIXIN_APPID, false);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onResp(BaseResp resp) {
        Bundle bundle = new Bundle();
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
//		可用以下两种方法获得code
//		resp.toBundle(bundle);
//		Resp sp = new Resp(bundle);
//		String code = sp.code;<span style="white-space:pre">
//		或者
                String code = ((SendAuth.Resp) resp).code;
                //上面的code就是接入指南里要拿到的code
                verCard(code);
                break;

            default:
                break;
        }

    }

    void verCard(final String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + InternetURL.WEIXIN_APPID + "&secret="+InternetURL.WEIXIN_SECRET +
                "&code=" + code + "&grant_type=authorization_code";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (StringUtil.isJson(s)) {
                            try {
                                JSONObject jo = new JSONObject(s);
                                String code = jo.getString("code");
                                if (Integer.parseInt(code) == 200) {

                                } else {
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        getRequestQueue().add(request);
    }
}
