# Google Volley Kotlin
Volley-Kotlin is the Kotlin implementation of Volley HTTP library. This approach to convert the library into Kotlin, because there no plans [issues#34](https://github.com/google/volley/issues/34) for Google to rewrite the libary to Kotlin.

>Volley is an HTTP library that makes networking for Android apps easier and most importantly, faster. Volley is available on [GitHub](https://github.com/google/volley).

##  Implementation
### Add the INTERNET permission
Add the permission to INTERNET in the manifest of the app
>To use Volley, you must add the `[android.permission.INTERNET](https://developer.android.com/reference/android/Manifest.permission.html#INTERNET)` permission to your app's manifest. Without this, your app won't be able to connect to the network.

```XML
<uses-permission android:name="android.permission.INTERNET" />
```
#### Send a simple request
Java code relies on SAM conversion - an automatic conversion of a lambda into an interface with a single abstract method. SAM conversion is [currently not supported](https://youtrack.jetbrains.com/issue/KT-7770) for interfaces defined in Kotlin. Instead, you need to define an anonymous object implementing the interface:
```Kotlin
// TextView
val textView = findViewById<TextView>(R.id.text)

// Instantiate the RequestQueue
val queue = Volley.newRequestQueue(this)  
val url = "https://raw.githubusercontent.com/gayanvoice/volley-kotlin/master/data/sample.txt" 
 
// Request a string response from the provided URL
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
```
#### Set up RequestQueue
```Kotlin
// TextView
val textView = findViewById<TextView>(R.id.text)

// Instantiate the RequestQueue
val queue = Volley.newRequestQueue(this) val cache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap  
val network = BasicNetwork(HurlStack())  
val requestQueue = RequestQueue(cache, network).apply {  
 start()
}

val url = "https://raw.githubusercontent.com/gayanvoice/volley-kotlin/master/data/sample.txt" 
 
// Request a string response from the provided URL
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
// Add the request to the RequestQueue
requestQueue.add(stringRequest)
```
## SmartyPants

SmartyPants converts ASCII punctuation characters into "smart" typographic punctuation HTML entities. For example:

|                |ASCII                          |HTML                         |
|----------------|-------------------------------|-----------------------------|
|Single backticks|`'Isn't this fun?'`            |'Isn't this fun?'            |
|Quotes          |`"Isn't this fun?"`            |"Isn't this fun?"            |
|Dashes          |`-- is en-dash, --- is em-dash`|-- is en-dash, --- is em-dash|
