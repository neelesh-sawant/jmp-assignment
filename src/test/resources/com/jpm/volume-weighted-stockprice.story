Scenario:  volume-weighted stock prices
 
Given the following trades:
|symbol|mins-ago|traded-price|quantity|
|TEA|2|101.10|30|
|TEA|3|102.10|60|
|TEA|14|101.10|50|
|TEA|21|105.10|40|
|TEA|16|102.10|30|
|GIN|12|101.5|30|
|GIN|13|102.10|60|
|GIN|4|101.10|50|
|GIN|11|105.10|40|
|GIN|16|102.10|30|

Then the volume-weighted stock price for TEA is 101.528

Then the volume-weighted stock price for GIN is 102.388