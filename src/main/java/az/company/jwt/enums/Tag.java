package az.company.jwt.enums;

public enum Tag {

    SCRIPT_JSON("script[type=\"application/ld+json\"]"),
    ANALYTICS_DIV("div[class=\"analytics-data\"]");

    private final String value;

    Tag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}