package com.pets.service.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CatResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("width") val width: Long?,
    @SerializedName("height") val height: Long?
) : Serializable