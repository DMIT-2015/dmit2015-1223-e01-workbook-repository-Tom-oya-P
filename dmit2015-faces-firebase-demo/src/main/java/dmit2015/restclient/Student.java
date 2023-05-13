package dmit2015.restclient;

// must update pom.xml to use lombok
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

@Getter @Setter
@NoArgsConstructor
public class Student {

    // fields to store data (always private)
    // project lombok simplifies code from default getter/setter and uses @Getter/@Setter instead
    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

//    // in C# we use Properties() to access private fields, in Java we have getters and setters (public access model)
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }

    // to use auto generated boilerplate code (getters/setters/constructors etc) use alt+insert shortcut
    // project lombok improves upon this by lowering boilerplate code used and simplifying it into @ annotations
//    public Student() {
//    }


}
