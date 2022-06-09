Feature: Create Order
    As a client of aDress I want to make an order so that it gets delivered

    Scenario: Information is correct
        Given I have items in my cart
        And I click the "Make purchase" button
        And my information is right
        And I click "Confirm"
        When I go to my profile
        Then the order shows in my orders

    Scenario: Information is not correct

        Given I have items in my cart
        And I click the "Make purchase" button
        And my information is wrong
        And I alter my information
        And I click "Confirm"
        When I go to my profile
        Then the order shows in my orders
        And my profile information is right