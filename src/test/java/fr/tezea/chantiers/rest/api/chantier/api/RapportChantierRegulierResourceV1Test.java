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
import fr.tezea.chantiers.service.RapportChantierRegulierService;
import fr.tezea.chantiers.service.dto.chantier.RapportChantierRegulierDTO;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = RapportChantierRegulierRessourceV1.class)
class RapportChantierRegulierResourceV1Test
{
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RapportChantierRegulierService chantierService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCallGetRapportChantierRegulierByIdAndReturnOk() throws Exception
    {
        long id = 1;
        /* Pas de mock car pas de logique dans les DTO */
        RapportChantierRegulierDTO rapportChantierRegulierDTO = new RapportChantierRegulierDTO();
        when(chantierService.getRapportChantierRegulierById(id)).thenReturn(rapportChantierRegulierDTO);
        this.mockMvc.perform(get("/api/v1/rapportchantierregulier/get/" + id))
                .andExpect(content().contentType("application/json")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(rapportChantierRegulierDTO)));
    }

    @Test
    public void shouldCallGetAllRapportChantierRegulierAndReturnOk() throws Exception
    {
        RapportChantierRegulierDTO rapportChantierRegulierDTO1 = new RapportChantierRegulierDTO();
        RapportChantierRegulierDTO rapportChantierRegulierDTO2 = new RapportChantierRegulierDTO();
        when(chantierService.getAllRapportChantierRegulier())
                .thenReturn(List.of(rapportChantierRegulierDTO1, rapportChantierRegulierDTO2));
        this.mockMvc.perform(get("/api/v1/rapportchantierregulier/get")).andDo(print())
                .andExpect(content().contentType("application/json")).andExpect(status().isOk())
                .andExpect(content().string(objectMapper
                        .writeValueAsString(List.of(rapportChantierRegulierDTO1, rapportChantierRegulierDTO2))));
    }

    @Test
    public void shouldCallAddRapportChantierRegulierMethodAndReturnOk() throws Exception
    {
        RapportChantierRegulierDTO rapportChantierRegulierDTO = new RapportChantierRegulierDTO();
        when(chantierService.addRapportChantierRegulier(any(RapportChantierRegulierDTO.class))).thenReturn(1L);
        this.mockMvc.perform(post("/api/v1/rapportchantierregulier/add")
                .content(objectMapper.writeValueAsString(rapportChantierRegulierDTO)).contentType("application/json"))
                .andDo(print()).andExpect(status().isCreated()).andExpect(header().string("location", "/get/1"));
    }

    @Test
    public void shouldCallUpdateRapportChantierRegulierAndReturnOk() throws Exception
    {
        long id = 1;
        RapportChantierRegulierDTO rapportChantierRegulierDTO = new RapportChantierRegulierDTO();
        RapportChantierRegulierDTO expected = new RapportChantierRegulierDTO();
        when(chantierService.updateRapportChantierRegulierById(id, rapportChantierRegulierDTO)).thenReturn(expected);
        this.mockMvc
                .perform(put("/api/v1/rapportchantierregulier/update/" + id).content(String.valueOf(id))
                        .content(objectMapper.writeValueAsString(rapportChantierRegulierDTO))
                        .contentType("application/json"))
                .andDo(print()).andExpect(content().contentType("application/json")).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expected)));
    }

    @Test
    public void shouldDeleteAndReturnOk() throws Exception
    {
        long id = 1;
        this.mockMvc.perform(delete("/api/v1/rapportchantierregulier/delete/" + id)).andDo(print())
                .andExpect(status().isOk());
    };
}
