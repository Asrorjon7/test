package info.texnoman.test.main

import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import info.texnoman.test.R
import info.texnoman.test.base.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor (var application: MainApplication): AndroidViewModel(application) {

    private var _listQr = SingleLiveEvent<ArrayList<String>>()
    val listQr:SingleLiveEvent<ArrayList<String>> =_listQr
    private val _timerFlow: MutableStateFlow<String> = MutableStateFlow("")
    val timerFlow: StateFlow<String> get() = _timerFlow.asStateFlow()

    private val _isStartFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isStartFlow: StateFlow<Boolean> get() = _isStartFlow.asStateFlow()

    private val _isEndFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isEndFlow: StateFlow<Boolean> get() = _isEndFlow.asStateFlow()
    fun getListQR():ArrayList<String>{
        var listQr=application.resources.getStringArray(R.array.algorithm)
        var  setListQR:ArrayList<String> =ArrayList()

        for (i in listQr.indices){
            setListQR.add(listQr[i])
        }
        _listQr.postValue(setListQR)
        return setListQR
    }

    fun timerStart(sekund:Long): CountDownTimer {
        _isStartFlow.value = true
        var timer: CountDownTimer = object : CountDownTimer(sekund*1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
                _isEndFlow.value = true
            }
        }
        return timer.start()
    }



}