/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.volley.kotlin;

import android.os.Handler;
import java.util.concurrent.Executor;

/*

package com.volley.kotlin

import android.os.Handler
import java.util.concurrent.Executor

open class ExecutorDelivery : ResponseDelivery{
    private var mResponsePoster: Executor? = null
    constructor(handler: Handler) { // Make an Executor that just wraps the handler.
        mResponsePoster = Executor { command -> handler.post(command) }
    }

    constructor(executor: Executor?) {
        mResponsePoster = executor
    }

    override fun postResponse(request: Request<*>?, response: Response<*>?) {
        postResponse(request, response, null)
    }


    override fun postResponse(request: Request<*>?, response: Response<*>?, runnable: Runnable?) {
        request!!.markDelivered()
        request.addMarker("post-response")
        mResponsePoster!!.execute(ResponseDeliveryRunnable(request, response!!, runnable!!))
    }

    override fun postError(request: Request<*>?, error: VolleyError?) {
        request!!.addMarker("post-error")
        val response: Response<*> = Response!!.error(error)
        mResponsePoster!!.execute(ResponseDeliveryRunnable(request, response, null))
    }


    inner class ResponseDeliveryRunnable: Runnable {
        var mRequest: Request<*>? = null
        var mResponse: Response<*>? = null
        var mRunnable: Runnable? = null

        constructor(request: Request<*>, response: Response<*>, runnable: Runnable) {
            this.mRequest = request
            this.mResponse = response
            this.mRunnable = runnable
        }

        @SuppressWarnings("unchecked")
        override fun run(){
            if (mRequest!!.isCanceled) {
                mRequest!!.finish("canceled-at-delivery")
                return
            }


            if (mResponse!!.isSuccess) {
                mRequest!!.deliverResponse(mResponse!!.result)
            } else {
                mRequest!!.deliverError(mResponse!!.error)
            }


            if (mResponse!!.intermediate) {
                mRequest!!.addMarker("intermediate-response")
            } else {
                mRequest!!.finish("done")
            }

            if (mRunnable != null) {
                mRunnable!!.run()
            }

        }

    }

}

 */



public class ExecutorDelivery implements ResponseDelivery {

    private final Executor mResponsePoster;

    public ExecutorDelivery(final Handler handler) {
        // Make an Executor that just wraps the handler.
        mResponsePoster =
                new Executor() {
                    @Override
                    public void execute(Runnable command) {
                        handler.post(command);
                    }
                };
    }

    public ExecutorDelivery(Executor executor) {
        mResponsePoster = executor;
    }

    @Override
    public void postResponse(Request<?> request, Response<?> response) {
        postResponse(request, response, null);
    }

    @Override
    public void postResponse(Request<?> request, Response<?> response, Runnable runnable) {
        request.markDelivered();
        request.addMarker("post-response");
        mResponsePoster.execute(new ResponseDeliveryRunnable(request, response, runnable));
    }

    @Override
    public void postError(Request<?> request, VolleyError error) {
        request.addMarker("post-error");
        Response<?> response = Response.Companion.error(error);
        mResponsePoster.execute(new ResponseDeliveryRunnable(request, response, null));
    }

    /** A Runnable used for delivering network responses to a listener on the main thread. */
    @SuppressWarnings("rawtypes")
    private static class ResponseDeliveryRunnable implements Runnable {
        private final Request mRequest;
        private final Response mResponse;
        private final Runnable mRunnable;

        public ResponseDeliveryRunnable(Request request, Response response, Runnable runnable) {
            mRequest = request;
            mResponse = response;
            mRunnable = runnable;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void run() {


            if (mRequest.isCanceled()) {
                mRequest.finish("canceled-at-delivery");
                return;
            }

            if (mResponse.isSuccess()) {
                mRequest.deliverResponse(mResponse.getResult());
            } else {
                mRequest.deliverError(mResponse.getError());
            }

            if (mResponse.getIntermediate()) {
                mRequest.addMarker("intermediate-response");
            } else {
                mRequest.finish("done");
            }


            if (mRunnable != null) {
                mRunnable.run();
            }
        }
    }
}
