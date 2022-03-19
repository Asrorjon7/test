package info.texnoman.test.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import info.texnoman.test.MainActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
/*
    @ContributesAndroidInjector
    abstract fun contributesUserActivity(): AuthActivity*/

}