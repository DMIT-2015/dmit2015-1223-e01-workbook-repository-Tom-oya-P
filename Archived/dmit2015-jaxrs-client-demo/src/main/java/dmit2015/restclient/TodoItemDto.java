package dmit2015.restclient;

import lombok.Data;

@Data
public class TodoItemDto {

    private Long id;

    private String name;

    private boolean completed;

    private Integer version;

}
