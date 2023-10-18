package com.yxr.pushbasic.config;

public enum Manufacturer {
    XIAOMI("xiaomi"),
    HUAWEI("huawei"),
    OPPO("oppo"),
    VIVO("vivo"),
    HONOR("honor"),
    MEIZU("meizu"),
    ;
    private String manufacturer;

    Manufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
