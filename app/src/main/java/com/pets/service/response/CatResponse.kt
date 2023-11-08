package com.pets.service.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CatResponse(
    @SerializedName("name") val name: String
) : Serializable