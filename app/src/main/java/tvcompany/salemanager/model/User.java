package tvcompany.salemanager.model;

import com.google.gson.annotations.Expose;


public class User {

    @Expose
    private String _id;

    @Expose
    private String data;

    @Expose
    private String date;

    @Expose
    private String userRecieve;

    @Expose
    private String userSend;

    @Expose
    private String idSort;

    @Expose
    private String dataType;

    @Expose
    private String typeAction;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserRecieve() {
        return userRecieve;
    }

    public void setUserRecieve(String userRecieve) {
        this.userRecieve = userRecieve;
    }

    public String getUserSend() {
        return userSend;
    }

    public void setUserSend(String userSend) {
        this.userSend = userSend;
    }

    public String getIdSort() {
        return idSort;
    }

    public void setIdSort(String idSort) {
        this.idSort = idSort;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(String typeAction) {
        this.typeAction = typeAction;
    }
}