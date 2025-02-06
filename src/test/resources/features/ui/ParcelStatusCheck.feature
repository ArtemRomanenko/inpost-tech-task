@UI
Feature: InPost Parcel Status Check

  Scenario Outline: Check parcel status for different parcel numbers
    Given User navigate to InPost website
    And User close Cookie banner
    When User search for parcel number "<parcelNumber>"
    Then The status should be "<expectedStatus>"

    Examples:
      | parcelNumber             | expectedStatus      |
      | 520113014230722029585646 | Delivered           |
      | 520107010449991105638120 | Passed for delivery |
      | 523000016696115042036670 | Label nullified     |
      | 520000011395200025754311 | Delivered           |