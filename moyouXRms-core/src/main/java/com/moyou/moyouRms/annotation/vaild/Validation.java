package com.moyou.moyouRms.annotation.vaild;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.moyou.moyouRms.annotation.rule.Length;
import com.moyou.moyouRms.annotation.rule.Rule;

/**
 * 自定义校验
 * 
 * @author PzC.
 * @date 2016年11月15日 下午8:14:44
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Validation {
	/**
	 * <font color="red">非空验证</font>
	 * 
	 * @Title notNull
	 * @return String[]
	 * @since 1.0
	 */
	public String[] notNull() default {};

	/**
	 * <font color="red">邮箱验证</font>
	 * 
	 * @Title email
	 * @return String[]
	 * @since 1.0
	 */
	public String[] email() default {};

	/**
	 * <font color="red">手机验证</font>
	 * 
	 * @Title mobile
	 * @return String[]
	 * @since 1.0
	 */
	public String[] mobile() default {};

	/**
	 * <font color="red">自定义验证</font>
	 * 
	 * @Title rules
	 * @return {@linkplain Rule}
	 * @since 1.0
	 */
	public Rule[] rules() default {};

	/**
	 * <font color="red">长度验证</font>
	 * 
	 * @Title length
	 * @return {@linkplain Length}
	 * @since 1.0
	 */
	public Length[] length() default {};

	/**
	 * <font color="red">数字验证</font>
	 * 
	 * @Title number
	 * @return String[]
	 * @since 1.0
	 * @WorkFlow
	 */
	public String[] number() default {};

	/**
	 * <font color="red">标识返回页面</font>
	 * 
	 * @Title view
	 * @return {@link String}
	 * @since 1.0
	 * @WorkFlow
	 */

	public String view() default "error";
}