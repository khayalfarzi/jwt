package az.company.jwt.enums;

public enum Url {

    PUMA("https://us.puma.com/"),
    PUMA_SEARCH("https://us.puma.com/en/us/search?q="),
    ADIDAS("https://www.adidas.com/"),
    NIKE("https://www.nike.com/tr/"),
    NIKE_SEARCH("https://www.nike.com/tr/w?q="),
    UCB("https://tr.benetton.com/"),
    HUMMEL("https://hummel.com.tr/"),
    HUMMEL_SEARCH("https://hummel.com.tr/Ara?k="),
    DEFACTO("https://www.defacto.com.tr/"),
    DEFACTO_SEARCH("https://www.defacto.com.tr/arama?q="),
    LC_WAIKIKI("https://www.lcwaikiki.com/"),
    LC_WAIKIKI_SEARCH("https://www.lcwaikiki.com/tr-TR/TR/arama?q="),
    LEVIS("https://www.levis.com.tr/"),
    LEVIS_SEARCH("https://www.levis.com.tr/Ara?k="),
    ZARA_SEARCH("https://www.zara.com/az/en/search?searchTerm="),
    NETWORK_SEARCH("https://www.network.com.tr/search?searchKey="),
    NETWORK("https://www.network.com.tr/"),
    MAVI_SEARCH("https://www.mavi.com/search/?text="),
    DERIMOD("https://www.derimod.com.tr/"),
    DERIMOD_SEARCH("https://www.derimod.com.tr/list/?search_text="),
    GIZIA("https://www.gizia.com/"),
    GIZIA_SEARCH("https://www.gizia.com/arama?q="),
    BOGGI_SEARCH("https://www.boggi.com/en_US/search?q="),
    GANT_SEARCH("https://www.gant.com.tr/list/?search_text="),
    GANT("https://www.gant.com.tr/"),
    TOM_TAILOR_SEARCH("https://www.tom-tailor.eu/search?q="),
    TOM_TAILOR("https://www.tom-tailor.eu"),
    KOTON_SEARCH("https://www.koton.com/tr/search/?text="),
    HM_SEARCH("https://www2.hm.com/tr_tr/search-results.html?q="),
    HM("https://www2.hm.com/"),
    VAKKO_SEARCH("https://www.vakko.com/list/?attributes_integration_is_outlet=False&search_text=");

    private final String value;

    Url(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
