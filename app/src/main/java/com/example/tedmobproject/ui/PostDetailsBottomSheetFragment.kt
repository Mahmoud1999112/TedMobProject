package com.example.tedmobproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.tedmobproject.Post
import com.example.tedmobproject.databinding.ItemPostBinding


class PostDetailsBottomSheetFragment(private val post: Post) : DialogFragment() {

    private lateinit var binding: ItemPostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.postTitle.text = post.title
        binding.postBody.text = post.body
    }

}