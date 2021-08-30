
import java.awt.Color;
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
        sceneText = new String[42][0][0];
        sceneColor = new Color[42][0][0];
        sceneUnderline = new Boolean[42][0][0];
        currentText = new String[0];
        currentColor = new Color[0];
        currentUnderline = new Boolean[0];
        sceneButtons = new String[42][0];
        sceneSummaries = new String[42][0];
    }

    public void organizeScenes(int scenesThisVersion)
    {
        newScene();
        String newSceneText[][][] = new String[scenesThisVersion][0][0];
        Color newSceneColor[][][] = new Color[scenesThisVersion][0][0];
        Boolean newSceneUnderline[][][] = new Boolean[scenesThisVersion][0][0];
        String newSceneButtons[][] = new String[scenesThisVersion][0];
        String newSceneSummaries[][] = new String[scenesThisVersion][0];
        if(sceneText != null)
        {
            for(int i = 0; i < sceneText.length; i++)
            {
                newSceneText[i] = sceneText[i];
                newSceneColor[i] = sceneColor[i];
                newSceneUnderline[i] = sceneUnderline[i];
                newSceneButtons[i] = sceneButtons[i];
                newSceneSummaries[i] = sceneSummaries[i];
            }

        }
        sceneText = newSceneText;
        sceneColor = newSceneColor;
        sceneUnderline = newSceneUnderline;
        sceneButtons = newSceneButtons;
        sceneSummaries = newSceneSummaries;
    }

    public void newScene()
    {
        currentText = new String[0];
        currentColor = new Color[0];
        currentUnderline = new Boolean[0];
    }

    public void addLine(String t, Color c, Boolean u)
    {
        String newText[] = new String[currentText.length + 1];
        Color newColor[] = new Color[currentColor.length + 1];
        Boolean newUnderline[] = new Boolean[currentUnderline.length + 1];
        for(int i = 0; i < currentText.length; i++)
        {
            newText[i] = currentText[i];
            newColor[i] = currentColor[i];
            newUnderline[i] = currentUnderline[i];
        }

        newText[currentText.length] = t;
        newColor[currentColor.length] = c;
        newUnderline[currentUnderline.length] = u;
        currentText = newText;
        currentColor = newColor;
        currentUnderline = newUnderline;
    }

    public void saveScene(int type, String button, String summary)
    {
        Boolean unique = Boolean.valueOf(true);
        for(int i = 0; i < sceneText[type].length && unique.booleanValue(); i++)
        {
            Boolean difference = Boolean.valueOf(false);
            for(int j = 0; j < sceneText[type][i].length && !difference.booleanValue(); j++)
                if(sceneText[type][i].length != currentText.length)
                    difference = Boolean.valueOf(true);
                else
                if(!sceneText[type][i][j].contentEquals(currentText[j]))
                    difference = Boolean.valueOf(true);

            unique = difference;
        }

        if(unique.booleanValue())
        {
            String newText[][] = new String[sceneText[type].length + 1][0];
            Color newColor[][] = new Color[sceneColor[type].length + 1][0];
            Boolean newUnderline[][] = new Boolean[sceneUnderline[type].length + 1][0];
            String newButtons[] = new String[sceneButtons[type].length + 1];
            String newSummaries[] = new String[sceneSummaries[type].length + 1];
            for(int i = 0; i < sceneText[type].length; i++)
            {
                newText[i] = sceneText[type][i];
                newColor[i] = sceneColor[type][i];
                newUnderline[i] = sceneUnderline[type][i];
                newButtons[i] = sceneButtons[type][i];
                newSummaries[i] = sceneSummaries[type][i];
            }

            newText[sceneText[type].length] = currentText;
            newColor[sceneColor[type].length] = currentColor;
            newUnderline[sceneUnderline[type].length] = currentUnderline;
            newButtons[sceneButtons[type].length] = button;
            newSummaries[sceneSummaries[type].length] = summary;
            sceneText[type] = newText;
            sceneColor[type] = newColor;
            sceneUnderline[type] = newUnderline;
            sceneButtons[type] = newButtons;
            sceneSummaries[type] = newSummaries;
            WriteObject wobj = new WriteObject();
            wobj.serializeSaveData(this);
        }
        newScene();
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
    String sceneText[][][];
    Color sceneColor[][][];
    Boolean sceneUnderline[][][];
    String currentText[];
    Color currentColor[];
    Boolean currentUnderline[];
    String sceneButtons[][];
    String sceneSummaries[][];
}
