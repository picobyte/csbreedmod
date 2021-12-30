
import java.awt.Color;
import java.io.Serializable;

public class SaveData
    implements Serializable
{

    public SaveData()
    {
        saves = new WorldState[0];
        names = new String[0];
        harem = new Forsaken[0];
        forsakenMade = 0;
        chosenMade = 0;
        sceneText = new String[48][0][0];
        sceneColor = new Color[48][0][0];
        sceneUnderline = new Boolean[48][0][0];
        currentText = new String[0];
        currentColor = new Color[0];
        currentUnderline = new Boolean[0];
        sceneButtons = new String[48][0];
        sceneSummaries = new String[48][0];
        sceneEmotions = new Project.Emotion[48][0][5];
        sceneFaces = new String[48][0][5];
        sceneSpecs = new Chosen.Species[48][0][5];
        sceneCivs = new Boolean[48][0][5];
        sceneFallen = new Boolean[48][0][5];
        sceneGenders = new Forsaken.Gender[48][0][5];
        customRoster = new Chosen[0];
    }

    public void organizeScenes(int scenesThisVersion)
    {
        newScene();
        String newSceneText[][][] = new String[scenesThisVersion][0][0];
        Color newSceneColor[][][] = new Color[scenesThisVersion][0][0];
        Boolean newSceneUnderline[][][] = new Boolean[scenesThisVersion][0][0];
        String newSceneButtons[][] = new String[scenesThisVersion][0];
        String newSceneSummaries[][] = new String[scenesThisVersion][0];
        Project.Emotion newSceneEmotions[][][] = new Project.Emotion[scenesThisVersion][0][5];
        String newSceneFaces[][][] = new String[scenesThisVersion][0][5];
        Chosen.Species newSceneSpecs[][][] = new Chosen.Species[scenesThisVersion][0][5];
        Boolean newSceneCivs[][][] = new Boolean[scenesThisVersion][0][5];
        Boolean newSceneFallen[][][] = new Boolean[scenesThisVersion][0][5];
        Forsaken.Gender newSceneGenders[][][] = new Forsaken.Gender[scenesThisVersion][0][5];
        if(sceneText != null)
        {
            for(int i = 0; i < sceneText.length; i++)
            {
                newSceneText[i] = sceneText[i];
                newSceneColor[i] = sceneColor[i];
                newSceneUnderline[i] = sceneUnderline[i];
                newSceneButtons[i] = sceneButtons[i];
                newSceneSummaries[i] = sceneSummaries[i];
                if(sceneEmotions != null)
                {
                    newSceneEmotions[i] = sceneEmotions[i];
                    newSceneFaces[i] = sceneFaces[i];
                    newSceneSpecs[i] = sceneSpecs[i];
                    newSceneCivs[i] = sceneCivs[i];
                    newSceneFallen[i] = sceneFallen[i];
                } else
                {
                    newSceneEmotions[i] = new Project.Emotion[sceneText[i].length][5];
                    newSceneFaces[i] = new String[sceneText[i].length][5];
                    newSceneSpecs[i] = new Chosen.Species[sceneText[i].length][5];
                    newSceneCivs[i] = new Boolean[sceneText[i].length][5];
                    newSceneFallen[i] = new Boolean[sceneText[i].length][5];
                }
                if(sceneGenders != null)
                    newSceneGenders[i] = sceneGenders[i];
                else
                    newSceneGenders[i] = new Forsaken.Gender[sceneText[i].length][5];
            }

        }
        sceneText = newSceneText;
        sceneColor = newSceneColor;
        sceneUnderline = newSceneUnderline;
        sceneButtons = newSceneButtons;
        sceneSummaries = newSceneSummaries;
        sceneEmotions = newSceneEmotions;
        sceneFaces = newSceneFaces;
        sceneSpecs = newSceneSpecs;
        sceneCivs = newSceneCivs;
        sceneFallen = newSceneFallen;
        sceneGenders = newSceneGenders;
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
            Project.Emotion newEmotions[][] = new Project.Emotion[sceneEmotions[type].length + 1][5];
            String newFaces[][] = new String[sceneFaces[type].length + 1][5];
            Chosen.Species newSpecs[][] = new Chosen.Species[sceneSpecs[type].length + 1][5];
            Boolean newCivs[][] = new Boolean[sceneCivs[type].length + 1][5];
            Boolean newFallen[][] = new Boolean[sceneFallen[type].length + 1][5];
            Forsaken.Gender newGenders[][] = new Forsaken.Gender[sceneGenders[type].length + 1][5];
            for(int i = 0; i < sceneText[type].length; i++)
            {
                newText[i] = sceneText[type][i];
                newColor[i] = sceneColor[type][i];
                newUnderline[i] = sceneUnderline[type][i];
                newButtons[i] = sceneButtons[type][i];
                newSummaries[i] = sceneSummaries[type][i];
                newEmotions[i] = sceneEmotions[type][i];
                newFaces[i] = sceneFaces[type][i];
                newSpecs[i] = sceneSpecs[type][i];
                newCivs[i] = sceneCivs[type][i];
                newFallen[i] = sceneFallen[type][i];
                newGenders[i] = sceneGenders[type][i];
            }

            newText[sceneText[type].length] = currentText;
            newColor[sceneColor[type].length] = currentColor;
            newUnderline[sceneUnderline[type].length] = currentUnderline;
            newButtons[sceneButtons[type].length] = button;
            newSummaries[sceneSummaries[type].length] = summary;
            newEmotions[sceneEmotions[type].length] = Project.displayedEmotions;
            newFaces[sceneFaces[type].length] = Project.displayedNames;
            newSpecs[sceneSpecs[type].length] = Project.displayedType;
            newCivs[sceneCivs[type].length] = Project.displayedCivilians;
            newFallen[sceneFallen[type].length] = Project.displayedFallen;
            newGenders[sceneGenders[type].length] = Project.displayedGender;
            sceneText[type] = newText;
            sceneColor[type] = newColor;
            sceneUnderline[type] = newUnderline;
            sceneButtons[type] = newButtons;
            sceneSummaries[type] = newSummaries;
            sceneEmotions[type] = newEmotions;
            sceneFaces[type] = newFaces;
            sceneSpecs[type] = newSpecs;
            sceneCivs[type] = newCivs;
            sceneFallen[type] = newFallen;
            sceneGenders[type] = newGenders;
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

    public int assignChosenID()
    {
        chosenMade++;
        return chosenMade;
    }

    public void fillIDs()
    {
        for(int i = 0; i < harem.length; i++)
            if(harem[i].forsakenID == 0)
                harem[i].forsakenID = assignID();

        for(int i = 0; i < saves.length; i++)
        {
            if(saves[i].campaign == null)
                saves[i].campaign = Boolean.valueOf(false);
            if(saves[i].campaign.booleanValue())
            {
                for(int j = 0; j < saves[i].conquered.length; j++)
                    if(saves[i].conquered[j].forsakenID == 0)
                        saves[i].conquered[j].forsakenID = assignID();

                for(int j = 0; j < saves[i].sacrificed.length; j++)
                    if(saves[i].sacrificed[j].forsakenID == 0)
                        saves[i].sacrificed[j].forsakenID = assignID();

            }
            for(int j = 0; j < 3; j++)
                if(saves[i].getCast()[j] != null && saves[i].getCast()[j].globalID == 0)
                    saves[i].getCast()[j].globalID = assignChosenID();

        }

    }

    public void checkIDs()
    {
        int highest = 0;
        int highestChosen = 0;
        for(int i = 0; i < harem.length; i++)
            if(harem[i].forsakenID > highest)
                highest = harem[i].forsakenID;

        for(int i = 0; i < saves.length; i++)
        {
            if(saves[i].campaign.booleanValue())
            {
                for(int j = 0; j < saves[i].conquered.length; j++)
                    if(saves[i].conquered[j].forsakenID > highest)
                        highest = saves[i].conquered[j].forsakenID;

                for(int j = 0; j < saves[i].sacrificed.length; j++)
                    if(saves[i].sacrificed[j].forsakenID > highest)
                        highest = saves[i].sacrificed[j].forsakenID;

            }
            for(int j = 0; j < 3; j++)
                if(saves[i].getCast()[j] != null && saves[i].getCast()[j].globalID > highestChosen)
                    highestChosen = saves[i].getCast()[j].globalID;

        }

        forsakenMade = highest;
        chosenMade = highestChosen;
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
    int chosenMade;
    String sceneText[][][];
    Color sceneColor[][][];
    Boolean sceneUnderline[][][];
    String currentText[];
    Color currentColor[];
    Boolean currentUnderline[];
    String sceneButtons[][];
    String sceneSummaries[][];
    Project.Emotion sceneEmotions[][][];
    String sceneFaces[][][];
    Chosen.Species sceneSpecs[][][];
    Boolean sceneCivs[][][];
    Boolean sceneFallen[][][];
    Forsaken.Gender sceneGenders[][][];
    Chosen customRoster[];
}
