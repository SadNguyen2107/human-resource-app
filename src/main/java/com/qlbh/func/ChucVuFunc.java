package com.qlbh.func;

import java.util.ArrayList;
import java.util.List;

import com.qlbh.entity.ChucVu;
import com.qlbh.entity.ChucVuByte;
import com.qlbh.utils.FileUtils;

public class ChucVuFunc {
    private static final String ORDER_FILE_NAME = "chucvu.ser";
    private List<ChucVu> positions;

    public ChucVuFunc(){
        this.positions = readListOrders();
    }

    public List<ChucVu> getList(){
        return this.positions;
    }

    public void writeListProducts(List<ChucVu> list) {
        ChucVuByte positionBinary = new ChucVuByte();
        positionBinary.setOrders(list);
        FileUtils.writetoFile(ORDER_FILE_NAME, positionBinary);
    }

    public List<ChucVu> readListOrders() {
        List<ChucVu> list = new ArrayList<ChucVu>();
        ChucVuByte positionBinary = (ChucVuByte) FileUtils.readFile(ORDER_FILE_NAME);
        if (positionBinary != null) {
            list = positionBinary.getOrders();
        }
        return list;
    }

    public boolean generateId(int id){
        int size = positions.size();
        for (int i = 0; i < size; i++) {
            if (positions.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void add(ChucVu position) {
        int id = (positions.size() > 0) ? (positions.size() + 1) : 1;

        while(generateId(id)){
            id--;
        }
        
        position.setId(id);
        positions.add(position);
        writeListProducts(positions);
    }

    public void edit(ChucVu position) {
        int size = positions.size();
        for (int i = 0; i < size; i++) {
            if (positions.get(i).getId() == position.getId()) {
                positions.get(i).setName(position.getName());
                positions.get(i).setSalaryM(position.getSalaryM());
                writeListProducts(positions);
                break;
            }
        }
    }

    public boolean delete(ChucVu position) {
        boolean isFound = false;
        int size = positions.size();
        for (int i = 0; i < size; i++) {
            if (positions.get(i).getId() == position.getId()) {
                position = positions.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            positions.remove(position);
            writeListProducts(positions);
            return true;
        }
        return false;
    }
}
