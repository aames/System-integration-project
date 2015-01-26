package Core.Workers;

import Core.Objects.Module;
import Core.Objects.Student;
import DataSources.DataProcessor;
import ExceptionHandler.ModuleCodeInvalidException;
import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class StudentBuilder {

    ArrayList<Student> students = new ArrayList();
    String classListFilePath, marksListFilePath, attendanceListFilePath;

    public StudentBuilder(String classListFilePath, String marksListFilePath, String attendanceListFilePath, ArrayList<Student> studentList) {
        this.classListFilePath = classListFilePath;
        this.marksListFilePath = marksListFilePath;
        this.attendanceListFilePath = attendanceListFilePath;
        //move Students to this class to work on them, this way we wont duplicate any
        this.students.clear();
        for (Student s : studentList) {
            this.students.add(s);
        }
    }

    public ArrayList<Student> buildStudents() throws ModuleCodeInvalidException {
        DataProcessor dataProcessor = new DataProcessor(classListFilePath, marksListFilePath, attendanceListFilePath);
        ArrayList<String[]> classList = dataProcessor.getClassList();
        for (String[] s : classList) {
            String[] studentConstruct = {s[0], s[1], s[2], s[3]};
            Student student = new Student(studentConstruct);
            String attendance = dataProcessor.getModuleAttendance(student.getStudentid());
            String mark = dataProcessor.getModuleMark(student.getStudentid());
            // format required: title code leader level mark attendance
            String[] moduleConstruct = {s[5], s[4], s[6], "" + s[4].charAt(s[4].length() - 1), ""+mark, ""+attendance};
            Module module = new Module(moduleConstruct);

            // Students shouldn't duplicate and modules shouldn't either
            if (!student.getModules().contains(module)) {
                student.addModule(module);
                if (!students.contains(student)) {
                    students.add(student);
                } else {
                    //check current students for the one we're looking for... 
                    for (Student studentFromList : students) {
                        // if the student matches the student ID and does not contain this module already then add it
                        if (studentFromList.getStudentid().equalsIgnoreCase(s[1]) && (!studentFromList.getModules().contains(module))) {
                            studentFromList.addModule(module);
                        }
                    }
                }
            }
        }
        return this.students;
    }
}
