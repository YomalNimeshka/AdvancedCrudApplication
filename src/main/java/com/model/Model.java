package com.model;

public class Model {
    private int id;
    private String userName;
    private String nic;
    private String mobileNumber;
    private String gender;
    private String password;

    public Model() {
    }

    public Model(int id) {
        this.id = id;
    }

    public Model(int id, String userName, String nic, String mobileNumber, String gender) {
        this.id = id;
        this.userName = userName;
        this.nic = nic;
        this.mobileNumber = mobileNumber;
        this.gender = gender;

    }

    public Model(String userName, String nic, String mobileNumber, String gender, String password) {
        this.userName = userName;
        this.nic = nic;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.password = password;
    }

    public Model(String userName, String nic, String mobileNumber, String gender) {
        this.userName = userName;
        this.nic = nic;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", nic='" + nic + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
