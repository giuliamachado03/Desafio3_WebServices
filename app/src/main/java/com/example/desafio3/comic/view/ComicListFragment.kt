package com.example.desafio3.comic.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio3.R
import com.example.desafio3.comic.model.ComicModel
import com.example.desafio3.comic.repository.ComicRepository
import com.example.desafio3.comic.viewModel.ComicViewModel


class ComicListFragment : Fragment() {
    private lateinit var _viewModel: ComicViewModel
    private lateinit var _view:View
    private lateinit var _listAdapter: ComicListAdapter
    private lateinit var _recyclerView: RecyclerView
    private var _comics = mutableListOf<ComicModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comics_list, container, false)
    }
    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view
        val list = _view.findViewById<RecyclerView>(R.id.recyclerViewComics)
        val manager = GridLayoutManager(_view.context, 3)
        _comics = mutableListOf<ComicModel>()
        _listAdapter = ComicListAdapter(_comics) {
            val bundle = bundleOf(COMICS_ID to it.id,
                COMICS_DESCRIPTION to it.description,
                COMICS_PRICE to it.prices,
                COMICS_IMAGES to it.images,
                COMICS_THUMBNAIL to it.thumbnail?.getImagePath(),
                COMICS_PAGES to it.pageCount,
                COMICS_DATES to it.dates,
                COMICS_TITLE to it.title
            )
            _view.findNavController().navigate(R.id.action_comicsListFragment_to_comicFragment, bundle)
        }
        _recyclerView = _view.findViewById<RecyclerView>(R.id.recyclerViewComics)
        list.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = _listAdapter
        }
        _viewModel = ViewModelProvider(
            this,
            ComicViewModel.ComicViewModelFactory(ComicRepository())
        ).get(ComicViewModel::class.java)
        _viewModel.getList().observe(viewLifecycleOwner, Observer {
            _comics.addAll(it)
            _listAdapter.notifyDataSetChanged()
            showLoading(false)
        })
        showLoading(true)
        setScrollView()
    }
    private fun showLoading(isLoading: Boolean) {
        val viewLoading = _view.findViewById<View>(R.id.comicsLoading)
        if (isLoading) {
            viewLoading.visibility = View.VISIBLE
        } else {
            viewLoading.visibility = View.GONE
        }
    }
    private fun setScrollView() {
        _recyclerView.run {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val target = recyclerView.layoutManager as GridLayoutManager?
                    val totalItemCount = target!!.itemCount
                    val lastVisible = target.findLastVisibleItemPosition()
                    val lastItem = lastVisible + 6 >= totalItemCount
                    if (totalItemCount > 0 && lastItem) {
                        showLoading(true)
                        _viewModel.nextPage().observe(viewLifecycleOwner, Observer {
                            _comics.addAll(it)
                            _listAdapter.notifyDataSetChanged()
                            showLoading(false)
                        })
                    }
                }
            })
        }
    }


    companion object {
        const val COMICS_ID = "COMICS_ID"
        const val COMICS_DESCRIPTION = "COMICS_DESCRIPTION"
        const val COMICS_PRICE = "COMICS_PRICE"
        const val COMICS_IMAGES = "COMICS_IMAGES"
        const val COMICS_THUMBNAIL = "COMICS_THUMBNAIL"
        const val COMICS_PAGES = "COMICS_PAGES"
        const val COMICS_DATES = "COMICS_DATES"
        const val COMICS_TITLE = "COMICS_TITLE"
    }
}