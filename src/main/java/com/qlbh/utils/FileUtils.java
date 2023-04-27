package com.qlbh.utils;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class FileUtils {
    public static void writetoFile(String fileName, Object object) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readFile(String fileName) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        }
        catch (EOFException e) {
            System.out.println("The file is empty");
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}