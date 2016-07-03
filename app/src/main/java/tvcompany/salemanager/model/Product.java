package tvcompany.salemanager.model;


import java.io.Serializable;

public class Product implements Serializable{
    private String _id;
    private String ID;
    private String productName;
    private String createDate;
    private double moneyPurchase;
    private double moneyOrder;
    private String[] groupProduct;
    private String note;
    private String[] shopId;
    private String image;

    public Product() {
    }

    public Product(String _id,String ID, String productName, String createDate, double moneyPurchase, double moneyOrder, String[] groupProduct, String note, String[] shopId, String image) {
        this._id = _id;
        this.ID = ID;
        this.productName = productName;
        this.createDate = createDate;
        this.moneyPurchase = moneyPurchase;
        this.moneyOrder = moneyOrder;
        this.groupProduct = groupProduct;
        this.note = note;
        this.shopId = shopId;
        this.image = image;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public double getMoneyPurchase() {
        return moneyPurchase;
    }

    public void setMoneyPurchase(double moneyPurchase) {
        this.moneyPurchase = moneyPurchase;
    }

    public double getMoneyOrder() {
        return moneyOrder;
    }

    public void setMoneyOrder(double moneyOrder) {
        this.moneyOrder = moneyOrder;
    }

    public String[] getGroupProduct() {
        return groupProduct;
    }

    public void setGroupProduct(String[] groupProduct) {
        this.groupProduct = groupProduct;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String[] getShopId() {
        return shopId;
    }

    public void setShopId(String[] shopId) {
        this.shopId = shopId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
