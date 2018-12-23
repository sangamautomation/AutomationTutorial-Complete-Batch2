package tests.javaTutorial.library;
/**
 * Enum Demo
 * 
 * @author Sangam 
 */
public class EnumDemo {

	public enum UserStatus {
		PENDING,
		ACTIVE,
		INACTIVE,
		DELETED;
	}

	public enum States{
		AR,
		NY,
		NJ,
		MO,
		TX;

		public String fullForm(String shortForm){
			String output = null;
			switch (this) {
			case AR:
				output =  "Arkansas";
				break;
			case NY:
				output =  "New York";
				break;
			case NJ:
				output =  "New Jersey";
				break;
			case MO:
				output =  "Missouri";
				break;
			case TX:
				output =  "Texas";
				break;
			default:
				throw new AssertionError();
			}
			return output;
		}

	}

	public enum Operation {
		PLUS,
		MINUS,
		TIMES,
		DIVIDE;

		public double calculate(double x, double y) {
			switch (this) {
			case PLUS:
				return x + y;
			case MINUS:
				return x - y;
			case TIMES:
				return x * y;
			case DIVIDE:
				return x / y;
			default:
				throw new AssertionError("Unknown operations " + this);
			}
		}

	}


}
