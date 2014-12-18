package yuseok.rxcycleappsandbox.activities.calculatevalues;

public class CalculatorVer1 implements Calculator {

	/* D로 날라오는 raw datas.. */
	private int mFrontWheel;
	private int mRearWheel;
	private int mFrontGear;
	private int mRearGear;

	/* F로 날려주는 친구들 */
	private int mFanSpeed1;
	private int mFanSpeed2;

	/* P로 날려주는 친구 */
	private int mPower;

	private static CalculatorVer1 instance;

	private CalculatorVer1() {
		instance = new CalculatorVer1();
	}

	public static CalculatorVer1 getInstance() {
		return instance;
	}

	@Override
	public int calculatePower(Integer... val) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int calculateFanSpeed(Integer... val) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFrontWheel(int val) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFrontWheel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRearWheel(int val) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getRearWheel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFrontGear(int val) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFrontGear() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRearGear(int val) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getRearGear() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFanSpeed1(int val) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFanSpeed1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFanSpeed2(int val) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFanSpeed2() {
		// TODO Auto-generated method stub
		return 0;
	}

}
