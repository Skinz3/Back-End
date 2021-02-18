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

import fr.tezea.chantiers.domain.chantier.Image;
import fr.tezea.chantiers.repository.chantier.ImageRepository;
import fr.tezea.chantiers.service.dto.chantier.ImageDTO;
import fr.tezea.chantiers.service.mapper.chantier.ImageMapper;
import java.io.IOException;
import java.util.Optional;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService
{
    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;
    private final SequenceGeneratorService sequenceGenerator;

    public ImageService(ImageMapper ImageMapper, ImageRepository ImageRepository,
            SequenceGeneratorService sequenceGenerator)
    {
        super();
        this.imageMapper = ImageMapper;
        this.imageRepository = ImageRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public ImageDTO getImageById(long id)
    {
        Optional<Image> image = this.imageRepository.findById(id);

        if (image.isPresent())
        {
            return this.imageMapper.toImageDTO(image.get());
        }
        return new ImageDTO();
    }

    public long addImage(MultipartFile file) throws IOException
    {
        Image image = new Image();
        image.setId(sequenceGenerator.generateSequence(Image.SEQUENCE_NAME));
        image.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        return imageRepository.save(image).getId();
    }

    public void deleteImageById(long id)
    {
        Optional<Image> image = this.imageRepository.findById(id);

        if (image.isPresent())
        {
            this.imageRepository.deleteById(id);
        }
    }
}
