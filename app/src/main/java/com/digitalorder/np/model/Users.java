package com.digitalorder.np.model;

public class Users {
    private String name;
    private String password;
    private String email;
    private String image;
    private String gender;

    public Users(String name, String email, String password, String image, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.image = image;
        this.gender= gender;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
