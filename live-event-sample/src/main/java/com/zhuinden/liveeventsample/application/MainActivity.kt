package com.zhuinden.liveeventsample.application

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.zhuinden.simplestack.History
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.navigator.Navigator
import com.zhuinden.liveeventsample.R
import com.zhuinden.liveeventsample.databinding.ActivityMainBinding
import com.zhuinden.liveeventsample.features.words.WordListKey
import com.zhuinden.simplestack.SimpleStateChanger
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentStateChanger
import com.zhuinden.simplestackextensions.services.DefaultServiceProvider

/**
 * Created by Zhuinden on 2020.
 */
class MainActivity : AppCompatActivity(), SimpleStateChanger.NavigationHandler {
    private lateinit var fragmentStateChanger: DefaultFragmentStateChanger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.bind(findViewById(Window.ID_ANDROID_CONTENT))

        fragmentStateChanger = DefaultFragmentStateChanger(supportFragmentManager, R.id.fragmentRoot)

        Navigator.configure()
            .setStateChanger(SimpleStateChanger(this))
            .setScopedServices(DefaultServiceProvider())
            .install(this, binding.fragmentRoot, History.of(WordListKey()))
    }

    override fun onBackPressed() {
        if (!Navigator.onBackPressed(this)) {
            super.onBackPressed()
        }
    }

    override fun onNavigationEvent(stateChange: StateChange) {
        fragmentStateChanger.handleStateChange(stateChange)
    }
}
