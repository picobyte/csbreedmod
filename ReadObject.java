
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.security.ProtectionDomain;

public class ReadObject
{

    public ReadObject()
    {
    }

    public WorldState[] importFiles()
    {
        WorldState worlds[];
        FileInputStream fin;
        ObjectInputStream ois;
        File matchingFiles[];
        worlds = new WorldState[0];
        fin = null;
        ois = null;
        String path = Project.getProtectionDomain().getCodeSource().getLocation().getPath();
        String fileName = "";
        for(int i = path.length() - 1; i >= 0; i--)
            if(path.charAt(i) != '/')
                fileName = (new StringBuilder(String.valueOf(path.charAt(i)))).append(fileName).toString();
            else
                i = -1;

        path = path.substring(0, path.length() - fileName.length() - 1);
        try
        {
            path = URLDecoder.decode(path, "UTF-8");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        path = path.replaceAll("file:/", "");
        path = path.replaceAll((new StringBuilder(String.valueOf(File.separator))).append("u0020").toString(), (new StringBuilder(String.valueOf(File.separator))).append(" ").toString());
        File f = new File(path);
        matchingFiles = f.listFiles(new FilenameFilter() {

            public boolean accept(File dir, String name)
            {
                return name.endsWith("par");
            }

            final ReadObject this$0;

            
            {
                this$0 = ReadObject.this;
                super();
            }
        });
        try
        {
            WorldState newWorlds[] = new WorldState[matchingFiles.length];
            for(int i = 0; i < matchingFiles.length; i++)
            {
                fin = new FileInputStream(matchingFiles[i]);
                ois = new ObjectInputStream(fin);
                newWorlds[i] = (WorldState)ois.readObject();
            }

            worlds = newWorlds;
            break MISSING_BLOCK_LABEL_372;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        if(fin != null)
            try
            {
                fin.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        if(ois != null)
            try
            {
                ois.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        break MISSING_BLOCK_LABEL_408;
        Exception exception;
        exception;
        if(fin != null)
            try
            {
                fin.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        if(ois != null)
            try
            {
                ois.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        throw exception;
        if(fin != null)
            try
            {
                fin.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        if(ois != null)
            try
            {
                ois.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        return worlds;
    }

    public SaveData deserializeSaveData(String filename)
    {
        SaveData s;
        FileInputStream fin;
        ObjectInputStream ois;
        s = null;
        fin = null;
        ois = null;
        try
        {
            fin = new FileInputStream(filename);
            ois = new ObjectInputStream(fin);
            s = (SaveData)ois.readObject();
            break MISSING_BLOCK_LABEL_129;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        if(fin != null)
            try
            {
                fin.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        if(ois != null)
            try
            {
                ois.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        break MISSING_BLOCK_LABEL_167;
        Exception exception;
        exception;
        if(fin != null)
            try
            {
                fin.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        if(ois != null)
            try
            {
                ois.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        throw exception;
        if(fin != null)
            try
            {
                fin.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        if(ois != null)
            try
            {
                ois.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        return s;
    }
}
