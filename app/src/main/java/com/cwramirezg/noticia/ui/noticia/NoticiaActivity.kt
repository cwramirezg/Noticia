package com.cwramirezg.noticia.ui.noticia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cwramirezg.noticia.R
import com.cwramirezg.noticia.databinding.ActivityNoticiaBinding
import com.cwramirezg.noticia.ui.noticia.adapter.NoticiaAdapter
import com.cwramirezg.noticia.util.SimpleDividerItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoticiaActivity : AppCompatActivity(R.layout.activity_noticia) {

    private lateinit var binding: ActivityNoticiaBinding
    private val viewModel: NoticiaViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticiaBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)
        init()
    }

    private fun init() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        setupAction()
        setupRv()
    }

    private fun setupAction() {
        viewModel.action.observe(this) {
            when (it) {
                "getDescarga" -> {

                }
            }
        }
    }

    private fun setupRv() {
        binding.rvNoticia.apply {
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SimpleDividerItemDecoration(context, R.drawable.line_divider_white))

        }
        binding.rvNoticia.adapter = NoticiaAdapter {
//            val intent = Intent()
//            val tipo = viewModel.noticiaList.value!![it]
//            if (viewModel.codigo == tipo.codigoMonedaOrigen) {
//                intent.putExtra("codigo", tipo.codigoMonedaDestino)
//            } else {
//                intent.putExtra("codigo", tipo.codigoMonedaOrigen)
//            }
//            setResult(RESULT_OK, intent)
//            finish()
        }
        viewModel.noticiaList.observe(this) {
            if (it.isNotEmpty()) {
                binding.rvNoticia.visibility = View.VISIBLE
                binding.tvSinData.visibility = View.GONE
                (binding.rvNoticia.adapter as NoticiaAdapter).submitList(it)
            } else {
                binding.rvNoticia.visibility = View.GONE
                binding.tvSinData.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        fun newInstance(
            context: Context
        ): Intent {
            val intent = Intent(context, NoticiaActivity::class.java)
            val bundle = Bundle()
            intent.putExtras(bundle)
            return intent
        }
    }
}