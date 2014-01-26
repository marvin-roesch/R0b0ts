package de.mineformers.robots.lib;

/**
 * R0b0ts
 * <p/>
 * Reference
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Reference
{

    public static final String MOD_ID = "r0b0ts";
    public static final String MOD_NAME = "R0b0ts";
    public static final String CHANNEL_NAME = MOD_ID;
    public static final String FINGERPRINT = "";
    public static final String RESOURCE_PATH = "robots";
    public static final String RESOURCE_PREFIX = RESOURCE_PATH + ":";
    public static final String DEPENDENCIES = "required-after:Forge@[9.11.1.964,);required-after:CoFHCore@[2.0.0.2,)";
    public static final String SERVER_PROXY_CLASS = "de.mineformers.robots.proxy.CommonProxy";
    public static final String CLIENT_PROXY_CLASS = "de.mineformers.robots.proxy.ClientProxy";

    public static final int ITEM_ID_SHIFT = 256;

}
