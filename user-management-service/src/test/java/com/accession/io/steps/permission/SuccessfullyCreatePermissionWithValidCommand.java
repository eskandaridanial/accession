package com.accession.io.steps.permission;

import com.accession.io.common.contracts.records.BaseResponse;
import com.accession.io.common.properties.ResponseProperties;
import com.accession.io.config.CucumberConfig;
import com.accession.io.message.commands.CreatePermissionCommand;
import com.accession.io.message.models.ResponseModel;
import com.accession.io.message.responses.CreatePermissionResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import lombok.RequiredArgsConstructor;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-12-26 18:19:46
 */
@RequiredArgsConstructor
public class SuccessfullyCreatePermissionWithValidCommand {

    private final CucumberConfig cucumberConfig;

    private CreatePermissionCommand command;
    private BaseResponse<CreatePermissionResponse> response;

    private static final ResponseModel expectedModel = ResponseProperties.of();

    @Given("the system is provided with the following valid permission details:")
    public void given(CreatePermissionCommand command) {
        this.command = command;
    }

    @When("the system processes a valid request to the {string} endpoint.")
    public void when(String path) {
        response = RestAssured.given().contentType("application/json").body(command)
                .post(cucumberConfig.urlOf(path)).as(new TypeRef<>() {});
    }

    @Then("the system should respond with the following permission details:")
    public void then(CreatePermissionResponse expected) {
        assert response != null;
        assert response.code().equals(expectedModel.code());
        assert response.message().equals(expectedModel.message());

        CreatePermissionResponse result = response.result();

        assert result != null;
        assert result.id() != null;
        assert result.timestamps() != null;

        assert result.name().equals(expected.name());
        assert result.endpoint().equals(expected.endpoint());
        assert result.httpMethod().equals(expected.httpMethod());
        assert result.verificationRequired().equals(expected.verificationRequired());
    }
}
