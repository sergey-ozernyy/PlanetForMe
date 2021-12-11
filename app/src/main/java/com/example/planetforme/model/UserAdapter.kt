package com.example.planetforme.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.planetforme.R
import com.example.planetforme.network.AvatarLoader

class UserAdapter(var mUsers: MutableList<User?>): RecyclerView.Adapter<UserAdapter.UserHolder>() {

    inner class UserHolder(view: View) : RecyclerView.ViewHolder(view) {
        val firstName:TextView = view.findViewById<TextView>(R.id.first_name)
        val lastName:TextView = view.findViewById<TextView>(R.id.last_name)
        val email:TextView = view.findViewById<TextView>(R.id.email)
        val avatarImage:ImageView = view.findViewById(R.id.avatar_image)

        fun bind(user:User){
            firstName.text = user.first_name
            lastName.text = user.last_name
            email.text = user.email
            AvatarLoader().avatarDownload(avatarImage,user.avatar, false)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return UserHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user: User? = mUsers[position]
        val bundle = Bundle()

        if (user != null) {
            holder.bind(user)
            bundle.putString("firstName", user.first_name)
            bundle.putString("lastName", user.last_name)
            bundle.putString("email", user.email)
            bundle.putString("avatarRef", user.avatar)
        }

        holder.itemView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_userListFragment_to_userDetailFragment, bundle))
    }

    override fun getItemCount(): Int {
        return mUsers.size
    }
}