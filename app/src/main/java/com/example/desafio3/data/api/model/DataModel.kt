package com.example.desafio3.data.api.model

data class DataModel <T>(
    val offset : Int,
    val limit : Int,
    val total : Int,
    val count : Int,
    val results : List <T>
)