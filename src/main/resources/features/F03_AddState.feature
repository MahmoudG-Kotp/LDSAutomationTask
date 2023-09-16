@LISTest
Feature: F03_Add State | Add state to the database

  Background: Admin login successfully
    Given admin navigates to login page
    And admin sets "m.alaa@egy2d.com" as email
    And admin sets "123456" as password
    And click login button
    Then login proceed successfully
    When admin clicks on branches tab
    And admin selects states tab
    And click open state form button

  Scenario: S01_Mandatory Field | Admin can't add state with blank data
    When admin sets "" as state name
    Then add state button won't be clickable

  Scenario Outline: S02_Invalid Name | Admin can't add state with invalid name
    When admin sets "<invalid name>" as state name
    Then state name field shows warning message
    And add state button won't be clickable
    Examples:
      | invalid name |
      | #dummy###    |
      | d!ummy@      |
      | dummy?       |

  Scenario: S03_Existent State | Admin can't add state with existent name
    When admin sets "Cairo" as state name
    And click add state button
    Then error message of existent state will be shown


  #Range between {2, 100} Outer boundaries according to BVA {1, 101}
  Scenario Outline: S04_Exceed Outer Boundaries Range | Admin can't add state if range exceeded
    When admin sets state name with "<range>" length
    Then state name field shows error message
    And add state button won't be clickable
    Examples:
      | range |
      | 1     |
      | 101   |

  #Inner boundaries according to BVA {2, 100}
  Scenario Outline: S05_Verify Inner Boundaries Range | Admin can add state if its name in range
    When admin sets state name with "<range>" length
    Then state name field doesn't show error message
    And click add state button
    And successful message of added state shown
    And state will be added successfully to the list
    Examples:
      | range |
      | 2     |
      | 100   |

  #Extra scenario because inner boundaries fail
  Scenario: S06_Valid Name | Admin able to add state with valid name successfully
    When admin sets state name with "10" length
    And click add state button
    Then state name field doesn't show error message
    And successful message of added state shown
    And state will be added successfully to the list