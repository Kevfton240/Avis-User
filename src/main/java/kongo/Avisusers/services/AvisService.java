package kongo.Avisusers.services;


import kongo.Avisusers.entites.Avis;
import kongo.Avisusers.repository.AvisRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class AvisService {

    private final AvisRepository avisRepository;

    public void creer(Avis avis){
        this.avisRepository.save(avis);
    }
}
