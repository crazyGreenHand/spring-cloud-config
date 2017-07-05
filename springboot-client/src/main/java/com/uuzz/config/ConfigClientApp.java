package com.uuzz.config;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.uuzz.config.interceptor.FormSubmissionInterceptor;

@RefreshScope
@SpringBootApplication(scanBasePackages="com.uuzz.*")
@MapperScan({"com.uuzz.*.dao","com.uuzz.*.mapper"})//扫描mybatis映射接口
@ConfigurationProperties("classpath:application.properties")//指定配置文件的位置
@ImportResource(locations={"classpath:quartz.xml"})
@EnableScheduling
public class ConfigClientApp extends WebMvcConfigurerAdapter{

	//注册拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//防止表单重复提交拦截器
		registry.addInterceptor(new FormSubmissionInterceptor());
	}
	
	// 配置消息转换器
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {

		super.configureMessageConverters(converters);
		//添加fastJson解析框架
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastConverter.setFastJsonConfig(fastJsonConfig);

		converters.add(fastConverter);
	}
		
	public static void main(String[] args) throws SchedulerException {
				
		SpringApplication.run(ConfigClientApp.class, args);	
	}
}
