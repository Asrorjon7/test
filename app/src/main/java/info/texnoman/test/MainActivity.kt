package info.texnoman.test

import android.view.LayoutInflater
import info.texnoman.test.base.BaseActivity
import info.texnoman.test.main.MainViewModel
import info.texnoman.test.databinding.ActivityMainBinding
import info.texnoman.test.di.factory.injectViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun injectViewModel() {
     mViewModel= injectViewModel(viewModelFactory)
    }

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun init() {

    }

    override fun setupViewBinding(inflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(inflater)
}