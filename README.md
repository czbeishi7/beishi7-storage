Beishi7-storage
======
基于android应用开发常用的业务场景，封装了一套方便使用的数据存储工具。
* 支持内存缓存
* 支持Sharedpreferences存储
* 支持带内存缓存的Sharedpreferences存储
* 支持文件缓存

初始化
------
```java
CzStorageEngine.getInstance().init(Context)
```

使用说明
------
使用CzStorageEngine.getInstance().getHttpAction()获得IStorageAction发起网络请求：
``` java
IStorageAction memoryStorage = CzStorageEngine.getMemoryStorage();
IStorageAction spfStorage = CzStorageEngine.getSpfStorage(false);
IStorageAction spfWithMemoryCacheStorage = CzStorageEngine.getSpfStorage();
IStorageAction fileStorage = CzStorageEngine.getFileStorage();

public interface IStorageAction {

    void save(String key, Object data);

    <T> T read(String key);

    <T> T read(String key, Object defValue);

    void remove(String key);

}
``` 

Release
------
```
implementation("op.beishi7.core:storage:1.0.1")

repositories {
    jcenter()     
}
```

License
-------
```
Copyright 2019 beishi7, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```