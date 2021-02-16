package fr.tezea.chantiers.domain.user;

public enum RoleType {

	TELEPHONE("Telephone"), ADMINISTRATION("Administration"), DIRECTION("Direction"), ETATDESLIEUX("État des lieux"), LIVRAISON("Livraison");

    private final String type;

    private RoleType(String type)
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
