#@LISTest
#Feature: F02_Add User | Add user to the database
#
#  Background: Admin login successfully
#    Given admin sets "m.alaa@egy2d.com" as email
#    And admin sets "123456" as password
#    And click login button
#    Then login proceed successfully
#    And dashboard must be selected from sidebar
#    When admin selects users management tab
#    And click add user button
#
#  Scenario: S01_Mandatory Fields | Admin can't add user with blank data
#    When admin fills all mandatory data
#    But admin leaves one of mandatory fields blank or not selected
#    Then add button won't be clickable
#
#  Scenario: S02_Exceed Range | Admin can't add user if range exceeded
#    When admin exceed fields range
#    Then input field shows warning message
#    And add button won't be clickable
#
#  Scenario Outline: S03_Invalid Email | Admin can't add user with invalid email
#    When admin fills all mandatory data
#    But admin sets "<invalid email>" as email
#    Then email field shows warning message
#    And add button won't be clickable
#    Examples:
#      | invalid email |
#      | dummy         |
#      | dummy@        |
#      | dummy@.com    |
#      | @dummy.com    |
#      | dummy@xx      |
#
#  Scenario: S04_Existent Email | Admin can't add user with existent email
#    When admin fills all mandatory data
#    But an existent email filled like "m.alaa@egy2d.com"
#    And click on add button
#    Then error message of existent email will be shown
#
#  Scenario: S05_Password Matching | Admin can't add user while passwords are not matching
#    When admin fills all mandatory data
#    But password and confirm password don't match each
#    Then error message of miss-match will be shown
#    And add button won't be clickable
#
#  Scenario: S06_Alerts Continuity | Admin keeps informed while passwords are not matching
#    When admin fills all mandatory data
#    And password and confirm password matched
#    And admin changes the password again
#    Then error message of miss-match will be shown
#    And add button won't be clickable
#
#  Scenario: S07_Optional Fields | Admin able to add user while optional fields blank
#    When admin fills all mandatory data
#    And click on add button
#    Then user will be added successfully