package com.example.repo4u

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by OddExtension5 on 5/27/20.
 */

interface GitHubService {
    @GET("search/repositories?")
    fun searchRepos(@Query("q") searchTerm: String) : Call<GitHubSearchResult>
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

}