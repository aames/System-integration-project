package Core.Objects;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Andrew
 */
public class Student {

    private String name;
    private String studentid;
    private String studytype;
    private String awardcode;
    private ArrayList<Module> modules;

    public Student(String id) {
        this.studentid = id;
    }

    public Student(String name, String studentid, ArrayList<Module> modules, String award, String studytype) {
        this.name = name;
        this.studentid = studentid;
        this.modules = modules;
        this.awardcode = award;
        this.studytype = studytype;
    }

    public Student(String[] studentDetails) {
        this.name = studentDetails[0];
        this.studentid = studentDetails[1];
        this.awardcode = studentDetails[2];
        this.studytype = studentDetails[3];
        modules = new ArrayList();
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules.clear();
        for (Module m : modules) {
            this.addModule(m);
        }
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void addModule(Module m) {
        this.modules.add(m);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getAwardcode() {
        return awardcode;
    }

    public void setAwardcode(String awardcode) {
        this.awardcode = awardcode;
    }

    public String getStudytype() {
        return studytype;
    }

    public void setStudytype(String studytype) {
        this.studytype = studytype;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (!Objects.equals(this.studentid, other.studentid)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.studentid);
        return hash;
    }

    public Module getModule(String code) throws ExceptionHandler.ModuleNotFoundException {
        if (this.modules.size() > 0) {
            for (Module m : modules) {
                if (m.getCode().equals(code)) {
                    return m;
                }
            }
            throw new ExceptionHandler.ModuleNotFoundException("Module code not found:" + code);
        }
        throw new ExceptionHandler.ModuleNotFoundException("Module code not found:" + code);
    }
}
