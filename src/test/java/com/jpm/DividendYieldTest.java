package com.jpm;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jmp.test.dto.Stock;
import com.jmp.test.dto.Stock.TYPE;
import com.jpm.test.util.Calc;

public class DividendYieldTest {

	@Test
	public void positiveTests() {
		Stock stock = new Stock.Builder().symbol("POP").type(TYPE.COMMON)
				.dividend(8.0).parValue(100.0).build();
		assertEquals(0.08, Calc.calculateDividendYield(stock, 100.0),0.001);
		
		stock = new Stock.Builder().symbol("ALE").type(TYPE.COMMON)
				.dividend(23.0).parValue(60.0).build();
		assertEquals(0.23, Calc.calculateDividendYield(stock, 100.0),0.001);
		
		stock = new Stock.Builder().symbol("GIN").type(TYPE.PREFERRED)
				.dividend(8.0).fixedDividendRate(0.02).parValue(100.0).build();
		assertEquals(0.04, Calc.calculateDividendYield(stock, 50.0),0.001);
		
		stock = new Stock.Builder().symbol("JOE").type(TYPE.COMMON)
				.dividend(13.0).parValue(250.0).build();
		assertEquals(0.13, Calc.calculateDividendYield(stock, 100.0),0.001);		
	}
	
	@Test
	public void negativeTests() {
		Stock stock = new Stock.Builder().symbol("TEA").type(TYPE.COMMON)
				.dividend(0.0).parValue(100.0).build();
		assertEquals(0.0, Calc.calculateDividendYield(stock, 100.0),0.001);
	}

}
