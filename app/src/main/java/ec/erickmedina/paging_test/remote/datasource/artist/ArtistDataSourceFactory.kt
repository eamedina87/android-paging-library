package ec.erickmedina.paging_test.remote.datasource.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import ec.erickmedina.paging_test.entity.LastFmResponses
import ec.erickmedina.paging_test.remote.client.RemoteClient
import ec.erickmedina.paging_test.states.DataState
import ec.erickmedina.paging_test.states.NetworkState

class ArtistDataSourceFactory(private val remoteClient: RemoteClient, private val artist: String,
                               private val error:String) :
    DataSource.Factory<String, LastFmResponses.Artist>() {


    val artistsSource = MutableLiveData<ArtistDataSource>()

    override fun create(): DataSource<String, LastFmResponses.Artist> {
        val source = ArtistDataSource(remoteClient, artist, error)
        artistsSource.postValue(source)
        return source
    }
}