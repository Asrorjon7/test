package info.texnoman.test.base
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerAppCompatActivity
import info.texnoman.test.di.factory.ViewModelFactory
import javax.inject.Inject

abstract class BaseActivity<B : ViewBinding, V : ViewModel> : DaggerAppCompatActivity(),LifecycleObserver {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var mViewModel: V
    private var _binding: B? = null
    protected val binding get() = requireNotNull(_binding)
    val viewModel: V get() = mViewModel
    abstract fun injectViewModel()
    abstract fun getViewModelClass(): Class<V>


    override fun onCreate(savedInstanceState: Bundle?) {
     //   setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        injectViewModel()
    _binding =setupViewBinding(layoutInflater)
        setContentView(binding.root)
    lifecycle.addObserver(this)
    init()


    }


    abstract fun init()
    override fun onDestroy() {
        super.onDestroy()
    }
    override fun onBackPressed() {
            super.onBackPressed()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    abstract fun setupViewBinding(inflater: LayoutInflater): B
}