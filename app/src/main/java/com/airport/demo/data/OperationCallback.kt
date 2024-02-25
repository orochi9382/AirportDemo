package com.airport.demo.data


interface OperationCallback<T> {
    fun onSuccess(data: Any?)
    fun onError(error: String?)
}
