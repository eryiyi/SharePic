package com.lbins.SharePic.base;

public class InternetURL {
    public static final String INTERNAL = "http://wshare.apptech.space/api/";

    public static final String INTERNAL_PIC = "http://wshare.apptech.space";

    public static final String WEIXIN_APPID = "wxa5b2c67286faf2c6";
    public static final String WEIXIN_SECRET = "1ce50d86bf7678027d18e734ca71a4e1";
    public static final String WX_API_KEY="a393cc92c26041c3cdad965a931ba537";

    public static final String WEIXIN_partnerId = "1368485802";
    public static final String WEIXIN_MCH_ID = "1368485802";

    public static final String QINIU_SPACE = "paopao-pic";
    //多媒体文件上传接口
    public static final String UPLOAD_FILE = INTERNAL + "uploadImage.do";
    public static final String UPLOAD_TOKEN = INTERNAL + "token.json";
    //1登陆
    public static final String LOGIN__URL = INTERNAL + "memberLogin.do";
    //发布文章列表
    public static final String ADD_ARTICLE_URL = INTERNAL + "article/add";
    //2获取文章列表
    public static final String GET_ARTICLE_URL = INTERNAL + "article/lists";
    //文章详细
    public static final String DETAIL_ARTICLE_URL = INTERNAL + "article/detail";

    //获得验证码
    public static final String SEND_CODE_URL = INTERNAL + "user/sendCode";
    //校验验证码
    public static final String VER_CODE_URL = INTERNAL + "user/verifyCode";
    //图片上传
    public static final String UPLOAD_FILE_URL = INTERNAL + "article/uploadFile";


//    http://wshare.apptech.space/api/user/sendCode?user_name=13346299752
}
