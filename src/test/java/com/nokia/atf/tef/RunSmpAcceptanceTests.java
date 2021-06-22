package com.nokia.atf.tef;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/smp",
        glue = "com.nokia.atf.tef"
       
)
public class RunSmpAcceptanceTests {
	
	@Rule
	public SpringIntegrationMethodRule springIntegrationMethodRule = new SpringIntegrationMethodRule();

	@BeforeClass
    public static void setUp(){

    }
	
    @AfterClass
    public static void tearDown(){
    	
    }
    

}

