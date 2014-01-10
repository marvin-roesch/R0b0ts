package de.mineformers.robots;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import de.mineformers.robots.proxy.CommonProxy;

import java.io.File;

/**
 * R0b0ts
 * <p/>
 * R0b0ts
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Mod(modid = R0b0ts.MOD_ID,
        name = R0b0ts.MOD_NAME,
        dependencies = R0b0ts.DEPENDENCIES,
        certificateFingerprint = R0b0ts.FINGERPRINT)
public class R0b0ts {

    public static final String MOD_ID = "r0b0ts";
    public static final String MOD_NAME = "R0b0ts";
    public static final String CHANNEL_NAME = MOD_ID;
    public static final String FINGERPRINT = "";
    public static final String RESOURCE_PATH = "robots";
    public static final String DEPENDENCIES = "required-after:Forge@[9.11.1.964,)";
    public static final String SERVER_PROXY_CLASS = "de.mineformers.robots.proxy.CommonProxy";
    public static final String CLIENT_PROXY_CLASS = "de.mineformers.robots.proxy.ClientProxy";

    @Mod.Instance(R0b0ts.MOD_ID)
    public static R0b0ts instance;

    @SidedProxy(clientSide = R0b0ts.CLIENT_PROXY_CLASS,
            serverSide = R0b0ts.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

}
