package com.example.moviev2.ui.fragment

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviev2.data.AppDataManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PopularViewModel @Inject internal constructor(private var appDataManager: AppDataManager) : ViewModel() {

    val data = MutableLiveData<State>()
    val image = ObservableField<String>()
    var title: String? = null
    var rating: Float? = null
    var day: String? = null
    var popularty: Double? = null
    var description: String? = null


    @SuppressLint("CheckResult")
    fun popularDetail(position: Int) {
        appDataManager.popularApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ //success
                image.set(it.results?.get(position)?.poster_path)
                title = it.results?.get(position)?.title
                rating = it.results?.get(position)?.star()
                day = it.results?.get(position)?.release_date
                popularty = it.results?.get(position)?.popularity
                description = it.results?.get(position)?.overview
                data.postValue(State.OnCompleted)


            }, {
                data.value = State.OnError(it.message ?: "Bir hatayla karşılaşıldı")
            })

    }


    sealed class State {
        object OnCompleted : State()
        data class OnError(val error: String) : State() //parametre göndermek için data class

    }
}