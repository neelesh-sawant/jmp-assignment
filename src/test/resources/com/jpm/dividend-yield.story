Scenario:  dividend yield of PREFERRED stock
 
Given a PREFERRED stock of symbol TEA, a parValue of 100.0 and dividend-rate of 2 %
When the stock is traded at 50.0
Then the dividend-yield should be 0.04

When the stock is traded at 100.0
Then the dividend-yield should be 0.02

When the stock is traded at 150.0
Then the dividend-yield should be 0.013

Scenario:  dividend yield of COMMON stock
 
Given a COMMON stock of symbol GIN with last dividend of 16.0
When the stock is traded at 50.0
Then the dividend-yield should be 0.32

When the stock is traded at 100.0
Then the dividend-yield should be 0.16

When the stock is traded at 150.0
Then the dividend-yield should be 0.106