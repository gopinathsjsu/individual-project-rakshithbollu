package scanfile;


import java.util.ArrayList;

public interface ScanFile<T> {

    public  ArrayList<T> dataScan(String filePath);
}