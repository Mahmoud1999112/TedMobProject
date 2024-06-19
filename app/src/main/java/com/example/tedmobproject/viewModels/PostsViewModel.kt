package com.example.tedmobproject.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tedmobproject.Post
import com.example.tedmobproject.useCases.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Fetching posts...")
                val posts = getPostsUseCase(Unit)
                _posts.postValue(posts)
                Log.d(TAG, "Posts fetched successfully")
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching posts", e)
            }
        }
    }

    companion object {
        private const val TAG = "PostsViewModel"
    }
}
