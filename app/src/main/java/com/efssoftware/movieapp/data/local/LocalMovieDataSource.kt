package com.efssoftware.movieapp.data.local

import com.efssoftware.movieapp.application.AppConstants
import com.efssoftware.movieapp.data.model.MovieEntity
import com.efssoftware.movieapp.data.model.MovieList
import com.efssoftware.movieapp.data.model.toMovieList

class LocalMovieDataSource(private val movieDao: MovieDao) {

    suspend fun getUpcomingMovies(): MovieList  {
        return movieDao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()
    }

    suspend fun getTopRatedMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "toprated" }.toMovieList()
    }

    suspend fun getPopularMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()
    }
    suspend fun saveMovie(movie: MovieEntity){
        movieDao.saveMovie(movie)
    }
}