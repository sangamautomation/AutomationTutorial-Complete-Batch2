package tests.javaTutorial.library;
/**
 * Switch-Case Demo
 * 
 * @author Sangam 
 */
public class SwitchCaseHandler
{
    
    // Method to print the name of the day by accepting number of the day
    public static void dayOfTheWeek(int day){

 
            
                      
           if(day==1)
                   System.out.println("Sunday");
            else if(day==2)
                  System.out.println("Monday");
            else if(day==3)
                  System.out.println("Tuesday");
            else if(day==4)
                  System.out.println("Wednesday");
            else if(day==5)
                  System.out.println("thursday");
            else if(day==6)
                  System.out.println("Friday");
            else if (day==7)
                  System.out.println("Saturday");
            else
                  System.out.println("Please enter numbers 1 through 7");
                  
 
        }
            
 
    
        // Method to return the name of the day by accepting number of the day
    public static String dayOfTheWeek(String day){

           String dayName =null;
            
             switch (day)
             {
           case "1":
                   dayName="Sunday";
                  System.out.println("Sunday");
                   break;
           case "2":
                   dayName="Monday";
                   break;
           case "3":
                   dayName="Tuesday";
                   break;
           case "4":
                   dayName="Wednesday";
                   break;
           case "5":
                   dayName="Thursday";
                   break;
           case "6":
                   dayName="Friday";
                   break;
           case "7":
                   dayName="Saturday";
                   break;
           default:
                   dayName="No Day";
                   break;

        }
        
                    return dayName;
            
    }   
    
}