package az.company.jwt.enums;

public enum Exception {

    EMPTY_SEARCH_KEY("Search key is null."),
    NO_PRODUCT_FOUNDED("Product not founded with search key : ");

    private final String value;

    Exception(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
