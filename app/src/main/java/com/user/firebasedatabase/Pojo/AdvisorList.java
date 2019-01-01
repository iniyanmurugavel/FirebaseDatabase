package com.user.firebasedatabase.Pojo;

import java.util.List;

public class AdvisorList {
    List<AdvisorPojo> cropPojos ;
    List<String> cropKey;

    public AdvisorList() {
    }

    public AdvisorList(List<AdvisorPojo> cropPojos, List<String> cropKey) {

        this.cropPojos = cropPojos;
        this.cropKey = cropKey;
    }


    public List<AdvisorPojo> getCropPojos() {
        return cropPojos;
    }

    public void setCropPojos(List<AdvisorPojo> cropPojos) {
        this.cropPojos = cropPojos;
    }

    public List<String> getCropKey() {
        return cropKey;
    }

    public void setCropKey(List<String> cropKey) {
        this.cropKey = cropKey;
    }
}
