package main;

public class Range {
	private int min, max;

	public Range(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}
	
	public int getRandom() {
		return NumeriCasualiGhz.estraiIntero(min, max);
	}
	
	public void setMin(int min) {
		this.min = min;
	}
	
	public void setMax(int max) {
		this.max = max;
	}
	
	public void reset(int min, int max) {
		this.min = min;
		this.max = max;
	}
}