
import java.awt.Color;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import javax.swing.JTextPane;
import javax.swing.text.*;

public class WorldState
    implements Serializable
{

    public Boolean isCheater()
    {
        return cheater;
    }

    public void setCheater()
    {
        cheater = Boolean.valueOf(true);
    }

    public void setHighScore(long newScore)
    {
        highScore = newScore;
    }

    public long getHighScore()
    {
        return highScore;
    }

    public void initializeTips()
    {
        tips = (new String[] {
            "The only randomized elements in this game are the initial personalities of the Chosen, some in-battle speech, and the order in which these tips are displayed.", "Upon completing Day 15 and getting scored, you can restart from Day 1 against the same group of Chosen in order to try for a better result.", "As long as at least one of the Chosen is surrounded or captured, the battle can't end.", "Every time one of the Chosen is surrounded or captured, her defense level goes up by 2.", "The number of rounds one of the Chosen remains surrounded is equal to her opening level at the moment she's surrounded, regardless of her defense level.", "The number of rounds one of the Chosen remains captured by your Commander depends on the Commander's upgrades, regardless of her opening level or defense level.", "When a Commander without any special abilities captures one of the Chosen, the target is surrounded by Thralls, but the duration of the surrounding is calculated as normal for a Commander capture.", "When one of the Chosen is surrounded or captured after the extermination has already reached 100%, then she cannot be surrounded or captured again for the rest of the battle.", "When one of the Chosen uses \"Rally,\" all trauma inflicted on the team is decreased by a flat percentage until her next turn.", "Only one Chosen at a time can use \"Rally.\"", 
            "When one of the Chosen uses \"Distract,\" all damage inflicted against surrounded Chosen is decreased by a flat percentage until her next turn.", "When multiple Chosen use \"Distract\" at the same time, their damage reduction percentages are added together to a maximum of 100% reduction.", "When \"Rally\" and \"Distract\" are active at once, both apply separately.  So, for example, if both are at 10%, surrounded Chosen take 90% circumstance damage and 81% trauma damage.", "Sometimes tormenting surrounded Chosen is less worthwhile than focusing on free Chosen whose circumstances are at higher levels.", "The traumas are FEAR, DISG, PAIN, and SHAM.  The circumstances are HATE, PLEA, INJU, and EXPO.", "When facing only a single Chosen, while she's captured by a Commander with a special ability, the player will have nothing useful to do each turn.", "Surrounded Chosen can only start using one countermeasure each turn.", "The first arrival of the second and third Chosen can only happen after a certain number of turns have happened during the playthrough.", "Breaking a Chosen's Major Vulnerability causes a confrontation between her and the Chosen with the corresponding Minor Vulnerability.  This confrontation will end in hostility unless the Minor Vulnerability has already been broken.", "During downtime, the Chosen prefer to perform activities that resolve their highest traumas.", 
            "During downtime, broken vulnerabilities allow the Chosen to perform more sinful activities.", "During downtime, the Chosen prefer not to perform more sinful activities unless their trauma and angst are high enough to require it.", "During downtime, Chosen who care more about right and wrong are less likely to perform more sinful activities.", "During downtime, Chosen who like each other more are more likely to perform the same activity together.", "During downtime, Chosen who like each other more benefit more from doing the same activity together.", "When two Chosen perform the same downtime activity, they resolve more trauma by doing so, even if they dislike each other.  When all three Chosen perform the same downtime activity, this effect becomes very strong.", "Chosen who are more naive are easily convinced to join other Chosen on the downtime activities they want to do.", "Chosen who are more confident will arrive to their allies' defense in battle more quickly.", "Chosen who fight at a distance are better at biding their time, resulting in fewer rounds elapsing before their allies arrive to help them.", "Chosen who like each other more arrive to each other's defense more quickly in battle.", 
            "During downtime, the Chosen can compromise on performing activities that none of them would perform on their own.", "Selectively corrupting one of the Chosen can make her want to perform actions that her allies aren't corrupt enough to join her on.", "The first step in corrupting one of the Chosen is generally to make her use sinful techniques to protect herself while surrounded.", "When captured by a Commander with a special ability, it is not possible for the Chosen to defend themselves.", "When the Thralls are instructed to torment a surrounded Chosen, they will continue to do so on their own until she escapes.", "Once the Chosen start using sinful methods to defend themselves, surrounding them will be less effective, but it will also be possible to extract more Evil Energy from them.", "A Chosen less vulnerable to a trauma will be more vulnerable to the associated circumstance, and vice versa.", "Compassionate Chosen hate their enemies less but are more fearful for their allies.", "Cruel Chosen hate their enemies more but are less fearful for their allies.", "Innocent Chosen have less interest in sex but are more prone to getting grossed out and disgusted.", 
            "Experienced Chosen have grown more accustomed to pleasure, but they're better at ignoring disgusting things.", "Prideful Chosen refuse to flinch away from pain, but they have the willpower to ignore being injured.", "Cowardly Chosen are careful to avoid pain, but they lack the willpower to ignore their injuries.", "Self-conscious Chosen feel more shame, but they're more careful to avoid getting stripped and exposed.", "Shameless Chosen care less about being humiliated, but they don't put much effort into avoiding getting stripped and exposed.", "After the evacuation has been completed, the extermination rate begins to increase exponentially.", "There are two types of damage: trauma and circumstance.", "Trauma inflicted during battle creates openings for the Chosen to be surrounded, but it decreases the damage received in the associated circumstance.", "Circumstances multiply the damage received by the Chosen (especially in the associated trauma), but they do not directly contribute to unresolved trauma.", "Chosen driven into a panic for their allies will stop listening to the hateful things said by their enemies.", 
            "Chosen who hate their enemies will feel greater fear for what those enemies will do.", "Chosen overwhelmed by disgust will be less receptive to pleasure.", "Chosen overwhelmed by pleasure in battle will be disgusted by that fact.", "Chosen who are in pain will be more careful to avoid getting further injured.", "Chosen who are already injured will feel more pain from being attacked again.", "Chosen who are feeling ashamed will be more careful to avoid getting exposed.", "Chosen who are exposed will feel more shame from all sources.", "The protective powers of the Chosen depend on their pure hearts, so a Chosen consumed by hate is more vulnerable in all respects.", "The more pleasure one of the Chosen feels, the deeper her trauma will be engraved in her memory.", "As the Chosen are injured, they become less able to defend themselves from other abuses.", 
            "When one Chosen is exposed and humiliated, it distracts and breaks the morale of the other Chosen on the battlefield.", "Every day, each Chosen's ANGST is increased by the trauma she hasn't successfully resolved yet.", "High ANGST makes the Chosen willing to perform sinful activities, and until it's resolved, the distraction makes them take more damage from all sources.", "Every doubling of ANGST increases the damage bonus by +1, so even a few hundred ANGST is much better than none at all.", "A Chosen's susceptibility to a damage type normally ranges from 0 to 100 based on personality.  The ANGST bonus is added to this value.", "A Chosen's susceptibility to a damage type ranges from 0 to 100 based on personality.  This base susceptibility to a trauma and to its associated circumstance normally adds up to 100, but corruption increases increases them both, potentially even over 100.", "More sinful actions produce a bit more Evil Energy, but they resolve trauma at an exponentially greater rate.", "The Chosen will only begin to use sinful methods to defend themselves if they expect to reach level 3 circumstance damage otherwise.", "Some sinful actions taken during battle will also damage their users.", "As the Chosen are corrupted, they will begin to use more sinful but also more effective versions of their abilities.", 
            "Fearful Chosen are more vulnerable when their allies are surrounded or captured.", "Disgusted Chosen are always more vulnerable, but being grossed out won't generally create a major opening on its own.", "Chosen in pain are more vulnerable for awhile, but after they get surrounded, the adrenaline allows them to shake it off until the pain reaches the next level.", "Ashamed Chosen aren't any more vulnerable to being surrounded, but their efforts to retain their modesty mean that they'll remain surrounded for longer.", "Damage which currently contributes to the opening level is displayed in purple text.  Damage which does not is displayed in black text.  Damage which is only partially contributing to the opening level is displayed in orange text.", "By using \"Regenerate\", one of the Chosen can remove a fraction of her current circumstance damage.  However, nothing done in battle can remove trauma damage that has already been dealt.", "By using \"Blast\", one of the Chosen can increase evacuation and extermination progress.  If evacuation is already complete, the progress that would be added there is wasted.", "The Chosen choose their actions in battle according to which actions would seem to be most useful at the moment and how effective they are at performing those actions.", "Taunting is more effective against self-conscious Chosen, especially those who have been humiliated in the past.", "Attacking is more effective against Chosen with low self-confidence, especially those whose pride has been broken in the past.", 
            "Sliming is more effective against more naive Chosen, especially those who have come to associate battle with sexual pleasure.", "Threatening allies is more effective against more compassionate Chosen, especially those whose consciences aren't clean.", "It isn't possible to raise a circumstance by more than one level with a single instance of damage.  This limitation does not apply to trauma.", "Each of the actions the Chosen can perform in battle is linked with one of the four vulnerabilities.  The Chosen are better at performing actions associated with their greater vulnerabilities.", "Chosen who are surrounded or captured do not contribute to extermination progress until they escape.", "When a surrounded Chosen uses a tactic that decreases the effectiveness of Grind, Caress, Pummel, or Humiliate, the damage from that source is decreased to 3/5.  When both tactics against the source are used at once, the damage becomes 2/5.", "The main benefit of Suppressor-class upgrades is that they ignore defensive tactics.  Against Chosen who have not yet begun to use any defensive tactics, a Commander without Suppressor-class upgrades can actually more effective."
        });
    }

    public void printTip(JTextPane t)
    {
        append(t, (new StringBuilder("\n\nTip: ")).append(tips[nextTip]).toString());
        for(int storage = nextTip; storage == nextTip; nextTip = (int)(Math.random() * (double)tips.length));
    }

    public void scoreSummary(JTextPane t)
    {
        long totalScore = 0L;
        underlineAppend(t, "Day Fifteen Score");
        append(t, "\n\nTotal ANGST: ");
        Chosen first = getCast()[0];
        Chosen second = getCast()[1];
        Chosen third = getCast()[2];
        long angstBonus = getCast()[0].getANGST() + getCast()[1].getANGST() + getCast()[2].getANGST();
        append(t, (new StringBuilder(String.valueOf(first.condensedFormat(getCast()[0].getANGST())))).append(" (").append(getCast()[0].getMainName()).append(") + ").append(second.condensedFormat(getCast()[1].getANGST())).append(" (").append(getCast()[1].getMainName()).append(") + ").append(third.condensedFormat(getCast()[2].getANGST())).append(" (").append(getCast()[2].getMainName()).append(") = ").append(first.condensedFormat(angstBonus)).append(" pts\n\nUnresolved Trauma: ").toString());
        totalScore += angstBonus;
        long firstTrauma = getCast()[0].getTotalFEAR() + getCast()[0].getTotalDISG() + getCast()[0].getTotalPAIN() + getCast()[0].getTotalSHAM();
        long secondTrauma = getCast()[1].getTotalFEAR() + getCast()[1].getTotalDISG() + getCast()[1].getTotalPAIN() + getCast()[1].getTotalSHAM();
        long thirdTrauma = getCast()[2].getTotalFEAR() + getCast()[2].getTotalDISG() + getCast()[2].getTotalPAIN() + getCast()[2].getTotalSHAM();
        long traumaBonus = (firstTrauma + secondTrauma + thirdTrauma) * 5L;
        append(t, (new StringBuilder(String.valueOf(first.condensedFormat(firstTrauma)))).append(" (").append(first.getMainName()).append(") + ").append(second.condensedFormat(secondTrauma)).append(" (").append(second.getMainName()).append(") + ").append(third.condensedFormat(thirdTrauma)).append(" (").append(third.getMainName()).append(") x 5 = ").append(first.condensedFormat(traumaBonus)).append(" pts\n\nRemaining Evil Energy: ").toString());
        totalScore += traumaBonus;
        long EEBonus = 0x186a0 * evilEnergy;
        append(t, (new StringBuilder(String.valueOf(evilEnergy))).append(" x ").append(first.condensedFormat(0x186a0L)).append(" = ").append(first.condensedFormat(EEBonus)).append("\n").toString());
        totalScore += EEBonus;
        long corruptionBonus = 0L;
        for(int i = 0; i < 3; i++)
        {
            Chosen c = getCast()[i];
            long cCorruption = 0L;
            append(t, (new StringBuilder("\n")).append(c.getMainName()).append("'s Corruption: ").toString());
            if((!c.isVVirg() || c.isRuthless()) && c.vStart.booleanValue())
            {
                long added = 0L;
                if(c.isRuthless())
                    if(c.getMorality() > 66)
                    {
                        long amount = 0xf4240L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getMorality() > 33)
                    {
                        long amount = 0x61a80L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x30d40L;
                        cCorruption += amount;
                        added += amount;
                    }
                if(!c.isVVirg())
                    if(c.getMorality() > 66)
                    {
                        long amount = 0x5e69ec0L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getMorality() > 33)
                    {
                        long amount = 0x3c6cc0L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x1e3660L;
                        cCorruption += amount;
                        added += amount;
                    }
                append(t, (new StringBuilder(String.valueOf(c.condensedFormat(added)))).append(" (Morality)").toString());
            }
            if((!c.isCVirg() || c.isLustful()) && c.cStart.booleanValue())
            {
                if(cCorruption > 0L)
                    append(t, " + ");
                long added = 0L;
                if(c.isLustful())
                    if(c.getInnocence() > 66)
                    {
                        long amount = 0xf4240L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getInnocence() > 33)
                    {
                        long amount = 0x61a80L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x30d40L;
                        cCorruption += amount;
                        added += amount;
                    }
                if(!c.isCVirg())
                    if(c.getInnocence() > 66)
                    {
                        long amount = 0x5e69ec0L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getInnocence() > 33)
                    {
                        long amount = 0x25c3f80L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x12e1fc0L;
                        cCorruption += amount;
                        added += amount;
                    }
                append(t, (new StringBuilder(String.valueOf(c.condensedFormat(added)))).append(" (Innocence)").toString());
            }
            if((!c.isAVirg() || c.isMeek()) && c.aStart.booleanValue())
            {
                if(cCorruption > 0L)
                    append(t, " + ");
                long added = 0L;
                if(c.isMeek())
                    if(c.getConfidence() > 66)
                    {
                        long amount = 0xf4240L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getConfidence() > 33)
                    {
                        long amount = 0x61a80L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x30d40L;
                        cCorruption += amount;
                        added += amount;
                    }
                if(!c.isAVirg())
                    if(c.getConfidence() > 66)
                    {
                        long amount = 0x5e69ec0L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getConfidence() > 33)
                    {
                        long amount = 0x25c3f80L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x12e1fc0L;
                        cCorruption += amount;
                        added += amount;
                    }
                append(t, (new StringBuilder(String.valueOf(c.condensedFormat(added)))).append(" (Confidence)").toString());
            }
            if((!c.isModest() || c.isDebased()) && c.mStart.booleanValue())
            {
                if(cCorruption > 0L)
                    append(t, " + ");
                long added = 0L;
                if(c.isDebased())
                    if(c.getDignity() > 66)
                    {
                        long amount = 0xf4240L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getDignity() > 33)
                    {
                        long amount = 0x61a80L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x30d40L;
                        cCorruption += amount;
                        added += amount;
                    }
                if(!c.isModest())
                    if(c.getDignity() > 66)
                    {
                        long amount = 0x5e69ec0L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getDignity() > 33)
                    {
                        long amount = 0x25c3f80L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x12e1fc0L;
                        cCorruption += amount;
                        added += amount;
                    }
                append(t, (new StringBuilder(String.valueOf(c.condensedFormat(added)))).append(" (Dignity)").toString());
            }
            if(cCorruption > 0L)
                append(t, (new StringBuilder(" = ")).append(c.condensedFormat(cCorruption)).append(" pts").toString());
            else
                append(t, "0 pts");
            corruptionBonus += cCorruption;
        }

        totalScore += corruptionBonus;
        long enmityBonus = 0L;
        append(t, "\n\nEnmity Bonus: ");
        for(int i = 0; i < 2; i++)
        {
            for(int j = i + 1; j < 3; j++)
                if(getRelationship(i, j) < 0)
                {
                    if(enmityBonus > 0L)
                        append(t, " + ");
                    append(t, (new StringBuilder(String.valueOf(first.condensedFormat(0x17d7840L)))).append(" (").append(getCast()[i].getMainName()).append(" vs. ").append(getCast()[j].getMainName()).append(")").toString());
                    enmityBonus += 0x17d7840L;
                }

        }

        if(enmityBonus > 0L)
            append(t, (new StringBuilder(" = ")).append(first.condensedFormat(enmityBonus)).toString());
        else
            append(t, "0 pts");
        totalScore += enmityBonus;
        append(t, (new StringBuilder("\n\nTotal:\n\n ")).append(first.fixedFormat(angstBonus)).append(" (ANGST)\n+").append(first.fixedFormat(traumaBonus)).append(" (Trauma)\n+").append(first.fixedFormat(EEBonus)).append(" (Evil Energy)\n+").append(first.fixedFormat(corruptionBonus)).append(" (Corruption)\n").toString());
        underlineAppend(t, (new StringBuilder("+")).append(first.fixedFormat(enmityBonus)).append(" (Enmity)").toString());
        append(t, (new StringBuilder("\n ")).append(first.fixedFormat(totalScore)).append(" pts\n\nThank you for playing this development version of Corrupted Saviors!  You may continue to play against this team in order to explore more of the corruption content, and a new button has been added to the shop which allows you to cheat in more Evil Energy!  ").toString());
        if(totalScore > highScore)
            highScore = totalScore;
        try
        {
            String path = Project.getProtectionDomain().getCodeSource().getLocation().getPath();
            String fileName = "";
            for(int i = path.length() - 1; i >= 0; i--)
                if(path.charAt(i) != '/')
                    fileName = (new StringBuilder(String.valueOf(path.charAt(i)))).append(fileName).toString();
                else
                    i = -1;

            path = path.substring(0, path.length() - fileName.length() - 1);
            path = URLDecoder.decode(path, "UTF-8");
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
            WriteObject wobj = new WriteObject();
            WorldState newWorld = new WorldState();
            newWorld.copyInitial(this);
            newWorld.setHighScore(highScore);
            Chosen newChosen = new Chosen();
            newChosen.setName(newWorld, newWorld.getNameSeed());
            newChosen.generate(newWorld);
            newWorld.addChosen(newChosen);
            newChosen.setNumber(0);
            saves.endSave(newWorld, "New Game+");
            wobj.serializeSaveData(saves);
            append(t, (new StringBuilder("A new save file has been added in slot ")).append(saves.getSaves().length).append(" which allows you to go back and start from Day 1 against this same group of Chosen.  ").toString());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        if(totalScore < 0x186a0L)
            append(t, "Try tormenting surrounded Chosen in order to apply circumstances that multiply the inflicted trauma!");
        else
        if(totalScore < 0xf4240L)
            append(t, "Try surrounding or capturing one of the Chosen just before the end of the battle in order to buy more time before the end of the day - and then doing the same to another Chosen when the first is about to escape!");
        else
        if(totalScore < 0x989680L)
            append(t, "Try getting a few Commander upgrades, then aggressively spending your EE on the Commander in order to more quickly traumatize the Chosen to the point of committing greater sins!");
        else
            append(t, "Try for an even higher score!");
    }

    public int[] getBreaks()
    {
        return pendingBreaks;
    }

    public void addBreak(int type)
    {
        int newBreaks[] = new int[pendingBreaks.length + 1];
        for(int i = 0; i < pendingBreaks.length; i++)
            newBreaks[i] = pendingBreaks[i];

        newBreaks[newBreaks.length - 1] = type;
        pendingBreaks = newBreaks;
    }

    public void resolveBreak()
    {
        int newBreaks[] = new int[pendingBreaks.length - 1];
        for(int i = 0; i < newBreaks.length; i++)
            newBreaks[i] = pendingBreaks[i + 1];

        pendingBreaks = newBreaks;
    }

    public Boolean commanderFree()
    {
        Boolean conclusion = Boolean.valueOf(true);
        for(int i = 0; i < 3; i++)
            if(getCombatants()[i] != null && getCombatants()[i].isCaptured().booleanValue())
                conclusion = Boolean.valueOf(false);

        return conclusion;
    }

    public void applyVersatility()
    {
        bodyStatus[10] = Boolean.valueOf(true);
    }

    public void applyMania()
    {
        bodyStatus[6] = Boolean.valueOf(true);
    }

    public void applyAnger()
    {
        bodyStatus[5] = Boolean.valueOf(true);
    }

    public void applyLust()
    {
        bodyStatus[4] = Boolean.valueOf(true);
    }

    public void applyHunger()
    {
        bodyStatus[3] = Boolean.valueOf(true);
    }

    public void addCaptureOne()
    {
        evilEnergy -= 2;
        bodyStatus[8] = Boolean.valueOf(true);
    }

    public void toggleAmbush()
    {
        bodyStatus[2] = Boolean.valueOf(!bodyStatus[2].booleanValue());
    }

    public void enhanceThree()
    {
        evilEnergy -= 2;
        bodyStatus[9] = Boolean.valueOf(true);
    }

    public void enhanceTwo()
    {
        evilEnergy--;
        bodyStatus[7] = Boolean.valueOf(true);
    }

    public void enhanceOne()
    {
        evilEnergy--;
        bodyStatus[1] = Boolean.valueOf(true);
    }

    public Boolean spendCapture()
    {
        capturesPossible--;
        if(capturesPossible > 0)
            return Boolean.valueOf(true);
        else
            return Boolean.valueOf(false);
    }

    public int getCapturesPossible()
    {
        return capturesPossible;
    }

    Boolean validLine(int test)
    {
        if(test != lastLine && test != lastLastLine && test > 0)
            return Boolean.valueOf(true);
        else
            return Boolean.valueOf(false);
    }

    public void chatter(JTextPane t)
    {
        int attempts = 0;
        Chosen nextSpeaker = null;
        Chosen target = nextSpeaker;
        int nextLine;
        for(nextLine = lastLine; attempts < 20 && !validLine(nextLine).booleanValue();)
        {
            attempts++;
            for(; nextSpeaker == null; nextSpeaker = currentCombatants[(int)(Math.random() * 3D)]);
            for(; target == nextSpeaker || target == null; target = currentCombatants[(int)(Math.random() * 3D)]);
            if(target.isSurrounded().booleanValue() || target.isCaptured().booleanValue())
            {
                if(!target.getViolence().booleanValue() && !target.getService().booleanValue() && !target.getBeg().booleanValue() && !target.getCover().booleanValue() && validLine(5).booleanValue())
                    nextLine = 5;
                else
                if(target.getViolence().booleanValue() && validLine(6).booleanValue())
                    nextLine = 6;
                else
                if(target.getService().booleanValue() && validLine(7).booleanValue())
                    nextLine = 7;
                else
                if(target.getBeg().booleanValue() && validLine(8).booleanValue())
                    nextLine = 8;
                else
                if(target.getCover().booleanValue() && validLine(9).booleanValue())
                    nextLine = 9;
            } else
            if(validLine(target.getLastAction()).booleanValue())
                nextLine = target.getLastAction();
            if(getRelationship(nextSpeaker.getNumber(), target.getNumber()) >= 0)
            {
                if(nextLine == 1)
                {
                    if(nextSpeaker.getMoralityBreakage() < target.getMoralityBreakage())
                        nextLine = lastLine;
                } else
                if(nextLine == 2)
                {
                    if(nextSpeaker.getInnocenceBreakage() < target.getInnocenceBreakage())
                        nextLine = lastLine;
                } else
                if(nextLine == 3)
                {
                    if(nextSpeaker.getConfidenceBreakage() < target.getConfidenceBreakage())
                        nextLine = lastLine;
                } else
                if(nextLine == 4)
                {
                    if(nextSpeaker.getDignityBreakage() < target.getDignityBreakage())
                        nextLine = lastLine;
                } else
                if(nextLine == 6)
                {
                    if(nextSpeaker.getMoralityBreakage() < 1)
                        nextLine = lastLine;
                } else
                if(nextLine == 7)
                {
                    if(nextSpeaker.getInnocenceBreakage() < 1)
                        nextLine = lastLine;
                } else
                if(nextLine == 8)
                {
                    if(nextSpeaker.getConfidenceBreakage() < 1)
                        nextLine = lastLine;
                } else
                if(nextLine == 9 && nextSpeaker.getDignityBreakage() < 1)
                    nextLine = lastLine;
            } else
            if(nextLine == 1 || nextLine == 6)
            {
                if(nextSpeaker.getMoralityBreakage() >= target.getMoralityBreakage())
                    nextLine = lastLine;
                else
                if(nextLine == 6 && nextSpeaker.getMoralityBreakage() > 0)
                    nextLine = lastLine;
            } else
            if(nextLine == 2 || nextLine == 7)
            {
                if(nextSpeaker.getInnocenceBreakage() >= target.getInnocenceBreakage())
                    nextLine = lastLine;
                else
                if(nextLine == 7 && nextSpeaker.getInnocenceBreakage() > 0)
                    nextLine = lastLine;
            } else
            if(nextLine == 3 || nextLine == 8)
            {
                if(nextSpeaker.getConfidenceBreakage() >= target.getConfidenceBreakage())
                    nextLine = lastLine;
                else
                if(nextLine == 8 && nextSpeaker.getConfidenceBreakage() > 0)
                    nextLine = lastLine;
            } else
            if(nextLine == 4 || nextLine == 9)
                if(nextSpeaker.getDignityBreakage() >= target.getDignityBreakage())
                    nextLine = lastLine;
                else
                if(nextLine == 9 && nextSpeaker.getDignityBreakage() > 0)
                    nextLine = lastLine;
            if(!validLine(nextLine).booleanValue())
                if(target.getHATELevel() > nextSpeaker.getHATELevel() && target.getHATELevel() >= target.getPLEALevel() && target.getHATELevel() >= target.getINJULevel() && target.getHATELevel() >= target.getEXPOLevel() && validLine(10).booleanValue())
                    nextLine = 10;
                else
                if(target.getPLEALevel() > nextSpeaker.getPLEALevel() && target.getPLEALevel() >= target.getHATELevel() && target.getPLEALevel() >= target.getINJULevel() && target.getPLEALevel() >= target.getEXPOLevel() && validLine(11).booleanValue())
                    nextLine = 11;
                else
                if(target.getINJULevel() > nextSpeaker.getINJULevel() && target.getINJULevel() >= target.getHATELevel() && target.getINJULevel() >= target.getPLEALevel() && target.getINJULevel() >= target.getEXPOLevel() && validLine(12).booleanValue())
                    nextLine = 12;
                else
                if(target.getEXPOLevel() > nextSpeaker.getEXPOLevel() && target.getEXPOLevel() >= target.getHATELevel() && target.getEXPOLevel() >= target.getPLEALevel() && target.getEXPOLevel() >= target.getINJULevel() && validLine(13).booleanValue())
                    nextLine = 13;
            if(!validLine(nextLine).booleanValue())
                if(nextSpeaker.getFEARLevel() > target.getFEARLevel() && nextSpeaker.getFEARLevel() >= nextSpeaker.getDISGLevel() && nextSpeaker.getFEARLevel() >= nextSpeaker.getPAINLevel() && nextSpeaker.getFEARLevel() >= nextSpeaker.getSHAMLevel() && validLine(14).booleanValue())
                    nextLine = 14;
                else
                if(nextSpeaker.getSHAMLevel() > target.getSHAMLevel() && nextSpeaker.getSHAMLevel() >= nextSpeaker.getFEARLevel() && nextSpeaker.getSHAMLevel() >= nextSpeaker.getDISGLevel() && nextSpeaker.getSHAMLevel() >= nextSpeaker.getPAINLevel() && validLine(17).booleanValue())
                {
                    nextLine = 17;
                    if(target.isSurrounded().booleanValue() || target.isCaptured().booleanValue())
                        nextLine = lastLine;
                } else
                if(nextSpeaker.getDISGLevel() > target.getDISGLevel() && nextSpeaker.getDISGLevel() >= nextSpeaker.getFEARLevel() && nextSpeaker.getDISGLevel() >= nextSpeaker.getPAINLevel() && nextSpeaker.getDISGLevel() >= nextSpeaker.getSHAMLevel() && validLine(15).booleanValue())
                {
                    nextLine = 15;
                    if(target.isSurrounded().booleanValue() || target.isCaptured().booleanValue())
                        nextLine = lastLine;
                } else
                if(nextSpeaker.getPAINLevel() > target.getPAINLevel() && nextSpeaker.getPAINLevel() >= nextSpeaker.getFEARLevel() && nextSpeaker.getPAINLevel() >= nextSpeaker.getDISGLevel() && nextSpeaker.getPAINLevel() >= nextSpeaker.getSHAMLevel() && validLine(16).booleanValue())
                {
                    nextLine = 15;
                    if(target.isSurrounded().booleanValue() || target.isCaptured().booleanValue())
                        nextLine = lastLine;
                }
        }

        if(nextSpeaker.isSurrounded().booleanValue() || nextSpeaker.isCaptured().booleanValue())
            nextLine = lastLine;
        if(!nextSpeaker.isIntroduced().booleanValue() || !target.isIntroduced().booleanValue())
            nextLine = lastLine;
        if(validLine(nextLine).booleanValue())
        {
            nextSpeaker.say(t, "\n\n\"");
            if(getRelationship(nextSpeaker.getNumber(), target.getNumber()) >= 0)
            {
                if(nextLine == 1)
                {
                    if(target.getMoralityBreakage() == 2)
                    {
                        if(nextSpeaker.getMorality() > 66)
                        {
                            if(nextSpeaker.getInnocence() > 66)
                                nextSpeaker.say(t, (new StringBuilder("They're bad guys, so you can go all out, ")).append(target.getMainName()).append("!").toString());
                            else
                            if(nextSpeaker.getInnocence() > 33)
                                nextSpeaker.say(t, (new StringBuilder("Punish them, ")).append(target.getMainName()).append("!").toString());
                            else
                                nextSpeaker.say(t, (new StringBuilder("You're completely justified, ")).append(target.getMainName()).append(".").toString());
                        } else
                        if(nextSpeaker.getMorality() > 33)
                        {
                            if(nextSpeaker.getInnocence() > 66)
                                nextSpeaker.say(t, (new StringBuilder("Hooray!  Good job, ")).append(target.getMainName()).append("!").toString());
                            else
                            if(nextSpeaker.getInnocence() > 33)
                                nextSpeaker.say(t, (new StringBuilder("The Thralls aren't innocent either, ")).append(target.getMainName()).append("!").toString());
                            else
                                nextSpeaker.say(t, (new StringBuilder("Best not to hold back, ")).append(target.getMainName()).append(".").toString());
                        } else
                        if(nextSpeaker.getInnocence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("Yes!  Hurt them more, ")).append(target.getMainName()).append("!").toString());
                        else
                        if(nextSpeaker.getInnocence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Yes, ")).append(target.getMainName()).append("...  Make them suffer.").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Good, ")).append(target.getMainName()).append("!  The more they suffer now, the less they'll bother us later!").toString());
                    } else
                    if(target.getMoralityBreakage() == 1)
                    {
                        if(nextSpeaker.getMorality() > 66)
                        {
                            if(nextSpeaker.getInnocence() > 66)
                                nextSpeaker.say(t, (new StringBuilder("It's their fault for messing with you, ")).append(target.getMainName()).append("!").toString());
                            else
                            if(nextSpeaker.getInnocence() > 33)
                                nextSpeaker.say(t, (new StringBuilder("Better in the long run to finish this quickly, ")).append(target.getMainName()).append(".  Even if it means being a little rough with them.").toString());
                            else
                                nextSpeaker.say(t, (new StringBuilder("You're well within acceptable limits of force, ")).append(target.getMainName()).append(".").toString());
                        } else
                        if(nextSpeaker.getMorality() > 33)
                        {
                            if(nextSpeaker.getInnocence() > 66)
                                nextSpeaker.say(t, (new StringBuilder("Your fighting style is reall cool, ")).append(target.getMainName()).append("!").toString());
                            else
                            if(nextSpeaker.getInnocence() > 33)
                                nextSpeaker.say(t, (new StringBuilder("Just take care of them, ")).append(target.getMainName()).append("!").toString());
                            else
                                nextSpeaker.say(t, (new StringBuilder("Very efficient, ")).append(target.getMainName()).append("!").toString());
                        } else
                        if(nextSpeaker.getInnocence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("Nothing wrong with having some fun with them, ")).append(target.getMainName()).append("!").toString());
                        else
                        if(nextSpeaker.getInnocence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("You're so gentle with them, ")).append(target.getMainName()).append(".").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Eliminate them as quickly as possible, ")).append(target.getMainName()).append(".").toString());
                    } else
                    if(nextSpeaker.getMorality() > 66)
                    {
                        if(nextSpeaker.getConfidence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("Well done, ")).append(target.getMainName()).append("!").toString());
                        else
                        if(nextSpeaker.getConfidence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("You're actually pretty strong, ")).append(target.getMainName()).append("!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("I'm so glad you're fighting with us, ")).append(target.getMainName()).append("...").toString());
                    } else
                    if(nextSpeaker.getMorality() > 33)
                    {
                        if(nextSpeaker.getConfidence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("You're almost as strong as me, ")).append(target.getMainName()).append("!").toString());
                        else
                        if(nextSpeaker.getConfidence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Great job, ")).append(target.getMainName()).append("!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("I wish I were as strong as you, ")).append(target.getMainName()).append("...").toString());
                    } else
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("Good, ")).append(target.getMainName()).append("!  You handle the small fry!").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("Not bad, ")).append(target.getMainName()).append(".").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("How are you so good at this, ")).append(target.getMainName()).append("?").toString());
                } else
                if(nextLine == 2)
                {
                    if(target.getInnocenceBreakage() == 2)
                    {
                        if(nextSpeaker.getInnocence() > 66)
                        {
                            if(nextSpeaker.getDignity() > 66)
                                nextSpeaker.say(t, (new StringBuilder("What's making you feel so good, ")).append(target.getMainName()).append("?").toString());
                            else
                            if(nextSpeaker.getDignity() > 33)
                                nextSpeaker.say(t, (new StringBuilder("You look like you're feeling really good, ")).append(target.getMainName()).append("!").toString());
                            else
                                nextSpeaker.say(t, (new StringBuilder("Ah, ")).append(target.getMainName()).append("...  I wanna feel good too!").toString());
                        } else
                        if(nextSpeaker.getInnocence() > 33)
                        {
                            if(nextSpeaker.getDignity() > 66)
                                nextSpeaker.say(t, (new StringBuilder("You do whatever you need to in order to get by, ")).append(target.getMainName()).append(".").toString());
                            else
                            if(nextSpeaker.getDignity() > 33)
                                nextSpeaker.say(t, (new StringBuilder("You're really enjoying yourself, ")).append(target.getMainName()).append("...").toString());
                            else
                                nextSpeaker.say(t, (new StringBuilder("I'm a little envious, ")).append(target.getMainName()).append("...").toString());
                        } else
                        if(nextSpeaker.getDignity() > 66)
                            nextSpeaker.say(t, (new StringBuilder("There's no reason not to enjoy the way it feels, ")).append(target.getMainName()).append(".").toString());
                        else
                        if(nextSpeaker.getDignity() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Take this chance to enjoy yourself, ")).append(target.getMainName()).append("!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("The energy ")).append(target.getMainName()).append(" is drawing is making me feel a bit amorous as well...").toString());
                    } else
                    if(target.getInnocenceBreakage() == 1)
                    {
                        if(nextSpeaker.getInnocence() > 66)
                        {
                            if(nextSpeaker.getDignity() > 66)
                                nextSpeaker.say(t, (new StringBuilder("What's making you look so happy, ")).append(target.getMainName()).append("!").toString());
                            else
                            if(nextSpeaker.getDignity() > 33)
                                nextSpeaker.say(t, (new StringBuilder("You look really happy for some reason, ")).append(target.getMainName()).append("!").toString());
                            else
                                nextSpeaker.say(t, (new StringBuilder("I wish I was as happy as ")).append(target.getMainName()).append("...").toString());
                        } else
                        if(nextSpeaker.getInnocence() > 33)
                        {
                            if(nextSpeaker.getDignity() > 66)
                                nextSpeaker.say(t, (new StringBuilder("You're looking better, ")).append(target.getMainName()).append(".  Happier, too.").toString());
                            else
                            if(nextSpeaker.getDignity() > 33)
                                nextSpeaker.say(t, (new StringBuilder("It does feel good, doesn't it, ")).append(target.getMainName()).append("?").toString());
                            else
                                nextSpeaker.say(t, (new StringBuilder("I'm a little bit addicted to that feeling, too, ")).append(target.getMainName()).append(".").toString());
                        } else
                        if(nextSpeaker.getDignity() > 66)
                            nextSpeaker.say(t, (new StringBuilder("Just be happy, ")).append(target.getMainName()).append("!").toString());
                        else
                        if(nextSpeaker.getDignity() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Might as well enjoy the feeling, ")).append(target.getMainName()).append(".").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("It's an interesting side-effect, isn't it, ")).append(target.getMainName()).append("?").toString());
                    } else
                    if(nextSpeaker.getInnocence() > 66)
                    {
                        if(nextSpeaker.getDignity() > 66)
                            nextSpeaker.say(t, (new StringBuilder("We'll hold them off, ")).append(target.getMainName()).append("!").toString());
                        else
                        if(nextSpeaker.getDignity() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Are you feeling better, ")).append(target.getMainName()).append("?").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Hooray!  ")).append(target.getMainName()).append(" is looking better!").toString());
                    } else
                    if(nextSpeaker.getInnocence() > 33)
                    {
                        if(nextSpeaker.getDignity() > 66)
                            nextSpeaker.say(t, (new StringBuilder("Take your time, ")).append(target.getMainName()).append(".").toString());
                        else
                        if(nextSpeaker.getDignity() > 33)
                            nextSpeaker.say(t, (new StringBuilder("How are you holding up, ")).append(target.getMainName()).append("?").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Alright, ")).append(target.getMainName()).append(" is back in action!").toString());
                    } else
                    if(nextSpeaker.getDignity() > 66)
                        nextSpeaker.say(t, (new StringBuilder("It would be unwise to rush, ")).append(target.getMainName()).append(".").toString());
                    else
                    if(nextSpeaker.getDignity() > 33)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("'s recovery will enhance our combat prowess significantly.").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("Is your recovery going smoothly, ")).append(target.getMainName()).append("?").toString());
                } else
                if(nextLine == 3)
                {
                    if(target.getConfidenceBreakage() == 2)
                    {
                        if(nextSpeaker.getConfidence() > 66)
                        {
                            if(nextSpeaker.getDignity() > 66)
                                nextSpeaker.say(t, (new StringBuilder("I won't waste this chance, ")).append(target.getMainName()).append(".").toString());
                            else
                            if(nextSpeaker.getDignity() > 33)
                                nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("!?  What do you think you're doing!?").toString());
                            else
                                nextSpeaker.say(t, (new StringBuilder("No!  ")).append(target.getMainName()).append("!  Come back right now!").toString());
                        } else
                        if(nextSpeaker.getConfidence() > 33)
                        {
                            if(nextSpeaker.getDignity() > 66)
                                nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("...  Understood!").toString());
                            else
                            if(nextSpeaker.getDignity() > 33)
                                nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("!?  What are you doing!?").toString());
                            else
                                nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("!  Stop!  Come back!").toString());
                        } else
                        if(nextSpeaker.getDignity() > 66)
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName().substring(0, 1)))).append("-").append(target.getMainName()).append("...  I won't cry!").toString());
                        else
                        if(nextSpeaker.getDignity() > 33)
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", you're going to get yourself killed!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("!  N-No!").toString());
                    } else
                    if(target.getConfidenceBreakage() == 1)
                    {
                        if(nextSpeaker.getConfidence() > 66)
                        {
                            if(nextSpeaker.getDignity() > 66)
                                nextSpeaker.say(t, (new StringBuilder("You've made a fine opening, ")).append(target.getMainName()).append("!").toString());
                            else
                            if(nextSpeaker.getDignity() > 33)
                                nextSpeaker.say(t, (new StringBuilder("You're pretty bold, ")).append(target.getMainName()).append("!").toString());
                            else
                                nextSpeaker.say(t, (new StringBuilder("Be less reckless, ")).append(target.getMainName()).append("!").toString());
                        } else
                        if(nextSpeaker.getConfidence() > 33)
                        {
                            if(nextSpeaker.getDignity() > 66)
                                nextSpeaker.say(t, (new StringBuilder("Thanks, ")).append(target.getMainName()).append("!").toString());
                            else
                            if(nextSpeaker.getDignity() > 33)
                                nextSpeaker.say(t, (new StringBuilder("It'll be hard to follow that up, ")).append(target.getMainName()).append("...").toString());
                            else
                                nextSpeaker.say(t, (new StringBuilder("Careful, ")).append(target.getMainName()).append("!").toString());
                        } else
                        if(nextSpeaker.getDignity() > 66)
                            nextSpeaker.say(t, (new StringBuilder("Y-You're so brave, ")).append(target.getMainName()).append("...").toString());
                        else
                        if(nextSpeaker.getDignity() > 33)
                            nextSpeaker.say(t, (new StringBuilder("W-Wow, ")).append(target.getMainName()).append("!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("!  A-Are you alright?").toString());
                    } else
                    if(nextSpeaker.getConfidence() > 66)
                    {
                        if(nextSpeaker.getDignity() > 66)
                            nextSpeaker.say(t, (new StringBuilder("Impressive, ")).append(target.getMainName()).append(".").toString());
                        else
                        if(nextSpeaker.getDignity() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Keep it up, ")).append(target.getMainName()).append("!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Looking good, ")).append(target.getMainName()).append("!").toString());
                    } else
                    if(nextSpeaker.getConfidence() > 33)
                    {
                        if(nextSpeaker.getDignity() > 66)
                            nextSpeaker.say(t, (new StringBuilder("I'll follow you anywhere, ")).append(target.getMainName()).append(".").toString());
                        else
                        if(nextSpeaker.getDignity() > 33)
                            nextSpeaker.say(t, (new StringBuilder("I'm right behind you, ")).append(target.getMainName()).append("!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Lead the way, ")).append(target.getMainName()).append("!").toString());
                    } else
                    if(nextSpeaker.getDignity() > 66)
                        nextSpeaker.say(t, (new StringBuilder("Th-Thanks, ")).append(target.getMainName()).append(", I feel much better now.").toString());
                    else
                    if(nextSpeaker.getDignity() > 33)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("!  I-I'm right behind you!").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("D-Don't worry, ")).append(target.getMainName()).append(", I'm done panicking...").toString());
                } else
                if(nextLine == 4)
                {
                    if(target.getDignityBreakage() == 2)
                    {
                        if(nextSpeaker.getDignity() > 66)
                        {
                            if(nextSpeaker.getInnocence() > 66)
                                nextSpeaker.say(t, (new StringBuilder("You're so pretty, ")).append(target.getMainName()).append("...").toString());
                            else
                            if(nextSpeaker.getInnocence() > 33)
                                nextSpeaker.say(t, (new StringBuilder("The Thralls are entranced by you, ")).append(target.getMainName()).append("!").toString());
                            else
                                nextSpeaker.say(t, (new StringBuilder("Excellent tactic, ")).append(target.getMainName()).append(".").toString());
                        } else
                        if(nextSpeaker.getDignity() > 33)
                        {
                            if(nextSpeaker.getInnocence() > 66)
                                nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" is... really sexy...").toString());
                            else
                            if(nextSpeaker.getInnocence() > 33)
                                nextSpeaker.say(t, (new StringBuilder("I can see why the Thralls are so distracted, ")).append(target.getMainName()).append("...").toString());
                            else
                                nextSpeaker.say(t, (new StringBuilder("Clever, ")).append(target.getMainName()).append("...").toString());
                        } else
                        if(nextSpeaker.getInnocence() > 66)
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("!  I wanna see more!").toString());
                        else
                        if(nextSpeaker.getInnocence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Those Thralls really want to fuck you, ")).append(target.getMainName()).append(".").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("...  I believe your tactic is working on me as well.").toString());
                    } else
                    if(target.getDignityBreakage() == 1)
                    {
                        if(nextSpeaker.getDignity() > 66)
                        {
                            if(nextSpeaker.getInnocence() > 66)
                                nextSpeaker.say(t, (new StringBuilder("You look wonderful, ")).append(target.getMainName()).append("!").toString());
                            else
                            if(nextSpeaker.getInnocence() > 33)
                                nextSpeaker.say(t, (new StringBuilder("The Thralls are completely captivated, ")).append(target.getMainName()).append(".").toString());
                            else
                                nextSpeaker.say(t, (new StringBuilder("Very effective, ")).append(target.getMainName()).append(".").toString());
                        } else
                        if(nextSpeaker.getDignity() > 33)
                        {
                            if(nextSpeaker.getInnocence() > 66)
                                nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" is really amazing...").toString());
                            else
                            if(nextSpeaker.getInnocence() > 33)
                                nextSpeaker.say(t, (new StringBuilder("I need to be careful that I don't get distracted by ")).append(target.getMainName()).append(" too...").toString());
                            else
                                nextSpeaker.say(t, (new StringBuilder("Good idea, ")).append(target.getMainName()).append(".").toString());
                        } else
                        if(nextSpeaker.getInnocence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("I could watch ")).append(target.getMainName()).append(" all day...").toString());
                        else
                        if(nextSpeaker.getInnocence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Ah, looks like ")).append(target.getMainName()).append(" is their next target...").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("You're far too good at that, ")).append(target.getMainName()).append("...").toString());
                    } else
                    if(nextSpeaker.getDignity() > 66)
                    {
                        if(nextSpeaker.getInnocence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("You're amazing, ")).append(target.getMainName()).append("...").toString());
                        else
                        if(nextSpeaker.getInnocence() > 33)
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" looks magnificent...").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Very heroic of you, ")).append(target.getMainName()).append(".").toString());
                    } else
                    if(nextSpeaker.getDignity() > 33)
                    {
                        if(nextSpeaker.getInnocence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("You're so cool, ")).append(target.getMainName()).append(".").toString());
                        else
                        if(nextSpeaker.getInnocence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Stylish, ")).append(target.getMainName()).append(".").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("You cut an impressive figure, ")).append(target.getMainName()).append(".").toString());
                    } else
                    if(nextSpeaker.getInnocence() > 66)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("!  Awesome!").toString());
                    else
                    if(nextSpeaker.getInnocence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("Looking good, ")).append(target.getMainName()).append("!").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("Well done, ")).append(target.getMainName()).append(".  The Thralls are completely distracted.").toString());
                } else
                if(nextLine == 5)
                {
                    if(nextSpeaker.getConfidence() > 66)
                    {
                        if(nextSpeaker.getInnocence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("I'll save you, ")).append(target.getMainName()).append("!").toString());
                        else
                        if(nextSpeaker.getInnocence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Hold on, ")).append(target.getMainName()).append("!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("You must not give in, ")).append(target.getMainName()).append("!").toString());
                    } else
                    if(nextSpeaker.getConfidence() > 33)
                    {
                        if(nextSpeaker.getInnocence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("It'll be alright, ")).append(target.getMainName()).append("!").toString());
                        else
                        if(nextSpeaker.getInnocence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Don't give up, ")).append(target.getMainName()).append("...!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("This may be painful, ")).append(target.getMainName()).append("...!").toString());
                    } else
                    if(nextSpeaker.getInnocence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("S-Someone help ")).append(target.getMainName()).append("!").toString());
                    else
                    if(nextSpeaker.getInnocence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("H-How could I let them get ")).append(target.getMainName()).append("?").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("...  This isn't good...").toString());
                } else
                if(nextLine == 6)
                {
                    if(nextSpeaker.getMorality() > 66)
                    {
                        if(nextSpeaker.getInnocence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("Fight back, ")).append(target.getMainName()).append("!  For justice!").toString());
                        else
                        if(nextSpeaker.getInnocence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Just get out of there, ")).append(target.getMainName()).append("!  I won't blame you for anything!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("!  Even if you have to break some bones, it's worth it if you can help us end this sooner!").toString());
                    } else
                    if(nextSpeaker.getMorality() > 33)
                    {
                        if(nextSpeaker.getInnocence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("Fight back, ")).append(target.getMainName()).append("!  I don't care what you have to do!").toString());
                        else
                        if(nextSpeaker.getInnocence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Just get out of there, ")).append(target.getMainName()).append("!  At any cost!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("There's no reason to show them any sympathy, ")).append(target.getMainName()).append(".").toString());
                    } else
                    if(nextSpeaker.getInnocence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("Fight back, ")).append(target.getMainName()).append("!  Beat them up!").toString());
                    else
                    if(nextSpeaker.getInnocence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("Hurt them as much as you have to, ")).append(target.getMainName()).append("!").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("Don't hold back, ")).append(target.getMainName()).append(".").toString());
                } else
                if(nextLine == 7)
                {
                    if(nextSpeaker.getInnocence() > 66)
                    {
                        if(nextSpeaker.getDignity() > 66)
                            nextSpeaker.say(t, (new StringBuilder("You're getting really good at that, ")).append(target.getMainName()).append("!").toString());
                        else
                        if(nextSpeaker.getDignity() > 33)
                            nextSpeaker.say(t, (new StringBuilder("The Thralls really love you, ")).append(target.getMainName()).append("!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("You're so lewd, ")).append(target.getMainName()).append("!").toString());
                    } else
                    if(nextSpeaker.getInnocence() > 33)
                    {
                        if(nextSpeaker.getDignity() > 66)
                            nextSpeaker.say(t, (new StringBuilder("There's nothing to be ashamed of, ")).append(target.getMainName()).append(".").toString());
                        else
                        if(nextSpeaker.getDignity() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Just finish them off as quickly as you can, ")).append(target.getMainName()).append("!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Good!  Fuck their brains out, ")).append(target.getMainName()).append("!").toString());
                    } else
                    if(nextSpeaker.getDignity() > 66)
                        nextSpeaker.say(t, (new StringBuilder("You're simply defeating them using unconventional means, ")).append(target.getMainName()).append(".").toString());
                    else
                    if(nextSpeaker.getDignity() > 33)
                        nextSpeaker.say(t, (new StringBuilder("It should be much easier to manage them after they ejaculate, ")).append(target.getMainName()).append(".").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("Your technique is proving highly effective, ")).append(target.getMainName()).append(".").toString());
                } else
                if(nextLine == 8)
                {
                    if(nextSpeaker.getConfidence() > 66)
                    {
                        if(nextSpeaker.getDignity() > 66)
                            nextSpeaker.say(t, (new StringBuilder("Yes, ")).append(target.getMainName()).append(", appeal to their conscience!").toString());
                        else
                        if(nextSpeaker.getDignity() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Just tell them what they want to hear, ")).append(target.getMainName()).append(".").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Your pride is less important than your safety, ")).append(target.getMainName()).append(".").toString());
                    } else
                    if(nextSpeaker.getConfidence() > 33)
                    {
                        if(nextSpeaker.getDignity() > 66)
                            nextSpeaker.say(t, (new StringBuilder("I hope this isn't too painful for you, ")).append(target.getMainName()).append("...").toString());
                        else
                        if(nextSpeaker.getDignity() > 33)
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" is having a hard time...").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Just say whatever you have to in order to get them to back off, ")).append(target.getMainName()).append(".").toString());
                    } else
                    if(nextSpeaker.getDignity() > 66)
                        nextSpeaker.say(t, (new StringBuilder("I'm glad that ")).append(target.getMainName()).append(" isn't clinging to ").append(target.hisHer()).append(" pride...").toString());
                    else
                    if(nextSpeaker.getDignity() > 33)
                        nextSpeaker.say(t, (new StringBuilder("It's hard, isn't it, ")).append(target.getMainName()).append("...?").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("Just throw your pride away, ")).append(target.getMainName()).append("...").toString());
                } else
                if(nextLine == 9)
                {
                    if(nextSpeaker.getDignity() > 66)
                    {
                        if(nextSpeaker.getMorality() > 66)
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" is trying so hard...").toString());
                        else
                        if(nextSpeaker.getMorality() > 33)
                            nextSpeaker.say(t, (new StringBuilder("The things they're doing to ")).append(target.getMainName()).append("...").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("They'll pay for doing this to ")).append(target.getMainName()).append(".").toString());
                    } else
                    if(nextSpeaker.getDignity() > 33)
                    {
                        if(nextSpeaker.getMorality() > 66)
                            nextSpeaker.say(t, (new StringBuilder("Be strong, ")).append(target.getMainName()).append("...!").toString());
                        else
                        if(nextSpeaker.getMorality() > 33)
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" is having a hard time...").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("We'll get our revenge, ")).append(target.getMainName()).append("!").toString());
                    } else
                    if(nextSpeaker.getMorality() > 66)
                        nextSpeaker.say(t, (new StringBuilder("Stop staring at ")).append(target.getMainName()).append("!").toString());
                    else
                    if(nextSpeaker.getMorality() > 33)
                        nextSpeaker.say(t, (new StringBuilder("How can I help ")).append(target.getMainName()).append("!?").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("I'll kill anyone that makes fun of ")).append(target.getMainName()).append("!").toString());
                } else
                if(nextLine == 10)
                {
                    if(nextSpeaker.getMorality() > 66)
                    {
                        if(nextSpeaker.getInnocence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("I don't like it when you look so mad, ")).append(target.getMainName()).append("...").toString());
                        else
                        if(nextSpeaker.getInnocence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Don't give in to the hatred, ")).append(target.getMainName()).append("!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Not good.  If ")).append(target.getMainName()).append(" lets ").append(target.himHer()).append("self become consumed by hatred...").toString());
                    } else
                    if(nextSpeaker.getMorality() > 33)
                    {
                        if(nextSpeaker.getInnocence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("You're looking scary, ")).append(target.getMainName()).append("...").toString());
                        else
                        if(nextSpeaker.getInnocence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("You have to stay sane, ")).append(target.getMainName()).append("...!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Careful, ")).append(target.getMainName()).append(".  It will cause problems if you give in to your emotions.").toString());
                    } else
                    if(nextSpeaker.getInnocence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("Ooh, the Demons made ")).append(target.getMainName()).append(" mad...").toString());
                    else
                    if(nextSpeaker.getInnocence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("You look like you're going crazy, ")).append(target.getMainName()).append(".").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("I think you're understanding how I feel about the Demons now, ")).append(target.getMainName()).append(".").toString());
                } else
                if(nextLine == 11)
                {
                    if(nextSpeaker.getInnocence() > 66)
                    {
                        if(nextSpeaker.getDignity() > 66)
                            nextSpeaker.say(t, (new StringBuilder("Are you feeling sick, ")).append(target.getMainName()).append("?  Your face is red...").toString());
                        else
                        if(nextSpeaker.getDignity() > 33)
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" is looking weird...").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("You look really naughty, ")).append(target.getMainName()).append("!").toString());
                    } else
                    if(nextSpeaker.getInnocence() > 33)
                    {
                        if(nextSpeaker.getDignity() > 66)
                            nextSpeaker.say(t, (new StringBuilder("Hold it together, ")).append(target.getMainName()).append("...").toString());
                        else
                        if(nextSpeaker.getDignity() > 33)
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" is having trouble keeping ").append(target.hisHer()).append(" composure...").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("You look like you're really turned on, ")).append(target.getMainName()).append(".").toString());
                    } else
                    if(nextSpeaker.getDignity() > 66)
                        nextSpeaker.say(t, (new StringBuilder("You must resist the pleasure, ")).append(target.getMainName()).append("...").toString());
                    else
                    if(nextSpeaker.getDignity() > 33)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" is being overcome by lustful feelings...").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("You're experiencing severe sexual arousal, ")).append(target.getMainName()).append(".").toString());
                } else
                if(nextLine == 12)
                {
                    if(nextSpeaker.getConfidence() > 66)
                    {
                        if(nextSpeaker.getMorality() > 66)
                            nextSpeaker.say(t, (new StringBuilder("Be careful, ")).append(target.getMainName()).append("!").toString());
                        else
                        if(nextSpeaker.getMorality() > 33)
                            nextSpeaker.say(t, (new StringBuilder("You're hurt, ")).append(target.getMainName()).append("!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("I'll kill them for hurting you like this, ")).append(target.getMainName()).append("!").toString());
                    } else
                    if(nextSpeaker.getConfidence() > 33)
                    {
                        if(nextSpeaker.getMorality() > 66)
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("...  Those wounds...").toString());
                        else
                        if(nextSpeaker.getMorality() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Are you alright, ")).append(target.getMainName()).append("?").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("!  Damn it...!").toString());
                    } else
                    if(nextSpeaker.getMorality() > 66)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("...  I'm sorry I couldn't protect you...").toString());
                    else
                    if(nextSpeaker.getMorality() > 33)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("!  Y-You're bleeding!").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("!  I wish I were stronger...!").toString());
                } else
                if(nextLine == 13)
                {
                    if(nextSpeaker.getDignity() > 66)
                    {
                        if(nextSpeaker.getInnocence() > 66)
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" is... a bit exposed...").toString());
                        else
                        if(nextSpeaker.getInnocence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("I should try to distract people from looking at ")).append(target.getMainName()).append("...").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("You look fine, ")).append(target.getMainName()).append("!").toString());
                    } else
                    if(nextSpeaker.getDignity() > 33)
                    {
                        if(nextSpeaker.getInnocence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("Wow, look at ")).append(target.getMainName()).append("...").toString());
                        else
                        if(nextSpeaker.getInnocence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("I wish I could do something for ")).append(target.getMainName()).append("...").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Don't worry about your appearance, ")).append(target.getMainName()).append(".").toString());
                    } else
                    if(nextSpeaker.getInnocence() > 66)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" looks weird!").toString());
                    else
                    if(nextSpeaker.getInnocence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("They're really having fun with ")).append(target.getMainName()).append("...").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("Just try to focus on fighting, ")).append(target.getMainName()).append(".").toString());
                } else
                if(nextLine == 14)
                {
                    if(nextSpeaker.getMorality() > 66)
                    {
                        if(nextSpeaker.getConfidence() > 66)
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", let me protect you!").toString());
                        else
                        if(nextSpeaker.getConfidence() > 33)
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", are you alright?").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", p-please be safe...!").toString());
                    } else
                    if(nextSpeaker.getMorality() > 33)
                    {
                        if(nextSpeaker.getConfidence() > 66)
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", I won't the Demons hurt you!").toString());
                        else
                        if(nextSpeaker.getConfidence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("I have to help ")).append(target.getMainName()).append("...").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Th-They're going to hurt ")).append(target.getMainName()).append("!").toString());
                    } else
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("You'd better not hurt ")).append(target.getMainName()).append("!").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("I'll stop them from hurting ")).append(target.getMainName()).append("... at any cost.").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("...  I'll punish them for threatening you...!").toString());
                } else
                if(nextLine == 15)
                {
                    if(nextSpeaker.getInnocence() > 66)
                    {
                        if(nextSpeaker.getConfidence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("I...  I can take anything they throw at me, ")).append(target.getMainName()).append("!").toString());
                        else
                        if(nextSpeaker.getConfidence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Got to be strong for ")).append(target.getMainName()).append("...!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Make it stop, ")).append(target.getMainName()).append("!").toString());
                    } else
                    if(nextSpeaker.getInnocence() > 33)
                    {
                        if(nextSpeaker.getConfidence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("I can deal with this much, ")).append(target.getMainName()).append("!").toString());
                        else
                        if(nextSpeaker.getConfidence() > 33)
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("...  I'm not doing so good...").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("H-Help me, ")).append(target.getMainName()).append("!").toString());
                    } else
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("I am... capable of enduring this much abuse, ")).append(target.getMainName()).append(".").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("I am... nearing my limit, ")).append(target.getMainName()).append("...").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("A-Assistance would be appreciated, ")).append(target.getMainName()).append("!").toString());
                } else
                if(nextLine == 17)
                    if(nextSpeaker.getDignity() > 66)
                    {
                        if(nextSpeaker.getConfidence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("T-Trust me, ")).append(target.getMainName()).append(", I'm doing fine!").toString());
                        else
                        if(nextSpeaker.getConfidence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("I must look terrible, ")).append(target.getMainName()).append("...").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("!  Don't look!").toString());
                    } else
                    if(nextSpeaker.getDignity() > 33)
                    {
                        if(nextSpeaker.getConfidence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("You're embarrassing me in front of ")).append(target.getMainName()).append("!").toString());
                        else
                        if(nextSpeaker.getConfidence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("I... I have to put on a strong face!  For ")).append(target.getMainName()).append("!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("I'm messing up in front of ")).append(target.getMainName()).append("...").toString());
                    } else
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("I don't have anything to hide from ")).append(target.getMainName()).append(".").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("I need to shake this off for ")).append(target.getMainName()).append("'s sake!").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("I-I'm so pathetic, ")).append(target.getMainName()).append("...!").toString());
            } else
            if(nextLine == 1 || nextLine == 6)
            {
                if(nextSpeaker.getMorality() > 66)
                {
                    if(nextSpeaker.getInnocence() > 66)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", you're hurting too many people!").toString());
                    else
                    if(nextSpeaker.getInnocence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("Stop, ")).append(target.getMainName()).append(", you're killing them!").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", they're not respnosible for their own actions!").toString());
                } else
                if(nextSpeaker.getMorality() > 33)
                {
                    if(nextSpeaker.getInnocence() > 66)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", you're acting like a bad guy!").toString());
                    else
                    if(nextSpeaker.getInnocence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("You need to hold back, ")).append(target.getMainName()).append("!").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("You're hurting more people than you're saving, ")).append(target.getMainName()).append(".").toString());
                } else
                if(nextSpeaker.getInnocence() > 66)
                    nextSpeaker.say(t, (new StringBuilder("You're way meaner than me, ")).append(target.getMainName()).append("!").toString());
                else
                if(nextSpeaker.getInnocence() > 33)
                    nextSpeaker.say(t, (new StringBuilder("You were the evil one all along, ")).append(target.getMainName()).append(".").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("...  You compensate for your cruelty by pretending to be righteous.").toString());
            } else
            if(nextLine == 2)
            {
                if(nextSpeaker.getInnocence() > 66)
                {
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("You're eating too much Demonic power, ")).append(target.getMainName()).append("!").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("You should be careful with Demonic power, ")).append(target.getMainName()).append("...").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", you shouldn't do that...").toString());
                } else
                if(nextSpeaker.getInnocence() > 33)
                {
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", stop!  Are you trying to brainwash yourself!?").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", you'll get brainwashed if you keep doing that!").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("You're going to get brainwashed, ")).append(target.getMainName()).append("...").toString());
                } else
                if(nextSpeaker.getConfidence() > 66)
                    nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("!  Show some willpower for once and stop taking in the Demonic energy!").toString());
                else
                if(nextSpeaker.getConfidence() > 33)
                    nextSpeaker.say(t, (new StringBuilder("You're getting addicted to the pleasure, ")).append(target.getMainName()).append(".").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", you fool...  Don't treat Demonic energy so lightly...").toString());
            } else
            if(nextLine == 3)
            {
                if(nextSpeaker.getConfidence() > 66)
                {
                    if(nextSpeaker.getDignity() > 66)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", stop and think for a moment!").toString());
                    else
                    if(nextSpeaker.getDignity() > 33)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", this is pointless!").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", have you gone insane!?").toString());
                } else
                if(nextSpeaker.getConfidence() > 33)
                {
                    if(nextSpeaker.getDignity() > 66)
                        nextSpeaker.say(t, (new StringBuilder("Calm down, ")).append(target.getMainName()).append("!").toString());
                    else
                    if(nextSpeaker.getDignity() > 33)
                        nextSpeaker.say(t, (new StringBuilder("What are you hoping to accomplish, ")).append(target.getMainName()).append("?").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", you masochist.").toString());
                } else
                if(nextSpeaker.getDignity() > 66)
                    nextSpeaker.say(t, (new StringBuilder("You're just acting on your emotions, ")).append(target.getMainName()).append("...").toString());
                else
                if(nextSpeaker.getDignity() > 33)
                    nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", you're beyond help...").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", you hotblooded idiot...").toString());
            } else
            if(nextLine == 4)
            {
                if(nextSpeaker.getDignity() > 66)
                {
                    if(nextSpeaker.getMorality() > 66)
                        nextSpeaker.say(t, (new StringBuilder("Show some modesty, ")).append(target.getMainName()).append("...").toString());
                    else
                    if(nextSpeaker.getMorality() > 33)
                        nextSpeaker.say(t, (new StringBuilder("You're making us look ridiculous, ")).append(target.getMainName()).append("...").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("Think of my reputation as your associate, ")).append(target.getMainName()).append("!").toString());
                } else
                if(nextSpeaker.getDignity() > 33)
                {
                    if(nextSpeaker.getMorality() > 66)
                        nextSpeaker.say(t, (new StringBuilder("That's just immoral, ")).append(target.getMainName()).append(".").toString());
                    else
                    if(nextSpeaker.getMorality() > 33)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("...  Making this about you...").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("You look ridiculous, ")).append(target.getMainName()).append(".").toString());
                } else
                if(nextSpeaker.getMorality() > 66)
                    nextSpeaker.say(t, (new StringBuilder("Stop posing and fight, ")).append(target.getMainName()).append("!").toString());
                else
                if(nextSpeaker.getMorality() > 33)
                    nextSpeaker.say(t, (new StringBuilder("What are you even trying to do, ")).append(target.getMainName()).append("?").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder("Stop pretending to be a hero, ")).append(target.getMainName()).append("!").toString());
            } else
            if(nextLine == 5)
            {
                if(nextSpeaker.getMorality() > 66)
                {
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("Don't you dare give in, ")).append(target.getMainName()).append("!").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("Even if it's you, ")).append(target.getMainName()).append(", I'll try to help.").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("I-I don't really want to see bad things happen to ")).append(target.getMainName()).append("...").toString());
                } else
                if(nextSpeaker.getMorality() > 33)
                {
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("You're lucky that I'm here to save you, ")).append(target.getMainName()).append("!").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("Can't you look after yourself, ")).append(target.getMainName()).append("?").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("Don't think that you can rely on me to save you, ")).append(target.getMainName()).append("...").toString());
                } else
                if(nextSpeaker.getConfidence() > 66)
                    nextSpeaker.say(t, (new StringBuilder("You're holding me back, ")).append(target.getMainName()).append("!").toString());
                else
                if(nextSpeaker.getConfidence() > 33)
                    nextSpeaker.say(t, (new StringBuilder("I'm not saving you, ")).append(target.getMainName()).append("!").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder("You're useless, ")).append(target.getMainName()).append("...").toString());
            } else
            if(nextLine == 7)
            {
                if(nextSpeaker.getInnocence() > 66)
                {
                    if(nextSpeaker.getDignity() > 66)
                        nextSpeaker.say(t, (new StringBuilder("You're such a pervert, ")).append(target.getMainName()).append(".").toString());
                    else
                    if(nextSpeaker.getDignity() > 33)
                        nextSpeaker.say(t, (new StringBuilder("You're just letting them do lewd things to you, ")).append(target.getMainName()).append("!").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("You look really funny, ")).append(target.getMainName()).append("!").toString());
                } else
                if(nextSpeaker.getInnocence() > 33)
                {
                    if(nextSpeaker.getDignity() > 66)
                        nextSpeaker.say(t, (new StringBuilder("You're such a slut, ")).append(target.getMainName()).append(".").toString());
                    else
                    if(nextSpeaker.getDignity() > 33)
                        nextSpeaker.say(t, (new StringBuilder("Looks like you've given up, ")).append(target.getMainName()).append(".").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("I kinda like seeing ")).append(target.getMainName()).append(" humbled like this.").toString());
                } else
                if(nextSpeaker.getDignity() > 66)
                    nextSpeaker.say(t, (new StringBuilder("It appears that you've given into your lusts, ")).append(target.getMainName()).append(".").toString());
                else
                if(nextSpeaker.getDignity() > 33)
                    nextSpeaker.say(t, (new StringBuilder("You're just encouraging them, ")).append(target.getMainName()).append(".").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder("That position suits you perfectly, ")).append(target.getMainName()).append(".").toString());
            } else
            if(nextLine == 8)
            {
                if(nextSpeaker.getConfidence() > 66)
                {
                    if(nextSpeaker.getDignity() > 66)
                        nextSpeaker.say(t, (new StringBuilder("You've completely given up your pride, ")).append(target.getMainName()).append(".").toString());
                    else
                    if(nextSpeaker.getDignity() > 33)
                        nextSpeaker.say(t, (new StringBuilder("Pathetic, ")).append(target.getMainName()).append("!").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("You weren't fighting hard enough, ")).append(target.getMainName()).append("!").toString());
                } else
                if(nextSpeaker.getConfidence() > 33)
                {
                    if(nextSpeaker.getDignity() > 66)
                        nextSpeaker.say(t, (new StringBuilder("You look ridiculous, ")).append(target.getMainName()).append(".").toString());
                    else
                    if(nextSpeaker.getDignity() > 33)
                        nextSpeaker.say(t, (new StringBuilder("Show some spine, ")).append(target.getMainName()).append(".").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("You should take this to heart, ")).append(target.getMainName()).append("!").toString());
                } else
                if(nextSpeaker.getDignity() > 66)
                    nextSpeaker.say(t, (new StringBuilder("You were the weak one all along, ")).append(target.getMainName()).append("...!").toString());
                else
                if(nextSpeaker.getDignity() > 33)
                    nextSpeaker.say(t, (new StringBuilder("I can't believe ")).append(target.getMainName()).append(" ended up being so pathetic...").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder("I shouldn't be enjoying seeing ")).append(target.getMainName()).append(" in this state so much...").toString());
            } else
            if(nextLine == 9)
            {
                if(nextSpeaker.getDignity() > 66)
                {
                    if(nextSpeaker.getMorality() > 66)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", just stop fighting if you can't handle it...").toString());
                    else
                    if(nextSpeaker.getMorality() > 33)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", you aren't cut out for this.").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("Aw, is it too much for ")).append(target.getMainName()).append("?").toString());
                } else
                if(nextSpeaker.getDignity() > 33)
                {
                    if(nextSpeaker.getMorality() > 66)
                        nextSpeaker.say(t, (new StringBuilder("You can't just hide from it all, ")).append(target.getMainName()).append(".").toString());
                    else
                    if(nextSpeaker.getMorality() > 33)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" is practically just giving up...").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("You look so pathetic, ")).append(target.getMainName()).append(".").toString());
                } else
                if(nextSpeaker.getMorality() > 66)
                    nextSpeaker.say(t, (new StringBuilder("This is what you get, ")).append(target.getMainName()).append(".").toString());
                else
                if(nextSpeaker.getMorality() > 33)
                    nextSpeaker.say(t, (new StringBuilder("What did you think would happen, ")).append(target.getMainName()).append("?").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder("I like seeing you like this, ")).append(target.getMainName()).append(".").toString());
            } else
            if(nextLine == 10)
            {
                if(nextSpeaker.getMorality() > 66)
                {
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("You'd better not give in to hatred, ")).append(target.getMainName()).append("!").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" really lets ").append(target.hisHer()).append(" hatred get the better of ").append(target.himHer()).append(".").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" is so full of hate...").toString());
                } else
                if(nextSpeaker.getMorality() > 33)
                {
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("Come to your senses, ")).append(target.getMainName()).append("!").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("You're losing it, ")).append(target.getMainName()).append("...").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" is going crazy...").toString());
                } else
                if(nextSpeaker.getConfidence() > 66)
                    nextSpeaker.say(t, (new StringBuilder("Your mind is weak, ")).append(target.getMainName()).append("!").toString());
                else
                if(nextSpeaker.getConfidence() > 33)
                    nextSpeaker.say(t, (new StringBuilder("You're going to lose your power like that, ")).append(target.getMainName()).append(".").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("...  You weak-willed fool...").toString());
            } else
            if(nextLine == 11)
            {
                if(nextSpeaker.getInnocence() > 66)
                {
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", what's wrong with you!?").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("Stop looking so distracted, ")).append(target.getMainName()).append("!").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" has ").append(target.hisHer()).append(" head in the clouds...").toString());
                } else
                if(nextSpeaker.getInnocence() > 33)
                {
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("Stop getting turned on and just focus, ")).append(target.getMainName()).append("!").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("I can't believe you're actually getting off on this, ")).append(target.getMainName()).append("!").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" is such a deviant...").toString());
                } else
                if(nextSpeaker.getConfidence() > 66)
                    nextSpeaker.say(t, (new StringBuilder("Even someone like you should be able to resist sexual temptation in the middle of battle, ")).append(target.getMainName()).append("!").toString());
                else
                if(nextSpeaker.getConfidence() > 33)
                    nextSpeaker.say(t, (new StringBuilder("You must be very sexually repressed, ")).append(target.getMainName()).append(".").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder("How am I supposed to fight alongside sexual deviants like ")).append(target.getMainName()).append("?").toString());
            } else
            if(nextLine == 12)
            {
                if(nextSpeaker.getConfidence() > 66)
                {
                    if(nextSpeaker.getDignity() > 66)
                        nextSpeaker.say(t, (new StringBuilder("How did you even get yourself hurt so quickly, ")).append(target.getMainName()).append("?").toString());
                    else
                    if(nextSpeaker.getDignity() > 33)
                        nextSpeaker.say(t, (new StringBuilder("Your wounds aren't that bad, ")).append(target.getMainName()).append(".").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("Just shake it off, ")).append(target.getMainName()).append("!").toString());
                } else
                if(nextSpeaker.getConfidence() > 33)
                {
                    if(nextSpeaker.getDignity() > 66)
                        nextSpeaker.say(t, (new StringBuilder("You got hurt really quickly, ")).append(target.getMainName()).append("...").toString());
                    else
                    if(nextSpeaker.getDignity() > 33)
                        nextSpeaker.say(t, (new StringBuilder("You're no help at all, ")).append(target.getMainName()).append(".").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("Stop screwing up and getting hurt, ")).append(target.getMainName()).append("!").toString());
                } else
                if(nextSpeaker.getDignity() > 66)
                    nextSpeaker.say(t, (new StringBuilder("If you're going to get hurt so quickly, ")).append(target.getMainName()).append(", you should just stay home next time...").toString());
                else
                if(nextSpeaker.getDignity() > 33)
                    nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" is even weaker than I am...").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder("I'm so sick of ")).append(target.getMainName()).append("'s incompetence...!").toString());
            } else
            if(nextLine == 13)
            {
                if(nextSpeaker.getDignity() > 66)
                {
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("Stop making a scene, ")).append(target.getMainName()).append("!").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("You look awful, ")).append(target.getMainName()).append(".").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("I can concentrate with ")).append(target.getMainName()).append("'s foolishness...").toString());
                } else
                if(nextSpeaker.getDignity() > 33)
                {
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("You're making us look foolish, ")).append(target.getMainName()).append("!").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("How did you manage to get your clothes torn, ")).append(target.getMainName()).append("?").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" is so careless...").toString());
                } else
                if(nextSpeaker.getConfidence() > 66)
                    nextSpeaker.say(t, (new StringBuilder("What the hell happened to ")).append(target.getMainName()).append("!?").toString());
                else
                if(nextSpeaker.getConfidence() > 33)
                    nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" screwed up again.").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder("Y-You're distracting me, ")).append(target.getMainName()).append("...!").toString());
            } else
            if(nextLine == 14)
            {
                if(nextSpeaker.getMorality() > 66)
                {
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("I won't let you get hurt, ")).append(target.getMainName()).append(", no matter how much you screw up!").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("I still worry about ")).append(target.getMainName()).append(", despite everything...").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("I can't stop myself from caring about ")).append(target.getMainName()).append("...").toString());
                } else
                if(nextSpeaker.getMorality() > 33)
                {
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("You'd better not make me rescue you, ")).append(target.getMainName()).append("!").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("I still worry about ")).append(target.getMainName()).append("... a little.").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("I don't want ")).append(target.getMainName()).append(" to get hurt... much...").toString());
                } else
                if(nextSpeaker.getConfidence() > 66)
                    nextSpeaker.say(t, (new StringBuilder("Damn that ")).append(target.getMainName()).append(", making me worry about ").append(target.himHer()).append("...!").toString());
                else
                if(nextSpeaker.getConfidence() > 33)
                    nextSpeaker.say(t, (new StringBuilder("I guess it would be nice to have ")).append(target.getMainName()).append(" in my debt...").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder("I guess I'll be in trouble if something happens to ")).append(target.getMainName()).append("...").toString());
            } else
            if(nextLine == 15)
            {
                if(nextSpeaker.getInnocence() > 66)
                {
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", I order you to help me!").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", you're supposed to help me!").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" wouldn't abandon me, r-right...?").toString());
                } else
                if(nextSpeaker.getInnocence() > 33)
                {
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("I-I can handle this on my own, ")).append(target.getMainName()).append("!").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", do something!").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("I don't want to rely on ")).append(target.getMainName()).append(", but...").toString());
                } else
                if(nextSpeaker.getConfidence() > 66)
                    nextSpeaker.say(t, (new StringBuilder("I... must learn to take care of myself... without ")).append(target.getMainName()).append("!").toString());
                else
                if(nextSpeaker.getConfidence() > 33)
                    nextSpeaker.say(t, (new StringBuilder("There must be a way to deal with this without relying on ")).append(target.getMainName()).append("...").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder("I really can't rely on ")).append(target.getMainName()).append("...").toString());
            } else
            if(nextLine == 17)
                if(nextSpeaker.getDignity() > 66)
                {
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("I'm doing perfectly fine, ")).append(target.getMainName()).append("!").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("I can deal with this myself, ")).append(target.getMainName()).append(".").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("I-I'm not embarrassed at all, ")).append(target.getMainName()).append("!").toString());
                } else
                if(nextSpeaker.getDignity() > 33)
                {
                    if(nextSpeaker.getConfidence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("What are you looking at, ")).append(target.getMainName()).append("?").toString());
                    else
                    if(nextSpeaker.getConfidence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("Just focus on the fight, ")).append(target.getMainName()).append("!").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("D-Don't look at me, ")).append(target.getMainName()).append("!").toString());
                } else
                if(nextSpeaker.getConfidence() > 66)
                    nextSpeaker.say(t, (new StringBuilder("What are you laughing at, ")).append(target.getMainName()).append("!?").toString());
                else
                if(nextSpeaker.getConfidence() > 33)
                    nextSpeaker.say(t, (new StringBuilder("Are you smirking at me, ")).append(target.getMainName()).append("?").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder("I bet you're enjoying seeing me like this, ")).append(target.getMainName()).append("...").toString());
            nextSpeaker.say(t, "\"");
        }
        lastLastLine = lastLine;
        lastLine = nextLine;
    }

    public Boolean decrementArrival(int index)
    {
        Boolean conclusion = Boolean.valueOf(false);
        arrivalTimer[index] -= 100;
        if(arrivalTimer[index] < 1)
            conclusion = Boolean.valueOf(true);
        return conclusion;
    }

    public void addToCombat(Chosen c)
    {
        if(currentCombatants[1] == null)
            currentCombatants[1] = c;
        else
            currentCombatants[2] = c;
    }

    public int getTotalRounds()
    {
        return totalRounds;
    }

    public void incrementTotalRounds()
    {
        totalRounds++;
    }

    public int getBattleRound()
    {
        return battleRound;
    }

    public void setCaptureTarget(Chosen target)
    {
        nextCapture = target;
    }

    public Chosen getNextCapture()
    {
        return nextCapture;
    }

    public int getCaptureDuration()
    {
        return captureDuration;
    }

    public void incrementDay()
    {
        day++;
    }

    public void clearCommander()
    {
        for(int i = 0; i < bodyStatus.length; i++)
            bodyStatus[i] = Boolean.valueOf(false);

    }

    public int getCommanderValue()
    {
        int value = 1;
        if(bodyStatus[1].booleanValue())
            value++;
        if(bodyStatus[7].booleanValue())
            value++;
        if(bodyStatus[8].booleanValue())
            value += 2;
        if(bodyStatus[9].booleanValue())
            value += 2;
        if(bodyStatus[10].booleanValue())
            value += 5;
        return value;
    }

    public void printCommanderSummary(JTextPane t, Chosen c)
    {
        int suppressors = 0;
        if(bodyStatus[3].booleanValue())
            suppressors++;
        if(bodyStatus[4].booleanValue())
            suppressors++;
        if(bodyStatus[5].booleanValue())
            suppressors++;
        if(bodyStatus[6].booleanValue())
            suppressors++;
        int reportedDuration = 2;
        if(c == null && (techs[8].isOwned().booleanValue() || techs[9].isOwned().booleanValue() || techs[10].isOwned().booleanValue() || techs[11].isOwned().booleanValue() || techs[12].isOwned().booleanValue() || techs[13].isOwned().booleanValue() || techs[14].isOwned().booleanValue() || techs[15].isOwned().booleanValue()))
        {
            if(techs[8].isOwned().booleanValue())
            {
                int durationCost = 0;
                append(t, "[X][X][");
                if(bodyStatus[1].booleanValue())
                {
                    append(t, "X");
                } else
                {
                    durationCost = 1;
                    append(t, " ");
                }
                append(t, "]");
                if(techs[14].isOwned().booleanValue())
                {
                    append(t, "[");
                    if(bodyStatus[7].booleanValue())
                    {
                        append(t, "X");
                    } else
                    {
                        append(t, " ");
                        if(durationCost == 0)
                            durationCost = 1;
                    }
                    append(t, "]");
                    if(techs[20].isOwned().booleanValue())
                    {
                        append(t, "[");
                        if(bodyStatus[9].booleanValue())
                        {
                            append(t, "X");
                        } else
                        {
                            append(t, " ");
                            if(durationCost == 0)
                                durationCost = 2;
                        }
                        append(t, "]");
                    }
                }
                append(t, " Duration");
                if(durationCost > 0)
                    append(t, (new StringBuilder(" (Next: ")).append(durationCost).append(" EE)").toString());
                append(t, "\n");
            }
            if(techs[15].isOwned().booleanValue())
            {
                int captureCost = 0;
                append(t, "[");
                if(bodyStatus[8].booleanValue())
                {
                    append(t, "X");
                } else
                {
                    append(t, " ");
                    captureCost = 2;
                }
                append(t, "]");
                append(t, " Extra Captures");
                if(captureCost > 0)
                    append(t, (new StringBuilder(" (Next: ")).append(captureCost).append(" EE)").toString());
                append(t, "\n");
            }
            if(techs[9].isOwned().booleanValue())
            {
                append(t, "[");
                if(bodyStatus[2].booleanValue())
                    append(t, " ");
                else
                    append(t, "X");
                append(t, "] Ambush (free)\n");
            }
            if(techs[10].isOwned().booleanValue() || techs[11].isOwned().booleanValue() || techs[12].isOwned().booleanValue() || techs[13].isOwned().booleanValue())
            {
                append(t, "Suppressor: ");
                if(suppressors == 0)
                    append(t, "None (free)");
                else
                if(suppressors == 1)
                {
                    if(bodyStatus[3].booleanValue())
                        append(t, "Hunger [HATE]");
                    else
                    if(bodyStatus[4].booleanValue())
                        append(t, "Lust [PLEA]");
                    else
                    if(bodyStatus[5].booleanValue())
                        append(t, "Anger [INJU]");
                    else
                    if(bodyStatus[6].booleanValue())
                        append(t, "Mania [EXPO]");
                    if(techs[21].isOwned().booleanValue())
                        append(t, " (Next: 5 EE)");
                } else
                if(suppressors == 2)
                {
                    String first = "";
                    String second = "";
                    if(bodyStatus[3].booleanValue())
                        first = "Hunger [HATE]";
                    if(bodyStatus[4].booleanValue())
                    {
                        String status = "Lust [PLEA]";
                        if(first.length() > 0)
                            second = status;
                        else
                            first = status;
                    }
                    if(bodyStatus[5].booleanValue())
                    {
                        String status = "Anger [INJU]";
                        if(first.length() > 0)
                            second = status;
                        else
                            first = status;
                    }
                    if(bodyStatus[6].booleanValue())
                        second = "Mania [EXPO]";
                    append(t, (new StringBuilder(String.valueOf(first))).append(", ").append(second).toString());
                }
                append(t, "\n");
            }
            append(t, "\n");
            if(suppressors == 0)
                append(t, "Your Commander is a humanoid Demon whose only exceptional feature is its ability to act as a body for your incorporeal form.  It ");
            else
            if(suppressors == 1)
            {
                if(bodyStatus[3].booleanValue())
                    append(t, "Your Commander is an oversized humanoid Demon covered with gaping mouths which allow you to speak your hateful will directly to the Chosen.  It ");
                else
                if(bodyStatus[4].booleanValue())
                    append(t, "Your Commander is an oversized humanoid Demon covered with various tentacles which seek out the most sensitive places on captured Chosen and force pleasure upon them.  It ");
                else
                if(bodyStatus[5].booleanValue())
                    append(t, "Your Commander is an oversized humanoid Demon with disproportionately large muscles which can deal grievous injuries even to the supernaturally-durable bodies of the Chosen.  It ");
                else
                if(bodyStatus[6].booleanValue())
                    append(t, "Your Commander is an oversized humanoid Demon with various human tools embedded in its body for the purpose of exposing the Chosen.  It ");
            } else
            if(suppressors == 2)
            {
                if(bodyStatus[3].booleanValue())
                {
                    append(t, "Your Commander is a giant humanoid Demon whose body is covered in gaping mouths which allow you to speak your hateful will directly to the Chosen.  ");
                    if(bodyStatus[4].booleanValue())
                        append(t, "Their tongues function as tentacles, capable of working their way under the Chosen's clothes and stimulating their erogenous zones.  ");
                    else
                    if(bodyStatus[5].booleanValue())
                        append(t, "They're filled with razor-sharp teeth for chewing on their victims, injuring and further aggravating them.  ");
                    else
                    if(bodyStatus[6].booleanValue())
                        append(t, "They drip with acidic saliva, and while this substance lacks the potency to injure the Chosen themselves, it will dissolve and weaken their clothes.  ");
                } else
                if(bodyStatus[4].booleanValue())
                {
                    append(t, "Your Commander is a giant humanoid Demon covered with various tentacles which seek out the most sensitive places on captured Chosen and force pleasure upon them.  ");
                    if(bodyStatus[5].booleanValue())
                        append(t, "Some are small and nimble, but others are thick and incredibly strong, capable of punching through solid brick and twisting steel beams apart.  ");
                    else
                    if(bodyStatus[6].booleanValue())
                        append(t, "They secrete an acidic fluid, and while this substance lacks the potency to injure the Chosen themselves, it will dissolve and weaken their clothes.  ");
                } else
                {
                    append(t, "Your Commander is a giant humanoid Demon with various human tools embedded in its body.  These tools include tanks of acid, spinning saws, chains, guns, and even flamethrowers.  They have been magically reinforced to deal significant damage to both the Chosen and their clothes.  ");
                }
                append(t, "The Commander ");
            }
        } else
        {
            append(t, "Your Commander ");
        }
        if(bodyStatus[2].booleanValue())
        {
            append(t, "will accompany your Demons ");
            if(c != null)
                append(t, (new StringBuilder("on their attack against ")).append(c.getMainName()).append(" ").toString());
            append(t, "and lie in wait, ");
        } else
        {
            append(t, "will lead your Demons into combat, ");
        }
        append(t, "allowing you to ambush ");
        if(c == null || bodyStatus[2].booleanValue())
            append(t, "your target and capture her");
        else
            append(t, (new StringBuilder(String.valueOf(c.getMainName()))).append(" and capture ").append(c.himHer()).toString());
        if(bodyStatus[9].booleanValue())
            reportedDuration += 3;
        else
        if(bodyStatus[7].booleanValue())
            reportedDuration += 2;
        else
        if(bodyStatus[1].booleanValue())
            reportedDuration++;
        if(reportedDuration == 2)
            append(t, " for two rounds");
        else
        if(reportedDuration == 3)
            append(t, " for three rounds");
        else
        if(reportedDuration == 4)
            append(t, " for four rounds");
        else
        if(reportedDuration == 5)
            append(t, " for five rounds");
        if(bodyStatus[2].booleanValue())
        {
            append(t, " once you give the order");
            if(bodyStatus[8].booleanValue())
                append(t, ", up to twice");
        } else
        {
            append(t, " at the start of battle");
            if(bodyStatus[8].booleanValue())
                append(t, ", and then one more time once you give the order");
        }
        if(c == null)
            append(t, (new StringBuilder(".  It is worth ")).append(getCommanderValue()).append(" Evil Energy.  You have ").append(evilEnergy).append(" Evil Energy remaining.").toString());
        else
        if(suppressors == 0)
        {
            append(t, (new StringBuilder(", giving your Thralls a chance to torment ")).append(c.himHer()).append(".").toString());
        } else
        {
            int place = 0;
            String damages[] = new String[2];
            if(suppressors == 2)
                damages = new String[4];
            if(bodyStatus[3].booleanValue())
            {
                damages[place] = "FEAR";
                place++;
                damages[place] = "HATE";
                place++;
            }
            if(bodyStatus[4].booleanValue())
            {
                damages[place] = "DISG";
                place++;
                damages[place] = "PLEA";
                place++;
            }
            if(bodyStatus[5].booleanValue())
            {
                damages[place] = "PAIN";
                place++;
                damages[place] = "INJU";
                place++;
            }
            if(bodyStatus[6].booleanValue())
            {
                damages[place] = "SHAM";
                place++;
                damages[place] = "EXPO";
                place++;
            }
            if(suppressors == 2)
                append(t, (new StringBuilder(", inflicting high levels of ")).append(damages[0]).append(", ").append(damages[1]).append(", ").append(damages[2]).append(", and ").append(damages[3]).append(".").toString());
            else
                append(t, (new StringBuilder(", inflicting high levels of ")).append(damages[0]).append(" and ").append(damages[1]).append(".").toString());
        }
    }

    public void newCommander()
    {
        bodyStatus[0] = Boolean.valueOf(true);
        for(int i = 1; i < bodyStatus.length; i++)
            if(i != 2)
                bodyStatus[i] = Boolean.valueOf(false);

    }

    public Boolean[] getBodyStatus()
    {
        return bodyStatus;
    }

    public int getEvilEnergy()
    {
        return evilEnergy;
    }

    public Tech[] getTechs()
    {
        return techs;
    }

    public int getDay()
    {
        return day;
    }

    public void printGroupTutorial(JTextPane t)
    {
        append(t, groupTutorial);
        groupTutorial = "";
    }

    public void printShopTutorial(JTextPane t)
    {
        append(t, shopTutorial);
        shopTutorial = "";
    }

    public void addEnergy(int amount)
    {
        evilEnergy += amount;
    }

    public void newCombat(WorldState w, Chosen c[])
    {
        currentCombatants = c;
        battleRound = 1;
        evacuationProgress = 0;
        evacuationComplete = 100;
        if(w.getTechs()[1].isOwned().booleanValue())
            evacuationComplete += 20;
        if(w.getTechs()[4].isOwned().booleanValue())
            evacuationComplete += 20;
        if(w.getTechs()[16].isOwned().booleanValue())
            evacuationComplete += 20;
        exterminationProgress = 0;
        exterminationComplete = 100;
        if(w.getTechs()[2].isOwned().booleanValue())
            exterminationComplete += 40;
        if(w.getTechs()[5].isOwned().booleanValue())
            exterminationComplete += 60;
        if(w.getTechs()[17].isOwned().booleanValue())
            exterminationComplete += 100;
        exterminationMultiplier = 100;
        captureDuration = 2;
        if(bodyStatus[7].booleanValue())
            captureDuration = 4;
        else
        if(bodyStatus[1].booleanValue())
            captureDuration = 3;
        else
            captureDuration = 2;
        capturesPossible = 0;
        if(bodyStatus[2].booleanValue() || bodyStatus[3].booleanValue() || bodyStatus[4].booleanValue() || bodyStatus[5].booleanValue() || bodyStatus[6].booleanValue())
            capturesPossible++;
        if(bodyStatus[8].booleanValue())
            capturesPossible++;
        nextCapture = null;
        nextSurround = null;
        rallyBonus = new int[3];
        distractBonus = new int[3];
        arrivalTimer = new int[3];
        for(int i = 0; i < 3; i++)
        {
            arrivalTimer[i] = 600 - c[0].getDignity() * 2;
            if(w.getCast()[i] != null)
            {
                arrivalTimer[i] = (arrivalTimer[i] * (250 - w.getCast()[i].getConfidence())) / 200;
                int analysis = w.getRelationship(c[0].getNumber(), w.getCast()[i].getNumber());
                if(analysis == 1)
                    arrivalTimer[i] = (arrivalTimer[i] * 9) / 10;
                else
                if(analysis == 2)
                    arrivalTimer[i] = (arrivalTimer[i] * 6) / 7;
                else
                if(analysis == 4)
                    arrivalTimer[i] = (arrivalTimer[i] * 4) / 5;
                else
                if(analysis == -2)
                    arrivalTimer[i] = (arrivalTimer[i] * 7) / 6;
                else
                if(analysis == -4)
                    arrivalTimer[i] = (arrivalTimer[i] * 5) / 4;
            }
        }

    }

    public int[] getArrivalTimer()
    {
        return arrivalTimer;
    }

    public Chosen[] getCast()
    {
        return cast;
    }

    public void addChosen(Chosen c)
    {
        if(cast[0] == null)
        {
            cast[0] = c;
            c.setNumber(0);
        } else
        if(cast[1] == null)
        {
            cast[1] = c;
            c.setNumber(1);
        } else
        if(cast[2] == null)
        {
            cast[2] = c;
            c.setNumber(2);
        }
    }

    public void append(JTextPane t, String s)
    {
        StyledDocument doc = t.getStyledDocument();
        SimpleAttributeSet keyWord = new SimpleAttributeSet();
        StyleConstants.setFontSize(keyWord, 16);
        StyleConstants.setForeground(keyWord, Color.BLACK);
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
        t.setCaretPosition(t.getDocument().getLength());
    }

    public void purpleAppend(JTextPane t, String s)
    {
        StyledDocument doc = t.getStyledDocument();
        SimpleAttributeSet keyWord = new SimpleAttributeSet();
        StyleConstants.setFontSize(keyWord, 16);
        StyleConstants.setForeground(keyWord, PURPLE);
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
        t.setCaretPosition(t.getDocument().getLength());
    }

    public void orangeAppend(JTextPane t, String s)
    {
        StyledDocument doc = t.getStyledDocument();
        SimpleAttributeSet keyWord = new SimpleAttributeSet();
        StyleConstants.setFontSize(keyWord, 16);
        StyleConstants.setForeground(keyWord, ORANGE);
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
        t.setCaretPosition(t.getDocument().getLength());
    }

    public void grayAppend(JTextPane t, String s)
    {
        StyledDocument doc = t.getStyledDocument();
        SimpleAttributeSet keyWord = new SimpleAttributeSet();
        StyleConstants.setFontSize(keyWord, 16);
        StyleConstants.setForeground(keyWord, Color.GRAY);
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
        t.setCaretPosition(t.getDocument().getLength());
    }

    public void underlineAppend(JTextPane t, String s)
    {
        StyledDocument doc = t.getStyledDocument();
        SimpleAttributeSet keyWord = new SimpleAttributeSet();
        StyleConstants.setFontSize(keyWord, 16);
        StyleConstants.setForeground(keyWord, Color.BLACK);
        StyleConstants.setFontFamily(keyWord, "DialogInput");
        StyleConstants.setBold(keyWord, true);
        StyleConstants.setUnderline(keyWord, true);
        try
        {
            doc.insertString(doc.getLength(), s, keyWord);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        t.setCaretPosition(t.getDocument().getLength());
    }

    public void redAppend(JTextPane t, String s)
    {
        StyledDocument doc = t.getStyledDocument();
        SimpleAttributeSet keyWord = new SimpleAttributeSet();
        StyleConstants.setFontSize(keyWord, 16);
        StyleConstants.setForeground(keyWord, RED);
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
        t.setCaretPosition(t.getDocument().getLength());
    }

    public void greenAppend(JTextPane t, String s)
    {
        StyledDocument doc = t.getStyledDocument();
        SimpleAttributeSet keyWord = new SimpleAttributeSet();
        StyleConstants.setFontSize(keyWord, 16);
        StyleConstants.setForeground(keyWord, GREEN);
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
        t.setCaretPosition(t.getDocument().getLength());
    }

    public void inverseAppend(JTextPane t, String s)
    {
        StyledDocument doc = t.getStyledDocument();
        SimpleAttributeSet keyWord = new SimpleAttributeSet();
        StyleConstants.setFontSize(keyWord, 16);
        StyleConstants.setForeground(keyWord, Color.WHITE);
        StyleConstants.setFontFamily(keyWord, "DialogInput");
        StyleConstants.setBackground(keyWord, Color.BLACK);
        StyleConstants.setBold(keyWord, true);
        try
        {
            doc.insertString(doc.getLength(), s, keyWord);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        t.setCaretPosition(t.getDocument().getLength());
    }

    public void copyInitial(WorldState w)
    {
        day = 1;
        initializeTips();
        nameSeed = w.getNameSeed();
        for(int i = 0; i < techs.length; i++)
        {
            techs[i] = new Tech();
            techs[i].initialize(i, this);
        }

        for(int i = 0; i < bodyStatus.length; i++)
            bodyStatus[i] = Boolean.valueOf(false);

        shopTutorial = "\n\nAs the Chosen are traumatized in battle, they give in to their own weakness and turn their backs on their duty.  As their trauma grows greater, so too do their transgressions.  And the greater their transgressions, the more powerful the Demons become!  Spend this Evil Energy to increase your abilities, and use those abilities to make the Chosen fall even further!";
        groupTutorial = "\n\nThere is weakness in numbers.  A rival can become an enemy, and a friend is just a burden.  The Chosen will grow closer as they're corrupted.  Turn those feelings to your own ends and use the Chosen against each other!";
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                friendship[i][j] = 0;
                friction[i][j] = 0;
                remaining[i][j] = 0;
            }

        }

        statSeed = w.getStatSeed();
        for(int i = 0; i < groupScenes.length; i++)
            groupScenes[i] = Boolean.valueOf(false);

    }

    public void tutorialInit()
    {
        nameSeed[0] = 0;
        nameSeed[1] = 0;
        nameSeed[2] = 2;
        nameSeed[3] = 2;
        nameSeed[4] = 4;
        nameSeed[5] = 4;
        for(int i = 0; i < techs.length; i++)
        {
            techs[i] = new Tech();
            techs[i].initialize(i, this);
        }

        techs[0].buy(this);
        techs[1].buy(this);
        techs[2].buy(this);
        techs[3].buy(this);
        techs[4].buy(this);
        techs[5].buy(this);
        techs[6].buy(this);
        techs[7].buy(this);
        techs[8].buy(this);
        techs[9].buy(this);
        techs[13].buy(this);
        for(int i = 0; i < bodyStatus.length; i++)
            bodyStatus[i] = Boolean.valueOf(false);

        bodyStatus[0] = Boolean.valueOf(true);
        bodyStatus[1] = Boolean.valueOf(true);
        bodyStatus[2] = Boolean.valueOf(true);
        bodyStatus[6] = Boolean.valueOf(true);
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                friendship[i][j] = 0;
                friction[i][j] = 0;
                remaining[i][j] = 0;
            }

        }

        statSeed[0] = 90;
        statSeed[1] = 50;
        statSeed[2] = 10;
        statSeed[3] = 30;
        statSeed[4] = 15;
        statSeed[5] = 70;
        statSeed[6] = 85;
        statSeed[7] = 65;
        statSeed[8] = 65;
        statSeed[9] = 10;
        statSeed[10] = 50;
        statSeed[11] = 70;
        for(int i = 0; i < 3; i++)
        {
            Chosen c = new Chosen();
            c.setName(this, nameSeed);
            c.generate(this);
            cast[i] = c;
            c.setSomeAngst();
        }

        tutorial = Boolean.valueOf(true);
        onTrack = Boolean.valueOf(true);
    }

    public void initialize()
    {
        initializeTips();
        for(int i = 0; i < 6; i++)
        {
            for(Boolean proceed = Boolean.valueOf(false); !proceed.booleanValue();)
            {
                nameSeed[i] = (int)(Math.random() * 26D);
                proceed = Boolean.valueOf(true);
                for(int j = 0; j < i; j++)
                    if(nameSeed[i] == nameSeed[j])
                        proceed = Boolean.valueOf(false);

            }

        }

        for(int i = 0; i < techs.length; i++)
        {
            techs[i] = new Tech();
            techs[i].initialize(i, this);
        }

        for(int i = 0; i < bodyStatus.length; i++)
            bodyStatus[i] = Boolean.valueOf(false);

        shopTutorial = "\n\nAs the Chosen are traumatized in battle, they give in to their own weakness and turn their backs on their duty.  As their trauma grows greater, so too do their transgressions.  And the greater their transgressions, the more powerful the Demons become!  Spend this Evil Energy to increase your abilities, and use those abilities to make the Chosen fall even further!";
        groupTutorial = "\n\nThere is weakness in numbers.  A rival can become an enemy, and a friend is just a burden.  The Chosen will grow closer as they're corrupted.  Turn those feelings to your own ends and use the Chosen against each other!";
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                friendship[i][j] = 0;
                friction[i][j] = 0;
                remaining[i][j] = 0;
            }

        }

        for(Boolean goodStats = Boolean.valueOf(false); !goodStats.booleanValue();)
        {
            goodStats = Boolean.valueOf(true);
            for(int i = 0; i < statSeed.length; i++)
                statSeed[i] = (int)(Math.random() * 101D);

            for(int i = 0; i < 4; i++)
            {
                int first = statSeed[i] / 33 + (statSeed[i] % 33 != 0 ? 1 : 0);
                if(first < 1)
                    first = 1;
                else
                if(first > 3)
                    first = 3;
                int second = statSeed[i + 4] / 33 + (statSeed[i + 4] % 33 != 0 ? 1 : 0);
                if(second < 1)
                    second = 1;
                else
                if(second > 3)
                    second = 3;
                int third = statSeed[i + 8] / 33 + (statSeed[i + 8] % 33 != 0 ? 1 : 0);
                if(third < 1)
                    third = 1;
                else
                if(third > 3)
                    third = 3;
                if(first == second || first == third || second == third)
                    goodStats = Boolean.valueOf(false);
            }

            for(int i = 0; i < 4; i++)
            {
                int first = statSeed[i] / 25 + (statSeed[i] % 25 != 0 ? 1 : 0);
                if(first < 1)
                    first = 1;
                int second = statSeed[i + 4] / 25 + (statSeed[i + 4] % 25 != 0 ? 1 : 0);
                if(second < 1)
                    second = 1;
                int third = statSeed[i + 8] / 25 + (statSeed[i + 8] % 25 != 0 ? 1 : 0);
                if(third < 1)
                    third = 1;
                if(first == second || first == third || second == third)
                    goodStats = Boolean.valueOf(false);
            }

            Boolean extremeLow = Boolean.valueOf(false);
            Boolean extremeHigh = Boolean.valueOf(false);
            Boolean midValue = Boolean.valueOf(false);
            for(int i = 0; i < 4; i++)
                if(statSeed[i] > 66)
                    extremeHigh = Boolean.valueOf(true);
                else
                if(statSeed[i] < 34)
                    extremeLow = Boolean.valueOf(true);
                else
                    midValue = Boolean.valueOf(true);

            if(!extremeLow.booleanValue() || !extremeHigh.booleanValue() || !midValue.booleanValue())
                goodStats = Boolean.valueOf(false);
            extremeLow = Boolean.valueOf(false);
            extremeHigh = Boolean.valueOf(false);
            midValue = Boolean.valueOf(false);
            for(int i = 4; i < 8; i++)
                if(statSeed[i] > 66)
                    extremeHigh = Boolean.valueOf(true);
                else
                if(statSeed[i] < 34)
                    extremeLow = Boolean.valueOf(true);
                else
                    midValue = Boolean.valueOf(true);

            if(!extremeLow.booleanValue() || !extremeHigh.booleanValue() || !midValue.booleanValue())
                goodStats = Boolean.valueOf(false);
            extremeLow = Boolean.valueOf(false);
            extremeHigh = Boolean.valueOf(false);
            midValue = Boolean.valueOf(false);
            for(int i = 8; i < 12; i++)
                if(statSeed[i] > 66)
                    extremeHigh = Boolean.valueOf(true);
                else
                if(statSeed[i] < 34)
                    extremeLow = Boolean.valueOf(true);
                else
                    midValue = Boolean.valueOf(true);

            if(!extremeLow.booleanValue() || !extremeHigh.booleanValue() || !midValue.booleanValue())
                goodStats = Boolean.valueOf(false);
            Boolean pureTarget = Boolean.valueOf(false);
            Boolean corruptTarget = Boolean.valueOf(false);
            for(int i = 0; i < 3; i++)
                if(determinePurity(statSeed[i * 4], statSeed[1 + i * 4], statSeed[2 + i * 4], statSeed[3 + i * 4]).booleanValue())
                    pureTarget = Boolean.valueOf(true);
                else
                    corruptTarget = Boolean.valueOf(true);

            if(!pureTarget.booleanValue() || !corruptTarget.booleanValue())
                goodStats = Boolean.valueOf(false);
        }

        for(int i = 0; i < groupScenes.length; i++)
            groupScenes[i] = Boolean.valueOf(false);

    }

    public Boolean determineVVirg(int morality, int innocence, int confidence, int dignity)
    {
        if((morality + confidence) - innocence - 20 < 0 && morality < 34)
            return Boolean.valueOf(false);
        else
            return Boolean.valueOf(true);
    }

    public Boolean determineCVirg(int morality, int innocence, int confidence, int dignity)
    {
        if((innocence + dignity) - morality - 20 < 0 && innocence < 34)
            return Boolean.valueOf(false);
        else
            return Boolean.valueOf(true);
    }

    public Boolean determineAVirg(int morality, int innocence, int confidence, int dignity)
    {
        if((confidence + innocence) - dignity - 20 < 0 && confidence < 34)
            return Boolean.valueOf(false);
        else
            return Boolean.valueOf(true);
    }

    public Boolean determineModest(int morality, int innocence, int confidence, int dignity)
    {
        if((morality + dignity) - confidence - 20 < 0 && dignity < 34)
            return Boolean.valueOf(false);
        else
            return Boolean.valueOf(true);
    }

    public Boolean determinePurity(int morality, int innocence, int confidence, int dignity)
    {
        if(determineVVirg(morality, innocence, confidence, dignity).booleanValue() && determineCVirg(morality, innocence, confidence, dignity).booleanValue() && determineAVirg(morality, innocence, confidence, dignity).booleanValue() && determineModest(morality, innocence, confidence, dignity).booleanValue())
            return Boolean.valueOf(true);
        else
            return Boolean.valueOf(false);
    }

    public int[] getNameSeed()
    {
        return nameSeed;
    }

    public int[] getStatSeed()
    {
        return statSeed;
    }

    public Chosen[] getCombatants()
    {
        return currentCombatants;
    }

    public boolean incrementThreatened()
    {
        return totalThreatened++ == 0;
    }

    public boolean incrementSlimed()
    {
        return totalSlimed++ == 0;
    }

    public boolean incrementAttacked()
    {
        return totalAttacked++ == 0;
    }

    public boolean incrementTaunted()
    {
        return totalTaunted++ == 0;
    }

    public boolean evacComplete()
    {
        return evacuationProgress >= evacuationComplete;
    }

    public String getEvacStatus()
    {
        int percentage = (evacuationProgress * 100) / evacuationComplete;
        return (new StringBuilder(String.valueOf(evacuationProgress))).append("/").append(evacuationComplete).append(" (").append(percentage).append("%)").toString();
    }

    public String getExterminationStatus()
    {
        int percentage = (exterminationProgress * 100) / exterminationComplete;
        return (new StringBuilder(String.valueOf(exterminationProgress))).append("/").append(exterminationComplete).append(" (").append(percentage).append("%)").toString();
    }

    public void setRallyBonus(int amount, int initiative)
    {
        rallyBonus[initiative] = amount;
    }

    public int getRallyBonus()
    {
        return rallyBonus[0] + rallyBonus[1] + rallyBonus[2];
    }

    public void setDistractBonus(int amount, int initiative)
    {
        distractBonus[initiative] = amount;
    }

    public int getDistractBonus()
    {
        int total = distractBonus[0] + distractBonus[1] + distractBonus[2];
        if(total > 250)
            total = 250;
        return total;
    }

    public void clearBonus(int index)
    {
        rallyBonus[index] = 0;
        distractBonus[index] = 0;
    }

    public boolean progressEvacuation(int amount)
    {
        evacuationProgress += amount;
        if(evacuationProgress < evacuationComplete)
        {
            return false;
        } else
        {
            evacuationProgress = evacuationComplete;
            return true;
        }
    }

    public boolean progressExtermination(int amount)
    {
        exterminationProgress += amount;
        if(exterminationProgress < exterminationComplete)
        {
            return false;
        } else
        {
            exterminationProgress = exterminationComplete;
            return true;
        }
    }

    public boolean endTurn(JTextPane t)
    {
        battleRound++;
        totalRounds++;
        append(t, (new StringBuilder("Round ")).append(battleRound).append("\n\n").toString());
        if(evacuationProgress < evacuationComplete)
            if(progressEvacuation(evacuationPerTurn))
                append(t, "Evacuation complete!\n");
            else
                append(t, (new StringBuilder("Evacuation: ")).append(getEvacStatus()).append("\n").toString());
        Boolean haltEnding = Boolean.valueOf(false);
        Chosen trappedChosen = null;
        for(int i = 0; i < 3; i++)
            if(getCombatants()[i] != null)
            {
                getCombatants()[i].updateSurround();
                if(!getCombatants()[i].isSurrounded().booleanValue() && !getCombatants()[i].isCaptured().booleanValue())
                {
                    progressExtermination((exterminationPerChosen * exterminationMultiplier) / 100);
                } else
                {
                    haltEnding = Boolean.valueOf(true);
                    trappedChosen = getCombatants()[i];
                }
            }

        if((exterminationProgress < exterminationComplete || haltEnding.booleanValue()) && getTechs()[18].isOwned().booleanValue())
            readyToEnd = Boolean.valueOf(false);
        if((exterminationProgress < exterminationComplete || haltEnding.booleanValue() || getTechs()[18].isOwned().booleanValue() && !readyToEnd.booleanValue()) && exterminationProgress >= 0)
        {
            append(t, (new StringBuilder("Extermination: ")).append(getExterminationStatus()).append("\n\n").toString());
            if(evacuationProgress < evacuationComplete)
            {
                append(t, "The desperate battle continues...\n");
            } else
            {
                Chosen c = null;
                Boolean allGrabbed = Boolean.valueOf(true);
                if(getCombatants()[0] != null)
                    if(getCombatants()[0].isSurrounded().booleanValue() || getCombatants()[0].isCaptured().booleanValue())
                    {
                        if(getCombatants()[1] != null)
                            if(getCombatants()[1].isSurrounded().booleanValue() || getCombatants()[1].isCaptured().booleanValue())
                            {
                                if(getCombatants()[2] != null && !getCombatants()[2].isSurrounded().booleanValue() && !getCombatants()[2].isCaptured().booleanValue())
                                    allGrabbed = Boolean.valueOf(false);
                            } else
                            {
                                allGrabbed = Boolean.valueOf(false);
                            }
                    } else
                    {
                        allGrabbed = Boolean.valueOf(false);
                    }
                if(allGrabbed.booleanValue())
                {
                    append(t, "The Demons have the Chosen at their mercy!\n");
                    exterminationMultiplier = (exterminationMultiplier * 3) / 2;
                } else
                if(exterminationProgress >= exterminationComplete)
                {
                    Boolean allFree = Boolean.valueOf(true);
                    if(getCombatants()[0].isSurrounded().booleanValue() || getCombatants()[0].isCaptured().booleanValue())
                        allFree = Boolean.valueOf(false);
                    else
                    if(getCombatants()[1] != null)
                        if(getCombatants()[1].isSurrounded().booleanValue() || getCombatants()[1].isCaptured().booleanValue())
                            allFree = Boolean.valueOf(false);
                        else
                        if(getCombatants()[2] != null && (getCombatants()[2].isSurrounded().booleanValue() || getCombatants()[2].isCaptured().booleanValue()))
                            allFree = Boolean.valueOf(false);
                    if(allFree.booleanValue())
                    {
                        readyToEnd = Boolean.valueOf(true);
                        append(t, "The reanimated Demons are fighting their last stand!  Combat will end next turn unless one of the Chosen are surrounded or captured.\n");
                    } else
                    {
                        while(c == null) 
                        {
                            c = getCombatants()[(int)(Math.random() * 3D)];
                            if(c != null && (c.isSurrounded().booleanValue() || c.isCaptured().booleanValue()))
                                c = null;
                        }
                        append(t, (new StringBuilder(String.valueOf(c.getMainName()))).append(" can't finish clearing out the Demons due to the risk of hitting the trapped ").append(trappedChosen.getMainName()).append(" with friendly fire!\n").toString());
                    }
                    exterminationMultiplier = (exterminationMultiplier * 3) / 2;
                } else
                {
                    while(c == null) 
                    {
                        c = getCombatants()[(int)(Math.random() * 3D)];
                        if(c != null && (c.isSurrounded().booleanValue() || c.isCaptured().booleanValue()))
                            c = null;
                    }
                    Boolean plural = Boolean.valueOf(false);
                    if(getCombatants()[1] != null)
                        plural = Boolean.valueOf(true);
                    if(exterminationMultiplier == 100)
                    {
                        append(t, (new StringBuilder("With the civilians evacuated, ")).append(c.getMainName()).toString());
                        if(plural.booleanValue())
                            append(t, " and the other Chosen can start drawing on their full power!");
                        else
                            append(t, (new StringBuilder(" can start drawing on ")).append(c.hisHer()).append(" full power!").toString());
                    } else
                    if(exterminationMultiplier == 150)
                        append(t, (new StringBuilder(String.valueOf(c.getMainName()))).append("'s attacks grow stronger and stronger, shattering windows and setting off alarms!").toString());
                    else
                    if(exterminationMultiplier == 225)
                        append(t, (new StringBuilder(String.valueOf(c.getMainName()))).append(" moves like a blur, taking down a wide swath of Demons!").toString());
                    else
                    if(exterminationMultiplier == 337)
                        append(t, (new StringBuilder("A blast of energy from ")).append(c.getMainName()).append(" brings down a small building in a cloud of rubble!").toString());
                    else
                    if(exterminationMultiplier == 505)
                        append(t, (new StringBuilder("The area is riddled with craters caused by the power of ")).append(c.getMainName()).append("'s attacks!").toString());
                    else
                    if(exterminationMultiplier == 757)
                        append(t, (new StringBuilder("The district is consumed by an enormous explosion as ")).append(c.getMainName()).append(" blasts away the Demons!").toString());
                    if((exterminationMultiplier * 3) / 2 > exterminationMultiplier)
                        exterminationMultiplier = (exterminationMultiplier * 3) / 2;
                    append(t, "\n(Extermination power");
                    if(cast[1] != null)
                        append(t, " per Chosen");
                    append(t, (new StringBuilder(": ")).append((exterminationPerChosen * exterminationMultiplier) / 100).append(")").toString());
                    append(t, "\n");
                }
            }
            return false;
        } else
        {
            return true;
        }
    }

    public void setSurroundTarget(Chosen c)
    {
        nextSurround = c;
    }

    public Chosen getSurroundTarget()
    {
        return nextSurround;
    }

    public Chosen getCaptureTarget()
    {
        return nextCapture;
    }

    public void primeRemaining(int first, int second)
    {
        remaining[first][second] += 49;
        remaining[second][first] += 49;
    }

    public void addFriendship(int first, int second, int amount)
    {
        friendship[first][second] += amount;
        friendship[second][first] += amount;
        remaining[first][second] -= amount;
        remaining[second][first] -= amount;
        if(remaining[first][second] == 0 && getRelationship(first, second) == 1)
        {
            friendship[first][second]++;
            friendship[second][first]++;
        }
    }

    public void addFriction(int first, int second, int amount)
    {
        friction[first][second] += amount;
        friction[second][first] += amount;
        remaining[first][second] -= amount;
        remaining[second][first] -= amount;
        if(remaining[first][second] == 0 && getRelationship(first, second) == 1)
        {
            friendship[first][second]++;
            friendship[second][first]++;
        }
    }

    public int getRelationship(int first, int second)
    {
        int value = 0;
        int difference = friendship[first][second] - friction[first][second];
        int undecided = remaining[first][second];
        if(friendship[first][second] == 0 && friction[first][second] == 0)
            value = 0;
        else
        if(difference == 0)
            value = 1;
        else
        if(difference > 0 && difference < undecided)
            value = 2;
        else
        if(difference < 0 && 0 - difference < undecided)
            value = -2;
        else
        if(difference > 0)
            value = 4;
        else
            value = -4;
        return value;
    }

    public Boolean tutorialResponse()
    {
        if(onTrack.booleanValue())
            return tutorial;
        else
            return Boolean.valueOf(false);
    }

    public Boolean isTutorial()
    {
        return tutorial;
    }

    public void endTutorial()
    {
        onTrack = Boolean.valueOf(false);
    }

    public WorldState()
    {
        PURPLE = new Color(100, 0, 150);
        ORANGE = new Color(200, 100, 0);
        RED = new Color(180, 0, 0);
        GREEN = new Color(0, 110, 0);
        nameSeed = new int[6];
        statSeed = new int[12];
        totalThreatened = 0;
        totalSlimed = 0;
        totalAttacked = 0;
        totalTaunted = 0;
        nextTip = 0;
        cast = new Chosen[3];
        techs = new Tech[22];
        evilEnergy = 0;
        day = 1;
        totalRounds = 1;
        friendship = new int[3][3];
        friction = new int[3][3];
        remaining = new int[3][3];
        groupScenes = new Boolean[20];
        lastSpeaker = null;
        lastLine = 0;
        lastLastLine = 0;
        pendingBreaks = new int[0];
        evacuationPerTurn = 20;
        exterminationPerChosen = 10;
        rallyBonus = new int[3];
        distractBonus = new int[3];
        nextSurround = null;
        nextCapture = null;
        captureDuration = 2;
        capturesPossible = 0;
        arrivalTimer = new int[3];
        readyToEnd = Boolean.valueOf(false);
        bodyStatus = new Boolean[11];
        tutorial = Boolean.valueOf(false);
        onTrack = Boolean.valueOf(false);
        cheater = Boolean.valueOf(false);
    }

    private static final long serialVersionUID = 4L;
    Color PURPLE;
    Color ORANGE;
    Color RED;
    Color GREEN;
    int nameSeed[];
    int statSeed[];
    String shopTutorial;
    String groupTutorial;
    int totalThreatened;
    int totalSlimed;
    int totalAttacked;
    int totalTaunted;
    String tips[];
    int nextTip;
    Chosen cast[];
    Tech techs[];
    int evilEnergy;
    int day;
    int totalRounds;
    long highScore;
    int friendship[][];
    int friction[][];
    int remaining[][];
    Boolean groupScenes[];
    Chosen lastSpeaker;
    int lastLine;
    int lastLastLine;
    int pendingBreaks[];
    int evacuationPerTurn;
    int exterminationPerChosen;
    Chosen currentCombatants[];
    int battleRound;
    int evacuationProgress;
    int evacuationComplete;
    int exterminationProgress;
    int exterminationComplete;
    int exterminationMultiplier;
    int rallyBonus[];
    int distractBonus[];
    Chosen nextSurround;
    Chosen nextCapture;
    int captureDuration;
    int capturesPossible;
    int arrivalTimer[];
    Boolean readyToEnd;
    Boolean bodyStatus[];
    Boolean tutorial;
    Boolean onTrack;
    Boolean cheater;
}
