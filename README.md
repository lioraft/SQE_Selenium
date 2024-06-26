# Software Quality Engineering - System Testing
This is a repository for the system-testing assignment of the Software Quality Engineering course at [Ben-Gurion University](https://in.bgu.ac.il/), Israel.

## Assignment Description
In this assignment, we tested an open-source software called [OpenCart](https://address-of-the-project.com).

*General Description:*
The software implements a mobile store. Users can add or remove items and buy products.

## Installation
*Instructions:*
To install OpenCart and set up the testing environment, follow these steps:
1. Download the latest release of OpenCart from [the official website](https://www.opencart.com/index.php?route=common/home).
2. Follow the installation instructions provided in the OpenCart documentation.
3. Set up the enviorement and configure the necessary settings.

## What we tested
*Module and User Stories:*
We tested the OpenCart that allows for adding and checks out products. We chose to test the following user stories:

*Module and User Stories:*

### User Checks Out a Product

- *User story:* A user successfully checks out a product.
   - *Preconditions:* The user has items in the shopping cart and is logged in.
   - *Steps:*
      1. Add product to cart
      2. Navigate to the shopping cart.
      3. Click on the "Checkout" button.
   - *Expected outcome:* The user receives an order confirmation, and the product is marked as purchased.

### Admin Changes a Product's Price

- *User story:* An admin successfully changes a product's price.
   - *Preconditions:* The user is logged in as an administrator and has access to product management.
   - *Steps:*
      1. Navigate to the product management section.
      2. Locate the product and update its price.
   - *Expected outcome:* The product's price is successfully updated, and the changes reflect across the system.
