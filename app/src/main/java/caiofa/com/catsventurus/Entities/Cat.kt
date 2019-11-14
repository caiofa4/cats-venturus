package caiofa.com.catsventurus.Entities

data class Cat (val id: String,
                 val title: String,
                 val cover_width: Int,
                 var cover_height: Int,
                 var image: Image)