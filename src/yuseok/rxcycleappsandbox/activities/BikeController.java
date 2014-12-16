package yuseok.rxcycleappsandbox.activities;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import yuseok.rxcycleappsandbox.R;
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
import android.widget.TextView;
import android.widget.Toast;

public class BikeController extends Activity {

	private TextView myLabel;
	private EditText myTextbox;
	private SeekBar mSeek;

	public BluetoothAdapter mBluetoothAdapter;
	protected BluetoothSocket mSocket;
	protected BluetoothDevice mDevice;

	private OutputStream mOuput;
	private InputStream mInput;

	private Thread workerThread;
	volatile boolean stopWorker;

	private byte[] readBuffer;
	private int readBufferPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bike_controller);

		Button openButton = (Button) findViewById(R.id.open);
		Button sendButton = (Button) findViewById(R.id.send);
		Button closeButton = (Button) findViewById(R.id.close);

		myLabel = (TextView) findViewById(R.id.label);
		myTextbox = (EditText) findViewById(R.id.entry);
		mSeek = (SeekBar) findViewById(R.id.seekBar1);

		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
			myLabel.setText("블루투스 모듈 지원이 안되요..");
			mSeek.setActivated(false);
			openButton.setActivated(false);
			sendButton.setActivated(false);
			closeButton.setActivated(false);
			return;
		}

		openButton.setOnClickListener(listener);
		sendButton.setOnClickListener(listener);
		closeButton.setOnClickListener(listener);

	}

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
					sendData();
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
				readBufferPosition = 0;
				readBuffer = new byte[1024];

			} catch (IOException e) {
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
						for (int i = 0; i < bytesAvailable; i++) {
							byte b = packetBytes[i];
							if (b == delimiter) {
								byte[] encodedBytes = new byte[readBufferPosition];
								System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
								final String data = new String(encodedBytes, "US-ASCII");
								readBufferPosition = 0;

								myLabel.post(new Runnable() {

									@Override
									public void run() {
										myLabel.setText(data);
									}
								});
							} else {
								readBuffer[readBufferPosition] = b;
							}
						}
					} else {
						try {
							Thread.sleep(1000);
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

	void sendData() throws IOException {
		String msg = myTextbox.getText().toString();
		msg += "\n";

		mOuput.write(msg.getBytes());

		myLabel.setText("Data Sent");

	}

	void closeBT() throws IOException {
		stopWorker = true;
		mOuput.close();
		mInput.close();
		mSocket.close();
		myLabel.setText("Bluetooth Closed");
	}
}
