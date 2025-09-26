package core.basesyntax;

public class MarketData {
    private int supplyTotal = 0;
    private int buyTotal = 0;

    public int getBuyTotal() {
        return buyTotal;
    }

    public void addBuyTotal(int buy) {
        this.buyTotal += buy;
    }

    public int getSupplyTotal() {
        return supplyTotal;
    }

    public void addSupplyTotal(int supply) {
        this.supplyTotal += supply;
    }

    public int getResult() {
        return supplyTotal - buyTotal;
    }
}
