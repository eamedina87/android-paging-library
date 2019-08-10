package ec.erickmedina.paging_test.util


import ec.erickmedina.paging_test.entity.LastFmResponses
import ec.erickmedina.paging_test.models.*

fun LastFmResponses.Album.mapToModel() : AlbumModel {
    val images = arrayListOf<ImageModel>()
    image?.forEach {
        images.add(it.mapToModel())
    }
    val trackList = arrayListOf<TrackModel>()
    tracks?.track?.forEach {
        trackList.add(it.mapToModel())
    }
    val tagList = arrayListOf<TagModel>()
    tags?.tag?.forEach {
        tagList.add(it.mapToModel())
    }
    return AlbumModel(name, artist, 0, mbid ?: "", images, listeners,
        playcount, trackList, tagList, wiki?.published ?: "", wiki?.summary?: "",
        wiki?.content ?: "", isSaved)
}

fun LastFmResponses.Image.mapToModel() : ImageModel =
    ImageModel(text ?: "", size ?: "")

fun LastFmResponses.Track.mapToModel() : TrackModel =
    TrackModel(name ?: "", duration ?: 0, attr["rank"]?.toInt() ?: 0)

fun LastFmResponses.Tag.mapToModel() : TagModel =
    TagModel(name ?: "")

fun LastFmResponses.Artist.mapToModel() : ArtistModel {
    val images = arrayListOf<ImageModel>()
    image?.forEach {
        images.add(it.mapToModel())
    }
    return ArtistModel(name ?: "", listeners ?: 0, mbid ?: "", images)
}

fun LastFmResponses.TopAlbum.mapToModel() : TopAlbumModel {
    val images = arrayListOf<ImageModel>()
    image?.forEach {
        images.add(it.mapToModel())
    }
    return TopAlbumModel(name ?: "", playcount ?: 0, mbid ?: "", url ?: "",
        artist.mapToModel(), images)
}

fun AlbumModel.mapToRemoteEntity() : LastFmResponses.Album {
    val image = arrayOf<LastFmResponses.Image>()
    val trackEntity = arrayOf<LastFmResponses.Track>()
    val tracksEntity = LastFmResponses.Tracks(trackEntity)
    this.tracks?.forEach {
        trackEntity.plus(it.mapToEntity())
    }
    val tagEntity = arrayOf<LastFmResponses.Tag>()
    val tagsEntity = LastFmResponses.Tags(tagEntity)
    this.tags?.forEach {
        tagEntity.plus(it.mapToEntity())
    }
    return LastFmResponses.Album(name, artist, remoteId, "", image, listeners, playcount,
        tracksEntity, tagsEntity, LastFmResponses.Wiki(publication, summary, description))
}

fun TagModel.mapToEntity() : LastFmResponses.Tag =
    LastFmResponses.Tag(name, "")

fun TrackModel.mapToEntity() : LastFmResponses.Track =
    LastFmResponses.Track(name, "", duration, order.toTrackAttribute(), HashMap(),
        LastFmResponses.Artist("", 0, "", "", 0, arrayOf()))

fun Int.toTrackAttribute() : Map<String, String> {
    val map = HashMap<String, String>()
    map["rank"] = this.toString()
    return map
}



