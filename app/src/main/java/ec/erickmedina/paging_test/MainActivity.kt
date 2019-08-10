package ec.erickmedina.paging_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ec.erickmedina.paging_test.common.base.BaseActivity
import ec.erickmedina.paging_test.search.SearchFragment

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        supportFragmentManager.beginTransaction().add(R.id.main_container, SearchFragment(), "").commit()
    }

}
