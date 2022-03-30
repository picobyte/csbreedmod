
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.*;

public class Body
    implements Serializable
{
    public static final class Appearance extends Enum
    {

        public static Appearance[] values()
        {
            Appearance aappearance[];
            int i;
            Appearance aappearance1[];
            System.arraycopy(aappearance = ENUM$VALUES, 0, aappearance1 = new Appearance[i = aappearance.length], 0, i);
            return aappearance1;
        }

        public static Appearance valueOf(String s)
        {
            return (Appearance)Enum.valueOf(Body$Appearance, s);
        }

        public static final Appearance CUTEGIRL;
        public static final Appearance CUTEBOY;
        public static final Appearance MONSTER;
        private static final Appearance ENUM$VALUES[];

        static 
        {
            CUTEGIRL = new Appearance("CUTEGIRL", 0);
            CUTEBOY = new Appearance("CUTEBOY", 1);
            MONSTER = new Appearance("MONSTER", 2);
            ENUM$VALUES = (new Appearance[] {
                CUTEGIRL, CUTEBOY, MONSTER
            });
        }

        private Appearance(String s, int i)
        {
            super(s, i);
        }
    }


    public Boolean coerced()
    {
        if(!imprisoned.booleanValue() && (chosenOwner == null || !chosenOwner.truce.booleanValue() && !chosenOwner.drained.booleanValue()))
            return Boolean.valueOf(false);
        else
            return Boolean.valueOf(true);
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
            Boolean overflow = Boolean.valueOf(false);
            if(i == 0 && incoming[i] > 0L)
            {
                for(long magnitude = currentFEAR; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] * 3L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = Boolean.valueOf(true);
                }

                for(long magnitude = currentPAIN; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] * 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = Boolean.valueOf(true);
                }

                incoming[i] = (incoming[i] * (long)(150 - getConfidence()) * (long)(100 + getObedience())) / 15000L;
                for(long magnitude = currentINJU; magnitude >= 1000L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] / 4L;
                }

                if(forsakenOwner == null && chosenOwner != null)
                    incoming[i] = incoming[i] / 2L;
            } else
            if(i == 1 && incoming[i] > 0L)
            {
                for(long magnitude = currentDISG; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] * 5L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = Boolean.valueOf(true);
                }

                for(long magnitude = currentHATE; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] * 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = Boolean.valueOf(true);
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
                        overflow = Boolean.valueOf(true);
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
                        overflow = Boolean.valueOf(true);
                }

                for(long magnitude = currentEXPO; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 3L) / 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = Boolean.valueOf(true);
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
                if(forsakenOwner == null && chosenOwner != null)
                    if(chosenOwner.negotiations == 0)
                        incoming[i] = incoming[i] * 2L;
                    else
                    if(chosenOwner.truce.booleanValue())
                        incoming[i] = incoming[i] / 2L;
                long magnitude;
                for(magnitude = currentHATE; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] * 5L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = Boolean.valueOf(true);
                }

                for(magnitude = currentDISG; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 3L) / 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = Boolean.valueOf(true);
                }

                for(magnitude = currentPAIN; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 3L) / 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = Boolean.valueOf(true);
                }

                for(magnitude = currentSHAM; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 3L) / 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = Boolean.valueOf(true);
                }

                for(magnitude = currentINJU; magnitude >= 1000L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] * 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = Boolean.valueOf(true);
                }

                magnitude = getObedience();
                for(incoming[i] = (incoming[i] * (long)(150 - getMorality()) * (long)(100 + getHostility()) * (long)(100 - getObedience())) / 0xb71b0L; magnitude > 60L; incoming[i] = (incoming[i] * 9L) / 10L)
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
                        overflow = Boolean.valueOf(true);
                }

                for(long magnitude = currentSHAM; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * (long)(50 + getDeviancy())) / 50L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = Boolean.valueOf(true);
                }

                if(!isDemonLord().booleanValue())
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
                        overflow = Boolean.valueOf(true);
                }

                for(long magnitude = currentFEAR; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 4L) / 3L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = Boolean.valueOf(true);
                }

                for(long magnitude = currentDISG; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 4L) / 3L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = Boolean.valueOf(true);
                }

                for(long magnitude = currentPAIN; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 3L) / 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = Boolean.valueOf(true);
                }

                for(long magnitude = currentSHAM; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 4L) / 3L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = Boolean.valueOf(true);
                }

                for(long magnitude = currentHATE; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 4L) / 3L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = Boolean.valueOf(true);
                }

                for(long magnitude = currentPLEA; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = (incoming[i] * 3L) / 2L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = Boolean.valueOf(true);
                }

            } else
            if(i == 7 && incoming[i] > 0L)
            {
                for(long magnitude = currentEXPO; magnitude >= 100L;)
                {
                    magnitude /= 10L;
                    incoming[i] = incoming[i] * 10L;
                    if(incoming[i] > 0xd6bf94d5e5L)
                        overflow = Boolean.valueOf(true);
                }

            }
            if(overflow.booleanValue())
                incoming[i] = 0L;
        }

        return incoming;
    }

    public void advanceAction(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s)
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
        Boolean endScene = Boolean.valueOf(false);
        if(forsakenOwner == null && chosenOwner != null)
        {
            if(getHATELevel() >= 3)
                endScene = Boolean.valueOf(true);
            if(getPLEALevel() >= 3 && chosenOwner.innocence > 66 && chosenOwner.cVirg.booleanValue())
                endScene = Boolean.valueOf(true);
            if(Project.PenetratedAnally.isInProgress(this, null).booleanValue() && !Project.BeLubricated.isInProgress(this, null).booleanValue() && !chosenOwner.truce.booleanValue())
                endScene = Boolean.valueOf(true);
            if(chosenOwner.truce.booleanValue() || chosenOwner.drained.booleanValue())
            {
                if(getINJULevel() >= 4)
                    endScene = Boolean.valueOf(true);
            } else
            if(getINJULevel() >= 3)
                endScene = Boolean.valueOf(true);
        }
        if(endScene.booleanValue())
        {
            p.removeAll();
            w.append(t, "\n\n");
            if(getHATELevel() >= 3)
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" isn't able to tolerate ").append(w.lordBody.ownerName()).append("'s provocations any longer.  ").toString());
            else
            if(getPLEALevel() >= 3 && chosenOwner.innocence > 66 && chosenOwner.cVirg.booleanValue())
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" is overwhelmed by the unfamiliar pleasure surging through ").append(himHer()).append(", and ").append(heShe()).append(" acts without thinking.  ").toString());
            else
            if(Project.PenetratedAnally.isInProgress(this, null).booleanValue() && !Project.BeLubricated.isInProgress(this, null).booleanValue() && !chosenOwner.truce.booleanValue())
                w.append(t, (new StringBuilder("Even before ")).append(w.lordBody.ownerName()).append(" can get completely inside ").append(himHer()).append(", ").append(ownerName()).append(" puts a stop to it.  ").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" is too tired to continue, so ").append(heShe()).append(" decides to leave.  ").toString());
            if(Project.BeTied.isInProgress(this, null).booleanValue())
            {
                if(transformed.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" uses ").append(hisHer()).append(" superhuman strength to tear off ").append(hisHer()).append(" bindings, then ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" transforms into ").append(hisHer()).append(" Chosen form and uses ").append(hisHer()).append(" superhuman strength to tear off ").append(hisHer()).append(" bindings, then ").toString());
            } else
            {
                w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" ").toString());
            }
            if(chosenOwner.negotiations > 0 || chosenOwner.drained.booleanValue() || getINJULevel() >= 3)
            {
                if(w.sceneLocation == Activity.Location.ALLEY)
                    w.append(t, (new StringBuilder("makes ")).append(hisHer()).append(" escape, flying off into the sky.").toString());
                else
                if(w.sceneLocation == Activity.Location.ROOM)
                    w.append(t, (new StringBuilder("guides ")).append(w.lordBody.ownerName()).append(" to the door, apologetic but still flatly refusing to let ").append(w.lordBody.himHer()).append(" stay.").toString());
            } else
            {
                w.append(t, (new StringBuilder("strikes ")).append(w.lordBody.ownerName()).append(" down with ").append(hisHer()).append(" ").append(chosenOwner.weapon).append(".  As ").append(w.lordBody.hisHer()).append(" corporeal form has been destroyed, ").append(w.lordBody.ownerName()).append(" can only watch ").append(ownerName()).append(" fly away.").toString());
            }
            JButton Continue = new JButton("Continue");
            Continue.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    if(w.active.booleanValue())
                    {
                        Project.Shop(t, p, f, w);
                    } else
                    {
                        WriteObject wobj = new WriteObject();
                        wobj.serializeSaveData(s);
                        Project.IntroOne(t, p, f, w);
                    }
                }

                final Body this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
            });
            p.add(Continue);
            p.validate();
            p.repaint();
        } else
        {
            w.sceneParticipants[nextActor].PickActivity(t, p, f, w, s);
        }
    }

    public void Continue(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s)
    {
        p.removeAll();
        JButton Wait = new JButton("Continue");
        Wait.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                advanceAction(t, p, f, w, s);
            }

            final Body this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final SaveData val$s;

            
            {
                this$0 = Body.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                super();
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
        Project.Talk.pickable = Boolean.valueOf(false);
        Project.Talk.sendReqs[MOUTH] = 1;
        Project.TweakClit.counterpart = Project.ClitTweaked;
        Project.TweakClit.sendReqs[HAND] = 1;
        Project.TweakClit.targeted = Boolean.valueOf(true);
        Project.TweakClit.enders = (new Activity[] {
            Project.StopActing, Project.Escape
        });
        Project.ClitTweaked.counterpart = Project.TweakClit;
        Project.ClitTweaked.sendReqs[CLIT] = 1;
        Project.ClitTweaked.causesOrgasm = Boolean.valueOf(true);
        Project.ClitTweaked.targeted = Boolean.valueOf(true);
        Project.ClitTweaked.pickable = Boolean.valueOf(false);
        Project.ClitTweaked.enders = (new Activity[] {
            Project.Escape
        });
        Project.SpreadLegs.sendReqs[FOOT] = 2;
        Project.SpreadLegs.enders = (new Activity[] {
            Project.StopActing, Project.Escape
        });
        Project.SpreadLegs.pickable = Boolean.valueOf(false);
        Project.Praise.sendReqs[MOUTH] = 1;
        Project.Praise.targeted = Boolean.valueOf(true);
        Project.Praise.endsSelf = Boolean.valueOf(true);
        Project.Insult.sendReqs[MOUTH] = 1;
        Project.Insult.targeted = Boolean.valueOf(true);
        Project.Insult.endsSelf = Boolean.valueOf(true);
        Project.Insult.hostile = Boolean.valueOf(true);
        Project.PushDown.sendReqs[HIPS] = 1;
        Project.PushDown.sendReqs[FOOT] = 2;
        Project.PushDown.nonBlocking = Boolean.valueOf(true);
        Project.PushDown.targeted = Boolean.valueOf(true);
        Project.PushDown.counterpart = Project.PullDown;
        Project.PushDown.enders = (new Activity[] {
            Project.StopActing, Project.Escape
        });
        Project.PullDown.sendReqs[HIPS] = 1;
        Project.PullDown.targeted = Boolean.valueOf(true);
        Project.PullDown.counterpart = Project.PushDown;
        Project.PullDown.enders = (new Activity[] {
            Project.Escape
        });
        Project.Escape.sendReqs[HAND] = 1;
        Project.Escape.sendReqs[FOOT] = 2;
        Project.Escape.targeted = Boolean.valueOf(true);
        Project.Escape.endsSelf = Boolean.valueOf(true);
        Project.Escape.pickable = Boolean.valueOf(false);
        Project.Escape.hostile = Boolean.valueOf(true);
        Project.StopActing.targeted = Boolean.valueOf(true);
        Project.StopActing.endsSelf = Boolean.valueOf(true);
        Project.StopActing.pickable = Boolean.valueOf(false);
        Project.StopActing.hostile = Boolean.valueOf(true);
        Project.TieUp.sendReqs[HAND] = 1;
        Project.TieUp.targeted = Boolean.valueOf(true);
        Project.TieUp.nonBlocking = Boolean.valueOf(true);
        Project.TieUp.counterpart = Project.BeTied;
        Project.TieUp.pickable = Boolean.valueOf(false);
        Project.BeTied.sendReqs[HAND] = 2;
        Project.BeTied.sendReqs[FOOT] = 2;
        Project.BeTied.targeted = Boolean.valueOf(true);
        Project.BeTied.counterpart = Project.TieUp;
        Project.BeTied.pickable = Boolean.valueOf(false);
        Project.StrokeCock.sendReqs[HAND] = 1;
        Project.StrokeCock.targeted = Boolean.valueOf(true);
        Project.StrokeCock.counterpart = Project.CockStroked;
        Project.StrokeCock.enders = (new Activity[] {
            Project.StopActing, Project.Escape
        });
        Project.StrokeCock.pickable = Boolean.valueOf(true);
        Project.CockStroked.sendReqs[PENIS] = 1;
        Project.CockStroked.causesOrgasm = Boolean.valueOf(true);
        Project.CockStroked.targeted = Boolean.valueOf(true);
        Project.CockStroked.counterpart = Project.StrokeCock;
        Project.CockStroked.enders = (new Activity[] {
            Project.Escape
        });
        Project.CockStroked.pickable = Boolean.valueOf(false);
        Project.Lubricate.sendReqs[HAND] = 1;
        Project.Lubricate.nonBlocking = Boolean.valueOf(true);
        Project.Lubricate.targeted = Boolean.valueOf(true);
        Project.Lubricate.counterpart = Project.BeLubricated;
        Project.Lubricate.endsSelf = Boolean.valueOf(true);
        Project.Lubricate.pickable = Boolean.valueOf(false);
        Project.BeLubricated.sendReqs[ASS] = 1;
        Project.BeLubricated.nonBlocking = Boolean.valueOf(true);
        Project.BeLubricated.pickable = Boolean.valueOf(false);
        Project.VaginalPenetrate.sendReqs[PENIS] = 1;
        Project.VaginalPenetrate.causesOrgasm = Boolean.valueOf(true);
        Project.VaginalPenetrate.targeted = Boolean.valueOf(true);
        Project.VaginalPenetrate.counterpart = Project.PenetratedVaginally;
        Project.VaginalPenetrate.enders = (new Activity[] {
            Project.StopActing, Project.Escape
        });
        Project.PenetratedVaginally.sendReqs[PUSSY] = 1;
        Project.PenetratedVaginally.causesOrgasm = Boolean.valueOf(true);
        Project.PenetratedVaginally.targeted = Boolean.valueOf(true);
        Project.PenetratedVaginally.counterpart = Project.VaginalPenetrate;
        Project.PenetratedVaginally.enders = (new Activity[] {
            Project.Escape
        });
        Project.AnalPenetrate.sendReqs[PENIS] = 1;
        Project.AnalPenetrate.causesOrgasm = Boolean.valueOf(true);
        Project.AnalPenetrate.targeted = Boolean.valueOf(true);
        Project.AnalPenetrate.counterpart = Project.PenetratedAnally;
        Project.AnalPenetrate.enders = (new Activity[] {
            Project.StopActing, Project.Escape
        });
        Project.PenetratedAnally.sendReqs[ASS] = 1;
        Project.PenetratedAnally.causesOrgasm = Boolean.valueOf(true);
        Project.PenetratedAnally.targeted = Boolean.valueOf(true);
        Project.PenetratedAnally.counterpart = Project.AnalPenetrate;
        Project.PenetratedAnally.enders = (new Activity[] {
            Project.Escape
        });
        Project.StripOther.sendReqs[HAND] = 1;
        Project.StripOther.targeted = Boolean.valueOf(true);
        Project.StripOther.counterpart = Project.Stripped;
        Project.StripOther.enders = (new Activity[] {
            Project.StopActing, Project.Escape
        });
        Project.StripOther.pickable = Boolean.valueOf(false);
        Project.Stripped.targeted = Boolean.valueOf(true);
        Project.Stripped.counterpart = Project.StripOther;
        Project.Stripped.enders = (new Activity[] {
            Project.Escape
        });
        Project.Stripped.pickable = Boolean.valueOf(false);
        Project.LickCock.sendReqs[MOUTH] = 1;
        Project.LickCock.sendReqs[FOOT] = 2;
        Project.LickCock.shares = Boolean.valueOf(true);
        Project.LickCock.nonBlocking = Boolean.valueOf(true);
        Project.LickCock.targeted = Boolean.valueOf(true);
        Project.LickCock.counterpart = Project.CockLicked;
        Project.LickCock.enders = (new Activity[] {
            Project.StopActing, Project.Escape
        });
        Project.CockLicked.sendReqs[PENIS] = 1;
        Project.CockLicked.causesOrgasm = Boolean.valueOf(true);
        Project.CockLicked.targeted = Boolean.valueOf(true);
        Project.CockLicked.counterpart = Project.LickCock;
        Project.CockLicked.enders = (new Activity[] {
            Project.Escape
        });
        Project.CockLicked.pickable = Boolean.valueOf(false);
        Project.LickPussy.sendReqs[MOUTH] = 1;
        Project.LickPussy.sendReqs[FOOT] = 2;
        Project.LickPussy.shares = Boolean.valueOf(true);
        Project.LickPussy.nonBlocking = Boolean.valueOf(true);
        Project.LickPussy.targeted = Boolean.valueOf(true);
        Project.LickPussy.counterpart = Project.PussyLicked;
        Project.LickPussy.enders = (new Activity[] {
            Project.StopActing, Project.Escape
        });
        Project.PussyLicked.sendReqs[PUSSY] = 1;
        Project.PussyLicked.causesOrgasm = Boolean.valueOf(true);
        Project.PussyLicked.targeted = Boolean.valueOf(true);
        Project.PussyLicked.counterpart = Project.LickPussy;
        Project.PussyLicked.enders = (new Activity[] {
            Project.Escape
        });
        Project.PussyLicked.pickable = Boolean.valueOf(false);
        Project.Supine.enders = (new Activity[] {
            Project.Escape
        });
        Project.Supine.pickable = Boolean.valueOf(false);
        Project.PullUp.sendReqs[HAND] = 1;
        Project.PullUp.targeted = Boolean.valueOf(true);
        Project.PullUp.endsSelf = Boolean.valueOf(true);
        Project.PullUp.pickable = Boolean.valueOf(false);
        Project.StepOnCock.sendReqs[FOOT] = 1;
        Project.StepOnCock.shares = Boolean.valueOf(true);
        Project.StepOnCock.targeted = Boolean.valueOf(true);
        Project.StepOnCock.counterpart = Project.CockSteppedOn;
        Project.StepOnCock.enders = (new Activity[] {
            Project.StopActing, Project.Escape
        });
        Project.CockSteppedOn.sendReqs[PENIS] = 1;
        Project.CockSteppedOn.causesOrgasm = Boolean.valueOf(true);
        Project.CockSteppedOn.targeted = Boolean.valueOf(true);
        Project.CockSteppedOn.counterpart = Project.StepOnCock;
        Project.CockSteppedOn.enders = (new Activity[] {
            Project.Escape
        });
        Project.CockSteppedOn.pickable = Boolean.valueOf(false);
        Project.StepOnClit.sendReqs[FOOT] = 1;
        Project.StepOnClit.shares = Boolean.valueOf(true);
        Project.StepOnClit.targeted = Boolean.valueOf(true);
        Project.StepOnClit.counterpart = Project.ClitSteppedOn;
        Project.StepOnClit.enders = (new Activity[] {
            Project.StopActing, Project.Escape
        });
        Project.ClitSteppedOn.sendReqs[CLIT] = 1;
        Project.ClitSteppedOn.causesOrgasm = Boolean.valueOf(true);
        Project.ClitSteppedOn.targeted = Boolean.valueOf(true);
        Project.ClitSteppedOn.counterpart = Project.StepOnClit;
        Project.ClitSteppedOn.enders = (new Activity[] {
            Project.Escape
        });
        Project.ClitSteppedOn.pickable = Boolean.valueOf(false);
        Project.DirtyTalk.sendReqs[MOUTH] = 1;
        Project.DirtyTalk.targeted = Boolean.valueOf(true);
        Project.DirtyTalk.endsSelf = Boolean.valueOf(true);
    }

    public Boolean orgasmPossible()
    {
        for(int i = 0; i < inProgress.length; i++)
            if(inProgress[i].causesOrgasm.booleanValue())
                return Boolean.valueOf(true);

        return Boolean.valueOf(false);
    }

    public void CancelActivities(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s, final int page)
    {
        p.removeAll();
        if(page > 0)
        {
            JButton Previous = new JButton("<");
            Previous.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    CancelActivities(t, p, f, w, s, page - 1);
                }

                final Body this$0;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final SaveData val$s;
                private final int val$page;

            
            {
                this$0 = Body.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                page = i;
                super();
            }
            });
            p.add(Previous);
        }
        for(int i = page * 3; i < page * 3 + 3 && i < w.lordBody.inProgress.length; i++)
        {
            JButton ThisOne = new JButton(w.lordBody.inProgress[i].activityName(w.lordBody.targets[i]));
            final int index = i;
            ThisOne.addActionListener(new ActionListener() {

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

                final Body this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final int val$index;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;
                private final int val$page;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                index = i;
                p = jpanel;
                f = jframe;
                s = savedata;
                page = j;
                super();
            }
            });
            p.add(ThisOne);
        }

        if((page + 1) * 3 < w.lordBody.inProgress.length)
        {
            JButton Next = new JButton(">");
            Next.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    CancelActivities(t, p, f, w, s, page + 1);
                }

                final Body this$0;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final SaveData val$s;
                private final int val$page;

            
            {
                this$0 = Body.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
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
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
                PickActivity(t, p, f, w, s);
            }

            final Body this$0;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public void TouchMenu(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s)
    {
        p.removeAll();
        if(Project.TweakClit.valid(this, w.targetBody).booleanValue())
        {
            JButton TweakClit = new JButton("Stroke Clit");
            TweakClit.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.TweakClit.startActivity(t, w, w.lordBody, w.targetBody);
                    Continue(t, p, f, w, s);
                }

                final Body this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
            });
            p.add(TweakClit);
        }
        if(Project.StrokeCock.valid(this, w.targetBody).booleanValue())
        {
            JButton StrokeCock = new JButton("Stroke Cock");
            StrokeCock.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.StrokeCock.startActivity(t, w, w.lordBody, w.targetBody);
                    Continue(t, p, f, w, s);
                }

                final Body this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
            });
            p.add(StrokeCock);
        }
        if(Project.LickCock.valid(this, w.targetBody).booleanValue())
        {
            JButton LickCock = new JButton("Lick Cock");
            LickCock.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.LickCock.startActivity(t, w, w.lordBody, w.targetBody);
                    Continue(t, p, f, w, s);
                }

                final Body this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
            });
            p.add(LickCock);
        }
        if(Project.LickPussy.valid(this, w.targetBody).booleanValue())
        {
            JButton LickPussy = new JButton("Lick Pussy");
            LickPussy.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.LickPussy.startActivity(t, w, w.lordBody, w.targetBody);
                    Continue(t, p, f, w, s);
                }

                final Body this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
            });
            p.add(LickPussy);
        }
        if(Project.StepOnCock.valid(this, w.targetBody).booleanValue())
        {
            JButton StepOnCock = new JButton("Give Footjob");
            StepOnCock.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.StepOnCock.startActivity(t, w, w.lordBody, w.targetBody);
                    Continue(t, p, f, w, s);
                }

                final Body this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
            });
            p.add(StepOnCock);
        }
        if(Project.StepOnClit.valid(this, w.targetBody).booleanValue())
        {
            JButton StepOnClit = new JButton("Give Footjob");
            StepOnClit.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.StepOnClit.startActivity(t, w, w.lordBody, w.targetBody);
                    Continue(t, p, f, w, s);
                }

                final Body this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
            });
            p.add(StepOnClit);
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
                PickActivity(t, p, f, w, s);
            }

            final Body this$0;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public void PositionMenu(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s)
    {
        p.removeAll();
        if(Project.PushDown.valid(this, w.targetBody).booleanValue())
        {
            JButton PushDown = new JButton("Push Down");
            PushDown.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.PushDown.startActivity(t, w, w.lordBody, w.targetBody);
                    w.append(t, "\n\n");
                    Project.PullDown.activityTalk(t, w, w.targetBody, w.lordBody);
                    Continue(t, p, f, w, s);
                }

                final Body this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
            });
            p.add(PushDown);
        }
        if(Project.PullDown.valid(this, w.targetBody).booleanValue())
        {
            JButton PullDown = new JButton("Pull Down");
            PullDown.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.PullDown.startActivity(t, w, w.lordBody, w.targetBody);
                    w.append(t, "\n\n");
                    Project.PushDown.activityTalk(t, w, w.targetBody, w.lordBody);
                    Continue(t, p, f, w, s);
                }

                final Body this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
            });
            p.add(PullDown);
        }
        if(!Project.TieUp.isInProgress(this, w.targetBody).booleanValue())
        {
            JButton TieUp = new JButton("Tie Up");
            TieUp.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.TieUp.startActivity(t, w, w.lordBody, w.targetBody);
                    if(w.targetBody.forsakenOwner == null && w.targetBody.chosenOwner != null)
                    {
                        w.append(t, "\n\n");
                        Project.BeTied.activityTalk(t, w, w.targetBody, w.lordBody);
                    }
                    Continue(t, p, f, w, s);
                }

                final Body this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
            });
            p.add(TieUp);
        }
        if(Project.Supine.valid(this, null).booleanValue())
        {
            JButton LayDown = new JButton("Lay Self Down");
            LayDown.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.Supine.startActivity(t, w, w.lordBody, null);
                    Continue(t, p, f, w, s);
                }

                final Body this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
            });
            p.add(LayDown);
        }
        if(Project.Supine.valid(w.targetBody, null).booleanValue())
        {
            JButton LayDown = new JButton((new StringBuilder("Lay ")).append(w.targetBody.ownerName()).append(" Down").toString());
            LayDown.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.Supine.startActivity(t, w, w.targetBody, null);
                    Continue(t, p, f, w, s);
                }

                final Body this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
            });
            p.add(LayDown);
        }
        if(Project.PullUp.valid(this, w.targetBody).booleanValue())
        {
            JButton PullUp = new JButton((new StringBuilder("Pull ")).append(w.targetBody.ownerName()).append(" Up").toString());
            PullUp.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.PullUp.startActivity(t, w, w.lordBody, w.targetBody);
                    Continue(t, p, f, w, s);
                }

                final Body this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
            });
            p.add(PullUp);
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
                PickActivity(t, p, f, w, s);
            }

            final Body this$0;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public void SexMenu(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s)
    {
        p.removeAll();
        if(Project.VaginalPenetrate.valid(w.lordBody, w.targetBody).booleanValue())
            if(Project.PenetratedVaginally.weight(w, w.targetBody, w.lordBody) >= 0 || w.targetBody.getHATELevel() >= 3)
            {
                JButton Missionary = new JButton("Missionary Vaginal");
                Missionary.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        Project.VaginalPenetrate.startActivity(t, w, w.lordBody, w.targetBody);
                        w.append(t, "\n\n");
                        Project.PenetratedVaginally.activityTalk(t, w, w.targetBody, w.lordBody);
                        Continue(t, p, f, w, s);
                    }

                    final Body this$0;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
                });
                p.add(Missionary);
            } else
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").append(w.targetBody.OwnerName()).append("'s Sexual Barrier prevents vaginal penetration.").toString());
            }
        if(Project.PenetratedVaginally.valid(w.lordBody, w.targetBody).booleanValue())
        {
            JButton Cowgirl = new JButton("Cowgirl Vaginal");
            Cowgirl.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.PenetratedVaginally.startActivity(t, w, w.lordBody, w.targetBody);
                    w.append(t, "\n\n");
                    Project.VaginalPenetrate.activityTalk(t, w, w.targetBody, w.lordBody);
                    Continue(t, p, f, w, s);
                }

                final Body this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
            });
            p.add(Cowgirl);
        }
        if(Project.AnalPenetrate.valid(w.lordBody, w.targetBody).booleanValue())
            if(Project.PenetratedAnally.weight(w, w.targetBody, w.lordBody) >= 0 || w.targetBody.getHATELevel() >= 3 || w.targetBody.parts[PUSSY] > 0)
            {
                JButton Missionary = new JButton("Missionary Anal");
                if(!Project.BeLubricated.isInProgress(w.targetBody, null).booleanValue())
                    Missionary.setText("Unlubricated Missionary Anal");
                Missionary.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        Project.AnalPenetrate.startActivity(t, w, w.lordBody, w.targetBody);
                        w.append(t, "\n\n");
                        Project.PenetratedAnally.activityTalk(t, w, w.targetBody, w.lordBody);
                        Continue(t, p, f, w, s);
                    }

                    final Body this$0;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
                });
                p.add(Missionary);
            } else
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").append(w.targetBody.OwnerName()).append("'s Sexual Barrier prevents anal penetration.").toString());
            }
        if(Project.PenetratedAnally.valid(w.lordBody, w.targetBody).booleanValue())
        {
            JButton Cowgirl = new JButton("Cowgirl Anal");
            if(appearanceGender(bodyType) == Forsaken.Gender.MALE)
                Cowgirl.setText("Cowgirl (Male) Anal");
            Cowgirl.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.PenetratedAnally.startActivity(t, w, w.lordBody, w.targetBody);
                    w.append(t, "\n\n");
                    Project.AnalPenetrate.activityTalk(t, w, w.targetBody, w.lordBody);
                    Continue(t, p, f, w, s);
                }

                final Body this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
            });
            p.add(Cowgirl);
        }
        if(Project.Lubricate.valid(w.lordBody, w.targetBody).booleanValue())
        {
            JButton Lubricate = new JButton("Lubricate Anus");
            Lubricate.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    Project.Lubricate.startActivity(t, w, w.lordBody, w.targetBody);
                    Continue(t, p, f, w, s);
                }

                final Body this$0;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
            });
            p.add(Lubricate);
        }
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
                PickActivity(t, p, f, w, s);
            }

            final Body this$0;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public void PickActivity(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s)
    {
        w.actingBody = this;
        for(int i = 0; i < inProgress.length; i++)
            if(inProgress[i].endsSelf.booleanValue())
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
            final String shownNames[] = {
                w.targetBody.portraitName(), 0, 0, 0, 0
            };
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
                            w.targetBody.currentEXPO = 0xf4240L;
                        } else
                        if(w.targetBody.getDeviancy() > 33)
                        {
                            w.append(t, (new StringBuilder("wearing nothing but a thin robe which ")).append(w.targetBody.heShe()).append(" quickly drops to the floor as ").append(w.targetBody.heShe()).append(" welcomes ").append(w.lordBody.himHer()).append(".  An eager smile is on ").append(w.targetBody.ownerName()).append("'s face, and ").append(w.targetBody.hisHer()).append(" cheeks are flushed with arousal.").toString());
                            w.targetBody.currentEXPO = 0xf4240L;
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
                            w.targetBody.currentEXPO = 0xf4240L;
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
                        if(!w.targetBody.hasBeenBroadcasted().booleanValue())
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
                            int audience = 0x493e0 + (int)(Math.random() * 300000D);
                            w.sceneParticipants[i].forsakenOwner.timesExposed += audience;
                            w.sceneParticipants[i].forsakenOwner.timesExposedSelf += audience;
                        }

                } else
                if(w.sceneLocation == Activity.Location.ALLEY)
                {
                    if(w.targetBody.forsakenOwner == null && w.targetBody.chosenOwner != null)
                        if(w.targetBody.chosenOwner.rememberedBodies.length == 1)
                        {
                            if(w.targetBody.getInnocence() > 66)
                            {
                                w.append(t, (new StringBuilder("When you arrive at an alleyway not far from ")).append(w.targetBody.ownerName()).append("'s home, ").append(w.targetBody.heShe()).append(" senses the Demonic presence and flies over to you, prepared for battle.  ").toString());
                                if(w.targetBody.isDrained().booleanValue())
                                {
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
                                    w.append(t, (new StringBuilder("However, as soon as ")).append(w.targetBody.heShe()).append(" recognizes that this is the Demon Lord ").append(w.lordBody.himHer()).append("self, ").append(w.targetBody.heShe()).append(" flinches and loses ").append(w.targetBody.hisHer()).append(" will to fight.\n\n").toString());
                                    w.targetBody.say(t, "\"");
                                    if(w.targetBody.getMorality() > 66)
                                        w.targetBody.say(t, "I-I haven't fallen far enough to let you drain me while I'm on duty!");
                                    else
                                    if(w.targetBody.getMorality() > 33)
                                        w.targetBody.say(t, "If you want more energy... just wait until tonight, okay?");
                                    else
                                        w.targetBody.say(t, "Are you just here to make fun of me...?");
                                    w.targetBody.say(t, "\"");
                                } else
                                {
                                    w.append(t, (new StringBuilder("It takes ")).append(w.targetBody.himHer()).append(" a moment to realize that this is the Demon Lord ").append(w.lordBody.himHer()).append("self, but once ").append(w.targetBody.heShe()).append(" does, ").append(w.targetBody.heShe()).append(" gets even more excited.\n\n").toString());
                                    w.targetBody.say(t, "\"");
                                    if(w.targetBody.getConfidence() > 66)
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                                        w.targetBody.say(t, "Come to beg for your life, Demon Lord!?  Hah!  I might blow you up right here!");
                                    } else
                                    if(w.targetBody.getConfidence() > 33)
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.ANGER);
                                        w.targetBody.say(t, (new StringBuilder("The Demon Lord is right here... but I guess blowing ")).append(w.lordBody.himHer()).append(" up wouldn't actually do anything... so it can't hurt to see what ").append(w.lordBody.heShe()).append(" wants...").toString());
                                    } else
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.ANGER);
                                        w.targetBody.say(t, "I-It's really the Demon Lord!?  If you do anything weird, I'll call the others here right away!");
                                    }
                                    w.targetBody.say(t, "\"");
                                }
                            } else
                            if(w.targetBody.getInnocence() > 33)
                            {
                                w.append(t, (new StringBuilder("While ")).append(w.targetBody.ownerName()).append(" is out on patrol, you deliberately leave a trail of Demonic influence that ").append(w.targetBody.heShe()).append(" can follow to find you.  When ").append(w.targetBody.heShe()).append(" senses that the person before ").append(w.targetBody.himHer()).append(" is actually a body being directly controlled by the Demon Lord, ").toString());
                                if(w.targetBody.isDrained().booleanValue())
                                {
                                    w.append(t, (new StringBuilder(String.valueOf(w.targetBody.heShe()))).append(" gasps in surprise and hesitates, uncertain of how ").append(w.targetBody.heShe()).append(" should react.\n\n").toString());
                                    w.targetBody.say(t, "\"");
                                    if(w.targetBody.getMorality() > 66)
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
                                        w.targetBody.say(t, "Have you come to gloat, Demon Lord?  Ugh, I guess I deserve it...");
                                    } else
                                    if(w.targetBody.getMorality() > 33)
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
                                        w.targetBody.say(t, "I knew I never should have given you my energy.  Now you're just harassing me...");
                                    } else
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.ANGER);
                                        w.targetBody.say(t, "Listen, Demon Lord.  If you want me to give you my energy in broad daylight... you'll have to make it worth my while, alright?");
                                    }
                                    w.targetBody.say(t, "\"");
                                } else
                                {
                                    w.append(t, (new StringBuilder(String.valueOf(w.targetBody.heShe()))).append(" narrows ").append(w.targetBody.hisHer()).append(" eyes and adjusts ").append(w.targetBody.hisHer()).append(" stance, ready for combat.\n\n").toString());
                                    w.targetBody.say(t, "\"");
                                    if(w.targetBody.getConfidence() > 66)
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
                                        w.targetBody.say(t, "What do you want, Demon Lord!?  Do you think I'll hold back just because you came here in a body that looks human?");
                                    } else
                                    if(w.targetBody.getConfidence() > 33)
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
                                        w.targetBody.say(t, "You came here alone?  Why?  If you want to talk, you'd better make it quick.");
                                    } else
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.ANGER);
                                        w.targetBody.say(t, "A-Are you trying to scare me, Demon Lord!?  I'm not going to quit my patrol just because you sent a decoy body to distract me!");
                                    }
                                    w.targetBody.say(t, "\"");
                                }
                            } else
                            {
                                w.append(t, (new StringBuilder("You leave a note for ")).append(w.targetBody.ownerName()).append(", inviting ").append(w.targetBody.himHer()).append(" to a private meeting in a secluded alleyway.  ").append(w.targetBody.HeShe()).append(" arrives promptly on time, ").toString());
                                if(w.targetBody.isDrained().booleanValue())
                                {
                                    w.append(t, "looking harried and nervous.\n\n");
                                    w.targetBody.say(t, "\"");
                                    if(w.targetBody.getDignity() > 66)
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.ANGER);
                                        w.targetBody.say(t, "Wh-What is the meaning of this?  If you insist on harassing me, I'll break off our... our energy-related arrangement.  I will!");
                                    } else
                                    if(w.targetBody.getDignity() > 33)
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.ANGER);
                                        w.targetBody.say(t, "Do you realize what will happen if I'm seen talking with the Demon Lord in public?  Tell me what you want, quickly, quickly!");
                                    } else
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
                                        w.targetBody.say(t, "What do you want from me, Demon Lord?  I'm already compromising my principles by giving you my energy.  Whatever it is, just be quick about it.");
                                    }
                                    w.targetBody.say(t, "\"");
                                } else
                                {
                                    w.append(t, "studying your body with an expression of detached curiosity.\n\n");
                                    w.targetBody.say(t, "\"");
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FOCUS);
                                    if(w.targetBody.getMorality() > 66)
                                        w.targetBody.say(t, "I almost didn't come... but it's against my principles to ignore an offer of parley, even from a Demon.  If you use that body to attack me, however, I have no qualms about destroying it.");
                                    else
                                    if(w.targetBody.getMorality() > 33)
                                        w.targetBody.say(t, "A chance to hear the Demonic perspective...  Well, I expect that we'll still end up fighting to the death in the end, but maybe we can learn something in the meantime.");
                                    else
                                        w.targetBody.say(t, "Have you finally decided that you'd rather have me on your side?  I doubt that you can offer terms better than the ones I'm getting from the government, but... I'm listening.");
                                    w.targetBody.say(t, "\"");
                                }
                            }
                        } else
                        if(w.targetBody.chosenOwner.negotiations < 3 || w.targetBody.chosenOwner.ANGST < 0x3b9aca00L)
                        {
                            if(w.targetBody.getInnocence() > 66)
                            {
                                w.append(t, (new StringBuilder("You use the same trick of using your Demonic presence to lure ")).append(w.targetBody.ownerName()).append(" from her home again.  ").append(w.targetBody.HeShe()).append(" frowns as soon as ").append(w.targetBody.heShe()).append(" sees you.\n\n").toString());
                                if(w.targetBody.chosenOwner.negotiations > 0 || w.targetBody.chosenOwner.drained.booleanValue())
                                {
                                    w.targetBody.say(t, "\"I wish you'd just leave us alone, Demon Lord...\"");
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
                                } else
                                {
                                    w.targetBody.say(t, "\"Leave me alone, Demon Lord!\"");
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FEAR);
                                }
                            } else
                            if(w.targetBody.getInnocence() > 33)
                            {
                                w.append(t, (new StringBuilder("You send out another body to meet ")).append(w.targetBody.ownerName()).append(" in the middle of ").append(w.targetBody.hisHer()).append(" patrol.  ").append(w.targetBody.HeShe()).append(" lands just inside the alleyway where you're waiting.\n\n").toString());
                                if(w.targetBody.chosenOwner.negotiations > 0 || w.targetBody.chosenOwner.drained.booleanValue())
                                {
                                    w.targetBody.say(t, "\"I'm here...  What do you want, Demon Lord?\"");
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
                                } else
                                {
                                    w.targetBody.say(t, "\"The only reason I haven't blown you up already is because you aren't using a combat-type body.  So, what do you want?\"");
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
                                }
                            } else
                            {
                                w.append(t, (new StringBuilder("You deliver another note to ")).append(w.targetBody.ownerName()).append(", offering a meeting, and ").append(w.targetBody.heShe()).append(" arrives at the designated time and place.\n\n").toString());
                                if(w.targetBody.chosenOwner.negotiations > 0 || w.targetBody.chosenOwner.drained.booleanValue())
                                {
                                    w.targetBody.say(t, "\"As you requested, I came.  What do you require of me?");
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
                                } else
                                {
                                    w.targetBody.say(t, "\"I only came because I was able to ensure that you haven't set a trap for me here.  I hope you aren't expecting me to let my guard down, Demon Lord.\"");
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.ANGER);
                                }
                            }
                        } else
                        if(w.targetBody.getDignity() > 66)
                        {
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.ownerName()))).append(" responds to your summons quickly, but with ").append(w.targetBody.hisHer()).append(" face blushing bright red as ").append(w.targetBody.heShe()).append(" constantly looks over ").append(w.targetBody.hisHer()).append(" shoulder to make sure ").append(w.targetBody.heShe()).append(" wasn't seen entering the alleyway.\n\n").toString());
                            w.targetBody.say(t, "\"Wanting to do this in public even though I offered to let you into my room...  Ugh, I should have known the Demon Lord would be a pervert...\"");
                            Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.LEWD);
                        } else
                        if(w.targetBody.getDignity() > 33)
                        {
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.ownerName()))).append(" arrives to the meeting place early, blushing slightly and shifting uncomfortably from foot to foot.  ").append(w.targetBody.HeShe()).append(" has trouble meeting your gaze.\n\n").toString());
                            w.targetBody.say(t, "\"I know why you called me here, so let's...  Let's just hurry up and do it, okay?\"");
                            Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.SHAME);
                        } else
                        {
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.ownerName()))).append(" seems to be in a good mood as ").append(w.targetBody.heShe()).append(" arrives at the alleyway.\n\n").toString());
                            w.targetBody.say(t, "\"I think I'm starting to enjoy this.  But if you want me to go along with it, I'll still need your promise not to attack today.\"");
                            Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                        }
                    w.targetBody.currentFEAR = w.targetBody.getObedience() * 5;
                    w.targetBody.currentDISG = (100 - w.targetBody.getDeviancy()) * 3;
                    w.targetBody.currentHATE = 2000 + w.targetBody.getHostility() * 20;
                    for(int counter = w.targetBody.chosenOwner.negotiations; counter > 0; counter--)
                    {
                        w.targetBody.currentFEAR = (w.targetBody.currentFEAR * 2L) / 3L;
                        w.targetBody.currentDISG = w.targetBody.currentDISG / 2L;
                        w.targetBody.currentSHAM += w.targetBody.getDignity();
                        w.targetBody.currentHATE = (w.targetBody.currentHATE * 100L) / (long)(200 + w.targetBody.getObedience());
                        w.targetBody.currentPLEA += (w.targetBody.getDeviancy() * 2) / 3;
                    }

                } else
                if(w.sceneLocation == Activity.Location.ROOM)
                {
                    w.sceneParticipants[1].transformed = Boolean.valueOf(false);
                    if(w.targetBody.getInnocence() > 66)
                    {
                        w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" eagerly ushers you into ").append(w.targetBody.hisHer()).append(" room, offering you drinks, snacks, and whatever else ").append(w.targetBody.heShe()).append(" thinks might make you happy and comfortable.  Then ").append(w.targetBody.heShe()).append(" sits on the edge of ").append(w.targetBody.hisHer()).append(" bed, blushing and fidgeting nervously.  It's plain to see that ").append(w.targetBody.heShe()).append("'s been anticipating this.\n\n").toString());
                        w.targetBody.say(t, "\"");
                        if(w.day % 3 == 0)
                        {
                            w.targetBody.say(t, "Do you want some candy?  Um, do Demons even like candy...?");
                            Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.JOY);
                        } else
                        if(w.day % 3 == 1)
                        {
                            w.targetBody.say(t, "Hey, have you ever thought about becoming one of the good guys?  I think that would make everybody happy.");
                            Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
                        } else
                        {
                            w.targetBody.say(t, "It's weird.  Even though we're enemies, sometimes I... think I kinda like you...");
                            Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                        }
                        w.targetBody.say(t, "\"");
                    } else
                    if(w.targetBody.getInnocence() > 33)
                    {
                        w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" welcomes you at ").append(w.targetBody.hisHer()).append(" door, already wearing a set of skimpy lingerie that presents ").append(w.targetBody.hisHer()).append(" body quite nicely.  ").append(w.targetBody.HeShe()).append(" sways ").append(w.targetBody.hisHer()).append(" hips as ").append(w.targetBody.heShe()).append(" turns to lead you inside, obviously attempting to entice you.\n\n").toString());
                        w.targetBody.say(t, "\"");
                        if(w.day % 3 == 0)
                            w.targetBody.say(t, "Do you like what you see?  You know the price by now.");
                        else
                        if(w.day % 3 == 1)
                            w.targetBody.say(t, "If you want to take my virginity...  Well, no, it would have to be more than a one day truce...");
                        else
                            w.targetBody.say(t, "Even if you aren't willing to agree on a truce today...  No, never mind!  I have to stay firm...");
                        w.targetBody.say(t, "\"");
                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                    } else
                    {
                        w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" hesitantly lets you into ").append(w.targetBody.hisHer()).append(" room, awkwardly trying to make conversation.  From time to time, ").append(w.targetBody.heShe()).append(" loses ").append(w.targetBody.hisHer()).append(" train of thought, staring into your eyes for several moments before coming back to the present.\n\n").toString());
                        w.targetBody.say(t, "\"");
                        if(w.day % 3 == 0)
                            w.targetBody.say(t, "Have I ever told you that you're... not at all what I was expecting from a Demon Lord...?");
                        else
                        if(w.day % 3 == 1)
                            w.targetBody.say(t, "My apologies.  I'm having trouble sorting out my own feelings...");
                        else
                            w.targetBody.say(t, "To think that I'd be so willing to let the Demon Lord into my personal life...");
                        w.targetBody.say(t, "\"");
                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.SHAME);
                    }
                    w.targetBody.currentFEAR = w.targetBody.getObedience() * 8;
                    w.targetBody.currentDISG = (100 - w.targetBody.getDeviancy()) * 2;
                    w.targetBody.currentHATE = 1000L;
                    w.targetBody.currentPLEA = (w.targetBody.getDeviancy() * 2) / 3;
                    for(int counter = w.targetBody.chosenOwner.negotiations; counter > 0; counter--)
                    {
                        w.targetBody.currentFEAR = (w.targetBody.currentFEAR * 2L) / 3L;
                        w.targetBody.currentDISG = w.targetBody.currentDISG / 2L;
                        w.targetBody.currentSHAM += w.targetBody.getDignity() / 2;
                        w.targetBody.currentHATE = (w.targetBody.currentHATE * 100L) / (long)(200 + w.targetBody.getObedience());
                        w.targetBody.currentPLEA += w.targetBody.getDeviancy() / 2;
                    }

                }
                w.append(t, "\n\n");
            } else
            {
                Boolean descriptionStarted = Boolean.valueOf(false);
                Activity mentioned[] = new Activity[0];
                Body targeted[] = new Body[0];
                for(int i = 0; i < w.sceneParticipants.length; i++)
                {
                    for(int j = 0; j < w.sceneParticipants[i].inProgress.length; j++)
                        if(!w.sceneParticipants[i].inProgress[j].endsSelf.booleanValue())
                        {
                            if(!descriptionStarted.booleanValue())
                            {
                                descriptionStarted = Boolean.valueOf(true);
                                w.append(t, "In Progress:");
                            }
                            Boolean found = Boolean.valueOf(false);
                            for(int k = 0; k < mentioned.length; k++)
                                if(mentioned[k].counterpart == w.sceneParticipants[i].inProgress[j] && targeted[k] == w.sceneParticipants[i])
                                    found = Boolean.valueOf(true);

                            if(!found.booleanValue())
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

                if(descriptionStarted.booleanValue())
                    w.append(t, "\n\n");
            }
            w.append(t, (new StringBuilder("How will ")).append(ownerName()).append(" act?").toString());
            JButton Touch = new JButton("Pleasure");
            Touch.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    TouchMenu(t, p, f, w, s);
                }

                final Body this$0;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                super();
            }
            });
            p.add(Touch);
            if(!Project.PushDown.isInProgress(w.lordBody, w.targetBody).booleanValue() && !Project.PullDown.isInProgress(w.lordBody, w.targetBody).booleanValue())
            {
                JButton Position = new JButton("Position");
                Position.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        PositionMenu(t, p, f, w, s);
                    }

                    final Body this$0;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final SaveData val$s;

            
            {
                this$0 = Body.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                super();
            }
                });
                p.add(Position);
            } else
            if(Project.PushDown.isInProgress(w.lordBody, w.targetBody).booleanValue())
            {
                JButton Sex = new JButton("Sex");
                Sex.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        SexMenu(t, p, f, w, s);
                    }

                    final Body this$0;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final SaveData val$s;

            
            {
                this$0 = Body.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                super();
            }
                });
                p.add(Sex);
            }
            if(Project.StripOther.valid(this, w.targetBody).booleanValue())
            {
                JButton StripOther = new JButton("Strip");
                StripOther.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        Project.StripOther.startActivity(t, w, w.lordBody, w.targetBody);
                        Continue(t, p, f, w, s);
                    }

                    final Body this$0;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
                });
                p.add(StripOther);
            }
            if(w.lordBody.inProgress.length > 0)
            {
                JButton Cancel = new JButton("Stop Action");
                Cancel.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\nWhich action will you cancel?").toString());
                        CancelActivities(t, p, f, w, s, 0);
                    }

                    final Body this$0;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final SaveData val$s;

            
            {
                this$0 = Body.this;
                w = worldstate;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                s = savedata;
                super();
            }
                });
                p.add(Cancel);
            }
            JButton Wait = new JButton("Wait");
            Wait.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Boolean summaryNeeded = Boolean.valueOf(false);
                    if(summaryNeeded.booleanValue())
                        Continue(t, p, f, w, s);
                    else
                        advanceAction(t, p, f, w, s);
                }

                final Body this$0;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                super();
            }
            });
            if(inProgress.length > 0)
                Wait.setText("Continue");
            p.add(Wait);
            if(w.targetBody.forsakenOwner == null && w.targetBody.chosenOwner != null && !w.targetBody.chosenOwner.truce.booleanValue() && w.day > 1 && w.day < 50 - w.eventOffset * 3 && !w.targetBody.chosenOwner.cVirg.booleanValue() && !w.targetBody.chosenOwner.aVirg.booleanValue() && w.targetBody.chosenOwner.vVirg.booleanValue() && w.targetBody.chosenOwner.modest.booleanValue() && !w.targetBody.chosenOwner.debased && w.targetBody.chosenOwner.timesSlaughtered() == 0 && w.targetBody.chosenOwner.timesStripped() == 0)
            {
                JButton Negotiate = new JButton("Negotiate");
                Negotiate.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        p.removeAll();
                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                        if(w.targetBody.chosenOwner.negotiations > 0)
                        {
                            w.append(t, (new StringBuilder("Once again, you offer to spare the city for a day in exchange for ")).append(w.targetBody.ownerName()).append("'s body.\n\n").toString());
                            w.targetBody.say(t, "\"");
                            if(w.targetBody.getMorality() > 66)
                            {
                                if(w.targetBody.chosenOwner.negotiations % 3 == 1)
                                {
                                    if(w.targetBody.getInnocence() > 66)
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
                                        w.targetBody.say(t, "Um, I guess I basically have to go along with this now...");
                                    } else
                                    if(w.targetBody.getInnocence() > 33)
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.NEUTRAL);
                                        w.targetBody.say(t, "Alright.  In order to protect the city, I'm willing to do this again.");
                                    } else
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FOCUS);
                                        w.targetBody.say(t, "If this is the course of action that minimizes the harm you do, I am still obligated to continue.");
                                    }
                                } else
                                if(w.targetBody.chosenOwner.negotiations % 3 == 2)
                                {
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                                    if(w.targetBody.getConfidence() > 66)
                                        w.targetBody.say(t, "You've proven surprisingly trustworthy for a Demon Lord.  Very well.");
                                    else
                                    if(w.targetBody.getConfidence() > 33)
                                        w.targetBody.say(t, "I was expecting this to be harder.  Alright, let's do it.");
                                    else
                                        w.targetBody.say(t, "I'm glad that you haven't gotten bored of me yet... U-Um, I mean, I accept, of course!");
                                } else
                                {
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.SHAME);
                                    if(w.targetBody.getDignity() > 66)
                                        w.targetBody.say(t, "I-It's not like I'm enjoying this!  That would make me some sort of... pervert...  Right, I'm doing this for everyone else's sake, so let's hurry up and do it!");
                                    else
                                    if(w.targetBody.getDignity() > 33)
                                        w.targetBody.say(t, "You don't have to go easy on me, you know.  I'm prepare to endure anything for everyone's sake, so... you can punish me harder if you want.  Ugh, that doesn't sound right...");
                                    else
                                        w.targetBody.say(t, "I feel guilty for enjoying this.  But refusing your offer would just make things harder on everyone...");
                                }
                            } else
                            if(w.targetBody.getMorality() > 33)
                            {
                                if(w.targetBody.chosenOwner.negotiations % 3 == 1)
                                {
                                    if(w.targetBody.getInnocence() > 66)
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                                        w.targetBody.say(t, "You wanna do it again?  Well, last time wasn't too bad... so okay!");
                                    } else
                                    if(w.targetBody.getInnocence() > 33)
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                                        w.targetBody.say(t, "Is it worth that much to you, using my body?  Fine, go ahead, I can take it.");
                                    } else
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FOCUS);
                                        w.targetBody.say(t, "I remain suspicious of your motivations... but very well.  I accept.");
                                    }
                                } else
                                if(w.targetBody.chosenOwner.negotiations % 3 == 2)
                                {
                                    if(w.targetBody.getConfidence() > 66)
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                                        w.targetBody.say(t, "Even the Demon Lord can't resist my charms.  Alright, I accept.");
                                    } else
                                    if(w.targetBody.getConfidence() > 33)
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                                        w.targetBody.say(t, "Am I really so irresistable?  I suppose I am.");
                                    } else
                                    {
                                        Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
                                        w.targetBody.say(t, "Why are you interested in someone like me?  N-Not that I'm refusing, but I... I don't understand it.");
                                    }
                                } else
                                if(w.targetBody.getDignity() > 66)
                                {
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                                    w.targetBody.say(t, "Yes!  Ah, I mean, um, if it's for the sake of the city, I can't refuse, can I?");
                                } else
                                if(w.targetBody.getDignity() > 33)
                                {
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FOCUS);
                                    w.targetBody.say(t, "What would the others say if they knew how I...?  No, that doesn't matter.  Let's just do this.");
                                } else
                                {
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                                    w.targetBody.say(t, "I was hoping you'd ask.  It seems like this works out to make everyone happy, doesn't it?");
                                }
                            } else
                            if(w.targetBody.chosenOwner.negotiations % 3 == 1)
                            {
                                if(w.targetBody.getInnocence() > 66)
                                {
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                                    w.targetBody.say(t, "Well, I don't feel like fighting you right now, so okay!");
                                } else
                                if(w.targetBody.getInnocence() > 33)
                                {
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FOCUS);
                                    w.targetBody.say(t, "I still don't completely trust you, Demon Lord.  But that doesn't mean we can't have some fun.");
                                } else
                                {
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                                    w.targetBody.say(t, "To think that a Demon Lord's lust would be so easy to manipulate...  Let's continue where we left off, shall we?");
                                }
                            } else
                            if(w.targetBody.chosenOwner.negotiations % 3 == 2)
                            {
                                Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                                if(w.targetBody.getConfidence() > 66)
                                    w.targetBody.say(t, "I should add some extra conditions.  Maybe have you send me some Thralls as personal servants?  For now, we can just do the same old agreement.");
                                else
                                if(w.targetBody.getConfidence() > 33)
                                    w.targetBody.say(t, "I'm your favorite, aren't I?  Well, alright, I'll do it for you.");
                                else
                                    w.targetBody.say(t, "Heh, I might not be strong... but now I've got the Demon Lord wrapped around my finger.  I'm better than all those heroes...");
                            } else
                            if(w.targetBody.getDignity() > 66)
                            {
                                Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.LEWD);
                                w.targetBody.say(t, "Don't take me for granted.  I still haven't forgiven you for what your minions have done to me.  But... you're in luck, because I feel like agreeing this time.");
                            } else
                            if(w.targetBody.getDignity() > 33)
                            {
                                Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.JOY);
                                w.targetBody.say(t, "You should have offered to do this in the first place, before we ever fought.  I would have accepted... maybe...");
                            } else
                            {
                                Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                                w.targetBody.say(t, "Fighting hasn't been as much fun lately.  But sex is more fun than ever!");
                            }
                            w.targetBody.say(t, "\"\n\n");
                            w.append(t, (new StringBuilder("Agreeing will prevent you from battling and from using the Assist Raid training action today.  However, it will increase the Resolve damage from Appeal by ")).append((w.achievementHeld(8)[0] + 1) * (w.achievementHeld(7)[0] + 1)).append("% (to a total of ").append((w.achievementHeld(8)[0] + w.targetBody.chosenOwner.negotiations + 1) * (w.achievementHeld(7)[0] + 1)).append("%).").toString());
                        } else
                        {
                            w.append(t, (new StringBuilder("You make a proposal to ")).append(w.targetBody.ownerName()).append(".  In exchange for letting you do whatever you want with ").append(w.targetBody.hisHer()).append(" body for awhile, you'll promise not to attack the city today.\n\n").toString());
                            w.targetBody.say(t, "\"");
                            if(w.targetBody.getMorality() > 66)
                            {
                                if(w.targetBody.getConfidence() > 66)
                                {
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
                                    w.targetBody.say(t, "Hmph.  This is demeaning, and I still suspect a trap.  But... I suppose I'm obligated to see whether you're being sincere.");
                                } else
                                if(w.targetBody.getConfidence() > 33)
                                {
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FEAR);
                                    if(w.targetBody.getGender() == Forsaken.Gender.MALE)
                                        w.targetBody.say(t, "I can't do any... butt stuff, because that would be... wrong.  Just wrong.  I'll do anything else you want, though.  Is that alright?");
                                    else
                                        w.targetBody.say(t, "I can't give you my virginity, because it doesn't belong to me.  It belongs to my future husband.  But aside from that... you can do what you want with me.");
                                } else
                                {
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
                                    w.targetBody.say(t, "I guess... this is the only way someone like me can save everyone...");
                                }
                            } else
                            if(w.targetBody.getMorality() > 33)
                            {
                                if(w.targetBody.getConfidence() > 66)
                                {
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
                                    w.targetBody.say(t, "Ergh...  I've tried everything else.  Maybe this is the only way to win...");
                                } else
                                if(w.targetBody.getConfidence() > 33)
                                {
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FEAR);
                                    w.targetBody.say(t, "I suppose my powers will keep you from doing anything too extreme to me...  Alright.");
                                } else
                                {
                                    Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.SHAME);
                                    if(w.targetBody.getGender() == Forsaken.Gender.MALE)
                                        w.targetBody.say(t, "I... I won't let you use my b-butt!  ");
                                    else
                                        w.targetBody.say(t, "I... I won't give you my v-virginity!  ");
                                    w.append(t, "If that's okay with you, though... then I accept.");
                                }
                            } else
                            if(w.targetBody.getConfidence() > 66)
                            {
                                Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.NEUTRAL);
                                if(w.targetBody.getGender() == Forsaken.Gender.MALE)
                                    w.targetBody.say(t, "I'd never let some disgusting Demon use my ass ");
                                else
                                    w.targetBody.say(t, "I'd never give up my virginity ");
                                w.targetBody.say(t, "for the sake of this worthless city.  That's where I'm drawing the line, take it or leave it.");
                            } else
                            if(w.targetBody.getConfidence() > 33)
                            {
                                Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.SHAME);
                                w.targetBody.say(t, "I suppose... I could give it a try.  As long as what you do to me isn't any worse than what you do when we fight.");
                            } else
                            {
                                Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FEAR);
                                w.targetBody.say(t, "I-It's not like I was hoping for you to offer something like this!  Just... don't do anything that hurts, or I'll call the deal off right away!");
                            }
                            w.targetBody.say(t, "\"\n\n");
                            w.append(t, (new StringBuilder("Agreeing will prevent you from battling and from using the Assist Raid training action today.  However, it will open the new 'Appeal' option during the final battle, which deals ")).append((w.achievementHeld(8)[0] + 1) * (w.achievementHeld(7)[0] + 1)).append("% Resolve damage and can be upgraded through Negotiating more on subsequent days.").toString());
                        }
                        JButton Accept = new JButton("Agree");
                        Accept.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                p.removeAll();
                                w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                                w.targetBody.chosenOwner.truce = Boolean.valueOf(true);
                                w.targetBody.currentHATE = w.targetBody.currentHATE / 5L;
                                if(w.targetBody.chosenOwner.negotiations == 0)
                                {
                                    w.underlineAppend(t, "Morality/Dignity Distortion");
                                    w.append(t, "\n\n");
                                    if(w.targetBody.chosenOwner.morality > 66 || w.targetBody.chosenOwner.dignity > 66)
                                        w.addBreak(18);
                                    if(w.targetBody.getMorality() > 66)
                                    {
                                        if(w.targetBody.getConfidence() > 66)
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append("'s sense of responsibility demands that ").append(w.targetBody.heShe()).append(" use every tool at ").append(w.targetBody.hisHer()).append(" disposal to protect the city.  ").toString());
                                        else
                                        if(w.targetBody.getConfidence() > 33)
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" has always believed in putting other people's happiness ahead of ").append(w.targetBody.hisHer()).append(" own.  ").toString());
                                        else
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append("'s low self-esteem has always caused ").append(w.targetBody.himHer()).append(" to view ").append(w.targetBody.hisHer()).append(" body as a disposable asset to be spent for the sake of other people.  ").toString());
                                        if(w.targetBody.getInnocence() > 66)
                                            w.append(t, (new StringBuilder("Every time the Demons kidnap another civilian or torment one of ")).append(w.targetBody.hisHer()).append(" allies, ").append(w.targetBody.heShe()).append(" blames ").append(w.targetBody.himHer()).append("self for not being smarter and stronger, ").toString());
                                        else
                                        if(w.targetBody.getInnocence() > 33)
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.HisHer()))).append(" constant defeats have made ").append(w.targetBody.himHer()).append(" desperate, ").toString());
                                        else
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.HeShe()))).append("'s been forced to conclude that ").append(w.targetBody.heShe()).append(" simply isn't strong enough to fulfill ").append(w.targetBody.hisHer()).append(" obligations through straightforward combat alone, ").toString());
                                        if(w.targetBody.getDignity() > 66)
                                            w.append(t, (new StringBuilder("and even though it shames ")).append(w.targetBody.himHer()).append(" to the core to willingly engage in depraved acts with the Demon Lord, ").append(w.targetBody.heShe()).append("'s begun to develop a fetish for that kind of shame.").toString());
                                        else
                                        if(w.targetBody.getDignity() > 33)
                                            w.append(t, (new StringBuilder("but as")).append(w.targetBody.heShe()).append("'s grown addicted to ").append(w.targetBody.hisHer()).append(" sexual encounters with the Demons, ").append(w.targetBody.heShe()).append("'s subconsciously started to look for excuses to expose ").append(w.targetBody.himHer()).append("self to assault, and this one is perfect.").toString());
                                        else
                                            w.append(t, (new StringBuilder("but ")).append(w.targetBody.heShe()).append(" actually feels some pride at finding an alternative way to stop the Demons' attacks.  ").append(w.targetBody.HeShe()).append(" sees no reason at all to be ashamed of using ").append(w.targetBody.hisHer()).append(" sex appeal like this.").toString());
                                    } else
                                    if(w.targetBody.getDignity() > 66)
                                    {
                                        if(w.targetBody.getMorality() > 33)
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" has grown addicted to the feeling of being praised by the public as an unbeatable hero.  ").toString());
                                        else
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" wants to build a celebrity identity founded on ").append(w.targetBody.hisHer()).append(" successes against the Demons.  ").toString());
                                        if(w.targetBody.getConfidence() > 66)
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.HeShe()))).append(" isn't used to failing, and the thought of being seen as a failure by others is even more foreign and terrifying.  ").append(w.targetBody.HeShe()).append("'s completely unprepared to deal with it.  ").toString());
                                        else
                                        if(w.targetBody.getConfidence() > 33)
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.HeShe()))).append(" knows that it's only a matter of time until everyone else finds out how weak ").append(w.targetBody.heShe()).append(" is, and ").append(w.targetBody.heShe()).append("'s willing to do anything to push that day further into the future.  ").toString());
                                        else
                                            w.append(t, (new StringBuilder("Lacking a sense of self-worth, ")).append(w.targetBody.heShe()).append("'s become completely dependent on the esteem of others, and ").append(w.targetBody.heShe()).append(" knows that it's only a matter of time until everyone finds out about ").append(w.targetBody.hisHer()).append(" failures.  ").toString());
                                        if(w.targetBody.getInnocence() > 66)
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.HeShe()))).append(" jumps on the chance to put a stop to the daily defeats, even if only briefly, and it doesn't even occur to ").append(w.targetBody.himHer()).append(" that ").append(w.targetBody.hisHer()).append(" willingness to use sexual methods is a sign that ").append(w.targetBody.heShe()).append("'s already lost ").append(w.targetBody.hisHer()).append(" grip on the cute, innocent image ").append(w.targetBody.heShe()).append(" always presented to the public.").toString());
                                        else
                                        if(w.targetBody.getInnocence() > 33)
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.HeShe()))).append(" isn't happy to be forced into such a humiliating situation, but ").append(w.targetBody.heShe()).append("'s always been willing to do whatever was necessary for ").append(w.targetBody.hisHer()).append(" image, and a part of ").append(w.targetBody.himHer()).append(" takes a sexual satisfaction in continuing to present ").append(w.targetBody.himHer()).append("self as a pure-hearted hero even while ").append(w.targetBody.heShe()).append(" engages in depraved acts with the Demon Lord.").toString());
                                        else
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.HeShe()))).append(" knows that allowing the Demon Lord to personally train ").append(w.targetBody.hisHer()).append(" body will only accelerate what's already happening to ").append(w.targetBody.himHer()).append(", but ").append(w.targetBody.heShe()).append("'s come to the decision that being able to claim credit for the lack of Demonic attacks is worth it.").toString());
                                    } else
                                    if(w.targetBody.getConfidence() > 66)
                                    {
                                        if(w.targetBody.getInnocence() > 66)
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append("'s ego has been utterly shattered by the sexual tortures ").append(w.targetBody.heShe()).append("'s been unable to prevent.  ").toString());
                                        else
                                        if(w.targetBody.getInnocence() > 33)
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" is still partially in denial about ").append(w.targetBody.hisHer()).append(" utter defeat and sexual training at the hands of the Demons.  ").toString());
                                        else
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" has been forced to re-evaluate ").append(w.targetBody.himHer()).append("self after finding that ").append(w.targetBody.heShe()).append(" was actually neither strong enough nor smart enough to avoid being used as a sexual plaything.  ").toString());
                                        if(w.targetBody.getDignity() > 33)
                                            w.append(t, (new StringBuilder("The continued support of the public is the only thing holding ")).append(w.targetBody.hisHer()).append(" self-esteem together, and ").append(w.targetBody.heShe()).append("'ll do anything to keep it.  ").toString());
                                        else
                                            w.append(t, (new StringBuilder("The only thing ")).append(w.targetBody.heShe()).append(" can be proud of is ").append(w.targetBody.hisHer()).append(" sexual prowess, and ").append(w.targetBody.heShe()).append("'s decided to embrace that fact.  ").toString());
                                        if(w.targetBody.getMorality() > 33)
                                            w.append(t, (new StringBuilder("The idea of being responsible for the city's safety again is like a soothing balm for ")).append(w.targetBody.hisHer()).append(" pride.  ").append(w.targetBody.HeShe()).append(" doesn't even care that ").append(w.targetBody.heShe()).append(" has to sell ").append(w.targetBody.hisHer()).append(" body to buy that safety.").toString());
                                        else
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.HeShe()))).append(" doesn't actually care about protecting the people, but ").append(w.targetBody.heShe()).append("'s so desperate to 'win' at something that ").append(w.targetBody.heShe()).append("'s even willing to serve ").append(w.targetBody.hisHer()).append(" hated enemy.").toString());
                                    } else
                                    {
                                        if(w.targetBody.getConfidence() > 33)
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" used to enjoy fighting, but that was back when ").append(w.targetBody.hisHer()).append(" foes were much weaker.  ").toString());
                                        else
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" never enjoyed fighting, and lately it's become even more unpleasant than usual.  ").toString());
                                        if(w.targetBody.getMorality() > 33)
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.HeShe()))).append(" kept fighting because ").append(w.targetBody.heShe()).append(" felt like ").append(w.targetBody.heShe()).append(" had to, but now that there's a way to help everyone without doing all that work, ").append(w.targetBody.heShe()).append("'s happy for the excuse to stop.  ").toString());
                                        else
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.HeShe()))).append("'s started to look for any excuse at all to avoid showing up to battle, and as far as ").append(w.targetBody.heShe()).append("'s concerned, the one that's just been offered by the Demon Lord is perfect.  ").toString());
                                        if(w.targetBody.getDignity() > 33)
                                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.HeShe()))).append("'s worried about everyone else finding out that ").append(w.targetBody.heShe()).append("'s doing naughty things, but at the same time, the fear of being discovered has also started to give ").append(w.targetBody.himHer()).append(" a strange sense of shameful pleasure.").toString());
                                        else
                                            w.append(t, (new StringBuilder("The fact that it also involves doing more of the sexual acts ")).append(w.targetBody.heShe()).append("'s started to enjoy is an added bonus.").toString());
                                    }
                                    w.append(t, (new StringBuilder("\n\nRelaxing ")).append(w.targetBody.hisHer()).append(" posture very slightly, ").append(w.targetBody.ownerName()).append(" allows the Demon Lord to get closer.").toString());
                                } else
                                if(w.targetBody.chosenOwner.negotiations < 3)
                                    w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" lets ").append(w.targetBody.hisHer()).append(" guard down slightly, allowing the Demon Lord to get closer.").toString());
                                else
                                    w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" smiles with satisfaction.  ").append(w.targetBody.HeShe()).append(" had already let ").append(w.targetBody.hisHer()).append(" guard down, but now ").append(w.targetBody.heShe()).append(" actively encourages the Demon Lord to get closer.").toString());
                                w.targetBody.chosenOwner.negotiations++;
                                JButton Continue = new JButton("Continue");
                                Continue.addActionListener(new ActionListener() {

                                    public void actionPerformed(ActionEvent e)
                                    {
                                        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).toString());
                                        PickActivity(t, p, f, w, s);
                                    }

                                    final _cls1 this$2;
                                    private final WorldState val$w;
                                    private final JTextPane val$t;
                                    private final JPanel val$p;
                                    private final JFrame val$f;
                                    private final SaveData val$s;

                        
                        {
                            this$2 = _cls1.this;
                            w = worldstate;
                            t = jtextpane;
                            p = jpanel;
                            f = jframe;
                            s = savedata;
                            super();
                        }
                                });
                                p.add(Continue);
                                p.validate();
                                p.repaint();
                            }

                            final _cls33 this$1;
                            private final JPanel val$p;
                            private final WorldState val$w;
                            private final JTextPane val$t;
                            private final JFrame val$f;
                            private final SaveData val$s;


                    
                    {
                        this$1 = _cls33.this;
                        p = jpanel;
                        w = worldstate;
                        t = jtextpane;
                        f = jframe;
                        s = savedata;
                        super();
                    }
                        });
                        Accept.setBackground(Project.PURPLISH);
                        p.add(Accept);
                        JButton Refuse = new JButton("Refuse");
                        Refuse.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                PickActivity(t, p, f, w, s);
                            }

                            final _cls33 this$1;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;
                            private final WorldState val$w;
                            private final SaveData val$s;

                    
                    {
                        this$1 = _cls33.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        w = worldstate;
                        s = savedata;
                        super();
                    }
                        });
                        p.add(Refuse);
                        p.validate();
                        p.repaint();
                    }

                    final Body this$0;
                    private final JPanel val$p;
                    private final WorldState val$w;
                    private final JTextPane val$t;
                    private final String val$shownNames[];
                    private final JFrame val$f;
                    private final SaveData val$s;


            
            {
                this$0 = Body.this;
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                shownNames = as;
                f = jframe;
                s = savedata;
                super();
            }
                });
                Negotiate.setBackground(Project.PURPLISH);
                p.add(Negotiate);
            }
            JButton Done = new JButton("End");
            Done.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    p.removeAll();
                    w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
                    if(w.targetBody.getPLEALevel() >= 4 && w.targetBody.orgasms > 1)
                    {
                        if(w.targetBody.getObedience() > 66)
                            w.append(t, (new StringBuilder("As ")).append(w.lordBody.ownerName()).append(" leaves, ").append(w.targetBody.ownerName()).append(" lays sprawled out on the floor, an expression of complete contentment on ").append(w.targetBody.hisHer()).append(" face in the afterglow of ").append(w.targetBody.hisHer()).append(" multiple orgasms.").toString());
                        else
                        if(w.targetBody.getObedience() > 33)
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" wears a conflicted expression on ").append(w.targetBody.hisHer()).append(" face as ").append(w.targetBody.heShe()).append(" watches ").append(w.lordBody.ownerName()).append(" leave.  On one hand, ").append(w.targetBody.heShe()).append("'s not sure that ").append(w.targetBody.hisHer()).append(" body could have endured any more orgasms in such a short time period.  But on the other hand, the part of ").append(w.targetBody.himHer()).append(" that's getting attached to ").append(w.lordBody.ownerName()).append(" wants to be broken even harder...").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" looks relieved to see ").append(w.lordBody.ownerName()).append(" go.  ").append(w.targetBody.HeShe()).append("'s humiliated by how easily ").append(w.lordBody.ownerName()).append(" was able to make ").append(w.targetBody.himHer()).append(" cum, and the sooner ").append(w.targetBody.heShe()).append(" can forget it, the better.").toString());
                    } else
                    if(w.targetBody.getHATELevel() >= 3)
                    {
                        if(w.targetBody.getDeviancy() > 66)
                        {
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" glares at the leaving ").append(w.lordBody.ownerName()).append(", but as soon as ").append(w.lordBody.heShe()).append("'s out of sight, ").toString());
                            if(w.sceneLocation == Activity.Location.STAGE)
                                w.append(t, (new StringBuilder(String.valueOf(w.targetBody.ownerName()))).append(" runs off to find a place ").append(w.targetBody.heShe()).append(" won't be seen ").toString());
                            else
                                w.append(t, (new StringBuilder(String.valueOf(w.targetBody.ownerName()))).append(" turns around ").toString());
                            w.append(t, (new StringBuilder("and starts masturbating furiously, hating ")).append(w.targetBody.himHer()).append("self for lusting after ").append(w.lordBody.ownerName()).append(" even now.").toString());
                        } else
                        if(w.targetBody.getDeviancy() > 33)
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" is huffing and scowling, face bright red as ").append(w.lordBody.ownerName()).append(" leaves, but it's only partly from anger.  Being treated in such a demeaning way by ").append(w.lordBody.ownerName()).append(" has started to turn ").append(w.targetBody.himHer()).append(" on, and a part of ").append(w.targetBody.himHer()).append(" wishes it would continue.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" glares off after ").append(w.lordBody.ownerName()).append(", even after ").append(w.lordBody.ownerName()).append(", is gone.  An expression of cold anger remains on ").append(w.targetBody.hisHer()).append(" face, mixed with the despair of knowing there's nothing ").append(w.targetBody.heShe()).append(" can do.").toString());
                    } else
                    if(w.targetBody.getINJULevel() >= 3)
                    {
                        if(w.targetBody.getObedience() > 66)
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" sleepily apologizes to ").append(w.lordBody.ownerName()).append(" for not being able to keep going, then heads off to get some rest.").toString());
                        else
                        if(w.targetBody.getObedience() > 33)
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" sighs with relief at seeing that ").append(w.lordBody.ownerName()).append(" is done with ").append(w.targetBody.himHer()).append(", then heads off to get some rest.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" looks angry at having been worn out by ").append(w.lordBody.ownerName()).append(".  ").append(w.targetBody.HeShe()).append(" struggles to stay upright and ready to defend ").append(w.targetBody.himHer()).append("self until ").append(w.lordBody.ownerName()).append(" is out of sight.").toString());
                    } else
                    if(w.targetBody.getObedience() > 66)
                    {
                        if(w.targetBody.getPLEALevel() == 3 && w.targetBody.orgasms > 0)
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" sighs with satisfaction, thanking ").append(w.lordBody.ownerName()).append(" for taking the time to come see ").append(w.targetBody.himHer()).append(".").toString());
                        else
                        if(w.targetBody.getHATELevel() == 2)
                            w.append(t, (new StringBuilder("As ")).append(w.lordBody.ownerName()).append(" starts to leave, ").append(w.targetBody.ownerName()).append(" realizes that ").append(w.targetBody.heShe()).append(" might have offended ").append(w.lordBody.himHer()).append(", and ").append(w.targetBody.heShe()).append(" gets down on ").append(w.targetBody.hisHer()).append(" knees, tearfully apologizing and promising to do better for ").append(w.lordBody.ownerName()).append(" next time.").toString());
                        else
                        if(w.targetBody.getINJULevel() == 2)
                        {
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" insists that ").append(w.targetBody.heShe()).append(" can keep going, but it's clear that ").append(w.targetBody.heShe()).append("'s becoming fatigued.  Finally, ").append(w.targetBody.heShe()).append(" bows ").append(w.targetBody.hisHer()).append(" head thanks ").append(w.lordBody.ownerName()).append(" for visiting ").append(w.targetBody.himHer()).append(".").toString());
                        } else
                        {
                            if(w.targetBody.getGender() != Forsaken.Gender.MALE || !w.targetBody.isVVirg().booleanValue())
                                w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" curtsies ").toString());
                            else
                                w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" bows ").toString());
                            w.append(t, (new StringBuilder("deeply, thanking ")).append(w.lordBody.ownerName()).append(" for visiting ").append(w.targetBody.himHer()).append(".").toString());
                        }
                    } else
                    if(w.targetBody.getDeviancy() > 66)
                    {
                        if(w.targetBody.getPLEALevel() == 3)
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" is incredibly turned on and not even close to being satisfied with this much pleasure.  ").append(w.targetBody.HeShe()).append(" tries to stop ").append(w.lordBody.ownerName()).append(" from leaving, and only after it becomes clear that it's hopeless does ").append(w.targetBody.heShe()).append(" go back into ").append(w.targetBody.hisHer()).append(" room to masturbate until ").append(w.targetBody.heShe()).append(" can barely even move ").append(w.targetBody.hisHer()).append(" hands.").toString());
                        else
                        if(w.targetBody.getHATELevel() == 2)
                            w.append(t, (new StringBuilder("Even before ")).append(w.lordBody.ownerName()).append(" is out of sight, ").append(w.targetBody.ownerName()).append(" has started masturbating to the memory of the degrading things ").append(w.lordBody.heShe()).append(" has put ").append(w.targetBody.himHer()).append(" through.  ").append(w.targetBody.HisHer()).append(" angry expression fades away into pure lewdness.").toString());
                        else
                        if(w.targetBody.getINJULevel() == 2)
                            w.append(t, (new StringBuilder("When ")).append(w.lordBody.ownerName()).append(" leaves, ").append(w.targetBody.ownerName()).append(" tries to get some rest, but ").append(w.targetBody.heShe()).append(" ends up playing with ").append(w.targetBody.himHer()).append("self in bed, masturbating to the point of orgasm several times before ").append(w.targetBody.heShe()).append("'s finally tired enough to sleep.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" had been hoping on some level to have ").append(w.targetBody.hisHer()).append(" sensitive body tormented by ").append(w.lordBody.ownerName()).append(".  ").append(w.targetBody.HeShe()).append(" sighs, then heads off to masturbate some more.").toString());
                    } else
                    if(w.targetBody.forsakenOwner == null && w.targetBody.chosenOwner != null)
                    {
                        if(w.targetBody.chosenOwner.negotiations > 0)
                        {
                            if(w.targetBody.getPLEALevel() == 3)
                                w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" can't meet ").append(w.lordBody.ownerName()).append("'s gaze as they part ways.  ").append(w.targetBody.HeShe()).append(" is still conflicted about feeling so much pleasure from ").append(w.lordBody.ownerName()).append("'s touch.").toString());
                            else
                            if(w.targetBody.getHATELevel() == 2)
                                w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" glares at ").append(w.lordBody.ownerName()).append(", but ").append(w.targetBody.heShe()).append(" still manages to remain civil as ").append(w.targetBody.heShe()).append(" says goodbye.  ").append(w.targetBody.HeShe()).append("'s proud of ").append(w.targetBody.himHer()).append("self for maintaining ").append(w.targetBody.hisHer()).append(" self-control.").toString());
                            else
                            if(w.targetBody.getINJULevel() == 2)
                                w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" slumps down onto ").append(w.targetBody.hisHer()).append(" bottom as soon as ").append(w.lordBody.ownerName()).append(" leaves.  ").append(w.targetBody.HeShe()).append("'s glad that ").append(w.targetBody.heShe()).append(" was able to last long enough to satisfy ").append(w.lordBody.ownerName()).append(".").toString());
                            else
                            if(w.targetBody.chosenOwner.truce.booleanValue())
                            {
                                if(w.targetBody.getInnocence() > 66)
                                    w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" briefly wonders why ").append(w.lordBody.ownerName()).append(" would go so easy on ").append(w.targetBody.himHer()).append(", but ").append(w.targetBody.heShe()).append(" soon loses ").append(w.targetBody.hisHer()).append(" train of thought, shrugs to ").append(w.targetBody.himHer()).append("self, and then goes about enjoying ").append(w.targetBody.hisHer()).append(" sudden day off.").toString());
                                else
                                if(w.targetBody.getInnocence() > 33)
                                    w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" repeatedly asks ").append(w.lordBody.ownerName()).append(" to make sure that their encounter here was good enough to meet the terms of the truce, but when ").append(w.lordBody.ownerName()).append(" confirms that it is, ").append(w.targetBody.ownerName()).append(" nods in satisfaction and lets ").append(w.lordBody.ownerName()).append(" go.").toString());
                                else
                                    w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append("'s eyes narrow with suspicion at ").append(w.lordBody.ownerName()).append(" being satisfied so easily, but there's nothing ").append(w.targetBody.heShe()).append(" can complain about or object to, so there's nothing ").append(w.targetBody.heShe()).append(" can do but watch ").append(w.lordBody.ownerName()).append(" go.").toString());
                            } else
                            if(w.targetBody.chosenOwner.ANGST >= 0x3b9aca00L)
                            {
                                if(w.targetBody.getConfidence() > 66)
                                    w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" looks annoyed at being disturbed without even getting a truce out of it, but ").append(w.targetBody.heShe()).append(" remains civil as ").append(w.lordBody.ownerName()).append(" leaves.").toString());
                                else
                                if(w.targetBody.getConfidence() > 33)
                                    w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" is clearly disappointed that ").append(w.lordBody.ownerName()).append(" didn't offer ").append(w.targetBody.himHer()).append(" a truce this time, but ").append(w.targetBody.heShe()).append(" just starts to prepare for the day's battle as soon as ").append(w.lordBody.ownerName()).append(" leaves.").toString());
                                else
                                    w.append(t, (new StringBuilder("As soon as ")).append(w.lordBody.ownerName()).append(" starts to leave, ").append(w.targetBody.ownerName()).append("'s eyes widen with fear, and ").append(w.targetBody.heShe()).append(" spends the rest of the day wondering if ").append(w.targetBody.heShe()).append(" did something wrong, and if there won't be any more truces as a result.").toString());
                            } else
                            if(w.targetBody.getMorality() > 66)
                                w.append(t, (new StringBuilder("As ")).append(w.targetBody.ownerName()).append(" watches ").append(w.lordBody.ownerName()).append(" leave, ").append(w.targetBody.heShe()).append(" feels proud of ").append(w.targetBody.himHer()).append("self for resisting the urge to offer another truce.  ").append(w.targetBody.HeShe()).append(" feels like ").append(w.targetBody.heShe()).append(" might be able to win in a straight fight now.").toString());
                            else
                            if(w.targetBody.getMorality() > 33)
                                w.append(t, (new StringBuilder("As soon as ")).append(w.lordBody.ownerName()).append(" leaves, ").append(w.targetBody.ownerName()).append(" starts to prepare for battle, feeling only a little regretful about not making another truce.").toString());
                            else
                                w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" smirks after the leaving ").append(w.lordBody.ownerName()).append(", happy to have played hard-to-get by resisting the temptation of making another truce.  ").append(w.targetBody.HeShe()).append(" daydreams about getting some even better terms from ").append(w.lordBody.ownerName()).append(".").toString());
                        } else
                        if(w.targetBody.getPLEALevel() == 3)
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" feels violated after having ").append(w.targetBody.hisHer()).append(" body so suddenly toyed with, and ").append(w.targetBody.heShe()).append(" tries to give chase, but the pleasure makes ").append(w.targetBody.hisHer()).append(" legs wobble, and ").append(w.targetBody.heShe()).append(" falls to ").append(w.targetBody.hisHer()).append(" knees, unable to stop ").append(w.lordBody.ownerName()).append(" from leaving.").toString());
                        else
                        if(w.targetBody.getHATELevel() == 2)
                            w.append(t, (new StringBuilder("As soon as it looks like ")).append(w.lordBody.ownerName()).append(" is about to leave, ").append(w.targetBody.ownerName()).append(" attacks with ").append(w.targetBody.hisHer()).append(" ").append(w.targetBody.chosenOwner.weapon).append(".  Killing ").append(w.lordBody.ownerName()).append("'s body is pointless, but it does make ").append(w.targetBody.ownerName()).append(" feel better.").toString());
                        else
                        if(w.targetBody.getINJULevel() == 2)
                            w.append(t, (new StringBuilder("When ")).append(w.lordBody.ownerName()).append(" starts to leave, ").append(w.targetBody.ownerName()).append(" steps back as well, holding ").append(w.targetBody.himHer()).append("self cautiously.  The encounter has been taxing on ").append(w.targetBody.hisHer()).append(" body and mind, and that gives ").append(w.lordBody.ownerName()).append(" the chance to slip away.").toString());
                        else
                        if(w.targetBody.getMorality() > 66)
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" still isn't sure why ").append(w.lordBody.ownerName()).append(" approached ").append(w.targetBody.himHer()).append(", but ").append(w.targetBody.heShe()).append(" respects ").append(w.targetBody.hisHer()).append(" opponent's seemingly peaceful intentions and allows ").append(w.lordBody.ownerName()).append(" to escape.").toString());
                        else
                        if(w.targetBody.getMorality() > 33)
                            w.append(t, (new StringBuilder("When ")).append(w.lordBody.ownerName()).append(" moves to leave, ").append(w.targetBody.ownerName()).append(" hesitates, considering whether ").append(w.targetBody.heShe()).append(" should kill this body just to hamper the Demon Lord, even if only a little bit.  But ").append(w.targetBody.heShe()).append(" ultimately decides that there's no point and lets ").append(w.lordBody.ownerName()).append(" go.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" doesn't feel any need to pursue ").append(w.lordBody.ownerName()).append(", so ").append(w.targetBody.heShe()).append(" just shrugs and continues about ").append(w.targetBody.hisHer()).append(" daily routine.").toString());
                    } else
                    if(w.targetBody.getPLEALevel() == 3 && w.targetBody.orgasms > 0)
                        w.append(t, (new StringBuilder("Caught up in the afterglow, ")).append(w.targetBody.ownerName()).append(" almost doesn't notice ").append(w.lordBody.ownerName()).append(" leaving.  ").append(w.targetBody.HeShe()).append(" hesitates, feeling awkward about what just happened, and then ").append(w.lordBody.ownerName()).append(" is gone.").toString());
                    else
                    if(w.targetBody.getHATELevel() == 2)
                        w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" glares after ").append(w.lordBody.ownerName()).append(", tempted to try to shout some insult after ").append(w.lordBody.ownerName()).append(".  But ultimately, ").append(w.targetBody.heShe()).append(" decides to just be grateful that it's over.").toString());
                    else
                    if(w.targetBody.getINJULevel() == 2)
                        w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" slumps over, glad that ").append(w.lordBody.ownerName()).append(" is satisfied.  As soon as ").append(w.lordBody.ownerName()).append(" is gone, ").append(w.targetBody.ownerName()).append(" goes to get some rest.").toString());
                    else
                    if(w.targetBody.getObedience() > w.targetBody.getDeviancy())
                    {
                        if(w.targetBody.getConfidence() > 66)
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" huffs in mild annoyance at being disturbed for no reason, but ").append(w.targetBody.heShe()).append("'s still careful to be courteous to ").append(w.lordBody.ownerName()).append(" as ").append(w.lordBody.heShe()).append(" leaves.").toString());
                        else
                        if(w.targetBody.getConfidence() > 33)
                        {
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" gives ").append(w.lordBody.ownerName()).append(" ").toString());
                            if(w.targetBody.getGender() != Forsaken.Gender.MALE || !w.targetBody.isVVirg().booleanValue())
                                w.append(t, "a small cursty ");
                            else
                                w.append(t, "a small bow ");
                            w.append(t, (new StringBuilder("as ")).append(w.lordBody.heShe()).append(" leaves.").toString());
                        } else
                        {
                            w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" is startled by ").append(w.lordBody.ownerName()).append("'s sudden departure, and ").append(w.targetBody.heShe()).append(" spends the rest of the day nervously wondering if ").append(w.targetBody.heShe()).append(" displeased ").append(w.lordBody.ownerName()).append(" somehow.").toString());
                        }
                    } else
                    if(w.targetBody.getInnocence() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" is happy to see ").append(w.lordBody.ownerName()).append(" leave, but ").append(w.targetBody.heShe()).append(" also feels a strange sadness.  ").append(w.targetBody.HeShe()).append("'s unable to consciously acknowledge that ").append(w.targetBody.heShe()).append("'s come to enjoy being sexually trained, and so ").append(w.targetBody.heShe()).append(" continues to wonder about it until something finally distracts ").append(w.targetBody.himHer()).append(".").toString());
                    else
                    if(w.targetBody.getInnocence() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" doesn't feel anything unusual as ").append(w.lordBody.ownerName()).append(" leaves, but that evening, when ").append(w.targetBody.heShe()).append("'s masturbating, fantasies of the things ").append(w.lordBody.ownerName()).append(" might do to ").append(w.targetBody.himHer()).append(" next time will continually intrude on ").append(w.targetBody.hisHer()).append(" thoughts.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(w.targetBody.OwnerName()))).append(" feels a sense of regret at seeing ").append(w.lordBody.ownerName()).append(" leave, and when ").append(w.targetBody.heShe()).append(" realizes that it's because ").append(w.targetBody.heShe()).append(" was looking forward to being sexually trained, ").append(w.targetBody.heShe()).append(" gets angry at ").append(w.targetBody.himHer()).append("self for being so weak.").toString());
                    JButton Continue = new JButton("Continue");
                    Continue.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e)
                        {
                            if(w.active.booleanValue())
                            {
                                Project.Shop(t, p, f, w);
                            } else
                            {
                                WriteObject wobj = new WriteObject();
                                wobj.serializeSaveData(s);
                                Project.IntroOne(t, p, f, w);
                            }
                        }

                        final _cls34 this$1;
                        private final WorldState val$w;
                        private final JTextPane val$t;
                        private final JPanel val$p;
                        private final JFrame val$f;
                        private final SaveData val$s;

                    
                    {
                        this$1 = _cls34.this;
                        w = worldstate;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        s = savedata;
                        super();
                    }
                    });
                    p.add(Continue);
                    p.validate();
                    p.repaint();
                }

                final Body this$0;
                private final JPanel val$p;
                private final WorldState val$w;
                private final JTextPane val$t;
                private final JFrame val$f;
                private final SaveData val$s;

            
            {
                this$0 = Body.this;
                p = jpanel;
                w = worldstate;
                t = jtextpane;
                f = jframe;
                s = savedata;
                super();
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
            Boolean ending = Boolean.valueOf(false);
            for(int i = 0; i < inProgress.length; i++)
            {
                int value = inProgress[i].weight(w, this, targets[i]);
                int validEnderIndex = -1;
                for(int j = 0; j < inProgress[i].enders.length; j++)
                    if(inProgress[i].enders[j].valid(this, targets[i]).booleanValue())
                    {
                        validEnderIndex = j;
                        j = inProgress[i].enders.length;
                    }

                if(value < extreme && validEnderIndex >= 0)
                {
                    ending = Boolean.valueOf(true);
                    extreme = value;
                    pickedActivity = inProgress[i].enders[validEnderIndex];
                    if(pickedActivity.targeted.booleanValue())
                        target = consideredTarget;
                    else
                        target = null;
                }
            }

            for(int i = 0; i < activityWeights.length && !ending.booleanValue(); i++)
                if(Project.allActivities[i].pickable.booleanValue() && Project.allActivities[i].valid(this, w.lordBody).booleanValue())
                {
                    int value = Project.allActivities[i].weight(w, this, w.lordBody);
                    if(value > extreme)
                    {
                        extreme = value;
                        pickedActivity = Project.allActivities[i];
                        if(pickedActivity.targeted.booleanValue())
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
            for(; totalDamage >= 0x989680L; totalDamage /= 0xf4240L)
                tier++;

            Boolean mixed = Boolean.valueOf(false);
            if(tier > 0)
            {
                remainChar = '#';
                mixed = Boolean.valueOf(true);
            }
            if(totalDamage >= 100L)
            {
                remainChar = fillChar;
                fillChar = '-';
                increment = 100;
                mixed = Boolean.valueOf(false);
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
            if(totalDamage >= 0x186a0L)
            {
                remainChar = fillChar;
                fillChar = 'X';
                increment = 0x186a0;
            }
            if(totalDamage >= 0xf4240L)
            {
                remainChar = fillChar;
                fillChar = '#';
                increment = 0xf4240;
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
                if(tier == 0 || tier == 1 && mixed.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(remainChar))).toString());
                else
                if(tier == 1 || tier == 2 && mixed.booleanValue())
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
                    if(currentDamage()[i] < 0x186a0L)
                        w.append(t, "desperate to please");
                    else
                    if(currentDamage()[i] < 0xf4240L)
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
                    if(currentDamage()[i] < 0x186a0L)
                        w.append(t, "struggling not to be sick");
                    else
                    if(currentDamage()[i] < 0xf4240L)
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
                    if(currentDamage()[i] < 0x186a0L)
                        w.append(t, "reeling from the pain");
                    else
                    if(currentDamage()[i] < 0xf4240L)
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
                    if(currentDamage()[i] < 0x186a0L)
                        w.append(t, (new StringBuilder("can't stop thinking about how ")).append(heShe()).append(" looks").toString());
                    else
                    if(currentDamage()[i] < 0xf4240L)
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
                    if(currentDamage()[i] < 0x186a0L)
                        w.append(t, "too angry to cooperate");
                    else
                    if(currentDamage()[i] < 0xf4240L)
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
                    if(currentDamage()[i] < 0x186a0L)
                        w.append(t, "orgasmic pleasure");
                    else
                    if(currentDamage()[i] < 0xf4240L)
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
                    if(currentDamage()[i] < 0x186a0L)
                        w.append(t, "completely spent");
                    else
                    if(currentDamage()[i] < 0xf4240L)
                        w.append(t, "unconscious");
                    else
                        w.append(t, "body failing under the strain");
                } else
                if(i == 7)
                    if(isParasitized().booleanValue())
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
                        if(currentDamage()[i] < 0x186a0L)
                        {
                            if(getGender().equals(Forsaken.Gender.MALE))
                                w.append(t, "asshole");
                            else
                                w.append(t, "pussy");
                            w.append(t, " uncovered for easy access");
                        } else
                        if(currentDamage()[i] < 0xf4240L)
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
                    if(currentDamage()[i] < 0x186a0L)
                        w.append(t, "shredded clothes sometimes expose everything");
                    else
                    if(currentDamage()[i] < 0xf4240L)
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
            if(!orgasmPossible().booleanValue())
            {
                w.purpleAppend(t, (new StringBuilder("\n\nPLEA up!  ")).append(OwnerName()).append(" wildly bucks ").append(hisHer()).append(" hips in search of orgasm, but ").append(heShe()).append(" can't give ").append(himHer()).append("self the necessary stimulation, and ").append(heShe()).append(" comes back from the brink of climax.").toString());
            } else
            {
                Body givers[] = new Body[0];
                for(int i = 0; i < inProgress.length; i++)
                    if(inProgress[i].causesOrgasm.booleanValue() && inProgress[i].counterpart != null && targets[i] != null && targets[i].forsakenOwner != null)
                    {
                        Boolean found = Boolean.valueOf(false);
                        for(int j = 0; j < givers.length; j++)
                            if(givers[j] == targets[i])
                                found = Boolean.valueOf(true);

                        if(!found.booleanValue())
                        {
                            Body newGivers[] = new Body[givers.length + 1];
                            for(int j = 0; j < givers.length; j++)
                                newGivers[j] = givers[j];

                            newGivers[givers.length] = targets[i];
                            givers = newGivers;
                        }
                    }

                for(int i = 0; i < givers.length; i++)
                    if(givers[i] != this && givers[i].forsakenOwner != null)
                        givers[i].forsakenOwner.orgasmsGiven++;

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
            if(i == 7 && currentDamage()[i] + actualDamage[i] > 0xf4240L)
            {
                actualDamage[i] = 0xf4240L - currentDamage()[i];
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
            for(; totalDamage >= 0x989680L; totalDamage /= 0xf4240L)
                tier++;

            Boolean mixed = Boolean.valueOf(false);
            if(tier > 0)
            {
                remainChar = '#';
                mixed = Boolean.valueOf(true);
            }
            if(totalDamage >= 100L)
            {
                remainChar = fillChar;
                fillChar = '-';
                increment = 100;
                mixed = Boolean.valueOf(false);
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
            if(totalDamage >= 0x186a0L)
            {
                remainChar = fillChar;
                fillChar = 'X';
                increment = 0x186a0;
            }
            if(totalDamage >= 0xf4240L)
            {
                remainChar = fillChar;
                fillChar = '#';
                increment = 0xf4240;
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
                if(tier == 0 || tier == 1 && mixed.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(remainChar))).toString());
                else
                if(tier == 1 || tier == 2 && mixed.booleanValue())
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
                    if(currentDamage()[i] < 0x186a0L)
                        w.append(t, "desperate to please");
                    else
                    if(currentDamage()[i] < 0xf4240L)
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
                    if(currentDamage()[i] < 0x186a0L)
                        w.append(t, "struggling not to be sick");
                    else
                    if(currentDamage()[i] < 0xf4240L)
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
                    if(currentDamage()[i] < 0x186a0L)
                        w.append(t, "reeling from the pain");
                    else
                    if(currentDamage()[i] < 0xf4240L)
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
                    if(currentDamage()[i] < 0x186a0L)
                        w.append(t, (new StringBuilder("can't stop thinking about how ")).append(heShe()).append(" looks").toString());
                    else
                    if(currentDamage()[i] < 0xf4240L)
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
                    if(currentDamage()[i] < 0x186a0L)
                        w.append(t, "too angry to cooperate");
                    else
                    if(currentDamage()[i] < 0xf4240L)
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
                    if(currentDamage()[i] < 0x186a0L)
                        w.append(t, "oragsmic pleasure");
                    else
                    if(currentDamage()[i] < 0xf4240L)
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
                    if(currentDamage()[i] < 0x186a0L)
                        w.append(t, "completely spent");
                    else
                    if(currentDamage()[i] < 0xf4240L)
                        w.append(t, "consciousness fading");
                    else
                        w.append(t, "body failing under the strain");
                } else
                if(i == 7)
                    if(isParasitized().booleanValue())
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
                        if(currentDamage()[i] < 0x186a0L)
                        {
                            if(getGender().equals(Forsaken.Gender.MALE))
                                w.append(t, "asshole");
                            else
                                w.append(t, "pussy");
                            w.append(t, " uncovered for easy access");
                        } else
                        if(currentDamage()[i] < 0xf4240L)
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
                    if(currentDamage()[i] < 0x186a0L)
                        w.append(t, "shredded clothes sometimes expose everything");
                    else
                    if(currentDamage()[i] < 0xf4240L)
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
            if(forsakenOwner == null && chosenOwner != null && chosenOwner.vVirg.booleanValue() && getInnocence() > 66)
            {
                w.purpleAppend(t, "\n\nPLEA up!  ");
                w.append(t, (new StringBuilder("The warm feelings surging through ")).append(ownerName()).append("'s lower body are reaching a level ").append(heShe()).append(" never thought possible.  ").append(HeShe()).append(" feels something amazing coming, but the sensations are so unfamiliar and overpowering that ").append(heShe()).append(" starts to panic.").toString());
            } else
            if(!orgasmPossible().booleanValue())
            {
                w.purpleAppend(t, "\n\nPLEA up!  ");
                w.append(t, (new StringBuilder(String.valueOf(OwnerName()))).append(" wildly bucks ").append(hisHer()).append(" hips in search of orgasm, but ").append(heShe()).append(" can't give ").append(himHer()).append("self the necessary stimulation, and ").append(heShe()).append(" comes back from the brink of climax.").toString());
            } else
            {
                Body givers[] = new Body[0];
                for(int i = 0; i < inProgress.length; i++)
                    if(inProgress[i].causesOrgasm.booleanValue() && inProgress[i].counterpart != null && targets[i] != null && targets[i].forsakenOwner != null)
                    {
                        Boolean found = Boolean.valueOf(false);
                        for(int j = 0; j < givers.length; j++)
                            if(givers[j] == targets[i])
                                found = Boolean.valueOf(true);

                        if(!found.booleanValue())
                        {
                            Body newGivers[] = new Body[givers.length + 1];
                            for(int j = 0; j < givers.length; j++)
                                newGivers[j] = givers[j];

                            newGivers[givers.length] = targets[i];
                            givers = newGivers;
                        }
                    }

                for(int i = 0; i < givers.length; i++)
                    if(givers[i] != this && givers[i].forsakenOwner != null)
                        givers[i].forsakenOwner.orgasmsGiven++;

                w.purpleAppend(t, "\n\nOrgasm!  ");
                int intensity = 200 + (int)(Math.random() * 200D);
                if(orgasms == 0)
                {
                    if(currentDamage()[5] - actualDamage[5] >= 10000L && currentDamage()[5] >= 30000L)
                    {
                        w.append(t, (new StringBuilder("The pent-up climax comes all at once, and ")).append(ownerName()).append(" squeals incoherently as ").toString());
                        if(parts[PENIS] > 0)
                            w.append(t, (new StringBuilder(String.valueOf(hisHer()))).append(" cock spurts a huge load.").toString());
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
                int added = 0x493e0 + (int)(Math.random() * 300000D);
                if(forsakenOwner.timesExposed < 0x493e0)
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
        if(previousPLEA >= 0x186a0L)
            previousLevel = (int)(previousPLEA / 0x186a0L + 2L);
        else
        if(previousPLEA >= 30000L)
            previousLevel = 2;
        else
        if(previousPLEA >= 10000L)
            previousLevel = 1;
        if(currentValue >= 0x186a0L)
            currentLevel = (int)(currentValue / 0x186a0L + 2L);
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

    public Forsaken.Gender ownAppearanceGender()
    {
        if(bodyType == Appearance.CUTEGIRL)
            return Forsaken.Gender.FEMALE;
        else
            return Forsaken.Gender.MALE;
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
            return Boolean.valueOf(true);
        else
            return Boolean.valueOf(false);
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
        if(removed == Project.Supine)
        {
            removeActivity(Project.CockSteppedOn, partner);
            removeActivity(Project.ClitSteppedOn, partner);
        }
        for(int i = 0; i < counterparts.length; i++)
        {
            if(removed == Project.PushDown && counterparts[i] == Project.PullDown && Project.PullDown.isInProgress(counterPartners[i], null).booleanValue())
                counterPartners[i].addActivity(Project.Supine, null);
            counterPartners[i].removeActivity(counterparts[i], this);
        }

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
        if(chosenOwner != null)
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
            if(chosenOwner.impregnated.booleanValue())
                value += 40;
            else
            if(chosenOwner.timesSlaughtered() > 0)
                value += 30;
            else
            if(!chosenOwner.vVirg.booleanValue())
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
            if(chosenOwner.hypnotized.booleanValue())
                value += 40;
            else
            if(chosenOwner.timesFantasized() > 0)
                value += 30;
            else
            if(!chosenOwner.cVirg.booleanValue())
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
            if(chosenOwner.drained.booleanValue())
                value += 40;
            else
            if(chosenOwner.timesDetonated() > 0)
                value += 30;
            else
            if(!chosenOwner.aVirg.booleanValue())
                value += 20;
            else
            if(chosenOwner.meek)
                value += 10;
            value += chosenOwner.negotiations;
            if(!chosenOwner.drained.booleanValue() && chosenOwner.negotiations == 0)
                value /= 2;
            else
            if(chosenOwner.negotiations > 0)
                value += 10;
            if(value > 100)
                value = 100;
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
            if(chosenOwner.parasitized.booleanValue())
                value += 40;
            else
            if(chosenOwner.timesStripped() > 0)
                value += 30;
            else
            if(!chosenOwner.modest.booleanValue())
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
            return Boolean.valueOf(false);
    }

    public Boolean isDrained()
    {
        if(forsakenOwner != null)
            return forsakenOwner.drained;
        if(chosenOwner != null)
            return chosenOwner.drained;
        else
            return Boolean.valueOf(false);
    }

    public Boolean hasBeenBroadcasted()
    {
        if(forsakenOwner != null)
            if(forsakenOwner.timesExposed >= 0x493e0)
                return Boolean.valueOf(true);
            else
                return Boolean.valueOf(false);
        if(chosenOwner != null)
        {
            if(chosenOwner.modest.booleanValue())
                return Boolean.valueOf(false);
            else
                return Boolean.valueOf(true);
        } else
        {
            return Boolean.valueOf(false);
        }
    }

    public Boolean isVVirg()
    {
        if(forsakenOwner != null)
            if(forsakenOwner.timesHadSex > 0)
                return Boolean.valueOf(false);
            else
                return Boolean.valueOf(true);
        if(chosenOwner != null)
            return chosenOwner.vVirg;
        else
            return Boolean.valueOf(false);
    }

    public Boolean isDemonLord()
    {
        if(chosenOwner == null && forsakenOwner == null)
            return Boolean.valueOf(true);
        else
            return Boolean.valueOf(false);
    }

    public String ownerName()
    {
        if(forsakenOwner != null)
            return forsakenOwner.mainName;
        if(chosenOwner != null)
        {
            if(transformed.booleanValue())
                return chosenOwner.mainName;
            else
                return chosenOwner.givenName;
        } else
        {
            return "the Demon Lord";
        }
    }

    public Boolean civilianPortrait()
    {
        if(forsakenOwner == null && transformed.booleanValue())
            return Boolean.valueOf(false);
        else
            return Boolean.valueOf(true);
    }

    public String portraitName()
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
        } else
        if(chosenOwner != null)
        {
            int obedience = getObedience();
            if(obedience < 10)
                result = -50 + (5 * obedience) / 2;
            else
            if(obedience < 20)
                result = -45 + 2 * obedience;
            else
            if(obedience < 30)
                result = -35 + (3 * obedience) / 2;
            else
            if(obedience < 40)
                result = -20 + obedience;
            else
            if(obedience < 50)
                result = obedience / 2;
            else
            if(obedience < 60)
                result = obedience / 2;
            else
            if(obedience < 70)
                result = -30 + obedience;
            else
            if(obedience < 80)
                result = -65 + (3 * obedience) / 2;
            else
            if(obedience < 90)
                result = -105 + 2 * obedience;
            else
                result = -150 + (5 * obedience) / 2;
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
                    Boolean found = Boolean.valueOf(false);
                    int opinionClamp = forsakenOwner.opinion(forsakenOwner.others[i]) + 100;
                    if(opinionClamp > 500)
                        opinionClamp = 500;
                    for(int j = 0; j < w.getHarem().length; j++)
                    {
                        if(forsakenOwner.others[i].equals(w.getHarem()[j]).booleanValue())
                        {
                            found = Boolean.valueOf(true);
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
                        if(!found.booleanValue())
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
        transformed = Boolean.valueOf(true);
        imprisoned = Boolean.valueOf(false);
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
        transformed = Boolean.valueOf(true);
        imprisoned = Boolean.valueOf(false);
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
        transformed = Boolean.valueOf(true);
        imprisoned = Boolean.valueOf(false);
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
        transformed = Boolean.valueOf(true);
        imprisoned = Boolean.valueOf(false);
        inProgress = new Activity[0];
        targets = new Body[0];
        parts = new int[14];
        orgasms = 0;
        specialLine = 0;
    }

    private static final long serialVersionUID = 4L;
    Chosen chosenOwner;
    Forsaken forsakenOwner;
    public Boolean transformed;
    Boolean imprisoned;
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
