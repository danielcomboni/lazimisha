package com.lazimisha.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {

	registry.addResourceHandler(
			"/webjars/**",
			"/css/**", 
			"/js/**")
	
	.addResourceLocations(
			"classpath:/META-INF/resources/webjars/",
            "classpath:/static/css/",
            "classpath:/static/js/");

	}

}



/* https://stackoverflow.com/questions/47552835/the-type-webmvcconfigureradapter-is-deprecated */
/*
	@Configuration
	public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			return bCryptPasswordEncoder;
		}
	
	}

*/