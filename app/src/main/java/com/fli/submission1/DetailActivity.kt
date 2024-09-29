package com.fli.submission1

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fli.submission1.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val datas = intent.getParcelableExtra<Champions>(Utils.EXTRA_DATA)
        datas?.let {
            binding.tvDetailName.text = it.name
            binding.tvDescription.text = it.description
            binding.ivImage.setImageResource(it.photo)
        }

        Utils.changeStatusBarColor(window, "#3498db")
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#D6E3FF")))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.actionShare.setOnClickListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onClick(view: View) {
        val datas = intent.getParcelableExtra<Champions>(Utils.EXTRA_DATA)
        datas?.let {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, it.name)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Bagikan menggunakan"))
        }
    }
}
