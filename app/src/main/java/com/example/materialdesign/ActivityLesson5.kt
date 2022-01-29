package com.example.materialdesign

import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.ArcMotion
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.activity_animations_path_transitions_lesson5.*

class ActivityLesson5 : AppCompatActivity() {

    private var textIsVisible = false
    private var isExpanded = false
    private var toRightAnimation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_lesson5)
        //setContentView(R.layout.activity_animations_enlarge_lesson5)
        setContentView(R.layout.activity_animations_path_transitions_lesson5)
        button.setOnClickListener {
            val changeBounds = ChangeBounds()
            changeBounds.setPathMotion(ArcMotion())
            changeBounds.duration = 500
            TransitionManager.beginDelayedTransition(
                transitions_container,
                changeBounds
            )

            toRightAnimation = !toRightAnimation
            val params = button.layoutParams as FrameLayout.LayoutParams
            params.gravity =
                if (toRightAnimation) {
                    Gravity.END or Gravity.BOTTOM
                } else {
                    Gravity.START or Gravity.TOP
                }
            button.layoutParams = params
        }
    }

    // остальные методы делал для теста, не стал делать оптимизацию и рефакторинг
    // увеличение картинки
    /*
        image_view.setOnClickListener {
            isExpanded = !isExpanded
            TransitionManager.beginDelayedTransition(
                container, TransitionSet()
                    .addTransition(ChangeBounds())
                    .addTransition(ChangeImageTransform())
            )

            val params: ViewGroup.LayoutParams = image_view.layoutParams
            params.height =
                if (isExpanded) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
            image_view.layoutParams = params
            image_view.scaleType =
                if (isExpanded) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER
        }
     */
    // разлетаются кнопки
    //recycler_view.adapter = Adapter()
    /*
    button.setOnClickListener {
        // направление выпада текста
        TransitionManager.beginDelayedTransition(transitions_container,Slide(Gravity.BOTTOM))
        textIsVisible = !textIsVisible
        text.visibility = if (textIsVisible) View.VISIBLE else View.GONE
    }

}
*/

/*
    private fun explode(clickedView: View) {
        val viewRect = Rect()
        clickedView.getGlobalVisibleRect(viewRect)
        val explode = Explode()
        explode.epicenterCallback = object : Transition.EpicenterCallback() {
            override fun onGetEpicenter(transition: Transition): Rect {
                return viewRect
            }
        }
        explode.excludeTarget(clickedView, true)
        val set = TransitionSet()
            .addTransition(explode)
            .addTransition(Fade().addTarget(clickedView))
            .addListener(object : TransitionListenerAdapter() {
                override fun onTransitionEnd(transition: Transition) {
                    transition.removeListener(this)
                    onBackPressed()
                }
            })
        TransitionManager.beginDelayedTransition(recycler_view, set)
        recycler_view.adapter = null
    }



    inner class Adapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.lesson5_recycler_view_item,
                    parent,
                    false
                ) as View
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                explode(it)
            }
        }

        override fun getItemCount(): Int {
            return 32
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

 */
}
