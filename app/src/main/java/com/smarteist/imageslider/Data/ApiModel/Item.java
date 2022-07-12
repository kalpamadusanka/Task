package com.smarteist.imageslider.Data.ApiModel;

public class Item {
     public String title;
     public String sub_title;
     public boolean is_completed;
     public String expire_date;
     public String image;
     public String id;

    public Item(String title, String sub_title, boolean is_completed, String expire_date, String image, String id) {
        this.title = title;
        this.sub_title = sub_title;
        this.is_completed = is_completed;
        this.expire_date = expire_date;
        this.image = image;
        this.id = id;
    }

    public Item() {

    }


    public boolean isIs_completed() {
        return is_completed;
    }

    public void setIs_completed(boolean is_completed) {
        this.is_completed = is_completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }



    public String getExpire_date() {
        return expire_date;
    }

    public String setExpire_date(String expire_date) {
        this.expire_date = expire_date;
        return expire_date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
