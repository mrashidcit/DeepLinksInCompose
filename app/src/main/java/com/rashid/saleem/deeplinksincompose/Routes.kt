package com.rashid.saleem.deeplinksincompose

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {

    @Serializable
    data object Home : Routes

    @Serializable
    data class Detail(val id: Int = -1) : Routes




}