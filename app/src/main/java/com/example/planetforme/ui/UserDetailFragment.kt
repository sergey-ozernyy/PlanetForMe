package com.example.planetforme.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.planetforme.R
import com.example.planetforme.network.AvatarLoader


class UserDetailFragment : Fragment() {

    lateinit var firstName:TextView
    lateinit var lastName:TextView
    lateinit var email:TextView
    lateinit var avatarImage:ImageView

    lateinit var avatarRef:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_detail, container, false)
        firstName = view.findViewById(R.id.firstName_textView)
        lastName = view.findViewById(R.id.lastName_textView)
        email = view.findViewById(R.id.editTextTextEmailAddress)
        avatarImage = view.findViewById(R.id.avatarImageView)

        firstName.text = arguments?.getString("firstName")
        lastName.text = arguments?.getString("lastName")
        email.text = arguments?.getString("email")
        avatarRef = arguments?.getString("avatarRef").toString()

        AvatarLoader().avatarDownload(avatarImage, avatarRef, true)

        return view
    }


}