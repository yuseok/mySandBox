package yuseok.rxcycleappsandbox.activities;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import yuseok.rxcycleappsandbox.R;
import yuseok.rxcycleappsandbox.activities.calculatevalues.CalculatorVer1;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class BikeController extends Activity {

	private final String WORKOUT_START = "H1S";
	private final String WORKOUT_STOP = "H1E";
	private CalculatorVer1 calculator;

	private Button startWorkButton;
	private TextView myLabel;
	private EditText myTextbox;
	private TextView myResult;
	private SeekBar mSeekPower;
	private SeekBar mSeekWind;
	private TextView mFrontWheel;
	private TextView mRearWheel;
	private TextView mFrontGear;
	private TextView mRearGear;
	volatile boolean isStartWorkout = false;

	public BluetoothAdapter mBluetoothAdapter;
	protected BluetoothSocket mSocket;
	protected BluetoothDevice mDevice;

	private OutputStream mOuput;
	private InputStream mInput;

	private Thread workerThread;
	volatile boolean stopWorker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bike_controller);

		Button openButton = (Button) findViewById(R.id.open);
		Button sendButton = (Button) findViewById(R.id.send);
		Button closeButton = (Button) findViewById(R.id.close);
		startWorkButton = (Button) findViewById(R.id.work_start);

		myLabel = (TextView) findViewById(R.id.label);
		myTextbox = (EditText) findViewById(R.id.entry);
		myResult = (TextView) findViewById(R.id.bt_receive);

		mFrontWheel = (TextView) findViewById(R.id.front_wheel);
		mRearWheel = (TextView) findViewById(R.id.rear_wheel);
		mFrontGear = (TextView) findViewById(R.id.front_gear);
		mRearGear = (TextView) findViewById(R.id.rear_gear);

		mSeekPower = (SeekBar) findViewById(R.id.seekBar1);
		mSeekWind = (SeekBar) findViewById(R.id.seekBar2);

		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
			myLabel.setText("블루투스 모듈 지원이 안되요..");
			mSeekPower.setActivated(false);
			mSeekWind.setActivated(false);
			openButton.setActivated(false);
			sendButton.setActivated(false);
			closeButton.setActivated(false);
			startWorkButton.setActivated(false);
			return;
		}
		
		calculator = CalculatorVer1.getInstance();
		
		openButton.setOnClickListener(listener);
		sendButton.setOnClickListener(listener);
		closeButton.setOnClickListener(listener);
		startWorkButton.setOnClickListener(listener);
		mSeekPower.setOnSeekBarChangeListener(seeklistener);
		mSeekWind.setOnSeekBarChangeListener(seeklistener);

	}

	OnSeekBarChangeListener seeklistener = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {

		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			switch (seekBar.getId()) {
			case R.id.seekBar1:
				try {
					if (progress < 10) {
						sendData("P300" + progress);
					} else if (progress > 9 && progress < 100) {
						sendData("P30" + progress);
					} else if (progress > 99) {
						sendData("P3" + progress);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case R.id.seekBar2:
				try {
					if (progress < 10) {
						sendData("F20" + progress);
					} else {
						sendData("F2" + progress);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}

		}
	};

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.open:
				try {
					openBT();
				} catch (Exception e) {
					Log.e("bikeController", "openBT", e);
					Toast.makeText(BikeController.this, "외가 발생했습니다..", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.send:
				try {
					sendData(myTextbox.getText().toString());
				} catch (Exception e) {
					Log.e("bikeController", "sendData", e);
					Toast.makeText(BikeController.this, "예외가 발생했습니다..", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.close:
				try {
					closeBT();
				} catch (Exception e) {
					Log.e("bikeController", "closeBT", e);
					Toast.makeText(BikeController.this, "예외가 발생했습니다..", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.work_start:
				try {
					if (!isStartWorkout) {
						sendData(WORKOUT_START);
						isStartWorkout = true;
						startWorkButton.setText("운동 끝내기");
					} else {
						sendData(WORKOUT_STOP);
						isStartWorkout = false;
						startWorkButton.setText("운동 시작");
					}
				} catch (Exception e) {
					Log.e("bikeController", "work_start", e);
					Toast.makeText(BikeController.this, "예외가 발생했습니다..", Toast.LENGTH_SHORT).show();
				}
				break;
			}

		}

	};

	void openBT() {
		if (!mBluetoothAdapter.isEnabled()) {
			startActivity(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE));
		}
		mDevice = null;

		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
		if (pairedDevices.size() > 0) {
			for (BluetoothDevice device : pairedDevices) {
				if (device.getName().equals("RX_CYCLE")) {
					myLabel.setText("기기를 찾음!");
					mDevice = device;
					break;
				}
			}

			if (mDevice == null) {
				myLabel.setText("페어링 해야함!");
				return;
			}

			workerThread = new Thread(new Writer());
			workerThread.start();

			myLabel.setText("Bluetooth Opened!");
		}

	}

	class Writer implements Runnable {

		public static final byte delimiter = 10;

		@Override
		public void run() {
			try {
				UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
				mSocket = mDevice.createInsecureRfcommSocketToServiceRecord(uuid);
				mSocket.connect();
				mOuput = mSocket.getOutputStream();
				mInput = mSocket.getInputStream();
				stopWorker = false;

			} catch (IOException e) {
				Log.e("bikeController", "Writer", e);
				myLabel.post(new Runnable() {

					@Override
					public void run() {
						myLabel.setText("예외가 발생..");
					}
				});
				stopWorker = true;
			}
			while (!stopWorker) {
				try {
					int bytesAvailable = mInput.available();
					if (bytesAvailable > 0) {
						byte[] packetBytes = new byte[bytesAvailable];
						mInput.read(packetBytes);
						final String data = checkData(new String(packetBytes, "US-ASCII"));

						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								myResult.append(data);
								if (!data.equals("R")) {
									if (data.length() != 9)
										return;
									mFrontWheel.setText(data.substring(2, 4) + " rpm");
									mRearWheel.setText(data.substring(2, 4) + " rpm");
									mFrontGear.setText(data.substring(6, 7));
									mRearGear.setText(data.substring(7, 8));
								}
							}
						});

					} else {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							stopWorker = true;
						}
					}
				} catch (IOException e) {
					stopWorker = true;
				}
			}
		}
	}

	void sendData(String msg) throws IOException {
		msg += "\n";
		mOuput.write(msg.getBytes("US-ASCII"));
		myLabel.setText(msg + "Sent");
	}

	void closeBT() throws IOException {
		stopWorker = true;
		mOuput.close();
		mInput.close();
		mSocket.close();
		myLabel.setText("Bluetooth Closed");
	}

	// CRC 부분
	synchronized String checkData(String data) {
		final String str;
		final int len = data.length();

		Log.v("Bluetooth Data", data);
		switch (data.charAt(0)) {
		case 'D':
			str = len == 9 ? data : "R";
			break;
		case 'P':
			str = len == 6 ? data : "R";
			break;
		case 'H':
			str = len == 4 ? data : "R";
			break;
		case 'F':
			str = len == 5 ? data : "R";
			break;
		case 'B':
			str = len == 5 ? data : "R";
			break;
		default:
			str = "R";
		}
		return str;
	}

}
