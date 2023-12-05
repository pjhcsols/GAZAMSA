package DataBaseProject.phase4.domain;

public class BrandUser {
    String id;
    String passWord;
    String emailAddress;
    String phoneNumber;
    Rank rank;
    String firmName;
    String firmPhoneNumber;
    String firmAddress;
    String businessRegistration;
    String firmWebUrl;

    public BrandUser(String id, String passWord, String emailAddress, String phoneNumber, Rank rank, String firmName,
            String firmPhoneNumber, String firmAddress, String businessRegistration, String firmWebUrl) {
        this.id = id;
        this.passWord = passWord;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.rank = rank;
        this.firmName = firmName;
        this.firmPhoneNumber = firmPhoneNumber;
        this.firmAddress = firmAddress;
        this.businessRegistration = businessRegistration;
        this.firmWebUrl = firmWebUrl;
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

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getFirmPhoneNumber() {
        return firmPhoneNumber;
    }

    public void setFirmPhoneNumber(String firmPhoneNumber) {
        this.firmPhoneNumber = firmPhoneNumber;
    }

    public String getFirmAddress() {
        return firmAddress;
    }

    public void setFirmAddress(String firmAddress) {
        this.firmAddress = firmAddress;
    }

    public String getBusinessRegistration() {
        return businessRegistration;
    }

    public void setBusinessRegistration(String businessRegistration) {
        this.businessRegistration = businessRegistration;
    }

    public String getFirmWebUrl() {
        return firmWebUrl;
    }

    public void setFirmWebUrl(String firmWebUrl) {
        this.firmWebUrl = firmWebUrl;
    }
}
