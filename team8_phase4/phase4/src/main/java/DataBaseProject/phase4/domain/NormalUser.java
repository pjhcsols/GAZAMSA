package DataBaseProject.phase4.domain;

public class NormalUser {
    String id;
    String passWord;
    String emailAddress;
    String phoneNumber;
    Rank rank;
    String name;
    Long age;
    String address;

    public NormalUser(String id, String passWord, String emailAddress, String phoneNumber, Rank rank, String name,
            Long age,
            String address) {
        this.id = id;
        this.passWord = passWord;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.rank = rank;
        this.name = name;
        this.age = age;
        this.address = address;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
