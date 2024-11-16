package com.challenge_kotlin.lab05.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.challenge_kotlin.lab05.R

class PlaceDescriptionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_place_description, container, false)

        val placeName = arguments?.getString("place_name")
        val placeDescription = arguments?.getString("place_description")
        val placeImage = arguments?.getInt("place_image")

        val nameTextView: TextView = view.findViewById(R.id.titleCard)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionCard)
        val imageView: ImageView = view.findViewById(R.id.image)

        nameTextView.text = placeName
        descriptionTextView.text = placeDescription
        placeImage?.let { imageView.setImageResource(it) }

        return view
    }
}
