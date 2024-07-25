Feature: Login Page validations

  Scenario: Verify user is able to launch the website successfully
    When User gives correct website link
    Then user is navigated to the home page of website

  Scenario: Verify user is able to launch website with invalid link
    When user gives invalid link
    Then user lands on invalid link

  Scenario: Verify for the broken links
    When User gives correct website link
    Then HTTP response >= 400. Then the link is broken

  Scenario:Verify the text spelling in the page
    When User gives correct website link
    Then user should see correct spellings in all fields

  Scenario:Verify the company logo
    When User gives correct website link
    Then user should see logo on the right side

  Scenario:Verify input descriptive test in username field
    When User gives correct website link
    Then user should see username in gray color

  #Scenario Outline:
    #Given User enters invalid "<username>" and "<password>"
    #When User clicks login button using mouse
    #Then user gets error message as "Invalid credentials"
    #Examples:
    #|username|password|
    #|software|testing |
    #|Selenium|Java    |
    #|#@$cbjsl|password|
    #|47389290|password|
#
    Scenario Outline: user loggedin
          Given user enters credentials from "<SheetName>" and <RowNumber> 
      When user clicks login
     Then user is logged in
      Examples:
      |SheetName|RowNumber|
      |login    |    0   |
