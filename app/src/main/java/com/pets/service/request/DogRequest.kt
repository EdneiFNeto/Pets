package com.pets.service.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DogRequest(
    @SerializedName("name") val name: String
): Serializable