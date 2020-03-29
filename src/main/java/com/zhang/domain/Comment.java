package com.zhang.domain;

import java.util.Date;

/**
 * @author prayers
 * @date 2020/1/14 20:49
 */
public class Comment {


    @Override
    public String toString() {
        return "Comment{" +
                "user_id=" + user_id +
                ", scenicspot_id=" + scenicspot_id +
                ", comment_title='" + comment_title + '\'' +
                ", comment_content='" + comment_content + '\'' +
                ", comment_time=" + comment_time +
                '}';
    }

    /**用户id*/
    private int user_id;
    private int scenicspot_id;
    private String comment_title;
    private String comment_content;
    private Date comment_time;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getScenicspot_id() {
        return scenicspot_id;
    }

    public void setScenicspot_id(int scenicspot_id) {
        this.scenicspot_id = scenicspot_id;
    }

    public String getComment_title() {
        return comment_title;
    }

    public void setComment_title(String comment_title) {
        this.comment_title = comment_title;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }
}
