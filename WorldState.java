
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

    public void Orgy(JTextPane t, JPanel p, JFrame f, Chosen c)
    {
        WorldState w = this;
        String topDesc = c.topCover;
        int morality = c.morality;
        int innocence = c.innocence;
        int confidence = c.confidence;
        int dignity = c.dignity;
        Boolean vVirg = c.vVirg;
        Boolean cVirg = c.cVirg;
        Boolean aVirg = c.aVirg;
        Boolean modest = c.modest;
        String mainName = c.getMainName();
        String bottomCover = c.bottomCover;
        if(topDesc.equals("crop"))
            topDesc = "crop top";
        else
        if(topDesc.equals("strips"))
            topDesc = "strips of cloth";
        String bottomDesc = c.bottomCover;
        String topCover = c.topCover;
        String feetType = c.feetType;
        String underType = c.underType;
        String bottomAccess = c.bottomAccess;
        if(bottomDesc.equals("strips"))
            bottomDesc = "strips of cloth";
        String hole = "pussy";
        if(c.getGender().equals("male"))
            hole = "anus";
        String organ = "penis";
        if(c.getGender().equals("female"))
            organ = "clit";
        int thisAttack = c.nextAttack[0];
        c.nextAttack[0] = c.nextAttack[1];
        c.nextAttack[1] = c.nextAttack[2];
        c.nextAttack[2] = thisAttack;
        Chosen high = null;
        Chosen mid = null;
        Chosen low = null;
        for(int i = 0; i < 3; i++)
            if(w.getCast()[i].getConfidence() > 66)
                high = w.getCast()[i];
            else
            if(w.getCast()[i].getConfidence() > 33)
                mid = w.getCast()[i];
            else
                low = w.getCast()[i];

        int variant = w.getOrgyStage();
        int valueCheck = 0;
        int order = 0;
        if(c.confidence > 66)
            order = 0;
        else
        if(c.confidence > 33)
            order = 1;
        else
            order = 2;
        valueCheck = w.getLastOrgyStage()[order];
        if(valueCheck == variant)
        {
            w.cycleOrgyStage();
            variant = w.getOrgyStage();
        }
        for(Boolean variantFound = Boolean.valueOf(false); !variantFound.booleanValue();)
            if((c.vVirg.booleanValue() || c.getHATELevel() < 3) && (c.cVirg.booleanValue() || c.getPLEALevel() < 3) && (c.aVirg.booleanValue() || c.getINJULevel() < 3) && (c.modest.booleanValue() || c.getEXPOLevel() < 3))
            {
                variant = 4;
                variantFound = Boolean.valueOf(true);
            } else
            if(variant == 0 && !c.vVirg.booleanValue() && c.getHATELevel() >= 3 || variant == 1 && !c.cVirg.booleanValue() && c.getPLEALevel() >= 3 || variant == 2 && !c.aVirg.booleanValue() && c.getINJULevel() >= 3 || variant == 3 && !c.modest.booleanValue() && c.getEXPOLevel() >= 3)
            {
                variantFound = Boolean.valueOf(true);
            } else
            {
                w.cycleOrgyStage();
                variant = w.getOrgyStage();
            }

        Boolean solo = Boolean.valueOf(true);
        Boolean trio = Boolean.valueOf(false);
        Chosen partner = null;
        Boolean hated = Boolean.valueOf(true);
        Boolean loved = Boolean.valueOf(false);
        Chosen lover = null;
        for(int i = 0; i < 3; i++)
            if(w.getCast()[i] != c)
            {
                if(variant == 0 && w.getCast()[i].getHATELevel() >= 3 && !w.getCast()[i].isVVirg() || variant == 1 && w.getCast()[i].getPLEALevel() >= 3 && !w.getCast()[i].isCVirg() || variant == 2 && w.getCast()[i].getINJULevel() >= 3 && !w.getCast()[i].isAVirg() || variant == 3 && w.getCast()[i].getEXPOLevel() >= 3 && !w.getCast()[i].isModest())
                    if(partner == null)
                    {
                        partner = w.getCast()[i];
                        solo = Boolean.valueOf(false);
                    } else
                    {
                        trio = Boolean.valueOf(true);
                    }
                if(w.getRelationship(c.number, w.getCast()[i].getNumber()) >= 0)
                    if(lover == null)
                    {
                        lover = w.getCast()[i];
                        hated = Boolean.valueOf(false);
                    } else
                    {
                        loved = Boolean.valueOf(true);
                    }
            }

        if(c.confidence > 66)
        {
            if(variant == 0)
            {
                if(c.getEXPOLevel() < 3 || c.modest.booleanValue())
                {
                    if(dignity > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" glares indignantly at the Thralls pulling aside ").append(c.hisHer()).append(" ").append(bottomDesc).toString());
                    else
                    if(dignity > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" struggles with all ").append(c.hisHer()).append(" strength, giving pause to the Thralls pulling aside ").append(c.hisHer()).append(" ").append(bottomDesc).toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" kicks and screams at the Thralls pulling aside ").append(c.hisHer()).append(" ").append(bottomDesc).toString());
                } else
                if(dignity > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" blushes bright red from anger and indignation as the Thralls bring their cameras close to ").append(c.hisHer()).append(" crotch").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" struggles with all ").append(c.hisHer()).append(" strength, giving pause to the Thralls spreading ").append(c.hisHer()).append(" legs in order to film ").append(c.hisHer()).append(" crotch").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" kicks and screams at the Thralls bringing their cameras close to ").append(c.hisHer()).append(" crotch").toString());
                String another = "one of the Thralls ";
                if(c.getINJULevel() < 3 || aVirg.booleanValue())
                {
                    if(morality > 66)
                        w.append(t, (new StringBuilder(", eyes focused and determined.  However, ")).append(c.heShe()).append(" ").toString());
                    else
                    if(morality > 33)
                        w.append(t, (new StringBuilder(", not even close to giving up.  However, ")).append(c.heShe()).append(" ").toString());
                    else
                        w.append(t, (new StringBuilder(", eyes full of rage and hatred.  However, ")).append(c.heShe()).append(" ").toString());
                } else
                if(w.tickle().booleanValue())
                {
                    if(morality > 66)
                        w.append(t, (new StringBuilder(", but ")).append(c.hisHer()).append(" heroic demeanor is shattered when the Thralls start tickling ").append(c.himHer()).append(", drawing out shrieks of laughter.  ").toString());
                    else
                    if(morality > 33)
                        w.append(t, (new StringBuilder(", but then the Thralls start tickling ")).append(c.himHer()).append(", causing ").append(c.himHer()).append(" to burst into helpless laughter that's very unlike ").append(c.hisHer()).append(" normal severe expression.  ").toString());
                    else
                        w.append(t, (new StringBuilder(", but then the Thralls start tickling ")).append(c.himHer()).append(", and ").append(c.hisHer()).append(" bloodthirsty expression melts into helpless laughter.  ").toString());
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" ").toString());
                } else
                if(c.getGender().equals("male"))
                {
                    if(morality > 66)
                        w.append(t, (new StringBuilder(", only to start cursing and screaming openly when the Thralls start stomping on ")).append(c.hisHer()).append(" penis.  ").toString());
                    else
                    if(morality > 33)
                        w.append(t, (new StringBuilder(", grunting in pain and struggling to keep ")).append(c.hisHer()).append(" cool as the Thralls viciously stomp on ").append(c.hisHer()).append(" penis.  ").toString());
                    else
                        w.append(t, (new StringBuilder(", shouting threats that devolve into cries of pain as the Thralls stomp on ")).append(c.hisHer()).append(" penis.  ").toString());
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" ").toString());
                } else
                {
                    if(morality > 66)
                        w.append(t, (new StringBuilder(", only to start wailing openly when one of the Thralls pushes his cock into ")).append(c.hisHer()).append(" ass.  ").toString());
                    else
                    if(morality > 33)
                        w.append(t, (new StringBuilder(", grunting in pain and struggling to keep ")).append(c.hisHer()).append(" cool as ").append(c.heShe()).append(" feels one of the Thralls' cocks begin forcing its way up ").append(c.hisHer()).append(" ass.  ").toString());
                    else
                        w.append(t, (new StringBuilder(", shouting threats that devolve into pitiful squeals as one of the Thralls forces himself further and further up ")).append(c.hisHer()).append(" ass.  ").toString());
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" ").toString());
                    another = "another Thrall ";
                }
                if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder("screams as ")).append(another).append("penetrates ").append(c.hisHer()).append(" ").append(hole).append(", vainly calling him names and trying to push him away.  ").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder("grits ")).append(c.hisHer()).append(" teeth as ").append(c.heShe()).append(" endures ").append(another).append("fucking ").append(c.hisHer()).append(" ").append(hole).append(".  ").toString());
                    else
                        w.append(t, (new StringBuilder("huffs in indignation when ")).append(another).append("begins to slowly penetrate ").append(c.hisHer()).append(" ").append(hole).append(", doing ").append(c.hisHer()).append(" best to ignore the unwelcome violation and search for a way to escape.  ").toString());
                } else
                if(innocence > 66)
                    w.append(t, (new StringBuilder("cums instantly when ")).append(another).append("starts fucking ").append(c.hisHer()).append(" ").append(hole).append(", too caught up in the pleasure to care about resisting anymore.  ").toString());
                else
                if(innocence > 33)
                    w.append(t, (new StringBuilder("can only grit ")).append(c.hisHer()).append(" teeth against the pleasure when ").append(another).append("starts fucking ").append(c.hisHer()).append(" ").append(hole).append(", and it's not long before ").append(c.heShe()).append("'s moaning in an unwilling climax.  ").toString());
                else
                    w.append(t, (new StringBuilder("stifles a desperate moan when ")).append(another).append("penetrates ").append(c.hisHer()).append(" ").append(hole).append(", clinging to ").append(c.hisHer()).append(" sanity for only a few moments before the overwhelming pleasure forces ").append(c.himHer()).append(" to cum.  ").toString());
                if(solo.booleanValue())
                {
                    if(mid.getHATELevel() < 3)
                    {
                        if(low.getHATELevel() < 3)
                            w.append(t, (new StringBuilder("With the other Chosen's Sexual Barriers still intact, the Thralls eagerly gather around to put ")).append(mainName).append(" in ").append(c.hisHer()).append(" place.").toString());
                        else
                            w.append(t, (new StringBuilder("With ")).append(mid.getMainName()).append("'s Sexual Barrier still intact and ").append(low.getMainName()).append(" occupied by other tortures, the Thralls eagerly gather around to put ").append(mainName).append(" in ").append(c.hisHer()).append(" place.").toString());
                    } else
                    if(low.getHATELevel() < 3)
                        w.append(t, (new StringBuilder("With ")).append(low.getMainName()).append("'s Sexual Barrier still intact and ").append(mid.getMainName()).append(" still managing to barely fend off ").append(mid.hisHer()).append(" attackers, the Thralls eagerly gather around to put ").append(mainName).append(" in ").append(c.hisHer()).append(" place.").toString());
                    else
                        w.append(t, (new StringBuilder("With ")).append(mid.getMainName()).append(" still managing to fend off ").append(mid.hisHer()).append(" attackers and ").append(low.getMainName()).append(" occupied by other tortures, the Thralls eagerly gather around to put ").append(mainName).append(" in ").append(c.hisHer()).append(" place.").toString());
                } else
                if(trio.booleanValue())
                {
                    if(hated.booleanValue())
                        w.append(t, (new StringBuilder(String.valueOf(mid.getMainName()))).append(" and ").append(low.getMainName()).append(", who are also being raped by the Thralls, glare angrily at ").append(mainName).append(" - silently blaming ").append(c.himHer()).append(" for not using ").append(c.hisHer()).append(" greater strength to stop this.").toString());
                    else
                    if(loved.booleanValue())
                        w.append(t, (new StringBuilder("The Thralls raping ")).append(mid.getMainName()).append(" and ").append(low.getMainName()).append(" threaten to hurt them worse unless ").append(mainName).append(" moves ").append(c.hisHer()).append(" hips on ").append(c.hisHer()).append(" own, and ").append(c.heShe()).append(" tries to focus and go along with it.").toString());
                    else
                        w.append(t, (new StringBuilder("The Thralls raping ")).append(mid.getMainName()).append(" and ").append(low.getMainName()).append(" threaten to hurt them worse unless ").append(mainName).append(" moves ").append(c.hisHer()).append(" hips on ").append(c.hisHer()).append(" own, and ").append(c.heShe()).append(" complies - mostly for ").append(lover.getMainName()).append("'s sake.").toString());
                } else
                if(loved.booleanValue() || partner == lover)
                    w.append(t, (new StringBuilder("At the sight of ")).append(partner.getMainName()).append(" breaking under ").append(partner.hisHer()).append(" own rape, ").append(mainName).append(" challenges the Thralls to come after ").append(c.himHer()).append(" instead, taking some of the pressure off ").append(c.hisHer()).append(" friend.").toString());
                else
                    w.append(t, (new StringBuilder("The Thrall raping ")).append(partner.getMainName()).append(" pushes ").append(partner.hisHer()).append(" crotch against ").append(mainName).append("'s, forcing ").append(partner.getMainName()).append(" to grind against ").append(c.himHer()).append(".  The other Thralls laugh at the anger that's obvious on their faces.").toString());
            } else
            if(variant == 1)
            {
                if(c.getEXPOLevel() < 3 || modest.booleanValue())
                {
                    if(dignity > 66)
                        w.append(t, (new StringBuilder("Full of indignant fury, ")).append(mainName).append(" tries to fend off the Thralls in the process of stripping ").append(c.himHer()).append(", but ").toString());
                    else
                    if(dignity > 33)
                        w.append(t, (new StringBuilder("A stubborn set in ")).append(c.hisHer()).append(" jaw, ").append(mainName).append(" firmly grasps ").append(c.hisHer()).append(" ").append(bottomDesc).append(" and refuses to let the Thralls strip ").append(c.himHer()).append(", but ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is struggling with all ").append(c.hisHer()).append(" might, not even caring how much ").append(c.heShe()).append("'s tearing ").append(c.hisHer()).append(" own ").append(bottomDesc).append(" in the process, but ").toString());
                    w.append(t, (new StringBuilder(String.valueOf(c.heShe()))).append(" can't stop one of them from slipping under ").append(c.hisHer()).append(" clothes and ").toString());
                } else
                {
                    if(dignity > 66)
                        w.append(t, (new StringBuilder("Boiling over with rage, ")).append(mainName).append(" furiously glares and kicks at any Thralls who dare film ").append(c.hisHer()).append(" exposed body, but ").toString());
                    else
                    if(dignity > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" grits ").append(c.hisHer()).append(" teeth and squeezes ").append(c.hisHer()).append(" thighs together, but ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" 's bare legs kick wildly as ").append(c.heShe()).append(" tries to escape, flashing the Thralls and their cameras, but ").toString());
                    w.append(t, (new StringBuilder(String.valueOf(c.heShe()))).append(" has no protection whatsoever from the Thrall ").toString());
                }
                if(c.getINJULevel() < 3 || aVirg.booleanValue())
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder("fondling ")).append(c.hisHer()).append(" ").append(organ).append(" until the pleasure makes it hard for ").append(c.himHer()).append(" to remember that ").append(c.heShe()).append("'s supposed to resist.  ").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder("fondling ")).append(c.hisHer()).append(" ").append(organ).append(" until ").append(c.hisHer()).append(" resistance begins to weaken under the growing pleasure.  ").toString());
                    else
                        w.append(t, (new StringBuilder("fondling ")).append(c.hisHer()).append(" ").append(organ).append(" until it becomes difficult for ").append(c.himHer()).append(" to maintain ").append(c.hisHer()).append(" rationality.  ").toString());
                } else
                if(w.tickle().booleanValue())
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder("tickling ")).append(c.himHer()).append(" until ").append(c.hisHer()).append(" squirming laughter leaves ").append(c.hisHer()).append(" ").append(organ).append(" wide open to the others' fondling.  ").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder("tickling ")).append(c.himHer()).append(" to force ").append(c.himHer()).append(" to laugh and let ").append(c.hisHer()).append(" guard down, then pulling ").append(c.hisHer()).append(" legs apart so the others can fondle ").append(c.hisHer()).append(" ").append(organ).append(".  ").toString());
                    else
                        w.append(t, (new StringBuilder("tickling ")).append(c.himHer()).append(" until ").append(c.hisHer()).append(" willpower finally breaks and ").append(c.heShe()).append(" starts thrashing with unwilling laughter, letting ").append(c.hisHer()).append(" guard down enough that the others can concentrate their attention on ").append(c.hisHer()).append(" ").append(organ).append(".  ").toString());
                } else
                if(c.getGender().equals("male"))
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder("cruelly pinching and twisting ")).append(c.hisHer()).append(" penis in a way that leaves ").append(c.himHer()).append(" too dazed and confused with combined pleasure and pain to continue fighting.  ").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder("manhandling ")).append(c.hisHer()).append(" penis, breaking ").append(c.hisHer()).append(" will to fight with combined pleasure and pain.  ").toString());
                    else
                        w.append(t, (new StringBuilder("alternating between stroking ")).append(c.hisHer()).append(" penis and harshly pinching it, using the unexpected sensations to wear down ").append(c.hisHer()).append(" willpower.  ").toString());
                } else
                if(innocence > 66)
                    w.append(t, (new StringBuilder("penetrating ")).append(c.hisHer()).append(" ass, the painful thrusts eventually building into a strange pleasure deep inside ").append(c.himHer()).append(".  ").toString());
                else
                if(innocence > 33)
                    w.append(t, (new StringBuilder("ramming up ")).append(c.hisHer()).append(" ass, breaking ").append(c.hisHer()).append(" will to fight with the combination of pain and pleasure deep inside.  ").toString());
                else
                    w.append(t, (new StringBuilder("repeatedly sliding his cock in and out of ")).append(c.hisHer()).append(" asshole until ").append(c.hisHer()).append(" willpower finally breaks and ").append(c.heShe()).append(" starts groaning at the intense stimulation.  ").toString());
                if(c.getHATELevel() < 3 || vVirg.booleanValue())
                {
                    if(morality > 66)
                        w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" heroic effort to hold back only delays the inevitable for a few moments before ").append(c.heShe()).append(" cums, ").toString());
                    else
                    if(morality > 33)
                        w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" limbs lose all their strength as ").append(c.heShe()).append(" cums, ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" enraged kicking weakens as ").append(c.heShe()).append(" cums, ").toString());
                } else
                if(morality > 66)
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" tries to calm down and remain focused, but then another Thrall's cock pushes into ").append(c.hisHer()).append(" ").append(hole).append(", forcing ").append(c.himHer()).append(" to cum again, ").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder("When another Thrall's cock pushes into ")).append(c.hisHer()).append(" ").append(hole).append(", ").append(c.heShe()).append(" cums hard, ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" bloodthirsty rage has left ").append(c.hisHer()).append(" Sexual Barrier completely broken, allowing another Thrall's cock to plunge into ").append(c.hisHer()).append(" ").append(hole).append(".  The penetration makes ").append(c.himHer()).append(" cum instantly, ").toString());
                if(solo.booleanValue())
                {
                    if(hated.booleanValue())
                        w.append(t, (new StringBuilder("moaning out loud while ")).append(mid.getMainName()).append(" and ").append(low.getMainName()).append(" look on in disappointment.").toString());
                    else
                    if(loved.booleanValue())
                        w.append(t, (new StringBuilder("moaning out loud while ")).append(mid.getMainName()).append(" and ").append(low.getMainName()).append(" blush and look away.").toString());
                    else
                        w.append(t, (new StringBuilder("moaning ")).append(lover.getMainName()).append("'s name.").toString());
                } else
                if(trio.booleanValue())
                {
                    if(loved.booleanValue())
                        w.append(t, (new StringBuilder("turned on even more by ")).append(mid.getMainName()).append("'s and ").append(low.getMainName()).append("'s own orgasmic moans.").toString());
                    else
                    if(hated.booleanValue())
                        w.append(t, (new StringBuilder("and while ")).append(c.heShe()).append(" hates to admit it to ").append(c.himHer()).append("self, the sound of ").append(mid.getMainName()).append("'s and ").append(low.getMainName()).append("'s own moans of orgasm are turning ").append(c.himHer()).append(" on even more.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(c.hisHer()))).append(" orgasmic moans joining those of ").append(mid.getMainName()).append(" and ").append(low.getMainName()).append(".").toString());
                } else
                if(loved.booleanValue() || lover == partner)
                    w.append(t, (new StringBuilder("the sound of ")).append(c.hisHer()).append(" moans pushing ").append(partner.getMainName()).append(" over the edge into ").append(partner.hisHer()).append(" own orgasm as well.").toString());
                else
                    w.append(t, (new StringBuilder("moaning out loud until the Thralls force ")).append(c.hisHer()).append(" mouth against ").append(partner.getMainName()).append("'s.  The two Chosen find themselves muffling each other's orgasmic cries with an unwilling kiss.").toString());
            } else
            if(variant == 2)
            {
                if(c.getEXPOLevel() < 3 || modest.booleanValue())
                {
                    if(dignity > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s expression of cold rage breaks with a ").toString());
                    else
                    if(dignity > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s determined struggles abruptly falter, and ").append(c.heShe()).append(" lets out a ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" struggles without regard for the Thralls grabbing ").append(c.himHer()).append(" by ").append(c.hisHer()).append(" ").append(bottomDesc).append(", letting out a ").toString());
                } else
                if(dignity > 66)
                    w.append(t, (new StringBuilder("Being seen in such a humiliating state has wounded ")).append(mainName).append("'s pride, and it gets even worse when ").append(c.heShe()).append("'s forced to let out a ").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" tries to ignore the cameras filming ").append(c.himHer()).append(" in ").append(c.hisHer()).append(" near-nude state, but the distraction weakens ").append(c.hisHer()).append(" usual strong will, and ").append(c.heShe()).append(" finds ").append(c.himHer()).append("self unable to keep in ").append(c.hisHer()).append(" ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" struggles without regard for how ").append(c.hisHer()).append(" near-nudity is resulting in an erotic show for the cameras, letting out a ").toString());
                if(w.tickle().booleanValue())
                    w.append(t, (new StringBuilder("cry of high-pitched laughter as the Thralls tickle ")).append(c.himHer()).append(".  ").toString());
                else
                if(c.getGender().equals("male"))
                    w.append(t, (new StringBuilder("cry of pain as a Thrall's boot crushes ")).append(c.hisHer()).append(" testicles.  ").toString());
                else
                    w.append(t, (new StringBuilder("breathless groan as the Thrall behind ")).append(c.himHer()).append(" thrusts in and out of ").append(c.hisHer()).append(" ass.  ").toString());
                if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append("'s still confused about how ").append(c.heShe()).append(" can be so weak to something so simple, ").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" body won't obey ").append(c.hisHer()).append(" wishes, ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" feels like ").append(c.heShe()).append(" should have acclimated to this torture by now, but it's still as effective as ever, ").toString());
                } else
                if(innocence > 66)
                    w.append(t, (new StringBuilder("The strange stimulation is impossible to fight, causing ")).append(c.himHer()).append(" to cum again, ").toString());
                else
                if(innocence > 33)
                    w.append(t, (new StringBuilder("Confused by the overwhelming sensations, ")).append(c.hisHer()).append(" body orgasms against ").append(c.hisHer()).append(" will, ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" has been trying to mentally train ").append(c.himHer()).append("self to endure this sort of thing, but ").append(c.heShe()).append(" still reaches climax as quickly as ever, ").toString());
                if(c.getHATELevel() < 3 || vVirg.booleanValue())
                {
                    if(morality > 66)
                        w.append(t, (new StringBuilder("and ")).append(c.heShe()).append(" can't help but wonder whether ").append(c.heShe()).append(" was ever truly strong at all.  ").toString());
                    else
                    if(morality > 33)
                        w.append(t, (new StringBuilder("leaving ")).append(c.himHer()).append(" unusually helpless.  ").toString());
                    else
                        w.append(t, (new StringBuilder("leaving ")).append(c.himHer()).append(" unable to even coherently word ").append(c.hisHer()).append(" threats at the surrounding Thralls.  ").toString());
                } else
                if(morality > 66)
                    w.append(t, (new StringBuilder("and ")).append(c.hisHer()).append(" sense of pride and heroism is further broken down as the other Thralls take turns violating ").append(c.hisHer()).append(" ").append(hole).append(".  ").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder("leaving ")).append(c.himHer()).append(" completely helpless as the other Thralls take turns fucking ").append(c.hisHer()).append(" ").append(hole).append(".  ").toString());
                else
                    w.append(t, (new StringBuilder("leaving ")).append(c.himHer()).append(" helpless to do anything but scream in rage as another Thrall penetrates ").append(c.hisHer()).append(" ").append(hole).append(".  ").toString());
                if(solo.booleanValue())
                {
                    if(loved.booleanValue())
                        w.append(t, (new StringBuilder(String.valueOf(mid.getMainName()))).append(" and ").append(low.getMainName()).append(" are horrified and heartbroken at seeing their stronger friend abused so savagely.").toString());
                    else
                    if(hated.booleanValue())
                        w.append(t, (new StringBuilder("Despite their rivalry, ")).append(mid.getMainName()).append(" and ").append(low.getMainName()).append(" are both horrified at the sight of their stronger teammate being so savagely abused.").toString());
                    else
                        w.append(t, (new StringBuilder("Despite their very different opinions of ")).append(c.himHer()).append(", ").append(mid.getMainName()).append(" and ").append(low.getMainName()).append(" are both horrified by the display.").toString());
                } else
                if(trio.booleanValue())
                {
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" louder voice rises above ").append(mid.getMainName()).append("'s and ").append(low.getMainName()).append("'s own ").toString());
                    if(w.tickle().booleanValue())
                        w.append(t, "unwilling laughter.");
                    else
                        w.append(t, "moans of pain.");
                } else
                if(loved.booleanValue() || lover == partner)
                {
                    w.append(t, (new StringBuilder("For ")).append(partner.getMainName()).append(", who had been relying on ").append(mainName).append("'s strength, the sight saps ").append(c.hisHer()).append(" drive to resist").toString());
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder(", causing ")).append(c.himHer()).append(" to let out ").append(c.hisHer()).append(" own unwilling laughter out as well.").toString());
                    else
                        w.append(t, (new StringBuilder(", causing ")).append(c.himHer()).append(" to sob softly in despair.").toString());
                } else
                {
                    w.append(t, (new StringBuilder("The Thralls taunt ")).append(mainName).append(" with the fact that ").append(c.heShe()).append("'s faring no better than ").append(partner.getMainName()).append(", who's ").toString());
                    if(w.tickle().booleanValue())
                        w.append(t, "also caught up in unwilling laughter.");
                    else
                        w.append(t, "squirming uselessly and moaning in pain as well.");
                }
            } else
            if(variant == 3)
            {
                if(c.getINJULevel() < 3 || aVirg.booleanValue())
                {
                    if(dignity > 66)
                        w.append(t, (new StringBuilder("Bringing their cameras close, the Thralls demand that ")).append(mainName).append(" apologize for being such a bitch").toString());
                    else
                    if(dignity > 33)
                        w.append(t, (new StringBuilder("The Thralls demand that ")).append(mainName).append(" proclaim ").append(c.himHer()).append("self a complete weakling in front of the cameras").toString());
                    else
                        w.append(t, (new StringBuilder("The Thralls demand that ")).append(mainName).append(" suck their cocks in order to provide a sexy show for the cameras").toString());
                } else
                if(w.tickle().booleanValue())
                {
                    if(dignity > 66)
                        w.append(t, (new StringBuilder("The filming Thralls capture a close-up of ")).append(mainName).append("'s laughing, tear-streaked face.  They tell ").append(c.himHer()).append(" that they won't let up on the tickling unless ").append(c.heShe()).append(" apologizes for being such a bitch").toString());
                    else
                    if(dignity > 33)
                        w.append(t, (new StringBuilder("The Thralls demand that ")).append(mainName).append(" acknowledge in front of the cameras that ").append(c.heShe()).append(" must be a true weakling to be so easily defeated by tickling").toString());
                    else
                        w.append(t, (new StringBuilder("Bringing their cameras close, the Thralls try to get ")).append(mainName).append(" to suck their cocks while ").append(c.hisHer()).append(" mouth is forced open with laughter by the tickling").toString());
                } else
                if(c.getGender().equals("male"))
                {
                    if(dignity > 66)
                        w.append(t, (new StringBuilder("The filming Thralls capture a close-up of ")).append(mainName).append("'s grimacing, tear-streaked face.  They tell ").append(c.himHer()).append(" that they won't let up on ").append(c.hisHer()).append(" balls unless ").append(c.heShe()).append(" apologizes for tempting other guys with ").append(c.hisHer()).append(" body").toString());
                    else
                    if(dignity > 33)
                        w.append(t, (new StringBuilder("The Thralls demand that ")).append(mainName).append(" acknowledge in front of the cameras that ").append(c.heShe()).append(" must be a true weakling to submit so easily to a little bit of ballbusting").toString());
                    else
                        w.append(t, (new StringBuilder("Bringing their cameras close, the Thralls try to get ")).append(mainName).append(" to suck their cocks while ").append(c.heShe()).append("'s distracted by the pain of ").append(c.hisHer()).append(" bruised testicles").toString());
                } else
                if(dignity > 66)
                    w.append(t, (new StringBuilder("The filming Thralls capture a close-up of ")).append(mainName).append("'s cross-eyed expression as ").append(c.heShe()).append("'s fucked up the ass.  They tell ").append(c.himHer()).append(" that they won't let up on ").append(c.himHer()).append(" unless ").append(c.heShe()).append(" apologizes for being such a bitch").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder("The Thralls demand that ")).append(mainName).append(" acknowledge in front of the cameras that ").append(c.heShe()).append("'s a pervert who's enjoying being fucked up the ass").toString());
                else
                    w.append(t, (new StringBuilder("Bringing their cameras close, the Thralls try to get ")).append(mainName).append(" to suck their cocks while ").append(c.heShe()).append("'s distracted by the cock up ").append(c.hisHer()).append(" ass").toString());
                if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder(", but when ")).append(c.heShe()).append(" doesn't stop ").append(c.hisHer()).append(" wild attempts to punch and bite them, they ").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder(", but when ")).append(c.heShe()).append(" just clenches ").append(c.hisHer()).append(" teeth and glares at them, they ").toString());
                    else
                        w.append(t, (new StringBuilder(", but when ")).append(c.heShe()).append(" coldly refuses, they ").toString());
                } else
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder(", but ")).append(c.heShe()).append("'s too busy cumming from their fingers on ").append(c.hisHer()).append(" ").append(organ).append(" to cooperate.  ").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder(", but ")).append(c.heShe()).append(" refuses to respond, even as their fingers tease an orgasm from ").append(c.hisHer()).append(" ").append(organ).append(".  ").toString());
                    else
                        w.append(t, (new StringBuilder(", but even though ")).append(c.heShe()).append("'s constantly cumming from their fingers on ").append(c.hisHer()).append(" ").append(organ).append(", ").append(c.heShe()).append(" still manages to stammer out a flat refusal.  ").toString());
                    w.append(t, "They ");
                }
                if(c.getHATELevel() < 3 || vVirg.booleanValue())
                {
                    if(morality > 66)
                        w.append(t, (new StringBuilder("force ")).append(c.himHer()).append(" to comply by threatening the others.  ").toString());
                    else
                    if(morality > 33)
                        w.append(t, "escalate the punishment even further.  ");
                    else
                        w.append(t, (new StringBuilder("punish ")).append(c.himHer()).append(" by ejaculating all over ").append(c.hisHer()).append(" face and chest - from a safe distance, of course.  ").toString());
                } else
                if(morality > 66)
                    w.append(t, (new StringBuilder("punish ")).append(c.himHer()).append(" by violating ").append(c.hisHer()).append(" ").append(hole).append(", telling ").append(c.himHer()).append(" to move ").append(c.hisHer()).append(" hips for them unless ").append(c.heShe()).append(" wants the others to suffer even worse treatment.  ").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder("punish ")).append(c.himHer()).append(" by fucking ").append(c.hisHer()).append(" ").append(hole).append(" at the same time.  ").toString());
                else
                    w.append(t, (new StringBuilder("punish ")).append(c.himHer()).append(" by violating ").append(c.hisHer()).append(" ").append(hole).append(" at the same time, pinning ").append(c.himHer()).append(" down so ").append(c.heShe()).append(" can't fight back.  ").toString());
                if(solo.booleanValue())
                {
                    w.append(t, (new StringBuilder("The broadcasted display of weakness is not only a shock to ")).append(c.hisHer()).append(" fans, ").toString());
                    if(loved.booleanValue())
                        w.append(t, (new StringBuilder("but a despair-inducing sight for ")).append(mid.getMainName()).append(" and ").append(low.getMainName()).append(" as well.").toString());
                    else
                    if(hated.booleanValue())
                    {
                        w.append(t, (new StringBuilder("but to ")).append(c.hisHer()).append(" watching rivals as well.").toString());
                    } else
                    {
                        w.append(t, "but a demoralizing sight for ");
                        if(lover == mid)
                            w.append(t, (new StringBuilder(String.valueOf(mid.getMainName()))).append(" and even ").append(low.getMainName()).append(" as well.").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(low.getMainName()))).append(" and even ").append(mid.getMainName()).append(" as well.").toString());
                    }
                } else
                if(trio.booleanValue())
                {
                    if(loved.booleanValue())
                        w.append(t, (new StringBuilder("Even though it means the Thralls aren't paying so much attention to ")).append(mid.getMainName()).append(" and ").append(low.getMainName()).append(", they both find it somehow even worse to watch ").append(mainName).append(" suffer.").toString());
                    else
                    if(hated.booleanValue())
                        w.append(t, (new StringBuilder(String.valueOf(mid.getMainName()))).append(" and ").append(low.getMainName()).append(" can't help but feel relieved on some level that ").append(mainName).append(" is bearing the brunt of the Thralls' attention for the moment.").toString());
                    else
                        w.append(t, (new StringBuilder("While it means that the Thralls aren't paying so much attention to the other Chosen, ")).append(lover.getMainName()).append(" still finds this painful to watch.").toString());
                } else
                if(loved.booleanValue() || lover == partner)
                    w.append(t, (new StringBuilder("While it means that the Thralls aren't paying so much attention to ")).append(partner.getMainName()).append(", ").append(partner.heShe()).append(" still finds it painful to watch.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(partner.getMainName()))).append(" can't help but be relieved that the Thralls are distracted from torturing ").append(partner.himHer()).append(".").toString());
            }
        } else
        if(confidence > 33)
        {
            if(variant == 0)
            {
                if(c.getINJULevel() < 3 || aVirg.booleanValue())
                {
                    if(morality > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" closes ").append(c.hisHer()).append(" eyes and tries to focus as the Thralls ").toString());
                    else
                    if(morality > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is unable to contain a yelp of surprise as the Thralls ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" glares and desperately clenches ").append(c.hisHer()).append(" teeth as the Thralls ").toString());
                } else
                if(w.tickle().booleanValue())
                {
                    if(morality > 66)
                        w.append(t, (new StringBuilder("Despite ")).append(c.hisHer()).append(" heroic effort, ").append(mainName).append(" still ends up laughing helplessly as the Thralls tickle ").append(c.himHer()).append(" and ").toString());
                    else
                    if(morality > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" has no choice but to laugh openly as the Thralls tickle ").append(c.himHer()).append(" and ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" completely fails to intimidate the Thralls, laughter leaking out as they tickle ").append(c.himHer()).append(" and ").toString());
                } else
                if(c.getGender().equals("male"))
                {
                    if(morality > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s heroic demeanor cracks as the Thralls stomp on ").append(c.hisHer()).append(" penis and ").toString());
                    else
                    if(morality > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is unable to contain ").append(c.hisHer()).append(" cries of pain as the Thralls stomp on ").append(c.hisHer()).append(" penis and ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s attempt to intimidate the Thralls falls apart with a pathetic cry as they stomp on ").append(c.hisHer()).append(" penis and ").toString());
                } else
                if(morality > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s heroic demeanor cracks as one Thrall thrusts into ").append(c.hisHer()).append(" ass while the others ").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is unable to contain ").append(c.hisHer()).append(" cries of pain as one of the Thralls thrusts into ").append(c.hisHer()).append(" ass.  The others ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s attempt to intimidate the Thralls falls apart with a pathetic cry as one of them thrusts into ").append(c.hisHer()).append(" ass.  The others ").toString());
                if(c.getEXPOLevel() < 3 || modest.booleanValue())
                {
                    if(dignity > 66)
                        w.append(t, (new StringBuilder("start trying to strip ")).append(c.himHer()).append(", ignoring ").append(c.hisHer()).append(" indignant protests.  ").toString());
                    else
                    if(dignity > 33)
                        w.append(t, (new StringBuilder("force their hands under ")).append(c.hisHer()).append(" ").append(bottomDesc).append(" and try to strip ").append(c.himHer()).append(".  ").toString());
                    else
                        w.append(t, (new StringBuilder("grab ")).append(c.himHer()).append(" by the ").append(bottomDesc).append(", and ").append(c.hisHer()).append(" flailing efforts to escape accomplish nothing but tearing ").append(c.hisHer()).append(" clothes.  ").toString());
                } else
                if(dignity > 66)
                    w.append(t, (new StringBuilder("pry apart ")).append(c.hisHer()).append(" tightly-clenched thighs to more easily film ").append(c.himHer()).append(" down there.  ").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder("force ")).append(c.hisHer()).append(" bare legs apart and bring their cameras in close.  ").toString());
                else
                    w.append(t, (new StringBuilder("grab ")).append(c.hisHer()).append(" kicking ankles and pull them apart in order to more easily film ").append(c.himHer()).append(" down there.  ").toString());
                if(solo.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append("'s so distracted that ").append(c.heShe()).append(" can't even defend ").append(c.himHer()).append("self when one of ").append(c.hisHer()).append(" attackers mounts ").append(c.himHer()).append(" and thrusts into ").append(c.hisHer()).append(" ").append(hole).append(".  Being taken right in front of ").append(high.getMainName()).append(" and ").append(low.getMainName()).append(", ").toString());
                else
                if(trio.booleanValue())
                {
                    w.append(t, "The three Chosen are all laid on their backs and fucked side-by-side.  ");
                    if(loved.booleanValue())
                    {
                        w.append(t, (new StringBuilder(String.valueOf(high.getMainName()))).append(" grasps one of ").append(mid.getMainName()).append("'s hands and ").append(low.getMainName()).append(" takes the other as they endure their punishment together.  Grip tightening with every thrust against ").append(c.hisHer()).append(" ").toString());
                        if(c.getGender().equals("male"))
                            w.append(t, "bowels, ");
                        else
                            w.append(t, "cervix, ");
                    } else
                    if(hated.booleanValue())
                        w.append(t, (new StringBuilder("Staring straight ahead and refusing to meet ")).append(high.getMainName()).append("'s and ").append(low.getMainName()).append("'s eyes, ").toString());
                    else
                    if(lover == high)
                        w.append(t, (new StringBuilder("Doing ")).append(c.hisHer()).append(" best to ignore ").append(low.getMainName()).append("'s whimpering and take strength from ").append(high.getMainName()).append("'s stubborn defiance, ").toString());
                    else
                        w.append(t, (new StringBuilder("Taking ")).append(lover.getMainName()).append(" by the hand and murmuring reassuring words even as ").append(high.getMainName()).append(" grunts and groans next to them, ").toString());
                } else
                {
                    w.append(t, (new StringBuilder(String.valueOf(partner.getMainName()))).append(" is forced down atop ").append(c.himHer()).append(" and they're both fucked at the same time.  ").toString());
                    if(lover == partner)
                        w.append(t, (new StringBuilder("Sharing a passionate kiss with ")).append(c.hisHer()).append(" friend, ").toString());
                    else
                        w.append(t, (new StringBuilder("Trying in vain to keep ")).append(partner.getMainName()).append("'s body from rubbing against ").append(c.himHer()).append(", ").toString());
                }
                if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" squeals in mingled disgust and arousal as ").append(c.heShe()).append(" feels the Thrall cum inside.").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" tells ").append(c.himHer()).append("self that ").append(c.heShe()).append(" won't lose to the pleasure.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" ignores the building pleasure as best ").append(c.heShe()).append(" can.").toString());
                } else
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is soon moaning openly in orgasm, having forgot completely about escape.  ").append(c.HisHer()).append(" whole body spasms").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" goes bright red with shame as ").append(c.heShe()).append(" feels ").append(c.himHer()).append("self start to climax").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" knows there's no way ").append(c.heShe()).append("'ll be able to stop the coming climax.  ").append(c.HisHer()).append(" body soon gives in").toString());
                    if(c.getGender().equals("female"))
                        w.append(t, ", squeezing down on the invading cock.");
                    else
                        w.append(t, (new StringBuilder(", cumming all over ")).append(c.hisHer()).append(" own chest.").toString());
                }
            } else
            if(variant == 1)
            {
                if(c.getINJULevel() < 3 || aVirg.booleanValue())
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is dazed and confused by the pleasure inflicted by the Thralls stroking ").append(c.hisHer()).append(" ").append(organ).toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder("The Thralls pin ")).append(mainName).append(" down and firmly stroke ").append(c.hisHer()).append(" ").append(organ).toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" tries to focus, but the pleasure of the Thralls stroking ").append(c.hisHer()).append(" ").append(organ).append(" is too strong").toString());
                } else
                if(w.tickle().booleanValue())
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder("The combination of the Thralls tickling ")).append(c.himHer()).append(" and stroking ").append(c.hisHer()).append(" ").append(organ).append(" has left ").append(mainName).append(" barely aware of ").append(c.hisHer()).append(" surroundings").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is unable resist the Thralls tickling ").append(c.himHer()).append(" and stroking ").append(c.hisHer()).append(" ").append(organ).toString());
                    else
                        w.append(t, (new StringBuilder("The laughter brought on by the Thralls' tickling has left ")).append(mainName).append(" lightheaded and unable to focus, vulnerable to the hands stroking ").append(c.hisHer()).append(" ").append(organ).toString());
                } else
                if(c.getGender().equals("male"))
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder("The combination of pain and pleasure inflicted by the Thralls tormenting ")).append(c.hisHer()).append(" penis has left ").append(mainName).append(" barely aware of ").append(c.hisHer()).append(" surroundings").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is unable to resist the Thralls alternating between stroking and stomping on ").append(c.hisHer()).append(" penis").toString());
                    else
                        w.append(t, (new StringBuilder("The repeated abuse to ")).append(c.hisHer()).append(" bruised penis has left ").append(mainName).append(" lightheaded and unable to focus, especially as the Thralls' hands shift to inflicting pleasure rather than pain").toString());
                } else
                if(innocence > 66)
                    w.append(t, (new StringBuilder("The combination of the Thrall's cock up ")).append(c.hisHer()).append(" ass and the others' hands stroking ").append(c.hisHer()).append(" ").append(organ).append(" has left ").append(mainName).append(" barely aware of ").append(c.hisHer()).append(" surroundings").toString());
                else
                if(innocence > 33)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is unable to resist the Thrall's cock thrusting up ").append(c.hisHer()).append(" ass as the others stroke ").append(c.hisHer()).append(" ").append(organ).toString());
                else
                    w.append(t, (new StringBuilder("The repeated thrusting of the Thrall's cock up ")).append(c.hisHer()).append(" ass has left ").append(mainName).append(" lightheaded and unable to focus, vulnerable to the hands stroking ").append(c.hisHer()).append(" ").append(organ).toString());
                if(c.getEXPOLevel() > 3 || modest.booleanValue())
                {
                    if(dignity > 66)
                        w.append(t, (new StringBuilder(", but ")).append(c.heShe()).append(" still reflexively tries to pull ").append(c.hisHer()).append(" ").append(bottomDesc).append(" back into place in order to hide ").append(c.hisHer()).append(" ").toString());
                    else
                    if(dignity > 33)
                        w.append(t, (new StringBuilder(", and with ")).append(c.hisHer()).append(" ").append(bottomDesc).append(" pulled aside, there's no hiding ").append(c.hisHer()).append(" ").toString());
                    else
                        w.append(t, (new StringBuilder(", and with ")).append(c.hisHer()).append(" ").append(bottomDesc).append(" pulled aside, ").append(c.hisHer()).append(" reflexive kicking causes ").append(c.himHer()).append(" to completely expose ").append(c.hisHer()).append(" ").toString());
                } else
                if(dignity > 66)
                    w.append(t, (new StringBuilder(", especially with the overwhelming embarrassment at the Thralls filming ")).append(c.hisHer()).append(" ").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder(", and in ")).append(c.hisHer()).append(" stripped state, everyone can see ").append(c.hisHer()).append(" ").toString());
                else
                    w.append(t, (new StringBuilder(", and ")).append(c.heShe()).append(" can't even spare a thought to hiding ").append(c.hisHer()).append(" ").toString());
                if(c.getGender().equals("female"))
                    w.append(t, "dripping wet slit.  ");
                else
                    w.append(t, "pre-cum dripping out the tip.  ");
                if(solo.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(high.getMainName()))).append(" and ").append(low.getMainName()).append(" both find it increasingly hard not to get turned on as they watch more Thralls step forward to ").toString());
                else
                if(trio.booleanValue())
                {
                    if(loved.booleanValue())
                        w.append(t, (new StringBuilder("Using ")).append(high.getMainName()).append("'s and ").append(low.getMainName()).append("'s feelings against them, ").toString());
                    else
                    if(hated.booleanValue())
                        w.append(t, (new StringBuilder("Savoring how much ")).append(high.getMainName()).append(" and ").append(low.getMainName()).append(" hate this, ").toString());
                    else
                    if(lover == high)
                        w.append(t, (new StringBuilder("Taking advantage of ")).append(high.getMainName()).append("'s feelings and ").append(low.getMainName()).append("'s mental weakness, ").toString());
                    else
                        w.append(t, (new StringBuilder("Much to ")).append(high.getMainName()).append("'s anger and ").append(low.getMainName()).append("'s despair, ").toString());
                    if(high.getGender().equals("female") && low.getGender().equals("female"))
                        w.append(t, (new StringBuilder("the Thralls force them against ")).append(mainName).append("'s thighs, grinding them together until they gasp and moan in climax.  Meanwhile, more Thralls step forward to ").toString());
                    else
                    if(high.getGender().equals("female"))
                        w.append(t, (new StringBuilder("the Thralls make ")).append(low.getMainName()).append(" join the Thralls attacking ").append(mainName).append(".  ").append(high.getMainName()).append(" is forced to climax as ").append(high.heShe()).append(" watches, while the Thralls (with ").append(low.getMainName()).append(") start to ").toString());
                    else
                    if(low.getGender().equals("female"))
                        w.append(t, (new StringBuilder("the Thralls make ")).append(high.getMainName()).append(" join the Thralls attacking ").append(mainName).append(".  ").append(low.getMainName()).append(" is forced to climax as ").append(low.heShe()).append(" watches, while the Thralls (with ").append(high.getMainName()).append(") start to ").toString());
                    else
                        w.append(t, "the Thralls force them to ");
                } else
                if(partner.getGender().equals("female"))
                {
                    w.append(t, (new StringBuilder("The Thralls force ")).append(mainName).append(" and ").append(partner.getMainName()).append(" to grind their bodies together until they're both close to climax again, ").toString());
                    if(loved.booleanValue() || lover == partner)
                        w.append(t, "using their mutual affection to make it more difficult for them to resist");
                    else
                        w.append(t, "enjoying how much they obviously hate it");
                    w.append(t, ", then the Thralls themselves join in and ");
                } else
                if(loved.booleanValue() || lover == partner)
                    w.append(t, (new StringBuilder("Forcing ")).append(partner.getMainName()).append(" to join the abuse of ").append(partner.hisHer()).append(" friend, the Thralls ").toString());
                else
                    w.append(t, (new StringBuilder("The Thralls force ")).append(partner.getMainName()).append(" to participate in the punishment, and together they start to ").toString());
                if(c.getHATELevel() < 3 || vVirg.booleanValue())
                {
                    w.append(t, (new StringBuilder("push their cocks into ")).append(mainName).append("'s mouth, ").append(c.hisHer()).append(" lips stretching to accommodate two at once.  ").toString());
                    if(morality > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" reflexively sucks as ").append(c.heShe()).append(" climaxes, and ").append(c.hisHer()).append(" orgasmic moans drive them to cum down ").append(c.hisHer()).append(" throat.").toString());
                    else
                    if(morality > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" reaches ").append(c.hisHer()).append(" own orgasm as ").append(c.heShe()).append(" feels their cum shoot down ").append(c.hisHer()).append(" throat.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s indignant anger doesn't stop ").append(c.himHer()).append(" from climaxing under the continued stimulation, and ").append(c.heShe()).append(" chokes on the cum being shot down ").append(c.hisHer()).append(" throat.").toString());
                } else
                {
                    w.append(t, (new StringBuilder("fuck ")).append(mainName).append("'s ").append(hole).append(" together, stretching it wide enough to hold two cocks at once.  ").toString());
                    if(morality > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s sense of violation doesn't stop ").append(c.himHer()).append(" from climaxing under the stimulation, and the feeling of the cocks shooting their loads inside ").append(c.himHer()).append(" only enhances the shameful pleasure.").toString());
                    else
                    if(morality > 33)
                        w.append(t, (new StringBuilder("The feeling of both of them shooting their loads deep inside ")).append(c.himHer()).append(" drives ").append(mainName).append(" to cum as well.").toString());
                    else
                        w.append(t, (new StringBuilder("The stimulation is too much to bear, and ")).append(mainName).append(" wraps ").append(c.hisHer()).append(" legs around them as they reach simultaneous orgasm together.").toString());
                }
            } else
            if(variant == 2)
            {
                if(w.tickle().booleanValue())
                    w.append(t, (new StringBuilder("The Thralls aren't content with just tickling ")).append(mainName).append(" and ").toString());
                else
                if(c.getGender().equals("male"))
                    w.append(t, (new StringBuilder("The Thralls aren't content with just torturing ")).append(mainName).append("'s penis and ").toString());
                else
                    w.append(t, (new StringBuilder("Only one Thrall at a time can fit himself into ")).append(mainName).append("'s asshole, and the others aren't content with just ").toString());
                if(c.getEXPOLevel() < 3 || modest.booleanValue())
                {
                    if(dignity > 66)
                        w.append(t, (new StringBuilder("pulling off ")).append(c.hisHer()).append(" clothes, even though ").append(c.hisHer()).append(" clear embarrassment is somewhat satisfying.  ").toString());
                    else
                    if(dignity > 33)
                        w.append(t, (new StringBuilder("tearing ")).append(c.hisHer()).append(" clothes off.  ").toString());
                    else
                        w.append(t, (new StringBuilder("stripping ")).append(c.himHer()).append(", especially with how little embarrassment ").append(c.heShe()).append("'s showing.  ").toString());
                } else
                if(dignity > 66)
                    w.append(t, (new StringBuilder("enjoying ")).append(c.hisHer()).append(" obvious humiliation at being filmed in such an exposed state.  ").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder("filming ")).append(c.hisHer()).append(" exposed body.  ").toString());
                else
                    w.append(t, (new StringBuilder("filming the places exposed by ")).append(c.hisHer()).append(" torn clothes and splayed out limbs as ").append(c.heShe()).append(" struggles wildly to escape.  ").toString());
                if(solo.booleanValue())
                {
                    if(loved.booleanValue())
                        w.append(t, (new StringBuilder("Ignoring ")).append(high.getMainName()).append("'s demands that they stop, they pull out their cocks, and ").append(low.getMainName()).append(" winces and averts ").append(low.hisHer()).append(" eyes.  ").toString());
                    else
                    if(hated.booleanValue())
                        w.append(t, (new StringBuilder(String.valueOf(high.getMainName()))).append(" and ").append(low.getMainName()).append(" watch without much of a reaction as the Thralls around ").append(mainName).append(" pull out their cocks.  ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(lover.getMainName()))).append(" raises ").append(c.hisHer()).append(" voice, trying to get the Thralls to stop, but they ignore ").append(c.himHer()).append(" and pull out their cocks.  ").toString());
                    w.append(t, "They close in and ");
                } else
                if(trio.booleanValue())
                {
                    w.append(t, "The three Chosen are forced to kneel facing toward each other with their butts all facing outward toward the crowd");
                    if(loved.booleanValue())
                        w.append(t, ", whispering reassurances to each other ");
                    else
                    if(hated.booleanValue())
                        w.append(t, ", glaring at each other ");
                    else
                        w.append(t, ", their breath hot on each other's faces ");
                    if(high.getGender().equals("male") && low.getGender().equals("male"))
                        w.append(t, (new StringBuilder("as the Thralls grab ")).append(high.getMainName()).append("'s and ").append(low.getMainName()).append("'s bruised penises to make them hold still while their ass cheeks are spread wide open.  ").toString());
                    else
                    if(high.getGender().equals("male"))
                        w.append(t, (new StringBuilder("as ")).append(low.getMainName()).append(" whimpers with every thrust into ").append(c.hisHer()).append(" ass.  ").append(high.getMainName()).append("'s bruised penis is grabbed firmly to make ").append(c.himHer()).append(" stop struggling as they spread ").append(c.hisHer()).append(" ass cheeks wide open.  ").toString());
                    else
                    if(low.getGender().equals("male"))
                        w.append(t, (new StringBuilder("as ")).append(high.getMainName()).append(" grunts and groans with every thrust into ").append(c.hisHer()).append(" ass.  ").append(low.getMainName()).append("'s bruised penis is grabbed firmly, leaving ").append(c.himHer()).append(" unable to do anything but whimper in pain as they spread ").append(c.hisHer()).append(" ass cheeks wide open.  ").toString());
                    else
                        w.append(t, (new StringBuilder("as ")).append(high.getMainName()).append(" and ").append(low.getMainName()).append(" endure their own anal rape.  ").toString());
                    w.append(t, (new StringBuilder("The Thralls closest to ")).append(mainName).append(" pull out their cocks and ").toString());
                } else
                {
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append("'s forced to lay atop ").append(partner.getMainName()).toString());
                    if(loved.booleanValue() || partner == lover)
                        w.append(t, ", who tries to say something supportive but stops with a gasp");
                    else
                        w.append(t, (new StringBuilder(", who winces and frowns at ")).append(c.himHer()).toString());
                    if(partner.getGender().equals("male"))
                    {
                        w.append(t, (new StringBuilder(" as the position puts pressure on ")).append(partner.hisHer()).append(" ").toString());
                        if(c.getGender().equals("male"))
                            w.append(t, "own ");
                        w.append(t, "bruised penis.  ");
                    } else
                    {
                        w.append(t, (new StringBuilder(" as ")).append(c.heShe()).append(" endures the Thrall's cock thrusting in and out of ").append(c.hisHer()).append(" ").toString());
                        if(!c.getGender().equals("male"))
                            w.append(t, "own ");
                        w.append(t, "ass.  ");
                    }
                    w.append(t, (new StringBuilder("Crowding around the both of them, the Thralls on ")).append(mainName).append("'s side pull out their cocks and ").toString());
                }
                if(c.getHATELevel() < 3 || vVirg.booleanValue())
                {
                    if(morality > 66)
                        w.append(t, (new StringBuilder("grind against ")).append(c.hisHer()).append(" whole body, taunting ").append(c.himHer()).append(" all the while in an effort to awaken ").append(c.hisHer()).append(" hatred.  ").toString());
                    else
                    if(morality > 33)
                        w.append(t, (new StringBuilder("grind against ")).append(c.hisHer()).append(" whole body.  ").toString());
                    else
                        w.append(t, (new StringBuilder("grind against ")).append(c.hisHer()).append(" whole body, careful to avoid ").append(c.hisHer()).append(" bared teeth.  ").toString());
                    if(c.getHATELevel() < 3)
                    {
                        w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" Sexual Barrier prevents them from penetrating ").append(c.himHer()).append(", but the way their shafts rub against ").toString());
                    } else
                    {
                        if(c.getGender().equals("male"))
                            w.append(t, (new StringBuilder("They can't agree on who gets to teach ")).append(c.himHer()).append(" the pleasure of getting fucked like a girl").toString());
                        else
                            w.append(t, (new StringBuilder("They can't agree on who gets to deflower ")).append(c.himHer()).toString());
                        w.append(t, ", but the way their jostling shafts rub against ");
                    }
                    if(c.getGender().equals("female"))
                        w.append(t, (new StringBuilder(String.valueOf(c.hisHer()))).append(" clit ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(c.hisHer()))).append(" own ").toString());
                } else
                if(morality > 66)
                    w.append(t, (new StringBuilder("take turns penetrating ")).append(c.hisHer()).append(" ").append(hole).append(".  The sense of violation, as terrible as it feels, also ").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder("take turns fucking ")).append(c.hisHer()).append(" ").append(hole).append(".  The sensation of them cumming inside ").toString());
                else
                    w.append(t, (new StringBuilder("take turns riding ")).append(c.himHer()).append(", penetrating ").append(c.hisHer()).append(" ").append(hole).append(" as ").append(c.heShe()).append(" angrily tries to buck them off.  It's infuriating, but it also ").toString());
                if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder("gives ")).append(c.himHer()).append(" a sense of butterflies in ").append(c.hisHer()).append(" belly.").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder("starts to turn ")).append(c.himHer()).append(" on.").toString());
                    else
                        w.append(t, (new StringBuilder("causes a degree of sexual arousal ")).append(c.heShe()).append(" can't deny.").toString());
                } else
                if(innocence > 66)
                    w.append(t, (new StringBuilder("makes ")).append(c.hisHer()).append(" mind go pure white with pulses of pleasure.").toString());
                else
                if(innocence > 33)
                    w.append(t, (new StringBuilder("doesn't take long to make ")).append(c.himHer()).append(" cum.").toString());
                else
                    w.append(t, "causes pleasure that quickly builds to the point of orgasm.");
            } else
            if(variant == 3)
            {
                if(c.getINJULevel() < 3 || aVirg.booleanValue())
                {
                    if(dignity > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" struggles not to show any reaction as the Thralls ").toString());
                    else
                    if(dignity > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" tells the Thralls to stop, attempting to put authority into ").append(c.hisHer()).append(" voice, but they continue to ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" shouts for the Thralls to stop as they ").toString());
                } else
                if(w.tickle().booleanValue())
                {
                    if(dignity > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" struggles vainly to keep a straight face and suppress ").append(c.hisHer()).append(" laughter as the Thralls tickle ").append(c.hisHer()).append(" bare feet and ").toString());
                    else
                    if(dignity > 33)
                        w.append(t, (new StringBuilder("Even as ")).append(c.heShe()).append(" laughs uncontrollably, ").append(mainName).append(" tries to tell the Thralls to leave ").append(c.himHer()).append(" alone, but they continue to tickle ").append(c.hisHer()).append(" bare feet and ").toString());
                    else
                        w.append(t, (new StringBuilder("Laughing uncontrollably as the Thralls tickle ")).append(c.hisHer()).append(" bare feet, ").append(mainName).append(" barely manages to tell them to stop, but they only torment ").append(c.himHer()).append(" even more, starting to ").toString());
                } else
                if(c.getGender().equals("male"))
                {
                    if(dignity > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" struggles vainly to suppress ").append(c.hisHer()).append(" moans of pain as the Thralls crush ").append(c.hisHer()).append(" testicles and ").toString());
                    else
                    if(dignity > 33)
                        w.append(t, (new StringBuilder("Even as ")).append(c.heShe()).append(" moans in pain, ").append(mainName).append(" tries to tell the Thralls to leave ").append(c.himHer()).append(" alone, but they continue to crush ").append(c.hisHer()).append(" testicles as they ").toString());
                    else
                        w.append(t, (new StringBuilder("Squealing in pain as the Thralls crush ")).append(c.hisHer()).append(" testicles, ").append(mainName).append(" tries to tell them to stop, but they only torment ").append(c.himHer()).append(" even more, starting to ").toString());
                } else
                if(dignity > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" struggles vainly to suppress ").append(c.hisHer()).append(" little squeaks and moans as one of the Thralls fucks ").append(c.himHer()).append(" up the ass and the others ").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder("Even as ")).append(c.heShe()).append(" moans in mingled pain and pleasure at being fucked up the ass, ").append(mainName).append(" tries to tell the Thralls to leave ").append(c.himHer()).append(" alone, but they only move on to ").toString());
                else
                    w.append(t, (new StringBuilder("Squealing as the Thralls fuck ")).append(c.himHer()).append(" up the ass, ").append(mainName).append(" tries to tell them to stop, but they only torment ").append(c.himHer()).append(" even more, starting to ").toString());
                if(c.getHATELevel() < 3 || vVirg.booleanValue())
                {
                    if(morality > 66)
                        w.append(t, (new StringBuilder("force ")).append(c.himHer()).append(" to suck their cocks as they tease ").append(c.hisHer()).append(" nipples and ").append(organ).append(".  ").toString());
                    else
                    if(morality > 33)
                        w.append(t, (new StringBuilder("push vibrating rotors against ")).append(c.hisHer()).append(" nipples and ").append(organ).append(".  ").toString());
                    else
                        w.append(t, (new StringBuilder("tease ")).append(c.hisHer()).append(" nipples and ").append(organ).append(" with no regard for ").append(c.hisHer()).append(" obvious anger.  ").toString());
                } else
                if(morality > 66)
                    w.append(t, (new StringBuilder("force their cocks into ")).append(c.hisHer()).append(" mouth and ").append(hole).append(" as well.  ").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder("penetrate ")).append(c.hisHer()).append(" ").append(hole).append(" and push vibrating rotors against ").append(c.hisHer()).append(" nipples and ").append(organ).append(" at the same time.  ").toString());
                else
                    w.append(t, (new StringBuilder("push their cocks into ")).append(c.hisHer()).append(" ").append(hole).append(", ignoring ").append(c.hisHer()).append(" demands that they not cum inside.  ").toString());
                if(solo.booleanValue())
                {
                    w.append(t, "The Thralls push their cameras close from all angles, ");
                    if(loved.booleanValue())
                        w.append(t, (new StringBuilder("forcing ")).append(high.getMainName()).append(" and ").append(low.getMainName()).append(" to watch as well.  The stimulation continues until ").toString());
                    else
                    if(hated.booleanValue())
                    {
                        w.append(t, (new StringBuilder("intending to make it so that the public as well as the watching ")).append(high.getMainName()).append(" and ").append(low.getMainName()).append(" are disappointed in ").append(c.hisHer()).append(" weakness when ").append(c.heShe()).append(" ").toString());
                    } else
                    {
                        w.append(t, "intending to show this to the public.  Meanwhile, ");
                        if(lover == high)
                            w.append(t, (new StringBuilder(String.valueOf(low.getMainName()))).append("'s disappointment and ").append(high.getMainName()).append("'s anger are obvious as the Thralls escalate the stimulation until ").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(low.getMainName()))).append("'s fear and ").append(high.getMainName()).append("'s contempt are obvious as the Thralls escalate the stimulation until ").toString());
                    }
                } else
                if(trio.booleanValue())
                {
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(", ").append(high.getMainName()).append(", and ").append(low.getMainName()).append(" are all forced together, tangling their bare limbs around each other as the pleasure escalates and the Thralls pull out their cameras to film their mutual lust.  ").toString());
                    if(loved.booleanValue())
                        w.append(t, (new StringBuilder("The feeling of ")).append(c.hisHer()).append(" friends' bodies trembling as they approach their own orgasms stimulates ").append(c.himHer()).append(" until ").toString());
                    else
                    if(hated.booleanValue())
                        w.append(t, (new StringBuilder("The taboo pleasure of being forced to grind against ")).append(c.hisHer()).append(" rivals grows stronger and stronger until ").toString());
                    else
                        w.append(t, (new StringBuilder("In the midst of the orgy, ")).append(c.heShe()).append(" and ").append(lover.getMainName()).append(" can clearly feel each other approaching orgasm, and that knowledge is so stimulating that ").toString());
                } else
                {
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" and ").append(partner.getMainName()).append(" are pushed together, bare limbs tangled around each other's stripped bodies, ").toString());
                    if(loved.booleanValue() || partner == lover)
                        w.append(t, "close enough to feel each other's breath on their faces");
                    else
                        w.append(t, "refusing to meet each other's gaze as they deny their shared passion");
                    w.append(t, ".  The Thralls close in with their cameras to film as ");
                }
                if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                {
                    if(innocence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" gasps and squirms against the warmth building in ").append(c.hisHer()).append(" loins.").toString());
                    else
                    if(innocence > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is shaking with the urge to cum.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" has to shake ").append(c.hisHer()).append(" head and take deep breaths in order to stay sane.").toString());
                } else
                if(innocence > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" throws ").append(c.hisHer()).append(" head back and surrenders to the urge to cum, forgetting everything else.").toString());
                else
                if(innocence > 33)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" starts reflexively bucking ").append(c.hisHer()).append(" hips, turning ").append(c.hisHer()).append(" face downward in shame at cumming like this.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" gasps and shudders, face bright red with shame at being forced to cum.").toString());
            }
        } else
        if(variant == 0)
        {
            if(solo.booleanValue())
            {
                if(high.getHATELevel() < 3 && mid.getHATELevel() < 3)
                    w.append(t, (new StringBuilder("With ")).append(high.getMainName()).append("'s and ").append(mid.getMainName()).append("'s Sexual Barriers still intact, ").toString());
                else
                if(high.getHATELevel() < 3)
                    w.append(t, (new StringBuilder("With ")).append(mid.getMainName()).append(" barely fending off ").append(mid.hisHer()).append(" attackers and ").append(high.getMainName()).append("'s Sexual Barrier still intact, ").toString());
                else
                if(mid.getHATELevel() < 3)
                    w.append(t, (new StringBuilder("With ")).append(high.getMainName()).append(" determinedly fending off ").append(high.hisHer()).append(" attackers and ").append(mid.getMainName()).append("'s Sexual Barrier still intact, ").toString());
                else
                    w.append(t, (new StringBuilder("With ")).append(high.getMainName()).append(" and ").append(mid.getMainName()).append(" fending off their attackers, ").toString());
                w.append(t, (new StringBuilder("the Thralls focus their efforts on ")).append(mainName).append(".  Some force ").append(c.himHer()).append(" to suck their cocks while others prefer ").toString());
            } else
            if(trio.booleanValue())
            {
                if(high.getGender().equals("female") && mid.getGender().equals("female"))
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" alternates between licking ").append(high.getMainName()).append("'s and ").append(mid.getMainName()).append("'s clits as their pussies are pounded by the Thralls, ").toString());
                else
                if(high.getGender().equals("female"))
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" alternates between sucking ").append(mid.getMainName()).append("'s cock and licking ").append(high.getMainName()).append("'s clit as they're both fucked by the Thralls, ").toString());
                else
                if(mid.getGender().equals("female"))
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" alternates between sucking ").append(high.getMainName()).append("'s cock and licking ").append(mid.getMainName()).append("'s clit as they're both fucked by the Thralls, ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" takes ").append(high.getMainName()).append("'s and ").append(mid.getMainName()).append("'s cocks into ").append(c.hisHer()).append(" mouth at once as they're both fucked from behind by the Thralls, ").toString());
                if(loved.booleanValue())
                    w.append(t, (new StringBuilder("deliriously trying to soothe ")).append(c.hisHer()).append(" friends' suffering.  ").toString());
                else
                if(hated.booleanValue())
                    w.append(t, (new StringBuilder("forced to move ")).append(c.hisHer()).append(" head back and forth by ").append(c.hisHer()).append(" attackers' hands gripping ").append(c.hisHer()).append(" hair.  ").toString());
                else
                    w.append(t, (new StringBuilder("being forced against ")).append(c.hisHer()).append(" will but clearly less bothered by servicing ").append(lover.getMainName()).append(".  ").toString());
                w.append(t, (new StringBuilder("Meanwhile, the Thralls make use of ")).append(mainName).append("'s lower body, ").toString());
            } else
            {
                if(partner.getGender().equals("female"))
                    w.append(t, (new StringBuilder("As one of the Thralls pounds ")).append(partner.getMainName()).append(", ").append(mainName).append(" licks ").append(partner.hisHer()).append(" clit").toString());
                else
                    w.append(t, (new StringBuilder("As one of the Thralls fucks ")).append(partner.getMainName()).append(", ").append(mainName).append(" sucks ").append(partner.hisHer()).append(" cock").toString());
                if(loved.booleanValue() || lover == partner)
                    w.append(t, (new StringBuilder(", obeying the Thralls' orders while also soothing ")).append(c.hisHer()).append(" friend.  ").toString());
                else
                    w.append(t, ".  ");
                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is completely distracted and defenseless when the Thralls start ").toString());
            }
            if(c.getINJULevel() < 3 || aVirg.booleanValue())
            {
                if(morality > 66)
                    w.append(t, (new StringBuilder("violating ")).append(c.hisHer()).append(" ").append(hole).append(" as ").append(c.heShe()).append(" shudders in self-disgust.  ").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder("penetrating ")).append(c.hisHer()).append(" ").append(hole).append(" as ").append(c.heShe()).append(" sobs and tries to shake ").append(c.hisHer()).append(" head.  ").toString());
                else
                    w.append(t, (new StringBuilder("thrusting in and out of ")).append(c.hisHer()).append(" ").append(hole).append(" as ").append(c.heShe()).append(" sobs in helpless fury.  ").toString());
            } else
            if(w.tickle().booleanValue())
            {
                if(morality > 66)
                    w.append(t, (new StringBuilder("tickling ")).append(c.himHer()).append(" and violating ").append(c.hisHer()).append(" ").append(hole).append(" at the same time.  The fact that ").append(c.heShe()).append(" can't contain ").append(c.hisHer()).append(" sobs of laughter makes ").append(c.himHer()).append(" feel like an utter failure.  ").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder("forcing ")).append(c.himHer()).append(" into helpless laughter so that ").append(c.hisHer()).append(" ").append(hole).append(" tightens up around their cocks.  ").toString());
                else
                    w.append(t, (new StringBuilder("deepening ")).append(c.hisHer()).append(" humiliation by using incessant tickling to force ").append(c.himHer()).append(" to laugh as they fuck ").append(c.hisHer()).append(" ").append(hole).append(".  ").toString());
            } else
            if(c.getGender().equals("male"))
            {
                if(morality > 66)
                    w.append(t, (new StringBuilder("violating ")).append(c.hisHer()).append(" asshole and pinching ").append(c.hisHer()).append(" penis as ").append(c.heShe()).append(" sobs with self-disgust at ").append(c.hisHer()).append(" own weakness for letting this happen.  ").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder("pummeling ")).append(c.hisHer()).append(" penis so that ").append(c.hisHer()).append(" anus tightens reflexively around their cocks.  ").toString());
                else
                    w.append(t, (new StringBuilder("twisting ")).append(c.hisHer()).append(" penis and pounding ").append(c.hisHer()).append(" asshole until ").append(c.hisHer()).append(" resentful glare melts into pathetic tears.  ").toString());
            } else
            if(morality > 66)
                w.append(t, (new StringBuilder("violating ")).append(c.hisHer()).append(" pussy and asshole in turn as ").append(c.heShe()).append(" shudders in self-disgust.  ").toString());
            else
            if(morality > 33)
                w.append(t, (new StringBuilder("penetrating ")).append(c.hisHer()).append(" pussy and asshole in turn as ").append(c.heShe()).append(" sobs and shakes ").append(c.hisHer()).append(" head.  ").toString());
            else
                w.append(t, (new StringBuilder("thrusting in and out of ")).append(c.hisHer()).append(" pussy and asshole in turn as ").append(c.heShe()).append(" sobs in helpless fury.  ").toString());
            if(c.getPLEALevel() < 3 || cVirg.booleanValue())
            {
                if(innocence > 66)
                    w.append(t, (new StringBuilder("The feeling of the Thrall cumming inside is so awful that ")).append(mainName).append(" barely even notices the Thralls ").toString());
                else
                if(innocence > 33)
                    w.append(t, (new StringBuilder("Feeling completely helpless, ")).append(mainName).append(" just goes limp in the arms of the Thralls ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" knows ").append(c.heShe()).append(" needs to focus on escaping, but ").append(c.heShe()).append(" can't even muster the energy to struggle against the Thralls ").toString());
            } else
            if(innocence > 66)
                w.append(t, (new StringBuilder("But the negative responses don't last long, as the pleasure soon builds to the point of orgasm, leaving ")).append(mainName).append(" moaning helplessly in the arms of the Thralls ").toString());
            else
            if(innocence > 33)
                w.append(t, (new StringBuilder("Feeling completely helpless, ")).append(mainName).append(" almost welcomes the haze of pleasure that builds to the point of orgasm, preventing ").append(c.himHer()).append(" from even thinking about fighting against the Thralls ").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" knows that ").append(c.heShe()).append(" needs to focus on escaping, but ").append(c.hisHer()).append(" body betrays ").append(c.himHer()).append(".  ").append(c.HeShe()).append(" climaxes, helpless against the Thralls ").toString());
            String girl = "girl";
            if(c.getGender().equals("male"))
                girl = "boy";
            if(c.getEXPOLevel() < 3 || modest.booleanValue())
            {
                if(dignity > 66)
                    w.append(t, (new StringBuilder("tearing at ")).append(c.hisHer()).append(" ").append(bottomDesc).append(" in order to expose the shy ").append(girl).append("'s body to the world.").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder("tearing at ")).append(c.hisHer()).append(" ").append(bottomDesc).append(" in hopes of humiliating ").append(c.himHer()).append(" even further.").toString());
                else
                    w.append(t, (new StringBuilder("pulling off ")).append(c.hisHer()).append(" ").append(bottomDesc).append(".").toString());
            } else
            if(dignity > 66)
                w.append(t, (new StringBuilder("filming the shy ")).append(girl).append("'s stripped body.").toString());
            else
            if(dignity > 33)
                w.append(t, (new StringBuilder("spreading ")).append(c.hisHer()).append(" legs apart in order to film the spot where ").append(c.heShe()).append("'s being penetrated.").toString());
            else
                w.append(t, (new StringBuilder("filming the spot between ")).append(c.hisHer()).append(" splayed-out legs.").toString());
        } else
        if(variant == 1)
        {
            if(solo.booleanValue())
            {
                if(loved.booleanValue())
                    w.append(t, (new StringBuilder("Turned on by the way ")).append(high.getMainName()).append(" and ").append(mid.getMainName()).append(" are watching ").append(c.himHer()).append(", ").append(mainName).append(" hates ").append(c.himHer()).append("self for ").toString());
                else
                if(hated.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(high.getMainName()))).append(" and ").append(mid.getMainName()).append(" are obviously contemptful toward ").append(mainName).append(" for ").toString());
                else
                    w.append(t, (new StringBuilder("Turned on by the feeling of ")).append(lover.getMainName()).append("'s eyes on ").append(c.himHer()).append(", ").append(mainName).append(" hates ").append(c.himHer()).append("self for ").toString());
            } else
            if(trio.booleanValue())
            {
                if(loved.booleanValue())
                    w.append(t, (new StringBuilder("The sight of ")).append(high.getMainName()).append(" and ").append(mid.getMainName()).append(" squirming and moaning in orgasm turns ").append(mainName).append(" on so much that ").append(c.heShe()).append(" has no chance to resist ").toString());
                else
                if(hated.booleanValue())
                    w.append(t, (new StringBuilder("The sight of ")).append(high.getMainName()).append(" and ").append(mid.getMainName()).append(" squirming and moaning in orgasm makes ").append(mainName).append(" feel like there's nothing wrong with ").toString());
                else
                    w.append(t, (new StringBuilder("Feeling turned on and a little jealous as ")).append(c.heShe()).append(" watches ").append(high.getMainName()).append(" and ").append(mid.getMainName()).append(" forced to grind against each other as they reach orgasm, ").append(mainName).append(" can't resist ").toString());
            } else
            if(lover == partner || loved.booleanValue())
                w.append(t, (new StringBuilder("In the press of bodies around the three Chosen, ")).append(partner.getMainName()).append("'s orgasmic spasms cause ").append(partner.himHer()).append(" to grind against ").append(mainName).append(", who is so turned on by the contact that ").append(c.heShe()).append(" has no chance to resist ").toString());
            else
                w.append(t, (new StringBuilder("In the press of bodies around the three Chosen, ")).append(partner.getMainName()).append("'s orgasmic spasms cause ").append(partner.himHer()).append(" to grind against ").append(mainName).append(".  Even though ").append(mainName).append(" tries to be strong, the stimulation is still too much to bear, and ").append(c.heShe()).append(" ends up ").toString());
            if(c.getINJULevel() < 3 || aVirg.booleanValue())
            {
                if(innocence > 66)
                    w.append(t, (new StringBuilder("surrendering to the pleasure of the Thralls' hands on ")).append(c.hisHer()).append(" ").append(organ).append(".  ").toString());
                else
                if(innocence > 33)
                    w.append(t, (new StringBuilder("cumming for the Thralls tugging ")).append(c.hisHer()).append(" ").append(organ).append(".  ").toString());
                else
                    w.append(t, (new StringBuilder("reaching climax due to the Thralls' stimulation of ")).append(c.hisHer()).append(" ").append(organ).append(".  ").toString());
            } else
            if(w.tickle().booleanValue())
            {
                if(innocence > 66)
                    w.append(t, (new StringBuilder("surrendering to the pleasure, whimpering with delirious laughter as the Thralls' tickling hands make their way to ")).append(c.hisHer()).append(" ").append(organ).append(".  ").toString());
                else
                if(innocence > 33)
                    w.append(t, (new StringBuilder("cumming and laughing at the same time, helpless against the Thralls tickling ")).append(c.himHer()).append(" and tugging ").append(c.hisHer()).append(" ").append(organ).append(".  ").toString());
                else
                    w.append(t, (new StringBuilder("reaching climax even as ")).append(c.heShe()).append(" shakes with helpless laughter due to the Thralls' tickling and stimulation of ").append(c.hisHer()).append(" ").append(organ).append(".  ").toString());
            } else
            if(c.getGender().equals("male"))
            {
                if(innocence > 66)
                    w.append(t, (new StringBuilder("surrendering to the mingled pleasure and pain of the Thralls' rough hands tormenting ")).append(c.hisHer()).append(" sensitive penis.  ").toString());
                else
                if(innocence > 33)
                    w.append(t, (new StringBuilder("cumming and whimpering in pain at the same time, helpless against the Thralls manhandling ")).append(c.hisHer()).append(" sensitive penis.  ").toString());
                else
                    w.append(t, (new StringBuilder("reaching climax even as the Thralls pummel ")).append(c.hisHer()).append(" sensitive testicles.  ").toString());
            } else
            if(innocence > 66)
                w.append(t, (new StringBuilder("surrendering to the mingled pleasure and pain as one of the Thralls pinches ")).append(c.hisHer()).append(" ").append(organ).append(" while another fists ").append(c.hisHer()).append(" ass.  ").toString());
            else
            if(innocence > 33)
                w.append(t, (new StringBuilder("cumming and whimpering in pain at the same time, helpless against the rough handling of ")).append(c.hisHer()).append(" ").append(organ).append(" from the outside and the fist ravaging ").append(c.hisHer()).append(" ass from the inside.  ").toString());
            else
                w.append(t, (new StringBuilder("reaching climax as the Thralls combine stimulating ")).append(c.hisHer()).append(" ").append(organ).append(" with fisting ").append(c.hisHer()).append(" ass.  ").toString());
            if(c.getHATELevel() < 3 || vVirg.booleanValue())
            {
                if(morality > 66)
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" still has the presence of mind to be disgusted by the Thralls grinding against ").append(c.himHer()).append(" in pursuit of their own orgasms, but ").append(c.heShe()).append(" feels ").append(c.heShe()).append(" deserves the degrading treatment for enjoying ").append(c.himHer()).append("self this much").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" limbs feel too weak to push away the Thralls grinding against ").append(c.himHer()).append(" in pursuit of their own orgasms").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append("'s so delirious with pleasure that ").append(c.heShe()).append(" barely notices the Thralls humping ").append(c.himHer()).append(" in pursuit of their own orgasms").toString());
            } else
            if(morality > 66)
                w.append(t, (new StringBuilder("As disgusted as ")).append(c.heShe()).append(" is by the Thrall's cock plunging into ").append(c.hisHer()).append(" ").append(hole).append(" and spurting its load inside, ").append(c.heShe()).append(" feels ").append(c.heShe()).append(" deserves it for enjoying this at all").toString());
            else
            if(morality > 33)
                w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" ").append(hole).append(" squeezes down on a Thrall's invading cock, and ").append(c.heShe()).append(" realizes with detached disgust that it's cumming inside ").append(c.himHer()).toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" tries to threaten retribution if the Thrall fucking ").append(c.hisHer()).append(" ").append(hole).append(" cums inside, but ").append(c.heShe()).append(" can't form the words.  ").append(c.HeShe()).append(" groans as ").append(c.heShe()).append(" feels the invading cock spurting inside ").append(c.himHer()).toString());
            if(c.getEXPOLevel() < 3 || modest.booleanValue())
            {
                if(dignity > 66)
                    w.append(t, (new StringBuilder(".  No matter how embarrassing it is, ")).append(c.heShe()).append(" doesn't have the willpower to stop the others from stripping ").append(c.himHer()).append(".").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder(".  With ")).append(c.hisHer()).append(" mind feeling so empty, ").append(c.heShe()).append(" can't muster the effort to stop the others from stripping ").append(c.himHer()).append(".").toString());
                else
                    w.append(t, (new StringBuilder(".  Before ")).append(c.heShe()).append(" knows it, the others have made significant progress in stripping ").append(c.himHer()).append(".").toString());
            } else
            if(dignity > 66)
                w.append(t, (new StringBuilder(".  No matter how embarrassing it is, ")).append(c.heShe()).append(" doesn't have the willpower to hide ").append(c.hisHer()).append(" stripped body from all the cameras.").toString());
            else
            if(dignity > 33)
                w.append(t, (new StringBuilder(".  With ")).append(c.hisHer()).append(" mind feeling so empty, ").append(c.heShe()).append(" can't muster the effort to stop the others from filming ").append(c.hisHer()).append(" humiliating state from all angles.").toString());
            else
                w.append(t, (new StringBuilder(".  Before ")).append(c.heShe()).append(" knows it, dozens of cameras are being pushed up close to film ").append(c.hisHer()).append(" face and stripped body.").toString());
        } else
        if(variant == 2)
        {
            if(solo.booleanValue())
            {
                if(loved.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" sobs for ").append(high.getMainName()).append(" and ").append(mid.getMainName()).append(" to save ").append(c.himHer()).append(", but they're both held back by their captors.  ").toString());
                else
                if(hated.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" sobs at the helplessness of ").append(c.hisHer()).append(" situation, earning contemptful glances from ").append(high.getMainName()).append(" and ").append(mid.getMainName()).append(".  ").toString());
                else
                if(lover == high)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" sobs openly for ").append(lover.getMainName()).append(" to help ").append(c.himHer()).append(", prompting ").append(mid.getMainName()).append(" to sigh with annoyance.  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" sobs openly for ").append(lover.getMainName()).append(" to help ").append(c.himHer()).append(", earning a contemptful glance from ").append(high.getMainName()).append(".  ").toString());
            } else
            if(trio.booleanValue())
            {
                if(!loved.booleanValue() && !hated.booleanValue())
                    w.append(t, (new StringBuilder("Even though ")).append(lover.getMainName()).append(" is dealing with ").append(lover.hisHer()).append(" own torture, ").toString());
                else
                    w.append(t, "Even though they're dealing with their own torture, ");
                if(loved.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" still sobs for ").append(high.getMainName()).append(" and ").append(mid.getMainName()).append(" to help ").append(c.himHer()).append(", which only makes them feel even worse.  ").toString());
                else
                if(hated.booleanValue())
                {
                    w.append(t, (new StringBuilder(String.valueOf(high.getMainName()))).append(" and ").append(mid.getMainName()).append(" aren't sobbing pathetically like ").append(mainName).append(" is.  ").toString());
                } else
                {
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" still sobs for ").append(lover.hisHer()).append(" help ").toString());
                    if(lover == high)
                        w.append(t, (new StringBuilder("while ")).append(mid.getMainName()).append(" clenches ").append(mid.hisHer()).append(" teeth and tries to hold back ").append(mid.hisHer()).append(" voice.  ").toString());
                    else
                        w.append(t, (new StringBuilder("while ")).append(high.getMainName()).append(" tries to fight ").append(high.hisHer()).append(" way free.  ").toString());
                }
            } else
            if(loved.booleanValue() || lover == partner)
                w.append(t, (new StringBuilder("Even though ")).append(partner.getMainName()).append(" is dealing with ").append(partner.hisHer()).append(" own torture, ").append(mainName).append(" still sobs for ").append(partner.hisHer()).append(" help.  ").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" sobs openly for someone to save ").append(c.himHer()).append(" while ").append(partner.getMainName()).append(" clenches ").append(c.hisHer()).append(" teeth and tries to hold back ").append(c.hisHer()).append(" own voice.  ").toString());
            if(w.tickle().booleanValue())
                w.append(t, (new StringBuilder("The Thrall's tickling is driving ")).append(mainName).append(" crazy, ").toString());
            else
            if(c.getGender().equals("male"))
                w.append(t, (new StringBuilder("The pain of the Thralls crushing and twisting ")).append(mainName).append("'s testicles is driving ").append(c.himHer()).append(" crazy, ").toString());
            else
                w.append(t, (new StringBuilder("The repeated impacts of the Thrall fucking ")).append(c.himHer()).append(" up the ass are driving ").append(c.himHer()).append(" crazy, ").toString());
            if(c.getHATELevel() < 3 || vVirg.booleanValue())
            {
                if(morality > 66)
                    w.append(t, (new StringBuilder("and ")).append(c.heShe()).append("'s ashamed of ").append(c.himHer()).append("self for feeling pleasure from the way they tug ").append(c.hisHer()).append(" ").append(organ).append(" at the same time.  ").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder("and ")).append(c.heShe()).append("'s desperately trying to deny the pleasure ").append(c.heShe()).append(" feels as they tug ").append(c.hisHer()).append(" ").append(organ).append(" at the same time.  ").toString());
                else
                    w.append(t, (new StringBuilder("and ")).append(c.heShe()).append("'s caught between rage, embarrassment, and confused pleasure as they tug ").append(c.hisHer()).append(" ").append(organ).append(" at the same time.  ").toString());
            } else
            if(morality > 66)
                w.append(t, (new StringBuilder("and it's only made worse by ")).append(c.hisHer()).append(" feelings of violated worthlessness as they take turns cumming inside ").append(c.hisHer()).append(" ").append(hole).append(" and stroking ").append(c.hisHer()).append(" ").append(organ).append(" at the same time.  ").toString());
            else
            if(morality > 33)
                w.append(t, (new StringBuilder("and ")).append(c.hisHer()).append(" efforts to deny the pleasure from the Thrall tugging ").append(c.hisHer()).append(" ").append(organ).append(" fall to pieces when another penetrates ").append(c.hisHer()).append(" ").append(hole).append(" and cums inside.  ").toString());
            else
                w.append(t, (new StringBuilder("and even as ")).append(c.heShe()).append(" deliriously tries to threaten the Thrall penetrating ").append(c.hisHer()).append(" ").append(hole).append(", ").append(c.heShe()).append(" can't help but buck ").append(c.hisHer()).append(" hips at the same time.  ").toString());
            if(c.getPLEALevel() < 3 || cVirg.booleanValue())
            {
                if(innocence > 66)
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" still manages to not completely give ").append(c.himHer()).append("self over to the feelings, focusing on ").toString());
                else
                if(innocence > 33)
                    w.append(t, (new StringBuilder("It's not quite enough to make ")).append(c.himHer()).append(" cum yet, mostly because of ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" still manages to resist the urge to climax, thanks largely to ").toString());
            } else
            if(innocence > 66)
                w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" feeble willpower is completely outmatched, and ").append(c.heShe()).append(" doesn't even try to fight the waves of pleasure running through ").append(c.hisHer()).append(" body despite ").toString());
            else
            if(innocence > 33)
                w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" cums quickly despite ").toString());
            else
                w.append(t, (new StringBuilder("Tears of humiliation leak out of ")).append(c.hisHer()).append(" eyes as ").append(c.heShe()).append("'s forced to climax, unable to stop dwelling on ").toString());
            if(c.getEXPOLevel() < 3 || modest.booleanValue())
            {
                if(dignity > 66)
                    w.append(t, (new StringBuilder(String.valueOf(c.hisHer()))).append(" horror at the fact that the Thralls are stripping ").append(c.himHer()).append(" at the same time.").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder(String.valueOf(c.hisHer()))).append(" embarrassment.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.hisHer()))).append(" belief that enjoying this sort of thing is just wrong.").toString());
            } else
            if(dignity > 66)
                w.append(t, (new StringBuilder(String.valueOf(c.hisHer()))).append(" overpowering shame at being stripped and filmed in such a humiliating state.").toString());
            else
            if(dignity > 33)
                w.append(t, (new StringBuilder("the fact that so many cameras are watching ")).append(c.himHer()).append(".").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(c.hisHer()))).append(" belief that a true hero shouldn't be affected by little things like stripping and molestation.").toString());
        } else
        if(variant == 3)
        {
            if(solo.booleanValue())
            {
                w.append(t, (new StringBuilder("The Thralls order ")).append(mainName).append(" to spread ").append(c.hisHer()).append(" own ").append(hole).append(" wide open for the cameras").toString());
                if(loved.booleanValue())
                    w.append(t, (new StringBuilder(", ignoring ")).append(high.getMainName()).append("'s and ").append(mid.getMainName()).append("'s attempts to distract them and protect ").append(c.himHer()).append(".  ").toString());
                else
                if(hated.booleanValue())
                    w.append(t, (new StringBuilder(", and since ")).append(c.heShe()).append("'s the one that allowed ").append(c.himHer()).append("self to get stripped, neither ").append(high.getMainName()).append(" nor ").append(mid.getMainName()).append(" feel much sympathy.  ").toString());
                else
                    w.append(t, (new StringBuilder(", ignoring ")).append(lover.getMainName()).append("'s attempts to divert their attention onto ").append(lover.himHer()).append("self.  ").toString());
            } else
            if(trio.booleanValue())
            {
                w.append(t, "The three Chosen are laid on their backs with their legs lifted up into the air in order to display their bare lower halves to the cameras");
                if(loved.booleanValue())
                    w.append(t, (new StringBuilder(", but ")).append(high.getMainName()).append(" and ").append(mid.getMainName()).append(" both look worried when the crowd turns its attention to their friend.  ").toString());
                else
                if(hated.booleanValue())
                    w.append(t, ", obviously all angry with each other for allowing this to happen.  ");
                else
                    w.append(t, (new StringBuilder(", and although ")).append(lover.getMainName()).append(" tries to get the Thralls to focus on ").append(lover.himHer()).append(", they have a different target in mind.  ").toString());
                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is ordered to use ").append(c.hisHer()).append(" own fingers to spread ").append(c.hisHer()).append(" ").append(hole).append(".  ").toString());
            } else
            {
                if(loved.booleanValue() || lover == partner)
                    w.append(t, (new StringBuilder("Even though they've both been stripped, ")).append(partner.getMainName()).append(" tries to use ").append(partner.hisHer()).append(" body to hide ").append(mainName).append(" from the cameras, but the Thralls pull them apart.  ").toString());
                else
                    w.append(t, (new StringBuilder("Neither ")).append(mainName).append(" nor ").append(partner.getMainName()).append(" are happy about being forced to sit side-by-side with their legs lifted up into the air in order to expose their bare lower halves to the cameras.  ").toString());
                w.append(t, (new StringBuilder("The Thralls order ")).append(mainName).append(" to use ").append(c.hisHer()).append(" own fingers to spread ").append(c.hisHer()).append(" ").append(hole).append(".  ").toString());
            }
            if(c.getINJULevel() < 3 || aVirg.booleanValue())
            {
                if(dignity > 66)
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" freezes up, stammering incoherently until the Thralls reach down to do it themselves.  ").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" squeezes ").append(c.hisHer()).append(" eyes shut as ").append(c.heShe()).append(" complies, face bright red.  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" hurriedly complies for fear of being punished.  ").toString());
            } else
            if(w.tickle().booleanValue())
            {
                if(dignity > 66)
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append("'s already practically delirious with embarrassment at being filmed while practically naked and flailing in helpless laughter, so ").append(c.heShe()).append(" doesn't even notice the request until the Thralls reach down to do it for ").append(c.himHer()).append(".  ").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" complies as best ").append(c.heShe()).append(" can while being tickled at the same time, ").append(c.hisHer()).append(" laughter coming out mingled with whimpers of embarrassment.  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" complies as best ").append(c.heShe()).append(" can while being tickled at the same time, stammering apologies for not being quicker about it.  ").toString());
            } else
            if(c.getGender().equals("male"))
            {
                if(dignity > 66)
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" tries to comply, but ").append(c.hisHer()).append(" hands are shaking so much with sheer embarrassment that the Thralls have to do it for ").append(c.himHer()).append(", twisting ").append(c.hisHer()).append(" bruised testicles at the same time as punishment.  ").toString());
                else
                if(dignity > 33)
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" slowly complies, ").append(c.hisHer()).append(" trembling fingers carefully avoiding ").append(c.hisHer()).append(" swollen, bruised testicles.  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" complies without hesitation, begging the Thralls to go easier on ").append(c.hisHer()).append(" bruised testicles in return.  ").toString());
            } else
            if(dignity > 66)
                w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" tries to comply, but ").append(c.hisHer()).append(" hands are shaking so badly with the embarrassment of being filmed while also being anally penetrated that ").append(c.heShe()).append(" can't do it, and the Thralls need to reach down to do it for ").append(c.himHer()).append(".  ").toString());
            else
            if(dignity > 33)
                w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" complies as best ").append(c.heShe()).append(" can while being fucked up the ass at the same time, practically delirious with shame over having ").append(c.hisHer()).append(" privates seen in such a state.  ").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" complies without hesitation, the cock thrusting in and out of ").append(c.hisHer()).append(" asshole making the image even more lewd.  ").toString());
            if(c.getHATELevel() < 3 || vVirg.booleanValue())
            {
                if(c.getHATELevel() < 3)
                    w.append(t, "In order to assert their dominance even more, ");
                else
                    w.append(t, (new StringBuilder("Because ")).append(c.hisHer()).append(" Sexual Barrier prevents them from going any further down there, ").toString());
                w.append(t, (new StringBuilder("they force ")).append(c.himHer()).append(" to suck their cocks as well.  ").toString());
                if(morality > 66)
                    w.append(t, (new StringBuilder("They force ")).append(c.hisHer()).append(" compliance by threatening the others, ").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" meek nature prevents ").append(c.himHer()).append(" from refusing, ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append("'s afraid of what will happen if ").append(c.heShe()).append(" bites down, ").toString());
            } else
            {
                w.append(t, (new StringBuilder("Then, they force ")).append(c.himHer()).append(" to straddle one of the Thralls and ride his cock.  ").toString());
                if(morality > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s ability to pleasure others is normally one of the few things ").append(c.heShe()).append(" prides ").append(c.himHer()).append("self on, ").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder("They order ")).append(mainName).append(" to move ").append(c.hisHer()).append(" hips, ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" starts to move ").append(c.hisHer()).append(" hips by reflex, ").toString());
            }
            if(c.getPLEALevel() < 3 || cVirg.booleanValue())
            {
                if(innocence > 66)
                    w.append(t, (new StringBuilder("but ")).append(c.heShe()).append("'s too scared to effectively perform.").toString());
                else
                if(innocence > 33)
                    w.append(t, (new StringBuilder("and ")).append(c.heShe()).append(" does ").append(c.hisHer()).append(" best to make them cum.").toString());
                else
                    w.append(t, (new StringBuilder("and ")).append(c.heShe()).append(" just focuses on bringing them to orgasm as quickly as possible.").toString());
            } else
            if(innocence > 66)
                w.append(t, (new StringBuilder("but with their fingers coaxing another orgasm from ")).append(c.hisHer()).append(" ").append(organ).append(", ").append(c.heShe()).append(" lacks the willpower to do anything but squirm and moan.").toString());
            else
            if(innocence > 33)
                w.append(t, (new StringBuilder("but with their fingers coaxing another orgasm from ")).append(c.hisHer()).append(" ").append(organ).append(", ").append(c.heShe()).append("'s almost too distracted to perform.").toString());
            else
                w.append(t, (new StringBuilder("but with their fingers coaxing another orgasm from ")).append(c.hisHer()).append(" ").append(organ).append(", ").append(c.hisHer()).append(" movements are much less skillful than usual.").toString());
        }
        if(variant == 4)
        {
            Chosen otherOne = null;
            Chosen otherTwo = null;
            if(confidence > 66)
            {
                otherOne = mid;
                otherTwo = low;
            } else
            if(confidence > 33)
            {
                otherOne = high;
                otherTwo = low;
            } else
            {
                otherOne = high;
                otherTwo = mid;
            }
            if(w.getOrgyStage() == 0)
            {
                if((otherOne.getHATELevel() < 3 || otherOne.isVVirg()) && (otherTwo.getHATELevel() < 3 || otherTwo.isVVirg()))
                    w.append(t, (new StringBuilder("Frustrated by ")).append(otherOne.getMainName()).append("'s and ").append(otherTwo.getMainName()).append("'s continued resistance against the attempts to penetrate them, the Thralls ").toString());
                else
                if(otherOne.getHATELevel() < 3 || otherOne.isVVirg())
                    w.append(t, (new StringBuilder(String.valueOf(otherTwo.getMainName()))).append(" is hardly visible among the crowd of Thralls filling ").append(otherTwo.hisHer()).append(" every available hole with their cum.  ").append(otherOne.getMainName()).append(" is struggling hard against the attempts to do the same to ").append(c.himHer()).append(", so the other Thralls ").toString());
                else
                if(otherTwo.getHATELevel() < 3 || otherTwo.isVVirg())
                    w.append(t, (new StringBuilder(String.valueOf(otherOne.getMainName()))).append(" is hardly visible among the crowd of Thralls filling ").append(otherOne.hisHer()).append(" every available hole with their cum.  There are only room for so many, though, and the others are growing bored tormenting ").append(otherTwo.getMainName()).append(", so they ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(otherOne.getMainName()))).append(" and ").append(otherTwo.getMainName()).append(" are both pinned down by the horde of Thralls frantically thrusting their cocks into every available hole.  Not content with just the two of them, the Thralls ").toString());
                if(c.getHATELevel() < 3)
                {
                    w.append(t, (new StringBuilder("try to force themselves through ")).append(mainName).append("'s Sexual Barrier, but it remains intact for the moment.").toString());
                } else
                {
                    w.append(t, (new StringBuilder("decide to use ")).append(mainName).append("'s ").append(hole).append(".  ").append(c.HisHer()).append(" ").toString());
                    if(c.getGender().equals("male"))
                        w.append(t, "anal ");
                    w.append(t, (new StringBuilder("virginity is onlys aved by the fact that they can't agree on which of them gets to break ")).append(c.himHer()).append(" in.").toString());
                }
            } else
            if(w.getOrgyStage() == 1)
            {
                if((otherOne.getPLEALevel() < 3 || otherOne.isCVirg()) && (otherTwo.getPLEALevel() < 3 || otherTwo.isCVirg()))
                    w.append(t, "The Thralls press the three Chosen against each other, forcing them to reflexively hump each other as they endure their torments.  ");
                else
                if(otherOne.getPLEALevel() < 3 || otherOne.isCVirg())
                    w.append(t, (new StringBuilder("Taking a break from trying to get the stubborn ")).append(otherOne.getMainName()).append(" to climax, the Thralls push ").append(mainName).append(" and ").append(otherTwo.getMainName()).append(" face-to-face, forcing them to grind against each other.  ").append(otherTwo.getMainName()).append(" moans helplessly into ").append(mainName).append("'s mouth, bucking ").append(otherTwo.hisHer()).append(" hips against ").append(c.himHer()).append(" as ").append(otherTwo.heShe()).append(" cums.  ").toString());
                else
                if(otherTwo.getPLEALevel() < 3 || otherTwo.isCVirg())
                    w.append(t, (new StringBuilder("While some Thralls try to break the helpless ")).append(otherTwo.getMainName()).append(" with various sex toys, the others push ").append(mainName).append(" and ").append(otherOne.getMainName()).append(" face-to-face, forcing them to grind against each other.  ").append(otherOne.getMainName()).append("'s struggles grow erratic as ").append(otherOne.heShe()).append(" cums, bucking ").append(otherOne.hisHer()).append(" hips against ").append(mainName).append(".  ").toString());
                else
                    w.append(t, (new StringBuilder("Pressing various sex toys against their crotches, the Thralls make ")).append(otherOne.getMainName()).append(" and ").append(otherTwo.getMainName()).append(" cum hard, forcing their faces between ").append(mainName).append("'s legs at the same time so they're moaning against ").append(c.hisHer()).append(" ").append(organ).append(".  ").toString());
                if(c.getPLEALevel() < 3)
                {
                    w.append(t, (new StringBuilder("However, ")).append(mainName).append(" is still more disgusted than turned on.").toString());
                } else
                {
                    w.append(t, (new StringBuilder("For ")).append(c.hisHer()).append(" part, ").append(mainName).append(" barely holds on, ").toString());
                    if(c.getGender().equals("female"))
                        w.append(t, (new StringBuilder(String.valueOf(c.hisHer()))).append(" body still refusing to accept the pleasure.").toString());
                    else
                        w.append(t, (new StringBuilder("pre-cum dribbling out of ")).append(c.hisHer()).append(" penis.").toString());
                }
            } else
            if(w.getOrgyStage() == 2)
            {
                Boolean allResist = Boolean.valueOf(false);
                if((otherOne.getINJULevel() < 3 || otherOne.isAVirg()) && (otherTwo.getINJULevel() < 3 || otherTwo.isAVirg()))
                {
                    allResist = Boolean.valueOf(true);
                    w.append(t, (new StringBuilder(String.valueOf(otherOne.getMainName()))).append(" and ").append(otherTwo.getMainName()).append(" barely manage to hold their voices in ").toString());
                } else
                if(otherOne.getINJULevel() < 3 || otherOne.isAVirg())
                    w.append(t, (new StringBuilder("While ")).append(otherOne.getMainName()).append(" stubbornly holds on, ").append(otherTwo.getMainName()).append(" whimpers ").toString());
                else
                if(otherTwo.getINJULevel() < 3 || otherTwo.isAVirg())
                    w.append(t, (new StringBuilder("While ")).append(otherTwo.getMainName()).append(" somehow holds on, ").append(otherOne.getMainName()).append(" is forced to scream ").toString());
                else
                    w.append(t, (new StringBuilder("Utterly defeated, ")).append(otherOne.getMainName()).append(" and ").append(otherTwo.getMainName()).append(" cry out ").toString());
                if(w.tickle().booleanValue())
                {
                    if(allResist.booleanValue())
                        w.append(t, "as they're tickled.  ");
                    else
                        w.append(t, "in helpless laughter at the Thralls' tickling.  ");
                    if(c.getINJULevel() < 3)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is resisting as best ").append(c.heShe()).append(" can, but it's getting harder every moment.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is only spared the indignity of laughing out loud by the Thrall's cock shoved down ").append(c.hisHer()).append(" throat.").toString());
                } else
                {
                    if(allResist.booleanValue())
                        w.append(t, "as the Thralls pummel them and twist their limbs.  ");
                    else
                        w.append(t, "in pain at the Thralls' savage abuse.  ");
                    if(c.getGender().equals("male"))
                        w.append(t, (new StringBuilder("For ")).append(c.hisHer()).append(" part, ").append(mainName).append(" is tightly clenching ").append(c.hisHer()).append(" thighs together to protect ").append(c.hisHer()).append(" vulnerable penis.").toString());
                    else
                        w.append(t, (new StringBuilder("For ")).append(c.hisHer()).append(" part, ").append(mainName).append(" is grimacing at the sensation of another Thrall trying and failing over and over again to shove a huge dildo up ").append(c.hisHer()).append(" ass.").toString());
                }
            } else
            if(w.getOrgyStage() == 3)
                if(otherOne.getEXPOLevel() < 3 || otherOne.isModest() || otherTwo.getEXPOLevel() < 3 || otherTwo.isModest())
                    w.append(t, "Scraps of shredded clothing are flying out of the crowd of Thralls around the three Chosen, but the group is so densely-packed that no one can get clear footage of their exposed bodies.");
                else
                if(otherOne.getEXPOLevel() < 3 || otherOne.isModest())
                    w.append(t, (new StringBuilder("The orgy is so densely-packed that the watching cameras can't get any good footage of the Chosen within.  Only ")).append(otherTwo.getMainName()).append(" is visible, being lifted above the crowd with ").append(otherTwo.hisHer()).append(" legs forcibly spread wide open.").toString());
                else
                if(otherTwo.getEXPOLevel() < 3 || otherTwo.isModest())
                    w.append(t, (new StringBuilder("The orgy is so densely-packed that the watching cameras can't get any good footage of the Chosen within.  Only ")).append(otherOne.getMainName()).append(" is visible at the edge of the crowd, ").append(otherOne.hisHer()).append(" bare bottom and flailing legs drawing plenty of attention.").toString());
                else
                    w.append(t, (new StringBuilder("The orgy is so densely-packed that ")).append(mainName).append(", who's currently at its center, can't be clearly seen.  Meanwhile, ").append(otherOne.getMainName()).append(" and ").append(otherTwo.getMainName()).append(" have been pulled to the edge and forced to bend over, showing their bare bottoms to the watching cameras.").toString());
        }
        w.append(t, (new StringBuilder("\n\n")).append(mainName).append(":\n").toString());
        int totalDamage[] = {
            200, 200, 200, 200, 200, 200, 200, 200
        };
        Boolean penetrationBonus = Boolean.valueOf(false);
        if(!vVirg.booleanValue() && c.getHATELevel() >= 3)
        {
            penetrationBonus = Boolean.valueOf(true);
            totalDamage = c.multiplyArray(totalDamage, 20);
        }
        Boolean orgasmBonus = Boolean.valueOf(false);
        if(!cVirg.booleanValue() && c.getPLEALevel() >= 3)
        {
            orgasmBonus = Boolean.valueOf(true);
            totalDamage = c.multiplyArray(totalDamage, 20);
        }
        Boolean analBonus = Boolean.valueOf(false);
        if(!aVirg.booleanValue() && c.getINJULevel() >= 3)
        {
            analBonus = Boolean.valueOf(true);
            totalDamage = c.multiplyArray(totalDamage, 20);
        }
        Boolean broadcastBonus = Boolean.valueOf(false);
        if(!modest.booleanValue() && c.getEXPOLevel() >= 3)
        {
            broadcastBonus = Boolean.valueOf(true);
            totalDamage = c.multiplyArray(totalDamage, 20);
        }
        String firstRelation = "";
        String secondRelation = "";
        if(c != high)
        {
            String summary = "(x";
            if(w.getRelationship(c.number, high.getNumber()) == -4)
                summary = (new StringBuilder(String.valueOf(summary))).append("2").toString();
            else
                summary = (new StringBuilder(String.valueOf(summary))).append("1.").append(6 - w.getRelationship(c.number, high.getNumber())).toString();
            summary = (new StringBuilder(String.valueOf(summary))).append(" damage due to relationship with ").append(high.getMainName()).append(")\n\n").toString();
            totalDamage = c.multiplyArray(totalDamage, 16 - w.getRelationship(c.number, high.getNumber()));
            firstRelation = summary;
        }
        if(c != mid)
        {
            String summary = "(x";
            if(w.getRelationship(c.number, mid.getNumber()) == -4)
                summary = (new StringBuilder(String.valueOf(summary))).append("2").toString();
            else
                summary = (new StringBuilder(String.valueOf(summary))).append("1.").append(6 - w.getRelationship(c.number, mid.getNumber())).toString();
            summary = (new StringBuilder(String.valueOf(summary))).append(" damage due to relationship with ").append(mid.getMainName()).append(")\n\n").toString();
            totalDamage = c.multiplyArray(totalDamage, 16 - w.getRelationship(c.number, mid.getNumber()));
            if(c != high)
                secondRelation = summary;
            else
                firstRelation = summary;
        }
        if(c != low)
        {
            String summary = "(x";
            if(w.getRelationship(c.number, low.getNumber()) == -4)
                summary = (new StringBuilder(String.valueOf(summary))).append("2").toString();
            else
                summary = (new StringBuilder(String.valueOf(summary))).append("1.").append(6 - w.getRelationship(c.number, low.getNumber())).toString();
            summary = (new StringBuilder(String.valueOf(summary))).append(" damage due to relationship with ").append(low.getMainName()).append(")\n\n").toString();
            totalDamage = c.multiplyArray(totalDamage, 16 - w.getRelationship(c.number, low.getNumber()));
            secondRelation = summary;
        }
        int previousHATE = c.getHATELevel();
        int previousPLEA = c.getPLEALevel();
        int previousINJU = c.getINJULevel();
        int previousEXPO = c.getEXPOLevel();
        c.damage(t, w, totalDamage);
        if(penetrationBonus.booleanValue())
            if(c.getGender().equals("male"))
                w.append(t, "(x2 damage due to penetration)\n\n");
            else
                w.append(t, "(x2 damage due to vaginal penetration)\n\n");
        if(orgasmBonus.booleanValue())
            w.append(t, "(x2 damage due to orgasm)\n\n");
        if(analBonus.booleanValue())
            if(w.tickle().booleanValue())
                w.append(t, "(x2 damage due to forced laughter)\n\n");
            else
            if(c.getGender().equals("male"))
                w.append(t, "(x2 damage due to genital torture)\n\n");
            else
                w.append(t, "(x2 damage due to anal penetration)\n\n");
        if(broadcastBonus.booleanValue())
            w.append(t, "(x2 damage due to broadcasted humiliation)\n\n");
        w.append(t, (new StringBuilder(String.valueOf(firstRelation))).append(secondRelation).toString());
        w.rememberOrgyStage(order);
        w.cycleOrgyStage();
        int priorities[] = new int[4];
        if(c.getHATELevel() > previousHATE)
        {
            priorities[0]++;
            if(c.getHATELevel() == 3 && !vVirg.booleanValue())
                priorities[0]++;
            w.purpleAppend(t, "HATE up!  ");
        }
        if(c.getPLEALevel() > previousPLEA)
        {
            priorities[1]++;
            if(c.getPLEALevel() == 3 && !cVirg.booleanValue())
                priorities[1]++;
            w.purpleAppend(t, "PLEA up!  ");
        }
        if(c.getINJULevel() > previousINJU)
        {
            priorities[2]++;
            if(c.getINJULevel() == 3 && !aVirg.booleanValue())
                priorities[2]++;
            if(w.tickle().booleanValue())
                w.purpleAppend(t, "ANTI up!  ");
            else
                w.purpleAppend(t, "INJU up!  ");
        }
        if(c.getEXPOLevel() > previousEXPO)
        {
            priorities[3]++;
            if(c.getEXPOLevel() == 3 && !modest.booleanValue())
                priorities[3]++;
            w.purpleAppend(t, "EXPO up!  ");
        }
        int highestPriority = -1;
        if(priorities[0] > priorities[1] && priorities[0] > priorities[3] && priorities[0] > priorities[2])
            highestPriority = 0;
        else
        if(priorities[2] > priorities[1] && priorities[2] > priorities[3])
            highestPriority = 2;
        else
        if(priorities[1] > priorities[3])
            highestPriority = 1;
        else
        if(priorities[3] > 0)
            highestPriority = 3;
        if(highestPriority == 3)
        {
            if(c.getEXPOLevel() == 1)
            {
                if(c.getHATELevel() < 1)
                    w.append(t, (new StringBuilder("Despite ")).append(mainName).append("'s best efforts to keep ").append(c.himHer()).append("self covered, ").toString());
                else
                if(c.getHATELevel() > 1)
                    w.append(t, (new StringBuilder("With ")).append(mainName).append("'s magical defenses decreased by ").append(c.hisHer()).append(" impure emotions, ").toString());
                else
                    w.append(t, (new StringBuilder("With ")).append(mainName).append("'s annoyance causing ").append(c.himHer()).append(" to become impatient and vulnerable, ").toString());
                if(bottomCover.equals("skirt"))
                    w.append(t, (new StringBuilder("the Thralls have managed to tear away a large section of ")).append(c.hisHer()).append(" skirt, rendering it much shorter than before.  ").toString());
                else
                if(bottomCover.equals("miniskirt"))
                    w.append(t, (new StringBuilder("the Thralls have ripped open ")).append(c.hisHer()).append(" miniskirt so that ").append(c.hisHer()).append(" hip and part of ").append(c.hisHer()).append(" ass are completely exposed.  ").toString());
                else
                if(bottomCover.equals("robe"))
                    w.append(t, (new StringBuilder("the Thralls have managed to tear away the bottom portion of ")).append(c.hisHer()).append(" robe so that ").append(c.hisHer()).append(" legs are exposed.  ").toString());
                else
                if(bottomCover.equals("cloak"))
                    w.append(t, (new StringBuilder("the Thralls have ripped ")).append(c.hisHer()).append(" cloak almost in half, so that it barely remains in one piece.  ").toString());
                else
                if(bottomCover.equals("trousers"))
                    w.append(t, (new StringBuilder("the Thralls have torn a large hole in ")).append(c.hisHer()).append(" trousers, partially exposing ").append(c.hisHer()).append(" ass.  ").toString());
                else
                if(bottomCover.equals("leotard"))
                    w.append(t, (new StringBuilder("the Thralls have ripped open the side of ")).append(c.hisHer()).append(" leotard, from ").append(c.hisHer()).append(" hip up to ").append(c.hisHer()).append(" ribcage.  ").toString());
                else
                if(bottomCover.equals("bodysuit"))
                    w.append(t, (new StringBuilder("the Thralls have torn a large hole in ")).append(c.hisHer()).append(" bodysuit, partially exposing ").append(c.hisHer()).append(" ass.  ").toString());
                else
                if(bottomCover.equals("armor"))
                    w.append(t, (new StringBuilder("the Thralls have pulled away one of ")).append(c.hisHer()).append(" armor plates, exposing the side of ").append(c.hisHer()).append(" ass.  ").toString());
                else
                if(bottomCover.equals("strips"))
                    w.append(t, (new StringBuilder("the Thralls have pulled apart enough of the strips of cloth covering ")).append(c.hisHer()).append(" lower body that part of ").append(c.hisHer()).append(" ass can be seen in the gaps between those that remain.  ").toString());
                else
                if(bottomCover.equals("belts"))
                    w.append(t, (new StringBuilder("the Thralls have snapped enough of the belts around ")).append(c.hisHer()).append(" lower body that part of ").append(c.hisHer()).append(" ass can be seen in the gaps between those that remain.  ").toString());
                else
                if(bottomCover.equals("shorts"))
                    w.append(t, (new StringBuilder("the Thralls have ripped ")).append(c.hisHer()).append(" shorts right down the middle, turning them into more of a skirt.  ").toString());
                else
                    w.append(t, (new StringBuilder("the Thralls have ripped ")).append(c.hisHer()).append(" ").append(bottomDesc).append(" all the way up to ").append(c.hisHer()).append(" hip.   ").toString());
                if(feetType.equals("none"))
                {
                    if(c.getINJULevel() < 1)
                        w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" does ").append(c.hisHer()).append(" best to squirm away and reduce the damage, but ").toString());
                    else
                    if(c.getINJULevel() > 1)
                    {
                        if(w.tickle().booleanValue())
                            w.append(t, (new StringBuilder("Flustered and demoralized, there's nothing ")).append(c.heShe()).append(" can do to resist the stripping, and ").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" bloodied body is in no state to resist the stripping, and ").toString());
                    } else
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder("The effects of exhaustion are starting to set in, slowing ")).append(c.hisHer()).append(" efforts to resist the stripping, and ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" bruised body is in no state to resist the stripping, and ").toString());
                    if(c.getPLEALevel() < 1)
                        w.append(t, (new StringBuilder(String.valueOf(c.hisHer()))).append(" clothes are much less durable than ").append(c.hisHer()).append(" flesh.").toString());
                    else
                    if(c.getPLEALevel() > 1)
                        w.append(t, (new StringBuilder(String.valueOf(c.heShe()))).append(" can't help but become even more turned on by the fact that ").append(c.heShe()).append("'s being stripped.").toString());
                    else
                        w.append(t, (new StringBuilder("the realization that ")).append(c.heShe()).append("'s showing off so much more skin than ").append(c.heShe()).append("'s comfortable with makes ").append(c.himHer()).append(" blush even more deeply.").toString());
                } else
                {
                    if(c.getINJULevel() < 1)
                        w.append(t, (new StringBuilder("Then, they pull off ")).append(c.hisHer()).append(" ").append(feetType).toString());
                    else
                    if(c.getINJULevel() > 1)
                    {
                        if(w.tickle().booleanValue())
                            w.append(t, (new StringBuilder("Then, while ")).append(c.heShe()).append("'s surprised and flustered, they pull off ").append(c.hisHer()).append(" ").append(feetType).append(" too").toString());
                        else
                            w.append(t, (new StringBuilder("Bruised and bloodied as ")).append(c.heShe()).append(" is, they have no trouble pulling off ").append(c.hisHer()).append(" ").append(feetType).append(" too").toString());
                    } else
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder("With the effects of exhaustion starting to set in, ")).append(c.heShe()).append("'s too slow to stop them from pulling off ").append(c.hisHer()).append(" ").append(feetType).append(" as well").toString());
                    else
                        w.append(t, (new StringBuilder("With bruises accumulating on ")).append(c.hisHer()).append(" body, ").append(c.heShe()).append("'s too slow to stop them from pulling off ").append(c.hisHer()).append(" ").append(feetType).append(" as well").toString());
                    if(c.getPLEALevel() < 1)
                        w.append(t, (new StringBuilder(", leaving ")).append(c.hisHer()).append(" feet bare.").toString());
                    else
                    if(c.getPLEALevel() > 1)
                        w.append(t, (new StringBuilder(", taking advantage of how turned on and distracted ")).append(c.heShe()).append(" is.").toString());
                    else
                        w.append(t, (new StringBuilder(", and when ")).append(c.heShe()).append(" realizes that ").append(c.hisHer()).append(" legs are completely exposed now, ").append(c.heShe()).append(" can't help but be a little turned on by the thought of what could happen next.").toString());
                }
            } else
            if(c.getEXPOLevel() == 2)
            {
                if(topCover.equals("blouse"))
                    w.append(t, (new StringBuilder("The Thralls have torn ")).append(mainName).append("'s blouse open down the front").toString());
                else
                if(topCover.equals("bodice"))
                    w.append(t, (new StringBuilder("The Thralls have torn apart ")).append(mainName).append("'s bodice").toString());
                else
                if(topCover.equals("cloak"))
                    w.append(t, (new StringBuilder("The Thralls have torn ")).append(mainName).append("'s cloak in half").toString());
                else
                if(topCover.equals("robe"))
                    w.append(t, (new StringBuilder("The Thralls have ripped open ")).append(mainName).append("'s robe").toString());
                else
                if(topCover.equals("jacket"))
                    w.append(t, (new StringBuilder("The Thralls have ripped away the front of ")).append(mainName).append("'s jacket").toString());
                else
                if(topCover.equals("shirt"))
                    w.append(t, (new StringBuilder("The Thralls have torn ")).append(mainName).append("'s shirt down off ").append(c.hisHer()).append(" shoulder").toString());
                else
                if(topCover.equals("strips"))
                    w.append(t, (new StringBuilder("The Thralls have torn apart the strips of cloth covering ")).append(mainName).append("'s chest").toString());
                else
                if(topCover.equals("crop"))
                    w.append(t, (new StringBuilder("The Thralls have torn ")).append(mainName).append("'s crop top in half").toString());
                else
                if(topCover.equals("bindings"))
                    w.append(t, (new StringBuilder("The Thralls have ripped ")).append(mainName).append("'s chest bindings").toString());
                else
                if(topCover.equals("belts"))
                    w.append(t, (new StringBuilder("The Thralls have snapped the belts covering ")).append(mainName).append("'s chest").toString());
                else
                if(topCover.equals("leotard"))
                    w.append(t, (new StringBuilder("The Thralls have ripped away the front of ")).append(mainName).append("'s leotard").toString());
                else
                if(topCover.equals("armor"))
                    w.append(t, (new StringBuilder("The Thralls have broken the clasps holding ")).append(mainName).append("'s armor closed").toString());
                else
                if(topCover.equals("bodysuit"))
                    w.append(t, (new StringBuilder("The Thralls have torn open the front of ")).append(mainName).append("'s bodysuit").toString());
                else
                if(topCover.equals(bottomCover))
                    w.append(t, (new StringBuilder("The Thralls have torn open the top of ")).append(mainName).append("'s ").append(topDesc).toString());
                else
                    w.append(t, (new StringBuilder("The Thralls have torn open ")).append(mainName).append("'s ").append(topDesc).toString());
                if(c.getINJULevel() < 2)
                {
                    if(c.getGender().equals("male"))
                        w.append(t, (new StringBuilder(" so that ")).append(c.hisHer()).append(" smooth chest is exposed.  ").toString());
                    else
                        w.append(t, (new StringBuilder(" so that ")).append(c.heShe()).append(" needs to devote one hand to keeping everything covered.  ").toString());
                } else
                if(c.getINJULevel() > 2)
                {
                    if(aVirg.booleanValue())
                    {
                        if(w.tickle().booleanValue())
                            w.append(t, (new StringBuilder(", and when they see that ")).append(c.heShe()).append("'s exhausted enough to have a hard time covering ").append(c.himHer()).append("self to compensate, they grin darkly and prepare to punish ").append(c.himHer()).append(" even more.  ").toString());
                        else
                            w.append(t, (new StringBuilder(", and when they see that ")).append(c.heShe()).append("'s badly-hurt enough to have a hard time covering ").append(c.himHer()).append("self to compensate, they grin darkly and prepare to punish ").append(c.himHer()).append(" even more.  ").toString());
                    } else
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder(", making it even easier to tickle under ")).append(c.hisHer()).append(" armpits.  ").toString());
                    else
                    if(c.getGender().equals("male"))
                        w.append(t, (new StringBuilder(", allowing them to pinch and twist ")).append(c.hisHer()).append(" nipples and ").append(c.hisHer()).append(" penis at the same time.  ").toString());
                    else
                        w.append(t, (new StringBuilder(", allowing the Thrall fucking ")).append(c.himHer()).append(" up the ass to pinch and twist ").append(c.hisHer()).append(" nipples at the same time.  ").toString());
                } else
                if(c.getGender().equals("male"))
                {
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder(", and with how worried ")).append(c.heShe()).append(" is by the direction the battle is going, ").append(c.heShe()).append(" can't even spare the effort of covering ").append(c.hisHer()).append(" smooth chest.  ").toString());
                    else
                        w.append(t, (new StringBuilder(", and in ")).append(c.hisHer()).append(" injured state, ").append(c.heShe()).append(" doesn't care at all about having ").append(c.hisHer()).append(" smooth chest exposed.  ").toString());
                } else
                if(w.tickle().booleanValue())
                    w.append(t, (new StringBuilder(", and there's obvious tiredness in ")).append(c.hisHer()).append(" expression as ").append(c.heShe()).append(" uses one arm to keep everything covered.  ").toString());
                else
                    w.append(t, (new StringBuilder(", and ")).append(c.heShe()).append(" has to use one bloodied arm to keep everything covered.  ").toString());
                if(c.getPLEALevel() < 2)
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" frowns with exertion").toString());
                else
                if(c.getPLEALevel() > 2)
                {
                    if(cVirg.booleanValue())
                    {
                        if(c.getGender().equals("male"))
                            w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" legs wobble as ").append(c.heShe()).append(" suppresses ").append(c.hisHer()).append(" arousal").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" breasts heave as ").append(c.heShe()).append(" grunts and moans with arousal").toString());
                    } else
                    if(c.getGender().equals("female"))
                        w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" juices soak ").append(c.hisHer()).append(" ").append(bottomDesc).append(" as ").append(c.heShe()).append(" endures ").append(c.hisHer()).append(" orgasm").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" spurting cock is barely contained within ").append(c.hisHer()).append(" torn ").append(bottomDesc).append(" as ").append(c.heShe()).append(" endures ").append(c.hisHer()).append(" orgasm").toString());
                } else
                if(c.getGender().equals("male"))
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" erection remains hidden as ").append(c.heShe()).append(" struggles with ").append(c.hisHer()).append(" arousal").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" breasts heave as ").append(c.heShe()).append(" gasps with arousal").toString());
                if(c.getHATELevel() < 2)
                    w.append(t, " and tries to focus.");
                else
                if(c.getHATELevel() > 2)
                {
                    if(vVirg.booleanValue())
                        w.append(t, (new StringBuilder(", keenly aware that ")).append(c.hisHer()).append(" Sexual Barrier has broken.").toString());
                    else
                        w.append(t, (new StringBuilder(" and the sensation of the shaft thrusting in and out of ")).append(c.hisHer()).append(" ").append(hole).append(".").toString());
                } else
                {
                    w.append(t, " and anger.");
                }
            } else
            if(c.getEXPOLevel() == 3)
            {
                if(underType.equals("none"))
                {
                    if(c.getGender().equals("female"))
                        w.append(t, (new StringBuilder("The Thralls have torn away the front of ")).append(mainName).append("'s ").append(bottomDesc).append(" so that ").append(c.hisHer()).append(" bare pussy is blatantly exposed.  ").append(c.HeShe()).append(" tries to cover ").append(c.himHer()).append("self, but they pull apart ").append(c.hisHer()).append(" ").toString());
                    else
                    if(c.getGender().equals("male"))
                        w.append(t, (new StringBuilder("The Thralls have torn away the front of ")).append(mainName).append("'s ").append(bottomDesc).append(" so that ").append(c.hisHer()).append(" bare penis is blatantly exposed.  ").append(c.HeShe()).append(" tries to cover ").append(c.himHer()).append("self, but they pull apart ").append(c.hisHer()).append(" ").toString());
                    else
                        w.append(t, (new StringBuilder("The Thralls have torn away the front of ")).append(mainName).append("'s ").append(bottomDesc).append(" so that ").append(c.hisHer()).append(" penis and pussy are both blatantly exposed.  ").append(c.HeShe()).append(" tries to cover ").append(c.himHer()).append("self, but they pull apart ").append(c.hisHer()).append(" ").toString());
                } else
                {
                    if(underType.equals("panties") || underType.equals("wrap"))
                        w.append(t, (new StringBuilder("The Thralls have pulled ")).append(mainName).append("'s panties ").toString());
                    else
                    if(underType.equals("g-string"))
                        w.append(t, (new StringBuilder("The Thralls have pulled the remains of ")).append(mainName).append("'s g-string ").toString());
                    else
                    if(underType.equals("shorts"))
                        w.append(t, (new StringBuilder("The Thralls have shredded ")).append(mainName).append("'s shorts and pulled them ").toString());
                    else
                    if(underType.equals("straps"))
                        w.append(t, (new StringBuilder("The Thralls have snapped ")).append(mainName).append("'s underharness and pulled the straps ").toString());
                    if(bottomAccess.equals("front") || bottomAccess.equals("cutout") || bottomAccess.equals("into") || bottomAccess.equals("around"))
                        w.append(t, (new StringBuilder("apart, removing them from under ")).append(c.hisHer()).append(" torn ").append(bottomDesc).append(".  Next, they forcibly spread ").append(c.hisHer()).append(" ").toString());
                    else
                    if(bottomAccess.equals("top"))
                        w.append(t, (new StringBuilder("apart, stripping ")).append(c.himHer()).append(" through ").append(c.hisHer()).append(" torn ").append(bottomDesc).append(".  Next, they forcibly spread ").append(c.hisHer()).append(" ").toString());
                    else
                        w.append(t, (new StringBuilder("down ")).append(c.hisHer()).append(" ").toString());
                }
                if(c.getPLEALevel() < 3)
                    w.append(t, "thighs.  ");
                else
                if(c.getPLEALevel() > 3 && cVirg.booleanValue())
                    w.append(t, "thighs as they uncontrollably spasm with pleasure.  ");
                else
                if(cVirg.booleanValue())
                {
                    if(c.getGender().equals("female"))
                        w.append(t, (new StringBuilder("thighs, driven wild by the way that ")).append(c.hisHer()).append(" flowing love juices signal ").append(c.hisHer()).append(" readiness for orgasm.  ").toString());
                    else
                        w.append(t, (new StringBuilder("thighs, driven wild by the way that ")).append(c.hisHer()).append(" erection springs free.  ").toString());
                } else
                if(c.getGender().equals("female"))
                    w.append(t, (new StringBuilder("thighs, driven wild by the clear view of the juices streaming from ")).append(c.hisHer()).append(" orgasmically clenching pussy.  ").toString());
                else
                    w.append(t, (new StringBuilder("thighs, driven wild by the clear view of ")).append(c.hisHer()).append(" repeatedly spurting cock.  ").toString());
                if(!modest.booleanValue())
                    w.append(t, (new StringBuilder("Cameras flash from all directions as the spectators eagerly capture ")).append(c.hisHer()).append(" most recent humiliation.  ").toString());
                if(c.getINJULevel() < 3)
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" struggles against them").toString());
                else
                if(c.getINJULevel() > 3 && aVirg.booleanValue())
                {
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" spasms wildly, wasting the last of ").append(c.hisHer()).append(" energy").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" shrieks in pain as ").append(c.hisHer()).append(" shattered limbs are manhandled").toString());
                } else
                if(aVirg.booleanValue())
                {
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" can't hold back ").append(c.hisHer()).append(" desperate moans").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" cries out in pain as ").append(c.hisHer()).append(" weakened body is prepared for even more extreme abuses").toString());
                } else
                if(w.tickle().booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" can't hold back ").append(c.hisHer()).append(" exhausted gasps of laughter as ").append(c.heShe()).append("'s tickled").toString());
                else
                if(c.getGender().equals("male"))
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" can't hold back ").append(c.hisHer()).append(" squeals of pain as ").append(c.heShe()).append(" feels the Thralls' boots directly on the sensitive skin of ").append(c.hisHer()).append(" penis").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" can't hold back ").append(c.hisHer()).append(" groans of intense discomfort as ").append(c.heShe()).append("'s fucked up the ass").toString());
                if(c.getHATELevel() < 3)
                    w.append(t, (new StringBuilder(", now protected only by the magic of ")).append(c.hisHer()).append(" Sexual Barrier.").toString());
                else
                if(c.getHATELevel() > 3 && vVirg.booleanValue())
                    w.append(t, ", cursing and sobbing with rage.");
                else
                if(vVirg.booleanValue())
                    w.append(t, (new StringBuilder(", completely defenseless now that ")).append(c.hisHer()).append(" Sexual Barrier has also failed.").toString());
                else
                    w.append(t, (new StringBuilder(", ")).append(c.hisHer()).append(" voice rising even higher as another Thrall penetrates ").append(c.hisHer()).append(" ").append(hole).append(".").toString());
            } else
            if(c.getEXPOLevel() == 4)
            {
                if(c.getPLEALevel() < 4)
                {
                    if(c.getGender().equals("male"))
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s chest is completely exposed, ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s breasts are completely exposed, ").toString());
                } else
                if(c.getPLEALevel() > 4)
                {
                    if(c.getGender().equals("male"))
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s bare chest heaves with ").append(c.hisHer()).append(" uncontrollable screams of pleasure, ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s bare breasts heave with ").append(c.hisHer()).append(" uncontrollable screams of pleasure, ").toString());
                } else
                if(c.getGender().equals("male"))
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s orgasmic spasms emphasize ").append(c.hisHer()).append(" bare chest and fully erect nipples, ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s orgasmic spasms emphasize ").append(c.hisHer()).append(" bare breasts and fully erect nipples, ").toString());
                if(bottomCover.equals("skirt"))
                {
                    if(c.getGender().equals("female"))
                        w.append(t, (new StringBuilder("while the remains of the skirt flapping around ")).append(c.hisHer()).append(" hips are too short to conceal ").append(c.hisHer()).append(" pussy.  ").toString());
                    else
                    if(c.getGender().equals("male"))
                        w.append(t, (new StringBuilder("while the remains of the skirt flapping around ")).append(c.hisHer()).append(" hips are too short to conceal ").append(c.hisHer()).append(" penis.  ").toString());
                    else
                        w.append(t, (new StringBuilder("while the remains of the skirt flapping around ")).append(c.hisHer()).append(" hips are too short to conceal ").append(c.hisHer()).append(" penis and pussy.  ").toString());
                } else
                if(bottomCover.equals("miniskirt"))
                    w.append(t, (new StringBuilder("while ")).append(c.hisHer()).append(" miniskirt has been reduced to nothing more than a belt of material around ").append(c.hisHer()).append(" waist.  ").toString());
                else
                if(bottomCover.equals("robe"))
                    w.append(t, (new StringBuilder("while ")).append(c.hisHer()).append(" robe has been torn to the point that it doesn't even reach past ").append(c.hisHer()).append(" waist.  ").toString());
                else
                if(bottomCover.equals("cloak"))
                    w.append(t, (new StringBuilder("while ")).append(c.hisHer()).append(" cloak has been torn to the point that it doesn't even reach past ").append(c.hisHer()).append(" waist.  ").toString());
                else
                if(bottomCover.equals("trousers"))
                    w.append(t, (new StringBuilder("while ")).append(c.hisHer()).append(" trousers have been shredded to the point that only a few scraps around ").append(c.hisHer()).append(" legs remain.  ").toString());
                else
                if(bottomCover.equals("leotard"))
                {
                    if(c.getGender().equals("female"))
                        w.append(t, (new StringBuilder("while the remaining scraps of ")).append(c.hisHer()).append(" leotard clinging to ").append(c.hisHer()).append(" body provide no coverage whatsoever for ").append(c.hisHer()).append(" hips and pussy.  ").toString());
                    else
                    if(c.getGender().equals("male"))
                        w.append(t, (new StringBuilder("while the remaining scraps of ")).append(c.hisHer()).append(" leotard clinging to ").append(c.hisHer()).append(" body provide no coverage whatsoever for ").append(c.hisHer()).append(" hips and penis.  ").toString());
                    else
                        w.append(t, (new StringBuilder("while the remaining scraps of ")).append(c.hisHer()).append(" leotard clinging to ").append(c.hisHer()).append(" body provide no coverage whatsoever for ").append(c.hisHer()).append(" penis and pussy.  ").toString());
                } else
                if(bottomCover.equals("bodysuit"))
                    w.append(t, (new StringBuilder("while the torso and crotch of ")).append(c.hisHer()).append(" bodysuit have been completely torn away.  ").toString());
                else
                if(bottomCover.equals("armor"))
                    w.append(t, (new StringBuilder("while the armor plates that would normally cover ")).append(c.hisHer()).append(" hips and crotch have been lost.  ").toString());
                else
                if(bottomCover.equals("strips"))
                    w.append(t, (new StringBuilder("while the strips of cloth that had been covering ")).append(c.hisHer()).append(" body have been completely removed save for a few dangling ends around ").append(c.hisHer()).append(" waist.  ").toString());
                else
                if(bottomCover.equals("belts"))
                    w.append(t, (new StringBuilder("while the belts that had been covering ")).append(c.hisHer()).append(" lower half have also been snapped and pulled away.  ").toString());
                else
                if(bottomCover.equals("shorts"))
                {
                    if(c.getGender().equals("female"))
                        w.append(t, (new StringBuilder("while ")).append(c.hisHer()).append(" shorts have been torn open over ").append(c.hisHer()).append(" pussy and ass.  ").toString());
                    else
                    if(c.getGender().equals("male"))
                        w.append(t, (new StringBuilder("while ")).append(c.hisHer()).append(" shorts have been torn open over ").append(c.hisHer()).append(" penis and ass.  ").toString());
                    else
                        w.append(t, (new StringBuilder("while ")).append(c.hisHer()).append(" shorts have been torn open over ").append(c.hisHer()).append(" pussy and penis.  ").toString());
                } else
                {
                    w.append(t, (new StringBuilder("while only a few useless scraps remain of ")).append(c.hisHer()).append(" ").append(bottomDesc).append(".  ").toString());
                }
                if(c.getINJULevel() < 4)
                    w.append(t, (new StringBuilder("With ")).append(c.hisHer()).append(" limbs splayed out, there's nothing ").append(c.heShe()).append(" can do to cover ").append(c.himHer()).append("self, ").toString());
                else
                if(c.getINJULevel() > 4)
                {
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" splayed-out form, gasping for breath, makes for a pathetic sight, ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" mortally-wounded body makes for a pathetic sight, ").toString());
                } else
                if(w.tickle().booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append("'s too worn out to even have the coordination to cover ").append(c.himHer()).append("self, ").toString());
                else
                    w.append(t, (new StringBuilder("With ")).append(c.hisHer()).append(" body shattered, ").append(c.heShe()).append(" has no strength to cover ").append(c.himHer()).append(" self, ").toString());
                if(c.getHATELevel() < 4)
                    w.append(t, (new StringBuilder("and ")).append(c.heShe()).append(" can only wait for the humiliation to end.").toString());
                else
                if(c.getHATELevel() > 4)
                    w.append(t, (new StringBuilder("and the Demonic energy erupting from ")).append(c.hisHer()).append(" body isn't opaque enough to provide any modesty.").toString());
                else
                    w.append(t, (new StringBuilder("and ")).append(c.hisHer()).append(" impotent screams of rage only make ").append(c.himHer()).append(" look even weaker.").toString());
            } else
            {
                w.append(t, (new StringBuilder("Removing the remaining scraps of ")).append(mainName).append("'s ").append(bottomDesc).append(" is only a formality, and the Thralls focus more on forcing ").append(c.hisHer()).append(" body into embarrassing positions.").toString());
            }
            w.append(t, "\n\n");
        } else
        if(highestPriority == 1)
        {
            if(c.getPLEALevel() == 1)
            {
                if(c.getHATELevel() < 1)
                    w.append(t, "Despite the dire situation, ");
                else
                if(c.getHATELevel() > 1)
                    w.append(t, (new StringBuilder("Despite ")).append(c.hisHer()).append(" growing fury, ").toString());
                else
                    w.append(t, (new StringBuilder("Despite ")).append(c.hisHer()).append(" growing annoyance, ").toString());
                if(innocence > 66)
                    w.append(t, (new StringBuilder("the things the Thralls are doing to ")).append(mainName).append(" make ").append(c.himHer()).append(" feel nice enough to start blushing and squirming.  ").toString());
                else
                if(innocence > 33)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" feels ").append(c.hisHer()).append(" face grow warm as the Thralls' stimulation starts to have an effect.  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" can't deny that the Thralls' sexual stimulation is starting to have an effect on ").append(c.himHer()).append(".  ").toString());
                if(c.getEXPOLevel() < 1)
                    w.append(t, (new StringBuilder("Their fingers are able to work their way in under ")).append(c.hisHer()).append(" clothes, and ").toString());
                else
                if(c.getEXPOLevel() > 1)
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" need to cover ").append(c.himHer()).append("self means ").append(c.heShe()).append(" can't ignore how exposed ").append(c.heShe()).append(" is, and ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" can feel the way the bystanders are staring at ").append(c.hisHer()).append(" legs exposed by ").append(c.hisHer()).append(" torn ").append(bottomDesc).append(", and ").toString());
                if(c.getINJULevel() < 1)
                    w.append(t, (new StringBuilder("no matter how hard ")).append(c.heShe()).append(" fights, it's impossible to completely fend off the pleasure.").toString());
                else
                if(c.getINJULevel() > 1)
                {
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder(String.valueOf(c.heShe()))).append("'s far too exhausted to fend off the assault.").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(c.heShe()))).append("'s far too badly hurt to fend off the assault.").toString());
                } else
                if(w.tickle().booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(c.hisHer()))).append(" growing tiredness and distraction prevent ").append(c.himHer()).append(" from fending off the assault.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.hisHer()))).append(" bruised limbs lack the strength to completely fend off the assault.").toString());
            } else
            if(c.getPLEALevel() == 2)
            {
                if(c.getINJULevel() < 2)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s gasps of exertion grow thick with pleasure ").toString());
                else
                if(c.getINJULevel() > 2)
                {
                    if(aVirg.booleanValue())
                    {
                        if(w.tickle().booleanValue())
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" moans helplessly ").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" cries out in pain as the Thralls manhandle ").append(c.himHer()).append(", but there's pleasure in ").append(c.hisHer()).append(" voice too ").toString());
                    } else
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder("The tickling, groping hands make ")).append(mainName).append(" laugh helplessly ").toString());
                    else
                    if(c.getGender().equals("male"))
                        w.append(t, (new StringBuilder("The Thrall's boot grinding back and forth on ")).append(mainName).append("'s penis makes ").append(c.himHer()).append(" moan in mingled pleasure and pain ").toString());
                    else
                        w.append(t, (new StringBuilder("The cock thrusting into ")).append(mainName).append("'s ass makes ").append(c.himHer()).append(" moan in mingled pleasure and pain ").toString());
                } else
                if(w.tickle().booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is barely able to suppress ").append(c.hisHer()).append(" moans of pleasure ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s grunts of pain turn into moans of pleasure ").toString());
                if(innocence > 66)
                    w.append(t, (new StringBuilder("as ")).append(c.heShe()).append(" struggles to understand what's happening to ").append(c.himHer()).append(".  ").toString());
                else
                if(innocence > 33)
                    w.append(t, (new StringBuilder("as ")).append(c.heShe()).append(" starts to give in to the sensations assaulting ").append(c.himHer()).append(".  ").toString());
                else
                    w.append(t, (new StringBuilder("as ")).append(c.heShe()).append(" begins to eagerly anticipate the stimulation despite ").append(c.himHer()).append("self.  ").toString());
                if(c.getEXPOLevel() < 2)
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" can only defend ").append(c.himHer()).append("self as best ").append(c.heShe()).append(" can").toString());
                else
                if(c.getEXPOLevel() > 2)
                {
                    if(modest.booleanValue())
                        w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" can only desperately try to cover ").append(c.hisHer()).append(" exposed body").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" can only look back at the cameras zooming in on ").append(c.hisHer()).append(" bare crotch").toString());
                } else
                {
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" can only tightly clutch ").append(c.hisHer()).append(" torn ").append(topDesc).append(" to ").append(c.hisHer()).append(" chest").toString());
                }
                if(c.getHATELevel() < 2)
                    w.append(t, " and try to stay calm.");
                else
                if(c.getHATELevel() > 2)
                {
                    if(vVirg.booleanValue())
                        w.append(t, (new StringBuilder(" and try ")).append(c.hisHer()).append(" best not to think about how ").append(c.hisHer()).append(" Sexual Barrier has come undone.").toString());
                    else
                        w.append(t, (new StringBuilder(" and endure having ")).append(c.hisHer()).append(" ").append(organ).append(" played with as one of the Thralls thrusts into ").append(c.hisHer()).append(" ").append(hole).append(" at the same time.").toString());
                } else
                {
                    w.append(t, " and glare back at them with seething hatred.");
                }
            } else
            if(c.getPLEALevel() == 3)
            {
                if(c.getEXPOLevel() < 3)
                    w.append(t, (new StringBuilder("As the battle rages around ")).append(mainName).toString());
                else
                if(c.getEXPOLevel() > 3 && modest.booleanValue())
                    w.append(t, (new StringBuilder("With ")).append(mainName).append("'s clothes essentially stripped from ").append(c.hisHer()).append(" body").toString());
                else
                if(modest.booleanValue())
                    w.append(t, (new StringBuilder("With ")).append(mainName).append("'s shredded ").append(bottomDesc).append(" and lack of panties").toString());
                else
                    w.append(t, (new StringBuilder("With ")).append(mainName).append(" stripped and being filmed from every angle").toString());
                if(cVirg.booleanValue())
                {
                    if(innocence > 66)
                    {
                        if(!c.getGender().equals("female"))
                            w.append(t, (new StringBuilder(", everyone else can see that ")).append(c.heShe()).append(" has an erection, even if ").append(mainName).append(" ").append(c.himHer()).append("self doesn't realize it.  ").toString());
                        else
                            w.append(t, (new StringBuilder(", everyone else can see the love juices dripping down ")).append(c.hisHer()).append(" thighs, even if ").append(mainName).append(" ").append(c.himHer()).append("self doesn't realize it.  ").toString());
                    } else
                    if(innocence > 33)
                    {
                        if(!c.getGender().equals("female"))
                            w.append(t, (new StringBuilder(", it's obvious that ")).append(c.heShe()).append(" has an erection.  ").toString());
                        else
                            w.append(t, (new StringBuilder(", it's obvious that ")).append(c.hisHer()).append(" thighs are slick with ").append(c.hisHer()).append(" fluids.  ").toString());
                    } else
                    if(!c.getGender().equals("female"))
                        w.append(t, (new StringBuilder(", ")).append(c.heShe()).append(" is fully aware that ").append(c.hisHer()).append(" shameful erection is visible.  ").toString());
                    else
                        w.append(t, (new StringBuilder(", ")).append(c.heShe()).append(" is fully aware that shameful secretions are dripping down ").append(c.hisHer()).append(" thighs.  ").toString());
                } else
                if(innocence > 66)
                {
                    if(c.getGender().equals("female"))
                        w.append(t, (new StringBuilder(", everyone else can see that ")).append(c.heShe()).append("'s cumming hard, because ").append(c.heShe()).append("'s too delirious with pleasure to hide it.  ").toString());
                    else
                        w.append(t, (new StringBuilder(", everyone can see the cum spurting from ")).append(c.hisHer()).append(" penis, even if ").append(c.heShe()).append("'s too caught up in how good it feels to realize what a scene ").append(c.heShe()).append("'s making.  ").toString());
                } else
                if(innocence > 33)
                {
                    if(c.getGender().equals("female"))
                        w.append(t, (new StringBuilder(", it's obvious that ")).append(c.heShe()).append("'s cumming.  ").toString());
                    else
                        w.append(t, (new StringBuilder(", ")).append(c.hisHer()).append(" spurting cock makes it obvious that ").append(c.heShe()).append("'s cumming.  ").toString());
                } else
                if(c.getGender().equals("female"))
                    w.append(t, (new StringBuilder(", ")).append(c.heShe()).append("'s fully aware that the spectators will likely realize that ").append(c.heShe()).append("'s orgasming.  ").toString());
                else
                    w.append(t, (new StringBuilder(", ")).append(c.heShe()).append("'s fully aware that ").append(c.hisHer()).append(" spurting cock is revealing how much pleasure ").append(c.heShe()).append("'s feeling right now.  ").toString());
                if(c.getHATELevel() < 3)
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" effort to stay focused ").toString());
                else
                if(c.getHATELevel() > 3 && vVirg.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" shrieking rage ").toString());
                else
                if(vVirg.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" violent anger ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" sense of violation over the Thralls penetrating ").append(c.hisHer()).append(" ").append(hole).append(" ").toString());
                if(c.getINJULevel() < 3)
                    w.append(t, "isn't enough to stop the pleasure from growing stronger and stronger.");
                else
                if(c.getINJULevel() > 3 && aVirg.booleanValue())
                {
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder("is completely irrelevant in the face of ")).append(c.hisHer()).append(" extreme exhaustion.").toString());
                    else
                        w.append(t, "and broken bones aren't enough to drown out the pleasure.");
                } else
                if(aVirg.booleanValue())
                {
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder("isn't enough to stop ")).append(c.hisHer()).append(" breathless moans from coming out.").toString());
                    else
                        w.append(t, (new StringBuilder("isn't enough to stop the pain of ")).append(c.hisHer()).append(" injuries from being overwhelmed by surging pleasure.").toString());
                } else
                if(w.tickle().booleanValue())
                    w.append(t, (new StringBuilder("isn't enough to stop ")).append(c.hisHer()).append(" laughter from growing whiny and desperate as the pleasure mounts.").toString());
                else
                if(c.getGender().equals("male"))
                    w.append(t, (new StringBuilder("isn't enough to stop the painful crushing of ")).append(c.hisHer()).append(" penis and balls from giving ").append(c.himHer()).append(" a masochistic pleasure.").toString());
                else
                    w.append(t, (new StringBuilder("isn't enough to stop the cock up ")).append(c.hisHer()).append(" butt from awakening even more shameful pleasure in ").append(c.himHer()).append(".").toString());
            } else
            if(c.getPLEALevel() == 4)
            {
                if(innocence > 66)
                {
                    if(c.getGender().equals("female"))
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is feeling so good that it's starting to scare ").append(c.himHer()).append(", ").append(c.hisHer()).append(" body spasming against ").append(c.hisHer()).append(" will").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is feeling so good that it's starting to scare ").append(c.himHer()).append(", ").append(c.hisHer()).append(" penis dribbling a constant stream of cum").toString());
                } else
                if(innocence > 33)
                {
                    if(c.getGender().equals("female"))
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s body spasms at the overwhelming pleasure").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s penis releases a constant dribble of cum as ").append(c.heShe()).append(" spasms in overwhelming pleasure").toString());
                } else
                if(c.getGender().equals("female"))
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" squeezes ").append(c.hisHer()).append(" eyes shut, but ").append(c.heShe()).append(" can't ignore the way that ").append(c.hisHer()).append(" body is spasming against ").append(c.hisHer()).append(" will").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" squeezes ").append(c.hisHer()).append(" eyes shut, but ").append(c.heShe()).append(" can't ignore the way that ").append(c.hisHer()).append(" twitching penis is dribbling cum in a constant debilitating climax").toString());
                if(c.getINJULevel() < 4)
                    w.append(t, (new StringBuilder(", interfering with ")).append(c.hisHer()).append(" attempts to escape.  ").toString());
                else
                if(c.getINJULevel() > 4)
                {
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder(", even as ")).append(c.heShe()).append(" almost blacks out from exhaustion.  ").toString());
                    else
                        w.append(t, (new StringBuilder(", the sensations somehow bypassing the missing nerves from ")).append(c.hisHer()).append(" moral wounds.  ").toString());
                } else
                if(w.tickle().booleanValue())
                    w.append(t, (new StringBuilder(", ")).append(c.hisHer()).append(" movements weak and uncoordinated with exhaustion.  ").toString());
                else
                    w.append(t, (new StringBuilder(", each movement causing a spike of pain to shoot through ")).append(c.hisHer()).append(" shattered limbs.  ").toString());
                if(c.getHATELevel() < 4)
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" can't think about anything except orgasm").toString());
                else
                if(c.getHATELevel() > 4)
                    w.append(t, (new StringBuilder("Demonic energy erupts from ")).append(c.himHer()).append(" with every movement").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" shrieks of rage contrast sharply with ").append(c.hisHer()).append(" erotic movements").toString());
                if(c.getEXPOLevel() < 4)
                    w.append(t, (new StringBuilder(", ")).append(c.hisHer()).append(" body gyrating on instinct.").toString());
                else
                if(c.getEXPOLevel() > 4)
                    w.append(t, (new StringBuilder(", ")).append(c.hisHer()).append(" naked body gyrating on instinct.").toString());
                else
                    w.append(t, (new StringBuilder(", the scraps of ")).append(c.hisHer()).append(" ").append(bottomDesc).append(" framing ").append(c.hisHer()).append(" shape as ").append(c.heShe()).append(" gyrates on pure instinct.").toString());
            } else
            {
                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" screams as ").append(c.hisHer()).append(" pleasure reaches a new peak.  ").append(c.HisHer()).append(" whole body feels like one erogenous zone, and the Thralls' abuses feel far, far better than they should.").toString());
            }
            w.append(t, "\n\n");
        } else
        if(highestPriority == 2)
        {
            if(c.getINJULevel() == 1)
            {
                if(c.getEXPOLevel() < 1)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is still trying to put on a brave face, but ").toString());
                else
                if(c.getEXPOLevel() > 1)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s clothes are badly torn and ").toString());
                else
                if(w.tickle().booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s bare feet are being ruthlessly exploited by the Thralls, and ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" has had ").append(c.hisHer()).append(" ").append(bottomDesc).append(" torn off ").append(c.hisHer()).append(" legs, and ").toString());
                if(w.tickle().booleanValue())
                {
                    if(confidence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(c.heShe()))).append("'s forced to acknowledge that the tickling is starting to affect ").append(c.himHer()).append(".  ").toString());
                    else
                    if(confidence > 33)
                        w.append(t, (new StringBuilder(String.valueOf(c.hisHer()))).append(" face wears a strained expression from being forced to endure the tickling.  ").toString());
                    else
                        w.append(t, (new StringBuilder("the tickling has started to affect ")).append(c.himHer()).append(".  ").append(c.HeShe()).append("'s afraid of just how much worse it can get.  ").toString());
                } else
                if(confidence > 66)
                    w.append(t, (new StringBuilder("the bruises covering ")).append(c.hisHer()).append(" body force ").append(c.himHer()).append(" to acknowledge that ").append(c.heShe()).append("'s getting hurt.  ").toString());
                else
                if(confidence > 33)
                    w.append(t, (new StringBuilder(String.valueOf(c.hisHer()))).append(" confidence has been shaken by the bruises covering ").append(c.hisHer()).append(" body.  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.heShe()))).append(" can't stifle ").append(c.hisHer()).append(" whimpers of pain nor cover the bruises across ").append(c.hisHer()).append(" body.  ").toString());
                if(c.getHATELevel() < 1)
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append("'s trying to remain calm and focused, but ").toString());
                else
                if(c.getHATELevel() > 1)
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" sense of hatred, unsuitable for one of the Chosen, has weakened ").append(c.hisHer()).append(" magical defenses, and ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" growing annoyance has disturbed ").append(c.hisHer()).append(" mental state and weakened ").append(c.hisHer()).append(" magical defenses, and ").toString());
                if(c.getPLEALevel() < 1)
                    w.append(t, (new StringBuilder(String.valueOf(c.heShe()))).append(" can't hold up against this kind of abuse forever.").toString());
                else
                if(c.getPLEALevel() > 1)
                    w.append(t, (new StringBuilder("the pleasure inflicted on ")).append(c.himHer()).append(" has rendered ").append(c.hisHer()).append(" nerves oversensitive and vulnerable.").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.hisHer()))).append(" skin, flush with pleasure, feels especially sensitive.").toString());
            } else
            if(c.getINJULevel() == 2)
            {
                if(w.tickle().booleanValue())
                {
                    if(c.getPLEALevel() < 2)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" reels").toString());
                    else
                    if(c.getPLEALevel() > 2)
                    {
                        if(cVirg.booleanValue())
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s near-orgasmic pleasure is interrupted by a spike of unpleasant stimulation").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s orgasm is interrupted by a spike of unpleasant stimulation").toString());
                    } else
                    {
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s gasping breaths of pleasure turn into a stifled squeak").toString());
                    }
                    if(confidence > 66)
                        w.append(t, (new StringBuilder(" as the tickling overwhelms ")).append(c.hisHer()).append(" willpower and starts to make ").append(c.himHer()).append(" flinch and twitch.  ").toString());
                    else
                    if(confidence > 33)
                        w.append(t, (new StringBuilder(" as the tickling starts to make ")).append(c.himHer()).append(" blatantly flinch and squirm.  ").toString());
                    else
                        w.append(t, (new StringBuilder(" as the tickling reaches the point that ")).append(c.heShe()).append(" can't help but flinch away from each touch.  ").toString());
                    if(c.getEXPOLevel() < 2)
                        w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" clothes are beginning to grow disheveled in the process").toString());
                    else
                    if(c.getEXPOLevel() > 2)
                    {
                        w.append(t, (new StringBuilder("The damage to ")).append(c.hisHer()).append(" clothes means that ").append(c.hisHer()).append(" reflexive movements threaten to expose everything").toString());
                        if(!modest.booleanValue())
                            w.append(t, " to the cameras");
                    } else
                    {
                        w.append(t, (new StringBuilder("The damage to ")).append(c.hisHer()).append(" ").append(topDesc).append(" has turned ").append(c.hisHer()).append(" armpits into a target").toString());
                    }
                } else
                {
                    if(c.getPLEALevel() < 2)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" reels").toString());
                    else
                    if(c.getPLEALevel() > 2)
                    {
                        if(cVirg.booleanValue())
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s near-orgasmic pleasure is interrupted by a spike of intense pain").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s orgasm is interrupted by a spike of intense pain").toString());
                    } else
                    {
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s gasping breaths of pleasure turn into cries of pain").toString());
                    }
                    if(confidence > 66)
                        w.append(t, (new StringBuilder(" as ")).append(c.hisHer()).append(" previous overconfidence is punished with attacks that start to draw blood.  ").toString());
                    else
                    if(confidence > 33)
                        w.append(t, " as the Thralls' attacks start to draw blood.  ");
                    else
                        w.append(t, (new StringBuilder(" as ")).append(c.hisHer()).append(" lack of self-confidence means that the Thralls' attacks are already starting to draw blood.  ").toString());
                    if(c.getEXPOLevel() < 2)
                        w.append(t, (new StringBuilder("Patches of red are beginning to spread across ")).append(c.hisHer()).append(" clothes").toString());
                    else
                    if(c.getEXPOLevel() > 2)
                    {
                        if(modest.booleanValue())
                            w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" clothes are too damaged to hide the wounds").toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" stripped, pitiful state is captured by the watching cameras").toString());
                    } else
                    {
                        w.append(t, (new StringBuilder("The ragged edges of ")).append(c.hisHer()).append(" ").append(topDesc).append(" are stained in red").toString());
                    }
                }
                if(c.getHATELevel() < 2)
                    w.append(t, (new StringBuilder(", and ")).append(c.heShe()).append("'ll only have a harder time defending ").append(c.himHer()).append("self from here.").toString());
                else
                if(c.getHATELevel() > 2)
                {
                    if(vVirg.booleanValue())
                        w.append(t, (new StringBuilder(", and with ")).append(c.hisHer()).append(" Sexual Barrier broken, ").append(c.heShe()).append("'s especially vulnerable.").toString());
                    else
                        w.append(t, (new StringBuilder(", and with one of the Thralls fucking ")).append(c.hisHer()).append(" ").append(hole).append(" even as ").append(c.heShe()).append("'s tortured, ").append(c.hisHer()).append(" defeat is undeniable.").toString());
                } else
                {
                    w.append(t, (new StringBuilder(", and a desire for vengeance smolders in ")).append(c.hisHer()).append(" eyes.").toString());
                }
            } else
            if(c.getINJULevel() == 3)
            {
                if(w.tickle().booleanValue())
                {
                    if(aVirg.booleanValue())
                    {
                        if(confidence > 66)
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" has been stubbornly concealing just how much all the tickling has been affecting ").append(c.himHer()).append(", but the Thralls are starting to notice ").append(c.hisHer()).append(" inability to completely suppress ").append(c.hisHer()).append(" voice").toString());
                        else
                        if(confidence > 33)
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s self-confidence has been completely destroyed by the way the Thralls are toying with ").append(c.himHer()).toString());
                        else
                            w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s voice comes out in small whimpers every time the Thralls tickle ").append(c.himHer()).append(".  ").append(c.HeShe()).append(" knows ").append(c.heShe()).append(" isn't strong enough to resist whatever they want to make ").append(c.himHer()).append(" do").toString());
                    } else
                    if(confidence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" has been stubbornly resisting the tickling so far, but now ").append(c.heShe()).append(" can't help but start laughing again").toString());
                    else
                    if(confidence > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" has been forced to laugh again by the Thralls' tickling").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s voice comes out in whimpering giggles every time the Thralls tickle ").append(c.himHer()).append(".  ").append(c.HeShe()).append(" knows ").append(c.heShe()).append(" never had a chance").toString());
                } else
                if(aVirg.booleanValue())
                {
                    if(confidence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" has been stubbornly concealing just how hurt ").append(c.heShe()).append(" is, but the Thralls can feel the way that ").append(c.hisHer()).append(" previously unyielding flesh is becoming soft under their fingers").toString());
                    else
                    if(confidence > 33)
                        w.append(t, (new StringBuilder("As the injuries accumulate, the Thralls feel ")).append(mainName).append("'s body becoming softer and more yielding").toString());
                    else
                        w.append(t, (new StringBuilder("The accumulating injuries destroy ")).append(mainName).append("'s self-confidence, causing ").append(c.hisHer()).append(" body to become more and more vulnerable to further abuses.  The Thralls notice how ").append(c.hisHer()).append(" skin seems to grow softer as ").append(c.hisHer()).append(" struggles weaken").toString());
                } else
                if(c.getGender().equals("male"))
                {
                    if(confidence > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" has been trying to ignore the pain of ").append(c.hisHer()).append(" injuries, but the constant pummeling and twisting to ").append(c.hisHer()).append(" testicles finally causes ").append(c.himHer()).append(" to scream out loud").toString());
                    else
                    if(confidence > 33)
                        w.append(t, (new StringBuilder("As the injuries accumulate, the Thralls take advantage of ")).append(mainName).append("'s weakening body by pummeling and twisting ").append(c.hisHer()).append(" sensitive testicles").toString());
                    else
                        w.append(t, (new StringBuilder("The accumulating injuries destroy ")).append(mainName).append("'s self-confidence, causing ").append(c.hisHer()).append(" sensitive testicles to become more vulnerable to the Thralls' pinching, twisting hands").toString());
                } else
                if(confidence > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" has been trying to ignore the pain of ").append(c.hisHer()).append(" injuries, but ").append(c.heShe()).append(" still screams out loud as one of the Thralls manages to force the tip of his cock past ").append(c.hisHer()).append(" anus").toString());
                else
                if(confidence > 33)
                    w.append(t, (new StringBuilder("As the injuries accumulate, the one of the Thralls takes advantage of ")).append(mainName).append("'s weakening body by forcing his cock into ").append(c.hisHer()).append(" ass").toString());
                else
                    w.append(t, (new StringBuilder("The accumulating injuries destroy ")).append(mainName).append("'s self-confidence, causing ").append(c.hisHer()).append(" body to become vulnerable enough that one of the Thralls is able to force his cock into ").append(c.hisHer()).append(" ass").toString());
                if(c.getHATELevel() < 3)
                    w.append(t, (new StringBuilder(", and even though ")).append(c.hisHer()).append(" Sexual Barrier remains intact, there are other ways for them to enjoy ").append(c.hisHer()).append(" body.  ").toString());
                else
                if(c.getHATELevel() > 3 && vVirg.booleanValue())
                    w.append(t, (new StringBuilder(", and ")).append(c.hisHer()).append(" sobs of rage do nothing to deter them from using ").append(c.himHer()).append(" as they please.  ").toString());
                else
                if(vVirg.booleanValue())
                {
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder(", and with ")).append(c.hisHer()).append(" Sexual Barrier broken, ").append(c.heShe()).append(" feels especially vulnerable.  ").toString());
                    else
                        w.append(t, (new StringBuilder(", and with ")).append(c.hisHer()).append(" Sexual Barrier broken, they have multiple ways they might enjoy that fact.  ").toString());
                } else
                {
                    w.append(t, (new StringBuilder(", and they find it easier than ever to penetrate ")).append(c.hisHer()).append(" ").append(hole).append(".  ").toString());
                }
                if(c.getEXPOLevel() < 3)
                    w.append(t, (new StringBuilder("They're tearing apart ")).append(c.hisHer()).append(" ").append(bottomDesc).append(" in the process").toString());
                else
                if(c.getEXPOLevel() > 3 && modest.booleanValue())
                    w.append(t, (new StringBuilder("They aren't hindered in the slightest by ")).append(c.hisHer()).append(" shredded ").append(bottomDesc).toString());
                else
                if(modest.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" lack of panties leaves ").append(c.himHer()).append(" especially vulnerable").toString());
                else
                    w.append(t, "The abuse is being filmed by countless cameras");
                if(c.getPLEALevel() < 3)
                    w.append(t, ".");
                else
                if(c.getPLEALevel() > 3 && cVirg.booleanValue())
                    w.append(t, (new StringBuilder(", and the pleasurable spasms in ")).append(c.hisHer()).append(" body intensify as ").append(c.heShe()).append(" can't help but anticipate what they're about to do.").toString());
                else
                if(cVirg.booleanValue())
                    w.append(t, (new StringBuilder(", and ")).append(c.hisHer()).append(" fluid-soaked thighs tremble with approaching orgasm.").toString());
                else
                    w.append(t, (new StringBuilder(", and ")).append(c.heShe()).append(" can't stop cumming as they stroke ").append(c.hisHer()).append(" ").append(organ).append(" at the same time.").toString());
            } else
            if(c.getINJULevel() == 4)
            {
                if(c.getHATELevel() < 4)
                {
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" cries out helplessly").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" cries out in pain").toString());
                } else
                if(c.getHATELevel() > 4)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" erupts with tendrils of Demonic energy").toString());
                else
                if(w.tickle().booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" shrieks in helpless rage").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" shrieks in rage and pain").toString());
                if(w.tickle().booleanValue())
                {
                    if(confidence > 66)
                        w.append(t, (new StringBuilder(", still thrashing wildly as ")).append(c.heShe()).append("'s tickled, but too exhausted for it to do ").append(c.himHer()).append(" any good.  ").toString());
                    else
                    if(confidence > 33)
                        w.append(t, ", completely fed up with the tickling but too exhausted to actually fight it.  ");
                    else
                        w.append(t, (new StringBuilder(", spasming weakly as ")).append(c.heShe()).append("'s tickled, but too exhausted and demoralized to put any strength in ").append(c.hisHer()).append(" limbs.  ").toString());
                    if(c.getPLEALevel() < 4)
                        w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" attempts to curl up and protect ").append(c.himHer()).append("self are completely useless").toString());
                    else
                    if(c.getPLEALevel() > 4)
                        w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" nerves have been corrupted to the point that even the lightest touches make ").append(c.himHer()).append(" move erratically").toString());
                    else
                        w.append(t, (new StringBuilder("Combined with ")).append(c.hisHer()).append(" sexual overstimulation, the attacks make ").append(c.hisHer()).append(" movements become completely uncontrolled").toString());
                } else
                {
                    if(confidence > 66)
                        w.append(t, (new StringBuilder(", struggling as much as ")).append(c.heShe()).append(" can as the Thralls mangle ").append(c.hisHer()).append(" body.  ").toString());
                    else
                    if(confidence > 33)
                        w.append(t, (new StringBuilder(" as the Thralls crush ")).append(c.hisHer()).append(" body.  ").toString());
                    else
                        w.append(t, (new StringBuilder(", trying and failing to curl up and protect ")).append(c.himHer()).append("self as the Thralls shatter ").append(c.hisHer()).append(" body.  ").toString());
                    if(c.getPLEALevel() < 4)
                        w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" attempts to move only hurt ").append(c.himHer()).append(" more").toString());
                    else
                    if(c.getPLEALevel() > 4)
                        w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" nerves have been corrupted to the point that the damage inflicts as much pain as pleasure, and the resulting orgasmic spasms only hurt ").append(c.himHer()).append(" more").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" body continues to spasm with the aftershocks of the pleasure already inflicted on ").append(c.himHer()).append(", and the movements only hurt ").append(c.himHer()).append(" more").toString());
                }
                if(c.getEXPOLevel() < 4)
                    w.append(t, ".");
                else
                if(c.getEXPOLevel() > 4)
                    w.append(t, (new StringBuilder(" and showcase ")).append(c.hisHer()).append(" exposed body for ").append(c.hisHer()).append(" abusers.").toString());
                else
                    w.append(t, (new StringBuilder(" and cause the remaining scraps of ")).append(c.hisHer()).append(" ").append(bottomDesc).append(" to shift so that they don't cover anything at all.").toString());
            } else
            if(w.tickle().booleanValue())
                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is completely out of breath from being forced to laugh so much, and a regular person would have passed out already.  However, ").append(c.hisHer()).append(" Chosen powers force ").append(c.himHer()).append(" to remain awake and alert, no matter how heavy ").append(c.hisHer()).append(" limbs feel or how much ").append(c.heShe()).append(" just wants to give up.").toString());
            else
                w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is being continually dealt wounds that ought to be fatal, but ").append(c.hisHer()).append(" Chosen powers don't permit ").append(c.himHer()).append(" to die or even pass out.  However, they do make it harder and harder for ").append(c.himHer()).append(" to defend ").append(c.himHer()).append("self.").toString());
            w.append(t, "\n\n");
        } else
        if(highestPriority == 0)
        {
            if(c.getHATELevel() == 1)
            {
                if(c.getPLEALevel() < 1)
                    w.append(t, "In contrast to the Thralls' delight, ");
                else
                if(c.getPLEALevel() > 1)
                    w.append(t, (new StringBuilder("Despite (or perhaps because of) the intense pleasure that has been inflicted on ")).append(c.himHer()).append(", ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" face turning red with combined arousal and anger, ").toString());
                if(morality > 66)
                    w.append(t, (new StringBuilder("some serious annoyance is hidden behind ")).append(mainName).append("'s outwardly compassionate demeanor.  ").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is already getting annoyed at the situation. ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append("'s short temper means that ").append(c.heShe()).append("'s quickly getting annoyed at being toyed with.  ").toString());
                if(c.getINJULevel() < 1)
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" realizes that they're deliberately provoking ").append(c.himHer()).append(", but ").append(c.heShe()).append("'s still ").toString());
                else
                if(c.getINJULevel() > 1)
                {
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" subconsciously compensates for ").append(c.hisHer()).append(" feelings of helplessness by blaming others, and ").append(c.heShe()).append("'s ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append("'s bleding from various wounds, putting ").append(c.himHer()).append(" in a desperate state of mind, and ").append(c.heShe()).append("'s ").toString());
                } else
                if(w.tickle().booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append("'s starting to get worried about how easy it seems to be for the Thralls to toy with ").append(c.himHer()).append(", and ").append(c.heShe()).append("'s ").toString());
                else
                    w.append(t, (new StringBuilder("The pain of ")).append(c.hisHer()).append(" bruises fuels ").append(c.hisHer()).append(" anger, and ").append(c.heShe()).append("'s ").toString());
                if(c.getEXPOLevel() < 1)
                    w.append(t, (new StringBuilder("losing ")).append(c.hisHer()).append(" cool.").toString());
                else
                if(c.getEXPOLevel() > 1)
                    w.append(t, "flustered at being so exposed.");
                else
                    w.append(t, (new StringBuilder("distracted enough to forget the need to hold ")).append(c.hisHer()).append(" torn ").append(bottomDesc).append(" closed.").toString());
            } else
            if(c.getHATELevel() == 2)
            {
                if(c.getEXPOLevel() < 2)
                    w.append(t, (new StringBuilder("As the Thralls continue to harass ")).append(mainName).append(", ").toString());
                else
                if(c.getEXPOLevel() > 2)
                {
                    if(modest.booleanValue())
                        w.append(t, (new StringBuilder("As ")).append(mainName).append(" tries to cover ").append(c.hisHer()).append(" stripped body, ").toString());
                    else
                        w.append(t, (new StringBuilder("As the Thralls film ")).append(mainName).append("'s stripped body, ").toString());
                } else
                {
                    w.append(t, (new StringBuilder("As ")).append(mainName).append(" struggles to hold ").append(c.hisHer()).append(" ").append(topDesc).append(" closed, ").toString());
                }
                if(morality > 66)
                    w.append(t, (new StringBuilder(String.valueOf(c.heShe()))).append(" wears an angry expression, unsuitable for ").append(c.hisHer()).append(" normally kind face.  ").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder("resentment burns in ")).append(c.hisHer()).append(" eyes.  ").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.hisHer()))).append(" teeth are gritted in rage.  ").toString());
                if(c.getINJULevel() < 2)
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" composure completely lost, ").toString());
                else
                if(c.getINJULevel() > 2)
                {
                    if(aVirg.booleanValue())
                    {
                        if(w.tickle().booleanValue())
                            w.append(t, "Showing obvious signs of exhaustion, ");
                        else
                            w.append(t, (new StringBuilder("With the pain of ")).append(c.hisHer()).append(" severe injuries, ").toString());
                    } else
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder("Even so, ")).append(c.heShe()).append(" still ends up squealing with laughter whenever they tickle ").append(c.himHer()).append(", and ").toString());
                    else
                    if(c.getGender().equals("male"))
                        w.append(t, (new StringBuilder("Crying out in pain whenever ")).append(c.hisHer()).append(" attackers stomp on ").append(c.hisHer()).append(" testicles, ").toString());
                    else
                        w.append(t, (new StringBuilder("Enduring a Thrall's cock up ")).append(c.hisHer()).append(" ass, ").toString());
                } else
                if(w.tickle().booleanValue())
                    w.append(t, "Reflexively flinching away from every touch, ");
                else
                    w.append(t, (new StringBuilder("With ")).append(c.hisHer()).append(" fight-or-flight response stimulated by the cuts covering ").append(c.hisHer()).append(" body, ").toString());
                if(c.getPLEALevel() < 2)
                    w.append(t, (new StringBuilder(String.valueOf(c.heShe()))).append("'s too caught up in the moment to calm down.").toString());
                else
                if(c.getPLEALevel() > 2)
                {
                    if(cVirg.booleanValue())
                        w.append(t, (new StringBuilder("the way ")).append(c.hisHer()).append(" nerves have been overstimulated by pleasure makes it impossible for ").append(c.himHer()).append(" to shut everything out and calm down.").toString());
                    else
                        w.append(t, (new StringBuilder("the countless hands stroking ")).append(c.hisHer()).append(" ").append(organ).append(" still bring ").append(c.himHer()).append(" to another orgasm.").toString());
                } else
                {
                    w.append(t, (new StringBuilder(String.valueOf(c.heShe()))).append(" would have a hard enough time focusing even if ").append(c.heShe()).append(" weren't also gasping for breath in the wake of the sexual stimulation.").toString());
                }
            } else
            if(c.getHATELevel() == 3)
            {
                if(c.getINJULevel() < 3)
                    w.append(t, (new StringBuilder("Unable to contain ")).append(c.hisHer()).append(" emotions anymore, ").toString());
                else
                if(c.getINJULevel() > 3 && aVirg.booleanValue())
                {
                    if(w.tickle().booleanValue())
                        w.append(t, (new StringBuilder("Desperately struggling against ")).append(c.hisHer()).append(" overwhelming exhaustion, ").toString());
                    else
                        w.append(t, (new StringBuilder("Desperately struggling despite ")).append(c.hisHer()).append(" shattered body, ").toString());
                } else
                if(aVirg.booleanValue())
                {
                    if(w.tickle().booleanValue())
                        w.append(t, "Gasping and moaning with exhaustion, ");
                    else
                        w.append(t, (new StringBuilder("Made desperate by ")).append(c.hisHer()).append(" serious wounds, ").toString());
                } else
                if(w.tickle().booleanValue())
                    w.append(t, "Gasping with unwilling laughter, ");
                else
                if(c.getGender().equals("male"))
                    w.append(t, (new StringBuilder("Made desperate by the pain from the Thralls stomping on ")).append(c.hisHer()).append(" testicles, ").toString());
                else
                    w.append(t, (new StringBuilder("Desperately struggling against the Thrall fucking ")).append(c.himHer()).append(" up the ass, ").toString());
                if(vVirg.booleanValue())
                {
                    if(morality > 66)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" lashes out, paying less mind than ").append(c.heShe()).append(" should to any innocents who might be close enough to get hurt.  ").toString());
                    else
                    if(morality > 33)
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" lashes out indiscrimiately in ").append(c.hisHer()).append(" attempts to escape.  ").toString());
                    else
                        w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" gives in to ").append(c.hisHer()).append(" rage and starts trying to lash out.  ").toString());
                } else
                if(morality > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" shudders in revulsion as one of ").append(c.hisHer()).append(" attackers manages to penetrate ").append(c.hisHer()).append(" ").append(hole).toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" goes stiff and freezes up briefly as one of ").append(c.hisHer()).append(" attackers manages to penetrate ").append(c.hisHer()).append(" ").append(hole).toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" screams with rage as one of ").append(c.hisHer()).append(" attackers manages to penetrate ").append(c.hisHer()).append(" ").append(hole).toString());
                if(c.getPLEALevel() < 3)
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append(" just wants this all to be over").toString());
                else
                if(c.getPLEALevel() > 3 && cVirg.booleanValue())
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" body is uncontrollably writhing in pleasure, and ").append(c.hisHer()).append(" mind has been affected too").toString());
                else
                if(cVirg.booleanValue())
                    w.append(t, (new StringBuilder("The pleasure has left ")).append(c.himHer()).append(" incapable of thinking straight").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.HeShe()))).append("'s already cumming again").toString());
                if(c.getEXPOLevel() < 3)
                    w.append(t, ".");
                else
                if(c.getEXPOLevel() > 3 && modest.booleanValue())
                    w.append(t, (new StringBuilder(", ")).append(c.heShe()).append(" has practically forgotten just how exposed ").append(c.hisHer()).append(" body is.").toString());
                else
                if(modest.booleanValue())
                    w.append(t, (new StringBuilder(", ")).append(c.heShe()).append(" can't even muster the composure to cover ").append(c.hisHer()).append(" torn ").append(bottomDesc).append(" and maintain some level of modesty.").toString());
                else
                    w.append(t, (new StringBuilder(", and ")).append(c.heShe()).append(" barely notices when ").append(c.hisHer()).append(" bare legs are forced wide apart for the benefit of the cameras.").toString());
            } else
            if(c.getHATELevel() == 4)
            {
                if(morality > 66)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" is torn between laughing and sobbing as ").append(c.hisHer()).append(" sanity cracks").toString());
                else
                if(morality > 33)
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" giggles madly as ").append(c.hisHer()).append(" sanity cracks").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(mainName))).append(" laughs with despairing abandon as ").append(c.hisHer()).append(" sanity cracks").toString());
                if(c.getINJULevel() < 4)
                    w.append(t, ".  ");
                else
                if(c.getINJULevel() > 4)
                {
                    if(w.tickle().booleanValue())
                        w.append(t, ", struggling for breath.  ");
                    else
                        w.append(t, (new StringBuilder(", barely even acknowledging the mortal wounds covering ")).append(c.hisHer()).append(" body.  ").toString());
                } else
                if(w.tickle().booleanValue())
                    w.append(t, ", twitching wildly at the slightest touch.  ");
                else
                    w.append(t, (new StringBuilder(", barely even acknowledging ")).append(c.hisHer()).append(" shattered body.  ").toString());
                if(c.getPLEALevel() < 4)
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" emotions have spiraled far beyond ").append(c.hisHer()).append(" control").toString());
                else
                if(c.getPLEALevel() > 4)
                    w.append(t, (new StringBuilder("The waves of pleasure rolling through ")).append(c.hisHer()).append(" overstimulated nerves burn the scene into ").append(c.hisHer()).append(" memory").toString());
                else
                    w.append(t, (new StringBuilder(String.valueOf(c.HisHer()))).append(" constant spasms of pleasure make ").append(c.himHer()).append(" look completely unhinged").toString());
                if(c.getEXPOLevel() < 4)
                    w.append(t, ".");
                else
                if(c.getEXPOLevel() > 4)
                    w.append(t, (new StringBuilder(", ")).append(c.hisHer()).append(" mind and body both stripped of all defenses.").toString());
                else
                    w.append(t, (new StringBuilder(", ")).append(c.hisHer()).append(" shredded ").append(bottomDesc).append(" enhancing ").append(c.hisHer()).append(" savage appearance.").toString());
            } else
            {
                w.append(t, (new StringBuilder("Your provocations reach into the depths of ")).append(mainName).append("'s soul, drawing tendrils of Demonic energy out of ").append(c.hisHer()).append(" body.  ").append(c.HisHer()).append(" Chosen powers grow weaker and weaker as ").append(c.heShe()).append(" is further alienated from the virtues of humanity.").toString());
            }
            w.append(t, "\n\n");
        }
        c.say(t, "\"");
        if(confidence > 66)
        {
            if(thisAttack == 0)
            {
                if(variant == 0)
                {
                    if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                        c.say(t, "Ergh, no, take it out!");
                    else
                        c.say(t, "Ngh, no...!  Don't make me... enjoy this...!  Aaah!");
                } else
                if(variant == 1)
                {
                    if(c.getHATELevel() < 3 || vVirg.booleanValue())
                        c.say(t, "Need to... hold... aaah, ah, I'm-");
                    else
                        c.say(t, "P-Pull out or I'll- ah, ah, nnnaaah!");
                } else
                if(variant == 2)
                {
                    if(w.tickle().booleanValue())
                    {
                        if(c.getHATELevel() < 3 || vVirg.booleanValue())
                            c.say(t, "Hahah, ahahah, stop, don't touch meee!");
                        else
                            c.say(t, "Ahahah, no, not while you're insiiide, hahahahah!");
                    } else
                    if(c.getHATELevel() < 3 || vVirg.booleanValue())
                        c.say(t, "Ah!  No!  No more!");
                    else
                        c.say(t, "Agh!  Ow!  No, no, take it out!");
                } else
                if(variant == 3)
                {
                    if(c.getHATELevel() < 3 || vVirg.booleanValue())
                        c.say(t, "Don't make me join in this disgusting stuff...");
                    else
                        c.say(t, "You'd better pull out...");
                } else
                if(loved.booleanValue())
                    c.say(t, "No!  Leave them alone!");
                else
                if(hated.booleanValue())
                {
                    if(morality > 66)
                        c.say(t, "Do not harm them!");
                    else
                    if(morality > 33)
                        c.say(t, "I guess those two are relying on me to rescue them...");
                    else
                        c.say(t, "I do like to see those two suffer...");
                } else
                {
                    c.say(t, (new StringBuilder("No!  Leave ")).append(lover.getMainName()).append(" alone!").toString());
                }
            } else
            if(thisAttack == 1)
            {
                if(variant == 0)
                {
                    if(c.getINJULevel() < 3 || aVirg.booleanValue())
                        c.say(t, "I won't... give in...!");
                    else
                    if(w.tickle().booleanValue())
                        c.say(t, "Ahahah, gaaah, take it ooout, hahahahah!");
                    else
                    if(c.getGender().equals("male"))
                        c.say(t, "Don't touch my- Ah!  Ow, nooo!");
                    else
                        c.say(t, "Agh, no, not in both at once- Gaaah!");
                } else
                if(variant == 1)
                {
                    if(c.getINJULevel() < 3 || aVirg.booleanValue())
                        c.say(t, "D-Don't touch me theeere!");
                    else
                    if(w.tickle().booleanValue())
                        c.say(t, "Ahahah, no, no, I don't want to- naaahahahah!");
                    else
                        c.say(t, "This... This shouldn't... feel goood...!");
                } else
                if(variant == 2)
                {
                    if(w.tickle().booleanValue())
                    {
                        if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                            c.say(t, "Ahah... ahah... why can't I...?");
                        else
                            c.say(t, "Ahahah, nooo, not while I'm- Hahahah, aaahnnn!");
                    } else
                    if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                        c.say(t, "Ow...  Why...  Why can't I beat this...?");
                    else
                        c.say(t, "I can't... fight thiiis...!");
                } else
                if(variant == 3)
                {
                    if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                        c.say(t, "I won't play along!");
                    else
                        c.say(t, "I won't... nnn... no, not there...!");
                } else
                if(loved.booleanValue())
                    c.say(t, "They don't deserve this!");
                else
                if(hated.booleanValue())
                {
                    if(morality > 66)
                        c.say(t, "Even if it's those two, they don't deserve this...");
                    else
                    if(morality > 33)
                        c.say(t, "Well, I guess those two might deserve this...");
                    else
                        c.say(t, "Heh, looks like those two are having a harder time...");
                } else
                {
                    c.say(t, (new StringBuilder(String.valueOf(lover.getMainName()))).append(" doesn't deserve this!").toString());
                }
            } else
            if(variant == 0)
            {
                if(c.getEXPOLevel() < 3 || modest.booleanValue())
                    c.say(t, "Y-You think I'll be bothered by getting stripped when you're already doing this!?");
                else
                    c.say(t, "What sort of freak would enjoy watching me get- Gh!  Aaah, stop!");
            } else
            if(variant == 1)
            {
                if(c.getEXPOLevel() < 3 || modest.booleanValue())
                    c.say(t, "I won't let you- Hng!?  Ah, ah, no, stooop!");
                else
                    c.say(t, "Aaah, wow... hah, wh-what are you all looking at...?");
            } else
            if(variant == 2)
            {
                if(w.tickle().booleanValue())
                {
                    if(c.getEXPOLevel() < 3 || modest.booleanValue())
                        c.say(t, "Hmph...  Ngah!?  Hahahahah, aaagh!");
                    else
                        c.say(t, "Aaahahah, no, no, not my feeet!");
                } else
                if(c.getEXPOLevel() < 3 || modest.booleanValue())
                    c.say(t, "Hmph...  Ngah, ow, ow, no, not there!");
                else
                    c.say(t, "Guh!  Urgh, ow, ow...  Is this really... so fun to watch...?");
            } else
            if(variant == 3)
            {
                if(c.getINJULevel() < 3 || aVirg.booleanValue())
                    c.say(t, "Ugh, leave me alone...");
                else
                if(w.tickle().booleanValue())
                    c.say(t, "Hahah, ahahah, j-just stop, stop it already!  Hahahahah!");
                else
                    c.say(t, "Just- ow!  J-Just stop!");
            } else
            if(loved.booleanValue())
                c.say(t, "Come on, focus on me instead!");
            else
            if(hated.booleanValue())
            {
                if(morality > 66)
                    c.say(t, "If you don't break me harder, I absolutely will rescue the others.");
                else
                if(morality > 33)
                    c.say(t, "You Thralls can't afford to waste your time breaking the weaklings!  Come!");
                else
                    c.say(t, "Come on, let me help you break the others!");
            } else
            {
                c.say(t, (new StringBuilder("Come on, focus on me instead of ")).append(lover.getMainName()).append("!").toString());
            }
        } else
        if(confidence > 33)
        {
            if(thisAttack == 0)
            {
                if(variant == 0)
                {
                    if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                        c.say(t, "Ugh, this is disgusting...");
                    else
                        c.say(t, "Don't want to... enjoy this- Aaah!");
                } else
                if(variant == 1)
                {
                    if(c.getHATELevel() < 3 || vVirg.booleanValue())
                        c.say(t, "Aaah, d-don't- Mmf!  Nn nn!");
                    else
                        c.say(t, "Aaah, no, I'm gonna breaaak!");
                } else
                if(variant == 2)
                {
                    if(w.tickle().booleanValue())
                    {
                        if(c.getHATELevel() < 3 || vVirg.booleanValue())
                            c.say(t, "Ahahah, w-wait, don't tickle me with your- Ahahahahah!");
                        else
                            c.say(t, "Ahahah, w-wait, don't put it- Agh!  Ahahahah!");
                    } else
                    if(c.getGender().equals("male"))
                    {
                        if(c.getHATELevel() < 3 || vVirg.booleanValue())
                            c.say(t, "They're all... so much bigger...");
                        else
                            c.say(t, "It... hurts...!");
                    } else
                    if(c.getHATELevel() < 3 || vVirg.booleanValue())
                        c.say(t, "They're... getting it all over me...");
                    else
                        c.say(t, "Urgh... They're filling me up...");
                } else
                if(variant == 3)
                {
                    if(c.getHATELevel() < 3 || vVirg.booleanValue())
                        c.say(t, "Nn...!  Gh!");
                    else
                        c.say(t, "Ugh!  Not... inside...!  Mmf!");
                } else
                if(loved.booleanValue())
                    c.say(t, "I don't want to see what's happening to them...");
                else
                if(hated.booleanValue())
                {
                    if(morality > 66)
                        c.say(t, "This is awful...");
                    else
                    if(morality > 33)
                        c.say(t, "I can... deal with this...");
                    else
                        c.say(t, "I don't care what happens to the others.");
                } else
                {
                    c.say(t, (new StringBuilder("I don't want to see what's happening to ")).append(lover.getMainName()).append("...").toString());
                }
            } else
            if(thisAttack == 1)
            {
                if(variant == 0)
                {
                    if(c.getINJULevel() < 3 || aVirg.booleanValue())
                        c.say(t, "Ah!  It's going inside...!");
                    else
                    if(w.tickle().booleanValue())
                        c.say(t, "Ahah!  Ahahah, no, i-it's too deep, I'm- Hahahahahah!");
                    else
                    if(c.getGender().equals("male"))
                        c.say(t, "Ng... hitting that spot... from both sides...!");
                    else
                        c.say(t, "Ugh!  B-Both holes at once...!?");
                } else
                if(variant == 1)
                {
                    if(c.getINJULevel() < 3 || aVirg.booleanValue())
                        c.say(t, "I can't... ah... can't resist...!");
                    else
                    if(w.tickle().booleanValue())
                        c.say(t, "Ahahah, ahn, if you tickle me at the same time, I-I can't- Hahahahah, oooh!");
                    else
                    if(c.getGender().equals("male"))
                        c.say(t, "Ow!  No!  If you hit me there anymore, I-I'll- Naaah, nooo!");
                    else
                        c.say(t, "It's... nn... hitting so deep inside...!");
                } else
                if(variant == 2)
                {
                    if(w.tickle().booleanValue())
                    {
                        if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                            c.say(t, "Ahah, ahahah, wh-where are you touching- Hahahahah!");
                        else
                            c.say(t, "Aaahahah, nn, no, I'm going to- Hahahahah, ah, ah, aaah!");
                    } else
                    if(c.getGender().equals("male"))
                    {
                        if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                            c.say(t, "It... hurts... but...!");
                        else
                            c.say(t, "Ow, ooow, no, it's coming ooout!");
                    } else
                    if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                        c.say(t, "Ugh...!  Ugh!  Deep...!");
                    else
                        c.say(t, "Ugh!  Ugh!  Nooo, even from my buuutt!");
                } else
                if(variant == 3)
                {
                    if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                        c.say(t, "Have to... ignore it...!");
                    else
                        c.say(t, "Ah!  Th-They're... watchiiing!");
                } else
                if(loved.booleanValue())
                    c.say(t, "I know this is even harder for them...");
                else
                if(hated.booleanValue())
                {
                    if(morality > 66)
                        c.say(t, "Those two must have it even worse...");
                    else
                    if(morality > 33)
                        c.say(t, "I guess it's not too bad compared to what they're going through...");
                    else
                        c.say(t, "At least those two are suffering worse.");
                } else
                {
                    c.say(t, (new StringBuilder("I know this is even harder for ")).append(lover.getMainName()).append("...").toString());
                }
            } else
            if(variant == 0)
            {
                if(c.getEXPOLevel() < 3 || modest.booleanValue())
                    c.say(t, "Let go of me!");
                else
                    c.say(t, "They can all see the spot where we're joined...");
            } else
            if(variant == 1)
            {
                if(c.getEXPOLevel() < 3 || modest.booleanValue())
                    c.say(t, "I'm... nn... a-again...  ah, wooow!");
                else
                    c.say(t, "Everyone is... aaah... w-watchiiing...!");
            } else
            if(variant == 2)
            {
                if(w.tickle().booleanValue())
                {
                    if(c.getEXPOLevel() < 3 || modest.booleanValue())
                        c.say(t, "Ahahah, i-if they strip me, it'll make it even easier to- Hahahahah!");
                    else
                        c.say(t, "Ahahah, nooo, I-I'm completely...!");
                } else
                if(c.getEXPOLevel() < 3 || modest.booleanValue())
                    c.say(t, "Ergh!  Even my clothes...!");
                else
                    c.say(t, "Ergh!  Let... go...!");
            } else
            if(variant == 3)
            {
                if(c.getINJULevel() < 3 || aVirg.booleanValue())
                    c.say(t, "Don't!");
                else
                if(w.tickle().booleanValue())
                    c.say(t, "Ahah, ahahah, dooon't!");
                else
                if(c.getGender().equals("male"))
                    c.say(t, "Ow, dooon't, aaagh!");
                else
                    c.say(t, "Nn!  Nn!  Stop... this...!");
            } else
            if(loved.booleanValue())
                c.say(t, "I need to... become stronger... for them...!");
            else
            if(hated.booleanValue())
            {
                if(morality > 66)
                    c.say(t, "For their sakes, too... I need to fight this...!");
                else
                if(morality > 33)
                    c.say(t, "I won't let you... do what you want...!");
                else
                    c.say(t, "I'll... make all of you pay...!");
            } else
            {
                c.say(t, (new StringBuilder("I need to... become stronger... for ")).append(lover.getMainName()).append("...!").toString());
            }
        } else
        if(thisAttack == 0)
        {
            if(variant == 0)
            {
                if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                    c.say(t, "Mmf, mmf...  Mm...");
                else
                    c.say(t, "Mmf...  Mm!?  Nn, nn, nnn!");
            } else
            if(variant == 1)
            {
                if(c.getHATELevel() < 3 || vVirg.booleanValue())
                    c.say(t, "They're... rubbing... aaah...!");
                else
                    c.say(t, "Aaahn...!  A-Ah, no... how could I enjoy something like this...?");
            } else
            if(variant == 2)
            {
                if(w.tickle().booleanValue())
                {
                    if(c.getHATELevel() < 3 || vVirg.booleanValue())
                        c.say(t, "Ahahah, ahah, th-they're touching my- Nn!  Hahahah!");
                    else
                        c.say(t, "Ah!  Ahah!  Ahah!  It's... in...!  Ahah!");
                } else
                if(c.getHATELevel() < 3 || vVirg.booleanValue())
                    c.say(t, "Nn!  So... rough...!");
                else
                    c.say(t, "Y-You're... ripping me in half...!");
            } else
            if(variant == 3)
            {
                if(c.getHATELevel() < 3 || vVirg.booleanValue())
                    c.say(t, "Um... P-Please... use my mouth...  Mm...");
                else
                    c.say(t, "Nn... O-Okay... I'm moving...");
            } else
            if(loved.booleanValue())
                c.say(t, "It's my fault that this is happening to them...");
            else
            if(hated.booleanValue())
            {
                if(morality > 66)
                    c.say(t, "I should have tried harder to stop this from happening to them...");
                else
                if(morality > 33)
                    c.say(t, "They're right about how weak I am...");
                else
                    c.say(t, "The others should have known better than to rely on me...");
            } else
            {
                c.say(t, (new StringBuilder("It's my fault that this is happening to ")).append(lover.getMainName()).append("...").toString());
            }
        } else
        if(thisAttack == 1)
        {
            if(variant == 0)
            {
                if(c.getINJULevel() < 3 || aVirg.booleanValue())
                    c.say(t, "Mm.  Mm...");
                else
                if(w.tickle().booleanValue())
                    c.say(t, "Hahahahammf!  Mm mm mm mm!");
                else
                    c.say(t, "Mm!  Mm!  Mm!  Mm!");
            } else
            if(variant == 1)
            {
                if(c.getINJULevel() < 3 || aVirg.booleanValue())
                    c.say(t, "Nooo... my body is...");
                else
                if(w.tickle().booleanValue())
                    c.say(t, "Ahahah, p-please, n-not while tickling me, i-it's going to make me- Aaaahahah, aaah!");
                else
                    c.say(t, "Ah, p-please, not so rough, it's- Ooow, nooo!");
            } else
            if(variant == 2)
            {
                if(w.tickle().booleanValue())
                {
                    if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                        c.say(t, "Hahah, ahahah, this shouldn't, ahah, feel good, hahahah!");
                    else
                        c.say(t, "Hahah, ahahah, no, I don't want to feel goood, hahahahah!");
                } else
                if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                    c.say(t, "Ah, please!  Please, stop trying to make me feel good!");
                else
                    c.say(t, "No, no, nooo, I don't want this to feel goood!");
            } else
            if(variant == 3)
            {
                if(c.getPLEALevel() < 3 || cVirg.booleanValue())
                    c.say(t, "Please... go easy on me...");
                else
                    c.say(t, "Aaah, pleeease, not theeere!  Mmm!");
            } else
            if(loved.booleanValue())
                c.say(t, "P-Please, I'll do anything, but leave them alone!");
            else
            if(hated.booleanValue())
            {
                if(morality > 66)
                    c.say(t, "I feel like I should offer myself in their place...");
                else
                if(morality > 33)
                    c.say(t, "There's nothing I can do to help the others...");
                else
                    c.say(t, "P-Please, I'll do anything, just let me go!");
            } else
            {
                c.say(t, (new StringBuilder("P-Please, I'll do anything, but leave ")).append(lover.getMainName()).append(" alone!").toString());
            }
        } else
        if(variant == 0)
        {
            if(c.getEXPOLevel() < 3 || modest.booleanValue())
                c.say(t, "Mmf!?  Nn...");
            else
                c.say(t, "Nn...");
        } else
        if(variant == 1)
        {
            if(c.getEXPOLevel() < 3 || modest.booleanValue())
                c.say(t, "Nn... I can't... do anything...");
            else
                c.say(t, "Aaah... th-they're seeing... how worthless I am...");
        } else
        if(variant == 2)
        {
            if(w.tickle().booleanValue())
            {
                if(c.getEXPOLevel() < 3 || modest.booleanValue())
                    c.say(t, "Ahah, ahahah, th-they're going to see...!");
                else
                    c.say(t, "Ahahah, nooo, don't loook!");
            } else
            if(c.getEXPOLevel() < 3 || modest.booleanValue())
                c.say(t, "I don't want... this...");
            else
                c.say(t, "I'm... completely defeated...");
        } else
        if(variant == 3)
        {
            if(c.getINJULevel() < 3 || aVirg.booleanValue())
                c.say(t, "Ah, I-I, um, really- AH!");
            else
            if(w.tickle().booleanValue())
                c.say(t, "Ahahah, hah, nnn, hahahahah, nooo!");
            else
                c.say(t, "I'm... I'm doing my best, so...");
        } else
        if(loved.booleanValue())
            c.say(t, "A-Are you two alright!?");
        else
        if(hated.booleanValue())
        {
            if(morality > 66)
                c.say(t, "Um, are you two doing alright...?");
            else
            if(morality > 33)
                c.say(t, "I hope those two are doing alright...");
            else
                c.say(t, "I don't really care what happens to the others...");
        } else
        {
            c.say(t, (new StringBuilder("A-Are you alright, ")).append(lover.getMainName()).append("!?").toString());
        }
        c.say(t, "\"");
    }

    public int[] getLastOrgyStage()
    {
        return lastOrgyStage;
    }

    public void rememberOrgyStage(int value)
    {
        lastOrgyStage[value] = orgyStage;
    }

    public void cycleOrgyStage()
    {
        orgyStage++;
        if(orgyStage > 3)
            orgyStage = 0;
    }

    public int getOrgyStage()
    {
        return orgyStage;
    }

    public long softClamp(long base, long change, int preApplied)
    {
        clampPercent = 100;
        long nextClamp = 10L;
        long startPower = clampStart;
        long usedPercent = clampPercent;
        for(; startPower > 0L; startPower--)
            nextClamp *= 10L;

        for(; preApplied > 0; preApplied--)
            nextClamp = nextMagnitude(nextClamp);

        while(base > nextClamp && nextClamp < 0xcccccccccccccccL) 
        {
            nextClamp = nextMagnitude(nextClamp);
            change /= 2L;
        }
        while(base + change > nextClamp && nextClamp < 0xcccccccccccccccL) 
        {
            if(nextClamp >= 0xe8d4a51000L && usedPercent > 20L)
                usedPercent = 20L;
            long remainder = (base + change) - nextClamp;
            if(remainder >= 0x147ae147ae147aeL)
                remainder = (remainder / 100L) * usedPercent;
            else
                remainder = (remainder * usedPercent) / 100L;
            change = (remainder + nextClamp) - base;
            if(nextClamp == nextMagnitude(nextClamp))
                nextClamp = 0x7fffffffffffffffL;
            else
                nextClamp = nextMagnitude(nextClamp);
        }
        return change;
    }

    public long nextMagnitude(long number)
    {
        if(number < 0xcccccccccccccccL)
            number *= 10L;
        return number;
    }

    public void synchSurroundDurations(Chosen c[])
    {
        int durations[] = new int[c.length];
        for(int i = 0; i < c.length; i++)
            if(c[i].isSurrounded().booleanValue())
                durations[i] = c[i].getSurroundDuration();
            else
            if(c[i].getFEAROpening(this) + c[i].getDISGOpening() + c[i].getPAINOpening() + c[i].getSHAMOpening(this) >= c[i].getDefenseLevel())
                durations[i] = c[i].getFEAROpening(this) + c[i].getDISGOpening() + c[i].getPAINOpening() + c[i].getSHAMOpening(this);
            else
                durations[i] = captureDuration + 1;

        int lowest = durations[0];
        if(c.length > 1 && durations[1] < lowest)
            lowest = durations[1];
        if(c.length > 2 && durations[2] < lowest)
            lowest = durations[2];
        for(int i = 0; i < c.length; i++)
            c[i].setSurroundDuration(lowest);

    }

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
        int techsThisVersion = 40;
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
                techs[i].assignTooltip(i, this);
                if(i < oldTechs.length && oldTechs[i].isOwned().booleanValue())
                    techs[i].owned = Boolean.valueOf(true);
            }

            adjusted = Boolean.valueOf(true);
        }
        int commanderUpgradesThisVersion = 25;
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
        if(lastOrgyStage == null)
        {
            lastOrgyStage = new int[3];
            adjusted = Boolean.valueOf(true);
        }
        if(techs[0].getTooltip() == null)
        {
            for(int i = 0; i < techs.length; i++)
                techs[i].assignTooltip(i, this);

            adjusted = Boolean.valueOf(true);
        }
        if(getCast()[0].impregnated == null)
        {
            for(int i = 0; i < 3; i++)
                if(getCast()[i] != null)
                    getCast()[i].setFourthVulnerabilities();

            adjusted = Boolean.valueOf(true);
        }
        if(version == null)
            adjusted = Boolean.valueOf(true);
        else
        if(!version.equals("12"))
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
            version = "12";
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
            "From a gameplay perspective, there are no differences between male, female, and futanari Chosen.", "It isn't possible for one of the Chosen to use \"Slaughter,\" \"Fantasize,\" or \"Striptease\" twice in a row.", "Even when \"Slaughter\" causes a surround duration to go below 0, it will never cause a surrounded Chosen to escape on the same turn.", "Because high trauma penalizes circumstance damage, the circumstance damage reduction from \"Fantasize\" doesn't necessarily decrease circumstance damage in the long run.", "When your Commander has no extra captures remaining, the extra capture depletion from Chosen using \"Detonate\" does nothing.", "\"Striptease\" decreases damage to surrounded Chosen in the short term, but the fact that it increases the user's EXPO level means that it can increase the overall damage taken during the battle.", "Even after the critical trauma level is reached, a third-tier Vulnerability is not actually broken until the Chosen uses the unlocked move for the first time.", "When two Chosen are targeted by the same defiler action, they both take greatly increased damage, but as soon as one of them escapes, the other does too."
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
            path = path.replaceAll((new StringBuilder(String.valueOf(File.separator))).append("u0020").toString(), (new StringBuilder(String.valueOf(File.separator))).append(" ").toString());
            File saveLocation = new File((new StringBuilder(String.valueOf(path))).append(File.separator).append("saves.sav").toString());
            SaveData saves = null;
            if(saveLocation.exists())
            {
                ReadObject robj = new ReadObject();
                saves = robj.deserializeSaveData((new StringBuilder(String.valueOf(path))).append(File.separator).append("saves.sav").toString());
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

    public void applyRelentlessness()
    {
        evilEnergy -= 10;
        bodyStatus[24] = Boolean.valueOf(true);
    }

    public void addCaptureFour()
    {
        evilEnergy -= 20;
        bodyStatus[23] = Boolean.valueOf(true);
    }

    public void applyParasitism()
    {
        bodyStatus[22] = Boolean.valueOf(true);
    }

    public void applyDrain()
    {
        bodyStatus[21] = Boolean.valueOf(true);
    }

    public void applyHypnosis()
    {
        bodyStatus[20] = Boolean.valueOf(true);
    }

    public void applyImpregnation()
    {
        bodyStatus[19] = Boolean.valueOf(true);
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
        if(bodyStatus[23].booleanValue())
            value += 20;
        if(bodyStatus[24].booleanValue())
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
        Boolean punisher = Boolean.valueOf(false);
        if(bodyStatus[19].booleanValue() || bodyStatus[20].booleanValue() || bodyStatus[21].booleanValue() || bodyStatus[22].booleanValue())
            punisher = Boolean.valueOf(true);
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
                        if(techs[38].isOwned().booleanValue())
                        {
                            append(t, "[");
                            if(bodyStatus[23].booleanValue())
                            {
                                append(t, "X");
                            } else
                            {
                                append(t, " ");
                                if(captureCost == 0)
                                    captureCost = 20;
                            }
                            append(t, "]");
                        }
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
            if(techs[39].isOwned().booleanValue())
            {
                append(t, "[");
                if(bodyStatus[24].booleanValue())
                    append(t, "X");
                else
                    append(t, " ");
                append(t, "] Flight");
                if(!bodyStatus[24].booleanValue())
                    append(t, " (Cost: 10 EE)");
                append(t, "\n");
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
                if(punisher.booleanValue())
                    append(t, "(LOCKED)");
                else
                    append(t, "None (free)");
                append(t, "\n");
            }
            if(techs[22].isOwned().booleanValue() || techs[23].isOwned().booleanValue() || techs[24].isOwned().booleanValue() || techs[25].isOwned().booleanValue())
            {
                append(t, "Defiler: ");
                if(suppressors == 2 || punisher.booleanValue())
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
                append(t, "\n");
            }
            if(techs[34].isOwned().booleanValue() || techs[35].isOwned().booleanValue() || techs[36].isOwned().booleanValue() || techs[37].isOwned().booleanValue())
            {
                append(t, "Punisher: ");
                if(suppressors > 0 || defiler.booleanValue())
                    append(t, "(LOCKED)");
                else
                if(bodyStatus[19].booleanValue())
                    append(t, "Impregnation [HATE]");
                else
                if(bodyStatus[20].booleanValue())
                    append(t, "Hypnosis [PLEA]");
                else
                if(bodyStatus[21].booleanValue())
                {
                    append(t, "Drain ");
                    if(tickleOn.booleanValue())
                        append(t, "[ANTI]");
                    else
                        append(t, "[INJU]");
                } else
                if(bodyStatus[22].booleanValue())
                    append(t, "Parasitism [EXPO]");
                else
                    append(t, "None (free)");
                append(t, "\n");
            }
            append(t, "\n");
            if(bodyStatus[19].booleanValue())
                append(t, "Your Commander is a regular-sized humanoid Demon whose only distinguishing feature is its abnormally large genitals, capable of delivering extra-potent seed into the bodies of the Chosen.  It ");
            else
            if(bodyStatus[20].booleanValue())
                append(t, "Your Commander is a large, dome-shaped, hollow Demon which can engulf the Chosen, subjecting them to hypnotic patterns and sounds in order to manipulate their mental state.  It ");
            else
            if(bodyStatus[21].booleanValue())
                append(t, "Your Commander is a chaotic mass of tentacles which absorb the psychic energy of anything they come into contact with, especially if the target isn't resisting.  It ");
            else
            if(bodyStatus[22].booleanValue())
                append(t, "Your Commander is a mostly-transparent cloud which drifts across the battlefield until it comes into contact with a suitable host.  After flowing into its targets, it can merge with and influence the psychic energy within them.  Your Commander ");
            else
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
            if(bodyStatus[23].booleanValue())
                append(t, ", up to five times");
            else
            if(bodyStatus[17].booleanValue())
                append(t, ", up to four times");
            else
            if(bodyStatus[16].booleanValue())
                append(t, ", up to three times");
            else
            if(bodyStatus[8].booleanValue())
                append(t, ", up to twice");
            append(t, ", even against a flying target");
        } else
        {
            append(t, " at the start of battle");
            if(bodyStatus[23].booleanValue())
                append(t, ", and then four more times whenever you give the order");
            else
            if(bodyStatus[17].booleanValue())
                append(t, ", and then three more times whenever you give the order");
            else
            if(bodyStatus[16].booleanValue())
                append(t, ", and then two more times whenever you give the order");
            else
            if(bodyStatus[8].booleanValue())
                append(t, ", and then one more time once you give the order");
            if(bodyStatus[8].booleanValue())
                append(t, ", even against a flying target");
        }
        if(c == null)
            append(t, (new StringBuilder(".  It is worth ")).append(getCommanderValue()).append(" Evil Energy.  You have ").append(evilEnergy).append(" Evil Energy remaining.").toString());
        else
        if(bodyStatus[19].booleanValue())
            append(t, ", inflicting extreme levels of HATE and FEAR, and causing Total Morality Break when the target's Impregnation effectiveness is at least 1000%.");
        else
        if(bodyStatus[20].booleanValue())
            append(t, ", inflicting extreme levels of PLEA and DISG, and causing Total Innocence Break when the target's Hypnosis effectiveness is at least 1000%.");
        else
        if(bodyStatus[21].booleanValue())
        {
            append(t, ", inflicting extreme levels of ");
            if(tickleOn.booleanValue())
                append(t, "ANTI and TICK");
            else
                append(t, "INJU and PAIN");
            append(t, ", and causing Total Confidence Break when the target's Drain effectiveness is at least 1000%.");
        } else
        if(bodyStatus[22].booleanValue())
            append(t, ", inflicting extreme levels of EXPO and SHAM, and causing Total Dignity Break when the target's Parasitism effectiveness is at least 1000%.");
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
        if(!bodyStatus[3].booleanValue() && !bodyStatus[4].booleanValue() && !bodyStatus[5].booleanValue() && !bodyStatus[6].booleanValue() && !bodyStatus[11].booleanValue() && !bodyStatus[12].booleanValue() && !bodyStatus[13].booleanValue() && !bodyStatus[14].booleanValue() && !bodyStatus[19].booleanValue() && !bodyStatus[20].booleanValue() && !bodyStatus[21].booleanValue() && !bodyStatus[22].booleanValue())
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
        if(bodyStatus[23].booleanValue())
            capturesPossible += 4;
        else
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
            techs[i].assignTooltip(i, this);
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
            techs[i].assignTooltip(i, this);
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
            techs[i].assignTooltip(i, this);
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
                    if(currentCombatants[i].getSurroundDuration() == 1 || currentCombatants[i].isCaptured().booleanValue() && (currentCombatants[i].getCaptureProgression() == captureDuration || currentCombatants[i].timesDetonated() > 0 && currentCombatants[i].getCaptureProgression() + currentCombatants[i].getINJULevel() + 1 >= captureDuration))
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
            techs[i].assignTooltip(i, this);
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
                path = path.replaceAll((new StringBuilder(String.valueOf(File.separator))).append("u0020").toString(), (new StringBuilder(String.valueOf(File.separator))).append(" ").toString());
                File saveLocation = new File((new StringBuilder(String.valueOf(path))).append(File.separator).append("saves.sav").toString());
                SaveData saves = null;
                if(saveLocation.exists())
                {
                    ReadObject robj = new ReadObject();
                    saves = robj.deserializeSaveData((new StringBuilder(String.valueOf(path))).append(File.separator).append("saves.sav").toString());
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
        version = "12";
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
        techs = new Tech[40];
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
        clampStart = 11;
        clampPercent = 100;
        bodyStatus = new Boolean[25];
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
        orgyStage = 0;
        lastOrgyStage = new int[3];
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
    int clampStart;
    int clampPercent;
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
    int orgyStage;
    int lastOrgyStage[];
}
