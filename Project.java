
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
    public static final class Emotion extends Enum
    {

        public static Emotion[] values()
        {
            Emotion aemotion[];
            int i;
            Emotion aemotion1[];
            System.arraycopy(aemotion = ENUM$VALUES, 0, aemotion1 = new Emotion[i = aemotion.length], 0, i);
            return aemotion1;
        }

        public static Emotion valueOf(String s)
        {
            return (Emotion)Enum.valueOf(Project$Emotion, s);
        }

        public static final Emotion ANGER;
        public static final Emotion FEAR;
        public static final Emotion FOCUS;
        public static final Emotion JOY;
        public static final Emotion LEWD;
        public static final Emotion NEUTRAL;
        public static final Emotion SHAME;
        public static final Emotion STRUGGLE;
        public static final Emotion SWOON;
        private static final Emotion ENUM$VALUES[];

        static 
        {
            ANGER = new Emotion("ANGER", 0);
            FEAR = new Emotion("FEAR", 1);
            FOCUS = new Emotion("FOCUS", 2);
            JOY = new Emotion("JOY", 3);
            LEWD = new Emotion("LEWD", 4);
            NEUTRAL = new Emotion("NEUTRAL", 5);
            SHAME = new Emotion("SHAME", 6);
            STRUGGLE = new Emotion("STRUGGLE", 7);
            SWOON = new Emotion("SWOON", 8);
            ENUM$VALUES = (new Emotion[] {
                ANGER, FEAR, FOCUS, JOY, LEWD, NEUTRAL, SHAME, STRUGGLE, SWOON
            });
        }

        private Emotion(String s, int i)
        {
            super(s, i);
        }
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
        File saveLocation = new File((new StringBuilder(String.valueOf(path))).append(File.separator).append("saves.sav").toString());
        SaveData saves = null;
        if(saveLocation.exists())
        {
            ReadObject robj = new ReadObject();
            saves = robj.deserializeSaveData((new StringBuilder(String.valueOf(path))).append(File.separator).append("saves.sav").toString());
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
            if(!ThisState.hardMode.booleanValue())
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
                    saves.harem[i].titled = Boolean.valueOf(false);
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
                            if(saves.harem[i].others[j].equals(saves.harem[i].firstPartner).booleanValue() || saves.harem[i].others[j].equals(saves.harem[i].secondPartner).booleanValue())
                                saves.harem[i].forsakenRelations[j] = Forsaken.Relationship.PARTNER;

                    } else
                    {
                        saves.harem[i].others = new Forsaken[0];
                        saves.harem[i].forsakenRelations = new Forsaken.Relationship[0];
                        saves.harem[i].troublemaker = new int[0];
                    }
                    Forsaken checkedForsaken[] = saves.harem;
                    for(int j = 0; j < checkedForsaken.length; j++)
                        if(checkedForsaken[j].equals(saves.harem[i].firstPartner).booleanValue() || checkedForsaken[j].equals(saves.harem[i].secondPartner).booleanValue())
                        {
                            Boolean alreadyThere = Boolean.valueOf(false);
                            for(int k = 0; k < saves.harem[i].others.length; k++)
                                if(saves.harem[i].others[k].equals(checkedForsaken[j]).booleanValue())
                                    alreadyThere = Boolean.valueOf(true);

                            if(!alreadyThere.booleanValue())
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
        result = Project.getProtectionDomain().getCodeSource().getLocation().getPath();
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
        result = result.replaceAll((new StringBuilder(String.valueOf(File.separator))).append("u0020").toString(), (new StringBuilder(String.valueOf(File.separator))).append(" ").toString());
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
        if(w.portraits.booleanValue())
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
                String path = (new StringBuilder(String.valueOf(getFilePath()))).append(File.separator).append("portraits").append(File.separator).append("empty").toString();
                if(names[i] != null)
                    path = (new StringBuilder(String.valueOf(getFilePath()))).append(File.separator).append("portraits").append(File.separator).append(names[i]).append(File.separator).toString();
                String folders[] = {
                    "", "", "", ""
                };
                if(displayedGender[i] == Forsaken.Gender.MALE)
                    folders[0] = (new StringBuilder("male")).append(File.separator).toString();
                if(displayedType[i] == Chosen.Species.SUPERIOR)
                    folders[1] = (new StringBuilder("superior")).append(File.separator).toString();
                if(displayedCivilians[i].booleanValue())
                    folders[2] = (new StringBuilder("civilian")).append(File.separator).toString();
                if(displayedFallen[i].booleanValue())
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
                        nav = (new StringBuilder(String.valueOf(nav))).append(folders[0]).toString();
                    if(folders[1].length() > 0 && j % 8 < 4)
                        nav = (new StringBuilder(String.valueOf(nav))).append(folders[1]).toString();
                    if(folders[2].length() > 0 && j % 4 < 2)
                        nav = (new StringBuilder(String.valueOf(nav))).append(folders[2]).toString();
                    if(folders[3].length() > 0 && j % 2 == 0)
                        nav = (new StringBuilder(String.valueOf(nav))).append(folders[3]).toString();
                    try
                    {
                        image = ImageIO.read(new File((new StringBuilder(String.valueOf(path))).append(nav).append(type).append(".png").toString()));
                    }
                    catch(IOException ie)
                    {
                        try
                        {
                            image = ImageIO.read(new File((new StringBuilder(String.valueOf(path))).append(nav).append(type).append(".jpg").toString()));
                        }
                        catch(IOException ig)
                        {
                            try
                            {
                                image = ImageIO.read(new File((new StringBuilder(String.valueOf(path))).append(nav).append(type).append(".gif").toString()));
                            }
                            catch(IOException ih)
                            {
                                try
                                {
                                    image = ImageIO.read(new File((new StringBuilder(String.valueOf(path))).append(nav).append(type).append(".jpeg").toString()));
                                }
                                catch(IOException ioexception) { }
                            }
                        }
                    }
                }

                if(image == null)
                    try
                    {
                        image = ImageIO.read(new File((new StringBuilder(String.valueOf(getFilePath()))).append(File.separator).append("portraits").append(File.separator).append("empty.png").toString()));
                    }
                    catch(IOException ie)
                    {
                        w.portraits = Boolean.valueOf(false);
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
        if(w.portraits.booleanValue())
        {
            BufferedImage image = null;
            try
            {
                image = ImageIO.read(new File((new StringBuilder(String.valueOf(getFilePath()))).append(File.separator).append("portraits").append(File.separator).append("empty.png").toString()));
            }
            catch(IOException ie)
            {
                w.portraits = Boolean.valueOf(false);
                clearPortraits();
            }
        }
        if(!t.getBackground().equals(w.BACKGROUND))
            w.toggleColors(t);
        w.append(t, (new StringBuilder("Corrupted Saviors, Release 29: \"Negotiation\"\n\nThis game contains content of an adult nature and should not be played by the underaged or by those unable to distinguish fantasy from reality.\n\n")).append(w.getSeparator()).append("\n\nJapan, mid-21st century.  The psychic energies of humanity have finally begun to coalesce into physical form.  The resulting beings are known as Demons.  Born from the base desires suppressed deep within the human mind, these creatures spread across the planet, leaving chaos and depravity in their wake.\n\nBut Demons do not represent the entirety of the human condition.  The hopes and determination of humanity have also risen up, gathering in the bodies of a few Chosen warriors in order to grant them the power to fight the Demons.  Although each of them was once an ordinary person, their new abilities place them at the center of the struggle for the soul of humanity.\n\nYou are a Demon Lord, the highest form of Demon, with your own mind and will, focused on the corruption of all that is good in the world.  The Chosen are the keystone of humanity's resistance to your goal, but to simply kill them would be meaningless.  Instead, shatter their notions of right and wrong, showing them the true darkness that hides within!").toString());
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

            public void actionPerformed(ActionEvent e)
            {
                if(w.getEarlyCheat().booleanValue())
                {
                    Project.Shop(t, p, f, w);
                } else
                {
                    w.active = Boolean.valueOf(true);
                    Project.IntroTwo(t, p, f, w);
                }
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(NewGame);
        JButton Campaign = new JButton("Campaign");
        Campaign.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Boolean enabled[] = new Boolean[w.save.customRoster.length];
                for(int i = 0; i < enabled.length; i++)
                    enabled[i] = Boolean.valueOf(true);

                Project.CampaignMenu(t, p, f, w, enabled);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(Campaign);
        JButton LoadGame = new JButton("Load Game");
        LoadGame.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.Data(t, p, f, w, "load", 0, Boolean.valueOf(false));
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
        });
        p.add(LoadGame);
        JButton Import = new JButton("Import");
        Import.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.Data(t, p, f, w, "import", 0, Boolean.valueOf(false));
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
        });
        p.add(Import);
        JButton Tutorial = new JButton("Tutorial");
        Tutorial.addActionListener(new ActionListener() {

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

            private final JTextPane val$t;
            private final WorldState val$w;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                t = jtextpane;
                w = worldstate;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(Tutorial);
        JButton Options = new JButton("Options");
        Options.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.OptionsMenu(t, p, f, w, null);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
        });
        p.add(Options);
        JButton Customize = new JButton("Customize");
        Customize.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                WorldState x = new WorldState();
                x.copySettings(t, w);
                x.copyToggles(w);
                x.setGenders(w.getGenderBalance());
                x.save = w.save;
                x.active = Boolean.valueOf(true);
                x.freshCustom(t, p, f);
            }

            private final JPanel val$p;
            private final JTextPane val$t;
            private final WorldState val$w;
            private final JFrame val$f;

            
            {
                p = jpanel;
                t = jtextpane;
                w = worldstate;
                f = jframe;
                super();
            }
        });
        p.add(Customize);
        if(w.save.harem == null)
            w.save.harem = new Forsaken[0];
        if(w.save.harem.length > 0 || w.getEarlyCheat().booleanValue())
        {
            final SaveData fileUsed = w.save;
            JButton Forsaken = new JButton("Forsaken");
            Forsaken.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.ForsakenMenu(t, p, f, w, fileUsed, 0);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final SaveData val$fileUsed;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                fileUsed = savedata;
                super();
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

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.SceneCompletion(t, p, f, w, w.save);
                        Project.SceneViewer(t, p, f, w, w.save, 0);
                    }

                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
                });
                p.add(Scenes);
            }

        JButton About = new JButton("About");
        About.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nCopyright 2019-2022 by CSdev. Corrupted Saviors is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License. To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/4.0/.\n\nDefault portrait set created by CSdev with the assistance of Artbreeder and dedicated to the public domain (CC0).  For more information, see https://creativecommons.org/publicdomain/zero/1.0/.\n\nIf you like this game, please share it and discuss it so that it can be further enjoyed and improved!  There is a good chance that the developer reads whatever forum you found it on.  Direct feedback can also be sent to corruptedsaviors@gmail.com\n\nNew versions are first posted to corruptedsaviors.blogspot.com\nThe developer's tip jar can be found at subscribestar.adult/csdev").toString());
            }

            private final WorldState val$w;
            private final JTextPane val$t;

            
            {
                w = worldstate;
                t = jtextpane;
                super();
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

            public void actionPerformed(ActionEvent e)
            {
                WorldState x = new WorldState();
                for(int i = 0; i < enabled.length; i++)
                    if(enabled[i].booleanValue())
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
                x.active = Boolean.valueOf(true);
                x.campaign = Boolean.valueOf(true);
                x.cityName = x.getCityName(0);
                x.campaignRand = new Random();
                x.hardMode = Boolean.valueOf(false);
                x.clampStart = 11;
                x.clampPercent = 100;
                x.eventOffset = 0;
                x.downtimeMultiplier = 100;
                Chosen newChosen = new Chosen();
                newChosen.setNumber(0);
                x.initialize();
                newChosen.generate(x);
                x.addChosen(newChosen);
                if(w.getEarlyCheat().booleanValue())
                {
                    Project.Shop(t, p, f, x);
                } else
                {
                    w.active = Boolean.valueOf(true);
                    Project.IntroTwo(t, p, f, x);
                }
            }

            private final Boolean val$enabled[];
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                enabled = aboolean;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(Begin);
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
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

                public void actionPerformed(ActionEvent e)
                {
                    Project.CampaignCustomToggle(t, p, f, w, enabled, 0, Boolean.valueOf(false));
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Boolean val$enabled[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                enabled = aboolean;
                super();
            }
            });
            p.add(Toggle);
            JButton DeleteChosen = new JButton("Delete Chosen");
            DeleteChosen.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.CampaignCustomToggle(t, p, f, w, enabled, 0, Boolean.valueOf(true));
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Boolean val$enabled[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                enabled = aboolean;
                super();
            }
            });
            p.add(DeleteChosen);
        }
        if(w.earlyCheat.booleanValue())
            w.append(t, "\n\nEasy Mode is turned on.  It will be possible to use cheats.  Aside from the presence of Elite Chosen, there will be no increases in the difficulty of later loops.");
        JButton LoadTeam = new JButton("Load Team");
        LoadTeam.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.Data(t, p, f, w, "teamload", 0, Boolean.valueOf(false));
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
        });
        p.add(LoadTeam);
        JButton SoloGen = new JButton("Single Custom");
        SoloGen.addActionListener(new ActionListener() {

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

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(SoloGen);
        JButton ExportIncluded = new JButton("Export Included");
        ExportIncluded.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                WriteObject wobj = new WriteObject();
                Chosen exportedRoster[] = new Chosen[0];
                for(int i = 0; i < enabled.length; i++)
                    if(enabled[i].booleanValue())
                    {
                        Chosen newRoster[] = new Chosen[exportedRoster.length + 1];
                        for(int j = 0; j < exportedRoster.length; j++)
                            newRoster[j] = exportedRoster[j];

                        newRoster[exportedRoster.length] = w.save.customRoster[i];
                        exportedRoster = newRoster;
                    }

                String newRosterName = JOptionPane.showInputDialog("What would you like to name the exported roster?");
                Boolean blankName = Boolean.valueOf(false);
                if(newRosterName == null)
                    blankName = Boolean.valueOf(true);
                else
                if(newRosterName.length() == 0)
                    blankName = Boolean.valueOf(true);
                if(blankName.booleanValue())
                    newRosterName = (new StringBuilder(String.valueOf(exportedRoster[0].mainName))).append("'s Roster").toString();
                exportedRoster[0].rosterName = newRosterName;
                String editedName = "";
                for(int i = 0; i < newRosterName.length(); i++)
                    if(newRosterName.charAt(i) == '/' || newRosterName.charAt(i) == ':')
                        editedName = (new StringBuilder(String.valueOf(editedName))).append("-").toString();
                    else
                        editedName = (new StringBuilder(String.valueOf(editedName))).append(newRosterName.charAt(i)).toString();

                wobj.exportRoster(exportedRoster, editedName);
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nNew roster saved to '").append(editedName).append(".ros'.").toString());
            }

            private final Boolean val$enabled[];
            private final WorldState val$w;
            private final JTextPane val$t;

            
            {
                enabled = aboolean;
                w = worldstate;
                t = jtextpane;
                super();
            }
        });
        if(enabled.length > 0)
            p.add(ExportIncluded);
        JButton ImportRoster = new JButton("Import Roster");
        ImportRoster.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.ImportMenu(t, p, f, w, enabled, 0);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Boolean val$enabled[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                enabled = aboolean;
                super();
            }
        });
        p.add(ImportRoster);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.IntroOne(t, p, f, w);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
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
            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nNo importable rosters found in directory.").toString());
        } else
        {
            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nFound the following importable rosters in directory.  Which would you like to import?").toString());
            if(page > 0)
            {
                JButton LastPage = new JButton("<");
                LastPage.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.ImportMenu(t, p, f, w, enabled, page - 1);
                    }

                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final Boolean val$enabled[];
                    private final int val$page;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                enabled = aboolean;
                page = i;
                super();
            }
                });
                p.add(LastPage);
            }
            for(; i < foundRosters.length && j < 4; j++)
            {
                w.append(t, (new StringBuilder("\n\nFile ")).append(i + 1).append(": ").append(foundRosters[i][0].rosterName).toString());
                final int rosterSelected = i;
                final Chosen rosterList[][] = foundRosters;
                JButton Access = new JButton((new StringBuilder()).append(i + 1).toString());
                Access.addActionListener(new ActionListener() {

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
                                newEnabled[k] = Boolean.valueOf(true);
                            }

                        w.save.customRoster = newRoster;
                        wobj.serializeSaveData(w.save);
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").append(rosterList[rosterSelected].length).append(" new Chosen added to custom roster.").toString());
                        Project.CampaignMenu(t, p, f, w, newEnabled);
                    }

                    private final WorldState val$w;
                    private final Chosen val$rosterList[][];
                    private final int val$rosterSelected;
                    private final Boolean val$enabled[];
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                w = worldstate;
                rosterList = achosen;
                rosterSelected = i;
                enabled = aboolean;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
                });
                p.add(Access);
                i++;
            }

            if(page * 4 + 4 < foundRosters.length)
            {
                JButton NextPage = new JButton(">");
                NextPage.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.ImportMenu(t, p, f, w, enabled, page + 1);
                    }

                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final Boolean val$enabled[];
                    private final int val$page;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                enabled = aboolean;
                page = i;
                super();
            }
                });
                p.add(NextPage);
            }
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.CampaignMenu(t, p, f, w, enabled);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Boolean val$enabled[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                enabled = aboolean;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void SingleCustom(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Chosen c, final int answers[])
    {
        p.removeAll();
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").append(c.customSummary()).append("\n\nSet the Chosen's basic characteristics.").toString());
        JButton FamilyName = new JButton("Family Name");
        FamilyName.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                String input = JOptionPane.showInputDialog((new StringBuilder("What is ")).append(c.hisHer()).append(" family surname?  Leave blank to have ").append(c.himHer()).append(" lack a surname.").toString());
                if(input == null)
                    c.familyName = "";
                else
                    c.familyName = input;
                Project.SingleCustom(t, p, f, w, c, answers);
            }

            private final Chosen val$c;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final int val$answers[];

            
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
        });
        p.add(FamilyName);
        JButton GivenName = new JButton("Given Name");
        GivenName.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                String input = JOptionPane.showInputDialog((new StringBuilder("What name was ")).append(c.heShe()).append(" given at birth?  Leave blank to keep the current choice (").append(c.givenName).append(").").toString());
                if(input != null && input.length() > 0)
                {
                    c.givenName = input;
                    Project.SingleCustom(t, p, f, w, c, answers);
                }
            }

            private final Chosen val$c;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final int val$answers[];

            
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
        });
        p.add(GivenName);
        JButton NameOrder = new JButton("Name Order");
        NameOrder.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                c.filthyGaijin = Boolean.valueOf(!c.filthyGaijin.booleanValue());
                Project.SingleCustom(t, p, f, w, c, answers);
            }

            private final Chosen val$c;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final int val$answers[];

            
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
        });
        p.add(NameOrder);
        JButton Sex = new JButton("Sex");
        Sex.addActionListener(new ActionListener() {

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

            private final Chosen val$c;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final int val$answers[];

            
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
        });
        p.add(Sex);
        JButton Species = new JButton("Species");
        Species.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                if(c.type == null)
                    c.type = Chosen.Species.SUPERIOR;
                else
                    c.type = null;
                Project.SingleCustom(t, p, f, w, c, answers);
            }

            private final Chosen val$c;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final int val$answers[];

            
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
        });
        p.add(Species);
        JButton Continue = new JButton("Continue (Quiz)");
        Continue.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                if(answers == null)
                    Project.CustomQuiz(t, p, f, w, c, 0, new int[32]);
                else
                    Project.CustomQuiz(t, p, f, w, c, 0, answers);
            }

            private final int val$answers[];
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Chosen val$c;

            
            {
                answers = ai;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                c = chosen;
                super();
            }
        });
        p.add(Continue);
        JButton DirectInput = new JButton("Continue (Direct Input)");
        DirectInput.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                String input = "";
                input = JOptionPane.showInputDialog((new StringBuilder("Enter ")).append(c.givenName).append("'s Morality (0 to 100).").toString());
                int upperLimit = 100;
                int lowerLimit = 0;
                int value = 0;
                try
                {
                    value = Integer.parseInt(input);
                }
                catch(Exception ex)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nInvalid value.").toString());
                    Project.SingleCustom(t, p, f, w, c, answers);
                    ex.printStackTrace();
                }
                if(value >= 0 && value <= 100)
                {
                    c.morality = Integer.parseInt(input);
                    input = JOptionPane.showInputDialog((new StringBuilder("Enter ")).append(c.givenName).append("'s Innocence (0 to 100).").toString());
                    try
                    {
                        value = Integer.parseInt(input);
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nInvalid value.").toString());
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
                            input = JOptionPane.showInputDialog((new StringBuilder("Enter ")).append(c.givenName).append("'s Confidence (").append(lowerLimit).append(" to ").append(upperLimit).append(").").toString());
                        else
                            input = JOptionPane.showInputDialog((new StringBuilder("Enter ")).append(c.givenName).append("'s Confidence (0 to 33 or 67 to 100).").toString());
                        try
                        {
                            value = Integer.parseInt(input);
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nInvalid value.").toString());
                            Project.SingleCustom(t, p, f, w, c, answers);
                        }
                        if(value >= 0 && value <= 100 && (value >= lowerLimit && value <= upperLimit || upperLimit < lowerLimit && (value > 66 || value < 34)))
                        {
                            c.confidence = value;
                            Boolean highFound = Boolean.valueOf(false);
                            Boolean midFound = Boolean.valueOf(false);
                            Boolean lowFound = Boolean.valueOf(false);
                            if(c.morality > 66)
                                highFound = Boolean.valueOf(true);
                            else
                            if(c.morality > 34)
                                midFound = Boolean.valueOf(true);
                            else
                                lowFound = Boolean.valueOf(true);
                            if(c.innocence > 66)
                                highFound = Boolean.valueOf(true);
                            else
                            if(c.innocence > 33)
                                midFound = Boolean.valueOf(true);
                            else
                                lowFound = Boolean.valueOf(true);
                            if(c.confidence > 66)
                                highFound = Boolean.valueOf(true);
                            else
                            if(c.confidence > 33)
                                midFound = Boolean.valueOf(true);
                            else
                                lowFound = Boolean.valueOf(true);
                            if(!highFound.booleanValue())
                            {
                                upperLimit = 100;
                                lowerLimit = 67;
                            } else
                            if(!midFound.booleanValue())
                            {
                                upperLimit = 66;
                                lowerLimit = 34;
                            } else
                            if(!lowFound.booleanValue())
                            {
                                upperLimit = 33;
                                lowerLimit = 0;
                            } else
                            {
                                upperLimit = 100;
                                lowerLimit = 0;
                            }
                            input = JOptionPane.showInputDialog((new StringBuilder("Enter ")).append(c.givenName).append("'s Dignity (").append(lowerLimit).append(" to ").append(upperLimit).append(").").toString());
                            try
                            {
                                value = Integer.parseInt(input);
                            }
                            catch(Exception ex)
                            {
                                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nInvalid value.").toString());
                                Project.SingleCustom(t, p, f, w, c, answers);
                                ex.printStackTrace();
                            }
                            if(value >= lowerLimit && value <= upperLimit)
                            {
                                c.dignity = value;
                                if(!w.determineVVirg(c.morality, c.innocence, c.confidence, c.dignity).booleanValue())
                                {
                                    c.ruthless = true;
                                    c.vVirg = Boolean.valueOf(false);
                                    c.vStart = Boolean.valueOf(false);
                                    c.vTaker = 0;
                                } else
                                {
                                    c.ruthless = false;
                                    c.vVirg = Boolean.valueOf(true);
                                    c.vStart = Boolean.valueOf(true);
                                    c.vTaker = -1;
                                }
                                if(!w.determineCVirg(c.morality, c.innocence, c.confidence, c.dignity).booleanValue())
                                {
                                    c.lustful = true;
                                    c.cVirg = Boolean.valueOf(false);
                                    c.cStart = Boolean.valueOf(false);
                                    c.cTaker = 0;
                                } else
                                {
                                    c.lustful = false;
                                    c.cVirg = Boolean.valueOf(true);
                                    c.cStart = Boolean.valueOf(true);
                                    c.cTaker = -1;
                                }
                                if(!w.determineAVirg(c.morality, c.innocence, c.confidence, c.dignity).booleanValue())
                                {
                                    c.meek = true;
                                    c.aVirg = Boolean.valueOf(false);
                                    c.aStart = Boolean.valueOf(false);
                                    c.aTaker = 0;
                                } else
                                {
                                    c.meek = false;
                                    c.aVirg = Boolean.valueOf(true);
                                    c.aStart = Boolean.valueOf(true);
                                    c.aTaker = -1;
                                }
                                if(!w.determineModest(c.morality, c.innocence, c.confidence, c.dignity).booleanValue())
                                {
                                    c.debased = true;
                                    c.modest = Boolean.valueOf(false);
                                    c.mStart = Boolean.valueOf(false);
                                    c.mTaker = 0;
                                } else
                                {
                                    c.debased = false;
                                    c.modest = Boolean.valueOf(true);
                                    c.mStart = Boolean.valueOf(true);
                                    c.mTaker = -1;
                                }
                                Project.SingleCosmetics(t, p, f, w, c, null);
                            } else
                            {
                                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nInvalid value.").toString());
                                Project.SingleCustom(t, p, f, w, c, answers);
                            }
                        } else
                        {
                            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nInvalid value.").toString());
                            Project.SingleCustom(t, p, f, w, c, answers);
                        }
                    } else
                    {
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nInvalid value.").toString());
                        Project.SingleCustom(t, p, f, w, c, answers);
                    }
                } else
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nInvalid value.").toString());
                    Project.SingleCustom(t, p, f, w, c, answers);
                }
            }

            private final Chosen val$c;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$answers[];

            
            {
                c = chosen;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                answers = ai;
                super();
            }
        });
        p.add(DirectInput);
        JButton RandomGen = new JButton("Continue (Random)");
        RandomGen.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Boolean valid = Boolean.valueOf(false);
                int stats[] = new int[4];
                int highs = 0;
                int mids = 0;
                int lows = 0;
                while(!valid.booleanValue()) 
                {
                    highs = 0;
                    mids = 0;
                    lows = 0;
                    valid = Boolean.valueOf(true);
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
                        valid = Boolean.valueOf(false);
                }
                c.morality = stats[0];
                c.innocence = stats[1];
                c.confidence = stats[2];
                c.dignity = stats[3];
                if(!w.determineVVirg(c.morality, c.innocence, c.confidence, c.dignity).booleanValue())
                {
                    c.ruthless = true;
                    c.vVirg = Boolean.valueOf(false);
                    c.vStart = Boolean.valueOf(false);
                    c.vTaker = 0;
                } else
                {
                    c.ruthless = false;
                    c.vVirg = Boolean.valueOf(true);
                    c.vStart = Boolean.valueOf(true);
                    c.vTaker = -1;
                }
                if(!w.determineCVirg(c.morality, c.innocence, c.confidence, c.dignity).booleanValue())
                {
                    c.lustful = true;
                    c.cVirg = Boolean.valueOf(false);
                    c.cStart = Boolean.valueOf(false);
                    c.cTaker = 0;
                } else
                {
                    c.lustful = false;
                    c.cVirg = Boolean.valueOf(true);
                    c.cStart = Boolean.valueOf(true);
                    c.cTaker = -1;
                }
                if(!w.determineAVirg(c.morality, c.innocence, c.confidence, c.dignity).booleanValue())
                {
                    c.meek = true;
                    c.aVirg = Boolean.valueOf(false);
                    c.aStart = Boolean.valueOf(false);
                    c.aTaker = 0;
                } else
                {
                    c.meek = false;
                    c.aVirg = Boolean.valueOf(true);
                    c.aStart = Boolean.valueOf(true);
                    c.aTaker = -1;
                }
                if(!w.determineModest(c.morality, c.innocence, c.confidence, c.dignity).booleanValue())
                {
                    c.debased = true;
                    c.modest = Boolean.valueOf(false);
                    c.mStart = Boolean.valueOf(false);
                    c.mTaker = 0;
                } else
                {
                    c.debased = false;
                    c.modest = Boolean.valueOf(true);
                    c.mStart = Boolean.valueOf(true);
                    c.mTaker = -1;
                }
                Project.SingleCosmetics(t, p, f, w, c, null);
            }

            private final Chosen val$c;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                c = chosen;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(RandomGen);
        JButton Quit = new JButton("Quit");
        Quit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nReally quit?  All customization of this Chosen will be lost!").toString());
                JButton MainMenu = new JButton("Quit");
                MainMenu.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        Project.IntroOne(t, p, f, w);
                    }

                    final _cls31 this$1;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls31.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                });
                p.add(MainMenu);
                JButton Cancel = new JButton("Cancel");
                Cancel.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.SingleCustom(t, p, f, w, c, answers);
                    }

                    final _cls31 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final Chosen val$c;
                    private final int val$answers[];

                    
                    {
                        this$1 = _cls31.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        c = chosen;
                        answers = ai;
                        super();
                    }
                });
                p.add(Cancel);
                p.validate();
                p.repaint();
            }

            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final Chosen val$c;
            private final int val$answers[];

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                c = chosen;
                answers = ai;
                super();
            }
        });
        p.add(Quit);
        p.validate();
        p.repaint();
    }

    public static void CustomQuiz(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Chosen c, final int progress, final int answers[])
    {
        p.removeAll();
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
        if(progress == 0)
            w.append(t, (new StringBuilder("This segment takes the form of a series of questions about how ")).append(c.givenName).append(" thinks and behaves.  Pick the option that comes closest to how ").append(c.heShe()).append(" really is.\n\nQuestion 1: In what way does ").append(c.givenName).append(" try to act?").toString());
        else
        if(progress == 1)
            w.append(t, (new StringBuilder("Question 2: Does ")).append(c.givenName).append(" try to earn others' fear, or does ").append(c.heShe()).append(" try to earn their love?").toString());
        else
        if(progress == 2)
            w.append(t, (new StringBuilder("Question 3: What does ")).append(c.givenName).append(" do after making a mistake that hurts someone else?").toString());
        else
        if(progress == 3)
            w.append(t, (new StringBuilder("Question 4: What does ")).append(c.givenName).append(" tend to do when someone disagrees with ").append(c.himHer()).append(" about what they should do?").toString());
        else
        if(progress == 4)
            w.append(t, (new StringBuilder("Question 5: Which enemies does ")).append(c.givenName).append(" prefer to target?").toString());
        else
        if(progress == 5)
            w.append(t, (new StringBuilder("Question 6: How does ")).append(c.givenName).append(" treat people ").append(c.heShe()).append(" dislikes?").toString());
        else
        if(progress == 6)
            w.append(t, (new StringBuilder("Question 7: What does ")).append(c.givenName).append(" do when someone hurts ").append(c.himHer()).append("?").toString());
        else
        if(progress == 7)
            w.append(t, (new StringBuilder("Question 8: Which social role describes ")).append(c.givenName).append("?").toString());
        else
        if(progress == 8)
            w.append(t, (new StringBuilder("Question 9: How does ")).append(c.givenName).append(" feel after being forced to run from a fight?").toString());
        else
        if(progress == 9)
            w.append(t, (new StringBuilder("Question 10: When is it possible for ")).append(c.givenName).append("'s friends to convince ").append(c.himHer()).append(" to commit a crime?").toString());
        else
        if(progress == 10)
            w.append(t, (new StringBuilder("Question 11: How can one pick a fight with ")).append(c.givenName).append("?").toString());
        else
        if(progress == 11)
            w.append(t, (new StringBuilder("Question 12: How does ")).append(c.givenName).append(" fight against a stronger enemy?").toString());
        else
        if(progress == 12)
            w.append(t, (new StringBuilder("Question 13: How does ")).append(c.givenName).append(" respond when civilians are taken hostage?").toString());
        else
        if(progress == 13)
            w.append(t, (new StringBuilder("Question 14: Will ")).append(c.givenName).append(" lie to protect someone else?").toString());
        else
        if(progress == 14)
            w.append(t, (new StringBuilder("Question 15: How does ")).append(c.givenName).append(" react when stripped while protecting civilians?").toString());
        else
        if(progress == 15)
            w.append(t, (new StringBuilder("Question 16: How does ")).append(c.givenName).append(" respond to being praised?").toString());
        else
        if(progress == 16)
            w.append(t, (new StringBuilder("Question 17: When does ")).append(c.givenName).append(" put ").append(c.himHer()).append("self in harm's way?").toString());
        else
        if(progress == 17)
            w.append(t, (new StringBuilder("Question 18: What does ")).append(c.givenName).append(" think of ").append(c.hisHer()).append(" fanbase?").toString());
        else
        if(progress == 18)
            w.append(t, (new StringBuilder("Question 19: What does ")).append(c.givenName).append(" do when interviewed by reporters after a major defeat?").toString());
        else
        if(progress == 19)
            w.append(t, (new StringBuilder("Question 20: How does ")).append(c.givenName).append(" fight against a weaker enemy?").toString());
        else
        if(progress == 20)
            w.append(t, (new StringBuilder("Question 21: How does ")).append(c.givenName).append(" handle it when ").append(c.hisHer()).append(" friends get into an argument?").toString());
        else
        if(progress == 21)
            w.append(t, (new StringBuilder("Question 22: What does ")).append(c.givenName).append(" do about ").append(c.hisHer()).append(" friends' personality flaws?").toString());
        else
        if(progress == 22)
            w.append(t, (new StringBuilder("Question 23: Does ")).append(c.givenName).append(" claim to be a good person?").toString());
        else
        if(progress == 23)
            w.append(t, (new StringBuilder("Question 24: Does ")).append(c.givenName).append(" pretend to be stronger than ").append(c.heShe()).append(" actually is?").toString());
        else
        if(progress == 24)
            w.append(t, (new StringBuilder("Question 25: When is ")).append(c.givenName).append(" willing to abandon innocent lives?").toString());
        else
        if(progress == 25)
            w.append(t, (new StringBuilder("Question 26: How does ")).append(c.givenName).append(" handle fans who try to get too close to ").append(c.himHer()).append("?").toString());
        else
        if(progress == 26)
            w.append(t, (new StringBuilder("Question 27: How does ")).append(c.givenName).append(" respond to being given orders?").toString());
        else
        if(progress == 27)
            w.append(t, (new StringBuilder("Question 28: How does ")).append(c.givenName).append(" prefer to get help from people?").toString());
        else
        if(progress == 28)
            w.append(t, (new StringBuilder("Question 29: What does ")).append(c.givenName).append(" do with the vast wealth paid to the Chosen?").toString());
        else
        if(progress == 29)
            w.append(t, (new StringBuilder("Question 30: What does ")).append(c.givenName).append(" tell ").append(c.hisHer()).append(" fans to do?").toString());
        else
        if(progress == 30)
            w.append(t, (new StringBuilder("Question 31: Does ")).append(c.givenName).append(" keep ").append(c.hisHer()).append(" promises?").toString());
        else
        if(progress == 31)
            w.append(t, (new StringBuilder("Question 32: What does ")).append(c.givenName).append(" do when ").append(c.heShe()).append(" notices ").append(c.heShe()).append("'s being filmed by spectators?").toString());
        if(answers[progress] != 0)
            w.append(t, (new StringBuilder("  (Previous answer: ")).append(answers[progress]).append(")").toString());
        w.append(t, "\n\n");
        if(progress == 0)
            w.append(t, (new StringBuilder("1: A way that makes others happy.\n2: A way that makes everyone happy.\n3: A way that makes ")).append(c.givenName).append(" ").append(c.himHer()).append("self happy.").toString());
        else
        if(progress == 1)
            w.append(t, "1: Love.\n2: Both fear and love.\n3: Fear.");
        else
        if(progress == 2)
            w.append(t, (new StringBuilder("1: Apologize.\n2: Ignore them.\n3: Try to cover up ")).append(c.hisHer()).append(" involvement.").toString());
        else
        if(progress == 3)
            w.append(t, (new StringBuilder("1: Give in.\n2: Stubbornly hold ")).append(c.hisHer()).append(" position.\n3: Try to reason with the other person.").toString());
        else
        if(progress == 4)
            w.append(t, (new StringBuilder("1: The ones threatening innocents.\n2: Whichever ones cross ")).append(c.hisHer()).append(" path.\n3: Whichever ones stand between ").append(c.himHer()).append(" and safety.").toString());
        else
        if(progress == 5)
            w.append(t, "1: Try to find a way to befriend them.\n2: Spread gossip about them.\n3: Constantly bring up their weaknesses and insecurities.");
        else
        if(progress == 6)
            w.append(t, (new StringBuilder("1: Hold ")).append(c.hisHer()).append(" pain inside.\n2: Hurt them back, by any means necessary.\n3: Whine to anyone who will listen.").toString());
        else
        if(progress == 7)
            w.append(t, "1: Follower.\n2: Leader.\n3: Outcast.");
        else
        if(progress == 8)
            w.append(t, (new StringBuilder("1: Angry at ")).append(c.himHer()).append("self for being too weak.\n2: Perfectly content to have safely escaped.\n3: Angry at ").append(c.hisHer()).append(" allies for putting ").append(c.himHer()).append(" in that position.").toString());
        else
        if(progress == 9)
            w.append(t, (new StringBuilder("1: When ")).append(c.heShe()).append(" thinks it won't hurt anybody.\n2: When ").append(c.heShe()).append("'s sure ").append(c.heShe()).append(" won't be punished for it.\n3: Pretty much always.").toString());
        else
        if(progress == 10)
            w.append(t, (new StringBuilder("1: By hurting others in front of ")).append(c.himHer()).append(".\n2: It isn't possible.\n3: By insulting ").append(c.hisHer()).append(" pride.").toString());
        else
        if(progress == 11)
            w.append(t, "1: Charge straight in.\n2: Call for help.\n3: Find a way to even the odds.");
        else
        if(progress == 12)
            w.append(t, (new StringBuilder("1: ")).append(c.HeShe()).append(" attacks anyway.\n2: ").append(c.HeShe()).append(" surrenders immediately.\n3: ").append(c.HeShe()).append(" cooperates only until ").append(c.heShe()).append(" can find a chance to free them.").toString());
        else
        if(progress == 13)
            w.append(t, (new StringBuilder("1: Only if they make it worth ")).append(c.hisHer()).append(" while.\n2: ").append(c.HeShe()).append(" thinks lying is wrong.\n3: ").append(c.HeShe()).append(" will, if ").append(c.heShe()).append(" thinks they deserve it.").toString());
        else
        if(progress == 14)
            w.append(t, (new StringBuilder("1: Flee, leaving the civilians to their fate.\n2: Laugh it off.\n3: Try to pretend it doesn't bother ")).append(c.himHer()).append(".").toString());
        else
        if(progress == 15)
            w.append(t, (new StringBuilder("1: ")).append(c.HeShe()).append(" assumes that the other person is trying to get something from ").append(c.himHer()).append(".\n2: ").append(c.HeShe()).append(" accepts it as what ").append(c.heShe()).append(" deserves.\n3: ").append(c.HeShe()).append(" feels surprised and happy.").toString());
        else
        if(progress == 16)
            w.append(t, "1: Whenever it protects others.\n2: Whenever there's an appropriate reward.\n3: Almost never.");
        else
        if(progress == 17)
            w.append(t, (new StringBuilder("1: ")).append(c.HeShe()).append(" wants to serve them.\n2: ").append(c.HeShe()).append(" wants them to serve ").append(c.himHer()).append(".\n3: ").append(c.HeShe()).append(" doesn't care about them.").toString());
        else
        if(progress == 18)
            w.append(t, "1: Reassure everyone.\n2: Blame someone else.\n3: Ignore them.");
        else
        if(progress == 19)
            w.append(t, (new StringBuilder("1: Let ")).append(c.hisHer()).append(" guard down and show off.\n2: Remain slow, steady, and cautious.\n3: Take them out quickly and move on.").toString());
        else
        if(progress == 20)
            w.append(t, (new StringBuilder("1: Take the side of whoever is more useful to ")).append(c.himHer()).append(".\n2: Take the side of whoever ").append(c.heShe()).append(" agrees with.\n3: Try to help them resolve their differences with each other.").toString());
        else
        if(progress == 21)
            w.append(t, (new StringBuilder("1: Exploit them for ")).append(c.hisHer()).append(" own benefit.\n2: Tolerate them patiently.\n3: Try to help them overcome their flaws.").toString());
        else
        if(progress == 22)
            w.append(t, (new StringBuilder("1: Yes, but ")).append(c.heShe()).append(" knows ").append(c.heShe()).append("'s not.\n2: Yes, and ").append(c.heShe()).append(" thinks it's true.\n3: No, ").append(c.heShe()).append(" doesn't.").toString());
        else
        if(progress == 23)
            w.append(t, "1: Yes, but not by so much that it can be disproven.\n2: Yes, to an impossible degree.\n3: No, never.");
        else
        if(progress == 24)
            w.append(t, (new StringBuilder("1: Whenever ")).append(c.heShe()).append(" feels like it would be even slightly dangerous for ").append(c.himHer()).append(".\n2: Whenever ").append(c.heShe()).append(" judges it unlikely that ").append(c.heShe()).append("'d be able to successfully save them.\n3: Never.").toString());
        else
        if(progress == 25)
            w.append(t, (new StringBuilder("1: Being mean to them.\n2: Firmly but gently turning them away.\n3: Trying to make them happy without going too far beyond ")).append(c.hisHer()).append(" comfort zone.").toString());
        else
        if(progress == 26)
            w.append(t, (new StringBuilder("1: Spitefully do the opposite.\n2: Obey quickly.\n3: Obey only if ")).append(c.heShe()).append(" agrees with them.").toString());
        else
        if(progress == 27)
            w.append(t, "1: Bargain for something of equal value.\n2: Whine and beg.\n3: Act cute and sweet-talk them.");
        else
        if(progress == 28)
            w.append(t, (new StringBuilder("1: Spend it all on luxuries for ")).append(c.himHer()).append("self.\n2: Personally direct it into projects which benefit society as ").append(c.heShe()).append(" sees it.\n3: Give most of it away to people who know better than ").append(c.himHer()).append(" how it should be spent.").toString());
        else
        if(progress == 29)
            w.append(t, (new StringBuilder("1: Worship ")).append(c.himHer()).append(".\n2: Try to make the world a better place.\n3: Do whatever makes them happy.").toString());
        else
        if(progress == 30)
            w.append(t, (new StringBuilder("1: Only when ")).append(c.heShe()).append(" feels like it.\n2: ").append(c.HeShe()).append(" tries, but often promises more than ").append(c.heShe()).append(" can do.\n3: Yes, ").append(c.heShe()).append("'s trustworthy.").toString());
        else
        if(progress == 31)
            w.append(t, "1: Focus even more on not making any mistakes.\n2: Get flustered.\n3: Ignore them.");
        for(int i = 0; i < 3; i++)
        {
            final int picked = i + 1;
            JButton ThisAnswer = new JButton((new StringBuilder()).append(picked).toString());
            ThisAnswer.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    answers[progress] = picked;
                    if(progress < 31)
                        Project.CustomQuiz(t, p, f, w, c, progress + 1, answers);
                    else
                        Project.SinglePersonality(t, p, f, w, c, answers);
                }

                private final int val$answers[];
                private final int val$progress;
                private final int val$picked;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Chosen val$c;

            
            {
                answers = ai;
                progress = i;
                picked = j;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                c = chosen;
                super();
            }
            });
            p.add(ThisAnswer);
        }

        JButton Pass = new JButton("Pass");
        Pass.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                answers[progress] = 0;
                if(progress < 31)
                    Project.CustomQuiz(t, p, f, w, c, progress + 1, answers);
                else
                    Project.SinglePersonality(t, p, f, w, c, answers);
            }

            private final int val$answers[];
            private final int val$progress;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Chosen val$c;

            
            {
                answers = ai;
                progress = i;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                c = chosen;
                super();
            }
        });
        p.add(Pass);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                if(progress > 0)
                    Project.CustomQuiz(t, p, f, w, c, progress - 1, answers);
                else
                    Project.SingleCustom(t, p, f, w, c, answers);
            }

            private final int val$progress;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Chosen val$c;
            private final int val$answers[];

            
            {
                progress = i;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                c = chosen;
                answers = ai;
                super();
            }
        });
        p.add(Back);
        JButton Quit = new JButton("Quit");
        Quit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nReally quit?  All customization of this Chosen will be lost!").toString());
                JButton MainMenu = new JButton("Quit");
                MainMenu.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        Project.IntroOne(t, p, f, w);
                    }

                    final _cls35 this$1;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls35.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                });
                p.add(MainMenu);
                JButton Cancel = new JButton("Cancel");
                Cancel.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.CustomQuiz(t, p, f, w, c, progress, answers);
                    }

                    final _cls35 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final Chosen val$c;
                    private final int val$progress;
                    private final int val$answers[];

                    
                    {
                        this$1 = _cls35.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        c = chosen;
                        progress = i;
                        answers = ai;
                        super();
                    }
                });
                p.validate();
                p.repaint();
            }

            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final Chosen val$c;
            private final int val$progress;
            private final int val$answers[];

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                c = chosen;
                progress = i;
                answers = ai;
                super();
            }
        });
        p.add(Quit);
        p.validate();
        p.repaint();
    }

    public static void SinglePersonality(JTextPane t, JPanel p, JFrame f, WorldState w, Chosen c, int answers[])
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
        if(!w.determineVVirg(c.morality, c.innocence, c.confidence, c.dignity).booleanValue())
        {
            c.ruthless = true;
            c.vVirg = Boolean.valueOf(false);
            c.vStart = Boolean.valueOf(false);
            c.vTaker = 0;
        } else
        {
            c.ruthless = false;
            c.vVirg = Boolean.valueOf(true);
            c.vStart = Boolean.valueOf(true);
            c.vTaker = -1;
        }
        if(!w.determineCVirg(c.morality, c.innocence, c.confidence, c.dignity).booleanValue())
        {
            c.lustful = true;
            c.cVirg = Boolean.valueOf(false);
            c.cStart = Boolean.valueOf(false);
            c.cTaker = 0;
        } else
        {
            c.lustful = false;
            c.cVirg = Boolean.valueOf(true);
            c.cStart = Boolean.valueOf(true);
            c.cTaker = -1;
        }
        if(!w.determineAVirg(c.morality, c.innocence, c.confidence, c.dignity).booleanValue())
        {
            c.meek = true;
            c.aVirg = Boolean.valueOf(false);
            c.aStart = Boolean.valueOf(false);
            c.aTaker = 0;
        } else
        {
            c.meek = false;
            c.aVirg = Boolean.valueOf(true);
            c.aStart = Boolean.valueOf(true);
            c.aTaker = -1;
        }
        if(!w.determineModest(c.morality, c.innocence, c.confidence, c.dignity).booleanValue())
        {
            c.debased = true;
            c.modest = Boolean.valueOf(false);
            c.mStart = Boolean.valueOf(false);
            c.mTaker = 0;
        } else
        {
            c.debased = false;
            c.modest = Boolean.valueOf(true);
            c.mStart = Boolean.valueOf(true);
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
                c.bonusHATE = Boolean.valueOf(true);
            else
                c.bonusHATE = Boolean.valueOf(false);
            if(c.innocence > 66)
                c.bonusPLEA = Boolean.valueOf(true);
            else
                c.bonusPLEA = Boolean.valueOf(false);
            if(c.confidence > 66)
                c.bonusINJU = Boolean.valueOf(true);
            else
                c.bonusINJU = Boolean.valueOf(false);
            if(c.dignity > 66)
                c.bonusEXPO = Boolean.valueOf(true);
            else
                c.bonusEXPO = Boolean.valueOf(false);
        }
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").append(c.customSummary()).toString());
        int highs = 0;
        int mids = 0;
        int lows = 0;
        if(c.morality > 66)
        {
            w.blueAppend(t, (new StringBuilder("\nMorality ")).append(c.morality).append(": Core").toString());
            highs++;
        } else
        if(c.morality > 33)
        {
            w.greenAppend(t, (new StringBuilder("\nMorality ")).append(c.morality).append(": Significant").toString());
            mids++;
        } else
        {
            w.redAppend(t, (new StringBuilder("\nMorality ")).append(c.morality).append(": Minor").toString());
            lows++;
        }
        if(!c.vVirg.booleanValue())
            w.redAppend(t, " (BROKEN)");
        if(c.innocence > 66)
        {
            w.blueAppend(t, (new StringBuilder("\nInnocence ")).append(c.innocence).append(": Core").toString());
            highs++;
        } else
        if(c.innocence > 33)
        {
            w.greenAppend(t, (new StringBuilder("\nInnocence ")).append(c.innocence).append(": Significant").toString());
            mids++;
        } else
        {
            w.redAppend(t, (new StringBuilder("\nInnocence ")).append(c.innocence).append(": Minor").toString());
            lows++;
        }
        if(!c.cVirg.booleanValue())
            w.redAppend(t, " (BROKEN)");
        if(c.confidence > 66)
        {
            w.blueAppend(t, (new StringBuilder("\nConfidence ")).append(c.confidence).append(": Core").toString());
            highs++;
        } else
        if(c.confidence > 33)
        {
            w.greenAppend(t, (new StringBuilder("\nConfidence ")).append(c.confidence).append(": Significant").toString());
            mids++;
        } else
        {
            w.redAppend(t, (new StringBuilder("\nConfidence ")).append(c.confidence).append(": Minor").toString());
            lows++;
        }
        if(!c.aVirg.booleanValue())
            w.redAppend(t, " (BROKEN)");
        if(c.dignity > 66)
        {
            w.blueAppend(t, (new StringBuilder("\nDignity ")).append(c.dignity).append(": Core").toString());
            highs++;
        } else
        if(c.dignity > 33)
        {
            w.greenAppend(t, (new StringBuilder("\nDignity ")).append(c.dignity).append(": Significant").toString());
            mids++;
        } else
        {
            w.redAppend(t, (new StringBuilder("\nDignity ")).append(c.dignity).append(": Minor").toString());
            lows++;
        }
        if(!c.modest.booleanValue())
            w.redAppend(t, " (BROKEN)");
        w.append(t, "\nValid Custom Partners:");
        int found = 0;
        for(int i = 0; i < w.save.customRoster.length; i++)
        {
            int foundHighs = 0;
            int foundMids = 0;
            int foundLows = 0;
            Boolean compatible = Boolean.valueOf(true);
            Chosen subject = w.save.customRoster[i];
            if(subject.morality > 66)
            {
                foundHighs++;
                if(c.morality > 66)
                    compatible = Boolean.valueOf(false);
            } else
            if(subject.morality > 33)
            {
                foundMids++;
                if(c.morality > 33 && c.morality < 67)
                    compatible = Boolean.valueOf(false);
            } else
            {
                foundLows++;
                if(c.morality < 34)
                    compatible = Boolean.valueOf(false);
            }
            if(subject.innocence > 66)
            {
                foundHighs++;
                if(c.innocence > 66)
                    compatible = Boolean.valueOf(false);
            } else
            if(subject.innocence > 33)
            {
                foundMids++;
                if(c.innocence > 33 && c.innocence < 67)
                    compatible = Boolean.valueOf(false);
            } else
            {
                foundLows++;
                if(c.innocence < 34)
                    compatible = Boolean.valueOf(false);
            }
            if(subject.confidence > 66)
            {
                foundHighs++;
                if(c.confidence > 66)
                    compatible = Boolean.valueOf(false);
            } else
            if(subject.confidence > 33)
            {
                foundMids++;
                if(c.confidence > 33 && c.confidence < 67)
                    compatible = Boolean.valueOf(false);
            } else
            {
                foundLows++;
                if(c.confidence < 34)
                    compatible = Boolean.valueOf(false);
            }
            if(subject.dignity > 66)
            {
                foundHighs++;
                if(c.dignity > 66)
                    compatible = Boolean.valueOf(false);
            } else
            if(subject.dignity > 33)
            {
                foundMids++;
                if(c.dignity > 33 && c.dignity < 67)
                    compatible = Boolean.valueOf(false);
            } else
            {
                foundLows++;
                if(c.dignity < 34)
                    compatible = Boolean.valueOf(false);
            }
            if(highs == 2 && foundHighs == 2 || mids == 2 && foundMids == 2 || lows == 2 && foundLows == 2)
                compatible = Boolean.valueOf(false);
            if(compatible.booleanValue())
            {
                found++;
                w.append(t, (new StringBuilder("\n ")).append(subject.mainName).toString());
            }
        }

        if(found == 0)
            w.append(t, "\nNone");
        w.append(t, "\n\nYou can adjust Vulnerability Breaks and cosmetic details here.  Note that going back to the previous screen will reset these to their default values.");
        JButton Breaks = new JButton("Vulnerability Breaks");
        Breaks.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.SingleVulnerabilities(t, p, f, w, c, answers, 0);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Chosen val$c;
            private final int val$answers[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                c = chosen;
                answers = ai;
                super();
            }
        });
        p.add(Breaks);
        JButton Cosmetics = new JButton("Cosmetics");
        Cosmetics.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.SingleNameAndClothes(t, p, f, w, c, answers, 0);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Chosen val$c;
            private final int val$answers[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                c = chosen;
                answers = ai;
                super();
            }
        });
        p.add(Cosmetics);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                c.mainName = null;
                if(answers == null)
                    Project.SingleCustom(t, p, f, w, c, null);
                else
                    Project.CustomQuiz(t, p, f, w, c, 31, answers);
            }

            private final Chosen val$c;
            private final int val$answers[];
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                c = chosen;
                answers = ai;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
        });
        p.add(Back);
        JButton Finish = new JButton("Finish");
        Finish.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nThis will add the Chosen to your save file's custom roster, and you will not be able to make further adjustments.").toString());
                JButton Cancel = new JButton("Back");
                Cancel.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.SingleCosmetics(t, p, f, w, c, answers);
                    }

                    final _cls39 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final Chosen val$c;
                    private final int val$answers[];

                    
                    {
                        this$1 = _cls39.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        c = chosen;
                        answers = ai;
                        super();
                    }
                });
                p.add(Cancel);
                JButton Done = new JButton("Done");
                Done.addActionListener(new ActionListener() {

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
                            included[i] = Boolean.valueOf(true);

                        Project.CampaignMenu(t, p, f, w, included);
                    }

                    final _cls39 this$1;
                    private final WorldState val$w;
                    private final Chosen val$c;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls39.this;
                        w = worldstate;
                        c = chosen;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                });
                p.add(Done);
                p.validate();
                p.repaint();
            }

            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final Chosen val$c;
            private final int val$answers[];

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                c = chosen;
                answers = ai;
                super();
            }
        });
        p.add(Finish);
        p.validate();
        p.repaint();
    }

    public static void SingleNameAndClothes(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Chosen c, final int answers[], final int progress)
    {
        p.removeAll();
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
        if(progress == 0)
        {
            if(c.mainName.equals(c.givenName) || c.mainName.equals(c.familyName))
                w.append(t, (new StringBuilder("The first step is to decide what ")).append(c.givenName).append(" will call ").append(c.himHer()).append("self.  ").append(c.HeShe()).append("'s currently just going by '").append(c.mainName).append("', but most Chosen pick an alias for themselves.  Which should ").append(c.heShe()).append(" choose?").toString());
            else
                w.append(t, (new StringBuilder("The first step is to decide what ")).append(c.givenName).append(" will call ").append(c.himHer()).append("self.  ").append(c.HeShe()).append(" likes the sound of '").append(c.mainName).append("', but the civilian identities of the Chosen are a matter of public record, so it wouldn't be too strange for ").append(c.himHer()).append(" to go by ").append(c.hisHer()).append(" real name.  Which should ").append(c.heShe()).append(" choose?").toString());
            if(!c.mainName.equals(c.givenName))
            {
                JButton CurrentName = new JButton(c.mainName);
                CurrentName.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 1);
                    }

                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final Chosen val$c;
                    private final int val$answers[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                c = chosen;
                answers = ai;
                super();
            }
                });
                p.add(CurrentName);
            }
            JButton GivenName = new JButton(c.givenName);
            GivenName.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    c.mainName = c.givenName;
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 1);
                }

                private final Chosen val$c;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final int val$answers[];

            
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
            });
            p.add(GivenName);
            if(!c.mainName.equals(c.familyName) && c.familyName.length() > 0)
            {
                JButton FamilyName = new JButton(c.familyName);
                FamilyName.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        c.mainName = c.familyName;
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 1);
                    }

                    private final Chosen val$c;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final int val$answers[];

            
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
                });
                p.add(FamilyName);
            }
            JButton SomethingElse = new JButton("Something Else");
            SomethingElse.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    String input = JOptionPane.showInputDialog((new StringBuilder("Type the name to be used here.  Leave blank to continue using '")).append(c.mainName).append("'.").toString());
                    if(input != null && input.length() > 0)
                        c.mainName = input;
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 1);
                }

                private final Chosen val$c;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final int val$answers[];

            
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
            });
            p.add(SomethingElse);
        } else
        if(progress == 1)
        {
            if(c.nounName.length() > 0)
                w.append(t, (new StringBuilder("Most Chosen also use a descriptive title that defines how they see themselves.  ")).append(c.mainName).append("'s first idea is to use '").append(c.adjectiveName).append(" ").append(c.nounName).append("', so that ").append(c.heShe()).append("'d be '").append(c.adjectiveName).append(" ").append(c.nounName).append(" ").append(c.mainName).append("'.  Should ").append(c.heShe()).append(" use something different?").toString());
            else
            if(c.adjectiveName.equals("none"))
                w.append(t, (new StringBuilder("Most Chosen also use a descriptive title that defines how they see themselves.  However, none immediately comes to ")).append(c.mainName).append(".  Should ").append(c.heShe()).append(" use one at all?").toString());
            else
                w.append(t, (new StringBuilder("Most Chosen also use a descriptive title that defines how they see themselves.  ")).append(c.mainName).append("'s first idea is to use '").append(c.adjectiveName).append("', so that ").append(c.heShe()).append("'d be '").append(c.adjectiveName).append(" ").append(c.mainName).append("'.  Should ").append(c.heShe()).append(" use something different?").toString());
            JButton CurrentTitle = new JButton(c.adjectiveName);
            if(c.nounName.length() > 0)
                CurrentTitle.setText((new StringBuilder(String.valueOf(c.adjectiveName))).append(" ").append(c.nounName).toString());
            CurrentTitle.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 2);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Chosen val$c;
                private final int val$answers[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                c = chosen;
                answers = ai;
                super();
            }
            });
            p.add(CurrentTitle);
            JButton SomethingElse = new JButton("Something Else");
            SomethingElse.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    c.nounName = "";
                    String input = JOptionPane.showInputDialog((new StringBuilder("Type the title to be used here.  Leave blank to just go by '")).append(c.mainName).append("'.").toString());
                    if(input != null)
                        if(input.length() > 0)
                            c.adjectiveName = input;
                        else
                            c.adjectiveName = "none";
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 2);
                }

                private final Chosen val$c;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final int val$answers[];

            
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
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
                    w.append(t, (new StringBuilder(String.valueOf(c.hisHer()))).append(" Chosen form").toString());
                else
                    w.append(t, c.mainName);
            } else
            if(c.nounName.length() > 0)
                w.append(t, (new StringBuilder(String.valueOf(c.adjectiveName))).append(" ").append(c.nounName).append(" ").append(c.mainName).toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(c.adjectiveName))).append(" ").append(c.mainName).toString());
            w.append(t, (new StringBuilder(", ")).append(c.givenName).append(" needs to speak an incantation of ").append(c.hisHer()).append(" choice.  The first that comes to ").append(c.hisHer()).append(" mind is '").append(c.incantation).append("'.").toString());
            JButton Keep = new JButton("Keep");
            Keep.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 3);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Chosen val$c;
                private final int val$answers[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                c = chosen;
                answers = ai;
                super();
            }
            });
            p.add(Keep);
            JButton Change = new JButton("Change");
            Change.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    String input = JOptionPane.showInputDialog("Type the new incantation here.  Leave blank to leave it unchanged.");
                    if(input != null && input.length() > 0)
                        c.incantation = input;
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 3);
                }

                private final Chosen val$c;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final int val$answers[];

            
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
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
                result = (new StringBuilder(String.valueOf(c.adjectiveName))).append(" ").append(result).toString();
            result = (new StringBuilder(String.valueOf(c.incantation))).append("  ").append(result).append(", transform!").toString();
            w.append(t, (new StringBuilder(String.valueOf(c.givenName))).append("'s civilian clothes will disintegrate when ").append(c.heShe()).append(" says '").append(result).append("'  In their place, garments and equipment woven of psychic energy representing ").append(c.hisHer()).append(" true nature will materialize.  Click 'Change' to give ").append(c.himHer()).append(" something different, or click the button for the current item to keep it.\n\nFirst off, what does ").append(c.heShe()).append(" wear to cover ").append(c.hisHer()).append(" chest?").toString());
            JButton Current = new JButton(c.topDesc());
            Current.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 5);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Chosen val$c;
                private final int val$answers[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                c = chosen;
                answers = ai;
                super();
            }
            });
            p.add(Current);
            JButton Change = new JButton("Change");
            Change.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    String input = JOptionPane.showInputDialog((new StringBuilder("Type the name of the garment here.  Leave blank to use '")).append(c.topDesc()).append("'.").toString());
                    Boolean changed = Boolean.valueOf(false);
                    if(input != null && !input.equals(c.topDesc()) && input.length() > 0)
                        changed = Boolean.valueOf(true);
                    if(changed.booleanValue())
                    {
                        c.topCover = input;
                        c.accessory = "none";
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 4);
                    } else
                    {
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 5);
                    }
                }

                private final Chosen val$c;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final int val$answers[];

            
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
            });
            p.add(Change);
        } else
        if(progress == 4)
        {
            if(c.gender.equals("male"))
                w.append(t, (new StringBuilder("And in order to get at ")).append(c.hisHer()).append(" nipples, does one go up ").append(c.hisHer()).append(" ").append(c.topDesc()).append(", into ").append(c.hisHer()).append(" ").append(c.topDesc()).append(", down ").append(c.hisHer()).append(" ").append(c.topDesc()).append(", or around ").append(c.hisHer()).append(" ").append(c.topDesc()).append("?").toString());
            else
                w.append(t, (new StringBuilder("And in order to get at ")).append(c.hisHer()).append(" breasts, does one go up ").append(c.hisHer()).append(" ").append(c.topDesc()).append(", into ").append(c.hisHer()).append(" ").append(c.topDesc()).append(", down ").append(c.hisHer()).append(" ").append(c.topDesc()).append(", or around ").append(c.hisHer()).append(" ").append(c.topDesc()).append("?").toString());
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

                    public void actionPerformed(ActionEvent e)
                    {
                        c.topAccess = finalMethod;
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 5);
                    }

                    private final Chosen val$c;
                    private final String val$finalMethod;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final int val$answers[];

            
            {
                c = chosen;
                finalMethod = s;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
                });
                p.add(ThisOne);
            }

        } else
        if(progress == 5)
        {
            w.append(t, (new StringBuilder("Next, what does ")).append(c.heShe()).append(" wear to cover ").append(c.hisHer()).append(" hips?").toString());
            JButton Current = new JButton(c.bottomDesc());
            Current.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 7);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Chosen val$c;
                private final int val$answers[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                c = chosen;
                answers = ai;
                super();
            }
            });
            p.add(Current);
            JButton Change = new JButton("Change");
            Change.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    String input = JOptionPane.showInputDialog((new StringBuilder("Type the name of the garment here.  Leave blank to use '")).append(c.bottomDesc()).append("'.").toString());
                    Boolean changed = Boolean.valueOf(false);
                    if(input != null && !input.equals(c.bottomDesc()) && input.length() > 0)
                        changed = Boolean.valueOf(true);
                    if(changed.booleanValue())
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

                private final Chosen val$c;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final int val$answers[];

            
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
            });
            p.add(Change);
        } else
        if(progress == 6)
        {
            if(c.gender.equals("female"))
                w.append(t, (new StringBuilder("And in order to get at ")).append(c.hisHer()).append(" pussy, does one go up ").append(c.hisHer()).append(" ").append(c.bottomDesc()).append(", into ").append(c.hisHer()).append(" ").append(c.bottomDesc()).append(", down ").append(c.hisHer()).append(" ").append(c.bottomDesc()).append(", or around ").append(c.hisHer()).append(" ").append(c.bottomDesc()).append("?").toString());
            else
                w.append(t, (new StringBuilder("And in order to get at ")).append(c.hisHer()).append(" penis, does one go up ").append(c.hisHer()).append(" ").append(c.bottomDesc()).append(", into ").append(c.hisHer()).append(" ").append(c.bottomDesc()).append(", down ").append(c.hisHer()).append(" ").append(c.bottomDesc()).append(", or around ").append(c.hisHer()).append(" ").append(c.bottomDesc()).append("?").toString());
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

                    public void actionPerformed(ActionEvent e)
                    {
                        c.bottomAccess = finalMethod;
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 7);
                    }

                    private final Chosen val$c;
                    private final String val$finalMethod;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final int val$answers[];

            
            {
                c = chosen;
                finalMethod = s;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
                });
                p.add(ThisOne);
            }

        } else
        if(progress == 7)
        {
            w.append(t, (new StringBuilder("What footwear does ")).append(c.mainName).append("'s transformation give ").append(c.himHer()).append("?  Enter 'none' (without the quotes) to have ").append(c.himHer()).append(" go barefoot.").toString());
            JButton Current = new JButton(c.feetType);
            Current.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 8);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Chosen val$c;
                private final int val$answers[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                c = chosen;
                answers = ai;
                super();
            }
            });
            p.add(Current);
            JButton Change = new JButton("Change");
            Change.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    String input = JOptionPane.showInputDialog((new StringBuilder("Type the name of the garment here.  Leave blank to use '")).append(c.feetType).append("'.").toString());
                    if(input != null && input.length() > 0)
                    {
                        if(!c.feetType.equals(input))
                            c.accessory = "none";
                        c.feetType = input;
                    }
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 8);
                }

                private final Chosen val$c;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final int val$answers[];

            
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
            });
            p.add(Change);
        } else
        if(progress == 8)
        {
            w.append(t, (new StringBuilder("When ")).append(c.heShe()).append("'s transformed, ").append(c.mainName).append(" is surrounded by '").append(c.color).append("' light.  Is this alright?").toString());
            JButton Keep = new JButton("Yes");
            Keep.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 9);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Chosen val$c;
                private final int val$answers[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                c = chosen;
                answers = ai;
                super();
            }
            });
            p.add(Keep);
            JButton Change = new JButton("Change");
            Change.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    String input = JOptionPane.showInputDialog((new StringBuilder("Type the light description to be used.  Leave blank to use '")).append(c.color).append("'.").toString());
                    if(input != null && input.length() > 0)
                    {
                        if(!c.color.equals(input))
                            c.accessory = "none";
                        c.color = input;
                    }
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 9);
                }

                private final Chosen val$c;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final int val$answers[];

            
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
            });
            p.add(Change);
        } else
        if(progress == 9)
        {
            w.append(t, (new StringBuilder("Currently, ")).append(c.mainName).append(" is set to fight using ").append(c.hisHer()).append(" ").append(c.weapon).append(".  Is this okay?").toString());
            JButton Keep = new JButton("Yes");
            Keep.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.SingleNameAndClothes(t, p, f, w, c, answers, 11);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Chosen val$c;
                private final int val$answers[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                c = chosen;
                answers = ai;
                super();
            }
            });
            p.add(Keep);
            JButton Change = new JButton("Change");
            Change.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Boolean changed = Boolean.valueOf(false);
                    String input = JOptionPane.showInputDialog((new StringBuilder("Type the name of the weapon to be used.  Leave blank to use '")).append(c.weapon).append("'.").toString());
                    if(input != null && input.length() > 0)
                    {
                        if(!c.weapon.equals(input))
                        {
                            c.accessory = "none";
                            changed = Boolean.valueOf(true);
                        }
                        c.weapon = input;
                    }
                    if(changed.booleanValue())
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 10);
                    else
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 11);
                }

                private final Chosen val$c;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final int val$answers[];

            
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
            });
            p.add(Change);
        } else
        if(progress == 10)
        {
            w.append(t, (new StringBuilder("Does ")).append(c.mainName).append(" swing ").append(c.hisHer()).append(" ").append(c.weapon).append(", shoot ").append(c.hisHer()).append(" ").append(c.weapon).append(", command ").append(c.hisHer()).append(" ").append(c.weapon).append(", or is ").append(c.hisHer()).append(" weapon a part of ").append(c.himHer()).append("?").toString());
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

                    public void actionPerformed(ActionEvent e)
                    {
                        c.customWeaponType = finalMethod;
                        Project.SingleNameAndClothes(t, p, f, w, c, answers, 11);
                    }

                    private final Chosen val$c;
                    private final String val$finalMethod;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final int val$answers[];

            
            {
                c = chosen;
                finalMethod = s;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
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
                w.append(t, (new StringBuilder(String.valueOf(c.mainName))).append("'s outfit doesn't currently include panties.  Should that be changed?").toString());
                JButton Change = new JButton("Wear panties");
                Change.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        c.underType = "panties";
                        c.accessory = "none";
                        Project.SingleCosmetics(t, p, f, w, c, answers);
                    }

                    private final Chosen val$c;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final int val$answers[];

            
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
                });
                p.add(Change);
            } else
            {
                w.append(t, (new StringBuilder("Would you prefer for ")).append(c.mainName).append(" to stop wearing anything under ").append(c.hisHer()).append(" ").append(c.bottomDesc()).append("?").toString());
                JButton Change = new JButton("Wear nothing");
                Change.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        c.underType = "none";
                        c.accessory = "none";
                        Project.SingleCosmetics(t, p, f, w, c, answers);
                    }

                    private final Chosen val$c;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final int val$answers[];

            
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
                });
                p.add(Change);
            }
            JButton Keep = new JButton("Leave it as is");
            Keep.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.SingleCosmetics(t, p, f, w, c, answers);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Chosen val$c;
                private final int val$answers[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                c = chosen;
                answers = ai;
                super();
            }
            });
            p.add(Keep);
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

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

            private final int val$progress;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Chosen val$c;
            private final int val$answers[];

            
            {
                progress = i;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                c = chosen;
                answers = ai;
                super();
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
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
        if(progress == 0)
        {
            if(c.morality > 66)
            {
                w.append(t, (new StringBuilder(String.valueOf(c.givenName))).append("'s sense of Morality is a ").toString());
                w.blueAppend(t, "Core");
                w.append(t, (new StringBuilder(" part of ")).append(c.hisHer()).append(" identity").toString());
            } else
            if(c.morality > 33)
            {
                w.append(t, (new StringBuilder(String.valueOf(c.givenName))).append("'s Morality is a ").toString());
                w.greenAppend(t, "Significant");
                w.append(t, (new StringBuilder(" component of ")).append(c.hisHer()).append(" personality").toString());
            }
            if(c.morality > 33)
            {
                if(c.gender.equals("male"))
                    w.append(t, (new StringBuilder(", so ")).append(c.heShe()).append(" wouldn't normally engage in 'immoral' activities like violence and sex with other men.  ").toString());
                else
                    w.append(t, (new StringBuilder(", so ")).append(c.heShe()).append(" wouldn't normally engage in 'immoral' activities like violence and promiscuity.  ").toString());
                if(c.vVirg.booleanValue())
                    w.append(t, (new StringBuilder("If this Vulnerability is broken, it means ")).append(c.heShe()).append(" has already been raped by the time the game starts.").toString());
                else
                    w.append(t, (new StringBuilder("However, ")).append(c.heShe()).append(" is set to have already been raped before the game starts.").toString());
            } else
            {
                w.append(t, "Morality is only a ");
                w.redAppend(t, "Minor");
                w.append(t, (new StringBuilder(" concern for ")).append(c.givenName).append(", ").toString());
                if(c.vVirg.booleanValue())
                {
                    if(c.gender.equals("male"))
                        w.append(t, (new StringBuilder("but ")).append(c.heShe()).append(" hasn't gotten curious enough to try having sex with other men yet.  Break this Vulnerability to change that.").toString());
                    else
                        w.append(t, (new StringBuilder("but ")).append(c.heShe()).append(" hasn't gotten around to having sex yet.  Break this Vulnerability to change that.").toString());
                } else
                if(c.gender.equals("male"))
                    w.append(t, (new StringBuilder("so ")).append(c.heShe()).append(" has seen no reason to avoid having sex with other men.  Restore this Vulnerability to have ").append(c.himHer()).append(" start as an anal virgin instead.").toString());
                else
                    w.append(t, (new StringBuilder("so ")).append(c.heShe()).append(" has seen no reason to avoid having sex.  Restore this Vulnerability to have ").append(c.himHer()).append(" start as a virgin instead.").toString());
            }
        } else
        if(progress == 1)
        {
            if(c.innocence > 66)
            {
                w.append(t, (new StringBuilder(String.valueOf(c.givenName))).append("'s Innocence is a ").toString());
                w.blueAppend(t, "Core");
                w.append(t, (new StringBuilder(" part of ")).append(c.hisHer()).append(" identity").toString());
            } else
            if(c.innocence > 33)
            {
                w.append(t, (new StringBuilder(String.valueOf(c.givenName))).append(" has retained ").toString());
                w.greenAppend(t, "Significant");
                w.append(t, " Innocence regarding sexual matters");
            }
            if(c.innocence > 33)
            {
                w.append(t, (new StringBuilder(", so ")).append(c.heShe()).append(" wouldn't normally have any idea how good it can feel to be forced to cum during battle.  ").toString());
                if(c.cVirg.booleanValue())
                    w.append(t, (new StringBuilder("If this Vulnerability is broken, it means ")).append(c.heShe()).append(" has already become addicted to this feeling.").toString());
                else
                    w.append(t, (new StringBuilder("However, ")).append(c.heShe()).append(" is set to have already become addicted to this feeling when the game starts.").toString());
            } else
            {
                w.append(t, (new StringBuilder(String.valueOf(c.givenName))).append(" has retained only a ").toString());
                w.redAppend(t, "Minor");
                w.append(t, " amount of Innocence");
                if(c.cVirg.booleanValue())
                    w.append(t, (new StringBuilder(", but ")).append(c.heShe()).append("'s still sane enough to hold back from cumming during battle.  Break this Vulnerability to change that.").toString());
                else
                    w.append(t, (new StringBuilder(", so ")).append(c.heShe()).append(" happily allows ").append(c.himHer()).append("self to cum during battle.  Restore this Vulnerability to have ").append(c.himHer()).append(" start out with some restraint.").toString());
            }
        } else
        if(progress == 2)
        {
            if(c.confidence > 66)
            {
                w.append(t, (new StringBuilder(String.valueOf(c.givenName))).append("'s Confidence is a ").toString());
                w.blueAppend(t, "Core");
                w.append(t, (new StringBuilder(" part of ")).append(c.hisHer()).append(" identity").toString());
            } else
            if(c.confidence > 33)
            {
                w.append(t, (new StringBuilder(String.valueOf(c.givenName))).append(" has a ").toString());
                w.greenAppend(t, "Significant");
                w.append(t, " amount of Confidence");
            }
            if(c.confidence > 33)
            {
                w.append(t, (new StringBuilder(" because of ")).append(c.hisHer()).append(" past victories against the Demons.  ").toString());
                if(c.aVirg.booleanValue())
                    w.append(t, (new StringBuilder("If this Vulnerability is broken, it means ")).append(c.heShe()).append(" has suffered a crushing defeat and been tortured before.").toString());
                else
                    w.append(t, (new StringBuilder("However, ")).append(c.heShe()).append(" is set to have already had ").append(c.hisHer()).append(" self-image shaken by being defeated and tortured recently.").toString());
            } else
            {
                w.append(t, (new StringBuilder(String.valueOf(c.givenName))).append(" has only a ").toString());
                w.redAppend(t, "Minor");
                w.append(t, " amount of Confidence left");
                if(c.aVirg.booleanValue())
                    w.append(t, (new StringBuilder(", but this is due purely to ")).append(c.hisHer()).append(" weak personality.  Break this Vulnerability to have ").append(c.hisHer()).append(" self-esteem damaged by a recent capture and torture.").toString());
                else
                    w.append(t, (new StringBuilder(", largely because of ")).append(c.hisHer()).append(" recent defeat and torture at the hands of the Demons.  Restore this Vulnerability to erase this event and let ").append(c.himHer()).append(" start out with at least a little bit of hope.").toString());
            }
        } else
        if(progress == 3)
        {
            if(c.dignity > 66)
            {
                w.append(t, (new StringBuilder(String.valueOf(c.givenName))).append("'s need for Dignity is a ").toString());
                w.blueAppend(t, "Core");
                w.append(t, (new StringBuilder(" part of ")).append(c.hisHer()).append(" identity").toString());
            } else
            if(c.dignity > 33)
            {
                w.append(t, (new StringBuilder(String.valueOf(c.givenName))).append(" maintains a ").toString());
                w.greenAppend(t, "Significant");
                w.append(t, " amount of Dignity");
            }
            if(c.dignity > 33)
            {
                w.append(t, ", wanting to be viewed as a mighty, unassailable warrior.  ");
                if(c.modest.booleanValue())
                    w.append(t, (new StringBuilder("If this Vulnerability is broken, it means ")).append(c.heShe()).append(" has been stripped and had ").append(c.hisHer()).append(" humiliation broadcast to the world.").toString());
                else
                    w.append(t, (new StringBuilder("However, ")).append(c.heShe()).append(" is set to have already had ").append(c.hisHer()).append(" public image damaged by being stripped during battle and having the footage broadcast to the world.").toString());
            } else
            {
                w.append(t, (new StringBuilder(String.valueOf(c.givenName))).append(" has only a ").toString());
                w.redAppend(t, "Minor");
                w.append(t, (new StringBuilder(" interest in retaining ")).append(c.hisHer()).append(" dignity").toString());
                if(c.modest.booleanValue())
                    w.append(t, (new StringBuilder(", but ")).append(c.heShe()).append(" has managed to avoid any public humiliation so far, mostly through pure luck.  Break this Vulnerability to have footage of ").append(c.hisHer()).append(" defeat and stripping be broadcast to the world.").toString());
                else
                    w.append(t, (new StringBuilder(", and as a result, ")).append(c.heShe()).append(" hasn't managed to stop footage of ").append(c.hisHer()).append(" defeat and stripping from being spread across the world.  Restore this Vulnerability to make it so that ").append(c.heShe()).append(" hasn't yet been captured in such a shameful state.").toString());
            }
        }
        if(progress == 0 && c.vVirg.booleanValue() || progress == 1 && c.cVirg.booleanValue() || progress == 2 && c.aVirg.booleanValue() || progress == 3 && c.modest.booleanValue())
        {
            JButton Break = new JButton("Break");
            Break.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    if(progress == 0)
                    {
                        c.vVirg = Boolean.valueOf(false);
                        c.ruthless = true;
                        c.vTaker = 0;
                        c.vStart = Boolean.valueOf(false);
                    } else
                    if(progress == 1)
                    {
                        c.cVirg = Boolean.valueOf(false);
                        c.lustful = true;
                        c.cTaker = 0;
                        c.cStart = Boolean.valueOf(false);
                    } else
                    if(progress == 2)
                    {
                        c.aVirg = Boolean.valueOf(false);
                        c.meek = true;
                        c.aTaker = 0;
                        c.aStart = Boolean.valueOf(false);
                    } else
                    {
                        c.modest = Boolean.valueOf(false);
                        c.debased = true;
                        c.mTaker = 0;
                        c.mStart = Boolean.valueOf(false);
                    }
                    if(progress < 3)
                        Project.SingleVulnerabilities(t, p, f, w, c, answers, progress + 1);
                    else
                        Project.SingleCosmetics(t, p, f, w, c, answers);
                }

                private final int val$progress;
                private final Chosen val$c;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final int val$answers[];

            
            {
                progress = i;
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
            });
            p.add(Break);
        } else
        {
            JButton Restore = new JButton("Restore");
            Restore.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    if(progress == 0)
                    {
                        c.vVirg = Boolean.valueOf(true);
                        c.ruthless = false;
                        c.vTaker = -1;
                        c.vStart = Boolean.valueOf(true);
                    } else
                    if(progress == 1)
                    {
                        c.cVirg = Boolean.valueOf(true);
                        c.lustful = false;
                        c.cTaker = -1;
                        c.cStart = Boolean.valueOf(true);
                    } else
                    if(progress == 2)
                    {
                        c.aVirg = Boolean.valueOf(true);
                        c.meek = false;
                        c.aTaker = -1;
                        c.aStart = Boolean.valueOf(true);
                    } else
                    {
                        c.modest = Boolean.valueOf(true);
                        c.debased = false;
                        c.mTaker = -1;
                        c.mStart = Boolean.valueOf(true);
                    }
                    if(progress < 3)
                        Project.SingleVulnerabilities(t, p, f, w, c, answers, progress + 1);
                    else
                        Project.SingleCosmetics(t, p, f, w, c, answers);
                }

                private final int val$progress;
                private final Chosen val$c;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final int val$answers[];

            
            {
                progress = i;
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                answers = ai;
                super();
            }
            });
            p.add(Restore);
        }
        JButton Continue = new JButton("Continue");
        Continue.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                if(progress < 3)
                    Project.SingleVulnerabilities(t, p, f, w, c, answers, progress + 1);
                else
                    Project.SingleCosmetics(t, p, f, w, c, answers);
            }

            private final int val$progress;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Chosen val$c;
            private final int val$answers[];

            
            {
                progress = i;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                c = chosen;
                answers = ai;
                super();
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
            if(enabled[i].booleanValue())
            {
                w.append(t, (new StringBuilder("\n")).append(w.save.customRoster[i].mainName).append(": ").toString());
                w.greenAppend(t, "INCLUDED");
            } else
            {
                w.grayAppend(t, (new StringBuilder("\n")).append(w.save.customRoster[i].mainName).append(": ").toString());
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

                public void actionPerformed(ActionEvent e)
                {
                    Project.CampaignCustomToggle(t, p, f, w, enabled, page - 1, deleting);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Boolean val$enabled[];
                private final int val$page;
                private final Boolean val$deleting;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                enabled = aboolean;
                page = i;
                deleting = boolean1;
                super();
            }
            });
            p.add(Previous);
        }
        for(int i = 0; i < 5; i++)
            if(i + page * 5 < enabled.length)
            {
                final int index = i + page * 5;
                shownNames[i] = w.save.customRoster[index].mainName;
                changePortrait(w.save.customRoster[index].convertGender(), w.save.customRoster[index].type, Boolean.valueOf(false), Boolean.valueOf(false), w, shownNames, i, Emotion.NEUTRAL, Emotion.NEUTRAL);
                JButton ThisOne = new JButton(w.save.customRoster[i + page * 5].mainName);
                ThisOne.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        if(deleting.booleanValue())
                        {
                            p.removeAll();
                            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nReally delete ").append(w.save.customRoster[index].mainName).append("?").toString());
                            JButton Delete = new JButton("Delete");
                            Delete.addActionListener(new ActionListener() {

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
                                        Project.CampaignCustomToggle(t, p, f, w, newEnabled, page - 1, Boolean.valueOf(true));
                                    else
                                        Project.CampaignCustomToggle(t, p, f, w, newEnabled, page, Boolean.valueOf(true));
                                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
                                    Project.ReportCustomInclusion(t, w, newEnabled);
                                }

                                final _cls69 this$1;
                                private final Boolean val$enabled[];
                                private final WorldState val$w;
                                private final int val$index;
                                private final int val$page;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls69.this;
                        enabled = aboolean;
                        w = worldstate;
                        index = i;
                        page = j;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(Delete);
                            JButton Back = new JButton("Back");
                            Back.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.CampaignCustomToggle(t, p, f, w, enabled, page, Boolean.valueOf(true));
                                }

                                final _cls69 this$1;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final WorldState val$w;
                                private final Boolean val$enabled[];
                                private final int val$page;

                    
                    {
                        this$1 = _cls69.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        enabled = aboolean;
                        page = i;
                        super();
                    }
                            });
                            p.add(Back);
                            p.validate();
                            p.repaint();
                        } else
                        {
                            enabled[index] = Boolean.valueOf(!enabled[index].booleanValue());
                            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
                            Project.ReportCustomInclusion(t, w, enabled);
                        }
                    }

                    private final Boolean val$deleting;
                    private final JPanel val$p;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final int val$index;
                    private final Boolean val$enabled[];
                    private final int val$page;
                    private final JFrame val$f;

            
            {
                deleting = boolean1;
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                index = i;
                enabled = aboolean;
                page = j;
                f = jframe;
                super();
            }
                });
                p.add(ThisOne);
            }

        if((page + 1) * 5 < enabled.length)
        {
            JButton Next = new JButton(">");
            Next.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.CampaignCustomToggle(t, p, f, w, enabled, page + 1, deleting);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Boolean val$enabled[];
                private final int val$page;
                private final Boolean val$deleting;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                enabled = aboolean;
                page = i;
                deleting = boolean1;
                super();
            }
            });
            p.add(Next);
        }
        JButton Done = new JButton("Done");
        Done.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.CampaignMenu(t, p, f, w, enabled);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Boolean val$enabled[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                enabled = aboolean;
                super();
            }
        });
        p.add(Done);
        p.validate();
        p.repaint();
        t.setCaretPosition(t.getDocument().getLength() - 1);
    }

    public static void OptionsDisplay(JTextPane t, JPanel p, JFrame f, WorldState w, Boolean earlyCheatVisible)
    {
        t.setText("");
        if(earlyCheatVisible.booleanValue())
        {
            w.append(t, "Difficulty: ");
            if(w.getEarlyCheat().booleanValue())
                w.append(t, "EASY (cheats available from the start)");
            else
            if(w.hardMode.booleanValue())
                w.append(t, "HARD (shorter deadlines, Chosen take less damage as damage level goes up, cannot use Forsaken)");
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
        if(w.getCommentaryRead().booleanValue())
        {
            if(w.getCommentaryWrite().booleanValue())
                w.append(t, "Read/Write");
            else
                w.append(t, "Read");
        } else
        if(w.getCommentaryWrite().booleanValue())
            w.append(t, "Write");
        else
            w.append(t, "None");
        w.append(t, (new StringBuilder("\n\nText size: ")).append(w.getTextSize()).toString());
        w.append(t, "\n\nEnemy composition: ");
        if(w.getGenderBalance()[0] == 0)
        {
            Boolean listed = Boolean.valueOf(false);
            if(w.getGenderBalance()[1] > 0)
            {
                listed = Boolean.valueOf(true);
                w.append(t, (new StringBuilder(String.valueOf(w.getGenderBalance()[1]))).append(" female").toString());
                if(w.getGenderBalance()[1] > 1)
                    w.append(t, "s");
            }
            if(w.getGenderBalance()[2] > 0)
            {
                if(listed.booleanValue())
                    w.append(t, ", ");
                w.append(t, (new StringBuilder(String.valueOf(w.getGenderBalance()[2]))).append(" male").toString());
                if(w.getGenderBalance()[2] > 1)
                    w.append(t, "s");
                listed = Boolean.valueOf(true);
            }
            if(w.getGenderBalance()[3] > 0)
            {
                if(listed.booleanValue())
                    w.append(t, ", ");
                w.append(t, (new StringBuilder(String.valueOf(w.getGenderBalance()[3]))).append(" futanari").toString());
                listed = Boolean.valueOf(true);
            }
            if(!listed.booleanValue())
                w.append(t, "none set");
        } else
        {
            Boolean listed = Boolean.valueOf(false);
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
                listed = Boolean.valueOf(true);
                if(count > 1)
                    w.append(t, (new StringBuilder(String.valueOf((multiplier * w.getGenderBalance()[1]) / 100))).append("% female").toString());
                else
                    w.append(t, "100% female");
            }
            if(w.getGenderBalance()[2] > 0)
            {
                if(listed.booleanValue())
                    w.append(t, ", ");
                if(count > 1)
                    w.append(t, (new StringBuilder(String.valueOf((multiplier * w.getGenderBalance()[2]) / 100))).append("% male").toString());
                else
                    w.append(t, "100% male");
                listed = Boolean.valueOf(true);
            }
            if(w.getGenderBalance()[3] > 0)
            {
                if(listed.booleanValue())
                    w.append(t, ", ");
                if(count > 1)
                    w.append(t, (new StringBuilder(String.valueOf((multiplier * w.getGenderBalance()[3]) / 100))).append("% futanari").toString());
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
        if(w.tickle().booleanValue())
            w.append(t, "OFF (replaced by tickling)");
        else
            w.append(t, "ON");
        w.append(t, "\n\nPortraits: ");
        if(w.portraits.booleanValue())
            w.append(t, "ON");
        else
            w.append(t, "OFF");
        w.append(t, (new StringBuilder("\n\nPassage separator:\n")).append(w.getSeparator()).toString());
    }

    public static void OptionsMenu(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, Boolean earlyCheatVisible)
    {
        p.removeAll();
        if(earlyCheatVisible == null)
        {
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
            File saveLocation = new File((new StringBuilder(String.valueOf(path))).append(File.separator).append("saves.sav").toString());
            SaveData saves = null;
            if(saveLocation.exists())
            {
                ReadObject robj = new ReadObject();
                saves = robj.deserializeSaveData((new StringBuilder(String.valueOf(path))).append(File.separator).append("saves.sav").toString());
            } else
            {
                saves = new SaveData();
            }
            SaveData saveFile = saves;
            for(int i = 0; i < saveFile.getSaves().length; i++)
                if(saveFile.getSaves()[i].getDay() > 50 - saveFile.getSaves()[i].eventOffset * 3)
                    earlyCheatVisible = Boolean.valueOf(true);

            if(w.getEarlyCheat().booleanValue())
                earlyCheatVisible = Boolean.valueOf(true);
            if(w.hardMode.booleanValue())
                earlyCheatVisible = Boolean.valueOf(true);
            if(saves.harem != null && saves.harem.length > 0)
                earlyCheatVisible = Boolean.valueOf(true);
            if(earlyCheatVisible == null)
                earlyCheatVisible = Boolean.valueOf(false);
        }
        final Boolean CheatVisibility = earlyCheatVisible;
        OptionsDisplay(t, p, f, w, earlyCheatVisible);
        JButton EarlyCheat = new JButton("Change Difficulty");
        EarlyCheat.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                if(w.getEarlyCheat().booleanValue())
                {
                    w.setEarlyCheat(Boolean.valueOf(false));
                    w.hardMode = Boolean.valueOf(true);
                    w.clampStart = 1;
                    w.clampPercent = 80;
                    w.eventOffset = 5;
                } else
                if(w.hardMode.booleanValue())
                {
                    w.hardMode = Boolean.valueOf(false);
                    w.clampStart = 11;
                    w.clampPercent = 100;
                    w.eventOffset = 0;
                } else
                {
                    w.setEarlyCheat(Boolean.valueOf(true));
                }
                Project.OptionsMenu(t, p, f, w, null);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        if(earlyCheatVisible.booleanValue())
            p.add(EarlyCheat);
        class _cls1EarlyCheatAction extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                w.setEarlyCheat(Boolean.valueOf(true));
                Project.OptionsMenu(t, p, f, w, Boolean.valueOf(true));
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            _cls1EarlyCheatAction()
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        }

        Action EarlyCheatAssignment = new _cls1EarlyCheatAction();
        p.getInputMap(2).put(KeyStroke.getKeyStroke(67, 0), "pressed");
        p.getActionMap().put("pressed", EarlyCheatAssignment);
        JButton Invert = new JButton("Change Background");
        Invert.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.toggleColors(t);
                Project.OptionsMenu(t, p, f, w, CheatVisibility);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final Boolean val$CheatVisibility;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                CheatVisibility = boolean1;
                super();
            }
        });
        p.add(Invert);
        JButton Commentary = new JButton("Change Commentary Mode");
        Commentary.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                if(w.getCommentaryRead().booleanValue())
                {
                    if(w.getCommentaryWrite().booleanValue())
                    {
                        w.setCommentaryWrite(Boolean.valueOf(false));
                    } else
                    {
                        w.setCommentaryRead(Boolean.valueOf(false));
                        w.setCommentaryWrite(Boolean.valueOf(true));
                    }
                } else
                if(w.getCommentaryWrite().booleanValue())
                {
                    w.setCommentaryWrite(Boolean.valueOf(false));
                } else
                {
                    w.setCommentaryRead(Boolean.valueOf(true));
                    w.setCommentaryWrite(Boolean.valueOf(true));
                }
                Project.OptionsMenu(t, p, f, w, CheatVisibility);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final Boolean val$CheatVisibility;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                CheatVisibility = boolean1;
                super();
            }
        });
        p.add(Commentary);
        JButton TextSize = new JButton("Change Text Size");
        TextSize.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.switchTextSize();
                Project.OptionsMenu(t, p, f, w, CheatVisibility);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final Boolean val$CheatVisibility;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                CheatVisibility = boolean1;
                super();
            }
        });
        p.add(TextSize);
        JButton Content = new JButton("Content Options");
        Content.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.ContentMenu(t, p, f, w, CheatVisibility);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Boolean val$CheatVisibility;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                CheatVisibility = boolean1;
                super();
            }
        });
        p.add(Content);
        JButton Portraits = new JButton("Toggle Portraits");
        Portraits.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.portraits = Boolean.valueOf(!w.portraits.booleanValue());
                Project.OptionsMenu(t, p, f, w, CheatVisibility);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final Boolean val$CheatVisibility;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                CheatVisibility = boolean1;
                super();
            }
        });
        p.add(Portraits);
        JButton ChangeSeparator = new JButton("Change Separator");
        ChangeSeparator.addActionListener(new ActionListener() {

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

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final Boolean val$CheatVisibility;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                CheatVisibility = boolean1;
                super();
            }
        });
        p.add(ChangeSeparator);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                t.setText("");
                Project.IntroOne(t, p, f, w);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
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

            public void actionPerformed(ActionEvent e)
            {
                w.toggleGenderRandomness();
                Project.GenderMenu(t, p, f, w, earlyCheatVisible);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final Boolean val$earlyCheatVisible;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                earlyCheatVisible = boolean1;
                super();
            }
        });
        p.add(ToggleRandomness);
        JButton FewerFemales = new JButton("Fewer Females");
        if(w.getGenderBalance()[1] > 0 && (w.getGenderBalance()[0] == 0 || w.getGenderBalance()[2] > 0 || w.getGenderBalance()[3] > 0))
            FewerFemales.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.decreaseGender(1);
                    Project.GenderMenu(t, p, f, w, earlyCheatVisible);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final Boolean val$earlyCheatVisible;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                earlyCheatVisible = boolean1;
                super();
            }
            });
        else
            FewerFemales.setForeground(Color.GRAY);
        p.add(FewerFemales);
        JButton MoreFemales = new JButton("More Females");
        if(w.getGenderBalance()[0] == 1 && (w.getGenderBalance()[2] > 0 || w.getGenderBalance()[3] > 0) || w.getGenderBalance()[0] == 0 && w.getGenderBalance()[1] + w.getGenderBalance()[2] + w.getGenderBalance()[3] < 3)
            MoreFemales.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.increaseGender(1);
                    Project.GenderMenu(t, p, f, w, earlyCheatVisible);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final Boolean val$earlyCheatVisible;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                earlyCheatVisible = boolean1;
                super();
            }
            });
        else
            MoreFemales.setForeground(Color.GRAY);
        p.add(MoreFemales);
        JButton FewerMales = new JButton("Fewer Males");
        if(w.getGenderBalance()[2] > 0 && (w.getGenderBalance()[0] == 0 || w.getGenderBalance()[1] > 0 || w.getGenderBalance()[3] > 0))
            FewerMales.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.decreaseGender(2);
                    Project.GenderMenu(t, p, f, w, earlyCheatVisible);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final Boolean val$earlyCheatVisible;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                earlyCheatVisible = boolean1;
                super();
            }
            });
        else
            FewerMales.setForeground(Color.GRAY);
        p.add(FewerMales);
        JButton MoreMales = new JButton("More Males");
        if(w.getGenderBalance()[0] == 1 && (w.getGenderBalance()[1] > 0 || w.getGenderBalance()[3] > 0) || w.getGenderBalance()[0] == 0 && w.getGenderBalance()[1] + w.getGenderBalance()[2] + w.getGenderBalance()[3] < 3)
            MoreMales.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.increaseGender(2);
                    Project.GenderMenu(t, p, f, w, earlyCheatVisible);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final Boolean val$earlyCheatVisible;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                earlyCheatVisible = boolean1;
                super();
            }
            });
        else
            MoreMales.setForeground(Color.GRAY);
        p.add(MoreMales);
        JButton FewerFuta = new JButton("Fewer Futanari");
        if(w.getGenderBalance()[3] > 0 && (w.getGenderBalance()[0] == 0 || w.getGenderBalance()[1] > 0 || w.getGenderBalance()[2] > 0))
            FewerFuta.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.decreaseGender(3);
                    Project.GenderMenu(t, p, f, w, earlyCheatVisible);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final Boolean val$earlyCheatVisible;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                earlyCheatVisible = boolean1;
                super();
            }
            });
        else
            FewerFuta.setForeground(Color.GRAY);
        p.add(FewerFuta);
        JButton MoreFuta = new JButton("More Futanari");
        if(w.getGenderBalance()[0] == 1 && (w.getGenderBalance()[2] > 0 || w.getGenderBalance()[1] > 0) || w.getGenderBalance()[0] == 0 && w.getGenderBalance()[1] + w.getGenderBalance()[2] + w.getGenderBalance()[3] < 3)
            MoreFuta.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.increaseGender(3);
                    Project.GenderMenu(t, p, f, w, earlyCheatVisible);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final Boolean val$earlyCheatVisible;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                earlyCheatVisible = boolean1;
                super();
            }
            });
        else
            MoreFuta.setForeground(Color.GRAY);
        p.add(MoreFuta);
        JButton Back = new JButton("Back");
        if(w.getGenderBalance()[0] == 1 || w.getGenderBalance()[1] + w.getGenderBalance()[2] + w.getGenderBalance()[3] == 3)
            Back.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.ContentMenu(t, p, f, w, earlyCheatVisible);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Boolean val$earlyCheatVisible;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                earlyCheatVisible = boolean1;
                super();
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

            public void actionPerformed(ActionEvent e)
            {
                w.toggleTickle();
                Project.ContentMenu(t, p, f, w, earlyCheatVisible);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final Boolean val$earlyCheatVisible;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                earlyCheatVisible = boolean1;
                super();
            }
        });
        p.add(Violence);
        JButton Genders = new JButton("Change Composition");
        Genders.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.GenderMenu(t, p, f, w, earlyCheatVisible);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Boolean val$earlyCheatVisible;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                earlyCheatVisible = boolean1;
                super();
            }
        });
        p.add(Genders);
        if(w.getGenderBalance()[2] > 0)
        {
            JButton MaleShift = new JButton("Toggle Male Shifting");
            MaleShift.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.changeMaleShift();
                    Project.ContentMenu(t, p, f, w, earlyCheatVisible);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final Boolean val$earlyCheatVisible;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                earlyCheatVisible = boolean1;
                super();
            }
            });
            p.add(MaleShift);
        }
        if(w.getGenderBalance()[1] > 0 || w.getGenderBalance()[2] > 0 && w.getMaleShift() == 1)
        {
            JButton FemaleShift = new JButton("Toggle Female Shifting");
            FemaleShift.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.changeFemaleShift();
                    Project.ContentMenu(t, p, f, w, earlyCheatVisible);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final Boolean val$earlyCheatVisible;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                earlyCheatVisible = boolean1;
                super();
            }
            });
            p.add(FemaleShift);
        }
        if(w.getMaleShift() > 0 || w.getFemaleShift() > 0)
        {
            JButton RepeatShift = new JButton("Toggle Repeated Shifting");
            RepeatShift.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.changeRepeatShift();
                    Project.ContentMenu(t, p, f, w, earlyCheatVisible);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final Boolean val$earlyCheatVisible;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                earlyCheatVisible = boolean1;
                super();
            }
            });
            p.add(RepeatShift);
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.OptionsMenu(t, p, f, w, earlyCheatVisible);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Boolean val$earlyCheatVisible;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                earlyCheatVisible = boolean1;
                super();
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
            if(w.active.booleanValue())
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                if(w.usedForsaken != null)
                    w.append(t, (new StringBuilder(String.valueOf(w.usedForsaken.mainName))).append(" is currently prepared to lead your forces into battle.  ").toString());
                if(w.loopComplete.booleanValue())
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
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWith which of your Forsaken would you like to interact?").toString());
            }
        } else
        {
            clearPortraits();
            JButton PreviousPage = new JButton("<");
            PreviousPage.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.ForsakenMenu(t, p, f, w, s, page - 1);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final SaveData val$s;
                private final int val$page;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                page = i;
                super();
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

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.ForsakenInteraction(t, p, f, w, s, subject);
                    }

                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final SaveData val$s;
                    private final Forsaken val$subject;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                subject = forsaken;
                super();
            }
                });
                p.add(ThisOne);
                nameDisplay[i] = subject.mainName;
                if(subject.flavorObedience() < 20)
                    changePortrait(subject.gender, subject.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, i, Emotion.ANGER, Emotion.NEUTRAL);
                else
                if(subject.flavorObedience() < 40)
                    changePortrait(subject.gender, subject.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, i, Emotion.ANGER, Emotion.SHAME);
                else
                if(subject.flavorObedience() < 61)
                    changePortrait(subject.gender, subject.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, i, Emotion.FEAR, Emotion.SHAME);
                else
                if(subject.flavorObedience() < 81)
                    changePortrait(subject.gender, subject.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, i, Emotion.FOCUS, Emotion.NEUTRAL);
                else
                    changePortrait(subject.gender, subject.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, i, Emotion.JOY, Emotion.FOCUS);
            }

        if(w.getHarem().length > 5 * (page + 1))
        {
            JButton NextPage = new JButton(">");
            NextPage.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.ForsakenMenu(t, p, f, w, s, page + 1);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final SaveData val$s;
                private final int val$page;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                page = i;
                super();
            }
            });
            p.add(NextPage);
        }
        JButton NewForsaken = new JButton("(Generate Forsaken)");
        NewForsaken.addActionListener(new ActionListener() {

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

            private final WorldState val$w;
            private final JTextPane val$t;
            private final SaveData val$s;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                s = savedata;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        if(!w.campaign.booleanValue())
            p.add(NewForsaken);
        JButton PassTime = new JButton("Pass Time");
        PassTime.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                if(w.loopComplete.booleanValue() || w.truceEnforced().booleanValue())
                {
                    p.removeAll();
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nDo you want to give your Forsaken a break and just move on to the next day?").toString());
                    JButton Confirm = new JButton("Confirm");
                    Confirm.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            Project.PostBattle(t, p, f, w);
                        }

                        final _cls98 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;

                    
                    {
                        this$1 = _cls98.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                    });
                    p.add(Confirm);
                    JButton Cancel = new JButton("Cancel");
                    Cancel.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            Project.ForsakenMenu(t, p, f, w, s, page);
                        }

                        final _cls98 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;
                        private final SaveData val$s;
                        private final int val$page;

                    
                    {
                        this$1 = _cls98.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        s = savedata;
                        page = i;
                        super();
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

            private final WorldState val$w;
            private final JPanel val$p;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final SaveData val$s;
            private final int val$page;

            
            {
                w = worldstate;
                p = jpanel;
                t = jtextpane;
                f = jframe;
                s = savedata;
                page = i;
                super();
            }
        });
        if(w.active.booleanValue() && !w.loopComplete.booleanValue() && !w.truceEnforced().booleanValue())
            PassTime.setText("Deploy");
        if(!w.campaign.booleanValue() || w.day <= 50 - w.eventOffset * 3)
            p.add(PassTime);
        if(w.active.booleanValue() && !w.loopComplete.booleanValue())
        {
            JButton UseDemon = new JButton("Use Demon");
            UseDemon.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    if(w.usedForsaken != null)
                        w.evilEnergy += w.usedForsaken.EECost();
                    w.usedForsaken = null;
                    Project.Customize(t, p, f, w);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
            });
            p.add(UseDemon);
        }
        JButton Back = new JButton("Done");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                if(w.active.booleanValue())
                {
                    Project.Shop(t, p, f, w);
                } else
                {
                    t.setText("");
                    Project.IntroOne(t, p, f, w);
                }
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
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
            if(w.active.booleanValue())
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhich of the Forsaken would you like to send into battle?").toString());
            else
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nYou can spend one of the Forsaken's Stamina and Motivation (as would normally happen when sending them into battle), or you can simply pass time without spending anything by selecting 'None'.").toString());
        } else
        {
            JButton PreviousPage = new JButton("<");
            PreviousPage.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
                    Project.ForsakenChoice(t, p, f, w, s, page - 1);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;
                private final int val$page;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                page = i;
                super();
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
                if(w.getHarem()[i].isFormerFriend(w.getCast()[0]).booleanValue() || w.getHarem()[i].isFormerFriend(w.getCast()[1]).booleanValue() || w.getHarem()[i].isFormerFriend(w.getCast()[2]).booleanValue())
                    actualCost *= 2;
                w.append(t, (new StringBuilder("\n\n")).append(w.getHarem()[i].mainName).append("\nStamina: ").append(w.getHarem()[i].stamina / 10).append(".").append(w.getHarem()[i].stamina % 10).append("%\nMotivation: ").append(w.getHarem()[i].motivation / 10).append(".").append(w.getHarem()[i].motivation % 10).append("%\nCost: 20% Stamina, ").append(actualCost / 10).append(".").append(actualCost % 10).append("% Motivation, ").append(w.getHarem()[i].EECost()).append(" EE\n").append(w.getHarem()[i].describeCombatStyle(w, Boolean.valueOf(false))).append("\nReputation Strength: ").append(200 - w.getHarem()[i].disgrace * 2).append("%\nTarget Compatibilities:").toString());
                if(w.active.booleanValue())
                {
                    for(int j = 0; j < 3; j++)
                        if(w.getCast()[j] != null)
                        {
                            w.append(t, (new StringBuilder("\n")).append(w.getCast()[j].getMainName()).append(" - ").toString());
                            int compatibility = w.getHarem()[i].compatibility(w.getCast()[j]);
                            if(w.getHarem()[i].knowsPersonally(w.getCast()[j]).booleanValue())
                                w.append(t, "Personal (8 rounds, +25% damage)");
                            else
                            if(w.getHarem()[i].obsessedWith(w.getCast()[j]).booleanValue())
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

                    public void actionPerformed(ActionEvent e)
                    {
                        if(w.active.booleanValue())
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

                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.ForsakenMenu(t, p, f, w, s, 0);
                                }

                                final _cls102 this$1;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final WorldState val$w;
                                private final SaveData val$s;

                    
                    {
                        this$1 = _cls102.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        s = savedata;
                        super();
                    }
                            });
                            p.add(Continue);
                            p.validate();
                            p.repaint();
                        }
                    }

                    private final WorldState val$w;
                    private final Forsaken val$Spent;
                    private final int val$index;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final SaveData val$s;

            
            {
                w = worldstate;
                Spent = forsaken;
                index = i;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
                });
                int EEAvailable = w.evilEnergy;
                if(w.usedForsaken != null)
                    EEAvailable += w.usedForsaken.EECost();
                if(Spent.stamina >= 200 && Spent.motivation >= actualCost && (!w.active.booleanValue() || EEAvailable >= Spent.EECost()))
                    p.add(Choice);
                nameDisplay[i - page * 5] = Spent.mainName;
                if(Spent.flavorObedience() < 20)
                    changePortrait(Spent.gender, Spent.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, i - page * 5, Emotion.ANGER, Emotion.NEUTRAL);
                else
                if(Spent.flavorObedience() < 40)
                    changePortrait(Spent.gender, Spent.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, i - page * 5, Emotion.ANGER, Emotion.SHAME);
                else
                if(Spent.flavorObedience() < 61)
                    changePortrait(Spent.gender, Spent.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, i - page * 5, Emotion.FEAR, Emotion.SHAME);
                else
                if(Spent.flavorObedience() < 81)
                    changePortrait(Spent.gender, Spent.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, i - page * 5, Emotion.FOCUS, Emotion.NEUTRAL);
                else
                    changePortrait(Spent.gender, Spent.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, i - page * 5, Emotion.JOY, Emotion.FOCUS);
            }

        if(w.getHarem().length > (page + 1) * 5)
        {
            JButton NextPage = new JButton(">");
            NextPage.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
                    Project.ForsakenChoice(t, p, f, w, s, page + 1);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;
                private final int val$page;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                page = i;
                super();
            }
            });
            p.add(NextPage);
        }
        JButton None = new JButton("None");
        None.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                Project.ForsakenDowntime(t, p, f, w, s, null);
                JButton Continue = new JButton("Continue");
                Continue.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.ForsakenMenu(t, p, f, w, s, 0);
                    }

                    final _cls104 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final SaveData val$s;

                    
                    {
                        this$1 = _cls104.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        s = savedata;
                        super();
                    }
                });
                p.add(Continue);
                p.validate();
                p.repaint();
            }

            private final JPanel val$p;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final WorldState val$w;
            private final SaveData val$s;

            
            {
                p = jpanel;
                t = jtextpane;
                f = jframe;
                w = worldstate;
                s = savedata;
                super();
            }
        });
        if(!w.active.booleanValue())
            p.add(None);
        JButton Cancel = new JButton("Cancel");
        Cancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.ForsakenMenu(t, p, f, w, s, 0);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final SaveData val$s;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                super();
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
            changePortrait(x.gender, x.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, 0, Emotion.ANGER, Emotion.NEUTRAL);
        else
        if(x.flavorObedience() < 40)
            changePortrait(x.gender, x.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, 0, Emotion.ANGER, Emotion.SHAME);
        else
        if(x.flavorObedience() < 61)
            changePortrait(x.gender, x.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, 0, Emotion.FEAR, Emotion.SHAME);
        else
        if(x.flavorObedience() < 81)
            changePortrait(x.gender, x.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, 0, Emotion.FOCUS, Emotion.NEUTRAL);
        else
            changePortrait(x.gender, x.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, 0, Emotion.JOY, Emotion.FOCUS);
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").append(x.mainName).toString());
        if(!x.mainName.equals(x.originalName))
        {
            w.append(t, " (formerly known as");
            if(!x.adjectiveName.equals("none"))
            {
                w.append(t, (new StringBuilder(" ")).append(x.adjectiveName).toString());
                if(x.nounName.length() > 0)
                    w.append(t, (new StringBuilder(" ")).append(x.nounName).toString());
            }
            w.append(t, (new StringBuilder(" ")).append(x.originalName).append(")").toString());
        }
        if(x.familyName.length() > 0)
        {
            if(x.mainName.equals(x.familyName) || x.mainName.equals(x.givenName) || x.originalName.equals(x.familyName) || x.originalName.equals(x.givenName))
                w.append(t, "\nFull name: ");
            else
                w.append(t, "\nReal name: ");
            if(x.filthyGaijin.booleanValue())
                w.append(t, (new StringBuilder(String.valueOf(x.givenName))).append(" ").append(x.familyName).toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(x.familyName))).append(" ").append(x.givenName).toString());
        } else
        if(!x.givenName.equals(x.mainName) && !x.givenName.equals(x.originalName))
            w.append(t, (new StringBuilder("\nReal name: ")).append(x.givenName).toString());
        w.append(t, (new StringBuilder("\n\nStamina: ")).append(x.stamina / 10).append(".").append(x.stamina % 10).append("%\nMotivation: ").append(x.motivation / 10).append(".").append(x.motivation % 10).append("%").toString());
        w.append(t, (new StringBuilder("\n\nExpertise\nHATE: ")).append(x.condensedFormat(x.hateExp)).append(" (x").append(x.expMultiplierDisplay(x.hateExp)).append(" dmg)\nPLEA: ").append(x.condensedFormat(x.pleaExp)).append(" (x").append(x.expMultiplierDisplay(x.pleaExp)).append(" dmg)").toString());
        if(w.tickleOn.booleanValue())
            w.append(t, "\nANTI: ");
        else
            w.append(t, "\nINJU: ");
        w.append(t, (new StringBuilder(String.valueOf(x.condensedFormat(x.injuExp)))).append(" (x").append(x.expMultiplierDisplay(x.injuExp)).append(" dmg)\nEXPO: ").append(x.condensedFormat(x.expoExp)).append(" (x").append(x.expMultiplierDisplay(x.expoExp)).append(" dmg)\n").append(x.describeCombatStyle(w, Boolean.valueOf(true))).toString());
        if(x.defilerType != 0)
            w.append(t, (new StringBuilder("\n\n")).append(x.describeDefilerType(w, Boolean.valueOf(false))).toString());
        if(x.defeatType == 5 && x.obedience < 40)
            w.append(t, "\n\nTrait: Eager Partner\nWhile Obedience remains below 40%, 1/4 Motivation cost to deploy and +50% PLEA and EXPO damage");
        else
        if(x.defeatType == 5)
            w.append(t, "\n\nTrait: Broken Traitor\nNo longer receives 'Eager Partner' bonus, but can still be trained to Tempt the Chosen");
        if(x.defeatType == 6)
        {
            w.append(t, "\n\nTrait: Dissociative Identity\nWhile consenting, training can only increase Disgrace.  +50% HATE and ");
            if(w.tickle().booleanValue())
                w.append(t, "ANTI damage");
            else
                w.append(t, "INJU damage");
        }
        if(x.defeatType == 7)
        {
            w.append(t, "\n\nTrait: Flexible Peacemaker\nUses better between Hostility and Obedience to determine Stamina regeneration.  +50% PLEA and ");
            if(w.tickle().booleanValue())
                w.append(t, "ANTI damage");
            else
                w.append(t, "INJU damage");
        }
        if(x.type == Chosen.Species.SUPERIOR)
            w.append(t, "\n\nTrait: Superior Forsaken\nx2 Motivation cost to deploy, +50% damage");
        w.append(t, "\n\nOrgasms given: ");
        if(x.orgasmsGiven == 0)
            w.append(t, "none");
        else
            w.append(t, (new StringBuilder(String.valueOf(x.orgasmsGiven))).toString());
        w.append(t, "\nOrgasms experienced: ");
        if(x.timesOrgasmed == 0)
            w.append(t, "none");
        else
            w.append(t, (new StringBuilder(String.valueOf(x.timesOrgasmed))).toString());
        w.append(t, "\nLongest continuous orgasm: ");
        if(x.timesOrgasmed == 0)
            w.append(t, "N/A");
        else
        if(x.strongestOrgasm < 600)
            w.append(t, (new StringBuilder(String.valueOf(x.strongestOrgasm / 10))).append(".").append(x.strongestOrgasm % 10).append(" seconds").toString());
        else
        if(x.strongestOrgasm < 36000)
            w.append(t, (new StringBuilder(String.valueOf(x.strongestOrgasm / 600))).append(" minutes ").append((x.strongestOrgasm % 600) / 10).append(" seconds").toString());
        else
            w.append(t, (new StringBuilder(String.valueOf(x.strongestOrgasm / 36000))).append(" hours ").append((x.strongestOrgasm % 36000) / 600).append(" minutes").toString());
        w.append(t, (new StringBuilder("\nSeen naked by: ")).append(x.timesExposed).append(" people (").append(x.timesExposedSelf).append(" with permission)").toString());
        if(x.gender != Forsaken.Gender.MALE)
        {
            w.append(t, (new StringBuilder("\nTimes vaginally penetrated: ")).append(x.timesHadSex).toString());
            if(x.timesHadSex == 0)
                w.append(t, " (virgin)");
        }
        w.append(t, "\nTimes anally penetrated: ");
        int analCount = x.enjoyedAnal;
        if(x.gender == Forsaken.Gender.MALE)
            analCount += x.timesHadSex;
        else
            analCount += x.timesTortured;
        w.append(t, (new StringBuilder(String.valueOf(analCount))).toString());
        if(analCount == 0)
            w.append(t, " (anal virgin)");
        if(x.demonicBirths > 0)
            w.append(t, (new StringBuilder("\nDemonic births: ")).append(x.demonicBirths).toString());
        else
            w.append(t, "\nDemonic births: 0");
        w.append(t, (new StringBuilder("\n\nPeople injured: ")).append(x.peopleInjured).append("\nPeople killed: ").append(x.timesKilled).append("\nSelf-harm incidents: ").append(x.timesHarmedSelf).append("\n\nHostility: ").append(x.hostility).append("% (").toString());
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
        if(w.active.booleanValue() && !w.loopComplete.booleanValue())
            w.append(t, (new StringBuilder("  Note that training ")).append(x.himHer()).append(" will take the entire day.").toString());
        JButton Self = new JButton("Herself");
        if(x.gender == Forsaken.Gender.MALE)
            Self.setText("Himself");
        Self.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                x.selfTalk(t);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final Forsaken val$x;

            
            {
                w = worldstate;
                t = jtextpane;
                x = forsaken;
                super();
            }
        });
        p.add(Self);
        JButton Philosophy = new JButton("Philosophy");
        Philosophy.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                x.philosophyTalk(t);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final Forsaken val$x;

            
            {
                w = worldstate;
                t = jtextpane;
                x = forsaken;
                super();
            }
        });
        p.add(Philosophy);
        JButton Training = new JButton("Training");
        Training.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                x.trainingTalk(t);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final Forsaken val$x;

            
            {
                w = worldstate;
                t = jtextpane;
                x = forsaken;
                super();
            }
        });
        JButton Life = new JButton("Everyday Life");
        Life.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                x.lifeTalk(t);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final Forsaken val$x;

            
            {
                w = worldstate;
                t = jtextpane;
                x = forsaken;
                super();
            }
        });
        final WriteObject wobj = new WriteObject();
        JButton Others = new JButton("Other Forsaken");
        Others.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                x.othersTalk(w, t, s);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final Forsaken val$x;
            private final SaveData val$s;

            
            {
                w = worldstate;
                t = jtextpane;
                x = forsaken;
                s = savedata;
                super();
            }
        });
        if(w.getHarem().length > 1)
            p.add(Others);
        JButton ChangeName = new JButton("Customize");
        ChangeName.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                x.CustomizeMenu(t, p, f, w, s);
            }

            private final Forsaken val$x;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final SaveData val$s;

            
            {
                x = forsaken;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                super();
            }
        });
        p.add(ChangeName);
        JButton Meet = new JButton("Meet");
        Meet.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Body participants[] = new Body[1];
                participants[0] = new Body(x);
                participants[0].imprisoned = Boolean.valueOf(true);
                Project.SelectBody(t, p, f, w, s, participants);
            }

            private final Forsaken val$x;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final SaveData val$s;

            
            {
                x = forsaken;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                super();
            }
        });
        if(!w.active.booleanValue() || !x.visited.booleanValue())
            p.add(Meet);
        JButton FreeTraining = new JButton("Free Training");
        FreeTraining.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Boolean newTraining[] = new Boolean[22];
                for(int i = 0; i < newTraining.length; i++)
                    newTraining[i] = Boolean.valueOf(false);

                x.trainingMenu(t, p, f, w, s, newTraining, 0, Boolean.valueOf(true));
            }

            private final Forsaken val$x;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final SaveData val$s;

            
            {
                x = forsaken;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                super();
            }
        });
        if(w.active.booleanValue())
            FreeTraining.setText("Training");
        if(!w.active.booleanValue() || (w.day != 50 - w.eventOffset * 3 || w.loopComplete.booleanValue()) && (w.day != 51 - w.eventOffset * 3 || !w.campaign.booleanValue()))
            p.add(FreeTraining);
        JButton Delete = new JButton("Delete");
        if(w.campaign.booleanValue())
            Delete.setText("Sacrifice");
        Delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                if(x.gender == Forsaken.Gender.MALE)
                    w.append(t, (new StringBuilder(String.valueOf(x.mainName))).append(" will have ").append(x.hisHer()).append(" body modified into single-purpose breeding stock for the Demons, and you will never interact directly with ").append(x.himHer()).append(" again.  The terror of facing a similar fate will motivate any other Forsaken to obey you much more faithfully in the short-term.  Is this okay?").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(x.mainName))).append(" will spend the rest of ").append(x.hisHer()).append(" life as single-purpose breeding stock for the Demons, and you will never interact directly with ").append(x.himHer()).append(" again.  The terror of facing a similar fate will motivate any other Forsaken to obey you much more faithfully in the short-term.  Is this okay?").toString());
                JButton Confirm = new JButton("Confirm");
                Confirm.addActionListener(new ActionListener() {

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

                        if(w.campaign.booleanValue())
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

                            public void actionPerformed(ActionEvent e)
                            {
                                Project.ForsakenMenu(t, p, f, w, s, 0);
                            }

                            final _cls1 this$2;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;
                            private final SaveData val$s;

                        
                        {
                            this$2 = _cls1.this;
                            t = jtextpane;
                            p = jpanel;
                            f = jframe;
                            w = worldstate;
                            s = savedata;
                            super();
                        }
                        });
                        p.add(Continue);
                        p.validate();
                        p.repaint();
                    }

                    final _cls114 this$1;
                    private final JPanel val$p;
                    private final WorldState val$w;
                    private final Forsaken val$x;
                    private final SaveData val$s;
                    private final WriteObject val$wobj;
                    private final JTextPane val$t;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls114.this;
                        p = jpanel;
                        w = worldstate;
                        x = forsaken;
                        s = savedata;
                        wobj = writeobject;
                        t = jtextpane;
                        f = jframe;
                        super();
                    }
                });
                p.add(Confirm);
                JButton Cancel = new JButton("Cancel");
                Cancel.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.ForsakenInteraction(t, p, f, w, s, x);
                    }

                    final _cls114 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final SaveData val$s;
                    private final Forsaken val$x;

                    
                    {
                        this$1 = _cls114.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        s = savedata;
                        x = forsaken;
                        super();
                    }
                });
                p.add(Cancel);
                p.validate();
                p.repaint();
            }

            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final Forsaken val$x;
            private final SaveData val$s;
            private final WriteObject val$wobj;
            private final JFrame val$f;

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                x = forsaken;
                s = savedata;
                wobj = writeobject;
                f = jframe;
                super();
            }
        });
        p.add(Delete);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.ForsakenMenu(t, p, f, w, s, 0);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final SaveData val$s;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void SelectBody(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s, final Body participants[])
    {
        p.removeAll();
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhat sort of body will you be bringing to see ").toString());
        if(participants.length != 1)
            w.append(t, "them?");
        else
            w.append(t, (new StringBuilder(String.valueOf(participants[0].himHer()))).append("?").toString());
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

            private final int val$parts[];
            private final Body val$participants[];
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final SaveData val$s;

            
            {
                parts = ai;
                participants = abody;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                super();
            }
        });
        p.add(Male);
        JButton Female = new JButton("Female");
        Female.addActionListener(new ActionListener() {

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

            private final int val$parts[];
            private final Body val$participants[];
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final SaveData val$s;

            
            {
                parts = ai;
                participants = abody;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                super();
            }
        });
        p.add(Female);
        JButton Futanari = new JButton("Futanari");
        Futanari.addActionListener(new ActionListener() {

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

            private final int val$parts[];
            private final Body val$participants[];
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final SaveData val$s;

            
            {
                parts = ai;
                participants = abody;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                super();
            }
        });
        p.add(Futanari);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                if(participants.length == 1 && participants[0].forsakenOwner != null)
                    Project.ForsakenInteraction(t, p, f, w, s, participants[0].forsakenOwner);
                else
                if(w.active.booleanValue())
                    Project.Shop(t, p, f, w);
                else
                    Project.IntroOne(t, p, f, w);
            }

            private final Body val$participants[];
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final SaveData val$s;

            
            {
                participants = abody;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                super();
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
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhere would you like to meet ").append(participants[1].himHer()).append("?").toString());
            else
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhere would you like to meet them?").toString());
        if(participants[1].forsakenOwner != null)
        {
            JButton Chamber = new JButton("Private Chamber");
            Chamber.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.NewMeeting(t, p, f, w, s, participants, Activity.Location.CHAMBER);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final SaveData val$s;
                private final Body val$participants[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                participants = abody;
                super();
            }
            });
            p.add(Chamber);
            JButton Stage = new JButton("On Stage");
            Stage.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.NewMeeting(t, p, f, w, s, participants, Activity.Location.STAGE);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final SaveData val$s;
                private final Body val$participants[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                participants = abody;
                super();
            }
            });
            p.add(Stage);
        } else
        if(participants[1].chosenOwner != null)
        {
            if(participants[1].chosenOwner.negotiations > 3 && participants[1].chosenOwner.ANGST >= 0x3b9aca00L)
            {
                JButton Room = new JButton((new StringBuilder(String.valueOf(participants[1].HisHer()))).append(" Room").toString());
                Room.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.NewMeeting(t, p, f, w, s, participants, Activity.Location.ROOM);
                    }

                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final SaveData val$s;
                    private final Body val$participants[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                participants = abody;
                super();
            }
                });
                p.add(Room);
            }
            JButton Alley = new JButton("Back Alley");
            Alley.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.NewMeeting(t, p, f, w, s, participants, Activity.Location.ALLEY);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final SaveData val$s;
                private final Body val$participants[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                participants = abody;
                super();
            }
            });
            p.add(Alley);
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

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

            private final Body val$participants[];
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final SaveData val$s;

            
            {
                participants = abody;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void NewMeeting(JTextPane t, JPanel p, JFrame f, WorldState w, SaveData s, Body participants[], Activity.Location spot)
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
                participants[i].forsakenOwner.visited = Boolean.valueOf(true);
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
                participants[i].chosenOwner.visited = Boolean.valueOf(true);
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

                        participants[i].chosenOwner.rememberedBodies = newRememberedBodies;
                    }
            }

        participants[0].PickActivity(t, p, f, w, s);
    }

    public static void SceneCompletion(JTextPane t, JPanel p, JFrame f, WorldState w, SaveData s)
    {
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
        w.underlineAppend(t, "Scenes Recorded");
        w.append(t, "\n");
        int types = 0;
        for(int i = 5; i < 21; i++)
            if(s.sceneText[i].length > 0)
                types++;

        w.append(t, (new StringBuilder("Core Vulnerability Break: ")).append(types).append("/20\n").toString());
        w.append(t, "Core Vulnerability Distortions: ");
        int found = 0;
        if(s.sceneText[21].length > 0)
            found++;
        if(s.sceneText[22].length > 0)
            found++;
        if(s.sceneText[23].length > 0)
            found++;
        w.append(t, (new StringBuilder(String.valueOf(found))).append("/3\n").toString());
        types = 0;
        for(int i = 33; i < 49; i++)
            if(s.sceneText[i].length > 0)
                types++;

        w.append(t, (new StringBuilder("Daily Vignettes: ")).append(types).append("/").append(16).toString());
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

                public void actionPerformed(ActionEvent e)
                {
                    Project.SceneViewer(t, p, f, w, s, newStartPoint);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final SaveData val$s;
                private final int val$newStartPoint;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                newStartPoint = i;
                super();
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
                if(i == 23)
                    sceneName = "First 'Negotiate'";
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

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.SceneChoice(t, p, f, w, s, sceneType, starting, 0);
                    }

                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final SaveData val$s;
                    private final int val$sceneType;
                    private final int val$starting;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                sceneType = i;
                starting = j;
                super();
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

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.SceneViewer(t, p, f, w, s, newStartPoint);
                    }

                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final SaveData val$s;
                    private final int val$newStartPoint;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                newStartPoint = i;
                super();
            }
                });
                p.add(Next);
                i = s.sceneText.length;
            }

        JButton Back = new JButton("Done");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                t.setText("");
                Project.IntroOne(t, p, f, w);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void SceneChoice(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s, final int type, final int starting, final int page)
    {
        p.removeAll();
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhose scene would you like to replay?\n").toString());
        if(page > 0)
        {
            JButton Previous = new JButton("<");
            Previous.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    int newPage = page - 5;
                    if(newPage < 0)
                        newPage = 0;
                    Project.SceneChoice(t, p, f, w, s, type, starting, newPage);
                }

                private final int val$page;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final SaveData val$s;
                private final int val$type;
                private final int val$starting;

            
            {
                page = i;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                type = j;
                starting = k;
                super();
            }
            });
            p.add(Previous);
        }
        for(int i = page; i < page + 5 && i < s.sceneText[type].length; i++)
        {
            w.append(t, (new StringBuilder("\n")).append(s.sceneButtons[type][i]).append(": ").append(s.sceneSummaries[type][i]).toString());
            JButton PickScene = new JButton(s.sceneButtons[type][i]);
            final int sceneID = i;
            PickScene.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.ReplayScene(t, p, f, w, s, type, starting, page, sceneID);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final SaveData val$s;
                private final int val$type;
                private final int val$starting;
                private final int val$page;
                private final int val$sceneID;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                type = i;
                starting = j;
                page = k;
                sceneID = l;
                super();
            }
            });
            p.add(PickScene);
        }

        if(s.sceneText[type].length > page + 5)
        {
            JButton Next = new JButton(">");
            Next.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.SceneChoice(t, p, f, w, s, type, starting, page + 5);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final SaveData val$s;
                private final int val$type;
                private final int val$starting;
                private final int val$page;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                type = i;
                starting = j;
                page = k;
                super();
            }
            });
            p.add(Next);
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.SceneCompletion(t, p, f, w, s);
                Project.SceneViewer(t, p, f, w, s, starting);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final SaveData val$s;
            private final int val$starting;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                starting = i;
                super();
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
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
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

            public void actionPerformed(ActionEvent e)
            {
                Project.SceneChoice(t, p, f, w, s, type, starting, page);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final SaveData val$s;
            private final int val$type;
            private final int val$starting;
            private final int val$page;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                type = i;
                starting = j;
                page = k;
                super();
            }
        });
        p.add(Back);
        JButton Delete = new JButton("Delete");
        Delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nReally delete this scene?").toString());
                JButton ReallyDelete = new JButton("Delete");
                ReallyDelete.addActionListener(new ActionListener() {

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

                    final _cls134 this$1;
                    private final SaveData val$s;
                    private final int val$type;
                    private final int val$entry;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final int val$starting;

                    
                    {
                        this$1 = _cls134.this;
                        s = savedata;
                        type = i;
                        entry = j;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        starting = k;
                        super();
                    }
                });
                p.add(ReallyDelete);
                JButton Return = new JButton("Return to Scene Choice");
                Return.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.SceneChoice(t, p, f, w, s, type, starting, page);
                    }

                    final _cls134 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final SaveData val$s;
                    private final int val$type;
                    private final int val$starting;
                    private final int val$page;

                    
                    {
                        this$1 = _cls134.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        s = savedata;
                        type = i;
                        starting = j;
                        page = k;
                        super();
                    }
                });
                p.add(Return);
                p.validate();
                p.repaint();
            }

            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final SaveData val$s;
            private final int val$type;
            private final int val$entry;
            private final JFrame val$f;
            private final int val$starting;
            private final int val$page;

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                s = savedata;
                type = i;
                entry = j;
                f = jframe;
                starting = k;
                page = l;
                super();
            }
        });
        p.add(Delete);
        p.validate();
        p.repaint();
    }

    public static void IntroTwo(JTextPane t, JPanel p, JFrame f, WorldState w)
    {
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
        File saveLocation = new File((new StringBuilder(String.valueOf(path))).append(File.separator).append("saves.sav").toString());
        if(saveLocation.exists())
        {
            ReadObject robj = new ReadObject();
            w.save = robj.deserializeSaveData((new StringBuilder(String.valueOf(path))).append(File.separator).append("saves.sav").toString());
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
        if(w.campaign.booleanValue())
            city = w.cityName;
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nThe peaceful everyday routine of ").append(city).append(" is instantly shattered as a horde of Demons and their dominated human Thralls spills out onto the street!  Screams and alarms fill the air, chaos descending on the scene in an instant.  Already, innocents are being mobbed and dragged away towards a terrible fate.\n\nJust then, a sound like a thunderclap cuts through the panic, and a voice calls out a challenge to the Demons below!\n\n").toString());
        w.getCast()[0].say(t, (new StringBuilder("\"")).append(w.getCast()[0].announcement()).append("\"\n\n").toString());
        w.getCast()[0].transform(t, w);
        w.newCombat(w, w.getCast());
        w.append(t, "\n");
        PickTarget(t, p, f, w);
    }

    public static void PickTarget(JTextPane t, JPanel p, JFrame f, WorldState w)
    {
        Color YELLOWISH = new Color(255, 225, 125);
        Color PURPLISH = new Color(225, 125, 255);
        int inseminated = 0;
        int orgasming = 0;
        int sodomized = 0;
        int broadcasted = 0;
        p.removeAll();
        Chosen initiative[] = new Chosen[3];
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
                if(w.getCombatants()[i].isInseminated().booleanValue())
                    inseminated++;
                else
                if(w.getCombatants()[i].isOrgasming().booleanValue())
                    orgasming++;
                else
                if(w.getCombatants()[i].isSodomized().booleanValue())
                    sodomized++;
                else
                if(w.getCombatants()[i].isBroadcasted().booleanValue())
                    broadcasted++;

        w.append(t, (new StringBuilder("\nRound ")).append(w.battleRound).append("\n").toString());
        if(w.evacNotice.booleanValue())
        {
            w.append(t, "Evacuation complete!\n");
            w.evacNotice = Boolean.valueOf(false);
        } else
        {
            w.append(t, (new StringBuilder("Evacuation: ")).append(w.getEvacStatus(Boolean.valueOf(true))).append("\n").toString());
        }
        Chosen trappedChosen = null;
        for(int i = 0; i < 3; i++)
            if(w.getCombatants()[i] != null)
            {
                w.getCombatants()[i].updateSurround();
                if(w.getCombatants()[i].isSurrounded().booleanValue() || w.getCombatants()[i].isCaptured().booleanValue())
                    trappedChosen = w.getCombatants()[i];
            }

        w.append(t, (new StringBuilder("Extermination: ")).append(w.getExterminationStatus(Boolean.valueOf(true))).append("\n\n").toString());
        if(w.evacuationProgress < w.evacuationComplete)
        {
            w.append(t, "The desperate battle continues...\n");
        } else
        {
            Chosen c = null;
            Boolean allGrabbed = Boolean.valueOf(true);
            if(w.getCombatants()[0] != null)
                if(w.getCombatants()[0].isSurrounded().booleanValue() || w.getCombatants()[0].isCaptured().booleanValue() || w.finalBattle.booleanValue() && (!w.getCombatants()[0].alive.booleanValue() || w.getCombatants()[0].resolve <= 0))
                {
                    if(w.getCombatants()[1] != null)
                        if(w.getCombatants()[1].isSurrounded().booleanValue() || w.getCombatants()[1].isCaptured().booleanValue() || w.finalBattle.booleanValue() && (!w.getCombatants()[1].alive.booleanValue() || w.getCombatants()[1].resolve <= 0))
                        {
                            if(w.getCombatants()[2] != null && !w.getCombatants()[2].isSurrounded().booleanValue() && !w.getCombatants()[2].isCaptured().booleanValue() && (!w.finalBattle.booleanValue() || w.getCombatants()[2].alive.booleanValue() && w.getCombatants()[2].resolve > 0))
                                allGrabbed = Boolean.valueOf(false);
                        } else
                        {
                            allGrabbed = Boolean.valueOf(false);
                        }
                } else
                {
                    allGrabbed = Boolean.valueOf(false);
                }
            if(allGrabbed.booleanValue())
                w.append(t, "The Demons have the Chosen at their mercy!\n");
            else
            if(w.exterminationProgress >= w.exterminationComplete)
            {
                Boolean allFree = Boolean.valueOf(true);
                if(w.getCombatants()[0].isSurrounded().booleanValue() || w.getCombatants()[0].isCaptured().booleanValue())
                    allFree = Boolean.valueOf(false);
                else
                if(w.getCombatants()[1] != null)
                    if(w.getCombatants()[1].isSurrounded().booleanValue() || w.getCombatants()[1].isCaptured().booleanValue())
                        allFree = Boolean.valueOf(false);
                    else
                    if(w.getCombatants()[2] != null && (w.getCombatants()[2].isSurrounded().booleanValue() || w.getCombatants()[2].isCaptured().booleanValue()))
                        allFree = Boolean.valueOf(false);
                if(allFree.booleanValue())
                {
                    int defeated = 0;
                    Chosen survivor = null;
                    for(int i = 0; i < 3; i++)
                        if(w.finalBattle.booleanValue())
                            if(!w.getCast()[i].alive.booleanValue() || w.getCast()[i].resolve <= 0)
                                defeated++;
                            else
                                survivor = w.getCast()[i];

                    if(defeated == 2 && w.finalBattle.booleanValue())
                        w.append(t, (new StringBuilder("With ")).append(survivor.hisHer()).append(" allies defeated and no hope of winning on ").append(survivor.hisHer()).append(" own, ").append(survivor.getMainName()).append(" is preparing to make use of the hole in the Demons' formation to escape!  ").append(survivor.HeShe()).append("'ll get away next turn unless ").append(survivor.heShe()).append("'s surrounded or captured.\n").toString());
                    else
                        w.append(t, "The reanimated Demons are fighting their last stand!  Combat will end next turn unless one of the Chosen is surrounded or captured.\n");
                } else
                if(w.finalBattle.booleanValue())
                {
                    Chosen killer1 = null;
                    Chosen killer2 = null;
                    Chosen victim1 = null;
                    Chosen victim2 = null;
                    for(int i = 0; i < 3; i++)
                        if(w.getCast()[i].isSurrounded().booleanValue() || w.getCast()[i].isCaptured().booleanValue())
                        {
                            if(victim1 == null)
                                victim1 = w.getCast()[i];
                            else
                                victim2 = w.getCast()[i];
                        } else
                        if(w.getCast()[i].alive.booleanValue() && w.getCast()[i].resolve > 0)
                            if(killer1 == null)
                                killer1 = w.getCast()[i];
                            else
                                killer2 = w.getCast()[i];

                    int duration1 = 0;
                    if(victim1.isSurrounded().booleanValue())
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
                        if(victim2.isSurrounded().booleanValue())
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
                                w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" waits for ").append(victim1.getMainName()).append("'s imminent escape so that the two of them can work together to end this.\n").toString());
                            else
                                w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" and ").append(killer2.getMainName()).append(" wait for ").append(victim1.getMainName()).append(" to escape and rejoin their formation.\n").toString());
                        } else
                        {
                            w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" waits for ").append(victim1.getMainName()).append(" and ").append(victim2.getMainName()).append(" to escape and form up so they can all work together to end this.\n").toString());
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
                            if(w.getRelationship(killer1.getNumber(), victim1.getNumber()) == -4 || victim1.isImpregnated().booleanValue() || victim1.isHypnotized().booleanValue() || victim1.isDrained().booleanValue() || victim1.isParasitized().booleanValue() || victim1.temptReq < 0x186a0L || victim1.dissociationReq < 10 || victim1.negotiations > 0 || victim1.resolve < 50)
                            {
                                if(w.getRelationship(killer1.getNumber(), victim2.getNumber()) == -4 || victim2.isImpregnated().booleanValue() || victim2.isHypnotized().booleanValue() || victim2.isDrained().booleanValue() || victim2.isParasitized().booleanValue() || victim2.temptReq < 0x186a0L || victim2.dissociationReq < 10 || victim2.negotiations > 0 || victim2.resolve < 50)
                                {
                                    if(w.getTechs()[40].isOwned().booleanValue() && !killer1.hesitated.booleanValue() && (w.getRelationship(killer1.getNumber(), victim1.getNumber()) == 4 || w.getRelationship(killer1.getNumber(), victim2.getNumber()) == 4))
                                    {
                                        if(w.getRelationship(killer1.getNumber(), victim1.getNumber()) == 4)
                                        {
                                            if(w.getRelationship(killer1.getNumber(), victim2.getNumber()) == 4)
                                                w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" calls out to the other Chosen, urging them to escape before they get caught up in ").append(killer1.hisHer()).append(" final attack.").toString());
                                            else
                                                w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" prepares to launch a devastating attack in order to finish the battle, even though ").append(victim2.getMainName()).append(" is in the way.").toString());
                                        } else
                                        {
                                            w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" prepares to launch a devastating attack in order to finish the battle, even though ").append(victim1.getMainName()).append(" is in the way.").toString());
                                        }
                                    } else
                                    {
                                        w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" prepares to launch a devastating attack in order to finish the battle, even though ").append(victim1.getMainName()).append(" and ").append(victim2.getMainName()).append(" are in the way.").toString());
                                    }
                                } else
                                if(duration2 > duration1)
                                    w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" buys time for ").append(victim2.getMainName()).append(" to escape so that the two of them can work together to end this.").toString());
                                else
                                if(w.getTechs()[40].isOwned().booleanValue() && !killer1.hesitated.booleanValue() && w.getRelationship(killer1.getNumber(), victim1.getNumber()) == 4)
                                    w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" calls out to ").append(victim1.getMainName()).append(", urging ").append(victim1.himHer()).append(" to escape before ").append(victim1.heShe()).append(" gets caught up in ").append(killer1.getMainName()).append("'s final attack.").toString());
                                else
                                    w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" prepares to launch a devastating attack so that the battle can be finished after ").append(victim2.getMainName()).append(" escapes, even though ").append(victim1.getMainName()).append(" is in the way.").toString());
                            } else
                            if(w.getRelationship(killer1.getNumber(), victim2.getNumber()) == -4 || victim2.isImpregnated().booleanValue() || victim2.isHypnotized().booleanValue() || victim2.isDrained().booleanValue() || victim2.isParasitized().booleanValue() || victim2.temptReq < 0x186a0L || victim2.dissociationReq < 10 || victim2.negotiations > 0 || victim2.resolve < 50)
                            {
                                if(duration1 > duration2)
                                    w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" buys time for ").append(victim1.getMainName()).append(" to escape so that the two of them can work together to end this.").toString());
                                else
                                if(w.getTechs()[40].isOwned().booleanValue() && !killer1.hesitated.booleanValue() && w.getRelationship(killer1.getNumber(), victim2.getNumber()) == 4)
                                    w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" calls out to ").append(victim2.getMainName()).append(", urging ").append(victim2.himHer()).append(" to escape before ").append(victim2.heShe()).append(" gets caught up in ").append(killer1.getMainName()).append("'s final attack.").toString());
                                else
                                    w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" prepares to launch a devastating attack in order to finish the battle, even though ").append(victim2.getMainName()).append(" is in the way.").toString());
                            } else
                            {
                                w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" buys time for the other two Chosen to escape so that they all can work together to end this.").toString());
                            }
                        } else
                        if(killer2 != null)
                        {
                            if(victim1.isImpregnated().booleanValue() || victim1.isHypnotized().booleanValue() || victim1.isDrained().booleanValue() || victim1.isParasitized().booleanValue() || victim1.temptReq < 0x186a0L || victim1.dissociationReq < 10 || victim1.negotiations > 0 || victim1.resolve < 50)
                            {
                                if(w.getTechs()[40].isOwned().booleanValue())
                                {
                                    if(w.getRelationship(killer1.getNumber(), victim1.getNumber()) == 4 && !killer1.hesitated.booleanValue())
                                    {
                                        if(w.getRelationship(killer2.getNumber(), victim1.getNumber()) == 4 && !killer2.hesitated.booleanValue())
                                            w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" and ").append(killer2.getMainName()).append(" call out to ").append(victim1.getMainName()).append(", urging ").append(victim1.himHer()).append(" to escape before ").append(victim1.heShe()).append(" gets caught up in their final attack.").toString());
                                        else
                                            w.append(t, (new StringBuilder(String.valueOf(victim1.getMainName()))).append("'s captivity is preventing the other Chosen from ending the battle, and while ").append(killer1.getMainName()).append(" looks conflicted, ").append(killer2.getMainName()).append(" is preparing to attack anyway.").toString());
                                    } else
                                    if(w.getRelationship(killer2.getNumber(), victim1.getNumber()) == 4 && !killer2.hesitated.booleanValue())
                                        w.append(t, (new StringBuilder(String.valueOf(victim1.getMainName()))).append("'s captivity is preventing the other Chosen from ending the battle, and while ").append(killer2.getMainName()).append(" looks conflicted, ").append(killer1.getMainName()).append(" is preparing to attack anyway.").toString());
                                    else
                                        w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" and ").append(killer2.getMainName()).append(" prepare to launch their most devastating attacks in order to finish the battle, even though ").append(victim1.getMainName()).append(" is in the way.").toString());
                                } else
                                {
                                    w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" and ").append(killer2.getMainName()).append(" prepare to launch their most devastating attacks in order to finish the battle, even though ").append(victim1.getMainName()).append(" is in the way.").toString());
                                }
                            } else
                            if(w.getRelationship(killer1.getNumber(), victim1.getNumber()) == -4)
                            {
                                if(w.getRelationship(killer2.getNumber(), victim1.getNumber()) == -4)
                                    w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" and ").append(killer2.getMainName()).append(" prepare to launch their most devastating attacks in order to finish the battle, even though ").append(victim1.getMainName()).append(" is in the way.").toString());
                                else
                                    w.append(t, (new StringBuilder(String.valueOf(victim1.getMainName()))).append("'s captivity is preventing the other Chosen from ending the battle, and while ").append(killer2.getMainName()).append(" isn't willing to sacrifice ").append(victim1.getMainName()).append("'s life in order to finish things sooner, ").append(killer1.getMainName()).append(" is.").toString());
                            } else
                            if(w.getRelationship(killer2.getNumber(), victim1.getNumber()) == -4)
                                w.append(t, (new StringBuilder(String.valueOf(victim1.getMainName()))).append("'s captivity is preventing the other Chosen from ending the battle, and while ").append(killer1.getMainName()).append(" isn't willing to sacrifice ").append(victim1.getMainName()).append("'s life in order to finish things sooner, ").append(killer2.getMainName()).append(" is.").toString());
                            else
                                w.append(t, (new StringBuilder(String.valueOf(victim1.getMainName()))).append("'s captivity is preventing the other Chosen from ending the battle, but ").append(killer1.getMainName()).append(" and ").append(killer2.getMainName()).append(" aren't willing to sacrifice ").append(victim1.hisHer()).append(" life just to finish things a little bit sooner.").toString());
                        } else
                        if(w.getRelationship(killer1.getNumber(), victim1.getNumber()) == -4 || victim1.isImpregnated().booleanValue() || victim1.isHypnotized().booleanValue() || victim1.isDrained().booleanValue() || victim1.isParasitized().booleanValue() || victim1.temptReq < 0x186a0L || victim1.dissociationReq < 10 || victim1.negotiations > 0 || victim1.resolve < 50)
                        {
                            if(w.getTechs()[40].isOwned().booleanValue() && !killer1.hesitated.booleanValue() && w.getRelationship(killer1.getNumber(), victim1.getNumber()) == 4)
                                w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" calls out to ").append(victim1.getMainName()).append(", urging ").append(victim1.himHer()).append(" to escape before ").append(victim1.heShe()).append(" gets caught up in ").append(killer1.getMainName()).append("'s final attack.").toString());
                            else
                                w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" prepares to launch a devastating attack in order to finish the battle, even though ").append(victim1.getMainName()).append(" is in the way.").toString());
                        } else
                        {
                            w.append(t, (new StringBuilder(String.valueOf(killer1.getMainName()))).append(" buys time for ").append(victim1.getMainName()).append(" to escape so that the two of them can work together to finish this.").toString());
                        }
                        w.append(t, "\n");
                    }
                } else
                {
                    while(c == null) 
                    {
                        c = w.getCombatants()[(int)(Math.random() * 3D)];
                        if(c != null && (c.isSurrounded().booleanValue() || c.isCaptured().booleanValue()))
                            c = null;
                    }
                    w.append(t, (new StringBuilder(String.valueOf(c.getMainName()))).append(" can't finish clearing out the Demons due to the risk of hitting the trapped ").append(trappedChosen.getMainName()).append(" with friendly fire!\n").toString());
                }
            } else
            {
                while(c == null) 
                {
                    c = w.getCombatants()[(int)(Math.random() * 3D)];
                    if(c != null && (c.isSurrounded().booleanValue() || c.isCaptured().booleanValue()))
                        c = null;
                }
                Boolean plural = Boolean.valueOf(false);
                if(w.getCombatants()[1] != null)
                    plural = Boolean.valueOf(true);
                if(w.exterminationMultiplier == 100)
                {
                    w.append(t, (new StringBuilder("With the civilians evacuated, ")).append(c.getMainName()).toString());
                    if(plural.booleanValue())
                        w.append(t, " and the other Chosen can start drawing on their full power!");
                    else
                        w.append(t, (new StringBuilder(" can start drawing on ")).append(c.hisHer()).append(" full power!").toString());
                } else
                if(w.exterminationMultiplier == 150)
                    w.append(t, (new StringBuilder(String.valueOf(c.getMainName()))).append("'s attacks grow stronger and stronger, shattering windows and setting off alarms!").toString());
                else
                if(w.exterminationMultiplier == 225)
                    w.append(t, (new StringBuilder(String.valueOf(c.getMainName()))).append(" moves like a blur, taking down a wide swath of Demons!").toString());
                else
                if(w.exterminationMultiplier == 337)
                    w.append(t, (new StringBuilder("A blast of energy from ")).append(c.getMainName()).append(" brings down a small building in a cloud of rubble!").toString());
                else
                if(w.exterminationMultiplier == 505)
                    w.append(t, (new StringBuilder("The area is riddled with craters caused by the power of ")).append(c.getMainName()).append("'s attacks!").toString());
                else
                if(w.exterminationMultiplier == 757)
                    w.append(t, (new StringBuilder("The district is consumed by an enormous explosion as ")).append(c.getMainName()).append(" blasts away the Demons!").toString());
                w.append(t, "\n(Extermination power");
                if(w.cast[1] != null)
                    w.append(t, " per Chosen");
                w.append(t, (new StringBuilder(": ")).append((w.exterminationPerChosen * w.exterminationMultiplier) / 100).append(")").toString());
                w.append(t, "\n");
            }
        }
        for(int i = 0; i < w.getCombatants().length; i++)
            if(w.getCombatants()[i] != null)
            {
                w.append(t, "\n");
                if(w.getCombatants()[i].type == Chosen.Species.SUPERIOR)
                    w.append(t, "[SUPERIOR] ");
                if(w.getCombatants()[i].isSurrounded().booleanValue() && (w.getCombatants()[i].resolve > 0 || !w.finalBattle.booleanValue()))
                {
                    w.orangeAppend(t, (new StringBuilder(String.valueOf(w.getCombatants()[i].getMainName()))).append(": ").toString());
                    if(inseminated == 3 || orgasming == 3 || sodomized == 3 || broadcasted == 3)
                        w.orangeAppend(t, "In Orgy");
                    else
                    if(w.getCombatants()[i].isInseminated().booleanValue())
                        w.orangeAppend(t, "Inseminated");
                    else
                    if(w.getCombatants()[i].isOrgasming().booleanValue())
                        w.orangeAppend(t, "Orgasming");
                    else
                    if(w.getCombatants()[i].isSodomized().booleanValue())
                    {
                        if(w.tickle().booleanValue())
                            w.orangeAppend(t, "Laughing");
                        else
                        if(w.getCombatants()[i].getGender().equals("male"))
                            w.orangeAppend(t, "Tortured");
                        else
                            w.orangeAppend(t, "Sodomized");
                    } else
                    if(w.getCombatants()[i].isBroadcasted().booleanValue())
                        w.orangeAppend(t, "Broadcasted");
                    else
                    if(w.getCombatants()[i].tempted.booleanValue())
                        w.orangeAppend(t, "Tempted");
                    else
                        w.orangeAppend(t, "Surrounded");
                    if(w.getCombatants()[i].getSurroundDuration() > 1)
                        w.orangeAppend(t, (new StringBuilder(" for ")).append(w.getCombatants()[i].getSurroundDuration()).append(" more turns").toString());
                    else
                        w.orangeAppend(t, " until next turn");
                } else
                if(w.getCombatants()[i].isCaptured().booleanValue())
                {
                    w.orangeAppend(t, (new StringBuilder(String.valueOf(w.getCombatants()[i].getMainName()))).append(": ").toString());
                    if(w.getCombatants()[i].timesDetonated() > 0 && !w.adaptationsDisabled().booleanValue())
                    {
                        if(w.getCombatants()[i].getCaptureProgression() + w.getCombatants()[i].getINJULevel() + 1 >= w.getCaptureDuration())
                            w.orangeAppend(t, "Detonating next turn");
                        else
                        if(w.getCombatants()[i].getCaptureProgression() + w.getCombatants()[i].getINJULevel() + 2 == w.getCaptureDuration())
                            w.orangeAppend(t, "Detonating in 2 more turns");
                        else
                        if(w.getBodyStatus()[5].booleanValue() || w.getBodyStatus()[12].booleanValue() || w.getBodyStatus()[13].booleanValue() || w.getBodyStatus()[21].booleanValue() || w.usedForsaken != null)
                            w.orangeAppend(t, (new StringBuilder("Detonating in up to ")).append(w.getCaptureDuration() - w.getCombatants()[i].getCaptureProgression() - w.getCombatants()[i].getINJULevel()).append(" more turns").toString());
                        else
                            w.orangeAppend(t, (new StringBuilder("Detonating in ")).append(w.getCaptureDuration() - w.getCombatants()[i].getCaptureProgression() - w.getCombatants()[i].getINJULevel()).append(" more turns").toString());
                    } else
                    if(w.getCombatants()[i].getCaptureProgression() < w.getCaptureDuration())
                        w.orangeAppend(t, (new StringBuilder("Captured for ")).append((w.getCaptureDuration() - w.getCombatants()[i].getCaptureProgression()) + 1).append(" more turns").toString());
                    else
                        w.orangeAppend(t, "Captured until next turn");
                } else
                if(!w.getCombatants()[i].alive.booleanValue())
                    w.redAppend(t, (new StringBuilder(String.valueOf(w.getCombatants()[i].getMainName()))).append(": Killed in Action").toString());
                else
                if(w.finalBattle.booleanValue() && w.getCombatants()[i].resolve <= 0)
                    w.greenAppend(t, (new StringBuilder(String.valueOf(w.getCombatants()[i].getMainName()))).append(": Resolve Broken!").toString());
                else
                if(w.getCombatants()[i].dissociated.booleanValue())
                {
                    if(w.getCombatants()[0].dissociated.booleanValue() && w.getCombatants()[1].dissociated.booleanValue() && w.getCombatants()[2].dissociated.booleanValue())
                        w.greenAppend(t, (new StringBuilder(String.valueOf(w.getCombatants()[i].getMainName()))).append(": Catatonic").toString());
                    else
                        w.greenAppend(t, (new StringBuilder(String.valueOf(w.getCombatants()[i].getMainName()))).append(": Fleeing").toString());
                } else
                if(w.getCombatants()[i].surroundPossible(w).booleanValue())
                {
                    if(!w.dissociationSurroundPossible().booleanValue())
                        w.purpleAppend(t, (new StringBuilder(String.valueOf(w.getCombatants()[i].getMainName()))).append(": Opening Level ").append(w.getCombatants()[i].getFEAROpening(w) + w.getCombatants()[i].getPAINOpening() + w.getCombatants()[i].getDISGOpening() + w.getCombatants()[i].getSHAMOpening(w)).append(" vs. Defense Level ").append(w.getCombatants()[i].getDefenseLevel()).toString());
                    else
                        w.purpleAppend(t, (new StringBuilder(String.valueOf(w.getCombatants()[i].getMainName()))).append(": Open to Surround").toString());
                } else
                if(w.getCombatants()[i].getDefenseLevel() > 9000)
                    w.append(t, (new StringBuilder(String.valueOf(w.getCombatants()[i].getMainName()))).append(": Flying Above Battlefield").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(w.getCombatants()[i].getMainName()))).append(": Opening Level ").append(w.getCombatants()[i].getFEAROpening(w) + w.getCombatants()[i].getPAINOpening() + w.getCombatants()[i].getDISGOpening() + w.getCombatants()[i].getSHAMOpening(w)).append(" vs. Defense Level ").append(w.getCombatants()[i].getDefenseLevel()).toString());
                if(w.finalBattle.booleanValue() && w.getCombatants()[i].resolve > 0 && w.getCombatants()[i].alive.booleanValue())
                    w.append(t, (new StringBuilder(" [Resolve at ")).append(w.getCombatants()[i].resolve).append("%]").toString());
            }

        if(w.usedForsaken != null)
        {
            w.append(t, (new StringBuilder("\n\n")).append(w.usedForsaken.mainName).append(": ").toString());
            int occupied = -1;
            for(int i = 0; i < w.getCombatants().length; i++)
                if(w.getCombatants()[i] != null && w.getCombatants()[i].captured.booleanValue())
                    occupied = i;

            if(occupied >= 0)
                w.purpleAppend(t, (new StringBuilder("Busy with ")).append(w.getCombatants()[occupied].mainName).toString());
            else
            if(w.usedForsaken.injured > 1)
            {
                w.redAppend(t, (new StringBuilder("Stunned for ")).append(w.usedForsaken.injured).append(" turns").toString());
                changePortrait(w.usedForsaken.gender, w.usedForsaken.type, displayedCivilians[3], Boolean.valueOf(true), w, w.nameCombatants(), 3, Emotion.SWOON, Emotion.SWOON);
            } else
            if(w.usedForsaken.injured == 1)
            {
                w.redAppend(t, "Stunned until next turn");
                changePortrait(w.usedForsaken.gender, w.usedForsaken.type, displayedCivilians[3], Boolean.valueOf(true), w, w.nameCombatants(), 3, Emotion.SWOON, Emotion.SWOON);
            } else
            {
                w.greenAppend(t, "Ready to capture target");
                if(w.usedForsaken.flavorObedience() < 20)
                    changePortrait(w.usedForsaken.gender, w.usedForsaken.type, displayedCivilians[3], Boolean.valueOf(true), w, w.nameCombatants(), 3, Emotion.ANGER, Emotion.NEUTRAL);
                else
                if(w.usedForsaken.flavorObedience() < 40)
                    changePortrait(w.usedForsaken.gender, w.usedForsaken.type, displayedCivilians[3], Boolean.valueOf(true), w, w.nameCombatants(), 3, Emotion.ANGER, Emotion.SHAME);
                else
                if(w.usedForsaken.flavorObedience() < 61)
                    changePortrait(w.usedForsaken.gender, w.usedForsaken.type, displayedCivilians[3], Boolean.valueOf(true), w, w.nameCombatants(), 3, Emotion.SHAME, Emotion.STRUGGLE);
                else
                if(w.usedForsaken.flavorObedience() < 81)
                    changePortrait(w.usedForsaken.gender, w.usedForsaken.type, displayedCivilians[3], Boolean.valueOf(true), w, w.nameCombatants(), 3, Emotion.FOCUS, Emotion.ANGER);
                else
                    changePortrait(w.usedForsaken.gender, w.usedForsaken.type, displayedCivilians[3], Boolean.valueOf(true), w, w.nameCombatants(), 3, Emotion.FOCUS, Emotion.JOY);
            }
        }
        if(w.getRallyBonus() > 0)
            w.append(t, (new StringBuilder("\n\nMorale bonus: incoming trauma decreased by ")).append(w.getRallyBonus() / 6).append("%").toString());
        if(w.getDistractBonus() > 0)
            w.append(t, (new StringBuilder("\n\nThralls distracted: damage to surrounded Chosen decreased by ")).append(w.getDistractBonus() / 3).append("%").toString());
        if(w.getBarrierMulti() > 10000L)
            w.append(t, (new StringBuilder("\n\nDemonic barrier: all damage increased by ")).append(w.getBarrierMulti() / 100L - 100L).append("%").toString());
        int targets = 0;
        int targetFound = 0;
        int defeated = 0;
        int dissociated = 0;
        int trapped = 0;
        for(int i = 0; i < 3; i++)
            if(w.getCombatants()[i] != null)
                if(!w.finalBattle.booleanValue())
                {
                    if(!w.getCombatants()[i].dissociated.booleanValue())
                    {
                        targets++;
                        targetFound = i;
                    } else
                    {
                        dissociated++;
                    }
                } else
                if(w.getCombatants()[i].isCaptured().booleanValue() || w.getCombatants()[i].isSurrounded().booleanValue() && (w.getCombatants()[i].isDefiled().booleanValue() || w.getCombatants()[i].getHATELevel() < 3 && w.getCombatants()[i].getPLEALevel() < 3 && w.getCombatants()[i].getINJULevel() < 3 && w.getCombatants()[i].getEXPOLevel() < 3 && w.getCombatants()[i].grind.booleanValue() && w.getCombatants()[i].caress.booleanValue() && w.getCombatants()[i].pummel.booleanValue() && w.getCombatants()[i].humiliate.booleanValue()))
                    trapped++;
                else
                if(w.getCombatants()[i].alive.booleanValue() && w.getCombatants()[i].resolve > 0)
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
                if(w.finalBattle.booleanValue() && defeated > 0)
                    w.append(t, (new StringBuilder("\n\n")).append(w.getCombatants()[targetFound].getMainName()).append(" is still resisting!").toString());
                else
                if(w.getCombatants()[1] != null)
                    w.append(t, (new StringBuilder("\n\n")).append(w.getCombatants()[targetFound].getMainName()).append(" is trying to stall until the team can fight at full strength!").toString());
                else
                    w.append(t, (new StringBuilder("\n\n")).append(w.getCombatants()[targetFound].getMainName()).append(" is fighting alone!").toString());
        } else
        if(targets == 0)
        {
            if(w.getCombatants()[1] == null)
                w.append(t, (new StringBuilder("\n\n")).append(w.getCombatants()[0].getMainName()).append("'s allies haven't shown up yet!").toString());
            else
            if(w.getCombatants()[0].dissociated.booleanValue())
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
                if(w.getCombatants()[i] != null && (!w.finalBattle.booleanValue() || w.getCombatants()[i].resolve > 0 && w.getCombatants()[i].alive.booleanValue()) && !w.getCombatants()[i].dissociated.booleanValue())
                {
                    int thisChosen = i;
                    class _cls1TargetButton extends AbstractAction
                    {

                        public void actionPerformed(ActionEvent e)
                        {
                            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
                            Project.PickAction(t, p, f, w, w.getCombatants()[thisChosen], initiative);
                        }

                        private final WorldState val$w;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final int val$thisChosen;
                        private final Chosen val$initiative[];

            public _cls1TargetButton(int i, 
                    Chosen achosen[])
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                thisChosen = i;
                initiative = achosen;
                super(text);
                putValue("ShortDescription", desc);
            }
                    }

                    Action TargetAction = new _cls1TargetButton(thisChosen, initiative);
                    JButton Target = new JButton(TargetAction) {

                        public Point getToolTipLocation(MouseEvent e)
                        {
                            return new Point(0, -30);
                        }

                    };
                    if((w.getCombatants()[i].getCurrentHATE() >= 10000L || w.getCombatants()[i].getCurrentPLEA() >= 10000L || w.getCombatants()[i].getCurrentINJU() >= 10000L || w.getCombatants()[i].getCurrentEXPO() >= 10000L) && w.getCombatants()[i].isSurrounded().booleanValue() && !w.getCombatants()[i].isDefiled().booleanValue())
                        if(w.getCombatants()[i].getCurrentHATE() >= 10000L && inseminated > 0 || w.getCombatants()[i].getCurrentPLEA() >= 10000L && orgasming > 0 || w.getCombatants()[i].getCurrentINJU() >= 10000L && sodomized > 0 || w.getCombatants()[i].getCurrentEXPO() >= 10000L && broadcasted > 0)
                            Target.setBackground(PURPLISH);
                        else
                            Target.setBackground(YELLOWISH);
                    Target.getInputMap(2).put(KeyStroke.getKeyStroke((new StringBuilder()).append(thisChosen + 1).toString()), "pressed");
                    if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && (w.getActions()[w.getCurrentAction()] - 1) / 14 == w.getCombatants()[thisChosen].getNumber())
                        Target.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                    Target.getActionMap().put("pressed", TargetAction);
                    p.add(Target);
                }

            class _cls1PassButton extends AbstractAction
            {

                public void actionPerformed(ActionEvent e)
                {
                    Project.advanceAction(p, w, 0);
                    if(w.getTechs()[30].isOwned().booleanValue() && !w.progressExtermination(0))
                    {
                        p.removeAll();
                        w.increaseBarrier(t);
                        class _cls1ContinueButton extends AbstractAction
                        {

                            public void actionPerformed(ActionEvent e)
                            {
                                Project.EnemyTurn(t, p, f, w, initiative, 0);
                            }

                            final _cls1PassButton this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;
                            private final Chosen val$initiative[];

                    public _cls1ContinueButton(WorldState worldstate, 
                            Chosen achosen[])
                    {
                        this$1 = _cls1PassButton.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        initiative = achosen;
                        super(text);
                        putValue("ShortDescription", desc);
                    }
                        }

                        Action ContinueAction = new _cls1ContinueButton(w, initiative);
                        JButton Continue = new JButton(ContinueAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -30);
                            }

                            final _cls1PassButton this$1;

                    
                    {
                        this$1 = _cls1PassButton.this;
                        super($anonymous0);
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

                private final JPanel val$p;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JFrame val$f;
                private final Chosen val$initiative[];

            public _cls1PassButton(JFrame jframe, Chosen achosen[])
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                initiative = achosen;
                super(text);
                putValue("ShortDescription", desc);
            }
            }

            Action PassAction = new _cls1PassButton(f, initiative);
            JButton Pass = new JButton(PassAction) {

                public Point getToolTipLocation(MouseEvent e)
                {
                    return new Point(0, -30);
                }

            };
            Pass.setForeground(Color.GRAY);
            Pass.getInputMap(2).put(KeyStroke.getKeyStroke(70, 0), "pressed");
            if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == 0)
                Pass.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
            Pass.getActionMap().put("pressed", PassAction);
            if(w.getTechs()[30].isOwned().booleanValue() && !w.progressExtermination(0))
            {
                Pass.setText("Barrier");
                Pass.setToolTipText("+5% damage for rest of battle");
            }
            p.add(Pass);
            int occupied = 0;
            for(int i = 0; i < 3; i++)
                if(w.getCombatants()[i] != null)
                    if(w.getCombatants()[i].isSurrounded().booleanValue())
                    {
                        if(w.getCombatants()[i].getSurroundDuration() > 0)
                            occupied += w.getCombatants()[i].getSurroundDuration();
                        else
                            occupied++;
                    } else
                    if(w.getCombatants()[i].isCaptured().booleanValue())
                        occupied += (w.getCaptureDuration() - w.getCombatants()[i].getCaptureProgression()) + 1;

            int occupiedBonus = occupied / 5;
            class _cls1RetreatButton extends AbstractAction
            {

                public void actionPerformed(ActionEvent e)
                {
                    p.removeAll();
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    if(occupiedBonus > 0)
                        w.append(t, (new StringBuilder("Retreat and end the battle immediately for +")).append(occupiedBonus).append(" Evil Energy?").toString());
                    else
                        w.append(t, "Really retreat?  You will not gain any bonus Evil Energy!");
                    JButton Confirm = new JButton("Confirm");
                    Confirm.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            p.removeAll();
                            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                            String trapped[] = new String[3];
                            String free[] = new String[3];
                            int trappedNumber = 0;
                            for(int i = 0; i < 3; i++)
                                if(w.getCombatants()[i] != null)
                                    if(w.getCombatants()[i].isSurrounded().booleanValue() || w.getCombatants()[i].isCaptured().booleanValue())
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
                                    if(w.getCast()[i] != null && !w.getCast()[i].equals(w.getCombatants()[0]).booleanValue())
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
                                    if(w.getCast()[i] != null && !w.getCast()[i].equals(w.getCombatants()[0]).booleanValue() && !w.getCast()[i].equals(w.getCombatants()[1]).booleanValue())
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
                                    w.append(t, (new StringBuilder("However, ")).append(free[0]).append(" is quick to pursue, cutting your forces down from behind and stopping them from taking any significant number of civilians back to the hive.").toString());
                                else
                                    w.append(t, (new StringBuilder(String.valueOf(trapped[0]))).append(" is unable to follow until plenty of civilians are already on their way to the hive.").toString());
                            } else
                            if(w.getCast()[2] == null)
                            {
                                if(trappedNumber == 0)
                                    w.append(t, "However, the two Chosen cut your forces down from behind, freeing most of the civilians before they can be brought to the hive.");
                                else
                                if(trappedNumber == 1)
                                    w.append(t, (new StringBuilder("With ")).append(trapped[0]).append(" unable to give chase, the risk of splitting the team forces ").append(free[0]).append(" to give up and let you take the civilians to the hive.").toString());
                                else
                                    w.append(t, "The Chosen have to finish dealing with their own problems before they can try to stop you, and by then, plenty of your forces have escaped.");
                            } else
                            if(trappedNumber == 0 || occupiedBonus == 0)
                                w.append(t, "However, the three Chosen cut your forces down from behind, freeing most of the civilians before they can be brought to the hive.");
                            else
                            if(trappedNumber == 1)
                                w.append(t, (new StringBuilder(String.valueOf(free[0]))).append(" and ").append(free[1]).append(" try to give chase, but with ").append(trapped[0]).append(" unable to follow, they're forced to give up due to the risk of splitting the team.").toString());
                            else
                            if(trappedNumber == 2)
                                w.append(t, (new StringBuilder(String.valueOf(free[0]))).append(" tries to stop them, but with ").append(trapped[0]).append(" and ").append(trapped[1]).append(" unable to help, you're able to get plenty of victims to the hive.").toString());
                            else
                                w.append(t, "The Chosen have to finish dealing with their own problems before they can try to stop you, and by then, plenty of your forces have escaped.");
                            if(occupiedBonus > 0)
                                w.append(t, (new StringBuilder("\n\n+")).append(occupiedBonus).append(" Evil Energy").toString());
                            Project.advanceAction(p, w, 43);
                            w.addEnergy(occupiedBonus);
                            JButton Continue = new JButton("Continue");
                            Continue.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.PostBattle(t, p, f, w);
                                }

                                final _cls1 this$2;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final WorldState val$w;

                        
                        {
                            this$2 = _cls1.this;
                            t = jtextpane;
                            p = jpanel;
                            f = jframe;
                            w = worldstate;
                            super();
                        }
                            });
                            p.add(Continue);
                            p.validate();
                            p.repaint();
                        }

                        final _cls1RetreatButton this$1;
                        private final JPanel val$p;
                        private final WorldState val$w;
                        private final JTextPane val$t;
                        private final int val$occupiedBonus;
                        private final JFrame val$f;

                    
                    {
                        this$1 = _cls1RetreatButton.this;
                        p = jpanel;
                        w = worldstate;
                        t = jtextpane;
                        occupiedBonus = i;
                        f = jframe;
                        super();
                    }
                    });
                    p.add(Confirm);
                    JButton Cancel = new JButton("Cancel");
                    Cancel.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n").toString());
                            Project.PickTarget(t, p, f, w);
                        }

                        final _cls1RetreatButton this$1;
                        private final WorldState val$w;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;

                    
                    {
                        this$1 = _cls1RetreatButton.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                    });
                    p.add(Cancel);
                    p.validate();
                    p.repaint();
                }

                private final JPanel val$p;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final int val$occupiedBonus;
                private final JFrame val$f;

            public _cls1RetreatButton(int i, JFrame jframe)
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                occupiedBonus = i;
                f = jframe;
                super(text);
                putValue("ShortDescription", desc);
            }
            }

            Action RetreatAction = new _cls1RetreatButton(occupiedBonus, f);
            JButton Retreat = new JButton(RetreatAction) {

                public Point getToolTipLocation(MouseEvent e)
                {
                    return new Point(0, -30);
                }

            };
            if(w.getTechs()[19].isOwned().booleanValue() && !w.finalBattle.booleanValue())
                p.add(Retreat);
            if(w.writePossible().booleanValue())
                addWriteButton(p, w);
            p.validate();
            p.repaint();
        }
        w.readCommentary(t);
    }

    public static void advanceAction(JPanel p, WorldState w, int action)
    {
        Boolean actionMatches = Boolean.valueOf(true);
        if(w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] != action)
        {
            actionMatches = Boolean.valueOf(false);
            w.truncateCommentary(w.getCurrentAction());
        }
        if(w.writePossible().booleanValue())
            if(w.getCurrentComment().length() > 0)
                w.writeCommentary(w.getCurrentComment());
            else
            if(w.getCommentary().length <= w.getCurrentAction() || !actionMatches.booleanValue())
            {
                String generated = "";
                if(action == 0)
                {
                    if(w.getTechs()[30].isOwned().booleanValue() && !w.progressExtermination(0))
                        generated = "Deepen your barrier.";
                    else
                        generated = "Do nothing.";
                } else
                if(action == 43)
                    generated = "Retreat from the battle.";
                else
                if(action > 46)
                {
                    String targetedChosen = w.getCast()[(action - 47) % 3].getMainName();
                    generated = (new StringBuilder("Appeal to ")).append(targetedChosen).append(".").toString();
                } else
                if(action > 43)
                {
                    int type = (action - 44) / 3;
                    int target = (action - 44) % 3;
                    String targetedChosen = w.getCast()[target].getMainName();
                    if(type == 0)
                        generated = "Tempt ";
                    generated = (new StringBuilder(String.valueOf(generated))).append(targetedChosen).append(".").toString();
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
                        if(w.tickle().booleanValue())
                            generated = "Poke ";
                        else
                            generated = "Attack ";
                    } else
                    if(type == 6)
                    {
                        generated = "Taunt ";
                    } else
                    {
                        if(w.getTechs()[31].isOwned().booleanValue() && !w.getCast()[target].isSurrounded().booleanValue())
                            if(!w.getCast()[target].surroundPossible(w).booleanValue())
                                generated = (new StringBuilder(String.valueOf(generated))).append("Capture and then ").toString();
                            else
                                generated = (new StringBuilder(String.valueOf(generated))).append("Surround and then ").toString();
                        if(type == 7)
                            generated = (new StringBuilder(String.valueOf(generated))).append("Grind against ").toString();
                        else
                        if(type == 8)
                            generated = (new StringBuilder(String.valueOf(generated))).append("Caress ").toString();
                        else
                        if(type == 9)
                        {
                            if(w.tickle().booleanValue())
                                generated = (new StringBuilder(String.valueOf(generated))).append("Tickle ").toString();
                            else
                                generated = (new StringBuilder(String.valueOf(generated))).append("Pummel ").toString();
                        } else
                        if(type == 10)
                            generated = (new StringBuilder(String.valueOf(generated))).append("Humiliate ").toString();
                        else
                        if(type == 11)
                            generated = (new StringBuilder(String.valueOf(generated))).append("Inseminate ").toString();
                        else
                        if(type == 12)
                            generated = (new StringBuilder(String.valueOf(generated))).append("Force Orgasm on ").toString();
                        else
                        if(type == 13)
                        {
                            if(w.tickle().booleanValue())
                                generated = (new StringBuilder(String.valueOf(generated))).append("Force Laughter from ").toString();
                            else
                            if(w.getCast()[target].getGender().equals("male"))
                                generated = (new StringBuilder(String.valueOf(generated))).append("Torture ").toString();
                            else
                                generated = (new StringBuilder(String.valueOf(generated))).append("Sodomize ").toString();
                        } else
                        if(type == 14)
                            generated = (new StringBuilder(String.valueOf(generated))).append("Broadcast ").toString();
                    }
                    generated = (new StringBuilder(String.valueOf(generated))).append(targetedChosen).append(".").toString();
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
                    prompt = (new StringBuilder(String.valueOf(prompt))).append("keep the comment you already wrote.").toString();
                else
                if(w.getCommentary().length > w.getCurrentAction())
                    prompt = (new StringBuilder(String.valueOf(prompt))).append("keep the previous playthrough's comment.").toString();
                else
                    prompt = (new StringBuilder(String.valueOf(prompt))).append("generate a default comment describing your action.").toString();
                String input = JOptionPane.showInputDialog(prompt);
                if(input != null && input.length() > 0)
                {
                    w.setCurrentComment(input);
                    Comment.setToolTipText((new StringBuilder("\"")).append(input).append("\"").toString());
                }
            }

            private final WorldState val$w;
            private final JButton val$Comment;

            
            {
                w = worldstate;
                Comment = jbutton;
                super();
            }
        });
        Comment.setForeground(Color.GRAY);
        Comment.setToolTipText("No comment currently stored.");
        p.add(Comment);
    }

    public static void PickAction(JTextPane t, JPanel p, JFrame f, WorldState w, Chosen c, Chosen initiative[])
    {
        Color YELLOWISH = new Color(255, 225, 125);
        Color PURPLISH = new Color(225, 125, 255);
        Color REDDISH = new Color(255, 145, 145);
        int inseminated = 0;
        int orgasming = 0;
        int sodomized = 0;
        int broadcasted = 0;
        for(int i = 0; i < 3; i++)
            if(w.getCombatants()[i] != null)
                if(w.getCombatants()[i].isInseminated().booleanValue())
                    inseminated++;
                else
                if(w.getCombatants()[i].isOrgasming().booleanValue())
                    orgasming++;
                else
                if(w.getCombatants()[i].isSodomized().booleanValue())
                    sodomized++;
                else
                if(w.getCombatants()[i].isBroadcasted().booleanValue())
                    broadcasted++;

        class _cls1ContinueButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Chosen val$initiative[];

            public _cls1ContinueButton(WorldState worldstate, Chosen achosen[])
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                initiative = achosen;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action ContinueAction = new _cls1ContinueButton(w, initiative);
        final String attackName = "Attack";
        if(w.tickle().booleanValue())
            attackName = "Poke";
        class _cls1AttackButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                if(w.finalBattle.booleanValue() && w.getTechs()[44].isOwned().booleanValue())
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

                    final _cls1AttackButton this$1;

                    
                    {
                        this$1 = _cls1AttackButton.this;
                        super($anonymous0);
                    }
                };
                Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Continue.getActionMap().put("pressed", ContinueAction);
                p.add(Continue);
                p.validate();
                p.repaint();
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final Chosen val$c;
            private final JPanel val$p;
            private final JFrame val$f;
            private final Action val$ContinueAction;

            public _cls1AttackButton(JFrame jframe, 
                    Action action)
            {
                w = worldstate;
                t = jtextpane;
                c = chosen;
                p = jpanel;
                f = jframe;
                ContinueAction = action;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action AttackAction = new _cls1AttackButton(f, ContinueAction);
        class _cls1SlimeButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                if(w.finalBattle.booleanValue() && w.getTechs()[43].isOwned().booleanValue() && c.isHypnotized().booleanValue())
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

                    final _cls1SlimeButton this$1;

                    
                    {
                        this$1 = _cls1SlimeButton.this;
                        super($anonymous0);
                    }
                };
                Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Continue.getActionMap().put("pressed", ContinueAction);
                p.add(Continue);
                p.validate();
                p.repaint();
            }

            private final WorldState val$w;
            private final Chosen val$c;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final Action val$ContinueAction;

            public _cls1SlimeButton(JFrame jframe, 
                    Action action)
            {
                w = worldstate;
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                ContinueAction = action;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action SlimeAction = new _cls1SlimeButton(f, ContinueAction);
        class _cls1TauntButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                if(w.finalBattle.booleanValue() && w.getTechs()[45].isOwned().booleanValue() && c.isParasitized().booleanValue() && c.surroundPossible(w).booleanValue())
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

                    final _cls1TauntButton this$1;

                    
                    {
                        this$1 = _cls1TauntButton.this;
                        super($anonymous0);
                    }
                };
                Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Continue.getActionMap().put("pressed", ContinueAction);
                p.add(Continue);
                p.validate();
                p.repaint();
            }

            private final WorldState val$w;
            private final Chosen val$c;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final Action val$ContinueAction;

            public _cls1TauntButton(JFrame jframe, 
                    Action action)
            {
                w = worldstate;
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                ContinueAction = action;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action TauntAction = new _cls1TauntButton(f, ContinueAction);
        class _cls1ThreatenButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                Boolean impregnatedAlly = Boolean.valueOf(false);
                for(int i = 0; i < 3; i++)
                    if(i != c.getNumber() && w.getCast()[i] != null && w.getCast()[i].isImpregnated().booleanValue() && w.getCast()[i].alive.booleanValue())
                        impregnatedAlly = Boolean.valueOf(true);

                if(w.finalBattle.booleanValue() && w.getTechs()[42].isOwned().booleanValue() && impregnatedAlly.booleanValue())
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

                    final _cls1ThreatenButton this$1;

                    
                    {
                        this$1 = _cls1ThreatenButton.this;
                        super($anonymous0);
                    }
                };
                Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Continue.getActionMap().put("pressed", ContinueAction);
                p.add(Continue);
                p.validate();
                p.repaint();
            }

            private final Chosen val$c;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final Action val$ContinueAction;

            public _cls1ThreatenButton(JFrame jframe, 
                    Action action)
            {
                c = chosen;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                ContinueAction = action;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action ThreatenAction = new _cls1ThreatenButton(f, ContinueAction);
        class _cls1GrindButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                c.beginGrind();
                Project.advanceAction(p, w, c.getNumber() * 14 + 7);
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }

            private final Chosen val$c;
            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final Chosen val$initiative[];

            public _cls1GrindButton(JFrame jframe, 
                    Chosen achosen[])
            {
                c = chosen;
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                initiative = achosen;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action GrindAction = new _cls1GrindButton(f, initiative);
        class _cls1CaressButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                c.beginCaress();
                Project.advanceAction(p, w, c.getNumber() * 14 + 8);
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }

            private final Chosen val$c;
            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final Chosen val$initiative[];

            public _cls1CaressButton(JFrame jframe, 
                    Chosen achosen[])
            {
                c = chosen;
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                initiative = achosen;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action CaressAction = new _cls1CaressButton(f, initiative);
        final String pummelName = "Pummel";
        if(w.tickle().booleanValue())
            pummelName = "Tickle";
        class _cls1PummelButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                c.beginPummel();
                Project.advanceAction(p, w, c.getNumber() * 14 + 9);
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }

            private final Chosen val$c;
            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final Chosen val$initiative[];

            public _cls1PummelButton(JFrame jframe, 
                    Chosen achosen[])
            {
                c = chosen;
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                initiative = achosen;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action PummelAction = new _cls1PummelButton(f, initiative);
        class _cls1HumiliateButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                c.beginHumiliate();
                Project.advanceAction(p, w, c.getNumber() * 14 + 10);
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }

            private final Chosen val$c;
            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final Chosen val$initiative[];

            public _cls1HumiliateButton(JFrame jframe, 
                    Chosen achosen[])
            {
                c = chosen;
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                initiative = achosen;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action HumiliateAction = new _cls1HumiliateButton(f, initiative);
        class _cls1InseminateButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                c.beginInseminate();
                Project.advanceAction(p, w, c.getNumber() * 14 + 11);
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }

            private final Chosen val$c;
            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final Chosen val$initiative[];

            public _cls1InseminateButton(JFrame jframe, 
                    Chosen achosen[])
            {
                c = chosen;
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                initiative = achosen;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action InseminateAction = new _cls1InseminateButton(f, initiative);
        class _cls1ForceOrgasmButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                c.beginOrgasm();
                Project.advanceAction(p, w, c.getNumber() * 14 + 12);
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }

            private final Chosen val$c;
            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final Chosen val$initiative[];

            public _cls1ForceOrgasmButton(JFrame jframe, 
                    Chosen achosen[])
            {
                c = chosen;
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                initiative = achosen;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action ForceOrgasmAction = new _cls1ForceOrgasmButton(f, initiative);
        final String SodomizeName = "Sodomize";
        if(w.tickle().booleanValue())
            SodomizeName = "Force Laughter";
        else
        if(c.getGender().equals("male"))
            SodomizeName = "Torture";
        class _cls1SodomizeButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                c.beginSodomize();
                Project.advanceAction(p, w, c.getNumber() * 14 + 13);
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }

            private final Chosen val$c;
            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final Chosen val$initiative[];

            public _cls1SodomizeButton(JFrame jframe, 
                    Chosen achosen[])
            {
                c = chosen;
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                initiative = achosen;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action SodomizeAction = new _cls1SodomizeButton(f, initiative);
        class _cls1BroadcastButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                c.beginBroadcast();
                Project.advanceAction(p, w, c.getNumber() * 14 + 14);
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }

            private final Chosen val$c;
            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final Chosen val$initiative[];

            public _cls1BroadcastButton(JFrame jframe, 
                    Chosen achosen[])
            {
                c = chosen;
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                initiative = achosen;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action BroadcastAction = new _cls1BroadcastButton(f, initiative);
        class _cls1TemptButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                if(w.finalBattle.booleanValue())
                    w.finalTempt(t, c);
                else
                    c.beginTempt();
                Project.advanceAction(p, w, c.getNumber() + 44);
                if(w.finalBattle.booleanValue())
                {
                    p.removeAll();
                    JButton Continue = new JButton(ContinueAction) {

                        public Point getToolTipLocation(MouseEvent e)
                        {
                            return new Point(0, -30);
                        }

                        final _cls1TemptButton this$1;

                    
                    {
                        this$1 = _cls1TemptButton.this;
                        super($anonymous0);
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

            private final WorldState val$w;
            private final JTextPane val$t;
            private final Chosen val$c;
            private final JPanel val$p;
            private final Action val$ContinueAction;
            private final JFrame val$f;
            private final Chosen val$initiative[];

            public _cls1TemptButton(JFrame jframe, Chosen achosen[])
            {
                w = worldstate;
                t = jtextpane;
                c = chosen;
                p = jpanel;
                ContinueAction = action;
                f = jframe;
                initiative = achosen;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action TemptAction = new _cls1TemptButton(f, initiative);
        class _cls1AppealButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                w.finalAppeal(t, w, c);
                Project.advanceAction(p, w, c.getNumber() * 14 + 14);
                p.removeAll();
                JButton Continue = new JButton(ContinueAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -30);
                    }

                    final _cls1AppealButton this$1;

                    
                    {
                        this$1 = _cls1AppealButton.this;
                        super($anonymous0);
                    }
                };
                Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Continue.getActionMap().put("pressed", ContinueAction);
                p.add(Continue);
                p.validate();
                p.repaint();
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final Chosen val$c;
            private final JPanel val$p;
            private final Action val$ContinueAction;

            public _cls1AppealButton(JPanel jpanel, Action action)
            {
                w = worldstate;
                t = jtextpane;
                c = chosen;
                p = jpanel;
                ContinueAction = action;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action AppealAction = new _cls1AppealButton(p, ContinueAction);
        final String ForsakenDefilerName = "";
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
            else
            if(w.usedForsaken.defilerType == 7)
                ForsakenDefilerName = "Traumatize";
        class _cls1ForsakenDefilerButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                Project.advanceAction(p, w, c.getNumber() * 14 + 2);
                if(w.finalBattle.booleanValue() && w.usedForsaken.defilerType == 5)
                {
                    w.usedForsaken.ForsakenFinalTempt(t, w, c);
                    p.removeAll();
                    JButton Continue = new JButton(ContinueAction) {

                        public Point getToolTipLocation(MouseEvent e)
                        {
                            return new Point(0, -30);
                        }

                        final _cls1ForsakenDefilerButton this$1;

                    
                    {
                        this$1 = _cls1ForsakenDefilerButton.this;
                        super($anonymous0);
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

                        final _cls1ForsakenDefilerButton this$1;

                    
                    {
                        this$1 = _cls1ForsakenDefilerButton.this;
                        super($anonymous0);
                    }
                    };
                    Continue.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                    Continue.getActionMap().put("pressed", ContinueAction);
                    p.add(Continue);
                    p.validate();
                    p.repaint();
                } else
                {
                    w.usedForsaken.defiling = Boolean.valueOf(true);
                    Project.EnemyTurn(t, p, f, w, initiative, 0);
                }
            }

            private final JPanel val$p;
            private final WorldState val$w;
            private final Chosen val$c;
            private final JTextPane val$t;
            private final Action val$ContinueAction;
            private final JFrame val$f;
            private final Chosen val$initiative[];

            public _cls1ForsakenDefilerButton(JFrame jframe, Chosen achosen[])
            {
                p = jpanel;
                w = worldstate;
                c = chosen;
                t = jtextpane;
                ContinueAction = action;
                f = jframe;
                initiative = achosen;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action ForsakenDefilerAction = new _cls1ForsakenDefilerButton(f, initiative);
        int finalInseminated = inseminated;
        int finalOrgasming = orgasming;
        int finalSodomized = sodomized;
        int finalBroadcasted = broadcasted;
        class _cls1SurroundButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                w.setSurroundTarget(c);
                if(w.getTechs()[31].isOwned().booleanValue())
                {
                    p.removeAll();
                    int defilers = 0;
                    Boolean plusPossible = Boolean.valueOf(false);
                    Boolean orgyPossible = Boolean.valueOf(false);
                    String PAINname = "PAIN";
                    String INJUname = "INJU";
                    if(w.tickle().booleanValue())
                    {
                        PAINname = "TICK";
                        INJUname = "ANTI";
                    }
                    if(!c.getGrind().booleanValue())
                    {
                        JButton Grind = new JButton(GrindAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }

                            final _cls1SurroundButton this$1;

                    
                    {
                        this$1 = _cls1SurroundButton.this;
                        super($anonymous0);
                    }
                        };
                        Grind.setToolTipText((new StringBuilder("<html><center>Inflicts HATE along with<br>FEAR, DISG, ")).append(PAINname).append(", and SHAM<br>Can cause tier-1 Morality or Dignity Break</center></html>").toString());
                        p.add(Grind);
                        Grind.getInputMap(2).put(KeyStroke.getKeyStroke("1"), "pressed");
                        if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 7)
                            Grind.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Grind.getActionMap().put("pressed", GrindAction);
                    }
                    if(!c.getCaress().booleanValue())
                    {
                        JButton Caress = new JButton(CaressAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }

                            final _cls1SurroundButton this$1;

                    
                    {
                        this$1 = _cls1SurroundButton.this;
                        super($anonymous0);
                    }
                        };
                        Caress.setToolTipText((new StringBuilder("<html><center>Inflicts PLEA along with<br>DISG, ")).append(PAINname).append(", SHAM, and FEAR<br>Can cause tier-1 Innocence or Confidence Break</center></html>").toString());
                        p.add(Caress);
                        Caress.getInputMap(2).put(KeyStroke.getKeyStroke("2"), "pressed");
                        if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 8)
                            Caress.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Caress.getActionMap().put("pressed", CaressAction);
                    }
                    if(!c.getPummel().booleanValue())
                    {
                        JButton Pummel = new JButton(PummelAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }

                            final _cls1SurroundButton this$1;

                    
                    {
                        this$1 = _cls1SurroundButton.this;
                        super($anonymous0);
                    }
                        };
                        Pummel.setToolTipText((new StringBuilder("<html><center>Inflicts ")).append(INJUname).append(" along with<br>").append(PAINname).append(", SHAM, FEAR, and DISG<br>Can cause tier-1 Morality or Confidence Break</center></html>").toString());
                        p.add(Pummel);
                        Pummel.getInputMap(2).put(KeyStroke.getKeyStroke("3"), "pressed");
                        if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 9)
                            Pummel.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Pummel.getActionMap().put("pressed", PummelAction);
                    }
                    if(!c.getHumiliate().booleanValue())
                    {
                        JButton Humiliate = new JButton(HumiliateAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }

                            final _cls1SurroundButton this$1;

                    
                    {
                        this$1 = _cls1SurroundButton.this;
                        super($anonymous0);
                    }
                        };
                        Humiliate.setToolTipText((new StringBuilder("<html><center>Inflicts EXPO along with<br>SHAM, FEAR, DISG, and ")).append(PAINname).append("<br>Can cause tier-1 Innocence or Dignity Break</center></html>").toString());
                        p.add(Humiliate);
                        Humiliate.getInputMap(2).put(KeyStroke.getKeyStroke("4"), "pressed");
                        if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 10)
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

                            final _cls1SurroundButton this$1;

                    
                    {
                        this$1 = _cls1SurroundButton.this;
                        super($anonymous0);
                    }
                        };
                        if(finalInseminated > 0)
                        {
                            Inseminate.setBackground(PURPLISH);
                            if(finalInseminated == 1)
                            {
                                Inseminate.setText("Inseminate+");
                                plusPossible = Boolean.valueOf(true);
                            } else
                            {
                                Inseminate.setText("Orgy");
                                orgyPossible = Boolean.valueOf(true);
                            }
                        } else
                        {
                            Inseminate.setBackground(YELLOWISH);
                        }
                        if((c.temptReq < 0x186a0L || c.negotiations > 0) && finalInseminated != 2)
                            Inseminate.setBackground(REDDISH);
                        Inseminate.setToolTipText((new StringBuilder("<html><center>Inflicts HATE and PLEA along with<br>FEAR, DISG, ")).append(PAINname).append(", and SHAM<br>Causes tier-2 Morality Break</center></html>").toString());
                        if(finalInseminated == 1)
                            Inseminate.setToolTipText((new StringBuilder("<html><center>Inflicts HATE, PLEA and ")).append(INJUname).append(" along with<br>FEAR, DISG, ").append(PAINname).append(", and SHAM<br>Causes tier-2 Morality Break</center></html>").toString());
                        else
                        if(finalInseminated == 2)
                            Inseminate.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                        p.add(Inseminate);
                        Inseminate.getInputMap(2).put(KeyStroke.getKeyStroke("5"), "pressed");
                        if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 11)
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

                            final _cls1SurroundButton this$1;

                    
                    {
                        this$1 = _cls1SurroundButton.this;
                        super($anonymous0);
                    }
                        };
                        if(finalOrgasming > 0)
                        {
                            ForceOrgasm.setBackground(PURPLISH);
                            if(finalOrgasming == 1)
                            {
                                ForceOrgasm.setText("Force Orgasm+");
                                plusPossible = Boolean.valueOf(true);
                            } else
                            {
                                ForceOrgasm.setText("Orgy");
                                orgyPossible = Boolean.valueOf(true);
                            }
                        } else
                        {
                            ForceOrgasm.setBackground(YELLOWISH);
                        }
                        if(c.dissociationReq < 10 && finalOrgasming != 2)
                            ForceOrgasm.setBackground(REDDISH);
                        ForceOrgasm.setToolTipText((new StringBuilder("<html><center>Inflicts PLEA and ")).append(INJUname).append(" along with<br>DISG, ").append(PAINname).append(", SHAM, and FEAR<br>Causes tier-2 Innocence Break</center></html>").toString());
                        if(finalOrgasming == 1)
                            ForceOrgasm.setToolTipText((new StringBuilder("<html><center>Inflicts PLEA,")).append(INJUname).append(", and EXPO along with<br>DISG, ").append(PAINname).append(", SHAM, and FEAR<br>Causes tier-2 Innocence Break</center></html>").toString());
                        else
                        if(finalOrgasming == 2)
                            ForceOrgasm.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                        p.add(ForceOrgasm);
                        ForceOrgasm.getInputMap(2).put(KeyStroke.getKeyStroke("6"), "pressed");
                        if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 12)
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

                            final _cls1SurroundButton this$1;

                    
                    {
                        this$1 = _cls1SurroundButton.this;
                        super($anonymous0);
                    }
                        };
                        if(finalSodomized > 0)
                        {
                            Sodomize.setBackground(PURPLISH);
                            if(finalSodomized == 1)
                            {
                                if(w.tickle().booleanValue())
                                    Sodomize.setText("Force Laughter+");
                                else
                                if(c.getGender().equals("male"))
                                    Sodomize.setText("Torture+");
                                else
                                    Sodomize.setText("Sodomize+");
                                plusPossible = Boolean.valueOf(true);
                            } else
                            {
                                Sodomize.setText("Orgy");
                                orgyPossible = Boolean.valueOf(true);
                            }
                        } else
                        {
                            Sodomize.setBackground(YELLOWISH);
                        }
                        if(c.temptReq < 0x186a0L && finalSodomized != 2)
                            Sodomize.setBackground(REDDISH);
                        Sodomize.setToolTipText((new StringBuilder("<html><center>Inflicts ")).append(INJUname).append(" and EXPO along with<br>").append(PAINname).append(", SHAM, FEAR, and DISG<br>Causes tier-2 Confidence Break</center></html>").toString());
                        if(finalSodomized == 1)
                            Sodomize.setToolTipText((new StringBuilder("<html><center>Inflicts ")).append(INJUname).append(", EXPO, and HATE along with<br>").append(PAINname).append(", SHAM, FEAR, and DISG<br>Causes tier-2 Confidence Break</center></html>").toString());
                        else
                        if(finalSodomized == 2)
                            Sodomize.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                        p.add(Sodomize);
                        Sodomize.getInputMap(2).put(KeyStroke.getKeyStroke("7"), "pressed");
                        if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 13)
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

                            final _cls1SurroundButton this$1;

                    
                    {
                        this$1 = _cls1SurroundButton.this;
                        super($anonymous0);
                    }
                        };
                        if(finalBroadcasted > 0)
                        {
                            Broadcast.setBackground(PURPLISH);
                            if(finalBroadcasted == 1)
                            {
                                Broadcast.setText("Broadcast+");
                                plusPossible = Boolean.valueOf(true);
                            } else
                            {
                                Broadcast.setText("Orgy");
                                orgyPossible = Boolean.valueOf(true);
                            }
                        } else
                        {
                            Broadcast.setBackground(YELLOWISH);
                        }
                        if((c.dissociationReq < 10 || c.negotiations > 0) && finalBroadcasted != 2)
                            Broadcast.setBackground(REDDISH);
                        Broadcast.setToolTipText((new StringBuilder("<html><center>Inflicts EXPO and HATE along with<br>SHAM, FEAR, DISG, and ")).append(PAINname).append("<br>Causes tier-2 Dignity Break</center></html>").toString());
                        if(finalBroadcasted == 1)
                            Broadcast.setToolTipText((new StringBuilder("<html><center>Inflicts EXPO, HATE, and PLEA along with<br>SHAM, FEAR, DISG, and ")).append(PAINname).append("<br>Causes tier-2 Dignity Break</center></html>").toString());
                        else
                        if(finalBroadcasted == 2)
                            Broadcast.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                        p.add(Broadcast);
                        Broadcast.getInputMap(2).put(KeyStroke.getKeyStroke("8"), "pressed");
                        if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 14)
                            Broadcast.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Broadcast.getActionMap().put("pressed", BroadcastAction);
                    }
                    long currentTemptReq = c.temptReq;
                    if(w.finalBattle.booleanValue())
                        currentTemptReq *= 10L;
                    if(c.getCurrentPLEA() >= currentTemptReq && c.vVirg.booleanValue() && c.aVirg.booleanValue() && !c.cVirg.booleanValue() && !c.modest.booleanValue() && !c.ruthless && !c.usingSlaughter.booleanValue() && !c.usingDetonate.booleanValue() && (c.temptReq < 0x186a0L || !w.finalBattle.booleanValue()))
                    {
                        JButton Tempt = new JButton(TemptAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }

                            final _cls1SurroundButton this$1;

                    
                    {
                        this$1 = _cls1SurroundButton.this;
                        super($anonymous0);
                    }
                        };
                        Tempt.setToolTipText("<html><center>Inflicts extremely high PLEA and EXPO<br>but decreases other circumstances to zero and does not inflict trauma<br>Causes and intensifies Morality/Confidence Distortion</center></html>");
                        Tempt.setBackground(PURPLISH);
                        p.add(Tempt);
                        Tempt.getInputMap(2).put(KeyStroke.getKeyStroke("9"), "pressed");
                        if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 14)
                            Tempt.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Tempt.getActionMap().put("pressed", TemptAction);
                    }
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhat should the Thralls do after surrounding ").append(c.getMainName()).append("?").toString());
                    if(defilers > 1)
                        w.append(t, (new StringBuilder("  ")).append(defilers).append(" defiler actions possible.").toString());
                    else
                    if(defilers == 1)
                        w.append(t, "  1 defiler action possible.");
                    int difference = 0;
                    if(orgyPossible.booleanValue())
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

                        w.append(t, (new StringBuilder("  Orgy with ")).append(firstName).append(" and ").append(secondName).toString());
                        if(duration > opening)
                        {
                            difference = duration - opening;
                            w.append(t, " will cause them");
                        } else
                        if(opening > duration)
                        {
                            difference = opening - duration;
                            w.append(t, (new StringBuilder(" will allow ")).append(c.getMainName()).toString());
                        }
                        if(difference > 1)
                            w.append(t, (new StringBuilder(" to escape ")).append(difference).append(" turns early.").toString());
                        else
                        if(difference == 1)
                            w.append(t, " to escape 1 turn early.");
                        else
                            w.append(t, " does not allow any of them to escape early.");
                    } else
                    if(plusPossible.booleanValue())
                    {
                        int opening = c.getFEAROpening(w) + c.getDISGOpening() + c.getPAINOpening() + c.getSHAMOpening(w) + 1;
                        for(int i = 0; i < 3; i++)
                            if(w.getCombatants()[i] != null && w.getCombatants()[i] != c)
                            {
                                String defilementType = "";
                                if(w.getCombatants()[i].isInseminated().booleanValue() && c.getHATELevel() >= 3)
                                    defilementType = "Inseminate";
                                else
                                if(w.getCombatants()[i].isOrgasming().booleanValue() && c.getPLEALevel() >= 3)
                                    defilementType = "Force Orgasm";
                                else
                                if(w.getCombatants()[i].isSodomized().booleanValue() && c.getINJULevel() >= 3)
                                {
                                    if(w.tickle().booleanValue())
                                        defilementType = "Force Laughter";
                                    else
                                    if(c.getGender().equals("male"))
                                        defilementType = "Torture";
                                    else
                                        defilementType = "Sodomize";
                                } else
                                if(w.getCombatants()[i].isBroadcasted().booleanValue() && c.getEXPOLevel() >= 3)
                                    defilementType = "Broadcast";
                                if(defilementType.length() > 0)
                                {
                                    w.append(t, (new StringBuilder("  ")).append(defilementType).append(" with ").append(w.getCombatants()[i].getMainName()).toString());
                                    if(opening > w.getCombatants()[i].getSurroundDuration())
                                    {
                                        w.append(t, (new StringBuilder(" will allow ")).append(c.getMainName()).toString());
                                        difference = opening - w.getCombatants()[i].getSurroundDuration();
                                    } else
                                    if(w.getCombatants()[i].getSurroundDuration() > opening)
                                    {
                                        w.append(t, (new StringBuilder(" will allow ")).append(w.getCombatants()[i].getMainName()).toString());
                                        difference = w.getCombatants()[i].getSurroundDuration() - opening;
                                    }
                                    if(difference > 1)
                                        w.append(t, (new StringBuilder(" to escape ")).append(difference).append(" turns early.").toString());
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

                        public void actionPerformed(ActionEvent e)
                        {
                            w.setSurroundTarget(null);
                            Project.PickAction(t, p, f, w, c, initiative);
                        }

                        final _cls1SurroundButton this$1;
                        private final WorldState val$w;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final Chosen val$c;
                        private final Chosen val$initiative[];

                    
                    {
                        this$1 = _cls1SurroundButton.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        c = chosen;
                        initiative = achosen;
                        super();
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

            private final WorldState val$w;
            private final Chosen val$c;
            private final JPanel val$p;
            private final Action val$GrindAction;
            private final Action val$CaressAction;
            private final Action val$PummelAction;
            private final Action val$HumiliateAction;
            private final Action val$InseminateAction;
            private final int val$finalInseminated;
            private final Color val$PURPLISH;
            private final Color val$YELLOWISH;
            private final Color val$REDDISH;
            private final Action val$ForceOrgasmAction;
            private final int val$finalOrgasming;
            private final Action val$SodomizeAction;
            private final int val$finalSodomized;
            private final Action val$BroadcastAction;
            private final int val$finalBroadcasted;
            private final Action val$TemptAction;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final Chosen val$initiative[];

            public _cls1SurroundButton(JFrame jframe, Chosen achosen[])
            {
                w = worldstate;
                c = chosen;
                p = jpanel;
                GrindAction = action;
                CaressAction = action1;
                PummelAction = action2;
                HumiliateAction = action3;
                InseminateAction = action4;
                finalInseminated = i;
                PURPLISH = color;
                YELLOWISH = color1;
                REDDISH = color2;
                ForceOrgasmAction = action5;
                finalOrgasming = j;
                SodomizeAction = action6;
                finalSodomized = k;
                BroadcastAction = action7;
                finalBroadcasted = l;
                TemptAction = action8;
                t = jtextpane;
                f = jframe;
                initiative = achosen;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action SurroundAction = new _cls1SurroundButton(f, initiative);
        class _cls1CaptureButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                Boolean directlyAdvance = Boolean.valueOf(true);
                w.setCaptureTarget(c);
                if(w.upgradedCommander().booleanValue())
                {
                    if(w.usedForsaken != null)
                        w.usedForsaken.defilerStage = 0;
                } else
                {
                    w.setSurroundTarget(c);
                    if(w.getTechs()[31].isOwned().booleanValue())
                        directlyAdvance = Boolean.valueOf(false);
                }
                if(directlyAdvance.booleanValue())
                {
                    Project.advanceAction(p, w, c.getNumber() * 14 + 2);
                    Project.EnemyTurn(t, p, f, w, initiative, 0);
                } else
                {
                    p.removeAll();
                    String PAINname = "PAIN";
                    String INJUname = "INJU";
                    if(w.tickle().booleanValue())
                    {
                        PAINname = "TICK";
                        INJUname = "ANTI";
                    }
                    int defilers = 0;
                    Boolean plusPossible = Boolean.valueOf(false);
                    Boolean orgyPossible = Boolean.valueOf(false);
                    if(!c.getGrind().booleanValue())
                    {
                        JButton Grind = new JButton(GrindAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }

                            final _cls1CaptureButton this$1;

                    
                    {
                        this$1 = _cls1CaptureButton.this;
                        super($anonymous0);
                    }
                        };
                        Grind.setToolTipText((new StringBuilder("<html><center>Inflicts HATE along with<br>FEAR, DISG, ")).append(PAINname).append(", and SHAM<br>Can cause tier-1 Morality or Dignity Break</center></html>").toString());
                        p.add(Grind);
                        Grind.getInputMap(2).put(KeyStroke.getKeyStroke("1"), "pressed");
                        if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 7)
                            Grind.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Grind.getActionMap().put("pressed", GrindAction);
                    }
                    if(!c.getCaress().booleanValue())
                    {
                        JButton Caress = new JButton(CaressAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }

                            final _cls1CaptureButton this$1;

                    
                    {
                        this$1 = _cls1CaptureButton.this;
                        super($anonymous0);
                    }
                        };
                        Caress.setToolTipText((new StringBuilder("<html><center>Inflicts PLEA along with<br>DISG, ")).append(PAINname).append(", SHAM, and FEAR<br>Can cause tier-1 Innocence or Confidence Break</center></html>").toString());
                        p.add(Caress);
                        Caress.getInputMap(2).put(KeyStroke.getKeyStroke("2"), "pressed");
                        if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 8)
                            Caress.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Caress.getActionMap().put("pressed", CaressAction);
                    }
                    if(!c.getPummel().booleanValue())
                    {
                        JButton Pummel = new JButton(PummelAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }

                            final _cls1CaptureButton this$1;

                    
                    {
                        this$1 = _cls1CaptureButton.this;
                        super($anonymous0);
                    }
                        };
                        Pummel.setToolTipText((new StringBuilder("<html><center>Inflicts ")).append(INJUname).append(" along with<br>").append(PAINname).append(", SHAM, FEAR, and DISG<br>Can cause tier-1 Morality or Confidence Break</center></html>").toString());
                        p.add(Pummel);
                        Pummel.getInputMap(2).put(KeyStroke.getKeyStroke("3"), "pressed");
                        if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 9)
                            Pummel.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Pummel.getActionMap().put("pressed", PummelAction);
                    }
                    if(!c.getHumiliate().booleanValue())
                    {
                        JButton Humiliate = new JButton(HumiliateAction) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -60);
                            }

                            final _cls1CaptureButton this$1;

                    
                    {
                        this$1 = _cls1CaptureButton.this;
                        super($anonymous0);
                    }
                        };
                        Humiliate.setToolTipText((new StringBuilder("<html><center>Inflicts EXPO along with<br>SHAM, FEAR, DISG, and ")).append(PAINname).append("<br>Can cause tier-1 Innocence or Dignity Break</center></html>").toString());
                        p.add(Humiliate);
                        Humiliate.getInputMap(2).put(KeyStroke.getKeyStroke("4"), "pressed");
                        if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 10)
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

                            final _cls1CaptureButton this$1;

                    
                    {
                        this$1 = _cls1CaptureButton.this;
                        super($anonymous0);
                    }
                        };
                        if(finalInseminated > 0)
                        {
                            Inseminate.setBackground(PURPLISH);
                            if(finalInseminated == 1)
                            {
                                Inseminate.setText("Inseminate+");
                                plusPossible = Boolean.valueOf(true);
                            } else
                            {
                                Inseminate.setText("Orgy");
                                orgyPossible = Boolean.valueOf(true);
                            }
                        } else
                        {
                            Inseminate.setBackground(YELLOWISH);
                        }
                        if((c.temptReq < 0x186a0L || c.negotiations > 0) && finalInseminated != 2)
                            Inseminate.setBackground(REDDISH);
                        Inseminate.setToolTipText((new StringBuilder("<html><center>Inflicts HATE and PLEA along with<br>FEAR, DISG, ")).append(PAINname).append(", and SHAM<br>Causes tier-2 Morality Break</center></html>").toString());
                        if(finalInseminated == 1)
                            Inseminate.setToolTipText((new StringBuilder("<html><center>Inflicts HATE, PLEA and ")).append(INJUname).append(" along with<br>FEAR, DISG, ").append(PAINname).append(", and SHAM<br>Causes tier-2 Morality Break</center></html>").toString());
                        else
                        if(finalInseminated == 2)
                            Inseminate.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                        p.add(Inseminate);
                        Inseminate.getInputMap(2).put(KeyStroke.getKeyStroke("5"), "pressed");
                        if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 11)
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

                            final _cls1CaptureButton this$1;

                    
                    {
                        this$1 = _cls1CaptureButton.this;
                        super($anonymous0);
                    }
                        };
                        if(finalOrgasming > 0)
                        {
                            ForceOrgasm.setBackground(PURPLISH);
                            if(finalOrgasming == 1)
                            {
                                ForceOrgasm.setText("Force Orgasm+");
                                plusPossible = Boolean.valueOf(true);
                            } else
                            {
                                ForceOrgasm.setText("Orgy");
                                orgyPossible = Boolean.valueOf(true);
                            }
                        } else
                        {
                            ForceOrgasm.setBackground(YELLOWISH);
                        }
                        if(c.dissociationReq < 10 && finalOrgasming != 2)
                            ForceOrgasm.setBackground(REDDISH);
                        ForceOrgasm.setToolTipText((new StringBuilder("<html><center>Inflicts PLEA and ")).append(INJUname).append(" along with<br>DISG, ").append(PAINname).append(", SHAM, and FEAR<br>Causes tier-2 Innocence Break</center></html>").toString());
                        if(finalOrgasming == 1)
                            ForceOrgasm.setToolTipText((new StringBuilder("<html><center>Inflicts PLEA,")).append(INJUname).append(", and EXPO along with<br>DISG, ").append(PAINname).append(", SHAM, and FEAR<br>Causes tier-2 Innocence Break</center></html>").toString());
                        else
                        if(finalOrgasming == 2)
                            ForceOrgasm.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                        p.add(ForceOrgasm);
                        ForceOrgasm.getInputMap(2).put(KeyStroke.getKeyStroke("6"), "pressed");
                        if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 12)
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

                            final _cls1CaptureButton this$1;

                    
                    {
                        this$1 = _cls1CaptureButton.this;
                        super($anonymous0);
                    }
                        };
                        if(finalSodomized > 0)
                        {
                            Sodomize.setBackground(PURPLISH);
                            if(finalSodomized == 1)
                            {
                                if(w.tickle().booleanValue())
                                    Sodomize.setText("Force Laughter+");
                                else
                                if(c.getGender().equals("male"))
                                    Sodomize.setText("Torture+");
                                else
                                    Sodomize.setText("Sodomize+");
                                plusPossible = Boolean.valueOf(true);
                            } else
                            {
                                Sodomize.setText("Orgy");
                                orgyPossible = Boolean.valueOf(true);
                            }
                        } else
                        {
                            Sodomize.setBackground(YELLOWISH);
                        }
                        if(c.temptReq < 0x186a0L && finalSodomized != 2)
                            Sodomize.setBackground(REDDISH);
                        Sodomize.setToolTipText((new StringBuilder("<html><center>Inflicts ")).append(INJUname).append(" and EXPO along with<br>").append(PAINname).append(", SHAM, FEAR, and DISG<br>Causes tier-2 Confidence Break</center></html>").toString());
                        if(finalSodomized == 1)
                            Sodomize.setToolTipText((new StringBuilder("<html><center>Inflicts ")).append(INJUname).append(", EXPO, and HATE along with<br>").append(PAINname).append(", SHAM, FEAR, and DISG<br>Causes tier-2 Confidence Break</center></html>").toString());
                        else
                        if(finalSodomized == 2)
                            Sodomize.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                        p.add(Sodomize);
                        Sodomize.getInputMap(2).put(KeyStroke.getKeyStroke("7"), "pressed");
                        if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 13)
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

                            final _cls1CaptureButton this$1;

                    
                    {
                        this$1 = _cls1CaptureButton.this;
                        super($anonymous0);
                    }
                        };
                        if(finalBroadcasted > 0)
                        {
                            Broadcast.setBackground(PURPLISH);
                            if(finalBroadcasted == 1)
                            {
                                Broadcast.setText("Broadcast+");
                                plusPossible = Boolean.valueOf(true);
                            } else
                            {
                                Broadcast.setText("Orgy");
                                orgyPossible = Boolean.valueOf(true);
                            }
                        } else
                        {
                            Broadcast.setBackground(YELLOWISH);
                        }
                        if((c.dissociationReq < 10 || c.negotiations > 0) && finalBroadcasted != 2)
                            Broadcast.setBackground(REDDISH);
                        Broadcast.setToolTipText((new StringBuilder("<html><center>Inflicts EXPO and HATE along with<br>SHAM, FEAR, DISG, and ")).append(PAINname).append("<br>Causes tier-2 Dignity Break</center></html>").toString());
                        if(finalBroadcasted == 1)
                            Broadcast.setToolTipText((new StringBuilder("<html><center>Inflicts EXPO, HATE, and PLEA along with<br>SHAM, FEAR, DISG, and ")).append(PAINname).append("<br>Causes tier-2 Dignity Break</center></html>").toString());
                        else
                        if(finalBroadcasted == 2)
                            Broadcast.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                        p.add(Broadcast);
                        Broadcast.getInputMap(2).put(KeyStroke.getKeyStroke("8"), "pressed");
                        if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 14)
                            Broadcast.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                        Broadcast.getActionMap().put("pressed", BroadcastAction);
                    }
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhat should the Thralls do after surrounding ").append(c.getMainName()).append("?").toString());
                    if(defilers > 1)
                        w.append(t, (new StringBuilder("  ")).append(defilers).append(" defiler actions possible.").toString());
                    else
                    if(defilers == 1)
                        w.append(t, "  1 defiler action possible.");
                    int difference = 0;
                    if(orgyPossible.booleanValue())
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

                        w.append(t, (new StringBuilder("  Orgy with ")).append(firstName).append(" and ").append(secondName).toString());
                        if(duration > opening)
                        {
                            difference = duration - opening;
                            w.append(t, " will cause them");
                        } else
                        if(opening > duration)
                        {
                            difference = opening - duration;
                            w.append(t, (new StringBuilder(" will allow ")).append(c.getMainName()).toString());
                        }
                        if(difference > 1)
                            w.append(t, (new StringBuilder(" to escape ")).append(difference).append(" turns early.").toString());
                        else
                        if(difference == 1)
                            w.append(t, " to escape 1 turn early.");
                        else
                            w.append(t, " does not allow any of them to escape early.");
                    } else
                    if(plusPossible.booleanValue())
                    {
                        int opening = w.getCaptureDuration() + 1;
                        for(int i = 0; i < 3; i++)
                            if(w.getCombatants()[i] != null && w.getCombatants()[i] != c)
                            {
                                String defilementType = "";
                                if(w.getCombatants()[i].isInseminated().booleanValue() && c.getHATELevel() >= 3)
                                    defilementType = "Inseminate";
                                else
                                if(w.getCombatants()[i].isOrgasming().booleanValue() && c.getPLEALevel() >= 3)
                                    defilementType = "Force Orgasm";
                                else
                                if(w.getCombatants()[i].isSodomized().booleanValue() && c.getINJULevel() >= 3)
                                {
                                    if(w.tickle().booleanValue())
                                        defilementType = "Force Laughter";
                                    else
                                    if(c.getGender().equals("male"))
                                        defilementType = "Torture";
                                    else
                                        defilementType = "Sodomize";
                                } else
                                if(w.getCombatants()[i].isBroadcasted().booleanValue() && c.getEXPOLevel() >= 3)
                                    defilementType = "Broadcast";
                                if(defilementType.length() > 0)
                                {
                                    w.append(t, (new StringBuilder("  ")).append(defilementType).append(" with ").append(w.getCombatants()[i].getMainName()).toString());
                                    if(opening > w.getCombatants()[i].getSurroundDuration())
                                    {
                                        w.append(t, (new StringBuilder(" will allow ")).append(c.getMainName()).toString());
                                        difference = opening - w.getCombatants()[i].getSurroundDuration();
                                    } else
                                    if(w.getCombatants()[i].getSurroundDuration() > opening)
                                    {
                                        w.append(t, (new StringBuilder(" will allow ")).append(w.getCombatants()[i].getMainName()).toString());
                                        difference = w.getCombatants()[i].getSurroundDuration() - opening;
                                    }
                                    if(difference > 1)
                                        w.append(t, (new StringBuilder(" to escape ")).append(difference).append(" turns early.").toString());
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

                        public void actionPerformed(ActionEvent e)
                        {
                            w.setSurroundTarget(null);
                            Project.PickAction(t, p, f, w, c, initiative);
                        }

                        final _cls1CaptureButton this$1;
                        private final WorldState val$w;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final Chosen val$c;
                        private final Chosen val$initiative[];

                    
                    {
                        this$1 = _cls1CaptureButton.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        c = chosen;
                        initiative = achosen;
                        super();
                    }
                    });
                    p.add(Back);
                    p.validate();
                    p.repaint();
                }
            }

            private final WorldState val$w;
            private final Chosen val$c;
            private final JPanel val$p;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final Chosen val$initiative[];
            private final Action val$GrindAction;
            private final Action val$CaressAction;
            private final Action val$PummelAction;
            private final Action val$HumiliateAction;
            private final Action val$InseminateAction;
            private final int val$finalInseminated;
            private final Color val$PURPLISH;
            private final Color val$YELLOWISH;
            private final Color val$REDDISH;
            private final Action val$ForceOrgasmAction;
            private final int val$finalOrgasming;
            private final Action val$SodomizeAction;
            private final int val$finalSodomized;
            private final Action val$BroadcastAction;
            private final int val$finalBroadcasted;

            public _cls1CaptureButton(Action action7, int l)
            {
                w = worldstate;
                c = chosen;
                p = jpanel;
                t = jtextpane;
                f = jframe;
                initiative = achosen;
                GrindAction = action;
                CaressAction = action1;
                PummelAction = action2;
                HumiliateAction = action3;
                InseminateAction = action4;
                finalInseminated = i;
                PURPLISH = color;
                YELLOWISH = color1;
                REDDISH = color2;
                ForceOrgasmAction = action5;
                finalOrgasming = j;
                SodomizeAction = action6;
                finalSodomized = k;
                BroadcastAction = action7;
                finalBroadcasted = l;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action CaptureAction = new _cls1CaptureButton(BroadcastAction, finalBroadcasted);
        class _cls1ExamineButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                w.Examine(t, p, f, c);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final Chosen val$c;

            public _cls1ExamineButton(JFrame jframe, Chosen chosen)
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                c = chosen;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action ExamineAction = new _cls1ExamineButton(f, c);
        p.removeAll();
        w.append(t, "\n\n");
        c.printStatus(t, w);
        if(!c.isCaptured().booleanValue() && !c.isDefiled().booleanValue())
        {
            w.append(t, "\n\nChoose your action.");
            if(w.usedForsaken != null && c.defenseLevel < 9000)
            {
                if(w.usedForsaken.injured == 0 && w.commanderFree().booleanValue())
                    w.append(t, (new StringBuilder("  ")).append(w.usedForsaken.mainName).append(" can ").toString());
                else
                    w.append(t, (new StringBuilder("  Once ")).append(w.usedForsaken.mainName).append(" is ready, ").append(w.usedForsaken.heShe()).append(" will be able to ").toString());
                w.append(t, (new StringBuilder("capture ")).append(c.mainName).append(" for ").append(w.usedForsaken.compatibility(c)).append(" rounds.").toString());
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
        class _cls2PassButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                Project.advanceAction(p, w, 0);
                if(w.getTechs()[30].isOwned().booleanValue() && !w.progressExtermination(0))
                {
                    p.removeAll();
                    w.increaseBarrier(t);
                    class _cls1ContinueButton extends AbstractAction
                    {

                        public void actionPerformed(ActionEvent e)
                        {
                            Project.EnemyTurn(t, p, f, w, initiative, 0);
                        }

                        final _cls2PassButton this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;
                        private final Chosen val$initiative[];

                    public _cls1ContinueButton(WorldState worldstate, 
                            Chosen achosen[])
                    {
                        this$1 = _cls2PassButton.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        initiative = achosen;
                        super(text);
                        putValue("ShortDescription", desc);
                    }
                    }

                    Action ContinueAction = new _cls1ContinueButton(w, initiative);
                    JButton Continue = new JButton(ContinueAction) {

                        public Point getToolTipLocation(MouseEvent e)
                        {
                            return new Point(0, -30);
                        }

                        final _cls2PassButton this$1;

                    
                    {
                        this$1 = _cls2PassButton.this;
                        super($anonymous0);
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

            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final Chosen val$initiative[];

            public _cls2PassButton(JFrame jframe, Chosen achosen[])
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                initiative = achosen;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action PassAction = new _cls2PassButton(f, initiative);
        JButton Pass = new JButton(PassAction) {

            public Point getToolTipLocation(MouseEvent e)
            {
                return new Point(0, -30);
            }

        };
        Pass.setForeground(Color.GRAY);
        Pass.getInputMap(2).put(KeyStroke.getKeyStroke(70, 0), "pressed");
        if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == 0)
            Pass.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
        Pass.getActionMap().put("pressed", PassAction);
        if(w.getTechs()[30].isOwned().booleanValue() && !w.progressExtermination(0))
        {
            Pass.setText("Barrier");
            Pass.setToolTipText("+5% damage for rest of battle");
        }
        if(c.isDefiled().booleanValue())
        {
            w.append(t, "\n\nThe Thralls have been driven into a frenzy ");
            if(c.isInseminated().booleanValue())
                w.append(t, (new StringBuilder("inseminating ")).append(c.getMainName()).toString());
            else
            if(c.isOrgasming().booleanValue())
                w.append(t, (new StringBuilder("forcing ")).append(c.getMainName()).append(" to orgasm").toString());
            else
            if(c.isSodomized().booleanValue())
            {
                if(w.tickle().booleanValue())
                    w.append(t, (new StringBuilder("forcing ")).append(c.getMainName()).append(" to laugh").toString());
                else
                if(c.getGender().equals("male"))
                    w.append(t, (new StringBuilder("torturing ")).append(c.getMainName()).toString());
                else
                    w.append(t, (new StringBuilder("sodomizing ")).append(c.getMainName()).toString());
            } else
            if(c.isBroadcasted().booleanValue())
                w.append(t, (new StringBuilder("broadcasting ")).append(c.getMainName()).append("'s humiliation").toString());
            else
            if(c.tempted.booleanValue())
                w.append(t, (new StringBuilder("giving ")).append(c.mainName).append(" all the pleasure ").append(c.heShe()).append(" wants").toString());
            w.append(t, ".  Any additional orders would simply confuse them right now.");
        } else
        if(c.isSurrounded().booleanValue())
        {
            int defilers = 0;
            String PAINname = "PAIN";
            String INJUname = "INJU";
            if(w.tickle().booleanValue())
            {
                PAINname = "TICK";
                INJUname = "ANTI";
            }
            Boolean plusPossible = Boolean.valueOf(false);
            Boolean orgyPossible = Boolean.valueOf(false);
            if(!c.getGrind().booleanValue())
            {
                JButton Grind = new JButton(GrindAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -60);
                    }

                };
                Grind.setToolTipText((new StringBuilder("<html><center>Inflicts HATE along with<br>FEAR, DISG, ")).append(PAINname).append(", and SHAM<br>Can cause tier-1 Morality or Dignity Break</center></html>").toString());
                p.add(Grind);
                Grind.getInputMap(2).put(KeyStroke.getKeyStroke("1"), "pressed");
                if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 7)
                    Grind.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Grind.getActionMap().put("pressed", GrindAction);
            }
            if(!c.getCaress().booleanValue())
            {
                JButton Caress = new JButton(CaressAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -60);
                    }

                };
                Caress.setToolTipText((new StringBuilder("<html><center>Inflicts PLEA along with<br>DISG, ")).append(PAINname).append(", SHAM, and FEAR<br>Can cause tier-1 Innocence or Confidence Break</center></html>").toString());
                p.add(Caress);
                Caress.getInputMap(2).put(KeyStroke.getKeyStroke("2"), "pressed");
                if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 8)
                    Caress.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Caress.getActionMap().put("pressed", CaressAction);
            }
            if(!c.getPummel().booleanValue())
            {
                JButton Pummel = new JButton(PummelAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -60);
                    }

                };
                Pummel.setToolTipText((new StringBuilder("<html><center>Inflicts ")).append(INJUname).append(" along with<br>").append(PAINname).append(", SHAM, FEAR, and DISG<br>Can cause tier-1 Morality or Confidence Break</center></html>").toString());
                p.add(Pummel);
                Pummel.getInputMap(2).put(KeyStroke.getKeyStroke("3"), "pressed");
                if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 9)
                    Pummel.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Pummel.getActionMap().put("pressed", PummelAction);
            }
            if(!c.getHumiliate().booleanValue())
            {
                JButton Humiliate = new JButton(HumiliateAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -60);
                    }

                };
                Humiliate.setToolTipText((new StringBuilder("<html><center>Inflicts EXPO along with<br>SHAM, FEAR, DISG, and ")).append(PAINname).append("<br>Can cause tier-1 Innocence or Dignity Break</center></html>").toString());
                p.add(Humiliate);
                Humiliate.getInputMap(2).put(KeyStroke.getKeyStroke("4"), "pressed");
                if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 10)
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
                        plusPossible = Boolean.valueOf(true);
                    } else
                    {
                        Inseminate.setText("Orgy");
                        orgyPossible = Boolean.valueOf(true);
                    }
                } else
                {
                    Inseminate.setBackground(YELLOWISH);
                }
                if((c.temptReq < 0x186a0L || c.negotiations > 0) && inseminated != 2)
                    Inseminate.setBackground(REDDISH);
                Inseminate.setToolTipText((new StringBuilder("<html><center>Inflicts HATE and PLEA along with<br>FEAR, DISG, ")).append(PAINname).append(", and SHAM<br>Causes tier-2 Morality Break</center></html>").toString());
                if(inseminated == 1)
                    Inseminate.setToolTipText((new StringBuilder("<html><center>Inflicts HATE, PLEA and ")).append(INJUname).append(" along with<br>FEAR, DISG, ").append(PAINname).append(", and SHAM<br>Causes tier-2 Morality Break</center></html>").toString());
                else
                if(inseminated == 2)
                    Inseminate.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                p.add(Inseminate);
                Inseminate.getInputMap(2).put(KeyStroke.getKeyStroke("5"), "pressed");
                if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 11)
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
                        plusPossible = Boolean.valueOf(true);
                    } else
                    {
                        ForceOrgasm.setText("Orgy");
                        orgyPossible = Boolean.valueOf(true);
                    }
                } else
                {
                    ForceOrgasm.setBackground(YELLOWISH);
                }
                if(c.dissociationReq < 10 && orgasming != 2)
                    ForceOrgasm.setBackground(REDDISH);
                ForceOrgasm.setToolTipText((new StringBuilder("<html><center>Inflicts PLEA and ")).append(INJUname).append(" along with<br>DISG, ").append(PAINname).append(", SHAM, and FEAR<br>Causes tier-2 Innocence Break</center></html>").toString());
                if(orgasming == 1)
                    ForceOrgasm.setToolTipText((new StringBuilder("<html><center>Inflicts PLEA,")).append(INJUname).append(", and EXPO along with<br>DISG, ").append(PAINname).append(", SHAM, and FEAR<br>Causes tier-2 Innocence Break</center></html>").toString());
                else
                if(orgasming == 2)
                    ForceOrgasm.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                p.add(ForceOrgasm);
                ForceOrgasm.getInputMap(2).put(KeyStroke.getKeyStroke("6"), "pressed");
                if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 12)
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
                        if(w.tickle().booleanValue())
                            Sodomize.setText("Force Laughter+");
                        else
                        if(c.getGender().equals("male"))
                            Sodomize.setText("Torture+");
                        else
                            Sodomize.setText("Sodomize+");
                        plusPossible = Boolean.valueOf(true);
                    } else
                    {
                        Sodomize.setText("Orgy");
                        orgyPossible = Boolean.valueOf(true);
                    }
                } else
                {
                    Sodomize.setBackground(YELLOWISH);
                }
                if(c.temptReq < 0x186a0L && sodomized != 2)
                    Sodomize.setBackground(REDDISH);
                Sodomize.setToolTipText((new StringBuilder("<html><center>Inflicts ")).append(INJUname).append(" and EXPO along with<br>").append(PAINname).append(", SHAM, FEAR, and DISG<br>Causes tier-2 Confidence Break</center></html>").toString());
                if(sodomized == 1)
                    Sodomize.setToolTipText((new StringBuilder("<html><center>Inflicts ")).append(INJUname).append(", EXPO, and HATE along with<br>").append(PAINname).append(", SHAM, FEAR, and DISG<br>Causes tier-2 Confidence Break</center></html>").toString());
                else
                if(sodomized == 2)
                    Sodomize.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                p.add(Sodomize);
                Sodomize.getInputMap(2).put(KeyStroke.getKeyStroke("7"), "pressed");
                if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 13)
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
                        plusPossible = Boolean.valueOf(true);
                    } else
                    {
                        Broadcast.setText("Orgy");
                        orgyPossible = Boolean.valueOf(true);
                    }
                } else
                {
                    Broadcast.setBackground(YELLOWISH);
                }
                if((c.dissociationReq < 10 || c.negotiations > 0) && broadcasted != 2)
                    Broadcast.setBackground(REDDISH);
                Broadcast.setToolTipText((new StringBuilder("<html><center>Inflicts EXPO and HATE along with<br>SHAM, FEAR, DISG, and ")).append(PAINname).append("<br>Causes tier-2 Dignity Break</center></html>").toString());
                if(broadcasted == 1)
                    Broadcast.setToolTipText((new StringBuilder("<html><center>Inflicts EXPO, HATE, and PLEA along with<br>SHAM, FEAR, DISG, and ")).append(PAINname).append("<br>Causes tier-2 Dignity Break</center></html>").toString());
                else
                if(broadcasted == 2)
                    Broadcast.setToolTipText("<html><center>Inflicts a high degree of all damage types</html>");
                p.add(Broadcast);
                Broadcast.getInputMap(2).put(KeyStroke.getKeyStroke("8"), "pressed");
                if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 14)
                    Broadcast.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Broadcast.getActionMap().put("pressed", BroadcastAction);
            }
            long currentTemptReq = c.temptReq;
            if(w.finalBattle.booleanValue())
                currentTemptReq *= 10L;
            if(c.getCurrentPLEA() >= currentTemptReq && c.vVirg.booleanValue() && c.aVirg.booleanValue() && !c.cVirg.booleanValue() && !c.modest.booleanValue() && !c.ruthless && !c.usingSlaughter.booleanValue() && !c.usingDetonate.booleanValue() && (c.temptReq < 0x186a0L || !w.finalBattle.booleanValue()))
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
                if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 14)
                    Tempt.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Tempt.getActionMap().put("pressed", TemptAction);
            }
            if(defilers > 0)
            {
                if(defilers > 1)
                    w.append(t, (new StringBuilder("  ")).append(defilers).append(" defiler actions possible.").toString());
                else
                if(defilers == 1)
                    w.append(t, "  1 defiler action possible.");
                int difference = 0;
                if(orgyPossible.booleanValue())
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

                    w.append(t, (new StringBuilder("  Orgy with ")).append(firstName).append(" and ").append(secondName).toString());
                    if(duration > opening)
                    {
                        difference = duration - opening;
                        w.append(t, " will cause them");
                    } else
                    if(opening > duration)
                    {
                        difference = opening - duration;
                        w.append(t, (new StringBuilder(" will allow ")).append(c.getMainName()).toString());
                    }
                    if(difference > 1)
                        w.append(t, (new StringBuilder(" to escape ")).append(difference).append(" turns early.").toString());
                    else
                    if(difference == 1)
                        w.append(t, " to escape 1 turn early.");
                    else
                        w.append(t, " does not allow any of them to escape early.");
                } else
                if(plusPossible.booleanValue())
                {
                    int opening = c.getSurroundDuration();
                    for(int i = 0; i < 3; i++)
                        if(w.getCombatants()[i] != null && w.getCombatants()[i] != c)
                        {
                            String defilementType = "";
                            if(w.getCombatants()[i].isInseminated().booleanValue() && c.getHATELevel() >= 3)
                                defilementType = "Inseminate";
                            else
                            if(w.getCombatants()[i].isOrgasming().booleanValue() && c.getPLEALevel() >= 3)
                                defilementType = "Force Orgasm";
                            else
                            if(w.getCombatants()[i].isSodomized().booleanValue() && c.getINJULevel() >= 3)
                            {
                                if(w.tickle().booleanValue())
                                    defilementType = "Force Laughter";
                                else
                                if(c.getGender().equals("male"))
                                    defilementType = "Torture";
                                else
                                    defilementType = "Sodomize";
                            } else
                            if(w.getCombatants()[i].isBroadcasted().booleanValue() && c.getEXPOLevel() >= 3)
                                defilementType = "Broadcast";
                            if(defilementType.length() > 0)
                            {
                                w.append(t, (new StringBuilder("  ")).append(defilementType).append(" with ").append(w.getCombatants()[i].getMainName()).toString());
                                if(opening > w.getCombatants()[i].getSurroundDuration())
                                {
                                    w.append(t, (new StringBuilder(" will allow ")).append(c.getMainName()).toString());
                                    difference = opening - w.getCombatants()[i].getSurroundDuration();
                                } else
                                if(w.getCombatants()[i].getSurroundDuration() > opening)
                                {
                                    w.append(t, (new StringBuilder(" will allow ")).append(w.getCombatants()[i].getMainName()).toString());
                                    difference = w.getCombatants()[i].getSurroundDuration() - opening;
                                }
                                if(difference > 1)
                                    w.append(t, (new StringBuilder(" to escape ")).append(difference).append(" turns early.").toString());
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
        if(c.isCaptured().booleanValue())
        {
            if(w.usedForsaken == null)
            {
                w.append(t, (new StringBuilder("\n\n")).append(c.getMainName()).append(" is captured by your Commander.  Any attempts to help by other Demons would simply get in the way.").toString());
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
                    if(w.tickleOn.booleanValue())
                        ForsakenDefiler.setToolTipText("<html><center>Deals bonus ANTI<br>and multiplies damage with lower Disgrace<br>Causes tier-2 Confidence Break</center></html>");
                    else
                        ForsakenDefiler.setToolTipText("<html><center>Deals bonus INJU<br>and multiplies damage with lower Disgrace<br>Causes tier-2 Confidence Break</center></html>");
                } else
                if(w.usedForsaken.defilerType == 4)
                    ForsakenDefiler.setToolTipText("<html><center>Deals bonus EXPO<br>and multiplies damage with lower Disgrace<br>Causes tier-2 Dignity Break</center></html>");
                else
                if(w.usedForsaken.defilerType == 5)
                {
                    if(w.tickleOn.booleanValue())
                        ForsakenDefiler.setToolTipText("<html><center>Deals bonus PLEA and EXPO but sets HATE and ANTI to zero<br>and multiplies damage with lower Disgrace<br>Causes and intensifies Morality/Confidence Distortion</center></html>");
                    else
                        ForsakenDefiler.setToolTipText("<html><center>Deals bonus PLEA and EXPO but sets HATE and INJU to zero<br>and multiplies damage with lower Disgrace<br>Causes and intensifies Morality/Confidence Distortion</center></html>");
                } else
                if(w.usedForsaken.defilerType == 6)
                    ForsakenDefiler.setToolTipText("<html><center>Adds target to Orgy<br>with duration of current Defiler+ action<br>Can trigger Innocence/Dignity Distortion just like any other Orgy</center></html>");
                else
                if(w.usedForsaken.defilerType == 7)
                    if(w.tickleOn.booleanValue())
                        ForsakenDefiler.setToolTipText("<html><center>Deals bonus DISG and TICK<br>but stops dealing circumstance damage</center></html>");
                    else
                        ForsakenDefiler.setToolTipText("<html><center>Deals bonus DISG and PAIN<br>but stops dealing circumstance damage</center></html>");
                Boolean conditionsMet = Boolean.valueOf(false);
                if(w.usedForsaken.defilerType == 1 && c.currentHATE >= 10000L)
                    conditionsMet = Boolean.valueOf(true);
                else
                if(w.usedForsaken.defilerType == 2 && c.currentPLEA >= 10000L)
                    conditionsMet = Boolean.valueOf(true);
                else
                if(w.usedForsaken.defilerType == 3 && c.currentINJU >= 10000L)
                    conditionsMet = Boolean.valueOf(true);
                else
                if(w.usedForsaken.defilerType == 4 && c.currentEXPO >= 10000L)
                    conditionsMet = Boolean.valueOf(true);
                else
                if(w.usedForsaken.defilerType == 5)
                {
                    long currentTemptReq = c.temptReq;
                    if(w.finalBattle.booleanValue())
                        currentTemptReq *= 10L;
                    if(c.currentPLEA >= currentTemptReq && c.vVirg.booleanValue() && c.aVirg.booleanValue() && !c.cVirg.booleanValue() && !c.modest.booleanValue() && !c.ruthless && c.timesSlaughtered() == 0 && c.timesDetonated() == 0 && (c.temptReq < 0x186a0L || !w.finalBattle.booleanValue()))
                        conditionsMet = Boolean.valueOf(true);
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

                    if(firstPartner != null && secondPartner != null && (firstPartner.inseminated.booleanValue() && secondPartner.inseminated.booleanValue() || firstPartner.orgasming.booleanValue() && secondPartner.orgasming.booleanValue() || firstPartner.sodomized.booleanValue() && secondPartner.sodomized.booleanValue() || firstPartner.broadcasted.booleanValue() && secondPartner.broadcasted.booleanValue()))
                        conditionsMet = Boolean.valueOf(true);
                } else
                if(w.usedForsaken.defilerType == 7)
                    conditionsMet = Boolean.valueOf(true);
                if(w.usedForsaken.defiling.booleanValue())
                    w.append(t, (new StringBuilder("\n\n")).append(w.usedForsaken.mainName).append(" has ").append(c.mainName).append(" completely under control.  There's no need for the Demons to get involved.").toString());
                else
                if(conditionsMet.booleanValue())
                {
                    w.append(t, "\n\nChoose your action.");
                    p.add(ForsakenDefiler);
                    ForsakenDefiler.getInputMap(2).put(KeyStroke.getKeyStroke("9"), "pressed");
                    if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 2)
                        ForsakenDefiler.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                    ForsakenDefiler.getActionMap().put("pressed", ForsakenDefilerAction);
                } else
                {
                    w.append(t, (new StringBuilder("\n\n")).append(c.getMainName()).append(" is engaged in combat with ").append(w.usedForsaken.mainName).append(".  There's no room for the Demons to get involved.").toString());
                }
            }
        } else
        {
            String PAINname = "PAIN";
            String INJUname = "INJU";
            if(w.tickle().booleanValue())
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
            Attack.setToolTipText((new StringBuilder("Inflicts ")).append(PAINname).toString());
            Attack.getInputMap(2).put(KeyStroke.getKeyStroke("3"), "pressed");
            if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 5)
                Attack.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
            Attack.getActionMap().put("pressed", AttackAction);
            if(w.finalBattle.booleanValue() && w.getTechs()[44].isOwned().booleanValue())
                Attack.setBackground(YELLOWISH);
            JButton Slime = new JButton(SlimeAction) {

                public Point getToolTipLocation(MouseEvent e)
                {
                    return new Point(0, -30);
                }

            };
            Slime.getInputMap(2).put(KeyStroke.getKeyStroke("2"), "pressed");
            if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 4)
                Slime.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
            Slime.getActionMap().put("pressed", SlimeAction);
            Slime.setToolTipText("Inflicts DISG");
            if(w.finalBattle.booleanValue() && w.getTechs()[43].isOwned().booleanValue() && c.isHypnotized().booleanValue())
                Slime.setBackground(YELLOWISH);
            JButton Taunt = new JButton(TauntAction) {

                public Point getToolTipLocation(MouseEvent e)
                {
                    return new Point(0, -30);
                }

            };
            Taunt.setToolTipText("Inflicts SHAM");
            Taunt.getInputMap(2).put(KeyStroke.getKeyStroke("4"), "pressed");
            if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 6)
                Taunt.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
            Taunt.getActionMap().put("pressed", TauntAction);
            if(w.finalBattle.booleanValue() && w.getTechs()[45].isOwned().booleanValue() && c.isParasitized().booleanValue() && c.surroundPossible(w).booleanValue())
                Taunt.setBackground(YELLOWISH);
            JButton Threaten = new JButton(ThreatenAction) {

                public Point getToolTipLocation(MouseEvent e)
                {
                    return new Point(0, -30);
                }

            };
            Threaten.setToolTipText("Inflicts FEAR");
            Threaten.getInputMap(2).put(KeyStroke.getKeyStroke("1"), "pressed");
            if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 3)
                Threaten.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
            Threaten.getActionMap().put("pressed", ThreatenAction);
            Boolean impregnatedAlly = Boolean.valueOf(false);
            for(int i = 0; i < 3; i++)
                if(i != c.getNumber() && w.getCast()[i] != null && w.getCast()[i].isImpregnated().booleanValue() && w.getCast()[i].alive.booleanValue())
                    impregnatedAlly = Boolean.valueOf(true);

            if(w.finalBattle.booleanValue() && w.getTechs()[42].isOwned().booleanValue() && impregnatedAlly.booleanValue())
                Threaten.setBackground(YELLOWISH);
            p.add(Threaten);
            p.add(Slime);
            p.add(Attack);
            p.add(Taunt);
            if(w.finalBattle.booleanValue() && c.negotiations > 0)
            {
                JButton Appeal = new JButton(AppealAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -30);
                    }

                };
                Appeal.setToolTipText("Damages Resolve");
                Appeal.getInputMap(2).put(KeyStroke.getKeyStroke("9"), "pressed");
                if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == 47 + c.getNumber())
                    Appeal.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Appeal.getActionMap().put("pressed", AppealAction);
                Appeal.setBackground(YELLOWISH);
                p.add(Appeal);
            }
            if(c.surroundPossible(w).booleanValue())
            {
                JButton Surround = new JButton(SurroundAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -60);
                    }

                };
                Surround.setBackground(YELLOWISH);
                Surround.getInputMap(2).put(KeyStroke.getKeyStroke(90, 0), "pressed");
                if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && (w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 1 || w.getTechs()[31].isOwned().booleanValue() && !c.isSurrounded().booleanValue() && w.getActions()[w.getCurrentAction()] >= c.getNumber() * 14 + 7 && w.getActions()[w.getCurrentAction()] <= c.getNumber() * 14 + 14))
                    Surround.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Surround.getActionMap().put("pressed", SurroundAction);
                p.add(Surround);
            }
            if(w.getCapturesPossible() > 0 && (c.getDefenseLevel() < 9000 || w.getBodyStatus()[24].booleanValue()) && w.commanderFree().booleanValue())
            {
                JButton Capture = new JButton(CaptureAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -90);
                    }

                };
                Capture.setBackground(PURPLISH);
                if(w.upgradedCommander().booleanValue())
                {
                    String description = "<html><center>Constantly inflicts ";
                    if(w.getBodyStatus()[26].booleanValue())
                    {
                        int types = 2;
                        String damages[] = new String[4];
                        if(w.getBodyStatus()[19].booleanValue())
                            damages[0] = "HATE";
                        else
                        if(w.getBodyStatus()[20].booleanValue())
                            damages[0] = "PLEA";
                        else
                        if(w.getBodyStatus()[21].booleanValue())
                        {
                            if(w.tickle().booleanValue())
                                damages[0] = "ANTI";
                            else
                                damages[0] = "INJU";
                        } else
                        if(w.getBodyStatus()[22].booleanValue())
                            damages[0] = "EXPO";
                        if(w.getBodyStatus()[11].booleanValue())
                        {
                            damages[1] = "HATE";
                            damages[2] = "PLEA";
                        } else
                        if(w.getBodyStatus()[12].booleanValue())
                        {
                            damages[1] = "PLEA";
                            if(w.tickle().booleanValue())
                                damages[2] = "ANTI";
                            else
                                damages[2] = "INJU";
                        } else
                        if(w.getBodyStatus()[13].booleanValue())
                        {
                            if(w.tickle().booleanValue())
                                damages[1] = "ANTI";
                            else
                                damages[1] = "INJU";
                            damages[2] = "EXPO";
                        } else
                        if(w.getBodyStatus()[14].booleanValue())
                        {
                            damages[1] = "EXPO";
                            damages[2] = "HATE";
                        }
                        if(w.getBodyStatus()[3].booleanValue())
                            damages[3] = "HATE";
                        else
                        if(w.getBodyStatus()[4].booleanValue())
                            damages[3] = "PLEA";
                        else
                        if(w.getBodyStatus()[5].booleanValue())
                        {
                            if(w.tickle().booleanValue())
                                damages[3] = "ANTI";
                            else
                                damages[3] = "INJU";
                        } else
                        if(w.getBodyStatus()[6].booleanValue())
                            damages[3] = "EXPO";
                        if(!damages[1].equals(damages[0]) && !damages[2].equals(damages[0]))
                            types++;
                        if(!damages[3].equals(damages[0]) && !damages[3].equals(damages[1]) && !damages[3].equals(damages[2]))
                            types++;
                        if(types == 2)
                        {
                            description = (new StringBuilder(String.valueOf(description))).append(damages[0]).append(" and ").toString();
                            if(damages[0].equals(damages[1]))
                                description = (new StringBuilder(String.valueOf(description))).append(damages[2]).toString();
                            else
                                description = (new StringBuilder(String.valueOf(description))).append(damages[1]).toString();
                        } else
                        if(types == 3)
                        {
                            description = (new StringBuilder(String.valueOf(description))).append(damages[0]).append(", ").toString();
                            if(damages[0].equals(damages[1]))
                                description = (new StringBuilder(String.valueOf(description))).append(damages[3]).append(", and ").append(damages[2]).toString();
                            else
                            if(damages[0].equals(damages[2]))
                                description = (new StringBuilder(String.valueOf(description))).append(damages[1]).append(", and ").append(damages[3]).toString();
                            else
                            if(damages[0].equals(damages[3]) || damages[1].equals(damages[3]))
                                description = (new StringBuilder(String.valueOf(description))).append(damages[1]).append(", and ").append(damages[2]).toString();
                            else
                                description = (new StringBuilder(String.valueOf(description))).append(damages[2]).append(", and ").append(damages[1]).toString();
                        } else
                        {
                            description = (new StringBuilder(String.valueOf(description))).append(damages[0]).append(", ").append(damages[1]).append(", ").append(damages[3]).append(", and ").append(damages[2]).toString();
                        }
                        description = (new StringBuilder(String.valueOf(description))).append(" along with<br>all four traumas").toString();
                    } else
                    if(w.getBodyStatus()[19].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("HATE along with<br>FEAR, DISG, ").append(PAINname).append(", and SHAM").toString();
                    else
                    if(w.getBodyStatus()[20].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("PLEA along with<br>DISG, ").append(PAINname).append(", SHAM, and FEAR").toString();
                    else
                    if(w.getBodyStatus()[21].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append(INJUname).append(" along with<br>").append(PAINname).append(", SHAM, FEAR, and DISG").toString();
                    else
                    if(w.getBodyStatus()[22].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("EXPO along with<br>SHAM, FEAR, DISG, and ").append(PAINname).toString();
                    else
                    if(w.getBodyStatus()[18].booleanValue())
                    {
                        String damages[] = new String[3];
                        if(w.getBodyStatus()[3].booleanValue())
                            damages[1] = "HATE";
                        else
                        if(w.getBodyStatus()[4].booleanValue())
                            damages[1] = "PLEA";
                        else
                        if(w.getBodyStatus()[5].booleanValue())
                            damages[1] = INJUname;
                        else
                        if(w.getBodyStatus()[6].booleanValue())
                            damages[1] = "EXPO";
                        if(w.getBodyStatus()[11].booleanValue())
                        {
                            damages[0] = "HATE";
                            damages[2] = "PLEA";
                        } else
                        if(w.getBodyStatus()[12].booleanValue())
                        {
                            damages[0] = "PLEA";
                            damages[2] = INJUname;
                        } else
                        if(w.getBodyStatus()[13].booleanValue())
                        {
                            damages[0] = INJUname;
                            damages[2] = "EXPO";
                        } else
                        if(w.getBodyStatus()[14].booleanValue())
                        {
                            damages[0] = "EXPO";
                            damages[2] = "HATE";
                        }
                        if(damages[0].equals(damages[2]))
                            description = (new StringBuilder(String.valueOf(description))).append(damages[0]).append(" and ").append(damages[1]).toString();
                        else
                        if(damages[1].equals(damages[2]))
                            description = (new StringBuilder(String.valueOf(description))).append(damages[1]).append(" and ").append(damages[0]).toString();
                        else
                            description = (new StringBuilder(String.valueOf(description))).append(damages[0]).append(", ").append(damages[1]).append(", and ").append(damages[2]).toString();
                        description = (new StringBuilder(String.valueOf(description))).append(" along with<br>all four traumas").toString();
                    } else
                    if(w.getBodyStatus()[11].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("HATE and PLEA along with<br>FEAR, DISG, ").append(PAINname).append(", and SHAM").toString();
                    else
                    if(w.getBodyStatus()[12].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("PLEA and ").append(INJUname).append(" along with<br>DISG, ").append(PAINname).append(", SHAM, and FEAR").toString();
                    else
                    if(w.getBodyStatus()[13].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append(INJUname).append(" and EXPO along with<br>").append(PAINname).append(", SHAM, FEAR, and DISG").toString();
                    else
                    if(w.getBodyStatus()[14].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("EXPO and HATE along with<br>SHAM, FEAR, DISG, and ").append(PAINname).toString();
                    else
                    if(w.getBodyStatus()[10].booleanValue())
                    {
                        Boolean firstFound = Boolean.valueOf(false);
                        if(w.getBodyStatus()[3].booleanValue())
                        {
                            description = (new StringBuilder(String.valueOf(description))).append("HATE").toString();
                            firstFound = Boolean.valueOf(true);
                        }
                        if(w.getBodyStatus()[4].booleanValue())
                        {
                            if(firstFound.booleanValue())
                                description = (new StringBuilder(String.valueOf(description))).append(" and ").toString();
                            description = (new StringBuilder(String.valueOf(description))).append("PLEA").toString();
                            firstFound = Boolean.valueOf(true);
                        }
                        if(w.getBodyStatus()[5].booleanValue())
                        {
                            if(firstFound.booleanValue())
                                description = (new StringBuilder(String.valueOf(description))).append(" and ").toString();
                            description = (new StringBuilder(String.valueOf(description))).append(INJUname).toString();
                            firstFound = Boolean.valueOf(true);
                        }
                        if(w.getBodyStatus()[6].booleanValue())
                            description = " and EXPO";
                        description = (new StringBuilder(String.valueOf(description))).append(" along with<br>all four traumas").toString();
                    } else
                    if(w.getBodyStatus()[3].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("HATE along with<br>FEAR, DISG, ").append(PAINname).append(", and SHAM").toString();
                    else
                    if(w.getBodyStatus()[4].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("PLEA along with<br>DISG, ").append(PAINname).append(", SHAM, and FEAR").toString();
                    else
                    if(w.getBodyStatus()[5].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append(INJUname).append(" along with<br>").append(PAINname).append(", SHAM, FEAR, and DISG").toString();
                    else
                    if(w.getBodyStatus()[6].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("EXPO along with<br>SHAM, FEAR, DISG, and ").append(PAINname).toString();
                    else
                        description = "<html><center>Surrounds the target";
                    description = (new StringBuilder(String.valueOf(description))).append("<br>for ").toString();
                    if(w.getBodyStatus()[25].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("eight").toString();
                    else
                    if(w.getBodyStatus()[15].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("six").toString();
                    else
                    if(w.getBodyStatus()[9].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("five").toString();
                    else
                    if(w.getBodyStatus()[7].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("four").toString();
                    else
                    if(w.getBodyStatus()[1].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("three").toString();
                    else
                        description = (new StringBuilder(String.valueOf(description))).append("two").toString();
                    description = (new StringBuilder(String.valueOf(description))).append(" rounds").toString();
                    if(w.getBodyStatus()[8].booleanValue())
                    {
                        description = (new StringBuilder(String.valueOf(description))).append(" (").toString();
                        if(w.getCapturesPossible() == 4)
                            description = (new StringBuilder(String.valueOf(description))).append("four").toString();
                        else
                        if(w.getCapturesPossible() == 3)
                            description = (new StringBuilder(String.valueOf(description))).append("three").toString();
                        else
                        if(w.getCapturesPossible() == 2)
                            description = (new StringBuilder(String.valueOf(description))).append("two").toString();
                        else
                        if(w.getCapturesPossible() == 1)
                            description = (new StringBuilder(String.valueOf(description))).append("one").toString();
                        description = (new StringBuilder(String.valueOf(description))).append(" left)").toString();
                    }
                    if(w.getBodyStatus()[11].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("<br>Above 10k HATE, causes tier-2 Morality Break").toString();
                    else
                    if(w.getBodyStatus()[12].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("<br>Above 10k PLEA, causes tier-2 Innocence Break").toString();
                    else
                    if(w.getBodyStatus()[13].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("<br>Above 10k ").append(INJUname).append(", causes tier-2 Confidence Break").toString();
                    else
                    if(w.getBodyStatus()[14].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("<br>Above 10k EXPO, causes tier-2 Dignity Break").toString();
                    if(w.getBodyStatus()[19].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("<br>Above 1000% Impregnation effectiveness, causes Total Morality Break").toString();
                    else
                    if(w.getBodyStatus()[20].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("<br>Above 1000% Hypnosis effectiveness, causes Total Innocence Break").toString();
                    else
                    if(w.getBodyStatus()[21].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("<br>Above 1000% Drain effectiveness, causes Total Confidence Break").toString();
                    else
                    if(w.getBodyStatus()[22].booleanValue())
                        description = (new StringBuilder(String.valueOf(description))).append("<br>Above 1000% Parasitism effectiveness, causes Total Dignity Break").toString();
                    if(w.usedForsaken != null)
                        description = (new StringBuilder("<html><center>Grab with ")).append(w.usedForsaken.mainName).append(" for ").append(w.usedForsaken.compatibility(c)).append(" rounds<br>").append(w.usedForsaken.describeCombatStyle(w, Boolean.valueOf(false))).toString();
                    description = (new StringBuilder(String.valueOf(description))).append("</center></html>").toString();
                    Capture.setToolTipText(description);
                }
                Capture.getInputMap(2).put(KeyStroke.getKeyStroke(67, 0), "pressed");
                if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 2)
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
                if(w.finalBattle.booleanValue() && (!w.getCombatants()[i].alive.booleanValue() || w.getCombatants()[i].resolve <= 0))
                    defeated++;
                else
                if(w.getCombatants()[i].dissociated.booleanValue())
                    dissociated++;
                else
                if(!w.getCombatants()[i].isCaptured().booleanValue() && (!w.getCombatants()[i].isSurrounded().booleanValue() || !w.getCombatants()[i].isDefiled().booleanValue() && (w.getCombatants()[i].getHATELevel() >= 3 || w.getCombatants()[i].getPLEALevel() >= 3 || w.getCombatants()[i].getINJULevel() >= 3 || w.getCombatants()[i].getEXPOLevel() >= 3 || !w.getCombatants()[i].grind.booleanValue() || !w.getCombatants()[i].caress.booleanValue() || !w.getCombatants()[i].pummel.booleanValue() || !w.getCombatants()[i].humiliate.booleanValue())) && w.getCombatants()[i] != c)
                    targets++;

        if(targets > 0)
        {
            class _cls1BackButton extends AbstractAction
            {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n").toString());
                    Project.PickTarget(t, p, f, w);
                    if(w.tutorialResponse().booleanValue() && w.getBattleRound() == 6 && c == w.getCast()[2])
                        w.grayAppend(t, "\n\n(We created another opening last turn, but because we've already grabbed Miracle once, her defense level has gone up.  We'll need at least three opening levels to grab her again.  Fortunately, she's taken enough FEAR and SHAM damage now that it should be easy to push her over 100 in both.  Target Miracle and then Threaten her.)");
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final Chosen val$c;

            public _cls1BackButton(JFrame jframe, Chosen chosen)
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                c = chosen;
                super(text);
                putValue("ShortDescription", desc);
            }
            }

            Action BackAction = new _cls1BackButton(f, c);
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
                    if(w.getCombatants()[i].isSurrounded().booleanValue() && w.getCombatants()[i].getSurroundDuration() > 0)
                    {
                        if(w.getCombatants()[i].getSurroundDuration() > 0)
                            occupied += w.getCombatants()[i].getSurroundDuration();
                        else
                            occupied++;
                    } else
                    if(w.getCombatants()[i].isCaptured().booleanValue())
                        occupied += (w.getCaptureDuration() - w.getCombatants()[i].getCaptureProgression()) + 1;

            int occupiedBonus = occupied / 5;
            class _cls2RetreatButton extends AbstractAction
            {

                public void actionPerformed(ActionEvent e)
                {
                    p.removeAll();
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    if(occupiedBonus > 0)
                        w.append(t, (new StringBuilder("Retreat and end the battle immediately for +")).append(occupiedBonus).append(" Evil Energy?").toString());
                    else
                        w.append(t, "Really retreat?  You will not gain any bonus Evil Energy!");
                    JButton Confirm = new JButton("Confirm");
                    Confirm.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            p.removeAll();
                            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                            String trapped[] = new String[3];
                            String free[] = new String[3];
                            int trappedNumber = 0;
                            for(int i = 0; i < 3; i++)
                                if(w.getCombatants()[i] != null)
                                    if(w.getCombatants()[i].isSurrounded().booleanValue() || w.getCombatants()[i].isCaptured().booleanValue())
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
                                    if(w.getCast()[i] != null && !w.getCast()[i].equals(w.getCombatants()[0]).booleanValue())
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
                                    if(w.getCast()[i] != null && !w.getCast()[i].equals(w.getCombatants()[0]).booleanValue() && !w.getCast()[i].equals(w.getCombatants()[1]).booleanValue())
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
                                    w.append(t, (new StringBuilder("However, ")).append(free[0]).append(" is quick to pursue, cutting your forces down from behind and stopping them from taking any significant number of civilians back to the hive.").toString());
                                else
                                    w.append(t, (new StringBuilder(String.valueOf(trapped[0]))).append(" is unable to follow until plenty of civilians are already on their way to the hive.").toString());
                            } else
                            if(w.getCast()[2] == null)
                            {
                                if(trappedNumber == 0)
                                    w.append(t, "However, the two Chosen cut your forces down from behind, freeing most of the civilians before they can be brought to the hive.");
                                else
                                if(trappedNumber == 1)
                                    w.append(t, (new StringBuilder("With ")).append(trapped[0]).append(" unable to give chase, the risk of splitting the team forces ").append(free[0]).append(" to give up and let you take the civilians to the hive.").toString());
                                else
                                    w.append(t, "The Chosen have to finish dealing with their own problems before they can try to stop you, and by then, plenty of your forces have escaped.");
                            } else
                            if(trappedNumber == 0 || occupiedBonus == 0)
                                w.append(t, "However, the three Chosen cut your forces down from behind, freeing most of the civilians before they can be brought to the hive.");
                            else
                            if(trappedNumber == 1)
                                w.append(t, (new StringBuilder(String.valueOf(free[0]))).append(" and ").append(free[1]).append(" try to give chase, but with ").append(trapped[0]).append(" unable to follow, they're forced to give up due to the risk of splitting the team.").toString());
                            else
                            if(trappedNumber == 2)
                                w.append(t, (new StringBuilder(String.valueOf(free[0]))).append(" tries to stop them, but with ").append(trapped[0]).append(" and ").append(trapped[1]).append(" unable to help, you're able to get plenty of victims to the hive.").toString());
                            else
                                w.append(t, "The Chosen have to finish dealing with their own problems before they can try to stop you, and by then, plenty of your forces have escaped.");
                            if(occupiedBonus > 0)
                                w.append(t, (new StringBuilder("\n\n+")).append(occupiedBonus).append(" Evil Energy").toString());
                            Project.advanceAction(p, w, 43);
                            w.addEnergy(occupiedBonus);
                            JButton Continue = new JButton("Continue");
                            Continue.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.PostBattle(t, p, f, w);
                                }

                                final _cls1 this$2;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final WorldState val$w;

                        
                        {
                            this$2 = _cls1.this;
                            t = jtextpane;
                            p = jpanel;
                            f = jframe;
                            w = worldstate;
                            super();
                        }
                            });
                            p.add(Continue);
                            p.validate();
                            p.repaint();
                        }

                        final _cls2RetreatButton this$1;
                        private final JPanel val$p;
                        private final WorldState val$w;
                        private final JTextPane val$t;
                        private final int val$occupiedBonus;
                        private final JFrame val$f;

                    
                    {
                        this$1 = _cls2RetreatButton.this;
                        p = jpanel;
                        w = worldstate;
                        t = jtextpane;
                        occupiedBonus = i;
                        f = jframe;
                        super();
                    }
                    });
                    p.add(Confirm);
                    JButton Cancel = new JButton("Cancel");
                    Cancel.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n").toString());
                            Project.PickTarget(t, p, f, w);
                        }

                        final _cls2RetreatButton this$1;
                        private final WorldState val$w;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;

                    
                    {
                        this$1 = _cls2RetreatButton.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                    });
                    p.add(Cancel);
                    p.validate();
                    p.repaint();
                }

                private final JPanel val$p;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final int val$occupiedBonus;
                private final JFrame val$f;

            public _cls2RetreatButton(int i, JFrame jframe)
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                occupiedBonus = i;
                f = jframe;
                super(text);
                putValue("ShortDescription", desc);
            }
            }

            Action RetreatAction = new _cls2RetreatButton(occupiedBonus, f);
            JButton Retreat = new JButton(RetreatAction) {

                public Point getToolTipLocation(MouseEvent e)
                {
                    return new Point(0, -30);
                }

            };
            if(w.getTechs()[19].isOwned().booleanValue() && !w.finalBattle.booleanValue())
                p.add(Retreat);
        }
        if(w.writePossible().booleanValue())
            addWriteButton(p, w);
        p.validate();
        p.repaint();
    }

    public static void EnemyTurn(JTextPane t, JPanel p, JFrame f, WorldState w, Chosen initiative[], int progress)
    {
        Boolean endgame = w.finalBattle;
        Boolean actorFound;
        for(actorFound = Boolean.valueOf(false); !actorFound.booleanValue() && progress < initiative.length && initiative[progress] != null;)
        {
            w.clearBonus(progress);
            actorFound = Boolean.valueOf(true);
            if(w.finalBattle.booleanValue() && (!initiative[progress].alive.booleanValue() || initiative[progress].resolve <= 0))
            {
                actorFound = Boolean.valueOf(false);
                progress++;
            }
        }

        if(actorFound.booleanValue())
        {
            if(w.getSurroundTarget() == initiative[progress] || initiative[progress].isSurrounded().booleanValue())
                initiative[progress].BeSurrounded(t, p, f, w);
            else
            if(w.getCaptureTarget() == initiative[progress] || initiative[progress].isCaptured().booleanValue())
            {
                if(w.usedForsaken == null)
                    w.BeCaptured(t, p, f, w, initiative[progress]);
                else
                    w.usedForsaken.captureChosen(t, p, f, w, initiative[progress]);
            } else
            {
                initiative[progress].TakeTurn(t, p, f, w);
            }
            progress++;
        }
        p.removeAll();
        int currentProgress = progress;
        Boolean moreTurns = Boolean.valueOf(true);
        if(progress > 2)
            moreTurns = Boolean.valueOf(false);
        else
        if(initiative[progress] == null)
            moreTurns = Boolean.valueOf(false);
        else
        if(w.finalBattle.booleanValue() && (!initiative[progress].alive.booleanValue() || initiative[progress].resolve <= 0))
            if(++progress > 2)
                moreTurns = Boolean.valueOf(false);
            else
            if(initiative[progress] == null)
                moreTurns = Boolean.valueOf(false);
            else
            if(w.finalBattle.booleanValue() && (!initiative[progress].alive.booleanValue() || initiative[progress].resolve <= 0))
                if(++progress > 2)
                    moreTurns = Boolean.valueOf(false);
                else
                if(initiative[progress] == null)
                    moreTurns = Boolean.valueOf(false);
                else
                if(w.finalBattle.booleanValue() && (!initiative[progress].alive.booleanValue() || initiative[progress].resolve <= 0))
                    moreTurns = Boolean.valueOf(false);
        if(!actorFound.booleanValue())
        {
            int defeated = 0;
            for(int i = 0; i < 3; i++)
                if(initiative[i] != null && (!initiative[i].alive.booleanValue() || initiative[i].resolve <= 0))
                    defeated++;

            if(defeated < 3)
            {
                endgame = Boolean.valueOf(false);
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nThe Demons swarm across the city unopposed!").toString());
            }
        } else
        {
            endgame = Boolean.valueOf(false);
        }
        if(w.finalBattle.booleanValue() && w.getCast()[2] != null && w.getCast()[0].dissociated.booleanValue() && w.getCast()[1].dissociated.booleanValue() && w.getCast()[2].dissociated.booleanValue())
            endgame = Boolean.valueOf(true);
        if(endgame.booleanValue())
        {
            int captured = 0;
            int dead = 0;
            Chosen survivors[] = new Chosen[3];
            Chosen killed[] = new Chosen[3];
            for(int i = 0; i < 3; i++)
                if(w.getCast()[i].alive.booleanValue())
                {
                    survivors[captured] = w.getCast()[i];
                    captured++;
                } else
                {
                    killed[dead] = w.getCast()[i];
                    dead++;
                }

            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
            if(captured == 3)
                w.append(t, (new StringBuilder("Finally, all three of the Chosen have surrendered to your forces.  This is a flawless victory - you couldn't have hoped for a better result.  By the time the reinforcements from other cities arrive, your Demonic Barrier has already reached full strength, and no more Chosen can enter without immediately losing their powers and joining the ranks of your captives.\n\nThe Demons escort ")).append(w.getCast()[0].getMainName()).append(", ").append(w.getCast()[1].getMainName()).append(", and ").append(w.getCast()[2].getMainName()).append(" to your throne room, where you will begin to train them into your own loyal servants...").toString());
            else
            if(captured == 2)
                w.append(t, (new StringBuilder("With both ")).append(survivors[0].getMainName()).append(" and ").append(survivors[1].getMainName()).append(" broken, your victory is complete.  By the time the reinforcements from other cities arrive, your Demonic Barrier has already reached full strength, and no more Chosen can enter without immediately losing their powers and joining the ranks of your captives.\n\n").append(killed[0].getMainName()).append("'s death was unfortunate - ").append(killed[0].heShe()).append(" would have made an excellent servant.  But you still have ").append(survivors[0].getMainName()).append(" and ").append(survivors[1].getMainName()).append(".  The Demons escort them to your throne room so that their training can begin...").toString());
            else
                w.append(t, (new StringBuilder("With ")).append(survivors[0].getMainName()).append(" defeated, your takeover of the city is complete.  By the time the reinforcements from other cities arrive, your Demonic Barrier has already reached full strength, and no more Chosen can enter without immediately losing their powers and joining the ranks of your captives.\n\nThe deaths of ").append(killed[0].getMainName()).append(" and ").append(killed[1].getMainName()).append(" were very unfortunate - they would have made excellent servants.  But you still managed to hold onto one prize.  The Demons escort ").append(survivors[0].getMainName()).append(" into your throne room so that ").append(survivors[0].hisHer()).append(" training can begin...").toString());
            EndFinalBattle(t, p, f, w);
        } else
        if(moreTurns.booleanValue())
        {
            class _cls2ContinueButton extends AbstractAction
            {

                public void actionPerformed(ActionEvent e)
                {
                    Project.EnemyTurn(t, p, f, w, initiative, currentProgress);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Chosen val$initiative[];
                private final int val$currentProgress;

            public _cls2ContinueButton(Chosen achosen[], 
                    int i)
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                initiative = achosen;
                currentProgress = i;
                super(text);
                putValue("ShortDescription", desc);
            }
            }

            Action ContinueAction = new _cls2ContinueButton(initiative, currentProgress);
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
                    if(w.getCombatants()[i].isInseminated().booleanValue())
                        type = 1;
                    else
                    if(w.getCombatants()[i].isOrgasming().booleanValue())
                        type = 2;
                    else
                    if(w.getCombatants()[i].isSodomized().booleanValue())
                        type = 3;
                    else
                    if(w.getCombatants()[i].isBroadcasted().booleanValue())
                        type = 4;
                    if(type > 0)
                    {
                        for(int j = i + 1; j < 3; j++)
                            if(w.getCombatants()[j] != null)
                            {
                                int otherType = 0;
                                if(w.getCombatants()[j].isInseminated().booleanValue())
                                    otherType = 1;
                                else
                                if(w.getCombatants()[j].isOrgasming().booleanValue())
                                    otherType = 2;
                                else
                                if(w.getCombatants()[j].isSodomized().booleanValue())
                                    otherType = 3;
                                else
                                if(w.getCombatants()[j].isBroadcasted().booleanValue())
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
            class _cls3ContinueButton extends AbstractAction
            {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Boolean newChosen = Boolean.valueOf(false);
                    if(w.getCast()[1] == null)
                    {
                        if(w.getTotalRounds() >= (18 * (15 - w.eventOffset)) / 15 || w.day > 20)
                            newChosen = Boolean.valueOf(true);
                    } else
                    if(w.getCast()[2] == null && (w.getTotalRounds() >= (60 * (15 - w.eventOffset)) / 15 || w.day > 30))
                        newChosen = Boolean.valueOf(true);
                    if((w.evacComplete() || w.getBattleRound() < 4) && (w.getCast()[1] != null && w.getTotalRounds() < (80 * (15 - w.eventOffset)) / 15 && w.day <= 30 || w.getCast()[1] == null && w.getTotalRounds() < (28 * (15 - w.eventOffset)) / 15 && w.day <= 20))
                        newChosen = Boolean.valueOf(false);
                    int arrival = -1;
                    int timerStandard = 10000;
                    for(int i = 0; i < 3; i++)
                        if(w.decrementArrival(i).booleanValue())
                        {
                            Chosen thisChosen = w.getCast()[i];
                            Boolean successfulArrival = Boolean.valueOf(true);
                            if(thisChosen == w.getCombatants()[0])
                                successfulArrival = Boolean.valueOf(false);
                            else
                            if(w.getCombatants()[1] != null && w.getCombatants()[1] == thisChosen)
                                successfulArrival = Boolean.valueOf(false);
                            if(w.getCast()[i] == null)
                                successfulArrival = Boolean.valueOf(false);
                            if(successfulArrival.booleanValue() && w.getArrivalTimer()[i] < timerStandard)
                            {
                                arrival = i;
                                timerStandard = w.getArrivalTimer()[i];
                            }
                        }

                    if(w.getCombatants()[2] != null)
                        arrival = -1;
                    if(newChosen.booleanValue())
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
                            w.append(t, (new StringBuilder("As the battle rages below, an unfamiliar figure arrives on the nearby rooftops.  After watching for a moment, ")).append(arrivingChosen.heShe()).append(" makes a fateful decision.  A loud crack rings through the air, light shining from above!\n\n").toString());
                        arrivingChosen.say(t, (new StringBuilder("\"")).append(arrivingChosen.announcement()).append("\"\n\n").toString());
                        arrivingChosen.transform(t, w);
                        w.append(t, "\n\n");
                        arrivingChosen.printGreeting(t, w);
                        Chosen responding = w.getCombatants()[1];
                        if(w.getCombatants()[1] == arrivingChosen)
                            responding = w.getCombatants()[0];
                        else
                        if((w.getCombatants()[1].isSurrounded().booleanValue() || w.getCombatants()[1].isCaptured().booleanValue()) && !w.getCombatants()[0].isSurrounded().booleanValue() && !w.getCombatants()[0].isCaptured().booleanValue())
                            responding = w.getCombatants()[0];
                        if(!responding.isSurrounded().booleanValue() && !responding.isCaptured().booleanValue())
                        {
                            w.append(t, "\n\n");
                            responding.printResponse(t, w);
                        }
                        class _cls1ContinueButtonTwo extends AbstractAction
                        {

                            public void actionPerformed(ActionEvent e)
                            {
                                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n").toString());
                                w.endTurn(t);
                                w.append(t, "\n");
                                Project.PickTarget(t, p, f, w);
                            }

                            final _cls3ContinueButton this$1;
                            private final WorldState val$w;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;

                    public _cls1ContinueButtonTwo(JPanel jpanel, JFrame jframe)
                    {
                        this$1 = _cls3ContinueButton.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super(text);
                        putValue("ShortDescription", desc);
                    }
                        }

                        Action ContinueActionTwo = new _cls1ContinueButtonTwo(p, f);
                        JButton Continue = new JButton(ContinueActionTwo) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -30);
                            }

                            final _cls3ContinueButton this$1;

                    
                    {
                        this$1 = _cls3ContinueButton.this;
                        super($anonymous0);
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
                        arrivingChosen.say(t, (new StringBuilder("\"")).append(arrivingChosen.announcement()).append("\"\n\n").toString());
                        arrivingChosen.transform(t, w);
                        if(w.finalBattle.booleanValue())
                        {
                            w.addToCombat(arrivingChosen);
                            w.append(t, "\n\n");
                            w.finalBattleIntro(t, arrivingChosen);
                        } else
                        {
                            Chosen responder = null;
                            for(Boolean response = Boolean.valueOf(false); !response.booleanValue();)
                            {
                                if(Math.random() < 0.5D)
                                {
                                    if(!w.getCombatants()[0].isSurrounded().booleanValue() && !w.getCombatants()[0].isCaptured().booleanValue() && w.getRelationship(w.getCombatants()[0].getNumber(), arrivingChosen.getNumber()) != 0)
                                    {
                                        responder = w.getCombatants()[0];
                                        response = Boolean.valueOf(true);
                                    }
                                } else
                                if(w.getCombatants()[1] != null && !w.getCombatants()[1].isSurrounded().booleanValue() && !w.getCombatants()[1].isCaptured().booleanValue() && w.getRelationship(w.getCombatants()[1].getNumber(), arrivingChosen.getNumber()) != 0)
                                {
                                    responder = w.getCombatants()[1];
                                    response = Boolean.valueOf(true);
                                }
                                if(w.getCombatants()[0].isSurrounded().booleanValue() || w.getCombatants()[0].isCaptured().booleanValue() || w.getRelationship(w.getCombatants()[0].getNumber(), arrivingChosen.getNumber()) == 0)
                                    if(w.getCombatants()[1] == null)
                                        response = Boolean.valueOf(true);
                                    else
                                    if(w.getCombatants()[1].isSurrounded().booleanValue() || w.getCombatants()[1].isCaptured().booleanValue() || w.getRelationship(w.getCombatants()[1].getNumber(), arrivingChosen.getNumber()) == 0)
                                        response = Boolean.valueOf(true);
                            }

                            w.addToCombat(arrivingChosen);
                            if(responder != null)
                            {
                                w.append(t, "\n\n");
                                responder.printGreetingAgain(t, w, arrivingChosen);
                            }
                        }
                        class _cls2ContinueButtonTwo extends AbstractAction
                        {

                            public void actionPerformed(ActionEvent e)
                            {
                                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n").toString());
                                if(w.endTurn(t))
                                {
                                    w.append(t, "\n");
                                    if(w.finalBattle.booleanValue())
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
                                            if(c != null && c.isSurrounded().booleanValue())
                                                c = null;
                                        }

                                        if(w.getCombatants()[1] == null)
                                            w.append(t, (new StringBuilder(String.valueOf(c.getMainName()))).append(" returns").toString());
                                        else
                                            w.append(t, "the Chosen return");
                                        w.append(t, " home to prepare for tomorrow's fight.\n\n");
                                        c.VictoryLine(t, p, f, w);
                                        JButton ContinueTwo = new JButton("Continue");
                                        ContinueTwo.addActionListener(new ActionListener() {

                                            public void actionPerformed(ActionEvent e)
                                            {
                                                Project.PostBattle(t, p, f, w);
                                            }

                                            final _cls2ContinueButtonTwo this$2;
                                            private final JTextPane val$t;
                                            private final JPanel val$p;
                                            private final JFrame val$f;
                                            private final WorldState val$w;

                        
                        {
                            this$2 = _cls2ContinueButtonTwo.this;
                            t = jtextpane;
                            p = jpanel;
                            f = jframe;
                            w = worldstate;
                            super();
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
                                    if(w.tutorialResponse().booleanValue())
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

                            final _cls3ContinueButton this$1;
                            private final WorldState val$w;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;

                    public _cls2ContinueButtonTwo(JPanel jpanel, JFrame jframe)
                    {
                        this$1 = _cls3ContinueButton.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super(text);
                        putValue("ShortDescription", desc);
                    }
                        }

                        Action ContinueActionTwo = new _cls2ContinueButtonTwo(p, f);
                        JButton Continue = new JButton(ContinueActionTwo) {

                            public Point getToolTipLocation(MouseEvent e)
                            {
                                return new Point(0, -30);
                            }

                            final _cls3ContinueButton this$1;

                    
                    {
                        this$1 = _cls3ContinueButton.this;
                        super($anonymous0);
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
                        if(w.finalBattle.booleanValue())
                        {
                            Project.DefeatScene(t, p, f, w);
                        } else
                        {
                            w.append(t, "The Demonic forces have been routed, and the stragglers flee back into their underground tunnels.  Crisis workers arrive to round up the remaining Thralls for purification.  Meanwhile, ");
                            Chosen c;
                            for(c = null; c == null;)
                            {
                                c = w.getCombatants()[(int)(Math.random() * 3D)];
                                if(c != null && c.isSurrounded().booleanValue())
                                    c = null;
                            }

                            if(w.getCombatants()[1] == null)
                                w.append(t, (new StringBuilder(String.valueOf(c.getMainName()))).append(" returns").toString());
                            else
                                w.append(t, "the Chosen return");
                            w.append(t, " home to prepare for tomorrow's fight.\n\n");
                            c.VictoryLine(t, p, f, w);
                            JButton ContinueTwo = new JButton("Continue");
                            ContinueTwo.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.PostBattle(t, p, f, w);
                                }

                                final _cls3ContinueButton this$1;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final WorldState val$w;

                    
                    {
                        this$1 = _cls3ContinueButton.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
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
                        if(w.tutorialResponse().booleanValue())
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
                                    if(w.tickle().booleanValue())
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
                                if(w.getCast()[0].isSurrounded().booleanValue())
                                {
                                    if(w.tickle().booleanValue())
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
                                    if(w.tickle().booleanValue())
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
                                if(w.getCast()[1].captured.booleanValue())
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
                                if(w.getCast()[0].isSurrounded().booleanValue())
                                {
                                    if(w.tickle().booleanValue())
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
                                if(w.getCast()[1].isSurrounded().booleanValue())
                                    w.grayAppend(t, "\n\n(It's tempting to try to do something with Spice, but because extermination was already completed, we won't be able to grab her again after this.  We don't have a way to increase her EXPO by another level, so it wouldn't help us break Miracle, either.  Just leave Spice alone and surround Miracle again.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 15)
                            {
                                if(w.getCast()[0].isSurrounded().booleanValue())
                                    w.grayAppend(t, "\n\n(Miracle's multipliers don't look too impressive, but because we've built up such a long surround duration, we have time to improve them.  HATE is already almost at the next level, so start with Grind on Miracle.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 16)
                            {
                                if(w.getCast()[0].getCurrentHATE() == 958L)
                                {
                                    if(w.tickle().booleanValue())
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
                                    if(w.tickle().booleanValue())
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
                                    if(w.tickle().booleanValue())
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

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;

            public _cls3ContinueButton(JPanel jpanel, JFrame jframe)
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super(text);
                putValue("ShortDescription", desc);
            }
            }

            Action ContinueAction = new _cls3ContinueButton(p, f);
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
        Boolean justContinue = Boolean.valueOf(true);
        Boolean postScene = Boolean.valueOf(false);
        int vignette = -1;
        if(!w.isTutorial().booleanValue() && !w.loopComplete.booleanValue())
            vignette = w.chooseVignette();
        if(w.loopComplete.booleanValue())
            w.pendingBreaks = new int[0];
        final int chosenVignette = vignette;
        for(int i = 0; i < 3; i++)
            if(w.getCast()[i] != null)
            {
                w.getCast()[i].visited = Boolean.valueOf(false);
                if(w.getCast()[i].morality < 34 && w.getCast()[i].impregnated.booleanValue())
                {
                    for(int j = 0; j < 3; j++)
                        if(w.getCast()[j] != null && w.getCast()[j].morality > 66)
                        {
                            long _tmp = w.getCast()[j].temptReq;
                        }

                }
                if(w.getCast()[i].confidence < 34 && w.getCast()[i].drained.booleanValue())
                {
                    for(int j = 0; j < 3; j++)
                        if(w.getCast()[j] != null && w.getCast()[j].confidence > 66)
                        {
                            long _tmp1 = w.getCast()[j].temptReq;
                        }

                }
            }

        if(w.isTutorial().booleanValue())
        {
            long totalTrauma = w.getCast()[0].getCurrentFEAR() + w.getCast()[0].getCurrentDISG() + w.getCast()[0].getCurrentPAIN() + w.getCast()[0].getCurrentSHAM() + w.getCast()[1].getCurrentFEAR() + w.getCast()[1].getCurrentDISG() + w.getCast()[1].getCurrentPAIN() + w.getCast()[1].getCurrentSHAM() + w.getCast()[2].getCurrentFEAR() + w.getCast()[2].getCurrentDISG() + w.getCast()[2].getCurrentPAIN() + w.getCast()[2].getCurrentSHAM();
            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nTotal trauma: ").append(w.getCast()[0].condensedFormat(totalTrauma)).append("\nVulnerabilities broken:").toString());
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
                w.append(t, (new StringBuilder(" ")).append(cores).append(" Core").toString());
            if(sigs > 0)
                w.append(t, (new StringBuilder(" ")).append(sigs).append(" Significant").toString());
            if(minors > 0)
                w.append(t, (new StringBuilder(" ")).append(minors).append(" Minor").toString());
        } else
        if(!w.getCast()[0].isIntroduced().booleanValue())
        {
            justContinue = Boolean.valueOf(false);
            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
            w.getCast()[0].printIntro(t, w);
        } else
        if(w.getCast()[1] != null)
        {
            if(!w.getCast()[1].isIntroduced().booleanValue())
            {
                justContinue = Boolean.valueOf(false);
                postScene = Boolean.valueOf(true);
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                w.getCast()[0].firstMeeting(t, w, w.getCast()[1]);
                p.removeAll();
                JButton Continue = new JButton("Continue");
                Continue.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        w.getCast()[1].printIntro(t, w);
                        JButton Continue2 = new JButton("Continue");
                        Continue2.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                Project.Downtime(t, p, f, w);
                            }

                            final _cls162 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls162.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                        });
                        p.add(Continue2);
                        p.validate();
                        p.repaint();
                    }

                    private final JPanel val$p;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JFrame val$f;

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                super();
            }
                });
                p.add(Continue);
                p.validate();
                p.repaint();
            } else
            if(w.getCast()[2] != null)
            {
                if(!w.getCast()[2].isIntroduced().booleanValue())
                {
                    justContinue = Boolean.valueOf(false);
                    postScene = Boolean.valueOf(true);
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    w.getCast()[2].firstTrio(t, w, w.getCast()[0], w.getCast()[1]);
                    p.removeAll();
                    JButton Continue = new JButton("Continue");
                    Continue.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            p.removeAll();
                            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                            w.getCast()[2].printIntro(t, w);
                            JButton ContinueTwo = new JButton("Continue");
                            ContinueTwo.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.Downtime(t, p, f, w);
                                }

                                final _cls163 this$1;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final WorldState val$w;

                    
                    {
                        this$1 = _cls163.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                            });
                            p.add(ContinueTwo);
                            p.validate();
                            p.repaint();
                        }

                        private final JPanel val$p;
                        private final WorldState val$w;
                        private final JTextPane val$t;
                        private final JFrame val$f;

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                super();
            }
                    });
                    p.add(Continue);
                    p.validate();
                    p.repaint();
                } else
                {
                    JButton lastContinue = new JButton("Continue");
                    if(w.getDay() == 15 - w.eventOffset && !w.loopComplete.booleanValue())
                    {
                        justContinue = Boolean.valueOf(false);
                        postScene = Boolean.valueOf(true);
                        if(w.getBreaks().length == 0)
                            InterviewChain(t, p, f, w);
                        else
                            lastContinue.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.InterviewChain(t, p, f, w);
                                }

                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
                            });
                    } else
                    if(w.getDay() == 30 - w.eventOffset * 2 && !w.loopComplete.booleanValue())
                    {
                        justContinue = Boolean.valueOf(false);
                        postScene = Boolean.valueOf(true);
                        if(w.getBreaks().length == 0)
                            VacationChain(t, p, f, w);
                        else
                            lastContinue.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.VacationChain(t, p, f, w);
                                }

                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
                            });
                    } else
                    if(w.getDay() == 45 - w.eventOffset * 3 && !w.loopComplete.booleanValue())
                    {
                        justContinue = Boolean.valueOf(false);
                        postScene = Boolean.valueOf(true);
                        if(w.getBreaks().length == 0)
                            DeploymentChain(t, p, f, w);
                        else
                            lastContinue.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.DeploymentChain(t, p, f, w);
                                }

                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
                            });
                    } else
                    if(chosenVignette >= 0 && w.getBreaks().length == 0)
                    {
                        justContinue = Boolean.valueOf(false);
                        if(w.getBreaks().length == 0)
                            w.showVignette(t, chosenVignette);
                        else
                            lastContinue.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.showVignette(t, chosenVignette);
                                }

                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final int val$chosenVignette;

            
            {
                w = worldstate;
                t = jtextpane;
                chosenVignette = i;
                super();
            }
                            });
                    } else
                    {
                        lastContinue.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                Project.Downtime(t, p, f, w);
                            }

                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
                        });
                    }
                    if(w.getBreaks().length > 0)
                    {
                        if(w.getBreaks().length > 1)
                            postScene = Boolean.valueOf(true);
                        justContinue = Boolean.valueOf(false);
                        SortBreaks(w);
                        HandleBreaks(t, p, f, w, lastContinue);
                    }
                }
            } else
            if(chosenVignette >= 0)
            {
                justContinue = Boolean.valueOf(false);
                w.showVignette(t, chosenVignette);
            }
        } else
        if(chosenVignette >= 0)
        {
            justContinue = Boolean.valueOf(false);
            w.showVignette(t, chosenVignette);
        }
        if(w.isTutorial().booleanValue())
        {
            p.removeAll();
            JButton Continue = new JButton("Continue");
            Continue.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    WorldState x = new WorldState();
                    x.copySettings(t, w);
                    x.copyToggles(w);
                    x.save = w.save;
                    Project.IntroOne(t, p, f, x);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
            });
            p.add(Continue);
            p.validate();
            p.repaint();
        } else
        if(justContinue.booleanValue())
            Downtime(t, p, f, w);
        else
        if(!postScene.booleanValue())
        {
            p.removeAll();
            JButton Continue = new JButton("Continue");
            Continue.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.Downtime(t, p, f, w);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
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
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
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

            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                order[3].deploymentTwo(t, w, order[4], order[5]);
                JButton ContinueTwo = new JButton("Continue");
                ContinueTwo.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        order[6].deploymentThree(t, w, order[7], order[8]);
                        JButton ContinueThree = new JButton("Continue");
                        ContinueThree.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                p.removeAll();
                                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                                Project.Downtime(t, p, f, w);
                            }

                            final _cls1 this$2;
                            private final JPanel val$p;
                            private final WorldState val$w;
                            private final JTextPane val$t;
                            private final JFrame val$f;

                        
                        {
                            this$2 = _cls1.this;
                            p = jpanel;
                            w = worldstate;
                            t = jtextpane;
                            f = jframe;
                            super();
                        }
                        });
                        p.add(ContinueThree);
                        p.validate();
                        p.repaint();
                    }

                    final _cls171 this$1;
                    private final JPanel val$p;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final Chosen val$order[];
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls171.this;
                        p = jpanel;
                        w = worldstate;
                        t = jtextpane;
                        order = achosen;
                        f = jframe;
                        super();
                    }
                });
                p.add(ContinueTwo);
                p.validate();
                p.repaint();
            }

            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final Chosen val$order[];
            private final JFrame val$f;

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                order = achosen;
                f = jframe;
                super();
            }
        });
        p.add(Continue);
        p.validate();
        p.repaint();
    }

    public static void VacationChain(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
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

            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                order[3].vacationTwo(t, w, order[4], order[5]);
                JButton ContinueTwo = new JButton("Continue");
                ContinueTwo.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        order[6].vacationThree(t, w, order[7], order[8]);
                        JButton ContinueThree = new JButton("Continue");
                        ContinueThree.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                p.removeAll();
                                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                                Project.Downtime(t, p, f, w);
                            }

                            final _cls1 this$2;
                            private final JPanel val$p;
                            private final WorldState val$w;
                            private final JTextPane val$t;
                            private final JFrame val$f;

                        
                        {
                            this$2 = _cls1.this;
                            p = jpanel;
                            w = worldstate;
                            t = jtextpane;
                            f = jframe;
                            super();
                        }
                        });
                        p.add(ContinueThree);
                        p.validate();
                        p.repaint();
                    }

                    final _cls172 this$1;
                    private final JPanel val$p;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final Chosen val$order[];
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls172.this;
                        p = jpanel;
                        w = worldstate;
                        t = jtextpane;
                        order = achosen;
                        f = jframe;
                        super();
                    }
                });
                p.add(ContinueTwo);
                p.validate();
                p.repaint();
            }

            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final Chosen val$order[];
            private final JFrame val$f;

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                order = achosen;
                f = jframe;
                super();
            }
        });
        p.add(Continue);
        p.validate();
        p.repaint();
    }

    public static void InterviewChain(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
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

            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                chosenTwo.interviewTwo(t, w, moral, innocent, confident, dignified);
                JButton ContinueTwo = new JButton("Continue");
                ContinueTwo.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        chosenThree.interviewThree(t, w, moral, innocent, confident, dignified);
                        JButton ContinueThree = new JButton("Continue");
                        ContinueThree.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                Project.Downtime(t, p, f, w);
                            }

                            final _cls1 this$2;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                        
                        {
                            this$2 = _cls1.this;
                            t = jtextpane;
                            p = jpanel;
                            f = jframe;
                            w = worldstate;
                            super();
                        }
                        });
                        p.add(ContinueThree);
                        p.validate();
                        p.repaint();
                    }

                    final _cls173 this$1;
                    private final JPanel val$p;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final Chosen val$chosenThree;
                    private final Chosen val$moral;
                    private final Chosen val$innocent;
                    private final Chosen val$confident;
                    private final Chosen val$dignified;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls173.this;
                        p = jpanel;
                        w = worldstate;
                        t = jtextpane;
                        chosenThree = chosen;
                        moral = chosen1;
                        innocent = chosen2;
                        confident = chosen3;
                        dignified = chosen4;
                        f = jframe;
                        super();
                    }
                });
                p.add(ContinueTwo);
                p.validate();
                p.repaint();
            }

            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final Chosen val$chosenTwo;
            private final Chosen val$moral;
            private final Chosen val$innocent;
            private final Chosen val$confident;
            private final Chosen val$dignified;
            private final Chosen val$chosenThree;
            private final JFrame val$f;

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                chosenTwo = chosen;
                moral = chosen1;
                innocent = chosen2;
                confident = chosen3;
                dignified = chosen4;
                chosenThree = chosen5;
                f = jframe;
                super();
            }
        });
        p.add(Continue);
        p.validate();
        p.repaint();
    }

    public static void HandleBreaks(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final JButton proceed)
    {
        p.removeAll();
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
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
            broken = null;
            if(sceneType == 16)
            {
                for(int i = 0; i < 3; i++)
                    if(w.getCast()[i].temptReq < 0x186a0L && !w.getCast()[i].pastTempted.booleanValue() && (w.getCast()[i].morality > 66 || w.getCast()[i].confidence > 66))
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
                    if(w.getCast()[i].dissociationReq < 10 && !w.getCast()[i].pastDissociated.booleanValue() && (w.getCast()[i].innocence > 66 || w.getCast()[i].dignity > 66))
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
            } else
            if(sceneType == 18)
            {
                for(int i = 0; i < 3; i++)
                    if(w.getCast()[i].negotiations > 0 && !w.getCast()[i].pastNegotiated.booleanValue() && (w.getCast()[i].morality > 66 || w.getCast()[i].dignity > 66))
                        broken = w.getCast()[i];

                if(broken == null)
                {
                    w.append(t, "(This should never be visible.)");
                    w.discardBreak();
                } else
                {
                    if(broken.morality > 66)
                    {
                        for(int i = 0; i < 3; i++)
                            if(w.getCast()[i].morality < 34)
                                if(c == null)
                                    c = w.getCast()[i];
                                else
                                    d = w.getCast()[i];

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

                public void actionPerformed(ActionEvent e)
                {
                    Project.HandleBreaks(t, p, f, w, proceed);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final JButton val$proceed;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                proceed = jbutton;
                super();
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
                if(w.getCast()[i].temptReq < 0x186a0L)
                    w.getCast()[i].pastTempted = Boolean.valueOf(true);
                else
                if(w.getCast()[i].dissociationReq < 10)
                    w.getCast()[i].pastDissociated = Boolean.valueOf(true);
                else
                if(w.getCast()[i].negotiations > 0)
                    w.getCast()[i].pastNegotiated = Boolean.valueOf(true);

        Forsaken exhaustedTest[] = new Forsaken[0];
        if(w.usedForsaken != null)
        {
            int actualCost = w.usedForsaken.motivationCost();
            if(w.usedForsaken.isFormerFriend(w.getCast()[0]).booleanValue() || w.usedForsaken.isFormerFriend(w.getCast()[1]).booleanValue() || w.usedForsaken.isFormerFriend(w.getCast()[2]).booleanValue())
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
        int totalActions = 24;
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

        while(highest > 0x9184e72a000L) 
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
            Boolean divided = Boolean.valueOf(true);
            if(fear == 0L && disg == 0L && pain == 0L && sham == 0L)
            {
                fear = w.getCast()[i].getTotalFEAR();
                disg = w.getCast()[i].getTotalDISG();
                pain = w.getCast()[i].getTotalPAIN();
                sham = w.getCast()[i].getTotalSHAM();
                angst *= divisor;
                divided = Boolean.valueOf(false);
            }
            actionWeights[i][0] = Long.valueOf(150L);
            actionWeights[i][1] = Long.valueOf(50L + (fear * 100L) / (long)(100 + w.getCast()[i].getMorality()));
            actionWeights[i][2] = Long.valueOf(50L + (disg * 100L) / (long)(100 + w.getCast()[i].getMorality()));
            actionWeights[i][3] = Long.valueOf(50L + (pain * 100L) / (long)(100 + w.getCast()[i].getMorality()));
            actionWeights[i][4] = Long.valueOf(50L + (sham * 100L) / (long)(100 + w.getCast()[i].getMorality()));
            long inhibition = (20000 * w.downtimeMultiplier) / 100;
            if(divided.booleanValue())
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
            inhibition = (0x3d0900L * (long)w.downtimeMultiplier) / 100L;
            if(divided.booleanValue())
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
            inhibition = (0x2540be400L * (long)w.downtimeMultiplier) / 100L;
            if(divided.booleanValue())
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
            inhibition = (0xb5e620f48000L * (long)w.downtimeMultiplier) / 100L;
            if(divided.booleanValue())
                inhibition /= divisor;
            if(w.getCast()[i].isImpregnated().booleanValue())
                actionWeights[i][17] = Long.valueOf((fear * 2000L + pain * 1000L + sham * 500L + angst * 250L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][17] = Long.valueOf(0L);
            if(w.getCast()[i].isHypnotized().booleanValue())
                actionWeights[i][18] = Long.valueOf((disg * 2000L + fear * 1000L + pain * 500L + angst * 250L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][18] = Long.valueOf(0L);
            if(w.getCast()[i].isDrained().booleanValue())
                actionWeights[i][19] = Long.valueOf((pain * 2000L + sham * 1000L + disg * 500L + angst * 250L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][19] = Long.valueOf(0L);
            if(w.getCast()[i].isParasitized().booleanValue())
                actionWeights[i][20] = Long.valueOf((sham * 2000L + disg * 1000L + fear * 500L + angst * 250L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition);
            else
                actionWeights[i][20] = Long.valueOf(0L);
            if(w.getCast()[i].betraying.booleanValue() && w.getCast()[i].temptReq < 0x186a0L)
            {
                actionWeights[i][21] = Long.valueOf(fear * 5L + disg * 5L + pain * 5L + sham * 5L + angst / 2L);
                if(divided.booleanValue())
                    actionWeights[i][21] = Long.valueOf(actionWeights[i][21].longValue() / divisor);
            } else
            {
                actionWeights[i][21] = Long.valueOf(0L);
            }
            if(w.getCast()[i].dissociated.booleanValue() && w.getCast()[i].dissociationReq < 10)
            {
                for(int j = 0; j < 22; j++)
                    actionWeights[i][j] = Long.valueOf(0L);

                actionWeights[i][22] = Long.valueOf(1000L);
            } else
            {
                actionWeights[i][22] = Long.valueOf(0L);
            }
            if(w.getCast()[i].truce.booleanValue() && w.getCast()[i].negotiations > 0)
            {
                for(int j = 0; j < 22; j++)
                    actionWeights[i][j] = Long.valueOf(0L);

                actionWeights[i][23] = Long.valueOf(1000L);
            } else
            {
                actionWeights[i][23] = Long.valueOf(0L);
            }
            w.getCast()[i].betraying = Boolean.valueOf(false);
            w.getCast()[i].dissociated = Boolean.valueOf(false);
            w.getCast()[i].truce = Boolean.valueOf(false);
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

        for(Boolean sorted = Boolean.valueOf(false); !sorted.booleanValue();)
        {
            sorted = Boolean.valueOf(true);
            for(int i = 0; i < totalActions - 1; i++)
                if(totalWeights[i] < totalWeights[i + 1])
                {
                    long storage = totalWeights[i];
                    totalWeights[i] = totalWeights[i + 1];
                    totalWeights[i + 1] = storage;
                    int storageTwo = testOrder[i];
                    testOrder[i] = testOrder[i + 1];
                    testOrder[i + 1] = storageTwo;
                    sorted = Boolean.valueOf(false);
                }

        }

        Boolean doubleFound = Boolean.valueOf(false);
        for(int i = 0; i < totalActions; i++)
        {
            int matches = 0;
            Boolean matching[] = {
                Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false)
            };
            if(w.getCast()[0] != null && combinedWeights[0][testOrder[i]] > actionWeights[0][chosenAction[0]].longValue())
            {
                matches++;
                matching[0] = Boolean.valueOf(true);
            }
            if(w.getCast()[1] != null && combinedWeights[1][testOrder[i]] > actionWeights[1][chosenAction[1]].longValue())
            {
                matches++;
                matching[1] = Boolean.valueOf(true);
            }
            if(w.getCast()[2] != null && combinedWeights[2][testOrder[i]] > actionWeights[2][chosenAction[2]].longValue())
            {
                matches++;
                matching[2] = Boolean.valueOf(true);
            }
            if(matches > 2)
            {
                chosenAction[0] = testOrder[i];
                chosenAction[1] = testOrder[i];
                chosenAction[2] = testOrder[i];
                i = totalActions;
            } else
            if(!doubleFound.booleanValue() && matches > 1)
            {
                for(int j = 0; j < 3; j++)
                    if(matching[j].booleanValue())
                        chosenAction[j] = testOrder[i];

                doubleFound = Boolean.valueOf(true);
            }
        }

        Boolean singleAction = Boolean.valueOf(true);
        if(w.loopComplete.booleanValue())
        {
            singleAction = Boolean.valueOf(false);
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
                singleAction = Boolean.valueOf(false);
                p.removeAll();
                JButton Continue = new JButton("Continue");
                Continue.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        w.getCast()[2].SingleDowntime(t, p, f, w, chosenAction[2]);
                        p.removeAll();
                        JButton Continue2 = new JButton("Continue");
                        Continue2.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                if(w.save != null && w.getHarem() != null && w.getHarem().length > 0)
                                    Project.ForsakenDowntime(t, p, f, w, w.save, exhausted);
                                else
                                    Project.Shop(t, p, f, w);
                            }

                            final _cls175 this$1;
                            private final WorldState val$w;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final Forsaken val$exhausted[];

                    
                    {
                        this$1 = _cls175.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        exhausted = aforsaken;
                        super();
                    }
                        });
                        p.add(Continue2);
                        p.validate();
                        p.repaint();
                    }

                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final int val$chosenAction[];
                    private final Forsaken val$exhausted[];

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                chosenAction = ai;
                exhausted = aforsaken;
                super();
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
            singleAction = Boolean.valueOf(false);
            p.removeAll();
            JButton Continue = new JButton("Continue");
            Continue.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    w.getCast()[1].SingleDowntime(t, p, f, w, chosenAction[1]);
                    p.removeAll();
                    JButton Continue2 = new JButton("Continue");
                    Continue2.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            if(w.save != null && w.getHarem() != null && w.getHarem().length > 0)
                                Project.ForsakenDowntime(t, p, f, w, w.save, exhausted);
                            else
                                Project.Shop(t, p, f, w);
                        }

                        final _cls176 this$1;
                        private final WorldState val$w;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final Forsaken val$exhausted[];

                    
                    {
                        this$1 = _cls176.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        exhausted = aforsaken;
                        super();
                    }
                    });
                    p.add(Continue2);
                    p.validate();
                    p.repaint();
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final int val$chosenAction[];
                private final Forsaken val$exhausted[];

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                chosenAction = ai;
                exhausted = aforsaken;
                super();
            }
            });
            p.add(Continue);
            p.validate();
            p.repaint();
        } else
        if(chosenAction[1] == chosenAction[2] && chosenAction[1] >= 0)
        {
            w.getCast()[0].SingleDowntime(t, p, f, w, chosenAction[0]);
            singleAction = Boolean.valueOf(false);
            p.removeAll();
            JButton Continue = new JButton("Continue");
            Continue.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    w.getCast()[1].DoubleDowntime(t, p, f, w, w.getCast()[2], chosenAction[1]);
                    p.removeAll();
                    JButton Continue2 = new JButton("Continue");
                    Continue2.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            if(w.save != null && w.getHarem() != null && w.getHarem().length > 0)
                                Project.ForsakenDowntime(t, p, f, w, w.save, exhausted);
                            else
                                Project.Shop(t, p, f, w);
                        }

                        final _cls177 this$1;
                        private final WorldState val$w;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final Forsaken val$exhausted[];

                    
                    {
                        this$1 = _cls177.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        exhausted = aforsaken;
                        super();
                    }
                    });
                    p.add(Continue2);
                    p.validate();
                    p.repaint();
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final int val$chosenAction[];
                private final Forsaken val$exhausted[];

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                chosenAction = ai;
                exhausted = aforsaken;
                super();
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
                singleAction = Boolean.valueOf(false);
                p.removeAll();
                JButton Continue = new JButton("Continue");
                Continue.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        w.getCast()[1].SingleDowntime(t, p, f, w, chosenAction[1]);
                        if(w.getCast()[2] != null)
                        {
                            p.removeAll();
                            JButton Continue2 = new JButton("Continue");
                            Continue2.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                                    w.getCast()[2].SingleDowntime(t, p, f, w, chosenAction[2]);
                                    p.removeAll();
                                    JButton Continue3 = new JButton("Continue");
                                    Continue3.addActionListener(new ActionListener() {

                                        public void actionPerformed(ActionEvent e)
                                        {
                                            if(w.save != null && w.getHarem() != null && w.getHarem().length > 0)
                                                Project.ForsakenDowntime(t, p, f, w, w.save, exhausted);
                                            else
                                                Project.Shop(t, p, f, w);
                                        }

                                        final _cls1 this$2;
                                        private final WorldState val$w;
                                        private final JTextPane val$t;
                                        private final JPanel val$p;
                                        private final JFrame val$f;
                                        private final Forsaken val$exhausted[];

                        
                        {
                            this$2 = _cls1.this;
                            w = worldstate;
                            t = jtextpane;
                            p = jpanel;
                            f = jframe;
                            exhausted = aforsaken;
                            super();
                        }
                                    });
                                    p.add(Continue3);
                                    p.validate();
                                    p.repaint();
                                }

                                final _cls178 this$1;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final int val$chosenAction[];
                                private final Forsaken val$exhausted[];

                    
                    {
                        this$1 = _cls178.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        chosenAction = ai;
                        exhausted = aforsaken;
                        super();
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

                                public void actionPerformed(ActionEvent e)
                                {
                                    if(w.save != null && w.getHarem() != null && w.getHarem().length > 0)
                                        Project.ForsakenDowntime(t, p, f, w, w.save, exhausted);
                                    else
                                        Project.Shop(t, p, f, w);
                                }

                                final _cls178 this$1;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final Forsaken val$exhausted[];

                    
                    {
                        this$1 = _cls178.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        exhausted = aforsaken;
                        super();
                    }
                            });
                            p.add(Continue4);
                            p.validate();
                            p.repaint();
                        }
                    }

                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final int val$chosenAction[];
                    private final Forsaken val$exhausted[];

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                chosenAction = ai;
                exhausted = aforsaken;
                super();
            }
                });
                p.add(Continue);
                p.validate();
                p.repaint();
            }
        }
        if(singleAction.booleanValue())
        {
            p.removeAll();
            JButton Continue = new JButton("Continue");
            Continue.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    if(!w.hardMode.booleanValue() && w.save != null && w.getHarem() != null && w.getHarem().length > 0)
                        Project.ForsakenDowntime(t, p, f, w, w.save, exhausted);
                    else
                        Project.Shop(t, p, f, w);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final Forsaken val$exhausted[];

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                exhausted = aforsaken;
                super();
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
                if(w.getCast()[i].temptReq < 0x186a0L)
                    w.getCast()[i].pastTempted = Boolean.valueOf(true);
                if(w.getCast()[i].dissociationReq < 10)
                    w.getCast()[i].pastDissociated = Boolean.valueOf(true);
            }

        w.active = Boolean.valueOf(true);
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
        File saveLocation = new File((new StringBuilder(String.valueOf(path))).append(File.separator).append("saves.sav").toString());
        if(saveLocation.exists())
        {
            ReadObject robj = new ReadObject();
            w.save = robj.deserializeSaveData((new StringBuilder(String.valueOf(path))).append(File.separator).append("saves.sav").toString());
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
            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
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
        if(w.campaign.booleanValue())
            w.append(t, (new StringBuilder(String.valueOf(w.cityName))).append(" - ").toString());
        clearPortraits();
        if(w.usedForsaken != null)
        {
            String nameDisplay[] = new String[5];
            nameDisplay[3] = w.usedForsaken.mainName;
            if(w.usedForsaken.flavorObedience() < 20)
                changePortrait(w.usedForsaken.gender, w.usedForsaken.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, 3, Emotion.ANGER, Emotion.NEUTRAL);
            else
            if(w.usedForsaken.flavorObedience() < 40)
                changePortrait(w.usedForsaken.gender, w.usedForsaken.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, 3, Emotion.ANGER, Emotion.SHAME);
            else
            if(w.usedForsaken.flavorObedience() < 61)
                changePortrait(w.usedForsaken.gender, w.usedForsaken.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, 3, Emotion.FEAR, Emotion.SHAME);
            else
            if(w.usedForsaken.flavorObedience() < 81)
                changePortrait(w.usedForsaken.gender, w.usedForsaken.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, 3, Emotion.FOCUS, Emotion.NEUTRAL);
            else
                changePortrait(w.usedForsaken.gender, w.usedForsaken.type, Boolean.valueOf(true), Boolean.valueOf(true), w, nameDisplay, 3, Emotion.JOY, Emotion.FOCUS);
        }
        w.append(t, (new StringBuilder("Day ")).append(w.getDay()).toString());
        if(w.clampPercent != 100)
            w.append(t, (new StringBuilder("\nDamage Mitigation: ")).append(100 - w.clampPercent).append("% per level").toString());
        if(w.eventOffset != 0)
            w.append(t, (new StringBuilder("\nPreparedness: Final Battle on Day ")).append(50 - w.eventOffset * 3).toString());
        if(w.downtimeMultiplier != 100)
            w.append(t, (new StringBuilder("\nLuxuries: ")).append(w.downtimeMultiplier).append("% Trauma resolution speed").toString());
        if(w.types[2] != null)
        {
            int superior = 0;
            for(int j = 0; j < 3; j++)
                if(w.types[j] == Chosen.Species.SUPERIOR)
                    superior++;

            w.append(t, (new StringBuilder("\nElites: ")).append(superior).append(" Superior Chosen").toString());
        }
        w.printShopTutorial(t);
        if(w.getCast()[1] != null)
            w.printGroupTutorial(t);
        if((w.getDay() > 50 - w.eventOffset * 3 || w.getEarlyCheat().booleanValue() || w.cheater.booleanValue()) && (!w.campaign.booleanValue() || w.getEarlyCheat().booleanValue()))
        {
            JButton Cheat = new JButton("Cheat");
            Cheat.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    if(w.isCheater().booleanValue())
                    {
                        Project.Cheat(t, p, f, w);
                    } else
                    {
                        p.removeAll();
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nActivating Cheat Mode will give you unlimited Evil Energy as well as other benefits").toString());
                        if(w.getDay() <= 35 && w.hardMode.booleanValue())
                            w.append(t, ", but you will not receive a score for the playthrough");
                        w.append(t, ".  Activate Cheat Mode?");
                        JButton Activate = new JButton("Activate Cheat Mode");
                        Activate.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                w.setCheater();
                                Project.Cheat(t, p, f, w);
                            }

                            final _cls180 this$1;
                            private final WorldState val$w;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;

                    
                    {
                        this$1 = _cls180.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                        });
                        p.add(Activate);
                        JButton Cancel = new JButton("Cancel");
                        Cancel.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                Project.Shop(t, p, f, w);
                            }

                            final _cls180 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls180.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                        });
                        p.add(Cancel);
                        p.validate();
                        p.repaint();
                    }
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
            });
            p.add(Cheat);
        }
        for(int i = 0; i < w.getTechs().length && !w.loopComplete.booleanValue(); i++)
            if(!w.getTechs()[i].isOwned().booleanValue())
            {
                Boolean shown = Boolean.valueOf(false);
                for(int j = 0; j < w.getTechs()[i].getPrereqs().length; j++)
                    if(w.getTechs()[i].getPrereqs()[j].isOwned().booleanValue())
                        shown = Boolean.valueOf(true);

                if(w.getTechs()[i].getPrereqs().length == 0)
                    shown = Boolean.valueOf(true);
                if(shown.booleanValue())
                {
                    w.append(t, "\n\n");
                    w.getTechs()[i].printSummary(w, t);
                    int ownedPrereqs = 0;
                    for(int j = 0; j < w.getTechs()[i].getPrereqs().length; j++)
                        if(w.getTechs()[i].getPrereqs()[j].isOwned().booleanValue())
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

                            public void actionPerformed(ActionEvent e)
                            {
                                p.removeAll();
                                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").append(w.getTechs()[thisTech].getName()).append(" costs ").append(w.getTechs()[thisTech].getCost()).append(" Evil Energy.  Will you develop it now?").toString());
                                JButton Confirm = new JButton("Confirm");
                                Confirm.addActionListener(new ActionListener() {

                                    public void actionPerformed(ActionEvent e)
                                    {
                                        w.getTechs()[thisTech].buy(w);
                                        Project.advanceDowntimeAction(p, w, thisTech);
                                        Project.Shop(t, p, f, w);
                                    }

                                    final _cls182 this$1;
                                    private final WorldState val$w;
                                    private final int val$thisTech;
                                    private final JPanel val$p;
                                    private final JTextPane val$t;
                                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls182.this;
                        w = worldstate;
                        thisTech = i;
                        p = jpanel;
                        t = jtextpane;
                        f = jframe;
                        super();
                    }
                                });
                                if(thisTech != 48 || w.getCast()[2] != null)
                                    p.add(Confirm);
                                else
                                if(thisTech == 48)
                                    w.append(t, "  (Forbidden until all three Chosen have been encountered.)");
                                JButton Cancel = new JButton("Cancel");
                                Cancel.addActionListener(new ActionListener() {

                                    public void actionPerformed(ActionEvent e)
                                    {
                                        Project.Shop(t, p, f, w);
                                    }

                                    final _cls182 this$1;
                                    private final JTextPane val$t;
                                    private final JPanel val$p;
                                    private final JFrame val$f;
                                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls182.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                                });
                                p.add(Cancel);
                                p.validate();
                                p.repaint();
                            }

                            private final JPanel val$p;
                            private final WorldState val$w;
                            private final JTextPane val$t;
                            private final int val$thisTech;
                            private final JFrame val$f;

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                thisTech = i;
                f = jframe;
                super();
            }
                        });
                        p.add(Buy);
                    }
                }
            }

        if(!w.loopComplete.booleanValue())
            w.append(t, (new StringBuilder("\n\nYou have ")).append(w.getEvilEnergy()).append(" Evil Energy.").toString());
        else
        if(w.day <= 50 - 3 * w.eventOffset)
        {
            w.append(t, (new StringBuilder("\n\n")).append(51 - w.day - 3 * w.eventOffset).append(" day").toString());
            if(51 - w.day - 3 * w.eventOffset != 1)
                w.append(t, "s remain ");
            else
                w.append(t, " remains ");
            w.append(t, "before your attack on the next city.");
        } else
        {
            w.append(t, "\n\nIt is time to choose your next destination.");
        }
        if(w.newAchievement().booleanValue())
            w.greenAppend(t, "\n\nYou have obtained a new Achievement!  See the Info menu for more details.");
        else
            w.printTip(t);
        JButton Profiles = new JButton("Info");
        Profiles.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.ShowInformation(t, p, f, w);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
        });
        for(int i = 0; i < 3 && w.getTechs()[0].isOwned().booleanValue(); i++)
            if(w.getCast()[i] != null && w.getCast()[i].negotiatePossible(w).booleanValue())
                Profiles.setBackground(PURPLISH);

        p.add(Profiles);
        if(w.getTechs()[3].isOwned().booleanValue() || w.truceEnforced().booleanValue())
        {
            JButton CustomBody = new JButton("Commander");
            CustomBody.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    if(!w.getBodyStatus()[0].booleanValue() && !w.hardMode.booleanValue() && w.save != null && w.getHarem() != null && w.getHarem().length > 0)
                        Project.ForsakenMenu(t, p, f, w, w.save, 0);
                    else
                        Project.Customize(t, p, f, w);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
            });
            if(w.loopComplete.booleanValue() || w.truceEnforced().booleanValue())
                CustomBody.setText("Forsaken");
            if(w.getHarem().length > 0 || !w.loopComplete.booleanValue() && !w.truceEnforced().booleanValue())
                p.add(CustomBody);
            else
            if(w.day != 51 - w.eventOffset * 3 || !w.campaign.booleanValue())
            {
                JButton Pass = new JButton("Pass Time");
                Pass.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.PostBattle(t, p, f, w);
                    }

                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
                });
                p.add(Pass);
            }
            if(!w.getBodyStatus()[0].booleanValue() && w.usedForsaken == null && w.getEvilEnergy() > 0 && !w.loopComplete.booleanValue())
                CustomBody.setBackground(Color.YELLOW);
        }
        JButton Data = new JButton("Data");
        Data.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nSelect an option.").toString());
                p.removeAll();
                JButton NewSave = new JButton("New Save File");
                NewSave.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Data(t, p, f, w, "newsave", 0, Boolean.valueOf(true));
                    }

                    final _cls186 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls186.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                });
                p.add(NewSave);
                JButton Overwrite = new JButton("Overwrite Save");
                Overwrite.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Data(t, p, f, w, "overwrite", 0, Boolean.valueOf(true));
                    }

                    final _cls186 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls186.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                });
                p.add(Overwrite);
                JButton Load = new JButton("Load");
                Load.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Data(t, p, f, w, "load", 0, Boolean.valueOf(true));
                    }

                    final _cls186 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls186.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                });
                p.add(Load);
                JButton Delete = new JButton("Delete");
                Delete.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Data(t, p, f, w, "delete", 0, Boolean.valueOf(true));
                    }

                    final _cls186 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls186.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                });
                p.add(Delete);
                JButton Import = new JButton("Import");
                Import.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Data(t, p, f, w, "import", 0, Boolean.valueOf(true));
                    }

                    final _cls186 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls186.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                });
                p.add(Import);
                JButton Export = new JButton("Export");
                Export.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Data(t, p, f, w, "export", 0, Boolean.valueOf(true));
                    }

                    final _cls186 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls186.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                });
                p.add(Export);
                JButton Back = new JButton("Back");
                Back.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Shop(t, p, f, w);
                    }

                    final _cls186 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls186.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                });
                p.add(Back);
                p.validate();
                p.repaint();
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(Data);
        JButton Quit = new JButton("Quit");
        Quit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nReally quit?  Current progress will not be saved.").toString());
                p.removeAll();
                JButton ReallyQuit = new JButton("Quit to main menu");
                ReallyQuit.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        WorldState x = new WorldState();
                        x.copySettings(t, w);
                        x.copyToggles(w);
                        x.save = w.save;
                        Project.IntroOne(t, p, f, x);
                    }

                    final _cls187 this$1;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls187.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                });
                p.add(ReallyQuit);
                JButton Back = new JButton("Back");
                Back.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Shop(t, p, f, w);
                    }

                    final _cls187 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls187.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                });
                p.add(Back);
                p.validate();
                p.repaint();
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(Quit);
        JButton NextBattle = new JButton("Next Battle");
        NextBattle.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                if(w.getCast()[1] == null)
                    Project.ConfirmBattle(t, p, f, w, w.getCast()[0]);
                else
                    Project.pickStartingTarget(t, p, f, w);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        if(!w.loopComplete.booleanValue() && !w.truceEnforced().booleanValue())
            p.add(NextBattle);
        else
        if(w.day > 50 - w.eventOffset * 3)
        {
            JButton NextCity = new JButton("Next City");
            NextCity.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.PickNextCity(t, p, f, w);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
            });
            p.add(NextCity);
        }
        if(w.writePossible().booleanValue())
            addWriteButton(p, w);
        p.validate();
        p.repaint();
        w.readCommentary(t);
    }

    public static void PickNextCity(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        p.removeAll();
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhich city will you attack next?").toString());
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
                w.nextCities[i].active = Boolean.valueOf(true);
                w.nextCities[i].campaign = Boolean.valueOf(true);
                w.nextCities[i].loops = w.loops + 1;
                w.nextCities[i].cityName = w.nextCities[i].getCityName(w.loops * 2 + i + 1);
                w.nextCities[i].earlyCheat = w.earlyCheat;
                w.nextCities[i].hardMode = Boolean.valueOf(false);
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
                        if(!w.earlyCheat.booleanValue())
                            clampRemoval += increase * 15;
                        difficultyScore += increase;
                    } else
                    if(difficultyType < 0.61111111111111116D)
                    {
                        if(difficultyScore <= w.nextCities[i].loops * 10 - 3 && w.nextCities[i].eventOffset < 10)
                        {
                            if(!w.earlyCheat.booleanValue())
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
                        for(; increase > 0 && !w.earlyCheat.booleanValue(); increase--)
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
                if(i == 1 && !w.earlyCheat.booleanValue())
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
                    Boolean sameElites = Boolean.valueOf(true);
                    Boolean noElites = Boolean.valueOf(true);
                    for(int j = 0; j < 3; j++)
                    {
                        if(w.nextCities[0].types[j] != w.nextCities[1].types[j] && (w.nextCities[0].types[j] == null || w.nextCities[1].types[j] == null))
                        {
                            sameElites = Boolean.valueOf(false);
                            differences += 11;
                        }
                        if(w.nextCities[0].types[j] != null || w.nextCities[1].types[j] != null)
                            noElites = Boolean.valueOf(false);
                    }

                    if(differences < requirement || sameElites.booleanValue() && !noElites.booleanValue())
                        i = -1;
                }
            }

        }
        for(int i = 0; i < w.nextCities.length; i++)
        {
            w.append(t, "\n\n");
            w.underlineAppend(t, w.nextCities[i].cityName);
            if(w.nextCities[i].clampPercent != 100)
                w.append(t, (new StringBuilder("\nDamage Mitigation: ")).append(100 - w.nextCities[i].clampPercent).append("% per level").toString());
            if(w.nextCities[i].eventOffset != 0)
                w.append(t, (new StringBuilder("\nPreparedness: Final Battle on Day ")).append(50 - w.nextCities[i].eventOffset * 3).toString());
            if(w.nextCities[i].downtimeMultiplier != 100)
                w.append(t, (new StringBuilder("\nLuxuries: ")).append(w.nextCities[i].downtimeMultiplier).append("% Trauma resolution speed").toString());
            if(w.nextCities[i].types[2] != null)
            {
                int superior = 0;
                for(int j = 0; j < 3; j++)
                    if(w.nextCities[i].types[j] == Chosen.Species.SUPERIOR)
                        superior++;

                w.append(t, (new StringBuilder("\nElites: ")).append(superior).append(" Superior Chosen").toString());
            }
            JButton ThisOne = new JButton(w.nextCities[i].cityName);
            final WorldState pickedWorld = w.nextCities[i];
            ThisOne.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    p.removeAll();
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").append(pickedWorld.cityName).append(" will be targeted.  Are you sure?").toString());
                    JButton Confirm = new JButton("Confirm");
                    Confirm.addActionListener(new ActionListener() {

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

                        final _cls190 this$1;
                        private final WorldState val$pickedWorld;
                        private final WorldState val$w;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;

                    
                    {
                        this$1 = _cls190.this;
                        pickedWorld = worldstate;
                        w = worldstate1;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                    });
                    p.add(Confirm);
                    JButton Cancel = new JButton("Cancel");
                    Cancel.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            Project.PickNextCity(t, p, f, w);
                        }

                        final _cls190 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;

                    
                    {
                        this$1 = _cls190.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                    });
                    p.add(Cancel);
                    p.validate();
                    p.repaint();
                }

                private final JPanel val$p;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final WorldState val$pickedWorld;
                private final JFrame val$f;

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                pickedWorld = worldstate1;
                f = jframe;
                super();
            }
            });
            p.add(ThisOne);
        }

        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.Shop(t, p, f, w);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void ShowInformation(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        p.removeAll();
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
        if(w.getTechs()[0].isOwned().booleanValue() && !w.loopComplete.booleanValue())
        {
            w.append(t, "\n\nOverall corruption progress:");
            int longest = 3;
            for(int i = 0; i < 3; i++)
                if(w.getCast()[i] != null && w.getCast()[i].getMainName().length() > longest)
                    longest = w.getCast()[i].getMainName().length();

            for(int i = 0; i < 3; i++)
                if(w.getCast()[i] != null)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getCast()[i].getMainName()).toString());
                    for(int j = w.getCast()[i].getMainName().length(); j < longest; j++)
                        w.append(t, " ");

                    String gap = "";
                    for(int j = 0; j < longest - 3; j++)
                        gap = (new StringBuilder(String.valueOf(gap))).append(" ").toString();

                    w.append(t, (new StringBuilder("  +2 T1 T2 T3 T4\nMOR")).append(gap).append(" ").toString());
                    if(w.getCast()[i].getMorality() > 66)
                    {
                        w.append(t, "[");
                        if(!w.getCast()[i].bonusHATE.booleanValue())
                            w.append(t, "X");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                    } else
                    {
                        w.append(t, "   [");
                    }
                    if(w.getCast()[i].temptReq == 0x186a0L)
                    {
                        if(w.getCast()[i].isRuthless())
                            w.append(t, "X");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                        if(w.getCast()[i].negotiations == 0)
                        {
                            if(!w.getCast()[i].isVVirg())
                                w.append(t, "X");
                            else
                                w.append(t, " ");
                            w.append(t, "][");
                            if(w.getCast()[i].timesSlaughtered() > 0)
                                w.append(t, "X");
                            else
                            if(w.getCast()[i].usingSlaughter.booleanValue())
                                w.append(t, "/");
                            else
                                w.append(t, " ");
                            w.append(t, "][");
                            if(w.getCast()[i].isImpregnated().booleanValue())
                                w.append(t, "X");
                            else
                            if(w.getCast()[i].getImpregnationEffectiveness() >= w.getCast()[i].impregnationReq())
                                w.append(t, "/");
                            else
                                w.append(t, " ");
                        } else
                        {
                            w.append(t, "~][~][~");
                        }
                    } else
                    {
                        w.append(t, "~][~][~][~");
                    }
                    w.append(t, "]");
                    if(w.getCast()[i].getImpregnationEffectiveness() > 100)
                    {
                        if(w.getCast()[i].getImpregnationEffectiveness() < 1000)
                            w.append(t, " ");
                        w.append(t, (new StringBuilder(" ")).append(w.getCast()[i].getImpregnationEffectiveness()).append("%").toString());
                    }
                    w.append(t, (new StringBuilder("\nINN")).append(gap).append(" ").toString());
                    if(w.getCast()[i].getInnocence() > 66)
                    {
                        w.append(t, "[");
                        if(!w.getCast()[i].bonusPLEA.booleanValue())
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
                        if(w.getCast()[i].usingFantasize.booleanValue())
                            w.append(t, "/");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                        if(w.getCast()[i].isHypnotized().booleanValue())
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
                        w.append(t, (new StringBuilder(" ")).append(w.getCast()[i].getHypnosisEffectiveness()).append("%").toString());
                    }
                    w.append(t, (new StringBuilder("\nCON")).append(gap).append(" ").toString());
                    if(w.getCast()[i].getConfidence() > 66)
                    {
                        w.append(t, "[");
                        if(!w.getCast()[i].bonusINJU.booleanValue())
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
                    if(w.getCast()[i].temptReq == 0x186a0L)
                    {
                        if(!w.getCast()[i].isAVirg())
                            w.append(t, "X");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                        if(w.getCast()[i].timesDetonated() > 0)
                            w.append(t, "X");
                        else
                        if(w.getCast()[i].usingDetonate.booleanValue())
                            w.append(t, "/");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                        if(w.getCast()[i].isDrained().booleanValue())
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
                        w.append(t, (new StringBuilder(" ")).append(w.getCast()[i].getDrainEffectiveness()).append("%").toString());
                    }
                    w.append(t, (new StringBuilder("\nDIG")).append(gap).append(" ").toString());
                    if(w.getCast()[i].getDignity() > 66)
                    {
                        w.append(t, "[");
                        if(!w.getCast()[i].bonusEXPO.booleanValue())
                            w.append(t, "X");
                        else
                            w.append(t, " ");
                        w.append(t, "][");
                    } else
                    {
                        w.append(t, "   [");
                    }
                    if(w.getCast()[i].negotiations == 0)
                    {
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
                            if(w.getCast()[i].usingStrip.booleanValue())
                                w.append(t, "/");
                            else
                                w.append(t, " ");
                            w.append(t, "][");
                            if(w.getCast()[i].isParasitized().booleanValue())
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
                    } else
                    {
                        w.append(t, "~][~][~][~");
                    }
                    w.append(t, "]");
                    if(w.getCast()[i].getParasitismEffectiveness() > 100)
                    {
                        if(w.getCast()[i].getParasitismEffectiveness() < 1000)
                            w.append(t, " ");
                        w.append(t, (new StringBuilder(" ")).append(w.getCast()[i].getParasitismEffectiveness()).append("%").toString());
                    }
                }

            for(int i = 0; i < 3 && !w.loopComplete.booleanValue(); i++)
                if(w.getCast()[i] != null)
                {
                    final int thisChosen = i;
                    JButton openProfile = new JButton((new StringBuilder(String.valueOf(w.getCast()[i].getMainName()))).append("'s Profile").toString());
                    openProfile.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            p.removeAll();
                            Project.clearPortraits();
                            String as[] = new String[5];
                            as[0] = w.getCast()[thisChosen].mainName;
                            Project.changePortrait(w.getCast()[thisChosen].convertGender(), w.getCast()[thisChosen].type, Boolean.valueOf(false), Boolean.valueOf(false), w, as, 0, Emotion.NEUTRAL, Emotion.NEUTRAL);
                            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                            w.getCast()[thisChosen].printIntro(t, w);
                            w.getCast()[thisChosen].printProfile(t, p, f, w);
                            JButton Approach = new JButton("Approach");
                            Approach.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    Body newParticipants[] = new Body[1];
                                    newParticipants[0] = new Body(w.getCast()[thisChosen]);
                                    newParticipants[0].imprisoned = Boolean.valueOf(false);
                                    Project.SelectBody(t, p, f, w, w.save, newParticipants);
                                }

                                final _cls192 this$1;
                                private final WorldState val$w;
                                private final int val$thisChosen;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls192.this;
                        w = worldstate;
                        thisChosen = i;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            if(w.day > 1 && !w.getCast()[thisChosen].visited.booleanValue())
                            {
                                if(w.getCast()[thisChosen].negotiatePossible(w).booleanValue())
                                    Approach.setBackground(Project.PURPLISH);
                                p.add(Approach);
                            }
                            JButton Continue = new JButton("Back");
                            Continue.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    Project.ShowInformation(t, p, f, w);
                                }

                                final _cls192 this$1;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final WorldState val$w;

                    
                    {
                        this$1 = _cls192.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                            });
                            p.add(Continue);
                            p.validate();
                            p.repaint();
                        }

                        private final JPanel val$p;
                        private final WorldState val$w;
                        private final int val$thisChosen;
                        private final JTextPane val$t;
                        private final JFrame val$f;

            
            {
                p = jpanel;
                w = worldstate;
                thisChosen = i;
                t = jtextpane;
                f = jframe;
                super();
            }
                    });
                    if(w.getCast()[i].negotiatePossible(w).booleanValue())
                        openProfile.setBackground(PURPLISH);
                    p.add(openProfile);
                }

        }
        w.append(t, "\n\nWhich information do you want to view?");
        JButton Statistics = new JButton("Statistics");
        Statistics.addActionListener(new ActionListener() {

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
                for(names[0] = w.getCast()[0].getMainName(); names[0].length() < highest; names[0] = (new StringBuilder(String.valueOf(names[0]))).append(" ").toString());
                if(w.getCast()[1] != null)
                    for(names[1] = w.getCast()[1].getMainName(); names[1].length() < highest; names[1] = (new StringBuilder(String.valueOf(names[1]))).append(" ").toString());
                if(w.getCast()[2] != null)
                    for(names[2] = w.getCast()[2].getMainName(); names[2].length() < highest; names[2] = (new StringBuilder(String.valueOf(names[2]))).append(" ").toString());
                String totals;
                for(totals = "All"; totals.length() < highest; totals = (new StringBuilder(String.valueOf(totals))).append(" ").toString());
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nOpening Levels Taken:\n\n").toString());
                for(int spaces = highest; spaces > 0; spaces--)
                    w.append(t, " ");

                if(w.tickle().booleanValue())
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
                        w.append(t, (new StringBuilder("\n")).append(names[i]).append(" ").append(w.getCast()[i].fixedFormat(w.getCast()[i].getFEARopenings())).append(" ").append(w.getCast()[i].fixedFormat(w.getCast()[i].getDISGopenings())).append(" ").append(w.getCast()[i].fixedFormat(w.getCast()[i].getPAINopenings())).append(" ").append(w.getCast()[i].fixedFormat(w.getCast()[i].getSHAMopenings())).append(" ").append(w.getCast()[i].fixedFormat(w.getCast()[i].getFEARopenings() + w.getCast()[i].getDISGopenings() + w.getCast()[i].getPAINopenings() + w.getCast()[i].getSHAMopenings())).toString());
                        totalFEAR += w.getCast()[i].getFEARopenings();
                        totalDISG += w.getCast()[i].getDISGopenings();
                        totalPAIN += w.getCast()[i].getPAINopenings();
                        totalSHAM += w.getCast()[i].getSHAMopenings();
                    }

                w.append(t, (new StringBuilder("\n")).append(totals).append(" ").append(w.getCast()[0].fixedFormat(totalFEAR)).append(" ").append(w.getCast()[0].fixedFormat(totalDISG)).append(" ").append(w.getCast()[0].fixedFormat(totalPAIN)).append(" ").append(w.getCast()[0].fixedFormat(totalSHAM)).append(" ").append(w.getCast()[0].fixedFormat(totalFEAR + totalDISG + totalPAIN + totalSHAM)).toString());
            }

            private final WorldState val$w;
            private final JTextPane val$t;

            
            {
                w = worldstate;
                t = jtextpane;
                super();
            }
        });
        p.add(Statistics);
        JButton Upgrades = new JButton("View All Upgrades");
        Upgrades.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nSelect an upgrade to view.").toString());
                Project.ViewUpgrades(t, p, f, w, 0);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(Upgrades);
        JButton Achievements = new JButton("Achievements");
        Achievements.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.ViewAchievements(t, p, f, w, 0);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
        });
        if(w.campaign.booleanValue())
            p.add(Achievements);
        if(w.newAchievement().booleanValue())
            Achievements.setBackground(new Color(255, 225, 125));
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.Shop(t, p, f, w);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void ViewAchievements(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final int page)
    {
        p.removeAll();
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
        if(page > 0)
        {
            JButton PreviousPage = new JButton("Previous Page");
            PreviousPage.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.ViewAchievements(t, p, f, w, page - 1);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final int val$page;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                page = i;
                super();
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
                description = (new StringBuilder(String.valueOf(description))).append("Forsaken Sacrificed: ").append(w.achievementHeld(0)[1]).append("\n").toString();
                description = (new StringBuilder(String.valueOf(description))).append("Level: ").append(w.achievementHeld(0)[0]).toString();
                if(w.achievementHeld(0)[0] == 0)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 1 sacrifice)\nBonus: N/A").toString();
                else
                if(w.achievementHeld(0)[0] == 1)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 3 sacrifices)\nBonus: +1 Starting EE").toString();
                else
                if(w.achievementHeld(0)[0] == 2)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 6 sacrifices)\nBonus: +2 Starting EE").toString();
                else
                if(w.achievementHeld(0)[0] == 3)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 15 sacrifices)\nBonus: +3 Starting EE").toString();
                else
                if(w.achievementHeld(0)[0] == 4)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 60 sacrifices)\nBonus: +4 Starting EE").toString();
                else
                    description = (new StringBuilder(String.valueOf(description))).append("\nBonus: +5 Starting EE").toString();
                description = (new StringBuilder(String.valueOf(description))).append("\nThe supernaturally-enhanced bodies of former Chosen make for excellent breeding stock.  This role prevents them from fighting in battle, but it can give you a head start in establishing new bases of operations.  And they tend to start enjoying it before too long.").toString();
            } else
            if(i == 1)
            {
                w.underlineAppend(t, "Impregnation Specialty");
                description = (new StringBuilder(String.valueOf(description))).append("Chosen Impregnated: ").append(w.achievementHeld(i)[1]).append("\n").toString();
                description = (new StringBuilder(String.valueOf(description))).append("Level: ").append(w.achievementHeld(i)[0]).toString();
                if(w.achievementHeld(i)[0] == 0)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 4 impregnated)\nBonus: N/A").toString();
                else
                if(w.achievementHeld(i)[0] == 1)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 10 impregnated)\nBonus: -200% Impregnation Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 2)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 25 impregnated)\nBonus: -400% Impregnation Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 3)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 60 impregnated)\nBonus: -600% Impregnation Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 4)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 160 impregnated)\nBonus: -700% Impregnation Threshold").toString();
                else
                    description = (new StringBuilder(String.valueOf(description))).append("\nBonus: -750% Impregnation Threshold").toString();
                description = (new StringBuilder(String.valueOf(description))).append("\nAs the Chosen hear rumors that you're able to impregnate even them, their lack of faith in their own protections will cause it to become even easier to do so.").toString();
            } else
            if(i == 2)
            {
                w.underlineAppend(t, "Hypnosis Specialty");
                description = (new StringBuilder(String.valueOf(description))).append("Chosen Hypnotized: ").append(w.achievementHeld(i)[1]).append("\n").toString();
                description = (new StringBuilder(String.valueOf(description))).append("Level: ").append(w.achievementHeld(i)[0]).toString();
                if(w.achievementHeld(i)[0] == 0)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 4 hypnotized)\nBonus: N/A").toString();
                else
                if(w.achievementHeld(i)[0] == 1)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 10 hypnotized)\nBonus: -200% Hypnosis Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 2)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 25 hypnotized)\nBonus: -400% Hypnosis Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 3)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 60 hypnotized)\nBonus: -600% Hypnosis Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 4)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 160 hypnotized)\nBonus: -700% Hypnosis Threshold").toString();
                else
                    description = (new StringBuilder(String.valueOf(description))).append("\nBonus: -750% Hypnosis Threshold").toString();
                description = (new StringBuilder(String.valueOf(description))).append("\nMuch of the difficulty in Demonic Hypnosis comes from finding exploitable weaknesses in the target's thought process.  But all human minds share some similarities, and the more you break, the more tricks you figure out.").toString();
            } else
            if(i == 3)
            {
                w.underlineAppend(t, "Drain Specialty");
                description = (new StringBuilder(String.valueOf(description))).append("Chosen Drained: ").append(w.achievementHeld(i)[1]).append("\n").toString();
                description = (new StringBuilder(String.valueOf(description))).append("Level: ").append(w.achievementHeld(i)[0]).toString();
                if(w.achievementHeld(i)[0] == 0)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 4 drained)\nBonus: N/A").toString();
                else
                if(w.achievementHeld(i)[0] == 1)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 10 drained)\nBonus: -200% Drain Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 2)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 25 drained)\nBonus: -400% Drain Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 3)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 60 drained)\nBonus: -600% Drain Threshold\n").toString();
                else
                if(w.achievementHeld(i)[0] == 4)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 160 drained)\nBonus: -700% Drain Threshold").toString();
                else
                    description = (new StringBuilder(String.valueOf(description))).append("\nBonus: -750% Drain Threshold").toString();
                description = (new StringBuilder(String.valueOf(description))).append("\nThe Holy Energy which empowers the Chosen is inherently difficult for a Demon to absorb, but whenever you do successfully begin draining energy from one of the Chosen, her aura mingles with your own, and you find it easier to draw more of their energy into yourself.").toString();
            } else
            if(i == 4)
            {
                w.underlineAppend(t, "Parasitism Specialty");
                description = (new StringBuilder(String.valueOf(description))).append("Chosen Parasitized: ").append(w.achievementHeld(i)[1]).append("\n").toString();
                description = (new StringBuilder(String.valueOf(description))).append("Level: ").append(w.achievementHeld(i)[0]).toString();
                if(w.achievementHeld(i)[0] == 0)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 4 parasitized)\nBonus: N/A").toString();
                else
                if(w.achievementHeld(i)[0] == 1)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 10 parasitized)\nBonus: -200% Parasitism Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 2)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 25 parasitized)\nBonus: -400% Parasitism Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 3)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 60 parasitized)\nBonus: -600% Parasitism Threshold").toString();
                else
                if(w.achievementHeld(i)[0] == 4)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 160 parasitized)\nBonus: -700% Parasitism Threshold").toString();
                else
                    description = (new StringBuilder(String.valueOf(description))).append("\nBonus: -750% Parasitism Threshold").toString();
                description = (new StringBuilder(String.valueOf(description))).append("\nThe public loves to see the Chosen humiliated, and as it becomes more common for their transformations to become corrupted by you, everyone's anticipation for the next such corruption will do much of the work for you.").toString();
            } else
            if(i == 5)
            {
                w.underlineAppend(t, "Tempting");
                description = (new StringBuilder(String.valueOf(description))).append("Chosen Tempted: ").append(w.achievementHeld(i)[1]).append("\n").toString();
                description = (new StringBuilder(String.valueOf(description))).append("Level: ").append(w.achievementHeld(i)[0]).toString();
                if(w.achievementHeld(i)[0] == 0)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 2 Tempted)\nBonus: N/A").toString();
                else
                if(w.achievementHeld(i)[0] == 1)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 5 Tempted)\nBonus: Tempt requirement decreases 15% per use").toString();
                else
                if(w.achievementHeld(i)[0] == 2)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 12 Tempted)\nBonus: Tempt requirement decreases 20% per use").toString();
                else
                if(w.achievementHeld(i)[0] == 3)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 30 Tempted)\nBonus: Tempt requirement decreases 25% per use").toString();
                else
                if(w.achievementHeld(i)[0] == 4)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 80 Tempted)\nBonus: Tempt requirement decreases 30% per use").toString();
                else
                    description = (new StringBuilder(String.valueOf(description))).append("\nBonus: Tempt requirement decreases 35% per use").toString();
                description = (new StringBuilder(String.valueOf(description))).append("\nThe Chosen are carefully guided by their handlers and by society at large so that they don't even consider the possibility of turning to the side of the Demons.  But the more they see other Chosen being treated kindly by the Thralls, the more willing they'll be to think of you as a potential ally.").toString();
            } else
            if(i == 6)
            {
                w.underlineAppend(t, "Mindbreaker");
                description = (new StringBuilder(String.valueOf(description))).append("Chosen with Aversion: ").append(w.achievementHeld(i)[1]).append("\n").toString();
                description = (new StringBuilder(String.valueOf(description))).append("Level: ").append(w.achievementHeld(i)[0]).toString();
                if(w.achievementHeld(i)[0] == 0)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 2 with Aversion)\nBonus: N/A").toString();
                else
                if(w.achievementHeld(i)[0] == 1)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 5 with Aversion)\nBonus: Aversion requirement decreases 2 rounds per use").toString();
                else
                if(w.achievementHeld(i)[0] == 2)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 12 with Aversion)\nBonus: Aversion requirement decreases 3 rounds per use").toString();
                else
                if(w.achievementHeld(i)[0] == 3)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 30 with Aversion)\nBonus: Aversion requirement decreases 4 rounds per use").toString();
                else
                if(w.achievementHeld(i)[0] == 4)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 80 with Aversion)\nBonus: Aversion requirement decreases 5 rounds per use").toString();
                else
                    description = (new StringBuilder(String.valueOf(description))).append("\nBonus: Aversion requirement decreases 6 rounds per use").toString();
                description = (new StringBuilder(String.valueOf(description))).append("\nThe Thralls you bring with you from city to city will gradually build their skills as they learn how to cause as much suffering as possible to their victims.  Every Chosen mindbroken is a useful example of what techniques work best.").toString();
            } else
            if(i == 7)
            {
                w.underlineAppend(t, "Persuasive");
                description = (new StringBuilder(String.valueOf(description))).append("Chosen successfully Negotiated with: ").append(w.achievementHeld(i)[1]).append("\n").toString();
                description = (new StringBuilder(String.valueOf(description))).append("Level: ").append(w.achievementHeld(i)[0]).toString();
                if(w.achievementHeld(i)[0] == 0)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 2 Negotiated)\nBonus: N/A").toString();
                else
                if(w.achievementHeld(i)[0] == 1)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 5 Negotiated)\nBonus: Appeal deals double Resolve damage").toString();
                else
                if(w.achievementHeld(i)[0] == 2)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 12 Negotiated)\nBonus: Appeal deals triple Resolve damage").toString();
                else
                if(w.achievementHeld(i)[0] == 3)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 30 Negotiated)\nBonus: Appeal deals quadruple Resolve damage").toString();
                else
                if(w.achievementHeld(i)[0] == 4)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 80 Negotiated)\nBonus: Appeal deals quintuple Resolve damage").toString();
                else
                    description = (new StringBuilder(String.valueOf(description))).append("\nBonus: Appeal deals sextuple Resolve damage").toString();
                description = (new StringBuilder(String.valueOf(description))).append("\nA reputation for being reasonable can also be useful for a Demon Lord.  As word spreads that Chosen can influence you by more peaceful methods, the idea of fighting will become less attractive.").toString();
            } else
            if(i == 8)
            {
                w.underlineAppend(t, "Heroine Hunter");
                description = (new StringBuilder(String.valueOf(description))).append("Superior Chosen Broken: ").append(w.achievementHeld(i)[1]).append("\n").toString();
                description = (new StringBuilder(String.valueOf(description))).append("Level: ").append(w.achievementHeld(i)[0]).toString();
                if(w.achievementHeld(i)[0] == 0)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 1 Broken)\nBonus: N/A").toString();
                else
                if(w.achievementHeld(i)[0] == 1)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 3 Broken)\nBonus: Slight increase to Resolve damage").toString();
                else
                if(w.achievementHeld(i)[0] == 2)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 6 Broken)\nBonus: Notable increase to Resolve damage").toString();
                else
                if(w.achievementHeld(i)[0] == 3)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 15 Broken)\nBonus: Moderate increase to Resolve damage").toString();
                else
                if(w.achievementHeld(i)[0] == 4)
                    description = (new StringBuilder(String.valueOf(description))).append(" (Next: 40 Broken)\nBonus: Large increase to Resolve damage").toString();
                else
                    description = (new StringBuilder(String.valueOf(description))).append("\nBonus: Extreme increase to Resolve damage").toString();
                description = (new StringBuilder(String.valueOf(description))).append("\nThe public may not know the difference, but the Chosen themselves are keenly aware that some of their number are far more competent than others.  As you prove that you can convert even the best of them to the Demonic cause, they'll all lose hope of ever winning against you.").toString();
            }
            if(w.achievementHeld(i)[0] > w.achievementSeen[i])
            {
                w.achievementSeen[i] = w.achievementHeld(i)[0];
                w.greenAppend(t, (new StringBuilder("\n")).append(description).toString());
            } else
            if(w.achievementHeld(i)[0] > 0)
                w.append(t, (new StringBuilder("\n")).append(description).toString());
            else
                w.grayAppend(t, (new StringBuilder("\n")).append(description).toString());
        }

        if(page < (w.achievementSeen.length - 1) / 5)
        {
            JButton NextPage = new JButton("Next Page");
            NextPage.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.ViewAchievements(t, p, f, w, page + 1);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final int val$page;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                page = i;
                super();
            }
            });
            p.add(NextPage);
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.ShowInformation(t, p, f, w);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
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

                public void actionPerformed(ActionEvent e)
                {
                    Project.ViewUpgrades(t, p, f, w, page - 1);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final int val$page;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                page = i;
                super();
            }
            });
            p.add(Previous);
        }
        for(int i = page * 5; i < w.getTechs().length && i < page * 5 + 5; i++)
        {
            final int id = i;
            JButton Upgrade = new JButton(w.getTechs()[i].name);
            Upgrade.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    w.getTechs()[id].printSummary(w, t);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final int val$id;

            
            {
                w = worldstate;
                t = jtextpane;
                id = i;
                super();
            }
            });
            p.add(Upgrade);
            if(!w.getTechs()[i].isOwned().booleanValue())
                Upgrade.setForeground(Color.GRAY);
        }

        if(page < (w.getTechs().length - 1) / 5)
        {
            JButton Next = new JButton(">");
            Next.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.ViewUpgrades(t, p, f, w, page + 1);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final int val$page;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                page = i;
                super();
            }
            });
            p.add(Next);
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.ShowInformation(t, p, f, w);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public static void Cheat(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        p.removeAll();
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhich cheat would you like to use?\n\n+100 Evil Energy: Increases your Evil Energy by 100.\n\nChange Day: Allows you to skip closer to future events or revisit past ones with the team in its current state.  Range is limited to 1-50, and because events require all three Chosen to be present, this cheat cannot be activated until the full team has been encountered.\n\nDisable/Enable Adaptations: Prevents/Allows Chosen use of Slaughter, Fantasize, Detonate, and Striptease.  Note that use of these actions is required to reach later corruption stages.\n\nUnlock All Upgrades: Purchases every upgrade aside from Imago Quickening at no Evil Energy cost.").toString());
        JButton AddEnergy = new JButton("+100 Evil Energy");
        AddEnergy.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.addEnergy(100);
                Project.Shop(t, p, f, w);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(AddEnergy);
        JButton ChangeDay = new JButton("Change Day");
        ChangeDay.addActionListener(new ActionListener() {

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
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nError: unable to recognize input as a number.").toString());
                    Project.Cheat(t, p, f, w);
                }
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        if(w.getCast()[2] != null)
            p.add(ChangeDay);
        JButton ToggleAdaptations = new JButton("Disable Adaptations");
        if(w.adaptationsDisabled().booleanValue())
            ToggleAdaptations.setText("Enable Adaptations");
        ToggleAdaptations.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.toggleAdaptations();
                Project.Cheat(t, p, f, w);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(ToggleAdaptations);
        JButton AllUpgrades = new JButton("Unlock All Upgrades");
        AllUpgrades.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                for(int i = 0; i < w.getTechs().length - 1; i++)
                    w.getTechs()[i].owned = Boolean.valueOf(true);

                Project.Cheat(t, p, f, w);
            }

            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(AllUpgrades);
        JButton Cancel = new JButton("Back");
        Cancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.Shop(t, p, f, w);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
        });
        p.add(Cancel);
        p.validate();
        p.repaint();
    }

    public static void Data(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final String function, final int page, final Boolean toShop)
    {
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
        File saveLocation = new File((new StringBuilder(String.valueOf(path))).append(File.separator).append("saves.sav").toString());
        SaveData saves = null;
        if(saveLocation.exists())
        {
            ReadObject robj = new ReadObject();
            saves = robj.deserializeSaveData((new StringBuilder(String.valueOf(path))).append(File.separator).append("saves.sav").toString());
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
            Boolean aborted = Boolean.valueOf(false);
            String newSaveName = JOptionPane.showInputDialog("What would you like to name this save?");
            if(newSaveName == null)
                aborted = Boolean.valueOf(true);
            else
            if(newSaveName.length() == 0)
            {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                newSaveName = (new StringBuilder("Save of ")).append(dateFormat.format(date)).toString();
            }
            if(aborted.booleanValue())
            {
                w.append(t, "  Aborted.");
            } else
            {
                w.append(t, (new StringBuilder("  \"")).append(newSaveName).append("\" saved").toString());
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
                    fullSaveName = (new StringBuilder(String.valueOf(fullSaveName))).append(saves.getSaves()[0].getCast()[0].getMainName()).toString();
                else
                if(saves.getSaves()[0].getCast()[2] == null)
                    fullSaveName = (new StringBuilder(String.valueOf(fullSaveName))).append(saves.getSaves()[0].getCast()[0].getMainName()).append(" and ").append(saves.getSaves()[0].getCast()[1].getMainName()).toString();
                else
                    fullSaveName = (new StringBuilder(String.valueOf(fullSaveName))).append(saves.getSaves()[0].getCast()[0].getMainName()).append(", ").append(saves.getSaves()[0].getCast()[1].getMainName()).append(", and ").append(saves.getSaves()[0].getCast()[2].getMainName()).toString();
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nReally overwrite \"").append(fullSaveName).append("\"?").toString());
                p.removeAll();
                JButton Confirm = new JButton("Confirm");
                Confirm.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        saveFile.overwriteSave(w);
                        wobj.serializeSaveData(saveFile);
                        w.append(t, "  Done.");
                        w.save = saveFile;
                        Project.Shop(t, p, f, w);
                    }

                    private final SaveData val$saveFile;
                    private final WorldState val$w;
                    private final WriteObject val$wobj;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                saveFile = savedata;
                w = worldstate;
                wobj = writeobject;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
                });
                p.add(Confirm);
                JButton Cancel = new JButton("Cancel");
                Cancel.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, "  Cancelled.");
                        Project.Shop(t, p, f, w);
                    }

                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
                });
                p.add(Cancel);
                p.validate();
                p.repaint();
            }
        } else
        if(function.equals("export"))
        {
            if(w.campaign.booleanValue())
            {
                WorldState newWorld = new WorldState();
                newWorld.copyInitial(w);
                Chosen newChosen = new Chosen();
                newChosen.setNumber(0);
                newChosen.generate(newWorld);
                newWorld.addChosen(newChosen);
                String newSaveName = JOptionPane.showInputDialog("What would you like to name the exported file?");
                Boolean blankName = Boolean.valueOf(false);
                if(newSaveName == null)
                    blankName = Boolean.valueOf(true);
                else
                if(newSaveName.length() == 0)
                    blankName = Boolean.valueOf(true);
                if(blankName.booleanValue())
                {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    newSaveName = (new StringBuilder("Team of ")).append(dateFormat.format(date)).toString();
                }
                String editedName = "";
                for(int i = 0; i < newSaveName.length(); i++)
                    if(newSaveName.charAt(i) == '/' || newSaveName.charAt(i) == ':')
                        editedName = (new StringBuilder(String.valueOf(editedName))).append("-").toString();
                    else
                        editedName = (new StringBuilder(String.valueOf(editedName))).append(newSaveName.charAt(i)).toString();

                if(w.getHighScore() > 0L)
                    newWorld.setParScore(w.getHighScore());
                if(w.getParScore() > newWorld.getParScore())
                    newWorld.setParScore(w.getParScore());
                newWorld.copySettings(t, w);
                newWorld.copyToggles(w);
                wobj.exportFile(newWorld, editedName);
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nDay 1 start against this team saved to '").append(editedName).append(".par'.").toString());
            } else
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nUnable to export campaign save.").toString());
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
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nNo importable files found in directory.").toString());
            } else
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nFound the following importable files in directory.  Which would you like to import?").toString());
                if(page > 0)
                {
                    JButton LastPage = new JButton("Previous Page");
                    LastPage.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            Project.Data(t, p, f, w, function, page - 1, toShop);
                        }

                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;
                        private final String val$function;
                        private final int val$page;
                        private final Boolean val$toShop;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                function = s;
                page = i;
                toShop = boolean1;
                super();
            }
                    });
                    p.add(LastPage);
                }
                for(; i < foundWorlds.length && j < 4; j++)
                {
                    w.append(t, (new StringBuilder("\n\nFile ")).append(i + 1).append(": ").append(foundWorlds[i].getSaveTitle()).toString());
                    if(foundWorlds[i].getParScore() > 0L)
                        w.append(t, (new StringBuilder(" (Par ")).append(foundWorlds[i].getCast()[0].condensedFormat(foundWorlds[i].getParScore())).append(")").toString());
                    final int worldSelected = i;
                    final WorldState worldList[] = foundWorlds;
                    JButton Access = new JButton((new StringBuilder()).append(i + 1).toString());
                    Access.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            String path = Project.getProtectionDomain().getCodeSource().getLocation().getPath();
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
                                path = path.replaceAll((new StringBuilder(String.valueOf(File.separator))).append("u0020").toString(), (new StringBuilder(String.valueOf(File.separator))).append(" ").toString());
                                File saveLocation = new File((new StringBuilder(String.valueOf(path))).append(File.separator).append("saves.sav").toString());
                                SaveData saves = null;
                                if(saveLocation.exists())
                                {
                                    ReadObject robj = new ReadObject();
                                    saves = robj.deserializeSaveData((new StringBuilder(String.valueOf(path))).append(File.separator).append("saves.sav").toString());
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
                                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nImported file saved to slot ").append(saves.getSaves().length).append(".").toString());
                            }
                            catch(Exception ex)
                            {
                                ex.printStackTrace();
                            }
                        }

                        private final WorldState val$worldList[];
                        private final int val$worldSelected;
                        private final WorldState val$w;
                        private final JTextPane val$t;

            
            {
                worldList = aworldstate;
                worldSelected = i;
                w = worldstate;
                t = jtextpane;
                super();
            }
                    });
                    p.add(Access);
                    i++;
                }

                if(page * 4 + 4 < foundWorlds.length)
                {
                    JButton NextPage = new JButton("Next Page");
                    NextPage.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            Project.Data(t, p, f, w, function, page + 1, toShop);
                        }

                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;
                        private final String val$function;
                        private final int val$page;
                        private final Boolean val$toShop;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                function = s;
                page = i;
                toShop = boolean1;
                super();
            }
                    });
                    p.add(NextPage);
                }
            }
            JButton Back = new JButton("Back");
            Back.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    if(toShop.booleanValue())
                    {
                        Project.Shop(t, p, f, w);
                    } else
                    {
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        Project.IntroOne(t, p, f, w);
                    }
                }

                private final Boolean val$toShop;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;

            
            {
                toShop = boolean1;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
            });
            p.add(Back);
            p.validate();
            p.repaint();
        } else
        {
            int i = page * 4;
            int j = 0;
            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
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

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Data(t, p, f, w, function, page - 1, toShop);
                    }

                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final String val$function;
                    private final int val$page;
                    private final Boolean val$toShop;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                function = s;
                page = i;
                toShop = boolean1;
                super();
            }
                });
                p.add(LastPage);
            }
            for(; i < saves.getSaves().length && j < 4; j++)
            {
                String fullSaveName = (new StringBuilder(String.valueOf(saves.getNames()[i]))).append(" - Day ").append(saves.getSaves()[i].getDay()).append(" versus ").toString();
                if(saves.getSaves()[i].getCast()[1] == null)
                    fullSaveName = (new StringBuilder(String.valueOf(fullSaveName))).append(saves.getSaves()[i].getCast()[0].getMainName()).toString();
                else
                if(saves.getSaves()[i].getCast()[2] == null)
                    fullSaveName = (new StringBuilder(String.valueOf(fullSaveName))).append(saves.getSaves()[i].getCast()[0].getMainName()).append(" and ").append(saves.getSaves()[i].getCast()[1].getMainName()).toString();
                else
                    fullSaveName = (new StringBuilder(String.valueOf(fullSaveName))).append(saves.getSaves()[i].getCast()[0].getMainName()).append(", ").append(saves.getSaves()[i].getCast()[1].getMainName()).append(", and ").append(saves.getSaves()[i].getCast()[2].getMainName()).toString();
                if(saves.getSaves()[i].getHighScore() > 0L)
                {
                    fullSaveName = (new StringBuilder(String.valueOf(fullSaveName))).append(" (HS ").append(saves.getSaves()[i].getCast()[0].condensedFormat(saves.getSaves()[i].getHighScore())).toString();
                    if(saves.getSaves()[i].getParScore() > 0L)
                        fullSaveName = (new StringBuilder(String.valueOf(fullSaveName))).append(" | Par ").append(saves.getSaves()[i].getCast()[0].condensedFormat(saves.getSaves()[i].getParScore())).append(")").toString();
                    else
                        fullSaveName = (new StringBuilder(String.valueOf(fullSaveName))).append(")").toString();
                } else
                if(saves.getSaves()[i].getParScore() > 0L)
                    fullSaveName = (new StringBuilder(String.valueOf(fullSaveName))).append(" (Par ").append(saves.getSaves()[i].getCast()[0].condensedFormat(saves.getSaves()[i].getParScore())).append(")").toString();
                String displayedName = (new StringBuilder("\n\nSlot ")).append(i + 1).toString();
                if(i == 0)
                    displayedName = (new StringBuilder(String.valueOf(displayedName))).append(" (most recent)").toString();
                else
                if(i == saves.getSaves().length - 1)
                    displayedName = (new StringBuilder(String.valueOf(displayedName))).append(" (oldest)").toString();
                displayedName = (new StringBuilder(String.valueOf(displayedName))).append(", ").append(fullSaveName).toString();
                if(saves.getSaves()[i].campaign.booleanValue())
                {
                    if(saves.getSaves()[i].earlyCheat.booleanValue())
                    {
                        w.greenAppend(t, (new StringBuilder(String.valueOf(displayedName))).append(" [Loop ").append(saves.getSaves()[i].loops + 1).append(": ").append(saves.getSaves()[i].cityName).toString());
                        if(saves.getSaves()[i].loopComplete.booleanValue())
                            w.greenAppend(t, "] [Loop Complete]");
                        else
                            w.greenAppend(t, "]");
                    } else
                    {
                        w.blueAppend(t, (new StringBuilder(String.valueOf(displayedName))).append(" [Loop ").append(saves.getSaves()[i].loops + 1).append(": ").append(saves.getSaves()[i].cityName).toString());
                        if(saves.getSaves()[i].loopComplete.booleanValue())
                            w.blueAppend(t, "] [Loop Complete]");
                        else
                            w.blueAppend(t, "]");
                    }
                } else
                if(saves.getSaves()[i].hardMode.booleanValue())
                    w.redAppend(t, displayedName);
                else
                if(saves.getSaves()[i].earlyCheat.booleanValue())
                    w.greenAppend(t, displayedName);
                else
                    w.append(t, displayedName);
                final int fileSelected = i;
                final String thisSaveName = fullSaveName;
                JButton Access = new JButton((new StringBuilder()).append(i + 1).toString());
                Access.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        if(function.equals("load"))
                        {
                            if(toShop.booleanValue())
                            {
                                p.removeAll();
                                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nReally load \"").append(thisSaveName).append("\"?  Your current progress won't be saved.").toString());
                                JButton Confirm = new JButton("Confirm");
                                Confirm.addActionListener(new ActionListener() {

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
                                        if(savedWorld.getDay() == 1 && savedWorld.evilEnergy == 0 && !savedWorld.getTechs()[0].owned.booleanValue() && !savedWorld.getTechs()[1].owned.booleanValue() && !savedWorld.getTechs()[2].owned.booleanValue() && !savedWorld.getTechs()[3].owned.booleanValue() && !savedWorld.campaign.booleanValue())
                                        {
                                            savedWorld.earlyCheat = w.earlyCheat;
                                            savedWorld.earlyCheat = w.earlyCheat;
                                            savedWorld.hardMode = w.hardMode;
                                            savedWorld.eventOffset = w.eventOffset;
                                            savedWorld.clampStart = w.clampStart;
                                            savedWorld.clampPercent = w.clampPercent;
                                            if(savedWorld.earlyCheat.booleanValue())
                                                Project.Shop(t, p, f, saveFile.getSaves()[0]);
                                            else
                                                Project.IntroTwo(t, p, f, saveFile.getSaves()[0]);
                                        } else
                                        {
                                            Project.Shop(t, p, f, saveFile.getSaves()[0]);
                                        }
                                    }

                                    final _cls216 this$1;
                                    private final SaveData val$saveFile;
                                    private final int val$fileSelected;
                                    private final WriteObject val$wobj;
                                    private final JTextPane val$t;
                                    private final WorldState val$w;
                                    private final JPanel val$p;
                                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls216.this;
                        saveFile = savedata;
                        fileSelected = i;
                        wobj = writeobject;
                        t = jtextpane;
                        w = worldstate;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                                });
                                p.add(Confirm);
                                JButton Cancel = new JButton("Cancel");
                                Cancel.addActionListener(new ActionListener() {

                                    public void actionPerformed(ActionEvent e)
                                    {
                                        Project.Shop(t, p, f, w);
                                    }

                                    final _cls216 this$1;
                                    private final JTextPane val$t;
                                    private final JPanel val$p;
                                    private final JFrame val$f;
                                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls216.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
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
                                if(savedWorld.getDay() == 1 && savedWorld.evilEnergy == 0 && !savedWorld.getTechs()[0].owned.booleanValue() && !savedWorld.getTechs()[1].owned.booleanValue() && !savedWorld.getTechs()[2].owned.booleanValue() && !savedWorld.getTechs()[3].owned.booleanValue())
                                {
                                    savedWorld.earlyCheat = w.earlyCheat;
                                    savedWorld.hardMode = w.hardMode;
                                    savedWorld.eventOffset = w.eventOffset;
                                    savedWorld.clampStart = w.clampStart;
                                    savedWorld.clampPercent = w.clampPercent;
                                    if(savedWorld.earlyCheat.booleanValue())
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
                            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
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
                                            copies[j].bonusHATE = Boolean.valueOf(true);
                                        if(copies[j].innocence > 66)
                                            copies[j].bonusPLEA = Boolean.valueOf(true);
                                        if(copies[j].confidence > 66)
                                            copies[j].bonusINJU = Boolean.valueOf(true);
                                        if(copies[j].dignity > 66)
                                            copies[j].bonusEXPO = Boolean.valueOf(true);
                                        copies[j].introduced = Boolean.valueOf(false);
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
                            w.append(t, (new StringBuilder("Added ")).append(saveFile.getSaves()[fileSelected].getCast()[0].mainName).toString());
                            if(found == 3)
                                w.append(t, (new StringBuilder(", ")).append(saveFile.getSaves()[fileSelected].getCast()[1].mainName).append(", and ").append(saveFile.getSaves()[fileSelected].getCast()[2].mainName).toString());
                            else
                            if(found == 2)
                                w.append(t, (new StringBuilder(" and ")).append(saveFile.getSaves()[fileSelected].getCast()[1].mainName).toString());
                            w.append(t, " to the custom roster.");
                        } else
                        {
                            p.removeAll();
                            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nReally delete \"").append(thisSaveName).append("\"?").toString());
                            JButton Confirm = new JButton("Confirm");
                            Confirm.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    saveFile.deleteSave(fileSelected);
                                    wobj.serializeSaveData(saveFile);
                                    w.append(t, "  Done.");
                                    w.save = saveFile;
                                    Project.Shop(t, p, f, w);
                                }

                                final _cls216 this$1;
                                private final SaveData val$saveFile;
                                private final int val$fileSelected;
                                private final WriteObject val$wobj;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls216.this;
                        saveFile = savedata;
                        fileSelected = i;
                        wobj = writeobject;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(Confirm);
                            JButton Cancel = new JButton("Cancel");
                            Cancel.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.append(t, "  Cancelled.");
                                    Project.Shop(t, p, f, w);
                                }

                                final _cls216 this$1;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls216.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(Cancel);
                            p.validate();
                            p.repaint();
                        }
                    }

                    private final String val$function;
                    private final Boolean val$toShop;
                    private final JPanel val$p;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final String val$thisSaveName;
                    private final SaveData val$saveFile;
                    private final int val$fileSelected;
                    private final WriteObject val$wobj;
                    private final JFrame val$f;

            
            {
                function = s;
                toShop = boolean1;
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                thisSaveName = s1;
                saveFile = savedata;
                fileSelected = i;
                wobj = writeobject;
                f = jframe;
                super();
            }
                });
                p.add(Access);
                i++;
            }

            if(page * 4 + 4 < saves.getSaves().length)
            {
                JButton NextPage = new JButton("Next Page");
                NextPage.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Data(t, p, f, w, function, page + 1, toShop);
                    }

                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final String val$function;
                    private final int val$page;
                    private final Boolean val$toShop;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                function = s;
                page = i;
                toShop = boolean1;
                super();
            }
                });
                p.add(NextPage);
            }
            JButton Cancel = new JButton("Cancel");
            if(function.equals("teamload"))
                Cancel.setText("Done");
            Cancel.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    if(function.equals("teamload"))
                    {
                        Boolean enabled[] = new Boolean[w.save.customRoster.length];
                        for(int i = 0; i < enabled.length; i++)
                            enabled[i] = Boolean.valueOf(true);

                        Project.CampaignMenu(t, p, f, w, enabled);
                    } else
                    if(toShop.booleanValue())
                    {
                        Project.Shop(t, p, f, w);
                    } else
                    {
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        Project.IntroOne(t, p, f, w);
                    }
                }

                private final String val$function;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final Boolean val$toShop;

            
            {
                function = s;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                toShop = boolean1;
                super();
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
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
        if(!w.getBodyStatus()[0].booleanValue())
        {
            w.append(t, "You aren't currently sending a Commander body to the battlefield.  Creating one costs 1 Evil Energy.");
            if(w.getEvilEnergy() >= 1)
            {
                JButton Create = new JButton("Create");
                Create.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.newCommander();
                        w.addEnergy(-1);
                        Project.Customize(t, p, f, w);
                    }

                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
                });
                p.add(Create);
            }
            if(!w.hardMode.booleanValue() && w.save != null && w.getHarem() != null && w.getHarem().length > 0)
            {
                JButton UseForsaken = new JButton("Use Forsaken");
                UseForsaken.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.ForsakenMenu(t, p, f, w, w.save, 0);
                    }

                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
                });
                p.add(UseForsaken);
            }
            JButton Back = new JButton("Back");
            Back.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.Shop(t, p, f, w);
                }

                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
            });
            p.add(Back);
        } else
        {
            w.printCommanderSummary(t, null);
            int suppressorsKnown = 0;
            if(w.getTechs()[10].isOwned().booleanValue())
                suppressorsKnown++;
            if(w.getTechs()[11].isOwned().booleanValue())
                suppressorsKnown++;
            if(w.getTechs()[12].isOwned().booleanValue())
                suppressorsKnown++;
            if(w.getTechs()[13].isOwned().booleanValue())
                suppressorsKnown++;
            int suppressorsUsed = 0;
            if(w.getBodyStatus()[3].booleanValue())
                suppressorsUsed++;
            if(w.getBodyStatus()[4].booleanValue())
                suppressorsUsed++;
            if(w.getBodyStatus()[5].booleanValue())
                suppressorsUsed++;
            if(w.getBodyStatus()[6].booleanValue())
                suppressorsUsed++;
            final Boolean defilerUsed = Boolean.valueOf(w.getBodyStatus()[11].booleanValue() || w.getBodyStatus()[12].booleanValue() || w.getBodyStatus()[13].booleanValue() || w.getBodyStatus()[14].booleanValue());
            Boolean defilerKnown = Boolean.valueOf(false);
            if(w.getTechs()[22].isOwned().booleanValue() || w.getTechs()[23].isOwned().booleanValue() || w.getTechs()[24].isOwned().booleanValue() || w.getTechs()[25].isOwned().booleanValue())
                defilerKnown = Boolean.valueOf(true);
            final int suppressorsUsedFinal = suppressorsUsed;
            final Boolean punisherUsed = Boolean.valueOf(w.getBodyStatus()[19].booleanValue() || w.getBodyStatus()[20].booleanValue() || w.getBodyStatus()[21].booleanValue() || w.getBodyStatus()[22].booleanValue());
            Boolean punisherKnown = Boolean.valueOf(false);
            if(w.getTechs()[34].isOwned().booleanValue() || w.getTechs()[35].isOwned().booleanValue() || w.getTechs()[36].isOwned().booleanValue() || w.getTechs()[37].isOwned().booleanValue())
                punisherKnown = Boolean.valueOf(true);
            if((!punisherUsed.booleanValue() || w.getTechs()[47].isOwned().booleanValue() && (w.getEvilEnergy() >= 66 || defilerUsed.booleanValue())) && (suppressorsKnown > 0 && suppressorsUsed == 0 && (!defilerUsed.booleanValue() || w.getTechs()[33].isOwned().booleanValue() && w.getEvilEnergy() >= 10 || punisherUsed.booleanValue()) || suppressorsKnown > 1 && suppressorsUsed == 1 && w.getEvilEnergy() >= 5 && w.getTechs()[21].isOwned().booleanValue() && !defilerUsed.booleanValue() && !punisherUsed.booleanValue()))
            {
                JButton Suppressor = new JButton("Suppressor Upgrades");
                Suppressor.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        if(w.getTechs()[10].isOwned().booleanValue() && !w.getBodyStatus()[3].booleanValue())
                        {
                            JButton Hunger = new JButton("Hunger [HATE]");
                            Hunger.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    if(punisherUsed.booleanValue())
                                        w.applyCompletion();
                                    else
                                    if(defilerUsed.booleanValue())
                                        w.applySynthesis();
                                    else
                                    if(suppressorsUsedFinal == 1)
                                        w.applyVersatility();
                                    w.applyHunger();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls222 this$1;
                                private final Boolean val$punisherUsed;
                                private final WorldState val$w;
                                private final Boolean val$defilerUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls222.this;
                        punisherUsed = boolean1;
                        w = worldstate;
                        defilerUsed = boolean2;
                        suppressorsUsedFinal = i;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(Hunger);
                        }
                        if(w.getTechs()[11].isOwned().booleanValue() && !w.getBodyStatus()[4].booleanValue())
                        {
                            JButton Lust = new JButton("Lust [PLEA]");
                            Lust.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    if(punisherUsed.booleanValue())
                                        w.applyCompletion();
                                    else
                                    if(defilerUsed.booleanValue())
                                        w.applySynthesis();
                                    else
                                    if(suppressorsUsedFinal == 1)
                                        w.applyVersatility();
                                    w.applyLust();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls222 this$1;
                                private final Boolean val$punisherUsed;
                                private final WorldState val$w;
                                private final Boolean val$defilerUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls222.this;
                        punisherUsed = boolean1;
                        w = worldstate;
                        defilerUsed = boolean2;
                        suppressorsUsedFinal = i;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(Lust);
                        }
                        if(w.getTechs()[12].isOwned().booleanValue() && !w.getBodyStatus()[5].booleanValue())
                        {
                            JButton Anger = new JButton("Anger [INJU]");
                            Anger.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    if(punisherUsed.booleanValue())
                                        w.applyCompletion();
                                    else
                                    if(defilerUsed.booleanValue())
                                        w.applySynthesis();
                                    else
                                    if(suppressorsUsedFinal == 1)
                                        w.applyVersatility();
                                    w.applyAnger();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls222 this$1;
                                private final Boolean val$punisherUsed;
                                private final WorldState val$w;
                                private final Boolean val$defilerUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls222.this;
                        punisherUsed = boolean1;
                        w = worldstate;
                        defilerUsed = boolean2;
                        suppressorsUsedFinal = i;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(Anger);
                        }
                        if(w.getTechs()[13].isOwned().booleanValue() && !w.getBodyStatus()[6].booleanValue())
                        {
                            JButton Mania = new JButton("Mania [EXPO]");
                            Mania.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    if(punisherUsed.booleanValue())
                                        w.applyCompletion();
                                    else
                                    if(defilerUsed.booleanValue())
                                        w.applySynthesis();
                                    else
                                    if(suppressorsUsedFinal == 1)
                                        w.applyVersatility();
                                    w.applyMania();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls222 this$1;
                                private final Boolean val$punisherUsed;
                                private final WorldState val$w;
                                private final Boolean val$defilerUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls222.this;
                        punisherUsed = boolean1;
                        w = worldstate;
                        defilerUsed = boolean2;
                        suppressorsUsedFinal = i;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(Mania);
                        }
                        JButton Cancel = new JButton("Cancel");
                        Cancel.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                Project.Customize(t, p, f, w);
                            }

                            final _cls222 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls222.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                        });
                        p.add(Cancel);
                        p.validate();
                        p.repaint();
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhich upgrade will you apply?").toString());
                    }

                    private final JPanel val$p;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final Boolean val$punisherUsed;
                    private final Boolean val$defilerUsed;
                    private final int val$suppressorsUsedFinal;
                    private final JFrame val$f;

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                punisherUsed = boolean1;
                defilerUsed = boolean2;
                suppressorsUsedFinal = i;
                f = jframe;
                super();
            }
                });
                p.add(Suppressor);
            }
            if((!punisherUsed.booleanValue() || w.getTechs()[47].isOwned().booleanValue() && w.getEvilEnergy() >= 66 || w.getTechs()[47].isOwned().booleanValue() && suppressorsUsed == 1) && defilerKnown.booleanValue() && !defilerUsed.booleanValue() && (suppressorsUsed == 0 || suppressorsUsed == 1 && w.getTechs()[33].isOwned().booleanValue() && w.getEvilEnergy() >= 16) && w.getEvilEnergy() >= 6)
            {
                JButton Defiler = new JButton("Defiler Upgrades");
                Defiler.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        if(w.getTechs()[22].isOwned().booleanValue())
                        {
                            JButton Ambition = new JButton("Ambition [HATE/PLEA]");
                            Ambition.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyAmbition();
                                    if(punisherUsed.booleanValue())
                                        w.applyCompletion();
                                    else
                                    if(suppressorsUsedFinal == 1)
                                        w.applySynthesis();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls223 this$1;
                                private final WorldState val$w;
                                private final Boolean val$punisherUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls223.this;
                        w = worldstate;
                        punisherUsed = boolean1;
                        suppressorsUsedFinal = i;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(Ambition);
                        }
                        if(w.getTechs()[23].isOwned().booleanValue())
                        {
                            JButton Dominance = new JButton("Dominance [PLEA/INJU]");
                            if(w.tickle().booleanValue())
                                Dominance.setText("Dominance [PLEA/ANTI]");
                            Dominance.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyDominance();
                                    if(punisherUsed.booleanValue())
                                        w.applyCompletion();
                                    else
                                    if(suppressorsUsedFinal == 1)
                                        w.applySynthesis();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls223 this$1;
                                private final WorldState val$w;
                                private final Boolean val$punisherUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls223.this;
                        w = worldstate;
                        punisherUsed = boolean1;
                        suppressorsUsedFinal = i;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(Dominance);
                        }
                        if(w.getTechs()[24].isOwned().booleanValue())
                        {
                            JButton Spite = new JButton("Spite [INJU/EXPO]");
                            if(w.tickle().booleanValue())
                                Spite.setText("Spite [ANTI/EXPO]");
                            Spite.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applySpite();
                                    if(punisherUsed.booleanValue())
                                        w.applyCompletion();
                                    else
                                    if(suppressorsUsedFinal == 1)
                                        w.applySynthesis();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls223 this$1;
                                private final WorldState val$w;
                                private final Boolean val$punisherUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls223.this;
                        w = worldstate;
                        punisherUsed = boolean1;
                        suppressorsUsedFinal = i;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(Spite);
                        }
                        if(w.getTechs()[25].isOwned().booleanValue())
                        {
                            JButton Vanity = new JButton("Vanity [EXPO/HATE]");
                            Vanity.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyVanity();
                                    if(punisherUsed.booleanValue())
                                        w.applyCompletion();
                                    else
                                    if(suppressorsUsedFinal == 1)
                                        w.applySynthesis();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls223 this$1;
                                private final WorldState val$w;
                                private final Boolean val$punisherUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls223.this;
                        w = worldstate;
                        punisherUsed = boolean1;
                        suppressorsUsedFinal = i;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(Vanity);
                        }
                        JButton Cancel = new JButton("Cancel");
                        Cancel.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                Project.Customize(t, p, f, w);
                            }

                            final _cls223 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls223.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                        });
                        p.add(Cancel);
                        p.validate();
                        p.repaint();
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhich upgrade will you apply?").toString());
                    }

                    private final JPanel val$p;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final Boolean val$punisherUsed;
                    private final int val$suppressorsUsedFinal;
                    private final JFrame val$f;

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                punisherUsed = boolean1;
                suppressorsUsedFinal = i;
                f = jframe;
                super();
            }
                });
                p.add(Defiler);
            }
            if(!punisherUsed.booleanValue() && punisherKnown.booleanValue() && (!defilerUsed.booleanValue() && suppressorsUsed == 0 || w.getTechs()[47].isOwned().booleanValue() && suppressorsUsed < 2 && (w.getEvilEnergy() >= 66 || defilerUsed.booleanValue() && w.getEvilEnergy() >= 60 || defilerUsed.booleanValue() && suppressorsUsed == 1 && w.getEvilEnergy() >= 50)))
            {
                JButton Punisher = new JButton("Punisher Upgrades");
                Punisher.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        if(w.getTechs()[34].isOwned().booleanValue())
                        {
                            JButton Impregnation = new JButton("Impregnation [HATE]");
                            Impregnation.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyImpregnation();
                                    if(defilerUsed.booleanValue() || suppressorsUsedFinal == 1)
                                        w.applyCompletion();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls224 this$1;
                                private final WorldState val$w;
                                private final Boolean val$defilerUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls224.this;
                        w = worldstate;
                        defilerUsed = boolean1;
                        suppressorsUsedFinal = i;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(Impregnation);
                        }
                        if(w.getTechs()[35].isOwned().booleanValue())
                        {
                            JButton Hypnosis = new JButton("Hypnosis [PLEA]");
                            Hypnosis.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyHypnosis();
                                    if(defilerUsed.booleanValue() || suppressorsUsedFinal == 1)
                                        w.applyCompletion();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls224 this$1;
                                private final WorldState val$w;
                                private final Boolean val$defilerUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls224.this;
                        w = worldstate;
                        defilerUsed = boolean1;
                        suppressorsUsedFinal = i;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(Hypnosis);
                        }
                        if(w.getTechs()[36].isOwned().booleanValue())
                        {
                            JButton Drain = new JButton("Drain [INJU]");
                            if(w.tickle().booleanValue())
                                Drain.setText("Drain [ANTI]");
                            Drain.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyDrain();
                                    if(defilerUsed.booleanValue() || suppressorsUsedFinal == 1)
                                        w.applyCompletion();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls224 this$1;
                                private final WorldState val$w;
                                private final Boolean val$defilerUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls224.this;
                        w = worldstate;
                        defilerUsed = boolean1;
                        suppressorsUsedFinal = i;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(Drain);
                        }
                        if(w.getTechs()[37].isOwned().booleanValue())
                        {
                            JButton Parasitism = new JButton("Parasitism [EXPO]");
                            Parasitism.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyParasitism();
                                    if(defilerUsed.booleanValue() || suppressorsUsedFinal == 1)
                                        w.applyCompletion();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls224 this$1;
                                private final WorldState val$w;
                                private final Boolean val$defilerUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls224.this;
                        w = worldstate;
                        defilerUsed = boolean1;
                        suppressorsUsedFinal = i;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(Parasitism);
                        }
                        JButton Cancel = new JButton("Cancel");
                        Cancel.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                Project.Customize(t, p, f, w);
                            }

                            final _cls224 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls224.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                        });
                        p.add(Cancel);
                        p.validate();
                        p.repaint();
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhich upgrade will you apply?").toString());
                    }

                    private final JPanel val$p;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final Boolean val$defilerUsed;
                    private final int val$suppressorsUsedFinal;
                    private final JFrame val$f;

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                defilerUsed = boolean1;
                suppressorsUsedFinal = i;
                f = jframe;
                super();
            }
                });
                p.add(Punisher);
            }
            if(w.getTechs()[8].isOwned().booleanValue() && !w.getBodyStatus()[1].booleanValue() && w.getEvilEnergy() >= 1)
            {
                JButton Enhance = new JButton("Enhance Duration (1)");
                Enhance.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.enhanceOne();
                        Project.Customize(t, p, f, w);
                    }

                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
                });
                p.add(Enhance);
            }
            if(w.getTechs()[14].isOwned().booleanValue() && w.getBodyStatus()[1].booleanValue() && !w.getBodyStatus()[7].booleanValue() && w.getEvilEnergy() >= 1)
            {
                JButton Enhance = new JButton("Enhance Duration (2)");
                Enhance.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.enhanceTwo();
                        Project.Customize(t, p, f, w);
                    }

                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
                });
                p.add(Enhance);
            }
            if(w.getTechs()[20].isOwned().booleanValue() && w.getBodyStatus()[7].booleanValue() && !w.getBodyStatus()[9].booleanValue() && w.getEvilEnergy() >= 2)
            {
                JButton Enhance = new JButton("Enhance Duration (3)");
                Enhance.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.enhanceThree();
                        Project.Customize(t, p, f, w);
                    }

                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
                });
                p.add(Enhance);
            }
            if(w.getTechs()[26].isOwned().booleanValue() && w.getBodyStatus()[9].booleanValue() && !w.getBodyStatus()[15].booleanValue() && w.getEvilEnergy() >= 2)
            {
                JButton Enhance = new JButton("Enhance Duration (4)");
                Enhance.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.enhanceFour();
                        Project.Customize(t, p, f, w);
                    }

                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
                });
                p.add(Enhance);
            }
            if(w.getTechs()[46].isOwned().booleanValue() && w.getBodyStatus()[15].booleanValue() && !w.getBodyStatus()[25].booleanValue() && w.getEvilEnergy() >= 30)
            {
                JButton Enhance = new JButton("Enhance Duration (5)");
                Enhance.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.enhanceFive();
                        Project.Customize(t, p, f, w);
                    }

                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
                });
                p.add(Enhance);
            }
            if(w.getTechs()[15].isOwned().booleanValue() && !w.getBodyStatus()[8].booleanValue() && w.getEvilEnergy() >= 2)
            {
                JButton AddCapture = new JButton("Extra Capture (1)");
                AddCapture.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.addCaptureOne();
                        Project.Customize(t, p, f, w);
                    }

                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
                });
                p.add(AddCapture);
            }
            if(w.getTechs()[27].isOwned().booleanValue() && w.getBodyStatus()[8].booleanValue() && !w.getBodyStatus()[16].booleanValue() && w.getEvilEnergy() >= 5)
            {
                JButton AddCapture = new JButton("Extra Capture (2)");
                AddCapture.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.addCaptureTwo();
                        Project.Customize(t, p, f, w);
                    }

                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
                });
                p.add(AddCapture);
            }
            if(w.getTechs()[32].isOwned().booleanValue() && w.getBodyStatus()[16].booleanValue() && !w.getBodyStatus()[17].booleanValue() && w.getEvilEnergy() >= 10)
            {
                JButton AddCapture = new JButton("Extra Capture (3)");
                AddCapture.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.addCaptureThree();
                        Project.Customize(t, p, f, w);
                    }

                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
                });
                p.add(AddCapture);
            }
            if(w.getTechs()[38].isOwned().booleanValue() && w.getBodyStatus()[17].booleanValue() && !w.getBodyStatus()[23].booleanValue() && w.getEvilEnergy() >= 20)
            {
                JButton AddCapture = new JButton("Extra Capture (4)");
                AddCapture.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.addCaptureFour();
                        Project.Customize(t, p, f, w);
                    }

                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
                });
                p.add(AddCapture);
            }
            if(w.getTechs()[39].isOwned().booleanValue() && !w.getBodyStatus()[24].booleanValue() && w.getEvilEnergy() >= 10)
            {
                JButton Flight = new JButton("Flight");
                Flight.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.applyRelentlessness();
                        Project.Customize(t, p, f, w);
                    }

                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
                });
                p.add(Flight);
            }
            if(w.getTechs()[9].isOwned().booleanValue())
            {
                JButton Toggle = new JButton("Toggle Ambush");
                Toggle.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.toggleAmbush();
                        Project.Customize(t, p, f, w);
                    }

                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
                });
                p.add(Toggle);
            }
            JButton Refund = new JButton("Refund");
            Refund.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.addEnergy(w.getCommanderValue());
                    w.clearCommander();
                    Project.Customize(t, p, f, w);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
            });
            p.add(Refund);
            if(!punisherUsed.booleanValue() || !defilerUsed.booleanValue() && suppressorsUsed == 0 || defilerUsed.booleanValue() && suppressorsUsed == 1)
            {
                JButton Done = new JButton("Done");
                Done.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.Shop(t, p, f, w);
                    }

                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
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
        Boolean actionMatches = Boolean.valueOf(true);
        if(w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] != action)
        {
            actionMatches = Boolean.valueOf(false);
            w.truncateCommentary(w.getCurrentAction());
        }
        if(w.writePossible().booleanValue())
            if(w.getCurrentComment().length() > 0)
                w.writeCommentary(w.getCurrentComment());
            else
            if(w.getCommentary().length <= w.getCurrentAction() || !actionMatches.booleanValue())
            {
                String generated = "";
                if(action < w.getTechs().length)
                    generated = (new StringBuilder("Buy ")).append(w.getTechs()[action].getName()).append(".").toString();
                else
                if(w.usedForsaken != null)
                {
                    generated = (new StringBuilder(String.valueOf(generated))).append("(This playthrough used one of the Forsaken here, so it may not be possible to reliably follow it.)").toString();
                } else
                {
                    int index = action - w.getTechs().length;
                    if(w.getBodyStatus()[0].booleanValue())
                    {
                        generated = "Buy a Commander with a ";
                        if(w.getBodyStatus()[1].booleanValue())
                        {
                            if(w.getBodyStatus()[7].booleanValue())
                            {
                                if(w.getBodyStatus()[9].booleanValue())
                                {
                                    if(w.getBodyStatus()[15].booleanValue())
                                    {
                                        if(w.getBodyStatus()[25].booleanValue())
                                            generated = (new StringBuilder(String.valueOf(generated))).append("eight").toString();
                                        else
                                            generated = (new StringBuilder(String.valueOf(generated))).append("six").toString();
                                    } else
                                    {
                                        generated = (new StringBuilder(String.valueOf(generated))).append("five").toString();
                                    }
                                } else
                                {
                                    generated = (new StringBuilder(String.valueOf(generated))).append("four").toString();
                                }
                            } else
                            {
                                generated = (new StringBuilder(String.valueOf(generated))).append("three").toString();
                            }
                        } else
                        {
                            generated = (new StringBuilder(String.valueOf(generated))).append("two").toString();
                        }
                        generated = (new StringBuilder(String.valueOf(generated))).append("-turn duration").toString();
                        if(w.getBodyStatus()[17].booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append(" and three extra captures.  ").toString();
                        else
                        if(w.getBodyStatus()[16].booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append(" and two extra captures.  ").toString();
                        else
                        if(w.getBodyStatus()[8].booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append(" and an extra capture.  ").toString();
                        else
                            generated = (new StringBuilder(String.valueOf(generated))).append(".  ").toString();
                        if(w.getBodyStatus()[26].booleanValue())
                        {
                            String suppressor = "";
                            String defiler = "";
                            String punisher = "";
                            if(w.getBodyStatus()[19].booleanValue())
                                punisher = "Impregnation";
                            else
                            if(w.getBodyStatus()[20].booleanValue())
                                punisher = "Hypnosis";
                            else
                            if(w.getBodyStatus()[21].booleanValue())
                                punisher = "Drain";
                            else
                            if(w.getBodyStatus()[22].booleanValue())
                                punisher = "Parasitism";
                            if(w.getBodyStatus()[11].booleanValue())
                                defiler = "Ambition";
                            else
                            if(w.getBodyStatus()[12].booleanValue())
                                defiler = "Dominance";
                            else
                            if(w.getBodyStatus()[13].booleanValue())
                                defiler = "Spite";
                            else
                            if(w.getBodyStatus()[14].booleanValue())
                                defiler = "Vanity";
                            if(w.getBodyStatus()[3].booleanValue())
                                suppressor = "Hunger";
                            else
                            if(w.getBodyStatus()[4].booleanValue())
                                suppressor = "Lust";
                            else
                            if(w.getBodyStatus()[5].booleanValue())
                                suppressor = "Anger";
                            else
                            if(w.getBodyStatus()[6].booleanValue())
                                suppressor = "Mania";
                            generated = (new StringBuilder(String.valueOf(generated))).append("Equip it with the Suppressor ").append(suppressor).append(", the Defiler ").append(defiler).append(", and the Punisher ").append(punisher).append(".  ").toString();
                        } else
                        if(w.getBodyStatus()[19].booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append("Equip it with the Punisher Impregnation.  ").toString();
                        else
                        if(w.getBodyStatus()[20].booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append("Equip it with the Punisher Hypnosis.  ").toString();
                        else
                        if(w.getBodyStatus()[21].booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append("Equip it with the Punisher Drain.  ").toString();
                        else
                        if(w.getBodyStatus()[22].booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append("Equip it with the Punisher Parasitism.  ").toString();
                        else
                        if(w.getBodyStatus()[18].booleanValue())
                        {
                            generated = (new StringBuilder(String.valueOf(generated))).append("Equip it with the Defiler ").toString();
                            if(w.getBodyStatus()[11].booleanValue())
                                generated = (new StringBuilder(String.valueOf(generated))).append("Ambition").toString();
                            else
                            if(w.getBodyStatus()[12].booleanValue())
                                generated = (new StringBuilder(String.valueOf(generated))).append("Dominance").toString();
                            else
                            if(w.getBodyStatus()[13].booleanValue())
                                generated = (new StringBuilder(String.valueOf(generated))).append("Spite").toString();
                            else
                            if(w.getBodyStatus()[14].booleanValue())
                                generated = (new StringBuilder(String.valueOf(generated))).append("Vanity").toString();
                            generated = (new StringBuilder(String.valueOf(generated))).append(" and the Suppressor ").toString();
                            if(w.getBodyStatus()[3].booleanValue())
                                generated = (new StringBuilder(String.valueOf(generated))).append("Hunger").toString();
                            else
                            if(w.getBodyStatus()[4].booleanValue())
                                generated = (new StringBuilder(String.valueOf(generated))).append("Lust").toString();
                            else
                            if(w.getBodyStatus()[5].booleanValue())
                                generated = (new StringBuilder(String.valueOf(generated))).append("Anger").toString();
                            else
                            if(w.getBodyStatus()[6].booleanValue())
                                generated = (new StringBuilder(String.valueOf(generated))).append("Mania").toString();
                            generated = (new StringBuilder(String.valueOf(generated))).append(".  ").toString();
                        } else
                        if(w.getBodyStatus()[11].booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append("Equip it with the Defiler Ambition.  ").toString();
                        else
                        if(w.getBodyStatus()[12].booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append("Equip it with the Defiler Dominance.  ").toString();
                        else
                        if(w.getBodyStatus()[13].booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append("Equip it with the Defiler Spite.  ").toString();
                        else
                        if(w.getBodyStatus()[14].booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append("Equip it with the Defiler Vanity.  ").toString();
                        else
                        if(w.getBodyStatus()[10].booleanValue())
                        {
                            generated = (new StringBuilder(String.valueOf(generated))).append("Equip it with the Suppressors ").toString();
                            Boolean first = Boolean.valueOf(false);
                            if(w.getBodyStatus()[3].booleanValue())
                            {
                                generated = (new StringBuilder(String.valueOf(generated))).append("Hunger").toString();
                                first = Boolean.valueOf(true);
                            }
                            if(w.getBodyStatus()[4].booleanValue())
                            {
                                if(first.booleanValue())
                                    generated = (new StringBuilder(String.valueOf(generated))).append(" and ").toString();
                                generated = (new StringBuilder(String.valueOf(generated))).append("Lust").toString();
                                first = Boolean.valueOf(true);
                            }
                            if(w.getBodyStatus()[5].booleanValue())
                            {
                                if(first.booleanValue())
                                    generated = (new StringBuilder(String.valueOf(generated))).append(" and ").toString();
                                generated = (new StringBuilder(String.valueOf(generated))).append("Anger").toString();
                                first = Boolean.valueOf(true);
                            }
                            if(w.getBodyStatus()[6].booleanValue())
                                generated = (new StringBuilder(String.valueOf(generated))).append(" and Mania").toString();
                            generated = (new StringBuilder(String.valueOf(generated))).append(".  ").toString();
                        } else
                        if(w.getBodyStatus()[3].booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append("Equip it with the Suppressor Hunger.  ").toString();
                        else
                        if(w.getBodyStatus()[4].booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append("Equip it with the Suppressor Lust.  ").toString();
                        else
                        if(w.getBodyStatus()[5].booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append("Equip it with the Suppressor Anger.  ").toString();
                        else
                        if(w.getBodyStatus()[6].booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append("Equip it with the Suppressor Mania.  ").toString();
                        if(w.getBodyStatus()[2].booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append("Turn off its ambush and send ").toString();
                        else
                        if(w.getTechs()[9].isOwned().booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append("Leave its ambush on and send ").toString();
                        else
                            generated = (new StringBuilder(String.valueOf(generated))).append("Send ").toString();
                        if(w.getTechs()[31].isOwned().booleanValue() && !w.upgradedCommander().booleanValue())
                        {
                            Chosen target = w.getCast()[(index - 3) / 4];
                            index = (index - 3) % 4;
                            generated = (new StringBuilder(String.valueOf(generated))).append("it after ").append(target.getMainName()).append(".  Have the Thralls start by using ").toString();
                            if(index == 0)
                                generated = (new StringBuilder(String.valueOf(generated))).append("Grind.").toString();
                            else
                            if(index == 1)
                                generated = (new StringBuilder(String.valueOf(generated))).append("Caress.").toString();
                            else
                            if(index == 2)
                            {
                                if(w.tickle().booleanValue())
                                    generated = (new StringBuilder(String.valueOf(generated))).append("Tickle.").toString();
                                else
                                    generated = (new StringBuilder(String.valueOf(generated))).append("Pummel.").toString();
                            } else
                            if(index == 3)
                                generated = (new StringBuilder(String.valueOf(generated))).append("Humiliate.").toString();
                        } else
                        {
                            generated = (new StringBuilder(String.valueOf(generated))).append("it after ").append(w.getCast()[index].getMainName()).append(".").toString();
                        }
                    } else
                    {
                        generated = (new StringBuilder("Target ")).append(w.getCast()[index].getMainName()).toString();
                        if(w.getTechs()[3].isOwned().booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append(" without bringing along a Commander.").toString();
                        else
                            generated = (new StringBuilder(String.valueOf(generated))).append(".").toString();
                    }
                }
                w.writeCommentary(generated);
            }
        w.nextAction(action);
    }

    public static void pickStartingTarget(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        p.removeAll();
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhich of the Chosen will you target?").toString());
        for(int i = 0; i < w.getCast().length; i++)
            if(w.getCast()[i] != null)
            {
                final int thisChosen = i;
                JButton thisOne = new JButton(w.getCast()[i].getMainName());
                thisOne.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.ConfirmBattle(t, p, f, w, w.getCast()[thisChosen]);
                    }

                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final int val$thisChosen;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                thisChosen = i;
                super();
            }
                });
                p.add(thisOne);
            }

        JButton Cancel = new JButton("Cancel");
        Cancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.Shop(t, p, f, w);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
        });
        p.add(Cancel);
        p.validate();
        p.repaint();
    }

    public static void ConfirmBattle(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Chosen c)
    {
        p.removeAll();
        Boolean immediateAction = Boolean.valueOf(false);
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
        if(w.getTechs()[0].isOwned().booleanValue())
        {
            w.append(t, (new StringBuilder(String.valueOf(c.mainName))).append("'s ").toString());
            c.printVulnerabilities(t, p, f, w);
            w.append(t, "\n\n");
        }
        if(w.getDay() == 50 - w.eventOffset * 3 || w.getTechs()[48].isOwned().booleanValue())
            w.append(t, "This will be the final battle.  When extermination is completed, instead of waiting for surrounded and captured allies to escape, the Chosen may sacrifice each other's lives in order to defeat you.  Victory requires neutralizing at least two of the three Chosen.\n\n");
        if(w.getBodyStatus()[0].booleanValue())
        {
            if(w.upgradedCommander().booleanValue() || !w.getTechs()[31].isOwned().booleanValue() || w.getBodyStatus()[2].booleanValue())
                w.printCommanderSummary(t, c);
            else
                immediateAction = Boolean.valueOf(true);
        } else
        if(w.usedForsaken != null)
        {
            int actualCost = w.usedForsaken.motivationCost();
            if(w.usedForsaken.isFormerFriend(w.getCast()[0]).booleanValue() || w.usedForsaken.isFormerFriend(w.getCast()[1]).booleanValue() || w.usedForsaken.isFormerFriend(w.getCast()[2]).booleanValue())
                actualCost *= 2;
            w.append(t, (new StringBuilder("Commanding Forsaken: ")).append(w.usedForsaken.mainName).append("\nStamina: ").append(w.usedForsaken.stamina / 10).append(".").append(w.usedForsaken.stamina % 10).append("%\nMotivation: ").append(w.usedForsaken.motivation / 10).append(".").append(w.usedForsaken.motivation % 10).append("%\nCost: 20% Stamina, ").append(actualCost / 10).append(".").append(actualCost % 10).append("% Motivation, ").append(w.usedForsaken.EECost()).append(" EE\n").append(w.usedForsaken.describeCombatStyle(w, Boolean.valueOf(false))).append("\nReputation Strength: ").append(200 - w.usedForsaken.disgrace * 2).append("%\nTarget Compatibilities:").toString());
            for(int j = 0; j < 3; j++)
                if(w.getCast()[j] != null)
                {
                    w.append(t, (new StringBuilder("\n")).append(w.getCast()[j].getMainName()).append(" - ").toString());
                    int compatibility = w.usedForsaken.compatibility(w.getCast()[j]);
                    if(w.usedForsaken.knowsPersonally(w.getCast()[j]).booleanValue())
                        w.append(t, "Personal (8 rounds, +25% damage)");
                    else
                    if(w.usedForsaken.obsessedWith(w.getCast()[j]).booleanValue())
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

            w.append(t, (new StringBuilder("\n\nYou will invade a neighborhood along ")).append(c.getMainName()).append("'s patrol path in order to lure ").append(c.himHer()).append(" in an attack ").append(c.himHer()).append(".  ").append(w.usedForsaken.mainName).append(" will lie in wait, ready to engage ").append(c.himHer()).append(" in battle upon your order.").toString());
        } else
        {
            w.append(t, (new StringBuilder("You will invade a neighborhood along ")).append(c.getMainName()).append("'s patrol path in order to lure ").append(c.himHer()).append(" in and attack ").append(c.himHer()).append(".").toString());
        }
        if(immediateAction.booleanValue())
        {
            w.append(t, (new StringBuilder("Which action do you want the Thralls to perform once they grab ")).append(c.himHer()).append("?").toString());
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
                    if(w.tickle().booleanValue())
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

                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        w.printCommanderSummary(t, c);
                        w.append(t, (new StringBuilder("  The Thralls will begin by using ")).append(finalTorment).append(" on ").append(c.himHer()).append(".  Is this okay?").toString());
                        JButton Confirm = new JButton("Confirm");
                        Confirm.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                if(w.usedForsaken != null || w.recordedCommanders.length < w.day - 1)
                                {
                                    w.commentaryRead = Boolean.valueOf(false);
                                    w.commentaryWrite = Boolean.valueOf(false);
                                }
                                if(w.getDay() > 1 && !w.isCheater().booleanValue() && (w.commentaryWrite.booleanValue() || w.commentaryRead.booleanValue()))
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
                                if(w.getDay() == 50 - w.eventOffset * 3 || w.getTechs()[48].isOwned().booleanValue())
                                    Project.BeginFinalBattle(t, p, f, w, c);
                                else
                                    Project.BeginBattle(t, p, f, w, c);
                            }

                            final _cls240 this$1;
                            private final WorldState val$w;
                            private final JPanel val$p;
                            private final Chosen val$c;
                            private final int val$type;
                            private final JTextPane val$t;
                            private final JFrame val$f;

                    
                    {
                        this$1 = _cls240.this;
                        w = worldstate;
                        p = jpanel;
                        c = chosen;
                        type = i;
                        t = jtextpane;
                        f = jframe;
                        super();
                    }
                        });
                        p.add(Confirm);
                        JButton Cancel = new JButton("Cancel");
                        Cancel.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                Project.Shop(t, p, f, w);
                            }

                            final _cls240 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls240.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                        });
                        p.add(Cancel);
                        p.validate();
                        p.repaint();
                    }

                    private final JPanel val$p;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final Chosen val$c;
                    private final String val$finalTorment;
                    private final int val$type;
                    private final JFrame val$f;

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                c = chosen;
                finalTorment = s;
                type = i;
                f = jframe;
                super();
            }
                });
                p.add(Action);
            }

        } else
        {
            w.append(t, "  Is this okay?");
            JButton Confirm = new JButton("Confirm");
            Confirm.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    if(w.usedForsaken != null || w.recordedCommanders.length < w.day - 1)
                    {
                        w.commentaryRead = Boolean.valueOf(false);
                        w.commentaryWrite = Boolean.valueOf(false);
                    }
                    if(w.getDay() > 1 && !w.isCheater().booleanValue() && (w.commentaryRead.booleanValue() || w.commentaryWrite.booleanValue()))
                        w.archiveCommander(w.getDay());
                    Project.advanceDowntimeAction(p, w, w.getTechs().length + c.getNumber());
                    if(w.getDay() == 50 - w.eventOffset * 3 || w.getTechs()[48].isOwned().booleanValue())
                        Project.BeginFinalBattle(t, p, f, w, c);
                    else
                        Project.BeginBattle(t, p, f, w, c);
                }

                private final WorldState val$w;
                private final JPanel val$p;
                private final Chosen val$c;
                private final JTextPane val$t;
                private final JFrame val$f;

            
            {
                w = worldstate;
                p = jpanel;
                c = chosen;
                t = jtextpane;
                f = jframe;
                super();
            }
            });
            p.add(Confirm);
        }
        JButton Cancel = new JButton("Cancel");
        Cancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.Shop(t, p, f, w);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
        });
        p.add(Cancel);
        p.validate();
        p.repaint();
    }

    public static void BeginBattle(JTextPane t, JPanel p, JFrame f, WorldState w, Chosen c)
    {
        w.incrementTotalRounds();
        Chosen newCombatants[] = new Chosen[3];
        newCombatants[0] = c;
        w.newCombat(w, newCombatants);
        if(w.getBodyStatus()[0].booleanValue() && !w.getBodyStatus()[2].booleanValue())
        {
            w.nextCapture = c;
            if(w.getTechs()[31].isOwned().booleanValue() && !w.upgradedCommander().booleanValue())
            {
                w.setBattleRound(0);
                c.BeSurrounded(t, p, f, w);
            } else
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                w.append(t, (new StringBuilder("You lure ")).append(c.getMainName()).append(" into battle with an attack on a neighborhood along ").append(c.hisHer()).append(" patrol route.  ").toString());
                if(w.upgradedCommander().booleanValue())
                {
                    w.append(t, "Then, you spring your ambush.  ");
                    c.startCaptured(t, w);
                } else
                {
                    w.append(t, "Then, with your Commander body on the battlefield, you set up an ambush.  ");
                    if(w.getCapturesPossible() > 0)
                        w.append(t, (new StringBuilder("In the chaos, your body takes a serious injury, but with ")).append(c.getMainName()).append(" surrounded, the battle has already progressed in the Demons' favor.").toString());
                    else
                        w.append(t, (new StringBuilder("In the chaos, your body is destroyed, but with ")).append(c.getMainName()).append(" surrounded, the battle has already progressed in the Demons' favor.").toString());
                    c.setSurrounded(w);
                    c.printSurroundedLine(t, w, c.getThisAttack());
                }
            }
        } else
        {
            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
            w.append(t, "In the middle of a busy street, a horde of Demons suddenly erupts from underground");
            if(w.getBodyStatus()[0].booleanValue())
                w.append(t, ", led by the Commander body you're remotely controlling.");
            else
                w.append(t, "!");
            w.append(t, "  They begin attacking civilians and dragging them away for conversion to the Demonic cause, but before they can get too far, a thundering burst of light shines down on the scene!\n\n");
            c.say(t, (new StringBuilder("\"")).append(c.announcement()).append("\"\n\n").toString());
            c.transform(t, w);
        }
        w.append(t, "\n");
        w.nextCapture = null;
        PickTarget(t, p, f, w);
    }

    public static void BeginFinalBattle(JTextPane t, JPanel p, JFrame f, WorldState w, Chosen c)
    {
        w.incrementTotalRounds();
        Chosen newCombatants[] = new Chosen[3];
        newCombatants[0] = c;
        w.newCombat(w, newCombatants);
        if(w.getDay() == 50 - w.eventOffset * 3)
            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nThe city's streets are devoid of life.  In preparation for the coming battle, the residents have been evacuated to temporary housing in the surrounding countryside.  The only ones who remain are the stubborn, the thrillseekers, some entrepreneurial journalists, and of course your minions.  They all know what's coming, and they're waiting for you to make your move.\n\nFinally, the silence is broken by the sound of shattering pavement.  An enormous, dark shape rises out of the ground, toppling buildings and sending tons of rubble spilling in all directions as it grows.  It's a gigantic pillar whose surface shimmers like an oil slick, and it continues upward until it dwarfs the skyscrapers below, penetrating the heavens themselves.  All throughout the city, space begins to warp and shift as you corrupt the fabric of reality and bend it to your will.\n\n").append(c.getMainName()).append(" is the closest of the Chosen to the epicenter.  Although ").append(c.hisHer()).append(" instincts are telling ").append(c.himHer()).append(" to immediately begin drawing on as much energy as ").append(c.heShe()).append(" can, ").append(c.heShe()).append(" recalls from the strategy briefing that it will still take some time to evacuate the last few VIPs who had to stay until the last moment.  The neighboring cities will also need a chance to prepare for the destructive electromagnetic pulses that are likely to be released as the Chosen fight at full power.\n\n").toString());
        else
            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nThe city's streets are bustling as if this were a day like any other.  Its citizens have no idea how close your plans are to completion.\n\nWithout warning, the pavement of one of the main streets shatters and opens up.  An enormous, dark shape rises out of the ground, toppling buildings and sending tons of rubble spilling in all directions as it grows.  It's an enormous pillar whose surface shimmers like an oil slick, and it continues upward until it dwarfs the skyscrapers below, penetrating the heavens themselves.  All throughout the city, space begins to warp and shift as you corrupt the fabric of reality and bend it to your will.\n\n").append(c.getMainName()).append(" is the closest of the Chosen to the epicenter.  Although ").append(c.hisHer()).append(" instincts are telling ").append(c.himHer()).append(" to immediately begin drawing on as much energy as ").append(c.heShe()).append(" can, ").append(c.heShe()).append(" has orders to restrain ").append(c.himHer()).append("self until ").append(c.heShe()).append("'s given clearance to go all-out.  Loudspeakers across the city broadcast instructions to the Chosen as they all hurry toward the tower, warning them that this will be the final battle and that they may not survive.  They're told to hold back at least until the most important VIPs can get a safe distance from the city.  It goes unsaid that the rest of the populace is considered an acceptable sacrifice.\n\n").toString());
        w.finalBattleIntro(t, c);
        if(w.getBodyStatus()[0].booleanValue() && !w.getBodyStatus()[2].booleanValue())
        {
            w.nextCapture = c;
            if(w.getTechs()[31].isOwned().booleanValue() && !w.upgradedCommander().booleanValue())
            {
                w.setBattleRound(0);
                c.BeSurrounded(t, p, f, w);
            } else
            {
                w.append(t, "\n\nBefore the Chosen can meet up with each other, you spring your ambush.  ");
                if(w.upgradedCommander().booleanValue())
                {
                    c.startCaptured(t, w);
                } else
                {
                    w.append(t, "Led by your Commander body, your minions emerge from their hiding places and rush in from all directions.  ");
                    if(w.getCapturesPossible() > 0)
                        w.append(t, (new StringBuilder("In the chaos, your body takes a serious injury, but with ")).append(c.getMainName()).append(" surrounded, the battle has already progressed in the Demons' favor.").toString());
                    else
                        w.append(t, (new StringBuilder("In the chaos, your body is destroyed, but with ")).append(c.getMainName()).append(" surrounded, the battle has already progressed in the Demons' favor.").toString());
                    c.setSurrounded(w);
                    c.printSurroundedLine(t, w, c.getThisAttack());
                }
            }
        }
        w.append(t, "\n");
        PickTarget(t, p, f, w);
        w.nextCapture = null;
    }

    public static void DefeatScene(JTextPane t, JPanel p, JFrame f, WorldState w)
    {
        Chosen killed[] = new Chosen[2];
        Chosen fallen[] = new Chosen[2];
        Chosen escaped[] = new Chosen[3];
        for(int i = 0; i < 3; i++)
            if(!w.getCast()[i].alive.booleanValue())
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
            changePortrait(high.convertGender(), high.type, Boolean.valueOf(false), Boolean.valueOf(false), w, as, 0, Emotion.FOCUS, Emotion.FOCUS);
            as1 = new String[5];
            as1[0] = high.mainName;
            as1[1] = mid.mainName;
            as1[2] = low.mainName;
            changePortrait(mid.convertGender(), mid.type, Boolean.valueOf(false), Boolean.valueOf(false), w, as1, 1, Emotion.FOCUS, Emotion.FOCUS);
            as2 = new String[5];
            as2[0] = high.mainName;
            as2[1] = mid.mainName;
            as2[2] = low.mainName;
            changePortrait(low.convertGender(), low.type, Boolean.valueOf(false), Boolean.valueOf(false), w, as2, 2, Emotion.FOCUS, Emotion.FOCUS);
            if(w.getRelationship(high.getNumber(), mid.getNumber()) >= 0)
            {
                if(w.getRelationship(high.getNumber(), low.getNumber()) >= 0)
                {
                    high.say(t, "Let's do it!  Just like we practiced!\"\n\n");
                    mid.say(t, "\"");
                    if(w.getRelationship(mid.getNumber(), low.getNumber()) >= 0)
                    {
                        mid.say(t, (new StringBuilder("I'm ready!  You okay, ")).append(low.getMainName()).append("!?\"\n\n").toString());
                        low.say(t, "\"R-Ready here too!");
                    } else
                    {
                        as3 = new String[5];
                        as3[0] = high.mainName;
                        as3[1] = mid.mainName;
                        as3[2] = low.mainName;
                        changePortrait(mid.convertGender(), mid.type, Boolean.valueOf(false), Boolean.valueOf(false), w, as3, 1, Emotion.ANGER, Emotion.ANGER);
                        mid.say(t, (new StringBuilder("I'm ready!  You'd better not screw it up for us, ")).append(low.getMainName()).append("!\"\n\n").toString());
                        low.say(t, (new StringBuilder("\"I-I won't let you down, ")).append(high.getMainName()).append("!").toString());
                    }
                } else
                {
                    as4 = new String[5];
                    as4[0] = high.mainName;
                    as4[1] = mid.mainName;
                    as4[2] = low.mainName;
                    changePortrait(high.convertGender(), high.type, Boolean.valueOf(false), Boolean.valueOf(false), w, as4, 0, Emotion.ANGER, Emotion.ANGER);
                    high.say(t, (new StringBuilder("You'd better not screw this up, ")).append(low.getMainName()).append("!\"\n\n").toString());
                    mid.say(t, "\"");
                    if(w.getRelationship(mid.getNumber(), low.getNumber()) >= 0)
                    {
                        mid.say(t, (new StringBuilder("Don't worry, ")).append(low.getMainName()).append("!  I believe in you!\"\n\n").toString());
                        low.say(t, "\"R-Right!");
                    } else
                    {
                        as5 = new String[5];
                        as5[0] = high.mainName;
                        as5[1] = mid.mainName;
                        as5[2] = low.mainName;
                        changePortrait(low.convertGender(), low.type, Boolean.valueOf(false), Boolean.valueOf(false), w, as5, 2, Emotion.ANGER, Emotion.ANGER);
                        mid.say(t, (new StringBuilder("Don't worry, we can finish this without ")).append(low.himHer()).append(" if we have to!\"\n\n").toString());
                        low.say(t, "\"I-I'm fine, just worry about yourselves!");
                    }
                }
            } else
            {
                as6 = new String[5];
                as6[0] = high.mainName;
                as6[1] = mid.mainName;
                as6[2] = low.mainName;
                changePortrait(high.convertGender(), high.type, Boolean.valueOf(false), Boolean.valueOf(false), w, as6, 0, Emotion.ANGER, Emotion.ANGER);
                if(w.getRelationship(high.getNumber(), low.getNumber()) >= 0)
                {
                    high.say(t, (new StringBuilder("You'd better not screw this up, ")).append(mid.getMainName()).append("!\"\n\n").toString());
                    as7 = new String[5];
                    as7[0] = high.mainName;
                    as7[1] = mid.mainName;
                    as7[2] = low.mainName;
                    changePortrait(mid.convertGender(), mid.type, Boolean.valueOf(false), Boolean.valueOf(false), w, as7, 1, Emotion.ANGER, Emotion.ANGER);
                    mid.say(t, "\"");
                    if(w.getRelationship(mid.getNumber(), low.getNumber()) >= 0)
                    {
                        mid.say(t, (new StringBuilder("Worry about yourself, ")).append(high.getMainName()).append("!\"\n\n").toString());
                        low.say(t, "\"P-Please, you two, we shouldn't be fighting each other now of all times!");
                        as8 = new String[5];
                        as8[0] = high.mainName;
                        as8[1] = mid.mainName;
                        as8[2] = low.mainName;
                        changePortrait(low.convertGender(), low.type, Boolean.valueOf(false), Boolean.valueOf(false), w, as8, 2, Emotion.FEAR, Emotion.FEAR);
                    } else
                    {
                        mid.say(t, (new StringBuilder("You know that if anyone's going to screw up here, it's ")).append(low.getMainName()).append("!\"\n\n").toString());
                        low.say(t, (new StringBuilder("\"I-I won't!  I'm ready, ")).append(high.getMainName()).append("!").toString());
                    }
                } else
                {
                    high.say(t, "You two had better not screw this up!\"\n\n");
                    mid.say(t, "\"");
                    if(w.getRelationship(mid.getNumber(), low.getNumber()) >= 0)
                    {
                        mid.say(t, (new StringBuilder("We definitely won't!  Right, ")).append(mid.getMainName()).append("!?\"\n\n").toString());
                        low.say(t, "\"R-Right!");
                    } else
                    {
                        mid.say(t, (new StringBuilder("I definitely won't, though I'm not sure about ")).append(mid.getMainName()).append("...\"\n\n").toString());
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
                w.append(t, (new StringBuilder("With ")).append(fallen[0].getMainName()).append("'s defeat, a powerful evil energy is gathering on the battlefield.  The tip of the Demonic spire begins to glow, preparing to release one final pulse of corruption that will cement your domination over this region of reality.  However, at the same time, ").append(escaped[0].getMainName()).append(" and ").append(escaped[1].getMainName()).append(" have cleared out the last of your Demonic forces, and they're ready to launch their counterattack.").toString());
            else
                w.append(t, (new StringBuilder("With every moment that passes, the Demonic spire grows upward, gathering power and deepening your domination over this region of reality.  However, by sacrificing ")).append(killed[0].getMainName()).append("'s life, ").append(escaped[0].getMainName()).append(" and ").append(escaped[1].getMainName()).append(" have exterminated all the surrounding Demons, and they're ready to launch their counterattack.").toString());
            as9 = new String[5];
            as9[0] = first.mainName;
            as9[1] = second.mainName;
            changePortrait(first.convertGender(), first.type, Boolean.valueOf(false), Boolean.valueOf(false), w, as9, 0, Emotion.FOCUS, Emotion.FOCUS);
            if(w.getRelationship(first.getNumber(), second.getNumber()) >= 0)
            {
                as10 = new String[5];
                as10[0] = first.mainName;
                as10[1] = second.mainName;
                changePortrait(second.convertGender(), second.type, Boolean.valueOf(false), Boolean.valueOf(false), w, as10, 1, Emotion.FOCUS, Emotion.FOCUS);
                first.say(t, "\n\n\"If we work together, I think we can still stop it!  Back me up!\"\n\n");
                second.say(t, "\"Got it!  I'm right behind you!\"\n\n");
                w.append(t, (new StringBuilder(String.valueOf(first.getMainName()))).append(" charges forward, blazing brighter than the sun as ").append(first.heShe()).append(" draws on as much psychic energy as ").append(first.heShe()).append(" can.  ").append(second.getMainName()).append(" has a hand on ").append(first.hisHer()).append(" shoulder, pushing ").append(first.himHer()).append(" forward as they fly together.  The two of them blast through the base of the tower, leaving an enormous hole behind them.  And with its lower structure compromised, the shaft begins to topple to one side.  It lands on the city with a deafening crash, kicking up a huge cloud of debris.  As quickly as that, the Demonic presence over the city lifts.\n\nThe battle is over.  But even though this Demon Lord has been defeated, the scars left on the hearts of the Chosen won't heal so easily.  ").toString());
            } else
            {
                as11 = new String[5];
                as11[0] = first.mainName;
                as11[1] = second.mainName;
                changePortrait(second.convertGender(), second.type, Boolean.valueOf(false), Boolean.valueOf(false), w, as11, 1, Emotion.FEAR, Emotion.FEAR);
                first.say(t, "\n\n\"If we're going to take that thing down, we need to go all-out!  Don't hold back, or you'll die!\"\n\n");
                second.say(t, "\"Huh?  Gaaah!  Ergh... you're... crazy...!\"\n\n");
                w.append(t, (new StringBuilder(String.valueOf(first.getMainName()))).append(" holds out one palm to shoot a beam of crackling destructive energy directly at ").append(second.getMainName()).append(".  For ").append(second.hisHer()).append(" part, ").append(second.getMainName()).append(" barely reacts in time to intercept the beam with ").append(second.hisHer()).append(" own blast.  The glowing line between ").append(first.getMainName()).append("'s hand and ").append(second.getMainName()).append("'s annihilates everything it touches as the two of them run toward the Demonic spire.  When it cuts into the base of the tower, the opposing energies cause a huge explosion that throws the two Chosen in different directions.  After they've come to their senses, they see the structure beginning to tilt to one side.  It finally topples, throwing up a huge cloud of debris as it lands on the city below.  As quickly as that, the Demonic presence over the city lifts.\n\nThe battle is over.  But even though this Demon Lord has been defeated, the scars left on the hearts of the Chosen won't heal so easily.  ").toString());
            }
            if(fallen[0] != null)
                w.append(t, (new StringBuilder(String.valueOf(first.getMainName()))).append(" and ").append(second.getMainName()).append(" have their own troubles to deal with, and ").append(fallen[0].getMainName()).append(" is nowhere to be found...").toString());
            else
                w.append(t, "Their troubles may be just beginning...");
        } else
        {
            w.append(t, (new StringBuilder("After exterminating the intervening Demons, ")).append(escaped[0].getMainName()).append(" attacks the Demonic spire, drawing on as much power as ").append(escaped[0].heShe()).append(" can in an attempt to destroy it.  ").toString());
            if(fallen[1] != null)
                w.append(t, (new StringBuilder("But with the other two Chosen having succumbed to the Demons, ")).append(escaped[0].heShe()).append("'s finding that ").append(escaped[0].heShe()).append(" isn't able to make a dent in it on ").append(escaped[0].hisHer()).append(" own.").toString());
            else
            if(killed[1] != null)
                w.append(t, (new StringBuilder("But as the only survivor among the three Chosen, ")).append(escaped[0].heShe()).append("'s finding that ").append(escaped[0].heShe()).append(" isn't able to make a dent in it on ").append(escaped[0].hisHer()).append(" own.").toString());
            else
                w.append(t, (new StringBuilder("But with ")).append(killed[0].getMainName()).append(" dead and ").append(fallen[0].getMainName()).append(" having succumbed to the Demons, ").append(escaped[0].getMainName()).append(" is finding that ").append(escaped[0].heShe()).append(" isn't able to make a dent in it on ").append(escaped[0].hisHer()).append(" own.").toString());
            escaped[0].say(t, "\n\n\"");
            as12 = new String[5];
            as12[0] = escaped[0].mainName;
            changePortrait(escaped[0].convertGender(), escaped[0].type, Boolean.valueOf(false), Boolean.valueOf(false), w, as12, 0, Emotion.SHAME, Emotion.SHAME);
            if(escaped[0].getConfidence() > 66)
            {
                as13 = new String[5];
                as13[0] = escaped[0].mainName;
                changePortrait(escaped[0].convertGender(), escaped[0].type, Boolean.valueOf(false), Boolean.valueOf(false), w, as13, 0, Emotion.STRUGGLE, Emotion.STRUGGLE);
                escaped[0].say(t, "No!  I... I should be strong enough...!");
            } else
            if(escaped[0].getConfidence() > 33)
                escaped[0].say(t, "Ugh...  We were so close...");
            else
                escaped[0].say(t, "I'm... I'm too weak after all...");
            escaped[0].say(t, "\"\n\n");
            if(escaped[0].isDrained().booleanValue())
                w.append(t, (new StringBuilder(String.valueOf(escaped[0].getMainName()))).append(" falls to ").append(escaped[0].hisHer()).append(" knees, overwhelmed by despair.  ").append(escaped[0].HeShe()).append(" spots a nearby knife discarded by a Thrall and takes it in ").append(escaped[0].hisHer()).append(" hands.  Before the fight, ").append(escaped[0].heShe()).append(" was worried about hurting ").append(escaped[0].himHer()).append("self too badly to fight, but not badly enough to actually be fatal.  But now, ").append(escaped[0].heShe()).append(" has nothing left to lose...").toString());
            else
            if(escaped[0].isParasitized().booleanValue())
            {
                w.append(t, (new StringBuilder(String.valueOf(escaped[0].getMainName()))).append(" turns and flies away, fleeing the battle.  However, ").append(escaped[0].hisHer()).append(" clothes begin to flicker and fade, and ").append(escaped[0].heShe()).append(" has a harder and harder time staying airborne.  Combined with ").append(escaped[0].hisHer()).append(" damaged reputation, this last failure has proven fatal for ").append(escaped[0].hisHer()).append(" connection to the public's psychic energy.  By the time ").append(escaped[0].heShe()).append(" reaches the neighboring city, ").append(escaped[0].heShe()).append("'ll return to being nothing more than ").toString());
                if(!escaped[0].getMainName().equals(escaped[0].getGivenName()))
                    w.append(t, (new StringBuilder(String.valueOf(escaped[0].getGivenName()))).append(", ").toString());
                w.append(t, "a typical powerless human.");
            } else
            if(escaped[0].isImpregnated().booleanValue())
                w.append(t, (new StringBuilder(String.valueOf(escaped[0].getMainName()))).append(" turns and flies away, fleeing the battle.  ").append(escaped[0].HeShe()).append(" has no intention of returning to the military and reporting ").append(escaped[0].hisHer()).append(" failure, because ").append(escaped[0].heShe()).append(" knows that soon, ").append(escaped[0].hisHer()).append(" Demonic pregnancy will begin to show.  ").append(escaped[0].HisHer()).append(" life as one of the Chosen is over, and ").append(escaped[0].hisHer()).append(" life as a fugitive begins...").toString());
            else
            if(escaped[0].isHypnotized().booleanValue())
                w.append(t, (new StringBuilder("As much as it pains ")).append(escaped[0].himHer()).append(" to do so, ").append(escaped[0].getMainName()).append(" turns and flees.  This battle may be lost, but ").append(escaped[0].heShe()).append("'s determined to escape and survive to fight another day.  However, even after ").append(escaped[0].heShe()).append(" escapes the range of your influence, your post-hypnotic commands continue to linger in ").append(escaped[0].hisHer()).append(" subconscious.  It remains to be seen what sort of depravity ").append(escaped[0].heShe()).append("'ll get into...").toString());
            else
                w.append(t, (new StringBuilder("With no other options remaining, ")).append(escaped[0].getMainName()).append(" turns to flee.").toString());
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
            if(!w.getCast()[i].alive.booleanValue())
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
            if(escaped.isDrained().booleanValue())
                type = 3;
            else
            if(escaped.isImpregnated().booleanValue())
                type = 1;
            else
            if(escaped.isParasitized().booleanValue())
                type = 4;
            else
            if(escaped.isHypnotized().booleanValue())
                type = 2;
            final int sceneShown = type;
            final Chosen sceneSubject = escaped;
            if(type != 0)
            {
                p.removeAll();
                JButton Continue = new JButton("Continue");
                Continue.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        w.ending(t, sceneShown, sceneSubject, null, null);
                        Project.ReportScore(t, p, f, w);
                    }

                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final int val$sceneShown;
                    private final Chosen val$sceneSubject;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                sceneShown = i;
                sceneSubject = chosen;
                p = jpanel;
                f = jframe;
                super();
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
                    if(w.getCast()[i].alive.booleanValue() && w.getCast()[i].resolve <= 0)
                    {
                        corrupted[forsaken] = w.getCast()[i];
                        forsaken++;
                    } else
                    if(w.getCast()[i].alive.booleanValue())
                    {
                        escaped[returning] = w.getCast()[i];
                        returning++;
                    } else
                    {
                        killed[casualties] = w.getCast()[i];
                        casualties++;
                    }

                if(!w.isCheater().booleanValue() && w.hardMode.booleanValue())
                    w.scoreSummary(t);
                w.finalBattle = Boolean.valueOf(false);
                w.getTechs()[48].owned = Boolean.valueOf(false);
                for(int i = 0; i < 3; i++)
                {
                    w.getCast()[i].alive = Boolean.valueOf(true);
                    w.getCast()[i].resolve = 100;
                }

                w.incrementDay();
                w.clearCommander();
                for(int i = 0; i < 3; i++)
                {
                    w.getCast()[i].addTrauma();
                    w.getCast()[i].surrounded = Boolean.valueOf(false);
                    w.getCast()[i].captured = Boolean.valueOf(false);
                    w.getCast()[i].removeSurround = Boolean.valueOf(false);
                }

                if(forsaken + casualties >= 2 || !w.campaign.booleanValue())
                {
                    JButton ContinueFour = new JButton("Continue Playing");
                    ContinueFour.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            Project.Shop(t, p, f, w);
                        }

                        final _cls244 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;

                    
                    {
                        this$1 = _cls244.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                    });
                    if(w.campaign.booleanValue())
                        ContinueFour.setText("Continue");
                    p.add(ContinueFour);
                }
                if((w.isCheater().booleanValue() || !w.hardMode.booleanValue()) && !w.campaign.booleanValue())
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nI hope you enjoyed this playthrough of Corrupted Saviors!  For an even greater challenge, consider trying Hard Mode or Campaign Mode!").toString());
                if(w.campaign.booleanValue())
                    if(forsaken + casualties >= 2)
                    {
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nThe city of ").append(w.cityName).append(" has fallen under your control.  ").toString());
                        if(w.day < 50 - w.eventOffset)
                            w.append(t, "Your followers won't be able to establish a foothold in another city right away, so you can take some time to consolidate power here and enjoy your conquest.");
                        else
                            w.append(t, "Now all that remains is to decide where to strike next.");
                        w.loopComplete = Boolean.valueOf(true);
                    } else
                    {
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWith your defeat in ").append(w.cityName).append(", the momentum of your advance across the globe has been halted.  However, so long as there is darkness within the human heart, a Demon Lord cannot be truly killed.  Before long, you will rise again.").toString());
                        if(w.conquered.length > 0 || w.sacrificed.length > 0)
                            w.append(t, "  And in the meantime, there's much enjoyment to be had from your conquests...");
                    }
                if(forsaken > 0)
                {
                    w.append(t, "\n\n");
                    if(forsaken == 1)
                        w.append(t, (new StringBuilder(String.valueOf(corrupted[0].getMainName()))).append(" has ").toString());
                    else
                    if(forsaken == 2)
                        w.append(t, (new StringBuilder(String.valueOf(corrupted[0].getMainName()))).append(" and ").append(corrupted[1].getMainName()).append(" have ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(corrupted[0].getMainName()))).append(", ").append(corrupted[1].getMainName()).append(", and ").append(corrupted[2].getMainName()).append(" have ").toString());
                    if(!w.campaign.booleanValue())
                        w.append(t, "been added to the ranks of the Forsaken!  You can interact with them from the Main Menu, and you may also use them to help corrupt new Chosen in future playthroughs!");
                    else
                    if(forsaken + casualties >= 2)
                        w.append(t, "been added to the ranks of the Forsaken!  This will surely prove useful against the Chosen you've yet to face.");
                    else
                        w.append(t, "been added to the ranks of the Forsaken!  This makes for a fine consolation prize.");
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
                    File saveLocation = new File((new StringBuilder(String.valueOf(path))).append(File.separator).append("saves.sav").toString());
                    SaveData saves = null;
                    if(saveLocation.exists())
                    {
                        ReadObject robj = new ReadObject();
                        saves = robj.deserializeSaveData((new StringBuilder(String.valueOf(path))).append(File.separator).append("saves.sav").toString());
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
                            if(!w.campaign.booleanValue() && saves.harem == null)
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
                                    Boolean alreadyThere = Boolean.valueOf(false);
                                    for(int m = 0; m < w.getHarem()[j].others.length; m++)
                                        if(newForsaken.equals(w.getHarem()[j].others[m]).booleanValue())
                                            alreadyThere = Boolean.valueOf(true);

                                    if(!alreadyThere.booleanValue() && w.getHarem()[j].otherChosen[k].equals(corrupted[i]).booleanValue())
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

                            if(w.campaign.booleanValue())
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
                    if(!w.campaign.booleanValue())
                    {
                        JButton ForsakenMenu = new JButton("Forsaken Menu");
                        ForsakenMenu.addActionListener(new ActionListener() {

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

                            final _cls244 this$1;
                            private final JTextPane val$t;
                            private final WorldState val$w;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final SaveData val$fileUsed;

                    
                    {
                        this$1 = _cls244.this;
                        t = jtextpane;
                        w = worldstate;
                        p = jpanel;
                        f = jframe;
                        fileUsed = savedata;
                        super();
                    }
                        });
                        p.add(ForsakenMenu);
                    }
                }
                if(w.campaign.booleanValue())
                {
                    int numberDiscarded = 0;
                    int numberKept = 0;
                    Chosen discarded[] = new Chosen[3];
                    Chosen kept[] = new Chosen[3];
                    for(int i = 0; i < 3; i++)
                        if(escaped[i] != null)
                            if(escaped[i].impregnated.booleanValue() || escaped[i].hypnotized.booleanValue() || escaped[i].drained.booleanValue() || escaped[i].parasitized.booleanValue())
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
                if(w.campaign.booleanValue() && forsaken + casualties < 2)
                    if(w.conquered.length > 0 || w.sacrificed.length > 0)
                    {
                        w.append(t, "\n\n");
                        Project.WrapUpCampaign(t, p, f, w, null, null);
                    } else
                    {
                        w.append(t, "\n\nThank you for playing the campaign mode of Corrupted Saviors!  If you're having trouble with the game, consider trying out Single Play mode, which allows the use of cheats after the final battle regardless of the result.");
                        JButton Continue = new JButton("Main Menu");
                        Continue.addActionListener(new ActionListener() {

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

                            final _cls244 this$1;
                            private final JTextPane val$t;
                            private final WorldState val$w;
                            private final JPanel val$p;
                            private final JFrame val$f;

                    
                    {
                        this$1 = _cls244.this;
                        t = jtextpane;
                        w = worldstate;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                        });
                        p.add(Continue);
                    }
                p.validate();
                p.repaint();
            }

            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JFrame val$f;

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                super();
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
                broughtConquered[i] = Boolean.valueOf(true);

            broughtSacrificed = new Boolean[sacrificed];
            for(int i = 0; i < sacrificed; i++)
                broughtSacrificed[i] = Boolean.valueOf(true);

        } else
        {
            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
        }
        if(conquered > 0 && sacrificed > 0)
            w.append(t, "You can bring your Forsaken with you to use in Single Play mode, including the Forsaken who were disposed of during play.");
        else
        if(sacrificed > 0)
            w.append(t, "Even though you didn't end the game with any Forsaken, you can still bring with you the ones you disposed of during play.");
        else
            w.append(t, "You can bring your Forsaken with you to use in Single Play mode.");
        Boolean saveAllConquered = Boolean.valueOf(false);
        Boolean deleteAllConquered = Boolean.valueOf(false);
        Boolean saveAllSacrificed = Boolean.valueOf(false);
        Boolean deleteAllSacrificed = Boolean.valueOf(false);
        if(conquered > 0)
        {
            w.append(t, "\n\nCurrent Forsaken\n");
            for(int i = 0; i < conquered; i++)
            {
                w.append(t, (new StringBuilder("\n")).append(w.conquered[i].mainName).append(": ").toString());
                if(broughtConquered[i].booleanValue())
                {
                    w.greenAppend(t, "SAVE");
                    deleteAllConquered = Boolean.valueOf(true);
                } else
                {
                    w.redAppend(t, "DELETE");
                    saveAllConquered = Boolean.valueOf(true);
                }
            }

        }
        if(sacrificed > 0)
        {
            w.append(t, "\n\nFormer Forsaken\n");
            for(int i = 0; i < sacrificed; i++)
            {
                w.append(t, (new StringBuilder("\n")).append(w.sacrificed[i].mainName).append(": ").toString());
                if(broughtSacrificed[i].booleanValue())
                {
                    w.greenAppend(t, "SAVE");
                    deleteAllSacrificed = Boolean.valueOf(true);
                } else
                {
                    w.redAppend(t, "DELETE");
                    saveAllSacrificed = Boolean.valueOf(true);
                }
            }

        }
        final Boolean conqueredSetting[] = broughtConquered;
        final Boolean sacrificedSetting[] = broughtSacrificed;
        if(saveAllConquered.booleanValue())
        {
            JButton Save = new JButton("Save All Current");
            Save.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Boolean newSaved[] = new Boolean[conquered];
                    for(int i = 0; i < conquered; i++)
                        newSaved[i] = Boolean.valueOf(true);

                    Project.WrapUpCampaign(t, p, f, w, newSaved, sacrificedSetting);
                }

                private final int val$conquered;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Boolean val$sacrificedSetting[];

            
            {
                conquered = i;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                sacrificedSetting = aboolean;
                super();
            }
            });
            p.add(Save);
        }
        if(deleteAllConquered.booleanValue())
        {
            JButton Delete = new JButton("Delete All Current");
            Delete.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Boolean newSaved[] = new Boolean[conquered];
                    for(int i = 0; i < conquered; i++)
                        newSaved[i] = Boolean.valueOf(false);

                    Project.WrapUpCampaign(t, p, f, w, newSaved, sacrificedSetting);
                }

                private final int val$conquered;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Boolean val$sacrificedSetting[];

            
            {
                conquered = i;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                sacrificedSetting = aboolean;
                super();
            }
            });
            p.add(Delete);
        }
        if(saveAllSacrificed.booleanValue())
        {
            JButton Save = new JButton("Save All Former");
            Save.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Boolean newSaved[] = new Boolean[sacrificed];
                    for(int i = 0; i < sacrificed; i++)
                        newSaved[i] = Boolean.valueOf(true);

                    Project.WrapUpCampaign(t, p, f, w, conqueredSetting, newSaved);
                }

                private final int val$sacrificed;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Boolean val$conqueredSetting[];

            
            {
                sacrificed = i;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                conqueredSetting = aboolean;
                super();
            }
            });
            p.add(Save);
        }
        if(deleteAllSacrificed.booleanValue())
        {
            JButton Save = new JButton("Delete All Former");
            Save.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Boolean newSaved[] = new Boolean[sacrificed];
                    for(int i = 0; i < sacrificed; i++)
                        newSaved[i] = Boolean.valueOf(false);

                    Project.WrapUpCampaign(t, p, f, w, conqueredSetting, newSaved);
                }

                private final int val$sacrificed;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Boolean val$conqueredSetting[];

            
            {
                sacrificed = i;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                conqueredSetting = aboolean;
                super();
            }
            });
            p.add(Save);
        }
        JButton Decide = new JButton("Decide Individually");
        Decide.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.DecideKeptForsaken(t, p, f, w, conqueredSetting, sacrificedSetting, 0);
            }

            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Boolean val$conqueredSetting[];
            private final Boolean val$sacrificedSetting[];

            
            {
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                conqueredSetting = aboolean;
                sacrificedSetting = aboolean1;
                super();
            }
        });
        p.add(Decide);
        JButton Done = new JButton("Done");
        Done.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nAre you sure?  ").toString());
                int brought = 0;
                int deleted = 0;
                for(int i = 0; i < conquered; i++)
                    if(conqueredSetting[i].booleanValue())
                        brought++;
                    else
                        deleted++;

                for(int i = 0; i < sacrificed; i++)
                    if(sacrificedSetting[i].booleanValue())
                        brought++;
                    else
                        deleted++;

                if(deleted == 0)
                    w.append(t, "All Forsaken from this playthrough will be added to the save file.");
                else
                if(brought == 0)
                    w.append(t, "All Forsaken from this playthrough will be deleted and can only be recovered by loading an old campaign save.");
                else
                    w.append(t, (new StringBuilder(String.valueOf(brought))).append(" Forsaken from this playthrough will be added to the save file and the other ").append(deleted).append(" will be deleted.  Deleted Forsaken can only be recovered by loading an old campaign save.").toString());
                final int totalBrought = brought;
                JButton Confirm = new JButton("Confirm");
                Confirm.addActionListener(new ActionListener() {

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
                                if(conqueredSetting[i].booleanValue())
                                {
                                    newHarem[w.save.harem.length + additional] = w.conquered[i];
                                    additional++;
                                }

                            for(int i = 0; i < sacrificed; i++)
                                if(sacrificedSetting[i].booleanValue())
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

                    final _cls250 this$1;
                    private final int val$totalBrought;
                    private final WorldState val$w;
                    private final int val$conquered;
                    private final Boolean val$conqueredSetting[];
                    private final int val$sacrificed;
                    private final Boolean val$sacrificedSetting[];
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls250.this;
                        totalBrought = i;
                        w = worldstate;
                        conquered = j;
                        conqueredSetting = aboolean;
                        sacrificed = k;
                        sacrificedSetting = aboolean1;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                });
                p.add(Confirm);
                JButton Cancel = new JButton("Cancel");
                Cancel.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Project.WrapUpCampaign(t, p, f, w, conqueredSetting, sacrificedSetting);
                    }

                    final _cls250 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final Boolean val$conqueredSetting[];
                    private final Boolean val$sacrificedSetting[];

                    
                    {
                        this$1 = _cls250.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        conqueredSetting = aboolean;
                        sacrificedSetting = aboolean1;
                        super();
                    }
                });
                p.add(Cancel);
                p.validate();
                p.repaint();
            }

            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final int val$conquered;
            private final Boolean val$conqueredSetting[];
            private final int val$sacrificed;
            private final Boolean val$sacrificedSetting[];
            private final JFrame val$f;

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                conquered = i;
                conqueredSetting = aboolean;
                sacrificed = j;
                sacrificedSetting = aboolean1;
                f = jframe;
                super();
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
            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhat will you do with ").append(w.conquered[position].mainName).append("?").toString());
            JButton Save = new JButton("Save");
            Save.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    broughtConquered[position] = Boolean.valueOf(true);
                    Project.DecideKeptForsaken(t, p, f, w, broughtConquered, broughtSacrificed, position + 1);
                }

                private final Boolean val$broughtConquered[];
                private final int val$position;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Boolean val$broughtSacrificed[];

            
            {
                broughtConquered = aboolean;
                position = i;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                broughtSacrificed = aboolean1;
                super();
            }
            });
            p.add(Save);
            JButton Delete = new JButton("Delete");
            Delete.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    broughtConquered[position] = Boolean.valueOf(false);
                    Project.DecideKeptForsaken(t, p, f, w, broughtConquered, broughtSacrificed, position + 1);
                }

                private final Boolean val$broughtConquered[];
                private final int val$position;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Boolean val$broughtSacrificed[];

            
            {
                broughtConquered = aboolean;
                position = i;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                broughtSacrificed = aboolean1;
                super();
            }
            });
            p.add(Delete);
        } else
        if(position < broughtConquered.length + broughtSacrificed.length)
        {
            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhat will you do with ").append(w.sacrificed[position - broughtConquered.length].mainName).append("?").toString());
            JButton Save = new JButton("Save");
            Save.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    broughtSacrificed[position - broughtConquered.length] = Boolean.valueOf(true);
                    Project.DecideKeptForsaken(t, p, f, w, broughtConquered, broughtSacrificed, position + 1);
                }

                private final Boolean val$broughtSacrificed[];
                private final int val$position;
                private final Boolean val$broughtConquered[];
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;

            
            {
                broughtSacrificed = aboolean;
                position = i;
                broughtConquered = aboolean1;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
            });
            p.add(Save);
            JButton Delete = new JButton("Delete");
            Delete.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    broughtSacrificed[position - broughtConquered.length] = Boolean.valueOf(false);
                    Project.DecideKeptForsaken(t, p, f, w, broughtConquered, broughtSacrificed, position + 1);
                }

                private final Boolean val$broughtSacrificed[];
                private final int val$position;
                private final Boolean val$broughtConquered[];
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;

            
            {
                broughtSacrificed = aboolean;
                position = i;
                broughtConquered = aboolean1;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
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
        if(!w.loopComplete.booleanValue())
            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
        for(int i = 0; i < w.getHarem().length; i++)
        {
            w.getHarem()[i].visited = Boolean.valueOf(false);
            if(w.getHarem()[i].others != null)
            {
                for(int j = 0; j < w.getHarem()[i].others.length; j++)
                {
                    Boolean present = Boolean.valueOf(false);
                    for(int k = 0; k < w.getHarem().length; k++)
                        if(w.getHarem()[k].equals(w.getHarem()[i].others[j]).booleanValue())
                            present = Boolean.valueOf(true);

                    if(present.booleanValue())
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
                Boolean notExhausted = Boolean.valueOf(true);
                for(int j = 0; j < exhausted.length; j++)
                    if(w.getHarem()[i].equals(exhausted[j]).booleanValue())
                        notExhausted = Boolean.valueOf(false);

                if(notExhausted.booleanValue())
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
                        Boolean found = Boolean.valueOf(false);
                        for(int j = 0; j < w.getHarem()[i].others.length; j++)
                            if(w.getHarem()[i].others[j].equals(tantruming).booleanValue())
                            {
                                found = Boolean.valueOf(true);
                                w.getHarem()[i].troublemaker[j] += offense;
                            }

                        if(!found.booleanValue())
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
                    w.append(t, (new StringBuilder(String.valueOf(tantruming.mainName))).append(" tries to organize a resistance against you, ").toString());
                    if(tantruming.confidence > 66)
                        w.append(t, (new StringBuilder("demanding that your other minions join ")).append(tantruming.himHer()).append(".  ").toString());
                    else
                    if(tantruming.confidence > 33)
                        w.append(t, "appealing to your other minions' sense of morality.  ");
                    else
                        w.append(t, "begging and pleading for your other minions to find their conscience.  ");
                    w.append(t, "Even for those who are inclined to listen to such arguments, the effort is more annoying than persuasive.");
                } else
                if(tantruming.hostility < 40)
                {
                    w.append(t, (new StringBuilder(String.valueOf(tantruming.mainName))).append(" lets out ").append(tantruming.hisHer()).append(" frustrations on your other minions, ").toString());
                    if(tantruming.confidence > 66)
                        w.append(t, (new StringBuilder("aggressively asserting ")).append(tantruming.hisHer()).append(" dominance over anyone who can't or won't stand up to ").append(tantruming.himHer()).append(".  ").toString());
                    else
                    if(tantruming.confidence > 33)
                        w.append(t, (new StringBuilder("spitting insults at anyone who so much as looks at ")).append(tantruming.himHer()).append(" funny.  ").toString());
                    else
                        w.append(t, (new StringBuilder("passive-aggressively insulting anyone who makes the mistake of spending too much time around ")).append(tantruming.himHer()).append(".  ").toString());
                    w.append(t, (new StringBuilder("Even for everyone else, ")).append(tantruming.hisHer()).append(" acting out is a constant annoyance.").toString());
                } else
                if(tantruming.hostility < 61)
                {
                    w.append(t, (new StringBuilder(String.valueOf(tantruming.mainName))).append(" makes a scene in the middle of your base of operations, ").toString());
                    if(tantruming.confidence > 66)
                        w.append(t, (new StringBuilder("ranting, raving, and blaming everyone else for all ")).append(tantruming.hisHer()).append(" problems.  ").toString());
                    else
                    if(tantruming.confidence > 33)
                        w.append(t, (new StringBuilder("shouting about how ")).append(tantruming.heShe()).append(" hates being one of the Forsaken.  ").toString());
                    else
                        w.append(t, "wailing in despair and whining about how unfair the world is.  ");
                    w.append(t, "The disruptive behavior is bad for your other minions' morale.");
                } else
                if(tantruming.hostility < 81)
                {
                    w.append(t, (new StringBuilder(String.valueOf(tantruming.mainName))).append(" gets violent with your other minions, ").toString());
                    if(tantruming.confidence > 66)
                        w.append(t, (new StringBuilder("challenging them to fight ")).append(tantruming.himHer()).append(" head-on, and outright attacking those who try to flee.  ").toString());
                    else
                    if(tantruming.confidence > 33)
                        w.append(t, "picking fights and getting into several scuffles over the course of the night.  ");
                    else
                        w.append(t, "abruptingly attacking them from behind and then fleeing before they can retaliate.  ");
                    w.append(t, (new StringBuilder("The anger and resentment directed at ")).append(tantruming.himHer()).append(" grows.").toString());
                } else
                {
                    w.append(t, (new StringBuilder(String.valueOf(tantruming.mainName))).append(" goes on a murderous rampage, ").toString());
                    if(tantruming.confidence > 66)
                        w.append(t, "carving a wide and indiscriminate swath of destruction through your base of operations.  ");
                    else
                    if(tantruming.confidence > 33)
                        w.append(t, (new StringBuilder("hunting down and attacking anyone ")).append(tantruming.heShe()).append(" feels has wronged ").append(tantruming.himHer()).append(".  ").toString());
                    else
                        w.append(t, (new StringBuilder("slipping poison into the meals of countless Thralls and others ")).append(tantruming.heShe()).append(" has a grudge against before ").append(tantruming.heShe()).append("'s caught.  ").toString());
                    w.append(t, "The resulting chaos affects your other minions as well.");
                }
                w.append(t, (new StringBuilder("  (+")).append(tantruming.staminaRegen() / 10).append(".").append(tantruming.staminaRegen() % 10).append("% Stamina, restores own Motivation at expense of everyone else)").toString());
            } else
            {
                w.append(t, (new StringBuilder(String.valueOf(tantruming.mainName))).append(" is too stressed to relax, but there aren't any other Forsaken around for ").append(tantruming.himHer()).append(" to release ").append(tantruming.hisHer()).append(" tension on. (+").append(tantruming.staminaRegen()).append(" Stamina)").toString());
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
                    w.append(t, (new StringBuilder("Now that ")).append(included[i].mainName).append(" is no longer one of the Chosen, the child in ").append(included[i].hisHer()).append(" belly is just a regular Demon, and ").append(included[i].heShe()).append(" quickly goes into labor.  The resulting abomination ").toString());
                    if(included[i].gender.equals(Forsaken.Gender.MALE))
                        w.append(t, (new StringBuilder("forces its way out of ")).append(included[i].mainName).append("'s asshole").toString());
                    else
                        w.append(t, (new StringBuilder("slides out of ")).append(included[i].mainName).append("'s distended vagina").toString());
                    w.append(t, (new StringBuilder(" while ")).append(included[i].heShe()).append(" ").toString());
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
                        w.append(t, (new StringBuilder("Due to ")).append(included[i].hisHer()).append(" nighttime activities, ").append(included[i].mainName).append(" has been impregnated with another fast-growing Demonic child.  ").append(included[i].HeShe()).append(" gives birth to a small tentacled creature, ").toString());
                        if(included[i].innocence > 66)
                            w.append(t, "then happily waves goodbye as it slithers away.");
                        else
                        if(included[i].innocence > 33)
                            w.append(t, (new StringBuilder("which leaves ")).append(included[i].himHer()).append(" gasping for breath.").toString());
                        else
                            w.append(t, (new StringBuilder("then mentally collects ")).append(included[i].himHer()).append("self and continues about ").append(included[i].hisHer()).append(" business.").toString());
                    } else
                    if(included[i].timesKilled > 2 && (int)(Math.random() * 2D) == 0)
                    {
                        included[i].timesKilled++;
                        w.append(t, (new StringBuilder("A particularly bold Thrall ambushes ")).append(included[i].mainName).append(" while ").append(included[i].heShe()).append("'s alone and tries to rape ").append(included[i].himHer()).toString());
                        if(included[i].morality > 66)
                            w.append(t, (new StringBuilder(", and ")).append(included[i].mainName).append(" is happy afterwards to note that ").append(included[i].heShe()).append(" doesn't feel guilty in the slightest about killing him.").toString());
                        else
                        if(included[i].morality > 33)
                            w.append(t, (new StringBuilder(", but the Forsaken has no trouble overpowering and killing ")).append(included[i].hisHer()).append(" attacker.").toString());
                        else
                            w.append(t, (new StringBuilder(", and ")).append(included[i].mainName).append(" enjoys giving him an especially slow and painful death.").toString());
                    } else
                    if(included[i].timesHadSex > 0 && ((int)(Math.random() * 2D) == 0 || included[i].peopleInjured == 0))
                    {
                        included[i].timesHadSex += 3 + (int)(Math.random() * 3D);
                        included[i].orgasmsGiven += 5 + (int)(Math.random() * 5D);
                        if(included[i].timesOrgasmed > 0)
                            included[i].timesOrgasmed++;
                        w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" attends a wild party and ends up participating in an orgy, ").toString());
                        if(included[i].confidence > 66)
                            w.append(t, "gleefully dominating several partners at once.");
                        else
                        if(included[i].confidence > 33)
                            w.append(t, (new StringBuilder("enjoying ")).append(included[i].himHer()).append("self greatly.").toString());
                        else
                            w.append(t, (new StringBuilder("surrendering ")).append(included[i].himHer()).append("self to the lustful crowd.").toString());
                    } else
                    if(included[i].peopleInjured > 0)
                    {
                        included[i].peopleInjured++;
                        w.append(t, (new StringBuilder("A particularly bold Thrall ambushes ")).append(included[i].mainName).append(" while ").append(included[i].heShe()).append("'s alone and tries to rape ").append(included[i].himHer()).toString());
                        if(included[i].morality > 66)
                            w.append(t, (new StringBuilder(", but ")).append(included[i].heShe()).append(" has no trouble fending him off.").toString());
                        else
                        if(included[i].morality > 33)
                            w.append(t, ", only to receive a sound beating.");
                        else
                            w.append(t, (new StringBuilder(", only to be left with some very painful injuries in ")).append(included[i].hisHer()).append(" wake.").toString());
                    } else
                    if(included[i].morality > 66)
                        w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" spends ").append(included[i].hisHer()).append(" time helping out your weaker minions, protecting them from danger and boosting their spirits.").toString());
                    else
                    if(included[i].morality > 33)
                        w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" hangs out with some of the friends ").append(included[i].heShe()).append("'s made among your minions.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" spends some time trying to bargain with you for better accommomdations, but to no avail.").toString());
                } else
                if(flavor == 1)
                {
                    if(included[i].hypnotized.booleanValue() && (int)(Math.random() * 2D) == 0)
                    {
                        w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" sleeps through most of the day, having vivid dreams as you reach directly into ").append(included[i].hisHer()).toString());
                        if(included[i].innocence > 66)
                            w.append(t, (new StringBuilder(" simple mind and rearrange ")).append(included[i].hisHer()).append(" instinctive impulses to your liking.").toString());
                        else
                        if(included[i].innocence > 33)
                            w.append(t, (new StringBuilder(" subconscious in order to reinforce ")).append(included[i].hisHer()).append(" hypnotic conditioning.").toString());
                        else
                            w.append(t, (new StringBuilder(" mind and carefully influence ")).append(included[i].hisHer()).append(" thought process in order to prevent ").append(included[i].himHer()).append(" from finding a way to break your hypnotism.").toString());
                    } else
                    if(included[i].strongestOrgasm >= 1000 && (int)(Math.random() * 2D) == 0)
                    {
                        w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" spends the day enjoying the company of several tentacled Demons").toString());
                        if(included[i].dignity > 66)
                            w.append(t, (new StringBuilder(", but while ")).append(included[i].heShe()).append(" tries to pretend that ").append(included[i].heShe()).append("'s just inspecting ").append(included[i].hisHer()).append(" forces, the truth is that ").append(included[i].heShe()).append("'s having them make ").append(included[i].himHer()).append(" cum over and over again.").toString());
                        else
                        if(included[i].dignity > 33)
                            w.append(t, (new StringBuilder(", allowing them to ravage ")).append(included[i].himHer()).append(" with their many appendages.").toString());
                        else
                            w.append(t, (new StringBuilder(", and soon ")).append(included[i].heShe()).append("'s screaming at the top of ").append(included[i].hisHer()).append(" lungs as ").append(included[i].heShe()).append("'s gripped by a long, continuous climax.").toString());
                        included[i].timesOrgasmed += 10 + (int)(Math.random() * 10D);
                    } else
                    if(included[i].strongestOrgasm >= 200 && ((int)(Math.random() * 2D) == 0 || included[i].orgasmsGiven < 1000))
                    {
                        included[i].timesOrgasmed += 4 + (int)(Math.random() * 4D);
                        if(included[i].confidence > 66)
                            w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" decides that ").append(included[i].heShe()).append(" needs a day to relax.  ").append(included[i].HeShe()).append(" spends much of it masturbating.").toString());
                        else
                        if(included[i].confidence > 33)
                            w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" tries to manage ").append(included[i].hisHer()).append(" lust by spending some time masturbating.  ").append(included[i].HeShe()).append(" ends up doing it for most of the day.").toString());
                        else
                            w.append(t, (new StringBuilder("Overcome by the Demonic influence in the air, ")).append(included[i].mainName).append(" hides in ").append(included[i].hisHer()).append(" room and starts to quietly masturbate, jumping in alarm whenever ").append(included[i].heShe()).append(" hears movement outside.").toString());
                    } else
                    if(included[i].orgasmsGiven >= 1000)
                    {
                        if(included[i].timesOrgasmed > 0)
                            included[i].timesOrgasmed += 2 + (int)(Math.random() * 2D);
                        if(included[i].innocence > 66)
                            w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" reads pornographic comics all day, marvelling at what ").append(included[i].heShe()).append(" sees.").toString());
                        else
                        if(included[i].innocence > 33)
                            w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" spends the day playing pornographic computer games.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" spends the day studying and theorizing about methods to more efficiently force an unwilling target to orgasm.").toString());
                    } else
                    if(included[i].innocence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" plays video games all day, forgetting for awhile where ").append(included[i].heShe()).append(" is.").toString());
                    else
                    if(included[i].innocence > 33)
                        w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" relaxes and spends ").append(included[i].hisHer()).append(" evening watching DVDs smuggled in from the outside world.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" spends most of the day reading scholarly articles on psychography.").toString());
                } else
                if(flavor == 2)
                {
                    if(included[i].drained.booleanValue() && (int)(Math.random() * 2D) == 0)
                    {
                        if(included[i].confidence > 66)
                        {
                            included[i].timesHarmedSelf++;
                            w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" whips ").append(included[i].himHer()).append("self until ").append(included[i].hisHer()).append(" back begins to show the marks, stubbornly enduring the pain to remind ").append(included[i].himHer()).append("self not to oppose you.").toString());
                        } else
                        if(included[i].confidence > 33)
                            w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" asks to be drained of what little residual psychic energy remains inside ").append(included[i].himHer()).append(", submitting ").append(included[i].himHer()).append("self to you completely.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" begs you to punish ").append(included[i].himHer()).append(" for ever daring to oppose you, and after you use a spare Demonic body to lightly molest ").append(included[i].himHer()).append(", ").append(included[i].heShe()).append(" seems grateful and satisfied.").toString());
                    } else
                    if(included[i].timesHarmedSelf > 0 && (int)(Math.random() * 2D) == 0)
                    {
                        w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" isolates ").append(included[i].himHer()).append("self and spends the day in silent contemplation of your greatness, ").toString());
                        if(included[i].innocence > 66)
                            w.append(t, "though it doesn't amount to much more than mentally repeating 'The Demon Lord is Really Strong' over and over again.");
                        else
                        if(included[i].innocence > 33)
                            w.append(t, (new StringBuilder("reminding ")).append(included[i].himHer()).append("self that your will is absolute.").toString());
                        else
                            w.append(t, "attempting to understand the true nature of a Demon Lord.");
                    } else
                    if(included[i].timesTortured > 0 && ((int)(Math.random() * 2D) == 0 || !included[i].meek.booleanValue()))
                    {
                        if(included[i].confidence > 66)
                            w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" humbles ").append(included[i].himHer()).append("self by doing manual labor alongside your lesser minions in an attempt to show you ").append(included[i].hisHer()).append(" willingness to serve.").toString());
                        else
                        if(included[i].confidence > 33)
                            w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" keeps ").append(included[i].himHer()).append("self busy by doing manual labor with the Thralls at your base of operations, hopeful that you'll notice ").append(included[i].hisHer()).append(" efforts.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" presents ").append(included[i].himHer()).append("self to the Thrall in charge of constructing your base of operations, offering to help out in a show of submission.").toString());
                    } else
                    if(included[i].meek.booleanValue())
                    {
                        if(included[i].confidence > 66)
                            w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" is suffering from flashbacks to ").append(included[i].hisHer()).append(" past abuses, but ").append(included[i].heShe()).append(" forces ").append(included[i].himHer()).append("self to go outside and do ").append(included[i].hisHer()).append(" daily routine anyway, and ").append(included[i].heShe()).append(" feels satisfied about it once ").append(included[i].heShe()).append(" returns to ").append(included[i].hisHer()).append(" room for the night.").toString());
                        else
                        if(included[i].confidence > 33)
                            w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" feels worried about going outside, so ").append(included[i].heShe()).append(" just spends the day in ").append(included[i].hisHer()).append(" room.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" locks ").append(included[i].himHer()).append("self in ").append(included[i].hisHer()).append(" room, resting there until ").append(included[i].heShe()).append(" can overcome ").append(included[i].hisHer()).append(" old fears of being abused by the Thralls.").toString());
                    } else
                    if(included[i].confidence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" has a good day, and ").append(included[i].heShe()).append(" goes to bed in high spirits.").toString());
                    else
                    if(included[i].confidence > 33)
                        w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" spends a leisurely day doing nothing in particular.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" lifts weights in ").append(included[i].hisHer()).append(" room all day, desperate to become stronger.").toString());
                } else
                if(included[i].parasitized.booleanValue() && (int)(Math.random() * 2D) == 0)
                {
                    w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" spends the day with what's left of ").append(included[i].hisHer()).append(" fans, ").toString());
                    if(included[i].innocence > 66)
                        w.append(t, "not really even noticing that there are far fewer than before.");
                    else
                    if(included[i].innocence > 33)
                        w.append(t, (new StringBuilder("and even though there clearly aren't as many as before, ")).append(included[i].heShe()).append(" still enjoys ").append(included[i].himHer()).append("self.").toString());
                    else
                        w.append(t, (new StringBuilder("but ")).append(included[i].heShe()).append(" can't help but dwell on the fact that most of them have moved on to newer Chosen and Forsaken.").toString());
                    included[i].timesExposed += 10 + (int)(Math.random() * 10D);
                    included[i].timesExposedSelf += 10 + (int)(Math.random() * 10D);
                } else
                if(included[i].timesExposedSelf > 100 && (int)(Math.random() * 2D) == 0)
                {
                    w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" goes outside in the nude").toString());
                    if(included[i].dignity > 66)
                        w.append(t, (new StringBuilder(", greatly enjoying the extra attention it gets ")).append(included[i].himHer()).append(".").toString());
                    else
                    if(included[i].dignity > 33)
                        w.append(t, (new StringBuilder(", letting a few of your minions catch glimpses of ")).append(included[i].himHer()).append(" before returning home.").toString());
                    else
                        w.append(t, " as if it isn't any big deal.");
                    included[i].timesExposed++;
                    included[i].timesExposedSelf++;
                } else
                if(included[i].timesExposed > 0x186a0 && (int)(Math.random() * 2D) == 0 || !included[i].debased.booleanValue())
                {
                    w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" goes outside in ").toString());
                    if(included[i].dignity > 66)
                        w.append(t, (new StringBuilder("a dress that's practically transparent, not quite showing the details of ")).append(included[i].hisHer()).append(" private parts, but leaving very little to the imagination.").toString());
                    else
                    if(included[i].dignity > 33)
                        w.append(t, (new StringBuilder("a long shirt with nothing underneath, teasing your minions with the promise of catching a glimpse of ")).append(included[i].hisHer()).append(" most intimate places.").toString());
                    else
                        w.append(t, (new StringBuilder("a tiny miniskirt with no panties, and ")).append(included[i].heShe()).append(" makes no effort whatsoever to avoid flashing people whenever ").append(included[i].heShe()).append(" stretches or bends over.").toString());
                } else
                if(included[i].debased.booleanValue())
                {
                    w.append(t, (new StringBuilder("During ")).append(included[i].hisHer()).append(" daily routine, ").append(included[i].mainName).append(" is confronted by a Thrall with a recording of ").append(included[i].himHer()).append(" being humiliated, ").toString());
                    if(included[i].dignity > 66)
                        w.append(t, (new StringBuilder("but ")).append(included[i].mainName).append(" is pleasantly surprised to see that the Thrall is just an enthusiastic fan.").toString());
                    else
                    if(included[i].dignity > 33)
                        w.append(t, (new StringBuilder("but ")).append(included[i].mainName).append(" doesn't let it get to ").append(included[i].himHer()).append(".").toString());
                    else
                        w.append(t, (new StringBuilder("but ")).append(included[i].mainName).append(" is past the point of caring, and ").append(included[i].heShe()).append(" doesn't let it ruin ").append(included[i].hisHer()).append(" day.").toString());
                } else
                {
                    w.append(t, (new StringBuilder(String.valueOf(included[i].mainName))).append(" spends the day talking to a gathering of ").append(included[i].hisHer()).append(" fans, ").toString());
                    if(included[i].confidence > 66)
                        w.append(t, (new StringBuilder("happily regaling them with stories of ")).append(included[i].hisHer()).append(" time as one of the Chosen.").toString());
                    else
                    if(included[i].confidence > 33)
                        w.append(t, "chatting about what life is like under the Demon Lord.");
                    else
                        w.append(t, (new StringBuilder("blushing and stammering when ")).append(included[i].heShe()).append(" hears how much they still love ").append(included[i].himHer()).append(".").toString());
                }
                w.append(t, (new StringBuilder("  (+")).append(included[i].staminaRegen() / 10).append(".").append(included[i].staminaRegen() % 10).append("% Stamina").toString());
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
                        w.redAppend(t, (new StringBuilder("-")).append(lost / 10).append(".").append(lost % 10).append("% Motivation").toString());
                    else
                        w.append(t, (new StringBuilder("-")).append(lost / 10).append(".").append(lost % 10).append("% Motivation").toString());
                }
                w.append(t, ")");
            }

        if(exhausted != null && (tantruming != null || included.length == 0))
        {
            for(int i = 0; i < exhausted.length; i++)
                if(tantruming != null)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(exhausted[i].mainName).append(" finds it difficult to rest due to ").append(tantruming.mainName).append("'s disturbance.  (").toString());
                    int lost = damages[1];
                    if(tantruming.opinion(exhausted[i]) > 100)
                        lost = damages[0];
                    else
                    if(tantruming.opinion(exhausted[i]) < -100)
                        lost = damages[2];
                    if(exhausted[i].motivation / 10 < exhausted[i].hostility)
                        w.redAppend(t, (new StringBuilder("-")).append(lost / 10).append(".").append(lost % 10).append("% Motivation").toString());
                    else
                        w.append(t, (new StringBuilder("-")).append(lost / 10).append(".").append(lost % 10).append("% Motivation").toString());
                    w.append(t, ")");
                } else
                {
                    if(i != 0)
                        w.append(t, "\n\n");
                    w.append(t, (new StringBuilder(String.valueOf(exhausted[i].mainName))).append(" is tired due to the day's activities.").toString());
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
        if(w.active.booleanValue())
        {
            p.removeAll();
            JButton Continue = new JButton("Continue");
            Continue.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.usedForsaken = null;
                    Project.Shop(t, p, f, w);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
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

    static final long million = 0xf4240L;
    static final long billion = 0x3b9aca00L;
    static final long trillion = 0xe8d4a51000L;
    static final long quadrillion = 0x38d7ea4c68000L;
    static final long quintillion = 0xde0b6b3a7640000L;
    static final int scenesThisVersion = 49;
    static final int vignettesThisVersion = 16;
    static final Color PURPLISH = new Color(225, 125, 255);
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
        Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false)
    };
    public static Boolean displayedFallen[] = {
        Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false)
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
    public static Activity LickCock;
    public static Activity CockLicked;
    public static Activity LickPussy;
    public static Activity PussyLicked;
    public static Activity Supine;
    public static Activity PullUp;
    public static Activity StepOnCock;
    public static Activity CockSteppedOn;
    public static Activity StepOnClit;
    public static Activity ClitSteppedOn;
    public static Activity DirtyTalk;
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
        LickCock = new Activity();
        CockLicked = new Activity();
        LickPussy = new Activity();
        PussyLicked = new Activity();
        Supine = new Activity();
        PullUp = new Activity();
        StepOnCock = new Activity();
        CockSteppedOn = new Activity();
        StepOnClit = new Activity();
        ClitSteppedOn = new Activity();
        DirtyTalk = new Activity();
        allActivities = (new Activity[] {
            Talk, TweakClit, ClitTweaked, SpreadLegs, Praise, Insult, PushDown, PullDown, Escape, StopActing, 
            TieUp, BeTied, StrokeCock, CockStroked, Lubricate, BeLubricated, VaginalPenetrate, PenetratedVaginally, AnalPenetrate, PenetratedAnally, 
            StripOther, Stripped, LickCock, CockLicked, LickPussy, PussyLicked, Supine, PullUp, StepOnCock, CockSteppedOn, 
            StepOnClit, ClitSteppedOn, DirtyTalk
        });
    }
}
