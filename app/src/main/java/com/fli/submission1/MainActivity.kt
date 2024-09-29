package com.fli.submission1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fli.submission1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvChampion: RecyclerView
    private val list = ArrayList<Champions>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvChampion.setHasFixedSize(true)

        list.addAll(getListChampions())
        showRecyclerList()

        supportActionBar?.let {
            it.title = getString(R.string.list_champions)
        }
    }

    private fun getListChampions(): ArrayList<Champions> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listChampions = ArrayList<Champions>()
        for (i in dataName.indices) {
            val champions = Champions(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listChampions.add(champions)
        }
        dataPhoto.recycle()
        return listChampions
    }

    private fun showRecyclerList() {
        binding.rvChampion.layoutManager = LinearLayoutManager(this)
        val listChamAdapter = ListChamAdapter(list)
        binding.rvChampion.adapter = listChamAdapter

        listChamAdapter.setOnItemClickCallback(object : ListChamAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Champions) {
                showSelectedChampion(data)
            }
        })
    }

    private fun showSelectedChampion(champions: Champions) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(Utils.EXTRA_DATA, champions)
        startActivity(intent)
    }

    // untuk bikin about page menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
