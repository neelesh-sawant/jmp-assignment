package com.jpm;

import java.util.Date;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.jmp.test.dto.Stock;
import com.jmp.test.dto.Stock.TYPE;
import com.jmp.test.dto.StockTrade;
import com.jpm.test.util.Calc;

import static org.junit.Assert.*;

public class DividendYieldStoriesRunnerApp {
	
	   private Stock stock;
	   private StockTrade stockTrade;
	   
	    @Given("a PREFERRED stock of symbol $symbol, a parValue of $threshold and dividend-rate of $dividendRate %")
	    public void aPreferredStock(String symbol, double parValue, double dividendRate) {
	        stock = new Stock.Builder().symbol(symbol).type(TYPE.PREFERRED).parValue(parValue).fixedDividendRate(dividendRate/100.0).build();
	    }
	    
	    @Given("a COMMON stock of symbol $symbol with last dividend of $lastDividend")
	    public void aCommonStock(String symbol, double lastDividend) {
	        stock = new Stock.Builder().symbol(symbol).type(TYPE.COMMON).dividend(lastDividend).build();
	    }	    
	 
	    @When("the stock is traded at $price")
	    public void theStockIsTradedAt(double price) {
	    	stockTrade = new StockTrade.Builder().stock(stock)
					.timestamp(new Date(System.currentTimeMillis()))
					.tradedPrice(price).build();
	    }
	 
	    @Then("the dividend-yield should be $divYield")
	    public void theDividendYieldShouldBe(double divYield) {
	    	assertEquals(divYield, Calc.calculateDividendYield(stock, stockTrade.getTradedPrice()),0.001);
	    }

}
