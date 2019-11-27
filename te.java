public class te { 
      
    public static void main(String args[]) { 
         
       // Initializing String  
       String Str = new String("Hello"); 
         
       // Testing if regex is present 
       System.out.print("Hello " ); 
       System.out.println(Str.matches("(.*)ll(.*)")); 
         
       // Testing if regex is present 
       System.out.print("Does String contains regex geeks ? : " ); 
       System.out.println(Str.matches("geeks")); 
         
    } 
 }