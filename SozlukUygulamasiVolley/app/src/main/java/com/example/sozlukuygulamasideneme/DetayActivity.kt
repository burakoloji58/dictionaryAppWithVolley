package com.example.sozlukuygulamasideneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sozlukuygulamasideneme.databinding.ActivityDetayBinding

class DetayActivity : AppCompatActivity() {
    private lateinit var ulas : ActivityDetayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        ulas = ActivityDetayBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(ulas.root)

        var intent = intent.getSerializableExtra("nesne") as Kelimeler

        ulas.textViewDetayIngilizce.text = intent.turkce
        ulas.textViewDetayTurkce.text = intent.ingilizce


    }
}