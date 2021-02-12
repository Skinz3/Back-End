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

import fr.tezea.chantiers.domain.chantier.Media;
import fr.tezea.chantiers.repository.chantier.DocumentRepository;
import fr.tezea.chantiers.service.dto.chantier.MediaDTO;
import fr.tezea.chantiers.service.mapper.chantier.MediaMapper;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MediaService
{
    private final MediaMapper mediaMapper;
    private final DocumentRepository mediaRepository;
    private final SequenceGeneratorService sequenceGenerator;

    public MediaService(MediaMapper mediaMapper, DocumentRepository mediaRepository,
            SequenceGeneratorService sequenceGenerator)
    {
        super();
        this.mediaMapper = mediaMapper;
        this.mediaRepository = mediaRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public MediaDTO getMediaById(long id)
    {
        Optional<Media> media = this.mediaRepository.findById(id);

        if (media.isPresent())
        {
            return this.mediaMapper.toMediaDTO(media.get());
        }
        return new MediaDTO();
    }

    public long addMedia(MediaDTO mediaDTO)
    {
        Media media = this.mediaMapper.toMedia(mediaDTO);
        media.setId(sequenceGenerator.generateSequence(Media.SEQUENCE_NAME));
        return this.mediaRepository.save(media).getId();
    }

    public MediaDTO updateMediaById(long id, MediaDTO mediaDTO)
    {
        Optional<Media> media = this.mediaRepository.findById(id);

        if (media.isPresent())
        {
            return this.mediaMapper
                    .toMediaDTO(mediaRepository.save(this.mediaMapper.updateMediaFromDTO(mediaDTO, media.get())));
        }
        return new MediaDTO();
    }

    public void deleteMediaById(long id)
    {
        Optional<Media> media = this.mediaRepository.findById(id);

        if (media.isPresent())
        {
            this.mediaRepository.deleteById(id);
        }
    }
}
