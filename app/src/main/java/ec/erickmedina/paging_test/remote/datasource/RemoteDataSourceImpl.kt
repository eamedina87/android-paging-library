package ec.erickmedina.paging_test.remote.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.paging.toLiveData
import ec.erickmedina.paging_test.BuildConfig
import ec.erickmedina.paging_test.entity.LastFmResponses
import ec.erickmedina.paging_test.remote.Listing
import ec.erickmedina.paging_test.remote.client.LastFmApiConstants
import ec.erickmedina.paging_test.remote.client.RemoteClient
import ec.erickmedina.paging_test.remote.datasource.artist.ArtistDataSourceFactory
import ec.erickmedina.paging_test.util.handleErrors

class RemoteDataSourceImpl(private val remoteClient: RemoteClient) : RemoteDataSource {

    private val artistErrorMessage = "Artist Search Response is null"
    private val topAlbumsErrorMessage = "Top Albums Response is null"
    private val albumInfoErrorMessage = "Album Info Response is null"

    private val caller =  remoteClient.getRemoteCaller()

    override fun searchArtist(
        artist: String,
        page: String?)
            : Listing<LastFmResponses.Artist> {
        val artistSourceFactory = ArtistDataSourceFactory(
            remoteClient,
            artist,
            artistErrorMessage
        )

        val artistsLive = artistSourceFactory.toLiveData(
            pageSize = LastFmApiConstants.param_search_limit_default.toInt())
        val dataState = Transformations.switchMap(artistSourceFactory.artistsSource) {
            it.getDataState()
        }
        return Listing(artistsLive, dataState)
    }

    override suspend fun getTopAlbumsForArtistIdAsync(
        artistId: String,
        page: String?
    ): LastFmResponses.TopAlbums {
        val response = caller.getTopAlbumsForArtistIdAsync(artistId = artistId, apiKey = BuildConfig.API_KEY,
            page = page?.toInt() ?: LastFmApiConstants.param_page_default)
            .await().handleErrors(topAlbumsErrorMessage)
        return response.topalbums
    }

    override suspend fun getAlbumInfoForId(
        albumId: String,
        lang: String): LastFmResponses.Album {
        val response =
            caller.getAlbumInfoForIdAsync(albumId = albumId, lang = lang, apiKey = BuildConfig.API_KEY).
                await().handleErrors(albumInfoErrorMessage)
        return response.album
    }

}