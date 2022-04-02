
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Document;

public class Project extends JFrame
{
    static final long million = 1_000_000L;
    static final long billion = 1_000_000_000L;
    static final long trillion = 1_000_000_000_000L;
    static final long quadrillion = 1_000_000_000_000_000L;
    static final long quintillion = 1_000_000_000_000_000_000L;
    static final int scenesThisVersion = 49;
    static final int vignettesThisVersion = 16;
    public static JFrame window = new JFrame("Project");
    public static Container nestedcp = new Container();
    public static Container portraits;
    public static JTextPane textPane;
    public static JScrollPane scrollPane;
    public static JScrollPane portraitPane;
    public static Emotion displayedEmotions[] = new Emotion[5];
    public static String displayedNames[] = new String[5];
    public static Chosen.Species displayedType[] = new Chosen.Species[5];
    public static Boolean displayedCivilians[] = {
        false, false, false, false, false
    };
    public static Boolean displayedFallen[] = {
        false, false, false, false, false
    };
    public static Forsaken.Gender displayedGender[];
    public static Activity Talk;
    public static Activity TweakClit;
    public static Activity ClitTweaked;
    public static Activity SpreadLegs;
    public static Activity Praise;
    public static Activity Insult;
    public static Activity PushDown;
    public static Activity PullDown;
    public static Activity Escape;
    public static Activity StopActing;
    public static Activity TieUp;
    public static Activity BeTied;
    public static Activity StrokeCock;
    public static Activity CockStroked;
    public static Activity Lubricate;
    public static Activity BeLubricated;
    public static Activity VaginalPenetrate;
    public static Activity PenetratedVaginally;
    public static Activity AnalPenetrate;
    public static Activity PenetratedAnally;
    public static Activity StripOther;
    public static Activity Stripped;
    public static Activity allActivities[];

    static 
    {
        portraits = new Container();
        textPane = new JTextPane();
        scrollPane = new JScrollPane(textPane);
        portraitPane = new JScrollPane(portraits);
        displayedGender = (new Forsaken.Gender[] {
            Forsaken.Gender.FEMALE, Forsaken.Gender.FEMALE, Forsaken.Gender.FEMALE, Forsaken.Gender.FEMALE, Forsaken.Gender.FEMALE
        });
        Talk = new Activity();
        TweakClit = new Activity();
        ClitTweaked = new Activity();
        SpreadLegs = new Activity();
        Praise = new Activity();
        Insult = new Activity();
        PushDown = new Activity();
        PullDown = new Activity();
        Escape = new Activity();
        StopActing = new Activity();
        TieUp = new Activity();
        BeTied = new Activity();
        StrokeCock = new Activity();
        CockStroked = new Activity();
        Lubricate = new Activity();
        BeLubricated = new Activity();
        VaginalPenetrate = new Activity();
        PenetratedVaginally = new Activity();
        AnalPenetrate = new Activity();
        PenetratedAnally = new Activity();
        StripOther = new Activity();
        Stripped = new Activity();
        allActivities = (new Activity[] {
            Talk, TweakClit, ClitTweaked, SpreadLegs, Praise, Insult, PushDown, PullDown, Escape, StopActing, 
            TieUp, BeTied, StrokeCock, CockStroked, Lubricate, BeLubricated, VaginalPenetrate, PenetratedVaginally, AnalPenetrate, PenetratedAnally, 
            StripOther, Stripped
        });
    }
    public enum Emotion {
        ANGER, FEAR, FOCUS, JOY, LEWD, NEUTRAL, SHAME, STRUGGLE, SWOON
    }

    public Project()
    {
        Container cp = window.getContentPane();
        window.setLayout(new BoxLayout(cp, 1));
        nestedcp.setLayout(new GridBagLayout());
        cp.add(nestedcp);
        portraits.setLayout(new BoxLayout(portraits, 1));
        textPane.setEditable(false);
        scrollPane.setVerticalScrollBarPolicy(22);
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1.0D;
        c.weighty = 1.0D;
        c.fill = 1;
        nestedcp.add(scrollPane, c);
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.setMaximumSize(new Dimension(5000, 40));
        cp.add(controlPanel);
        window.setDefaultCloseOperation(3);
        window.setTitle("Corrupted Saviors");
        window.setSize(new Dimension(1300, 800));
        window.setVisible(true);
        ToolTipManager.sharedInstance().setDismissDelay(0x7fffffff);
        WorldState ThisState = new WorldState();
        ThisState.toggleColors(textPane);
        String path = Project.class.getProtectionDomain().getCodeSource().getLocation().getPath();
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
        path = path.replaceAll(File.separator + "u0020", File.separator + " ");
        File saveLocation = new File(path + File.separator + "saves.sav");
        SaveData saves = null;
        if(saveLocation.exists())
        {
            ReadObject robj = new ReadObject();
            saves = robj.deserializeSaveData(path + File.separator + "saves.sav");
        } else
        {
            saves = new SaveData();
        }
        SaveData saveFile = saves;
        if(saveFile.getSaves().length > 0)
        {
            ThisState.copySettings(textPane, saveFile.getSaves()[0]);
            ThisState.copyToggles(saveFile.getSaves()[0]);
            ThisState.setGenders(saveFile.getSaves()[0].getGenderBalance());
            if(!ThisState.hardMode)
            {
                ThisState.eventOffset = 0;
                ThisState.clampPercent = 100;
                ThisState.clampStart = 11;
                ThisState.downtimeMultiplier = 100;
                ThisState.types = new Chosen.Species[3];
            }
        }
        if(saves.harem == null)
            saves.harem = new Forsaken[0];
        for(int i = 0; i < saves.harem.length; i++)
        {
            if(saves.harem[i].others.length > saves.harem[i].forsakenRelations.length)
            {
                Forsaken newOthers[] = new Forsaken[saves.harem[i].forsakenRelations.length];
                for(int j = 0; j < newOthers.length; j++)
                    newOthers[j] = saves.harem[i].others[j];

                saves.harem[i].others = newOthers;
            }
            if(saves.harem[i].others.length > saves.harem[i].troublemaker.length)
            {
                Forsaken newOthers[] = new Forsaken[saves.harem[i].troublemaker.length];
                for(int j = 0; j < newOthers.length; j++)
                    newOthers[j] = saves.harem[i].others[j];

                saves.harem[i].others = newOthers;
            }
        }

        saves.fillIDs();
        if(saves.harem != null && saves.harem.length > 0)
        {
            if(saves.harem[0].demonLord == null)
            {
                for(int i = 0; i < saves.harem.length; i++)
                {
                    saves.harem[i].rememberedDemonLordBody = new Body();
                    saves.harem[i].pickEpithet();
                    saves.harem[i].titled = false;
                }

            }
            if(saves.harem[0].hateExp == 0L)
            {
                for(int i = 0; i < saves.harem.length; i++)
                {
                    saves.harem[i].hateExp = 20000L;
                    saves.harem[i].pleaExp = 20000L;
                    saves.harem[i].injuExp = 20000L;
                    saves.harem[i].expoExp = 20000L;
                    saves.harem[i].chooseCombatStyle();
                    saves.harem[i].motivation = 1000;
                    saves.harem[i].stamina = 1000;
                    if(saves.harem[i].innocence > 66)
                    {
                        saves.harem[i].textColor = new Color(255, 0, 150);
                        saves.harem[i].darkColor = new Color(255, 0, 150);
                    } else
                    if(saves.harem[i].innocence > 33)
                    {
                        saves.harem[i].textColor = new Color(120, 50, 180);
                        saves.harem[i].darkColor = new Color(150, 100, 200);
                    } else
                    {
                        saves.harem[i].textColor = new Color(200, 100, 100);
                        saves.harem[i].darkColor = new Color(255, 130, 220);
                    }
                    saves.harem[i].others = null;
                }

                WriteObject wobj = new WriteObject();
                wobj.serializeSaveData(saves);
            }
            for(int i = 0; i < saves.harem.length; i++)
            {
                if(saves.harem[i].forsakenID == 0)
                {
                    saves.harem[i].forsakenID = saves.assignID();
                    WriteObject wobj = new WriteObject();
                    wobj.serializeSaveData(saves);
                }
                if(saves.harem[i].forsakenRelations == null && saves.harem[i].chosenRelations == null)
                {
                    saves.harem[i].otherChosen = (new Chosen[] {
                        saves.harem[i].firstFormerPartner, saves.harem[i].secondFormerPartner
                    });
                    saves.harem[i].chosenRelations = (new Forsaken.Relationship[] {
                        Forsaken.Relationship.PARTNER, Forsaken.Relationship.PARTNER
                    });
                    if(saves.harem[i].others != null)
                    {
                        saves.harem[i].forsakenRelations = new Forsaken.Relationship[saves.harem[i].others.length];
                        for(int j = 0; j < saves.harem[i].others.length; j++)
                            if(saves.harem[i].others[j].equals(saves.harem[i].firstPartner) || saves.harem[i].others[j].equals(saves.harem[i].secondPartner))
                                saves.harem[i].forsakenRelations[j] = Forsaken.Relationship.PARTNER;

                    } else
                    {
                        saves.harem[i].others = new Forsaken[0];
                        saves.harem[i].forsakenRelations = new Forsaken.Relationship[0];
                        saves.harem[i].troublemaker = new int[0];
                    }
                    Forsaken checkedForsaken[] = saves.harem;
                    for(int j = 0; j < checkedForsaken.length; j++)
                        if(checkedForsaken[j].equals(saves.harem[i].firstPartner) || checkedForsaken[j].equals(saves.harem[i].secondPartner))
                        {
                            Boolean alreadyThere = false;
                            for(int k = 0; k < saves.harem[i].others.length; k++)
                                if(saves.harem[i].others[k].equals(checkedForsaken[j]))
                                    alreadyThere = true;

                            if(!alreadyThere)
                            {
                                Forsaken newOthers[] = new Forsaken[saves.harem[i].others.length + 1];
                                Forsaken.Relationship newRelationships[] = new Forsaken.Relationship[saves.harem[i].forsakenRelations.length + 1];
                                int newTroubles[] = new int[saves.harem[i].troublemaker.length + 1];
                                for(int k = 0; k < saves.harem[i].others.length; k++)
                                {
                                    newOthers[k] = saves.harem[i].others[k];
                                    newRelationships[k] = saves.harem[i].forsakenRelations[k];
                                    newTroubles[k] = saves.harem[i].troublemaker[k];
                                }

                                newOthers[saves.harem[i].others.length] = checkedForsaken[j];
                                newRelationships[saves.harem[i].forsakenRelations.length] = Forsaken.Relationship.PARTNER;
                                saves.harem[i].others = newOthers;
                                saves.harem[i].forsakenRelations = newRelationships;
                                saves.harem[i].troublemaker = newTroubles;
                            }
                        }

                }
                saves.harem[i].save = saves;
            }

        }
        if(saves.customRoster == null)
            saves.customRoster = new Chosen[0];
        if(saves.sceneText == null)
            saves.organizeScenes(49);
        else
        if(saves.sceneText.length < 49)
            saves.organizeScenes(49);
        if(saves.harem == null)
        {
            saves.harem = new Forsaken[0];
            WriteObject wobj = new WriteObject();
            wobj.serializeSaveData(saves);
        }
        saves.currentText = new String[0];
        saves.currentColor = new Color[0];
        saves.currentUnderline = new Boolean[0];
        ThisState.save = saves;
        IntroOne(textPane, controlPanel, window, ThisState);
    }

    public static String getFilePath()
    {
        String result = "";
        result = Project.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String fileName = "";
        for(int i = result.length() - 1; i >= 0; i--)
            if(result.charAt(i) != '/')
                fileName = (new StringBuilder(String.valueOf(result.charAt(i)))).append(fileName).toString();
            else
                i = -1;

        result = result.substring(0, result.length() - fileName.length() - 1);
        try
        {
            result = URLDecoder.decode(result, "UTF-8");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        result = result.replaceAll("file:/", "");
        result = result.replaceAll(File.separator + "u0020", File.separator + " ");
        return result;
    }

    public static void clearPortraits()
    {
        nestedcp.remove(portraitPane);
        portraits.removeAll();
        displayedNames = new String[5];
        nestedcp.validate();
        nestedcp.repaint();
    }

    public static void changePortrait(Forsaken.Gender gender, Chosen.Species spec, Boolean civilian, Boolean fallen, WorldState w, String names[], int number, Emotion first, 
            Emotion backup)
    {
        if(w.portraits)
        {
            int displayed = 5;
            if(names[3] == null)
            {
                displayed = 3;
                displayedEmotions[3] = null;
                displayedNames[3] = null;
                displayedEmotions[4] = null;
                displayedNames[4] = null;
            } else
            if(names[4] == null)
            {
                displayed = 4;
                displayedEmotions[4] = null;
                displayedNames[4] = null;
            }
            displayedType[number] = spec;
            displayedCivilians[number] = civilian;
            displayedFallen[number] = fallen;
            displayedGender[number] = gender;
            nestedcp.remove(portraitPane);
            portraits.removeAll();
            portraitPane.setHorizontalScrollBarPolicy(31);
            portraitPane.setVerticalScrollBarPolicy(21);
            int imageSize = scrollPane.getHeight() / displayed;
            BufferedImage image = null;
            for(int i = 0; i < displayed; i++)
            {
                displayedNames[i] = names[i];
                if(i == number)
                    if(displayedEmotions[i] == first)
                        displayedEmotions[i] = backup;
                    else
                        displayedEmotions[i] = first;
                String path = (new StringBuilder(getFilePath())).append(File.separator).append("portraits").append(File.separator).append("empty").toString();
                if(names[i] != null)
                    path = (new StringBuilder(getFilePath())).append(File.separator).append("portraits").append(File.separator).append(names[i]).append(File.separator).toString();
                String folders[] = {
                    "", "", "", ""
                };
                if(displayedGender[i] == Forsaken.Gender.MALE)
                    folders[0] = (new StringBuilder("male")).append(File.separator).toString();
                if(displayedType[i] == Chosen.Species.SUPERIOR)
                    folders[1] = (new StringBuilder("superior")).append(File.separator).toString();
                if(displayedCivilians[i])
                    folders[2] = (new StringBuilder("civilian")).append(File.separator).toString();
                if(displayedFallen[i])
                    folders[3] = (new StringBuilder("forsaken")).append(File.separator).toString();
                String type = "neutral";
                if(displayedEmotions[i] == Emotion.ANGER)
                    type = "anger";
                else
                if(displayedEmotions[i] == Emotion.FEAR)
                    type = "fear";
                else
                if(displayedEmotions[i] == Emotion.FOCUS)
                    type = "focus";
                else
                if(displayedEmotions[i] == Emotion.JOY)
                    type = "joy";
                else
                if(displayedEmotions[i] == Emotion.LEWD)
                    type = "lewd";
                else
                if(displayedEmotions[i] == Emotion.NEUTRAL)
                    type = "neutral";
                else
                if(displayedEmotions[i] == Emotion.SHAME)
                    type = "sadness";
                else
                if(displayedEmotions[i] == Emotion.STRUGGLE)
                    type = "struggle";
                else
                if(displayedEmotions[i] == Emotion.SWOON)
                    type = "swoon";
                for(int j = 0; j < 16 && image == null && displayedNames[i] != null; j++)
                {
                    String nav = "";
                    if(folders[0].length() > 0 && j < 8)
                        nav = (new StringBuilder(nav)).append(folders[0]).toString();
                    if(folders[1].length() > 0 && j % 8 < 4)
                        nav = (new StringBuilder(nav)).append(folders[1]).toString();
                    if(folders[2].length() > 0 && j % 4 < 2)
                        nav = (new StringBuilder(nav)).append(folders[2]).toString();
                    if(folders[3].length() > 0 && j % 2 == 0)
                        nav = (new StringBuilder(nav)).append(folders[3]).toString();
                    try
                    {
                        image = ImageIO.read(new File((new StringBuilder(path)).append(nav).append(type).append(".png").toString()));
                    }
                    catch(IOException ie)
                    {
                        try
                        {
                            image = ImageIO.read(new File((new StringBuilder(path)).append(nav).append(type).append(".jpg").toString()));
                        }
                        catch(IOException ig)
                        {
                            try
                            {
                                image = ImageIO.read(new File((new StringBuilder(path)).append(nav).append(type).append(".gif").toString()));
                            }
                            catch(IOException ih)
                            {
                                try
                                {
                                    image = ImageIO.read(new File((new StringBuilder(path)).append(nav).append(type).append(".jpeg").toString()));
                                }
                                catch(IOException ioexception) { }
                            }
                        }
                    }
                }

                if(image == null)
                    try
                    {
                        image = ImageIO.read(new File((new StringBuilder(getFilePath())).append(File.separator).append("portraits").append(File.separator).append("empty.png").toString()));
                    }
                    catch(IOException ie)
                    {
                        w.portraits = false;
                        clearPortraits();
                    }
                if(image != null)
                {
                    java.awt.Image resized = image.getScaledInstance(imageSize, imageSize, 4);
                    JLabel picLabel = new JLabel(new ImageIcon(resized));
                    portraits.add(picLabel);
                }
                image = null;
            }

            GridBagConstraints c = new GridBagConstraints();
            c.weighty = 0.0D;
            c.weightx = 0.0D;
            c.fill = 3;
            c.ipadx = imageSize;
            nestedcp.add(portraitPane, c);
            nestedcp.validate();
            nestedcp.repaint();
        }
    }

    public static void IntroOne(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        w.setGenders(w.getGenderBalance());
        p.getInputMap().clear();
        p.getActionMap().clear();
        clearPortraits();
        if(w.portraits)
        {
            BufferedImage image = null;
            try
            {
                image = ImageIO.read(new File((new StringBuilder(getFilePath())).append(File.separator).append("portraits").append(File.separator).append("empty.png").toString()));
            }
            catch(IOException ie)
            {
                w.portraits = false;
                clearPortraits();
            }
        }
        if(!t.getBackground().equals(w.BACKGROUND))
            w.toggleColors(t);
        w.append(t, "Corrupted Saviors, Release 28: \"Encounter\"\n\nThis game contains content of an adult nature and should not be played by the underaged or by those unable to distinguish fantasy from reality.\n\n" + w.getSeparator() + "\n\nJapan, mid-21st century.  The psychic energies of humanity have finally begun to coalesce into physical form.  The resulting beings are known as Demons.  Born from the base desires suppressed deep within the human mind, these creatures spread across the planet, leaving chaos and depravity in their wake.\n\nBut Demons do not represent the entirety of the human condition.  The hopes and determination of humanity have also risen up, gathering in the bodies of a few Chosen warriors in order to grant them the power to fight the Demons.  Although each of them was once an ordinary person, their new abilities place them at the center of the struggle for the soul of humanity.\n\nYou are a Demon Lord, the highest form of Demon, with your own mind and will, focused on the corruption of all that is good in the world.  The Chosen are the keystone of humanity's resistance to your goal, but to simply kill them would be meaningless.  Instead, shatter their notions of right and wrong, showing them the true darkness that hides within!");
        if(w.getCast()[0] == null)
        {
            Chosen newChosen = new Chosen();
            newChosen.setNumber(0);
            w.initialize();
            newChosen.generate(w);
            w.addChosen(newChosen);
        } else
        if(!w.getCast()[0].getGender().equals(w.getGenders()[0]))
        {
            w.getCast()[0] = null;
            Chosen newChosen = new Chosen();
            newChosen.setNumber(0);
            w.initialize();
            newChosen.generate(w);
            w.addChosen(newChosen);
        }
        p.removeAll();
        JButton NewGame = new JButton("Single Play");
        NewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(w.getEarlyCheat())
                {
                    Project.Shop(t, p, f, w);
                } else
                {
                    w.active = true;
                    Project.IntroTwo(t, p, f, w);
                }
            }
        });
        p.add(NewGame);
        JButton Campaign = new JButton("Campaign");
        Campaign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Boolean enabled[] = new Boolean[w.save.customRoster.length];
                for(int i = 0; i < enabled.length; i++)
                    enabled[i] = true;

                Project.CampaignMenu(t, p, f, w, enabled);
            }
        });
        p.add(Campaign);
        JButton LoadGame = new JButton("Load Game");
        LoadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.Data(t, p, f, w, "load", 0, false);
            }
        });
        p.add(LoadGame);
        JButton Import = new JButton("Import");
        Import.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.Data(t, p, f, w, "import", 0, false);
            }
        });
        p.add(Import);
        JButton Tutorial = new JButton("Tutorial");
        Tutorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                WorldState x = new WorldState();
                x.copySettings(t, w);
                x.copyToggles(w);
                x.tutorialInit();
                x.save = w.save;
                Project.BeginBattle(t, p, f, x, x.getCast()[0]);
                x.grayAppend(t, "\n\n(Welcome to the tutorial!  This feature is intended to demonstrate some useful techniques for corrupting the Chosen.  It uses a mid-game save file with several upgrades already purchased.  When playing from the start, it makes more sense to use the first several days experimenting to find the strengths and weaknesses of the Chosen and accumulating Evil Energy before aiming to break a vulnerability.  Read the guide.txt file included with the game for a more basic overview of the mechanics.\n\nFor now, let's start by using Examine to figure out how best to deal with Miracle.)");
            }
        });
        p.add(Tutorial);
        JButton Options = new JButton("Options");
        Options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.OptionsMenu(t, p, f, w, null);
            }
        });
        p.add(Options);
        JButton Customize = new JButton("Customize");
        Customize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                WorldState x = new WorldState();
                x.copySettings(t, w);
                x.copyToggles(w);
                x.setGenders(w.getGenderBalance());
                x.save = w.save;
                x.active = true;
                x.freshCustom(t, p, f);
            }
        });
        p.add(Customize);
        if(w.save.harem == null)
            w.save.harem = new Forsaken[0];
        if(w.save.harem.length > 0 || w.getEarlyCheat())
        {
            final SaveData fileUsed = w.save;
            JButton Forsaken = new JButton("Forsaken");
            Forsaken.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.ForsakenMenu(t, p, f, w, fileUsed, 0);
                }
            });
            p.add(Forsaken);
        }
        if(w.save.sceneText == null)
            w.save.organizeScenes(49);
        for(int i = 0; i < w.save.sceneText.length; i++)
            if(w.save.sceneText[i].length > 0)
            {
                i = w.save.sceneText.length;
                JButton Scenes = new JButton("Scenes");
                Scenes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.SceneCompletion(t, p, f, w, w.save);
                        Project.SceneViewer(t, p, f, w, w.save, 0);
                    }
                });
                p.add(Scenes);
            }

        JButton About = new JButton("About");
        About.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.append(t, "\n\n" + w.getSeparator() + "\n\nCopyright 2019-2022 by CSdev. Corrupted Saviors is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License. To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/4.0/.\n\nDefault portrait set created by CSdev with the assistance of Artbreeder and dedicated to the public domain (CC0).  For more information, see https://creativecommons.org/publicdomain/zero/1.0/.\n\nIf you like this game, please share it and discuss it so that it can be further enjoyed and improved!  There is a good chance that the developer reads whatever forum you found it on.  Direct feedback can also be sent to corruptedsaviors@gmail.com\n\nNew versions are first posted to corruptedsaviors.blogspot.com\nThe developer's tip jar can be found at subscribestar.adult/csdev");
            }
        });
        p.add(About);
        p.validate();
        p.repaint();
    }

    public static void CampaignMenu(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Boolean enabled[])
    {
        p.removeAll();
        clearPortraits();
        JButton Begin = new JButton("Begin");
        Begin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                WorldState x = new WorldState();
                for(int i = 0; i < enabled.length; i++)
                    if(enabled[i])
                    {
                        Chosen newCustom[] = new Chosen[x.campaignCustom.length + 1];
                        for(int j = 0; j < x.campaignCustom.length; j++)
                            newCustom[j] = x.campaignCustom[j];

                        newCustom[x.campaignCustom.length] = w.save.customRoster[i];
                        x.campaignCustom = newCustom;
                    }

                x.copySettings(t, w);
                x.copyToggles(w);
                x.save = w.save;
                x.setGenders(x.getGenderBalance());
                x.active = true;
                x.campaign = true;
                x.cityName = x.getCityName(0);
                x.campaignRand = new Random();
                x.hardMode = false;
                x.clampStart = 11;
                x.clampPercent = 100;
                x.eventOffset = 0;
                x.downtimeMultiplier = 100;
                Chosen newChosen = new Chosen();
                newChosen.setNumber(0);
                x.initialize();
                newChosen.generate(x);
                x.addChosen(newChosen);
                if(w.getEarlyCheat())
                {
                    Project.Shop(t, p, f, x);
                } else
                {
                    w.active = true;
                    Project.IntroTwo(t, p, f, x);
                }
            }
        });
        p.add(Begin);
        w.append(t, "\n\n" + w.getSeparator());
        if(w.save.customRoster == null)
            w.save.customRoster = new Chosen[0];
        if(w.save.customRoster.length == 0)
        {
            w.append(t, "\n\nBefore you start, you can generate custom Chosen who will eventually appear to face you.  You can also import a full team of Chosen from a save, in which case they'll face you in their Day 1 (i.e. uncorrupted) state.");
        } else
        {
            ReportCustomInclusion(t, w, enabled);
            JButton Toggle = new JButton("Toggle Inclusion");
            Toggle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.CampaignCustomToggle(t, p, f, w, enabled, 0, false);
                }
            });
            p.add(Toggle);
            JButton DeleteChosen = new JButton("Delete Chosen");
            DeleteChosen.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.CampaignCustomToggle(t, p, f, w, enabled, 0, true);
                }
            });
            p.add(DeleteChosen);
        }
        if(w.earlyCheat)
            w.append(t, "\n\nEasy Mode is turned on.  It will be possible to use cheats.  Aside from the presence of Elite Chosen, there will be no increases in the difficulty of later loops.");
        JButton LoadTeam = new JButton("Load Team");
        LoadTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.Data(t, p, f, w, "teamload", 0, false);
            }
        });
        p.add(LoadTeam);
        JButton SoloGen = new JButton("Single Custom");
        SoloGen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Chosen c = new Chosen();
                int nameSeed[] = {
                    (int)(Math.random() * 26D), (int)(Math.random() * 26D), 0, 0, 0, 0
                };
                WorldState dummy = new WorldState();
                for(int i = 0; i < 3; i++)
                    dummy.genders[i] = w.genders[i];

                String names[] = c.genName(dummy, nameSeed);
                c.givenName = names[0];
                c.familyName = names[1];
                c.gender = dummy.genders[0];
                c.originalGender = c.gender;
                c.textSize = w.getCast()[0].textSize;
                Project.SingleCustom(t, p, f, w, c, null);
            }
        });
        p.add(SoloGen);
        JButton ExportIncluded = new JButton("Export Included");
        ExportIncluded.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                WriteObject wobj = new WriteObject();
                Chosen exportedRoster[] = new Chosen[0];
                for(int i = 0; i < enabled.length; i++)
                    if(enabled[i])
                    {
                        Chosen newRoster[] = new Chosen[exportedRoster.length + 1];
                        for(int j = 0; j < exportedRoster.length; j++)
                            newRoster[j] = exportedRoster[j];

                        newRoster[exportedRoster.length] = w.save.customRoster[i];
                        exportedRoster = newRoster;
                    }

                String newRosterName = JOptionPane.showInputDialog("What would you like to name the exported roster?");
                Boolean blankName = false;
                if(newRosterName == null)
                    blankName = true;
                else
                if(newRosterName.length() == 0)
                    blankName = true;
                if(blankName)
                    newRosterName = (new StringBuilder(exportedRoster[0].mainName)).append("'s Roster").toString();
                exportedRoster[0].rosterName = newRosterName;
                String editedName = "";
                for(int i = 0; i < newRosterName.length(); i++)
                    if(newRosterName.charAt(i) == '/' || newRosterName.charAt(i) == ':')
                        editedName = (new StringBuilder(editedName)).append("-").toString();
                    else
                        editedName = (new StringBuilder(editedName)).append(newRosterName.charAt(i)).toString();

                wobj.exportRoster(exportedRoster, editedName);
                w.append(t, "\n\n" + w.getSeparator() + "\n\nNew roster saved to '" + editedName + ".ros'.");
            }
        });
        if(enabled.length > 0)
            p.add(ExportIncluded);
        JButton ImportRoster = new JButton("Import Roster");
        ImportRoster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.ImportMenu(t, p, f, w, enabled, 0);
            }
        });
        p.add(ImportRoster);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.IntroOne(t, p, f, w);
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
        WriteObject wobj = new WriteObject();
        wobj.serializeSaveData(w.save);
    }

    public static void ImportMenu(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Boolean enabled[], final int page)
    {
        p.removeAll();
        int i = page * 4;
        int j = 0;
        Chosen foundRosters[][] = new Chosen[0][0];
        ReadObject robj = new ReadObject();
        foundRosters = robj.importRoster();
        if(foundRosters.length == 0)
        {
            w.append(t, "\n\n" + w.getSeparator() + "\n\nNo importable rosters found in directory.");
        } else
        {
            w.append(t, "\n\n" + w.getSeparator() + "\n\nFound the following importable rosters in directory.  Which would you like to import?");
            if(page > 0)
            {
                JButton LastPage = new JButton("<");
                LastPage.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.ImportMenu(t, p, f, w, enabled, page - 1);
                    }
                });
                p.add(LastPage);
            }
            for(; i < foundRosters.length && j < 4; j++)
            {
                w.append(t, "\n\nFile " + String.valueOf(i + 1) + ": " + foundRosters[i][0].rosterName);
                final int rosterSelected = i;
                final Chosen rosterList[][] = foundRosters;
                JButton Access = new JButton((new StringBuilder()).append(i + 1).toString());
                Access.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        WriteObject wobj = new WriteObject();
                        Chosen newRoster[] = new Chosen[w.save.customRoster.length + rosterList[rosterSelected].length];
                        Boolean newEnabled[] = new Boolean[w.save.customRoster.length + rosterList[rosterSelected].length];
                        for(int k = 0; k < newRoster.length; k++)
                            if(k < w.save.customRoster.length)
                            {
                                newRoster[k] = w.save.customRoster[k];
                                newEnabled[k] = enabled[k];
                            } else
                            {
                                newRoster[k] = rosterList[rosterSelected][k - w.save.customRoster.length];
                                newRoster[k].globalID = w.save.assignChosenID();
                                newEnabled[k] = true;
                            }

                        w.save.customRoster = newRoster;
                        wobj.serializeSaveData(w.save);
                        w.append(t, "\n\n" + w.getSeparator() + "\n\n" + rosterList[rosterSelected].length + " new Chosen added to custom roster.");
                        Project.CampaignMenu(t, p, f, w, newEnabled);
                    }
                });
                p.add(Access);
                i++;
            }

            if(page * 4 + 4 < foundRosters.length)
            {
                JButton NextPage = new JButton(">");
                NextPage.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.ImportMenu(t, p, f, w, enabled, page + 1);
                    }
                });
                p.add(NextPage);
            }
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.CampaignMenu(t, p, f, w, enabled);
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void SingleCustom(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Chosen c, final int answers[])
    {
        p.removeAll();
        w.append(t, "\n\n" + w.getSeparator() + "\n\n" + c.customSummary() + "\n\nSet the Chosen's basic characteristics.");
        JButton FamilyName = new JButton("Family Name");
        FamilyName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String input = JOptionPane.showInputDialog("What is " + c.hisHer() + " family surname?  Leave blank to have " + c.himHer() + " lack a surname.");
                if(input == null)
                    c.familyName = "";
                else
                    c.familyName = input;
                Project.SingleCustom(t, p, f, w, c, answers);
            }
        });
        p.add(FamilyName);
        JButton GivenName = new JButton("Given Name");
        GivenName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String input = JOptionPane.showInputDialog("What name was " + c.heShe() + " given at birth?  Leave blank to keep the current choice (" + c.givenName + ").");
                if(input != null && input.length() > 0)
                {
                    c.givenName = input;
                    Project.SingleCustom(t, p, f, w, c, answers);
                }
            }
        });
        p.add(GivenName);
        JButton NameOrder = new JButton("Name Order");
        NameOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                c.filthyGaijin = Boolean.valueOf(!c.filthyGaijin);
                Project.SingleCustom(t, p, f, w, c, answers);
            }
        });
        p.add(NameOrder);
        JButton Sex = new JButton("Sex");
        Sex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(c.gender.equals("female"))
                    c.gender = "male";
                else
                if(c.gender.equals("male"))
                    c.gender = "futanari";
                else
                    c.gender = "female";
                Project.SingleCustom(t, p, f, w, c, answers);
            }
        });
        p.add(Sex);
        JButton Species = new JButton("Species");
        Species.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(c.type == null)
                    c.type = Chosen.Species.SUPERIOR;
                else
                    c.type = null;
                Project.SingleCustom(t, p, f, w, c, answers);
            }
        });
        p.add(Species);
        JButton Continue = new JButton("Continue (Quiz)");
        Continue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(answers == null)
                    Project.CustomQuiz(t, p, f, w, c, 0, new int[32]);
                else
                    Project.CustomQuiz(t, p, f, w, c, 0, answers);
            }
        });
        p.add(Continue);
        JButton DirectInput = new JButton("Continue (Direct Input)");
        DirectInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String input = "";
                input = JOptionPane.showInputDialog("Enter " + c.givenName + "'s Morality (0 to 100).");
                int upperLimit = 100;
                int lowerLimit = 0;
                int value = 0;
                try
                {
                    value = Integer.parseInt(input);
                }
                catch(Exception ex)
                {
                    w.append(t, "\n\n" + w.getSeparator() + "\n\nInvalid value.");
                    Project.SingleCustom(t, p, f, w, c, answers);
                    ex.printStackTrace();
                }
                if(value >= 0 && value <= 100)
                {
                    c.morality = Integer.parseInt(input);
                    input = JOptionPane.showInputDialog("Enter " + c.givenName + "'s Innocence (0 to 100).");
                    try
                    {
                        value = Integer.parseInt(input);
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                        w.append(t, "\n\n" + w.getSeparator() + "\n\nInvalid value.");
                        Project.SingleCustom(t, p, f, w, c, answers);
                    }
                    if(value >= 0 && value <= 100)
                    {
                        c.innocence = value;
                        if(c.morality > 66 && c.innocence > 66)
                            upperLimit = 66;
                        else
                        if(c.morality < 34 && c.innocence < 34)
                            lowerLimit = 34;
                        else
                        if(c.morality > 33 && c.innocence > 33 && c.morality < 67 && c.innocence < 67)
                        {
                            upperLimit = 33;
                            lowerLimit = 67;
                        }
                        if(upperLimit > lowerLimit)
                            input = JOptionPane.showInputDialog("Enter " + c.givenName + "'s Confidence (" + lowerLimit + " to " + upperLimit + ").");
                        else
                            input = JOptionPane.showInputDialog("Enter " + c.givenName + "'s Confidence (0 to 33 or 67 to 100).");
                        try
                        {
                            value = Integer.parseInt(input);
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                            w.append(t, "\n\n" + w.getSeparator() + "\n\nInvalid value.");
                            Project.SingleCustom(t, p, f, w, c, answers);
                        }
                        if(value >= 0 && value <= 100 && (value >= lowerLimit && value <= upperLimit || upperLimit < lowerLimit && (value > 66 || value < 34)))
                        {
                            c.confidence = value;
                            Boolean highFound = false;
                            Boolean midFound = false;
                            Boolean lowFound = false;
                            if(c.morality > 66)
                                highFound = true;
                            else
                            if(c.morality > 34)
                                midFound = true;
                            else
                                lowFound = true;
                            if(c.innocence > 66)
                                highFound = true;
                            else
                            if(c.innocence > 33)
                                midFound = true;
                            else
                                lowFound = true;
                            if(c.confidence > 66)
                                highFound = true;
                            else
                            if(c.confidence > 33)
                                midFound = true;
                            else
                                lowFound = true;
                            if(!highFound)
                            {
                                upperLimit = 100;
                                lowerLimit = 67;
                            } else
                            if(!midFound)
                            {
                                upperLimit = 66;
                                lowerLimit = 34;
                            } else
                            if(!lowFound)
                            {
                                upperLimit = 33;
                                lowerLimit = 0;
                            } else
                            {
                                upperLimit = 100;
                                lowerLimit = 0;
                            }
                            input = JOptionPane.showInputDialog("Enter " + c.givenName + "'s Dignity (" + lowerLimit + " to " + upperLimit + ").");
                            try
                            {
                                value = Integer.parseInt(input);
                            }
                            catch(Exception ex)
                            {
                                w.append(t, "\n\n" + w.getSeparator() + "\n\nInvalid value.");
                                Project.SingleCustom(t, p, f, w, c, answers);
                                ex.printStackTrace();
                            }
                            if(value >= lowerLimit && value <= upperLimit)
                            {
                                c.dignity = value;
                                if(!w.determineVVirg(c.morality, c.innocence, c.confidence, c.dignity))
                                {
                                    c.ruthless = true;
                                    c.vVirg = false;
                                    c.vStart = false;
                                    c.vTaker = 0;
                                } else
                                {
                                    c.ruthless = false;
                                    c.vVirg = true;
                                    c.vStart = true;
                                    c.vTaker = -1;
                                }
                                if(!w.determineCVirg(c.morality, c.innocence, c.confidence, c.dignity))
                                {
                                    c.lustful = true;
                                    c.cVirg = false;
                                    c.cStart = false;
                                    c.cTaker = 0;
                                } else
                                {
                                    c.lustful = false;
                                    c.cVirg = true;
                                    c.cStart = true;
                                    c.cTaker = -1;
                                }
                                if(!w.determineAVirg(c.morality, c.innocence, c.confidence, c.dignity))
                                {
                                    c.meek = true;
                                    c.aVirg = false;
                                    c.aStart = false;
                                    c.aTaker = 0;
                                } else
                                {
                                    c.meek = false;
                                    c.aVirg = true;
                                    c.aStart = true;
                                    c.aTaker = -1;
                                }
                                if(!w.determineModest(c.morality, c.innocence, c.confidence, c.dignity))
                                {
                                    c.debased = true;
                                    c.modest = false;
                                    c.mStart = false;
                                    c.mTaker = 0;
                                } else
                                {
                                    c.debased = false;
                                    c.modest = true;
                                    c.mStart = true;
                                    c.mTaker = -1;
                                }
                                Project.SingleCosmetics(t, p, f, w, c, null);
                            } else
                            {
                                w.append(t, "\n\n" + w.getSeparator() + "\n\nInvalid value.");
                                Project.SingleCustom(t, p, f, w, c, answers);
                            }
                        } else
                        {
                            w.append(t, "\n\n" + w.getSeparator() + "\n\nInvalid value.");
                            Project.SingleCustom(t, p, f, w, c, answers);
                        }
                    } else
                    {
                        w.append(t, "\n\n" + w.getSeparator() + "\n\nInvalid value.");
                        Project.SingleCustom(t, p, f, w, c, answers);
                    }
                } else
                {
                    w.append(t, "\n\n" + w.getSeparator() + "\n\nInvalid value.");
                    Project.SingleCustom(t, p, f, w, c, answers);
                }
            }
        });
        p.add(DirectInput);
        JButton RandomGen = new JButton("Continue (Random)");
        RandomGen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Boolean valid = false;
                int stats[] = new int[4];
                int highs = 0;
                int mids = 0;
                int lows = 0;
                while(!valid) 
                {
                    highs = 0;
                    mids = 0;
                    lows = 0;
                    valid = true;
                    for(int i = 0; i < 4; i++)
                    {
                        stats[i] = (int)(Math.random() * 101D);
                        if(stats[i] > 66)
                            highs++;
                        else
                        if(stats[i] > 33)
                            mids++;
                        else
                            lows++;
                    }

                    if(highs == 0 || mids == 0 || lows == 0)
                        valid = false;
                }
                c.morality = stats[0];
                c.innocence = stats[1];
                c.confidence = stats[2];
                c.dignity = stats[3];
                if(!w.determineVVirg(c.morality, c.innocence, c.confidence, c.dignity))
                {
                    c.ruthless = true;
                    c.vVirg = false;
                    c.vStart = false;
                    c.vTaker = 0;
                } else
                {
                    c.ruthless = false;
                    c.vVirg = true;
                    c.vStart = true;
                    c.vTaker = -1;
                }
                if(!w.determineCVirg(c.morality, c.innocence, c.confidence, c.dignity))
                {
                    c.lustful = true;
                    c.cVirg = false;
                    c.cStart = false;
                    c.cTaker = 0;
                } else
                {
                    c.lustful = false;
                    c.cVirg = true;
                    c.cStart = true;
                    c.cTaker = -1;
                }
                if(!w.determineAVirg(c.morality, c.innocence, c.confidence, c.dignity))
                {
                    c.meek = true;
                    c.aVirg = false;
                    c.aStart = false;
                    c.aTaker = 0;
                } else
                {
                    c.meek = false;
                    c.aVirg = true;
                    c.aStart = true;
                    c.aTaker = -1;
                }
                if(!w.determineModest(c.morality, c.innocence, c.confidence, c.dignity))
                {
                    c.debased = true;
                    c.modest = false;
                    c.mStart = false;
                    c.mTaker = 0;
                } else
                {
                    c.debased = false;
                    c.modest = true;
                    c.mStart = true;
                    c.mTaker = -1;
                }
                Project.SingleCosmetics(t, p, f, w, c, null);
            }
        });
        p.add(RandomGen);
        JButton Quit = new JButton("Quit");
        Quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                w.append(t, "\n\n" + w.getSeparator() + "\n\nReally quit?  All customization of this Chosen will be lost!");
                JButton MainMenu = new JButton("Quit");
                MainMenu.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                        Project.IntroOne(t, p, f, w);
                    }
                });
                p.add(MainMenu);
                JButton Cancel = new JButton("Cancel");
                Cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.SingleCustom(t, p, f, w, c, answers);
                    }
                });
                p.add(Cancel);
                p.validate();
                p.repaint();
            }
        });
        p.add(Quit);
        p.validate();
        p.repaint();
    }

    public static void CustomQuiz(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Chosen c, final int progress, final int answers[])
    {
        p.removeAll();
        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
        if(progress == 0)
            w.append(t, String.format("This segment takes the form of a series of questions about how %1$s", c.givenName) + " thinks and behaves.  Pick the option that comes closest to how " + c.heShe() + " really is.\n\nQuestion 1: In what way does " + c.givenName + " try to act?");
        else
        if(progress == 1)
            w.append(t, String.format("Question 2: Does %1$s", c.givenName) + " try to earn others' fear, or does " + c.heShe() + " try to earn their love?");
        else
        if(progress == 2)
            w.append(t, String.format("Question 3: What does %1$s", c.givenName) + " do after making a mistake that hurts someone else?");
        else
        if(progress == 3)
            w.append(t, String.format("Question 4: What does %1$s", c.givenName) + " tend to do when someone disagrees with " + c.himHer() + " about what they should do?");
        else
        if(progress == 4)
            w.append(t, String.format("Question 5: Which enemies does %1$s", c.givenName) + " prefer to target?");
        else
        if(progress == 5)
            w.append(t, String.format("Question 6: How does %1$s", c.givenName) + " treat people " + c.heShe() + " dislikes?");
        else
        if(progress == 6)
            w.append(t, String.format("Question 7: What does %1$s", c.givenName) + " do when someone hurts " + c.himHer() + "?");
        else
        if(progress == 7)
            w.append(t, String.format("Question 8: Which social role describes %1$s", c.givenName) + "?");
        else
        if(progress == 8)
            w.append(t, String.format("Question 9: How does %1$s", c.givenName) + " feel after being forced to run from a fight?");
        else
        if(progress == 9)
            w.append(t, String.format("Question 10: When is it possible for %1$s", c.givenName) + "'s friends to convince " + c.himHer() + " to commit a crime?");
        else
        if(progress == 10)
            w.append(t, String.format("Question 11: How can one pick a fight with %1$s", c.givenName) + "?");
        else
        if(progress == 11)
            w.append(t, String.format("Question 12: How does %1$s", c.givenName) + " fight against a stronger enemy?");
        else
        if(progress == 12)
            w.append(t, String.format("Question 13: How does %1$s", c.givenName) + " respond when civilians are taken hostage?");
        else
        if(progress == 13)
            w.append(t, String.format("Question 14: Will %1$s", c.givenName) + " lie to protect someone else?");
        else
        if(progress == 14)
            w.append(t, String.format("Question 15: How does %1$s", c.givenName) + " react when stripped while protecting civilians?");
        else
        if(progress == 15)
            w.append(t, String.format("Question 16: How does %1$s", c.givenName) + " respond to being praised?");
        else
        if(progress == 16)
            w.append(t, String.format("Question 17: When does %1$s", c.givenName) + " put " + c.himHer() + "self in harm's way?");
        else
        if(progress == 17)
            w.append(t, String.format("Question 18: What does %1$s", c.givenName) + " think of " + c.hisHer() + " fanbase?");
        else
        if(progress == 18)
            w.append(t, String.format("Question 19: What does %1$s", c.givenName) + " do when interviewed by reporters after a major defeat?");
        else
        if(progress == 19)
            w.append(t, String.format("Question 20: How does %1$s", c.givenName) + " fight against a weaker enemy?");
        else
        if(progress == 20)
            w.append(t, String.format("Question 21: How does %1$s", c.givenName) + " handle it when " + c.hisHer() + " friends get into an argument?");
        else
        if(progress == 21)
            w.append(t, String.format("Question 22: What does %1$s", c.givenName) + " do about " + c.hisHer() + " friends' personality flaws?");
        else
        if(progress == 22)
            w.append(t, String.format("Question 23: Does %1$s", c.givenName) + " claim to be a good person?");
        else
        if(progress == 23)
            w.append(t, String.format("Question 24: Does %1$s", c.givenName) + " pretend to be stronger than " + c.heShe() + " actually is?");
        else
        if(progress == 24)
            w.append(t, String.format("Question 25: When is %1$s", c.givenName) + " willing to abandon innocent lives?");
        else
        if(progress == 25)
            w.append(t, String.format("Question 26: How does %1$s", c.givenName) + " handle fans who try to get too close to " + c.himHer() + "?");
        else
        if(progress == 26)
            w.append(t, String.format("Question 27: How does %1$s", c.givenName) + " respond to being given orders?");
        else
        if(progress == 27)
            w.append(t, String.format("Question 28: How does %1$s", c.givenName) + " prefer to get help from people?");
        else
        if(progress == 28)
            w.append(t, String.format("Question 29: What does %1$s", c.givenName) + " do with the vast wealth paid to the Chosen?");
        else
        if(progress == 29)
            w.append(t, String.format("Question 30: What does %1$s", c.givenName) + " tell " + c.hisHer() + " fans to do?");
        else
        if(progress == 30)
            w.append(t, String.format("Question 31: Does %1$s", c.givenName) + " keep " + c.hisHer() + " promises?");
        else
        if(progress == 31)
            w.append(t, String.format("Question 32: What does %1$s", c.givenName) + " do when " + c.heShe() + " notices " + c.heShe() + "'s being filmed by spectators?");
        if(answers[progress] != 0)
            w.append(t, String.format("  (Previous answer: %1$s", answers[progress]) + ")");
        w.append(t, "\n\n");
        if(progress == 0)
            w.append(t, "1: A way that makes others happy.\n2: A way that makes everyone happy.\n3: A way that makes " + c.givenName + " " + c.himHer() + "self happy.");
        else
        if(progress == 1)
            w.append(t, "1: Love.\n2: Both fear and love.\n3: Fear.");
        else
        if(progress == 2)
            w.append(t, "1: Apologize.\n2: Ignore them.\n3: Try to cover up " + c.hisHer() + " involvement.");
        else
        if(progress == 3)
            w.append(t, "1: Give in.\n2: Stubbornly hold " + c.hisHer() + " position.\n3: Try to reason with the other person.");
        else
        if(progress == 4)
            w.append(t, "1: The ones threatening innocents.\n2: Whichever ones cross " + c.hisHer() + " path.\n3: Whichever ones stand between " + c.himHer() + " and safety.");
        else
        if(progress == 5)
            w.append(t, "1: Try to find a way to befriend them.\n2: Spread gossip about them.\n3: Constantly bring up their weaknesses and insecurities.");
        else
        if(progress == 6)
            w.append(t, String.format("1: Hold %1$s", c.hisHer()) + " pain inside.\n2: Hurt them back, by any means necessary.\n3: Whine to anyone who will listen.");
        else
        if(progress == 7)
            w.append(t, "1: Follower.\n2: Leader.\n3: Outcast.");
        else
        if(progress == 8)
            w.append(t, String.format("1: Angry at %1$s", c.himHer()) + "self for being too weak.\n2: Perfectly content to have safely escaped.\n3: Angry at " + c.hisHer() + " allies for putting " + c.himHer() + " in that position.");
        else
        if(progress == 9)
            w.append(t, String.format("1: When %1$s", c.heShe()) + " thinks it won't hurt anybody.\n2: When " + c.heShe() + "'s sure " + c.heShe() + " won't be punished for it.\n3: Pretty much always.");
        else
        if(progress == 10)
            w.append(t, String.format("1: By hurting others in front of %1$s", c.himHer()) + ".\n2: It isn't possible.\n3: By insulting " + c.hisHer() + " pride.");
        else
        if(progress == 11)
            w.append(t, "1: Charge straight in.\n2: Call for help.\n3: Find a way to even the odds.");
        else
        if(progress == 12)
            w.append(t, String.format("1: %1$s", c.HeShe()) + " attacks anyway.\n2: " + c.HeShe() + " surrenders immediately.\n3: " + c.HeShe() + " cooperates only until " + c.heShe() + " can find a chance to free them.");
        else
        if(progress == 13)
            w.append(t, String.format("1: Only if they make it worth %1$s", c.hisHer()) + " while.\n2: " + c.HeShe() + " thinks lying is wrong.\n3: " + c.HeShe() + " will, if " + c.heShe() + " thinks they deserve it.");
        else
        if(progress == 14)
            w.append(t, "1: Flee, leaving the civilians to their fate.\n2: Laugh it off.\n3: Try to pretend it doesn't bother " + c.himHer() + ".");
        else
        if(progress == 15)
            w.append(t, String.format("1: %1$s", c.HeShe()) + " assumes that the other person is trying to get something from " + c.himHer() + ".\n2: " + c.HeShe() + " accepts it as what " + c.heShe() + " deserves.\n3: " + c.HeShe() + " feels surprised and happy.");
        else
        if(progress == 16)
            w.append(t, "1: Whenever it protects others.\n2: Whenever there's an appropriate reward.\n3: Almost never.");
        else
        if(progress == 17)
            w.append(t, String.format("1: %1$s", c.HeShe()) + " wants to serve them.\n2: " + c.HeShe() + " wants them to serve " + c.himHer() + ".\n3: " + c.HeShe() + " doesn't care about them.");
        else
        if(progress == 18)
            w.append(t, "1: Reassure everyone.\n2: Blame someone else.\n3: Ignore them.");
        else
        if(progress == 19)
            w.append(t, String.format("1: Let %1$s", c.hisHer()) + " guard down and show off.\n2: Remain slow, steady, and cautious.\n3: Take them out quickly and move on.");
        else
        if(progress == 20)
            w.append(t, String.format("1: Take the side of whoever is more useful to %1$s", c.himHer()) + ".\n2: Take the side of whoever " + c.heShe() + " agrees with.\n3: Try to help them resolve their differences with each other.");
        else
        if(progress == 21)
            w.append(t, String.format("1: Exploit them for %1$s", c.hisHer()) + " own benefit.\n2: Tolerate them patiently.\n3: Try to help them overcome their flaws.");
        else
        if(progress == 22)
            w.append(t, String.format("1: Yes, but %1$s", c.heShe()) + " knows " + c.heShe() + "'s not.\n2: Yes, and " + c.heShe() + " thinks it's true.\n3: No, " + c.heShe() + " doesn't.");
        else
        if(progress == 23)
            w.append(t, "1: Yes, but not by so much that it can be disproven.\n2: Yes, to an impossible degree.\n3: No, never.");
        else
        if(progress == 24)
            w.append(t, String.format("1: Whenever %1$s", c.heShe()) + " feels like it would be even slightly dangerous for " + c.himHer() + ".\n2: Whenever " + c.heShe() + " judges it unlikely that " + c.heShe() + "'d be able to successfully save them.\n3: Never.");
        else
        if(progress == 25)
            w.append(t, "1: Being mean to them.\n2: Firmly but gently turning them away.\n3: Trying to make them happy without going too far beyond " + c.hisHer() + " comfort zone.");
        else
        if(progress == 26)
            w.append(t, "1: Spitefully do the opposite.\n2: Obey quickly.\n3: Obey only if " + c.heShe() + " agrees with them.");
        else
        if(progress == 27)
            w.append(t, "1: Bargain for something of equal value.\n2: Whine and beg.\n3: Act cute and sweet-talk them.");
        else
        if(progress == 28)
            w.append(t, String.format("1: Spend it all on luxuries for %1$s", c.himHer()) + "self.\n2: Personally direct it into projects which benefit society as " + c.heShe() + " sees it.\n3: Give most of it away to people who know better than " + c.himHer() + " how it should be spent.");
        else
        if(progress == 29)
            w.append(t, String.format("1: Worship %1$s", c.himHer()) + ".\n2: Try to make the world a better place.\n3: Do whatever makes them happy.");
        else
        if(progress == 30)
            w.append(t, String.format("1: Only when %1$s", c.heShe()) + " feels like it.\n2: " + c.HeShe() + " tries, but often promises more than " + c.heShe() + " can do.\n3: Yes, " + c.heShe() + "'s trustworthy.");
        else
        if(progress == 31)
            w.append(t, "1: Focus even more on not making any mistakes.\n2: Get flustered.\n3: Ignore them.");
        for(int i = 0; i < 3; i++)
        {
            final int picked = i + 1;
            JButton ThisAnswer = new JButton((new StringBuilder()).append(picked).toString());
            ThisAnswer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    answers[progress] = picked;
                    if(progress < 31)
                        Project.CustomQuiz(t, p, f, w, c, progress + 1, answers);
                    else
                        Project.SinglePersonality(t, p, f, w, c, answers);
                }
            });
            p.add(ThisAnswer);
        }

        JButton Pass = new JButton("Pass");
        Pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                answers[progress] = 0;
                if(progress < 31)
                    Project.CustomQuiz(t, p, f, w, c, progress + 1, answers);
                else
                    Project.SinglePersonality(t, p, f, w, c, answers);
            }
        });
        p.add(Pass);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(progress > 0)
                    Project.CustomQuiz(t, p, f, w, c, progress - 1, answers);
                else
                    Project.SingleCustom(t, p, f, w, c, answers);
            }
        });
        p.add(Back);
        JButton Quit = new JButton("Quit");
        Quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                w.append(t, "\n\n" + w.getSeparator() + "\n\nReally quit?  All customization of this Chosen will be lost!");
                JButton MainMenu = new JButton("Quit");
                MainMenu.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                        Project.IntroOne(t, p, f, w);
                    }
                });
                p.add(MainMenu);
                JButton Cancel = new JButton("Cancel");
                Cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.CustomQuiz(t, p, f, w, c, progress, answers);
                    }
                });
                p.validate();
                p.repaint();
            }
        });
        p.add(Quit);
        p.validate();
        p.repaint();
    }

    public static void SinglePersonality(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Chosen c, int answers[])
    {
        int totals[] = new int[4];
        int certainties[] = new int[4];
        for(int i = 0; i < 32; i++)
        {
            int skipped = 3 - i % 4;
            int signs[] = new int[3];
            int first = 0;
            int second = 0;
            int third = 0;
            if(i / 4 == 0)
            {
                signs[0] = 1;
                signs[1] = 1;
                signs[2] = 1;
            } else
            if(i / 4 == 1)
            {
                signs[0] = 1;
                signs[1] = 1;
                signs[2] = -1;
            } else
            if(i / 4 == 2)
            {
                signs[0] = 1;
                signs[1] = -1;
                signs[2] = 1;
            } else
            if(i / 4 == 3)
            {
                signs[0] = -1;
                signs[1] = 1;
                signs[2] = 1;
            } else
            if(i / 4 == 4)
            {
                signs[0] = 1;
                signs[1] = -1;
                signs[2] = -1;
            } else
            if(i / 4 == 5)
            {
                signs[0] = -1;
                signs[1] = 1;
                signs[2] = -1;
            } else
            if(i / 4 == 6)
            {
                signs[0] = -1;
                signs[1] = -1;
                signs[2] = 1;
            } else
            {
                signs[0] = -1;
                signs[1] = -1;
                signs[2] = -1;
            }
            for(int j = 0; j < 3 && answers[i] != 0; j++)
            {
                int index = j;
                if(skipped <= j)
                    index++;
                if(answers[i] - 1 == j)
                {
                    totals[index] = totals[index] + 2 * signs[j];
                    certainties[index] = certainties[index] + 2;
                } else
                {
                    totals[index] = totals[index] - signs[j];
                    certainties[index] = certainties[index] + 1;
                }
            }

        }

        int stats[] = new int[4];
        int highs = 0;
        int mids = 0;
        int lows = 0;
        for(int i = 0; i < 4; i++)
        {
            if(totals[i] < -14)
                stats[i] = totals[i] + 36;
            else
            if(totals[i] > 14)
                stats[i] = totals[i] + 64;
            else
                stats[i] = totals[i] * 2 + 50;
            if(certainties[i] < 30)
                if(stats[i] > 50)
                    stats[i] = (stats[i] + 30) - certainties[i];
                else
                    stats[i] = (stats[i] - 30) + certainties[i];
            if(stats[i] > 66)
                highs++;
            else
            if(stats[i] > 33)
                mids++;
            else
                lows++;
        }

        while(highs == 0 || mids == 0 || lows == 0) 
        {
            highs = 0;
            mids = 0;
            lows = 0;
            for(int i = 0; i < 4; i++)
                if(stats[i] > 66)
                    highs++;
                else
                if(stats[i] > 33)
                    mids++;
                else
                    lows++;

            if(highs == 0)
            {
                int greatestFlexibility = 0;
                int flexibleStat = 0;
                for(int i = 0; i < 4; i++)
                    if(stats[i] * (36 - certainties[i]) > greatestFlexibility)
                    {
                        greatestFlexibility = stats[i] * (36 - certainties[i]);
                        flexibleStat = i;
                    }

                stats[flexibleStat] = 67 + (36 - certainties[flexibleStat]) / 2;
            } else
            if(lows == 0)
            {
                int greatestFlexibility = 0;
                int flexibleStat = 0;
                for(int i = 0; i < 4; i++)
                    if((100 - stats[i]) * (36 - certainties[i]) > greatestFlexibility)
                    {
                        greatestFlexibility = (100 - stats[i]) * (36 - certainties[i]);
                        flexibleStat = i;
                    }

                stats[flexibleStat] = 33 - (36 - certainties[flexibleStat]) / 2;
            } else
            {
                int greatestFlexibility = 0;
                int flexibleStat = 0;
                for(int i = 0; i < 4; i++)
                    if((stats[i] > 66 && highs > 1 || stats[i] < 34 && lows > 1) && Math.abs(stats[i] - 50) * (36 - certainties[i]) > greatestFlexibility)
                    {
                        greatestFlexibility = (100 - stats[i]) * (36 - certainties[i]);
                        flexibleStat = i;
                    }

                if(stats[flexibleStat] > 50)
                    stats[flexibleStat] = 66 - (36 - certainties[flexibleStat]) / 2;
                else
                    stats[flexibleStat] = 33 + (36 - certainties[flexibleStat]) / 2;
            }
            for(int i = 0; i < 4; i++)
                if(stats[i] > 66)
                    highs++;
                else
                if(stats[i] > 33)
                    mids++;
                else
                    lows++;

        }
        c.morality = stats[0];
        c.innocence = stats[1];
        c.confidence = stats[2];
        c.dignity = stats[3];
        if(!w.determineVVirg(c.morality, c.innocence, c.confidence, c.dignity))
        {
            c.ruthless = true;
            c.vVirg = false;
            c.vStart = false;
            c.vTaker = 0;
        } else
        {
            c.ruthless = false;
            c.vVirg = true;
            c.vStart = true;
            c.vTaker = -1;
        }
        if(!w.determineCVirg(c.morality, c.innocence, c.confidence, c.dignity))
        {
            c.lustful = true;
            c.cVirg = false;
            c.cStart = false;
            c.cTaker = 0;
        } else
        {
            c.lustful = false;
            c.cVirg = true;
            c.cStart = true;
            c.cTaker = -1;
        }
        if(!w.determineAVirg(c.morality, c.innocence, c.confidence, c.dignity))
        {
            c.meek = true;
            c.aVirg = false;
            c.aStart = false;
            c.aTaker = 0;
        } else
        {
            c.meek = false;
            c.aVirg = true;
            c.aStart = true;
            c.aTaker = -1;
        }
        if(!w.determineModest(c.morality, c.innocence, c.confidence, c.dignity))
        {
            c.debased = true;
            c.modest = false;
            c.mStart = false;
            c.mTaker = 0;
        } else
        {
            c.debased = false;
            c.modest = true;
            c.mStart = true;
            c.mTaker = -1;
        }
        SingleCosmetics(t, p, f, w, c, answers);
    }

    public static void SingleCosmetics(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Chosen c, final int answers[])
    {
        p.removeAll();
        if(c.mainName == null)
        {
            c.world = w;
            if(c.globalID == 0)
                c.globalID = w.save.assignChosenID();
            c.textSize = w.textSize;
            if(c.morality > 66)
            {
                c.textColor = new Color(0, 0, 230);
                c.darkColor = new Color(100, 100, 255);
            } else
            if(c.morality > 33)
            {
                c.textColor = new Color(0, 110, 0);
                c.darkColor = new Color(70, 170, 70);
            } else
            {
                c.textColor = new Color(180, 0, 0);
                c.darkColor = new Color(220, 90, 90);
            }
            c.incantation = c.genIncantation(c.morality, c.dignity);
            c.adjectiveName = c.genAdjectiveName(c.innocence, c.confidence);
            c.nounName = c.genNounName(c.morality, c.innocence);
            c.mainName = c.genMainName(c.morality, c.innocence, c.confidence, c.dignity);
            String cosmetics[] = w.pickCosmetics(c.morality, c.innocence, c.confidence, c.dignity);
            c.topCover = cosmetics[0];
            c.topAccess = cosmetics[1];
            c.bottomCover = cosmetics[2];
            c.bottomAccess = cosmetics[3];
            c.feetType = cosmetics[9];
            c.underType = cosmetics[4];
            c.color = cosmetics[5];
            c.accessory = cosmetics[6];
            c.weapon = cosmetics[7];
            c.customWeaponType = cosmetics[8];
            if(c.morality > 66)
                c.bonusHATE = true;
            else
                c.bonusHATE = false;
            if(c.innocence > 66)
                c.bonusPLEA = true;
            else
                c.bonusPLEA = false;
            if(c.confidence > 66)
                c.bonusINJU = true;
            else
                c.bonusINJU = false;
            if(c.dignity > 66)
                c.bonusEXPO = true;
            else
                c.bonusEXPO = false;
        }
        w.append(t, "\n\n" + w.getSeparator() + "\n\n" + c.customSummary());
        int highs = 0;
        int mids = 0;
        int lows = 0;
        if(c.morality > 66)
        {
            w.blueAppend(t, "\nMorality " + c.morality + ": Core");
            highs++;
        } else
        if(c.morality > 33)
        {
            w.greenAppend(t, "\nMorality " + c.morality + ": Significant");
            mids++;
        } else
        {
            w.redAppend(t, "\nMorality " + c.morality + ": Minor");
            lows++;
        }
        if(!c.vVirg)
            w.redAppend(t, " (BROKEN)");
        if(c.innocence > 66)
        {
            w.blueAppend(t, "\nInnocence " + c.innocence + ": Core");
            highs++;
        } else
        if(c.innocence > 33)
        {
            w.greenAppend(t, "\nInnocence " + c.innocence + ": Significant");
            mids++;
        } else
        {
            w.redAppend(t, "\nInnocence " + c.innocence + ": Minor");
            lows++;
        }
        if(!c.cVirg)
            w.redAppend(t, " (BROKEN)");
        if(c.confidence > 66)
        {
            w.blueAppend(t, "\nConfidence " + c.confidence + ": Core");
            highs++;
        } else
        if(c.confidence > 33)
        {
            w.greenAppend(t, "\nConfidence " + c.confidence + ": Significant");
            mids++;
        } else
        {
            w.redAppend(t, "\nConfidence " + c.confidence + ": Minor");
            lows++;
        }
        if(!c.aVirg)
            w.redAppend(t, " (BROKEN)");
        if(c.dignity > 66)
        {
            w.blueAppend(t, "\nDignity " + c.dignity + ": Core");
            highs++;
        } else
        if(c.dignity > 33)
        {
            w.greenAppend(t, "\nDignity " + c.dignity + ": Significant");
            mids++;
        } else
        {
            w.redAppend(t, "\nDignity " + c.dignity + ": Minor");
            lows++;
        }
        if(!c.modest)
            w.redAppend(t, " (BROKEN)");
        w.append(t, "\nValid Custom Partners:");
        int found = 0;
        for(int i = 0; i < w.save.customRoster.length; i++)
        {
            int foundHighs = 0;
            int foundMids = 0;
            int foundLows = 0;
            Boolean compatible = true;
            Chosen subject = w.save.customRoster[i];
            if(subject.morality > 66)
            {
                foundHighs++;
                if(c.morality > 66)
                    compatible = false;
            } else
            if(subject.morality > 33)
            {
                foundMids++;
                if(c.morality > 33 && c.morality < 67)
                    compatible = false;
            } else
            {
                foundLows++;
                if(c.morality < 34)
                    compatible = false;
            }
            if(subject.innocence > 66)
            {
                foundHighs++;
                if(c.innocence > 66)
                    compatible = false;
            } else
            if(subject.innocence > 33)
            {
                foundMids++;
                if(c.innocence > 33 && c.innocence < 67)
                    compatible = false;
            } else
            {
                foundLows++;
                if(c.innocence < 34)
                    compatible = false;
            }
            if(subject.confidence > 66)
            {
                foundHighs++;
                if(c.confidence > 66)
                    compatible = false;
            } else
            if(subject.confidence > 33)
            {
                foundMids++;
                if(c.confidence > 33 && c.confidence < 67)
                    compatible = false;
            } else
            {
                foundLows++;
                if(c.confidence < 34)
                    compatible = false;
            }
            if(subject.dignity > 66)
            {
                foundHighs++;
                if(c.dignity > 66)
                    compatible = false;
            } else
            if(subject.dignity > 33)
            {
                foundMids++;
                if(c.dignity > 33 && c.dignity < 67)
                    compatible = false;
            } else
            {
                foundLows++;
                if(c.dignity < 34)
                    compatible = false;
            }
            if(highs == 2 && foundHighs == 2 || mids == 2 && foundMids == 2 || lows == 2 && foundLows == 2)
                compatible = false;
            if(compatible)
            {
                found++;
                w.append(t, "\n " + subject.mainName);
            }
        }

        if(found == 0)
            w.append(t, "\nNone");
        w.append(t, "\n\nYou can adjust Vulnerability Breaks and cosmetic details here.  Note that going back to the previous screen will reset these to their default values.");
        JButton Breaks = new JButton("Vulnerability Breaks");
        Breaks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.SingleVulnerabilities(t, p, f, w, c, answers, 0);
            }
        });
        p.add(Breaks);
        JButton Cosmetics = new JButton("Cosmetics");
        Cosmetics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.SingleNameAndClothes(t, p, f, w, c, answers, 0);
            }
        });
        p.add(Cosmetics);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                c.mainName = null;
                if(answers == null)
                    Project.SingleCustom(t, p, f, w, c, null);
                else
                    Project.CustomQuiz(t, p, f, w, c, 31, answers);
            }
        });
        p.add(Back);
        JButton Finish = new JButton("Finish");
        Finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                w.append(t, "\n\n" + w.getSeparator() + "\n\nThis will add the Chosen to your save file's custom roster, and you will not be able to make further adjustments.");
                JButton Cancel = new JButton("Back");
                Cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.SingleCosmetics(t, p, f, w, c, answers);
                    }
                });
                p.add(Cancel);
                JButton Done = new JButton("Done");
                Done.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Chosen newRoster[] = new Chosen[w.save.customRoster.length + 1];
                        for(int i = 0; i < w.save.customRoster.length; i++)
                            newRoster[i] = w.save.customRoster[i];

                        newRoster[w.save.customRoster.length] = c;
                        w.save.customRoster = newRoster;
                        WriteObject wobj = new WriteObject();
                        wobj.serializeSaveData(w.save);
                        Boolean included[] = new Boolean[w.save.customRoster.length];
                        for(int i = 0; i < included.length; i++)
                            included[i] = true;

                        Project.CampaignMenu(t, p, f, w, included);
                    }
                });
                p.add(Done);
                p.validate();
                p.repaint();
            }
        });
        p.add(Finish);
        p.validate();
        p.repaint();
    }

    public static void SingleNameAndClothes(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Chosen c, final int answers[], final int progress)
    {
        p.removeAll();
        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
        if(progress == 0)
        {
            if(c.mainName.equals(c.givenName) || c.mainName.equals(c.familyName))
                w.append(t, String.format("The first step is to decide what %1$s", c.givenName) + " will call " + c.himHer() + "self.  " + c.HeShe() + "'s currently just going by '" + c.mainName + "', but most Chosen pick an alias for themselves.  Which should " + c.heShe() + " choose?");
            else
                w.append(t, String.format("The first step is to decide what %1$s", c.givenName) + " will call " + c.himHer() + "self.  " + c.HeShe() + " likes the sound of '" + c.mainName + "', but the civilian identities of the Chosen are a matter of public record, so it wouldn't be too strange for " + c.himHer() + " to go by " + c.hisHer() + " real name.  Which should " + c.heShe() + " choose?");
            if(!c.mainName.equals(c.givenName))
            {
                JButton CurrentName = new JButton(c.mainName);
                CurrentName.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 1);
                    }
                });
                p.add(CurrentName);
            }
            JButton GivenName = new JButton(c.givenName);
            GivenName.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    c.mainName = c.givenName;
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 1);
                }
            });
            p.add(GivenName);
            if(!c.mainName.equals(c.familyName) && c.familyName.length() > 0)
            {
                JButton FamilyName = new JButton(c.familyName);
                FamilyName.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        c.mainName = c.familyName;
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 1);
                    }
                });
                p.add(FamilyName);
            }
            JButton SomethingElse = new JButton("Something Else");
            SomethingElse.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String input = JOptionPane.showInputDialog("Type the name to be used here.  Leave blank to continue using '" + c.mainName + "'.");
                    if(input != null && input.length() > 0)
                        c.mainName = input;
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 1);
                }
            });
            p.add(SomethingElse);
        } else
        if(progress == 1)
        {
            if(c.nounName.length() > 0)
                w.append(t, String.format("Most Chosen also use a descriptive title that defines how they see themselves.  %1$s", c.mainName) + "'s first idea is to use '" + c.adjectiveName + " " + c.nounName + "', so that " + c.heShe() + "'d be '" + c.adjectiveName + " " + c.nounName + " " + c.mainName + "'.  Should " + c.heShe() + " use something different?");
            else
            if(c.adjectiveName.equals("none"))
                w.append(t, String.format("Most Chosen also use a descriptive title that defines how they see themselves.  However, none immediately comes to %1$s", c.mainName) + ".  Should " + c.heShe() + " use one at all?");
            else
                w.append(t, String.format("Most Chosen also use a descriptive title that defines how they see themselves.  %1$s", c.mainName) + "'s first idea is to use '" + c.adjectiveName + "', so that " + c.heShe() + "'d be '" + c.adjectiveName + " " + c.mainName + "'.  Should " + c.heShe() + " use something different?");
            JButton CurrentTitle = new JButton(c.adjectiveName);
            if(c.nounName.length() > 0)
                CurrentTitle.setText(c.adjectiveName + " " + c.nounName);
            CurrentTitle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 2);
                }
            });
            p.add(CurrentTitle);
            JButton SomethingElse = new JButton("Something Else");
            SomethingElse.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    c.nounName = "";
                    String input = JOptionPane.showInputDialog("Type the title to be used here.  Leave blank to just go by '" + c.mainName + "'.");
                    if(input != null)
                        if(input.length() > 0)
                            c.adjectiveName = input;
                        else
                            c.adjectiveName = "none";
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 2);
                }
            });
            p.add(SomethingElse);
        } else
        if(progress == 2)
        {
            w.append(t, "In order to transform into ");
            if(c.adjectiveName.equals("none"))
            {
                if(c.mainName.equals(c.givenName) || c.mainName.equals(c.familyName))
                    w.append(t, String.format("%1$s Chosen form", c.hisHer()));
                else
                    w.append(t, c.mainName);
            } else
            if(c.nounName.length() > 0)
                w.append(t, String.format("%1$s ", c.adjectiveName) + c.nounName + " " + c.mainName);
            else
                w.append(t, String.format("%1$s ", c.adjectiveName) + c.mainName);
            w.append(t, String.format(", %1$s", c.givenName) + " needs to speak an incantation of " + c.hisHer() + " choice.  The first that comes to " + c.hisHer() + " mind is '" + c.incantation + "'.");
            JButton Keep = new JButton("Keep");
            Keep.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 3);
                }
            });
            p.add(Keep);
            JButton Change = new JButton("Change");
            Change.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String input = JOptionPane.showInputDialog("Type the new incantation here.  Leave blank to leave it unchanged.");
                    if(input != null && input.length() > 0)
                        c.incantation = input;
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 3);
                }
            });
            p.add(Change);
        } else
        if(progress == 3)
        {
            String result = c.mainName;
            if(c.nounName.length() > 0)
                result = (new StringBuilder(String.valueOf(c.nounName))).append(" ").append(result).toString();
            if(!c.adjectiveName.equals("none"))
                result = (new StringBuilder(c.adjectiveName)).append(" ").append(result).toString();
            result = (new StringBuilder(String.valueOf(c.incantation))).append("  ").append(result).append(", transform!").toString();
            w.append(t, String.format("%1$s's civilian clothes will disintegrate when ", c.givenName) + c.heShe() + " says '" + result + "'  In their place, garments and equipment woven of psychic energy representing " + c.hisHer() + " true nature will materialize.  Click 'Change' to give " + c.himHer() + " something different, or click the button for the current item to keep it.\n\nFirst off, what does " + c.heShe() + " wear to cover " + c.hisHer() + " chest?");
            JButton Current = new JButton(c.topDesc());
            Current.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 5);
                }
            });
            p.add(Current);
            JButton Change = new JButton("Change");
            Change.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String input = JOptionPane.showInputDialog("Type the name of the garment here.  Leave blank to use '" + c.topDesc() + "'.");
                    Boolean changed = false;
                    if(input != null && !input.equals(c.topDesc()) && input.length() > 0)
                        changed = true;
                    if(changed)
                    {
                        c.topCover = input;
                        c.accessory = "none";
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 4);
                    } else
                    {
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 5);
                    }
                }
            });
            p.add(Change);
        } else
        if(progress == 4)
        {
            if(c.gender.equals("male"))
                w.append(t, String.format("And in order to get at %1$s", c.hisHer()) + " nipples, does one go up " + c.hisHer() + " " + c.topDesc() + ", into " + c.hisHer() + " " + c.topDesc() + ", down " + c.hisHer() + " " + c.topDesc() + ", or around " + c.hisHer() + " " + c.topDesc() + "?");
            else
                w.append(t, String.format("And in order to get at %1$s", c.hisHer()) + " breasts, does one go up " + c.hisHer() + " " + c.topDesc() + ", into " + c.hisHer() + " " + c.topDesc() + ", down " + c.hisHer() + " " + c.topDesc() + ", or around " + c.hisHer() + " " + c.topDesc() + "?");
            for(int j = 0; j < 4; j++)
            {
                String method = "";
                if(j == 0)
                    method = "up";
                else
                if(j == 1)
                    method = "into";
                else
                if(j == 2)
                    method = "down";
                else
                if(j == 3)
                    method = "around";
                final String finalMethod = method;
                JButton ThisOne = new JButton(method);
                ThisOne.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        c.topAccess = finalMethod;
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 5);
                    }
                });
                p.add(ThisOne);
            }

        } else
        if(progress == 5)
        {
            w.append(t, String.format("Next, what does %1$s", c.heShe()) + " wear to cover " + c.hisHer() + " hips?");
            JButton Current = new JButton(c.bottomDesc());
            Current.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 7);
                }
            });
            p.add(Current);
            JButton Change = new JButton("Change");
            Change.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String input = JOptionPane.showInputDialog("Type the name of the garment here.  Leave blank to use '" + c.bottomDesc() + "'.");
                    Boolean changed = false;
                    if(input != null && !input.equals(c.bottomDesc()) && input.length() > 0)
                        changed = true;
                    if(changed)
                    {
                        c.bottomCover = input;
                        c.accessory = "none";
                        if(!c.underType.equals("none"))
                            c.underType = "panties";
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 6);
                    } else
                    {
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 7);
                    }
                }
            });
            p.add(Change);
        } else
        if(progress == 6)
        {
            if(c.gender.equals("female"))
                w.append(t, String.format("And in order to get at %1$s", c.hisHer()) + " pussy, does one go up " + c.hisHer() + " " + c.bottomDesc() + ", into " + c.hisHer() + " " + c.bottomDesc() + ", down " + c.hisHer() + " " + c.bottomDesc() + ", or around " + c.hisHer() + " " + c.bottomDesc() + "?");
            else
                w.append(t, String.format("And in order to get at %1$s", c.hisHer()) + " penis, does one go up " + c.hisHer() + " " + c.bottomDesc() + ", into " + c.hisHer() + " " + c.bottomDesc() + ", down " + c.hisHer() + " " + c.bottomDesc() + ", or around " + c.hisHer() + " " + c.bottomDesc() + "?");
            for(int j = 0; j < 4; j++)
            {
                String method = "";
                if(j == 0)
                    method = "up";
                else
                if(j == 1)
                    method = "into";
                else
                if(j == 2)
                    method = "down";
                else
                if(j == 3)
                    method = "around";
                final String finalMethod = method;
                JButton ThisOne = new JButton(method);
                ThisOne.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        c.bottomAccess = finalMethod;
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 7);
                    }
                });
                p.add(ThisOne);
            }

        } else
        if(progress == 7)
        {
            w.append(t, String.format("What footwear does %1$s", c.mainName) + "'s transformation give " + c.himHer() + "?  Enter 'none' (without the quotes) to have " + c.himHer() + " go barefoot.");
            JButton Current = new JButton(c.feetType);
            Current.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 8);
                }
            });
            p.add(Current);
            JButton Change = new JButton("Change");
            Change.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String input = JOptionPane.showInputDialog("Type the name of the garment here.  Leave blank to use '" + c.feetType + "'.");
                    if(input != null && input.length() > 0)
                    {
                        if(!c.feetType.equals(input))
                            c.accessory = "none";
                        c.feetType = input;
                    }
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 8);
                }
            });
            p.add(Change);
        } else
        if(progress == 8)
        {
            w.append(t, String.format("When %1$s", c.heShe()) + "'s transformed, " + c.mainName + " is surrounded by '" + c.color + "' light.  Is this alright?");
            JButton Keep = new JButton("Yes");
            Keep.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 9);
                }
            });
            p.add(Keep);
            JButton Change = new JButton("Change");
            Change.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String input = JOptionPane.showInputDialog("Type the light description to be used.  Leave blank to use '" + c.color + "'.");
                    if(input != null && input.length() > 0)
                    {
                        if(!c.color.equals(input))
                            c.accessory = "none";
                        c.color = input;
                    }
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 9);
                }
            });
            p.add(Change);
        } else
        if(progress == 9)
        {
            w.append(t, String.format("Currently, %1$s", c.mainName) + " is set to fight using " + c.hisHer() + " " + c.weapon + ".  Is this okay?");
            JButton Keep = new JButton("Yes");
            Keep.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 11);
                }
            });
            p.add(Keep);
            JButton Change = new JButton("Change");
            Change.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Boolean changed = false;
                    String input = JOptionPane.showInputDialog("Type the name of the weapon to be used.  Leave blank to use '" + c.weapon + "'.");
                    if(input != null && input.length() > 0)
                    {
                        if(!c.weapon.equals(input))
                        {
                            c.accessory = "none";
                            changed = true;
                        }
                        c.weapon = input;
                    }
                    if(changed)
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 10);
                    else
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 11);
                }
            });
            p.add(Change);
        } else
        if(progress == 10)
        {
            w.append(t, String.format("Does %1$s", c.mainName) + " swing " + c.hisHer() + " " + c.weapon + ", shoot " + c.hisHer() + " " + c.weapon + ", command " + c.hisHer() + " " + c.weapon + ", or is " + c.hisHer() + " weapon a part of " + c.himHer() + "?");
            for(int j = 0; j < 4; j++)
            {
                String method = "";
                if(j == 0)
                    method = "swing";
                else
                if(j == 1)
                    method = "shoot";
                else
                if(j == 2)
                    method = "command";
                else
                if(j == 3)
                    method = (new StringBuilder("part of ")).append(c.himHer()).toString();
                JButton ThisOne = new JButton(method);
                if(method.contains("part"))
                    method = "part";
                final String finalMethod = method;
                ThisOne.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        c.customWeaponType = finalMethod;
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 11);
                    }
                });
                p.add(ThisOne);
            }

        } else
        if(progress == 11)
        {
            w.append(t, "There's one final important question.  ");
            if(c.underType.equals("none"))
            {
                w.append(t, String.format("%1$s's outfit doesn't currently include panties.  Should that be changed?", c.mainName));
                JButton Change = new JButton("Wear panties");
                Change.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        c.underType = "panties";
                        c.accessory = "none";
                        Project.SingleCosmetics(t, p, f, w, c, answers);
                    }
                });
                p.add(Change);
            } else
            {
                w.append(t, String.format("Would you prefer for %1$s", c.mainName) + " to stop wearing anything under " + c.hisHer() + " " + c.bottomDesc() + "?");
                JButton Change = new JButton("Wear nothing");
                Change.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        c.underType = "none";
                        c.accessory = "none";
                        Project.SingleCosmetics(t, p, f, w, c, answers);
                    }
                });
                p.add(Change);
            }
            JButton Keep = new JButton("Leave it as is");
            Keep.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.SingleCosmetics(t, p, f, w, c, answers);
                }
            });
            p.add(Keep);
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(progress - 1 == 4 || progress - 1 == 6 || progress - 1 == 10)
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, progress - 2);
                else
                if(progress == 0)
                    Project.SingleCosmetics(t, p, f, w, c, answers);
                else
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, progress - 1);
            }
        });
        if(progress != 4 && progress != 6 && progress != 10)
            p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void SingleVulnerabilities(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Chosen c, final int answers[], final int progress)
    {
        p.removeAll();
        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
        if(progress == 0)
        {
            if(c.morality > 66)
            {
                w.append(t, String.format("%1$s's sense of Morality is a ", c.givenName));
                w.blueAppend(t, "Core");
                w.append(t, String.format(" part of %1$s", c.hisHer()) + " identity");
            } else
            if(c.morality > 33)
            {
                w.append(t, String.format("%1$s's Morality is a ", c.givenName));
                w.greenAppend(t, "Significant");
                w.append(t, String.format(" component of %1$s", c.hisHer()) + " personality");
            }
            if(c.morality > 33)
            {
                if(c.gender.equals("male"))
                    w.append(t, String.format(", so %1$s", c.heShe()) + " wouldn't normally engage in 'immoral' activities like violence and sex with other men.  ");
                else
                    w.append(t, String.format(", so %1$s", c.heShe()) + " wouldn't normally engage in 'immoral' activities like violence and promiscuity.  ");
                if(c.vVirg)
                    w.append(t, String.format("If this Vulnerability is broken, it means %1$s", c.heShe()) + " has already been raped by the time the game starts.");
                else
                    w.append(t, String.format("However, %1$s", c.heShe()) + " is set to have already been raped before the game starts.");
            } else
            {
                w.append(t, "Morality is only a ");
                w.redAppend(t, "Minor");
                w.append(t, String.format(" concern for %1$s", c.givenName) + ", ");
                if(c.vVirg)
                {
                    if(c.gender.equals("male"))
                        w.append(t, String.format("but %1$s", c.heShe()) + " hasn't gotten curious enough to try having sex with other men yet.  Break this Vulnerability to change that.");
                    else
                        w.append(t, String.format("but %1$s", c.heShe()) + " hasn't gotten around to having sex yet.  Break this Vulnerability to change that.");
                } else
                if(c.gender.equals("male"))
                    w.append(t, String.format("so %1$s", c.heShe()) + " has seen no reason to avoid having sex with other men.  Restore this Vulnerability to have " + c.himHer() + " start as an anal virgin instead.");
                else
                    w.append(t, String.format("so %1$s", c.heShe()) + " has seen no reason to avoid having sex.  Restore this Vulnerability to have " + c.himHer() + " start as a virgin instead.");
            }
        } else
        if(progress == 1)
        {
            if(c.innocence > 66)
            {
                w.append(t, String.format("%1$s's Innocence is a ", c.givenName));
                w.blueAppend(t, "Core");
                w.append(t, String.format(" part of %1$s", c.hisHer()) + " identity");
            } else
            if(c.innocence > 33)
            {
                w.append(t, String.format("%1$s has retained ", c.givenName));
                w.greenAppend(t, "Significant");
                w.append(t, " Innocence regarding sexual matters");
            }
            if(c.innocence > 33)
            {
                w.append(t, String.format(", so %1$s", c.heShe()) + " wouldn't normally have any idea how good it can feel to be forced to cum during battle.  ");
                if(c.cVirg)
                    w.append(t, String.format("If this Vulnerability is broken, it means %1$s", c.heShe()) + " has already become addicted to this feeling.");
                else
                    w.append(t, String.format("However, %1$s", c.heShe()) + " is set to have already become addicted to this feeling when the game starts.");
            } else
            {
                w.append(t, String.format("%1$s has retained only a ", c.givenName));
                w.redAppend(t, "Minor");
                w.append(t, " amount of Innocence");
                if(c.cVirg)
                    w.append(t, String.format(", but %1$s", c.heShe()) + "'s still sane enough to hold back from cumming during battle.  Break this Vulnerability to change that.");
                else
                    w.append(t, String.format(", so %1$s", c.heShe()) + " happily allows " + c.himHer() + "self to cum during battle.  Restore this Vulnerability to have " + c.himHer() + " start out with some restraint.");
            }
        } else
        if(progress == 2)
        {
            if(c.confidence > 66)
            {
                w.append(t, String.format("%1$s's Confidence is a ", c.givenName));
                w.blueAppend(t, "Core");
                w.append(t, String.format(" part of %1$s", c.hisHer()) + " identity");
            } else
            if(c.confidence > 33)
            {
                w.append(t, String.format("%1$s has a ", c.givenName));
                w.greenAppend(t, "Significant");
                w.append(t, " amount of Confidence");
            }
            if(c.confidence > 33)
            {
                w.append(t, String.format(" because of %1$s", c.hisHer()) + " past victories against the Demons.  ");
                if(c.aVirg)
                    w.append(t, String.format("If this Vulnerability is broken, it means %1$s", c.heShe()) + " has suffered a crushing defeat and been tortured before.");
                else
                    w.append(t, String.format("However, %1$s", c.heShe()) + " is set to have already had " + c.hisHer() + " self-image shaken by being defeated and tortured recently.");
            } else
            {
                w.append(t, String.format("%1$s has only a ", c.givenName));
                w.redAppend(t, "Minor");
                w.append(t, " amount of Confidence left");
                if(c.aVirg)
                    w.append(t, String.format(", but this is due purely to %1$s", c.hisHer()) + " weak personality.  Break this Vulnerability to have " + c.hisHer() + " self-esteem damaged by a recent capture and torture.");
                else
                    w.append(t, String.format(", largely because of %1$s", c.hisHer()) + " recent defeat and torture at the hands of the Demons.  Restore this Vulnerability to erase this event and let " + c.himHer() + " start out with at least a little bit of hope.");
            }
        } else
        if(progress == 3)
        {
            if(c.dignity > 66)
            {
                w.append(t, String.format("%1$s's need for Dignity is a ", c.givenName));
                w.blueAppend(t, "Core");
                w.append(t, String.format(" part of %1$s", c.hisHer()) + " identity");
            } else
            if(c.dignity > 33)
            {
                w.append(t, String.format("%1$s maintains a ", c.givenName));
                w.greenAppend(t, "Significant");
                w.append(t, " amount of Dignity");
            }
            if(c.dignity > 33)
            {
                w.append(t, ", wanting to be viewed as a mighty, unassailable warrior.  ");
                if(c.modest)
                    w.append(t, String.format("If this Vulnerability is broken, it means %1$s", c.heShe()) + " has been stripped and had " + c.hisHer() + " humiliation broadcast to the world.");
                else
                    w.append(t, String.format("However, %1$s", c.heShe()) + " is set to have already had " + c.hisHer() + " public image damaged by being stripped during battle and having the footage broadcast to the world.");
            } else
            {
                w.append(t, String.format("%1$s has only a ", c.givenName));
                w.redAppend(t, "Minor");
                w.append(t, String.format(" interest in retaining %1$s", c.hisHer()) + " dignity");
                if(c.modest)
                    w.append(t, String.format(", but %1$s", c.heShe()) + " has managed to avoid any public humiliation so far, mostly through pure luck.  Break this Vulnerability to have footage of " + c.hisHer() + " defeat and stripping be broadcast to the world.");
                else
                    w.append(t, String.format(", and as a result, %1$s", c.heShe()) + " hasn't managed to stop footage of " + c.hisHer() + " defeat and stripping from being spread across the world.  Restore this Vulnerability to make it so that " + c.heShe() + " hasn't yet been captured in such a shameful state.");
            }
        }
        if(progress == 0 && c.vVirg || progress == 1 && c.cVirg || progress == 2 && c.aVirg || progress == 3 && c.modest)
        {
            JButton Break = new JButton("Break");
            Break.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(progress == 0)
                    {
                        c.vVirg = false;
                        c.ruthless = true;
                        c.vTaker = 0;
                        c.vStart = false;
                    } else
                    if(progress == 1)
                    {
                        c.cVirg = false;
                        c.lustful = true;
                        c.cTaker = 0;
                        c.cStart = false;
                    } else
                    if(progress == 2)
                    {
                        c.aVirg = false;
                        c.meek = true;
                        c.aTaker = 0;
                        c.aStart = false;
                    } else
                    {
                        c.modest = false;
                        c.debased = true;
                        c.mTaker = 0;
                        c.mStart = false;
                    }
                    if(progress < 3)
                        Project.SingleVulnerabilities(t, p, f, w, c, answers, progress + 1);
                    else
                        Project.SingleCosmetics(t, p, f, w, c, answers);
                }
            });
            p.add(Break);
        } else
        {
            JButton Restore = new JButton("Restore");
            Restore.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(progress == 0)
                    {
                        c.vVirg = true;
                        c.ruthless = false;
                        c.vTaker = -1;
                        c.vStart = true;
                    } else
                    if(progress == 1)
                    {
                        c.cVirg = true;
                        c.lustful = false;
                        c.cTaker = -1;
                        c.cStart = true;
                    } else
                    if(progress == 2)
                    {
                        c.aVirg = true;
                        c.meek = false;
                        c.aTaker = -1;
                        c.aStart = true;
                    } else
                    {
                        c.modest = true;
                        c.debased = false;
                        c.mTaker = -1;
                        c.mStart = true;
                    }
                    if(progress < 3)
                        Project.SingleVulnerabilities(t, p, f, w, c, answers, progress + 1);
                    else
                        Project.SingleCosmetics(t, p, f, w, c, answers);
                }
            });
            p.add(Restore);
        }
        JButton Continue = new JButton("Continue");
        Continue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(progress < 3)
                    Project.SingleVulnerabilities(t, p, f, w, c, answers, progress + 1);
                else
                    Project.SingleCosmetics(t, p, f, w, c, answers);
            }
        });
        p.add(Continue);
        p.validate();
        p.repaint();
    }

    public static void ReportCustomInclusion(JTextPane t, WorldState w, Boolean enabled[])
    {
        w.append(t, "\n\nCustom Chosen:");
        for(int i = 0; i < w.save.customRoster.length; i++)
            if(enabled[i])
            {
                w.append(t, "\n" + w.save.customRoster[i].mainName + ": ");
                w.greenAppend(t, "INCLUDED");
            } else
            {
                w.grayAppend(t, "\n" + w.save.customRoster[i].mainName + ": ");
                w.redAppend(t, "EXCLUDED");
            }

    }

    public static void CampaignCustomToggle(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Boolean enabled[], final int page, final Boolean deleting)
    {
        p.removeAll();
        clearPortraits();
        String shownNames[] = new String[5];
        if(page > 0)
        {
            JButton Previous = new JButton("<");
            Previous.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.CampaignCustomToggle(t, p, f, w, enabled, page - 1, deleting);
                }
            });
            p.add(Previous);
        }
        for(int i = 0; i < 5; i++)
            if(i + page * 5 < enabled.length)
            {
                final int index = i + page * 5;
                shownNames[i] = w.save.customRoster[index].mainName;
                changePortrait(w.save.customRoster[index].convertGender(), w.save.customRoster[index].type, false, false, w, shownNames, i, Emotion.NEUTRAL, Emotion.NEUTRAL);
                JButton ThisOne = new JButton(w.save.customRoster[i + page * 5].mainName);
                ThisOne.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        if(deleting)
                        {
                            p.removeAll();
                            w.append(t, "\n\n" + w.getSeparator() + "\n\nReally delete " + w.save.customRoster[index].mainName + "?");
                            JButton Delete = new JButton("Delete");
                            Delete.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    Boolean newEnabled[] = new Boolean[enabled.length - 1];
                                    Chosen newRoster[] = new Chosen[w.save.customRoster.length - 1];
                                    for(int j = 0; j < newRoster.length; j++)
                                        if(j < index)
                                        {
                                            newEnabled[j] = enabled[j];
                                            newRoster[j] = w.save.customRoster[j];
                                        } else
                                        {
                                            newEnabled[j] = enabled[j + 1];
                                            newRoster[j] = w.save.customRoster[j + 1];
                                        }

                                    w.save.customRoster = newRoster;
                                    if(index == newEnabled.length && newEnabled.length % 5 == 0 && page != 0)
                                        Project.CampaignCustomToggle(t, p, f, w, newEnabled, page - 1, true);
                                    else
                                        Project.CampaignCustomToggle(t, p, f, w, newEnabled, page, true);
                                    w.append(t, "\n\n" + w.getSeparator());
                                    Project.ReportCustomInclusion(t, w, newEnabled);
                                }
                            });
                            p.add(Delete);
                            JButton Back = new JButton("Back");
                            Back.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.CampaignCustomToggle(t, p, f, w, enabled, page, true);
                                }
                            });
                            p.add(Back);
                            p.validate();
                            p.repaint();
                        } else
                        {
                            enabled[index] = Boolean.valueOf(!enabled[index]);
                            w.append(t, "\n\n" + w.getSeparator());
                            Project.ReportCustomInclusion(t, w, enabled);
                        }
                    }
                });
                p.add(ThisOne);
            }

        if((page + 1) * 5 < enabled.length)
        {
            JButton Next = new JButton(">");
            Next.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.CampaignCustomToggle(t, p, f, w, enabled, page + 1, deleting);
                }
            });
            p.add(Next);
        }
        JButton Done = new JButton("Done");
        Done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.CampaignMenu(t, p, f, w, enabled);
            }
        });
        p.add(Done);
        p.validate();
        p.repaint();
        t.setCaretPosition(t.getDocument().getLength() - 1);
    }

    public static void OptionsDisplay(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, Boolean earlyCheatVisible)
    {
        t.setText("");
        if(earlyCheatVisible)
        {
            w.append(t, "Difficulty: ");
            if(w.getEarlyCheat())
                w.append(t, "EASY (cheats available from the start)");
            else
            if(w.hardMode)
                w.append(t, "HARD (shorter deadlines, final Chosen take less damage as damage level goes up, cannot use Forsaken)");
            else
                w.append(t, "NORMAL");
            w.append(t, "\n\n");
        }
        w.append(t, "Current background: ");
        if(t.getBackground().equals(Color.WHITE))
            w.append(t, "white");
        else
            w.append(t, "black");
        w.append(t, "\n\nCommentary mode: ");
        if(w.getCommentaryRead())
        {
            if(w.getCommentaryWrite())
                w.append(t, "Read/Write");
            else
                w.append(t, "Read");
        } else
        if(w.getCommentaryWrite())
            w.append(t, "Write");
        else
            w.append(t, "None");
        w.append(t, "\n\nText size: " + w.getTextSize());
        w.append(t, "\n\nEnemy composition: ");
        if(w.getGenderBalance()[0] == 0)
        {
            Boolean listed = false;
            if(w.getGenderBalance()[1] > 0)
            {
                listed = true;
                w.append(t, String.valueOf(w.getGenderBalance()[1]) + " female");
                if(w.getGenderBalance()[1] > 1)
                    w.append(t, "s");
            }
            if(w.getGenderBalance()[2] > 0)
            {
                if(listed)
                    w.append(t, ", ");
                w.append(t, String.valueOf(w.getGenderBalance()[2]) + " male");
                if(w.getGenderBalance()[2] > 1)
                    w.append(t, "s");
                listed = true;
            }
            if(w.getGenderBalance()[3] > 0)
            {
                if(listed)
                    w.append(t, ", ");
                w.append(t, String.valueOf(w.getGenderBalance()[3]) + " futanari");
                listed = true;
            }
            if(!listed)
                w.append(t, "none set");
        } else
        {
            Boolean listed = false;
            int divisor = w.getGenderBalance()[1] + w.getGenderBalance()[2] + w.getGenderBalance()[3];
            if(divisor == 0)
                divisor = 1;
            int count = 0;
            for(int i = 1; i < 4; i++)
                if(w.getGenderBalance()[i] > 0)
                    count++;

            int multiplier = 10000 / divisor;
            if(w.getGenderBalance()[1] > 0)
            {
                listed = true;
                if(count > 1)
                    w.append(t, String.valueOf((multiplier * w.getGenderBalance()[1]) / 100) + "% female");
                else
                    w.append(t, "100% female");
            }
            if(w.getGenderBalance()[2] > 0)
            {
                if(listed)
                    w.append(t, ", ");
                if(count > 1)
                    w.append(t, String.valueOf((multiplier * w.getGenderBalance()[2]) / 100) + "% male");
                else
                    w.append(t, "100% male");
                listed = true;
            }
            if(w.getGenderBalance()[3] > 0)
            {
                if(listed)
                    w.append(t, ", ");
                if(count > 1)
                    w.append(t, String.valueOf((multiplier * w.getGenderBalance()[3]) / 100) + "% futanari");
                else
                    w.append(t, "100% futanari");
            }
        }
        if(w.getGenderBalance()[2] > 0)
        {
            w.append(t, "\n\nMales shift: ");
            if(w.getMaleShift() == 0)
                w.append(t, "never");
            else
            if(w.getMaleShift() == 1)
                w.append(t, "to female when first inseminated");
            else
            if(w.getMaleShift() == 2)
                w.append(t, "to futanari when first inseminated");
        }
        if(w.getGenderBalance()[1] > 0 || w.getGenderBalance()[2] > 0 && w.getMaleShift() == 1)
        {
            w.append(t, "\n\nFemales shift: ");
            if(w.getFemaleShift() == 0)
                w.append(t, "never");
            else
                w.append(t, "to futanari when first using Fantasize");
        }
        if(w.getMaleShift() > 0 || w.getFemaleShift() > 0)
        {
            w.append(t, "\n\nShifted Chosen can shift again: ");
            if(w.getRepeatShift())
                w.append(t, "yes");
            else
                w.append(t, "no");
        }
        w.append(t, "\n\nGraphic violence: ");
        if(w.tickle())
            w.append(t, "OFF (replaced by tickling)");
        else
            w.append(t, "ON");
        w.append(t, "\n\nPortraits: ");
        if(w.portraits)
            w.append(t, "ON");
        else
            w.append(t, "OFF");
        w.append(t, "\n\nPassage separator:\n" + w.getSeparator());
    }

    public static void OptionsMenu(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, Boolean earlyCheatVisible)
    {
        p.removeAll();
        if(earlyCheatVisible == null)
        {
            String path = Project.class.getProtectionDomain().getCodeSource().getLocation().getPath();
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
            path = path.replaceAll(File.separator + "u0020", File.separator + " ");
            File saveLocation = new File(path + File.separator + "saves.sav");
            SaveData saves = null;
            if(saveLocation.exists())
            {
                ReadObject robj = new ReadObject();
                saves = robj.deserializeSaveData(path + File.separator + "saves.sav");
            } else
            {
                saves = new SaveData();
            }
            SaveData saveFile = saves;
            for(int i = 0; i < saveFile.getSaves().length; i++)
                if(saveFile.getSaves()[i].getDay() > 50 - saveFile.getSaves()[i].eventOffset * 3)
                    earlyCheatVisible = true;

            if(w.getEarlyCheat())
                earlyCheatVisible = true;
            if(w.hardMode)
                earlyCheatVisible = true;
            if(saves.harem != null && saves.harem.length > 0)
                earlyCheatVisible = true;
            if(earlyCheatVisible == null)
                earlyCheatVisible = false;
        }
        final Boolean CheatVisibility = earlyCheatVisible;
        OptionsDisplay(t, p, f, w, earlyCheatVisible);
        JButton EarlyCheat = new JButton("Change Difficulty");
        EarlyCheat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(w.getEarlyCheat())
                {
                    w.setEarlyCheat(false);
                    w.hardMode = true;
                    w.clampStart = 1;
                    w.clampPercent = 80;
                    w.eventOffset = 5;
                } else
                if(w.hardMode)
                {
                    w.hardMode = false;
                    w.clampStart = 11;
                    w.clampPercent = 100;
                    w.eventOffset = 0;
                } else
                {
                    w.setEarlyCheat(true);
                }
                Project.OptionsMenu(t, p, f, w, null);
            }
        });
        if(earlyCheatVisible)
            p.add(EarlyCheat);
        class EarlyCheatAction extends AbstractAction
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.setEarlyCheat(true);
                Project.OptionsMenu(t, p, f, w, true);
            }
        }

        Action EarlyCheatAssignment = new EarlyCheatAction();
        p.getInputMap(2).put(KeyStroke.getKeyStroke(67, 0), "pressed");
        p.getActionMap().put("pressed", EarlyCheatAssignment);
        JButton Invert = new JButton("Change Background");
        Invert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.toggleColors(t);
                Project.OptionsMenu(t, p, f, w, CheatVisibility);
            }
        });
        p.add(Invert);
        JButton Commentary = new JButton("Change Commentary Mode");
        Commentary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(w.getCommentaryRead())
                {
                    if(w.getCommentaryWrite())
                    {
                        w.setCommentaryWrite(false);
                    } else
                    {
                        w.setCommentaryRead(false);
                        w.setCommentaryWrite(true);
                    }
                } else
                if(w.getCommentaryWrite())
                {
                    w.setCommentaryWrite(false);
                } else
                {
                    w.setCommentaryRead(true);
                    w.setCommentaryWrite(true);
                }
                Project.OptionsMenu(t, p, f, w, CheatVisibility);
            }
        });
        p.add(Commentary);
        JButton TextSize = new JButton("Change Text Size");
        TextSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.switchTextSize();
                Project.OptionsMenu(t, p, f, w, CheatVisibility);
            }
        });
        p.add(TextSize);
        JButton Content = new JButton("Content Options");
        Content.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.ContentMenu(t, p, f, w, CheatVisibility);
            }
        });
        p.add(Content);
        JButton Portraits = new JButton("Toggle Portraits");
        Portraits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.portraits = Boolean.valueOf(!w.portraits);
                Project.OptionsMenu(t, p, f, w, CheatVisibility);
            }
        });
        p.add(Portraits);
        JButton ChangeSeparator = new JButton("Change Separator");
        ChangeSeparator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String input = JOptionPane.showInputDialog("Enter the text that will be used to separate passages.  Leave blank to use the default, '---'.");
                if(input == null)
                    w.setSeparator("---");
                else
                if(input.length() == 0)
                    w.setSeparator("---");
                else
                    w.setSeparator(input);
                Project.OptionsMenu(t, p, f, w, CheatVisibility);
            }
        });
        p.add(ChangeSeparator);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                t.setText("");
                Project.IntroOne(t, p, f, w);
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void GenderMenu(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Boolean earlyCheatVisible)
    {
        p.removeAll();
        p.getInputMap().clear();
        p.getActionMap().clear();
        OptionsDisplay(t, p, f, w, earlyCheatVisible);
        JButton ToggleRandomness = new JButton("Randomize Composition");
        if(w.getGenderBalance()[0] == 1)
            ToggleRandomness.setText("Fix Composition");
        ToggleRandomness.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.toggleGenderRandomness();
                Project.GenderMenu(t, p, f, w, earlyCheatVisible);
            }
        });
        p.add(ToggleRandomness);
        JButton FewerFemales = new JButton("Fewer Females");
        if(w.getGenderBalance()[1] > 0 && (w.getGenderBalance()[0] == 0 || w.getGenderBalance()[2] > 0 || w.getGenderBalance()[3] > 0))
            FewerFemales.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.decreaseGender(1);
                    Project.GenderMenu(t, p, f, w, earlyCheatVisible);
                }
            });
        else
            FewerFemales.setForeground(Color.GRAY);
        p.add(FewerFemales);
        JButton MoreFemales = new JButton("More Females");
        if(w.getGenderBalance()[0] == 1 && (w.getGenderBalance()[2] > 0 || w.getGenderBalance()[3] > 0) || w.getGenderBalance()[0] == 0 && w.getGenderBalance()[1] + w.getGenderBalance()[2] + w.getGenderBalance()[3] < 3)
            MoreFemales.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.increaseGender(1);
                    Project.GenderMenu(t, p, f, w, earlyCheatVisible);
                }
            });
        else
            MoreFemales.setForeground(Color.GRAY);
        p.add(MoreFemales);
        JButton FewerMales = new JButton("Fewer Males");
        if(w.getGenderBalance()[2] > 0 && (w.getGenderBalance()[0] == 0 || w.getGenderBalance()[1] > 0 || w.getGenderBalance()[3] > 0))
            FewerMales.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.decreaseGender(2);
                    Project.GenderMenu(t, p, f, w, earlyCheatVisible);
                }
            });
        else
            FewerMales.setForeground(Color.GRAY);
        p.add(FewerMales);
        JButton MoreMales = new JButton("More Males");
        if(w.getGenderBalance()[0] == 1 && (w.getGenderBalance()[1] > 0 || w.getGenderBalance()[3] > 0) || w.getGenderBalance()[0] == 0 && w.getGenderBalance()[1] + w.getGenderBalance()[2] + w.getGenderBalance()[3] < 3)
            MoreMales.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.increaseGender(2);
                    Project.GenderMenu(t, p, f, w, earlyCheatVisible);
                }
            });
        else
            MoreMales.setForeground(Color.GRAY);
        p.add(MoreMales);
        JButton FewerFuta = new JButton("Fewer Futanari");
        if(w.getGenderBalance()[3] > 0 && (w.getGenderBalance()[0] == 0 || w.getGenderBalance()[1] > 0 || w.getGenderBalance()[2] > 0))
            FewerFuta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.decreaseGender(3);
                    Project.GenderMenu(t, p, f, w, earlyCheatVisible);
                }
            });
        else
            FewerFuta.setForeground(Color.GRAY);
        p.add(FewerFuta);
        JButton MoreFuta = new JButton("More Futanari");
        if(w.getGenderBalance()[0] == 1 && (w.getGenderBalance()[2] > 0 || w.getGenderBalance()[1] > 0) || w.getGenderBalance()[0] == 0 && w.getGenderBalance()[1] + w.getGenderBalance()[2] + w.getGenderBalance()[3] < 3)
            MoreFuta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.increaseGender(3);
                    Project.GenderMenu(t, p, f, w, earlyCheatVisible);
                }
            });
        else
            MoreFuta.setForeground(Color.GRAY);
        p.add(MoreFuta);
        JButton Back = new JButton("Back");
        if(w.getGenderBalance()[0] == 1 || w.getGenderBalance()[1] + w.getGenderBalance()[2] + w.getGenderBalance()[3] == 3)
            Back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.ContentMenu(t, p, f, w, earlyCheatVisible);
                }
            });
        else
            Back.setForeground(Color.GRAY);
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void ContentMenu(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Boolean earlyCheatVisible)
    {
        p.removeAll();
        p.getInputMap().clear();
        p.getActionMap().clear();
        OptionsDisplay(t, p, f, w, earlyCheatVisible);
        JButton Violence = new JButton("Toggle Violence");
        Violence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.toggleTickle();
                Project.ContentMenu(t, p, f, w, earlyCheatVisible);
            }
        });
        p.add(Violence);
        JButton Genders = new JButton("Change Composition");
        Genders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.GenderMenu(t, p, f, w, earlyCheatVisible);
            }
        });
        p.add(Genders);
        if(w.getGenderBalance()[2] > 0)
        {
            JButton MaleShift = new JButton("Toggle Male Shifting");
            MaleShift.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.changeMaleShift();
                    Project.ContentMenu(t, p, f, w, earlyCheatVisible);
                }
            });
            p.add(MaleShift);
        }
        if(w.getGenderBalance()[1] > 0 || w.getGenderBalance()[2] > 0 && w.getMaleShift() == 1)
        {
            JButton FemaleShift = new JButton("Toggle Female Shifting");
            FemaleShift.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.changeFemaleShift();
                    Project.ContentMenu(t, p, f, w, earlyCheatVisible);
                }
            });
            p.add(FemaleShift);
        }
        if(w.getMaleShift() > 0 || w.getFemaleShift() > 0)
        {
            JButton RepeatShift = new JButton("Toggle Repeated Shifting");
            RepeatShift.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.changeRepeatShift();
                    Project.ContentMenu(t, p, f, w, earlyCheatVisible);
                }
            });
            p.add(RepeatShift);
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.OptionsMenu(t, p, f, w, earlyCheatVisible);
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void ForsakenMenu(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s, final int page)
    {
        p.removeAll();
        if(page == 0)
        {
            if(w.active)
            {
                w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                if(w.usedForsaken != null)
                    w.append(t, String.format("%1$s is currently prepared to lead your forces into battle.  ", w.usedForsaken.mainName));
                if(w.loopComplete)
                {
                    if(w.day <= 50 - 3 * w.eventOffset)
                        w.append(t, "Select one of the Forsaken to see more information and management options, or select 'Pass Time' to move to the next day without doing any training.");
                    else
                        w.append(t, "Select one of the Forsaken to see more information and management options.");
                } else
                {
                    w.append(t, "Select one of the Forsaken to see more information and management options, or select 'Deploy' to use one as your Commander.");
                }
            } else
            {
                w.append(t, "\n\n" + w.getSeparator() + "\n\nWith which of your Forsaken would you like to interact?");
            }
        } else
        {
            clearPortraits();
            JButton PreviousPage = new JButton("<");
            PreviousPage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.ForsakenMenu(t, p, f, w, s, page - 1);
                }
            });
            p.add(PreviousPage);
        }
        clearPortraits();
        String nameDisplay[] = new String[5];
        for(int i = 0; i < 5; i++)
            if(w.getHarem() != null && w.getHarem().length > i + page * 5)
            {
                final Forsaken subject = w.getHarem()[i + page * 5];
                subject.textSize = w.textSize;
                JButton ThisOne = new JButton(subject.mainName);
                ThisOne.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.ForsakenInteraction(t, p, f, w, s, subject);
                    }
                });
                p.add(ThisOne);
                nameDisplay[i] = subject.mainName;
                if(subject.flavorObedience() < 20)
                    changePortrait(subject.gender, subject.type, true, true, w, nameDisplay, i, Emotion.ANGER, Emotion.NEUTRAL);
                else
                if(subject.flavorObedience() < 40)
                    changePortrait(subject.gender, subject.type, true, true, w, nameDisplay, i, Emotion.ANGER, Emotion.SHAME);
                else
                if(subject.flavorObedience() < 61)
                    changePortrait(subject.gender, subject.type, true, true, w, nameDisplay, i, Emotion.FEAR, Emotion.SHAME);
                else
                if(subject.flavorObedience() < 81)
                    changePortrait(subject.gender, subject.type, true, true, w, nameDisplay, i, Emotion.FOCUS, Emotion.NEUTRAL);
                else
                    changePortrait(subject.gender, subject.type, true, true, w, nameDisplay, i, Emotion.JOY, Emotion.FOCUS);
            }

        if(w.getHarem().length > 5 * (page + 1))
        {
            JButton NextPage = new JButton(">");
            NextPage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.ForsakenMenu(t, p, f, w, s, page + 1);
                }
            });
            p.add(NextPage);
        }
        JButton NewForsaken = new JButton("(Generate Forsaken)");
        NewForsaken.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                WriteObject wobj = new WriteObject();
                WorldState dummy = new WorldState();
                dummy.copyToggles(w);
                dummy.copySettings(t, w);
                dummy.setGenders(w.getGenderBalance());
                Chosen newChosen = new Chosen();
                newChosen.setNumber(0);
                dummy.initialize();
                newChosen.generate(dummy);
                w.corruptColors(newChosen);
                int index = 0;
                if(s.harem == null)
                    s.harem = new Forsaken[1];
                else
                    index = s.harem.length;
                int lastPage = s.harem.length / 5;
                Forsaken newHarem[] = new Forsaken[index + 1];
                for(int j = 0; j < index; j++)
                    newHarem[j] = s.harem[j];

                Forsaken newForsaken = new Forsaken();
                newForsaken.initialize(w, newChosen);
                newHarem[index] = newForsaken;
                newForsaken.forsakenID = s.assignID();
                s.harem = newHarem;
                wobj.serializeSaveData(s);
                Project.ForsakenMenu(t, p, f, w, s, lastPage);
            }
        });
        if(!w.campaign)
            p.add(NewForsaken);
        JButton PassTime = new JButton("Pass Time");
        PassTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(w.loopComplete)
                {
                    p.removeAll();
                    w.append(t, "\n\n" + w.getSeparator() + "\n\nDo you want to give your Forsaken a break and just move on to the next day?");
                    JButton Confirm = new JButton("Confirm");
                    Confirm.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            Project.PostBattle(t, p, f, w);
                        }
                    });
                    p.add(Confirm);
                    JButton Cancel = new JButton("Cancel");
                    Cancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            Project.ForsakenMenu(t, p, f, w, s, page);
                        }
                    });
                    p.add(Cancel);
                    p.validate();
                    p.repaint();
                } else
                {
                    Project.ForsakenChoice(t, p, f, w, s, 0);
                }
            }
        });
        if(w.active && !w.loopComplete)
            PassTime.setText("Deploy");
        if(!w.campaign || w.day <= 50 - w.eventOffset * 3)
            p.add(PassTime);
        if(w.active && !w.loopComplete)
        {
            JButton UseDemon = new JButton("Use Demon");
            UseDemon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(w.usedForsaken != null)
                        w.evilEnergy += w.usedForsaken.EECost();
                    w.usedForsaken = null;
                    Project.Customize(t, p, f, w);
                }
            });
            p.add(UseDemon);
        }
        JButton Back = new JButton("Done");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(w.active)
                {
                    Project.Shop(t, p, f, w);
                } else
                {
                    t.setText("");
                    Project.IntroOne(t, p, f, w);
                }
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void ForsakenChoice(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s, final int page)
    {
        p.removeAll();
        if(page == 0)
        {
            if(w.active)
                w.append(t, "\n\n" + w.getSeparator() + "\n\nWhich of the Forsaken would you like to send into battle?");
            else
                w.append(t, "\n\n" + w.getSeparator() + "\n\nYou can spend one of the Forsaken's Stamina and Motivation (as would normally happen when sending them into battle), or you can simply pass time without spending anything by selecting 'None'.");
        } else
        {
            JButton PreviousPage = new JButton("<");
            PreviousPage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, "\n\n" + w.getSeparator());
                    Project.ForsakenChoice(t, p, f, w, s, page - 1);
                }
            });
            p.add(PreviousPage);
        }
        clearPortraits();
        String nameDisplay[] = new String[5];
        for(int i = page * 5; i < page * 5 + 5; i++)
            if(i < w.getHarem().length)
            {
                int actualCost = w.getHarem()[i].motivationCost();
                if(w.getHarem()[i].isFormerFriend(w.getCast()[0]) || w.getHarem()[i].isFormerFriend(w.getCast()[1]) || w.getHarem()[i].isFormerFriend(w.getCast()[2]))
                    actualCost *= 2;
                w.append(t, "\n\n" + w.getHarem()[i].mainName + "\nStamina: " + String.valueOf(w.getHarem()[i].stamina / 10) + "." + String.valueOf(w.getHarem()[i].stamina % 10) + "%\nMotivation: " + String.valueOf(w.getHarem()[i].motivation / 10) + "." + String.valueOf(w.getHarem()[i].motivation % 10) + "%\nCost: 20% Stamina, " + String.valueOf(actualCost / 10) + "." + String.valueOf(actualCost % 10) + "% Motivation, " + w.getHarem()[i].EECost() + " EE\n" + w.getHarem()[i].describeCombatStyle(w, false) + "\nReputation Strength: " + String.valueOf(200 - w.getHarem()[i].disgrace * 2) + "%\nTarget Compatibilities:");
                if(w.active)
                {
                    for(int j = 0; j < 3; j++)
                        if(w.getCast()[j] != null)
                        {
                            w.append(t, "\n" + w.getCast()[j].getMainName() + " - ");
                            int compatibility = w.getHarem()[i].compatibility(w.getCast()[j]);
                            if(w.getHarem()[i].knowsPersonally(w.getCast()[j]))
                                w.append(t, "Personal (8 rounds, +25% damage)");
                            else
                            if(w.getHarem()[i].obsessedWith(w.getCast()[j]))
                                w.append(t, "Obsessed (8 rounds, +25% damage)");
                            else
                            if(compatibility >= 8)
                                w.append(t, "Excellent (8 rounds)");
                            else
                            if(compatibility == 7)
                                w.append(t, "Good (7 rounds)");
                            else
                            if(compatibility == 6)
                                w.append(t, "Average (6 rounds)");
                            else
                            if(compatibility == 5)
                                w.append(t, "Poor (5 rounds)");
                            else
                                w.append(t, "Terrible (4 rounds)");
                        }

                } else
                {
                    w.append(t, "N/A");
                }
                JButton Choice = new JButton(w.getHarem()[i].mainName);
                final Forsaken Spent = w.getHarem()[i];
                final int index = i;
                Choice.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        if(w.active)
                        {
                            if(w.usedForsaken != null)
                                w.evilEnergy += w.usedForsaken.EECost();
                            w.evilEnergy -= Spent.EECost();
                            w.usedForsaken = Spent;
                            w.usedForsakenIndex = index;
                            Project.ForsakenMenu(t, p, f, w, s, 0);
                        } else
                        {
                            p.removeAll();
                            Spent.stamina -= 200;
                            Spent.motivation -= Spent.motivationCost();
                            Project.ForsakenDowntime(t, p, f, w, s, new Forsaken[] {
                                Spent
                            });
                            JButton Continue = new JButton("Continue");
                            Continue.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.ForsakenMenu(t, p, f, w, s, 0);
                                }
                            });
                            p.add(Continue);
                            p.validate();
                            p.repaint();
                        }
                    }
                });
                int EEAvailable = w.evilEnergy;
                if(w.usedForsaken != null)
                    EEAvailable += w.usedForsaken.EECost();
                if(Spent.stamina >= 200 && Spent.motivation >= actualCost && (!w.active || EEAvailable >= Spent.EECost()))
                    p.add(Choice);
                nameDisplay[i - page * 5] = Spent.mainName;
                if(Spent.flavorObedience() < 20)
                    changePortrait(Spent.gender, Spent.type, true, true, w, nameDisplay, i - page * 5, Emotion.ANGER, Emotion.NEUTRAL);
                else
                if(Spent.flavorObedience() < 40)
                    changePortrait(Spent.gender, Spent.type, true, true, w, nameDisplay, i - page * 5, Emotion.ANGER, Emotion.SHAME);
                else
                if(Spent.flavorObedience() < 61)
                    changePortrait(Spent.gender, Spent.type, true, true, w, nameDisplay, i - page * 5, Emotion.FEAR, Emotion.SHAME);
                else
                if(Spent.flavorObedience() < 81)
                    changePortrait(Spent.gender, Spent.type, true, true, w, nameDisplay, i - page * 5, Emotion.FOCUS, Emotion.NEUTRAL);
                else
                    changePortrait(Spent.gender, Spent.type, true, true, w, nameDisplay, i - page * 5, Emotion.JOY, Emotion.FOCUS);
            }

        if(w.getHarem().length > (page + 1) * 5)
        {
            JButton NextPage = new JButton(">");
            NextPage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, "\n\n" + w.getSeparator());
                    Project.ForsakenChoice(t, p, f, w, s, page + 1);
                }
            });
            p.add(NextPage);
        }
        JButton None = new JButton("None");
        None.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                Project.ForsakenDowntime(t, p, f, w, s, null);
                JButton Continue = new JButton("Continue");
                Continue.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.ForsakenMenu(t, p, f, w, s, 0);
                    }
                });
                p.add(Continue);
                p.validate();
                p.repaint();
            }
        });
        if(!w.active)
            p.add(None);
        JButton Cancel = new JButton("Cancel");
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.ForsakenMenu(t, p, f, w, s, 0);
            }
        });
        p.add(Cancel);
        p.validate();
        p.repaint();
    }

    public static void ForsakenInteraction(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s, final Forsaken x)
    {
        p.removeAll();
        clearPortraits();
        String nameDisplay[] = new String[5];
        nameDisplay[0] = x.mainName;
        if(x.flavorObedience() < 20)
            changePortrait(x.gender, x.type, true, true, w, nameDisplay, 0, Emotion.ANGER, Emotion.NEUTRAL);
        else
        if(x.flavorObedience() < 40)
            changePortrait(x.gender, x.type, true, true, w, nameDisplay, 0, Emotion.ANGER, Emotion.SHAME);
        else
        if(x.flavorObedience() < 61)
            changePortrait(x.gender, x.type, true, true, w, nameDisplay, 0, Emotion.FEAR, Emotion.SHAME);
        else
        if(x.flavorObedience() < 81)
            changePortrait(x.gender, x.type, true, true, w, nameDisplay, 0, Emotion.FOCUS, Emotion.NEUTRAL);
        else
            changePortrait(x.gender, x.type, true, true, w, nameDisplay, 0, Emotion.JOY, Emotion.FOCUS);
        w.append(t, "\n\n" + w.getSeparator() + "\n\n" + x.mainName);
        if(!x.mainName.equals(x.originalName))
        {
            w.append(t, " (formerly known as");
            if(!x.adjectiveName.equals("none"))
            {
                w.append(t, String.format(" %1$s", x.adjectiveName));
                if(x.nounName.length() > 0)
                    w.append(t, String.format(" %1$s", x.nounName));
            }
            w.append(t, String.format(" %1$s", x.originalName) + ")");
        }
        if(x.familyName.length() > 0)
        {
            if(x.mainName.equals(x.familyName) || x.mainName.equals(x.givenName) || x.originalName.equals(x.familyName) || x.originalName.equals(x.givenName))
                w.append(t, "\nFull name: ");
            else
                w.append(t, "\nReal name: ");
            if(x.filthyGaijin)
                w.append(t, String.format("%1$s ", x.givenName) + x.familyName);
            else
                w.append(t, String.format("%1$s ", String.valueOf(x.familyName)) + x.givenName);
        } else
        if(!x.givenName.equals(x.mainName) && !x.givenName.equals(x.originalName))
            w.append(t, "\nReal name: " + x.givenName);
        w.append(t, "\n\nStamina: " + String.valueOf(x.stamina / 10) + "." + String.valueOf(x.stamina % 10) + "%\nMotivation: " + String.valueOf(x.motivation / 10) + "." + String.valueOf(x.motivation % 10) + "%");
        w.append(t, "\n\nExpertise\nHATE: " + x.condensedFormat(x.hateExp) + " (x" + x.expMultiplierDisplay(x.hateExp) + " dmg)\nPLEA: " + x.condensedFormat(x.pleaExp) + " (x" + x.expMultiplierDisplay(x.pleaExp) + " dmg)");
        if(w.tickleOn)
            w.append(t, "\nANTI: ");
        else
            w.append(t, "\nINJU: ");
        w.append(t, String.valueOf(x.condensedFormat(x.injuExp)) + " (x" + x.expMultiplierDisplay(x.injuExp) + " dmg)\nEXPO: " + x.condensedFormat(x.expoExp) + " (x" + x.expMultiplierDisplay(x.expoExp) + " dmg)\n" + x.describeCombatStyle(w, true));
        if(x.defilerType != 0)
            w.append(t, "\n\n" + x.describeDefilerType(w, false));
        if(x.defeatType == 5 && x.obedience < 40)
            w.append(t, "\n\nTrait: Eager Partner\nWhile Obedience remains below 40%, 1/4 Motivation cost to deploy and +50% PLEA and EXPO damage");
        else
        if(x.defeatType == 5)
            w.append(t, "\n\nTrait: Broken Traitor\nNo longer receives 'Eager Partner' bonus, but can still be trained to Tempt the Chosen");
        if(x.defeatType == 6)
            w.append(t, "\n\nTrait: Dissociative Identity\nWhile consenting, training can only increase Disgrace.  +50% HATE and INJU damage");
        if(x.type == Chosen.Species.SUPERIOR)
            w.append(t, "\n\nTrait: Superior Forsaken\nx2 Motivation cost to deploy, +50% damage");
        w.append(t, "\n\nOrgasms given: ");
        if(x.orgasmsGiven == 0)
            w.append(t, "none");
        else
            w.append(t, String.valueOf(x.orgasmsGiven));
        w.append(t, "\nOrgasms experienced: ");
        if(x.timesOrgasmed == 0)
            w.append(t, "none");
        else
            w.append(t, String.valueOf(x.timesOrgasmed));
        w.append(t, "\nLongest continuous orgasm: ");
        if(x.timesOrgasmed == 0)
            w.append(t, "N/A");
        else
        if(x.strongestOrgasm < 600)
            w.append(t, String.format("%1$s.", String.valueOf(x.strongestOrgasm / 10)) + String.valueOf(x.strongestOrgasm % 10) + " seconds");
        else
        if(x.strongestOrgasm < 36000)
            w.append(t, String.format("%1$s minutes ", String.valueOf(x.strongestOrgasm / 600)) + String.valueOf((x.strongestOrgasm % 600) / 10) + " seconds");
        else
            w.append(t, String.format("%1$s hours ", String.valueOf(x.strongestOrgasm / 36000)) + String.valueOf((x.strongestOrgasm % 36000) / 600) + " minutes");
        w.append(t, "\nSeen naked by: " + x.timesExposed + " people (" + x.timesExposedSelf + " with permission)");
        if(x.gender != Forsaken.Gender.MALE)
        {
            w.append(t, "\nTimes vaginally penetrated: " + x.timesHadSex);
            if(x.timesHadSex == 0)
                w.append(t, " (virgin)");
        }
        w.append(t, "\nTimes anally penetrated: ");
        int analCount = x.enjoyedAnal;
        if(x.gender == Forsaken.Gender.MALE)
            analCount += x.timesHadSex;
        else
            analCount += x.timesTortured;
        w.append(t, String.valueOf(analCount));
        if(analCount == 0)
            w.append(t, " (anal virgin)");
        if(x.demonicBirths > 0)
            w.append(t, "\nDemonic births: " + x.demonicBirths);
        else
            w.append(t, "\nDemonic births: 0");
        w.append(t, "\n\nPeople injured: " + x.peopleInjured + "\nPeople killed: " + x.timesKilled + "\nSelf-harm incidents: " + x.timesHarmedSelf + "\n\nHostility: " + x.hostility + "% (");
        if(x.defeatType == 6 && x.flavorHostility() > x.hostility)
            w.append(t, "Pretending to be hateful toward humanity");
        else
        if(x.hostility < 20)
            w.append(t, "Optimistic about humanity");
        else
        if(x.hostility < 40)
            w.append(t, "Ambivalent about humanity");
        else
        if(x.hostility < 61)
            w.append(t, "Pessimistic about humanity");
        else
        if(x.hostility < 81)
            w.append(t, "Hateful toward humanity itself");
        else
            w.append(t, "Desires the destruction of humanity");
        w.append(t, (new StringBuilder(")\nDeviancy: ")).append(x.deviancy).append("% (").toString());
        if(x.defeatType == 6 && x.flavorDeviancy() > x.deviancy)
            w.append(t, "Pretending to fetishize aberrant actions");
        else
        if(x.deviancy < 20)
            w.append(t, "Little interest in sexuality");
        else
        if(x.deviancy < 40)
            w.append(t, "Elaborate sexual fantasies");
        else
        if(x.deviancy < 61)
            w.append(t, "Twisted sexual desires");
        else
        if(x.deviancy < 81)
            w.append(t, "Fetishizes aberrant actions");
        else
            w.append(t, "Seeks sexual pleasure regardless of situation");
        w.append(t, (new StringBuilder(")\nObedience: ")).append(x.obedience).append("% (").toString());
        if(x.defeatType == 5 && x.obedience < 40)
            w.append(t, "Obeys due to expectation of rewards");
        else
        if(x.defeatType == 6 && x.flavorObedience() > x.obedience)
            w.append(t, "Pretending to be eager to obey");
        else
        if(x.obedience < 20)
            w.append(t, "Reflexively disobeys");
        else
        if(x.obedience < 40)
            w.append(t, "Obeys when convenient");
        else
        if(x.obedience < 61)
            w.append(t, "Obeys out of fear");
        else
        if(x.obedience < 81)
            w.append(t, "Eagerly obeys");
        else
            w.append(t, "Unthinkingly obeys");
        w.append(t, (new StringBuilder(")\nDisgrace: ")).append(x.disgrace).append("% (").toString());
        if(x.disgrace < 20)
            w.append(t, "Still somewhat respected");
        else
        if(x.disgrace < 40)
            w.append(t, "Humiliated");
        else
        if(x.disgrace < 61)
            w.append(t, "Object of base lust");
        else
        if(x.disgrace < 81)
            w.append(t, "Viewed with contempt");
        else
            w.append(t, "Considered powerless and worthless");
        w.append(t, (new StringBuilder(")\n\nWhat would you like to speak to ")).append(x.mainName).append(" about?").toString());
        if(w.active && !w.loopComplete)
            w.append(t, String.format("  Note that training %1$s", x.himHer()) + " will take the entire day.");
        JButton Self = new JButton("Herself");
        if(x.gender == Forsaken.Gender.MALE)
            Self.setText("Himself");
        Self.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                x.selfTalk(t);
            }
        });
        p.add(Self);
        JButton Philosophy = new JButton("Philosophy");
        Philosophy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                x.philosophyTalk(t);
            }
        });
        p.add(Philosophy);
        JButton Training = new JButton("Training");
        Training.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                x.trainingTalk(t);
            }
        });
        JButton Life = new JButton("Everyday Life");
        Life.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                x.lifeTalk(t);
            }
        });
        final WriteObject wobj = new WriteObject();
        JButton Others = new JButton("Other Forsaken");
        Others.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                x.othersTalk(w, t, s);
            }
        });
        if(w.getHarem().length > 1)
            p.add(Others);
        JButton ChangeName = new JButton("Customize");
        ChangeName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                x.CustomizeMenu(t, p, f, w, s);
            }
        });
        p.add(ChangeName);
        JButton Meet = new JButton("Meet");
        Meet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Body participants[] = new Body[1];
                participants[0] = new Body(x);
                Project.SelectBody(t, p, f, w, s, participants);
            }
        });
        if(!w.active || !x.visited)
            p.add(Meet);
        JButton FreeTraining = new JButton("Free Training");
        FreeTraining.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Boolean newTraining[] = new Boolean[22];
                for(int i = 0; i < newTraining.length; i++)
                    newTraining[i] = false;

                x.trainingMenu(t, p, f, w, s, newTraining, 0, true);
            }
        });
        if(w.active)
            FreeTraining.setText("Training");
        if(!w.active || (w.day != 50 - w.eventOffset * 3 || w.loopComplete) && (w.day != 51 - w.eventOffset * 3 || !w.campaign))
            p.add(FreeTraining);
        JButton Delete = new JButton("Delete");
        if(w.campaign)
            Delete.setText("Sacrifice");
        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                if(x.gender == Forsaken.Gender.MALE)
                    w.append(t, String.format("%1$s will have ", x.mainName) + x.hisHer() + " body modified into single-purpose breeding stock for the Demons, and you will never interact directly with " + x.himHer() + " again.  The terror of facing a similar fate will motivate any other Forsaken to obey you much more faithfully in the short-term.  Is this okay?");
                else
                    w.append(t, String.format("%1$s will spend the rest of ", x.mainName) + x.hisHer() + " life as single-purpose breeding stock for the Demons, and you will never interact directly with " + x.himHer() + " again.  The terror of facing a similar fate will motivate any other Forsaken to obey you much more faithfully in the short-term.  Is this okay?");
                JButton Confirm = new JButton("Confirm");
                Confirm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        Forsaken newHarem[] = new Forsaken[w.getHarem().length - 1];
                        int removal = 0;
                        for(int i = 0; i < w.getHarem().length; i++)
                            if(w.getHarem()[i] == x)
                                removal = i;
                            else
                                w.getHarem()[i].motivation = 1000;

                        for(int i = 0; i < newHarem.length; i++)
                            if(i < removal)
                                newHarem[i] = w.getHarem()[i];
                            else
                                newHarem[i] = w.getHarem()[i + 1];

                        if(w.campaign)
                        {
                            Forsaken newSacrificed[] = new Forsaken[w.sacrificed.length + 1];
                            for(int i = 0; i < w.sacrificed.length; i++)
                                newSacrificed[i] = w.sacrificed[i];

                            newSacrificed[newSacrificed.length - 1] = w.conquered[removal];
                            w.conquered = newHarem;
                            w.sacrificed = newSacrificed;
                        } else
                        {
                            s.harem = newHarem;
                        }
                        wobj.serializeSaveData(s);
                        x.describeSacrifice(t, w);
                        JButton Continue = new JButton("Continue");
                        Continue.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                Project.ForsakenMenu(t, p, f, w, s, 0);
                            }
                        });
                        p.add(Continue);
                        p.validate();
                        p.repaint();
                    }
                });
                p.add(Confirm);
                JButton Cancel = new JButton("Cancel");
                Cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.ForsakenInteraction(t, p, f, w, s, x);
                    }
                });
                p.add(Cancel);
                p.validate();
                p.repaint();
            }
        });
        p.add(Delete);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.ForsakenMenu(t, p, f, w, s, 0);
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void SelectBody(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s, final Body participants[])
    {
        p.removeAll();
        w.append(t, "\n\n" + w.getSeparator() + "\n\nWhat sort of body will you be bringing to see ");
        if(participants.length != 1)
            w.append(t, "them?");
        else
            w.append(t, String.format("%1$s?", participants[0].himHer()));
        final int parts[] = new int[14];
        parts[Body.MOUTH] = 1;
        parts[Body.HAND] = 2;
        parts[Body.ASS] = 1;
        parts[Body.FOOT] = 2;
        parts[Body.ARMPIT] = 2;
        parts[Body.KNEEPIT] = 2;
        parts[Body.HAIR] = 1;
        parts[Body.HIPS] = 1;
        parts[Body.BACK] = 1;
        JButton Male = new JButton("Male");
        Male.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                parts[Body.PENIS] = 1;
                parts[Body.BALLS] = 1;
                Body newParticipants[] = new Body[participants.length + 1];
                newParticipants[0] = new Body();
                newParticipants[0].parts = parts;
                newParticipants[0].bodyType = Body.Appearance.CUTEBOY;
                for(int i = 0; i < participants.length; i++)
                    newParticipants[i + 1] = participants[i];

                Project.DecideLocation(t, p, f, w, s, newParticipants);
            }
        });
        p.add(Male);
        JButton Female = new JButton("Female");
        Female.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                parts[Body.CLIT] = 1;
                parts[Body.PUSSY] = 1;
                parts[Body.CLEAVAGE] = 1;
                Body newParticipants[] = new Body[participants.length + 1];
                newParticipants[0] = new Body();
                newParticipants[0].parts = parts;
                newParticipants[0].bodyType = Body.Appearance.CUTEGIRL;
                for(int i = 0; i < participants.length; i++)
                    newParticipants[i + 1] = participants[i];

                Project.DecideLocation(t, p, f, w, s, newParticipants);
            }
        });
        p.add(Female);
        JButton Futanari = new JButton("Futanari");
        Futanari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                parts[Body.PENIS] = 1;
                parts[Body.PUSSY] = 1;
                parts[Body.CLEAVAGE] = 1;
                Body newParticipants[] = new Body[participants.length + 1];
                newParticipants[0] = new Body();
                newParticipants[0].parts = parts;
                newParticipants[0].bodyType = Body.Appearance.CUTEGIRL;
                for(int i = 0; i < participants.length; i++)
                    newParticipants[i + 1] = participants[i];

                Project.DecideLocation(t, p, f, w, s, newParticipants);
            }
        });
        p.add(Futanari);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(participants.length == 1 && participants[0].forsakenOwner != null)
                    Project.ForsakenInteraction(t, p, f, w, s, participants[0].forsakenOwner);
                else
                if(w.active)
                    Project.Shop(t, p, f, w);
                else
                    Project.IntroOne(t, p, f, w);
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void DecideLocation(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s, final Body participants[])
    {
        p.removeAll();
        if(participants[0].chosenOwner == null && participants[0].forsakenOwner == null)
            if(participants.length == 2)
                w.append(t, "\n\n" + w.getSeparator() + "\n\nWhere would you like to meet " + participants[1].himHer() + "?");
            else
                w.append(t, "\n\n" + w.getSeparator() + "\n\nWhere would you like to meet them?");
        JButton Chamber = new JButton("Private Chamber");
        Chamber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.NewMeeting(t, p, f, w, s, participants, Activity.Location.CHAMBER);
            }
        });
        p.add(Chamber);
        JButton Stage = new JButton("On Stage");
        Stage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.NewMeeting(t, p, f, w, s, participants, Activity.Location.STAGE);
            }
        });
        p.add(Stage);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Body newParticipants[] = new Body[participants.length - 1];
                if(participants[0].chosenOwner == null && participants[0].forsakenOwner == null)
                {
                    for(int i = 0; i < newParticipants.length; i++)
                        newParticipants[i] = participants[i + 1];

                } else
                {
                    newParticipants = participants;
                }
                Project.SelectBody(t, p, f, w, s, newParticipants);
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void NewMeeting(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, SaveData s, Body participants[], Activity.Location spot)
    {
        if(participants[0].chosenOwner == null && participants[0].forsakenOwner == null)
            w.lordBody = participants[0];
        w.sceneParticipants = participants;
        w.sceneDuration = 0;
        w.sceneLocation = spot;
        for(int i = 0; i < participants.length; i++)
            if(participants[i].forsakenOwner != null)
            {
                Body.Appearance previous = null;
                participants[i].forsakenOwner.visited = true;
                if(participants[0] == w.lordBody)
                {
                    if(participants[i].forsakenOwner.rememberedBodies == null || participants[i].forsakenOwner.rememberedBodies.length == 0)
                    {
                        participants[i].forsakenOwner.rememberedBodies = (new Body[] {
                            participants[0]
                        });
                        participants[i].forsakenOwner.rememberedDemonLordBody = participants[0];
                    } else
                    {
                        previous = participants[i].forsakenOwner.rememberedDemonLordBody.bodyType;
                        Body newRememberedBodies[] = new Body[5];
                        if(participants[i].forsakenOwner.rememberedBodies.length > 4)
                        {
                            for(int j = 0; j < 4; j++)
                                newRememberedBodies[j] = participants[i].forsakenOwner.rememberedBodies[j + 1];

                            newRememberedBodies[4] = w.lordBody;
                        } else
                        {
                            newRememberedBodies = new Body[participants[i].forsakenOwner.rememberedBodies.length + 1];
                            for(int j = 0; j < participants[i].forsakenOwner.rememberedBodies.length; j++)
                                newRememberedBodies[j] = participants[i].forsakenOwner.rememberedBodies[j];

                            newRememberedBodies[newRememberedBodies.length - 1] = w.lordBody;
                        }
                        for(int j = 0; j < newRememberedBodies.length; j++)
                        {
                            int counted = 0;
                            Body.Appearance consensus = newRememberedBodies[j].bodyType;
                            for(int k = j; k < newRememberedBodies.length; k++)
                            {
                                if(newRememberedBodies[k].bodyType == consensus)
                                    counted++;
                                if(counted > 2)
                                    participants[i].forsakenOwner.rememberedDemonLordBody = newRememberedBodies[j];
                            }

                        }

                        participants[i].forsakenOwner.rememberedBodies = newRememberedBodies;
                    }
                    if(participants[i].appearanceGender(previous) != participants[i].appearanceGender(participants[i].forsakenOwner.rememberedDemonLordBody.bodyType) && ((participants[i].forsakenOwner.demonLord.equals("Mister Demon Lord") || participants[i].forsakenOwner.demonLord.equals("Master")) && participants[i].forsakenOwner.rememberedDemonLordBody.appearanceGender(participants[i].forsakenOwner.rememberedDemonLordBody.bodyType) == Forsaken.Gender.FEMALE || (participants[i].forsakenOwner.demonLord.equals("Miss Demon Lord") || participants[i].forsakenOwner.demonLord.equals("Mistress")) && participants[i].appearanceGender(participants[i].forsakenOwner.rememberedDemonLordBody.bodyType) == Forsaken.Gender.MALE))
                        participants[i].forsakenOwner.pickEpithet();
                }
            } else
            if(participants[i].chosenOwner != null)
            {
                participants[i].chosenOwner.visited = true;
                if(participants[0] == w.lordBody)
                    if(participants[i].chosenOwner.rememberedBodies == null)
                    {
                        participants[i].chosenOwner.rememberedBodies = (new Body[] {
                            participants[0]
                        });
                        participants[i].chosenOwner.rememberedDemonLordBody = participants[0];
                    } else
                    {
                        Body newRememberedBodies[] = new Body[5];
                        if(participants[i].chosenOwner.rememberedBodies.length > 5)
                        {
                            for(int j = 0; j < 4; j++)
                                newRememberedBodies[j] = participants[i].chosenOwner.rememberedBodies[j + 1];

                            newRememberedBodies[4] = w.lordBody;
                        } else
                        {
                            newRememberedBodies = new Body[participants[i].chosenOwner.rememberedBodies.length + 1];
                            for(int j = 0; j < participants[i].chosenOwner.rememberedBodies.length; j++)
                                newRememberedBodies[j] = participants[i].chosenOwner.rememberedBodies[j];

                            newRememberedBodies[newRememberedBodies.length - 1] = w.lordBody;
                        }
                        for(int j = 0; j < newRememberedBodies.length; j++)
                        {
                            int counted = 0;
                            Body.Appearance consensus = newRememberedBodies[j].bodyType;
                            for(int k = j; k < newRememberedBodies.length; k++)
                            {
                                if(newRememberedBodies[k].bodyType == consensus)
                                    counted++;
                                if(counted > 2)
                                    participants[i].chosenOwner.rememberedDemonLordBody = newRememberedBodies[j];
                            }

                        }

                    }
            }

        participants[0].PickActivity(t, p, f, w, s);
    }

    public static void SceneCompletion(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, SaveData s)
    {
        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
        w.underlineAppend(t, "Scenes Recorded");
        w.append(t, "\n");
        int types = 0;
        for(int i = 5; i < 21; i++)
            if(s.sceneText[i].length > 0)
                types++;

        w.append(t, String.format("Core Vulnerability Break: %1$s", types) + "/20\n");
        w.append(t, "Core Vulnerability Distortions: ");
        int found = 0;
        if(s.sceneText[21].length > 0)
            found++;
        if(s.sceneText[22].length > 0)
            found++;
        w.append(t, String.valueOf(found) + "/2\n");
        types = 0;
        for(int i = 33; i < 49; i++)
            if(s.sceneText[i].length > 0)
                types++;

        w.append(t, String.format("Daily Vignettes: %1$s", types) + "/" + String.valueOf(16));
        w.append(t, "\n\nWhich type of scene would you like to view?");
    }

    public static void SceneViewer(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s, final int starting)
    {
        p.removeAll();
        int found = 0;
        int highest = 0;
        for(int i = starting - 1; i >= 0 && found < 5; i--)
            if(s.sceneText[i].length > 0)
            {
                found++;
                highest = i;
            }

        if(found > 0)
        {
            final int newStartPoint = highest;
            JButton Previous = new JButton("<");
            Previous.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.SceneViewer(t, p, f, w, s, newStartPoint);
                }
            });
            p.add(Previous);
        }
        found = 0;
        highest = 0;
        for(int i = starting; i < s.sceneText.length && found < 5; i++)
            if(s.sceneText[i].length > 0)
            {
                String sceneName = "";
                found++;
                highest = i;
                if(i == 0)
                    sceneName = "First Meeting";
                else
                if(i == 1)
                    sceneName = "Interview";
                else
                if(i == 2)
                    sceneName = "Vacation";
                else
                if(i == 3)
                    sceneName = "Final Preparation";
                else
                if(i == 4)
                    sceneName = "Epilogue";
                else
                if(i == 5)
                    sceneName = "First 'Violence'";
                else
                if(i == 6)
                    sceneName = "First 'Service'";
                else
                if(i == 7)
                    sceneName = "First 'Begging'";
                else
                if(i == 8)
                    sceneName = "First 'Covering'";
                else
                if(i == 9)
                    sceneName = "First 'Insemination'";
                else
                if(i == 10)
                    sceneName = "First 'Force Orgasm'";
                else
                if(i == 11)
                    sceneName = "First 'Sodomize/Torture/Force Laughter'";
                else
                if(i == 12)
                    sceneName = "First 'Broadcast'";
                else
                if(i == 13)
                    sceneName = "First 'Slaughter'";
                else
                if(i == 14)
                    sceneName = "First 'Fantasize'";
                else
                if(i == 15)
                    sceneName = "First 'Detonate'";
                else
                if(i == 16)
                    sceneName = "First 'Striptease'";
                else
                if(i == 17)
                    sceneName = "First 'Impregnation'";
                else
                if(i == 18)
                    sceneName = "First 'Hypnotism'";
                else
                if(i == 19)
                    sceneName = "First 'Drain'";
                else
                if(i == 20)
                    sceneName = "First 'Parasitism'";
                else
                if(i == 21)
                    sceneName = "First 'Tempt'";
                else
                if(i == 22)
                    sceneName = "First Catatonia";
                else
                if(i == 33)
                    sceneName = "Perverted Donor";
                else
                if(i == 34)
                    sceneName = "Sexual Technique Training";
                else
                if(i == 35)
                    sceneName = "Blackmailed";
                else
                if(i == 36)
                    sceneName = "Bodypaint Experiment";
                else
                if(i == 37)
                    sceneName = "Photoshoot";
                else
                if(i == 38)
                    sceneName = "Stripped in Public";
                else
                if(i == 39)
                    sceneName = "Movie Date";
                else
                if(i == 40)
                    sceneName = "Petplay";
                else
                if(i == 41)
                    sceneName = "Train Molester";
                else
                if(i == 42)
                    sceneName = "Sexual Combat Training";
                else
                if(i == 43)
                    sceneName = "Guilty Service";
                else
                if(i == 44)
                    sceneName = "Sleep Molester";
                else
                if(i == 45)
                    sceneName = "Saving One's Rival";
                else
                if(i == 46)
                    sceneName = "Service Competition";
                else
                if(i == 47)
                    sceneName = "Relief Through Abuse";
                else
                if(i == 48)
                    sceneName = "Endurance Match";
                JButton PickScene = new JButton(sceneName);
                final int sceneType = i;
                PickScene.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.SceneChoice(t, p, f, w, s, sceneType, starting, 0);
                    }
                });
                p.add(PickScene);
            }

        for(int i = highest + 1; i < s.sceneText.length; i++)
            if(s.sceneText[i].length > 0)
            {
                JButton Next = new JButton(">");
                final int newStartPoint = i;
                Next.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.SceneViewer(t, p, f, w, s, newStartPoint);
                    }
                });
                p.add(Next);
                i = s.sceneText.length;
            }

        JButton Back = new JButton("Done");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                t.setText("");
                Project.IntroOne(t, p, f, w);
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void SceneChoice(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s, final int type, final int starting, final int page)
    {
        p.removeAll();
        w.append(t, "\n\n" + w.getSeparator() + "\n\nWhose scene would you like to replay?\n");
        if(page > 0)
        {
            JButton Previous = new JButton("<");
            Previous.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    int newPage = page - 5;
                    if(newPage < 0)
                        newPage = 0;
                    Project.SceneChoice(t, p, f, w, s, type, starting, newPage);
                }
            });
            p.add(Previous);
        }
        for(int i = page; i < page + 5 && i < s.sceneText[type].length; i++)
        {
            w.append(t, "\n" + s.sceneButtons[type][i] + ": " + s.sceneSummaries[type][i]);
            JButton PickScene = new JButton(s.sceneButtons[type][i]);
            final int sceneID = i;
            PickScene.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.ReplayScene(t, p, f, w, s, type, starting, page, sceneID);
                }
            });
            p.add(PickScene);
        }

        if(s.sceneText[type].length > page + 5)
        {
            JButton Next = new JButton(">");
            Next.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.SceneChoice(t, p, f, w, s, type, starting, page + 5);
                }
            });
            p.add(Next);
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.SceneCompletion(t, p, f, w, s);
                Project.SceneViewer(t, p, f, w, s, starting);
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void ReplayScene(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s, final int type, final int starting, final int page, 
            final int entry)
    {
        p.removeAll();
        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
        String shownFaces[] = new String[5];
        clearPortraits();
        for(int i = 0; i < 5; i++)
            if(s.sceneEmotions[type][entry][i] != null)
            {
                shownFaces[i] = s.sceneFaces[type][entry][i];
                changePortrait(s.sceneGenders[type][entry][i], s.sceneSpecs[type][entry][i], s.sceneCivs[type][entry][i], s.sceneFallen[type][entry][i], w, shownFaces, i, s.sceneEmotions[type][entry][i], s.sceneEmotions[type][entry][i]);
            }

        for(int i = 0; i < s.sceneText[type][entry].length; i++)
            w.flexibleAppend(t, s.sceneText[type][entry][i], s.sceneColor[type][entry][i], s.sceneUnderline[type][entry][i]);

        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.SceneChoice(t, p, f, w, s, type, starting, page);
            }
        });
        p.add(Back);
        JButton Delete = new JButton("Delete");
        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                w.append(t, "\n\n" + w.getSeparator() + "\n\nReally delete this scene?");
                JButton ReallyDelete = new JButton("Delete");
                ReallyDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        String newSceneText[][] = new String[s.sceneText[type].length - 1][0];
                        Color newSceneColor[][] = new Color[s.sceneColor[type].length - 1][0];
                        Boolean newSceneUnderline[][] = new Boolean[s.sceneUnderline[type].length - 1][0];
                        String newSceneButtons[] = new String[s.sceneButtons[type].length - 1];
                        String newSceneSummaries[] = new String[s.sceneSummaries[type].length - 1];
                        Emotion newSceneEmotions[][] = new Emotion[s.sceneEmotions[type].length - 1][5];
                        String newSceneFaces[][] = new String[s.sceneFaces[type].length - 1][5];
                        Chosen.Species newSceneSpecs[][] = new Chosen.Species[s.sceneSpecs[type].length - 1][5];
                        Boolean newSceneCivs[][] = new Boolean[s.sceneCivs[type].length - 1][5];
                        Boolean newSceneFallen[][] = new Boolean[s.sceneFallen[type].length - 1][5];
                        Forsaken.Gender newSceneGenders[][] = new Forsaken.Gender[s.sceneGenders[type].length - 1][5];
                        for(int i = 0; i < s.sceneText[type].length - 1; i++)
                        {
                            int editedEntry = i;
                            if(i >= entry)
                                editedEntry++;
                            newSceneText[i] = s.sceneText[type][editedEntry];
                            newSceneColor[i] = s.sceneColor[type][editedEntry];
                            newSceneUnderline[i] = s.sceneUnderline[type][editedEntry];
                            newSceneButtons[i] = s.sceneButtons[type][editedEntry];
                            newSceneSummaries[i] = s.sceneSummaries[type][editedEntry];
                            newSceneEmotions[i] = s.sceneEmotions[type][editedEntry];
                            newSceneFaces[i] = s.sceneFaces[type][editedEntry];
                            newSceneSpecs[i] = s.sceneSpecs[type][editedEntry];
                            newSceneCivs[i] = s.sceneCivs[type][editedEntry];
                            newSceneFallen[i] = s.sceneFallen[type][editedEntry];
                            newSceneGenders[i] = s.sceneGenders[type][editedEntry];
                        }

                        s.sceneText[type] = newSceneText;
                        s.sceneColor[type] = newSceneColor;
                        s.sceneUnderline[type] = newSceneUnderline;
                        s.sceneButtons[type] = newSceneButtons;
                        s.sceneSummaries[type] = newSceneSummaries;
                        s.sceneEmotions[type] = newSceneEmotions;
                        s.sceneFaces[type] = newSceneFaces;
                        s.sceneSpecs[type] = newSceneSpecs;
                        s.sceneCivs[type] = newSceneCivs;
                        s.sceneFallen[type] = newSceneFallen;
                        s.sceneGenders[type] = newSceneGenders;
                        WriteObject wobj = new WriteObject();
                        wobj.serializeSaveData(s);
                        if(s.sceneText[type].length > 0)
                        {
                            Project.SceneChoice(t, p, f, w, s, type, starting, 0);
                        } else
                        {
                            Project.SceneCompletion(t, p, f, w, s);
                            Project.SceneViewer(t, p, f, w, s, 0);
                        }
                    }
                });
                p.add(ReallyDelete);
                JButton Return = new JButton("Return to Scene Choice");
                Return.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.SceneChoice(t, p, f, w, s, type, starting, page);
                    }
                });
                p.add(Return);
                p.validate();
                p.repaint();
            }
        });
        p.add(Delete);
        p.validate();
        p.repaint();
    }

    public static void IntroTwo(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        String path = Project.class.getProtectionDomain().getCodeSource().getLocation().getPath();
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
        path = path.replaceAll(File.separator + "u0020", File.separator + " ");
        File saveLocation = new File(path + File.separator + "saves.sav");
        if(saveLocation.exists())
        {
            ReadObject robj = new ReadObject();
            w.save = robj.deserializeSaveData(path + File.separator + "saves.sav");
            if(w.save.sceneText == null)
                w.save.organizeScenes(49);
            else
            if(w.save.sceneText.length < 49)
                w.save.organizeScenes(49);
        } else
        {
            w.save = new SaveData();
            if(w.save.sceneText == null)
                w.save.organizeScenes(49);
            else
            if(w.save.sceneText.length < 49)
                w.save.organizeScenes(49);
        }
        w.getCast()[0].world = w;
        String city = "the capital city";
        if(w.campaign)
            city = w.cityName;
        w.append(t, "\n\n" + w.getSeparator() + "\n\nThe peaceful everyday routine of " + city + " is instantly shattered as a horde of Demons and their dominated human Thralls spills out onto the street!  Screams and alarms fill the air, chaos descending on the scene in an instant.  Already, innocents are being mobbed and dragged away towards a terrible fate.\n\nJust then, a sound like a thunderclap cuts through the panic, and a voice calls out a challenge to the Demons below!\n\n");
        w.getCast()[0].say(t, "\"" + w.getCast()[0].announcement() + "\"\n\n");
        w.getCast()[0].transform(t, w);
        w.newCombat(w, w.getCast());
        w.append(t, "\n");
        PickTarget(t, p, f, w);
    }

    public static void PickTarget(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        final Color YELLOWISH = new Color(255, 225, 125);
        final Color PURPLISH = new Color(225, 125, 255);
        int inseminated = 0;
        int orgasming = 0;
        int sodomized = 0;
        int broadcasted = 0;
        p.removeAll();
        final Chosen initiative[] = new Chosen[3];
        if(w.getCombatants()[2] != null)
        {
            if(w.getCombatants()[0].getConfidence() > w.getCombatants()[1].getConfidence())
            {
                if(w.getCombatants()[0].getConfidence() > w.getCombatants()[2].getConfidence())
                {
                    if(w.getCombatants()[1].getConfidence() > w.getCombatants()[2].getConfidence())
                    {
                        initiative[0] = w.getCombatants()[0];
                        initiative[1] = w.getCombatants()[1];
                        initiative[2] = w.getCombatants()[2];
                    } else
                    {
                        initiative[0] = w.getCombatants()[0];
                        initiative[1] = w.getCombatants()[2];
                        initiative[2] = w.getCombatants()[1];
                    }
                } else
                {
                    initiative[0] = w.getCombatants()[2];
                    initiative[1] = w.getCombatants()[0];
                    initiative[2] = w.getCombatants()[1];
                }
            } else
            if(w.getCombatants()[0].getConfidence() > w.getCombatants()[2].getConfidence())
            {
                initiative[0] = w.getCombatants()[1];
                initiative[1] = w.getCombatants()[0];
                initiative[2] = w.getCombatants()[2];
            } else
            if(w.getCombatants()[1].getConfidence() > w.getCombatants()[2].getConfidence())
            {
                initiative[0] = w.getCombatants()[1];
                initiative[1] = w.getCombatants()[2];
                initiative[2] = w.getCombatants()[0];
            } else
            {
                initiative[0] = w.getCombatants()[2];
                initiative[1] = w.getCombatants()[1];
                initiative[2] = w.getCombatants()[0];
            }
        } else
        if(w.getCombatants()[1] != null)
        {
            if(w.getCombatants()[0].getConfidence() > w.getCombatants()[1].getConfidence())
            {
                initiative[0] = w.getCombatants()[0];
                initiative[1] = w.getCombatants()[1];
            } else
            {
                initiative[0] = w.getCombatants()[1];
                initiative[1] = w.getCombatants()[0];
            }
        } else
        {
            initiative[0] = w.getCombatants()[0];
        }
        for(int i = 0; i < w.getCombatants().length; i++)
            if(w.getCombatants()[i] != null)
                if(w.getCombatants()[i].isInseminated())
                    inseminated++;
                else
                if(w.getCombatants()[i].isOrgasming())
                    orgasming++;
                else
                if(w.getCombatants()[i].isSodomized())
                    sodomized++;
                else
                if(w.getCombatants()[i].isBroadcasted())
                    broadcasted++;

        w.append(t, "\nRound " + w.battleRound + "\n");
        if(w.evacNotice)
        {
            w.append(t, "Evacuation complete!\n");
            w.evacNotice = false;
        } else
        {
            w.append(t, String.format("Evacuation: %1$s", w.getEvacStatus(true)) + "\n");
        }
        Chosen trappedChosen = null;
        for(int i = 0; i < 3; i++)
            if(w.getCombatants()[i] != null)
            {
                w.getCombatants()[i].updateSurround();
                if(w.getCombatants()[i].isSurrounded() || w.getCombatants()[i].isCaptured())
                    trappedChosen = w.getCombatants()[i];
            }

        w.append(t, String.format("Extermination: %1$s", w.getExterminationStatus(true)) + "\n\n");
        if(w.evacuationProgress < w.evacuationComplete)
        {
            w.append(t, "The desperate battle continues...\n");
        } else
        {
            Chosen c = null;
            Boolean allGrabbed = true;
            if(w.getCombatants()[0] != null)
                if(w.getCombatants()[0].isSurrounded() || w.getCombatants()[0].isCaptured() || w.finalBattle && (!w.getCombatants()[0].alive || w.getCombatants()[0].resolve <= 0))
                {
                    if(w.getCombatants()[1] != null)
                        if(w.getCombatants()[1].isSurrounded() || w.getCombatants()[1].isCaptured() || w.finalBattle && (!w.getCombatants()[1].alive || w.getCombatants()[1].resolve <= 0))
                        {
                            if(w.getCombatants()[2] != null && !w.getCombatants()[2].isSurrounded() && !w.getCombatants()[2].isCaptured() && (!w.finalBattle || w.getCombatants()[2].alive && w.getCombatants()[2].resolve > 0))
                                allGrabbed = false;
                        } else
                        {
                            allGrabbed = false;
                        }
                } else
                {
                    allGrabbed = false;
                }
            if(allGrabbed)
                w.append(t, "The Demons have the Chosen at their mercy!\n");
            else
            if(w.exterminationProgress >= w.exterminationComplete)
            {
                Boolean allFree = true;
                if(w.getCombatants()[0].isSurrounded() || w.getCombatants()[0].isCaptured())
                    allFree = false;
                else
                if(w.getCombatants()[1] != null)
                    if(w.getCombatants()[1].isSurrounded() || w.getCombatants()[1].isCaptured())
                        allFree = false;
                    else
                    if(w.getCombatants()[2] != null && (w.getCombatants()[2].isSurrounded() || w.getCombatants()[2].isCaptured()))
                        allFree = false;
                if(allFree)
                {
                    int defeated = 0;
                    Chosen survivor = null;
                    for(int i = 0; i < 3; i++)
                        if(w.finalBattle)
                            if(!w.getCast()[i].alive || w.getCast()[i].resolve <= 0)
                                defeated++;
                            else
                                survivor = w.getCast()[i];

                    if(defeated == 2 && w.finalBattle)
                        w.append(t, String.format("With %1$s", survivor.hisHer()) + " allies defeated and no hope of winning on " + survivor.hisHer() + " own, " + survivor.getMainName() + " is preparing to make use of the hole in the Demons' formation to escape!  " + survivor.HeShe() + "'ll get away next turn unless " + survivor.heShe() + "'s surrounded or captured.\n");
                    else
                        w.append(t, "The reanimated Demons are fighting their last stand!  Combat will end next turn unless one of the Chosen is surrounded or captured.\n");
                } else
                if(w.finalBattle)
                {
                    Chosen killer1 = null;
                    Chosen killer2 = null;
                    Chosen victim1 = null;
                    Chosen victim2 = null;
                    for(int i = 0; i < 3; i++)
                        if(w.getCast()[i].isSurrounded() || w.getCast()[i].isCaptured())
                        {
                            if(victim1 == null)
                                victim1 = w.getCast()[i];
                            else
                                victim2 = w.getCast()[i];
                        } else
                        if(w.getCast()[i].alive && w.getCast()[i].resolve > 0)
                            if(killer1 == null)
                                killer1 = w.getCast()[i];
                            else
                                killer2 = w.getCast()[i];

                    int duration1 = 0;
                    if(victim1.isSurrounded())
                    {
                        duration1 = victim1.getSurroundDuration();
                    } else
                    {
                        duration1 = w.captureDuration - victim1.captureProgression;
                        if(victim1.timesDetonated() > 0)
                            duration1 -= victim1.getINJULevel();
                    }
                    int duration2 = 0;
                    if(victim2 != null)
                        if(victim2.isSurrounded())
                        {
                            duration2 = victim2.getSurroundDuration();
                        } else
                        {
                            duration2 = w.captureDuration - victim1.captureProgression;
                            if(victim2.timesDetonated() > 0)
                                duration2 -= victim2.getINJULevel();
                        }
                    if(duration1 < 2 && duration2 < 2)
                    {
                        if(victim2 == null)
                        {
                            if(killer2 == null)
                                w.append(t, String.format("%1$s waits for ", killer1.getMainName()) + victim1.getMainName() + "'s imminent escape so that the two of them can work together to end this.\n");
                            else
                                w.append(t, String.format("%1$s and ", killer1.getMainName()) + killer2.getMainName() + " wait for " + victim1.getMainName() + " to escape and rejoin their formation.\n");
                        } else
                        {
                            w.append(t, String.format("%1$s waits for ", killer1.getMainName()) + victim1.getMainName() + " and " + victim2.getMainName() + " to escape and form up so they can all work together to end this.\n");
                        }
                    } else
                    {
                        if(victim2 != null && duration2 < 2)
                            victim2 = null;
                        if(victim2 != null && duration1 < 2)
                        {
                            victim1 = victim2;
                            duration1 = duration2;
                            victim2 = null;
                        }
                        if(victim2 != null)
                        {
                            if(w.getRelationship(killer1.getNumber(), victim1.getNumber()) == -4 || victim1.isImpregnated() || victim1.isHypnotized() || victim1.isDrained() || victim1.isParasitized() || victim1.temptReq < 100_000L || victim1.dissociationReq < 10 || victim1.resolve < 50)
                            {
                                if(w.getRelationship(killer1.getNumber(), victim2.getNumber()) == -4 || victim2.isImpregnated() || victim2.isHypnotized() || victim2.isDrained() || victim2.isParasitized() || victim2.temptReq < 100_000L || victim2.dissociationReq < 10 || victim2.resolve < 50)
                                {
                                    if(w.getTechs()[40].isOwned() && !killer1.hesitated && (w.getRelationship(killer1.getNumber(), victim1.getNumber()) == 4 || w.getRelationship(killer1.getNumber(), victim2.getNumber()) == 4))
                                    {
                                        if(w.getRelationship(killer1.getNumber(), victim1.getNumber()) == 4)
                                        {
                                            if(w.getRelationship(killer1.getNumber(), victim2.getNumber()) == 4)
                                                w.append(t, String.format("%1$s calls out to the other Chosen, urging them to escape before they get caught up in ", killer1.getMainName()) + killer1.hisHer() + " final attack.");
                                            else
                                                w.append(t, String.format("%1$s prepares to launch a devastating attack in order to finish the battle, even though ", killer1.getMainName()) + victim2.getMainName() + " is in the way.");
                                        } else
                                        {
                                            w.append(t, String.format("%1$s prepares to launch a devastating attack in order to finish the battle, even though ", killer1.getMainName()) + victim1.getMainName() + " is in the way.");
                                        }
                                    } else
                                    {
                                        w.append(t, String.format("%1$s prepares to launch a devastating attack in order to finish the battle, even though ", killer1.getMainName()) + victim1.getMainName() + " and " + victim2.getMainName() + " are in the way.");
                                    }
                                } else
                                if(duration2 > duration1)
                                    w.append(t, String.format("%1$s buys time for ", killer1.getMainName()) + victim2.getMainName() + " to escape so that the two of them can work together to end this.");
                                else
                                if(w.getTechs()[40].isOwned() && !killer1.hesitated && w.getRelationship(killer1.getNumber(), victim1.getNumber()) == 4)
                                    w.append(t, String.format("%1$s calls out to ", killer1.getMainName()) + victim1.getMainName() + ", urging " + victim1.himHer() + " to escape before " + victim1.heShe() + " gets caught up in " + killer1.getMainName() + "'s final attack.");
                                else
                                    w.append(t, String.format("%1$s prepares to launch a devastating attack so that the battle can be finished after ", killer1.getMainName()) + victim2.getMainName() + " escapes, even though " + victim1.getMainName() + " is in the way.");
                            } else
                            if(w.getRelationship(killer1.getNumber(), victim2.getNumber()) == -4 || victim2.isImpregnated() || victim2.isHypnotized() || victim2.isDrained() || victim2.isParasitized() || victim2.temptReq < 100_000L || victim2.dissociationReq < 10 || victim2.resolve < 50)
                            {
                                if(duration1 > duration2)
                                    w.append(t, String.format("%1$s buys time for ", killer1.getMainName()) + victim1.getMainName() + " to escape so that the two of them can work together to end this.");
                                else
                                if(w.getTechs()[40].isOwned() && !killer1.hesitated && w.getRelationship(killer1.getNumber(), victim2.getNumber()) == 4)
                                    w.append(t, String.format("%1$s calls out to ", killer1.getMainName()) + victim2.getMainName() + ", urging " + victim2.himHer() + " to escape before " + victim2.heShe() + " gets caught up in " + killer1.getMainName() + "'s final attack.");
                                else
                                    w.append(t, String.format("%1$s prepares to launch a devastating attack in order to finish the battle, even though ", killer1.getMainName()) + victim2.getMainName() + " is in the way.");
                            } else
                            {
                                w.append(t, String.format("%1$s buys time for the other two Chosen to escape so that they all can work together to end this.", killer1.getMainName()));
                            }
                        } else
                        if(killer2 != null)
                        {
                            if(victim1.isImpregnated() || victim1.isHypnotized() || victim1.isDrained() || victim1.isParasitized() || victim1.temptReq < 100_000L || victim1.dissociationReq < 10 || victim1.resolve < 50)
                            {
                                if(w.getTechs()[40].isOwned())
                                {
                                    if(w.getRelationship(killer1.getNumber(), victim1.getNumber()) == 4 && !killer1.hesitated)
                                    {
                                        if(w.getRelationship(killer2.getNumber(), victim1.getNumber()) == 4 && !killer2.hesitated)
                                            w.append(t, String.format("%1$s and ", killer1.getMainName()) + killer2.getMainName() + " call out to " + victim1.getMainName() + ", urging " + victim1.himHer() + " to escape before " + victim1.heShe() + " gets caught up in their final attack.");
                                        else
                                            w.append(t, String.format("%1$s's captivity is preventing the other Chosen from ending the battle, and while ", victim1.getMainName()) + killer1.getMainName() + " looks conflicted, " + killer2.getMainName() + " is preparing to attack anyway.");
                                    } else
                                    if(w.getRelationship(killer2.getNumber(), victim1.getNumber()) == 4 && !killer2.hesitated)
                                        w.append(t, String.format("%1$s's captivity is preventing the other Chosen from ending the battle, and while ", victim1.getMainName()) + killer2.getMainName() + " looks conflicted, " + killer1.getMainName() + " is preparing to attack anyway.");
                                    else
                                        w.append(t, String.format("%1$s and ", killer1.getMainName()) + killer2.getMainName() + " prepare to launch their most devastating attacks in order to finish the battle, even though " + victim1.getMainName() + " is in the way.");
                                } else
                                {
                                    w.append(t, String.format("%1$s and ", killer1.getMainName()) + killer2.getMainName() + " prepare to launch their most devastating attacks in order to finish the battle, even though " + victim1.getMainName() + " is in the way.");
                                }
                            } else
                            if(w.getRelationship(killer1.getNumber(), victim1.getNumber()) == -4)
                            {
                                if(w.getRelationship(killer2.getNumber(), victim1.getNumber()) == -4)
                                    w.append(t, String.format("%1$s and ", killer1.getMainName()) + killer2.getMainName() + " prepare to launch their most devastating attacks in order to finish the battle, even though " + victim1.getMainName() + " is in the way.");
                                else
                                    w.append(t, String.format("%1$s's captivity is preventing the other Chosen from ending the battle, and while ", victim1.getMainName()) + killer2.getMainName() + " isn't willing to sacrifice " + victim1.getMainName() + "'s life in order to finish things sooner, " + killer1.getMainName() + " is.");
                            } else
                            if(w.getRelationship(killer2.getNumber(), victim1.getNumber()) == -4)
                                w.append(t, String.format("%1$s's captivity is preventing the other Chosen from ending the battle, and while ", victim1.getMainName()) + killer1.getMainName() + " isn't willing to sacrifice " + victim1.getMainName() + "'s life in order to finish things sooner, " + killer2.getMainName() + " is.");
                            else
                                w.append(t, String.format("%1$s's captivity is preventing the other Chosen from ending the battle, but ", victim1.getMainName()) + killer1.getMainName() + " and " + killer2.getMainName() + " aren't willing to sacrifice " + victim1.hisHer() + " life just to finish things a little bit sooner.");
                        } else
                        if(w.getRelationship(killer1.getNumber(), victim1.getNumber()) == -4 || victim1.isImpregnated() || victim1.isHypnotized() || victim1.isDrained() || victim1.isParasitized() || victim1.temptReq < 100_000L || victim1.dissociationReq < 10 || victim1.resolve < 50)
                        {
                            if(w.getTechs()[40].isOwned() && !killer1.hesitated && w.getRelationship(killer1.getNumber(), victim1.getNumber()) == 4)
                                w.append(t, String.format("%1$s calls out to ", killer1.getMainName()) + victim1.getMainName() + ", urging " + victim1.himHer() + " to escape before " + victim1.heShe() + " gets caught up in " + killer1.getMainName() + "'s final attack.");
                            else
                                w.append(t, String.format("%1$s prepares to launch a devastating attack in order to finish the battle, even though ", killer1.getMainName()) + victim1.getMainName() + " is in the way.");
                        } else
                        {
                            w.append(t, String.format("%1$s buys time for ", killer1.getMainName()) + victim1.getMainName() + " to escape so that the two of them can work together to finish this.");
                        }
                        w.append(t, "\n");
                    }
                } else
                {
                    while(c == null) 
                    {
                        c = w.getCombatants()[(int)(Math.random() * 3D)];
                        if(c != null && (c.isSurrounded() || c.isCaptured()))
                            c = null;
                    }
                    w.append(t, String.format("%1$s can't finish clearing out the Demons due to the risk of hitting the trapped ", c.getMainName()) + trappedChosen.getMainName() + " with friendly fire!\n");
                }
            } else
            {
                while(c == null) 
                {
                    c = w.getCombatants()[(int)(Math.random() * 3D)];
                    if(c != null && (c.isSurrounded() || c.isCaptured()))
                        c = null;
                }
                Boolean plural = false;
                if(w.getCombatants()[1] != null)
                    plural = true;
                if(w.exterminationMultiplier == 100)
                {
                    w.append(t, String.format("With the civilians evacuated, %1$s", c.getMainName()));
                    if(plural)
                        w.append(t, " and the other Chosen can start drawing on their full power!");
                    else
                        w.append(t, String.format(" can start drawing on %1$s", c.hisHer()) + " full power!");
                } else
                if(w.exterminationMultiplier == 150)
                    w.append(t, String.format("%1$s's attacks grow stronger and stronger, shattering windows and setting off alarms!", c.getMainName()));
                else
                if(w.exterminationMultiplier == 225)
                    w.append(t, String.format("%1$s moves like a blur, taking down a wide swath of Demons!", c.getMainName()));
                else
                if(w.exterminationMultiplier == 337)
                    w.append(t, String.format("A blast of energy from %1$s", c.getMainName()) + " brings down a small building in a cloud of rubble!");
                else
                if(w.exterminationMultiplier == 505)
                    w.append(t, String.format("The area is riddled with craters caused by the power of %1$s", c.getMainName()) + "'s attacks!");
                else
                if(w.exterminationMultiplier == 757)
                    w.append(t, String.format("The district is consumed by an enormous explosion as %1$s", c.getMainName()) + " blasts away the Demons!");
                w.append(t, "\n(Extermination power");
                if(w.cast[1] != null)
                    w.append(t, " per Chosen");
                w.append(t, String.format(": %1$s", String.valueOf((w.exterminationPerChosen * w.exterminationMultiplier) / 100)) + ")");
                w.append(t, "\n");
            }
        }
        for(int i = 0; i < w.getCombatants().length; i++)
            if(w.getCombatants()[i] != null)
            {
                w.append(t, "\n");
                if(w.getCombatants()[i].type == Chosen.Species.SUPERIOR)
                    w.append(t, "[SUPERIOR] ");
                if(w.getCombatants()[i].isSurrounded() && (w.getCombatants()[i].resolve > 0 || !w.finalBattle))
                {
                    w.orangeAppend(t, w.getCombatants()[i].getMainName() + ": ");
                    if(inseminated == 3 || orgasming == 3 || sodomized == 3 || broadcasted == 3)
                        w.orangeAppend(t, "In Orgy");
                    else
                    if(w.getCombatants()[i].isInseminated())
                        w.orangeAppend(t, "Inseminated");
                    else
                    if(w.getCombatants()[i].isOrgasming())
                        w.orangeAppend(t, "Orgasming");
                    else
                    if(w.getCombatants()[i].isSodomized())
                    {
                        if(w.tickle())
                            w.orangeAppend(t, "Laughing");
                        else
                        if(w.getCombatants()[i].getGender().equals("male"))
                            w.orangeAppend(t, "Tortured");
                        else
                            w.orangeAppend(t, "Sodomized");
                    } else
                    if(w.getCombatants()[i].isBroadcasted())
                        w.orangeAppend(t, "Broadcasted");
                    else
                    if(w.getCombatants()[i].tempted)
                        w.orangeAppend(t, "Tempted");
                    else
                        w.orangeAppend(t, "Surrounded");
                    if(w.getCombatants()[i].getSurroundDuration() > 1)
                        w.orangeAppend(t, " for " + w.getCombatants()[i].getSurroundDuration() + " more turns");
                    else
                        w.orangeAppend(t, " until next turn");
                } else
                if(w.getCombatants()[i].isCaptured())
                {
                    w.orangeAppend(t, w.getCombatants()[i].getMainName() + ": ");
                    if(w.getCombatants()[i].timesDetonated() > 0 && !w.adaptationsDisabled())
                    {
                        if(w.getCombatants()[i].getCaptureProgression() + w.getCombatants()[i].getINJULevel() + 1 >= w.getCaptureDuration())
                            w.orangeAppend(t, "Detonating next turn");
                        else
                        if(w.getCombatants()[i].getCaptureProgression() + w.getCombatants()[i].getINJULevel() + 2 == w.getCaptureDuration())
                            w.orangeAppend(t, "Detonating in 2 more turns");
                        else
                        if(w.getBodyStatus()[5] || w.getBodyStatus()[12] || w.getBodyStatus()[13] || w.getBodyStatus()[21] || w.usedForsaken != null)
                            w.orangeAppend(t, "Detonating in up to " + String.valueOf(w.getCaptureDuration() - w.getCombatants()[i].getCaptureProgression() - w.getCombatants()[i].getINJULevel()) + " more turns");
                        else
                            w.orangeAppend(t, "Detonating in " + String.valueOf(w.getCaptureDuration() - w.getCombatants()[i].getCaptureProgression() - w.getCombatants()[i].getINJULevel()) + " more turns");
                    } else
                    if(w.getCombatants()[i].getCaptureProgression() < w.getCaptureDuration())
                        w.orangeAppend(t, "Captured for " + (w.getCaptureDuration() - w.getCombatants()[i].getCaptureProgression()) + 1 + " more turns");
                    else
                        w.orangeAppend(t, "Captured until next turn");
                } else
                if(!w.getCombatants()[i].alive)
                    w.redAppend(t, w.getCombatants()[i].getMainName() + ": Killed in Action");
                else
                if(w.finalBattle && w.getCombatants()[i].resolve <= 0)
                    w.greenAppend(t, w.getCombatants()[i].getMainName() + ": Resolve Broken!");
                else
                if(w.getCombatants()[i].dissociated)
                {
                    if(w.getCombatants()[0].dissociated && w.getCombatants()[1].dissociated && w.getCombatants()[2].dissociated)
                        w.greenAppend(t, w.getCombatants()[i].getMainName() + ": Catatonic");
                    else
                        w.greenAppend(t, w.getCombatants()[i].getMainName() + ": Fleeing");
                } else
                if(w.getCombatants()[i].surroundPossible(w))
                {
                    if(!w.dissociationSurroundPossible())
                        w.purpleAppend(t, w.getCombatants()[i].getMainName() + ": Opening Level " + String.valueOf(w.getCombatants()[i].getFEAROpening(w) + w.getCombatants()[i].getPAINOpening() + w.getCombatants()[i].getDISGOpening() + w.getCombatants()[i].getSHAMOpening(w)) + " vs. Defense Level " + w.getCombatants()[i].getDefenseLevel());
                    else
                        w.purpleAppend(t, w.getCombatants()[i].getMainName() + ": Open to Surround");
                } else
                if(w.getCombatants()[i].getDefenseLevel() > 9000)
                    w.append(t, String.format("%1$s: Flying Above Battlefield", w.getCombatants()[i].getMainName()));
                else
                    w.append(t, String.format("%1$s: Opening Level ", w.getCombatants()[i].getMainName()) + String.valueOf(w.getCombatants()[i].getFEAROpening(w) + w.getCombatants()[i].getPAINOpening() + w.getCombatants()[i].getDISGOpening() + w.getCombatants()[i].getSHAMOpening(w)) + " vs. Defense Level " + w.getCombatants()[i].getDefenseLevel());
                if(w.finalBattle && w.getCombatants()[i].resolve > 0 && w.getCombatants()[i].alive)
                    w.append(t, String.format(" [Resolve at %1$s", w.getCombatants()[i].resolve) + "%]");
            }

        if(w.usedForsaken != null)
        {
            w.append(t, "\n\n" + w.usedForsaken.mainName + ": ");
            int occupied = -1;
            for(int i = 0; i < w.getCombatants().length; i++)
                if(w.getCombatants()[i] != null && w.getCombatants()[i].captured)
                    occupied = i;

            if(occupied >= 0)
                w.purpleAppend(t, "Busy with " + w.getCombatants()[occupied].mainName);
            else
            if(w.usedForsaken.injured > 1)
            {
                w.redAppend(t, "Stunned for " + w.usedForsaken.injured + " turns");
                changePortrait(w.usedForsaken.gender, w.usedForsaken.type, displayedCivilians[3], true, w, w.nameCombatants(), 3, Emotion.SWOON, Emotion.SWOON);
            } else
            if(w.usedForsaken.injured == 1)
            {
                w.redAppend(t, "Stunned until next turn");
                changePortrait(w.usedForsaken.gender, w.usedForsaken.type, displayedCivilians[3], true, w, w.nameCombatants(), 3, Emotion.SWOON, Emotion.SWOON);
            } else
            {
                w.greenAppend(t, "Ready to capture target");
                if(w.usedForsaken.flavorObedience() < 20)
                    changePortrait(w.usedForsaken.gender, w.usedForsaken.type, displayedCivilians[3], true, w, w.nameCombatants(), 3, Emotion.ANGER, Emotion.NEUTRAL);
                else
                if(w.usedForsaken.flavorObedience() < 40)
                    changePortrait(w.usedForsaken.gender, w.usedForsaken.type, displayedCivilians[3], true, w, w.nameCombatants(), 3, Emotion.ANGER, Emotion.SHAME);
                else
                if(w.usedForsaken.flavorObedience() < 61)
                    changePortrait(w.usedForsaken.gender, w.usedForsaken.type, displayedCivilians[3], true, w, w.nameCombatants(), 3, Emotion.SHAME, Emotion.STRUGGLE);
                else
                if(w.usedForsaken.flavorObedience() < 81)
                    changePortrait(w.usedForsaken.gender, w.usedForsaken.type, displayedCivilians[3], true, w, w.nameCombatants(), 3, Emotion.FOCUS, Emotion.ANGER);
                else
                    changePortrait(w.usedForsaken.gender, w.usedForsaken.type, displayedCivilians[3], true, w, w.nameCombatants(), 3, Emotion.FOCUS, Emotion.JOY);
            }
        }
        if(w.getRallyBonus() > 0)
            w.append(t, "\n\nMorale bonus: incoming trauma decreased by " + String.valueOf(w.getRallyBonus() / 6) + "%");
        if(w.getDistractBonus() > 0)
            w.append(t, "\n\nThralls distracted: damage to surrounded Chosen decreased by " + String.valueOf(w.getDistractBonus() / 3) + "%");
        if(w.getBarrierMulti() > 10000L)
            w.append(t, "\n\nDemonic barrier: all damage increased by " + String.valueOf(w.getBarrierMulti() / 100L - 100L) + "%");
        int targets = 0;
        int targetFound = 0;
        int defeated = 0;
        int dissociated = 0;
        int trapped = 0;
        for(int i = 0; i < 3; i++)
            if(w.getCombatants()[i] != null)
                if(!w.finalBattle)
                {
                    if(!w.getCombatants()[i].dissociated)
                    {
                        targets++;
                        targetFound = i;
                    } else
                    {
                        dissociated++;
                    }
                } else
                if(w.getCombatants()[i].isCaptured() || w.getCombatants()[i].isSurrounded() && (w.getCombatants()[i].isDefiled() || w.getCombatants()[i].getHATELevel() < 3 && w.getCombatants()[i].getPLEALevel() < 3 && w.getCombatants()[i].getINJULevel() < 3 && w.getCombatants()[i].getEXPOLevel() < 3 && w.getCombatants()[i].grind && w.getCombatants()[i].caress && w.getCombatants()[i].pummel && w.getCombatants()[i].humiliate))
                    trapped++;
                else
                if(w.getCombatants()[i].alive && w.getCombatants()[i].resolve > 0)
                {
                    targets++;
                    targetFound = i;
                } else
                {
                    defeated++;
                }

        if(targets == 1)
        {
            if(w.getCast()[1] != null)
                if(w.finalBattle && defeated > 0)
                    w.append(t, "\n\n" + w.getCombatants()[targetFound].getMainName() + " is still resisting!");
                else
                if(w.getCombatants()[1] != null)
                    w.append(t, "\n\n" + w.getCombatants()[targetFound].getMainName() + " is trying to stall until the team can fight at full strength!");
                else
                    w.append(t, "\n\n" + w.getCombatants()[targetFound].getMainName() + " is fighting alone!");
        } else
        if(targets == 0)
        {
            if(w.getCombatants()[1] == null)
                w.append(t, "\n\n" + w.getCombatants()[0].getMainName() + "'s allies haven't shown up yet!");
            else
            if(w.getCombatants()[0].dissociated)
                w.append(t, "\n\nThe Chosen are too traumatized to notice anything done to them!");
            else
                w.append(t, "\n\nThe Chosen are struggling to escape the Demons' clutches!");
        } else
        {
            w.chatter(t);
            w.append(t, "\n\nWho will you target?");
        }
        if(targets == 1 && (w.getCombatants()[1] == null || defeated == 2 || dissociated == 2))
        {
            PickAction(t, p, f, w, w.getCombatants()[targetFound], initiative);
        } else
        {
            p.removeAll();
            for(int i = 0; i < 3; i++)
                if(w.getCombatants()[i] != null && (!w.finalBattle || w.getCombatants()[i].resolve > 0 && w.getCombatants()[i].alive) && !w.getCombatants()[i].dissociated)
                {
                    final int thisChosen = i;
                    class TargetButton extends AbstractAction
                    {

                        public TargetButton(String text, String desc) {
                            super(text);
                            this.putValue(SHORT_DESCRIPTION, desc);
                        }
                        public void actionPerformed(ActionEvent e)
                        {
                            w.append(t, "\n\n" + w.getSeparator());
                            Project.PickAction(t, p, f, w, w.getCombatants()[thisChosen], initiative);
                        }
                    }

                    final Action TargetAction = new TargetButton(w.getCombatants()[thisChosen].getMainName(), "Hotkey:");
                    JButton Target = new JButton(TargetAction) {

                        public Point getToolTipLocation(MouseEvent e)
                        {
                            return new Point(0, -30);
                        }

                    };
                    if((w.getCombatants()[i].getCurrentHATE() >= 10000L || w.getCombatants()[i].getCurrentPLEA() >= 10000L || w.getCombatants()[i].getCurrentINJU() >= 10000L || w.getCombatants()[i].getCurrentEXPO() >= 10000L) && w.getCombatants()[i].isSurrounded() && !w.getCombatants()[i].isDefiled())
                        if(w.getCombatants()[i].getCurrentHATE() >= 10000L && inseminated > 0 || w.getCombatants()[i].getCurrentPLEA() >= 10000L && orgasming > 0 || w.getCombatants()[i].getCurrentINJU() >= 10000L && sodomized > 0 || w.getCombatants()[i].getCurrentEXPO() >= 10000L && broadcasted > 0)
                            Target.setBackground(PURPLISH);
                        else
                            Target.setBackground(YELLOWISH);
                    Target.getInputMap(2).put(KeyStroke.getKeyStroke((new StringBuilder()).append(thisChosen + 1).toString()), "pressed");
                    if(w.onTrack && w.getActions().length > w.getCurrentAction() && (w.getActions()[w.getCurrentAction()] - 1) / 14 == w.getCombatants()[thisChosen].getNumber())
                        Target.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                    Target.getActionMap().put("pressed", TargetAction);
                    p.add(Target);
                }

            class PassButton extends AbstractAction
            {
                public PassButton(String text, String desc) {
                    super(text);
                    this.putValue(SHORT_DESCRIPTION, desc);
                }
                public void actionPerformed(ActionEvent e)
                {
                    Project.advanceAction(p, w, 0);
                    if(w.getTechs()[30].isOwned() && !w.progressExtermination(0))
                    {
                        p.removeAll();
                        w.increaseBarrier(t);
                        class ContinueButton extends AbstractAction
                        {
                            public ContinueButton(String text, String desc) {
                                super(text);
                                this.putValue(SHORT_DESCRIPTION, desc);
                            }
                            public void actionPerformed(ActionEvent e)
                            {
                                Project.EnemyTurn(t, p, f, w, initiative, 0);
                            }
                        }
                        final Action ContinueAction = new ContinueButton("Continue", "Hotkey:");
                        JButton Continue = new JButton(ContinueAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -30);
                            }
                        };
                        Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Continue.getActionMap().put("pressed", ContinueAction);
                        p.add(Continue);
                        p.validate();
                        p.repaint();
                    } else
                    {
                        Project.EnemyTurn(t, p, f, w, initiative, 0);
                    }
                }
            }

            final Action PassAction = new PassButton("Do Nothing", "Hotkey:");
            JButton Pass = new JButton(PassAction) {

                public Point getToolTipLocation(MouseEvent e)
                {
                    return new Point(0, -30);
                }

            };
            Pass.setForeground(Color.GRAY);
            Pass.getInputMap(2).put(KeyStroke.getKeyStroke(70, 0), "pressed");
            if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == 0)
                Pass.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
            Pass.getActionMap().put("pressed", PassAction);
            if(w.getTechs()[30].isOwned() && !w.progressExtermination(0))
            {
                Pass.setText("Barrier");
                Pass.setToolTipText("+5% damage for rest of battle");
            }
            p.add(Pass);
            int occupied = 0;
            for(int i = 0; i < 3; i++)
                if(w.getCombatants()[i] != null)
                    if(w.getCombatants()[i].isSurrounded())
                    {
                        if(w.getCombatants()[i].getSurroundDuration() > 0)
                            occupied += w.getCombatants()[i].getSurroundDuration();
                        else
                            occupied++;
                    } else
                    if(w.getCombatants()[i].isCaptured())
                        occupied += (w.getCaptureDuration() - w.getCombatants()[i].getCaptureProgression()) + 1;

            final int occupiedBonus = occupied / 5;
            class RetreatButton extends AbstractAction
            {

                public RetreatButton(String text, String desc) {
                    super(text);
                    this.putValue(SHORT_DESCRIPTION, desc);
                }
                public void actionPerformed(ActionEvent e)
                {
                    p.removeAll();
                    w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                    if(occupiedBonus > 0)
                        w.append(t, String.format("Retreat and end the battle immediately for +%1$s", occupiedBonus) + " Evil Energy?");
                    else
                        w.append(t, "Really retreat?  You will not gain any bonus Evil Energy!");
                    JButton Confirm = new JButton("Confirm");
                    Confirm.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            p.removeAll();
                            w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                            String trapped[] = new String[3];
                            String free[] = new String[3];
                            int trappedNumber = 0;
                            for(int i = 0; i < 3; i++)
                                if(w.getCombatants()[i] != null)
                                    if(w.getCombatants()[i].isSurrounded() || w.getCombatants()[i].isCaptured())
                                    {
                                        for(int j = 0; j < 3; j++)
                                            if(trapped[j] == null)
                                            {
                                                trapped[j] = w.getCombatants()[i].getMainName();
                                                trappedNumber++;
                                                j = 3;
                                            }

                                    } else
                                    {
                                        for(int j = 0; j < 3; j++)
                                            if(free[j] == null)
                                            {
                                                free[j] = w.getCombatants()[i].getMainName();
                                                j = 3;
                                            }

                                    }

                            if(w.getCombatants()[1] == null)
                            {
                                for(int i = 0; i < 3; i++)
                                    if(w.getCast()[i] != null && !w.getCast()[i].equals(w.getCombatants()[0]))
                                        if(free[0] == null)
                                            free[0] = w.getCast()[i].mainName;
                                        else
                                        if(free[1] == null)
                                            free[1] = w.getCast()[i].mainName;
                                        else
                                            free[2] = w.getCast()[i].mainName;

                            } else
                            if(w.getCombatants()[2] == null)
                            {
                                for(int i = 0; i < 3; i++)
                                    if(w.getCast()[i] != null && !w.getCast()[i].equals(w.getCombatants()[0]) && !w.getCast()[i].equals(w.getCombatants()[1]))
                                        if(free[0] == null)
                                            free[0] = w.getCast()[i].mainName;
                                        else
                                        if(free[1] == null)
                                            free[1] = w.getCast()[i].mainName;
                                        else
                                            free[2] = w.getCast()[i].mainName;

                            }
                            w.append(t, "You order your Demons to flee back into the tunnels beneath the city along with their captive victims.  ");
                            if(w.getCast()[1] == null)
                            {
                                if(trappedNumber == 0)
                                    w.append(t, String.format("However, %1$s", free[0]) + " is quick to pursue, cutting your forces down from behind and stopping them from taking any significant number of civilians back to the hive.");
                                else
                                    w.append(t, String.format("%1$s is unable to follow until plenty of civilians are already on their way to the hive.", String.valueOf(trapped[0])));
                            } else
                            if(w.getCast()[2] == null)
                            {
                                if(trappedNumber == 0)
                                    w.append(t, "However, the two Chosen cut your forces down from behind, freeing most of the civilians before they can be brought to the hive.");
                                else
                                if(trappedNumber == 1)
                                    w.append(t, String.format("With %1$s", trapped[0]) + " unable to give chase, the risk of splitting the team forces " + free[0] + " to give up and let you take the civilians to the hive.");
                                else
                                    w.append(t, "The Chosen have to finish dealing with their own problems before they can try to stop you, and by then, plenty of your forces have escaped.");
                            } else
                            if(trappedNumber == 0 || occupiedBonus == 0)
                                w.append(t, "However, the three Chosen cut your forces down from behind, freeing most of the civilians before they can be brought to the hive.");
                            else
                            if(trappedNumber == 1)
                                w.append(t, String.format("%1$s and ", free[0]) + free[1] + " try to give chase, but with " + trapped[0] + " unable to follow, they're forced to give up due to the risk of splitting the team.");
                            else
                            if(trappedNumber == 2)
                                w.append(t, String.format("%1$s tries to stop them, but with ", free[0]) + trapped[0] + " and " + trapped[1] + " unable to help, you're able to get plenty of victims to the hive.");
                            else
                                w.append(t, "The Chosen have to finish dealing with their own problems before they can try to stop you, and by then, plenty of your forces have escaped.");
                            if(occupiedBonus > 0)
                                w.append(t, "\n\n+" + occupiedBonus + " Evil Energy");
                            Project.advanceAction(p, w, 43);
                            w.addEnergy(occupiedBonus);
                            JButton Continue = new JButton("Continue");
                            Continue.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.PostBattle(t, p, f, w);
                                }
                            });
                            p.add(Continue);
                            p.validate();
                            p.repaint();
                        }
                    });
                    p.add(Confirm);
                    JButton Cancel = new JButton("Cancel");
                    Cancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            w.append(t, "\n\n" + w.getSeparator() + "\n");
                            Project.PickTarget(t, p, f, w);
                        }
                    });
                    p.add(Cancel);
                    p.validate();
                    p.repaint();
                }
            }
            final Action RetreatAction = new RetreatButton("Retreat (" + occupiedBonus + ")", "End battle immediately for +" + occupiedBonus + " EE");
            JButton Retreat = new JButton(RetreatAction) {

                public Point getToolTipLocation(MouseEvent e)
                {
                    return new Point(0, -30);
                }

            };
            if(w.getTechs()[19].isOwned() && !w.finalBattle)
                p.add(Retreat);
            if(w.writePossible())
                addWriteButton(p, w);
            p.validate();
            p.repaint();
        }
        w.readCommentary(t);
    }

    public static void advanceAction(JPanel p, WorldState w, int action)
    {
        Boolean actionMatches = true;
        if(w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] != action)
        {
            actionMatches = false;
            w.truncateCommentary(w.getCurrentAction());
        }
        if(w.writePossible())
            if(w.getCurrentComment().length() > 0)
                w.writeCommentary(w.getCurrentComment());
            else
            if(w.getCommentary().length <= w.getCurrentAction() || !actionMatches)
            {
                String generated = "";
                if(action == 0)
                {
                    if(w.getTechs()[30].isOwned() && !w.progressExtermination(0))
                        generated = "Deepen your barrier.";
                    else
                        generated = "Do nothing.";
                } else
                if(action == 43)
                    generated = "Retreat from the battle.";
                else
                if(action > 43)
                {
                    int type = (action - 44) / 3;
                    int target = (action - 44) % 3;
                    String targetedChosen = w.getCast()[target].getMainName();
                    if(type == 0)
                        generated = "Tempt ";
                    generated = (new StringBuilder(generated)).append(targetedChosen).append(".").toString();
                } else
                {
                    int target = (action - 1) / 14;
                    int type = (action - 1) % 14 + 1;
                    String targetedChosen = w.getCast()[target].getMainName();
                    if(type == 1)
                        generated = "Surround ";
                    else
                    if(type == 2)
                        generated = "Capture ";
                    else
                    if(type == 3)
                        generated = "Threaten ";
                    else
                    if(type == 4)
                        generated = "Slime ";
                    else
                    if(type == 5)
                    {
                        if(w.tickle())
                            generated = "Poke ";
                        else
                            generated = "Attack ";
                    } else
                    if(type == 6)
                    {
                        generated = "Taunt ";
                    } else
                    {
                        if(w.getTechs()[31].isOwned() && !w.getCast()[target].isSurrounded())
                            if(!w.getCast()[target].surroundPossible(w))
                                generated = (new StringBuilder(generated)).append("Capture and then ").toString();
                            else
                                generated = (new StringBuilder(generated)).append("Surround and then ").toString();
                        if(type == 7)
                            generated = (new StringBuilder(generated)).append("Grind against ").toString();
                        else
                        if(type == 8)
                            generated = (new StringBuilder(generated)).append("Caress ").toString();
                        else
                        if(type == 9)
                        {
                            if(w.tickle())
                                generated = (new StringBuilder(generated)).append("Tickle ").toString();
                            else
                                generated = (new StringBuilder(generated)).append("Pummel ").toString();
                        } else
                        if(type == 10)
                            generated = (new StringBuilder(generated)).append("Humiliate ").toString();
                        else
                        if(type == 11)
                            generated = (new StringBuilder(generated)).append("Inseminate ").toString();
                        else
                        if(type == 12)
                            generated = (new StringBuilder(generated)).append("Force Orgasm on ").toString();
                        else
                        if(type == 13)
                        {
                            if(w.tickle())
                                generated = (new StringBuilder(generated)).append("Force Laughter from ").toString();
                            else
                            if(w.getCast()[target].getGender().equals("male"))
                                generated = (new StringBuilder(generated)).append("Torture ").toString();
                            else
                                generated = (new StringBuilder(generated)).append("Sodomize ").toString();
                        } else
                        if(type == 14)
                            generated = (new StringBuilder(generated)).append("Broadcast ").toString();
                    }
                    generated = (new StringBuilder(generated)).append(targetedChosen).append(".").toString();
                }
                w.writeCommentary(generated);
            }
        w.nextAction(action);
    }

    public static void addWriteButton(JPanel p, final WorldState w)
    {
        final JButton Comment = new JButton("Comment");
        Comment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String prompt = "Enter your comment here.  Leave blank to ";
                if(w.getCurrentComment().length() > 0)
                    prompt = (new StringBuilder(prompt)).append("keep the comment you already wrote.").toString();
                else
                if(w.getCommentary().length > w.getCurrentAction())
                    prompt = (new StringBuilder(prompt)).append("keep the previous playthrough's comment.").toString();
                else
                    prompt = (new StringBuilder(prompt)).append("generate a default comment describing your action.").toString();
                String input = JOptionPane.showInputDialog(prompt);
                if(input != null && input.length() > 0)
                {
                    w.setCurrentComment(input);
                    Comment.setToolTipText("\"" + input + "\"");
                }
            }
        });
        Comment.setForeground(Color.GRAY);
        Comment.setToolTipText("No comment currently stored.");
        p.add(Comment);
    }

    public static void PickAction(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Chosen c, final Chosen initiative[])
    {
        final Color YELLOWISH = new Color(255, 225, 125);
        final Color PURPLISH = new Color(225, 125, 255);
        final Color REDDISH = new Color(255, 145, 145);
        int inseminated = 0;
        int orgasming = 0;
        int sodomized = 0;
        int broadcasted = 0;
        for(int i = 0; i < 3; i++)
            if(w.getCombatants()[i] != null)
                if(w.getCombatants()[i].isInseminated())
                    inseminated++;
                else
                if(w.getCombatants()[i].isOrgasming())
                    orgasming++;
                else
                if(w.getCombatants()[i].isSodomized())
                    sodomized++;
                else
                if(w.getCombatants()[i].isBroadcasted())
                    broadcasted++;

        class ContinueButton extends AbstractAction
        {

            public ContinueButton(String text, String desc) {
                super(text);
                this.putValue(SHORT_DESCRIPTION, desc);
            }
            public void actionPerformed(ActionEvent e)
            {
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }
        }

        final Action ContinueAction = new ContinueButton("Continue", "Hotkey:");
        String attackName = "Attack";
        if(w.tickle())
            attackName = "Poke";
        class AttackButton extends AbstractAction
        {

            public AttackButton(String text, String desc) {
                super(text);
                this.putValue(SHORT_DESCRIPTION, desc);
            }
            public void actionPerformed(ActionEvent e)
            {
                if(w.finalBattle && w.getTechs()[44].isOwned())
                    w.finalAttack(t, w, c);
                else
                    c.Attack(t, p, f, w);
                Project.advanceAction(p, w, c.getNumber() * 14 + 5);
                p.removeAll();
                JButton Continue = new JButton(ContinueAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -30);
                    }
                };
                Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Continue.getActionMap().put("pressed", ContinueAction);
                p.add(Continue);
                p.validate();
                p.repaint();
            }
        }

        final Action AttackAction = new AttackButton("Attack", "Use Attack");
        class SlimeButton extends AbstractAction
        {

            public SlimeButton(String text, String desc) {
                super(text);
                this.putValue(SHORT_DESCRIPTION, desc);
            }
            public void actionPerformed(ActionEvent e)
            {
                if(w.finalBattle && w.getTechs()[43].isOwned() && c.isHypnotized())
                    w.finalSlime(t, w, c);
                else
                    c.Slime(t, p, f, w);
                Project.advanceAction(p, w, c.getNumber() * 14 + 4);
                p.removeAll();
                JButton Continue = new JButton(ContinueAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -30);
                    }
                };
                Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Continue.getActionMap().put("pressed", ContinueAction);
                p.add(Continue);
                p.validate();
                p.repaint();
            }
        }

        final Action SlimeAction = new SlimeButton("Slime", "Use Slime");
        class TauntButton extends AbstractAction
        {

            public TauntButton(String text, String desc) {
                super(text);
                this.putValue(SHORT_DESCRIPTION, desc);
            }
            public void actionPerformed(ActionEvent e)
            {
                if(w.finalBattle && w.getTechs()[45].isOwned() && c.isParasitized() && c.surroundPossible(w))
                    w.finalTaunt(t, w, c);
                else
                    c.Taunt(t, p, f, w);
                Project.advanceAction(p, w, c.getNumber() * 14 + 6);
                p.removeAll();
                JButton Continue = new JButton(ContinueAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -30);
                    }
                };
                Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Continue.getActionMap().put("pressed", ContinueAction);
                p.add(Continue);
                p.validate();
                p.repaint();
            }
        }

        final Action TauntAction = new TauntButton("Taunt", "Use Taunt");
        class ThreatenButton extends AbstractAction
        {

            public ThreatenButton(String text, String desc) {
                super(text);
                this.putValue(SHORT_DESCRIPTION, desc);
            }
            public void actionPerformed(ActionEvent e)
            {
                Boolean impregnatedAlly = false;
                for(int i = 0; i < 3; i++)
                    if(i != c.getNumber() && w.getCast()[i] != null && w.getCast()[i].isImpregnated() && w.getCast()[i].alive)
                        impregnatedAlly = true;

                if(w.finalBattle && w.getTechs()[42].isOwned() && impregnatedAlly)
                    w.finalThreaten(t, w, c);
                else
                    c.Threaten(t, p, f, w);
                Project.advanceAction(p, w, c.getNumber() * 14 + 3);
                p.removeAll();
                JButton Continue = new JButton(ContinueAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -30);
                    }
                };
                Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Continue.getActionMap().put("pressed", ContinueAction);
                p.add(Continue);
                p.validate();
                p.repaint();
            }
        }

        final Action ThreatenAction = new ThreatenButton("Threaten", "Use Threaten");
        class GrindButton extends AbstractAction
        {

            public GrindButton(String text, String desc) {
                super(text);
                this.putValue(SHORT_DESCRIPTION, desc);
            }
            public void actionPerformed(ActionEvent e)
            {
                c.beginGrind();
                Project.advanceAction(p, w, c.getNumber() * 14 + 7);
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }
        }

        final Action GrindAction = new GrindButton("Grind", "Use Grind");
        class CaressButton extends AbstractAction
        {

            public CaressButton(String text, String desc) {
                super(text);
                this.putValue(SHORT_DESCRIPTION, desc);
            }
            public void actionPerformed(ActionEvent e)
            {
                c.beginCaress();
                Project.advanceAction(p, w, c.getNumber() * 14 + 8);
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }
        }

        final Action CaressAction = new CaressButton("Caress", "Use Caress");
        String pummelName = "Pummel";
        if(w.tickle())
            pummelName = "Tickle";
        class PummelButton extends AbstractAction
        {

            public PummelButton(String text, String desc) {
                super(text);
                this.putValue(SHORT_DESCRIPTION, desc);
            }
            public void actionPerformed(ActionEvent e)
            {
                c.beginPummel();
                Project.advanceAction(p, w, c.getNumber() * 14 + 9);
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }
        }

        final Action PummelAction = new PummelButton(pummelName, "Use " + pummelName);
        class HumiliateButton extends AbstractAction
        {

            public HumiliateButton(String text, String desc) {
                super(text);
                this.putValue(SHORT_DESCRIPTION, desc);
            }
            public void actionPerformed(ActionEvent e)
            {
                c.beginHumiliate();
                Project.advanceAction(p, w, c.getNumber() * 14 + 10);
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }
        }

        final Action HumiliateAction = new HumiliateButton("Humiliate", "Use Humiliate");
        class InseminateButton extends AbstractAction
        {

            public InseminateButton(String text, String desc) {
                super(text);
                this.putValue(SHORT_DESCRIPTION, desc);
            }
            public void actionPerformed(ActionEvent e)
            {
                c.beginInseminate();
                Project.advanceAction(p, w, c.getNumber() * 14 + 11);
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }
        }

        final Action InseminateAction = new InseminateButton("Inseminate", "Use Inseminate");
        class ForceOrgasmButton extends AbstractAction
        {

            public ForceOrgasmButton(String text, String desc) {
                super(text);
                this.putValue(SHORT_DESCRIPTION, desc);
            }
            public void actionPerformed(ActionEvent e)
            {
                c.beginOrgasm();
                Project.advanceAction(p, w, c.getNumber() * 14 + 12);
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }
        }

        final Action ForceOrgasmAction = new ForceOrgasmButton("Force Orgasm", "Use Force Orgasm");
        String SodomizeName = "Sodomize";
        if(w.tickle())
            SodomizeName = "Force Laughter";
        else
        if(c.getGender().equals("male"))
            SodomizeName = "Torture";
        class SodomizeButton extends AbstractAction
        {

            public SodomizeButton(String text, String desc) {
                super(text);
                this.putValue(SHORT_DESCRIPTION, desc);
            }
            public void actionPerformed(ActionEvent e)
            {
                c.beginSodomize();
                Project.advanceAction(p, w, c.getNumber() * 14 + 13);
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }
        }

        final Action SodomizeAction = new SodomizeButton(SodomizeName, "Use " + SodomizeName);
        class BroadcastButton extends AbstractAction
        {

            public BroadcastButton(String text, String desc) {
                super(text);
                this.putValue(SHORT_DESCRIPTION, desc);
            }
            public void actionPerformed(ActionEvent e)
            {
                c.beginBroadcast();
                Project.advanceAction(p, w, c.getNumber() * 14 + 14);
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }
        }

        final Action BroadcastAction = new BroadcastButton("Broadcast", "Use Broadcast");
        class TemptButton extends AbstractAction
        {

            public TemptButton(String text, String desc) {
                super(text);
                this.putValue(SHORT_DESCRIPTION, desc);
            }
            public void actionPerformed(ActionEvent e)
            {
                if(w.finalBattle)
                    w.finalTempt(t, c);
                else
                    c.beginTempt();
                Project.advanceAction(p, w, c.getNumber() + 44);
                if(w.finalBattle)
                {
                    p.removeAll();
                    JButton Continue = new JButton(ContinueAction) {

                        public Point getToolTipLocation(MouseEvent e)
                        {
                            return new Point(0, -30);
                        }
                    };
                    Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                    Continue.getActionMap().put("pressed", ContinueAction);
                    p.add(Continue);
                    p.validate();
                    p.repaint();
                } else
                {
                    Project.EnemyTurn(t, p, f, w, initiative, 0);
                }
            }
        }

        final Action TemptAction = new TemptButton("Tempt", "Use Tempt");
        String ForsakenDefilerName = "";
        if(w.usedForsaken != null && w.usedForsaken.defilerType > 0)
            if(w.usedForsaken.defilerType == 1)
                ForsakenDefilerName = "Penetrate";
            else
            if(w.usedForsaken.defilerType == 2)
                ForsakenDefilerName = "Force Orgasm";
            else
            if(w.usedForsaken.defilerType == 3)
                ForsakenDefilerName = "Torture";
            else
            if(w.usedForsaken.defilerType == 4)
                ForsakenDefilerName = "Broadcast";
            else
            if(w.usedForsaken.defilerType == 5)
                ForsakenDefilerName = "Tempt";
            else
            if(w.usedForsaken.defilerType == 6)
                ForsakenDefilerName = "Orgy";
        class ForsakenDefilerButton extends AbstractAction
        {
            public ForsakenDefilerButton(String text, String desc) {
                super(text);
                this.putValue(SHORT_DESCRIPTION, desc);
            }
            public void actionPerformed(ActionEvent e)
            {
                Project.advanceAction(p, w, c.getNumber() * 14 + 2);
                if(w.finalBattle && w.usedForsaken.defilerType == 5)
                {
                    w.usedForsaken.ForsakenFinalTempt(t, w, c);
                    p.removeAll();
                    JButton Continue = new JButton(ContinueAction) {

                        public Point getToolTipLocation(MouseEvent e)
                        {
                            return new Point(0, -30);
                        }
                    };
                    Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                    Continue.getActionMap().put("pressed", ContinueAction);
                    p.add(Continue);
                    p.validate();
                    p.repaint();
                } else
                if(w.usedForsaken.defilerType == 6)
                {
                    w.usedForsaken.addToOrgy(t, w, c);
                    p.removeAll();
                    JButton Continue = new JButton(ContinueAction) {

                        public Point getToolTipLocation(MouseEvent e)
                        {
                            return new Point(0, -30);
                        }
                    };
                    Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                    Continue.getActionMap().put("pressed", ContinueAction);
                    p.add(Continue);
                    p.validate();
                    p.repaint();
                } else
                {
                    w.usedForsaken.defiling = true;
                    Project.EnemyTurn(t, p, f, w, initiative, 0);
                }
            }
        }

        final Action ForsakenDefilerAction = new ForsakenDefilerButton("Forsaken Defiler", "Use Forsaken Defiler");
        final int finalInseminated = inseminated;
        final int finalOrgasming = orgasming;
        final int finalSodomized = sodomized;
        final int finalBroadcasted = broadcasted;
        class SurroundButton extends AbstractAction {
			public SurroundButton(String text, String desc) {
				super(text);
				putValue(SHORT_DESCRIPTION, desc);
			}
            public void actionPerformed(ActionEvent e)
            {
                w.setSurroundTarget(c);
                if(w.getTechs()[31].isOwned())
                {
                    p.removeAll();
                    int defilers = 0;
                    Boolean plusPossible = false;
                    Boolean orgyPossible = false;
                    String PAINname = "PAIN";
                    String INJUname = "INJU";
                    if(w.tickle())
                    {
                        PAINname = "TICK";
                        INJUname = "ANTI";
                    }
                    if(!c.getGrind())
                    {
                        JButton Grind = new JButton(GrindAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }
                        };
                        Grind.setToolTipText("<html><center>Inflicts HATE along with<br>FEAR, DISG, " + PAINname + ", and SHAM<br>Can cause tier-1 Morality or Dignity Break</center></html>");
                        p.add(Grind);
                        Grind.getInputMap(2).put(KeyStroke.getKeyStroke("1"), "pressed");
                        if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 7)
                            Grind.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Grind.getActionMap().put("pressed", GrindAction);
                    }
                    if(!c.getCaress())
                    {
                        JButton Caress = new JButton(CaressAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }
                        };
                        Caress.setToolTipText("<html><center>Inflicts PLEA along with<br>DISG, " + PAINname + ", SHAM, and FEAR<br>Can cause tier-1 Innocence or Confidence Break</center></html>");
                        p.add(Caress);
                        Caress.getInputMap(2).put(KeyStroke.getKeyStroke("2"), "pressed");
                        if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 8)
                            Caress.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Caress.getActionMap().put("pressed", CaressAction);
                    }
                    if(!c.getPummel())
                    {
                        JButton Pummel = new JButton(PummelAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }
                        };
                        Pummel.setToolTipText("<html><center>Inflicts " + INJUname + " along with<br>" + PAINname + ", SHAM, FEAR, and DISG<br>Can cause tier-1 Morality or Confidence Break</center></html>");
                        p.add(Pummel);
                        Pummel.getInputMap(2).put(KeyStroke.getKeyStroke("3"), "pressed");
                        if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 9)
                            Pummel.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Pummel.getActionMap().put("pressed", PummelAction);
                    }
                    if(!c.getHumiliate())
                    {
                        JButton Humiliate = new JButton(HumiliateAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }
                        };
                        Humiliate.setToolTipText("<html><center>Inflicts EXPO along with<br>SHAM, FEAR, DISG, and " + PAINname + "<br>Can cause tier-1 Innocence or Dignity Break</center></html>");
                        p.add(Humiliate);
                        Humiliate.getInputMap(2).put(KeyStroke.getKeyStroke("4"), "pressed");
                        if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 10)
                            Humiliate.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Humiliate.getActionMap().put("pressed", HumiliateAction);
                    }
                    if(c.getCurrentHATE() >= 10000L)
                    {
                        defilers++;
                        JButton Inseminate = new JButton(InseminateAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }
                        };
                        if(finalInseminated > 0)
                        {
                            Inseminate.setBackground(PURPLISH);
                            if(finalInseminated == 1)
                            {
                                Inseminate.setText("Inseminate+");
                                plusPossible = true;
                            } else
                            {
                                Inseminate.setText("Orgy");
                                orgyPossible = true;
                            }
                        } else
                        {
                            Inseminate.setBackground(YELLOWISH);
                        }
                        if(c.temptReq < 100_000L && finalInseminated != 2)
                            Inseminate.setBackground(REDDISH);
                        Inseminate.setToolTipText("<html><center>Inflicts HATE and PLEA along with<br>FEAR, DISG, " + PAINname + ", and SHAM<br>Causes tier-2 Morality Break</center></html>");
                        if(finalInseminated == 1)
                            Inseminate.setToolTipText("<html><center>Inflicts HATE, PLEA and " + INJUname + " along with<br>FEAR, DISG, " + PAINname + ", and SHAM<br>Causes tier-2 Morality Break</center></html>");
                        else
                        if(finalInseminated == 2)
                            Inseminate.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                        p.add(Inseminate);
                        Inseminate.getInputMap(2).put(KeyStroke.getKeyStroke("5"), "pressed");
                        if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 11)
                            Inseminate.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Inseminate.getActionMap().put("pressed", InseminateAction);
                    }
                    if(c.getCurrentPLEA() >= 10000L)
                    {
                        defilers++;
                        JButton ForceOrgasm = new JButton(ForceOrgasmAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }
                        };
                        if(finalOrgasming > 0)
                        {
                            ForceOrgasm.setBackground(PURPLISH);
                            if(finalOrgasming == 1)
                            {
                                ForceOrgasm.setText("Force Orgasm+");
                                plusPossible = true;
                            } else
                            {
                                ForceOrgasm.setText("Orgy");
                                orgyPossible = true;
                            }
                        } else
                        {
                            ForceOrgasm.setBackground(YELLOWISH);
                        }
                        if(c.dissociationReq < 10 && finalOrgasming != 2)
                            ForceOrgasm.setBackground(REDDISH);
                        ForceOrgasm.setToolTipText("<html><center>Inflicts PLEA and " + INJUname + " along with<br>DISG, " + PAINname + ", SHAM, and FEAR<br>Causes tier-2 Innocence Break</center></html>");
                        if(finalOrgasming == 1)
                            ForceOrgasm.setToolTipText("<html><center>Inflicts PLEA," + INJUname + ", and EXPO along with<br>DISG, " + PAINname + ", SHAM, and FEAR<br>Causes tier-2 Innocence Break</center></html>");
                        else
                        if(finalOrgasming == 2)
                            ForceOrgasm.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                        p.add(ForceOrgasm);
                        ForceOrgasm.getInputMap(2).put(KeyStroke.getKeyStroke("6"), "pressed");
                        if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 12)
                            ForceOrgasm.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        ForceOrgasm.getActionMap().put("pressed", ForceOrgasmAction);
                    }
                    if(c.getCurrentINJU() >= 10000L)
                    {
                        defilers++;
                        JButton Sodomize = new JButton(SodomizeAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }
                        };
                        if(finalSodomized > 0)
                        {
                            Sodomize.setBackground(PURPLISH);
                            if(finalSodomized == 1)
                            {
                                if(w.tickle())
                                    Sodomize.setText("Force Laughter+");
                                else
                                if(c.getGender().equals("male"))
                                    Sodomize.setText("Torture+");
                                else
                                    Sodomize.setText("Sodomize+");
                                plusPossible = true;
                            } else
                            {
                                Sodomize.setText("Orgy");
                                orgyPossible = true;
                            }
                        } else
                        {
                            Sodomize.setBackground(YELLOWISH);
                        }
                        if(c.temptReq < 100_000L && finalSodomized != 2)
                            Sodomize.setBackground(REDDISH);
                        Sodomize.setToolTipText("<html><center>Inflicts " + INJUname + " and EXPO along with<br>" + PAINname + ", SHAM, FEAR, and DISG<br>Causes tier-2 Confidence Break</center></html>");
                        if(finalSodomized == 1)
                            Sodomize.setToolTipText("<html><center>Inflicts " + INJUname + ", EXPO, and HATE along with<br>" + PAINname + ", SHAM, FEAR, and DISG<br>Causes tier-2 Confidence Break</center></html>");
                        else
                        if(finalSodomized == 2)
                            Sodomize.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                        p.add(Sodomize);
                        Sodomize.getInputMap(2).put(KeyStroke.getKeyStroke("7"), "pressed");
                        if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 13)
                            Sodomize.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Sodomize.getActionMap().put("pressed", SodomizeAction);
                    }
                    if(c.getCurrentEXPO() >= 10000L)
                    {
                        defilers++;
                        JButton Broadcast = new JButton(BroadcastAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }
                        };
                        if(finalBroadcasted > 0)
                        {
                            Broadcast.setBackground(PURPLISH);
                            if(finalBroadcasted == 1)
                            {
                                Broadcast.setText("Broadcast+");
                                plusPossible = true;
                            } else
                            {
                                Broadcast.setText("Orgy");
                                orgyPossible = true;
                            }
                        } else
                        {
                            Broadcast.setBackground(YELLOWISH);
                        }
                        if(c.dissociationReq < 10 && finalBroadcasted != 2)
                            Broadcast.setBackground(REDDISH);
                        Broadcast.setToolTipText("<html><center>Inflicts EXPO and HATE along with<br>SHAM, FEAR, DISG, and " + PAINname + "<br>Causes tier-2 Dignity Break</center></html>");
                        if(finalBroadcasted == 1)
                            Broadcast.setToolTipText("<html><center>Inflicts EXPO, HATE, and PLEA along with<br>SHAM, FEAR, DISG, and " + PAINname + "<br>Causes tier-2 Dignity Break</center></html>");
                        else
                        if(finalBroadcasted == 2)
                            Broadcast.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                        p.add(Broadcast);
                        Broadcast.getInputMap(2).put(KeyStroke.getKeyStroke("8"), "pressed");
                        if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 14)
                            Broadcast.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Broadcast.getActionMap().put("pressed", BroadcastAction);
                    }
                    long currentTemptReq = c.temptReq;
                    if(w.finalBattle)
                        currentTemptReq *= 10L;
                    if(c.getCurrentPLEA() >= currentTemptReq && c.vVirg && c.aVirg && !c.cVirg && !c.modest && !c.ruthless && !c.usingSlaughter && !c.usingDetonate && (c.temptReq < 100_000L || !w.finalBattle))
                    {
                        JButton Tempt = new JButton(TemptAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }
                        };
                        Tempt.setToolTipText("<html><center>Inflicts extremely high PLEA and EXPO<br>but decreases other circumstances to zero and does not inflict trauma<br>Causes and intensifies Morality/Confidence Distortion</center></html>");
                        Tempt.setBackground(PURPLISH);
                        p.add(Tempt);
                        Tempt.getInputMap(2).put(KeyStroke.getKeyStroke("9"), "pressed");
                        if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 14)
                            Tempt.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Tempt.getActionMap().put("pressed", TemptAction);
                    }
                    w.append(t, "\n\n" + w.getSeparator() + "\n\nWhat should the Thralls do after surrounding " + c.getMainName() + "?");
                    if(defilers > 1)
                        w.append(t, String.format("  %1$s", defilers) + " defiler actions possible.");
                    else
                    if(defilers == 1)
                        w.append(t, "  1 defiler action possible.");
                    int difference = 0;
                    if(orgyPossible)
                    {
                        String firstName = "";
                        String secondName = "";
                        int duration = 0;
                        int opening = c.getFEAROpening(w) + c.getDISGOpening() + c.getPAINOpening() + c.getSHAMOpening(w) + 1;
                        for(int i = 0; i < 3; i++)
                            if(w.getCombatants()[i] != null && w.getCombatants()[i] != c)
                                if(firstName.length() == 0)
                                {
                                    firstName = w.getCombatants()[i].getMainName();
                                    duration = w.getCombatants()[i].getSurroundDuration();
                                } else
                                {
                                    secondName = w.getCombatants()[i].getMainName();
                                }

                        w.append(t, String.format("  Orgy with %1$s", firstName) + " and " + secondName);
                        if(duration > opening)
                        {
                            difference = duration - opening;
                            w.append(t, " will cause them");
                        } else
                        if(opening > duration)
                        {
                            difference = opening - duration;
                            w.append(t, String.format(" will allow %1$s", c.getMainName()));
                        }
                        if(difference > 1)
                            w.append(t, String.format(" to escape %1$s", difference) + " turns early.");
                        else
                        if(difference == 1)
                            w.append(t, " to escape 1 turn early.");
                        else
                            w.append(t, " does not allow any of them to escape early.");
                    } else
                    if(plusPossible)
                    {
                        int opening = c.getFEAROpening(w) + c.getDISGOpening() + c.getPAINOpening() + c.getSHAMOpening(w) + 1;
                        for(int i = 0; i < 3; i++)
                            if(w.getCombatants()[i] != null && w.getCombatants()[i] != c)
                            {
                                String defilementType = "";
                                if(w.getCombatants()[i].isInseminated() && c.getHATELevel() >= 3)
                                    defilementType = "Inseminate";
                                else
                                if(w.getCombatants()[i].isOrgasming() && c.getPLEALevel() >= 3)
                                    defilementType = "Force Orgasm";
                                else
                                if(w.getCombatants()[i].isSodomized() && c.getINJULevel() >= 3)
                                {
                                    if(w.tickle())
                                        defilementType = "Force Laughter";
                                    else
                                    if(c.getGender().equals("male"))
                                        defilementType = "Torture";
                                    else
                                        defilementType = "Sodomize";
                                } else
                                if(w.getCombatants()[i].isBroadcasted() && c.getEXPOLevel() >= 3)
                                    defilementType = "Broadcast";
                                if(defilementType.length() > 0)
                                {
                                    w.append(t, String.format("  %1$s", defilementType) + " with " + w.getCombatants()[i].getMainName());
                                    if(opening > w.getCombatants()[i].getSurroundDuration())
                                    {
                                        w.append(t, String.format(" will allow %1$s", c.getMainName()));
                                        difference = opening - w.getCombatants()[i].getSurroundDuration();
                                    } else
                                    if(w.getCombatants()[i].getSurroundDuration() > opening)
                                    {
                                        w.append(t, String.format(" will allow %1$s", w.getCombatants()[i].getMainName()));
                                        difference = w.getCombatants()[i].getSurroundDuration() - opening;
                                    }
                                    if(difference > 1)
                                        w.append(t, String.format(" to escape %1$s", difference) + " turns early.");
                                    else
                                    if(difference == 1)
                                        w.append(t, " to escape 1 turn early.");
                                    else
                                        w.append(t, " does not allow either of them to escape early.");
                                    difference = 0;
                                }
                            }

                    }
                    JButton Back = new JButton("Cancel");
                    Back.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            w.setSurroundTarget(null);
                            Project.PickAction(t, p, f, w, c, initiative);
                        }
                    });
                    p.add(Back);
                    p.validate();
                    p.repaint();
                } else
                {
                    Project.advanceAction(p, w, c.getNumber() * 14 + 1);
                    Project.EnemyTurn(t, p, f, w, initiative, 0);
                }
            }
        }

        final Action SurroundAction = new SurroundButton("Surround", "Hotkey:");
        class CaptureButton extends AbstractAction {
			public CaptureButton(String text, String desc) {
				super(text);
				putValue(SHORT_DESCRIPTION, desc);
			}
            public void actionPerformed(ActionEvent e)
            {
                Boolean directlyAdvance = true;
                if(w.upgradedCommander())
                {
                    if(w.usedForsaken != null)
                        w.usedForsaken.defilerStage = 0;
                    w.setCaptureTarget(c);
                } else
                {
                    w.setSurroundTarget(c);
                    if(w.getTechs()[31].isOwned())
                        directlyAdvance = false;
                }
                if(directlyAdvance)
                {
                    Project.advanceAction(p, w, c.getNumber() * 14 + 2);
                    Project.EnemyTurn(t, p, f, w, initiative, 0);
                } else
                {
                    p.removeAll();
                    String PAINname = "PAIN";
                    String INJUname = "INJU";
                    if(w.tickle())
                    {
                        PAINname = "TICK";
                        INJUname = "ANTI";
                    }
                    int defilers = 0;
                    Boolean plusPossible = false;
                    Boolean orgyPossible = false;
                    if(!c.getGrind())
                    {
                        JButton Grind = new JButton(GrindAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }
                        };
                        Grind.setToolTipText("<html><center>Inflicts HATE along with<br>FEAR, DISG, " + PAINname + ", and SHAM<br>Can cause tier-1 Morality or Dignity Break</center></html>");
                        p.add(Grind);
                        Grind.getInputMap(2).put(KeyStroke.getKeyStroke("1"), "pressed");
                        if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 7)
                            Grind.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Grind.getActionMap().put("pressed", GrindAction);
                    }
                    if(!c.getCaress())
                    {
                        JButton Caress = new JButton(CaressAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }
                        };
                        Caress.setToolTipText("<html><center>Inflicts PLEA along with<br>DISG, " + PAINname + ", SHAM, and FEAR<br>Can cause tier-1 Innocence or Confidence Break</center></html>");
                        p.add(Caress);
                        Caress.getInputMap(2).put(KeyStroke.getKeyStroke("2"), "pressed");
                        if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 8)
                            Caress.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Caress.getActionMap().put("pressed", CaressAction);
                    }
                    if(!c.getPummel())
                    {
                        JButton Pummel = new JButton(PummelAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }
                        };
                        Pummel.setToolTipText("<html><center>Inflicts " + INJUname + " along with<br>" + PAINname + ", SHAM, FEAR, and DISG<br>Can cause tier-1 Morality or Confidence Break</center></html>");
                        p.add(Pummel);
                        Pummel.getInputMap(2).put(KeyStroke.getKeyStroke("3"), "pressed");
                        if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 9)
                            Pummel.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Pummel.getActionMap().put("pressed", PummelAction);
                    }
                    if(!c.getHumiliate())
                    {
                        JButton Humiliate = new JButton(HumiliateAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }
                        };
                        Humiliate.setToolTipText("<html><center>Inflicts EXPO along with<br>SHAM, FEAR, DISG, and " + PAINname + "<br>Can cause tier-1 Innocence or Dignity Break</center></html>");
                        p.add(Humiliate);
                        Humiliate.getInputMap(2).put(KeyStroke.getKeyStroke("4"), "pressed");
                        if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 10)
                            Humiliate.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Humiliate.getActionMap().put("pressed", HumiliateAction);
                    }
                    if(c.getCurrentHATE() >= 10000L)
                    {
                        defilers++;
                        JButton Inseminate = new JButton(InseminateAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }
                        };
                        if(finalInseminated > 0)
                        {
                            Inseminate.setBackground(PURPLISH);
                            if(finalInseminated == 1)
                            {
                                Inseminate.setText("Inseminate+");
                                plusPossible = true;
                            } else
                            {
                                Inseminate.setText("Orgy");
                                orgyPossible = true;
                            }
                        } else
                        {
                            Inseminate.setBackground(YELLOWISH);
                        }
                        if(c.temptReq < 100_000L && finalInseminated != 2)
                            Inseminate.setBackground(REDDISH);
                        Inseminate.setToolTipText("<html><center>Inflicts HATE and PLEA along with<br>FEAR, DISG, " + PAINname + ", and SHAM<br>Causes tier-2 Morality Break</center></html>");
                        if(finalInseminated == 1)
                            Inseminate.setToolTipText("<html><center>Inflicts HATE, PLEA and " + INJUname + " along with<br>FEAR, DISG, " + PAINname + ", and SHAM<br>Causes tier-2 Morality Break</center></html>");
                        else
                        if(finalInseminated == 2)
                            Inseminate.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                        p.add(Inseminate);
                        Inseminate.getInputMap(2).put(KeyStroke.getKeyStroke("5"), "pressed");
                        if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 11)
                            Inseminate.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Inseminate.getActionMap().put("pressed", InseminateAction);
                    }
                    if(c.getCurrentPLEA() >= 10000L)
                    {
                        defilers++;
                        JButton ForceOrgasm = new JButton(ForceOrgasmAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }
                        };
                        if(finalOrgasming > 0)
                        {
                            ForceOrgasm.setBackground(PURPLISH);
                            if(finalOrgasming == 1)
                            {
                                ForceOrgasm.setText("Force Orgasm+");
                                plusPossible = true;
                            } else
                            {
                                ForceOrgasm.setText("Orgy");
                                orgyPossible = true;
                            }
                        } else
                        {
                            ForceOrgasm.setBackground(YELLOWISH);
                        }
                        if(c.dissociationReq < 10 && finalOrgasming != 2)
                            ForceOrgasm.setBackground(REDDISH);
                        ForceOrgasm.setToolTipText("<html><center>Inflicts PLEA and " + INJUname + " along with<br>DISG, " + PAINname + ", SHAM, and FEAR<br>Causes tier-2 Innocence Break</center></html>");
                        if(finalOrgasming == 1)
                            ForceOrgasm.setToolTipText("<html><center>Inflicts PLEA," + INJUname + ", and EXPO along with<br>DISG, " + PAINname + ", SHAM, and FEAR<br>Causes tier-2 Innocence Break</center></html>");
                        else
                        if(finalOrgasming == 2)
                            ForceOrgasm.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                        p.add(ForceOrgasm);
                        ForceOrgasm.getInputMap(2).put(KeyStroke.getKeyStroke("6"), "pressed");
                        if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 12)
                            ForceOrgasm.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        ForceOrgasm.getActionMap().put("pressed", ForceOrgasmAction);
                    }
                    if(c.getCurrentINJU() >= 10000L)
                    {
                        defilers++;
                        JButton Sodomize = new JButton(SodomizeAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }
                        };
                        if(finalSodomized > 0)
                        {
                            Sodomize.setBackground(PURPLISH);
                            if(finalSodomized == 1)
                            {
                                if(w.tickle())
                                    Sodomize.setText("Force Laughter+");
                                else
                                if(c.getGender().equals("male"))
                                    Sodomize.setText("Torture+");
                                else
                                    Sodomize.setText("Sodomize+");
                                plusPossible = true;
                            } else
                            {
                                Sodomize.setText("Orgy");
                                orgyPossible = true;
                            }
                        } else
                        {
                            Sodomize.setBackground(YELLOWISH);
                        }
                        if(c.temptReq < 100_000L && finalSodomized != 2)
                            Sodomize.setBackground(REDDISH);
                        Sodomize.setToolTipText("<html><center>Inflicts " + INJUname + " and EXPO along with<br>" + PAINname + ", SHAM, FEAR, and DISG<br>Causes tier-2 Confidence Break</center></html>");
                        if(finalSodomized == 1)
                            Sodomize.setToolTipText("<html><center>Inflicts " + INJUname + ", EXPO, and HATE along with<br>" + PAINname + ", SHAM, FEAR, and DISG<br>Causes tier-2 Confidence Break</center></html>");
                        else
                        if(finalSodomized == 2)
                            Sodomize.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                        p.add(Sodomize);
                        Sodomize.getInputMap(2).put(KeyStroke.getKeyStroke("7"), "pressed");
                        if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 13)
                            Sodomize.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Sodomize.getActionMap().put("pressed", SodomizeAction);
                    }
                    if(c.getCurrentEXPO() >= 10000L)
                    {
                        defilers++;
                        JButton Broadcast = new JButton(BroadcastAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }
                        };
                        if(finalBroadcasted > 0)
                        {
                            Broadcast.setBackground(PURPLISH);
                            if(finalBroadcasted == 1)
                            {
                                Broadcast.setText("Broadcast+");
                                plusPossible = true;
                            } else
                            {
                                Broadcast.setText("Orgy");
                                orgyPossible = true;
                            }
                        } else
                        {
                            Broadcast.setBackground(YELLOWISH);
                        }
                        if(c.dissociationReq < 10 && finalBroadcasted != 2)
                            Broadcast.setBackground(REDDISH);
                        Broadcast.setToolTipText("<html><center>Inflicts EXPO and HATE along with<br>SHAM, FEAR, DISG, and " + PAINname + "<br>Causes tier-2 Dignity Break</center></html>");
                        if(finalBroadcasted == 1)
                            Broadcast.setToolTipText("<html><center>Inflicts EXPO, HATE, and PLEA along with<br>SHAM, FEAR, DISG, and " + PAINname + "<br>Causes tier-2 Dignity Break</center></html>");
                        else
                        if(finalBroadcasted == 2)
                            Broadcast.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                        p.add(Broadcast);
                        Broadcast.getInputMap(2).put(KeyStroke.getKeyStroke("8"), "pressed");
                        if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 14)
                            Broadcast.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Broadcast.getActionMap().put("pressed", BroadcastAction);
                    }
                    w.append(t, "\n\n" + w.getSeparator() + "\n\nWhat should the Thralls do after surrounding " + c.getMainName() + "?");
                    if(defilers > 1)
                        w.append(t, String.format("  %1$s", defilers) + " defiler actions possible.");
                    else
                    if(defilers == 1)
                        w.append(t, "  1 defiler action possible.");
                    int difference = 0;
                    if(orgyPossible)
                    {
                        String firstName = "";
                        String secondName = "";
                        int duration = 0;
                        int opening = w.getCaptureDuration() + 1;
                        for(int i = 0; i < 3; i++)
                            if(w.getCombatants()[i] != null && w.getCombatants()[i] != c)
                                if(firstName.length() == 0)
                                {
                                    firstName = w.getCombatants()[i].getMainName();
                                    duration = w.getCombatants()[i].getSurroundDuration();
                                } else
                                {
                                    secondName = w.getCombatants()[i].getMainName();
                                }

                        w.append(t, String.format("  Orgy with %1$s", firstName) + " and " + secondName);
                        if(duration > opening)
                        {
                            difference = duration - opening;
                            w.append(t, " will cause them");
                        } else
                        if(opening > duration)
                        {
                            difference = opening - duration;
                            w.append(t, String.format(" will allow %1$s", c.getMainName()));
                        }
                        if(difference > 1)
                            w.append(t, String.format(" to escape %1$s", difference) + " turns early.");
                        else
                        if(difference == 1)
                            w.append(t, " to escape 1 turn early.");
                        else
                            w.append(t, " does not allow any of them to escape early.");
                    } else
                    if(plusPossible)
                    {
                        int opening = w.getCaptureDuration() + 1;
                        for(int i = 0; i < 3; i++)
                            if(w.getCombatants()[i] != null && w.getCombatants()[i] != c)
                            {
                                String defilementType = "";
                                if(w.getCombatants()[i].isInseminated() && c.getHATELevel() >= 3)
                                    defilementType = "Inseminate";
                                else
                                if(w.getCombatants()[i].isOrgasming() && c.getPLEALevel() >= 3)
                                    defilementType = "Force Orgasm";
                                else
                                if(w.getCombatants()[i].isSodomized() && c.getINJULevel() >= 3)
                                {
                                    if(w.tickle())
                                        defilementType = "Force Laughter";
                                    else
                                    if(c.getGender().equals("male"))
                                        defilementType = "Torture";
                                    else
                                        defilementType = "Sodomize";
                                } else
                                if(w.getCombatants()[i].isBroadcasted() && c.getEXPOLevel() >= 3)
                                    defilementType = "Broadcast";
                                if(defilementType.length() > 0)
                                {
                                    w.append(t, String.format("  %1$s", defilementType) + " with " + w.getCombatants()[i].getMainName());
                                    if(opening > w.getCombatants()[i].getSurroundDuration())
                                    {
                                        w.append(t, String.format(" will allow %1$s", c.getMainName()));
                                        difference = opening - w.getCombatants()[i].getSurroundDuration();
                                    } else
                                    if(w.getCombatants()[i].getSurroundDuration() > opening)
                                    {
                                        w.append(t, String.format(" will allow %1$s", w.getCombatants()[i].getMainName()));
                                        difference = w.getCombatants()[i].getSurroundDuration() - opening;
                                    }
                                    if(difference > 1)
                                        w.append(t, String.format(" to escape %1$s", difference) + " turns early.");
                                    else
                                    if(difference == 1)
                                        w.append(t, " to escape 1 turn early.");
                                    else
                                        w.append(t, " does not allow either of them to escape early.");
                                    difference = 0;
                                }
                            }

                    }
                    JButton Back = new JButton("Cancel");
                    Back.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            w.setSurroundTarget(null);
                            Project.PickAction(t, p, f, w, c, initiative);
                        }
                    });
                    p.add(Back);
                    p.validate();
                    p.repaint();
                }
            }
        }

        final Action CaptureAction = new CaptureButton("Capture", "Use Capture");
        class ExamineButton extends AbstractAction
        {
		    public ExamineButton(String text, String desc) {
				super(text);
				putValue(SHORT_DESCRIPTION, desc);
			}
            public void actionPerformed(ActionEvent e)
            {
                w.Examine(t, p, f, c);
            }
        }

        final Action ExamineAction = new ExamineButton("Examine", "Hotkey:");
        p.removeAll();
        w.append(t, "\n\n");
        c.printStatus(t, w);
        if(!c.isCaptured() && !c.isDefiled())
        {
            w.append(t, "\n\nChoose your action.");
            if(w.usedForsaken != null && c.defenseLevel < 9000)
            {
                if(w.usedForsaken.injured == 0 && w.commanderFree())
                    w.append(t, String.format("  %1$s", w.usedForsaken.mainName) + " can ");
                else
                    w.append(t, String.format("  Once %1$s", w.usedForsaken.mainName) + " is ready, " + w.usedForsaken.heShe() + " will be able to ");
                w.append(t, String.format("capture %1$s", c.mainName) + " for " + w.usedForsaken.compatibility(c) + " rounds.");
            }
        }
        JButton Examine = new JButton(ExamineAction) {

            public Point getToolTipLocation(MouseEvent e)
            {
                return new Point(0, -30);
            }

        };
        Examine.setForeground(Color.GRAY);
        p.add(Examine);
        Examine.getInputMap(2).put(KeyStroke.getKeyStroke(88, 0), "pressed");
        Examine.getActionMap().put("pressed", ExamineAction);
        class PassButton extends AbstractAction
        {

            public PassButton(String text, String desc) {
                super(text);
                this.putValue(SHORT_DESCRIPTION, desc);
            }
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.advanceAction(p, w, 0);
                if(w.getTechs()[30].isOwned() && !w.progressExtermination(0))
                {
                    p.removeAll();
                    w.increaseBarrier(t);
                    class ContinueButton extends AbstractAction
                    {

                        public ContinueButton(String text, String desc) {
                            super(text);
                            this.putValue(SHORT_DESCRIPTION, desc);
                        }
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            Project.EnemyTurn(t, p, f, w, initiative, 0);
                        }
                    }

                    final Action ContinueAction = new ContinueButton("Continue", "Hotkey:");
                    JButton Continue = new JButton(ContinueAction) {

                        public Point getToolTipLocation(MouseEvent e)
                        {
                            return new Point(0, -30);
                        }
                    };
                    Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                    Continue.getActionMap().put("pressed", ContinueAction);
                    p.add(Continue);
                    p.validate();
                    p.repaint();
                } else
                {
                    Project.EnemyTurn(t, p, f, w, initiative, 0);
                }
            }
        }

        final Action PassAction = new PassButton("Do Nothing", "Hotkey:");
        JButton Pass = new JButton(PassAction) {

            public Point getToolTipLocation(MouseEvent e)
            {
                return new Point(0, -30);
            }

        };
        Pass.setForeground(Color.GRAY);
        Pass.getInputMap(2).put(KeyStroke.getKeyStroke(70, 0), "pressed");
        if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == 0)
            Pass.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
        Pass.getActionMap().put("pressed", PassAction);
        if(w.getTechs()[30].isOwned() && !w.progressExtermination(0))
        {
            Pass.setText("Barrier");
            Pass.setToolTipText("+5% damage for rest of battle");
        }
        if(c.isDefiled())
        {
            w.append(t, "\n\nThe Thralls have been driven into a frenzy ");
            if(c.isInseminated())
                w.append(t, String.format("inseminating %1$s", c.getMainName()));
            else
            if(c.isOrgasming())
                w.append(t, String.format("forcing %1$s", c.getMainName()) + " to orgasm");
            else
            if(c.isSodomized())
            {
                if(w.tickle())
                    w.append(t, String.format("forcing %1$s", c.getMainName()) + " to laugh");
                else
                if(c.getGender().equals("male"))
                    w.append(t, String.format("torturing %1$s", c.getMainName()));
                else
                    w.append(t, String.format("sodomizing %1$s", c.getMainName()));
            } else
            if(c.isBroadcasted())
                w.append(t, String.format("broadcasting %1$s", c.getMainName()) + "'s humiliation");
            else
            if(c.tempted)
                w.append(t, String.format("giving %1$s", c.mainName) + " all the pleasure " + c.heShe() + " wants");
            w.append(t, ".  Any additional orders would simply confuse them right now.");
        } else
        if(c.isSurrounded())
        {
            int defilers = 0;
            String PAINname = "PAIN";
            String INJUname = "INJU";
            if(w.tickle())
            {
                PAINname = "TICK";
                INJUname = "ANTI";
            }
            Boolean plusPossible = false;
            Boolean orgyPossible = false;
            if(!c.getGrind())
            {
                JButton Grind = new JButton(GrindAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -60);
                    }

                };
                Grind.setToolTipText("<html><center>Inflicts HATE along with<br>FEAR, DISG, " + PAINname + ", and SHAM<br>Can cause tier-1 Morality or Dignity Break</center></html>");
                p.add(Grind);
                Grind.getInputMap(2).put(KeyStroke.getKeyStroke("1"), "pressed");
                if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 7)
                    Grind.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Grind.getActionMap().put("pressed", GrindAction);
            }
            if(!c.getCaress())
            {
                JButton Caress = new JButton(CaressAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -60);
                    }

                };
                Caress.setToolTipText("<html><center>Inflicts PLEA along with<br>DISG, " + PAINname + ", SHAM, and FEAR<br>Can cause tier-1 Innocence or Confidence Break</center></html>");
                p.add(Caress);
                Caress.getInputMap(2).put(KeyStroke.getKeyStroke("2"), "pressed");
                if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 8)
                    Caress.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Caress.getActionMap().put("pressed", CaressAction);
            }
            if(!c.getPummel())
            {
                JButton Pummel = new JButton(PummelAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -60);
                    }

                };
                Pummel.setToolTipText("<html><center>Inflicts " + INJUname + " along with<br>" + PAINname + ", SHAM, FEAR, and DISG<br>Can cause tier-1 Morality or Confidence Break</center></html>");
                p.add(Pummel);
                Pummel.getInputMap(2).put(KeyStroke.getKeyStroke("3"), "pressed");
                if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 9)
                    Pummel.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Pummel.getActionMap().put("pressed", PummelAction);
            }
            if(!c.getHumiliate())
            {
                JButton Humiliate = new JButton(HumiliateAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -60);
                    }

                };
                Humiliate.setToolTipText("<html><center>Inflicts EXPO along with<br>SHAM, FEAR, DISG, and " + PAINname + "<br>Can cause tier-1 Innocence or Dignity Break</center></html>");
                p.add(Humiliate);
                Humiliate.getInputMap(2).put(KeyStroke.getKeyStroke("4"), "pressed");
                if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 10)
                    Humiliate.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Humiliate.getActionMap().put("pressed", HumiliateAction);
            }
            if(c.getCurrentHATE() >= 10000L)
            {
                defilers++;
                JButton Inseminate = new JButton(InseminateAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -60);
                    }

                };
                if(inseminated > 0)
                {
                    Inseminate.setBackground(PURPLISH);
                    if(inseminated == 1)
                    {
                        Inseminate.setText("Inseminate+");
                        plusPossible = true;
                    } else
                    {
                        Inseminate.setText("Orgy");
                        orgyPossible = true;
                    }
                } else
                {
                    Inseminate.setBackground(YELLOWISH);
                }
                if(c.temptReq < 100_000L && inseminated != 2)
                    Inseminate.setBackground(REDDISH);
                Inseminate.setToolTipText("<html><center>Inflicts HATE and PLEA along with<br>FEAR, DISG, " + PAINname + ", and SHAM<br>Causes tier-2 Morality Break</center></html>");
                if(inseminated == 1)
                    Inseminate.setToolTipText("<html><center>Inflicts HATE, PLEA and " + INJUname + " along with<br>FEAR, DISG, " + PAINname + ", and SHAM<br>Causes tier-2 Morality Break</center></html>");
                else
                if(inseminated == 2)
                    Inseminate.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                p.add(Inseminate);
                Inseminate.getInputMap(2).put(KeyStroke.getKeyStroke("5"), "pressed");
                if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 11)
                    Inseminate.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Inseminate.getActionMap().put("pressed", InseminateAction);
            }
            if(c.getCurrentPLEA() >= 10000L)
            {
                defilers++;
                JButton ForceOrgasm = new JButton(ForceOrgasmAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -60);
                    }

                };
                if(orgasming > 0)
                {
                    ForceOrgasm.setBackground(PURPLISH);
                    if(orgasming == 1)
                    {
                        ForceOrgasm.setText("Force Orgasm+");
                        plusPossible = true;
                    } else
                    {
                        ForceOrgasm.setText("Orgy");
                        orgyPossible = true;
                    }
                } else
                {
                    ForceOrgasm.setBackground(YELLOWISH);
                }
                if(c.dissociationReq < 10 && orgasming != 2)
                    ForceOrgasm.setBackground(REDDISH);
                ForceOrgasm.setToolTipText("<html><center>Inflicts PLEA and " + INJUname + " along with<br>DISG, " + PAINname + ", SHAM, and FEAR<br>Causes tier-2 Innocence Break</center></html>");
                if(orgasming == 1)
                    ForceOrgasm.setToolTipText("<html><center>Inflicts PLEA," + INJUname + ", and EXPO along with<br>DISG, " + PAINname + ", SHAM, and FEAR<br>Causes tier-2 Innocence Break</center></html>");
                else
                if(orgasming == 2)
                    ForceOrgasm.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                p.add(ForceOrgasm);
                ForceOrgasm.getInputMap(2).put(KeyStroke.getKeyStroke("6"), "pressed");
                if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 12)
                    ForceOrgasm.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                ForceOrgasm.getActionMap().put("pressed", ForceOrgasmAction);
            }
            if(c.getCurrentINJU() >= 10000L)
            {
                defilers++;
                JButton Sodomize = new JButton(SodomizeAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -60);
                    }

                };
                if(sodomized > 0)
                {
                    Sodomize.setBackground(PURPLISH);
                    if(sodomized == 1)
                    {
                        if(w.tickle())
                            Sodomize.setText("Force Laughter+");
                        else
                        if(c.getGender().equals("male"))
                            Sodomize.setText("Torture+");
                        else
                            Sodomize.setText("Sodomize+");
                        plusPossible = true;
                    } else
                    {
                        Sodomize.setText("Orgy");
                        orgyPossible = true;
                    }
                } else
                {
                    Sodomize.setBackground(YELLOWISH);
                }
                if(c.temptReq < 100_000L && sodomized != 2)
                    Sodomize.setBackground(REDDISH);
                Sodomize.setToolTipText("<html><center>Inflicts " + INJUname + " and EXPO along with<br>" + PAINname + ", SHAM, FEAR, and DISG<br>Causes tier-2 Confidence Break</center></html>");
                if(sodomized == 1)
                    Sodomize.setToolTipText("<html><center>Inflicts " + INJUname + ", EXPO, and HATE along with<br>" + PAINname + ", SHAM, FEAR, and DISG<br>Causes tier-2 Confidence Break</center></html>");
                else
                if(sodomized == 2)
                    Sodomize.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                p.add(Sodomize);
                Sodomize.getInputMap(2).put(KeyStroke.getKeyStroke("7"), "pressed");
                if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 13)
                    Sodomize.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Sodomize.getActionMap().put("pressed", SodomizeAction);
            }
            if(c.getCurrentEXPO() >= 10000L)
            {
                defilers++;
                JButton Broadcast = new JButton(BroadcastAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -60);
                    }

                };
                if(broadcasted > 0)
                {
                    Broadcast.setBackground(PURPLISH);
                    if(broadcasted == 1)
                    {
                        Broadcast.setText("Broadcast+");
                        plusPossible = true;
                    } else
                    {
                        Broadcast.setText("Orgy");
                        orgyPossible = true;
                    }
                } else
                {
                    Broadcast.setBackground(YELLOWISH);
                }
                if(c.dissociationReq < 10 && broadcasted != 2)
                    Broadcast.setBackground(REDDISH);
                Broadcast.setToolTipText("<html><center>Inflicts EXPO and HATE along with<br>SHAM, FEAR, DISG, and " + PAINname + "<br>Causes tier-2 Dignity Break</center></html>");
                if(broadcasted == 1)
                    Broadcast.setToolTipText("<html><center>Inflicts EXPO, HATE, and PLEA along with<br>SHAM, FEAR, DISG, and " + PAINname + "<br>Causes tier-2 Dignity Break</center></html>");
                else
                if(broadcasted == 2)
                    Broadcast.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                p.add(Broadcast);
                Broadcast.getInputMap(2).put(KeyStroke.getKeyStroke("8"), "pressed");
                if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 14)
                    Broadcast.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Broadcast.getActionMap().put("pressed", BroadcastAction);
            }
            long currentTemptReq = c.temptReq;
            if(w.finalBattle)
                currentTemptReq *= 10L;
            if(c.getCurrentPLEA() >= currentTemptReq && c.vVirg && c.aVirg && !c.cVirg && !c.modest && !c.ruthless && !c.usingSlaughter && !c.usingDetonate && (c.temptReq < 100_000L || !w.finalBattle))
            {
                JButton Tempt = new JButton(TemptAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -60);
                    }

                };
                Tempt.setToolTipText("<html><center>Inflicts extremely high PLEA and EXPO<br>but decreases other circumstances to zero and does not inflict trauma<br>Causes and intensifies Morality/Confidence Distortion</center></html>");
                Tempt.setBackground(PURPLISH);
                p.add(Tempt);
                Tempt.getInputMap(2).put(KeyStroke.getKeyStroke("9"), "pressed");
                if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 14)
                    Tempt.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Tempt.getActionMap().put("pressed", TemptAction);
            }
            if(defilers > 0)
            {
                if(defilers > 1)
                    w.append(t, String.format("  %1$s", defilers) + " defiler actions possible.");
                else
                if(defilers == 1)
                    w.append(t, "  1 defiler action possible.");
                int difference = 0;
                if(orgyPossible)
                {
                    String firstName = "";
                    String secondName = "";
                    int duration = 0;
                    int opening = c.getSurroundDuration();
                    for(int i = 0; i < 3; i++)
                        if(w.getCombatants()[i] != null && w.getCombatants()[i] != c)
                            if(firstName.length() == 0)
                            {
                                firstName = w.getCombatants()[i].getMainName();
                                duration = w.getCombatants()[i].getSurroundDuration();
                            } else
                            {
                                secondName = w.getCombatants()[i].getMainName();
                            }

                    w.append(t, String.format("  Orgy with %1$s", firstName) + " and " + secondName);
                    if(duration > opening)
                    {
                        difference = duration - opening;
                        w.append(t, " will cause them");
                    } else
                    if(opening > duration)
                    {
                        difference = opening - duration;
                        w.append(t, String.format(" will allow %1$s", c.getMainName()));
                    }
                    if(difference > 1)
                        w.append(t, String.format(" to escape %1$s", difference) + " turns early.");
                    else
                    if(difference == 1)
                        w.append(t, " to escape 1 turn early.");
                    else
                        w.append(t, " does not allow any of them to escape early.");
                } else
                if(plusPossible)
                {
                    int opening = c.getSurroundDuration();
                    for(int i = 0; i < 3; i++)
                        if(w.getCombatants()[i] != null && w.getCombatants()[i] != c)
                        {
                            String defilementType = "";
                            if(w.getCombatants()[i].isInseminated() && c.getHATELevel() >= 3)
                                defilementType = "Inseminate";
                            else
                            if(w.getCombatants()[i].isOrgasming() && c.getPLEALevel() >= 3)
                                defilementType = "Force Orgasm";
                            else
                            if(w.getCombatants()[i].isSodomized() && c.getINJULevel() >= 3)
                            {
                                if(w.tickle())
                                    defilementType = "Force Laughter";
                                else
                                if(c.getGender().equals("male"))
                                    defilementType = "Torture";
                                else
                                    defilementType = "Sodomize";
                            } else
                            if(w.getCombatants()[i].isBroadcasted() && c.getEXPOLevel() >= 3)
                                defilementType = "Broadcast";
                            if(defilementType.length() > 0)
                            {
                                w.append(t, String.format("  %1$s", defilementType) + " with " + w.getCombatants()[i].getMainName());
                                if(opening > w.getCombatants()[i].getSurroundDuration())
                                {
                                    w.append(t, String.format(" will allow %1$s", c.getMainName()));
                                    difference = opening - w.getCombatants()[i].getSurroundDuration();
                                } else
                                if(w.getCombatants()[i].getSurroundDuration() > opening)
                                {
                                    w.append(t, String.format(" will allow %1$s", w.getCombatants()[i].getMainName()));
                                    difference = w.getCombatants()[i].getSurroundDuration() - opening;
                                }
                                if(difference > 1)
                                    w.append(t, String.format(" to escape %1$s", difference) + " turns early.");
                                else
                                if(difference == 1)
                                    w.append(t, " to escape 1 turn early.");
                                else
                                    w.append(t, " does not allow either of them to escape early.");
                                difference = 0;
                            }
                        }

                }
            }
        } else
        if(c.isCaptured())
        {
            if(w.usedForsaken == null)
            {
                w.append(t, "\n\n" + c.getMainName() + " is captured by your Commander.  Any attempts to help by other Demons would simply get in the way.");
            } else
            {
                JButton ForsakenDefiler = new JButton(ForsakenDefilerAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -60);
                    }

                };
                if(w.usedForsaken.defilerType == 1)
                    ForsakenDefiler.setToolTipText("<html><center>Deals bonus HATE<br>and multiplies damage with lower Disgrace<br>Causes tier-2 Morality Break</center></html>");
                else
                if(w.usedForsaken.defilerType == 2)
                    ForsakenDefiler.setToolTipText("<html><center>Deals bonus PLEA<br>and multiplies damage with lower Disgrace<br>Causes tier-2 Innocence Break</center></html>");
                else
                if(w.usedForsaken.defilerType == 3)
                {
                    if(w.tickleOn)
                        ForsakenDefiler.setToolTipText("<html><center>Deals bonus ANTI<br>and multiplies damage with lower Disgrace<br>Causes tier-2 Confidence Break</center></html>");
                    else
                        ForsakenDefiler.setToolTipText("<html><center>Deals bonus INJU<br>and multiplies damage with lower Disgrace<br>Causes tier-2 Confidence Break</center></html>");
                } else
                if(w.usedForsaken.defilerType == 4)
                    ForsakenDefiler.setToolTipText("<html><center>Deals bonus EXPO<br>and multiplies damage with lower Disgrace<br>Causes tier-2 Dignity Break</center></html>");
                else
                if(w.usedForsaken.defilerType == 5)
                {
                    if(w.tickleOn)
                        ForsakenDefiler.setToolTipText("<html><center>Deals bonus PLEA and EXPO but sets HATE and ANTI to zero<br>and multiplies damage with lower Disgrace<br>Causes and intensifies Morality/Confidence Distortion</center></html>");
                    else
                        ForsakenDefiler.setToolTipText("<html><center>Deals bonus PLEA and EXPO but sets HATE and INJU to zero<br>and multiplies damage with lower Disgrace<br>Causes and intensifies Morality/Confidence Distortion</center></html>");
                } else
                if(w.usedForsaken.defilerType == 6)
                    ForsakenDefiler.setToolTipText("<html><center>Adds target to Orgy<br>with duration of current Defiler+ action<br>Can trigger Innocence/Dignity Distortion just like any other Orgy</center></html>");
                Boolean conditionsMet = false;
                if(w.usedForsaken.defilerType == 1 && c.currentHATE >= 10000L)
                    conditionsMet = true;
                else
                if(w.usedForsaken.defilerType == 2 && c.currentPLEA >= 10000L)
                    conditionsMet = true;
                else
                if(w.usedForsaken.defilerType == 3 && c.currentINJU >= 10000L)
                    conditionsMet = true;
                else
                if(w.usedForsaken.defilerType == 4 && c.currentEXPO >= 10000L)
                    conditionsMet = true;
                else
                if(w.usedForsaken.defilerType == 5)
                {
                    long currentTemptReq = c.temptReq;
                    if(w.finalBattle)
                        currentTemptReq *= 10L;
                    if(c.currentPLEA >= currentTemptReq && c.vVirg && c.aVirg && !c.cVirg && !c.modest && !c.ruthless && c.timesSlaughtered() == 0 && c.timesDetonated() == 0 && (c.temptReq < 100_000L || !w.finalBattle))
                        conditionsMet = true;
                } else
                if(w.usedForsaken.defilerType == 6 && (c.currentHATE >= 10000L || c.currentINJU >= 10000L))
                {
                    Chosen firstPartner = null;
                    Chosen secondPartner = null;
                    for(int i = 0; i < 3; i++)
                        if(i != c.number && w.getCast()[i] != null)
                            if(firstPartner == null)
                                firstPartner = w.getCast()[i];
                            else
                                secondPartner = w.getCast()[i];

                    if(firstPartner != null && secondPartner != null && (firstPartner.inseminated && secondPartner.inseminated || firstPartner.orgasming && secondPartner.orgasming || firstPartner.sodomized && secondPartner.sodomized || firstPartner.broadcasted && secondPartner.broadcasted))
                        conditionsMet = true;
                }
                if(conditionsMet)
                {
                    w.append(t, "\n\nChoose your action.");
                    p.add(ForsakenDefiler);
                    ForsakenDefiler.getInputMap(2).put(KeyStroke.getKeyStroke("9"), "pressed");
                    if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 2)
                        ForsakenDefiler.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                    ForsakenDefiler.getActionMap().put("pressed", TemptAction);
                } else
                if(w.usedForsaken.defiling)
                    w.append(t, "\n\n" + w.usedForsaken.mainName + " has " + c.mainName + " completely under control.  There's no need for the Demons to get involved.");
                else
                    w.append(t, "\n\n" + c.getMainName() + " is engaged in combat with " + w.usedForsaken.mainName + ".  There's no room for the Demons to get involved.");
            }
        } else
        {
            String PAINname = "PAIN";
            String INJUname = "INJU";
            if(w.tickle())
            {
                PAINname = "TICK";
                INJUname = "ANTI";
            }
            JButton Attack = new JButton(AttackAction) {

                public Point getToolTipLocation(MouseEvent e)
                {
                    return new Point(0, -30);
                }

            };
            Attack.setToolTipText("Inflicts " + PAINname);
            Attack.getInputMap(2).put(KeyStroke.getKeyStroke("3"), "pressed");
            if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 5)
                Attack.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
            Attack.getActionMap().put("pressed", AttackAction);
            if(w.finalBattle && w.getTechs()[44].isOwned())
                Attack.setBackground(YELLOWISH);
            JButton Slime = new JButton(SlimeAction) {

                public Point getToolTipLocation(MouseEvent e)
                {
                    return new Point(0, -30);
                }

            };
            Slime.getInputMap(2).put(KeyStroke.getKeyStroke("2"), "pressed");
            if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 4)
                Slime.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
            Slime.getActionMap().put("pressed", SlimeAction);
            Slime.setToolTipText("Inflicts DISG");
            if(w.finalBattle && w.getTechs()[43].isOwned() && c.isHypnotized())
                Slime.setBackground(YELLOWISH);
            JButton Taunt = new JButton(TauntAction) {

                public Point getToolTipLocation(MouseEvent e)
                {
                    return new Point(0, -30);
                }

            };
            Taunt.setToolTipText("Inflicts SHAM");
            Taunt.getInputMap(2).put(KeyStroke.getKeyStroke("4"), "pressed");
            if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 6)
                Taunt.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
            Taunt.getActionMap().put("pressed", TauntAction);
            if(w.finalBattle && w.getTechs()[45].isOwned() && c.isParasitized() && c.surroundPossible(w))
                Taunt.setBackground(YELLOWISH);
            JButton Threaten = new JButton(ThreatenAction) {

                public Point getToolTipLocation(MouseEvent e)
                {
                    return new Point(0, -30);
                }

            };
            Threaten.setToolTipText("Inflicts FEAR");
            Threaten.getInputMap(2).put(KeyStroke.getKeyStroke("1"), "pressed");
            if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 3)
                Threaten.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
            Threaten.getActionMap().put("pressed", ThreatenAction);
            Boolean impregnatedAlly = false;
            for(int i = 0; i < 3; i++)
                if(i != c.getNumber() && w.getCast()[i] != null && w.getCast()[i].isImpregnated() && w.getCast()[i].alive)
                    impregnatedAlly = true;

            if(w.finalBattle && w.getTechs()[42].isOwned() && impregnatedAlly)
                Threaten.setBackground(YELLOWISH);
            p.add(Threaten);
            p.add(Slime);
            p.add(Attack);
            p.add(Taunt);
            if(c.surroundPossible(w))
            {
                JButton Surround = new JButton(SurroundAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -60);
                    }

                };
                Surround.setBackground(YELLOWISH);
                Surround.getInputMap(2).put(KeyStroke.getKeyStroke(90, 0), "pressed");
                if(w.onTrack && w.getActions().length > w.getCurrentAction() && (w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 1 || w.getTechs()[31].isOwned() && !c.isSurrounded() && w.getActions()[w.getCurrentAction()] >= c.getNumber() * 14 + 7 && w.getActions()[w.getCurrentAction()] <= c.getNumber() * 14 + 14))
                    Surround.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Surround.getActionMap().put("pressed", SurroundAction);
                p.add(Surround);
            }
            if(w.getCapturesPossible() > 0 && (c.getDefenseLevel() < 9000 || w.getBodyStatus()[24]) && w.commanderFree())
            {
                JButton Capture = new JButton(CaptureAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -90);
                    }

                };
                Capture.setBackground(PURPLISH);
                if(w.upgradedCommander())
                {
                    String description = "<html><center>Constantly inflicts ";
                    if(w.getBodyStatus()[26])
                    {
                        int types = 2;
                        String damages[] = new String[4];
                        if(w.getBodyStatus()[19])
                            damages[0] = "HATE";
                        else
                        if(w.getBodyStatus()[20])
                            damages[0] = "PLEA";
                        else
                        if(w.getBodyStatus()[21])
                        {
                            if(w.tickle())
                                damages[0] = "ANTI";
                            else
                                damages[0] = "INJU";
                        } else
                        if(w.getBodyStatus()[22])
                            damages[0] = "EXPO";
                        if(w.getBodyStatus()[11])
                        {
                            damages[1] = "HATE";
                            damages[2] = "PLEA";
                        } else
                        if(w.getBodyStatus()[12])
                        {
                            damages[1] = "PLEA";
                            if(w.tickle())
                                damages[2] = "ANTI";
                            else
                                damages[2] = "INJU";
                        } else
                        if(w.getBodyStatus()[13])
                        {
                            if(w.tickle())
                                damages[1] = "ANTI";
                            else
                                damages[1] = "INJU";
                            damages[2] = "EXPO";
                        } else
                        if(w.getBodyStatus()[14])
                        {
                            damages[1] = "EXPO";
                            damages[2] = "HATE";
                        }
                        if(w.getBodyStatus()[3])
                            damages[3] = "HATE";
                        else
                        if(w.getBodyStatus()[4])
                            damages[3] = "PLEA";
                        else
                        if(w.getBodyStatus()[5])
                        {
                            if(w.tickle())
                                damages[3] = "ANTI";
                            else
                                damages[3] = "INJU";
                        } else
                        if(w.getBodyStatus()[6])
                            damages[3] = "EXPO";
                        if(!damages[1].equals(damages[0]) && !damages[2].equals(damages[0]))
                            types++;
                        if(!damages[3].equals(damages[0]) && !damages[3].equals(damages[1]) && !damages[3].equals(damages[2]))
                            types++;
                        if(types == 2)
                        {
                            description = (new StringBuilder(description)).append(damages[0]).append(" and ").toString();
                            if(damages[0].equals(damages[1]))
                                description = (new StringBuilder(description)).append(damages[2]).toString();
                            else
                                description = (new StringBuilder(description)).append(damages[1]).toString();
                        } else
                        if(types == 3)
                        {
                            description = (new StringBuilder(description)).append(damages[0]).append(", ").toString();
                            if(damages[0].equals(damages[1]))
                                description = (new StringBuilder(description)).append(damages[3]).append(", and ").append(damages[2]).toString();
                            else
                            if(damages[0].equals(damages[2]))
                                description = (new StringBuilder(description)).append(damages[1]).append(", and ").append(damages[3]).toString();
                            else
                            if(damages[0].equals(damages[3]) || damages[1].equals(damages[3]))
                                description = (new StringBuilder(description)).append(damages[1]).append(", and ").append(damages[2]).toString();
                            else
                                description = (new StringBuilder(description)).append(damages[2]).append(", and ").append(damages[1]).toString();
                        } else
                        {
                            description = (new StringBuilder(description)).append(damages[0]).append(", ").append(damages[1]).append(", ").append(damages[3]).append(", and ").append(damages[2]).toString();
                        }
                        description = (new StringBuilder(description)).append(" along with<br>all four traumas").toString();
                    } else
                    if(w.getBodyStatus()[19])
                        description = (new StringBuilder(description)).append("HATE along with<br>FEAR, DISG, ").append(PAINname).append(", and SHAM").toString();
                    else
                    if(w.getBodyStatus()[20])
                        description = (new StringBuilder(description)).append("PLEA along with<br>DISG, ").append(PAINname).append(", SHAM, and FEAR").toString();
                    else
                    if(w.getBodyStatus()[21])
                        description = (new StringBuilder(description)).append(INJUname).append(" along with<br>").append(PAINname).append(", SHAM, FEAR, and DISG").toString();
                    else
                    if(w.getBodyStatus()[22])
                        description = (new StringBuilder(description)).append("EXPO along with<br>SHAM, FEAR, DISG, and ").append(PAINname).toString();
                    else
                    if(w.getBodyStatus()[18])
                    {
                        String damages[] = new String[3];
                        if(w.getBodyStatus()[3])
                            damages[1] = "HATE";
                        else
                        if(w.getBodyStatus()[4])
                            damages[1] = "PLEA";
                        else
                        if(w.getBodyStatus()[5])
                            damages[1] = INJUname;
                        else
                        if(w.getBodyStatus()[6])
                            damages[1] = "EXPO";
                        if(w.getBodyStatus()[11])
                        {
                            damages[0] = "HATE";
                            damages[2] = "PLEA";
                        } else
                        if(w.getBodyStatus()[12])
                        {
                            damages[0] = "PLEA";
                            damages[2] = INJUname;
                        } else
                        if(w.getBodyStatus()[13])
                        {
                            damages[0] = INJUname;
                            damages[2] = "EXPO";
                        } else
                        if(w.getBodyStatus()[14])
                        {
                            damages[0] = "EXPO";
                            damages[2] = "HATE";
                        }
                        if(damages[0].equals(damages[2]))
                            description = (new StringBuilder(description)).append(damages[0]).append(" and ").append(damages[1]).toString();
                        else
                        if(damages[1].equals(damages[2]))
                            description = (new StringBuilder(description)).append(damages[1]).append(" and ").append(damages[0]).toString();
                        else
                            description = (new StringBuilder(description)).append(damages[0]).append(", ").append(damages[1]).append(", and ").append(damages[2]).toString();
                        description = (new StringBuilder(description)).append(" along with<br>all four traumas").toString();
                    } else
                    if(w.getBodyStatus()[11])
                        description = (new StringBuilder(description)).append("HATE and PLEA along with<br>FEAR, DISG, ").append(PAINname).append(", and SHAM").toString();
                    else
                    if(w.getBodyStatus()[12])
                        description = (new StringBuilder(description)).append("PLEA and ").append(INJUname).append(" along with<br>DISG, ").append(PAINname).append(", SHAM, and FEAR").toString();
                    else
                    if(w.getBodyStatus()[13])
                        description = (new StringBuilder(description)).append(INJUname).append(" and EXPO along with<br>").append(PAINname).append(", SHAM, FEAR, and DISG").toString();
                    else
                    if(w.getBodyStatus()[14])
                        description = (new StringBuilder(description)).append("EXPO and HATE along with<br>SHAM, FEAR, DISG, and ").append(PAINname).toString();
                    else
                    if(w.getBodyStatus()[10])
                    {
                        Boolean firstFound = false;
                        if(w.getBodyStatus()[3])
                        {
                            description = (new StringBuilder(description)).append("HATE").toString();
                            firstFound = true;
                        }
                        if(w.getBodyStatus()[4])
                        {
                            if(firstFound)
                                description = (new StringBuilder(description)).append(" and ").toString();
                            description = (new StringBuilder(description)).append("PLEA").toString();
                            firstFound = true;
                        }
                        if(w.getBodyStatus()[5])
                        {
                            if(firstFound)
                                description = (new StringBuilder(description)).append(" and ").toString();
                            description = (new StringBuilder(description)).append(INJUname).toString();
                            firstFound = true;
                        }
                        if(w.getBodyStatus()[6])
                            description = " and EXPO";
                        description = (new StringBuilder(description)).append(" along with<br>all four traumas").toString();
                    } else
                    if(w.getBodyStatus()[3])
                        description = (new StringBuilder(description)).append("HATE along with<br>FEAR, DISG, ").append(PAINname).append(", and SHAM").toString();
                    else
                    if(w.getBodyStatus()[4])
                        description = (new StringBuilder(description)).append("PLEA along with<br>DISG, ").append(PAINname).append(", SHAM, and FEAR").toString();
                    else
                    if(w.getBodyStatus()[5])
                        description = (new StringBuilder(description)).append(INJUname).append(" along with<br>").append(PAINname).append(", SHAM, FEAR, and DISG").toString();
                    else
                    if(w.getBodyStatus()[6])
                        description = (new StringBuilder(description)).append("EXPO along with<br>SHAM, FEAR, DISG, and ").append(PAINname).toString();
                    else
                        description = "<html><center>Surrounds the target";
                    description = (new StringBuilder(description)).append("<br>for ").toString();
                    if(w.getBodyStatus()[25])
                        description = (new StringBuilder(description)).append("eight").toString();
                    else
                    if(w.getBodyStatus()[15])
                        description = (new StringBuilder(description)).append("six").toString();
                    else
                    if(w.getBodyStatus()[9])
                        description = (new StringBuilder(description)).append("five").toString();
                    else
                    if(w.getBodyStatus()[7])
                        description = (new StringBuilder(description)).append("four").toString();
                    else
                    if(w.getBodyStatus()[1])
                        description = (new StringBuilder(description)).append("three").toString();
                    else
                        description = (new StringBuilder(description)).append("two").toString();
                    description = (new StringBuilder(description)).append(" rounds").toString();
                    if(w.getBodyStatus()[8])
                    {
                        description = (new StringBuilder(description)).append(" (").toString();
                        if(w.getCapturesPossible() == 4)
                            description = (new StringBuilder(description)).append("four").toString();
                        else
                        if(w.getCapturesPossible() == 3)
                            description = (new StringBuilder(description)).append("three").toString();
                        else
                        if(w.getCapturesPossible() == 2)
                            description = (new StringBuilder(description)).append("two").toString();
                        else
                        if(w.getCapturesPossible() == 1)
                            description = (new StringBuilder(description)).append("one").toString();
                        description = (new StringBuilder(description)).append(" left)").toString();
                    }
                    if(w.getBodyStatus()[11])
                        description = (new StringBuilder(description)).append("<br>Above 10k HATE, causes tier-2 Morality Break").toString();
                    else
                    if(w.getBodyStatus()[12])
                        description = (new StringBuilder(description)).append("<br>Above 10k PLEA, causes tier-2 Innocence Break").toString();
                    else
                    if(w.getBodyStatus()[13])
                        description = (new StringBuilder(description)).append("<br>Above 10k ").append(INJUname).append(", causes tier-2 Confidence Break").toString();
                    else
                    if(w.getBodyStatus()[14])
                        description = (new StringBuilder(description)).append("<br>Above 10k EXPO, causes tier-2 Dignity Break").toString();
                    if(w.getBodyStatus()[19])
                        description = (new StringBuilder(description)).append("<br>Above 1000% Impregnation effectiveness, causes Total Morality Break").toString();
                    else
                    if(w.getBodyStatus()[20])
                        description = (new StringBuilder(description)).append("<br>Above 1000% Hypnosis effectiveness, causes Total Innocence Break").toString();
                    else
                    if(w.getBodyStatus()[21])
                        description = (new StringBuilder(description)).append("<br>Above 1000% Drain effectiveness, causes Total Confidence Break").toString();
                    else
                    if(w.getBodyStatus()[22])
                        description = (new StringBuilder(description)).append("<br>Above 1000% Parasitism effectiveness, causes Total Dignity Break").toString();
                    if(w.usedForsaken != null)
                        description = (new StringBuilder("<html><center>Grab with ")).append(w.usedForsaken.mainName).append(" for ").append(w.usedForsaken.compatibility(c)).append(" rounds<br>").append(w.usedForsaken.describeCombatStyle(w, false)).toString();
                    description = (new StringBuilder(description)).append("</center></html>").toString();
                    Capture.setToolTipText(description);
                }
                Capture.getInputMap(2).put(KeyStroke.getKeyStroke(67, 0), "pressed");
                if(w.onTrack && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 2)
                    Capture.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Capture.getActionMap().put("pressed", CaptureAction);
                p.add(Capture);
            }
        }
        int defeated = 0;
        int dissociated = 0;
        int targets = 0;
        for(int i = 0; i < 3; i++)
            if(w.getCombatants()[i] != null)
                if(w.finalBattle && (!w.getCombatants()[i].alive || w.getCombatants()[i].resolve <= 0))
                    defeated++;
                else
                if(w.getCombatants()[i].dissociated)
                    dissociated++;
                else
                if(!w.getCombatants()[i].isCaptured() && (!w.getCombatants()[i].isSurrounded() || !w.getCombatants()[i].isDefiled() && (w.getCombatants()[i].getHATELevel() >= 3 || w.getCombatants()[i].getPLEALevel() >= 3 || w.getCombatants()[i].getINJULevel() >= 3 || w.getCombatants()[i].getEXPOLevel() >= 3 || !w.getCombatants()[i].grind || !w.getCombatants()[i].caress || !w.getCombatants()[i].pummel || !w.getCombatants()[i].humiliate)) && w.getCombatants()[i] != c)
                    targets++;

        if(targets > 0)
        {
            class BackButton extends AbstractAction
            {

                public BackButton(String text, String desc) {
                    super(text);
                    this.putValue(SHORT_DESCRIPTION, desc);
                }
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, "\n\n" + w.getSeparator() + "\n");
                    Project.PickTarget(t, p, f, w);
                    if(w.tutorialResponse() && w.getBattleRound() == 6 && c == w.getCast()[2])
                        w.grayAppend(t, "\n\n(We created another opening last turn, but because we've already grabbed Miracle once, her defense level has gone up.  We'll need at least three opening levels to grab her again.  Fortunately, she's taken enough FEAR and SHAM damage now that it should be easy to push her over 100 in both.  Target Miracle and then Threaten her.)");
                }
            }

            final Action BackAction = new BackButton("Back", "Hotkey:");
            JButton Back = new JButton(BackAction) {

                public Point getToolTipLocation(MouseEvent e)
                {
                    return new Point(0, -30);
                }

            };
            Back.getInputMap(2).put(KeyStroke.getKeyStroke(66, 0), "pressed");
            Back.getActionMap().put("pressed", BackAction);
            p.add(Back);
        } else
        {
            p.add(Pass);
            int occupied = 0;
            for(int i = 0; i < 3; i++)
                if(w.getCombatants()[i] != null)
                    if(w.getCombatants()[i].isSurrounded() && w.getCombatants()[i].getSurroundDuration() > 0)
                    {
                        if(w.getCombatants()[i].getSurroundDuration() > 0)
                            occupied += w.getCombatants()[i].getSurroundDuration();
                        else
                            occupied++;
                    } else
                    if(w.getCombatants()[i].isCaptured())
                        occupied += (w.getCaptureDuration() - w.getCombatants()[i].getCaptureProgression()) + 1;

            final int occupiedBonus = occupied / 5;
            class RetreatButton extends AbstractAction
            {

                public RetreatButton(String text, String desc) {
                    super(text);
                    this.putValue(SHORT_DESCRIPTION, desc);
                }public void actionPerformed(ActionEvent e)
                {
                    p.removeAll();
                    w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                    if(occupiedBonus > 0)
                        w.append(t, String.format("Retreat and end the battle immediately for +%1$s", occupiedBonus) + " Evil Energy?");
                    else
                        w.append(t, "Really retreat?  You will not gain any bonus Evil Energy!");
                    JButton Confirm = new JButton("Confirm");
                    Confirm.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            p.removeAll();
                            w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                            String trapped[] = new String[3];
                            String free[] = new String[3];
                            int trappedNumber = 0;
                            for(int i = 0; i < 3; i++)
                                if(w.getCombatants()[i] != null)
                                    if(w.getCombatants()[i].isSurrounded() || w.getCombatants()[i].isCaptured())
                                    {
                                        for(int j = 0; j < 3; j++)
                                            if(trapped[j] == null)
                                            {
                                                trapped[j] = w.getCombatants()[i].getMainName();
                                                trappedNumber++;
                                                j = 3;
                                            }

                                    } else
                                    {
                                        for(int j = 0; j < 3; j++)
                                            if(free[j] == null)
                                            {
                                                free[j] = w.getCombatants()[i].getMainName();
                                                j = 3;
                                            }

                                    }

                            if(w.getCombatants()[1] == null)
                            {
                                for(int i = 0; i < 3; i++)
                                    if(w.getCast()[i] != null && !w.getCast()[i].equals(w.getCombatants()[0]))
                                        if(free[0] == null)
                                            free[0] = w.getCast()[i].mainName;
                                        else
                                        if(free[1] == null)
                                            free[1] = w.getCast()[i].mainName;
                                        else
                                            free[2] = w.getCast()[i].mainName;

                            } else
                            if(w.getCombatants()[2] == null)
                            {
                                for(int i = 0; i < 3; i++)
                                    if(w.getCast()[i] != null && !w.getCast()[i].equals(w.getCombatants()[0]) && !w.getCast()[i].equals(w.getCombatants()[1]))
                                        if(free[0] == null)
                                            free[0] = w.getCast()[i].mainName;
                                        else
                                        if(free[1] == null)
                                            free[1] = w.getCast()[i].mainName;
                                        else
                                            free[2] = w.getCast()[i].mainName;

                            }
                            w.append(t, "You order your Demons to flee back into the tunnels beneath the city along with their captive victims.  ");
                            if(w.getCast()[1] == null)
                            {
                                if(trappedNumber == 0)
                                    w.append(t, String.format("However, %1$s", free[0]) + " is quick to pursue, cutting your forces down from behind and stopping them from taking any significant number of civilians back to the hive.");
                                else
                                    w.append(t, String.format("%1$s is unable to follow until plenty of civilians are already on their way to the hive.", String.valueOf(trapped[0])));
                            } else
                            if(w.getCast()[2] == null)
                            {
                                if(trappedNumber == 0)
                                    w.append(t, "However, the two Chosen cut your forces down from behind, freeing most of the civilians before they can be brought to the hive.");
                                else
                                if(trappedNumber == 1)
                                    w.append(t, String.format("With %1$s", trapped[0]) + " unable to give chase, the risk of splitting the team forces " + free[0] + " to give up and let you take the civilians to the hive.");
                                else
                                    w.append(t, "The Chosen have to finish dealing with their own problems before they can try to stop you, and by then, plenty of your forces have escaped.");
                            } else
                            if(trappedNumber == 0 || occupiedBonus == 0)
                                w.append(t, "However, the three Chosen cut your forces down from behind, freeing most of the civilians before they can be brought to the hive.");
                            else
                            if(trappedNumber == 1)
                                w.append(t, String.format("%1$s and ", free[0]) + free[1] + " try to give chase, but with " + trapped[0] + " unable to follow, they're forced to give up due to the risk of splitting the team.");
                            else
                            if(trappedNumber == 2)
                                w.append(t, String.format("%1$s tries to stop them, but with ", free[0]) + trapped[0] + " and " + trapped[1] + " unable to help, you're able to get plenty of victims to the hive.");
                            else
                                w.append(t, "The Chosen have to finish dealing with their own problems before they can try to stop you, and by then, plenty of your forces have escaped.");
                            if(occupiedBonus > 0)
                                w.append(t, "\n\n+" + occupiedBonus + " Evil Energy");
                            Project.advanceAction(p, w, 43);
                            w.addEnergy(occupiedBonus);
                            JButton Continue = new JButton("Continue");
                            Continue.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.PostBattle(t, p, f, w);
                                }
                            });
                            p.add(Continue);
                            p.validate();
                            p.repaint();
                        }
                    });
                    p.add(Confirm);
                    JButton Cancel = new JButton("Cancel");
                    Cancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            w.append(t, "\n\n" + w.getSeparator() + "\n");
                            Project.PickTarget(t, p, f, w);
                        }
                    });
                    p.add(Cancel);
                    p.validate();
                    p.repaint();
                }
            }
            final Action RetreatAction = new RetreatButton("Retreat (" + occupiedBonus + ")", "End battle immediately for +" + occupiedBonus + " EE");
            JButton Retreat = new JButton(RetreatAction) {

                public Point getToolTipLocation(MouseEvent e)
                {
                    return new Point(0, -30);
                }

            };
            if(w.getTechs()[19].isOwned() && !w.finalBattle)
                p.add(Retreat);
        }
        if(w.writePossible())
            addWriteButton(p, w);
        p.validate();
        p.repaint();
    }

    public static void EnemyTurn(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Chosen initiative[], int progress)
    {
        Boolean endgame = w.finalBattle;
        Boolean actorFound;
        for(actorFound = false; !actorFound && progress < initiative.length && initiative[progress] != null;)
        {
            w.clearBonus(progress);
            actorFound = true;
            if(w.finalBattle && (!initiative[progress].alive || initiative[progress].resolve <= 0))
            {
                actorFound = false;
                progress++;
            }
        }

        if(actorFound)
        {
            if(w.getCaptureTarget() == initiative[progress] || initiative[progress].isCaptured())
            {
                if(w.usedForsaken == null)
                    w.BeCaptured(t, p, f, w, initiative[progress]);
                else
                    w.usedForsaken.captureChosen(t, p, f, w, initiative[progress]);
            } else
            if(w.getSurroundTarget() == initiative[progress] || initiative[progress].isSurrounded())
                initiative[progress].BeSurrounded(t, p, f, w);
            else
                initiative[progress].TakeTurn(t, p, f, w);
            progress++;
        }
        p.removeAll();
        final int currentProgress = progress;
        Boolean moreTurns = true;
        if(progress > 2)
            moreTurns = false;
        else
        if(initiative[progress] == null)
            moreTurns = false;
        else
        if(w.finalBattle && (!initiative[progress].alive || initiative[progress].resolve <= 0))
            if(++progress > 2)
                moreTurns = false;
            else
            if(initiative[progress] == null)
                moreTurns = false;
            else
            if(w.finalBattle && (!initiative[progress].alive || initiative[progress].resolve <= 0))
                if(++progress > 2)
                    moreTurns = false;
                else
                if(initiative[progress] == null)
                    moreTurns = false;
                else
                if(w.finalBattle && (!initiative[progress].alive || initiative[progress].resolve <= 0))
                    moreTurns = false;
        if(!actorFound)
        {
            int defeated = 0;
            for(int i = 0; i < 3; i++)
                if(initiative[i] != null && (!initiative[i].alive || initiative[i].resolve <= 0))
                    defeated++;

            if(defeated < 3)
            {
                endgame = false;
                w.append(t, "\n\n" + w.getSeparator() + "\n\nThe Demons swarm across the city unopposed!");
            }
        } else
        {
            endgame = false;
        }
        if(w.finalBattle && w.getCast()[2] != null && w.getCast()[0].dissociated && w.getCast()[1].dissociated && w.getCast()[2].dissociated)
            endgame = true;
        if(endgame)
        {
            int captured = 0;
            int dead = 0;
            Chosen survivors[] = new Chosen[3];
            Chosen killed[] = new Chosen[3];
            for(int i = 0; i < 3; i++)
                if(w.getCast()[i].alive)
                {
                    survivors[captured] = w.getCast()[i];
                    captured++;
                } else
                {
                    killed[dead] = w.getCast()[i];
                    dead++;
                }

            w.append(t, "\n\n" + w.getSeparator() + "\n\n");
            if(captured == 3)
                w.append(t, "Finally, all three of the Chosen have surrendered to your forces.  This is a flawless victory - you couldn't have hoped for a better result.  By the time the reinforcements from other cities arrive, your Demonic Barrier has already reached full strength, and no more Chosen can enter without immediately losing their powers and joining the ranks of your captives.\n\nThe Demons escort " + w.getCast()[0].getMainName() + ", " + w.getCast()[1].getMainName() + ", and " + w.getCast()[2].getMainName() + " to your throne room, where you will begin to train them into your own loyal servants...");
            else
            if(captured == 2)
                w.append(t, String.format("With both %1$s", survivors[0].getMainName()) + " and " + survivors[1].getMainName() + " broken, your victory is complete.  By the time the reinforcements from other cities arrive, your Demonic Barrier has already reached full strength, and no more Chosen can enter without immediately losing their powers and joining the ranks of your captives.\n\n" + killed[0].getMainName() + "'s death was unfortunate - " + killed[0].heShe() + " would have made an excellent servant.  But you still have " + survivors[0].getMainName() + " and " + survivors[1].getMainName() + ".  The Demons escort them to your throne room so that their training can begin...");
            else
                w.append(t, String.format("With %1$s", survivors[0].getMainName()) + " defeated, your takeover of the city is complete.  By the time the reinforcements from other cities arrive, your Demonic Barrier has already reached full strength, and no more Chosen can enter without immediately losing their powers and joining the ranks of your captives.\n\nThe deaths of " + killed[0].getMainName() + " and " + killed[1].getMainName() + " were very unfortunate - they would have made excellent servants.  But you still managed to hold onto one prize.  The Demons escort " + survivors[0].getMainName() + " into your throne room so that " + survivors[0].hisHer() + " training can begin...");
            EndFinalBattle(t, p, f, w);
        } else
        if(moreTurns)
        {
            class ContinueButton extends AbstractAction
            {

                public ContinueButton(String text, String desc) {
                    super(text);
                    this.putValue(SHORT_DESCRIPTION, desc);
                }
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.EnemyTurn(t, p, f, w, initiative, currentProgress);
                }
            }

            final Action ContinueAction = new ContinueButton("Continue", "Hotkey:");
            JButton Continue = new JButton(ContinueAction) {

                public Point getToolTipLocation(MouseEvent e)
                {
                    return new Point(0, -30);
                }

            };
            Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
            Continue.getActionMap().put("pressed", ContinueAction);
            p.add(Continue);
            p.validate();
            p.repaint();
        } else
        {
            Chosen synch[] = new Chosen[0];
            for(int i = 0; i < 3; i++)
                if(w.getCombatants()[i] != null)
                {
                    int type = 0;
                    if(w.getCombatants()[i].isInseminated())
                        type = 1;
                    else
                    if(w.getCombatants()[i].isOrgasming())
                        type = 2;
                    else
                    if(w.getCombatants()[i].isSodomized())
                        type = 3;
                    else
                    if(w.getCombatants()[i].isBroadcasted())
                        type = 4;
                    if(type > 0)
                    {
                        for(int j = i + 1; j < 3; j++)
                            if(w.getCombatants()[j] != null)
                            {
                                int otherType = 0;
                                if(w.getCombatants()[j].isInseminated())
                                    otherType = 1;
                                else
                                if(w.getCombatants()[j].isOrgasming())
                                    otherType = 2;
                                else
                                if(w.getCombatants()[j].isSodomized())
                                    otherType = 3;
                                else
                                if(w.getCombatants()[j].isBroadcasted())
                                    otherType = 4;
                                if(type == otherType)
                                    if(synch.length == 0)
                                        synch = (new Chosen[] {
                                            w.getCombatants()[i], w.getCombatants()[j]
                                        });
                                    else
                                        synch = w.getCombatants();
                            }

                    }
                }

            if(synch.length > 1)
                w.synchSurroundDurations(synch);
            class ContinueButton extends AbstractAction
            {

                public ContinueButton(String text, String desc) {
                    super(text);
                    this.putValue(SHORT_DESCRIPTION, desc);
                }
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                    Boolean newChosen = false;
                    if(w.getCast()[1] == null)
                    {
                        if(w.getTotalRounds() >= (18 * (15 - w.eventOffset)) / 15 || w.day > 20)
                            newChosen = true;
                    } else
                    if(w.getCast()[2] == null && (w.getTotalRounds() >= (60 * (15 - w.eventOffset)) / 15 || w.day > 30))
                        newChosen = true;
                    if((w.evacComplete() || w.getBattleRound() < 4) && (w.getCast()[1] != null && w.getTotalRounds() < (80 * (15 - w.eventOffset)) / 15 && w.day <= 30 || w.getCast()[1] == null && w.getTotalRounds() < (28 * (15 - w.eventOffset)) / 15 && w.day <= 20))
                        newChosen = false;
                    int arrival = -1;
                    int timerStandard = 10000;
                    for(int i = 0; i < 3; i++)
                        if(w.decrementArrival(i))
                        {
                            Chosen thisChosen = w.getCast()[i];
                            Boolean successfulArrival = true;
                            if(thisChosen == w.getCombatants()[0])
                                successfulArrival = false;
                            else
                            if(w.getCombatants()[1] != null && w.getCombatants()[1] == thisChosen)
                                successfulArrival = false;
                            if(w.getCast()[i] == null)
                                successfulArrival = false;
                            if(successfulArrival && w.getArrivalTimer()[i] < timerStandard)
                            {
                                arrival = i;
                                timerStandard = w.getArrivalTimer()[i];
                            }
                        }

                    if(w.getCombatants()[2] != null)
                        arrival = -1;
                    if(newChosen)
                    {
                        p.removeAll();
                        Chosen arrivingChosen = new Chosen();
                        if(w.getCast()[1] == null)
                            arrivingChosen.setNumber(1);
                        else
                            arrivingChosen.setNumber(2);
                        arrivingChosen.generate(w);
                        w.addChosen(arrivingChosen);
                        w.addToCombat(arrivingChosen);
                        if(arrivingChosen.type == Chosen.Species.SUPERIOR)
                            w.append(t, "A sudden ripple passes through your army, a psychic shockwave carrying an emotion rarely felt by Demons: fear.  Those with voices let loose roars of dismay, and the rest slither backward to gain some distance from the newcomer on the rooftop on the edge of the battlefield.\n\nRendered indistinct by the sun at its back, the newcomer's silhouette could belong to anyone at all.  But even though there's nothing special about the flash of light and the thunderclap of the Chosen's transformation, the Demons recognize the voice that rings out, and they know that one of their most dangerous foes has arrived.\n\n");
                        else
                            w.append(t, String.format("As the battle rages below, an unfamiliar figure arrives on the nearby rooftops.  After watching for a moment, %1$s", arrivingChosen.heShe()) + " makes a fateful decision.  A loud crack rings through the air, light shining from above!\n\n");
                        arrivingChosen.say(t, "\"" + arrivingChosen.announcement() + "\"\n\n");
                        arrivingChosen.transform(t, w);
                        w.append(t, "\n\n");
                        arrivingChosen.printGreeting(t, w);
                        Chosen responding = w.getCombatants()[1];
                        if(w.getCombatants()[1] == arrivingChosen)
                            responding = w.getCombatants()[0];
                        else
                        if((w.getCombatants()[1].isSurrounded() || w.getCombatants()[1].isCaptured()) && !w.getCombatants()[0].isSurrounded() && !w.getCombatants()[0].isCaptured())
                            responding = w.getCombatants()[0];
                        if(!responding.isSurrounded() && !responding.isCaptured())
                        {
                            w.append(t, "\n\n");
                            responding.printResponse(t, w);
                        }
                        class ContinueButtonTwo extends AbstractAction
                        {
                            public ContinueButtonTwo(String text, String desc) {
                                super(text);
                                this.putValue(SHORT_DESCRIPTION, desc);
                            }
                            public void actionPerformed(ActionEvent e)
                            {
                                w.append(t, "\n\n" + w.getSeparator() + "\n");
                                w.endTurn(t);
                                w.append(t, "\n");
                                Project.PickTarget(t, p, f, w);
                            }
                        }

                        Action ContinueActionTwo = new ContinueButtonTwo("Continue", "Hotkey:");
                        JButton Continue = new JButton(ContinueActionTwo) {
                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -30);
                            }
                        };
                        Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Continue.getActionMap().put("pressed", ContinueActionTwo);
                        p.add(Continue);
                        p.validate();
                        p.repaint();
                    } else
                    if(arrival >= 0)
                    {
                        p.removeAll();
                        Chosen arrivingChosen = w.getCast()[arrival];
                        arrivingChosen.say(t, "\"" + arrivingChosen.announcement() + "\"\n\n");
                        arrivingChosen.transform(t, w);
                        if(w.finalBattle)
                        {
                            w.addToCombat(arrivingChosen);
                            w.append(t, "\n\n");
                            w.finalBattleIntro(t, arrivingChosen);
                        } else
                        {
                            Chosen responder = null;
                            for(Boolean response = false; !response;)
                            {
                                if(Math.random() < 0.5D)
                                {
                                    if(!w.getCombatants()[0].isSurrounded() && !w.getCombatants()[0].isCaptured() && w.getRelationship(w.getCombatants()[0].getNumber(), arrivingChosen.getNumber()) != 0)
                                    {
                                        responder = w.getCombatants()[0];
                                        response = true;
                                    }
                                } else
                                if(w.getCombatants()[1] != null && !w.getCombatants()[1].isSurrounded() && !w.getCombatants()[1].isCaptured() && w.getRelationship(w.getCombatants()[1].getNumber(), arrivingChosen.getNumber()) != 0)
                                {
                                    responder = w.getCombatants()[1];
                                    response = true;
                                }
                                if(w.getCombatants()[0].isSurrounded() || w.getCombatants()[0].isCaptured() || w.getRelationship(w.getCombatants()[0].getNumber(), arrivingChosen.getNumber()) == 0)
                                    if(w.getCombatants()[1] == null)
                                        response = true;
                                    else
                                    if(w.getCombatants()[1].isSurrounded() || w.getCombatants()[1].isCaptured() || w.getRelationship(w.getCombatants()[1].getNumber(), arrivingChosen.getNumber()) == 0)
                                        response = true;
                            }

                            w.addToCombat(arrivingChosen);
                            if(responder != null)
                            {
                                w.append(t, "\n\n");
                                responder.printGreetingAgain(t, w, arrivingChosen);
                            }
                        }
                        class ContinueButtonTwo extends AbstractAction
                        {

                            public ContinueButtonTwo(String text, String desc) {
                                super(text);
                                this.putValue(SHORT_DESCRIPTION, desc);
                            }
                            public void actionPerformed(ActionEvent e)
                            {
                                w.append(t, "\n\n" + w.getSeparator() + "\n");
                                if(w.endTurn(t))
                                {
                                    w.append(t, "\n");
                                    if(w.finalBattle)
                                    {
                                        Project.DefeatScene(t, p, f, w);
                                    } else
                                    {
                                        p.removeAll();
                                        w.append(t, "The Demonic forces have been routed, and the stragglers flee back into their underground tunnels.  Crisis workers arrive to round up the remaining Thralls for purification.  Meanwhile, ");
                                        Chosen c;
                                        for(c = null; c == null;)
                                        {
                                            c = w.getCombatants()[(int)(Math.random() * 3D)];
                                            if(c != null && c.isSurrounded())
                                                c = null;
                                        }

                                        if(w.getCombatants()[1] == null)
                                            w.append(t, String.format("%1$s returns", c.getMainName()));
                                        else
                                            w.append(t, "the Chosen return");
                                        w.append(t, " home to prepare for tomorrow's fight.\n\n");
                                        c.VictoryLine(t, p, f, w);
                                        JButton ContinueTwo = new JButton("Continue");
                                        ContinueTwo.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e)
                                            {
                                                Project.PostBattle(t, p, f, w);
                                            }
                                        });
                                        p.add(ContinueTwo);
                                        p.validate();
                                        p.repaint();
                                    }
                                } else
                                {
                                    w.append(t, "\n");
                                    Project.PickTarget(t, p, f, w);
                                    if(w.tutorialResponse())
                                        if(w.getBattleRound() == 6)
                                        {
                                            if(w.getCast()[0].currentPAIN == 108L)
                                                w.grayAppend(t, "\n\n(The factors that determine when reinforcements show up are the personalities and relationships of the initially-targeted Chosen and the arriving Chosen.  This means that as long as their relationship doesn't change, Axiom will always show up on Round 6 when we go after Miracle.\n\nLet's target Axiom and then use Examine to see what she's like.)");
                                            else
                                                w.endTutorial();
                                        } else
                                        if(w.getBattleRound() == 7)
                                            if(w.getCast()[0].getCurrentFEAR() == 133L)
                                                w.grayAppend(t, "\n\n(We put another FEAR level on Miracle, but FEAR only provides an opening when one of the other Chosen is already surrounded.  Fortunately, another possible target has just arrived.  Target Spice and use Examine to see what we can expect from her.)");
                                            else
                                                w.endTutorial();
                                }
                            }
                        }

                        Action ContinueActionTwo = new ContinueButtonTwo("Continue", "Hotkey:");
                        JButton Continue = new JButton(ContinueActionTwo) {
                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -30);
                            }
                        };
                        Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Continue.getActionMap().put("pressed", ContinueActionTwo);
                        p.add(Continue);
                        p.validate();
                        p.repaint();
                    } else
                    if(w.endTurn(t))
                    {
                        p.removeAll();
                        if(w.finalBattle)
                        {
                            Project.DefeatScene(t, p, f, w);
                        } else
                        {
                            w.append(t, "The Demonic forces have been routed, and the stragglers flee back into their underground tunnels.  Crisis workers arrive to round up the remaining Thralls for purification.  Meanwhile, ");
                            Chosen c;
                            for(c = null; c == null;)
                            {
                                c = w.getCombatants()[(int)(Math.random() * 3D)];
                                if(c != null && c.isSurrounded())
                                    c = null;
                            }

                            if(w.getCombatants()[1] == null)
                                w.append(t, String.format("%1$s returns", c.getMainName()));
                            else
                                w.append(t, "the Chosen return");
                            w.append(t, " home to prepare for tomorrow's fight.\n\n");
                            c.VictoryLine(t, p, f, w);
                            JButton ContinueTwo = new JButton("Continue");
                            ContinueTwo.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.PostBattle(t, p, f, w);
                                }
                            });
                            p.add(ContinueTwo);
                            p.validate();
                            p.repaint();
                        }
                    } else
                    {
                        w.append(t, "\n");
                        Project.PickTarget(t, p, f, w);
                        if(w.tutorialResponse())
                            if(w.getBattleRound() == 2)
                            {
                                if(w.getCast()[0].getCurrentDISG() == 70L)
                                    w.grayAppend(t, "\n\n(With the right upgrades, high ANGST, or naturally high vulnerabilities, it's possible to reliably deal 100 or more trauma in a single turn, setting up openings very quickly.  But those don't apply here, so let's use Slime again.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 3)
                            {
                                if(w.getCast()[0].getCurrentDISG() == 140L)
                                {
                                    if(w.tickle())
                                        w.grayAppend(t, "\n\n(Surrounding Miracle right now will only give us one turn to torment her.  In other situations, it might be a good idea to create another opening to increase the duration.  But since she's pretty weak to ANTI, and since we have the upgrade that increases circumstance damage, one turn should be plenty.  Surround her!)");
                                    else
                                        w.grayAppend(t, "\n\n(Surrounding Miracle right now will only give us one turn to torment her.  In other situations, it might be a good idea to create another opening to increase the duration.  But since she's pretty weak to INJU, and since we have the upgrade that increases circumstance damage, one turn should be plenty.  Surround her!)");
                                } else
                                {
                                    w.endTutorial();
                                }
                            } else
                            if(w.getBattleRound() == 4)
                            {
                                if(w.getCast()[0].isSurrounded())
                                {
                                    if(w.tickle())
                                        w.grayAppend(t, "\n\n(Tickle deals ANTIcipation damage, whose main effect is to multiply other circumstance damage, so it's often a good idea to start there.  Try Tickling her.)");
                                    else
                                        w.grayAppend(t, "\n\n(Pummel deals INJU damage, whose main effect is to multiply other circumstance damage, so it's often a good idea to start there.  Try Pummeling her.)");
                                } else
                                {
                                    w.endTutorial();
                                }
                            } else
                            if(w.getBattleRound() == 5)
                            {
                                if(w.getCast()[0].getCurrentINJU() == 126L)
                                {
                                    if(w.tickle())
                                        w.grayAppend(t, "\n\n(She escaped quickly, but not before getting level 1 ANTI.  All circumstances, in addition to their other effects, also apply an extra x2 multiplier to their associated trauma.  For ANTIcipation, that's TICKle.  Even though Miracle doesn't usually take much TICK damage, that extra multiplier will let us use it to create another opening.  Tickle her!");
                                    else
                                        w.grayAppend(t, "\n\n(She escaped quickly, but not before getting level 1 INJU.  All circumstances, in addition to their other effects, also apply an extra x2 multiplier to their associated trauma.  For INJU, that's PAIN.  Even though Miracle doesn't usually take much PAIN damage, that extra multiplier will let us use it to create another opening.  Attack her!");
                                } else
                                {
                                    w.endTutorial();
                                }
                            } else
                            if(w.getBattleRound() == 8)
                            {
                                if(w.getCast()[1].captured)
                                    w.grayAppend(t, "\n\n(The EXPO damage on Spice will increase the circumstance damage (but not the trauma damage) taken by the other two Chosen (but not by Spice herself).  From the extermination bar, we can see that the Chosen won't finish killing the Demons until next turn, so we can still spend another turn setting up.  Taunt Miracle in order to create another opening on her.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 9)
                            {
                                if(w.getCast()[0].getCurrentSHAM() == 105L)
                                    w.grayAppend(t, "\n\n(As long as one of the Chosen is surrounded or captured, the battle won't immediately end when extermination is completed, but any Chosen surrounded or captured after that point will take off into the sky afterward and become ungrabbable.  That means that it's a very good idea to set up situations like this where you can grab Chosen immediately before extermination is completed.  Take this chance to surround Miracle!)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 10)
                            {
                                if(w.getCast()[0].isSurrounded())
                                {
                                    if(w.tickle())
                                        w.grayAppend(t, "\n\n(This surround duration isn't long enough to actually break Miracle, so we want to focus on dealing lots of trauma damage to create some even higher openings.  This is especially important because using a TICK opening causes it to stop providing further openings until it levels up again.  HATE (from Grind) and PLEA (from Caress) both increase the trauma multiplier.  PLEA increases it by more, but HATE also increases the circumstance multiplier.  Because we're planning to apply both, let's start with Grind.)");
                                    else
                                        w.grayAppend(t, "\n\n(This surround duration isn't long enough to actually break Miracle, so we want to focus on dealing lots of trauma damage to create some even higher openings.  This is especially important because using a PAIN opening causes it to stop providing further openings until it levels up again.  HATE (from Grind) and PLEA (from Caress) both increase the trauma multiplier.  PLEA increases it by more, but HATE also increases the circumstance multiplier.  Because we're planning to apply both, let's start with Grind.)");
                                } else
                                {
                                    w.endTutorial();
                                }
                            } else
                            if(w.getBattleRound() == 11)
                            {
                                if(w.getCast()[0].getCurrentHATE() == 153L)
                                    w.grayAppend(t, "\n\n(And now Caress Miracle.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 12)
                            {
                                if(w.getCast()[0].getCurrentPLEA() == 531L)
                                    w.grayAppend(t, "\n\n(At this rate, the battle will end once Miracle escapes, because at that point, none of the Chosen will be surrounded.  We can stop that from happening by surrounding one of the others, but that isn't possible yet.  We have to set up a surround this turn, then use it next turn.  Attack Spice.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 13)
                            {
                                if(w.getCast()[1].currentPAIN == 109L)
                                    w.grayAppend(t, "\n\n(And now Surround Spice.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 14)
                            {
                                if(w.getCast()[1].isSurrounded())
                                    w.grayAppend(t, "\n\n(It's tempting to try to do something with Spice, but because extermination was already completed, we won't be able to grab her again after this.  We don't have a way to increase her EXPO by another level, so it wouldn't help us break Miracle, either.  Just leave Spice alone and surround Miracle again.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 15)
                            {
                                if(w.getCast()[0].isSurrounded())
                                    w.grayAppend(t, "\n\n(Miracle's multipliers don't look too impressive, but because we've built up such a long surround duration, we have time to improve them.  HATE is already almost at the next level, so start with Grind on Miracle.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 16)
                            {
                                if(w.getCast()[0].getCurrentHATE() == 958L)
                                {
                                    if(w.tickle())
                                        w.grayAppend(t, "\n\n(Next, Tickle Miracle again.)");
                                    else
                                        w.grayAppend(t, "\n\n(Next, Pummel Miracle again.)");
                                } else
                                {
                                    w.endTutorial();
                                }
                            } else
                            if(w.getBattleRound() == 17)
                            {
                                if(w.getCast()[0].getCurrentINJU() == 415L)
                                {
                                    w.grayAppend(t, "\n\n(We can see that HATE and PLEA are being penalized because their associated traumas (FEAR and DISG) are pulling ahead of the other two.  Keeping the traumas balanced is an important part of keeping the Chosen off-guard so that they can't muster resistance against any one circumstance.  ");
                                    if(w.tickle())
                                        w.grayAppend(t, "TICK should catch up since we're tickling her");
                                    else
                                        w.grayAppend(t, "PAIN should catch up since we're pummeling her");
                                    w.grayAppend(t, ", but SHAM might have trouble.  Therefore, even though we aren't trying to deal any damage to the other two Chosen, we should still Humiliate Miracle here.");
                                }
                            } else
                            if(w.getBattleRound() == 18)
                            {
                                if(w.getCast()[0].getCurrentEXPO() == 372L)
                                {
                                    w.grayAppend(t, "\n\n(That did it!  Miracle saw that after ");
                                    if(w.tickle())
                                        w.grayAppend(t, "TICK, SHAM, and EXPO");
                                    else
                                        w.grayAppend(t, "PAIN, SHAM, and INJU");
                                    w.grayAppend(t, " leveled up, she'd be facing a massive x18 multiplier to her circumstances.  With the long surround duration, that would put her at risk of reaching 10k damage and unlocking a new level of torments.  In order to mitigate the damage and avoid that, she threw her pride away.");
                                    w.grayAppend(t, "\n\nThe bonus Evil Energy you get from breaking the vulnerability is enough to pay for the Commander you used, and now that Miracle has a broken vulnerability, she can be induced to commit greater sins during downtime and increase your Evil Energy income even more, allowing you to buy more upgrades and Commanders to crack the harder vulnerabilities!\n\nThis concludes the tutorial for Corrupted Saviors.  You can finish the battle however you like, or even go back to the start of the tutorial to try a completely different strategy.  Good luck!)");
                                }
                                w.endTutorial();
                            }
                    }
                }
            }

            final Action ContinueAction = new ContinueButton("Continue", "Hotkey:");
            JButton Continue = new JButton(ContinueAction) {

                public Point getToolTipLocation(MouseEvent e)
                {
                    return new Point(0, -30);
                }

            };
            Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
            Continue.getActionMap().put("pressed", ContinueAction);
            p.add(Continue);
            p.validate();
            p.repaint();
        }
    }

    public static void PostBattle(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        Boolean justContinue = true;
        Boolean postScene = false;
        int vignette = -1;
        if(!w.isTutorial() && !w.loopComplete)
            vignette = w.chooseVignette();
        if(w.loopComplete)
            w.pendingBreaks = new int[0];
        final int chosenVignette = vignette;
        for(int i = 0; i < 3; i++)
            if(w.getCast()[i] != null)
            {
                w.getCast()[i].visited = false;
                if(w.getCast()[i].morality < 34 && w.getCast()[i].impregnated)
                {
                    for(int j = 0; j < 3; j++)
                        if(w.getCast()[j] != null && w.getCast()[j].morality > 66)
                        {
                            long _tmp = w.getCast()[j].temptReq;
                        }

                }
                if(w.getCast()[i].confidence < 34 && w.getCast()[i].drained)
                {
                    for(int j = 0; j < 3; j++)
                        if(w.getCast()[j] != null && w.getCast()[j].confidence > 66)
                        {
                            long _tmp1 = w.getCast()[j].temptReq;
                        }

                }
            }

        if(w.isTutorial())
        {
            long totalTrauma = w.getCast()[0].getCurrentFEAR() + w.getCast()[0].getCurrentDISG() + w.getCast()[0].getCurrentPAIN() + w.getCast()[0].getCurrentSHAM() + w.getCast()[1].getCurrentFEAR() + w.getCast()[1].getCurrentDISG() + w.getCast()[1].getCurrentPAIN() + w.getCast()[1].getCurrentSHAM() + w.getCast()[2].getCurrentFEAR() + w.getCast()[2].getCurrentDISG() + w.getCast()[2].getCurrentPAIN() + w.getCast()[2].getCurrentSHAM();
            w.append(t, "\n\n" + w.getSeparator() + "\n\nTotal trauma: " + w.getCast()[0].condensedFormat(totalTrauma) + "\nVulnerabilities broken:");
            int cores = 0;
            int sigs = 0;
            int minors = 0;
            for(int i = 0; i < 3; i++)
            {
                if(w.getCast()[i].isRuthless())
                    if(w.getCast()[i].getMorality() > 66)
                        cores++;
                    else
                    if(w.getCast()[i].getMorality() > 33)
                        sigs++;
                if(!w.getCast()[i].isVVirg())
                    if(w.getCast()[i].getMorality() > 66)
                        cores++;
                    else
                    if(w.getCast()[i].getMorality() > 33)
                        sigs++;
                if(w.getCast()[i].isLustful())
                    if(w.getCast()[i].getInnocence() > 66)
                        cores++;
                    else
                    if(w.getCast()[i].getInnocence() > 33)
                        sigs++;
                    else
                        minors++;
                if(!w.getCast()[i].isCVirg())
                    if(w.getCast()[i].getInnocence() > 66)
                        cores++;
                    else
                    if(w.getCast()[i].getInnocence() > 33)
                        sigs++;
                    else
                        minors++;
                if(w.getCast()[i].isMeek())
                    if(w.getCast()[i].getConfidence() > 66)
                        cores++;
                    else
                    if(w.getCast()[i].getConfidence() > 33)
                        sigs++;
                    else
                        minors++;
                if(!w.getCast()[i].isAVirg())
                    if(w.getCast()[i].getConfidence() > 66)
                        cores++;
                    else
                    if(w.getCast()[i].getConfidence() > 33)
                        sigs++;
                    else
                        minors++;
                if(w.getCast()[i].isDebased())
                    if(w.getCast()[i].getDignity() > 66)
                        cores++;
                    else
                    if(w.getCast()[i].getDignity() > 33)
                        sigs++;
                if(!w.getCast()[i].isModest())
                    if(w.getCast()[i].getDignity() > 66)
                        cores++;
                    else
                    if(w.getCast()[i].getDignity() > 33)
                        sigs++;
            }

            if(cores == 0 && sigs == 0 && minors == 0)
                w.append(t, " none");
            if(cores > 0)
                w.append(t, String.format(" %1$s", cores) + " Core");
            if(sigs > 0)
                w.append(t, String.format(" %1$s", sigs) + " Significant");
            if(minors > 0)
                w.append(t, String.format(" %1$s", minors) + " Minor");
        } else
        if(!w.getCast()[0].isIntroduced())
        {
            justContinue = false;
            w.append(t, "\n\n" + w.getSeparator() + "\n\n");
            w.getCast()[0].printIntro(t, w);
        } else
        if(w.getCast()[1] != null)
        {
            if(!w.getCast()[1].isIntroduced())
            {
                justContinue = false;
                postScene = true;
                w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                w.getCast()[0].firstMeeting(t, w, w.getCast()[1]);
                p.removeAll();
                JButton Continue = new JButton("Continue");
                Continue.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                        w.getCast()[1].printIntro(t, w);
                        JButton Continue2 = new JButton("Continue");
                        Continue2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                Project.Downtime(t, p, f, w);
                            }
                        });
                        p.add(Continue2);
                        p.validate();
                        p.repaint();
                    }
                });
                p.add(Continue);
                p.validate();
                p.repaint();
            } else
            if(w.getCast()[2] != null)
            {
                if(!w.getCast()[2].isIntroduced())
                {
                    justContinue = false;
                    postScene = true;
                    w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                    w.getCast()[2].firstTrio(t, w, w.getCast()[0], w.getCast()[1]);
                    p.removeAll();
                    JButton Continue = new JButton("Continue");
                    Continue.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            p.removeAll();
                            w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                            w.getCast()[2].printIntro(t, w);
                            JButton ContinueTwo = new JButton("Continue");
                            ContinueTwo.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.Downtime(t, p, f, w);
                                }
                            });
                            p.add(ContinueTwo);
                            p.validate();
                            p.repaint();
                        }
                    });
                    p.add(Continue);
                    p.validate();
                    p.repaint();
                } else
                {
                    JButton lastContinue = new JButton("Continue");
                    if(w.getDay() == 15 - w.eventOffset && !w.loopComplete)
                    {
                        justContinue = false;
                        postScene = true;
                        if(w.getBreaks().length == 0)
                            InterviewChain(t, p, f, w);
                        else
                            lastContinue.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.InterviewChain(t, p, f, w);
                                }
                            });
                    } else
                    if(w.getDay() == 30 - w.eventOffset * 2 && !w.loopComplete)
                    {
                        justContinue = false;
                        postScene = true;
                        if(w.getBreaks().length == 0)
                            VacationChain(t, p, f, w);
                        else
                            lastContinue.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.VacationChain(t, p, f, w);
                                }
                            });
                    } else
                    if(w.getDay() == 45 - w.eventOffset * 3 && !w.loopComplete)
                    {
                        justContinue = false;
                        postScene = true;
                        if(w.getBreaks().length == 0)
                            DeploymentChain(t, p, f, w);
                        else
                            lastContinue.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.DeploymentChain(t, p, f, w);
                                }
                            });
                    } else
                    if(chosenVignette >= 0 && w.getBreaks().length == 0)
                    {
                        justContinue = false;
                        if(w.getBreaks().length == 0)
                            w.showVignette(t, chosenVignette);
                        else
                            lastContinue.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    w.showVignette(t, chosenVignette);
                                }
                            });
                    } else
                    {
                        lastContinue.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                Project.Downtime(t, p, f, w);
                            }
                        });
                    }
                    if(w.getBreaks().length > 0)
                    {
                        if(w.getBreaks().length > 1)
                            postScene = true;
                        justContinue = false;
                        SortBreaks(w);
                        HandleBreaks(t, p, f, w, lastContinue);
                    }
                }
            } else
            if(chosenVignette >= 0)
            {
                justContinue = false;
                w.showVignette(t, chosenVignette);
            }
        } else
        if(chosenVignette >= 0)
        {
            justContinue = false;
            w.showVignette(t, chosenVignette);
        }
        if(w.isTutorial())
        {
            p.removeAll();
            JButton Continue = new JButton("Continue");
            Continue.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                    WorldState x = new WorldState();
                    x.copySettings(t, w);
                    x.copyToggles(w);
                    x.save = w.save;
                    Project.IntroOne(t, p, f, x);
                }
            });
            p.add(Continue);
            p.validate();
            p.repaint();
        } else
        if(justContinue)
            Downtime(t, p, f, w);
        else
        if(!postScene)
        {
            p.removeAll();
            JButton Continue = new JButton("Continue");
            Continue.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.Downtime(t, p, f, w);
                }
            });
            p.add(Continue);
            p.validate();
            p.repaint();
        }
    }

    public static void SortBreaks(WorldState worldstate)
    {
    }

    public static void DeploymentChain(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
        Chosen moral = null;
        Chosen innocent = null;
        Chosen confident = null;
        Chosen dignified = null;
        Chosen neitherOne = null;
        Chosen neitherTwo = null;
        Chosen neitherThree = null;
        Chosen neitherFour = null;
        Chosen immoral = null;
        Chosen nocent = null;
        Chosen unconfident = null;
        Chosen undignified = null;
        for(int i = 0; i < 3; i++)
        {
            if(w.getCast()[i].getMorality() > 66)
                moral = w.getCast()[i];
            else
            if(w.getCast()[i].getMorality() > 33)
                neitherOne = w.getCast()[i];
            else
                immoral = w.getCast()[i];
            if(w.getCast()[i].getInnocence() > 66)
                innocent = w.getCast()[i];
            else
            if(w.getCast()[i].getInnocence() > 33)
                neitherTwo = w.getCast()[i];
            else
                nocent = w.getCast()[i];
            if(w.getCast()[i].getConfidence() > 66)
                confident = w.getCast()[i];
            else
            if(w.getCast()[i].getConfidence() > 33)
                neitherThree = w.getCast()[i];
            else
                unconfident = w.getCast()[i];
            if(w.getCast()[i].getDignity() > 66)
                dignified = w.getCast()[i];
            else
            if(w.getCast()[i].getDignity() > 33)
                neitherFour = w.getCast()[i];
            else
                undignified = w.getCast()[i];
        }

        Chosen first = dignified;
        Chosen second = neitherFour;
        Chosen third = undignified;
        Chosen fourth = null;
        Chosen fifth = null;
        Chosen sixth = null;
        Chosen seventh = null;
        Chosen eighth = null;
        Chosen ninth = null;
        if(moral == dignified)
        {
            fourth = innocent;
            fifth = neitherTwo;
            sixth = nocent;
        } else
        {
            fourth = moral;
            fifth = neitherOne;
            sixth = immoral;
        }
        if(dignified == confident || confident == moral)
        {
            seventh = innocent;
            eighth = neitherTwo;
            ninth = nocent;
        } else
        {
            seventh = confident;
            eighth = neitherThree;
            ninth = unconfident;
        }
        final Chosen order[] = {
            first, second, third, fourth, fifth, sixth, seventh, eighth, ninth
        };
        first.deploymentOne(t, w, second, third);
        p.removeAll();
        JButton Continue = new JButton("Continue");
        Continue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                order[3].deploymentTwo(t, w, order[4], order[5]);
                JButton ContinueTwo = new JButton("Continue");
                ContinueTwo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                        order[6].deploymentThree(t, w, order[7], order[8]);
                        JButton ContinueThree = new JButton("Continue");
                        ContinueThree.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                p.removeAll();
                                w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                                Project.Downtime(t, p, f, w);
                            }
                        });
                        p.add(ContinueThree);
                        p.validate();
                        p.repaint();
                    }
                });
                p.add(ContinueTwo);
                p.validate();
                p.repaint();
            }
        });
        p.add(Continue);
        p.validate();
        p.repaint();
    }

    public static void VacationChain(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
        Chosen moral = null;
        Chosen innocent = null;
        Chosen confident = null;
        Chosen dignified = null;
        Chosen neitherOne = null;
        Chosen neitherTwo = null;
        Chosen neitherThree = null;
        Chosen neitherFour = null;
        Chosen immoral = null;
        Chosen nocent = null;
        Chosen unconfident = null;
        Chosen undignified = null;
        for(int i = 0; i < 3; i++)
        {
            if(w.getCast()[i].getMorality() > 66)
                moral = w.getCast()[i];
            else
            if(w.getCast()[i].getMorality() > 33)
                neitherOne = w.getCast()[i];
            else
                immoral = w.getCast()[i];
            if(w.getCast()[i].getInnocence() > 66)
                innocent = w.getCast()[i];
            else
            if(w.getCast()[i].getInnocence() > 33)
                neitherTwo = w.getCast()[i];
            else
                nocent = w.getCast()[i];
            if(w.getCast()[i].getConfidence() > 66)
                confident = w.getCast()[i];
            else
            if(w.getCast()[i].getConfidence() > 33)
                neitherThree = w.getCast()[i];
            else
                unconfident = w.getCast()[i];
            if(w.getCast()[i].getDignity() > 66)
                dignified = w.getCast()[i];
            else
            if(w.getCast()[i].getDignity() > 33)
                neitherFour = w.getCast()[i];
            else
                undignified = w.getCast()[i];
        }

        Chosen first = confident;
        Chosen second = neitherThree;
        Chosen third = unconfident;
        Chosen fourth = null;
        Chosen fifth = null;
        Chosen sixth = null;
        Chosen seventh = null;
        Chosen eighth = null;
        Chosen ninth = null;
        if(confident == dignified)
        {
            fourth = moral;
            fifth = neitherOne;
            sixth = immoral;
        } else
        {
            fourth = dignified;
            fifth = neitherFour;
            sixth = undignified;
        }
        if(confident == innocent || dignified == innocent)
        {
            seventh = moral;
            eighth = neitherOne;
            ninth = immoral;
        } else
        {
            seventh = innocent;
            eighth = neitherTwo;
            ninth = nocent;
        }
        final Chosen order[] = {
            first, second, third, fourth, fifth, sixth, seventh, eighth, ninth
        };
        first.vacationOne(t, w, second, third);
        p.removeAll();
        JButton Continue = new JButton("Continue");
        Continue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                order[3].vacationTwo(t, w, order[4], order[5]);
                JButton ContinueTwo = new JButton("Continue");
                ContinueTwo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                        order[6].vacationThree(t, w, order[7], order[8]);
                        JButton ContinueThree = new JButton("Continue");
                        ContinueThree.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                p.removeAll();
                                w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                                Project.Downtime(t, p, f, w);
                            }
                        });
                        p.add(ContinueThree);
                        p.validate();
                        p.repaint();
                    }
                });
                p.add(ContinueTwo);
                p.validate();
                p.repaint();
            }
        });
        p.add(Continue);
        p.validate();
        p.repaint();
    }

    public static void InterviewChain(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
        Chosen first = null;
        Chosen second = null;
        Chosen third = null;
        Chosen innocents = null;
        Chosen morals = null;
        Chosen confidents = null;
        Chosen dignifieds = null;
        for(int i = 0; i < 3; i++)
        {
            if(w.getCast()[i].getInnocence() > 66)
                innocents = w.getCast()[i];
            if(w.getCast()[i].getMorality() > 66)
                morals = w.getCast()[i];
            if(w.getCast()[i].getConfidence() > 66)
                confidents = w.getCast()[i];
            if(w.getCast()[i].getDignity() > 66)
                dignifieds = w.getCast()[i];
        }

        final Chosen moral = morals;
        final Chosen innocent = innocents;
        final Chosen confident = confidents;
        final Chosen dignified = dignifieds;
        first = innocent;
        if(innocent == moral)
        {
            second = confident;
            third = dignified;
        } else
        {
            second = moral;
            if(innocent == dignified || moral == dignified)
                third = confident;
            else
                third = dignified;
        }
        first.interviewOne(t, w, moral, innocent, confident, dignified);
        final Chosen chosenTwo = second;
        final Chosen chosenThree = third;
        p.removeAll();
        JButton Continue = new JButton("Continue");
        Continue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                chosenTwo.interviewTwo(t, w, moral, innocent, confident, dignified);
                JButton ContinueTwo = new JButton("Continue");
                ContinueTwo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                        chosenThree.interviewThree(t, w, moral, innocent, confident, dignified);
                        JButton ContinueThree = new JButton("Continue");
                        ContinueThree.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                Project.Downtime(t, p, f, w);
                            }
                        });
                        p.add(ContinueThree);
                        p.validate();
                        p.repaint();
                    }
                });
                p.add(ContinueTwo);
                p.validate();
                p.repaint();
            }
        });
        p.add(Continue);
        p.validate();
        p.repaint();
    }

    public static void HandleBreaks(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final JButton proceed)
    {
        p.removeAll();
        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
        int sceneType = w.getBreaks()[0];
        Chosen broken = null;
        Chosen c = null;
        Chosen d = null;
        if(sceneType % 4 == 0)
        {
            for(int i = 0; i < 3; i++)
                if(w.getCast()[i].getMorality() > 66)
                    broken = w.getCast()[i];
                else
                if(w.getCast()[i].getMorality() < 34)
                    c = w.getCast()[i];

        } else
        if(sceneType % 4 == 1)
        {
            for(int i = 0; i < 3; i++)
                if(w.getCast()[i].getInnocence() > 66)
                    broken = w.getCast()[i];
                else
                if(w.getCast()[i].getInnocence() < 34)
                    c = w.getCast()[i];

        } else
        if(sceneType % 4 == 2)
        {
            for(int i = 0; i < 3; i++)
                if(w.getCast()[i].getConfidence() > 66)
                    broken = w.getCast()[i];
                else
                if(w.getCast()[i].getConfidence() < 34)
                    c = w.getCast()[i];

        } else
        if(sceneType % 4 == 3)
        {
            for(int i = 0; i < 3; i++)
                if(w.getCast()[i].getDignity() > 66)
                    broken = w.getCast()[i];
                else
                if(w.getCast()[i].getDignity() < 34)
                    c = w.getCast()[i];

        }
        if(sceneType < 16)
        {
            broken.breakScene(t, w, c, sceneType);
        } else
        {
            c = null;
            d = null;
            if(sceneType == 16)
            {
                for(int i = 0; i < 3; i++)
                    if(w.getCast()[i].temptReq < 100_000L && !w.getCast()[i].pastTempted && (w.getCast()[i].morality > 66 || w.getCast()[i].confidence > 66))
                        broken = w.getCast()[i];

                if(broken == null)
                {
                    w.append(t, "One of the Chosen began to trust the Thralls, but that trust was betrayed before it had time to take root.");
                    w.discardBreak();
                } else
                {
                    if(broken.morality > 66)
                    {
                        for(int i = 0; i < 3; i++)
                            if(w.getCast()[i].morality < 34)
                                c = w.getCast()[i];

                    }
                    if(broken.confidence > 66)
                    {
                        for(int i = 0; i < 3; i++)
                            if(w.getCast()[i].confidence < 34)
                                if(c == null)
                                    c = w.getCast()[i];
                                else
                                if(c != w.getCast()[i])
                                    d = w.getCast()[i];

                    }
                    w.distortionScene(t, broken, c, d, sceneType);
                }
            } else
            if(sceneType == 17)
            {
                for(int i = 0; i < 3; i++)
                    if(w.getCast()[i].dissociationReq < 10 && !w.getCast()[i].pastDissociated && (w.getCast()[i].innocence > 66 || w.getCast()[i].dignity > 66))
                        broken = w.getCast()[i];

                if(broken == null)
                {
                    w.append(t, "One of the Chosen began to fall into despair over being forced into sexual situations while having no say in the matter, but managed to find a way to take ownership of what was happening in the end.");
                    w.discardBreak();
                } else
                {
                    if(broken.innocence > 66)
                    {
                        for(int i = 0; i < 3; i++)
                            if(w.getCast()[i].innocence < 34)
                                c = w.getCast()[i];

                    }
                    if(broken.dignity > 66)
                    {
                        for(int i = 0; i < 3; i++)
                            if(w.getCast()[i].dignity < 34)
                                if(c == null)
                                    c = w.getCast()[i];
                                else
                                    d = w.getCast()[i];

                    }
                    w.distortionScene(t, broken, c, d, sceneType);
                }
            }
        }
        if(w.getBreaks().length > 0)
        {
            JButton Continue = new JButton("Continue");
            Continue.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.HandleBreaks(t, p, f, w, proceed);
                }
            });
            p.add(Continue);
        } else
        {
            p.add(proceed);
        }
        p.validate();
        p.repaint();
    }

    public static void Downtime(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        for(int i = 0; i < 3; i++)
            if(w.getCast()[i] != null)
                if(w.getCast()[i].temptReq < 100_000L)
                    w.getCast()[i].pastTempted = true;
                else
                if(w.getCast()[i].dissociationReq < 10)
                    w.getCast()[i].pastDissociated = true;

        Forsaken exhaustedTest[] = new Forsaken[0];
        if(w.usedForsaken != null)
        {
            int actualCost = w.usedForsaken.motivationCost();
            if(w.usedForsaken.isFormerFriend(w.getCast()[0]) || w.usedForsaken.isFormerFriend(w.getCast()[1]) || w.usedForsaken.isFormerFriend(w.getCast()[2]))
                actualCost *= 2;
            w.usedForsaken.motivation -= actualCost;
            w.usedForsaken.stamina -= 200;
            exhaustedTest = (new Forsaken[] {
                w.usedForsaken
            });
        }
        final Forsaken exhausted[] = exhaustedTest;
        t.setText("");
        w.incrementDay();
        w.clearCommander();
        int lastChosen = 0;
        int totalActions = 23;
        Long actionWeights[][] = new Long[3][totalActions];
        final int chosenAction[] = {
            -1, -1, -1
        };
        if(w.getCast()[2] != null)
            lastChosen = 2;
        else
        if(w.getCast()[1] != null)
            lastChosen = 1;
        for(int i = 0; i <= lastChosen; i++)
            w.getCast()[i].addTrauma();

        long divisor = 1L;
        long highest = 0L;
        for(int i = 0; i <= lastChosen; i++)
        {
            if(w.getCast()[i].getANGST() > highest)
                highest = w.getCast()[i].getANGST();
            if(w.getCast()[i].getTotalFEAR() > highest)
                highest = w.getCast()[i].getTotalFEAR();
            if(w.getCast()[i].getTotalDISG() > highest)
                highest = w.getCast()[i].getTotalDISG();
            if(w.getCast()[i].getTotalPAIN() > highest)
                highest = w.getCast()[i].getTotalPAIN();
            if(w.getCast()[i].getTotalSHAM() > highest)
                highest = w.getCast()[i].getTotalSHAM();
        }

        while(highest > 10_000_000_000_000L) 
        {
            highest /= 10L;
            divisor *= 10L;
        }
        for(int i = 0; i <= lastChosen; i++)
        {
            long fear = w.getCast()[i].getTotalFEAR() / divisor;
            long disg = w.getCast()[i].getTotalDISG() / divisor;
            long pain = w.getCast()[i].getTotalPAIN() / divisor;
            long sham = w.getCast()[i].getTotalSHAM() / divisor;
            long angst = w.getCast()[i].getANGST() / divisor;
            Boolean divided = true;
            if(fear == 0L && disg == 0L && pain == 0L && sham == 0L)
            {
                fear = w.getCast()[i].getTotalFEAR();
                disg = w.getCast()[i].getTotalDISG();
                pain = w.getCast()[i].getTotalPAIN();
                sham = w.getCast()[i].getTotalSHAM();
                angst *= divisor;
                divided = false;
            }
            actionWeights[i][0] = Long.valueOf(150L);
            actionWeights[i][1] = Long.valueOf(50L + (fear * 100L) / (long)(100 + w.getCast()[i].getMorality()));
            actionWeights[i][2] = Long.valueOf(50L + (disg * 100L) / (long)(100 + w.getCast()[i].getMorality()));
            actionWeights[i][3] = Long.valueOf(50L + (pain * 100L) / (long)(100 + w.getCast()[i].getMorality()));
            actionWeights[i][4] = Long.valueOf(50L + (sham * 100L) / (long)(100 + w.getCast()[i].getMorality()));
            long inhibition = (20000 * w.downtimeMultiplier) / 100;
            if(divided)
                inhibition /= divisor;
            if(w.getCast()[i].isRuthless())
                actionWeights[i][5] = Long.valueOf((fear * 200L + pain * 100L + angst * 20L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][5] = Long.valueOf(0L);
            if(w.getCast()[i].isLustful())
                actionWeights[i][6] = Long.valueOf((disg * 200L + fear * 100L + angst * 20L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][6] = Long.valueOf(0L);
            if(w.getCast()[i].isMeek())
                actionWeights[i][7] = Long.valueOf((pain * 200L + sham * 100L + angst * 20L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][7] = Long.valueOf(0L);
            if(w.getCast()[i].isDebased())
                actionWeights[i][8] = Long.valueOf((sham * 200L + disg * 100L + angst * 20L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][8] = Long.valueOf(0L);
            inhibition = (4_000_000L * (long)w.downtimeMultiplier) / 100L;
            if(divided)
                inhibition /= divisor;
            if(!w.getCast()[i].isVVirg())
                actionWeights[i][9] = Long.valueOf((fear * 400L + disg * 200L + angst * 40L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][9] = Long.valueOf(0L);
            if(!w.getCast()[i].isCVirg())
                actionWeights[i][10] = Long.valueOf((disg * 400L + pain * 200L + angst * 40L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][10] = Long.valueOf(0L);
            if(!w.getCast()[i].isAVirg())
                actionWeights[i][11] = Long.valueOf((pain * 400L + sham * 200L + angst * 40L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][11] = Long.valueOf(0L);
            if(!w.getCast()[i].isModest())
                actionWeights[i][12] = Long.valueOf((sham * 400L + fear * 200L + angst * 40L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][12] = Long.valueOf(0L);
            inhibition = (10_000_000_000L * (long)w.downtimeMultiplier) / 100L;
            if(divided)
                inhibition /= divisor;
            if(w.getCast()[i].timesSlaughtered() > 0)
                actionWeights[i][13] = Long.valueOf((fear * 1000L + pain * 500L + disg * 250L + angst * 100L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][13] = Long.valueOf(0L);
            if(w.getCast()[i].timesFantasized() > 0)
                actionWeights[i][14] = Long.valueOf((disg * 1000L + sham * 500L + fear * 250L + angst * 100L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][14] = Long.valueOf(0L);
            if(w.getCast()[i].timesDetonated() > 0)
                actionWeights[i][15] = Long.valueOf((pain * 1000L + disg * 500L + sham * 250L + angst * 100L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][15] = Long.valueOf(0L);
            if(w.getCast()[i].timesStripped() > 0)
                actionWeights[i][16] = Long.valueOf((sham * 1000L + fear * 500L + pain * 250L + angst * 100L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][16] = Long.valueOf(0L);
            inhibition = (200_000_000_000_000L * (long)w.downtimeMultiplier) / 100L;
            if(divided)
                inhibition /= divisor;
            if(w.getCast()[i].isImpregnated())
                actionWeights[i][17] = Long.valueOf((fear * 2000L + pain * 1000L + sham * 500L + angst * 250L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][17] = Long.valueOf(0L);
            if(w.getCast()[i].isHypnotized())
                actionWeights[i][18] = Long.valueOf((disg * 2000L + fear * 1000L + pain * 500L + angst * 250L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][18] = Long.valueOf(0L);
            if(w.getCast()[i].isDrained())
                actionWeights[i][19] = Long.valueOf((pain * 2000L + sham * 1000L + disg * 500L + angst * 250L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][19] = Long.valueOf(0L);
            if(w.getCast()[i].isParasitized())
                actionWeights[i][20] = Long.valueOf((sham * 2000L + disg * 1000L + fear * 500L + angst * 250L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][20] = Long.valueOf(0L);
            if(w.getCast()[i].betraying && w.getCast()[i].temptReq < 100_000L)
            {
                actionWeights[i][21] = Long.valueOf(fear * 5L + disg * 5L + pain * 5L + sham * 5L + angst / 2L);
                if(divided)
                    actionWeights[i][21] = Long.valueOf(actionWeights[i][21].longValue() / divisor);
            } else
            {
                actionWeights[i][21] = Long.valueOf(0L);
            }
            if(w.getCast()[i].dissociated && w.getCast()[i].dissociationReq < 10)
            {
                for(int j = 0; j < 22; j++)
                    actionWeights[i][j] = Long.valueOf(0L);

                actionWeights[i][22] = Long.valueOf(1000L);
            } else
            {
                actionWeights[i][22] = Long.valueOf(0L);
            }
            w.getCast()[i].betraying = false;
            w.getCast()[i].dissociated = false;
            long highestWeight = 0L;
            for(int j = 0; j < actionWeights[i].length; j++)
                if(actionWeights[i][j].compareTo(Long.valueOf(highestWeight)) > 0)
                {
                    highestWeight = actionWeights[i][j].longValue();
                    chosenAction[i] = j;
                } else
                if(actionWeights[i][j].longValue() < 0L)
                    actionWeights[i][j] = Long.valueOf(0L);

        }

        long combinedWeights[][] = new long[3][totalActions];
        for(int i = 0; i <= lastChosen; i++)
        {
            for(int j = 0; j < totalActions; j++)
            {
                combinedWeights[i][j] = actionWeights[i][j].longValue();
                for(int k = 0; k <= lastChosen; k++)
                    if(i != k)
                    {
                        combinedWeights[i][j] = (combinedWeights[i][j] * (long)(200 + w.getCast()[i].getInnocence())) / 200L;
                        combinedWeights[i][j] = ((((actionWeights[k][chosenAction[k]].longValue() + actionWeights[k][j].longValue()) * 100L) / actionWeights[k][chosenAction[k]].longValue()) * combinedWeights[i][j]) / 100L;
                        combinedWeights[i][j] = (combinedWeights[i][j] * (long)(8 + w.getRelationship(i, k))) / 8L;
                        long addedWeight = combinedWeights[i][j] - actionWeights[i][j].longValue();
                        if(addedWeight > 0L && w.getCast()[i].getANGST() > w.getCast()[k].getANGST())
                        {
                            addedWeight = (((w.getCast()[k].getANGST() * 100L) / w.getCast()[i].getANGST()) * addedWeight) / 100L;
                            combinedWeights[i][j] = actionWeights[i][j].longValue() + addedWeight;
                        }
                    }

            }

        }

        long totalWeights[] = new long[totalActions];
        int testOrder[] = new int[totalActions];
        for(int i = 0; i < totalActions; i++)
        {
            totalWeights[i] = 0L;
            testOrder[i] = i;
            for(int j = 0; j <= lastChosen; j++)
                if(combinedWeights[j][i] >= actionWeights[j][chosenAction[j]].longValue())
                    totalWeights[i] += combinedWeights[j][i];

        }

        for(Boolean sorted = false; !sorted;)
        {
            sorted = true;
            for(int i = 0; i < totalActions - 1; i++)
                if(totalWeights[i] < totalWeights[i + 1])
                {
                    long storage = totalWeights[i];
                    totalWeights[i] = totalWeights[i + 1];
                    totalWeights[i + 1] = storage;
                    int storageTwo = testOrder[i];
                    testOrder[i] = testOrder[i + 1];
                    testOrder[i + 1] = storageTwo;
                    sorted = false;
                }

        }

        Boolean doubleFound = false;
        for(int i = 0; i < totalActions; i++)
        {
            int matches = 0;
            Boolean matching[] = {
                false, false, false
            };
            if(w.getCast()[0] != null && combinedWeights[0][testOrder[i]] > actionWeights[0][chosenAction[0]].longValue())
            {
                matches++;
                matching[0] = true;
            }
            if(w.getCast()[1] != null && combinedWeights[1][testOrder[i]] > actionWeights[1][chosenAction[1]].longValue())
            {
                matches++;
                matching[1] = true;
            }
            if(w.getCast()[2] != null && combinedWeights[2][testOrder[i]] > actionWeights[2][chosenAction[2]].longValue())
            {
                matches++;
                matching[2] = true;
            }
            if(matches > 2)
            {
                chosenAction[0] = testOrder[i];
                chosenAction[1] = testOrder[i];
                chosenAction[2] = testOrder[i];
                i = totalActions;
            } else
            if(!doubleFound && matches > 1)
            {
                for(int j = 0; j < 3; j++)
                    if(matching[j])
                        chosenAction[j] = testOrder[i];

                doubleFound = true;
            }
        }

        Boolean singleAction = true;
        if(w.loopComplete)
        {
            singleAction = false;
            if(w.getHarem().length > 0)
                ForsakenDowntime(t, p, f, w, w.save, exhausted);
            else
                Shop(t, p, f, w);
        } else
        if(chosenAction[0] == chosenAction[1] && chosenAction[0] == chosenAction[2])
            w.getCast()[0].TripleDowntime(t, p, f, w, w.getCast()[1], w.getCast()[2], chosenAction[0]);
        else
        if(chosenAction[0] == chosenAction[1])
        {
            w.getCast()[0].DoubleDowntime(t, p, f, w, w.getCast()[1], chosenAction[0]);
            if(w.getCast()[2] != null)
            {
                singleAction = false;
                p.removeAll();
                JButton Continue = new JButton("Continue");
                Continue.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                        w.getCast()[2].SingleDowntime(t, p, f, w, chosenAction[2]);
                        p.removeAll();
                        JButton Continue2 = new JButton("Continue");
                        Continue2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                if(w.save != null && w.getHarem() != null && w.getHarem().length > 0)
                                    Project.ForsakenDowntime(t, p, f, w, w.save, exhausted);
                                else
                                    Project.Shop(t, p, f, w);
                            }
                        });
                        p.add(Continue2);
                        p.validate();
                        p.repaint();
                    }
                });
                p.add(Continue);
                p.validate();
                p.repaint();
            }
        } else
        if(chosenAction[0] == chosenAction[2])
        {
            w.getCast()[0].DoubleDowntime(t, p, f, w, w.getCast()[2], chosenAction[0]);
            singleAction = false;
            p.removeAll();
            JButton Continue = new JButton("Continue");
            Continue.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                    w.getCast()[1].SingleDowntime(t, p, f, w, chosenAction[1]);
                    p.removeAll();
                    JButton Continue2 = new JButton("Continue");
                    Continue2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            if(w.save != null && w.getHarem() != null && w.getHarem().length > 0)
                                Project.ForsakenDowntime(t, p, f, w, w.save, exhausted);
                            else
                                Project.Shop(t, p, f, w);
                        }
                    });
                    p.add(Continue2);
                    p.validate();
                    p.repaint();
                }
            });
            p.add(Continue);
            p.validate();
            p.repaint();
        } else
        if(chosenAction[1] == chosenAction[2] && chosenAction[1] >= 0)
        {
            w.getCast()[0].SingleDowntime(t, p, f, w, chosenAction[0]);
            singleAction = false;
            p.removeAll();
            JButton Continue = new JButton("Continue");
            Continue.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                    w.getCast()[1].DoubleDowntime(t, p, f, w, w.getCast()[2], chosenAction[1]);
                    p.removeAll();
                    JButton Continue2 = new JButton("Continue");
                    Continue2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            if(w.save != null && w.getHarem() != null && w.getHarem().length > 0)
                                Project.ForsakenDowntime(t, p, f, w, w.save, exhausted);
                            else
                                Project.Shop(t, p, f, w);
                        }
                    });
                    p.add(Continue2);
                    p.validate();
                    p.repaint();
                }
            });
            p.add(Continue);
            p.validate();
            p.repaint();
        } else
        {
            w.getCast()[0].SingleDowntime(t, p, f, w, chosenAction[0]);
            if(w.getCast()[1] != null)
            {
                singleAction = false;
                p.removeAll();
                JButton Continue = new JButton("Continue");
                Continue.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                        w.getCast()[1].SingleDowntime(t, p, f, w, chosenAction[1]);
                        if(w.getCast()[2] != null)
                        {
                            p.removeAll();
                            JButton Continue2 = new JButton("Continue");
                            Continue2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                                    w.getCast()[2].SingleDowntime(t, p, f, w, chosenAction[2]);
                                    p.removeAll();
                                    JButton Continue3 = new JButton("Continue");
                                    Continue3.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e)
                                        {
                                            if(w.save != null && w.getHarem() != null && w.getHarem().length > 0)
                                                Project.ForsakenDowntime(t, p, f, w, w.save, exhausted);
                                            else
                                                Project.Shop(t, p, f, w);
                                        }
                                    });
                                    p.add(Continue3);
                                    p.validate();
                                    p.repaint();
                                }
                            });
                            p.add(Continue2);
                            p.validate();
                            p.repaint();
                        } else
                        {
                            p.removeAll();
                            JButton Continue4 = new JButton("Continue");
                            Continue4.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    if(w.save != null && w.getHarem() != null && w.getHarem().length > 0)
                                        Project.ForsakenDowntime(t, p, f, w, w.save, exhausted);
                                    else
                                        Project.Shop(t, p, f, w);
                                }
                            });
                            p.add(Continue4);
                            p.validate();
                            p.repaint();
                        }
                    }
                });
                p.add(Continue);
                p.validate();
                p.repaint();
            }
        }
        if(singleAction)
        {
            p.removeAll();
            JButton Continue = new JButton("Continue");
            Continue.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(!w.hardMode && w.save != null && w.getHarem() != null && w.getHarem().length > 0)
                        Project.ForsakenDowntime(t, p, f, w, w.save, exhausted);
                    else
                        Project.Shop(t, p, f, w);
                }
            });
            p.add(Continue);
            p.validate();
            p.repaint();
        }
    }

    public static void Shop(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        p.removeAll();
        for(int i = 0; i < 3; i++)
            if(w.getCast()[i] != null)
            {
                if(w.getCast()[i].temptReq < 100_000L)
                    w.getCast()[i].pastTempted = true;
                if(w.getCast()[i].dissociationReq < 10)
                    w.getCast()[i].pastDissociated = true;
            }

        w.active = true;
        String path = Project.class.getProtectionDomain().getCodeSource().getLocation().getPath();
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
        path = path.replaceAll(File.separator + "u0020", File.separator + " ");
        File saveLocation = new File(path + File.separator + "saves.sav");
        if(saveLocation.exists())
        {
            ReadObject robj = new ReadObject();
            w.save = robj.deserializeSaveData(path + File.separator + "saves.sav");
            if(w.save.sceneText == null)
                w.save.organizeScenes(49);
            else
            if(w.save.sceneText.length < 49)
                w.save.organizeScenes(49);
        } else
        {
            w.save = new SaveData();
            if(w.save.sceneText == null)
                w.save.organizeScenes(49);
            else
            if(w.save.sceneText.length < 49)
                w.save.organizeScenes(49);
        }
        if(w.save.harem == null)
            w.save.harem = new Forsaken[0];
        if(t.getText().length() > 0)
            w.append(t, "\n\n" + w.getSeparator() + "\n\n");
        for(int i = 0; i < 3; i++)
            if(w.getCast()[i] != null)
            {
                w.getCast()[i].setTextSize(w.getTextSize());
                w.getCast()[i].world = w;
            }

        if(w.getTextSize() == 0)
            w.switchTextSize();
        if(w.usedForsaken != null && w.save != null && w.getHarem() != null && w.getHarem().length > w.usedForsakenIndex && w.getHarem()[w.usedForsakenIndex].EECost() == w.usedForsaken.EECost())
            w.usedForsaken = w.getHarem()[w.usedForsakenIndex];
        else
        if(w.usedForsaken != null)
        {
            w.evilEnergy += w.usedForsaken.EECost();
            w.usedForsaken = null;
        }
        if(w.campaign)
            w.append(t, String.format("%1$s - ", String.valueOf(w.cityName)));
        clearPortraits();
        if(w.usedForsaken != null)
        {
            String nameDisplay[] = new String[5];
            nameDisplay[3] = w.usedForsaken.mainName;
            if(w.usedForsaken.flavorObedience() < 20)
                changePortrait(w.usedForsaken.gender, w.usedForsaken.type, true, true, w, nameDisplay, 3, Emotion.ANGER, Emotion.NEUTRAL);
            else
            if(w.usedForsaken.flavorObedience() < 40)
                changePortrait(w.usedForsaken.gender, w.usedForsaken.type, true, true, w, nameDisplay, 3, Emotion.ANGER, Emotion.SHAME);
            else
            if(w.usedForsaken.flavorObedience() < 61)
                changePortrait(w.usedForsaken.gender, w.usedForsaken.type, true, true, w, nameDisplay, 3, Emotion.FEAR, Emotion.SHAME);
            else
            if(w.usedForsaken.flavorObedience() < 81)
                changePortrait(w.usedForsaken.gender, w.usedForsaken.type, true, true, w, nameDisplay, 3, Emotion.FOCUS, Emotion.NEUTRAL);
            else
                changePortrait(w.usedForsaken.gender, w.usedForsaken.type, true, true, w, nameDisplay, 3, Emotion.JOY, Emotion.FOCUS);
        }
        w.append(t, String.format("Day %1$s", w.getDay()));
        if(w.clampPercent != 100)
            w.append(t, "\nDamage Mitigation: " + String.valueOf(100 - w.clampPercent) + "% per level");
        if(w.eventOffset != 0)
            w.append(t, "\nPreparedness: Final Battle on Day " + String.valueOf(50 - w.eventOffset * 3));
        if(w.downtimeMultiplier != 100)
            w.append(t, "\nLuxuries: " + w.downtimeMultiplier + "% Trauma resolution speed");
        if(w.types[2] != null)
        {
            int superior = 0;
            for(int j = 0; j < 3; j++)
                if(w.types[j] == Chosen.Species.SUPERIOR)
                    superior++;

            w.append(t, "\nElites: " + superior + " Superior Chosen");
        }
        w.printShopTutorial(t);
        if(w.getCast()[1] != null)
            w.printGroupTutorial(t);
        if((w.getDay() > 50 - w.eventOffset * 3 || w.getEarlyCheat() || w.cheater) && (!w.campaign || w.getEarlyCheat()))
        {
            JButton Cheat = new JButton("Cheat");
            Cheat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(w.isCheater())
                    {
                        Project.Cheat(t, p, f, w);
                    } else
                    {
                        p.removeAll();
                        w.append(t, "\n\n" + w.getSeparator() + "\n\nActivating Cheat Mode will give you unlimited Evil Energy as well as other benefits");
                        if(w.getDay() <= 35 && w.hardMode)
                            w.append(t, ", but you will not receive a score for the playthrough");
                        w.append(t, ".  Activate Cheat Mode?");
                        JButton Activate = new JButton("Activate Cheat Mode");
                        Activate.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                w.setCheater();
                                Project.Cheat(t, p, f, w);
                            }
                        });
                        p.add(Activate);
                        JButton Cancel = new JButton("Cancel");
                        Cancel.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                Project.Shop(t, p, f, w);
                            }
                        });
                        p.add(Cancel);
                        p.validate();
                        p.repaint();
                    }
                }
            });
            p.add(Cheat);
        }
        for(int i = 0; i < w.getTechs().length && !w.loopComplete; i++)
            if(!w.getTechs()[i].isOwned())
            {
                Boolean shown = false;
                for(int j = 0; j < w.getTechs()[i].getPrereqs().length; j++)
                    if(w.getTechs()[i].getPrereqs()[j].isOwned())
                        shown = true;

                if(w.getTechs()[i].getPrereqs().length == 0)
                    shown = true;
                if(shown)
                {
                    w.append(t, "\n\n");
                    w.getTechs()[i].printSummary(w, t);
                    int ownedPrereqs = 0;
                    for(int j = 0; j < w.getTechs()[i].getPrereqs().length; j++)
                        if(w.getTechs()[i].getPrereqs()[j].isOwned())
                            ownedPrereqs++;

                    final int thisTech = i;
                    if(w.getEvilEnergy() >= w.getTechs()[i].getCost() && ownedPrereqs >= w.getTechs()[i].getPrereqReqs())
                    {
                        JButton Buy = new JButton(w.getTechs()[i].getName()) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -30);
                            }

                        };
                        Buy.setToolTipText(w.getTechs()[i].getTooltip());
                        Buy.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                p.removeAll();
                                w.append(t, "\n\n" + w.getSeparator() + "\n\n" + w.getTechs()[thisTech].getName() + " costs " + w.getTechs()[thisTech].getCost() + " Evil Energy.  Will you develop it now?");
                                JButton Confirm = new JButton("Confirm");
                                Confirm.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e)
                                    {
                                        w.getTechs()[thisTech].buy(w);
                                        Project.advanceDowntimeAction(p, w, thisTech);
                                        Project.Shop(t, p, f, w);
                                    }
                                });
                                if(thisTech != 48 || w.getCast()[2] != null)
                                    p.add(Confirm);
                                else
                                if(thisTech == 48)
                                    w.append(t, "  (Forbidden until all three Chosen have been encountered.)");
                                JButton Cancel = new JButton("Cancel");
                                Cancel.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e)
                                    {
                                        Project.Shop(t, p, f, w);
                                    }
                                });
                                p.add(Cancel);
                                p.validate();
                                p.repaint();
                            }
                        });
                        p.add(Buy);
                    }
                }
            }

        if(!w.loopComplete)
            w.append(t, "\n\nYou have " + w.getEvilEnergy() + " Evil Energy.");
        else
        if(w.day <= 50 - 3 * w.eventOffset)
        {
            w.append(t, "\n\n" + String.valueOf(51 - w.day - 3 * w.eventOffset) + " day");
            if(51 - w.day - 3 * w.eventOffset != 1)
                w.append(t, "s remain ");
            else
                w.append(t, " remains ");
            w.append(t, "before your attack on the next city.");
        } else
        {
            w.append(t, "\n\nIt is time to choose your next destination.");
        }
        if(w.newAchievement())
            w.greenAppend(t, "\n\nYou have obtained a new Achievement!  See the Info menu for more details.");
        else
            w.printTip(t);
        JButton Profiles = new JButton("Info");
        Profiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.ShowInformation(t, p, f, w);
            }
        });
        p.add(Profiles);
        if(w.getTechs()[3].isOwned())
        {
            JButton CustomBody = new JButton("Commander");
            CustomBody.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(!w.getBodyStatus()[0] && !w.hardMode && w.save != null && w.getHarem() != null && w.getHarem().length > 0)
                        Project.ForsakenMenu(t, p, f, w, w.save, 0);
                    else
                        Project.Customize(t, p, f, w);
                }
            });
            if(w.loopComplete)
                CustomBody.setText("Forsaken");
            if(w.getHarem().length > 0 || !w.loopComplete)
                p.add(CustomBody);
            else
            if(w.day != 51 - w.eventOffset * 3 || !w.campaign)
            {
                JButton Pass = new JButton("Pass Time");
                Pass.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.PostBattle(t, p, f, w);
                    }
                });
                p.add(Pass);
            }
            if(!w.getBodyStatus()[0] && w.usedForsaken == null && w.getEvilEnergy() > 0 && !w.loopComplete)
                CustomBody.setBackground(Color.YELLOW);
        }
        JButton Data = new JButton("Data");
        Data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.append(t, "\n\n" + w.getSeparator() + "\n\nSelect an option.");
                p.removeAll();
                JButton NewSave = new JButton("New Save File");
                NewSave.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Data(t, p, f, w, "newsave", 0, true);
                    }
                });
                p.add(NewSave);
                JButton Overwrite = new JButton("Overwrite Save");
                Overwrite.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Data(t, p, f, w, "overwrite", 0, true);
                    }
                });
                p.add(Overwrite);
                JButton Load = new JButton("Load");
                Load.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Data(t, p, f, w, "load", 0, true);
                    }
                });
                p.add(Load);
                JButton Delete = new JButton("Delete");
                Delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Data(t, p, f, w, "delete", 0, true);
                    }
                });
                p.add(Delete);
                JButton Import = new JButton("Import");
                Import.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Data(t, p, f, w, "import", 0, true);
                    }
                });
                p.add(Import);
                JButton Export = new JButton("Export");
                Export.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Data(t, p, f, w, "export", 0, true);
                    }
                });
                p.add(Export);
                JButton Back = new JButton("Back");
                Back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Shop(t, p, f, w);
                    }
                });
                p.add(Back);
                p.validate();
                p.repaint();
            }
        });
        p.add(Data);
        JButton Quit = new JButton("Quit");
        Quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.append(t, "\n\n" + w.getSeparator() + "\n\nReally quit?  Current progress will not be saved.");
                p.removeAll();
                JButton ReallyQuit = new JButton("Quit to main menu");
                ReallyQuit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                        WorldState x = new WorldState();
                        x.copySettings(t, w);
                        x.copyToggles(w);
                        x.save = w.save;
                        Project.IntroOne(t, p, f, x);
                    }
                });
                p.add(ReallyQuit);
                JButton Back = new JButton("Back");
                Back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Shop(t, p, f, w);
                    }
                });
                p.add(Back);
                p.validate();
                p.repaint();
            }
        });
        p.add(Quit);
        JButton NextBattle = new JButton("Next Battle");
        NextBattle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(w.getCast()[1] == null)
                    Project.ConfirmBattle(t, p, f, w, w.getCast()[0]);
                else
                    Project.pickStartingTarget(t, p, f, w);
            }
        });
        if(!w.loopComplete)
            p.add(NextBattle);
        else
        if(w.day > 50 - w.eventOffset * 3)
        {
            JButton NextCity = new JButton("Next City");
            NextCity.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.PickNextCity(t, p, f, w);
                }
            });
            p.add(NextCity);
        }
        if(w.writePossible())
            addWriteButton(p, w);
        p.validate();
        p.repaint();
        w.readCommentary(t);
    }

    public static void PickNextCity(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        p.removeAll();
        w.append(t, "\n\n" + w.getSeparator() + "\n\nWhich city will you attack next?");
        if(w.nextCities == null || w.nextCities.length == 0)
        {
            w.nextCities = new WorldState[2];
            for(int i = 0; i < w.nextCities.length; i++)
            {
                w.nextCities[i] = new WorldState();
                w.nextCities[i].campaignRand = w.campaignRand;
                w.nextCities[i].save = w.save;
                w.nextCities[i].copySettings(t, w);
                w.nextCities[i].copyToggles(w);
                w.nextCities[i].setGenders(w.nextCities[i].getGenderBalance());
                w.nextCities[i].active = true;
                w.nextCities[i].campaign = true;
                w.nextCities[i].loops = w.loops + 1;
                w.nextCities[i].cityName = w.nextCities[i].getCityName(w.loops * 2 + i + 1);
                w.nextCities[i].earlyCheat = w.earlyCheat;
                w.nextCities[i].hardMode = false;
                w.nextCities[i].eventOffset = 0;
                w.nextCities[i].clampStart = 11;
                w.nextCities[i].clampPercent = 100;
                w.nextCities[i].downtimeMultiplier = 100;
                int difficultyScore = 0;
                int clampRemoval = 0;
                while(difficultyScore < w.nextCities[i].loops * 10) 
                {
                    double difficultyType = w.nextCities[i].campaignRand.nextDouble();
                    if(difficultyType < 0.30555555555555558D && clampRemoval < 1660)
                    {
                        w.nextCities[i].clampStart = 1;
                        int increase = (int)(w.nextCities[i].campaignRand.nextDouble() * 5D) + 1;
                        if(increase > w.nextCities[i].loops * 10 - difficultyScore)
                            increase = w.nextCities[i].loops * 10 - difficultyScore;
                        if(!w.earlyCheat)
                            clampRemoval += increase * 15;
                        difficultyScore += increase;
                    } else
                    if(difficultyType < 0.61111111111111116D)
                    {
                        if(difficultyScore <= w.nextCities[i].loops * 10 - 3 && w.nextCities[i].eventOffset < 10)
                        {
                            if(!w.earlyCheat)
                                w.nextCities[i].eventOffset++;
                            difficultyScore += 3;
                        }
                    } else
                    if(difficultyType < 0.91666666666666663D)
                    {
                        int increase = (int)(w.nextCities[i].campaignRand.nextDouble() * 5D) + 1;
                        if(increase > w.nextCities[i].loops * 10 - difficultyScore)
                            increase = w.nextCities[i].loops * 10 - difficultyScore;
                        difficultyScore += increase;
                        for(; increase > 0 && !w.earlyCheat; increase--)
                            w.nextCities[i].downtimeMultiplier = (w.nextCities[i].downtimeMultiplier * 11) / 10;

                    } else
                    if(difficultyType < 1.0D && w.nextCities[i].loops * 10 - difficultyScore >= 11 && w.nextCities[i].types[0] == null)
                    {
                        if(w.nextCities[i].types[2] == null)
                            w.nextCities[i].types[2] = Chosen.Species.SUPERIOR;
                        else
                        if(w.nextCities[i].types[1] == null)
                            w.nextCities[i].types[1] = Chosen.Species.SUPERIOR;
                        else
                            w.nextCities[i].types[0] = Chosen.Species.SUPERIOR;
                        difficultyScore += 11;
                    }
                }
                for(; clampRemoval > 0; clampRemoval -= 10 + (100 - w.clampPercent) / 5)
                    w.nextCities[i].clampPercent--;

                w.nextCities[i].conquered = w.conquered;
                w.nextCities[i].sacrificed = w.sacrificed;
                w.nextCities[i].returning = w.returning;
                w.nextCities[i].deceased = w.deceased;
                w.nextCities[i].formerChosen = w.formerChosen;
                w.nextCities[i].campaignCustom = w.campaignCustom;
                if(i == 1 && !w.earlyCheat)
                {
                    int differences = 0;
                    int requirement = (int)Math.sqrt(w.nextCities[0].loops * 5);
                    differences += Math.abs(w.nextCities[0].clampPercent - w.nextCities[1].clampPercent);
                    differences += Math.abs((w.nextCities[0].eventOffset - w.nextCities[1].eventOffset) * 3);
                    int higher = w.nextCities[0].downtimeMultiplier;
                    int lower = w.nextCities[1].downtimeMultiplier;
                    if(lower > higher)
                    {
                        int storage = lower;
                        lower = higher;
                        higher = storage;
                    }
                    while(higher > lower) 
                    {
                        higher = (higher * 10) / 11;
                        differences++;
                    }
                    Boolean sameElites = true;
                    Boolean noElites = true;
                    for(int j = 0; j < 3; j++)
                    {
                        if(w.nextCities[0].types[j] != w.nextCities[1].types[j] && (w.nextCities[0].types[j] == null || w.nextCities[1].types[j] == null))
                        {
                            sameElites = false;
                            differences += 11;
                        }
                        if(w.nextCities[0].types[j] != null || w.nextCities[1].types[j] != null)
                            noElites = false;
                    }

                    if(differences < requirement || sameElites && !noElites)
                        i = -1;
                }
            }

        }
        for(int i = 0; i < w.nextCities.length; i++)
        {
            w.append(t, "\n\n");
            w.underlineAppend(t, w.nextCities[i].cityName);
            if(w.nextCities[i].clampPercent != 100)
                w.append(t, "\nDamage Mitigation: " + String.valueOf(100 - w.nextCities[i].clampPercent) + "% per level");
            if(w.nextCities[i].eventOffset != 0)
                w.append(t, "\nPreparedness: Final Battle on Day " + String.valueOf(50 - w.nextCities[i].eventOffset * 3));
            if(w.nextCities[i].downtimeMultiplier != 100)
                w.append(t, "\nLuxuries: " + w.nextCities[i].downtimeMultiplier + "% Trauma resolution speed");
            if(w.nextCities[i].types[2] != null)
            {
                int superior = 0;
                for(int j = 0; j < 3; j++)
                    if(w.nextCities[i].types[j] == Chosen.Species.SUPERIOR)
                        superior++;

                w.append(t, "\nElites: " + superior + " Superior Chosen");
            }
            JButton ThisOne = new JButton(w.nextCities[i].cityName);
            final WorldState pickedWorld = w.nextCities[i];
            ThisOne.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    p.removeAll();
                    w.append(t, "\n\n" + w.getSeparator() + "\n\n" + pickedWorld.cityName + " will be targeted.  Are you sure?");
                    JButton Confirm = new JButton("Confirm");
                    Confirm.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            pickedWorld.save = w.save;
                            pickedWorld.initialize();
                            Chosen newChosen = new Chosen();
                            newChosen.setNumber(0);
                            newChosen.generate(pickedWorld);
                            pickedWorld.addChosen(newChosen);
                            pickedWorld.achievementSeen = w.achievementSeen;
                            pickedWorld.evilEnergy = w.achievementHeld(0)[0];
                            Project.Shop(t, p, f, pickedWorld);
                        }
                    });
                    p.add(Confirm);
                    JButton Cancel = new JButton("Cancel");
                    Cancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            Project.PickNextCity(t, p, f, w);
                        }
                    });
                    p.add(Cancel);
                    p.validate();
                    p.repaint();
                }
            });
            p.add(ThisOne);
        }

        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.Shop(t, p, f, w);
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void ShowInformation(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        p.removeAll();
        w.append(t, "\n\n" + w.getSeparator());
        if(w.getTechs()[0].isOwned() && !w.loopComplete)
        {
            w.append(t, "\n\nOverall corruption progress:");
            int longest = 3;
            for(int i = 0; i < 3; i++)
                if(w.getCast()[i] != null && w.getCast()[i].getMainName().length() > longest)
                    longest = w.getCast()[i].getMainName().length();

            for(int i = 0; i < 3; i++)
                if(w.getCast()[i] != null)
                {
                    w.append(t, "\n\n" + w.getCast()[i].getMainName());
                    for(int j = w.getCast()[i].getMainName().length(); j < longest; j++)
                        w.append(t, " ");

                    String gap = "";
                    for(int j = 0; j < longest - 3; j++)
                        gap = (new StringBuilder(String.valueOf(gap))).append(" ").toString();

                    w.append(t, "  +2 T1 T2 T3 T4\nMOR" + gap + " ");
                    if(w.getCast()[i].getMorality() > 66)
                    {
                        w.append(t, "[");
                        if(!w.getCast()[i].bonusHATE)
                            w.append(t, "X");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                    } else
                    {
                        w.append(t, "   [");
                    }
                    if(w.getCast()[i].temptReq == 100_000L)
                    {
                        if(w.getCast()[i].isRuthless())
                            w.append(t, "X");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                        if(!w.getCast()[i].isVVirg())
                            w.append(t, "X");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                        if(w.getCast()[i].timesSlaughtered() > 0)
                            w.append(t, "X");
                        else
                        if(w.getCast()[i].usingSlaughter)
                            w.append(t, "/");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                        if(w.getCast()[i].isImpregnated())
                            w.append(t, "X");
                        else
                        if(w.getCast()[i].getImpregnationEffectiveness() >= w.getCast()[i].impregnationReq())
                            w.append(t, "/");
                        else
                            w.append(t, " ");
                    } else
                    {
                        w.append(t, "~][~][~][~");
                    }
                    w.append(t, "]");
                    if(w.getCast()[i].getImpregnationEffectiveness() > 100)
                    {
                        if(w.getCast()[i].getImpregnationEffectiveness() < 1000)
                            w.append(t, " ");
                        w.append(t, String.format(" %1$s", w.getCast()[i].getImpregnationEffectiveness()) + "%");
                    }
                    w.append(t, "\nINN" + gap + " ");
                    if(w.getCast()[i].getInnocence() > 66)
                    {
                        w.append(t, "[");
                        if(!w.getCast()[i].bonusPLEA)
                            w.append(t, "X");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                    } else
                    {
                        w.append(t, "   [");
                    }
                    if(w.getCast()[i].dissociationReq == 10)
                    {
                        if(w.getCast()[i].isLustful())
                            w.append(t, "X");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                        if(!w.getCast()[i].isCVirg())
                            w.append(t, "X");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                        if(w.getCast()[i].timesFantasized() > 0)
                            w.append(t, "X");
                        else
                        if(w.getCast()[i].usingFantasize)
                            w.append(t, "/");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                        if(w.getCast()[i].isHypnotized())
                            w.append(t, "X");
                        else
                        if(w.getCast()[i].getHypnosisEffectiveness() >= w.getCast()[i].hypnosisReq())
                            w.append(t, "/");
                        else
                            w.append(t, " ");
                    } else
                    {
                        w.append(t, "~][~][~][~");
                    }
                    w.append(t, "]");
                    if(w.getCast()[i].getHypnosisEffectiveness() > 100)
                    {
                        if(w.getCast()[i].getHypnosisEffectiveness() < 1000)
                            w.append(t, " ");
                        w.append(t, String.format(" %1$s", w.getCast()[i].getHypnosisEffectiveness()) + "%");
                    }
                    w.append(t, "\nCON" + gap + " ");
                    if(w.getCast()[i].getConfidence() > 66)
                    {
                        w.append(t, "[");
                        if(!w.getCast()[i].bonusINJU)
                            w.append(t, "X");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                    } else
                    {
                        w.append(t, "   [");
                    }
                    if(w.getCast()[i].isMeek())
                        w.append(t, "X");
                    else
                        w.append(t, " ");
                    w.append(t, "][");
                    if(w.getCast()[i].temptReq == 100_000L)
                    {
                        if(!w.getCast()[i].isAVirg())
                            w.append(t, "X");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                        if(w.getCast()[i].timesDetonated() > 0)
                            w.append(t, "X");
                        else
                        if(w.getCast()[i].usingDetonate)
                            w.append(t, "/");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                        if(w.getCast()[i].isDrained())
                            w.append(t, "X");
                        else
                        if(w.getCast()[i].getDrainEffectiveness() >= w.getCast()[i].drainReq())
                            w.append(t, "/");
                        else
                            w.append(t, " ");
                    } else
                    {
                        w.append(t, "~][~][~");
                    }
                    w.append(t, "]");
                    if(w.getCast()[i].getDrainEffectiveness() > 100)
                    {
                        if(w.getCast()[i].getDrainEffectiveness() < 1000)
                            w.append(t, " ");
                        w.append(t, String.format(" %1$s", w.getCast()[i].getDrainEffectiveness()) + "%");
                    }
                    w.append(t, "\nDIG" + gap + " ");
                    if(w.getCast()[i].getDignity() > 66)
                    {
                        w.append(t, "[");
                        if(!w.getCast()[i].bonusEXPO)
                            w.append(t, "X");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                    } else
                    {
                        w.append(t, "   [");
                    }
                    if(w.getCast()[i].isDebased())
                        w.append(t, "X");
                    else
                        w.append(t, " ");
                    w.append(t, "][");
                    if(w.getCast()[i].dissociationReq == 10)
                    {
                        if(!w.getCast()[i].isModest())
                            w.append(t, "X");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                        if(w.getCast()[i].timesStripped() > 0)
                            w.append(t, "X");
                        else
                        if(w.getCast()[i].usingStrip)
                            w.append(t, "/");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                        if(w.getCast()[i].isParasitized())
                            w.append(t, "X");
                        else
                        if(w.getCast()[i].getParasitismEffectiveness() >= w.getCast()[i].parasitismReq())
                            w.append(t, "/");
                        else
                            w.append(t, " ");
                    } else
                    {
                        w.append(t, "~][~][~");
                    }
                    w.append(t, "]");
                    if(w.getCast()[i].getParasitismEffectiveness() > 100)
                    {
                        if(w.getCast()[i].getParasitismEffectiveness() < 1000)
                            w.append(t, " ");
                        w.append(t, String.format(" %1$s", w.getCast()[i].getParasitismEffectiveness()) + "%");
                    }
                }

            for(int i = 0; i < 3 && !w.loopComplete; i++)
                if(w.getCast()[i] != null)
                {
                    final int thisChosen = i;
                    JButton openProfile = new JButton(w.getCast()[i].getMainName() + "'s Profile");
                    openProfile.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            p.removeAll();
                            Project.clearPortraits();
                            String as[] = new String[5];
                            as[0] = w.getCast()[thisChosen].mainName;
                            Project.changePortrait(w.getCast()[thisChosen].convertGender(), w.getCast()[thisChosen].type, false, false, w, as, 0, Emotion.NEUTRAL, Emotion.NEUTRAL);
                            w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                            w.getCast()[thisChosen].printIntro(t, w);
                            w.getCast()[thisChosen].printProfile(t, p, f, w);
                            JButton Continue = new JButton("Continue");
                            Continue.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.ShowInformation(t, p, f, w);
                                }
                            });
                            p.add(Continue);
                            p.validate();
                            p.repaint();
                        }
                    });
                    p.add(openProfile);
                }

        }
        w.append(t, "\n\nWhich information do you want to view?");
        JButton Statistics = new JButton("Statistics");
        Statistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int highest = w.getCast()[0].getMainName().length();
                if(highest < 3)
                    highest = 3;
                if(w.getCast()[1] != null && w.getCast()[1].getMainName().length() > highest)
                    highest = w.getCast()[1].getMainName().length();
                if(w.getCast()[2] != null && w.getCast()[2].getMainName().length() > highest)
                    highest = w.getCast()[2].getMainName().length();
                String names[] = {
                    "", "", ""
                };
                for(names[0] = w.getCast()[0].getMainName(); names[0].length() < highest; names[0] = String.valueOf(names[0]) + " ");
                if(w.getCast()[1] != null)
                    for(names[1] = w.getCast()[1].getMainName(); names[1].length() < highest; names[1] = String.valueOf(names[1]) + " ");
                if(w.getCast()[2] != null)
                    for(names[2] = w.getCast()[2].getMainName(); names[2].length() < highest; names[2] = String.valueOf(names[2]) + " ");
                String totals;
                for(totals = "All"; totals.length() < highest; totals = String.valueOf(totals) + " ");
                w.append(t, "\n\n" + w.getSeparator() + "\n\nOpening Levels Taken:\n\n");
                for(int spaces = highest; spaces > 0; spaces--)
                    w.append(t, " ");

                if(w.tickle())
                    w.append(t, "  FEAR  DISG  TICK  SHAM Total");
                else
                    w.append(t, "  FEAR  DISG  PAIN  SHAM Total");
                int totalFEAR = 0;
                int totalDISG = 0;
                int totalPAIN = 0;
                int totalSHAM = 0;
                for(int i = 0; i < 3; i++)
                    if(w.getCast()[i] != null)
                    {
                        w.append(t, "\n" + names[i] + " " + w.getCast()[i].fixedFormat(w.getCast()[i].getFEARopenings()) + " " + w.getCast()[i].fixedFormat(w.getCast()[i].getDISGopenings()) + " " + w.getCast()[i].fixedFormat(w.getCast()[i].getPAINopenings()) + " " + w.getCast()[i].fixedFormat(w.getCast()[i].getSHAMopenings()) + " " + w.getCast()[i].fixedFormat(w.getCast()[i].getFEARopenings() + w.getCast()[i].getDISGopenings() + w.getCast()[i].getPAINopenings() + w.getCast()[i].getSHAMopenings()));
                        totalFEAR += w.getCast()[i].getFEARopenings();
                        totalDISG += w.getCast()[i].getDISGopenings();
                        totalPAIN += w.getCast()[i].getPAINopenings();
                        totalSHAM += w.getCast()[i].getSHAMopenings();
                    }

                w.append(t, "\n" + totals + " " + w.getCast()[0].fixedFormat(totalFEAR) + " " + w.getCast()[0].fixedFormat(totalDISG) + " " + w.getCast()[0].fixedFormat(totalPAIN) + " " + w.getCast()[0].fixedFormat(totalSHAM) + " " + w.getCast()[0].fixedFormat(totalFEAR + totalDISG + totalPAIN + totalSHAM));
            }
        });
        p.add(Statistics);
        JButton Upgrades = new JButton("View All Upgrades");
        Upgrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.append(t, "\n\n" + w.getSeparator() + "\n\nSelect an upgrade to view.");
                Project.ViewUpgrades(t, p, f, w, 0);
            }
        });
        p.add(Upgrades);
        JButton Achievements = new JButton("Achievements");
        Achievements.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.ViewAchievements(t, p, f, w, 0);
            }
        });
        if(w.campaign)
            p.add(Achievements);
        if(w.newAchievement())
            Achievements.setBackground(new Color(255, 225, 125));
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.Shop(t, p, f, w);
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void ViewAchievements(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final int page)
    {
        p.removeAll();
        w.append(t, "\n\n" + w.getSeparator());
        if(page > 0)
        {
            JButton PreviousPage = new JButton("Previous Page");
            PreviousPage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.ViewAchievements(t, p, f, w, page - 1);
                }
            });
            p.add(PreviousPage);
        }
        for(int i = page * 5; i < page * 5 + 5 && i < w.achievementSeen.length; i++)
        {
            w.append(t, "\n\n");
            String description = "";
            if(i == 0)
            {
                w.underlineAppend(t, "Residual Energy");
                description = (new StringBuilder(description)).append("Forsaken Sacrificed: ").append(w.achievementHeld(0)[1]).append("\n").toString();
                description = (new StringBuilder(description)).append("Level: ").append(w.achievementHeld(0)[0]).toString();
                if(w.achievementHeld(0)[0] == 0)
                    description = (new StringBuilder(description)).append(" (Next: 1 sacrifice)\nBonus: N/A").toString();
                else
                if(w.achievementHeld(0)[0] == 1)
                    description = (new StringBuilder(description)).append(" (Next: 3 sacrifices)\nBonus: +1 Starting EE").toString();
                else
                if(w.achievementHeld(0)[0] == 2)
                    description = (new StringBuilder(description)).append(" (Next: 6 sacrifices)\nBonus: +2 Starting EE").toString();
                else
                if(w.achievementHeld(0)[0] == 3)
                    description = (new StringBuilder(description)).append(" (Next: 15 sacrifices)\nBonus: +3 Starting EE").toString();
                else
                if(w.achievementHeld(0)[0] == 4)
                    description = (new StringBuilder(description)).append(" (Next: 60 sacrifices)\nBonus: +4 Starting EE").toString();
                else
                    description = (new StringBuilder(description)).append("\nBonus: +5 Starting EE").toString();
                description = (new StringBuilder(description)).append("\nThe supernaturally-enhanced bodies of former Chosen make for excellent breeding stock.  This role prevents them from fighting in battle, but it can give you a head start in establishing new bases of operations.  And they tend to start enjoying it before too long.").toString();
            } else
            if(i == 1)
            {
                w.underlineAppend(t, "Impregnation Specialty");
                description = (new StringBuilder(description)).append("Chosen Impregnated: ").append(w.achievementHeld(i)[1]).append("\n").toString();
                description = (new StringBuilder(description)).append("Level: ").append(w.achievementHeld(i)[0]).toString();
                if(w.achievementHeld(i)[0] == 0)
                    description = (new StringBuilder(description)).append(" (Next: 4 impregnated)\nBonus: N/A").toString();
                else
                if(w.achievementHeld(i)[0] == 1)
                    description = (new StringBuilder(description)).append(" (Next: 10 impregnated)\nBonus: -200% Impregnation Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 2)
                    description = (new StringBuilder(description)).append(" (Next: 25 impregnated)\nBonus: -400% Impregnation Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 3)
                    description = (new StringBuilder(description)).append(" (Next: 60 impregnated)\nBonus: -600% Impregnation Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 4)
                    description = (new StringBuilder(description)).append(" (Next: 160 impregnated)\nBonus: -700% Impregnation Threshold").toString();
                else
                    description = (new StringBuilder(description)).append("\nBonus: -750% Impregnation Threshold").toString();
                description = (new StringBuilder(description)).append("\nAs the Chosen hear rumors that you're able to impregnate even them, their lack of faith in their own protections will cause it to become even easier to do so.").toString();
            } else
            if(i == 2)
            {
                w.underlineAppend(t, "Hypnosis Specialty");
                description = (new StringBuilder(description)).append("Chosen Hypnotized: ").append(w.achievementHeld(i)[1]).append("\n").toString();
                description = (new StringBuilder(description)).append("Level: ").append(w.achievementHeld(i)[0]).toString();
                if(w.achievementHeld(i)[0] == 0)
                    description = (new StringBuilder(description)).append(" (Next: 4 hypnotized)\nBonus: N/A").toString();
                else
                if(w.achievementHeld(i)[0] == 1)
                    description = (new StringBuilder(description)).append(" (Next: 10 hypnotized)\nBonus: -200% Hypnosis Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 2)
                    description = (new StringBuilder(description)).append(" (Next: 25 hypnotized)\nBonus: -400% Hypnosis Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 3)
                    description = (new StringBuilder(description)).append(" (Next: 60 hypnotized)\nBonus: -600% Hypnosis Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 4)
                    description = (new StringBuilder(description)).append(" (Next: 160 hypnotized)\nBonus: -700% Hypnosis Threshold").toString();
                else
                    description = (new StringBuilder(description)).append("\nBonus: -750% Hypnosis Threshold").toString();
                description = (new StringBuilder(description)).append("\nMuch of the difficulty in Demonic Hypnosis comes from finding exploitable weaknesses in the target's thought process.  But all human minds share some similarities, and the more you break, the more tricks you figure out.").toString();
            } else
            if(i == 3)
            {
                w.underlineAppend(t, "Drain Specialty");
                description = (new StringBuilder(description)).append("Chosen Drained: ").append(w.achievementHeld(i)[1]).append("\n").toString();
                description = (new StringBuilder(description)).append("Level: ").append(w.achievementHeld(i)[0]).toString();
                if(w.achievementHeld(i)[0] == 0)
                    description = (new StringBuilder(description)).append(" (Next: 4 drained)\nBonus: N/A").toString();
                else
                if(w.achievementHeld(i)[0] == 1)
                    description = (new StringBuilder(description)).append(" (Next: 10 drained)\nBonus: -200% Drain Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 2)
                    description = (new StringBuilder(description)).append(" (Next: 25 drained)\nBonus: -400% Drain Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 3)
                    description = (new StringBuilder(description)).append(" (Next: 60 drained)\nBonus: -600% Drain Threshold\n").toString();
                else
                if(w.achievementHeld(i)[0] == 4)
                    description = (new StringBuilder(description)).append(" (Next: 160 drained)\nBonus: -700% Drain Threshold").toString();
                else
                    description = (new StringBuilder(description)).append("\nBonus: -750% Drain Threshold").toString();
                description = (new StringBuilder(description)).append("\nThe Holy Energy which empowers the Chosen is inherently difficult for a Demon to absorb, but whenever you do successfully begin draining energy from one of the Chosen, her aura mingles with your own, and you find it easier to draw more of their energy into yourself.").toString();
            } else
            if(i == 4)
            {
                w.underlineAppend(t, "Parasitism Specialty");
                description = (new StringBuilder(description)).append("Chosen Parasitized: ").append(w.achievementHeld(i)[1]).append("\n").toString();
                description = (new StringBuilder(description)).append("Level: ").append(w.achievementHeld(i)[0]).toString();
                if(w.achievementHeld(i)[0] == 0)
                    description = (new StringBuilder(description)).append(" (Next: 4 parasitized)\nBonus: N/A").toString();
                else
                if(w.achievementHeld(i)[0] == 1)
                    description = (new StringBuilder(description)).append(" (Next: 10 parasitized)\nBonus: -200% Parasitism Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 2)
                    description = (new StringBuilder(description)).append(" (Next: 25 parasitized)\nBonus: -400% Parasitism Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 3)
                    description = (new StringBuilder(description)).append(" (Next: 60 parasitized)\nBonus: -600% Parasitism Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 4)
                    description = (new StringBuilder(description)).append(" (Next: 160 parasitized)\nBonus: -700% Parasitism Threshold").toString();
                else
                    description = (new StringBuilder(description)).append("\nBonus: -750% Parasitism Threshold").toString();
                description = (new StringBuilder(description)).append("\nThe public loves to see the Chosen humiliated, and as it becomes more common for their transformations to become corrupted by you, everyone's anticipation for the next such corruption will do much of the work for you.").toString();
            } else
            if(i == 5)
            {
                w.underlineAppend(t, "Tempting");
                description = (new StringBuilder(description)).append("Chosen Tempted: ").append(w.achievementHeld(i)[1]).append("\n").toString();
                description = (new StringBuilder(description)).append("Level: ").append(w.achievementHeld(i)[0]).toString();
                if(w.achievementHeld(i)[0] == 0)
                    description = (new StringBuilder(description)).append(" (Next: 2 Tempted)\nBonus: N/A").toString();
                else
                if(w.achievementHeld(i)[0] == 1)
                    description = (new StringBuilder(description)).append(" (Next: 5 Tempted)\nBonus: Tempt requirement decreases 15% per use").toString();
                else
                if(w.achievementHeld(i)[0] == 2)
                    description = (new StringBuilder(description)).append(" (Next: 12 Tempted)\nBonus: Tempt requirement decreases 20% per use").toString();
                else
                if(w.achievementHeld(i)[0] == 3)
                    description = (new StringBuilder(description)).append(" (Next: 30 Tempted)\nBonus: Tempt requirement decreases 25% per use").toString();
                else
                if(w.achievementHeld(i)[0] == 4)
                    description = (new StringBuilder(description)).append(" (Next: 80 Tempted)\nBonus: Tempt requirement decreases 30% per use").toString();
                else
                    description = (new StringBuilder(description)).append("\nBonus: Tempt requirement decreases 35% per use").toString();
                description = (new StringBuilder(description)).append("\nThe Chosen are carefully guided by their handlers and by society at large so that they don't even consider the possibility of turning to the side of the Demons.  But the more they see other Chosen being treated kindly by the Thralls, the more willing they'll be to think of you as a potential ally.").toString();
            } else
            if(i == 6)
            {
                w.underlineAppend(t, "Mindbreaker");
                description = (new StringBuilder(description)).append("Chosen with Aversion: ").append(w.achievementHeld(i)[1]).append("\n").toString();
                description = (new StringBuilder(description)).append("Level: ").append(w.achievementHeld(i)[0]).toString();
                if(w.achievementHeld(i)[0] == 0)
                    description = (new StringBuilder(description)).append(" (Next: 2 with Aversion)\nBonus: N/A").toString();
                else
                if(w.achievementHeld(i)[0] == 1)
                    description = (new StringBuilder(description)).append(" (Next: 5 with Aversion)\nBonus: Aversion requirement decreases 2 rounds per use").toString();
                else
                if(w.achievementHeld(i)[0] == 2)
                    description = (new StringBuilder(description)).append(" (Next: 12 with Aversion)\nBonus: Aversion requirement decreases 3 rounds per use").toString();
                else
                if(w.achievementHeld(i)[0] == 3)
                    description = (new StringBuilder(description)).append(" (Next: 30 with Aversion)\nBonus: Aversion requirement decreases 4 rounds per use").toString();
                else
                if(w.achievementHeld(i)[0] == 4)
                    description = (new StringBuilder(description)).append(" (Next: 80 with Aversion)\nBonus: Aversion requirement decreases 5 rounds per use").toString();
                else
                    description = (new StringBuilder(description)).append("\nBonus: Aversion requirement decreases 6 rounds per use").toString();
                description = (new StringBuilder(description)).append("\nThe Thralls you bring with you from city to city will gradually build their skills as they learn how to cause as much suffering as possible to their victims.  Every Chosen mindbroken is a useful example of what techniques work best.").toString();
            } else
            if(i == 7)
            {
                w.underlineAppend(t, "Heroine Hunter");
                description = (new StringBuilder(description)).append("Superior Chosen Broken: ").append(w.achievementHeld(i)[1]).append("\n").toString();
                description = (new StringBuilder(description)).append("Level: ").append(w.achievementHeld(i)[0]).toString();
                if(w.achievementHeld(i)[0] == 0)
                    description = (new StringBuilder(description)).append(" (Next: 1 Broken)\nBonus: N/A").toString();
                else
                if(w.achievementHeld(i)[0] == 1)
                    description = (new StringBuilder(description)).append(" (Next: 3 Broken)\nBonus: Slight increase to Resolve damage").toString();
                else
                if(w.achievementHeld(i)[0] == 2)
                    description = (new StringBuilder(description)).append(" (Next: 6 Broken)\nBonus: Notable increase to Resolve damage").toString();
                else
                if(w.achievementHeld(i)[0] == 3)
                    description = (new StringBuilder(description)).append(" (Next: 15 Broken)\nBonus: Moderate increase to Resolve damage").toString();
                else
                if(w.achievementHeld(i)[0] == 4)
                    description = (new StringBuilder(description)).append(" (Next: 40 Broken)\nBonus: Large increase to Resolve damage").toString();
                else
                    description = (new StringBuilder(description)).append("\nBonus: Extreme increase to Resolve damage").toString();
                description = (new StringBuilder(description)).append("\nThe public may not know the difference, but the Chosen themselves are keenly aware that some of their number are far more competent than others.  As you prove that you can convert even the best of them to the Demonic cause, they'll all lose hope of ever winning against you.").toString();
            }
            if(w.achievementHeld(i)[0] > w.achievementSeen[i])
            {
                w.achievementSeen[i] = w.achievementHeld(i)[0];
                w.greenAppend(t, "\n" + description);
            } else
            if(w.achievementHeld(i)[0] > 0)
                w.append(t, "\n" + description);
            else
                w.grayAppend(t, "\n" + description);
        }

        if(page < (w.achievementSeen.length - 1) / 5)
        {
            JButton NextPage = new JButton("Next Page");
            NextPage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.ViewAchievements(t, p, f, w, page + 1);
                }
            });
            p.add(NextPage);
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.ShowInformation(t, p, f, w);
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void ViewUpgrades(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final int page)
    {
        p.removeAll();
        if(page > 0)
        {
            JButton Previous = new JButton("<");
            Previous.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.ViewUpgrades(t, p, f, w, page - 1);
                }
            });
            p.add(Previous);
        }
        for(int i = page * 5; i < w.getTechs().length && i < page * 5 + 5; i++)
        {
            final int id = i;
            JButton Upgrade = new JButton(w.getTechs()[i].name);
            Upgrade.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                    w.getTechs()[id].printSummary(w, t);
                }
            });
            p.add(Upgrade);
            if(!w.getTechs()[i].isOwned())
                Upgrade.setForeground(Color.GRAY);
        }

        if(page < (w.getTechs().length - 1) / 5)
        {
            JButton Next = new JButton(">");
            Next.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.ViewUpgrades(t, p, f, w, page + 1);
                }
            });
            p.add(Next);
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.ShowInformation(t, p, f, w);
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void Cheat(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        p.removeAll();
        w.append(t, "\n\n" + w.getSeparator() + "\n\nWhich cheat would you like to use?\n\n+100 Evil Energy: Increases your Evil Energy by 100.\n\nChange Day: Allows you to skip closer to future events or revisit past ones with the team in its current state.  Range is limited to 1-50, and because events require all three Chosen to be present, this cheat cannot be activated until the full team has been encountered.\n\nDisable/Enable Adaptations: Prevents/Allows Chosen use of Slaughter, Fantasize, Detonate, and Striptease.  Note that use of these actions is required to reach later corruption stages.\n\nUnlock All Upgrades: Purchases every upgrade aside from Imago Quickening at no Evil Energy cost.");
        JButton AddEnergy = new JButton("+100 Evil Energy");
        AddEnergy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.addEnergy(100);
                Project.Shop(t, p, f, w);
            }
        });
        p.add(AddEnergy);
        JButton ChangeDay = new JButton("Change Day");
        ChangeDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String input = JOptionPane.showInputDialog("Enter the number of the day you wish to move to.");
                try
                {
                    int newDay = Integer.valueOf(input).intValue();
                    if(newDay < 2)
                        newDay = 2;
                    else
                    if(newDay > 50 - w.eventOffset * 3)
                        newDay = 50;
                    w.setDay(newDay);
                    Project.Shop(t, p, f, w);
                }
                catch(NumberFormatException n)
                {
                    w.append(t, "\n\n" + w.getSeparator() + "\n\nError: unable to recognize input as a number.");
                    Project.Cheat(t, p, f, w);
                }
            }
        });
        if(w.getCast()[2] != null)
            p.add(ChangeDay);
        JButton ToggleAdaptations = new JButton("Disable Adaptations");
        if(w.adaptationsDisabled())
            ToggleAdaptations.setText("Enable Adaptations");
        ToggleAdaptations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.toggleAdaptations();
                Project.Cheat(t, p, f, w);
            }
        });
        p.add(ToggleAdaptations);
        JButton AllUpgrades = new JButton("Unlock All Upgrades");
        AllUpgrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                for(int i = 0; i < w.getTechs().length - 1; i++)
                    w.getTechs()[i].owned = true;

                Project.Cheat(t, p, f, w);
            }
        });
        p.add(AllUpgrades);
        JButton Cancel = new JButton("Back");
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.Shop(t, p, f, w);
            }
        });
        p.add(Cancel);
        p.validate();
        p.repaint();
    }

    public static void Data(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final String function, final int page, final Boolean toShop)
    {
        String path = Project.class.getProtectionDomain().getCodeSource().getLocation().getPath();
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
        path = path.replaceAll(File.separator + "u0020", File.separator + " ");
        File saveLocation = new File(path + File.separator + "saves.sav");
        SaveData saves = null;
        if(saveLocation.exists())
        {
            ReadObject robj = new ReadObject();
            saves = robj.deserializeSaveData(path + File.separator + "saves.sav");
        } else
        {
            saves = new SaveData();
        }
        for(int i = 0; i < saves.getSaves().length; i++)
            saves.getSaves()[i].repairSave();

        final WriteObject wobj = new WriteObject();
        final SaveData saveFile = saves;
        if(function.equals("newsave"))
        {
            Boolean aborted = false;
            String newSaveName = JOptionPane.showInputDialog("What would you like to name this save?");
            if(newSaveName == null)
                aborted = true;
            else
            if(newSaveName.length() == 0)
            {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                newSaveName = (new StringBuilder("Save of ")).append(dateFormat.format(date)).toString();
            }
            if(aborted)
            {
                w.append(t, "  Aborted.");
            } else
            {
                w.append(t, "  \"" + newSaveName + "\" saved");
                saves.newSave(w, newSaveName);
                wobj.serializeSaveData(saves);
                w.save = saves;
            }
            Shop(t, p, f, w);
        } else
        if(function.equals("overwrite"))
        {
            if(saves.getSaves().length == 0)
            {
                Data(t, p, f, w, "newsave", 0, toShop);
            } else
            {
                String fullSaveName = (new StringBuilder(String.valueOf(saves.getNames()[0]))).append(" - Day ").append(saves.getSaves()[0].getDay()).append(" versus ").toString();
                if(saves.getSaves()[0].getCast()[1] == null)
                    fullSaveName = (new StringBuilder(fullSaveName)).append(saves.getSaves()[0].getCast()[0].getMainName()).toString();
                else
                if(saves.getSaves()[0].getCast()[2] == null)
                    fullSaveName = (new StringBuilder(fullSaveName)).append(saves.getSaves()[0].getCast()[0].getMainName()).append(" and ").append(saves.getSaves()[0].getCast()[1].getMainName()).toString();
                else
                    fullSaveName = (new StringBuilder(fullSaveName)).append(saves.getSaves()[0].getCast()[0].getMainName()).append(", ").append(saves.getSaves()[0].getCast()[1].getMainName()).append(", and ").append(saves.getSaves()[0].getCast()[2].getMainName()).toString();
                w.append(t, "\n\n" + w.getSeparator() + "\n\nReally overwrite \"" + fullSaveName + "\"?");
                p.removeAll();
                JButton Confirm = new JButton("Confirm");
                Confirm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        saveFile.overwriteSave(w);
                        wobj.serializeSaveData(saveFile);
                        w.append(t, "  Done.");
                        w.save = saveFile;
                        Project.Shop(t, p, f, w);
                    }
                });
                p.add(Confirm);
                JButton Cancel = new JButton("Cancel");
                Cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, "  Cancelled.");
                        Project.Shop(t, p, f, w);
                    }
                });
                p.add(Cancel);
                p.validate();
                p.repaint();
            }
        } else
        if(function.equals("export"))
        {
            if(w.campaign)
            {
                WorldState newWorld = new WorldState();
                newWorld.copyInitial(w);
                Chosen newChosen = new Chosen();
                newChosen.setNumber(0);
                newChosen.generate(newWorld);
                newWorld.addChosen(newChosen);
                String newSaveName = JOptionPane.showInputDialog("What would you like to name the exported file?");
                Boolean blankName = false;
                if(newSaveName == null)
                    blankName = true;
                else
                if(newSaveName.length() == 0)
                    blankName = true;
                if(blankName)
                {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    newSaveName = (new StringBuilder("Team of ")).append(dateFormat.format(date)).toString();
                }
                String editedName = "";
                for(int i = 0; i < newSaveName.length(); i++)
                    if(newSaveName.charAt(i) == '/' || newSaveName.charAt(i) == ':')
                        editedName = (new StringBuilder(editedName)).append("-").toString();
                    else
                        editedName = (new StringBuilder(editedName)).append(newSaveName.charAt(i)).toString();

                if(w.getHighScore() > 0L)
                    newWorld.setParScore(w.getHighScore());
                if(w.getParScore() > newWorld.getParScore())
                    newWorld.setParScore(w.getParScore());
                newWorld.copySettings(t, w);
                newWorld.copyToggles(w);
                wobj.exportFile(newWorld, editedName);
                w.append(t, "\n\n" + w.getSeparator() + "\n\nDay 1 start against this team saved to '" + editedName + ".par'.");
            } else
            {
                w.append(t, "\n\n" + w.getSeparator() + "\n\nUnable to export campaign save.");
            }
        } else
        if(function.equals("import"))
        {
            p.removeAll();
            int i = page * 4;
            int j = 0;
            WorldState foundWorlds[] = new WorldState[0];
            ReadObject robj = new ReadObject();
            foundWorlds = robj.importFiles();
            if(foundWorlds.length == 0)
            {
                w.append(t, "\n\n" + w.getSeparator() + "\n\nNo importable files found in directory.");
            } else
            {
                w.append(t, "\n\n" + w.getSeparator() + "\n\nFound the following importable files in directory.  Which would you like to import?");
                if(page > 0)
                {
                    JButton LastPage = new JButton("Previous Page");
                    LastPage.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            Project.Data(t, p, f, w, function, page - 1, toShop);
                        }
                    });
                    p.add(LastPage);
                }
                for(; i < foundWorlds.length && j < 4; j++)
                {
                    w.append(t, "\n\nFile " + String.valueOf(i + 1) + ": " + foundWorlds[i].getSaveTitle());
                    if(foundWorlds[i].getParScore() > 0L)
                        w.append(t, String.format(" (Par %1$s", foundWorlds[i].getCast()[0].condensedFormat(foundWorlds[i].getParScore())) + ")");
                    final int worldSelected = i;
                    final WorldState worldList[] = foundWorlds;
                    JButton Access = new JButton((new StringBuilder()).append(i + 1).toString());
                    Access.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            String path = Project.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                            String fileName = "";
                            for(int i = path.length() - 1; i >= 0; i--)
                                if(path.charAt(i) != '/')
                                    fileName = (new StringBuilder(String.valueOf(path.charAt(i)))).append(fileName).toString();
                                else
                                    i = -1;

                            try
                            {
                                path = path.substring(0, path.length() - fileName.length() - 1);
                                path = URLDecoder.decode(path, "UTF-8");
                                path = path.replaceAll("file:/", "");
                                path = path.replaceAll(File.separator + "u0020", File.separator + " ");
                                File saveLocation = new File(path + File.separator + "saves.sav");
                                SaveData saves = null;
                                if(saveLocation.exists())
                                {
                                    ReadObject robj = new ReadObject();
                                    saves = robj.deserializeSaveData(path + File.separator + "saves.sav");
                                } else
                                {
                                    saves = new SaveData();
                                }
                                WriteObject wobj = new WriteObject();
                                saves.endSave(worldList[worldSelected], worldList[worldSelected].getSaveTitle());
                                for(int i = 0; i < 3; i++)
                                    if(worldList[worldSelected].getCast()[i] != null)
                                        worldList[worldSelected].getCast()[i].globalID = saves.assignChosenID();

                                wobj.serializeSaveData(saves);
                                w.append(t, "\n\n" + w.getSeparator() + "\n\nImported file saved to slot " + saves.getSaves().length + ".");
                            }
                            catch(Exception ex)
                            {
                                ex.printStackTrace();
                            }
                        }
                    });
                    p.add(Access);
                    i++;
                }

                if(page * 4 + 4 < foundWorlds.length)
                {
                    JButton NextPage = new JButton("Next Page");
                    NextPage.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            Project.Data(t, p, f, w, function, page + 1, toShop);
                        }
                    });
                    p.add(NextPage);
                }
            }
            JButton Back = new JButton("Back");
            Back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(toShop)
                    {
                        Project.Shop(t, p, f, w);
                    } else
                    {
                        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                        Project.IntroOne(t, p, f, w);
                    }
                }
            });
            p.add(Back);
            p.validate();
            p.repaint();
        } else
        {
            int i = page * 4;
            int j = 0;
            w.append(t, "\n\n" + w.getSeparator() + "\n\n");
            if(function.equals("load"))
                w.append(t, "Load which slot?");
            else
            if(function.equals("teamload"))
                w.append(t, "Load which team?");
            else
                w.append(t, "Delete which slot?");
            p.removeAll();
            if(page > 0)
            {
                JButton LastPage = new JButton("Previous Page");
                LastPage.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Data(t, p, f, w, function, page - 1, toShop);
                    }
                });
                p.add(LastPage);
            }
            for(; i < saves.getSaves().length && j < 4; j++)
            {
                String fullSaveName = (new StringBuilder(String.valueOf(saves.getNames()[i]))).append(" - Day ").append(saves.getSaves()[i].getDay()).append(" versus ").toString();
                if(saves.getSaves()[i].getCast()[1] == null)
                    fullSaveName = (new StringBuilder(fullSaveName)).append(saves.getSaves()[i].getCast()[0].getMainName()).toString();
                else
                if(saves.getSaves()[i].getCast()[2] == null)
                    fullSaveName = (new StringBuilder(fullSaveName)).append(saves.getSaves()[i].getCast()[0].getMainName()).append(" and ").append(saves.getSaves()[i].getCast()[1].getMainName()).toString();
                else
                    fullSaveName = (new StringBuilder(fullSaveName)).append(saves.getSaves()[i].getCast()[0].getMainName()).append(", ").append(saves.getSaves()[i].getCast()[1].getMainName()).append(", and ").append(saves.getSaves()[i].getCast()[2].getMainName()).toString();
                if(saves.getSaves()[i].getHighScore() > 0L)
                {
                    fullSaveName = (new StringBuilder(fullSaveName)).append(" (HS ").append(saves.getSaves()[i].getCast()[0].condensedFormat(saves.getSaves()[i].getHighScore())).toString();
                    if(saves.getSaves()[i].getParScore() > 0L)
                        fullSaveName = (new StringBuilder(fullSaveName)).append(" | Par ").append(saves.getSaves()[i].getCast()[0].condensedFormat(saves.getSaves()[i].getParScore())).append(")").toString();
                    else
                        fullSaveName = (new StringBuilder(fullSaveName)).append(")").toString();
                } else
                if(saves.getSaves()[i].getParScore() > 0L)
                    fullSaveName = (new StringBuilder(fullSaveName)).append(" (Par ").append(saves.getSaves()[i].getCast()[0].condensedFormat(saves.getSaves()[i].getParScore())).append(")").toString();
                String displayedName = (new StringBuilder("\n\nSlot ")).append(i + 1).toString();
                if(i == 0)
                    displayedName = (new StringBuilder(displayedName)).append(" (most recent)").toString();
                else
                if(i == saves.getSaves().length - 1)
                    displayedName = (new StringBuilder(displayedName)).append(" (oldest)").toString();
                displayedName = (new StringBuilder(displayedName)).append(", ").append(fullSaveName).toString();
                if(saves.getSaves()[i].campaign)
                {
                    if(saves.getSaves()[i].earlyCheat)
                    {
                        w.greenAppend(t, displayedName + " [Loop " + String.valueOf(saves.getSaves()[i].loops + 1) + ": " + saves.getSaves()[i].cityName);
                        if(saves.getSaves()[i].loopComplete)
                            w.greenAppend(t, "] [Loop Complete]");
                        else
                            w.greenAppend(t, "]");
                    } else
                    {
                        w.blueAppend(t, displayedName + " [Loop " + String.valueOf(saves.getSaves()[i].loops + 1) + ": " + saves.getSaves()[i].cityName);
                        if(saves.getSaves()[i].loopComplete)
                            w.blueAppend(t, "] [Loop Complete]");
                        else
                            w.blueAppend(t, "]");
                    }
                } else
                if(saves.getSaves()[i].hardMode)
                    w.redAppend(t, displayedName);
                else
                if(saves.getSaves()[i].earlyCheat)
                    w.greenAppend(t, displayedName);
                else
                    w.append(t, displayedName);
                final int fileSelected = i;
                final String thisSaveName = fullSaveName;
                JButton Access = new JButton((new StringBuilder()).append(i + 1).toString());
                Access.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        if(function.equals("load"))
                        {
                            if(toShop)
                            {
                                p.removeAll();
                                w.append(t, "\n\n" + w.getSeparator() + "\n\nReally load \"" + thisSaveName + "\"?  Your current progress won't be saved.");
                                JButton Confirm = new JButton("Confirm");
                                Confirm.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e)
                                    {
                                        WorldState savedWorld = new WorldState();
                                        savedWorld = saveFile.getSaves()[fileSelected];
                                        wobj.serializeSaveData(saveFile);
                                        saveFile.moveToFront(fileSelected);
                                        wobj.serializeSaveData(saveFile);
                                        savedWorld.copySettings(t, w);
                                        savedWorld.setCommentaryRead(w.getCommentaryRead());
                                        savedWorld.setCommentaryWrite(w.getCommentaryWrite());
                                        saveFile.getSaves()[0].save = saveFile;
                                        if(savedWorld.getDay() == 1 && savedWorld.evilEnergy == 0 && !savedWorld.getTechs()[0].owned && !savedWorld.getTechs()[1].owned && !savedWorld.getTechs()[2].owned && !savedWorld.getTechs()[3].owned && !savedWorld.campaign)
                                        {
                                            savedWorld.earlyCheat = w.earlyCheat;
                                            savedWorld.earlyCheat = w.earlyCheat;
                                            savedWorld.hardMode = w.hardMode;
                                            savedWorld.eventOffset = w.eventOffset;
                                            savedWorld.clampStart = w.clampStart;
                                            savedWorld.clampPercent = w.clampPercent;
                                            if(savedWorld.earlyCheat)
                                                Project.Shop(t, p, f, saveFile.getSaves()[0]);
                                            else
                                                Project.IntroTwo(t, p, f, saveFile.getSaves()[0]);
                                        } else
                                        {
                                            Project.Shop(t, p, f, saveFile.getSaves()[0]);
                                        }
                                    }
                                });
                                p.add(Confirm);
                                JButton Cancel = new JButton("Cancel");
                                Cancel.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e)
                                    {
                                        Project.Shop(t, p, f, w);
                                    }
                                });
                                p.add(Cancel);
                                p.validate();
                                p.repaint();
                            } else
                            {
                                WorldState savedWorld = new WorldState();
                                savedWorld = saveFile.getSaves()[fileSelected];
                                saveFile.moveToFront(fileSelected);
                                wobj.serializeSaveData(saveFile);
                                savedWorld.copySettings(t, w);
                                savedWorld.setCommentaryRead(w.getCommentaryRead());
                                savedWorld.setCommentaryWrite(w.getCommentaryWrite());
                                savedWorld.save = saveFile;
                                if(savedWorld.getDay() == 1 && savedWorld.evilEnergy == 0 && !savedWorld.getTechs()[0].owned && !savedWorld.getTechs()[1].owned && !savedWorld.getTechs()[2].owned && !savedWorld.getTechs()[3].owned)
                                {
                                    savedWorld.earlyCheat = w.earlyCheat;
                                    savedWorld.hardMode = w.hardMode;
                                    savedWorld.eventOffset = w.eventOffset;
                                    savedWorld.clampStart = w.clampStart;
                                    savedWorld.clampPercent = w.clampPercent;
                                    if(savedWorld.earlyCheat)
                                        Project.Shop(t, p, f, savedWorld);
                                    else
                                        Project.IntroTwo(t, p, f, savedWorld);
                                } else
                                {
                                    Project.Shop(t, p, f, savedWorld);
                                }
                            }
                        } else
                        if(function.equals("teamload"))
                        {
                            w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                            int found = 1;
                            Chosen copies[] = new Chosen[3];
                            WorldState x = new WorldState();
                            x.copySettings(t, saveFile.getSaves()[fileSelected]);
                            x.copyToggles(saveFile.getSaves()[fileSelected]);
                            x.copyInitial(saveFile.getSaves()[fileSelected]);
                            x.save = w.save;
                            x.campaign = saveFile.getSaves()[fileSelected].campaign;
                            for(int j = 0; j < 3; j++)
                                if(saveFile.getSaves()[fileSelected].getCast()[j] != null)
                                {
                                    found = j + 1;
                                    if(x.loopChosen != null && x.loopChosen[j] != null)
                                    {
                                        copies[j] = new Chosen();
                                        copies[j].campaignImport(x, x.loopChosen[j]);
                                        copies[j].globalID = w.save.assignChosenID();
                                        copies[j].originalCity = null;
                                        copies[j].lastLoop = 0;
                                        copies[j].originalGender = copies[j].gender;
                                        if(copies[j].morality > 66)
                                            copies[j].bonusHATE = true;
                                        if(copies[j].innocence > 66)
                                            copies[j].bonusPLEA = true;
                                        if(copies[j].confidence > 66)
                                            copies[j].bonusINJU = true;
                                        if(copies[j].dignity > 66)
                                            copies[j].bonusEXPO = true;
                                        copies[j].introduced = false;
                                        copies[j].resetDistortions();
                                        copies[j].kills = new Chosen[0];
                                        copies[j].killRelationships = new int[0];
                                        copies[j].formerPartners = new Chosen[0];
                                        copies[j].formerRelationships = new int[0];
                                    } else
                                    {
                                        copies[j] = new Chosen();
                                        copies[j].setNumber(j);
                                        copies[j].generate(x);
                                        x.addChosen(copies[j]);
                                        copies[j].originalCity = null;
                                        copies[j].lastLoop = 0;
                                    }
                                }

                            Chosen newRoster[] = new Chosen[w.save.customRoster.length + found];
                            for(int j = 0; j < newRoster.length; j++)
                                if(j < w.save.customRoster.length)
                                    newRoster[j] = w.save.customRoster[j];
                                else
                                    newRoster[j] = copies[j - w.save.customRoster.length];

                            w.save.customRoster = newRoster;
                            w.append(t, String.format("Added %1$s", saveFile.getSaves()[fileSelected].getCast()[0].mainName));
                            if(found == 3)
                                w.append(t, String.format(", %1$s", saveFile.getSaves()[fileSelected].getCast()[1].mainName) + ", and " + saveFile.getSaves()[fileSelected].getCast()[2].mainName);
                            else
                            if(found == 2)
                                w.append(t, String.format(" and %1$s", saveFile.getSaves()[fileSelected].getCast()[1].mainName));
                            w.append(t, " to the custom roster.");
                        } else
                        {
                            p.removeAll();
                            w.append(t, "\n\n" + w.getSeparator() + "\n\nReally delete \"" + thisSaveName + "\"?");
                            JButton Confirm = new JButton("Confirm");
                            Confirm.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    saveFile.deleteSave(fileSelected);
                                    wobj.serializeSaveData(saveFile);
                                    w.append(t, "  Done.");
                                    w.save = saveFile;
                                    Project.Shop(t, p, f, w);
                                }
                            });
                            p.add(Confirm);
                            JButton Cancel = new JButton("Cancel");
                            Cancel.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    w.append(t, "  Cancelled.");
                                    Project.Shop(t, p, f, w);
                                }
                            });
                            p.add(Cancel);
                            p.validate();
                            p.repaint();
                        }
                    }
                });
                p.add(Access);
                i++;
            }

            if(page * 4 + 4 < saves.getSaves().length)
            {
                JButton NextPage = new JButton("Next Page");
                NextPage.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Data(t, p, f, w, function, page + 1, toShop);
                    }
                });
                p.add(NextPage);
            }
            JButton Cancel = new JButton("Cancel");
            if(function.equals("teamload"))
                Cancel.setText("Done");
            Cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(function.equals("teamload"))
                    {
                        Boolean enabled[] = new Boolean[w.save.customRoster.length];
                        for(int i = 0; i < enabled.length; i++)
                            enabled[i] = true;

                        Project.CampaignMenu(t, p, f, w, enabled);
                    } else
                    if(toShop)
                    {
                        Project.Shop(t, p, f, w);
                    } else
                    {
                        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                        Project.IntroOne(t, p, f, w);
                    }
                }
            });
            p.add(Cancel);
            p.validate();
            p.repaint();
        }
    }

    public static void Customize(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        p.removeAll();
        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
        if(!w.getBodyStatus()[0])
        {
            w.append(t, "You aren't currently sending a Commander body to the battlefield.  Creating one costs 1 Evil Energy.");
            if(w.getEvilEnergy() >= 1)
            {
                JButton Create = new JButton("Create");
                Create.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.newCommander();
                        w.addEnergy(-1);
                        Project.Customize(t, p, f, w);
                    }
                });
                p.add(Create);
            }
            if(!w.hardMode && w.save != null && w.getHarem() != null && w.getHarem().length > 0)
            {
                JButton UseForsaken = new JButton("Use Forsaken");
                UseForsaken.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.ForsakenMenu(t, p, f, w, w.save, 0);
                    }
                });
                p.add(UseForsaken);
            }
            JButton Back = new JButton("Back");
            Back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Project.Shop(t, p, f, w);
                }
            });
            p.add(Back);
        } else
        {
            w.printCommanderSummary(t, null);
            int suppressorsKnown = 0;
            if(w.getTechs()[10].isOwned())
                suppressorsKnown++;
            if(w.getTechs()[11].isOwned())
                suppressorsKnown++;
            if(w.getTechs()[12].isOwned())
                suppressorsKnown++;
            if(w.getTechs()[13].isOwned())
                suppressorsKnown++;
            int suppressorsUsed = 0;
            if(w.getBodyStatus()[3])
                suppressorsUsed++;
            if(w.getBodyStatus()[4])
                suppressorsUsed++;
            if(w.getBodyStatus()[5])
                suppressorsUsed++;
            if(w.getBodyStatus()[6])
                suppressorsUsed++;
            final Boolean defilerUsed = Boolean.valueOf(w.getBodyStatus()[11] || w.getBodyStatus()[12] || w.getBodyStatus()[13] || w.getBodyStatus()[14]);
            Boolean defilerKnown = false;
            if(w.getTechs()[22].isOwned() || w.getTechs()[23].isOwned() || w.getTechs()[24].isOwned() || w.getTechs()[25].isOwned())
                defilerKnown = true;
            final int suppressorsUsedFinal = suppressorsUsed;
            final Boolean punisherUsed = Boolean.valueOf(w.getBodyStatus()[19] || w.getBodyStatus()[20] || w.getBodyStatus()[21] || w.getBodyStatus()[22]);
            Boolean punisherKnown = false;
            if(w.getTechs()[34].isOwned() || w.getTechs()[35].isOwned() || w.getTechs()[36].isOwned() || w.getTechs()[37].isOwned())
                punisherKnown = true;
            if((!punisherUsed || w.getTechs()[47].isOwned() && (w.getEvilEnergy() >= 66 || defilerUsed)) && (suppressorsKnown > 0 && suppressorsUsed == 0 && (!defilerUsed || w.getTechs()[33].isOwned() && w.getEvilEnergy() >= 10 || punisherUsed) || suppressorsKnown > 1 && suppressorsUsed == 1 && w.getEvilEnergy() >= 5 && w.getTechs()[21].isOwned() && !defilerUsed && !punisherUsed))
            {
                JButton Suppressor = new JButton("Suppressor Upgrades");
                Suppressor.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        if(w.getTechs()[10].isOwned() && !w.getBodyStatus()[3])
                        {
                            JButton Hunger = new JButton("Hunger [HATE]");
                            Hunger.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    if(punisherUsed)
                                        w.applyCompletion();
                                    else
                                    if(defilerUsed)
                                        w.applySynthesis();
                                    else
                                    if(suppressorsUsedFinal == 1)
                                        w.applyVersatility();
                                    w.applyHunger();
                                    Project.Customize(t, p, f, w);
                                }
                            });
                            p.add(Hunger);
                        }
                        if(w.getTechs()[11].isOwned() && !w.getBodyStatus()[4])
                        {
                            JButton Lust = new JButton("Lust [PLEA]");
                            Lust.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    if(punisherUsed)
                                        w.applyCompletion();
                                    else
                                    if(defilerUsed)
                                        w.applySynthesis();
                                    else
                                    if(suppressorsUsedFinal == 1)
                                        w.applyVersatility();
                                    w.applyLust();
                                    Project.Customize(t, p, f, w);
                                }
                            });
                            p.add(Lust);
                        }
                        if(w.getTechs()[12].isOwned() && !w.getBodyStatus()[5])
                        {
                            JButton Anger = new JButton("Anger [INJU]");
                            Anger.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    if(punisherUsed)
                                        w.applyCompletion();
                                    else
                                    if(defilerUsed)
                                        w.applySynthesis();
                                    else
                                    if(suppressorsUsedFinal == 1)
                                        w.applyVersatility();
                                    w.applyAnger();
                                    Project.Customize(t, p, f, w);
                                }
                            });
                            p.add(Anger);
                        }
                        if(w.getTechs()[13].isOwned() && !w.getBodyStatus()[6])
                        {
                            JButton Mania = new JButton("Mania [EXPO]");
                            Mania.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    if(punisherUsed)
                                        w.applyCompletion();
                                    else
                                    if(defilerUsed)
                                        w.applySynthesis();
                                    else
                                    if(suppressorsUsedFinal == 1)
                                        w.applyVersatility();
                                    w.applyMania();
                                    Project.Customize(t, p, f, w);
                                }
                            });
                            p.add(Mania);
                        }
                        JButton Cancel = new JButton("Cancel");
                        Cancel.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                Project.Customize(t, p, f, w);
                            }
                        });
                        p.add(Cancel);
                        p.validate();
                        p.repaint();
                        w.append(t, "\n\n" + w.getSeparator() + "\n\nWhich upgrade will you apply?");
                    }
                });
                p.add(Suppressor);
            }
            if((!punisherUsed || w.getTechs()[47].isOwned() && w.getEvilEnergy() >= 66 || w.getTechs()[47].isOwned() && suppressorsUsed == 1) && defilerKnown && !defilerUsed && (suppressorsUsed == 0 || suppressorsUsed == 1 && w.getTechs()[33].isOwned() && w.getEvilEnergy() >= 16) && w.getEvilEnergy() >= 6)
            {
                JButton Defiler = new JButton("Defiler Upgrades");
                Defiler.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        if(w.getTechs()[22].isOwned())
                        {
                            JButton Ambition = new JButton("Ambition [HATE/PLEA]");
                            Ambition.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyAmbition();
                                    if(punisherUsed)
                                        w.applyCompletion();
                                    else
                                    if(suppressorsUsedFinal == 1)
                                        w.applySynthesis();
                                    Project.Customize(t, p, f, w);
                                }
                            });
                            p.add(Ambition);
                        }
                        if(w.getTechs()[23].isOwned())
                        {
                            JButton Dominance = new JButton("Dominance [PLEA/INJU]");
                            if(w.tickle())
                                Dominance.setText("Dominance [PLEA/ANTI]");
                            Dominance.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyDominance();
                                    if(punisherUsed)
                                        w.applyCompletion();
                                    else
                                    if(suppressorsUsedFinal == 1)
                                        w.applySynthesis();
                                    Project.Customize(t, p, f, w);
                                }
                            });
                            p.add(Dominance);
                        }
                        if(w.getTechs()[24].isOwned())
                        {
                            JButton Spite = new JButton("Spite [INJU/EXPO]");
                            if(w.tickle())
                                Spite.setText("Spite [ANTI/EXPO]");
                            Spite.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applySpite();
                                    if(punisherUsed)
                                        w.applyCompletion();
                                    else
                                    if(suppressorsUsedFinal == 1)
                                        w.applySynthesis();
                                    Project.Customize(t, p, f, w);
                                }
                            });
                            p.add(Spite);
                        }
                        if(w.getTechs()[25].isOwned())
                        {
                            JButton Vanity = new JButton("Vanity [EXPO/HATE]");
                            Vanity.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyVanity();
                                    if(punisherUsed)
                                        w.applyCompletion();
                                    else
                                    if(suppressorsUsedFinal == 1)
                                        w.applySynthesis();
                                    Project.Customize(t, p, f, w);
                                }
                            });
                            p.add(Vanity);
                        }
                        JButton Cancel = new JButton("Cancel");
                        Cancel.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                Project.Customize(t, p, f, w);
                            }
                        });
                        p.add(Cancel);
                        p.validate();
                        p.repaint();
                        w.append(t, "\n\n" + w.getSeparator() + "\n\nWhich upgrade will you apply?");
                    }
                });
                p.add(Defiler);
            }
            if(!punisherUsed && punisherKnown && (!defilerUsed && suppressorsUsed == 0 || w.getTechs()[47].isOwned() && suppressorsUsed < 2 && (w.getEvilEnergy() >= 66 || defilerUsed && w.getEvilEnergy() >= 60 || defilerUsed && suppressorsUsed == 1 && w.getEvilEnergy() >= 50)))
            {
                JButton Punisher = new JButton("Punisher Upgrades");
                Punisher.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        if(w.getTechs()[34].isOwned())
                        {
                            JButton Impregnation = new JButton("Impregnation [HATE]");
                            Impregnation.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyImpregnation();
                                    if(defilerUsed || suppressorsUsedFinal == 1)
                                        w.applyCompletion();
                                    Project.Customize(t, p, f, w);
                                }
                            });
                            p.add(Impregnation);
                        }
                        if(w.getTechs()[35].isOwned())
                        {
                            JButton Hypnosis = new JButton("Hypnosis [PLEA]");
                            Hypnosis.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyHypnosis();
                                    if(defilerUsed || suppressorsUsedFinal == 1)
                                        w.applyCompletion();
                                    Project.Customize(t, p, f, w);
                                }
                            });
                            p.add(Hypnosis);
                        }
                        if(w.getTechs()[36].isOwned())
                        {
                            JButton Drain = new JButton("Drain [INJU]");
                            if(w.tickle())
                                Drain.setText("Drain [ANTI]");
                            Drain.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyDrain();
                                    if(defilerUsed || suppressorsUsedFinal == 1)
                                        w.applyCompletion();
                                    Project.Customize(t, p, f, w);
                                }
                            });
                            p.add(Drain);
                        }
                        if(w.getTechs()[37].isOwned())
                        {
                            JButton Parasitism = new JButton("Parasitism [EXPO]");
                            Parasitism.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyParasitism();
                                    if(defilerUsed || suppressorsUsedFinal == 1)
                                        w.applyCompletion();
                                    Project.Customize(t, p, f, w);
                                }
                            });
                            p.add(Parasitism);
                        }
                        JButton Cancel = new JButton("Cancel");
                        Cancel.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                Project.Customize(t, p, f, w);
                            }
                        });
                        p.add(Cancel);
                        p.validate();
                        p.repaint();
                        w.append(t, "\n\n" + w.getSeparator() + "\n\nWhich upgrade will you apply?");
                    }
                });
                p.add(Punisher);
            }
            if(w.getTechs()[8].isOwned() && !w.getBodyStatus()[1] && w.getEvilEnergy() >= 1)
            {
                JButton Enhance = new JButton("Enhance Duration (1)");
                Enhance.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.enhanceOne();
                        Project.Customize(t, p, f, w);
                    }
                });
                p.add(Enhance);
            }
            if(w.getTechs()[14].isOwned() && w.getBodyStatus()[1] && !w.getBodyStatus()[7] && w.getEvilEnergy() >= 1)
            {
                JButton Enhance = new JButton("Enhance Duration (2)");
                Enhance.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.enhanceTwo();
                        Project.Customize(t, p, f, w);
                    }
                });
                p.add(Enhance);
            }
            if(w.getTechs()[20].isOwned() && w.getBodyStatus()[7] && !w.getBodyStatus()[9] && w.getEvilEnergy() >= 2)
            {
                JButton Enhance = new JButton("Enhance Duration (3)");
                Enhance.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.enhanceThree();
                        Project.Customize(t, p, f, w);
                    }
                });
                p.add(Enhance);
            }
            if(w.getTechs()[26].isOwned() && w.getBodyStatus()[9] && !w.getBodyStatus()[15] && w.getEvilEnergy() >= 2)
            {
                JButton Enhance = new JButton("Enhance Duration (4)");
                Enhance.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.enhanceFour();
                        Project.Customize(t, p, f, w);
                    }
                });
                p.add(Enhance);
            }
            if(w.getTechs()[46].isOwned() && w.getBodyStatus()[15] && !w.getBodyStatus()[25] && w.getEvilEnergy() >= 30)
            {
                JButton Enhance = new JButton("Enhance Duration (5)");
                Enhance.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.enhanceFive();
                        Project.Customize(t, p, f, w);
                    }
                });
                p.add(Enhance);
            }
            if(w.getTechs()[15].isOwned() && !w.getBodyStatus()[8] && w.getEvilEnergy() >= 2)
            {
                JButton AddCapture = new JButton("Extra Capture (1)");
                AddCapture.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.addCaptureOne();
                        Project.Customize(t, p, f, w);
                    }
                });
                p.add(AddCapture);
            }
            if(w.getTechs()[27].isOwned() && w.getBodyStatus()[8] && !w.getBodyStatus()[16] && w.getEvilEnergy() >= 5)
            {
                JButton AddCapture = new JButton("Extra Capture (2)");
                AddCapture.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.addCaptureTwo();
                        Project.Customize(t, p, f, w);
                    }
                });
                p.add(AddCapture);
            }
            if(w.getTechs()[32].isOwned() && w.getBodyStatus()[16] && !w.getBodyStatus()[17] && w.getEvilEnergy() >= 10)
            {
                JButton AddCapture = new JButton("Extra Capture (3)");
                AddCapture.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.addCaptureThree();
                        Project.Customize(t, p, f, w);
                    }
                });
                p.add(AddCapture);
            }
            if(w.getTechs()[38].isOwned() && w.getBodyStatus()[17] && !w.getBodyStatus()[23] && w.getEvilEnergy() >= 20)
            {
                JButton AddCapture = new JButton("Extra Capture (4)");
                AddCapture.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.addCaptureFour();
                        Project.Customize(t, p, f, w);
                    }
                });
                p.add(AddCapture);
            }
            if(w.getTechs()[39].isOwned() && !w.getBodyStatus()[24] && w.getEvilEnergy() >= 10)
            {
                JButton Flight = new JButton("Flight");
                Flight.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.applyRelentlessness();
                        Project.Customize(t, p, f, w);
                    }
                });
                p.add(Flight);
            }
            if(w.getTechs()[9].isOwned())
            {
                JButton Toggle = new JButton("Toggle Ambush");
                Toggle.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.toggleAmbush();
                        Project.Customize(t, p, f, w);
                    }
                });
                p.add(Toggle);
            }
            JButton Refund = new JButton("Refund");
            Refund.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.addEnergy(w.getCommanderValue());
                    w.clearCommander();
                    Project.Customize(t, p, f, w);
                }
            });
            p.add(Refund);
            if(!punisherUsed || !defilerUsed && suppressorsUsed == 0 || defilerUsed && suppressorsUsed == 1)
            {
                JButton Done = new JButton("Done");
                Done.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Shop(t, p, f, w);
                    }
                });
                p.add(Done);
            }
        }
        p.validate();
        p.repaint();
    }

    public static void advanceDowntimeAction(JPanel p, WorldState w, int action)
    {
        Boolean actionMatches = true;
        if(w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] != action)
        {
            actionMatches = false;
            w.truncateCommentary(w.getCurrentAction());
        }
        if(w.writePossible())
            if(w.getCurrentComment().length() > 0)
                w.writeCommentary(w.getCurrentComment());
            else
            if(w.getCommentary().length <= w.getCurrentAction() || !actionMatches)
            {
                String generated = "";
                if(action < w.getTechs().length)
                    generated = (new StringBuilder("Buy ")).append(w.getTechs()[action].getName()).append(".").toString();
                else
                if(w.usedForsaken != null)
                {
                    generated = (new StringBuilder(generated)).append("(This playthrough used one of the Forsaken here, so it may not be possible to reliably follow it.)").toString();
                } else
                {
                    int index = action - w.getTechs().length;
                    if(w.getBodyStatus()[0])
                    {
                        generated = "Buy a Commander with a ";
                        if(w.getBodyStatus()[1])
                        {
                            if(w.getBodyStatus()[7])
                            {
                                if(w.getBodyStatus()[9])
                                {
                                    if(w.getBodyStatus()[15])
                                    {
                                        if(w.getBodyStatus()[25])
                                            generated = (new StringBuilder(generated)).append("eight").toString();
                                        else
                                            generated = (new StringBuilder(generated)).append("six").toString();
                                    } else
                                    {
                                        generated = (new StringBuilder(generated)).append("five").toString();
                                    }
                                } else
                                {
                                    generated = (new StringBuilder(generated)).append("four").toString();
                                }
                            } else
                            {
                                generated = (new StringBuilder(generated)).append("three").toString();
                            }
                        } else
                        {
                            generated = (new StringBuilder(generated)).append("two").toString();
                        }
                        generated = (new StringBuilder(generated)).append("-turn duration").toString();
                        if(w.getBodyStatus()[17])
                            generated = (new StringBuilder(generated)).append(" and three extra captures.  ").toString();
                        else
                        if(w.getBodyStatus()[16])
                            generated = (new StringBuilder(generated)).append(" and two extra captures.  ").toString();
                        else
                        if(w.getBodyStatus()[8])
                            generated = (new StringBuilder(generated)).append(" and an extra capture.  ").toString();
                        else
                            generated = (new StringBuilder(generated)).append(".  ").toString();
                        if(w.getBodyStatus()[26])
                        {
                            String suppressor = "";
                            String defiler = "";
                            String punisher = "";
                            if(w.getBodyStatus()[19])
                                punisher = "Impregnation";
                            else
                            if(w.getBodyStatus()[20])
                                punisher = "Hypnosis";
                            else
                            if(w.getBodyStatus()[21])
                                punisher = "Drain";
                            else
                            if(w.getBodyStatus()[22])
                                punisher = "Parasitism";
                            if(w.getBodyStatus()[11])
                                defiler = "Ambition";
                            else
                            if(w.getBodyStatus()[12])
                                defiler = "Dominance";
                            else
                            if(w.getBodyStatus()[13])
                                defiler = "Spite";
                            else
                            if(w.getBodyStatus()[14])
                                defiler = "Vanity";
                            if(w.getBodyStatus()[3])
                                suppressor = "Hunger";
                            else
                            if(w.getBodyStatus()[4])
                                suppressor = "Lust";
                            else
                            if(w.getBodyStatus()[5])
                                suppressor = "Anger";
                            else
                            if(w.getBodyStatus()[6])
                                suppressor = "Mania";
                            generated = (new StringBuilder(generated)).append("Equip it with the Suppressor ").append(suppressor).append(", the Defiler ").append(defiler).append(", and the Punisher ").append(punisher).append(".  ").toString();
                        } else
                        if(w.getBodyStatus()[19])
                            generated = (new StringBuilder(generated)).append("Equip it with the Punisher Impregnation.  ").toString();
                        else
                        if(w.getBodyStatus()[20])
                            generated = (new StringBuilder(generated)).append("Equip it with the Punisher Hypnosis.  ").toString();
                        else
                        if(w.getBodyStatus()[21])
                            generated = (new StringBuilder(generated)).append("Equip it with the Punisher Drain.  ").toString();
                        else
                        if(w.getBodyStatus()[22])
                            generated = (new StringBuilder(generated)).append("Equip it with the Punisher Parasitism.  ").toString();
                        else
                        if(w.getBodyStatus()[18])
                        {
                            generated = (new StringBuilder(generated)).append("Equip it with the Defiler ").toString();
                            if(w.getBodyStatus()[11])
                                generated = (new StringBuilder(generated)).append("Ambition").toString();
                            else
                            if(w.getBodyStatus()[12])
                                generated = (new StringBuilder(generated)).append("Dominance").toString();
                            else
                            if(w.getBodyStatus()[13])
                                generated = (new StringBuilder(generated)).append("Spite").toString();
                            else
                            if(w.getBodyStatus()[14])
                                generated = (new StringBuilder(generated)).append("Vanity").toString();
                            generated = (new StringBuilder(generated)).append(" and the Suppressor ").toString();
                            if(w.getBodyStatus()[3])
                                generated = (new StringBuilder(generated)).append("Hunger").toString();
                            else
                            if(w.getBodyStatus()[4])
                                generated = (new StringBuilder(generated)).append("Lust").toString();
                            else
                            if(w.getBodyStatus()[5])
                                generated = (new StringBuilder(generated)).append("Anger").toString();
                            else
                            if(w.getBodyStatus()[6])
                                generated = (new StringBuilder(generated)).append("Mania").toString();
                            generated = (new StringBuilder(generated)).append(".  ").toString();
                        } else
                        if(w.getBodyStatus()[11])
                            generated = (new StringBuilder(generated)).append("Equip it with the Defiler Ambition.  ").toString();
                        else
                        if(w.getBodyStatus()[12])
                            generated = (new StringBuilder(generated)).append("Equip it with the Defiler Dominance.  ").toString();
                        else
                        if(w.getBodyStatus()[13])
                            generated = (new StringBuilder(generated)).append("Equip it with the Defiler Spite.  ").toString();
                        else
                        if(w.getBodyStatus()[14])
                            generated = (new StringBuilder(generated)).append("Equip it with the Defiler Vanity.  ").toString();
                        else
                        if(w.getBodyStatus()[10])
                        {
                            generated = (new StringBuilder(generated)).append("Equip it with the Suppressors ").toString();
                            Boolean first = false;
                            if(w.getBodyStatus()[3])
                            {
                                generated = (new StringBuilder(generated)).append("Hunger").toString();
                                first = true;
                            }
                            if(w.getBodyStatus()[4])
                            {
                                if(first)
                                    generated = (new StringBuilder(generated)).append(" and ").toString();
                                generated = (new StringBuilder(generated)).append("Lust").toString();
                                first = true;
                            }
                            if(w.getBodyStatus()[5])
                            {
                                if(first)
                                    generated = (new StringBuilder(generated)).append(" and ").toString();
                                generated = (new StringBuilder(generated)).append("Anger").toString();
                                first = true;
                            }
                            if(w.getBodyStatus()[6])
                                generated = (new StringBuilder(generated)).append(" and Mania").toString();
                            generated = (new StringBuilder(generated)).append(".  ").toString();
                        } else
                        if(w.getBodyStatus()[3])
                            generated = (new StringBuilder(generated)).append("Equip it with the Suppressor Hunger.  ").toString();
                        else
                        if(w.getBodyStatus()[4])
                            generated = (new StringBuilder(generated)).append("Equip it with the Suppressor Lust.  ").toString();
                        else
                        if(w.getBodyStatus()[5])
                            generated = (new StringBuilder(generated)).append("Equip it with the Suppressor Anger.  ").toString();
                        else
                        if(w.getBodyStatus()[6])
                            generated = (new StringBuilder(generated)).append("Equip it with the Suppressor Mania.  ").toString();
                        if(w.getBodyStatus()[2])
                            generated = (new StringBuilder(generated)).append("Turn off its ambush and send ").toString();
                        else
                        if(w.getTechs()[9].isOwned())
                            generated = (new StringBuilder(generated)).append("Leave its ambush on and send ").toString();
                        else
                            generated = (new StringBuilder(generated)).append("Send ").toString();
                        if(w.getTechs()[31].isOwned() && !w.upgradedCommander())
                        {
                            Chosen target = w.getCast()[(index - 3) / 4];
                            index = (index - 3) % 4;
                            generated = (new StringBuilder(generated)).append("it after ").append(target.getMainName()).append(".  Have the Thralls start by using ").toString();
                            if(index == 0)
                                generated = (new StringBuilder(generated)).append("Grind.").toString();
                            else
                            if(index == 1)
                                generated = (new StringBuilder(generated)).append("Caress.").toString();
                            else
                            if(index == 2)
                            {
                                if(w.tickle())
                                    generated = (new StringBuilder(generated)).append("Tickle.").toString();
                                else
                                    generated = (new StringBuilder(generated)).append("Pummel.").toString();
                            } else
                            if(index == 3)
                                generated = (new StringBuilder(generated)).append("Humiliate.").toString();
                        } else
                        {
                            generated = (new StringBuilder(generated)).append("it after ").append(w.getCast()[index].getMainName()).append(".").toString();
                        }
                    } else
                    {
                        generated = (new StringBuilder("Target ")).append(w.getCast()[index].getMainName()).toString();
                        if(w.getTechs()[3].isOwned())
                            generated = (new StringBuilder(generated)).append(" without bringing along a Commander.").toString();
                        else
                            generated = (new StringBuilder(generated)).append(".").toString();
                    }
                }
                w.writeCommentary(generated);
            }
        w.nextAction(action);
    }

    public static void pickStartingTarget(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        p.removeAll();
        w.append(t, "\n\n" + w.getSeparator() + "\n\nWhich of the Chosen will you target?");
        for(int i = 0; i < w.getCast().length; i++)
            if(w.getCast()[i] != null)
            {
                final int thisChosen = i;
                JButton thisOne = new JButton(w.getCast()[i].getMainName());
                thisOne.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.ConfirmBattle(t, p, f, w, w.getCast()[thisChosen]);
                    }
                });
                p.add(thisOne);
            }

        JButton Cancel = new JButton("Cancel");
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.Shop(t, p, f, w);
            }
        });
        p.add(Cancel);
        p.validate();
        p.repaint();
    }

    public static void ConfirmBattle(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Chosen c)
    {
        p.removeAll();
        Boolean immediateAction = false;
        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
        if(w.getTechs()[0].isOwned())
        {
            w.append(t, String.format("%1$s's ", c.mainName));
            c.printVulnerabilities(t, p, f, w);
            w.append(t, "\n\n");
        }
        if(w.getDay() == 50 - w.eventOffset * 3 || w.getTechs()[48].isOwned())
            w.append(t, "This will be the final battle.  When extermination is completed, instead of waiting for surrounded and captured allies to escape, the Chosen may sacrifice each other's lives in order to defeat you.  Victory requires neutralizing at least two of the three Chosen.\n\n");
        if(w.getBodyStatus()[0])
        {
            if(w.upgradedCommander() || !w.getTechs()[31].isOwned() || w.getBodyStatus()[2])
                w.printCommanderSummary(t, c);
            else
                immediateAction = true;
        } else
        if(w.usedForsaken != null)
        {
            int actualCost = w.usedForsaken.motivationCost();
            if(w.usedForsaken.isFormerFriend(w.getCast()[0]) || w.usedForsaken.isFormerFriend(w.getCast()[1]) || w.usedForsaken.isFormerFriend(w.getCast()[2]))
                actualCost *= 2;
            w.append(t, String.format("Commanding Forsaken: %1$s", w.usedForsaken.mainName) + "\nStamina: " + String.valueOf(w.usedForsaken.stamina / 10) + "." + String.valueOf(w.usedForsaken.stamina % 10) + "%\nMotivation: " + String.valueOf(w.usedForsaken.motivation / 10) + "." + String.valueOf(w.usedForsaken.motivation % 10) + "%\nCost: 20% Stamina, " + String.valueOf(actualCost / 10) + "." + String.valueOf(actualCost % 10) + "% Motivation, " + w.usedForsaken.EECost() + " EE\n" + w.usedForsaken.describeCombatStyle(w, false) + "\nReputation Strength: " + String.valueOf(200 - w.usedForsaken.disgrace * 2) + "%\nTarget Compatibilities:");
            for(int j = 0; j < 3; j++)
                if(w.getCast()[j] != null)
                {
                    w.append(t, "\n" + w.getCast()[j].getMainName() + " - ");
                    int compatibility = w.usedForsaken.compatibility(w.getCast()[j]);
                    if(w.usedForsaken.knowsPersonally(w.getCast()[j]))
                        w.append(t, "Personal (8 rounds, +25% damage)");
                    else
                    if(w.usedForsaken.obsessedWith(w.getCast()[j]))
                        w.append(t, "Obsessed (8 rounds, +25% damage)");
                    else
                    if(compatibility >= 8)
                        w.append(t, "Excellent (8 rounds)");
                    else
                    if(compatibility == 7)
                        w.append(t, "Good (7 rounds)");
                    else
                    if(compatibility == 6)
                        w.append(t, "Average (6 rounds)");
                    else
                    if(compatibility == 5)
                        w.append(t, "Poor (5 rounds)");
                    else
                        w.append(t, "Terrible (4 rounds)");
                }

            w.append(t, "\n\nYou will invade a neighborhood along " + c.getMainName() + "'s patrol path in order to lure " + c.himHer() + " in an attack " + c.himHer() + ".  " + w.usedForsaken.mainName + " will lie in wait, ready to engage " + c.himHer() + " in battle upon your order.");
        } else
        {
            w.append(t, String.format("You will invade a neighborhood along %1$s", c.getMainName()) + "'s patrol path in order to lure " + c.himHer() + " in and attack " + c.himHer() + ".");
        }
        if(immediateAction)
        {
            w.append(t, String.format("Which action do you want the Thralls to perform once they grab %1$s", c.himHer()) + "?");
            for(int i = 0; i < 4; i++)
            {
                final int type = i;
                String torment = "ERROR";
                if(i == 0)
                    torment = "Grind";
                else
                if(i == 1)
                    torment = "Caress";
                else
                if(i == 2)
                {
                    if(w.tickle())
                        torment = "Tickle";
                    else
                        torment = "Pummel";
                } else
                {
                    torment = "Humiliate";
                }
                final String finalTorment = torment;
                JButton Action = new JButton(torment);
                Action.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                        w.printCommanderSummary(t, c);
                        w.append(t, String.format("  The Thralls will begin by using %1$s", finalTorment) + " on " + c.himHer() + ".  Is this okay?");
                        JButton Confirm = new JButton("Confirm");
                        Confirm.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                if(w.usedForsaken != null || w.recordedCommanders.length < w.day - 1)
                                {
                                    w.commentaryRead = false;
                                    w.commentaryWrite = false;
                                }
                                if(w.getDay() > 1 && !w.isCheater() && (w.commentaryWrite || w.commentaryRead))
                                    w.archiveCommander(w.getDay());
                                Project.advanceDowntimeAction(p, w, w.getTechs().length + w.getCast().length + c.getNumber() * 4 + type);
                                if(type == 0)
                                    c.beginGrind();
                                else
                                if(type == 1)
                                    c.beginCaress();
                                else
                                if(type == 2)
                                    c.beginPummel();
                                else
                                if(type == 3)
                                    c.beginHumiliate();
                                if(w.getDay() == 50 - w.eventOffset * 3 || w.getTechs()[48].isOwned())
                                    Project.BeginFinalBattle(t, p, f, w, c);
                                else
                                    Project.BeginBattle(t, p, f, w, c);
                            }
                        });
                        p.add(Confirm);
                        JButton Cancel = new JButton("Cancel");
                        Cancel.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                Project.Shop(t, p, f, w);
                            }
                        });
                        p.add(Cancel);
                        p.validate();
                        p.repaint();
                    }
                });
                p.add(Action);
            }

        } else
        {
            w.append(t, "  Is this okay?");
            JButton Confirm = new JButton("Confirm");
            Confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(w.usedForsaken != null || w.recordedCommanders.length < w.day - 1)
                    {
                        w.commentaryRead = false;
                        w.commentaryWrite = false;
                    }
                    if(w.getDay() > 1 && !w.isCheater() && (w.commentaryRead || w.commentaryWrite))
                        w.archiveCommander(w.getDay());
                    Project.advanceDowntimeAction(p, w, w.getTechs().length + c.getNumber());
                    if(w.getDay() == 50 - w.eventOffset * 3 || w.getTechs()[48].isOwned())
                        Project.BeginFinalBattle(t, p, f, w, c);
                    else
                        Project.BeginBattle(t, p, f, w, c);
                }
            });
            p.add(Confirm);
        }
        JButton Cancel = new JButton("Cancel");
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.Shop(t, p, f, w);
            }
        });
        p.add(Cancel);
        p.validate();
        p.repaint();
    }

    public static void BeginBattle(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Chosen c)
    {
        w.incrementTotalRounds();
        Chosen newCombatants[] = new Chosen[3];
        newCombatants[0] = c;
        w.newCombat(w, newCombatants);
        if(w.getBodyStatus()[0] && !w.getBodyStatus()[2])
        {
            if(w.getTechs()[31].isOwned() && !w.upgradedCommander())
            {
                w.setBattleRound(0);
                c.BeSurrounded(t, p, f, w);
            } else
            {
                w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                w.append(t, String.format("You lure %1$s", c.getMainName()) + " into battle with an attack on a neighborhood along " + c.hisHer() + " patrol route.  ");
                if(w.upgradedCommander())
                {
                    w.append(t, "Then, you spring your ambush.  ");
                    c.startCaptured(t, w);
                } else
                {
                    w.append(t, "Then, with your Commander body on the battlefield, you set up an ambush.  ");
                    if(w.getCapturesPossible() > 0)
                        w.append(t, String.format("In the chaos, your body takes a serious injury, but with %1$s", c.getMainName()) + " surrounded, the battle has already progressed in the Demons' favor.");
                    else
                        w.append(t, String.format("In the chaos, your body is destroyed, but with %1$s", c.getMainName()) + " surrounded, the battle has already progressed in the Demons' favor.");
                    c.setSurrounded(w);
                    c.printSurroundedLine(t, w, c.getThisAttack());
                }
            }
        } else
        {
            w.append(t, "\n\n" + w.getSeparator() + "\n\n");
            w.append(t, "In the middle of a busy street, a horde of Demons suddenly erupts from underground");
            if(w.getBodyStatus()[0])
                w.append(t, ", led by the Commander body you're remotely controlling.");
            else
                w.append(t, "!");
            w.append(t, "  They begin attacking civilians and dragging them away for conversion to the Demonic cause, but before they can get too far, a thundering burst of light shines down on the scene!\n\n");
            c.say(t, "\"" + c.announcement() + "\"\n\n");
            c.transform(t, w);
        }
        w.append(t, "\n");
        PickTarget(t, p, f, w);
    }

    public static void BeginFinalBattle(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Chosen c)
    {
        w.incrementTotalRounds();
        Chosen newCombatants[] = new Chosen[3];
        newCombatants[0] = c;
        w.newCombat(w, newCombatants);
        if(w.getDay() == 50 - w.eventOffset * 3)
            w.append(t, "\n\n" + w.getSeparator() + "\n\nThe city's streets are devoid of life.  In preparation for the coming battle, the residents have been evacuated to temporary housing in the surrounding countryside.  The only ones who remain are the stubborn, the thrillseekers, some entrepreneurial journalists, and of course your minions.  They all know what's coming, and they're waiting for you to make your move.\n\nFinally, the silence is broken by the sound of shattering pavement.  An enormous, dark shape rises out of the ground, toppling buildings and sending tons of rubble spilling in all directions as it grows.  It's a gigantic pillar whose surface shimmers like an oil slick, and it continues upward until it dwarfs the skyscrapers below, penetrating the heavens themselves.  All throughout the city, space begins to warp and shift as you corrupt the fabric of reality and bend it to your will.\n\n" + c.getMainName() + " is the closest of the Chosen to the epicenter.  Although " + c.hisHer() + " instincts are telling " + c.himHer() + " to immediately begin drawing on as much energy as " + c.heShe() + " can, " + c.heShe() + " recalls from the strategy briefing that it will still take some time to evacuate the last few VIPs who had to stay until the last moment.  The neighboring cities will also need a chance to prepare for the destructive electromagnetic pulses that are likely to be released as the Chosen fight at full power.\n\n");
        else
            w.append(t, "\n\n" + w.getSeparator() + "\n\nThe city's streets are bustling as if this were a day like any other.  Its citizens have no idea how close your plans are to completion.\n\nWithout warning, the pavement of one of the main streets shatters and opens up.  An enormous, dark shape rises out of the ground, toppling buildings and sending tons of rubble spilling in all directions as it grows.  It's an enormous pillar whose surface shimmers like an oil slick, and it continues upward until it dwarfs the skyscrapers below, penetrating the heavens themselves.  All throughout the city, space begins to warp and shift as you corrupt the fabric of reality and bend it to your will.\n\n" + c.getMainName() + " is the closest of the Chosen to the epicenter.  Although " + c.hisHer() + " instincts are telling " + c.himHer() + " to immediately begin drawing on as much energy as " + c.heShe() + " can, " + c.heShe() + " has orders to restrain " + c.himHer() + "self until " + c.heShe() + "'s given clearance to go all-out.  Loudspeakers across the city broadcast instructions to the Chosen as they all hurry toward the tower, warning them that this will be the final battle and that they may not survive.  They're told to hold back at least until the most important VIPs can get a safe distance from the city.  It goes unsaid that the rest of the populace is considered an acceptable sacrifice.\n\n");
        w.finalBattleIntro(t, c);
        if(w.getBodyStatus()[0] && !w.getBodyStatus()[2])
            if(w.getTechs()[31].isOwned() && !w.upgradedCommander())
            {
                w.setBattleRound(0);
                c.BeSurrounded(t, p, f, w);
            } else
            {
                w.append(t, "\n\nBefore the Chosen can meet up with each other, you spring your ambush.  ");
                if(w.upgradedCommander())
                {
                    c.startCaptured(t, w);
                } else
                {
                    w.append(t, "Led by your Commander body, your minions emerge from their hiding places and rush in from all directions.  ");
                    if(w.getCapturesPossible() > 0)
                        w.append(t, String.format("In the chaos, your body takes a serious injury, but with %1$s", c.getMainName()) + " surrounded, the battle has already progressed in the Demons' favor.");
                    else
                        w.append(t, String.format("In the chaos, your body is destroyed, but with %1$s", c.getMainName()) + " surrounded, the battle has already progressed in the Demons' favor.");
                    c.setSurrounded(w);
                    c.printSurroundedLine(t, w, c.getThisAttack());
                }
            }
        w.append(t, "\n");
        PickTarget(t, p, f, w);
    }

    public static void DefeatScene(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        Chosen killed[] = new Chosen[2];
        Chosen fallen[] = new Chosen[2];
        Chosen escaped[] = new Chosen[3];
        for(int i = 0; i < 3; i++)
            if(!w.getCast()[i].alive)
            {
                if(killed[0] == null)
                    killed[0] = w.getCast()[i];
                else
                    killed[1] = w.getCast()[i];
            } else
            if(w.getCast()[i].resolve <= 0)
            {
                if(fallen[0] == null)
                    fallen[0] = w.getCast()[i];
                else
                    fallen[1] = w.getCast()[i];
            } else
            if(escaped[0] == null)
                escaped[0] = w.getCast()[i];
            else
            if(escaped[1] == null)
                escaped[1] = w.getCast()[i];
            else
                escaped[2] = w.getCast()[i];

        String as[];
        String as1[];
        String as2[];
        String as3[];
        String as4[];
        String as5[];
        String as6[];
        String as7[];
        String as8[];
        String as9[];
        String as10[];
        String as11[];
        String as12[];
        String as13[];
        if(escaped[2] != null)
        {
            w.append(t, "With the Demons all exterminated, there's nothing to stop the Chosen from launching their final maneuver.  They split up, flying in different directions across the city until they can barely see each other as tiny glowing specks against the darkening sky.  Then, they extend their arms toward each other, forming the points of an enormous triangle - with the Demonic spire at its center.\n\n");
            Chosen high = null;
            Chosen mid = null;
            Chosen low = null;
            for(int i = 0; i < 3; i++)
                if(w.getCast()[i].getConfidence() > 66)
                    high = w.getCast()[i];
                else
                if(w.getCast()[i].getConfidence() > 33)
                    mid = w.getCast()[i];
                else
                    low = w.getCast()[i];

            high.say(t, "\"");
            as = new String[5];
            as[0] = high.mainName;
            as[1] = mid.mainName;
            as[2] = low.mainName;
            changePortrait(high.convertGender(), high.type, false, false, w, as, 0, Emotion.FOCUS, Emotion.FOCUS);
            as1 = new String[5];
            as1[0] = high.mainName;
            as1[1] = mid.mainName;
            as1[2] = low.mainName;
            changePortrait(mid.convertGender(), mid.type, false, false, w, as1, 1, Emotion.FOCUS, Emotion.FOCUS);
            as2 = new String[5];
            as2[0] = high.mainName;
            as2[1] = mid.mainName;
            as2[2] = low.mainName;
            changePortrait(low.convertGender(), low.type, false, false, w, as2, 2, Emotion.FOCUS, Emotion.FOCUS);
            if(w.getRelationship(high.getNumber(), mid.getNumber()) >= 0)
            {
                if(w.getRelationship(high.getNumber(), low.getNumber()) >= 0)
                {
                    high.say(t, "Let's do it!  Just like we practiced!\"\n\n");
                    mid.say(t, "\"");
                    if(w.getRelationship(mid.getNumber(), low.getNumber()) >= 0)
                    {
                        mid.say(t, "I'm ready!  You okay, " + low.getMainName() + "!?\"\n\n");
                        low.say(t, "\"R-Ready here too!");
                    } else
                    {
                        as3 = new String[5];
                        as3[0] = high.mainName;
                        as3[1] = mid.mainName;
                        as3[2] = low.mainName;
                        changePortrait(mid.convertGender(), mid.type, false, false, w, as3, 1, Emotion.ANGER, Emotion.ANGER);
                        mid.say(t, "I'm ready!  You'd better not screw it up for us, " + low.getMainName() + "!\"\n\n");
                        low.say(t, "\"I-I won't let you down, " + high.getMainName() + "!");
                    }
                } else
                {
                    as4 = new String[5];
                    as4[0] = high.mainName;
                    as4[1] = mid.mainName;
                    as4[2] = low.mainName;
                    changePortrait(high.convertGender(), high.type, false, false, w, as4, 0, Emotion.ANGER, Emotion.ANGER);
                    high.say(t, "You'd better not screw this up, " + low.getMainName() + "!\"\n\n");
                    mid.say(t, "\"");
                    if(w.getRelationship(mid.getNumber(), low.getNumber()) >= 0)
                    {
                        mid.say(t, "Don't worry, " + low.getMainName() + "!  I believe in you!\"\n\n");
                        low.say(t, "\"R-Right!");
                    } else
                    {
                        as5 = new String[5];
                        as5[0] = high.mainName;
                        as5[1] = mid.mainName;
                        as5[2] = low.mainName;
                        changePortrait(low.convertGender(), low.type, false, false, w, as5, 2, Emotion.ANGER, Emotion.ANGER);
                        mid.say(t, "Don't worry, we can finish this without " + low.himHer() + " if we have to!\"\n\n");
                        low.say(t, "\"I-I'm fine, just worry about yourselves!");
                    }
                }
            } else
            {
                as6 = new String[5];
                as6[0] = high.mainName;
                as6[1] = mid.mainName;
                as6[2] = low.mainName;
                changePortrait(high.convertGender(), high.type, false, false, w, as6, 0, Emotion.ANGER, Emotion.ANGER);
                if(w.getRelationship(high.getNumber(), low.getNumber()) >= 0)
                {
                    high.say(t, "You'd better not screw this up, " + mid.getMainName() + "!\"\n\n");
                    as7 = new String[5];
                    as7[0] = high.mainName;
                    as7[1] = mid.mainName;
                    as7[2] = low.mainName;
                    changePortrait(mid.convertGender(), mid.type, false, false, w, as7, 1, Emotion.ANGER, Emotion.ANGER);
                    mid.say(t, "\"");
                    if(w.getRelationship(mid.getNumber(), low.getNumber()) >= 0)
                    {
                        mid.say(t, "Worry about yourself, " + high.getMainName() + "!\"\n\n");
                        low.say(t, "\"P-Please, you two, we shouldn't be fighting each other now of all times!");
                        as8 = new String[5];
                        as8[0] = high.mainName;
                        as8[1] = mid.mainName;
                        as8[2] = low.mainName;
                        changePortrait(low.convertGender(), low.type, false, false, w, as8, 2, Emotion.FEAR, Emotion.FEAR);
                    } else
                    {
                        mid.say(t, "You know that if anyone's going to screw up here, it's " + low.getMainName() + "!\"\n\n");
                        low.say(t, "\"I-I won't!  I'm ready, " + high.getMainName() + "!");
                    }
                } else
                {
                    high.say(t, "You two had better not screw this up!\"\n\n");
                    mid.say(t, "\"");
                    if(w.getRelationship(mid.getNumber(), low.getNumber()) >= 0)
                    {
                        mid.say(t, "We definitely won't!  Right, " + mid.getMainName() + "!?\"\n\n");
                        low.say(t, "\"R-Right!");
                    } else
                    {
                        mid.say(t, "I definitely won't, though I'm not sure about " + mid.getMainName() + "...\"\n\n");
                        low.say(t, "\"I-I'm fine!  So let's do this!");
                    }
                }
            }
            low.say(t, "\"\n\n");
            w.append(t, "The three Chosen concentrate, and the space between their outstretched hands briefly shimmers like a pane of glass.  A sharp glint cuts across the city, and suddenly the black tower is severed at its base.  It topples downward, striking the ground with a great rumble and a cloud of dust.  As quickly as that, the Demonic presence over the city lifts.\n\nThe battle is over.  But even though this Demon Lord has been defeated, the scars left on the hearts of the Chosen won't heal so easily.  Their troubles may be just beginning...");
        } else
        if(escaped[1] != null)
        {
            Chosen first = escaped[0];
            Chosen second = escaped[1];
            if(second.getConfidence() > first.getConfidence())
            {
                first = escaped[1];
                second = escaped[0];
            }
            if(fallen[0] != null)
                w.append(t, String.format("With %1$s", fallen[0].getMainName()) + "'s defeat, a powerful evil energy is gathering on the battlefield.  The tip of the Demonic spire begins to glow, preparing to release one final pulse of corruption that will cement your domination over this region of reality.  However, at the same time, " + escaped[0].getMainName() + " and " + escaped[1].getMainName() + " have cleared out the last of your Demonic forces, and they're ready to launch their counterattack.");
            else
                w.append(t, String.format("With every moment that passes, the Demonic spire grows upward, gathering power and deepening your domination over this region of reality.  However, by sacrificing %1$s", killed[0].getMainName()) + "'s life, " + escaped[0].getMainName() + " and " + escaped[1].getMainName() + " have exterminated all the surrounding Demons, and they're ready to launch their counterattack.");
            as9 = new String[5];
            as9[0] = first.mainName;
            as9[1] = second.mainName;
            changePortrait(first.convertGender(), first.type, false, false, w, as9, 0, Emotion.FOCUS, Emotion.FOCUS);
            if(w.getRelationship(first.getNumber(), second.getNumber()) >= 0)
            {
                as10 = new String[5];
                as10[0] = first.mainName;
                as10[1] = second.mainName;
                changePortrait(second.convertGender(), second.type, false, false, w, as10, 1, Emotion.FOCUS, Emotion.FOCUS);
                first.say(t, "\n\n\"If we work together, I think we can still stop it!  Back me up!\"\n\n");
                second.say(t, "\"Got it!  I'm right behind you!\"\n\n");
                w.append(t, String.format("%1$s charges forward, blazing brighter than the sun as ", first.getMainName()) + first.heShe() + " draws on as much psychic energy as " + first.heShe() + " can.  " + second.getMainName() + " has a hand on " + first.hisHer() + " shoulder, pushing " + first.himHer() + " forward as they fly together.  The two of them blast through the base of the tower, leaving an enormous hole behind them.  And with its lower structure compromised, the shaft begins to topple to one side.  It lands on the city with a deafening crash, kicking up a huge cloud of debris.  As quickly as that, the Demonic presence over the city lifts.\n\nThe battle is over.  But even though this Demon Lord has been defeated, the scars left on the hearts of the Chosen won't heal so easily.  ");
            } else
            {
                as11 = new String[5];
                as11[0] = first.mainName;
                as11[1] = second.mainName;
                changePortrait(second.convertGender(), second.type, false, false, w, as11, 1, Emotion.FEAR, Emotion.FEAR);
                first.say(t, "\n\n\"If we're going to take that thing down, we need to go all-out!  Don't hold back, or you'll die!\"\n\n");
                second.say(t, "\"Huh?  Gaaah!  Ergh... you're... crazy...!\"\n\n");
                w.append(t, String.format("%1$s holds out one palm to shoot a beam of crackling destructive energy directly at ", first.getMainName()) + second.getMainName() + ".  For " + second.hisHer() + " part, " + second.getMainName() + " barely reacts in time to intercept the beam with " + second.hisHer() + " own blast.  The glowing line between " + first.getMainName() + "'s hand and " + second.getMainName() + "'s annihilates everything it touches as the two of them run toward the Demonic spire.  When it cuts into the base of the tower, the opposing energies cause a huge explosion that throws the two Chosen in different directions.  After they've come to their senses, they see the structure beginning to tilt to one side.  It finally topples, throwing up a huge cloud of debris as it lands on the city below.  As quickly as that, the Demonic presence over the city lifts.\n\nThe battle is over.  But even though this Demon Lord has been defeated, the scars left on the hearts of the Chosen won't heal so easily.  ");
            }
            if(fallen[0] != null)
                w.append(t, String.format("%1$s and ", first.getMainName()) + second.getMainName() + " have their own troubles to deal with, and " + fallen[0].getMainName() + " is nowhere to be found...");
            else
                w.append(t, "Their troubles may be just beginning...");
        } else
        {
            w.append(t, String.format("After exterminating the intervening Demons, %1$s", escaped[0].getMainName()) + " attacks the Demonic spire, drawing on as much power as " + escaped[0].heShe() + " can in an attempt to destroy it.  ");
            if(fallen[1] != null)
                w.append(t, String.format("But with the other two Chosen having succumbed to the Demons, %1$s", escaped[0].heShe()) + "'s finding that " + escaped[0].heShe() + " isn't able to make a dent in it on " + escaped[0].hisHer() + " own.");
            else
            if(killed[1] != null)
                w.append(t, String.format("But as the only survivor among the three Chosen, %1$s", escaped[0].heShe()) + "'s finding that " + escaped[0].heShe() + " isn't able to make a dent in it on " + escaped[0].hisHer() + " own.");
            else
                w.append(t, String.format("But with %1$s", killed[0].getMainName()) + " dead and " + fallen[0].getMainName() + " having succumbed to the Demons, " + escaped[0].getMainName() + " is finding that " + escaped[0].heShe() + " isn't able to make a dent in it on " + escaped[0].hisHer() + " own.");
            escaped[0].say(t, "\n\n\"");
            as12 = new String[5];
            as12[0] = escaped[0].mainName;
            changePortrait(escaped[0].convertGender(), escaped[0].type, false, false, w, as12, 0, Emotion.SHAME, Emotion.SHAME);
            if(escaped[0].getConfidence() > 66)
            {
                as13 = new String[5];
                as13[0] = escaped[0].mainName;
                changePortrait(escaped[0].convertGender(), escaped[0].type, false, false, w, as13, 0, Emotion.STRUGGLE, Emotion.STRUGGLE);
                escaped[0].say(t, "No!  I... I should be strong enough...!");
            } else
            if(escaped[0].getConfidence() > 33)
                escaped[0].say(t, "Ugh...  We were so close...");
            else
                escaped[0].say(t, "I'm... I'm too weak after all...");
            escaped[0].say(t, "\"\n\n");
            if(escaped[0].isDrained())
                w.append(t, String.format("%1$s falls to ", escaped[0].getMainName()) + escaped[0].hisHer() + " knees, overwhelmed by despair.  " + escaped[0].HeShe() + " spots a nearby knife discarded by a Thrall and takes it in " + escaped[0].hisHer() + " hands.  Before the fight, " + escaped[0].heShe() + " was worried about hurting " + escaped[0].himHer() + "self too badly to fight, but not badly enough to actually be fatal.  But now, " + escaped[0].heShe() + " has nothing left to lose...");
            else
            if(escaped[0].isParasitized())
            {
                w.append(t, String.format("%1$s turns and flies away, fleeing the battle.  However, ", escaped[0].getMainName()) + escaped[0].hisHer() + " clothes begin to flicker and fade, and " + escaped[0].heShe() + " has a harder and harder time staying airborne.  Combined with " + escaped[0].hisHer() + " damaged reputation, this last failure has proven fatal for " + escaped[0].hisHer() + " connection to the public's psychic energy.  By the time " + escaped[0].heShe() + " reaches the neighboring city, " + escaped[0].heShe() + "'ll return to being nothing more than ");
                if(!escaped[0].getMainName().equals(escaped[0].getGivenName()))
                    w.append(t, String.format("%1$s, ", escaped[0].getGivenName()));
                w.append(t, "a typical powerless human.");
            } else
            if(escaped[0].isImpregnated())
                w.append(t, String.format("%1$s turns and flies away, fleeing the battle.  ", escaped[0].getMainName()) + escaped[0].HeShe() + " has no intention of returning to the military and reporting " + escaped[0].hisHer() + " failure, because " + escaped[0].heShe() + " knows that soon, " + escaped[0].hisHer() + " Demonic pregnancy will begin to show.  " + escaped[0].HisHer() + " life as one of the Chosen is over, and " + escaped[0].hisHer() + " life as a fugitive begins...");
            else
            if(escaped[0].isHypnotized())
                w.append(t, String.format("As much as it pains %1$s", escaped[0].himHer()) + " to do so, " + escaped[0].getMainName() + " turns and flees.  This battle may be lost, but " + escaped[0].heShe() + "'s determined to escape and survive to fight another day.  However, even after " + escaped[0].heShe() + " escapes the range of your influence, your post-hypnotic commands continue to linger in " + escaped[0].hisHer() + " subconscious.  It remains to be seen what sort of depravity " + escaped[0].heShe() + "'ll get into...");
            else
                w.append(t, String.format("With no other options remaining, %1$s", escaped[0].getMainName()) + " turns to flee.");
        }
        EndFinalBattle(t, p, f, w);
    }

    public static void EndFinalBattle(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        Chosen escaped = null;
        Chosen defeated[] = new Chosen[3];
        int numberDefeated = 0;
        int numberKilled = 0;
        for(int i = 0; i < 3; i++)
            if(!w.getCast()[i].alive)
                numberKilled++;
            else
            if(w.getCast()[i].resolve <= 0)
            {
                defeated[numberDefeated] = w.getCast()[i];
                numberDefeated++;
            } else
            {
                escaped = w.getCast()[i];
            }

        if(numberKilled + numberDefeated == 2)
        {
            int type = 0;
            if(escaped.isDrained())
                type = 3;
            else
            if(escaped.isImpregnated())
                type = 1;
            else
            if(escaped.isParasitized())
                type = 4;
            else
            if(escaped.isHypnotized())
                type = 2;
            final int sceneShown = type;
            final Chosen sceneSubject = escaped;
            if(type != 0)
            {
                p.removeAll();
                JButton Continue = new JButton("Continue");
                Continue.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, "\n\n" + w.getSeparator() + "\n\n");
                        w.ending(t, sceneShown, sceneSubject, null, null);
                        Project.ReportScore(t, p, f, w);
                    }
                });
                p.add(Continue);
                p.validate();
                p.repaint();
            } else
            {
                ReportScore(t, p, f, w);
            }
        } else
        {
            ReportScore(t, p, f, w);
        }
    }

    public static void ReportScore(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        p.removeAll();
        JButton Continue = new JButton("Continue");
        Continue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                int forsaken = 0;
                int casualties = 0;
                int returning = 0;
                Chosen corrupted[] = new Chosen[3];
                Chosen killed[] = new Chosen[3];
                Chosen escaped[] = new Chosen[3];
                for(int i = 0; i < 3; i++)
                    if(w.getCast()[i].alive && w.getCast()[i].resolve <= 0)
                    {
                        corrupted[forsaken] = w.getCast()[i];
                        forsaken++;
                    } else
                    if(w.getCast()[i].alive)
                    {
                        escaped[returning] = w.getCast()[i];
                        returning++;
                    } else
                    {
                        killed[casualties] = w.getCast()[i];
                        casualties++;
                    }

                if(!w.isCheater() && w.hardMode)
                    w.scoreSummary(t);
                w.finalBattle = false;
                w.getTechs()[48].owned = false;
                for(int i = 0; i < 3; i++)
                {
                    w.getCast()[i].alive = true;
                    w.getCast()[i].resolve = 100;
                }

                w.incrementDay();
                w.clearCommander();
                for(int i = 0; i < 3; i++)
                {
                    w.getCast()[i].addTrauma();
                    w.getCast()[i].surrounded = false;
                    w.getCast()[i].captured = false;
                    w.getCast()[i].removeSurround = false;
                }

                if(forsaken + casualties >= 2 || !w.campaign)
                {
                    JButton ContinueFour = new JButton("Continue Playing");
                    ContinueFour.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            Project.Shop(t, p, f, w);
                        }
                    });
                    if(w.campaign)
                        ContinueFour.setText("Continue");
                    p.add(ContinueFour);
                }
                if((w.isCheater() || !w.hardMode) && !w.campaign)
                    w.append(t, "\n\n" + w.getSeparator() + "\n\nI hope you enjoyed this playthrough of Corrupted Saviors!  For an even greater challenge, consider trying Hard Mode or Campaign Mode!");
                if(w.campaign)
                    if(forsaken + casualties >= 2)
                    {
                        w.append(t, "\n\n" + w.getSeparator() + "\n\nThe city of " + w.cityName + " has fallen under your control.  ");
                        if(w.day < 50 - w.eventOffset)
                            w.append(t, "Your followers won't be able to establish a foothold in another city right away, so you can take some time to consolidate power here and enjoy your conquest.");
                        else
                            w.append(t, "Now all that remains is to decide where to strike next.");
                        w.loopComplete = true;
                    } else
                    {
                        w.append(t, "\n\n" + w.getSeparator() + "\n\nWith your defeat in " + w.cityName + ", the momentum of your advance across the globe has been halted.  However, so long as there is darkness within the human heart, a Demon Lord cannot be truly killed.  Before long, you will rise again.");
                        if(w.conquered.length > 0 || w.sacrificed.length > 0)
                            w.append(t, "  And in the meantime, there's much enjoyment to be had from your conquests...");
                    }
                if(forsaken > 0)
                {
                    w.append(t, "\n\n");
                    if(forsaken == 1)
                        w.append(t, String.format("%1$s has ", corrupted[0].getMainName()));
                    else
                    if(forsaken == 2)
                        w.append(t, String.format("%1$s and ", corrupted[0].getMainName()) + corrupted[1].getMainName() + " have ");
                    else
                        w.append(t, String.format("%1$s, ", corrupted[0].getMainName()) + corrupted[1].getMainName() + ", and " + corrupted[2].getMainName() + " have ");
                    if(!w.campaign)
                        w.append(t, "been added to the ranks of the Forsaken!  You can interact with them from the Main Menu, and you may also use them to help corrupt new Chosen in future playthroughs!");
                    else
                    if(forsaken + casualties >= 2)
                        w.append(t, "been added to the ranks of the Forsaken!  This will surely prove useful against the Chosen you've yet to face.");
                    else
                        w.append(t, "been added to the ranks of the Forsaken!  This makes for a fine consolation prize.");
                    String path = Project.class.getProtectionDomain().getCodeSource().getLocation().getPath();
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
                    path = path.replaceAll(File.separator + "u0020", File.separator + " ");
                    File saveLocation = new File(path + File.separator + "saves.sav");
                    SaveData saves = null;
                    if(saveLocation.exists())
                    {
                        ReadObject robj = new ReadObject();
                        saves = robj.deserializeSaveData(path + File.separator + "saves.sav");
                    } else
                    {
                        saves = new SaveData();
                    }
                    for(int i = 0; i < saves.getSaves().length; i++)
                        saves.getSaves()[i].repairSave();

                    WriteObject wobj = new WriteObject();
                    SaveData saveFile = saves;
                    for(int i = 0; i < 3; i++)
                        if(forsaken > i)
                        {
                            int index = 0;
                            if(!w.campaign && saves.harem == null)
                                saves.harem = new Forsaken[1];
                            else
                                index = w.getHarem().length;
                            Forsaken newHarem[] = new Forsaken[index + 1];
                            for(int j = 0; j < index; j++)
                                newHarem[j] = w.getHarem()[j];

                            Forsaken newForsaken = new Forsaken();
                            newForsaken.initialize(w, corrupted[i]);
                            newHarem[index] = newForsaken;
                            newHarem[index].forsakenID = saves.assignID();
                            newHarem[index].otherChosen = new Chosen[2 + corrupted[i].formerPartners.length];
                            newHarem[index].chosenRelations = new Forsaken.Relationship[2 + corrupted[i].formerPartners.length];
                            for(int j = 0; j < 3 + corrupted[i].formerPartners.length; j++)
                                if(j < 3)
                                {
                                    if(j < corrupted[i].number)
                                    {
                                        newHarem[index].otherChosen[j] = w.getCast()[j];
                                        newHarem[index].chosenRelations[j] = Forsaken.Relationship.PARTNER;
                                    } else
                                    if(j > corrupted[i].number)
                                    {
                                        newHarem[index].otherChosen[j - 1] = w.getCast()[j];
                                        newHarem[index].chosenRelations[j - 1] = Forsaken.Relationship.PARTNER;
                                    }
                                } else
                                {
                                    newHarem[index].otherChosen[j - 1] = corrupted[i].formerPartners[j - 3];
                                    newHarem[index].chosenRelations[j - 1] = Forsaken.Relationship.PARTNER;
                                }

                            newHarem[index].formerPartners = new Chosen[corrupted[i].formerPartners.length + 2];
                            newHarem[index].formerFriendships = new int[corrupted[i].formerRelationships.length + 2];
                            for(int j = 0; j < newHarem[index].formerPartners.length + 1; j++)
                                if(j < 3)
                                {
                                    if(j < corrupted[i].number)
                                    {
                                        newHarem[index].formerPartners[j] = w.getCast()[j];
                                        newHarem[index].formerFriendships[j] = w.getRelationship(corrupted[i].number, j);
                                    } else
                                    if(j > corrupted[i].number)
                                    {
                                        newHarem[index].formerPartners[j - 1] = w.getCast()[j];
                                        newHarem[index].formerFriendships[j - 1] = w.getRelationship(corrupted[i].number, j);
                                    }
                                } else
                                {
                                    newHarem[index].formerPartners[j - 1] = corrupted[i].formerPartners[j - 3];
                                    newHarem[index].formerFriendships[j - 1] = corrupted[i].formerRelationships[j - 3];
                                }

                            newHarem[index].kills = corrupted[i].kills;
                            newHarem[index].killRelationships = corrupted[i].killRelationships;
                            if(i == 1)
                            {
                                newForsaken.others = (new Forsaken[] {
                                    newHarem[index - 1]
                                });
                                newForsaken.forsakenRelations = (new Forsaken.Relationship[] {
                                    Forsaken.Relationship.PARTNER
                                });
                                newForsaken.troublemaker = new int[1];
                                newHarem[index - 1].others = (new Forsaken[] {
                                    newForsaken
                                });
                                newHarem[index - 1].forsakenRelations = (new Forsaken.Relationship[] {
                                    Forsaken.Relationship.PARTNER
                                });
                                newHarem[index - 1].troublemaker = new int[1];
                            } else
                            if(i == 2)
                            {
                                newForsaken.others = (new Forsaken[] {
                                    newHarem[index - 1], newHarem[index - 2]
                                });
                                newForsaken.forsakenRelations = (new Forsaken.Relationship[] {
                                    Forsaken.Relationship.PARTNER, Forsaken.Relationship.PARTNER
                                });
                                newForsaken.troublemaker = new int[2];
                                newHarem[index - 1].others = (new Forsaken[] {
                                    newForsaken, newHarem[index - 2]
                                });
                                newHarem[index - 1].forsakenRelations = (new Forsaken.Relationship[] {
                                    Forsaken.Relationship.PARTNER, Forsaken.Relationship.PARTNER
                                });
                                newHarem[index - 1].troublemaker = new int[2];
                                newHarem[index - 2].others = (new Forsaken[] {
                                    newForsaken, newHarem[index - 1]
                                });
                                newHarem[index - 2].forsakenRelations = (new Forsaken.Relationship[] {
                                    Forsaken.Relationship.PARTNER, Forsaken.Relationship.PARTNER
                                });
                                newHarem[index - 2].troublemaker = new int[2];
                            }
                            for(int j = 0; j < w.getHarem().length; j++)
                            {
                                for(int k = 0; k < w.getHarem()[j].otherChosen.length; k++)
                                {
                                    Boolean alreadyThere = false;
                                    for(int m = 0; m < w.getHarem()[j].others.length; m++)
                                        if(newForsaken.equals(w.getHarem()[j].others[m]))
                                            alreadyThere = true;

                                    if(!alreadyThere && w.getHarem()[j].otherChosen[k].equals(corrupted[i]))
                                    {
                                        Forsaken newOtherForsaken[] = new Forsaken[w.getHarem()[j].others.length + 1];
                                        Forsaken.Relationship newForsakenRelations[] = new Forsaken.Relationship[w.getHarem()[j].forsakenRelations.length + 1];
                                        for(int m = 0; m < w.getHarem()[j].others.length; m++)
                                        {
                                            newOtherForsaken[m] = w.getHarem()[j].others[m];
                                            newForsakenRelations[m] = w.getHarem()[j].forsakenRelations[m];
                                        }

                                        newOtherForsaken[newOtherForsaken.length - 1] = newForsaken;
                                        newForsakenRelations[newForsakenRelations.length - 1] = w.getHarem()[j].chosenRelations[k];
                                        w.getHarem()[j].others = newOtherForsaken;
                                        w.getHarem()[j].forsakenRelations = newForsakenRelations;
                                        Chosen newOtherChosen[] = new Chosen[w.getHarem()[j].otherChosen.length - 1];
                                        Forsaken.Relationship newChosenRelations[] = new Forsaken.Relationship[w.getHarem()[j].chosenRelations.length - 1];
                                        for(int m = 0; m < w.getHarem()[j].otherChosen.length; m++)
                                            if(m < k)
                                            {
                                                newOtherChosen[m] = w.getHarem()[j].otherChosen[m];
                                                newChosenRelations[m] = w.getHarem()[j].chosenRelations[m];
                                            } else
                                            if(m > k)
                                            {
                                                newOtherChosen[m - 1] = w.getHarem()[j].otherChosen[m];
                                                newChosenRelations[m - 1] = w.getHarem()[j].chosenRelations[m];
                                            }

                                        w.getHarem()[j].otherChosen = newOtherChosen;
                                        w.getHarem()[j].chosenRelations = newChosenRelations;
                                    }
                                }

                            }

                            if(w.campaign)
                            {
                                w.conquered = newHarem;
                            } else
                            {
                                saves.harem = newHarem;
                                w.save = saves;
                            }
                        }

                    wobj.serializeSaveData(saves);
                    final SaveData fileUsed = saves;
                    if(!w.campaign)
                    {
                        JButton ForsakenMenu = new JButton("Forsaken Menu");
                        ForsakenMenu.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                WorldState x = new WorldState();
                                x.initialize();
                                x.copySettings(t, w);
                                x.copyToggles(w);
                                x.setGenders(w.getGenderBalance());
                                x.save = w.save;
                                Project.ForsakenMenu(t, p, f, x, fileUsed, 0);
                            }
                        });
                        p.add(ForsakenMenu);
                    }
                }
                if(w.campaign)
                {
                    int numberDiscarded = 0;
                    int numberKept = 0;
                    Chosen discarded[] = new Chosen[3];
                    Chosen kept[] = new Chosen[3];
                    for(int i = 0; i < 3; i++)
                        if(escaped[i] != null)
                            if(escaped[i].impregnated || escaped[i].hypnotized || escaped[i].drained || escaped[i].parasitized)
                            {
                                discarded[numberDiscarded] = escaped[i];
                                numberDiscarded++;
                            } else
                            {
                                kept[numberKept] = escaped[i];
                                numberKept++;
                            }

                    if(numberKept > 0)
                    {
                        Chosen newReturning[] = new Chosen[w.returning.length + numberKept];
                        for(int i = 0; i < w.returning.length; i++)
                            newReturning[i] = w.returning[i];

                        for(int i = 0; i < numberKept; i++)
                            newReturning[w.returning.length + i] = kept[i];

                        w.returning = newReturning;
                    }
                    if(numberDiscarded > 0)
                    {
                        Chosen newDeceased[] = new Chosen[w.deceased.length + numberDiscarded];
                        for(int i = 0; i < w.deceased.length; i++)
                            newDeceased[i] = w.deceased[i];

                        for(int i = 0; i < numberDiscarded; i++)
                            newDeceased[w.deceased.length + i] = discarded[i];

                        w.deceased = newDeceased;
                    }
                    if(forsaken > 0)
                    {
                        Chosen newFormerChosen[] = new Chosen[w.formerChosen.length + forsaken];
                        for(int i = 0; i < w.formerChosen.length; i++)
                            newFormerChosen[i] = w.formerChosen[i];

                        for(int i = 0; i < forsaken; i++)
                            newFormerChosen[w.formerChosen.length + i] = corrupted[i];

                        w.formerChosen = newFormerChosen;
                    }
                }
                if(w.campaign && forsaken + casualties < 2)
                    if(w.conquered.length > 0 || w.sacrificed.length > 0)
                    {
                        w.append(t, "\n\n");
                        Project.WrapUpCampaign(t, p, f, w, null, null);
                    } else
                    {
                        w.append(t, "\n\nThank you for playing the campaign mode of Corrupted Saviors!  If you're having trouble with the game, consider trying out Single Play mode, which allows the use of cheats after the final battle regardless of the result.");
                        JButton Continue = new JButton("Main Menu");
                        Continue.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                WorldState x = new WorldState();
                                x.initialize();
                                x.copySettings(t, w);
                                x.copyToggles(w);
                                x.setGenders(w.getGenderBalance());
                                x.save = w.save;
                                Project.IntroOne(t, p, f, x);
                            }
                        });
                        p.add(Continue);
                    }
                p.validate();
                p.repaint();
            }
        });
        p.add(Continue);
        p.validate();
        p.repaint();
    }

    public static void WrapUpCampaign(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, Boolean broughtConquered[], Boolean broughtSacrificed[])
    {
        p.removeAll();
        final int conquered = w.conquered.length;
        final int sacrificed = w.sacrificed.length;
        if(broughtConquered == null || broughtSacrificed == null)
        {
            broughtConquered = new Boolean[conquered];
            for(int i = 0; i < conquered; i++)
                broughtConquered[i] = true;

            broughtSacrificed = new Boolean[sacrificed];
            for(int i = 0; i < sacrificed; i++)
                broughtSacrificed[i] = true;

        } else
        {
            w.append(t, "\n\n" + w.getSeparator() + "\n\n");
        }
        if(conquered > 0 && sacrificed > 0)
            w.append(t, "You can bring your Forsaken with you to use in Single Play mode, including the Forsaken who were disposed of during play.");
        else
        if(sacrificed > 0)
            w.append(t, "Even though you didn't end the game with any Forsaken, you can still bring with you the ones you disposed of during play.");
        else
            w.append(t, "You can bring your Forsaken with you to use in Single Play mode.");
        Boolean saveAllConquered = false;
        Boolean deleteAllConquered = false;
        Boolean saveAllSacrificed = false;
        Boolean deleteAllSacrificed = false;
        if(conquered > 0)
        {
            w.append(t, "\n\nCurrent Forsaken\n");
            for(int i = 0; i < conquered; i++)
            {
                w.append(t, "\n" + w.conquered[i].mainName + ": ");
                if(broughtConquered[i])
                {
                    w.greenAppend(t, "SAVE");
                    deleteAllConquered = true;
                } else
                {
                    w.redAppend(t, "DELETE");
                    saveAllConquered = true;
                }
            }

        }
        if(sacrificed > 0)
        {
            w.append(t, "\n\nFormer Forsaken\n");
            for(int i = 0; i < sacrificed; i++)
            {
                w.append(t, "\n" + w.sacrificed[i].mainName + ": ");
                if(broughtSacrificed[i])
                {
                    w.greenAppend(t, "SAVE");
                    deleteAllSacrificed = true;
                } else
                {
                    w.redAppend(t, "DELETE");
                    saveAllSacrificed = true;
                }
            }

        }
        final Boolean conqueredSetting[] = broughtConquered;
        final Boolean sacrificedSetting[] = broughtSacrificed;
        if(saveAllConquered)
        {
            JButton Save = new JButton("Save All Current");
            Save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Boolean newSaved[] = new Boolean[conquered];
                    for(int i = 0; i < conquered; i++)
                        newSaved[i] = true;

                    Project.WrapUpCampaign(t, p, f, w, newSaved, sacrificedSetting);
                }
            });
            p.add(Save);
        }
        if(deleteAllConquered)
        {
            JButton Delete = new JButton("Delete All Current");
            Delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Boolean newSaved[] = new Boolean[conquered];
                    for(int i = 0; i < conquered; i++)
                        newSaved[i] = false;

                    Project.WrapUpCampaign(t, p, f, w, newSaved, sacrificedSetting);
                }
            });
            p.add(Delete);
        }
        if(saveAllSacrificed)
        {
            JButton Save = new JButton("Save All Former");
            Save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Boolean newSaved[] = new Boolean[sacrificed];
                    for(int i = 0; i < sacrificed; i++)
                        newSaved[i] = true;

                    Project.WrapUpCampaign(t, p, f, w, conqueredSetting, newSaved);
                }
            });
            p.add(Save);
        }
        if(deleteAllSacrificed)
        {
            JButton Save = new JButton("Delete All Former");
            Save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Boolean newSaved[] = new Boolean[sacrificed];
                    for(int i = 0; i < sacrificed; i++)
                        newSaved[i] = false;

                    Project.WrapUpCampaign(t, p, f, w, conqueredSetting, newSaved);
                }
            });
            p.add(Save);
        }
        JButton Decide = new JButton("Decide Individually");
        Decide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Project.DecideKeptForsaken(t, p, f, w, conqueredSetting, sacrificedSetting, 0);
            }
        });
        p.add(Decide);
        JButton Done = new JButton("Done");
        Done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                w.append(t, "\n\n" + w.getSeparator() + "\n\nAre you sure?  ");
                int brought = 0;
                int deleted = 0;
                for(int i = 0; i < conquered; i++)
                    if(conqueredSetting[i])
                        brought++;
                    else
                        deleted++;

                for(int i = 0; i < sacrificed; i++)
                    if(sacrificedSetting[i])
                        brought++;
                    else
                        deleted++;

                if(deleted == 0)
                    w.append(t, "All Forsaken from this playthrough will be added to the save file.");
                else
                if(brought == 0)
                    w.append(t, "All Forsaken from this playthrough will be deleted and can only be recovered by loading an old campaign save.");
                else
                    w.append(t, String.format("%1$s Forsaken from this playthrough will be added to the save file and the other ", String.valueOf(brought)) + deleted + " will be deleted.  Deleted Forsaken can only be recovered by loading an old campaign save.");
                final int totalBrought = brought;
                JButton Confirm = new JButton("Confirm");
                Confirm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        if(totalBrought > 0)
                        {
                            if(w.save.harem == null)
                                w.save.harem = new Forsaken[0];
                            Forsaken newHarem[] = new Forsaken[w.save.harem.length + totalBrought];
                            for(int i = 0; i < w.save.harem.length; i++)
                                newHarem[i] = w.save.harem[i];

                            int additional = 0;
                            for(int i = 0; i < conquered; i++)
                                if(conqueredSetting[i])
                                {
                                    newHarem[w.save.harem.length + additional] = w.conquered[i];
                                    additional++;
                                }

                            for(int i = 0; i < sacrificed; i++)
                                if(sacrificedSetting[i])
                                {
                                    newHarem[w.save.harem.length + additional] = w.sacrificed[i];
                                    additional++;
                                }

                            w.save.harem = newHarem;
                        }
                        t.setText("");
                        WorldState x = new WorldState();
                        x.initialize();
                        x.copySettings(t, w);
                        x.copyToggles(w);
                        x.setGenders(w.getGenderBalance());
                        x.save = w.save;
                        Project.IntroOne(t, p, f, x);
                    }
                });
                p.add(Confirm);
                JButton Cancel = new JButton("Cancel");
                Cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Project.WrapUpCampaign(t, p, f, w, conqueredSetting, sacrificedSetting);
                    }
                });
                p.add(Cancel);
                p.validate();
                p.repaint();
            }
        });
        p.add(Done);
        p.validate();
        p.repaint();
    }

    public static void DecideKeptForsaken(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Boolean broughtConquered[], final Boolean broughtSacrificed[], final int position)
    {
        p.removeAll();
        if(position < broughtConquered.length)
        {
            w.append(t, "\n\n" + w.getSeparator() + "\n\nWhat will you do with " + w.conquered[position].mainName + "?");
            JButton Save = new JButton("Save");
            Save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    broughtConquered[position] = true;
                    Project.DecideKeptForsaken(t, p, f, w, broughtConquered, broughtSacrificed, position + 1);
                }
            });
            p.add(Save);
            JButton Delete = new JButton("Delete");
            Delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    broughtConquered[position] = false;
                    Project.DecideKeptForsaken(t, p, f, w, broughtConquered, broughtSacrificed, position + 1);
                }
            });
            p.add(Delete);
        } else
        if(position < broughtConquered.length + broughtSacrificed.length)
        {
            w.append(t, "\n\n" + w.getSeparator() + "\n\nWhat will you do with " + w.sacrificed[position - broughtConquered.length].mainName + "?");
            JButton Save = new JButton("Save");
            Save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    broughtSacrificed[position - broughtConquered.length] = true;
                    Project.DecideKeptForsaken(t, p, f, w, broughtConquered, broughtSacrificed, position + 1);
                }
            });
            p.add(Save);
            JButton Delete = new JButton("Delete");
            Delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    broughtSacrificed[position - broughtConquered.length] = false;
                    Project.DecideKeptForsaken(t, p, f, w, broughtConquered, broughtSacrificed, position + 1);
                }
            });
            p.add(Delete);
        } else
        {
            WrapUpCampaign(t, p, f, w, broughtConquered, broughtSacrificed);
        }
        p.validate();
        p.repaint();
    }

    public static void ForsakenDowntime(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, SaveData s, Forsaken exhausted[])
    {
        if(!w.loopComplete)
            w.append(t, "\n\n" + w.getSeparator() + "\n\n");
        for(int i = 0; i < w.getHarem().length; i++)
        {
            w.getHarem()[i].visited = false;
            if(w.getHarem()[i].others != null)
            {
                for(int j = 0; j < w.getHarem()[i].others.length; j++)
                {
                    Boolean present = false;
                    for(int k = 0; k < w.getHarem().length; k++)
                        if(w.getHarem()[k].equals(w.getHarem()[i].others[j]))
                            present = true;

                    if(present)
                        w.getHarem()[i].troublemaker[j] = (w.getHarem()[i].troublemaker[j] * 9) / 10;
                }

            }
        }

        Forsaken included[] = w.getHarem();
        if(exhausted != null && exhausted.length == 0)
            exhausted = w.trainedForsaken;
        if(exhausted != null && exhausted.length > 0)
        {
            Forsaken newIncluded[] = new Forsaken[w.getHarem().length - exhausted.length];
            int numberFound = 0;
            for(int i = 0; i < w.getHarem().length; i++)
            {
                Boolean notExhausted = true;
                for(int j = 0; j < exhausted.length; j++)
                    if(w.getHarem()[i].equals(exhausted[j]))
                        notExhausted = false;

                if(notExhausted)
                {
                    newIncluded[numberFound] = w.getHarem()[i];
                    numberFound++;
                }
            }

            included = newIncluded;
        }
        Forsaken tantruming = null;
        int highest = 0;
        for(int i = 0; i < included.length; i++)
            if(included[i].hostility * 10 - included[i].motivation > highest)
            {
                highest = included[i].hostility * 10 - included[i].motivation;
                tantruming = included[i];
            }

        int damages[] = new int[3];
        if(tantruming != null)
        {
            damages = tantruming.motivationDamage();
            for(int i = 0; i < w.getHarem().length; i++)
                if(w.getHarem()[i] != tantruming)
                {
                    int offense = 100;
                    int damage = damages[1];
                    if(tantruming.opinion(w.getHarem()[i]) > 100)
                    {
                        offense = 70;
                        damage = damages[0];
                    } else
                    if(tantruming.opinion(w.getHarem()[i]) < -100)
                    {
                        offense = 150;
                        damage = damages[2];
                    }
                    w.getHarem()[i].motivation -= damage;
                    if(w.getHarem()[i].motivation < 0)
                        w.getHarem()[i].motivation = 0;
                    if(w.getHarem()[i].others != null)
                    {
                        Boolean found = false;
                        for(int j = 0; j < w.getHarem()[i].others.length; j++)
                            if(w.getHarem()[i].others[j].equals(tantruming))
                            {
                                found = true;
                                w.getHarem()[i].troublemaker[j] += offense;
                            }

                        if(!found)
                        {
                            Forsaken newOthers[] = new Forsaken[w.getHarem()[i].others.length + 1];
                            int newTroublemaker[] = new int[w.getHarem()[i].troublemaker.length + 1];
                            Forsaken.Relationship newRelations[] = new Forsaken.Relationship[w.getHarem()[i].forsakenRelations.length + 1];
                            for(int j = 0; j < w.getHarem()[i].others.length; j++)
                            {
                                newOthers[j] = w.getHarem()[i].others[j];
                                newTroublemaker[j] = w.getHarem()[i].troublemaker[j];
                                newRelations[j] = w.getHarem()[i].forsakenRelations[j];
                            }

                            newOthers[newOthers.length - 1] = tantruming;
                            newTroublemaker[newTroublemaker.length - 1] = offense;
                            newRelations[newRelations.length - 1] = null;
                            w.getHarem()[i].others = newOthers;
                            w.getHarem()[i].troublemaker = newTroublemaker;
                            w.getHarem()[i].forsakenRelations = newRelations;
                        }
                    } else
                    {
                        w.getHarem()[i].others = (new Forsaken[] {
                            tantruming
                        });
                        w.getHarem()[i].troublemaker = new int[offense];
                        w.getHarem()[i].forsakenRelations = new Forsaken.Relationship[1];
                    }
                }

            if(w.getHarem().length > 1)
            {
                if(tantruming.hostility < 20 && tantruming.defeatType != 5)
                {
                    w.append(t, String.format("%1$s tries to organize a resistance against you, ", tantruming.mainName));
                    if(tantruming.confidence > 66)
                        w.append(t, String.format("demanding that your other minions join %1$s", tantruming.himHer()) + ".  ");
                    else
                    if(tantruming.confidence > 33)
                        w.append(t, "appealing to your other minions' sense of morality.  ");
                    else
                        w.append(t, "begging and pleading for your other minions to find their conscience.  ");
                    w.append(t, "Even for those who are inclined to listen to such arguments, the effort is more annoying than persuasive.");
                } else
                if(tantruming.hostility < 40)
                {
                    w.append(t, String.format("%1$s lets out ", tantruming.mainName) + tantruming.hisHer() + " frustrations on your other minions, ");
                    if(tantruming.confidence > 66)
                        w.append(t, String.format("aggressively asserting %1$s", tantruming.hisHer()) + " dominance over anyone who can't or won't stand up to " + tantruming.himHer() + ".  ");
                    else
                    if(tantruming.confidence > 33)
                        w.append(t, String.format("spitting insults at anyone who so much as looks at %1$s", tantruming.himHer()) + " funny.  ");
                    else
                        w.append(t, String.format("passive-aggressively insulting anyone who makes the mistake of spending too much time around %1$s", tantruming.himHer()) + ".  ");
                    w.append(t, String.format("Even for everyone else, %1$s", tantruming.hisHer()) + " acting out is a constant annoyance.");
                } else
                if(tantruming.hostility < 61)
                {
                    w.append(t, String.format("%1$s makes a scene in the middle of your base of operations, ", tantruming.mainName));
                    if(tantruming.confidence > 66)
                        w.append(t, String.format("ranting, raving, and blaming everyone else for all %1$s", tantruming.hisHer()) + " problems.  ");
                    else
                    if(tantruming.confidence > 33)
                        w.append(t, String.format("shouting about how %1$s", tantruming.heShe()) + " hates being one of the Forsaken.  ");
                    else
                        w.append(t, "wailing in despair and whining about how unfair the world is.  ");
                    w.append(t, "The disruptive behavior is bad for your other minions' morale.");
                } else
                if(tantruming.hostility < 81)
                {
                    w.append(t, String.format("%1$s gets violent with your other minions, ", tantruming.mainName));
                    if(tantruming.confidence > 66)
                        w.append(t, String.format("challenging them to fight %1$s", tantruming.himHer()) + " head-on, and outright attacking those who try to flee.  ");
                    else
                    if(tantruming.confidence > 33)
                        w.append(t, "picking fights and getting into several scuffles over the course of the night.  ");
                    else
                        w.append(t, "abruptingly attacking them from behind and then fleeing before they can retaliate.  ");
                    w.append(t, String.format("The anger and resentment directed at %1$s", tantruming.himHer()) + " grows.");
                } else
                {
                    w.append(t, String.format("%1$s goes on a murderous rampage, ", tantruming.mainName));
                    if(tantruming.confidence > 66)
                        w.append(t, "carving a wide and indiscriminate swath of destruction through your base of operations.  ");
                    else
                    if(tantruming.confidence > 33)
                        w.append(t, String.format("hunting down and attacking anyone %1$s", tantruming.heShe()) + " feels has wronged " + tantruming.himHer() + ".  ");
                    else
                        w.append(t, String.format("slipping poison into the meals of countless Thralls and others %1$s", tantruming.heShe()) + " has a grudge against before " + tantruming.heShe() + "'s caught.  ");
                    w.append(t, "The resulting chaos affects your other minions as well.");
                }
                w.append(t, String.format("  (+%1$s", String.valueOf(tantruming.staminaRegen() / 10)) + "." + String.valueOf(tantruming.staminaRegen() % 10) + "% Stamina, restores own Motivation at expense of everyone else)");
            } else
            {
                w.append(t, String.format("%1$s is too stressed to relax, but there aren't any other Forsaken around for ", tantruming.mainName) + tantruming.himHer() + " to release " + tantruming.hisHer() + " tension on. (+" + tantruming.staminaRegen() + " Stamina)");
            }
        }
        for(int i = 0; i < included.length; i++)
            if(included[i] != tantruming)
            {
                if(tantruming != null || i > 0)
                    w.append(t, "\n\n");
                int flavor = (int)(4D * Math.random());
                if(included[i].demonicBirths < 0)
                {
                    w.append(t, String.format("Now that %1$s", included[i].mainName) + " is no longer one of the Chosen, the child in " + included[i].hisHer() + " belly is just a regular Demon, and " + included[i].heShe() + " quickly goes into labor.  The resulting abomination ");
                    if(included[i].gender.equals(Forsaken.Gender.MALE))
                        w.append(t, String.format("forces its way out of %1$s", included[i].mainName) + "'s asshole");
                    else
                        w.append(t, String.format("slides out of %1$s", included[i].mainName) + "'s distended vagina");
                    w.append(t, String.format(" while %1$s", included[i].heShe()) + " ");
                    if(included[i].confidence > 66)
                        w.append(t, "grunts and strains");
                    else
                    if(included[i].confidence > 33)
                        w.append(t, "stares down in horror");
                    else
                        w.append(t, "whimpers and whines");
                    w.append(t, ", then scuttles off immediately in search of its first victim.");
                    included[i].demonicBirths = 1;
                } else
                if(flavor == 0)
                {
                    if(included[i].demonicBirths > 0 && (int)(Math.random() * 2D) == 0)
                    {
                        included[i].demonicBirths++;
                        w.append(t, String.format("Due to %1$s", included[i].hisHer()) + " nighttime activities, " + included[i].mainName + " has been impregnated with another fast-growing Demonic child.  " + included[i].HeShe() + " gives birth to a small tentacled creature, ");
                        if(included[i].innocence > 66)
                            w.append(t, "then happily waves goodbye as it slithers away.");
                        else
                        if(included[i].innocence > 33)
                            w.append(t, String.format("which leaves %1$s", included[i].himHer()) + " gasping for breath.");
                        else
                            w.append(t, String.format("then mentally collects %1$s", included[i].himHer()) + "self and continues about " + included[i].hisHer() + " business.");
                    } else
                    if(included[i].timesKilled > 2 && (int)(Math.random() * 2D) == 0)
                    {
                        included[i].timesKilled++;
                        w.append(t, String.format("A particularly bold Thrall ambushes %1$s", included[i].mainName) + " while " + included[i].heShe() + "'s alone and tries to rape " + included[i].himHer());
                        if(included[i].morality > 66)
                            w.append(t, String.format(", and %1$s", included[i].mainName) + " is happy afterwards to note that " + included[i].heShe() + " doesn't feel guilty in the slightest about killing him.");
                        else
                        if(included[i].morality > 33)
                            w.append(t, String.format(", but the Forsaken has no trouble overpowering and killing %1$s", included[i].hisHer()) + " attacker.");
                        else
                            w.append(t, String.format(", and %1$s", included[i].mainName) + " enjoys giving him an especially slow and painful death.");
                    } else
                    if(included[i].timesHadSex > 0 && ((int)(Math.random() * 2D) == 0 || included[i].peopleInjured == 0))
                    {
                        included[i].timesHadSex += 3 + (int)(Math.random() * 3D);
                        included[i].orgasmsGiven += 5 + (int)(Math.random() * 5D);
                        if(included[i].timesOrgasmed > 0)
                            included[i].timesOrgasmed++;
                        w.append(t, String.format("%1$s attends a wild party and ends up participating in an orgy, ", included[i].mainName));
                        if(included[i].confidence > 66)
                            w.append(t, "gleefully dominating several partners at once.");
                        else
                        if(included[i].confidence > 33)
                            w.append(t, String.format("enjoying %1$s", included[i].himHer()) + "self greatly.");
                        else
                            w.append(t, String.format("surrendering %1$s", included[i].himHer()) + "self to the lustful crowd.");
                    } else
                    if(included[i].peopleInjured > 0)
                    {
                        included[i].peopleInjured++;
                        w.append(t, String.format("A particularly bold Thrall ambushes %1$s", included[i].mainName) + " while " + included[i].heShe() + "'s alone and tries to rape " + included[i].himHer());
                        if(included[i].morality > 66)
                            w.append(t, String.format(", but %1$s", included[i].heShe()) + " has no trouble fending him off.");
                        else
                        if(included[i].morality > 33)
                            w.append(t, ", only to receive a sound beating.");
                        else
                            w.append(t, String.format(", only to be left with some very painful injuries in %1$s", included[i].hisHer()) + " wake.");
                    } else
                    if(included[i].morality > 66)
                        w.append(t, String.format("%1$s spends ", included[i].mainName) + included[i].hisHer() + " time helping out your weaker minions, protecting them from danger and boosting their spirits.");
                    else
                    if(included[i].morality > 33)
                        w.append(t, String.format("%1$s hangs out with some of the friends ", included[i].mainName) + included[i].heShe() + "'s made among your minions.");
                    else
                        w.append(t, String.format("%1$s spends some time trying to bargain with you for better accommomdations, but to no avail.", included[i].mainName));
                } else
                if(flavor == 1)
                {
                    if(included[i].hypnotized && (int)(Math.random() * 2D) == 0)
                    {
                        w.append(t, String.format("%1$s sleeps through most of the day, having vivid dreams as you reach directly into ", included[i].mainName) + included[i].hisHer());
                        if(included[i].innocence > 66)
                            w.append(t, String.format(" simple mind and rearrange %1$s", included[i].hisHer()) + " instinctive impulses to your liking.");
                        else
                        if(included[i].innocence > 33)
                            w.append(t, String.format(" subconscious in order to reinforce %1$s", included[i].hisHer()) + " hypnotic conditioning.");
                        else
                            w.append(t, String.format(" mind and carefully influence %1$s", included[i].hisHer()) + " thought process in order to prevent " + included[i].himHer() + " from finding a way to break your hypnotism.");
                    } else
                    if(included[i].strongestOrgasm >= 1000 && (int)(Math.random() * 2D) == 0)
                    {
                        w.append(t, String.format("%1$s spends the day enjoying the company of several tentacled Demons", included[i].mainName));
                        if(included[i].dignity > 66)
                            w.append(t, String.format(", but while %1$s", included[i].heShe()) + " tries to pretend that " + included[i].heShe() + "'s just inspecting " + included[i].hisHer() + " forces, the truth is that " + included[i].heShe() + "'s having them make " + included[i].himHer() + " cum over and over again.");
                        else
                        if(included[i].dignity > 33)
                            w.append(t, String.format(", allowing them to ravage %1$s", included[i].himHer()) + " with their many appendages.");
                        else
                            w.append(t, String.format(", and soon %1$s", included[i].heShe()) + "'s screaming at the top of " + included[i].hisHer() + " lungs as " + included[i].heShe() + "'s gripped by a long, continuous climax.");
                        included[i].timesOrgasmed += 10 + (int)(Math.random() * 10D);
                    } else
                    if(included[i].strongestOrgasm >= 200 && ((int)(Math.random() * 2D) == 0 || included[i].orgasmsGiven < 1000))
                    {
                        included[i].timesOrgasmed += 4 + (int)(Math.random() * 4D);
                        if(included[i].confidence > 66)
                            w.append(t, String.format("%1$s decides that ", included[i].mainName) + included[i].heShe() + " needs a day to relax.  " + included[i].HeShe() + " spends much of it masturbating.");
                        else
                        if(included[i].confidence > 33)
                            w.append(t, String.format("%1$s tries to manage ", included[i].mainName) + included[i].hisHer() + " lust by spending some time masturbating.  " + included[i].HeShe() + " ends up doing it for most of the day.");
                        else
                            w.append(t, String.format("Overcome by the Demonic influence in the air, %1$s", included[i].mainName) + " hides in " + included[i].hisHer() + " room and starts to quietly masturbate, jumping in alarm whenever " + included[i].heShe() + " hears movement outside.");
                    } else
                    if(included[i].orgasmsGiven >= 1000)
                    {
                        if(included[i].timesOrgasmed > 0)
                            included[i].timesOrgasmed += 2 + (int)(Math.random() * 2D);
                        if(included[i].innocence > 66)
                            w.append(t, String.format("%1$s reads pornographic comics all day, marvelling at what ", included[i].mainName) + included[i].heShe() + " sees.");
                        else
                        if(included[i].innocence > 33)
                            w.append(t, String.format("%1$s spends the day playing pornographic computer games.", included[i].mainName));
                        else
                            w.append(t, String.format("%1$s spends the day studying and theorizing about methods to more efficiently force an unwilling target to orgasm.", included[i].mainName));
                    } else
                    if(included[i].innocence > 66)
                        w.append(t, String.format("%1$s plays video games all day, forgetting for awhile where ", included[i].mainName) + included[i].heShe() + " is.");
                    else
                    if(included[i].innocence > 33)
                        w.append(t, String.format("%1$s relaxes and spends ", included[i].mainName) + included[i].hisHer() + " evening watching DVDs smuggled in from the outside world.");
                    else
                        w.append(t, String.format("%1$s spends most of the day reading scholarly articles on psychography.", included[i].mainName));
                } else
                if(flavor == 2)
                {
                    if(included[i].drained && (int)(Math.random() * 2D) == 0)
                    {
                        if(included[i].confidence > 66)
                        {
                            included[i].timesHarmedSelf++;
                            w.append(t, String.format("%1$s whips ", included[i].mainName) + included[i].himHer() + "self until " + included[i].hisHer() + " back begins to show the marks, stubbornly enduring the pain to remind " + included[i].himHer() + "self not to oppose you.");
                        } else
                        if(included[i].confidence > 33)
                            w.append(t, String.format("%1$s asks to be drained of what little residual psychic energy remains inside ", included[i].mainName) + included[i].himHer() + ", submitting " + included[i].himHer() + "self to you completely.");
                        else
                            w.append(t, String.format("%1$s begs you to punish ", included[i].mainName) + included[i].himHer() + " for ever daring to oppose you, and after you use a spare Demonic body to lightly molest " + included[i].himHer() + ", " + included[i].heShe() + " seems grateful and satisfied.");
                    } else
                    if(included[i].timesHarmedSelf > 0 && (int)(Math.random() * 2D) == 0)
                    {
                        w.append(t, String.format("%1$s isolates ", included[i].mainName) + included[i].himHer() + "self and spends the day in silent contemplation of your greatness, ");
                        if(included[i].innocence > 66)
                            w.append(t, "though it doesn't amount to much more than mentally repeating 'The Demon Lord is Really Strong' over and over again.");
                        else
                        if(included[i].innocence > 33)
                            w.append(t, String.format("reminding %1$s", included[i].himHer()) + "self that your will is absolute.");
                        else
                            w.append(t, "attempting to understand the true nature of a Demon Lord.");
                    } else
                    if(included[i].timesTortured > 0 && ((int)(Math.random() * 2D) == 0 || !included[i].meek))
                    {
                        if(included[i].confidence > 66)
                            w.append(t, String.format("%1$s humbles ", included[i].mainName) + included[i].himHer() + "self by doing manual labor alongside your lesser minions in an attempt to show you " + included[i].hisHer() + " willingness to serve.");
                        else
                        if(included[i].confidence > 33)
                            w.append(t, String.format("%1$s keeps ", included[i].mainName) + included[i].himHer() + "self busy by doing manual labor with the Thralls at your base of operations, hopeful that you'll notice " + included[i].hisHer() + " efforts.");
                        else
                            w.append(t, String.format("%1$s presents ", included[i].mainName) + included[i].himHer() + "self to the Thrall in charge of constructing your base of operations, offering to help out in a show of submission.");
                    } else
                    if(included[i].meek)
                    {
                        if(included[i].confidence > 66)
                            w.append(t, String.format("%1$s is suffering from flashbacks to ", included[i].mainName) + included[i].hisHer() + " past abuses, but " + included[i].heShe() + " forces " + included[i].himHer() + "self to go outside and do " + included[i].hisHer() + " daily routine anyway, and " + included[i].heShe() + " feels satisfied about it once " + included[i].heShe() + " returns to " + included[i].hisHer() + " room for the night.");
                        else
                        if(included[i].confidence > 33)
                            w.append(t, String.format("%1$s feels worried about going outside, so ", included[i].mainName) + included[i].heShe() + " just spends the day in " + included[i].hisHer() + " room.");
                        else
                            w.append(t, String.format("%1$s locks ", included[i].mainName) + included[i].himHer() + "self in " + included[i].hisHer() + " room, resting there until " + included[i].heShe() + " can overcome " + included[i].hisHer() + " old fears of being abused by the Thralls.");
                    } else
                    if(included[i].confidence > 66)
                        w.append(t, String.format("%1$s has a good day, and ", included[i].mainName) + included[i].heShe() + " goes to bed in high spirits.");
                    else
                    if(included[i].confidence > 33)
                        w.append(t, String.format("%1$s spends a leisurely day doing nothing in particular.", included[i].mainName));
                    else
                        w.append(t, String.format("%1$s lifts weights in ", included[i].mainName) + included[i].hisHer() + " room all day, desperate to become stronger.");
                } else
                if(included[i].parasitized && (int)(Math.random() * 2D) == 0)
                {
                    w.append(t, String.format("%1$s spends the day with what's left of ", included[i].mainName) + included[i].hisHer() + " fans, ");
                    if(included[i].innocence > 66)
                        w.append(t, "not really even noticing that there are far fewer than before.");
                    else
                    if(included[i].innocence > 33)
                        w.append(t, String.format("and even though there clearly aren't as many as before, %1$s", included[i].heShe()) + " still enjoys " + included[i].himHer() + "self.");
                    else
                        w.append(t, String.format("but %1$s", included[i].heShe()) + " can't help but dwell on the fact that most of them have moved on to newer Chosen and Forsaken.");
                    included[i].timesExposed += 10 + (int)(Math.random() * 10D);
                    included[i].timesExposedSelf += 10 + (int)(Math.random() * 10D);
                } else
                if(included[i].timesExposedSelf > 100 && (int)(Math.random() * 2D) == 0)
                {
                    w.append(t, String.format("%1$s goes outside in the nude", included[i].mainName));
                    if(included[i].dignity > 66)
                        w.append(t, String.format(", greatly enjoying the extra attention it gets %1$s", included[i].himHer()) + ".");
                    else
                    if(included[i].dignity > 33)
                        w.append(t, String.format(", letting a few of your minions catch glimpses of %1$s", included[i].himHer()) + " before returning home.");
                    else
                        w.append(t, " as if it isn't any big deal.");
                    included[i].timesExposed++;
                    included[i].timesExposedSelf++;
                } else
                if(included[i].timesExposed > 100_000 && (int)(Math.random() * 2D) == 0 || !included[i].debased)
                {
                    w.append(t, String.format("%1$s goes outside in ", included[i].mainName));
                    if(included[i].dignity > 66)
                        w.append(t, String.format("a dress that's practically transparent, not quite showing the details of %1$s", included[i].hisHer()) + " private parts, but leaving very little to the imagination.");
                    else
                    if(included[i].dignity > 33)
                        w.append(t, String.format("a long shirt with nothing underneath, teasing your minions with the promise of catching a glimpse of %1$s", included[i].hisHer()) + " most intimate places.");
                    else
                        w.append(t, String.format("a tiny miniskirt with no panties, and %1$s", included[i].heShe()) + " makes no effort whatsoever to avoid flashing people whenever " + included[i].heShe() + " stretches or bends over.");
                } else
                if(included[i].debased)
                {
                    w.append(t, String.format("During %1$s", included[i].hisHer()) + " daily routine, " + included[i].mainName + " is confronted by a Thrall with a recording of " + included[i].himHer() + " being humiliated, ");
                    if(included[i].dignity > 66)
                        w.append(t, String.format("but %1$s", included[i].mainName) + " is pleasantly surprised to see that the Thrall is just an enthusiastic fan.");
                    else
                    if(included[i].dignity > 33)
                        w.append(t, String.format("but %1$s", included[i].mainName) + " doesn't let it get to " + included[i].himHer() + ".");
                    else
                        w.append(t, String.format("but %1$s", included[i].mainName) + " is past the point of caring, and " + included[i].heShe() + " doesn't let it ruin " + included[i].hisHer() + " day.");
                } else
                {
                    w.append(t, String.format("%1$s spends the day talking to a gathering of ", included[i].mainName) + included[i].hisHer() + " fans, ");
                    if(included[i].confidence > 66)
                        w.append(t, String.format("happily regaling them with stories of %1$s", included[i].hisHer()) + " time as one of the Chosen.");
                    else
                    if(included[i].confidence > 33)
                        w.append(t, "chatting about what life is like under the Demon Lord.");
                    else
                        w.append(t, String.format("blushing and stammering when %1$s", included[i].heShe()) + " hears how much they still love " + included[i].himHer() + ".");
                }
                w.append(t, String.format("  (+%1$s", String.valueOf(included[i].staminaRegen() / 10)) + "." + String.valueOf(included[i].staminaRegen() % 10) + "% Stamina");
                if(tantruming != null)
                {
                    int lost = damages[1];
                    if(tantruming.opinion(included[i]) > 100)
                        lost = damages[0];
                    else
                    if(tantruming.opinion(included[i]) < -100)
                        lost = damages[2];
                    w.append(t, ", ");
                    if(included[i].motivation / 10 < included[i].hostility)
                        w.redAppend(t, "-" + String.valueOf(lost / 10) + "." + String.valueOf(lost % 10) + "% Motivation");
                    else
                        w.append(t, String.format("-%1$s", String.valueOf(lost / 10)) + "." + String.valueOf(lost % 10) + "% Motivation");
                }
                w.append(t, ")");
            }

        if(exhausted != null && (tantruming != null || included.length == 0))
        {
            for(int i = 0; i < exhausted.length; i++)
                if(tantruming != null)
                {
                    w.append(t, "\n\n" + exhausted[i].mainName + " finds it difficult to rest due to " + tantruming.mainName + "'s disturbance.  (");
                    int lost = damages[1];
                    if(tantruming.opinion(exhausted[i]) > 100)
                        lost = damages[0];
                    else
                    if(tantruming.opinion(exhausted[i]) < -100)
                        lost = damages[2];
                    if(exhausted[i].motivation / 10 < exhausted[i].hostility)
                        w.redAppend(t, "-" + String.valueOf(lost / 10) + "." + String.valueOf(lost % 10) + "% Motivation");
                    else
                        w.append(t, String.format("-%1$s", String.valueOf(lost / 10)) + "." + String.valueOf(lost % 10) + "% Motivation");
                    w.append(t, ")");
                } else
                {
                    if(i != 0)
                        w.append(t, "\n\n");
                    w.append(t, String.format("%1$s is tired due to the day's activities.", exhausted[i].mainName));
                }

        }
        for(int i = 0; i < included.length; i++)
        {
            included[i].stamina += included[i].staminaRegen();
            if(included[i].stamina > 1000)
                included[i].stamina = 1000;
        }

        if(tantruming != null && w.getHarem().length > 1)
            tantruming.motivation = 1000;
        WriteObject wobj = new WriteObject();
        wobj.serializeSaveData(s);
        w.trainedForsaken = null;
        if(w.active)
        {
            p.removeAll();
            JButton Continue = new JButton("Continue");
            Continue.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.usedForsaken = null;
                    Project.Shop(t, p, f, w);
                }
            });
            p.add(Continue);
            p.validate();
            p.repaint();
        }
    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                new Project();
            }

        });
    }

}
