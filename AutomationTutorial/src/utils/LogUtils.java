package utils;

import javax.swing.JOptionPane;

/**
 * To print logs on to the console
 * @author Sangam
 *
 */
public class LogUtils {
	
	// Log to Console
	public static void log(String aMessage){
		System.out.println(DateUtils.timestamp()+" | "+ aMessage);
	}

	public static void log2(String message){
		System.out.println(DateUtils.getCurrentTimestamp("MM/dd/yyyy HH:mm:ss")+ " | "  + message);
	}

	public static void logSpecial(String aMessage){
		System.out.println(DateUtils.timestamp()+" | "+ " O ==================== "+aMessage+ " ====================");
	}
	public static void log(int aMessage){
		System.out.println(DateUtils.timestamp()+" | "+ aMessage);
	}
	public static void logStart(String message){
		System.out.println("\n################################################## "+message.toUpperCase().replaceAll("", " ")+" ##################################################\n");
		//System.out.println(" \n __________________________________________"+" - START - "+message+"_______________________________________________\n");
		//	System.out.println(DateUtils.timestamp()+" | "+message+" :: "); 
	}

	public static void logDone(String message){
		System.out.println(DateUtils.timestamp()+" | "+message+" :: ");
		System.out.println(" \n __________________________________________"+" - END - "+message.replaceAll("", " ").trim()+"_________________________________________________\n");
	}	  


	public static void sleep(int millis) throws InterruptedException {
		//log("Waiting for "+millis/(60*60)+ " mins...");
		log("Waiting for "+millis/(1000*60)+ " mins");
		log("Waiting for "+millis/1000+ " sec");

		for (int i = 0; i < millis/1000; i++) {
			Thread.sleep(1000);
			System.out.print(".");
		}
		System.out.println();

		/*	try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/ 
	}

	public static void sleep(int sec, String info) throws InterruptedException {
		//log("Waiting for "+millis/(60*60)+ " mins...");
		log("Waiting for "+info + " "+sec/60+ " mins");
		log("Waiting for "+sec+ " sec");

		for (int i = 0; i < sec; i++) {
			Thread.sleep(1000);
			System.out.print(".");
		}
		System.out.println();
	}

	// Debug by pausing and finding out xpath in browser
	public static void debug() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^debug^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		JOptionPane.showMessageDialog(null, "Debug it!!"); 
 	}

	public static void debugEnd() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^debug-End^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		JOptionPane.showMessageDialog(null, "Debug it - Will End!!"); 
		System.exit(0);//To end Java process after OK in above pane
	}


	/**
	 * ExceptionToString: Stacktrace to String conversion to print it in file
	 * @param e
	 * @return
	 */
	public static String excp2str(Throwable e) {
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement element : e.getStackTrace()) {
			/*sb.append(e.toString());//exception content optional
			sb.append("\n");
*/
 			sb.append(element.toString());//exception thread with line no
			sb.append("\n");
		}
		return sb.toString();
	}


}
