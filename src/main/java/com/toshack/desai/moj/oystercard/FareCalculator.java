package com.toshack.desai.moj.oystercard;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.toshack.desai.moj.oystercard.enums.Fare;

public class FareCalculator {

	 BigDecimal calculateFare(final OysterCard oysterCard, final int[] inZones, final int[] outZones) {
		final Set<Integer> items = new HashSet<>();
		final Set<Integer> commonZone = IntStream.concat(IntStream.of(inZones), IntStream.of(outZones)).boxed()
				.filter(n -> !items.add(n)).collect(Collectors.toSet());
		if (!commonZone.isEmpty()) {
			if (commonZone.contains(1)) {
				return oysterCard.getBalance().subtract(Fare.ZONE_ONE_ONLY.getFare());
			} else {
				return oysterCard.getBalance().subtract(Fare.ONE_ZONE_OUTSIDE_ZONE_ONE.getFare());
			}
		} else if (inZones.length == 1 && outZones.length == 1) {
			return calculateFareForStationsWithOneZone(oysterCard, inZones, outZones);
		} else {
			return calculateFareForStationsWithMultipleZones(oysterCard, inZones, outZones);
		}
	}

	private BigDecimal calculateFareForStationsWithOneZone(final OysterCard oysterCard, final int[] inZones, final int[] outZones) {
		final int zoneIn = inZones[0];
		final int zoneOut = outZones[0];
		if (zoneIn - zoneOut == 0) { // Same zone fares
			if (zoneIn == 1) { // Zone one only fare
				return oysterCard.getBalance().subtract(Fare.ZONE_ONE_ONLY.getFare());
			} else {
				return oysterCard.getBalance().subtract(Fare.ONE_ZONE_OUTSIDE_ZONE_ONE.getFare());
			}
		} else {
			int numberOfZones = Math.abs(inZones[inZones.length - 1] - outZones[outZones.length - 1]) + 1;
			if (numberOfZones == 2) {
				if (zoneIn == 1 || zoneOut == 1) {
					return oysterCard.getBalance().subtract(Fare.TWO_ZONES_INCLUDING_ZONE_ONE.getFare());
				} else {
					return oysterCard.getBalance().subtract(Fare.TWO_ZONES_EXCLUDING_ZONE_ONE.getFare());
				}
			} else {
				return oysterCard.getBalance().subtract(Fare.MAX.getFare());
			}
		}
	}

	private BigDecimal calculateFareForStationsWithMultipleZones(final OysterCard oysterCard, final int[] inZones, final int[] outZones) {
		final int numberOfZones = Math.abs(inZones[inZones.length - 1] - outZones[outZones.length - 1]) + 1;
		if (numberOfZones == 2) {
			// Zone 1 and 2
			if (inZones[inZones.length - 1] == 1 || outZones[outZones.length - 1] == 1) {
				return oysterCard.getBalance().subtract(Fare.TWO_ZONES_INCLUDING_ZONE_ONE.getFare());
			} else { // Zone 2 and 3
				return oysterCard.getBalance().subtract(Fare.TWO_ZONES_EXCLUDING_ZONE_ONE.getFare());
			}
		} else {// 3 Zones
			return oysterCard.getBalance().subtract(Fare.MAX.getFare());
		}
	}

}
