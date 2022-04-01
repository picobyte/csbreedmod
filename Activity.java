
import javax.swing.JTextPane;

public class Activity
{
    public enum Location {
        CHAMBER, STAGE
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
            return (new StringBuilder("Fucking ")).append(partner.ownerName()).append("'s Pussy").toString();
        if(this == Project.PenetratedVaginally)
            return (new StringBuilder("Pussy Fucked By ")).append(partner.ownerName()).toString();
        if(this == Project.AnalPenetrate)
            return (new StringBuilder("Fucking ")).append(partner.ownerName()).append("'s Ass").toString();
        if(this == Project.PenetratedAnally)
            return (new StringBuilder("Ass Fucked By ")).append(partner.ownerName()).toString();
        if(this == Project.StripOther)
            return (new StringBuilder("Strip ")).append(partner.ownerName()).toString();
        if(this == Project.Stripped)
            return (new StringBuilder("Stripped by ")).append(partner.ownerName()).toString();
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
            input[6] += 4L;
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
            if(subject.isVVirg())
            {
                input[2] += 200L;
                input[4] += 30L;
            }
            input[5] += 200L;
            input[6] += 4L;
        } else
        if(this == Project.AnalPenetrate)
        {
            input[1] += 3L;
            input[3] += 2L;
            input[5] += 350L;
            input[6] += 4L;
        } else
        if(this == Project.PenetratedAnally)
        {
            input[1] += 5L;
            input[2] += 8L;
            input[3] += 10L;
            input[4] += 10L;
            if(subject.parts[PUSSY] == 0 && subject.isVVirg())
                input[4] += 30L;
            if(!Project.BeLubricated.isInProgress(subject, null))
            {
                input[4] += 20L;
                input[2] += 200L;
            }
            input[5] += 100L;
            input[6] += 5L;
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
        }
        return input;
    }

    public void activityTalk(JTextPane t, WorldState w, Body sender, Body receiver)
    {
        String shownNames[] = {
            sender.ownerName(), null, null, null, null
        };
        if(Project.PenetratedVaginally.isInProgress(sender, receiver) && (sender.forsakenOwner != null && sender.forsakenOwner.timesHadSex == 0 || sender.chosenOwner != null && sender.chosenOwner.vVirg))
            sender.specialLine = 1;
        if(Project.PenetratedAnally.isInProgress(sender, receiver))
        {
            if(sender.forsakenOwner != null && sender.forsakenOwner.gender == Forsaken.Gender.MALE && sender.forsakenOwner.timesHadSex == 0 || sender.chosenOwner != null && sender.chosenOwner.gender.equals("male") && sender.chosenOwner.vVirg)
                sender.specialLine = 1;
            else
            if(!Project.BeLubricated.isInProgress(sender, null) && sender.forsakenOwner != null && sender.forsakenOwner.timesTortured == 0)
                sender.specialLine = 3;
            if(sender.forsakenOwner != null)
                if(sender.forsakenOwner.gender == Forsaken.Gender.MALE)
                {
                    sender.forsakenOwner.timesHadSex++;
                    if(!Project.BeLubricated.isInProgress(sender, null))
                        sender.forsakenOwner.timesTortured++;
                } else
                if(Project.BeLubricated.isInProgress(sender, null))
                    sender.forsakenOwner.enjoyedAnal++;
                else
                    sender.forsakenOwner.timesTortured++;
        }
        sender.say(t, "\"");
        if(sender.specialLine == 1)
        {
            if(sender.getGender() == Forsaken.Gender.MALE)
            {
                if(Project.BeLubricated.isInProgress(sender, null))
                {
                    if(sender.getObedience() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.JOY);
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
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.SHAME);
                        if(sender.getInnocence() > 66)
                            sender.say(t, "It kinda hurts...");
                        else
                        if(sender.getInnocence() > 33)
                            sender.say(t, "I'm surprised you took so long to do this...");
                        else
                            sender.say(t, "I suppose... I had already come to accept that you'd eventually use me back there as well...");
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.ANGER);
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
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.FEAR);
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
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.JOY);
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
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.SHAME);
                if(sender.getInnocence() > 66)
                    sender.say(t, "It kinda hurts...");
                else
                if(sender.getInnocence() > 33)
                    sender.say(t, "I'm surprised you took so long to do this...");
                else
                    sender.say(t, "I suppose... I had already come to accept that you'd be my first...");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.ANGER);
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
                sender.chosenOwner.vVirg = false;
        } else
        if(sender.specialLine == 2)
        {
            Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.LEWD);
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
            Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.STRUGGLE);
            if(sender.getObedience() > 66)
            {
                if(sender.getDeviancy() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.LEWD);
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
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.LEWD);
                if(sender.getDignity() > 66)
                    sender.say(t, (new StringBuilder("Such an... incredible feeling of shame...!  Ngh...!  Thank you, ")).append(sender.demonLord()).append(", thank you for letting me feel this...!").toString());
                else
                if(sender.getDignity() > 33)
                {
                    sender.say(t, (new StringBuilder("Aaah, yes!  Now everyone will know that I belong to ")).append(sender.theDemonLord()).append("!").toString());
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.FOCUS);
                    sender.say(t, (new StringBuilder("Is there anything else you want me to show them, ")).append(sender.demonLord()).append("?").toString());
                }
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.SHAME);
                if(sender.getDignity() > 66)
                    sender.say(t, "You've taken... everything... from me...  Ngh...");
                else
                if(sender.getDignity() > 33)
                    sender.say(t, "Maybe... if I look like I'm enjoying it... they won't think I'm weak...");
                else
                    sender.say(t, "I never cared how much they saw, so... it's fine.");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.ANGER);
                if(sender.getDignity() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.FEAR);
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
            Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SWOON, Project.Emotion.SWOON);
            sender.say(t, "...");
        } else
        if(this == Project.TweakClit)
        {
            if(receiver == w.lordBody)
                if(sender.getObedience() > 66)
                {
                    if(sender.getInnocence() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                        sender.say(t, (new StringBuilder("I'm so happy I get to make ")).append(sender.theDemonLord()).append(" feel good!").toString());
                    } else
                    if(sender.getInnocence() > 33)
                    {
                        sender.say(t, (new StringBuilder("Does this feel good, ")).append(sender.demonLord()).append("?  I just want to make you happy...").toString());
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                    } else
                    {
                        sender.say(t, (new StringBuilder("I need to impress ")).append(sender.theDemonLord()).append(" with my technique...!").toString());
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.NEUTRAL);
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.currentHATE > 1000L)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                        sender.say(t, "Ugh, making me touch you like this...!");
                    } else
                    if(sender.currentHATE > 100L)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
                        sender.say(t, "Why do you need me to do this...?");
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FOCUS);
                        sender.say(t, "Is this alright?");
                    }
                } else
                if(sender.getDeviancy() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                    sender.say(t, "Ngh...  You knew... I wouldn't be able to resist this beautiful body you made...");
                } else
                if(sender.getDeviancy() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.ANGER);
                    sender.say(t, "Fine.  I'll make you feel good.  But you'd better return the favor...");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                    sender.say(t, "Disgusting... I don't want to touch the Demon Lord...");
                }
        } else
        if(this == Project.Praise)
        {
            if(w.sceneDuration % 3 == 0)
            {
                if(sender.getObedience() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
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
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.JOY);
                    if(sender.getInnocence() > 66)
                        sender.say(t, "This is weird, but... kinda fun, too.");
                    else
                    if(sender.getInnocence() > 33)
                        sender.say(t, (new StringBuilder("You're amazing, ")).append(sender.demonLord()).append(".").toString());
                    else
                        sender.say(t, "Let me prove my value to you.");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
                    if(sender.getInnocence() > 66)
                        sender.say(t, (new StringBuilder(String.valueOf(sender.TheDemonLord()))).append(" isn't so bad...  Wait, what am I saying!?").toString());
                    else
                    if(sender.getInnocence() > 33)
                        sender.say(t, "Ugh, I'm completely under your control...");
                    else
                        sender.say(t, (new StringBuilder("I'm... aware that I stand no chance of defeating you, ")).append(sender.demonLord()).append(".").toString());
                }
            } else
            if(w.sceneDuration % 3 == 1)
            {
                if(sender.getObedience() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
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
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, (new StringBuilder("I want you to make me feel even better, ")).append(sender.demonLord()).append("!").toString());
                    } else
                    if(sender.getDeviancy() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.JOY);
                        sender.say(t, (new StringBuilder("My body is all yours, ")).append(sender.demonLord()).append(".").toString());
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
                        sender.say(t, (new StringBuilder("Your touch is... unforgettable, ")).append(sender.demonLord()).append("...").toString());
                    }
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
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
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                        sender.say(t, "I want to make you forget about all your other servants...");
                    } else
                    if(sender.getConfidence() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                        sender.say(t, (new StringBuilder("You're just perfect, ")).append(sender.demonLord()).append("!  I don't know why I ever fought you...").toString());
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
                        sender.say(t, "I know it's wrong to want you to care about me... but...");
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
                    if(sender.getConfidence() > 66)
                        sender.say(t, "I've never feared anyone but you...");
                    else
                    if(sender.getConfidence() > 33)
                        sender.say(t, "I'll never oppose you again.  I understand that now.");
                    else
                        sender.say(t, "I can't even think about fighting you anymore...");
                } else
                if(sender.getConfidence() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.NEUTRAL);
                    sender.say(t, "You're... a worthy opponent.  I wouldn't even feel bad about losing to you.  Not that I'm giving up!");
                } else
                if(sender.getConfidence() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
                    sender.say(t, "I... never stood a chance against you.  Ugh, I hate saying that...");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
                    sender.say(t, "I, um...  I don't actually think I'm going to be able to resist you for that long.  I just don't want to give up right away...");
                }
        } else
        if(this == Project.Insult)
        {
            if(w.sceneDuration % 3 == 0)
            {
                if(sender.getObedience() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.SHAME);
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
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
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
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.ANGER);
                    sender.say(t, "Heh...  I'll keep resisting... until I pass out...");
                } else
                if(sender.getINJULevel() == 2)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                    sender.say(t, "I'm not going to do what you want, so just go away!");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
                    sender.say(t, "Stop this!");
                }
            } else
            if(w.sceneDuration % 3 == 1)
            {
                if(sender.getObedience() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
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
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                        sender.say(t, "I should have known better than to trust you...!");
                    } else
                    if(sender.getHATELevel() == 1)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
                        sender.say(t, (new StringBuilder("I'm not happy with you right now, ")).append(sender.demonLord()).append(".").toString());
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.STRUGGLE);
                        sender.say(t, "Can't we do this another time?");
                    }
                } else
                if(sender.getHATELevel() > 1)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                    sender.say(t, "I swear I'll kill you, Demon Lord!");
                } else
                if(sender.getHATELevel() == 1)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
                    sender.say(t, "Are you just trying to provoke me!?");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.ANGER);
                    sender.say(t, "You think you'll convince me like this?  Hah!");
                }
            } else
            if(sender.getObedience() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
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
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.ANGER);
                if(sender.getDeviancy() > 66)
                    sender.say(t, "This is too much, even for me!");
                else
                if(sender.getDeviancy() > 33)
                    sender.say(t, "This is too much for me!");
                else
                    sender.say(t, "You know I don't like this sort of thing...");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                if(sender.getDeviancy() > 66)
                    sender.say(t, "Just go away and let me play with myself!");
                else
                if(sender.getDeviancy() > 33)
                    sender.say(t, "I don't want to do this!  At least not with you!");
                else
                    sender.say(t, "You're... disgusting...");
            }
        } else
        if(this == Project.SpreadLegs)
        {
            sender.say(t, (new StringBuilder("Please, touch me more, ")).append(sender.demonLord()).append("!").toString());
            Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
        } else
        if(this == Project.PushDown)
        {
            if(sender.getDeviancy() > 66)
            {
                if(sender.getConfidence() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                    sender.say(t, (new StringBuilder("Aaah... I wanna rape ")).append(sender.theDemonLord()).append(" a lot...!").toString());
                } else
                if(sender.getConfidence() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                    sender.say(t, "Hurry, hurry...!");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                    sender.say(t, "If we don't do this now... I-I'm gonna go crazy...!");
                }
            } else
            if(sender.getDeviancy() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
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
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
                sender.say(t, (new StringBuilder("Are you sure you don't want to lead, ")).append(sender.demonLord()).append("?").toString());
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
                sender.say(t, "I have no idea what I'm doing...");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
                sender.say(t, "Let's get this over with.");
            }
        } else
        if(this == Project.PullDown)
        {
            if(sender.getDeviancy() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
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
                if(sender.getDignity() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.LEWD);
                    sender.say(t, "I-I guess I'm ready when you are...");
                } else
                if(sender.getDignity() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.LEWD);
                    sender.say(t, "This is going to feel good...");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                    sender.say(t, "I can't take it any more...!");
                }
            } else
            if(sender.getObedience() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
                sender.say(t, "I hope you enjoy my body...");
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
                sender.say(t, "I never thought I'd be doing this...");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
                sender.say(t, "Go on, finish satisfying yourself so I can get out of here!");
            }
        } else
        if(this == Project.Escape)
        {
            if(sender.getObedience() > 66)
            {
                if(sender.getMorality() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
                    sender.say(t, (new StringBuilder("I-I'm so sorry, ")).append(sender.demonLord()).append("...  I'm worthless...").toString());
                } else
                if(sender.getMorality() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.SHAME);
                    sender.say(t, "Are you going to punish me...?");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.SHAME);
                    sender.say(t, (new StringBuilder("I can't!  Even if you are ")).append(sender.theDemonLord()).append("!").toString());
                }
            } else
            if(sender.getObedience() > 33)
            {
                if(sender.getInnocence() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.SHAME);
                    sender.say(t, "Oh no!  I- I didn't mean to do that!");
                } else
                if(sender.getInnocence() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
                    sender.say(t, "I'm sorry, but I really need a break...");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
                    sender.say(t, "I cannot continue.");
                }
            } else
            if(sender.getConfidence() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
                sender.say(t, "Whew...  Heh, I couldn't hold myself back anymore.");
            } else
            if(sender.getConfidence() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
                sender.say(t, "I'm not afraid of you!");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
                sender.say(t, "I c-can't take this anymore!");
            }
        } else
        if(this == Project.StopActing)
        {
            if(sender.getObedience() > 66)
            {
                if(sender.getMorality() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
                    sender.say(t, "Maybe... I need more training...");
                } else
                if(sender.getMorality() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.SHAME);
                    sender.say(t, "Sorry, I'm... at my limit...");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.JOY);
                    sender.say(t, "How about I lay here while you rub up against me?  That will feel good too, right?");
                }
            } else
            if(sender.getObedience() > 33)
            {
                if(sender.getDeviancy() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                    sender.say(t, "Well, I enjoyed that.  Let's do it again sometime.");
                } else
                if(sender.getDeviancy() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
                    sender.say(t, "That was good enough, right?");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
                    sender.say(t, "I should go... do some combat training.  I can't stay here doing this all day.");
                }
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
                if(sender.getInnocence() > 66)
                    sender.say(t, "I'm not gonna make you feel good anymore!");
                else
                if(sender.getInnocence() > 33)
                    sender.say(t, "This is over.");
                else
                    sender.say(t, "Continuing to service you is worse than any punishment you can inflict on me.");
            }
        } else
        if(this == Project.StrokeCock)
        {
            if(receiver == w.lordBody)
                if(sender.getObedience() > 66)
                {
                    if(sender.getInnocence() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                        sender.say(t, (new StringBuilder("I'm so happy I get to make ")).append(sender.theDemonLord()).append(" feel good!").toString());
                    } else
                    if(sender.getInnocence() > 33)
                    {
                        sender.say(t, (new StringBuilder("Does this feel good, ")).append(sender.demonLord()).append("?  I just want to make you happy...").toString());
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                    } else
                    {
                        sender.say(t, (new StringBuilder("I need to impress ")).append(sender.theDemonLord()).append(" with my technique...!").toString());
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.NEUTRAL);
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.currentHATE > 1000L)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                        sender.say(t, "Ugh, making me touch you like this...!");
                    } else
                    if(sender.currentHATE > 100L)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
                        sender.say(t, "Why do you need me to do this...?");
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FOCUS);
                        sender.say(t, "Is this alright?");
                    }
                } else
                if(sender.getDeviancy() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                    sender.say(t, "Ngh...  You knew... I wouldn't be able to resist this beautiful body you made...");
                } else
                if(sender.getDeviancy() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.ANGER);
                    sender.say(t, "Fine.  I'll make you feel good.  But you'd better return the favor...");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                    sender.say(t, "Disgusting... I don't want to touch the Demon Lord...");
                }
        } else
        if(this == Project.VaginalPenetrate)
        {
            if(Project.PushDown.isInProgress(sender, receiver))
            {
                if(sender.getDeviancy() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
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
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Aaah...!  Wow...!");
                    } else
                    if(sender.getInnocence() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                        sender.say(t, "That's... tight...!");
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                        sender.say(t, "Ah, yes...  Here we go...");
                    }
                } else
                if(sender.getObedience() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                    sender.say(t, (new StringBuilder("I love you, ")).append(sender.demonLord()).append("!  I love you so much!").toString());
                } else
                if(sender.getObedience() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FEAR);
                    sender.say(t, "This is... really intense...");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
                    sender.say(t, "How dare you... tempt me...!?  Take this!  And this!");
                }
            } else
            if(sender.getDeviancy() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
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
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
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
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.LEWD);
                sender.say(t, "I'll hold out... as long as I can...!");
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.LEWD);
                sender.say(t, "Why... are you making me feel so good...!?");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
                sender.say(t, "J-Just... have to calm down...!");
            }
        } else
        if(this == Project.PenetratedVaginally)
        {
            if(Project.PushDown.isInProgress(sender, receiver))
            {
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getObedience() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Yes!  Yes!  Cum inside me!.");
                    } else
                    if(sender.getObedience() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, (new StringBuilder("Aaah...  Your cock is amazing, ")).append(sender.demonLord()).append("!").toString());
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                        sender.say(t, "You... t-tricked me into doing this...!");
                    }
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getConfidence() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                        sender.say(t, "Ngh...!  I know you like it... rough...!");
                    } else
                    if(sender.getConfidence() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                        sender.say(t, "It's...  Ah!  Inside!");
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Th-This... actually feels really good...!");
                    }
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.LEWD);
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
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                    sender.say(t, "Yes!  Yes!  Take me!  Take me!");
                } else
                if(sender.getDeviancy() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                    sender.say(t, (new StringBuilder("I'm sorry, ")).append(sender.demonLord()).append("!  It feels too good with you inside me!").toString());
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                    sender.say(t, "Does it feel good when I... ngh... squeeze?  Like this?");
                }
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
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
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                sender.say(t, "Aaagh, gaaah!  Stop it, nnnaaah!");
            } else
            if(sender.getInnocence() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.LEWD);
                sender.say(t, "Mph!  Gh!  Agh!");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                sender.say(t, "Just... climax already, you brute...!");
            }
        } else
        if(this == Project.AnalPenetrate)
        {
            if(Project.PushDown.isInProgress(sender, receiver))
            {
                if(sender.getDeviancy() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
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
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Aaah...!  Wow...!");
                    } else
                    if(sender.getInnocence() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                        sender.say(t, "That's... tight...!");
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                        sender.say(t, "Ah, yes...  Here we go...");
                    }
                } else
                if(sender.getObedience() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                    sender.say(t, (new StringBuilder("I love you, ")).append(sender.demonLord()).append("!  I love you so much!").toString());
                } else
                if(sender.getObedience() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FEAR);
                    sender.say(t, "This is... really intense...");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
                    sender.say(t, "How dare you... tempt me...!?  Take this!  And this!");
                }
            } else
            if(sender.getDeviancy() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
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
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
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
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.LEWD);
                sender.say(t, "I'll hold out... as long as I can...!");
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.LEWD);
                sender.say(t, "Why... are you making me feel so good...!?");
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
                sender.say(t, "J-Just... have to calm down...!");
            }
        } else
        if(this == Project.PenetratedAnally)
            if(Project.PushDown.isInProgress(sender, receiver))
            {
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getObedience() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Yes!  Yes!  Cum inside me!.");
                    } else
                    if(sender.getObedience() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, (new StringBuilder("Aaah...  Your cock is amazing, ")).append(sender.demonLord()).append("!").toString());
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                        sender.say(t, "You... t-tricked me into doing this...!");
                    }
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getConfidence() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                        sender.say(t, "Ngh...!  I know you like it... rough...!");
                    } else
                    if(sender.getConfidence() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                        sender.say(t, "It's...  Ah!  Inside!");
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Th-This... actually feels really good...!");
                    }
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.LEWD);
                    if(sender.getDignity() > 66)
                        sender.say(t, "I can't believe... I'm doing this...!");
                    else
                    if(sender.getDignity() > 33)
                        sender.say(t, "Let me... catch my breath...");
                    else
                        sender.say(t, "Ow...");
                }
            } else
            if(Project.BeLubricated.isInProgress(sender, null))
            {
                if(sender.getObedience() > 66)
                {
                    if(sender.getDeviancy() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Yes!  Yes!  Take me!  Take me!");
                    } else
                    if(sender.getDeviancy() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                        sender.say(t, (new StringBuilder("I'm sorry, ")).append(sender.demonLord()).append("!  It feels too good with you inside me!").toString());
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                        sender.say(t, "Does it feel good when I... ngh... squeeze?  Like this?");
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
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
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                    sender.say(t, "Aaagh, gaaah!  Stop it, nnnaaah!");
                } else
                if(sender.getInnocence() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.LEWD);
                    sender.say(t, "Mph!  Gh!  Agh!");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                    sender.say(t, "Just... climax already, you brute...!");
                }
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.FEAR);
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
            w.append(t, (new StringBuilder("\n\n")).append(sender.capitalizedOwnerName()).append(" stands, letting ").append(receiver.ownerName()).append(" up.").toString());
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
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is too tired to resist physically, but ").append(sender.heShe()).append(" still verbally attacks ").append(receiver.ownerName()).append(".").toString());
                else
                if(sender.getHATELevel() >= 1)
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" already hates ").append(receiver.ownerName()).append(", but ").append(sender.heShe()).append("'s even angrier than usual at being forced into this, and ").append(sender.heShe()).append(" vents ").append(sender.hisHer()).append(" frustration with a stream of insults").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" takes the chance to insult ").append(receiver.ownerName()).append(" to ").append(receiver.hisHer()).append(" face.").toString());
        } else
        if(this == Project.PushDown)
        {
            if(sender == w.lordBody)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" looms over ").append(receiver.ownerName()).append(".").toString());
            else
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
                w.append(t, (new StringBuilder("With an annoyed expression on ")).append(sender.hisHer()).append(" face, ").append(sender.ownerName()).append(" pushes ").append(receiver.ownerName()).append(" down onto ").append(receiver.hisHer()).append(" back without a hint of sensuality.").toString());
        } else
        if(this == Project.PullDown)
        {
            if(sender == w.lordBody)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" is laid down under ").append(receiver.ownerName()).append(".").toString());
            else
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
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" submissively presents ").append(sender.himHer()).append("self to ").append(receiver.ownerName()).append(", layin on ").append(sender.hisHer()).append(" back and squirming nervously as ").append(sender.heShe()).append(" anticipates how ").append(sender.heShe()).append("'ll be used.").toString());
            else
            if(sender.getObedience() > 33)
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" lays down for ").append(receiver.ownerName()).append(", but ").append(sender.heShe()).append(" refuses to meet ").append(receiver.hisHer()).append(" eyes, still uncertain how ").append(sender.heShe()).append(" feels about sex with the Demon Lord.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" angrily lays down, glaring up at ").append(receiver.ownerName()).append(".").toString());
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
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(", overwhelmed by ").append(sender.hisHer()).append(" hatred for the Demon Lord, starts struggling to get away before ").append(sender.heShe()).append(" knows what ").append(sender.heShe()).append("'s doing.  However, ").append(sender.heShe()).append(" stands tall afterward, regaining ").append(sender.hisHer()).append(" poise as if daring ").append(receiver.ownerName()).append(" to punish ").append(sender.himHer()).append(".").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" flails in panic, then makes an effort to run away.").toString());
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
                        w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" starts to get distracted and make excuses for why ").append(sender.heShe()).append(" should be allowed to leave, and ").append(sender.heShe()).append(" stops trying to pleasure ").append(receiver.ownerName()).append(".").toString());
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
            if(Project.PushDown.isInProgress(sender, receiver))
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
            if(Project.PushDown.isInProgress(sender, receiver))
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
                if(sender.isVVirg())
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
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" glares up at ").append(receiver.ownerName()).append(", and ").append(sender.hisHer()).append(" comes through clearly even as ").append(sender.heShe()).append(" winces and groans at the stimulation to ").append(sender.hisHer()).append(" most sensitive inner places.").toString());
                if(sender.isVVirg())
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
            if(Project.PushDown.isInProgress(sender, receiver))
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
            if(Project.PushDown.isInProgress(sender, receiver))
            {
                if(sender == w.lordBody)
                {
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" mounts ").append(receiver.ownerName()).append(" and lowers ").append(sender.himHer()).append("self until ").append(sender.hisHer()).append(" anus envelops ").append(receiver.ownerName()).append("'s cock.").toString());
                } else
                {
                    if(!Project.BeLubricated.isInProgress(sender, null))
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
                if(sender.isVVirg())
                    if(sender.getObedience() > 66)
                        w.append(t, (new StringBuilder("  ")).append(sender.HeShe()).append(" embraces the pain of ").append(sender.hisHer()).append(" first vaginal penetration, ecstatic to have received it from ").append(receiver.ownerName()).append(".").toString());
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, (new StringBuilder("  ")).append(sender.HeShe()).append(" looks down at the trickle of blood on ").append(sender.hisHer()).append(" thigh with an expression of resignation.").toString());
                    else
                        w.append(t, (new StringBuilder("  ")).append(sender.HeShe()).append(" lifts ").append(sender.himHer()).append("self up enough to look down and see the blood on ").append(receiver.ownerName()).append("'s cock, then grits ").append(sender.hisHer()).append(" teeth.").toString());
            } else
            if(Project.BeLubricated.isInProgress(sender, null) || sender == w.lordBody)
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
                    w.append(t, (new StringBuilder(String.valueOf(sender.OwnerName()))).append(" glares up at ").append(receiver.ownerName()).append(", and ").append(sender.hisHer()).append(" comes through clearly even as ").append(sender.heShe()).append(" winces and groans at the stimulation through ").append(sender.hisHer()).append(" anal walls.").toString());
                if(sender.getGender() == Forsaken.Gender.MALE && sender.isVVirg())
                    if(sender.getObedience() > 66)
                        w.append(t, (new StringBuilder("  ")).append(sender.HeShe()).append(" smiles broadly up at ").append(receiver.ownerName()).append(", tears of joy leaking from ").append(sender.hisHer()).append(" eyes at receiving ").append(sender.hisHer()).append(" first anal penetration from the one ").append(sender.heShe()).append(" loves.").toString());
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, (new StringBuilder("  To ")).append(sender.hisHer()).append(" horror, the pleasure keeps building further and further, until ").append(sender.heShe()).append(" can't deny that ").append(sender.heShe()).append(" enjoys being fucked like a girl.").toString());
                    else
                        w.append(t, (new StringBuilder("  ")).append(sender.HisHer()).append(" anger drowns out the pain of having ").append(sender.hisHer()).append(" inexperienced asshole stretched so wide for the first time.").toString());
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
                if(sender.getGender() == Forsaken.Gender.MALE && sender.isVVirg())
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
        if(counterpart != null)
        {
            Boolean found = false;
            for(int i = 0; i < receiver.inProgress.length; i++)
                if(receiver.inProgress[i] == counterpart && receiver.targets[i] == sender)
                    found = true;

            if(!found)
            {
                w.append(t, "\n\n");
                Body actualTarget = sender;
                if(!counterpart.targeted)
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
            result += (sender.getObedience() - 30) / 2;
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
            if(sender.isVVirg())
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
                result += (sender.getObedience() - 30) / 2;
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
                if(Project.PullDown.isInProgress(sender, receiver))
                    result += sender.getFEARLevel() * 20;
                result -= sender.getDISGLevel() * 20;
                result -= sender.getHATELevel() * 20;
                result += sender.getPLEALevel() * 30;
                result += (sender.getDeviancy() - 50) / 2;
                result += (sender.getObedience() - 70) / 2;
                result -= (sender.getMorality() - 50) / 3;
                if(sender.isVVirg())
                    result += sender.getObedience() - 60;
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
                if(Project.PullDown.isInProgress(sender, receiver))
                    result += sender.getFEARLevel() * 20;
                if(!Project.BeLubricated.isInProgress(sender, null) && !Project.PushDown.isInProgress(sender, receiver))
                    result -= 30;
                result -= sender.getDISGLevel() * 30;
                result -= sender.getHATELevel() * 20;
                result += sender.getPLEALevel() * 20;
                result += (sender.getDeviancy() - 50) / 2;
                result += (sender.getObedience() - 70) / 2;
                if(sender.parts[PUSSY] == 0 && sender.isVVirg())
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
                    if(sender.forsakenOwner != null && sender.forsakenOwner.timesExposed < 0x493e0 || sender.chosenOwner != null && sender.chosenOwner.modest)
                        result -= 30;
                }
                result -= sender.getHATELevel() * 10;
                result += sender.getPLEALevel() * 10;
                result += (sender.getDeviancy() - 30) / 2;
                result += (sender.getObedience() - 30) / 2;
                result += (sender.getDisgrace() - 30) / 2;
                result += (sender.getConfidence() - 60) / 2;
                result -= (sender.getDignity() - 30) / 2;
            }
        if(isInProgress(sender, receiver))
        {
            result += sender.getFEARLevel() * 15;
            result += sender.getObedience() / 2;
        }
        if(hostile)
        {
            result -= sender.obedienceMod(w, receiver);
            result -= sender.friendsMod(w, receiver);
        } else
        {
            result += sender.obedienceMod(w, receiver);
            result += sender.friendsMod(w, receiver);
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
        for(int i = 0; i < sender.inProgress.length; i++)
            if(sender.inProgress[i] == this && (receiver == null || sender.targets[i] == receiver))
                return true;

        return false;
    }

    public Boolean valid(Body sender, Body receiver)
    {
        if(this == Project.Lubricate && !Project.PushDown.isInProgress(sender, receiver))
            return false;
        if(this == Project.VaginalPenetrate || this == Project.PenetratedVaginally || this == Project.AnalPenetrate || this == Project.PenetratedAnally)
        {
            if(!Project.PushDown.isInProgress(sender, receiver))
                return false;
            if(Project.VaginalPenetrate.isInProgress(receiver, sender) || Project.PenetratedVaginally.isInProgress(receiver, sender) || Project.AnalPenetrate.isInProgress(receiver, sender) || Project.PenetratedAnally.isInProgress(receiver, sender))
                return false;
        }
        for(int i = 0; i < sender.inProgress.length; i++)
            if(sender.inProgress[i] == this && sender.targets[i] == receiver)
                return false;

        for(int i = 0; receiver != null && i < receiver.inProgress.length && counterpart != null; i++)
            if(receiver.inProgress[i] == counterpart && receiver.targets[i] == sender)
                return false;

        if(this == Project.StripOther && receiver.getEXPOLevel() > 4)
            return false;
        for(int i = 0; i < sendReqs.length; i++)
            if(sendReqs[i] > 0)
            {
                if(sender.parts[i] < sendReqs[i])
                    return false;
                int remaining = sender.parts[i];
                for(int j = 0; j < sender.inProgress.length; j++)
                    if(sender.inProgress[j].sendReqs[i] > 0 && !sender.inProgress[j].nonBlocking && (!shares || sender.inProgress[j] != this))
                        remaining -= sender.inProgress[j].sendReqs[i];

                if(remaining < sendReqs[i])
                    return false;
            }

        if(counterpart != null)
        {
            for(int i = 0; i < counterpart.sendReqs.length; i++)
                if(counterpart.sendReqs[i] > 0)
                {
                    if(receiver.parts[i] < counterpart.sendReqs[i])
                        return false;
                    int remaining = receiver.parts[i];
                    for(int j = 0; j < receiver.inProgress.length; j++)
                        if(receiver.inProgress[j].sendReqs[i] > 0 && !receiver.inProgress[j].nonBlocking && (!counterpart.shares || receiver.inProgress[j] != counterpart))
                            remaining -= receiver.inProgress[j].sendReqs[i];

                    if(remaining < counterpart.sendReqs[i])
                        return false;
                }

            Body actualTarget = sender;
            if(!counterpart.targeted)
                actualTarget = null;
            if(counterpart.isInProgress(receiver, actualTarget))
                return false;
        }
        return true;
    }

    public Activity()
    {
        sendReqs = new int[14];
        shares = false;
        nonBlocking = false;
        targeted = false;
        enders = new Activity[0];
        endsSelf = false;
        pickable = true;
        hostile = false;
        causesOrgasm = false;
    }
}
