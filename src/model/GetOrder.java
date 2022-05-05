package model;
public class GetOrder {
    // instance variables
    private String productName;
    private int productQuantity;
    private String cardNumber;

    public GetOrder(String productName, int productQuantity, String cardNumber) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.cardNumber = cardNumber;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return  productName + ", " + productQuantity + ", " + cardNumber;
    }

}