package model;

public class MeasurementConverter {
	
	public static double addMeasurements(double amount1, String unit1, double amount2, String unit2){
		if(unit1.equals(unit2)){
			return amount1 + amount2;
		}
		else{
			double amt1, amt2;
			String u1, u2;
			if(unitWeight(unit1) > unitWeight(unit2)){
				amt1 = amount1;
				amt2 = amount2;
				u1 = unit1;
				u2 = unit2;
			}
			else if(unitWeight(unit1) < unitWeight(unit2)){
				amt2 = amount1;
				amt1 = amount2;
				u2 = unit1;
				u1 = unit2;
			}
			else{
				return 0;
			}
			if(u1.equals("Teaspoons")){
				return amt1 + ConvertMlToTsp(amt2);
			}
			if(u1.equals("Tablespoons")){
				if(u2.equals("mL"))
					return amt1 + ConvertMlToTbs(amt2);
				if(u2.equals("Teaspoons"))
					return amt1 + ConvertTspToTbs(amt2);
			}
			if(u1.equals("Fl. Oz.")){
				if(u2.equals("mL"))
					return amt1 + ConvertMlToFlOz(amt2);
				if(u2.equals("Teaspoons"))
					return amt1 + ConvertTspToFlOz(amt2);
				if(u2.equals("Tablespoons"))
					return amt1 + ConvertTbsToFlOz(amt2);
			}
			if(u1.equals("Cups")){
				if(u2.equals("mL"))
					return amt1 + ConvertMlToCups(amt2);
				if(u2.equals("Teaspoons"))
					return amt1 + ConvertTspToCups(amt2);
				if(u2.equals("Tablespoons"))
					return amt1 + ConvertTbsToCups(amt2);
				if(u2.equals("Fl. Oz."))
					return amt1 + ConvertFlOzToCups(amt2);
			}
			if(u1.equals("Pints")){
				if(u2.equals("mL"))
					return amt1 + ConvertMlToPints(amt2);
				if(u2.equals("Teaspoons"))
					return amt1 + ConvertTspToPints(amt2);
				if(u2.equals("Tablespoons"))
					return amt1 + ConvertTbsToPints(amt2);
				if(u2.equals("Fl. Oz."))
					return amt1 + ConvertFlOzToPints(amt2);
				if(u2.equals("Cups"))
					return amt1 + ConvertCupsToPints(amt2);
			}
			if(u1.equals("Quarts")){
				if(u2.equals("mL"))
					return amt1 + ConvertMlToQuarts(amt2);
				if(u2.equals("Teaspoons"))
					return amt1 + ConvertTspToQuarts(amt2);
				if(u2.equals("Tablespoons"))
					return amt1 + ConvertTbsToQuarts(amt2);
				if(u2.equals("Fl. Oz."))
					return amt1 + ConvertFlOzToQuarts(amt2);
				if(u2.equals("Cups"))
					return amt1 + ConvertCupsToQuarts(amt2);
				if(u2.equals("Pints"))
					return amt1 + ConvertPintsToQuarts(amt2);
			}
			if(u1.equals("Liters")){
				if(u2.equals("mL"))
					return amt1 + ConvertMlToLiters(amt2);
				if(u2.equals("Teaspoons"))
					return amt1 + ConvertTspToLiters(amt2);
				if(u2.equals("Tablespoons"))
					return amt1 + ConvertTbsToLiters(amt2);
				if(u2.equals("Fl. Oz."))
					return amt1 + ConvertFlOzToLiters(amt2);
				if(u2.equals("Cups"))
					return amt1 + ConvertCupsToLiters(amt2);
				if(u2.equals("Pints"))
					return amt1 + ConvertPintsToLiters(amt2);
				if(u2.equals("Quarts"))
					return amt1 + ConvertQuartsToLiters(amt2);
			}
			if(u1.equals("Gallons")){
				if(u2.equals("mL"))
					return amt1 + ConvertMlToGallons(amt2);
				if(u2.equals("Teaspoons"))
					return amt1 + ConvertTspToGallons(amt2);
				if(u2.equals("Tablespoons"))
					return amt1 + ConvertTbsToGallons(amt2);
				if(u2.equals("Fl. Oz."))
					return amt1 + ConvertFlOzToGallons(amt2);
				if(u2.equals("Cups"))
					return amt1 + ConvertCupsToGallons(amt2);
				if(u2.equals("Pints"))
					return amt1 + ConvertPintsToGallons(amt2);
				if(u2.equals("Quarts"))
					return amt1 + ConvertQuartsToGallons(amt2);
				if(u2.equals("Liters"))
					return amt1 + ConvertLitersToGallons(amt2);
			}
		}
		return 0;
	}
	
	public static int unitWeight(String measureType){
		if(measureType.equals("mL"))
			return 1;
		if(measureType.equals("Teaspoons"))
			return 2;
		if(measureType.equals("Tablespoons"))
			return 3;
		if(measureType.equals("Fl. Oz."))
			return 4;
		if(measureType.equals("Cups"))
			return 5;
		if(measureType.equals("Pints"))
			return 6;
		if(measureType.equals("Quarts"))
			return 7;
		if(measureType.equals("Liters"))
			return 8;
		if(measureType.equals("Gallons"))
			return 9;
		if(measureType.equals("Ounces"))
			return 1;
		if(measureType.equals("Grams"))
			return 2;
		if(measureType.equals("Kilograms"))
			return 3;
		if(measureType.equals("Pounds"))
			return 4;
		return 0;
	}

	private static double ConvertMlToTsp(double amt){
		return amt / 5;
	}
	private static double ConvertMlToTbs(double amt){
		return amt / 15.0;
	}
	private static double ConvertMlToFlOz(double amt){
		return amt / 30.0;
	}
	private static double ConvertMlToCups(double amt){
		return amt / 240.0;
	}
	private static double ConvertMlToPints(double amt){
		return amt / 480.0;
	}
	private static double ConvertMlToQuarts(double amt){
		return amt / 960.0;
	}
	private static double ConvertMlToLiters(double amt){
		return amt / 1020.0;
	}
	private static double ConvertMlToGallons(double amt){
		return amt / 3840.0;
	}
	
	private static double ConvertTspToTbs(double amt){
		return amt / 3.0;
	}
	private static double ConvertTspToFlOz(double amt){
		return amt / 6.0;
	}
	private static double ConvertTspToCups(double amt){
		return amt / 48.0;
	}
	private static double ConvertTspToPints(double amt){
		return amt / 96.0;
	}
	private static double ConvertTspToQuarts(double amt){
		return amt / 192.0;
	}
	private static double ConvertTspToLiters(double amt){
		return amt / 204.0;
	}
	private static double ConvertTspToGallons(double amt){
		return amt / 768.0;
	}
	
	private static double ConvertTbsToFlOz(double amt){
		return amt / 2.0;
	}
	private static double ConvertTbsToCups(double amt){
		return amt / 16.0;
	}
	private static double ConvertTbsToPints(double amt){
		return amt / 32.0;
	}
	private static double ConvertTbsToQuarts(double amt){
		return amt / 64.0;
	}
	private static double ConvertTbsToLiters(double amt){
		return amt / 68.0;
	}
	private static double ConvertTbsToGallons(double amt){
		return amt / 256.0;
	}
	
	private static double ConvertFlOzToCups(double amt){
		return amt / 8.0;
	}
	private static double ConvertFlOzToPints(double amt){
		return amt / 16.0;
	}
	private static double ConvertFlOzToQuarts(double amt){
		return amt / 32.0;
	}
	private static double ConvertFlOzToLiters(double amt){
		return amt / 34.0;
	}
	private static double ConvertFlOzToGallons(double amt){
		return amt / 128.0;
	}
	
	private static double ConvertCupsToPints(double amt){
		return amt / 2.0;
	}
	private static double ConvertCupsToQuarts(double amt){
		return amt / 4.0;
	}
	private static double ConvertCupsToLiters(double amt){
		return amt / 4.25;
	}
	private static double ConvertCupsToGallons(double amt){
		return amt / 16.0;
	}
	
	private static double ConvertPintsToQuarts(double amt){
		return amt / 2.0;
	}
	private static double ConvertPintsToLiters(double amt){
		return amt / 2.125;
	}
	private static double ConvertPintsToGallons(double amt){
		return amt / 8.0;
	}
	
	private static double ConvertQuartsToLiters(double amt){
		return amt / 1.0625;
	}
	private static double ConvertQuartsToGallons(double amt){
		return amt / 4.0;
	}
	
	private static double ConvertLitersToGallons(double amt){
		return amt / 3.8;
	}
}
