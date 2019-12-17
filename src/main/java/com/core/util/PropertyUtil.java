package com.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.io.*;
import java.util.Properties;
 
/**
 * Desc:properties文件获取工具类
 */
public class PropertyUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    private static Properties props;
 
    public synchronized static Object getPropertyValue(String fileName,String key){
        logger.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;
        try {
        	//第一种，通过类加载器进行获取properties文件流
            in = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName);
            //第二种，通过类进行获取properties文件流
            //in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error(fileName+"文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error(fileName+"文件流关闭出现异常");
            }
        }
        logger.info("加载properties文件内容完成...........");
        logger.info("properties文件内容：" + props);
        return props.getProperty(key);
    }
    
    public synchronized static Properties getProperty(String fileName){
    	logger.info("开始加载properties文件内容.......");
    	props = new Properties();
    	InputStream in = null;
    	try {
    		//第一种，通过类加载器进行获取properties文件流
    		in = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName);
    		//第二种，通过类进行获取properties文件流
    		//in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
    		props.load(in);
    	} catch (FileNotFoundException e) {
    		logger.error(fileName+"文件未找到");
    	} catch (IOException e) {
    		logger.error("出现IOException");
    	} finally {
    		try {
    			if(null != in) {
    				in.close();
    			}
    		} catch (IOException e) {
    			logger.error(fileName+"文件流关闭出现异常");
    		}
    	}
    	logger.info("加载properties文件内容完成...........");
    	logger.info("properties文件内容：" + props);
    	return props;
    }
}
