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
package fr.tezea.chantiers.rest.api.chantier.impl;

import fr.tezea.chantiers.rest.api.chantier.api.ImageResourceV1;
import fr.tezea.chantiers.service.ImageService;
import fr.tezea.chantiers.service.dto.chantier.ImageDTO;
import java.io.IOException;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageResourceImplV1 implements ImageResourceV1
{
    private final ImageService imageService;

    public ImageResourceImplV1(ImageService imageService)
    {
        super();
        this.imageService = imageService;
    }

    @Override
    public ResponseEntity<ImageDTO> getImageById(@PathVariable("id") long id)
    {
        return ResponseEntity.ok(this.imageService.getImageById(id));
    }

    @Override
    @PostMapping("/photos/add")
    public ResponseEntity<URI> addImage(@RequestParam("image") MultipartFile image, Model model) throws IOException
    {
        URI location = URI.create(String.format("/get/%s", imageService.addImage(image)));
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> deleteImageById(@PathVariable("id") long id)
    {
        this.imageService.deleteImageById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
