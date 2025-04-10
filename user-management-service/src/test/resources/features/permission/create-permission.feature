Feature: Create Permission Feature

  As a system administrator,
  I want to create new permissions in the system
  So that I can control and manage access to various endpoints.

  @smoke
  Scenario: SuccessfullyCreatePermissionWithValidCommand
    Given the system is provided with the following valid permission details:
      | name               | endpoint          | httpMethod  | verificationRequired |
      | ViewPermissions    | /permissions      | GET         | false                |
    When the system processes a valid request to the '/permission' endpoint.
    Then the system should respond with the following permission details:
      | id       | name               | endpoint          | httpMethod  | verificationRequired | timestamps   |
      | notNull  | ViewPermissions    | /permissions      | GET         | false                | notNull      |