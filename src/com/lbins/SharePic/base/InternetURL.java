package com.lbins.SharePic.base;

public class InternetURL {
    public static final String INTERNAL = "http://wshare.apptech.space/api/";

    public static final String WEIXIN_APPID = "wx4da8b73a07135cd1";
    public static final String WEIXIN_SECRET = "a393cc92c26041c3cdad965a931ba537";
    public static final String WX_API_KEY="a393cc92c26041c3cdad965a931ba537";

    public static final String WEIXIN_partnerId = "1368485802";
    public static final String WEIXIN_MCH_ID = "1368485802";

    public static final String QINIU_SPACE = "paopao-pic";
    //多媒体文件上传接口
    public static final String UPLOAD_FILE = INTERNAL + "uploadImage.do";
    public static final String UPLOAD_TOKEN = INTERNAL + "token.json";
    //1登陆
    public static final String LOGIN__URL = INTERNAL + "memberLogin.do";
    //2获取文章列表
    public static final String GET_ARTICLE_URL = INTERNAL + "article/lists";


}
