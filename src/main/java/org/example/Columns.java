package org.example;

public enum Columns {
    //Primary keys
    FURNITURE_ID("id"), ADDRESS_ID("id"), CUSTOMER_CODE("code"), CONTRACT_NUM("number"), SALE_ID("id"),
    //furniture_string
    FURNITURE_NAME("name"), FURNITURE_MODEL("model"), FURNITURE_COLOR("color"),
    //furniture_number
    FURNITURE_COST("cost"), FURNITURE_LEN("length"), FURNITURE_WIDTH("width"), FURNITURE_HEIGHT("height"),
    FURNITURE_WEIGHT("weight"),
    //customer
    CUSTOMER_NAME("name"), CUSTOMER_PHONE("phoneNum"), ADDRESS_CITY("city"), ADDRESS_STREET("street"),
    //contract
    CONTRACT_CUSTOMER_CODE("customerCode"), CONTRACT_REG_DATE("regDate"),CONTRACT_DONE_DATE("doneDate"),
    //sale
    SALE_CONTRACT_NUM("contractNum"), SALE_AMOUNT("amount");
    Columns(String col) {
        this.column = col;
    }
    private final String column;

    public String getColumnName() {
        return column;
    }
}
