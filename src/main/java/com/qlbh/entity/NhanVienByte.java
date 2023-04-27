package com.qlbh.entity;

import java.io.Serializable;
import java.util.List;

public class NhanVienByte implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<NhanVien> employees;

    public List<NhanVien> getOrders(){
        return this.employees;
    }

    public void setOrders(List<NhanVien> employees){
        this.employees = employees;
    }
}
