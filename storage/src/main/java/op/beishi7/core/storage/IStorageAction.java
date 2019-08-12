package op.beishi7.core.storage;

import android.content.Context;

public interface IStorageAction {

    void init(Context context);

    void save(String key, Object data);

    <T> T read(String key);

    <T> T read(String key, Object defValue);

    void remove(String key);

}
