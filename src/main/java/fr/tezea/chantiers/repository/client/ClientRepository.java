package fr.tezea.chantiers.repository.client;

import fr.tezea.chantiers.domain.client.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Client, Long>
{
}
