package ec.erickmedina.paging_test.search

import ec.erickmedina.paging_test.common.base.BaseViewModel
import ec.erickmedina.paging_test.remote.datasource.RemoteDataSource

class SearchViewModel(private val remoteDataSource: RemoteDataSource) : BaseViewModel(), SearchContract.ViewModel {

    override fun getArtistForInput(input: String) =
        remoteDataSource.searchArtist(input)


}