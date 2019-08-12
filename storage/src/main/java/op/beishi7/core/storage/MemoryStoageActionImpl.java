package op.beishi7.core.storage;

import android.content.Context;
import android.text.TextUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class MemoryStoageActionImpl implements IStorageAction {
    
    private Map<String, Object> mMemoryCacheMap = new ConcurrentHashMap<>();
    
    @Override
    public void init(Context context) {}
    
    @Override
    public void save(String key, Object data) {
        if (!TextUtils.isEmpty(key)) {
            if (null != data) {
                mMemoryCacheMap.put(key, data);
            } else {
                remove(key);
            }
        }
    }
    
    @Override
    public <T> T read(String key) {
        return (T) mMemoryCacheMap.get(key);
    }
    
    @Override
    public <T> T read(String key, Object defValue) {
        return (T) (mMemoryCacheMap.containsKey(key) ? read(key) : defValue);
    }
    
    @Override
    public void remove(String key) {
        if (mMemoryCacheMap.containsKey(key)) {
            mMemoryCacheMap.remove(key);
        }
    }
    
}
