package com.nokia.atf.tef.common.transforms;

import java.util.Locale;

import com.nokia.atf.core.common.lib.HubType;

import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.cucumberexpressions.ParameterType;

public class Transformer implements TypeRegistryConfigurer {
	
	@Override
	public void configureTypeRegistry(TypeRegistry registry) {
		
		registry.defineParameterType(
				new ParameterType<>("hubtype", ".*?", HubType.class, 
						HubType::parseNameDetails));
	}
	
	@Override
	public Locale locale() {
		return Locale.ENGLISH;
}
}
