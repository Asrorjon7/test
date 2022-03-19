package info.texnoman.test.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import info.texnoman.test.di.module.ActivityBuilder
import info.texnoman.test.di.module.AppModule
import info.texnoman.test.di.module.FragmentModule
import info.texnoman.test.di.module.ViewModelModule
import info.texnoman.test.main.MainApplication
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ActivityBuilder::class,
        AppModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class,
        FragmentModule::class
    ]
)
interface AppComponent:AndroidInjector<MainApplication> {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: MainApplication): Builder
        fun build(): AppComponent

    }

    override fun inject(app: MainApplication)
}
