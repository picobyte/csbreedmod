
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
                    incoming[i] *= 3L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                for(long magnitude = currentPAIN; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] *= 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                incoming[i] = (incoming[i] * (long)(150 - getConfidence()) * (long)(100 + getObedience())) / 15000L;
                for(long magnitude = currentINJU; magnitude >= 1000L;)
                {
                    magnitude /= 10L;
                    incoming[i] /= 4L;
                }

            } else
            if(i == 1 && incoming[i] > 0L)
            {
                for(long magnitude = currentDISG; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] *= 5L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                for(long magnitude = currentHATE; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] *= 2L;
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
                    incoming[i] *= 4L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                for(long magnitude = currentINJU; magnitude >= 1000L;)
                {
                    magnitude /= 10L;
                    incoming[i] /= 2L;
                }

            } else
            if(i == 3 && incoming[i] > 0L)
            {
                for(long magnitude = currentSHAM; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] *= 3L;
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
                    incoming[i] /= 2L;
                }

            } else
            if(i == 4 && incoming[i] > 0L)
            {
                long magnitude;
                for(magnitude = currentHATE; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] *= 5L;
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
                    incoming[i] *= 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = true;
                }

                magnitude = getObedience();
                for(incoming[i] = (incoming[i] * (long)(150 - getMorality()) * (long)(100 + getHostility()) * (long)(100 - getObedience())) / 750_000L; magnitude > 60L; incoming[i] = (incoming[i] * 9L) / 10L)
                    magnitude--;

                for(magnitude = currentINJU; magnitude >= 1000L;)
                {
                    magnitude /= 10L;
                    incoming[i] /= 3L;
                }

            } else
            if(i == 5 && incoming[i] > 0L)
            {
                for(long magnitude = currentPLEA; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] *= 3L;
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
                    incoming[i] /= 4L;
                }

            } else
            if(i == 6 && incoming[i] > 0L)
            {
                for(long magnitude = currentINJU; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] *= 5L;
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
                    incoming[i] *= 10L;
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

        w.append(t, String.format("\n\n%s", w.getSeparator()));
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
                    w.append(t, String.format("\n\n%s", w.getSeparator()));
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
                w.append(t, String.format("\n\n%s", w.getSeparator()));
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
                    w.append(t, String.format("\n\n%s\n\n", w.getSeparator()));
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
                    w.append(t, String.format("\n\n%s\n\n", w.getSeparator()));
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
                    w.append(t, String.format("\n\n%s\n\n", w.getSeparator()));
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
                w.append(t, String.format("\n\n%s", w.getSeparator()));
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
                    w.append(t, String.format("\n\n%s\n\n", w.getSeparator()));
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
                    w.append(t, String.format("\n\n%s\n\n", w.getSeparator()));
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
                    w.append(t, String.format("\n\n%s\n\n", w.getSeparator()));
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
                w.append(t, String.format("\n\n%s", w.getSeparator()));
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
                        w.append(t, String.format("\n\n%s\n\n", w.getSeparator()));
                        Project.VaginalPenetrate.startActivity(t, w, w.lordBody, w.targetBody);
                        w.append(t, "\n\n");
                        Project.PenetratedVaginally.activityTalk(t, w, w.targetBody, w.lordBody);
                        Continue(t, p, f, w, s);
                    }
                });
                p.add(Missionary);
            } else
            {
                w.append(t, String.format("\n\n%s\n\n%s's Sexual Barrier prevents vaginal penetration.", w.getSeparator(), w.targetBody.OwnerName()));
            }
        if(Project.PenetratedVaginally.valid(w.lordBody, w.targetBody))
        {
            JButton Cowgirl = new JButton("Cowgirl Vaginal");
            Cowgirl.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, String.format("\n\n%s\n\n", w.getSeparator()));
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
                        w.append(t, String.format("\n\n%s\n\n", w.getSeparator()));
                        Project.AnalPenetrate.startActivity(t, w, w.lordBody, w.targetBody);
                        w.append(t, "\n\n");
                        Project.PenetratedAnally.activityTalk(t, w, w.targetBody, w.lordBody);
                        Continue(t, p, f, w, s);
                    }
                });
                p.add(Missionary);
            } else
            {
                w.append(t, String.format("\n\n%s\n\n%s's Sexual Barrier prevents anal penetration.", w.getSeparator(), w.targetBody.OwnerName()));
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
                    w.append(t, String.format("\n\n%s\n\n", w.getSeparator()));
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
                    w.append(t, String.format("\n\n%s\n\n", w.getSeparator()));
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
                w.append(t, String.format("\n\n%s", w.getSeparator()));
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
            w.append(t, String.valueOf(w.getSeparator()) + "\n\n");
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
                        w.append(t, String.format("%s is waiting eagerly in %s room when %s arrives, ", w.targetBody.OwnerName(), w.targetBody.hisHer(), w.lordBody.ownerName()));
                        switch (w.targetBody.getDeviancy() / 33) {
                        case 0:
                           w.append(t, String.format("shifting nervously from foot to foot.  %s knows roughly what will be expected of %s, but %s's unsure whether %3$s'll be able to perform.", w.targetBody.HeShe(), w.targetBody.himHer(), w.targetBody.heShe()));
                           break;
                        case 1:
                           w.append(t, String.format("wearing nothing but a thin robe which %s quickly drops to the floor as %1$s welcomes %s.  An eager smile is on %s's face, and %s cheeks are flushed with arousal.", w.targetBody.heShe(), w.lordBody.himHer(), w.targetBody.ownerName(), w.targetBody.hisHer()));
                            w.targetBody.currentEXPO = 1_000_000L;
                           break;
                        default:
                            w.append(t, String.format("already naked and unable to stop %sself from masturbating as %s anticipates what's to come.  When %2$s sees %s, %2$s scrambles to %s feet, panting with unrestrained desire.", w.targetBody.himHer(), w.targetBody.heShe(), w.lordBody.ownerName(), w.targetBody.hisHer()));
                            w.targetBody.currentEXPO = 1_000_000L;
                        }
                        w.targetBody.currentPLEA = (w.targetBody.getObedience() * w.targetBody.getDeviancy()) / 10;
                    } else
                    if(w.targetBody.getObedience() > 33)
                    {
                        switch (w.targetBody.getDeviancy() / 33) {
                        case 0:
                           w.append(t, String.format("%s reluctantly allows %s into %s room.  %s's clearly resigned to what %s's going to be forced to do.", w.targetBody.OwnerName(), w.lordBody.ownerName(), w.targetBody.hisHer(), w.targetBody.HeShe(), w.targetBody.heShe()));
                            w.targetBody.currentFEAR = 100 - w.targetBody.getConfidence();
                           break;
                        case 1:
                            w.append(t, String.format("%s welcomes %s into %s room, then stands obediently at attention.  Only the slight flush in %3$s cheeks betrays the fact that %s might be hoping for this encounter to turn intimate.", w.targetBody.capitalizedOwnerName(), w.lordBody.ownerName(), w.targetBody.hisHer(), w.targetBody.heShe()));
                           w.targetBody.currentPLEA = (w.targetBody.getDeviancy() * w.targetBody.getDeviancy()) / 100;
                           break;
                        default:
                           w.append(t, String.format("%s is masturbating when %s arrives, and %s looks almost annoyed at being interrupted.  But as %3$s remembers the possibilities offered by a partner, %3$s cheers up and sheds %s clothes, offering %sself to %2$s.", w.targetBody.capitalizedOwnerName(), w.lordBody.ownerName(), w.targetBody.heShe(), w.targetBody.hisHer(), w.targetBody.himHer()));
                           w.targetBody.currentPLEA = (w.targetBody.getDeviancy() * w.targetBody.getDeviancy()) / 20;
                           w.targetBody.currentEXPO = 1_000_000L;
                        }
                        w.targetBody.currentHATE = ((100 - w.targetBody.getDeviancy()) * (100 - w.targetBody.getObedience())) / 100;
                    } else
                    {
                        switch (w.targetBody.getDeviancy() / 33) {
                        case 0:
                           w.append(t, String.format("%s waits in %s room with %2$s arms crossed.  %s doesn't even acknowledge %s entering the room.", w.targetBody.OwnerName(), w.targetBody.hisHer(), w.targetBody.HeShe(), w.lordBody.ownerName()));
                           break;
                        case 1:
                           w.append(t, String.format("When %s enters %s room, %s dares %s to do %s worst.  The growing perverted side of %s is secretly looking forward to it.", w.lordBody.ownerName(), w.targetBody.hisHer(), w.targetBody.ownerName(), w.lordBody.himHer(), w.lordBody.hisHer(), w.targetBody.ownerName()));
                           w.targetBody.currentPLEA = (w.targetBody.getDeviancy() * w.targetBody.getObedience()) / 40;
                           break;
                        default:
                           w.append(t, String.format("%s is trembling with arousal as %s enters %s room, but %s still tries to glare at %s, denying how turned on %s is at being at the mercy of %s hated enemy.", w.targetBody.OwnerName(), w.lordBody.ownerName(), w.targetBody.hisHer(), w.targetBody.heShe(), w.lordBody.himHer(), w.targetBody.heShe(), w.targetBody.hisHer()));
                           w.targetBody.currentPLEA = (w.targetBody.getDeviancy() * w.targetBody.getDeviancy()) / 40;
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
                            w.append(t, String.format("%s is already trembling with arousal as %s joins %s on stage in front of a cheering crowd of Thralls, ", w.targetBody.OwnerName(), w.targetBody.heShe(), w.lordBody.ownerName()));
                            if(w.targetBody.parts[PENIS] > 0)
                                w.append(t, String.format("struggling with the urge to undress and start stroking %s straining penis right there.", w.targetBody.hisHer()));
                            else
                                w.append(t, String.format("%s wetness dripping down %1$s thighs.", w.targetBody.hisHer()));
                            w.append(t, String.format("  The prospect of being used right there in public is incredibly arousing to %s.", w.targetBody.himHer()));
                        } else
                        if(w.targetBody.getDeviancy() > 33)
                            w.append(t, String.format("%s waves happily to the surrounding Thralls as %s joins %s on the stage.  %s's proud to have the chance to show everyone how deeply %2$s belongs to %s.", w.targetBody.OwnerName(), w.targetBody.heShe(), w.lordBody.ownerName(), w.targetBody.HeShe(), w.lordBody.ownerName()));
                        else
                            w.append(t, String.format("%s looks nervous as %s makes %s way through the Thralls in order to take the stage with %s.  %s's willing to be humiliated for %s, but %2$s's ashamed to show %s unfamiliarity with the sexual activities %2$s'll surely be asked to perform.", w.targetBody.OwnerName(), w.targetBody.heShe(), w.targetBody.hisHer(), w.lordBody.ownerName(), w.targetBody.HeShe(), w.lordBody.himHer(), w.targetBody.hisHer()));
                        w.targetBody.currentPLEA += (w.targetBody.getObedience() * w.targetBody.getDeviancy() * (100 + w.targetBody.getDignity())) / 1000;
                        w.targetBody.currentSHAM += (w.targetBody.getDignity() * (100 - w.targetBody.getDeviancy())) / 50;
                    } else
                    if(w.targetBody.getObedience() > 33)
                    {
                        switch (w.targetBody.getHostility() / 33) {
                            case 0: w.append(t, String.format("%s seems a bit intimidated by the crowd of Thralls, but %s still willingly joins %s on the stage and tells everyone that %2$s hopes they enjoy the show.", w.targetBody.OwnerName(), w.targetBody.heShe(), w.lordBody.ownerName())); break;
                            case 1: w.append(t, String.format("%s obediently joins %s on stage, sparing only the occasional annoyed glance at the crowd of Thralls who shout out suggestions for ways %s should be humiliated.  %s waits to see what will be done with %s.", w.targetBody.OwnerName(), w.lordBody.ownerName(), w.targetBody.heShe(), w.targetBody.HeShe(), w.targetBody.himHer())); break;
                            default: w.append(t, String.format("%s stands on the stage, glaring angrily at the surrounding Thralls who jeer and takes pictures of %s, but %s still bows %s head respectfully as %s joins %2$s.", w.targetBody.OwnerName(), w.targetBody.himHer(), w.targetBody.heShe(), w.targetBody.hisHer(), w.lordBody.ownerName()));
                        }
                        w.targetBody.currentHATE += (50 + w.targetBody.getHostility()) - w.targetBody.getObedience();
                        if(w.targetBody.currentHATE < 0L)
                            w.targetBody.currentHATE = 0L;
                        w.targetBody.currentFEAR = 100 - w.targetBody.getConfidence();
                        w.targetBody.currentPLEA += (w.targetBody.getDeviancy() * w.targetBody.getDeviancy()) / 100;
                    } else
                    {
                        switch (w.targetBody.getConfidence() / 33) {
                            case 0: w.append(t, String.format("%s is trembling with fear as %s allows a pair of Thralls to escort %s onto the stage with %s, flinching at every crude comment and threat shouted from the crowd.  ", w.targetBody.OwnerName(), w.targetBody.heShe(), w.targetBody.himHer(), w.lordBody.ownerName())); break;
                            case 1: w.append(t, String.format("On a stage with %s in front of a crowd that roars with eagerness to see %s raped, %s tries to remain calm, but %s feels %s heart pounding in %5$s chest.  ", w.lordBody.ownerName(), w.targetBody.himHer(), w.targetBody.ownerName(), w.targetBody.heShe(), w.targetBody.hisHer())); break;
                            default: w.append(t, String.format("%s has to be dragged onto the stage by force, kicking and shouting, and when %s's finally thrown in front of %s, %2$s glares up in angry defiance.  ", w.targetBody.OwnerName(), w.targetBody.heShe(), w.lordBody.ownerName()));
                        }
                        w.targetBody.currentFEAR = ((100 - w.targetBody.getConfidence()) * (100 - w.targetBody.getDignity())) / 50;
                        w.targetBody.currentHATE = ((100 - w.targetBody.getObedience()) * (100 - w.targetBody.getDignity())) / 50;
                        w.targetBody.currentPLEA = (w.targetBody.getDeviancy() * (100 - w.targetBody.getDignity())) / 50;
                        if(!w.targetBody.hasBeenBroadcasted())
                        {
                            w.targetBody.currentFEAR *= 3L;
                            w.append(t, String.format("%s sees filming phones in the crowd, and %s knows that what happens here could change how people see %s forever.", w.targetBody.HeShe(), w.targetBody.heShe(), w.targetBody.himHer()));
                        } else
                        switch (w.targetBody.getDeviancy() / 33) {
                            case 0: w.append(t, String.format("%s hates everything about this situation.", w.targetBody.HeShe())); break;
                            case 1: w.append(t, String.format("%s tries not to admit to %sself just how much this is turning %2$s on.", w.targetBody.HeShe(), w.targetBody.himHer())); break;
                            default: w.append(t, String.format("And yet, as always, %s body aches to be savagely fucked right there in front of everyone.", w.targetBody.hisHer()));
                        }
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
            w.append(t, String.format("How will %s act?", ownerName()));
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
                        w.append(t, String.format("\n\n%s\n\nWhich action will you cancel?", w.getSeparator()));
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
            switch (i) {
            case 0: w.append(t, "\nFEAR ["); break;
            case 1: w.append(t, "\nDISG ["); break;
            case 2: w.append(t, "\nPAIN ["); break;
            case 3: w.append(t, "\nSHAM ["); break;
            case 4: w.append(t, "\n\nHATE ["); break;
            case 5: w.append(t, "\nPLEA ["); break;
            case 6: w.append(t, "\nTIRE ["); break;
            default: w.append(t, "\nEXPO [");
            }
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
                    switch (tier) {
                    case 0: w.append(t, String.valueOf(fillChar)); break;
                    case 1: w.inverseAppend(t, String.valueOf(fillChar)); break;
                    default: w.tierTwoAppend(t, String.valueOf(fillChar));
                    }
                } else
                if(tier == 0 || tier == 1 && mixed)
                    w.append(t, String.valueOf(remainChar));
                else
                if(tier == 1 || tier == 2 && mixed)
                    w.inverseAppend(t, String.valueOf(remainChar));
                else
                    w.tierTwoAppend(t, String.valueOf(remainChar));

            w.append(t, "] (" + (new Chosen()).fixedFormat(currentDamage()[i]));
            if(actualDamage[i] == 0L)
                w.append(t, "        ");
            else
                w.append(t, " + " + (new Chosen()).fixedFormat(actualDamage[i]));
            switch (i) {
            case 0: currentFEAR += actualDamage[i]; break;
            case 1: currentDISG += actualDamage[i]; break;
            case 2: currentPAIN += actualDamage[i]; break;
            case 3: currentSHAM += actualDamage[i]; break;
            case 4: currentHATE += actualDamage[i]; break;
            case 5: currentPLEA += actualDamage[i]; break;
            case 6: currentINJU += actualDamage[i]; break;
            case 7: currentEXPO += actualDamage[i];
            }
            w.append(t, " = " + (new Chosen()).fixedFormat(currentDamage()[i]) + ") ");
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
                        w.append(t, String.format("realizes how %s must look", heShe()));
                    else
                    if(currentDamage()[i] < 10000L)
                        w.append(t, "feels watching eyes");
                    else
                    if(currentDamage()[i] < 100_000L)
                        w.append(t, String.format("can't stop thinking about how %s looks", heShe()));
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
                        w.append(t, String.format("clothes torn across %s hips and chest", hisHer()));
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
                    w.append(t, String.format("%s's body is beginning to experience sexual arousal.", OwnerName()));
                else
                if(getPLEALevel() == 2)
                {
                    w.append(t, String.format("%s's ", OwnerName()));
                    if(parts[PENIS] > 0)
                        w.append(t, String.format("penis is twitching, straining as %s body approaches orgasm.", hisHer()));
                    else
                        w.append(t, String.format("whole body feels sensitive, pleasure pulsing in %s loins as orgasm approaches.", hisHer()));
                }
            } else
            if(!orgasmPossible())
            {
                w.purpleAppend(t, "\n\nPLEA up!  " + OwnerName() + " wildly bucks " + hisHer() + " hips in search of orgasm, but " + heShe() + " can't give " + himHer() + "self the necessary stimulation, and " + heShe() + " comes back from the brink of climax.");
            } else
            {
                w.purpleAppend(t, "\n\nOrgasm!  ");
                if(orgasms == 0)
                {
                    if(currentDamage()[5] - actualDamage[5] >= 10000L && currentDamage()[5] >= 30000L)
                    {
                        w.append(t, String.format("The pent-up climax comes all at once, and %s", ownerName()));
                        if(parts[PENIS] > 0)
                            w.append(t, "'s cock spurts a huge load in all directions.");
                        else
                            w.append(t, String.format(" cums so hard that %s feels weak and unsteady afterwards.", heShe()));
                    } else
                    {
                        w.append(t, String.format("%s's whole body shudders shudders as ", OwnerName()));
                        if(parts[PENIS] > 0)
                            w.append(t, String.format("%s cock shoots its load.", hisHer()));
                        else
                            w.append(t, String.format("%s climaxes.", heShe()));
                    }
                } else
                if(orgasms == 1)
                {
                    w.append(t, String.format("%s cums again, ", OwnerName()));
                    if(parts[PENIS] > 0)
                        w.append(t, "shooting a smaller load this time, but it feels even better.");
                    else
                        w.append(t, String.format("%s second climax proving even more intense than the first.", hisHer()));
                } else
                {
                    w.append(t, String.format("%s cums yet again, ", OwnerName()));
                    if(parts[PENIS] > 0)
                        w.append(t, String.format("%s exhausted penis managing only a trickle of cum, but even that much feels incredibly good.", hisHer()));
                    else
                        w.append(t, String.format("body spasming wildly, completely outside %s control.", hisHer()));
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
            damageTaken[3] *= 3L;
        long actualDamage[] = InflictDamage(damageTaken);
        for(int i = 0; i < 8; i++)
        {
            if(i == 7 && currentDamage()[i] + actualDamage[i] > 1_000_000L)
            {
                actualDamage[i] = 1_000_000L - currentDamage()[i];
                removeActivity(Project.Stripped, null);
            }
            switch (i) {
            case 0: w.append(t, "\nFEAR ["); break;
            case 1: w.append(t, "\nDISG ["); break;
            case 2: w.append(t, "\nPAIN ["); break;
            case 3: w.append(t, "\nSHAM ["); break;
            case 4: w.append(t, "\n\nHATE ["); break;
            case 5: w.append(t, "\nPLEA ["); break;
            case 6: w.append(t, "\nTIRE ["); break;
            default: w.append(t, "\nEXPO [");
            }
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
                    switch (tier) {
                    case 0: w.append(t, String.valueOf(fillChar)); break;
                    case 1: w.inverseAppend(t, String.valueOf(fillChar)); break;
                    default: w.tierTwoAppend(t, String.valueOf(fillChar));
                    }
                } else
                if(tier == 0 || tier == 1 && mixed)
                    w.append(t, String.valueOf(remainChar));
                else
                if(tier == 1 || tier == 2 && mixed)
                    w.inverseAppend(t, String.valueOf(remainChar));
                else
                    w.tierTwoAppend(t, String.valueOf(remainChar));

            w.append(t, "] (" + (new Chosen()).fixedFormat(currentDamage()[i]));
            if(actualDamage[i] == 0L)
                w.append(t, "        ");
            else
                w.append(t, " + " + (new Chosen()).fixedFormat(actualDamage[i]));
            switch (i) {
            case 0: currentFEAR += actualDamage[i]; break;
            case 1: currentDISG += actualDamage[i]; break;
            case 2: currentPAIN += actualDamage[i]; break;
            case 3: currentSHAM += actualDamage[i]; break;
            case 4: currentHATE += actualDamage[i]; break;
            case 5: currentPLEA += actualDamage[i]; break;
            case 6: currentINJU += actualDamage[i]; break;
            case 7: currentEXPO += actualDamage[i];
            }
            w.append(t, " = " + (new Chosen()).fixedFormat(currentDamage()[i]) + ") ");
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
                        w.append(t, String.format("realizes how %s must look", heShe()));
                    else
                    if(currentDamage()[i] < 10000L)
                        w.append(t, "feels watching eyes");
                    else
                    if(currentDamage()[i] < 100_000L)
                        w.append(t, String.format("can't stop thinking about how %s looks", heShe()));
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
                        w.append(t, String.format("clothes torn across %s hips and chest", hisHer()));
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
            switch (crossedThreshold(actualDamage[0], currentDamage()[0])) {
            case 1:
                w.append(t, String.format("%s can't help but think of the consquences of displeasing %s.", OwnerName(), w.lordBody.ownerName()));
            break;
            case 2:
                w.append(t, String.format("%s is desperate to please %s.", OwnerName(), w.lordBody.ownerName()));
            break;
            default:
                w.append(t, String.format("%s is overcome by %s terror of %s.", OwnerName(), hisHer(), w.lordBody.ownerName()));
            }
        }
        if(crossedThreshold(actualDamage[1], currentDamage()[1]) > 0)
        {
            w.purpleAppend(t, "\n\nDISG up!  ");
            switch (crossedThreshold(actualDamage[1], currentDamage()[1])) {
            case 1:
                w.append(t, String.format("%s has started to feel bad about what %s's doing.", OwnerName(), heShe()));
            break;
            case 2:
                w.append(t, String.format("%s has firmly crossed the line into activities %s's not comfortable with.", OwnerName(), heShe()));
            break;
            default:
                w.append(t, String.format("It's a struggle for %s to force %sself to continue.", ownerName(), himHer()));
            }
        }
        if(crossedThreshold(actualDamage[2], currentDamage()[2]) > 0)
        {
            w.purpleAppend(t, "\n\nPAIN up!  ");
            switch (crossedThreshold(actualDamage[2], currentDamage()[2])) {
            case 1:
                w.append(t, String.format("%s consciously notices that %s's feeling pain.", OwnerName(), heShe()));
            break;
            case 2:
                w.append(t, String.format("%s can't stop %sself from flinching away from the pain.", OwnerName(), himHer()));
            break;
            default:
                w.append(t, String.format("%s is in so much pain that %s can hardly focus.", OwnerName(), heShe()));
            }
        }
        if(crossedThreshold(actualDamage[3], currentDamage()[3]) > 0)
        {
            w.purpleAppend(t, "\n\nSHAM up!  ");
            switch (crossedThreshold(actualDamage[3], currentDamage()[3])) {
            case 1:
                w.append(t, String.format("%s has realized how lascivious %s appearance has become.", OwnerName(), hisHer()));
            break;
            case 2:
                w.append(t, String.format("%s is becoming increasingly self-conscious.", OwnerName()));
            break;
            default:
                w.append(t, String.format("%s can't stop thinking about how %s must look.", OwnerName(), heShe()));
            }
        }
        if(crossedThreshold(actualDamage[4], currentDamage()[4]) > 0)
        {
            w.purpleAppend(t, "\n\nHATE up!  ");
            if(crossedThreshold(actualDamage[4], currentDamage()[4]) == 1)
                w.append(t, String.format("%s is growing annoyed with the situation.", OwnerName()));
            else
            if(crossedThreshold(actualDamage[4], currentDamage()[4]) == 2)
            {
                w.append(t, String.format("%s is angry at %s.", OwnerName(), w.lordBody.ownerName()));
            } else
            {
                w.append(t, String.format("%s is consumed by hatred.", OwnerName()));
                if(currentDamage()[4] - actualDamage[4] < 10000L)
                    w.append(t, String.format("  %s Sexual Barrier fades away.", HisHer()));
            }
        }
        if(crossedThreshold(actualDamage[5], currentDamage()[5]) > 0 || orgasmLevel(actualDamage[5], currentDamage()[5]) > 0)
            if(getPLEALevel() < 3)
            {
                w.purpleAppend(t, "\n\nPLEA up!  ");
                if(getPLEALevel() == 1)
                    w.append(t, String.format("%s is starting to get turned on.", OwnerName()));
                else
                if(getPLEALevel() == 2)
                {
                    w.append(t, String.format("%s's ", OwnerName()));
                    if(parts[PENIS] > 0)
                        w.append(t, String.format("penis is twitching, straining as %s approaches orgasm.", heShe()));
                    else
                        w.append(t, String.format("whole body feels sensitive, pleasure pulsing in %s loins as orgasm approaches.", hisHer()));
                }
            } else
            if(!orgasmPossible())
            {
                w.purpleAppend(t, "\n\nPLEA up!  " + OwnerName() + " wildly bucks " + hisHer() + " hips in search of orgasm, but " + heShe() + " can't give " + himHer() + "self the necessary stimulation, and " + heShe() + " comes back from the brink of climax.");
            } else
            {
                w.purpleAppend(t, "\n\nOrgasm!  ");
                int intensity = 200 + (int)(Math.random() * 200D);
                if(orgasms == 0)
                {
                    if(currentDamage()[5] - actualDamage[5] >= 10000L && currentDamage()[5] >= 30000L)
                    {
                        w.append(t, String.format("The pent-up climax comes all at once, and %s squeals incoherently as ", ownerName()));
                        if(parts[PENIS] > 0)
                            w.append(t, String.format("%s cock spurts a huge load in all directions.", hisHer()));
                        else
                            w.append(t, String.format("%s cums almost hard enough to make %s pass out.", heShe(), himHer()));
                    } else
                    {
                        intensity = 450 * (int)(Math.random() * 450D);
                        w.append(t, String.format("%s shudders as ", OwnerName()));
                        if(parts[PENIS] > 0)
                            w.append(t, String.format("%s cock shoots its load.", hisHer()));
                        else
                            w.append(t, String.format("%s climaxes.", heShe()));
                    }
                } else
                {
                    intensity = 300 + (int)(Math.random() * 300D);
                    if(orgasms == 1)
                    {
                        w.append(t, String.format("%s cums again, ", OwnerName()));
                        if(parts[PENIS] > 0)
                            w.append(t, "shooting a smaller load this time, but it feels even better.");
                        else
                            w.append(t, "moaning and gasping for breath as the second climax proves even more intense than the first.");
                    } else
                    {
                        w.append(t, String.format("%s cums yet again, ", OwnerName()));
                        if(parts[PENIS] > 0)
                            w.append(t, String.format("%s exhausted penis managing only a trickle of cum, but even that much is enough to make %s groan with unbearable pleasure.", hisHer(), himHer()));
                        else
                            w.append(t, String.format("body spasming wildly, %s eyes wide and staring off into the distance as %1$s vision flashes white.", hisHer()));
                    }
                }
                orgasms++;
                if(forsakenOwner != null)
                {
                    if(forsakenOwner.timesOrgasmed == 0)
                    {
                        specialLine = 2;
                        w.append(t, String.format("  It's %s first time experiencing orgasm.", hisHer()));
                    } else
                    if(forsakenOwner.strongestOrgasm < 200)
                    {
                        specialLine = 2;
                        w.append(t, String.format("  It's by far the strongest orgasm %s's had in %s life.", heShe(), hisHer()));
                    }
                    forsakenOwner.timesOrgasmed++;
                    if(intensity > forsakenOwner.strongestOrgasm)
                        forsakenOwner.strongestOrgasm = intensity;
                }
            }
        if(crossedThreshold(actualDamage[6], currentDamage()[6]) > 0)
        {
            w.purpleAppend(t, "\n\nTIRE up!  ");
            switch (crossedThreshold(actualDamage[6], currentDamage()[6])) {
            case 1:
                w.append(t, String.format("%s is starting to get tired, but it's not slowing %s down yet.", OwnerName(), himHer()));
            break;
            case 2:
                w.append(t, String.format("%s's fatigue is catching up with %s, and %s's losing focus.", OwnerName(), himHer(), heShe()));
            break;
            case 3:
                w.append(t, String.format("%s is practically passing out.  %s can't do much like this.", OwnerName(), HeShe()));
            break;
            default:
                w.append(t, String.format("%s fades in and out of consciousness.", OwnerName()));
            }
        }
        if(crossedThreshold(actualDamage[7], currentDamage()[7]) > 0)
        {
            w.purpleAppend(t, "\n\nEXPO up!  ");
            switch (crossedThreshold(actualDamage[7], currentDamage()[7])) {
            case 1: w.append(t, String.format("%s's clothes are partially undone.", OwnerName())); break;
            case 2: w.append(t, String.format("%s's %s can clearly be seen.", OwnerName(), breasts())); break;
            case 3:
                w.append(t, String.format("%s's clothes have been shifted out of the way to show %s most private places.", OwnerName(), hisHer()));
                break;
            case 4: w.append(t, String.format("Only a few scraps of %s's clothes remain.", OwnerName())); break;
            default: w.append(t, String.format("%s is now completely naked.", OwnerName()));
            }
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
        return currentLevel > previousLevel ? currentLevel : 0;
    }

    public Forsaken.Gender appearanceGender(Appearance seen)
    {
        if(seen == Appearance.CUTEGIRL)
            return Forsaken.Gender.FEMALE;
        return seen == Appearance.CUTEBOY ? Forsaken.Gender.MALE : null;
    }

    public String heShe()
    {
        if(appearanceGender(bodyType) == Forsaken.Gender.FEMALE)
            return "she";
        return appearanceGender(bodyType) == Forsaken.Gender.MALE ? "he" : "it";
    }

    public String hisHer()
    {
        if(appearanceGender(bodyType) == Forsaken.Gender.FEMALE)
            return "her";
        return appearanceGender(bodyType) == Forsaken.Gender.MALE ? "his" : "its";
    }

    public String himHer()
    {
        if(appearanceGender(bodyType) == Forsaken.Gender.FEMALE)
            return "her";
        return appearanceGender(bodyType) == Forsaken.Gender.MALE ? "him" : "it";
    }

    public String HeShe()
    {
        if(appearanceGender(bodyType) == Forsaken.Gender.FEMALE)
            return "She";
        return appearanceGender(bodyType) == Forsaken.Gender.MALE ? "He" : "It";
    }

    public String HisHer()
    {
        if(appearanceGender(bodyType) == Forsaken.Gender.FEMALE)
            return "Her";
        return appearanceGender(bodyType) == Forsaken.Gender.MALE ? "His" : "Its";
    }

    public String HimHer()
    {
        if(appearanceGender(bodyType) == Forsaken.Gender.FEMALE)
            return "Her";
        return appearanceGender(bodyType) == Forsaken.Gender.MALE ? "Him" : "It";
    }

    public Forsaken.Gender getGender()
    {
        if(forsakenOwner != null)
            return forsakenOwner.gender;
        if(chosenOwner != null)
        {
            if(chosenOwner.gender.equals("male"))
                return Forsaken.Gender.MALE;
            return chosenOwner.gender.equals("female") ? Forsaken.Gender.FEMALE : Forsaken.Gender.FUTANARI;
        }
        if(appearanceGender(bodyType) == Forsaken.Gender.FEMALE)
        {
            return parts[PENIS] > 0 ? Forsaken.Gender.FUTANARI : Forsaken.Gender.FEMALE;
        } else
        {
            return Forsaken.Gender.MALE;
        }
    }

    public Chosen.Species getType()
    {
        if(forsakenOwner != null)
            return forsakenOwner.type;
        return chosenOwner != null ? chosenOwner.type : null;
    }

    public Boolean isForsaken()
    {
        return forsakenOwner != null;
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
        return chosenOwner != null ? chosenOwner.morality : 50;
    }

    public int getInnocence()
    {
        if(forsakenOwner != null)
            return forsakenOwner.innocence;
        return chosenOwner != null ? chosenOwner.innocence : 50;
    }

    public int getConfidence()
    {
        if(forsakenOwner != null)
            return forsakenOwner.confidence;
        return forsakenOwner != null ? chosenOwner.confidence : 50;
    }

    public int getDignity()
    {
        if(forsakenOwner != null)
            return forsakenOwner.dignity;
        return chosenOwner != null ? chosenOwner.dignity : 50;
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
        return value > 0 ? value : 0;
    }

    public int getDISGLevel()
    {
        int value = 0;
        if(currentDISG > 0L)
            value = (int)Math.log10(currentDISG) - 1;
        return value > 0 ? value : 0;
    }

    public int getPAINLevel()
    {
        int value = 0;
        if(currentPAIN > 0L)
            value = (int)Math.log10(currentPAIN) - 1;
        return value > 0 ? value : 0;
    }

    public int getSHAMLevel()
    {
        int value = 0;
        if(currentSHAM > 0L)
            value = (int)Math.log10(currentSHAM) - 1;
        return value > 0 ? value : 0;
    }

    public int getHATELevel()
    {
        int value = 0;
        if(currentHATE > 0L)
            value = (int)Math.log10(currentHATE) - 1;
        return value > 0 ? value : 0;
    }

    public int getPLEALevel()
    {
        int value = 0;
        if(currentPLEA > 0L)
            value = (int)Math.log10(currentPLEA) - 1;
        return value > 0 ? value : 0;
    }

    public int getINJULevel()
    {
        int value = 0;
        if(currentINJU > 0L)
            value = (int)Math.log10(currentINJU) - 1;
        return value > 0 ? value : 0;
    }

    public int getEXPOLevel()
    {
        int value = 0;
        if(currentEXPO > 0L)
            value = (int)Math.log10(currentEXPO) - 1;
        return value > 0 ? value : 0;
    }

    public Boolean isParasitized()
    {
        if(forsakenOwner != null)
            return forsakenOwner.parasitized;
        return chosenOwner != null ? chosenOwner.parasitized : false;
    }

    public Boolean hasBeenBroadcasted()
    {
        if(forsakenOwner != null)
            return forsakenOwner.timesExposed >= 300_000;
        return chosenOwner != null ? !chosenOwner.modest : false;
    }

    public Boolean isVVirg()
    {
        if(forsakenOwner != null)
            return forsakenOwner.timesHadSex <= 0;
        return chosenOwner != null ? chosenOwner.vVirg : false;
    }

    public Boolean isDemonLord()
    {
        return chosenOwner == null && forsakenOwner == null;
    }

    public String ownerName()
    {
        if(forsakenOwner != null)
            return forsakenOwner.mainName;
        return chosenOwner != null ? chosenOwner.mainName : "the Demon Lord";
    }

    public String OwnerName()
    {
        return capitalizedOwnerName();
    }

    public String capitalizedOwnerName()
    {
        return forsakenOwner != null || chosenOwner != null ? ownerName() : "The Demon Lord";
    }

    public String breasts()
    {
        return parts[CLEAVAGE] > 0 ? "breasts" : "chest";
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
        return forsakenOwner != null ? forsakenOwner.theDemonLord() : "the Demon Lord";
    }

    public String TheDemonLord()
    {
        return forsakenOwner != null ? forsakenOwner.TheDemonLord() : "The Demon Lord";
    }

    public String demonLord()
    {
        return forsakenOwner != null ? forsakenOwner.demonLord : "Demon Lord";
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
        return parts[PUSSY] > 0 ? "pussy" : "crotch";
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
