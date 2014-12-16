package yuseok.rxcycleappsandbox.activities.bluetooth;

public class BluetoothCommand {

	public static byte[] BLUETOOTH_CONNECTION = { (byte) 0xAA, (byte) 0x43, (byte) 0x4f, (byte) 0x4e, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xE0, (byte) 0x55 };
	public static byte[] BLUETOOTH_CONNECTION_RESPONSE = { (byte) 0xAA, (byte) 0x44, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xff, (byte) 0x55 };
	
	public static byte[] BLUETOOTH_READ_DEVICE_NAME = { (byte) 0xAA, (byte) 0x43, (byte) 0x4f, (byte) 0x4e, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xe0, (byte) 0x55 };
	public static byte[] BLUETOOTH_READ_DEVICE_NAME_RESPONSE = { (byte) 0xAA, (byte) 0x43, (byte) 0x4f, (byte) 0x4e, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xe0, (byte) 0x55 };

	public static byte[] BLUETOOTH_SET_DEVICE_NAME = { (byte) 0xAA, (byte) 0x43, (byte) 0x4f, (byte) 0x4e, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xe0, (byte) 0x55 };
	public static byte[] BLUETOOTH_SET_DEVICE_NAME_RESPONSE = { (byte) 0xAA, (byte) 0x43, (byte) 0x4f, (byte) 0x4e, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xe0, (byte) 0x55 };

	public static byte[] BLUETOOTH_START_WORKOUT = { (byte) 0xAA, (byte) 0x43, (byte) 0x4f, (byte) 0x4e, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xe0, (byte) 0x55 };
	public static byte[] BLUETOOTH_START_WORKOUT_RESPONSE = { (byte) 0xAA, (byte) 0x43, (byte) 0x4f, (byte) 0x4e, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xe0, (byte) 0x55 };
	
	public static byte[] BLUETOOTH_STOP_WORKOUT = { (byte) 0xAA, (byte) 0x43, (byte) 0x4f, (byte) 0x4e, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xe0, (byte) 0x55 };
	public static byte[] BLUETOOTH_STOP_WORKOUT_RESPONSE = { (byte) 0xAA, (byte) 0x43, (byte) 0x4f, (byte) 0x4e, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xe0, (byte) 0x55 };
	
	public static byte[] BLUETOOTH_SEND_DATA = { (byte) 0xAA, (byte) 0x43, (byte) 0x4f, (byte) 0x4e, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xe0, (byte) 0x55 };
	public static byte[] BLUETOOTH_RECEIVE_DATA = { (byte) 0xAA, (byte) 0x43, (byte) 0x4f, (byte) 0x4e, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xe0, (byte) 0x55 };

	
	

}
