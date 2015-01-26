package Tests;

import Core.Objects.Module;
import Core.Objects.Student;
import Core.Workers.StudentBuilder;
import ExceptionHandler.ModuleCodeInvalidException;
import ExceptionHandler.ModuleNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class StudentBuilderTest {

    public static void main(String args[]) throws ModuleCodeInvalidException, ModuleNotFoundException {
        ArrayList<Student> students = new ArrayList();
        System.out.println("===sb1===");
        StudentBuilder sb = new StudentBuilder("c:/classlist.xlsx", "c:/ce01096-5marks.xls", "c:/ce01096-5sesi.xls", students);
        students = sb.buildStudents();
        for (Student s : students) {
            System.out.println(s.getStudentid() + s.getName() + s.getStudytype() + s.getAwardcode());
        }
        // test dupes
        System.out.println("===sb2===");
        StudentBuilder sb2 = new StudentBuilder("c:/classlist.xlsx", "c:/ce01096-5marks.xls", "c:/ce01096-5sesi.xls", students);
        students = sb2.buildStudents();
        for (Student s : students) {
            System.out.println(s.getStudentid() + s.getName() + s.getStudytype() + s.getAwardcode());
        }
        // test second module 
        System.out.println("===sb3===");
        StudentBuilder sb3 = new StudentBuilder("c:/2classlist.xlsx", "c:/2ce01094-5marks.xls", "c:/2ce01094-5sesi.xls", students);
        students = sb3.buildStudents();
        for (Student s : students) {
            System.out.println(s.getStudentid() + s.getName() + s.getStudytype() + s.getAwardcode());
            System.out.println("Modules");
            for (Module m : s.getModules()) {
                System.out.println(m.getCode());
            }
        }
        // test student builder against incorrect files
        try {
            System.out.println("===sb4===");
            StudentBuilder sb4 = new StudentBuilder("c:/classlist.xlsx", "c:/ce01096-5marks.xls", "c:/ce01096-5marks.xls", students);
            students = sb4.buildStudents();
            for (Student s : students) {
                System.out.println(s.getStudentid() + s.getName() + s.getStudytype() + s.getAwardcode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
