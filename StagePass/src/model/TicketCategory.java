
package model;

public enum TicketCategory {
    DIAMOND(25000),
    VIP(20000),
    PLATINUM(15000),
    GOLD(9000),
    SILVER(4000);

    private final int price;

TicketCategory(int price) {
    this.price = price;
}

public int getPrice() {
    return price;
}
}
