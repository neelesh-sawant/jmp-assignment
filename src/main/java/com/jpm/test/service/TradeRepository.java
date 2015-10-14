package com.jpm.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jmp.test.dto.StockTrade;
import com.jpm.test.util.Calc;

public class TradeRepository {
	
	/*
	 * Service for 
	 * 1) capturing Trades
	 * 2) computing Volume Weighted Price for a given stock symbol
	 * 3) computing GBCE All Share Index Price
	 */

	private static final long FIFTEEN_MINS = 15 * 60 * 1000;

	private List<StockTrade> tradesStore = new ArrayList<StockTrade>();

	private Map<String, Double> lastTradedPriceMap = new HashMap<String, Double>();

	public void captureTrade(StockTrade trade) {
		validate(trade);
		
		tradesStore.add(trade);
		
		/*
		 * Assumption: 
		 * trades are submitted in order of their execution timestamp, 
		 * thus the lastTradedPriceMap would always contain the latest price for a given symbol 
		 */
		lastTradedPriceMap.put(trade.getStock().getSymbol(),
				trade.getTradedPrice());
	}

	private void validate(StockTrade trade) {
		/*
		 * TODO: Throw a proper business exception instead of AssertionError
		 * */
		if (null == trade) {
			throw new AssertionError("Null trade supplied");
		}

		if (null == trade.getStock()) {
			throw new AssertionError("Null Stock supplied");
		}

		if (null == trade.getTradedPrice()) {
			throw new AssertionError("Null Trade Price supplied");
		}

		if (trade.getQuantity() <= 0) {
			throw new AssertionError("Trade Quantity must be greater than zero");
		}
	}

	public Double getVolumeWeightedPrice(String symbol) {

		double tradedPricexQty = 0;
		long qty = 0;

		for (StockTrade trade : tradesStore) {
			if (trade.getStock().getSymbol().equals(symbol)
					&& trade.getTimestamp()
							.after(new Date(System.currentTimeMillis()
									- FIFTEEN_MINS))) {
				tradedPricexQty += (trade.getQuantity() * trade
						.getTradedPrice());
				qty += trade.getQuantity();
			}
		}
		if (qty > 0) {
			return tradedPricexQty / qty;
		}
		return null;
	}

	public Double getLatestGBCEIndexPrice() {
		return Calc.geometricMean(lastTradedPriceMap.values());
	}

}
