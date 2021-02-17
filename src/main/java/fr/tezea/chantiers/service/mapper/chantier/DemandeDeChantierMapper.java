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

import fr.tezea.chantiers.domain.chantier.DemandeDeChantier;
import fr.tezea.chantiers.domain.client.Client;
import fr.tezea.chantiers.domain.site.Site;
import fr.tezea.chantiers.repository.client.ClientRepository;
import fr.tezea.chantiers.repository.site.SiteRepository;
import fr.tezea.chantiers.service.dto.chantier.DemandeDeChantierDTO;
import fr.tezea.chantiers.service.dto.chantier.DemandeDeChantierGetDTO;
import fr.tezea.chantiers.service.mapper.client.ClientMapper;
import fr.tezea.chantiers.service.mapper.site.SiteMapper;
import java.util.List;
import java.util.Optional;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = { ClientMapper.class,
        SiteMapper.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DemandeDeChantierMapper
{
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "siteId", target = "site", qualifiedByName = "siteIdToSite")
    @Mapping(source = "clientId", target = "client", qualifiedByName = "clientIdToClient")
    DemandeDeChantier toDemandeDeChantier(DemandeDeChantierDTO demandeDeChantierDTO,
            @Context ClientRepository clientRepository, @Context SiteRepository siteRepository);

    @InheritInverseConfiguration(name = "toDemandeDeChantier")
    @Mapping(source = "site.id", target = "siteId")
    @Mapping(source = "client.id", target = "clientId")
    DemandeDeChantierDTO toDemandeDeChantierDTO(DemandeDeChantier demandeDeChantier);

    @Mapping(target = "id", ignore = true)
    DemandeDeChantier toDemandeDeChantier(DemandeDeChantierGetDTO demandeDeChantierDTO);

    @InheritInverseConfiguration(name = "toDemandeDeChantier")
    DemandeDeChantierGetDTO toDemandeDeChantierGetDTO(DemandeDeChantier demandeDeChantier);

    List<DemandeDeChantierGetDTO> toDemandeDeChantierGetDTO(List<DemandeDeChantier> demandeDeChantiers);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "siteId", target = "site", qualifiedByName = "siteIdToSite")
    @Mapping(source = "clientId", target = "client", qualifiedByName = "clientIdToClient")
    DemandeDeChantier updateDemandeDeChantierFromDTO(DemandeDeChantierDTO demandeDeChantierDTO,
            @MappingTarget DemandeDeChantier demandeDeChantier, @Context ClientRepository clientRepository,
            @Context SiteRepository siteRepository);

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
