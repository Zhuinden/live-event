package com.zhuinden.liveeventsample.features.words

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhuinden.liveeventsample.R
import com.zhuinden.liveeventsample.databinding.NewWordFragmentBinding
import com.zhuinden.liveeventsample.utils.onClick
import com.zhuinden.simplestackextensions.fragments.KeyedFragment
import com.zhuinden.simplestackextensions.fragmentsktx.lookup


/**
 * Created by Zhuinden on 2020.
 */
class NewWordFragment : KeyedFragment(R.layout.new_word_fragment) {
    interface ActionHandler {
        fun onAddWordClicked(newWordFragment: NewWordFragment, word: String)
    }

    private val actionHandler by lazy { lookup<ActionHandler>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = NewWordFragmentBinding.bind(view)

        with(binding) {
            buttonAddNewWord.onClick {
                val word = textInputNewWord.text.toString().trim()
                actionHandler.onAddWordClicked(this@NewWordFragment, word)
            }
        }
    }
}
