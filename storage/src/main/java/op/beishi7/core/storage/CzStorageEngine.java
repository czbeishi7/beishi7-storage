package op.beishi7.core.storage;

import android.content.Context;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

public class CzStorageEngine {
    
    private static CzStorageEngine sInstance;
    
    public static CzStorageEngine getInstance() {
        if (null == sInstance) {
            sInstance = new CzStorageEngine();
        }
        return sInstance;
    }
    
    public static IStorageAction getMemoryStorage() {
        return getInstance().getStorageAction(STORAGE_CHANNEL_MEMORY);
    }
    
    public static IStorageAction getSpfStorage() {
        return getSpfStorage(true);
    }
    
    public static IStorageAction getSpfStorage(boolean withMemoryCache) {
        return getInstance().getStorageAction(withMemoryCache ? STORAGE_CHANNEL_SPF_WITH_MEMORY_CACHE : STORAGE_CHANNEL_SPF);
    }
    
    public static IStorageAynscAction getFileStorage() {
        return (IStorageAynscAction) getInstance().getStorageAction(STORAGE_CHANNEL_FILE);
    }
    
    public static final String STORAGE_CHANNEL_MEMORY = "storage_channel_memory";
    public static final String STORAGE_CHANNEL_SPF = "storage_channel_spf";
    public static final String STORAGE_CHANNEL_SPF_WITH_MEMORY_CACHE = "storage_channel_spf_with_memory_cache";
    public static final String STORAGE_CHANNEL_FILE = "storage_channel_file";
    
    @StringDef({STORAGE_CHANNEL_MEMORY, STORAGE_CHANNEL_SPF, STORAGE_CHANNEL_SPF_WITH_MEMORY_CACHE, STORAGE_CHANNEL_FILE})
    @Retention(RetentionPolicy.SOURCE)
    private @interface StorageChannel {}
    
    private Context mContext;
    private Map<String, IStorageAction> mStorageImpls;
    
    private CzStorageEngine() {
        mStorageImpls = new HashMap<>();
        mStorageImpls.put(STORAGE_CHANNEL_MEMORY, new MemoryStoageActionImpl());
        mStorageImpls.put(STORAGE_CHANNEL_SPF, new SpfStorageActionImpl());
        mStorageImpls.put(STORAGE_CHANNEL_SPF_WITH_MEMORY_CACHE, new SpfStorageWithMemoryCacheActionImpl());
        mStorageImpls.put(STORAGE_CHANNEL_FILE, new FileStorageActionImpl());
    }
    
    public void init(Context context) {
        mContext = context.getApplicationContext();
    }
    
    Context getContext() {
        return mContext;
    }
    
    public IStorageAction getStorageAction(@StorageChannel String storageChannel) {
        return mStorageImpls.get(storageChannel);
    }
    
}
