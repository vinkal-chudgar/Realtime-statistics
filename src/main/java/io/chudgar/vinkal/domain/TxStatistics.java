/**
 * 
 */
package io.chudgar.vinkal.domain;

/**
 * @author Vinkal Chudgar
 *
 */

//Immutable. 
public class TxStatistics implements Statistics {

	private final long count;
	private final double sum;
	private final double max;
	private final double min;	
	private final long timeStamp;
	
	public TxStatistics() {
		this.count = 0;
		this.sum = 0;
		this.max = Double.NEGATIVE_INFINITY;
		this.min = Double.POSITIVE_INFINITY;;
		this.timeStamp = 0;
	}

	public TxStatistics(double amount, long timeStamp) {
		this.count = 1;
		this.sum = amount;
		this.max = amount;
		this.min = amount;
		this.timeStamp = timeStamp;
	}

	public TxStatistics(long count, double amount, double max, double min, long timeStamp) {
		this.count = count;
		this.sum = amount;
		this.max = max;
		this.min = min;
		this.timeStamp = timeStamp;
	}

	public long getCount() {
		return count;
	}

	public double getAvg() {
		return count == 0 ? 0 : sum / count;
	}

	public double getSum() {
		return sum;
	}

	public double getMax() {
		return max;
	}

	public double getMin() {
		return min;
	}
	
	public long getTimeStamp() {
		return timeStamp;
	}

}
