package com.mastercodint.mvvm_2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.mastercodint.mvvm_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Adapter.INoteRvAdapter {
    lateinit var binding: ActivityMainBinding

    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter=Adapter(this,this)
        binding.recyclerView.adapter=adapter

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNote.observe(this, Observer {list->
            list?.let {
                adapter.updateList(list)
            }
        })
        binding.buttonSubmit.setOnClickListener {
            submitNote()
            if(binding.editTextNote.text.isEmpty()){
                Toast.makeText(this@MainActivity,"No data to add ",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            binding.editTextNote.text=null
        }
    }

    private fun submitNote() {
        val text=binding.editTextNote.text.toString()
        if(text.isNotEmpty()){
            viewModel.addNote(Note(0,text))
        }
    }

    override fun OnItemClicked(note: Note) {
        viewModel.deleteNote(note)
    }
}