package yuseok.rxcycleappsandbox.activities;

import yuseok.rxcycleappsandbox.R;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class AsyncTaskTest extends Activity {

	MyAsyncTask task;
	MyAsyncTask[] tasks = new MyAsyncTask[10];
		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_async_task_test);
		for(int i = 0 ; i < tasks.length; i++){
			tasks[i] = new MyAsyncTask();
		}
	}

	@Override
	protected void onResume() {
		Log.d("async", "Resumed");
		task = new MyAsyncTask();
		task.execute("a", "b", "c", "d");
		
		for(int i = 0 ; i < tasks.length; i ++){
			tasks[i].execute("a","b","c","d");
		}
		
		super.onResume();
	}

	@Override
	protected void onStart() { 
		Log.d("async", "Started");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		Log.d("async", "Restarted");
		super.onRestart();
	}

	@Override
	protected void onPause() {
		task.cancel(true);
		for(int i = 0 ; i< tasks.length; i++){
			tasks[i].cancel(true);
		}
		Log.d("async", "Paused");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.d("async", "Stopped");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.d("async", "Destroyed");
		super.onDestroy();
	}

	private class MyAsyncTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {

			final String[] str = params;
			Log.v("async",str.length+"");
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						Log.v("asyncThread1", "><");
						for(int i = 0 ; i < str.length ; i++){
							Log.v("asyncThread1", str[i]);
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (isCancelled()) {
							break;
						}
					}
				}
			}).start();

			
			return null;
		}

	}

}
