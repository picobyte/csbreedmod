
import java.io.Serializable;
import javax.swing.JTextPane;

public class Tech
    implements Serializable
{

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
        } else
        if(index == 1)
        {
            name = "Wide Deployment";
            prereqsRequired = 0;
            cost = 1;
            prereqs = new Tech[0];
            description = "Develops your ability to command Demons and Thralls in a wider area at once.  This forces the humans to evacuate more people before permitting the Chosen to use their full power.  Increases evacuation requirement from 100 to 120.";
        } else
        if(index == 2)
        {
            name = "Fast Breeders";
            prereqsRequired = 0;
            cost = 1;
            prereqs = new Tech[0];
            description = "Develops your captive humans in order to let them give birth to Demons more frequently.  The Chosen will have to slog through more enemies before they can consider the extermination complete.  Increases extermination requirement from 100 to 140.";
        } else
        if(index == 3)
        {
            name = "Focus";
            prereqsRequired = 0;
            cost = 1;
            prereqs = new Tech[0];
            description = "Allows your captive humans to give birth to a body you can directly control in order to bring the fight to the Chosen, albeit only at great cost.  Once this has been purchased, options for 'Commander' bodies are unlocked here in the shop.  Every time you send a Commander into battle, it costs Evil Energy.  However, the effects can be quite strong.  The default Commander captures the target Chosen, causing her to start the battle surrounded for 2 turns.  This increases the opening level required to surround her later as normal.";
        } else
        if(index == 4)
        {
            name = "Coordinated Deployment";
            prereqsRequired = 2;
            cost = 2;
            prereqs = (new Tech[] {
                w.getTechs()[0], w.getTechs()[1]
            });
            description = "Develops your ability to give your Demons and Thralls detailed strategic orders.  By directing them toward civilian escape routes, you can further slow the evacuation process.  Increases evacuation requirement from 120 to 140.";
        } else
        if(index == 5)
        {
            name = "Eager Breeders";
            prereqsRequired = 2;
            cost = 2;
            prereqs = (new Tech[] {
                w.getTechs()[2], w.getTechs()[3]
            });
            description = "Modifies your captive humans further so that the process of giving birth to Demons causes them to experience mind-shattering ecstasy.  Their cooperation will allow you to grow your army even further.  Increases extermination requirement from 140 to 200.";
        } else
        if(index == 6)
        {
            name = "Weakness Sense";
            prereqsRequired = 2;
            cost = 2;
            prereqs = (new Tech[] {
                w.getTechs()[0], w.getTechs()[2]
            });
            description = "Further develops the psychic link between yourself and your human Thralls.  This allows them to more effectively respond to your instructions to torment surrounded Chosen.  Increases circumstances inflicted against surrounded Chosen by 50%.";
        } else
        if(index == 7)
        {
            name = "Enhanced Polymorphism";
            prereqsRequired = 2;
            cost = 2;
            prereqs = (new Tech[] {
                w.getTechs()[1], w.getTechs()[2]
            });
            description = "Enhances your control over the biology of Demons born to your captive humans.  Your army will be supplied with specialist Demons who have enlarged slime sacs, larger claws, or other similar modifications.  Increases trauma inflicted using the four basic attacks by 20%.";
        } else
        if(index == 8)
        {
            name = "Perception";
            prereqsRequired = 2;
            cost = 2;
            prereqs = (new Tech[] {
                w.getTechs()[0], w.getTechs()[3]
            });
            description = "Allows you to create Commanders capable of using a greater portion of your psychic capacity.  This finer control will make it possible to more effectively subdue captured Chosen.  For +1 Evil Energy per deployment, your Commander's capture duration will increase by 1.";
        } else
        if(index == 9)
        {
            name = "Patience";
            prereqsRequired = 2;
            cost = 2;
            prereqs = (new Tech[] {
                w.getTechs()[1], w.getTechs()[3]
            });
            description = "Develops your ability to control your Commander body.  Instead of launching your ambush immediately at the start of battle, you may save it until later - and it will function at full effectiveness regardless of the target's defense level.  There is no Evil Energy cost to equip your Commander with this ability, but giving the order to capture will consume your action for that round.";
        } else
        if(index == 10)
        {
            name = "Hunger";
            prereqsRequired = 2;
            cost = 3;
            prereqs = (new Tech[] {
                w.getTechs()[1], w.getTechs()[5]
            });
            description = "Strengthens your connection with your Commander body, endowing it with your instinct to gorge yourself on the suffering of the Chosen.  The resulting mouths covering your body are a 'Suppressor'-class upgrade which is not compatible with others of its type.  It can singlehandedly subdue a full-strength Chosen, binding them with countless tongues and gibbering your hateful intensions in the captured Chosen's ear.  There is no Evil Energy cost to apply this upgrade, but its attack is more specialized than that of the basic Commander.";
        } else
        if(index == 11)
        {
            name = "Lust";
            prereqsRequired = 2;
            cost = 3;
            prereqs = (new Tech[] {
                w.getTechs()[2], w.getTechs()[8]
            });
            description = "Strengthens your connection with your Commander body, endowing it with your instinct to fill all minds with sexual excitement.  The resulting tentacles covering your body are a 'Suppressor'-class upgrade which is not compatible with others of its type.  It can singlehandedly subdue a full-strength Chosen, binding them with writhing appendages which seek out their most sensitive places and force pleasure upon them.  There is no Evil Energy cost to apply this upgrade, but its attack is more specialized than that of the basic Commander.";
        } else
        if(index == 12)
        {
            name = "Anger";
            prereqsRequired = 2;
            cost = 3;
            prereqs = (new Tech[] {
                w.getTechs()[0], w.getTechs()[9]
            });
            description = "Strengthens your connection with your Commander body, endowing it with your instinct to cripple all opposition.  The resulting huge muscles covering your body are a 'Suppressor'-class upgrade which is not compatible with others of its type.  It can singlehandedly subdue a full-strength Chosen, beating them down with brute force and dealing grievous injuries even to their supernaturally-durable bodies.  There is no Evil Energy cost to apply this upgrade, but its attack is more specialized than that of the basic Commander.";
        } else
        if(index == 13)
        {
            name = "Mania";
            prereqsRequired = 2;
            cost = 3;
            prereqs = (new Tech[] {
                w.getTechs()[3], w.getTechs()[4]
            });
            description = "Strengthens your connection with your Commander body, endowing it with your instinct to merge with humanity and all its creations.  The resulting devices incorporated into your body are a 'Suppressor'-class upgrade which is not compatible with others of its type.  It can singlehandedly subdue a full-strength Chosen, binding them with chains and attacking them with mundane saws which are too weak to injure them, but which may expose them by shredding their clothes.  There is no Evil Energy cost to apply this upgrade, but its attack is more specialized than that of the basic Commander.";
        } else
        if(index == 14)
        {
            name = "Cunning";
            prereqsRequired = 2;
            cost = 2;
            prereqs = (new Tech[] {
                w.getTechs()[6], w.getTechs()[8]
            });
            description = "Allows you to create Commanders capable of using an even greater portion of your psychic capacity.  This refined control will make it possible to more effectively subdue captured Chosen.  For +1 Evil Energy per deployment, your Commander's capture duration will increase by 1.";
        } else
        if(index == 15)
        {
            name = "Persistence";
            prereqsRequired = 2;
            cost = 2;
            prereqs = (new Tech[] {
                w.getTechs()[7], w.getTechs()[9]
            });
            description = "Develops your ability to concentrate your psychic energy on a single aspect of the human mind.  Your Commander body will be correspondingly more pure and resilient.  For +2 Evil Energy per deployment, your Commander will be able to perform a second capture (provided that the battle does not end beforehand).";
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
        prereqs = new Tech[0];
        owned = Boolean.valueOf(false);
    }

    private static final long serialVersionUID = 4L;
    int prereqsRequired;
    int cost;
    String name;
    String description;
    Tech prereqs[];
    Boolean owned;
}
