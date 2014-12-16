package yuseok.rxcycleappsandbox.activities;

import yuseok.rxcycleappsandbox.R;
import yuseok.rxcycleappsandbox.activities.bluetooth.BluetoothServiceTest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BluetoothTest extends Activity {

	private BluetoothServiceTest mBTService;
	private TextView mText;
	private Button mConnectBtn;
	private Button mDisConnectBtn;
	private EditText mEdit;
	private String mMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bluetooth_test);

		if (mBTService == null) {
			mBTService = new BluetoothServiceTest(this);
		}

		mText = (TextView) findViewById(R.id.result);
		mConnectBtn = (Button) findViewById(R.id.connection);
		mDisConnectBtn = (Button) findViewById(R.id.disconnection);
		mEdit = (EditText) findViewById(R.id.msg);
		
		mMsg = mEdit.getText().toString();

		mConnectBtn.setOnClickListener(listener);
		mDisConnectBtn.setOnClickListener(listener);
	}

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.connection:
				if (mBTService.getDeviceState()) {
					mBTService.enableBluetooth();
				} else {
					Toast.makeText(getApplicationContext(), "블투 지원 안하는 기기", Toast.LENGTH_LONG).show();
					finish();
				}
				break;
			case R.id.disconnection:
				break;
			}
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {
		case BluetoothServiceTest.REQUEST_ENABLE_BT:
			if (resultCode == Activity.RESULT_OK) {
				mBTService.scanDevice();
			} else {
				Toast.makeText(this, "켰어야지..", Toast.LENGTH_SHORT).show();
				finish();
			}
			break;
		case BluetoothServiceTest.REQUEST_CONNECT_DEVICE:
			if (resultCode == Activity.RESULT_OK) {
				mBTService.getDeviceInfo(data);
			}
			break;
		}

	}

}
