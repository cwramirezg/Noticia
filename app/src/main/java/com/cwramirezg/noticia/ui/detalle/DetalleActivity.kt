package com.cwramirezg.noticia.ui.detalle

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.cwramirezg.noticia.R
import com.cwramirezg.noticia.data.model.Noticia
import com.cwramirezg.noticia.databinding.ActivityDetalleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetalleActivity : AppCompatActivity(R.layout.activity_detalle) {

    private lateinit var binding: ActivityDetalleBinding
    private val viewModel: DetalleViewModel by viewModel{ parametersOf(intent.extras) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)
        init()
    }

    private fun init() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        setupAction()
    }


    private fun setupAction() {
        viewModel.action.observe(this) {
            when (it) {
                "getDescarga" -> {

                }
            }
        }
    }

    companion object {
        const val EXTRA_NOTICIA = "extra_noticia"
        fun newInstance(
            context: Context,
            noticia: Noticia
        ): Intent {
            val intent = Intent(context, DetalleActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_NOTICIA, noticia)
            intent.putExtras(bundle)
            return intent
        }
    }
}