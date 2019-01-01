package com.user.firebasedatabase.Pojo;

public class ProductAddPojo {

       String cropname,marketprice,scheduleprice,quantity,totalamount,mobileno,username,imageurl,key,deliveryoption;

    public String getDeliveryoption() {
        return deliveryoption;
    }

    public void setDeliveryoption(String deliveryoption) {
        this.deliveryoption = deliveryoption;
    }

    public ProductAddPojo() {
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCropname() {
        return cropname;
    }

    public void setCropname(String cropname) {
        this.cropname = cropname;
    }

    public String getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(String marketprice) {
        this.marketprice = marketprice;
    }

    public String getScheduleprice() {
        return scheduleprice;
    }

    public void setScheduleprice(String scheduleprice) {
        this.scheduleprice = scheduleprice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ProductAddPojo(String cropname, String marketprice, String scheduleprice, String quantity, String totalamount, String mobileno, String username, String imageurl, String key, String deliveryoption) {
        this.cropname = cropname;
        this.marketprice = marketprice;
        this.scheduleprice = scheduleprice;
        this.quantity = quantity;
        this.totalamount = totalamount;
        this.mobileno = mobileno;
        this.username = username;
        this.imageurl = imageurl;
        this.key = key;
        this.deliveryoption = deliveryoption;
    }
}
