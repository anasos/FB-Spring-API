package encoder;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;


public class MD5PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        String encPass = "";
       try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(charSequence.toString().getBytes());
            byte[] b64 = Base64.encodeBase64(digest);
            encPass = new String(b64);
            encPass = encPass.replaceAll("=", "");
        }catch(Exception ex){
           // Logger.error("An exception trying to encode a password", ex);
        }
        return encPass;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encode(charSequence).equals(s);
    }

}
