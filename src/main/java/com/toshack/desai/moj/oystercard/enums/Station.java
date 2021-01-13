package com.toshack.desai.moj.oystercard.enums;

public enum Station {

	HOLBORN("Holborn", new int[] { 1 }), EARLS_COURT("Earls Court", new int[] { 1, 2 }),
	HAMMERSMITH("Hammersmith", new int[] { 2 }), WIMBLEDON("Wimbledon", new int[] { 3 });

	private String name;
	private int[] zones;

	Station(String name, int[] zones) {
		this.name = name;
		this.zones = zones;
	}

	public String getName() {
		return name;
	}

	public int[] getZones() {
		return zones;
	}
	
}
