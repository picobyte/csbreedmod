
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
        controlPanel.setMaximumSize(new Dimension(1300, 40));
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
        path = path.replaceAll("\\u0020", "\\ ");
        File saveLocation = new File((new StringBuilder(String.valueOf(path))).append("\\saves.sav").toString());
        SaveData saves = null;
        if(saveLocation.exists())
        {
            ReadObject robj = new ReadObject();
            saves = robj.deserializeSaveData((new StringBuilder(String.valueOf(path))).append("\\saves.sav").toString());
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
        w.append(t, (new StringBuilder("Corrupted Saviors, Release 6b: \"Variation\"\n\nThis game contains content of an adult nature and should not be played by the underaged or by those unable to distinguish fantasy from reality.\n\n")).append(w.getSeparator()).append("\n\nJapan, mid-21st century.  The psychic energies of humanity have finally begun to coalesce into physical form.  The resulting beings are known as Demons.  Born from the base desires suppressed deep within the human mind, these creatures spread across the planet, leaving chaos and depravity in their wake.\n\nBut Demons do not represent the entirety of the human condition.  The hopes and determination of humanity have also risen up, gathering in the bodies of a few Chosen warriors in order to grant them the power to fight the Demons.  Although each of them was once an ordinary person, their new abilities place them at the center of the struggle for the soul of humanity.\n\nYou are a Demon Lord, the highest form of Demon, with your own mind and will, focused on the corruption of all that is good in the world.  The Chosen are the keystone of humanity's resistance to your goal, but to simply kill them would be meaningless.  Instead, shatter their notions of right and wrong, showing them the true darkness that hides within!").toString());
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
                Project.IntroTwo(t, p, f, w);
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
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nCopyright 2019-2020 by CSdev. Corrupted Saviors is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License. To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/4.0/.\n\nIf you like this game, please share it and discuss it so that it can be further enjoyed and improved!  There is a good chance that the developer reads whatever forum you found it on.  Direct feedback can also be sent to corruptedsaviors@gmail.com").toString());
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
            path = path.replaceAll("\\u0020", "\\ ");
            File saveLocation = new File((new StringBuilder(String.valueOf(path))).append("\\saves.sav").toString());
            SaveData saves = null;
            if(saveLocation.exists())
            {
                ReadObject robj = new ReadObject();
                saves = robj.deserializeSaveData((new StringBuilder(String.valueOf(path))).append("\\saves.sav").toString());
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
        JButton Genders = new JButton("Change Composition");
        Genders.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Project.GenderMenu(t, p, f, w, CheatVisibility);
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
        p.add(Genders);
        if(w.getGenderBalance()[2] > 0)
        {
            JButton MaleShift = new JButton("Toggle Male Shifting");
            MaleShift.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.changeMaleShift();
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
            p.add(MaleShift);
        }
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
        else
            Back.setForeground(Color.GRAY);
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
                if(w.getCombatants()[i].isSurrounded().booleanValue())
                {
                    w.orangeAppend(t, (new StringBuilder("\n")).append(w.getCombatants()[i].getMainName()).append(": ").toString());
                    if(w.getCombatants()[i].isInseminated().booleanValue())
                    {
                        w.orangeAppend(t, "Inseminated");
                        inseminated++;
                    } else
                    if(w.getCombatants()[i].isOrgasming().booleanValue())
                    {
                        w.orangeAppend(t, "Orgasming");
                        orgasming++;
                    } else
                    if(w.getCombatants()[i].isSodomized().booleanValue())
                    {
                        if(w.getCombatants()[i].getGender().equals("male"))
                            w.orangeAppend(t, "Tortured");
                        else
                            w.orangeAppend(t, "Sodomized");
                        sodomized++;
                    } else
                    if(w.getCombatants()[i].isBroadcasted().booleanValue())
                    {
                        w.orangeAppend(t, "Broadcasted");
                        broadcasted++;
                    } else
                    {
                        w.orangeAppend(t, "Surrounded");
                    }
                    if(w.getCombatants()[i].getSurroundDuration() > 1)
                        w.orangeAppend(t, (new StringBuilder(" for ")).append(w.getCombatants()[i].getSurroundDuration()).append(" more turns").toString());
                    else
                        w.orangeAppend(t, " until next turn");
                } else
                if(w.getCombatants()[i].isCaptured().booleanValue())
                {
                    w.orangeAppend(t, (new StringBuilder("\n")).append(w.getCombatants()[i].getMainName()).append(": ").toString());
                    if(w.getCombatants()[i].getCaptureProgression() < w.getCaptureDuration())
                        w.orangeAppend(t, (new StringBuilder("Captured for ")).append((w.getCaptureDuration() - w.getCombatants()[i].getCaptureProgression()) + 1).append(" more turns").toString());
                    else
                        w.orangeAppend(t, "Captured until next turn");
                } else
                if(w.getCombatants()[i].surroundPossible(w).booleanValue())
                    w.purpleAppend(t, (new StringBuilder("\n")).append(w.getCombatants()[i].getMainName()).append(": Opening Level ").append(w.getCombatants()[i].getFEAROpening(w) + w.getCombatants()[i].getPAINOpening() + w.getCombatants()[i].getDISGOpening() + w.getCombatants()[i].getSHAMOpening(w)).append(" vs. Defense Level ").append(w.getCombatants()[i].getDefenseLevel()).toString());
                else
                if(w.getCombatants()[i].getDefenseLevel() > 9000)
                    w.append(t, (new StringBuilder("\n")).append(w.getCombatants()[i].getMainName()).append(": Flying Above Battlefield").toString());
                else
                    w.append(t, (new StringBuilder("\n")).append(w.getCombatants()[i].getMainName()).append(": Opening Level ").append(w.getCombatants()[i].getFEAROpening(w) + w.getCombatants()[i].getPAINOpening() + w.getCombatants()[i].getDISGOpening() + w.getCombatants()[i].getSHAMOpening(w)).append(" vs. Defense Level ").append(w.getCombatants()[i].getDefenseLevel()).toString());

        if(w.getRallyBonus() > 0)
            w.append(t, (new StringBuilder("\n\nMorale bonus: incoming trauma decreased by ")).append(w.getRallyBonus() / 6).append("%").toString());
        if(w.getDistractBonus() > 0)
            w.append(t, (new StringBuilder("\n\nThralls distracted: damage to surrounded Chosen decreased by ")).append(w.getDistractBonus() / 3).append("%").toString());
        if(w.getCombatants()[1] == null)
        {
            if(w.getCast()[1] != null)
                w.append(t, (new StringBuilder("\n\n")).append(w.getCombatants()[0].getMainName()).append(" is fighting alone!").toString());
            PickAction(t, p, f, w, w.getCombatants()[0], initiative);
        } else
        {
            p.removeAll();
            w.chatter(t);
            w.append(t, "\n\nWho will you target?");
            for(int i = 0; i < 3; i++)
                if(w.getCombatants()[i] != null)
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
                    if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && (w.getActions()[w.getCurrentAction()] - 1) / 14 == w.getCombatants()[thisChosen].getNumber() * 14)
                        Target.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                    Target.getActionMap().put("pressed", TargetAction);
                    p.add(Target);
                }

            class _cls1PassButton extends AbstractAction
            {

                public void actionPerformed(ActionEvent e)
                {
                    Project.advanceAction(p, w, 0);
                    Project.EnemyTurn(t, p, f, w, initiative, 0);
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
            p.add(Pass);
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
                    generated = "Do nothing.";
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
                        generated = "Attack ";
                    else
                    if(type == 6)
                        generated = "Taunt ";
                    else
                    if(type == 7)
                        generated = "Grind against ";
                    else
                    if(type == 8)
                        generated = "Caress ";
                    else
                    if(type == 9)
                        generated = "Pummel ";
                    else
                    if(type == 10)
                        generated = "Humiliate ";
                    else
                    if(type == 11)
                        generated = "Inseminate ";
                    else
                    if(type == 12)
                        generated = "Force Orgasm on ";
                    else
                    if(type == 13)
                    {
                        if(w.getCast()[target].getGender().equals("male"))
                            generated = "Torture ";
                        else
                            generated = "Sodomize ";
                    } else
                    if(type == 14)
                        generated = "Broadcast ";
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
        class _cls1AttackButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
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

            private final Chosen val$c;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Action val$ContinueAction;

            public _cls1AttackButton(WorldState worldstate, 
                    Action action)
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                ContinueAction = action;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action AttackAction = new _cls1AttackButton(w, ContinueAction);
        class _cls1SlimeButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
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

            private final Chosen val$c;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Action val$ContinueAction;

            public _cls1SlimeButton(WorldState worldstate, 
                    Action action)
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                ContinueAction = action;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action SlimeAction = new _cls1SlimeButton(w, ContinueAction);
        class _cls1TauntButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
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

            private final Chosen val$c;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Action val$ContinueAction;

            public _cls1TauntButton(WorldState worldstate, 
                    Action action)
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                ContinueAction = action;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action TauntAction = new _cls1TauntButton(w, ContinueAction);
        class _cls1ThreatenButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
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
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Action val$ContinueAction;

            public _cls1ThreatenButton(WorldState worldstate, 
                    Action action)
            {
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                ContinueAction = action;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action ThreatenAction = new _cls1ThreatenButton(w, ContinueAction);
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
        class _cls1SurroundButton extends AbstractAction
        {

            public void actionPerformed(ActionEvent e)
            {
                w.setSurroundTarget(c);
                Project.advanceAction(p, w, c.getNumber() * 14 + 1);
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }

            private final WorldState val$w;
            private final Chosen val$c;
            private final JPanel val$p;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final Chosen val$initiative[];

            public _cls1SurroundButton(JFrame jframe, 
                    Chosen achosen[])
            {
                w = worldstate;
                c = chosen;
                p = jpanel;
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
                if(w.upgradedCommander().booleanValue())
                    w.setCaptureTarget(c);
                else
                    w.setSurroundTarget(c);
                Project.advanceAction(p, w, c.getNumber() * 14 + 2);
                Project.EnemyTurn(t, p, f, w, initiative, 0);
            }

            private final WorldState val$w;
            private final Chosen val$c;
            private final JPanel val$p;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final Chosen val$initiative[];

            public _cls1CaptureButton(JFrame jframe, 
                    Chosen achosen[])
            {
                w = worldstate;
                c = chosen;
                p = jpanel;
                t = jtextpane;
                f = jframe;
                initiative = achosen;
                super(text);
                putValue("ShortDescription", desc);
            }
        }

        Action CaptureAction = new _cls1CaptureButton(f, initiative);
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
                Project.EnemyTurn(t, p, f, w, initiative, 0);
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
            if(!c.getGrind().booleanValue())
            {
                JButton Grind = new JButton(GrindAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -60);
                    }

                };
                Grind.setToolTipText("<html><center>Inflicts HATE along with<br>FEAR, DISG, PAIN, and SHAM<br>Can cause tier-1 Morality or Dignity Break</center></html>");
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
                Caress.setToolTipText("<html><center>Inflicts PLEA along with<br>DISG, PAIN, SHAM, and FEAR<br>Can cause tier-1 Innocence or Confidence Break</center></html>");
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
                Pummel.setToolTipText("<html><center>Inflicts INJU along with<br>PAIN, SHAM, FEAR, and DISG<br>Can cause tier-1 Morality or Confidence Break</center></html>");
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
                Humiliate.setToolTipText("<html><center>Inflicts EXPO along with<br>SHAM, FEAR, DISG, and PAIN<br>Can cause tier-1 Innocence or Dignity Break</center></html>");
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
                    Inseminate.setBackground(PURPLISH);
                else
                    Inseminate.setBackground(YELLOWISH);
                Inseminate.setToolTipText("<html><center>Inflicts HATE and PLEA along with<br>FEAR, DISG, PAIN, and SHAM<br>Causes tier-2 Morality Break</center></html>");
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
                    ForceOrgasm.setBackground(PURPLISH);
                else
                    ForceOrgasm.setBackground(YELLOWISH);
                ForceOrgasm.setToolTipText("<html><center>Inflicts PLEA and INJU along with<br>DISG, PAIN, SHAM, and FEAR<br>Causes tier-2 Innocence Break</center></html>");
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
                    Sodomize.setBackground(PURPLISH);
                else
                    Sodomize.setBackground(YELLOWISH);
                Sodomize.setToolTipText("<html><center>Inflicts INJU and EXPO along with<br>PAIN, SHAM, FEAR, and DISG<br>Causes tier-2 Confidence Break</center></html>");
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
                    Broadcast.setBackground(PURPLISH);
                else
                    Broadcast.setBackground(YELLOWISH);
                Broadcast.setToolTipText("<html><center>Inflicts EXPO and HATE along with<br>SHAM, FEAR, DISG, and PAIN<br>Causes tier-2 Dignity Break</center></html>");
                p.add(Broadcast);
                Broadcast.getInputMap(2).put(KeyStroke.getKeyStroke("8"), "pressed");
                if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 14)
                    Broadcast.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Broadcast.getActionMap().put("pressed", BroadcastAction);
            }
            if(defilers > 1)
                w.append(t, (new StringBuilder("  ")).append(defilers).append(" defiler actions possible.").toString());
            else
            if(defilers == 1)
                w.append(t, "  1 defiler action possible.");
        } else
        if(c.isCaptured().booleanValue())
        {
            w.append(t, (new StringBuilder("\n\n")).append(c.getMainName()).append(" is captured by your Commander.  Any attempts by other Demons to attack would simply get in the way.").toString());
        } else
        {
            JButton Attack = new JButton(AttackAction) {

                public Point getToolTipLocation(MouseEvent e)
                {
                    return new Point(0, -30);
                }

            };
            Attack.setToolTipText("Inflicts PAIN");
            Attack.getInputMap(2).put(KeyStroke.getKeyStroke("3"), "pressed");
            if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 5)
                Attack.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
            Attack.getActionMap().put("pressed", AttackAction);
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
                if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 1)
                    Surround.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Surround.getActionMap().put("pressed", SurroundAction);
                p.add(Surround);
            }
            if((!c.surroundPossible(w).booleanValue() || w.upgradedCommander().booleanValue()) && w.getCapturesPossible() > 0 && c.getDefenseLevel() < 9000 && w.commanderFree().booleanValue())
            {
                JButton Capture = new JButton(CaptureAction) {

                    public Point getToolTipLocation(MouseEvent e)
                    {
                        return new Point(0, -90);
                    }

                };
                Capture.setBackground(PURPLISH);
                String description = "<html><center>Constantly inflicts ";
                if(w.getBodyStatus()[11].booleanValue())
                    description = (new StringBuilder(String.valueOf(description))).append("HATE and PLEA along with<br>FEAR, DISG, PAIN, and SHAM").toString();
                else
                if(w.getBodyStatus()[12].booleanValue())
                    description = (new StringBuilder(String.valueOf(description))).append("PLEA and INJU along with<br>DISG, PAIN, SHAM, and FEAR").toString();
                else
                if(w.getBodyStatus()[13].booleanValue())
                    description = (new StringBuilder(String.valueOf(description))).append("INJU and EXPO along with<br>PAIN, SHAM, FEAR, and DISG").toString();
                else
                if(w.getBodyStatus()[14].booleanValue())
                    description = (new StringBuilder(String.valueOf(description))).append("EXPO and HATE along with<br>SHAM, FEAR, DISG, and PAIN").toString();
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
                        description = (new StringBuilder(String.valueOf(description))).append("INJU").toString();
                        firstFound = Boolean.valueOf(true);
                    }
                    if(w.getBodyStatus()[6].booleanValue())
                        description = " and EXPO";
                    description = (new StringBuilder(String.valueOf(description))).append(" along with<br>all four traumas").toString();
                } else
                if(w.getBodyStatus()[3].booleanValue())
                    description = (new StringBuilder(String.valueOf(description))).append("HATE along with<br>FEAR, DISG, PAIN, and SHAM").toString();
                else
                if(w.getBodyStatus()[4].booleanValue())
                    description = (new StringBuilder(String.valueOf(description))).append("PLEA along with<br>DISG, PAIN, SHAM, and FEAR").toString();
                else
                if(w.getBodyStatus()[5].booleanValue())
                    description = (new StringBuilder(String.valueOf(description))).append("INJU along with<br>PAIN, SHAM, FEAR, and DISG").toString();
                else
                if(w.getBodyStatus()[6].booleanValue())
                    description = (new StringBuilder(String.valueOf(description))).append("EXPO along with<br>SHAM, FEAR, DISG, and PAIN").toString();
                else
                    description = "Surrounds the target";
                description = (new StringBuilder(String.valueOf(description))).append("<br>for ").toString();
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
                    description = (new StringBuilder(String.valueOf(description))).append("<br>Above 10k INJU, causes tier-2 Confidence Break").toString();
                else
                if(w.getBodyStatus()[14].booleanValue())
                    description = (new StringBuilder(String.valueOf(description))).append("<br>Above 10k EXPO, causes tier-2 Dignity Break").toString();
                description = (new StringBuilder(String.valueOf(description))).append("</center></html>").toString();
                Capture.setToolTipText(description);
                Capture.getInputMap(2).put(KeyStroke.getKeyStroke(67, 0), "pressed");
                if(w.onTrack.booleanValue() && w.getActions().length > w.getCurrentAction() && w.getActions()[w.getCurrentAction()] == c.getNumber() * 14 + 2)
                    Capture.getInputMap(2).put(KeyStroke.getKeyStroke("SPACE"), "pressed");
                Capture.getActionMap().put("pressed", CaptureAction);
                p.add(Capture);
            }
        }
        if(w.getCombatants()[1] != null)
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
        }
        if(w.writePossible().booleanValue())
            addWriteButton(p, w);
        p.validate();
        p.repaint();
    }

    public static void EnemyTurn(JTextPane t, JPanel p, JFrame f, WorldState w, Chosen initiative[], int progress)
    {
        w.clearBonus(progress);
        if(w.getCaptureTarget() == initiative[progress] || initiative[progress].isCaptured().booleanValue())
            initiative[progress].BeCaptured(t, p, f, w);
        else
        if(w.getSurroundTarget() == initiative[progress] || initiative[progress].isSurrounded().booleanValue())
            initiative[progress].BeSurrounded(t, p, f, w);
        else
            initiative[progress].TakeTurn(t, p, f, w);
        progress++;
        p.removeAll();
        int currentProgress = progress;
        Boolean moreTurns = Boolean.valueOf(true);
        if(progress > 2)
            moreTurns = Boolean.valueOf(false);
        else
        if(initiative[progress] == null)
            moreTurns = Boolean.valueOf(false);
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
                        class _cls2ContinueButtonTwo extends AbstractAction
                        {

                            public void actionPerformed(ActionEvent e)
                            {
                                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                                if(w.endTurn(t))
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
                                } else
                                {
                                    Project.PickTarget(t, p, f, w);
                                    if(w.tutorialResponse().booleanValue())
                                        if(w.getBattleRound() == 6)
                                        {
                                            if(w.getCast()[0].isCaptured().booleanValue())
                                                w.grayAppend(t, "\n\n(The factors that determine when reinforcements show up are the personalities and relationships of the initially-targeted Chosen and the arriving Chosen.  This means that as long as their relationship doesn't change, Calamity will always show up on Round 6 when we attack Miracle.\n\nLet's target Calamity and then use Examine to see what she's like.)");
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
                                    w.grayAppend(t, "\n\n(Surrounding Miracle right now will only give us one turn to torment her.  In other situations, it might be a good idea to create another opening to increase the duration.  But since she's pretty weak to INJU, and since we have the upgrade that increases circumstance damage, one turn should be plenty.  Surround her!)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 4)
                            {
                                if(w.getCast()[0].isSurrounded().booleanValue())
                                    w.grayAppend(t, "\n\n(Pummel deals INJU damage, while Humiliate deals EXPO damage.  INJU's main effect is to multiply other circumstance damage, so it's generally better to start there.  Try Pummeling her.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 5)
                            {
                                if(w.getCast()[0].getCurrentINJU() == 148L)
                                    w.grayAppend(t, "\n\n(She escaped quickly, but not before getting level 1 INJU.  Beating her defense level of 3 for another Surround might be difficult, but we have a Commander, and Commanders ignore defense level.  The one prepared for this battle cost 2 Evil Energy: 1 base, plus 1 to increase its duration to 3 rounds.  It was given the Mania upgrade, which allows it to inflict EXPO.  EXPO's effect is to multiply damage dealt to other Chosen.  Use Capture to summon your Commander!)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 8)
                            {
                                if(w.getCast()[1].getCurrentPAIN() == 384L)
                                    w.grayAppend(t, "\n\n(And finally, let's increase the opening by one more level with Taunt on Calamity.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 9)
                            {
                                if(w.getCast()[1].getCurrentSHAM() == 302L)
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
                                if(w.getCast()[1].getCurrentHATE() == 372L)
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
                                    w.grayAppend(t, "\n\n(The first level of vulnerability is broken when Chosen use desperate methods to stop themselves from going over 10,000 circumstance damage.  They look at their susceptibility to those circumstances and the number of rounds they'll be surrounded when deciding whether they're at risk.  Calamity's multipliers are already high, so use Pummel to try making them go even higher!)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 15)
                            {
                                if(w.getCast()[1].getCurrentINJU() == 144L)
                                    w.grayAppend(t, "\n\n(Calamity was taking almost 400 HATE damage from Grind with a x4 multiplier, and now she has a x16 multiplier.  Considering that she doesn't know whether you have another Commander in reserve to stop Miracle and Mirage from distracting the Thralls, she has good reason to worry about being brought to over 10k HATE.  Use Grind on her to force her use desperate measures to defend herself!)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 16)
                            {
                                if(w.getCast()[1].getCurrentHATE() == 2748L)
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
        if(!w.getCombatants()[0].isIntroduced().booleanValue())
        {
            justContinue = Boolean.valueOf(false);
            w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
            w.getCombatants()[0].printIntro(t, w);
        } else
        if(w.getCast()[1] != null)
            if(!w.getCast()[1].isIntroduced().booleanValue())
            {
                justContinue = Boolean.valueOf(false);
                postScene = Boolean.valueOf(true);
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                w.getCombatants()[0].firstMeeting(t, w, w.getCombatants()[1]);
                p.removeAll();
                JButton Continue = new JButton("Continue");
                Continue.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        w.getCombatants()[1].printIntro(t, w);
                        JButton Continue2 = new JButton("Continue");
                        Continue2.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                Project.Downtime(t, p, f, w);
                            }

                            final _cls46 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls46.this;
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

                                final _cls47 this$1;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final WorldState val$w;

                    
                    {
                        this$1 = _cls47.this;
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
                                if(w.isCheater().booleanValue())
                                {
                                    Project.Downtime(t, p, f, w);
                                } else
                                {
                                    w.scoreSummary(t);
                                    JButton ContinueFour = new JButton("Continue");
                                    ContinueFour.addActionListener(new ActionListener() {

                                        public void actionPerformed(ActionEvent e)
                                        {
                                            Project.Downtime(t, p, f, w);
                                        }

                                        final _cls1 this$3;
                                        private final JTextPane val$t;
                                        private final JPanel val$p;
                                        private final JFrame val$f;
                                        private final WorldState val$w;

                            
                            {
                                this$3 = _cls1.this;
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

                    final _cls53 this$1;
                    private final JPanel val$p;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final Chosen val$order[];
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls53.this;
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

                    final _cls54 this$1;
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
                        this$1 = _cls54.this;
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
        int totalActions = 13;
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

        for(int i = 0; i <= lastChosen; i++)
        {
            actionWeights[i][0] = 150L;
            actionWeights[i][1] = 50L + (w.getCast()[i].getTotalFEAR() * 100L) / (long)(100 + w.getCast()[i].getMorality());
            actionWeights[i][2] = 50L + (w.getCast()[i].getTotalDISG() * 100L) / (long)(100 + w.getCast()[i].getMorality());
            actionWeights[i][3] = 50L + (w.getCast()[i].getTotalPAIN() * 100L) / (long)(100 + w.getCast()[i].getMorality());
            actionWeights[i][4] = 50L + (w.getCast()[i].getTotalSHAM() * 100L) / (long)(100 + w.getCast()[i].getMorality());
            if(w.getCast()[i].isRuthless())
                actionWeights[i][5] = (w.getCast()[i].getTotalFEAR() * 200L + w.getCast()[i].getTotalPAIN() * 100L + w.getCast()[i].getANGST() * 20L) / (long)(100 + w.getCast()[i].getMorality()) - 20000L;
            else
                actionWeights[i][5] = 0L;
            if(w.getCast()[i].isLustful())
                actionWeights[i][6] = (w.getCast()[i].getTotalDISG() * 200L + w.getCast()[i].getTotalFEAR() * 100L + w.getCast()[i].getANGST() * 20L) / (long)(100 + w.getCast()[i].getMorality()) - 20000L;
            else
                actionWeights[i][6] = 0L;
            if(w.getCast()[i].isMeek())
                actionWeights[i][7] = (w.getCast()[i].getTotalPAIN() * 200L + w.getCast()[i].getTotalSHAM() * 100L + w.getCast()[i].getANGST() * 20L) / (long)(100 + w.getCast()[i].getMorality()) - 20000L;
            else
                actionWeights[i][7] = 0L;
            if(w.getCast()[i].isDebased())
                actionWeights[i][8] = (w.getCast()[i].getTotalSHAM() * 200L + w.getCast()[i].getTotalDISG() * 100L + w.getCast()[i].getANGST() * 20L) / (long)(100 + w.getCast()[i].getMorality()) - 20000L;
            else
                actionWeights[i][8] = 0L;
            if(!w.getCast()[i].isVVirg())
                actionWeights[i][9] = (w.getCast()[i].getTotalFEAR() * 400L + w.getCast()[i].getTotalDISG() * 200L + w.getCast()[i].getANGST() * 40L) / (long)(100 + w.getCast()[i].getMorality()) - 0x3d0900L;
            else
                actionWeights[i][9] = 0L;
            if(!w.getCast()[i].isCVirg())
                actionWeights[i][10] = (w.getCast()[i].getTotalDISG() * 400L + w.getCast()[i].getTotalPAIN() * 200L + w.getCast()[i].getANGST() * 40L) / (long)(100 + w.getCast()[i].getMorality()) - 0x3d0900L;
            else
                actionWeights[i][10] = 0L;
            if(!w.getCast()[i].isAVirg())
                actionWeights[i][11] = (w.getCast()[i].getTotalPAIN() * 400L + w.getCast()[i].getTotalSHAM() * 200L + w.getCast()[i].getANGST() * 40L) / (long)(100 + w.getCast()[i].getMorality()) - 0x3d0900L;
            else
                actionWeights[i][11] = 0L;
            if(!w.getCast()[i].isModest())
                actionWeights[i][12] = (w.getCast()[i].getTotalSHAM() * 400L + w.getCast()[i].getTotalFEAR() * 200L + w.getCast()[i].getANGST() * 40L) / (long)(100 + w.getCast()[i].getMorality()) - 0x3d0900L;
            else
                actionWeights[i][12] = 0L;
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

                            final _cls56 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls56.this;
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

                        final _cls57 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;

                    
                    {
                        this$1 = _cls57.this;
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

                        final _cls58 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;

                    
                    {
                        this$1 = _cls58.this;
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

                                final _cls59 this$1;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final int val$chosenAction[];

                    
                    {
                        this$1 = _cls59.this;
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

                                final _cls59 this$1;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final WorldState val$w;

                    
                    {
                        this$1 = _cls59.this;
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
        if(w.getDay() > 30 || w.getEarlyCheat().booleanValue())
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
                        if(w.getDay() < 31)
                            w.append(t, ", but you will not receive a score for the playthrough");
                        w.append(t, ".  Activate Cheat Mode?");
                        JButton Activate = new JButton("Activate Cheat Mode");
                        Activate.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                w.setCheater();
                                Project.Cheat(t, p, f, w);
                            }

                            final _cls61 this$1;
                            private final WorldState val$w;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;

                    
                    {
                        this$1 = _cls61.this;
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

                            final _cls61 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls61.this;
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
                        JButton Buy = new JButton(w.getTechs()[i].getName());
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

                                    final _cls62 this$1;
                                    private final WorldState val$w;
                                    private final int val$thisTech;
                                    private final JPanel val$p;
                                    private final JTextPane val$t;
                                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls62.this;
                        w = worldstate;
                        thisTech = i;
                        p = jpanel;
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

                                    final _cls62 this$1;
                                    private final JTextPane val$t;
                                    private final JPanel val$p;
                                    private final JFrame val$f;
                                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls62.this;
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
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhose profile will you review?").toString());
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

                                final _cls63 this$1;
                                private final JPanel val$p;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final int val$thisChosen;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls63.this;
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

                    JButton Back = new JButton("Back");
                    Back.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            Project.Shop(t, p, f, w);
                        }

                        final _cls63 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;

                    
                    {
                        this$1 = _cls63.this;
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

                    final _cls65 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls65.this;
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

                    final _cls65 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls65.this;
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

                    final _cls65 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls65.this;
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

                    final _cls65 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls65.this;
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

                    final _cls65 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls65.this;
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

                    final _cls65 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls65.this;
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

                    final _cls65 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls65.this;
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

                    final _cls66 this$1;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls66.this;
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
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhich cheat would you like to use?").toString());
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
        path = path.replaceAll("\\u0020", "\\ ");
        File saveLocation = new File((new StringBuilder(String.valueOf(path))).append("\\saves.sav").toString());
        SaveData saves = null;
        if(saveLocation.exists())
        {
            ReadObject robj = new ReadObject();
            saves = robj.deserializeSaveData((new StringBuilder(String.valueOf(path))).append("\\saves.sav").toString());
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
                                path = path.replaceAll("\\u0020", "\\ ");
                                File saveLocation = new File((new StringBuilder(String.valueOf(path))).append("\\saves.sav").toString());
                                SaveData saves = null;
                                if(saveLocation.exists())
                                {
                                    ReadObject robj = new ReadObject();
                                    saves = robj.deserializeSaveData((new StringBuilder(String.valueOf(path))).append("\\saves.sav").toString());
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
                                        if(savedWorld.getDay() == 1)
                                            Project.IntroTwo(t, p, f, saveFile.getSaves()[0]);
                                        else
                                            Project.Shop(t, p, f, saveFile.getSaves()[0]);
                                    }

                                    final _cls77 this$1;
                                    private final SaveData val$saveFile;
                                    private final int val$fileSelected;
                                    private final WriteObject val$wobj;
                                    private final JTextPane val$t;
                                    private final WorldState val$w;
                                    private final JPanel val$p;
                                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls77.this;
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
                                if(savedWorld.getDay() == 1)
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

                                final _cls77 this$1;
                                private final SaveData val$saveFile;
                                private final int val$fileSelected;
                                private final WriteObject val$wobj;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls77.this;
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
            Boolean defilerUsed = Boolean.valueOf(false);
            Boolean defilerKnown = Boolean.valueOf(false);
            if(w.getBodyStatus()[11].booleanValue() || w.getBodyStatus()[12].booleanValue() || w.getBodyStatus()[13].booleanValue() || w.getBodyStatus()[14].booleanValue())
                defilerUsed = Boolean.valueOf(true);
            if(w.getTechs()[22].isOwned().booleanValue() || w.getTechs()[23].isOwned().booleanValue() || w.getTechs()[24].isOwned().booleanValue() || w.getTechs()[25].isOwned().booleanValue())
                defilerKnown = Boolean.valueOf(true);
            final int suppressorsUsedFinal = suppressorsUsed;
            if((suppressorsKnown > 0 && suppressorsUsed == 0 || suppressorsKnown > 1 && suppressorsUsed == 1 && w.getEvilEnergy() >= 5 && w.getTechs()[21].isOwned().booleanValue()) && !defilerUsed.booleanValue())
            {
                JButton Suppressor = new JButton("Suppressor Upgrades");
                Suppressor.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        if(w.getTechs()[10].isOwned().booleanValue() && !w.getBodyStatus()[3].booleanValue())
                        {
                            JButton Hunger = new JButton("Hunger");
                            Hunger.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    if(suppressorsUsedFinal == 1)
                                        w.applyVersatility();
                                    w.applyHunger();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls82 this$1;
                                private final int val$suppressorsUsedFinal;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls82.this;
                        suppressorsUsedFinal = i;
                        w = worldstate;
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
                            JButton Lust = new JButton("Lust");
                            Lust.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    if(suppressorsUsedFinal == 1)
                                        w.applyVersatility();
                                    w.applyLust();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls82 this$1;
                                private final int val$suppressorsUsedFinal;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls82.this;
                        suppressorsUsedFinal = i;
                        w = worldstate;
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
                            JButton Anger = new JButton("Anger");
                            Anger.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    if(suppressorsUsedFinal == 1)
                                        w.applyVersatility();
                                    w.applyAnger();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls82 this$1;
                                private final int val$suppressorsUsedFinal;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls82.this;
                        suppressorsUsedFinal = i;
                        w = worldstate;
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
                            JButton Mania = new JButton("Mania");
                            Mania.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    if(suppressorsUsedFinal == 1)
                                        w.applyVersatility();
                                    w.applyMania();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls82 this$1;
                                private final int val$suppressorsUsedFinal;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls82.this;
                        suppressorsUsedFinal = i;
                        w = worldstate;
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

                            final _cls82 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls82.this;
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
                    private final int val$suppressorsUsedFinal;
                    private final JFrame val$f;

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                suppressorsUsedFinal = i;
                f = jframe;
                super();
            }
                });
                p.add(Suppressor);
            }
            if(defilerKnown.booleanValue() && !defilerUsed.booleanValue() && suppressorsUsed == 0 && w.getEvilEnergy() >= 6)
            {
                JButton Defiler = new JButton("Defiler Upgrades");
                Defiler.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        if(w.getTechs()[22].isOwned().booleanValue())
                        {
                            JButton Ambition = new JButton("Ambition");
                            Ambition.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyAmbition();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls83 this$1;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls83.this;
                        w = worldstate;
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
                            JButton Dominance = new JButton("Dominance");
                            Dominance.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyDominance();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls83 this$1;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls83.this;
                        w = worldstate;
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
                            JButton Spite = new JButton("Spite");
                            Spite.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applySpite();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls83 this$1;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls83.this;
                        w = worldstate;
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
                            JButton Vanity = new JButton("Vanity");
                            Vanity.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyVanity();
                                    Project.Customize(t, p, f, w);
                                }

                                final _cls83 this$1;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls83.this;
                        w = worldstate;
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

                            final _cls83 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls83.this;
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
                    private final JFrame val$f;

            
            {
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                super();
            }
                });
                p.add(Defiler);
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
                                        generated = (new StringBuilder(String.valueOf(generated))).append("six").toString();
                                    else
                                        generated = (new StringBuilder(String.valueOf(generated))).append("five").toString();
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
                        if(w.getBodyStatus()[16].booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append(" and two extra captures.  ").toString();
                        else
                        if(w.getBodyStatus()[8].booleanValue())
                            generated = (new StringBuilder(String.valueOf(generated))).append(" and an extra capture.  ").toString();
                        else
                            generated = (new StringBuilder(String.valueOf(generated))).append(".  ").toString();
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
                        generated = (new StringBuilder(String.valueOf(generated))).append("it after ").append(w.getCast()[index].getMainName()).append(".").toString();
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
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
        if(w.getBodyStatus()[0].booleanValue())
            w.printCommanderSummary(t, c);
        else
            w.append(t, (new StringBuilder("You will invade a neighborhood along ")).append(c.getMainName()).append("'s patrol path in order to lure ").append(c.himHer()).append(" in and attack ").append(c.himHer()).append(".").toString());
        w.append(t, "  Is this okay?");
        JButton Confirm = new JButton("Confirm");
        Confirm.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.archiveCommander(w.getDay());
                Project.advanceDowntimeAction(p, w, w.getTechs().length + c.getNumber());
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
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
        Chosen newCombatants[] = new Chosen[3];
        newCombatants[0] = c;
        w.newCombat(w, newCombatants);
        if(w.getBodyStatus()[0].booleanValue() && !w.getBodyStatus()[2].booleanValue())
        {
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
        } else
        {
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

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                new Project();
            }

        });
    }

    public JFrame window;
}
