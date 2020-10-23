package com.zhuinden.liveeventsample.features.words

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhuinden.liveevent.observe
import com.zhuinden.liveeventsample.R
import com.zhuinden.liveeventsample.databinding.WordListFragmentBinding
import com.zhuinden.liveeventsample.utils.*
import com.zhuinden.simplestackextensions.fragments.KeyedFragment
import com.zhuinden.simplestackextensions.fragmentsktx.lookup

/**
 * Created by Zhuinden on 2020.
 */

class WordListFragment : KeyedFragment(R.layout.word_list_fragment) {
    interface ActionHandler {
        fun onAddNewWordClicked(wordListFragment: WordListFragment)
    }

    interface DataProvider {
        val wordList: LiveData<List<String>>
    }

    private val actionHandler by lazy { lookup<ActionHandler>() }
    private val dataProvider by lazy { lookup<DataProvider>() }
    private val wordList by lazy { dataProvider.wordList }

    private val controllerEvents by lazy { lookup<WordEventEmitter>() }

    val adapter = WordListAdapter()

    @Suppress("NAME_SHADOWING")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = WordListFragmentBinding.bind(view)

        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter

            buttonGoToAddNewWord.onClick { view ->
                actionHandler.onAddNewWordClicked(this@WordListFragment)
            }

            wordList.observe(viewLifecycleOwner) { words ->
                adapter.updateWords(words!!)
            }

            controllerEvents.observe(viewLifecycleOwner) { event: WordController.Events ->
                when (event) {
                    is WordController.Events.NewWordAdded -> showToast("Added ${event.word}")
                }.safe()
            }
        }
    }
}