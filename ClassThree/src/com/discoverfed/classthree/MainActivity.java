package com.discoverfed.classthree;



import android.os.Bundle;
import android.app.Activity;

import android.view.Menu;
import android.webkit.WebView;


public class MainActivity extends Activity {

	WebView webview;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		webview = (WebView) this.findViewById(R.id.webView1);
		webview.loadUrl("http://itp.nyu.edu/itp/");
		//webview.loadUrl("file://android_assets/Index.html");
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
