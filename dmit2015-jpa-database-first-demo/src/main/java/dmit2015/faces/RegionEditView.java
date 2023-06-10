package dmit2015.faces;

import dmit2015.entity.Region;
import dmit2015.persistence.RegionRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Optional;

@Named("currentRegionEditView")
@ViewScoped
public class RegionEditView implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private RegionRepository _regionRepository;

    @Inject
    @ManagedProperty("#{param.editId}")
    @Getter
    @Setter
    private BigInteger editId;

    @Getter
    private Region existingRegion;

    @PostConstruct
    public void init() {
        if (!Faces.isPostback()) {
            if (editId != null) {
                Optional<Region> optionalRegion = _regionRepository.findById(editId);
                if (optionalRegion.isPresent()) {
                    existingRegion = optionalRegion.get();
                } else {
                    Faces.redirect(Faces.getRequestURI().substring(0, Faces.getRequestURI().lastIndexOf("/")) + "/index.xhtml");
                }
            } else {
                Faces.redirect(Faces.getRequestURI().substring(0, Faces.getRequestURI().lastIndexOf("/")) + "/index.xhtml");
            }
        }
    }

    public String onUpdate() {
        String nextPage = "";
        try {
            _regionRepository.update(editId, existingRegion);
            Messages.addFlashGlobalInfo("Update was successful.");
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Update was not successful.");
        }
        return nextPage;
    }
}