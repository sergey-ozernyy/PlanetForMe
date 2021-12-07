package com.example.planetforme.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.planetforme.R

class UserRecycleAdapter: RecyclerView.Adapter<UserRecycleAdapter.UserHolder>() {

    lateinit var mUsers:MutableList<User>

    inner class UserHolder(inflater: LayoutInflater, viewGroup: ViewGroup, itemView: View) :
        RecyclerView.ViewHolder(itemView) {


        fun bind(user:User){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user:User = mUsers[position]
        holder.bind(user)
        holder.itemView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_userListFragment_to_userDetailFragment))
    }

    override fun getItemCount(): Int {
        return mUsers.size
    }
}