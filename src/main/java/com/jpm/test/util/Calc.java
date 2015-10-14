package com.jpm.test.util;

import java.util.Collection;

import com.jmp.test.dto.Stock;

public class Calc {
	
	/*
	 * Utility class for applying various formulae
	 */

	public static double calculateDividendYield(Stock stock, Double price) {
		switch (stock.getType()) {
		case COMMON:
			if (stock.getLastDividend() != null && price != null && price > 0) {
				return stock.getLastDividend() / price;
			}
		case PREFERRED:
			if (stock.getFixedDividendRate() != null
					&& stock.getParValue() != null && price != null && price > 0) {
				return (stock.getFixedDividendRate() * stock.getParValue())
						/ price;
			}
		}

		return 0d;
	}

	public static double calculatePEratio(Stock stock, Double price) {
		switch (stock.getType()) {
		case COMMON:
			if (stock.getLastDividend() != null && stock.getLastDividend() > 0
					&& price != null) {
				return price / stock.getLastDividend();
			}
		case PREFERRED:
			if (stock.getFixedDividendRate() != null
					&& stock.getParValue() != null
					&& (stock.getFixedDividendRate() * stock.getParValue()) > 0 && price != null) {
				return price
						/ (stock.getFixedDividendRate() * stock.getParValue());
			}
		}

		return 0d;
	}

	public static double geometricMean(Collection<Double> prices) {
		double temp = 1;
		for (Double price : prices) {
			temp *= price;
		}

		return Math.pow(temp, 1.0 / prices.size());
	}
}
