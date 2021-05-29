package com.algaworks.algalog.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	
	//@Bean é gerenciado pelo spring
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
