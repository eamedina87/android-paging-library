package ec.erickmedina.paging_test.di

import ec.erickmedina.paging_test.BuildConfig
import ec.erickmedina.paging_test.common.Navigator
import ec.erickmedina.paging_test.remote.client.RemoteClient
import ec.erickmedina.paging_test.remote.datasource.RemoteDataSource
import ec.erickmedina.paging_test.remote.datasource.RemoteDataSourceImpl
import ec.erickmedina.paging_test.search.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val dbName = "musicalisimo.db"

val appModule = module {
    single { Navigator() }
}

val viewModelModule = module {
    viewModel { SearchViewModel(get()) }
}

val domainModule = module {

}

val dataModule = module {
    //DATA SOURCE
    single <RemoteDataSource> { RemoteDataSourceImpl(get()) }
    //REMOTE
    single { RemoteClient(androidContext(), BuildConfig.API_URL) }
}
