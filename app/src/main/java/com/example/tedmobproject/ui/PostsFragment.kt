package com.example.tedmobproject.ui

import com.example.tedmobproject.helpers.PostsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tedmobproject.Post
import com.example.tedmobproject.viewModels.PostsViewModel
import com.example.tedmobproject.databinding.FragmentPostsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostsFragment : Fragment() {

    private lateinit var binding: FragmentPostsBinding
    private lateinit var adapter: PostsAdapter
    private val viewModel: PostsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = PostsAdapter(
            onItemClick = { post -> showPostDetails(post) },
            onShowMoreClick = { post -> showMoreClicked(post) }
        )

        recyclerView.adapter = adapter

        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
        }
    }

    private fun showPostDetails(post: Post) {

    }

    private fun showMoreClicked(post: Post) {
        val bottomSheetFragment = PostDetailsBottomSheetFragment(post)
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
    }

}
