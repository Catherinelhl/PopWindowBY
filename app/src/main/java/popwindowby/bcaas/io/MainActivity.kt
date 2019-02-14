package popwindowby.bcaas.io

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.PopupWindowCompat
import kotlinx.android.synthetic.main.activity_main.*

/**
 * popWindow 不建議規定其大小，否則如果用戶調節了系統的字體就會容易造成顯示異常。
 * 所以，應該在彈框前，重新得到PopWindow的大小
 *
 * https://www.jianshu.com/p/6c32889e6377
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnBAL.setOnClickListener {
            showBottomAndLeft()
        }
        btnUp.setOnClickListener {
            showUpOrLeftOrRight()
        }

    }

    /**
     * 顯示在底部&&左邊
     */
    private fun showBottomAndLeft() {
        val popWindow = TestPopWindow(this)
        //根据指定view定位
        PopupWindowCompat.showAsDropDown(popWindow, btnBAL, 50, 0, Gravity.END)
        // 或者
//        popWindow.showAsDropDown(btn)
        //又或者使用showAtLocation 根据屏幕来定位
//        popWindow.showAtLocation(window.decorView,Gravity.END,50,0)
    }

    /**
     * 重新結算popWindow的寬高
     */
    private fun makeDropDownMeasureSpec(measureSpec: Int): Int {
        val mode: Int = if (measureSpec == ViewGroup.LayoutParams.WRAP_CONTENT)
            View.MeasureSpec.UNSPECIFIED else View.MeasureSpec.EXACTLY
        return View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(measureSpec), mode)
    }

    /**
     * 弹出在视图的上面/左边/右边，因为不能直接写死popWindow的宽高，那么就需要重新量popWindow的的宽高
     */
    private fun showUpOrLeftOrRight() {
        val popWindow = TestPopWindow(this)
        popWindow.showBackGroundAnimator(0.7f)
        popWindow.setOnDismissListener {         popWindow.showBackGroundAnimator(1f)
        }
        val contentView = popWindow.contentView
        //need to measure first ,popWindow's width and height is Zero before it show
        contentView.measure(makeDropDownMeasureSpec(popWindow.width), makeDropDownMeasureSpec(popWindow.height))
        //You should show anyWhere after measured
        //first，显示在左边，对等
//        val offsetX = -popWindow.contentView.measuredWidth
//        val offsetY = -popWindow.contentView.measuredHeight
//        PopupWindowCompat.showAsDropDown(popWindow, btnUp, offsetX, offsetY, Gravity.START)
        //Second 显示在上边
        val offsetX = Math.abs(popWindow.contentView.measuredWidth - btnUp.width) / 2
        val offsetY = -popWindow.contentView.measuredHeight-btnUp.height
        PopupWindowCompat.showAsDropDown(popWindow, btnUp, offsetX, offsetY, Gravity.START)
    }

}
