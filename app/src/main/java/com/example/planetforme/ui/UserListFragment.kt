package com.example.planetforme.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planetforme.R
import com.example.planetforme.R.menu.user_list_menu
import com.example.planetforme.model.Data
import com.example.planetforme.model.User
import com.example.planetforme.model.UserAdapter
import com.example.planetforme.network.UserApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver

import io.reactivex.rxjava3.schedulers.Schedulers


class UserListFragment : Fragment() {

    var userList:MutableList<User?> = mutableListOf(
        User(first_name = "Sergey", last_name = "Ozernyy", avatar = "ffff"))


    lateinit var userRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //getRequest()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)
        setHasOptionsMenu(true)
        userRecyclerView = view.findViewById(R.id.user_recycler_view)
        updateUI()
        return view
    }

    private fun updateUI(){
        userRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = UserAdapter(userList)
        }
    }

    private fun getRequest() {
        UserApiService.getInstance()
            .getJSONApi()
            .getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Data?>() {
                override fun onSuccess(value: Data?) {
                    if (value != null) {
                        userList.addAll(value.data)
                    }
                    updateUI()
                }

                override fun onError(e: Throwable) {}

            })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.user_list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.update_users -> {
                getRequest()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}

