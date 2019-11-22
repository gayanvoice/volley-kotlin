package com.volley.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.volley.kotlin.*
import com.volley.kotlin.toolbox.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text)

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://raw.githubusercontent.com/gayanvoice/volley-kotlin/master/CONTRIBUTING.md"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                textView.setText(response)
            },
            Response.ErrorListener { textView.text = "That didn't work!" })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)

    }
}
