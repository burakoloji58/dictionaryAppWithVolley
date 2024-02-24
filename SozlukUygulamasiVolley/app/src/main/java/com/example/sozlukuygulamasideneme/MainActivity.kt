package com.example.sozlukuygulamasideneme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.sozlukuygulamasideneme.databinding.ActivityMainBinding
import org.json.JSONObject
import java.util.Objects

class MainActivity : AppCompatActivity() , SearchView.OnQueryTextListener{
    private lateinit var ulas : ActivityMainBinding
    private lateinit var kelimelistesi: ArrayList<Kelimeler>
    private lateinit var adapter : KelimelerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        ulas = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(ulas.root)

        ulas.toolbar.title="Sözlük Uygulaması"
        setSupportActionBar(ulas.toolbar)

        ulas.rv.setHasFixedSize(true)
        ulas.rv.layoutManager = LinearLayoutManager(this)

        tum_kelimeler()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_arama,menu)

        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        kelime_ara(query)
        Log.e("aranan yer",query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        kelime_ara(newText)

        return true
    }

    fun tum_kelimeler(){
        val url = "https://androiddeveloper.online/sozlukUygulamasi/tum_kelimeler.php"

        val istek = StringRequest(Request.Method.GET,url, Response.Listener { cevap->

            kelimelistesi= ArrayList()

            try {
                val jsonObject = JSONObject(cevap)

                val kelimeler = jsonObject.getJSONArray("kelimeler")

                for (i in 0 until kelimeler.length()){

                    val k = kelimeler.getJSONObject(i)

                    val kelime = Kelimeler(
                         k.getInt("kelime_id")
                        ,k.getString("ingilizce")
                        ,k.getString("turkce"))
                    kelimelistesi.add(kelime)

                    adapter = KelimelerAdapter(this@MainActivity,kelimelistesi)
                    ulas.rv.adapter = adapter

                }

            }catch (e : Exception){
                e.printStackTrace()
            }

        },Response.ErrorListener {})

        Volley.newRequestQueue(this).add(istek)

    }

    fun kelime_ara(aramaKelime : String){
        val url = "https://androiddeveloper.online/sozlukUygulamasi/kelime_ara.php"

        val istek = object : StringRequest(Request.Method.POST,url, Response.Listener { cevap->

            kelimelistesi= ArrayList()

            try {
                val jsonObject = JSONObject(cevap)

                val kelimeler = jsonObject.getJSONArray("kelimeler")

                for (i in 0 until kelimeler.length()){

                    val k = kelimeler.getJSONObject(i)

                    val kelime = Kelimeler(
                        k.getInt("kelime_id")
                        ,k.getString("ingilizce")
                        ,k.getString("turkce"))
                    kelimelistesi.add(kelime)

                    adapter = KelimelerAdapter(this@MainActivity,kelimelistesi)
                    ulas.rv.adapter = adapter

                }

            }catch (e : Exception){
                e.printStackTrace()
            }

        },Response.ErrorListener {}){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String,String>()
                params["ingilizce"]=aramaKelime
                return params
            }
        }

        Volley.newRequestQueue(this).add(istek)

    }


}