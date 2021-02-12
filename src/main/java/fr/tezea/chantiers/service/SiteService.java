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
package fr.tezea.chantiers.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.tezea.chantiers.domain.site.Site;
import fr.tezea.chantiers.repository.site.SiteRepository;
import fr.tezea.chantiers.service.dto.site.SiteDTO;
import fr.tezea.chantiers.service.mapper.site.SiteMapper;

@Service
public class SiteService
{
	private final SiteMapper siteMapper;
	private final SiteRepository siteRepository;
	private final SequenceGeneratorService sequenceGenerator;


	public SiteService(SiteMapper siteMapper, SiteRepository siteRepository,
			SequenceGeneratorService sequenceGenerator) {
		super();
		this.siteMapper = siteMapper;
		this.siteRepository = siteRepository;
		this.sequenceGenerator = sequenceGenerator;
	}

	public SiteDTO getSiteById(long id)
	{
		Optional<Site> site = this.siteRepository.findById(id);

		if (site.isPresent())
		{
			return this.siteMapper.toSiteDTO(site.get());
		}
		return new SiteDTO();
	}

	public long addSite(SiteDTO siteDTO)
	{
		Site site = this.siteMapper.toSite(siteDTO);
		site.setId(sequenceGenerator.generateSequence(Site.SEQUENCE_NAME));
		return this.siteRepository.save(site).getId();
	}

	public SiteDTO updateSiteById(long id, SiteDTO siteDTO)
	{
		Optional<Site> site = this.siteRepository.findById(id);

		if (site.isPresent())
		{
			return this.siteMapper.toSiteDTO(
					siteRepository.save(this.siteMapper.updateSiteFromDTO(siteDTO, site.get())));
		}
		return new SiteDTO();
	}

	public void deleteSiteById(long id)
	{
		Optional<Site> site = this.siteRepository.findById(id);

		if (site.isPresent())
		{
			this.siteRepository.deleteById(id);
		}
	}
}
