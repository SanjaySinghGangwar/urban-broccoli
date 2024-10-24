package dev.sanjaygangwar.tempproject.utils.retrofit.operations

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import dev.sanjaygangwar.tempproject.utils.retrofit.Resource
import kotlinx.coroutines.Dispatchers

fun <T, A> performGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            responseStatus.data?.let {
                saveCallResult(it)
            }
        } else if (responseStatus.status == Resource.Status.ERROR) {
            responseStatus.message?.let {
                emit(Resource.error(it))
            }
            emitSource(source)
        }
    }