
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
            return "Stroke " + partner.ownerName() + "'s Clit";
        if(this == Project.ClitTweaked)
            return "Clit Stroked by " + partner.ownerName();
        if(this == Project.SpreadLegs)
            return "Spread Legs";
        if(this == Project.Praise)
            return "Praise " + partner.ownerName();
        if(this == Project.Insult)
            return "Insult " + partner.ownerName();
        if(this == Project.PushDown)
            return "Atop " + partner.ownerName();
        if(this == Project.PullDown)
            return "Beneath " + partner.ownerName();
        if(this == Project.Escape)
            return "Escape " + partner.ownerName();
        if(this == Project.StopActing)
            return "Stop Activities";
        if(this == Project.TieUp)
            return "Tie Up " + partner.ownerName();
        if(this == Project.BeTied)
            return "Tied Up By " + partner.ownerName();
        if(this == Project.StrokeCock)
            return "Stroke " + partner.ownerName() + "'s Cock";
        if(this == Project.CockStroked)
            return "Cock Stroked by " + partner.ownerName();
        if(this == Project.Lubricate)
            return "Lubricate " + partner.ownerName() + "'s Anus";
        if(this == Project.BeLubricated)
            return "Anus Lubricated";
        if(this == Project.VaginalPenetrate)
            return "Fucking " + partner.ownerName() + "'s Pussy";
        if(this == Project.PenetratedVaginally)
            return "Pussy Fucked By " + partner.ownerName();
        if(this == Project.AnalPenetrate)
            return "Fucking " + partner.ownerName() + "'s Ass";
        if(this == Project.PenetratedAnally)
            return "Ass Fucked By " + partner.ownerName();
        if(this == Project.StripOther)
            return "Strip " + partner.ownerName();
        if(this == Project.Stripped)
            return "Stripped by " + partner.ownerName();
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
                        switch (sender.getMorality() / 33) {
                            case 0: sender.say(t, "I always felt like sex was just about feeling good, but... I managed to give you my first time.  That makes me really happy."); break;
                            case 1: sender.say(t, "I'm so happy that I was able to save myself for you!"); break;
                            default: sender.say(t, "With this...  Even though I'm a boy, I feel like I'm... your wife...  Is that okay?");
                        }
                    } else
                    if(sender.getObedience() > 33)
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.SHAME);
                        switch (sender.getInnocence() / 33) {
                            case 0: sender.say(t, "I suppose... I had already come to accept that you'd eventually use me back there as well..."); break;
                            case 1: sender.say(t, "I'm surprised you took so long to do this..."); break;
                            default: sender.say(t, "It kinda hurts...");
                        }
                    } else
                    {
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.ANGER);
                        switch (sender.getInnocence() / 33) {
                            case 0: sender.say(t, "All that effort to keep the Thralls from raping me, only to do it yourself?  How... ngh... pointless..."); break;
                            case 1: sender.say(t, "Even if you defile my body, I'll never give you my heart!"); break;
                            default: sender.say(t, "I'll never forgive you for this!  Never!  Never!");
                        }
                    }
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.FEAR);
                    if(sender.getConfidence() > 66)
                    {
                        switch (sender.getObedience() / 33) {
                            case 0: sender.say(t, "I'll stop fighting, I'll stop talking back, I'll do anything!"); break;
                            case 1: sender.say(t, "Why are you doing this!?  I'm serving you now!  Gaaah, pleeease!"); break;
                            default: sender.say(t, "I'm... guh... f-fine, " + sender.demonLord() + "!");
                        }
                    } else
                    if(sender.getConfidence() > 33)
                    {
                        switch (sender.getDignity() / 33) {
                            case 0: sender.say(t, "AAAGH, NOOO!"); break;
                            case 1: sender.say(t, "Mgh...  guh... I-I can't..."); break;
                            default: sender.say(t, "Please, please!  Stooop!");
                        }
                    } else
                    switch (sender.getDeviancy() / 33) {
                        case 0: sender.say(t, "Agh!  Mph, guh, n-no...!"); break;
                        case 1: sender.say(t, "Aah, nnooo, oooh!"); break;
                        default: sender.say(t, "Nnnaaah~!");
                    }
                }
            } else
            if(sender.getObedience() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.JOY);
                switch (sender.getMorality() / 33) {
                    case 0: sender.say(t, "I never cared much about who my first would be... but now, I'm really glad that it's you..."); break;
                    case 1: sender.say(t, "I'm so happy that I was able to save myself for you!"); break;
                    default: sender.say(t, "With this...  It's like I'm your wife now, isn't it...?  Ah, wonderful...");
                }
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.SHAME);
                switch (sender.getInnocence() / 33) {
                    case 0: sender.say(t, "I suppose... I had already come to accept that you'd be my first..."); break;
                    case 1: sender.say(t, "I'm surprised you took so long to do this..."); break;
                    default: sender.say(t, "It kinda hurts...");
                }
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.ANGER);
                switch (sender.getInnocence() / 33) {
                    case 0: sender.say(t, "All that effort to preserve my virginity, only to take it now?  How... ngh... pointless..."); break;
                    case 1: sender.say(t, "Even if you defile my body, I'll never give you my heart!"); break;
                    default: sender.say(t, "I'll never forgive you for this!  Never!  Never!");
                }
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
                switch (sender.getObedience() / 33) {
                    case 0: sender.say(t, "Whuh?  What was... that feeling...?  What did you do to me...?"); break;
                    case 1: sender.say(t, "Aaah, what's happening...!?  I feel good!  I feel too good!  I'm going crazy...!"); break;
                    default: sender.say(t, "That was... amazing!  It's like all my love for " + sender.theDemonLord() + "... came out at once...!");
                }
            } else
            if(sender.getInnocence() > 33)
            {
                switch (sender.getObedience() / 33) {
                    case 0: sender.say(t, "Ugh, you made me cum... even though I didn't want to...  Not with you..."); break;
                    case 1: sender.say(t, "Wow!  That was... intense!"); break;
                    default: sender.say(t, "When " + sender.theDemonLord() + " makes me cum... it feels so much better than when I do it myself...!");
                }
            } else
            switch (sender.getObedience() / 33) {
                case 0: sender.say(t, "Nnngh...  Pathetic... to feel such pleasure from the Demon Lord..."); break;
                case 1: sender.say(t, "Your... nn... technique... certainly does not disappoint..."); break;
                default: sender.say(t, "Such an... overwhelmingly intense orgasm!  Ah, " + sender.demonLord() + ", my body has come to desire your touch above all else!");
            }
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
                sender.say(t, sender.getDeviancy() > 33 ? "Too much!  It's too much!  Aaagh!" : "NNNOOOGH!  S-Stop, please!  I'm serving you, so why...!?");
            } else
            if(sender.getObedience() > 33)
            {
                switch (sender.getConfidence() / 33) {
                    case 0: sender.say(t, "P-Please...!  No more!  NoooOOOGH!"); break;
                    case 1: sender.say(t, "I'm breaking!  You're breaking me!"); break;
                    default: sender.say(t, "I'M SORRY!  I'M SORRY FOR DEFYING YOU!  AAARGH!");
                }
            } else
            switch (sender.getConfidence() / 33) {
                case 0: sender.say(t, "I-I give up!  I'm sorry, I'm sorryyy!"); break;
                case 1: sender.say(t, "You win!  You win!  You don't have to- GRAAAH!"); break;
                default: sender.say(t, "NO! NOOO!  AAAGH!");
            }
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
                switch (sender.getDignity() / 33) {
                    case 0: sender.say(t, "I never cared how much they saw, so... it's fine."); break;
                    case 1: sender.say(t, "Maybe... if I look like I'm enjoying it... they won't think I'm weak..."); break;
                    default: sender.say(t, "You've taken... everything... from me...  Ngh...");
                }
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
                    switch (sender.getInnocence() / 33) {
                    case 0:
                        sender.say(t, "I need to impress " + sender.theDemonLord() + " with my technique...!");
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.NEUTRAL);
                    break;
                    case 1:
                        sender.say(t, "Does this feel good, " + sender.demonLord() + "?  I just want to make you happy...");
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                    break;
                    default:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                        sender.say(t, "I'm so happy I get to make " + sender.theDemonLord() + " feel good!");
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
                switch (sender.getDeviancy() / 33) {
                case 0:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                    sender.say(t, "Disgusting... I don't want to touch the Demon Lord...");
                break;
                case 1:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.ANGER);
                    sender.say(t, "Fine.  I'll make you feel good.  But you'd better return the favor...");
                break;
                default:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                    sender.say(t, "Ngh...  You knew... I wouldn't be able to resist this beautiful body you made...");
                }
        } else
        if(this == Project.Praise)
        {
            if(w.sceneDuration % 3 == 0)
            {
                if(sender.getObedience() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                    switch (sender.getInnocence() / 33) {
                        case 0: sender.say(t, "I am unworthy to serve " + sender.theDemonLord() + "... but I shall try my best!"); break;
                        case 1: sender.say(t, "Use me up until nothing is left, " + sender.demonLord() + "."); break;
                        default: sender.say(t, "I'm so happy that I get to spend time with " + sender.theDemonLord() + "!");
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.JOY);
                    switch (sender.getInnocence() / 33) {
                        case 0: sender.say(t, "Let me prove my value to you."); break;
                        case 1: sender.say(t, "You're amazing, " + sender.demonLord() + "."); break;
                        default: sender.say(t, "This is weird, but... kinda fun, too.");
                    }
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
                    switch (sender.getInnocence() / 33) {
                        case 0: sender.say(t, "I'm... aware that I stand no chance of defeating you, " + sender.demonLord() + "."); break;
                        case 1: sender.say(t, "Ugh, I'm completely under your control..."); break;
                        default: sender.say(t, String.valueOf(sender.TheDemonLord()) + " isn't so bad...  Wait, what am I saying!?");
                    }
                }
            } else
            if(w.sceneDuration % 3 == 1)
            {
                if(sender.getObedience() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                    switch (sender.getDeviancy() / 33) {
                        case 0: sender.say(t, "You're remaking me... into your own personal toy...!"); break;
                        case 1: sender.say(t, "I love you, " + sender.demonLord() + "!"); break;
                        default: sender.say(t, "I-Increidble...!  Being with " + sender.theDemonLord() + " feels even better...!");
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    switch (sender.getDeviancy() / 33) {
                    case 0:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
                        sender.say(t, "Your touch is... unforgettable, " + sender.demonLord() + "...");
                    break;
                    case 1:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.JOY);
                        sender.say(t, "My body is all yours, " + sender.demonLord() + ".");
                    break;
                    default:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "I want you to make me feel even better, " + sender.demonLord() + "!");
                    }
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
                    switch (sender.getDeviancy() / 33) {
                        case 0: sender.say(t, "If you can even make someone like me feel like this..."); break;
                        case 1: sender.say(t, "You're almost making me forget that you're the Demon Lord."); break;
                        default: sender.say(t, "Ugh... I... I can't resist you, " + sender.demonLord() + "...");
                    }
                }
            } else
            if(w.sceneDuration % 3 == 2)
                if(sender.getObedience() > 66)
                {
                    switch (sender.getConfidence() / 33) {
                    case 0:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
                        sender.say(t, "I know it's wrong to want you to care about me... but...");
                    break;
                    case 1:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                        sender.say(t, "You're just perfect, " + sender.demonLord() + "!  I don't know why I ever fought you...");
                    break;
                    default:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                        sender.say(t, "I want to make you forget about all your other servants...");
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
                    switch (sender.getConfidence() / 33) {
                        case 0: sender.say(t, "I can't even think about fighting you anymore..."); break;
                        case 1: sender.say(t, "I'll never oppose you again.  I understand that now."); break;
                        default: sender.say(t, "I've never feared anyone but you...");
                    }
                } else
                switch (sender.getConfidence() / 33) {
                case 0:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
                    sender.say(t, "I, um...  I don't actually think I'm going to be able to resist you for that long.  I just don't want to give up right away...");
                break;
                case 1:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
                    sender.say(t, "I... never stood a chance against you.  Ugh, I hate saying that...");
                break;
                default:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.NEUTRAL);
                    sender.say(t, "You're... a worthy opponent.  I wouldn't even feel bad about losing to you.  Not that I'm giving up!");
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
                    sender.say(t, sender.getINJULevel() == 2 ? "Can't you tell I'm getting tired?  Let's just stop here." : "I can't take this anymore...!");
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
                switch (sender.getDeviancy() / 33) {
                    case 0: sender.say(t, "Even if it's with " + sender.theDemonLord() + ", I... I don't like this..."); break;
                    case 1: sender.say(t, "How can I hate this, when it's with " + sender.theDemonLord() + "...?"); break;
                    default: sender.say(t, "I'll just... dream about a nicer " + sender.demonLord() + "...");
                }
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.ANGER);
                switch (sender.getDeviancy() / 33) {
                    case 0: sender.say(t, "You know I don't like this sort of thing..."); break;
                    case 1: sender.say(t, "This is too much for me!"); break;
                    default: sender.say(t, "This is too much, even for me!");
                }
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                switch (sender.getDeviancy() / 33) {
                    case 0: sender.say(t, "You're... disgusting..."); break;
                    case 1: sender.say(t, "I don't want to do this!  At least not with you!"); break;
                    default: sender.say(t, "Just go away and let me play with myself!");
                }
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
                switch (sender.getConfidence() / 33) {
                case 0:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                    sender.say(t, "If we don't do this now... I-I'm gonna go crazy...!");
                break;
                case 1:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                    sender.say(t, "Hurry, hurry...!");
                break;
                default:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                    sender.say(t, "Aaah... I wanna rape " + sender.theDemonLord() + " a lot...!");
                }
            } else
            if(sender.getDeviancy() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                switch (sender.getInnocence() / 33) {
                    case 0: sender.say(t, "Such an... impressive specimen..."); break;
                    case 1: sender.say(t, "Let's just do it."); break;
                    default: sender.say(t, "Mm...!  Mm...");
                }
            } else
            switch (sender.getObedience() / 33) {
            case 0:
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
                sender.say(t, "Let's get this over with.");
            break;
            case 1:
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
                sender.say(t, "I have no idea what I'm doing...");
            break;
            default:
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
                sender.say(t, "Are you sure you don't want to lead, " + sender.demonLord() + "?");
            }
        } else
        if(this == Project.PullDown)
        {
            if(sender.getDeviancy() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                switch (sender.getConfidence() / 33) {
                    case 0: sender.say(t, "P-Please... do whatever you want with me...!"); break;
                    case 1: sender.say(t, "I'm ready for you...!"); break;
                    default: sender.say(t, "Hurry up and take me!");
                }
            } else
            if(sender.getDeviancy() > 33)
            {
                switch (sender.getDignity() / 33) {
                case 0:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                    sender.say(t, "I can't take it any more...!");
                break;
                case 1:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.LEWD);
                    sender.say(t, "This is going to feel good...");
                break;
                default:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.LEWD);
                    sender.say(t, "I-I guess I'm ready when you are...");
                }
            } else
            switch (sender.getObedience() / 33) {
            case 0:
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
                sender.say(t, "Go on, finish satisfying yourself so I can get out of here!");
            break;
            case 1:
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
                sender.say(t, "I never thought I'd be doing this...");
            break;
            default:
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
                sender.say(t, "I hope you enjoy my body...");
            }
        } else
        if(this == Project.Escape)
        {
            if(sender.getObedience() > 66)
            {
                switch (sender.getMorality() / 33) {
                case 0:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.SHAME);
                    sender.say(t, "I can't!  Even if you are " + sender.theDemonLord() + "!");
                break;
                case 1:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.SHAME);
                    sender.say(t, "Are you going to punish me...?");
                break;
                default:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
                    sender.say(t, "I-I'm so sorry, " + sender.demonLord() + "...  I'm worthless...");
                }
            } else
            if(sender.getObedience() > 33)
            {
                switch (sender.getInnocence() / 33) {
                case 0:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
                    sender.say(t, "I cannot continue.");
                break;
                case 1:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
                    sender.say(t, "I'm sorry, but I really need a break...");
                break;
                default:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.SHAME);
                    sender.say(t, "Oh no!  I- I didn't mean to do that!");
                }
            } else
            switch (sender.getConfidence() / 33) {
            case 0:
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
                sender.say(t, "I c-can't take this anymore!");
            break;
            case 1:
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
                sender.say(t, "I'm not afraid of you!");
            break;
            default:
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
                sender.say(t, "Whew...  Heh, I couldn't hold myself back anymore.");
            }
        } else
        if(this == Project.StopActing)
        {
            if(sender.getObedience() > 66)
            {
                switch (sender.getMorality() / 33) {
                case 0:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.JOY);
                    sender.say(t, "How about I lay here while you rub up against me?  That will feel good too, right?");
                break;
                case 1:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.SHAME);
                    sender.say(t, "Sorry, I'm... at my limit...");
                break;
                default:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
                    sender.say(t, "Maybe... I need more training...");
                }
            } else
            if(sender.getObedience() > 33)
            {
                switch (sender.getDeviancy() / 33) {
                case 0:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
                    sender.say(t, "I should go... do some combat training.  I can't stay here doing this all day.");
                break;
                case 1:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
                    sender.say(t, "That was good enough, right?");
                break;
                default:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                    sender.say(t, "Well, I enjoyed that.  Let's do it again sometime.");
                }
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
                switch (sender.getInnocence() / 33) {
                    case 0: sender.say(t, "Continuing to service you is worse than any punishment you can inflict on me."); break;
                    case 1: sender.say(t, "This is over."); break;
                    default: sender.say(t, "I'm not gonna make you feel good anymore!");
                }
            }
        } else
        if(this == Project.StrokeCock)
        {
            if(receiver == w.lordBody)
                if(sender.getObedience() > 66)
                {
                    switch (sender.getInnocence() / 33) {
                    case 0:
                        sender.say(t, "I need to impress " + sender.theDemonLord() + " with my technique...!");
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.NEUTRAL);
                    break;
                    case 1:
                        sender.say(t, "Does this feel good, " + sender.demonLord() + "?  I just want to make you happy...");
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                    break;
                    default:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                        sender.say(t, "I'm so happy I get to make " + sender.theDemonLord() + " feel good!");
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
                switch (sender.getDeviancy() / 33) {
                case 0:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                    sender.say(t, "Disgusting... I don't want to touch the Demon Lord...");
                break;
                case 1:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.ANGER);
                    sender.say(t, "Fine.  I'll make you feel good.  But you'd better return the favor...");
                break;
                default:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                    sender.say(t, "Ngh...  You knew... I wouldn't be able to resist this beautiful body you made...");
                }
        } else
        if(this == Project.VaginalPenetrate)
        {
            if(Project.PushDown.isInProgress(sender, receiver))
            {
                if(sender.getDeviancy() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                    switch (sender.getConfidence() / 33) {
                        case 0: sender.say(t, "I-I'm gonna... fill you up...!"); break;
                        case 1: sender.say(t, "Your... pussy... feels... too... good...!"); break;
                        default: sender.say(t, "Ngh...!  Fuck...!  Yes...!");
                    }
                } else
                if(sender.getDeviancy() > 33)
                {
                    switch (sender.getInnocence() / 33) {
                    case 0:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                        sender.say(t, "Ah, yes...  Here we go...");
                    break;
                    case 1:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                        sender.say(t, "That's... tight...!");
                    break;
                    default:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Aaah...!  Wow...!");
                    }
                } else
                switch (sender.getObedience() / 33) {
                case 0:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
                    sender.say(t, "How dare you... tempt me...!?  Take this!  And this!");
                break;
                case 1:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FEAR);
                    sender.say(t, "This is... really intense...");
                break;
                default:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                    sender.say(t, "I love you, " + sender.demonLord() + "!  I love you so much!");
                }
            } else
            if(sender.getDeviancy() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                switch (sender.getInnocence() / 33) {
                    case 0: sender.say(t, "I-I'm- Ah!  Going to climax alreadyyy!"); break;
                    case 1: sender.say(t, "Yes!  Yes!  More!"); break;
                    default: sender.say(t, "Aaah...  Wooow...");
                }
            } else
            if(sender.getDeviancy() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                switch (sender.getDignity() / 33) {
                    case 0: sender.say(t, "Ooogh!  It's squeezing meee!"); break;
                    case 1: sender.say(t, "This is... amazing...!"); break;
                    default: sender.say(t, "Ngh!  Ah!  Aaah!");
                }
            } else
            switch (sender.getObedience() / 33) {
            case 0:
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
                sender.say(t, "J-Just... have to calm down...!");
            break;
            case 1:
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.LEWD);
                sender.say(t, "Why... are you making me feel so good...!?");
            break;
            default:
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.LEWD);
                sender.say(t, "I'll hold out... as long as I can...!");
            }
        } else
        if(this == Project.PenetratedVaginally)
        {
            if(Project.PushDown.isInProgress(sender, receiver))
            {
                if(sender.getDeviancy() > 66)
                {
                    switch (sender.getObedience() / 33) {
                    case 0:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                        sender.say(t, "You... t-tricked me into doing this...!");
                    break;
                    case 1:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Aaah...  Your cock is amazing, " + sender.demonLord() + "!");
                    break;
                    default:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Yes!  Yes!  Cum inside me!.");
                    }
                } else
                if(sender.getDeviancy() > 33)
                {
                    switch (sender.getConfidence() / 33) {
                    case 0:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Th-This... actually feels really good...!");
                    break;
                    case 1:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                        sender.say(t, "It's...  Ah!  Inside!");
                    break;
                    default:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                        sender.say(t, "Ngh...!  I know you like it... rough...!");
                    }
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.LEWD);
                    switch (sender.getDignity() / 33) {
                        case 0: sender.say(t, "Ow..."); break;
                        case 1: sender.say(t, "Let me... catch my breath..."); break;
                        default: sender.say(t, "I can't believe... I'm doing this...!");
                    }
                }
            } else
            if(sender.getObedience() > 66)
            {
                switch (sender.getDeviancy() / 33) {
                case 0:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                    sender.say(t, "Does it feel good when I... ngh... squeeze?  Like this?");
                break;
                case 1:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                    sender.say(t, "I'm sorry, " + sender.demonLord() + "!  It feels too good with you inside me!");
                break;
                default:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                    sender.say(t, "Yes!  Yes!  Take me!  Take me!");
                }
            } else
            if(sender.getObedience() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                switch (sender.getConfidence() / 33) {
                    case 0: sender.say(t, "Mm!  Nn...!  Ah, I-I'm...!"); break;
                    case 1: sender.say(t, "Ah!  Ah, wow..."); break;
                    default: sender.say(t, "Aaagh...!  This shouldn't... feel so good...!");
                }
            } else
            switch (sender.getInnocence() / 33) {
            case 0:
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                sender.say(t, "Just... climax already, you brute...!");
            break;
            case 1:
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.LEWD);
                sender.say(t, "Mph!  Gh!  Agh!");
            break;
            default:
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                sender.say(t, "Aaagh, gaaah!  Stop it, nnnaaah!");
            }
        } else
        if(this == Project.AnalPenetrate)
        {
            if(Project.PushDown.isInProgress(sender, receiver))
            {
                if(sender.getDeviancy() > 66)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                    switch (sender.getConfidence() / 33) {
                        case 0: sender.say(t, "I-I'm gonna... fill you up...!"); break;
                        case 1: sender.say(t, "Your... ass... feels... too... good...!"); break;
                        default: sender.say(t, "Ngh...!  Fuck...!  Yes...!");
                    }
                } else
                if(sender.getDeviancy() > 33)
                {
                    switch (sender.getInnocence() / 33) {
                    case 0:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                        sender.say(t, "Ah, yes...  Here we go...");
                    break;
                    case 1:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                        sender.say(t, "That's... tight...!");
                    break;
                    default:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Aaah...!  Wow...!");
                    }
                } else
                switch (sender.getObedience() / 33) {
                case 0:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
                    sender.say(t, "How dare you... tempt me...!?  Take this!  And this!");
                break;
                case 1:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FEAR);
                    sender.say(t, "This is... really intense...");
                break;
                default:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
                    sender.say(t, "I love you, " + sender.demonLord() + "!  I love you so much!");
                }
            } else
            if(sender.getDeviancy() > 66)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                switch (sender.getInnocence() / 33) {
                    case 0: sender.say(t, "I-I'm- Ah!  Going to climax alreadyyy!"); break;
                    case 1: sender.say(t, "Yes!  Yes!  More!"); break;
                    default: sender.say(t, "Aaah...  Wooow...");
                }
            } else
            if(sender.getDeviancy() > 33)
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                switch (sender.getDignity() / 33) {
                    case 0: sender.say(t, "Ooogh!  It's squeezing meee!"); break;
                    case 1: sender.say(t, "This is... amazing...!"); break;
                    default: sender.say(t, "Ngh!  Ah!  Aaah!");
                }
            } else
            switch (sender.getObedience() / 33) {
            case 0:
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
                sender.say(t, "J-Just... have to calm down...!");
            break;
            case 1:
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.LEWD);
                sender.say(t, "Why... are you making me feel so good...!?");
            break;
            default:
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.LEWD);
                sender.say(t, "I'll hold out... as long as I can...!");
            }
        } else
        if(this == Project.PenetratedAnally)
            if(Project.PushDown.isInProgress(sender, receiver))
            {
                if(sender.getDeviancy() > 66)
                {
                    switch (sender.getObedience() / 33) {
                    case 0:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                        sender.say(t, "You... t-tricked me into doing this...!");
                    break;
                    case 1:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Aaah...  Your cock is amazing, " + sender.demonLord() + "!");
                    break;
                    default:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Yes!  Yes!  Cum inside me!.");
                    }
                } else
                if(sender.getDeviancy() > 33)
                {
                    switch (sender.getConfidence() / 33) {
                    case 0:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Th-This... actually feels really good...!");
                    break;
                    case 1:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                        sender.say(t, "It's...  Ah!  Inside!");
                    break;
                    default:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
                        sender.say(t, "Ngh...!  I know you like it... rough...!");
                    }
                } else
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.LEWD);
                    switch (sender.getDignity() / 33) {
                        case 0: sender.say(t, "Ow..."); break;
                        case 1: sender.say(t, "Let me... catch my breath..."); break;
                        default: sender.say(t, "I can't believe... I'm doing this...!");
                    }
                }
            } else
            if(Project.BeLubricated.isInProgress(sender, null))
            {
                if(sender.getObedience() > 66)
                {
                    switch (sender.getDeviancy() / 33) {
                    case 0:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
                        sender.say(t, "Does it feel good when I... ngh... squeeze?  Like this?");
                    break;
                    case 1:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                        sender.say(t, "I'm sorry, " + sender.demonLord() + "!  It feels too good with you inside me!");
                    break;
                    default:
                        Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
                        sender.say(t, "Yes!  Yes!  Take me!  Take me!");
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
                    switch (sender.getConfidence() / 33) {
                        case 0: sender.say(t, "Mm!  Nn...!  Ah, I-I'm...!"); break;
                        case 1: sender.say(t, "Ah!  Ah, wow..."); break;
                        default: sender.say(t, "Aaagh...!  This shouldn't... feel so good...!");
                    }
                } else
                switch (sender.getInnocence() / 33) {
                case 0:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
                    sender.say(t, "Just... climax already, you brute...!");
                break;
                case 1:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.LEWD);
                    sender.say(t, "Mph!  Gh!  Agh!");
                break;
                default:
                    Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
                    sender.say(t, "Aaagh, gaaah!  Stop it, nnnaaah!");
                }
            } else
            {
                Project.changePortrait(sender.getGender(), sender.getType(), true, sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.FEAR);
                if(sender.getConfidence() > 66)
                {
                    switch (sender.getObedience() / 33) {
                        case 0: sender.say(t, "I'll stop fighting, I'll stop talking back, I'll do anything!"); break;
                        case 1: sender.say(t, "Why are you doing this!?  I'm serving you now!  Gaaah, pleeease!"); break;
                        default: sender.say(t, "I'm... guh... f-fine, " + sender.demonLord() + "!");
                    }
                } else
                if(sender.getConfidence() > 33)
                {
                    switch (sender.getDignity() / 33) {
                        case 0: sender.say(t, "AAAGH, NOOO!"); break;
                        case 1: sender.say(t, "Mgh...  guh... I-I can't..."); break;
                        default: sender.say(t, "Please, please!  Stooop!");
                    }
                } else
                switch (sender.getDeviancy() / 33) {
                    case 0: sender.say(t, "Agh!  Mph, guh, n-no...!"); break;
                    case 1: sender.say(t, "Aah, nnooo, oooh!"); break;
                    default: sender.say(t, "Nnnaaah~!");
                }
            }
        sender.say(t, "\"");
        sender.specialLine = 0;
    }

    public void shortDescription(JTextPane t, WorldState w, Body sender, Body receiver)
    {
        if(this == Project.TweakClit)
            w.append(t, String.format("\n%s is stroking %s's clit", sender.capitalizedOwnerName(), receiver.ownerName()));
        else
        if(this == Project.ClitTweaked)
            w.append(t, String.format("\n%s is stroking %s's clit", receiver.capitalizedOwnerName(), sender.ownerName()));
        else
        if(this == Project.SpreadLegs)
            w.append(t, String.format("\n%s is spreading %s legs wide apart", sender.capitalizedOwnerName(), sender.hisHer()));
        else
        if(this == Project.PushDown)
            w.append(t, String.format("\n%s is atop %s", sender.OwnerName(), receiver.ownerName()));
        else
        if(this == Project.PullDown)
            w.append(t, String.format("\n%s is atop %s", receiver.OwnerName(), sender.ownerName()));
        else
        if(this == Project.TieUp)
            w.append(t, String.format("\n%s has been tied up by %s", receiver.OwnerName(), sender.ownerName()));
        else
        if(this == Project.BeTied)
            w.append(t, String.format("\n%s has been tied up by %s", sender.OwnerName(), receiver.ownerName()));
        else
        if(this == Project.StrokeCock)
            w.append(t, String.format("\n%s strokes %s's cock", sender.OwnerName(), receiver.ownerName()));
        else
        if(this == Project.CockStroked)
            w.append(t, String.format("\n%s strokes %s's cock", receiver.OwnerName(), sender.ownerName()));
        else
        if(this == Project.BeLubricated)
            w.append(t, String.format("\n%s's anus is coated with slick lubricant", sender.OwnerName()));
        else
        if(this == Project.VaginalPenetrate)
            w.append(t, String.format("\n%s fucks %s vaginally", sender.OwnerName(), receiver.ownerName()));
        else
        if(this == Project.PenetratedVaginally)
            w.append(t, String.format("\n%s fucks %s vaginally", receiver.OwnerName(), sender.ownerName()));
        else
        if(this == Project.AnalPenetrate)
            w.append(t, String.format("\n%s fucks %s's ass", sender.OwnerName(), receiver.ownerName()));
        else
        if(this == Project.PenetratedAnally)
            w.append(t, String.format("\n%s fucks %s's ass", receiver.OwnerName(), sender.ownerName()));
        else
        if(this == Project.StripOther)
            w.append(t, String.format("\n%s strips off %s's clothes.", sender.OwnerName(), receiver.ownerName()));
        else
        if(this == Project.Stripped)
            w.append(t, String.format("\n%s strips off %s's clothes.", receiver.OwnerName(), sender.ownerName()));
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
            w.append(t, String.format("\n\n%s stands, letting %s up.", sender.capitalizedOwnerName(), receiver.ownerName()));
        else
        if(this == Project.PullDown)
            w.append(t, String.format("\n\n%s gets out from under %s.", sender.OwnerName(), receiver.ownerName()));
        else
        if(this == Project.TieUp)
            w.append(t, String.format("\n\n%s unties %s's bindings.", sender.OwnerName(), receiver.ownerName()));
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
            w.append(t, String.format("\n\n%s stops removing %s's clothes.", sender.OwnerName(), receiver.ownerName()));
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
                w.append(t, String.format("%s begins to caress %s's clit.", sender.capitalizedOwnerName(), receiver.ownerName()));
            else
            if(receiver == w.lordBody)
            {
                w.append(t, String.format("%s ", sender.capitalizedOwnerName()));
                if(sender.getDeviancy() > 66)
                {
                    switch (sender.getInnocence() / 33) {
                        case 0: w.append(t, String.format("abruptly puts %s hand against %s's clit and starts rubbing up and down, then fondling it with %1$s fingers, then gently tugging on it, using every technique at %1$s disposal.", sender.hisHer(), receiver.ownerName())); break;
                        case 1: w.append(t, String.format("reaches eagerly for %s's clit, stroking it with an aggressive firmness that comes just short of being painful.", receiver.ownerName())); break;
                        default: w.append(t, String.format("loses %sself in the act of rubbing %s's clit, eyes glazed over and drooling with desire.", sender.himHer(), receiver.ownerName()));
                    }
                } else
                if(sender.getDeviancy() > 33)
                {
                    switch (sender.getInnocence() / 33) {
                        case 0: w.append(t, String.format("carefully starts to stroke %s's clit, staring into %s eyes to gauge %2$s reaction.", receiver.ownerName(), receiver.hisHer())); break;
                        case 1: w.append(t, String.format("brushes %s fingers against %s's clit, trailing them up and down, then growing more firm, stroking it in earnest.", sender.hisHer(), receiver.ownerName())); break;
                        default: w.append(t, String.format("pokes and prods %s's clit, then gradually gets into a rhythm of stroking it as %s grows more confident in what %2$s's doing.", receiver.ownerName(), receiver.heShe()));
                    }
                } else
                {
                    switch (sender.getInnocence() / 33) {
                        case 0: w.append(t, String.format("brings %s fingers to %s's clit and begins to stroke it with precise, methodical movements.  ", sender.hisHer(), receiver.ownerName())); break;
                        case 1: w.append(t, String.format("begins to stroke %s's clit with stiff, mechanical movements.  ", receiver.ownerName())); break;
                        default: w.append(t, String.format("hesitantly touches %1$s's clit, then begins to clumsily stroke it, averting %s eyes as %s does so.  ", receiver.ownerName(), sender.hisHer(), sender.heShe()));
                    }
                    switch (sender.getObedience() / 33) {
                        case 0: w.append(t, String.format("%s looks sickened by what %s's doing.", sender.HeShe(), sender.heShe())); break;
                        case 1: w.append(t, String.format("%s heart clearly isn't in it.", sender.HisHer())); break;
                        default: w.append(t, String.format("It's clear that %1$s's unfamiliar with this sort of thing, but %1$s's doing %s best for %s.", sender.heShe(), sender.hisHer(), receiver.ownerName()));
                    }
                }
            }
        } else
        if(this == Project.ClitTweaked)
        {
            if(sender == w.lordBody)
            {
                w.append(t, String.format("%s's clit is caressed by %s.", sender.capitalizedOwnerName(), receiver.ownerName()));
            } else
            {
                w.append(t, sender.capitalizedOwnerName());
                if(sender.getInnocence() > 66)
                {
                    w.append(t, String.format(" gasps at the sudden intensity of the stimulation to %s most sensitive part, and ", sender.hisHer()));
                    switch (sender.getDeviancy() / 33) {
                        case 0: w.append(t, String.format("%s reflexively tries to jerk %s hips away.  ", sender.heShe(), sender.hisHer())); break;
                        case 1: w.append(t, String.format("%s hips jerk wildly, seemingly caught between jerking away and pushing themselves against %s's fingers.  ", sender.hisHer(), receiver.ownerName())); break;
                        default: w.append(t, String.format("%s eyes glaze over as %s loses the ability to think of anything other than feeling even better.  ", sender.hisHer(), sender.heShe()));
                    }
                } else
                if(sender.getInnocence() > 33)
                {
                    w.append(t, String.format(" feels jolts of pleasure shooting into %s lower tummy", sender.hisHer()));
                    switch (sender.getDeviancy() / 33) {
                        case 0: w.append(t, String.format(", even as %s tries to ignore them.  ", sender.heShe())); break;
                        case 1: w.append(t, String.format(", and it's a struggle for %s to hold onto %s reason.  ", sender.himHer(), sender.hisHer())); break;
                        default: w.append(t, String.format(", and %1$s moans helplessly, %s well-trained body eagerly submitting itself to %s's touch.  ", sender.heShe(), sender.hisHer(), receiver.ownerName()));
                    }
                } else
                {
                    w.append(t, String.format(" knew this was coming, but %s still ", sender.heShe()));
                    switch (sender.getDeviancy() / 33) {
                        case 0: w.append(t, "flinches and grimaces, uncomfortable with the intensity of the sensations.  "); break;
                        case 1: w.append(t, String.format("gasps softly at the waves of pleasure that begin to wash over %s lower body.  ", sender.hisHer())); break;
                        default: w.append(t, String.format("squirms and cries out, unable to maintain any self-control in the face of the pleasure %s's come to crave.  ", sender.heShe()));
                    }
                }
                if(receiver == w.lordBody)
                    if(sender.getDeviancy() > 66)
                    {
                        switch (sender.getObedience() / 33) {
                            case 0: w.append(t, String.format("%s tries to resist, but %s body has grown so sensitive that the lightest touch to %2$s weak spots is enough to subdue %s.", sender.HeShe(), sender.hisHer(), sender.himHer())); break;
                            case 1: w.append(t, String.format("Soon, %1$s's completely consumed in %s efforts to hump %s's hand.", sender.heShe(), sender.hisHer(), receiver.ownerName())); break;
                            default: w.append(t, String.format("%s body has grown quite sensitive, but it's even more sensitive than usual when %s knows %2$s's being touched by %s.", sender.HisHer(), sender.heShe(), receiver.ownerName()));
                        }
                    } else
                    if(sender.getDeviancy() > 33)
                    {
                        switch (sender.getObedience() / 33) {
                            case 0: w.append(t, String.format("%s has a hard time remembering that %s's supposed to hate the Demon Lord.", sender.HeShe(), sender.heShe())); break;
                            case 1: w.append(t, String.format("%s's content to let %s pleasure %s.", sender.HeShe(), receiver.ownerName(), sender.himHer())); break;
                            default: w.append(t, String.format("Even more than the physical stimulation itself, %1$s is ecstatic that %s is pleasuring %s directly.", sender.ownerName(), receiver.ownerName(), sender.himHer()));
                        }
                    } else
                    switch (sender.getObedience() / 33) {
                        case 0: w.append(t, String.format("%s glares at %s, not appreciating it in the slightest.", sender.HeShe(), receiver.ownerName())); break;
                        case 1: w.append(t, String.format("%s doesn't make eye contact with %s, glancing off to the side.", sender.HeShe(), receiver.ownerName())); break;
                        default: w.append(t, String.format("%s endures this for %s's sake, hoping that %2$s will enjoy playing with %s body.", sender.HeShe(), receiver.ownerName(), sender.hisHer()));
                    }
            }
        } else
        if(this == Project.SpreadLegs)
        {
            if(sender != w.lordBody)
                w.append(t, String.format("%s spreads %s legs wide apart, eager to be pleasured.", sender.ownerName(), sender.hisHer()));
        } else
        if(this == Project.Praise)
        {
            if(sender != w.lordBody && receiver == w.lordBody)
                if(sender.getObedience() > 66)
                {
                    switch (sender.getConfidence() / 33) {
                        case 0: w.append(t, String.format("%s viciously insults %sself, offering %s body to %s in order to make up for %s own failures.", sender.OwnerName(), sender.himHer(), sender.hisHer(), receiver.ownerName(), sender.hisHer())); break;
                        case 1: w.append(t, String.format("%s voices %s admiration for %s, describing all %s great qualities at length.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), receiver.hisHer())); break;
                        default: w.append(t, String.format("%s repeatedly, insistently offers to do anything at all that %s desires of %s.", sender.OwnerName(), receiver.ownerName(), sender.himHer()));
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    switch (sender.getDeviancy() / 33) {
                        case 0: w.append(t, String.format("%s humbly debases %sself and praises %s, but the quaver in %s voice betrays the fact that %s's doing so out of fear rather than because %5$s actually believes %s own words.", sender.OwnerName(), sender.himHer(), receiver.ownerName(), sender.hisHer(), sender.heShe(), sender.hisHer())); break;
                        case 1: w.append(t, String.format("%s encourages %s to do whatever %s'd like with %s.", sender.OwnerName(), receiver.ownerName(), receiver.heShe(), sender.himHer())); break;
                        default: w.append(t, String.format("%s eagerly lavishes praise and encouragement on %s, hoping to be rewarded with pleasure.", sender.OwnerName(), receiver.ownerName()));
                    }
                } else
                switch (sender.getInnocence() / 33) {
                    case 0: w.append(t, String.format("%s acts like %s's coming around to %s's side, giving voice to some compliments, but %2$s's actually just hoping to manipulate %s.", sender.OwnerName(), sender.heShe(), receiver.ownerName(), receiver.himHer())); break;
                    case 1: w.append(t, String.format("%s grudgingly compliments %s, though %s isn't happy about it.", sender.OwnerName(), receiver.ownerName(), sender.heShe())); break;
                    default: w.append(t, String.format("%s starts to praise %s before %s remembers that they're supposed to be enemies.", sender.OwnerName(), receiver.ownerName(), sender.heShe()));
                }
        } else
        if(this == Project.Insult)
        {
            if(sender != w.lordBody && receiver == w.lordBody)
                if(sender.getObedience() > 66)
                {
                    if(sender.getINJULevel() >= 2)
                        w.append(t, String.format("%s is growing too tired to serve %s, and %s desperately tries to justify %s own weakness.", sender.OwnerName(), receiver.ownerName(), sender.heShe(), sender.hisHer()));
                    else
                    if(sender.getHATELevel() >= 1)
                        w.append(t, String.format("%s is becoming agitated, and while %s adores %s too much to confront %s directly, %s feelings still come through.", sender.OwnerName(), sender.heShe(), receiver.ownerName(), receiver.himHer(), sender.hisHer()));
                    else
                        w.append(t, String.format("%s's psyche, fragile after being trained so thoroughly by the Demon Lord, begins to crack under the strain.", sender.OwnerName()));
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.getINJULevel() >= 2)
                        w.append(t, String.format("%s is getting tired, and %s lets %s knows that %2$s wants to stop this.", sender.OwnerName(), sender.heShe(), receiver.ownerName()));
                    else
                    if(sender.getHATELevel() >= 1)
                        w.append(t, String.format("%s is growing annoyed with %s, and %s lets %s know it in no uncertain terms.", sender.OwnerName(), receiver.ownerName(), sender.heShe(), receiver.himHer()));
                    else
                        w.append(t, String.format("%s insults %s, hoping to get a reaction.", sender.OwnerName(), receiver.ownerName()));
                } else
                if(sender.getINJULevel() >= 2)
                    w.append(t, String.format("%s is too tired to resist physically, but %s still verbally attacks %s.", sender.OwnerName(), sender.heShe(), receiver.ownerName()));
                else
                if(sender.getHATELevel() >= 1)
                    w.append(t, String.format("%s already hates %s, but %s's even angrier than usual at being forced into this, and %3$s vents %s frustration with a stream of insults", sender.OwnerName(), receiver.ownerName(), sender.heShe(), sender.hisHer()));
                else
                    w.append(t, String.format("%s takes the chance to insult %s to %s face.", sender.OwnerName(), receiver.ownerName(), receiver.hisHer()));
        } else
        if(this == Project.PushDown)
        {
            if(sender == w.lordBody)
                w.append(t, String.format("%s looms over %s.", sender.OwnerName(), receiver.ownerName()));
            else
            if(sender.getDeviancy() > 66)
            {
                switch (sender.getConfidence() / 33) {
                    case 0: w.append(t, String.format("%s's lust has made %s far more bold than usual, and %s eagerly pushes %s down onto %s back.", sender.OwnerName(), sender.himHer(), sender.heShe(), receiver.ownerName(), receiver.hisHer())); break;
                    case 1: w.append(t, String.format("%s practically tackles %s down onto the floor, overflowing with lust.", sender.OwnerName(), receiver.ownerName())); break;
                    default: w.append(t, String.format("%s roughly pushes %s down onto %s back, panting with barely-restrained desire as %s takes %s place atop %s.", sender.OwnerName(), receiver.ownerName(), receiver.hisHer(), sender.heShe(), sender.hisHer(), receiver.himHer()));
                }
            } else
            if(sender.getDeviancy() > 33)
            {
                switch (sender.getInnocence() / 33) {
                    case 0: w.append(t, String.format("With %s eyes locked onto %s's %s, %s urges %s to lay down on %s back, then climbs atop %s.", sender.hisHer(), receiver.ownerName(), receiver.mainOrgan(), sender.ownerName(), receiver.himHer(), receiver.hisHer(), receiver.himHer())); break;
                    case 1: w.append(t, String.format("%s embraces %s, guiding %s down onto %s back.", sender.OwnerName(), receiver.ownerName(), receiver.himHer(), receiver.hisHer())); break;
                    default: w.append(t, String.format("Overcome by %s growing lust, %s wraps %1$s arms around %s and kisses %s repeatedly, then seems surprised to realize that %s's pushed %s down to the floor in the process.", sender.hisHer(), sender.ownerName(), receiver.ownerName(), receiver.himHer(), sender.heShe(), receiver.himHer()));
                }
            } else
            switch (sender.getObedience() / 33) {
                case 0: w.append(t, String.format("With an annoyed expression on %s face, %s pushes %s down onto %s back without a hint of sensuality.", sender.hisHer(), sender.ownerName(), receiver.ownerName(), receiver.hisHer())); break;
                case 1: w.append(t, String.format("%s pushes %s down onto %s back, but hesitates before going further, uncertain about how to proceed.", sender.OwnerName(), receiver.ownerName(), receiver.hisHer())); break;
                default: w.append(t, String.format("%s gently guides %s onto %s back, then lays atop %s, taking deep breaths and trying to ready %sself to serve %s.", sender.OwnerName(), receiver.ownerName(), receiver.hisHer(), receiver.himHer(), sender.himHer(), sender.theDemonLord()));
            }
        } else
        if(this == Project.PullDown)
        {
            if(sender == w.lordBody)
                w.append(t, String.format("%s is laid down under %s.", sender.OwnerName(), receiver.ownerName()));
            else
            if(sender.getDeviancy() > 66)
            {
                switch (sender.getConfidence() / 33) {
                    case 0: w.append(t, String.format("%s lays down on %s back, weakly trying to pull %s down with %s.  %s trembles with desire as %s looks up at %s.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.himHer(), sender.HeShe(), sender.heShe(), receiver.ownerName())); break;
                    case 1: w.append(t, String.format("%s tangles %s limbs around %s, bringing them both down to the floor together with %3$s atop %s.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.himHer())); break;
                    default: w.append(t, String.format("%s forcefully pulls %s atop %s, aggressive even in %s submission.", sender.OwnerName(), receiver.ownerName(), sender.himHer(), sender.hisHer()));
                }
            } else
            if(sender.getDeviancy() > 33)
            {
                switch (sender.getDignity() / 33) {
                    case 0: w.append(t, String.format("%s begs %s to take %s, positioning %3$sself underneath %s.", sender.OwnerName(), receiver.ownerName(), sender.himHer(), receiver.himHer())); break;
                    case 1: w.append(t, String.format("%s eagerly pulls %s down atop %s, smiling in anticipation of what's to come.", sender.OwnerName(), receiver.ownerName(), sender.himHer())); break;
                    default: w.append(t, String.format("%s lays down under %s, trying to act as though %s's doing %s a favor, but %s's lustful panting betrays how much %s wants to be taken.", sender.OwnerName(), receiver.ownerName(), sender.heShe(), receiver.himHer(), sender.ownerName(), sender.heShe()));
                }
            } else
            switch (sender.getObedience() / 33) {
                case 0: w.append(t, String.format("%s angrily lays down, glaring up at %s.", sender.OwnerName(), receiver.ownerName())); break;
                case 1: w.append(t, String.format("%s lays down for %s, but %s refuses to meet %s eyes, still uncertain how %s feels about sex with the Demon Lord.", sender.OwnerName(), receiver.ownerName(), sender.heShe(), receiver.hisHer(), sender.heShe())); break;
                default: w.append(t, String.format("%s submissively presents %sself to %s, layin on %s back and squirming nervously as %s anticipates how %5$s'll be used.", sender.OwnerName(), sender.himHer(), receiver.ownerName(), sender.hisHer(), sender.heShe()));
            }
        } else
        if(this == Project.Escape)
        {
            if(sender != w.lordBody)
                if(sender.getObedience() > 66)
                {
                    switch (sender.getMorality() / 33) {
                        case 0: w.append(t, String.format("%s shoves %s and scrambles away, briefly forgetting %s devotion in the face of %3$s intense emotions.", sender.OwnerName(), receiver.ownerName(), sender.hisHer())); break;
                        case 1: w.append(t, String.format("%s abruptly starts struggling to escape %s, and it's only after %s's free that %3$s hangs %s head in shame for being so disobedient.", sender.OwnerName(), receiver.ownerName(), sender.heShe(), sender.hisHer())); break;
                        default: w.append(t, String.format("%s cries out in despair as %s turns and crawls away from %s, already consumed by self-loathing for %s failure to properly serve %s.", sender.OwnerName(), sender.heShe(), receiver.ownerName(), sender.hisHer(), sender.theDemonLord()));
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    switch (sender.getInnocence() / 33) {
                        case 0: w.append(t, String.format("%s clearly states that %s's done with this and moves to free %sself.  The quaver in %s voice is the only thing that betrays %4$s worries about the consequences of rejecting %s.", sender.OwnerName(), sender.heShe(), sender.himHer(), sender.hisHer(), sender.theDemonLord())); break;
                        case 1: w.append(t, String.format("Apologizing profusely, %s disentangles %sself from %s and takes a few steps backward.", sender.ownerName(), sender.himHer(), receiver.ownerName())); break;
                        default: w.append(t, String.format("%s cries out in panic and shoves %s away.  Only a moment later does %s realize what %3$s's done, and %3$s cowers in fear of punishment.", sender.OwnerName(), receiver.ownerName(), sender.heShe()));
                    }
                } else
                switch (sender.getConfidence() / 33) {
                    case 0: w.append(t, String.format("%s flails in panic, then makes an effort to run away.", sender.OwnerName())); break;
                    case 1: w.append(t, String.format("%s, overwhelmed by %s hatred for the Demon Lord, starts struggling to get away before %s knows what %3$s's doing.  However, %3$s stands tall afterward, regaining %2$s poise as if daring %s to punish %s.", sender.OwnerName(), sender.hisHer(), sender.heShe(), receiver.ownerName(), sender.himHer())); break;
                    default: w.append(t, String.format("%s starts to viciously beat %s, remaining hostile even after %s frees %sself.", sender.OwnerName(), receiver.ownerName(), sender.heShe(), sender.himHer()));
                }
        } else
        if(this == Project.StopActing)
        {
            if(sender != w.lordBody)
                if(sender.getObedience() > 66)
                {
                    switch (sender.getMorality() / 33) {
                        case 0: w.append(t, String.format("%s is sincere about wanting to service %s, but %s begins getting lazier and lazier with %s movements until %s's not actually making %2$s feel good at all.", sender.OwnerName(), receiver.ownerName(), sender.heShe(), sender.hisHer(), sender.heShe())); break;
                        case 1: w.append(t, String.format("%s keeps trying to service %s until %s own body starts to grow too exhausted to move.  Eventually, %s realizes that %4$s's too tired to effectively please %2$s anymore.", sender.OwnerName(), receiver.ownerName(), sender.hisHer(), sender.heShe())); break;
                        default: w.append(t, String.format("%s confesses that %s's grown too tired to keep servicing %s, then eagerly proposes that %3$s punish %s for %s weakness.", sender.OwnerName(), sender.heShe(), receiver.ownerName(), sender.himHer(), sender.hisHer()));
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    switch (sender.getDeviancy() / 33) {
                        case 0: w.append(t, String.format("%s starts to get distracted and make excuses for why %s should be allowed to leave, and %2$s stops trying to pleasure %s.", sender.OwnerName(), sender.heShe(), receiver.ownerName())); break;
                        case 1: w.append(t, String.format("%s gradually starts to grow less and less interested in pleasuring %s, and finally %s stops completely.", sender.OwnerName(), receiver.ownerName(), sender.heShe())); break;
                        default: w.append(t, String.format("%s heaves a satisfied sigh, %s tremendous lust finally satisfied.  %s doesn't seem interested in continuing for now.", sender.OwnerName(), sender.hisHer(), sender.HeShe()));
                    }
                } else
                switch (sender.getInnocence() / 33) {
                    case 0: w.append(t, String.format("%s, deciding that %s's cooperated long enough, halts %s movements and glares at %s as if daring %s to punish %s.", sender.OwnerName(), sender.heShe(), sender.hisHer(), receiver.ownerName(), receiver.himHer(), sender.himHer())); break;
                    case 1: w.append(t, String.format("%s gets angry and refuses to cooperate further.  %s turns %s head away, refusing to even look at %s.", sender.OwnerName(), sender.HeShe(), sender.hisHer(), receiver.ownerName())); break;
                    default: w.append(t, String.format("%s abruptly seems to remember that %s is the enemy, and %s refuses to continue this any longer.", sender.OwnerName(), receiver.ownerName(), sender.heShe()));
                }
        } else
        if(this == Project.TieUp)
        {
            if(sender == w.lordBody)
                w.append(t, String.format("%s ties down %s's arms and legs, leaving %s helpless.", sender.OwnerName(), receiver.ownerName(), receiver.himHer()));
        } else
        if(this == Project.BeTied)
        {
            if(sender != w.lordBody)
                if(sender.getObedience() > 66)
                {
                    switch (sender.getInnocence() / 33) {
                        case 0: w.append(t, String.format("%s happily cooperates, holding %s limbs as %s's directed in order to help speed the process.", sender.OwnerName(), sender.hisHer(), sender.heShe())); break;
                        case 1: w.append(t, String.format("%s doesn't resist in the slightest, happy to let %s enjoy %s body however %s wishes.", sender.OwnerName(), receiver.ownerName(), sender.hisHer(), receiver.heShe())); break;
                        default: w.append(t, String.format("%s's eyes glaze over with pure bliss as %s enjoys entrusting %s body to %s.", sender.OwnerName(), sender.heShe(), sender.hisHer(), receiver.ownerName()));
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    switch (sender.getConfidence() / 33) {
                        case 0: w.append(t, String.format("%s starts to panic, but %s's too frightened to actively resist.", sender.OwnerName(), sender.heShe())); break;
                        case 1: w.append(t, String.format("%s flinches in trepidation, imagining what %s will be doing to %s next.", sender.OwnerName(), receiver.ownerName(), sender.himHer())); break;
                        default: w.append(t, String.format("%s holds %s head high as %s's tied up, refusing to let %sself show fear.", sender.OwnerName(), sender.hisHer(), sender.heShe(), sender.himHer()));
                    }
                } else
                if(sender.getDeviancy() > 66)
                    w.append(t, String.format("%s tries to resist, but %s lustful body betrays %s, and by the time %s's stopped daydreaming about what kind of sexual punishment awaits %s, %s's already been tied up.", sender.OwnerName(), sender.hisHer(), sender.himHer(), sender.heShe(), sender.himHer(), sender.heShe()));
                else
                if(sender.getDeviancy() > 33)
                {
                    w.append(t, String.format("%s puts up a bit of resistance.  However, %s's weaker than %2$s should be, and ", sender.OwnerName(), sender.heShe()));
                    w.append(t, String.format(sender.parts[PENIS] > 0 ? "the stiffness between %s legs" : "the wetness on %s thighs", sender.hisHer()));
                    w.append(t, String.format(" shows that a part of %s is eager to be punished.", sender.himHer()));
                } else
                {
                    w.append(t, String.format("%s fights back with all %s might, but %s can't win against %s.  Soon, there's nothing %s can do but rock back and forth while growling at %2$s captor.", sender.OwnerName(), sender.hisHer(), sender.heShe(), receiver.ownerName(), sender.heShe()));
                }
        } else
        if(this == Project.StrokeCock)
        {
            if(sender == w.lordBody)
            {
                w.append(t, String.format("%s begins to pump %s hand up and down %s's cock.", sender.OwnerName(), sender.hisHer(), receiver.ownerName()));
            } else
            {
                w.append(t, String.format("%s ", sender.capitalizedOwnerName()));
                if(sender.getDeviancy() > 66)
                {
                    switch (sender.getInnocence() / 33) {
                        case 0: w.append(t, String.format("abruptly wraps %s fingers around %s's cock and starts stroking it gently, then fondling the tip, then gently tugging on it, using every technique at %1$s disposal.", sender.hisHer(), receiver.ownerName())); break;
                        case 1: w.append(t, String.format("reaches eagerly for %s's cock, pumping it up and down with an aggressive firmness that comes just short of being painful.", receiver.ownerName())); break;
                        default: w.append(t, String.format("loses %sself in the act of running %s hand up and down %s's cock, eyes glazed over and drooling with desire.", sender.himHer(), sender.hisHer(), receiver.ownerName()));
                    }
                } else
                if(sender.getDeviancy() > 33)
                {
                    switch (sender.getInnocence() / 33) {
                        case 0: w.append(t, String.format("carefully starts to stroke %s's cock, staring into %s eyes to gauge %2$s reaction.", receiver.ownerName(), receiver.hisHer())); break;
                        case 1: w.append(t, String.format("brushes %s fingers against %s's cock, trailing them up and down, then growing more firm, stroking it in earnest.", sender.hisHer(), receiver.ownerName())); break;
                        default: w.append(t, String.format("tugs and jerks %s's cock, then gradually gets into a rhythm of stroking it as %s grows more confident in what %2$s's doing.", receiver.ownerName(), receiver.heShe()));
                    }
                } else
                {
                    switch (sender.getInnocence() / 33) {
                        case 0: w.append(t, String.format("brings %s hand to %s's cock and begins to stroke it with precise, methodical movements.  ", sender.hisHer(), receiver.ownerName())); break;
                        case 1: w.append(t, String.format("begins to stroke %s's cock with stiff, mechanical movements.  ", receiver.ownerName())); break;
                        default: w.append(t, String.format("hesitantly touches %s's cock, then begins to clumsily stroke it, averting %s eyes as %s does so.  ", receiver.ownerName(), sender.hisHer(), sender.heShe()));
                    }
                    switch (sender.getObedience() / 33) {
                        case 0: w.append(t, String.format("%s looks sickened by what %s's doing.", sender.HeShe(), sender.heShe())); break;
                        case 1: w.append(t, String.format("%s heart clearly isn't in it.", sender.HisHer())); break;
                        default: w.append(t, String.format("It's clear that %s's unfamiliar with this sort of thing, but %1$s's doing %s best for %s.", sender.heShe(), sender.hisHer(), receiver.ownerName()));
                    }
                }
            }
        } else
        if(this == Project.CockStroked)
        {
            if(sender == w.lordBody)
            {
                w.append(t, String.format("%s's cock pulses under %s's fingers.", sender.OwnerName(), receiver.ownerName()));
            } else
            {
                w.append(t, sender.capitalizedOwnerName());
                if(sender.getInnocence() > 66)
                {
                    w.append(t, String.format(" gasps at the sudden intensity of the stimulation to %s most sensitive part, and ", sender.hisHer()));
                    switch (sender.getDeviancy() / 33) {
                        case 0: w.append(t, String.format("%s reflexively tries to jerk %s hips away.  ", sender.heShe(), sender.hisHer())); break;
                        case 1: w.append(t, String.format("%s hips jerk wildly, seemingly caught between jerking away and pushing further into %s's hand.  ", sender.hisHer(), receiver.ownerName())); break;
                        default: w.append(t, String.format("%s eyes glaze over as %s loses the ability to think of anything other than feeling even better.  ", sender.hisHer(), sender.heShe()));
                    }
                } else
                if(sender.getInnocence() > 33)
                {
                    w.append(t, String.format(" feels jolts of pleasure shooting through %s shaft", sender.hisHer()));
                    switch (sender.getDeviancy() / 33) {
                        case 0: w.append(t, String.format(", even as %s tries to ignore them.  ", sender.heShe())); break;
                        case 1: w.append(t, String.format(", and it's a struggle for %s to hold onto %s reason.  ", sender.himHer(), sender.hisHer())); break;
                        default: w.append(t, String.format(", and %s moans helplessly, %s well-trained body eagerly submitting itself to %s's touch.  ", sender.heShe(), sender.hisHer(), receiver.ownerName()));
                    }
                } else
                {
                    w.append(t, String.format(" knew this was coming, but %s still ", sender.heShe()));
                    switch (sender.getDeviancy() / 33) {
                        case 0: w.append(t, "flinches and grimaces, uncomfortable with the intensity of the sensations.  "); break;
                        case 1: w.append(t, String.format("gasps softly at the waves of pleasure that begin to wash over %s lower body.  ", sender.hisHer())); break;
                        default: w.append(t, String.format("squirms and cries out, unable to maintain any self-control in the face of the pleasure %s's come to crave.  ", sender.heShe()));
                    }
                }
                if(receiver == w.lordBody)
                    if(sender.getDeviancy() > 66)
                    {
                        switch (sender.getObedience() / 33) {
                            case 0: w.append(t, String.format("%s tries to resist, but %s body has grown so sensitive that the lightest touch to %2$s weak spots is enough to subdue %s.", sender.HeShe(), sender.hisHer(), sender.himHer())); break;
                            case 1: w.append(t, String.format("Soon, %s's completely consumed in %s efforts to hump %s's hand.", sender.heShe(), sender.hisHer(), receiver.ownerName())); break;
                            default: w.append(t, String.format("%s body has grown quite sensitive, but it's even more sensitive than usual when %s knows %2$s's being touched by %s.", sender.HisHer(), sender.heShe(), receiver.ownerName()));
                        }
                    } else
                    if(sender.getDeviancy() > 33)
                    {
                        switch (sender.getObedience() / 33) {
                            case 0: w.append(t, String.format("%s has a hard time remembering that %s's supposed to hate the Demon Lord.", sender.HeShe(), sender.heShe())); break;
                            case 1: w.append(t, String.format("%s's content to let %s pleasure %s.", sender.HeShe(), receiver.ownerName(), sender.himHer())); break;
                            default: w.append(t, String.format("Even more than the physical stimulation itself, %s is ecstatic that %s is pleasuring %s directly.", sender.ownerName(), receiver.ownerName(), sender.himHer()));
                        }
                    } else
                    switch (sender.getObedience() / 33) {
                        case 0: w.append(t, String.format("%s glares at %s, not appreciating it in the slightest.", sender.HeShe(), receiver.ownerName())); break;
                        case 1: w.append(t, String.format("%s doesn't make eye contact with %s, glancing off to the side.", sender.HeShe(), receiver.ownerName())); break;
                        default: w.append(t, String.format("%s endures this for %s's sake, hoping that %2$s will enjoy playing with %s body.", sender.HeShe(), receiver.ownerName(), sender.hisHer()));
                    }
            }
        } else
        if(this == Project.Lubricate)
        {
            if(sender == w.lordBody)
                w.append(t, String.format("%s gathers a generous amount of slippery lubricant on one finger, then uses it to coat %s's anus.", sender.OwnerName(), receiver.ownerName()));
        } else
        if(this == Project.BeLubricated)
        {
            if(sender != w.lordBody)
                if(sender.getDeviancy() > 66)
                {
                    switch (sender.getObedience() / 33) {
                        case 0: w.append(t, String.format("%s tries to keep acting tough, but %s breaks down into a stream of pitiful moans and halfhearted protests as the light stimulation is enough to break %s concentration.", sender.OwnerName(), sender.heShe(), sender.hisHer())); break;
                        case 1: w.append(t, String.format("%s looks nervous, but also eager.  %s grins down at %sself, imagining what will be done to %3$s next.", sender.OwnerName(), sender.HeShe(), sender.himHer())); break;
                        default: w.append(t, String.format("%s moans and spreads %s legs as wide as %s can, eager for more than just a finger.", sender.OwnerName(), sender.hisHer(), sender.heShe()));
                    }
                } else
                if(sender.getDeviancy() > 33)
                {
                    switch (sender.getDignity() / 33) {
                        case 0: w.append(t, String.format("%s groans and reflexively tries to buck %s hips down onto the finger.", sender.OwnerName(), sender.hisHer())); break;
                        case 1: w.append(t, String.format("%s squirms and blushes, clearly a bit ashamed at being excited by the stimulation back there.", sender.OwnerName())); break;
                        default: w.append(t, String.format("%s inhales sharply, but doesn't show any other sign that %s's feeling anything from this.", sender.OwnerName(), sender.heShe()));
                    }
                } else
                switch (sender.getInnocence() / 33) {
                    case 0: w.append(t, String.format("%s shudders at the unpleasant sensation, then tries to relax and accept it.", sender.OwnerName())); break;
                    case 1: w.append(t, String.format("%s's eyes go wide and %s tries to look down at what's happening down there.", sender.OwnerName(), sender.heShe())); break;
                    default: w.append(t, String.format("%s squirms in discomfort at the strange feeling in a place %s doesn't even mentally associate with sex.", sender.OwnerName(), sender.heShe()));
                }
        } else
        if(this == Project.VaginalPenetrate)
        {
            if(Project.PushDown.isInProgress(sender, receiver))
            {
                if(sender == w.lordBody)
                    w.append(t, String.format("%s puts the tip of %s cock against %s's pussy, then thrusts inside.", sender.OwnerName(), sender.hisHer(), receiver.ownerName()));
                else
                if(sender.getDeviancy() > 66)
                {
                    switch (sender.getConfidence() / 33) {
                        case 0: w.append(t, String.format("%s's timid nature seems to vanish entirely, and %s starts to ram %s hips forward with wild abandon, hammering in and out of %s's pussy.", sender.OwnerName(), sender.heShe(), sender.hisHer(), receiver.ownerName())); break;
                        case 1: w.append(t, String.format("%s starts to slowly push %s cock into %s, but the surge of pleasure from having just the tip inside overwhelms %2$s reason.  %s jerks %2$s hips forward, burying %sself inside %s's pussy, then pulls halfway out, then pushes back in, hips jerking wildly as intense pleasure surges through %s with every movement.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.HeShe(), sender.himHer(), receiver.ownerName(), sender.himHer())); break;
                        default: w.append(t, String.format("%s eagerly pins %s down and shoves %s shaft into %2$s's pussy, immediately setting a rapid pace of thrusting in and out.  It's a struggle to keep moving steadily with the flashes of ecstasy shooting through %s, but %s is determined to keep going.", sender.OwnerName(), receiver.ownerName(), sender.hisHer(), sender.himHer(), sender.ownerName()));
                    }
                } else
                if(sender.getDeviancy() > 33)
                {
                    switch (sender.getInnocence() / 33) {
                        case 0: w.append(t, String.format("%s gradually pushes %s cock into %s's pussy, inch-by-inch, then withdraws it just as carefully.  %s moves slowly, feeling out which spots feel better for %s and which seem to get more of a reaction from %s, and only then starts to move more quickly, getting into a rhythm.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.HeShe(), sender.himHer(), receiver.ownerName())); break;
                        case 1: w.append(t, String.format("%s starts to fuck %s's pussy, but the pleasure of %s folds squeezing down on %s cock gives %s pause.  %s gasps, taking a moment to catch %s breath, then continues more slowly, enjoying the warm wetness around %7$s shaft.", sender.OwnerName(), receiver.ownerName(), receiver.hisHer(), sender.himHer(), sender.ownerName(), sender.HeShe(), sender.hisHer())); break;
                        default: w.append(t, String.format("%s pushes %s cock into %s's pussy, and the moment %s feels %s folds squeezing down on %s, %s loses the ability to think of anything but the pleasure.  Moaning softly, %7$s starts thrusting in and out, acting on pure instinct.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.heShe(), receiver.hisHer(), sender.himHer(), sender.heShe()));
                    }
                } else
                switch (sender.getObedience() / 33) {
                    case 0: w.append(t, String.format("%s's angry demeanor fades for just a moment as %s thrusts into %s and feels the intense pleasure of %s folds squeezing %s.  But then %2$s recovers and starts to move violently in and out, as if stabbing %s with a weapon.", sender.OwnerName(), sender.heShe(), receiver.ownerName(), receiver.hisHer(), sender.himHer(), receiver.ownerName())); break;
                    case 1: w.append(t, String.format("%s presses the tip of %s cock against %s's pussy, hesitates for a moment, and then finally pushes it inside.  %s gasps and shudders at the pleasure, far beyond what %s was prepared for, and then cautiously starts to move %2$s hips forward and back.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.HeShe(), sender.heShe())); break;
                    default: w.append(t, String.format("%s eagerly thrusts %s cock inside %s's pussy, ecstatic to be joined with %s.  %s moves unselfishly, trying to hit all of %s's sensitive parts without any regard for %2$s own pleasure.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), receiver.himHer(), sender.HeShe(), receiver.ownerName()));
                }
            } else
            if(sender == w.lordBody)
                w.append(t, String.format("%s's cock pushes all the way into %s's depths.", sender.OwnerName(), receiver.ownerName()));
            else
            if(sender.getDeviancy() > 66)
            {
                switch (sender.getInnocence() / 33) {
                    case 0: w.append(t, String.format("At first, %s tries to reciprocate with %s own thrusts.  But it soon becomes clear that %s's completely at %s's mercy, and all %s can do is moan and jerk %2$s hips as %s slides up and down %2$s shaft.", sender.ownerName(), sender.hisHer(), sender.heShe(), receiver.ownerName(), sender.heShe(), receiver.ownerName())); break;
                    case 1: w.append(t, String.format("The feeling of %s sliding down to the base of %s shaft is enough to make %s cry out and lose %2$s sense of reason.  %s desperately clings to %1$s, hips jerking wildly up and down as %s tries to thrust even deeper into %s.", receiver.ownerName(), sender.hisHer(), sender.ownerName(), sender.HeShe(), sender.heShe(), receiver.himHer())); break;
                    default: w.append(t, String.format("%s's whole body goes slack except for %s hips desperately trying to thrust deeper into %s.  %s eyes roll and %2$s tongue hangs out.", sender.OwnerName(), sender.hisHer(), receiver.himHer(), sender.HisHer()));
                }
            } else
            if(sender.getDeviancy() > 33)
            {
                switch (sender.getDignity() / 33) {
                    case 0: w.append(t, String.format("%s gasps with pleasure, reflexively embracing %s and thrusting %s own hips in time with %2$s's movements.", sender.OwnerName(), receiver.ownerName(), sender.hisHer())); break;
                    case 1: w.append(t, String.format("%s lays back and loses %sself in the feeling of %s sliding up and down %s length, overwhelmed by the intensity of the sensations.", sender.OwnerName(), sender.himHer(), receiver.ownerName(), sender.hisHer())); break;
                    default: w.append(t, String.format("%s tries to hide just how good it feels, but when %s clenches around %s, %s gasps and reflexively bucks %s hips.  After that, %s has a harder and harder time suppressing %s moans.", sender.OwnerName(), receiver.ownerName(), sender.himHer(), sender.heShe(), sender.hisHer(), sender.heShe(), sender.hisHer()));
                }
            } else
            switch (sender.getObedience() / 33) {
                case 0: w.append(t, String.format("%s struggles to keep glaring at %s, wincing slightly every time %2$s squeezes the base of %s shaft and breaks %3$s concentration.", sender.OwnerName(), receiver.ownerName(), sender.hisHer())); break;
                case 1: w.append(t, String.format("%s tries to relax and enjoy it, but even as %s slides up and down %s shaft, %s can't quite shake off the fear that this pleasure is just meant to make %s let %s guard down.", sender.OwnerName(), receiver.ownerName(), sender.hisHer(), sender.ownerName(), sender.himHer(), sender.hisHer())); break;
                default: w.append(t, String.format("%s winces and struggles not to cum right away, wanting to ensure that %s stays hard for %s as long as possible.", sender.OwnerName(), sender.heShe(), receiver.ownerName()));
            }
        } else
        if(this == Project.PenetratedVaginally)
        {
            if(Project.PushDown.isInProgress(sender, receiver))
            {
                if(sender == w.lordBody)
                    w.append(t, String.format("%s mounts %s and lowers %sself until %s pussy envelops %2$s's cock.", sender.OwnerName(), receiver.ownerName(), sender.himHer(), sender.hisHer()));
                else
                if(sender.getDeviancy() > 66)
                {
                    switch (sender.getObedience() / 33) {
                        case 0: w.append(t, String.format("%s tries to resist %s urges, but this close to %s's cock, %s can't stop %sself from grinding against it.  Then, before %s realizes it, %6$s's taken it into %2$s pussy.  The surge of pleasure destroys the last of %2$s reason, and %6$s starts moving in earnest.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.heShe(), sender.himHer(), sender.heShe())); break;
                        case 1: w.append(t, String.format("%s quickly lowers %sself onto %s's cock, then immediately begins to bounce on %s lap, searching for the precise angle where it pokes just the right spot inside %s pussy.", sender.OwnerName(), sender.himHer(), receiver.ownerName(), receiver.hisHer(), sender.hisHer())); break;
                        default: w.append(t, String.format("%s eagerly impales %s pussy on %s's upright cock, sliding %sself up and down with manic energy while grinning at %s.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.himHer(), receiver.himHer()));
                    }
                } else
                if(sender.getDeviancy() > 33)
                {
                    switch (sender.getConfidence() / 33) {
                        case 0: w.append(t, String.format("%s is nervous about lowering %sself onto %s's cock, but %s gasps with pleasure as it goes inside %s pussy and immediately hits a sensitive spot.  %s moans and begins moving %2$sself up and down with genuine enthusiasm.", sender.OwnerName(), sender.himHer(), receiver.ownerName(), sender.heShe(), sender.hisHer(), sender.HeShe())); break;
                        case 1: w.append(t, String.format("%s straddles %s, touching %s pussy against the tip of %2$s's cock, then slowly lowering %sself downward onto it, bit by bit.  %s gasps and twitches as it bottoms out inside %s.", sender.OwnerName(), receiver.ownerName(), sender.hisHer(), sender.himHer(), sender.HeShe(), sender.himHer())); break;
                        default: w.append(t, String.format("%s pins %s down and takes %2$s's cock into %s pussy.  %s grimaces slightly at the sudden insertion, but wastes no time in bucking %s hips with savage force, causing the discomfort to melt away into pleasure.", sender.OwnerName(), receiver.ownerName(), sender.hisHer(), sender.HeShe(), sender.hisHer()));
                    }
                } else
                switch (sender.getDignity() / 33) {
                    case 0: w.append(t, String.format("%s doesn't waste any time before slamming %s hips down onto %s's cock.  However, %s groans with discomfort at the sudden insertion, and %4$s needs a few moments to recover before %4$s can start moving.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.heShe())); break;
                    case 1: w.append(t, String.format("%s delays as long as possible before taking %s's cock into %s pussy.  %s tries various angles, slides %sself against it several times, and only then does %s finally lower %sself onto it.  When %s does, %8$s lets out a little moan, uncomfortable with just how intense the pleasure is.", sender.OwnerName(), receiver.ownerName(), sender.hisHer(), sender.HeShe(), sender.himHer(), sender.heShe(), sender.himHer(), sender.heShe())); break;
                    default: w.append(t, String.format("%s is blushing bright red at having to be the one to lower %sself onto %s's cock, but %s pretends that it doesn't bother %2$s.  %s just flinches as it spreads %s pussy open, then stifles a moan when it goes all the way inside.", sender.OwnerName(), sender.himHer(), receiver.ownerName(), sender.heShe(), sender.HeShe(), sender.hisHer()));
                }
                if(sender.isVVirg())
                    switch (sender.getObedience() / 33) {
                        case 0: w.append(t, String.format("  %s lifts %sself up enough to look down and see the blood on %s's cock, then grits %s teeth.", sender.HeShe(), sender.himHer(), receiver.ownerName(), sender.hisHer())); break;
                        case 1: w.append(t, String.format("  %s looks down at the trickle of blood on %s thigh with an expression of resignation.", sender.HeShe(), sender.hisHer())); break;
                        default: w.append(t, String.format("  %s embraces the pain of %s first vaginal penetration, ecstatic to have received it from %s.", sender.HeShe(), sender.hisHer(), receiver.ownerName()));
                    }
            } else
            {
                if(sender == w.lordBody)
                    w.append(t, String.format("%s's pussy tightens around %s's cock as though trying to milk it dry.", sender.OwnerName(), receiver.ownerName()));
                else
                if(sender.getObedience() > 66)
                {
                    switch (sender.getDeviancy() / 33) {
                        case 0: w.append(t, String.format("%s bites %s lip in concentration, trying to buck %2$s hips in turn and squeeze down with %2$s pussy in order to make it feel as good as possible for %s.", sender.OwnerName(), sender.hisHer(), receiver.ownerName())); break;
                        case 1: w.append(t, String.format("%s tries to help %s feel good as well, moving %s hips and urging %s deeper inside, but soon the pleasure overwhelms %s and %s can't think of anything but wanting to cum.", sender.OwnerName(), receiver.ownerName(), sender.hisHer(), receiver.himHer(), sender.himHer(), sender.heShe())); break;
                        default: w.append(t, String.format("%s goes wild with joyful lust, bucking %s hips wildly and enjoying every moment of being taken by %s.", sender.OwnerName(), sender.hisHer(), receiver.ownerName()));
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    switch (sender.getConfidence() / 33) {
                        case 0: w.append(t, String.format("%s is too nervous to move much, worried about doing something to displease %s.  Whimpering moans of pleasure begin to leak out of %s throat.", sender.OwnerName(), receiver.ownerName(), sender.hisHer())); break;
                        case 1: w.append(t, String.format("At first, %s tolerates it with an expression of resignation.  However, as the pleasure builds, %s starts to gasp with passion and then actively move %s hips.", sender.ownerName(), sender.heShe(), sender.hisHer())); break;
                        default: w.append(t, String.format("%s doesn't look entirely happy to be on the bottom, but the pleasure of each thrust hitting %s deepest places soon makes %s forget all about that and start crying out in pleasure.", sender.OwnerName(), sender.hisHer(), sender.himHer()));
                    }
                } else
                switch (sender.getInnocence() / 33) {
                    case 0: w.append(t, String.format("%s glares up at %s, and %s comes through clearly even as %s winces and groans at the stimulation to %s most sensitive inner places.", sender.OwnerName(), receiver.ownerName(), sender.hisHer(), sender.heShe(), sender.hisHer())); break;
                    case 1: w.append(t, String.format("%s attempts to deny %s the satisfaction of seeing %s reactions, but the longer the fucking continues, the more difficult it is.  Soon %s's reduced to covering %s face and biting %5$s lip to stifle %5$s moans.", sender.OwnerName(), receiver.ownerName(), sender.hisHer(), sender.heShe(), sender.hisHer())); break;
                    default: w.append(t, String.format("%s tries to kick and scream, but %s movements only drive %s deeper inside %s, and %2$s voice takes on a passionate quality.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.himHer()));
                }
                if(sender.isVVirg())
                    switch (sender.getObedience() / 33) {
                        case 0: w.append(t, String.format("  %s anger drowns out the pain of having %s hymen torn.", sender.HisHer(), sender.hisHer())); break;
                        case 1: w.append(t, String.format("  When %s sees the blood on %s's cock, %1$s flinches away, as if trying to ignore it.", sender.heShe(), receiver.ownerName())); break;
                        default: w.append(t, String.format("  %s smiles broadly up at %s, tears of joy leaking from %s eyes at being able to give %3$s first time to the one %s loves.", sender.HeShe(), receiver.ownerName(), sender.hisHer(), sender.heShe()));
                    }
            }
        } else
        if(this == Project.AnalPenetrate)
        {
            if(Project.PushDown.isInProgress(sender, receiver))
            {
                if(sender == w.lordBody)
                    w.append(t, String.format("%s puts the tip of %s cock against %s's anus, then thrusts inside.", sender.OwnerName(), sender.hisHer(), receiver.ownerName()));
                else
                if(sender.getDeviancy() > 66)
                {
                    switch (sender.getConfidence() / 33) {
                        case 0: w.append(t, String.format("%s's timid nature seems to vanish entirely, and %s starts to ram %s hips forward with wild abandon, hammering in and out of %s's asshole.", sender.OwnerName(), sender.heShe(), sender.hisHer(), receiver.ownerName())); break;
                        case 1: w.append(t, String.format("%s starts to slowly push %s cock up %s's ass, but the surge of pleasure from the sphincter squeezing %2$s tip overwhelms %2$s reason.  %s jerks %2$s hips forward, burying %sself all the way inside, then pulls halfway out, then pushes back in, hips jerking wildly as intense ecstasy surges through %5$s with every movement.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.HeShe(), sender.himHer())); break;
                        default: w.append(t, String.format("%s eagerly pins %s down and shoves %s shaft all the way up %2$s's ass, immediately setting a rapid pace of thrusting in and out.  It's a struggle to keep moving steadily with the flashes of pleasure shooting through %s, but %s is determined to keep going.", sender.OwnerName(), receiver.ownerName(), sender.hisHer(), sender.himHer(), sender.ownerName()));
                    }
                } else
                if(sender.getDeviancy() > 33)
                {
                    switch (sender.getInnocence() / 33) {
                        case 0: w.append(t, String.format("%s gradually pushes %s cock up %s's ass, inch-by-inch, then withdraws it just as carefully.  %s moves slowly, feeling out which spots feel better for %s and which seem to get more of a reaction from %s, and only then starts to move more quickly, getting into a rhythm.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.HeShe(), sender.himHer(), receiver.ownerName())); break;
                        case 1: w.append(t, String.format("%s starts to fuck %s's asshole, but the pleasure of %s sphincter squeezing down on %s cock gives %s pause.  %s gasps, taking a moment to catch %s breath, then continues more slowly, enjoying the incredible tightness around %7$s shaft.", sender.OwnerName(), receiver.ownerName(), receiver.hisHer(), sender.himHer(), sender.ownerName(), sender.HeShe(), sender.hisHer())); break;
                        default: w.append(t, String.format("%s pushes %s cock up %s's ass, and the moment %s feels %s rear entrance squeezing down on %s, %s loses the ability to think of anything but the pleasure.  Moaning softly, %7$s starts thrusting in and out, acting on pure instinct.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.heShe(), receiver.hisHer(), sender.himHer(), sender.heShe()));
                    }
                } else
                switch (sender.getObedience() / 33) {
                    case 0: w.append(t, String.format("%s's angry demeanor fades for just a moment as %s thrusts into %s and feels the intense pleasure of %s asshole squeezing %s.  But then %2$s recovers and starts to move violently in and out, as if stabbing %s with a weapon.", sender.OwnerName(), sender.heShe(), receiver.ownerName(), receiver.hisHer(), sender.himHer(), receiver.ownerName())); break;
                    case 1: w.append(t, String.format("%s presses the tip of %s cock against %s's anus, hesitates for a moment, and then finally pushes it inside.  %s gasps and shudders at the pleasure, far beyond what %s was prepared for, and then cautiously starts to move %2$s hips forward and back.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.HeShe(), sender.heShe())); break;
                    default: w.append(t, String.format("%s eagerly thrusts %s cock up %s's ass, ecstatic to be joined with %s.  %s moves unselfishly, trying to hit all of %s's sensitive parts without any regard for %2$s own pleasure.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), receiver.himHer(), sender.HeShe(), receiver.ownerName()));
                }
            } else
            if(sender == w.lordBody)
                w.append(t, String.format("%s's cock pushes all the way into %s's bowels.", sender.OwnerName(), receiver.ownerName()));
            else
            if(sender.getDeviancy() > 66)
            {
                switch (sender.getInnocence() / 33) {
                    case 0: w.append(t, String.format("At first, %s tries to reciprocate with %s own thrusts.  But it soon becomes clear that %s's completely at %s's mercy, and all %s can do is moan and jerk %2$s hips as %s slides up and down %2$s shaft.", sender.ownerName(), sender.hisHer(), sender.heShe(), receiver.ownerName(), sender.heShe(), receiver.ownerName())); break;
                    case 1: w.append(t, String.format("The feeling of %s sliding down to the base of %s shaft is enough to make %s cry out and lose %2$s sense of reason.  %s desperately clings to %1$s, hips jerking wildly up and down as %s tries to thrust even deeper into %s.", receiver.ownerName(), sender.hisHer(), sender.ownerName(), sender.HeShe(), sender.heShe(), receiver.himHer())); break;
                    default: w.append(t, String.format("%s's whole body goes slack except for %s hips desperately trying to thrust deeper into %s.  %s eyes roll and %2$s tongue hangs out.", sender.OwnerName(), sender.hisHer(), receiver.himHer(), sender.HisHer()));
                }
            } else
            if(sender.getDeviancy() > 33)
            {
                switch (sender.getDignity() / 33) {
                    case 0: w.append(t, String.format("%s gasps with pleasure, reflexively embracing %s and thrusting %s own hips in time with %2$s's movements.", sender.OwnerName(), receiver.ownerName(), sender.hisHer())); break;
                    case 1: w.append(t, String.format("%s lays back and loses %sself in the feeling of %s sliding up and down %s length, overwhelmed by the intensity of the sensations.", sender.OwnerName(), sender.himHer(), receiver.ownerName(), sender.hisHer())); break;
                    default: w.append(t, String.format("%s tries to hide just how good it feels, but when %s clenches around %s, %s gasps and reflexively bucks %s hips.  After that, %s has a harder and harder time suppressing %s moans.", sender.OwnerName(), receiver.ownerName(), sender.himHer(), sender.heShe(), sender.hisHer(), sender.heShe(), sender.hisHer()));
                }
            } else
            switch (sender.getObedience() / 33) {
                case 0: w.append(t, String.format("%s struggles to keep glaring at %s, wincing slightly every time %2$s's anus squeezes the base of %s shaft and breaks %3$s concentration.", sender.OwnerName(), receiver.ownerName(), sender.hisHer())); break;
                case 1: w.append(t, String.format("%s tries to relax and enjoy it, but even as %s slides up and down %s shaft, %s can't quite shake off the fear that this pleasure is just meant to make %s let %s guard down.", sender.OwnerName(), receiver.ownerName(), sender.hisHer(), sender.ownerName(), sender.himHer(), sender.hisHer())); break;
                default: w.append(t, String.format("%s winces and struggles not to cum right away, wanting to ensure that %s stays hard for %s as long as possible.", sender.OwnerName(), sender.heShe(), receiver.ownerName()));
            }
        } else
        if(this == Project.PenetratedAnally)
        {
            if(Project.PushDown.isInProgress(sender, receiver))
            {
                if(sender == w.lordBody)
                {
                    w.append(t, String.format("%s mounts %s and lowers %sself until %s anus envelops %2$s's cock.", sender.OwnerName(), receiver.ownerName(), sender.himHer(), sender.hisHer()));
                } else
                {
                    if(!Project.BeLubricated.isInProgress(sender, null))
                    {
                        switch (sender.getInnocence() / 33) {
                            case 0: w.append(t, String.format("Before continuing, %s carefully applies some slippery lubricant to %s rear entrance.", sender.ownerName(), sender.hisHer())); break;
                            case 1: w.append(t, String.format("As %s lays atop %s, %s reaches back with one finger covered in lubricant to prepare for what comes next.", sender.heShe(), receiver.ownerName(), sender.ownerName())); break;
                            default: w.append(t, String.format("Full of eager lust, %s almost forgets to lubricate %sself back there before continuing.", sender.ownerName(), sender.himHer()));
                        }
                        w.append(t, "\n\n");
                        sender.addActivity(Project.BeLubricated, null);
                    }
                    if(sender.getDeviancy() > 66)
                    {
                        switch (sender.getObedience() / 33) {
                            case 0: w.append(t, String.format("%s tries to resist %s urges, but this close to %s's cock, %s can't stop %sself from grinding against it.  Then, before %s realizes it, %6$s's taken it up %2$s ass.  The surge of pleasure destroys the last of %2$s reason, and %6$s starts moving in earnest.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.heShe(), sender.himHer(), sender.heShe())); break;
                            case 1: w.append(t, String.format("%s quickly lowers %sself onto %s's cock, then immediately begins to bounce on %s lap, searching for the precise angle where it pokes just the right spot inside %s bowels.", sender.OwnerName(), sender.himHer(), receiver.ownerName(), receiver.hisHer(), sender.hisHer())); break;
                            default: w.append(t, String.format("%s eagerly impales %s asshole on %s's upright cock, sliding %sself up and down with manic energy while grinning at %s.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.himHer(), receiver.himHer()));
                        }
                    } else
                    if(sender.getDeviancy() > 33)
                    {
                        switch (sender.getConfidence() / 33) {
                            case 0: w.append(t, String.format("%s is nervous about lowering %sself onto %s's cock, but %s gasps with pleasure as it goes up %s ass and immediately hits a sensitive spot.  %s moans and begins moving %2$sself up and down with genuine enthusiasm.", sender.OwnerName(), sender.himHer(), receiver.ownerName(), sender.heShe(), sender.hisHer(), sender.HeShe())); break;
                            case 1: w.append(t, String.format("%s straddles %s, touching %s anus against the tip of %2$s's cock, then slowly lowering %sself downward onto it, bit by bit.  %s gasps and twitches as it bottoms out inside %s.", sender.OwnerName(), receiver.ownerName(), sender.hisHer(), sender.himHer(), sender.HeShe(), sender.himHer())); break;
                            default: w.append(t, String.format("%s pins %s down and takes %2$s's cock up %s ass.  %s grimaces slightly at the sensation of being spread wide open, but wastes no time in bucking %s hips with savage force, causing the discomfort to melt away into pleasure.", sender.OwnerName(), receiver.ownerName(), sender.hisHer(), sender.HeShe(), sender.hisHer()));
                        }
                    } else
                    switch (sender.getDignity() / 33) {
                        case 0: w.append(t, String.format("%s doesn't waste any time before slamming %s ass down onto %s's cock.  However, %s groans with discomfort at the sudden insertion, and %4$s needs a few moments to recover before %4$s can start moving.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.heShe())); break;
                        case 1: w.append(t, String.format("%s delays as long as possible before taking %s's cock up %s ass.  %s tries various angles, grinds the tip against %s anus, and only then does %s finally lower %sself onto it.  When %s does, %8$s lets out a little moan, uncomfortable with just how intense the pleasure is.", sender.OwnerName(), receiver.ownerName(), sender.hisHer(), sender.HeShe(), sender.hisHer(), sender.heShe(), sender.himHer(), sender.heShe())); break;
                        default: w.append(t, String.format("%s is blushing bright red at having to be the one to lower %sself onto %s's cock, but %s pretends that it doesn't bother %2$s.  %s just flinches as it spreads %s anus open, then stifles a moan when it goes all the way inside.", sender.OwnerName(), sender.himHer(), receiver.ownerName(), sender.heShe(), sender.HeShe(), sender.hisHer()));
                    }
                }
                if(sender.isVVirg())
                    switch (sender.getObedience() / 33) {
                        case 0: w.append(t, String.format("  %s lifts %sself up enough to look down and see the blood on %s's cock, then grits %s teeth.", sender.HeShe(), sender.himHer(), receiver.ownerName(), sender.hisHer())); break;
                        case 1: w.append(t, String.format("  %s looks down at the trickle of blood on %s thigh with an expression of resignation.", sender.HeShe(), sender.hisHer())); break;
                        default: w.append(t, String.format("  %s embraces the pain of %s first vaginal penetration, ecstatic to have received it from %s.", sender.HeShe(), sender.hisHer(), receiver.ownerName()));
                    }
            } else
            if(Project.BeLubricated.isInProgress(sender, null) || sender == w.lordBody)
            {
                if(sender == w.lordBody)
                    w.append(t, String.format("%s's asshole tightens around %s's cock as though trying to milk it dry.", sender.OwnerName(), receiver.ownerName()));
                else
                if(sender.getObedience() > 66)
                {
                    switch (sender.getDeviancy() / 33) {
                        case 0: w.append(t, String.format("%s bites %s lip in concentration, trying to buck %2$s hips in turn and squeeze down with %2$s anus in order to make it feel as good as possible for %s.", sender.OwnerName(), sender.hisHer(), receiver.ownerName())); break;
                        case 1: w.append(t, String.format("%s tries to help %s feel good as well, moving %s hips and urging %s deeper inside, but soon the pleasure overwhelms %s and %s can't think of anything but wanting to cum.", sender.OwnerName(), receiver.ownerName(), sender.hisHer(), receiver.himHer(), sender.himHer(), sender.heShe())); break;
                        default: w.append(t, String.format("%s goes wild with joyful lust, bucking %s hips wildly and enjoying every moment of being taken by %s.", sender.OwnerName(), sender.hisHer(), receiver.ownerName()));
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    switch (sender.getConfidence() / 33) {
                        case 0: w.append(t, String.format("%s is too nervous to move much, worried about doing something to displease %s.  Whimpering moans of pleasure begin to leak out of %s throat.", sender.OwnerName(), receiver.ownerName(), sender.hisHer())); break;
                        case 1: w.append(t, String.format("At first, %s tolerates it with an expression of resignation.  However, as %s sensitive places start to get stimulated through %2$s anal walls, %s starts to gasp with passion and then actively move %2$s hips.", sender.ownerName(), sender.hisHer(), sender.heShe())); break;
                        default: w.append(t, String.format("%s doesn't look entirely happy to be taking it up the ass, but the pleasure of each thrust hitting %s deepest places soon makes %s forget all about that and start crying out in pleasure.", sender.OwnerName(), sender.hisHer(), sender.himHer()));
                    }
                } else
                switch (sender.getInnocence() / 33) {
                    case 0: w.append(t, String.format("%s glares up at %s, and %s comes through clearly even as %s winces and groans at the stimulation through %s anal walls.", sender.OwnerName(), receiver.ownerName(), sender.hisHer(), sender.heShe(), sender.hisHer())); break;
                    case 1: w.append(t, String.format("%s attempts to deny %s the satisfaction of seeing %s reactions, but the longer the fucking continues, the more difficult it is.  Soon %s's reduced to covering %s face and biting %5$s lip to stifle %5$s moans.", sender.OwnerName(), receiver.ownerName(), sender.hisHer(), sender.heShe(), sender.hisHer())); break;
                    default: w.append(t, String.format("%s tries to kick and scream, but %s movements only drive %s deeper inside %s, and %2$s voice takes on a passionate quality.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.himHer()));
                }
                if(sender.getGender() == Forsaken.Gender.MALE && sender.isVVirg())
                    switch (sender.getObedience() / 33) {
                        case 0: w.append(t, String.format("  %s anger drowns out the pain of having %s inexperienced asshole stretched so wide for the first time.", sender.HisHer(), sender.hisHer())); break;
                        case 1: w.append(t, String.format("  To %s horror, the pleasure keeps building further and further, until %s can't deny that %2$s enjoys being fucked like a girl.", sender.hisHer(), sender.heShe())); break;
                        default: w.append(t, String.format("  %s smiles broadly up at %s, tears of joy leaking from %s eyes at receiving %3$s first anal penetration from the one %s loves.", sender.HeShe(), receiver.ownerName(), sender.hisHer(), sender.heShe()));
                    }
            } else
            {
                if(sender.getConfidence() > 66)
                {
                    switch (sender.getObedience() / 33) {
                        case 0: w.append(t, String.format("%s's determined resolve lasts only a moment before cracking, and %s starts begging for mercy in a shrill voice, apologizing for %s defiance.", sender.OwnerName(), sender.heShe(), sender.hisHer())); break;
                        case 1: w.append(t, String.format("The agony of being anally penetrated without any lubrication is almost enough to paralyze %s's thoughts, but %s has just enough willpower to beg for mercy, offering to do anything in hopes of ending the torture.", sender.ownerName(), sender.heShe())); break;
                        default: w.append(t, String.format("%s grits %s teeth into a forced smile, encouraging %s to keep fucking %s even though %s's in agony from having %2$s ass forced open without any lubrication.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.himHer(), sender.heShe()));
                    }
                } else
                if(sender.getConfidence() > 33)
                {
                    switch (sender.getDignity() / 33) {
                        case 0: w.append(t, String.format("%s kicks wildly and screams at the top of %s lungs, heedless of the fact that %s's only scraping %sself even more against the cruel invading shaft.", sender.OwnerName(), sender.hisHer(), sender.heShe(), sender.himHer())); break;
                        case 1: w.append(t, String.format("Without any lubrication, the insertion is more painful than anything, and %s is ashamed with %sself as %s starts uncontrollably sobbing.", sender.ownerName(), sender.himHer(), sender.heShe())); break;
                        default: w.append(t, String.format("At first, %s tries to pretend that %s can take it.  But the painful friction against %s unlubricated anal walls is too much for %s, and soon %2$s's crying and begging without restraint.", sender.ownerName(), sender.heShe(), sender.hisHer(), sender.himHer()));
                    }
                } else
                switch (sender.getDeviancy() / 33) {
                    case 0: w.append(t, String.format("%s screams, crying and sobbing at the explosion of agony %s feels from the unlubricated insertion.  The pain far outweighs the pleasure.", sender.OwnerName(), sender.heShe())); break;
                    case 1: w.append(t, String.format("%s whimpers, trying to curl up and protect %sself, but %s can't hide from the painful friction of the shaft invading %s unprepared hole.  %s moans aren't entirely from pain, as %s feels a hint of shameful pleasure as well.", sender.OwnerName(), sender.himHer(), sender.heShe(), sender.hisHer(), sender.HisHer(), sender.heShe())); break;
                    default: w.append(t, String.format("%s's eyes shoot wide open, a scream caught in %s throat.  But when it finally comes out, there's a lewd quality to it, a sign of %2$s insatiable masochism.", sender.OwnerName(), sender.hisHer()));
                }
                if(sender.getGender() == Forsaken.Gender.MALE && sender.isVVirg())
                    switch (sender.getObedience() / 33) {
                        case 0: w.append(t, String.format("  A part of %s is horrified that %s was broken so easily, and %s confidence in %3$s own masculinity will never recover.", sender.himHer(), sender.heShe(), sender.hisHer())); break;
                        case 1: w.append(t, String.format("  %s realizes that it was a mistake to ever take pride in managing to protect %s anal virginity.", sender.HeShe(), sender.hisHer())); break;
                        default: w.append(t, String.format("  Afterward, %s'll be full of joyful pride that %s saw fit to break %s in with such a memorable fucking, but for now, %1$s's in too much pain to think about it in those terms.", sender.heShe(), receiver.ownerName(), sender.himHer()));
                    }
            }
        } else
        if(this == Project.StripOther)
        {
            if(sender == w.lordBody)
            {
                w.append(t, String.format("%s begins to strip off %s's clothes.", sender.OwnerName(), receiver.ownerName()));
                if(w.sceneLocation == Location.STAGE)
                    w.append(t, "  The crowd goes wild, eager to see more skin.");
            }
        } else
        if(this == Project.Stripped)
            if(w.sceneLocation == Location.STAGE)
            {
                if(sender.getObedience() > 66)
                {
                    switch (sender.getDisgrace() / 33) {
                        case 0: w.append(t, String.format("%s looks afraid, but also eager, wanting to show everyone that %s really is nothing more than %s's toy.", sender.OwnerName(), sender.heShe(), receiver.ownerName())); break;
                        case 1: w.append(t, String.format("%s smiles, eager to help %s put on a show for everyone.", sender.OwnerName(), receiver.ownerName())); break;
                        default: w.append(t, String.format("%s doesn't even seem to notice all the spectators.  %s only has eyes for %s.", sender.OwnerName(), sender.HeShe(), receiver.ownerName()));
                    }
                } else
                if(sender.getObedience() > 33)
                {
                    if(sender.forsakenOwner != null && sender.forsakenOwner.timesExposed < 300_000)
                    {
                        switch (sender.getDignity() / 33) {
                            case 0: w.append(t, String.format("%s just ignores all the shouts and jeers.", sender.OwnerName())); break;
                            case 1: w.append(t, String.format("%s looks resigned to the fact that %s'll shortly be showing off more than %2$s ever has before.", sender.OwnerName(), sender.heShe())); break;
                            default: w.append(t, String.format("%s is clearly terrified of the prospect of being forced to show everything, but %s's come too far to back out now.", sender.OwnerName(), sender.heShe()));
                        }
                    } else
                    switch (sender.getDignity() / 33) {
                        case 0: w.append(t, String.format("%s just ignores all the shouts and jeers.", sender.OwnerName())); break;
                        case 1: w.append(t, String.format("%s tries to make the best of it, smiling at the crowd.", sender.OwnerName())); break;
                        default: w.append(t, String.format("%s tries to make it look like it was %s idea to undress, moving to help %s strip %s.", sender.OwnerName(), sender.hisHer(), receiver.ownerName(), sender.himHer()));
                    }
                } else
                switch (sender.getDeviancy() / 33) {
                    case 0: w.append(t, String.format("%s clutches at %s clothes and glares at %s.", sender.OwnerName(), sender.hisHer(), receiver.ownerName())); break;
                    case 1: w.append(t, String.format("%s huffs angrily, but the heat rising to %s cheeks is partly from arousal.", sender.OwnerName(), sender.hisHer())); break;
                    default: w.append(t, String.format("%s's eyes dart back and forth among all the people watching, and despite %s anger, %s's panting wth barely-restrained desire.", sender.OwnerName(), sender.hisHer(), sender.heShe()));
                }
            } else
            if(sender.getDeviancy() > 66)
            {
                switch (sender.getInnocence() / 33) {
                    case 0: w.append(t, String.format("%s starts to breathe more quickly, imagining what might come next.", sender.OwnerName())); break;
                    case 1: w.append(t, String.format("As %s clothes are taken off, %s gets more and more turned on, and soon %s can't think of anything but sex.", sender.hisHer(), sender.ownerName(), sender.heShe())); break;
                    default: w.append(t, String.format("The act of being stripped immediately causes %s's mind to wander off into erotic fantasy, and %s starts to drool with a silly expression on %s face.", sender.ownerName(), sender.heShe(), sender.hisHer()));
                }
            } else
            if(sender.getDeviancy() > 33)
            {
                switch (sender.getConfidence() / 33) {
                    case 0: w.append(t, String.format("%s glances off to the side and reflexively tries to cover %sself.", sender.OwnerName(), sender.himHer())); break;
                    case 1: w.append(t, String.format("%s blushes, anticipating what's to come.", sender.OwnerName())); break;
                    default: w.append(t, String.format("%s proudly shows off, refusing to be ashamed of %s body.", sender.OwnerName(), sender.hisHer()));
                }
            } else
            switch (sender.getObedience() / 33) {
                case 0: w.append(t, String.format("%s clutches at %s clothes and glares at %s.", sender.OwnerName(), sender.hisHer(), receiver.ownerName())); break;
                case 1: w.append(t, String.format("%s sighs, accepting that this was inevitable.", sender.OwnerName())); break;
                default: w.append(t, String.format("%s smiles, happy that %s wants to see %s body.", sender.OwnerName(), receiver.ownerName(), sender.hisHer()));
            }
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

            switch (sender.getINJULevel()) {
                case 2: result += 20; break;
                case 3: result += 40; break;
                case 4: result += 100;
            }
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
