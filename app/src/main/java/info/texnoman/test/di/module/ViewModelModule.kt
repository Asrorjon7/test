package info.texnoman.test.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import info.texnoman.test.main.MainViewModel
import info.texnoman.test.di.component.ViewModelKey
import info.texnoman.test.di.factory.ViewModelFactory

/*
* created by Asrorjon
*/
@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun providesPlayerViewModel(viewModel: MainViewModel): ViewModel



}