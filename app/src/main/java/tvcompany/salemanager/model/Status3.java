package tvcompany.salemanager.model;

import java.util.List;

/**
 * Created by vietp on 04/09/2016.
 */
public class Status3 {
    private int code;
    private List<Book> book;

    public Status3(int code, List<Book> book) {
        this.code = code;
        this.book = book;
    }

    public Status3() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }
}
