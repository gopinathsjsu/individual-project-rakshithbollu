package scanfile;

import model.Card;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.HashMap;

public class GetCardData implements ScanFile<Card>{

    public ArrayList<Card> dataScan(String filePath) {

        ArrayList<Card> data = new ArrayList<>();
        File file = new File(filePath);
        try {
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] lineData = sc.nextLine().split(",");
                Card card = new Card(lineData[0]);
                data.add(card);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Card Error: " + ex.getMessage());
        }

        return data;
    }
}