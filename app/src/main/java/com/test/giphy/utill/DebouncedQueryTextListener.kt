package com.test.giphy.utill

import androidx.appcompat.widget.SearchView.OnQueryTextListener

abstract class DebouncedQueryTextListener :
    OnQueryTextListener {
    override fun onQueryTextSubmit(text: String?): Boolean = true

    abstract override fun onQueryTextChange(newText: String?): Boolean
}