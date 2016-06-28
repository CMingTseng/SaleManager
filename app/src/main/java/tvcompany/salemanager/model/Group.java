package tvcompany.salemanager.model;

import java.util.ArrayList;

/**
 * Created by Administrator on 6/26/2016.
 */
public class Group {
    public int image;
    public String text1;
    public String text2;
    public String text3;
    ArrayList<Child> Item;

    public ArrayList<Child> getItem() {
        return Item;
    }

    public void setItem(ArrayList<Child> item) {
        Item = item;
    }

    public Group(int image, String text1, String text2, String text3, ArrayList<Child> item) {
        this.image = image;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        Item = item;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }
}
