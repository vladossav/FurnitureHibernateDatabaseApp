package org.example;

public enum Columns {
    //Primary keys
    FURNITURE_ID("id"), ADDRESS_ID("id"), CUSTOMER_CODE("code"), CONTRACT_NUM("number"), SALE_ID("id"),
    //furniture_string
    FURNITURE_NAME("name"), FURNITURE_MODEL("model"), FURNITURE_COLOR("color"),
    //furniture_number
    FURNITURE_COST("cost"), FURNITURE_LEN("length"), FURNITURE_WIDTH("width"), FURNITURE_HEIGHT("height"),
    FURNITURE_WEIGHT("weight"),

    ADDRESS_CITY("city"), ADDRESS_STREET("street"),
    CUSTOMER_NAME("name"), CUSTOMER_PHONE("phoneNum");


    Columns(String col) {
        this.column = col;
    }
    private final String column;

    public String getColumnName() {
        return column;
    }
}
