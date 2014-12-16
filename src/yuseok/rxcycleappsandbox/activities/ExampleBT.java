package yuseok.rxcycleappsandbox.activities;

import yuseok.rxcycleappsandbox.R;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ExampleBT extends Activity {

	BluetoothAdapter mBTAdapter;
	Button mBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_example_bt);
		
		mBtn = (Button)findViewById(R.id.bt_scan);
		mBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				doDiscovery();
				v.setVisibility(View.GONE);
			}
		});
		mBTAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBTAdapter == null) {
			Toast.makeText(this, "Bluetooth is not available!", Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
	}
	
	private void doDiscovery(){
		if(mBTAdapter.isDiscovering()){
			mBTAdapter.cancelDiscovery();
		}
		mBTAdapter.startDiscovery();
	}
	
	private static final int REQUEST_CONNECT_DEVICE =1;
	private static final int REQUEST_ENABLE_BT = 2;
	
	@Override
	protected void onStart() {
		if(!mBTAdapter.isEnabled()){
			startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), REQUEST_ENABLE_BT);
		}
		super.onStart();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode){
		case REQUEST_CONNECT_DEVICE:
			//temp ...
			break;
		case REQUEST_ENABLE_BT:
			if(resultCode == Activity.RESULT_OK){
				setupChat();
			}else{
				Toast.makeText(this, "바봉가..", Toast.LENGTH_SHORT).show();
				finish();
			}
			break;
		}
	}
	
	void setupChat(){
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.example_bt, menu);
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
	
	private static long back_pressed = 0;

	@Override
	public void onBackPressed() {
		if (back_pressed + 2000 > System.currentTimeMillis()) {
			super.onBackPressed();
		} else {
			Toast.makeText(getBaseContext(), "한번 더 누르면 꺼짐", Toast.LENGTH_SHORT)
					.show();
			back_pressed = System.currentTimeMillis();
		}

	}
}

class BluetoothStateReceiver extends BroadcastReceiver{
	public static int BLUETOOTH_STATE_UNKNOWN = -1;
	public static int BLUETOOTH_STATE_OFF = BluetoothAdapter.STATE_OFF;
	public static int BLUETOOTH_STATE_TURNING_ON = BluetoothAdapter.STATE_TURNING_ON;
	public static int BLUETOOTH_STATE_TURNING_OFF = BluetoothAdapter.STATE_TURNING_OFF;
	public static int BLUETOOTH_STATE_ON = BluetoothAdapter.STATE_ON;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,BLUETOOTH_STATE_UNKNOWN);
		onBluetoothStateChanged(state);
	}
	private void onBluetoothStateChanged(int state){

	}
	
}
