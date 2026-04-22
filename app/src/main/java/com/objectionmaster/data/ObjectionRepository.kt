package com.objectionmaster.data

import android.content.Context
import com.objectionmaster.model.Objection
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import java.io.BufferedReader
import java.io.InputStreamReader

class ObjectionRepository(private val context: Context) {

    fun getAllObjections(): List<Objection> {
        return loadObjectionsFromJson()
    }

    fun getObjectionById(id: Int): Objection? {
        return getAllObjections().find { it.id == id }
    }

    fun searchObjections(query: String): List<Objection> {
        val normalizedQuery = query.lowercase().trim()
        if (normalizedQuery.isEmpty()) {
            return getAllObjections()
        }
        return getAllObjections().filter { objection ->
            objection.title.lowercase().contains(normalizedQuery) ||
            objection.response.lowercase().contains(normalizedQuery)
        }
    }

    private fun loadObjectionsFromJson(): List<Objection> {
        return try {
            val jsonString = readJsonFromAssets("objections.json")
            val json = Json { ignoreUnknownKeys = true }
            json.decodeFromString(jsonString)
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun readJsonFromAssets(fileName: String): String {
        return context.assets.open(fileName).use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).readText()
        }
    }
}
