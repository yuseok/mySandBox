package yuseok.rxcycleappsandbox.activities;

import yuseok.rxcycleappsandbox.R;
import yuseok.rxcycleappsandbox.activities.bluetoothVersions.BLE_Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BluetoothNewer extends FragmentActivity {

	FragmentManager mFragmentManager;
	BLE_Fragment BLE;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.ble_conatiner);
		mFragmentManager = getFragmentManager();
		BLE = new BLE_Fragment();
		mFragmentManager.beginTransaction().add(R.id.container, BLE, "LOL").commit();
	}

}
