package scanfile;

import model.StockInventory;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.HashMap;

public class StockInventoryData implements ScanFile<StockInventory> {

    public ArrayList<StockInventory> dataScan(String filePath) {

        ArrayList<StockInventory> inventoryData = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine().replace("\"", "");
                String[] lineData = line.split(",");
                StockInventory stockInventory = new StockInventory(lineData[1].trim(), lineData[0].trim(),
                        Integer.parseInt(lineData[2].trim()), Double.parseDouble(lineData[3].trim()));
                inventoryData.add(stockInventory);
            }

        } catch (FileNotFoundException ex) {
            System.err.println("stockInventory Error: " + ex.getMessage());
        }
        return inventoryData;
    }
}