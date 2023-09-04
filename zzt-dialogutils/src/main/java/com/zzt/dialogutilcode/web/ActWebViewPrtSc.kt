package com.zzt.dialogutilcode.web

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.zzt.dialogutilcode.R

class ActWebViewPrtSc : AppCompatActivity() {
    val TAG = ActWebViewPrtSc::class.java.simpleName

    var iv_pic: ImageView? = null
    var button1: Button? = null
    var button2: Button? = null
    var newScaleWeb: Float = 0F

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ActWebViewPrtSc::class.java)
            context.startActivity(starter)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_web_view_prt_sc)
        val web_view = findViewById<WebView>(R.id.web_view)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebView.enableSlowWholeDocumentDraw();
        }
        setWebSetting(web_view)
        web_view.loadUrl("http://test-cdn.daily-fx.net/app/activity/worldcup/index.html#/Share?language=zh-CN")

        findViewById<Button>(R.id.button).setOnClickListener {
            val captureWebView = captureWebView(web_view)
            iv_pic?.setImageBitmap(captureWebView)
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            val bit = captureWebView11(web_view)
            iv_pic?.setImageBitmap(bit)
        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            val bit = captureWebView12(web_view)
            iv_pic?.setImageBitmap(bit)
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            val bit = captureWebView4(web_view)
            iv_pic?.setImageBitmap(bit)
        }
        findViewById<Button>(R.id.button5).setOnClickListener {
            val bit = SSSSS.shotWebView(web_view)
            iv_pic?.setImageBitmap(bit)
        }
        button1 = findViewById(R.id.button1)
        button1?.setOnClickListener {
            val bit1 = captureWebView(this@ActWebViewPrtSc)
            iv_pic?.setImageBitmap(bit1)
        }
        iv_pic = findViewById(R.id.iv_pic)
    }


    private fun captureWebView(webView: WebView): Bitmap? {
        val bitmap = webView.drawingCache
//        try {
//            val fileName = Environment.getExternalStorageDirectory().path + "/webview_jietu.jpg"
//            val fos = FileOutputStream(fileName)
//            //压缩bitmap到输出流中
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fos)
//            bitmap.recycle()
//            fos.close()
//        } catch (e: Exception) {
//            Log.e(TAG, e.message!!)
//        } finally {
//            bitmap.recycle()
//        }
        return bitmap
    }

    private fun captureWebView11(webView: WebView): Bitmap? {
        val picture: Picture = webView.capturePicture()
        val width: Int = picture.getWidth()
        val height: Int = picture.getHeight()
        if (width > 0 && height > 0) {
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            val canvas = Canvas(bitmap)
            picture.draw(canvas)
            return bitmap
        }
        return null
    }


    private fun captureWebView12(webView: WebView): Bitmap? {
        val scale = newScaleWeb
        val width = webView.width
        val height = (webView.height * scale).toInt()
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        val canvas = Canvas(bitmap)
        webView.draw(canvas)
        return bitmap
    }

    private fun captureWebView4(mWebView: WebView): Bitmap? {
        mWebView.measure(
            View.MeasureSpec.makeMeasureSpec(
                View.MeasureSpec.UNSPECIFIED,
                View.MeasureSpec.UNSPECIFIED
            ),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        mWebView.layout(0, 0, mWebView.measuredWidth, mWebView.measuredHeight);
        mWebView.isDrawingCacheEnabled = true;
        mWebView.buildDrawingCache();
        var longImage: Bitmap = Bitmap.createBitmap(
            mWebView.measuredWidth,
            mWebView.measuredHeight,
            Bitmap.Config.ARGB_8888
        )

        var canvas = Canvas(longImage)    // 画布的宽高和 WebView 的网页保持一致
        var paint = Paint()
        var src = Rect(
            0, 0, mWebView.measuredWidth,
            mWebView.measuredHeight
        )
        canvas.drawBitmap(longImage, src, src, paint);
        mWebView.draw(canvas);
        return longImage
    }


    private fun captureWebView(activity: Activity): Bitmap? {
        val view = activity.window.decorView
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    fun setWebSetting(webView: WebView) {
        webView.getSettings().setDefaultTextEncodingName("UTF-8")
        webView.getSettings().setSupportMultipleWindows(true) // 设置允许开启多窗口

        webView.getSettings().setDomStorageEnabled(true)
        // 开启javascript 渲染
        webView.getSettings().setJavaScriptEnabled(true)

        webView.getSettings().setPluginState(WebSettings.PluginState.ON)

        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH)

//        webView.getSettings().setAppCacheEnabled(true)

        webView.getSettings().setDatabaseEnabled(true)
        webView.getSettings().setLoadsImagesAutomatically(true) //支持自动加载图片

        webView.getSettings().setAllowFileAccessFromFileURLs(true)
        webView.getSettings().setAllowContentAccess(true)
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true)


        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT) //根据cache-control决定是否从网络上取数据。。


        webView.setWebViewClient(object : WebViewClient() {
            override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float) {
                super.onScaleChanged(view, oldScale, newScale)
                newScaleWeb = newScale
                Log.d(TAG, ">>>>>>>>>>>>>>onScaleChanged>>>>>>>>>>>>>>>>>")
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

                Log.d(TAG, ">>>>>>>>>>>>>>shouldOverrideUrlLoading>>>>>>>>>>>>>>>>>")
                view.loadUrl(url)
                return true
            }

            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                Log.d(TAG, ">>>>>>>>>>>>>>shouldOverrideUrlLoading>>>>>>>>>>>>>>>>>")
                return super.shouldOverrideUrlLoading(view, request)
            }
        })

    }
}