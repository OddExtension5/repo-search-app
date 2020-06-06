package com.example.repo4u

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val searchTerm = intent.getStringExtra("searchTerm")
        val retriever = GitHubRetriever()

        if (searchTerm != null) {
            // Search
            val callback = object : Callback<GitHubSearchResult> {
                override fun onResponse(
                    call: Call<GitHubSearchResult>?,
                    response: Response<GitHubSearchResult>?
                ) {
                    val searchResult = response?.body()

                    if (searchResult != null) {
                        listRepos(searchResult.items)
                    }
                }


                override fun onFailure(call: Call<GitHubSearchResult>?, t: Throwable?) {
                    println("It's not working")
                }
            }
            retriever.searchRepos(callback, searchTerm)
        } else {
            // User Repo
            val username = intent.getStringExtra("username")
            val callback = object : Callback<List<Repo>> {
                override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                    print("Don't Work")
                }

                override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                    if (response.code() == 404) {
                        val theView =
                            this@SearchResultActivity.findViewById<View>(android.R.id.content)
                        Snackbar.make(
                            theView,
                            "User not found 👀 Go back and try again.",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        val repos = response.body()
                        if (repos != null) {
                            listRepos(repos)
                        }
                    }
                }

            }
            retriever.userRepos(callback, username)
        }

    }

    fun listRepos(repos: List<Repo>) {
        val listView = findViewById<ListView>(R.id.repoListView)
        listView.setOnItemClickListener { _, _, i, _ ->
            val selectedRepo = repos[i]

            //OPEN THE URL IN A BROWSER
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(selectedRepo.html_url))
            startActivity(intent)
        }
        val adapter =
            RepoAdapter(this@SearchResultActivity, android.R.layout.simple_list_item_1, repos)
        listView.adapter = adapter
    }
}


class RepoAdapter(context: Context?, resource: Int, objects: List<Repo>) : ArrayAdapter<Repo>(context!!, resource, objects as MutableList<Repo>) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val repoView = inflator.inflate(R.layout.repo_list_layout, parent, false)


        val textView = repoView.findViewById<TextView>(R.id.repoTextView)
        val imageView = repoView.findViewById<ImageView>(R.id.repoImageView)

        val repo = getItem(position)

        Picasso.with(context).load(Uri.parse(repo?.owner?.avatar_url)).into(imageView)

        textView.text = repo?.full_name

        return repoView
    }

}


