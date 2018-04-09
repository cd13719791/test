package com.moyou.moyouRms.intercept;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.moyou.moyouRms.constants.enums.LogEnum;

 
 @Target({ElementType.PARAMETER, ElementType.METHOD})  
 @Retention(RetentionPolicy.RUNTIME)  
 @Documented 
public @interface Log {

	 
	 /** 要执行的操作类型比如：add操作 **/  
	      public String operationType() default "";  
	       
	    
	      /**
	       * 操作日志枚举
	       * @return
	       */
	      public LogEnum operationLogEnum() default LogEnum.DEFAULT_VALUE;
	      
	      
	     /** 要执行的具体操作比如：添加用户 **/  
	      public String operationName() default "";
	  }
 
