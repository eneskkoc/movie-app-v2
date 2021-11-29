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
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.moviev2.R
import com.example.moviev2.core.BaseAdapter
import com.example.moviev2.data.model.now.Result
import com.example.moviev2.data.model.popular.PopularResult
import com.example.moviev2.databinding.FragmentMainBinding
import com.example.moviev2.databinding.ItemNowBinding
import com.example.moviev2.databinding.ItemPopularBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainFragment : Fragment() {
    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    lateinit var viewModel: MainFragmentViewModel
    private var binding: FragmentMainBinding? = null
    private lateinit var nowAdapter: BaseAdapter<ItemNowBinding, Result>
    private lateinit var popularAdapter: BaseAdapter<ItemPopularBinding, PopularResult>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModel = ViewModelProvider(this, viewModelProvider).get(MainFragmentViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding?.viewModel = viewModel
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var action: NavDirections
        nowAdapter = object : BaseAdapter<ItemNowBinding, Result>(R.layout.item_now) {
            override fun bindView(binding: ItemNowBinding, item: Result?, adapterPosition: Int) {
                binding.now = item
            }

            override fun clickListener(item: Result?, position: Int, binding: ItemNowBinding) {
                action = MainFragmentDirections.actionMainFragmentToDetailFragment(position)
                Navigation.findNavController(requireView()).navigate(action)
            }


        }
        popularAdapter = object : BaseAdapter<ItemPopularBinding, PopularResult>(R.layout.item_popular) {
            override fun bindView(binding: ItemPopularBinding, item: PopularResult?, adapterPosition: Int) {
                binding.popular = item
            }

            override fun clickListener(item: PopularResult?, position: Int, binding: ItemPopularBinding) {
                action = MainFragmentDirections.actionMainFragmentToPopularFragment(position)
                Navigation.findNavController(requireView()).navigate(action)
            }


        }
        binding?.rvNow?.adapter = nowAdapter
        viewModel.now()
        binding?.rvPopular?.adapter = popularAdapter
        viewModel.popular()
        viewModel.data.observe(viewLifecycleOwner, { state ->
            when (state) {
                is MainFragmentViewModel.State.OnCompleted -> onMovie()
                is MainFragmentViewModel.State.OnError -> onMessage(state.error)
            }
        })

    }

    private fun onMovie() {
        val now = viewModel.result?.toCollection(ArrayList())
        nowAdapter.items = now
        val popular = viewModel.popularResult?.toCollection(ArrayList())
        popularAdapter.items = popular

    }


    private fun onMessage(error: String) {
        Log.e("backend hatası", error)

    }

}

