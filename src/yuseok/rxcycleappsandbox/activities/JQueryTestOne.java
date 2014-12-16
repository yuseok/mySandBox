package yuseok.rxcycleappsandbox.activities;

import yuseok.rxcycleappsandbox.R;
import yuseok.rxcycleappsandbox.activities.jquery.NavigationDrawerFragment;
import yuseok.rxcycleappsandbox.activities.jquery.NavigationDrawerFragment.NavigationDrawerCallbacks;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class JQueryTestOne extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	private NavigationDrawerFragment mNavigationDrawerFragment;
	private CharSequence mTitle;
	public static FragmentManager mFragmentManager;

	// 뭐냐 ㅡ,.ㅡ; 왜 걍 종료되냐...

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jquery_test_one);
		mFragmentManager = getFragmentManager();

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();

		switch (position) {
		case 0:
			fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit();
			break;
		case 1:
			fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment2.newInstance(position + 1)).commit();
			break;
		case 2:
			fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment2.newInstance(position + 1)).commit();
			break;
		}
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.jquery_test_one, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */

	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		private GoogleMap mMap;

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.fragment_jquery_test_one, container, false);

			mMap = ((MapFragment) JQueryTestOne.mFragmentManager.findFragmentById(R.id.map1)).getMap();

			return rootView;

		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((JQueryTestOne) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}

		
		@Override
		public void onDestroyView() {
			super.onDestroyView();
			try {
				if (mMap != null) {
					// 전환은 잘 되는데.. 이 프래그먼트 상태에서 backpressed되면 기존 MainActivity까지 같이 종료되는 이유를 찾아봐야함! 'ㅡ '!!!! 꺅..
					getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.map1)).commit();
//					JQueryTestOne.mFragmentManager.beginTransaction().remove(JQueryTestOne.mFragmentManager.findFragmentById(R.id.map1)).commit();
//					mMap = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public static class PlaceholderFragment2 extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment2 newInstance(int sectionNumber) {
			PlaceholderFragment2 fragment = new PlaceholderFragment2();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment2() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.activity_graph_test, container, false);

			return rootView;

		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((JQueryTestOne) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}

	}

}
