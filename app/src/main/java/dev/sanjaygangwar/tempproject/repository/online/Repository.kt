package dev.sanjaygangwar.tempproject.repository.online

import dev.sanjaygangwar.tempproject.repository.offline.dao.mainDao
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: mainDao
) {

}