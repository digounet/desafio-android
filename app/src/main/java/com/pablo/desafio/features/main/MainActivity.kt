package com.pablo.desafio.features.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import com.example.pablo.desafio.R
import com.pablo.desafio.features.detail.MovieDetailActivity
import com.pablo.desafio.features.detail.MovieDetailFragment
import com.pablo.desafio.model.data.Movie
import com.pablo.desafio.model.persistence.api.ApiConstants
import com.pablo.desafio.shared.base.BaseActivity
import com.pablo.desafio.shared.widget.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.movie_list.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {
    @Inject
    lateinit var presenter: MainContract.Presenter

    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        setSupportActionBar(toolbar)

        if (item_detail_container != null) {
            twoPane = true
        }

        presenter.attachView(this)
        presenter.subscribe()

        val linearLayoutManager = LinearLayoutManager(this)
        movie_list.layoutManager = linearLayoutManager
        movie_list.addOnScrollListener(object : EndlessRecyclerViewScrollListener(linearLayoutManager, ApiConstants.API_PAGE_SIZE) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                presenter.loadMovies(false, page)
            }

        })
    }

    override fun showMovieDetail(movie: Movie) {
        if (twoPane) {
            val fragment = MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MovieDetailFragment.ARG_ITEM, movie)
                }
            }
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
        } else {
            val intent = Intent(this, MovieDetailActivity::class.java).apply {
                putExtra(MovieDetailFragment.ARG_ITEM, movie)
            }
            startActivity(intent)
        }
    }

    override fun updateList(movies: List<Movie>) {
        movie_list.adapter = MovieListAdapter(movies, { showMovieDetail(it)})
    }

    override fun showLoadErrorMessage() {
        Snackbar.make(root, R.string.error_loading_data, Snackbar.LENGTH_LONG)
    }

}
