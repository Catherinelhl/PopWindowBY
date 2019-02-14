package popwindowby.bcaas.io

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.PopupWindow

/**
 *
 * @since 2019/2/13
 *
 * @author catherine.brainwilliam
 *
 * @version
 *
 */
class TestPopWindow : PopupWindow {
    private var context: Context?
//    private var alpha = 1f//背景灰度 0-1，1表示全透明

    constructor(context: Context) : super(context) {
        this.context = context
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        width = ViewGroup.LayoutParams.WRAP_CONTENT
        isOutsideTouchable = true
        isFocusable = true
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        contentView = LayoutInflater.from(context).inflate(
            R.layout.pop_test,
            null, false
        )
    }

    /**
     * 控制窗口背景的不透明度
     */
    private fun setWindowBackgroundAlpha(alpha: Float) {
        //if the context is null,should let it return
        context ?: return
        if (context is Activity) {
            val window: Window = (context as Activity).window
            val layoutParams: WindowManager.LayoutParams = window.attributes
            layoutParams.alpha = alpha
            window.attributes = layoutParams
        }
    }

    /**
     * 窗口显示，窗口背景透明度渐变动画
     */
    fun showBackGroundAnimator(alpha: Float) {
        if (alpha >= 1f) return
        val animator: ValueAnimator = ValueAnimator.ofFloat(1.0f, alpha)
        animator.addUpdateListener {
            setWindowBackgroundAlpha(animator.animatedValue as Float)
        }
        animator.duration = 360
        animator.start()
    }
}