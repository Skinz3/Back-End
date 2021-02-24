package fr.tezea.chantiers.domain.chantier;

public enum StatusIntervention {
    ESTIMATION("estimation"),COMMERCIAL("commercial"),PLANNING("planning");
    private final String type;

    private StatusIntervention(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return this.type;
    }

    public String getTypeLowerCase()
    {
        return this.type.toLowerCase();
    }
}
