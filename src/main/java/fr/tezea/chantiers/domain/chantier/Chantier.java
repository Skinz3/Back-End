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
package fr.tezea.chantiers.domain.chantier;

import fr.tezea.chantiers.domain.client.Client;
import fr.tezea.chantiers.domain.site.Site;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Chantier
{
    @Transient
    public static final String SEQUENCE_NAME = "chantier_sequence";
    @Id
    private long id;
    @DBRef
    private Site site;
    @DBRef
    private Client client;
    @DBRef
    private Set<Probleme> problemes;
    @DBRef
    private Set<Media> medias;
    private String adresse;
    private Set<String> ouvriers;
    private String materiel;
    private Date dateDebutTheorique;
    private Date dateFinTheorique;
    private int estimationTemps;
    private String telephone;
    private StatusType statusChantier;
    private String nomChantier;
    private String informationsInterne;
    private String description;
    private Set<JourSemaineType> joursRegularite;
    private Date dateDebutRegularite;
    private Date dateFinRegularite;
    private boolean regularite;
    private Date dateDebutEffectif;
    private Date dateFinEffectif;
    private boolean conducteurPresent;
    @DBRef
    private Set<RapportChantierRegulier> rapportsRegulier;
    private StatusIntervention statusIntervention;
    private int nbOuvrier;
    private String volume;
    private String poid;
    private String accessibilite;

    public Chantier()
    {
        this.problemes = new HashSet<>();
        this.medias = new HashSet<>();
        this.ouvriers = new HashSet<>();
        this.rapportsRegulier = new HashSet<>();
        this.dateDebutTheorique = new Date(System.currentTimeMillis());
        this.dateFinTheorique = new Date(System.currentTimeMillis());
        this.dateDebutEffectif = new Date(System.currentTimeMillis());
        this.dateFinEffectif = new Date(System.currentTimeMillis());
    }
}
