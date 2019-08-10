package ec.erickmedina.paging_test.util
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import java.lang.NumberFormatException

fun View.visible() {
    this.visibility = VISIBLE
}

fun View.invisible() {
    this.visibility = GONE
}

fun String.nextIndex(): String =
    try {
        this.toInt().plus(1).toString()
    } catch (e:NumberFormatException) {
        "1"
    }

