package com.challenge_kotlin.lab05.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.challenge_kotlin.lab05.R

class ListPlacesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_places, container, false)

        val listaItems: LinearLayout = view.findViewById(R.id.lista_items)

        val catedralButton = newButton(listaItems, getString(R.string.catedral_title), R.drawable.catedral)
        val claustroButton = newButton(listaItems, getString(R.string.claustro_title), R.drawable.claustro)

        catedralButton.setOnClickListener {
            onCardClick(catedralButton, getString(R.string.catedral_description), R.drawable.catedral)
        }
        claustroButton.setOnClickListener {
            onCardClick(claustroButton, getString(R.string.claustro_description), R.drawable.claustro)
        }

        listaItems.addView(catedralButton)
        listaItems.addView(claustroButton)

        return view
    }

    private fun newButton(listaItems: LinearLayout, title: String, image: Int): View {
        val customButton = LayoutInflater.from(context).inflate(R.layout.custom_button, listaItems, false)
        val titleButton = customButton.findViewById<TextView>(R.id.titleButton)
        val imageButton = customButton.findViewById<ImageView>(R.id.imageButton)

        titleButton.text = title
        imageButton.setImageResource(image)
        return customButton
    }

    private fun onCardClick(customButton: View, description: String, image: Int) {
        val placeName = customButton.findViewById<TextView>(R.id.titleButton).text.toString()

        val placeDescriptionFragment = PlaceDescriptionFragment()

        val bundle = Bundle().apply {
            putString("place_name", placeName)
            putString("place_description", description)
            putInt("place_image", image)
        }

        placeDescriptionFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, placeDescriptionFragment)
            .addToBackStack(null)
            .commit()
    }
}

