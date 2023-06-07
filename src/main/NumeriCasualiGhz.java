package main;
import java.util.*;

public class NumeriCasualiGhz {

	private static Random estrattore = new Random();

	public static int estraiIntero(int min, int max){
		int range = max + 1 - min;
		int casuale = estrattore.nextInt(range);
		return casuale + min;
	}

	public static double estraiDouble(double min, double max){
		double range = max - min;
		double casuale = estrattore.nextDouble();

		double posEstratto = range*casuale;

		return posEstratto + min;
	}
	
	public static boolean tiraMoneta() {
		return estraiIntero(0,1) == 0;
	}
}