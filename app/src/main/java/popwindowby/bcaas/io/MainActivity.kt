package popwindowby.bcaas.io

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.core.widget.PopupWindowCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener {
            showBottomAndLeft()
        }

    }

    /**
     * 顯示在底部&&左邊
     */
    private fun showBottomAndLeft() {
        val popWindow = TestPopWindow(this)
        //根据指定view定位
        PopupWindowCompat.showAsDropDown(popWindow, btn, 50, 0, Gravity.END)
        // 或者
//        popWindow.showAsDropDown(btn)
        //又或者使用showAtLocation 根据屏幕来定位
//        popWindow.showAtLocation(window.decorView,Gravity.END,50,0)
    }
}
