package com.example.gsontojson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = Gson()

        //Конвертация из строки json в объект
        val resultObject = gson.fromJson(json, JsonModel::class.java)
        Log.d("TAG", resultObject.toString())

        val profileJsonString = gson.toJson(JsonObject().apply {
            addProperty("dob", resultObject.profile.dob)
            addProperty("name", resultObject.profile.name)
            addProperty("address", resultObject.profile.address)
            addProperty("company", resultObject.profile.company)
            addProperty("location", gson.toJson(resultObject.profile.location))
        })

        //Конвертация объектов в строку Json
        val resultJsonString = gson.toJson(JsonObject().apply {
            addProperty("id", resultObject.id)
            addProperty("email", resultObject.email)
            addProperty("roles", gson.toJson((resultObject.roles)))
            addProperty("apiKey", resultObject.apiKey)
            addProperty("profile", profileJsonString)
            addProperty("username", resultObject.username)
            addProperty("createdAt", resultObject.createdAt)
            addProperty("updatedAt", resultObject.updatedAt)
        })
        Log.d("TAG", resultJsonString)
    }
}