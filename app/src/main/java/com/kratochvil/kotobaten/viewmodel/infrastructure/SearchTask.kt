package com.kratochvil.kotobaten.viewmodel.infrastructure

import android.os.AsyncTask
import com.kratochvil.kotobaten.model.entity.SearchResult
import com.kratochvil.kotobaten.model.service.JishoApiService

class SearchTask : AsyncTask<String, Void, List<SearchResult>>() {
    var callback: ((x: List<SearchResult>) -> Unit) = {}

    override fun doInBackground(vararg params: String?): List<SearchResult> {
        return params
            .filter { it != null }
            .flatMap { JishoApiService().search(it as String) }
    }

    override fun onPostExecute(result: List<SearchResult>?) {
        super.onPostExecute(result)

        if(result != null)
            callback(result)
    }
}