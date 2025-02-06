@API
Feature: InPost Parcel Lockers Search

  Scenario Outline: Search parcel lockers in different cities
    Given User search for parcel lockers in "<city>"
    Then The parcel lockers data should be saved to "parcellockers.<city>.json"

    Examples:
      | city     |
      | Warszawa |
      | Krakow   |