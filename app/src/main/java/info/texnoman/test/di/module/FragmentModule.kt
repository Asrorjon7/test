package info.texnoman.test.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import info.texnoman.test.main.MainFragment


@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesOrderFragment(): MainFragment


}