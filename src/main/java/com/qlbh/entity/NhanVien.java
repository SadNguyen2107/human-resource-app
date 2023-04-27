package com.qlbh.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class NhanVien implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private int gender;
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
    private ChucVu position;
    private byte[] pictureData;

    public NhanVien(){

    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public String getGender(){
        if(gender == 0){
            return "Nam";
        }
        else{
            return "Nữ";
        }
    }

    public int getGender2(){
        return this.gender;
    }

    public void setGender(int gender){
        this.gender = gender;
    }

    public LocalDate getBirthDate(){
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate){
        this.birthDate = birthDate;
    }

    public byte[] getPictureData(){
        return this.pictureData;
    }

    public void setPictureData(byte[] pictureData){
        this.pictureData = pictureData;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public ChucVu getPosition(){
        return position;
    }

    public void setPosition(ChucVu position){
        this.position = position;
    }
}
