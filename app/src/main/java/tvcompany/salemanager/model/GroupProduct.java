package tvcompany.salemanager.model;

/**
 * Created by MtViet on 28/06/2016.
 */
public class GroupProduct {
    private String _id;
    private String groupName_VN;
    private String getGroupName_ENG;

    public GroupProduct() {
    }

    public GroupProduct(String _id, String groupName_VN, String getGroupName_ENG) {
        this._id = _id;
        this.groupName_VN = groupName_VN;
        this.getGroupName_ENG = getGroupName_ENG;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getGetGroupName_ENG() {
        return getGroupName_ENG;
    }

    public void setGetGroupName_ENG(String getGroupName_ENG) {
        this.getGroupName_ENG = getGroupName_ENG;
    }

    public String getGroupName_VN() {
        return groupName_VN;
    }

    public void setGroupName_VN(String groupName_VN) {
        this.groupName_VN = groupName_VN;
    }
}
