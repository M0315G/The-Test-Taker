import java.io.*;
import java.util.*;
import java.lang.*;

class WrongPassword extends Exception
{
    WrongPassword(String announce)
    {
        super(announce);
    }
}

public class TheTestTaker {

    private static final int PasswordSize = 8;

    private static Scanner Parent_Scanner = new Scanner(System.in);

    public static boolean isStringOnlyAlphabet(String str) 
    { 
        return ((!str.equals("")) 
                && (str != null) 
                && (str.matches("^[a-zA-Z]*$")));
    }

    public static void Student_Login() throws IOException {
        String Rollno;
        String studentPassword;
        boolean a=true;
        System.out.println("#################################### STUDENT LOGIN ###################################");
        System.out.println("Enter your Roll no. : ");
        System.out.println("The format of the Roll no is- $$BCE$$$.\nWhere the places denoted by $ can be any number");
        while(true){
            Rollno = Parent_Scanner.next();
            if(!(Rollno.matches("(.*)BCE(.*)"))){
                System.out.println("The format of the Roll is wrong!");
                continue;
            }else{
                String arr[]=Rollno.split("BCE");
                for(String i:arr){
                    if(i.matches("[0-9]+") && i.length()>2){
                        a=true;
                    }else{
                        a=false;
                    }
                }
                if(a==false){
                    System.out.println("The input roll no is not in the correct format. Try again!");
                    
                }else{
                    break;
                }
            }
        }
        
        
                    
        
        System.out.println();
        System.out.println("Enter your Password : ");
        studentPassword = Parent_Scanner.next();
        System.out.println();

        File StudentFolder = new File("..\\The_Test_Taker\\All_Text_Files\\Credentials\\StudentDetails");
        try {
            if (!(StudentFolder.exists()))
                throw new FileNotFoundException("Folder Does not Exist!");
        } catch (Exception e) {
            System.out.println();
            System.out.println(e.getMessage());
            Student_Signup();
        }
        if (StudentFolder.exists()) {
            File StudentFiles = new File("..\\The_Test_Taker\\All_Text_Files\\Credentials\\StudentDetails\\" + Rollno + ".txt");

            try {
                if (!(StudentFiles.exists()))
                    throw new FileNotFoundException("User Does Not Exist");
            } catch (Exception e) {
                System.out.println();
                System.out.println(e.getMessage());
                System.out.println("First Create an Account!");
                Student_Signup();
            }
            if (StudentFiles.exists()) {
                BufferedReader S_Login_BR = new BufferedReader(new FileReader("..\\The_Test_Taker\\All_Text_Files\\Credentials\\StudentDetails\\" + Rollno + ".txt"));
                String PassCheck;
                S_Login_BR.readLine();
                S_Login_BR.readLine();
                S_Login_BR.readLine();
                S_Login_BR.readLine(); // skip name
                S_Login_BR.readLine(); // skip rollno
                S_Login_BR.readLine(); // skip gender
                S_Login_BR.readLine(); //skip semester
                PassCheck = S_Login_BR.readLine().substring(10); // go to password
                try {
                    if (!PassCheck.equals(studentPassword)){
                        S_Login_BR.close();
                        throw new WrongPassword("Wrong Password!");
                    }
                        
                } catch (WrongPassword e) {
                    System.out.println();
                    System.out.println(e.getMessage());
                    System.out.println("Password doesn't match.");
                    Student_Login();
                }
                if (PassCheck.equals(studentPassword))
                    System.out.println("Logged In Successfully.");
                S_Login_BR.close();
            }
            Scanner file_reader=null;
            File f1;
            String name=null,gender=null;
            while(true){
                f1= new File("C://Manish/The_Test_Taker//All_Text_Files//Student_Record//"+Rollno+".txt");
                try{
                    file_reader= new Scanner(f1);
                    break;
                }catch(IOException e){
                    continue;    
                }
            }
            int i=0;
            while(i<3){
                file_reader.nextLine();
                i++;
            }

            name=file_reader.nextLine().substring(6);
            file_reader.nextLine();
            gender= file_reader.nextLine().substring(8);

            Student s = new Student(name,Rollno,3,gender);
            s.welcome();
            
        }
        
    }

    public static void Student_Signup() throws IOException {
        File Student_Directory = new File("..\\The_Test_Taker\\All_Text_Files\\Credentials\\StudentDetails");
        if (Student_Directory.mkdir()) {
            System.out.println("A new Parent folder (StudentDetails) has been created.");
        } else {
            //System.out.println("StudentDetails Folder exists.");
        }

        String studentGender;
        String studentName;
        String f_name,l_name;
        String Rollno;
        String studentPassword;
        String ConfirmStudentPassword=null;
        System.out.println("############################ STUDENT SIGNUP ###################################");
        System.out.println("Please Enter Your First Name : ");
        f_name = Parent_Scanner.nextLine();
        
        while(true){
            f_name = Parent_Scanner.nextLine();
            if(isStringOnlyAlphabet(f_name)){
                break;
            }else{
                System.out.println("Enter your First name Again!");
            }

        }

        System.out.println("Please Enter Your Last Name : ");
        //l_name = Parent_Scanner.nextLine();
        
        while(true){
            l_name = Parent_Scanner.nextLine();
            if(isStringOnlyAlphabet(l_name)){
                break;
            }else{
                System.out.println("Enter your Last name Again!");
            }

        }

        studentName=f_name+" "+l_name;
        
        

        System.out.println("Enter Your Gender : ");
        System.out.println("Enter 'M' for Male \nEnter 'F' for Female.\n");
        while(true){
            try{
                studentGender = Parent_Scanner.nextLine().toUpperCase();
                if(!(studentGender.equals("M")||studentGender.equals("F")))
                {
                    System.out.println("Enter Valid Gender!");
                }
                else{
                    break;
                }
            
            }catch(InputMismatchException e){
                System.out.println("Invalid Input. Please Enter your gender again.");
                Parent_Scanner.nextLine();
            }
        }
        

        System.out.println("Enter Your Roll no. : ");
        System.out.println("The format of the Roll no is- $$BCE$$$.\n(Where the places denoted by $ can be any number)");
        boolean a=true;
        while(true){
            Rollno = Parent_Scanner.nextLine();
            if(Rollno.matches("(.*)BCE(.*)")){
                String arr[]=Rollno.split("BCE");
                //for(String i:arr){System.out.println(i);}
                for(String i:arr){
                    if(i.matches("[0-9]+") && i.length()>2){
                        a=true;
                    }else{
                        a=false;
                    }
                }
                if(a==false){
                    System.out.println("The input roll no is not in the correct format. Try again!");
                    continue;
                }
                break;    
            }else{
                System.out.println("The format of the Roll is wrong!");
            }
        }
        
        

        do {
            System.out.println("Please Create your Password : \n");
            System.out.println("1. A password must have at least 8 characters.\n"+
                               "2. It must consist of only letters and digits.\n" +
                               "3. It must contain at least 2 digits \n"+
                               "4. It must not contain any spaces.");
            //Parent_Scanner.nextLine();
            studentPassword = Parent_Scanner.nextLine();
            if (PasswordChecker(studentPassword)) {
                System.out.println("Password is valid!");
            } else {
                System.out.println("Password is not valid!");
                continue;
            }

            System.out.println("Confirm Your Password : ");
            ConfirmStudentPassword = Parent_Scanner.nextLine();
            if (ConfirmStudentPassword.equals(studentPassword)) {
                System.out.println("Authentication Granted!");
                break;
            } else {
                System.out.println("Passwords don't match!");
            }
        }while (true);

        File studentFile = new File("..\\The_Test_Taker\\All_Text_Files\\Credentials\\StudentDetails\\" + Rollno + ".txt");
        if (studentFile.createNewFile()){
            FileWriter w = new FileWriter(studentFile);
            w.write("#########################################################################\r\n"+
                    "This the the master file for the student-"+studentName+"\r\n"+
                    "#########################################################################\r\n"+
                    "Name- "+studentName+"\r\n"+
                    "Rollno- "+Rollno+"\r\n"+	
                    "Gender- "+studentGender+"\r\n"+
                    "Semester- 3\r\n"+
                    "Password- "+studentPassword+"\r\n"+
                    "#########################################################################");
                    w.close();
        }
        FileWriterStudent(studentName,studentGender,Rollno);
        File class_data= new File("c:\\Manish\\The_Test_Taker\\All_Text_Files\\Student_Record\\Class_data.txt");
        FileWriter writer= new FileWriter(class_data,true);
        writer.write("\r\n"+studentName);
        writer.write("\r\n"+Rollno);
        writer.write("\r\n--------------");
        writer.close();
        Student s=new Student(studentName,Rollno,3,studentGender);
        s.welcome();
    }

    public static void Teacher_Login() throws IOException {
        String teacherName;
        String teacherPassword;
        String f_name,l_name;
        System.out.println("################################## TEACHER LOGIN ####################################");
        System.out.println("Please Enter Your First Name : ");
        f_name = Parent_Scanner.nextLine();
        
        while(true){
            f_name = Parent_Scanner.nextLine();
            if(isStringOnlyAlphabet(f_name)){
                break;
            }else{
                System.out.println("Enter your First name Again!");
            }

        }

        System.out.println("Please Enter Your Last Name : ");
        //l_name = Parent_Scanner.nextLine();
        
        while(true){
            l_name = Parent_Scanner.next();
            if(isStringOnlyAlphabet(l_name)){
                break;
            }else{
                System.out.println("Enter your Last name Again!");
            }

        }

        teacherName=f_name+" "+l_name;

        System.out.println();
        System.out.println("Enter your Password : ");
        teacherPassword = Parent_Scanner.next();
        System.out.println();

        File TeacherFolder = new File("..\\The_Test_Taker\\All_Text_Files\\Credentials\\TeacherDetails");
        try {
            if (!(TeacherFolder.exists()))
                throw new FileNotFoundException("Folder Does not Exist!");
        } catch (Exception e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println("Create your account first!");
            Teacher_Signup();
        }
        if (TeacherFolder.exists()) {
            //System.out.println("New Folder (TeacherDetails) has been created.");
            File TeacherFiles = new File("C://Manish//The_Test_Taker//All_Text_files//Credentials//TeacherDetails//" + teacherName + ".txt");

            try {
                if (!(TeacherFiles.exists()))
                    throw new FileNotFoundException("File Does Not Exist");
            } catch (Exception e) {
                System.out.println();
                System.out.println(e.getMessage());
                System.out.println("Please create your account first!");
                Teacher_Signup();
            }
            if (TeacherFiles.exists()) {
                BufferedReader T_Login_BR = new BufferedReader(new FileReader("C://Manish//The_Test_Taker//All_Text_files//Credentials//TeacherDetails//" + teacherName + ".txt"));
                String PassCheck;
                T_Login_BR.readLine();
                T_Login_BR.readLine();
                T_Login_BR.readLine();
                T_Login_BR.readLine(); // skip name
                T_Login_BR.readLine(); // skip gender
                T_Login_BR.readLine(); // skip designation
                PassCheck = T_Login_BR.readLine().substring(10); // go to password
    
                try {
                    if (!(PassCheck.equals(teacherPassword))){
                        T_Login_BR.close();
                        throw new WrongPassword("INVALID PASSWORD!");
                    }    
                } catch (WrongPassword e) {
                    System.out.println();
                    System.out.println(e.getMessage());
                    System.out.println("Password doesn't match.");
                    T_Login_BR.close();
                    Teacher_Login();
                    
                }
                if (PassCheck.equals(teacherPassword))
                    System.out.println("Logged In Successfully.");

                T_Login_BR.close();
                Teacher some_teacher= new Teacher(teacherName);
                some_teacher.welcome();
            }
            //T_Login_BR.close();
        }
        
    }

    public static void Teacher_Signup() throws IOException {
        File Teacher_Directory = new File("..\\TheTestTaker\\All_Text_Files\\Credentials\\TeacherDetails");
        if (Teacher_Directory.mkdir()) {
            System.out.println("A new Parent folder (TeacherDetails) has been created.");
        } else {
            //System.out.println("TeacherDetails Folder exists.");
        }

        int teacherGender;
        String teacher_gender=null;
        String teacherName;
        int teacherDesignation=0;
        String teacher_designation=null;
        String teacherPassword;
        String ConfirmTeacherPassword;
        String f_name,l_name;

        
        System.out.println("############################ TEACHER SIGNUP ###################################");
        
        System.out.println("Please Enter Your First Name : ");
        
        
        while(true){
            f_name = Parent_Scanner.next();
            if(isStringOnlyAlphabet(f_name)){
                break;
            }else{
                System.out.println("Enter your First name Again!");
            }

        }

        System.out.println("Please Enter Your Last Name : ");
        //l_name = Parent_Scanner.nextLine();
        
        while(true){
            l_name = Parent_Scanner.next();
            if(isStringOnlyAlphabet(l_name)){
                break;
            }else{
                System.out.println("Enter your Last name Again!");
            }

        }

        teacherName=f_name+" "+l_name;
        
            System.out.println("Select Your Gender : ");
            System.out.println("1.Male\n2.Female");
        do{
            while(true){
                try{
                    teacherGender = Parent_Scanner.nextInt();
                    break;
                }catch(InputMismatchException e){
                    System.out.println("Invalid Input. Please Enter your gender again.");
                    Parent_Scanner.nextLine();
                }
            }

            
            if (teacherGender == 1) {
                teacher_gender = "Male";
                //teacherName = "Mr."+teacherName;
            }else if (teacherGender == 2) {
                teacher_gender = "Female";
                //teacherName = "Ms."+teacherName;
            }else
            {
                System.out.println("Enter a valid gender!");
            }
            }while(!(teacherGender == 1||teacherDesignation == 2));

            

            do {
                System.out.println("Select your Designation : ");
                System.out.println("1. Professor\n" + "2. Associate Proffessor\n" + "3. Intern");
                while(true){
                    try{
                        teacherDesignation = Parent_Scanner.nextInt();
                        break;
                    }catch(InputMismatchException e){
                        System.out.println("Invalid Input. Please Enter enter a valid choice again.");
                        Parent_Scanner.nextLine();
                    }
                }
                
                if (teacherDesignation==1)
                    teacher_designation="Professor";
                if (teacherDesignation==2)
                    teacher_designation="Associate Proffessor";
                if (teacherDesignation==3)
                    teacher_designation="Intern";
        }
        while (!(teacherDesignation == 1 ||teacherDesignation == 2 ||teacherDesignation == 3));

        do {
            System.out.println("Please Create your Password : \n");
            System.out.println("1. A password must have at least 8 characters.\n" +
                    "2. It must consist of only letters and digits.\n" +
                    "3. It must contain at least 2 digits \n");
            Parent_Scanner.nextLine();
            teacherPassword = Parent_Scanner.nextLine();
            if (PasswordChecker(teacherPassword)) {
                System.out.println("Password is valid!");
            } else {
                System.out.println("Password is not valid!");
                continue;
            }

            System.out.println("Confirm Your Password : ");
            ConfirmTeacherPassword = Parent_Scanner.nextLine();
            if (ConfirmTeacherPassword.equals(teacherPassword)) {
                System.out.println("Authentication Granted!");
                break;
            } else {
                System.out.println("Passwords don't match!");
                
            }
        }while (true);

        File teacherFile = new File("..\\The_Test_Taker\\All_Text_Files\\Credentials\\TeacherDetails\\" + teacherName + ".txt");
        File teacherFile2 = new File("..\\The_Test_Taker\\All_Text_Files\\Teacher_Record\\" + teacherName + ".txt");
        if (teacherFile.createNewFile()&&teacherFile2.createNewFile()) {
            System.out.println("Your Account has been created!");
            FileWriter w = new FileWriter(teacherFile);
            w.write("#########################################################################\r\n"+
                    "This the the master file for the Teacher-"+teacherName+"\r\n"+
                    "#########################################################################\r\n"+
                    "Name- "+teacherName+"\r\n"+	
                    "Gender- "+teacherGender+"\r\n"+
                    "Designation- "+teacher_designation+"\r\n"+
                    "Password- "+teacherPassword+"\r\n"+
                    "#########################################################################");
                    w.close();
        }
        FileWriterTeacher(teacherName,teacher_gender,teacher_designation);
        Teacher t = new Teacher(teacherName);
        t.welcome();
    }

    public static boolean PasswordChecker(String password) {

        if (password.length() < PasswordSize)
            return false;

        int charCount = 0;
        int numCount = 0;
        if(password.contains(" ")){
            return false;
        }
        for (int i = 0; i < password.length(); i++) {

            char Passwordch = password.charAt(i);

            if (is_Numeric(Passwordch))
                numCount++;
            else if (is_Letter(Passwordch))
                charCount++;
            else
                return false;
        }
        return (charCount >= 2 && numCount >= 2);
    }

    public static boolean is_Letter(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }

    public static boolean is_Numeric(char ch) {
        return (ch >= '0' && ch <= '9');
    }


    public static void FileWriterStudent(String studentName,String studentGender,String Rollno) throws IOException
    {
        File full_details_of_student= new File("..\\The_Test_Taker\\All_Text_Files\\Student_Record\\" + Rollno + ".txt");
        if(full_details_of_student.createNewFile()){
            System.out.println("User Successfully SignedUp!!");
        }else{
            System.out.println("There was some problem in creating your account. Please try again.");
            return;
        }
        BufferedWriter BW_StudentDetails = new BufferedWriter(new FileWriter("..\\The_Test_Taker\\All_Text_Files\\Student_Record\\" + Rollno + ".txt"));

        //change the path names!!
        BW_StudentDetails.write("###################################################################################\r\n");
        BW_StudentDetails.write("This is the master file of the individual Student-"+studentName+"\r\n");
        BW_StudentDetails.write("###################################################################################\r\n");
        BW_StudentDetails.write("Name- "+studentName+"\r\n");
        BW_StudentDetails.write("Roll no- "+Rollno+"\r\n");
        BW_StudentDetails.write("Gender- "+studentGender+"\r\n");
        BW_StudentDetails.write("Semester- 3\r\n");
        BW_StudentDetails.write("Subjects offered:- \r\n(1)OOP\r\n(2)DE\r\n(3)DSA\r\n");
        BW_StudentDetails.write("*****************************************************************************\r\n");
        BW_StudentDetails.write("Quiz Data\r\n");
        BW_StudentDetails.write("*****************************************************************************\r\n");
        BW_StudentDetails.write("Subject- OOP\r\n");
        BW_StudentDetails.write("List of Quizes taken with their marks\r\n");
        BW_StudentDetails.write("-----------------------------------------------------------------------------\r\n");
        BW_StudentDetails.write("Chap_1- 0\r\n");
        BW_StudentDetails.write("Chap_2- 0\r\n");
        BW_StudentDetails.write("Chap_3- 0\r\n");
        BW_StudentDetails.write("Chap_4- 0\r\n");
        BW_StudentDetails.write("-----------------------------------------------------------------------------\r\n");
        BW_StudentDetails.write("Subject- DE\r\n");
        BW_StudentDetails.write("List of Quizes taken with their marks\r\n");
        BW_StudentDetails.write("-----------------------------------------------------------------------------\r\n");
        BW_StudentDetails.write("Chap_1- 0\r\n");
        BW_StudentDetails.write("Chap_2- 0\r\n");
        BW_StudentDetails.write("Chap_3- 0\r\n");
        BW_StudentDetails.write("-----------------------------------------------------------------------------\r\n");
        BW_StudentDetails.write("Subject- DSA\r\n");
        BW_StudentDetails.write("List of Quizes taken with their marks\r\n");
        BW_StudentDetails.write("-----------------------------------------------------------------------------\r\n");
        BW_StudentDetails.write("Chap_1- 0\r\n");
        BW_StudentDetails.write("Chap_2- 0\r\n");
        BW_StudentDetails.write("Chap_3- 0\r\n");
        BW_StudentDetails.write("*****************************************************************************\r\n");
        BW_StudentDetails.write("Total marks- 0\r\n");
        BW_StudentDetails.write("Average- 0\r\n");
        BW_StudentDetails.write("*****************************************************************************\r\n");

        BW_StudentDetails.close();
    }
    public static void FileWriterTeacher(String teacherName,String gender,String designation) throws IOException
    {
        BufferedWriter BW_TeacherDetails = new BufferedWriter(new FileWriter("..\\The_Test_Taker\\All_Text_Files\\Teacher_Record\\" + teacherName + ".txt"));

        BW_TeacherDetails.write("#########################################################################");
        BW_TeacherDetails.write("This is the master file of the teacher-"+teacherName);
        BW_TeacherDetails.write("#########################################################################");
        BW_TeacherDetails.write("Name:- "+teacherName);
        BW_TeacherDetails.write("Gender:-"+gender);
        BW_TeacherDetails.write("Designation:-"+designation);
        BW_TeacherDetails.write("#########################################################################");

        BW_TeacherDetails.close();
        Teacher some_teacher= new Teacher(teacherName);
        some_teacher.welcome();
    }

    public static void main(String[] args) throws IOException {

        File Parent_Directory = new File("..\\The_Test_Taker");
        if (Parent_Directory.mkdir()) {
            System.out.println("A new Parent folder (TheTestTaker) has been created.");
        } else {
            System.out.println("Parent Folder exists.");
        }

        int choice=0;
        while(choice!=5){
            System.out.println("-*-*-*-*-*-*-*-*-*-MAIN MENU-*-*-*-*-*-*-*-*-*-");
            System.out.println("1. Student Sign-In");
            System.out.println("2. Student Sign-Up");
            System.out.println("3. Teacher Sign-In");
            System.out.println("4. Teacher Sign-Up");
            System.out.println("5. Exit the Application");
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
            while(true){
                try{
                    choice = Parent_Scanner.nextInt();
                    break;
                }catch(InputMismatchException e){
                    System.out.println("The entered choice was incorrect. Please try again!");
                    Parent_Scanner.nextLine();
                }
            }
            
            switch (choice) {
                case 1:
                    Student_Login();
                    break;

                case 2:
                    Student_Signup();
                    break;

                case 3:
                    Teacher_Login();
                    break;

                case 4:
                    Teacher_Signup();
                    break;
                case 5:
                    System.out.println("Thank you for using our application.");
                    System.out.println("Visit Again!");
                    break;
                default:
                    System.out.println("Invalid Choice Input!");
                    break;
            }
        }
    }
}
