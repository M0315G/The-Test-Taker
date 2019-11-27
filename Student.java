import java.io.*;
import java.util.*;

//This is the main class file where all the functionalities of Student are handled.
class Student{

    String student_name;
    String student_rollno;
    int semester;
    private int count=0;
    String student_gender;
    Scanner input= new Scanner(System.in);
    float OOP[]={0,0,0,0};
    float DE[]={0,0,0};
    float DSA[]={0,0,0};
    float total_marks,average;
    Student(String name,String rollno,int semester,String gender){
        student_name=name;
        student_rollno=rollno;
        this.semester=semester;
        student_gender=gender;
    }

//Index-1
    //After the student gets verified by the authenticator or when a new student registers, this function will be called immediately.
    final void welcome(){
        int c=-1;
        System.out.println("####################################################################################");
        System.out.println("                         Welcome  " + student_name + "!");
        System.out.println("                         Rollno:-"+student_rollno);
        System.out.println("                         Semester:-"+semester);
        System.out.println("####################################################################################");
        while(c!=0){
            System.out.println("\n");
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("What do you want to do?");
            System.out.println("1. Opt for a test.");
            System.out.println("2. See Progress report.");
            System.out.println("0. Logout.");
            System.out.println("-------------------------------------------------------------------------------");
            while(true){
                try{
                    c = input.nextInt();
                    break;
                }catch(InputMismatchException e){
                    System.out.println("Invalid Input. Please Enter your choice again.");
                    input.nextLine();
                }
            }
            switch(c){
                case 1:
                    //Functions to take test of the student.
                    actual_test_taker(student_name, student_rollno);
                    break;
                case 2:
                    //Function to generate the progress report of the student.
                    generate_report(student_rollno);
                    break;
                case 0:
                    System.out.println("User Successfully LoggedOut");
                    return;
                    //Will call the main function which handles the main menu of login/signup.
                    //break;
                default:
                    System.out.println("You entered a wrong choice of Input.\nTry Again.");
            }
            
        }
    }

//Index-2
    //This function will handle the functionality of taking the quiz of the Student according to his/her choice.
    void actual_test_taker(String name,String rollno){
        File file_of_the_current_student= new File("C://Manish//The_Test_Taker//All_Text_files//Student_Record//"+rollno+".txt");
        String subject;
        int chapter;

        //This function will read all the values of the instance variable for the quiz.
        get_values(file_of_the_current_student);

        //This function will display all the subjects offered to the student based on the semester in which he/she is.
        //and ask the student for the choice of the subject of which he/she wants to take the quiz.
        subject=choose_among_the_subjects_offered();

        //This function will display all the chapters offered in a particular subject and ask the student about his/her choice of chapter/s.
        
        chapter=choose_among_the_chapters_of_the_subject(subject,"SEM_3");
        if(chapter == -1){
            return;
        }
    
        
        //Now we will open the file of the chapter of the specific subject.
        File quiz_file = new File("C://Manish//The_Test_Taker//All_Text_files//Quizes//"+"SEM_3"+"//"+subject+"//"+subject+"_CHAPTER0"+chapter+".txt");

        //This function will display each question one by one as per the chapter opted for.
        //Also it will store the marks of the student and will keep on calculating the final total marks of that quiz.
        int quiz_marks= taking_test(quiz_file); 
        if(quiz_marks==-1){
            return;
        }
        
        //Assigning the corresponding quiz variable it's value after the test.
        switch(subject){
            case "OOP":
                OOP[chapter-1]=quiz_marks;
                break;
            case "DSA":
                DSA[chapter-1]=quiz_marks;
                break;
            case "DE":
                DE[chapter-1]=quiz_marks;
                break;
        }
        total_marks+=quiz_marks;
        average=total_marks/10;

        set_values(file_of_the_current_student);
        System.out.println("###############################################################################################");
        System.out.println("                                You completed the test successfully.");
        System.out.println("                                You got a total score of "+quiz_marks);
        System.out.println("###############################################################################################");
        //System.out.println(quiz_marks);

        

    }

//Index-3
    //Function to help the student choose the subject of which he/she wants to take the test.
    private String choose_among_the_subjects_offered(){
        String subject;
        
        System.out.println("************************************************");
        System.out.println("The Subjects offered are:-");
        System.out.println("OOP\nDE\nDSA");
        System.out.println("************************************************");
        
        //Asking user for his/her choice of Subject.
        System.out.println("Enter the one of the Subject from above whose Quiz you want to take.");
        subject=input.nextLine().toUpperCase();
        while(true){
            try{
                
                subject=input.nextLine().toUpperCase();
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid Input. Please Enter your choice again.");
                input.nextLine();
            }
        }

        switch(subject){
            case "OOP":
            case "DE":
            case "DSA":
                break;
            default:
                System.out.println("You entered a wrong choice of Subject.");
                subject=choose_among_the_subjects_offered();

        }

        return subject;
    }

//Index-4    
    //Function to help the student choose the subject of which he/she wants to take the test.
    private int choose_among_the_chapters_of_the_subject(String subject,String semester){

        int chapter=-1;
        File subject_index= new File("C://Manish//The_Test_Taker//All_Text_files//Quizes//"+semester+"//"+subject+"//Subject_index.txt");
        Scanner chapter_read= null;
        try{
            chapter_read= new Scanner(subject_index);
        }catch(FileNotFoundException e){
            System.out.println("Some error occured while loading the questions.");
            //chapter=choose_among_the_chapters_of_the_subject(subject,semester);
            return chapter;
        }
        float a[]= new float[4];
        if(subject.toUpperCase().equals("OOP")){
            a= OOP;
        }else if(subject.toUpperCase().equals("DE")){
            a=DE;
        }else if(subject.toUpperCase().equals("DSA")){
            a=DSA;
        }

        //This will display the list of the chapters available in a subject by traversing the subject index of each subject
        String line_will_be_of_use_sometime=chapter_read.nextLine();
        while(!(line_will_be_of_use_sometime.equals("List of chapters:-")))
        {
            line_will_be_of_use_sometime=chapter_read.nextLine();
        }
        int i=0;
        System.out.println("*************************************************************");
        System.out.println(line_will_be_of_use_sometime);
        System.out.println("*************************************************************");
        System.out.println("Chapter Name                            Status");
        line_will_be_of_use_sometime=chapter_read.nextLine();
        int no_of_chapters=0;
        
        while(line_will_be_of_use_sometime.charAt(0)!='#'&&i<4){
            System.out.println(line_will_be_of_use_sometime +"               "+(a[i]!=0?"Attempted":"Yet to be Attempted"));
            line_will_be_of_use_sometime=chapter_read.nextLine();
            no_of_chapters++;//To get the count of the chapters available
            i++;
        }
        System.out.println("************************************************");


        //Asking user for his/her choice of Chapter.
        int chap;
        System.out.println("Enter the choice of the Chapter from above whose Quiz you want to take.");
        //String chap1= input.next();
        //chap=Integer.parseInt(chap1);
        while(true){
            try{
                chap=input.nextInt();
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid Input. Please Enter your choice again.");
                input.nextLine();
            }
        }
        if(chap>no_of_chapters){//If the user enters some wrong chapter_no then we'll call this function again and ask his/her to choose.
            System.out.println("You entered a wrong choice of Chapter.");
            chapter=choose_among_the_chapters_of_the_subject(subject, semester);
        }else{
            if(a[chap-1]==0){
                chapter=chap;
            }else{
                System.out.println("You cannot attemp a quiz twice. Choose another chapter.");
                chapter=choose_among_the_chapters_of_the_subject(subject, semester);
            }
        }
        chapter_read.close();
        return chapter;
    }

//Index-5
    //This fucntion will display each question one by one and ask the user for his/her answer and simultaneously calulate the total marks for the intende quiz.
    private int taking_test(File quiz_file){
        int total_marks=-1;
        Scanner read_question=null;
        
        try{
            count++;
            read_question= new Scanner(quiz_file);
        }catch(FileNotFoundException e){
            System.out.println("There is no database of questions for the choosen chapter");
            if(count<3){
                total_marks=taking_test(quiz_file);
                return total_marks;
            }else{
                System.out.println("The system is experiencing some problem loading the questions. Please try again later!");
                return total_marks;
            }
            
        }
        String question= read_question.nextLine();
        while(question.charAt(0)!='*'){
            question=read_question.nextLine();
        }
        String answer;
        System.out.println("########################################################################");
        System.out.println("                        Starting the Test.");
        System.out.println("########################################################################");
        question=read_question.nextLine();
        while(question!=null){
            System.out.println("Question:-");
            System.out.println(question);
            System.out.println("Options:-");
            question= read_question.nextLine();
            while(!(question.contains("Correct Answer"))){
                System.out.println(question);
                question= read_question.nextLine();
                
            }
            System.out.println("Enter your answer");
            while(true){
                try{
                    answer=input.next().toUpperCase();
                    if(answer.equals("B")||answer.equals("A")||answer.equals("C")||answer.equals("D")){
                        break;
                    }else{
                        System.out.println("Enter a valid choice from (A/B/C/D)");
                    }
                    
                }catch(InputMismatchException e){
                    System.out.println("Invalid Input. Please Enter your choice again.");
                    input.nextLine();
                }
            }
            if(answer.equals(question.substring(15))){
                total_marks+=2;
            }
            question=read_question.nextLine();
            System.out.println(question);
            if(read_question.hasNextLine()){
                question=read_question.nextLine();
            }else{
                break;
            }
            

        }

        read_question.close();
        return total_marks;
    }

    //This function will get the values from the file.
    private void get_values(File f1){
        String line;
        Scanner read_file=null;
        try{
            read_file= new Scanner(f1);
        }catch(FileNotFoundException e){
            System.out.println("The specified file of the Student cannot be found.");
            return;
        }
        line=read_file.nextLine();
        while(!(line.contains("OOP"))){
            line=read_file.nextLine();
        }
        line=read_file.nextLine();
        while(!(line.contains("Chap_1"))){
            line=read_file.nextLine();
        }
        //System.out.println(line.substring(8));
        OOP[0]=Float.parseFloat(line.substring(8));
        line=read_file.nextLine();
        OOP[1]=Float.parseFloat(line.substring(8));
        line=read_file.nextLine();
        OOP[2]=Float.parseFloat(line.substring(8));
        line=read_file.nextLine();
        OOP[3]=Float.parseFloat(line.substring(8));

        line=read_file.nextLine();
        while(!(line.contains("DE"))){
            line=read_file.nextLine();
        }
        line=read_file.nextLine();
        while(!(line.contains("Chap_1"))){
            line=read_file.nextLine();
        }
        DE[0]=Float.parseFloat(line.substring(8));
        line=read_file.nextLine();
        DE[1]=Float.parseFloat(line.substring(8));
        line=read_file.nextLine();
        DE[2]=Float.parseFloat(line.substring(8));

        line=read_file.nextLine();
        while(!(line.contains("DSA"))){
            line=read_file.nextLine();
        }
        line=read_file.nextLine();
        while(!(line.contains("Chap_1"))){
            line=read_file.nextLine();
        }
        DSA[0]=Float.parseFloat(line.substring(8));
        line=read_file.nextLine();
        DSA[1]=Float.parseFloat(line.substring(8));
        line=read_file.nextLine();
        DSA[2]=Float.parseFloat(line.substring(8));

        line=read_file.nextLine();
        line=read_file.nextLine();
        total_marks=Float.parseFloat(line.substring(13));
        average=total_marks/10;

        read_file.close();
    }

    //TO set the new Values into the file of the student.
    private void set_values(File f1){
        FileWriter writer= null;
        try{
            writer=new FileWriter(f1);
            writer.write("###################################################################################\r\n"+
                          "This is the master file of the individual Student-"+student_name+"\r\n"+
                          "##################################################################################\r\n"+
                          "Name- "+student_name+"\r\n"+
                          "Roll no- "+student_rollno+"\r\n"+
                          "Gender- "+student_gender+"\r\n"+
                          "Semester- 3\r\n"+
                          "Subjects offered:-\r\n"+
                          "(1)OOP\r\n"+
                          "(2)DE\r\n"+
                          "(3)DSA\r\n"+
                          "*****************************************************************************\r\n"+
                          "Quiz data\r\n"+
                          "*****************************************************************************\r\n"+
                          "Subject- OOP\r\n"+
                          "List of Quizes taken with their marks\r\n"+
                          "-----------------------------------------------------------------------------\r\n"+
                          "Chap_1- "+OOP[0]+"\r\n"+
                          "Chap_2- "+OOP[1]+"\r\n"+
                          "Chap_3- "+OOP[2]+"\r\n"+
                          "Chap_4- "+OOP[3]+"\r\n"+
                          "-----------------------------------------------------------------------------\r\n"+
                          "Subject- DE\r\n"+
                          "List of Quizes taken with their marks\r\n"+
                          "-----------------------------------------------------------------------------\r\n"+
                          "Chap_1- "+DE[0]+"\r\n"+
                          "Chap_2- "+DE[1]+"\r\n"+
                          "Chap_3- "+DE[2]+"\r\n"+
                          "-----------------------------------------------------------------------------\r\n"+
                          "Subject- DSA\r\n"+
                          "List of Quizes taken with their marks\r\n"+
                          "-----------------------------------------------------------------------------\r\n"+
                          "Chap_1- "+DSA[0]+"\r\n"+ 
                          "Chap_2- "+DSA[1]+"\r\n"+
                          "Chap_3- "+DSA[2]+"\r\n"+
                          "*****************************************************************************\r\n"+
                          "Total marks- "+total_marks+"\r\n"+
                          "Average- "+average+"\r\n"+
                          "*****************************************************************************\r\n");
            writer.close();
        }catch(IOException e){
            System.out.println("An error occured");
        }
    }


    private boolean generate_report(String rollno){
        File student = new File("C://Manish//The_Test_Taker//All_Text_files//Student_Record//"+rollno+".txt");
        Scanner read=null;
        try{
            read= new Scanner(student);
        }catch(FileNotFoundException e){
            System.out.println("Couldnot load the Database of the said Student.Please try again");
            return true;
        }
        int i=0;
        while(i<3){
            read.nextLine();
            i++;
        }
        String student_name= read.nextLine();
        System.out.println("\n\n");
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



class test_student{
    public static void main(String args[]){
        Student s= new Student("Abhinav","18BCE001", 3,"Male");
        s.welcome();
    }
}