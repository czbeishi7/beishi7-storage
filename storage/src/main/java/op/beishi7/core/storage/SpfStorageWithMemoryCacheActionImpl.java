package op.beishi7.core.storage;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Map;
import java.util.Set;

class SpfStorageWithMemoryCacheActionImpl extends SpfStorageActionImpl {
    private IStorageAction mMemoryCache;
    
    @Override
    public void init(Context context) {
        super.init(context);
        mMemoryCache = new MemoryStoageActionImpl();
        mMemoryCache.init(context);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Map<String, ?> cacheMap = mSharedPref.getAll();
                if (null != cacheMap && !cacheMap.isEmpty()) {
                    Set<String> keys = cacheMap.keySet();
                    for (String key : keys) {
                        saveSpfMemoryCache(key, cacheMap.get(key));
                    }
                }
            }
        });
    }
    
    @Override
    public void save(String key, Object data) {
        super.save(key, data);
        saveSpfMemoryCache(key, data);
    }
    
    @Override
    public <T> T read(String key) {
        return read(key, null);
    }
    
    @Override
    public <T> T read(String key, Object defValue) {
        return mMemoryCache.read(genSpfMemoryCacheKey(key));
    }
    
    @Override
    public void remove(String key) {
        super.remove(key);
        mMemoryCache.remove(key);
    }
    
    private void saveSpfMemoryCache(String key, Object data) {
        mMemoryCache.save(genSpfMemoryCacheKey(key), data);
    }
    
    private String genSpfMemoryCacheKey(String key) {
        return "app_spf_memory_" + key;
    }
    
}
