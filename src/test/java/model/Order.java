package model;

public class Order {

    private String productName;
    private String numberOfInstances;

    public Order(String productName, String numberOfInstances) {
        this.productName = productName;
        this.numberOfInstances = numberOfInstances;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(String numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    @Override
    public String toString() {
        return "Order{" +
                "productName='" + productName + '\'' +
                ", numberOfInstances='" + numberOfInstances + '\'' +
                '}';
    }

}
