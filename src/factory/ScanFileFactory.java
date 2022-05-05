
package factory;

import scanfile.StockInventoryData;
import scanfile.GetCardData;
import scanfile.GetOrderData;
import scanfile.ScanFile;

/**
 * Factory Design Pattern Implementation
 */
public class ScanFileFactory {

    public ScanFile getInstance(String typeFile){
        if (typeFile.equals("StockInventory")){
            return new StockInventoryData();
        }
        else if(typeFile.equals("CARD")){
            return new GetCardData();
        }

        else if(typeFile.equals("GetOrder")){
            return new GetOrderData();
        }
        else {
            throw new RuntimeException("instance is not present"+ typeFile);
        }
    }
}