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
import com.cwramirezg.noticia.ui.detalle.DetalleActivity
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
        setupRefresh()
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
            val noticia = viewModel.noticiaList.value!![it]
            startActivity(DetalleActivity.newInstance(this, noticia))
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

    private fun setupRefresh() {
        binding.srlNoticia.setOnRefreshListener {
            viewModel.getNoticiasApi()
            binding.srlNoticia.isRefreshing = false
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