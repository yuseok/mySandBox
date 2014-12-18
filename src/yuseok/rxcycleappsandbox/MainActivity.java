package yuseok.rxcycleappsandbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 
 * @author ohyuseok
 * @version 0.1
 * @category TestApplications for RXCycleApp
 */

public class MainActivity extends Activity {

	ListView mListView;
	private final String[] mListData = { "ThreadTest", "AsyncTaskTest", "GraphTest", "RunOnUIThreadTest", "SoundFXTest", "GoogleMapFullyUsage", "JQueryTestOne", "JQueryTestTwo",
			"CallBackTest", "BluetoothTest", "BluetoothNewer", "ExampleBT", "DownImage", "AndroidCmmExample", "IntentTest", "BikeController", "WidgetLibrary", "WebViewMaster" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mListView = (ListView) findViewById(R.id.lv_list);
		mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListData));
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long longposition) {

				try {
					startActivity(new Intent(getApplicationContext(), Class.forName(getPackageName() + ".activities." + mListData[position])));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			}
		});
	}
}
