package tvcompany.salemanager.model;

import java.util.Date;

/**
 * Created by Administrator on 26/06/2016.
 */
public class Shop {
    private String ID;
    private String shopName;
    private String createDate;
    private double longitude;
    private double latitude;
    private String address;
    private String manager;
    private String note;
    private String image;
    private boolean valid;
    public Shop() {
    }

    public Shop(String ID, String shopName, String createDate, double longitude, double latitude, String address, String manager, String note, String image,boolean valid) {
        this.ID = ID;
        this.shopName = shopName;
        this.createDate = createDate;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.manager = manager;
        this.note = note;
        this.image = image;
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getId() {
        return ID;
    }

    public void setId(String id) {
        this.ID = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
