package com.example.desafio3.comic.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.desafio3.R
import com.squareup.picasso.Picasso

class ImageDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_image_detail, container, false)
        val image = view.findViewById<ImageView>(R.id.imgDetailComic)
        val coverThumbnail = arguments?.getString(ComicFragment.COMIC_IMAGE)
        Picasso.get().load(coverThumbnail).into(image)

        return view
    }
}
   