package com.codzure.leonard.nyumbani.util

sealed class Lce<out T> {

    object Loading : Lce<Nothing>()

    object Empty : Lce<Nothing>()

    data class Content<T>(val content: T) : Lce<T>()

    data class Error(val error: Throwable? = null) : Lce<Nothing>()
}