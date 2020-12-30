
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class Project extends JFrame
{

    public Project()
    {
        window = new JFrame("Project");
        Container cp = window.getContentPane();
        window.setLayout(new BoxLayout(cp, 3));
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(1300, 760));
        scrollPane.setVerticalScrollBarPolicy(22);
        cp.add(scrollPane);
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
        }
        IntroOne(textPane, controlPanel, window, ThisState);
    }

    public static void IntroOne(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        w.setGenders(w.getGenderBalance());
        p.getInputMap().clear();
        p.getActionMap().clear();
        if(!t.getBackground().equals(w.BACKGROUND))
            w.toggleColors(t);
        w.append(t, (new StringBuilder("Corrupted Saviors, Release 14c: \"Climax\"\n\nThis game contains content of an adult nature and should not be played by the underaged or by those unable to distinguish fantasy from reality.\n\n")).append(w.getSeparator()).append("\n\nJapan, mid-21st century.  The psychic energies of humanity have finally begun to coalesce into physical form.  The resulting beings are known as Demons.  Born from the base desires suppressed deep within the human mind, these creatures spread across the planet, leaving chaos and depravity in their wake.\n\nBut Demons do not represent the entirety of the human condition.  The hopes and determination of humanity have also risen up, gathering in the bodies of a few Chosen warriors in order to grant them the power to fight the Demons.  Although each of them was once an ordinary person, their new abilities place them at the center of the struggle for the soul of humanity.\n\nYou are a Demon Lord, the highest form of Demon, with your own mind and will, focused on the corruption of all that is good in the world.  The Chosen are the keystone of humanity's resistance to your goal, but to simply kill them would be meaningless.  Instead, shatter their notions of right and wrong, showing them the true darkness that hides within!").toString());
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
        JButton NewGame = new JButton("New Game");
        NewGame.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                if(w.getEarlyCheat().booleanValue())
                    Project.Shop(t, p, f, w);
                else
                    Project.IntroTwo(t, p, f, w);
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
        JButton About = new JButton("About");
        About.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nCopyright 2019-2021 by CSdev. Corrupted Saviors is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License. To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/4.0/.\n\nIf you like this game, please share it and discuss it so that it can be further enjoyed and improved!  There is a good chance that the developer reads whatever forum you found it on.  Direct feedback can also be sent to corruptedsaviors@gmail.com").toString());
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

    public static void OptionsDisplay(JTextPane t, JPanel p, JFrame f, WorldState w, Boolean earlyCheatVisible)
    {
        t.setText("");
        if(earlyCheatVisible.booleanValue())
        {
            w.append(t, "Early cheats: ");
            if(w.getEarlyCheat().booleanValue())
                w.append(t, "ON");
            else
                w.append(t, "OFF");
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
                if(saveFile.getSaves()[i].getDay() > 30)
                    earlyCheatVisible = Boolean.valueOf(true);

            if(w.getEarlyCheat().booleanValue())
                earlyCheatVisible = Boolean.valueOf(true);
            if(earlyCheatVisible == null)
                earlyCheatVisible = Boolean.valueOf(false);
        }
        final Boolean CheatVisibility = earlyCheatVisible;
        OptionsDisplay(t, p, f, w, earlyCheatVisible);
        JButton EarlyCheat = new JButton("Toggle Early Cheat");
        EarlyCheat.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.setEarlyCheat(Boolean.valueOf(!w.getEarlyCheat().booleanValue()));
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

    public static void IntroTwo(JTextPane t, JPanel p, JFrame f, WorldState w)
    {
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nThe peaceful everyday routine of the capital city is instantly shattered as a horde of Demons and their dominated human Thralls spills out onto the street!  Screams and alarms fill the air, chaos descending on the scene in an instant.  Already, innocents are being mobbed and dragged away towards a terrible fate.\n\nJust then, a sound like a thunderclap cuts through the panic, and a voice calls out a challenge to the Demons below!\n\n").toString());
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

        for(int i = 0; i < w.getCombatants().length; i++)
            if(w.getCombatants()[i] != null)
            {
                if(w.getCombatants()[i].isSurrounded().booleanValue())
                {
                    w.orangeAppend(t, (new StringBuilder("\n")).append(w.getCombatants()[i].getMainName()).append(": ").toString());
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
                        w.orangeAppend(t, "Surrounded");
                    if(w.getCombatants()[i].getSurroundDuration() > 1)
                        w.orangeAppend(t, (new StringBuilder(" for ")).append(w.getCombatants()[i].getSurroundDuration()).append(" more turns").toString());
                    else
                        w.orangeAppend(t, " until next turn");
                } else
                if(w.getCombatants()[i].isCaptured().booleanValue())
                {
                    w.orangeAppend(t, (new StringBuilder("\n")).append(w.getCombatants()[i].getMainName()).append(": ").toString());
                    if(w.getCombatants()[i].timesDetonated() > 0 && !w.adaptationsDisabled().booleanValue())
                    {
                        if(w.getCombatants()[i].getCaptureProgression() + w.getCombatants()[i].getINJULevel() + 1 >= w.getCaptureDuration())
                            w.orangeAppend(t, "Detonating next turn");
                        else
                        if(w.getCombatants()[i].getCaptureProgression() + w.getCombatants()[i].getINJULevel() + 2 == w.getCaptureDuration())
                            w.orangeAppend(t, "Detonating in 2 more turns");
                        else
                        if(w.getBodyStatus()[5].booleanValue() || w.getBodyStatus()[12].booleanValue() || w.getBodyStatus()[13].booleanValue())
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
                    w.redAppend(t, (new StringBuilder("\n")).append(w.getCombatants()[i].getMainName()).append(": Killed in Action").toString());
                else
                if(w.finalBattle.booleanValue() && w.getCombatants()[i].resolve <= 0)
                    w.greenAppend(t, (new StringBuilder("\n")).append(w.getCombatants()[i].getMainName()).append(": Resolve Broken!").toString());
                else
                if(w.getCombatants()[i].surroundPossible(w).booleanValue())
                    w.purpleAppend(t, (new StringBuilder("\n")).append(w.getCombatants()[i].getMainName()).append(": Opening Level ").append(w.getCombatants()[i].getFEAROpening(w) + w.getCombatants()[i].getPAINOpening() + w.getCombatants()[i].getDISGOpening() + w.getCombatants()[i].getSHAMOpening(w)).append(" vs. Defense Level ").append(w.getCombatants()[i].getDefenseLevel()).toString());
                else
                if(w.getCombatants()[i].getDefenseLevel() > 9000)
                    w.append(t, (new StringBuilder("\n")).append(w.getCombatants()[i].getMainName()).append(": Flying Above Battlefield").toString());
                else
                    w.append(t, (new StringBuilder("\n")).append(w.getCombatants()[i].getMainName()).append(": Opening Level ").append(w.getCombatants()[i].getFEAROpening(w) + w.getCombatants()[i].getPAINOpening() + w.getCombatants()[i].getDISGOpening() + w.getCombatants()[i].getSHAMOpening(w)).append(" vs. Defense Level ").append(w.getCombatants()[i].getDefenseLevel()).toString());
                if(w.finalBattle.booleanValue() && w.getCombatants()[i].resolve > 0 && w.getCombatants()[i].alive.booleanValue())
                    w.append(t, (new StringBuilder(" [Resolve at ")).append(w.getCombatants()[i].resolve).append("%]").toString());
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
        for(int i = 0; i < 3; i++)
            if(w.getCombatants()[i] != null)
                if(!w.finalBattle.booleanValue())
                {
                    targets++;
                    targetFound = i;
                } else
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
                if(w.finalBattle.booleanValue() && defeated == 2)
                    w.append(t, (new StringBuilder("\n\n")).append(w.getCombatants()[targetFound].getMainName()).append(" is fighting just to escape!").toString());
                else
                if(w.finalBattle.booleanValue() && defeated == 1)
                    w.append(t, (new StringBuilder("\n\n")).append(w.getCombatants()[targetFound].getMainName()).append(" is fighting just to stall until reinforcements arrive!").toString());
                else
                    w.append(t, (new StringBuilder("\n\n")).append(w.getCombatants()[targetFound].getMainName()).append(" is fighting alone!").toString());
            PickAction(t, p, f, w, w.getCombatants()[targetFound], initiative);
        } else
        if(targets == 0)
        {
            p.removeAll();
            if(defeated == 1)
                w.append(t, "\n\nThe Demons have already neutralized their first opponent, but more are on the way!");
            else
                w.append(t, "\n\nThe Demons have already neutralized two of the Chosen, but the third is on the way!");
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
            p.validate();
            p.repaint();
        } else
        {
            p.removeAll();
            w.chatter(t);
            w.append(t, "\n\nWho will you target?");
            for(int i = 0; i < 3; i++)
                if(w.getCombatants()[i] != null && (!w.finalBattle.booleanValue() || w.getCombatants()[i].resolve > 0 && w.getCombatants()[i].alive.booleanValue()))
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
            p.add(Pass);
            int occupied = 0;
            for(int i = 0; i < 3; i++)
                if(w.getCombatants()[i] != null)
                    if(w.getCombatants()[i].isSurrounded().booleanValue())
                        occupied += w.getCombatants()[i].getSurroundDuration();
                    else
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
                                                free[j] = w.getCombatants()[i].getMainName();

                                    }

                            w.append(t, "You order your Demons to flee back into the tunnels beneath the city along with their captive victims.  ");
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
                            Project.PostBattle(t, p, f, w);
                        }

                        final _cls1RetreatButton this$1;
                        private final WorldState val$w;
                        private final JTextPane val$t;
                        private final int val$occupiedBonus;
                        private final JPanel val$p;
                        private final JFrame val$f;

                    
                    {
                        this$1 = _cls1RetreatButton.this;
                        w = worldstate;
                        t = jtextpane;
                        occupiedBonus = i;
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
                {
                    generated = "Retreat from the battle.";
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
            private final Action val$ForceOrgasmAction;
            private final int val$finalOrgasming;
            private final Action val$SodomizeAction;
            private final int val$finalSodomized;
            private final Action val$BroadcastAction;
            private final int val$finalBroadcasted;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final Chosen val$initiative[];

            public _cls1SurroundButton(JFrame jframe, 
                    Chosen achosen[])
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
                ForceOrgasmAction = action5;
                finalOrgasming = j;
                SodomizeAction = action6;
                finalSodomized = k;
                BroadcastAction = action7;
                finalBroadcasted = l;
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
                if(w.upgradedCommander().booleanValue())
                {
                    w.setCaptureTarget(c);
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
            private final Action val$ForceOrgasmAction;
            private final int val$finalOrgasming;
            private final Action val$SodomizeAction;
            private final int val$finalSodomized;
            private final Action val$BroadcastAction;
            private final int val$finalBroadcasted;

            public _cls1CaptureButton(Action action7, 
                    int l)
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
                c.Examine(t, p, f, w);
            }

            private final Chosen val$c;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            public _cls1ExamineButton(JFrame jframe, WorldState worldstate)
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action ExamineAction = new _cls1ExamineButton(f, w);
        p.removeAll();
        w.append(t, "\n\n");
        c.printStatus(t, w);
        if(!c.isCaptured().booleanValue() && !c.isDefiled().booleanValue())
            w.append(t, "\n\nChoose your action.");
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
        class _cls3PassButton extends AbstractAction
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

                        final _cls3PassButton this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;
                        private final Chosen val$initiative[];

                    public _cls1ContinueButton(WorldState worldstate, 
                            Chosen achosen[])
                    {
                        this$1 = _cls3PassButton.this;
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

                        final _cls3PassButton this$1;

                    
                    {
                        this$1 = _cls3PassButton.this;
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

            public _cls3PassButton(JFrame jframe, Chosen achosen[])
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

        Action PassAction = new _cls3PassButton(f, initiative);
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
            w.append(t, (new StringBuilder("\n\n")).append(c.getMainName()).append(" is captured by your Commander.  Any attempts by other Demons to attack would simply get in the way.").toString());
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
            if((!c.surroundPossible(w).booleanValue() || w.upgradedCommander().booleanValue()) && w.getCapturesPossible() > 0 && (c.getDefenseLevel() < 9000 || w.getBodyStatus()[24].booleanValue()) && w.commanderFree().booleanValue())
            {
                JButton Capture = new JButton(CaptureAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -90);
                    }

                };
                Capture.setBackground(PURPLISH);
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
                description = (new StringBuilder(String.valueOf(description))).append("</center></html>").toString();
                Capture.setToolTipText(description);
                Capture.getInputMap(2).put(KeyStroke.getKeyStroke(67, 0), "pressed");
                if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 2)
                    Capture.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Capture.getActionMap().put("pressed", CaptureAction);
                p.add(Capture);
            }
        }
        int defeated = 0;
        for(int i = 0; i < 3 && w.finalBattle.booleanValue(); i++)
            if(!w.getCast()[i].alive.booleanValue() || w.getCast()[i].resolve <= 0)
                defeated++;

        if(w.getCombatants()[1] != null && defeated < 2)
        {
            class _cls1BackButton extends AbstractAction
            {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n").toString());
                    Project.PickTarget(t, p, f, w);
                }

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;

            public _cls1BackButton(JPanel jpanel, JFrame jframe)
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super(text);
                putValue("ShortDescription", desc);
            }
            }

            Action BackAction = new _cls1BackButton(p, f);
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
                    if(w.getCombatants()[i].isSurrounded().booleanValue())
                        occupied += w.getCombatants()[i].getSurroundDuration();
                    else
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
                                                free[j] = w.getCombatants()[i].getMainName();

                                    }

                            w.append(t, "You order your Demons to flee back into the tunnels beneath the city along with their captive victims.  ");
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
                            Project.PostBattle(t, p, f, w);
                        }

                        final _cls2RetreatButton this$1;
                        private final WorldState val$w;
                        private final JTextPane val$t;
                        private final int val$occupiedBonus;
                        private final JPanel val$p;
                        private final JFrame val$f;

                    
                    {
                        this$1 = _cls2RetreatButton.this;
                        w = worldstate;
                        t = jtextpane;
                        occupiedBonus = i;
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
            if(w.getCaptureTarget() == initiative[progress] || initiative[progress].isCaptured().booleanValue())
                initiative[progress].BeCaptured(t, p, f, w);
            else
            if(w.getSurroundTarget() == initiative[progress] || initiative[progress].isSurrounded().booleanValue())
                initiative[progress].BeSurrounded(t, p, f, w);
            else
                initiative[progress].TakeTurn(t, p, f, w);
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
                        if(w.getTotalRounds() >= 18)
                            newChosen = Boolean.valueOf(true);
                    } else
                    if(w.getCast()[2] == null && w.getTotalRounds() >= 60)
                        newChosen = Boolean.valueOf(true);
                    if((w.evacComplete() || w.getBattleRound() < 4) && (w.getCast()[1] != null && w.getTotalRounds() < 80 || w.getCast()[1] == null && w.getTotalRounds() < 28))
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
                                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                                w.endTurn(t);
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
                                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                                if(w.endTurn(t))
                                {
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
                                    Project.PickTarget(t, p, f, w);
                                    if(w.tutorialResponse().booleanValue())
                                        if(w.getBattleRound() == 6)
                                        {
                                            if(w.getCast()[0].isCaptured().booleanValue())
                                                w.grayAppend(t, "\n\n(The factors that determine when reinforcements show up are the personalities and relationships of the initially-targeted Chosen and the arriving Chosen.  This means that as long as their relationship doesn't change, Calamity will always show up on Round 6 when we go after Miracle.\n\nLet's target Calamity and then use Examine to see what she's like.)");
                                            else
                                                w.endTutorial();
                                        } else
                                        if(w.getBattleRound() == 7)
                                            if(w.getCast()[1].getCurrentDISG() == 189L)
                                                w.grayAppend(t, "\n\n(The EXPO damage makes setting up openings in one turn very reliable, and it just climbed another level.  But now the third Chosen has arrived.  Target Mirage and use Examine to see what we can expect from her.)");
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
                                        w.grayAppend(t, "\n\n(Tickle deals ANTIcipation damage, while Humiliate deals EXPO damage.  ANTI's main effect is to multiply other circumstance damage, so it's generally better to start there.  Try Tickling her.)");
                                    else
                                        w.grayAppend(t, "\n\n(Pummel deals INJU damage, while Humiliate deals EXPO damage.  INJU's main effect is to multiply other circumstance damage, so it's generally better to start there.  Try Pummeling her.)");
                                } else
                                {
                                    w.endTutorial();
                                }
                            } else
                            if(w.getBattleRound() == 5)
                            {
                                if(w.getCast()[0].getCurrentINJU() == 148L)
                                {
                                    if(w.tickle().booleanValue())
                                        w.grayAppend(t, "\n\n(She escaped quickly, but not before getting level 1 ANTI.  Beating her defense level of 3 for another Surround might be difficult, but we have a Commander, and Commanders ignore defense level.  The one prepared for this battle cost 2 Evil Energy: 1 base, plus 1 to increase its duration to 3 rounds.  It was given the Mania upgrade, which allows it to inflict EXPO.  EXPO's effect is to multiply damage dealt to other Chosen.  Use Capture to summon your Commander!)");
                                    else
                                        w.grayAppend(t, "\n\n(She escaped quickly, but not before getting level 1 INJU.  Beating her defense level of 3 for another Surround might be difficult, but we have a Commander, and Commanders ignore defense level.  The one prepared for this battle cost 2 Evil Energy: 1 base, plus 1 to increase its duration to 3 rounds.  It was given the Mania upgrade, which allows it to inflict EXPO.  EXPO's effect is to multiply damage dealt to other Chosen.  Use Capture to summon your Commander!)");
                                } else
                                {
                                    w.endTutorial();
                                }
                            } else
                            if(w.getBattleRound() == 8)
                            {
                                if(w.getCast()[1].getCurrentPAIN() == 387L)
                                    w.grayAppend(t, "\n\n(And finally, let's increase the opening by one more level with Taunt on Calamity.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 9)
                            {
                                if(w.getCast()[1].getCurrentSHAM() == 304L)
                                    w.grayAppend(t, "\n\n(Judging by the Extermination Progress and the Extermination Per Chosen, the battle will end this round if we don't do something.  Fortunately, we've built a nice opening.  Let's Surround Calamity.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 10)
                            {
                                if(w.getCast()[1].isSurrounded().booleanValue())
                                    w.grayAppend(t, "\n\n(We don't have to worry about the battle ending as long as at least one of the Chosen are surrounded.  But any surroundings after this point will cause the target to flee to the sky afterward, so we need to deal lots of trauma to Calamity in order to make our last surrounding count.  HATE and PLEA both multiply incoming trauma.  PLEA increases it by more, but HATE increases circumstance damage too.  Calamity is weak to HATE, so let's have the Thralls Grind against her to inflict it.");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 11)
                            {
                                if(w.getCast()[1].getCurrentHATE() == 432L)
                                    w.grayAppend(t, "\n\n(We need to make sure that the battle doesn't end before we're done with Calamity.  While she stews in her HATE, we have two rounds to set up another opening and then take it.  Miracle's defense level is too high to grab her again in that time, but we can use Mirage.  Let's Threaten Mirage.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 12)
                            {
                                if(w.getCast()[2].getCurrentFEAR() == 355L)
                                    w.grayAppend(t, "\n\n(And now, Surround Mirage.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 13)
                            {
                                if(w.getCast()[2].isSurrounded().booleanValue())
                                    w.grayAppend(t, "\n\n(We don't have time to do anything with Mirage, but look at the opening level on Calamity!  Surround her again.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 14)
                            {
                                if(w.getCast()[1].isSurrounded().booleanValue())
                                {
                                    if(w.tickle().booleanValue())
                                        w.grayAppend(t, "\n\n(The first level of vulnerability is broken when Chosen use desperate methods to stop themselves from going over 10,000 circumstance damage.  They look at their susceptibility to those circumstances and the number of rounds they'll be surrounded when deciding whether they're at risk.  Calamity's multipliers are already high, so use Tickle to try making them go even higher!)");
                                    else
                                        w.grayAppend(t, "\n\n(The first level of vulnerability is broken when Chosen use desperate methods to stop themselves from going over 10,000 circumstance damage.  They look at their susceptibility to those circumstances and the number of rounds they'll be surrounded when deciding whether they're at risk.  Calamity's multipliers are already high, so use Pummel to try making them go even higher!)");
                                } else
                                {
                                    w.endTutorial();
                                }
                            } else
                            if(w.getBattleRound() == 15)
                            {
                                if(w.getCast()[1].getCurrentINJU() == 144L)
                                    w.grayAppend(t, "\n\n(Calamity was taking over 400 HATE damage from Grind with a x4 multiplier, and now she has a x16 multiplier.  Considering that she doesn't know whether you have another Commander in reserve to stop Miracle and Mirage from distracting the Thralls, she has good reason to worry about being brought to over 10k HATE.  Use Grind on her to force her use desperate measures to defend herself!)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 16)
                            {
                                if(w.getCast()[1].getCurrentHATE() == 3197L)
                                    w.grayAppend(t, "\n\n(The bonus Evil Energy you get from breaking the vulnerability is enough to pay for the Commander you used, and now that Calamity has a broken vulnerability, she can be induced to commit greater sins during downtime and increase your Evil Energy income even more, allowing you to buy more upgrades and Commanders to crack the harder vulnerabilities!\n\nThis concludes the tutorial for Corrupted Saviors.  You can finish the battle however you like, or even go back to the start of the tutorial to try a completely different strategy.  Good luck!)");
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
                    else
                        minors++;
                if(!w.getCast()[i].isVVirg())
                    if(w.getCast()[i].getMorality() > 66)
                        cores++;
                    else
                    if(w.getCast()[i].getMorality() > 33)
                        sigs++;
                    else
                        minors++;
                if(w.getCast()[i].isLustful())
                    if(w.getCast()[i].getInnocence() > 66)
                        cores++;
                    else
                    if(w.getCast()[i].getInnocence() > 33)
                        sigs++;
                if(!w.getCast()[i].isCVirg())
                    if(w.getCast()[i].getInnocence() > 66)
                        cores++;
                    else
                    if(w.getCast()[i].getInnocence() > 33)
                        sigs++;
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
                    else
                        minors++;
                if(!w.getCast()[i].isModest())
                    if(w.getCast()[i].getDignity() > 66)
                        cores++;
                    else
                    if(w.getCast()[i].getDignity() > 33)
                        sigs++;
                    else
                        minors++;
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

                            final _cls54 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls54.this;
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

                                final _cls55 this$1;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final WorldState val$w;

                    
                    {
                        this$1 = _cls55.this;
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
                    if(w.getDay() == 15)
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
                    if(w.getDay() == 30)
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
                    if(w.getDay() == 45)
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

                    final _cls62 this$1;
                    private final JPanel val$p;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final Chosen val$order[];
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls62.this;
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

                    final _cls63 this$1;
                    private final JPanel val$p;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final Chosen val$order[];
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls63.this;
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

                    final _cls64 this$1;
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
                        this$1 = _cls64.this;
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
        broken.breakScene(t, w, c, sceneType);
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
        t.setText("");
        w.incrementDay();
        w.clearCommander();
        int lastChosen = 0;
        int totalActions = 21;
        long actionWeights[][] = new long[3][totalActions];
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
            actionWeights[i][0] = 150L;
            actionWeights[i][1] = 50L + (fear * 100L) / (long)(100 + w.getCast()[i].getMorality());
            actionWeights[i][2] = 50L + (disg * 100L) / (long)(100 + w.getCast()[i].getMorality());
            actionWeights[i][3] = 50L + (pain * 100L) / (long)(100 + w.getCast()[i].getMorality());
            actionWeights[i][4] = 50L + (sham * 100L) / (long)(100 + w.getCast()[i].getMorality());
            long inhibition = 20000L;
            if(divided.booleanValue())
                inhibition /= divisor;
            if(w.getCast()[i].isRuthless())
                actionWeights[i][5] = (fear * 200L + pain * 100L + angst * 20L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition;
            else
                actionWeights[i][5] = 0L;
            if(w.getCast()[i].isLustful())
                actionWeights[i][6] = (disg * 200L + fear * 100L + angst * 20L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition;
            else
                actionWeights[i][6] = 0L;
            if(w.getCast()[i].isMeek())
                actionWeights[i][7] = (pain * 200L + sham * 100L + angst * 20L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition;
            else
                actionWeights[i][7] = 0L;
            if(w.getCast()[i].isDebased())
                actionWeights[i][8] = (sham * 200L + disg * 100L + angst * 20L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition;
            else
                actionWeights[i][8] = 0L;
            inhibition = 0x3d0900L;
            if(divided.booleanValue())
                inhibition /= divisor;
            if(!w.getCast()[i].isVVirg())
                actionWeights[i][9] = (fear * 400L + disg * 200L + angst * 40L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition;
            else
                actionWeights[i][9] = 0L;
            if(!w.getCast()[i].isCVirg())
                actionWeights[i][10] = (disg * 400L + pain * 200L + angst * 40L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition;
            else
                actionWeights[i][10] = 0L;
            if(!w.getCast()[i].isAVirg())
                actionWeights[i][11] = (pain * 400L + sham * 200L + angst * 40L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition;
            else
                actionWeights[i][11] = 0L;
            if(!w.getCast()[i].isModest())
                actionWeights[i][12] = (sham * 400L + fear * 200L + angst * 40L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition;
            else
                actionWeights[i][12] = 0L;
            inhibition = 0x2540be400L;
            if(divided.booleanValue())
                inhibition /= divisor;
            if(w.getCast()[i].timesSlaughtered() > 0)
                actionWeights[i][13] = (fear * 1000L + pain * 500L + disg * 250L + angst * 100L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition;
            else
                actionWeights[i][13] = 0L;
            if(w.getCast()[i].timesFantasized() > 0)
                actionWeights[i][14] = (disg * 1000L + sham * 500L + fear * 250L + angst * 100L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition;
            else
                actionWeights[i][14] = 0L;
            if(w.getCast()[i].timesDetonated() > 0)
                actionWeights[i][15] = (pain * 1000L + disg * 500L + sham * 250L + angst * 100L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition;
            else
                actionWeights[i][15] = 0L;
            if(w.getCast()[i].timesStripped() > 0)
                actionWeights[i][16] = (sham * 1000L + fear * 500L + pain * 250L + angst * 100L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition;
            else
                actionWeights[i][16] = 0L;
            inhibition = 0xb5e620f48000L;
            if(divided.booleanValue())
                inhibition /= divisor;
            if(w.getCast()[i].isImpregnated().booleanValue())
                actionWeights[i][17] = (fear * 2000L + pain * 1000L + sham * 500L + angst * 250L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition;
            else
                actionWeights[i][17] = 0L;
            if(w.getCast()[i].isHypnotized().booleanValue())
                actionWeights[i][18] = (disg * 2000L + fear * 1000L + pain * 500L + angst * 250L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition;
            else
                actionWeights[i][18] = 0L;
            if(w.getCast()[i].isDrained().booleanValue())
                actionWeights[i][19] = (pain * 2000L + sham * 1000L + disg * 500L + angst * 250L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition;
            else
                actionWeights[i][19] = 0L;
            if(w.getCast()[i].isParasitized().booleanValue())
                actionWeights[i][20] = (sham * 2000L + disg * 1000L + fear * 500L + angst * 250L) / (long)(100 + w.getCast()[i].getMorality()) - inhibition;
            else
                actionWeights[i][20] = 0L;
            long highestWeight = 0L;
            for(int j = 0; j < actionWeights[i].length; j++)
                if(actionWeights[i][j] > highestWeight)
                {
                    highestWeight = actionWeights[i][j];
                    chosenAction[i] = j;
                }

        }

        long combinedWeights[][] = new long[3][totalActions];
        for(int i = 0; i <= lastChosen; i++)
        {
            for(int j = 0; j < totalActions; j++)
            {
                combinedWeights[i][j] = actionWeights[i][j];
                for(int k = 0; k <= lastChosen; k++)
                    if(i != k)
                    {
                        combinedWeights[i][j] = (combinedWeights[i][j] * (long)(200 + w.getCast()[i].getInnocence())) / 200L;
                        combinedWeights[i][j] = ((((actionWeights[k][chosenAction[k]] + actionWeights[k][j]) * 100L) / actionWeights[k][chosenAction[k]]) * combinedWeights[i][j]) / 100L;
                        combinedWeights[i][j] = (combinedWeights[i][j] * (long)(8 + w.getRelationship(i, k))) / 8L;
                        long addedWeight = combinedWeights[i][j] - actionWeights[i][j];
                        if(addedWeight > 0L && w.getCast()[i].getANGST() > w.getCast()[k].getANGST())
                        {
                            addedWeight = (((w.getCast()[k].getANGST() * 100L) / w.getCast()[i].getANGST()) * addedWeight) / 100L;
                            combinedWeights[i][j] = actionWeights[i][j] + addedWeight;
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
                if(combinedWeights[j][i] >= actionWeights[j][chosenAction[j]])
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
            if(w.getCast()[0] != null && combinedWeights[0][testOrder[i]] >= actionWeights[0][chosenAction[0]])
            {
                matches++;
                matching[0] = Boolean.valueOf(true);
            }
            if(w.getCast()[1] != null && combinedWeights[1][testOrder[i]] >= actionWeights[1][chosenAction[1]])
            {
                matches++;
                matching[1] = Boolean.valueOf(true);
            }
            if(w.getCast()[2] != null && combinedWeights[2][testOrder[i]] >= actionWeights[2][chosenAction[2]])
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
                                Project.Shop(t, p, f, w);
                            }

                            final _cls66 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls66.this;
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

                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final int val$chosenAction[];

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                chosenAction = ai;
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
                            Project.Shop(t, p, f, w);
                        }

                        final _cls67 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;

                    
                    {
                        this$1 = _cls67.this;
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

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final int val$chosenAction[];

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                chosenAction = ai;
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
                            Project.Shop(t, p, f, w);
                        }

                        final _cls68 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;

                    
                    {
                        this$1 = _cls68.this;
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

                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final int val$chosenAction[];

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                chosenAction = ai;
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
                                            Project.Shop(t, p, f, w);
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
                                    p.add(Continue3);
                                    p.validate();
                                    p.repaint();
                                }

                                final _cls69 this$1;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final int val$chosenAction[];

                    
                    {
                        this$1 = _cls69.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        chosenAction = ai;
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
                                    Project.Shop(t, p, f, w);
                                }

                                final _cls69 this$1;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final WorldState val$w;

                    
                    {
                        this$1 = _cls69.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
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

            
            {
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                chosenAction = ai;
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
            p.add(Continue);
            p.validate();
            p.repaint();
        }
    }

    public static void Shop(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        p.removeAll();
        if(t.getText().length() > 0)
            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
        for(int i = 0; i < 3; i++)
            if(w.getCast()[i] != null)
                w.getCast()[i].setTextSize(w.getTextSize());

        if(w.getTextSize() == 0)
            w.switchTextSize();
        w.append(t, (new StringBuilder("Day ")).append(w.getDay()).toString());
        w.printShopTutorial(t);
        if(w.getCast()[1] != null)
            w.printGroupTutorial(t);
        if(w.getDay() > 50 || w.getEarlyCheat().booleanValue())
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
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nActivating Cheat Mode will give you unlimited Evil Energy to spend").toString());
                        if(w.getDay() < 51)
                            w.append(t, ", but you will not receive a score for the playthrough");
                        w.append(t, ".  Activate Cheat Mode?");
                        JButton Activate = new JButton("Activate Cheat Mode");
                        Activate.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                w.setCheater();
                                Project.Cheat(t, p, f, w);
                            }

                            final _cls71 this$1;
                            private final WorldState val$w;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;

                    
                    {
                        this$1 = _cls71.this;
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

                            final _cls71 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls71.this;
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
        for(int i = 0; i < w.getTechs().length; i++)
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

                                    final _cls73 this$1;
                                    private final WorldState val$w;
                                    private final int val$thisTech;
                                    private final JPanel val$p;
                                    private final JTextPane val$t;
                                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls73.this;
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

                                    final _cls73 this$1;
                                    private final JTextPane val$t;
                                    private final JPanel val$p;
                                    private final JFrame val$f;
                                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls73.this;
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

        w.append(t, (new StringBuilder("\n\nYou have ")).append(w.getEvilEnergy()).append(" Evil Energy.").toString());
        w.printTip(t);
        if(w.getTechs()[0].isOwned().booleanValue())
        {
            JButton Profiles = new JButton("Profiles");
            Profiles.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    p.removeAll();
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nOverall corruption progress:").toString());
                    for(int i = 0; i < 3; i++)
                        if(w.getCast()[i] != null)
                        {
                            int spaces = w.getCast()[i].getMainName().length() - 3;
                            w.append(t, (new StringBuilder("\n\n")).append(w.getCast()[i].getMainName()).toString());
                            for(int j = spaces; j < 0; j++)
                                w.append(t, " ");

                            String gap = "";
                            for(int j = 0; j < spaces; j++)
                                gap = (new StringBuilder(String.valueOf(gap))).append(" ").toString();

                            w.append(t, (new StringBuilder("  T1 T2 T3 T4\nMOR")).append(gap).append(" [").toString());
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
                                w.append(t, " ");
                            w.append(t, "][");
                            if(w.getCast()[i].isImpregnated().booleanValue())
                                w.append(t, "X");
                            else
                                w.append(t, " ");
                            w.append(t, (new StringBuilder("]\nINN")).append(gap).append(" [").toString());
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
                                w.append(t, " ");
                            w.append(t, "][");
                            if(w.getCast()[i].isHypnotized().booleanValue())
                                w.append(t, "X");
                            else
                                w.append(t, " ");
                            w.append(t, (new StringBuilder("]\nCON")).append(gap).append(" [").toString());
                            if(w.getCast()[i].isMeek())
                                w.append(t, "X");
                            else
                                w.append(t, " ");
                            w.append(t, "][");
                            if(!w.getCast()[i].isAVirg())
                                w.append(t, "X");
                            else
                                w.append(t, " ");
                            w.append(t, "][");
                            if(w.getCast()[i].timesDetonated() > 0)
                                w.append(t, "X");
                            else
                                w.append(t, " ");
                            w.append(t, "][");
                            if(w.getCast()[i].isDrained().booleanValue())
                                w.append(t, "X");
                            else
                                w.append(t, " ");
                            w.append(t, (new StringBuilder("]\nDIG")).append(gap).append(" [").toString());
                            if(w.getCast()[i].isDebased())
                                w.append(t, "X");
                            else
                                w.append(t, " ");
                            w.append(t, "][");
                            if(!w.getCast()[i].isModest())
                                w.append(t, "X");
                            else
                                w.append(t, " ");
                            w.append(t, "][");
                            if(w.getCast()[i].timesStripped() > 0)
                                w.append(t, "X");
                            else
                                w.append(t, " ");
                            w.append(t, "][");
                            if(w.getCast()[i].isParasitized().booleanValue())
                                w.append(t, "X");
                            else
                                w.append(t, " ");
                            w.append(t, "]");
                        }

                    w.append(t, "\n\nWhose profile will you review?");
                    for(int i = 0; i < 3; i++)
                        if(w.getCast()[i] != null)
                        {
                            final int thisChosen = i;
                            JButton openProfile = new JButton(w.getCast()[i].getMainName());
                            openProfile.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    p.removeAll();
                                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                                    w.getCast()[thisChosen].printIntro(t, w);
                                    w.getCast()[thisChosen].printProfile(t, p, f, w);
                                    JButton Continue = new JButton("Continue");
                                    Continue.addActionListener(new ActionListener() {

                                        public void actionPerformed(ActionEvent e)
                                        {
                                            Project.Shop(t, p, f, w);
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

                                final _cls74 this$1;
                                private final JPanel val$p;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final int val$thisChosen;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls74.this;
                        p = jpanel;
                        w = worldstate;
                        t = jtextpane;
                        thisChosen = i;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(openProfile);
                        }

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

                        final _cls74 this$1;
                        private final WorldState val$w;
                        private final JTextPane val$t;

                    
                    {
                        this$1 = _cls74.this;
                        w = worldstate;
                        t = jtextpane;
                        super();
                    }
                    });
                    p.add(Statistics);
                    JButton Back = new JButton("Back");
                    Back.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            Project.Shop(t, p, f, w);
                        }

                        final _cls74 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;

                    
                    {
                        this$1 = _cls74.this;
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
            p.add(Profiles);
        }
        if(w.getTechs()[3].isOwned().booleanValue())
        {
            JButton CustomBody = new JButton("Commander");
            CustomBody.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Project.Customize(t, p, f, w);
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
            p.add(CustomBody);
            if(!w.getBodyStatus()[0].booleanValue() && w.getEvilEnergy() > 0)
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

                    final _cls76 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls76.this;
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

                    final _cls76 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls76.this;
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

                    final _cls76 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls76.this;
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

                    final _cls76 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls76.this;
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

                    final _cls76 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls76.this;
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

                    final _cls76 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls76.this;
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

                    final _cls76 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls76.this;
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
                        Project.IntroOne(t, p, f, x);
                    }

                    final _cls77 this$1;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls77.this;
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

                    final _cls77 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls77.this;
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
                Project.pickStartingTarget(t, p, f, w);
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
        p.add(NextBattle);
        if(w.writePossible().booleanValue())
            addWriteButton(p, w);
        p.validate();
        p.repaint();
        w.readCommentary(t);
    }

    public static void Cheat(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        p.removeAll();
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhich cheat would you like to use?\n\n+100 Evil Energy: Increases your Evil Energy by 100.\n\nChange Day: Allows you to skip closer to future events or revisit past ones with the team in its current state.  Range is limited to 1-50, and because events require all three Chosen to be present, this cheat cannot be activated until the full team has been encountered.\n\nDisable/Enable Adaptations: Prevents/Allows Chosen use of Slaughter, Fantasize, Detonate, and Striptease.  Note that use of these actions is required to reach later corruption stages.").toString());
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
                    if(newDay < 1)
                        newDay = 1;
                    else
                    if(newDay > 50)
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
                w.append(t, (new StringBuilder("\n\nSlot ")).append(i + 1).toString());
                if(i == 0)
                    w.append(t, " (most recent)");
                else
                if(i == saves.getSaves().length - 1)
                    w.append(t, " (oldest)");
                w.append(t, (new StringBuilder(", ")).append(fullSaveName).toString());
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
                                        if(savedWorld.getDay() == 1 && w.getEarlyCheat().equals(Boolean.valueOf(false)))
                                            Project.IntroTwo(t, p, f, saveFile.getSaves()[0]);
                                        else
                                            Project.Shop(t, p, f, saveFile.getSaves()[0]);
                                    }

                                    final _cls90 this$1;
                                    private final SaveData val$saveFile;
                                    private final int val$fileSelected;
                                    private final WriteObject val$wobj;
                                    private final JTextPane val$t;
                                    private final WorldState val$w;
                                    private final JPanel val$p;
                                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls90.this;
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

                                    final _cls90 this$1;
                                    private final JTextPane val$t;
                                    private final JPanel val$p;
                                    private final JFrame val$f;
                                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls90.this;
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
                                if(savedWorld.getDay() == 1 && w.getEarlyCheat().equals(Boolean.valueOf(false)))
                                    Project.IntroTwo(t, p, f, savedWorld);
                                else
                                    Project.Shop(t, p, f, savedWorld);
                            }
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
                                    Project.Shop(t, p, f, w);
                                }

                                final _cls90 this$1;
                                private final SaveData val$saveFile;
                                private final int val$fileSelected;
                                private final WriteObject val$wobj;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls90.this;
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

                                final _cls90 this$1;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls90.this;
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
            Cancel.addActionListener(new ActionListener() {

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

                                final _cls95 this$1;
                                private final Boolean val$punisherUsed;
                                private final WorldState val$w;
                                private final Boolean val$defilerUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls95.this;
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

                                final _cls95 this$1;
                                private final Boolean val$punisherUsed;
                                private final WorldState val$w;
                                private final Boolean val$defilerUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls95.this;
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

                                final _cls95 this$1;
                                private final Boolean val$punisherUsed;
                                private final WorldState val$w;
                                private final Boolean val$defilerUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls95.this;
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

                                final _cls95 this$1;
                                private final Boolean val$punisherUsed;
                                private final WorldState val$w;
                                private final Boolean val$defilerUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls95.this;
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

                            final _cls95 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls95.this;
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

                                final _cls96 this$1;
                                private final WorldState val$w;
                                private final Boolean val$punisherUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls96.this;
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

                                final _cls96 this$1;
                                private final WorldState val$w;
                                private final Boolean val$punisherUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls96.this;
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

                                final _cls96 this$1;
                                private final WorldState val$w;
                                private final Boolean val$punisherUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls96.this;
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

                                final _cls96 this$1;
                                private final WorldState val$w;
                                private final Boolean val$punisherUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls96.this;
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

                            final _cls96 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls96.this;
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
            if(!punisherUsed.booleanValue() && punisherKnown.booleanValue() && (!defilerUsed.booleanValue() && suppressorsUsed == 0 || w.getTechs()[47].isOwned().booleanValue() && (w.getEvilEnergy() >= 66 || defilerUsed.booleanValue() && w.getEvilEnergy() >= 60 || defilerUsed.booleanValue() && suppressorsUsed == 1 && w.getEvilEnergy() >= 50)))
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

                                final _cls97 this$1;
                                private final WorldState val$w;
                                private final Boolean val$defilerUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls97.this;
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

                                final _cls97 this$1;
                                private final WorldState val$w;
                                private final Boolean val$defilerUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls97.this;
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

                                final _cls97 this$1;
                                private final WorldState val$w;
                                private final Boolean val$defilerUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls97.this;
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

                                final _cls97 this$1;
                                private final WorldState val$w;
                                private final Boolean val$defilerUsed;
                                private final int val$suppressorsUsedFinal;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls97.this;
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

                            final _cls97 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls97.this;
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
                {
                    generated = (new StringBuilder("Buy ")).append(w.getTechs()[action].getName()).append(".").toString();
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
        if(w.getDay() == 50 || w.getTechs()[48].isOwned().booleanValue())
            w.append(t, "This will be the final battle.  When extermination is completed, instead of waiting for surrounded and captured allies to escape, the Chosen may sacrifice each other's lives in order to defeat you.  Victory requires neutralizing at least two of the three Chosen.\n\n");
        if(w.getBodyStatus()[0].booleanValue())
        {
            if(w.upgradedCommander().booleanValue() || !w.getTechs()[31].isOwned().booleanValue() || w.getBodyStatus()[2].booleanValue())
                w.printCommanderSummary(t, c);
            else
                immediateAction = Boolean.valueOf(true);
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
                                if(w.getDay() > 1 && !w.isCheater().booleanValue())
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
                                if(w.getDay() == 50 || w.getTechs()[48].isOwned().booleanValue())
                                    Project.BeginFinalBattle(t, p, f, w, c);
                                else
                                    Project.BeginBattle(t, p, f, w, c);
                            }

                            final _cls113 this$1;
                            private final WorldState val$w;
                            private final JPanel val$p;
                            private final Chosen val$c;
                            private final int val$type;
                            private final JTextPane val$t;
                            private final JFrame val$f;

                    
                    {
                        this$1 = _cls113.this;
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

                            final _cls113 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls113.this;
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
                    if(w.getDay() > 1 && !w.isCheater().booleanValue())
                        w.archiveCommander(w.getDay());
                    Project.advanceDowntimeAction(p, w, w.getTechs().length + c.getNumber());
                    if(w.getDay() == 50 || w.getTechs()[48].isOwned().booleanValue())
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
        PickTarget(t, p, f, w);
    }

    public static void BeginFinalBattle(JTextPane t, JPanel p, JFrame f, WorldState w, Chosen c)
    {
        w.incrementTotalRounds();
        Chosen newCombatants[] = new Chosen[3];
        newCombatants[0] = c;
        w.newCombat(w, newCombatants);
        if(w.getDay() == 50)
            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nThe city's streets are devoid of life.  In preparation for the coming battle, the residents have been evacuated to temporary housing in the surrounding countryside.  The only ones who remain are the stubborn, the thrillseekers, some entrepreneurial journalists, and of course your minions.  They all know what's coming, and they're waiting for you to make your move.\n\nFinally, the silence is broken by the sound of shattering pavement.  An enormous, dark shape rises out of the ground, toppling buildings and sending tons of rubble spilling in all directions as it grows.  It's an enormous pillar whose surface shimmers like an oil slick, and it continues upward until it dwarfs the skyscrapers below, penetrating the heavens themselves.  All throughout the city, space begins to warp and shift as you corrupt the fabric of reality and bend it to your will.\n\n").append(c.getMainName()).append(" is the closest of the Chosen to the epicenter.  Although ").append(c.hisHer()).append(" instincts are telling ").append(c.himHer()).append(" to immediately begin drawing on as much energy as ").append(c.heShe()).append(" can, ").append(c.heShe()).append(" recalls from the strategy briefing that it will still take some time to evacuate the last few VIPs who had to stay until the last moment.  The neighboring cities will also need a chance to prepare for the destructive electromagnetic pulses that are likely to be released as the Chosen fight at full power.\n\n").toString());
        else
            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nThe city's streets are bustling as if this were a day like any other.  Its citizens have no idea how close your plans are to completion.\n\nWithout warning, the pavement of one of the main streets shatters and opens up.  An enormous, dark shape rises out of the ground, toppling buildings and sending tons of rubble spilling in all directions as it grows.  It's an enormous pillar whose surface shimmers like an oil slick, and it continues upward until it dwarfs the skyscrapers below, penetrating the heavens themselves.  All throughout the city, space begins to warp and shift as you corrupt the fabric of reality and bend it to your will.\n\n").append(c.getMainName()).append(" is the closest of the Chosen to the epicenter.  Although ").append(c.hisHer()).append(" instincts are telling ").append(c.himHer()).append(" to immediately begin drawing on as much energy as ").append(c.heShe()).append(" can, ").append(c.heShe()).append(" has orders to restrain ").append(c.himHer()).append("self until ").append(c.heShe()).append("'s given clearance to go all-out.  Loudspeakers across the city broadcast instructions to the Chosen as they all hurry toward the tower, warning them that this will be the final battle and that they may not survive.  They're told to hold back at least until the most important VIPs can get a safe distance from the city.  It goes unsaid that the rest of the populace is considered an acceptable sacrifice.\n\n").toString());
        w.finalBattleIntro(t, c);
        if(w.getBodyStatus()[0].booleanValue() && !w.getBodyStatus()[2].booleanValue())
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
        w.append(t, "\n");
        PickTarget(t, p, f, w);
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
                        mid.say(t, (new StringBuilder("I'm ready!  You'd better not screw it up for us, ")).append(low.getMainName()).append("!\"\n\n").toString());
                        low.say(t, (new StringBuilder("\"I-I won't let you down, ")).append(high.getMainName()).append("!").toString());
                    }
                } else
                {
                    high.say(t, (new StringBuilder("You'd better not screw this up, ")).append(low.getMainName()).append("!\"\n\n").toString());
                    mid.say(t, "\"");
                    if(w.getRelationship(mid.getNumber(), low.getNumber()) >= 0)
                    {
                        mid.say(t, (new StringBuilder("Don't worry, ")).append(low.getMainName()).append("!  I believe in you!\"\n\n").toString());
                        low.say(t, "\"R-Right!");
                    } else
                    {
                        mid.say(t, (new StringBuilder("Don't worry, we can finish this without ")).append(low.himHer()).append(" if we have to!\"\n\n").toString());
                        low.say(t, "\"I-I'm fine, just worry about yourselves!");
                    }
                }
            } else
            if(w.getRelationship(high.getNumber(), low.getNumber()) >= 0)
            {
                high.say(t, (new StringBuilder("You'd better not screw this up, ")).append(mid.getMainName()).append("!\"\n\n").toString());
                mid.say(t, "\"");
                if(w.getRelationship(mid.getNumber(), low.getNumber()) >= 0)
                {
                    mid.say(t, (new StringBuilder("Worry about yourself, ")).append(high.getMainName()).append("!\"\n\n").toString());
                    low.say(t, "\"P-Please, you two, we shouldn't be fighting each other now of all times!");
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
            if(w.getRelationship(first.getNumber(), second.getNumber()) >= 0)
            {
                first.say(t, "\n\n\"If we work together, I think we can still stop it!  Back me up!\"\n\n");
                second.say(t, "\"Got it!  I'm right behind you!\"\n\n");
                w.append(t, (new StringBuilder(String.valueOf(first.getMainName()))).append(" charges forward, blazing brighter than the sun as ").append(first.heShe()).append(" draws on as much psychic energy as ").append(first.heShe()).append(" can.  ").append(second.getMainName()).append(" has a hand on ").append(first.hisHer()).append(" shoulder, pushing ").append(first.himHer()).append(" forward as they fly together.  The two of them blast through the base of the tower, leaving an enormous hole behind them.  And with its lower structure compromised, the shaft begins to topple to one side.  It lands on the city with a deafening crash, kicking up a huge cloud of debris.  As quickly as that, the Demonic presence over the city lifts.\n\nThe battle is over.  But even though this Demon Lord has been defeated, the scars left on the hearts of the Chosen won't heal so easily.  ").toString());
            } else
            {
                first.say(t, "\n\n\"If we're going to take that thing down, we need to go all-out!  Don't hold back, or you'll die!\"\n\n");
                second.say(t, "\"Huh?  Gaaah!  Ergh... you're... crazy...!\"\n\n");
                w.append(t, (new StringBuilder(String.valueOf(first.getMainName()))).append(" holds out one palm to shoot a beam of crackling destructive energy directly at ").append(second.getMainName()).append(".  For ").append(second.hisHer()).append(" part, ").append(second.getMainName()).append(" barely reacts in time to intercept the beam with ").append(second.hisHer()).append(" own blast.  The glowing line between ").append(first.getMainName()).append("'s hand and ").append(second.getMainName()).append("'s annihilates everything it touches as the two of them run toward the Demonic spire.  When it cuts into the base of the tower, the opposing energies cause a huge explosion that throws the two Chosen in different directions.  When they come to their senses, they see the structure beginning to tilt to one side.  It finally topples, throwing up a huge cloud of debris as it lands on the city below.  As quickly as that, the Demonic presence over the city lifts.\n\nThe battle is over.  But even though this Demon Lord has been defeated, the scars left on the hearts of the Chosen won't heal so easily.  ").toString());
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
            if(escaped[0].getConfidence() > 66)
                escaped[0].say(t, "No!  I... I should be strong enough...!");
            else
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
                w.append(t, (new StringBuilder("As much as it pains ")).append(escaped[0].himHer()).append(" to do so, ").append(escaped[0].getMainName()).append(" turns and flees.  This battle may be lost, but ").append(escaped[0].heShe()).append("'s determined to escape and survive to fight another day.").toString());
        }
        EndFinalBattle(t, p, f, w);
    }

    public static void EndFinalBattle(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        p.removeAll();
        JButton Continue = new JButton("Continue");
        Continue.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                if(w.isCheater().booleanValue())
                {
                    p.removeAll();
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nI hope you enjoyed this playthrough of Corrupted Saviors!  It looks like cheats are enabled on this file, so the traditional score display won't show up here.  In future versions, there will be a more satisfying epilogue for each of the surviving Chosen, and there will also be options to use your corrupted Chosen in future playthroughs, even on cheat files.  Look forward to it!").toString());
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

                    JButton ContinueFour = new JButton("Continue");
                    ContinueFour.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            Project.Shop(t, p, f, w);
                        }

                        final _cls116 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;

                    
                    {
                        this$1 = _cls116.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                    });
                    p.add(ContinueFour);
                    p.validate();
                    p.repaint();
                } else
                {
                    p.removeAll();
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

                    JButton ContinueFour = new JButton("Continue");
                    ContinueFour.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            Project.Shop(t, p, f, w);
                        }

                        final _cls116 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;

                    
                    {
                        this$1 = _cls116.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                    });
                    p.add(ContinueFour);
                    p.validate();
                    p.repaint();
                }
            }

            private final WorldState val$w;
            private final JPanel val$p;
            private final JTextPane val$t;
            private final JFrame val$f;

            
            {
                w = worldstate;
                p = jpanel;
                t = jtextpane;
                f = jframe;
                super();
            }
        });
        p.add(Continue);
        p.validate();
        p.repaint();
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
    public JFrame window;
}
