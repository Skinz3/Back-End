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
package fr.tezea.chantiers.rest.api.chantier.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.tezea.chantiers.service.MediaService;
import fr.tezea.chantiers.service.dto.chantier.MediaDTO;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = MediaResourceV1.class)
class MediaResourceV1Test
{
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MediaService chantierService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCallGetMediaByIdAndReturnOk() throws Exception
    {
        long id = 1;
        /* Pas de mock car pas de logique dans les DTO */
        MediaDTO mediaDTO = new MediaDTO();
        when(chantierService.getMediaById(id)).thenReturn(mediaDTO);
        this.mockMvc.perform(get("/api/v1/media/get/" + id)).andExpect(content().contentType("application/json"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(mediaDTO)));
    }

    @Test
    public void shouldCallGetAllMediaAndReturnOk() throws Exception
    {
        MediaDTO mediaDTO1 = new MediaDTO();
        MediaDTO mediaDTO2 = new MediaDTO();
        when(chantierService.getAllMedia()).thenReturn(List.of(mediaDTO1, mediaDTO2));
        this.mockMvc.perform(get("/api/v1/media/get")).andDo(print())
                .andExpect(content().contentType("application/json")).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(List.of(mediaDTO1, mediaDTO2))));
    }

    @Test
    public void shouldCallAddMediaMethodAndReturnOk() throws Exception
    {
        MediaDTO mediaDTO = new MediaDTO();
        when(chantierService.addMedia(any(MediaDTO.class))).thenReturn(1L);
        this.mockMvc
                .perform(post("/api/v1/media/add").content(objectMapper.writeValueAsString(mediaDTO))
                        .contentType("application/json"))
                .andDo(print()).andExpect(status().isCreated()).andExpect(header().string("location", "/get/1"));
    }

    @Test
    public void shouldCallUpdateMediaAndReturnOk() throws Exception
    {
        long id = 1;
        MediaDTO mediaDTO = new MediaDTO();
        MediaDTO expected = new MediaDTO();
        when(chantierService.updateMediaById(id, mediaDTO)).thenReturn(expected);
        this.mockMvc
                .perform(put("/api/v1/media/update/" + id).content(String.valueOf(id))
                        .content(objectMapper.writeValueAsString(mediaDTO)).contentType("application/json"))
                .andDo(print()).andExpect(content().contentType("application/json")).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expected)));
    }

    @Test
    public void shouldDeleteAndReturnOk() throws Exception
    {
        long id = 1;
        this.mockMvc.perform(delete("/api/v1/media/delete/" + id)).andDo(print()).andExpect(status().isOk());
    };
}
