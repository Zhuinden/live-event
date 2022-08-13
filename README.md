# Live-Event

The `LiveEvent` helper class allows you to observe events from an `EventEmitter` with a lifecycle-aware observer.

Using the `LifecycleOwner` passed to `observe`, it will automatically stop listening when the Lifecycle is destroyed.

You can only emit events and listen for events on the thread where you created the `EventEmitter`.

## Example

``` kotlin
// write
private val emitter: EventEmitter<String> = EventEmitter()
val events: EventSource<String> = emitter

fun doSomething() {
    emitter.emit("hello")
}

// read
fun observe() {
    events.observe(lifecycleOwner) { event ->
        showToast(event)
    }
}
```


## Using Live Event

In order to use Live Event, you need to add jitpack to your project root gradle:

    buildscript {
        repositories {
            // ...
            maven { url "https://jitpack.io" }
        }
        // ...
    }
    allprojects {
        repositories {
            // ...
            maven { url "https://jitpack.io" }
        }
        // ...
    }

In newer projects, you need to also update the `settings.gradle` file's `dependencyResolutionManagement` block:

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }  // <--
        jcenter() // Warning: this repository is going to shut down soon
    }
}
```



and add the compile dependency to your module level gradle.

    implementation 'com.github.Zhuinden:live-event:1.3.0'

## License

    Copyright 2020 Gabor Varadi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
