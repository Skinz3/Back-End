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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.tezea.chantiers.service.ChantierService;
import fr.tezea.chantiers.service.dto.chantier.ChantierDTO;
import fr.tezea.chantiers.service.dto.chantier.ChantierGetDTO;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ChantierResourceV1.class)
class ChantierResourceV1Test
{
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ChantierService chantierService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCallGetChantierByIdAndReturnOk() throws Exception
    {
        long id = 1;
        /* Pas de mock car pas de logique dans les DTO */
        ChantierGetDTO chantierGetDTO = new ChantierGetDTO();
        when(chantierService.getChantierById(id)).thenReturn(chantierGetDTO);
        this.mockMvc.perform(get("/api/v1/chantier/get/" + id)).andExpect(content().contentType("application/json"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(chantierGetDTO)));
    }

    @Test
    public void shouldCallGetAllChantierAndReturnOk() throws Exception
    {
        ChantierGetDTO chantierGetDTO1 = new ChantierGetDTO();
        ChantierGetDTO chantierGetDTO2 = new ChantierGetDTO();
        when(chantierService.getAllChantier()).thenReturn(List.of(chantierGetDTO1, chantierGetDTO2));
        this.mockMvc.perform(get("/api/v1/chantier/get")).andDo(print())
                .andExpect(content().contentType("application/json")).andExpect(status().isOk()).andExpect(
                        content().string(objectMapper.writeValueAsString(List.of(chantierGetDTO1, chantierGetDTO2))));
    }

    @Test
    public void shouldCallAddMethodAndReturnOk() throws Exception
    {
        ChantierDTO chantierDTO = new ChantierDTO();
        when(chantierService.addChantier(any(ChantierDTO.class))).thenReturn(1L);
        this.mockMvc
                .perform(post("/api/v1/chantier/add").content(objectMapper.writeValueAsString(chantierDTO))
                        .contentType("application/json"))
                .andDo(print()).andExpect(status().isCreated()).andExpect(header().string("location", "/get/1"));
    }

    @Test
    public void shouldCallUpdateAndReturnOk() throws Exception
    {
        long id = 1;
        ChantierDTO chantierDTO = new ChantierDTO();
        ChantierDTO expected = new ChantierDTO();
        when(chantierService.updateChantierById(id, chantierDTO)).thenReturn(expected);
        this.mockMvc
                .perform(put("/api/v1/chantier/update/" + id).content(String.valueOf(id))
                        .content(objectMapper.writeValueAsString(chantierDTO)).contentType("application/json"))
                .andDo(print()).andExpect(content().contentType("application/json")).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expected)));
    }

    @Test
    public void shouldDeleteAndReturnOk() throws Exception
    {
        long id = 1;
        this.mockMvc.perform(delete("/api/v1/chantier/delete/" + id)).andDo(print()).andExpect(status().isOk());
    };
}
