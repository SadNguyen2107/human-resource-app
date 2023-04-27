package com.qlbh.entity;

import java.io.Serializable;

public class ChucVu implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private Double salaryMultiplier;

    public ChucVu(){

    }

    @Override
    public String toString(){
        return this.name;
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

    public double getSalaryM(){
        return this.salaryMultiplier;
    }

    public void setSalaryM(double salaryMultiplier){
        this.salaryMultiplier = salaryMultiplier;
    }
}
