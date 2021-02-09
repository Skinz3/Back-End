package fr.tezea.chantiers.repository.site;

import fr.tezea.chantiers.domain.site.Site;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends MongoRepository<Site, Long>
{
}
