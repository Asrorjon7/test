package info.texnoman.test.main

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import info.texnoman.test.di.component.DaggerAppComponent

class MainApplication:DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()

    }
}