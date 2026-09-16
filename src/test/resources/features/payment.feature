Feature: Payment with Virtual Account

  Background:
    Given User is logged in to Klik Indomaret with email "your_email@example.com" and password "your_password"

  Scenario: Complete purchase using Virtual Account and verify total price calculation
    When User searches for product "Indomie Goreng"
    And User opens the first product detail page
    And User sees the product price is "Rp3.500"
    And User adds the product to cart
    And User chooses Delivery method
    And User clicks button "Beli"
    And User selects payment option "Virtual Account"
    And User clicks button "Bayar sekarang"
    Then User should be redirected to order success page
    And The total price displayed should match the calculated total
    And I see the product price is still "Rp3.500"
