package com.user.firebasedatabase.Pojo;

public class AddCropPojo {

   public String cropname,marketprice,scheduleprice,image;

    public AddCropPojo() {
    }

    public AddCropPojo(String cropname, String marketprice, String scheduleprice, String image) {
        this.cropname = cropname;
        this.marketprice = marketprice;
        this.scheduleprice = scheduleprice;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
