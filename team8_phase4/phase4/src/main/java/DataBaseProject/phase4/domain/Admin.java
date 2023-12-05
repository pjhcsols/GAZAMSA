package DataBaseProject.phase4.domain;

public class Admin {

    String id;
    String passWord;
    String emailAddress;
    String phoneNumber;
    Rank rank;


    public Admin(String id, String passWord, String emailAddress, String phoneNumber, Rank rank) {
        this.id = id;
        this.passWord = passWord;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
