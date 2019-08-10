package ec.erickmedina.paging_test.search

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import ec.erickmedina.paging_test.entity.LastFmResponses
import ec.erickmedina.paging_test.models.ArtistModel
import ec.erickmedina.paging_test.remote.Listing

interface SearchContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun onArtistSearchSuccess(list: PagedList<LastFmResponses.Artist>)
        fun onArtistSearchEmpty()
        fun onArtistSearchError(error: String?)
    }

    interface ViewModel {
        fun getArtistForInput(input:String):Listing<LastFmResponses.Artist>
    }
}