package com.lbins.SharePic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.lbins.SharePic.adapter.OnClickContentItemListener;
import com.lbins.SharePic.adapter.RecordAdapter;
import com.lbins.SharePic.base.BaseActivity;
import com.lbins.SharePic.base.InternetURL;
import com.lbins.SharePic.data.RecordDATA;
import com.lbins.SharePic.entity.ArticleObj;
import com.lbins.SharePic.library.PullToRefreshBase;
import com.lbins.SharePic.library.PullToRefreshListView;
import com.lbins.SharePic.ui.AddRecordActivity;
import com.lbins.SharePic.util.Constants;
import com.lbins.SharePic.util.HttpUtils;
import com.lbins.SharePic.util.StringUtil;
import com.lbins.SharePic.widget.CustomProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements View.OnClickListener,OnClickContentItemListener{
    //动态listview
    private PullToRefreshListView home_lstv;
    //动态适配器
    private RecordAdapter adapter;
    private int pageIndex = 1;
    public static boolean IS_REFRESH = true;

    boolean isMobileNet, isWifiNet;
    private List<ArticleObj> lists = new ArrayList<ArticleObj>();

    private String emp_id; //当前登陆者ID
    private ImageView icon_write;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        icon_write = (ImageView) this.findViewById(R.id.icon_write);
        icon_write.setOnClickListener(this);

        emp_id = getGson().fromJson(getSp().getString(Constants.EMPID, ""), String.class);

        home_lstv = (PullToRefreshListView) this.findViewById(R.id.lstv);
        adapter = new RecordAdapter(lists, MainActivity.this, emp_id);
        home_lstv.setMode(PullToRefreshBase.Mode.BOTH);
        home_lstv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                IS_REFRESH = true;
                pageIndex = 1;
                //判断是否有网
                try {
                    isMobileNet = HttpUtils.isMobileDataEnable(MainActivity.this);
                    isWifiNet = HttpUtils.isWifiDataEnable(MainActivity.this);
                    if (!isMobileNet && !isWifiNet) {
                        home_lstv.onRefreshComplete();
                    } else {
                        initData();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                IS_REFRESH = false;
                pageIndex++;
                //判断是否有网
                try {
                    isMobileNet = HttpUtils.isMobileDataEnable(MainActivity.this);
                    isWifiNet = HttpUtils.isWifiDataEnable(MainActivity.this);
                    if (!isMobileNet && !isWifiNet) {
                        home_lstv.onRefreshComplete();
                    } else {
                        initData();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        home_lstv.setAdapter(adapter);
        home_lstv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //判断是否有网
                try {
                    isMobileNet = HttpUtils.isMobileDataEnable(MainActivity.this);
                    isWifiNet = HttpUtils.isWifiDataEnable(MainActivity.this);
                    if (!isMobileNet && !isWifiNet) {
                        Toast.makeText(MainActivity.this, "请检查网络链接", Toast.LENGTH_SHORT).show();
                    } else {
//                        Record record = recordList.get(position - 1);
//                        if (!record.getRecordType().equals("1")) {
//                            Intent detail = new Intent(getActivity(), DetailPageAcitvity.class);
//                            detail.putExtra(Constants.INFO, record);
//                            startActivity(detail);
//                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        adapter.setOnClickContentItemListener(this);

        //判断是否有网
        try {
            isMobileNet = HttpUtils.isMobileDataEnable(MainActivity.this);
            isWifiNet = HttpUtils.isWifiDataEnable(MainActivity.this);
            if (!isMobileNet && !isWifiNet) {
//                recordList.addAll(DBHelper.getInstance(MainActivity.this).getRecordList());
//                adapter.notifyDataSetChanged();
            } else {
                progressDialog = new CustomProgressDialog(MainActivity.this, "正在加载中", R.anim.custom_dialog_frame);
                progressDialog.show();
                initData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClickContentItem(int position, int flag, Object object) {
        switch (flag){
            case 1:
            {
                //评论
                showMsg(MainActivity.this, "点击了评论");
            }
                break;
            case 2:
            {
                //转发
                showMsg(MainActivity.this, "点击了转发");
            }
                break;

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.icon_write:
            {
                //添加
                Intent intent = new Intent(MainActivity.this, AddRecordActivity.class);
                startActivity(intent);
            }
            break;
        }
    }

//    public void initData() {
//        StringRequest request = new StringRequest(
//                Request.Method.POST,
//                InternetURL.GET_ARTICLE_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String s) {
//                        if (StringUtil.isJson(s)) {
//                            RecordDATA data = getGson().fromJson(s, RecordDATA.class);
//                            if (data.getCode() == 200) {
//                                if (IS_REFRESH) {
//                                    lists.clear();
//                                }
//                                lists.addAll(data.getData());
//                                home_lstv.onRefreshComplete();
//                                adapter.notifyDataSetChanged();
//                                //处理数据，需要的话保存到数据库
////                                if (data != null && data.getData() != null) {
////                                    DBHelper dbHelper = DBHelper.getInstance(getActivity());
////                                    for (Record record1 : data.getData()) {
////                                        if (dbHelper.getRecordById(record1.getRecordId()) == null) {
////                                            DBHelper.getInstance(getActivity()).saveRecord(record1);
////                                        }
////                                    }
////                                }
//                            } else {
//                                Toast.makeText(MainActivity.this, R.string.get_data_error, Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            Toast.makeText(MainActivity.this, R.string.get_data_error, Toast.LENGTH_SHORT).show();
//                        }
//                        if(progressDialog != null){
//                            progressDialog.dismiss();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) {
//                        if(progressDialog != null){
//                            progressDialog.dismiss();
//                        }
//                        Toast.makeText(MainActivity.this, R.string.get_data_error, Toast.LENGTH_SHORT).show();
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("Content-Type", "application/x-www-form-urlencoded");
//                return params;
//            }
//        };
//
//        request.setRetryPolicy(new DefaultRetryPolicy(10000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        getRequestQueue().add(request);
//    }

    void initData(){
        lists.add(new ArticleObj("message_id", "晚霞落日", "晚霞落日",
                "1", "最专业的海外房产移民展", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "内容",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "0", "0", "0",
                "0", "2016-01-14 10:14:01", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "媒体"
        ));
        lists.add(new ArticleObj("message_id", "晚霞落日", "晚霞落日",
                "1", "最专业的海外房产移民展", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "内容",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "0", "0", "0",
                "0", "2016-01-14 10:14:01", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "媒体"
        ));
        lists.add(new ArticleObj("message_id", "晚霞落日", "晚霞落日",
                "1", "最专业的海外房产移民展", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "内容",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "0", "0", "0",
                "0", "2016-01-14 10:14:01", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "", "", "",
                "", "", "媒体"
        ));
        lists.add(new ArticleObj("message_id", "晚霞落日", "晚霞落日",
                "1", "最专业的海外房产移民展", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "内容",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "0", "0", "0",
                "0", "2016-01-14 10:14:01", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "",
                "", "", "媒体"
        ));
        lists.add(new ArticleObj("message_id", "晚霞落日", "晚霞落日",
                "1", "最专业的海外房产移民展", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "内容",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "0", "0", "0",
                "0", "2016-01-14 10:14:01", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "", "", "",
                "", "", "",
                "", "", "媒体"
        ));
        lists.add(new ArticleObj("message_id", "晚霞落日", "晚霞落日",
                "1", "最专业的海外房产移民展", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "内容",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "0", "0", "0",
                "0", "2016-01-14 10:14:01", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "", "",
                "", "", "",
                "", "", "媒体"
        ));
        lists.add(new ArticleObj("message_id", "晚霞落日", "晚霞落日",
                "1", "最专业的海外房产移民展", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "内容",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "0", "0", "0",
                "0", "2016-01-14 10:14:01", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "",
                "", "", "",
                "", "", "媒体"
        ));
        lists.add(new ArticleObj("message_id", "晚霞落日", "晚霞落日",
                "1", "最专业的海外房产移民展", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "内容",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "0", "0", "0",
                "0", "2016-01-14 10:14:01", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "", "", "",
                "", "", "媒体"
        )); lists.add(new ArticleObj("message_id", "晚霞落日", "晚霞落日",
                "1", "最专业的海外房产移民展", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "内容",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "0", "0", "0",
                "0", "2016-01-14 10:14:01", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "", "媒体"
        )); lists.add(new ArticleObj("message_id", "晚霞落日", "晚霞落日",
                "1", "最专业的海外房产移民展", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "内容",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",  "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "0", "0", "0",
                "0", "2016-01-14 10:14:01", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg",
                "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "http://wshare.apptech.space/Uploads/2016-02-24/56cd296d34dcb.jpg", "媒体"
        ));


        adapter.notifyDataSetChanged();
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }
}
