package dev.sanjaygangwar.tempproject.utils.datastore

import android.content.Context
import androidx.datastore.dataStore
import dev.sanjaygangwar.tempproject.models.protoclass.appsetting.AppSettingsSerializer

val Context.AppSetting by dataStore("app-settings.json", AppSettingsSerializer)