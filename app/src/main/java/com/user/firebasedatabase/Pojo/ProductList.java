package com.user.firebasedatabase.Pojo;

import java.util.List;

public class ProductList {

    List<ProductAddPojo> cropPojos ;
    List<String> cropKey;

    public List<ProductAddPojo> getCropPojos() {
        return cropPojos;
    }

    public ProductList() {
    }

    public void setCropPojos(List<ProductAddPojo> cropPojos) {
        this.cropPojos = cropPojos;
    }

    public List<String> getCropKey() {
        return cropKey;
    }

    public void setCropKey(List<String> cropKey) {
        this.cropKey = cropKey;
    }

    public ProductList(List<ProductAddPojo> cropPojos, List<String> cropKey) {

        this.cropPojos = cropPojos;
        this.cropKey = cropKey;
    }
}
