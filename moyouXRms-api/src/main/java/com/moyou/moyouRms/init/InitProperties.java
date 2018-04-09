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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.util.PropertiesUtil;
import com.moyou.moyouRms.util.file.CMyFileUtils;
import com.pingplusplus.Pingpp;

@Component
public class InitProperties
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void init()
    {
        CONSTANT.APP_FLAG_CURRENT_VALUE = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
        // 设置 API Key
        Pingpp.apiKey = CONSTANT.PINGXX_API_KEY;
        // 设置私钥路径，用于请求签名
        Pingpp.privateKeyPath =
            getClass().getResource("/").getPath() + "/pingxx/" + CONSTANT.PINGXX_PRIVATE_KEY_FILE_PATH;
        String pubKeyPath =
            getClass().getResource("/").getPath() + "/pingxx/" + CONSTANT.PINGXX_PUBLIC_KEY_FILE_PATH;

        try
        {
            String pubKeyString = CMyFileUtils.getPublicKeyStringFromFile(pubKeyPath);
            CONSTANT.PINGXX_WEBHOOKS_PUBKEY_STRING =
                pubKeyString.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");
        }
        catch (Exception e)
        {
            logger.error("ping++ get pubKeyString error", e);
        }
    }
}
