Feature: A set of scenarios for testing the "User checks out a product; Admin changes the products price" module

  Scenario Outline: Testing a case where user checks out a product
    Given User is on Home Page
    When User is logged in with "<Email>" and "<Password>"
    And User adds a "<Product>" to cart
    Then "<Product>" successfully added to the cart
    And User navigate to checkout page
    Examples:
      | Email                 | Password  | Product |
      | lioraftabi@gmail.com  | 12345678  | MacBook |
      | lioraftabi@gmail.com  | 12345678  | iPhone  |


#  Scenario Outline: Testing a case where admin change a products price
#    Given Admin is on Home Page
#    When Admin is logged in with "<UserName>" and "<Password>"
#    And Admin navigates to Catalog
#    And Admin clicks on Products
#    And Admin clicks on Edit of a specific "<Product>"
#    And Admin navigates to Data tab
#    And Admin changes the price to "<Price>"
#    And Admin clicks on Save
#    Then Price successfully changed
#
#    Examples:
#      | UserName | Password | Product | Price |
#      | admin    | 1234     | iMac    | 5700  |
#      | admin    | 1234     | iPhone  | 1000  |
#
#
#  Scenario Outline: Testing a case where user checks out a product and admin change a products price
#    Given User is on Home Page
#    When User is logged in with "<Email>" and "<UserPassword>"
#    And User adds a "<Product>" to cart
#    And Product successfully added to the cart
#    And User navigate to checkout page
#    And Admin is on Home Page
#    And Admin is logged in with "<Admin>" and "<AdminPassword>"
#    And Admin navigates to Catalog
#    And Admin clicks on Products
#    And Admin clicks on Edit of a specific "<Product>"
#    And Admin navigates to Data tab
#    And Admin changes the price to "<Price>"
#    And Admin clicks on Save
#    Then Price successfully changed
#    Examples:
#      | Admin | Email                 | UserPassword  | AdminPassword | Product | Price |
#      | admin | lioraftabi@gmail.com  | 12345678      |     1234      | MacBook | 6000  |
#      | admin | lioraftabi@gmail.com  | 12345678      |     1234      | iPhone  | 2000  |



    #http://localhost/opencart/adminn/index.php?route=common/dashboard&user_token=4f5d93beb791a4e2967c61bd79f5edda