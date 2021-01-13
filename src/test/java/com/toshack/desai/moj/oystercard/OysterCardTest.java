package com.toshack.desai.moj.oystercard;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.toshack.desai.moj.oystercard.enums.Fare;
import com.toshack.desai.moj.oystercard.enums.Station;

public class OysterCardTest {

	private BigDecimal balance = BigDecimal.valueOf(30.00);
	private OysterCard oysterCard = new OysterCard(1000l);
	
	@Before
	public void beforeTest() {		
		oysterCard.setBalance(balance);	
	}
	
	@Test
	public void touchInBusSuccess() {
		oysterCard.touchInBus();
		assertEquals(balance.subtract(Fare.BUS.getFare()), oysterCard.getBalance());
	}

	@Test
	public void touchInStationSuccess() {
		oysterCard.setTouchInStation(Station.EARLS_COURT);
		assertEquals(balance.subtract(Fare.MAX.getFare()), oysterCard.getBalance());
	}
	
	@Test
	public void touchOutStationSuccess() {
		oysterCard.setTouchInStation(Station.HAMMERSMITH);
		oysterCard.setTouchOutStation(Station.WIMBLEDON);
		assertEquals(balance.subtract(Fare.TWO_ZONES_EXCLUDING_ZONE_ONE.getFare()), oysterCard.getBalance());
	}
}