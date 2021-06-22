package com.nokia.atf.tef.common.hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;

import com.nokia.atf.tef.common.utilities.WEB_Methods;

import cucumber.api.Scenario;

public class ScenarioHooks {

	@Before
	public void beforeCallingScenario() {
		System.out.println("*********** About to start the scenario.");
	}

	@After
	public void afterRunningScenario(Scenario scenario) {
		System.out.println("*********** Just finished running scenario: "
				+ scenario.getStatus() + " : " + scenario.getName());
			}
}