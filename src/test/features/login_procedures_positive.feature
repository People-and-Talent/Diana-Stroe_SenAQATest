@regression

  Feature: User Login

  Scenario: The user checks login successfully
  Given The user will login with username "diana.stroe21@gmail.com" and password "Logifuture2024"
    Then Check the login was successful


    Scenario: The user checks login having username in capital letters and a correct password
      Given The user will login with username "DIANA.STROE21@GMAIL.COM" and password "Logifuture2024"
      Then Check the login was successful

      #here the check is that basically he complained about invalid user and pass because he got them both through copy paste
    Scenario: The user checks if copy paste is allows copy paste
      Given The user copy pastes and insert in both fields
      Then  Check the login wasn't successful and a warning is displayed