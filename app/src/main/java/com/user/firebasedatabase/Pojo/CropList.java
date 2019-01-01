package com.user.firebasedatabase.Pojo;

import java.util.List;

public class CropList {
    public CropList() {
    }

    List<AddCropPojo> cropPojos ;
    List<String> cropKey;

    public CropList(List<AddCropPojo> cropPojos, List<String> cropKey) {
        this.cropPojos = cropPojos;
        this.cropKey = cropKey;
    }

    public List<AddCropPojo> getCropPojos() {
        return cropPojos;
    }

    public void setCropPojos(List<AddCropPojo> cropPojos) {
        this.cropPojos = cropPojos;
    }

    public List<String> getCropKey() {
        return cropKey;
    }

    public void setCropKey(List<String> cropKey) {
        this.cropKey = cropKey;
    }
}
