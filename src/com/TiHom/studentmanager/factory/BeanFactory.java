package com.TiHom.studentmanager.factory;

import java.util.ResourceBundle;

/**
 * 2018/3/29 15:58
 * 工厂创建Dao或Service实例
 * @author TiHom
 */
public class BeanFactory {
    //加载配置文件
    private static ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle("instance");
    }

    /**
     * 根据指定的key获取配置文件中对应类的全路径，创建对象
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getInstance(String key,Class<T> clazz){
        //获取配置信息
        String className = bundle.getString(key);
        try {
            return (T) Class.forName(className).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
