/**
 */
package com.moyou.moyouRms.util.json;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

/**
 * @describe TODO
 * @author liuxinyi
 * @date 2017年4月21日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public class MyBeanSerializerModifier extends BeanSerializerModifier {

    private JsonSerializer<Object> _nullArrayJsonSerializer = new MyNullArrayJsonSerializer();

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
            List<BeanPropertyWriter> beanProperties) {
        // 循环所有的beanPropertyWriter
        for (int i = 0; i < beanProperties.size(); i++) {
            BeanPropertyWriter writer = beanProperties.get(i);
            // 判断字段的类型，如果是array，list，set则注册nullSerializer
            if (isArrayType(writer)) {
                 //给writer注册一个自己的nullSerializer
                writer.assignNullSerializer(this.defaultNullArrayJsonSerializer());
            } else if (isIntType(writer)) {
            	  writer.assignNullSerializer(new JsonSerializer<Object>() {
          			@Override
        			public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException,
        					JsonProcessingException {
        				jg.writeObject(0);
        			}
        		});
            } else if (isDoubleType(writer)) {
          	  writer.assignNullSerializer(new JsonSerializer<Object>() {
        			@Override
      			public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException,
      					JsonProcessingException {
      				jg.writeObject(0.0);
      			}
      		});
          }  else  {
          	  writer.assignNullSerializer(new JsonSerializer<Object>() {
        			@Override
      			public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException,
      					JsonProcessingException {
      				jg.writeString("");
      			}
      		});
          }
        }
        return beanProperties;
    }

    // 判断是什么类型
   	protected boolean isArrayType(BeanPropertyWriter writer) {
   		Class<?> clazz = writer.getType().getRawClass();
           return clazz.isArray() || clazz.equals(List.class) || clazz.equals(Set.class);

       }
   	protected boolean isIntType(BeanPropertyWriter writer) {
   		Class<?> clazz = writer.getType().getRawClass();
       	return clazz.equals(Short.class) || clazz.equals(Integer.class) || clazz.equals(Long.class);
       	
       }
   	protected boolean isDoubleType(BeanPropertyWriter writer) {
   		Class<?> clazz = writer.getType().getRawClass();
   		return clazz.equals(Double.class);
   		
   	}
    protected JsonSerializer<Object> defaultNullArrayJsonSerializer() {
        return _nullArrayJsonSerializer;
    }
}
