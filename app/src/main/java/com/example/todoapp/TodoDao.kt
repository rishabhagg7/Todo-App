package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao{
    @Insert
    fun insertTask(todoModel: TodoModel):Long

    @Query("SELECT * FROM TodoModel WHERE isFinished != -1")
    fun getTask():LiveData<List<TodoModel>>

    @Query("UPDATE TodoModel SET isFinished = 1 WHERE id=:arg0")
    fun finishTask(arg0:Long)

    @Query("DELETE FROM TodoModel WHERE id=:arg0")
    fun deleteTask(arg0:Long)
}