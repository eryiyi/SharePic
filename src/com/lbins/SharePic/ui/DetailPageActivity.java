package com.lbins.SharePic.ui;

import android.os.Bundle;
import android.view.View;
import com.lbins.SharePic.R;
import com.lbins.SharePic.base.BaseActivity;
import com.lbins.SharePic.entity.ArticleObj;
import com.lbins.SharePic.util.Constants;

/**
 * Created by zhl on 2016/8/31.
 */
public class DetailPageActivity extends BaseActivity implements View.OnClickListener {
    private ArticleObj articleObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articleObj = (ArticleObj) getIntent().getExtras().get(Constants.INFO);
        setContentView(R.layout.main);
    }

    @Override
    public void onClick(View view) {

    }
}
