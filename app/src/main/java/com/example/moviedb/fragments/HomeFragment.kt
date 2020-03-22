package com.example.moviedb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.services.MovieModel

import com.example.moviedb.R
import com.example.moviedb.adapters.RecyclerAdapter
import com.example.moviedb.services.MoviesApi
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

//https://api.themoviedb.org/3/movie/550?api_key=983b46f95f78ff0f9dd82a7bb2a6d321

    private val apiKey = "983b46f95f78ff0f9dd82a7bb2a6d321"
    private val baseUrl = "https://api.themoviedb.org/3/"
    private var  movieModels: ArrayList<MovieModel>? = null
    private var recyclerViewAdapter : RecyclerAdapter? = null
   // private var compositeDisposable : CompositeDisposable? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        //compositeDisposable = CompositeDisposable()
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(activity,3,LinearLayoutManager.VERTICAL,false)
        recyclerView?.layoutManager = layoutManager
        loadData()
    }

    private fun loadData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        /*compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))*/


        val service = retrofit.create(MoviesApi::class.java)
        val call = service.getData(apiKey)

        call.enqueue(object: Callback<List<MovieModel>> {
            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                println("Bu hiç iyi olmadı.")
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                if(response.isSuccessful){
                response.body()?.let {

                    movieModels = ArrayList(it)
                    movieModels?.let {
                        recyclerViewAdapter = RecyclerAdapter(it)
                        recyclerView.adapter = recyclerViewAdapter
                    }
                }
                }else{
                    println("olmadı")
            }
        }

        })
    }
    /*private fun handleResponse(movieList : List<MovieModel>) {

        movieModels = ArrayList(movieList)

        movieModels?.let {
            this.recyclerViewAdapter = RecyclerAdapter(it)
            recyclerView.adapter = this.recyclerViewAdapter

        }
    }*/

    override fun onDestroy() {
        super.onDestroy()
        //compositeDisposable?.clear()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
