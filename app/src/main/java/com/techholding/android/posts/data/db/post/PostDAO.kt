package com.techholding.android.posts.data.db.post

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDAO {

    @Query("SELECT * FROM post_entity WHERE id = :id")
    suspend fun get(id: Long): PostEntity

    @Query("SELECT * FROM post_entity")
    suspend fun getAll(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(postEntity: PostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(postEntity: List<PostEntity>)

    @Delete
    suspend fun delete(postEntity: PostEntity)

    @Query("DELETE FROM post_entity")
    suspend fun deleteAll()
}