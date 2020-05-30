package com.itwh.a0520erp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
 * @Author 翁煌
 * @Description:
 * @Version 1.0
 */
@Configuration
@ConditionalOnClass(value= {PaginationInterceptor.class})
public class MybatisPlusConfig {

	//配置分页插件
	@Bean
	public PaginationInterceptor  paginationInterceptor() {
		return new PaginationInterceptor();
	}

}
