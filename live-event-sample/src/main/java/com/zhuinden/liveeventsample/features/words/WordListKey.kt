package com.zhuinden.liveeventsample.features.words

import androidx.fragment.app.Fragment
import com.zhuinden.simplestack.ServiceBinder
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentKey
import com.zhuinden.simplestackextensions.services.DefaultServiceProvider
import com.zhuinden.simplestackextensions.servicesktx.add
import com.zhuinden.simplestackextensions.servicesktx.get
import com.zhuinden.simplestackextensions.servicesktx.rebind
import kotlinx.android.parcel.Parcelize

/**
 * Created by Zhuinden on 2020.
 */
@Parcelize
data class WordListKey(val placeholder: String = "") : DefaultFragmentKey(), DefaultServiceProvider.HasServices {
    override fun instantiateFragment(): Fragment = WordListFragment()

    override fun getScopeTag(): String = fragmentTag

    override fun bindServices(serviceBinder: ServiceBinder) {
        with(serviceBinder) {
            add(WordController(backstack))
            rebind<WordListFragment.DataProvider>(get<WordController>())
            rebind<WordListFragment.ActionHandler>(get<WordController>())
            rebind<NewWordFragment.ActionHandler>(get<WordController>())
            add(get<WordController>().eventEmitter)
        }
    }
}
