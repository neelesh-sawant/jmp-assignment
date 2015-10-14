package com.jpm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

		/// 16 mins older trade, shouldn't be considered in the volume-weighted price
		StockTrade trade = new StockTrade.Builder().stock(stock)
				.timestamp(new Date(System.currentTimeMillis() - 16*60*1000))
				.quantity(10)
				.tradedPrice(120.0).build();
		
		tradeRepository.captureTrade(trade);
		
		trade = new StockTrade.Builder().stock(stock)
				.timestamp(new Date(System.currentTimeMillis() - 14*60*1000))
				.quantity(20)
				.tradedPrice(121.0).build();
		
		tradeRepository.captureTrade(trade);
		
		trade = new StockTrade.Builder().stock(stock)
				.timestamp(new Date(System.currentTimeMillis() - 7*60*1000))
				.quantity(10)
				.tradedPrice(122.0).build();
		
		tradeRepository.captureTrade(trade);
		
		trade = new StockTrade.Builder().stock(stock)
				.timestamp(new Date(System.currentTimeMillis() - 5*60*1000))
				.quantity(5)
				.tradedPrice(123.0).build();
		
		tradeRepository.captureTrade(trade);
		
		assertNotNull(tradeRepository.getVolumeWeightedPrice("TEA"));
		assertEquals(121.571, tradeRepository.getVolumeWeightedPrice("TEA"),0.001);
	}
	
	@Test
	public void negativeTests() throws Exception {
		
		TradeRepository tradeRepository = new TradeRepository();
		
		Stock stock = new Stock.Builder().symbol("TEA").type(TYPE.COMMON)
				.dividend(0.0).parValue(100.0).build();

		/// 16 mins older trade, shouldn't be considered in the volume-weighted price
		StockTrade trade = new StockTrade.Builder().stock(stock)
				.timestamp(new Date(System.currentTimeMillis() - 16*60*1000))
				.quantity(10)
				.tradedPrice(120.0).build();
		
		tradeRepository.captureTrade(trade);
		
		/// 25 mins older trade, shouldn't be considered in the volume-weighted price
		trade = new StockTrade.Builder().stock(stock)
				.timestamp(new Date(System.currentTimeMillis() - 25*60*1000))
				.quantity(20)
				.tradedPrice(121.0).build();
		
		tradeRepository.captureTrade(trade);
		
		
		//// No trades in last 15 mins for TEA, expecting null price
		assertNull(tradeRepository.getVolumeWeightedPrice("TEA"));
		
		
		//// No trades captured for GIN, expecting null price
		assertNull(tradeRepository.getVolumeWeightedPrice("GIN"));
	}
	
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	@Test
	public void quantityValidationTest() {
		TradeRepository tradeRepository = new TradeRepository();
		
		Stock stock = new Stock.Builder().symbol("TEA").type(TYPE.COMMON)
				.dividend(0.0).parValue(100.0).build();
		
		StockTrade trade = new StockTrade.Builder().stock(stock)
				.timestamp(new Date(System.currentTimeMillis() - 25*60*1000))
				.quantity(-20)
				.tradedPrice(121.0).build();
		
		thrown.expect(AssertionError.class);
		thrown.expectMessage("Trade Quantity must be greater than zero");
		tradeRepository.captureTrade(trade);		
	}
	
	@Test
	public void priceValidationTest() {
		TradeRepository tradeRepository = new TradeRepository();
		
		Stock stock = new Stock.Builder().symbol("TEA").type(TYPE.COMMON)
				.dividend(0.0).parValue(100.0).build();
		
		StockTrade trade = new StockTrade.Builder().stock(stock)
				.timestamp(new Date(System.currentTimeMillis() - 25*60*1000))
				.quantity(20)
				.tradedPrice(-21.0).build();
		
		thrown.expect(AssertionError.class);
		thrown.expectMessage("Trade Price must be greater than zero");
		tradeRepository.captureTrade(trade);		
	}	
}
