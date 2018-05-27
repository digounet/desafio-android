package com.pablo.desafio.features.detail

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.view.MenuItem
import com.example.pablo.desafio.R
import com.pablo.desafio.features.main.MainActivity
import com.pablo.desafio.shared.base.BaseActivity
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(detail_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            val fragment = MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MovieDetailFragment.ARG_ITEM,
                            intent.getParcelableExtra(MovieDetailFragment.ARG_ITEM))
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    NavUtils.navigateUpTo(this, Intent(this, MainActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}
