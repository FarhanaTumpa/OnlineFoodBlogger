package com.example.user.onlinefoodbloggers;

public class FoodItemInfo {
    private  String Name  ;
    private  String Price ;
    private  String ImageLink ;


    public FoodItemInfo(){
        this.Name = "" ;
        this.Price = "" ;
        ImageLink = "";
    }
    public FoodItemInfo(String name, String price ,String ImageLink) {
        this.Name = name;
        Price = price;
        this.ImageLink = ImageLink;

    }

    public String getImageLink() {
        return ImageLink;
    }

    public void setImageLink(String imageLink) {
        ImageLink = imageLink;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }


}
