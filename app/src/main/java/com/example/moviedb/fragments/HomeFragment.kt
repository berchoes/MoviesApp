package com.example.moviedb.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.services.MovieModel
import com.example.moviedb.R
import com.example.moviedb.adapters.RecyclerAdapter
import com.example.moviedb.services.MoviesApi
import com.example.moviedb.services.ResponseModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
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

/**b
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private val apiKey = "39a81601"
    private val baseUrl = "https://www.omdbapi.com"
    private lateinit var  movieModels: ArrayList<MovieModel>
    private lateinit var recyclerViewAdapter : RecyclerAdapter


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private fun search(view: SearchView){
        view.setQuery("Doctor Who", false)

        view.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                if(query == null){
                    Toast.makeText(activity,"Search cannot be Empty",Toast.LENGTH_SHORT).show()
                }else{
                    loadData(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

    private fun loadData(searchQuery: String){

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val service = retrofit.create(MoviesApi::class.java)
        val call = service.getData(apiKey,searchQuery)

        call.enqueue(object: Callback<ResponseModel> {

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                println("Bu hiç iyi olmadı.")
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                if(response.isSuccessful){
                response.body()?.let { it ->

                    movieModels = ArrayList(it.Search)
                    movieModels.let {
                        recyclerViewAdapter = RecyclerAdapter(movieList = it)
                        recyclerView.adapter = recyclerViewAdapter
                    }
                }
                }else{
                    println("Olmadı")
                }
            }
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v  = inflater.inflate(R.layout.fragment_home, container, false)
        val searchView= v.findViewById<SearchView>(R.id.searchView)
        val recyclerView= v.findViewById<RecyclerView>(R.id.recyclerView)
        searchView.setQuery("Doctor Who", false)
        searchView.clearFocus()

        recyclerView.layoutManager = GridLayoutManager(activity,3,LinearLayoutManager.VERTICAL,false)
        loadData(searchView.query.toString())
        search(v.searchView)
        return v

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
