# JSON Bank - Loan Broker Project

This is the JSON bank for the Loan Broker Project for CPHBusiness Software Development course: **System Integration**.
 
## Overview

To retrieve data from the bank, you can send a POST request to the bank at:  
http://94.130.57.246:9000/bankjson/bank/interestrate

The request you're sending to the bank has to follow the below structure:  
```
{
	"ssn" : "1605789787",
	"creditScore" : "498",
	"loanAmount" : "15000.0",
	"loanDuration" : "10"
}

```

* The **creditScore** is an integer
* The **loanAmount** is a double
* The **loanDuration** is a double

If you send a POST request like above, you will receive a response as below: 
```
{
    "loanResponse" : {
        "bankName" : "BankJSON",
        "interestRate" : "3.1020220708326987",
        "refund" : "20118.0"
    }
}
```  

* The **bankName** is a String
* The **interestRate** returns a Double
* The **refund** returns a Double

The interestrate is calculated as followed:

* The bank has a default credit score value, example 400
* It will take the difference between the default value and the customer credit score value  
* A bank has an incremental,- and decrementalvalue, which indicates when the interestrate will fall or raise.
* A bank has an increment,- and decrement-rate, which indicates how much the interest rate will be correlated 
  when the score differs from the default value.
* The incremental value could as an example be 15. This means that every time the Customer Credit Score increments 
  with 15 from the default value, it will decrease the interestrate with a percentage (decrement-rate). 
  This could be 1% or 5%. The other way around, with the decremental value, it could be set to 10, which would mean 
  that for every 10 credit points below the default score, it would increase the interestrate for the loan.
* The bank has a loan-duration-rate applied as well, which indicates a decrement of the loan interest rate for each year extra added to the loan.
