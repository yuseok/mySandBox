package yuseok.rxcycleappsandbox.activities;

import yuseok.rxcycleappsandbox.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RunOnUIThreadTest extends Activity {

	GoogleMap map1;
	GoogleMap map2;
	TextView[] mText = new TextView[10];
	Thread thread1;
	boolean isThreadRun = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_run_on_uithread_test);

		mText[0] = (TextView) findViewById(R.id.text1);
		mText[1] = (TextView) findViewById(R.id.text2);
		mText[2] = (TextView) findViewById(R.id.text3);
		mText[3] = (TextView) findViewById(R.id.text4);
		mText[4] = (TextView) findViewById(R.id.text5);
		mText[5] = (TextView) findViewById(R.id.text6);
		mText[6] = (TextView) findViewById(R.id.text7);
		mText[7] = (TextView) findViewById(R.id.text8);
		mText[8] = (TextView) findViewById(R.id.text9);
		mText[9] = (TextView) findViewById(R.id.text10);

		map1 = ((MapFragment) getFragmentManager().findFragmentById(R.id.map1)).getMap();
		map2 = ((MapFragment) getFragmentManager().findFragmentById(R.id.map2)).getMap();
		LatLng company = new LatLng(37.5677, 126.9487);
		LatLng home = new LatLng(37.5989911, 126.9201388);

		// 37.5677894,126.9487551,17z

		map1.setMyLocationEnabled(true);
		map2.setMyLocationEnabled(true);

		map1.moveCamera(CameraUpdateFactory.newLatLngZoom(home, 15));
		map2.moveCamera(CameraUpdateFactory.newLatLngZoom(company, 17));

		map1.addMarker(new MarkerOptions().title("우리집").snippet("응암동 110-31번지 402호").position(home));
		map2.addMarker(new MarkerOptions().title("이화여대").snippet("테스트 'ㅡ'").position(company));

		// map1.addPolyline(new PolylineOptions().geodesic(true));

		thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				isThreadRun = true;

				while (true) {
					Log.v("runonui", "still running...");
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							for (int i = 0; i < mText.length; i++) {
								double local = Math.sin((Math.random() * 10));
								mText[i].setText(local + "");
							}
						}
					});
					try {
						Thread.sleep(30);
						if (isThreadRun != true) {
							break;
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});

		thread1.start();

	}

	@Override
	protected void onPause() {
		isThreadRun = false;
		super.onPause();
	}

}
