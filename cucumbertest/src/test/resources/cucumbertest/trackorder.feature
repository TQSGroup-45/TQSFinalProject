Feature: As a client of aDress, I want to track my order,so that I know where it is

    Scenario: Track an order
        Given I've made an order
        When I go to my profile
        And I find my order and click "track"
        Then I get information about its whereabouts