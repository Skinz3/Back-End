/*
 * MIT License
 *
 * Copyright (c) 2021 TEZEA-Chantiers
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package fr.tezea.chantiers.service.mapper.chantier;

import fr.tezea.chantiers.domain.chantier.Chantier;
import fr.tezea.chantiers.domain.chantier.Media;
import fr.tezea.chantiers.domain.chantier.Probleme;
import fr.tezea.chantiers.domain.chantier.RapportChantierRegulier;
import fr.tezea.chantiers.domain.client.Client;
import fr.tezea.chantiers.domain.site.Site;
import fr.tezea.chantiers.repository.chantier.MediaRepository;
import fr.tezea.chantiers.repository.chantier.ProblemeRepository;
import fr.tezea.chantiers.repository.chantier.RapportChantierRegulierRepository;
import fr.tezea.chantiers.repository.client.ClientRepository;
import fr.tezea.chantiers.repository.site.SiteRepository;
import fr.tezea.chantiers.service.dto.chantier.ChantierDTO;
import fr.tezea.chantiers.service.dto.chantier.ChantierGetDTO;
import fr.tezea.chantiers.service.mapper.client.ClientMapper;
import fr.tezea.chantiers.service.mapper.site.SiteMapper;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = { ClientMapper.class, SiteMapper.class, ProblemeMapper.class,
        MediaMapper.class,
        RapportChantierRegulierMapper.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ChantierMapper
{
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "siteId", target = "site", qualifiedByName = "siteIdToSite")
    @Mapping(source = "clientId", target = "client", qualifiedByName = "clientIdToClient")
    @Mapping(source = "problemeIds", target = "problemes", qualifiedByName = "problemeIdsToProblemes")
    @Mapping(source = "mediaIds", target = "medias", qualifiedByName = "mediaIdsToMedias")
    @Mapping(source = "rapportsRegulierIds", target = "rapportsRegulier", qualifiedByName = "rapportsRegulierIdsToRapportsRegulier")
    Chantier toChantier(ChantierDTO chantierDTO, @Context ClientRepository clientRepository,
            @Context SiteRepository siteRepository, @Context ProblemeRepository problemeRepository,
            @Context MediaRepository mediaRepository,
            @Context RapportChantierRegulierRepository rapportChantierRegulierRepository);

    @InheritInverseConfiguration(name = "toChantier")
    @Mapping(source = "site.id", target = "siteId")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "problemes", target = "problemeIds", qualifiedByName = "problemesToProblemeIds")
    @Mapping(source = "medias", target = "mediaIds", qualifiedByName = "mediasToMediaIds")
    @Mapping(source = "rapportsRegulier", target = "rapportsRegulierIds", qualifiedByName = "rapportsRegulierToRapportsRegulierIds")
    ChantierDTO toChantierDTO(Chantier chantier);

    @Mapping(target = "id", ignore = true)
    Chantier toChantier(ChantierGetDTO chantierGetDTO);

    @InheritInverseConfiguration(name = "toChantier")
    ChantierGetDTO toChantierGetDTO(Chantier chantier);

    List<ChantierGetDTO> toChantierGetDTO(List<Chantier> chantiers);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "siteId", target = "site", qualifiedByName = "siteIdToSite")
    @Mapping(source = "clientId", target = "client", qualifiedByName = "clientIdToClient")
    @Mapping(source = "problemeIds", target = "problemes", qualifiedByName = "problemeIdsToProblemes")
    @Mapping(source = "mediaIds", target = "medias", qualifiedByName = "mediaIdsToMedias")
    @Mapping(source = "rapportsRegulierIds", target = "rapportsRegulier", qualifiedByName = "rapportsRegulierIdsToRapportsRegulier")
    Chantier updateChantierFromDTO(ChantierDTO chantierDTO, @MappingTarget Chantier chantier,
            @Context ClientRepository clientRepository, @Context SiteRepository siteRepository,
            @Context ProblemeRepository problemeRepository, @Context MediaRepository mediaRepository,
            @Context RapportChantierRegulierRepository rapportChantierRegulierRepository);

    @Named("problemesToProblemeIds")
    default Set<Long> problemesToProblemeIds(Set<Probleme> problemes)
    {
        Set<Long> problemeIds = new HashSet<>();

        for (Probleme probleme : problemes)
        {
            problemeIds.add(probleme.getId());
        }
        return problemeIds;
    }

    @Named("mediasToMediaIds")
    default Set<Long> mediasToMediaIds(Set<Media> medias)
    {
        Set<Long> mediaIds = new HashSet<>();

        for (Media media : medias)
        {
            mediaIds.add(media.getId());
        }
        return mediaIds;
    }

    @Named("rapportsRegulierToRapportsRegulierIds")
    default Set<Long> rapportsRegulierToRapportsRegulierIds(Set<RapportChantierRegulier> rapportsRegulier)
    {
        Set<Long> rapportsRegulierIds = new HashSet<>();

        for (RapportChantierRegulier rapportChantierRegulier : rapportsRegulier)
        {
            rapportsRegulierIds.add(rapportChantierRegulier.getId());
        }
        return rapportsRegulierIds;
    }

    @Named("problemeIdsToProblemes")
    default Set<Probleme> problemeIdsToProblemes(Set<Long> problemeIds, @Context ProblemeRepository problemeRepository)
    {

        if (problemeIds == null)
        {
            return null;
        }
        Set<Probleme> problemeIdsTmp = new HashSet<>();

        for (long problemeId : problemeIds)
        {
            Optional<Probleme> probleme = problemeRepository.findById(problemeId);

            if (probleme.isPresent())
            {
                problemeIdsTmp.add(probleme.get());
            }
        }
        return problemeIdsTmp;
    }

    @Named("mediaIdsToMedias")
    default Set<Media> mediaIdsToMedias(Set<Long> mediaIds, @Context MediaRepository mediaRepository)
    {

        if (mediaIds == null)
        {
            return null;
        }
        Set<Media> mediaIdsTmp = new HashSet<>();

        for (long mediaId : mediaIds)
        {
            Optional<Media> media = mediaRepository.findById(mediaId);

            if (media.isPresent())
            {
                mediaIdsTmp.add(media.get());
            }
        }
        return mediaIdsTmp;
    }

    @Named("rapportsRegulierIdsToRapportsRegulier")
    default Set<RapportChantierRegulier> rapportsRegulierIdsToRapportsRegulier(Set<Long> rapportsRegulierIds,
            @Context RapportChantierRegulierRepository rapportChantierRegulierRepository)
    {

        if (rapportsRegulierIds == null)
        {
            return null;
        }
        Set<RapportChantierRegulier> rapportsRegulierIdsTmp = new HashSet<>();

        for (long rapportsRegulierId : rapportsRegulierIds)
        {
            Optional<RapportChantierRegulier> rapportChantierRegulier = rapportChantierRegulierRepository
                    .findById(rapportsRegulierId);

            if (rapportChantierRegulier.isPresent())
            {
                rapportsRegulierIdsTmp.add(rapportChantierRegulier.get());
            }
        }
        return rapportsRegulierIdsTmp;
    }

    @Named("clientIdToClient")
    default Client clientIdToClient(long clientId, @Context ClientRepository clientRepository)
    {
        Optional<Client> client = clientRepository.findById(clientId);

        if (client.isPresent())
        {
            return client.get();
        }
        return null;
    }

    @Named("siteIdToSite")
    default Site siteIdToSite(long siteId, @Context SiteRepository siteRepository)
    {
        Optional<Site> site = siteRepository.findById(siteId);

        if (site.isPresent())
        {
            return site.get();
        }
        return null;
    }
}
