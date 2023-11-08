package com.techholding.android.posts.data.db.comment

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CommentDAO {

    @Query("SELECT * FROM comment_entity WHERE id = :id")
    suspend fun get(id: Long): CommentEntity

    @Query("SELECT * FROM comment_entity")
    suspend fun getAll(): List<CommentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(commentEntity: CommentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(commentEntity: List<CommentEntity>)

    @Delete
    suspend fun delete(commentEntity: CommentEntity)

    @Query("DELETE FROM comment_entity")
    suspend fun deleteAll()

}