package com.example.repo4u

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by OddExtension5 on 5/27/20.
 */

interface GitHubService {
    @GET("search/repositories?")
    fun searchRepos(@Query("q") searchTerm: String): Call<GitHubSearchResult>

    @GET("users/{user}/repos")
    fun userRepos(@Path("user") username: String): Call<List<Repo>>
}

class GitHubSearchResult(val items: List<Repo>)
class Repo(val full_name: String, val owner: GitHubUser, val html_url: String)
class GitHubUser(val avatar_url: String)

class GitHubRetriever {
    val service: GitHubService
    init {
        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(GitHubService::class.java)
    }

    fun searchRepos(callback: Callback<GitHubSearchResult>, searchTerm: String) {
        var searchT = searchTerm
        if (searchT == "") {
            searchT = "OddExtension5"
        }
        val call = service.searchRepos(searchT)
        call.enqueue(callback)
    }

    fun userRepos(callback: Callback<List<Repo>>, username: String) {
        val call = service.userRepos(username)
        call.enqueue(callback)

    }

}