
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.*;

public class Body
    implements Serializable
{
    public enum Appearance {
        CUTEGIRL,
        CUTEBOY,
        MONSTER
    }

    public long[] currentDamage()
    {
        return (new long[] {
            currentFEAR, currentDISG, currentPAIN, currentSHAM, currentHATE, currentPLEA, currentINJU, currentEXPO
        });
    }

    public long[] InflictDamage(long incoming[])
    {
        for(int i = 0; i < 8; i++)
        {
            Boolean overflow = false;
            if(i == 0 && incoming[i] > 0L)
            {
                for(long magnitude = currentFEAR; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] * 3L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                for(long magnitude = currentPAIN; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] * 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                incoming[i] = (incoming[i] * (long)(150 - getConfidence()) * (long)(100 + getObedience())) / 15000L;
                for(long magnitude = currentINJU; magnitude >= 1000L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] / 4L;
                }

            } else
            if(i == 1 && incoming[i] > 0L)
            {
                for(long magnitude = currentDISG; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] * 5L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                for(long magnitude = currentHATE; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] * 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                incoming[i] = (incoming[i] * (long)(200 + getInnocence()) * (long)(100 - getDeviancy())) / 12500L;
            } else
            if(i == 2 && incoming[i] > 0L)
            {
                for(long magnitude = currentPAIN; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] * 4L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                for(long magnitude = currentINJU; magnitude >= 1000L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] / 2L;
                }

            } else
            if(i == 3 && incoming[i] > 0L)
            {
                for(long magnitude = currentSHAM; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] * 3L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                for(long magnitude = currentEXPO; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 3L) / 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                incoming[i] = (incoming[i] * (long)(150 - getDignity())) / 100L;
                for(long magnitude = currentINJU; magnitude >= 1000L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] / 2L;
                }

            } else
            if(i == 4 && incoming[i] > 0L)
            {
                long magnitude;
                for(magnitude = currentHATE; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] * 5L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                for(magnitude = currentDISG; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 3L) / 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                for(magnitude = currentPAIN; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 3L) / 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                for(magnitude = currentSHAM; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 3L) / 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                for(magnitude = currentINJU; magnitude >= 1000L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] * 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                magnitude = getObedience();
                for(incoming[i] = (incoming[i] * (long)(150 - getMorality()) * (long)(100 + getHostility()) * (long)(100 - getObedience())) / 750_000L; magnitude > 60L; incoming[i] = (incoming[i] * 9L) / 10L)
                    magnitude--;

                for(magnitude = currentINJU; magnitude >= 1000L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] / 3L;
                }

            } else
            if(i == 5 && incoming[i] > 0L)
            {
                for(long magnitude = currentPLEA; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] * 3L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                for(long magnitude = currentSHAM; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * (long)(50 + getDeviancy())) / 50L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                if(!isDemonLord())
                    incoming[i] = (incoming[i] * (long)(50 + getDeviancy())) / 100L;
                for(long magnitude = currentDISG; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 2L) / 3L;
                }

                for(long magnitude = currentPAIN; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * (long)(75 + getDeviancy())) / 175L;
                }

                for(long magnitude = currentINJU; magnitude >= 1000L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] / 4L;
                }

            } else
            if(i == 6 && incoming[i] > 0L)
            {
                for(long magnitude = currentINJU; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] * 5L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                for(long magnitude = currentFEAR; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 4L) / 3L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                for(long magnitude = currentDISG; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 4L) / 3L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                for(long magnitude = currentPAIN; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 3L) / 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                for(long magnitude = currentSHAM; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 4L) / 3L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                for(long magnitude = currentHATE; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 4L) / 3L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                for(long magnitude = currentPLEA; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 3L) / 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

            } else
            if(i == 7 && incoming[i] > 0L)
            {
                for(long magnitude = currentEXPO; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] * 10L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

            }
            if(overflow)
                incoming[i] = 0L;
        }

        return incoming;
    }

    public void advanceAction(JTextPane t, JPanel p, JFrame f, WorldState w, SaveData s)
    {
        if(this == w.sceneParticipants[0])
            w.sceneDuration++;
        int nextActor = 0;
        for(int i = 0; i < w.sceneParticipants.length - 1; i++)
            if(w.sceneParticipants[i] == this)
                nextActor = i + 1;

        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
        if(w.sceneParticipants[nextActor] == w.lordBody)
        {
            w.append(t, "\n\n");
            w.sceneParticipants[nextActor].LordDamage(t, w);
        }
        w.sceneParticipants[nextActor].PickActivity(t, p, f, w, s);
    }

    public void Continue(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s)
    {
        p.removeAll();
        JButton Wait = new JButton("Continue");
        Wait.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                advanceAction(t, p, f, w, s);
            }
        });
        if(inProgress.length == 0 && this == w.lordBody)
            Wait.setText("Wait");
        p.add(Wait);
        p.validate();
        p.repaint();
    }

    public void InitializeActivities()
    {
        Project.Talk.pickable = false;
        Project.Talk.sendReqs[MOUTH] = 1;
        Project.TweakClit.counterpart = Project.ClitTweaked;
        Project.TweakClit.sendReqs[HAND] = 1;
        Project.TweakClit.targeted = true;
        Project.TweakClit.enders = (new Activity[] {
            Project.StopActing, Project.Escape
        });
        Project.ClitTweaked.counterpart = Project.TweakClit;
        Project.ClitTweaked.sendReqs[CLIT] = 1;
        Project.ClitTweaked.causesOrgasm = true;
        Project.ClitTweaked.targeted = true;
        Project.ClitTweaked.pickable = false;
        Project.ClitTweaked.enders = (new Activity[] {
            Project.Escape
        });
        Project.SpreadLegs.sendReqs[FOOT] = 2;
        Project.SpreadLegs.enders = (new Activity[] {
            Project.StopActing, Project.Escape
        });
        Project.SpreadLegs.pickable = false;
        Project.Praise.sendReqs[MOUTH] = 1;
        Project.Praise.targeted = true;
        Project.Praise.endsSelf = true;
        Project.Insult.sendReqs[MOUTH] = 1;
        Project.Insult.targeted = true;
        Project.Insult.endsSelf = true;
        Project.Insult.hostile = true;
        Project.PushDown.sendReqs[HIPS] = 1;
        Project.PushDown.sendReqs[FOOT] = 2;
        Project.PushDown.targeted = true;
        Project.PushDown.counterpart = Project.PullDown;
        Project.PushDown.enders = (new Activity[] {
            Project.StopActing, Project.Escape
        });
        Project.PullDown.sendReqs[HIPS] = 1;
        Project.PullDown.targeted = true;
        Project.PullDown.counterpart = Project.PushDown;
        Project.PullDown.enders = (new Activity[] {
            Project.Escape
        });
        Project.Escape.sendReqs[HAND] = 1;
        Project.Escape.sendReqs[FOOT] = 2;
        Project.Escape.targeted = true;
        Project.Escape.endsSelf = true;
        Project.Escape.pickable = false;
        Project.Escape.hostile = true;
        Project.StopActing.targeted = true;
        Project.StopActing.endsSelf = true;
        Project.StopActing.pickable = false;
        Project.StopActing.hostile = true;
        Project.TieUp.sendReqs[HAND] = 1;
        Project.TieUp.targeted = true;
        Project.TieUp.nonBlocking = true;
        Project.TieUp.counterpart = Project.BeTied;
        Project.TieUp.pickable = false;
        Project.BeTied.sendReqs[HAND] = 2;
        Project.BeTied.sendReqs[FOOT] = 2;
        Project.BeTied.targeted = true;
        Project.BeTied.counterpart = Project.TieUp;
        Project.BeTied.pickable = false;
        Project.StrokeCock.sendReqs[HAND] = 1;
        Project.StrokeCock.targeted = true;
        Project.StrokeCock.counterpart = Project.CockStroked;
        Project.StrokeCock.enders = (new Activity[] {
            Project.StopActing, Project.Escape
        });
        Project.StrokeCock.pickable = true;
        Project.CockStroked.sendReqs[PENIS] = 1;
        Project.CockStroked.causesOrgasm = true;
        Project.CockStroked.targeted = true;
        Project.CockStroked.counterpart = Project.StrokeCock;
        Project.CockStroked.enders = (new Activity[] {
            Project.Escape
        });
        Project.CockStroked.pickable = false;
        Project.Lubricate.sendReqs[HAND] = 1;
        Project.Lubricate.nonBlocking = true;
        Project.Lubricate.targeted = true;
        Project.Lubricate.counterpart = Project.BeLubricated;
        Project.Lubricate.endsSelf = true;
        Project.Lubricate.pickable = false;
        Project.BeLubricated.sendReqs[ASS] = 1;
        Project.BeLubricated.nonBlocking = true;
        Project.BeLubricated.pickable = false;
        Project.VaginalPenetrate.sendReqs[PENIS] = 1;
        Project.VaginalPenetrate.causesOrgasm = true;
        Project.VaginalPenetrate.targeted = true;
        Project.VaginalPenetrate.counterpart = Project.PenetratedVaginally;
        Project.VaginalPenetrate.enders = (new Activity[] {
            Project.StopActing, Project.Escape
        });
        Project.PenetratedVaginally.sendReqs[PUSSY] = 1;
        Project.PenetratedVaginally.causesOrgasm = true;
        Project.PenetratedVaginally.targeted = true;
        Project.PenetratedVaginally.counterpart = Project.VaginalPenetrate;
        Project.PenetratedVaginally.enders = (new Activity[] {
            Project.Escape
        });
        Project.AnalPenetrate.sendReqs[PENIS] = 1;
        Project.AnalPenetrate.causesOrgasm = true;
        Project.AnalPenetrate.targeted = true;
        Project.AnalPenetrate.counterpart = Project.PenetratedAnally;
        Project.AnalPenetrate.enders = (new Activity[] {
            Project.StopActing, Project.Escape
        });
        Project.PenetratedAnally.sendReqs[ASS] = 1;
        Project.PenetratedAnally.causesOrgasm = true;
        Project.PenetratedAnally.targeted = true;
        Project.PenetratedAnally.counterpart = Project.AnalPenetrate;
        Project.PenetratedAnally.enders = (new Activity[] {
            Project.Escape
        });
        Project.StripOther.sendReqs[HAND] = 1;
        Project.StripOther.targeted = true;
        Project.StripOther.counterpart = Project.Stripped;
        Project.StripOther.enders = (new Activity[] {
            Project.StopActing, Project.Escape
        });
        Project.StripOther.pickable = false;
        Project.Stripped.targeted = true;
        Project.Stripped.counterpart = Project.StripOther;
        Project.Stripped.enders = (new Activity[] {
            Project.Escape
        });
        Project.Stripped.pickable = false;
    }

    public Boolean orgasmPossible()
    {
        for(int i = 0; i < inProgress.length; i++)
            if(inProgress[i].causesOrgasm)
                return true;

        return false;
    }

    public void CancelActivities(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s, final int page)
    {
        p.removeAll();
        if(page > 0)
        {
            JButton Previous = new JButton("<");
            Previous.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    CancelActivities(t, p, f, w, s, page - 1);
                }
            });
            p.add(Previous);
        }
        for(int i = page * 3; i < page * 3 + 3 && i < w.lordBody.inProgress.length; i++)
        {
            JButton ThisOne = new JButton(w.lordBody.inProgress[i].activityName(w.lordBody.targets[i]));
            final int index = i;
            ThisOne.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
                    w.lordBody.inProgress[index].endActivityFlavor(t, w, w.lordBody, w.lordBody.targets[index]);
                    removeActivity(w.lordBody.inProgress[index], w.lordBody.targets[index]);
                    if(index % 3 != 0)
                        CancelActivities(t, p, f, w, s, page);
                    else
                    if(index != 0)
                        CancelActivities(t, p, f, w, s, page - 1);
                    else
                    if(w.lordBody.inProgress.length > 0)
                        CancelActivities(t, p, f, w, s, 0);
                    else
                        PickActivity(t, p, f, w, s);
                }
            });
            p.add(ThisOne);
        }

        if((page + 1) * 3 < w.lordBody.inProgress.length)
        {
            JButton Next = new JButton(">");
            Next.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    CancelActivities(t, p, f, w, s, page + 1);
                }
            });
            p.add(Next);
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
                PickActivity(t, p, f, w, s);
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public void TouchMenu(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s)
    {
        p.removeAll();
        if(Project.TweakClit.valid(this, w.targetBody))
        {
            JButton TweakClit = new JButton("Stroke Clit");
            TweakClit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.TweakClit.startActivity(t, w, w.lordBody, w.targetBody);
                    Continue(t, p, f, w, s);
                }
            });
            p.add(TweakClit);
        }
        if(Project.StrokeCock.valid(this, w.targetBody))
        {
            JButton StrokeCock = new JButton("Stroke Cock");
            StrokeCock.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.StrokeCock.startActivity(t, w, w.lordBody, w.targetBody);
                    Continue(t, p, f, w, s);
                }
            });
            p.add(StrokeCock);
        }
        if(Project.StripOther.valid(this, w.targetBody))
        {
            JButton StripOther = new JButton("Strip");
            StripOther.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.StripOther.startActivity(t, w, w.lordBody, w.targetBody);
                    Continue(t, p, f, w, s);
                }
            });
            p.add(StripOther);
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
                PickActivity(t, p, f, w, s);
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public void PositionMenu(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s)
    {
        p.removeAll();
        if(Project.PushDown.valid(this, w.targetBody))
        {
            JButton PushDown = new JButton("Push Down");
            PushDown.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.PushDown.startActivity(t, w, w.lordBody, w.targetBody);
                    w.append(t, "\n\n");
                    Project.PullDown.activityTalk(t, w, w.targetBody, w.lordBody);
                    Continue(t, p, f, w, s);
                }
            });
            p.add(PushDown);
        }
        if(Project.PullDown.valid(this, w.targetBody))
        {
            JButton PullDown = new JButton("Pull Down");
            PullDown.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.PullDown.startActivity(t, w, w.lordBody, w.targetBody);
                    w.append(t, "\n\n");
                    Project.PushDown.activityTalk(t, w, w.targetBody, w.lordBody);
                    Continue(t, p, f, w, s);
                }
            });
            p.add(PullDown);
        }
        if(!Project.TieUp.isInProgress(this, w.targetBody))
        {
            JButton TieUp = new JButton("Tie Up");
            TieUp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.TieUp.startActivity(t, w, w.lordBody, w.targetBody);
                    Continue(t, p, f, w, s);
                }
            });
            p.add(TieUp);
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
                PickActivity(t, p, f, w, s);
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public void SexMenu(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s)
    {
        p.removeAll();
        if(Project.VaginalPenetrate.valid(w.lordBody, w.targetBody))
            if(Project.PenetratedVaginally.weight(w, w.targetBody, w.lordBody) >= 0 || w.targetBody.getHATELevel() >= 3)
            {
                JButton Missionary = new JButton("Missionary Vaginal");
                Missionary.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        Project.VaginalPenetrate.startActivity(t, w, w.lordBody, w.targetBody);
                        w.append(t, "\n\n");
                        Project.PenetratedVaginally.activityTalk(t, w, w.targetBody, w.lordBody);
                        Continue(t, p, f, w, s);
                    }
                });
                p.add(Missionary);
            } else
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").append(w.targetBody.OwnerName()).append("'s Sexual Barrier prevents vaginal penetration.").toString());
            }
        if(Project.PenetratedVaginally.valid(w.lordBody, w.targetBody))
        {
            JButton Cowgirl = new JButton("Cowgirl Vaginal");
            Cowgirl.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.PenetratedVaginally.startActivity(t, w, w.lordBody, w.targetBody);
                    w.append(t, "\n\n");
                    Project.VaginalPenetrate.activityTalk(t, w, w.targetBody, w.lordBody);
                    Continue(t, p, f, w, s);
                }
            });
            p.add(Cowgirl);
        }
        if(Project.AnalPenetrate.valid(w.lordBody, w.targetBody))
            if(Project.PenetratedAnally.weight(w, w.targetBody, w.lordBody) >= 0 || w.targetBody.getHATELevel() >= 3 || w.targetBody.parts[PUSSY] > 0)
            {
                JButton Missionary = new JButton("Missionary Anal");
                if(!Project.BeLubricated.isInProgress(w.targetBody, null))
                    Missionary.setText("Unlubricated Missionary Anal");
                Missionary.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        Project.AnalPenetrate.startActivity(t, w, w.lordBody, w.targetBody);
                        w.append(t, "\n\n");
                        Project.PenetratedAnally.activityTalk(t, w, w.targetBody, w.lordBody);
                        Continue(t, p, f, w, s);
                    }
                });
                p.add(Missionary);
            } else
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").append(w.targetBody.OwnerName()).append("'s Sexual Barrier prevents anal penetration.").toString());
            }
        if(Project.PenetratedAnally.valid(w.lordBody, w.targetBody))
        {
            JButton Cowgirl = new JButton("Cowgirl Anal");
            if(appearanceGender(bodyType) == Forsaken.Gender.MALE)
                Cowgirl.setText("Cowgirl (Male) Anal");
            Cowgirl.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.PenetratedAnally.startActivity(t, w, w.lordBody, w.targetBody);
                    w.append(t, "\n\n");
                    Project.AnalPenetrate.activityTalk(t, w, w.targetBody, w.lordBody);
                    Continue(t, p, f, w, s);
                }
            });
            p.add(Cowgirl);
        }
        if(Project.Lubricate.valid(w.lordBody, w.targetBody))
        {
            JButton Lubricate = new JButton("Lubricate Anus");
            Lubricate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.Lubricate.startActivity(t, w, w.lordBody, w.targetBody);
                    Continue(t, p, f, w, s);
                }
            });
            p.add(Lubricate);
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
                PickActivity(t, p, f, w, s);
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public void PickActivity(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s)
    {
        for(int i = 0; i < inProgress.length; i++)
            if(inProgress[i].endsSelf)
                removeActivity(inProgress[i], targets[i]);

        w.append(t, "\n\n");
        if(w.sceneDuration == 0)
        {
            w.append(t, (new StringBuilder(String.valueOf(w.getSeparator()))).append("\n\n").toString());
            InitializeActivities();
        }
        if(this == w.lordBody)
        {
            w.targetBody = w.sceneParticipants[1];
            p.removeAll();
            if(w.sceneDuration == 0)
            {
                w.sceneDuration++;
                if(w.sceneLocation == Activity.Location.CHAMBER)
                {
                    if(w.targetBody.getObedience() > 66)
                    {
                        w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" is waiting eagerly in ").append(w.targetBody.hisHer()).append(" room when ").append(w.lordBody.ownerName()).append(" arrives, ").toString());
                        if(w.targetBody.getDeviancy() > 66)
                        {
                            w.append(t, (new StringBuilder("already naked and unable to stop ")).append(w.targetBody.himHer()).append("self from masturbating as ").append(w.targetBody.heShe()).append(" anticipates what's to come.  When ").append(w.targetBody.heShe()).append(" sees ").append(w.lordBody.ownerName()).append(", ").append(w.targetBody.heShe()).append(" scrambles to ").append(w.targetBody.hisHer()).append(" feet, panting with unrestrained desire.").toString());
                            w.targetBody.currentEXPO = 1_000_000L;
                        } else
                        if(w.targetBody.getDeviancy() > 33)
                        {
                            w.append(t, (new StringBuilder("wearing nothing but a thin robe which ")).append(w.targetBody.heShe()).append(" quickly drops to the floor as ").append(w.targetBody.heShe()).append(" welcomes ").append(w.lordBody.himHer()).append(".  An eager smile is on ").append(w.targetBody.ownerName()).append("'s face, and ").append(w.targetBody.hisHer()).append(" cheeks are flushed with arousal.").toString());
                            w.targetBody.currentEXPO = 1_000_000L;
                        } else
                        {
                            w.append(t, (new StringBuilder("shifting nervously from foot to foot.  ")).append(w.targetBody.HeShe()).append(" knows roughly what will be expected of ").append(w.targetBody.himHer()).append(", but ").append(w.targetBody.heShe()).append("'s unsure whether ").append(w.targetBody.heShe()).append("'ll be able to perform.").toString());
                        }
                        w.targetBody.currentPLEA = (w.targetBody.getObedience() * w.targetBody.getDeviancy()) / 10;
                    } else
                    if(w.targetBody.getObedience() > 33)
                    {
                        if(w.targetBody.getDeviancy() > 66)
                        {
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.capitalizedOwnerName()))).append(" is masturbating when ").append(w.lordBody.ownerName()).append(" arrives, and ").append(w.targetBody.heShe()).append(" looks almost annoyed at being interrupted.  But as ").append(w.targetBody.heShe()).append(" remembers the possibilities offered by a partner, ").append(w.targetBody.heShe()).append(" cheers up and sheds ").append(w.targetBody.hisHer()).append(" clothes, offering ").append(w.targetBody.himHer()).append("self to ").append(w.lordBody.ownerName()).append(".").toString());
                            w.targetBody.currentPLEA = (w.targetBody.getDeviancy() * w.targetBody.getDeviancy()) / 20;
                            w.targetBody.currentEXPO = 1_000_000L;
                        } else
                        if(w.targetBody.getDeviancy() > 33)
                        {
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.capitalizedOwnerName()))).append(" welcomes ").append(w.lordBody.ownerName()).append(" into ").append(w.targetBody.hisHer()).append(" room, then stands obediently at attention.  Only the slight flush in ").append(w.targetBody.hisHer()).append(" cheeks betrays the fact that ").append(w.targetBody.heShe()).append(" might be hoping for this encounter to turn intimate.").toString());
                            w.targetBody.currentPLEA = (w.targetBody.getDeviancy() * w.targetBody.getDeviancy()) / 100;
                        } else
                        {
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" reluctantly allows ").append(w.lordBody.ownerName()).append(" into ").append(w.targetBody.hisHer()).append(" room.  ").append(w.targetBody.HeShe()).append("'s clearly resigned to what ").append(w.targetBody.heShe()).append("'s going to be forced to do.").toString());
                            w.targetBody.currentFEAR = 100 - w.targetBody.getConfidence();
                        }
                        w.targetBody.currentHATE = ((100 - w.targetBody.getDeviancy()) * (100 - w.targetBody.getObedience())) / 100;
                    } else
                    {
                        if(w.targetBody.getDeviancy() > 66)
                        {
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" is trembling with arousal as ").append(w.lordBody.ownerName()).append(" enters ").append(w.targetBody.hisHer()).append(" room, but ").append(w.targetBody.heShe()).append(" still tries to glare at ").append(w.lordBody.himHer()).append(", denying how turned on ").append(w.targetBody.heShe()).append(" is at being at the mercy of ").append(w.targetBody.hisHer()).append(" hated enemy.").toString());
                            w.targetBody.currentPLEA = (w.targetBody.getDeviancy() * w.targetBody.getDeviancy()) / 40;
                        } else
                        if(w.targetBody.getDeviancy() > 33)
                        {
                            w.append(t, (new StringBuilder("When ")).append(w.lordBody.ownerName()).append(" enters ").append(w.targetBody.hisHer()).append(" room, ").append(w.targetBody.ownerName()).append(" dares ").append(w.lordBody.himHer()).append(" to do ").append(w.lordBody.hisHer()).append(" worst.  The growing perverted side of ").append(w.targetBody.ownerName()).append(" is secretly looking forward to it.").toString());
                            w.targetBody.currentPLEA = (w.targetBody.getDeviancy() * w.targetBody.getObedience()) / 40;
                        } else
                        {
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" waits in ").append(w.targetBody.hisHer()).append(" room with ").append(w.targetBody.hisHer()).append(" arms crossed.  ").append(w.targetBody.HeShe()).append(" doesn't even acknowledge ").append(w.lordBody.ownerName()).append(" entering the room.").toString());
                        }
                        w.targetBody.currentFEAR = 100 - w.targetBody.getConfidence();
                        w.targetBody.currentHATE = ((100 - w.targetBody.getObedience()) * (100 - w.targetBody.getObedience())) / 50;
                    }
                } else
                if(w.sceneLocation == Activity.Location.STAGE)
                {
                    if(w.targetBody.getObedience() > 66)
                    {
                        if(w.targetBody.getDeviancy() > 66)
                        {
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" is already trembling with arousal as ").append(w.targetBody.heShe()).append(" joins ").append(w.lordBody.ownerName()).append(" on stage in front of a cheering crowd of Thralls, ").toString());
                            if(w.targetBody.parts[PENIS] > 0)
                                w.append(t, (new StringBuilder("struggling with the urge to undress and start stroking ")).append(w.targetBody.hisHer()).append(" straining penis right there.").toString());
                            else
                                w.append(t, (new StringBuilder(String.valueOf(w.targetBody.hisHer()))).append(" wetness dripping down ").append(w.targetBody.hisHer()).append(" thighs.").toString());
                            w.append(t, (new StringBuilder("  The prospect of being used right there in public is incredibly arousing to ")).append(w.targetBody.himHer()).append(".").toString());
                        } else
                        if(w.targetBody.getDeviancy() > 33)
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" waves happily to the surrounding Thralls as ").append(w.targetBody.heShe()).append(" joins ").append(w.lordBody.ownerName()).append(" on the stage.  ").append(w.targetBody.HeShe()).append("'s proud to have the chance to show everyone how deeply ").append(w.targetBody.heShe()).append(" belongs to ").append(w.lordBody.ownerName()).append(".").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" looks nervous as ").append(w.targetBody.heShe()).append(" makes ").append(w.targetBody.hisHer()).append(" way through the Thralls in order to take the stage with ").append(w.lordBody.ownerName()).append(".  ").append(w.targetBody.HeShe()).append("'s willing to be humiliated for ").append(w.lordBody.himHer()).append(", but ").append(w.targetBody.heShe()).append("'s ashamed to show ").append(w.targetBody.hisHer()).append(" unfamiliarity with the sexual activities ").append(w.targetBody.heShe()).append("'ll surely be asked to perform.").toString());
                        w.targetBody.currentPLEA += (w.targetBody.getObedience() * w.targetBody.getDeviancy() * (100 + w.targetBody.getDignity())) / 1000;
                        w.targetBody.currentSHAM += (w.targetBody.getDignity() * (100 - w.targetBody.getDeviancy())) / 50;
                    } else
                    if(w.targetBody.getObedience() > 33)
                    {
                        if(w.targetBody.getHostility() > 66)
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" stands on the stage, glaring angrily at the surrounding Thralls who jeer and takes pictures of ").append(w.targetBody.himHer()).append(", but ").append(w.targetBody.heShe()).append(" still bows ").append(w.targetBody.hisHer()).append(" head respectfully as ").append(w.lordBody.ownerName()).append(" joins ").append(w.targetBody.himHer()).append(".").toString());
                        else
                        if(w.targetBody.getHostility() > 33)
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" obediently joins ").append(w.lordBody.ownerName()).append(" on stage, sparing only the occasional annoyed glance at the crowd of Thralls who shout out suggestions for ways ").append(w.targetBody.heShe()).append(" should be humiliated.  ").append(w.targetBody.HeShe()).append(" waits to see what will be done with ").append(w.targetBody.himHer()).append(".").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" seems a bit intimidated by the crowd of Thralls, but ").append(w.targetBody.heShe()).append(" still willingly joins ").append(w.lordBody.ownerName()).append(" on the stage and tells everyone that ").append(w.targetBody.heShe()).append(" hopes they enjoy the show.").toString());
                        w.targetBody.currentHATE += (50 + w.targetBody.getHostility()) - w.targetBody.getObedience();
                        if(w.targetBody.currentHATE < 0L)
                            w.targetBody.currentHATE = 0L;
                        w.targetBody.currentFEAR = 100 - w.targetBody.getConfidence();
                        w.targetBody.currentPLEA += (w.targetBody.getDeviancy() * w.targetBody.getDeviancy()) / 100;
                    } else
                    {
                        if(w.targetBody.getConfidence() > 66)
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" has to be dragged onto the stage by force, kicking and shouting, and when ").append(w.targetBody.heShe()).append("'s finally thrown in front of ").append(w.lordBody.ownerName()).append(", ").append(w.targetBody.heShe()).append(" glares up in angry defiance.  ").toString());
                        else
                        if(w.targetBody.getConfidence() > 33)
                            w.append(t, (new StringBuilder("On a stage with ")).append(w.lordBody.ownerName()).append(" in front of a crowd that roars with eagerness to see ").append(w.targetBody.himHer()).append(" raped, ").append(w.targetBody.ownerName()).append(" tries to remain calm, but ").append(w.targetBody.heShe()).append(" feels ").append(w.targetBody.hisHer()).append(" heart pounding in ").append(w.targetBody.hisHer()).append(" chest.  ").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" is trembling with fear as ").append(w.targetBody.heShe()).append(" allows a pair of Thralls to escort ").append(w.targetBody.himHer()).append(" onto the stage with ").append(w.lordBody.ownerName()).append(", flinching at every crude comment and threat shouted from the crowd.  ").toString());
                        w.targetBody.currentFEAR = ((100 - w.targetBody.getConfidence()) * (100 - w.targetBody.getDignity())) / 50;
                        w.targetBody.currentHATE = ((100 - w.targetBody.getObedience()) * (100 - w.targetBody.getDignity())) / 50;
                        w.targetBody.currentPLEA = (w.targetBody.getDeviancy() * (100 - w.targetBody.getDignity())) / 50;
                        if(!w.targetBody.hasBeenBroadcasted())
                        {
                            w.targetBody.currentFEAR = w.targetBody.currentFEAR * 3L;
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.HeShe()))).append(" sees filming phones in the crowd, and ").append(w.targetBody.heShe()).append(" knows that what happens here could change how people see ").append(w.targetBody.himHer()).append(" forever.").toString());
                        } else
                        if(w.targetBody.getDeviancy() > 66)
                            w.append(t, (new StringBuilder("And yet, as always, ")).append(w.targetBody.hisHer()).append(" body aches to be savagely fucked right there in front of everyone.").toString());
                        else
                        if(w.targetBody.getDeviancy() > 33)
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.HeShe()))).append(" tries not to admit to ").append(w.targetBody.himHer()).append("self just how much this is turning ").append(w.targetBody.himHer()).append(" on.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.HeShe()))).append(" hates everything about this situation.").toString());
                    }
                    for(int i = 0; i < w.sceneParticipants.length && w.sceneLocation == Activity.Location.STAGE; i++)
                        if(w.sceneParticipants[i].forsakenOwner != null && w.sceneParticipants[i].getEXPOLevel() >= 3)
                        {
                            int audience = 300_000 + (int)(Math.random() * 300000D);
                            w.sceneParticipants[i].forsakenOwner.timesExposed += audience;
                            w.sceneParticipants[i].forsakenOwner.timesExposedSelf += audience;
                        }

                }
                w.append(t, "\n\n");
            } else
            {
                Boolean descriptionStarted = false;
                Activity mentioned[] = new Activity[0];
                Body targeted[] = new Body[0];
                for(int i = 0; i < w.sceneParticipants.length; i++)
                {
                    for(int j = 0; j < w.sceneParticipants[i].inProgress.length; j++)
                        if(!w.sceneParticipants[i].inProgress[j].endsSelf)
                        {
                            if(!descriptionStarted)
                            {
                                descriptionStarted = true;
                                w.append(t, "In Progress:");
                            }
                            Boolean found = false;
                            for(int k = 0; k < mentioned.length; k++)
                                if(mentioned[k].counterpart == w.sceneParticipants[i].inProgress[j] && targeted[k] == w.sceneParticipants[i])
                                    found = true;

                            if(!found)
                            {
                                w.sceneParticipants[i].inProgress[j].shortDescription(t, w, w.sceneParticipants[i], w.sceneParticipants[i].targets[j]);
                                Activity newMentioned[] = new Activity[mentioned.length + 1];
                                Body newTargeted[] = new Body[targeted.length + 1];
                                for(int k = 0; k < mentioned.length; k++)
                                {
                                    newMentioned[k] = mentioned[k];
                                    newTargeted[k] = targeted[k];
                                }

                                newMentioned[mentioned.length] = w.sceneParticipants[i].inProgress[j];
                                newTargeted[targeted.length] = w.sceneParticipants[i].targets[j];
                                mentioned = newMentioned;
                                targeted = newTargeted;
                            }
                        }

                }

                if(descriptionStarted)
                    w.append(t, "\n\n");
            }
            w.append(t, (new StringBuilder("How will ")).append(ownerName()).append(" act?").toString());
            JButton Touch = new JButton("Touch");
            Touch.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    TouchMenu(t, p, f, w, s);
                }
            });
            p.add(Touch);
            if(!Project.PushDown.isInProgress(w.lordBody, w.targetBody) && !Project.PullDown.isInProgress(w.lordBody, w.targetBody))
            {
                JButton Position = new JButton("Position");
                Position.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        PositionMenu(t, p, f, w, s);
                    }
                });
                p.add(Position);
            } else
            if(Project.PushDown.isInProgress(w.lordBody, w.targetBody))
            {
                JButton Sex = new JButton("Sex");
                Sex.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        SexMenu(t, p, f, w, s);
                    }
                });
                p.add(Sex);
            }
            if(w.lordBody.inProgress.length > 0)
            {
                JButton Cancel = new JButton("Stop Action");
                Cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhich action will you cancel?").toString());
                        CancelActivities(t, p, f, w, s, 0);
                    }
                });
                p.add(Cancel);
            }
            JButton Wait = new JButton("Wait");
            Wait.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Boolean summaryNeeded = false;
                    if(summaryNeeded)
                        Continue(t, p, f, w, s);
                    else
                        advanceAction(t, p, f, w, s);
                }
            });
            if(inProgress.length > 0)
                Wait.setText("Continue");
            p.add(Wait);
            JButton Done = new JButton("End");
            Done.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(w.active)
                    {
                        Project.Shop(t, p, f, w);
                    } else
                    {
                        WriteObject wobj = new WriteObject();
                        wobj.serializeSaveData(s);
                        Project.IntroOne(t, p, f, w);
                    }
                }
            });
            p.add(Done);
            p.validate();
            p.repaint();
        } else
        {
            p.removeAll();
            Activity pickedActivity = Project.SpreadLegs;
            Body target = null;
            Body consideredTarget = w.lordBody;
            int activityWeights[] = new int[Project.allActivities.length];
            int extreme = 0;
            Boolean ending = false;
            for(int i = 0; i < inProgress.length; i++)
            {
                int value = inProgress[i].weight(w, this, targets[i]);
                int validEnderIndex = -1;
                for(int j = 0; j < inProgress[i].enders.length; j++)
                    if(inProgress[i].enders[j].valid(this, targets[i]))
                    {
                        validEnderIndex = j;
                        j = inProgress[i].enders.length;
                    }

                if(value < extreme && validEnderIndex >= 0)
                {
                    ending = true;
                    extreme = value;
                    pickedActivity = inProgress[i].enders[validEnderIndex];
                    if(pickedActivity.targeted)
                        target = consideredTarget;
                    else
                        target = null;
                }
            }

            for(int i = 0; i < activityWeights.length && !ending; i++)
                if(Project.allActivities[i].pickable && Project.allActivities[i].valid(this, w.lordBody))
                {
                    int value = Project.allActivities[i].weight(w, this, w.lordBody);
                    if(value > extreme)
                    {
                        extreme = value;
                        pickedActivity = Project.allActivities[i];
                        if(pickedActivity.targeted)
                            target = consideredTarget;
                        else
                            target = null;
                    }
                }

            pickedActivity.startActivity(t, w, this, target);
            w.append(t, "\n\n");
            CharacterDamage(t, w);
            w.append(t, "\n\n");
            pickedActivity.activityTalk(t, w, this, target);
            Continue(t, p, f, w, s);
        }
    }

    public void LordDamage(JTextPane t, WorldState w)
    {
        w.append(t, OwnerName());
        long damageTaken[] = new long[8];
        for(int i = 0; i < inProgress.length; i++)
            damageTaken = inProgress[i].damageContribution(w, this, damageTaken);

        long actualDamage[] = InflictDamage(damageTaken);
        for(int i = 5; i < 6; i++)
        {
            if(i == 0)
                w.append(t, "\nFEAR [");
            else
            if(i == 1)
                w.append(t, "\nDISG [");
            else
            if(i == 2)
                w.append(t, "\nPAIN [");
            else
            if(i == 3)
                w.append(t, "\nSHAM [");
            else
            if(i == 4)
                w.append(t, "\n\nHATE [");
            else
            if(i == 5)
                w.append(t, "\nPLEA [");
            else
            if(i == 6)
                w.append(t, "\nTIRE [");
            else
                w.append(t, "\nEXPO [");
            char fillChar = '\'';
            char remainChar = ' ';
            int tier = 0;
            long totalDamage = currentDamage()[i] + actualDamage[i];
            int increment = 10;
            for(; totalDamage >= 10_000_000L; totalDamage /= 1_000_000L)
                tier++;

            Boolean mixed = false;
            if(tier > 0)
            {
                remainChar = '#';
                mixed = true;
            }
            if(totalDamage >= 100L)
            {
                remainChar = fillChar;
                fillChar = '-';
                increment = 100;
                mixed = false;
            }
            if(totalDamage >= 1000L)
            {
                remainChar = fillChar;
                fillChar = '~';
                increment = 1000;
            }
            if(totalDamage >= 10000L)
            {
                remainChar = fillChar;
                fillChar = '=';
                increment = 10000;
            }
            if(totalDamage >= 100_000L)
            {
                remainChar = fillChar;
                fillChar = 'X';
                increment = 100_000;
            }
            if(totalDamage >= 1_000_000L)
            {
                remainChar = fillChar;
                fillChar = '#';
                increment = 1_000_000;
            }
            long incrementsRemaining = totalDamage / (long)increment;
            for(int j = 0; j < 10; j++)
                if(incrementsRemaining-- > 0L)
                {
                    if(tier == 0)
                        w.append(t, (new StringBuilder(String.valueOf(fillChar))).toString());
                    else
                    if(tier == 1)
                        w.inverseAppend(t, (new StringBuilder(String.valueOf(fillChar))).toString());
                    else
                        w.tierTwoAppend(t, (new StringBuilder(String.valueOf(fillChar))).toString());
                } else
                if(tier == 0 || tier == 1 && mixed)
                    w.append(t, (new StringBuilder(String.valueOf(remainChar))).toString());
                else
                if(tier == 1 || tier == 2 && mixed)
                    w.inverseAppend(t, (new StringBuilder(String.valueOf(remainChar))).toString());
                else
                    w.tierTwoAppend(t, (new StringBuilder(String.valueOf(remainChar))).toString());

            w.append(t, (new StringBuilder("] (")).append((new Chosen()).fixedFormat(currentDamage()[i])).toString());
            if(actualDamage[i] == 0L)
                w.append(t, "        ");
            else
                w.append(t, (new StringBuilder(" + ")).append((new Chosen()).fixedFormat(actualDamage[i])).toString());
            if(i == 0)
                currentFEAR += actualDamage[i];
            else
            if(i == 1)
                currentDISG += actualDamage[i];
            else
            if(i == 2)
                currentPAIN += actualDamage[i];
            else
            if(i == 3)
                currentSHAM += actualDamage[i];
            else
            if(i == 4)
                currentHATE += actualDamage[i];
            else
            if(i == 5)
                currentPLEA += actualDamage[i];
            else
            if(i == 6)
                currentINJU += actualDamage[i];
            else
            if(i == 7)
                currentEXPO += actualDamage[i];
            w.append(t, (new StringBuilder(" = ")).append((new Chosen()).fixedFormat(currentDamage()[i])).append(") ").toString());
            if(currentDamage()[i] > 0L)
                if(i == 0)
                {
                    if(currentDamage()[i] < 100L)
                        w.append(t, "feeling in control");
                    else
                    if(currentDamage()[i] < 1000L)
                        w.append(t, "slightly intimidated");
                    else
                    if(currentDamage()[i] < 10000L)
                        w.append(t, "terrified");
                    else
                    if(currentDamage()[i] < 100_000L)
                        w.append(t, "desperate to please");
                    else
                    if(currentDamage()[i] < 1_000_000L)
                        w.append(t, "blind panic");
                    else
                        w.append(t, "consumed by fear");
                } else
                if(i == 1)
                {
                    if(currentDamage()[i] < 100L)
                        w.append(t, "comfortable with own actions");
                    else
                    if(currentDamage()[i] < 1000L)
                        w.append(t, "uncomfortable");
                    else
                    if(currentDamage()[i] < 10000L)
                        w.append(t, "disgusted with self");
                    else
                    if(currentDamage()[i] < 100_000L)
                        w.append(t, "struggling not to be sick");
                    else
                    if(currentDamage()[i] < 1_000_000L)
                        w.append(t, "trying to ignore everything");
                    else
                        w.append(t, "overwhelming disgust");
                } else
                if(i == 2)
                {
                    if(currentDamage()[i] < 100L)
                        w.append(t, "no notable pain");
                    else
                    if(currentDamage()[i] < 1000L)
                        w.append(t, "conscious of pain");
                    else
                    if(currentDamage()[i] < 10000L)
                        w.append(t, "distracted by pain");
                    else
                    if(currentDamage()[i] < 100_000L)
                        w.append(t, "reeling from the pain");
                    else
                    if(currentDamage()[i] < 1_000_000L)
                        w.append(t, "overwhelmed by pain");
                    else
                        w.append(t, "feels nothing but pain");
                } else
                if(i == 3)
                {
                    if(currentDamage()[i] < 100L)
                        w.append(t, "not self-conscious");
                    else
                    if(currentDamage()[i] < 1000L)
                        w.append(t, (new StringBuilder("realizes how ")).append(heShe()).append(" must look").toString());
                    else
                    if(currentDamage()[i] < 10000L)
                        w.append(t, "feels watching eyes");
                    else
                    if(currentDamage()[i] < 100_000L)
                        w.append(t, (new StringBuilder("can't stop thinking about how ")).append(heShe()).append(" looks").toString());
                    else
                    if(currentDamage()[i] < 1_000_000L)
                        w.append(t, "tears of shame");
                    else
                        w.append(t, "mind paralyzed by shame");
                } else
                if(i == 4)
                {
                    if(currentDamage()[i] < 100L)
                        w.append(t, "doesn't mind what's happening");
                    else
                    if(currentDamage()[i] < 1000L)
                        w.append(t, "annoyed");
                    else
                    if(currentDamage()[i] < 10000L)
                        w.append(t, "strong anger");
                    else
                    if(currentDamage()[i] < 100_000L)
                        w.append(t, "too angry to cooperate");
                    else
                    if(currentDamage()[i] < 1_000_000L)
                        w.append(t, "overpowering hatred");
                    else
                        w.append(t, "all-consuming hatred");
                } else
                if(i == 5)
                {
                    if(currentDamage()[i] < 100L)
                        w.append(t, "not really turned on");
                    else
                    if(currentDamage()[i] < 1000L)
                        w.append(t, "sexually aroused");
                    else
                    if(currentDamage()[i] < 10000L)
                        w.append(t, "orgasm approaching");
                    else
                    if(currentDamage()[i] < 100_000L)
                        w.append(t, "orgasmic pleasure");
                    else
                    if(currentDamage()[i] < 1_000_000L)
                        w.append(t, "experiencing multiple orgasms");
                    else
                        w.append(t, "cumming nonstop");
                } else
                if(i == 6)
                {
                    if(currentDamage()[i] < 100L)
                        w.append(t, "full of energy");
                    else
                    if(currentDamage()[i] < 1000L)
                        w.append(t, "starting to get tired");
                    else
                    if(currentDamage()[i] < 10000L)
                        w.append(t, "too tired to continue");
                    else
                    if(currentDamage()[i] < 100_000L)
                        w.append(t, "completely spent");
                    else
                    if(currentDamage()[i] < 1_000_000L)
                        w.append(t, "unconscious");
                    else
                        w.append(t, "body failing under the strain");
                } else
                if(i == 7)
                    if(isParasitized())
                    {
                        if(currentDamage()[i] < 100L)
                            w.append(t, "no more exposed than usual");
                        else
                        if(currentDamage()[i] < 1000L)
                            w.append(t, "more exposed than usual");
                        else
                        if(currentDamage()[i] < 10000L)
                        {
                            if(getGender().equals(Forsaken.Gender.MALE))
                                w.append(t, "chest");
                            else
                                w.append(t, "breasts");
                            w.append(t, " on full display");
                        } else
                        if(currentDamage()[i] < 100_000L)
                        {
                            if(getGender().equals(Forsaken.Gender.MALE))
                                w.append(t, "asshole");
                            else
                                w.append(t, "pussy");
                            w.append(t, " uncovered for easy access");
                        } else
                        if(currentDamage()[i] < 1_000_000L)
                            w.append(t, "essentially naked");
                        else
                            w.append(t, "worse than naked");
                    } else
                    if(currentDamage()[i] < 100L)
                        w.append(t, "clothes slightly disheveled");
                    else
                    if(currentDamage()[i] < 1000L)
                        w.append(t, "clothes have some small tears");
                    else
                    if(currentDamage()[i] < 10000L)
                        w.append(t, (new StringBuilder("clothes torn across ")).append(hisHer()).append(" hips and chest").toString());
                    else
                    if(currentDamage()[i] < 100_000L)
                        w.append(t, "shredded clothes sometimes expose everything");
                    else
                    if(currentDamage()[i] < 1_000_000L)
                        w.append(t, "essentially naked");
                    else
                        w.append(t, "every inch of skin exposed");
        }

        if(crossedThreshold(actualDamage[5], currentDamage()[5]) > 0 || orgasmLevel(actualDamage[5], currentDamage()[5]) > 0)
            if(getPLEALevel() < 3)
            {
                w.purpleAppend(t, "\n\nPLEA up!  ");
                if(getPLEALevel() == 1)
                    w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append("'s body is beginning to experience sexual arousal.").toString());
                else
                if(getPLEALevel() == 2)
                {
                    w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append("'s ").toString());
                    if(parts[PENIS] > 0)
                        w.append(t, (new StringBuilder("penis is twitching, straining as ")).append(hisHer()).append(" body approaches orgasm.").toString());
                    else
                        w.append(t, (new StringBuilder("whole body feels sensitive, pleasure pulsing in ")).append(hisHer()).append(" loins as orgasm approaches.").toString());
                }
            } else
            if(!orgasmPossible())
            {
                w.purpleAppend(t, (new StringBuilder("\n\nPLEA up!  ")).append(OwnerName()).append(" wildly bucks ").append(hisHer()).append(" hips in search of orgasm, but ").append(heShe()).append(" can't give ").append(himHer()).append("self the necessary stimulation, and ").append(heShe()).append(" comes back from the brink of climax.").toString());
            } else
            {
                w.purpleAppend(t, "\n\nOrgasm!  ");
                if(orgasms == 0)
                {
                    if(currentDamage()[5] - actualDamage[5] >= 10000L && currentDamage()[5] >= 30000L)
                    {
                        w.append(t, (new StringBuilder("The pent-up climax comes all at once, and ")).append(ownerName()).toString());
                        if(parts[PENIS] > 0)
                            w.append(t, "'s cock spurts a huge load in all directions.");
                        else
                            w.append(t, (new StringBuilder(" cums so hard that ")).append(heShe()).append(" feels weak and unsteady afterwards.").toString());
                    } else
                    {
                        w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append("'s whole body shudders shudders as ").toString());
                        if(parts[PENIS] > 0)
                            w.append(t, (new StringBuilder(String.valueOf(hisHer()))).append(" cock shoots its load.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(heShe()))).append(" climaxes.").toString());
                    }
                } else
                if(orgasms == 1)
                {
                    w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" cums again, ").toString());
                    if(parts[PENIS] > 0)
                        w.append(t, "shooting a smaller load this time, but it feels even better.");
                    else
                        w.append(t, (new StringBuilder(String.valueOf(hisHer()))).append(" second climax proving even more intense than the first.").toString());
                } else
                {
                    w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" cums yet again, ").toString());
                    if(parts[PENIS] > 0)
                        w.append(t, (new StringBuilder(String.valueOf(hisHer()))).append(" exhausted penis managing only a trickle of cum, but even that much feels incredibly good.").toString());
                    else
                        w.append(t, (new StringBuilder("body spasming wildly, completely outside ")).append(hisHer()).append(" control.").toString());
                }
                orgasms++;
            }
    }

    public void CharacterDamage(JTextPane t, WorldState w)
    {
        w.append(t, OwnerName());
        long damageTaken[] = new long[8];
        if(w.sceneLocation == Activity.Location.STAGE)
            damageTaken[3] = 10L;
        if(getObedience() < 67)
            damageTaken[4] = (67 - getObedience()) / 11;
        damageTaken[6] = 4L;
        for(int i = 0; i < inProgress.length; i++)
            damageTaken = inProgress[i].damageContribution(w, this, damageTaken);

        if(w.sceneLocation == Activity.Location.STAGE)
            damageTaken[3] = damageTaken[3] * 3L;
        long actualDamage[] = InflictDamage(damageTaken);
        for(int i = 0; i < 8; i++)
        {
            if(i == 7 && currentDamage()[i] + actualDamage[i] > 1_000_000L)
            {
                actualDamage[i] = 1_000_000L - currentDamage()[i];
                removeActivity(Project.Stripped, null);
            }
            if(i == 0)
                w.append(t, "\nFEAR [");
            else
            if(i == 1)
                w.append(t, "\nDISG [");
            else
            if(i == 2)
                w.append(t, "\nPAIN [");
            else
            if(i == 3)
                w.append(t, "\nSHAM [");
            else
            if(i == 4)
                w.append(t, "\n\nHATE [");
            else
            if(i == 5)
                w.append(t, "\nPLEA [");
            else
            if(i == 6)
                w.append(t, "\nTIRE [");
            else
                w.append(t, "\nEXPO [");
            char fillChar = '\'';
            char remainChar = ' ';
            int tier = 0;
            long totalDamage = currentDamage()[i] + actualDamage[i];
            int increment = 10;
            for(; totalDamage >= 10_000_000L; totalDamage /= 1_000_000L)
                tier++;

            Boolean mixed = false;
            if(tier > 0)
            {
                remainChar = '#';
                mixed = true;
            }
            if(totalDamage >= 100L)
            {
                remainChar = fillChar;
                fillChar = '-';
                increment = 100;
                mixed = false;
            }
            if(totalDamage >= 1000L)
            {
                remainChar = fillChar;
                fillChar = '~';
                increment = 1000;
            }
            if(totalDamage >= 10000L)
            {
                remainChar = fillChar;
                fillChar = '=';
                increment = 10000;
            }
            if(totalDamage >= 100_000L)
            {
                remainChar = fillChar;
                fillChar = 'X';
                increment = 100_000;
            }
            if(totalDamage >= 1_000_000L)
            {
                remainChar = fillChar;
                fillChar = '#';
                increment = 1_000_000;
            }
            long incrementsRemaining = totalDamage / (long)increment;
            for(int j = 0; j < 10; j++)
                if(incrementsRemaining-- > 0L)
                {
                    if(tier == 0)
                        w.append(t, (new StringBuilder(String.valueOf(fillChar))).toString());
                    else
                    if(tier == 1)
                        w.inverseAppend(t, (new StringBuilder(String.valueOf(fillChar))).toString());
                    else
                        w.tierTwoAppend(t, (new StringBuilder(String.valueOf(fillChar))).toString());
                } else
                if(tier == 0 || tier == 1 && mixed)
                    w.append(t, (new StringBuilder(String.valueOf(remainChar))).toString());
                else
                if(tier == 1 || tier == 2 && mixed)
                    w.inverseAppend(t, (new StringBuilder(String.valueOf(remainChar))).toString());
                else
                    w.tierTwoAppend(t, (new StringBuilder(String.valueOf(remainChar))).toString());

            w.append(t, (new StringBuilder("] (")).append((new Chosen()).fixedFormat(currentDamage()[i])).toString());
            if(actualDamage[i] == 0L)
                w.append(t, "        ");
            else
                w.append(t, (new StringBuilder(" + ")).append((new Chosen()).fixedFormat(actualDamage[i])).toString());
            if(i == 0)
                currentFEAR += actualDamage[i];
            else
            if(i == 1)
                currentDISG += actualDamage[i];
            else
            if(i == 2)
                currentPAIN += actualDamage[i];
            else
            if(i == 3)
                currentSHAM += actualDamage[i];
            else
            if(i == 4)
                currentHATE += actualDamage[i];
            else
            if(i == 5)
                currentPLEA += actualDamage[i];
            else
            if(i == 6)
                currentINJU += actualDamage[i];
            else
            if(i == 7)
                currentEXPO += actualDamage[i];
            w.append(t, (new StringBuilder(" = ")).append((new Chosen()).fixedFormat(currentDamage()[i])).append(") ").toString());
            if(currentDamage()[i] > 0L)
                if(i == 0)
                {
                    if(currentDamage()[i] < 100L)
                        w.append(t, "feeling in control");
                    else
                    if(currentDamage()[i] < 1000L)
                        w.append(t, "slightly intimidated");
                    else
                    if(currentDamage()[i] < 10000L)
                        w.append(t, "terrified");
                    else
                    if(currentDamage()[i] < 100_000L)
                        w.append(t, "desperate to please");
                    else
                    if(currentDamage()[i] < 1_000_000L)
                        w.append(t, "blind panic");
                    else
                        w.append(t, "consumed by fear");
                } else
                if(i == 1)
                {
                    if(currentDamage()[i] < 100L)
                        w.append(t, "comfortable with own actions");
                    else
                    if(currentDamage()[i] < 1000L)
                        w.append(t, "uncomfortable");
                    else
                    if(currentDamage()[i] < 10000L)
                        w.append(t, "disgusted with self");
                    else
                    if(currentDamage()[i] < 100_000L)
                        w.append(t, "struggling not to be sick");
                    else
                    if(currentDamage()[i] < 1_000_000L)
                        w.append(t, "trying to ignore everything");
                    else
                        w.append(t, "overwhelming disgust");
                } else
                if(i == 2)
                {
                    if(currentDamage()[i] < 100L)
                        w.append(t, "no notable pain");
                    else
                    if(currentDamage()[i] < 1000L)
                        w.append(t, "conscious of pain");
                    else
                    if(currentDamage()[i] < 10000L)
                        w.append(t, "distracted by pain");
                    else
                    if(currentDamage()[i] < 100_000L)
                        w.append(t, "reeling from the pain");
                    else
                    if(currentDamage()[i] < 1_000_000L)
                        w.append(t, "overwhelmed by pain");
                    else
                        w.append(t, "feels nothing but pain");
                } else
                if(i == 3)
                {
                    if(currentDamage()[i] < 100L)
                        w.append(t, "not self-conscious");
                    else
                    if(currentDamage()[i] < 1000L)
                        w.append(t, (new StringBuilder("realizes how ")).append(heShe()).append(" must look").toString());
                    else
                    if(currentDamage()[i] < 10000L)
                        w.append(t, "feels watching eyes");
                    else
                    if(currentDamage()[i] < 100_000L)
                        w.append(t, (new StringBuilder("can't stop thinking about how ")).append(heShe()).append(" looks").toString());
                    else
                    if(currentDamage()[i] < 1_000_000L)
                        w.append(t, "tears of shame");
                    else
                        w.append(t, "mind paralyzed by shame");
                } else
                if(i == 4)
                {
                    if(currentDamage()[i] < 100L)
                        w.append(t, "doesn't mind what's happening");
                    else
                    if(currentDamage()[i] < 1000L)
                        w.append(t, "annoyed");
                    else
                    if(currentDamage()[i] < 10000L)
                        w.append(t, "strong anger");
                    else
                    if(currentDamage()[i] < 100_000L)
                        w.append(t, "too angry to cooperate");
                    else
                    if(currentDamage()[i] < 1_000_000L)
                        w.append(t, "overpowering hatred");
                    else
                        w.append(t, "all-consuming hatred");
                } else
                if(i == 5)
                {
                    if(currentDamage()[i] < 100L)
                        w.append(t, "not really turned on");
                    else
                    if(currentDamage()[i] < 1000L)
                        w.append(t, "blushing and distracted");
                    else
                    if(currentDamage()[i] < 10000L)
                        w.append(t, "orgasm approaching");
                    else
                    if(currentDamage()[i] < 100_000L)
                        w.append(t, "oragsmic pleasure");
                    else
                    if(currentDamage()[i] < 1_000_000L)
                        w.append(t, "experiencing multiple orgasms");
                    else
                        w.append(t, "cumming nonstop");
                } else
                if(i == 6)
                {
                    if(currentDamage()[i] < 100L)
                        w.append(t, "full of energy");
                    else
                    if(currentDamage()[i] < 1000L)
                        w.append(t, "starting to get tired");
                    else
                    if(currentDamage()[i] < 10000L)
                        w.append(t, "too tired to continue");
                    else
                    if(currentDamage()[i] < 100_000L)
                        w.append(t, "completely spent");
                    else
                    if(currentDamage()[i] < 1_000_000L)
                        w.append(t, "consciousness fading");
                    else
                        w.append(t, "body failing under the strain");
                } else
                if(i == 7)
                    if(isParasitized())
                    {
                        if(currentDamage()[i] < 100L)
                            w.append(t, "no more exposed than usual");
                        else
                        if(currentDamage()[i] < 1000L)
                            w.append(t, "more exposed than usual");
                        else
                        if(currentDamage()[i] < 10000L)
                        {
                            if(getGender().equals(Forsaken.Gender.MALE))
                                w.append(t, "chest");
                            else
                                w.append(t, "breasts");
                            w.append(t, " on full display");
                        } else
                        if(currentDamage()[i] < 100_000L)
                        {
                            if(getGender().equals(Forsaken.Gender.MALE))
                                w.append(t, "asshole");
                            else
                                w.append(t, "pussy");
                            w.append(t, " uncovered for easy access");
                        } else
                        if(currentDamage()[i] < 1_000_000L)
                            w.append(t, "essentially naked");
                        else
                            w.append(t, "completely naked");
                    } else
                    if(currentDamage()[i] < 100L)
                        w.append(t, "clothes slightly disheveled");
                    else
                    if(currentDamage()[i] < 1000L)
                        w.append(t, "clothes have some small tears");
                    else
                    if(currentDamage()[i] < 10000L)
                        w.append(t, (new StringBuilder("clothes torn across ")).append(hisHer()).append(" hips and chest").toString());
                    else
                    if(currentDamage()[i] < 100_000L)
                        w.append(t, "shredded clothes sometimes expose everything");
                    else
                    if(currentDamage()[i] < 1_000_000L)
                        w.append(t, "essentially naked");
                    else
                        w.append(t, "completely naked");
        }

        if(crossedThreshold(actualDamage[0], currentDamage()[0]) > 0)
        {
            w.purpleAppend(t, "\n\nFEAR up!  ");
            if(crossedThreshold(actualDamage[0], currentDamage()[0]) == 1)
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" can't help but think of the consquences of displeasing ").append(w.lordBody.ownerName()).append(".").toString());
            else
            if(crossedThreshold(actualDamage[0], currentDamage()[0]) == 2)
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" is desperate to please ").append(w.lordBody.ownerName()).append(".").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" is overcome by ").append(hisHer()).append(" terror of ").append(w.lordBody.ownerName()).append(".").toString());
        }
        if(crossedThreshold(actualDamage[1], currentDamage()[1]) > 0)
        {
            w.purpleAppend(t, "\n\nDISG up!  ");
            if(crossedThreshold(actualDamage[1], currentDamage()[1]) == 1)
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" has started to feel bad about what ").append(heShe()).append("'s doing.").toString());
            else
            if(crossedThreshold(actualDamage[1], currentDamage()[1]) == 2)
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" has firmly crossed the line into activities ").append(heShe()).append("'s not comfortable with.").toString());
            else
                w.append(t, (new StringBuilder("It's a struggle for ")).append(ownerName()).append(" to force ").append(himHer()).append("self to continue.").toString());
        }
        if(crossedThreshold(actualDamage[2], currentDamage()[2]) > 0)
        {
            w.purpleAppend(t, "\n\nPAIN up!  ");
            if(crossedThreshold(actualDamage[2], currentDamage()[2]) == 1)
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" consciously notices that ").append(heShe()).append("'s feeling pain.").toString());
            else
            if(crossedThreshold(actualDamage[2], currentDamage()[2]) == 2)
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" can't stop ").append(himHer()).append("self from flinching away from the pain.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" is in so much pain that ").append(heShe()).append(" can hardly focus.").toString());
        }
        if(crossedThreshold(actualDamage[3], currentDamage()[3]) > 0)
        {
            w.purpleAppend(t, "\n\nSHAM up!  ");
            if(crossedThreshold(actualDamage[3], currentDamage()[3]) == 1)
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" has realized how lascivious ").append(hisHer()).append(" appearance has become.").toString());
            else
            if(crossedThreshold(actualDamage[3], currentDamage()[3]) == 2)
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" is becoming increasingly self-conscious.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" can't stop thinking about how ").append(heShe()).append(" must look.").toString());
        }
        if(crossedThreshold(actualDamage[4], currentDamage()[4]) > 0)
        {
            w.purpleAppend(t, "\n\nHATE up!  ");
            if(crossedThreshold(actualDamage[4], currentDamage()[4]) == 1)
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" is growing annoyed with the situation.").toString());
            else
            if(crossedThreshold(actualDamage[4], currentDamage()[4]) == 2)
            {
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" is angry at ").append(w.lordBody.ownerName()).append(".").toString());
            } else
            {
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" is consumed by hatred.").toString());
                if(currentDamage()[4] - actualDamage[4] < 10000L)
                    w.append(t, (new StringBuilder("  ")).append(HisHer()).append(" Sexual Barrier fades away.").toString());
            }
        }
        if(crossedThreshold(actualDamage[5], currentDamage()[5]) > 0 || orgasmLevel(actualDamage[5], currentDamage()[5]) > 0)
            if(getPLEALevel() < 3)
            {
                w.purpleAppend(t, "\n\nPLEA up!  ");
                if(getPLEALevel() == 1)
                    w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" is starting to get turned on.").toString());
                else
                if(getPLEALevel() == 2)
                {
                    w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append("'s ").toString());
                    if(parts[PENIS] > 0)
                        w.append(t, (new StringBuilder("penis is twitching, straining as ")).append(heShe()).append(" approaches orgasm.").toString());
                    else
                        w.append(t, (new StringBuilder("whole body feels sensitive, pleasure pulsing in ")).append(hisHer()).append(" loins as orgasm approaches.").toString());
                }
            } else
            if(!orgasmPossible())
            {
                w.purpleAppend(t, (new StringBuilder("\n\nPLEA up!  ")).append(OwnerName()).append(" wildly bucks ").append(hisHer()).append(" hips in search of orgasm, but ").append(heShe()).append(" can't give ").append(himHer()).append("self the necessary stimulation, and ").append(heShe()).append(" comes back from the brink of climax.").toString());
            } else
            {
                w.purpleAppend(t, "\n\nOrgasm!  ");
                int intensity = 200 + (int)(Math.random() * 200D);
                if(orgasms == 0)
                {
                    if(currentDamage()[5] - actualDamage[5] >= 10000L && currentDamage()[5] >= 30000L)
                    {
                        w.append(t, (new StringBuilder("The pent-up climax comes all at once, and ")).append(ownerName()).append(" squeals incoherently as ").toString());
                        if(parts[PENIS] > 0)
                            w.append(t, (new StringBuilder(String.valueOf(hisHer()))).append(" cock spurts a huge load in all directions.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(heShe()))).append(" cums almost hard enough to make ").append(himHer()).append(" pass out.").toString());
                    } else
                    {
                        intensity = 450 * (int)(Math.random() * 450D);
                        w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" shudders as ").toString());
                        if(parts[PENIS] > 0)
                            w.append(t, (new StringBuilder(String.valueOf(hisHer()))).append(" cock shoots its load.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(heShe()))).append(" climaxes.").toString());
                    }
                } else
                {
                    intensity = 300 + (int)(Math.random() * 300D);
                    if(orgasms == 1)
                    {
                        w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" cums again, ").toString());
                        if(parts[PENIS] > 0)
                            w.append(t, "shooting a smaller load this time, but it feels even better.");
                        else
                            w.append(t, "moaning and gasping for breath as the second climax proves even more intense than the first.");
                    } else
                    {
                        w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" cums yet again, ").toString());
                        if(parts[PENIS] > 0)
                            w.append(t, (new StringBuilder(String.valueOf(hisHer()))).append(" exhausted penis managing only a trickle of cum, but even that much is enough to make ").append(himHer()).append(" groan with unbearable pleasure.").toString());
                        else
                            w.append(t, (new StringBuilder("body spasming wildly, ")).append(hisHer()).append(" eyes wide and staring off into the distance as ").append(hisHer()).append(" vision flashes white.").toString());
                    }
                }
                orgasms++;
                if(forsakenOwner != null)
                {
                    if(forsakenOwner.timesOrgasmed == 0)
                    {
                        specialLine = 2;
                        w.append(t, (new StringBuilder("  It's ")).append(hisHer()).append(" first time experiencing orgasm.").toString());
                    } else
                    if(forsakenOwner.strongestOrgasm < 200)
                    {
                        specialLine = 2;
                        w.append(t, (new StringBuilder("  It's by far the strongest orgasm ")).append(heShe()).append("'s had in ").append(hisHer()).append(" life.").toString());
                    }
                    forsakenOwner.timesOrgasmed++;
                    if(intensity > forsakenOwner.strongestOrgasm)
                        forsakenOwner.strongestOrgasm = intensity;
                }
            }
        if(crossedThreshold(actualDamage[6], currentDamage()[6]) > 0)
        {
            w.purpleAppend(t, "\n\nTIRE up!  ");
            if(crossedThreshold(actualDamage[6], currentDamage()[6]) == 1)
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" is starting to get tired, but it's not slowing ").append(himHer()).append(" down yet.").toString());
            else
            if(crossedThreshold(actualDamage[6], currentDamage()[6]) == 2)
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append("'s fatigue is catching up with ").append(himHer()).append(", and ").append(heShe()).append("'s losing focus.").toString());
            else
            if(crossedThreshold(actualDamage[6], currentDamage()[6]) == 3)
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" is practically passing out.  ").append(HeShe()).append(" can't do much like this.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" fades in and out of consciousness.").toString());
        }
        if(crossedThreshold(actualDamage[7], currentDamage()[7]) > 0)
        {
            w.purpleAppend(t, "\n\nEXPO up!  ");
            if(crossedThreshold(actualDamage[7], currentDamage()[7]) == 1)
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append("'s clothes are partially undone.").toString());
            else
            if(crossedThreshold(actualDamage[7], currentDamage()[7]) == 2)
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append("'s ").append(breasts()).append(" can clearly be seen.").toString());
            else
            if(crossedThreshold(actualDamage[7], currentDamage()[7]) == 3)
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append("'s clothes have been shifted out of the way to show ").append(hisHer()).append(" most private places.").toString());
            else
            if(crossedThreshold(actualDamage[7], currentDamage()[7]) == 4)
                w.append(t, (new StringBuilder("Only a few scraps of ")).append(OwnerName()).append("'s clothes remain.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" is now completely naked.").toString());
            if(w.sceneLocation == Activity.Location.STAGE && currentDamage()[7] - actualDamage[7] < 10000L && currentDamage()[7] >= 10000L && forsakenOwner != null)
            {
                int added = 300_000 + (int)(Math.random() * 300000D);
                if(forsakenOwner.timesExposed < 300_000)
                {
                    specialLine = 4;
                    w.append(t, "  Flashing cameras from the crowd immortalize the moment forever.");
                }
                forsakenOwner.timesExposed += added;
                if(getObedience() + getDeviancy() >= 133)
                    forsakenOwner.timesExposedSelf += added;
            }
        }
    }

    public int crossedThreshold(long change, long currentValue)
    {
        int result = 0;
        if(currentValue > 0L && (currentValue == change || (int)Math.log10(currentValue - change) < (int)Math.log10(currentValue)))
        {
            result = (int)Math.log10(currentValue) - 1;
            if(result < 0)
                result = 0;
        }
        return result;
    }

    public int orgasmLevel(long change, long currentValue)
    {
        int previousLevel = 0;
        int currentLevel = 0;
        long previousPLEA = currentValue - change;
        if(previousPLEA >= 100_000L)
            previousLevel = (int)(previousPLEA / 100_000L + 2L);
        else
        if(previousPLEA >= 30000L)
            previousLevel = 2;
        else
        if(previousPLEA >= 10000L)
            previousLevel = 1;
        if(currentValue >= 100_000L)
            currentLevel = (int)(currentValue / 100_000L + 2L);
        else
        if(currentValue >= 30000L)
            currentLevel = 2;
        else
        if(currentValue >= 10000L)
            currentLevel = 1;
        if(currentLevel > previousLevel)
            return currentLevel;
        else
            return 0;
    }

    public Forsaken.Gender appearanceGender(Appearance seen)
    {
        if(seen == Appearance.CUTEGIRL)
            return Forsaken.Gender.FEMALE;
        if(seen == Appearance.CUTEBOY)
            return Forsaken.Gender.MALE;
        else
            return null;
    }

    public String heShe()
    {
        if(appearanceGender(bodyType) == Forsaken.Gender.FEMALE)
            return "she";
        if(appearanceGender(bodyType) == Forsaken.Gender.MALE)
            return "he";
        else
            return "it";
    }

    public String hisHer()
    {
        if(appearanceGender(bodyType) == Forsaken.Gender.FEMALE)
            return "her";
        if(appearanceGender(bodyType) == Forsaken.Gender.MALE)
            return "his";
        else
            return "its";
    }

    public String himHer()
    {
        if(appearanceGender(bodyType) == Forsaken.Gender.FEMALE)
            return "her";
        if(appearanceGender(bodyType) == Forsaken.Gender.MALE)
            return "him";
        else
            return "it";
    }

    public String HeShe()
    {
        if(appearanceGender(bodyType) == Forsaken.Gender.FEMALE)
            return "She";
        if(appearanceGender(bodyType) == Forsaken.Gender.MALE)
            return "He";
        else
            return "It";
    }

    public String HisHer()
    {
        if(appearanceGender(bodyType) == Forsaken.Gender.FEMALE)
            return "Her";
        if(appearanceGender(bodyType) == Forsaken.Gender.MALE)
            return "His";
        else
            return "Its";
    }

    public String HimHer()
    {
        if(appearanceGender(bodyType) == Forsaken.Gender.FEMALE)
            return "Her";
        if(appearanceGender(bodyType) == Forsaken.Gender.MALE)
            return "Him";
        else
            return "It";
    }

    public Forsaken.Gender getGender()
    {
        if(forsakenOwner != null)
            return forsakenOwner.gender;
        if(chosenOwner != null)
        {
            if(chosenOwner.gender.equals("male"))
                return Forsaken.Gender.MALE;
            if(chosenOwner.gender.equals("female"))
                return Forsaken.Gender.FEMALE;
            else
                return Forsaken.Gender.FUTANARI;
        }
        if(appearanceGender(bodyType) == Forsaken.Gender.FEMALE)
        {
            if(parts[PENIS] > 0)
                return Forsaken.Gender.FUTANARI;
            else
                return Forsaken.Gender.FEMALE;
        } else
        {
            return Forsaken.Gender.MALE;
        }
    }

    public Chosen.Species getType()
    {
        if(forsakenOwner != null)
            return forsakenOwner.type;
        if(chosenOwner != null)
            return chosenOwner.type;
        else
            return null;
    }

    public Boolean isForsaken()
    {
        if(forsakenOwner != null)
            return true;
        else
            return false;
    }

    public void addActivity(Activity added, Body partner)
    {
        Activity newInProgress[] = new Activity[inProgress.length + 1];
        Body newTargets[] = new Body[targets.length + 1];
        for(int i = 0; i < inProgress.length; i++)
        {
            newInProgress[i] = inProgress[i];
            newTargets[i] = targets[i];
        }

        newInProgress[newInProgress.length - 1] = added;
        newTargets[newTargets.length - 1] = partner;
        inProgress = newInProgress;
        targets = newTargets;
    }

    public void removeActivity(Activity removed, Body partner)
    {
        Activity prunedList[] = new Activity[inProgress.length];
        Body prunedPartners[] = new Body[targets.length];
        int index = 0;
        Activity counterparts[] = new Activity[0];
        Body counterPartners[] = new Body[0];
        for(int i = 0; i < prunedList.length; i++)
            if(inProgress[i] != removed || partner != targets[i] && partner != null)
            {
                prunedList[index] = inProgress[i];
                prunedPartners[index] = targets[i];
                index++;
            } else
            if(removed.counterpart != null && removed.counterpart.counterpart == removed)
            {
                Activity newCounterparts[] = new Activity[counterparts.length + 1];
                Body newCounterPartners[] = new Body[counterPartners.length + 1];
                for(int j = 0; j < counterparts.length; j++)
                {
                    newCounterparts[j] = counterparts[j];
                    newCounterPartners[j] = counterPartners[j];
                }

                newCounterparts[newCounterparts.length - 1] = inProgress[i].counterpart;
                newCounterPartners[newCounterPartners.length - 1] = targets[i];
                counterparts = newCounterparts;
                counterPartners = newCounterPartners;
            }

        Activity newInProgress[] = new Activity[index];
        Body newTargets[] = new Body[index];
        for(int i = 0; i < index; i++)
        {
            newInProgress[i] = prunedList[i];
            newTargets[i] = prunedPartners[i];
        }

        inProgress = newInProgress;
        targets = newTargets;
        if(removed == Project.PushDown || removed == Project.PullDown)
        {
            removeActivity(Project.VaginalPenetrate, partner);
            removeActivity(Project.PenetratedVaginally, partner);
            removeActivity(Project.AnalPenetrate, partner);
            removeActivity(Project.PenetratedAnally, partner);
        }
        for(int i = 0; i < counterparts.length; i++)
            counterPartners[i].removeActivity(counterparts[i], this);

    }

    public void freeBodyPart(int freedPart)
    {
        for(int i = 0; i < inProgress.length; i++)
            if(inProgress[i].sendReqs[freedPart] > 0)
                removeActivity(inProgress[i], null);

    }

    public int getMorality()
    {
        if(forsakenOwner != null)
            return forsakenOwner.morality;
        if(chosenOwner != null)
            return chosenOwner.morality;
        else
            return 50;
    }

    public int getInnocence()
    {
        if(forsakenOwner != null)
            return forsakenOwner.innocence;
        if(chosenOwner != null)
            return chosenOwner.innocence;
        else
            return 50;
    }

    public int getConfidence()
    {
        if(forsakenOwner != null)
            return forsakenOwner.confidence;
        if(forsakenOwner != null)
            return chosenOwner.confidence;
        else
            return 50;
    }

    public int getDignity()
    {
        if(forsakenOwner != null)
            return forsakenOwner.dignity;
        if(chosenOwner != null)
            return chosenOwner.dignity;
        else
            return 50;
    }

    public int getHostility()
    {
        if(forsakenOwner != null)
            return forsakenOwner.flavorHostility();
        if(chosenOwner != null)
        {
            int value = (100 - chosenOwner.morality) / 3;
            if(chosenOwner.impregnated)
                value += 40;
            else
            if(chosenOwner.timesSlaughtered() > 0)
                value += 30;
            else
            if(!chosenOwner.vVirg)
                value += 20;
            else
            if(chosenOwner.ruthless)
                value += 10;
            return value;
        } else
        {
            return 50;
        }
    }

    public int getDeviancy()
    {
        if(forsakenOwner != null)
            return forsakenOwner.flavorDeviancy();
        if(chosenOwner != null)
        {
            int value = (100 - chosenOwner.innocence) / 3;
            if(chosenOwner.hypnotized)
                value += 40;
            else
            if(chosenOwner.timesFantasized() > 0)
                value += 30;
            else
            if(!chosenOwner.cVirg)
                value += 20;
            else
            if(chosenOwner.lustful)
                value += 10;
            return value;
        } else
        {
            return 50;
        }
    }

    public int getObedience()
    {
        if(forsakenOwner != null)
            return forsakenOwner.flavorObedience();
        if(chosenOwner != null)
        {
            int value = (100 - chosenOwner.confidence) / 3;
            if(chosenOwner.drained)
                value += 40;
            else
            if(chosenOwner.timesDetonated() > 0)
                value += 30;
            else
            if(!chosenOwner.aVirg)
                value += 20;
            else
            if(chosenOwner.meek)
                value += 10;
            return value;
        } else
        {
            return 50;
        }
    }

    public int getDisgrace()
    {
        if(forsakenOwner != null)
            return forsakenOwner.disgrace;
        if(chosenOwner != null)
        {
            int value = (100 - chosenOwner.dignity) / 3;
            if(chosenOwner.parasitized)
                value += 40;
            else
            if(chosenOwner.timesStripped() > 0)
                value += 30;
            else
            if(!chosenOwner.modest)
                value += 20;
            else
            if(chosenOwner.debased)
                value += 10;
            return value;
        } else
        {
            return 50;
        }
    }

    public int getFEARLevel()
    {
        int value = 0;
        if(currentFEAR > 0L)
            value = (int)Math.log10(currentFEAR) - 1;
        if(value > 0)
            return value;
        else
            return 0;
    }

    public int getDISGLevel()
    {
        int value = 0;
        if(currentDISG > 0L)
            value = (int)Math.log10(currentDISG) - 1;
        if(value > 0)
            return value;
        else
            return 0;
    }

    public int getPAINLevel()
    {
        int value = 0;
        if(currentPAIN > 0L)
            value = (int)Math.log10(currentPAIN) - 1;
        if(value > 0)
            return value;
        else
            return 0;
    }

    public int getSHAMLevel()
    {
        int value = 0;
        if(currentSHAM > 0L)
            value = (int)Math.log10(currentSHAM) - 1;
        if(value > 0)
            return value;
        else
            return 0;
    }

    public int getHATELevel()
    {
        int value = 0;
        if(currentHATE > 0L)
            value = (int)Math.log10(currentHATE) - 1;
        if(value > 0)
            return value;
        else
            return 0;
    }

    public int getPLEALevel()
    {
        int value = 0;
        if(currentPLEA > 0L)
            value = (int)Math.log10(currentPLEA) - 1;
        if(value > 0)
            return value;
        else
            return 0;
    }

    public int getINJULevel()
    {
        int value = 0;
        if(currentINJU > 0L)
            value = (int)Math.log10(currentINJU) - 1;
        if(value > 0)
            return value;
        else
            return 0;
    }

    public int getEXPOLevel()
    {
        int value = 0;
        if(currentEXPO > 0L)
            value = (int)Math.log10(currentEXPO) - 1;
        if(value > 0)
            return value;
        else
            return 0;
    }

    public Boolean isParasitized()
    {
        if(forsakenOwner != null)
            return forsakenOwner.parasitized;
        if(chosenOwner != null)
            return chosenOwner.parasitized;
        else
            return false;
    }

    public Boolean hasBeenBroadcasted()
    {
        if(forsakenOwner != null)
            if(forsakenOwner.timesExposed >= 300_000)
                return true;
            else
                return false;
        if(chosenOwner != null)
        {
            if(chosenOwner.modest)
                return false;
            else
                return true;
        } else
        {
            return false;
        }
    }

    public Boolean isVVirg()
    {
        if(forsakenOwner != null)
            if(forsakenOwner.timesHadSex > 0)
                return false;
            else
                return true;
        if(chosenOwner != null)
            return chosenOwner.vVirg;
        else
            return false;
    }

    public Boolean isDemonLord()
    {
        if(chosenOwner == null && forsakenOwner == null)
            return true;
        else
            return false;
    }

    public String ownerName()
    {
        if(forsakenOwner != null)
            return forsakenOwner.mainName;
        if(chosenOwner != null)
            return chosenOwner.mainName;
        else
            return "the Demon Lord";
    }

    public String OwnerName()
    {
        return capitalizedOwnerName();
    }

    public String capitalizedOwnerName()
    {
        if(forsakenOwner != null || chosenOwner != null)
            return ownerName();
        else
            return "The Demon Lord";
    }

    public String breasts()
    {
        if(parts[CLEAVAGE] > 0)
            return "breasts";
        else
            return "chest";
    }

    public void say(JTextPane t, String s)
    {
        if(forsakenOwner != null)
            forsakenOwner.say(t, s);
        else
        if(chosenOwner != null)
            chosenOwner.say(t, s);
    }

    public int obedienceMod(WorldState w, Body subject)
    {
        int result = 0;
        if(forsakenOwner != null)
        {
            result += forsakenOwner.consentModifier();
            if(forsakenOwner.defeatType == 5 && forsakenOwner.obedience < 40)
                result += 50;
            else
            if(forsakenOwner.defeatType == 6)
                result += 40;
        }
        return result;
    }

    public String theDemonLord()
    {
        if(forsakenOwner != null)
            return forsakenOwner.theDemonLord();
        else
            return "the Demon Lord";
    }

    public String TheDemonLord()
    {
        if(forsakenOwner != null)
            return forsakenOwner.TheDemonLord();
        else
            return "The Demon Lord";
    }

    public String demonLord()
    {
        if(forsakenOwner != null)
            return forsakenOwner.demonLord;
        else
            return "Demon Lord";
    }

    public int friendsMod(WorldState w, Body subject)
    {
        int result = 0;
        if(forsakenOwner != null)
        {
            for(int i = 0; i < forsakenOwner.others.length; i++)
                if(forsakenOwner.forsakenRelations[i] == Forsaken.Relationship.PARTNER && forsakenOwner.opinion(forsakenOwner.others[i]) > -100)
                {
                    Boolean found = false;
                    int opinionClamp = forsakenOwner.opinion(forsakenOwner.others[i]) + 100;
                    if(opinionClamp > 500)
                        opinionClamp = 500;
                    for(int j = 0; j < w.getHarem().length; j++)
                    {
                        if(forsakenOwner.others[i].equals(w.getHarem()[j]))
                        {
                            found = true;
                            if(forsakenOwner.others[i].obedience > forsakenOwner.obedience)
                                opinionClamp = (opinionClamp * ((20 + forsakenOwner.obedience / 2) - forsakenOwner.others[i].obedience / 2)) / 500;
                            else
                                opinionClamp = (opinionClamp * 20) / 500;
                            if(opinionClamp < 0)
                                if(forsakenOwner.obedience < 80)
                                    opinionClamp = (opinionClamp * (80 - forsakenOwner.obedience)) / 80;
                                else
                                    opinionClamp = 0;
                        }
                        if(!found)
                        {
                            opinionClamp = (-30 * opinionClamp) / 500;
                            if(forsakenOwner.obedience > 80)
                                opinionClamp = 0;
                            else
                                opinionClamp = (opinionClamp * (80 - forsakenOwner.obedience)) / 80;
                        }
                    }

                    result += opinionClamp;
                }

        }
        return result;
    }

    public String mainOrgan()
    {
        if(parts[PENIS] > 0)
            return "cock";
        if(parts[PUSSY] > 0)
            return "pussy";
        else
            return "crotch";
    }

    public Body(Chosen c)
    {
        chosenOwner = null;
        forsakenOwner = null;
        inProgress = new Activity[0];
        targets = new Body[0];
        parts = new int[14];
        orgasms = 0;
        specialLine = 0;
        chosenOwner = c;
        parts[MOUTH] = 1;
        parts[HAND] = 2;
        parts[ASS] = 1;
        parts[FOOT] = 2;
        parts[ARMPIT] = 2;
        parts[KNEEPIT] = 2;
        parts[HAIR] = 1;
        parts[HIPS] = 1;
        parts[BACK] = 1;
        if(c.gender.equals("male"))
        {
            bodyType = Appearance.CUTEBOY;
            parts[PENIS] = 1;
            parts[BALLS] = 1;
        } else
        if(c.gender.equals("female"))
        {
            bodyType = Appearance.CUTEGIRL;
            parts[PUSSY] = 1;
            parts[CLIT] = 1;
            parts[CLEAVAGE] = 1;
        } else
        if(c.gender.equals("futanari"))
        {
            bodyType = Appearance.CUTEGIRL;
            parts[PUSSY] = 1;
            parts[PENIS] = 1;
            parts[CLEAVAGE] = 1;
        }
    }

    public Body(Forsaken x)
    {
        chosenOwner = null;
        forsakenOwner = null;
        inProgress = new Activity[0];
        targets = new Body[0];
        parts = new int[14];
        orgasms = 0;
        specialLine = 0;
        forsakenOwner = x;
        parts[MOUTH] = 1;
        parts[HAND] = 2;
        parts[ASS] = 1;
        parts[FOOT] = 2;
        parts[ARMPIT] = 2;
        parts[KNEEPIT] = 2;
        parts[HAIR] = 1;
        parts[HIPS] = 1;
        parts[BACK] = 1;
        if(x.gender == Forsaken.Gender.MALE)
        {
            bodyType = Appearance.CUTEBOY;
            parts[PENIS] = 1;
            parts[BALLS] = 1;
        } else
        if(x.gender == Forsaken.Gender.FEMALE)
        {
            bodyType = Appearance.CUTEGIRL;
            parts[PUSSY] = 1;
            parts[CLIT] = 1;
            parts[CLEAVAGE] = 1;
        } else
        if(x.gender == Forsaken.Gender.FUTANARI)
        {
            bodyType = Appearance.CUTEGIRL;
            parts[PUSSY] = 1;
            parts[PENIS] = 1;
            parts[CLEAVAGE] = 1;
        }
    }

    public Body(WorldState w)
    {
        chosenOwner = null;
        forsakenOwner = null;
        inProgress = new Activity[0];
        targets = new Body[0];
        parts = new int[14];
        orgasms = 0;
        specialLine = 0;
    }

    public Body()
    {
        chosenOwner = null;
        forsakenOwner = null;
        inProgress = new Activity[0];
        targets = new Body[0];
        parts = new int[14];
        orgasms = 0;
        specialLine = 0;
    }

    private static final long serialVersionUID = 4L;
    Chosen chosenOwner;
    Forsaken forsakenOwner;
    transient Activity inProgress[];
    Body targets[];
    public Appearance bodyType;
    public int parts[];
    public static int MOUTH = 0;
    public static int HAND = 1;
    public static int CLEAVAGE = 2;
    public static int PENIS = 3;
    public static int CLIT = 4;
    public static int PUSSY = 5;
    public static int BALLS = 6;
    public static int ASS = 7;
    public static int FOOT = 8;
    public static int ARMPIT = 9;
    public static int KNEEPIT = 10;
    public static int HAIR = 11;
    public static int HIPS = 12;
    public static int BACK = 13;
    public long currentFEAR;
    public long currentDISG;
    public long currentPAIN;
    public long currentSHAM;
    public long currentHATE;
    public long currentPLEA;
    public long currentINJU;
    public long currentEXPO;
    int orgasms;
    int specialLine;

}
