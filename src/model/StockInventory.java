package model;

public class StockInventory {
    private String productCategory;
    private String productName;
    private int productQuantity;
    private double productPrice;

    public StockInventory(String productCategory, String productName, int productQuantity, double productPrice) {
        this.productCategory = productCategory;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    @Override
    public String toString() {
        return "Stock{" + "itemCategory=" + productCategory + ", item=" + productName + ", itemQuantity=" + productQuantity + ", itemPrice=" + productPrice + '}';
    }

}