
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        WorldState ThisState = new WorldState();
        IntroOne(textPane, controlPanel, window, ThisState);
    }

    public void IntroOne(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        w.append(t, "Corrupted Saviors, Release 2: \"Guidance\"\n\nThis game contains content of an adult nature and should not be played by the underaged or by those unable to distinguish fantasy from reality.\n\n---\n\nJapan, mid-21st century.  The psychic energies of humanity have finally begun to coalesce into physical form.  The resulting beings are known as Demons.  Born from the base desires suppressed deep within the human mind, these creatures spread across the planet, leaving chaos and depravity in their wake.\n\nBut Demons do not represent the entirety of the human condition.  The hopes and determination of humanity have also risen up, gathering in the bodies of a few Chosen warriors in order to grant them the power to fight the Demons.  Although each of them was once an ordinary person, their new abilities place them at the center of the struggle for the soul of humanity.\n\nYou are a Demon Lord, the highest form of Demon, with your own mind and will, focused on the corruption of all that is good in the world.  The Chosen are the keystone of humanity's resistance to your goal, but to simply kill them would be meaningless.  Instead, shatter their notions of right and wrong, showing them the true darkness that hides within!");
        if(w.getCast()[0] == null)
        {
            Chosen newChosen = new Chosen();
            w.initialize();
            newChosen.setName(w, w.getNameSeed());
            newChosen.generate(w);
            w.addChosen(newChosen);
            newChosen.setNumber(0);
        }
        p.removeAll();
        JButton NewGame = new JButton("New Game");
        NewGame.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                IntroTwo(t, p, f, w);
            }

            final Project this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                this$0 = Project.this;
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
                Data(t, p, f, w, "load", 0, Boolean.valueOf(false));
            }

            final Project this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                this$0 = Project.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
        });
        p.add(LoadGame);
        JButton Tutorial = new JButton("Tutorial");
        Tutorial.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                WorldState x = new WorldState();
                x.tutorialInit();
                BeginBattle(t, p, f, x, x.getCast()[0]);
                x.grayAppend(t, "\n\n(Welcome to the tutorial!  This feature is intended to demonstrate some useful techniques for corrupting the Chosen.  It uses a mid-game save file with several upgrades already purchased.  When playing from the start, it makes more sense to use the first several days experimenting to find the strengths and weaknesses of the Chosen and accumulating Evil Energy before aiming to break a vulnerability.  Read the guide.txt file included with the game for a more basic overview of the mechanics.\n\nFor now, let's start by using Examine to figure out how best to deal with Miracle.)");
            }

            final Project this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                this$0 = Project.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(Tutorial);
        JButton About = new JButton("About");
        About.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.append(t, "\n\n---\n\nCopyright 2019-2020 by CSdev. Corrupted Saviors is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License. To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/4.0/.\n\nIf you like this game, please share it and discuss it so that it can be further enjoyed and improved!  There is a good chance that the developer reads whatever forum you found it on.  Direct feedback can also be sent to corruptedsaviors@gmail.com");
            }

            final Project this$0;
            private final WorldState val$w;
            private final JTextPane val$t;

            
            {
                this$0 = Project.this;
                w = worldstate;
                t = jtextpane;
                super();
            }
        });
        p.add(About);
        p.validate();
        p.repaint();
    }

    public void IntroTwo(JTextPane t, JPanel p, JFrame f, WorldState w)
    {
        w.append(t, "\n\n---\n\nThe peaceful everyday routine of the capital city is instantly shattered as a horde of Demons and their dominated human Thralls spills out onto the street!  Screams and alarms fill the air, chaos descending on the scene in an instant.  Already, innocents are being mobbed and dragged away towards a terrible fate.\n\nJust then, a sound like a thunderclap cuts through the panic, and a voice calls out a challenge to the Demons below!\n\n");
        w.getCast()[0].say(t, (new StringBuilder("\"")).append(w.getCast()[0].announcement()).append("\"\n\n").toString());
        w.getCast()[0].transform(t, w);
        w.newCombat(w, w.getCast());
        w.append(t, "\n");
        PickTarget(t, p, f, w);
    }

    public void PickTarget(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        p.removeAll();
        for(int i = 0; i < w.getCombatants().length; i++)
            if(w.getCombatants()[i] != null)
                if(w.getCombatants()[i].isSurrounded().booleanValue())
                {
                    w.orangeAppend(t, (new StringBuilder("\n")).append(w.getCombatants()[i].getMainName()).append(": ").toString());
                    if(w.getCombatants()[i].getSurroundDuration() > 1)
                        w.orangeAppend(t, (new StringBuilder("Surrounded for ")).append(w.getCombatants()[i].getSurroundDuration()).append(" more turns").toString());
                    else
                        w.orangeAppend(t, "Surrounded until next turn");
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
            PickAction(t, p, f, w, w.getCombatants()[0]);
        } else
        {
            p.removeAll();
            w.chatter(t);
            w.append(t, "\n\nWho will you target?");
            for(int i = 0; i < 3; i++)
                if(w.getCombatants()[i] != null)
                {
                    final int thisChosen = i;
                    JButton Target = new JButton(w.getCombatants()[i].getMainName());
                    Target.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            w.append(t, "\n\n---");
                            PickAction(t, p, f, w, w.getCombatants()[thisChosen]);
                        }

                        final Project this$0;
                        private final WorldState val$w;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final int val$thisChosen;

            
            {
                this$0 = Project.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                thisChosen = i;
                super();
            }
                    });
                    p.add(Target);
                }

            p.validate();
            p.repaint();
        }
    }

    public void PickAction(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Chosen c)
    {
        p.removeAll();
        w.append(t, "\n\n");
        c.printStatus(t, w);
        if(!c.isCaptured().booleanValue())
            w.append(t, "\n\nChoose your action.");
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
        JButton Examine = new JButton("Examine");
        Examine.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                c.Examine(t, p, f, w);
            }

            final Project this$0;
            private final Chosen val$c;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                this$0 = Project.this;
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
        });
        Examine.setForeground(Color.GRAY);
        p.add(Examine);
        if(c.isSurrounded().booleanValue())
        {
            if(!c.getGrind().booleanValue())
            {
                JButton Grind = new JButton("Grind");
                Grind.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        c.beginGrind();
                        EnemyTurn(t, p, f, w, initiative, 0);
                    }

                    final Project this$0;
                    private final Chosen val$c;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final Chosen val$initiative[];

            
            {
                this$0 = Project.this;
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                initiative = achosen;
                super();
            }
                });
                p.add(Grind);
            }
            if(!c.getCaress().booleanValue())
            {
                JButton Caress = new JButton("Caress");
                Caress.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        c.beginCaress();
                        EnemyTurn(t, p, f, w, initiative, 0);
                    }

                    final Project this$0;
                    private final Chosen val$c;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final Chosen val$initiative[];

            
            {
                this$0 = Project.this;
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                initiative = achosen;
                super();
            }
                });
                p.add(Caress);
            }
            if(!c.getPummel().booleanValue())
            {
                JButton Pummel = new JButton("Pummel");
                Pummel.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        c.beginPummel();
                        EnemyTurn(t, p, f, w, initiative, 0);
                    }

                    final Project this$0;
                    private final Chosen val$c;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final Chosen val$initiative[];

            
            {
                this$0 = Project.this;
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                initiative = achosen;
                super();
            }
                });
                p.add(Pummel);
            }
            if(!c.getHumiliate().booleanValue())
            {
                JButton Humiliate = new JButton("Humiliate");
                Humiliate.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        c.beginHumiliate();
                        EnemyTurn(t, p, f, w, initiative, 0);
                    }

                    final Project this$0;
                    private final Chosen val$c;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final Chosen val$initiative[];

            
            {
                this$0 = Project.this;
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                initiative = achosen;
                super();
            }
                });
                p.add(Humiliate);
            }
        } else
        if(c.isCaptured().booleanValue())
        {
            w.append(t, (new StringBuilder("\n\n")).append(c.getMainName()).append(" is captured by your Commander.  Any attempts by other Demons to attack would simply get in the way.").toString());
        } else
        {
            JButton Attack = new JButton("Attack");
            Attack.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    c.Attack(t, p, f, w);
                    p.removeAll();
                    JButton Continue = new JButton("Continue");
                    Continue.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            EnemyTurn(t, p, f, w, initiative, 0);
                        }

                        final _cls11 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;
                        private final Chosen val$initiative[];

                    
                    {
                        this$1 = _cls11.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        initiative = achosen;
                        super();
                    }
                    });
                    p.add(Continue);
                    p.validate();
                    p.repaint();
                }

                final Project this$0;
                private final Chosen val$c;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Chosen val$initiative[];


            
            {
                this$0 = Project.this;
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                initiative = achosen;
                super();
            }
            });
            p.add(Attack);
            JButton Slime = new JButton("Slime");
            Slime.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    c.Slime(t, p, f, w);
                    p.removeAll();
                    JButton Continue = new JButton("Continue");
                    Continue.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            EnemyTurn(t, p, f, w, initiative, 0);
                        }

                        final _cls12 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;
                        private final Chosen val$initiative[];

                    
                    {
                        this$1 = _cls12.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        initiative = achosen;
                        super();
                    }
                    });
                    p.add(Continue);
                    p.validate();
                    p.repaint();
                }

                final Project this$0;
                private final Chosen val$c;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Chosen val$initiative[];


            
            {
                this$0 = Project.this;
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                initiative = achosen;
                super();
            }
            });
            p.add(Slime);
            JButton Taunt = new JButton("Taunt");
            Taunt.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    c.Taunt(t, p, f, w);
                    p.removeAll();
                    JButton Continue = new JButton("Continue");
                    Continue.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            EnemyTurn(t, p, f, w, initiative, 0);
                        }

                        final _cls13 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;
                        private final Chosen val$initiative[];

                    
                    {
                        this$1 = _cls13.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        initiative = achosen;
                        super();
                    }
                    });
                    p.add(Continue);
                    p.validate();
                    p.repaint();
                }

                final Project this$0;
                private final Chosen val$c;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Chosen val$initiative[];


            
            {
                this$0 = Project.this;
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                initiative = achosen;
                super();
            }
            });
            p.add(Taunt);
            JButton Threaten = new JButton("Threaten");
            Threaten.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    c.Threaten(t, p, f, w);
                    p.removeAll();
                    JButton Continue = new JButton("Continue");
                    Continue.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            EnemyTurn(t, p, f, w, initiative, 0);
                        }

                        final _cls14 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;
                        private final Chosen val$initiative[];

                    
                    {
                        this$1 = _cls14.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        initiative = achosen;
                        super();
                    }
                    });
                    p.add(Continue);
                    p.validate();
                    p.repaint();
                }

                final Project this$0;
                private final Chosen val$c;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Chosen val$initiative[];


            
            {
                this$0 = Project.this;
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                initiative = achosen;
                super();
            }
            });
            p.add(Threaten);
            if(c.surroundPossible(w).booleanValue())
            {
                JButton Surround = new JButton("Surround");
                Surround.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.setSurroundTarget(c);
                        EnemyTurn(t, p, f, w, initiative, 0);
                    }

                    final Project this$0;
                    private final WorldState val$w;
                    private final Chosen val$c;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final Chosen val$initiative[];

            
            {
                this$0 = Project.this;
                w = worldstate;
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                initiative = achosen;
                super();
            }
                });
                Color YELLOWISH = new Color(255, 225, 125);
                Surround.setBackground(YELLOWISH);
                p.add(Surround);
            }
            if((!c.surroundPossible(w).booleanValue() || w.getBodyStatus()[3].booleanValue() || w.getBodyStatus()[4].booleanValue() || w.getBodyStatus()[5].booleanValue() || w.getBodyStatus()[6].booleanValue()) && w.getCapturesPossible() > 0 && c.getDefenseLevel() < 9000 && w.commanderFree().booleanValue())
            {
                JButton Capture = new JButton("Capture");
                Capture.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        if(w.getBodyStatus()[3].booleanValue() || w.getBodyStatus()[4].booleanValue() || w.getBodyStatus()[5].booleanValue() || w.getBodyStatus()[6].booleanValue())
                            w.setCaptureTarget(c);
                        else
                            w.setSurroundTarget(c);
                        EnemyTurn(t, p, f, w, initiative, 0);
                    }

                    final Project this$0;
                    private final WorldState val$w;
                    private final Chosen val$c;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final Chosen val$initiative[];

            
            {
                this$0 = Project.this;
                w = worldstate;
                c = chosen;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                initiative = achosen;
                super();
            }
                });
                Color PURPLISH = new Color(225, 125, 255);
                Capture.setBackground(PURPLISH);
                p.add(Capture);
            }
        }
        if(w.getCombatants()[1] != null)
        {
            JButton Back = new JButton("Back");
            Back.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, "\n\n---\n");
                    PickTarget(t, p, f, w);
                }

                final Project this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;

            
            {
                this$0 = Project.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
            });
            p.add(Back);
        }
        JButton Pass = new JButton("Do Nothing");
        Pass.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                EnemyTurn(t, p, f, w, initiative, 0);
            }

            final Project this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Chosen val$initiative[];

            
            {
                this$0 = Project.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                initiative = achosen;
                super();
            }
        });
        Pass.setForeground(Color.GRAY);
        p.add(Pass);
        p.validate();
        p.repaint();
    }

    public void EnemyTurn(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Chosen initiative[], int progress)
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
        final int currentProgress = progress;
        Boolean moreTurns = Boolean.valueOf(true);
        if(progress > 2)
            moreTurns = Boolean.valueOf(false);
        else
        if(initiative[progress] == null)
            moreTurns = Boolean.valueOf(false);
        if(moreTurns.booleanValue())
        {
            JButton Continue = new JButton("Continue");
            Continue.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    EnemyTurn(t, p, f, w, initiative, currentProgress);
                }

                final Project this$0;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final Chosen val$initiative[];
                private final int val$currentProgress;

            
            {
                this$0 = Project.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                initiative = achosen;
                currentProgress = i;
                super();
            }
            });
            p.add(Continue);
            p.validate();
            p.repaint();
        } else
        {
            JButton Continue = new JButton("Continue");
            Continue.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, "\n\n---\n\n");
                    Boolean newChosen = Boolean.valueOf(false);
                    if(w.getCast()[1] == null)
                    {
                        if(w.getTotalRounds() >= 17)
                            newChosen = Boolean.valueOf(true);
                    } else
                    if(w.getCast()[2] == null && w.getTotalRounds() >= 60)
                        newChosen = Boolean.valueOf(true);
                    if(w.evacComplete() || w.getBattleRound() < 4)
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
                        arrivingChosen.setName(w, w.getNameSeed());
                        arrivingChosen.generate(w);
                        w.addChosen(arrivingChosen);
                        if(arrivingChosen == w.getCast()[1])
                            arrivingChosen.setNumber(1);
                        else
                            arrivingChosen.setNumber(2);
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
                        JButton Continue = new JButton("Continue");
                        Continue.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                w.append(t, "\n\n---\n\n");
                                w.endTurn(t);
                                PickTarget(t, p, f, w);
                            }

                            final _cls20 this$1;
                            private final WorldState val$w;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;

                    
                    {
                        this$1 = _cls20.this;
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
                        JButton Continue = new JButton("Continue");
                        Continue.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                w.append(t, "\n\n---\n\n");
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
                                            PostBattle(t, p, f, w);
                                        }

                                        final _cls2 this$2;
                                        private final JTextPane val$t;
                                        private final JPanel val$p;
                                        private final JFrame val$f;
                                        private final WorldState val$w;

                        
                        {
                            this$2 = _cls2.this;
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
                                    PickTarget(t, p, f, w);
                                    if(w.getBattleRound() == 6)
                                    {
                                        if(w.getCast()[0].isCaptured().booleanValue())
                                            w.grayAppend(t, "\n\n(The factors that determine when reinforcements show up are the personalities and relationships of the initially-targeted Chosen and the arriving Chosen.  This means that as long as their relationship doesn't change, Calamity will always show up on Round 6 when we attack Miracle.\n\nLet's target Calamity and then use Examine to see what she's like.)");
                                        else
                                            w.endTutorial();
                                    } else
                                    if(w.getBattleRound() == 7)
                                        if(w.getCast()[1].getCurrentPAIN() == 232L)
                                            w.grayAppend(t, "\n\n(The EXPO damage makes setting up openings in one turn very reliable, and it just climbed another level.  But now the third Chosen has arrived.  Target Truth and use Examine to see what we can expect from her.)");
                                        else
                                            w.endTutorial();
                                }
                            }

                            final _cls20 this$1;
                            private final WorldState val$w;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;


                    
                    {
                        this$1 = _cls20.this;
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
                                PostBattle(t, p, f, w);
                            }

                            final _cls20 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls20.this;
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
                        PickTarget(t, p, f, w);
                        if(w.tutorialResponse().booleanValue())
                            if(w.getBattleRound() == 2)
                            {
                                if(w.getCast()[0].getCurrentDISG() == 74L)
                                    w.grayAppend(t, "\n\n(With the right upgrades, high ANGST, or naturally high vulnerabilities, it's possible to reliably deal 100 trauma in a single turn, setting up openings very quickly.  But those don't apply here, so let's use Slime again.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 3)
                            {
                                if(w.getCast()[0].getCurrentDISG() == 148L)
                                    w.grayAppend(t, "\n\n(Surrounding Miracle right now will only give us one turn to torment her.  In other situations, it might be a good idea to create another opening to increase the duration.  But since she's pretty vulnerable to INJU, one turn should be plenty.  Surround her!)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 4)
                            {
                                if(w.getCast()[0].isSurrounded().booleanValue())
                                    w.grayAppend(t, "\n\n(Pummel deals INJU damage, while Humiliate deals EXPO damage.  INJU's main effect is to multiply other circumstance damage, so it's generally better to start there.  Try pummeling her.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 5)
                            {
                                if(w.getCast()[0].getCurrentINJU() == 123L)
                                    w.grayAppend(t, "\n\n(She escaped quickly, but not before getting level 1 INJU.  Now, it's time to use our Commander.  The one prepared for this battle cost 2 Evil Energy: 1 base, plus 1 to increase its duration to 3 rounds.  It was given the Mania upgrade, which allows it to inflict EXPO.  Use Capture to summon it!)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 8)
                            {
                                if(w.getCast()[1].getCurrentDISG() == 168L)
                                    w.grayAppend(t, "\n\n(And finally, let's increase the opening by one more level with Taunt on Calamity.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 9)
                            {
                                if(w.getCast()[1].getCurrentSHAM() == 374L)
                                    w.grayAppend(t, "\n\n(Judging by the Extermination Progress and the Extermination Per Chosen, the battle will end this round if we don't do something.  Fortunately, we've built a nice opening.  Let's Surround Calamity.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 10)
                            {
                                if(w.getCast()[1].isSurrounded().booleanValue())
                                    w.grayAppend(t, "\n\n(We don't have to worry about the battle ending as long as at least one of the Chosen are surrounded.  But any surroundings after this point will cause the target to flee to the sky afterward, so we need to wrap this up.  Let's Grind against Calamity to make use of her susceptibility to HATE.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 11)
                            {
                                if(w.getCast()[1].getCurrentHATE() == 378L)
                                    w.grayAppend(t, "\n\n(We need to make sure that the battle doesn't end before we're done with Calamity.  While she stews in her HATE, we have two rounds to set up another opening and then take it.  Miracle's defense level is too high to grab her again in that time, but we can use Truth.  Let's Threaten Truth.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 12)
                            {
                                if(w.getCast()[2].getCurrentFEAR() == 244L)
                                    w.grayAppend(t, "\n\n(And now, Surround Truth.)");
                                else
                                    w.endTutorial();
                            } else
                            if(w.getBattleRound() == 13)
                            {
                                if(w.getCast()[2].isSurrounded().booleanValue())
                                    w.grayAppend(t, "\n\n(We don't have time to do anything with Truth, but look at the opening level on Calamity!  Surround her again.)");
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
                                if(w.getCast()[1].getCurrentINJU() == 84L)
                                    w.grayAppend(t, "\n\n(The bonus Evil Energy you get from breaking the vulnerability is enough to pay for the Commander you used, and now that Calamity has a broken vulnerability, she can be induced to commit greater sins during downtime and increase your Evil Energy income even more, allowing you to buy more upgrades and Commanders to crack the harder vulnerabilities!\n\nThis concludes the tutorial for Corrupted Saviors.  You can finish the battle however you like, or even go back to the start of the tutorial to try a completely different strategy.  Good luck!)");
                                w.endTutorial();
                            }
                    }
                }

                final Project this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;


            
            {
                this$0 = Project.this;
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

    public void PostBattle(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        Boolean justContinue = Boolean.valueOf(true);
        Boolean postScene = Boolean.valueOf(false);
        if(w.isTutorial().booleanValue())
        {
            long totalTrauma = w.getCast()[0].getCurrentFEAR() + w.getCast()[0].getCurrentDISG() + w.getCast()[0].getCurrentPAIN() + w.getCast()[0].getCurrentSHAM() + w.getCast()[1].getCurrentFEAR() + w.getCast()[1].getCurrentDISG() + w.getCast()[1].getCurrentPAIN() + w.getCast()[1].getCurrentSHAM() + w.getCast()[2].getCurrentFEAR() + w.getCast()[2].getCurrentDISG() + w.getCast()[2].getCurrentPAIN() + w.getCast()[2].getCurrentSHAM();
            w.append(t, (new StringBuilder("\n\n---\n\nTotal trauma: ")).append(w.getCast()[0].condensedFormat(totalTrauma)).append("\nVulnerabilities broken:").toString());
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
                if(w.getCast()[i].isLustful())
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
                if(w.getCast()[i].isDebased())
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
        if(!w.getCombatants()[0].isIntroduced().booleanValue())
        {
            justContinue = Boolean.valueOf(false);
            w.append(t, "\n\n---\n\n");
            w.getCombatants()[0].printIntro(t, w);
        } else
        if(w.getCast()[1] != null)
            if(!w.getCast()[1].isIntroduced().booleanValue())
            {
                justContinue = Boolean.valueOf(false);
                postScene = Boolean.valueOf(true);
                w.append(t, "\n\n---\n\n");
                w.getCombatants()[0].firstMeeting(t, w, w.getCombatants()[1]);
                p.removeAll();
                JButton Continue = new JButton("Continue");
                Continue.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        w.append(t, "\n\n---\n\n");
                        w.getCombatants()[1].printIntro(t, w);
                        JButton Continue2 = new JButton("Continue");
                        Continue2.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                Downtime(t, p, f, w);
                            }

                            final _cls21 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls21.this;
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

                    final Project this$0;
                    private final JPanel val$p;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JFrame val$f;


            
            {
                this$0 = Project.this;
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
                    w.append(t, "\n\n---\n\n");
                    w.getCast()[2].firstTrio(t, w, w.getCast()[0], w.getCast()[1]);
                    p.removeAll();
                    JButton Continue = new JButton("Continue");
                    Continue.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            p.removeAll();
                            w.append(t, "\n\n---\n\n");
                            w.getCast()[2].printIntro(t, w);
                            JButton ContinueTwo = new JButton("Continue");
                            ContinueTwo.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    Downtime(t, p, f, w);
                                }

                                final _cls22 this$1;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final WorldState val$w;

                    
                    {
                        this$1 = _cls22.this;
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

                        final Project this$0;
                        private final JPanel val$p;
                        private final WorldState val$w;
                        private final JTextPane val$t;
                        private final JFrame val$f;


            
            {
                this$0 = Project.this;
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
                if(w.getDay() == 15)
                {
                    justContinue = Boolean.valueOf(false);
                    postScene = Boolean.valueOf(true);
                    w.append(t, "\n\n---\n\n");
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
                            w.append(t, "\n\n---\n\n");
                            chosenTwo.interviewTwo(t, w, moral, innocent, confident, dignified);
                            JButton ContinueTwo = new JButton("Continue");
                            ContinueTwo.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    p.removeAll();
                                    w.append(t, "\n\n---\n\n");
                                    chosenThree.interviewThree(t, w, moral, innocent, confident, dignified);
                                    JButton ContinueThree = new JButton("Continue");
                                    ContinueThree.addActionListener(new ActionListener() {

                                        public void actionPerformed(ActionEvent e)
                                        {
                                            p.removeAll();
                                            w.append(t, "\n\n---\n\n");
                                            w.scoreSummary(t);
                                            JButton ContinueFour = new JButton("Continue");
                                            ContinueFour.addActionListener(new ActionListener() {

                                                public void actionPerformed(ActionEvent e)
                                                {
                                                    Downtime(t, p, f, w);
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

                                final _cls23 this$1;
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
                        this$1 = _cls23.this;
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

                        final Project this$0;
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
                this$0 = Project.this;
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
                } else
                if(w.getBreaks().length > 0)
                {
                    justContinue = Boolean.valueOf(false);
                    w.append(t, "\n\n---\n\n");
                    int sceneType = w.getBreaks()[0];
                    Chosen broken = null;
                    Chosen c = null;
                    if(sceneType == 0)
                    {
                        for(int i = 0; i < 3; i++)
                            if(w.getCast()[i].getMorality() > 66)
                                broken = w.getCast()[i];
                            else
                            if(w.getCast()[i].getMorality() < 34)
                                c = w.getCast()[i];

                    } else
                    if(sceneType == 1)
                    {
                        for(int i = 0; i < 3; i++)
                            if(w.getCast()[i].getInnocence() > 66)
                                broken = w.getCast()[i];
                            else
                            if(w.getCast()[i].getInnocence() < 34)
                                c = w.getCast()[i];

                    } else
                    if(sceneType == 2)
                    {
                        for(int i = 0; i < 3; i++)
                            if(w.getCast()[i].getConfidence() > 66)
                                broken = w.getCast()[i];
                            else
                            if(w.getCast()[i].getConfidence() < 34)
                                c = w.getCast()[i];

                    } else
                    if(sceneType == 3)
                    {
                        for(int i = 0; i < 3; i++)
                            if(w.getCast()[i].getDignity() > 66)
                                broken = w.getCast()[i];
                            else
                            if(w.getCast()[i].getDignity() < 34)
                                c = w.getCast()[i];

                    }
                    broken.breakScene(t, w, c, sceneType);
                }
        if(w.isTutorial().booleanValue())
        {
            p.removeAll();
            JButton Continue = new JButton("Continue");
            Continue.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, "\n\n---\n\n");
                    WorldState x = new WorldState();
                    IntroOne(t, p, f, x);
                }

                final Project this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;

            
            {
                this$0 = Project.this;
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
                    Downtime(t, p, f, w);
                }

                final Project this$0;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;

            
            {
                this$0 = Project.this;
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

    public void Downtime(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        w.append(t, "\n\n---\n\n");
        w.incrementDay();
        w.clearCommander();
        int lastChosen = 0;
        int totalActions = 9;
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
                actionWeights[i][5] = (w.getCast()[i].getTotalFEAR() * 200L + w.getCast()[i].getTotalPAIN() * 100L + w.getCast()[i].getANGST() * 20L) / (long)(100 + w.getCast()[i].getMorality()) - 60000L;
            else
                actionWeights[i][5] = 0L;
            if(w.getCast()[i].isLustful())
                actionWeights[i][6] = (w.getCast()[i].getTotalDISG() * 200L + w.getCast()[i].getTotalFEAR() * 100L + w.getCast()[i].getANGST() * 20L) / (long)(100 + w.getCast()[i].getMorality()) - 60000L;
            else
                actionWeights[i][6] = 0L;
            if(w.getCast()[i].isMeek())
                actionWeights[i][7] = (w.getCast()[i].getTotalPAIN() * 200L + w.getCast()[i].getTotalSHAM() * 100L + w.getCast()[i].getANGST() * 20L) / (long)(100 + w.getCast()[i].getMorality()) - 60000L;
            else
                actionWeights[i][7] = 0L;
            if(w.getCast()[i].isDebased())
                actionWeights[i][8] = (w.getCast()[i].getTotalSHAM() * 200L + w.getCast()[i].getTotalDISG() * 100L + w.getCast()[i].getANGST() * 20L) / (long)(100 + w.getCast()[i].getMorality()) - 60000L;
            else
                actionWeights[i][8] = 0L;
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
                        w.append(t, "\n\n---\n\n");
                        w.getCast()[2].SingleDowntime(t, p, f, w, chosenAction[2]);
                        p.removeAll();
                        JButton Continue2 = new JButton("Continue");
                        Continue2.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                t.setText("");
                                Shop(t, p, f, w);
                            }

                            final _cls26 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls26.this;
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

                    final Project this$0;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final int val$chosenAction[];


            
            {
                this$0 = Project.this;
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
                    w.append(t, "\n\n---\n\n");
                    w.getCast()[1].SingleDowntime(t, p, f, w, chosenAction[1]);
                    p.removeAll();
                    JButton Continue2 = new JButton("Continue");
                    Continue2.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            t.setText("");
                            Shop(t, p, f, w);
                        }

                        final _cls27 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;

                    
                    {
                        this$1 = _cls27.this;
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

                final Project this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final int val$chosenAction[];


            
            {
                this$0 = Project.this;
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
                    w.append(t, "\n\n---\n\n");
                    w.getCast()[1].DoubleDowntime(t, p, f, w, w.getCast()[2], chosenAction[1]);
                    p.removeAll();
                    JButton Continue2 = new JButton("Continue");
                    Continue2.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            t.setText("");
                            Shop(t, p, f, w);
                        }

                        final _cls28 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;

                    
                    {
                        this$1 = _cls28.this;
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

                final Project this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final int val$chosenAction[];


            
            {
                this$0 = Project.this;
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
                        w.append(t, "\n\n---\n\n");
                        w.getCast()[1].SingleDowntime(t, p, f, w, chosenAction[1]);
                        if(w.getCast()[2] != null)
                        {
                            p.removeAll();
                            JButton Continue2 = new JButton("Continue");
                            Continue2.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.append(t, "\n\n---\n\n");
                                    w.getCast()[2].SingleDowntime(t, p, f, w, chosenAction[2]);
                                    p.removeAll();
                                    JButton Continue3 = new JButton("Continue");
                                    Continue3.addActionListener(new ActionListener() {

                                        public void actionPerformed(ActionEvent e)
                                        {
                                            t.setText("");
                                            Shop(t, p, f, w);
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

                                final _cls29 this$1;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final int val$chosenAction[];


                    
                    {
                        this$1 = _cls29.this;
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
                                    t.setText("");
                                    Shop(t, p, f, w);
                                }

                                final _cls29 this$1;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;
                                private final WorldState val$w;

                    
                    {
                        this$1 = _cls29.this;
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

                    final Project this$0;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final int val$chosenAction[];


            
            {
                this$0 = Project.this;
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
                    t.setText("");
                    Shop(t, p, f, w);
                }

                final Project this$0;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;

            
            {
                this$0 = Project.this;
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

    public void Shop(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        p.removeAll();
        if(t.getText().length() > 0)
            w.append(t, "\n\n---\n\n");
        w.append(t, (new StringBuilder("Day ")).append(w.getDay()).toString());
        w.printShopTutorial(t);
        if(w.getCast()[1] != null)
            w.printGroupTutorial(t);
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
                                w.append(t, (new StringBuilder("\n\n---\n\n")).append(w.getTechs()[thisTech].getName()).append(" costs ").append(w.getTechs()[thisTech].getCost()).append(" Evil Energy.  Will you develop it now?").toString());
                                JButton Confirm = new JButton("Confirm");
                                Confirm.addActionListener(new ActionListener() {

                                    public void actionPerformed(ActionEvent e)
                                    {
                                        w.getTechs()[thisTech].buy(w);
                                        Shop(t, p, f, w);
                                    }

                                    final _cls31 this$1;
                                    private final WorldState val$w;
                                    private final int val$thisTech;
                                    private final JTextPane val$t;
                                    private final JPanel val$p;
                                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls31.this;
                        w = worldstate;
                        thisTech = i;
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
                                        Shop(t, p, f, w);
                                    }

                                    final _cls31 this$1;
                                    private final JTextPane val$t;
                                    private final JPanel val$p;
                                    private final JFrame val$f;
                                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls31.this;
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

                            final Project this$0;
                            private final JPanel val$p;
                            private final WorldState val$w;
                            private final JTextPane val$t;
                            private final int val$thisTech;
                            private final JFrame val$f;


            
            {
                this$0 = Project.this;
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
                    w.append(t, "\n\n---\n\nWhose profile will you review?");
                    for(int i = 0; i < 3; i++)
                        if(w.getCast()[i] != null)
                        {
                            final int thisChosen = i;
                            JButton openProfile = new JButton(w.getCast()[i].getMainName());
                            openProfile.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    p.removeAll();
                                    w.append(t, "\n\n---\n\n");
                                    w.getCast()[thisChosen].printIntro(t, w);
                                    w.getCast()[thisChosen].printProfile(t, p, f, w);
                                    JButton Continue = new JButton("Continue");
                                    Continue.addActionListener(new ActionListener() {

                                        public void actionPerformed(ActionEvent e)
                                        {
                                            Shop(t, p, f, w);
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

                                final _cls32 this$1;
                                private final JPanel val$p;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final int val$thisChosen;
                                private final JFrame val$f;


                    
                    {
                        this$1 = _cls32.this;
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
                            Shop(t, p, f, w);
                        }

                        final _cls32 this$1;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final WorldState val$w;

                    
                    {
                        this$1 = _cls32.this;
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

                final Project this$0;
                private final JPanel val$p;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JFrame val$f;


            
            {
                this$0 = Project.this;
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
                    Customize(t, p, f, w);
                }

                final Project this$0;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;

            
            {
                this$0 = Project.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
            });
            p.add(CustomBody);
        }
        JButton Data = new JButton("Data");
        Data.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.append(t, "\n\n---\n\nSelect an option.");
                p.removeAll();
                JButton NewSave = new JButton("New Save File");
                NewSave.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Data(t, p, f, w, "newsave", 0, Boolean.valueOf(true));
                    }

                    final _cls34 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls34.this;
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
                        Data(t, p, f, w, "overwrite", 0, Boolean.valueOf(true));
                    }

                    final _cls34 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls34.this;
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
                        Data(t, p, f, w, "load", 0, Boolean.valueOf(true));
                    }

                    final _cls34 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls34.this;
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
                        Data(t, p, f, w, "delete", 0, Boolean.valueOf(true));
                    }

                    final _cls34 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls34.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        super();
                    }
                });
                p.add(Delete);
                JButton Back = new JButton("Back");
                Back.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Shop(t, p, f, w);
                    }

                    final _cls34 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls34.this;
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

            final Project this$0;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;


            
            {
                this$0 = Project.this;
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
                w.append(t, "\n\n---\n\nReally quit?  Current progress will not be saved.");
                p.removeAll();
                JButton ReallyQuit = new JButton("Quit to main menu");
                ReallyQuit.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, "\n\n---\n\n");
                        IntroOne(t, p, f, new WorldState());
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
                p.add(ReallyQuit);
                JButton Back = new JButton("Back");
                Back.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        Shop(t, p, f, w);
                    }

                    final _cls35 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls35.this;
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

            final Project this$0;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;


            
            {
                this$0 = Project.this;
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
                pickStartingTarget(t, p, f, w);
            }

            final Project this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                this$0 = Project.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                super();
            }
        });
        p.add(NextBattle);
        p.validate();
        p.repaint();
    }

    public void Data(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final String function, final int page, final Boolean toShop)
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
                w.append(t, (new StringBuilder("\n\n---\n\nReally overwrite \"")).append(fullSaveName).append("\"?").toString());
                p.removeAll();
                JButton Confirm = new JButton("Confirm");
                Confirm.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        saveFile.overwriteSave(w);
                        wobj.serializeSaveData(saveFile);
                        w.append(t, "  Done.");
                        Shop(t, p, f, w);
                    }

                    final Project this$0;
                    private final SaveData val$saveFile;
                    private final WorldState val$w;
                    private final WriteObject val$wobj;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                this$0 = Project.this;
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
                        Shop(t, p, f, w);
                    }

                    final Project this$0;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                this$0 = Project.this;
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
        {
            int i = page * 4;
            int j = 0;
            w.append(t, "\n\n---\n\n");
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
                        Data(t, p, f, w, function, page - 1, toShop);
                    }

                    final Project this$0;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final String val$function;
                    private final int val$page;
                    private final Boolean val$toShop;

            
            {
                this$0 = Project.this;
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
                    fullSaveName = (new StringBuilder(String.valueOf(fullSaveName))).append(" (HS ").append(saves.getSaves()[i].getCast()[0].condensedFormat(saves.getSaves()[i].getHighScore())).append(")").toString();
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
                                w.append(t, (new StringBuilder("\n\n---\n\nReally load \"")).append(thisSaveName).append("\"?  Your current progress won't be saved.").toString());
                                JButton Confirm = new JButton("Confirm");
                                Confirm.addActionListener(new ActionListener() {

                                    public void actionPerformed(ActionEvent e)
                                    {
                                        wobj.serializeSaveData(saveFile);
                                        saveFile.moveToFront(fileSelected);
                                        wobj.serializeSaveData(saveFile);
                                        if(saveFile.getSaves()[0].getDay() == 1)
                                            IntroTwo(t, p, f, saveFile.getSaves()[0]);
                                        else
                                            Shop(t, p, f, saveFile.getSaves()[0]);
                                    }

                                    final _cls40 this$1;
                                    private final WriteObject val$wobj;
                                    private final SaveData val$saveFile;
                                    private final int val$fileSelected;
                                    private final JTextPane val$t;
                                    private final JPanel val$p;
                                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls40.this;
                        wobj = writeobject;
                        saveFile = savedata;
                        fileSelected = i;
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
                                        Shop(t, p, f, w);
                                    }

                                    final _cls40 this$1;
                                    private final JTextPane val$t;
                                    private final JPanel val$p;
                                    private final JFrame val$f;
                                    private final WorldState val$w;

                    
                    {
                        this$1 = _cls40.this;
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
                                saveFile.moveToFront(fileSelected);
                                wobj.serializeSaveData(saveFile);
                                Shop(t, p, f, saveFile.getSaves()[0]);
                            }
                        } else
                        {
                            p.removeAll();
                            w.append(t, (new StringBuilder("\n\n---\n\nReally delete \"")).append(thisSaveName).append("\"?").toString());
                            JButton Confirm = new JButton("Confirm");
                            Confirm.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    saveFile.deleteSave(fileSelected);
                                    wobj.serializeSaveData(saveFile);
                                    w.append(t, "  Done.");
                                    Shop(t, p, f, w);
                                }

                                final _cls40 this$1;
                                private final SaveData val$saveFile;
                                private final int val$fileSelected;
                                private final WriteObject val$wobj;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls40.this;
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
                                    Shop(t, p, f, w);
                                }

                                final _cls40 this$1;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls40.this;
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

                    final Project this$0;
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
                this$0 = Project.this;
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
                        Data(t, p, f, w, function, page + 1, toShop);
                    }

                    final Project this$0;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final String val$function;
                    private final int val$page;
                    private final Boolean val$toShop;

            
            {
                this$0 = Project.this;
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
                        Shop(t, p, f, w);
                    } else
                    {
                        w.append(t, "\n\n---\n\n");
                        IntroOne(t, p, f, w);
                    }
                }

                final Project this$0;
                private final Boolean val$toShop;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;

            
            {
                this$0 = Project.this;
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

    public void Customize(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        p.removeAll();
        w.append(t, "\n\n---\n\n");
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
                        Customize(t, p, f, w);
                    }

                    final Project this$0;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                this$0 = Project.this;
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
                    Shop(t, p, f, w);
                }

                final Project this$0;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;

            
            {
                this$0 = Project.this;
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
            if((w.getTechs()[10].isOwned().booleanValue() || w.getTechs()[11].isOwned().booleanValue() || w.getTechs()[12].isOwned().booleanValue() || w.getTechs()[13].isOwned().booleanValue()) && !w.getBodyStatus()[3].booleanValue() && !w.getBodyStatus()[4].booleanValue() && !w.getBodyStatus()[5].booleanValue() && !w.getBodyStatus()[6].booleanValue())
            {
                JButton Suppressor = new JButton("Suppressor Upgrades");
                Suppressor.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        if(w.getTechs()[10].isOwned().booleanValue() && !w.getBodyStatus()[3].booleanValue() && !w.getBodyStatus()[4].booleanValue() && !w.getBodyStatus()[5].booleanValue() && !w.getBodyStatus()[6].booleanValue())
                        {
                            JButton Hunger = new JButton("Hunger");
                            Hunger.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyHunger();
                                    Customize(t, p, f, w);
                                }

                                final _cls45 this$1;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls45.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(Hunger);
                        }
                        if(w.getTechs()[11].isOwned().booleanValue() && !w.getBodyStatus()[4].booleanValue() && !w.getBodyStatus()[3].booleanValue() && !w.getBodyStatus()[5].booleanValue() && !w.getBodyStatus()[6].booleanValue())
                        {
                            JButton Lust = new JButton("Lust");
                            Lust.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyLust();
                                    Customize(t, p, f, w);
                                }

                                final _cls45 this$1;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls45.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(Lust);
                        }
                        if(w.getTechs()[12].isOwned().booleanValue() && !w.getBodyStatus()[5].booleanValue() && !w.getBodyStatus()[3].booleanValue() && !w.getBodyStatus()[4].booleanValue() && !w.getBodyStatus()[6].booleanValue())
                        {
                            JButton Anger = new JButton("Anger");
                            Anger.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyAnger();
                                    Customize(t, p, f, w);
                                }

                                final _cls45 this$1;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls45.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                            });
                            p.add(Anger);
                        }
                        if(w.getTechs()[13].isOwned().booleanValue() && !w.getBodyStatus()[6].booleanValue() && !w.getBodyStatus()[3].booleanValue() && !w.getBodyStatus()[4].booleanValue() && !w.getBodyStatus()[5].booleanValue())
                        {
                            JButton Mania = new JButton("Mania");
                            Mania.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    w.applyMania();
                                    Customize(t, p, f, w);
                                }

                                final _cls45 this$1;
                                private final WorldState val$w;
                                private final JTextPane val$t;
                                private final JPanel val$p;
                                private final JFrame val$f;

                    
                    {
                        this$1 = _cls45.this;
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
                                Customize(t, p, f, w);
                            }

                            final _cls45 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;

                    
                    {
                        this$1 = _cls45.this;
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
                        w.append(t, "\n\n---\n\nWhich upgrade will you apply?");
                    }

                    final Project this$0;
                    private final JPanel val$p;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JFrame val$f;


            
            {
                this$0 = Project.this;
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                super();
            }
                });
                p.add(Suppressor);
            }
            if(w.getTechs()[8].isOwned().booleanValue() && !w.getBodyStatus()[1].booleanValue() && w.getEvilEnergy() >= 1)
            {
                JButton Enhance = new JButton("Enhance Duration (1)");
                Enhance.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.enhanceOne();
                        Customize(t, p, f, w);
                    }

                    final Project this$0;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                this$0 = Project.this;
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
                        Customize(t, p, f, w);
                    }

                    final Project this$0;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                this$0 = Project.this;
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
                        Customize(t, p, f, w);
                    }

                    final Project this$0;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                this$0 = Project.this;
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
                        Customize(t, p, f, w);
                    }

                    final Project this$0;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

            
            {
                this$0 = Project.this;
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
                    Shop(t, p, f, w);
                }

                final Project this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;

            
            {
                this$0 = Project.this;
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
                    Shop(t, p, f, w);
                }

                final Project this$0;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;

            
            {
                this$0 = Project.this;
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

    public void pickStartingTarget(final JTextPane t, final JPanel p, final JFrame f, final WorldState w)
    {
        p.removeAll();
        w.append(t, "\n\n---\n\nWhich of the Chosen will you target?");
        for(int i = 0; i < w.getCast().length; i++)
            if(w.getCast()[i] != null)
            {
                final int thisChosen = i;
                JButton thisOne = new JButton(w.getCast()[i].getMainName());
                thisOne.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        ConfirmBattle(t, p, f, w, w.getCast()[thisChosen]);
                    }

                    final Project this$0;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final int val$thisChosen;

            
            {
                this$0 = Project.this;
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
                Shop(t, p, f, w);
            }

            final Project this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                this$0 = Project.this;
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

    public void ConfirmBattle(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final Chosen c)
    {
        p.removeAll();
        w.append(t, "\n\n---\n\n");
        if(w.getBodyStatus()[0].booleanValue())
            w.printCommanderSummary(t, c);
        else
            w.append(t, (new StringBuilder("You will invade a neighborhood along ")).append(c.getMainName()).append("'s patrol path in order to lure ").append(c.himHer()).append(" in and attack ").append(c.himHer()).append(".").toString());
        w.append(t, "  Is this okay?");
        JButton Confirm = new JButton("Confirm");
        Confirm.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                BeginBattle(t, p, f, w, c);
            }

            final Project this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Chosen val$c;

            
            {
                this$0 = Project.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                c = chosen;
                super();
            }
        });
        p.add(Confirm);
        JButton Cancel = new JButton("Cancel");
        Cancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Shop(t, p, f, w);
            }

            final Project this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;

            
            {
                this$0 = Project.this;
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

    public void BeginBattle(JTextPane t, JPanel p, JFrame f, WorldState w, Chosen c)
    {
        w.incrementTotalRounds();
        w.append(t, "\n\n---\n\n");
        Chosen newCombatants[] = new Chosen[3];
        newCombatants[0] = c;
        w.newCombat(w, newCombatants);
        if(w.getBodyStatus()[0].booleanValue() && !w.getBodyStatus()[2].booleanValue())
        {
            w.append(t, (new StringBuilder("You lure ")).append(c.getMainName()).append(" into battle with an attack on a neighborhood along ").append(c.hisHer()).append(" patrol route.  ").toString());
            if(w.getBodyStatus()[3].booleanValue() || w.getBodyStatus()[4].booleanValue() || w.getBodyStatus()[5].booleanValue() || w.getBodyStatus()[6].booleanValue())
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
