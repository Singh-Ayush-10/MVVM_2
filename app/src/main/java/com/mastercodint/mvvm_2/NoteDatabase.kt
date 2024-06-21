package com.mastercodint.mvvm_2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase :RoomDatabase(){

    abstract fun getNoteDao():NoteDao


    companion object{
        @Volatile
        var INSTANCE:NoteDatabase?=null

        fun getDatabaseInstance(context: Context):NoteDatabase{

            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val roomDatabaseInstance =
                    Room.databaseBuilder(context,
                        NoteDatabase::class.java,"notes_table").allowMainThreadQueries().build()
                INSTANCE=roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }
}