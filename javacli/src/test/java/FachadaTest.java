import com.danilo.salaaula.dao.*;
import com.danilo.salaaula.fachada.Fachada;
import com.danilo.salaaula.models.ClassRoom;
import com.danilo.salaaula.models.Post;
import com.danilo.salaaula.models.Professor;
import com.danilo.salaaula.models.Student;
import org.junit.jupiter.api.*;

import java.util.List;

public class FachadaTest {
    private final DAOStudent daoStudent     = new DAOStudent();
    private final DAOProfessor daoProfessor = new DAOProfessor();
    private final DAOClassroom daoClassroom = new DAOClassroom();
    private final DAOPost daoPost           = new DAOPost();
    private final DAOComment daoComment     = new DAOComment();


    @AfterEach
    void cleanDatabase() {
        Fachada.inicializar();
        List<Student> studentList = daoStudent.readAll();
        for (Student student: studentList)
            daoStudent.delete(student);

        List<ClassRoom> classRoomList = daoClassroom.readAll();
        for (ClassRoom classRoom: classRoomList)
            daoClassroom.delete(classRoom);

        List<Professor> professorList = daoProfessor.readAll();
        for (Professor professor: professorList)
            daoProfessor.delete(professor);

        List<Post> postList = daoPost.readAll();
        for (Post post: postList)
            daoPost.delete(post);

        Fachada.finalizar();
    }

    @Test
    @DisplayName("Should create a student")
    public void createStudent() {
       Fachada.inicializar();
       try {
           Fachada.addStudent("1", "Danilo", "danilo@gmail.com", "1234");
           Student student = daoStudent.read("danilo@gmail.com");
           Assertions.assertEquals("1", student.getCpf());
       } catch (Exception e) {
           String error = String.format("Error adding user %s", e.getMessage());
           Assertions.fail(error);
       }
        Fachada.finalizar();
    }

    @Test
    @DisplayName("Should create a professor")
    void creatProfessor() {
        Fachada.inicializar();
        try {
            Fachada.addProfessor("1", "Danilo", "danilo@gmail.com", "1234");
            Professor professor = daoProfessor.read("danilo@gmail.com");
            Assertions.assertEquals("1", professor.getCpf());
        } catch (Exception e) {
            String error = String.format("Error adding user %s", e.getMessage());
            Assertions.fail(error);
        }
        Fachada.finalizar();
    }

    @Test
    @DisplayName("Should sign in professor")
    void signInProfessor() {
        Fachada.inicializar();
        try {
            Fachada.addProfessor("1", "Danilo", "danilo@gmail.com", "1234");
            Professor professor = Fachada.signInProfessor("danilo@gmail.com", "1234");
            Assertions.assertEquals("1", professor.getCpf());
        } catch (Exception e) {
            String error = String.format("Error adding user %s", e.getMessage());
            Assertions.fail(error);
        }
        Fachada.finalizar();
    }

    @Test
    @DisplayName("Should sign in student")
    void signInStudent() {
        Fachada.inicializar();
        try {
            Fachada.addStudent("1", "Danilo", "danilo@gmail.com", "1234");
            Student student = Fachada.signInStudent("danilo@gmail.com", "1234");
            Assertions.assertEquals("1", student.getCpf());
        } catch (Exception e) {
            String error = String.format("Error adding user %s", e.getMessage());
            Assertions.fail(error);
        }
        Fachada.finalizar();
    }

    @Test
    @DisplayName("Should create a classroom")
    void createClass() {
        Fachada.inicializar();
        try {
            Fachada.addProfessor("1", "Danilo", "danilo@gmail.com", "1234");
            Professor professor = Fachada.signInProfessor("danilo@gmail.com", "1234");
            Fachada.addClassRoom("Turma 1", "danilo@gmail.com");
            ClassRoom c = daoClassroom.read("Turma 1");
            Assertions.assertEquals("Turma 1", c.getName());
            Assertions.assertEquals(professor.getName(), c.getAuthor().getName());
        } catch (Exception e) {
            String error = String.format("Error adding user %s", e.getMessage());
            Assertions.fail(error);
        }
        Fachada.finalizar();
    }

    @Test
    @DisplayName("Should list the user not in the class")
    void listUserNotInClass() {
        Fachada.inicializar();
        try {
            Fachada.addProfessor("1", "Danilo", "danilo@gmail.com", "1234");
            Fachada.addStudent("2", "Jose", "jose@gmail.com", "1234");
            Fachada.addClassRoom("Turma 1", "danilo@gmail.com");
            List<Student> studentList = Fachada.listUsersNotInClass("Turma 1");

            Assertions.assertEquals(1, studentList.size());
            Assertions.assertEquals("Jose", studentList.get(0).getName());
        } catch (Exception e) {
            String error = String.format("Error adding user %s", e.getMessage());
            Assertions.fail(error);
        }
        Fachada.finalizar();
    }

    @Test
    @DisplayName("Should list the user in the class")
    void listUserInClass() {
        Fachada.inicializar();
        try {
            Fachada.addProfessor("1", "Danilo", "danilo@gmail.com", "1234");
            Fachada.addStudent("2", "Jose", "jose@gmail.com", "1234");
            Fachada.addClassRoom("Turma 1", "danilo@gmail.com");
            Fachada.addStudentToClass("jose@gmail.com", "Turma 1");
            List<Student> studentList = Fachada.listStudentsInClass("Turma 1");

            Assertions.assertEquals(1, studentList.size());
            Assertions.assertEquals("Jose", studentList.get(0).getName());
        } catch (Exception e) {
            String error = String.format("Error adding user %s", e.getMessage());
            Assertions.fail(error);
        }
        Fachada.finalizar();
    }

    @Test
    @DisplayName("Should return all classes")
    void listAllClasses() {
        Fachada.inicializar();
        try {
            Fachada.addProfessor("1", "Danilo", "danilo@gmail.com", "1234");
            Fachada.addClassRoom("Turma 1", "danilo@gmail.com");
            Fachada.addClassRoom("Turma 2", "danilo@gmail.com");
            List<ClassRoom> classes = Fachada.getAllClasses();

            Assertions.assertEquals(2, classes.size());
            Assertions.assertEquals("Turma 1", classes.get(0).getName());
            Assertions.assertEquals("Turma 2", classes.get(1).getName());
        } catch (Exception e) {
            String error = String.format("Error adding user %s", e.getMessage());
            Assertions.fail(error);
        }
        Fachada.finalizar();
    }

    @Test
    @DisplayName("Should update classroom name")
    void updateClassRoomName() {
        Fachada.inicializar();
        try {
            Fachada.addProfessor("1", "Danilo", "danilo@gmail.com", "1234");
            Fachada.addClassRoom("Turma 1", "danilo@gmail.com");
            Fachada.updateClassRoomName("Turma 1", "Novo");
            ClassRoom c = Fachada.getClassRoomByname("Novo");

            Assertions.assertEquals("Novo", c.getName());
            Assertions.assertEquals("Danilo", c.getAuthor().getName());
        } catch (Exception e) {
            String error = String.format("Error adding user %s", e.getMessage());
            Assertions.fail(error);
        }
        Fachada.finalizar();
    }
}
