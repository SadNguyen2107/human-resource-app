package com.qlbh.entity;

import java.io.Serializable;
import java.util.List;

public class ChucVuByte implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<ChucVu> positions;

    public List<ChucVu> getOrders(){
        return this.positions;
    }

    public void setOrders(List<ChucVu> positions){
        this.positions = positions;
    }
}
