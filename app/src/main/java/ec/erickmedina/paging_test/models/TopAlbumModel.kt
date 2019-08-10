package ec.erickmedina.paging_test.models

data class TopAlbumModel (val name: String, val playcount: Long, val id: String, val url: String,
                          val artist: ArtistModel, val image:ArrayList<ImageModel>)


