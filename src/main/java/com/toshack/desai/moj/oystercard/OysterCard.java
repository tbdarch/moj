package com.toshack.desai.moj.oystercard;


import java.math.BigDecimal;

import com.toshack.desai.moj.oystercard.enums.Fare;
import com.toshack.desai.moj.oystercard.enums.Station;

public class OysterCard {
	
	private BigDecimal balance = BigDecimal.valueOf(0);
	private long id;
	private Station touchInStation;
	private Station touchOutStation;
	private FareCalculator fareCalculator = new FareCalculator();
	
	
	public OysterCard(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public void touchInBus() {
		this.setBalance(getBalance().subtract(Fare.BUS.getFare()));
	}

	public Station getTouchInStation() {
		return touchInStation;
	}

	public void setTouchInStation(Station touchInStation) {
		this.touchInStation = touchInStation;	
		this.setBalance(getBalance().subtract(Fare.MAX.getFare()));
	}

	public Station getTouchOutStation() {
		return touchOutStation;
	}

	public void setTouchOutStation(Station touchOutStation) {	
		this.setBalance(getBalance().add(Fare.MAX.getFare()));
		final int[] inZones = touchInStation.getZones();
		final int[] outZones = touchOutStation.getZones();
		this.setBalance(fareCalculator.calculateFare(this, inZones, outZones));
		this.touchInStation = null;
		this.touchOutStation = null;
	}

}

