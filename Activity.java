
import javax.swing.JTextPane;

public class Activity
{
    public static final class Location extends Enum
    {

        public static Location[] values()
        {
            Location alocation[];
            int i;
            Location alocation1[];
            System.arraycopy(alocation = ENUM$VALUES, 0, alocation1 = new Location[i = alocation.length], 0, i);
            return alocation1;
        }

        public static Location valueOf(String s)
        {
            return (Location)Enum.valueOf(Activity$Location, s);
        }

        public static final Location CHAMBER;
        public static final Location STAGE;
        public static final Location ALLEY;
        public static final Location ROOM;
        private static final Location ENUM$VALUES[];

        static 
        {
            CHAMBER = new Location("CHAMBER", 0);
            STAGE = new Location("STAGE", 1);
            ALLEY = new Location("ALLEY", 2);
            ROOM = new Location("ROOM", 3);
            ENUM$VALUES = (new Location[] {
                CHAMBER, STAGE, ALLEY, ROOM
            });
        }

        private Location(String s, int i)
        {
            super(s, i);
        }
    }


    public String activityName(Body partner)
    {
        if(this == Project.TweakClit)
            return (new StringBuilder("Stroke ")).append(partner.ownerName()).append("'s Clit").toString();
        if(this == Project.ClitTweaked)
            return (new StringBuilder("Clit Stroked by ")).append(partner.ownerName()).toString();
        if(this == Project.SpreadLegs)
            return "Spread Legs";
        if(this == Project.Praise)
            return (new StringBuilder("Praise ")).append(partner.ownerName()).toString();
        if(this == Project.Insult)
            return (new StringBuilder("Insult ")).append(partner.ownerName()).toString();
        if(this == Project.PushDown)
            return (new StringBuilder("Atop ")).append(partner.ownerName()).toString();
        if(this == Project.PullDown)
            return (new StringBuilder("Beneath ")).append(partner.ownerName()).toString();
        if(this == Project.Escape)
            return (new StringBuilder("Escape ")).append(partner.ownerName()).toString();
        if(this == Project.StopActing)
            return "Stop Activities";
        if(this == Project.TieUp)
            return (new StringBuilder("Tie Up ")).append(partner.ownerName()).toString();
        if(this == Project.BeTied)
            return (new StringBuilder("Tied Up By ")).append(partner.ownerName()).toString();
        if(this == Project.StrokeCock)
            return (new StringBuilder("Stroke ")).append(partner.ownerName()).append("'s Cock").toString();
        if(this == Project.CockStroked)
            return (new StringBuilder("Cock Stroked by ")).append(partner.ownerName()).toString();
        if(this == Project.Lubricate)
            return (new StringBuilder("Lubricate ")).append(partner.ownerName()).append("'s Anus").toString();
        if(this == Project.BeLubricated)
            return "Anus Lubricated";
        if(this == Project.VaginalPenetrate)
            return (new StringBuilder("Fuck ")).append(partner.ownerName()).append("'s Pussy").toString();
        if(this == Project.PenetratedVaginally)
            return (new StringBuilder("Pussy Fucked By ")).append(partner.ownerName()).toString();
        if(this == Project.AnalPenetrate)
            return (new StringBuilder("Fuck ")).append(partner.ownerName()).append("'s Ass").toString();
        if(this == Project.PenetratedAnally)
            return (new StringBuilder("Ass Fucked By ")).append(partner.ownerName()).toString();
        if(this == Project.StripOther)
            return (new StringBuilder("Strip ")).append(partner.ownerName()).toString();
        if(this == Project.Stripped)
            return (new StringBuilder("Stripped by ")).append(partner.ownerName()).toString();
        if(this == Project.LickCock)
            return (new StringBuilder("Lick ")).append(partner.ownerName()).append("'s Cock").toString();
        if(this == Project.CockLicked)
            return (new StringBuilder("Cock Licked By ")).append(partner.ownerName()).toString();
        if(this == Project.LickPussy)
            return (new StringBuilder("Lick ")).append(partner.ownerName()).append("'s Pussy").toString();
        if(this == Project.PussyLicked)
            return (new StringBuilder("Pussy Licked By ")).append(partner.ownerName()).toString();
        if(this == Project.Supine)
            return "Lying on Back";
        if(this == Project.PullUp)
            return (new StringBuilder("Pull ")).append(partner.ownerName()).append(" Up").toString();
        if(this == Project.StepOnCock)
            return (new StringBuilder("Step On ")).append(partner.ownerName()).append("'s Cock").toString();
        if(this == Project.CockSteppedOn)
            return (new StringBuilder("Cock Stepped On By ")).append(partner.ownerName()).toString();
        if(this == Project.StepOnClit)
            return (new StringBuilder("Step On ")).append(partner.ownerName()).append("'s Clit").toString();
        if(this == Project.ClitSteppedOn)
            return (new StringBuilder("Clit Stepped On By ")).append(partner.ownerName()).toString();
        if(this == Project.DirtyTalk)
            return (new StringBuilder("Talk Dirty To ")).append(partner.ownerName()).toString();
        else
            return "";
    }

    public long[] damageContribution(WorldState w, Body subject, long input[])
    {
        if(this == Project.ClitTweaked)
        {
            input[1] += 5L;
            input[3] += 5L;
            input[5] += 100L;
            input[6] += 2L;
        } else
        if(this == Project.TweakClit)
        {
            input[1] += 10L;
            input[3] += 10L;
            input[4] += 2L;
            input[5]++;
            input[6] += 4L;
        } else
        if(this == Project.Praise)
        {
            input[1] += 2L;
            input[3] += 3L;
            input[6]++;
        } else
        if(this == Project.Insult)
        {
            input[0] += 2L;
            input[4] += 4L;
            input[6]++;
        } else
        if(this == Project.PushDown)
        {
            input[5] += 5L;
            input[6] += 10L;
        } else
        if(this == Project.PullDown)
        {
            input[0] += 2L;
            input[2] += 2L;
            input[3]++;
            input[4] += 3L;
            input[5] += 10L;
            input[6] += 3L;
        } else
        if(this == Project.Escape)
        {
            input[0] += 2L;
            input[3]++;
            input[6] += 10L;
        } else
        if(this == Project.BeTied)
        {
            input[0] += 20L;
            input[1] += 2L;
            input[2] += 10L;
            input[3] += 5L;
            input[4] += 50L;
            input[6] += 3L;
        } else
        if(this == Project.CockStroked)
        {
            input[1] += 5L;
            input[3] += 5L;
            input[5] += 140L;
            input[6] += 2L;
        } else
        if(this == Project.StrokeCock)
        {
            input[1] += 10L;
            input[3] += 10L;
            input[4] += 2L;
            input[5]++;
            input[6] += 4L;
        } else
        if(this == Project.Lubricate)
        {
            input[1] += 20L;
            input[3] += 20L;
            input[6] += 4L;
        } else
        if(this == Project.BeLubricated)
        {
            input[1] += 5L;
            input[3] += 10L;
            input[4] += 5L;
        } else
        if(this == Project.VaginalPenetrate)
        {
            input[1] += 2L;
            input[3] += 2L;
            input[5] += 300L;
            input[6] += 6L;
        } else
        if(this == Project.PenetratedVaginally)
        {
            input[1] += 4L;
            input[2] += 3L;
            input[3] += 3L;
            input[4] += 5L;
            if(subject.getPLEALevel() == 0)
            {
                input[2] += 20L;
                input[4] += 10L;
            }
            if(subject.isVVirg().booleanValue())
            {
                input[2] += 200L;
                input[4] += 30L;
            }
            input[5] += 200L;
            input[6] += 5L;
        } else
        if(this == Project.AnalPenetrate)
        {
            input[1] += 3L;
            input[3] += 2L;
            input[5] += 350L;
            input[6] += 6L;
        } else
        if(this == Project.PenetratedAnally)
        {
            input[1] += 5L;
            input[2] += 8L;
            input[3] += 10L;
            input[4] += 10L;
            if(subject.parts[PUSSY] == 0 && subject.isVVirg().booleanValue())
                input[4] += 30L;
            if(!Project.BeLubricated.isInProgress(subject, null).booleanValue())
            {
                input[4] += 20L;
                input[2] += 200L;
            }
            input[5] += 100L;
            input[6] += 6L;
        } else
        if(this == Project.Stripped)
        {
            input[0] += 3L;
            if(subject.forsakenOwner != null && subject.forsakenOwner.timesExposed < 0x493e0)
                input[0] += subject.getDignity() / 6;
            input[1] += 2L;
            input[3] += 20L;
            if(w.sceneLocation == Location.STAGE)
                input[4] += 10L;
            else
                input[4] += 2L;
            input[5] += 5L;
            input[6]++;
            input[7] += 100L;
        } else
        if(this == Project.LickCock)
        {
            input[1] += 20L;
            input[3] += 20L;
            input[4] += 3L;
            input[5] += 2L;
            input[6] += 5L;
        } else
        if(this == Project.CockLicked)
        {
            input[1] += 6L;
            input[3] += 3L;
            input[5] += 200L;
            input[6] += 2L;
        } else
        if(this == Project.LickPussy)
        {
            input[1] += 20L;
            input[3] += 20L;
            input[4] += 3L;
            input[5] += 2L;
            input[6] += 5L;
        } else
        if(this == Project.PussyLicked)
        {
            input[1] += 8L;
            input[3] += 4L;
            input[5] += 140L;
            input[6] += 2L;
        } else
        if(this == Project.Supine)
            input[6]--;
        else
        if(this == Project.StepOnCock)
        {
            input[1] += 5L;
            input[3] += 2L;
            input[5] += 2L;
            input[6] += 4L;
        } else
        if(this == Project.CockSteppedOn)
        {
            input[0] += 5L;
            input[1] += 8L;
            input[2] += 10L;
            input[3] += 15L;
            input[4] += 10L;
            input[5] += 100L;
            input[6] += 4L;
        } else
        if(this == Project.StepOnClit)
        {
            input[1] += 5L;
            input[3] += 2L;
            input[5] += 2L;
            input[6] += 4L;
        } else
        if(this == Project.ClitSteppedOn)
        {
            input[0] += 4L;
            input[1] += 8L;
            input[2] += 6L;
            input[3] += 15L;
            input[4] += 10L;
            input[5] += 50L;
            input[6] += 3L;
        } else
        if(this == Project.DirtyTalk)
        {
            input[1] += 8L;
            input[3] += 2L;
            input[4] += 2L;
            input[5] += 5L;
            input[6]++;
        }
        return input;
    }

    public void activityTalk(JTextPane t, WorldState w, Body sender, Body receiver)
    {
        String shownNames[] = {
            sender.portraitName(), 0, 0, 0, 0
        };
        if(Project.PenetratedVaginally.isInProgress(sender, receiver).booleanValue() && (sender.forsakenOwner != null && sender.forsakenOwner.timesHadSex == 0 || sender.chosenOwner != null && sender.chosenOwner.vVirg.booleanValue()))
            sender.specialLine = 1;
        if(Project.PenetratedAnally.isInProgress(sender, receiver).booleanValue())
        {
            if(sender.forsakenOwner != null && sender.forsakenOwner.gender == Forsaken.Gender.MALE && sender.forsakenOwner.timesHadSex == 0 || sender.chosenOwner != null && sender.chosenOwner.gender.equals("male") && sender.chosenOwner.vVirg.booleanValue())
                sender.specialLine = 1;
            else
            if(!Project.BeLubricated.isInProgress(sender, null).booleanValue() && sender.forsakenOwner != null && sender.forsakenOwner.timesTortured == 0)
                sender.specialLine = 3;
            if(sender.forsakenOwner != null)
                if(sender.forsakenOwner.gender == Forsaken.Gender.MALE)
                {
                    sender.forsakenOwner.timesHadSex++;
                    if(!Project.BeLubricated.isInProgress(sender, null).booleanValue())
                        sender.forsakenOwner.timesTortured++;
                } else
                if(Project.BeLubricated.isInProgress(sender, null).booleanValue())
                    sender.forsakenOwner.enjoyedAnal++;
                else
                    sender.forsakenOwner.timesTortured++;
        }
        sender.say(t, "\"");
        if(sender.specialLine == 1)
        {
            if(sender.getGender() == Forsaken.Gender.MALE)
            {
                if(Project.BeLubricated.isInProgress(sender, null).booleanValue())
                {
                    if(sender.getObedience() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.JOY);
                        if(sender.getMorality() > 66)
                            sender.say(t, "With this...  Even though I'm a boy, I feel like I'm... your wife...  Is that okay?");
                        else
                        if(sender.getMorality() > 33)
                            sender.say(t, "I'm so happy that I was able to save myself for you!");
                        else
                            sender.say(t, "I always felt like sex was just about feeling good, but... I managed to give you my first time.  That makes me really happy.");
                    } else
                    if(sender.getObedience() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.SHAME);
                        if(sender.getInnocence() > 66)
                            sender.say(t, "It kinda hurts...");
                        else
                        if(sender.getInnocence() > 33)
                            sender.say(t, "I'm surprised you took so long to do this...");
                        else
                            sender.say(t, "I suppose... I had already come to accept that you'd eventually use me back there as well...");
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.ANGER);
                        if(sender.getInnocence() > 66)
                            sender.say(t, "I'll never forgive you for this!  Never!  Never!");
                        else
                        if(sender.getInnocence() > 33)
                            sender.say(t, "Even if you defile my body, I'll never give you my heart!");
                        else
                            sender.say(t, "All that effort to keep the Thralls from raping me, only to do it yourself?  How... ngh... pointless...");
                    }
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.FEAR);
                    if(sender.getConfidence() > 66)
                    {
                        if(sender.getObedience() > 66)
                            sender.say(t, (new StringBuilder("I'm... guh... f-fine, ")).append(sender.demonLord()).append("!").toString());
                        else
                        if(sender.getObedience() > 33)
                            sender.say(t, "Why are you doing this!?  I'm serving you now!  Gaaah, pleeease!");
                        else
                            sender.say(t, "I'll stop fighting, I'll stop talking back, I'll do anything!");
                    } else
                    if(sender.getConfidence() > 33)
                    {
                        if(sender.getDignity() > 66)
                            sender.say(t, "Please, please!  Stooop!");
                        else
                        if(sender.getDignity() > 33)
                            sender.say(t, "Mgh...  guh... I-I can't...");
                        else
                            sender.say(t, "AAAGH, NOOO!");
                    } else
                    if(sender.getDeviancy() > 66)
                        sender.say(t, "Nnnaaah~!");
                    else
                    if(sender.getDeviancy() > 33)
                        sender.say(t, "Aah, nnooo, oooh!");
                    else
                        sender.say(t, "Agh!  Mph, guh, n-no...!");
                }
            } else
            if(sender.getObedience() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.JOY);
                if(sender.getMorality() > 66)
                    sender.say(t, "With this...  It's like I'm your wife now, isn't it...?  Ah, wonderful...");
                else
                if(sender.getMorality() > 33)
                    sender.say(t, "I'm so happy that I was able to save myself for you!");
                else
                    sender.say(t, "I never cared much about who my first would be... but now, I'm really glad that it's you...");
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.SHAME);
                if(sender.getInnocence() > 66)
                    sender.say(t, "It kinda hurts...");
                else
                if(sender.getInnocence() > 33)
                    sender.say(t, "I'm surprised you took so long to do this...");
                else
                    sender.say(t, "I suppose... I had already come to accept that you'd be my first...");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.ANGER);
                if(sender.getInnocence() > 66)
                    sender.say(t, "I'll never forgive you for this!  Never!  Never!");
                else
                if(sender.getInnocence() > 33)
                    sender.say(t, "Even if you defile my body, I'll never give you my heart!");
                else
                    sender.say(t, "All that effort to preserve my virginity, only to take it now?  How... ngh... pointless...");
            }
            if(sender.forsakenOwner != null)
                sender.forsakenOwner.timesHadSex++;
            else
            if(sender.chosenOwner != null)
                sender.chosenOwner.vVirg = Boolean.valueOf(false);
        } else
        if(sender.specialLine == 2)
        {
            Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.LEWD);
            if(sender.getInnocence() > 66)
            {
                if(sender.getObedience() > 66)
                    sender.say(t, (new StringBuilder("That was... amazing!  It's like all my love for ")).append(sender.theDemonLord()).append("... came out at once...!").toString());
                else
                if(sender.getObedience() > 33)
                    sender.say(t, "Aaah, what's happening...!?  I feel good!  I feel too good!  I'm going crazy...!");
                else
                    sender.say(t, "Whuh?  What was... that feeling...?  What did you do to me...?");
            } else
            if(sender.getInnocence() > 33)
            {
                if(sender.getObedience() > 66)
                    sender.say(t, (new StringBuilder("When ")).append(sender.theDemonLord()).append(" makes me cum... it feels so much better than when I do it myself...!").toString());
                else
                if(sender.getObedience() > 33)
                    sender.say(t, "Wow!  That was... intense!");
                else
                    sender.say(t, "Ugh, you made me cum... even though I didn't want to...  Not with you...");
            } else
            if(sender.getObedience() > 66)
                sender.say(t, (new StringBuilder("Such an... overwhelmingly intense orgasm!  Ah, ")).append(sender.demonLord()).append(", my body has come to desire your touch above all else!").toString());
            else
            if(sender.getObedience() > 33)
                sender.say(t, "Your... nn... technique... certainly does not disappoint...");
            else
                sender.say(t, "Nnngh...  Pathetic... to feel such pleasure from the Demon Lord...");
        } else
        if(sender.specialLine == 3)
        {
            Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.STRUGGLE);
            if(sender.getObedience() > 66)
            {
                if(sender.getDeviancy() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.LEWD);
                    sender.say(t, "AAAGH!  YES!  YES!  PUNISH ME HARDER!");
                } else
                if(sender.getDeviancy() > 33)
                    sender.say(t, "Too much!  It's too much!  Aaagh!");
                else
                    sender.say(t, "NNNOOOGH!  S-Stop, please!  I'm serving you, so why...!?");
            } else
            if(sender.getObedience() > 33)
            {
                if(sender.getConfidence() > 66)
                    sender.say(t, "I'M SORRY!  I'M SORRY FOR DEFYING YOU!  AAARGH!");
                else
                if(sender.getConfidence() > 33)
                    sender.say(t, "I'm breaking!  You're breaking me!");
                else
                    sender.say(t, "P-Please...!  No more!  NoooOOOGH!");
            } else
            if(sender.getConfidence() > 66)
                sender.say(t, "NO! NOOO!  AAAGH!");
            else
            if(sender.getConfidence() > 33)
                sender.say(t, "You win!  You win!  You don't have to- GRAAAH!");
            else
                sender.say(t, "I-I give up!  I'm sorry, I'm sorryyy!");
        } else
        if(sender.specialLine == 4)
        {
            if(sender.getObedience() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.LEWD);
                if(sender.getDignity() > 66)
                    sender.say(t, (new StringBuilder("Such an... incredible feeling of shame...!  Ngh...!  Thank you, ")).append(sender.demonLord()).append(", thank you for letting me feel this...!").toString());
                else
                if(sender.getDignity() > 33)
                {
                    sender.say(t, (new StringBuilder("Aaah, yes!  Now everyone will know that I belong to ")).append(sender.theDemonLord()).append("!").toString());
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.FOCUS);
                    sender.say(t, (new StringBuilder("Is there anything else you want me to show them, ")).append(sender.demonLord()).append("?").toString());
                }
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.SHAME);
                if(sender.getDignity() > 66)
                    sender.say(t, "You've taken... everything... from me...  Ngh...");
                else
                if(sender.getDignity() > 33)
                    sender.say(t, "Maybe... if I look like I'm enjoying it... they won't think I'm weak...");
                else
                    sender.say(t, "I never cared how much they saw, so... it's fine.");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.ANGER);
                if(sender.getDignity() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.FEAR);
                    sender.say(t, "No!  No!  Let me cover myself!");
                } else
                if(sender.getDignity() > 33)
                    sender.say(t, "Even if you humiliate me...  I won't give up!");
                else
                    sender.say(t, "They can laugh all they like!  I'll still beat you in the end!");
            }
        } else
        if(sender.getINJULevel() > 3)
        {
            Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SWOON, Project.Emotion.SWOON);
            sender.say(t, "...");
        } else
        if(this == Project.TweakClit)
        {
            if(receiver == w.lordBody)
                if(sender.getObedience() > 66)
                {
                    if(sender.getInnocence() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                        sender.say(t, (new StringBuilder("I'm so happy I get to make ")).append(sender.theDemonLord()).append(" feel good!").toString());
                    } else
                    if(sender.getInnocence() > 33)
                    {
                        sender.say(t, (new StringBuilder("Does this feel good, ")).append(sender.demonLord()).append("?  I just want to make you happy...").toString());
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                    } else
                    {
                        sender.say(t, (new StringBuilder("I need to impress ")).append(sender.theDemonLord()).append(" with my technique...!").toString());
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.NEUTRAL);
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.currentHATE > 1000L)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                        if(sender.imprisoned.booleanValue())
                            sender.say(t, "Ugh, making me touch you like this...!");
                        else
                            sender.say(t, "I'll touch you here, but that's it!");
                    } else
                    if(sender.currentHATE > 100L)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
                        sender.say(t, "Why do you need me to do this...?");
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FOCUS);
                        sender.say(t, "Is this alright?");
                    }
                } else
                if(sender.getDeviancy() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                    sender.say(t, "Ngh...  You knew... I wouldn't be able to resist this beautiful body you made...");
                } else
                if(sender.getDeviancy() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.ANGER);
                    sender.say(t, "Fine.  I'll make you feel good.  But you'd better return the favor...");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                    sender.say(t, "Disgusting... I don't want to touch the Demon Lord...");
                }
        } else
        if(this == Project.Praise)
        {
            if(w.sceneDuration % 3 == 0)
            {
                if(sender.getObedience() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                    if(sender.getInnocence() > 66)
                        sender.say(t, (new StringBuilder("I'm so happy that I get to spend time with ")).append(sender.theDemonLord()).append("!").toString());
                    else
                    if(sender.getInnocence() > 33)
                        sender.say(t, (new StringBuilder("Use me up until nothing is left, ")).append(sender.demonLord()).append(".").toString());
                    else
                        sender.say(t, (new StringBuilder("I am unworthy to serve ")).append(sender.theDemonLord()).append("... but I shall try my best!").toString());
                } else
                if(sender.getObedience() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.JOY);
                    if(sender.getInnocence() > 66)
                        sender.say(t, "This is weird, but... kinda fun, too.");
                    else
                    if(sender.getInnocence() > 33)
                        sender.say(t, (new StringBuilder("You're amazing, ")).append(sender.demonLord()).append(".").toString());
                    else
                        sender.say(t, "Let me prove my value to you.");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
                    if(sender.getInnocence() > 66)
                        sender.say(t, (new StringBuilder(String.valueOf(sender.TheDemonLord()))).append(" isn't so bad...  Wait, what am I saying!?").toString());
                    else
                    if(sender.getInnocence() > 33)
                    {
                        if(sender.imprisoned.booleanValue())
                            sender.say(t, "Ugh, I'm completely under your control...");
                        else
                            sender.say(t, "Ugh, you've got me completely wrapped around your finger...");
                    } else
                    {
                        sender.say(t, (new StringBuilder("I'm... aware that I stand no chance of defeating you, ")).append(sender.demonLord()).append(".").toString());
                    }
                }
            } else
            if(w.sceneDuration % 3 == 1)
            {
                if(sender.getObedience() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                    if(sender.getDeviancy() > 66)
                        sender.say(t, (new StringBuilder("I-Increidble...!  Being with ")).append(sender.theDemonLord()).append(" feels even better...!").toString());
                    else
                    if(sender.getDeviancy() > 33)
                        sender.say(t, (new StringBuilder("I love you, ")).append(sender.demonLord()).append("!").toString());
                    else
                        sender.say(t, "You're remaking me... into your own personal toy...!");
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.getDeviancy() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, (new StringBuilder("I want you to make me feel even better, ")).append(sender.demonLord()).append("!").toString());
                    } else
                    if(sender.getDeviancy() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.JOY);
                        sender.say(t, (new StringBuilder("My body is all yours, ")).append(sender.demonLord()).append(".").toString());
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
                        sender.say(t, (new StringBuilder("Your touch is... unforgettable, ")).append(sender.demonLord()).append("...").toString());
                    }
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
                    if(sender.getDeviancy() > 66)
                        sender.say(t, (new StringBuilder("Ugh... I... I can't resist you, ")).append(sender.demonLord()).append("...").toString());
                    else
                    if(sender.getDeviancy() > 33)
                        sender.say(t, "You're almost making me forget that you're the Demon Lord.");
                    else
                        sender.say(t, "If you can even make someone like me feel like this...");
                }
            } else
            if(w.sceneDuration % 3 == 2)
                if(sender.getObedience() > 66)
                {
                    if(sender.getConfidence() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                        sender.say(t, "I want to make you forget about all your other servants...");
                    } else
                    if(sender.getConfidence() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                        sender.say(t, (new StringBuilder("You're just perfect, ")).append(sender.demonLord()).append("!  I don't know why I ever fought you...").toString());
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
                        sender.say(t, "I know it's wrong to want you to care about me... but...");
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
                    if(sender.getConfidence() > 66)
                        sender.say(t, "I've never feared anyone but you...");
                    else
                    if(sender.getConfidence() > 33)
                    {
                        if(sender.imprisoned.booleanValue())
                            sender.say(t, "I'll never oppose you again.  I understand that now.");
                        else
                            sender.say(t, "I can't oppose you without being punished.  I understand that now.");
                    } else
                    if(sender.imprisoned.booleanValue())
                        sender.say(t, "I can't even think about fighting you anymore...");
                    else
                        sender.say(t, "I-It's hard to even think about fighting you right now...");
                } else
                if(sender.getConfidence() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.NEUTRAL);
                    sender.say(t, "You're... a worthy opponent.  I wouldn't even feel bad about losing to you.  Not that I'm giving up!");
                } else
                if(sender.getConfidence() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
                    if(sender.imprisoned.booleanValue())
                        sender.say(t, "I... never stood a chance against you.  Ugh, I hate saying that...");
                    else
                        sender.say(t, "I... probably don't stand a chance against you.  Ugh, I hate saying that...");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
                    sender.say(t, "I, um...  I don't actually think I'm going to be able to resist you for that long.  I just don't want to give up right away...");
                }
        } else
        if(this == Project.Insult)
        {
            if(w.sceneDuration % 3 == 0)
            {
                if(sender.getObedience() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.SHAME);
                    if(sender.getINJULevel() > 3)
                        sender.say(t, (new StringBuilder("P-Please... ")).append(sender.demonLord()).append("...  I'm passing out...").toString());
                    else
                    if(sender.getINJULevel() == 2)
                        sender.say(t, (new StringBuilder("I can't keep up with you, ")).append(sender.demonLord()).append("...").toString());
                    else
                        sender.say(t, (new StringBuilder("Are you trying to punish me, ")).append(sender.demonLord()).append("?").toString());
                } else
                if(sender.getObedience() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                    if(sender.getINJULevel() > 3)
                        sender.say(t, "Let... Let me go...!");
                    else
                    if(sender.getINJULevel() == 2)
                        sender.say(t, "Can't you tell I'm getting tired?  Let's just stop here.");
                    else
                        sender.say(t, "I can't take this anymore...!");
                } else
                if(sender.getINJULevel() > 3)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.ANGER);
                    if(sender.imprisoned.booleanValue())
                        sender.say(t, "Heh...  I'll keep resisting... until I pass out...");
                    else
                        sender.say(t, "Heh...  I've still got plenty of fight in me...");
                } else
                if(sender.getINJULevel() == 2)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                    sender.say(t, "I'm not going to do what you want, so just go away!");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
                    if(sender.forsakenOwner != null)
                    {
                        if(sender.getMorality() > 66)
                            sender.say(t, "I'll never stop resisting you!");
                        else
                        if(sender.getMorality() > 33)
                            sender.say(t, "This is pointless, just stop!");
                        else
                            sender.say(t, "I'll get my revenge on you!");
                    } else
                    if(sender.getMorality() > 66)
                        sender.say(t, "You'd better have a good reason for this!");
                    else
                    if(sender.getMorality() > 33)
                        sender.say(t, "Stop this!");
                    else
                        sender.say(t, "You're already getting on my nerves.");
                }
            } else
            if(w.sceneDuration % 3 == 1)
            {
                if(sender.getObedience() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
                    if(sender.getHATELevel() > 1)
                        sender.say(t, (new StringBuilder("I don't want to be angry with ")).append(sender.theDemonLord()).append(", but...!").toString());
                    else
                    if(sender.getHATELevel() == 1)
                        sender.say(t, "Just... Just give me a moment to calm down...!");
                    else
                        sender.say(t, (new StringBuilder("I'm sorry, ")).append(sender.demonLord()).append("...").toString());
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.getHATELevel() > 1)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                        sender.say(t, "I should have known better than to trust you...!");
                    } else
                    if(sender.getHATELevel() == 1)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
                        sender.say(t, (new StringBuilder("I'm not happy with you right now, ")).append(sender.demonLord()).append(".").toString());
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.STRUGGLE);
                        sender.say(t, "Can't we do this another time?");
                    }
                } else
                if(sender.getHATELevel() > 1)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                    sender.say(t, "I swear I'll kill you, Demon Lord!");
                } else
                if(sender.getHATELevel() == 1)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
                    sender.say(t, "Are you just trying to provoke me!?");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.ANGER);
                    if(sender.forsakenOwner != null)
                    {
                        if(sender.getConfidence() > 66)
                            sender.say(t, "Well!?  Aren't you going to torture me!?");
                        else
                        if(sender.getConfidence() > 33)
                            sender.say(t, "I still don't want to serve you.");
                        else
                            sender.say(t, "J-Just leave me alone...");
                    } else
                    if(sender.getConfidence() > 66)
                        sender.say(t, "You're wasting my time!");
                    else
                    if(sender.getConfidence() > 33)
                        sender.say(t, "If you have something to say, make it quick!");
                    else
                        sender.say(t, "Why am I even listening to you...?");
                }
            } else
            if(sender.getObedience() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
                if(sender.getDeviancy() > 66)
                    sender.say(t, (new StringBuilder("I'll just... dream about a nicer ")).append(sender.demonLord()).append("...").toString());
                else
                if(sender.getDeviancy() > 33)
                    sender.say(t, (new StringBuilder("How can I hate this, when it's with ")).append(sender.theDemonLord()).append("...?").toString());
                else
                    sender.say(t, (new StringBuilder("Even if it's with ")).append(sender.theDemonLord()).append(", I... I don't like this...").toString());
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.ANGER);
                if(sender.getDeviancy() > 66)
                    sender.say(t, "This is too much, even for me!");
                else
                if(sender.getDeviancy() > 33)
                    sender.say(t, "This is too much for me!");
                else
                    sender.say(t, "You know I don't like this sort of thing...");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                if(sender.getDeviancy() > 66)
                    sender.say(t, "Just go away and let me play with myself!");
                else
                if(sender.getDeviancy() > 33)
                    sender.say(t, "I don't want to do this!  At least not with you!");
                else
                if(sender.forsakenOwner != null)
                {
                    if(sender.getInnocence() > 66)
                        sender.say(t, "Just do your pervy stuff and get out of here already.");
                    else
                    if(sender.getInnocence() > 33)
                        sender.say(t, "You're... disgusting.");
                    else
                        sender.say(t, "I expect that you're here to abuse my body.  How predictable...");
                } else
                if(sender.getInnocence() > 66)
                    sender.say(t, "Are you just here to perv on me?  Gross.");
                else
                if(sender.getInnocence() > 33)
                    sender.say(t, "If you're trying to seduce me, then you're wasting your time.");
                else
                    sender.say(t, "Disgusting creature.  State your purpose or get out of my sight.");
            }
        } else
        if(this == Project.SpreadLegs)
        {
            sender.say(t, (new StringBuilder("Please, touch me more, ")).append(sender.demonLord()).append("!").toString());
            Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
        } else
        if(this == Project.PushDown)
        {
            if(sender.getDeviancy() > 66)
            {
                if(sender.getConfidence() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                    sender.say(t, (new StringBuilder("Aaah... I wanna rape ")).append(sender.theDemonLord()).append(" a lot...!").toString());
                } else
                if(sender.getConfidence() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                    sender.say(t, "Hurry, hurry...!");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                    sender.say(t, "If we don't do this now... I-I'm gonna go crazy...!");
                }
            } else
            if(sender.getDeviancy() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                if(sender.getInnocence() > 66)
                    sender.say(t, "Mm...!  Mm...");
                else
                if(sender.getInnocence() > 33)
                    sender.say(t, "Let's just do it.");
                else
                    sender.say(t, "Such an... impressive specimen...");
            } else
            if(sender.getObedience() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
                sender.say(t, (new StringBuilder("Are you sure you don't want to lead, ")).append(sender.demonLord()).append("?").toString());
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
                sender.say(t, "I have no idea what I'm doing...");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
                sender.say(t, "Let's get this over with.");
            }
        } else
        if(this == Project.PullDown)
        {
            if(sender.getDeviancy() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                if(sender.getConfidence() > 66)
                    sender.say(t, "Hurry up and take me!");
                else
                if(sender.getConfidence() > 33)
                    sender.say(t, "I'm ready for you...!");
                else
                    sender.say(t, "P-Please... do whatever you want with me...!");
            } else
            if(sender.getDeviancy() > 33)
            {
                if(sender.coerced().booleanValue())
                {
                    if(sender.getDignity() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.LEWD);
                        sender.say(t, "I-I guess I'm ready when you are...");
                    } else
                    if(sender.getDignity() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.LEWD);
                        sender.say(t, "This is going to feel good...");
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                        sender.say(t, "I can't take it any more...!");
                    }
                } else
                if(sender.getConfidence() > 66)
                    sender.say(t, "Get... back...!");
                else
                if(sender.getConfidence() > 33)
                    sender.say(t, "Wha- What!?");
                else
                    sender.say(t, "N-No...!");
            } else
            if(sender.getObedience() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
                sender.say(t, "I hope you enjoy my body...");
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
                sender.say(t, "I never thought I'd be doing this...");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
                if(sender.imprisoned.booleanValue())
                    sender.say(t, "Go on, finish satisfying yourself so I can get out of here!");
                else
                    sender.say(t, "What do you think you're trying to do with me!?");
            }
        } else
        if(this == Project.Escape)
        {
            if(sender.getObedience() > 66)
            {
                if(sender.getMorality() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
                    sender.say(t, (new StringBuilder("I-I'm so sorry, ")).append(sender.demonLord()).append("...  I'm worthless...").toString());
                } else
                if(sender.getMorality() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.SHAME);
                    sender.say(t, "Are you going to punish me...?");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.SHAME);
                    sender.say(t, (new StringBuilder("I can't!  Even if you are ")).append(sender.theDemonLord()).append("!").toString());
                }
            } else
            if(sender.getObedience() > 33)
            {
                if(sender.getInnocence() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.SHAME);
                    sender.say(t, "Oh no!  I- I didn't mean to do that!");
                } else
                if(sender.getInnocence() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
                    sender.say(t, "I'm sorry, but I really need a break...");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
                    sender.say(t, "I cannot continue.");
                }
            } else
            if(sender.getConfidence() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
                sender.say(t, "Whew...  Heh, I couldn't hold myself back anymore.");
            } else
            if(sender.getConfidence() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
                sender.say(t, "I'm not afraid of you!");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
                sender.say(t, "I c-can't take this anymore!");
            }
        } else
        if(this == Project.StopActing)
        {
            if(sender.getObedience() > 66)
            {
                if(sender.getMorality() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
                    sender.say(t, "Maybe... I need more training...");
                } else
                if(sender.getMorality() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.SHAME);
                    sender.say(t, "Sorry, I'm... at my limit...");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.JOY);
                    sender.say(t, "How about I lay here while you rub up against me?  That will feel good too, right?");
                }
            } else
            if(sender.getObedience() > 33)
            {
                if(sender.getDeviancy() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                    sender.say(t, "Well, I enjoyed that.  Let's do it again sometime.");
                } else
                if(sender.getDeviancy() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
                    sender.say(t, "That was good enough, right?");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
                    sender.say(t, "I should go... do some combat training.  I can't stay here doing this all day.");
                }
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
                if(sender.getInnocence() > 66)
                    sender.say(t, "I'm not gonna make you feel good anymore!");
                else
                if(sender.getInnocence() > 33)
                    sender.say(t, "This is over.");
                else
                if(sender.imprisoned.booleanValue())
                    sender.say(t, "Continuing to service you is worse than any punishment you can inflict on me.");
                else
                    sender.say(t, "I have no interest whatsoever in satisfying you.");
            }
        } else
        if(this == Project.StrokeCock)
        {
            if(receiver == w.lordBody)
                if(sender.getObedience() > 66)
                {
                    if(sender.getInnocence() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                        sender.say(t, (new StringBuilder("I'm so happy I get to make ")).append(sender.theDemonLord()).append(" feel good!").toString());
                    } else
                    if(sender.getInnocence() > 33)
                    {
                        sender.say(t, (new StringBuilder("Does this feel good, ")).append(sender.demonLord()).append("?  I just want to make you happy...").toString());
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                    } else
                    {
                        sender.say(t, (new StringBuilder("I need to impress ")).append(sender.theDemonLord()).append(" with my technique...!").toString());
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.NEUTRAL);
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.currentHATE > 1000L)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                        if(sender.imprisoned.booleanValue())
                            sender.say(t, "Ugh, making me touch you like this...!");
                        else
                            sender.say(t, "I should just rip it off...!");
                    } else
                    if(sender.currentHATE > 100L)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
                        sender.say(t, "Why do you need me to do this...?");
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FOCUS);
                        sender.say(t, "Is this alright?");
                    }
                } else
                if(sender.getDeviancy() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                    sender.say(t, "Ngh...  You knew... I wouldn't be able to resist this beautiful body you made...");
                } else
                if(sender.getDeviancy() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.ANGER);
                    sender.say(t, "Fine.  I'll make you feel good.  But you'd better return the favor...");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                    if(sender.imprisoned.booleanValue())
                        sender.say(t, "Disgusting... I don't want to touch the Demon Lord...");
                    else
                        sender.say(t, "It's... twitching.  What a disgusting thing.");
                }
        } else
        if(this == Project.VaginalPenetrate)
        {
            if(Project.PushDown.isInProgress(sender, receiver).booleanValue())
            {
                if(sender.getDeviancy() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                    if(sender.getConfidence() > 66)
                        sender.say(t, "Ngh...!  Fuck...!  Yes...!");
                    else
                    if(sender.getConfidence() > 33)
                        sender.say(t, "Your... pussy... feels... too... good...!");
                    else
                        sender.say(t, "I-I'm gonna... fill you up...!");
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getInnocence() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Aaah...!  Wow...!");
                    } else
                    if(sender.getInnocence() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                        sender.say(t, "That's... tight...!");
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                        sender.say(t, "Ah, yes...  Here we go...");
                    }
                } else
                if(sender.getObedience() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                    sender.say(t, (new StringBuilder("I love you, ")).append(sender.demonLord()).append("!  I love you so much!").toString());
                } else
                if(sender.getObedience() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FEAR);
                    sender.say(t, "This is... really intense...");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
                    sender.say(t, "How dare you... tempt me...!?  Take this!  And this!");
                }
            } else
            if(sender.getDeviancy() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                if(sender.getInnocence() > 66)
                    sender.say(t, "Aaah...  Wooow...");
                else
                if(sender.getInnocence() > 33)
                    sender.say(t, "Yes!  Yes!  More!");
                else
                    sender.say(t, "I-I'm- Ah!  Going to climax alreadyyy!");
            } else
            if(sender.getDeviancy() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                if(sender.getDignity() > 66)
                    sender.say(t, "Ngh!  Ah!  Aaah!");
                else
                if(sender.getDignity() > 33)
                    sender.say(t, "This is... amazing...!");
                else
                    sender.say(t, "Ooogh!  It's squeezing meee!");
            } else
            if(sender.getObedience() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.LEWD);
                sender.say(t, "I'll hold out... as long as I can...!");
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.LEWD);
                sender.say(t, "Why... are you making me feel so good...!?");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
                sender.say(t, "J-Just... have to calm down...!");
            }
        } else
        if(this == Project.PenetratedVaginally)
        {
            if(Project.PushDown.isInProgress(sender, receiver).booleanValue())
            {
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getObedience() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Yes!  Yes!  Cum inside me!.");
                    } else
                    if(sender.getObedience() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, (new StringBuilder("Aaah...  Your cock is amazing, ")).append(sender.demonLord()).append("!").toString());
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                        sender.say(t, "You... t-tricked me into doing this...!");
                    }
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getConfidence() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                        sender.say(t, "Ngh...!  I know you like it... rough...!");
                    } else
                    if(sender.getConfidence() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                        sender.say(t, "It's...  Ah!  Inside!");
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Th-This... actually feels really good...!");
                    }
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.LEWD);
                    if(sender.getDignity() > 66)
                        sender.say(t, "I can't believe... I'm doing this...!");
                    else
                    if(sender.getDignity() > 33)
                        sender.say(t, "Let me... catch my breath...");
                    else
                        sender.say(t, "Ow...");
                }
            } else
            if(sender.getObedience() > 66)
            {
                if(sender.getDeviancy() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                    sender.say(t, "Yes!  Yes!  Take me!  Take me!");
                } else
                if(sender.getDeviancy() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                    sender.say(t, (new StringBuilder("I'm sorry, ")).append(sender.demonLord()).append("!  It feels too good with you inside me!").toString());
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                    sender.say(t, "Does it feel good when I... ngh... squeeze?  Like this?");
                }
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                if(sender.getConfidence() > 66)
                    sender.say(t, "Aaagh...!  This shouldn't... feel so good...!");
                else
                if(sender.getConfidence() > 33)
                    sender.say(t, "Ah!  Ah, wow...");
                else
                    sender.say(t, "Mm!  Nn...!  Ah, I-I'm...!");
            } else
            if(sender.getInnocence() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                sender.say(t, "Aaagh, gaaah!  Stop it, nnnaaah!");
            } else
            if(sender.getInnocence() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.LEWD);
                sender.say(t, "Mph!  Gh!  Agh!");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                sender.say(t, "Just... climax already, you brute...!");
            }
        } else
        if(this == Project.AnalPenetrate)
        {
            if(Project.PushDown.isInProgress(sender, receiver).booleanValue())
            {
                if(sender.getDeviancy() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                    if(sender.getConfidence() > 66)
                        sender.say(t, "Ngh...!  Fuck...!  Yes...!");
                    else
                    if(sender.getConfidence() > 33)
                        sender.say(t, "Your... ass... feels... too... good...!");
                    else
                        sender.say(t, "I-I'm gonna... fill you up...!");
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getInnocence() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Aaah...!  Wow...!");
                    } else
                    if(sender.getInnocence() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                        sender.say(t, "That's... tight...!");
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                        sender.say(t, "Ah, yes...  Here we go...");
                    }
                } else
                if(sender.getObedience() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                    sender.say(t, (new StringBuilder("I love you, ")).append(sender.demonLord()).append("!  I love you so much!").toString());
                } else
                if(sender.getObedience() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FEAR);
                    sender.say(t, "This is... really intense...");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
                    sender.say(t, "How dare you... tempt me...!?  Take this!  And this!");
                }
            } else
            if(sender.getDeviancy() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                if(sender.getInnocence() > 66)
                    sender.say(t, "Aaah...  Wooow...");
                else
                if(sender.getInnocence() > 33)
                    sender.say(t, "Yes!  Yes!  More!");
                else
                    sender.say(t, "I-I'm- Ah!  Going to climax alreadyyy!");
            } else
            if(sender.getDeviancy() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                if(sender.getDignity() > 66)
                    sender.say(t, "Ngh!  Ah!  Aaah!");
                else
                if(sender.getDignity() > 33)
                    sender.say(t, "This is... amazing...!");
                else
                    sender.say(t, "Ooogh!  It's squeezing meee!");
            } else
            if(sender.getObedience() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.LEWD);
                sender.say(t, "I'll hold out... as long as I can...!");
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.LEWD);
                sender.say(t, "Why... are you making me feel so good...!?");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
                sender.say(t, "J-Just... have to calm down...!");
            }
        } else
        if(this == Project.PenetratedAnally)
        {
            if(Project.PushDown.isInProgress(sender, receiver).booleanValue())
            {
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getObedience() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Yes!  Yes!  Cum inside me!.");
                    } else
                    if(sender.getObedience() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, (new StringBuilder("Aaah...  Your cock is amazing, ")).append(sender.demonLord()).append("!").toString());
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                        sender.say(t, "You... t-tricked me into doing this...!");
                    }
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getConfidence() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                        sender.say(t, "Ngh...!  I know you like it... rough...!");
                    } else
                    if(sender.getConfidence() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                        sender.say(t, "It's...  Ah!  Inside!");
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Th-This... actually feels really good...!");
                    }
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.LEWD);
                    if(sender.getDignity() > 66)
                        sender.say(t, "I can't believe... I'm doing this...!");
                    else
                    if(sender.getDignity() > 33)
                        sender.say(t, "Let me... catch my breath...");
                    else
                        sender.say(t, "Ow...");
                }
            } else
            if(Project.BeLubricated.isInProgress(sender, null).booleanValue())
            {
                if(sender.getObedience() > 66)
                {
                    if(sender.getDeviancy() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Yes!  Yes!  Take me!  Take me!");
                    } else
                    if(sender.getDeviancy() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                        sender.say(t, (new StringBuilder("I'm sorry, ")).append(sender.demonLord()).append("!  It feels too good with you inside me!").toString());
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                        sender.say(t, "Does it feel good when I... ngh... squeeze?  Like this?");
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                    if(sender.getConfidence() > 66)
                        sender.say(t, "Aaagh...!  This shouldn't... feel so good...!");
                    else
                    if(sender.getConfidence() > 33)
                        sender.say(t, "Ah!  Ah, wow...");
                    else
                        sender.say(t, "Mm!  Nn...!  Ah, I-I'm...!");
                } else
                if(sender.getInnocence() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                    sender.say(t, "Aaagh, gaaah!  Stop it, nnnaaah!");
                } else
                if(sender.getInnocence() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.LEWD);
                    sender.say(t, "Mph!  Gh!  Agh!");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                    sender.say(t, "Just... climax already, you brute...!");
                }
            } else
            if(sender.forsakenOwner == null && sender.chosenOwner != null && sender.chosenOwner.aVirg.booleanValue())
            {
                if(sender.getConfidence() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                    sender.say(t, "That's it!  We're done here!");
                } else
                if(sender.getConfidence() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                    sender.say(t, "You... You tried to... Ugh, I never should have come here!");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
                    sender.say(t, "I-IT HURTS!  STOOOP!");
                }
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.FEAR);
                if(sender.getConfidence() > 66)
                {
                    if(sender.getObedience() > 66)
                        sender.say(t, (new StringBuilder("I'm... guh... f-fine, ")).append(sender.demonLord()).append("!").toString());
                    else
                    if(sender.getObedience() > 33)
                        sender.say(t, "Why are you doing this!?  I'm serving you now!  Gaaah, pleeease!");
                    else
                        sender.say(t, "I'll stop fighting, I'll stop talking back, I'll do anything!");
                } else
                if(sender.getConfidence() > 33)
                {
                    if(sender.getDignity() > 66)
                        sender.say(t, "Please, please!  Stooop!");
                    else
                    if(sender.getDignity() > 33)
                        sender.say(t, "Mgh...  guh... I-I can't...");
                    else
                        sender.say(t, "AAAGH, NOOO!");
                } else
                if(sender.getDeviancy() > 66)
                    sender.say(t, "Nnnaaah~!");
                else
                if(sender.getDeviancy() > 33)
                    sender.say(t, "Aah, nnooo, oooh!");
                else
                    sender.say(t, "Agh!  Mph, guh, n-no...!");
            }
        } else
        if(this == Project.BeTied)
        {
            if(sender.forsakenOwner == null && sender.chosenOwner != null)
                if(sender.chosenOwner.truce.booleanValue() || sender.chosenOwner.drained.booleanValue())
                {
                    if(sender.getConfidence() > 66)
                    {
                        if(sender.getInnocence() > 66)
                        {
                            Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.FEAR);
                            sender.say(t, "H-Heh, this is actually a little bit scary...");
                        } else
                        if(sender.getInnocence() > 33)
                        {
                            Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                            sender.say(t, "You'll have to try harder than this if you want to make me act meek and submissive.");
                        } else
                        {
                            Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.ANGER);
                            sender.say(t, "Bondage play?  Hm, if you insist.");
                        }
                    } else
                    if(sender.getConfidence() > 33)
                    {
                        if(sender.getInnocence() > 66)
                        {
                            Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
                            sender.say(t, "I guess I can't refuse to let you tie me up...  B-But I can break out anytime I want!  Probably!");
                        } else
                        if(sender.getInnocence() > 33)
                        {
                            Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                            sender.say(t, "You'd better not be trying to trick me into doing something I don't want to do, Demon Lord...");
                        } else
                        {
                            Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                            sender.say(t, "I see that you're testing the limits of my trust...");
                        }
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
                        if(sender.getInnocence() > 66)
                            sender.say(t, "I-I said I'd do whatever you want!  You don't have to tie me up...  But I guess you can if you want to...");
                        else
                        if(sender.getInnocence() > 33)
                            sender.say(t, "Nn...  P-Please... be gentle with me...");
                        else
                            sender.say(t, "I... I can endure this... I must not cry...");
                    }
                } else
                if(sender.getConfidence() > 66)
                {
                    if(sender.getInnocence() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                        sender.say(t, "Hey!  What are you trying to do!?  I'm gonna bust out of here right away!");
                    } else
                    if(sender.getInnocence() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
                        sender.say(t, "Are you seriously trying to take me down alone!?  Looks like you need to be taught a lesson!");
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
                        sender.say(t, "I'm sure that you aren't stupid enough to think that physical restraints of this level are meaningful to one of the Chosen.");
                    }
                } else
                if(sender.getConfidence() > 33)
                {
                    if(sender.getInnocence() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.ANGER);
                        sender.say(t, "I'm...!  Nnngh...!  Not gonna let you tie me up!");
                    } else
                    if(sender.getInnocence() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.FEAR);
                        sender.say(t, "Stop that!  Stop, or I'll stop you myself!");
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                        sender.say(t, "Ridiculous!  I offer you a chance at good-faith negotiation, and this is how you respond!?");
                    }
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
                    if(sender.getInnocence() > 66)
                        sender.say(t, "S-Stop!  I'm gonna start fighting back!  I-I really am!");
                    else
                    if(sender.getInnocence() > 33)
                        sender.say(t, "Was this some sort of trap!?  No!  I-I have to escape!");
                    else
                        sender.say(t, "I... I know that I can escape these restraints!  I must not panic!");
                }
        } else
        if(this == Project.LickCock)
        {
            if(sender.getObedience() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                if(sender.getInnocence() > 66)
                    sender.say(t, (new StringBuilder("Aaah, yes...!  It's ")).append(sender.theDemonLord()).append("'s cock!  ").append(sender.TheDemonLord()).append("'s cock!").toString());
                else
                if(sender.getInnocence() > 33)
                    sender.say(t, (new StringBuilder("Does this feel good, ")).append(sender.demonLord()).append("?  Mm... I'm happy...").toString());
                else
                    sender.say(t, (new StringBuilder("This... is my purpose now...  To give ")).append(sender.theDemonLord()).append(" pleasure...").toString());
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.LEWD);
                if(sender.getConfidence() > 66)
                    sender.say(t, "I'll make you cum right away!");
                else
                if(sender.getConfidence() > 33)
                    sender.say(t, "Mm...");
                else
                    sender.say(t, "A-Am I doing it right?  Does my mouth feel good?");
            } else
            {
                if(sender.getDeviancy() > 33)
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                else
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                if(sender.getDignity() > 66)
                    w.append(t, "Ugh...  It just... smells disgusting...");
                else
                if(sender.getDignity() > 33)
                    w.append(t, "Fine... but if you keep acting smug... I swear I'll bite it...");
                else
                    w.append(t, "I can do this.  It doesn't even taste that bad.");
            }
        } else
        if(this == Project.LickPussy)
        {
            if(sender.getObedience() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                if(sender.getInnocence() > 66)
                    sender.say(t, (new StringBuilder("Aaah, yes...!  It's ")).append(sender.theDemonLord()).append("'s pussy!  ").append(sender.TheDemonLord()).append("'s pussy!").toString());
                else
                if(sender.getInnocence() > 33)
                    sender.say(t, (new StringBuilder("Does this feel good, ")).append(sender.demonLord()).append("?  Mm... I'm happy...").toString());
                else
                    sender.say(t, (new StringBuilder("This... is my purpose now...  To give ")).append(sender.theDemonLord()).append(" pleasure...").toString());
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.LEWD);
                if(sender.getConfidence() > 66)
                    sender.say(t, "I'll make you cum right away!");
                else
                if(sender.getConfidence() > 33)
                    sender.say(t, "Mm...");
                else
                    sender.say(t, "A-Am I doing it right?  Does my tongue feel good?");
            } else
            {
                if(sender.getDeviancy() > 33)
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                else
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                if(sender.getDignity() > 66)
                    w.append(t, "Ugh...  It just... smells disgusting...");
                else
                if(sender.getDignity() > 33)
                    w.append(t, "Do you think I'm enjoying this?  Ugh...");
                else
                    w.append(t, "I can do this.  It doesn't even taste that bad.");
            }
        } else
        if(this == Project.StepOnCock)
        {
            if(sender.getObedience() > 66)
            {
                if(sender.getInnocence() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                    sender.say(t, "Doing it with my feet is fun!");
                } else
                if(sender.getInnocence() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.FEAR);
                    sender.say(t, "If I do it too hard, it will hurt... but if I don't do it hard enough, it won't feel good.");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                    sender.say(t, (new StringBuilder("I've trained every single part of my body for serving ")).append(sender.theDemonLord()).append(".").toString());
                }
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                if(sender.getConfidence() > 66)
                    sender.say(t, "I always knew I'd be able to find a way to grind the Demon Lord under my heel...");
                else
                if(sender.getConfidence() > 33)
                    sender.say(t, "This is really all you want from me?");
                else
                    sender.say(t, "I-Is this really alright?  You aren't going to suddenly get angry?");
            } else
            {
                if(sender.getDeviancy() > 33)
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                else
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
                if(sender.getMorality() > 66)
                    sender.say(t, "Stop enjoying this!  You're making me look like some sort of pervert!");
                else
                if(sender.getMorality() > 33)
                    sender.say(t, "Don't move, or I'll crush it!");
                else
                    sender.say(t, "Still doesn't hurt!?  Then I'll just do it harder!");
            }
        } else
        if(this == Project.CockSteppedOn)
        {
            if(sender.getDeviancy() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                if(sender.getInnocence() > 66)
                    sender.say(t, (new StringBuilder("Ah!  Aaah!  ")).append(sender.TheDemonLord()).append("'s foot is driving me crazyyy!").toString());
                else
                if(sender.getInnocence() > 33)
                    sender.say(t, "Aaah, ooough, wooow!");
                else
                    sender.say(t, (new StringBuilder("Yes!  Yeesss!  Stomp on me harder, ")).append(sender.demonLord()).append(", pleeease!").toString());
            } else
            if(sender.getDeviancy() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                if(sender.getDignity() > 66)
                    sender.say(t, "Enjoying something like this...!  I'm- nnnaaah!  Pathetiiic!");
                else
                if(sender.getDignity() > 33)
                    sender.say(t, "Guh!  I c-can't... resist...!");
                else
                    sender.say(t, "Aaah, nooo!  This shouldn't feel goood!");
            } else
            {
                if(sender.getObedience() > 33)
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.SHAME);
                else
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                if(sender.getConfidence() > 66)
                    sender.say(t, "Ngh!  Agh...  I can take this...!");
                else
                if(sender.getConfidence() > 33)
                    sender.say(t, "Gah!  Wh-Where are you- Gh!");
                else
                    sender.say(t, "Nn!  It... hurts...");
            }
        } else
        if(this == Project.StepOnClit)
        {
            if(sender.getObedience() > 66)
            {
                if(sender.getInnocence() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                    sender.say(t, "Doing it with my feet is fun!");
                } else
                if(sender.getInnocence() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.FEAR);
                    sender.say(t, "If I do it too hard, it will hurt... but if I don't do it hard enough, it won't feel good.");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                    sender.say(t, (new StringBuilder("I've trained every single part of my body for serving ")).append(sender.theDemonLord()).append(".").toString());
                }
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                if(sender.getConfidence() > 66)
                    sender.say(t, "I always knew I'd be able to find a way to grind the Demon Lord under my heel...");
                else
                if(sender.getConfidence() > 33)
                    sender.say(t, "This is really all you want from me?");
                else
                    sender.say(t, "I-Is this really alright?  You aren't going to suddenly get angry?");
            } else
            {
                if(sender.getDeviancy() > 33)
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                else
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
                if(sender.getMorality() > 66)
                    sender.say(t, "Stop enjoying this!  You're making me look like some sort of pervert!");
                else
                if(sender.getMorality() > 33)
                    sender.say(t, "Don't move, or I'll shove my whole foot inside!");
                else
                    sender.say(t, "Still doesn't hurt!?  Then I'll just do it harder!");
            }
        } else
        if(this == Project.ClitSteppedOn)
        {
            if(sender.getDeviancy() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                if(sender.getInnocence() > 66)
                    sender.say(t, (new StringBuilder("Ah!  Aaah!  ")).append(sender.TheDemonLord()).append("'s foot is driving me crazyyy!").toString());
                else
                if(sender.getInnocence() > 33)
                    sender.say(t, "Aaah, ooough, wooow!");
                else
                    sender.say(t, (new StringBuilder("Yes!  Yeesss!  Stomp on me harder, ")).append(sender.demonLord()).append(", pleeease!").toString());
            } else
            if(sender.getDeviancy() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                if(sender.getDignity() > 66)
                    sender.say(t, "Enjoying something like this...!  I'm- nnnaaah!  Pathetiiic!");
                else
                if(sender.getDignity() > 33)
                    sender.say(t, "Guh!  I c-can't... resist...!");
                else
                    sender.say(t, "Aaah, nooo!  This shouldn't feel goood!");
            } else
            {
                if(sender.getObedience() > 33)
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.SHAME);
                else
                    Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                if(sender.getConfidence() > 66)
                    sender.say(t, "Ngh!  Agh...  I can take this...!");
                else
                if(sender.getConfidence() > 33)
                    sender.say(t, "Gah!  Wh-Where are you- Gh!");
                else
                    sender.say(t, "Nn!  It... hurts...");
            }
        } else
        if(this == Project.DirtyTalk)
            if(sender.getObedience() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                if(w.sceneDuration % 3 == 0)
                {
                    if(sender.getMorality() > 66)
                        sender.say(t, "Please don't hold back.  You can use my body to cum as many times as you like...");
                    else
                    if(sender.getMorality() > 33)
                        sender.say(t, "I'm so happy that you're enjoying this!");
                    else
                        sender.say(t, "I'm your favorite, right?  Go on, tell me that I'm your favorite...!");
                } else
                if(w.sceneDuration % 3 == 1)
                {
                    if(sender.getConfidence() > 66)
                        sender.say(t, "I can... nnh... give you more pleasure than anyone else...!");
                    else
                    if(sender.getConfidence() > 33)
                        sender.say(t, "I can tell that you're feeling good.");
                    else
                        sender.say(t, (new StringBuilder("E-Even someone like me can make ")).append(sender.theDemonLord()).append(" feel good, huh?").toString());
                } else
                if(sender.getDeviancy() > 66)
                    sender.say(t, (new StringBuilder("Aaah, ")).append(sender.demonLord()).append("!  I love you!  I love making you cum!").toString());
                else
                if(sender.getDeviancy() > 33)
                    sender.say(t, "Being able to make you cum... feels incredible...!");
                else
                    sender.say(t, "Just knowing that you're happy... is what makes me feel good.");
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                if(w.sceneDuration % 3 == 0)
                {
                    if(sender.getMorality() > 66)
                        sender.say(t, (new StringBuilder("It almost feels like I'm raping you, ")).append(sender.demonLord()).append(".").toString());
                    else
                    if(sender.getMorality() > 33)
                        sender.say(t, "You aren't holding back, are you?");
                    else
                        sender.say(t, "I'll make you cum, but you'd better do something nice for me in return.");
                } else
                if(w.sceneDuration % 3 == 1)
                {
                    if(sender.getConfidence() > 66)
                        sender.say(t, "Go on!  Cum!");
                    else
                    if(sender.getConfidence() > 33)
                        sender.say(t, "Are you cumming already?");
                    else
                        sender.say(t, "Right now, I have... the p-power... to decide whether you cum...");
                } else
                if(sender.getDeviancy() > 66)
                    sender.say(t, "The face you make when you want to cum... is pretty sexy too...");
                else
                if(sender.getDeviancy() > 33)
                    sender.say(t, "Tell me how good it feels.");
                else
                    sender.say(t, (new StringBuilder("You're a real pervert, ")).append(sender.demonLord()).append(".").toString());
            } else
            if(w.sceneDuration % 3 == 0)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
                if(sender.getMorality() > 66)
                    sender.say(t, "You're nothing but a pitiful pervert.");
                else
                if(sender.getMorality() > 33)
                    sender.say(t, "I can't believe you're enjoying this.");
                else
                    sender.say(t, "I'm going to do it harder.  Let's see if you can feel pain.");
            } else
            if(w.sceneDuration % 3 == 1)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
                if(sender.getConfidence() > 66)
                    sender.say(t, "What a worthless Demon Lord.");
                else
                if(sender.getConfidence() > 33)
                    sender.say(t, "Disgusting!");
                else
                    sender.say(t, "A-Are you really this desperate to have someone touch you...?");
            } else
            if(sender.getDeviancy() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                sender.say(t, "Hurry up and cum!  Cum and cum until you can't anymore!");
            } else
            if(sender.getDeviancy() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                sender.say(t, "How does it feel to be dominated!?");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FOCUS);
                sender.say(t, "This is all it takes?  Your body is so weak.");
            }
        sender.say(t, "\"");
        sender.specialLine = 0;
    }

    public void shortDescription(JTextPane t, WorldState w, Body sender, Body receiver)
    {
        if(this == Project.TweakClit)
            w.append(t, (new StringBuilder("\n")).append(sender.capitalizedOwnerName()).append(" is stroking ").append(receiver.ownerName()).append("'s clit").toString());
        else
        if(this == Project.ClitTweaked)
            w.append(t, (new StringBuilder("\n")).append(receiver.capitalizedOwnerName()).append(" is stroking ").append(sender.ownerName()).append("'s clit").toString());
        else
        if(this == Project.SpreadLegs)
            w.append(t, (new StringBuilder("\n")).append(sender.capitalizedOwnerName()).append(" is spreading ").append(sender.hisHer()).append(" legs wide apart").toString());
        else
        if(this == Project.PushDown)
            w.append(t, (new StringBuilder("\n")).append(sender.OwnerName()).append(" is atop ").append(receiver.ownerName()).toString());
        else
        if(this == Project.PullDown)
            w.append(t, (new StringBuilder("\n")).append(receiver.OwnerName()).append(" is atop ").append(sender.ownerName()).toString());
        else
        if(this == Project.TieUp)
            w.append(t, (new StringBuilder("\n")).append(receiver.OwnerName()).append(" has been tied up by ").append(sender.ownerName()).toString());
        else
        if(this == Project.BeTied)
            w.append(t, (new StringBuilder("\n")).append(sender.OwnerName()).append(" has been tied up by ").append(receiver.ownerName()).toString());
        else
        if(this == Project.StrokeCock)
            w.append(t, (new StringBuilder("\n")).append(sender.OwnerName()).append(" strokes ").append(receiver.ownerName()).append("'s cock").toString());
        else
        if(this == Project.CockStroked)
            w.append(t, (new StringBuilder("\n")).append(receiver.OwnerName()).append(" strokes ").append(sender.ownerName()).append("'s cock").toString());
        else
        if(this == Project.BeLubricated)
            w.append(t, (new StringBuilder("\n")).append(sender.OwnerName()).append("'s anus is coated with slick lubricant").toString());
        else
        if(this == Project.VaginalPenetrate)
            w.append(t, (new StringBuilder("\n")).append(sender.OwnerName()).append(" fucks ").append(receiver.ownerName()).append(" vaginally").toString());
        else
        if(this == Project.PenetratedVaginally)
            w.append(t, (new StringBuilder("\n")).append(receiver.OwnerName()).append(" fucks ").append(sender.ownerName()).append(" vaginally").toString());
        else
        if(this == Project.AnalPenetrate)
            w.append(t, (new StringBuilder("\n")).append(sender.OwnerName()).append(" fucks ").append(receiver.ownerName()).append("'s ass").toString());
        else
        if(this == Project.PenetratedAnally)
            w.append(t, (new StringBuilder("\n")).append(receiver.OwnerName()).append(" fucks ").append(sender.ownerName()).append("'s ass").toString());
        else
        if(this == Project.StripOther)
            w.append(t, (new StringBuilder("\n")).append(sender.OwnerName()).append(" strips off ").append(receiver.ownerName()).append("'s clothes.").toString());
        else
        if(this == Project.Stripped)
            w.append(t, (new StringBuilder("\n")).append(receiver.OwnerName()).append(" strips off ").append(sender.ownerName()).append("'s clothes.").toString());
        else
        if(this == Project.LickCock)
            w.append(t, (new StringBuilder("\n")).append(sender.OwnerName()).append(" licks ").append(receiver.ownerName()).append("'s cock.").toString());
        else
        if(this == Project.CockLicked)
            w.append(t, (new StringBuilder("\n")).append(receiver.OwnerName()).append(" licks ").append(sender.ownerName()).append("'s cock.").toString());
        else
        if(this == Project.LickPussy)
            w.append(t, (new StringBuilder("\n")).append(sender.OwnerName()).append(" licks ").append(receiver.ownerName()).append("'s pussy.").toString());
        else
        if(this == Project.PussyLicked)
            w.append(t, (new StringBuilder("\n")).append(receiver.OwnerName()).append(" licks ").append(sender.ownerName()).append("'s pussy.").toString());
        else
        if(this == Project.Supine)
            w.append(t, (new StringBuilder("\n")).append(sender.OwnerName()).append(" lies on ").append(sender.hisHer()).append(" back.").toString());
        else
        if(this == Project.StepOnCock)
            w.append(t, (new StringBuilder("\n")).append(sender.OwnerName()).append(" rubs ").append(receiver.ownerName()).append("'s cock with ").append(sender.hisHer()).append(" foot.").toString());
        else
        if(this == Project.CockSteppedOn)
            w.append(t, (new StringBuilder("\n")).append(receiver.OwnerName()).append(" rubs ").append(sender.ownerName()).append("'s cock with ").append(receiver.hisHer()).append(" foot.").toString());
        else
        if(this == Project.StepOnClit)
            w.append(t, (new StringBuilder("\n")).append(sender.OwnerName()).append(" rubs ").append(receiver.ownerName()).append("'s clit with ").append(sender.hisHer()).append(" foot.").toString());
        else
        if(this == Project.ClitSteppedOn)
            w.append(t, (new StringBuilder("\n")).append(receiver.OwnerName()).append(" rubs ").append(sender.ownerName()).append("'s clit with ").append(receiver.hisHer()).append(" foot.").toString());
    }

    public void endActivityFlavor(JTextPane t, WorldState w, Body sender, Body receiver)
    {
        if(this == Project.TweakClit)
            w.append(t, (new StringBuilder("\n\n")).append(sender.capitalizedOwnerName()).append(" takes ").append(sender.hisHer()).append(" hand from ").append(receiver.ownerName()).append("'s clit.").toString());
        else
        if(this == Project.ClitTweaked)
            w.append(t, (new StringBuilder("\n\n")).append(sender.capitalizedOwnerName()).append(" moves ").append(sender.hisHer()).append(" hips away from ").append(receiver.ownerName()).append("'s hand.").toString());
        else
        if(this == Project.PushDown)
            w.append(t, (new StringBuilder("\n\n")).append(sender.capitalizedOwnerName()).append(" gets off ").append(receiver.ownerName()).append(", standing up.").toString());
        else
        if(this == Project.PullDown)
            w.append(t, (new StringBuilder("\n\n")).append(sender.OwnerName()).append(" gets out from under ").append(receiver.ownerName()).append(".").toString());
        else
        if(this == Project.TieUp)
            w.append(t, (new StringBuilder("\n\n")).append(sender.OwnerName()).append(" unties ").append(receiver.ownerName()).append("'s bindings.").toString());
        else
        if(this == Project.StrokeCock)
            w.append(t, (new StringBuilder("\n\n")).append(sender.capitalizedOwnerName()).append(" takes ").append(sender.hisHer()).append(" hand from ").append(receiver.ownerName()).append("'s cock.").toString());
        else
        if(this == Project.CockStroked)
            w.append(t, (new StringBuilder("\n\n")).append(sender.capitalizedOwnerName()).append(" moves ").append(sender.hisHer()).append(" hips away from ").append(receiver.ownerName()).append("'s hand.").toString());
        else
        if(this == Project.VaginalPenetrate)
            w.append(t, (new StringBuilder("\n\n")).append(sender.capitalizedOwnerName()).append(" pulls ").append(sender.himHer()).append("self out of ").append(receiver.ownerName()).append("'s pussy.").toString());
        else
        if(this == Project.PenetratedVaginally)
            w.append(t, (new StringBuilder("\n\n")).append(sender.capitalizedOwnerName()).append(" lifts ").append(sender.himHer()).append("self until ").append(receiver.ownerName()).append("'s cock slides out of ").append(sender.hisHer()).append(" pussy.").toString());
        else
        if(this == Project.AnalPenetrate)
            w.append(t, (new StringBuilder("\n\n")).append(sender.OwnerName()).append(" pulls ").append(sender.himHer()).append("self out of ").append(receiver.ownerName()).append("'s ass.").toString());
        else
        if(this == Project.PenetratedAnally)
            w.append(t, (new StringBuilder("\n\n")).append(sender.OwnerName()).append(" lifts ").append(sender.himHer()).append("self until ").append(receiver.ownerName()).append("'s cock slides out of ").append(sender.hisHer()).append(" ass.").toString());
        else
        if(this == Project.StripOther)
            w.append(t, (new StringBuilder("\n\n")).append(sender.OwnerName()).append(" stops removing ").append(receiver.ownerName()).append("'s clothes.").toString());
        else
        if(this == Project.LickCock)
            w.append(t, (new StringBuilder("\n\n")).append(sender.OwnerName()).append(" stops licking ").append(receiver.ownerName()).append("'s cock.").toString());
        else
        if(this == Project.CockLicked)
            w.append(t, (new StringBuilder("\n\n")).append(sender.OwnerName()).append(" pulls ").append(sender.hisHer()).append(" cock away from ").append(receiver.ownerName()).append("'s mouth.").toString());
        else
        if(this == Project.LickPussy)
            w.append(t, (new StringBuilder("\n\n")).append(sender.OwnerName()).append(" stops licking ").append(receiver.ownerName()).append("'s pussy.").toString());
        else
        if(this == Project.PussyLicked)
            w.append(t, (new StringBuilder("\n\n")).append(sender.OwnerName()).append(" pulls ").append(sender.hisHer()).append(" hips away from ").append(receiver.ownerName()).append("'s tongue.").toString());
        else
        if(this == Project.Supine)
            w.append(t, (new StringBuilder("\n\n")).append(sender.OwnerName()).append(" stands up.").toString());
        else
        if(this == Project.StepOnCock)
            w.append(t, (new StringBuilder("\n\n")).append(sender.OwnerName()).append(" takes ").append(sender.hisHer()).append(" foot off ").append(receiver.ownerName()).append("'s cock.").toString());
        else
        if(this == Project.CockSteppedOn)
            w.append(t, (new StringBuilder("\n\n")).append(sender.OwnerName()).append(" moves ").append(sender.hisHer()).append(" crotch away from ").append(receiver.ownerName()).append("'s foot.").toString());
        else
        if(this == Project.StepOnClit)
            w.append(t, (new StringBuilder("\n\n")).append(sender.OwnerName()).append(" takes ").append(sender.hisHer()).append(" foot off ").append(receiver.ownerName()).append("'s crotch.").toString());
        else
        if(this == Project.ClitSteppedOn)
            w.append(t, (new StringBuilder("\n\n")).append(sender.OwnerName()).append(" moves ").append(sender.hisHer()).append(" crotch away from ").append(receiver.ownerName()).append("'s foot.").toString());
    }

    public void startActivity(JTextPane t, WorldState w, Body sender, Body receiver)
    {
        if(this == Project.BeTied)
        {
            sender.freeBodyPart(HAND);
            sender.freeBodyPart(FOOT);
        }
        Activity newInProgress[] = new Activity[sender.inProgress.length + 1];
        Body newTargets[] = new Body[sender.targets.length + 1];
        for(int i = 0; i < sender.inProgress.length; i++)
        {
            newInProgress[i] = sender.inProgress[i];
            newTargets[i] = sender.targets[i];
        }

        newInProgress[newInProgress.length - 1] = this;
        newTargets[newTargets.length - 1] = receiver;
        sender.inProgress = newInProgress;
        sender.targets = newTargets;
        if(this == Project.TweakClit)
        {
            if(sender == w.lordBody)
                w.append(t, (new StringBuilder(String.valueOf(sender.capitalizedOwnerName()))).append(" begins to caress ").append(receiver.ownerName()).append("'s clit.").toString());
            else
            if(receiver == w.lordBody)
            {
                w.append(t, (new StringBuilder(String.valueOf(sender.capitalizedOwnerName()))).append(" ").toString());
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, (new StringBuilder("loses ")).append(sender.himHer()).append("self in the act of rubbing ").append(receiver.ownerName()).append("'s clit, eyes glazed over and drooling with desire.").toString());
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, (new StringBuilder("reaches eagerly for ")).append(receiver.ownerName()).append("'s clit, stroking it with an aggressive firmness that comes just short of being painful.").toString());
                    else
                        w.append(t, (new StringBuilder("abruptly puts ")).append(sender.hisHer()).append(" hand against ").append(receiver.ownerName()).append("'s clit and starts rubbing up and down, then fondling it with ").append(sender.hisHer()).append(" fingers, then gently tugging on it, using every technique at ").append(sender.hisHer()).append(" disposal.").toString());
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, (new StringBuilder("pokes and prods ")).append(receiver.ownerName()).append("'s clit, then gradually gets into a rhythm of stroking it as ").append(receiver.heShe()).append(" grows more confident in what ").append(receiver.heShe()).append("'s doing.").toString());
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, (new StringBuilder("brushes ")).append(sender.hisHer()).append(" fingers against ").append(receiver.ownerName()).append("'s clit, trailing them up and down, then growing more firm, stroking it in earnest.").toString());
                    else
                        w.append(t, (new StringBuilder("carefully starts to stroke ")).append(receiver.ownerName()).append("'s clit, staring into ").append(receiver.hisHer()).append(" eyes to gauge ").append(receiver.hisHer()).append(" reaction.").toString());
                } else
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, (new StringBuilder("hesitantly touches ")).append(receiver.ownerName()).append("'s clit, then begins to clumsily stroke it, averting ").append(sender.hisHer()).append(" eyes as ").append(sender.heShe()).append(" does so.  ").toString());
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, (new StringBuilder("begins to stroke ")).append(receiver.ownerName()).append("'s clit with stiff, mechanical movements.  ").toString());
                    else
                        w.append(t, (new StringBuilder("brings ")).append(sender.hisHer()).append(" fingers to ").append(receiver.ownerName()).append("'s clit and begins to stroke it with precise, methodical movements.  ").toString());
                    if(sender.getObedience() > 66)
                        w.append(t, (new StringBuilder("It's clear that ")).append(sender.heShe()).append("'s unfamiliar with this sort of thing, but ").append(sender.heShe()).append("'s doing ").append(sender.hisHer()).append(" best for ").append(receiver.ownerName()).append(".").toString());
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.HisHer()))).append(" heart clearly isn't in it.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.HeShe()))).append(" looks sickened by what ").append(sender.heShe()).append("'s doing.").toString());
                }
            }
        } else
        if(this == Project.ClitTweaked)
        {
            if(sender == w.lordBody)
            {
                w.append(t, (new StringBuilder(String.valueOf(sender.capitalizedOwnerName()))).append("'s clit is caressed by ").append(receiver.ownerName()).append(".").toString());
            } else
            {
                w.append(t, sender.capitalizedOwnerName());
                if(sender.getInnocence() > 66)
                {
                    w.append(t, (new StringBuilder(" gasps at the sudden intensity of the stimulation to ")).append(sender.hisHer()).append(" most sensitive part, and ").toString());
                    if(sender.getDeviancy() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.hisHer()))).append(" eyes glaze over as ").append(sender.heShe()).append(" loses the ability to think of anything other than feeling even better.  ").toString());
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.hisHer()))).append(" hips jerk wildly, seemingly caught between jerking away and pushing themselves against ").append(receiver.ownerName()).append("'s fingers.  ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.heShe()))).append(" reflexively tries to jerk ").append(sender.hisHer()).append(" hips away.  ").toString());
                } else
                if(sender.getInnocence() > 33)
                {
                    w.append(t, (new StringBuilder(" feels jolts of pleasure shooting into ")).append(sender.hisHer()).append(" lower tummy").toString());
                    if(sender.getDeviancy() > 66)
                        w.append(t, (new StringBuilder(", and ")).append(sender.heShe()).append(" moans helplessly, ").append(sender.hisHer()).append(" well-trained body eagerly submitting itself to ").append(receiver.ownerName()).append("'s touch.  ").toString());
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, (new StringBuilder(", and it's a struggle for ")).append(sender.himHer()).append(" to hold onto ").append(sender.hisHer()).append(" reason.  ").toString());
                    else
                        w.append(t, (new StringBuilder(", even as ")).append(sender.heShe()).append(" tries to ignore them.  ").toString());
                } else
                {
                    w.append(t, (new StringBuilder(" knew this was coming, but ")).append(sender.heShe()).append(" still ").toString());
                    if(sender.getDeviancy() > 66)
                        w.append(t, (new StringBuilder("squirms and cries out, unable to maintain any self-control in the face of the pleasure ")).append(sender.heShe()).append("'s come to crave.  ").toString());
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, (new StringBuilder("gasps softly at the waves of pleasure that begin to wash over ")).append(sender.hisHer()).append(" lower body.  ").toString());
                    else
                        w.append(t, "flinches and grimaces, uncomfortable with the intensity of the sensations.  ");
                }
                if(receiver == w.lordBody)
                    if(sender.getDeviancy() > 66)
                    {
                        if(sender.getObedience() > 66)
                            w.append(t, (new StringBuilder(String.valueOf(sender.HisHer()))).append(" body has grown quite sensitive, but it's even more sensitive than usual when ").append(sender.heShe()).append(" knows ").append(sender.heShe()).append("'s being touched by ").append(receiver.ownerName()).append(".").toString());
                        else
                        if(sender.getObedience() > 33)
                            w.append(t, (new StringBuilder("Soon, ")).append(sender.heShe()).append("'s completely consumed in ").append(sender.hisHer()).append(" efforts to hump ").append(receiver.ownerName()).append("'s hand.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(sender.HeShe()))).append(" tries to resist, but ").append(sender.hisHer()).append(" body has grown so sensitive that the lightest touch to ").append(sender.hisHer()).append(" weak spots is enough to subdue ").append(sender.himHer()).append(".").toString());
                    } else
                    if(sender.getDeviancy() > 33)
                    {
                        if(sender.getObedience() > 66)
                            w.append(t, (new StringBuilder("Even more than the physical stimulation itself, ")).append(sender.ownerName()).append(" is ecstatic that ").append(receiver.ownerName()).append(" is pleasuring ").append(sender.himHer()).append(" directly.").toString());
                        else
                        if(sender.getObedience() > 33)
                            w.append(t, (new StringBuilder(String.valueOf(sender.HeShe()))).append("'s content to let ").append(receiver.ownerName()).append(" pleasure ").append(sender.himHer()).append(".").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(sender.HeShe()))).append(" has a hard time remembering that ").append(sender.heShe()).append("'s supposed to hate the Demon Lord.").toString());
                    } else
                    if(sender.getObedience() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.HeShe()))).append(" endures this for ").append(receiver.ownerName()).append("'s sake, hoping that ").append(receiver.ownerName()).append(" will enjoy playing with ").append(sender.hisHer()).append(" body.").toString());
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.HeShe()))).append(" doesn't make eye contact with ").append(receiver.ownerName()).append(", glancing off to the side.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.HeShe()))).append(" glares at ").append(receiver.ownerName()).append(", not appreciating it in the slightest.").toString());
            }
        } else
        if(this == Project.SpreadLegs)
        {
            if(sender != w.lordBody)
                w.append(t, (new StringBuilder(String.valueOf(sender.ownerName()))).append(" spreads ").append(sender.hisHer()).append(" legs wide apart, eager to be pleasured.").toString());
        } else
        if(this == Project.Praise)
        {
            if(sender != w.lordBody && receiver == w.lordBody)
                if(sender.getObedience() > 66)
                {
                    if(sender.getConfidence() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" repeatedly, insistently offers to do anything at all that ").append(receiver.ownerName()).append(" desires of ").append(sender.himHer()).append(".").toString());
                    else
                    if(sender.getConfidence() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" voices ").append(sender.hisHer()).append(" admiration for ").append(receiver.ownerName()).append(", describing all ").append(receiver.hisHer()).append(" great qualities at length.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" viciously insults ").append(sender.himHer()).append("self, offering ").append(sender.hisHer()).append(" body to ").append(receiver.ownerName()).append(" in order to make up for ").append(sender.hisHer()).append(" own failures.").toString());
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.getDeviancy() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" eagerly lavishes praise and encouragement on ").append(receiver.ownerName()).append(", hoping to be rewarded with pleasure.").toString());
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" encourages ").append(receiver.ownerName()).append(" to do whatever ").append(receiver.heShe()).append("'d like with ").append(sender.himHer()).append(".").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" humbly debases ").append(sender.himHer()).append("self and praises ").append(receiver.ownerName()).append(", but the quaver in ").append(sender.hisHer()).append(" voice betrays the fact that ").append(sender.heShe()).append("'s doing so out of fear rather than because ").append(sender.heShe()).append(" actually believes ").append(sender.hisHer()).append(" own words.").toString());
                } else
                if(sender.getInnocence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" starts to praise ").append(receiver.ownerName()).append(" before ").append(sender.heShe()).append(" remembers that they're supposed to be enemies.").toString());
                else
                if(sender.getInnocence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" grudgingly compliments ").append(receiver.ownerName()).append(", though ").append(sender.heShe()).append(" isn't happy about it.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" acts like ").append(sender.heShe()).append("'s coming around to ").append(receiver.ownerName()).append("'s side, giving voice to some compliments, but ").append(sender.heShe()).append("'s actually just hoping to manipulate ").append(receiver.himHer()).append(".").toString());
        } else
        if(this == Project.Insult)
        {
            if(sender != w.lordBody && receiver == w.lordBody)
                if(sender.getObedience() > 66)
                {
                    if(sender.getINJULevel() >= 2)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is growing too tired to serve ").append(receiver.ownerName()).append(", and ").append(sender.heShe()).append(" desperately tries to justify ").append(sender.hisHer()).append(" own weakness.").toString());
                    else
                    if(sender.getHATELevel() >= 1)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is becoming agitated, and while ").append(sender.heShe()).append(" adores ").append(receiver.ownerName()).append(" too much to confront ").append(receiver.himHer()).append(" directly, ").append(sender.hisHer()).append(" feelings still come through.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s psyche, fragile after being trained so thoroughly by the Demon Lord, begins to crack under the strain.").toString());
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.getINJULevel() >= 2)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is getting tired, and ").append(sender.heShe()).append(" lets ").append(receiver.ownerName()).append(" knows that ").append(sender.heShe()).append(" wants to stop this.").toString());
                    else
                    if(sender.getHATELevel() >= 1)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is growing annoyed with ").append(receiver.ownerName()).append(", and ").append(sender.heShe()).append(" lets ").append(receiver.himHer()).append(" know it in no uncertain terms.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" insults ").append(receiver.ownerName()).append(", hoping to get a reaction.").toString());
                } else
                if(sender.getINJULevel() >= 2)
                {
                    if(sender.imprisoned.booleanValue())
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is too tired to resist physically, but ").append(sender.heShe()).append(" still verbally attacks ").append(receiver.ownerName()).append(".").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is too tired to feel like fighting, but ").append(sender.heShe()).append(" still verbally attacks ").append(receiver.ownerName()).append(".").toString());
                } else
                if(sender.getHATELevel() >= 1)
                {
                    if(sender.imprisoned.booleanValue())
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" already hates ").append(receiver.ownerName()).append(", but ").append(sender.heShe()).append("'s even angrier than usual at being forced into this, and ").append(sender.heShe()).append(" vents ").append(sender.hisHer()).append(" frustration with a stream of insults").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" already hates ").append(receiver.ownerName()).append(", but ").append(sender.heShe()).append("'s even angrier about being suddenly approached and provoked like this.  ").append(sender.HeShe()).append(" vents ").append(sender.hisHer()).append(" feelings with a stream of insults.").toString());
                } else
                {
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" takes the chance to insult ").append(receiver.ownerName()).append(" to ").append(receiver.hisHer()).append(" face.").toString());
                }
        } else
        if(this == Project.PushDown)
        {
            if(sender == w.lordBody)
            {
                if(w.actingBody == sender)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" pushes ").append(receiver.ownerName()).append(" down onto ").append(receiver.hisHer()).append(" back.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" ends up atop ").append(receiver.ownerName()).append(".").toString());
            } else
            if(w.actingBody == sender)
            {
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getConfidence() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" roughly pushes ").append(receiver.ownerName()).append(" down onto ").append(receiver.hisHer()).append(" back, panting with barely-restrained desire as ").append(sender.heShe()).append(" takes ").append(sender.hisHer()).append(" place atop ").append(receiver.himHer()).append(".").toString());
                    else
                    if(sender.getConfidence() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" practically tackles ").append(receiver.ownerName()).append(" down onto the floor, overflowing with lust.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s lust has made ").append(sender.himHer()).append(" far more bold than usual, and ").append(sender.heShe()).append(" eagerly pushes ").append(receiver.ownerName()).append(" down onto ").append(receiver.hisHer()).append(" back.").toString());
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, (new StringBuilder("Overcome by ")).append(sender.hisHer()).append(" growing lust, ").append(sender.ownerName()).append(" wraps ").append(sender.hisHer()).append(" arms around ").append(receiver.ownerName()).append(" and kisses ").append(receiver.himHer()).append(" repeatedly, then seems surprised to realize that ").append(sender.heShe()).append("'s pushed ").append(receiver.himHer()).append(" down to the floor in the process.").toString());
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" embraces ").append(receiver.ownerName()).append(", guiding ").append(receiver.himHer()).append(" down onto ").append(receiver.hisHer()).append(" back.").toString());
                    else
                        w.append(t, (new StringBuilder("With ")).append(sender.hisHer()).append(" eyes locked onto ").append(receiver.ownerName()).append("'s ").append(receiver.mainOrgan()).append(", ").append(sender.ownerName()).append(" urges ").append(receiver.himHer()).append(" to lay down on ").append(receiver.hisHer()).append(" back, then climbs atop ").append(receiver.himHer()).append(".").toString());
                } else
                if(sender.getObedience() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" gently guides ").append(receiver.ownerName()).append(" onto ").append(receiver.hisHer()).append(" back, then lays atop ").append(receiver.himHer()).append(", taking deep breaths and trying to ready ").append(sender.himHer()).append("self to serve ").append(sender.theDemonLord()).append(".").toString());
                else
                if(sender.getObedience() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" pushes ").append(receiver.ownerName()).append(" down onto ").append(receiver.hisHer()).append(" back, but hesitates before going further, uncertain about how to proceed.").toString());
                else
                    w.append(t, (new StringBuilder("Wearing an annoyed expression on ")).append(sender.hisHer()).append(" face, ").append(sender.ownerName()).append(" pushes ").append(receiver.ownerName()).append(" down onto ").append(receiver.hisHer()).append(" back without a hint of sensuality.").toString());
            } else
            if(sender.getDeviancy() > 66)
            {
                if(sender.getConfidence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" hungrily pushes ").append(receiver.ownerName()).append(" the rest of the way down, taking ").append(sender.hisHer()).append(" place atop ").append(receiver.himHer()).append(".").toString());
                else
                if(sender.getConfidence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is eager to let ").append(sender.himHer()).append("self be pulled atop ").append(receiver.ownerName()).append(", looking down at ").append(receiver.himHer()).append(" with clear hunger in ").append(sender.hisHer()).append(" eyes.").toString());
                else
                    w.append(t, (new StringBuilder("As soon as it's clear that ")).append(receiver.ownerName()).append(" wants to be on the bottom, ").append(sender.ownerName()).append(" loses all traces of hesitation, clambering atop ").append(receiver.himHer()).append(" with enthusiasm.").toString());
            } else
            if(sender.getDeviancy() > 33)
            {
                if(sender.getInnocence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" seems surprised to suddenly find ").append(sender.himHer()).append("self atop ").append(receiver.ownerName()).append(".  ").append(sender.HeShe()).append(" blinks down at ").append(receiver.himHer()).append(", trying to decide what to do with ").append(sender.hisHer()).append(" new dominant position.").toString());
                else
                if(sender.getInnocence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" allows ").append(sender.himHer()).append("self to be pulled atop ").append(receiver.ownerName()).append(", and ").append(sender.heShe()).append(" finds that ").append(sender.heShe()).append(" enjoys the feeling of their bodies against each other.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" sees no reason to resist.  ").append(sender.HeShe()).append(" takes ").append(sender.hisHer()).append(" time deciding what to do next, looking down at ").append(receiver.ownerName()).append(" with thoughtful eyes.").toString());
            } else
            if(sender.getObedience() > 66)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" obediently lays atop ").append(receiver.ownerName()).append(", but ").append(sender.heShe()).append(" clearly isn't sure what to do next.").toString());
            else
            if(sender.getObedience() > 33)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" hesitates, resisting slightly as ").append(sender.heShe()).append("'s pulled down.  ").append(sender.HeShe()).append(" looks at ").append(receiver.ownerName()).append(" suspiciously.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" takes the opportunity to pin ").append(receiver.ownerName()).append(" down, clearly uninterested in using this position for sexual purposes.").toString());
        } else
        if(this == Project.PullDown)
        {
            if(sender == w.lordBody)
            {
                if(w.actingBody == sender)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" lays down on ").append(sender.hisHer()).append(" back, pulling ").append(receiver.ownerName()).append(" down on top of ").append(sender.himHer()).append(".").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" lays on ").append(sender.hisHer()).append(" back under ").append(receiver.ownerName()).append(".").toString());
            } else
            if(w.actingBody == sender)
            {
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getConfidence() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" forcefully pulls ").append(receiver.ownerName()).append(" atop ").append(sender.himHer()).append(", aggressive even in ").append(sender.hisHer()).append(" submission.").toString());
                    else
                    if(sender.getConfidence() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tangles ").append(sender.hisHer()).append(" limbs around ").append(receiver.ownerName()).append(", bringing them both down to the floor together with ").append(receiver.ownerName()).append(" atop ").append(sender.himHer()).append(".").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" lays down on ").append(sender.hisHer()).append(" back, weakly trying to pull ").append(receiver.ownerName()).append(" down with ").append(sender.himHer()).append(".  ").append(sender.HeShe()).append(" trembles with desire as ").append(sender.heShe()).append(" looks up at ").append(receiver.ownerName()).append(".").toString());
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getDignity() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" lays down under ").append(receiver.ownerName()).append(", trying to act as though ").append(sender.heShe()).append("'s doing ").append(receiver.himHer()).append(" a favor, but ").append(sender.ownerName()).append("'s lustful panting betrays how much ").append(sender.heShe()).append(" wants to be taken.").toString());
                    else
                    if(sender.getDignity() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" eagerly pulls ").append(receiver.ownerName()).append(" down atop ").append(sender.himHer()).append(", smiling in anticipation of what's to come.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" begs ").append(receiver.ownerName()).append(" to take ").append(sender.himHer()).append(", positioning ").append(sender.himHer()).append("self underneath ").append(receiver.himHer()).append(".").toString());
                } else
                if(sender.getObedience() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" submissively presents ").append(sender.himHer()).append("self to ").append(receiver.ownerName()).append(", laying on ").append(sender.hisHer()).append(" back and squirming nervously as ").append(sender.heShe()).append(" anticipates how ").append(sender.heShe()).append("'ll be used.").toString());
                else
                if(sender.getObedience() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" lays down for ").append(receiver.ownerName()).append(", but ").append(sender.heShe()).append(" refuses to meet ").append(receiver.hisHer()).append(" eyes, still uncertain how ").append(sender.heShe()).append(" feels about sex with the Demon Lord.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" angrily lays down, glaring up at ").append(receiver.ownerName()).append(".").toString());
            } else
            if(sender.getObedience() > 66)
            {
                if(sender.getDignity() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to hide ").append(sender.hisHer()).append(" excitement, but ").append(sender.heShe()).append("'s trembling with eagerness to be of use to ").append(receiver.ownerName()).append(".").toString());
                else
                if(sender.getDignity() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" smiles up at ").append(receiver.ownerName()).append(", warm affection in ").append(sender.hisHer()).append(" eyes.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" laughs, overjoyed that ").append(receiver.ownerName()).append(" is getting ready to use ").append(sender.himHer()).append(".").toString());
            } else
            if(sender.getObedience() > 33)
            {
                if(sender.coerced().booleanValue())
                {
                    if(sender.getConfidence() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" pulls ").append(receiver.ownerName()).append(" the rest of the way atop ").append(sender.himHer()).append(", maintaining control of the situation in ").append(sender.hisHer()).append(" own way.  ").append(sender.HeShe()).append(" looks up at ").append(receiver.ownerName()).append(" expectantly.").toString());
                    else
                    if(sender.getConfidence() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" obediently plays along, positioning ").append(sender.himHer()).append("self however ").append(receiver.ownerName()).append(" directs ").append(sender.himHer()).append(".").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" meekly allows ").append(sender.himHer()).append("self to be pushed down, fidgeting in anticipation of what will happen to ").append(sender.himHer()).append(" next.").toString());
                } else
                if(sender.getConfidence() > 66)
                    w.append(t, (new StringBuilder("Once, ")).append(sender.ownerName()).append(" would have immediately fought ").append(sender.hisHer()).append(" way free.  But now ").append(sender.heShe()).append(" hesitates, memories of ").append(sender.hisHer()).append(" past defeats filling ").append(sender.himHer()).append(" with self-doubt.").toString());
                else
                if(sender.getConfidence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is too startled to fight back, and ").append(sender.heShe()).append(" ends up laying down under ").append(receiver.ownerName()).append(".").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" squeaks in alarm and freezes up, unable to fight back for the few crucial moments it takes ").append(receiver.ownerName()).append(" to get atop ").append(sender.himHer()).append(".").toString());
            } else
            if(sender.getDeviancy() > 66)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to resist, but ").append(sender.hisHer()).append(" well-trained body betrays ").append(sender.himHer()).append(", and ").append(sender.heShe()).append(" can only moan in anticipation of how ").append(sender.hisHer()).append(" hated enemy will treat ").append(sender.himHer()).append(".").toString());
            else
            if(sender.getDeviancy() > 33)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" immediately starts squirming and trying to push ").append(receiver.ownerName()).append(" back, but there's less strength in ").append(sender.hisHer()).append(" arms than usual, and ").append(sender.hisHer()).append(" cheeks are flushing with arousal.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" shouts in anger and immediately starts trying to get free.").toString());
            sender.removeActivity(Project.Supine, null);
        } else
        if(this == Project.Escape)
        {
            if(sender != w.lordBody)
                if(sender.getObedience() > 66)
                {
                    if(sender.getMorality() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" cries out in despair as ").append(sender.heShe()).append(" turns and crawls away from ").append(receiver.ownerName()).append(", already consumed by self-loathing for ").append(sender.hisHer()).append(" failure to properly serve ").append(sender.theDemonLord()).append(".").toString());
                    else
                    if(sender.getMorality() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" abruptly starts struggling to escape ").append(receiver.ownerName()).append(", and it's only after ").append(sender.heShe()).append("'s free that ").append(sender.heShe()).append(" hangs ").append(sender.hisHer()).append(" head in shame for being so disobedient.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" shoves ").append(receiver.ownerName()).append(" and scrambles away, briefly forgetting ").append(sender.hisHer()).append(" devotion in the face of ").append(sender.hisHer()).append(" intense emotions.").toString());
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" cries out in panic and shoves ").append(receiver.ownerName()).append(" away.  Only a moment later does ").append(sender.heShe()).append(" realize what ").append(sender.heShe()).append("'s done, and ").append(sender.heShe()).append(" cowers in fear of punishment.").toString());
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, (new StringBuilder("Apologizing profusely, ")).append(sender.ownerName()).append(" disentangles ").append(sender.himHer()).append("self from ").append(receiver.ownerName()).append(" and takes a few steps backward.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" clearly states that ").append(sender.heShe()).append("'s done with this and moves to free ").append(sender.himHer()).append("self.  The quaver in ").append(sender.hisHer()).append(" voice is the only thing that betrays ").append(sender.hisHer()).append(" worries about the consequences of rejecting ").append(sender.theDemonLord()).append(".").toString());
                } else
                if(sender.getConfidence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" starts to viciously beat ").append(receiver.ownerName()).append(", remaining hostile even after ").append(sender.heShe()).append(" frees ").append(sender.himHer()).append("self.").toString());
                else
                if(sender.getConfidence() > 33)
                {
                    if(sender.imprisoned.booleanValue())
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(", overwhelmed by ").append(sender.hisHer()).append(" hatred for the Demon Lord, starts struggling to get away before ").append(sender.heShe()).append(" knows what ").append(sender.heShe()).append("'s doing.  However, ").append(sender.heShe()).append(" stands tall afterward, regaining ").append(sender.hisHer()).append(" poise as if daring ").append(receiver.ownerName()).append(" to punish ").append(sender.himHer()).append(".").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(", overwhelmed by ").append(sender.hisHer()).append(" hatred for the Demon Lord, starts struggling to get away before ").append(sender.heShe()).append(" knows what ").append(sender.heShe()).append("'s doing.  However, ").append(sender.heShe()).append(" stands tall afterward, regaining ").append(sender.hisHer()).append(" poise as if daring ").append(receiver.ownerName()).append(" to do ").append(receiver.hisHer()).append(" worst.").toString());
                } else
                {
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" flails in panic, then makes an effort to run away.").toString());
                }
        } else
        if(this == Project.StopActing)
        {
            if(sender != w.lordBody)
                if(sender.getObedience() > 66)
                {
                    if(sender.getMorality() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" confesses that ").append(sender.heShe()).append("'s grown too tired to keep servicing ").append(receiver.ownerName()).append(", then eagerly proposes that ").append(receiver.ownerName()).append(" punish ").append(sender.himHer()).append(" for ").append(sender.hisHer()).append(" weakness.").toString());
                    else
                    if(sender.getMorality() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" keeps trying to service ").append(receiver.ownerName()).append(" until ").append(sender.hisHer()).append(" own body starts to grow too exhausted to move.  Eventually, ").append(sender.heShe()).append(" realizes that ").append(sender.heShe()).append("'s too tired to effectively please ").append(receiver.ownerName()).append(" anymore.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is sincere about wanting to service ").append(receiver.ownerName()).append(", but ").append(sender.heShe()).append(" begins getting lazier and lazier with ").append(sender.hisHer()).append(" movements until ").append(sender.heShe()).append("'s not actually making ").append(receiver.ownerName()).append(" feel good at all.").toString());
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.getDeviancy() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" heaves a satisfied sigh, ").append(sender.hisHer()).append(" tremendous lust finally satisfied.  ").append(sender.HeShe()).append(" doesn't seem interested in continuing for now.").toString());
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" gradually starts to grow less and less interested in pleasuring ").append(receiver.ownerName()).append(", and finally ").append(sender.heShe()).append(" stops completely.").toString());
                    else
                    if(sender.imprisoned.booleanValue())
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" starts to get distracted and make excuses for why ").append(sender.heShe()).append(" should be allowed to leave, and ").append(sender.heShe()).append(" stops trying to pleasure ").append(receiver.ownerName()).append(".").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" starts to get distracted by thoughts of other things ").append(sender.heShe()).append(" could be doing right now, and ").append(sender.heShe()).append(" stops trying to pleasure ").append(receiver.ownerName()).append(".").toString());
                } else
                if(sender.getInnocence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" abruptly seems to remember that ").append(receiver.ownerName()).append(" is the enemy, and ").append(sender.heShe()).append(" refuses to continue this any longer.").toString());
                else
                if(sender.getInnocence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" gets angry and refuses to cooperate further.  ").append(sender.HeShe()).append(" turns ").append(sender.hisHer()).append(" head away, refusing to even look at ").append(receiver.ownerName()).append(".").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(", deciding that ").append(sender.heShe()).append("'s cooperated long enough, halts ").append(sender.hisHer()).append(" movements and glares at ").append(receiver.ownerName()).append(" as if daring ").append(receiver.himHer()).append(" to punish ").append(sender.himHer()).append(".").toString());
        } else
        if(this == Project.TieUp)
        {
            if(sender == w.lordBody)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" ties down ").append(receiver.ownerName()).append("'s arms and legs, leaving ").append(receiver.himHer()).append(" helpless.").toString());
        } else
        if(this == Project.BeTied)
        {
            if(sender != w.lordBody)
                if(sender.getObedience() > 66)
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s eyes glaze over with pure bliss as ").append(sender.heShe()).append(" enjoys entrusting ").append(sender.hisHer()).append(" body to ").append(receiver.ownerName()).append(".").toString());
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" doesn't resist in the slightest, happy to let ").append(receiver.ownerName()).append(" enjoy ").append(sender.hisHer()).append(" body however ").append(receiver.heShe()).append(" wishes.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" happily cooperates, holding ").append(sender.hisHer()).append(" limbs as ").append(sender.heShe()).append("'s directed in order to help speed the process.").toString());
                } else
                if(!sender.coerced().booleanValue())
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" briefly panics as ").append(sender.heShe()).append("'s tied up, but ").append(sender.heShe()).append(" soon remembers that ").append(sender.heShe()).append("'s one of the Chosen and begins to draw on ").append(sender.hisHer()).append(" powers to escape.").toString());
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is caught by surprise and unable to avoid getting tangled up in ").append(receiver.ownerName()).append("'s bindings, but ").append(sender.hisHer()).append(" Chosen powers will let ").append(sender.himHer()).append(" escape in a matter of moments.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" seems more curious than angry as ").append(receiver.ownerName()).append(" starts to tie ").append(sender.himHer()).append(" up, but ").append(sender.heShe()).append(" still begins drawing on ").append(sender.hisHer()).append(" Chosen powers so that ").append(sender.heShe()).append("'ll be able to escape on a moment's notice.").toString());
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.getConfidence() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" holds ").append(sender.hisHer()).append(" head high as ").append(sender.heShe()).append("'s tied up, refusing to let ").append(sender.himHer()).append("self show fear.").toString());
                    else
                    if(sender.getConfidence() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" flinches in trepidation, imagining what ").append(receiver.ownerName()).append(" will be doing to ").append(sender.himHer()).append(" next.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" starts to panic, but ").append(sender.heShe()).append("'s too frightened to actively resist.").toString());
                } else
                if(sender.getDeviancy() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to resist, but ").append(sender.hisHer()).append(" lustful body betrays ").append(sender.himHer()).append(", and by the time ").append(sender.heShe()).append("'s stopped daydreaming about what kind of sexual punishment awaits ").append(sender.himHer()).append(", ").append(sender.heShe()).append("'s already been tied up.").toString());
                else
                if(sender.getDeviancy() > 33)
                {
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" puts up a bit of resistance.  However, ").append(sender.heShe()).append("'s weaker than ").append(sender.heShe()).append(" should be, and ").toString());
                    if(sender.parts[PENIS] > 0)
                        w.append(t, (new StringBuilder("the stiffness between ")).append(sender.hisHer()).append(" legs").toString());
                    else
                        w.append(t, (new StringBuilder("the wetness on ")).append(sender.hisHer()).append(" thighs").toString());
                    w.append(t, (new StringBuilder(" shows that a part of ")).append(sender.himHer()).append(" is eager to be punished.").toString());
                } else
                {
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" fights back with all ").append(sender.hisHer()).append(" might, but ").append(sender.heShe()).append(" can't win against ").append(receiver.ownerName()).append(".  Soon, there's nothing ").append(sender.heShe()).append(" can do but rock back and forth while growling at ").append(sender.hisHer()).append(" captor.").toString());
                }
        } else
        if(this == Project.StrokeCock)
        {
            if(sender == w.lordBody)
            {
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" begins to pump ").append(sender.hisHer()).append(" hand up and down ").append(receiver.ownerName()).append("'s cock.").toString());
            } else
            {
                w.append(t, (new StringBuilder(String.valueOf(sender.capitalizedOwnerName()))).append(" ").toString());
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, (new StringBuilder("loses ")).append(sender.himHer()).append("self in the act of running ").append(sender.hisHer()).append(" hand up and down ").append(receiver.ownerName()).append("'s cock, eyes glazed over and drooling with desire.").toString());
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, (new StringBuilder("reaches eagerly for ")).append(receiver.ownerName()).append("'s cock, pumping it up and down with an aggressive firmness that comes just short of being painful.").toString());
                    else
                        w.append(t, (new StringBuilder("abruptly wraps ")).append(sender.hisHer()).append(" fingers around ").append(receiver.ownerName()).append("'s cock and starts stroking it gently, then fondling the tip, then gently tugging on it, using every technique at ").append(sender.hisHer()).append(" disposal.").toString());
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, (new StringBuilder("tugs and jerks ")).append(receiver.ownerName()).append("'s cock, then gradually gets into a rhythm of stroking it as ").append(receiver.heShe()).append(" grows more confident in what ").append(receiver.heShe()).append("'s doing.").toString());
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, (new StringBuilder("brushes ")).append(sender.hisHer()).append(" fingers against ").append(receiver.ownerName()).append("'s cock, trailing them up and down, then growing more firm, stroking it in earnest.").toString());
                    else
                        w.append(t, (new StringBuilder("carefully starts to stroke ")).append(receiver.ownerName()).append("'s cock, staring into ").append(receiver.hisHer()).append(" eyes to gauge ").append(receiver.hisHer()).append(" reaction.").toString());
                } else
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, (new StringBuilder("hesitantly touches ")).append(receiver.ownerName()).append("'s cock, then begins to clumsily stroke it, averting ").append(sender.hisHer()).append(" eyes as ").append(sender.heShe()).append(" does so.  ").toString());
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, (new StringBuilder("begins to stroke ")).append(receiver.ownerName()).append("'s cock with stiff, mechanical movements.  ").toString());
                    else
                        w.append(t, (new StringBuilder("brings ")).append(sender.hisHer()).append(" hand to ").append(receiver.ownerName()).append("'s cock and begins to stroke it with precise, methodical movements.  ").toString());
                    if(sender.getObedience() > 66)
                        w.append(t, (new StringBuilder("It's clear that ")).append(sender.heShe()).append("'s unfamiliar with this sort of thing, but ").append(sender.heShe()).append("'s doing ").append(sender.hisHer()).append(" best for ").append(receiver.ownerName()).append(".").toString());
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.HisHer()))).append(" heart clearly isn't in it.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.HeShe()))).append(" looks sickened by what ").append(sender.heShe()).append("'s doing.").toString());
                }
            }
        } else
        if(this == Project.CockStroked)
        {
            if(sender == w.lordBody)
            {
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s cock pulses under ").append(receiver.ownerName()).append("'s fingers.").toString());
            } else
            {
                w.append(t, sender.capitalizedOwnerName());
                if(sender.getInnocence() > 66)
                {
                    w.append(t, (new StringBuilder(" gasps at the sudden intensity of the stimulation to ")).append(sender.hisHer()).append(" most sensitive part, and ").toString());
                    if(sender.getDeviancy() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.hisHer()))).append(" eyes glaze over as ").append(sender.heShe()).append(" loses the ability to think of anything other than feeling even better.  ").toString());
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.hisHer()))).append(" hips jerk wildly, seemingly caught between jerking away and pushing further into ").append(receiver.ownerName()).append("'s hand.  ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.heShe()))).append(" reflexively tries to jerk ").append(sender.hisHer()).append(" hips away.  ").toString());
                } else
                if(sender.getInnocence() > 33)
                {
                    w.append(t, (new StringBuilder(" feels jolts of pleasure shooting through ")).append(sender.hisHer()).append(" shaft").toString());
                    if(sender.getDeviancy() > 66)
                        w.append(t, (new StringBuilder(", and ")).append(sender.heShe()).append(" moans helplessly, ").append(sender.hisHer()).append(" well-trained body eagerly submitting itself to ").append(receiver.ownerName()).append("'s touch.  ").toString());
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, (new StringBuilder(", and it's a struggle for ")).append(sender.himHer()).append(" to hold onto ").append(sender.hisHer()).append(" reason.  ").toString());
                    else
                        w.append(t, (new StringBuilder(", even as ")).append(sender.heShe()).append(" tries to ignore them.  ").toString());
                } else
                {
                    w.append(t, (new StringBuilder(" knew this was coming, but ")).append(sender.heShe()).append(" still ").toString());
                    if(sender.getDeviancy() > 66)
                        w.append(t, (new StringBuilder("squirms and cries out, unable to maintain any self-control in the face of the pleasure ")).append(sender.heShe()).append("'s come to crave.  ").toString());
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, (new StringBuilder("gasps softly at the waves of pleasure that begin to wash over ")).append(sender.hisHer()).append(" lower body.  ").toString());
                    else
                        w.append(t, "flinches and grimaces, uncomfortable with the intensity of the sensations.  ");
                }
                if(receiver == w.lordBody)
                    if(sender.getDeviancy() > 66)
                    {
                        if(sender.getObedience() > 66)
                            w.append(t, (new StringBuilder(String.valueOf(sender.HisHer()))).append(" body has grown quite sensitive, but it's even more sensitive than usual when ").append(sender.heShe()).append(" knows ").append(sender.heShe()).append("'s being touched by ").append(receiver.ownerName()).append(".").toString());
                        else
                        if(sender.getObedience() > 33)
                            w.append(t, (new StringBuilder("Soon, ")).append(sender.heShe()).append("'s completely consumed in ").append(sender.hisHer()).append(" efforts to hump ").append(receiver.ownerName()).append("'s hand.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(sender.HeShe()))).append(" tries to resist, but ").append(sender.hisHer()).append(" body has grown so sensitive that the lightest touch to ").append(sender.hisHer()).append(" weak spots is enough to subdue ").append(sender.himHer()).append(".").toString());
                    } else
                    if(sender.getDeviancy() > 33)
                    {
                        if(sender.getObedience() > 66)
                            w.append(t, (new StringBuilder("Even more than the physical stimulation itself, ")).append(sender.ownerName()).append(" is ecstatic that ").append(receiver.ownerName()).append(" is pleasuring ").append(sender.himHer()).append(" directly.").toString());
                        else
                        if(sender.getObedience() > 33)
                            w.append(t, (new StringBuilder(String.valueOf(sender.HeShe()))).append("'s content to let ").append(receiver.ownerName()).append(" pleasure ").append(sender.himHer()).append(".").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(sender.HeShe()))).append(" has a hard time remembering that ").append(sender.heShe()).append("'s supposed to hate the Demon Lord.").toString());
                    } else
                    if(sender.getObedience() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.HeShe()))).append(" endures this for ").append(receiver.ownerName()).append("'s sake, hoping that ").append(receiver.ownerName()).append(" will enjoy playing with ").append(sender.hisHer()).append(" body.").toString());
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.HeShe()))).append(" doesn't make eye contact with ").append(receiver.ownerName()).append(", glancing off to the side.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.HeShe()))).append(" glares at ").append(receiver.ownerName()).append(", not appreciating it in the slightest.").toString());
            }
        } else
        if(this == Project.Lubricate)
        {
            if(sender == w.lordBody)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" gathers a generous amount of slippery lubricant on one finger, then uses it to coat ").append(receiver.ownerName()).append("'s anus.").toString());
        } else
        if(this == Project.BeLubricated)
        {
            if(sender != w.lordBody)
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getObedience() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" moans and spreads ").append(sender.hisHer()).append(" legs as wide as ").append(sender.heShe()).append(" can, eager for more than just a finger.").toString());
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" looks nervous, but also eager.  ").append(sender.HeShe()).append(" grins down at ").append(sender.himHer()).append("self, imagining what will be done to ").append(sender.himHer()).append(" next.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to keep acting tough, but ").append(sender.heShe()).append(" breaks down into a stream of pitiful moans and halfhearted protests as the light stimulation is enough to break ").append(sender.hisHer()).append(" concentration.").toString());
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getDignity() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" inhales sharply, but doesn't show any other sign that ").append(sender.heShe()).append("'s feeling anything from this.").toString());
                    else
                    if(sender.getDignity() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" squirms and blushes, clearly a bit ashamed at being excited by the stimulation back there.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" groans and reflexively tries to buck ").append(sender.hisHer()).append(" hips down onto the finger.").toString());
                } else
                if(sender.getInnocence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" squirms in discomfort at the strange feeling in a place ").append(sender.heShe()).append(" doesn't even mentally associate with sex.").toString());
                else
                if(sender.getInnocence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s eyes go wide and ").append(sender.heShe()).append(" tries to look down at what's happening down there.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" shudders at the unpleasant sensation, then tries to relax and accept it.").toString());
        } else
        if(this == Project.VaginalPenetrate)
        {
            if(Project.PushDown.isInProgress(sender, receiver).booleanValue())
            {
                if(sender == w.lordBody)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" puts the tip of ").append(sender.hisHer()).append(" cock against ").append(receiver.ownerName()).append("'s pussy, then thrusts inside.").toString());
                else
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getConfidence() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" eagerly pins ").append(receiver.ownerName()).append(" down and shoves ").append(sender.hisHer()).append(" shaft into ").append(receiver.ownerName()).append("'s pussy, immediately setting a rapid pace of thrusting in and out.  It's a struggle to keep moving steadily with the flashes of ecstasy shooting through ").append(sender.himHer()).append(", but ").append(sender.ownerName()).append(" is determined to keep going.").toString());
                    else
                    if(sender.getConfidence() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" starts to slowly push ").append(sender.hisHer()).append(" cock into ").append(receiver.ownerName()).append(", but the surge of pleasure from having just the tip inside overwhelms ").append(sender.hisHer()).append(" reason.  ").append(sender.HeShe()).append(" jerks ").append(sender.hisHer()).append(" hips forward, burying ").append(sender.himHer()).append("self inside ").append(receiver.ownerName()).append("'s pussy, then pulls halfway out, then pushes back in, hips jerking wildly as intense pleasure surges through ").append(sender.himHer()).append(" with every movement.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s timid nature seems to vanish entirely, and ").append(sender.heShe()).append(" starts to ram ").append(sender.hisHer()).append(" hips forward with wild abandon, hammering in and out of ").append(receiver.ownerName()).append("'s pussy.").toString());
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" pushes ").append(sender.hisHer()).append(" cock into ").append(receiver.ownerName()).append("'s pussy, and the moment ").append(sender.heShe()).append(" feels ").append(receiver.hisHer()).append(" folds squeezing down on ").append(sender.himHer()).append(", ").append(sender.heShe()).append(" loses the ability to think of anything but the pleasure.  Moaning softly, ").append(sender.heShe()).append(" starts thrusting in and out, acting on pure instinct.").toString());
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" starts to fuck ").append(receiver.ownerName()).append("'s pussy, but the pleasure of ").append(receiver.hisHer()).append(" folds squeezing down on ").append(sender.himHer()).append(" cock gives ").append(sender.ownerName()).append(" pause.  ").append(sender.HeShe()).append(" gasps, taking a moment to catch ").append(sender.hisHer()).append(" breath, then continues more slowly, enjoying the warm wetness around ").append(sender.hisHer()).append(" shaft.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" gradually pushes ").append(sender.hisHer()).append(" cock into ").append(receiver.ownerName()).append("'s pussy, inch-by-inch, then withdraws it just as carefully.  ").append(sender.HeShe()).append(" moves slowly, feeling out which spots feel better for ").append(sender.himHer()).append(" and which seem to get more of a reaction from ").append(receiver.ownerName()).append(", and only then starts to move more quickly, getting into a rhythm.").toString());
                } else
                if(sender.getObedience() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" eagerly thrusts ").append(sender.hisHer()).append(" cock inside ").append(receiver.ownerName()).append("'s pussy, ecstatic to be joined with ").append(receiver.himHer()).append(".  ").append(sender.HeShe()).append(" moves unselfishly, trying to hit all of ").append(receiver.ownerName()).append("'s sensitive parts without any regard for ").append(sender.hisHer()).append(" own pleasure.").toString());
                else
                if(sender.getObedience() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" presses the tip of ").append(sender.hisHer()).append(" cock against ").append(receiver.ownerName()).append("'s pussy, hesitates for a moment, and then finally pushes it inside.  ").append(sender.HeShe()).append(" gasps and shudders at the pleasure, far beyond what ").append(sender.heShe()).append(" was prepared for, and then cautiously starts to move ").append(sender.hisHer()).append(" hips forward and back.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s angry demeanor fades for just a moment as ").append(sender.heShe()).append(" thrusts into ").append(receiver.ownerName()).append(" and feels the intense pleasure of ").append(receiver.hisHer()).append(" folds squeezing ").append(sender.himHer()).append(".  But then ").append(sender.heShe()).append(" recovers and starts to move violently in and out, as if stabbing ").append(receiver.ownerName()).append(" with a weapon.").toString());
            } else
            if(sender == w.lordBody)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s cock pushes all the way into ").append(receiver.ownerName()).append("'s depths.").toString());
            else
            if(sender.getDeviancy() > 66)
            {
                if(sender.getInnocence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s whole body goes slack except for ").append(sender.hisHer()).append(" hips desperately trying to thrust deeper into ").append(receiver.himHer()).append(".  ").append(sender.HisHer()).append(" eyes roll and ").append(sender.hisHer()).append(" tongue hangs out.").toString());
                else
                if(sender.getInnocence() > 33)
                    w.append(t, (new StringBuilder("The feeling of ")).append(receiver.ownerName()).append(" sliding down to the base of ").append(sender.hisHer()).append(" shaft is enough to make ").append(sender.ownerName()).append(" cry out and lose ").append(sender.hisHer()).append(" sense of reason.  ").append(sender.HeShe()).append(" desperately clings to ").append(receiver.ownerName()).append(", hips jerking wildly up and down as ").append(sender.heShe()).append(" tries to thrust even deeper into ").append(receiver.himHer()).append(".").toString());
                else
                    w.append(t, (new StringBuilder("At first, ")).append(sender.ownerName()).append(" tries to reciprocate with ").append(sender.hisHer()).append(" own thrusts.  But it soon becomes clear that ").append(sender.heShe()).append("'s completely at ").append(receiver.ownerName()).append("'s mercy, and all ").append(sender.heShe()).append(" can do is moan and jerk ").append(sender.hisHer()).append(" hips as ").append(receiver.ownerName()).append(" slides up and down ").append(sender.hisHer()).append(" shaft.").toString());
            } else
            if(sender.getDeviancy() > 33)
            {
                if(sender.getDignity() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to hide just how good it feels, but when ").append(receiver.ownerName()).append(" clenches around ").append(sender.himHer()).append(", ").append(sender.heShe()).append(" gasps and reflexively bucks ").append(sender.hisHer()).append(" hips.  After that, ").append(sender.heShe()).append(" has a harder and harder time suppressing ").append(sender.hisHer()).append(" moans.").toString());
                else
                if(sender.getDignity() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" lays back and loses ").append(sender.himHer()).append("self in the feeling of ").append(receiver.ownerName()).append(" sliding up and down ").append(sender.hisHer()).append(" length, overwhelmed by the intensity of the sensations.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" gasps with pleasure, reflexively embracing ").append(receiver.ownerName()).append(" and thrusting ").append(sender.hisHer()).append(" own hips in time with ").append(receiver.ownerName()).append("'s movements.").toString());
            } else
            if(sender.getObedience() > 66)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" winces and struggles not to cum right away, wanting to ensure that ").append(sender.heShe()).append(" stays hard for ").append(receiver.ownerName()).append(" as long as possible.").toString());
            else
            if(sender.getObedience() > 33)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to relax and enjoy it, but even as ").append(receiver.ownerName()).append(" slides up and down ").append(sender.hisHer()).append(" shaft, ").append(sender.ownerName()).append(" can't quite shake off the fear that this pleasure is just meant to make ").append(sender.himHer()).append(" let ").append(sender.hisHer()).append(" guard down.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" struggles to keep glaring at ").append(receiver.ownerName()).append(", wincing slightly every time ").append(receiver.ownerName()).append(" squeezes the base of ").append(sender.hisHer()).append(" shaft and breaks ").append(sender.hisHer()).append(" concentration.").toString());
        } else
        if(this == Project.PenetratedVaginally)
        {
            if(Project.PushDown.isInProgress(sender, receiver).booleanValue())
            {
                if(sender == w.lordBody)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" mounts ").append(receiver.ownerName()).append(" and lowers ").append(sender.himHer()).append("self until ").append(sender.hisHer()).append(" pussy envelops ").append(receiver.ownerName()).append("'s cock.").toString());
                else
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getObedience() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" eagerly impales ").append(sender.hisHer()).append(" pussy on ").append(receiver.ownerName()).append("'s upright cock, sliding ").append(sender.himHer()).append("self up and down with manic energy while grinning at ").append(receiver.himHer()).append(".").toString());
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" quickly lowers ").append(sender.himHer()).append("self onto ").append(receiver.ownerName()).append("'s cock, then immediately begins to bounce on ").append(receiver.hisHer()).append(" lap, searching for the precise angle where it pokes just the right spot inside ").append(sender.hisHer()).append(" pussy.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to resist ").append(sender.hisHer()).append(" urges, but this close to ").append(receiver.ownerName()).append("'s cock, ").append(sender.heShe()).append(" can't stop ").append(sender.himHer()).append("self from grinding against it.  Then, before ").append(sender.heShe()).append(" realizes it, ").append(sender.heShe()).append("'s taken it into ").append(sender.hisHer()).append(" pussy.  The surge of pleasure destroys the last of ").append(sender.hisHer()).append(" reason, and ").append(sender.heShe()).append(" starts moving in earnest.").toString());
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getConfidence() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" pins ").append(receiver.ownerName()).append(" down and takes ").append(receiver.ownerName()).append("'s cock into ").append(sender.hisHer()).append(" pussy.  ").append(sender.HeShe()).append(" grimaces slightly at the sudden insertion, but wastes no time in bucking ").append(sender.hisHer()).append(" hips with savage force, causing the discomfort to melt away into pleasure.").toString());
                    else
                    if(sender.getConfidence() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" straddles ").append(receiver.ownerName()).append(", touching ").append(sender.hisHer()).append(" pussy against the tip of ").append(receiver.ownerName()).append("'s cock, then slowly lowering ").append(sender.himHer()).append("self downward onto it, bit by bit.  ").append(sender.HeShe()).append(" gasps and twitches as it bottoms out inside ").append(sender.himHer()).append(".").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is nervous about lowering ").append(sender.himHer()).append("self onto ").append(receiver.ownerName()).append("'s cock, but ").append(sender.heShe()).append(" gasps with pleasure as it goes inside ").append(sender.hisHer()).append(" pussy and immediately hits a sensitive spot.  ").append(sender.HeShe()).append(" moans and begins moving ").append(sender.himHer()).append("self up and down with genuine enthusiasm.").toString());
                } else
                if(sender.getDignity() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is blushing bright red at having to be the one to lower ").append(sender.himHer()).append("self onto ").append(receiver.ownerName()).append("'s cock, but ").append(sender.heShe()).append(" pretends that it doesn't bother ").append(sender.himHer()).append(".  ").append(sender.HeShe()).append(" just flinches as it spreads ").append(sender.hisHer()).append(" pussy open, then stifles a moan when it goes all the way inside.").toString());
                else
                if(sender.getDignity() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" delays as long as possible before taking ").append(receiver.ownerName()).append("'s cock into ").append(sender.hisHer()).append(" pussy.  ").append(sender.HeShe()).append(" tries various angles, slides ").append(sender.himHer()).append("self against it several times, and only then does ").append(sender.heShe()).append(" finally lower ").append(sender.himHer()).append("self onto it.  When ").append(sender.heShe()).append(" does, ").append(sender.heShe()).append(" lets out a little moan, uncomfortable with just how intense the pleasure is.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" doesn't waste any time before slamming ").append(sender.hisHer()).append(" hips down onto ").append(receiver.ownerName()).append("'s cock.  However, ").append(sender.heShe()).append(" groans with discomfort at the sudden insertion, and ").append(sender.heShe()).append(" needs a few moments to recover before ").append(sender.heShe()).append(" can start moving.").toString());
                if(sender.isVVirg().booleanValue())
                    if(sender.getObedience() > 66)
                        w.append(t, (new StringBuilder("  ")).append(sender.HeShe()).append(" embraces the pain of ").append(sender.hisHer()).append(" first vaginal penetration, ecstatic to have received it from ").append(receiver.ownerName()).append(".").toString());
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, (new StringBuilder("  ")).append(sender.HeShe()).append(" looks down at the trickle of blood on ").append(sender.hisHer()).append(" thigh with an expression of resignation.").toString());
                    else
                        w.append(t, (new StringBuilder("  ")).append(sender.HeShe()).append(" lifts ").append(sender.himHer()).append("self up enough to look down and see the blood on ").append(receiver.ownerName()).append("'s cock, then grits ").append(sender.hisHer()).append(" teeth.").toString());
            } else
            {
                if(sender == w.lordBody)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s pussy tightens around ").append(receiver.ownerName()).append("'s cock as though trying to milk it dry.").toString());
                else
                if(sender.getObedience() > 66)
                {
                    if(sender.getDeviancy() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" goes wild with joyful lust, bucking ").append(sender.hisHer()).append(" hips wildly and enjoying every moment of being taken by ").append(receiver.ownerName()).append(".").toString());
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to help ").append(receiver.ownerName()).append(" feel good as well, moving ").append(sender.hisHer()).append(" hips and urging ").append(receiver.himHer()).append(" deeper inside, but soon the pleasure overwhelms ").append(sender.himHer()).append(" and ").append(sender.heShe()).append(" can't think of anything but wanting to cum.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" bites ").append(sender.hisHer()).append(" lip in concentration, trying to buck ").append(sender.hisHer()).append(" hips in turn and squeeze down with ").append(sender.hisHer()).append(" pussy in order to make it feel as good as possible for ").append(receiver.ownerName()).append(".").toString());
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.getConfidence() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" doesn't look entirely happy to be on the bottom, but the pleasure of each thrust hitting ").append(sender.hisHer()).append(" deepest places soon makes ").append(sender.himHer()).append(" forget all about that and start crying out in pleasure.").toString());
                    else
                    if(sender.getConfidence() > 33)
                        w.append(t, (new StringBuilder("At first, ")).append(sender.ownerName()).append(" tolerates it with an expression of resignation.  However, as the pleasure builds, ").append(sender.heShe()).append(" starts to gasp with passion and then actively move ").append(sender.hisHer()).append(" hips.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is too nervous to move much, worried about doing something to displease ").append(receiver.ownerName()).append(".  Whimpering moans of pleasure begin to leak out of ").append(sender.hisHer()).append(" throat.").toString());
                } else
                if(sender.getInnocence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to kick and scream, but ").append(sender.hisHer()).append(" movements only drive ").append(receiver.ownerName()).append(" deeper inside ").append(sender.himHer()).append(", and ").append(sender.hisHer()).append(" voice takes on a passionate quality.").toString());
                else
                if(sender.getInnocence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" attempts to deny ").append(receiver.ownerName()).append(" the satisfaction of seeing ").append(sender.hisHer()).append(" reactions, but the longer the fucking continues, the more difficult it is.  Soon ").append(sender.heShe()).append("'s reduced to covering ").append(sender.hisHer()).append(" face and biting ").append(sender.hisHer()).append(" lip to stifle ").append(sender.hisHer()).append(" moans.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" glares up at ").append(receiver.ownerName()).append(", and ").append(sender.hisHer()).append("  hatred comes through clearly even as ").append(sender.heShe()).append(" winces and groans at the stimulation to ").append(sender.hisHer()).append(" most sensitive inner places.").toString());
                if(sender.isVVirg().booleanValue())
                    if(sender.getObedience() > 66)
                        w.append(t, (new StringBuilder("  ")).append(sender.HeShe()).append(" smiles broadly up at ").append(receiver.ownerName()).append(", tears of joy leaking from ").append(sender.hisHer()).append(" eyes at being able to give ").append(sender.hisHer()).append(" first time to the one ").append(sender.heShe()).append(" loves.").toString());
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, (new StringBuilder("  When ")).append(sender.heShe()).append(" sees the blood on ").append(receiver.ownerName()).append("'s cock, ").append(sender.heShe()).append(" flinches away, as if trying to ignore it.").toString());
                    else
                        w.append(t, (new StringBuilder("  ")).append(sender.HisHer()).append(" anger drowns out the pain of having ").append(sender.hisHer()).append(" hymen torn.").toString());
            }
        } else
        if(this == Project.AnalPenetrate)
        {
            if(Project.PushDown.isInProgress(sender, receiver).booleanValue())
            {
                if(sender == w.lordBody)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" puts the tip of ").append(sender.hisHer()).append(" cock against ").append(receiver.ownerName()).append("'s anus, then thrusts inside.").toString());
                else
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getConfidence() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" eagerly pins ").append(receiver.ownerName()).append(" down and shoves ").append(sender.hisHer()).append(" shaft all the way up ").append(receiver.ownerName()).append("'s ass, immediately setting a rapid pace of thrusting in and out.  It's a struggle to keep moving steadily with the flashes of pleasure shooting through ").append(sender.himHer()).append(", but ").append(sender.ownerName()).append(" is determined to keep going.").toString());
                    else
                    if(sender.getConfidence() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" starts to slowly push ").append(sender.hisHer()).append(" cock up ").append(receiver.ownerName()).append("'s ass, but the surge of pleasure from the sphincter squeezing ").append(sender.hisHer()).append(" tip overwhelms ").append(sender.hisHer()).append(" reason.  ").append(sender.HeShe()).append(" jerks ").append(sender.hisHer()).append(" hips forward, burying ").append(sender.himHer()).append("self all the way inside, then pulls halfway out, then pushes back in, hips jerking wildly as intense ecstasy surges through ").append(sender.himHer()).append(" with every movement.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s timid nature seems to vanish entirely, and ").append(sender.heShe()).append(" starts to ram ").append(sender.hisHer()).append(" hips forward with wild abandon, hammering in and out of ").append(receiver.ownerName()).append("'s asshole.").toString());
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" pushes ").append(sender.hisHer()).append(" cock up ").append(receiver.ownerName()).append("'s ass, and the moment ").append(sender.heShe()).append(" feels ").append(receiver.hisHer()).append(" rear entrance squeezing down on ").append(sender.himHer()).append(", ").append(sender.heShe()).append(" loses the ability to think of anything but the pleasure.  Moaning softly, ").append(sender.heShe()).append(" starts thrusting in and out, acting on pure instinct.").toString());
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" starts to fuck ").append(receiver.ownerName()).append("'s asshole, but the pleasure of ").append(receiver.hisHer()).append(" sphincter squeezing down on ").append(sender.himHer()).append(" cock gives ").append(sender.ownerName()).append(" pause.  ").append(sender.HeShe()).append(" gasps, taking a moment to catch ").append(sender.hisHer()).append(" breath, then continues more slowly, enjoying the incredible tightness around ").append(sender.hisHer()).append(" shaft.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" gradually pushes ").append(sender.hisHer()).append(" cock up ").append(receiver.ownerName()).append("'s ass, inch-by-inch, then withdraws it just as carefully.  ").append(sender.HeShe()).append(" moves slowly, feeling out which spots feel better for ").append(sender.himHer()).append(" and which seem to get more of a reaction from ").append(receiver.ownerName()).append(", and only then starts to move more quickly, getting into a rhythm.").toString());
                } else
                if(sender.getObedience() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" eagerly thrusts ").append(sender.hisHer()).append(" cock up ").append(receiver.ownerName()).append("'s ass, ecstatic to be joined with ").append(receiver.himHer()).append(".  ").append(sender.HeShe()).append(" moves unselfishly, trying to hit all of ").append(receiver.ownerName()).append("'s sensitive parts without any regard for ").append(sender.hisHer()).append(" own pleasure.").toString());
                else
                if(sender.getObedience() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" presses the tip of ").append(sender.hisHer()).append(" cock against ").append(receiver.ownerName()).append("'s anus, hesitates for a moment, and then finally pushes it inside.  ").append(sender.HeShe()).append(" gasps and shudders at the pleasure, far beyond what ").append(sender.heShe()).append(" was prepared for, and then cautiously starts to move ").append(sender.hisHer()).append(" hips forward and back.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s angry demeanor fades for just a moment as ").append(sender.heShe()).append(" thrusts into ").append(receiver.ownerName()).append(" and feels the intense pleasure of ").append(receiver.hisHer()).append(" asshole squeezing ").append(sender.himHer()).append(".  But then ").append(sender.heShe()).append(" recovers and starts to move violently in and out, as if stabbing ").append(receiver.ownerName()).append(" with a weapon.").toString());
            } else
            if(sender == w.lordBody)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s cock pushes all the way into ").append(receiver.ownerName()).append("'s bowels.").toString());
            else
            if(sender.getDeviancy() > 66)
            {
                if(sender.getInnocence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s whole body goes slack except for ").append(sender.hisHer()).append(" hips desperately trying to thrust deeper into ").append(receiver.himHer()).append(".  ").append(sender.HisHer()).append(" eyes roll and ").append(sender.hisHer()).append(" tongue hangs out.").toString());
                else
                if(sender.getInnocence() > 33)
                    w.append(t, (new StringBuilder("The feeling of ")).append(receiver.ownerName()).append(" sliding down to the base of ").append(sender.hisHer()).append(" shaft is enough to make ").append(sender.ownerName()).append(" cry out and lose ").append(sender.hisHer()).append(" sense of reason.  ").append(sender.HeShe()).append(" desperately clings to ").append(receiver.ownerName()).append(", hips jerking wildly up and down as ").append(sender.heShe()).append(" tries to thrust even deeper into ").append(receiver.himHer()).append(".").toString());
                else
                    w.append(t, (new StringBuilder("At first, ")).append(sender.ownerName()).append(" tries to reciprocate with ").append(sender.hisHer()).append(" own thrusts.  But it soon becomes clear that ").append(sender.heShe()).append("'s completely at ").append(receiver.ownerName()).append("'s mercy, and all ").append(sender.heShe()).append(" can do is moan and jerk ").append(sender.hisHer()).append(" hips as ").append(receiver.ownerName()).append(" slides up and down ").append(sender.hisHer()).append(" shaft.").toString());
            } else
            if(sender.getDeviancy() > 33)
            {
                if(sender.getDignity() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to hide just how good it feels, but when ").append(receiver.ownerName()).append(" clenches around ").append(sender.himHer()).append(", ").append(sender.heShe()).append(" gasps and reflexively bucks ").append(sender.hisHer()).append(" hips.  After that, ").append(sender.heShe()).append(" has a harder and harder time suppressing ").append(sender.hisHer()).append(" moans.").toString());
                else
                if(sender.getDignity() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" lays back and loses ").append(sender.himHer()).append("self in the feeling of ").append(receiver.ownerName()).append(" sliding up and down ").append(sender.hisHer()).append(" length, overwhelmed by the intensity of the sensations.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" gasps with pleasure, reflexively embracing ").append(receiver.ownerName()).append(" and thrusting ").append(sender.hisHer()).append(" own hips in time with ").append(receiver.ownerName()).append("'s movements.").toString());
            } else
            if(sender.getObedience() > 66)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" winces and struggles not to cum right away, wanting to ensure that ").append(sender.heShe()).append(" stays hard for ").append(receiver.ownerName()).append(" as long as possible.").toString());
            else
            if(sender.getObedience() > 33)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to relax and enjoy it, but even as ").append(receiver.ownerName()).append(" slides up and down ").append(sender.hisHer()).append(" shaft, ").append(sender.ownerName()).append(" can't quite shake off the fear that this pleasure is just meant to make ").append(sender.himHer()).append(" let ").append(sender.hisHer()).append(" guard down.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" struggles to keep glaring at ").append(receiver.ownerName()).append(", wincing slightly every time ").append(receiver.ownerName()).append("'s anus squeezes the base of ").append(sender.hisHer()).append(" shaft and breaks ").append(sender.hisHer()).append(" concentration.").toString());
        } else
        if(this == Project.PenetratedAnally)
        {
            if(Project.PushDown.isInProgress(sender, receiver).booleanValue())
            {
                if(sender == w.lordBody)
                {
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" mounts ").append(receiver.ownerName()).append(" and lowers ").append(sender.himHer()).append("self until ").append(sender.hisHer()).append(" anus envelops ").append(receiver.ownerName()).append("'s cock.").toString());
                } else
                {
                    if(!Project.BeLubricated.isInProgress(sender, null).booleanValue())
                    {
                        if(sender.getInnocence() > 66)
                            w.append(t, (new StringBuilder("Full of eager lust, ")).append(sender.ownerName()).append(" almost forgets to lubricate ").append(sender.himHer()).append("self back there before continuing.").toString());
                        else
                        if(sender.getInnocence() > 33)
                            w.append(t, (new StringBuilder("As ")).append(sender.heShe()).append(" lays atop ").append(receiver.ownerName()).append(", ").append(sender.ownerName()).append(" reaches back with one finger covered in lubricant to prepare for what comes next.").toString());
                        else
                            w.append(t, (new StringBuilder("Before continuing, ")).append(sender.ownerName()).append(" carefully applies some slippery lubricant to ").append(sender.hisHer()).append(" rear entrance.").toString());
                        w.append(t, "\n\n");
                        sender.addActivity(Project.BeLubricated, null);
                    }
                    if(sender.getDeviancy() > 66)
                    {
                        if(sender.getObedience() > 66)
                            w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" eagerly impales ").append(sender.hisHer()).append(" asshole on ").append(receiver.ownerName()).append("'s upright cock, sliding ").append(sender.himHer()).append("self up and down with manic energy while grinning at ").append(receiver.himHer()).append(".").toString());
                        else
                        if(sender.getObedience() > 33)
                            w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" quickly lowers ").append(sender.himHer()).append("self onto ").append(receiver.ownerName()).append("'s cock, then immediately begins to bounce on ").append(receiver.hisHer()).append(" lap, searching for the precise angle where it pokes just the right spot inside ").append(sender.hisHer()).append(" bowels.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to resist ").append(sender.hisHer()).append(" urges, but this close to ").append(receiver.ownerName()).append("'s cock, ").append(sender.heShe()).append(" can't stop ").append(sender.himHer()).append("self from grinding against it.  Then, before ").append(sender.heShe()).append(" realizes it, ").append(sender.heShe()).append("'s taken it up ").append(sender.hisHer()).append(" ass.  The surge of pleasure destroys the last of ").append(sender.hisHer()).append(" reason, and ").append(sender.heShe()).append(" starts moving in earnest.").toString());
                    } else
                    if(sender.getDeviancy() > 33)
                    {
                        if(sender.getConfidence() > 66)
                            w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" pins ").append(receiver.ownerName()).append(" down and takes ").append(receiver.ownerName()).append("'s cock up ").append(sender.hisHer()).append(" ass.  ").append(sender.HeShe()).append(" grimaces slightly at the sensation of being spread wide open, but wastes no time in bucking ").append(sender.hisHer()).append(" hips with savage force, causing the discomfort to melt away into pleasure.").toString());
                        else
                        if(sender.getConfidence() > 33)
                            w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" straddles ").append(receiver.ownerName()).append(", touching ").append(sender.hisHer()).append(" anus against the tip of ").append(receiver.ownerName()).append("'s cock, then slowly lowering ").append(sender.himHer()).append("self downward onto it, bit by bit.  ").append(sender.HeShe()).append(" gasps and twitches as it bottoms out inside ").append(sender.himHer()).append(".").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is nervous about lowering ").append(sender.himHer()).append("self onto ").append(receiver.ownerName()).append("'s cock, but ").append(sender.heShe()).append(" gasps with pleasure as it goes up ").append(sender.hisHer()).append(" ass and immediately hits a sensitive spot.  ").append(sender.HeShe()).append(" moans and begins moving ").append(sender.himHer()).append("self up and down with genuine enthusiasm.").toString());
                    } else
                    if(sender.getDignity() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is blushing bright red at having to be the one to lower ").append(sender.himHer()).append("self onto ").append(receiver.ownerName()).append("'s cock, but ").append(sender.heShe()).append(" pretends that it doesn't bother ").append(sender.himHer()).append(".  ").append(sender.HeShe()).append(" just flinches as it spreads ").append(sender.hisHer()).append(" anus open, then stifles a moan when it goes all the way inside.").toString());
                    else
                    if(sender.getDignity() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" delays as long as possible before taking ").append(receiver.ownerName()).append("'s cock up ").append(sender.hisHer()).append(" ass.  ").append(sender.HeShe()).append(" tries various angles, grinds the tip against ").append(sender.hisHer()).append(" anus, and only then does ").append(sender.heShe()).append(" finally lower ").append(sender.himHer()).append("self onto it.  When ").append(sender.heShe()).append(" does, ").append(sender.heShe()).append(" lets out a little moan, uncomfortable with just how intense the pleasure is.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" doesn't waste any time before slamming ").append(sender.hisHer()).append(" ass down onto ").append(receiver.ownerName()).append("'s cock.  However, ").append(sender.heShe()).append(" groans with discomfort at the sudden insertion, and ").append(sender.heShe()).append(" needs a few moments to recover before ").append(sender.heShe()).append(" can start moving.").toString());
                }
                if(sender.isVVirg().booleanValue())
                    if(sender.getObedience() > 66)
                        w.append(t, (new StringBuilder("  ")).append(sender.HeShe()).append(" embraces the pain of ").append(sender.hisHer()).append(" first vaginal penetration, ecstatic to have received it from ").append(receiver.ownerName()).append(".").toString());
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, (new StringBuilder("  ")).append(sender.HeShe()).append(" looks down at the trickle of blood on ").append(sender.hisHer()).append(" thigh with an expression of resignation.").toString());
                    else
                        w.append(t, (new StringBuilder("  ")).append(sender.HeShe()).append(" lifts ").append(sender.himHer()).append("self up enough to look down and see the blood on ").append(receiver.ownerName()).append("'s cock, then grits ").append(sender.hisHer()).append(" teeth.").toString());
            } else
            if(Project.BeLubricated.isInProgress(sender, null).booleanValue() || sender == w.lordBody)
            {
                if(sender == w.lordBody)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s asshole tightens around ").append(receiver.ownerName()).append("'s cock as though trying to milk it dry.").toString());
                else
                if(sender.getObedience() > 66)
                {
                    if(sender.getDeviancy() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" goes wild with joyful lust, bucking ").append(sender.hisHer()).append(" hips wildly and enjoying every moment of being taken by ").append(receiver.ownerName()).append(".").toString());
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to help ").append(receiver.ownerName()).append(" feel good as well, moving ").append(sender.hisHer()).append(" hips and urging ").append(receiver.himHer()).append(" deeper inside, but soon the pleasure overwhelms ").append(sender.himHer()).append(" and ").append(sender.heShe()).append(" can't think of anything but wanting to cum.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" bites ").append(sender.hisHer()).append(" lip in concentration, trying to buck ").append(sender.hisHer()).append(" hips in turn and squeeze down with ").append(sender.hisHer()).append(" anus in order to make it feel as good as possible for ").append(receiver.ownerName()).append(".").toString());
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.getConfidence() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" doesn't look entirely happy to be taking it up the ass, but the pleasure of each thrust hitting ").append(sender.hisHer()).append(" deepest places soon makes ").append(sender.himHer()).append(" forget all about that and start crying out in pleasure.").toString());
                    else
                    if(sender.getConfidence() > 33)
                        w.append(t, (new StringBuilder("At first, ")).append(sender.ownerName()).append(" tolerates it with an expression of resignation.  However, as ").append(sender.hisHer()).append(" sensitive places start to get stimulated through ").append(sender.hisHer()).append(" anal walls, ").append(sender.heShe()).append(" starts to gasp with passion and then actively move ").append(sender.hisHer()).append(" hips.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is too nervous to move much, worried about doing something to displease ").append(receiver.ownerName()).append(".  Whimpering moans of pleasure begin to leak out of ").append(sender.hisHer()).append(" throat.").toString());
                } else
                if(sender.getInnocence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to kick and scream, but ").append(sender.hisHer()).append(" movements only drive ").append(receiver.ownerName()).append(" deeper inside ").append(sender.himHer()).append(", and ").append(sender.hisHer()).append(" voice takes on a passionate quality.").toString());
                else
                if(sender.getInnocence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" attempts to deny ").append(receiver.ownerName()).append(" the satisfaction of seeing ").append(sender.hisHer()).append(" reactions, but the longer the fucking continues, the more difficult it is.  Soon ").append(sender.heShe()).append("'s reduced to covering ").append(sender.hisHer()).append(" face and biting ").append(sender.hisHer()).append(" lip to stifle ").append(sender.hisHer()).append(" moans.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" glares up at ").append(receiver.ownerName()).append(", and ").append(sender.hisHer()).append(" hatred comes through clearly even as ").append(sender.heShe()).append(" winces and groans at the stimulation through ").append(sender.hisHer()).append(" anal walls.").toString());
                if(sender.getGender() == Forsaken.Gender.MALE && sender.isVVirg().booleanValue())
                    if(sender.getObedience() > 66)
                        w.append(t, (new StringBuilder("  ")).append(sender.HeShe()).append(" smiles broadly up at ").append(receiver.ownerName()).append(", tears of joy leaking from ").append(sender.hisHer()).append(" eyes at receiving ").append(sender.hisHer()).append(" first anal penetration from the one ").append(sender.heShe()).append(" loves.").toString());
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, (new StringBuilder("  To ")).append(sender.hisHer()).append(" horror, the pleasure keeps building further and further, until ").append(sender.heShe()).append(" can't deny that ").append(sender.heShe()).append(" enjoys being fucked like a girl.").toString());
                    else
                        w.append(t, (new StringBuilder("  ")).append(sender.HisHer()).append(" anger drowns out the pain of having ").append(sender.hisHer()).append(" inexperienced asshole stretched so wide for the first time.").toString());
            } else
            if(sender.forsakenOwner == null && sender.chosenOwner != null && sender.chosenOwner.aVirg.booleanValue())
            {
                if(sender.getConfidence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s superhuman durability prevents ").append(receiver.ownerName()).append(" from getting more than the tip inside, but even that much is enough to make ").append(sender.himHer()).append(" grit ").append(sender.hisHer()).append(" teeth in anger, surprise, and pain.").toString());
                else
                if(sender.getConfidence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s whole body lurches, more from surprise than from any conscious attempt at defiance, but ").append(sender.heShe()).append("'s strong enough to stop ").append(receiver.ownerName()).append(" from getting more than the tip inside ").append(sender.himHer()).append(".  As ").append(sender.heShe()).append(" realizes what just almost happened, ").append(sender.heShe()).append(" glares at ").append(receiver.ownerName()).append(".").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" screams in pain and starts to panic even before ").append(receiver.ownerName()).append(" can get more than the tip inside ").append(sender.himHer()).append(".").toString());
            } else
            {
                if(sender.getConfidence() > 66)
                {
                    if(sender.getObedience() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" grits ").append(sender.hisHer()).append(" teeth into a forced smile, encouraging ").append(receiver.ownerName()).append(" to keep fucking ").append(sender.himHer()).append(" even though ").append(sender.heShe()).append("'s in agony from having ").append(sender.hisHer()).append(" ass forced open without any lubrication.").toString());
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, (new StringBuilder("The agony of being anally penetrated without any lubrication is almost enough to paralyze ")).append(sender.ownerName()).append("'s thoughts, but ").append(sender.heShe()).append(" has just enough willpower to beg for mercy, offering to do anything in hopes of ending the torture.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s determined resolve lasts only a moment before cracking, and ").append(sender.heShe()).append(" starts begging for mercy in a shrill voice, apologizing for ").append(sender.hisHer()).append(" defiance.").toString());
                } else
                if(sender.getConfidence() > 33)
                {
                    if(sender.getDignity() > 66)
                        w.append(t, (new StringBuilder("At first, ")).append(sender.ownerName()).append(" tries to pretend that ").append(sender.heShe()).append(" can take it.  But the painful friction against ").append(sender.hisHer()).append(" unlubricated anal walls is too much for ").append(sender.himHer()).append(", and soon ").append(sender.heShe()).append("'s crying and begging without restraint.").toString());
                    else
                    if(sender.getDignity() > 33)
                        w.append(t, (new StringBuilder("Without any lubrication, the insertion is more painful than anything, and ")).append(sender.ownerName()).append(" is ashamed with ").append(sender.himHer()).append("self as ").append(sender.heShe()).append(" starts uncontrollably sobbing.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" kicks wildly and screams at the top of ").append(sender.hisHer()).append(" lungs, heedless of the fact that ").append(sender.heShe()).append("'s only scraping ").append(sender.himHer()).append("self even more against the cruel invading shaft.").toString());
                } else
                if(sender.getDeviancy() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s eyes shoot wide open, a scream caught in ").append(sender.hisHer()).append(" throat.  But when it finally comes out, there's a lewd quality to it, a sign of ").append(sender.hisHer()).append(" insatiable masochism.").toString());
                else
                if(sender.getDeviancy() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" whimpers, trying to curl up and protect ").append(sender.himHer()).append("self, but ").append(sender.heShe()).append(" can't hide from the painful friction of the shaft invading ").append(sender.hisHer()).append(" unprepared hole.  ").append(sender.HisHer()).append(" moans aren't entirely from pain, as ").append(sender.heShe()).append(" feels a hint of shameful pleasure as well.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" screams, crying and sobbing at the explosion of agony ").append(sender.heShe()).append(" feels from the unlubricated insertion.  The pain far outweighs the pleasure.").toString());
                if(sender.getGender() == Forsaken.Gender.MALE && sender.isVVirg().booleanValue())
                    if(sender.getObedience() > 66)
                        w.append(t, (new StringBuilder("  Afterward, ")).append(sender.heShe()).append("'ll be full of joyful pride that ").append(receiver.ownerName()).append(" saw fit to break ").append(sender.himHer()).append(" in with such a memorable fucking, but for now, ").append(sender.heShe()).append("'s in too much pain to think about it in those terms.").toString());
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, (new StringBuilder("  ")).append(sender.HeShe()).append(" realizes that it was a mistake to ever take pride in managing to protect ").append(sender.hisHer()).append(" anal virginity.").toString());
                    else
                        w.append(t, (new StringBuilder("  A part of ")).append(sender.himHer()).append(" is horrified that ").append(sender.heShe()).append(" was broken so easily, and ").append(sender.hisHer()).append(" confidence in ").append(sender.hisHer()).append(" own masculinity will never recover.").toString());
            }
        } else
        if(this == Project.StripOther)
        {
            if(sender == w.lordBody)
            {
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" begins to strip off ").append(receiver.ownerName()).append("'s clothes.").toString());
                if(w.sceneLocation == Location.STAGE)
                    w.append(t, "  The crowd goes wild, eager to see more skin.");
            }
        } else
        if(this == Project.Stripped)
        {
            if(sender != w.lordBody)
                if(w.sceneLocation == Location.STAGE)
                {
                    if(sender.getObedience() > 66)
                    {
                        if(sender.getDisgrace() > 66)
                            w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" doesn't even seem to notice all the spectators.  ").append(sender.HeShe()).append(" only has eyes for ").append(receiver.ownerName()).append(".").toString());
                        else
                        if(sender.getDisgrace() > 33)
                            w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" smiles, eager to help ").append(receiver.ownerName()).append(" put on a show for everyone.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" looks afraid, but also eager, wanting to show everyone that ").append(sender.heShe()).append(" really is nothing more than ").append(receiver.ownerName()).append("'s toy.").toString());
                    } else
                    if(sender.getObedience() > 33)
                    {
                        if(sender.forsakenOwner != null && sender.forsakenOwner.timesExposed < 0x493e0)
                        {
                            if(sender.getDignity() > 66)
                                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is clearly terrified of the prospect of being forced to show everything, but ").append(sender.heShe()).append("'s come too far to back out now.").toString());
                            else
                            if(sender.getDignity() > 33)
                                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" looks resigned to the fact that ").append(sender.heShe()).append("'ll shortly be showing off more than ").append(sender.heShe()).append(" ever has before.").toString());
                            else
                                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" just ignores all the shouts and jeers.").toString());
                        } else
                        if(sender.getDignity() > 66)
                            w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to make it look like it was ").append(sender.hisHer()).append(" idea to undress, moving to help ").append(receiver.ownerName()).append(" strip ").append(sender.himHer()).append(".").toString());
                        else
                        if(sender.getDignity() > 33)
                            w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to make the best of it, smiling at the crowd.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" just ignores all the shouts and jeers.").toString());
                    } else
                    if(sender.getDeviancy() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s eyes dart back and forth among all the people watching, and despite ").append(sender.hisHer()).append(" anger, ").append(sender.heShe()).append("'s panting wth barely-restrained desire.").toString());
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" huffs angrily, but the heat rising to ").append(sender.hisHer()).append(" cheeks is partly from arousal.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" clutches at ").append(sender.hisHer()).append(" clothes and glares at ").append(receiver.ownerName()).append(".").toString());
                } else
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, (new StringBuilder("The act of being stripped immediately causes ")).append(sender.ownerName()).append("'s mind to wander off into erotic fantasy, and ").append(sender.heShe()).append(" starts to drool with a silly expression on ").append(sender.hisHer()).append(" face.").toString());
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, (new StringBuilder("As ")).append(sender.hisHer()).append(" clothes are taken off, ").append(sender.ownerName()).append(" gets more and more turned on, and soon ").append(sender.heShe()).append(" can't think of anything but sex.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" starts to breathe more quickly, imagining what might come next.").toString());
                } else
                if(!sender.coerced().booleanValue())
                {
                    if(sender.getDignity() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" lets out an outraged squeal and clutches at ").append(sender.hisHer()).append(" clothes as best ").append(sender.heShe()).append(" can.").toString());
                    else
                    if(sender.getDignity() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is caught by surprise, but quickly recovers, attempting to squirm away.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to step backward, frowning at ").append(receiver.ownerName()).append(" in confusion.").toString());
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getConfidence() > 66)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" proudly shows off, refusing to be ashamed of ").append(sender.hisHer()).append(" body.").toString());
                    else
                    if(sender.getConfidence() > 33)
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" blushes, anticipating what's to come.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" glances off to the side and reflexively tries to cover ").append(sender.himHer()).append("self.").toString());
                } else
                if(sender.getObedience() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" smiles, happy that ").append(receiver.ownerName()).append(" wants to see ").append(sender.hisHer()).append(" body.").toString());
                else
                if(sender.getObedience() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" sighs, accepting that this was inevitable.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" clutches at ").append(sender.hisHer()).append(" clothes and glares at ").append(receiver.ownerName()).append(".").toString());
        } else
        if(this == Project.LickCock)
        {
            if(sender == w.lordBody)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" lowers ").append(sender.hisHer()).append(" face to ").append(receiver.ownerName()).append("'s cock and begins to run ").append(sender.hisHer()).append(" tongue up and down its length.").toString());
            else
            if(sender.getObedience() > 66)
            {
                if(sender.getInnocence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" buries ").append(sender.hisHer()).append(" face in ").append(receiver.ownerName()).append("'s crotch, kissing and licking ").append(receiver.ownerName()).append("'s cock all over and moaning in satisfaction as though ").append(sender.heShe()).append(" were enjoying a delicious piece of candy.").toString());
                else
                if(sender.getInnocence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" rubs ").append(sender.hisHer()).append(" face against ").append(receiver.ownerName()).append("'s cock, sighing with happiness at being able to service ").append(receiver.himHer()).append(".  ").append(sender.HeShe()).append(" gives it an open-mouthed kiss, slathering it in saliva while gazing dreamily into ").append(receiver.ownerName()).append("'s eyes.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" brings ").append(sender.hisHer()).append(" face to ").append(receiver.ownerName()).append("'s cock, and suddenly ").append(sender.hisHer()).append(" tongue is dancing from the tip to the base, finding all of ").append(receiver.ownerName()).append("'s most sensitive spots and then moving on before the stimulation can become too monotonous.").toString());
            } else
            if(sender.getObedience() > 33)
            {
                if(sender.getConfidence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" begins to aggressively lick every inch of ").append(receiver.ownerName()).append("'s cock, occasionally sucking it and swirling ").append(sender.hisHer()).append(" tongue around the tip, completely focused on making ").append(receiver.ownerName()).append(" cum.").toString());
                else
                if(sender.getConfidence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" moves ").append(sender.hisHer()).append(" face downward until ").append(receiver.ownerName()).append("'s cock is resting against it.  Then, ").append(sender.heShe()).append(" starts licking ").append(sender.hisHer()).append(" way from the base up to the tip, suckling it gently with ").append(sender.hisHer()).append(" eyes closed.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" lowers ").append(sender.hisHer()).append(" head to ").append(receiver.ownerName()).append("'s cock and begins licking it without a word of complaint.  ").append(sender.HeShe()).append(" constantly looks up to make sure that ").append(receiver.ownerName()).append(" is satisfied.").toString());
            } else
            if(sender.getDeviancy() > 66)
                w.append(t, (new StringBuilder("At first, ")).append(sender.ownerName()).append(" is murmuring angry protests as ").append(sender.heShe()).append(" brings ").append(sender.hisHer()).append(" face to ").append(receiver.ownerName()).append("'s cock, but ").append(sender.hisHer()).append(" words trail off as ").append(sender.heShe()).append(" begins compulsively licking the shaft.").toString());
            else
            if(sender.getDeviancy() > 33)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" huffs with irritation before starting to lick ").append(receiver.ownerName()).append("'s cock.  ").append(sender.HisHer()).append(" movements are skillful, but ").append(sender.heShe()).append(" doesn't stop glaring up at ").append(receiver.ownerName()).append(".").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" bares ").append(sender.hisHer()).append(" teeth at ").append(receiver.ownerName()).append("'s cock, clearly thinking about biting it, but ").append(sender.heShe()).append(" eventually sticks ").append(sender.hisHer()).append(" tongue halfway out and begins reluctantly licking it instead.").toString());
        } else
        if(this == Project.CockLicked)
        {
            if(sender == w.lordBody)
                w.append(t, (new StringBuilder("The warm wetness of ")).append(receiver.ownerName()).append("'s tongue sends pleasure shooting through ").append(sender.ownerName()).append("'s lower body.").toString());
            else
            if(sender.getDeviancy() > 66)
            {
                if(sender.getDignity() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to hide ").append(sender.hisHer()).append(" reaction to the pleasure, but within a matter of moments ").append(sender.heShe()).append("'s squealing and bucking ").append(sender.hisHer()).append(" hips, ").append(sender.hisHer()).append(" face bright red with shame at being subdued so easily.").toString());
                else
                if(sender.getDignity() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s eyes roll and ").append(sender.hisHer()).append(" tongue hangs out, ").append(sender.hisHer()).append(" well-trained penis utterly helpless against ").append(receiver.ownerName()).append("'s mouth.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" gasps and moans, wildly bucking ").append(sender.hisHer()).append(" hips against ").append(receiver.ownerName()).append("'s face, as if trying to fuck ").append(receiver.hisHer()).append(" mouth.").toString());
            } else
            if(sender.getDeviancy() > 33)
            {
                if(sender.getObedience() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to shake off the pleasure and focus on serving ").append(receiver.ownerName()).append(", but ").append(sender.hisHer()).append(" happiness at being rewarded by ").append(receiver.ownerName()).append(" drives all other thoughts out of ").append(sender.hisHer()).append(" mind.").toString());
                else
                if(sender.getObedience() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" sighs and relaxes ").append(sender.hisHer()).append(" body, closing ").append(sender.hisHer()).append(" eyes and losing ").append(sender.himHer()).append("self in the pleasure.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" attempts to push ").append(receiver.ownerName()).append(" away, but the sudden pleasure shooting through ").append(sender.hisHer()).append(" lower body makes ").append(sender.himHer()).append(" momentarily weaker.").toString());
            } else
            if(sender.getConfidence() > 66)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" frowns down at ").append(receiver.ownerName()).append(" and tries to mentally shut out the pleasure in order to stay focused.").toString());
            else
            if(sender.getConfidence() > 33)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" leans away from ").append(receiver.ownerName()).append(", feeling uncertain about being pleasured like this.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" stammers incoherently, clearly unsure about how to deal with this.").toString());
        } else
        if(this == Project.LickPussy)
        {
            if(sender == w.lordBody)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" lowers ").append(sender.hisHer()).append(" face to ").append(receiver.ownerName()).append("'s lower lips and begins to run ").append(sender.hisHer()).append(" tongue up and down between them.").toString());
            else
            if(sender.getObedience() > 66)
            {
                if(sender.getInnocence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" buries ").append(sender.hisHer()).append(" face in ").append(receiver.ownerName()).append("'s crotch, kissing and licking ").append(receiver.ownerName()).append("'s pussy all over and moaning in satisfaction as though ").append(sender.heShe()).append(" were slurping up a bowl of something delicious").toString());
                else
                if(sender.getInnocence() > 33)
                {
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" rubs ").append(sender.hisHer()).append(" face against ").append(receiver.ownerName()).append("'s crotch, sighing with happiness at being able to service ").append(receiver.himHer()).append(".  ").append(sender.HeShe()).append(" starts licking it, ").append(sender.hisHer()).append(" tongue slightly pushing into ").append(receiver.ownerName()).append("'s pussy and lapping up the juices while ").append(sender.heShe()).append(" gazes dreamily into ").append(receiver.ownerName()).append("'s eyes.").toString());
                } else
                {
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" brings ").append(sender.hisHer()).append(" face to ").append(receiver.ownerName()).append("'s pussy and begins to lick it, ").append(sender.hisHer()).append(" tongue darting deeper and deeper each time.   Then, ").append(sender.heShe()).append(" begins to expertly move ").append(sender.hisHer()).append(" tongue, curling it upward and exploring around, stimulating ").toString());
                    if(receiver.parts[PENIS] > 0)
                        w.append(t, (new StringBuilder("the base of ")).append(receiver.ownerName()).append("'s cock from the inside.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(receiver.ownerName()))).append("'s clitoris from the inside.").toString());
                }
            } else
            if(sender.getObedience() > 33)
            {
                if(sender.getConfidence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" begins to aggressively push ").append(sender.hisHer()).append(" tongue between ").append(receiver.ownerName()).append("'s lower lips, swirling ").append(sender.hisHer()).append(" it around inside, completely focused on making ").append(receiver.ownerName()).append(" cum.").toString());
                else
                if(sender.getConfidence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" moves ").append(sender.hisHer()).append(" face downward until ").append(sender.heShe()).append("'s kissing ").append(receiver.ownerName()).append("'s pussy.  Then, ").append(sender.heShe()).append(" starts licking ").append(sender.hisHer()).append(" way from the bottom to the top, over and over again, closing ").append(sender.hisHer()).append(" eyes in concentration.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" lowers ").append(sender.hisHer()).append(" head to ").append(receiver.ownerName()).append("'s pussy and begins licking it without a word of complaint.  ").append(sender.HeShe()).append(" constantly looks up to make sure that ").append(receiver.ownerName()).append(" is satisfied.").toString());
            } else
            if(sender.getDeviancy() > 66)
                w.append(t, (new StringBuilder("At first, ")).append(sender.ownerName()).append(" is murmuring angry protests as ").append(sender.heShe()).append(" brings ").append(sender.hisHer()).append(" face to ").append(receiver.ownerName()).append("'s pussy, but ").append(sender.hisHer()).append(" words trail off as ").append(sender.heShe()).append(" begins compulsively licking it, entranced by the taste of the juices flowing out.").toString());
            else
            if(sender.getDeviancy() > 33)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" huffs with irritation before starting to lick ").append(receiver.ownerName()).append("'s pussy.  ").append(sender.HisHer()).append(" movements are skillful, but ").append(sender.heShe()).append(" doesn't stop glaring up at ").append(receiver.ownerName()).append(".").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" turns ").append(sender.hisHer()).append(" nose up at ").append(receiver.ownerName()).append("'s pussy, almost turning away and refusing to service ").append(sender.himHer()).append(".  But in the end, ").append(sender.heShe()).append(" gets to work, licking it reluctantly and looking annoyed at the juices and saliva that end up dribbling down ").append(sender.hisHer()).append(" chin.").toString());
        } else
        if(this == Project.PussyLicked)
        {
            if(sender == w.lordBody)
                w.append(t, (new StringBuilder(String.valueOf(receiver.ownerName()))).append("'s smooth tongue sends jolts of pleasure shooting all the way to ").append(sender.ownerName()).append("'s womb.").toString());
            else
            if(sender.getDeviancy() > 66)
            {
                if(sender.getDignity() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to hide ").append(sender.hisHer()).append(" reaction to the pleasure, but within a matter of moments ").append(sender.heShe()).append("'s squealing and bucking ").append(sender.hisHer()).append(" hips, ").append(sender.hisHer()).append(" face bright red with shame at being subdued so easily.").toString());
                else
                if(sender.getDignity() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s eyes roll and ").append(sender.hisHer()).append(" tongue hangs out, ").append(sender.hisHer()).append(" well-trained hole utterly helpless against ").append(receiver.ownerName()).append("'s tongue.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" gasps and moans, wildly bucking ").append(sender.hisHer()).append(" hips against ").append(receiver.ownerName()).append("'s face, as if trying to impale ").append(sender.himHer()).append("self on ").append(receiver.ownerName()).append("'s tongue.").toString());
            } else
            if(sender.getDeviancy() > 33)
            {
                if(sender.getObedience() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to shake off the pleasure and focus on serving ").append(receiver.ownerName()).append(", but ").append(sender.hisHer()).append(" happiness at being rewarded by ").append(receiver.ownerName()).append(" drives all other thoughts out of ").append(sender.hisHer()).append(" mind.").toString());
                else
                if(sender.getObedience() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" sighs and relaxes ").append(sender.hisHer()).append(" body, closing ").append(sender.hisHer()).append(" eyes and losing ").append(sender.himHer()).append("self in the pleasure.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" attempts to push ").append(receiver.ownerName()).append(" away, but the sudden pleasure shooting through ").append(sender.hisHer()).append(" lower body makes ").append(sender.himHer()).append(" momentarily weaker.").toString());
            } else
            if(sender.getConfidence() > 66)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" frowns down at ").append(receiver.ownerName()).append(" and tries to mentally shut out the pleasure in order to stay focused.").toString());
            else
            if(sender.getConfidence() > 33)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" leans away from ").append(receiver.ownerName()).append(", feeling uncertain about being pleasured like this.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" stammers incoherently, clearly unsure about how to deal with this.").toString());
        } else
        if(this == Project.Supine)
        {
            if(sender == w.actingBody)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" lays down on ").append(sender.hisHer()).append(" back.").toString());
            else
            if(Project.BeTied.isInProgress(sender, null).booleanValue())
                w.append(t, (new StringBuilder(String.valueOf(w.actingBody.OwnerName()))).append(" places the helpless ").append(sender.ownerName()).append(" on the floor, rolling ").append(sender.himHer()).append(" over onto ").append(sender.hisHer()).append(" back.").toString());
            else
            if(sender.getObedience() > 66)
                w.append(t, (new StringBuilder("At ")).append(w.actingBody.ownerName()).append("'s command, ").append(sender.ownerName()).append(" obediently lays down on ").append(sender.hisHer()).append(" back.").toString());
            else
            if(sender.coerced().booleanValue())
                w.append(t, (new StringBuilder("When ")).append(w.actingBody.ownerName()).append(" orders ").append(sender.himHer()).append(" to lay down, ").append(sender.ownerName()).append(" hesitates only a moment before ").append(sender.heShe()).append(" complies.").toString());
            else
            if(sender.getObedience() > 33)
                w.append(t, (new StringBuilder(String.valueOf(w.actingBody.OwnerName()))).append(" forces ").append(sender.ownerName()).append(" down onto ").append(sender.hisHer()).append(" back, and ").append(sender.ownerName()).append(" is momentarily too intimidated to resist.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(w.actingBody.OwnerName()))).append(" trips ").append(sender.ownerName()).append(" up, and after a brief scuffle, ").append(sender.ownerName()).append(" lands on the ground.").toString());
        } else
        if(this == Project.PullUp)
        {
            if(Project.BeTied.isInProgress(receiver, null).booleanValue())
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" pulls the helpless ").append(receiver.ownerName()).append(" up onto ").append(receiver.hisHer()).append(" feet so that ").append(receiver.heShe()).append(" can stand on ").append(receiver.hisHer()).append(" own.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" helps ").append(receiver.ownerName()).append(" up onto ").append(receiver.hisHer()).append(" feet.").toString());
            receiver.removeActivity(Project.Supine, null);
        } else
        if(this == Project.StepOnCock)
        {
            if(sender == w.lordBody)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" steps on ").append(receiver.ownerName()).append("'s penis, then begins moving ").append(sender.hisHer()).append(" foot up and down to stimualte it.").toString());
            else
            if(sender.getObedience() > 66)
            {
                if(sender.getInnocence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" giggles at ").append(receiver.ownerName()).append("'s penis, poking it with a toe, but ").append(sender.heShe()).append(" quickly grows more serious about pleasuring it, rubbing ").append(sender.hisHer()).append(" foot up and down its length with such singleminded intensity that the stimulation causes almost as much pain as pleasure.").toString());
                else
                if(sender.getInnocence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" feels strange about doing something so disrespectful to ").append(receiver.ownerName()).append(", but at the same time, ").append(sender.heShe()).append(" wants to give ").append(receiver.himHer()).append(" pleasure, and so ").append(sender.heShe()).append(" starts using ").append(sender.hisHer()).append(" foot to stimulate ").append(receiver.ownerName()).append("'s penis.  ").append(sender.HeShe()).append("'s as gentle as ").append(sender.heShe()).append(" can be, but ").append(sender.hisHer()).append(" stroking movements up and down the shaft still cause some pain as well as pleasure.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" brings ").append(sender.hisHer()).append(" foot to ").append(receiver.ownerName()).append("'s penis, then uses it to service ").append(receiver.ownerName()).append(" as skillfully as most other people could with a hand.  ").append(sender.HeShe()).append(" strokes it up and down, uses a toe to toy with the tip, puts enough pressure on it to be borderline painful, then caresses it gently to prepare it for more stimulation.").toString());
            } else
            if(sender.getObedience() > 33)
            {
                if(sender.getConfidence() > 66)
                    w.append(t, (new StringBuilder("When ")).append(sender.heShe()).append(" realizes that ").append(receiver.ownerName()).append(" won't stop ").append(sender.himHer()).append(", ").append(sender.ownerName()).append(" gleefully starts stepping on ").append(receiver.ownerName()).append("'s penis, putting a painful amount of force into it as ").append(sender.heShe()).append(" enjoys the feeling of being in control again.").toString());
                else
                if(sender.getConfidence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" puts ").append(sender.hisHer()).append(" foot on ").append(receiver.ownerName()).append("'s crotch, then starts grinding it against ").append(receiver.ownerName()).append("'s penis.  ").append(sender.HeShe()).append(" keeps a wary eye on ").append(receiver.ownerName()).append(" the whole while, prepared for ").append(receiver.himHer()).append(" to try to turn the tables.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s fear of being punished causes ").append(sender.himHer()).append(" to hesitate before lowering ").append(sender.hisHer()).append(" foot to ").append(receiver.ownerName()).append("'s penis, and at first ").append(sender.hisHer()).append(" movements are too soft to do much of anything, but ").append(sender.heShe()).append(" soon starts to enjoy ").append(sender.himHer()).append("self, smiling down as ").append(sender.heShe()).append(" gives ").append(receiver.ownerName()).append(" a footjob.").toString());
            } else
            if(sender.getDeviancy() > 66)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to stomp on ").append(receiver.ownerName()).append("'s penis, but ").append(sender.hisHer()).append(" movements begin to change without ").append(sender.himHer()).append(" realizing it, and ").append(sender.heShe()).append(" subconsciously falls into the familiar rhythm of giving a footjob.").toString());
            else
            if(sender.getDeviancy() > 33)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" steps on ").append(receiver.ownerName()).append("'s penis, smirking with satisfaction at the way ").append(receiver.hisHer()).append(" body reflexively jerks.  ").append(sender.HeShe()).append(" starts putting more weight on ").append(receiver.ownerName()).append(", causing both pleasure and pain.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" stomps hard on ").append(receiver.ownerName()).append("'s penis.  ").append(sender.HeShe()).append(" looks disgusted that ").append(receiver.ownerName()).append(" seems to enjoy the stimulation, but ").append(sender.heShe()).append(" doesn't stop.").toString());
        } else
        if(this == Project.CockSteppedOn)
        {
            if(sender == w.lordBody)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" twitches and strains under ").append(receiver.ownerName()).append("'s foot.").toString());
            else
            if(sender.getDeviancy() > 66)
            {
                if(sender.getInnocence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" squeals and squirms wildly, but ").append(sender.hisHer()).append(" penis remains firmly pinned under ").append(receiver.ownerName()).append("'s foot.").toString());
                else
                if(sender.getInnocence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" moans and thrashes, ").append(sender.hisHer()).append(" body spasming with pleasure even as ").append(sender.heShe()).append(" instinctively tries to avoid the pain.  ").append(sender.HeShe()).append("'s helpless under ").append(receiver.ownerName()).append("'s foot.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" cries out incoherently with pleasure, but ").append(sender.heShe()).append(" still has the presence of mind to try to grind ").append(sender.himHer()).append("self against ").append(receiver.ownerName()).append("'s foot in turn, seeking even greater stimulation.").toString());
            } else
            if(sender.getDeviancy() > 33)
            {
                if(sender.getDignity() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" blushes deeply, ashamed at responding to such a humiliating form of stimulation, but that only turns ").append(sender.himHer()).append(" on even more.").toString());
                else
                if(sender.getDignity() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" gasps and shudders, the combined pleasure and pain breaking down ").append(sender.hisHer()).append(" resistance and leaving ").append(sender.himHer()).append(" helpless.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" cries out and bucks ").append(sender.hisHer()).append(" hips into ").append(receiver.ownerName()).append("'s foot.").toString());
            } else
            if(sender.getObedience() > 66)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" hides ").append(sender.hisHer()).append(" winces of pain as best ").append(sender.heShe()).append(" can, not wanting to seem ungrateful for ").append(receiver.ownerName()).append("'s service.").toString());
            else
            if(sender.coerced().booleanValue())
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" sighs and averts ").append(sender.hisHer()).append(" eyes, trying to ignore the slightly painful stimulation.").toString());
            else
            if(sender.getObedience() > 33)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" freezes up, the pain to ").append(sender.hisHer()).append(" most sensitive part bringing traumatic flashbacks.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" shouts in anger and pain, trying to get up, but ").append(receiver.ownerName()).append("'s foot on ").append(sender.hisHer()).append(" penis holds ").append(sender.himHer()).append(" down.").toString());
        } else
        if(this == Project.StepOnClit)
        {
            if(sender == w.lordBody)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" steps on ").append(receiver.ownerName()).append("'s crotch, then begins moving ").append(sender.hisHer()).append(" foot forward and back to stimualte ").append(receiver.ownerName()).append("'s clitoris.").toString());
            else
            if(sender.getObedience() > 66)
            {
                if(sender.getInnocence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" giggles playfully as ").append(sender.heShe()).append(" pokes ").append(receiver.ownerName()).append("'s clit with ").append(sender.hisHer()).append(" toe, but ").append(sender.heShe()).append(" quickly grows more serious about pleasuring it, rubbing ").append(sender.hisHer()).append(" foot forward and back with such singleminded intensity that the stimulation causes almost as much pain as pleasure.").toString());
                else
                if(sender.getInnocence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" feels strange about doing something so disrespectful to ").append(receiver.ownerName()).append(", but at the same time, ").append(sender.heShe()).append(" wants to give ").append(receiver.himHer()).append(" pleasure, and so ").append(sender.heShe()).append(" starts using ").append(sender.hisHer()).append(" foot to stimulate ").append(receiver.ownerName()).append("'s clit.  ").append(sender.HeShe()).append("'s as gentle as ").append(sender.heShe()).append(" can be, but the firm pressure ").append(sender.heShe()).append(" places on ").append(receiver.ownerName()).append("'s most sensitive part still causes some pain as well as pleasure.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" brings ").append(sender.hisHer()).append(" foot to ").append(receiver.ownerName()).append("'s clit, then uses it to service ").append(receiver.ownerName()).append(" as skillfully as most other people could with their fingers.  ").append(sender.HeShe()).append(" rubs forward and back, uses a toe to rub the surrounding region, puts enough pressure on it to stimulate the parts beneath the skin, then caresses it gently to prepare it for more stimulation.").toString());
            } else
            if(sender.getObedience() > 33)
            {
                if(sender.getConfidence() > 66)
                    w.append(t, (new StringBuilder("When ")).append(sender.heShe()).append(" realizes that ").append(receiver.ownerName()).append(" won't stop ").append(sender.himHer()).append(", ").append(sender.ownerName()).append(" gleefully starts stepping on ").append(receiver.ownerName()).append("'s crotch,  putting a painful amount of force into it as ").append(sender.heShe()).append(" enjoys the feeling of being in control again.").toString());
                else
                if(sender.getConfidence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" puts ").append(sender.hisHer()).append(" foot on ").append(receiver.ownerName()).append("'s crotch, then starts grinding it against ").append(receiver.ownerName()).append("'s clit.  ").append(sender.HeShe()).append(" keeps a wary eye on ").append(receiver.ownerName()).append(" the whole while, prepared for ").append(receiver.himHer()).append(" to try to turn the tables.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append("'s fear of being punished causes ").append(sender.himHer()).append(" to hesitate before lowering ").append(sender.hisHer()).append(" foot to ").append(receiver.ownerName()).append("'s crotch, and at first ").append(sender.hisHer()).append(" movements are too soft to do much of anything, but ").append(sender.heShe()).append(" soon starts to enjoy ").append(sender.himHer()).append("self, smiling down as ").append(sender.heShe()).append(" gives ").append(receiver.ownerName()).append(" a footjob.").toString());
            } else
            if(sender.getDeviancy() > 66)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to stomp on ").append(receiver.ownerName()).append("'s crotch, but ").append(sender.hisHer()).append(" movements begin to change without ").append(sender.himHer()).append(" realizing it, and ").append(sender.heShe()).append(" subconsciously falls into the familiar rhythm of stimulating ").append(sender.hisHer()).append(" partner's clitoris.").toString());
            else
            if(sender.getDeviancy() > 33)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" steps on ").append(receiver.ownerName()).append("'s crotch, smirking with satisfaction at the way ").append(receiver.hisHer()).append(" body reflexively jerks.  ").append(sender.HeShe()).append(" starts putting more weight directly on ").append(receiver.ownerName()).append(" clit, causing both pleasure and pain.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" stomps hard on ").append(receiver.ownerName()).append("'s crotch.  ").append(sender.HeShe()).append(" looks disgusted that ").append(receiver.ownerName()).append(" seems to enjoy the stimulation to ").append(receiver.hisHer()).append(" clit, but ").append(sender.heShe()).append(" doesn't stop.").toString());
        } else
        if(this == Project.ClitSteppedOn)
        {
            if(sender == w.lordBody)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" twitches and strains under ").append(receiver.ownerName()).append("'s foot.").toString());
            else
            if(sender.getDeviancy() > 66)
            {
                if(sender.getInnocence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" squeals and squirms wildly, but ").append(sender.hisHer()).append(" crotch and sensitive clit remain firmly pinned under ").append(receiver.ownerName()).append("'s foot.").toString());
                else
                if(sender.getInnocence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" moans and thrashes, ").append(sender.hisHer()).append(" body spasming with pleasure even as ").append(sender.heShe()).append(" instinctively tries to avoid the pain.  ").append(sender.HeShe()).append("'s helpless under ").append(receiver.ownerName()).append("'s foot.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" cries out incoherently with pleasure, but ").append(sender.heShe()).append(" still has the presence of mind to try to grind ").append(sender.himHer()).append("self against ").append(receiver.ownerName()).append("'s foot in turn, seeking even greater stimulation.").toString());
            } else
            if(sender.getDeviancy() > 33)
            {
                if(sender.getDignity() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" blushes deeply, ashamed at responding to such a humiliating form of stimulation, but that only turns ").append(sender.himHer()).append(" on even more.").toString());
                else
                if(sender.getDignity() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" gasps and shudders, the combined pleasure and pain breaking down ").append(sender.hisHer()).append(" resistance and leaving ").append(sender.himHer()).append(" helpless.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" cries out and bucks ").append(sender.hisHer()).append(" hips into ").append(receiver.ownerName()).append("'s foot.").toString());
            } else
            if(sender.getObedience() > 66)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" hides ").append(sender.hisHer()).append(" winces of pain as best ").append(sender.heShe()).append(" can, not wanting to seem ungrateful for ").append(receiver.ownerName()).append("'s service.").toString());
            else
            if(sender.coerced().booleanValue())
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" sighs and averts ").append(sender.hisHer()).append(" eyes, trying to ignore the slightly painful stimulation.").toString());
            else
            if(sender.getObedience() > 33)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" freezes up, the pain between ").append(sender.hisHer()).append(" legs bringing traumatic flashbacks.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" shouts in anger and pain, trying to get up, but ").append(receiver.ownerName()).append("'s foot between ").append(sender.hisHer()).append(" legs holds ").append(sender.himHer()).append(" down.").toString());
        } else
        if(this == Project.DirtyTalk)
            if(sender.getObedience() > 66)
            {
                if(sender.getMorality() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" gently encourages ").append(receiver.ownerName()).append(" to enjoy ").append(receiver.himHer()).append("self, wanting to give ").append(receiver.himHer()).append(" as much pleasure as possible.").toString());
                else
                if(sender.getMorality() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" can't contain ").append(sender.hisHer()).append(" joy over being able to pleasure ").append(receiver.ownerName()).append(", making cheerful conversation even as ").append(receiver.ownerName()).append(" continues to enjoy ").append(sender.hisHer()).append(" body.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is smug about the fact that ").append(sender.heShe()).append("'s the one ").append(receiver.ownerName()).append(" came to for pleasure, and ").append(sender.heShe()).append(" uses the opportunity to try to convince ").append(receiver.ownerName()).append(" to pay less attention to the others vying for").append(receiver.hisHer()).append(" affection.").toString());
            } else
            if(sender.getObedience() > 33)
            {
                if(sender.getConfidence() > 66)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" tries to take control of the situation and dominate ").append(receiver.ownerName()).append(" using some slightly demeaning dirty talk.").toString());
                else
                if(sender.getConfidence() > 33)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is mostly enjoying this, and ").append(sender.heShe()).append("'s feeling comfortable enough with the situation to verbally tease ").append(receiver.ownerName()).append(".").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" feels a thrill at being in control of ").append(receiver.ownerName()).append("'s pleasure, and ").append(sender.heShe()).append(" lets it get to ").append(sender.hisHer()).append(" head, making comments that ").append(sender.heShe()).append(" otherwise wouldn't dare say out loud.").toString());
            } else
            if(sender.getDeviancy() > 66)
            {
                w.append(t, (new StringBuilder("Even though ")).append(sender.heShe()).append("'s pleasuring an enemy, ").append(sender.ownerName()).append(" finds ").append(sender.himHer()).append("self gleefully making lewd, abusive comments in anticipation of making ").append(receiver.ownerName()).append(" cum").toString());
                if(receiver.orgasms > 0)
                    w.append(t, "again.");
                else
                    w.append(t, ".");
            } else
            if(sender.getDeviancy() > 33)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is getting turned on by the situation, and as ").append(sender.heShe()).append(" verbally demeans and degrades ").append(receiver.ownerName()).append(", the act of doing so turns ").append(sender.himHer()).append(" on even more.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is disgusted by how much ").append(receiver.ownerName()).append(" is enjoying this, and ").append(sender.heShe()).append(" tells ").append(receiver.himHer()).append(" so in great detail.").toString());
        if(counterpart != null)
        {
            Boolean found = Boolean.valueOf(false);
            for(int i = 0; i < receiver.inProgress.length; i++)
                if(receiver.inProgress[i] == counterpart && receiver.targets[i] == sender)
                    found = Boolean.valueOf(true);

            if(!found.booleanValue())
            {
                w.append(t, "\n\n");
                Body actualTarget = sender;
                if(!counterpart.targeted.booleanValue())
                    actualTarget = null;
                counterpart.startActivity(t, w, receiver, actualTarget);
            }
        }
        for(int i = 0; i < sender.inProgress.length; i++)
        {
            for(int j = 0; j < sender.inProgress[i].enders.length; j++)
            {
                if(sender.inProgress[i].enders[j] != this)
                    continue;
                sender.removeActivity(sender.inProgress[i], null);
                i = -1;
                break;
            }

        }

    }

    public int weight(WorldState w, Body sender, Body receiver)
    {
        int result = 0;
        if(this == Project.TweakClit)
        {
            result -= sender.getFEARLevel() * 5;
            result -= sender.getDISGLevel() * 10;
            result -= sender.getSHAMLevel() * 3;
            result -= sender.getHATELevel() * 15;
            result += sender.getPLEALevel() * 10;
            result += (sender.getDeviancy() - 30) / 2;
            result += (sender.getObedience() - 50) / 2;
            result -= (sender.getInnocence() - 50) / 3;
        } else
        if(this == Project.ClitTweaked)
        {
            result -= sender.getDISGLevel() * 10;
            result -= sender.getSHAMLevel() * 5;
            result -= sender.getHATELevel() * 10;
            result += sender.getPLEALevel() * 25;
            result += (sender.getDeviancy() - 20) / 2;
        } else
        if(this == Project.SpreadLegs)
        {
            result -= sender.getFEARLevel() * 5;
            result -= sender.getDISGLevel() * 10;
            result -= sender.getPAINLevel() * 5;
            result -= sender.getSHAMLevel() * 15;
            result -= sender.getHATELevel() * 10;
            result += sender.getPLEALevel() * 20;
            result += (sender.getDeviancy() - 50) / 2;
            result -= (sender.getDignity() - 50) / 3;
        } else
        if(this == Project.Praise)
        {
            result += sender.getFEARLevel() * 10;
            result -= sender.getHATELevel() * 25;
            result += sender.getPLEALevel() * 5;
            result += (sender.getObedience() - 65) / 2;
            result -= (sender.getConfidence() - 50) / 3;
        } else
        if(this == Project.Insult)
        {
            result -= sender.getFEARLevel() * 10;
            result += sender.getHATELevel() * 25;
            result -= sender.getPLEALevel() * 5;
            result -= (sender.getObedience() - 65) / 2;
            result += (sender.getConfidence() - 50) / 3;
            if(sender.getINJULevel() == 2)
                result += 20;
            else
            if(sender.getINJULevel() == 3)
                result += 40;
            else
            if(sender.getINJULevel() == 4)
                result += 100;
            result++;
        } else
        if(this == Project.PushDown)
        {
            result -= sender.getFEARLevel() * 10;
            result -= sender.getDISGLevel() * 15;
            result -= sender.getSHAMLevel() * 5;
            result -= sender.getHATELevel() * 10;
            result += sender.getPLEALevel() * 25;
            result += (sender.getDeviancy() - 60) / 2;
            result += (sender.getConfidence() - 50) / 3;
        } else
        if(this == Project.PullDown)
        {
            result -= sender.getFEARLevel() * 5;
            result -= sender.getDISGLevel() * 15;
            result -= sender.getSHAMLevel() * 3;
            result -= sender.getHATELevel() * 10;
            result += sender.getPLEALevel() * 20;
            result += (sender.getDeviancy() - 40) / 2;
            result += (sender.getObedience() - 30) / 2;
            result += (sender.getConfidence() - 50) / 3;
            if(sender.isVVirg().booleanValue())
                result -= 30;
        } else
        if(this != Project.Escape)
            if(this == Project.StrokeCock)
            {
                result -= sender.getFEARLevel() * 5;
                result -= sender.getDISGLevel() * 10;
                result -= sender.getSHAMLevel() * 3;
                result -= sender.getHATELevel() * 15;
                result += sender.getPLEALevel() * 10;
                result += (sender.getDeviancy() - 30) / 2;
                result += (sender.getObedience() - 50) / 2;
                result -= (sender.getInnocence() - 50) / 3;
            } else
            if(this == Project.CockStroked)
            {
                result -= sender.getDISGLevel() * 10;
                result -= sender.getSHAMLevel() * 5;
                result -= sender.getHATELevel() * 10;
                result += sender.getPLEALevel() * 25;
                result += (sender.getDeviancy() - 20) / 2;
            } else
            if(this == Project.VaginalPenetrate)
            {
                result -= sender.getFEARLevel() * 5;
                result -= sender.getDISGLevel() * 15;
                result -= sender.getHATELevel() * 15;
                result += sender.getPLEALevel() * 30;
                result += (sender.getDeviancy() - 40) / 2;
                result += (sender.getObedience() - 60) / 2;
                result -= (sender.getMorality() - 50) / 3;
                result += (sender.getConfidence() - 50) / 3;
            } else
            if(this == Project.PenetratedVaginally)
            {
                if(Project.PullDown.isInProgress(sender, receiver).booleanValue())
                    result += sender.getFEARLevel() * 20;
                result -= sender.getDISGLevel() * 20;
                result -= sender.getHATELevel() * 20;
                result += sender.getPLEALevel() * 30;
                result += (sender.getDeviancy() - 50) / 2;
                result += (sender.getObedience() - 70) / 2;
                result -= (sender.getMorality() - 50) / 3;
                if(sender.isVVirg().booleanValue())
                    result += sender.getObedience() - 60;
                if(sender.forsakenOwner == null && sender.chosenOwner != null && sender.chosenOwner.vVirg.booleanValue())
                    result = -10000;
            } else
            if(this == Project.AnalPenetrate)
            {
                result -= sender.getFEARLevel() * 5;
                result -= sender.getDISGLevel() * 20;
                result -= sender.getHATELevel() * 15;
                result += sender.getPLEALevel() * 25;
                result += (sender.getDeviancy() - 50) / 2;
                result += (sender.getObedience() - 60) / 2;
                result += (sender.getConfidence() - 50) / 3;
            } else
            if(this == Project.PenetratedAnally)
            {
                if(Project.PullDown.isInProgress(sender, receiver).booleanValue())
                    result += sender.getFEARLevel() * 20;
                if(!Project.BeLubricated.isInProgress(sender, null).booleanValue() && !Project.PushDown.isInProgress(sender, receiver).booleanValue())
                {
                    result -= 30;
                    if(sender.forsakenOwner == null && sender.chosenOwner != null && sender.chosenOwner.aVirg.booleanValue())
                        result = -10000;
                }
                result -= sender.getDISGLevel() * 30;
                result -= sender.getHATELevel() * 20;
                result += sender.getPLEALevel() * 20;
                result += (sender.getDeviancy() - 50) / 2;
                result += (sender.getObedience() - 70) / 2;
                if(sender.parts[PUSSY] == 0 && sender.isVVirg().booleanValue())
                {
                    result += sender.getObedience() - 60;
                    result -= (sender.getMorality() - 50) / 3;
                }
            } else
            if(this == Project.Stripped)
            {
                result -= sender.getDISGLevel() * 10;
                result -= sender.getSHAMLevel() * 5;
                if(w.sceneLocation == Location.STAGE)
                {
                    result -= sender.getSHAMLevel() * 10;
                    if(sender.forsakenOwner != null && sender.forsakenOwner.timesExposed < 0x493e0 || sender.chosenOwner != null && sender.chosenOwner.modest.booleanValue())
                        result -= 30;
                }
                result -= sender.getHATELevel() * 10;
                result += sender.getPLEALevel() * 10;
                result += (sender.getDeviancy() - 30) / 2;
                result += (sender.getObedience() - 30) / 2;
                result += (sender.getDisgrace() - 30) / 2;
                result += (sender.getConfidence() - 60) / 2;
                result -= (sender.getDignity() - 30) / 2;
            } else
            if(this == Project.LickCock)
            {
                result -= sender.getFEARLevel() * 5;
                result -= sender.getDISGLevel() * 15;
                result -= sender.getSHAMLevel() * 5;
                result -= sender.getHATELevel() * 25;
                result += sender.getPLEALevel() * 15;
                result += ((sender.getDeviancy() - 40) * 3) / 2;
                result += (sender.getObedience() - 50) / 2;
                result -= (sender.getInnocence() - 50) / 3;
            } else
            if(this == Project.CockLicked)
            {
                result -= sender.getDISGLevel() * 10;
                result -= sender.getSHAMLevel() * 3;
                result -= sender.getHATELevel() * 10;
                result += sender.getPLEALevel() * 30;
                result += (sender.getDeviancy() - 30) / 2;
            } else
            if(this == Project.LickPussy)
            {
                result -= sender.getFEARLevel() * 5;
                result -= sender.getDISGLevel() * 15;
                result -= sender.getSHAMLevel() * 5;
                result -= sender.getHATELevel() * 25;
                result += sender.getPLEALevel() * 15;
                result += ((sender.getDeviancy() - 40) * 3) / 2;
                result += (sender.getObedience() - 50) / 2;
                result -= (sender.getInnocence() - 50) / 3;
            } else
            if(this == Project.PussyLicked)
            {
                result -= sender.getDISGLevel() * 15;
                result -= sender.getSHAMLevel() * 5;
                result -= sender.getHATELevel() * 15;
                result += sender.getPLEALevel() * 25;
                result += (sender.getDeviancy() - 30) / 2;
            } else
            if(this == Project.StepOnCock)
            {
                result -= sender.getFEARLevel() * 10;
                result -= sender.getDISGLevel() * 5;
                result -= sender.getHATELevel() * 5;
                result += sender.getPLEALevel() * 10;
                result += (sender.getDeviancy() - 30) / 2;
                result -= (sender.getObedience() - 30) / 3;
                result += (sender.getConfidence() - 50) / 3;
            } else
            if(this == Project.CockSteppedOn)
            {
                result -= sender.getDISGLevel() * 10;
                result -= sender.getPAINLevel() * 10;
                result -= sender.getSHAMLevel() * 10;
                result -= sender.getHATELevel() * 15;
                result += sender.getPLEALevel() * 20;
                result += (sender.getDeviancy() - 40) / 2;
                result += (sender.getObedience() - 50) / 2;
            } else
            if(this == Project.StepOnClit)
            {
                result -= sender.getFEARLevel() * 10;
                result -= sender.getDISGLevel() * 5;
                result -= sender.getHATELevel() * 5;
                result += sender.getPLEALevel() * 10;
                result += (sender.getDeviancy() - 30) / 2;
                result -= (sender.getObedience() - 30) / 3;
                result += (sender.getConfidence() - 50) / 3;
            } else
            if(this == Project.ClitSteppedOn)
            {
                result -= sender.getDISGLevel() * 10;
                result -= sender.getPAINLevel() * 10;
                result -= sender.getSHAMLevel() * 10;
                result -= sender.getHATELevel() * 15;
                result += sender.getPLEALevel() * 20;
                result += (sender.getDeviancy() - 40) / 2;
                result += (sender.getObedience() - 50) / 2;
            } else
            if(this == Project.DirtyTalk)
            {
                result -= sender.getFEARLevel() * 15;
                result -= sender.getDISGLevel() * 15;
                result -= sender.getSHAMLevel() * 5;
                result += sender.getPLEALevel() * 5;
                result += (sender.getDeviancy() - 30) / 2;
                result -= (sender.getObedience() - 30) / 3;
            }
        if(isInProgress(sender, receiver).booleanValue())
        {
            result += sender.getFEARLevel() * 15;
            result += sender.getObedience() / 2;
        }
        if(hostile.booleanValue())
        {
            result -= sender.obedienceMod(w, receiver);
            result -= sender.friendsMod(w, receiver);
        } else
        {
            result += sender.obedienceMod(w, receiver);
            result += sender.friendsMod(w, receiver);
            if(sender.forsakenOwner == null && sender.chosenOwner != null && sender.chosenOwner.truce.booleanValue())
                result += 30;
            if(sender.getINJULevel() == 2)
                result -= 20;
            else
            if(sender.getINJULevel() == 3)
                result -= 40;
            else
            if(sender.getINJULevel() > 3)
                result -= 100;
        }
        return result;
    }

    public Boolean isInProgress(Body sender, Body receiver)
    {
        if(sender == null)
            return Boolean.valueOf(false);
        for(int i = 0; i < sender.inProgress.length; i++)
            if(sender.inProgress[i] == this && (receiver == null || sender.targets[i] == receiver))
                return Boolean.valueOf(true);

        return Boolean.valueOf(false);
    }

    public Boolean valid(Body sender, Body receiver)
    {
        if(Project.BeTied.isInProgress(sender, null).booleanValue() && this == Project.PullDown)
            return Boolean.valueOf(false);
        if(Project.Supine.isInProgress(sender, null).booleanValue())
        {
            Boolean ender = Boolean.valueOf(false);
            for(int i = 0; i < Project.Supine.enders.length; i++)
                if(this == Project.Supine.enders[i])
                    ender = Boolean.valueOf(true);

            if(!ender.booleanValue() && (sendReqs[HAND] > 0 || sendReqs[FOOT] > 0))
                return Boolean.valueOf(false);
        }
        if(this == Project.PullDown && Project.Supine.isInProgress(receiver, null).booleanValue())
            return Boolean.valueOf(false);
        if(this == Project.PullUp && !Project.Supine.isInProgress(receiver, null).booleanValue())
            return Boolean.valueOf(false);
        if((this == Project.StepOnCock || this == Project.StepOnClit) && !Project.Supine.isInProgress(receiver, null).booleanValue())
            return Boolean.valueOf(false);
        if(this == Project.Lubricate && !Project.PushDown.isInProgress(sender, receiver).booleanValue())
            return Boolean.valueOf(false);
        if(this == Project.DirtyTalk)
        {
            Boolean pleasuring = Boolean.valueOf(false);
            for(int i = 0; i < receiver.inProgress.length; i++)
                if(receiver.inProgress[i].causesOrgasm.booleanValue() && receiver.targets[i] == sender && !receiver.inProgress[i].counterpart.causesOrgasm.booleanValue())
                    pleasuring = Boolean.valueOf(true);

            if(!pleasuring.booleanValue())
                return Boolean.valueOf(false);
        }
        if(sendReqs[MOUTH] > 0 && counterpart != null && (counterpart.sendReqs[PENIS] > 0 || counterpart.sendReqs[BALLS] > 0 || counterpart.sendReqs[CLIT] > 0 || counterpart.sendReqs[PUSSY] > 0) && (Project.PushDown.isInProgress(sender, receiver).booleanValue() || Project.PushDown.isInProgress(receiver, sender).booleanValue()))
            return Boolean.valueOf(false);
        if(this == Project.VaginalPenetrate || this == Project.PenetratedVaginally || this == Project.AnalPenetrate || this == Project.PenetratedAnally)
        {
            if(!Project.PushDown.isInProgress(sender, receiver).booleanValue())
                return Boolean.valueOf(false);
            if(Project.VaginalPenetrate.isInProgress(receiver, sender).booleanValue() || Project.PenetratedVaginally.isInProgress(receiver, sender).booleanValue() || Project.AnalPenetrate.isInProgress(receiver, sender).booleanValue() || Project.PenetratedAnally.isInProgress(receiver, sender).booleanValue())
                return Boolean.valueOf(false);
        }
        for(int i = 0; i < sender.inProgress.length; i++)
            if(sender.inProgress[i] == this && sender.targets[i] == receiver)
                return Boolean.valueOf(false);

        for(int i = 0; receiver != null && i < receiver.inProgress.length && counterpart != null; i++)
            if(receiver.inProgress[i] == counterpart && receiver.targets[i] == sender)
                return Boolean.valueOf(false);

        if(this == Project.StripOther && receiver.getEXPOLevel() > 4)
            return Boolean.valueOf(false);
        for(int i = 0; i < sendReqs.length; i++)
            if(sendReqs[i] > 0)
            {
                if(sender.parts[i] < sendReqs[i])
                    return Boolean.valueOf(false);
                int remaining = sender.parts[i];
                for(int j = 0; j < sender.inProgress.length; j++)
                    if(sender.inProgress[j].sendReqs[i] > 0 && !sender.inProgress[j].nonBlocking.booleanValue() && (!shares.booleanValue() || sender.inProgress[j] != this))
                        remaining -= sender.inProgress[j].sendReqs[i];

                if(remaining < sendReqs[i])
                    return Boolean.valueOf(false);
            }

        if(counterpart != null)
        {
            for(int i = 0; i < counterpart.sendReqs.length; i++)
                if(counterpart.sendReqs[i] > 0)
                {
                    if(receiver.parts[i] < counterpart.sendReqs[i])
                        return Boolean.valueOf(false);
                    int remaining = receiver.parts[i];
                    for(int j = 0; j < receiver.inProgress.length; j++)
                        if(receiver.inProgress[j].sendReqs[i] > 0 && !receiver.inProgress[j].nonBlocking.booleanValue() && (!counterpart.shares.booleanValue() || receiver.inProgress[j] != counterpart))
                            remaining -= receiver.inProgress[j].sendReqs[i];

                    if(remaining < counterpart.sendReqs[i])
                        return Boolean.valueOf(false);
                }

            Body actualTarget = sender;
            if(!counterpart.targeted.booleanValue())
                actualTarget = null;
            if(counterpart.isInProgress(receiver, actualTarget).booleanValue())
                return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }

    public Activity()
    {
        sendReqs = new int[14];
        shares = Boolean.valueOf(false);
        nonBlocking = Boolean.valueOf(false);
        targeted = Boolean.valueOf(false);
        enders = new Activity[0];
        endsSelf = Boolean.valueOf(false);
        pickable = Boolean.valueOf(true);
        hostile = Boolean.valueOf(false);
        causesOrgasm = Boolean.valueOf(false);
    }

    public int sendReqs[];
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
    public Boolean shares;
    public Boolean nonBlocking;
    public Boolean targeted;
    public Activity counterpart;
    public Activity enders[];
    public Boolean endsSelf;
    public Boolean pickable;
    public Boolean hostile;
    public Boolean causesOrgasm;

}
