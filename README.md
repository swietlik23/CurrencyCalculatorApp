# CurrencyCalculatorApp
---
This application allows you to convert euros to another currency. __CurrencyCalculatorApp__ is a Java console application.
Detailed information is presented below.
### Table of contents

---

* [Build with](#build-with)

* [Data source](#data-source)

* [Presentation](#presentation)

* [Unit tests](#unit-tests)

* [Status](#status)

### Build with
---
* Java

* JDK 1.8

* Gradle

* JUnit5

### Data source
---
This application is based on exchange rate data downloaded from the website: 
https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml

The schema showing the file structure is shown below:
![XMLfile](https://github.com/swietlik23/CurrencyCalculatorApp/blob/main/picturesToReadeMe/XMLfile.PNG)

### Presentation
---
_Main menu_ After launching the application, you can see the main menu as below.

![main](https://github.com/swietlik23/CurrencyCalculatorApp/blob/main/picturesToReadeMe/main.PNG)

_Currency list_ In order to see the available destination currencies, please type >2< and press >enter<. You will see the following image:

![currencyList](https://github.com/swietlik23/CurrencyCalculatorApp/blob/main/picturesToReadeMe/currencyList.PNG)

_Calculate EUR to another currency_ When you type >1< and press >enter< in main menu 
the application will ask you to enter the amount of euros and the target currency into which you want to convert the amount.
If input data is correct, you get the result (see below).

![result](https://github.com/swietlik23/CurrencyCalculatorApp/blob/main/picturesToReadeMe/result.PNG)

_Incorrect input data_ If the data is incorrect you will see the following message and the application will return to the main menu.

![incorrectData](https://github.com/swietlik23/CurrencyCalculatorApp/blob/main/picturesToReadeMe/incorrectData.PNG)

### Unit tests
---
In order to verify the correct operation of the application, unit tests were prepared (based on JUnit5).
You can see below a screenshot of an excerpt from the testing class

![unitTest](https://github.com/swietlik23/CurrencyCalculatorApp/blob/main/picturesToReadeMe/unitTest.PNG)

### Status
---
The project is in progress with some active features and another improvements to make.

---
