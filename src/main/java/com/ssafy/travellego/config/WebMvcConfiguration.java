package com.ssafy.travellego.config;

import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@MapperScan(basePackages = {"com.ssafy.travellego.**.mapper"})
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	private final List<String> patterns = Arrays.asList("/attraction/*", "/board/*", "/member/*");
	
//	private ConfirmInterceptor confirmInterceptor;
//	
//	public WebMvcConfiguration(ConfirmInterceptor confirmInterceptor) {
//		this.confirmInterceptor = confirmInterceptor;
//	}
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(confirmInterceptor).addPathPatterns(patterns);
//	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*")
		//.allowedOrigins("http://www.ssafy.com", "http://localhost")
		.allowedMethods("GET","POST","PUT","DELETE")
		//.allowedMethods(RequestMethod.GET.name(), RequestMethod.POST.name())
		//.allowedMethods(*)
		.maxAge(1800);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
}
