package yuseok.rxcycleappsandbox.activities.calculatevalues;

/**
 * @author ohyuseok
 * @category bluetooth
 * @version 0.1
 * 
 */

public interface Calculator {
	/**
	 * calculate power
	 * 
	 * @param values
	 * @return fan power
	 */
	int calculatePower(Integer... val);

	/**
	 * calculate fan speed
	 * 
	 * @param values
	 * @return fan speed
	 */
	int calculateFanSpeed(Integer... val);

	/* getter & setter */
	/**
	 * set front wheel speed
	 * 
	 * @param val
	 */
	void setFrontWheel(int val);

	/**
	 * get front wheel speed
	 * 
	 * @return front wheel speed
	 */
	int getFrontWheel();

	/**
	 * set rear wheel speed
	 * 
	 * @param val
	 */
	void setRearWheel(int val);

	/**
	 * get rear wheel speed
	 * 
	 * @return front wheel speed
	 */
	int getRearWheel();

	/**
	 * set front gear level
	 * 
	 * @param val
	 */
	void setFrontGear(int val);

	/**
	 * get front gear level
	 * 
	 * @return front gear lv.
	 */
	int getFrontGear();

	/**
	 * set rear gear level
	 * 
	 * @param val
	 */
	void setRearGear(int val);

	/**
	 * get rear gear level
	 * 
	 * @return rear gear level
	 */
	int getRearGear();

	/**
	 * set Fan1 speed
	 * 
	 * @param val
	 */
	void setFanSpeed1(int val);

	/**
	 * get Fan1 speed
	 * 
	 * @return FAN1 speed
	 */
	int getFanSpeed1();

	/**
	 * set Fan2 speed
	 * 
	 * @param val
	 */
	void setFanSpeed2(int val);

	/**
	 * get Fan2 speed
	 * 
	 * @return FAN2 speed
	 */
	int getFanSpeed2();

}
