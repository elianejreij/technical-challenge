package mobi.el.technicalchallenge.repository.helpers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mobi.el.technicalchallenge.repository.datasource.Resource

fun <T> performGetOperation(
    databaseQuery: (() -> T)?,
    networkCall: (suspend () -> Resource<T>)?,
    saveCallResult: (suspend (T) -> Unit)?
): Flow<Resource<T>> = flow {
    emit(Resource.loading())

    if (networkCall == null) {
        val a = databaseQuery?.invoke()
        if (a != null)
            emit(Resource.success(a))
    } else {
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            if (saveCallResult != null)
                saveCallResult(responseStatus.data!!)
            emit(Resource.success(responseStatus.data!!))
        } else if (responseStatus.status == Resource.Status.ERROR) {
            val source = databaseQuery?.invoke()
            if (source != null) {
                emit(Resource.success(source))
            } else {
                emit(Resource.error(responseStatus.message!!, null))
            }
        }
    }
}
