package com.example.todoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//for testing, verdict: working fine
//object Database{
//    val tasks:List<TodoModel> = listOf(
//        TodoModel(
//            "Sample",
//            "This is a sample task",
//            "Personal",
//            0L,0L
//        )
//    )
//}

@Database(entities = [TodoModel::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun todoDao():TodoDao

    companion object{
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context):AppDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}