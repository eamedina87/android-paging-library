package ec.erickmedina.paging_test.remote.datasource

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import ec.erickmedina.paging_test.entity.LastFmResponses
import ec.erickmedina.paging_test.remote.Listing

interface RemoteDataSource {

    fun searchArtist(
        artist: String,
         page: String? = null
    ) : Listing<LastFmResponses.Artist>

    suspend fun getTopAlbumsForArtistIdAsync(
        artistId: String,
        page: String? = null) : LastFmResponses.TopAlbums

    suspend fun getAlbumInfoForId(
        albumId: String,
        lang: String) : LastFmResponses.Album

}