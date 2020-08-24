package az.company.jwt.enums;

public enum Language {

    AZ("AZ"),
    EN("EN"),
    RU("RU"),
    TR("TR");

    private final String value;

    Language(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}