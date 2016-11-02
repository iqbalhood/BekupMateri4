package com.cari.gadget.bekup.medan.setget;

/**
 * Created by iqbalhood on 30/10/16.
 */

public class Phone {

    private String title;
    private String image;
    private String slug;


    public Phone(){
        // TODO Auto-generated constructor stub
    }


    public Phone(String title, String image, String slug) {
        super();
        this.title = title;
        this.image = image;
        this.slug = slug;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }








}
