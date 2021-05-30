
import java.io.Serializable;

public class SaveData
    implements Serializable
{

    public SaveData()
    {
        saves = new WorldState[0];
        names = new String[0];
        harem = null;
        forsakenMade = 0;
    }

    public int assignID()
    {
        forsakenMade++;
        return forsakenMade;
    }

    public WorldState[] getSaves()
    {
        return saves;
    }

    public String[] getNames()
    {
        return names;
    }

    public void newSave(WorldState w, String name)
    {
        WorldState newSaves[] = new WorldState[saves.length + 1];
        newSaves[0] = w;
        String newNames[] = new String[names.length + 1];
        newNames[0] = name;
        for(int i = 0; i < saves.length; i++)
        {
            newSaves[i + 1] = saves[i];
            newNames[i + 1] = names[i];
        }

        saves = newSaves;
        names = newNames;
    }

    public void endSave(WorldState w, String name)
    {
        WorldState newSaves[] = new WorldState[saves.length + 1];
        newSaves[saves.length] = w;
        w.save = this;
        String newNames[] = new String[names.length + 1];
        newNames[saves.length] = name;
        for(int i = 0; i < saves.length; i++)
        {
            newSaves[i] = saves[i];
            newNames[i] = names[i];
        }

        saves = newSaves;
        names = newNames;
    }

    public void overwriteSave(WorldState w)
    {
        saves[0] = w;
    }

    public void deleteSave(int file)
    {
        WorldState newSaves[] = new WorldState[saves.length - 1];
        String newNames[] = new String[names.length - 1];
        for(int i = 0; i < saves.length; i++)
            if(i < file)
            {
                newSaves[i] = saves[i];
                newNames[i] = names[i];
            } else
            if(i > file)
            {
                newSaves[i - 1] = saves[i];
                newNames[i - 1] = names[i];
            }

        saves = newSaves;
        names = newNames;
    }

    public void moveToFront(int file)
    {
        WorldState newSaves[] = new WorldState[saves.length];
        String newNames[] = new String[names.length];
        newSaves[0] = saves[file];
        newNames[0] = names[file];
        for(int i = 0; i < saves.length - 1; i++)
            if(i < file)
            {
                newSaves[i + 1] = saves[i];
                newNames[i + 1] = names[i];
            } else
            {
                newSaves[i + 1] = saves[i + 1];
                newNames[i + 1] = names[i + 1];
            }

        saves = newSaves;
        names = newNames;
    }

    private static final long serialVersionUID = 0xd05cc0c71818b918L;
    WorldState saves[];
    String names[];
    Forsaken harem[];
    int forsakenMade;
}
