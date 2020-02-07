package com.digitalorder.np.model;

public class Users {
   // private String _id;
    private String fullname;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String image;
    private String gender;

    public Users(String fullname, String name, String email, String phone, String password, String image, String gender) {
        this.fullname = fullname;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.image = image;
        this.gender= gender;

    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

//    public String get_id() {
//        return _id;
//    }
//
//    public void set_id(String _id) {
//        this._id = _id;
//    }
}
