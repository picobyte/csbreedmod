
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
            if(subject.forsakenOwner != null && subject.forsakenOwner.timesExposed < 300_000)
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
                            sender.say(t, "I'm... guh... f-fine, " + sender.demonLord() + "!");
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
                    sender.say(t, "That was... amazing!  It's like all my love for " + sender.theDemonLord() + "... came out at once...!");
                else
                if(sender.getObedience() > 33)
                    sender.say(t, "Aaah, what's happening...!?  I feel good!  I feel too good!  I'm going crazy...!");
                else
                    sender.say(t, "Whuh?  What was... that feeling...?  What did you do to me...?");
            } else
            if(sender.getInnocence() > 33)
            {
                if(sender.getObedience() > 66)
                    sender.say(t, "When " + sender.theDemonLord() + " makes me cum... it feels so much better than when I do it myself...!");
                else
                if(sender.getObedience() > 33)
                    sender.say(t, "Wow!  That was... intense!");
                else
                    sender.say(t, "Ugh, you made me cum... even though I didn't want to...  Not with you...");
            } else
            if(sender.getObedience() > 66)
                sender.say(t, "Such an... overwhelmingly intense orgasm!  Ah, " + sender.demonLord() + ", my body has come to desire your touch above all else!");
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
                    sender.say(t, "Such an... incredible feeling of shame...!  Ngh...!  Thank you, " + sender.demonLord() + ", thank you for letting me feel this...!");
                else
                if(sender.getDignity() > 33)
                {
                    sender.say(t, "Aaah, yes!  Now everyone will know that I belong to " + sender.theDemonLord() + "!");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.FOCUS);
                    sender.say(t, "Is there anything else you want me to show them, " + sender.demonLord() + "?");
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
                        sender.say(t, "I'm so happy I get to make " + sender.theDemonLord() + " feel good!");
                    } else
                    if(sender.getInnocence() > 33)
                    {
                        sender.say(t, "Does this feel good, " + sender.demonLord() + "?  I just want to make you happy...");
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                    } else
                    {
                        sender.say(t, "I need to impress " + sender.theDemonLord() + " with my technique...!");
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
                        sender.say(t, "I'm so happy that I get to spend time with " + sender.theDemonLord() + "!");
                    else
                    if(sender.getInnocence() > 33)
                        sender.say(t, "Use me up until nothing is left, " + sender.demonLord() + ".");
                    else
                        sender.say(t, "I am unworthy to serve " + sender.theDemonLord() + "... but I shall try my best!");
                } else
                if(sender.getObedience() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.JOY);
                    if(sender.getInnocence() > 66)
                        sender.say(t, "This is weird, but... kinda fun, too.");
                    else
                    if(sender.getInnocence() > 33)
                        sender.say(t, "You're amazing, " + sender.demonLord() + ".");
                    else
                        sender.say(t, "Let me prove my value to you.");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
                    if(sender.getInnocence() > 66)
                        sender.say(t, String.valueOf(sender.TheDemonLord()) + " isn't so bad...  Wait, what am I saying!?");
                    else
                    if(sender.getInnocence() > 33)
                        sender.say(t, "Ugh, I'm completely under your control...");
                    else
                        sender.say(t, "I'm... aware that I stand no chance of defeating you, " + sender.demonLord() + ".");
                }
            } else
            if(w.sceneDuration % 3 == 1)
            {
                if(sender.getObedience() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                    if(sender.getDeviancy() > 66)
                        sender.say(t, "I-Increidble...!  Being with " + sender.theDemonLord() + " feels even better...!");
                    else
                    if(sender.getDeviancy() > 33)
                        sender.say(t, "I love you, " + sender.demonLord() + "!");
                    else
                        sender.say(t, "You're remaking me... into your own personal toy...!");
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.getDeviancy() > 66)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "I want you to make me feel even better, " + sender.demonLord() + "!");
                    } else
                    if(sender.getDeviancy() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.JOY);
                        sender.say(t, "My body is all yours, " + sender.demonLord() + ".");
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
                        sender.say(t, "Your touch is... unforgettable, " + sender.demonLord() + "...");
                    }
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
                    if(sender.getDeviancy() > 66)
                        sender.say(t, "Ugh... I... I can't resist you, " + sender.demonLord() + "...");
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
                        sender.say(t, "You're just perfect, " + sender.demonLord() + "!  I don't know why I ever fought you...");
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
                        sender.say(t, "P-Please... " + sender.demonLord() + "...  I'm passing out...");
                    else
                    if(sender.getINJULevel() == 2)
                        sender.say(t, "I can't keep up with you, " + sender.demonLord() + "...");
                    else
                        sender.say(t, "Are you trying to punish me, " + sender.demonLord() + "?");
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
                        sender.say(t, "I don't want to be angry with " + sender.theDemonLord() + ", but...!");
                    else
                    if(sender.getHATELevel() == 1)
                        sender.say(t, "Just... Just give me a moment to calm down...!");
                    else
                        sender.say(t, "I'm sorry, " + sender.demonLord() + "...");
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
                        sender.say(t, "I'm not happy with you right now, " + sender.demonLord() + ".");
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
                    sender.say(t, "I'll just... dream about a nicer " + sender.demonLord() + "...");
                else
                if(sender.getDeviancy() > 33)
                    sender.say(t, "How can I hate this, when it's with " + sender.theDemonLord() + "...?");
                else
                    sender.say(t, "Even if it's with " + sender.theDemonLord() + ", I... I don't like this...");
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
            sender.say(t, "Please, touch me more, " + sender.demonLord() + "!");
            Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
        } else
        if(this == Project.PushDown)
        {
            if(sender.getDeviancy() > 66)
            {
                if(sender.getConfidence() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                    sender.say(t, "Aaah... I wanna rape " + sender.theDemonLord() + " a lot...!");
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
                sender.say(t, "Are you sure you don't want to lead, " + sender.demonLord() + "?");
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
                    sender.say(t, "I-I'm so sorry, " + sender.demonLord() + "...  I'm worthless...");
                } else
                if(sender.getMorality() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.SHAME);
                    sender.say(t, "Are you going to punish me...?");
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.SHAME);
                    sender.say(t, "I can't!  Even if you are " + sender.theDemonLord() + "!");
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
                        sender.say(t, "I'm so happy I get to make " + sender.theDemonLord() + " feel good!");
                    } else
                    if(sender.getInnocence() > 33)
                    {
                        sender.say(t, "Does this feel good, " + sender.demonLord() + "?  I just want to make you happy...");
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                    } else
                    {
                        sender.say(t, "I need to impress " + sender.theDemonLord() + " with my technique...!");
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
                    sender.say(t, "I love you, " + sender.demonLord() + "!  I love you so much!");
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
                        sender.say(t, "Aaah...  Your cock is amazing, " + sender.demonLord() + "!");
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
                    sender.say(t, "I'm sorry, " + sender.demonLord() + "!  It feels too good with you inside me!");
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
                    sender.say(t, "I love you, " + sender.demonLord() + "!  I love you so much!");
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
                        sender.say(t, "Aaah...  Your cock is amazing, " + sender.demonLord() + "!");
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
                        sender.say(t, "I'm sorry, " + sender.demonLord() + "!  It feels too good with you inside me!");
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
                        sender.say(t, "I'm... guh... f-fine, " + sender.demonLord() + "!");
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
            w.append(t, "\n" + sender.capitalizedOwnerName() + " is stroking " + receiver.ownerName() + "'s clit");
        else
        if(this == Project.ClitTweaked)
            w.append(t, "\n" + receiver.capitalizedOwnerName() + " is stroking " + sender.ownerName() + "'s clit");
        else
        if(this == Project.SpreadLegs)
            w.append(t, "\n" + sender.capitalizedOwnerName() + " is spreading " + sender.hisHer() + " legs wide apart");
        else
        if(this == Project.PushDown)
            w.append(t, "\n" + sender.OwnerName() + " is atop " + receiver.ownerName());
        else
        if(this == Project.PullDown)
            w.append(t, "\n" + receiver.OwnerName() + " is atop " + sender.ownerName());
        else
        if(this == Project.TieUp)
            w.append(t, "\n" + receiver.OwnerName() + " has been tied up by " + sender.ownerName());
        else
        if(this == Project.BeTied)
            w.append(t, "\n" + sender.OwnerName() + " has been tied up by " + receiver.ownerName());
        else
        if(this == Project.StrokeCock)
            w.append(t, "\n" + sender.OwnerName() + " strokes " + receiver.ownerName() + "'s cock");
        else
        if(this == Project.CockStroked)
            w.append(t, "\n" + receiver.OwnerName() + " strokes " + sender.ownerName() + "'s cock");
        else
        if(this == Project.BeLubricated)
            w.append(t, "\n" + sender.OwnerName() + "'s anus is coated with slick lubricant");
        else
        if(this == Project.VaginalPenetrate)
            w.append(t, "\n" + sender.OwnerName() + " fucks " + receiver.ownerName() + " vaginally");
        else
        if(this == Project.PenetratedVaginally)
            w.append(t, "\n" + receiver.OwnerName() + " fucks " + sender.ownerName() + " vaginally");
        else
        if(this == Project.AnalPenetrate)
            w.append(t, "\n" + sender.OwnerName() + " fucks " + receiver.ownerName() + "'s ass");
        else
        if(this == Project.PenetratedAnally)
            w.append(t, "\n" + receiver.OwnerName() + " fucks " + sender.ownerName() + "'s ass");
        else
        if(this == Project.StripOther)
            w.append(t, "\n" + sender.OwnerName() + " strips off " + receiver.ownerName() + "'s clothes.");
        else
        if(this == Project.Stripped)
            w.append(t, "\n" + receiver.OwnerName() + " strips off " + sender.ownerName() + "'s clothes.");
    }

    public void endActivityFlavor(JTextPane t, WorldState w, Body sender, Body receiver)
    {
        if(this == Project.TweakClit)
            w.append(t, "\n\n" + sender.capitalizedOwnerName() + " takes " + sender.hisHer() + " hand from " + receiver.ownerName() + "'s clit.");
        else
        if(this == Project.ClitTweaked)
            w.append(t, "\n\n" + sender.capitalizedOwnerName() + " moves " + sender.hisHer() + " hips away from " + receiver.ownerName() + "'s hand.");
        else
        if(this == Project.PushDown)
            w.append(t, "\n\n" + sender.capitalizedOwnerName() + " stands, letting " + receiver.ownerName() + " up.");
        else
        if(this == Project.PullDown)
            w.append(t, "\n\n" + sender.OwnerName() + " gets out from under " + receiver.ownerName() + ".");
        else
        if(this == Project.TieUp)
            w.append(t, "\n\n" + sender.OwnerName() + " unties " + receiver.ownerName() + "'s bindings.");
        else
        if(this == Project.StrokeCock)
            w.append(t, "\n\n" + sender.capitalizedOwnerName() + " takes " + sender.hisHer() + " hand from " + receiver.ownerName() + "'s cock.");
        else
        if(this == Project.CockStroked)
            w.append(t, "\n\n" + sender.capitalizedOwnerName() + " moves " + sender.hisHer() + " hips away from " + receiver.ownerName() + "'s hand.");
        else
        if(this == Project.VaginalPenetrate)
            w.append(t, "\n\n" + sender.capitalizedOwnerName() + " pulls " + sender.himHer() + "self out of " + receiver.ownerName() + "'s pussy.");
        else
        if(this == Project.PenetratedVaginally)
            w.append(t, "\n\n" + sender.capitalizedOwnerName() + " lifts " + sender.himHer() + "self until " + receiver.ownerName() + "'s cock slides out of " + sender.hisHer() + " pussy.");
        else
        if(this == Project.AnalPenetrate)
            w.append(t, "\n\n" + sender.OwnerName() + " pulls " + sender.himHer() + "self out of " + receiver.ownerName() + "'s ass.");
        else
        if(this == Project.PenetratedAnally)
            w.append(t, "\n\n" + sender.OwnerName() + " lifts " + sender.himHer() + "self until " + receiver.ownerName() + "'s cock slides out of " + sender.hisHer() + " ass.");
        else
        if(this == Project.StripOther)
            w.append(t, "\n\n" + sender.OwnerName() + " stops removing " + receiver.ownerName() + "'s clothes.");
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
                w.append(t, sender.capitalizedOwnerName() + " begins to caress " + receiver.ownerName() + "'s clit.");
            else
            if(receiver == w.lordBody)
            {
                w.append(t, sender.capitalizedOwnerName() + " ");
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, "loses " + sender.himHer() + "self in the act of rubbing " + receiver.ownerName() + "'s clit, eyes glazed over and drooling with desire.");
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, "reaches eagerly for " + receiver.ownerName() + "'s clit, stroking it with an aggressive firmness that comes just short of being painful.");
                    else
                        w.append(t, "abruptly puts " + sender.hisHer() + " hand against " + receiver.ownerName() + "'s clit and starts rubbing up and down, then fondling it with " + sender.hisHer() + " fingers, then gently tugging on it, using every technique at " + sender.hisHer() + " disposal.");
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, "pokes and prods " + receiver.ownerName() + "'s clit, then gradually gets into a rhythm of stroking it as " + receiver.heShe() + " grows more confident in what " + receiver.heShe() + "'s doing.");
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, "brushes " + sender.hisHer() + " fingers against " + receiver.ownerName() + "'s clit, trailing them up and down, then growing more firm, stroking it in earnest.");
                    else
                        w.append(t, "carefully starts to stroke " + receiver.ownerName() + "'s clit, staring into " + receiver.hisHer() + " eyes to gauge " + receiver.hisHer() + " reaction.");
                } else
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, "hesitantly touches " + receiver.ownerName() + "'s clit, then begins to clumsily stroke it, averting " + sender.hisHer() + " eyes as " + sender.heShe() + " does so.  ");
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, "begins to stroke " + receiver.ownerName() + "'s clit with stiff, mechanical movements.  ");
                    else
                        w.append(t, "brings " + sender.hisHer() + " fingers to " + receiver.ownerName() + "'s clit and begins to stroke it with precise, methodical movements.  ");
                    if(sender.getObedience() > 66)
                        w.append(t, "It's clear that " + sender.heShe() + "'s unfamiliar with this sort of thing, but " + sender.heShe() + "'s doing " + sender.hisHer() + " best for " + receiver.ownerName() + ".");
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, sender.HisHer() + " heart clearly isn't in it.");
                    else
                        w.append(t, sender.HeShe() + " looks sickened by what " + sender.heShe() + "'s doing.");
                }
            }
        } else
        if(this == Project.ClitTweaked)
        {
            if(sender == w.lordBody)
            {
                w.append(t, sender.capitalizedOwnerName() + "'s clit is caressed by " + receiver.ownerName() + ".");
            } else
            {
                w.append(t, sender.capitalizedOwnerName());
                if(sender.getInnocence() > 66)
                {
                    w.append(t, " gasps at the sudden intensity of the stimulation to " + sender.hisHer() + " most sensitive part, and ");
                    if(sender.getDeviancy() > 66)
                        w.append(t, sender.hisHer() + " eyes glaze over as " + sender.heShe() + " loses the ability to think of anything other than feeling even better.  ");
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, sender.hisHer() + " hips jerk wildly, seemingly caught between jerking away and pushing themselves against " + receiver.ownerName() + "'s fingers.  ");
                    else
                        w.append(t, sender.heShe() + " reflexively tries to jerk " + sender.hisHer() + " hips away.  ");
                } else
                if(sender.getInnocence() > 33)
                {
                    w.append(t, " feels jolts of pleasure shooting into " + sender.hisHer() + " lower tummy");
                    if(sender.getDeviancy() > 66)
                        w.append(t, ", and " + sender.heShe() + " moans helplessly, " + sender.hisHer() + " well-trained body eagerly submitting itself to " + receiver.ownerName() + "'s touch.  ");
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, ", and it's a struggle for " + sender.himHer() + " to hold onto " + sender.hisHer() + " reason.  ");
                    else
                        w.append(t, ", even as " + sender.heShe() + " tries to ignore them.  ");
                } else
                {
                    w.append(t, " knew this was coming, but " + sender.heShe() + " still ");
                    if(sender.getDeviancy() > 66)
                        w.append(t, "squirms and cries out, unable to maintain any self-control in the face of the pleasure " + sender.heShe() + "'s come to crave.  ");
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, "gasps softly at the waves of pleasure that begin to wash over " + sender.hisHer() + " lower body.  ");
                    else
                        w.append(t, "flinches and grimaces, uncomfortable with the intensity of the sensations.  ");
                }
                if(receiver == w.lordBody)
                    if(sender.getDeviancy() > 66)
                    {
                        if(sender.getObedience() > 66)
                            w.append(t, sender.HisHer() + " body has grown quite sensitive, but it's even more sensitive than usual when " + sender.heShe() + " knows " + sender.heShe() + "'s being touched by " + receiver.ownerName() + ".");
                        else
                        if(sender.getObedience() > 33)
                            w.append(t, "Soon, " + sender.heShe() + "'s completely consumed in " + sender.hisHer() + " efforts to hump " + receiver.ownerName() + "'s hand.");
                        else
                            w.append(t, sender.HeShe() + " tries to resist, but " + sender.hisHer() + " body has grown so sensitive that the lightest touch to " + sender.hisHer() + " weak spots is enough to subdue " + sender.himHer() + ".");
                    } else
                    if(sender.getDeviancy() > 33)
                    {
                        if(sender.getObedience() > 66)
                            w.append(t, "Even more than the physical stimulation itself, " + sender.ownerName() + " is ecstatic that " + receiver.ownerName() + " is pleasuring " + sender.himHer() + " directly.");
                        else
                        if(sender.getObedience() > 33)
                            w.append(t, sender.HeShe() + "'s content to let " + receiver.ownerName() + " pleasure " + sender.himHer() + ".");
                        else
                            w.append(t, sender.HeShe() + " has a hard time remembering that " + sender.heShe() + "'s supposed to hate the Demon Lord.");
                    } else
                    if(sender.getObedience() > 66)
                        w.append(t, sender.HeShe() + " endures this for " + receiver.ownerName() + "'s sake, hoping that " + receiver.ownerName() + " will enjoy playing with " + sender.hisHer() + " body.");
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, sender.HeShe() + " doesn't make eye contact with " + receiver.ownerName() + ", glancing off to the side.");
                    else
                        w.append(t, sender.HeShe() + " glares at " + receiver.ownerName() + ", not appreciating it in the slightest.");
            }
        } else
        if(this == Project.SpreadLegs)
        {
            if(sender != w.lordBody)
                w.append(t, sender.ownerName() + " spreads " + sender.hisHer() + " legs wide apart, eager to be pleasured.");
        } else
        if(this == Project.Praise)
        {
            if(sender != w.lordBody && receiver == w.lordBody)
                if(sender.getObedience() > 66)
                {
                    if(sender.getConfidence() > 66)
                        w.append(t, sender.OwnerName() + " repeatedly, insistently offers to do anything at all that " + receiver.ownerName() + " desires of " + sender.himHer() + ".");
                    else
                    if(sender.getConfidence() > 33)
                        w.append(t, sender.OwnerName() + " voices " + sender.hisHer() + " admiration for " + receiver.ownerName() + ", describing all " + receiver.hisHer() + " great qualities at length.");
                    else
                        w.append(t, sender.OwnerName() + " viciously insults " + sender.himHer() + "self, offering " + sender.hisHer() + " body to " + receiver.ownerName() + " in order to make up for " + sender.hisHer() + " own failures.");
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.getDeviancy() > 66)
                        w.append(t, sender.OwnerName() + " eagerly lavishes praise and encouragement on " + receiver.ownerName() + ", hoping to be rewarded with pleasure.");
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, sender.OwnerName() + " encourages " + receiver.ownerName() + " to do whatever " + receiver.heShe() + "'d like with " + sender.himHer() + ".");
                    else
                        w.append(t, sender.OwnerName() + " humbly debases " + sender.himHer() + "self and praises " + receiver.ownerName() + ", but the quaver in " + sender.hisHer() + " voice betrays the fact that " + sender.heShe() + "'s doing so out of fear rather than because " + sender.heShe() + " actually believes " + sender.hisHer() + " own words.");
                } else
                if(sender.getInnocence() > 66)
                    w.append(t, sender.OwnerName() + " starts to praise " + receiver.ownerName() + " before " + sender.heShe() + " remembers that they're supposed to be enemies.");
                else
                if(sender.getInnocence() > 33)
                    w.append(t, sender.OwnerName() + " grudgingly compliments " + receiver.ownerName() + ", though " + sender.heShe() + " isn't happy about it.");
                else
                    w.append(t, sender.OwnerName() + " acts like " + sender.heShe() + "'s coming around to " + receiver.ownerName() + "'s side, giving voice to some compliments, but " + sender.heShe() + "'s actually just hoping to manipulate " + receiver.himHer() + ".");
        } else
        if(this == Project.Insult)
        {
            if(sender != w.lordBody && receiver == w.lordBody)
                if(sender.getObedience() > 66)
                {
                    if(sender.getINJULevel() >= 2)
                        w.append(t, sender.OwnerName() + " is growing too tired to serve " + receiver.ownerName() + ", and " + sender.heShe() + " desperately tries to justify " + sender.hisHer() + " own weakness.");
                    else
                    if(sender.getHATELevel() >= 1)
                        w.append(t, sender.OwnerName() + " is becoming agitated, and while " + sender.heShe() + " adores " + receiver.ownerName() + " too much to confront " + receiver.himHer() + " directly, " + sender.hisHer() + " feelings still come through.");
                    else
                        w.append(t, sender.OwnerName() + "'s psyche, fragile after being trained so thoroughly by the Demon Lord, begins to crack under the strain.");
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.getINJULevel() >= 2)
                        w.append(t, sender.OwnerName() + " is getting tired, and " + sender.heShe() + " lets " + receiver.ownerName() + " knows that " + sender.heShe() + " wants to stop this.");
                    else
                    if(sender.getHATELevel() >= 1)
                        w.append(t, sender.OwnerName() + " is growing annoyed with " + receiver.ownerName() + ", and " + sender.heShe() + " lets " + receiver.himHer() + " know it in no uncertain terms.");
                    else
                        w.append(t, sender.OwnerName() + " insults " + receiver.ownerName() + ", hoping to get a reaction.");
                } else
                if(sender.getINJULevel() >= 2)
                    w.append(t, sender.OwnerName() + " is too tired to resist physically, but " + sender.heShe() + " still verbally attacks " + receiver.ownerName() + ".");
                else
                if(sender.getHATELevel() >= 1)
                    w.append(t, sender.OwnerName() + " already hates " + receiver.ownerName() + ", but " + sender.heShe() + "'s even angrier than usual at being forced into this, and " + sender.heShe() + " vents " + sender.hisHer() + " frustration with a stream of insults");
                else
                    w.append(t, sender.OwnerName() + " takes the chance to insult " + receiver.ownerName() + " to " + receiver.hisHer() + " face.");
        } else
        if(this == Project.PushDown)
        {
            if(sender == w.lordBody)
                w.append(t, sender.OwnerName() + " looms over " + receiver.ownerName() + ".");
            else
            if(sender.getDeviancy() > 66)
            {
                if(sender.getConfidence() > 66)
                    w.append(t, sender.OwnerName() + " roughly pushes " + receiver.ownerName() + " down onto " + receiver.hisHer() + " back, panting with barely-restrained desire as " + sender.heShe() + " takes " + sender.hisHer() + " place atop " + receiver.himHer() + ".");
                else
                if(sender.getConfidence() > 33)
                    w.append(t, sender.OwnerName() + " practically tackles " + receiver.ownerName() + " down onto the floor, overflowing with lust.");
                else
                    w.append(t, sender.OwnerName() + "'s lust has made " + sender.himHer() + " far more bold than usual, and " + sender.heShe() + " eagerly pushes " + receiver.ownerName() + " down onto " + receiver.hisHer() + " back.");
            } else
            if(sender.getDeviancy() > 33)
            {
                if(sender.getInnocence() > 66)
                    w.append(t, "Overcome by " + sender.hisHer() + " growing lust, " + sender.ownerName() + " wraps " + sender.hisHer() + " arms around " + receiver.ownerName() + " and kisses " + receiver.himHer() + " repeatedly, then seems surprised to realize that " + sender.heShe() + "'s pushed " + receiver.himHer() + " down to the floor in the process.");
                else
                if(sender.getInnocence() > 33)
                    w.append(t, sender.OwnerName() + " embraces " + receiver.ownerName() + ", guiding " + receiver.himHer() + " down onto " + receiver.hisHer() + " back.");
                else
                    w.append(t, "With " + sender.hisHer() + " eyes locked onto " + receiver.ownerName() + "'s " + receiver.mainOrgan() + ", " + sender.ownerName() + " urges " + receiver.himHer() + " to lay down on " + receiver.hisHer() + " back, then climbs atop " + receiver.himHer() + ".");
            } else
            if(sender.getObedience() > 66)
                w.append(t, sender.OwnerName() + " gently guides " + receiver.ownerName() + " onto " + receiver.hisHer() + " back, then lays atop " + receiver.himHer() + ", taking deep breaths and trying to ready " + sender.himHer() + "self to serve " + sender.theDemonLord() + ".");
            else
            if(sender.getObedience() > 33)
                w.append(t, sender.OwnerName() + " pushes " + receiver.ownerName() + " down onto " + receiver.hisHer() + " back, but hesitates before going further, uncertain about how to proceed.");
            else
                w.append(t, "With an annoyed expression on " + sender.hisHer() + " face, " + sender.ownerName() + " pushes " + receiver.ownerName() + " down onto " + receiver.hisHer() + " back without a hint of sensuality.");
        } else
        if(this == Project.PullDown)
        {
            if(sender == w.lordBody)
                w.append(t, sender.OwnerName() + " is laid down under " + receiver.ownerName() + ".");
            else
            if(sender.getDeviancy() > 66)
            {
                if(sender.getConfidence() > 66)
                    w.append(t, sender.OwnerName() + " forcefully pulls " + receiver.ownerName() + " atop " + sender.himHer() + ", aggressive even in " + sender.hisHer() + " submission.");
                else
                if(sender.getConfidence() > 33)
                    w.append(t, sender.OwnerName() + " tangles " + sender.hisHer() + " limbs around " + receiver.ownerName() + ", bringing them both down to the floor together with " + receiver.ownerName() + " atop " + sender.himHer() + ".");
                else
                    w.append(t, sender.OwnerName() + " lays down on " + sender.hisHer() + " back, weakly trying to pull " + receiver.ownerName() + " down with " + sender.himHer() + ".  " + sender.HeShe() + " trembles with desire as " + sender.heShe() + " looks up at " + receiver.ownerName() + ".");
            } else
            if(sender.getDeviancy() > 33)
            {
                if(sender.getDignity() > 66)
                    w.append(t, sender.OwnerName() + " lays down under " + receiver.ownerName() + ", trying to act as though " + sender.heShe() + "'s doing " + receiver.himHer() + " a favor, but " + sender.ownerName() + "'s lustful panting betrays how much " + sender.heShe() + " wants to be taken.");
                else
                if(sender.getDignity() > 33)
                    w.append(t, sender.OwnerName() + " eagerly pulls " + receiver.ownerName() + " down atop " + sender.himHer() + ", smiling in anticipation of what's to come.");
                else
                    w.append(t, sender.OwnerName() + " begs " + receiver.ownerName() + " to take " + sender.himHer() + ", positioning " + sender.himHer() + "self underneath " + receiver.himHer() + ".");
            } else
            if(sender.getObedience() > 66)
                w.append(t, sender.OwnerName() + " submissively presents " + sender.himHer() + "self to " + receiver.ownerName() + ", layin on " + sender.hisHer() + " back and squirming nervously as " + sender.heShe() + " anticipates how " + sender.heShe() + "'ll be used.");
            else
            if(sender.getObedience() > 33)
                w.append(t, sender.OwnerName() + " lays down for " + receiver.ownerName() + ", but " + sender.heShe() + " refuses to meet " + receiver.hisHer() + " eyes, still uncertain how " + sender.heShe() + " feels about sex with the Demon Lord.");
            else
                w.append(t, sender.OwnerName() + " angrily lays down, glaring up at " + receiver.ownerName() + ".");
        } else
        if(this == Project.Escape)
        {
            if(sender != w.lordBody)
                if(sender.getObedience() > 66)
                {
                    if(sender.getMorality() > 66)
                        w.append(t, sender.OwnerName() + " cries out in despair as " + sender.heShe() + " turns and crawls away from " + receiver.ownerName() + ", already consumed by self-loathing for " + sender.hisHer() + " failure to properly serve " + sender.theDemonLord() + ".");
                    else
                    if(sender.getMorality() > 33)
                        w.append(t, sender.OwnerName() + " abruptly starts struggling to escape " + receiver.ownerName() + ", and it's only after " + sender.heShe() + "'s free that " + sender.heShe() + " hangs " + sender.hisHer() + " head in shame for being so disobedient.");
                    else
                        w.append(t, sender.OwnerName() + " shoves " + receiver.ownerName() + " and scrambles away, briefly forgetting " + sender.hisHer() + " devotion in the face of " + sender.hisHer() + " intense emotions.");
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, sender.OwnerName() + " cries out in panic and shoves " + receiver.ownerName() + " away.  Only a moment later does " + sender.heShe() + " realize what " + sender.heShe() + "'s done, and " + sender.heShe() + " cowers in fear of punishment.");
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, "Apologizing profusely, " + sender.ownerName() + " disentangles " + sender.himHer() + "self from " + receiver.ownerName() + " and takes a few steps backward.");
                    else
                        w.append(t, sender.OwnerName() + " clearly states that " + sender.heShe() + "'s done with this and moves to free " + sender.himHer() + "self.  The quaver in " + sender.hisHer() + " voice is the only thing that betrays " + sender.hisHer() + " worries about the consequences of rejecting " + sender.theDemonLord() + ".");
                } else
                if(sender.getConfidence() > 66)
                    w.append(t, sender.OwnerName() + " starts to viciously beat " + receiver.ownerName() + ", remaining hostile even after " + sender.heShe() + " frees " + sender.himHer() + "self.");
                else
                if(sender.getConfidence() > 33)
                    w.append(t, sender.OwnerName() + ", overwhelmed by " + sender.hisHer() + " hatred for the Demon Lord, starts struggling to get away before " + sender.heShe() + " knows what " + sender.heShe() + "'s doing.  However, " + sender.heShe() + " stands tall afterward, regaining " + sender.hisHer() + " poise as if daring " + receiver.ownerName() + " to punish " + sender.himHer() + ".");
                else
                    w.append(t, sender.OwnerName() + " flails in panic, then makes an effort to run away.");
        } else
        if(this == Project.StopActing)
        {
            if(sender != w.lordBody)
                if(sender.getObedience() > 66)
                {
                    if(sender.getMorality() > 66)
                        w.append(t, sender.OwnerName() + " confesses that " + sender.heShe() + "'s grown too tired to keep servicing " + receiver.ownerName() + ", then eagerly proposes that " + receiver.ownerName() + " punish " + sender.himHer() + " for " + sender.hisHer() + " weakness.");
                    else
                    if(sender.getMorality() > 33)
                        w.append(t, sender.OwnerName() + " keeps trying to service " + receiver.ownerName() + " until " + sender.hisHer() + " own body starts to grow too exhausted to move.  Eventually, " + sender.heShe() + " realizes that " + sender.heShe() + "'s too tired to effectively please " + receiver.ownerName() + " anymore.");
                    else
                        w.append(t, sender.OwnerName() + " is sincere about wanting to service " + receiver.ownerName() + ", but " + sender.heShe() + " begins getting lazier and lazier with " + sender.hisHer() + " movements until " + sender.heShe() + "'s not actually making " + receiver.ownerName() + " feel good at all.");
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.getDeviancy() > 66)
                        w.append(t, sender.OwnerName() + " heaves a satisfied sigh, " + sender.hisHer() + " tremendous lust finally satisfied.  " + sender.HeShe() + " doesn't seem interested in continuing for now.");
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, sender.OwnerName() + " gradually starts to grow less and less interested in pleasuring " + receiver.ownerName() + ", and finally " + sender.heShe() + " stops completely.");
                    else
                        w.append(t, sender.OwnerName() + " starts to get distracted and make excuses for why " + sender.heShe() + " should be allowed to leave, and " + sender.heShe() + " stops trying to pleasure " + receiver.ownerName() + ".");
                } else
                if(sender.getInnocence() > 66)
                    w.append(t, sender.OwnerName() + " abruptly seems to remember that " + receiver.ownerName() + " is the enemy, and " + sender.heShe() + " refuses to continue this any longer.");
                else
                if(sender.getInnocence() > 33)
                    w.append(t, sender.OwnerName() + " gets angry and refuses to cooperate further.  " + sender.HeShe() + " turns " + sender.hisHer() + " head away, refusing to even look at " + receiver.ownerName() + ".");
                else
                    w.append(t, sender.OwnerName() + ", deciding that " + sender.heShe() + "'s cooperated long enough, halts " + sender.hisHer() + " movements and glares at " + receiver.ownerName() + " as if daring " + receiver.himHer() + " to punish " + sender.himHer() + ".");
        } else
        if(this == Project.TieUp)
        {
            if(sender == w.lordBody)
                w.append(t, sender.OwnerName() + " ties down " + receiver.ownerName() + "'s arms and legs, leaving " + receiver.himHer() + " helpless.");
        } else
        if(this == Project.BeTied)
        {
            if(sender != w.lordBody)
                if(sender.getObedience() > 66)
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, sender.OwnerName() + "'s eyes glaze over with pure bliss as " + sender.heShe() + " enjoys entrusting " + sender.hisHer() + " body to " + receiver.ownerName() + ".");
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, sender.OwnerName() + " doesn't resist in the slightest, happy to let " + receiver.ownerName() + " enjoy " + sender.hisHer() + " body however " + receiver.heShe() + " wishes.");
                    else
                        w.append(t, sender.OwnerName() + " happily cooperates, holding " + sender.hisHer() + " limbs as " + sender.heShe() + "'s directed in order to help speed the process.");
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.getConfidence() > 66)
                        w.append(t, sender.OwnerName() + " holds " + sender.hisHer() + " head high as " + sender.heShe() + "'s tied up, refusing to let " + sender.himHer() + "self show fear.");
                    else
                    if(sender.getConfidence() > 33)
                        w.append(t, sender.OwnerName() + " flinches in trepidation, imagining what " + receiver.ownerName() + " will be doing to " + sender.himHer() + " next.");
                    else
                        w.append(t, sender.OwnerName() + " starts to panic, but " + sender.heShe() + "'s too frightened to actively resist.");
                } else
                if(sender.getDeviancy() > 66)
                    w.append(t, sender.OwnerName() + " tries to resist, but " + sender.hisHer() + " lustful body betrays " + sender.himHer() + ", and by the time " + sender.heShe() + "'s stopped daydreaming about what kind of sexual punishment awaits " + sender.himHer() + ", " + sender.heShe() + "'s already been tied up.");
                else
                if(sender.getDeviancy() > 33)
                {
                    w.append(t, sender.OwnerName() + " puts up a bit of resistance.  However, " + sender.heShe() + "'s weaker than " + sender.heShe() + " should be, and ");
                    if(sender.parts[PENIS] > 0)
                        w.append(t, "the stiffness between " + sender.hisHer() + " legs");
                    else
                        w.append(t, "the wetness on " + sender.hisHer() + " thighs");
                    w.append(t, " shows that a part of " + sender.himHer() + " is eager to be punished.");
                } else
                {
                    w.append(t, sender.OwnerName() + " fights back with all " + sender.hisHer() + " might, but " + sender.heShe() + " can't win against " + receiver.ownerName() + ".  Soon, there's nothing " + sender.heShe() + " can do but rock back and forth while growling at " + sender.hisHer() + " captor.");
                }
        } else
        if(this == Project.StrokeCock)
        {
            if(sender == w.lordBody)
            {
                w.append(t, sender.OwnerName() + " begins to pump " + sender.hisHer() + " hand up and down " + receiver.ownerName() + "'s cock.");
            } else
            {
                w.append(t, sender.capitalizedOwnerName() + " ");
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, "loses " + sender.himHer() + "self in the act of running " + sender.hisHer() + " hand up and down " + receiver.ownerName() + "'s cock, eyes glazed over and drooling with desire.");
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, "reaches eagerly for " + receiver.ownerName() + "'s cock, pumping it up and down with an aggressive firmness that comes just short of being painful.");
                    else
                        w.append(t, "abruptly wraps " + sender.hisHer() + " fingers around " + receiver.ownerName() + "'s cock and starts stroking it gently, then fondling the tip, then gently tugging on it, using every technique at " + sender.hisHer() + " disposal.");
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, "tugs and jerks " + receiver.ownerName() + "'s cock, then gradually gets into a rhythm of stroking it as " + receiver.heShe() + " grows more confident in what " + receiver.heShe() + "'s doing.");
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, "brushes " + sender.hisHer() + " fingers against " + receiver.ownerName() + "'s cock, trailing them up and down, then growing more firm, stroking it in earnest.");
                    else
                        w.append(t, "carefully starts to stroke " + receiver.ownerName() + "'s cock, staring into " + receiver.hisHer() + " eyes to gauge " + receiver.hisHer() + " reaction.");
                } else
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, "hesitantly touches " + receiver.ownerName() + "'s cock, then begins to clumsily stroke it, averting " + sender.hisHer() + " eyes as " + sender.heShe() + " does so.  ");
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, "begins to stroke " + receiver.ownerName() + "'s cock with stiff, mechanical movements.  ");
                    else
                        w.append(t, "brings " + sender.hisHer() + " hand to " + receiver.ownerName() + "'s cock and begins to stroke it with precise, methodical movements.  ");
                    if(sender.getObedience() > 66)
                        w.append(t, "It's clear that " + sender.heShe() + "'s unfamiliar with this sort of thing, but " + sender.heShe() + "'s doing " + sender.hisHer() + " best for " + receiver.ownerName() + ".");
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, sender.HisHer() + " heart clearly isn't in it.");
                    else
                        w.append(t, sender.HeShe() + " looks sickened by what " + sender.heShe() + "'s doing.");
                }
            }
        } else
        if(this == Project.CockStroked)
        {
            if(sender == w.lordBody)
            {
                w.append(t, sender.OwnerName() + "'s cock pulses under " + receiver.ownerName() + "'s fingers.");
            } else
            {
                w.append(t, sender.capitalizedOwnerName());
                if(sender.getInnocence() > 66)
                {
                    w.append(t, " gasps at the sudden intensity of the stimulation to " + sender.hisHer() + " most sensitive part, and ");
                    if(sender.getDeviancy() > 66)
                        w.append(t, sender.hisHer() + " eyes glaze over as " + sender.heShe() + " loses the ability to think of anything other than feeling even better.  ");
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, sender.hisHer() + " hips jerk wildly, seemingly caught between jerking away and pushing further into " + receiver.ownerName() + "'s hand.  ");
                    else
                        w.append(t, sender.heShe() + " reflexively tries to jerk " + sender.hisHer() + " hips away.  ");
                } else
                if(sender.getInnocence() > 33)
                {
                    w.append(t, " feels jolts of pleasure shooting through " + sender.hisHer() + " shaft");
                    if(sender.getDeviancy() > 66)
                        w.append(t, ", and " + sender.heShe() + " moans helplessly, " + sender.hisHer() + " well-trained body eagerly submitting itself to " + receiver.ownerName() + "'s touch.  ");
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, ", and it's a struggle for " + sender.himHer() + " to hold onto " + sender.hisHer() + " reason.  ");
                    else
                        w.append(t, ", even as " + sender.heShe() + " tries to ignore them.  ");
                } else
                {
                    w.append(t, " knew this was coming, but " + sender.heShe() + " still ");
                    if(sender.getDeviancy() > 66)
                        w.append(t, "squirms and cries out, unable to maintain any self-control in the face of the pleasure " + sender.heShe() + "'s come to crave.  ");
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, "gasps softly at the waves of pleasure that begin to wash over " + sender.hisHer() + " lower body.  ");
                    else
                        w.append(t, "flinches and grimaces, uncomfortable with the intensity of the sensations.  ");
                }
                if(receiver == w.lordBody)
                    if(sender.getDeviancy() > 66)
                    {
                        if(sender.getObedience() > 66)
                            w.append(t, sender.HisHer() + " body has grown quite sensitive, but it's even more sensitive than usual when " + sender.heShe() + " knows " + sender.heShe() + "'s being touched by " + receiver.ownerName() + ".");
                        else
                        if(sender.getObedience() > 33)
                            w.append(t, "Soon, " + sender.heShe() + "'s completely consumed in " + sender.hisHer() + " efforts to hump " + receiver.ownerName() + "'s hand.");
                        else
                            w.append(t, sender.HeShe() + " tries to resist, but " + sender.hisHer() + " body has grown so sensitive that the lightest touch to " + sender.hisHer() + " weak spots is enough to subdue " + sender.himHer() + ".");
                    } else
                    if(sender.getDeviancy() > 33)
                    {
                        if(sender.getObedience() > 66)
                            w.append(t, "Even more than the physical stimulation itself, " + sender.ownerName() + " is ecstatic that " + receiver.ownerName() + " is pleasuring " + sender.himHer() + " directly.");
                        else
                        if(sender.getObedience() > 33)
                            w.append(t, sender.HeShe() + "'s content to let " + receiver.ownerName() + " pleasure " + sender.himHer() + ".");
                        else
                            w.append(t, sender.HeShe() + " has a hard time remembering that " + sender.heShe() + "'s supposed to hate the Demon Lord.");
                    } else
                    if(sender.getObedience() > 66)
                        w.append(t, sender.HeShe() + " endures this for " + receiver.ownerName() + "'s sake, hoping that " + receiver.ownerName() + " will enjoy playing with " + sender.hisHer() + " body.");
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, sender.HeShe() + " doesn't make eye contact with " + receiver.ownerName() + ", glancing off to the side.");
                    else
                        w.append(t, sender.HeShe() + " glares at " + receiver.ownerName() + ", not appreciating it in the slightest.");
            }
        } else
        if(this == Project.Lubricate)
        {
            if(sender == w.lordBody)
                w.append(t, sender.OwnerName() + " gathers a generous amount of slippery lubricant on one finger, then uses it to coat " + receiver.ownerName() + "'s anus.");
        } else
        if(this == Project.BeLubricated)
        {
            if(sender != w.lordBody)
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getObedience() > 66)
                        w.append(t, sender.OwnerName() + " moans and spreads " + sender.hisHer() + " legs as wide as " + sender.heShe() + " can, eager for more than just a finger.");
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, sender.OwnerName() + " looks nervous, but also eager.  " + sender.HeShe() + " grins down at " + sender.himHer() + "self, imagining what will be done to " + sender.himHer() + " next.");
                    else
                        w.append(t, sender.OwnerName() + " tries to keep acting tough, but " + sender.heShe() + " breaks down into a stream of pitiful moans and halfhearted protests as the light stimulation is enough to break " + sender.hisHer() + " concentration.");
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getDignity() > 66)
                        w.append(t, sender.OwnerName() + " inhales sharply, but doesn't show any other sign that " + sender.heShe() + "'s feeling anything from this.");
                    else
                    if(sender.getDignity() > 33)
                        w.append(t, sender.OwnerName() + " squirms and blushes, clearly a bit ashamed at being excited by the stimulation back there.");
                    else
                        w.append(t, sender.OwnerName() + " groans and reflexively tries to buck " + sender.hisHer() + " hips down onto the finger.");
                } else
                if(sender.getInnocence() > 66)
                    w.append(t, sender.OwnerName() + " squirms in discomfort at the strange feeling in a place " + sender.heShe() + " doesn't even mentally associate with sex.");
                else
                if(sender.getInnocence() > 33)
                    w.append(t, sender.OwnerName() + "'s eyes go wide and " + sender.heShe() + " tries to look down at what's happening down there.");
                else
                    w.append(t, sender.OwnerName() + " shudders at the unpleasant sensation, then tries to relax and accept it.");
        } else
        if(this == Project.VaginalPenetrate)
        {
            if(Project.PushDown.isInProgress(sender, receiver))
            {
                if(sender == w.lordBody)
                    w.append(t, sender.OwnerName() + " puts the tip of " + sender.hisHer() + " cock against " + receiver.ownerName() + "'s pussy, then thrusts inside.");
                else
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getConfidence() > 66)
                        w.append(t, sender.OwnerName() + " eagerly pins " + receiver.ownerName() + " down and shoves " + sender.hisHer() + " shaft into " + receiver.ownerName() + "'s pussy, immediately setting a rapid pace of thrusting in and out.  It's a struggle to keep moving steadily with the flashes of ecstasy shooting through " + sender.himHer() + ", but " + sender.ownerName() + " is determined to keep going.");
                    else
                    if(sender.getConfidence() > 33)
                        w.append(t, sender.OwnerName() + " starts to slowly push " + sender.hisHer() + " cock into " + receiver.ownerName() + ", but the surge of pleasure from having just the tip inside overwhelms " + sender.hisHer() + " reason.  " + sender.HeShe() + " jerks " + sender.hisHer() + " hips forward, burying " + sender.himHer() + "self inside " + receiver.ownerName() + "'s pussy, then pulls halfway out, then pushes back in, hips jerking wildly as intense pleasure surges through " + sender.himHer() + " with every movement.");
                    else
                        w.append(t, sender.OwnerName() + "'s timid nature seems to vanish entirely, and " + sender.heShe() + " starts to ram " + sender.hisHer() + " hips forward with wild abandon, hammering in and out of " + receiver.ownerName() + "'s pussy.");
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, sender.OwnerName() + " pushes " + sender.hisHer() + " cock into " + receiver.ownerName() + "'s pussy, and the moment " + sender.heShe() + " feels " + receiver.hisHer() + " folds squeezing down on " + sender.himHer() + ", " + sender.heShe() + " loses the ability to think of anything but the pleasure.  Moaning softly, " + sender.heShe() + " starts thrusting in and out, acting on pure instinct.");
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, sender.OwnerName() + " starts to fuck " + receiver.ownerName() + "'s pussy, but the pleasure of " + receiver.hisHer() + " folds squeezing down on " + sender.himHer() + " cock gives " + sender.ownerName() + " pause.  " + sender.HeShe() + " gasps, taking a moment to catch " + sender.hisHer() + " breath, then continues more slowly, enjoying the warm wetness around " + sender.hisHer() + " shaft.");
                    else
                        w.append(t, sender.OwnerName() + " gradually pushes " + sender.hisHer() + " cock into " + receiver.ownerName() + "'s pussy, inch-by-inch, then withdraws it just as carefully.  " + sender.HeShe() + " moves slowly, feeling out which spots feel better for " + sender.himHer() + " and which seem to get more of a reaction from " + receiver.ownerName() + ", and only then starts to move more quickly, getting into a rhythm.");
                } else
                if(sender.getObedience() > 66)
                    w.append(t, sender.OwnerName() + " eagerly thrusts " + sender.hisHer() + " cock inside " + receiver.ownerName() + "'s pussy, ecstatic to be joined with " + receiver.himHer() + ".  " + sender.HeShe() + " moves unselfishly, trying to hit all of " + receiver.ownerName() + "'s sensitive parts without any regard for " + sender.hisHer() + " own pleasure.");
                else
                if(sender.getObedience() > 33)
                    w.append(t, sender.OwnerName() + " presses the tip of " + sender.hisHer() + " cock against " + receiver.ownerName() + "'s pussy, hesitates for a moment, and then finally pushes it inside.  " + sender.HeShe() + " gasps and shudders at the pleasure, far beyond what " + sender.heShe() + " was prepared for, and then cautiously starts to move " + sender.hisHer() + " hips forward and back.");
                else
                    w.append(t, sender.OwnerName() + "'s angry demeanor fades for just a moment as " + sender.heShe() + " thrusts into " + receiver.ownerName() + " and feels the intense pleasure of " + receiver.hisHer() + " folds squeezing " + sender.himHer() + ".  But then " + sender.heShe() + " recovers and starts to move violently in and out, as if stabbing " + receiver.ownerName() + " with a weapon.");
            } else
            if(sender == w.lordBody)
                w.append(t, sender.OwnerName() + "'s cock pushes all the way into " + receiver.ownerName() + "'s depths.");
            else
            if(sender.getDeviancy() > 66)
            {
                if(sender.getInnocence() > 66)
                    w.append(t, sender.OwnerName() + "'s whole body goes slack except for " + sender.hisHer() + " hips desperately trying to thrust deeper into " + receiver.himHer() + ".  " + sender.HisHer() + " eyes roll and " + sender.hisHer() + " tongue hangs out.");
                else
                if(sender.getInnocence() > 33)
                    w.append(t, "The feeling of " + receiver.ownerName() + " sliding down to the base of " + sender.hisHer() + " shaft is enough to make " + sender.ownerName() + " cry out and lose " + sender.hisHer() + " sense of reason.  " + sender.HeShe() + " desperately clings to " + receiver.ownerName() + ", hips jerking wildly up and down as " + sender.heShe() + " tries to thrust even deeper into " + receiver.himHer() + ".");
                else
                    w.append(t, "At first, " + sender.ownerName() + " tries to reciprocate with " + sender.hisHer() + " own thrusts.  But it soon becomes clear that " + sender.heShe() + "'s completely at " + receiver.ownerName() + "'s mercy, and all " + sender.heShe() + " can do is moan and jerk " + sender.hisHer() + " hips as " + receiver.ownerName() + " slides up and down " + sender.hisHer() + " shaft.");
            } else
            if(sender.getDeviancy() > 33)
            {
                if(sender.getDignity() > 66)
                    w.append(t, sender.OwnerName() + " tries to hide just how good it feels, but when " + receiver.ownerName() + " clenches around " + sender.himHer() + ", " + sender.heShe() + " gasps and reflexively bucks " + sender.hisHer() + " hips.  After that, " + sender.heShe() + " has a harder and harder time suppressing " + sender.hisHer() + " moans.");
                else
                if(sender.getDignity() > 33)
                    w.append(t, sender.OwnerName() + " lays back and loses " + sender.himHer() + "self in the feeling of " + receiver.ownerName() + " sliding up and down " + sender.hisHer() + " length, overwhelmed by the intensity of the sensations.");
                else
                    w.append(t, sender.OwnerName() + " gasps with pleasure, reflexively embracing " + receiver.ownerName() + " and thrusting " + sender.hisHer() + " own hips in time with " + receiver.ownerName() + "'s movements.");
            } else
            if(sender.getObedience() > 66)
                w.append(t, sender.OwnerName() + " winces and struggles not to cum right away, wanting to ensure that " + sender.heShe() + " stays hard for " + receiver.ownerName() + " as long as possible.");
            else
            if(sender.getObedience() > 33)
                w.append(t, sender.OwnerName() + " tries to relax and enjoy it, but even as " + receiver.ownerName() + " slides up and down " + sender.hisHer() + " shaft, " + sender.ownerName() + " can't quite shake off the fear that this pleasure is just meant to make " + sender.himHer() + " let " + sender.hisHer() + " guard down.");
            else
                w.append(t, sender.OwnerName() + " struggles to keep glaring at " + receiver.ownerName() + ", wincing slightly every time " + receiver.ownerName() + " squeezes the base of " + sender.hisHer() + " shaft and breaks " + sender.hisHer() + " concentration.");
        } else
        if(this == Project.PenetratedVaginally)
        {
            if(Project.PushDown.isInProgress(sender, receiver))
            {
                if(sender == w.lordBody)
                    w.append(t, sender.OwnerName() + " mounts " + receiver.ownerName() + " and lowers " + sender.himHer() + "self until " + sender.hisHer() + " pussy envelops " + receiver.ownerName() + "'s cock.");
                else
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getObedience() > 66)
                        w.append(t, sender.OwnerName() + " eagerly impales " + sender.hisHer() + " pussy on " + receiver.ownerName() + "'s upright cock, sliding " + sender.himHer() + "self up and down with manic energy while grinning at " + receiver.himHer() + ".");
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, sender.OwnerName() + " quickly lowers " + sender.himHer() + "self onto " + receiver.ownerName() + "'s cock, then immediately begins to bounce on " + receiver.hisHer() + " lap, searching for the precise angle where it pokes just the right spot inside " + sender.hisHer() + " pussy.");
                    else
                        w.append(t, sender.OwnerName() + " tries to resist " + sender.hisHer() + " urges, but this close to " + receiver.ownerName() + "'s cock, " + sender.heShe() + " can't stop " + sender.himHer() + "self from grinding against it.  Then, before " + sender.heShe() + " realizes it, " + sender.heShe() + "'s taken it into " + sender.hisHer() + " pussy.  The surge of pleasure destroys the last of " + sender.hisHer() + " reason, and " + sender.heShe() + " starts moving in earnest.");
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getConfidence() > 66)
                        w.append(t, sender.OwnerName() + " pins " + receiver.ownerName() + " down and takes " + receiver.ownerName() + "'s cock into " + sender.hisHer() + " pussy.  " + sender.HeShe() + " grimaces slightly at the sudden insertion, but wastes no time in bucking " + sender.hisHer() + " hips with savage force, causing the discomfort to melt away into pleasure.");
                    else
                    if(sender.getConfidence() > 33)
                        w.append(t, sender.OwnerName() + " straddles " + receiver.ownerName() + ", touching " + sender.hisHer() + " pussy against the tip of " + receiver.ownerName() + "'s cock, then slowly lowering " + sender.himHer() + "self downward onto it, bit by bit.  " + sender.HeShe() + " gasps and twitches as it bottoms out inside " + sender.himHer() + ".");
                    else
                        w.append(t, sender.OwnerName() + " is nervous about lowering " + sender.himHer() + "self onto " + receiver.ownerName() + "'s cock, but " + sender.heShe() + " gasps with pleasure as it goes inside " + sender.hisHer() + " pussy and immediately hits a sensitive spot.  " + sender.HeShe() + " moans and begins moving " + sender.himHer() + "self up and down with genuine enthusiasm.");
                } else
                if(sender.getDignity() > 66)
                    w.append(t, sender.OwnerName() + " is blushing bright red at having to be the one to lower " + sender.himHer() + "self onto " + receiver.ownerName() + "'s cock, but " + sender.heShe() + " pretends that it doesn't bother " + sender.himHer() + ".  " + sender.HeShe() + " just flinches as it spreads " + sender.hisHer() + " pussy open, then stifles a moan when it goes all the way inside.");
                else
                if(sender.getDignity() > 33)
                    w.append(t, sender.OwnerName() + " delays as long as possible before taking " + receiver.ownerName() + "'s cock into " + sender.hisHer() + " pussy.  " + sender.HeShe() + " tries various angles, slides " + sender.himHer() + "self against it several times, and only then does " + sender.heShe() + " finally lower " + sender.himHer() + "self onto it.  When " + sender.heShe() + " does, " + sender.heShe() + " lets out a little moan, uncomfortable with just how intense the pleasure is.");
                else
                    w.append(t, sender.OwnerName() + " doesn't waste any time before slamming " + sender.hisHer() + " hips down onto " + receiver.ownerName() + "'s cock.  However, " + sender.heShe() + " groans with discomfort at the sudden insertion, and " + sender.heShe() + " needs a few moments to recover before " + sender.heShe() + " can start moving.");
                if(sender.isVVirg())
                    if(sender.getObedience() > 66)
                        w.append(t, "  " + sender.HeShe() + " embraces the pain of " + sender.hisHer() + " first vaginal penetration, ecstatic to have received it from " + receiver.ownerName() + ".");
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, "  " + sender.HeShe() + " looks down at the trickle of blood on " + sender.hisHer() + " thigh with an expression of resignation.");
                    else
                        w.append(t, "  " + sender.HeShe() + " lifts " + sender.himHer() + "self up enough to look down and see the blood on " + receiver.ownerName() + "'s cock, then grits " + sender.hisHer() + " teeth.");
            } else
            {
                if(sender == w.lordBody)
                    w.append(t, sender.OwnerName() + "'s pussy tightens around " + receiver.ownerName() + "'s cock as though trying to milk it dry.");
                else
                if(sender.getObedience() > 66)
                {
                    if(sender.getDeviancy() > 66)
                        w.append(t, sender.OwnerName() + " goes wild with joyful lust, bucking " + sender.hisHer() + " hips wildly and enjoying every moment of being taken by " + receiver.ownerName() + ".");
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, sender.OwnerName() + " tries to help " + receiver.ownerName() + " feel good as well, moving " + sender.hisHer() + " hips and urging " + receiver.himHer() + " deeper inside, but soon the pleasure overwhelms " + sender.himHer() + " and " + sender.heShe() + " can't think of anything but wanting to cum.");
                    else
                        w.append(t, sender.OwnerName() + " bites " + sender.hisHer() + " lip in concentration, trying to buck " + sender.hisHer() + " hips in turn and squeeze down with " + sender.hisHer() + " pussy in order to make it feel as good as possible for " + receiver.ownerName() + ".");
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.getConfidence() > 66)
                        w.append(t, sender.OwnerName() + " doesn't look entirely happy to be on the bottom, but the pleasure of each thrust hitting " + sender.hisHer() + " deepest places soon makes " + sender.himHer() + " forget all about that and start crying out in pleasure.");
                    else
                    if(sender.getConfidence() > 33)
                        w.append(t, "At first, " + sender.ownerName() + " tolerates it with an expression of resignation.  However, as the pleasure builds, " + sender.heShe() + " starts to gasp with passion and then actively move " + sender.hisHer() + " hips.");
                    else
                        w.append(t, sender.OwnerName() + " is too nervous to move much, worried about doing something to displease " + receiver.ownerName() + ".  Whimpering moans of pleasure begin to leak out of " + sender.hisHer() + " throat.");
                } else
                if(sender.getInnocence() > 66)
                    w.append(t, sender.OwnerName() + " tries to kick and scream, but " + sender.hisHer() + " movements only drive " + receiver.ownerName() + " deeper inside " + sender.himHer() + ", and " + sender.hisHer() + " voice takes on a passionate quality.");
                else
                if(sender.getInnocence() > 33)
                    w.append(t, sender.OwnerName() + " attempts to deny " + receiver.ownerName() + " the satisfaction of seeing " + sender.hisHer() + " reactions, but the longer the fucking continues, the more difficult it is.  Soon " + sender.heShe() + "'s reduced to covering " + sender.hisHer() + " face and biting " + sender.hisHer() + " lip to stifle " + sender.hisHer() + " moans.");
                else
                    w.append(t, sender.OwnerName() + " glares up at " + receiver.ownerName() + ", and " + sender.hisHer() + " comes through clearly even as " + sender.heShe() + " winces and groans at the stimulation to " + sender.hisHer() + " most sensitive inner places.");
                if(sender.isVVirg())
                    if(sender.getObedience() > 66)
                        w.append(t, "  " + sender.HeShe() + " smiles broadly up at " + receiver.ownerName() + ", tears of joy leaking from " + sender.hisHer() + " eyes at being able to give " + sender.hisHer() + " first time to the one " + sender.heShe() + " loves.");
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, "  When " + sender.heShe() + " sees the blood on " + receiver.ownerName() + "'s cock, " + sender.heShe() + " flinches away, as if trying to ignore it.");
                    else
                        w.append(t, "  " + sender.HisHer() + " anger drowns out the pain of having " + sender.hisHer() + " hymen torn.");
            }
        } else
        if(this == Project.AnalPenetrate)
        {
            if(Project.PushDown.isInProgress(sender, receiver))
            {
                if(sender == w.lordBody)
                    w.append(t, sender.OwnerName() + " puts the tip of " + sender.hisHer() + " cock against " + receiver.ownerName() + "'s anus, then thrusts inside.");
                else
                if(sender.getDeviancy() > 66)
                {
                    if(sender.getConfidence() > 66)
                        w.append(t, sender.OwnerName() + " eagerly pins " + receiver.ownerName() + " down and shoves " + sender.hisHer() + " shaft all the way up " + receiver.ownerName() + "'s ass, immediately setting a rapid pace of thrusting in and out.  It's a struggle to keep moving steadily with the flashes of pleasure shooting through " + sender.himHer() + ", but " + sender.ownerName() + " is determined to keep going.");
                    else
                    if(sender.getConfidence() > 33)
                        w.append(t, sender.OwnerName() + " starts to slowly push " + sender.hisHer() + " cock up " + receiver.ownerName() + "'s ass, but the surge of pleasure from the sphincter squeezing " + sender.hisHer() + " tip overwhelms " + sender.hisHer() + " reason.  " + sender.HeShe() + " jerks " + sender.hisHer() + " hips forward, burying " + sender.himHer() + "self all the way inside, then pulls halfway out, then pushes back in, hips jerking wildly as intense ecstasy surges through " + sender.himHer() + " with every movement.");
                    else
                        w.append(t, sender.OwnerName() + "'s timid nature seems to vanish entirely, and " + sender.heShe() + " starts to ram " + sender.hisHer() + " hips forward with wild abandon, hammering in and out of " + receiver.ownerName() + "'s asshole.");
                } else
                if(sender.getDeviancy() > 33)
                {
                    if(sender.getInnocence() > 66)
                        w.append(t, sender.OwnerName() + " pushes " + sender.hisHer() + " cock up " + receiver.ownerName() + "'s ass, and the moment " + sender.heShe() + " feels " + receiver.hisHer() + " rear entrance squeezing down on " + sender.himHer() + ", " + sender.heShe() + " loses the ability to think of anything but the pleasure.  Moaning softly, " + sender.heShe() + " starts thrusting in and out, acting on pure instinct.");
                    else
                    if(sender.getInnocence() > 33)
                        w.append(t, sender.OwnerName() + " starts to fuck " + receiver.ownerName() + "'s asshole, but the pleasure of " + receiver.hisHer() + " sphincter squeezing down on " + sender.himHer() + " cock gives " + sender.ownerName() + " pause.  " + sender.HeShe() + " gasps, taking a moment to catch " + sender.hisHer() + " breath, then continues more slowly, enjoying the incredible tightness around " + sender.hisHer() + " shaft.");
                    else
                        w.append(t, sender.OwnerName() + " gradually pushes " + sender.hisHer() + " cock up " + receiver.ownerName() + "'s ass, inch-by-inch, then withdraws it just as carefully.  " + sender.HeShe() + " moves slowly, feeling out which spots feel better for " + sender.himHer() + " and which seem to get more of a reaction from " + receiver.ownerName() + ", and only then starts to move more quickly, getting into a rhythm.");
                } else
                if(sender.getObedience() > 66)
                    w.append(t, sender.OwnerName() + " eagerly thrusts " + sender.hisHer() + " cock up " + receiver.ownerName() + "'s ass, ecstatic to be joined with " + receiver.himHer() + ".  " + sender.HeShe() + " moves unselfishly, trying to hit all of " + receiver.ownerName() + "'s sensitive parts without any regard for " + sender.hisHer() + " own pleasure.");
                else
                if(sender.getObedience() > 33)
                    w.append(t, sender.OwnerName() + " presses the tip of " + sender.hisHer() + " cock against " + receiver.ownerName() + "'s anus, hesitates for a moment, and then finally pushes it inside.  " + sender.HeShe() + " gasps and shudders at the pleasure, far beyond what " + sender.heShe() + " was prepared for, and then cautiously starts to move " + sender.hisHer() + " hips forward and back.");
                else
                    w.append(t, sender.OwnerName() + "'s angry demeanor fades for just a moment as " + sender.heShe() + " thrusts into " + receiver.ownerName() + " and feels the intense pleasure of " + receiver.hisHer() + " asshole squeezing " + sender.himHer() + ".  But then " + sender.heShe() + " recovers and starts to move violently in and out, as if stabbing " + receiver.ownerName() + " with a weapon.");
            } else
            if(sender == w.lordBody)
                w.append(t, sender.OwnerName() + "'s cock pushes all the way into " + receiver.ownerName() + "'s bowels.");
            else
            if(sender.getDeviancy() > 66)
            {
                if(sender.getInnocence() > 66)
                    w.append(t, sender.OwnerName() + "'s whole body goes slack except for " + sender.hisHer() + " hips desperately trying to thrust deeper into " + receiver.himHer() + ".  " + sender.HisHer() + " eyes roll and " + sender.hisHer() + " tongue hangs out.");
                else
                if(sender.getInnocence() > 33)
                    w.append(t, "The feeling of " + receiver.ownerName() + " sliding down to the base of " + sender.hisHer() + " shaft is enough to make " + sender.ownerName() + " cry out and lose " + sender.hisHer() + " sense of reason.  " + sender.HeShe() + " desperately clings to " + receiver.ownerName() + ", hips jerking wildly up and down as " + sender.heShe() + " tries to thrust even deeper into " + receiver.himHer() + ".");
                else
                    w.append(t, "At first, " + sender.ownerName() + " tries to reciprocate with " + sender.hisHer() + " own thrusts.  But it soon becomes clear that " + sender.heShe() + "'s completely at " + receiver.ownerName() + "'s mercy, and all " + sender.heShe() + " can do is moan and jerk " + sender.hisHer() + " hips as " + receiver.ownerName() + " slides up and down " + sender.hisHer() + " shaft.");
            } else
            if(sender.getDeviancy() > 33)
            {
                if(sender.getDignity() > 66)
                    w.append(t, sender.OwnerName() + " tries to hide just how good it feels, but when " + receiver.ownerName() + " clenches around " + sender.himHer() + ", " + sender.heShe() + " gasps and reflexively bucks " + sender.hisHer() + " hips.  After that, " + sender.heShe() + " has a harder and harder time suppressing " + sender.hisHer() + " moans.");
                else
                if(sender.getDignity() > 33)
                    w.append(t, sender.OwnerName() + " lays back and loses " + sender.himHer() + "self in the feeling of " + receiver.ownerName() + " sliding up and down " + sender.hisHer() + " length, overwhelmed by the intensity of the sensations.");
                else
                    w.append(t, sender.OwnerName() + " gasps with pleasure, reflexively embracing " + receiver.ownerName() + " and thrusting " + sender.hisHer() + " own hips in time with " + receiver.ownerName() + "'s movements.");
            } else
            if(sender.getObedience() > 66)
                w.append(t, sender.OwnerName() + " winces and struggles not to cum right away, wanting to ensure that " + sender.heShe() + " stays hard for " + receiver.ownerName() + " as long as possible.");
            else
            if(sender.getObedience() > 33)
                w.append(t, sender.OwnerName() + " tries to relax and enjoy it, but even as " + receiver.ownerName() + " slides up and down " + sender.hisHer() + " shaft, " + sender.ownerName() + " can't quite shake off the fear that this pleasure is just meant to make " + sender.himHer() + " let " + sender.hisHer() + " guard down.");
            else
                w.append(t, sender.OwnerName() + " struggles to keep glaring at " + receiver.ownerName() + ", wincing slightly every time " + receiver.ownerName() + "'s anus squeezes the base of " + sender.hisHer() + " shaft and breaks " + sender.hisHer() + " concentration.");
        } else
        if(this == Project.PenetratedAnally)
        {
            if(Project.PushDown.isInProgress(sender, receiver))
            {
                if(sender == w.lordBody)
                {
                    w.append(t, sender.OwnerName() + " mounts " + receiver.ownerName() + " and lowers " + sender.himHer() + "self until " + sender.hisHer() + " anus envelops " + receiver.ownerName() + "'s cock.");
                } else
                {
                    if(!Project.BeLubricated.isInProgress(sender, null))
                    {
                        if(sender.getInnocence() > 66)
                            w.append(t, "Full of eager lust, " + sender.ownerName() + " almost forgets to lubricate " + sender.himHer() + "self back there before continuing.");
                        else
                        if(sender.getInnocence() > 33)
                            w.append(t, "As " + sender.heShe() + " lays atop " + receiver.ownerName() + ", " + sender.ownerName() + " reaches back with one finger covered in lubricant to prepare for what comes next.");
                        else
                            w.append(t, "Before continuing, " + sender.ownerName() + " carefully applies some slippery lubricant to " + sender.hisHer() + " rear entrance.");
                        w.append(t, "\n\n");
                        sender.addActivity(Project.BeLubricated, null);
                    }
                    if(sender.getDeviancy() > 66)
                    {
                        if(sender.getObedience() > 66)
                            w.append(t, sender.OwnerName() + " eagerly impales " + sender.hisHer() + " asshole on " + receiver.ownerName() + "'s upright cock, sliding " + sender.himHer() + "self up and down with manic energy while grinning at " + receiver.himHer() + ".");
                        else
                        if(sender.getObedience() > 33)
                            w.append(t, sender.OwnerName() + " quickly lowers " + sender.himHer() + "self onto " + receiver.ownerName() + "'s cock, then immediately begins to bounce on " + receiver.hisHer() + " lap, searching for the precise angle where it pokes just the right spot inside " + sender.hisHer() + " bowels.");
                        else
                            w.append(t, sender.OwnerName() + " tries to resist " + sender.hisHer() + " urges, but this close to " + receiver.ownerName() + "'s cock, " + sender.heShe() + " can't stop " + sender.himHer() + "self from grinding against it.  Then, before " + sender.heShe() + " realizes it, " + sender.heShe() + "'s taken it up " + sender.hisHer() + " ass.  The surge of pleasure destroys the last of " + sender.hisHer() + " reason, and " + sender.heShe() + " starts moving in earnest.");
                    } else
                    if(sender.getDeviancy() > 33)
                    {
                        if(sender.getConfidence() > 66)
                            w.append(t, sender.OwnerName() + " pins " + receiver.ownerName() + " down and takes " + receiver.ownerName() + "'s cock up " + sender.hisHer() + " ass.  " + sender.HeShe() + " grimaces slightly at the sensation of being spread wide open, but wastes no time in bucking " + sender.hisHer() + " hips with savage force, causing the discomfort to melt away into pleasure.");
                        else
                        if(sender.getConfidence() > 33)
                            w.append(t, sender.OwnerName() + " straddles " + receiver.ownerName() + ", touching " + sender.hisHer() + " anus against the tip of " + receiver.ownerName() + "'s cock, then slowly lowering " + sender.himHer() + "self downward onto it, bit by bit.  " + sender.HeShe() + " gasps and twitches as it bottoms out inside " + sender.himHer() + ".");
                        else
                            w.append(t, sender.OwnerName() + " is nervous about lowering " + sender.himHer() + "self onto " + receiver.ownerName() + "'s cock, but " + sender.heShe() + " gasps with pleasure as it goes up " + sender.hisHer() + " ass and immediately hits a sensitive spot.  " + sender.HeShe() + " moans and begins moving " + sender.himHer() + "self up and down with genuine enthusiasm.");
                    } else
                    if(sender.getDignity() > 66)
                        w.append(t, sender.OwnerName() + " is blushing bright red at having to be the one to lower " + sender.himHer() + "self onto " + receiver.ownerName() + "'s cock, but " + sender.heShe() + " pretends that it doesn't bother " + sender.himHer() + ".  " + sender.HeShe() + " just flinches as it spreads " + sender.hisHer() + " anus open, then stifles a moan when it goes all the way inside.");
                    else
                    if(sender.getDignity() > 33)
                        w.append(t, sender.OwnerName() + " delays as long as possible before taking " + receiver.ownerName() + "'s cock up " + sender.hisHer() + " ass.  " + sender.HeShe() + " tries various angles, grinds the tip against " + sender.hisHer() + " anus, and only then does " + sender.heShe() + " finally lower " + sender.himHer() + "self onto it.  When " + sender.heShe() + " does, " + sender.heShe() + " lets out a little moan, uncomfortable with just how intense the pleasure is.");
                    else
                        w.append(t, sender.OwnerName() + " doesn't waste any time before slamming " + sender.hisHer() + " ass down onto " + receiver.ownerName() + "'s cock.  However, " + sender.heShe() + " groans with discomfort at the sudden insertion, and " + sender.heShe() + " needs a few moments to recover before " + sender.heShe() + " can start moving.");
                }
                if(sender.isVVirg())
                    if(sender.getObedience() > 66)
                        w.append(t, "  " + sender.HeShe() + " embraces the pain of " + sender.hisHer() + " first vaginal penetration, ecstatic to have received it from " + receiver.ownerName() + ".");
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, "  " + sender.HeShe() + " looks down at the trickle of blood on " + sender.hisHer() + " thigh with an expression of resignation.");
                    else
                        w.append(t, "  " + sender.HeShe() + " lifts " + sender.himHer() + "self up enough to look down and see the blood on " + receiver.ownerName() + "'s cock, then grits " + sender.hisHer() + " teeth.");
            } else
            if(Project.BeLubricated.isInProgress(sender, null) || sender == w.lordBody)
            {
                if(sender == w.lordBody)
                    w.append(t, sender.OwnerName() + "'s asshole tightens around " + receiver.ownerName() + "'s cock as though trying to milk it dry.");
                else
                if(sender.getObedience() > 66)
                {
                    if(sender.getDeviancy() > 66)
                        w.append(t, sender.OwnerName() + " goes wild with joyful lust, bucking " + sender.hisHer() + " hips wildly and enjoying every moment of being taken by " + receiver.ownerName() + ".");
                    else
                    if(sender.getDeviancy() > 33)
                        w.append(t, sender.OwnerName() + " tries to help " + receiver.ownerName() + " feel good as well, moving " + sender.hisHer() + " hips and urging " + receiver.himHer() + " deeper inside, but soon the pleasure overwhelms " + sender.himHer() + " and " + sender.heShe() + " can't think of anything but wanting to cum.");
                    else
                        w.append(t, sender.OwnerName() + " bites " + sender.hisHer() + " lip in concentration, trying to buck " + sender.hisHer() + " hips in turn and squeeze down with " + sender.hisHer() + " anus in order to make it feel as good as possible for " + receiver.ownerName() + ".");
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.getConfidence() > 66)
                        w.append(t, sender.OwnerName() + " doesn't look entirely happy to be taking it up the ass, but the pleasure of each thrust hitting " + sender.hisHer() + " deepest places soon makes " + sender.himHer() + " forget all about that and start crying out in pleasure.");
                    else
                    if(sender.getConfidence() > 33)
                        w.append(t, "At first, " + sender.ownerName() + " tolerates it with an expression of resignation.  However, as " + sender.hisHer() + " sensitive places start to get stimulated through " + sender.hisHer() + " anal walls, " + sender.heShe() + " starts to gasp with passion and then actively move " + sender.hisHer() + " hips.");
                    else
                        w.append(t, sender.OwnerName() + " is too nervous to move much, worried about doing something to displease " + receiver.ownerName() + ".  Whimpering moans of pleasure begin to leak out of " + sender.hisHer() + " throat.");
                } else
                if(sender.getInnocence() > 66)
                    w.append(t, sender.OwnerName() + " tries to kick and scream, but " + sender.hisHer() + " movements only drive " + receiver.ownerName() + " deeper inside " + sender.himHer() + ", and " + sender.hisHer() + " voice takes on a passionate quality.");
                else
                if(sender.getInnocence() > 33)
                    w.append(t, sender.OwnerName() + " attempts to deny " + receiver.ownerName() + " the satisfaction of seeing " + sender.hisHer() + " reactions, but the longer the fucking continues, the more difficult it is.  Soon " + sender.heShe() + "'s reduced to covering " + sender.hisHer() + " face and biting " + sender.hisHer() + " lip to stifle " + sender.hisHer() + " moans.");
                else
                    w.append(t, sender.OwnerName() + " glares up at " + receiver.ownerName() + ", and " + sender.hisHer() + " comes through clearly even as " + sender.heShe() + " winces and groans at the stimulation through " + sender.hisHer() + " anal walls.");
                if(sender.getGender() == Forsaken.Gender.MALE && sender.isVVirg())
                    if(sender.getObedience() > 66)
                        w.append(t, "  " + sender.HeShe() + " smiles broadly up at " + receiver.ownerName() + ", tears of joy leaking from " + sender.hisHer() + " eyes at receiving " + sender.hisHer() + " first anal penetration from the one " + sender.heShe() + " loves.");
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, "  To " + sender.hisHer() + " horror, the pleasure keeps building further and further, until " + sender.heShe() + " can't deny that " + sender.heShe() + " enjoys being fucked like a girl.");
                    else
                        w.append(t, "  " + sender.HisHer() + " anger drowns out the pain of having " + sender.hisHer() + " inexperienced asshole stretched so wide for the first time.");
            } else
            {
                if(sender.getConfidence() > 66)
                {
                    if(sender.getObedience() > 66)
                        w.append(t, sender.OwnerName() + " grits " + sender.hisHer() + " teeth into a forced smile, encouraging " + receiver.ownerName() + " to keep fucking " + sender.himHer() + " even though " + sender.heShe() + "'s in agony from having " + sender.hisHer() + " ass forced open without any lubrication.");
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, "The agony of being anally penetrated without any lubrication is almost enough to paralyze " + sender.ownerName() + "'s thoughts, but " + sender.heShe() + " has just enough willpower to beg for mercy, offering to do anything in hopes of ending the torture.");
                    else
                        w.append(t, sender.OwnerName() + "'s determined resolve lasts only a moment before cracking, and " + sender.heShe() + " starts begging for mercy in a shrill voice, apologizing for " + sender.hisHer() + " defiance.");
                } else
                if(sender.getConfidence() > 33)
                {
                    if(sender.getDignity() > 66)
                        w.append(t, "At first, " + sender.ownerName() + " tries to pretend that " + sender.heShe() + " can take it.  But the painful friction against " + sender.hisHer() + " unlubricated anal walls is too much for " + sender.himHer() + ", and soon " + sender.heShe() + "'s crying and begging without restraint.");
                    else
                    if(sender.getDignity() > 33)
                        w.append(t, "Without any lubrication, the insertion is more painful than anything, and " + sender.ownerName() + " is ashamed with " + sender.himHer() + "self as " + sender.heShe() + " starts uncontrollably sobbing.");
                    else
                        w.append(t, sender.OwnerName() + " kicks wildly and screams at the top of " + sender.hisHer() + " lungs, heedless of the fact that " + sender.heShe() + "'s only scraping " + sender.himHer() + "self even more against the cruel invading shaft.");
                } else
                if(sender.getDeviancy() > 66)
                    w.append(t, sender.OwnerName() + "'s eyes shoot wide open, a scream caught in " + sender.hisHer() + " throat.  But when it finally comes out, there's a lewd quality to it, a sign of " + sender.hisHer() + " insatiable masochism.");
                else
                if(sender.getDeviancy() > 33)
                    w.append(t, sender.OwnerName() + " whimpers, trying to curl up and protect " + sender.himHer() + "self, but " + sender.heShe() + " can't hide from the painful friction of the shaft invading " + sender.hisHer() + " unprepared hole.  " + sender.HisHer() + " moans aren't entirely from pain, as " + sender.heShe() + " feels a hint of shameful pleasure as well.");
                else
                    w.append(t, sender.OwnerName() + " screams, crying and sobbing at the explosion of agony " + sender.heShe() + " feels from the unlubricated insertion.  The pain far outweighs the pleasure.");
                if(sender.getGender() == Forsaken.Gender.MALE && sender.isVVirg())
                    if(sender.getObedience() > 66)
                        w.append(t, "  Afterward, " + sender.heShe() + "'ll be full of joyful pride that " + receiver.ownerName() + " saw fit to break " + sender.himHer() + " in with such a memorable fucking, but for now, " + sender.heShe() + "'s in too much pain to think about it in those terms.");
                    else
                    if(sender.getObedience() > 33)
                        w.append(t, "  " + sender.HeShe() + " realizes that it was a mistake to ever take pride in managing to protect " + sender.hisHer() + " anal virginity.");
                    else
                        w.append(t, "  A part of " + sender.himHer() + " is horrified that " + sender.heShe() + " was broken so easily, and " + sender.hisHer() + " confidence in " + sender.hisHer() + " own masculinity will never recover.");
            }
        } else
        if(this == Project.StripOther)
        {
            if(sender == w.lordBody)
            {
                w.append(t, sender.OwnerName() + " begins to strip off " + receiver.ownerName() + "'s clothes.");
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
                        w.append(t, sender.OwnerName() + " doesn't even seem to notice all the spectators.  " + sender.HeShe() + " only has eyes for " + receiver.ownerName() + ".");
                    else
                    if(sender.getDisgrace() > 33)
                        w.append(t, sender.OwnerName() + " smiles, eager to help " + receiver.ownerName() + " put on a show for everyone.");
                    else
                        w.append(t, sender.OwnerName() + " looks afraid, but also eager, wanting to show everyone that " + sender.heShe() + " really is nothing more than " + receiver.ownerName() + "'s toy.");
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.forsakenOwner != null && sender.forsakenOwner.timesExposed < 300_000)
                    {
                        if(sender.getDignity() > 66)
                            w.append(t, sender.OwnerName() + " is clearly terrified of the prospect of being forced to show everything, but " + sender.heShe() + "'s come too far to back out now.");
                        else
                        if(sender.getDignity() > 33)
                            w.append(t, sender.OwnerName() + " looks resigned to the fact that " + sender.heShe() + "'ll shortly be showing off more than " + sender.heShe() + " ever has before.");
                        else
                            w.append(t, sender.OwnerName() + " just ignores all the shouts and jeers.");
                    } else
                    if(sender.getDignity() > 66)
                        w.append(t, sender.OwnerName() + " tries to make it look like it was " + sender.hisHer() + " idea to undress, moving to help " + receiver.ownerName() + " strip " + sender.himHer() + ".");
                    else
                    if(sender.getDignity() > 33)
                        w.append(t, sender.OwnerName() + " tries to make the best of it, smiling at the crowd.");
                    else
                        w.append(t, sender.OwnerName() + " just ignores all the shouts and jeers.");
                } else
                if(sender.getDeviancy() > 66)
                    w.append(t, sender.OwnerName() + "'s eyes dart back and forth among all the people watching, and despite " + sender.hisHer() + " anger, " + sender.heShe() + "'s panting wth barely-restrained desire.");
                else
                if(sender.getDeviancy() > 33)
                    w.append(t, sender.OwnerName() + " huffs angrily, but the heat rising to " + sender.hisHer() + " cheeks is partly from arousal.");
                else
                    w.append(t, sender.OwnerName() + " clutches at " + sender.hisHer() + " clothes and glares at " + receiver.ownerName() + ".");
            } else
            if(sender.getDeviancy() > 66)
            {
                if(sender.getInnocence() > 66)
                    w.append(t, "The act of being stripped immediately causes " + sender.ownerName() + "'s mind to wander off into erotic fantasy, and " + sender.heShe() + " starts to drool with a silly expression on " + sender.hisHer() + " face.");
                else
                if(sender.getInnocence() > 33)
                    w.append(t, "As " + sender.hisHer() + " clothes are taken off, " + sender.ownerName() + " gets more and more turned on, and soon " + sender.heShe() + " can't think of anything but sex.");
                else
                    w.append(t, sender.OwnerName() + " starts to breathe more quickly, imagining what might come next.");
            } else
            if(sender.getDeviancy() > 33)
            {
                if(sender.getConfidence() > 66)
                    w.append(t, sender.OwnerName() + " proudly shows off, refusing to be ashamed of " + sender.hisHer() + " body.");
                else
                if(sender.getConfidence() > 33)
                    w.append(t, sender.OwnerName() + " blushes, anticipating what's to come.");
                else
                    w.append(t, sender.OwnerName() + " glances off to the side and reflexively tries to cover " + sender.himHer() + "self.");
            } else
            if(sender.getObedience() > 66)
                w.append(t, sender.OwnerName() + " smiles, happy that " + receiver.ownerName() + " wants to see " + sender.hisHer() + " body.");
            else
            if(sender.getObedience() > 33)
                w.append(t, sender.OwnerName() + " sighs, accepting that this was inevitable.");
            else
                w.append(t, sender.OwnerName() + " clutches at " + sender.hisHer() + " clothes and glares at " + receiver.ownerName() + ".");
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
                    if(sender.forsakenOwner != null && sender.forsakenOwner.timesExposed < 300_000 || sender.chosenOwner != null && sender.chosenOwner.modest)
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
