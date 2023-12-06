package com.example.movies
//
//import android.app.Application
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ViewModelComponent
//import dagger.hilt.android.scopes.ViewModelScoped
//
//@Module
//@InstallIn(ViewModelComponent::class)
//object MoviePlayerModule {
//
//    @Provides
//    @ViewModelScoped
//    fun providePreviousOrient(app: Application): Boolean {
//        return ExoPlayer.Builder(app)
//            .build()
//    }
//
//    @Provides
//    @ViewModelScoped
//    fun provideMetaDataReader(app: Application): MetaDataReader {
//        return MetaDataReaderImpl(app)
//    }
//object MoviePlayerModule {
//}