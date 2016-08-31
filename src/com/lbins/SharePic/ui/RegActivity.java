package com.lbins.SharePic.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhl on 2016/8/24.
 */
public class RegActivity extends BaseActivity implements View.OnClickListener {
    private TextView title;
    private EditText mobile;
    private EditText code;
    private TextView btn_code;

    private boolean progressShow;
    ProgressDialog pd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        this.findViewById(R.id.back).setOnClickListener(this);
        this.findViewById(R.id.btn_reg).setOnClickListener(this);
        title = (TextView) this.findViewById(R.id.title);
        title.setText("注册账号");

        mobile = (EditText) this.findViewById(R.id.mobile);
        code = (EditText) this.findViewById(R.id.code);
        btn_code = (TextView) this.findViewById(R.id.btn_code);
        btn_code.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.btn_reg:
            {
                if (StringUtil.isNullOrEmpty(mobile.getText().toString())) {
                    showMsg(RegActivity.this, "请输入手机号");
                    return;
                }

                if (StringUtil.isNullOrEmpty(code.getText().toString())) {
                    showMsg(RegActivity.this, "请输入验证码");
                    return;
                }
                progressShow = true;
                pd = new ProgressDialog(RegActivity.this);
                pd.setCanceledOnTouchOutside(false);
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        progressShow = false;
                    }
                });
                pd.setMessage(getString(R.string.please_wait));
                pd.show();
                verCard();
            }
                break;
            case R.id.btn_code:
                btn_code.setClickable(false);//不可点击
                MyTimer myTimer = new MyTimer(60000, 1000);
                myTimer.start();

                progressShow = true;
                pd = new ProgressDialog(RegActivity.this);
                pd.setCanceledOnTouchOutside(false);
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        progressShow = false;
                    }
                });
                pd.setMessage(getString(R.string.please_wait));
                pd.show();
                getCard();
                break;
        }
    }

    void getCard() {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                InternetURL.SEND_CODE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (StringUtil.isJson(s)) {
                            try {
                                JSONObject jo = new JSONObject(s);
                                String code = jo.getString("code");
                                if (Integer.parseInt(code) == 200) {
                                    Toast.makeText(RegActivity.this, "发送验证码成功，请注意查收", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(RegActivity.this, jo.getString("alert"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(RegActivity.this, "获得验证码失败", Toast.LENGTH_SHORT).show();
                        }
                        if (pd != null && pd.isShowing()) {
                            pd.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if (pd != null && pd.isShowing()) {
                            pd.dismiss();
                        }
                        Toast.makeText(RegActivity.this,  "获得验证码失败", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_name", mobile.getText().toString());
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

    class MyTimer extends CountDownTimer {

        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            btn_code.setText(getResources().getString(R.string.daojishi_three));
            btn_code.setClickable(true);//可点击
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btn_code.setText(getResources().getString(R.string.daojishi_one) + millisUntilFinished / 1000 + getResources().getString(R.string.daojishi_two));
        }
    }

    void verCard() {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                InternetURL.VER_CODE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (StringUtil.isJson(s)) {
                            try {
                                JSONObject jo = new JSONObject(s);
                                String code = jo.getString("code");
                                if (Integer.parseInt(code) == 200) {
                                    Toast.makeText(RegActivity.this, "验证成功，请使用微信登录", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegActivity.this, RegSuccessActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(RegActivity.this, jo.getString("alert"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(RegActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                        }
                        if (pd != null && pd.isShowing()) {
                            pd.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if (pd != null && pd.isShowing()) {
                            pd.dismiss();
                        }
                        Toast.makeText(RegActivity.this,  "验证失败", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_name", mobile.getText().toString());
                params.put("code", code.getText().toString());
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
