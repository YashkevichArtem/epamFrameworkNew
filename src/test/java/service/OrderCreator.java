package service;

import model.Order;

public class OrderCreator {
    public static final String PRODUCT_NAME = "product.name";
    public static final String NUMBER_OF_INSTANCES = "number.of.instances";

    public static Order withCredentialsFromProperty() {

        return new Order(TestDataReader.getTestData(PRODUCT_NAME),
                TestDataReader.getTestData(NUMBER_OF_INSTANCES));
    }
}
