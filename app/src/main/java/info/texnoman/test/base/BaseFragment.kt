package info.texnoman.test.base
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerDialogFragment
import dagger.android.support.DaggerFragment
import info.texnoman.test.di.factory.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment<B : ViewBinding, V : ViewModel> : DaggerFragment(),
    LifecycleObserver {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var mViewModel: V

    val viewModel: V get() = mViewModel


    abstract fun injectViewModel()

    abstract fun getViewModelClass(): Class<V>

    private var _binding: B? = null
    protected val binding get() = requireNotNull(_binding)!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  viewLifecycleOwner.lifecycle.addObserver(this)
        init()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        savedInstance(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if(savedInstanceState != null) {
            restoreInstanse(savedInstanceState)
        }
      }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
     //   dialog?.window?.attributes?.windowAnimations = R.style.AppTheme_Fragment
        super.onActivityCreated(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          injectViewModel()
        _binding = setupViewBinding(inflater, container)
        return binding.root
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

    }
    abstract fun init()

    abstract fun savedInstance(bundle: Bundle)

    abstract fun restoreInstanse(savedInstanceState: Bundle)

    abstract fun setupViewBinding(
        inflater: LayoutInflater, container: ViewGroup?,
    ): B

   /* fun setTitle(title: String?) {
        sharedViewModel.setPageTitle(title)
    }*/
    protected fun snackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
        //Timber.e("saom",message.toString())
    }
   /* @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun clearViewBinding() {
      //  _binding = null
      //  viewLifecycleOwner.lifecycle.removeObserver(this)
    }*/



}