package com.smarttoolfactory.datastructuresandalgorithmsplayground

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     val v: View =   findViewById<View>(R.id.view1)

        val root: ViewGroup =
            findViewById<ViewGroup>(android.R.id.content)?.getChildAt(0) as ViewGroup

        val view: View? = findViewByMyId(root, R.id.view3)
        println("🔥ROOT View: $root, VIEW: $view")

     val anotherView  = findViewByMyIdGeneric<View>(root, R.id.view4)



    }
}

fun findViewByMyId(rootView: ViewGroup, id: Int): View? {

    if (rootView.id == id) return rootView

    val childCount = rootView.childCount

    for (i in 0..childCount) {
        val childView: View? = rootView.getChildAt(i)

        childView?.let { child ->
            if (child.id == id) {
                return childView
            }

            if (child is ViewGroup) {
                val currentView = findViewByMyId(child, id)
                if (currentView != null) return currentView
            }
        }
    }

    return null
}

fun <T:View> findViewByMyIdGeneric(rootView: ViewGroup, id: Int): T? {

    if (rootView.id == id) return rootView as T

    val childCount = rootView.childCount

    for (i in 0..childCount) {
        val childView: View? = rootView.getChildAt(i)

        childView?.let { child ->
            if (child.id == id) {
                return childView as T
            }

            if (child is ViewGroup) {
                val currentView = findViewByMyId(child, id)
                if (currentView != null) return currentView as T
            }
        }
    }

    return null
}