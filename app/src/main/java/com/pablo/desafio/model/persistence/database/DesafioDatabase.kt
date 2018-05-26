package com.pablo.desafio.model.persistence.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.pablo.desafio.model.data.Movie

@Database(entities = [(Movie::class)], version = 1, exportSchema = false)
abstract class DesafioDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}