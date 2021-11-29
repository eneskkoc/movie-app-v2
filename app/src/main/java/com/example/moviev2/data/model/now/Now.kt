package com.example.moviev2.data.model.now

data class Now(
    val dates: Dates?,
    val page: Int?,
    val results: List<Result>?,
    val total_pages: Int?,
    val total_results: Int?
)