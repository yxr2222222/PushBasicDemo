## README
该项目是为了帮助开发者快速接入Umeng基础功能和推送功能。

### 安装说明
1. 编译环境

```groovy
Gradle:       7.2
JDK           11
```

2. 内部包含引用

```groovy
// 友盟基础库
api 'com.umeng.umsdk:common:9.6.4'
api 'com.umeng.umsdk:asms:1.8.0'
api 'com.umeng.umsdk:apm:1.9.3'

// 推送基础库（只有依赖推送模块才会引入）
api 'com.umeng.umsdk:push:6.6.2'
api 'com.umeng.umsdk:uyumao:1.1.2'

// 各个推送厂商渠道库（只有依赖各个厂商渠道推送模块才会引入）
// 小米
api 'com.umeng.umsdk:xiaomi-umengaccs:2.0.0'
api 'com.umeng.umsdk:xiaomi-push:5.6.2'

// 华为
api 'com.umeng.umsdk:huawei-umengaccs:2.0.0'
api 'com.huawei.hms:push:6.9.0.300'

// oppo
api 'com.umeng.umsdk:oppo-umengaccs:2.0.0'
api 'com.umeng.umsdk:oppo-push:3.1.0'

// vivo
api 'com.umeng.umsdk:vivo-umengaccs:2.0.0'
api 'com.umeng.umsdk:vivo-push:3.0.0.4'

// 荣耀
api 'com.umeng.umsdk:honor-umengaccs:2.0.0'
api 'com.umeng.umsdk:honor-push:7.0.41.301'

// 魅族
api 'com.umeng.umsdk:meizu-umengaccs:2.0.0'
api 'com.umeng.umsdk:meizu-push:4.2.3'
```

3. 依赖集成 
在项目根目录的gradle中添加
```groovy
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        // 如果接了华为渠道推送需要添加
        classpath 'com.huawei.agconnect:agcp:1.6.0.300'
    }
    repositories {
        // jitpack仓库地址
        maven { url 'https://jitpack.io' }

        // 如果接了华为渠道推送
        maven { url 'https://developer.huawei.com/repo/'}
    }
}

allprojects {
    repositories {
        // jitpack仓库地址
        maven { url 'https://jitpack.io' }

        // 如果接了华为渠道推送
        maven { url 'https://developer.huawei.com/repo/'}
        maven { url 'https://repo1.maven.org/maven2/' }
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
```
添加依赖
```groovy
// 友盟基础服务（必须，统计埋点 + 性能监控）
implementation 'com.github.yxr2222222.PushBasicDemo:umeng:v1.0.4'

// 友盟推送基础服务（可选，不需要推送服务可不选）
implementation 'com.github.yxr2222222.PushBasicDemo:pushbasic:v1.0.4'

// 友盟推送厂商渠道服务（可选，需要哪些厂商渠道就选）
implementation 'com.github.yxr2222222.PushBasicDemo:xiaomi:v1.0.4'
implementation 'com.github.yxr2222222.PushBasicDemo:huawei:v1.0.4'
implementation 'com.github.yxr2222222.PushBasicDemo:oppo:v1.0.4'
implementation 'com.github.yxr2222222.PushBasicDemo:vivo:v1.0.4'
implementation 'com.github.yxr2222222.PushBasicDemo:honor:v1.0.4'
implementation 'com.github.yxr2222222.PushBasicDemo:meizu:v1.0.4'
```

### 使用说明
1. AndroidManifest.xml厂商渠道推送配置（可选，如果需要）
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.yxr.pushbasicdemo">

    <application>
        <!-- 小米推送配置 -->
        <meta-data android:name="xiaomi_id" android:value="替换成自己的小米Id"/>
        <meta-data android:name="xiaomi_key" android:value="替换成自己的小米Key"/>

        <!-- 华为推送配置 -->
        <meta-data android:name="com.huawei.hms.client.appid" android:value="appid=替换成自己的华为AppId"/>

        <!-- Oppo推送配置 -->
        <meta-data android:name="oppo_appkey" android:value="替换成自己的Oppo的AppKey"/>
        <meta-data android:name="oppo_appsecret" android:value="替换成自己的Oppo的AppSecret"/>

        <!-- Vivo推送配置 -->
        <meta-data android:name="com.vivo.push.app_id" android:value="替换成自己的Vivo的AppId"/>
        <meta-data android:name="com.vivo.push.api_key" android:value="替换成自己的Vivo的AppKey"/>

        <!-- Honor推送配置 -->
        <meta-data android:name="com.hihonor.push.app_id" android:value="appid=替换成自己的荣耀AppId"/>

        <!-- 魅族推送配置 -->
        <meta-data android:name="meizu_appid" android:value="替换成自己的魅族AppId"/>
        <meta-data android:name="meizu_appkey" android:value="替换成自己的魅族AppKey"/>
    </application>

</manifest>
```
2. 预初始化，没有同意隐私之前即可进行预初始化
```java
package com.yxr.pushbasicdemo.ui;

import android.app.Application;

import androidx.annotation.NonNull;

import com.umeng.message.api.UPushRegisterCallback;
import com.yxr.pushbasic.callback.OnNotifyClickCallback;
import com.yxr.pushbasicdemo.BuildConfig;

import com.yxr.pushbasic.PushManager;
import com.yxr.pushbasic.config.PushConfig;
import com.yxr.umeng.UmengManager;
import com.yxr.umeng.config.UmengConfig;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 如果只集成了友盟基础服务，则只需要进行基础服务预初始化
//        UmengManager.getInstance().preInit(this, new UmengConfig.Builder()
//                .setAppKey("友盟AppKey")
//                .setChannel("友盟渠道")
//                .setMessageSecret("友盟MessageSecret")
//                .setDebug(BuildConfig.DEBUG)
//                .build());


        // 如果集成了友盟推送服务，则需要进行推送服务预初始化（内部会初始化友盟基础服务）
        PushManager.getInstance().preInit(this, new PushConfig.Builder()
                .setUmengConfog(new UmengConfig.Builder()
                        .setAppKey("友盟AppKey")
                        .setChannel("友盟渠道")
                        .setMessageSecret("友盟MessageSecret")
                        .setDebug(BuildConfig.DEBUG)
                        .build())
                .setPushRegisterCallback(new UPushRegisterCallback() {
                    @Override
                    public void onSuccess(String deviceToken) {
                        UmengManager.getInstance().log("onSuccess deviceToken: " + deviceToken);
                    }

                    @Override
                    public void onFailure(String errCode, String errDesc) {
                        UmengManager.getInstance().log("onFailure errCode: " + errCode + ", errDesc: " + errDesc);
                    }
                })
                .setOnNotifyClickCallback(new OnNotifyClickCallback() {
                    @Override
                    public void onNotifyClick(@NonNull String body) {
                        // TODO 推送通知栏被点击
                        UmengManager.getInstance().log("onNotifyClick body: " + body);
                    }
                })
                .build());
    }
}

```
3. 初始化，在用户同意隐私政策之后即可进行初始化；
```java
package com.yxr.pushbasicdemo.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yxr.pushbasic.PushManager;
import com.yxr.pushbasicdemo.R;
import com.yxr.umeng.UmengManager;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 如果只集成了友盟基础服务，则只需要进行基础服务初始化
//        UmengManager.getInstance().init();


        // 如果集成了友盟推送服务，则需要进行推送服务初始化（内部会初始化友盟基础服务）
        PushManager.getInstance().init();
    }
}
```

4. 埋点
```java
UmengManager.getInstance().track(eventID, map);
```
