package com.example.moviev2.ui.fragment

import android.content.Context
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviev2.R
import com.example.moviev2.databinding.FragmentDetailBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DetailFragment : Fragment() {
    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    lateinit var viewModel: DetailViewModel
    private var binding: FragmentDetailBinding? = null
    private var position: Int? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModel = ViewModelProvider(this, viewModelProvider).get(DetailViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding?.viewModel = viewModel
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let { bundle ->
            position = bundle?.let { arg -> DetailFragmentArgs.fromBundle(arg).position }
            position?.let { position -> viewModel.movieDetail(position) }
        }
        viewModel.data.observe(viewLifecycleOwner, { state ->
            when (state) {
                is DetailViewModel.State.OnCompleted -> onMovie()
                is DetailViewModel.State.OnError -> onMessage(state.error)
            }
        })
    }

    private fun onMovie() {
        binding?.tvTitle?.text = viewModel.title
        binding?.tvDay?.text = viewModel.day
        binding?.tvReview?.text = viewModel.popularty.toString() + " review"
        binding?.ratingBar?.rating = viewModel.rating!!
        binding?.tvDescription?.text = viewModel.description
    }

    private fun onMessage(error: String) {
        Log.e("backend hatası", error)

    }
}