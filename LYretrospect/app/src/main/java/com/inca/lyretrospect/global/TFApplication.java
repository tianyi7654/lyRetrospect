
package com.inca.lyretrospect.global;
import android.app.Activity;
import android.app.Application;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 豆腾飞
 *
 */
public class TFApplication extends Application {
	private static TFApplication instance ;
	public boolean isLogined ;
	private List<Activity> activityList = new LinkedList<Activity>();
	@Override
	public void onCreate() {
		super.onCreate();
//		initImageLoader(this);
		instance = this;
	}
	public static TFApplication getInstance() {
		if (null == instance) {
			instance = new TFApplication();
		}
		return instance;
	}
	/**
	 * 	添加启动的Activity对象
	 */
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	/**
	 * 安全退出方法
	 */
	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
	}
	//imageLoader初始化
//	public void initImageLoader(Context context) {
//		// 创建默认的ImageLoader配置参数
//		ImageLoaderConfiguration configuration = ImageLoaderConfiguration
//				.createDefault(this);
//		ImageLoader.getInstance().init(configuration);
//	}
}
