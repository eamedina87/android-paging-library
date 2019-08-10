package ec.erickmedina.paging_test.common.base

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ec.erickmedina.paging_test.common.Navigator
import org.koin.android.ext.android.inject

abstract class BaseFragment : Fragment() {

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun initView()

    protected abstract val mViewModel : BaseViewModel

    val navigator : Navigator by inject()

    fun getBaseActivity() = activity!! as BaseActivity

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    protected fun setActivityTitle(title: String) {
        getBaseActivity().setActivityTitle(title)
    }

    protected fun setActivityButtonUp(show: Boolean) {
        getBaseActivity().displayHomeAsUpButton(show)
    }

    fun showMessage(message: String) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
        }
    }


}