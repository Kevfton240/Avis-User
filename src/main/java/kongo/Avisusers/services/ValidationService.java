package kongo.Avisusers.services;

import kongo.Avisusers.entites.Utilisateur;
import kongo.Avisusers.entites.Validation;
import kongo.Avisusers.repository.ValidationRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@AllArgsConstructor
@Service
public class ValidationService {

    private ValidationRepository validationRepository;
    private NotificationService notificationService;

    public void enreistre(Utilisateur utilisateur){
        Validation validation = new Validation();
        validation.setUtilisateur(utilisateur);

        Instant creation = Instant.now();
        validation.setCreation(creation);

        Instant expiration = creation.plus(10, ChronoUnit.MINUTES);
        validation.setExpire(expiration);

        Random random = new Random();
        int randomInteger = random.nextInt(9999999);
        String code = String.format("%06d", randomInteger);
        validation.setCode(code);

        this.validationRepository.save(validation);
        this.notificationService.envoyer(validation);
    }

    public Validation lireEnFonctionDuCode(String code){
     return this.validationRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Votre code est invlide"));

    }
}
