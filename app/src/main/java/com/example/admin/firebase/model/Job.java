package com.example.admin.firebase.model;

public class Job {
    private String user_id;
    private long phone_number;
    private String address;
    private String job;
    private String username;

    public Job(String user_id, String username, String job, String address, long phone_number) {
        this.user_id = user_id;
        this.phone_number = phone_number;
        this.address=address;
        this.job = job;
        this.username = username;
    }

    public Job() {

    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String getjob() {
        return job;
    }

    public void setjob(String job) {
        this.job = job;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", job='"+ job+ '\''+
                ", address='" + address+ '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }
}

