package com.zaatsiyah.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainActivity : AppCompatActivity() {

    var noteDatabase: NoteDatabase? = null

    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteDatabase= NoteDatabase.getInstance(this)

        //tatanan tampilan yang akan dipake, untuk mengatur
        layoutManager = LinearLayoutManager(this)
        adapter = NoteAdapter(ArrayList())

        ma_rv_notes.layoutManager = layoutManager
        ma_rv_notes.adapter = adapter

        getAllNotes()

        ma_fab_add.onClick {
            startActivity(intentFor<AddUpdateActivity>())
        }
    }

    //untuk mengambil seluruh data notes yg ada pada database
    private fun getAllNotes() {
        GlobalScope.launch {
            val list : List<NoteModel>? =
                noteDatabase?.noteDao()?.getAllNote()
            adapter.setListNote(list ?: ArrayList())
        }
    }
}