package ec.erickmedina.paging_test.models

data class AlbumModel (
    val name:String,
    val artist:String,
    val localId: Long,
    val remoteId: String,
    val images: ArrayList<ImageModel>,
    val listeners: Long,
    val playcount: Long,
    val tracks: ArrayList<TrackModel>,
    val tags: ArrayList<TagModel>,
    val publication: String,
    val summary: String,
    val description: String,
    val isSaved: Boolean
    ) {
    fun isEmpty(): Boolean {
        return name.isEmpty() || artist.isEmpty() || tracks.isEmpty()
    }
}