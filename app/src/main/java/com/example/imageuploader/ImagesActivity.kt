package com.example.imageuploader

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imageuploader.databinding.ActivityImagesBinding
import com.google.firebase.firestore.FirebaseFirestore

class ImagesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImagesBinding
    private lateinit var firebaseFireStore: FirebaseFirestore
    private var mList = mutableListOf<String>()
    private lateinit var adapter: ImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityImagesBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initVars()
        getImages()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getImages() {
        firebaseFireStore.collection("images").get().addOnSuccessListener {
            for (i in it) {
                mList.add(i.data["pic"].toString())
            }
            adapter.notifyDataSetChanged()
        }
    }

    private fun initVars() {
        firebaseFireStore = FirebaseFirestore.getInstance()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter  =ImagesAdapter(mList)
        binding.recyclerView.adapter = adapter

    }
}