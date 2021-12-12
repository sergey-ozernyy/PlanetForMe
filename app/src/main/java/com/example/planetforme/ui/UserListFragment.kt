package com.example.planetforme.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planetforme.R
import com.example.planetforme.UserRepository
import com.example.planetforme.model.Data
import com.example.planetforme.model.User
import com.example.planetforme.model.UserAdapter
import com.example.planetforme.network.UserApiService
import com.example.planetforme.vm.UserListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.observers.DisposableSingleObserver

import io.reactivex.rxjava3.schedulers.Schedulers


private const val TAG = "UserListFragment"


class UserListFragment : Fragment() {

    private lateinit var userRecyclerView: RecyclerView
    private var adapter: UserAdapter? = UserAdapter(emptyList())

    var userList:MutableList<User?> = mutableListOf(
        User(first_name = "Sergey", last_name = "Ozernyy", avatar = "ffff"))

    private val userListViewModel:UserListViewModel by lazy {
        ViewModelProvider(this)[UserListViewModel::class.java]
    }

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
        userRecyclerView.layoutManager = LinearLayoutManager(context)
        userRecyclerView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userListViewModel.userListFlowable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                updateUI(it)
            }
    }

    private fun updateUI(users:List<User>){
        adapter = UserAdapter(users)
        userRecyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.user_list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.update_users -> {
                UserRepository.get().getUsersFromServer()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



}

