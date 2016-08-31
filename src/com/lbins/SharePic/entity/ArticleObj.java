package com.lbins.SharePic.entity;

import java.io.Serializable;

/**
 * Created by zhl on 2016/8/25.
 *  "message_id": "33",
 "nick_name": "marks",
 "user_name": "13266816551",
 "type_id": "11",
 "title": "最专业的海外房产移民展",
 "cover": "",
 "content": "
 <p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(86, 100, 115); font-family: Arial, 宋体, Helvetica, sans-serif; font-size: 14px; line-height: 25.2px; white-space: norm",
 "picture": "/Uploads/2015-09-01/55e53adeb3042.jpg",
 "movie": "/Uploads/2016-02-24/56cd2768885be.jpg",
 "accessory": null,
 "attention": "0",
 "praise": "0",
 "comment": "0",
 "date": "2016-01-14 10:14:01",
 "picture1": "/Uploads/2016-02-24/56cd276881d45.jpg",
 "picture2": "/Uploads/2016-02-24/56cd2768871ac.jpg",
 "picture3": "/Uploads/2016-02-24/56cd276887972.jpg",
 "picture4": "/Uploads/2016-08-16/57b2fc88ad11f.jpg",
 "picture5": "",
 "picture6": "",
 "picture7": "",
 "picture8": "",
 "picture9": "",
 "cat_name": "媒体"
 */
public class ArticleObj implements Serializable{
    private String message_id;
    private String nick_name;
    private String user_name;
    private String type_id;
    private String title;
    private String cover;
    private String content;
    private String picture;
    private String movie;
    private String accessory;
    private String attention;
    private String praise;
    private String comment;
    private String date;
    private String picture1;
    private String picture2;
    private String picture3;
    private String picture4;
    private String picture5;
    private String picture6;
    private String picture7;
    private String picture8;
    private String picture9;
    private String cat_name;

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getPraise() {
        return praise;
    }

    public void setPraise(String praise) {
        this.praise = praise;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }

    public String getPicture4() {
        return picture4;
    }

    public void setPicture4(String picture4) {
        this.picture4 = picture4;
    }

    public String getPicture5() {
        return picture5;
    }

    public void setPicture5(String picture5) {
        this.picture5 = picture5;
    }

    public String getPicture6() {
        return picture6;
    }

    public void setPicture6(String picture6) {
        this.picture6 = picture6;
    }

    public String getPicture7() {
        return picture7;
    }

    public void setPicture7(String picture7) {
        this.picture7 = picture7;
    }

    public String getPicture8() {
        return picture8;
    }

    public void setPicture8(String picture8) {
        this.picture8 = picture8;
    }

    public String getPicture9() {
        return picture9;
    }

    public void setPicture9(String picture9) {
        this.picture9 = picture9;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public ArticleObj(String message_id, String nick_name, String user_name, String type_id, String title, String cover, String content, String picture, String movie, String accessory, String attention, String praise, String comment, String date, String picture1, String picture2, String picture3, String picture4, String picture5, String picture6, String picture7, String picture8, String picture9, String cat_name) {
        this.message_id = message_id;
        this.nick_name = nick_name;
        this.user_name = user_name;
        this.type_id = type_id;
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.picture = picture;
        this.movie = movie;
        this.accessory = accessory;
        this.attention = attention;
        this.praise = praise;
        this.comment = comment;
        this.date = date;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 = picture3;
        this.picture4 = picture4;
        this.picture5 = picture5;
        this.picture6 = picture6;
        this.picture7 = picture7;
        this.picture8 = picture8;
        this.picture9 = picture9;
        this.cat_name = cat_name;
    }
}
