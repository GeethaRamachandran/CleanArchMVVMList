package com.example.cleanarchmvvmlist.data.dto

import com.example.cleanarchmvvmlist.domain.model.Item
import com.google.gson.annotations.SerializedName

data class ItemDto(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)

fun ItemDto.toItem(): Item {
    return Item(
        body=body,
        id= id,
        title=title,
        userId = userId
    )
}