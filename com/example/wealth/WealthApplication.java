public class WealthApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化CacheUtils
        CacheUtils.init(this);
    }
} 