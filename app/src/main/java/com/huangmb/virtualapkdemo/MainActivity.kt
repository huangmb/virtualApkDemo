package com.huangmb.virtualapkdemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.didi.virtualapk.PluginManager
import com.didi.virtualapk.internal.utils.PluginUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

private const val PLUGIN_NAME = "pluginA.apk"
private const val PLUGIN_PKG = "com.huangmb.plugin.a"
private const val PLUGIN_ACTIVITY = "com.huangmb.plugin.a.ScrollingActivity"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPlugin()
        button.setOnClickListener {
            if (checkPlugin()) {
                val i = Intent()
                i.setClassName(PLUGIN_PKG, PLUGIN_ACTIVITY)
                startActivity(i)
            }
        }
    }

    private fun checkPlugin(): Boolean {
        PluginManager.getInstance(this).getLoadedPlugin(PLUGIN_PKG) ?: return loadPlugin()
        return true
    }


    private fun loadPlugin(): Boolean {
        val apk = File(externalCacheDir, PLUGIN_NAME)
        if (apk.exists()) {
            toast("加载插件")
            val manager = PluginManager.getInstance(this)
            manager.loadPlugin(apk)
            PluginUtil.hookActivityResources(this, PLUGIN_PKG)
            return true
        }
        toast("插件不存在")
        return false

    }

    private fun toast(msg: CharSequence) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
