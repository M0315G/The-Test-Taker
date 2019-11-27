
/*This class is the main class which will handle all the functionalities of the Teacher.*/
import java.io.*;
import java.util.*;

class Teacher {

    String name, subject="OOP";
    Scanner src = new Scanner(System.in);

    // File Teacher_file_pointer= new File();
    Teacher(String name) { // Constructor to get the credentials of current logged in Teacher.
        this.name = name;
    }

    // Welcome function for the LoggedIN Teacher which will display his/her name,
    // subject he/she teaches and offers a choice of Actions.
    final void welcome() {
        int choice;
        boolean c = true;
        System.out.println("####################################################################################");
        System.out.println("                              Welcome  " + name + "!");
        System.out.println("####################################################################################");
        

        //Main menu for the Teacher which logs in the system.
        while (c) {
            System.out.println("\n**************************************************************************************");
            System.out.println("Enter your choice:-");
            System.out.println("1 to Add Quiz question/s for your Subject.\n2 to see Progress of the Class.\n-1 to Logout.");
            System.out.println("**************************************************************************************");
            while(true){
                try{
                    choice = src.nextInt();
                    break;
                }catch(InputMismatchException e){
                    System.out.println("Invalid Input. Please Enter your choice again.");
                    src.nextLine();
                }
            }
            
            switch (choice) {
            case 1:
                    // Functions to handle the Question adding functionality
                    System.out.println("\n------------------------------------------------------------------------");
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Enter the name of Subject for which you want to add the Quiz.");
                    System.out.println("OOP\nDE\nDSA");
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("------------------------------------------------------------------------");
                    
                    while(true){
                        try{
                            subject = src.nextLine();
                            subject = src.nextLine();
                            break;
                        }catch(InputMismatchException e){
                            System.out.println("Invalid Input. Please Enter your choice again.");
                            src.nextLine();
                        }
                    }
                    //Will add a loop to display all the choice of Subjects that he/she teaches
                    //{}
                    subject = subject.toUpperCase();
                    boolean flag=true;
                    while(flag){
                    int choice2 = choose_among_the_chapters_of_the_subject(subject);
                    switch (choice2) {
                    case 1:
                        q_and_a((subject + "_CHAPTER01.txt"));
                        flag=false;
                        break;
                    case 2:
                        q_and_a((subject + "_CHAPTER02.txt"));
                        flag=false;
                        break;
                    case 3:
                        q_and_a((subject + "_CHAPTER03.txt"));
                        flag=false;
                        break;
                    default:
                        System.out.println("You entered a wrong choice for Chapter.");
                    }}
                    break;
            case 2:
                        int c1=-1;
                        boolean flag2=true;
                        while(flag2){
                            System.out.println("\n------------------------------------------------------------------------");
                            System.out.println("------------------------------------------------------------------------");
                            System.out.println("You chose to fetch the Progress Report.\nEnter your choice.\n1.Progress report of whole class combined.\n2.Progress report of an individual student");
                            System.out.println("------------------------------------------------------------------------");
                            System.out.println("------------------------------------------------------------------------");
                            while(true){
                                try{
                                    c1 = src.nextInt();
                                    break;
                                }catch(InputMismatchException e){
                                    System.out.println("Invalid Input. Please Enter your choice again.");
                                    src.nextLine();
                                }
                            }
                            if(flag2){
                                switch(c1){
                                    case 1:
                                        File class_ref = new File("C://Manish//The_Test_Taker//All_Text_files//Student_Record//Class_data.txt");
                                        //Function to display the progress of the whole class.
                                        flag2=getReport(class_ref);
                                        break;
                                    case 2:
                                        String roll_no;
                                        System.out.println("Enter the roll no of the student whose Progress Report yu want to generate:-");
                                        while(true){
                                            try{
                                                roll_no = src.nextLine();
                                                roll_no = src.nextLine();
                                                break;
                                            }catch(InputMismatchException e){
                                                System.out.println("Invalid Input. Please Enter your choice again.");
                                                src.nextLine();
                                            }
                                        }
                                        //Function to display the progress of an individual student.
                                        flag2=getReport(roll_no);
                                        break;
                                    default:
                                        System.out.println("Enter a valid Argument.");
                                }
                            }
                        }
                        break;
            case -1:
                //c = false;
                System.out.println("Logged out Successfully.");
                return;
                //break;
            default:
                System.out.println("Please Enter a valid choice.");
            }
        }
        return;
    }

    private int choose_among_the_chapters_of_the_subject(String subject){

        int chapter=-1;
        File subject_index= new File("C://Manish//The_Test_Taker//All_Text_files//Quizes//SEM_3//"+subject+"//Subject_index.txt");
        Scanner chapter_read= null;
        try{
            chapter_read= new Scanner(subject_index);
        }catch(FileNotFoundException e){
            System.out.println("Try Again");
            //chapter=choose_among_the_chapters_of_the_subject(subject,semester);
            return chapter;
        }

        //This will display the list of the chapters available in a subject by traversing the subject index of each subject
        String line_will_be_of_use_sometime=chapter_read.nextLine();
        while(!(line_will_be_of_use_sometime.equals("List of chapters:-")))
        {
            line_will_be_of_use_sometime=chapter_read.nextLine();
        }
        System.out.println("************************************************");
        System.out.println(line_will_be_of_use_sometime);
        line_will_be_of_use_sometime=chapter_read.nextLine();
        int no_of_chapters=0;
        while(line_will_be_of_use_sometime.charAt(0)!='#'){
            System.out.println(line_will_be_of_use_sometime);
            line_will_be_of_use_sometime=chapter_read.nextLine();
            no_of_chapters++;//To get the count of the chapters available
        }
        System.out.println("************************************************");


        //Asking user for his/her choice of Chapter.
        int chap;
        System.out.println("Enter the number of the Chapter from above whose Quiz you want to take.");
        //String chap1= input.next();
        //chap=Integer.parseInt(chap1);
        while(true){
            try{
                chap = src.nextInt();
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid Input. Please Enter your choice again.");
                src.nextLine();
            }
        }
        
        if(chap>no_of_chapters){//If the user enters some wrong chapter_no then we'll call this function again and ask his/her to choose.
            System.out.println("You entered a wrong choice of Chapter.");
            chapter=choose_among_the_chapters_of_the_subject(subject);
        }else{
            chapter=chap;
        }
        chapter_read.close();
        return chapter;
    }
    
    //This function asks the Teacher to entera aquestion one by one followed by it's answer.
    final void q_and_a(String file_name) {
        int c = 1;
        // File object for the specific chapter.
        File q_and_a = new File("C://Manish//The_Test_Taker//All_Text_files//Quizes//SEM_3//"+subject+"//" + file_name);
        
        if (q_and_a.exists()) {
            System.out.println("\nFile opened successfully.");
        } else {
            System.out.println("not found");
            return;
        }
        Boolean check = false;
        String question;
        String[] answer = new String[5];
        Scanner append_data=null;
        int i=0;
        while (c == 1) {
            try {
                append_data = new Scanner(q_and_a);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            System.out.println("\n--------------------------------------------------------------------------------------");
            System.out.println("Enter your Question:-");
            System.out.println("--------------------------------------------------------------------------------------");
            question = src.nextLine();
            question = src.nextLine();
            //First we check if the question already exist in the questionarre.
            while (append_data.hasNextLine()) {
                if (question.equals(append_data.nextLine())) {
                    System.out.println("\n\nThis question already exists in the Questionarre.");
                    check = true;//Flag to store that the input question already exist in the file. 
                }
           }
            
        //Appending the data into the file only if the question doesnot exist in the file previously.
            if (check == false) {
                while(i<4){
                    System.out.println("Enter Option("+(i+1)+")");
                    answer[i] = src.nextLine();
                    i++;
                }
                System.out.println("Enter the correct option:-(A/B/C/D)");
                while(true){
                    
                    answer[4]=src.nextLine().toUpperCase();
                    if(answer[4].equals("A")||answer[4].equals("B")||answer[4].equals("C")||answer[4].equals("D")){
                        break;
                    }else{
                        System.out.println("Enter a valid Option from A/B/C/D");
                    }
                }
                

                
                append_data.close();i=0;
                FileWriter writer;
                try {
                    writer = new FileWriter(q_and_a, true);
                    writer.write("\r\n");
                    writer.write(question);
                    writer.write("\r\n(A)"+answer[0]+
                                 "\r\n(B)"+answer[1]+
                                 "\r\n(c)"+answer[2]+
                                 "\r\n(D)"+answer[3]);
                    writer.write("\r\n");
                    writer.write("Correct option:-"+answer[4]);
                    writer.write("\r\n");
                    writer.write("*****************************************************************");
                    writer.close();

                    System.out.println("\nQuestion/s added successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Couldnot complete the operation.\nPlease try again.");

                }}
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println("Do you want to add another Question?");
                System.out.println("1. YES\n0. NO");
                System.out.println("--------------------------------------------------------------------------------------");
                try{
                    c=src.nextInt();
                }catch(Exception e){
                    System.out.println("Invalid type of Argument.");
                    q_and_a(file_name);
                }
        }
        
    }


    //This funation will print the progress report for the whole class.
    //It will traverse the master file having required data of all the students and display them in a proper manner.
    boolean getReport(File f1){
        Scanner reader = null;
        try{
            reader=new Scanner(f1);
        }catch(FileNotFoundException e){
            System.out.println("Something's not right. Try again.");
            return true;
        }
        float class_total=0;
        int counter=0;
        System.out.println("###################################################################################################");
        System.out.println("                      The cummulative progress of the class is display below                 ");
        System.out.println("###################################################################################################");
        System.out.println("   Name                         Roll No          Total Quizes        Total Marks      Percentage   ");
        int i=0;
            while(i<5){
                reader.nextLine();
                i++;
            }
        while(reader.hasNextLine()){
            
            String STUDENT_name=reader.nextLine();
            String rollo = reader.nextLine();
            String useless= reader.nextLine();
            File student_file = new File("C://Manish//The_Test_Taker//All_Text_Files//Student_record//"+rollo+".txt");
            Scanner input= null;
            try{
                input=new Scanner(student_file);
            }catch(IOException e){
                System.out.println("Error");
                continue;
            }
            String total_marks=null,avg=null;
            
            String used= input.nextLine();
            while(!(used.contains("Total marks"))){
                if(input.hasNextLine()){
                    used=input.nextLine();
                }else{
                    System.out.println("error");
                }
            }
            total_marks= used.substring(13);
            used=input.nextLine();
            avg=used.substring(9);
            
            input.close();
            
            class_total+=Float.parseFloat(total_marks);
            counter++;
            System.out.println(STUDENT_name+"\t\t"+rollo+"\t\t10"+"\t\t"+total_marks+"\t\t"+avg);
            
        }
        
        //Calculate the standard deviation of the data of the class
        //Calculate the coefficient of variation
        System.out.println("                                      Class Total:-"+class_total);
        System.out.println("##################################################################################");
        reader.close();
        return false;
    }

    //This function will generate the report card for an Individual student.
    boolean getReport(String rollno){
        File student = new File("C://Manish//The_Test_Taker//All_Text_files//Student_Record//"+rollno+".txt");
        Scanner read=null;
        try{
            read= new Scanner(student);
        }catch(FileNotFoundException e){
            System.out.println("Something's not right. Please try again");
            return true;
        }
        int i=0;
        while(i<3){
            read.nextLine();
            i++;
        }
        String student_name= read.nextLine();
        
        System.out.println("#################################################################################");
        System.out.println("                 Progress Report of-"+student_name.substring(6));
        System.out.println("#################################################################################");
        while(read.hasNextLine()){
            String line=read.nextLine();
            System.out.println(line);
        }
        System.out.println("\n\n\n");
        read.close();
        return false;
    }

}
class Testing{
    public static void main(String args[]){
        Teacher t1= new Teacher("Manish");
        t1.welcome();
    }
}