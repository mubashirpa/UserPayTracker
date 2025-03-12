package com.example.userpaytracker.core.utils

import com.example.userpaytracker.core.Result
import com.example.userpaytracker.core.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
) = flow {
    emit(Result.Loading())

    val data = query().first()

    val flow =
        if (shouldFetch(data)) {
            emit(Result.Loading(data))

            try {
                saveFetchResult(fetch())
                query().map { Result.Success(it) }
            } catch (throwable: Throwable) {
                query().map { Result.Error(UiText.DynamicString(throwable.message.orEmpty()), it) }
            }
        } else {
            query().map { Result.Success(it) }
        }

    emitAll(flow)
}
