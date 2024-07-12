# Animated Flip View

Animated Flip View is a dynamic and easy-to-use Android library that lets you flip views with smooth animations. You can flip views to the front or back in any direction and set custom durations for the animations. This library provides an intuitive way to add engaging visual transitions to your app, enhancing user experience without complex coding.

```

Min SDK           : 21 - (Android – 5.0 Lollipop)
Max(Target) SDK   : 34 - (Android - 14.0 UPSIDE_DOWN_CAKE)
Language          : Kotlin
Latest Version    : 2.0.2
License           : Apache 2.0
Repository        : JitPack

```

## Animated Flip View Demo
![Example GIF](images/animatedFlipViewDemo.gif)

## Key Features

- **Lightweight and Easy to Use:** Enables smooth view flipping with minimal coding effort.
- **Flexible Flip Directions:** Supports flipping views in any direction—left, right, up, or down.
- **Customizable Animations:** You can set custom durations and easing functions for animations.



## Get Started


> Step 1. Add the JitPack repository to your build file

```
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://www.jitpack.io' }
		}
	}
```
> Step 2. Add the dependency

```
dependencies {
	        implementation 'com.github.HasnathJami:AnimatedFlipView:version'
	}
```

## Implementation
> Add this custom view to your layout or single-row design
```xml
    <com.hasnath.jami.animated_flip_view.afv_custom_view.AnimatedFlipView
        android:id="@+id/animatedFlipView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:frontFlipDuration="800"
        app:backFlipDuration="800"
        app:flipDirection="RIGHT_TO_LEFT"/>
```
## Attributes (XML)
flipDirection = {RIGHT_TO_LEFT, LEFT_TO_RIGHT, TOP_TO_BOTTOM, BOTTOM_TO_TOP}

> Use the following code in your Activity

```kotlin
class YourActivity : AppCompatActivity() {

    private lateinit var flipView: AnimatedFlipView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your)

        flipView = findViewById(R.id.animatedFlipView)

        // Set your front layout
        val frontView = layoutInflater.inflate(R.layout.your_front_side_layout, null)

        // Set your back layout
        val backView = layoutInflater.inflate(R.layout.your_back_side_layout, null)

        // Add front layout to parent view
        flipView.setFrontView(frontView)

        // Add back layout to parent view
        flipView.setBackView(backView)

        /* Control the front view flipping with view clicking */
        frontView.findViewById<Your_View>(R.id.view_id).setOnClickListener {
            flipView.flipToBack()
        }

        /* Control the back view flipping with view clicking */
        backView.findViewById<Your_View>(R.id.view_id).setOnClickListener {
            flipView.flipToFront()
        }

         //OR
        /* Handle the auto back view flipping */
//        frontView.findViewById<Your_View>(R.id.view_id).setOnClickListener {
//        flipView.startAutoBackFlippingWithIntervals(delay = 1500)
        }
    }

   // Release the handler when the screen destroys
    override fun onDestroy() {
        super.onDestroy()
        flipView.removeHandler()

    }
}



```

> or Use the following code in your Fragment

```kotlin
class YourFragment : Fragment() {

    private lateinit var flipView: AnimatedFlipView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_your, container, false)
        flipView = view.findViewById(R.id.animatedFlipView)

        // Set your front layout
        val frontView = inflater.inflate(R.layout.your_front_side_layout, null)

        // Set your back layout
        val backView = inflater.inflate(R.layout.your_back_side_layout, null)

        // Add the front layout to parent view
        flipView.setFrontView(frontView)

        // Add the back layout to parent view
        flipView.setBackView(backView)

        /* Control the front view flipping with view clicking */
        frontView.findViewById<Your_View>(R.id.view_id).setOnClickListener {
            flipView.flipToBack()
        }
         /* Control the back view flipping with view clicking */
        backView.findViewById<Your_View>(R.id.view_id).setOnClickListener {
            flipView.flipToFront()
        }

        //OR
        /* Handle the auto back view flipping */
 //       frontView.findViewById<Your_View>(R.id.view_id).setOnClickListener {
 //           flipView.startAutoBackFlippingWithIntervals(delay = 1500)
 //       }

        return view
    }

    // Release the handler when the fragment view destroys
    override fun onDestroyView() {
        super.onDestroyView()
        flipView?.removeHandler()
    }

}
```

> or Use the following code in your Recyclerview
```kotlin
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val flipView: AnimatedFlipView = itemView.findViewById(R.id.animatedFlipView)

        // Set your front layout
        private val frontView: View = LayoutInflater.from(itemView.context).inflate(R.layout.your_front_side_layout, null)

        // Set your back layout
        private val backView: View = LayoutInflater.from(itemView.context).inflate(R.layout.your_back_side_layout, null)

        init {
            // Set your front layout
            flipView.setFrontView(frontView)

            // Set your back layout
            flipView.setBackView(backView)

            /* Control the front view flipping with view clicking */
            frontView.findViewById<Your_View>(R.id.view_id).setOnClickListener {
                flipView.flipToBack()
            }

            /* Control the back view flipping with view clicking */
            backView.findViewById<Your_View>(R.id.view_id).setOnClickListener {
                flipView.flipToFront()
            }

             //OR
             /* Handle the auto back view flipping */          
//            frontView.findViewById<Your_View>(R.id.view_id).setOnClickListener {
//                flipView.startAutoBackFlippingWithIntervals(delay = 1500)
//            }
        }

        fun bind(item: Item) {
            // Bind data to your views here
            // Add other bindings as needed
        }
    }

```

## Contribution

Contributions to the Animated Flip View library are welcome! Enhance the library with bug fixes, new features, or improvements. Your input makes it better for everyone. Visit the GitHub repository to collaborate with fellow developers.

## Licence

Animated Flip View is under Apache 2.0 License, permitting personal and commercial use. You can use, modify, and distribute the library per the license terms.

```
Copyright 2024 Hasnath Jami Chowdhury

Licensed under the Apache License, Version 2.0 (the "License");
You won't be using this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```
