package dmit2015.restclient;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

// This lombok annotation adds getters, setters, and no arg constructor to all fields. Good for when a class is only storing data.
@Data
public class TodoItem {

    private String description;

    private boolean done;

    private LocalDateTime createTime;

}
