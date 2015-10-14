package com.jpm.storyrunners;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Map;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.model.ExamplesTable;

import com.jmp.test.dto.Stock;
import com.jmp.test.dto.Stock.TYPE;
import com.jmp.test.dto.StockTrade;
import com.jpm.test.service.TradeRepository;

public class GBCEIndexPriceStoriesRunnerApp {
	
	private TradeRepository tradeRepository = new TradeRepository();

	@Given("the following trades for GBCE Index: $tradesTable")
	public void theTradesForGBCEIndexPrice(ExamplesTable tradesTable) {
		
		for (Map<String, String> row : tradesTable.getRows()) {
			String symbol = row.get("symbol");
			String minsAgo = row.get("mins-ago");
			String tradedPrice = row.get("traded-price");
			String quantity = row.get("quantity");
			
			Stock stock = new Stock.Builder().symbol(symbol).type(TYPE.COMMON).build();
			
			StockTrade trade = new StockTrade.Builder().stock(stock)
					.timestamp(new Date(System.currentTimeMillis() - Integer.valueOf(minsAgo)*60*1000))
					.quantity(Integer.valueOf(quantity))
					.tradedPrice(Double.valueOf(tradedPrice)).build();
			
			tradeRepository.captureTrade(trade);
		}
	}

	@Then("the GBCE All Share Index is $price")
	public void theIndexPriceIs(double price) {
		assertEquals(price,tradeRepository.getLatestGBCEIndexPrice(),0.001);
	}

}
