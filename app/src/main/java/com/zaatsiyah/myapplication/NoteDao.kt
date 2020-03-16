package com.zaatsiyah.myapplication

import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT* from Note")
    fun  getAllNote(): List<NoteModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteModel: NoteModel)

    @Delete
    fun deleteNote(noteModel: NoteModel)

    @Query("UPDATE Note SET title =:title, message =:message WHERE id =:id")
    fun updateNote(title:String?, message:String?, id:Long)

}