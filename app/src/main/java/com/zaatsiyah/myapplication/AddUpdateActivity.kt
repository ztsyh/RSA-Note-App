package com.zaatsiyah.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_update.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class AddUpdateActivity : AppCompatActivity() {

    var noteDatabase: NoteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update)

        noteDatabase = NoteDatabase.getInstance(this)

        generateDate()

        aua_btn_save.onClick {
            insertNewNote()
        }
    }

    private fun insertNewNote() {
        val cipherTeks = doEncrypt()
        val note = NoteModel(
            title = aua_edt_title.text.toString(),
            message = cipherTeks,
            createdAt = aua_tv_date.text.toString()
        )
        GlobalScope.launch {
            noteDatabase?.noteDao()?.insertNote(note)
            runOnUiThread {
                toast("Berhasil menambahkan data baru")
            }

        }
    }

    private fun generateDate() {
        val locale = Locale("in", "ID")
        val sdf = SimpleDateFormat("EEE, dd MMM yyyy, hh::mm::ss", locale)

        val dateNow = Calendar.getInstance().time
        val dateNowFormat = sdf.format(dateNow).toString()

        aua_tv_date.text = dateNowFormat
    }

    private fun doEncrypt(): String? {
        val rsa = RSA()
        val e = rsa.eValue(rsa.Qn)

        var cipherTeks = ""
        for (i in aua_edt_message.text.toString()) {
            val cipher = rsa.encrypt(i, e, rsa.n)
            cipherTeks += cipher
        }
        return cipherTeks
    }
}