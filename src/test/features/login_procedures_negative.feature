@regression

Feature: User Login -negative cases

  Scenario: The user checks login having an invalid username and a correct password
    Given The user will login with username "sadasd" and password "Logifuture2024"
    Then Check the login wasn't successful and a warning is displayed

  Scenario: The user checks login having a correct username and a wrong password
    Given The user will login with username "diana.stroe21@gmail.com" and password "Logifuture"
    Then Check the login wasn't successful and a warning is displayed

  Scenario: The user checks if there is any complaint regaring to special characters in username field
    Given The user will login with username "$#%^$^%" and password "Logifuture"
    Then Check the login wasn't successful and a warning is displayed