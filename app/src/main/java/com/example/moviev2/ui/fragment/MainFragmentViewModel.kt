package com.example.moviev2.ui.fragment

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviev2.data.AppDataManager
import com.example.moviev2.data.model.now.Result
import com.example.moviev2.data.model.popular.PopularResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainFragmentViewModel@Inject internal constructor( private var appDataManager: AppDataManager): ViewModel() {
    val data = MutableLiveData<State>()
    var result:List<Result>?=null
    var popularResult:List<PopularResult>?=null
    val progressVisible = ObservableField(false)

    @SuppressLint("CheckResult")
    fun now() {
        progressVisible.set(true)
        appDataManager.api()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ //success

                result=it.results
                data.postValue(State.OnCompleted)


            }, {
                data.value = State.OnError(it.message ?: "Bir hatayla karşılaşıldı")
            })

    }

    @SuppressLint("CheckResult")
    fun popular() {
        appDataManager.popularApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ //success
                popularResult=it.results
                data.postValue(State.OnCompleted)
                progressVisible.set(false)

            }, {
                data.value = State.OnError(it.message ?: "Bir hatayla karşılaşıldı")
            })

    }


    sealed class State {
        object OnCompleted : State()
        data class OnError(val error: String) : State() //parametre göndermek için data class

    }
}