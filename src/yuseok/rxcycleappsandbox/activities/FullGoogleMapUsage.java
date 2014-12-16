package yuseok.rxcycleappsandbox.activities;

import yuseok.rxcycleappsandbox.R;
import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class FullGoogleMapUsage extends Activity {

	GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_full_google_map_usage);
		mMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.fullMap)).getMap();
		mMap.setMyLocationEnabled(true);
		mMap.setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public void onMapClick(LatLng arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		mMap.isBuildingsEnabled();
		
	}
}
