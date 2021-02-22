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
package fr.tezea.chantiers;

import static org.assertj.core.api.Assertions.assertThat;

import fr.tezea.chantiers.rest.api.chantier.api.ChantierResourceV1;
import fr.tezea.chantiers.rest.api.client.api.ClientResourceV1;
import fr.tezea.chantiers.rest.api.school.api.StudentResourceV1;
import fr.tezea.chantiers.rest.api.site.api.SiteResourceV1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChantiersApplicationTests
{
    @Autowired
    private ChantierResourceV1 chantierResourceV1;
    @Autowired
    private ClientResourceV1 clientResourceV1;
    @Autowired
    private StudentResourceV1 studentResourceV1;
    @Autowired
    private SiteResourceV1 siteResourceV1;

    @Test
    void contextLoads()
    {
        assertThat(chantierResourceV1).isNotNull();
        assertThat(clientResourceV1).isNotNull();
        assertThat(studentResourceV1).isNotNull();
        assertThat(siteResourceV1).isNotNull();
    }
}
