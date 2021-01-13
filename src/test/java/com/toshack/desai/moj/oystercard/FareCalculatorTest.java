package com.toshack.desai.moj.oystercard;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import com.toshack.desai.moj.oystercard.enums.Fare;
import com.toshack.desai.moj.oystercard.enums.Station;

public class FareCalculatorTest {

	private OysterCard oysterCard = new OysterCard(1000l);
	private static BigDecimal balance = BigDecimal.valueOf(30.00);
	private static BigDecimal zeroBalance = BigDecimal.valueOf(0.00);
	private static FareCalculator fareCalculator;

	@Before
	public void beforeTest() {		
		oysterCard.setBalance(balance);
		fareCalculator = new FareCalculator();		
	}

	@After
	public void afterTest() {
		oysterCard.setBalance(zeroBalance);
	}

	@Test
	public void twoZonesExcludingZoneOneSuccess() throws Exception {
		BigDecimal result = Whitebox.invokeMethod(fareCalculator, "calculateFareForStationsWithOneZone",
				oysterCard, Station.WIMBLEDON.getZones(), Station.HAMMERSMITH.getZones());
		assertEquals(balance.subtract(Fare.TWO_ZONES_EXCLUDING_ZONE_ONE.getFare()), result);
	}

	@Test
	public void twoZonesIncludingZoneOneSuccess() throws Exception {
		BigDecimal result = Whitebox.invokeMethod(fareCalculator, "calculateFareForStationsWithOneZone",
				oysterCard, Station.HOLBORN.getZones(), Station.HAMMERSMITH.getZones());
		assertEquals(balance.subtract(Fare.TWO_ZONES_INCLUDING_ZONE_ONE.getFare()), result);
	}
	
	@Test
	public void twoZonesExcludingZoneOneMultipleSuccess() throws Exception {
		BigDecimal result = Whitebox.invokeMethod(fareCalculator, "calculateFareForStationsWithMultipleZones",
				oysterCard, Station.WIMBLEDON.getZones(), Station.EARLS_COURT.getZones());
		assertEquals(balance.subtract(Fare.TWO_ZONES_EXCLUDING_ZONE_ONE.getFare()), result);
	}

	@Test
	public void oneZoneOutsideZoneOneSuccess() throws Exception {
		BigDecimal result = Whitebox.invokeMethod(fareCalculator, "calculateFare",
				oysterCard, Station.EARLS_COURT.getZones(), Station.HAMMERSMITH.getZones());
		assertEquals(balance.subtract(Fare.ONE_ZONE_OUTSIDE_ZONE_ONE.getFare()), result);
	}
	
	@Test
	public void zoneOneOnlySuccess() throws Exception {
		BigDecimal result = Whitebox.invokeMethod(fareCalculator, "calculateFare",
				oysterCard, Station.EARLS_COURT.getZones(), Station.HOLBORN.getZones());
		assertEquals(balance.subtract(Fare.ZONE_ONE_ONLY.getFare()), result);
	}
}