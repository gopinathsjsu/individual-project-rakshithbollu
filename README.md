# individual-project-rakshithbollu

## Problem Statement
Create a Java based Warehouse management application with the product details like product name , stock available , category and the card details for payment. A static
database file is created in csv format . Once user adds the products to his cart and proceeds for payment the cap limit check for each category of the products is made.
If the user uses a new card that is not present in database then the card should be added in the database and the total cart value along with the items should be stored
in the output file.

# Steps to run the program
1. To compile the source code run :  'Javac Billing.java' in src folder.
2. Execute 'java Billing' to run the program.
3. Enter the input file path.

## Design Patterns
### Singleton
* Singleton pattern solves 2 questions :
    1) Ensure that a class has just a single instance. 
    2) Provide a global access point to that instance.
* ItemCap is a Singleton class that has the cap limit details of each product category.
* Everytime a object creation is executed for this class , the same single instance is returned.
* 
![](https://github.com/gopinathsjsu/individual-project-rakshithbollu/blob/main/screenshots/singleton_design_pattern_class_diagram.png)

