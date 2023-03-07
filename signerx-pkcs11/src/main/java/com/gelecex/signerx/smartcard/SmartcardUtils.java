package com.gelecex.signerx.smartcard;

import com.gelecex.signerx.common.EnumOsArch;
import com.gelecex.signerx.common.EnumOsName;
import com.gelecex.signerx.common.exception.SignerxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.smartcardio.CardTerminals;
import javax.smartcardio.TerminalFactory;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by obetron on 27.05.2022
 */
public class SmartcardUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(SmartcardUtils.class);

    private SmartcardUtils(){}

    public static EnumOsArch detectSystemArch() {
        String osArch = System.getProperty("os.arch");
        if(osArch.contains("64")) {
            return EnumOsArch.x64;
        }
        return EnumOsArch.x32;
    }

    public static String getSystemExtension() throws SignerxException {
        String osName = System.getProperty("os.name");
        if (osName.contains(EnumOsName.Windows.name())) {
            return ".dll";
        } else if (osName.contains(EnumOsName.Linux.name())) {
            return ".so";
        } else if (osName.contains(EnumOsName.Mac.name())) {
            return ".dylib";
        } else {
            throw new SignerxException("Bilinmeyen isletim sistemi!");
        }
    }

    public static void clearSmartcardCache() {
        try {
            Class<?> pcscterminal = null;
            pcscterminal = Class.forName("sun.security.smartcardio.PCSCTerminals");
            Field contextId = pcscterminal.getDeclaredField("contextId");
            contextId.setAccessible(true);

            if (contextId.getLong(pcscterminal) != 0L) {
                // First get a new context value
                Class<?> pcsc = Class.forName("sun.security.smartcardio.PCSC");
                Method SCardEstablishContext = pcsc.getDeclaredMethod(
                        "SCardEstablishContext",
                        new Class[]{Integer.TYPE}
                );
                SCardEstablishContext.setAccessible(true);

                Field SCARD_SCOPE_USER = pcsc.getDeclaredField("SCARD_SCOPE_USER");
                SCARD_SCOPE_USER.setAccessible(true);

                long newId = ((Long) SCardEstablishContext.invoke(pcsc,
                        new Object[]{SCARD_SCOPE_USER.getInt(pcsc)}
                ));
                contextId.setLong(pcscterminal, newId);
                // Then clear the terminals in cache
                TerminalFactory factory = TerminalFactory.getDefault();
                CardTerminals terminals = factory.terminals();
                Field fieldTerminals = pcscterminal.getDeclaredField("terminals");
                fieldTerminals.setAccessible(true);
                Class classMap = Class.forName("java.util.Map");
                Method clearMap = classMap.getDeclaredMethod("clear");

                clearMap.invoke(fieldTerminals.get(terminals));
            }
        } catch(ClassNotFoundException | NoSuchFieldException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e){
            LOGGER.error("Window isletim sistemi icin smartcard sinifi cache temizligi sirasinda hata meydana geldi!", e);
        }
    }

}
