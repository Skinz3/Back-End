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
import fr.tezea.chantiers.service.DemandeDeChantierService;
import fr.tezea.chantiers.service.dto.chantier.DemandeDeChantierDTO;
import fr.tezea.chantiers.service.dto.chantier.DemandeDeChantierGetDTO;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = DemandeDeChantierResourceV1.class)
class DemandeDeChantierResourceV1Test
{
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DemandeDeChantierService chantierService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCallGetDemandeDeChantierByIdAndReturnOk() throws Exception
    {
        long id = 1;
        /* Pas de mock car pas de logique dans les DTO */
        DemandeDeChantierGetDTO demandeDeChantierGetDTO = new DemandeDeChantierGetDTO();
        when(chantierService.getDemandeDeChantierById(id)).thenReturn(demandeDeChantierGetDTO);
        this.mockMvc.perform(get("/api/v1/demandedechantier/get/" + id))
                .andExpect(content().contentType("application/json")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(demandeDeChantierGetDTO)));
    }

    @Test
    public void shouldCallGetAllDemandeDeChantierAndReturnOk() throws Exception
    {
        DemandeDeChantierGetDTO demandeDeChantierGetDTO1 = new DemandeDeChantierGetDTO();
        DemandeDeChantierGetDTO demandeDeChantierGetDTO2 = new DemandeDeChantierGetDTO();
        when(chantierService.getAllDemandeDeChantier())
                .thenReturn(List.of(demandeDeChantierGetDTO1, demandeDeChantierGetDTO2));
        this.mockMvc.perform(get("/api/v1/demandedechantier/get")).andDo(print())
                .andExpect(content().contentType("application/json")).andExpect(status().isOk())
                .andExpect(content().string(
                        objectMapper.writeValueAsString(List.of(demandeDeChantierGetDTO1, demandeDeChantierGetDTO2))));
    }

    @Test
    public void shouldCallAddDemandeDeChantierMethodAndReturnOk() throws Exception
    {
        DemandeDeChantierDTO demandeDeChantierDTO = new DemandeDeChantierDTO();
        when(chantierService.addDemandeDeChantier(any(DemandeDeChantierDTO.class))).thenReturn(1L);
        this.mockMvc
                .perform(post("/api/v1/demandedechantier/add")
                        .content(objectMapper.writeValueAsString(demandeDeChantierDTO)).contentType("application/json"))
                .andDo(print()).andExpect(status().isCreated()).andExpect(header().string("location", "/get/1"));
    }

    @Test
    public void shouldCallUpdateDemandeDeChantierAndReturnOk() throws Exception
    {
        long id = 1;
        DemandeDeChantierDTO demandeDeChantierDTO = new DemandeDeChantierDTO();
        DemandeDeChantierDTO expected = new DemandeDeChantierDTO();
        when(chantierService.updateDemandeDeChantierById(id, demandeDeChantierDTO)).thenReturn(expected);
        this.mockMvc
                .perform(put("/api/v1/demandedechantier/update/" + id).content(String.valueOf(id))
                        .content(objectMapper.writeValueAsString(demandeDeChantierDTO)).contentType("application/json"))
                .andDo(print()).andExpect(content().contentType("application/json")).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expected)));
    }

    @Test
    public void shouldDeleteAndReturnOk() throws Exception
    {
        long id = 1;
        this.mockMvc.perform(delete("/api/v1/demandedechantier/delete/" + id)).andDo(print())
                .andExpect(status().isOk());
    };
}
