package com.lbins.SharePic.data;

import com.lbins.SharePic.entity.ArticleObj;

import java.util.List;

/**
 * Created by zhl on 2016/8/25.
 */
public class RecordDATA extends Data{
    private List<ArticleObj> data;

    public List<ArticleObj> getData() {
        return data;
    }

    public void setData(List<ArticleObj> data) {
        this.data = data;
    }
}
