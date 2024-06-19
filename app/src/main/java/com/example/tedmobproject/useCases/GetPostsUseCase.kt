package com.example.tedmobproject.useCases

import com.example.tedmobproject.Post
import com.example.tedmobproject.data.PostApiService
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postApiService: PostApiService
) : SuspendableUseCase<List<Post>, Unit>() {
    override suspend fun performAction(params: Unit): List<Post> {
        return postApiService.getPosts()
    }
}