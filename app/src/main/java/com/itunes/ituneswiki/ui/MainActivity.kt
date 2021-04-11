package com.itunes.ituneswiki.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import com.itunes.ituneswiki.R
import com.itunes.ituneswiki.model.ITuneSongs
import com.itunes.ituneswiki.ui.adapters.SongsAdapter
import com.itunes.ituneswiki.viewmodel.ITunesViewModel

@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var noResultsCardView: CardView
    private lateinit var searchButton: CardView
    private lateinit var iTunesViewModel: ITunesViewModel
    private lateinit var iTuneSongsList: List<ITuneSongs>
    private lateinit var searchCardView: MaterialCardView
    private lateinit var songsRecyclerView: RecyclerView
    private lateinit var songsAdapter: SongsAdapter
    private lateinit var searchEditText: TextInputEditText
    private lateinit var resultsDescText: TextView

    private fun checkInternetConnection(): Boolean {

        var isConnected = false
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        if (capabilities != null) {

            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {

                isConnected = true

            }

        }

        return isConnected
    }

    private fun hideTheKeyboard() {

        val view = this.currentFocus

        if (view != null) {

            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)

        }

    }

    private fun initializeOnClickListener() {

        searchButton.setOnClickListener(this)
        searchCardView.setOnClickListener(this)
    }

    private fun initializeViews() {

        iTuneSongsList = ArrayList()
        iTunesViewModel = ViewModelProviders.of(this).get(ITunesViewModel::class.java)
        noResultsCardView = findViewById(R.id.noResultsCardView)
        resultsDescText = findViewById(R.id.resultsDescText)
        resultsDescText.text = getString(R.string.search_artists)
        searchButton = findViewById(R.id.searchButton)
        searchCardView = findViewById(R.id.searchCardView)
        searchEditText = findViewById(R.id.searchEditText)
        songsAdapter = SongsAdapter(iTuneSongsList)
        songsRecyclerView = findViewById(R.id.songsRecyclerView)
        songsRecyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            setHasFixedSize(true)
            adapter = songsAdapter
        }
    }

    private fun showTheKeyboard() {

        searchEditText.requestFocus()
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        initializeOnClickListener()
    }

    override fun onClick(v: View?) {

        if (v?.id == R.id.searchButton) {

            if (checkInternetConnection()) {

                if (searchEditText.text?.isEmpty() == true) {

                    Toast.makeText(
                        this,
                        "Please, search artist's name to get albums.",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    val searchTerm = searchEditText.text.toString()
                    iTunesViewModel.getSongs(searchTerm).observe(this, { iTuneSongsList ->

                        if (iTuneSongsList.isNotEmpty()) {

                            hideTheKeyboard()
                            noResultsCardView.visibility = View.GONE

                            if (songsRecyclerView.visibility == View.GONE) {

                                songsRecyclerView.visibility = View.VISIBLE

                            }

                            songsAdapter.updateAdapter(iTuneSongsList)

                        } else {

                            hideTheKeyboard()
                            resultsDescText.text = getString(R.string.no_results_found)
                            noResultsCardView.visibility = View.VISIBLE
                            songsRecyclerView.visibility = View.GONE

                        }

                    })

                }

            } else {

                Toast.makeText(
                    this,
                    "Please, make sure you are connected to internet.",
                    Toast.LENGTH_SHORT
                ).show()

            }

        } else if (v?.id == R.id.searchCardView) {

            showTheKeyboard()

        }

    }
}