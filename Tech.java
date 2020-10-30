
import java.io.Serializable;
import javax.swing.JTextPane;

public class Tech
    implements Serializable
{

    public void assignTooltip(int index, WorldState w)
    {
        if(index == 0)
            tooltip = "View Chosen profiles";
        else
        if(index == 1)
            tooltip = "Evac req 100 -> 120";
        else
        if(index == 2)
            tooltip = "Exterm req 100 -> 140";
        else
        if(index == 3)
            tooltip = "Can make Commanders for 1 EE";
        else
        if(index == 4)
            tooltip = "Evac req 120 -> 140";
        else
        if(index == 5)
            tooltip = "Exterm req 140 -> 200";
        else
        if(index == 6)
            tooltip = "+50% circumstance damage when surrounded";
        else
        if(index == 7)
            tooltip = "+20% trauma with the four basic attacks";
        else
        if(index == 8)
            tooltip = "+1 Commander duration for +1 EE";
        else
        if(index == 9)
            tooltip = "Commanders can wait to capture";
        else
        if(index == 10)
            tooltip = "Commander inflicts HATE";
        else
        if(index == 11)
            tooltip = "Commander inflicts PLEA";
        else
        if(index == 12)
        {
            if(w.tickle().booleanValue())
                tooltip = "Commander inflicts ANTI";
            else
                tooltip = "Commander inflicts INJU";
        } else
        if(index == 13)
            tooltip = "Commander inflicts EXPO";
        else
        if(index == 14)
            tooltip = "+1 Commander duration for +1 EE";
        else
        if(index == 15)
            tooltip = "+1 Commander capture for +2 EE";
        else
        if(index == 16)
            tooltip = "Evac req 140 -> 160";
        else
        if(index == 17)
            tooltip = "Exterm req 200 -> 300";
        else
        if(index == 18)
            tooltip = "Battle end delayed by 1 turn";
        else
        if(index == 19)
            tooltip = "Only +1 defense level after first surround/capture";
        else
        if(index == 20)
            tooltip = "+1 Commander duration for +2 EE";
        else
        if(index == 21)
            tooltip = "Commander equips two Suppressors for +5 EE";
        else
        if(index == 22)
            tooltip = "Inseminate-like Commander for +6 EE";
        else
        if(index == 23)
            tooltip = "Force Orgasm-like Commander for +6 EE";
        else
        if(index == 24)
        {
            if(w.tickle().booleanValue())
                tooltip = "Force Laughter-like Commander for +6 EE";
            else
            if(w.getGenderBalance()[2] == 0)
                tooltip = "Sodomize-like Commander for +6 EE";
            else
            if(w.getGenderBalance()[1] == 0 && w.getGenderBalance()[3] == 0)
                tooltip = "Torture-like Commander for +6 EE";
            else
                tooltip = "Sodomize/Torture-like Commander for +6 EE";
        } else
        if(index == 25)
            tooltip = "Broadcast-like Commander for +6 EE";
        else
        if(index == 26)
            tooltip = "+1 Commander duration for +2 EE";
        else
        if(index == 27)
            tooltip = "+1 Commander capture for +5 EE";
        else
        if(index == 28)
            tooltip = "Evac req 160 -> 200";
        else
        if(index == 29)
            tooltip = "Exterm req 300 -> 500";
        else
        if(index == 30)
            tooltip = "Spend turns to increase damage for rest of battle";
        else
        if(index == 31)
            tooltip = "Act immediately after surrounding target";
        else
        if(index == 32)
            tooltip = "+1 Commander capture for +10 EE";
        else
        if(index == 33)
            tooltip = "Commander equips Suppressor and Defiler for +10 EE";
        else
        if(index == 34)
            tooltip = "Commander inflicts HATE/FEAR, Total Morality Break against Slaughter users";
        else
        if(index == 35)
            tooltip = "Commander inflicts PLEA/DISG, Total Innocence Break against Fantasize users";
        else
        if(index == 36)
        {
            if(w.tickle().booleanValue())
                tooltip = "Commander inflicts ANTI/TICK, Total Confidence Break against Detonate users";
            else
                tooltip = "Commander inflicts INJU/PAIN, Total Confidence Break against Detonate users";
        } else
        if(index == 37)
            tooltip = "Commander inflicts EXPO/SHAM, Total Dignity Break against Striptease users";
        else
        if(index == 38)
            tooltip = "+1 Commander capture for +20 EE";
        else
        if(index == 39)
            tooltip = "Commander becomes capable of capturing flying Chosen for +10 EE";
    }

    public String getTooltip()
    {
        return tooltip;
    }

    public int getPrereqReqs()
    {
        return prereqsRequired;
    }

    public void buy(WorldState w)
    {
        owned = Boolean.valueOf(true);
        w.addEnergy(0 - cost);
    }

    public int getCost()
    {
        return cost;
    }

    public void initialize(int index, WorldState w)
    {
        if(index == 0)
        {
            name = "Psychic Reading";
            prereqsRequired = 0;
            cost = 1;
            prereqs = new Tech[0];
            description = "Refines your innate control over the psychic energies associated with emotions and the subconscious.  Once this has been purchased, the 'Profiles' option is unlocked here in the shop.  This button allows you to review the status of the Chosen you've met before, including their vulnerabilities and their progress toward corruption.  This information is also displayed when you 'Examine' targets in battle.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 1)
        {
            name = "Wide Deployment";
            prereqsRequired = 0;
            cost = 1;
            prereqs = new Tech[0];
            description = "Develops your ability to command Demons and Thralls in a wider area at once.  This forces the humans to evacuate more people before permitting the Chosen to use their full power.  Increases evacuation requirement from 100 to 120.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 2)
        {
            name = "Fast Breeders";
            prereqsRequired = 0;
            cost = 1;
            prereqs = new Tech[0];
            description = "Develops your captive humans in order to let them give birth to Demons more frequently.  The Chosen will have to slog through more enemies before they can consider the extermination complete.  Increases extermination requirement from 100 to 140.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 3)
        {
            name = "Focus";
            prereqsRequired = 0;
            cost = 1;
            prereqs = new Tech[0];
            description = "Allows your captive humans to give birth to a body you can directly control in order to bring the fight to the Chosen, albeit only at great cost.  Once this has been purchased, options for 'Commander' bodies are unlocked here in the shop.  Every time you send a Commander into battle, it costs Evil Energy.  However, the effects can be quite strong.  The default Commander captures the target Chosen, causing her to start the battle surrounded for 2 turns.  This increases the opening level required to surround her later as normal.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 4)
        {
            name = "Coordinated Deployment";
            prereqsRequired = 2;
            cost = 2;
            prereqs = (new Tech[] {
                w.getTechs()[0], w.getTechs()[1]
            });
            description = "Develops your ability to give your Demons and Thralls detailed strategic orders.  By directing them toward civilian escape routes, you can further slow the evacuation process.  Increases evacuation requirement from 120 to 140.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 5)
        {
            name = "Eager Breeders";
            prereqsRequired = 2;
            cost = 2;
            prereqs = (new Tech[] {
                w.getTechs()[2], w.getTechs()[3]
            });
            description = "Modifies your captive humans further so that the process of giving birth to Demons causes them to experience mind-shattering ecstasy.  Their cooperation will allow you to grow your army even further.  Increases extermination requirement from 140 to 200.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 6)
        {
            name = "Weakness Sense";
            prereqsRequired = 2;
            cost = 2;
            prereqs = (new Tech[] {
                w.getTechs()[0], w.getTechs()[2]
            });
            description = "Further develops the psychic link between yourself and your human Thralls.  This allows them to more effectively respond to your instructions to torment surrounded Chosen.  Increases circumstances inflicted against surrounded Chosen by 50%.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 7)
        {
            name = "Enhanced Polymorphism";
            prereqsRequired = 2;
            cost = 2;
            prereqs = (new Tech[] {
                w.getTechs()[1], w.getTechs()[2]
            });
            description = "Enhances your control over the biology of Demons born to your captive humans.  Your army will be supplied with specialist Demons who have enlarged slime sacs, larger claws, or other similar modifications.  Increases trauma inflicted using the four basic attacks by 20%.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 8)
        {
            name = "Perception";
            prereqsRequired = 2;
            cost = 2;
            prereqs = (new Tech[] {
                w.getTechs()[0], w.getTechs()[3]
            });
            description = "Allows you to create Commanders capable of using a greater portion of your psychic capacity.  This finer control will make it possible to more effectively subdue captured Chosen.  For +1 Evil Energy per deployment, your Commander's capture duration will increase by 1.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 9)
        {
            name = "Patience";
            prereqsRequired = 2;
            cost = 2;
            prereqs = (new Tech[] {
                w.getTechs()[1], w.getTechs()[3]
            });
            description = "Develops your ability to control your Commander body.  Instead of launching your ambush immediately at the start of battle, you may save it until later - and it will function at full effectiveness regardless of the target's defense level.  There is no Evil Energy cost to equip your Commander with this ability, but giving the order to capture will consume your action for that round.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 10)
        {
            name = "Hunger";
            prereqsRequired = 2;
            cost = 3;
            prereqs = (new Tech[] {
                w.getTechs()[1], w.getTechs()[5]
            });
            description = "Strengthens your connection with your Commander body, endowing it with your instinct to gorge yourself on the suffering of the Chosen.  The resulting mouths covering your body are a 'Suppressor'-class upgrade which is not compatible with others of its type.  It can singlehandedly subdue a full-strength Chosen, binding them with countless tongues and gibbering your hateful intensions in the captured Chosen's ear.  There is no Evil Energy cost to apply this upgrade, but its attack is more specialized than that of the basic Commander.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 11)
        {
            name = "Lust";
            prereqsRequired = 2;
            cost = 3;
            prereqs = (new Tech[] {
                w.getTechs()[2], w.getTechs()[8]
            });
            description = "Strengthens your connection with your Commander body, endowing it with your instinct to fill all minds with sexual excitement.  The resulting tentacles covering your body are a 'Suppressor'-class upgrade which is not compatible with others of its type.  It can singlehandedly subdue a full-strength Chosen, binding them with writhing appendages which seek out their most sensitive places and force pleasure upon them.  There is no Evil Energy cost to apply this upgrade, but its attack is more specialized than that of the basic Commander.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 12)
        {
            name = "Anger";
            prereqsRequired = 2;
            cost = 3;
            prereqs = (new Tech[] {
                w.getTechs()[0], w.getTechs()[9]
            });
            description = "Strengthens your connection with your Commander body, endowing it with your instinct to cripple all opposition.  The resulting huge muscles covering your body are a 'Suppressor'-class upgrade which is not compatible with others of its type.  It can singlehandedly subdue a full-strength Chosen, beating them down with brute force and dealing grievous injuries even to their supernaturally-durable bodies.  There is no Evil Energy cost to apply this upgrade, but its attack is more specialized than that of the basic Commander.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 13)
        {
            name = "Mania";
            prereqsRequired = 2;
            cost = 3;
            prereqs = (new Tech[] {
                w.getTechs()[3], w.getTechs()[4]
            });
            description = "Strengthens your connection with your Commander body, endowing it with your instinct to merge with humanity and all its creations.  The resulting devices incorporated into your body are a 'Suppressor'-class upgrade which is not compatible with others of its type.  It can singlehandedly subdue a full-strength Chosen, binding them with chains and attacking them with mundane saws which are too weak to injure them, but which may expose them by shredding their clothes.  There is no Evil Energy cost to apply this upgrade, but its attack is more specialized than that of the basic Commander.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 14)
        {
            name = "Cunning";
            prereqsRequired = 2;
            cost = 2;
            prereqs = (new Tech[] {
                w.getTechs()[6], w.getTechs()[8]
            });
            description = "Allows you to create Commanders capable of using an even greater portion of your psychic capacity.  This refined control will make it possible to more effectively subdue captured Chosen.  For +1 Evil Energy per deployment, your Commander's capture duration will increase by 1.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 15)
        {
            name = "Persistence";
            prereqsRequired = 2;
            cost = 2;
            prereqs = (new Tech[] {
                w.getTechs()[7], w.getTechs()[9]
            });
            description = "Develops your ability to concentrate your psychic energy on a single aspect of the human mind.  Your Commander body will be correspondingly more pure and resilient.  For +2 Evil Energy per deployment, your Commander will be able to perform a second capture (provided that the battle does not end beforehand).";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 16)
        {
            name = "Human Collaborators";
            prereqsRequired = 2;
            cost = 5;
            prereqs = (new Tech[] {
                w.getTechs()[4], w.getTechs()[11]
            });
            description = "Develops your ability to make psychic contact with humans outside your hivemind.  Authority figures can be recruited to your side with the promise of Demonic powers or obedient Thralls to satisfy their every sexual fantasy.  In exchange, they will sabotage evacuation efforts.  Increases evacuation requirement from 140 to 160.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 17)
        {
            name = "Nursery Hives";
            prereqsRequired = 2;
            cost = 5;
            prereqs = (new Tech[] {
                w.getTechs()[5], w.getTechs()[13]
            });
            description = "Modifies your Demons' underground lair to contain biological equivalents of incubators and life support equipment, allowing your captured humans to constantly give birth to Demons with little risk of medical complications. Increases extermination requirement from 200 to 300.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 18)
        {
            name = "Vengeful Reconstitution";
            prereqsRequired = 2;
            cost = 5;
            prereqs = (new Tech[] {
                w.getTechs()[7], w.getTechs()[12]
            });
            description = "Broadens the range of physiological traits your Demons can have, endowing some of them with amorphous forms that allow them to eventually rebuild themselves by drawing on the resentment of the defeated.  This delays the end of battle until the usual conditions (full extermination progress and no Chosen surrounded or captured) are fulfilled for two turns in a row.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 19)
        {
            name = "Augmented Thralls";
            prereqsRequired = 2;
            cost = 5;
            prereqs = (new Tech[] {
                w.getTechs()[8], w.getTechs()[10]
            });
            description = "Greatly develops your ability to influence your Thralls' state of mind.  Their senses and predatory instincts will be enhanced, making them more effective at cornering the Chosen.  The first time each Chosen's defense level increases in each battle, it will only to 2 instead of to 3.  This means that defense levels will follow the pattern \"1, 2, 4, 6...\" instead of \"1, 3, 5, 7...\"";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 20)
        {
            name = "Intelligence";
            prereqsRequired = 2;
            cost = 5;
            prereqs = (new Tech[] {
                w.getTechs()[9], w.getTechs()[14]
            });
            description = "Allows you to create Commanders capable of using a tremendous portion of your psychic capacity.  This comprehensive control will make it possible to more effectively subdue captured Chosen.  For +2 Evil Energy per deployment, your Commander's capture duration will increase by 1.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 21)
        {
            name = "Versatility";
            prereqsRequired = 2;
            cost = 5;
            prereqs = (new Tech[] {
                w.getTechs()[6], w.getTechs()[15]
            });
            description = "Develops your ability to find common ground between two different aspects of the human mind and concentrate your psychic energy there in order to manifest both aspects at once.  For +5 Evil Energy per deployment, you can bypass the usual prohibition against putting a second Suppressor-class upgrade on your Commander.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 22)
        {
            name = "Ambition";
            prereqsRequired = 2;
            cost = 10;
            prereqs = (new Tech[] {
                w.getTechs()[15], w.getTechs()[19]
            });
            description = "Deepens your connection with your Commander body, endowing it with your desire to propagate yourself across all sapient life.  Your body will be equipped with a monstrous phallus capable of penetrating and ejaculating inside the Chosen.  This is a 'Defiler'-class upgrade which is not normally compatible with other Suppressor- or Defiler-class upgrades.  It functions like the Inseminate action, including its ability to inflict Morality Break, but its improved effectiveness comes at the cost of 6 Evil Energy per deployment.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 23)
        {
            name = "Dominance";
            prereqsRequired = 2;
            cost = 10;
            prereqs = (new Tech[] {
                w.getTechs()[12], w.getTechs()[16]
            });
            description = "Deepens your connection with your Commander body, endowing it with your desire to inflict mind-breaking pleasure until all resistance is unthinkable.  Your body will be equipped with an internal chamber which pulls Chosen inside and stimulates them to climax.  This is a 'Defiler'-class upgrade which is not normally compatible with other Suppressor- or Defiler-class upgrades.  It functions like the Force Orgasm action, including its ability to inflict Innocence Break, but its improved effectiveness comes at the cost of 6 Evil Energy per deployment.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 24)
        {
            name = "Spite";
            prereqsRequired = 2;
            cost = 10;
            prereqs = (new Tech[] {
                w.getTechs()[14], w.getTechs()[18]
            });
            if(w.getGenderBalance()[2] == 0)
                description = "Deepens your connection with your Commander body, endowing it with your desire to punish those who dare to stand in your way.  Your body will be equipped with several muscular arms which will torture and violate any Chosen they manage to get their hands on.  This is a 'Defiler'-class upgrade which is not normally compatible with other Suppressor- or Defiler-class upgrades.  It functions like the Sodomize action, including its ability to inflict Confidence Break, but its improved effectiveness comes at the cost of 6 Evil Energy per deployment.";
            else
            if(w.getGenderBalance()[1] == 0 && w.getGenderBalance()[3] == 0)
                description = "Deepens your connection with your Commander body, endowing it with your desire to punish those who dare to stand in your way.  Your body will be equipped with several muscular arms which will torture and violate any Chosen they manage to get their hands on.  This is a 'Defiler'-class upgrade which is not normally compatible with other Suppressor- or Defiler-class upgrades.  It functions like the Torture action, including its ability to inflict Confidence Break, but its improved effectiveness comes at the cost of 6 Evil Energy per deployment.";
            else
                description = "Deepens your connection with your Commander body, endowing it with your desire to punish those who dare to stand in your way.  Your body will be equipped with several muscular arms which will torture and violate any Chosen they manage to get their hands on.  This is a 'Defiler'-class upgrade which is not normally compatible with other Suppressor- or Defiler-class upgrades.  It functions like the Sodomize/Torture action, including its ability to inflict Confidence Break, but its improved effectiveness comes at the cost of 6 Evil Energy per deployment.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 25)
        {
            name = "Vanity";
            prereqsRequired = 2;
            cost = 10;
            prereqs = (new Tech[] {
                w.getTechs()[11], w.getTechs()[17]
            });
            description = "Deepens your connection with your Commander body, endowing it with your desire to leave no doubt as to your superiority over your foes.  Your body will be equipped with electronic tendrils which can hijack communications lines and send live footage of the battle to people who otherwise wouldn't be watching.  This is a 'Defiler'-class upgrade which is not normally compatible with other Suppressor- or Defiler-class upgrades.  It functions like the Broadcast action, including its ability to inflict Dignity Break, but its improved effectiveness comes at the cost of 6 Evil Energy per deployment.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 26)
        {
            name = "Genius";
            prereqsRequired = 2;
            cost = 10;
            prereqs = (new Tech[] {
                w.getTechs()[13], w.getTechs()[20]
            });
            description = "Allows you to create Commanders capable of using a majority of your psychic capacity.  This optimized control will make it possible to more effectively subdue captured Chosen.  For +2 Evil Energy per deployment, your Commander's capture duration will increase by 1.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 27)
        {
            name = "Determination";
            prereqsRequired = 2;
            cost = 10;
            prereqs = (new Tech[] {
                w.getTechs()[10], w.getTechs()[21]
            });
            description = "Develops your ability to tap into the psychic energy associated with aspects of the human mind.  Your Commander body will be correspondingly more pure and resilient.  For +5 Evil Energy per deployment, your Commander will be able to perform a third capture (provided that the battle does not end beforehand).";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 28)
        {
            name = "Soul Resonance";
            prereqsRequired = 2;
            cost = 20;
            prereqs = (new Tech[] {
                w.getTechs()[16], w.getTechs()[22]
            });
            description = "Spreads your ability to influence thoughts even beyond the Demonic hivemind.  Civilians in the area of Demonic raids will feel subconsciously motivated to resist the authorities' attempts to get them to evacuate.  Increases evacuation requirement from 160 to 200.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 29)
        {
            name = "Passion Release";
            prereqsRequired = 2;
            cost = 20;
            prereqs = (new Tech[] {
                w.getTechs()[17], w.getTechs()[24]
            });
            description = "Modifies your human captives' brains so that they become mentally fixated on the power and supremecy of the Demons - and on the suffering and defeat of those who oppose them.  Their absolute confidence in your victory will produce psychic energy far disproportionate to their numbers, rivalling the power of the worldwide public.  Increases extermination requirement from 300 to 500.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 30)
        {
            name = "Reality Sealing";
            prereqsRequired = 2;
            cost = 20;
            prereqs = (new Tech[] {
                w.getTechs()[18], w.getTechs()[26]
            });
            description = "Allows you to apply your mastery of psychic energy to directly influence the local laws of physics.  By channeling your power through your army, you can focus on creating a region where the Chosen find that reality itself conspires against them.  Until extermination is completed, the 'Do Nothing' action is replaced with 'Barrier', which increases all damage taken for the rest of the battle by 5% (compounding).";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 31)
        {
            name = "Networked Consciousness";
            prereqsRequired = 2;
            cost = 20;
            prereqs = (new Tech[] {
                w.getTechs()[19], w.getTechs()[23]
            });
            description = "Dissolves the distinction between different Thralls' minds and desires.  This will allow them to display supernatural reaction time and intelligence by relying on each other's senses and neural processing power.  Whenever the Thralls Surround one of the Chosen, you will immediately be able to give those Thralls another order on the same turn.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 32)
        {
            name = "Transcendence";
            prereqsRequired = 2;
            cost = 20;
            prereqs = (new Tech[] {
                w.getTechs()[20], w.getTechs()[27]
            });
            description = "Greatly develops the depth of your control over the psychic energy associated with aspects of the human mind.  Your Commander body will be correspondingly more pure and resilient.  For +10 Evil Energy per deployment, your Commander will be able to perform a fourth capture (provided that the battle does not end beforehand).";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index == 33)
        {
            name = "Synthesis";
            prereqsRequired = 2;
            cost = 20;
            prereqs = (new Tech[] {
                w.getTechs()[21], w.getTechs()[25]
            });
            description = "Allows you to craft complex psychic constructs which use different layers of the human mind to reinforce one another.  For +10 Evil Energy per deployment, you can put both a Suppressor-class upgrade and a Defiler-class upgrade on the same Commander.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index != 34)
            break MISSING_BLOCK_LABEL_2110;
        name = "Impregnation";
        prereqsRequired = 2;
        cost = 40;
        prereqs = (new Tech[] {
            w.getTechs()[22], w.getTechs()[29]
        });
        description = "Optimizes your connection with your Commander body, endowing it with the ability to seed your offspring even in one of the Chosen";
        if(w.getGenderBalance()[2] <= 0) goto _L2; else goto _L1
_L1:
        this;
        description;
        JVM INSTR new #314 <Class StringBuilder>;
        JVM INSTR dup_x1 ;
        JVM INSTR swap ;
        String.valueOf();
        StringBuilder();
        ", regardless of whether they're male or female";
        append();
        toString();
        description;
_L2:
        this;
        description;
        JVM INSTR new #314 <Class StringBuilder>;
        JVM INSTR dup_x1 ;
        JVM INSTR swap ;
        String.valueOf();
        StringBuilder();
        ".  The targets' souls must be blackened with the Slaughter of many victims in order to be susceptible to this effect, but the resulting Total Morality Break as they're forced to bear an enemy of humanity will irrevocably sever their connection to society.  This is a Punisher-class upgrade which is not compatible with other Suppressor-, Defiler-, or Punisher-class upgrades, but it has no additional cost to apply and focuses strongly on FEAR and HATE damage.";
        append();
        toString();
        description;
        break MISSING_BLOCK_LABEL_2486;
        if(index == 35)
        {
            name = "Hypnosis";
            prereqsRequired = 2;
            cost = 40;
            prereqs = (new Tech[] {
                w.getTechs()[23], w.getTechs()[33]
            });
            description = "Optimizes your connection with your Commander body, endowing it with the ability to directly dominate the mind of the Chosen.  The targets must Fantasize on a regular basis in order to weaken their grasp of reality enough for this to work, but the resulting Total Innocence Break as they experience the ecstasy of the Demonic hivemind will render them permanently weaker to your temptations.  This is a Punisher-class upgrade which is not compatible with other Suppressor-, Defiler-, or Punisher-class upgrades, but it has no additional cost to apply and focuses strongly on DISG and PLEA damage.";
            break MISSING_BLOCK_LABEL_2486;
        }
        if(index != 36)
            break MISSING_BLOCK_LABEL_2303;
        name = "Drain";
        prereqsRequired = 2;
        cost = 40;
        prereqs = (new Tech[] {
            w.getTechs()[24], w.getTechs()[28]
        });
        description = "Optimizes your connection with your Commander body, endowing it with the ability to absorb even the righteous energy of the Chosen.  The targets must be desperate enough for death to Detonate themselves over and over again or else they won't have the subconscious willingness that allows you to do this, but the resulting Total Confidence Break as they realize that they can only have peace by giving up their power will completely break their will to actually defeat you.  This is a Punisher-class upgrade which is not compatible with other Suppressor-, Defiler-, or Punisher-class upgrades, but it has no additional cost to apply and focuses strongly on ";
        if(!w.tickle().booleanValue())
            break MISSING_BLOCK_LABEL_2272;
        this;
        description;
        JVM INSTR new #314 <Class StringBuilder>;
        JVM INSTR dup_x1 ;
        JVM INSTR swap ;
        String.valueOf();
        StringBuilder();
        "TICK and ANTI damage.";
        append();
        toString();
        description;
        break MISSING_BLOCK_LABEL_2486;
        this;
        description;
        JVM INSTR new #314 <Class StringBuilder>;
        JVM INSTR dup_x1 ;
        JVM INSTR swap ;
        String.valueOf();
        StringBuilder();
        "PAIN and INJU damage.";
        append();
        toString();
        description;
        break MISSING_BLOCK_LABEL_2486;
        if(index == 37)
        {
            name = "Parasitism";
            prereqsRequired = 2;
            cost = 40;
            prereqs = (new Tech[] {
                w.getTechs()[25], w.getTechs()[30]
            });
            description = "Optimizes your connection with your Commander body, endowing it with the ability to latch on to a target's psychic signature and influence its physical manifestation.  The targets must be known to Striptease and perform other degrading acts in order for the effect to persist beyond your direct influence, but the resulting Total Dignity Break as the public sees the targets' sinful nature on full display will bring them within a hair's breadth of losing their Chosen powers.  This is a Punisher-class upgrade which is not compatible with other Suppressor-, Defiler-, or Punisher-class upgrades, but it has no additional cost to apply and focuses strongly on SHAM and EXPO.";
        } else
        if(index == 38)
        {
            name = "Mastery";
            prereqsRequired = 2;
            cost = 40;
            prereqs = (new Tech[] {
                w.getTechs()[26], w.getTechs()[32]
            });
            description = "Perfects your control over the psychic energy associated with aspects of the human mind.  Your Commander body will reach maximum purity and resilience.  For +20 Evil Energy per deployment, your Commander will be able to perform a fifth capture.";
        } else
        if(index == 39)
        {
            name = "Relentlessness";
            prereqsRequired = 2;
            cost = 40;
            prereqs = (new Tech[] {
                w.getTechs()[27], w.getTechs()[31]
            });
            description = "Extends your innate control over space and time to the region around your Commander body as well.  The Chosen will no longer be able to let their guard down after accumulating enough psychic energy to take flight.  For +10 Evil Energy per deployment, your Commander will be able to capture Chosen who are flying above the battlefield.";
        }
    }

    public Boolean isOwned()
    {
        return owned;
    }

    public void printSummary(WorldState w, JTextPane t)
    {
        w.append(t, (new StringBuilder(String.valueOf(name))).append("\nCost: ").append(cost).append(" EE\nPrerequisites: ").toString());
        for(int i = 0; i < prereqs.length; i++)
        {
            if(prereqs[i].isOwned().booleanValue())
                w.append(t, prereqs[i].getName());
            else
                w.grayAppend(t, prereqs[i].getName());
            if(i < prereqs.length - 1)
                w.append(t, ", ");
        }

        if(prereqs.length == 0)
            w.append(t, "None");
        w.append(t, "\nLeads to: ");
        int leadsCount = 0;
        int leadsCovered = 0;
        for(int i = 0; i < w.getTechs().length; i++)
        {
            for(int j = 0; j < w.getTechs()[i].getPrereqs().length; j++)
                if(w.getTechs()[i].getPrereqs()[j] == this)
                    leadsCount++;

        }

        for(int i = 0; i < w.getTechs().length; i++)
        {
            for(int j = 0; j < w.getTechs()[i].getPrereqs().length; j++)
                if(w.getTechs()[i].getPrereqs()[j] == this)
                {
                    if(leadsCovered != 0)
                        w.append(t, "; ");
                    leadsCovered++;
                    w.append(t, w.getTechs()[i].getName());
                    w.append(t, " (with ");
                    int withCovered = 0;
                    for(int k = 0; k < w.getTechs()[i].getPrereqs().length; k++)
                        if(j != k)
                        {
                            if(withCovered > 0)
                                w.append(t, ", ");
                            withCovered++;
                            if(w.getTechs()[i].getPrereqs()[k].isOwned().booleanValue())
                                w.append(t, w.getTechs()[i].getPrereqs()[k].getName());
                            else
                                w.grayAppend(t, w.getTechs()[i].getPrereqs()[k].getName());
                        }

                    w.append(t, ")");
                }

        }

        if(leadsCount == 0)
            w.grayAppend(t, "(not implemented)");
        w.append(t, (new StringBuilder("\n")).append(description).toString());
    }

    public String getName()
    {
        return name;
    }

    public Tech[] getPrereqs()
    {
        return prereqs;
    }

    public Tech()
    {
        prereqsRequired = 1;
        cost = 1;
        name = "";
        description = "";
        tooltip = "";
        prereqs = new Tech[0];
        owned = Boolean.valueOf(false);
    }

    private static final long serialVersionUID = 4L;
    int prereqsRequired;
    int cost;
    String name;
    String description;
    String tooltip;
    Tech prereqs[];
    Boolean owned;
}
