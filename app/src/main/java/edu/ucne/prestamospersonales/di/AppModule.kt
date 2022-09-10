package edu.ucne.prestamospersonales.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.prestamospersonales.data.AppDataBase
import edu.ucne.prestamospersonales.data.repositoryImpl.OcupacionRepositoryImpl
import edu.ucne.prestamospersonales.feature_Ocupaciones.domain.repository.OcupacionRepository
import edu.ucne.prestamospersonales.utils.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePrestamosPersonales(app: Application) = Room.databaseBuilder(
        app,
        AppDataBase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideRepository(db: AppDataBase): OcupacionRepository {
        return OcupacionRepositoryImpl(db.ocupacionDao)
    }
}