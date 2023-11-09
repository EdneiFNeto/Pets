package com.pets.service.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BreedsResponse(
    @SerializedName("url") val url: String,
    @SerializedName("breeds") val breeds: List<Breeds>
) : Serializable {
    data class Breeds(
        @SerializedName("name") val name: String?,
        @SerializedName("description") val description: String?,
        @SerializedName("life_span") val life_span: String?,
        @SerializedName("temperament") val temperament: String?
    ) : Serializable
}
