package com.example.desafio3.comic.model

import com.example.desafio3.data.api.model.ThumbnailModel

class ComicModel (
    val id: Int?,
    val title: String?,
    val description: String?,
    val pageCount: Int?,
    val dates: List<ComicDate>?,
    val prices: List<ComicPrice>?,
    val thumbnail: ThumbnailModel?,
    val images: List<ThumbnailModel>?,
)