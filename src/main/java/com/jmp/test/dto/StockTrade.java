package com.jmp.test.dto;

import java.util.Date;

public class StockTrade {
	
	public enum TYPE { BUY, SELL};
	
	public StockTrade()
	{
		
	}
	
	public StockTrade(Builder builder)
	{
		this.stock = builder.stock;
		this.type = builder.type;
		this.timestamp = builder.timestamp;
		this.quantity = builder.quantity;
		this.tradedPrice = builder.tradedPrice;
	}	
	
	private Stock stock;
	private Date timestamp;
	private int quantity;
	private TYPE type;
	private Double tradedPrice;
	
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public TYPE getType() {
		return type;
	}
	public void setType(TYPE type) {
		this.type = type;
	}
	public Double getTradedPrice() {
		return tradedPrice;
	}
	public void setTradedPrice(Double tradedPrice) {
		this.tradedPrice = tradedPrice;
	}
	
	public static class Builder
	{
		private Stock stock;
		private Date timestamp;
		private int quantity;
		private TYPE type;
		private Double tradedPrice;
		
		public Builder stock(Stock stock) {
			this.stock = stock;
			return this;
		}
		public Builder timestamp(Date timestamp) {
			this.timestamp = timestamp;
			return this;
		}
		public Builder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}
		public Builder type(TYPE type) {
			this.type = type;
			return this;
		}
		public Builder tradedPrice(Double tradedPrice) {
			this.tradedPrice = tradedPrice;
			return this;
		}
		public StockTrade build()
		{
			return new StockTrade(this);
		}
	}	
}
