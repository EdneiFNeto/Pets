package com.pets.data.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DogRequest(
    @SerializedName("name") val name: String
): Serializable