Feature: Cars CRUD operations

  Scenario: Retrieving all cars
    Given The method "GET" is available for endpoint "cars"
    When I retrieve all cars
    Then I should receive a list of cars