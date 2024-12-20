package com.rest.Enum;

public enum SeatModule {
	REGULAR(200),
	PREMIUM(300);

	private final double price;

    SeatModule(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

}
