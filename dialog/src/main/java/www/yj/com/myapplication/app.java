package www.yj.com.myapplication;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * 创建者：admin
 * <p>时间：2017/3/15 09:41
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}
