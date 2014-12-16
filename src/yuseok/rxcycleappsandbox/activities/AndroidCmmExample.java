package yuseok.rxcycleappsandbox.activities;

import java.util.Set;

import yuseok.rxcycleappsandbox.R;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidCmmExample extends Activity {
	private static final int REQUEST_ENABLE_BT = 1;
	public static int BLUETOOTH_STATE_UNKNOWN = -1;

	ListView listDevicesFound;
	Button btnScanDevice;
	TextView txtStateBluetooth;
	BluetoothAdapter bluetoothAdapter;
	ArrayAdapter<String> btArrayAdapter;
	Set<BluetoothDevice> pairedDevices;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS); // progress
																		// bar...
		setContentView(R.layout.activity_android_cmm_example);

		btnScanDevice = (Button) findViewById(R.id.scandevice);
		txtStateBluetooth = (TextView) findViewById(R.id.bluetoothstate);
		listDevicesFound = (ListView) findViewById(R.id.devicesfound);
		btArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		listDevicesFound.setAdapter(btArrayAdapter);
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		if (bluetoothAdapter == null) {
			txtStateBluetooth.setText("블루투스 지원하는 기기가 아님다...");
			return;
		}

		if (bluetoothAdapter.isEnabled()) {

			if (bluetoothAdapter.isDiscovering()) {
				txtStateBluetooth.setText("Bluetooth is currently in device discovery..");

			} else {
				txtStateBluetooth.setText("Bluetooth is Enabled!");
				btnScanDevice.setEnabled(true);

				pairedDevices = bluetoothAdapter.getBondedDevices();
				if (pairedDevices.size() > 0) {
					for (BluetoothDevice device : pairedDevices) {
						btArrayAdapter.add(device.getName() + '\n' + device.getAddress());
					}
				}

			}
		} else {
			txtStateBluetooth.setText("Bluetooth is NOT enabled!!");
			startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), REQUEST_ENABLE_BT);
		}

		btnScanDevice.setOnClickListener(ScanDevice);

		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
		filter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		registerReceiver(BluetoothStateReceiver, filter);

	}
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(BluetoothStateReceiver);
	}

	private OnClickListener ScanDevice = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (bluetoothAdapter.isDiscovering())
				return;
			btArrayAdapter.clear();
			setProgressBarIndeterminateVisibility(true);
			bluetoothAdapter.startDiscovery();
			if (pairedDevices == null) {
				pairedDevices = bluetoothAdapter.getBondedDevices();
			}
			if (pairedDevices.size() > 0) {
				for (BluetoothDevice btdevice : pairedDevices) {
					btArrayAdapter.add(btdevice.getName() + '\n' + btdevice.getAddress());
				}
			}

		}

	};

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case REQUEST_ENABLE_BT:
			if (resultCode == Activity.RESULT_OK) {
				Toast.makeText(this, "블루투스 활성화 되었슴..!", Toast.LENGTH_SHORT).show();
				btnScanDevice.setEnabled(true);
				txtStateBluetooth.setText("Bluetooth is Enabled!");
			} else {
				Toast.makeText(this, "취소하였거나 오류...", Toast.LENGTH_SHORT).show();
				finish();
			}
			break;
		}

	};

	private final BroadcastReceiver BluetoothStateReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

				btArrayAdapter.add(device.getName() + "\n" + device.getAddress());
				btArrayAdapter.notifyDataSetChanged();
			} else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
				setProgressBarIndeterminateVisibility(false);
				Toast.makeText(AndroidCmmExample.this, "검색 끝..", Toast.LENGTH_SHORT).show();

			} else {
				// BluetoothAdapter.ACTION_STATE_CHANGED
				int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BLUETOOTH_STATE_UNKNOWN);
				String Message = null;

				switch (state) {
				case BluetoothAdapter.STATE_CONNECTED:
					Message = "STATE_CONNECTED";
					break;
				case BluetoothAdapter.STATE_CONNECTING:
					Message = "STATE_CONNECTING";
					break;
				case BluetoothAdapter.STATE_DISCONNECTED:
					Message = "STATE_DISCONNECTED";
					break;
				case BluetoothAdapter.STATE_DISCONNECTING:
					Message = "STATE_DISCONNECTING";
					break;
				case BluetoothAdapter.STATE_OFF:
					Message = "STATE_OFF";
					btnScanDevice.setEnabled(false);
					txtStateBluetooth.setText("Bluetooth is NOT enabled!!");
					break;
				case BluetoothAdapter.STATE_ON:
					Message = "STATE_ON";
					btnScanDevice.setEnabled(true);
					txtStateBluetooth.setText("Bluetooth is Enabled!");
					break;
				case BluetoothAdapter.STATE_TURNING_ON:
					Message = "STATE_TURNING_ON";
					break;
				case BluetoothAdapter.STATE_TURNING_OFF:
					Message = "STATE_TURNING_OFF";
					break;
				default:
					break;
				}
				Toast.makeText(AndroidCmmExample.this, Message, Toast.LENGTH_LONG).show();
			}

		}

	};

}
