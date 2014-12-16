package yuseok.rxcycleappsandbox.activities;

import callback.YSAsyncTaskCompareWithNumbers;
import callback.YSCallback;
import yuseok.rxcycleappsandbox.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class CallBackTest extends Activity {
	private final String TAG = "saltfactory.net";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call_back_test);

		new YSAsyncTaskCompareWithNumbers(new YSCallback() {

			@Override
			public void callback() {
				// TODO Auto-generated method stub
				Log.d(TAG, "call preprocessCallback from MainActivity");
			}
		}, new YSCallback() {

			@Override
			public void callback() {
				// TODO Auto-generated method stub
				Log.d(TAG, "call successCallback from MainActivity");

			}
		}, new YSCallback() {

			@Override
			public void callback() {
				// TODO Auto-generated method stub
				Log.d(TAG, "call failCallback from MainActivity");

			}
		}).execute(1, 1);

	}
}
