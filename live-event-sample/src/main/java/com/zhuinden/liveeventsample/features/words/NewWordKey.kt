package com.zhuinden.liveeventsample.features.words

import androidx.fragment.app.Fragment
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentKey
import kotlinx.parcelize.Parcelize

/**
 * Created by Zhuinden on 2020.
 */
@Parcelize
data class NewWordKey(val placeholder: String = "") : DefaultFragmentKey() {
    override fun instantiateFragment(): Fragment = NewWordFragment()
}
