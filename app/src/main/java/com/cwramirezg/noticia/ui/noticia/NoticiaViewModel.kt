package com.cwramirezg.noticia.ui.noticia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cwramirezg.noticia.data.model.Noticia
import com.cwramirezg.noticia.data.source.DataSourceRepositoryContract
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
        disposables.add(
            repository.getNoticia()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
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
                        _noticiaList.value = list
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