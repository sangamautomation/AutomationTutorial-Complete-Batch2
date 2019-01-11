package utils;

import java.io.IOException;

/**
 * Description : Batch Utility Functions
 * 
 * @author Sangam
 */
public class BatchUtils {
	// Command Prompt

		public static void runCmd(String command) {
			Runtime runTime = Runtime.getRuntime();

			try {
				runTime.exec(new String[] { "cmd.exe", "/c", "start", command });
				Runtime.getRuntime().exec(command);

			} catch (IOException e) {

				e.printStackTrace();
			}
//			((ITopWindow) objcit.commandPromptwindow).inputKeys(command + "{ENTER}"+ "exit" + "{ENTER}");

		}

	public static void runBatch(String batchFilePath) {
			try {
				String[] command = { "cmd.exe", "/C", "Start", batchFilePath };
				System.out.println(batchFilePath);
				System.out.println(Runtime.getRuntime().exec(command).getOutputStream().toString());

				Process p = Runtime.getRuntime().exec(command);
				System.out.println(Runtime.getRuntime().exec(command).getOutputStream().toString());
				System.out.println(Runtime.getRuntime().exec(command).toString());
			} catch (IOException ex) {
			}
		}
}
