
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.text.*;

public class WorldState
    implements Serializable
{

    public void toggleTickle()
    {
        tickleOn = Boolean.valueOf(!tickleOn.booleanValue());
    }

    public Boolean tickle()
    {
        return tickleOn;
    }

    public long getBarrierMulti()
    {
        return barrierMulti;
    }

    public void increaseBarrier(JTextPane t)
    {
        barrierMulti = (barrierMulti * 105L) / 100L;
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
        long comparison = 10000L;
        int previousBarriers;
        for(previousBarriers = 0; comparison < barrierMulti; previousBarriers++)
            comparison = (comparison * 105L) / 100L;

        if(previousBarriers == 0)
            append(t, "A faint haze, invisible to the naked eye, appears across the battlefield as you begin to exert control over reality.");
        else
        if(previousBarriers == 1)
            append(t, "The haze of your influence thickens enough to become visible, lending a faint red tint to the battlefield.");
        else
        if(previousBarriers == 2)
            append(t, "The red haze grows to the point that it's impossible not to notice, lending an unearthly quality to the scene.");
        else
        if(previousBarriers == 3)
            append(t, "From elsewhere in the city, your region of influence is visible as a transparent red dome which pulsates with every surge of power, signaling your increasing control over the space within.");
        else
        if(previousBarriers == 4)
            append(t, "The battlefield is completely sealed off from the rest of the city by the red fog, preparing it to be twisted even further by your will.");
        else
        if(previousBarriers == 5)
            append(t, "A high-pitched ringing sound begins to echo across the blood-red battlefield, affecting the minds of all within and influencing their emotions.");
        else
        if(previousBarriers == 6)
            append(t, "The ringing of your power grows louder, causing windows to shake and pebbles to vibrate and jump along the ground.");
        else
        if(previousBarriers == 7)
            append(t, "The red air grows thick and stifling, clinging to the Chosen and slowing their movements even as it protects and supports your servants.");
        else
        if(previousBarriers == 8)
            append(t, "Space begins to ripple and distort, as if flowing around unseen obstructions that exist parallel to the mundane architecture of the city.");
        else
            append(t, "The mind-numbing ringing digs deeper and deeper into the minds within your barrier.  For those trapped within, it feels as though countless eyes are watching from the edge of their peripheral vision.");
        append(t, (new StringBuilder("\n\n+5% damage taken for the remainder of the battle.  (+")).append(barrierMulti / 100L - 100L).append("% total)").toString());
    }

    public String getSeparator()
    {
        return separator;
    }

    public void setSeparator(String newSeparator)
    {
        separator = newSeparator;
    }

    public void setEarlyCheat(Boolean value)
    {
        earlyCheat = value;
    }

    public Boolean getEarlyCheat()
    {
        return earlyCheat;
    }

    public void repairSave()
    {
        Boolean adjusted = Boolean.valueOf(false);
        if(genderBalance == null)
        {
            genderBalance = (new int[] {
                0, 3, 0, 0
            });
            adjusted = Boolean.valueOf(true);
        }
        if(genders == null)
        {
            genders = (new String[] {
                "female", "female", "female"
            });
            adjusted = Boolean.valueOf(true);
        }
        if(separator == null)
            separator = "---";
        if(earlyCheat == null)
        {
            earlyCheat = Boolean.valueOf(false);
            adjusted = Boolean.valueOf(true);
        }
        for(int i = 0; i < 3; i++)
            if(getCast()[i] != null && (getCast()[i].isUsingStrip() == null || getCast()[i].getNextAdaptation() == 0L || getCast()[i].FANTlevels == null))
            {
                getCast()[i].resetAdaptations();
                adjusted = Boolean.valueOf(true);
            }

        if(resolvedBreaks == null)
        {
            resolvedBreaks = new int[0];
            adjusted = Boolean.valueOf(true);
        }
        int techsThisVersion = 34;
        if(techs.length < techsThisVersion)
        {
            Tech oldTechs[] = new Tech[techs.length];
            for(int i = 0; i < techs.length; i++)
                oldTechs[i] = techs[i];

            techs = new Tech[techsThisVersion];
            for(int i = 0; i < techsThisVersion; i++)
            {
                techs[i] = new Tech();
                techs[i].initialize(i, this);
                if(i < oldTechs.length && oldTechs[i].isOwned().booleanValue())
                    techs[i].owned = Boolean.valueOf(true);
            }

            adjusted = Boolean.valueOf(true);
        }
        int commanderUpgradesThisVersion = 19;
        Boolean commanderUpdate = Boolean.valueOf(false);
        if(bodyStatus.length < commanderUpgradesThisVersion)
            commanderUpdate = Boolean.valueOf(true);
        else
        if(recordedCommanders.length > 0 && recordedCommanders[0].length < commanderUpgradesThisVersion)
            commanderUpdate = Boolean.valueOf(true);
        if(commanderUpdate.booleanValue())
        {
            Boolean newUpgrades[] = new Boolean[commanderUpgradesThisVersion];
            Boolean newRecordedCommanders[][] = new Boolean[recordedCommanders.length][commanderUpgradesThisVersion];
            for(int i = 0; i < newUpgrades.length; i++)
            {
                if(i < bodyStatus.length)
                    newUpgrades[i] = bodyStatus[i];
                else
                    newUpgrades[i] = Boolean.valueOf(false);
                if(recordedCommanders.length > 0)
                {
                    if(i < recordedCommanders[0].length)
                    {
                        for(int j = 0; j < recordedCommanders.length; j++)
                            newRecordedCommanders[j][i] = recordedCommanders[j][i];

                    } else
                    {
                        for(int j = 0; j < recordedCommanders.length; j++)
                            newRecordedCommanders[j][i] = Boolean.valueOf(false);

                    }
                    recordedCommanders = newRecordedCommanders;
                }
            }

            bodyStatus = newUpgrades;
            adjusted = Boolean.valueOf(true);
        }
        if(barrierMulti == 0L)
        {
            barrierMulti = 10000L;
            adjusted = Boolean.valueOf(true);
        }
        if(tickleOn == null)
        {
            tickleOn = Boolean.valueOf(false);
            adjusted = Boolean.valueOf(true);
        }
        if(customFeet == null)
        {
            customFeet = (new String[] {
                "", "", ""
            });
            for(int i = 0; i < 3; i++)
                if(getCast()[i] != null)
                {
                    String cosmetics[] = pickCosmetics(cast[i].getMorality(), cast[i].getInnocence(), cast[i].getConfidence(), cast[i].getDignity());
                    cast[i].feetType = cosmetics[9];
                }

        }
        if(version == null)
            adjusted = Boolean.valueOf(true);
        else
        if(!version.equals("9"))
            adjusted = Boolean.valueOf(true);
        if(adjusted.booleanValue())
        {
            if(day > 1)
            {
                cheater = Boolean.valueOf(true);
                earlyCheat = Boolean.valueOf(true);
            }
            highScore = 0L;
            parScore = 0L;
            version = "9";
        }
    }

    public void copySettings(JTextPane t, WorldState w)
    {
        w.repairSave();
        if(!w.BACKGROUND.equals(BACKGROUND))
            toggleColors(t);
        if(w.getTextSize() != textSize)
            switchTextSize();
        if(w.getTextSize() != textSize)
            switchTextSize();
        maleShift = w.getMaleShift();
        femaleShift = w.getFemaleShift();
        separator = w.getSeparator();
        if(!tickleOn.equals(w.tickle()))
            toggleTickle();
    }

    public void copyToggles(WorldState w)
    {
        w.repairSave();
        if(w.getGenderBalance() != null)
            trackGenderBalance(w.getGenderBalance());
        setCommentaryRead(w.getCommentaryRead());
        setCommentaryWrite(w.getCommentaryWrite());
        earlyCheat = w.getEarlyCheat();
    }

    public int getFemaleShift()
    {
        return femaleShift;
    }

    public int getMaleShift()
    {
        return maleShift;
    }

    public void changeFemaleShift()
    {
        if(femaleShift == 0)
            femaleShift = 1;
        else
            femaleShift = 0;
    }

    public void changeMaleShift()
    {
        maleShift++;
        if(maleShift == 3)
            maleShift = 0;
    }

    public void toggleGenderRandomness()
    {
        if(genderBalance[0] == 0)
        {
            genderBalance[0] = 1;
            if(genderBalance[1] == 0 && genderBalance[2] == 0 && genderBalance[3] == 0)
                genderBalance[1] = 3;
        } else
        {
            if(genderBalance[1] == 0 && genderBalance[2] == 0 && genderBalance[3] == 0)
                genderBalance[1] = 3;
            int maleThreshold = genderBalance[1];
            int futaThreshold = genderBalance[1] + genderBalance[2];
            int total = genderBalance[1] + genderBalance[2] + genderBalance[3];
            genderBalance = new int[4];
            for(int i = 1; i < 4; i++)
            {
                int test = (int)(Math.random() * (double)total);
                if(test < maleThreshold)
                    genderBalance[1] = genderBalance[1] + 1;
                else
                if(test < futaThreshold)
                    genderBalance[2] = genderBalance[2] + 1;
                else
                    genderBalance[3] = genderBalance[3] + 1;
            }

        }
        setGenders(genderBalance);
    }

    public void increaseGender(int index)
    {
        genderBalance[index] = genderBalance[index] + 1;
        if(genderBalance[1] + genderBalance[2] + genderBalance[3] == 3 || genderBalance[0] == 1)
            setGenders(genderBalance);
    }

    public void decreaseGender(int index)
    {
        genderBalance[index] = genderBalance[index] - 1;
        if(genderBalance[1] + genderBalance[2] + genderBalance[3] == 3 || genderBalance[0] == 1)
            setGenders(genderBalance);
    }

    public int[] getGenderBalance()
    {
        return genderBalance;
    }

    public void trackGenderBalance(int type[])
    {
        genderBalance = type;
    }

    public void setGenders(int type[])
    {
        genderBalance = type;
        int dupeArray[] = new int[4];
        for(int i = 0; i < 4; i++)
            dupeArray[i] = type[i];

        if(type[0] == 0)
        {
            for(int i = 0; i < 3; i++)
            {
                int total = dupeArray[1] + dupeArray[2] + dupeArray[3];
                int maleIndex = dupeArray[1];
                int futaIndex = dupeArray[1] + dupeArray[2];
                int test = (int)(Math.random() * (double)total);
                if(test < maleIndex)
                {
                    genders[i] = "female";
                    dupeArray[1] = dupeArray[1] - 1;
                } else
                if(test < futaIndex)
                {
                    genders[i] = "male";
                    dupeArray[2] = dupeArray[2] - 1;
                } else
                {
                    genders[i] = "futanari";
                    dupeArray[3] = dupeArray[3] - 1;
                }
            }

        } else
        {
            if(genderBalance[1] == 0 && genderBalance[2] == 0 && genderBalance[3] == 0)
                genderBalance[1] = 3;
            int maleThreshold = genderBalance[1];
            int futaThreshold = genderBalance[1] + genderBalance[2];
            int total = genderBalance[1] + genderBalance[2] + genderBalance[3];
            for(int i = 0; i < 3; i++)
            {
                int test = (int)(Math.random() * (double)total);
                if(test < maleThreshold)
                    genders[i] = "female";
                else
                if(test < futaThreshold)
                    genders[i] = "male";
                else
                    genders[i] = "futanari";
            }

        }
        if(cast[0] != null)
            cast[0].setGender(genders[0]);
    }

    public String[] getGenders()
    {
        return genders;
    }

    public int getTextSize()
    {
        return textSize;
    }

    public void switchTextSize()
    {
        if(textSize == 16)
            textSize = 20;
        else
            textSize = 16;
        for(int i = 0; i < 3; i++)
            if(cast[i] != null)
                cast[i].setTextSize(textSize);

    }

    public void setParScore(long newScore)
    {
        parScore = newScore;
    }

    public long getParScore()
    {
        return parScore;
    }

    public void setSaveTitle(String s)
    {
        saveTitle = s;
    }

    public String getSaveTitle()
    {
        return saveTitle;
    }

    public String getCurrentComment()
    {
        return currentComment;
    }

    public void setCurrentComment(String s)
    {
        currentComment = s;
    }

    public Boolean[][] getRecordedCommanders()
    {
        return recordedCommanders;
    }

    public void archiveCommander(int day)
    {
        Boolean difference = Boolean.valueOf(false);
        if(recordedCommanders.length < day - 1)
        {
            difference = Boolean.valueOf(true);
        } else
        {
            for(int i = 0; i < bodyStatus.length; i++)
                if(!bodyStatus[i].equals(recordedCommanders[day - 2][i]))
                    difference = Boolean.valueOf(true);

        }
        if(difference.booleanValue())
        {
            Boolean newRecordedCommanders[][] = new Boolean[day - 1][bodyStatus.length];
            for(int i = 0; i < day - 2; i++)
            {
                for(int j = 0; j < bodyStatus.length; j++)
                    newRecordedCommanders[i][j] = recordedCommanders[i][j];

            }

            for(int j = 0; j < bodyStatus.length; j++)
                newRecordedCommanders[day - 2][j] = bodyStatus[j];

            recordedCommanders = newRecordedCommanders;
            onTrack = Boolean.valueOf(false);
        }
    }

    public void nextAction(int action)
    {
        Boolean newArray = Boolean.valueOf(false);
        if(actions.length > currentAction)
        {
            if(action != actions[currentAction])
            {
                newArray = Boolean.valueOf(true);
                onTrack = Boolean.valueOf(false);
            }
        } else
        if(!tutorial.booleanValue())
        {
            newArray = Boolean.valueOf(true);
            onTrack = Boolean.valueOf(false);
        }
        if(newArray.booleanValue())
        {
            int newActions[] = new int[currentAction + 1];
            for(int i = 0; i < currentAction; i++)
                newActions[i] = actions[i];

            newActions[currentAction] = action;
            actions = newActions;
        }
        currentAction++;
    }

    public int getCurrentAction()
    {
        return currentAction;
    }

    public int[] getActions()
    {
        return actions;
    }

    public String[] getCommentary()
    {
        return commentary;
    }

    public Boolean writePossible()
    {
        if(currentAction <= commentary.length && commentaryWrite.booleanValue())
            return Boolean.valueOf(true);
        else
            return Boolean.valueOf(false);
    }

    public void readCommentary(JTextPane t)
    {
        if(!tutorial.booleanValue())
            if(commentaryRead.booleanValue() && onTrack.booleanValue())
            {
                if(commentary.length > currentAction)
                    grayAppend(t, (new StringBuilder("\n\n")).append(commentary[currentAction]).toString());
                else
                    onTrack = Boolean.valueOf(false);
            } else
            {
                onTrack = Boolean.valueOf(false);
            }
    }

    public void truncateCommentary(int lastAction)
    {
        if(!tutorial.booleanValue())
        {
            onTrack = Boolean.valueOf(false);
            String newCommentary[] = new String[lastAction];
            int newActions[] = new int[lastAction];
            for(int i = 0; i < lastAction; i++)
                if(commentary.length > i)
                {
                    newCommentary[i] = commentary[i];
                    newActions[i] = actions[i];
                } else
                {
                    i = lastAction;
                }

            commentary = newCommentary;
        }
    }

    public void writeCommentary(String s)
    {
        if(commentary.length > currentAction)
        {
            commentary[currentAction] = s;
        } else
        {
            String newCommentary[] = new String[currentAction + 1];
            for(int i = 0; i < currentAction; i++)
                newCommentary[i] = commentary[i];

            newCommentary[currentAction] = s;
            commentary = newCommentary;
        }
        currentComment = "";
    }

    public Boolean getCommentaryRead()
    {
        return commentaryRead;
    }

    public Boolean getCommentaryWrite()
    {
        return commentaryWrite;
    }

    public void setCommentaryRead(Boolean setting)
    {
        commentaryRead = setting;
    }

    public void setCommentaryWrite(Boolean setting)
    {
        commentaryWrite = setting;
    }

    public Boolean[] getCustomBooleans()
    {
        Boolean allBooleans[] = new Boolean[15];
        for(int i = 0; i < 3; i++)
        {
            allBooleans[i] = invertVVirg[i];
            allBooleans[i + 3] = invertCVirg[i];
            allBooleans[i + 6] = invertAVirg[i];
            allBooleans[i + 9] = invertModest[i];
            allBooleans[i + 12] = gaijinStatus[i];
        }

        return allBooleans;
    }

    public void setCustomBooleans(Boolean allBooleans[])
    {
        for(int i = 0; i < 3; i++)
        {
            invertVVirg[i] = allBooleans[i];
            invertCVirg[i] = allBooleans[i + 3];
            invertAVirg[i] = allBooleans[i + 6];
            invertModest[i] = allBooleans[i + 9];
            gaijinStatus[i] = allBooleans[i + 12];
        }

    }

    public String[] getCustomStrings()
    {
        String allStrings[] = new String[45];
        for(int i = 0; i < 3; i++)
        {
            allStrings[i] = customNames[i];
            allStrings[i + 3] = customNames[i + 3];
            allStrings[i + 6] = customTop[i];
            allStrings[i + 9] = customTopAccess[i];
            allStrings[i + 12] = customBottom[i];
            allStrings[i + 15] = customBottomAccess[i];
            allStrings[i + 18] = customUnder[i];
            allStrings[i + 21] = customColor[i];
            allStrings[i + 24] = customAccessory[i];
            allStrings[i + 27] = customWeapons[i];
            allStrings[i + 30] = customWeaponTypes[i];
            allStrings[i + 33] = customAliases[i];
            allStrings[i + 36] = customTitles[i];
            allStrings[i + 39] = customIncantations[i];
            allStrings[i + 42] = customFeet[i];
        }

        return allStrings;
    }

    public void setCustomStrings(String allStrings[])
    {
        for(int i = 0; i < 3; i++)
        {
            customNames[i] = allStrings[i];
            customNames[i + 3] = allStrings[i + 3];
            customTop[i] = allStrings[i + 6];
            customTopAccess[i] = allStrings[i + 9];
            customBottom[i] = allStrings[i + 12];
            customBottomAccess[i] = allStrings[i + 15];
            customUnder[i] = allStrings[i + 18];
            customColor[i] = allStrings[i + 21];
            customAccessory[i] = allStrings[i + 24];
            customWeapons[i] = allStrings[i + 27];
            customWeaponTypes[i] = allStrings[i + 30];
            customAliases[i] = allStrings[i + 33];
            customTitles[i] = allStrings[i + 36];
            customIncantations[i] = allStrings[i + 39];
            customFeet[i] = allStrings[i + 42];
        }

    }

    public Boolean[] getInvertVVirg()
    {
        return invertVVirg;
    }

    public Boolean[] getInvertCVirg()
    {
        return invertCVirg;
    }

    public Boolean[] getInvertAVirg()
    {
        return invertAVirg;
    }

    public Boolean[] getInvertModest()
    {
        return invertModest;
    }

    public void toggleColors(JTextPane t)
    {
        if(BACKGROUND.equals(Color.WHITE))
        {
            FOREGROUND = Color.WHITE;
            BACKGROUND = Color.BLACK;
            PURPLE = new Color(170, 70, 220);
            ORANGE = new Color(240, 150, 100);
            RED = new Color(220, 90, 90);
            GREEN = new Color(70, 170, 70);
            BLUE = new Color(100, 100, 255);
        } else
        {
            FOREGROUND = Color.BLACK;
            BACKGROUND = Color.WHITE;
            PURPLE = new Color(100, 0, 150);
            ORANGE = new Color(200, 100, 0);
            RED = new Color(180, 0, 0);
            GREEN = new Color(0, 110, 0);
            BLUE = new Color(0, 0, 230);
        }
        t.setBackground(BACKGROUND);
    }

    public Boolean isCheater()
    {
        return cheater;
    }

    public void setCheater()
    {
        cheater = Boolean.valueOf(true);
    }

    public void removeCheater()
    {
        cheater = Boolean.valueOf(false);
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
            "During downtime, the Chosen can compromise on performing activities that none of them would perform on their own.", "Selectively corrupting one of the Chosen can make her want to perform actions that her allies aren't corrupt enough to join her on.", "The first step in corrupting one of the Chosen is generally to make her use sinful techniques to protect herself while surrounded.", "When captured by a Commander with a special ability, it is not possible for the Chosen to defend themselves.", "When the Thralls are instructed to torment a surrounded Chosen, they will continue to do so on their own until she escapes.", "Once the Chosen start using sinful methods to defend themselves, surrounding them will be less effective, but it will also be possible to extract more Evil Energy from them.", "A Chosen less inherently susceptible to a trauma will be more susceptible to the associated circumstance, and vice versa.", "Compassionate Chosen hate their enemies less but are more fearful for their allies.", "Cruel Chosen hate their enemies more but are less fearful for their allies.", "Innocent Chosen have less interest in sex but are more prone to getting grossed out and disgusted.", 
            "Experienced Chosen have grown more accustomed to pleasure, but they're better at ignoring disgusting things.", "Prideful Chosen refuse to flinch away from pain, but they have the willpower to ignore being injured.", "Cowardly Chosen are careful to avoid pain, but they lack the willpower to ignore their injuries.", "Self-conscious Chosen feel more shame, but they're more careful to avoid getting stripped and exposed.", "Shameless Chosen care less about being humiliated, but they don't put much effort into avoiding getting stripped and exposed.", "After the evacuation has been completed, the extermination rate begins to increase exponentially.", "There are two types of damage: trauma and circumstance.", "Trauma inflicted during battle creates openings for the Chosen to be surrounded, but it decreases the damage received in the associated circumstance.", "Circumstances multiply the damage received by the Chosen (especially in the associated trauma), but they do not directly contribute to unresolved trauma.", "Chosen driven into a panic for their allies will stop listening to the hateful things said by their enemies.", 
            "Chosen who hate their enemies will feel greater fear for what those enemies will do.", "Chosen overwhelmed by disgust will be less receptive to pleasure.", "Chosen overwhelmed by pleasure in battle will be disgusted by that fact.", "Chosen who are in pain will be more careful to avoid getting further injured.", "Chosen who are already injured will feel more pain from being attacked again.", "Chosen who are feeling ashamed will be more careful to avoid getting exposed.", "Chosen who are exposed will feel more shame from all sources.", "The protective powers of the Chosen depend on their pure hearts, so a Chosen consumed by hate is more vulnerable both emotionally and physically.", "The more pleasure one of the Chosen feels, the deeper her trauma will be engraved in her memory.", "As the Chosen are injured, they become less able to defend themselves from other abuses.", 
            "When one Chosen is exposed and humiliated, it distracts and breaks the morale of the other Chosen on the battlefield.", "Every day, each Chosen's ANGST is increased by the trauma she hasn't successfully resolved yet.", "High ANGST makes the Chosen willing to perform sinful activities, and until it's resolved, the distraction makes them take more damage from all sources.", "Every doubling of ANGST increases the damage bonus by +1, so even a few hundred ANGST is much better than none at all.", "A Chosen's susceptibility to a damage type normally ranges from 0 to 100 based on personality.  The ANGST bonus is added to this value.", "A Chosen's susceptibility to a damage type ranges from 0 to 100 based on personality.  This base susceptibility to a trauma and to its associated circumstance normally adds up to 100, but corruption increases increases them both, potentially even over 100.", "More sinful actions produce a bit more Evil Energy, but they resolve trauma at an exponentially greater rate.", "The Chosen will only begin to use sinful methods to defend themselves if they expect to reach level 3 circumstance damage otherwise.", "Some sinful actions taken during battle will also damage their users.", "As the Chosen are corrupted, they will begin to use more sinful but also more effective versions of their abilities.", 
            "Fearful Chosen are more vulnerable when their allies are surrounded or captured.", "Disgusted Chosen are always more vulnerable, but being grossed out won't generally create a major opening on its own.", "Chosen in pain are more vulnerable for awhile, but after they get surrounded, the adrenaline allows them to shake it off until the pain reaches the next level.", "Ashamed Chosen aren't any more vulnerable to being surrounded, but their efforts to retain their modesty mean that they'll remain surrounded for longer.", "Damage which currently contributes to the opening level is displayed in purple text.  Damage which does not is displayed in black text.  Damage which is only partially contributing to the opening level is displayed in orange text.", "By using \"Regenerate\", one of the Chosen can remove a fraction of her current circumstance damage.  However, nothing done in battle can remove trauma damage that has already been dealt.", "By using \"Blast\", one of the Chosen can increase evacuation and extermination progress.  If evacuation is already complete, the progress that would be added there is wasted.", "The Chosen choose their actions in battle according to which actions would seem to be most useful at the moment and how effective they are at performing those actions.", "Taunting is more effective against self-conscious Chosen, especially those who have been humiliated in the past.", "Attacking is more effective against Chosen who refuse to back down from a fight, especially those who feel insecure about past failures", 
            "Sliming is more effective against more naive Chosen, especially those who have come to associate battle with sexual pleasure.", "Threatening allies is more effective against more compassionate Chosen, especially those whose consciences aren't clean.", "It isn't possible to raise a circumstance by more than one level with a single instance of damage.  This limitation does not apply to trauma.", "Each of the actions the Chosen can perform in battle is linked with one of the four vulnerabilities.  The Chosen are better at performing actions associated with their greater vulnerabilities.", "Chosen who are surrounded or captured do not contribute to extermination progress until they escape.", "When a surrounded Chosen uses a tactic that decreases the effectiveness of Grind, Caress, Pummel, or Humiliate, the damage from that source is decreased to 3/5.  When both tactics against the source are used at once, the damage becomes 2/5.", "The main benefit of Suppressor-class upgrades is that they ignore defensive tactics.  Against Chosen who have not yet begun to use any defensive tactics, a Commander without Suppressor-class upgrades can actually more effective.", "When two of the Chosen have a hostile interaction with each other, Evil Energy is generated, especially when the interaction turns them from friends into enemies.", "Any action that deals circumstance damage also deals all four types of trauma damage, especially the one corresponding to the circumstance.", "An action's tooltip lists its damage types in descending order of how much is dealt.", 
            "From a gameplay perspective, there are no differences between male, female, and futanari Chosen.", "It isn't possible for one of the Chosen to use \"Slaughter,\" \"Fantasize,\" or \"Striptease\" twice in a row.", "Even when \"Slaughter\" causes a surround duration to go below 0, it will never cause a surrounded Chosen to escape on the same turn.", "Because high trauma penalizes circumstance damage, the circumstance damage reduction from \"Fantasize\" doesn't necessarily decrease circumstance damage in the long run.", "When your Commander has no extra captures remaining, the extra capture depletion from Chosen using \"Detonate\" does nothing.", "\"Striptease\" decreases damage to surrounded Chosen in the short term, but the fact that it increases the user's EXPO level means that it can increase the overall damage taken during the battle.", "Even after the critical trauma level is reached, a third-tier Vulnerability is not actually broken until the Chosen uses the unlocked move for the first time."
        });
        if(tickleOn.booleanValue())
        {
            tips[14] = "The traumas are FEAR, DISG, TICK, and SHAM.  The circumstances are HATE, PLEA, ANTI, and EXPO.";
            tips[41] = "Prideful Chosen refuse to acknowledge that they're bothered by something as trivial as tickling, but they have the confidence to not overly dwell on anticipating what torments you have in store for them.";
            tips[42] = "Cowardly Chosen will always try to protect themselves, even from something as trivial as tickling, but it's easier to fill them with dreadful anticipation of what torments you have in store for them.";
            tips[53] = "Chosen flustered and distracted by tickling won't be able to spare as much thought toward anticipating what you'll do to them next.";
            tips[54] = "Chosen who have had their anticipation built up will react that much more strongly when you finally go all-out in tickling them.";
            tips[59] = "Because the Chosen are empowered by their own self-confidence, their anticipation of what you'll do to them will actually make them more vulnerable.";
            tips[72] = "Chosen distracted by tickling are more vulnerable for awhile, but after they get surrounded, they'll be more focused on escape and defense until they've been tickled some more.";
            tips[79] = "Poking is more effective against Chosen who refuse to protect themselves, especially those who feel insecure about past failures.";
        }
    }

    public void printTip(JTextPane t)
    {
        append(t, (new StringBuilder("\n\nTip: ")).append(tips[nextTip]).toString());
        for(int storage = nextTip; storage == nextTip; nextTip = (int)(Math.random() * (double)tips.length));
    }

    public void scoreSummary(JTextPane t)
    {
        long totalScore = 0L;
        underlineAppend(t, "Day Thirty Score");
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
        long traumaBonus = (firstTrauma + secondTrauma + thirdTrauma) * 10L;
        append(t, (new StringBuilder(String.valueOf(first.condensedFormat(firstTrauma)))).append(" (").append(first.getMainName()).append(") + ").append(second.condensedFormat(secondTrauma)).append(" (").append(second.getMainName()).append(") + ").append(third.condensedFormat(thirdTrauma)).append(" (").append(third.getMainName()).append(") x 10 = ").append(first.condensedFormat(traumaBonus)).append(" pts\n\nRemaining Evil Energy: ").toString());
        totalScore += traumaBonus;
        long EEMulti = 0x3b9aca00L;
        long EEBonus = EEMulti * (long)evilEnergy;
        append(t, (new StringBuilder(String.valueOf(evilEnergy))).append(" x ").append(first.condensedFormat(EEMulti)).append(" = ").append(first.condensedFormat(EEBonus)).append("\n").toString());
        totalScore += EEBonus;
        long corruptionBonus = 0L;
        for(int i = 0; i < 3; i++)
        {
            Chosen c = getCast()[i];
            long cCorruption = 0L;
            append(t, (new StringBuilder("\n")).append(c.getMainName()).append("'s Corruption: ").toString());
            if((!c.isVVirg() || c.isRuthless()) && c.vStart.booleanValue() || c.timesSlaughtered() > 0)
            {
                long added = 0L;
                if(c.isRuthless())
                    if(c.getMorality() > 66)
                    {
                        long amount = 0x5f5e100L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getMorality() > 33)
                    {
                        long amount = 0x2625a00L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x1312d00L;
                        cCorruption += amount;
                        added += amount;
                    }
                if(!c.isVVirg())
                    if(c.getMorality() > 66)
                    {
                        long amount = 0x24e160300L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getMorality() > 33)
                    {
                        long amount = 0xec08ce00L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x76046700L;
                        cCorruption += amount;
                        added += amount;
                    }
                if(c.timesSlaughtered() > 0)
                    if(c.getMorality() > 66)
                    {
                        long amount = 0xe680992c00L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getMorality() > 33)
                    {
                        long amount = 0x5c33707800L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x2e19b83c00L;
                        cCorruption += amount;
                        added += amount;
                    }
                append(t, (new StringBuilder(String.valueOf(c.condensedFormat(added)))).append(" (Morality)").toString());
            }
            if((!c.isCVirg() || c.isLustful()) && c.cStart.booleanValue() || c.timesFantasized() > 0)
            {
                if(cCorruption > 0L)
                    append(t, " + ");
                long added = 0L;
                if(c.isLustful())
                    if(c.getInnocence() > 66)
                    {
                        long amount = 0x5f5e100L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getInnocence() > 33)
                    {
                        long amount = 0x2625a00L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x1312d00L;
                        cCorruption += amount;
                        added += amount;
                    }
                if(!c.isCVirg())
                    if(c.getInnocence() > 66)
                    {
                        long amount = 0x24e160300L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getInnocence() > 33)
                    {
                        long amount = 0xec08ce00L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x76046700L;
                        cCorruption += amount;
                        added += amount;
                    }
                if(c.timesFantasized() > 0)
                    if(c.getInnocence() > 66)
                    {
                        long amount = 0xe680992c00L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getInnocence() > 33)
                    {
                        long amount = 0x5c33707800L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x2e19b83c00L;
                        cCorruption += amount;
                        added += amount;
                    }
                append(t, (new StringBuilder(String.valueOf(c.condensedFormat(added)))).append(" (Innocence)").toString());
            }
            if((!c.isAVirg() || c.isMeek()) && c.aStart.booleanValue() || c.timesDetonated() > 0)
            {
                if(cCorruption > 0L)
                    append(t, " + ");
                long added = 0L;
                if(c.isMeek())
                    if(c.getConfidence() > 66)
                    {
                        long amount = 0x5f5e100L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getConfidence() > 33)
                    {
                        long amount = 0x2625a00L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x1312d00L;
                        cCorruption += amount;
                        added += amount;
                    }
                if(!c.isAVirg())
                    if(c.getConfidence() > 66)
                    {
                        long amount = 0x24e160300L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getConfidence() > 33)
                    {
                        long amount = 0xec08ce00L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x76046700L;
                        cCorruption += amount;
                        added += amount;
                    }
                if(c.timesDetonated() > 0)
                    if(c.getConfidence() > 66)
                    {
                        long amount = 0xe680992c00L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getConfidence() > 33)
                    {
                        long amount = 0x5c33707800L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x2e19b83c00L;
                        cCorruption += amount;
                        added += amount;
                    }
                append(t, (new StringBuilder(String.valueOf(c.condensedFormat(added)))).append(" (Confidence)").toString());
            }
            if((!c.isModest() || c.isDebased()) && c.mStart.booleanValue() || c.timesStripped() > 0)
            {
                if(cCorruption > 0L)
                    append(t, " + ");
                long added = 0L;
                if(c.isDebased())
                    if(c.getDignity() > 66)
                    {
                        long amount = 0x5f5e100L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getDignity() > 33)
                    {
                        long amount = 0x2625a00L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x1312d00L;
                        cCorruption += amount;
                        added += amount;
                    }
                if(!c.isModest())
                    if(c.getDignity() > 66)
                    {
                        long amount = 0x24e160300L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getDignity() > 33)
                    {
                        long amount = 0xec08ce00L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x76046700L;
                        cCorruption += amount;
                        added += amount;
                    }
                if(c.timesStripped() > 0)
                    if(c.getDignity() > 66)
                    {
                        long amount = 0xe680992c00L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    if(c.getDignity() > 33)
                    {
                        long amount = 0x5c33707800L;
                        cCorruption += amount;
                        added += amount;
                    } else
                    {
                        long amount = 0x2e19b83c00L;
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
                    long added = 0x48c27395000L;
                    if(getRelationship(i, j) == -4)
                        added = 0x9184e72a000L;
                    append(t, (new StringBuilder(String.valueOf(first.condensedFormat(added)))).append(" (").append(getCast()[i].getMainName()).append(" vs. ").append(getCast()[j].getMainName()).append(")").toString());
                    enmityBonus += added;
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
            newChosen.setNumber(0);
            newChosen.generate(newWorld);
            newWorld.addChosen(newChosen);
            newWorld.copySettings(t, this);
            newWorld.copyToggles(this);
            saves.endSave(newWorld, "New Game+");
            wobj.serializeSaveData(saves);
            append(t, (new StringBuilder("A new save file has been added in slot ")).append(saves.getSaves().length).append(" which allows you to go back and start from Day 1 against this same group of Chosen.  You may also export this file from the shop menu in order to let others try to beat your score.  ").toString());
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
        int newResolved[] = new int[resolvedBreaks.length + 1];
        newResolved[newResolved.length - 1] = pendingBreaks[0];
        for(int i = 0; i < newBreaks.length; i++)
            newBreaks[i] = pendingBreaks[i + 1];

        for(int i = 0; i < resolvedBreaks.length; i++)
            newResolved[i] = resolvedBreaks[i];

        pendingBreaks = newBreaks;
        resolvedBreaks = newResolved;
    }

    public Boolean isResolved(int index)
    {
        for(int i = 0; i < resolvedBreaks.length; i++)
            if(resolvedBreaks[i] == index)
                return Boolean.valueOf(true);

        return Boolean.valueOf(false);
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
        evilEnergy -= 5;
        bodyStatus[10] = Boolean.valueOf(true);
    }

    public void applySynthesis()
    {
        evilEnergy -= 10;
        bodyStatus[18] = Boolean.valueOf(true);
    }

    public void applyVanity()
    {
        evilEnergy -= 6;
        bodyStatus[14] = Boolean.valueOf(true);
    }

    public void applySpite()
    {
        evilEnergy -= 6;
        bodyStatus[13] = Boolean.valueOf(true);
    }

    public void applyDominance()
    {
        evilEnergy -= 6;
        bodyStatus[12] = Boolean.valueOf(true);
    }

    public void applyAmbition()
    {
        evilEnergy -= 6;
        bodyStatus[11] = Boolean.valueOf(true);
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

    public void addCaptureTwo()
    {
        evilEnergy -= 5;
        bodyStatus[16] = Boolean.valueOf(true);
    }

    public void addCaptureThree()
    {
        evilEnergy -= 10;
        bodyStatus[17] = Boolean.valueOf(true);
    }

    public void toggleAmbush()
    {
        bodyStatus[2] = Boolean.valueOf(!bodyStatus[2].booleanValue());
    }

    public void enhanceFour()
    {
        evilEnergy -= 2;
        bodyStatus[15] = Boolean.valueOf(true);
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

    public Boolean validLine(int test)
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
                if(!target.getViolence().booleanValue() && !target.getService().booleanValue() && !target.getBeg().booleanValue() && !target.getCover().booleanValue() && !target.isDefiled().booleanValue() && validLine(5).booleanValue())
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
                else
                if(target.isInseminated().booleanValue() && validLine(18).booleanValue())
                    nextLine = 18;
                else
                if(target.isOrgasming().booleanValue() && validLine(19).booleanValue())
                    nextLine = 19;
                else
                if(target.isSodomized().booleanValue() && validLine(20).booleanValue())
                    nextLine = 20;
                else
                if(target.isBroadcasted().booleanValue() && validLine(21).booleanValue())
                    nextLine = 21;
            } else
            if(target.getLastAction() < 5 && validLine(target.getLastAction()).booleanValue())
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
                if(nextLine == 9)
                {
                    if(nextSpeaker.getDignityBreakage() < 1)
                        nextLine = lastLine;
                } else
                if(nextLine == 18)
                {
                    if(nextSpeaker.getMoralityBreakage() < 2)
                        nextLine = lastLine;
                } else
                if(nextLine == 19)
                {
                    if(nextSpeaker.getInnocenceBreakage() < 2)
                        nextLine = lastLine;
                } else
                if(nextLine == 20)
                {
                    if(nextSpeaker.getConfidenceBreakage() < 2)
                        nextLine = lastLine;
                } else
                if(nextLine == 21 && nextSpeaker.getDignityBreakage() < 2)
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
            {
                if(nextSpeaker.getDignityBreakage() >= target.getDignityBreakage())
                    nextLine = lastLine;
                else
                if(nextLine == 9 && nextSpeaker.getDignityBreakage() > 0)
                    nextLine = lastLine;
            } else
            if(nextLine == 18)
            {
                if(nextSpeaker.getMoralityBreakage() >= 2)
                    nextLine = lastLine;
            } else
            if(nextLine == 19)
            {
                if(nextSpeaker.getInnocenceBreakage() >= 2)
                    nextLine = lastLine;
            } else
            if(nextLine == 20)
            {
                if(nextSpeaker.getConfidenceBreakage() >= 2)
                    nextLine = lastLine;
            } else
            if(nextLine == 21 && nextSpeaker.getDignityBreakage() >= 2)
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
                            nextSpeaker.say(t, (new StringBuilder("The Thralls look like they'd rather go after ")).append(target.getMainName()).append(".").toString());
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
                        nextSpeaker.say(t, (new StringBuilder("Fight back, ")).append(target.getMainName()).append("!  Beat 'em up!").toString());
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
                        {
                            if(tickleOn.booleanValue())
                                nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("...  The way they're treating ").append(target.himHer()).append("...").toString());
                            else
                                nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("...  Those wounds...").toString());
                        } else
                        if(nextSpeaker.getMorality() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Are you alright, ")).append(target.getMainName()).append("?").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("!  Damn it...!").toString());
                    } else
                    if(nextSpeaker.getMorality() > 66)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("...  I'm sorry I couldn't protect you...").toString());
                    else
                    if(nextSpeaker.getMorality() > 33)
                    {
                        if(tickleOn.booleanValue())
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("!  A-Are you okay?").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("!  Y-You're bleeding!").toString());
                    } else
                    {
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("!  I wish I were stronger...!").toString());
                    }
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
                {
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
                if(nextLine == 18)
                {
                    if(nextSpeaker.getMorality() > 66)
                    {
                        if(nextSpeaker.getInnocence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("It's alright, ")).append(target.getMainName()).append(", getting raped doesn't make you a bad person!").toString());
                        else
                        if(nextSpeaker.getInnocence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("You resisted as best you could, ")).append(target.getMainName()).append("...").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("I hope you don't blame yourself for this, ")).append(target.getMainName()).append("...").toString());
                    } else
                    if(nextSpeaker.getMorality() > 33)
                    {
                        if(nextSpeaker.getInnocence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("Oh no, they're doing gross stuff to ")).append(target.getMainName()).append(" now...").toString());
                        else
                        if(nextSpeaker.getInnocence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Damn, I couldn't stop them from raping ")).append(target.getMainName()).append("...").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Don't worry, ")).append(target.getMainName()).append(", you won't get pregnant.  Probably.").toString());
                    } else
                    if(nextSpeaker.getInnocence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("It can actually feel kinda nice if you don't resist, ")).append(target.getMainName()).append("...").toString());
                    else
                    if(nextSpeaker.getInnocence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("Just let it happen, ")).append(target.getMainName()).append(".").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("I'd recommend that you stop trying to fight back, ")).append(target.getMainName()).append(".").toString());
                } else
                if(nextLine == 19)
                {
                    if(nextSpeaker.getInnocence() > 66)
                    {
                        if(nextSpeaker.getDignity() > 66)
                            nextSpeaker.say(t, (new StringBuilder("Don't be embarrassed about feeling good, ")).append(target.getMainName()).append("!").toString());
                        else
                        if(nextSpeaker.getDignity() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Ah, they're forcing ")).append(target.getMainName()).append(" to feel good...").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Why not just let yourself feel good, ")).append(target.getMainName()).append("?").toString());
                    } else
                    if(nextSpeaker.getInnocence() > 33)
                    {
                        if(nextSpeaker.getDignity() > 66)
                            nextSpeaker.say(t, (new StringBuilder("You don't have to force yourself not to cum, ")).append(target.getMainName()).append(".").toString());
                        else
                        if(nextSpeaker.getDignity() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Looks like they're forcing ")).append(target.getMainName()).append(" to cum...").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("You should stop trying not to cum, ")).append(target.getMainName()).append(".").toString());
                    } else
                    if(nextSpeaker.getDignity() > 66)
                        nextSpeaker.say(t, (new StringBuilder("There's no shame in feeling the physiological orgasm response, ")).append(target.getMainName()).append(".").toString());
                    else
                    if(nextSpeaker.getDignity() > 33)
                        nextSpeaker.say(t, (new StringBuilder("It seems that ")).append(target.getMainName()).append(" is being forced to orgasm.").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("It is less painful when you cooperate, ")).append(target.getMainName()).append(".").toString());
                } else
                if(nextLine == 20)
                {
                    if(nextSpeaker.getConfidence() > 66)
                    {
                        if(nextSpeaker.getDignity() > 66)
                            nextSpeaker.say(t, (new StringBuilder("Endure it, ")).append(target.getMainName()).append(".").toString());
                        else
                        if(nextSpeaker.getDignity() > 33)
                            nextSpeaker.say(t, (new StringBuilder("Hold on, ")).append(target.getMainName()).append("!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("I can't help you this time, ")).append(target.getMainName()).append(".").toString());
                    } else
                    if(nextSpeaker.getInnocence() > 33)
                    {
                        if(nextSpeaker.getDignity() > 66)
                            nextSpeaker.say(t, (new StringBuilder("You'll be alright, ")).append(target.getMainName()).append(".").toString());
                        else
                        if(nextSpeaker.getDignity() > 33)
                            nextSpeaker.say(t, (new StringBuilder("I'm on my way, ")).append(target.getMainName()).append("!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("I know what you're going through, ")).append(target.getMainName()).append("...").toString());
                    } else
                    if(nextSpeaker.getDignity() > 66)
                        nextSpeaker.say(t, (new StringBuilder("I know this isn't enough to break you, ")).append(target.getMainName()).append("...").toString());
                    else
                    if(nextSpeaker.getDignity() > 33)
                        nextSpeaker.say(t, (new StringBuilder("I-I'll try to help, ")).append(target.getMainName()).append("!").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("I'm sorry I'm not strong enough to help, ")).append(target.getMainName()).append("...").toString());
                } else
                if(nextLine == 21)
                    if(nextSpeaker.getDignity() > 66)
                    {
                        if(nextSpeaker.getInnocence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("I know it's embarrassing, ")).append(target.getMainName()).append(", but you'll be fine!").toString());
                        else
                        if(nextSpeaker.getInnocence() > 33)
                            nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append("...  I wish we could stop everyone from seeing us like this...").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("It's alright, ")).append(target.getMainName()).append(", people should be less interested in watching videos of your humiliation now that videos of mine are already out there.").toString());
                    } else
                    if(nextSpeaker.getDignity() > 33)
                    {
                        if(nextSpeaker.getInnocence() > 66)
                            nextSpeaker.say(t, (new StringBuilder("It's really embarrassing, isn't it, ")).append(target.getMainName()).append("?").toString());
                        else
                        if(nextSpeaker.getInnocence() > 33)
                            nextSpeaker.say(t, (new StringBuilder("The public may mock us now, ")).append(target.getMainName()).append(", but they'll be thanking us in the end.").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("I'll find a way to cheer ")).append(target.getMainName()).append(" up after we're done here...").toString());
                    } else
                    if(nextSpeaker.getInnocence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("There's no reason to be embarrassed, ")).append(target.getMainName()).append("!").toString());
                    else
                    if(nextSpeaker.getInnocence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("The public may mock us, ")).append(target.getMainName()).append(", but it changes nothing.").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("Perhaps I should let them film me some more in order to draw attention from ")).append(target.getMainName()).append("'s humiliation.").toString());
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
                        nextSpeaker.say(t, (new StringBuilder("How did you manage to get your clothes torn off so quickly, ")).append(target.getMainName()).append("?").toString());
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
            {
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
            } else
            if(nextLine == 18)
            {
                if(nextSpeaker.getMorality() > 66)
                {
                    if(nextSpeaker.getInnocence() > 66)
                        nextSpeaker.say(t, (new StringBuilder("Why'd you let them rape you, ")).append(target.getMainName()).append("?").toString());
                    else
                    if(nextSpeaker.getInnocence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("How can you let them defile you so easily, ")).append(target.getMainName()).append("?").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("This is what you get for giving in to hatred, ")).append(target.getMainName()).append(".").toString());
                } else
                if(nextSpeaker.getMorality() > 33)
                {
                    if(nextSpeaker.getInnocence() > 66)
                    {
                        if(target.getGender().equals("male"))
                            nextSpeaker.say(t, (new StringBuilder("Silly Thralls, ")).append(target.getMainName()).append(" can't be a mommy!").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Is ")).append(target.getMainName()).append(" gonna become a mommy?").toString());
                    } else
                    if(nextSpeaker.getInnocence() > 33)
                        nextSpeaker.say(t, (new StringBuilder("Are you seriously having sex in the middle of battle, ")).append(target.getMainName()).append("?").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("You let them break your Sexual Barrier?  ")).append(target.getMainName()).append(", you idiot...").toString());
                } else
                if(nextSpeaker.getInnocence() > 66)
                    nextSpeaker.say(t, (new StringBuilder("Hahah, they're doing naughty stuff with ")).append(target.getMainName()).append(".").toString());
                else
                if(nextSpeaker.getInnocence() > 33)
                    nextSpeaker.say(t, (new StringBuilder("You make a good fleshlight, ")).append(target.getMainName()).append(".").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder("This is what you get for being so weak, ")).append(target.getMainName()).append(".").toString());
            } else
            if(nextLine == 19)
            {
                if(nextSpeaker.getInnocence() > 66)
                {
                    if(nextSpeaker.getMorality() > 66)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", it's wrong to feel good from something like that!").toString());
                    else
                    if(nextSpeaker.getMorality() > 33)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" must be a huge pervert to feel good from something like that.").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("Hahah, ")).append(target.getMainName()).append(", you pervert, are you actually feeling good?").toString());
                } else
                if(nextSpeaker.getInnocence() > 33)
                {
                    if(nextSpeaker.getMorality() > 66)
                        nextSpeaker.say(t, (new StringBuilder("You're cumming, ")).append(target.getMainName()).append("?  How can you live with the shame?").toString());
                    else
                    if(nextSpeaker.getMorality() > 33)
                        nextSpeaker.say(t, (new StringBuilder("I can't believe ")).append(target.getMainName()).append(" is actually cumming.").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", you slut, are you actually cumming?").toString());
                } else
                if(nextSpeaker.getMorality() > 66)
                    nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(", do you really lack the willpower to contain your lust?").toString());
                else
                if(nextSpeaker.getMorality() > 33)
                    nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" must be very weak-willed to allow ").append(target.himHer()).append("self to orgasm here.").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder("Oh?  Wasn't enjoying that sort of thing supposed to be 'wrong', ")).append(target.getMainName()).append("?").toString());
            } else
            if(nextLine == 20)
            {
                if(nextSpeaker.getConfidence() > 66)
                {
                    if(nextSpeaker.getMorality() > 66)
                        nextSpeaker.say(t, (new StringBuilder("Sorry, ")).append(target.getMainName()).append(", but there are more deserving victims I need to save before I get around to you.").toString());
                    else
                    if(nextSpeaker.getMorality() > 33)
                    {
                        if(tickleOn.booleanValue())
                            nextSpeaker.say(t, (new StringBuilder("Stop laughing, ")).append(target.getMainName()).append(", it's distracting.").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Stop screaming, ")).append(target.getMainName()).append(", it's distracting.").toString());
                    } else
                    if(tickleOn.booleanValue())
                        nextSpeaker.say(t, (new StringBuilder("Are you really so weak that tickling is enough to break you, ")).append(target.getMainName()).append("?").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("Scream all you like, ")).append(target.getMainName()).append(", I'm still not going to help you.").toString());
                } else
                if(nextSpeaker.getConfidence() > 33)
                {
                    if(nextSpeaker.getMorality() > 66)
                    {
                        if(tickleOn.booleanValue())
                            nextSpeaker.say(t, (new StringBuilder("Is it really that hard not to laugh when we're doing something like this, ")).append(target.getMainName()).append("?").toString());
                        else
                            nextSpeaker.say(t, (new StringBuilder("Is it really that painful, ")).append(target.getMainName()).append("?").toString());
                    } else
                    if(nextSpeaker.getMorality() > 33)
                        nextSpeaker.say(t, (new StringBuilder("Just be quiet, ")).append(target.getMainName()).append("...").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("Heh, I might take a break from the fight to watch ")).append(target.getMainName()).append(" get what's coming to ").append(target.himHer()).append(".").toString());
                } else
                if(nextSpeaker.getMorality() > 66)
                    nextSpeaker.say(t, (new StringBuilder("Come on, ")).append(target.getMainName()).append(", there are people who are suffering a lot more than you...").toString());
                else
                if(nextSpeaker.getMorality() > 33)
                    nextSpeaker.say(t, (new StringBuilder("Guess ")).append(target.getMainName()).append(" wasn't so strong after all...").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder("Maybe now ")).append(target.getMainName()).append(" will stop thinking ").append(target.heShe()).append("'s so much better than everyone else...").toString());
            } else
            if(nextLine == 21)
                if(nextSpeaker.getDignity() > 66)
                {
                    if(nextSpeaker.getMorality() > 66)
                        nextSpeaker.say(t, (new StringBuilder("This is what you get for not paying attention to the state of your clothes, ")).append(target.getMainName()).append("...").toString());
                    else
                    if(nextSpeaker.getMorality() > 33)
                        nextSpeaker.say(t, (new StringBuilder("You're going to make the rest of us look bad by association, ")).append(target.getMainName()).append(".").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("Your pathetic state only makes me look that much better, ")).append(target.getMainName()).append(".").toString());
                } else
                if(nextSpeaker.getDignity() > 33)
                {
                    if(nextSpeaker.getMorality() > 66)
                        nextSpeaker.say(t, (new StringBuilder(String.valueOf(target.getMainName()))).append(" is going to make the people lose hope in us...").toString());
                    else
                    if(nextSpeaker.getMorality() > 33)
                        nextSpeaker.say(t, (new StringBuilder("You're distracting me, ")).append(target.getMainName()).append(".").toString());
                    else
                        nextSpeaker.say(t, (new StringBuilder("Now everyone will see you for the failure you are, ")).append(target.getMainName()).append("!").toString());
                } else
                if(nextSpeaker.getMorality() > 66)
                    nextSpeaker.say(t, (new StringBuilder("This is what you get for caring so much about your public image, ")).append(target.getMainName()).append(".").toString());
                else
                if(nextSpeaker.getMorality() > 33)
                    nextSpeaker.say(t, (new StringBuilder("I thought you were the type to try harder to keep your dignity, ")).append(target.getMainName()).append(".").toString());
                else
                    nextSpeaker.say(t, (new StringBuilder("Hahahah, you look so pathetic, ")).append(target.getMainName()).append("!").toString());
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

    public void setBattleRound(int newRound)
    {
        battleRound = newRound;
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
        if(bodyStatus[11].booleanValue() || bodyStatus[12].booleanValue() || bodyStatus[13].booleanValue() || bodyStatus[14].booleanValue())
            value += 6;
        if(bodyStatus[15].booleanValue())
            value += 2;
        if(bodyStatus[16].booleanValue())
            value += 5;
        if(bodyStatus[17].booleanValue())
            value += 10;
        if(bodyStatus[18].booleanValue())
            value += 10;
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
        Boolean defiler = Boolean.valueOf(false);
        if(bodyStatus[11].booleanValue() || bodyStatus[12].booleanValue() || bodyStatus[13].booleanValue() || bodyStatus[14].booleanValue())
            defiler = Boolean.valueOf(true);
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
                        if(techs[26].isOwned().booleanValue())
                        {
                            append(t, "[");
                            if(bodyStatus[15].booleanValue())
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
                if(techs[27].isOwned().booleanValue())
                {
                    append(t, "[");
                    if(bodyStatus[16].booleanValue())
                    {
                        append(t, "X");
                    } else
                    {
                        append(t, " ");
                        if(captureCost == 0)
                            captureCost = 5;
                    }
                    append(t, "]");
                    if(techs[32].isOwned().booleanValue())
                    {
                        append(t, "[");
                        if(bodyStatus[17].booleanValue())
                        {
                            append(t, "X");
                        } else
                        {
                            append(t, " ");
                            if(captureCost == 0)
                                captureCost = 10;
                        }
                        append(t, "]");
                    }
                }
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
                if(suppressors == 1)
                {
                    if(bodyStatus[3].booleanValue())
                        append(t, "Hunger [HATE]");
                    else
                    if(bodyStatus[4].booleanValue())
                        append(t, "Lust [PLEA]");
                    else
                    if(bodyStatus[5].booleanValue())
                    {
                        if(tickleOn.booleanValue())
                            append(t, "Anger [ANTI]");
                        else
                            append(t, "Anger [INJU]");
                    } else
                    if(bodyStatus[6].booleanValue())
                        append(t, "Mania [EXPO]");
                    if(techs[21].isOwned().booleanValue() && !defiler.booleanValue())
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
                        if(tickleOn.booleanValue())
                            status = "Anger [ANTI]";
                        if(first.length() > 0)
                            second = status;
                        else
                            first = status;
                    }
                    if(bodyStatus[6].booleanValue())
                        second = "Mania [EXPO]";
                    append(t, (new StringBuilder(String.valueOf(first))).append(", ").append(second).toString());
                } else
                if(defiler.booleanValue())
                {
                    if(techs[33].isOwned().booleanValue())
                        append(t, "None (Cost: 10 EE)");
                    else
                        append(t, "(LOCKED)");
                } else
                {
                    append(t, "None (free)");
                }
                append(t, "\n");
            }
            if(techs[22].isOwned().booleanValue() || techs[23].isOwned().booleanValue() || techs[24].isOwned().booleanValue() || techs[25].isOwned().booleanValue())
            {
                append(t, "Defiler: ");
                if(suppressors == 2)
                    append(t, "(LOCKED)");
                else
                if(suppressors == 1 && !defiler.booleanValue())
                {
                    if(techs[33].isOwned().booleanValue())
                        append(t, "None (Cost: 16 EE)");
                    else
                        append(t, "(LOCKED)");
                } else
                if(!defiler.booleanValue())
                    append(t, "None (Cost: 6 EE)");
                else
                if(bodyStatus[11].booleanValue())
                    append(t, "Ambition [HATE, PLEA]");
                else
                if(bodyStatus[12].booleanValue())
                {
                    if(tickleOn.booleanValue())
                        append(t, "Dominance [PLEA, ANTI]");
                    else
                        append(t, "Dominance [PLEA, INJU]");
                } else
                if(bodyStatus[13].booleanValue())
                {
                    if(tickleOn.booleanValue())
                        append(t, "Spite [ANTI, EXPO]");
                    else
                        append(t, "Spite [INJU, EXPO]");
                } else
                if(bodyStatus[14].booleanValue())
                    append(t, "Vanity [EXPO, HATE]");
            }
            append(t, "\n");
            if(bodyStatus[18].booleanValue())
            {
                if(bodyStatus[11].booleanValue())
                {
                    if(bodyStatus[3].booleanValue())
                        append(t, "Your Commander is a huge, Sphinx-like Demon, a beast with a human face, which prowls about on all fours.  Capable of speaking to the Chosen, it taunts them in order to pollute their minds with hateful feelings so that their defenses weaken enough to let it fuck them with its foot-long flared penis.  It ");
                    else
                    if(bodyStatus[4].booleanValue())
                        append(t, "Your Commander is a huge Demon which prowls about on all fours, its underside lined with tentacles that capture and stimulate the Chosen in order to prepare them to be fucked by its foot-long flared penis.  Your Commander ");
                    else
                    if(bodyStatus[5].booleanValue())
                    {
                        if(tickleOn.booleanValue())
                            append(t, "Your Commander is a huge Demon which prowls about on six legs, the middle pair ending in nimble claws meant for further stimulating and tormenting captured prey.  Slung under its body is a foot-long flared penis.  Your Commander ");
                        else
                            append(t, "Your Commander is a huge Demon which prowls about on all fours.  Slung under its body is an enormous double penis, each shaft almost two feet long and covered in rough barbs which inflict as much pain as pleasure.  It ");
                    } else
                    if(bodyStatus[6].booleanValue())
                        append(t, "Your Commander is a huge Demon made of transparent, clothes-dissolving slime.  It pounces on Chosen and then rapes them with a huge pseudopod, its see-through nature ensuring that spectators can see every humiliating detail.  Your Commander ");
                } else
                if(bodyStatus[12].booleanValue())
                {
                    if(bodyStatus[3].booleanValue())
                        append(t, "Your Commander is a huge, shambling mass which uses long tentacles to pull its prey into an internal chamber full of hallucinogenic aphrodisiac and smaller pleasure-inducing tentacles.  The potent fluid allows you to form a psychic link with the captives' minds, inducing hateful thoughts in order to break their Sexual Barriers.  Your Commander ");
                    else
                    if(bodyStatus[4].booleanValue())
                        append(t, "Your Commander is a huge, shambling mass which uses long tentacles to pull its prey into an internal chamber full of mind-breakingly powerful aphrodisiac and advanced pleasure-inducing tentacles.  It ");
                    else
                    if(bodyStatus[5].booleanValue())
                    {
                        if(tickleOn.booleanValue())
                            append(t, "Your Commander is a huge, shambling mass which uses long tentacles to pull its prey into an internal chamber full of aphrodisiac and smaller tentacles specialized to mercilessly tickle its victims even as it forces them to cum.  Your Commander ");
                        else
                            append(t, "Your Commander is a huge, shambling mass which uses long tentacles to pull its prey into an internal chamber full of aphrodisiac and smaller tentacles shaped like various torture implements to induce mind-breaking pleasure and pain.  It ");
                    } else
                    if(bodyStatus[6].booleanValue())
                        append(t, "Your Commander is a huge, shambling mass made of transparent slime.  It's capable of shaping itself into powerful pseudopods in order to capture and pull the Chosen inside, and its body contains substances that dissolve clothes and induce sexual pleasure.  Your Commander ");
                } else
                if(bodyStatus[13].booleanValue())
                {
                    if(bodyStatus[3].booleanValue())
                        append(t, "Your Commander is a huge, vaguely-humanoid titan of a Demon covered in mouths that constantly gibber madness, conveying your hateful thoughts.  Their teeth inflict painful bites, and their tongues constantly writhe in search of orifices to violate.  Your Commander ");
                    else
                    if(bodyStatus[4].booleanValue())
                        append(t, "Your Commander is a huge, vaguely-humanoid titan of a Demon with countless nimble arms that are capable of restraining your prey and inflicting various humiliations and tortures in addition to more pleasurable stimualations.  It ");
                    else
                    if(bodyStatus[5].booleanValue())
                    {
                        if(tickleOn.booleanValue())
                            append(t, "Your Commander is a huge, vaguely-humanoid titan of a Demon with countless arms and fine-pointed claws capable of precisely tickling every single vulnerable spot of its victims' bodies at once.  It ");
                        else
                            append(t, "Your Commander is a huge, vaguely-humanoid titan of a Demon with several bestial arms equipped with razor-sharp claws capable of restraining your prey and inflicting various tortures and humiliations.  It ");
                    } else
                    if(bodyStatus[6].booleanValue())
                        append(t, "Your Commander is a huge, vaguely-humanoid titan of a Demon with several extra muscular arms capable of restraining its prey and inflicting various tortures and humiliations.  It's aided in its task by the fact that its skin is coated in clothes-dissolving slime.  Your Commander ");
                } else
                if(bodyStatus[14].booleanValue())
                    if(bodyStatus[3].booleanValue())
                        append(t, "Your Commander is a huge, biomechanical Demon with electrified tentacles capable of hijacking communications infrastructure and using it to show unsuspecting people footage of how it torments its prey.  At the same time, it uses advanced technology to allow the audience to take part in the violation.  Your Commander ");
                    else
                    if(bodyStatus[4].booleanValue())
                        append(t, "Your Commander is a huge, biomechanical Demon with electrified tentacles capable of hijacking communications infrastructure and using it to show unsuspecting people footage of how it torments its prey.  It's equipped with vibrators and other sex toys for maximum humiliation.  Your Commander ");
                    else
                    if(bodyStatus[5].booleanValue())
                    {
                        if(tickleOn.booleanValue())
                            append(t, "Your Commander is a huge, biomechanical Demon with electrified tentacles capable of hijacking communications infrastructure and using it to show unsuspecting people footage of how it torments its prey.  It's equipped with various tickle torture implements in order to emphasize its captives' helplessness.  Your Commander ");
                        else
                            append(t, "Your Commander is a huge, biomechanical Demon with electrified tentacles capable of hijacking communications infrastructure and using it to show unsuspecting people footage of how it torments its prey.  It's equipped with weapons and torture implements in order to emphasize its captives' helplessness.  Your Commander ");
                    } else
                    {
                        append(t, "Your Commander is a huge, biomechanical Demon with electrified tentacles capable of hijacking communications infrastructure and using it to show unsuspecting people footage of how it torments its prey.  It's equipped with various clothes-destroying implements and high-quality cameras in order to prioritize exposing and humiliating its captives as efficiently as possible.  Your Commander ");
                    }
            } else
            if(defiler.booleanValue())
            {
                if(bodyStatus[11].booleanValue())
                    append(t, "Your Commander is a huge Demon which prowls about on all fours with a foot-long flared penis slung under its massive body.  It ");
                else
                if(bodyStatus[12].booleanValue())
                    append(t, "Your Commander is a huge, shambling mass which uses long tentacles to pull its prey into an internal chamber full of aphrodisiac and smaller pleasure-inducing tentacles.  It ");
                else
                if(bodyStatus[13].booleanValue())
                    append(t, "Your Commander is a huge, vaguely-humanoid titan of a Demon with several extra muscular arms capable of restraining its prey and inflicting various tortures and humiliations.  It ");
                else
                if(bodyStatus[14].booleanValue())
                    append(t, "Your Commander is a huge, biomechanical Demon with electrified tentacles capable of hijacking communications infrastructure and using it to show unsuspecting people footage of how it torments its prey.  It ");
            } else
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
                {
                    if(tickleOn.booleanValue())
                        append(t, "Your Commander is an oversized humanoid Demon with disproportionately large muscles which can pin the Chosen and hold them still enough to tickle them with brutal efficiency.  It ");
                    else
                        append(t, "Your Commander is an oversized humanoid Demon with disproportionately large muscles which can deal grievous injuries even to the supernaturally-durable bodies of the Chosen.  It ");
                } else
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
                    {
                        if(tickleOn.booleanValue())
                            append(t, "They drip with saliva that acts as a powerful drug, weakening and confusing their victims.  ");
                        else
                            append(t, "They're filled with razor-sharp teeth for chewing on their victims, injuring and further aggravating them.  ");
                    } else
                    if(bodyStatus[6].booleanValue())
                        append(t, "They drip with acidic saliva, and while this substance lacks the potency to injure the Chosen themselves, it will dissolve and weaken their clothes.  ");
                } else
                if(bodyStatus[4].booleanValue())
                {
                    append(t, "Your Commander is a giant humanoid Demon covered with various tentacles which seek out the most sensitive places on captured Chosen and force pleasure upon them.  ");
                    if(bodyStatus[5].booleanValue())
                    {
                        if(tickleOn.booleanValue())
                            append(t, "They're filled with a specialized slime which acts as a powerful drug for weakening and confusing the Chosen.  ");
                        else
                            append(t, "Some are small and nimble, but others are thick and incredibly strong, capable of punching through solid brick and twisting steel beams apart.  ");
                    } else
                    if(bodyStatus[6].booleanValue())
                        append(t, "They secrete an acidic fluid, and while this substance lacks the potency to injure the Chosen themselves, it will dissolve and weaken their clothes.  ");
                } else
                if(tickleOn.booleanValue())
                    append(t, "Your Commander is a giant humanoid Demon with various human tools embedded in its body.  Some are specialized for clothes removal, while the others are used to deliver a potent cocktail of psychoactive drugs which can weaken and disorient even the Chosen.  ");
                else
                    append(t, "Your Commander is a giant humanoid Demon with various human tools embedded in its body.  These tools include tanks of acid, spinning saws, chains, guns, and even flamethrowers.  They have been magically reinforced to deal significant damage to both the Chosen and their clothes.  ");
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
        if(bodyStatus[15].booleanValue())
            reportedDuration += 4;
        else
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
        else
        if(reportedDuration == 6)
            append(t, " for six rounds");
        if(bodyStatus[2].booleanValue())
        {
            append(t, " once you give the order");
            if(bodyStatus[17].booleanValue())
                append(t, ", up to four times");
            else
            if(bodyStatus[16].booleanValue())
                append(t, ", up to three times");
            else
            if(bodyStatus[8].booleanValue())
                append(t, ", up to twice");
        } else
        {
            append(t, " at the start of battle");
            if(bodyStatus[17].booleanValue())
                append(t, ", and then three more times whenever you give the order");
            else
            if(bodyStatus[16].booleanValue())
                append(t, ", and then two more times whenever you give the order");
            else
            if(bodyStatus[8].booleanValue())
                append(t, ", and then one more time once you give the order");
        }
        if(c == null)
            append(t, (new StringBuilder(".  It is worth ")).append(getCommanderValue()).append(" Evil Energy.  You have ").append(evilEnergy).append(" Evil Energy remaining.").toString());
        else
        if(bodyStatus[18].booleanValue())
        {
            String damages[] = new String[3];
            String breakType = "";
            if(bodyStatus[3].booleanValue())
                damages[1] = "HATE";
            else
            if(bodyStatus[4].booleanValue())
                damages[1] = "PLEA";
            else
            if(bodyStatus[5].booleanValue())
            {
                if(tickleOn.booleanValue())
                    damages[1] = "ANTI";
                else
                    damages[1] = "INJU";
            } else
            if(bodyStatus[6].booleanValue())
                damages[1] = "EXPO";
            if(bodyStatus[11].booleanValue())
            {
                damages[0] = "HATE";
                damages[2] = "PLEA";
                breakType = "Morality Break above 10k HATE";
            } else
            if(bodyStatus[12].booleanValue())
            {
                damages[0] = "PLEA";
                if(tickleOn.booleanValue())
                    damages[2] = "ANTI";
                else
                    damages[2] = "INJU";
                breakType = "Innocence Break above 10k PLEA";
            } else
            if(bodyStatus[13].booleanValue())
            {
                if(tickleOn.booleanValue())
                    damages[0] = "ANTI";
                else
                    damages[0] = "INJU";
                damages[2] = "EXPO";
                if(tickleOn.booleanValue())
                    breakType = "Confidence Break above 10k ANTI";
                else
                    breakType = "Confidence Break above 10k INJU";
            } else
            if(bodyStatus[14].booleanValue())
            {
                damages[0] = "EXPO";
                damages[2] = "HATE";
                breakType = "Dignity Break above 10k EXPO";
            }
            if(damages[0].equals(damages[1]))
                append(t, (new StringBuilder(", inflicting overwhelming levels of ")).append(damages[2]).append(", even higher levels of ").append(damages[0]).append(", and potentially causing ").append(breakType).append(".").toString());
            else
            if(damages[2].equals(damages[1]))
                append(t, (new StringBuilder(", inflicting overwhelming levels of ")).append(damages[2]).append(" and ").append(damages[0]).append(", and potentially causing ").append(breakType).append(".").toString());
            else
                append(t, (new StringBuilder(", inflicting overwhelming levels of ")).append(damages[0]).append(", ").append(damages[1]).append(", and ").append(damages[2]).append(", and potentially causing ").append(breakType).append(".").toString());
        } else
        if(defiler.booleanValue())
        {
            if(bodyStatus[11].booleanValue())
                append(t, ", inflicting overwhelming levels of HATE and PLEA, and potentially causing Morality Break above 10k HATE.");
            else
            if(bodyStatus[12].booleanValue())
            {
                if(tickleOn.booleanValue())
                    append(t, ", inflicting overwhelming levels of PLEA and ANTI, and potentially causing Innocence Break above 10k PLEA.");
                else
                    append(t, ", inflicting overwhelming levels of PLEA and INJU, and potentially causing Innocence Break above 10k PLEA.");
            } else
            if(bodyStatus[13].booleanValue())
            {
                if(tickleOn.booleanValue())
                    append(t, ", inflicting overwhelming levels of ANTI and EXPO, and potentially causing Confidence Break above 10k ANTI.");
                else
                    append(t, ", inflicting overwhelming levels of INJU and EXPO, and potentially causing Confidence Break above 10k INJU.");
            } else
            if(bodyStatus[14].booleanValue())
                append(t, ", inflicting overwhelming levels of EXPO and HATE, and potentially causing Dignity Break above 10k EXPO.");
        } else
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
                if(tickleOn.booleanValue())
                {
                    damages[place] = "TICK";
                    place++;
                    damages[place] = "ANTI";
                    place++;
                } else
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
                append(t, (new StringBuilder(", inflicting high levels of ")).append(damages[0]).append(", ").append(damages[2]).append(", ").append(damages[1]).append(", and ").append(damages[3]).append(".").toString());
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

    public Boolean upgradedCommander()
    {
        if(!bodyStatus[3].booleanValue() && !bodyStatus[4].booleanValue() && !bodyStatus[5].booleanValue() && !bodyStatus[6].booleanValue() && !bodyStatus[11].booleanValue() && !bodyStatus[12].booleanValue() && !bodyStatus[13].booleanValue() && !bodyStatus[14].booleanValue())
            return Boolean.valueOf(false);
        else
            return Boolean.valueOf(true);
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
        if(w.getTechs()[28].isOwned().booleanValue())
            evacuationComplete += 40;
        exterminationProgress = 0;
        exterminationComplete = 100;
        if(w.getTechs()[2].isOwned().booleanValue())
            exterminationComplete += 40;
        if(w.getTechs()[5].isOwned().booleanValue())
            exterminationComplete += 60;
        if(w.getTechs()[17].isOwned().booleanValue())
            exterminationComplete += 100;
        if(w.getTechs()[29].isOwned().booleanValue())
            exterminationComplete += 200;
        exterminationMultiplier = 100;
        captureDuration = 2;
        if(bodyStatus[15].booleanValue())
            captureDuration = 6;
        else
        if(bodyStatus[9].booleanValue())
            captureDuration = 5;
        else
        if(bodyStatus[7].booleanValue())
            captureDuration = 4;
        else
        if(bodyStatus[1].booleanValue())
            captureDuration = 3;
        else
            captureDuration = 2;
        capturesPossible = 0;
        if(bodyStatus[2].booleanValue() || upgradedCommander().booleanValue() || techs[31].isOwned().booleanValue() && bodyStatus[0].booleanValue())
            capturesPossible++;
        if(bodyStatus[17].booleanValue())
            capturesPossible += 3;
        else
        if(bodyStatus[16].booleanValue())
            capturesPossible += 2;
        else
        if(bodyStatus[8].booleanValue())
            capturesPossible++;
        nextCapture = null;
        nextSurround = null;
        barrierMulti = 10000L;
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
        StyleConstants.setFontSize(keyWord, textSize);
        StyleConstants.setForeground(keyWord, FOREGROUND);
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
        StyleConstants.setFontSize(keyWord, textSize);
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
        StyleConstants.setFontSize(keyWord, textSize);
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
        StyleConstants.setFontSize(keyWord, textSize);
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
        StyleConstants.setFontSize(keyWord, textSize);
        StyleConstants.setForeground(keyWord, FOREGROUND);
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
        StyleConstants.setFontSize(keyWord, textSize);
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
        StyleConstants.setFontSize(keyWord, textSize);
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

    public void blueAppend(JTextPane t, String s)
    {
        StyledDocument doc = t.getStyledDocument();
        SimpleAttributeSet keyWord = new SimpleAttributeSet();
        StyleConstants.setFontSize(keyWord, textSize);
        StyleConstants.setForeground(keyWord, BLUE);
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
        StyleConstants.setFontSize(keyWord, textSize);
        StyleConstants.setForeground(keyWord, BACKGROUND);
        StyleConstants.setFontFamily(keyWord, "DialogInput");
        StyleConstants.setBackground(keyWord, FOREGROUND);
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

    public void tierTwoAppend(JTextPane t, String s)
    {
        StyledDocument doc = t.getStyledDocument();
        SimpleAttributeSet keyWord = new SimpleAttributeSet();
        StyleConstants.setFontSize(keyWord, textSize);
        StyleConstants.setForeground(keyWord, BACKGROUND);
        StyleConstants.setFontFamily(keyWord, "DialogInput");
        StyleConstants.setBackground(keyWord, RED);
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
        genders = w.getGenders();
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

        setCustomStrings(w.getCustomStrings());
        setCustomBooleans(w.getCustomBooleans());
        recordedCommanders = w.getRecordedCommanders();
        commentary = w.getCommentary();
        actions = w.getActions();
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

            if(!legalSpread(statSeed, Boolean.valueOf(false)).booleanValue())
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

    public Boolean legalSpread(int statSeed[], Boolean customized)
    {
        Boolean goodStats = Boolean.valueOf(true);
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

        for(int i = 0; i < 4 && !customized.booleanValue(); i++)
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
        return goodStats;
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

    public String getEvacStatus(Boolean bar)
    {
        int percentage = (evacuationProgress * 100) / evacuationComplete;
        String result = (new StringBuilder(String.valueOf(evacuationProgress))).append("/").append(evacuationComplete).append(" (").append(percentage).append("%)").toString();
        if(bar.booleanValue())
        {
            for(int charCount = result.length(); charCount < 17;)
            {
                charCount++;
                result = (new StringBuilder(String.valueOf(result))).append(" ").toString();
            }

            result = (new StringBuilder(String.valueOf(result))).append("[").toString();
            int base = 20;
            int possible = 0;
            for(int i = 0; i < 3; i++)
                if(getCombatants()[i] != null && !currentCombatants[i].isSurrounded().booleanValue() && !currentCombatants[i].isCaptured().booleanValue())
                    possible += currentCombatants[i].FEARMulti() / 12;

            for(int i = 1; i < 21; i++)
                if(evacuationProgress * 20 >= evacuationComplete * i)
                    result = (new StringBuilder(String.valueOf(result))).append("#").toString();
                else
                if((evacuationProgress + base) * 20 >= evacuationComplete * i)
                    result = (new StringBuilder(String.valueOf(result))).append("=").toString();
                else
                if((evacuationProgress + base + possible) * 20 >= evacuationComplete * i)
                    result = (new StringBuilder(String.valueOf(result))).append("-").toString();
                else
                    result = (new StringBuilder(String.valueOf(result))).append(" ").toString();

            result = (new StringBuilder(String.valueOf(result))).append("]").toString();
        }
        return result;
    }

    public String getExterminationStatus(Boolean bar)
    {
        int percentage = (exterminationProgress * 100) / exterminationComplete;
        String result = (new StringBuilder(String.valueOf(exterminationProgress))).append("/").append(exterminationComplete).append(" (").append(percentage).append("%)").toString();
        if(bar.booleanValue())
        {
            for(int charCount = result.length(); charCount < 14;)
            {
                charCount++;
                result = (new StringBuilder(String.valueOf(result))).append(" ").toString();
            }

            result = (new StringBuilder(String.valueOf(result))).append("[").toString();
            int base = 0;
            int possible = 0;
            for(int i = 0; i < 3; i++)
                if(getCombatants()[i] != null)
                    if(!currentCombatants[i].isSurrounded().booleanValue() && !currentCombatants[i].isCaptured().booleanValue())
                    {
                        int usedExterminationMultiplier = exterminationMultiplier;
                        if(evacuationProgress >= evacuationComplete)
                            usedExterminationMultiplier = (usedExterminationMultiplier * 3) / 2;
                        base += (exterminationPerChosen * usedExterminationMultiplier) / 100;
                        possible += currentCombatants[i].FEARMulti() / 12;
                    } else
                    if(currentCombatants[i].getSurroundDuration() == 1 || currentCombatants[i].getCaptureProgression() == captureDuration || currentCombatants[i].timesDetonated() > 0 && currentCombatants[i].getCaptureProgression() + currentCombatants[i].getINJULevel() + 1 >= captureDuration)
                    {
                        int usedExterminationMultiplier = exterminationMultiplier;
                        if(evacuationProgress >= evacuationComplete)
                            usedExterminationMultiplier = (usedExterminationMultiplier * 3) / 2;
                        base += (exterminationPerChosen * usedExterminationMultiplier) / 100;
                    }

            for(int i = 1; i < 21; i++)
                if(exterminationProgress * 20 >= exterminationComplete * i)
                    result = (new StringBuilder(String.valueOf(result))).append("#").toString();
                else
                if((exterminationProgress + base) * 20 >= exterminationComplete * i)
                    result = (new StringBuilder(String.valueOf(result))).append("=").toString();
                else
                if((exterminationProgress + base + possible) * 20 >= exterminationComplete * i)
                    result = (new StringBuilder(String.valueOf(result))).append("-").toString();
                else
                    result = (new StringBuilder(String.valueOf(result))).append(" ").toString();

            result = (new StringBuilder(String.valueOf(result))).append("]").toString();
        }
        return result;
    }

    public void setRallyBonus(int amount, int initiative)
    {
        rallyBonus[initiative] = amount;
    }

    public int getRallyBonus()
    {
        return rallyBonus[0] + rallyBonus[1] + rallyBonus[2];
    }

    public void setDistractBonus(long amount, int initiative)
    {
        distractBonus[initiative] = (int)amount;
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
                append(t, (new StringBuilder("Evacuation: ")).append(getEvacStatus(Boolean.valueOf(true))).append("\n").toString());
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
            append(t, (new StringBuilder("Extermination: ")).append(getExterminationStatus(Boolean.valueOf(true))).append("\n\n").toString());
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
                    if(exterminationProgress < exterminationComplete)
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
                        append(t, "The reanimated Demons are fighting their last stand!  Combat will end next turn unless one of the Chosen is surrounded or captured.\n");
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
                    if(exterminationProgress < exterminationComplete)
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

    public void freshCustom(JTextPane t, JPanel p, JFrame f)
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

        for(int i = 0; i < groupScenes.length; i++)
            groupScenes[i] = Boolean.valueOf(false);

        nameGen(t, p, f);
    }

    public void nameGen(final JTextPane t, final JPanel p, final JFrame f)
    {
        p.removeAll();
        Chosen dummy = new Chosen();
        dummy.setNumber(0);
        if(customNames[0] == null)
        {
            customNames[0] = dummy.genName(this, nameSeed)[0];
            customNames[1] = dummy.genName(this, nameSeed)[1];
        }
        dummy.setNumber(1);
        if(customNames[2] == null)
        {
            customNames[2] = dummy.genName(this, nameSeed)[0];
            customNames[3] = dummy.genName(this, nameSeed)[1];
        }
        dummy.setNumber(2);
        if(customNames[4] == null)
        {
            customNames[4] = dummy.genName(this, nameSeed)[0];
            customNames[5] = dummy.genName(this, nameSeed)[1];
        }
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nGenerating a custom team of Chosen.  Their real names are (in order of appearance):\n\n").toString());
        if(gaijinStatus[0].booleanValue())
        {
            append(t, customNames[0]);
            if(customNames[1].length() > 0)
                append(t, (new StringBuilder(" ")).append(customNames[1]).toString());
        } else
        {
            if(customNames[1].length() > 0)
                append(t, (new StringBuilder(String.valueOf(customNames[1]))).append(" ").toString());
            append(t, customNames[0]);
        }
        if(genderBalance[0] != 0 || genderBalance[1] != 3 && genderBalance[2] != 3 && genderBalance[3] != 3)
            append(t, (new StringBuilder(" (")).append(genders[0]).append(")").toString());
        append(t, "\n");
        if(gaijinStatus[1].booleanValue())
        {
            append(t, customNames[2]);
            if(customNames[3].length() > 0)
                append(t, (new StringBuilder(" ")).append(customNames[3]).toString());
        } else
        {
            if(customNames[3].length() > 0)
                append(t, (new StringBuilder(String.valueOf(customNames[3]))).append(" ").toString());
            append(t, customNames[2]);
        }
        if(genderBalance[0] != 0 || genderBalance[1] != 3 && genderBalance[2] != 3 && genderBalance[3] != 3)
            append(t, (new StringBuilder(" (")).append(genders[1]).append(")").toString());
        append(t, "\n");
        if(gaijinStatus[2].booleanValue())
        {
            append(t, customNames[4]);
            if(customNames[5].length() > 0)
                append(t, (new StringBuilder(" ")).append(customNames[5]).toString());
        } else
        {
            if(customNames[5].length() > 0)
                append(t, (new StringBuilder(String.valueOf(customNames[5]))).append(" ").toString());
            append(t, customNames[4]);
        }
        if(genderBalance[0] != 0 || genderBalance[1] != 3 && genderBalance[2] != 3 && genderBalance[3] != 3)
            append(t, (new StringBuilder(" (")).append(genders[2]).append(")").toString());
        append(t, "\n\nPick which one's name you'd like to change.  When you're done, either continue to the personality questionaire or allow the game to generate a random set of personalities as normal.");
        for(int i = 0; i < 3; i++)
        {
            final int thisChosen = i;
            JButton Change = new JButton(customNames[2 * i]);
            Change.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    p.removeAll();
                    String hisHer = "her";
                    String himHer = "her";
                    String heShe = "she";
                    if(genders[thisChosen].equals("male"))
                    {
                        hisHer = "his";
                        himHer = "him";
                        heShe = "he";
                    }
                    String input = JOptionPane.showInputDialog((new StringBuilder("What is ")).append(hisHer).append(" family surname?  Leave blank to have ").append(himHer).append(" lack a surname.").toString());
                    if(input == null)
                        customNames[thisChosen * 2 + 1] = "";
                    else
                        customNames[thisChosen * 2 + 1] = input;
                    input = JOptionPane.showInputDialog((new StringBuilder("And what name was ")).append(heShe).append(" given at birth?  Leave blank to keep the current choice (").append(customNames[thisChosen * 2]).append(").").toString());
                    if(input != null && input.length() > 0)
                        customNames[thisChosen * 2] = input;
                    if(customNames[thisChosen * 2 + 1].length() > 0)
                    {
                        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nDoes ").append(heShe).append(" use Eastern name order (").append(customNames[thisChosen * 2 + 1]).append(" ").append(customNames[thisChosen * 2]).append(") or Western name order (").append(customNames[thisChosen * 2]).append(" ").append(customNames[thisChosen * 2 + 1]).append(")?").toString());
                        JButton Eastern = new JButton("Eastern");
                        Eastern.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                gaijinStatus[thisChosen] = Boolean.valueOf(false);
                                nameGen(t, p, f);
                            }

                            final _cls1 this$1;
                            private final int val$thisChosen;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;

                    
                    {
                        this$1 = _cls1.this;
                        thisChosen = i;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                        });
                        p.add(Eastern);
                        JButton Western = new JButton("Western");
                        Western.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                gaijinStatus[thisChosen] = Boolean.valueOf(true);
                                nameGen(t, p, f);
                            }

                            final _cls1 this$1;
                            private final int val$thisChosen;
                            private final JTextPane val$t;
                            private final JPanel val$p;
                            private final JFrame val$f;

                    
                    {
                        this$1 = _cls1.this;
                        thisChosen = i;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                        });
                        p.add(Western);
                        p.validate();
                        p.repaint();
                    } else
                    {
                        nameGen(t, p, f);
                    }
                }

                final WorldState this$0;
                private final JPanel val$p;
                private final int val$thisChosen;
                private final JTextPane val$t;
                private final JFrame val$f;


            
            {
                this$0 = WorldState.this;
                p = jpanel;
                thisChosen = i;
                t = jtextpane;
                f = jframe;
                super();
            }
            });
            p.add(Change);
        }

        JButton Continue = new JButton("Continue");
        Continue.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                personalityGen(t, p, f, 0);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(Continue);
        JButton Randomize = new JButton("Full Random");
        Randomize.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                for(int i = 0; i < 24; i++)
                    quizAnswers[i] = null;

                for(Boolean goodStats = Boolean.valueOf(false); !goodStats.booleanValue();)
                {
                    goodStats = Boolean.valueOf(true);
                    for(int i = 0; i < statSeed.length; i++)
                        statSeed[i] = (int)(Math.random() * 101D);

                    if(!legalSpread(statSeed, Boolean.valueOf(false)).booleanValue())
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

                newVulnerabilities(t, p, f);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(Randomize);
        final WorldState w = this;
        JButton Quit = new JButton("Quit");
        Quit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nReally quit?  All customization of this team will be lost.").toString());
                JButton ReallyQuit = new JButton("Quit");
                ReallyQuit.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
                        WorldState x = new WorldState();
                        x.copySettings(t, w);
                        x.copyToggles(w);
                        Project.IntroOne(t, p, f, x);
                    }

                    final _cls4 this$1;
                    private final JTextPane val$t;
                    private final WorldState val$w;
                    private final JPanel val$p;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls4.this;
                        t = jtextpane;
                        w = worldstate;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                });
                p.add(ReallyQuit);
                JButton Back = new JButton("Cancel");
                Back.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        nameGen(t, p, f);
                    }

                    final _cls4 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls4.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                });
                p.add(Back);
                p.validate();
                p.repaint();
            }

            final WorldState this$0;
            private final JPanel val$p;
            private final JTextPane val$t;
            private final WorldState val$w;
            private final JFrame val$f;


            
            {
                this$0 = WorldState.this;
                p = jpanel;
                t = jtextpane;
                w = worldstate1;
                f = jframe;
                super();
            }
        });
        p.add(Quit);
        p.validate();
        p.repaint();
    }

    public void personalityGen(final JTextPane t, final JPanel p, final JFrame f, final int progress)
    {
        p.removeAll();
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 8; j++)
                personalityWeights[progress][i][j] = 0;

        }

        int questionImpact[] = new int[5];
        if(progress == 0)
        {
            append(t, "This segment takes the form of a series of questions about which of the Chosen fits a given description best.  If the question doesn't have a clear answer, then select 'Pass'.\n\nThis system will try to produce a set of three contrasting personalities, so if two of them seem to share a trait, it will pick one to exhibit the trait more strongly than the other.  It isn't possible for it to generate two Chosen with the same personality.\n\nQuestion 1: Which of them has the most faith in humanity?");
            questionImpact = (new int[] {
                1, 1, 0, 0, 0, 0, 0, 0
            });
        } else
        if(progress == 1)
        {
            append(t, "Question 2: Who is the first to jump into harm's way when people are in danger?");
            questionImpact = (new int[] {
                1, 0, 1, 0, 0, 0, 0, 0
            });
        } else
        if(progress == 2)
        {
            append(t, "Question 3: Who puts the most effort into inspiring hope in others?");
            questionImpact = (new int[] {
                1, 0, 0, 1, 0, 0, 0, 0
            });
        } else
        if(progress == 3)
        {
            append(t, "Question 4: Who is the most headstrong?");
            questionImpact = (new int[] {
                0, 1, 1, 0, 0, 0, 0, 0
            });
        } else
        if(progress == 4)
        {
            append(t, "Question 5: Who tries the hardest to look cool?");
            questionImpact = (new int[] {
                0, 1, 0, 1, 0, 0, 0, 0
            });
        } else
        if(progress == 5)
        {
            append(t, "Question 6: Who is the most prideful?");
            questionImpact = (new int[] {
                0, 0, 1, 1, 0, 0, 0, 0
            });
        } else
        if(progress == 6)
        {
            append(t, "Question 7: Who thinks the most about what good and evil actually are?");
            questionImpact = (new int[] {
                1, 0, 0, 0, 0, 1, 0, 0
            });
        } else
        if(progress == 7)
        {
            append(t, "Question 8: Who cares the most about improving herself?");
            questionImpact = (new int[] {
                1, 0, 0, 0, 0, 0, 1, 0
            });
        } else
        if(progress == 8)
        {
            append(t, "Question 9: Who is the most willing to tolerate humiliation?");
            questionImpact = (new int[] {
                1, 0, 0, 0, 0, 0, 0, 1
            });
        } else
        if(progress == 9)
        {
            append(t, "Question 10: Who has the weakest willpower?");
            questionImpact = (new int[] {
                0, 1, 0, 0, 0, 0, 1, 0
            });
        } else
        if(progress == 10)
        {
            append(t, "Question 11: Who finds it most difficult to lie?");
            questionImpact = (new int[] {
                0, 1, 0, 0, 0, 0, 0, 1
            });
        } else
        if(progress == 11)
        {
            append(t, "Question 12: Who cares the least what people think of her?");
            questionImpact = (new int[] {
                0, 0, 1, 0, 0, 0, 0, 1
            });
        } else
        if(progress == 12)
        {
            append(t, "Question 13: Who is the most self-centered?");
            questionImpact = (new int[] {
                0, 1, 0, 0, 1, 0, 0, 0
            });
        } else
        if(progress == 13)
        {
            append(t, "Question 14: Who is the most violent?");
            questionImpact = (new int[] {
                0, 0, 1, 0, 1, 0, 0, 0
            });
        } else
        if(progress == 14)
        {
            append(t, "Question 15: Who is least likely to admit her own faults?");
            questionImpact = (new int[] {
                0, 0, 0, 1, 1, 0, 0, 0
            });
        } else
        if(progress == 15)
        {
            append(t, "Question 16: Who is the best at remaining calm and rational despite distractions?");
            questionImpact = (new int[] {
                0, 0, 1, 0, 0, 1, 0, 0
            });
        } else
        if(progress == 16)
        {
            append(t, "Question 17: Who is the best at reading a social situation?");
            questionImpact = (new int[] {
                0, 0, 0, 1, 0, 1, 0, 0
            });
        } else
        if(progress == 17)
        {
            append(t, "Question 18: Who is the most embarrassed by sexual matters?");
            questionImpact = (new int[] {
                0, 0, 0, 1, 0, 0, 1, 0
            });
        } else
        if(progress == 18)
        {
            append(t, "Question 19: Who is bothered the least when she sees other people suffer?");
            questionImpact = (new int[] {
                0, 0, 0, 0, 1, 1, 0, 0
            });
        } else
        if(progress == 19)
        {
            append(t, "Question 20: Who spends the most time worrying about her own safety?");
            questionImpact = (new int[] {
                0, 0, 0, 0, 1, 0, 1, 0
            });
        } else
        if(progress == 20)
        {
            append(t, "Question 21: Who says the most hurtful things?");
            questionImpact = (new int[] {
                0, 0, 0, 0, 1, 0, 0, 1
            });
        } else
        if(progress == 21)
        {
            append(t, "Question 22: Who is the most pessimistic about her chances of winning?");
            questionImpact = (new int[] {
                0, 0, 0, 0, 0, 1, 1, 0
            });
        } else
        if(progress == 22)
        {
            append(t, "Question 23: Who finds it most difficult to relate to regular people?");
            questionImpact = (new int[] {
                0, 0, 0, 0, 0, 1, 0, 1
            });
        } else
        if(progress == 23)
        {
            append(t, "Question 24: Who is most willing to ask for help?");
            questionImpact = (new int[] {
                0, 0, 0, 0, 0, 0, 1, 1
            });
        }
        if(quizAnswers[progress] != null)
            append(t, (new StringBuilder("  (Previous answer: ")).append(quizAnswers[progress]).append(".)").toString());
        for(int i = 0; i < 3; i++)
        {
            final int finalImpact[] = questionImpact;
            final int thisChosen = i;
            JButton Pick = new JButton(customNames[i * 2]);
            Pick.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    personalityWeights[progress][thisChosen] = finalImpact;
                    quizAnswers[progress] = customNames[thisChosen * 2];
                    if(progress < 23)
                        personalityGen(t, p, f, progress + 1);
                    else
                        determineStats(t, p, f);
                }

                final WorldState this$0;
                private final int val$progress;
                private final int val$thisChosen;
                private final int val$finalImpact[];
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;

            
            {
                this$0 = WorldState.this;
                progress = i;
                thisChosen = j;
                finalImpact = ai;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
            });
            p.add(Pick);
        }

        JButton Pass = new JButton("Pass");
        Pass.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                quizAnswers[progress] = "pass";
                if(progress < 23)
                    personalityGen(t, p, f, progress + 1);
                else
                    determineStats(t, p, f);
            }

            final WorldState this$0;
            private final int val$progress;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                this$0 = WorldState.this;
                progress = i;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(Pass);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                if(progress > 0)
                    personalityGen(t, p, f, progress - 1);
                else
                    nameGen(t, p, f);
            }

            final WorldState this$0;
            private final int val$progress;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                this$0 = WorldState.this;
                progress = i;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(Back);
        JButton Quit = new JButton("Quit");
        final WorldState w = this;
        Quit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nReally quit?  All customization of this team will be lost.").toString());
                JButton ReallyQuit = new JButton("Quit");
                ReallyQuit.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
                        WorldState x = new WorldState();
                        x.copySettings(t, w);
                        x.copyToggles(w);
                        Project.IntroOne(t, p, f, x);
                    }

                    final _cls8 this$1;
                    private final JTextPane val$t;
                    private final WorldState val$w;
                    private final JPanel val$p;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls8.this;
                        t = jtextpane;
                        w = worldstate;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                });
                p.add(ReallyQuit);
                JButton Back = new JButton("Cancel");
                Back.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        personalityGen(t, p, f, progress);
                    }

                    final _cls8 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final int val$progress;

                    
                    {
                        this$1 = _cls8.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        progress = i;
                        super();
                    }
                });
                p.add(Back);
                p.validate();
                p.repaint();
            }

            final WorldState this$0;
            private final JPanel val$p;
            private final JTextPane val$t;
            private final WorldState val$w;
            private final JFrame val$f;
            private final int val$progress;


            
            {
                this$0 = WorldState.this;
                p = jpanel;
                t = jtextpane;
                w = worldstate1;
                f = jframe;
                progress = i;
                super();
            }
        });
        p.add(Quit);
        p.validate();
        p.repaint();
    }

    public void determineStats(JTextPane t, JPanel p, JFrame f)
    {
        int certainties[][] = new int[3][4];
        int ranges[][] = new int[3][4];
        int flexibilities[][] = new int[3][4];
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                for(int k = 0; k < 24; k++)
                {
                    certainties[i][j] += personalityWeights[k][i][j] + personalityWeights[k][i][j + 4];
                    ranges[i][j] += personalityWeights[k][i][j] - personalityWeights[k][i][j + 4];
                }

            }

        }

        int mostCertain[] = new int[4];
        for(int j = 0; j < 4; j++)
        {
            int highestCertainty = 0;
            for(int i = 0; i < 3; i++)
                if(certainties[i][j] > highestCertainty)
                {
                    mostCertain[j] = i;
                    highestCertainty = certainties[i][j];
                }

            int absoluteRange = ranges[mostCertain[j]][j];
            if(absoluteRange < 0)
                absoluteRange = -absoluteRange;
            int margin = 0;
            if(absoluteRange != certainties[mostCertain[j]][j])
            {
                int multiplier = (100 * (certainties[mostCertain[j]][j] + absoluteRange)) / (certainties[mostCertain[j]][j] - absoluteRange);
                if((absoluteRange * multiplier) / 20 > 50)
                {
                    int sign = 1;
                    if(ranges[mostCertain[j]][j] < 0)
                        sign = -1;
                    if(certainties[mostCertain[j]][j] == 8)
                        margin = 40;
                    if(certainties[mostCertain[j]][j] == 7)
                        margin = 49;
                    else
                    if(certainties[mostCertain[j]][j] == 6)
                        margin = 43;
                    else
                    if(certainties[mostCertain[j]][j] == 5)
                        margin = 45;
                    margin *= sign;
                } else
                {
                    margin = (ranges[mostCertain[j]][j] * multiplier) / 20;
                }
            } else
            {
                int multiplier = 1;
                if(ranges[mostCertain[j]][j] < 0)
                    multiplier = -1;
                if(certainties[mostCertain[j]][j] == 6)
                    margin = 50;
                else
                if(certainties[mostCertain[j]][j] == 5)
                    margin = 48;
                else
                if(certainties[mostCertain[j]][j] == 4)
                    margin = 47;
                else
                if(certainties[mostCertain[j]][j] == 3)
                    margin = 35;
                else
                if(certainties[mostCertain[j]][j] == 2)
                    margin = 28;
                else
                if(certainties[mostCertain[j]][j] == 1)
                    margin = 13;
                else
                    margin = 0;
                margin *= multiplier;
            }
            int baseValue = 50 + margin;
            statSeed[mostCertain[j] * 4 + j] = baseValue;
            int otherValues[] = new int[2];
            if(mostCertain[j] == 0)
                otherValues = (new int[] {
                    1, 2
                });
            else
            if(mostCertain[j] == 1)
                otherValues = (new int[] {
                    0, 2
                });
            else
                otherValues = (new int[] {
                    0, 1
                });
            if(baseValue > 66)
            {
                int middle;
                int low;
                if(ranges[otherValues[0]][j] >= ranges[otherValues[1]][j])
                {
                    middle = otherValues[0];
                    low = otherValues[1];
                } else
                {
                    middle = otherValues[1];
                    low = otherValues[0];
                }
                statSeed[middle * 4 + j] = 50 + ranges[middle][j] * 4;
                if(statSeed[middle * 4 + j] > 66)
                    statSeed[middle * 4 + j] = 66;
                else
                if(statSeed[middle * 4 + j] < 34)
                    statSeed[middle * 4 + j] = 34;
                statSeed[low * 4 + j] = 30 + ranges[low][j] * 5;
                if(statSeed[low * 4 + j] > 33)
                    statSeed[low * 4 + j] = 33;
            } else
            if(baseValue > 33)
            {
                int high;
                int low;
                if(ranges[otherValues[0]][j] >= ranges[otherValues[1]][j])
                {
                    high = otherValues[0];
                    low = otherValues[1];
                } else
                {
                    high = otherValues[1];
                    low = otherValues[0];
                }
                statSeed[high * 4 + j] = 70 + ranges[high][j] * 5;
                if(statSeed[high * 4 + j] < 67)
                    statSeed[high * 4 + j] = 67;
                statSeed[low * 4 + j] = 30 + ranges[low][j] * 5;
                if(statSeed[low * 4 + j] > 33)
                    statSeed[low * 4 + j] = 33;
            } else
            {
                int high;
                int middle;
                if(ranges[otherValues[0]][j] >= ranges[otherValues[1]][j])
                {
                    high = otherValues[0];
                    middle = otherValues[1];
                } else
                {
                    high = otherValues[1];
                    middle = otherValues[0];
                }
                statSeed[high * 4 + j] = 70 + ranges[high][j] * 5;
                if(statSeed[high * 4 + j] < 67)
                    statSeed[high * 4 + j] = 67;
                statSeed[middle * 4 + j] = 50 + ranges[middle][j] * 4;
                if(statSeed[middle * 4 + j] > 66)
                    statSeed[middle * 4 + j] = 66;
                else
                if(statSeed[middle * 4 + j] < 34)
                    statSeed[middle * 4 + j] = 34;
            }
            for(int i = 0; i < 3; i++)
                if(i != mostCertain[j])
                    flexibilities[i][j] = highestCertainty - certainties[i][j];

        }

        int attempts;
        for(attempts = 0; !legalSpread(statSeed, Boolean.valueOf(true)).booleanValue() && attempts < 1000; attempts++)
        {
            int counts[][] = new int[3][3];
            for(int i = 0; i < 3; i++)
            {
                for(int j = 0; j < 4; j++)
                    if(statSeed[i * 4 + j] > 66)
                        counts[i][0]++;
                    else
                    if(statSeed[i * 4 + j] > 33)
                        counts[i][1]++;
                    else
                        counts[i][2]++;

                for(int j = 0; j < 4; j++)
                    if(flexibilities[i][j] == 0)
                        flexibilities[i][j]++;

            }

            for(int m = 0; m < 3; m++)
            {
                int n = m + 1;
                if(n == 3)
                    n = 0;
                int q = m - 1;
                if(q < 0)
                    q = 2;
                Boolean tradeHigherForLower = Boolean.valueOf(false);
                int tradeUpperRange = 0;
                int tradeLowerRange = 1;
                int tradeRangeIndex = -1;
                if(counts[m][0] > 2)
                {
                    tradeHigherForLower = Boolean.valueOf(true);
                    tradeUpperRange = 66;
                    tradeLowerRange = 34;
                    tradeRangeIndex = 1;
                } else
                if(counts[m][2] > 2)
                {
                    tradeHigherForLower = Boolean.valueOf(false);
                    tradeUpperRange = 66;
                    tradeLowerRange = 34;
                    tradeRangeIndex = 1;
                } else
                if(counts[m][1] > 2)
                {
                    if(counts[m][0] == 1)
                    {
                        tradeHigherForLower = Boolean.valueOf(true);
                        tradeUpperRange = 33;
                        tradeLowerRange = 0;
                        tradeRangeIndex = 2;
                    } else
                    {
                        tradeHigherForLower = Boolean.valueOf(false);
                        tradeUpperRange = 100;
                        tradeLowerRange = 67;
                        tradeRangeIndex = 0;
                    }
                } else
                if(counts[m][0] == 0)
                {
                    tradeHigherForLower = Boolean.valueOf(false);
                    tradeUpperRange = 100;
                    tradeLowerRange = 67;
                    tradeRangeIndex = 0;
                } else
                if(counts[m][2] == 0)
                {
                    tradeHigherForLower = Boolean.valueOf(true);
                    tradeUpperRange = 33;
                    tradeLowerRange = 0;
                    tradeRangeIndex = 2;
                } else
                if(counts[m][1] == 0)
                {
                    int highOpportunities = 0;
                    int lowOpportunities = 0;
                    for(int j = 0; j < 4; j++)
                        if(statSeed[m * 4 + j] > 66)
                        {
                            if(counts[n][1] > 1 && statSeed[n * 4 + j] > 33 && statSeed[n * 4 + j] < 67)
                                highOpportunities += flexibilities[m][j] * flexibilities[n][j];
                            if(counts[q][1] > 1 && statSeed[q * 4 + j] > 33 && statSeed[q * 4 + j] < 67)
                                highOpportunities += flexibilities[m][j] * flexibilities[q][j];
                        } else
                        {
                            if(counts[n][1] > 1 && statSeed[n * 4 + j] > 33 && statSeed[n * 4 + j] < 67)
                                lowOpportunities += flexibilities[m][j] * flexibilities[n][j];
                            if(counts[q][1] > 1 && statSeed[q * 4 + j] > 33 && statSeed[q * 4 + j] < 67)
                                lowOpportunities += flexibilities[m][j] * flexibilities[q][j];
                        }

                    if(highOpportunities >= lowOpportunities)
                        tradeHigherForLower = Boolean.valueOf(true);
                    else
                        tradeHigherForLower = Boolean.valueOf(false);
                    tradeUpperRange = 66;
                    tradeLowerRange = 34;
                    tradeRangeIndex = 1;
                }
                int traded = -1;
                int subject = 0;
                if(tradeRangeIndex >= 0)
                {
                    if(counts[n][tradeRangeIndex] > counts[q][tradeRangeIndex])
                        subject = n;
                    else
                    if(counts[n][tradeRangeIndex] < counts[q][tradeRangeIndex])
                    {
                        subject = q;
                    } else
                    {
                        int highestSum = 0;
                        for(int j = 0; j < 4; j++)
                        {
                            Boolean legalTrade = Boolean.valueOf(false);
                            if(tradeHigherForLower.booleanValue())
                            {
                                if(tradeRangeIndex == 1)
                                {
                                    if(statSeed[m * 4 + j] > 66)
                                        legalTrade = Boolean.valueOf(true);
                                } else
                                if(statSeed[m * 4 + j] < 67)
                                    legalTrade = Boolean.valueOf(true);
                            } else
                            if(tradeRangeIndex == 0)
                            {
                                if(statSeed[m * 4 + j] > 33)
                                    legalTrade = Boolean.valueOf(true);
                            } else
                            if(statSeed[m * 4 + j] < 34)
                                legalTrade = Boolean.valueOf(true);
                            if(flexibilities[m][j] + flexibilities[n][j] > highestSum && legalTrade.booleanValue() && statSeed[n * 4 + j] >= tradeLowerRange && statSeed[n * 4 + j] <= tradeUpperRange)
                            {
                                highestSum = flexibilities[m][j] + flexibilities[n][j];
                                traded = j;
                                subject = n;
                            }
                            if(flexibilities[m][j] + flexibilities[q][j] > highestSum && legalTrade.booleanValue() && statSeed[q * 4 + j] >= tradeLowerRange && statSeed[q * 4 + j] <= tradeUpperRange)
                            {
                                highestSum = flexibilities[m][j] + flexibilities[q][j];
                                traded = j;
                                subject = q;
                            }
                        }

                    }
                    if(traded < 0)
                    {
                        int highestSum = 0;
                        for(int j = 0; j < 4; j++)
                        {
                            Boolean legalTrade = Boolean.valueOf(false);
                            if(statSeed[subject * 4 + j] >= tradeLowerRange && statSeed[subject * 4 + j] <= tradeUpperRange)
                                if(tradeHigherForLower.booleanValue())
                                {
                                    if(tradeRangeIndex == 1)
                                    {
                                        if(statSeed[m * 4 + j] > 66)
                                            legalTrade = Boolean.valueOf(true);
                                    } else
                                    if(statSeed[m * 4 + j] < 67)
                                        legalTrade = Boolean.valueOf(true);
                                } else
                                if(tradeRangeIndex == 0)
                                {
                                    if(statSeed[m * 4 + j] > 33)
                                        legalTrade = Boolean.valueOf(true);
                                } else
                                if(statSeed[m * 4 + j] < 34)
                                    legalTrade = Boolean.valueOf(true);
                            if(flexibilities[m][j] + flexibilities[subject][j] > highestSum && legalTrade.booleanValue())
                            {
                                highestSum = flexibilities[m][j] + flexibilities[subject][j];
                                traded = j;
                            }
                        }

                    }
                    if(traded >= 0)
                        if(tradeHigherForLower.booleanValue())
                        {
                            statSeed[m * 4 + traded] = (tradeUpperRange + 2) - flexibilities[m][traded] * 2;
                            statSeed[subject * 4 + traded] = tradeUpperRange + flexibilities[subject][traded] * 2;
                        } else
                        {
                            statSeed[m * 4 + traded] = (tradeLowerRange - 2) + flexibilities[m][traded] * 2;
                            statSeed[subject * 4 + traded] = tradeLowerRange - flexibilities[subject][traded] * 2;
                        }
                    m = 3;
                }
            }

        }

        if(attempts >= 1000)
        {
            append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
            append(t, "Generation failed.\n\n");
            Project.IntroOne(t, p, f, new WorldState());
        } else
        {
            newVulnerabilities(t, p, f);
        }
    }

    public void newVulnerabilities(JTextPane t, JPanel p, JFrame f)
    {
        p.removeAll();
        invertVVirg = (new Boolean[] {
            Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false)
        });
        invertCVirg = (new Boolean[] {
            Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false)
        });
        invertAVirg = (new Boolean[] {
            Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false)
        });
        invertModest = (new Boolean[] {
            Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false)
        });
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nPersonalities generated.  ").toString());
        vulnerabilityMenu(t, p, f, Boolean.valueOf(false));
    }

    public void vulnerabilityMenu(final JTextPane t, final JPanel p, final JFrame f, final Boolean shown)
    {
        p.removeAll();
        Boolean allPurities[][] = new Boolean[3][4];
        for(int i = 0; i < 3; i++)
        {
            int stats[] = {
                statSeed[i * 4], statSeed[i * 4 + 1], statSeed[i * 4 + 2], statSeed[i * 4 + 3]
            };
            allPurities[i][0] = Boolean.valueOf(invertVVirg[i] != determineVVirg(stats[0], stats[1], stats[2], stats[3]));
            allPurities[i][1] = Boolean.valueOf(invertCVirg[i] != determineCVirg(stats[0], stats[1], stats[2], stats[3]));
            allPurities[i][2] = Boolean.valueOf(invertAVirg[i] != determineAVirg(stats[0], stats[1], stats[2], stats[3]));
            allPurities[i][3] = Boolean.valueOf(invertModest[i] != determineModest(stats[0], stats[1], stats[2], stats[3]));
        }

        append(t, "If you like, you can review the Vulnerabilities of the team you've created here, and you may determine whether they start broken or not.  The Chosen take more damage of types associated with broken Vulnerabilities, but breaking Vulnerabilities during play grants bonus Evil Energy and can cause tension within the team.");
        if(shown.booleanValue())
        {
            append(t, "  Click 'Continue' once you're done.");
            for(int i = 0; i < 3; i++)
            {
                append(t, (new StringBuilder("\n\n")).append(customNames[i * 2]).toString());
                for(int j = 0; j < 4; j++)
                {
                    String type = "Morality";
                    if(j == 1)
                        type = "Innocence";
                    else
                    if(j == 2)
                        type = "Confidence";
                    else
                    if(j == 3)
                        type = "Dignity";
                    if(statSeed[i * 4 + j] > 66)
                        blueAppend(t, (new StringBuilder("\n")).append(type).append(": Core").toString());
                    else
                    if(statSeed[i * 4 + j] > 33)
                        greenAppend(t, (new StringBuilder("\n")).append(type).append(": Significant").toString());
                    else
                        redAppend(t, (new StringBuilder("\n")).append(type).append(": Minor").toString());
                    if(!allPurities[i][j].booleanValue())
                        redAppend(t, " (BROKEN)");
                }

            }

        } else
        {
            append(t, "  The Vulnerabilities are presently hidden so that players who wish to avoid being spoiled can face the team blind.  Click 'Show All' to reveal them, or click on one of the names to view and break the Vulnerabilities of that Chosen in particular.");
            append(t, "  Click 'Continue' once you're done.");
        }
        for(int i = 0; i < 3; i++)
        {
            final int thisChosen = i;
            JButton ThisOne = new JButton(customNames[thisChosen * 2]);
            ThisOne.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    reviewVulnerabilities(t, p, f, shown, thisChosen, 0);
                }

                final WorldState this$0;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final Boolean val$shown;
                private final int val$thisChosen;

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                shown = boolean1;
                thisChosen = i;
                super();
            }
            });
            p.add(ThisOne);
        }

        JButton ShowAll = new JButton("Show All");
        if(shown.booleanValue())
            ShowAll.setText("Hide All");
        ShowAll.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
                vulnerabilityMenu(t, p, f, Boolean.valueOf(!shown.booleanValue()));
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final Boolean val$shown;

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                shown = boolean1;
                super();
            }
        });
        p.add(ShowAll);
        JButton Continue = new JButton("Continue");
        Continue.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                newCosmetics(t, p, f);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(Continue);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                final Boolean toQuiz = Boolean.valueOf(quizAnswers[23] != null);
                append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nReally go back to ").toString());
                if(toQuiz.booleanValue())
                    append(t, "the personality quiz");
                else
                    append(t, "name settings");
                append(t, "?  The Vulnerability adjustments set on this screen will be reset.");
                JButton ReallyQuit = new JButton("Back");
                ReallyQuit.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
                        if(toQuiz.booleanValue())
                            personalityGen(t, p, f, 23);
                        else
                            nameGen(t, p, f);
                    }

                    final _cls12 this$1;
                    private final JTextPane val$t;
                    private final Boolean val$toQuiz;
                    private final JPanel val$p;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls12.this;
                        t = jtextpane;
                        toQuiz = boolean1;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                });
                p.add(ReallyQuit);
                JButton Back = new JButton("Cancel");
                Back.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
                        vulnerabilityMenu(t, p, f, shown);
                    }

                    final _cls12 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final Boolean val$shown;

                    
                    {
                        this$1 = _cls12.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        shown = boolean1;
                        super();
                    }
                });
                p.add(Back);
                p.validate();
                p.repaint();
            }

            final WorldState this$0;
            private final JPanel val$p;
            private final JTextPane val$t;
            private final JFrame val$f;
            private final Boolean val$shown;


            
            {
                this$0 = WorldState.this;
                p = jpanel;
                t = jtextpane;
                f = jframe;
                shown = boolean1;
                super();
            }
        });
        p.add(Back);
        final WorldState w = this;
        JButton Quit = new JButton("Quit");
        Quit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nReally quit?  All customization of this team will be lost.").toString());
                JButton ReallyQuit = new JButton("Quit");
                ReallyQuit.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
                        WorldState x = new WorldState();
                        x.copySettings(t, w);
                        x.copyToggles(w);
                        Project.IntroOne(t, p, f, x);
                    }

                    final _cls13 this$1;
                    private final JTextPane val$t;
                    private final WorldState val$w;
                    private final JPanel val$p;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls13.this;
                        t = jtextpane;
                        w = worldstate;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                });
                p.add(ReallyQuit);
                JButton Back = new JButton("Cancel");
                Back.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
                        vulnerabilityMenu(t, p, f, shown);
                    }

                    final _cls13 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;
                    private final Boolean val$shown;

                    
                    {
                        this$1 = _cls13.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        shown = boolean1;
                        super();
                    }
                });
                p.add(Back);
                p.validate();
                p.repaint();
            }

            final WorldState this$0;
            private final JPanel val$p;
            private final JTextPane val$t;
            private final WorldState val$w;
            private final JFrame val$f;
            private final Boolean val$shown;


            
            {
                this$0 = WorldState.this;
                p = jpanel;
                t = jtextpane;
                w = worldstate1;
                f = jframe;
                shown = boolean1;
                super();
            }
        });
        p.add(Quit);
        p.validate();
        p.repaint();
    }

    public void reviewVulnerabilities(final JTextPane t, final JPanel p, final JFrame f, final Boolean shown, final int id, final int progress)
    {
        String hisHer = "her";
        String himHer = "her";
        String heShe = "she";
        if(genders[id].equals("male"))
        {
            hisHer = "his";
            himHer = "him";
            heShe = "he";
        }
        p.removeAll();
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
        Boolean allPurities[][] = new Boolean[3][4];
        for(int i = 0; i < 3; i++)
        {
            int stats[] = {
                statSeed[i * 4], statSeed[i * 4 + 1], statSeed[i * 4 + 2], statSeed[i * 4 + 3]
            };
            allPurities[i][0] = Boolean.valueOf(invertVVirg[i] != determineVVirg(stats[0], stats[1], stats[2], stats[3]));
            allPurities[i][1] = Boolean.valueOf(invertCVirg[i] != determineCVirg(stats[0], stats[1], stats[2], stats[3]));
            allPurities[i][2] = Boolean.valueOf(invertAVirg[i] != determineAVirg(stats[0], stats[1], stats[2], stats[3]));
            allPurities[i][3] = Boolean.valueOf(invertModest[i] != determineModest(stats[0], stats[1], stats[2], stats[3]));
        }

        if(progress == 0)
        {
            if(statSeed[id * 4] > 66)
            {
                append(t, (new StringBuilder(String.valueOf(customNames[id * 2]))).append("'s sense of Morality is a ").toString());
                blueAppend(t, "Core");
                append(t, (new StringBuilder(" part of ")).append(hisHer).append(" identity").toString());
            } else
            if(statSeed[id * 4] > 33)
            {
                append(t, (new StringBuilder(String.valueOf(customNames[id * 2]))).append("'s Morality is a ").toString());
                greenAppend(t, "Significant");
                append(t, (new StringBuilder(" component of ")).append(hisHer).append(" personality").toString());
            }
            if(statSeed[id * 4] > 33)
            {
                if(genders[id].equals("male"))
                    append(t, (new StringBuilder(", so ")).append(heShe).append(" wouldn't normally engage in 'immoral' activities like violence and sex with other men.  ").toString());
                else
                    append(t, (new StringBuilder(", so ")).append(heShe).append(" wouldn't normally engage in 'immoral' activities like violence and promiscuity.  ").toString());
                if(allPurities[id][0].booleanValue())
                    append(t, (new StringBuilder("If this Vulnerability is broken, it means ")).append(heShe).append(" has already been raped by the time the game starts.").toString());
                else
                    append(t, (new StringBuilder("However, ")).append(heShe).append(" is set to have already been raped before the game starts.").toString());
            } else
            {
                append(t, "Morality is only a ");
                redAppend(t, "Minor");
                append(t, (new StringBuilder(" concern for ")).append(customNames[id * 2]).append(", ").toString());
                if(allPurities[id][0].booleanValue())
                {
                    if(genders[id].equals("male"))
                        append(t, (new StringBuilder("but ")).append(heShe).append(" hasn't gotten curious enough to try having sex with other men yet.  Break this Vulnerability to change that.").toString());
                    else
                        append(t, (new StringBuilder("but ")).append(heShe).append(" hasn't gotten around to having sex yet.  Break this Vulnerability to change that.").toString());
                } else
                if(genders[id].equals("male"))
                    append(t, (new StringBuilder("so ")).append(heShe).append(" has seen no reason to avoid having sex with other men.  Restore this Vulnerability to have ").append(himHer).append(" start as an anal virgin instead.").toString());
                else
                    append(t, (new StringBuilder("so ")).append(heShe).append(" has seen no reason to avoid having sex.  Restore this Vulnerability to have ").append(himHer).append(" start as a virgin instead.").toString());
            }
        } else
        if(progress == 1)
        {
            if(statSeed[id * 4 + 1] > 66)
            {
                append(t, (new StringBuilder(String.valueOf(customNames[id * 2]))).append("'s Innocence is a ").toString());
                blueAppend(t, "Core");
                append(t, (new StringBuilder(" part of ")).append(hisHer).append(" identity").toString());
            } else
            if(statSeed[id * 4 + 1] > 33)
            {
                append(t, (new StringBuilder(String.valueOf(customNames[id * 2]))).append(" has retained ").toString());
                greenAppend(t, "Significant");
                append(t, " Innocence regarding sexual matters");
            }
            if(statSeed[id * 4 + 1] > 33)
            {
                append(t, (new StringBuilder(", so ")).append(heShe).append(" wouldn't normally have any idea how good it can feel to be forced to cum during battle.  ").toString());
                if(allPurities[id][1].booleanValue())
                    append(t, (new StringBuilder("If this Vulnerability is broken, it means ")).append(heShe).append(" has already become addicted to this feeling.").toString());
                else
                    append(t, (new StringBuilder("However, ")).append(heShe).append(" is set to have already become addicted to this feeling when the game starts.").toString());
            } else
            {
                append(t, (new StringBuilder(String.valueOf(customNames[id * 2]))).append(" has retained only a ").toString());
                redAppend(t, "Minor");
                append(t, " amount of Innocence");
                if(allPurities[id][1].booleanValue())
                    append(t, (new StringBuilder(", but ")).append(heShe).append("'s still sane enough to hold back from cumming during battle.  Break this Vulnerability to change that.").toString());
                else
                    append(t, (new StringBuilder(", so ")).append(heShe).append(" happily allows ").append(himHer).append("self to cum during battle.  Restore this Vulnerability to have ").append(himHer).append(" start out with some restraint.").toString());
            }
        } else
        if(progress == 2)
        {
            if(statSeed[id * 4 + 2] > 66)
            {
                append(t, (new StringBuilder(String.valueOf(customNames[id * 2]))).append("'s Confidence is a ").toString());
                blueAppend(t, "Core");
                append(t, (new StringBuilder(" part of ")).append(hisHer).append(" identity").toString());
            } else
            if(statSeed[id * 4 + 2] > 33)
            {
                append(t, (new StringBuilder(String.valueOf(customNames[id * 2]))).append(" has a ").toString());
                greenAppend(t, "Significant");
                append(t, " amount of Confidence");
            }
            if(statSeed[id * 4 + 2] > 33)
            {
                append(t, (new StringBuilder(" because of ")).append(hisHer).append(" past victories against the Demons.  ").toString());
                if(allPurities[id][2].booleanValue())
                    append(t, (new StringBuilder("If this Vulnerability is broken, it means ")).append(heShe).append(" has suffered a crushing defeat and been tortured before.").toString());
                else
                    append(t, (new StringBuilder("However, ")).append(heShe).append(" is set to have already had ").append(hisHer).append(" self-image shaken by being defeated and tortured recently.").toString());
            } else
            {
                append(t, (new StringBuilder(String.valueOf(customNames[id * 2]))).append(" has only a ").toString());
                redAppend(t, "Minor");
                append(t, " amount of Confidence left");
                if(allPurities[id][2].booleanValue())
                    append(t, (new StringBuilder(", but this is due purely to ")).append(hisHer).append(" weak personality.  Break this Vulnerability to have ").append(hisHer).append(" self-esteem damaged by a recent capture and torture.").toString());
                else
                    append(t, (new StringBuilder(", largely because of ")).append(hisHer).append(" recent defeat and torture at the hands of the Demons.  Restore this Vulnerability to erase this event and let ").append(himHer).append(" start out with at least a little bit of hope.").toString());
            }
        } else
        if(progress == 3)
        {
            if(statSeed[id * 4 + 3] > 66)
            {
                append(t, (new StringBuilder(String.valueOf(customNames[id * 2]))).append("'s need for Dignity is a ").toString());
                blueAppend(t, "Core");
                append(t, (new StringBuilder(" part of ")).append(hisHer).append(" identity").toString());
            } else
            if(statSeed[id * 4 + 3] > 33)
            {
                append(t, (new StringBuilder(String.valueOf(customNames[id * 2]))).append(" maintains a ").toString());
                greenAppend(t, "Significant");
                append(t, " amount of Dignity");
            }
            if(statSeed[id * 4 + 3] > 33)
            {
                append(t, ", wanting to be viewed as a mighty, unassailable warrior.  ");
                if(allPurities[id][3].booleanValue())
                    append(t, (new StringBuilder("If this Vulnerability is broken, it means ")).append(heShe).append(" has been stripped and had ").append(hisHer).append(" humiliation broadcast to the world.").toString());
                else
                    append(t, (new StringBuilder("However, ")).append(heShe).append(" is set to have already had ").append(hisHer).append(" public image damaged by being stripped during battle and having the footage broadcast to the world.").toString());
            } else
            {
                append(t, (new StringBuilder(String.valueOf(customNames[id * 2]))).append(" has only a ").toString());
                redAppend(t, "Minor");
                append(t, (new StringBuilder(" interest in retaining ")).append(hisHer).append(" dignity").toString());
                if(allPurities[id][3].booleanValue())
                    append(t, (new StringBuilder(", but ")).append(heShe).append(" has managed to avoid any public humiliation so far, mostly through pure luck.  Break this Vulnerability to have footage of ").append(hisHer).append(" defeat and stripping be broadcast to the world.").toString());
                else
                    append(t, (new StringBuilder(", and as a result, ")).append(heShe).append(" hasn't managed to stop footage of ").append(hisHer).append(" defeat and stripping from being spread across the world.  Restore this Vulnerability to make it so that ").append(heShe).append(" hasn't yet been captured in such a shameful state.").toString());
            }
        }
        JButton Toggle = new JButton("Break");
        if(!allPurities[id][progress].booleanValue())
            Toggle.setText("Restore");
        Toggle.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                if(progress == 0)
                    invertVVirg[id] = Boolean.valueOf(!invertVVirg[id].booleanValue());
                else
                if(progress == 1)
                    invertCVirg[id] = Boolean.valueOf(!invertCVirg[id].booleanValue());
                else
                if(progress == 2)
                    invertAVirg[id] = Boolean.valueOf(!invertAVirg[id].booleanValue());
                else
                if(progress == 3)
                    invertModest[id] = Boolean.valueOf(!invertModest[id].booleanValue());
                if(progress < 3)
                {
                    reviewVulnerabilities(t, p, f, shown, id, progress + 1);
                } else
                {
                    append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
                    vulnerabilityMenu(t, p, f, shown);
                }
            }

            final WorldState this$0;
            private final int val$progress;
            private final int val$id;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final Boolean val$shown;

            
            {
                this$0 = WorldState.this;
                progress = i;
                id = j;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                shown = boolean1;
                super();
            }
        });
        p.add(Toggle);
        JButton Continue = new JButton("Continue");
        Continue.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                if(progress < 3)
                {
                    reviewVulnerabilities(t, p, f, shown, id, progress + 1);
                } else
                {
                    append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
                    vulnerabilityMenu(t, p, f, shown);
                }
            }

            final WorldState this$0;
            private final int val$progress;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final Boolean val$shown;
            private final int val$id;

            
            {
                this$0 = WorldState.this;
                progress = i;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                shown = boolean1;
                id = j;
                super();
            }
        });
        p.add(Continue);
        p.validate();
        p.repaint();
    }

    public void newCosmetics(JTextPane t, JPanel p, JFrame f)
    {
        customTop = (new String[] {
            "", "", ""
        });
        customTopAccess = (new String[] {
            "", "", ""
        });
        customBottom = (new String[] {
            "", "", ""
        });
        customBottomAccess = (new String[] {
            "", "", ""
        });
        customUnder = (new String[] {
            "", "", ""
        });
        customColor = (new String[] {
            "", "", ""
        });
        customAccessory = (new String[] {
            "", "", ""
        });
        customWeapons = (new String[] {
            "", "", ""
        });
        customWeaponTypes = (new String[] {
            "", "", ""
        });
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
        cosmeticsGen(t, p, f);
    }

    public String[] pickCosmetics(int morality, int innocence, int confidence, int dignity)
    {
        String cosmetics[] = new String[10];
        String topCover = "";
        String topAccess = "";
        String bottomCover = "";
        String bottomAccess = "";
        String underType = "";
        String color = "";
        String accessory = "";
        String weapon = "";
        String customWeaponType = "";
        String feetType = "";
        if(innocence > 75)
        {
            if(morality > 25)
            {
                topCover = "bodice";
                topAccess = "down";
                bottomCover = "skirt";
                bottomAccess = "up";
            } else
            {
                topCover = "cloak";
                topAccess = "up";
                bottomCover = "cloak";
                bottomAccess = "up";
            }
            if(dignity > 25)
                underType = "panties";
            else
                underType = "none";
        } else
        if(innocence > 50)
        {
            if(morality > 75)
            {
                topCover = "robe";
                topAccess = "into";
                bottomCover = "robe";
                bottomAccess = "into";
            } else
            if(morality > 50)
            {
                topCover = "blouse";
                topAccess = "down";
                bottomCover = "skirt";
                bottomAccess = "up";
            } else
            if(morality > 25)
            {
                topCover = "jacket";
                topAccess = "down";
                bottomCover = "trousers";
                bottomAccess = "down";
            } else
            {
                topCover = "strips";
                topAccess = "around";
                bottomCover = "strips";
                bottomAccess = "around";
            }
            if(dignity > 50)
                underType = "panties";
            else
            if(dignity > 25)
                underType = "wrap";
            else
                underType = "none";
        } else
        if(innocence > 25)
        {
            if(morality > 75)
            {
                topCover = "bodice";
                topAccess = "cutout";
                bottomCover = "skirt";
                bottomAccess = "cutout";
            } else
            if(morality > 50)
            {
                topCover = "crop";
                topAccess = "up";
                bottomCover = "miniskirt";
                bottomAccess = "front";
            } else
            if(morality > 25)
            {
                topCover = "bindings";
                topAccess = "under";
                bottomCover = "trousers";
                bottomAccess = "down";
            } else
            {
                topCover = "armor";
                topAccess = "around";
                bottomCover = "armor";
                bottomAccess = "around";
            }
            if(dignity > 75)
                underType = "shorts";
            else
            if(dignity > 50)
                underType = "g-string";
            else
            if(dignity > 25)
                underType = "straps";
            else
                underType = "none";
        } else
        {
            if(morality > 75)
            {
                topCover = "leotard";
                topAccess = "into";
                bottomCover = "leotard";
                bottomAccess = "into";
            } else
            if(morality > 50)
            {
                topCover = "bodysuit";
                topAccess = "into";
                bottomCover = "bodysuit";
                bottomAccess = "into";
            } else
            if(morality > 25)
            {
                topCover = "shirt";
                topAccess = "up";
                bottomCover = "shorts";
                bottomAccess = "down";
            } else
            {
                topCover = "belts";
                topAccess = "around";
                bottomCover = "belts";
                bottomAccess = "around";
            }
            if(dignity > 75)
                underType = "panties";
            else
            if(dignity > 50)
                underType = "panties";
            else
                underType = "none";
        }
        if(dignity > 75)
        {
            if(confidence > 50)
                color = "dark";
            else
            if(confidence > 25)
                color = "pastel";
            else
                color = "white";
        } else
        if(dignity > 50)
        {
            if(confidence > 75)
                color = "bright";
            else
            if(confidence > 50)
                color = "bright";
            else
            if(confidence > 25)
                color = "pastel";
            else
                color = "white";
        } else
        if(dignity > 25)
        {
            if(confidence > 75)
                color = "bright";
            else
            if(confidence > 50)
                color = "bright";
            else
            if(confidence > 25)
                color = "pastel";
            else
                color = "gray";
        } else
        if(confidence > 75)
            color = "clashing";
        else
        if(confidence > 50)
            color = "clashing";
        else
        if(confidence > 25)
            color = "drab";
        else
            color = "drab";
        if(confidence > 75)
        {
            if(morality > 75)
            {
                if(innocence > 75)
                    accessory = "gown";
                else
                if(innocence > 50)
                    accessory = "robe";
                else
                if(innocence > 25)
                    accessory = "dress";
                else
                    accessory = "cape";
            } else
            if(morality > 50)
                accessory = "skirt";
            else
            if(morality > 25)
                accessory = "bands";
            else
            if(innocence > 75)
                accessory = "cloak";
            else
            if(innocence > 50)
                accessory = "cloth";
            else
                accessory = "chains";
        } else
        if(confidence > 50)
        {
            if(morality > 75)
                accessory = "sleeves";
            else
            if(morality > 50)
                accessory = "ribbons";
            else
            if(morality > 25)
                accessory = "bandages";
            else
            if(innocence > 75)
                accessory = "cloak";
            else
            if(innocence > 50)
                accessory = "cloth";
            else
                accessory = "chains";
        } else
        if(confidence > 25)
        {
            if(morality > 75)
                accessory = "holy";
            else
            if(morality > 50)
                accessory = "charms";
            else
            if(morality > 25)
                accessory = "belts";
            else
                accessory = "occult";
        } else
        if(morality > 75)
            accessory = "holy";
        else
        if(morality > 50)
            accessory = "jewellery";
        else
        if(morality > 25)
            accessory = "armor";
        else
            accessory = "trophies";
        if(dignity < 26)
        {
            if(morality > 75)
                weapon = "boots";
            else
            if(morality > 50)
                weapon = "gauntlets";
            else
            if(morality > 25)
                weapon = "fists";
            else
                weapon = "claws";
        } else
        if(dignity < 51)
        {
            if(morality > 75)
                weapon = "mace";
            else
            if(morality > 50)
                weapon = "sword";
            else
            if(morality > 25)
                weapon = "greatsword";
            else
                weapon = "scythe";
        } else
        if(dignity < 76)
        {
            if(morality > 75)
                weapon = "shurikens";
            else
            if(morality > 50)
                weapon = "revolver";
            else
            if(morality > 25)
                weapon = "pistols";
            else
                weapon = "spirits";
        } else
        if(morality > 75)
            weapon = "staff";
        else
        if(morality > 50)
            weapon = "bow";
        else
        if(morality > 25)
            weapon = "rifle";
        else
            weapon = "monster";
        if(morality > 75 && dignity < 26)
            feetType = "armored boots";
        else
        if(innocence > 75)
        {
            if(confidence > 75)
                feetType = "none";
            else
            if(confidence > 50)
                feetType = "high-heeled sandals";
            else
            if(confidence > 25)
                feetType = "strappy sandals";
            else
                feetType = "ribbon-laced sandals";
        } else
        if(innocence > 50)
        {
            if(confidence > 75)
                feetType = "foot wraps";
            else
            if(confidence > 50)
                feetType = "high heels";
            else
            if(confidence > 25)
                feetType = "sneakers";
            else
                feetType = "doll shoes";
        } else
        if(innocence > 25)
        {
            if(confidence > 75)
                feetType = "combat boots";
            else
            if(confidence > 50)
                feetType = "high-heeled boots";
            else
            if(confidence > 25)
                feetType = "running shoes and athletic socks";
            else
                feetType = "loafers";
        } else
        if(confidence > 75)
            feetType = "thigh-high boots";
        else
        if(confidence > 50)
            feetType = "thigh-high stockings";
        else
        if(confidence > 25)
            feetType = "kneesocks";
        else
            feetType = "lacy stockings";
        cosmetics[0] = topCover;
        cosmetics[1] = topAccess;
        cosmetics[2] = bottomCover;
        cosmetics[3] = bottomAccess;
        cosmetics[4] = underType;
        cosmetics[5] = color;
        cosmetics[6] = accessory;
        cosmetics[7] = weapon;
        cosmetics[8] = customWeaponType;
        cosmetics[9] = feetType;
        return cosmetics;
    }

    public void cosmeticsGen(final JTextPane t, final JPanel p, final JFrame f)
    {
        p.removeAll();
        append(t, "The final step is to customize the appearances of the team.  None of the questions in this section will affect their combat performance.  Because the default appearances are connected to their personalities, players who wish to avoid spoiling themselves may prefer to skip this step.  Otherwise, click on the team member to be customized.  Clicking on a team member you've already customized will completely reset that member's customization process.");
        for(int i = 0; i < 3; i++)
        {
            JButton ThisOne = new JButton(customNames[i * 2]);
            final int thisChosen = i;
            ThisOne.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    String baseAesthetics[] = pickCosmetics(statSeed[thisChosen * 4], statSeed[thisChosen * 4 + 1], statSeed[thisChosen * 4 + 2], statSeed[thisChosen * 4 + 3]);
                    aliasCustomize(t, p, f, thisChosen, baseAesthetics);
                }

                final WorldState this$0;
                private final int val$thisChosen;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;

            
            {
                this$0 = WorldState.this;
                thisChosen = i;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
            });
            p.add(ThisOne);
        }

        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nReally go back to Vulnerability review?  The cosmetic traits set on this screen will be reset.").toString());
                JButton ReallyQuit = new JButton("Back");
                ReallyQuit.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
                        vulnerabilityMenu(t, p, f, Boolean.valueOf(false));
                    }

                    final _cls17 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls17.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                });
                p.add(ReallyQuit);
                JButton Back = new JButton("Cancel");
                Back.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
                        cosmeticsGen(t, p, f);
                    }

                    final _cls17 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls17.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                });
                p.add(Back);
                p.validate();
                p.repaint();
            }

            final WorldState this$0;
            private final JPanel val$p;
            private final JTextPane val$t;
            private final JFrame val$f;


            
            {
                this$0 = WorldState.this;
                p = jpanel;
                t = jtextpane;
                f = jframe;
                super();
            }
        });
        p.add(Back);
        final WorldState w = this;
        JButton Quit = new JButton("Quit");
        Quit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nReally quit?  All customization of this team will be lost.").toString());
                JButton ReallyQuit = new JButton("Quit");
                ReallyQuit.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
                        WorldState x = new WorldState();
                        x.copySettings(t, w);
                        x.copyToggles(w);
                        Project.IntroOne(t, p, f, x);
                    }

                    final _cls18 this$1;
                    private final JTextPane val$t;
                    private final WorldState val$w;
                    private final JPanel val$p;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls18.this;
                        t = jtextpane;
                        w = worldstate;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                });
                p.add(ReallyQuit);
                JButton Back = new JButton("Cancel");
                Back.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
                        cosmeticsGen(t, p, f);
                    }

                    final _cls18 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls18.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                });
                p.add(Back);
                p.validate();
                p.repaint();
            }

            final WorldState this$0;
            private final JPanel val$p;
            private final JTextPane val$t;
            private final WorldState val$w;
            private final JFrame val$f;


            
            {
                this$0 = WorldState.this;
                p = jpanel;
                t = jtextpane;
                w = worldstate1;
                f = jframe;
                super();
            }
        });
        JButton Finish = new JButton("Finish");
        Finish.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                p.removeAll();
                String path = Project.getProtectionDomain().getCodeSource().getLocation().getPath();
                String fileName = "";
                for(int i = path.length() - 1; i >= 0; i--)
                    if(path.charAt(i) != '/')
                        fileName = (new StringBuilder(String.valueOf(path.charAt(i)))).append(fileName).toString();
                    else
                        i = -1;

                path = path.substring(0, path.length() - fileName.length() - 1);
                try
                {
                    path = URLDecoder.decode(path, "UTF-8");
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
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
                final WriteObject wobj = new WriteObject();
                SaveData saveFile = saves;
                if(w.getCast()[0] == null)
                {
                    Chosen newChosen = new Chosen();
                    newChosen.setNumber(0);
                    newChosen.generate(w);
                    w.addChosen(newChosen);
                }
                String newSaveName = JOptionPane.showInputDialog("What would you like to name this team?");
                Boolean blankName = Boolean.valueOf(false);
                if(newSaveName == null)
                    blankName = Boolean.valueOf(true);
                else
                if(newSaveName.length() == 0)
                    blankName = Boolean.valueOf(true);
                if(blankName.booleanValue())
                {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    newSaveName = (new StringBuilder("Team of ")).append(dateFormat.format(date)).toString();
                }
                saves.endSave(w, newSaveName);
                wobj.serializeSaveData(saves);
                final String displayedName = newSaveName;
                append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nA new save file has been added in slot ").append(saves.getSaves().length).append(" which allows you to fight this team.  You may also wish to export this team to an external file for easy sharing.").toString());
                JButton Export = new JButton("Export");
                Export.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        String newFileName = JOptionPane.showInputDialog((new StringBuilder("What would you like the file to be named?  Leave blank to use '")).append(displayedName).append("'.").toString());
                        Boolean noNameProvided = Boolean.valueOf(false);
                        if(newFileName == null)
                            noNameProvided = Boolean.valueOf(true);
                        else
                        if(newFileName.length() == 0)
                            noNameProvided = Boolean.valueOf(true);
                        if(noNameProvided.booleanValue())
                            newFileName = displayedName;
                        String editedName = "";
                        for(int i = 0; i < newFileName.length(); i++)
                            if(newFileName.charAt(i) == '/' || newFileName.charAt(i) == ':')
                                editedName = (new StringBuilder(String.valueOf(editedName))).append("-").toString();
                            else
                                editedName = (new StringBuilder(String.valueOf(editedName))).append(newFileName.charAt(i)).toString();

                        wobj.exportFile(w, editedName);
                        w.append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nCustomized team saved to '").append(editedName).append(".par'.").toString());
                    }

                    final _cls19 this$1;
                    private final String val$displayedName;
                    private final WriteObject val$wobj;
                    private final WorldState val$w;
                    private final JTextPane val$t;

                    
                    {
                        this$1 = _cls19.this;
                        displayedName = s;
                        wobj = writeobject;
                        w = worldstate;
                        t = jtextpane;
                        super();
                    }
                });
                p.add(Export);
                JButton Back = new JButton("Back");
                Back.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
                        cosmeticsGen(t, p, f);
                    }

                    final _cls19 this$1;
                    private final JTextPane val$t;
                    private final JPanel val$p;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls19.this;
                        t = jtextpane;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                });
                p.add(Back);
                JButton Done = new JButton("Done");
                Done.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
                        WorldState x = new WorldState();
                        x.copySettings(t, w);
                        x.copyToggles(w);
                        Project.IntroOne(t, p, f, x);
                    }

                    final _cls19 this$1;
                    private final JTextPane val$t;
                    private final WorldState val$w;
                    private final JPanel val$p;
                    private final JFrame val$f;

                    
                    {
                        this$1 = _cls19.this;
                        t = jtextpane;
                        w = worldstate;
                        p = jpanel;
                        f = jframe;
                        super();
                    }
                });
                p.add(Done);
                p.validate();
                p.repaint();
            }

            final WorldState this$0;
            private final JPanel val$p;
            private final WorldState val$w;
            private final JTextPane val$t;
            private final JFrame val$f;


            
            {
                this$0 = WorldState.this;
                p = jpanel;
                w = worldstate1;
                t = jtextpane;
                f = jframe;
                super();
            }
        });
        p.add(Finish);
        p.add(Quit);
        p.validate();
        p.repaint();
    }

    public void aliasCustomize(final JTextPane t, final JPanel p, final JFrame f, final int i, final String baseAesthetics[])
    {
        p.removeAll();
        Chosen dummy = new Chosen();
        String HeShe = "She";
        String hisHer = "her";
        String himHer = "her";
        String heShe = "she";
        if(genders[i].equals("male"))
        {
            HeShe = "He";
            hisHer = "his";
            himHer = "him";
            heShe = "he";
        }
        final String ownChoice = dummy.genMainName(statSeed[i * 4], statSeed[i * 4 + 1], statSeed[i * 4 + 2], statSeed[i * 4 + 3]);
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nThe first step is to decide what ").append(heShe).append("'ll call ").append(himHer).append("self.  ").append(HeShe).append(" likes the sound of '").append(ownChoice).append("', but the civilian identities of the Chosen are a matter of public record, so it wouldn't be too strange for ").append(himHer).append(" to go by ").append(hisHer).append(" real name.  Which should ").append(heShe).append(" choose?").toString());
        JButton Alias = new JButton(ownChoice);
        Alias.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                customAliases[i] = ownChoice;
                titleCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final int val$i;
            private final String val$ownChoice;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                i = j;
                ownChoice = s;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Alias);
        JButton Given = new JButton(customNames[i * 2]);
        Given.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                customAliases[i] = customNames[i * 2];
                titleCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final int val$i;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                i = j;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Given);
        if(customNames[i * 2 + 1].length() > 0)
        {
            JButton Surname = new JButton(customNames[i * 2 + 1]);
            Given.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    customAliases[i] = customNames[i * 2 + 1];
                    titleCustomize(t, p, f, i, baseAesthetics);
                }

                final WorldState this$0;
                private final int val$i;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                i = j;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                baseAesthetics = as;
                super();
            }
            });
            p.add(Surname);
        }
        JButton Custom = new JButton("Something else");
        Custom.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                String input = JOptionPane.showInputDialog((new StringBuilder("Type the name to be used here.  Leave blank to use the default, '")).append(ownChoice).append("'.").toString());
                customAliases[i] = ownChoice;
                if(input != null && input.length() > 0)
                    customAliases[i] = input;
                titleCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final String val$ownChoice;
            private final int val$i;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                ownChoice = s;
                i = j;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Custom);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").toString());
                cosmeticsGen(t, p, f);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public String[] getCustomAliases()
    {
        return customAliases;
    }

    public String[] getCustomNames()
    {
        return customNames;
    }

    public Boolean[] getGaijinStatus()
    {
        return gaijinStatus;
    }

    public void titleCustomize(final JTextPane t, final JPanel p, final JFrame f, final int i, final String baseAesthetics[])
    {
        p.removeAll();
        Chosen dummy = new Chosen();
        String hisHer = "her";
        String himHer = "her";
        String heShe = "she";
        if(genders[i].equals("male"))
        {
            hisHer = "his";
            himHer = "him";
            heShe = "he";
        }
        if(genders[i].equals("male"))
            dummy.gender = "male";
        String ownChoice = (new StringBuilder(String.valueOf(dummy.genAdjectiveName(statSeed[i * 4 + 1], statSeed[i * 4 + 2])))).append(" ").append(dummy.genNounName(statSeed[i * 4], statSeed[i * 4 + 1])).toString();
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nMost Chosen also use a descriptive title that defines how they see themselves.  ").append(customAliases[i]).append("'s first idea is to use '").append(ownChoice).append("', so that ").append(heShe).append("'d be '").append(ownChoice).append(" ").append(customAliases[i]).append("'.  Should ").append(heShe).append(" use something different?").toString());
        customTitles[i] = ownChoice;
        JButton Default = new JButton(ownChoice);
        Default.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                incantationCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Default);
        JButton Custom = new JButton("Something else");
        Custom.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                String input = JOptionPane.showInputDialog((new StringBuilder("Type the title to be used here.  Leave blank to just go by '")).append(customAliases[i]).append("'.").toString());
                if(input != null)
                    if(input.length() > 0)
                        customTitles[i] = input;
                    else
                        customTitles[i] = "none";
                incantationCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final int val$i;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                i = j;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Custom);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                aliasCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public String[] getCustomTitles()
    {
        return customTitles;
    }

    public void incantationCustomize(final JTextPane t, final JPanel p, final JFrame f, final int i, final String baseAesthetics[])
    {
        p.removeAll();
        Chosen dummy = new Chosen();
        String hisHer = "her";
        String himHer = "her";
        String heShe = "she";
        if(genders[i].equals("male"))
        {
            hisHer = "his";
            himHer = "him";
            heShe = "he";
        }
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nIn order to transform from ").append(customNames[i * 2]).append(" into ").toString());
        if(!customTitles[i].equals("none") || !customAliases[i].equals(customNames[i * 2]))
        {
            if(customTitles[i].equals("none"))
                append(t, customAliases[i]);
            else
                append(t, (new StringBuilder(String.valueOf(customTitles[i]))).append(" ").append(customAliases[i]).toString());
        } else
        {
            append(t, (new StringBuilder(String.valueOf(dummy.hisHer()))).append(" Chosen identity").toString());
        }
        String ownChoice = dummy.genIncantation(statSeed[i * 4], statSeed[i * 4 + 3]);
        append(t, (new StringBuilder(", ")).append(heShe).append(" needs to speak an incantation of ").append(hisHer).append(" choice.  The first that comes to ").append(hisHer).append(" mind is '").append(ownChoice).append("'.").toString());
        customIncantations[i] = ownChoice;
        JButton Keep = new JButton("Keep");
        Keep.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                topCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Keep);
        JButton Change = new JButton("Change");
        Change.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                String input = JOptionPane.showInputDialog("Type the new incantation here.  Leave blank to leave it unchanged.");
                if(input != null && input.length() > 0)
                    customIncantations[i] = input;
                topCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final int val$i;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                i = j;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Change);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                titleCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public String[] getCustomIncantations()
    {
        return customIncantations;
    }

    public void topCustomize(final JTextPane t, final JPanel p, final JFrame f, final int i, final String baseAesthetics[])
    {
        p.removeAll();
        String hisHer = "her";
        String himHer = "her";
        String heShe = "she";
        if(genders[i].equals("male"))
        {
            hisHer = "his";
            himHer = "him";
            heShe = "he";
        }
        String result = "";
        result = customIncantations[i];
        if(!customTitles[i].equals("none"))
            result = (new StringBuilder(String.valueOf(result))).append("  ").append(customTitles[i]).toString();
        else
            result = (new StringBuilder(String.valueOf(result))).append(" ").toString();
        result = (new StringBuilder(String.valueOf(result))).append(" ").append(customAliases[i]).append(", transform!").toString();
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").append(customNames[i * 2]).append("'s civilian clothes will disintegrate when ").append(heShe).append(" says '").append(result).append("'  In their place, garments and equipment woven of psychic energy representing ").append(hisHer).append(" true nature will materialize.  Click 'Change' to give ").append(himHer).append(" something different, or click the button for the current item to keep it.\n\nFirst off, what does ").append(heShe).append(" wear to cover ").append(hisHer).append(" chest?").toString());
        String current = baseAesthetics[0];
        if(current.equals("strips"))
            current = "strips of cloth";
        else
        if(current.equals("bindings"))
            current = "wrapped chest bindings";
        else
        if(current.equals("crop"))
            current = "crop top";
        final String finalCurrent = current;
        JButton Default = new JButton(current);
        Default.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                bottomCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Default);
        JButton Change = new JButton("Change");
        Change.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                String input = JOptionPane.showInputDialog((new StringBuilder("Type the name of the garment here.  Leave blank to use '")).append(finalCurrent).append("'.").toString());
                Boolean changed = Boolean.valueOf(false);
                if(input != null && !input.equals(finalCurrent) && input.length() > 0)
                    changed = Boolean.valueOf(true);
                if(changed.booleanValue())
                    topChange(t, p, f, i, baseAesthetics, input);
                else
                    bottomCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final String val$finalCurrent;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                finalCurrent = s;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Change);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                incantationCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public String[] getCustomTop()
    {
        return customTop;
    }

    public void topChange(final JTextPane t, final JPanel p, final JFrame f, final int i, final String baseAesthetics[], final String input)
    {
        p.removeAll();
        String hisHer = "her";
        String himHer = "her";
        String heShe = "she";
        if(genders[i].equals("male"))
        {
            hisHer = "his";
            himHer = "him";
            heShe = "he";
        }
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nAnd in order to get at ").append(hisHer).append(" breasts, does one go up ").append(hisHer).append(" ").append(input).append(", into ").append(hisHer).append(" ").append(input).append(", down ").append(hisHer).append(" ").append(input).append(", or around ").append(hisHer).append(" ").append(input).append("?").toString());
        for(int j = 0; j < 4; j++)
        {
            String method = "";
            if(j == 0)
                method = "up";
            else
            if(j == 1)
                method = "into";
            else
            if(j == 2)
                method = "down";
            else
            if(j == 3)
                method = "around";
            final String finalMethod = method;
            JButton ThisOne = new JButton(method);
            ThisOne.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    baseAesthetics[0] = input;
                    baseAesthetics[1] = finalMethod;
                    bottomCustomize(t, p, f, i, baseAesthetics);
                }

                final WorldState this$0;
                private final String val$baseAesthetics[];
                private final String val$input;
                private final String val$finalMethod;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final int val$i;

            
            {
                this$0 = WorldState.this;
                baseAesthetics = as;
                input = s;
                finalMethod = s1;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                super();
            }
            });
            p.add(ThisOne);
        }

        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                topCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public String[] getCustomTopAccess()
    {
        return customTopAccess;
    }

    public void bottomCustomize(final JTextPane t, final JPanel p, final JFrame f, final int i, final String baseAesthetics[])
    {
        p.removeAll();
        String hisHer = "her";
        String himHer = "her";
        String heShe = "she";
        if(genders[i].equals("male"))
        {
            hisHer = "his";
            himHer = "him";
            heShe = "he";
        }
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nNext, what does ").append(heShe).append(" wear to cover ").append(hisHer).append(" hips?").toString());
        String current = baseAesthetics[2];
        if(current.equals("strips"))
            current = "strips of cloth";
        final String finalCurrent = current;
        JButton Default = new JButton(current);
        Default.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                feetCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Default);
        JButton Change = new JButton("Change");
        Change.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                String input = JOptionPane.showInputDialog((new StringBuilder("Type the name of the garment here.  Leave blank to use '")).append(finalCurrent).append("'.").toString());
                Boolean changed = Boolean.valueOf(false);
                if(input != null && !input.equals(finalCurrent) && input.length() > 0)
                    changed = Boolean.valueOf(true);
                if(changed.booleanValue())
                    bottomChange(t, p, f, i, baseAesthetics, input);
                else
                    feetCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final String val$finalCurrent;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                finalCurrent = s;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Change);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                topCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public String[] getCustomBottom()
    {
        return customBottom;
    }

    public void bottomChange(final JTextPane t, final JPanel p, final JFrame f, final int i, final String baseAesthetics[], final String input)
    {
        p.removeAll();
        String hisHer = "her";
        String himHer = "her";
        String heShe = "she";
        String organ = "pussy";
        if(genders[i].equals("male"))
        {
            hisHer = "his";
            himHer = "him";
            heShe = "he";
            organ = "penis";
        }
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nAnd in order to get at ").append(hisHer).append(" ").append(organ).append(", does one go up ").append(hisHer).append(" ").append(input).append(", into ").append(hisHer).append(" ").append(input).append(", down ").append(hisHer).append(" ").append(input).append(", or around ").append(hisHer).append(" ").append(input).append("?").toString());
        for(int j = 0; j < 4; j++)
        {
            String method = "";
            if(j == 0)
                method = "up";
            else
            if(j == 1)
                method = "into";
            else
            if(j == 2)
                method = "down";
            else
            if(j == 3)
                method = "around";
            final String finalMethod = method;
            JButton ThisOne = new JButton(method);
            ThisOne.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    baseAesthetics[2] = input;
                    baseAesthetics[3] = finalMethod;
                    feetCustomize(t, p, f, i, baseAesthetics);
                }

                final WorldState this$0;
                private final String val$baseAesthetics[];
                private final String val$input;
                private final String val$finalMethod;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final int val$i;

            
            {
                this$0 = WorldState.this;
                baseAesthetics = as;
                input = s;
                finalMethod = s1;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                super();
            }
            });
            p.add(ThisOne);
        }

        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                bottomCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public String[] getCustomBottomAccess()
    {
        return customBottomAccess;
    }

    public String[] getCustomFeet()
    {
        return customFeet;
    }

    public void feetCustomize(final JTextPane t, final JPanel p, final JFrame f, final int i, final String baseAesthetics[])
    {
        p.removeAll();
        String hisHer = "her";
        String himHer = "her";
        String heShe = "she";
        if(genders[i].equals("male"))
        {
            hisHer = "his";
            himHer = "him";
            heShe = "he";
        }
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nWhat footwear does ").append(customAliases[i]).append("'s transformation give ").append(himHer).append("?  Enter 'none' (without the quotes) to have ").append(himHer).append(" go barefoot.").toString());
        final String current = baseAesthetics[9];
        JButton Default = new JButton(current);
        Default.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                colorCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Default);
        JButton Change = new JButton("Change");
        Change.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                String input = JOptionPane.showInputDialog((new StringBuilder("Type the name of the garment here.  Leave blank to use '")).append(current).append("'.").toString());
                if(input != null && !input.equals(current) && input.length() > 0)
                    baseAesthetics[9] = input;
                colorCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final String val$current;
            private final String val$baseAesthetics[];
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;

            
            {
                this$0 = WorldState.this;
                current = s;
                baseAesthetics = as;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                super();
            }
        });
        p.add(Change);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                bottomCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public void colorCustomize(final JTextPane t, final JPanel p, final JFrame f, final int i, final String baseAesthetics[])
    {
        p.removeAll();
        String hisHer = "her";
        String himHer = "her";
        String heShe = "she";
        if(genders[i].equals("male"))
        {
            hisHer = "his";
            himHer = "him";
            heShe = "he";
        }
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nWhen ").append(heShe).append("'s transformed, ").append(customAliases[i]).append(" is surrounded by '").append(baseAesthetics[5]).append("' light.  Is this alright?").toString());
        JButton Keep = new JButton("Yes");
        Keep.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                weaponCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Keep);
        JButton Change = new JButton("Change");
        Change.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                String input = JOptionPane.showInputDialog((new StringBuilder("Type the color description to be used.  Leave blank to use '")).append(baseAesthetics[5]).append("'.").toString());
                if(input != null && !input.equals(baseAesthetics[5]) && input.length() > 0)
                    baseAesthetics[5] = input;
                weaponCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final String val$baseAesthetics[];
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;

            
            {
                this$0 = WorldState.this;
                baseAesthetics = as;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                super();
            }
        });
        p.add(Change);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                bottomCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public String[] getCustomColor()
    {
        return customColor;
    }

    public String[] getCustomAccessory()
    {
        return customAccessory;
    }

    public void weaponCustomize(final JTextPane t, final JPanel p, final JFrame f, final int i, final String baseAesthetics[])
    {
        p.removeAll();
        String hisHer = "her";
        String himHer = "her";
        String heShe = "she";
        if(genders[i].equals("male"))
        {
            hisHer = "his";
            himHer = "him";
            heShe = "he";
        }
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nCurrently, ").append(customAliases[i]).append(" is set to fight using ").append(hisHer).append(" ").append(baseAesthetics[7]).append(".  Is this okay?").toString());
        JButton Keep = new JButton("Yes");
        Keep.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                underwearCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Keep);
        JButton Change = new JButton("Change");
        Change.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                String input = JOptionPane.showInputDialog((new StringBuilder("Type the name of the weapon to be used.  Leave blank to use '")).append(baseAesthetics[7]).append("'.").toString());
                Boolean changed = Boolean.valueOf(false);
                if(input != null && !input.equals(baseAesthetics[7]) && input.length() > 0)
                    changed = Boolean.valueOf(true);
                if(changed.booleanValue())
                    weaponChange(t, p, f, i, baseAesthetics, input);
                else
                    underwearCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final String val$baseAesthetics[];
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;

            
            {
                this$0 = WorldState.this;
                baseAesthetics = as;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                super();
            }
        });
        p.add(Change);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                colorCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public String[] getCustomWeapon()
    {
        return customWeapons;
    }

    public void weaponChange(final JTextPane t, final JPanel p, final JFrame f, final int i, final String baseAesthetics[], final String input)
    {
        p.removeAll();
        String hisHer = "her";
        String himHer = "her";
        String heShe = "she";
        if(genders[i].equals("male"))
        {
            hisHer = "his";
            himHer = "him";
            heShe = "he";
        }
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nDoes ").append(customAliases[i]).append(" swing ").append(hisHer).append(" ").append(input).append(", shoot ").append(hisHer).append(" ").append(input).append(", command ").append(hisHer).append(" ").append(input).append(", or is ").append(hisHer).append(" weapon a part of ").append(himHer).append("?").toString());
        for(int j = 0; j < 4; j++)
        {
            String method = "";
            if(j == 0)
                method = "swing";
            else
            if(j == 1)
                method = "shoot";
            else
            if(j == 2)
                method = "command";
            else
            if(j == 3)
                method = (new StringBuilder("part of ")).append(himHer).toString();
            JButton ThisOne = new JButton(method);
            if(method.contains("part"))
                method = "part";
            final String finalMethod = method;
            ThisOne.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    baseAesthetics[7] = input;
                    baseAesthetics[8] = finalMethod;
                    underwearCustomize(t, p, f, i, baseAesthetics);
                }

                final WorldState this$0;
                private final String val$baseAesthetics[];
                private final String val$input;
                private final String val$finalMethod;
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final int val$i;

            
            {
                this$0 = WorldState.this;
                baseAesthetics = as;
                input = s;
                finalMethod = s1;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                super();
            }
            });
            p.add(ThisOne);
        }

        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                weaponCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public String[] getCustomWeaponType()
    {
        return customWeaponTypes;
    }

    public void underwearCustomize(final JTextPane t, final JPanel p, final JFrame f, final int i, final String baseAesthetics[])
    {
        p.removeAll();
        String hisHer = "her";
        String himHer = "her";
        String heShe = "she";
        if(genders[i].equals("male"))
        {
            hisHer = "his";
            himHer = "him";
            heShe = "he";
        }
        String bottomDesc = baseAesthetics[2];
        if(baseAesthetics[2].equals("strips"))
            bottomDesc = "strips of cloth";
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\nThere's one final important question.  ").toString());
        if(baseAesthetics[4].equals("none"))
        {
            append(t, (new StringBuilder(String.valueOf(customAliases[i]))).append("'s outfit doesn't currently include panties.  Should that be changed?").toString());
            JButton Change = new JButton("Wear panties");
            Change.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    baseAesthetics[4] = "panties";
                    finalizeCustomization(t, p, f, i, baseAesthetics);
                }

                final WorldState this$0;
                private final String val$baseAesthetics[];
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final int val$i;

            
            {
                this$0 = WorldState.this;
                baseAesthetics = as;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                super();
            }
            });
            p.add(Change);
        } else
        {
            append(t, (new StringBuilder("Would you prefer for ")).append(customAliases[i]).append(" to stop wearing anything under ").append(hisHer).append(" ").append(bottomDesc).append("?").toString());
            JButton Change = new JButton("Wear nothing");
            Change.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    baseAesthetics[4] = "none";
                    finalizeCustomization(t, p, f, i, baseAesthetics);
                }

                final WorldState this$0;
                private final String val$baseAesthetics[];
                private final JTextPane val$t;
                private final JPanel val$p;
                private final JFrame val$f;
                private final int val$i;

            
            {
                this$0 = WorldState.this;
                baseAesthetics = as;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                super();
            }
            });
            p.add(Change);
        }
        JButton Keep = new JButton("Leave it as is");
        Keep.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                finalizeCustomization(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Keep);
        JButton Back = new JButton("Back");
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                weaponCustomize(t, p, f, i, baseAesthetics);
            }

            final WorldState this$0;
            private final JTextPane val$t;
            private final JPanel val$p;
            private final JFrame val$f;
            private final int val$i;
            private final String val$baseAesthetics[];

            
            {
                this$0 = WorldState.this;
                t = jtextpane;
                p = jpanel;
                f = jframe;
                i = j;
                baseAesthetics = as;
                super();
            }
        });
        p.add(Back);
        p.validate();
        p.repaint();
    }

    public String[] getCustomUnder()
    {
        return customUnder;
    }

    public void finalizeCustomization(JTextPane t, JPanel p, JFrame f, int i, String baseAesthetics[])
    {
        p.removeAll();
        String comparison[] = pickCosmetics(statSeed[i * 4], statSeed[i * 4 + 1], statSeed[i * 4 + 2], statSeed[i * 4 + 3]);
        for(int j = 0; j < comparison.length; j++)
            if(!comparison[j].equals(baseAesthetics[j]))
                baseAesthetics[6] = "none";

        customTop[i] = baseAesthetics[0];
        customTopAccess[i] = baseAesthetics[1];
        customBottom[i] = baseAesthetics[2];
        customBottomAccess[i] = baseAesthetics[3];
        customUnder[i] = baseAesthetics[4];
        customColor[i] = baseAesthetics[5];
        customAccessory[i] = baseAesthetics[6];
        customWeapons[i] = baseAesthetics[7];
        customWeaponTypes[i] = baseAesthetics[8];
        customFeet[i] = baseAesthetics[9];
        append(t, (new StringBuilder("\n\n")).append(separator).append("\n\n").append(customAliases[i]).append("'s customization is complete!\n\n").toString());
        cosmeticsGen(t, p, f);
    }

    public WorldState()
    {
        textSize = 16;
        version = "9";
        PURPLE = new Color(100, 0, 150);
        ORANGE = new Color(200, 100, 0);
        RED = new Color(180, 0, 0);
        GREEN = new Color(0, 110, 0);
        BLUE = new Color(0, 0, 230);
        BACKGROUND = Color.WHITE;
        FOREGROUND = Color.BLACK;
        separator = "---";
        nameSeed = new int[6];
        statSeed = new int[12];
        maleShift = 0;
        femaleShift = 0;
        tickleOn = Boolean.valueOf(false);
        totalThreatened = 0;
        totalSlimed = 0;
        totalAttacked = 0;
        totalTaunted = 0;
        nextTip = 0;
        cast = new Chosen[3];
        techs = new Tech[34];
        evilEnergy = 0;
        day = 1;
        totalRounds = 1;
        highScore = 0L;
        parScore = 0L;
        saveTitle = "";
        friendship = new int[3][3];
        friction = new int[3][3];
        remaining = new int[3][3];
        groupScenes = new Boolean[20];
        lastSpeaker = null;
        lastLine = 0;
        lastLastLine = 0;
        pendingBreaks = new int[0];
        resolvedBreaks = new int[0];
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
        barrierMulti = 10000L;
        bodyStatus = new Boolean[19];
        tutorial = Boolean.valueOf(false);
        onTrack = Boolean.valueOf(true);
        commentary = new String[0];
        currentComment = "";
        actions = new int[0];
        currentAction = 0;
        recordedCommanders = new Boolean[0][bodyStatus.length];
        commentaryRead = Boolean.valueOf(true);
        commentaryWrite = Boolean.valueOf(false);
        cheater = Boolean.valueOf(false);
        earlyCheat = Boolean.valueOf(false);
        customNames = new String[6];
        customAliases = new String[3];
        customTitles = new String[3];
        customIncantations = new String[3];
        personalityWeights = new int[24][3][8];
        quizAnswers = new String[24];
    }

    private static final long serialVersionUID = 4L;
    int textSize;
    String version;
    Color PURPLE;
    Color ORANGE;
    Color RED;
    Color GREEN;
    Color BLUE;
    Color BACKGROUND;
    Color FOREGROUND;
    String separator;
    int nameSeed[];
    int statSeed[];
    String genders[] = {
        "female", "female", "female"
    };
    int genderBalance[] = {
        0, 3, 0, 0
    };
    int maleShift;
    int femaleShift;
    Boolean tickleOn;
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
    long parScore;
    String saveTitle;
    int friendship[][];
    int friction[][];
    int remaining[][];
    Boolean groupScenes[];
    Chosen lastSpeaker;
    int lastLine;
    int lastLastLine;
    int pendingBreaks[];
    int resolvedBreaks[];
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
    long barrierMulti;
    Boolean bodyStatus[];
    Boolean tutorial;
    Boolean onTrack;
    String commentary[];
    String currentComment;
    int actions[];
    int currentAction;
    Boolean recordedCommanders[][];
    Boolean commentaryRead;
    Boolean commentaryWrite;
    Boolean cheater;
    Boolean earlyCheat;
    String customNames[];
    String customTop[] = {
        "", "", ""
    };
    String customTopAccess[] = {
        "", "", ""
    };
    String customBottom[] = {
        "", "", ""
    };
    String customBottomAccess[] = {
        "", "", ""
    };
    String customUnder[] = {
        "", "", ""
    };
    String customColor[] = {
        "", "", ""
    };
    String customAccessory[] = {
        "", "", ""
    };
    String customWeapons[] = {
        "", "", ""
    };
    String customWeaponTypes[] = {
        "", "", ""
    };
    String customFeet[] = {
        "", "", ""
    };
    String customAliases[];
    String customTitles[];
    String customIncantations[];
    Boolean invertVVirg[] = {
        Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false)
    };
    Boolean invertCVirg[] = {
        Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false)
    };
    Boolean invertAVirg[] = {
        Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false)
    };
    Boolean invertModest[] = {
        Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false)
    };
    Boolean gaijinStatus[] = {
        Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false)
    };
    int personalityWeights[][][];
    String quizAnswers[];
}
