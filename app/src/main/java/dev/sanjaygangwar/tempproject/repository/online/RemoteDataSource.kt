package dev.sanjaygangwar.tempproject.repository.online

import dev.sanjaygangwar.tempproject.repository.sharedpreferences.AppSharePreference
import dev.sanjaygangwar.tempproject.utils.retrofit.BaseDataSource
import javax.inject.Inject

class RemoteDataSource  @Inject constructor(
    private val APIService: APIService,
    private val sharedPreferences: AppSharePreference
) : BaseDataSource() {

}