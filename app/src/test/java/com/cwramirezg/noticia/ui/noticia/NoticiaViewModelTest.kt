package com.cwramirezg.noticia.ui.noticia

import RxImmediateSchedulerRule
import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.cwramirezg.noticia.data.model.Noticia
import com.cwramirezg.noticia.data.pojo.Response
import com.cwramirezg.noticia.data.source.DataSourceRepositoryContract
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*

class NoticiaViewModelTest {
    private lateinit var handle: SavedStateHandle
    private lateinit var bundle: Bundle
    private lateinit var repository: DataSourceRepositoryContract
    private lateinit var viewModel: NoticiaViewModel

    @get: Rule
    var rule = InstantTaskExecutorRule()

    @get: Rule
    var timeoutRule = RxImmediateSchedulerRule()

    @Before
    fun setUp() {
        handle = SavedStateHandle()
        bundle = mock()
        repository = mock()
    }

    @Test
    fun obtenerDatosDelApiExitoso() {
        val response = Response(
            arrayListOf(
                Response.Hits(
                    "cwramirezg",
                    "prueba",
                    123,
                    "Prueba",
                    "url"
                )
            ),
            "",
            0,
            0,
            0
        )
        whenever(repository.isOnline()).thenReturn(true)
        whenever(repository.getNoticiaApi())
            .thenReturn(Observable.just(response))
        whenever(repository.getNoticiaSave(any()))
            .thenReturn(Single.just(arrayListOf(0)))
        whenever(repository.getNoticia()).thenReturn(Flowable.just(arrayListOf(Noticia())))

        viewModel = NoticiaViewModel(repository)

        verify(repository, times(1)).isOnline()
        verify(repository, times(1)).getNoticiaApi()
        verify(repository, times(1)).getNoticiaSave(any())
        verify(repository, times(1)).getNoticia()
        Assert.assertNotNull(viewModel.noticiaList.value)
    }

    @Test
    fun obtenerDatosDelLocalexitoso() {
        val response = Response(
            arrayListOf(
                Response.Hits(
                    "cwramirezg",
                    "prueba",
                    123,
                    "Prueba",
                    "url"
                )
            ),
            "",
            0,
            0,
            0
        )
        whenever(repository.isOnline()).thenReturn(false)
        whenever(repository.getNoticiaApi())
            .thenReturn(Observable.just(response))
        whenever(repository.getNoticiaSave(any()))
            .thenReturn(Single.just(arrayListOf(0)))
        whenever(repository.getNoticia()).thenReturn(Flowable.just(arrayListOf(Noticia())))

        viewModel = NoticiaViewModel(repository)

        verify(repository, times(1)).isOnline()
        verify(repository, times(0)).getNoticiaApi()
        verify(repository, times(0)).getNoticiaSave(any())
        verify(repository, times(1)).getNoticia()
        Assert.assertNotNull(viewModel.noticiaList.value)
    }
}