package com.toshack.desai.moj.oystercard.enums;

import java.math.BigDecimal;

public enum Fare {

	BUS("Bus fare", BigDecimal.valueOf(1.80)), MAX("Maximum fare", BigDecimal.valueOf(3.20)),
	ZONE_ONE_ONLY("Zone one only fare", BigDecimal.valueOf(2.50)),
	ONE_ZONE_OUTSIDE_ZONE_ONE("One zone outside zone one fare", BigDecimal.valueOf(2.00)),
	TWO_ZONES_INCLUDING_ZONE_ONE("Two zones including zone one fare", BigDecimal.valueOf(3.00)),
	TWO_ZONES_EXCLUDING_ZONE_ONE("Two zones excluding zone one fare", BigDecimal.valueOf(2.25));

	private String description;
	private BigDecimal fare;

	private Fare(final String description, final BigDecimal fare) {
		this.description = description;
		this.fare = fare;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getFare() {
		return fare;
	}
	
}