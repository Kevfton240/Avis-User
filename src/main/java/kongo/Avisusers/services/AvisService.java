package kongo.Avisusers.services;


import kongo.Avisusers.entites.Avis;
import kongo.Avisusers.entites.Utilisateur;
import kongo.Avisusers.repository.AvisRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class AvisService {

    private final AvisRepository avisRepository;

    public void creer(Avis avis){
     Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     avis.setUtilisateur(utilisateur);
        this.avisRepository.save(avis);
    }
}
