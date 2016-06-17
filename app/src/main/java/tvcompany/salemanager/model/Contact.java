package tvcompany.salemanager.model;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 15/06/2016.
 */
public class Contact {
    String s_name;
    String s_fullName;
    String s_Phone;
    Bitmap i_image;

    public Bitmap getI_image() {
        return i_image;
    }

    public void setI_image(Bitmap i_image) {
        this.i_image = i_image;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_fullName() {
        return s_fullName;
    }

    public void setS_fullName(String s_fullName) {
        this.s_fullName = s_fullName;
    }

    public String getS_Phone() {
        return s_Phone;
    }

    public void setS_Phone(String s_Phone) {
        this.s_Phone = s_Phone;
    }

    public Contact(String s_name, String s_fullName, String s_Phone, Bitmap i_image) {
        this.s_name = s_name;
        this.s_fullName = s_fullName;
        this.s_Phone = s_Phone;
        this.i_image = i_image;
    }
    public Contact(){

    }
}
