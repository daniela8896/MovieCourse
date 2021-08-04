package com.efssoftware.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.efssoftware.movieapp.core.Resource
import com.efssoftware.movieapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher

class MovieViewModel(private val repo: MovieRepository): ViewModel() {

    fun fetchMainScreenMovies() = liveData(Dispatchers.IO){
        emit(Resource.Loading())

        try {
           emit(Resource.Success(Triple(repo.getUpcomingMovies(), repo.getTopRatedMovies(),repo.getPopularMovies())) )
        }catch (e: Exception){
           emit((Resource.Failure(e)))
        }

    }

}
class MovieViewModelFactory(private val repo: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }

}