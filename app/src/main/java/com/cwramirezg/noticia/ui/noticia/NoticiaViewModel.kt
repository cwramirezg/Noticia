package com.cwramirezg.noticia.ui.noticia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cwramirezg.noticia.data.model.Noticia
import com.cwramirezg.noticia.data.source.DataSourceRepositoryContract
import com.cwramirezg.noticia.util.ExtensionStatic
import com.cwramirezg.noticia.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NoticiaViewModel(
    private val repository: DataSourceRepositoryContract
) : ViewModel() {
    private val disposables = CompositeDisposable()

    private val _noticiaList = MutableLiveData<List<Noticia>>()
    val noticiaList: LiveData<List<Noticia>> = _noticiaList

    private val _action = SingleLiveEvent<String>()
    val action: LiveData<String> = _action

    init {
        if (ExtensionStatic.isOnline()) {
            getNoticiasApi()
        } else {
            getNoticias()
        }
    }

    fun getNoticiasApi() {
        disposables.add(
            repository.getNoticiaApi()
                .subscribeOn(Schedulers.io())
                .map {
                    val list = it.hits.map {
                        Noticia(
                            0L,
                            it.story_id,
                            it.story_title ?: "",
                            it.story_url ?: "",
                            it.comment_text ?: "",
                            it.author ?: "",
                            false
                        )
                    }
                    list
                }
                .flatMapSingle { repository.getNoticiaSave(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {

                    },
                    {
                        it.printStackTrace()
                    },
                    {
                        getNoticias()
                    }
                )
        )
    }

    fun getNoticias() {
        disposables.add(
            repository.getNoticia()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _noticiaList.value = it
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