package com.ardwiinoo.honestbites

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ardwiinoo.honestbites.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_FOOD = "extra_food"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val food = if(Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FOOD, Food::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FOOD)
        }

        if(food != null) {
            binding.imgFoodPhoto.setImageResource(food.photo)
            binding.tvFoodName.text = food.name
            binding.tvFoodDesc.text = food.description
            binding.tvFoodPrice.text = food.price
        }

        binding.btnLike.setOnClickListener(this)
        binding.actionShare.setOnClickListener(this)

        // back btn
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        if (food != null) {
            supportActionBar!!.title = food.name
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.btnLike.id -> {
                if(binding.btnLike.text == "Like") {
                    binding.btnLike.text = "Unlike"
                    Toast.makeText(this@DetailActivity, "Berhasil Menyukai", Toast.LENGTH_SHORT).show()
                } else {
                    binding.btnLike.text = "Like"
                    Toast.makeText(this@DetailActivity, "Batal Menyukai", Toast.LENGTH_SHORT).show()
                }
            }
            binding.actionShare.id -> {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_SUBJECT, "Bagikan Food")
                    putExtra(Intent.EXTRA_TEXT, "Lihat makanan enak ini, bikin ngilerr...")
                }
                startActivity(Intent.createChooser(intent, "Bagikan Food"))
            }
        }
    }
}