package com.cwramirezg.noticia.ui.detalle

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cwramirezg.noticia.data.model.Noticia
import com.cwramirezg.noticia.data.source.DataSourceRepositoryContract
import com.cwramirezg.noticia.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetalleViewModel(
    bundle: Bundle?,
    private val repository: DataSourceRepositoryContract
) : ViewModel() {


    private val disposables = CompositeDisposable()

    private val _noticia = MutableLiveData(
        bundle?.getParcelable(DetalleActivity.EXTRA_NOTICIA) ?: Noticia()
    )
    val noticia: LiveData<Noticia> = _noticia

    private val _action = SingleLiveEvent<String>()
    val action: LiveData<String> = _action

    init {
        disposables.add(
            repository.setLeido(_noticia.value!!.copy(leido = true))
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {

                    },
                    {
                        it.printStackTrace()
                    }
                )
        )
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}