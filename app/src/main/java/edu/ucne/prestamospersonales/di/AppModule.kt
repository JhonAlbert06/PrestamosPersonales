package edu.ucne.prestamospersonales.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.prestamospersonales.data.local.OcupacionDB
import edu.ucne.prestamospersonales.data.repository.OcupacionReposImpl
import edu.ucne.prestamospersonales.domain.repository.OcupacionRepository
import edu.ucne.prestamospersonales.utils.DATABASE
import edu.ucne.prestamospersonales.utils.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePrestamosPersonales(app: Application) = Room.databaseBuilder(
        app,
        OcupacionDB::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideRepository(db: OcupacionDB): OcupacionRepository{
        return OcupacionReposImpl(db.ocupacionDao)
    }
}