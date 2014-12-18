package yuseok.rxcycleappsandbox;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewMaster extends Activity {

	WebView mWebView1;
	WebView mWebView2;
	WebView mWebView3;
	WebView mWebView4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view_master);

		mWebView1 = (WebView) findViewById(R.id.webview_1);
		mWebView1.setWebViewClient(new WebViewClient1());
		mWebView1.setWebChromeClient(new WebChromeClinet1());

		mWebView2 = (WebView) findViewById(R.id.webview_2);
		mWebView2.setWebViewClient(new WebViewClient2());
		mWebView2.setWebChromeClient(new WebChromeClinet2());

		mWebView3 = (WebView) findViewById(R.id.webview_3);
		mWebView3.setWebViewClient(new WebViewClient3());
		mWebView3.setWebChromeClient(new WebChromeClinet3());

		mWebView4 = (WebView) findViewById(R.id.webview_4);
		mWebView4.setWebViewClient(new WebViewClient4());
		mWebView4.setWebChromeClient(new WebChromeClinet4());

	}

	private class WebViewClient1 extends WebViewClient {

	}

	private class WebChromeClinet1 extends WebChromeClient {

	}

	private class WebViewClient2 extends WebViewClient {

	}

	private class WebChromeClinet2 extends WebChromeClient {

	}

	private class WebViewClient3 extends WebViewClient {

	}

	private class WebChromeClinet3 extends WebChromeClient {

	}

	private class WebViewClient4 extends WebViewClient {

	}

	private class WebChromeClinet4 extends WebChromeClient {

	}
}
