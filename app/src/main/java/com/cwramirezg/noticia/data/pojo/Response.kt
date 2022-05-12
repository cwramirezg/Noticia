package com.cwramirezg.noticia.data.pojo

class Response(
    val hits: List<Hits>,
    val query: String,
    val nbHits: Int,
    val nbPages: Int,
    val page: Int
) {
    class Hits(
        val author: String,
        val comment_text: String,
        val story_id: Long,
        val story_title: String,
        val story_url: String,
    )
}