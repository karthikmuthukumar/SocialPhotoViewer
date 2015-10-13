package com.codepath.socialphotoviewer;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by kmuthu1 on 10/10/15.
 */
public class InstagramPhoto {

    private String userName;
    private String profileImageUrl;
    private String imageUrl;
    private String caption;
    private int imageHeight;
    private int imageWidth;
    private int likesCount;


    private int commentsCount;

    private UserComment userComment;

    private String createdTime;

    public String getUserName() {
        return userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public String getCaption() {
        return caption;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public UserComment getUserComment() {
        return userComment;
    }

    public void setUserComment(UserComment userComment) {
        this.userComment = userComment;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getRelativeTime() {
        long ct = Long.parseLong(createdTime);
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Calendar calendar = Calendar.getInstance(timeZone);
        long now = 252000l + (calendar.getTime().getTime() / 1000);
        float elapsedSeconds = now - ct;
        System.out.println("Ct: "+ct+" Now: "+now);
        if (elapsedSeconds < 60) { // less than a minute
            return String.format("%.0fs", elapsedSeconds);
        } else if (elapsedSeconds < 3600) { // less than an hour
            return String.format("%.0fm", Math.floor(elapsedSeconds / 60));
        } else if (elapsedSeconds < 86400) { // less than a day
            return String.format("%.0fh", Math.floor(elapsedSeconds / 3600));
        } else {
            return String.format("%.0fd", Math.floor(elapsedSeconds / 86400));
        }
    }
}
