package com.qlbh.func;

import java.util.ArrayList;
import java.util.List;

import com.qlbh.entity.ChucVu;
import com.qlbh.entity.NhanVien;
import com.qlbh.entity.NhanVienByte;
import com.qlbh.utils.FileUtils;

public class NhanVienFunc {
    private static final String ORDER_FILE_NAME = "nhanvien.ser";
    private List<NhanVien> employees;
    private List<NhanVien> filteredList;

    public NhanVienFunc(){
        this.employees = readListOrders();
        this.filteredList = new ArrayList<NhanVien>();
    }

    public void writeListProducts(List<NhanVien> list) {
        NhanVienByte orderBinary = new NhanVienByte();
        orderBinary.setOrders(list);
        FileUtils.writetoFile(ORDER_FILE_NAME, orderBinary);
    }

    public List<NhanVien> readListOrders() {
        List<NhanVien> list = new ArrayList<NhanVien>();
        NhanVienByte orderBinary = (NhanVienByte) FileUtils.readFile(ORDER_FILE_NAME);
        if (orderBinary != null) {
            list = orderBinary.getOrders();
        }
        return list;
    }

    public List<NhanVien> getList(){
        return this.employees;
    }

    public List<NhanVien> getFilteredList(){
        return this.filteredList;
    }

    public boolean generateId(int id){
        int size = employees.size();
        for (int i = 0; i < size; i++) {
            if (employees.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void add(NhanVien employee) {
        int id = (employees.size() > 0) ? (employees.size() + 1) : 1;

        while(generateId(id)){
            id--;
        }
        
        employee.setId(id);
        employees.add(employee);
        writeListProducts(employees);
    }

    public void edit(NhanVien employee) {
        int size = employees.size();
        for (int i = 0; i < size; i++) {
            if (employees.get(i).getId() == employee.getId()) {
                employees.get(i).setName(employee.getName());
                employees.get(i).setGender(employee.getGender2());
                employees.get(i).setBirthDate(employee.getBirthDate());
                employees.get(i).setAddress(employee.getAddress());
                employees.get(i).setPhoneNumber(employee.getPhoneNumber());
                employees.get(i).setPosition(employee.getPosition());
                employees.get(i).setPictureData(employee.getPictureData());
                writeListProducts(employees);
                break;
            }
        }
    }

    public void followEdit(ChucVu editedPosition){
        int size = employees.size();
        for(int i = 0; i < size; i++){
            ChucVu position = employees.get(i).getPosition();
            if(position.getId() == editedPosition.getId()){
                position.setName(editedPosition.getName());
                position.setSalaryM(editedPosition.getSalaryM());
            }
        }
    }

    public boolean delete(NhanVien employee) {
        boolean isFound = false;
        int size = employees.size();
        for (int i = 0; i < size; i++) {
            if (employees.get(i).getId() == employee.getId()) {
                employee = employees.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            employees.remove(employee);
            writeListProducts(employees);
            return true;
        }
        return false;
    }

    public void followDelete(int id){
        int size = employees.size();
        for(int i = 0; i < size; i++){
            ChucVu position = employees.get(i).getPosition();
            if(position.getId() == id){
                position = null;
            }
        }
    }

    public void filter(double filter){
        this.filteredList.clear();
        int size = this.employees.size();
        for(int i = 0; i < size; i++){
            NhanVien employee = employees.get(i);
            if(employee.getPosition().getSalaryM() > filter){
                filteredList.add(employee);
            }
        }
    }
}
