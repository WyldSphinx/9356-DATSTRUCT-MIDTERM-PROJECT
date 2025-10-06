package GoogleClassroom.ReferenceClass;

public class Professor {
    private String professorName;
    private String professorId;
    private String department;

    public Professor() {
        this.professorName = null;
        this.professorId = null;
        this.department = null;
    }

    public Professor(String professorName, String professorId, String department) {
        this.professorName = professorName;
        this.professorId = professorId;
        this.department = department;
    }

    // Getters
    public String getProfessorName() { return professorName; }
    public String getProfessorId() { return professorId; }
    public String getDepartment() { return department; }

    // Setters
    public void setProfessorName(String professorName) { this.professorName = professorName; }
    public void setProfessorId(String professorId) { this.professorId = professorId; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        return professorName + " (" + department + ")";
    }
}
