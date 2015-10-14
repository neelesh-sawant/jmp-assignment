package com.jmp.test.dto;

import java.util.Date;

public class Stock {
	
	public Stock()
	{
		
	}
	
	public Stock(Builder builder)
	{
		this.symbol = builder.symbol;
		this.type = builder.type;
		this.lastDividend = builder.lastDividend;
		this.lastDividendDate = builder.lastDividendDate;
		this.fixedDividendRate = builder.fixedDividendRate;
		this.parValue = builder.parValue;
	}
	
	public enum TYPE {COMMON, PREFERRED};
	
	private String symbol;
	
	private String instrumentId;
	private String issuerId;
	
	private TYPE type;
	
	private Double lastDividend;
	private Date lastDividendDate;
	
	private Double fixedDividendRate;
	private Double parValue;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getInstrumentId() {
		return instrumentId;
	}
	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public Double getLastDividend() {
		return lastDividend;
	}
	public void setLastDividend(Double lastDividend) {
		this.lastDividend = lastDividend;
	}
	public Date getLastDividendDate() {
		return lastDividendDate;
	}
	public void setLastDividendDate(Date lastDividendDate) {
		this.lastDividendDate = lastDividendDate;
	}
	public Double getFixedDividendRate() {
		return fixedDividendRate;
	}
	public void setFixedDividendRate(Double fixedDividendRate) {
		this.fixedDividendRate = fixedDividendRate;
	}
	public Double getParValue() {
		return parValue;
	}
	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}
	
	public void setType(String typeStr) {
		this.type = TYPE.valueOf(typeStr);
	}
	public void setType(TYPE type) {
		this.type = type;
	}
	public TYPE getType() {
		return this.type;
	}	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((instrumentId == null) ? 0 : instrumentId.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (instrumentId == null) {
			if (other.instrumentId != null)
				return false;
		} else if (!instrumentId.equals(other.instrumentId))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}
	
	
	public static class Builder
	{
		private String symbol;
		private TYPE type;
		private Double lastDividend;
		private Date lastDividendDate;
		private Double fixedDividendRate;
		private Double parValue;
		
		public Builder symbol(String symbol) {
			this.symbol = symbol;
			return this;
		}
		public Builder type(TYPE type) {
			this.type = type;
			return this;
		}
		public Builder dividend(Double lastDividend) {
			this.lastDividend = lastDividend;
			return this;
		}
		public Builder dividendDate(Date lastDividendDate) {
			this.lastDividendDate = lastDividendDate;
			return this;
		}
		public Builder fixedDividendRate(Double fixedDividendRate) {
			this.fixedDividendRate = fixedDividendRate;
			return this;
		}
		public Builder parValue(Double parValue) {
			this.parValue = parValue;
			return this;
		}
		
		public Stock build()
		{
			return new Stock(this);
		}
	}
}
