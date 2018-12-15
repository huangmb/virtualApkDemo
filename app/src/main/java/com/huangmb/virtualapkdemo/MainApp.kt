package com.huangmb.virtualapkdemo

import android.app.Application
import android.content.Context
import com.didi.virtualapk.PluginManager

/**
 * Created by bob.huang on 2018/12/9.
 */
class MainApp : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        PluginManager.getInstance(base).init()
    }
}