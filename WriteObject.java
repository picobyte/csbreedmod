
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.security.ProtectionDomain;

public class WriteObject
{

    public WriteObject()
    {
    }

    public void serializeSaveData(SaveData s)
    {
        FileOutputStream fout;
        ObjectOutputStream oos;
        fout = null;
        oos = null;
        try
        {
            String path = Project.getProtectionDomain().getCodeSource().getLocation().getPath();
            String fileName = "";
            for(int i = path.length() - 1; i >= 0; i--)
                if(path.charAt(i) != '/')
                    fileName = (new StringBuilder(String.valueOf(path.charAt(i)))).append(fileName).toString();
                else
                    i = -1;

            path = path.substring(0, path.length() - fileName.length() - 1);
            path = URLDecoder.decode(path, "UTF-8");
            path = path.replaceAll("file:/", "");
            path = path.replaceAll("\\u0020", "\\ ");
            fout = new FileOutputStream((new StringBuilder(String.valueOf(path))).append("\\saves.sav").toString());
            oos = new ObjectOutputStream(fout);
            oos.writeObject(s);
            break MISSING_BLOCK_LABEL_273;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        if(fout != null)
            try
            {
                fout.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        if(oos != null)
            try
            {
                oos.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        break MISSING_BLOCK_LABEL_309;
        Exception exception;
        exception;
        if(fout != null)
            try
            {
                fout.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        if(oos != null)
            try
            {
                oos.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        throw exception;
        if(fout != null)
            try
            {
                fout.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        if(oos != null)
            try
            {
                oos.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
    }
}
