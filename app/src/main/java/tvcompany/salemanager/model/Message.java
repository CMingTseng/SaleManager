package tvcompany.salemanager.model;

import java.util.Date;

public class Message {
    private String userSend;
    private String userRecieve;
    private String data;
    private int dataType;
    private long idSort;
    private Date date;
    private int typeAction;

    public Message(){}

    public Message(String userSend, String userRecieve, String data, int dataType, long idSort, Date date,int typeAction) {
        this.userSend = userSend;
        this.userRecieve = userRecieve;
        this.data = data;
        this.dataType = dataType;
        this.idSort = idSort;
        this.date = date;
        this.typeAction = typeAction;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getIdSort() {
        return idSort;
    }

    public void setIdSort(long idSort) {
        this.idSort = idSort;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public int getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(int typeAction) {
        this.typeAction = typeAction;
    }
}
