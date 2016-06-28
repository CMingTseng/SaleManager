package tvcompany.salemanager.library;

import android.widget.Toast;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MtViet on 27/06/2016.
 */
public class ValidString {
    public boolean CheckSpecialCharacter(String str)
    {
        Pattern regex = Pattern.compile("[!@#$%^&*()_$&+/,:;=?|]");
        Matcher matcher = regex.matcher(str);
        if (matcher.find()){
            return false;
        }
        return true;
    }

    public String ReplaceToValidString(String str)
    {
        str = str.replaceAll("\\s+","");
        String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public boolean CheckValidLengthRegex(String str)
    {
        if(ReplaceToValidString(str).length() < 6)
        {
            return false;
        }
        return true;
    }

    public boolean CheckValidLength(String str)
    {
        if(str.length() < 6)
        {
            return false;
        }
        return true;
    }

}
