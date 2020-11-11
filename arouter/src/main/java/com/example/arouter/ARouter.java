package com.example.arouter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Map;

/**
 * <pre>
 *     author: Jafar
 *     date  : 2020/11/11
 *     desc  : 路由表
 *     App启动之后就要存在
 *     使用单例比较好
 * </pre>
 */
public class ARouter {

    private static ARouter aRouter = new ARouter();

    // 装载Activity的容器
    private Map<String, Class<? extends Activity>> map;
    private Context mContext;

    private ARouter(){}

    /**
     * 单例
     * @return
     */
    public static ARouter getInstance() {
        return aRouter;
    }

    /**
     * 初始化上下文
     * @param context
     */
    public void init(Context context) {
        mContext = context;
    }

    /**
     * 添加Activity进去
     * @param key
     * @param clazz
     */
    public void addActivity(String key, Class<? extends Activity> clazz) {
        if (key != null && clazz != null && !map.containsKey(key)) {
            map.put(key, clazz);
        }
    }

    /**
     * 获取某个Activity
     * @param key
     * @return
     */
    public Class getActivity(String key) {
        Class<? extends Activity> aClass = map.get(key);
        return aClass;
    }

    /**
     * 跳转到某个Activity
     */
    public void jumpActivity(String key, Bundle bundle) {
        Class<? extends Activity> activityClazz = map.get(key);
        if (activityClazz != null) {
            Intent intent = new Intent(mContext, activityClazz);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            mContext.startActivity(intent);
        }
    }
}
