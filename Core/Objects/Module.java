package Core.Objects;

import java.util.Objects;

/**
 *
 * @author Andrew
 */
public class Module {
    private String title;
    private String code;
    private String level;
    private String leader;
    private String mark;
    private String attendance;

    public Module(String title, String code, String leader, String mark, String attendance, String level) {
        this.title = title;
        this.code = code;
        this.leader = leader;
        this.mark = mark;
        this.attendance = attendance;
        this.level = level;
    }
    public Module(String[] moduleDetails){
        this.title = moduleDetails[0];
        this.code = moduleDetails[1];
        this.leader = moduleDetails[2];
        this.level = moduleDetails[3];
        this.mark = moduleDetails[4];
        this.attendance = moduleDetails[5];
        
    }
    public Module(String title, String code, String leader, String level) {
        this.title = title;
        this.code = code;
        this.leader = leader;
        this.level = level;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Module other = (Module) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.code);
        return hash;
    }
    
}
