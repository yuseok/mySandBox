package yuseok.rxcycleappsandbox.activities;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import yuseok.rxcycleappsandbox.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class DownImage extends Activity {

	ImageView mImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_down_image);
		mImage = (ImageView) findViewById(R.id.result);
	}

	public void mOnClick(View v) {

		switch (v.getId()) {
		case R.id.btndown:
			new DownThread("http://img.naver.net/static/www/u/2013/0731/nmms_224940510.gif").start();
			break;
		}

	}

	private class DownThread extends Thread {
		String mAddr;

		public DownThread(String url) {
			mAddr = url;

		}

		@Override
		public void run() {
			try {
				InputStream is = new URL(mAddr).openStream();
				Bitmap bit = BitmapFactory.decodeStream(is);
				is.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.down_image, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
