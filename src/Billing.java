
import model.Card;
import model.GetOrder;
import model.StockInventory;
import singleton.ItemCap;
import factory.ScanFileFactory;
import scanfile.ScanFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.HashMap;

public class Billing {

    public static void main(String[] args) throws IOException {
        double totalCost = 0;
        int errorCount=0;
        int cardCount=0;
        int essential=0;
        int luxury=0;
        int misc=0;
        int errorcountfunc=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Input File Path");
        String FileInput = sc.nextLine();
        ScanFileFactory scanfilefactory = new ScanFileFactory();
        ScanFile stockFileRead = scanfilefactory.getInstance("StockInventory");
        ScanFile cardFileRead = scanfilefactory.getInstance("CARD");
        ScanFile orderFileRead = scanfilefactory.getInstance("GetOrder");
        ArrayList<StockInventory> stockInventory= stockFileRead.dataScan("stockinventory.csv");
        ArrayList<Card> cards = cardFileRead.dataScan("Carddetails.csv");
        ArrayList<GetOrder> getOrders = orderFileRead.dataScan(FileInput);
        ArrayList<List<String>> saveOrders = new ArrayList<List<String>>();
        ItemCap capitems = ItemCap.getInstance();
        HashMap<String, Integer> categories = capitems.itemInfo;
        int essencap = categories.get("Essential");

        int luxurycap = categories.get("Luxury");
        int misccap = categories.get("Misc");
        for (GetOrder or : getOrders) {
            for (StockInventory si : stockInventory) {
                if (or.getProductName().equalsIgnoreCase(si.getProductName())) {
                    if (si.getProductCategory().equalsIgnoreCase("essentials") && si.getProductQuantity() >= or.getProductQuantity())
                    {
                        essential =essential+or.getProductQuantity();
                        if(essential <= essencap) {
                            double price = si.getProductPrice() * or.getProductQuantity();
                            if (cardCount == 0) {
                                if (!isCard(cards, or.getCardNumber())) {
                                    writeToCard(or.getCardNumber());
                                    cardCount=cardCount+1;
                                }
                            }
                            totalCost += price;
                            ArrayList<String> orderItem = new ArrayList<String>();
                            orderItem.add(or.getProductName());
                            orderItem.add(Integer.toString(or.getProductQuantity()));
                            orderItem.add(Double.toString(price));
                            saveOrders.add(orderItem);
                            break;
                        }
                        else{
                            if (errorcountfunc==0) {
                                int stockCount = 0;
                                int errors = errorsFunc(stockCount, or.getProductName(), or.getProductQuantity());
                                errorCount = errorCount + errors;
                                errorcountfunc=errorcountfunc+1;
                            }
                        }
                    } else if (si.getProductCategory().equalsIgnoreCase("luxury") && si.getProductQuantity() >= or.getProductQuantity())
                    {
                        luxury=luxury+or.getProductQuantity();
                        if ( luxury <= luxurycap) {
                            double price = si.getProductPrice() * or.getProductQuantity();
                            totalCost += price;
                            if (cardCount == 0) {
                                if (!isCard(cards, or.getCardNumber())) {
                                    writeToCard(or.getCardNumber());
                                    cardCount = cardCount + 1;
                                }
                            }
                            ArrayList<String> orderItem = new ArrayList<String>();
                            orderItem.add(or.getProductName());
                            orderItem.add(Integer.toString(or.getProductQuantity()));
                            orderItem.add(Double.toString(price));
                            saveOrders.add(orderItem);
                            break;
                        }
                        else
                        {
                            if (errorcountfunc==0) {
                                int stockCount = 0;
                                int errors = errorsFunc(stockCount, or.getProductName(), or.getProductQuantity());
                                errorCount = errorCount + errors;
                                errorcountfunc=errorcountfunc+1;
                            }
                        }
                    } else if (si.getProductCategory().equalsIgnoreCase("misc") && si.getProductQuantity() >= or.getProductQuantity())
                    {
                        misc=misc+or.getProductQuantity();
                            if(misc <= misccap) {
                        double price = si.getProductPrice() * or.getProductQuantity();
                        totalCost+= price;
                                if (cardCount == 0) {
                                    if (!isCard(cards, or.getCardNumber())) {
                                        writeToCard(or.getCardNumber());
                                    }
                                }
                                ArrayList<String> orderItem = new ArrayList<String>();
                                orderItem.add(or.getProductName());
                                orderItem.add(Integer.toString(or.getProductQuantity()));
                                orderItem.add(Double.toString(price));
                                saveOrders.add(orderItem);
                        break;
                            }
                            else
                            {
                                if (errorcountfunc==0) {
                                    int stockCount = 0;
                                    int errors = errorsFunc(stockCount, or.getProductName(), or.getProductQuantity());
                                    errorCount = errorCount + errors;
                                    errorcountfunc=errorcountfunc+1;
                                }
                            }
                    } else {
                        int stockCount=1;
                        int errors= errorsFunc(stockCount,or.getProductName(),or.getProductQuantity());
                        errorCount = errorCount+errors;
                    }
                }
            }

        }
        if (totalCost > 0 && errorCount==0 ) {
            writeToFile(totalCost,saveOrders);
        }
    }
    public static int errorsFunc(int stockCount,String productName,int productQuantity)
    {
    int errorinc =0;
        System.err.println(" Incorrect quantity.. writing to errorlog.txt");
        if (stockCount == 1)
        {
            String log = "Items are not present in stock\n";
            errorLog(log);
        }
        else{
            String log = "Cap limit exceeds on the few of the item categories ,Please check the quantities added to the cart\n";
            errorLog(log);
        }
        errorinc+=1;
        return errorinc;
    }
    public static void errorLog(String content) {
        FileWriter writer = null;
        try {
            File file = new File("errorlog.txt");
            writer = new FileWriter(file, true);
            BufferedWriter out = new BufferedWriter(writer);
            out.write(content + "\n");
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void writeToCard(String cardNumber) throws IOException {
        FileWriter pw = new FileWriter("carddetails.csv", true);
        pw.append(cardNumber + ",");
        pw.append("\n");
        pw.flush();
        pw.close();
    }

    private static boolean isCard(ArrayList<Card> cards, String cardNumber) {
        boolean status = false;
        for (Card c : cards) {
            if (c.getCardNumber().equals(cardNumber)) {
                status = true;
                break;
            }
        }

        return status;
    }

    public static void writeToFile(double cost,ArrayList<List<String>> saveOrders) throws IOException {
        System.out.println("Amount Paid " + cost);
        FileWriter pw = new FileWriter("Output-File.csv");
        pw.append("Items");
        pw.append(",");
        pw.append("Quantity");
        pw.append(",");
        pw.append("Price");
        pw.append(",");
        pw.append("Amt Paid");
        pw.append("\n");
        for (int m =0 ; m< saveOrders.size();m++) {
            for (int n = 0; n < saveOrders.get(m).size(); n++) {
                pw.append(saveOrders.get(m).get(n));
                pw.append(",");
            }
            if (m == 0) {
                pw.append(cost + "rs");
            }
            pw.append("\n");
        }
        pw.flush();
        pw.close();
    }

}
