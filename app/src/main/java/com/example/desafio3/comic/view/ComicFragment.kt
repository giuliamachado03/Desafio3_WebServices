package com.example.desafio3.comic.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.desafio3.R
import com.example.desafio3.comic.model.ComicDate
import com.example.desafio3.comic.model.ComicPrice
import com.example.desafio3.comic.repository.ComicRepository
import com.example.desafio3.comic.viewModel.ComicViewModel
import com.example.desafio3.data.api.model.ThumbnailModel
import com.squareup.picasso.Picasso
import java.util.*

class ComicFragment : Fragment() {

    private lateinit var _view: View
    private lateinit var _viewModel: ComicViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comic, container, false)
    }
}
