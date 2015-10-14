package com.jpm;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.Test;

import com.jmp.test.dto.Stock;
import com.jmp.test.dto.Stock.TYPE;
import com.jmp.test.dto.StockTrade;
import com.jpm.test.service.TradeRepository;

public class VolumeWeightedStockPriceTest {
	
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");	

	@Test
	public void positiveTests() throws Exception {
		
		TradeRepository tradeRepository = new TradeRepository();
		
		Stock stock = new Stock.Builder().symbol("TEA").type(TYPE.COMMON)
				.dividend(0.0).parValue(100.0).build();

		StockTrade trade = new StockTrade.Builder().stock(stock)
				.timestamp(sdf.parse("10/14/2015 12:35:10")).quantity(10)
				.tradedPrice(120.0).build();
		
		tradeRepository.captureTrade(trade);
		
		trade = new StockTrade.Builder().stock(stock)
				.timestamp(sdf.parse("10/14/2015 12:36:10")).quantity(20)
				.tradedPrice(121.0).build();
		
		tradeRepository.captureTrade(trade);
		
		trade = new StockTrade.Builder().stock(stock)
				.timestamp(sdf.parse("10/14/2015 12:55:10")).quantity(10)
				.tradedPrice(122.0).build();
		
		tradeRepository.captureTrade(trade);
		
		trade = new StockTrade.Builder().stock(stock)
				.timestamp(sdf.parse("10/14/2015 12:55:10")).quantity(5)
				.tradedPrice(123.0).build();
		
		tradeRepository.captureTrade(trade);		
		assertNull(tradeRepository.getVolumeWeightedPrice("TEA"));
		//assertEquals(122.33, tradeRepository.getVolumeWeightedPrice("TEA"),0.01);
	}
}
