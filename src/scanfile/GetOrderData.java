package scanfile;

import model.GetOrder;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.HashMap;

public class GetOrderData implements ScanFile<GetOrder> {

    public ArrayList<GetOrder> dataScan(String filePath) {

        ArrayList<GetOrder> data = new ArrayList<>();

        try {
            int orderCount=0;
            String cardnumber ="12345";
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine().replace("\"", "");
                String[] lineData = line.split(",");
                if(orderCount<=0) {
                    GetOrder order = new GetOrder(lineData[0], Integer.parseInt(lineData[1]), lineData[2]);
                    orderCount=orderCount+1;
                    cardnumber = lineData[2];
                    data.add(order);
                }
                else{
                    GetOrder order = new GetOrder(lineData[0], Integer.parseInt(lineData[1]), cardnumber);
                    data.add(order);
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("input Error: " + ex.getMessage());
        }

        return data;
    }

}