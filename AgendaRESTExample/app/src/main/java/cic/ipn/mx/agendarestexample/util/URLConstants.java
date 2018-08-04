package cic.ipn.mx.agendarestexample.util;

public class URLConstants {

    private URLConstants() {
    }

    private static final String HOST = "https://baas.kinvey.com";

    /**
     * El valor de este contexto depende de mi colección en Kinvey,
     * Para uso de su proyecto, copien el contexto mostrado
     * en su portal.
     */

    public static final String AUTH_HEADER =
            "Basic a2lkX1N5RXhvdUdIWDo3YjczMTkwYTg2YjQ0MWYxYTc2OWM4NzY2NDlkNzk5Zg==";

    public static final String ALL_CONTACTS_URL =
            HOST + "/appdata/kid_SyExouGHX/Contacts";

    public static final String DELETE_CONTACT_URL =
            HOST + "/appdata/kid_SyExouGHX/Contacts/{CONTACT_ID}";

    public static final String NEW_CONTACT_URL =
            HOST + "/appdata/kid_SyExouGHX/Contacts";

    public static final String UPDATE_CONTACT_URL =
            HOST + "/appdata/kid_SyExouGHX/Contacts/{CONTACT_ID}";

}
