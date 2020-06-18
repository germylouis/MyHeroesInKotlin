package com.example.myheroesinkotlin.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myheroesinkotlin.R
import com.example.myheroesinkotlin.adapter.HeroAdapter
import com.example.myheroesinkotlin.model.HeroResults
import com.example.myheroesinkotlin.viewmodel.HeroViewModel
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    lateinit var viewModel: HeroViewModel
    var hero = "Spiderman"
    lateinit var text: EditText
    lateinit var button: Button

    lateinit var rView: RecyclerView
    lateinit var myObserver: Observer<HeroResults>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        rView = listof_heroes
        text = search

        text.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE ||
                event.action == KeyEvent.ACTION_DOWN ||
                event.action == KeyEvent.KEYCODE_ENTER
            ) { //execute our method for searching
                hero = text.text.toString()
                viewModel.getResultLiveData().observe(this, myObserver)
                viewModel.getHeroRepo(hero)
            }
            false
        }

        viewModel = ViewModelProviders.of(this).get(HeroViewModel::class.java)
        myObserver =
            Observer<HeroResults> { hero ->
                setupRV(hero)
            }

        viewModel.getResultLiveData().observe(this, myObserver)
        viewModel.getHeroRepo()


    }

    private fun setupRV( hero: HeroResults) {
        val adapter = HeroAdapter(hero)
        rView.adapter = adapter
        rView.layoutManager = LinearLayoutManager(this@SecondActivity)
    }


}
