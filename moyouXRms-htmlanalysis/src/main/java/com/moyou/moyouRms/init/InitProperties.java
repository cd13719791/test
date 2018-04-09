/**  
 * @Title:  InitProperties.java   
 * @Package com.moyou.moyouRms.init   
 * @Description:    初始化配置文件内容
 * @author: liuxinyi
 * @date:   2017年6月22日 上午11:44:32   
 * @email liuxinyi@mousns.com
 * @version V1.0 
 */
package com.moyou.moyouRms.init;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.util.PropertiesUtil;

@Component
public class InitProperties
{
//    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void init()
    {
        CONSTANT.APP_FLAG_CURRENT_VALUE = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
        /**
    	 * 七牛
    	 */
    		CONSTANT.IMAGE_URL = getInitString("qiniu_http_image_url");
    }
    /**
	 * 获取初始化 字符串值
	 * @param key
	 * @return
	 */
	private String getInitString(String key) {
		if (CONSTANT.APP_FLAG_CURRENT_VALUE.equals(CONSTANT.APP_FLAG_OFFICIAL_VALUE)) {
			return  PropertiesUtil.getValueByKey(key);
		}
		return PropertiesUtil.getValueByKey(key+"_test");
	}
}
