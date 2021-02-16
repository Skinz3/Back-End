package fr.tezea.chantiers.repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fr.tezea.chantiers.domain.user.Utilisateur;

@Repository
public interface UtilisateurRepository extends MongoRepository<Utilisateur, Long>
{

}
