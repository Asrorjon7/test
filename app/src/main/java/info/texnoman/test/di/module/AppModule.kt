package info.texnoman.test.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import info.texnoman.test.main.MainApplication

import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Provides
    @Singleton
    fun provideContext(app: MainApplication):Context =app

    @Provides
    @Singleton
    fun provideApplication(app: MainApplication): Application = app

}