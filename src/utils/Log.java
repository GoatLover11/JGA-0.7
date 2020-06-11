package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Log {
    public static void createLog(){
        try {
            File f = new File("log.txt");

            if(f.delete()) {
                System.out.println("File deleted successfully");
            }
            else {
                System.out.println("Failed to delete the file");
            }

            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void deleteLog(){
        File f = new File("log.txt");
        //......... valami majd ráér
    }

    public static void log(String s){
        try {
            s = s.replace('.', ',');
            RandomAccessFile raf = new RandomAccessFile("log.txt", "rw");
            raf.seek(raf.length());
            raf.writeBytes(s + "\r\n");
            raf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println(e);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e);
        }
    }
}
