package com.example.ecolift.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface PostDao {

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(person: PostPerson)

    @Update
    suspend fun update(person: PostPerson)

    @Delete
    suspend fun delete(person: PostPerson)

    @Query(value = "DELETE FROM post_person")
    suspend fun deleteAllStudents()

    @Query(value = "SELECT * FROM post_person")
    fun getAllStudentData(): LiveData<List<PostPerson>>

    @Query("SELECT * FROM post_person WHERE id = :id")
    fun getStudent(id: Int): LiveData<PostPerson>
}
