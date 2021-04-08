package Model;

import java.io.Serializable;

/**
 * Every reader must have an unique readers card to be able to use libary
 */
public class ReadersCard implements Serializable {
    private String readerName;
    private String readerAddress;
    private String readerPhoneNumber;
    private String readerEmail;
    private int id;

    public ReadersCard(String readerName, String readerAddress,
                       String readerPhoneNumber, String readerEmail, int id) {
        this.readerName = readerName;
        this.readerAddress = readerAddress;
        this.readerPhoneNumber = readerPhoneNumber;
        this.readerEmail = readerEmail;
        this.id = id;               //id should be generated
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getReaderAddress() {
        return readerAddress;
    }

    public void setReaderAddress(String readerAddress) {
        this.readerAddress = readerAddress;
    }

    public String getReaderPhoneNumber() {
        return readerPhoneNumber;
    }

    public void setReaderPhoneNumber(String readerPhoneNumber) {
        this.readerPhoneNumber = readerPhoneNumber;
    }

    public String getReaderEmail() {
        return readerEmail;
    }

    public void setReaderEmail(String readerEmail) {
        this.readerEmail = readerEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
