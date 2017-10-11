package co.com.data.di

import android.arch.persistence.room.Room
import android.content.Context
import co.com.data.AppDatabase
import co.com.data.DATABASE_NAME

import dagger.Module
import dagger.Provides

/**
 * Created by oscargallon on 6/26/17.
 */
@Module
class DatabaseModule(val mContext: Context) {


    @Provides
    fun provideDatabase(): AppDatabase = Room.databaseBuilder(mContext, AppDatabase::class.java,
            DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    fun provideContext(): Context = mContext

}