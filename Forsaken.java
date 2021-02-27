
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.io.Serializable;
import javax.swing.*;
import javax.swing.text.*;

public class Forsaken
    implements Serializable
{
    public static final class Gender extends Enum
    {

        public static Gender[] values()
        {
            Gender agender[];
            int i;
            Gender agender1[];
            System.arraycopy(agender = ENUM$VALUES, 0, agender1 = new Gender[i = agender.length], 0, i);
            return agender1;
        }

        public static Gender valueOf(String s)
        {
            return (Gender)Enum.valueOf(Forsaken$Gender, s);
        }

        public static final Gender MALE;
        public static final Gender FEMALE;
        public static final Gender FUTANARI;
        private static final Gender ENUM$VALUES[];

        static 
        {
            MALE = new Gender("MALE", 0);
            FEMALE = new Gender("FEMALE", 1);
            FUTANARI = new Gender("FUTANARI", 2);
            ENUM$VALUES = (new Gender[] {
                MALE, FEMALE, FUTANARI
            });
        }

        private Gender(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class Relationship extends Enum
    {

        public static Relationship[] values()
        {
            Relationship arelationship[];
            int i;
            Relationship arelationship1[];
            System.arraycopy(arelationship = ENUM$VALUES, 0, arelationship1 = new Relationship[i = arelationship.length], 0, i);
            return arelationship1;
        }

        public static Relationship valueOf(String s)
        {
            return (Relationship)Enum.valueOf(Forsaken$Relationship, s);
        }

        public static final Relationship PARTNER;
        private static final Relationship ENUM$VALUES[];

        static 
        {
            PARTNER = new Relationship("PARTNER", 0);
            ENUM$VALUES = (new Relationship[] {
                PARTNER
            });
        }

        private Relationship(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class Taker extends Enum
    {

        public static Taker[] values()
        {
            Taker ataker[];
            int i;
            Taker ataker1[];
            System.arraycopy(ataker = ENUM$VALUES, 0, ataker1 = new Taker[i = ataker.length], 0, i);
            return ataker1;
        }

        public static Taker valueOf(String s)
        {
            return (Taker)Enum.valueOf(Forsaken$Taker, s);
        }

        public static final Taker NONE;
        public static final Taker THRALLS;
        public static final Taker COMMANDER;
        public static final Taker SELF;
        public static final Taker TOGETHER;
        public static final Taker CHOSEN;
        public static final Taker FORSAKEN;
        private static final Taker ENUM$VALUES[];

        static 
        {
            NONE = new Taker("NONE", 0);
            THRALLS = new Taker("THRALLS", 1);
            COMMANDER = new Taker("COMMANDER", 2);
            SELF = new Taker("SELF", 3);
            TOGETHER = new Taker("TOGETHER", 4);
            CHOSEN = new Taker("CHOSEN", 5);
            FORSAKEN = new Taker("FORSAKEN", 6);
            ENUM$VALUES = (new Taker[] {
                NONE, THRALLS, COMMANDER, SELF, TOGETHER, CHOSEN, FORSAKEN
            });
        }

        private Taker(String s, int i)
        {
            super(s, i);
        }
    }


    public void initialize(WorldState w, Chosen c)
    {
        hostility = (100 - c.morality) / 3;
        if(c.isImpregnated().booleanValue())
            hostility += 40;
        else
        if(c.timesSlaughtered() > 0)
            hostility += 30;
        else
        if(!c.isVVirg())
            hostility += 20;
        else
        if(c.isRuthless())
            hostility += 10;
        deviancy = (100 - c.innocence) / 3;
        if(c.isHypnotized().booleanValue())
            deviancy += 40;
        else
        if(c.timesFantasized() > 0)
            deviancy += 30;
        else
        if(!c.isCVirg())
            deviancy += 20;
        else
        if(c.isLustful())
            deviancy += 10;
        obedience = (100 - c.confidence) / 3;
        if(c.isDrained().booleanValue())
            obedience += 40;
        else
        if(c.timesDetonated() > 0)
            obedience += 30;
        else
        if(!c.isAVirg())
            obedience += 20;
        else
        if(c.isMeek())
            obedience += 10;
        disgrace = (100 - c.dignity) / 3;
        if(c.isParasitized().booleanValue())
            disgrace += 40;
        else
        if(c.timesStripped() > 0)
            disgrace += 30;
        else
        if(!c.isModest())
            disgrace += 20;
        else
        if(c.isDebased())
            disgrace += 10;
        if(c.isImpregnated().booleanValue())
            demonicBirths = 1;
        kills[0] = c.firstKilled;
        kills[1] = c.secondKilled;
        if(kills[1] != null)
            timesKilled = 2;
        else
        if(kills[0] != null)
            timesKilled = 1;
        if(c.timesSlaughtered() > 0)
            timesKilled = c.timesSlaughtered() * 10 + (int)(Math.random() * 10D);
        if(!c.isVVirg())
        {
            timesHadSex = 100 + (int)(Math.random() * 500D);
            if(!c.vStart.booleanValue())
            {
                int added = 300 + (int)(Math.random() * 300D);
                int addedExtra = (int)(Math.random() * 50D);
                timesHadSex += added;
                timesExposedSelf += added + addedExtra;
                timesExposed += added + addedExtra;
                orgasmsGiven += added + (int)(Math.random() * 50D);
            }
        }
        if(c.isRuthless())
        {
            peopleInjured = 100 + (int)(Math.random() * 100D);
            ruthless = Boolean.valueOf(true);
        }
        if(c.isHypnotized().booleanValue())
            hypnotized = Boolean.valueOf(true);
        if(c.timesFantasized() > 0 && (!c.isCVirg() || c.innocence < 67))
        {
            timesOrgasmed = 1000 + c.timesFantasized() * 25 + (int)(Math.random() * 25D);
            strongestOrgasm = 1000 + (int)(Math.random() * 1000D);
        } else
        if(!c.isCVirg())
        {
            timesOrgasmed = 100 + (int)(Math.random() * 800D);
            strongestOrgasm = 200 + (int)(Math.random() * 200D);
        } else
        if(c.innocence < 67)
        {
            timesOrgasmed = (int)(Math.random() * 99D);
            strongestOrgasm = 100 + (int)(Math.random() * 100D);
        }
        if(c.isLustful())
        {
            orgasmsGiven += 1000 + (int)(Math.random() * 1000D);
            lustful = Boolean.valueOf(true);
        }
        if(c.isMeek())
            meek = Boolean.valueOf(true);
        if(!c.isAVirg())
            timesTortured = 30 + (int)(Math.random() * 30D);
        if(c.timesDetonated() > 0)
            timesHarmedSelf = c.timesDetonated() + (int)(Math.random() * (double)(c.timesDetonated() * 2));
        if(c.isDrained().booleanValue())
            drained = Boolean.valueOf(true);
        if(c.isDebased())
            debased = Boolean.valueOf(true);
        if(c.timesStripped() > 0)
        {
            int added = c.timesStripped() * 20 + (int)(Math.random() * (double)(c.timesStripped() * 40));
            timesExposedSelf += added;
            timesExposed += added;
        }
        if(!c.isModest())
        {
            int added = 0x493e0 + (int)(Math.random() * 300000D);
            timesExposed += added;
            if(!c.mStart.booleanValue())
                timesExposedSelf += added;
        }
        if(c.isParasitized().booleanValue())
            parasitized = Boolean.valueOf(true);
        defeatType = c.defeatType;
        givenName = c.givenName;
        familyName = c.familyName;
        mainName = c.mainName;
        originalName = c.mainName;
        filthyGaijin = c.filthyGaijin;
        textColor = c.textColor;
        darkColor = c.darkColor;
        if(w.getGenders()[c.getNumber()].equals("male"))
            originalGender = Gender.MALE;
        else
        if(w.getGenders()[c.getNumber()].equals("female"))
            originalGender = Gender.FEMALE;
        else
            originalGender = Gender.FUTANARI;
        if(c.gender.equals("male"))
            originalGender = Gender.MALE;
        else
        if(c.gender.equals("female"))
            originalGender = Gender.FEMALE;
        else
            originalGender = Gender.FUTANARI;
        incantation = c.incantation;
        adjectiveName = c.adjectiveName;
        nounName = c.nounName;
        topCover = c.topCover;
        topAccess = c.topAccess;
        bottomCover = c.bottomCover;
        bottomAccess = c.bottomAccess;
        underType = c.underType;
        color = c.color;
        accessory = c.accessory;
        weapon = c.weapon;
        customWeaponType = c.customWeaponType;
        feetType = c.feetType;
        morality = c.morality;
        innocence = c.innocence;
        confidence = c.confidence;
        dignity = c.dignity;
        if(c.vTaker == 0)
            takers[0] = Taker.THRALLS;
        else
        if(c.vTaker == 1)
            takers[0] = Taker.COMMANDER;
        else
        if(c.vTaker == 2)
            takers[0] = Taker.SELF;
        else
        if(c.vTaker < 6)
            takers[0] = Taker.TOGETHER;
        else
            takers[0] = Taker.CHOSEN;
        if(c.cTaker == 0)
            takers[1] = Taker.THRALLS;
        else
        if(c.cTaker == 1)
            takers[1] = Taker.COMMANDER;
        else
        if(c.cTaker == 2)
            takers[1] = Taker.SELF;
        else
        if(c.cTaker < 6)
            takers[1] = Taker.TOGETHER;
        else
            takers[1] = Taker.CHOSEN;
        if(c.aTaker == 0)
            takers[2] = Taker.THRALLS;
        else
        if(c.aTaker == 1)
            takers[2] = Taker.COMMANDER;
        else
        if(c.aTaker == 2)
            takers[2] = Taker.SELF;
        else
        if(c.aTaker < 6)
            takers[2] = Taker.TOGETHER;
        else
            takers[2] = Taker.CHOSEN;
        if(c.mTaker == 0)
            takers[3] = Taker.THRALLS;
        else
        if(c.mTaker == 1)
            takers[3] = Taker.COMMANDER;
        else
        if(c.mTaker == 2)
            takers[3] = Taker.SELF;
        else
        if(c.mTaker < 6)
            takers[3] = Taker.TOGETHER;
        else
            takers[3] = Taker.CHOSEN;
        stamina = 100;
        motivation = 100;
    }

    public void selfTalk(JTextPane t)
    {
        say(t, "\"");
        if(obedience < 20)
        {
            if(confidence > 66)
            {
                if(!originalName.equals(mainName) && !givenName.equals(mainName) && !familyName.equals(mainName))
                    say(t, (new StringBuilder("My name is ")).append(originalName).append("!  And I won't respond to '").append(mainName).append("', no matter what you do to me.  ").toString());
                else
                    say(t, (new StringBuilder("You know who I am!  I'm ")).append(mainName).append(", but you're probably expecting me to say more than that.  ").toString());
            } else
            if(!originalName.equals(mainName) && !givenName.equals(mainName) && !familyName.equals(mainName))
                say(t, (new StringBuilder("I'm still ")).append(originalName).append(" - even if you prefer to call me ").append(mainName).append(".  ").toString());
            else
                say(t, (new StringBuilder("This is a waste of time, but fine.  I'm ")).append(mainName).append(".  ").toString());
            if(defeatType == 0)
            {
                if(innocence > 66)
                    say(t, "You tricked me into acting like I was surrendering, which made it so I can't transform on my own anymore, but I'm not gonna let that stop me!  ");
                else
                if(innocence > 33)
                    say(t, "I let my guard down during our fight, and now I've lost my powers, but this is just a temporary setback!  ");
                else
                    say(t, "After falling for your bluff that you were willing to kill me, I seem to have lost access to my Chosen powers, and so I must work with you for now if I wish to accomplish anything.  ");
            } else
            if(defeatType == 1)
            {
                if(morality > 66)
                    say(t, "Right now, my main goal is to stop the human side from doing evil things to fallen Chosen, and I can only do that by working together with the Demon side.  ");
                else
                if(morality > 33)
                    say(t, "I'm only working with you for now because I don't like the way that the humans treat fallen Chosen.  ");
                else
                    say(t, "The only reason I haven't killed you already is that you don't piss me off as much as the so-called human side.  ");
            } else
            if(defeatType == 2)
            {
                if(innocence > 66)
                    say(t, "I can't really remember why I'm working with you, but it feels right, so I'm gonna keep doing it, no matter who tries to stop me!  ");
                else
                if(innocence > 33)
                    say(t, "I'm doing what I think is best, and it just happens to fit with fighting on your side sometimes.  ");
                else
                    say(t, "Your hypnotic influence has led me to work for you, and although I suspect that I could break it with enough effort, I have decided not to resist for the time being.  ");
            } else
            if(defeatType != 3 && defeatType == 4)
                if(dignity > 66)
                    say(t, "My Chosen powers are gone, but my fans seem to prefer seeing me fighting on this side, so here I am.  ");
                else
                if(dignity > 33)
                    say(t, "I need to regain popularity in order to be able to transform on my own again, and you're helping me do that.  ");
                else
                    say(t, "Now that I can't transform on my own, I figure that if you're dumb enough to lend me your power to do so, I might as well take you up on it.  ");
            if(hostility > 66)
            {
                if(morality > 66)
                    say(t, "There are so many people who still need to be punished.");
                else
                if(morality > 33)
                    say(t, "I really enjoy fighting my useless former allies.");
                else
                    say(t, "The most important thing is that it gives me an excuse to make my enemies suffer.");
            } else
            if(hostility > 33)
            {
                if(morality > 66)
                    say(t, "I need to be more harsh, more willing to do cruel things, if I'm going to save humanity.");
                else
                if(morality > 33)
                    say(t, "The situation is frustrating, but at least I get to vent my anger on people.");
                else
                    say(t, "And I do enjoy hurting people, so this setup works nicely.");
            } else
            if(morality > 66)
                say(t, "I haven't given up on saving as many people as possible.");
            else
            if(morality > 33)
                say(t, "As far as I'm concerned, I'm still one of the Chosen.");
            else
                say(t, "I'm just looking out for myself.");
        } else
        if(obedience < 40)
        {
            String dots = "...";
            if(originalName.equals(mainName))
                dots = "";
            if(confidence > 66)
                say(t, (new StringBuilder("Hmph.  I'm")).append(dots).append(" ").append(mainName).append(".  ").toString());
            else
            if(confidence > 33)
                say(t, (new StringBuilder("I'm")).append(dots).append(" ").append(mainName).append(".  ").toString());
            else
                say(t, (new StringBuilder("I-I'm")).append(dots).append(" ").append(mainName).append("...  ").toString());
            if(defeatType == 0)
            {
                if(innocence > 66)
                    say(t, "I was scared that you were going to kill me, so, um... I guess I joined you.  ");
                else
                if(innocence > 33)
                    say(t, "I joined you so that you wouldn't kill me.  ");
                else
                    say(t, "After recognizing that you had the power to kill me if you wished, I opted to join you instead.  ");
            } else
            if(defeatType == 1)
            {
                if(morality > 66)
                    say(t, "I realized that the side I was fighting for was no better than yours, so now I'm here.  ");
                else
                if(morality > 33)
                    say(t, "With what the human side does to fallen Chosen, I couldn't work for them anymore.  And I had nowhere else to turn but to you.  ");
                else
                    say(t, "You may be a Demon, but I figured that even working for you would be better than waiting for what the other humans would eventually have in store for me.  ");
            } else
            if(defeatType == 2)
            {
                if(innocence > 66)
                    say(t, "I feel really, really nice whenever I work for you, so I figured, um... I'd just keep doing it.  ");
                else
                if(innocence > 33)
                    say(t, "I don't want to work for you, but whenever I try to leave, I wake up the next morning to find that I've come back.  ");
                else
                    say(t, "Your hypnotic influence has left me with little choice but to work for you.  ");
            } else
            if(defeatType != 3 && defeatType == 4)
                if(dignity > 66)
                    say(t, "I don't really want to work for you, but... my fans... expect it of me now.  ");
                else
                if(dignity > 33)
                    say(t, "I lost the ability to transform on my own, but I'm not ready to stop being one of the Chosen.  Even if I am called one of the 'Forsaken' now.  ");
                else
                    say(t, "I can't transform on my own anymore, so I don't have any choice other than to work for you.  ");
            if(hostility > 66)
            {
                if(morality > 66)
                    say(t, "Most of the people you have me hurt are people who deserve it, so I don't really mind.");
                else
                if(morality > 33)
                    say(t, "I've started to enjoy it... the screams of your enemies...  Did you know this would happen...?");
                else
                    say(t, "I wish you'd let me fight for you more often.  I... want to make more people suffer...!");
            } else
            if(hostility > 33)
            {
                if(morality > 66)
                    say(t, "Whenever your orders involve hurting people, I just have to tell myself that it's not actually my fault.");
                else
                if(morality > 33)
                    say(t, "I'm starting to get numb to people's screams...");
                else
                    say(t, "I enjoy making your enemies scream, so you don't have to force me...");
            } else
            if(morality > 66)
                say(t, "I'm trying to do as much good as I can while still following your orders.");
            else
            if(morality > 33)
                say(t, "I don't think I'm really in a position to help anyone else right now.");
            else
                say(t, "It's annoying, but... it's not like I really care whether you're having me hurt people or not.");
        } else
        if(obedience < 61)
        {
            if(confidence > 66)
                say(t, (new StringBuilder("I'm ")).append(mainName).append(".  ").toString());
            else
            if(confidence > 33)
                say(t, (new StringBuilder("I'm ")).append(mainName).append(", one of your servants.  ").toString());
            else
                say(t, (new StringBuilder("I'm ")).append(mainName).append(", y-your loyal servant.  ").toString());
            if(defeatType == 0)
            {
                if(innocence > 66)
                    say(t, "I'm not smart, so it took me awhile to realize I couldn't beat you... but now I know!  ");
                else
                if(innocence > 33)
                    say(t, "I'm following you because... I don't want to die.  And I know there's nothing I can do to stop you from killing me.  ");
                else
                    say(t, "I would prefer not to be following you, but... I understand now that I have no choice.  It's either service or death.  ");
            } else
            if(defeatType == 1)
            {
                if(morality > 66)
                    say(t, "I only came over to your side because of the awful way my side was treating the Chosen, but... I don't think I have any choice anymore.  ");
                else
                if(morality > 33)
                    say(t, "I don't think the human side is actually any better than the Demons' side... and I think it will turn out better for me if I'm not trying to fight you anymore...  ");
                else
                    say(t, "I realized that I was never going to be able to resist your power, and instead of facing what the humans have in store for fallen Chosen, I'd rather be on the winning side.  ");
            } else
            if(defeatType == 2)
            {
                if(innocence > 66)
                    say(t, "I joined you because... b-because...  Ergh...  Please, don't make me think...  It doesn't matter anyway.  ");
                else
                if(innocence > 33)
                    say(t, "You own my body and my mind both now.  Trying to resist is... too painful...  ");
                else
                    say(t, "I recognize that you have complete control over my mind.  There is no point in attempting to resist.  ");
            } else
            if(defeatType == 3)
            {
                if(confidence > 66)
                    say(t, "I'm not going to try to kill myself anymore, so you don't have to keep checking up on me!  I've... learned my lesson.  You're the one who decides how I die.  Not me.  ");
                else
                if(confidence > 33)
                    say(t, "I surrendered to you because... I realized that resisting was just pointless and painful.  Even killing myself isn't possible without your permission.  ");
                else
                    say(t, "I realized that... I don't get to decide when I die.  I don't really understand why you keep me around, but I know that trying to kill myself will just get me punished.  ");
            } else
            if(defeatType == 4)
                if(dignity > 66)
                    say(t, "I realized how worthless I am on my own.  My only value in my fans... a-and if I don't serve you, you'll take them away from me...  ");
                else
                if(dignity > 33)
                    say(t, "I realize now that I can't do anything on my own, so I just want... I can only ask you to give me the power to transform once in awhile and still pretend to be one of the Chosen.  ");
                else
                    say(t, "Now that I've lost my powers, I know that the only way I can transform... the only way I can do anything... is if I do whatever you want me to.  ");
            if(hostility > 66)
            {
                if(morality > 66)
                    say(t, "It still hurts to fight innocent people, but I'm happy that it hurts, because I deserve it.");
                else
                if(morality > 33)
                    say(t, "The only time I can forget my fear is when I'm fighting...  It makes me happy.");
                else
                    say(t, "I promise that I won't become useless to you.  I'll make your enemies scream!");
            } else
            if(hostility > 33)
            {
                if(morality > 66)
                    say(t, "I'm trying to learn to enjoy hurting people... because I know I can't disobey you.");
                else
                if(morality > 33)
                    say(t, "Fighting for your sake is starting to make me happy... I guess that's a good thing.");
                else
                    say(t, "I don't hate the Demons anymore.  Or at least, I hate humans more.");
            } else
            if(morality > 66)
                say(t, "I'll even... hurt people for you... although I hate it...");
            else
            if(morality > 33)
                say(t, "I don't like fighting people for you, but I know that it's either them or me.");
            else
                say(t, "I'll fight whoever you want me to!  Whatever you want me to do!");
        } else
        if(obedience < 81)
        {
            if(confidence > 66)
                say(t, (new StringBuilder("I'm ")).append(mainName).append(", your greatest servant!  ").toString());
            else
            if(confidence > 33)
                say(t, (new StringBuilder(String.valueOf(mainName))).append(", reporting for duty!  ").toString());
            else
                say(t, (new StringBuilder("I'm... nothing but your servant.  You can call me ")).append(mainName).append(", or whatever else you wish...  ").toString());
            if(defeatType == 0)
            {
                if(confidence > 66)
                    say(t, "You were the only one strong enough to defeat me in battle, and I eventually realized that following you was what I needed to do!  ");
                else
                if(confidence > 33)
                    say(t, "I originally surrendered in order to avoid being killed, but now I understand that my place is beneath you.  ");
                else
                    say(t, (new StringBuilder("I was... an idiot who thought ")).append(heShe()).append(" had a chance to beat you.  I know better now...  ").toString());
            } else
            if(defeatType == 1)
            {
                if(morality > 66)
                    say(t, "The human governments are awful toward Chosen who start to fall, but you Demons give us all exactly what we deserve.  ");
                else
                if(morality > 33)
                    say(t, "I joined you because I was afraid of what the other humans would do to me once I started to fall.  And... I stayed with you because I want to serve you for your own sake.  ");
                else
                    say(t, "At first, I just joined you because I was angry about how the humans treat fallen Chosen.  But now I'm really happy to serve you!  ");
            } else
            if(defeatType == 2)
            {
                if(innocence > 66)
                    say(t, "I used to... fight you... I think...?  But that doesn't really sound like something I'd do, so I probably just imagined it!  ");
                else
                if(innocence > 33)
                    say(t, "Serving you... feels good.  I want... to keep feeling good...  ");
                else
                    say(t, "I understand... that my mind is beginning to break under your mighty influence.  The parts that are left... are entirely devoted to serving you.  ");
            } else
            if(defeatType == 3)
            {
                if(confidence > 66)
                    say(t, "You broke my pride to the point that I wanted to kill myself.  And then you built it back up!  I'm proud to live in order to serve you!  ");
                else
                if(confidence > 33)
                    say(t, "You taught me that there's no need to kill myself, when I can just serve you instead.  ");
                else
                    say(t, "I'm so ashamed that I used to try to kill myself without your permission.  After all, my life belongs to you.  ");
            } else
            if(defeatType == 4)
                if(dignity > 66)
                    say(t, "My fans helped to show me that what I should really be doing is serving you.  They saw me for what I truly am, a natural member of the Forsaken.  ");
                else
                if(dignity > 33)
                    say(t, "I'm honored that you allow me to lead some of your forces.  I just have to be careful to remind them that I'm just a servant to a greater power - you.  ");
                else
                    say(t, "I'm nothing without you.  I can't even transform on my own.  Serving you is my only purpose in life.  ");
            if(hostility > 66)
            {
                if(morality > 66)
                    say(t, "I used to be so weak and sentimental, but you taught me to throw all that aside.  Thank you so much!");
                else
                if(morality > 33)
                    say(t, "You've taught me to enjoy hurting people.  I'm so much happier now that I'm serving you.");
                else
                    say(t, "Please, please, send me out to fight for you!  To torture for you!  To kill for you!");
            } else
            if(hostility > 33)
            {
                if(morality > 66)
                    say(t, "I'm trying to learn to be bothered less by the screams of the people you send me after.  If I'm not learning fast enough, please feel free to torture me however you like...");
                else
                if(morality > 33)
                    say(t, "I never used to enjoy hurting people, but now that I'm doing it for your sake, it's... satisfying.");
                else
                    say(t, "I always had to hold back my violent urges, but now that I'm serving you, I can just let them all out!");
            } else
            if(morality > 66)
                say(t, "You're always torturing me by forcing me to hurt the innocent - and I'm so happy to be tortured by you!");
            else
            if(morality > 33)
                say(t, "I don't enjoy hurting people, but if it's for you, I'll do anything!");
            else
                say(t, "I'll kill whoever you want me to, torture whoever you want me to, and I won't feel a thing...");
        }
        say(t, "\"");
    }

    public void philosophyTalk(JTextPane t)
    {
        say(t, "\"");
        if(hostility < 20)
        {
            if(innocence > 66)
            {
                if(obedience > 66)
                    say(t, "I don't really get why you care what I think, since I'm dumb and you're basically the smartest thing in the world... um, n-not that I'm disobeying!  I'll do my best to explain it...  ");
                else
                if(obedience > 33)
                    say(t, "Are you just gonna ask me hard questions in order to make fun of my answers...?  Well, whatever, I'll answer anyway...  ");
                else
                    say(t, "Okay, I'll tell you what I think about everything!  Maybe I can convince you to stop doing all this bad stuff!  ");
                if(disgrace > 66)
                    say(t, "People are always mean to me nowadays, but ");
                else
                if(disgrace > 33)
                    say(t, "Back when I was one of the Chosen, people looked up to me, and ");
                else
                    say(t, "Even now that I'm not one of the Chosen anymore, people still kinda seem to look up to me, and ");
                if(morality > 66)
                    say(t, "I think it's my job to help them as much as I can.  ");
                else
                if(morality > 33)
                    say(t, "I don't like to see them get sad.  ");
                if(deviancy > 66)
                    say(t, "Even after all the weird stuff I've been through, that hasn't changed.");
                else
                if(deviancy > 33)
                    say(t, "I guess I could be wrong, but it just feels right to help people");
                else
                    say(t, "I know that there's a lot I don't know... it even feels like I just became one of the Chosen a few days ago... but I think that helping people is a good thing.");
            } else
            if(innocence > 33)
            {
                if(obedience > 66)
                    say(t, "Do you... really care what I think...?  Of course, I'll answer regardless!  ");
                else
                if(obedience > 33)
                    say(t, "Sure.  I guess we can talk about right and wrong, if that's what you want me to do.  ");
                else
                    say(t, "This is pointless.  We aren't going to change each other's minds.  But alright, I'll answer.  ");
                if(disgrace > 66)
                    say(t, "I understand better than anyone else how cruel people can be, but that doesn't change what's right.  ");
                else
                if(disgrace > 33)
                    say(t, "People may do evil things under your influence, but I don't think that means those people are actually evil.  ");
                else
                    say(t, "People are basically good, in my opinion.  ");
                if(morality > 66)
                    say(t, "And even when they actually do bad things on their own, I think it's important to show them compassion and forgiveness.  ");
                else
                    say(t, "Sometimes you have to use violence to stop them from doing bad things, but solving things nonviolently is always better whenever you can manage it.  ");
                if(deviancy > 66)
                    say(t, "Even in all the tortures you've put me through, when I look at the other people involved, I think I can see a core of goodness inside them.");
                else
                if(deviancy > 33)
                    say(t, "Some of the things I've seen people do when you use them to torture me have made me question that... but no, it's not enough to convince me otherwise.");
                else
                    say(t, "I guess that might be considered a sheltered point of view... but I haven't seen anything to disprove it.");
            } else
            {
                if(obedience > 66)
                    say(t, "My point of view must be of little value to you, since you are obviously my intellectual superior, but of course I will explain my own thoughts as best I can.  ");
                else
                if(obedience > 33)
                    say(t, "A philosphical discussion?  I am unsure of your purpose, but... if it is a command, I will obey it.  Very well.  ");
                else
                    say(t, "Having a philosophical discussion with what amounts to the physical manifestation of an ideology strikes me as... unproductive.  But nonetheless, I must take the opportunity to convince you to give up your evil ways.  ");
                if(disgrace > 66)
                    say(t, "My mistreatment at the hands of the people I attempted to protect has been, though humiliating, ultimately irrelevant to my moral convictions.  ");
                else
                if(disgrace > 33)
                    say(t, "Over the course of my downward arc from respected Chosen to playtoy for the Thralls, I have seen both sides of human nature, but I remain convinced that kindness still reigns over cruelty.  ");
                else
                    say(t, "Aside from those directly under your influence, I have found that humans are generally kind by nature.  ");
                if(morality > 66)
                    say(t, "I believe that it is important to follow moral principles such as nonviolence in order to respect the rights of others, and that as long as we do so, we can all make the world a much better place.  ");
                else
                    say(t, "I would not call myself an especially merciful or compassionate person, but I nonetheless believe that it is possible to live in harmony and mutual respect with the rest of society.  ");
                if(deviancy > 66)
                    say(t, "If it were possible to convince me otherwise, I think it already would have happened in the course of all the various tortures your minions have put me through.");
                else
                if(deviancy > 33)
                    say(t, "I think I've had more opportunities than most to see humans at their worst, and none of what I've seen has shaken my convictions.");
                else
                    say(t, "I acknowledge that I might be wrong, and I invite you to try to convince me otherwise.");
            }
        } else
        if(hostility < 40)
        {
            if(innocence > 66)
            {
                if(obedience > 66)
                    say(t, "I think it's kind of a waste of time to think much about 'philosophy', since you're so much smarter than I am and it's easier to just follow what you tell me to do.  Um... I guess I can tell you about what I think when I do think of it, though.  ");
                else
                if(obedience > 33)
                    say(t, "Are you just gonna ask me hard questions in order to make fun of my answers...?  Well, whatever, it's not like I care much about anything anyway...  ");
                else
                    say(t, "Okay, I'll tell you what I think about everything!  Though, I'm not exactly sure what to think lately...  ");
                if(disgrace > 66)
                    say(t, "After having to put up with the mean way everyone treats me nowadays, ");
                else
                if(disgrace > 33)
                    say(t, "After seeing how people are starting to be more mean to me since I started to lose, ");
                else
                    say(t, "Even though people have always considered me some sort of big hero, ");
                if(morality > 66)
                    say(t, "I'm starting to wonder whether it's really worth helping people.  ");
                else
                if(morality > 33)
                    say(t, "I've started liking other people less and less.  ");
                else
                    say(t, "I still really don't like people.  ");
                if(deviancy > 66)
                    say(t, "I've pretty much stopped paying attention to them so I can just focus on things that feel good.");
                else
                if(deviancy > 33)
                    say(t, "The more I find out about them, the more I realize that we're all really messed up inside..");
                else
                    say(t, "It's not like I want to kill them all or anything, but... I dunno, I should probably stop there.");
            } else
            if(innocence > 33)
            {
                if(obedience > 66)
                    say(t, "Okay.  First of all, though, just to be clear, I'm not going to let any of my own ideas stand in the way of serving you.  ");
                else
                if(obedience > 33)
                    say(t, "Sure.  I guess I can complain about how the world works, if you really want...  ");
                else
                    say(t, "This is pointless.  I'm not going to be able to convince you to do anything.  But alright, I'll answer.  ");
                if(disgrace > 66)
                    say(t, "Every since I fell from grace, people have been showing me their true colors, and they aren't pretty.  ");
                else
                if(disgrace > 33)
                    say(t, "I know better than most how cruel people can be.  ");
                else
                    say(t, "Some people still call me a defender of humanity, but I'm not interested in that sort of thing anymore.  ");
                if(morality > 66)
                    say(t, "I used to be more idealistic, but now... I'm not sure what to think anymore.  ");
                else
                if(morality > 33)
                    say(t, "I always knew that humanity was a mixed bag, but I think I was probably giving them too much credit.  ");
                else
                    say(t, "I never had much faith in humanity in the first place, and somehow I still end up disappointed on a regular basis.  ");
                if(deviancy > 66)
                    say(t, "I understand why people act the way they do - after all, I've done some pretty sick stuff for my own pleasure, too.  So, I can't really hate them for it.");
                else
                if(deviancy > 33)
                    say(t, "I'm not sure if I'm really any better than them, though.  They just want to feel good, and so do I.");
                else
                    say(t, "The worst part is that I don't think I've even seen how dark people's hearts can get...");
            } else
            {
                if(obedience > 66)
                    say(t, "Above all else, my purpose is to serve you.  That said, I am beginning to suspect that your actions are not as incompatible with the wellbeing of humanity as I had been led to believe.  ");
                else
                if(obedience > 33)
                    say(t, "If I weren't at least somewhat of a misanthrope, I wouldn't be cooperating with you as much as I am now.  ");
                else
                    say(t, "You want to know why I'm going along with your plans, even though I hate you so much.  Very well.  ");
                if(disgrace > 66)
                    say(t, "I've seen how cruel people can be, even without your direct influence.  ");
                else
                if(disgrace > 33)
                    say(t, "Now that I'm no longer held in high regard, people are more willing to show me their true nature, including the... darker parts.  ");
                else
                    say(t, "I've tried not to be swayed by the way people praise and pamper me.  ");
                if(morality > 66)
                    say(t, "I originally believed that individuals had natural rights, and that morality was an emergent property of consciousness.  I... no longer hold this view.  ");
                else
                if(morality > 33)
                    say(t, "My experiences have led me to the view that people act however they must in order to satisfy their own desires, and it's only by coincidence that these desires sometimes result in the appearance of selfless morality.  ");
                else
                    say(t, "I don't think that the term 'morality' refers to any objectively measurable entity, and based on the way they behave, I think that most people agree with me on some subconscious level.  ");
                if(deviancy > 66)
                    say(t, "After cultivating my own deviant impulses, I recognize that other people are essentially the same, myself included.  I feel neither pride nor hatred about that fact.");
                else
                if(deviancy > 33)
                    say(t, "Frankly, I'm not sure whether I can even consider myself fundamentally better than any of the most depraved torturers I've faced.");
                else
                    say(t, "The more I find out about what people truly desire, the more my feelings turn from apathy toward active disgust.");
            }
        } else
        if(hostility < 61)
        {
            if(innocence > 66)
            {
                if(obedience > 66)
                    say(t, "You mean like, why I'm serving you?  Well, why wouldn't I?  ");
                else
                if(obedience > 33)
                    say(t, "Um... I'm not really sure how to explain it, but I think you Demons might actually be right about some things.  ");
                else
                    say(t, "Oh, is it weird that I'm fighting on your side even though I kinda hate you?  I guess it doesn't seem so weird to me.  ");
                if(disgrace > 66)
                    say(t, "Humans are just the worst.  One day, they'll treat you like you're so great, but then a few weeks later, they all just bully you and make fun of you.  ");
                else
                if(disgrace > 33)
                    say(t, "Other people used to treat me so nice, but as soon as I started losing to the Demons, it was like they didn't even want to be seen with me anymore.  ");
                else
                    say(t, "People always act like I'm some great hero, but that's just 'cause they want me to do stuff for them.  ");
                if(morality > 66)
                    say(t, "I was... really stupid to think that they actually respected me.  ");
                else
                if(morality > 33)
                    say(t, "They tricked me, over and over again.  I'm tired of it.  ");
                else
                    say(t, "I wish they'd all just die.  ");
                if(deviancy > 66)
                    say(t, "I've decided that I just want to spend my life feeling good, no matter how many people it ends up hurting.");
                else
                if(deviancy > 33)
                    say(t, "I'm done with letting them do gross stuff to me.");
                else
                    say(t, "And the things they try to do with me whenever I let my guard down...  Ugh, people are just so gross!");
            } else
            if(innocence > 33)
            {
                if(obedience > 66)
                    say(t, "All I care about is serving you.  But even without that, I don't think the Demons are exactly wrong to do what they do.  ");
                else
                if(obedience > 33)
                    say(t, "I mean, I wouldn't be following your orders unless I thought you Demons were right about some stuff.  ");
                else
                    say(t, "I hate to say it, but I think my own views aren't that much different from those of the Demons.  ");
                if(disgrace > 66)
                    say(t, "I've seen for myself that people are all vicious, cruel bastards.  ");
                else
                if(disgrace > 33)
                    say(t, "I'm not happy about how my fellow humans have treated me, and I know that a lot of people face even worse.  ");
                else
                    say(t, "People have always hailed me as a savior, but there wouldn't be any need for saviors if everyone else would stop taking advantage of each other - even without any encouragement from the Demons.  ");
                if(morality > 66)
                    say(t, "I never even made a dent in all the evil of the world.  One hero can't reverse human nature.  ");
                else
                if(morality > 33)
                    say(t, "I'm done helping people.  They can get what's coming to them.  ");
                else
                    say(t, "If everyone's going to be constantly screwing everyone else over, I might as well join in.  ");
                if(deviancy > 66)
                    say(t, "I'm just going to spend the rest of my days living out my most twisted fantasies.");
                else
                if(deviancy > 33)
                    say(t, "The more I see, the less I care.");
                else
                    say(t, "Humanity is just... disgusting to the core, every last bit of it.");
            } else
            {
                if(obedience > 66)
                    say(t, "At this point, I'm absolutely certain that the Demons must be victorious in order for humanity to move forward.  ");
                else
                if(obedience > 33)
                    say(t, "In my time working with you, I've found that your goals are... surprisingly less objectionable than I would have thought.  ");
                else
                    say(t, "I'm working with the Demons because your interests and mine happen to coincide.  That is all.  ");
                if(disgrace > 66)
                    say(t, "The abuses I've suffered at the hands of humans, even those free of Demonic influence, have given me ample opportunity to see the truth of human nature.  ");
                else
                if(disgrace > 33)
                    say(t, "As my own public standing has fallen, I have had an opportunity to experience more and more of the abuse which is normally reserved for the drgs of society.  ");
                else
                    say(t, "The fame and fortune I experienced as one of the Chosen was not enough to distract me from noticing the constant abuse to which humans subject their less fortunate kin.  ");
                if(morality > 66)
                    say(t, "I now believe that altruism toward one's peers is not only pointless, but also contrary to human nature, and only results in greater problems in the long term.  ");
                else
                if(morality > 33)
                    say(t, "Personally, I feel no particular inclination toward cruelty, but I'm in the minority in that regard, and I see nothing wrong with giving people the treatment they secretly wish to visit upon others.  ");
                else
                    say(t, "Everyone is fundamentally cruel, although most prefer not to acknowledge their own cruelty.  ");
                if(deviancy > 66)
                    say(t, "I simply wish to be left alone to pursue my own pleasures.");
                else
                if(deviancy > 33)
                    say(t, "I'm beginning to grow numb to the perversions in which I see people indulge every day.");
                else
                    say(t, "Somehow, I'm still surprised when I see the depths to which some people will sink in pursuit of sadistic pleasure.");
            }
        } else
        if(hostility < 81)
            if(innocence > 66)
            {
                if(obedience > 66)
                    say(t, "Philosophy?  Um, I don't really think about it.  You just tell me who needs to suffer, and then I make it happen!  ");
                else
                if(obedience > 33)
                    say(t, "I don't really care about all that philosophy stuff.  All I know is that as long as I follow you, I get to make people cry and scream!  ");
                else
                    say(t, "Ugh, I don't wanna talk about boring stuff.  Hurry, send me out to make more people suffer...  ");
                if(disgrace > 66)
                    say(t, "They're always making fun of me, laughing behind my back... but they can't laugh after they're dead!  ");
                else
                if(disgrace > 33)
                    say(t, "I'll make them respect me again... make them fear me again...  ");
                else
                    say(t, "The way they scream and run from me is so much fun!  ");
                if(morality > 66)
                    say(t, "I was so stupid, always babbling about love and hope.  Hate and despair are so much cooler!  ");
                else
                if(morality > 33)
                    say(t, "I always just accepted everything everyone told me about right and wrong.  I'm a little smarter now!  ");
                else
                    say(t, "I always wanted to do stuff like this, but I let them trick me into being a hero...  Heheh, those days are over...  ");
                if(deviancy > 66)
                    say(t, "Nn...  Aaah, now that we've started talking about this, I'm getting all excited.  I wanna tear somebody apart right now!");
                else
                if(deviancy > 33)
                    say(t, "I wonder what I could do to have even more fun...");
                else
                    say(t, "It seems like no matter how many people I hurt, it's never quite enough to satisfy me...  Maybe there's something else missing...");
            } else
            if(innocence > 33)
            {
                if(obedience > 66)
                    say(t, "Well, obviously I hate humanity.  I'd stop hurting people if you told me to... but I'm hoping you don't tell me to do that.  ");
                else
                if(obedience > 33)
                    say(t, "I think a big part of the reason I'm following you is that it gives me an excuse to hurt people.  ");
                else
                    say(t, "Well, basically, my only reason for fighting alongside you is that I hate humanity even more than I hate the Demons.  ");
                if(disgrace > 66)
                    say(t, "The way they've treated me after I stopped being useful to them is part of why I hate everyone, but it's not all of it.  ");
                else
                if(disgrace > 33)
                    say(t, "Ever since I first started to lose to the Demons, people have started to show me their true faces.  But that's not even the main reason I hate them.  ");
                else
                    say(t, "Everyone either fears or respects me, sure, but that isn't enough to stop me from hating them.  ");
                if(morality > 66)
                    say(t, "They're all just so... selfish.  Even the supposedly selfless ones are just doing what they do for self-gratification.  If justice even means anything, then justice requires them to suffer.  ");
                else
                if(morality > 33)
                    say(t, "They just do whatever's easiest in the moment, justifying it to themselves after the fact.  They're animals pretending to be conscious beings.  ");
                else
                    say(t, "They're each irritating in their own little way.  Perverted, pathetic, self-absorbed, self-righteous, useless, disgusting humans.  ");
                if(deviancy > 66)
                    say(t, "Of course, I know I'm no exception.  I hate myself most of all.");
                else
                if(deviancy > 33)
                    say(t, "The only thing they're good for is hurting them to make myself feel better.");
                else
                    say(t, "I don't even exactly enjoy hurting them.  It just... makes me feel less empty inside for a little while.");
            } else
            {
                if(obedience > 66)
                    say(t, "I have cast aside philosophy in favor of blind servitude under you.  I don't attach any moral significance to making your enemies suffer.  ");
                else
                if(obedience > 33)
                    say(t, "I do not have much of a philosophy anymore.  I simply follow you in order to satisfy my sadistic urges.  ");
                else
                    say(t, "I've decided to devote my life toward making others suffer.  If it happens to serve your ends... well, I'm not happy about it, but it can't be helped.  ");
                if(disgrace > 66)
                    say(t, "I would like to say that humanity deserves it for turning its back on me, but that would just be retroactive self-justification.  ");
                else
                if(disgrace > 33)
                    say(t, "I do not particularly care who I victimize, whether they're those who have turned their back on me or those who still remain loyal.  ");
                else
                    say(t, "Although some still seem to consider me to be some sort of hero, the nature of my motivation is utterly amoral.  ");
                if(morality > 66)
                    say(t, "It took me much too long to realize that restricting myself to arbitrary ethical codes was pointless, and I intend to make up for lost time.  ");
                else
                if(morality > 33)
                    say(t, "My actions have no particular long-term purpose behind them.  ");
                else
                    say(t, "My prior obession with pride and glory was no less arbitrary than a deluded hero's efforts toward the 'greater good'.  ");
                if(deviancy > 66)
                    say(t, "I take a certain degree of sexual pleasure from the knowledge that I'm causing pain to others, and so I do so at every opportunity.  That's all.");
                else
                if(deviancy > 33)
                    say(t, "I simply act in whichever manner is necessary to make myself feel better in the moment.  It's perfectly rational.");
                else
                    say(t, "This is the only form of entertainment which I still enjoy.");
            }
        say(t, "\"");
    }

    public void trainingTalk(JTextPane t)
    {
        if(deviancy >= 20 && deviancy >= 40)
            if(deviancy < 61);
    }

    public void lifeTalk(JTextPane t)
    {
        if(disgrace >= 20 && disgrace >= 40)
            if(disgrace < 61);
    }

    public void trainingMenu(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s, final Boolean currentTraining[], int page, 
            final Boolean consenting)
    {
        p.removeAll();
        int currentHostility = 0;
        int currentDeviancy = 0;
        int currentObedience = 0;
        int currentDisgrace = 0;
        int types = 0;
        int hostilityCount = 0;
        int deviancyCount = 0;
        int obedienceCount = 0;
        int disgraceCount = 0;
        int trainingIntensities[][] = new int[currentTraining.length][4];
        for(int i = 0; i < currentTraining.length; i++)
        {
            if(i == 0)
            {
                trainingIntensities[i][0] = 20;
                trainingIntensities[i][1] = 20;
            } else
            if(i == 1)
            {
                trainingIntensities[i][0] = 10;
                trainingIntensities[i][2] = 15;
            } else
            if(i == 2)
            {
                trainingIntensities[i][0] = 15;
                trainingIntensities[i][3] = 15;
            } else
            if(i == 3)
            {
                trainingIntensities[i][1] = 15;
                trainingIntensities[i][2] = 10;
            } else
            if(i == 4)
            {
                trainingIntensities[i][1] = 10;
                trainingIntensities[i][3] = 10;
            } else
            if(i == 5)
            {
                trainingIntensities[i][2] = 20;
                trainingIntensities[i][3] = 20;
            }
            if(currentTraining[i].booleanValue())
            {
                types++;
                int baseHostility = trainingIntensities[i][0];
                int baseDeviancy = trainingIntensities[i][1];
                int baseObedience = trainingIntensities[i][2];
                int baseDisgrace = trainingIntensities[i][3];
                if(baseHostility > 0)
                {
                    if(currentHostility < baseHostility + 5 * hostilityCount)
                        currentHostility = baseHostility + 5 * hostilityCount;
                    else
                        currentHostility += 5;
                    hostilityCount++;
                }
                if(baseDeviancy > 0)
                {
                    if(currentDeviancy < baseDeviancy + 5 * deviancyCount)
                        currentDeviancy = baseDeviancy + 5 * deviancyCount;
                    else
                        currentDeviancy += 5;
                    deviancyCount++;
                }
                if(baseObedience > 0)
                {
                    if(currentObedience < baseObedience + 5 * obedienceCount)
                        currentObedience = baseObedience + 5 * obedienceCount;
                    else
                        currentObedience += 5;
                    obedienceCount++;
                }
                if(baseDisgrace > 0)
                {
                    if(currentDisgrace < baseDisgrace + 5 * disgraceCount)
                        currentDisgrace = baseDisgrace + 5 * disgraceCount;
                    else
                        currentDisgrace += 5;
                    disgraceCount++;
                }
            }
        }

        int currentIntensity[] = {
            currentHostility, currentDeviancy, currentObedience, currentDisgrace
        };
        int trainingCounts[] = {
            hostilityCount, deviancyCount, obedienceCount, disgraceCount
        };
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
        w.underlineAppend(t, "Current Training Intensity:");
        w.append(t, (new StringBuilder("\nHostility ")).append(currentHostility).append("%\nDeviancy ").append(currentDeviancy).append("%\nObedience ").append(currentObedience).append("%\nDisgrace ").append(currentDisgrace).append("%\n\n").toString());
        w.underlineAppend(t, "Current Training Modifiers:");
        if(types == 0)
        {
            w.append(t, "\nNone");
        } else
        {
            if(hostilityCount != deviancyCount)
            {
                w.append(t, "\nGained skill ");
                if(hostilityCount > deviancyCount)
                    w.append(t, (new StringBuilder("+")).append((hostilityCount - deviancyCount) * 20).append("%").toString());
                else
                    w.append(t, (new StringBuilder("-")).append((deviancyCount - hostilityCount) * 20).append("%").toString());
            }
            if(types != disgraceCount)
            {
                w.append(t, "\nStamina costs x");
                int difference = (hostilityCount + types) - disgraceCount;
                int base = 2;
                for(; difference > 1; difference--)
                    base *= 2;

                w.append(t, (new StringBuilder(String.valueOf(base))).toString());
            }
            if(deviancyCount != disgraceCount)
            {
                w.append(t, "\nMotivation restored x");
                int difference = deviancyCount - disgraceCount;
                if(deviancyCount > disgraceCount)
                {
                    int base = 2;
                    for(; difference > 1; difference--)
                        base *= 2;

                    w.append(t, (new StringBuilder(String.valueOf(base))).toString());
                } else
                {
                    w.append(t, "0.");
                    int base = 10000;
                    for(; difference < 0; difference++)
                        base /= 2;

                    if(base < 1000)
                        w.append(t, "0");
                    for(; base % 10 == 0 && base != 0; base /= 10);
                    w.append(t, (new StringBuilder(String.valueOf(base))).toString());
                }
            }
            if(obedienceCount > 0)
                w.append(t, "\nConsent not required\nIf not consenting, all corruption increases also increase Obedience");
        }
        String trainingNames[] = new String[currentTraining.length];
        for(int i = 0; i < trainingNames.length; i++)
            if(i == 0)
                trainingNames[0] = "Pheromone Curse";
            else
            if(i == 1)
                trainingNames[1] = "Psychic Torture";
            else
            if(i == 2)
                trainingNames[2] = "Self-Debasement";
            else
            if(i == 3)
                trainingNames[3] = "Attach Toys";
            else
            if(i == 4)
                trainingNames[4] = "Revealing Transformation";
            else
            if(i == 5)
                trainingNames[5] = "Public Confinement";

        if(types > 0)
        {
            w.append(t, "\n\n");
            w.underlineAppend(t, "In Progress");
            for(int i = 0; i < currentTraining.length; i++)
                if(currentTraining[i].booleanValue())
                    w.append(t, (new StringBuilder("\n")).append(trainingNames[i]).toString());

        }
        w.append(t, "\n\n");
        w.underlineAppend(t, "Training Options (Intensity)");
        for(int i = 0; i < 6; i++)
        {
            final int trainingType = page * 6 + i;
            final int threatenedIntensity[] = new int[4];
            if(!currentTraining[trainingType].booleanValue())
            {
                String trainingName = trainingNames[trainingType];
                w.append(t, (new StringBuilder("\n")).append(trainingName).append(" (").toString());
                Boolean firstFound = Boolean.valueOf(false);
                for(int j = 0; j < 4; j++)
                {
                    threatenedIntensity[j] = intensity(trainingIntensities[trainingType][j], currentIntensity[j], trainingCounts[j]);
                    if(threatenedIntensity[j] != currentIntensity[j])
                    {
                        if(threatenedIntensity[j] != trainingIntensities[trainingType][j])
                            w.append(t, (new StringBuilder("+")).append(threatenedIntensity[j] - currentIntensity[j]).append("% ").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(threatenedIntensity[j]))).append("% ").toString());
                        if(j == 0)
                            w.append(t, "Hostility");
                        else
                        if(j == 1)
                            w.append(t, "Deviancy");
                        else
                        if(j == 2)
                            w.append(t, "Obedience");
                        else
                            w.append(t, "Disgrace");
                        if(!firstFound.booleanValue())
                        {
                            w.append(t, ", ");
                            firstFound = Boolean.valueOf(true);
                        } else
                        {
                            w.append(t, ")");
                        }
                    }
                }

                JButton Action = new JButton(trainingName);
                Action.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        trainingDescription(t, p, f, w, s, currentTraining, trainingType, threatenedIntensity, consenting);
                    }

                    final Forsaken this$0;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final WorldState val$w;
                    private final SaveData val$s;
                    private final Boolean val$currentTraining[];
                    private final int val$trainingType;
                    private final int val$threatenedIntensity[];
                    private final Boolean val$consenting;

            
            {
                this$0 = Forsaken.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                currentTraining = aboolean;
                trainingType = i;
                threatenedIntensity = ai;
                consenting = boolean1;
                super();
            }
                });
                p.add(Action);
            }
        }

        JButton Back = new JButton("Back");
        if(types != 0)
            Back.setText("Done");
        final Forsaken x = this;
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                WriteObject wobj = new WriteObject();
                wobj.serializeSaveData(s);
                Project.ForsakenInteraction(t, p, f, w, s, x);
            }

            final Forsaken this$0;
            private final SaveData val$s;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final Forsaken val$x;

            
            {
                this$0 = Forsaken.this;
                s = savedata;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                x = forsaken1;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public void trainingDescription(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s, final Boolean currentTraining[], final int nextTraining, 
            int threatenedIntensity[], final Boolean consenting)
    {
        p.removeAll();
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
        Boolean firstTraining = Boolean.valueOf(true);
        for(int i = 0; i < currentTraining.length; i++)
            if(currentTraining[i].booleanValue())
                firstTraining = Boolean.valueOf(false);

        Boolean inPublic = Boolean.valueOf(currentTraining[0].booleanValue() || currentTraining[2].booleanValue() || currentTraining[4].booleanValue() || currentTraining[5].booleanValue());
        Boolean tiedUp = Boolean.valueOf(currentTraining[3].booleanValue() && !consenting.booleanValue() || currentTraining[5].booleanValue());
        if(nextTraining == 0)
        {
            w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" will be placed under a curse which renders ").append(himHer()).append(" sexually irresistible to everyone else in your domain.  ").toString());
            if(inPublic.booleanValue())
                w.append(t, (new StringBuilder("The attention ")).append(heShe()).append("'s getting from the public will become even more intense, more depraved, and less concerned with ").append(hisHer()).append(" own comfort.").toString());
            else
                w.append(t, (new StringBuilder("Every time ")).append(heShe()).append(" goes out in public, ").append(heShe()).append("'ll be the subject of constant sexual advances - and ").append(hisHer()).append(" suitors won't take 'no' for an answer.").toString());
        } else
        if(nextTraining == 1)
        {
            w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" will be given unfiltered access to the Demonic hivemind, whether ").append(heShe()).append(" wants it or not.  ").toString());
            if(inPublic.booleanValue())
                w.append(t, (new StringBuilder("Being able to experience the thoughts and emotions of all the people around ")).append(himHer()).append(" will give ").append(himHer()).append(" a deeper appreciation of humanity's innate sadism.").toString());
            else
            if(tiedUp.booleanValue())
                w.append(t, (new StringBuilder("The overwhelming flood of thoughts, emotions, and sensations that aren't ")).append(hisHer()).append(" own will render ").append(himHer()).append(" even more helpless.").toString());
            else
                w.append(t, (new StringBuilder("The overwhelming flood of thoughts, emotions, and sensations that aren't ")).append(hisHer()).append(" own will render ").append(himHer()).append(" completely helpless to resist your will.").toString());
        } else
        if(nextTraining == 2)
        {
            w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" will ").toString());
            if(inPublic.booleanValue())
                w.append(t, (new StringBuilder("submit ")).append(himHer()).append("self to the people around ").append(himHer()).append(", inviting them to ridicule and abuse ").append(himHer()).append(".  ").toString());
            else
                w.append(t, (new StringBuilder("go out in public and then submit ")).append(himHer()).append("self to everyone's ridicule and abuse.  ").toString());
            w.append(t, (new StringBuilder("Even the people that don't have any reason to hate ")).append(himHer()).append(" will be tempted to join in on the fun.").toString());
        } else
        if(nextTraining == 3)
        {
            w.append(t, (new StringBuilder("Various telekinetically-manipulated sex toys will attach themselves to ")).append(mainName).append("'s body and begin tormenting ").append(himHer()).append(" with pleasure.  ").toString());
            if(consenting.booleanValue())
                w.append(t, (new StringBuilder("If ")).append(heShe()).append(" tries to take them off, then restraints and bindings will arrive to prevent ").append(himHer()).append(" from doing so.").toString());
            else
            if(tiedUp.booleanValue())
                w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append("'s in no position to refuse.").toString());
            else
                w.append(t, (new StringBuilder("Ropes and cuffs will immobilize ")).append(hisHer()).append(" limbs so that ").append(heShe()).append(" can't take them off.").toString());
        } else
        if(nextTraining == 4)
        {
            w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" will be granted the psychic energy to transform and call on ").append(hisHer()).append(" Forsaken powers, but the clothes that come with ").append(hisHer()).append(" transformation will be modified to humiliate ").append(himHer()).append(".  ").toString());
            if(timesExposedSelf == 0)
            {
                w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" won't be able to maintain any degree of modesty.").toString());
            } else
            {
                w.append(t, (new StringBuilder("Exposing ")).append(hisHer()).append(" body doesn't bother ").append(himHer()).append(" much anymore, but ").toString());
                if(deviancy < 34)
                    w.append(t, "the blatantly sexual design will be even worse than just being seen naked.");
                else
                if(obedience < 34)
                    w.append(t, (new StringBuilder("being forced to wear an outfit of your choosing will rankle ")).append(himHer()).append(".").toString());
                else
                if(hostility < 34)
                    w.append(t, (new StringBuilder("the Demonic features incorporated into ")).append(hisHer()).append(" garb will highlight how far ").append(heShe()).append("'s fallen.").toString());
                else
                    w.append(t, (new StringBuilder("the fact that it's being forced upon ")).append(himHer()).append(" will take ").append(himHer()).append(" out of ").append(hisHer()).append(" comfort zone.").toString());
            }
        } else
        if(nextTraining == 5)
        {
            if(inPublic.booleanValue())
            {
                if(tiedUp.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s restraints will be anchored to the ground so that there's time for a larger crowd to gather and witness ").append(hisHer()).append(" humiliation.  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" will be immobilized in stationary restraints so that ").append(heShe()).append(" can no longer flee or fight back against the surrounding public.  ").toString());
            } else
            if(tiedUp.booleanValue())
                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" will be dragged out into public so that a larger number of people can witness ").append(hisHer()).append(" humiliation.  ").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" will be locked in outdoors stationary restraints so that a large number of people can see how helpless ").append(heShe()).append(" is.  ").toString());
            w.append(t, (new StringBuilder("The fact that ")).append(heShe()).append(" can't do anything to resist will be obvious to everyone, including ").append(himHer()).append(".").toString());
        }
        int corruptionComparison[] = {
            hostility, deviancy, obedience, disgrace
        };
        Boolean consentBlock = Boolean.valueOf(true);
        Boolean lacksConsent = Boolean.valueOf(!consenting.booleanValue());
        if(consenting.booleanValue())
        {
            w.append(t, "\n\n");
            int intensityFactor = 100;
            for(int i = 0; i < 4; i++)
                if(corruptionComparison[i] > threatenedIntensity[i])
                {
                    if(corruptionComparison[i] - threatenedIntensity[i] < intensityFactor)
                        intensityFactor = corruptionComparison[i] - threatenedIntensity[i];
                } else
                {
                    if(intensityFactor > 0)
                        intensityFactor = 0;
                    intensityFactor -= threatenedIntensity[i] - corruptionComparison[i];
                }

            w.append(t, "Consent factors: ");
            if(consentModifier() < 0)
                w.append(t, (new StringBuilder(String.valueOf(consentModifier()))).append(" (low obedience)").toString());
            else
                w.append(t, (new StringBuilder("+")).append(consentModifier()).append(" (obedience)").toString());
            if(intensityFactor < 0)
                w.append(t, (new StringBuilder(" ")).append(intensityFactor).append(" (intensity exceeds current corruption)").toString());
            else
                w.append(t, (new StringBuilder(" +")).append(intensityFactor).append(" (accustomed to similar circumstances)").toString());
            int consentTotal = consentModifier() + intensityFactor;
            w.append(t, (new StringBuilder(" = ")).append(consentTotal).toString());
            if(consentTotal < 0)
            {
                lacksConsent = Boolean.valueOf(true);
                for(int i = 0; i < currentTraining.length; i++)
                    if((currentTraining[i].booleanValue() || i == nextTraining) && (i % 6 == 1 || i % 6 == 3 || i % 6 == 5))
                        consentBlock = Boolean.valueOf(false);

            } else
            {
                consentBlock = Boolean.valueOf(false);
            }
        }
        say(t, "\n\n\"");
        if(!consenting.booleanValue() || lacksConsent.booleanValue() && (nextTraining % 6 == 1 || nextTraining % 6 == 3 || nextTraining % 6 == 5))
        {
            if(nextTraining == 0)
            {
                if(obedience < 20)
                {
                    if(innocence > 66)
                        say(t, "I-I'm not afraid of regular humans, even if you do make them way more perverted than usual!");
                    else
                    if(innocence > 33)
                        say(t, "Ugh, damn it, I can't fight back like this...");
                    else
                        say(t, "So you're encouraging them to torture me while I'm helpless?  Typical.");
                } else
                if(obedience < 40)
                {
                    if(innocence > 66)
                        say(t, "Let me go!  Th-They're looking really scary!");
                    else
                    if(innocence > 33)
                        say(t, "A-Are you crazy!?  If you drive them into a frenzy right now, they'll... completely mess me up...");
                    else
                        say(t, "R-Ridiculous!  Aren't they already lustful enough!?");
                } else
                if(obedience < 61)
                {
                    if(innocence > 66)
                        say(t, "I-I'm sorry!  I won't disobey you again!  S-So don't make them go even crazier!");
                    else
                    if(innocence > 33)
                        say(t, "They're going to go wild on me...  Please, please, don't do this!");
                    else
                        say(t, "Ah...  I... I understand that I displeased you, so...  Please do not make them... ravage me...");
                } else
                if(obedience < 81)
                {
                    if(innocence > 66)
                        say(t, "Wow, they're gonna... do all sorts of stuff to me...");
                    else
                    if(innocence > 33)
                        say(t, "If they go all-out, I'm... I'm not sure I'll be able to take it...");
                    else
                        say(t, "I... I understand.  I will try to learn as much as I can from them, at least.");
                } else
                if(innocence > 66)
                    say(t, "They'll be able to do whatever they want...");
                else
                if(innocence > 33)
                    say(t, "Nn... they'll be able to help break me completely...");
                else
                    say(t, "My body is yours to give to them as you wish...");
            } else
            if(nextTraining == 1)
            {
                if(obedience < 20)
                {
                    if(innocence > 66)
                        say(t, "Hey!  Stay outta my head!");
                    else
                    if(innocence > 33)
                        say(t, "My will is too strong to succumb to your cheap tricks!");
                    else
                        say(t, "I must focus.  Clear my mind of any attempted outside influence.");
                } else
                if(obedience < 40)
                {
                    if(innocence > 66)
                        say(t, "Huh!?  What kind of weird stuff are you trying to put in my head?");
                    else
                    if(innocence > 33)
                        say(t, "These thoughts... aren't mine...!");
                    else
                        say(t, "I... I must try not to lose myself...!");
                } else
                if(obedience < 60)
                {
                    if(innocence > 66)
                        say(t, "Please!  I'll do whatever you want, just stop making my head feel all funny!");
                    else
                    if(innocence > 33)
                        say(t, "You- You don't have to mess with my brain!  We can work something else out!");
                    else
                        say(t, "S-Surely my mind is a large part of what makes me useful to you.  If you tamper with it carelessly, I might... not be the same afterward...");
                } else
                if(obedience < 81)
                {
                    if(innocence > 66)
                        say(t, "I-I'll try to let you into my brain, but... I'm scared...!");
                    else
                    if(innocence > 33)
                        say(t, "I can't stop my mind from trying to force you out... so I guess you'll have to smash it to pieces...");
                    else
                        say(t, "I must apologize.  It appears as though some part of my subconscious still resists you...");
                } else
                if(innocence > 66)
                    say(t, "My head feels... so weird...");
                else
                if(innocence > 33)
                    say(t, "Even my thoughts... aren't my own anymore...");
                else
                    say(t, "My mind is yours to mold as you wish...");
            } else
            if(nextTraining == 2)
            {
                if(obedience < 20)
                {
                    if(innocence > 66)
                        say(t, "I'm not gonna let you present me to everyone like this!  I'll just... um... scream really loud at everyone until they get annoyed and go away!");
                    else
                    if(innocence > 33)
                        say(t, "I'm not submitting myself to anything!  Do you hear me!?  I refuse to submit!");
                    else
                        say(t, "Hmph.  I may be in no position to stop you from offering me to everyone, but I will not cooperate in the slightest!");
                } else
                if(obedience < 40)
                {
                    if(innocence > 66)
                        say(t, "Huh!?  W-Wait, don't show me to everyone like this, they'll think I'm super weak!");
                    else
                    if(innocence > 33)
                        say(t, "Guh...  Damn it, I guess I can't stop you from humiliating me however you want...");
                    else
                        say(t, "Hmph.  Showing everyone your dominion over me?  So be it.");
                } else
                if(obedience < 61)
                {
                    if(innocence > 66)
                        say(t, "Y-You're gonna humiliate me even more!?");
                    else
                    if(innocence > 33)
                        say(t, "Ah, w-wait, if you just let me go, I can show myself off much better than I can when you've got me like this!");
                    else
                        say(t, "Y-You could at least let me present myself to everyone on my own terms...!");
                } else
                if(obedience < 81)
                {
                    if(innocence > 66)
                        say(t, "Huh?  Y-You're gonna present me to everyone?  Is there anything I should be doing to help...?");
                    else
                    if(innocence > 33)
                        say(t, "Ah... I wish I could play along, but... I'm a little... overwhelmed right now...");
                    else
                        say(t, "I... I see.  Yes, everyone should know that I belong to you...");
                } else
                if(innocence > 66)
                    say(t, "Wow...!  E-Everyone will see...!");
                else
                if(innocence > 33)
                    say(t, "Yes... make sure I can't have second thoughts... since everyone will know that I'm completely yours...");
                else
                    say(t, "Let everyone see... how pathetic I am...");
            } else
            if(nextTraining == 3)
            {
                if(obedience < 20)
                {
                    if(innocence > 66)
                        say(t, "It'll take more than that to keep me under control!  I'll break free!  Watch me!");
                    else
                    if(innocence > 33)
                        say(t, "Using dirty tricks to keep me from fighting back?  You coward!");
                    else
                        say(t, "Heh.  Am I so dangerous that you have no choice but to tie me up?");
                } else
                if(obedience < 40)
                {
                    if(innocence > 66)
                        say(t, "Even if it feels really good... I have to make sure not to lose control...!");
                    else
                    if(innocence > 33)
                        say(t, "Forcing me to cum...  Ugh, damn it...");
                    else
                        say(t, "You may be able to get some... reactions from my body.  But my mind will not break.");
                } else
                if(obedience < 61)
                {
                    if(innocence > 66)
                        say(t, "H-Hey!  There's no need to use all that stuff on me right now, is there!?");
                    else
                    if(innocence > 33)
                        say(t, "Just...  Just let me catch my breath, please...!");
                    else
                        say(t, "If you don't let up... I will truly lose my mind...!");
                } else
                if(obedience < 81)
                {
                    if(innocence > 66)
                        say(t, "Huh...?  What're all those for...?");
                    else
                    if(innocence > 33)
                        say(t, "Aaah...  I'm already... going crazy...");
                    else
                        say(t, "I... I see...  I must be disciplined even further...");
                } else
                if(innocence > 66)
                    say(t, "More naughty stuff... eheheh...");
                else
                if(innocence > 33)
                    say(t, "This is getting so kinky...");
                else
                    say(t, "Please... train... my body... until I can endure even this...");
            } else
            if(nextTraining == 4)
            {
                if(obedience < 20)
                {
                    if(innocence > 66)
                        say(t, "But... But I can't fight back if I don't transform!");
                    else
                    if(innocence > 33)
                        say(t, "No matter what it forces me to wear, I'm still going to transform and fight back!");
                    else
                        say(t, "Using a humiliating appearance to dissuade me from transforming?  Even so, I will not give up!");
                } else
                if(obedience < 40)
                {
                    if(innocence > 66)
                        say(t, "Huh?  Something feels funny...");
                    else
                    if(innocence > 33)
                        say(t, "Wait, what are you doing to me!?");
                    else
                        say(t, "This disruption to my psychic energy...  Could it be...?");
                } else
                if(obedience < 61)
                {
                    if(innocence > 66)
                        say(t, "Taking control of the way I look?  Th-That's so mean!");
                    else
                    if(innocence > 33)
                        say(t, "You could at least let me choose how I look during all this...");
                    else
                        say(t, "I... I must confess that I do not appreciate having my appearance altered like that!");
                } else
                if(obedience < 81)
                {
                    if(innocence > 66)
                        say(t, "Um, I want to make you happy and all, but this feels weird...");
                    else
                    if(innocence > 33)
                        say(t, "Well, if you want to change how I look, I guess I can't refuse...");
                    else
                        say(t, "Does it please you more to train me in a different appearance?  Very well...");
                } else
                if(innocence > 66)
                    say(t, "I don't get what you're doing... but I guess that's okay...");
                else
                if(innocence > 33)
                    say(t, "Ah...  You're going to make sure everyone can see that I'm yours...");
                else
                    say(t, "The way that others see me... is yours to dictate.");
            } else
            if(nextTraining == 5)
                if(obedience < 20)
                {
                    if(innocence > 66)
                        say(t, "Everyone's going to be impressed when they see that I'm not broken yet!  Yeah, that's how it's gonna work!");
                    else
                    if(innocence > 33)
                        say(t, "F-Fine!  Let everyone see how hard I'm fighting back!");
                    else
                        say(t, "Isn't it too early to parade me around like some sort of trophy?  I am not defeated yet!");
                } else
                if(obedience < 40)
                {
                    if(innocence > 66)
                        say(t, "Everyone's going to laugh at me...");
                    else
                    if(innocence > 33)
                        say(t, "Ergh...  Fine, I guess it's not like I can hide the fact that I'm at your mercy...");
                    else
                        say(t, "I can only hope that the crowd won't grow too bold...");
                } else
                if(obedience < 61)
                {
                    if(innocence > 66)
                        say(t, "Aaah, no!  I'm scared of what they'll do to me while I'm like this!");
                    else
                    if(innocence > 33)
                        say(t, "Please, wait!  I'm not ready for everyone to see me like this!");
                    else
                        say(t, "To be seen by so many in my current state...  No, please, don't do this!");
                } else
                if(obedience < 81)
                {
                    if(innocence > 66)
                        say(t, "I'm afraid to go out in public like this... b-but I'll do it for you!");
                    else
                    if(innocence > 33)
                        say(t, "In public?  S-Sure, okay...");
                    else
                        say(t, "I see, so you wish to show everyone what you're doing to me...  Of course, I'm in no place to object.");
                } else
                if(innocence > 66)
                    say(t, "Aaah, wow, everyone will see...!");
                else
                if(innocence > 33)
                    say(t, "I'm already completely helpless before you.  And now we're going even further...");
                else
                    say(t, "This should ensure... that everyone knows I'm yours...");
        } else
        if(lacksConsent.booleanValue() && consentBlock.booleanValue())
        {
            if(nextTraining == 0)
            {
                if(deviancy < 20)
                {
                    if(morality > 66)
                        say(t, "I'm not just going to let everyone do perverted stuff with me!  ");
                    else
                    if(morality > 33)
                        say(t, "I'm not some sort of slut.  ");
                    else
                        say(t, "I don't feel like putting up with a bunch of perverted stuff.  ");
                } else
                if(hostility < 20)
                {
                    if(innocence > 66)
                        say(t, "There's no way I'd actually be able to make that many people happy, though...  ");
                    else
                    if(innocence > 33)
                        say(t, "I'm not going to let myself be raped, but I'm not going to hurt people either, especially if it can be avoided.  ");
                    else
                        say(t, "It would be wrong to tempt people like that - especially if fending them off required violence.  ");
                } else
                if(morality > 66)
                    say(t, "I may not be sure about how this serves your evil plans, but I still want no part in it.  ");
                else
                if(morality > 33)
                    say(t, "Why do you think I'd let you do something like that to me?  ");
                else
                    say(t, "This normally wouldn't bother me, but I still don't feel like playing along with your games.  ");
                if(innocence > 66)
                    say(t, "I'll just run away until the curse thingy wears off!");
                else
                if(innocence > 33)
                    say(t, "If you put that curse on me anyway, I'll just stop going outside for however long it lasts.");
                else
                    say(t, "It shouldn't be difficult to avoid social interaction for the duration of the curse.");
            } else
            if(nextTraining == 2)
            {
                if(disgrace < 15)
                {
                    if(morality > 66)
                        say(t, "Even defeated, I can still be a symbol of hope!  ");
                    else
                    if(morality > 33)
                        say(t, "I still have my reputation to consider!  ");
                    else
                        say(t, "Do you think that just because I'm your prisoner, I've given up on my dreams of becoming a beloved celebrity?  ");
                } else
                if(hostility < 15)
                {
                    if(dignity > 66)
                        say(t, "There still have to be a few people who haven't given up faith in me.  ");
                    else
                    if(dignity > 33)
                        say(t, "I don't want to betray the hope that people used to place in me...  ");
                    else
                        say(t, "I'm not going to lie to people and say stuff I don't believe in.  ");
                } else
                if(dignity > 66)
                    say(t, "You're just trying to sabotage my inevitable return to glory!  ");
                else
                if(dignity > 33)
                    say(t, "I'm not going to help you make yourself look more powerful.  ");
                else
                    say(t, "You want me to pretend to be submissive and defeated?  That's not happening.  ");
                if(confidence > 66)
                    say(t, "I still hold my head as high as ever!");
                else
                if(confidence > 33)
                    say(t, "I refuse!");
                else
                    say(t, "I-It may not be much, but I still have a little bit of pride left!");
            } else
            if(nextTraining == 4)
            {
                if(deviancy < 10)
                {
                    if(dignity > 66)
                        say(t, "B-But everyone will think that I'm totally a pervert!  And I'm not!  ");
                    else
                    if(dignity > 33)
                        say(t, "But won't it look kinda like I'm inviting them to touch me?  ");
                    else
                        say(t, "But what if they see my perverted outfit and try to do gross stuff with me?  ");
                } else
                if(disgrace < 10)
                {
                    if(innocence > 66)
                        say(t, "I don't wanna let people see my private parts!  ");
                    else
                    if(innocence > 33)
                        say(t, "If I let you pick my outfit, I just know I'll ending up showing off everything.  I'd rather not.  ");
                    else
                        say(t, "I would prefer to keep some degree of modesty.  ");
                } else
                if(confidence > 66)
                    say(t, "My outfit is perfect the way it is!  ");
                else
                if(confidence > 33)
                    say(t, "I'm not going to let you dress me up however you like!  ");
                else
                    say(t, "J-Just because you beat me doesn't mean I'm going to let you show me off like some sort of prize!  ");
                if(innocence > 66)
                    say(t, "If you try anything funny like that, I just won't transform at all!");
                else
                if(innocence > 33)
                    say(t, "You can't make me transform, so I won't.");
                else
                    say(t, "If I choose not to transform, then your alterations to the associated clothing are a moot point!");
            }
        } else
        if(lacksConsent.booleanValue())
        {
            if(nextTraining == 0)
            {
                if(obedience < 20)
                {
                    if(hostility < 20)
                    {
                        if(innocence > 66)
                            say(t, "Even if you try to tempt them, I know that these people won't go too far!  Probably!  ");
                        else
                        if(innocence > 33)
                            say(t, "This is wrong!  These people aren't your toys to manipulate.  ");
                        else
                            say(t, "Are you attempting to make me hate these people?  Don't bother.  I can see your strings controlling them.  ");
                    } else
                    if(innocence > 66)
                        say(t, "Hey!  I'm already so cute that people can't keep their hands off me, even without you doing weird stuff like that!  ");
                    else
                    if(innocence > 33)
                        say(t, "If you drive them crazy like that, I won't be able to control them!  ");
                    else
                        say(t, "This may surprise you, but my abilities do have limits.  I won't be able to maintain control of the people affected by the curse.  ");
                    if(deviancy < 20)
                    {
                        if(morality > 66)
                            say(t, "I'm not interested in being part of something perverted.");
                        else
                        if(morality > 33)
                            say(t, "You'd better not be trying to turn me into a pervert.");
                        else
                            say(t, "This will stop being fun if everyone starts acting like perverts.");
                    } else
                    if(morality > 66)
                        say(t, "I saw nothing wrong with playing along up until this point, but now I'm drawing the line.");
                    else
                    if(morality > 33)
                        say(t, "I won't forgive you if you take advantage of the situation to do it anyway.");
                    else
                        say(t, "I let you go this far for my own reasons, but I'm not interested in going any further!");
                } else
                {
                    if(deviancy < 20)
                    {
                        if(morality > 66)
                            say(t, "Wait, please!  If you let them go any further, they might really turn me into a pervert!  ");
                        else
                        if(morality > 33)
                            say(t, "H-Hold on, I'm not sure I can take it if you drive them all crazy...  ");
                        else
                            say(t, "Huh?  You're gonna make them even more perverted?  W-Wait, I don't want that!  ");
                    } else
                    if(morality > 66)
                        say(t, "I won't lie.  It turns me on to think of the things they'll do to me if you flip their switch.  But...  ");
                    else
                    if(morality > 33)
                        say(t, "Ah...  I'm... completely helpless like this...  Ergh, no, more importantly...  ");
                    else
                        say(t, "I guess that driving them crazy might also feel good for me... but...  ");
                    if(hostility < 20)
                    {
                        if(innocence > 66)
                            say(t, "I'd feel bad about tempting other people to do naughty things, too.  Can't you just leave them out of it...?");
                        else
                        if(innocence > 33)
                            say(t, "I don't think they'd want to be part of this if they were in their right minds.  Just... stick to punishing me, alright?");
                        else
                            say(t, "Manipulating their desires like this is unethical.  I... do not wish to be a part of it...");
                    } else
                    if(innocence > 66)
                        say(t, "They always get so mean when I can't fight back.");
                    else
                    if(innocence > 33)
                        say(t, "I hate them.  I don't want them to enjoy themselves.");
                    else
                        say(t, "I'm sure that they will only be focused on their own pleasure...");
                }
            } else
            if(nextTraining == 2)
            {
                if(obedience < 20)
                {
                    if(disgrace < 15)
                    {
                        if(morality > 66)
                            say(t, "Wait.  I am not prepared to give up my status as a hero of the people.  ");
                        else
                        if(morality > 33)
                            say(t, "Hold on, just because I let you go this far doesn't mean I'm willing to throw my dignity away!  ");
                        else
                            say(t, "Don't you dare try to exploit this situation to make me look weak!  ");
                    } else
                    if(morality > 66)
                        say(t, "People might not think of me as a hero anymore, but I'm not going to make it even worse!  ");
                    else
                    if(morality > 33)
                        say(t, "I'm not going to give people any more reason to think I've become weak.  ");
                    else
                        say(t, "Are you trying to sabotage my inevitable comeback as a beloved hero!?  ");
                    if(hostility < 15)
                    {
                        if(dignity > 66)
                            say(t, "If I completely give in, then surely everyone else will follow.");
                        else
                        if(dignity > 33)
                            say(t, "I have to live up to the hopes of the people who still believe in me!");
                        else
                            say(t, "Even if it's just stubbornness, I can't give up.");
                    } else
                    if(dignity > 66)
                        say(t, "Someone needs to show the ignorant masses what strength looks like.");
                    else
                    if(dignity > 33)
                        say(t, "If I can't be loved, at least I should be feared.");
                    else
                        say(t, "There's no way I'll act submissive just for your sake.");
                } else
                {
                    if(hostility < 15)
                    {
                        if(dignity > 66)
                            say(t, "I'm... not really comfortable with letting everyone know exactly how far I've fallen.  ");
                        else
                        if(dignity > 33)
                            say(t, "I'm sorry, but I don't think I can make myself say those words to everyone...  ");
                        else
                            say(t, "I can't go that far.  There were never very many people who put their hopes in me... but there were a few.  ");
                    } else
                    if(dignity > 66)
                        say(t, "I... don't want to humiliate myself like that.  ");
                    else
                    if(dignity > 33)
                        say(t, "I'm not going to help you humiliate me.  ");
                    else
                        say(t, "That kind of over-the-top submission just leaves a bad taste in my mouth.  ");
                    if(disgrace < 15)
                    {
                        if(morality > 66)
                            say(t, "People still put their hopes in me... for some reason.");
                        else
                        if(morality > 33)
                            say(t, "My reputation is all I have now.");
                        else
                            say(t, "As long as people respect me, I still have some power...");
                    } else
                    if(morality > 66)
                        say(t, "Even if I'm not respected anymore... it's the principle of the thing.");
                    else
                    if(morality > 33)
                        say(t, "I may already be disgraced, but I don't have to make it even worse...");
                    else
                        say(t, "Making people fear me again is the only hope I have...");
                }
            } else
            if(nextTraining == 4)
                if(obedience < 20)
                {
                    if(deviancy < 10)
                    {
                        if(dignity > 66)
                            say(t, "But if you do that, everyone will think I'm a pervert!  ");
                        else
                        if(dignity > 33)
                            say(t, "I won't forgive you if you make me dress me up like a pervert!  ");
                        else
                            say(t, "Yuck, this sounds totally gross!  ");
                    } else
                    if(dignity > 66)
                        say(t, "Hey, I might like to show off a little, but only on my own terms!  ");
                    else
                    if(dignity > 33)
                        say(t, "You aren't the one who decides how much skin I show off!  ");
                    else
                        say(t, "If you mess with my transformation, I'm going to fight my hardest to get out of here right now.  ");
                    if(disgrace < 10)
                    {
                        if(innocence > 66)
                            say(t, "You're just mad that people still respect me even after all the stuff you've put me through!");
                        else
                        if(innocence > 33)
                            say(t, "Everyone knows how strong I am, and I plan on keeping it that way.");
                        else
                            say(t, "I won't squander my reputation, especially not for your sake.");
                    } else
                    if(innocence > 66)
                        say(t, "It's bad enough that everyone's always making fun of me!");
                    else
                    if(innocence > 33)
                        say(t, "No matter what people say, I'm still strong enough that you shouldn't piss me off.");
                    else
                        say(t, "I won't assist you in your attempts to make my reputation sink even further.");
                } else
                {
                    if(disgrace < 10)
                    {
                        if(innocence > 66)
                            say(t, "W-Wait, people still sort of respect me...!  ");
                        else
                        if(innocence > 33)
                            say(t, "This would really mark me as belonging to you...  ");
                        else
                            say(t, "I suppose it would have been overly optimistic to expect you to allow me to keep my reputation.  Even so...  ");
                    } else
                    if(innocence > 66)
                        say(t, "Well, it's probably impossible for people to make fun of me more than they already are.  But still...  ");
                    else
                    if(innocence > 33)
                        say(t, "It might be too late to save my reputation, but...  ");
                    else
                        say(t, "I'm aware that I'm already somewhat of a laughing stock, but...  ");
                    if(deviancy < 10)
                    {
                        if(dignity > 66)
                            say(t, "It'd look like I'm inviting them to do weird stuff with me...");
                        else
                        if(dignity > 33)
                            say(t, "It's just way too perverted...");
                        else
                            say(t, "Everyone will want to touch me, and I probably won't be able to stop myself from running away...");
                    } else
                    if(dignity > 66)
                        say(t, "I won't be able to take it.");
                    else
                    if(dignity > 33)
                        say(t, "This is just too extreme.");
                    else
                        say(t, "I don't want to let you do it.");
                }
        } else
        if(nextTraining == 0)
        {
            if(obedience < 20)
            {
                if(deviancy < 20)
                {
                    if(morality > 66)
                        say(t, "You're trying to get me wrapped up in immoral behavior.  But ");
                    else
                    if(morality > 33)
                        say(t, "I don't enjoy this sort of thing at all.  But ");
                    else
                        say(t, "Hmph.  I'm not interested in being entertainment for your minions.  But ");
                } else
                if(morality > 66)
                    say(t, "I suppose I deserve to take a little break.  And ");
                else
                if(morality > 33)
                    say(t, "I'd normally refuse to play along, but this does sound like it could be fun.  And ");
                else
                    say(t, "One way or another, your minions will entertain me.  And ");
                if(hostility < 20)
                {
                    if(innocence > 66)
                        say(t, "I like making people happy.");
                    else
                    if(innocence > 33)
                        say(t, "I should be able to ensure that nobody gets hurt.");
                    else
                        say(t, "I'm not one to hold people's lustful natures against them.");
                } else
                if(innocence > 66)
                    say(t, "if they try to go too far, I'll just beat them up!");
                else
                if(innocence > 33)
                    say(t, "I'm not afraid of anything they can do to me.");
                else
                    say(t, "I have nothing to fear from regular people.");
            } else
            {
                if(hostility < 20)
                {
                    if(innocence > 66)
                        say(t, "They're not actually gonna hurt me, right?  ");
                    else
                    if(innocence > 33)
                        say(t, "I'll just have to trust that they won't be too rough with me...  ");
                    else
                        say(t, "I expect that they may get a bit... rough with me.  But I will not fight back.  ");
                } else
                if(innocence > 66)
                    say(t, "I'm a little worried about what they're gonna do to me...  ");
                else
                if(innocence > 33)
                    say(t, "Ugh, they're going to get rough with me again, I know it...  ");
                else
                    say(t, "Would you prefer that I fight back, or should I let them... have their way with me?  ");
                if(deviancy < 20)
                {
                    if(morality > 66)
                        say(t, "Maybe I deserve it all...");
                    else
                    if(morality > 33)
                        say(t, "I wish I were kinky enough to enjoy it...");
                    else
                        say(t, "I hate them so much...");
                } else
                if(morality > 66)
                    say(t, "Although, it does feel sort of good to get punished...");
                else
                if(morality > 33)
                    say(t, "Sometimes I enjoy it, but still...");
                else
                    say(t, "They don't really care about my pleasure...");
            }
        } else
        if(nextTraining == 1)
        {
            if(obedience < 20)
            {
                if(hostility < 10)
                {
                    if(confidence > 66)
                        say(t, "My faith in humanity will survive whatever you try to show me!  ");
                    else
                    if(confidence > 33)
                        say(t, "I won't hide from whatever you have to show me.  ");
                } else
                if(confidence > 66)
                    say(t, "Nothing you can show me will surprise me anymore, Demon Lord.  ");
                else
                if(confidence > 33)
                    say(t, "I've already seen the worst that humanity has to offer.  ");
                if(morality > 66)
                    say(t, "I can endure all of it.");
                else
                if(morality > 33)
                    say(t, "You're wasting your time.");
                else
                    say(t, "You must not know me that well after all.");
            } else
            {
                if(morality > 66)
                    say(t, "I always thought that people were basically good.  ");
                else
                if(morality > 33)
                    say(t, "I sometimes had my doubts about whether humanity was actually worth saving.  ");
                else
                    say(t, "I've always known how cruel people are, deep down inside...  ");
                if(hostility < 10)
                {
                    if(confidence > 66)
                        say(t, "And I still... I still think so!  Punish me for that if you want!");
                    else
                    if(confidence > 33)
                        say(t, "No, I mean, I still think that people are basically good!");
                    else
                        say(t, "And... y-you won't be able to change my mind!  Phew, I actually said it...");
                } else
                if(confidence > 66)
                    say(t, "But if you want to show me this, I can't stop you anymore.");
                else
                if(confidence > 33)
                    say(t, "I suppose I should thank you for showing me the truth.");
                else
                    say(t, "But to think that they'd enjoy hurting me so much...");
            }
        } else
        if(nextTraining == 2)
        {
            if(obedience < 20)
            {
                if(disgrace < 15)
                {
                    if(morality > 66)
                        say(t, "The people know that I'm their hero, no matter what I'm forced to say.  ");
                    else
                    if(morality > 33)
                        say(t, "Do you really think you can ruin my reputation just by forcing me to do this sort of thing?  ");
                    else
                        say(t, "I'm so popular that I don't even need to worry about making people think less of me!  ");
                } else
                if(morality > 66)
                    say(t, "I've always cared more about being right than about being popular.  ");
                else
                if(morality > 33)
                    say(t, "Hmph.  I don't care what people think of me anymore.  ");
                else
                    say(t, "If I can convince these idiots that I'm weak, then they'll just be taken by surprise when I show them my true power.  ");
                if(hostility < 15)
                {
                    if(dignity > 66)
                        say(t, "I'll just have to hope that they understand that I don't mean any of the things I say.");
                    else
                    if(dignity > 33)
                        say(t, "It's embarrassing, but I wouldn't be helping anybody by refusing.");
                    else
                        say(t, "If I can keep you occupied with me just by saying some meaningless words to everyone, then I'll do it gladly.");
                } else
                if(dignity > 66)
                    say(t, "If I had wasted less of my time on being a celebrity and focused on my own power, I probably wouldn't have lost to you in the first place.");
                else
                if(dignity > 33)
                    say(t, "They're all fools anyway.");
                else
                    say(t, "I can beat you without anyone else's help.");
            } else
            {
                if(hostility < 15)
                {
                    if(dignity > 66)
                        say(t, "I was wrong to tell everyone that I was strong in the first place.  ");
                    else
                    if(dignity > 33)
                        say(t, "It's embarrassing, but everyone deserves to hear the truth about me from my own mouth.  ");
                    else
                        say(t, "So I just have to tell them the truth?  I can do that.  ");
                } else
                if(dignity > 66)
                    say(t, "I was an idiot to care about what people thought of me in the first place.  ");
                else
                if(dignity > 33)
                    say(t, "I don't even want people to like me anymore.  ");
                else
                    say(t, "It doesn't matter what people think of me.  I'll say whatever you want me to.  ");
                if(disgrace < 15)
                {
                    if(morality > 66)
                        say(t, "I should have done this a long time ago.");
                    else
                    if(morality > 33)
                        say(t, "I'm tired of pretending to be strong.");
                    else
                        say(t, "Relying on other humans is just weakness...");
                } else
                if(morality > 66)
                    say(t, "This is part of burying the old me.");
                else
                if(morality > 33)
                    say(t, "It's not like I have anything left to lose at this point.");
                else
                    say(t, "Let them feel some of my despair...");
            }
        } else
        if(nextTraining == 3)
        {
            if(obedience < 20)
            {
                if(innocence > 66)
                    say(t, "You're a huge pervert, Demon Lord.  ");
                else
                if(innocence > 33)
                    say(t, "Am I supposed to be afraid of this sort of thing?  ");
                else
                    say(t, "Attempting to break my will with pleasure?  I am not worried.  ");
                if(deviancy < 15)
                {
                    if(confidence > 66)
                        say(t, "I'll go along with it, but don't expect me to actually enjoy any of this.");
                    else
                    if(confidence > 33)
                        say(t, "Even if I start to enjoy it, I'm still gonna keep resisting you!");
                } else
                if(confidence > 66)
                    say(t, "I guess I'll let you pleasure me.");
                else
                if(confidence > 33)
                    say(t, "I'll just take this chance to enjoy myself.");
            } else
            {
                if(deviancy < 15)
                {
                    if(confidence > 66)
                        say(t, "I can't believe I'm just letting you do this to me...  ");
                    else
                    if(confidence > 33)
                        say(t, "This sounds perverted, but I can't really refuse...  ");
                    else
                        say(t, "O-Okay...  Even though I don't really want to do this kind of thing...  ");
                } else
                if(confidence > 66)
                    say(t, "Here, let me help you put them on me!  ");
                else
                if(confidence > 33)
                    say(t, "This should be fun...  ");
                else
                    say(t, "Ah, y-yes, I enjoy this sort of thing now, so...  ");
                if(innocence > 66)
                    say(t, "I'm becoming a huge pervert...");
                else
                if(innocence > 33)
                    say(t, "I'm ready to cum for you.");
                else
                    say(t, "Thank you for granting me pleasure.");
            }
        } else
        if(nextTraining == 4)
        {
            if(obedience < 20)
            {
                if(deviancy < 10)
                {
                    if(dignity > 66)
                        say(t, "But I don't really want to look super perverted!  ");
                    else
                    if(dignity > 33)
                        say(t, "If you do that, then people will basically think I'm inviting them to do weird stuff with me!  ");
                    else
                        say(t, "That'll make it super annoying to stop people from doing weird stuff to me!  ");
                } else
                if(dignity > 66)
                    say(t, "I do like the idea of getting everyone to look at me.  ");
                else
                if(dignity > 33)
                    say(t, "Hm, it'll be like I'm announcing my lewdness to the whole city.  ");
                else
                    say(t, "Well, I don't really care whether people think my outfit is appropriate or not.  ");
                if(disgrace < 10)
                {
                    if(innocence > 66)
                        say(t, "This had better not change the way that people think about me...");
                    else
                    if(innocence > 33)
                        say(t, "I doubt that your modifications will be able to stand in the way of my natural charm.");
                    else
                        say(t, "Perhaps it would be better to get everyone accustomed to seeing me like this on my own terms.");
                } else
                if(innocence > 66)
                    say(t, "It's not like I'm gonna refuse to transform or anything like that.");
                else
                if(innocence > 33)
                    say(t, "It's not like I have much of a reputation to damage at this point.");
                else
                    say(t, "I may be able to turn this to my advantage.");
            } else
            {
                if(disgrace < 10)
                {
                    if(innocence > 66)
                        say(t, "Ah, we're gonna tell everyone that I'm a huge pervert...  ");
                    else
                    if(innocence > 33)
                        say(t, "I can't keep pretending to be pure and innocent anymore...  ");
                    else
                        say(t, "Frankly, I'm surprised that my perversions aren't common knowledge by now...  ");
                } else
                if(innocence > 66)
                    say(t, "Everyone already thinks that I'm a huge pervert, so this should be fine...  ");
                else
                if(innocence > 33)
                    say(t, "Well, it's not like I'm respected much anymore anyway...  ");
                else
                    say(t, "I've already suffered the shame of being defeated by you.  This isn't any worse.  ");
                if(deviancy < 10)
                {
                    if(dignity > 66)
                        say(t, "I always feels so weird when people look at my whole body...");
                    else
                    if(dignity > 33)
                        say(t, "I just hope the sight doesn't get them too excited...");
                    else
                        say(t, "I hope it doesn't get too chilly with most of my clothes gone...");
                } else
                if(dignity > 66)
                    say(t, "I actually enjoy the way their eyes feel on my body...");
                else
                if(dignity > 33)
                    say(t, "My heart is pounding...");
                else
                    say(t, "Just tell me whenever you want me transformed.");
            }
        } else
        if(nextTraining == 5)
            if(obedience < 20)
            {
                if(disgrace < 20)
                {
                    if(confidence > 66)
                        say(t, "Hah!  Fine!  Let everyone see how bravely I resist you!  ");
                    else
                        say(t, "Hmph.  If you're going to torture me anyway, then there's no point in making a scene.  ");
                } else
                if(confidence > 66)
                    say(t, "Sure, I'll show you how well I can handle your torture!  I don't even care who's watching!  ");
                else
                    say(t, "I don't have much of a reputation anymore, so I don't really care if you tie me up in public.  ");
                if(dignity > 66)
                    say(t, "I won't scream.");
                else
                if(dignity > 33)
                    say(t, "I'm not afraid.");
                else
                    say(t, "Let's get this over with.");
            } else
            {
                if(disgrace < 20)
                {
                    if(confidence > 66)
                        say(t, "Ugh...  I already know I'm not strong anymore, you don't have to show everyone...  ");
                    else
                    if(confidence > 33)
                        say(t, "I guess it was too much to hope that you'd let everyone keep thinking I'm strong...  ");
                    else
                        say(t, "I-I'm not going to fight back, so you don't need to tie me up...  ");
                } else
                if(confidence > 66)
                    say(t, "Well, everyone already knows I'm not strong anymore...  ");
                else
                if(confidence > 33)
                    say(t, "In public?  Well, alright...  ");
                else
                    say(t, "A-Alright...  I'm ready for you to tie me up anytime...  ");
                if(dignity > 66)
                    say(t, "I'm so ashamed.");
                else
                if(dignity > 33)
                    say(t, "It's a bit embarrassing...");
                else
                    say(t, "Do you want my hands behind my back?");
            }
        say(t, "\"");
        final int increases[] = new int[4];
        if(!consenting.booleanValue() || !lacksConsent.booleanValue() || !consentBlock.booleanValue())
        {
            w.append(t, "\n\nCorruption Increase:");
            for(int i = 0; i < 4; i++)
                if(corruptionComparison[i] + deviancyBuffer() < threatenedIntensity[i])
                    increases[i] = ((threatenedIntensity[i] + 4) - (corruptionComparison[i] + deviancyBuffer())) / 5;

            if(!consenting.booleanValue() || lacksConsent.booleanValue())
            {
                for(int i = 0; i < 4; i++)
                    if(i != 2)
                        increases[2] += increases[i];

            }
            if(increases[0] == 0 && increases[1] == 0 && increases[2] == 0 && increases[3] == 0)
            {
                w.append(t, "\nNone");
            } else
            {
                for(int i = 0; i < 4; i++)
                {
                    Boolean comma = Boolean.valueOf(false);
                    if(increases[i] > 0)
                    {
                        if(comma.booleanValue())
                            w.append(t, ",");
                        else
                            comma = Boolean.valueOf(true);
                        w.append(t, (new StringBuilder(" +")).append(increases[i]).append("% ").toString());
                        if(i == 0)
                            w.append(t, "Hostility");
                        else
                        if(i == 1)
                            w.append(t, "Deviancy");
                        else
                        if(i == 2)
                            w.append(t, "Obedience");
                        else
                            w.append(t, "Disgrace");
                    }
                }

            }
        }
        Boolean helpless = Boolean.valueOf(false);
        for(int i = 1; i < currentTraining.length; i += 2)
            if(currentTraining[i].booleanValue())
                helpless = Boolean.valueOf(true);

        if(!consenting.booleanValue() || helpless.booleanValue())
            w.append(t, (new StringBuilder("\n\n")).append(mainName).append(" is helpless, so you can proceed without ").append(hisHer()).append(" consent.").toString());
        else
        if(lacksConsent.booleanValue() && !consentBlock.booleanValue())
            w.append(t, (new StringBuilder("\n\nThis type of training doesn't require ")).append(mainName).append("'s cooperation, so you can proceed without ").append(hisHer()).append(" consent.").toString());
        else
        if(lacksConsent.booleanValue())
            w.append(t, (new StringBuilder("\n\nThis type of training requires ")).append(mainName).append("'s cooperation, so you cannot continue.").toString());
        if(!consenting.booleanValue() || !consentBlock.booleanValue() || !lacksConsent.booleanValue())
        {
            JButton Proceed = new JButton("Proceed");
            final Boolean nowConsenting = Boolean.valueOf(consenting.booleanValue() && !lacksConsent.booleanValue());
            Proceed.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    hostility += increases[0];
                    deviancy += increases[1];
                    obedience += increases[2];
                    disgrace += increases[3];
                    executeTraining(t, p, f, w, s, currentTraining, nextTraining, nowConsenting);
                }

                final Forsaken this$0;
                private final int val$increases[];
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final WorldState val$w;
                private final SaveData val$s;
                private final Boolean val$currentTraining[];
                private final int val$nextTraining;
                private final Boolean val$nowConsenting;

            
            {
                this$0 = Forsaken.this;
                increases = ai;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                currentTraining = aboolean;
                nextTraining = i;
                nowConsenting = boolean1;
                super();
            }
            });
            p.add(Proceed);
        }
        JButton Back = new JButton("Cancel");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                trainingMenu(t, p, f, w, s, currentTraining, nextTraining / 6, consenting);
            }

            final Forsaken this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final SaveData val$s;
            private final Boolean val$currentTraining[];
            private final int val$nextTraining;
            private final Boolean val$consenting;

            
            {
                this$0 = Forsaken.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                currentTraining = aboolean;
                nextTraining = i;
                consenting = boolean1;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public void executeTraining(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s, final Boolean currentTraining[], int nextTraining, 
            final Boolean consenting)
    {
        p.removeAll();
        w.append(t, (new StringBuilder("\n\n")).append(w.getSeparator()).append("\n\n").toString());
        Boolean tied = currentTraining[5];
        if(currentTraining[3].booleanValue() && !consenting.booleanValue())
            tied = Boolean.valueOf(true);
        Boolean helpless = tied;
        if(currentTraining[1].booleanValue() || currentTraining[3].booleanValue())
            helpless = Boolean.valueOf(true);
        Boolean inPublic = Boolean.valueOf(currentTraining[0].booleanValue() || currentTraining[2].booleanValue() || currentTraining[4].booleanValue() || currentTraining[5].booleanValue());
        Boolean stimulated = currentTraining[3];
        Boolean servicing = Boolean.valueOf(currentTraining[0].booleanValue() && (helpless.booleanValue() || orgasmsGiven >= 1000));
        Boolean penile = Boolean.valueOf(stimulated.booleanValue() && gender != Gender.FEMALE);
        Boolean clitoral = Boolean.valueOf(stimulated.booleanValue() && gender == Gender.FEMALE);
        Boolean anal = Boolean.valueOf(false);
        Boolean vaginal = Boolean.valueOf(false);
        if(servicing.booleanValue() || stimulated.booleanValue())
            if(gender == Gender.MALE)
            {
                if(timesHadSex > 0)
                    anal = Boolean.valueOf(true);
            } else
            {
                if(timesTortured > 0)
                    anal = Boolean.valueOf(true);
                if(timesHadSex > 0)
                    vaginal = Boolean.valueOf(true);
            }
        Boolean orgasming = Boolean.valueOf((stimulated.booleanValue() || vaginal.booleanValue() || anal.booleanValue()) && timesOrgasmed > 0);
        Boolean fighting = Boolean.valueOf(!consenting.booleanValue() && peopleInjured > 0);
        Boolean oral = Boolean.valueOf(servicing.booleanValue() && (!fighting.booleanValue() || obedience >= 20));
        int penetrations = 0;
        if(oral.booleanValue())
            penetrations++;
        if(servicing.booleanValue())
        {
            if(vaginal.booleanValue())
                penetrations++;
            if(anal.booleanValue())
                penetrations++;
        }
        Boolean pained = currentTraining[1];
        if((currentTraining[0].booleanValue() || nextTraining == 0) && (servicing.booleanValue() || stimulated.booleanValue()) || currentTraining[4].booleanValue() || currentTraining[5].booleanValue() || nextTraining == 4 || nextTraining == 5)
        {
            int added = 15 + (int)(Math.random() * 15D);
            timesExposed += added;
            if(consenting.booleanValue())
                timesExposedSelf += added;
        }
        if(nextTraining == 0)
        {
            if(inPublic.booleanValue())
                w.append(t, (new StringBuilder("A shudder runs through the crowd of people surrounding ")).append(mainName).append(".  ").toString());
            else
            if(helpless.booleanValue())
                w.append(t, (new StringBuilder("Subconsciously drawn in by the potent pheromones wafting off ")).append(hisHer()).append(" body, it's not long before several people loitering in the slums come across the room where ").append(mainName).append(" lays helpless.  ").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" knows the effect the pheromones will have on those around ").append(himHer()).append(", but ").append(heShe()).append(" still goes out in public.  It isn't long before ").append(heShe()).append("'s attracted a crowd.  ").toString());
            if(penetrations > 0)
            {
                if(fighting.booleanValue() && obedience < 20)
                    w.append(t, (new StringBuilder("One attacker, succumbing to his amplified lust, tries again to shove his cock into ")).append(mainName).append("'s mouth, but ").append(mainName).append(" bares ").append(hisHer()).append(" teeth in warning, and the attacker wisely chooses to back off rather than press the issue.  ").toString());
                else
                if(penetrations > 1)
                    w.append(t, (new StringBuilder("The people fucking ")).append(mainName).append(" begin to thrust with renewed intensity, causing ").append(himHer()).append(" to groan with every impact deep inside ").append(himHer()).append(".  ").toString());
                else
                if(oral.booleanValue())
                {
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" whimpers around ").append(hisHer()).append(" mouthful of cock as ").append(heShe()).append(" feels a pair of hands roughly grabbing ").append(hisHer()).append(" hair, and the man ").append(heShe()).append("'s servicing begins to fuck ").append(hisHer()).append(" throat with urgent intensity.  ").toString());
                } else
                {
                    w.append(t, (new StringBuilder(String.valueOf(HisHer()))).append(" eyes go wide with anticipation as the man fucking ").append(himHer()).append(" abruptly seizes ").append(hisHer()).append(" hips, and then ").append(heShe()).append("'s crying out with every thrust into ").append(hisHer()).append(" ").toString());
                    if(vaginal.booleanValue())
                        w.append(t, "pussy.  ");
                    else
                        w.append(t, "ass.  ");
                }
            } else
            {
                if(fighting.booleanValue() && helpless.booleanValue() && obedience < 20)
                {
                    w.append(t, (new StringBuilder("One of the men immediately pulls out his cock and grabs ")).append(mainName).append(" by the hair, but ").append(mainName).append(" snaps ").append(hisHer()).append(" teeth shut, and the would-be attacker quickly rethinks his plan.  ").toString());
                    servicing = Boolean.valueOf(true);
                } else
                if(helpless.booleanValue())
                {
                    w.append(t, (new StringBuilder("One of the men grabs ")).append(mainName).append(" by the hair and lifts ").append(hisHer()).append(" face up so that it's level with the man's cock.  When ").append(mainName).append(" feels the shaft pressing against ").append(hisHer()).append(" lips, ").append(heShe()).append(" reflexively opens them and allows it inside.  ").toString());
                    oral = Boolean.valueOf(true);
                    penetrations++;
                    servicing = Boolean.valueOf(true);
                } else
                if(orgasmsGiven >= 1000 && (!fighting.booleanValue() || obedience >= 20))
                {
                    w.append(t, (new StringBuilder("Several of the men pull out their cocks, and ")).append(mainName).append(" obediently gets down on ").append(hisHer()).append(" knees and opens ").append(hisHer()).append(" mouth for the closest of the group, allowing him to slide it inside and down ").append(hisHer()).append(" throat.  ").toString());
                    oral = Boolean.valueOf(true);
                    penetrations++;
                    servicing = Boolean.valueOf(true);
                } else
                {
                    w.append(t, (new StringBuilder("Several of the men pull out their cocks, but ")).append(mainName).append(" backs away warily, taking a fighting stance.  None of the would-be attackers are brave enough to approach, but they do start to masturbate, unable to contain themselves as they leer at ").append(hisHer()).append(" body.  ").toString());
                }
                if(servicing.booleanValue())
                    if(currentTraining[3].booleanValue())
                    {
                        if(vaginal.booleanValue() && anal.booleanValue())
                            w.append(t, (new StringBuilder("Meanwhile, ")).append(mainName).append(" is briefly relieved from the stimulation down below as two other men remove the vibrators from ").append(hisHer()).append(" pussy and ass - but then ").append(heShe()).append("'s violently filled up again by a pair of cocks.  ").toString());
                        else
                        if(vaginal.booleanValue())
                            w.append(t, (new StringBuilder("Meanwhile, ")).append(mainName).append(" is briefly relieved from the stimulation down below as another man removes the vibrator from ").append(hisHer()).append(" pussy - only to shove his cock inside in its place.  ").toString());
                        else
                        if(anal.booleanValue())
                            w.append(t, (new StringBuilder("Meanwhile, ")).append(mainName).append(" is briefly relieved from the stimulation down below as another man removes the anal vibrator from ").append(hisHer()).append(" chastity belt - only to shove his cock inside in its place.  ").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s chastity belt prevents any of the other men from penetrating ").append(himHer()).append(", which means that they can only try to relieve their lust by stroking themselves with handfuls of ").append(hisHer()).append(" hair, grinding against ").append(hisHer()).append(" limbs and torso, and masturbating close to ").append(hisHer()).append(" face.  ").toString());
                    } else
                    if(timesHadSex > 0 || timesTortured > 0 && gender != Gender.MALE)
                    {
                        w.append(t, (new StringBuilder("Meanwhile, the other men jostle for position behind ")).append(mainName).append(", with one of them finally pushing his way to the front of the pack and lining his cock up against ").append(mainName).append("'s ").toString());
                        if(gender != Gender.MALE || timesHadSex == 0)
                        {
                            vaginal = Boolean.valueOf(true);
                            w.append(t, (new StringBuilder("lower lips.  He forces his way inside, disregarding ")).append(mainName).append("'s pleasure, ramming ").append(hisHer()).append(" deepest parts with wild abandon.  ").toString());
                            penetrations++;
                            if(timesTortured > 0)
                            {
                                anal = Boolean.valueOf(true);
                                w.append(t, (new StringBuilder("Noticing the free hole, yet another man gives the same treatment to ")).append(hisHer()).append(" ass.  ").toString());
                                penetrations++;
                            }
                        } else
                        {
                            anal = Boolean.valueOf(true);
                            w.append(t, (new StringBuilder("tightly-puckered asshole.  He forces his way inside, quickly stretching the orifice open and ramming deep into ")).append(mainName).append("'s bowels with wild abandon.  ").toString());
                            penetrations++;
                        }
                    } else
                    {
                        w.append(t, (new StringBuilder("The other men grow bolder and bolder, stroking themselves with handfuls of ")).append(hisHer()).append(" hair, grinding against ").append(hisHer()).append(" limbs and torso, and masturbating close to ").append(hisHer()).append(" face.  ").toString());
                    }
            }
            if(vaginal.booleanValue() || anal.booleanValue())
            {
                if(deviancy > 33)
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder("The pleasure soon overwhelms ")).append(mainName).append(", leaving ").append(himHer()).append(" moaning and bucking ").append(hisHer()).append(" hips like an animal in heat.").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s well-trained body welcomes the pleasure that grows with every impact deep inside ").append(himHer()).append(", and soon enough ").append(heShe()).append("'s cooperating wholeheartedly.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" surrenders ").append(himHer()).append("self to the pleasure, pursuing the sweet release that ").append(heShe()).append(" feels growing closer and closer with every thrust inside.").toString());
                } else
                if(consenting.booleanValue())
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" still isn't entirely comfortable with the feeling of getting fucked, but ").append(hisHer()).append(" discomfort slowly melts away as the pleasure builds.").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder("Some part of ")).append(mainName).append(" is still mortified that ").append(heShe()).append("'s enjoying a gangbang this much, but that doesn't stop ").append(himHer()).append(" from bucking ").append(hisHer()).append(" hips in time with each thrust.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" tries as best ").append(heShe()).append(" can to reciprocate as ").append(heShe()).append("'s fucked, but ").append(hisHer()).append(" movements gradually become jerky and erratic as ").append(heShe()).append(" approaches ").append(hisHer()).append(" peak.").toString());
                } else
                if(innocence > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is scared of the pleasure growing inside ").append(himHer()).append(", but ").append(hisHer()).append(" thrashing efforts to escape only succeed at impaling ").append(himHer()).append(" even further.").toString());
                else
                if(innocence > 33)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is disgusted to recognize the signs that ").append(hisHer()).append(" body is beginning to welcome being raped.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" closes ").append(hisHer()).append(" eyes and tries to turn ").append(hisHer()).append(" mind away from the feeling of being so roughly penetrated, but no matter what ").append(heShe()).append(" does, the pleasure continues to grow, causing ").append(hisHer()).append(" face to flush and ").append(hisHer()).append(" breathing to grow ragged.").toString());
                w.append(t, "\n\n");
                if(timesOrgasmed == 0)
                {
                    if(vaginal.booleanValue() && anal.booleanValue())
                        w.append(t, (new StringBuilder("The feeling of the two cocks inside ")).append(himHer()).append(" beginning to spurt their cum into ").append(himHer()).append(" ").toString());
                    else
                        w.append(t, (new StringBuilder("The feeling of the cock inside ")).append(himHer()).append(" beginning to spurt its cum into ").append(hisHer()).append(" deepest place ").toString());
                    w.append(t, (new StringBuilder("causes ")).append(himHer()).append(" to shudder briefly with a small peak of pleasure - not quite an orgasm, but close enough that ").append(heShe()).append(" can't help but wonder what kinds of pleasures ").append(heShe()).append("'s been missing.  ").toString());
                } else
                if(dignity > 66)
                {
                    w.append(t, (new StringBuilder("When ")).append(mainName).append(" cums, ").append(heShe()).append(" instinctively tries to hide it, but ").toString());
                    if(vaginal.booleanValue() && anal.booleanValue())
                        w.append(t, (new StringBuilder(String.valueOf(hisHer()))).append(" partners can feel the muscles in ").append(hisHer()).append(" pussy and anus squeeze down on them, and they laugh and comment on how nice and tight ").append(heShe()).append(" is as they cum inside.  ").toString());
                    else
                    if(vaginal.booleanValue())
                        w.append(t, (new StringBuilder(String.valueOf(hisHer()))).append(" pussy still squeezes down on the penetrating cock, pushing it over the edge so that it cums deep into ").append(hisHer()).append(" womb.  ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(hisHer()))).append(" ass still squeezes down on the penetrating cock, pushing it over the edge so that it cums deep into ").append(hisHer()).append(" bowels.  ").toString());
                    timesOrgasmed++;
                } else
                if(dignity > 33)
                {
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" can't hide ").append(hisHer()).append(" orgasm, shuddering and spasming as ").append(heShe()).append("'s filled and covered with another layer of cum.  ").toString());
                    timesOrgasmed++;
                } else
                {
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" thrashes wildly as ").append(heShe()).append(" cums, ").append(hisHer()).append(" orgasmic spasms ").toString());
                    if(vaginal.booleanValue() && anal.booleanValue())
                        w.append(t, (new StringBuilder("milking the cocks inside ")).append(himHer()).append(" until they spurt out their loads into ").append(hisHer()).append(" pussy and asshole both.  ").toString());
                    else
                    if(vaginal.booleanValue())
                        w.append(t, (new StringBuilder("milking ")).append(hisHer()).append(" partner's cock of every drop of semen until ").append(hisHer()).append(" womb is completely full.  ").toString());
                    else
                        w.append(t, (new StringBuilder("milking ")).append(hisHer()).append(" partner's cock of every drop of semen until ").append(hisHer()).append(" ass has been completely filled.  ").toString());
                    timesOrgasmed++;
                }
                if(hostility < 20)
                {
                    if(morality > 66)
                    {
                        if(oral.booleanValue())
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" starts to ask for a moment's rest, ").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" tries to pull the cock out of ").append(hisHer()).append(" mouth for a moment to ask for a break, ").toString());
                        w.append(t, (new StringBuilder("but before ")).append(heShe()).append(" has a chance, ").toString());
                        if(vaginal.booleanValue() && anal.booleanValue())
                            w.append(t, "another pair of cocks has been thrusted ");
                        else
                            w.append(t, "another cock is thrust ");
                        w.append(t, (new StringBuilder("inside ")).append(himHer()).append(" with complete disregard for ").append(hisHer()).append(" comfort.").toString());
                    } else
                    {
                        if(vaginal.booleanValue() && anal.booleanValue())
                            w.append(t, "When they pull ");
                        else
                            w.append(t, "When the man pulls ");
                        w.append(t, (new StringBuilder("out, ")).append(mainName).append(" is ready to heave a sigh of relief, but ").append(heShe()).append("'s horrified to be abruptly penetrated again, ").append(hisHer()).append(" aching body being given no chance to enjoy the afterglow.").toString());
                    }
                } else
                if(morality > 66)
                    w.append(t, (new StringBuilder("Exhausted from the climax, ")).append(mainName).append(" wants to take a break, but ").append(heShe()).append(" finds ").append(himHer()).append("self spreading ").append(hisHer()).append(" legs again, knowing that it's pointless to expect the crowd to care about ").append(hisHer()).append(" wishes.").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s body is especially sensitive in the afterglow, but ").append(heShe()).append(" knows that the crowd won't spare any thought to ").append(hisHer()).append(" own desires.  All ").append(heShe()).append(" can do is prepare ").append(himHer()).append("self to be taken again.").toString());
                else
                    w.append(t, (new StringBuilder("When the next man steps forward to fuck ")).append(himHer()).append(", ").append(mainName).append(" plants ").append(hisHer()).append(" foot against ").append(hisHer()).append(" prospective partner's chest, holding ").append(himHer()).append(" at bay for a moment while ").append(heShe()).append(" tries to recover.").toString());
            } else
            if(stimulated.booleanValue())
            {
                String target = "penis";
                if(gender == Gender.FEMALE)
                    target = "clit";
                if(oral.booleanValue())
                {
                    if(currentTraining[3].booleanValue())
                        if(deviancy > 33)
                        {
                            if(innocence > 66)
                                w.append(t, (new StringBuilder("When the vibrators attached to ")).append(mainName).append("'s nipples and ").append(target).append(" abruptly turn up to a higher intensity, ").append(hisHer()).append(" squeak of surprise is muffled by the cock in ").append(hisHer()).append(" mouth.").toString());
                            else
                            if(innocence > 33)
                                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" loses ").append(himHer()).append("self in the pleasure of sucking a cock, giving ").append(himHer()).append("self over to the sensation of the vibrators against ").append(hisHer()).append(" nipples and ").append(target).append(".").toString());
                            else
                                w.append(t, (new StringBuilder("The vibrators against ")).append(hisHer()).append(" nipples and ").append(target).append(" alone aren't enough to push ").append(mainName).append(" over the edge, but the fact that ").append(heShe()).append("'s sucking a cock turns ").append(himHer()).append(" on more and more.").toString());
                        } else
                        if(consenting.booleanValue())
                        {
                            if(innocence > 66)
                                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" still isn't entirely comfortable with the feeling of a cock in ").append(hisHer()).append(" mouth, but ").append(heShe()).append(" can't deny that the vibrators against ").append(hisHer()).append(" nipples and ").append(target).append(" feel really, really good.").toString());
                            else
                            if(innocence > 33)
                                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is reluctant at first, but with the vibrators against ").append(hisHer()).append(" nipples and ").append(target).append(" spurring ").append(himHer()).append(" on, ").append(heShe()).append(" starts to take the cock deeper and deeper into ").append(hisHer()).append(" throat.").toString());
                            else
                                w.append(t, (new StringBuilder("At first, ")).append(mainName).append(" is entirely focused on servicing the cock as quickly as possible in order to get this over with, but the relentless pulsing of the vibrators against ").append(hisHer()).append(" nipples and ").append(target).append(" soon cause ").append(hisHer()).append(" movements to become more eager and passionate.").toString());
                        } else
                        if(innocence > 66)
                            w.append(t, (new StringBuilder("The unwashed cock tastes awful, and ")).append(mainName).append(" just wants to get it out of ").append(hisHer()).append(" mouth as soon as possible, but the vibrators against ").append(hisHer()).append(" nipples and ").append(target).append(" are making it harder and harder to focus.").toString());
                        else
                        if(innocence > 33)
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" stubbornly refuses to actively service the cock in ").append(hisHer()).append(" mouth, but it makes little difference, as the breathy moans and involuntary shudders elicited by the vibrators against ").append(hisHer()).append(" nipples and ").append(target).append(" cause ").append(himHer()).append(" to unwillingly stimulate the shaft.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" attempts to retreat into ").append(hisHer()).append(" own mind in order to avoid thinking about what ").append(heShe()).append("'s being forced to do, but the pleasure from the vibrators against ").append(hisHer()).append(" nipples and ").append(target).append(" continues to build, and ").append(heShe()).append(" enjoys it on some level.").toString());
                } else
                if(currentTraining[3].booleanValue())
                    if(innocence > 66)
                        w.append(t, (new StringBuilder("As if in response, the vibrators against ")).append(mainName).append("'s nipples and ").append(target).append(" begin to stimulate ").append(himHer()).append(" even more intensely, and ").append(hisHer()).append(" reflexive struggling to get them off just makes it even worse.").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder("Even as ")).append(heShe()).append(" stubbornly denies them the use of ").append(hisHer()).append(" mouth, the pleasure of the vibrators against ").append(hisHer()).append(" nipples and ").append(target).append(" continues to build, and soon ").append(heShe()).append("'s reduced to desperately clenching ").append(hisHer()).append(" teeth as ").append(heShe()).append(" feels the peak approach.").toString());
                    else
                        w.append(t, (new StringBuilder("When the stimulation from the vibrators against ")).append(hisHer()).append(" nipples and ").append(target).append(" grows too strong, ").append(mainName).append(" turns ").append(hisHer()).append(" face toward the floor before opening ").append(hisHer()).append(" mouth to moan, knowing that ").append(hisHer()).append(" attackers will be looking for any chance to force a gag onto ").append(himHer()).append(".").toString());
                w.append(t, "\n\n");
                if(timesOrgasmed == 0)
                    w.append(t, (new StringBuilder("Even for ")).append(mainName).append("'s inexperienced body, the stimulation doesn't take long to produce a reaction.  ").append(HeShe()).append(" shudders and moans softly, then blinks and looks around in confusion, uncertain about what ").append(heShe()).append(" just experienced.  It was too small to be called a true orgasm, but the memory of the pleasure will stay with ").append(himHer()).append(".  ").toString());
                else
                if(dignity > 66)
                {
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" tries to hold back ").append(hisHer()).append(" orgasm until ").toString());
                    if(oral.booleanValue())
                        w.append(t, (new StringBuilder("the man fucking ")).append(hisHer()).append(" throat finishes, ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(hisHer()))).append(" would-be attackers aren't looking, ").toString());
                    w.append(t, (new StringBuilder("but that just makes it all the more spectacular when it finally overwhelms ")).append(himHer()).append(".  ").append(HeShe()).append(" coughs and sputters as ").toString());
                    if(gender == Gender.FEMALE)
                        w.append(t, (new StringBuilder(String.valueOf(hisHer()))).append(" juices squirt down ").append(hisHer()).append(" thighs").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(hisHer()))).append(" cum paints the ground").toString());
                    w.append(t, ", prompting raucous laughter from the crowd.  ");
                    timesOrgasmed++;
                } else
                if(dignity > 33)
                {
                    w.append(t, (new StringBuilder("When the inevitable orgasm finally comes, ")).append(mainName).append(" tries to take it in stride, ").append(hisHer()).append(" thighs squeezing together as ").toString());
                    if(gender == Gender.FEMALE)
                        w.append(t, (new StringBuilder(String.valueOf(hisHer()))).append(" juices drip down between them.  ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(hisHer()))).append(" cum spurts onto the ground.  ").toString());
                    timesOrgasmed++;
                } else
                {
                    w.append(t, (new StringBuilder("Finally, ")).append(heShe()).append(" cums hard, ").toString());
                    if(oral.booleanValue())
                        w.append(t, (new StringBuilder("groaning loudly around ")).append(hisHer()).append(" partner's cock").toString());
                    else
                        w.append(t, (new StringBuilder("clenching ")).append(hisHer()).append(" teeth and groaning out loud").toString());
                    if(gender == Gender.FEMALE)
                        w.append(t, (new StringBuilder(" as ")).append(hisHer()).append(" juices stream down ").append(hisHer()).append(" thighs.  ").toString());
                    timesOrgasmed++;
                }
                if(hostility < 20)
                {
                    if(morality > 66)
                    {
                        w.append(t, (new StringBuilder(String.valueOf(HisHer()))).append(" head is swimming in the afterglow, and a part of ").append(himHer()).append(" assumes that ").append(heShe()).append("'ll get to rest, but ").append(heShe()).append("'s shocked when ").append(heShe()).append(" feels ").toString());
                        if(oral.booleanValue())
                            w.append(t, (new StringBuilder("cum suddenly spurting down ")).append(hisHer()).append(" throat.  It pulls out, only to be replaced immediately by another man who facefucks ").append(himHer()).append(" even harder.").toString());
                        else
                            w.append(t, (new StringBuilder("several men taking advantage of ")).append(hisHer()).append(" distraction to try to pry ").append(hisHer()).append(" mouth open, while others seize ").append(hisHer()).append(" hands and try to make ").append(himHer()).append(" stroke their cocks.").toString());
                    } else
                    if(morality > 33)
                        w.append(t, (new StringBuilder("A wave of exhaustion hits ")).append(himHer()).append(", and ").append(heShe()).append(" desperately tries to signal for a break, but the lustful crowd ignores ").append(himHer()).append(", trying to force ").append(hisHer()).append(" hands around their cocks with complete disregard for ").append(hisHer()).append(" own desires.").toString());
                } else
                if(morality > 66)
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" knows now that the sight of ").append(himHer()).append(" orgasming will only make the rest of the crowd more eager to pursue their own, and ").append(heShe()).append("'s already warily looking between the lustful faces around ").append(himHer()).append(" to see who will be wanting to use ").append(himHer()).append(" next.").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" tries to fight through ").append(hisHer()).append(" growing exhaustion, knowing that the crowd won't let up.").toString());
                else
                    w.append(t, (new StringBuilder("With ")).append(hisHer()).append(" body satisfied and ").append(hisHer()).append(" head feeling more clear, ").append(heShe()).append(" tries to make ").append(hisHer()).append(" exit, but much to ").append(hisHer()).append(" annoyance, ").append(heShe()).append("'s boxed in on all sides by more people thrusting their cocks at ").append(himHer()).append(" and demanding service.").toString());
            } else
            if(oral.booleanValue())
            {
                if(hostility < 20)
                {
                    if(morality > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" endures having ").append(hisHer()).append(" throat violently raped until ").append(hisHer()).append(" partner finishes, then tries to use the brief period while ").append(hisHer()).append(" mouth is empty to ask for gentler treatment.  However, the next man cuts ").append(himHer()).append(" off by thrusting inside.").toString());
                    else
                    if(morality > 33)
                        w.append(t, (new StringBuilder("The pain of having ")).append(hisHer()).append(" throat fucked causes ").append(mainName).append(" to wince and choke.  ").append(HeShe()).append(" desperately tries to signal for ").append(hisHer()).append(" partner to slow down, but ").append(hisHer()).append(" frantic movements accomplish nothing more than drawing a few chuckles from the watching crowd.").toString());
                } else
                if(morality > 66)
                    w.append(t, (new StringBuilder("With the cock pushing down ")).append(hisHer()).append(" throat, ").append(mainName).append(" is starting to have trouble breathing.  ").append(HeShe()).append(" frantically tries to bob ").append(hisHer()).append(" head and work ").append(hisHer()).append(" tongue along the shaft, desperate for the man to finish so that ").append(heShe()).append(" can have a chance to come up for air.").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder("Having a cock repeatedly thrust down ")).append(hisHer()).append(" throat doesn't feel good at all, but ").append(mainName).append(" just endures it, ").append(hisHer()).append(" eyes glazed over.  ").append(HeShe()).append(" knows that there's no point in protesting.").toString());
                else
                    w.append(t, (new StringBuilder("The lust amplification causes the man thrusting down ")).append(mainName).append("'s throat to be exceptionally rough and merciless, prompting ").append(mainName).append(" to glare up at him with resentful fury.  ").append(mainName).append(" briefly considers biting down, but ").append(heShe()).append(" knows that it would only make things much worse for ").append(himHer()).append(".").toString());
            } else
            {
                w.append(t, (new StringBuilder("Tension hangs in the air as the men grow more and more irritated at ")).append(hisHer()).append(" refusal to service them.\n\n").toString());
                if(currentTraining[4].booleanValue())
                {
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" tries to put on an intimidating presence and scare the crowd away, but ").append(hisHer()).append(" efforts are sabotaged from the start by ").append(hisHer()).append(" ridiculous appearance.  ").toString());
                    if(dignity > 66)
                        w.append(t, (new StringBuilder(String.valueOf(HisHer()))).append(" cheeks begin to turn red as ").append(heShe()).append(" realizes how ridiculous ").append(heShe()).append(" must look, and the men catch on, beginning to catcall and taunt ").append(himHer()).append(" just to see ").append(hisHer()).append(" reactions.  ").toString());
                    else
                    if(dignity > 33)
                        w.append(t, (new StringBuilder("The more ")).append(heShe()).append(" tries to scare them off, the more they laugh and crowd around ").append(himHer()).append(" even more.  ").toString());
                    else
                        w.append(t, (new StringBuilder("Even though ")).append(heShe()).append(" normally wouldn't care how people see ").append(himHer()).append(", the fact that it's now putting ").append(himHer()).append(" in danger causes ").append(himHer()).append(" to clench ").append(hisHer()).append(" fists in annoyance.  ").toString());
                } else
                if(dignity > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" tries to intimidate them into leaving ").append(himHer()).append(" alone, shouting threats and reminding them all of ").append(hisHer()).append(" mighty reputation, but they no longer pay any heed to ").append(himHer()).append(", chatting amongst themselves as they make plans to forcibly tie ").append(himHer()).append(" up.  ").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" tries to convince them to go away, alternating between promises and threats, but no one in the crowd is afraid of ").append(himHer()).append(" anymore.  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" soon lapses into sullen silence, glaring at the assembled crowd as it grows larger and larger.  They know that ").append(heShe()).append(" can't truly stop them if they all decide to take ").append(himHer()).append(" together, and ").append(heShe()).append(" knows that ").append(heShe()).append(" knows.  ").toString());
                if(hostility < 20)
                {
                    if(morality > 66)
                        w.append(t, (new StringBuilder("In the end, all ")).append(heShe()).append(" can do is appeal to their sense of right and wrong, and ").append(hisHer()).append(" heart breaks a little when ").append(heShe()).append(" sees how ineffective it is.").toString());
                    else
                    if(morality > 33)
                        w.append(t, (new StringBuilder("Although ")).append(heShe()).append(" tries to tell ").append(himHer()).append("self that these people are only acting like this because of the circumstances, ").append(heShe()).append(" can't stop ").append(himHer()).append("self from feeling some anger at them.").toString());
                } else
                if(morality > 66)
                    w.append(t, (new StringBuilder("In the end, all ")).append(heShe()).append(" can do is speak out loud directly to the Demon Lord, asking you to end this here before things get any worse.").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder("Resentment boils in ")).append(hisHer()).append(" eyes.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" tries to content ").append(himHer()).append("self with the knowledge that if they try to go any further, ").append(heShe()).append("'ll probably be able to hurt a few of them for it.").toString());
            }
            if(vaginal.booleanValue())
            {
                int added = 3 + (int)(Math.random() * 3D);
                timesHadSex += added;
                orgasmsGiven += added;
            }
            if(anal.booleanValue())
            {
                int added = 3 + (int)(Math.random() * 3D);
                orgasmsGiven += added;
                if(gender == Gender.MALE)
                    timesHadSex += added;
                else
                    timesTortured += added;
            }
            if(oral.booleanValue())
                orgasmsGiven += 3 + (int)(Math.random() * 3D);
        } else
        if(nextTraining == 1)
        {
            if(helpless.booleanValue() || consenting.booleanValue() && orgasmsGiven >= 1000)
            {
                if(!consenting.booleanValue())
                {
                    if(confidence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s struggles grow increasingly frantic and disjointed as ").append(hisHer()).append(" mind buckles under the weight of dozens upon dozens of other people's thoughts and perceptions.  ").toString());
                    else
                    if(confidence > 33)
                        w.append(t, (new StringBuilder("The flood of thoughts and sensations that aren't ")).append(hisHer()).append(" own paralyzes ").append(mainName).append("'s mind, and ").append(hisHer()).append(" struggles to escape abruptly come to a complete halt.  ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s struggles, already feeble in the first place, come to a complete stop when foreign thoughts and feelings begin forcing their way into ").append(hisHer()).append(" mind.  ").toString());
                } else
                if(tied.booleanValue())
                {
                    if(confidence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" reflexively strains against ").append(hisHer()).append(" bonds as ").append(hisHer()).append(" mind struggles to accommodate the sudden flood of new information from the new psychic linkages.  ").toString());
                    else
                    if(confidence > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is already tied up and helpless, but ").append(heShe()).append(" loses the ability to even think about resisting when the abrupt surge of sensations from other people makes it too hard for ").append(himHer()).append(" to even keep track of which body belongs to ").append(himHer()).append(".  ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s mind puts up little resistance against the other minds suddenly intruding upon it, leaving ").append(himHer()).append(" defenseless against the psychic flood.  ").toString());
                } else
                if(confidence > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" spasms wildly as ").append(hisHer()).append(" mind reflexively resists the flood of information from other brains bearing down upon it, but ").append(heShe()).append(" soon goes completely limp.  ").toString());
                else
                if(confidence > 33)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" immediately seizes up, unable to process the information rushing through ").append(hisHer()).append(" mind as ").append(heShe()).append("'s connected to dozens of other people's thoughts and feelings.  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" whimpers and clutches ").append(hisHer()).append(" head, shaking it back and forth as intrusive thoughts and sensations seem to tear ").append(himHer()).append(" out of ").append(hisHer()).append(" own body.  ").toString());
                if(servicing.booleanValue())
                    w.append(t, (new StringBuilder("Unconcerned with whatever ")).append(heShe()).append("'s going through, the crowd of people surrounding ").append(himHer()).append(" continues to enjoy ").append(hisHer()).append(" body.").toString());
                else
                if(inPublic.booleanValue())
                    w.append(t, (new StringBuilder("Laughter and jeers erupt from the surrounding crowd, mingled with murmured discussion as they watch what ")).append(heShe()).append("'s going through with curiosity.").toString());
                else
                    w.append(t, (new StringBuilder("Even though there's no one nearby to take advantage of ")).append(hisHer()).append(" even greater vulnerability, the experiences ").append(heShe()).append("'s drawing from the people ").append(heShe()).append("'s linked with are intense enough on their own.").toString());
            } else
            {
                if(confidence > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" grits ").append(hisHer()).append(" teeth as ").append(hisHer()).append(" mind reflexively resists the flood of information from other brains bearing down upon it, but ").append(heShe()).append(" soon collapses onto ").append(hisHer()).append(" hands and knees.  ").toString());
                else
                if(confidence > 33)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" immediately falls to ").append(hisHer()).append(" knees, unable to process the information rushing through ").append(hisHer()).append(" mind as ").append(heShe()).append("'s connected to dozens of other people's thoughts and feelings.  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" whimpers and clutches ").append(hisHer()).append(" head, shaking it back and forth as intrusive thoughts and sensations seem to tear ").append(himHer()).append(" out of ").append(hisHer()).append(" own body.  ").append(HeShe()).append(" curls up on the ground, helpless.  ").toString());
                if(servicing.booleanValue())
                    w.append(t, (new StringBuilder("The crowd is disappointed that ")).append(heShe()).append("'s stopped actively servicing them, but they content themselves by making use of ").append(hisHer()).append(" limp, unresisting body.").toString());
                else
                if(currentTraining[0].booleanValue())
                {
                    servicing = Boolean.valueOf(true);
                    w.append(t, (new StringBuilder("The crowd recognizes that this is a rare opportunity, surging forward to take advantage of ")).append(hisHer()).append(" sudden helplessness.  ").toString());
                    if(timesHadSex > 0)
                    {
                        if(gender == Gender.MALE)
                            anal = Boolean.valueOf(true);
                        else
                            vaginal = Boolean.valueOf(true);
                        if(gender == Gender.FEMALE && timesTortured > 0)
                            anal = Boolean.valueOf(true);
                    } else
                    if(gender == Gender.FEMALE && timesTortured > 0)
                        anal = Boolean.valueOf(true);
                    if(!fighting.booleanValue() || obedience >= 20)
                        oral = Boolean.valueOf(true);
                    if(anal.booleanValue())
                    {
                        w.append(t, (new StringBuilder("One attacker grabs ")).append(himHer()).append(" from behind, lifting ").append(himHer()).append(" up with ").append(hisHer()).append(" legs in the air.  Then, he lowers ").append(mainName).append(" down, ").toString());
                        if(hostility < 20)
                        {
                            if(morality > 66)
                            {
                                w.append(t, (new StringBuilder("and ")).append(mainName).append("'s pleas for ").append(hisHer()).append(" attackers to stop this turn into a strangled cry as the man's cock slams into ").append(hisHer()).append(" ass.  ").toString());
                                if(vaginal.booleanValue())
                                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" tries to get them to at least spare ").append(hisHer()).append(" pussy, only to be cut off once again as a second attacker rams his way in.  ").toString());
                            } else
                            if(morality > 33)
                            {
                                w.append(t, (new StringBuilder("slowly, allowing the rest of the crowd plenty of time to enjoy ")).append(mainName).append("'s expression of stunned disbelief as ").append(hisHer()).append(" anus is impaled on the man's cock.  ").toString());
                                if(vaginal.booleanValue())
                                    w.append(t, (new StringBuilder("By the time the second attacker penetrates ")).append(hisHer()).append(" pussy, ").append(hisHer()).append(" eyes are downcast.  ").toString());
                            }
                        } else
                        if(morality > 66)
                        {
                            w.append(t, (new StringBuilder("enjoying the look of bitter betrayal in ")).append(hisHer()).append(" eyes as ").append(hisHer()).append(" ass inevitably sinks onto ").append(hisHer()).append(" attacker's cock.  ").toString());
                            if(vaginal.booleanValue())
                                w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" shudders with revulsion as a second attacker slides his cock into ").append(hisHer()).append(" pussy.  ").toString());
                        } else
                        if(morality > 33)
                        {
                            w.append(t, (new StringBuilder("causing ")).append(mainName).append(" to wince with resignation as ").append(heShe()).append(" feels ").append(hisHer()).append(" anus spread open by the waiting cock below.  ").toString());
                            if(vaginal.booleanValue())
                                w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" doesn't even react at first to a second attacker ramming himself into ").append(hisHer()).append(" pussy.  ").toString());
                        } else
                        {
                            w.append(t, (new StringBuilder("and ")).append(mainName).append("'s strained glare turns into an expression of shock as ").append(hisHer()).append(" ass is forced open by ").append(hisHer()).append(" attacker's cock.  ").toString());
                            if(vaginal.booleanValue())
                                w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" kicks angrily, only for ").append(hisHer()).append(" legs to jerk and stiffen when a second attacker starts fucking ").append(hisHer()).append(" pussy.  ").toString());
                        }
                    } else
                    if(vaginal.booleanValue())
                    {
                        w.append(t, (new StringBuilder("One attacker lifts ")).append(himHer()).append(" up from behind, placing his cock against ").append(hisHer()).append(" pussy and then slowly lowering ").append(himHer()).append(" donwward.  ").toString());
                        if(hostility < 20)
                        {
                            if(morality > 66)
                                w.append(t, (new StringBuilder("At first, ")).append(mainName).append("'s pleas for ").append(hisHer()).append(" attackers to stop, but ").append(hisHer()).append(" voice breaks with a strangled cry as the man's cock slams its way inside.  ").toString());
                            else
                            if(morality > 33)
                                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" wears an expression of stunned disbelief as ").append(heShe()).append("'s impaled on the man's cock.  ").toString());
                        } else
                        if(morality > 66)
                            w.append(t, (new StringBuilder("A look of bitter betrayal simmers in ")).append(hisHer()).append(" eyes as ").append(heShe()).append(" inevitably sinks onto ").append(hisHer()).append(" attacker's cock.  ").toString());
                        else
                        if(morality > 33)
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" winces with resignation as ").append(heShe()).append(" feels ").append(hisHer()).append(" intimate folds forced open by the waiting cock below.  ").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s strained glare turns into an expression of shock as ").append(heShe()).append("'s forced open by ").append(hisHer()).append(" attacker's cock.  ").toString());
                    } else
                    if(currentTraining[3].booleanValue())
                    {
                        if(hostility < 20)
                        {
                            if(morality > 66)
                                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" tries to explain that the chastity belt that's been forced upon ").append(himHer()).append(" means that they're wasting their time attacking ").append(himHer()).append(", but ").append(heShe()).append("'s surprised when they start taking out their rage on ").append(hisHer()).append(" body.  ").toString());
                            else
                            if(morality > 33)
                                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" had expected that the chastity belt you forced on ").append(himHer()).append(" would protect ").append(himHer()).append(" from the worst of this kind of abuse, but it just makes the crowd angrier.  ").toString());
                        } else
                        if(morality > 66)
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" holds ").append(hisHer()).append(" head high in defiance, knowing that the chastity belt you forced on ").append(himHer()).append(" will protect ").append(himHer()).append(" from being raped, but ").append(heShe()).append(" knows that the crowd won't just give up and leave ").append(himHer()).append(" alone.  ").toString());
                        else
                        if(morality > 33)
                            w.append(t, (new StringBuilder("Even though ")).append(heShe()).append("'s protected by a chastity belt, ").append(mainName).append(" knows that the crowd will find a way to hurt ").append(himHer()).append(".  ").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" manages a cheeky smirk as ").append(heShe()).append(" taunts them about being foiled by the chastity belt you put on ").append(himHer()).append(", but they're quick to punish ").append(himHer()).append(" for ").append(hisHer()).append(" insolence.  ").toString());
                    } else
                    if(hostility < 20)
                    {
                        if(morality > 66)
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" tries to keep a pure heart and a clear head, trusting ").append(hisHer()).append(" Sexual Barrier to protect ").append(himHer()).append(", but ").append(heShe()).append("'s horrified by how determined the crowd is to abuse ").append(himHer()).append(".  ").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s Sexual Barrier prevents ").append(himHer()).append(" from being raped, but it begins to waver as ").append(heShe()).append(" sees how determined the crowd is to hurt ").append(himHer()).append(", failing along with ").append(hisHer()).append(" faith in humanity.  ").toString());
                    } else
                    {
                        String virginity = "virginity";
                        if(gender == Gender.MALE)
                            virginity = "anal virginity";
                        if(morality > 66)
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is forced to curl up into a ball as ").append(heShe()).append(" desperately protects ").append(hisHer()).append(" ").append(virginity).append(" - the last thing connecting ").append(himHer()).append(" to the morals ").append(heShe()).append(" once held.  ").toString());
                        else
                        if(morality > 33)
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s Sexual Barrier quickly dissolves in the heat of ").append(hisHer()).append(" hatred for ").append(hisHer()).append(" attackers, and the only thing protecting ").append(hisHer()).append(" ").append(virginity).append(" is the last bit of desperate physical resistance ").append(heShe()).append(" can muster.  ").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" tries to fight to protect ").append(hisHer()).append(" ").append(virginity).append(", but the best ").append(heShe()).append(" can do in ").append(hisHer()).append(" current state is to clench ").append(hisHer()).append(" hands tightly between ").append(hisHer()).append(" thighs.  ").toString());
                    }
                    if(oral.booleanValue())
                    {
                        if(w.tickle().booleanValue())
                            w.append(t, (new StringBuilder("Tickling hands emerge from all directions, but when ")).append(heShe()).append(" opens ").append(hisHer()).append(" mouth to laugh, a cock is immediately shoved inside.").toString());
                        else
                            w.append(t, (new StringBuilder("Rough hands emerge from all directions to twist ")).append(hisHer()).append(" limbs and pummel ").append(hisHer()).append(" body.  When ").append(heShe()).append(" opens ").append(hisHer()).append(" mouth to cry out in pain, a cock is immediately shoved inside, muffling ").append(hisHer()).append(" voice.").toString());
                    } else
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder("Tickling hands emerge from all directions, and it's all ")).append(heShe()).append(" can do to suppress ").append(hisHer()).append(" laughter through gritted teeth, unwilling to allow ").append(hisHer()).append(" attackers to rape ").append(hisHer()).append(" mouth.").toString());
                    else
                        w.append(t, (new StringBuilder("Rough hands emerge from all directions to twist ")).append(hisHer()).append(" limbs and pummel ").append(hisHer()).append(" body.  When ").append(heShe()).append(" opens ").append(hisHer()).append(" mouth to cry out in pain, one of ").append(hisHer()).append(" attackers almost manages to force his cock inside, but ").append(mainName).append("'s teeth snap shut quickly enough to make him rethink his priorities.").toString());
                } else
                if(inPublic.booleanValue())
                    w.append(t, (new StringBuilder("There's a smattering of laughter and conversation as the spectators enjoy seeing ")).append(himHer()).append(" subjected to this newest torment.").toString());
                else
                    w.append(t, (new StringBuilder("Even though there's no one nearby to take advantage of ")).append(hisHer()).append(" vulnerability, the experiences ").append(heShe()).append("'s drawing from the people ").append(heShe()).append("'s linked with are intense enough on their own.").toString());
            }
            w.append(t, "\n\n");
            if((vaginal.booleanValue() || anal.booleanValue() || oral.booleanValue()) && servicing.booleanValue())
            {
                if(obedience < 20)
                {
                    if(confidence > 66)
                        w.append(t, (new StringBuilder("Looking down at ")).append(himHer()).append("self from the perspective of each man who fucks ").append(himHer()).append(", ").append(mainName).append(" is horrified and disturbed at how pathetic ").append(heShe()).append(" looks.  ").toString());
                    else
                    if(confidence > 33)
                        w.append(t, (new StringBuilder("As ")).append(heShe()).append(" shares the senses of the ones fucking ").append(himHer()).append(" in turn, ").append(mainName).append(" can't help but notice ").append(hisHer()).append(" own small and fragile appearance.  ").toString());
                } else
                if(confidence > 66)
                    w.append(t, (new StringBuilder("As ")).append(heShe()).append(" focuses on one of the people fucking ").append(himHer()).append(", ").append(mainName).append(" can feel how feeble ").append(hisHer()).append(" reflexive struggles are, and ").append(heShe()).append(" realizes once again that ").append(heShe()).append(" was never as strong as ").append(heShe()).append(" thought ").append(heShe()).append(" was.  ").toString());
                else
                if(confidence > 33)
                    w.append(t, (new StringBuilder("By this point, ")).append(mainName).append(" has already acknowledged ").append(hisHer()).append(" own weakness, but feeling what it's like to fuck ").append(hisHer()).append(" helpless body from the shared senses of the crowd drives the point home even further.  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is content to lose ").append(himHer()).append("self in the senses of the crowd caught up in the gangbang, taking the opportunity to dissocate ").append(himHer()).append("self from the weak, useless body that's never been able to put up any resistance to those who would abuse ").append(himHer()).append(".  ").toString());
                if(deviancy > 33)
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" quickly loses track of where ").append(hisHer()).append(" senses end and the others' begin, simply enjoying each and every orgasm experienced by anyone in the gangbang.").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" cums over and over again, overwhelmed by the amplified arousal streaming into ").append(hisHer()).append(" brain from each and every member of the crowd.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" finds ").append(himHer()).append("self mentally urging one of the men to fuck ").append(himHer()).append(" harder and harder, and the sympathetic orgasm ").append(heShe()).append(" feels as the man cums is almost strong enough to make ").append(himHer()).append(" pass out.").toString());
                    timesOrgasmed += 5 + (int)(Math.random() * 5D);
                } else
                {
                    if(timesOrgasmed == 0)
                        w.append(t, (new StringBuilder("Even though ")).append(heShe()).append("'s never orgasmed before, ").append(heShe()).append(" still gets to feel the sensation vicariously several times over, and it's enough to leave ").append(himHer()).append(" wanting more.").toString());
                    else
                    if(innocence > 66)
                        w.append(t, (new StringBuilder("The psychically-transmitted pleasure felt by the crowd is more disorienting than arousing, but ")).append(heShe()).append("'s still dumbfounded by how much everyone seems to be enjoying ").append(hisHer()).append(" body.").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder("With the stimulation to ")).append(hisHer()).append(" own body amplified by the arousal felt by all those around ").append(himHer()).append(", ").append(mainName).append(" is forced to cum again and again.").toString());
                    else
                        w.append(t, (new StringBuilder("At first, ")).append(mainName).append("'s disgust prevents ").append(hisHer()).append(" body from showing any signs of arousal, but the pleasure felt by all those ").append(heShe()).append("'s psychically connected to continues to seep into ").append(hisHer()).append(" mind.  ").append(HeShe()).append(" can't hold back the tide, and after the first time ").append(heShe()).append(" cums, ").append(heShe()).append(" starts to feel genuine arousal that makes it even easier to cum again and again.").toString());
                    if(timesOrgasmed > 0)
                        timesOrgasmed += 5 + (int)(Math.random() * 5D);
                }
            } else
            if(inPublic.booleanValue())
            {
                if(obedience < 20)
                {
                    if(confidence > 66)
                        w.append(t, (new StringBuilder("Looking down at ")).append(himHer()).append("self from the perspective of the crowd, ").append(mainName).append(" is horrified and disturbed at how pathetic ").append(heShe()).append(" looks.  ").toString());
                    else
                    if(confidence > 33)
                        w.append(t, (new StringBuilder("As ")).append(heShe()).append(" shares the senses of the crowd, ").append(mainName).append(" can't help but notice ").append(hisHer()).append(" own small and fragile appearance.  ").toString());
                } else
                if(confidence > 66)
                    w.append(t, (new StringBuilder("As looks down at ")).append(himHer()).append("self from other people's eyes, ").append(mainName).append(" can see how feeble ").append(hisHer()).append(" reflexive struggles are, and ").append(heShe()).append(" realizes once again that ").append(heShe()).append(" was never as strong as ").append(heShe()).append(" thought ").append(heShe()).append(" was.  ").toString());
                else
                if(confidence > 33)
                    w.append(t, (new StringBuilder("By this point, ")).append(mainName).append(" has already acknowledged ").append(hisHer()).append(" own weakness, but seeing how helpless ").append(heShe()).append(" is from an outside perspective drives the point home even further.  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is content to lose ").append(himHer()).append("self in the senses of the crowd watching ").append(hisHer()).append(" torment, taking the opportunity to dissocate ").append(himHer()).append("self from the weak, useless body that's never been able to put up any resistance to those who would abuse ").append(himHer()).append(".  ").toString());
                if(currentTraining[4].booleanValue())
                {
                    if(dignity > 66)
                        w.append(t, (new StringBuilder("As embarrassing as it was to be forced to wear ")).append(hisHer()).append(" current outfit, seeing it from the outside is even worse.").toString());
                    else
                    if(dignity > 33)
                        w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" was able to ignore ").append(hisHer()).append(" embarrassing outfit easily enough before, but now that ").append(heShe()).append(" sees ").append(himHer()).append("self from outside, it's far worse.").toString());
                    else
                        w.append(t, (new StringBuilder("Forced to hear the crowd's internal monologues about how ridiculous ")).append(heShe()).append(" looks in the outfit you forced upon ").append(himHer()).append(", ").append(mainName).append(" feels even more uncomfortable.").toString());
                } else
                if(dignity > 66)
                    w.append(t, (new StringBuilder("The sight is so shameful that ")).append(heShe()).append(" can't help but try to beg the crowd to look away, but the mental connection only goes in one direction, and ").append(hisHer()).append(" pleas go unheard.").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder("A chorus of derogatory thoughts directed at ")).append(himHer()).append(" fills ").append(hisHer()).append(" head, driving ").append(himHer()).append(" insane.").toString());
                else
                    w.append(t, (new StringBuilder("Under the effects of your power, ")).append(heShe()).append(" can't ignore what people think about ").append(himHer()).append(" anymore.").toString());
            } else
            {
                if(obedience < 20)
                {
                    if(confidence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" clenches ").append(hisHer()).append(" teeth as ").append(heShe()).append(" tries to cling to ").append(hisHer()).append(" sense of self, but ").append(heShe()).append(" feels ").append(hisHer()).append(" sanity eroding away.  ").toString());
                    else
                    if(confidence > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" cries out unwillingly, blindly thrashing as ").append(hisHer()).append(" mind loses its grasp on ").append(hisHer()).append(" body.  ").toString());
                } else
                if(confidence > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s once-strong mind is now completely porous and vulnerable to your manipulation, allowing itself to be stripped away from ").append(hisHer()).append(" body and spread thin across many others.  ").toString());
                else
                if(confidence > 33)
                    w.append(t, (new StringBuilder("As ")).append(mainName).append("'s body lies abandoned, ").append(hisHer()).append(" mind is yours to manipulate.  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s mind is unable to put up even a token resistance, and soon it's completely at your mercy, subject to being shown whatever experiences you see fit.  ").toString());
                if(hostility < 20)
                {
                    if(morality > 66)
                        w.append(t, (new StringBuilder(String.valueOf(HisHer()))).append(" horror at what ").append(heShe()).append(" sees happening across your domain - violence, debauchery, and sin - causes ").append(himHer()).append(" to recoil, and ").append(heShe()).append(" gradually starts to return to ").append(himHer()).append("self.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append("'s forced to experience the worst of what's happening in neighboring buildings, seeing through the eyes of rapists and victims, thieves and murderers, and those who know what's happening but do nothing about it.  When ").append(heShe()).append(" returns to ").append(hisHer()).append(" own body, ").append(heShe()).append("'s gasping and covered with sweat.").toString());
                } else
                if(morality > 66)
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" doesn't run away from what you force ").append(himHer()).append(" to see, taking in every detail of the horrible abuses being constantly committed around ").append(himHer()).append(".  ").append(HeShe()).append(" uses the painful experience to burn away the last of ").append(hisHer()).append(" faith in humanity before ").append(heShe()).append(" starts to return to ").append(hisHer()).append(" body.").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" tries to resist, subconsciously afraid of leaving ").append(hisHer()).append(" body unattended, but even as ").append(heShe()).append(" squirms and struggles on the ground, the experiences ").append(heShe()).append("'s forced to live through ensure that ").append(heShe()).append(" doesn't forget the cruel things humans do to each other.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" isn't surprised by what ").append(heShe()).append(" sees, having known all along that ").append(heShe()).append("'s surrounded by rapists, sadists, and weaklings who allow themselves and others to be trampled upon.  But as ").append(heShe()).append(" starts to return to ").append(hisHer()).append(" body, ").append(hisHer()).append(" eyes burn with renewed contempt for the rest of humanity.").toString());
            }
            if(servicing.booleanValue())
            {
                if(vaginal.booleanValue())
                {
                    int added = 3 + (int)(Math.random() * 3D);
                    timesHadSex += added;
                    orgasmsGiven += added;
                }
                if(anal.booleanValue())
                {
                    int added = 3 + (int)(Math.random() * 3D);
                    orgasmsGiven += added;
                    if(gender == Gender.MALE)
                        timesHadSex += added;
                    else
                        timesTortured += added;
                }
                if(oral.booleanValue())
                    orgasmsGiven += 3 + (int)(Math.random() * 3D);
            }
        } else
        if(nextTraining == 2)
        {
            if(consenting.booleanValue())
            {
                if(oral.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" swallows a load of cum from the cock down ").append(hisHer()).append(" throat.  When its owner pulls out, ").append(heShe()).append(" takes the opportunity to speak up before the next man to be serviced can step forward.  ").toString());
                else
                if(inPublic.booleanValue() && helpless.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" lifts ").append(hisHer()).append(" head to look up at the gathered crowd, takes a deep breath, and then begins to speak.  ").toString());
                else
                if(helpless.booleanValue())
                    w.append(t, (new StringBuilder("One of your Thralls goes to fetch ")).append(mainName).append("'s helpless form, dragging ").append(himHer()).append(" out into public.  The Thrall calls everyone's attention to ").append(himHer()).append(", and then steps aside to let ").append(himHer()).append(" speak.  ").toString());
                else
                if(inPublic.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" gets down on ").append(hisHer()).append(" knees before the assembled crowd, bows ").append(hisHer()).append(" head, and then begins to speak.  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" heads out into public, where a crowd of people quickly takes notice of ").append(himHer()).append(".  ").append(HeShe()).append(" gets down on ").append(hisHer()).append(" knees, bows ").append(hisHer()).append(" head, and then begins to speak.  ").toString());
                if(disgrace < 20)
                {
                    if(dignity > 66)
                        w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" tells everyone that ").append(heShe()).append("'s a fraud, undeserving of ").append(hisHer()).append(" reputation, and that ").append(heShe()).append(" needs to be punished for deceiving everyone for so long.").toString());
                    else
                    if(dignity > 33)
                        w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" shouts that ").append(heShe()).append("'s actually not strong at all, that ").append(heShe()).append(" never should have been made one of the Chosen, and that ").append(heShe()).append("'s at the crowd's complete mercy.").toString());
                } else
                if(dignity > 66)
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" tells everyone at length about how ").append(heShe()).append(" used to be famous but is now just a playtoy for the Demon Lord.").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" reminds everyone that ").append(heShe()).append(" was a fool for ever trying to stand up to the mighty Demon Lord.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" speaks freely about how weak and frightened ").append(heShe()).append(" feels, making sure that everyone present knows that ").append(heShe()).append("'s at their mercy.").toString());
            } else
            {
                if(oral.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" coughs and chokes as the man fucking ").append(hisHer()).append(" throat shoots a load of cum inside, but in the brief period between that man pulling out and the next man stepping forward, ").append(heShe()).append(" starts shouting out loud, voicing ").append(hisHer()).append(" defiance.  ").toString());
                else
                if(inPublic.booleanValue())
                    w.append(t, (new StringBuilder("When you order ")).append(mainName).append(" to humiliate ").append(himHer()).append("self for the crowd, ").append(heShe()).append(" just starts struggling, attempting to do the opposite out of pure spite.  ").toString());
                else
                    w.append(t, (new StringBuilder("You send your Thralls to drag ")).append(mainName).append("'s helpless form out into public.  A crowd quickly gathers around ").append(himHer()).append(", gawking curiously, but ").append(mainName).append(" tries to stand up for ").append(himHer()).append("self despite the humiliating situation.  ").toString());
                if(disgrace < 20)
                {
                    if(dignity > 66)
                        w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" claims that ").append(heShe()).append("'s here by ").append(hisHer()).append(" own choice, and that it's part of a plan ").append(heShe()).append("'s enacting as one of the Chosen, but the feeble lie doesn't convince anyone.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" insists that the Demon Lord will never break ").append(hisHer()).append(" spirit, and that this method won't work either.").toString());
                } else
                if(dignity > 66)
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" rants about how the Demon Lord is just trying to make everyone think ").append(heShe()).append("'s weak, but that ").append(heShe()).append("'s actually still as strong as ever.").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" insults you directly, claiming that the Demon Lord isn't actually so strong, and that ").append(heShe()).append(" only lost to you because ").append(heShe()).append(" got unlucky.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" screams wordlessly, fighting with all ").append(hisHer()).append(" might in hopes that the raw effort will leave an impression on everyone who sees.").toString());
            }
            w.append(t, "\n\n");
            if((vaginal.booleanValue() || anal.booleanValue()) && servicing.booleanValue())
            {
                if(vaginal.booleanValue() && anal.booleanValue())
                    w.append(t, (new StringBuilder("The men penetrating ")).append(mainName).append("'s lower holes are too occupied with their own pleasure to even bother listening to ").append(mainName).append("'s words, and their pace quickens as they approach their climaxes.  ").toString());
                else
                    w.append(t, (new StringBuilder("The man thrusting into ")).append(mainName).append(" from behind is too occupied with his own pleasure to even bother listening to ").append(hisHer()).append(" words, and his pace quickens as ").append(heShe()).append(" approaches his climax.  ").toString());
                if(deviancy > 33)
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s voice becomes weaker and less coherent as the pace increases, and soon ").append(heShe()).append("'s just squealing and moaning in ").append(hisHer()).append(" own pitiful orgasm.").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder("With all the training ")).append(hisHer()).append(" body has experienced, ").append(mainName).append(" can't resist the pleasure for long, and ").append(heShe()).append(" quickly ends up forgetting all about the crowd, entirely focused on trying to cum as much as possible.").toString());
                    else
                        w.append(t, (new StringBuilder("The more the pleasure mounts, the more difficulty ")).append(mainName).append(" has controlling ").append(hisHer()).append(" voice.  At first, ").append(heShe()).append(" falls silent in an attempt to collect ").append(himHer()).append("self again, but soon ").append(heShe()).append("'s openly crying out in orgasm.").toString());
                } else
                if(hostility < 20)
                {
                    if(morality > 66)
                        w.append(t, (new StringBuilder("The sheer apathy drives ")).append(mainName).append(" to despair, and ").append(heShe()).append(" soon loses the will to raise ").append(hisHer()).append(" voice, hanging ").append(hisHer()).append(" head as ").append(heShe()).append(" tries to ignore the shameful pleasure building inside ").append(himHer()).append(".").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s voice is broken up by grunts and soft moans, but when ").append(heShe()).append(" tries to ask for more gentle treatment, there's no response.").toString());
                } else
                if(morality > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" tries to continue regardless, but the distraction is too much to bear, and ").append(heShe()).append(" ends up just biting ").append(hisHer()).append(" lip as ").append(heShe()).append(" tries to endure the rough stimulation.").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder("It's not long before ")).append(mainName).append(" loses ").append(hisHer()).append(" motivation to speak, hanging ").append(hisHer()).append(" head in exhaustion.").toString());
                else
                    w.append(t, (new StringBuilder("The combined pleasure and pain of the rough treatment eventually grows to the point of annoyance, but ")).append(mainName).append("'s only reaction is to halfheartedly try to kick behind ").append(himHer()).append(".  ").append(HeShe()).append(" knows that there's no point in asking for mercy.").toString());
            } else
            {
                if(currentTraining[4].booleanValue())
                {
                    if(dignity > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s voice dies out when ").append(heShe()).append(" sees how everyone in the crowd is looking at ").append(himHer()).append(".  ").append(HisHer()).append(" appearance, customized to exploit ").append(hisHer()).append(" insecurities, paralyzes ").append(hisHer()).append(" mind.  ").toString());
                    else
                    if(dignity > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s voice is soon drowned out by the derisive laughter of the crowd as they mock ").append(hisHer()).append(" ridiculous appearance, and ").append(hisHer()).append(" face goes red with shame.  ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s voice breaks with a startled cry as the crowd surges forward to grope, pinch, and write derogatory messages on the skin exposed by ").append(hisHer()).append(" ridiculous outfit.  ").toString());
                } else
                {
                    w.append(t, (new StringBuilder("At first, the crowd is interested, but they soon grow bored and begin pelting ")).append(mainName).append(" with garbage and shouting over ").append(himHer()).append(".  ").toString());
                }
                if(hostility < 20)
                {
                    if(morality > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is shocked speechless by how vicious they are.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" tries to keep going, but it's no use, as things have become so rowdy that ").append(heShe()).append(" has no hope of making ").append(himHer()).append("self heard.").toString());
                } else
                if(morality > 66)
                    w.append(t, (new StringBuilder("Once it's obvious that there's no point in trying to continue, ")).append(mainName).append(" hangs ").append(hisHer()).append(" head, conserving ").append(hisHer()).append(" energy.").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" bites ").append(hisHer()).append(" lip in annoyance, but doesn't bother trying to continue.  ").append(HeShe()).append(" knows that there's no point.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" falls silent, taking a sort of grim satisfaction in seeing proof that ").append(heShe()).append(" was right not to expect any sympathy from them.").toString());
            }
            if(servicing.booleanValue())
            {
                if(vaginal.booleanValue())
                {
                    int added = 3 + (int)(Math.random() * 3D);
                    timesHadSex += added;
                    orgasmsGiven += added;
                }
                if(anal.booleanValue())
                {
                    int added = 3 + (int)(Math.random() * 3D);
                    orgasmsGiven += added;
                    if(gender == Gender.MALE)
                        timesHadSex += added;
                    else
                        timesTortured += added;
                }
                if(oral.booleanValue())
                    orgasmsGiven += 3 + (int)(Math.random() * 3D);
            }
        } else
        if(nextTraining == 3)
        {
            String target = "penis";
            if(gender == Gender.FEMALE)
                target = "clit";
            if(tied.booleanValue())
            {
                if(confidence > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" reflexively struggles against ").append(hisHer()).append(" bonds as ").append(heShe()).append(" feels a sudden vibration against ").append(hisHer()).append(" most sensitive places.  Egg-shaped vibrators attach themselves to ").append(hisHer()).append(" nipples and ").append(target).append(", stimulating ").append(himHer()).append(" incessantly.  ").toString());
                else
                if(confidence > 33)
                    w.append(t, (new StringBuilder("More and more straps wrap themselves around ")).append(mainName).append(", anchoring ").append(himHer()).append(" in place and holding vibrating wands against ").append(hisHer()).append(" nipples and ").append(target).append(".  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" releases an involuntary whimper as a trio of vibrating rings form around ").append(hisHer()).append(" nipples and ").append(target).append(", squeezing around them so tightly that it's almost painful.  ").toString());
            } else
            if(!consenting.booleanValue())
            {
                if(confidence > 66)
                    w.append(t, (new StringBuilder("Countless ribbons begin to materialize in the air around ")).append(mainName).append(", fragile in appearance but strong enough to bind ").append(hisHer()).append(" wrists and ankles and to hold vibrators against ").append(hisHer()).append(" most sensitive parts.  ").toString());
                else
                if(confidence > 33)
                    w.append(t, (new StringBuilder("Handcuffs and anklecuffs abruptly appear on ")).append(mainName).append("'s body, instantly immobilizing ").append(himHer()).append(".  A moment later, they're joined by vibrating eggs stuck against ").append(hisHer()).append(" nipples and ").append(target).append(", and with ").append(hisHer()).append(" limbs trapped, ").append(heShe()).append(" can't do anything to pull them off.  ").toString());
                else
                    w.append(t, (new StringBuilder("Electrified rings appear around ")).append(mainName).append("'s nipples and ").append(target).append(", causing ").append(himHer()).append(" to squeak with startled pleasure as they deliver low pulses to their targets.  When ").append(heShe()).append(" tries to escape, the pulses become so strong that ").append(heShe()).append("'s unable to resist.  ").toString());
            } else
            if(confidence > 66)
                w.append(t, (new StringBuilder("Rough ropes wrap themselves around ")).append(mainName).append("'s body, then tighten up abruptly enough to force a grunt out of ").append(himHer()).append(".  ").append(HeShe()).append(" briefly manages to regain control of ").append(himHer()).append("self, only to gasp unwillingly as vibrating clamps attach themselves to ").append(hisHer()).append(" nipples and ").append(target).append(".  ").toString());
            else
            if(confidence > 33)
                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is startled by how quickly ").append(heShe()).append("'s surrounded by heavy chains that start binding ").append(hisHer()).append(" body, but ").append(heShe()).append(" does ").append(hisHer()).append(" best to calm ").append(himHer()).append("self, even when small egg-shaped vibrators appear to adorn ").append(hisHer()).append(" nipples and ").append(target).append(".  ").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" obediently lays down with ").append(hisHer()).append(" wrists together above ").append(hisHer()).append(" head and ").append(hisHer()).append(" ankles spread wide apart.  Handcuffs appear above and a spreader bar below, joined by vibrating rings attached to ").append(hisHer()).append(" nipples and ").append(target).append(".  ").toString());
            if(timesHadSex > 0)
            {
                if(gender == Gender.MALE)
                    anal = Boolean.valueOf(true);
                else
                    vaginal = Boolean.valueOf(true);
                if(gender == Gender.FEMALE && timesTortured > 0)
                    anal = Boolean.valueOf(true);
            } else
            if(gender == Gender.FEMALE && timesTortured > 0)
                anal = Boolean.valueOf(true);
            if(servicing.booleanValue())
            {
                if(vaginal.booleanValue() && anal.booleanValue())
                    w.append(t, (new StringBuilder("The crowd, which had begun to grow bored with ")).append(hisHer()).append(" ruined holes, watches with renewed interest as a pair of dildoes appear from underneath ").append(himHer()).append(" and fill ").append(himHer()).append(" completely.").toString());
                else
                if(vaginal.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(HisHer()))).append(" exhausted, dripping pussy is filled almost unbearably full with a huge dildo which forces its way inside.").toString());
                else
                if(anal.booleanValue())
                {
                    if(gender == Gender.MALE)
                        w.append(t, (new StringBuilder(String.valueOf(HisHer()))).append(" exhausted, dripping asshole is filled almost unbearably full with a huge dildo which forces its way inside.").toString());
                    else
                        w.append(t, (new StringBuilder("Mercifully, a chastity belt locks itself onto ")).append(himHer()).append(" in order to help preserve ").append(hisHer()).append(" virginity - but an opening in the back allows a thick dildo to force its way into ").append(hisHer()).append(" ruined anus, stretching it even wider.").toString());
                } else
                {
                    w.append(t, (new StringBuilder("Mercifully, a chastity belt locks itself onto ")).append(himHer()).append(" in order to make it easier for ").append(himHer()).append(" to defend ").append(hisHer()).append(" ").toString());
                    if(gender == Gender.MALE)
                        w.append(t, "anal ");
                    w.append(t, (new StringBuilder("virginity - but it also means that ")).append(heShe()).append("'s even more helpless to stop the pleasure.").toString());
                }
            } else
            if(inPublic.booleanValue())
            {
                if(vaginal.booleanValue() && anal.booleanValue())
                    w.append(t, (new StringBuilder("The crowd watches with intense interest as ")).append(hisHer()).append(" pussy and asshole both are forced open by a pair of dildoes, filling ").append(himHer()).append(" to the point that a bulge is visible in ").append(hisHer()).append(" lower belly.").toString());
                else
                if(vaginal.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(HisHer()))).append(" slit is forced open, a huge dildo penetrating ").append(himHer()).append(" and pushing its way deep inside while the crowd masturbates to the sight.").toString());
                else
                if(anal.booleanValue())
                {
                    if(gender == Gender.MALE)
                        w.append(t, (new StringBuilder(String.valueOf(HisHer()))).append(" tight anus is forced open, a huge dildo penetrating ").append(himHer()).append(" and pushing its way deep inside while the crowd masturbates to the sight.").toString());
                    else
                        w.append(t, (new StringBuilder("A chastity belt locks itself around ")).append(hisHer()).append(" hips, enticing the crowd with the reminder of ").append(hisHer()).append(" virginity - but also leaving ").append(hisHer()).append(" backside bare so that a thick dildo can force its way inside.").toString());
                } else
                {
                    w.append(t, (new StringBuilder("A chastity belt locks itself around ")).append(hisHer()).append(" hips, enticing the crowd with the reminder of ").append(hisHer()).append(" ").toString());
                    if(gender == Gender.MALE)
                        w.append(t, "anal ");
                    w.append(t, (new StringBuilder("virginity - and also rendering ")).append(himHer()).append(" even more helpless to stop the pleasure.").toString());
                }
            } else
            if(vaginal.booleanValue() && anal.booleanValue())
                w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" cries out as ").append(hisHer()).append(" pussy and asshole are both forced open by a pair of dildoes, so that ").append(heShe()).append("'s stimulated from the outside and the inside at the same time.").toString());
            else
            if(vaginal.booleanValue())
                w.append(t, (new StringBuilder(String.valueOf(HisHer()))).append(" slit is forced open, a huge dildo penetrating ").append(himHer()).append(" and pushing its way deep inside.").toString());
            else
            if(anal.booleanValue())
            {
                if(gender == Gender.MALE)
                    w.append(t, (new StringBuilder(String.valueOf(HisHer()))).append(" tight anus is forced open, a huge dildo penetrating ").append(himHer()).append(" and pushing its way deep inside.").toString());
                else
                    w.append(t, (new StringBuilder("A chastity belt locks itself around ")).append(hisHer()).append(" hips, ensuring that ").append(heShe()).append(" can't prevent the stimulation - but an opening in the back allows a thick dildo to force its way into ").append(hisHer()).append(" ass.").toString());
            } else
            {
                w.append(t, (new StringBuilder("A chastity belt locks itself around ")).append(hisHer()).append(" hips, ensuring that ").append(heShe()).append(" can't prevent the stimulation.").toString());
            }
            if(currentTraining[0].booleanValue())
                servicing = Boolean.valueOf(true);
            w.append(t, "\n\n");
            if(obedience < 20)
            {
                if(confidence > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" still isn't used to feeling so vulnerable, and the knowledge that there's nothing ").append(heShe()).append(" can do to stop this makes the experience that much more intense.  ").toString());
                else
                if(confidence > 33)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is unable to stop ").append(himHer()).append("self from struggling wildly, although even ").append(heShe()).append(" isn't sure whether ").append(heShe()).append("'s trying to escape the vibration or to press ").append(himHer()).append("self against it more firmly.  ").toString());
            } else
            if(confidence > 66)
                w.append(t, (new StringBuilder("Even though ")).append(mainName).append(" has come to submit ").append(himHer()).append("self to your will, ").append(heShe()).append("'s still put mentally off-balance by this kind of vulnerability.  ").toString());
            else
            if(confidence > 33)
                w.append(t, (new StringBuilder("Even more than the vibrating toys, the fact that ")).append(heShe()).append("'s completely powerless now turns ").append(mainName).append(" on.  ").toString());
            else
                w.append(t, (new StringBuilder("For the naturally-submissive ")).append(mainName).append(", this kind of situation is inherently arousing.  ").toString());
            if(inPublic.booleanValue())
            {
                if(vaginal.booleanValue() || anal.booleanValue())
                {
                    String dildo = "dildo";
                    if(vaginal.booleanValue() && anal.booleanValue())
                        dildo = "dildoes";
                    if(deviancy > 33)
                    {
                        if(innocence > 66)
                            w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" cums over and over again, tongue hanging out of ").append(hisHer()).append(" mouth, and when you finally remove the ").append(dildo).append(", ").append(heShe()).append(" arches ").append(hisHer()).append(" back without thinking, presenting ").append(himHer()).append("self to be taken by the whole crowd.").toString());
                        else
                        if(innocence > 33)
                            w.append(t, (new StringBuilder("But with how much ")).append(hisHer()).append(" libido has grown, this alone isn't enough to satisfy ").append(himHer()).append(".  ").append(HeShe()).append(" groans with disappointment at the removal of the ").append(dildo).append(", looking back at the crowd with glazed eyes and begging them to rape ").append(himHer()).append(".").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" no longer even wants to resist the pleasure, and ").append(heShe()).append(" fucks the ").append(dildo).append(" enthusiastically.  When you finally pull out, ").append(heShe()).append(" smirks deliriously and provocatively waggles ").append(hisHer()).append(" bottom for the crowd's benefit.").toString());
                    } else
                    if(timesOrgasmed == 0)
                        w.append(t, (new StringBuilder("The penetration is still uncomfortable for ")).append(hisHer()).append(" inexperienced, untrained body, but ").append(heShe()).append("'s still breathing heavily by the time you pull out to give the crowd a clear look at ").append(hisHer()).append(" intimate places.").toString());
                    else
                    if(innocence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" soon shudders with orgasm, then goes limp as you pull the ").append(dildo).append(" out.  ").append(HeShe()).append(" can't even imagine feeling a stronger pleasure than that, but the crowd is eager to teach ").append(himHer()).append(".").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" isn't comfortable with cumming in front of such a large crowd, but ").append(heShe()).append(" soon succumbs anyway, and the sight of ").append(himHer()).append(" writhing and moaning excites everyone who sees it - especially when you pull out and move aside the ").append(dildo).append(".").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" allows ").append(himHer()).append("self to cum, knowing that there's no point in resistance, but then looks back in trepidation as you pull out the ").append(dildo).append(" and present ").append(hisHer()).append(" defenseless body to the crowd.").toString());
                } else
                if(deviancy > 33)
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" cums over and over again, tongue hanging out of ").append(hisHer()).append(" mouth, and when you give ").append(himHer()).append(" a break from the vibration, ").append(heShe()).append(" thoughtlessly starts trying to squirm out of ").append(hisHer()).append(" chastity belt, unable to think of anything other than getting filled up inside.").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder("But with how much ")).append(hisHer()).append(" libido has grown, this alone isn't enough to satisfy ").append(himHer()).append(".  ").append(HeShe()).append(" groans with disappointment when you shut off the vibration, begging you to unlock ").append(hisHer()).append(" belt and let the crowd violate ").append(himHer()).append(".").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" no longer even wants to resist the pleasure, and ").append(heShe()).append(" bucks ").append(hisHer()).append(" hips enthusiastically whenever you pulse the vibration.  Even after the vibration dies down, ").append(heShe()).append(" looks eager for more torment.").toString());
                } else
                if(timesOrgasmed == 0)
                    w.append(t, (new StringBuilder("The intense vibration is still uncomfortable for ")).append(hisHer()).append(" inexperienced, untrained body, but ").append(heShe()).append("'s still breathing heavily once you shut off the vibration in order to avoid desensitizing ").append(himHer()).append(".").toString());
                else
                if(innocence > 66)
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" soon shudders with orgasm, then goes limp as you briefly turn the vibration off.  ").append(HeShe()).append(" can't even imagine feeling a stronger pleasure than that - at least not yet.").toString());
                else
                if(innocence > 33)
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" isn't comfortable with cumming in front of such a large crowd, but ").append(heShe()).append(" soon succumbs anyway, and the sight of ").append(himHer()).append(" writhing and moaning excites everyone who sees it, prompting many of them to reach their own orgasms as well.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" allows ").append(himHer()).append("self to cum, knowing that there's no point in resistance.  When the vibration briefly shuts off, ").append(heShe()).append(" looks relieved at first, but ").append(heShe()).append(" quickly realizes that you surely have more torments in store.").toString());
            } else
            if(deviancy > 33)
            {
                if(innocence > 66)
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" cums over and over again, tongue hanging out of ").append(hisHer()).append(" mouth.  Babbling incoherently, ").append(heShe()).append(" offers ").append(himHer()).append("self to anyone who can make ").append(himHer()).append(" feel even better, but there's no one nearby to take ").append(himHer()).append(".").toString());
                else
                if(innocence > 33)
                    w.append(t, (new StringBuilder("But with how much ")).append(hisHer()).append(" libido has grown, this alone isn't enough to satisfy ").append(himHer()).append(".  ").append(HeShe()).append(" whines pitifully, begging someone to rape ").append(himHer()).append(", but there's nobody within earshot to take ").append(himHer()).append(" up on the offer.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" no longer even wants to resist the pleasure, and ").append(heShe()).append(" bucks ").append(hisHer()).append(" hips enthusiastically as ").append(heShe()).append(" cums.  The fact that ").append(heShe()).append("'s alone makes ").append(himHer()).append(" even more willing to indulge in ").append(hisHer()).append(" debauchery.").toString());
            } else
            if(timesOrgasmed == 0)
                w.append(t, (new StringBuilder("The intense vibration is still uncomfortable for ")).append(hisHer()).append(" inexperienced, untrained body, but ").append(heShe()).append("'s still breathing heavily once you shut off the vibration in order to avoid desensitizing ").append(himHer()).append(".").toString());
            else
            if(innocence > 66)
                w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" soon shudders with orgasm, then goes limp as you briefly turn the vibration off.  ").append(HeShe()).append(" can't even imagine feeling a stronger pleasure than that - at least not yet.").toString());
            else
            if(innocence > 33)
                w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" still reflexively denies the pleasure you're forcing upon ").append(himHer()).append(", but ").append(heShe()).append(" soon succumbs to the inevitable orgasm anyway.  Over and over again, you give ").append(himHer()).append(" bursts of intense stimulation to bring ").append(himHer()).append(" to the peak, until ").append(hisHer()).append(" body begins to crave it.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" allows ").append(himHer()).append("self to cum, knowing that there's no point in resistance.  When the vibration briefly shuts off, ").append(heShe()).append(" looks relieved at first, but ").append(heShe()).append(" quickly realizes that you surely have more torments in store.").toString());
            if(timesOrgasmed > 0)
                timesOrgasmed += 5 + (int)(Math.random() * 5D);
        } else
        if(nextTraining == 4)
        {
            if(consenting.booleanValue())
            {
                if(inPublic.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" transforms at your command, allowing you plenty of opportunity to influence the form ").append(hisHer()).append(" Forsaken garb will take.  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" heads out into public, gathers the attention of a crowd, and then transforms.  However, under the influence of your power, not even ").append(heShe()).append(" can predict what form ").append(hisHer()).append(" outfit will take.  ").toString());
            } else
            {
                w.append(t, (new StringBuilder("In ")).append(hisHer()).append(" efforts to escape, ").append(mainName).append(" has transformed into ").append(hisHer()).append(" Forsaken form, hoping to draw on whatever residual psychic energy ").append(heShe()).append(" can.  However, under the influence of your power, the psychic energy which forms ").append(hisHer()).append(" garb begins to shift and warp.  ").toString());
                if(!inPublic.booleanValue())
                    w.append(t, (new StringBuilder("The commotion caused by ")).append(hisHer()).append(" struggles quickly draws a curious crowd, and they're surprised by what they see.  ").toString());
            }
            if(currentTraining[3].booleanValue())
            {
                w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append("'s left practically naked, as the material of ").append(hisHer()).append(" outfit migrates to ").append(hisHer()).append(" limbs, becoming tight bindings that force ").append(himHer()).append(" to crawl on all fours.  A headband resembling animal ears appears on ").append(hisHer()).append(" head").toString());
                if(anal.booleanValue())
                {
                    if(servicing.booleanValue())
                        w.append(t, (new StringBuilder(", and the cum flowing out of ")).append(hisHer()).append(" ass is plugged inside by a vibrator with a tail attached to the other end.").toString());
                    else
                        w.append(t, (new StringBuilder(", and the vibrator up ")).append(hisHer()).append(" ass sprouts a long tail.").toString());
                } else
                {
                    w.append(t, ".");
                }
            } else
            if(currentTraining[2].booleanValue())
                w.append(t, (new StringBuilder(String.valueOf(HisHer()))).append(" clothes vanish almost completely, revealing that tattoos spelling out derogatory messages have appeared across ").append(hisHer()).append(" skin.  Arrows pointing toward ").append(hisHer()).append(" mouth and crotch proclaim that ").append(heShe()).append("'s free for public use.").toString());
            else
            if(disgrace < 20)
                w.append(t, (new StringBuilder(String.valueOf(HisHer()))).append(" clothes become skimpy in the extreme, consisting of a few strings around ").append(hisHer()).append(" chest and waist which hold thin pieces of cloth over ").append(hisHer()).append(" nipples and crotch.  The cloth hangs down loosely, so that even moving ").append(hisHer()).append(" body slightly to the side causes them to shift and reveal everything.  The sexual nature of the outfit makes it even more embarrassing than simple nudity.").toString());
            else
            if(obedience < 20)
                w.append(t, (new StringBuilder("A fur-lined bodysuit spreads across ")).append(hisHer()).append(" skin, with openings over ").append(hisHer()).append(" nipples and groin, complete with an animal ear headband, a fluffy tail, and paw-like gloves that render ").append(hisHer()).append(" hands practically useless.  A collar with an attached leash completes the ensemble, marking ").append(himHer()).append(" as the Demon Lord's pet.").toString());
            else
            if(deviancy < 20)
            {
                w.append(t, (new StringBuilder("Latex spreads across ")).append(hisHer()).append(" skin, but stops short of ").append(hisHer()).append(" nipples and groin.  The outfit is fitted tightly enough that it squeezes and emphasizes ").append(hisHer()).append(" chest and butt, and adhesive strips build into the material spread open ").append(hisHer()).append(" ass ").toString());
                if(gender != Gender.MALE)
                    w.append(t, "and pussy ");
                w.append(t, "so that the pink insides are plainly visible.");
            } else
            if(hostility < 20)
                w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append("'s enveloped in tentacles which caress ").append(hisHer()).append(" chest and frame ").append(hisHer()).append(" groin in a mockery of modesty, marking ").append(himHer()).append(" as a servant of the Demons.").toString());
            else
                w.append(t, (new StringBuilder("Countless ropes wrap themselves intricately around ")).append(hisHer()).append(" torso, the crisscrossing pattern managing to avoid actually covering any of ").append(hisHer()).append(" private parts.  It's less of an outfit and more of an ornament for ").append(hisHer()).append(" naked body.").toString());
            w.append(t, "\n\n");
            if(consenting.booleanValue())
            {
                if(disgrace < 20)
                {
                    if(dignity > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s heart pounds, ").append(hisHer()).append(" face turning a bright shade of red, but ").append(heShe()).append(" forces ").append(himHer()).append("self to smile back at all the people leering at ").append(himHer()).append(".  ").toString());
                    else
                    if(dignity > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" reflexively tries to cover ").append(himHer()).append("self, but barely manages to keep ").append(hisHer()).append(" trembling hands where they are.  ").toString());
                } else
                if(dignity > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" feels ").append(hisHer()).append(" heart jump when ").append(heShe()).append(" realizes how hard everyone is staring at ").append(himHer()).append(", and ").append(heShe()).append(" can't keep a delirious smile off ").append(hisHer()).append(" face as ").append(heShe()).append(" basks in the attention.  ").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder("The cool air on the exposed parts of ")).append(mainName).append("'s skin is a constant reminder of how much ").append(heShe()).append("'s showing off, but it doesn't even bother ").append(himHer()).append(" anymore.  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" can't help but be a bit amused at how strongly the crowd is affected by ").append(hisHer()).append(" new appearance.  ").toString());
            } else
            if(disgrace < 20)
            {
                if(dignity > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" freezes up, so embarrassed that ").append(heShe()).append(" can't even move a muscle.  ").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" cries out and attempts to cover ").append(himHer()).append("self, but ").append(hisHer()).append(" attempts are futile.  ").toString());
            } else
            if(dignity > 66)
                w.append(t, (new StringBuilder("Rather than trying to cover ")).append(hisHer()).append(" body, ").append(mainName).append(" just turns ").append(hisHer()).append(" face away from the crowd.  At this point, the exposure bothers ").append(himHer()).append(" less than the show of submission itself.  ").toString());
            else
            if(dignity > 33)
                w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s face goes red with humiliation, less at the fact that ").append(heShe()).append("'s exposing everything and more at the particular way ").append(heShe()).append("'s being forced to expose ").append(himHer()).append("self.  ").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" doesn't stop struggling, more motivated than embarrassed by the display ").append(heShe()).append("'s being forced to put on.  ").toString());
            if(servicing.booleanValue())
            {
                if(oral.booleanValue())
                {
                    if(anal.booleanValue())
                    {
                        if(currentTraining[3].booleanValue())
                        {
                            w.append(t, (new StringBuilder("The crowd surges forward, pulling the tail back out of ")).append(hisHer()).append(" ass in order to ").toString());
                            if(vaginal.booleanValue())
                                w.append(t, (new StringBuilder("pound ")).append(himHer()).append(" in all three holes at once.  ").toString());
                            else
                            if(gender == Gender.MALE)
                                w.append(t, (new StringBuilder("pound ")).append(himHer()).append(" from both sides at once.  ").toString());
                            else
                                w.append(t, (new StringBuilder("pound ")).append(hisHer()).append(" hole as another fucks ").append(hisHer()).append(" throat.  ").toString());
                        } else
                        if(vaginal.booleanValue())
                            w.append(t, (new StringBuilder("With its lust stoked even further, the crowd rushes in again, pounding ")).append(himHer()).append(" relentlessly in all three holes.  ").toString());
                        else
                        if(gender == Gender.MALE)
                            w.append(t, (new StringBuilder("With its lust stoked even further, the crowd rushes in again, pounding ")).append(himHer()).append(" in both holes.  ").toString());
                        else
                            w.append(t, (new StringBuilder("With its lust stoked even further, the crowd rushes in again, pounding ")).append(hisHer()).append(" mouth from the front and ").append(hisHer()).append(" ass from behind, filling both holes with their cum.  ").toString());
                    } else
                    if(vaginal.booleanValue())
                        w.append(t, (new StringBuilder("Enticed by the holes on display, the crowd grabs ")).append(himHer()).append(", one of its members ramming himself into ").append(hisHer()).append(" pussy, then another thrusting down ").append(hisHer()).append(" throat when ").append(heShe()).append(" gasps at the intense insertion.  ").toString());
                    else
                        w.append(t, (new StringBuilder("Enticed by ")).append(hisHer()).append(" lewd appearance, the crowd forms a line, each member in turn thrusting down ").append(hisHer()).append(" throat and shooting a load of cum inside, then moving aside for whoever's next.  ").toString());
                } else
                if(anal.booleanValue())
                {
                    if(currentTraining[3].booleanValue())
                    {
                        w.append(t, (new StringBuilder("The crowd surges forward, pulling the tail back out of ")).append(hisHer()).append(" ass in order to ").toString());
                        if(vaginal.booleanValue())
                            w.append(t, (new StringBuilder("pound both ")).append(hisHer()).append(" lower holes at once.  ").toString());
                        else
                            w.append(t, (new StringBuilder("pound ")).append(hisHer()).append(" ass.  ").toString());
                    } else
                    if(vaginal.booleanValue())
                        w.append(t, (new StringBuilder("With its lust stoked even further, the crowd rushes in again, pounding both ")).append(hisHer()).append(" lower holes at once.  ").toString());
                    else
                        w.append(t, (new StringBuilder("With its lust stoked even further, the crowd rushes in again, pounding ")).append(hisHer()).append(" relentlessly.  ").toString());
                } else
                if(vaginal.booleanValue())
                    w.append(t, (new StringBuilder("Enticed by the hole on display, the crowd pulls ")).append(himHer()).append(" down to the ground.  Two of its members force ").append(hisHer()).append(" legs wide apart, while the others take turns fucking ").append(himHer()).append(".  ").toString());
                else
                    w.append(t, (new StringBuilder("Enticed by the lewd display, many of the members of the crowd break out into open masturbation, aiming their ejaculations directly at ")).append(mainName).append("'s body.  ").toString());
                if(vaginal.booleanValue())
                {
                    int added = 3 + (int)(Math.random() * 3D);
                    timesHadSex += added;
                    orgasmsGiven += added;
                }
                if(anal.booleanValue())
                {
                    int added = 3 + (int)(Math.random() * 3D);
                    orgasmsGiven += added;
                    if(gender == Gender.MALE)
                        timesHadSex += added;
                    else
                        timesTortured += added;
                }
                if(oral.booleanValue())
                    orgasmsGiven += 3 + (int)(Math.random() * 3D);
            }
            if(oral.booleanValue() || anal.booleanValue() || vaginal.booleanValue())
            {
                if(deviancy < 20)
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" absolutely hates it.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" feels sick, all the moreso when ").append(heShe()).append(" recognizes how good it's starting to feel.").toString());
                } else
                if(innocence > 66)
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" finds the experience of being put on display as a public cum receptacle oddly relaxing.").toString());
                else
                if(innocence > 33)
                    w.append(t, (new StringBuilder("The kinkiness of the situation is too much for ")).append(himHer()).append(" to deny, and ").append(heShe()).append(" ends up enjoying ").append(himHer()).append("self.").toString());
                else
                    w.append(t, (new StringBuilder("In between being groped and fucked, ")).append(heShe()).append(" has to admire how ").append(hisHer()).append(" appearance alterations were calculated not only to degrade ").append(himHer()).append(", but also to drive everyone else into a frenzy.").toString());
            } else
            if(deviancy < 20)
            {
                if(innocence > 66)
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append("'s confused about how they can enjoy just looking at ").append(himHer()).append(" so much.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append("'s just glad that they aren't going any further.").toString());
            } else
            if(innocence > 66)
                w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" feels ").append(hisHer()).append(" own body getting strangely heated as ").append(heShe()).append(" sees how everyone's looking at ").append(himHer()).append(".").toString());
            else
            if(innocence > 33)
                w.append(t, (new StringBuilder("A part of ")).append(himHer()).append(" wishes that they'd go further.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" can't deny that ").append(heShe()).append("'s getting more and more aroused, too.").toString());
        } else
        if(nextTraining == 5)
        {
            String target = "penis";
            if(gender == Gender.FEMALE)
                target = "clit";
            if(currentTraining[3].booleanValue())
            {
                w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s bindings begin to expand and merge together, encasing ").append(himHer()).append(" in a solid block of material that leaves ").append(himHer()).append(" completely immobile, unable to move even slightly away from the stimulation against ").append(hisHer()).append(" ").append(target).append(".  Only ").append(hisHer()).append(" head ").toString());
                if(vaginal.booleanValue() || anal.booleanValue())
                    w.append(t, "and butt are exposed to the air.  ");
                else
                    w.append(t, (new StringBuilder("is exposed to the air.  ")).append(HisHer()).append(" lower body is covered as if by a chastity belt, complete with an internal vibrator.  ").toString());
            } else
            {
                w.append(t, (new StringBuilder("A heavy pillory appears and snaps shut around ")).append(mainName).append("'s neck and wrists, rendering ").append(himHer()).append(" completely helpless.  ").toString());
            }
            if(servicing.booleanValue())
                w.append(t, (new StringBuilder("Although the crowd was already enjoying ")).append(hisHer()).append(" body, the sight of ").append(himHer()).append(" being bound so tightly turns many of them on even more.  ").toString());
            else
            if(currentTraining[0].booleanValue())
            {
                w.append(t, (new StringBuilder("And now that ")).append(heShe()).append("'s unable to stop them, the lustful crowd wastes no time in surging forward to make use of ").append(hisHer()).append(" body.  ").toString());
                if(timesHadSex > 0)
                {
                    if(timesTortured > 0)
                        anal = Boolean.valueOf(true);
                    if(gender != Gender.MALE)
                        vaginal = Boolean.valueOf(true);
                } else
                if(timesTortured > 0 && gender != Gender.MALE)
                    anal = Boolean.valueOf(true);
                if(obedience >= 20 || orgasmsGiven >= 1000)
                    oral = Boolean.valueOf(true);
                servicing = Boolean.valueOf(true);
                if(vaginal.booleanValue() || anal.booleanValue())
                {
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" can't even move ").append(hisHer()).append(" hips away from the first ").toString());
                    if(vaginal.booleanValue() && anal.booleanValue())
                    {
                        w.append(t, (new StringBuilder("two people who step forward to line their cocks up with ")).append(hisHer()).append(" lower holes and thrust their way inside.  ").toString());
                    } else
                    {
                        w.append(t, (new StringBuilder("person who steps forward to line his cock up with ")).append(mainName).append("'s ").toString());
                        if(anal.booleanValue())
                            w.append(t, "anus");
                        else
                            w.append(t, "lower lips");
                        w.append(t, " and thrust his way inside.  ");
                    }
                    if(oral.booleanValue())
                        w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" tries to look back at what's happening, but another man grabs ").append(himHer()).append(" by the hair and forces ").append(himHer()).append(" to start sucking his cock.  ").toString());
                } else
                if(oral.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s eyes go wide and ").append(heShe()).append(" shakes ").append(hisHer()).append(" head as one man approaches with his erect cock already out, but there's nothing ").append(mainName).append(" can do to stop him from grabbing ").append(himHer()).append(" by the hair and ramming it into ").append(hisHer()).append(" mouth.  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s clenched teeth are enough to hold at bay the members of the crowd who might be interested in fucking ").append(hisHer()).append(" mouth, but they instead content themselves with masturbating nearby and cumming all over ").append(hisHer()).append(" face and hair.  ").toString());
            } else
            if(inPublic.booleanValue())
                w.append(t, (new StringBuilder("The crowd cheers, eager to see ")).append(himHer()).append(" humbled even further.  ").toString());
            else
                w.append(t, (new StringBuilder("You summon some of your Thralls to wheel ")).append(himHer()).append(" out into public so that everyone can see ").append(hisHer()).append(" predicament.  ").toString());
            if(consenting.booleanValue())
            {
                if(obedience < 20)
                {
                    if(confidence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" finds the treatment extremely insulting, but ").append(heShe()).append(" stubbornly endures it.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" tries not to let it get to ").append(himHer()).append(", but the truth is that ").append(heShe()).append(" feels weaker and more pathetic than ever.").toString());
                } else
                if(confidence > 66)
                    w.append(t, (new StringBuilder("Far from ")).append(hisHer()).append(" once defiant nature, ").append(mainName).append(" just hangs limp, not even attempting to escape.").toString());
                else
                if(confidence > 33)
                    w.append(t, (new StringBuilder("Having known that this was coming, ")).append(mainName).append(" cooperates fully, entrusting ").append(hisHer()).append(" body to your training.").toString());
                else
                    w.append(t, (new StringBuilder("As much as the crowd's cruelty stings, ")).append(mainName).append(" finds it oddly comforting that there's nothing ").append(heShe()).append(" could do to stop this even if ").append(heShe()).append(" tried.").toString());
            } else
            if(obedience < 20)
            {
                if(confidence > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" struggles with all ").append(hisHer()).append(" considerable might, moving the contraption left and right with every shift of ").append(hisHer()).append(" weight, but ").append(heShe()).append(" still can't get free.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" struggles as best ").append(heShe()).append(" can, grunting with futile effort.").toString());
            } else
            if(confidence > 66)
                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" glares angrily up at those surrounding ").append(himHer()).append(", but ").append(heShe()).append(" knows that there's no point in resisting anymore.").toString());
            else
            if(confidence > 33)
                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" just closes ").append(hisHer()).append(" eyes and hopes that it will be over soon.").toString());
            else
                w.append(t, (new StringBuilder("Tears run down ")).append(mainName).append("'s cheeks.").toString());
            w.append(t, "\n\n");
            if(servicing.booleanValue())
                w.append(t, (new StringBuilder("Within a few minutes, ")).append(mainName).append(" is a sticky mess, unable to even wipe the cum off ").append(hisHer()).append(" face.  ").toString());
            else
                w.append(t, (new StringBuilder("The longer ")).append(mainName).append(" spends on display, the more bold the crowd gets.  They throw garbage at ").append(himHer()).append(", write degrading messages on ").append(hisHer()).append(" exposed skin, and generally enjoy themselves at ").append(hisHer()).append(" expense.  ").toString());
            if(disgrace < 20)
            {
                if(dignity > 66)
                    w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append("'s mortified by the crowd's amused chatter about how ").append(heShe()).append(" must not have ever been very strong after all, and the worst part is that ").append(heShe()).append(" can't do anything at all to prove them wrong.").toString());
                else
                    w.append(t, (new StringBuilder("The way the crowd is looking at ")).append(himHer()).append(" is already more contemptful than it was when they first saw ").append(himHer()).append(".").toString());
            } else
            if(dignity > 66)
                w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append("'s gotten used to the lack of respect by now, but the occasional laughing comment still cuts deep enough to make ").append(hisHer()).append(" heart sink.").toString());
            else
            if(dignity > 33)
                w.append(t, (new StringBuilder(String.valueOf(HisHer()))).append(" only saving grace is that ").append(heShe()).append("'s lost ").append(hisHer()).append(" novelty as a target of abuse, as everyone already knows that ").append(heShe()).append("'s unable to fight back.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(HeShe()))).append(" tries to ignore the crowd's amused laughter, but a part of ").append(himHer()).append(" knows that the less they think of ").append(himHer()).append(", the worse ").append(hisHer()).append(" treatment will get.").toString());
            if(servicing.booleanValue())
            {
                if(vaginal.booleanValue())
                {
                    int added = 3 + (int)(Math.random() * 3D);
                    timesHadSex += added;
                    orgasmsGiven += added;
                }
                if(anal.booleanValue())
                {
                    int added = 3 + (int)(Math.random() * 3D);
                    orgasmsGiven += added;
                    if(gender == Gender.MALE)
                        timesHadSex += added;
                    else
                        timesTortured += added;
                }
                if(oral.booleanValue())
                    orgasmsGiven += 3 + (int)(Math.random() * 3D);
            }
        }
        currentTraining[nextTraining] = Boolean.valueOf(true);
        JButton Continue = new JButton("Continue");
        Continue.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                trainingMenu(t, p, f, w, s, currentTraining, 0, consenting);
            }

            final Forsaken this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final WorldState val$w;
            private final SaveData val$s;
            private final Boolean val$currentTraining[];
            private final Boolean val$consenting;

            
            {
                this$0 = Forsaken.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                w = worldstate;
                s = savedata;
                currentTraining = aboolean;
                consenting = boolean1;
                super();
            }
        });
        p.add(Continue);
        p.validate();
        p.repaint();
    }

    public int intensity(int baseParameter, int currentParameter, int parameterCount)
    {
        int total = currentParameter;
        if(baseParameter > 0 && (total += 5) < baseParameter + 5 * parameterCount)
            total = baseParameter + 5 * parameterCount;
        return total;
    }

    public int deviancyBuffer()
    {
        return deviancy / 2;
    }

    public int consentModifier()
    {
        int value = 0;
        if(obedience < 10)
            value = -50 + (5 * obedience) / 2;
        else
        if(obedience < 20)
            value = -45 + 2 * obedience;
        else
        if(obedience < 30)
            value = -35 + (3 * obedience) / 2;
        else
        if(obedience < 40)
            value = -20 + obedience;
        else
        if(obedience < 50)
            value = obedience / 2;
        else
        if(obedience < 60)
            value = obedience / 2;
        else
        if(obedience < 70)
            value = -30 + obedience;
        else
        if(obedience < 80)
            value = -65 + (3 * obedience) / 2;
        else
        if(obedience < 90)
            value = -105 + 2 * obedience;
        else
            value = -150 + (5 * obedience) / 2;
        return value;
    }

    public String himHer()
    {
        if(gender == Gender.MALE)
            return "him";
        else
            return "her";
    }

    public String hisHer()
    {
        if(gender == Gender.MALE)
            return "his";
        else
            return "her";
    }

    public String heShe()
    {
        if(gender == Gender.MALE)
            return "he";
        else
            return "she";
    }

    public String HimHer()
    {
        if(gender == Gender.MALE)
            return "Him";
        else
            return "Her";
    }

    public String HisHer()
    {
        if(gender == Gender.MALE)
            return "His";
        else
            return "Her";
    }

    public String HeShe()
    {
        if(gender == Gender.MALE)
            return "He";
        else
            return "She";
    }

    public void say(JTextPane t, String s)
    {
        StyledDocument doc = t.getStyledDocument();
        SimpleAttributeSet keyWord = new SimpleAttributeSet();
        StyleConstants.setFontSize(keyWord, textSize);
        Color usedColor = textColor;
        if(t.getBackground() == Color.BLACK)
            usedColor = darkColor;
        StyleConstants.setForeground(keyWord, usedColor);
        StyleConstants.setFontFamily(keyWord, "DialogInput");
        StyleConstants.setBold(keyWord, true);
        try
        {
            doc.insertString(doc.getLength(), s, keyWord);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public Forsaken()
    {
        textSize = 16;
        filthyGaijin = Boolean.valueOf(false);
        originalGender = Gender.FEMALE;
        gender = Gender.FEMALE;
        customWeaponType = "";
        feetType = "";
        ruthless = Boolean.valueOf(false);
        peopleInjured = 0;
        timesHadSex = 0;
        timesKilled = 0;
        demonicBirths = 0;
        lustful = Boolean.valueOf(false);
        orgasmsGiven = 0;
        timesOrgasmed = 0;
        strongestOrgasm = 0;
        hypnotized = Boolean.valueOf(false);
        meek = Boolean.valueOf(false);
        timesTortured = 0;
        timesHarmedSelf = 0;
        drained = Boolean.valueOf(false);
        debased = Boolean.valueOf(false);
        timesExposed = 0;
        timesExposedSelf = 0;
        parasitized = Boolean.valueOf(false);
        takers = (new Taker[] {
            Taker.NONE, Taker.NONE, Taker.NONE, Taker.NONE
        });
        takerIDs = new int[4];
        kills = new Chosen[2];
        defeatType = 0;
        others = new Forsaken[2];
        relationships = new Relationship[2];
        feelings = new int[2];
    }

    private static final long serialVersionUID = 4L;
    int textSize;
    String givenName;
    String familyName;
    Boolean filthyGaijin;
    Color textColor;
    Color darkColor;
    Gender originalGender;
    Gender gender;
    String incantation;
    String adjectiveName;
    String nounName;
    String mainName;
    String originalName;
    String topCover;
    String topAccess;
    String bottomCover;
    String bottomAccess;
    String underType;
    String color;
    String accessory;
    String weapon;
    String customWeaponType;
    String feetType;
    int number;
    int morality;
    int innocence;
    int confidence;
    int dignity;
    int hostility;
    int deviancy;
    int obedience;
    int disgrace;
    int stamina;
    int motivation;
    Boolean ruthless;
    int peopleInjured;
    int timesHadSex;
    int timesKilled;
    int demonicBirths;
    Boolean lustful;
    int orgasmsGiven;
    int timesOrgasmed;
    int strongestOrgasm;
    Boolean hypnotized;
    Boolean meek;
    int timesTortured;
    int timesHarmedSelf;
    Boolean drained;
    Boolean debased;
    int timesExposed;
    int timesExposedSelf;
    Boolean parasitized;
    Taker takers[];
    int takerIDs[];
    Chosen kills[];
    int defeatType;
    Forsaken others[];
    Relationship relationships[];
    int feelings[];
}
