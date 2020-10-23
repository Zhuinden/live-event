package com.zhuinden.liveevent

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.zhuinden.eventemitter.EventEmitter
import org.assertj.core.api.Assertions
import org.junit.Test

class LiveEventTest {
    class FakeLifecycleOwner: LifecycleOwner {
        private val lifecycleRegistry = LifecycleRegistry(this)

        fun create() {
            lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED)
        }

        fun start() {
            lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED)
        }

        fun resume() {
            lifecycleRegistry.setCurrentState(Lifecycle.State.RESUMED)
        }

        fun pause() {
            lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED)
        }

        fun stop() {
            lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED)
        }

        fun destroy() {
            lifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED)
        }

        override fun getLifecycle(): Lifecycle = lifecycleRegistry
    }

    @Test
    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
    fun liveEventWorks() {
        val lifecycleOwner = FakeLifecycleOwner()

        val event = Object()

        val eventEmitter = EventEmitter<Object>()

        val mutableList = mutableListOf<Object>()

        lifecycleOwner.create()

        eventEmitter.observe(lifecycleOwner) {
            mutableList.add(it)
        }

        eventEmitter.emit(event)

        Assertions.assertThat(mutableList).isEmpty()

        lifecycleOwner.start()

        Assertions.assertThat(mutableList).containsExactly(event)

        lifecycleOwner.resume()

        Assertions.assertThat(mutableList).containsExactly(event)

        lifecycleOwner.pause()

        lifecycleOwner.stop()

        eventEmitter.emit(event)

        Assertions.assertThat(mutableList).containsExactly(event)

        lifecycleOwner.start()

        Assertions.assertThat(mutableList).containsExactly(event, event)

        lifecycleOwner.resume()

        lifecycleOwner.pause()

        lifecycleOwner.stop()

        lifecycleOwner.destroy()
    }
}