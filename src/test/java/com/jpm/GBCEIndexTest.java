package com.jpm;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.Test;

import com.jmp.test.dto.Stock;
import com.jmp.test.dto.Stock.TYPE;
import com.jmp.test.dto.StockTrade;
import com.jpm.test.service.TradeRepository;

public class GBCEIndexTest {
	
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");	

	@Test
	public void positiveTests() throws Exception {
		
		TradeRepository tradeRepository = new TradeRepository();
		
		Stock stock1 = new Stock.Builder().symbol("TEA").type(TYPE.COMMON)
				.dividend(0.0).parValue(100.0).build();
		
		Stock stock2 = new Stock.Builder().symbol("POP").type(TYPE.COMMON)
				.dividend(0.0).parValue(100.0).build();
		
		Stock stock3 = new Stock.Builder().symbol("ALE").type(TYPE.COMMON)
				.dividend(0.0).parValue(100.0).build();
		
		Stock stock4 = new Stock.Builder().symbol("GIN").type(TYPE.COMMON)
				.dividend(0.0).parValue(100.0).build();		

		StockTrade trade = new StockTrade.Builder().stock(stock1)
				.timestamp(sdf.parse("10/14/2015 12:35:10")).quantity(10)
				.tradedPrice(100.0).build();
		
		tradeRepository.captureTrade(trade);
		
		trade = new StockTrade.Builder().stock(stock2)
				.timestamp(sdf.parse("10/14/2015 12:36:10")).quantity(20)
				.tradedPrice(110.0).build();
		
		tradeRepository.captureTrade(trade);
		
		trade = new StockTrade.Builder().stock(stock3)
				.timestamp(sdf.parse("10/14/2015 12:55:10")).quantity(10)
				.tradedPrice(120.0).build();
		
		tradeRepository.captureTrade(trade);
		
		trade = new StockTrade.Builder().stock(stock4)
				.timestamp(sdf.parse("10/14/2015 12:55:10")).quantity(5)
				.tradedPrice(125.0).build();
		
		tradeRepository.captureTrade(trade);		
		
		trade = new StockTrade.Builder().stock(stock4)
				.timestamp(sdf.parse("10/14/2015 12:55:10")).quantity(5)
				.tradedPrice(130.0).build();
		
		tradeRepository.captureTrade(trade);		
		
		assertEquals(114.45, tradeRepository.getLatestGBCEIndexPrice(),0.01);
	}
}
