package com.example.moviev2.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.moviev2.R
import com.example.moviev2.databinding.FragmentDetailBinding
import com.example.moviev2.databinding.FragmentPopularBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class PopularFragment : Fragment() {
    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    lateinit var viewModel: PopularViewModel
    private var binding: FragmentPopularBinding? = null
    private var position: Int? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this, viewModelProvider).get(PopularViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false)
        binding?.viewModel = viewModel
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let { bundle ->
            position = bundle?.let { arg -> DetailFragmentArgs.fromBundle(arg).position }
            position?.let { position -> viewModel.popularDetail(position) }
        }
        viewModel.data.observe(viewLifecycleOwner, { state ->
            when (state) {
                is PopularViewModel.State.OnCompleted -> onPopular()
                is PopularViewModel.State.OnError -> onMessage(state.error)
            }
        })
    }

    private fun onPopular() {
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