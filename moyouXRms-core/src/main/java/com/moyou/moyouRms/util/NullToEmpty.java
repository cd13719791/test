package com.moyou.moyouRms.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.moyou.moyouRms.util.json.MyBeanSerializerModifier;

/**
 * @author PzC.
 * @time 2017年1月6日 下午2:33:59
 * 
 */
public class NullToEmpty extends ObjectMapper {
	private static final long serialVersionUID = -717115746788875505L;

	public NullToEmpty() {
		super();
		// 允许单引号
		this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		// 字段和值都加引号
		this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		// 数字不加引号
		this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, false);
		this.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, false);
		this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		// 空值处理为空串
		/*this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException,
					JsonProcessingException {
				jg.writeString("");
			}
		});*/
		this.setSerializerFactory(this.getSerializerFactory().withSerializerModifier(new MyBeanSerializerModifier()));
	}

	public static void main(String[] args) throws IOException {
		System.out.println(System.currentTimeMillis());
	}
}