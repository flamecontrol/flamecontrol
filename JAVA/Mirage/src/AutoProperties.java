package src;


import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;

/**
 * <p>类名称: [类中文名称]</p>
 * <p>类描述: [类功能中文描述]</p>
 * <p>所属模块: 语音服务系统_[模块名称]</p>
 * <p>创建时间 11-8-31 上午11:13 </p> 
 * @author mali li-ma@neusoft.com
 * @version 1.0
*/
public class AutoProperties {
    /**
     * <p>属性描述: [属性描述]</p>
     */

    /**
     * <p>属性描述: [属性描述]</p>
     */
    private static Properties prop = null;

    /**
     * <p>方法描述: [方法功能中文描述]</p>
    *
    * @return 返回结果的说明
    */
    public static synchronized Properties getInstance() {
        if (prop == null) { //第一次使用，加载
            AutoProperties.reload();
        }

        return prop;
    }

    /**
     * <p>方法描述: [方法功能中文描述]</p>
    */
    public static void reload() {
        prop = new Properties();

        FileInputStream in = null;

        try {
            in = new FileInputStream("setting.ini");
            prop.load(in);
            in.close();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {

                }
            }
        }
    }
}
