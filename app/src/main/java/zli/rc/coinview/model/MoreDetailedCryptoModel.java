package zli.rc.coinview.model;

public class MoreDetailedCryptoModel {
    private String name;
    private String symbol;
    private double price;
    private double priceChange1h;
    private double priceChange3h;
    private double priceChange24h;
    private double priceChange7d;
    private double priceChange30d;


    public MoreDetailedCryptoModel(String name, String symbol, double price, double priceChange1h, double priceChange3h, double priceChange24h, double priceChange7d, double priceChange30d) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.priceChange1h = priceChange1h;
        this.priceChange3h = priceChange3h;
        this.priceChange24h = priceChange24h;
        this.priceChange7d = priceChange7d;
        this.priceChange30d = priceChange30d;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceChange1h() {
        return priceChange1h;
    }

    public void setPriceChange1h(double priceChange1h) {
        this.priceChange1h = priceChange1h;
    }

    public double getPriceChange3h() {
        return priceChange3h;
    }

    public void setPriceChange3h(double priceChange3h) {
        this.priceChange3h = priceChange3h;
    }

    public double getPriceChange24h() {
        return priceChange24h;
    }

    public void setPriceChange24h(double priceChange24h) {
        this.priceChange24h = priceChange24h;
    }

    public double getPriceChange7d() {
        return priceChange7d;
    }

    public void setPriceChange7d(double priceChange7d) {
        this.priceChange7d = priceChange7d;
    }

    public double getPriceChange30d() {
        return priceChange30d;
    }

    public void setPriceChange30d(double priceChange30d) {
        this.priceChange30d = priceChange30d;
    }
}
