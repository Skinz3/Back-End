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

import fr.tezea.chantiers.domain.client.Client;
import fr.tezea.chantiers.repository.client.ClientRepository;
import fr.tezea.chantiers.service.dto.client.ClientDTO;
import fr.tezea.chantiers.service.mapper.client.ClientMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ClientService
{
    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;
    private final SequenceGeneratorService sequenceGenerator;

    public ClientService(ClientMapper clientMapper, ClientRepository clientRepository,
            SequenceGeneratorService sequenceGenerator)
    {
        super();
        this.clientMapper = clientMapper;
        this.clientRepository = clientRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public ClientDTO getClientById(long id)
    {
        Optional<Client> client = this.clientRepository.findById(id);

        if (client.isPresent())
        {
            return this.clientMapper.toClientDTO(client.get());
        }
        return new ClientDTO();
    }

    public List<ClientDTO> getAllClient()
    {
        List<Client> client = this.clientRepository.findAll();

        if (!client.isEmpty())
        {
            return this.clientMapper.toClientDTO(client);
        }
        return new ArrayList<>();
    }

    public long addClient(ClientDTO clientDTO)
    {
        Client client = this.clientMapper.toClient(clientDTO);
        client.setId(sequenceGenerator.generateSequence(Client.SEQUENCE_NAME));
        return this.clientRepository.save(client).getId();
    }

    public ClientDTO updateClientById(long id, ClientDTO clientDTO)
    {
        Optional<Client> client = this.clientRepository.findById(id);

        if (client.isPresent())
        {
            return this.clientMapper
                    .toClientDTO(clientRepository.save(this.clientMapper.updateClientFromDTO(clientDTO, client.get())));
        }
        return new ClientDTO();
    }

    public void deleteClientById(long id)
    {
        Optional<Client> client = this.clientRepository.findById(id);

        if (client.isPresent())
        {
            this.clientRepository.deleteById(id);
        }
    }
}
