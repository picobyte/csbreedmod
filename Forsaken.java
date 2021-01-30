
import java.awt.Color;
import java.io.PrintStream;
import java.io.Serializable;
import javax.swing.JTextPane;
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
            timesExposed += 0x493e0 + (int)(Math.random() * 300000D);
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
                if(!originalName.equals(mainName))
                    say(t, (new StringBuilder("My name is ")).append(originalName).append("!  And I won't respond to '").append(mainName).append("', no matter what you do to me.  ").toString());
                else
                    say(t, (new StringBuilder("You know who I am!  I'm ")).append(mainName).append(", but you're probably expecting me to say more than that.  ").toString());
            } else
            if(!originalName.equals(mainName))
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
                    say(t, "I always wanted to do stuff like this, but I let them trick me into being a hero...  Heheh, but not anymore...  ");
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
