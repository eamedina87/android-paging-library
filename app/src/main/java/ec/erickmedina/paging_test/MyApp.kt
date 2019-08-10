package ec.erickmedina.paging_test

import android.app.Application
import ec.erickmedina.paging_test.di.appModule
import ec.erickmedina.paging_test.di.dataModule
import ec.erickmedina.paging_test.di.domainModule
import ec.erickmedina.paging_test.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MyApp)
            loadKoinModules(listOf(appModule, viewModelModule, domainModule, dataModule))
        }
    }
}