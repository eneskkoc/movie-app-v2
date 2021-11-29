package com.example.moviev2.data.model.popular

data class Popular(
    val page: Int?,
    val results: List<PopularResult>?,
    val total_pages: Int?,
    val total_results: Int?
)