package op.beishi7.core.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Set;

class SpfStorageActionImpl implements IStorageAction {
    private final String DEF_SPF_NAME = "app_spf.xml";
    protected SharedPreferences mSharedPref;
    
    @Override
    public void init(Context context) {
        mSharedPref = context.getSharedPreferences(DEF_SPF_NAME, Context.MODE_PRIVATE);
    }
    
    @Override
    public void save(String key, Object data) {
        Editor editor = mSharedPref.edit();
        if (data instanceof String) {
            editor.putString(key, (String) data);
        } else if (data instanceof Integer) {
            editor.putInt(key, (Integer) data);
        } else if (data instanceof Float) {
            editor.putFloat(key, (Float) data);
        } else if (data instanceof Long) {
            editor.putLong(key, (Long) data);
        } else if (data instanceof Boolean) {
            editor.putBoolean(key, (Boolean) data);
        } else if (data instanceof Set) {
            try {
                editor.putStringSet(key, (Set<String>) data);
            } catch (Exception e) {
                //TODO exception
            }
        }
        editor.commit();
    }
    
    @Override
    public <T> T read(String key) {
        return null;
    }
    
    @Override
    public <T> T read(String key, Object defValue) {
        Object result = null;
        if (defValue instanceof Boolean) {
            result = mSharedPref.getBoolean(key, (Boolean) defValue);
        } else if (defValue instanceof Float) {
            result = mSharedPref.getFloat(key, (Float) defValue);
        } else if (defValue instanceof Integer) {
            result = mSharedPref.getInt(key, (Integer) defValue);
        } else if (defValue instanceof Long) {
            result = mSharedPref.getLong(key, (Long) defValue);
        } else if (defValue instanceof String) {
            result = mSharedPref.getString(key, (String) defValue);
        }
        return (T) result;
    }
    
    @Override
    public void remove(String key) {
        mSharedPref.edit().remove(key).commit();
    }
    
}
