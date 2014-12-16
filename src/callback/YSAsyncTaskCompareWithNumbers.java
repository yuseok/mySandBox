package callback;

import android.os.AsyncTask;

public class YSAsyncTaskCompareWithNumbers extends AsyncTask<Number, Void, Boolean> {

	private YSCallback preprocessCallback;
	private YSCallback successCallback;
	private YSCallback failCallback;

	public YSAsyncTaskCompareWithNumbers(YSCallback preprocessCallback, YSCallback successCallback, YSCallback failCallback) {
		this.preprocessCallback = preprocessCallback;
		this.successCallback = successCallback;
		this.failCallback = failCallback;
	}

	@Override
	protected Boolean doInBackground(Number... params) {

		if (preprocessCallback != null) {
			preprocessCallback.callback();
		}
		return params[0].equals(params[1]);
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);

		if (result) {
			successCallback.callback();
		} else {
			failCallback.callback();
		}

	}

}
