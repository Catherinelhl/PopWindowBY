package popwindowby.bcaas.io

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
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

    constructor(context: Context) : super(context) {
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
}