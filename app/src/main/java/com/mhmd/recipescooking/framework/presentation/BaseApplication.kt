package com.mhmd.recipescooking.framework.presentation

import android.app.Application
import com.mhmd.recipescooking.framework.datasource.datastore.SettingsDataStore
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication : Application()