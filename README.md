# Google Volley Kotlin
Volley-Kotlin is the Kotlin implementation of Volley HTTP library. This approach to convert the library into Kotlin, because there are no plans [issues#34](https://github.com/google/volley/issues/34) for Google to write the libary to Kotlin.

>Volley is an HTTP library that makes networking for Android apps easier and most importantly, faster. Volley is available on [GitHub](https://github.com/google/volley).

### This library is still under development. You can contribute to this library by converting the exisiting source code in this repository.

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
## Contribution

The following files have been translated and their progress can be shown below

#### `com.volley.kotlin`
 - **[AuthFailureError.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/AuthFailureError.kt)** ✔️
 - **[Cache.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/Cache.kt)** ✔️
 - **[CacheDispatcher.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/CacheDispatcher.java)** ❌
 - **[ClientError.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/ClientError.kt)** ✔️
 - **[DefaultRetryPolicy.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/DefaultRetryPolicy.kt)** ✔️
 - **[ExecutorDelivery.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/ExecutorDelivery.java)** ❌
 - **[Header.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/Header.kt)** ✔️
 - **[Network.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/Network.java)** ✔️
 - **[NetworkDispatcher.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/NetworkDispatcher.java)** ❌
 - **[NetworkError](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/NetworkError.kt)** ✔️
 - **[NetworkResponse.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/NetworkResponse.kt)** ✔️
 - **[NoConnectionError.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/NoConnectionError.kt)** ✔️
 - **[ParseError.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/ParseError.kt)** ✔️
 - **[Request.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/Request.java)** ❌
 - **[RequestQueue.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/RequestQueue.java)** ❌
 - **[Response.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/Response.kt)** ✔️
 - **[ResponseDelivery.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/ResponseDelivery.kt)** ✔️
 - **[RetryPolicy.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/RetryPolicy.kt)** ✔️ 
 - **[ServerError.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/ServerError.kt)** ✔️
 - **[TimeoutError.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/TimeoutError.kt)** ✔️
 - **[VolleyError.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/VolleyError.kt)** ✔️
 - **[VolleyLog.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/VolleyLog.kt)** ✔️

#### `com.volley.kotlin.toolbox`
- **[AdaptedHttpStack.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/AdaptedHttpStack.kt)** ✔️
- **[AndroidAuthenticator.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/AndroidAuthenticator.java)** ❌
- **[Authenticator.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/Authenticator.kt)** ✔️
- **[BaseHttpStack.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/BaseHttpStack.java)** ❌
- **[BasicNetwork.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/BasicNetwork.java)** ❌
- **[ByteArrayPool.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/ByteArrayPool.java)** ❌
- **[ClearCacheRequest.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/ClearCacheRequest.kt)** ✔️
- **[DiskBasedCache.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/DiskBasedCache.java)** ❌
- **[HttpClientStack.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/HttpClientStack.java)** ❌
- **[HttpHeaderParser.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/HttpHeaderParser.java)** ❌

- **[HttpResponse.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/HttpResponse.java)** ❌
- **[ImageLoader.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/ImageLoader.java)** ❌
- **[ImageRequest.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/ImageRequest.java)** ❌
- **[JsonArrayRequest.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/JsonArrayRequest.java)** ❌
- **[JsonObjectRequest.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/JsonObjectRequest.java)** ❌
- **[JsonArrayRequest.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/JsonArrayRequest.java)** ❌
- **[JsonObjectRequest.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/JsonObjectRequest.java)** ❌
- **[JsonRequest.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/JsonRequest.java)** ❌
- **[NoCache.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/NoCache.kt)** ✔️
- **[PoolingByteArrayOutputStream.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/PoolingByteArrayOutputStream.kt)** ✔️
- **[RequestFuture.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/RequestFuture.java)** ❌
- **[StringRequest.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/StringRequest.java)** ❌
- **[Threads.kt](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/Threads.kt)** ✔️
- **[Volley.java](https://github.com/gayanvoice/volley-kotlin/blob/master/library/src/main/java/com/volley/kotlin/toolbox/Volley.java)** ❌
 
