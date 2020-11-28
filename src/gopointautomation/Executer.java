package gopointautomation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

public class Executer
{

    public String TOTPCode(String secretKey)
    {
        String normalizedBase32Key = secretKey.replace(" ", "").toUpperCase();
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(normalizedBase32Key);
        String hexKey = Hex.encodeHexString(bytes);
        long time = (System.currentTimeMillis() / 1000) / 30;
        String hexTime = Long.toHexString(time);
        return TOTP.generateTOTP(hexKey, hexTime, "6");
    }

    public String ExecuteCommand(String command)
    {
        try
        {
            Process p = Runtime.getRuntime().exec(command);

            p.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            return reader.readLine();
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
    }

    public String RandomServiceGoPay()
    {
        ArrayList<String> list = new ArrayList<String>();

        list.add("GO_BLUE_BIRD");
        list.add("GO_CAR_XTRA");
        list.add("GO-DEALS");
        list.add("GO-BILLS");
        list.add("GO_CAR");
        list.add("GO-FOOD");
        list.add("GO_PULSA");
        list.add("GO-RIDE");
        list.add("GO-TIX");
        list.add("GO-SHOP");
        list.add("GO_MASSAGE");

        Random r = new Random();
        return list.get(r.nextInt(list.size()));     
    }
    
    public String RandomServiceCash()
    {
        ArrayList<String> list = new ArrayList<String>();

        list.add("GO_BLUE_BIRD");
        list.add("GO_CAR_XTRA");
        list.add("GO_CAR");
        list.add("GO-FOOD");
        list.add("GO-RIDE");
        list.add("GO-SHOP");

        Random r = new Random();
        return list.get(r.nextInt(list.size()));     
    }
    
    public String RandomPaymentType()
    {
        ArrayList<String> list = new ArrayList<String>();

        list.add("GOPAY");
//        list.add("CASH");

        Random r = new Random();
        return list.get(r.nextInt(list.size()));     
    }
}
