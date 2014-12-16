package yuseok.rxcycleappsandbox.activities;

import yuseok.rxcycleappsandbox.R;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SoundFXTest extends Activity {

	TextView text;
	ListView list;
	ImageView img;

//	private class kyakya {
//		kyakya redd() {
//			return this.redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd()
//					.redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd()
//					.redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd().redd();
//		}
//	}

	private OnClickListener onclick = new OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.musicStart:
				musicStart();
				break;
			case R.id.musicSTOP:
				musicStop();
				break;

			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sound_fxtest);

		findViewById(R.id.musicStart).setOnClickListener(onclick);
		findViewById(R.id.musicSTOP).setOnClickListener(onclick);
		text = (TextView) findViewById(R.id.textResult);
		img = (ImageView) findViewById(R.id.albumCover);
		list = (ListView) findViewById(R.id.list);

	}

	private int musicStart() {
//		Toast.makeText(this, "음악이 시작됩니다", Toast.LENGTH_SHORT).show();
		return 0;
	}

	private int musicStop() {
//		Toast.makeText(this, "음악이 멈춥니다", Toast.LENGTH_SHORT).show();
		return 0;
	}

	private Runnable musicRunnable = new Runnable() {

		@Override
		public void run() {

		}
	};
	private Thread musicThread = new Thread(musicRunnable);
	private backgroundPlayMusic musicAsync = new backgroundPlayMusic();

	private class backgroundPlayMusic extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(String... params) {
			musicThread.start();
			return null;
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if (!musicAsync.isCancelled()) {
			musicAsync.cancel(true);
		}

		super.onPause();
	}

	@Override
	protected void onResume() {
		if (!musicAsync.isCancelled()) {
			musicAsync.execute();
		}
		super.onResume();
	}

	// private class requestHTTP extends HttpRequestBase{
	//
	// @Override
	// public String getMethod() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// }

}
