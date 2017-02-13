package xg.cn.customerdrawerrecycler.app;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * @author yuhuibin
 * @date 2016-05-12
 */
public class Application extends android.app.Application{

    private RefWatcher mRefWatcher;

    private static Application sApp;

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化Fresco
        Fresco.initialize(this);
        mRefWatcher = LeakCanary.install(this);

        sApp = this;
    }

    public static Application getAppInstance() {
        return sApp;
    }
}
