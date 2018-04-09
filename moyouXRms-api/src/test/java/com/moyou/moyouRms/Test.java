/**
 */
package com.moyou.moyouRms;

import org.apache.log4j.Logger;


/**
 * @describe TODO
 * @author liuxinyi
 * @date 2017年2月21日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public class Test  {
	protected static Logger logger = Logger.getLogger(Test.class);
	public static void main(String[] args) {
		logger.debug("This is a debug message!");
        logger.info("This is info message!");
        logger.warn("This is a warn message!");
        logger.error("This is error message!");
        try{
           System.out.println(5/0);
        }catch(Exception e){
            logger.error(e);
        }

	}
}
