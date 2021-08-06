#Test Cases
[] Case1  
Given a parking lot, and a car  
When park the car  
Then return a parking ticket.

[] Case2  
Given a parking lot, a ticket  
When fetch the car   
Then return a car

[] Case3   
Given a parking lot, a wrong ticket  
When fetch the car   
Then no car fetch


[] Case4   
Given a parking lot, a used ticket  
When fetch the car   
Then no car fetch

[] Case5  
Given a parking lot, a full slot  
When park the car
Then no ticket





