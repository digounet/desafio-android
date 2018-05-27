package com.pablo.desafio.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.pablo.desafio.R
import com.pablo.desafio.model.data.Movie
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.movie_detail.*

class MovieDetailFragment : DaggerFragment(), MovieDetailContract.View {

    @Inject
    lateinit var presenter: MovieDetailContract.Presenter

    private val glideOptions = RequestOptions()
            .centerCrop()
            .priority(Priority.HIGH)
            .diskCacheStrategy(DiskCacheStrategy.ALL)

    override fun onCreate(savedInstanceState: Bundle?) {
        presenter.attachView(this)

        super.onCreate(savedInstanceState)

        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM)) {
                presenter.showMovie(it.getParcelable(ARG_ITEM) as Movie)
            }
        }
    }

    override fun showMovie(movie: Movie) {

        activity?.toolbar_layout?.title = movie.name

        Glide.with(activity)
                .load(movie.url)
                .apply(glideOptions)
                .into(activity?.imgMovie)

        txtMovieDescription.text = movie.description
    }

    override fun showLoadErrorMessage() {

    }

    companion object {
        const val ARG_ITEM = "item"
    }
}
