
import java.io.*;

public class ReadObject
{

    public ReadObject()
    {
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
