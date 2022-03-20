package info.texnoman.test.main

import android.content.res.Configuration
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import com.google.zxing.WriterException
import info.texnoman.test.R
import info.texnoman.test.base.BaseFragment
import info.texnoman.test.databinding.FragmentMainBinding
import info.texnoman.test.di.factory.injectViewModel
import info.texnoman.test.utils.Position.background1
import info.texnoman.test.utils.Position.background2
import info.texnoman.test.utils.Position.position1
import info.texnoman.test.utils.Position.position2
import info.texnoman.test.utils.getPositionData
import info.texnoman.test.utils.setBackground
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(),
    AdapterView.OnItemSelectedListener {
    var timer: CountDownTimer? = null
    var SpinnerFirstPosition = "SpinnerFirst"
    var SpinnerSecondPosition = "SpinnerSecond"
    var BackgroundFirst = "BackFirst"
    var BackgroundSecond = "BackSecond"
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)

    var count: Int = 0

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun init() {
        getListQR()
        generateQrCode()
        setGreenBackGroundImageView()
    }

    private fun getListQR() {
        var list = viewModel.getListQR()
        binding.apply {
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                list
            ).also { adapter ->
                spinnerFirst.adapter = adapter
                spinnerFirst.onItemSelectedListener = this@MainFragment
                spinnerSecond.adapter = adapter
                spinnerSecond.onItemSelectedListener = this@MainFragment
            }
            spinnerFirst.setSelection(position1)
            spinnerFirst.setSelection(position2)
        }
    }
    private fun generateQrCode() {
        binding.apply {
            btnGenerate.setOnClickListener {
                count = 0
                if (timer != null) {
                    timer!!.cancel()
                }
                var getList = viewModel.getListQR()
                timer(getList)
                timer?.start()
            }
        }
    }

    private fun setGreenBackGroundImageView() {
        binding.apply {
            ivImageFirst.setOnClickListener {
                ivImageFirst.setBackground()
            }
            ivImageSecond.setOnClickListener {
                ivImageSecond.setBackground()
            }
        }
    }

    private fun setQRCodeImageView(id: Int, position: Int) {
        try {
            when (id) {
                R.id.spinner_first -> {
                    binding.ivImageFirst.setImageBitmap(requireContext().getPositionData(position1))
                }
                R.id.spinner_second -> {
                    binding.ivImageSecond.setImageBitmap(requireContext().getPositionData(position))

                }
            }
        } catch (e: WriterException) {
            Log.e("error", e.toString())
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        setQRCodeImageView(parent!!.id, position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    private fun timer(list: ArrayList<String>) {
        timer = object : CountDownTimer(
            binding.seekbar.progress * list.size * 1000L, binding.seekbar.progress.toLong() * 1000L
        ) {
            override fun onTick(millisUntilFinished: Long) {
                try {
                    binding.apply {
                        ivImageFirst.setImageBitmap(requireContext().getPositionData(count))
                        ivImageSecond.setImageBitmap(requireContext().getPositionData(count))
                        spinnerFirst.setSelection(count)
                        spinnerSecond.setSelection(count)
                        count++
                    }
                } catch (e: Exception) {
                    count = 0
                }
            }

            override fun onFinish() {
                binding.apply {
                    spinnerFirst.setSelection(0)
                    spinnerSecond.setSelection(0)
                }
            }
        }
    }



    override fun savedInstance(bundle: Bundle) {
        bundle.putInt(SpinnerFirstPosition, binding.spinnerFirst.selectedItemPosition)
        bundle.putInt(SpinnerSecondPosition, binding.spinnerSecond.selectedItemPosition)
        bundle.putBoolean(BackgroundFirst, background1)
        bundle.putBoolean(BackgroundSecond, background2)
    }

    override fun restoreInstanse(savedInstanceState: Bundle) {
        var imageView =view?.findViewById<ImageView>(R.id.ivImageFirst)
        imageView?.setBackground()
        position1 = savedInstanceState.getInt(SpinnerFirstPosition, 0)
        position2 = savedInstanceState.getInt(SpinnerSecondPosition, 0)

        background1 = savedInstanceState.getBoolean(BackgroundFirst, false)
        background2 = savedInstanceState.getBoolean(BackgroundSecond, false)

        binding.apply {

            spinnerFirst.setSelection(position1)
            spinnerSecond.setSelection(position2)

        }
    }

}