package com.example.learnbymobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class WordsActivity : AppCompatActivity() {
    lateinit var viewWordAdapter: WordRecycleViewAdapter
    lateinit var viewRecycle: RecyclerView
    lateinit var button: Button
    lateinit var back: ImageView
    lateinit var word: TextView
    lateinit var pronunciation: TextView
    var itemsList: MutableList<WordItem> = mutableListOf()
    var number: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_words)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var excersises = ArrayList<WordsTask>()
        var itemList = ArrayList<String>()
        itemList.add(getString(R.string.wordsItem1_1))
        itemList.add(getString(R.string.wordsItem1_2))
        itemList.add(getString(R.string.wordsItem1_3))
        itemList.add(getString(R.string.wordsItem1_4))
        excersises.add(WordsTask("dog","['dog]",itemList,itemList.get(0)))

        number = Random.nextInt(from = 0, until = 1)
        word = findViewById(R.id.textWord) as TextView
        word.text = excersises.get(number).word
        pronunciation = findViewById(R.id.textPronunciation) as TextView
        pronunciation.text = excersises.get(number).pronunciation
        excersises.get(number).variants.forEachIndexed { index, item ->
            itemsList.add(WordItem(item))
        }
        back = findViewById(R.id.back) as ImageView
        back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(this@WordsActivity,HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
        viewRecycle = findViewById(R.id.recycleView) as RecyclerView
        viewRecycle.layoutManager = LinearLayoutManager(this)
        viewWordAdapter = WordRecycleViewAdapter(itemsList) { position ->
            itemsList.forEachIndexed { index, item ->
                item.isSelected = index == position
            }
            viewRecycle.adapter?.notifyDataSetChanged()
        }
        viewRecycle.adapter = viewWordAdapter
        button = findViewById(R.id.button2) as Button
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                if (button.text.toString() == getString(R.string.wordsNext)) {
                    itemsList.clear()
                    number = Random.nextInt(from = 0, until = 1)
                    excersises.get(number).variants.forEachIndexed { index, item ->
                        itemsList.add(WordItem(item))
                    }
                    word.text = excersises.get(number).word
                    pronunciation.text = excersises.get(number).pronunciation
                    viewRecycle.adapter?.notifyDataSetChanged()
                    button.setText(R.string.wordsCheck)
                }
                else {
                    var noSkip = false
                    itemsList.forEach { wordItem ->
                        noSkip = noSkip || wordItem.isSelected
                    }
                    if (!noSkip) return

                    itemsList.forEach { wordItem ->
                        if (wordItem.word == excersises.get(number).correct) {
                            wordItem.isCorrect = true
                        }
                        if (wordItem.isSelected && (wordItem.word != excersises.get(number).correct)) {
                            wordItem.isWrong = true
                        }
                    }
                    button.setText(R.string.wordsNext)
                    viewRecycle.adapter?.notifyDataSetChanged()
                }
            }
        })

    }


}