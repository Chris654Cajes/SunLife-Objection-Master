package com.objectionmaster

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.SearchView
import com.objectionmaster.databinding.ActivityMainBinding
import com.objectionmaster.ui.adapter.ObjectionAdapter
import com.objectionmaster.utils.TextToSpeechManager
import com.objectionmaster.viewmodel.ObjectionViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ObjectionViewModel
    private lateinit var adapter: ObjectionAdapter
    private var textToSpeechManager: TextToSpeechManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(ObjectionViewModel::class.java)

        // Initialize TextToSpeech
        textToSpeechManager = TextToSpeechManager(this)

        // Setup RecyclerView
        setupRecyclerView()

        // Setup Search
        setupSearch()

        // Observe ViewModel
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = ObjectionAdapter(
            onItemClick = { objection ->
                val intent = Intent(this, ObjectionDetailActivity::class.java)
                intent.putExtra("objection_id", objection.id)
                startActivity(intent)
            },
            onPlayAudio = { objection ->
                textToSpeechManager?.speak(objection.response)
            }
        )

        binding.objectionRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupSearch() {
        // Ensure SearchView is enabled and focusable
        binding.searchView.isEnabled = true
        binding.searchView.isFocusable = true
        binding.searchView.isFocusableInTouchMode = true
        
        // Request focus on the SearchView
        binding.searchView.requestFocus()
        
        // Set up query text listener for AppCompat SearchView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchObjections(query ?: "")
                return true
            }
            
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchObjections(newText ?: "")
                return true
            }
        })
    }

    private fun observeViewModel() {
        viewModel.objections.observe(this) { objections ->
            if (objections.isEmpty()) {
                binding.emptyStateText.visibility = View.VISIBLE
                binding.objectionRecyclerView.visibility = View.GONE
            } else {
                binding.emptyStateText.visibility = View.GONE
                binding.objectionRecyclerView.visibility = View.VISIBLE
                adapter.submitList(objections)
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            // You can add a ProgressBar here if needed
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeechManager?.shutdown()
    }
}
