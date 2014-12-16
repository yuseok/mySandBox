package yuseok.rxcycleappsandbox.activities;

import java.io.IOException;

import yuseok.rxcycleappsandbox.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class JQueryTestTwo extends Activity {

	WebView webview;
	Spinner spinner;
	String[] spinnerArray;
	final String ASSET_PATH = "file:///android_asset/www/";
	String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jquery_test_two);

		spinner = (Spinner) findViewById(R.id.spinner);
		try {
			spinnerArray = getResources().getAssets().list("www");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
		spinner.setAdapter(adapter);
		try {
			Log.v("assets", getResources().getAssets().list("www").length + "");
			for (String str : getResources().getAssets().list("www")) {
				Log.v("assetlist", str);
			}
			Log.v("assets", getResources().getAssets().list("www")[0] + "");
		} catch (IOException e) {
			e.printStackTrace();
			Log.v("assets", getResources().getAssets().toString());
		}
		OnItemSelectedListener listener = new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

				try {
					url = getResources().getAssets().list("www")[arg2];
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				webview.loadUrl(ASSET_PATH + url);
				webview.reload();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				webview.reload();
			}
		};
		spinner.setOnItemSelectedListener(listener);

		webview = (WebView) findViewById(R.id.webview);
		webview.setWebViewClient(new WebClient());
		webview.setWebChromeClient(new WebChromeClient(){

			@Override
			public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
				Log.d("webchromeclient", message);
				result.confirm();
				return true;
			
			}
			
		});
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setBuiltInZoomControls(false);
		webview.addJavascriptInterface(new JavaScriptInterface(this), "jsInterface");
		

		try {
			webview.loadUrl(ASSET_PATH + getResources().getAssets().list("www")[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		if(webview.canGoBack()){
			webview.goBack();
		}else{
			super.onBackPressed();
		}
		
	}
	class JavaScriptInterface{
		
		Context mContext;
		public JavaScriptInterface(Context context){
			mContext = context;
		}
		
		@JavascriptInterface
		public String showToast(String msg){
			Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
			return msg;
		}
		public int makeItDoubleByJava (int val){
			return val*2;
		}
	}
	
	class WebClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

	}
}
