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
        val url = "https://raw.githubusercontent.com/gayanvoice/volley-kotlin/master/data/sample.txt"

        /*
        // Instantiate the cache
        val cache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap
        val network = BasicNetwork(HurlStack())
        val requestQueue = RequestQueue(cache, network).apply {
            start()
        }


        // SAM conversion is currently not supported for interfaces defined in Kotlin https://stackoverflow.com/a/43737962
        val stringRequest = StringRequest(Request.Method.GET, url,
            object:Response.Listener<String> {
                override fun onResponse(response: String) {
                    textView.setText(response)
                }
            },
            object:Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    textView.text = error.toString()
                }
            })
        requestQueue.add(stringRequest)



*/

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        //val url = "https://raw.githubusercontent.com/gayanvoice/volley-kotlin/master/data/sample.txt"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
              object:Response.Listener<String> {
                override fun onResponse(response: String) {
                    textView.setText(response)
                }
            },
            object:Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    textView.text = error.toString()
                }
            })
        // Add the request to the RequestQueue.
        queue!!.add(stringRequest)


    }
}
