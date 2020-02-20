package com.automation.step_definitions;

import com.automation.pages.LoginPage;
import com.automation.utilities.MobileUtilities;
import io.cucumber.java.en.*;

public class LoginStepDefinitions {

    LoginPage loginPage = new LoginPage();

    @Given("use clicks on get started")
    public void use_clicks_on_get_started() {
        loginPage.clickOnGetStarted();
    }

    @When("user logs in with etsy credentials")
    public void user_logs_in_with_etsy_credentials() {
        loginPage.login();
        MobileUtilities.wait(10);//for demo
    }

    @Then("user verifies that etsy logo is displayed")
    public void user_verifies_that_etsy_logo_is_displayed() {

    }

}
