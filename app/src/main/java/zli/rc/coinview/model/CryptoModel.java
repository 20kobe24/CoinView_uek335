package zli.rc.coinview.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CryptoModel {
    private String name;
    private String symbol;
    private double price;

}
