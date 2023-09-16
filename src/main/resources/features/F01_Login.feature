@LISTest
Feature: F01_Login | Login process validation

  Background: Navigate to login page
    Given admin navigates to login page

  Scenario: S01_Mandatory Fields | Admin can't login with blank data
    When admin sets "" as email
    And admin sets "" as password
    And click login button
    Then error message shown

  Scenario Outline: S02_Invalid Email | Admin can't login with invalid email
    When admin sets "<invalid email>" as email
    And click login button
    Then email field shows warning message
    Examples:
      | invalid email |
      | dummy         |
      | dummy@        |
      | dummy@.com    |
      | @dummy.com    |
      | dumm!y@a#     |

  Scenario: S03_Non-Existent Email | Admin can't login with non-existent email
    When admin sets "non_existent@email.com" as email
    And admin sets "12345678" as password
    And click login button
    Then error message shown

  Scenario: S04_Valid Data | Admin login with valid data successfully
    When admin sets "m.alaa@egy2d.com" as email
    And admin sets "123456" as password
    And click login button
    Then login proceed successfully
    And dashboard selected from sidebar
