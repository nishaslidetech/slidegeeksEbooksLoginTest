@FreeLogin
Feature: Download Ebooks via free login

  Scenario: Download Ebooks via free login

  Given User is on geeks Home Page
  Then click on Sign in button
  Then Enter valid user name and password
  Then Click on Ebooks 
  Then Click on Buy Now button 
  Then verify the checkout process
  Then user logout from the application
