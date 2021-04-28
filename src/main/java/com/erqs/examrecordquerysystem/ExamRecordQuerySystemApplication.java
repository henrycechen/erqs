package com.erqs.examrecordquerysystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@SpringBootApplication
public class ExamRecordQuerySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamRecordQuerySystemApplication.class, args);
    }
}


@Entity
class User {

    private @Id
    @GeneratedValue
    Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private int privilege; // 0: student, 1: teacher, 2: administrator
    private boolean active;

    public User() {
    }

    public User(Long id, String username, String password, String firstName, String lastName, String email, String gender, int privilege, boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.privilege = privilege;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return privilege == user.privilege &&
                active == user.active &&
                id.equals(user.id) &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(gender, user.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, lastName, email, gender, privilege, active);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", privilege=" + privilege +
                ", active=" + active +
                '}';
    }
}

@Entity
class Module {
    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private float fullGrade;
    private boolean active;

    public Module() {
    }

    public Module(Long id, String name, float fullGrade, boolean active) {
        this.id = id;
        this.name = name;
        this.fullGrade = fullGrade;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getFullGrade() {
        return fullGrade;
    }

    public void setFullGrade(float fullGrade) {
        this.fullGrade = fullGrade;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        return Float.compare(module.fullGrade, fullGrade) == 0 &&
                active == module.active &&
                id.equals(module.id) &&
                Objects.equals(name, module.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fullGrade, active);
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullGrade=" + fullGrade +
                ", active=" + active +
                '}';
    }
}

@Entity
class Grade {
    private @Id
    @GeneratedValue
    Long id;
    //    private Long uid;
    private Long mid;
    private Long sid;
    private Long lid;
    private float grade;
    private int year;
    private int semester;
    private boolean pass;
    private boolean active;

    public Grade() {
    }

    public Grade(Long id, Long sid, Long lid, Long mid, float grade, int year, int semester, boolean pass, boolean active) {
        this.id = id;
        this.sid = sid;
        this.lid = lid;
        this.mid = mid;
        this.grade = grade;
        this.year = year;
        this.semester = semester;
        this.pass = pass;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getLid() {
        return lid;
    }

    public void setLid(Long lid) {
        this.lid = lid;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade1 = (Grade) o;
        return Float.compare(grade1.grade, grade) == 0 &&
                year == grade1.year &&
                semester == grade1.semester &&
                pass == grade1.pass &&
                active == grade1.active &&
                id.equals(grade1.id) &&
                sid.equals(grade1.sid) &&
                lid.equals(grade1.lid) &&
                mid.equals(grade1.mid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sid, lid, mid, grade, year, semester, pass, active);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", sid=" + sid +
                ", lid=" + lid +
                ", mid=" + mid +
                ", grade=" + grade +
                ", year=" + year +
                ", semester=" + semester +
                ", pass=" + pass +
                ", active=" + active +
                '}';
    }
}


interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.active = false")
    List<User> findAllActiveUsers();

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findUserByUsername(String username);
}

interface ModuleRepository extends JpaRepository<Module, Long> {

    @Query("SELECT u FROM User u WHERE u.active = false")
    Optional<EntityModel<Module>> findAllActiveModules();

    @Query("SELECT m FROM Module m WHERE m.name = ?1")
    Optional<Module> findModuleByModuleName(String name);
}

interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("SELECT u FROM User u WHERE u.active = false")
    List<Grade> findAllActiveGradeRecords();

    @Query("SELECT g FROM Grade g WHERE g.sid = ?1")
    List<Grade> findGradeRecordsBySid(Long sid);

    @Query("SELECT g FROM Grade g WHERE g.lid = ?1")
    List<Grade> findGradeRecordsByLid(Long lid);

    @Query("SELECT g FROM Grade g WHERE g.mid = ?1")
    List<Grade> findGradeRecordsByMid(Long mid);
}


@RestController
class UserController {
    private final UserRepository repository;

    private final UserModelAssembler assembler;

    // An UserRepository is injected by constructor into the controller.
    @Autowired
    UserController(UserRepository repository, UserModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    //test
    @GetMapping("/all-active-user")
    CollectionModel<User> allActive() {
        List<User> users = repository.findAllActiveUsers();
        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping("/get-user-by-username")
    User check(@RequestBody User verifyUser) {
        return repository.findUserByUsername(verifyUser.getUsername());
    }

    @GetMapping("/users")
    CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> users = repository.findAll().stream()
                .map(assembler::toModel) // user -> assembler.toModel(user)
                .collect(Collectors.toList());
        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping("/users")
    ResponseEntity<?> newUser(@RequestBody User newUser) {
        EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/users/{id}")
    EntityModel<User> one(@PathVariable Long id) {
        return assembler.toModel(repository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    @PutMapping("/users/{id}")
    ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        User updatedUser = repository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setEmail(newUser.getEmail());
                    user.setGender(newUser.getGender());
                    user.setPrivilege(newUser.getPrivilege());
                    user.setActive(true);
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
        EntityModel<User> entityModel = assembler.toModel(updatedUser);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

@RestController
class ModuleController {
    private final ModuleRepository repository;

    private final ModuleModelAssembler assembler;

    @Autowired
    ModuleController(ModuleRepository repository, ModuleModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @PostMapping("/get-module-by-name")
    EntityModel<Module> check(@RequestBody Module verifyModule) {
        return assembler.toModel(
                repository.findModuleByModuleName(verifyModule.getName()).orElseThrow(() -> new ModuleNotFoundException(verifyModule.getName()))
        );
    }

    @GetMapping("/modules")
    CollectionModel<EntityModel<Module>> all() {
        List<EntityModel<Module>> modules = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(modules, linkTo(methodOn(ModuleController.class).all()).withSelfRel());
    }

    @PostMapping("/modules")
    ResponseEntity<?> newModule(@RequestBody Module newModule) {
        EntityModel<Module> entityModel = assembler.toModel(repository.save(newModule));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/modules/{id}")
    EntityModel<Module> one(@PathVariable Long id) {
        return assembler.toModel(repository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    @PutMapping("/modules/{id}")
    ResponseEntity<?> replaceModule(@RequestBody Module newModule, @PathVariable Long id) {
        Module updatedModule = repository.findById(id)
                .map(module -> {
                    module.setName(newModule.getName());
                    module.setFullGrade(newModule.getFullGrade());
                    module.setActive(true);
                    return repository.save(module);
                })
                .orElseGet(() -> {
                    newModule.setId(id);
                    return repository.save(newModule);
                });
        EntityModel<Module> entityModel = assembler.toModel(updatedModule);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/modules/{id}")
    ResponseEntity<?> deleteModule(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

@RestController
class GradeController {
    private final GradeRepository repository;

    private final GradeModelAssembler assembler;

    // An UserRepository is injected by constructor into the controller.
    @Autowired
    GradeController(GradeRepository repository, GradeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    //test
    @GetMapping("/all-active-grade-records")
    CollectionModel<Grade> allActive() {
        List<Grade> grades = repository.findAllActiveGradeRecords();
        return CollectionModel.of(grades, linkTo(methodOn(GradeController.class).all()).withSelfRel());
    }

    @PostMapping("/get-grades-by-sid")
    CollectionModel<EntityModel<Grade>> checkBySid(@RequestBody Grade targetGrade) {
        List<EntityModel<Grade>> grades = repository.findGradeRecordsBySid(targetGrade.getSid()).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(grades, linkTo(methodOn(GradeController.class).all()).withSelfRel());
    }

    @PostMapping("/get-grades-by-lid")
    CollectionModel<EntityModel<Grade>> checkByLid(@RequestBody Grade targetGrade) {
        List<EntityModel<Grade>> grades = repository.findGradeRecordsByLid(targetGrade.getLid()).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(grades, linkTo(methodOn(GradeController.class).all()).withSelfRel());
    }

    @PostMapping("/get-grades-by-mid")
    CollectionModel<EntityModel<Grade>> checkByMid(@RequestBody Grade targetGrade) {
        List<EntityModel<Grade>> grades = repository.findGradeRecordsByMid(targetGrade.getMid()).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(grades, linkTo(methodOn(GradeController.class).all()).withSelfRel());
    }

    @GetMapping("/grades")
    CollectionModel<EntityModel<Grade>> all() {
        List<EntityModel<Grade>> grades = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(grades, linkTo(methodOn(GradeController.class).all()).withSelfRel());
    }

    @PostMapping("/grades")
    ResponseEntity<?> newGrade(@RequestBody Grade newGrade) {
        EntityModel<Grade> entityModel = assembler.toModel(repository.save(newGrade));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/grades/{id}")
    EntityModel<Grade> one(@PathVariable Long id) {
        return assembler.toModel(repository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    @PutMapping("/grades/{id}")
    ResponseEntity<?> replaceUser(@RequestBody Grade newGrade, @PathVariable Long id) {
        Grade updatedGrade = repository.findById(id)
                .map(grade -> {
                    grade.setMid(newGrade.getMid());
                    grade.setSid(newGrade.getSid());
                    grade.setLid(newGrade.getLid());
                    grade.setGrade(newGrade.getGrade());
                    grade.setSemester(newGrade.getSemester());
                    grade.setYear(newGrade.getYear());
                    grade.setPass(newGrade.getGrade() >= 60);
                    grade.setActive(true);
                    return repository.save(grade);
                })
                .orElseGet(() -> {
                    newGrade.setId(id);
                    return repository.save(newGrade);
                });
        EntityModel<Grade> entityModel = assembler.toModel(updatedGrade);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/grades/{id}")
    ResponseEntity<?> deleteGrade(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}


class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Could not find user: " + id);
    }
}

class ModuleNotFoundException extends RuntimeException {
    public ModuleNotFoundException(Long id) {
        super("Could not find module: " + id);
    }

    public ModuleNotFoundException(String name) {
        super("Could not find module: " + name);
    }
}

class GradeNotFoundException extends RuntimeException {
    public GradeNotFoundException(Long id) {
        super("Could not find grade record: " + id);
    }
}


@ControllerAdvice
class UserNotFoundAdvice {
    @ResponseBody
    // "@ResponseBody" signals that this advice is rendered straight into the response body.
    @ExceptionHandler(UserNotFoundException.class)
    // "@ExceptionHandler" configures the advice to only respond if an EmployeeNotFoundException is thrown.
    @ResponseStatus(HttpStatus.NOT_FOUND)
        // "@ResponseStatus" says to issue an HttpStatus.NOT_FOUND, i.e. an HTTP 404.
    String userNotFoundHandler(UserNotFoundException e) {
        return e.getMessage();
    }
}

@ControllerAdvice
class ModuleNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ModuleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String moduleNotFoundHandler(ModuleNotFoundException e) {
        return e.getMessage();
    }
}

@ControllerAdvice
class GradeNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(GradeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String gradeNotFoundHandler(GradeNotFoundException e) {
        return e.getMessage();
    }
}


@Component
class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(User user) {
        return EntityModel.of(user,
                // "withSelfRel()" asks that Spring HATEOAS build a link to the UserController's one() method,
                // and flag it as a self link.
                linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
                // "withRel("users")" asks Spring HATEOAS to build a link to the aggregate root, all(), and call it "users".
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }
}

@Component
class ModuleModelAssembler implements RepresentationModelAssembler<Module, EntityModel<Module>> {
    @Override
    public EntityModel<Module> toModel(Module module) {
        return EntityModel.of(module,
                linkTo(methodOn(UserController.class).one(module.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("modules"));
    }
}

@Component
class GradeModelAssembler implements RepresentationModelAssembler<Grade, EntityModel<Grade>> {
    @Override
    public EntityModel<Grade> toModel(Grade grade) {
        return EntityModel.of(grade,
                linkTo(methodOn(UserController.class).one(grade.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("grade records"));
    }
}
