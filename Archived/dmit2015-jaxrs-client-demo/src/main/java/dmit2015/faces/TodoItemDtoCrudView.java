package dmit2015.faces;

import dmit2015.entity.TodoItemDto;
import dmit2015.persistence.TodoItemDtoRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

import java.io.Serializable;

@Named("currentTodoItemDtoCrudView")
@ViewScoped
public class TodoItemDtoCrudView implements Serializable {

    @Inject
    private TodoItemDtoRepository _todoitemdtoRepository;

    @Getter
    private List<TodoItemDto> todoitemdtoList;

    @Getter
    @Setter
    private TodoItemDto selectedTodoItemDto;

    @Getter
    @Setter
    private Long selectedId;

    @PostConstruct  // After @Inject is complete
    public void init() {
        try {
            todoitemdtoList = _todoitemdtoRepository.findAll();
        } catch (Exception ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }

    public void onOpenNew() {
        selectedTodoItemDto = new TodoItemDto();
    }

    public void onSave() {
        if (selectedId == null) {
            try {
                _todoitemdtoRepository.add(selectedTodoItemDto);
                Messages.addGlobalInfo("Create was successful. {0}", selectedTodoItemDto.getId());
                todoitemdtoList = _todoitemdtoRepository.findAll();
            } catch (RuntimeException e) {
                Messages.addGlobalError(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                Messages.addGlobalError("Create was not successful. {0}", e.getMessage());
            }
        } else {
            try {
                _todoitemdtoRepository.update(selectedId, selectedTodoItemDto);
                Messages.addFlashGlobalInfo("Update was successful.");
                todoitemdtoList = _todoitemdtoRepository.findAll();
            } catch (RuntimeException e) {
                Messages.addGlobalError(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                Messages.addGlobalError("Update was not successful.");
            }
        }

        PrimeFaces.current().executeScript("PF('manageTodoItemDtoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-TodoItemDtos");
    }

    public void onDelete() {
        try {
            _todoitemdtoRepository.delete(selectedTodoItemDto);
            selectedTodoItemDto = null;
            Messages.addGlobalInfo("Delete was successful.");
            todoitemdtoList = _todoitemdtoRepository.findAll();
            PrimeFaces.current().ajax().update("form:messages", "form:dt-TodoItemDtos");
        } catch (RuntimeException e) {
            Messages.addGlobalError(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Delete not successful.");
        }
    }

}