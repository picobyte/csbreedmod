import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Body implements Serializable {
	
	private static final long serialVersionUID = 4L;
	
	Chosen chosenOwner = null;
	Forsaken forsakenOwner = null;
	public Boolean transformed = true;
	Boolean imprisoned = false;
	
	transient Activity[] inProgress = new Activity[0];
	Body[] targets = new Body[0];
	
	public Appearance bodyType;
	
	public int[] parts = new int[14];
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
	
	public long currentFEAR;
	public long currentDISG;
	public long currentPAIN;
	public long currentSHAM;
	public long currentHATE;
	public long currentPLEA;
	public long currentINJU;
	public long currentEXPO;
	
	int orgasms = 0;
	int specialLine = 0;
	
	public static enum Appearance {
		CUTEGIRL,
		CUTEBOY,
		MONSTER
	}
	
	public Boolean coerced() {
		return imprisoned || (chosenOwner != null && (chosenOwner.truce || chosenOwner.drained));
	}
	
	public long[] currentDamage() {
		return new long[]{currentFEAR, currentDISG, currentPAIN, currentSHAM, currentHATE, currentPLEA, currentINJU, currentEXPO};
	}
	
	public long[] InflictDamage(long[] incoming) {
		for (int i = 0; i < 8; i++) {
			Boolean overflow = false;
			if (i == 0 && incoming[i] > 0) {
				//FEAR: lower with high Confidence, low Obedience
				//      higher with PAIN
				//      increases Consent modifier
				long magnitude = currentFEAR;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*3;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				magnitude = currentPAIN;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*2;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				incoming[i] = incoming[i]*(150-getConfidence())*(100+getObedience())/(100*150);
				magnitude = currentINJU;
				while (magnitude >= 1000) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]/4;
				}
				if (forsakenOwner == null && chosenOwner != null) {
					incoming[i] = incoming[i]/2;
				}
			} else if (i == 1 && incoming[i] > 0) {
				//DISG: lower with low Innocence, high Deviancy
				//      higher with HATE
				//      decreases Consent modifier
				long magnitude = currentDISG;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*5;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				magnitude = currentHATE;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*2;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				incoming[i] = incoming[i]*(200+getInnocence())*(100-getDeviancy())/(250*50);
			} else if (i == 2 && incoming[i] > 0) {
				//PAIN: constant increase
				long magnitude = currentPAIN;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*4;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				magnitude = currentINJU;
				while (magnitude >= 1000) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]/2;
				}
			} else if (i == 3 && incoming[i] > 0) {
				//SHAM: lower with low Dignity
				//      higher with EXPO
				long magnitude = currentSHAM;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*3;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				magnitude = currentEXPO;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*3/2;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				incoming[i] = incoming[i]*(150-getDignity())/(100);
				magnitude = currentINJU;
				while (magnitude >= 1000) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]/2;
				}
			} else if (i == 4 && incoming[i] > 0) {
				//HATE: lower with high Morality, low Hostility, high Obedience
				//      higher with DISG, PAIN, SHAM, TIRE
				//      decreases Consent modifier
				if (forsakenOwner == null && chosenOwner != null) {
					if (chosenOwner.negotiations == 0) {
						incoming[i] = incoming[i]*2;
					} else if (chosenOwner.truce) {
						incoming[i] = incoming[i]/2;
					}
				}
				long magnitude = currentHATE;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*5;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				magnitude = currentDISG;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*3/2;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				magnitude = currentPAIN;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*3/2;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				magnitude = currentSHAM;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*3/2;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				magnitude = currentINJU;
				while (magnitude >= 1000) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*2;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				magnitude = getObedience();
				incoming[i] = incoming[i]*(150-getMorality())*(100+getHostility())*(100-getObedience())/(100*150*50);
				while (magnitude > 60) {
					magnitude--;
					incoming[i] = incoming[i]*9/10;
				}
				magnitude = currentINJU;
				while (magnitude >= 1000) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]/3;
				}
			} else if (i == 5 && incoming[i] > 0) {
				//PLEA: lower with low Deviancy
				//      lower with DISG, variable with PAIN and SHAM depending on Deviancy
				//      increases consent modifier
				long magnitude = currentPLEA;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*3;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				magnitude = currentSHAM;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*(50+getDeviancy())/50;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				if (isDemonLord() == false) {
					incoming[i] = incoming[i]*(50+getDeviancy())/100;
				}
				magnitude = currentDISG;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*2/3;
				}
				magnitude = currentPAIN;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*(75+getDeviancy())/175;
				}
				magnitude = currentINJU;
				while (magnitude >= 1000) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]/4;
				}
			} else if (i == 6 && incoming[i] > 0) {
				//TIRE: higher with FEAR, DISG, PAIN, SHAM, HATE, PLEA
				//      no effects until lv 2
				//      decreases everything but DISG and EXPO
				long magnitude = currentINJU;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*5;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				magnitude = currentFEAR;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*4/3;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				magnitude = currentDISG;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*4/3;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				magnitude = currentPAIN;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*3/2;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				magnitude = currentSHAM;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*4/3;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				magnitude = currentHATE;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*4/3;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
				magnitude = currentPLEA;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*3/2;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
			} else if (i == 7 && incoming[i] > 0) {
				//EXPO: constant increase
				//      can be set to various levels with actions
				long magnitude = currentEXPO;
				while (magnitude >= 100) {
					magnitude = magnitude/10;
					incoming[i] = incoming[i]*10;
					if (incoming[i] > Long.MAX_VALUE/10000000) {
						overflow = true;
					}
				}
			}
			if (overflow) {
				incoming[i] = 0;
			}
		}
		return incoming;
	}
	public void advanceAction(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s) {
		if (this == w.sceneParticipants[0]) {
			w.sceneDuration++;
		}
		int nextActor = 0;
		for (int i = 0; i < w.sceneParticipants.length-1; i++) {
			if (w.sceneParticipants[i] == this) {
				nextActor = i+1;
			}
		}
		w.append(t, "\n\n" + w.getSeparator());
		if (w.sceneParticipants[nextActor] == w.lordBody) {
			w.append(t, "\n\n");
			w.sceneParticipants[nextActor].LordDamage(t, w);
		}
		Boolean endScene = false;
		if (forsakenOwner == null && chosenOwner != null) {
			if (getHATELevel() >= 3) {
				endScene = true;
			}
			if (getPLEALevel() >= 3 && chosenOwner.innocence > 66 && chosenOwner.cVirg) {
				endScene = true;
			}
			if (Project.PenetratedAnally.isInProgress(this, null) && Project.BeLubricated.isInProgress(this, null) == false && chosenOwner.truce == false) {
				endScene = true;
			}
			if (chosenOwner.truce || chosenOwner.drained) {
				if (getINJULevel() >= 4) {
					endScene = true;
				}
			} else {
				if (getINJULevel() >= 3) {
					endScene = true;
				}
			}
		}
		if (endScene) {
			p.removeAll();
			w.append(t, "\n\n");
			if (getHATELevel() >= 3) {
				w.append(t, OwnerName() + " isn't able to tolerate " + w.lordBody.ownerName() + "'s provocations any longer.  ");
			} else if (getPLEALevel() >= 3 && chosenOwner.innocence > 66 && chosenOwner.cVirg) {
				w.append(t, OwnerName() + " is overwhelmed by the unfamiliar pleasure surging through " + himHer() + ", and " + heShe() + " acts without thinking.  ");
			} else if (Project.PenetratedAnally.isInProgress(this, null) && Project.BeLubricated.isInProgress(this, null) == false && chosenOwner.truce == false) {
				w.append(t, "Even before " + w.lordBody.ownerName() + " can get completely inside " + himHer() + ", " + ownerName() + " puts a stop to it.  ");
			} else {
				w.append(t, OwnerName() + " is too tired to continue, so " + heShe() + " decides to leave.  ");
			}
			if (Project.BeTied.isInProgress(this, null)) {
				if (transformed) {
					w.append(t, HeShe() + " uses " + hisHer() + " superhuman strength to tear off " + hisHer() + " bindings, then ");
				} else {
					w.append(t, HeShe() + " transforms into " + hisHer() + " Chosen form and uses " + hisHer() + " superhuman strength to tear off " + hisHer() + " bindings, then ");
				}
			} else {
				w.append(t, HeShe() + " ");
			}
			if (chosenOwner.negotiations > 0 || chosenOwner.drained || getINJULevel() >= 3) {
				if (w.sceneLocation == Activity.Location.ALLEY) {
					w.append(t, "makes " + hisHer() + " escape, flying off into the sky.");
				} else if (w.sceneLocation == Activity.Location.ROOM) {
					w.append(t, "guides " + w.lordBody.ownerName() + " to the door, apologetic but still flatly refusing to let " + w.lordBody.himHer() + " stay.");
				}
			} else {
				w.append(t, "strikes " + w.lordBody.ownerName() + " down with " + hisHer() + " " + chosenOwner.weapon + ".  As " + w.lordBody.hisHer() + " corporeal form has been destroyed, " + w.lordBody.ownerName() + " can only watch " + ownerName() + " fly away.");
			}
			JButton Continue = new JButton("Continue");
			Continue.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (w.active) {
						Project.Shop(t, p, f, w);
					} else {
						WriteObject wobj = new WriteObject();
						wobj.serializeSaveData(s);
						Project.IntroOne(t, p, f, w);
					}
				}
			});
			p.add(Continue);
			p.validate();
			p.repaint();
		} else {
			w.sceneParticipants[nextActor].PickActivity(t, p, f, w, s);
		}
	}
	
	public void Continue(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s) {
		p.removeAll();
		JButton Wait = new JButton("Continue");
		Wait.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				advanceAction(t, p, f, w, s);
			}
		});
		if (inProgress.length == 0 && this == w.lordBody) {
			Wait.setText("Wait");
		}
		p.add(Wait);
		p.validate();
		p.repaint();
	}
	
	public void InitializeActivities() {
		Project.Talk.pickable = false;
		Project.Talk.sendReqs[MOUTH] = 1;
		Project.TweakClit.counterpart = Project.ClitTweaked;
		Project.TweakClit.sendReqs[HAND] = 1;
		Project.TweakClit.targeted = true;
		Project.TweakClit.enders = new Activity[]{Project.StopActing, Project.Escape};
		Project.ClitTweaked.counterpart = Project.TweakClit;
		Project.ClitTweaked.sendReqs[CLIT] = 1;
		Project.ClitTweaked.causesOrgasm = true;
		Project.ClitTweaked.targeted = true;
		Project.ClitTweaked.pickable = false;
		Project.ClitTweaked.enders = new Activity[]{Project.Escape};
		Project.SpreadLegs.sendReqs[FOOT] = 2;
		Project.SpreadLegs.enders = new Activity[]{Project.StopActing, Project.Escape};
		Project.SpreadLegs.pickable = false;
		Project.Praise.sendReqs[MOUTH] = 1;
		Project.Praise.targeted = true;
		Project.Praise.endsSelf = true;
		Project.Insult.sendReqs[MOUTH] = 1;
		Project.Insult.targeted = true;
		Project.Insult.endsSelf = true;
		Project.Insult.hostile = true;
		Project.PushDown.sendReqs[HIPS] = 1;
		Project.PushDown.sendReqs[FOOT] = 2;
		Project.PushDown.nonBlocking = true;
		Project.PushDown.targeted = true;
		Project.PushDown.counterpart = Project.PullDown;
		Project.PushDown.enders = new Activity[]{Project.StopActing, Project.Escape};
		Project.PullDown.sendReqs[HIPS] = 1;
		Project.PullDown.targeted = true;
		Project.PullDown.counterpart = Project.PushDown;
		Project.PullDown.enders = new Activity[]{Project.Escape};
		Project.Escape.sendReqs[HAND] = 1;
		Project.Escape.sendReqs[FOOT] = 2;
		Project.Escape.targeted = true;
		Project.Escape.endsSelf = true;
		Project.Escape.pickable = false;
		Project.Escape.hostile = true;
		Project.StopActing.targeted = true;
		Project.StopActing.endsSelf = true;
		Project.StopActing.pickable = false;
		Project.StopActing.hostile = true;
		Project.TieUp.sendReqs[HAND] = 1;
		Project.TieUp.targeted = true;
		Project.TieUp.nonBlocking = true;
		Project.TieUp.counterpart = Project.BeTied;
		Project.TieUp.pickable = false;
		Project.BeTied.sendReqs[HAND] = 2;
		Project.BeTied.sendReqs[FOOT] = 2;
		Project.BeTied.targeted = true;
		Project.BeTied.counterpart = Project.TieUp;
		Project.BeTied.pickable = false;
		Project.StrokeCock.sendReqs[HAND] = 1;
		Project.StrokeCock.targeted = true;
		Project.StrokeCock.counterpart = Project.CockStroked;
		Project.StrokeCock.enders = new Activity[]{Project.StopActing, Project.Escape};
		Project.StrokeCock.pickable = true;
		Project.CockStroked.sendReqs[PENIS] = 1;
		Project.CockStroked.causesOrgasm = true;
		Project.CockStroked.targeted = true;
		Project.CockStroked.counterpart = Project.StrokeCock;
		Project.CockStroked.enders = new Activity[]{Project.Escape};
		Project.CockStroked.pickable = false;
		Project.Lubricate.sendReqs[HAND] = 1;
		Project.Lubricate.nonBlocking = true;
		Project.Lubricate.targeted = true;
		Project.Lubricate.counterpart = Project.BeLubricated;
		Project.Lubricate.endsSelf = true;
		Project.Lubricate.pickable = false;
		Project.BeLubricated.sendReqs[ASS] = 1;
		Project.BeLubricated.nonBlocking = true;
		Project.BeLubricated.pickable = false;
		Project.VaginalPenetrate.sendReqs[PENIS] = 1;
		Project.VaginalPenetrate.causesOrgasm = true;
		Project.VaginalPenetrate.targeted = true;
		Project.VaginalPenetrate.counterpart = Project.PenetratedVaginally;
		Project.VaginalPenetrate.enders = new Activity[]{Project.StopActing, Project.Escape};
		Project.PenetratedVaginally.sendReqs[PUSSY] = 1;
		Project.PenetratedVaginally.causesOrgasm = true;
		Project.PenetratedVaginally.targeted = true;
		Project.PenetratedVaginally.counterpart = Project.VaginalPenetrate;
		Project.PenetratedVaginally.enders = new Activity[]{Project.Escape};
		Project.AnalPenetrate.sendReqs[PENIS] = 1;
		Project.AnalPenetrate.causesOrgasm = true;
		Project.AnalPenetrate.targeted = true;
		Project.AnalPenetrate.counterpart = Project.PenetratedAnally;
		Project.AnalPenetrate.enders = new Activity[]{Project.StopActing, Project.Escape};
		Project.PenetratedAnally.sendReqs[ASS] = 1;
		Project.PenetratedAnally.causesOrgasm = true;
		Project.PenetratedAnally.targeted = true;
		Project.PenetratedAnally.counterpart = Project.AnalPenetrate;
		Project.PenetratedAnally.enders = new Activity[]{Project.Escape};
		Project.StripOther.sendReqs[HAND] = 1;
		Project.StripOther.targeted = true;
		Project.StripOther.counterpart = Project.Stripped;
		Project.StripOther.enders = new Activity[]{Project.StopActing, Project.Escape};
		Project.StripOther.pickable = false;
		Project.Stripped.targeted = true;
		Project.Stripped.counterpart = Project.StripOther;
		Project.Stripped.enders = new Activity[]{Project.Escape};
		Project.Stripped.pickable = false;
		Project.LickCock.sendReqs[MOUTH] = 1;
		Project.LickCock.sendReqs[FOOT] = 2;
		Project.LickCock.shares = true;
		Project.LickCock.nonBlocking = true;
		Project.LickCock.targeted = true;
		Project.LickCock.counterpart = Project.CockLicked;
		Project.LickCock.enders = new Activity[]{Project.StopActing, Project.Escape};
		Project.CockLicked.sendReqs[PENIS] = 1;
		Project.CockLicked.causesOrgasm = true;
		Project.CockLicked.targeted = true;
		Project.CockLicked.counterpart = Project.LickCock;
		Project.CockLicked.enders = new Activity[]{Project.Escape};
		Project.CockLicked.pickable = false;
		Project.LickPussy.sendReqs[MOUTH] = 1;
		Project.LickPussy.sendReqs[FOOT] = 2;
		Project.LickPussy.shares = true;
		Project.LickPussy.nonBlocking = true;
		Project.LickPussy.targeted = true;
		Project.LickPussy.counterpart = Project.PussyLicked;
		Project.LickPussy.enders = new Activity[]{Project.StopActing, Project.Escape};
		Project.PussyLicked.sendReqs[PUSSY] = 1;
		Project.PussyLicked.causesOrgasm = true;
		Project.PussyLicked.targeted = true;
		Project.PussyLicked.counterpart = Project.LickPussy;
		Project.PussyLicked.enders = new Activity[]{Project.Escape};
		Project.PussyLicked.pickable = false;
		Project.Supine.enders = new Activity[]{Project.Escape};
		Project.Supine.pickable = false;
		Project.PullUp.sendReqs[HAND] = 1;
		Project.PullUp.targeted = true;
		Project.PullUp.endsSelf = true;
		Project.PullUp.pickable = false;
		Project.StepOnCock.sendReqs[FOOT] = 1;
		Project.StepOnCock.shares = true;
		Project.StepOnCock.targeted = true;
		Project.StepOnCock.counterpart = Project.CockSteppedOn;
		Project.StepOnCock.enders = new Activity[]{Project.StopActing, Project.Escape};
		Project.CockSteppedOn.sendReqs[PENIS] = 1;
		Project.CockSteppedOn.causesOrgasm = true;
		Project.CockSteppedOn.targeted = true;
		Project.CockSteppedOn.counterpart = Project.StepOnCock;
		Project.CockSteppedOn.enders = new Activity[]{Project.Escape};
		Project.CockSteppedOn.pickable = false;
		Project.StepOnClit.sendReqs[FOOT] = 1;
		Project.StepOnClit.shares = true;
		Project.StepOnClit.targeted = true;
		Project.StepOnClit.counterpart = Project.ClitSteppedOn;
		Project.StepOnClit.enders = new Activity[]{Project.StopActing, Project.Escape};
		Project.ClitSteppedOn.sendReqs[CLIT] = 1;
		Project.ClitSteppedOn.causesOrgasm = true;
		Project.ClitSteppedOn.targeted = true;
		Project.ClitSteppedOn.counterpart = Project.StepOnClit;
		Project.ClitSteppedOn.enders = new Activity[]{Project.Escape};
		Project.ClitSteppedOn.pickable = false;
		Project.DirtyTalk.sendReqs[MOUTH] = 1;
		Project.DirtyTalk.targeted = true;
		Project.DirtyTalk.endsSelf = true;
		//sendReqs defaults to none
		//causes orgasm defaults to false
		//shares defaults to false
		//nonBlocking defaults to false
		//targeted defaults to false
		//counterpart defaults to null
		//enders defaults to 0 length array
		//endsSelf defaults to false
		//pickable defaults to true
		//hostile defaults to false
	}
	
	public Boolean orgasmPossible() {
		for (int i = 0; i < inProgress.length; i++) {
			if (inProgress[i].causesOrgasm) {
				return true;
			}
		}
		return false;
	}
	
	public void CancelActivities(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s, final int page) {
		p.removeAll();
		if (page > 0) {
			JButton Previous = new JButton("<");
			Previous.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CancelActivities(t, p, f, w, s, page-1);
				}
			});
			p.add(Previous);
		}
		for (int i = page*3; i < page*3 + 3 && i < w.lordBody.inProgress.length; i++) {
			JButton ThisOne = new JButton(w.lordBody.inProgress[i].activityName(w.lordBody.targets[i]));
			final int index = i;
			ThisOne.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					w.append(t, "\n\n" + w.getSeparator());
					w.lordBody.inProgress[index].endActivityFlavor(t, w, w.lordBody, w.lordBody.targets[index]);
					removeActivity(w.lordBody.inProgress[index], w.lordBody.targets[index]);
					if (index % 3 != 0) {
						CancelActivities(t, p, f, w, s, page);
					} else if (index != 0) {
						CancelActivities(t, p, f, w, s, page-1);
					} else if (w.lordBody.inProgress.length > 0) {
						CancelActivities(t, p, f, w, s, 0);
					} else {
						PickActivity(t, p, f, w, s);
					}
				}
			});
			p.add(ThisOne);
		}
		if ((page+1)*3 < w.lordBody.inProgress.length) {
			JButton Next = new JButton(">");
			Next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CancelActivities(t, p, f, w, s, page+1);
				}
			});
			p.add(Next);
		}
		JButton Back = new JButton("Back");
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				w.append(t, "\n\n" + w.getSeparator());
				PickActivity(t, p, f, w, s);
			}
		});
		p.add(Back);
		p.validate();
		p.repaint();
	}
	
	public void TouchMenu(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s) {
		p.removeAll();
		if (Project.TweakClit.valid(this, w.targetBody)) {
			JButton TweakClit = new JButton("Stroke Clit");
			TweakClit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					w.append(t, "\n\n" + w.getSeparator() + "\n\n");
					Project.TweakClit.startActivity(t, w, w.lordBody, w.targetBody);
					Continue(t, p, f, w, s);
				}
			});
			p.add(TweakClit);
		}
		if (Project.StrokeCock.valid(this, w.targetBody)) {
			JButton StrokeCock = new JButton("Stroke Cock");
			StrokeCock.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					w.append(t, "\n\n" + w.getSeparator() + "\n\n");
					Project.StrokeCock.startActivity(t, w, w.lordBody, w.targetBody);
					Continue(t, p, f, w, s);
				}
			});
			p.add(StrokeCock);
		}
		if (Project.LickCock.valid(this, w.targetBody)) {
			JButton LickCock = new JButton("Lick Cock");
			LickCock.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					w.append(t, "\n\n" + w.getSeparator() + "\n\n");
					Project.LickCock.startActivity(t, w, w.lordBody, w.targetBody);
					Continue(t, p, f, w, s);
				}
			});
			p.add(LickCock);
		}
		if (Project.LickPussy.valid(this, w.targetBody)) {
			JButton LickPussy = new JButton("Lick Pussy");
			LickPussy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					w.append(t, "\n\n" + w.getSeparator() + "\n\n");
					Project.LickPussy.startActivity(t, w, w.lordBody, w.targetBody);
					Continue(t, p, f, w, s);
				}
			});
			p.add(LickPussy);
		}
		if (Project.StepOnCock.valid(this, w.targetBody)) {
			JButton StepOnCock = new JButton("Give Footjob");
			StepOnCock.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					w.append(t, "\n\n" + w.getSeparator() + "\n\n");
					Project.StepOnCock.startActivity(t, w, w.lordBody, w.targetBody);
					Continue(t, p, f, w, s);
				}
			});
			p.add(StepOnCock);
		}
		if (Project.StepOnClit.valid(this, w.targetBody)) {
			JButton StepOnClit = new JButton("Give Footjob");
			StepOnClit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					w.append(t, "\n\n" + w.getSeparator() + "\n\n");
					Project.StepOnClit.startActivity(t, w, w.lordBody, w.targetBody);
					Continue(t, p, f, w, s);
				}
			});
			p.add(StepOnClit);
		}
		JButton Back = new JButton("Back");
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				w.append(t, "\n\n" + w.getSeparator());
				PickActivity(t, p, f, w, s);
			}
		});
		p.add(Back);
		p.validate();
		p.repaint();
	}
	
	public void PositionMenu(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s) {
		p.removeAll();
		if (Project.PushDown.valid(this, w.targetBody)) {
			JButton PushDown = new JButton("Push Down");
			PushDown.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					w.append(t, "\n\n" + w.getSeparator() + "\n\n");
					Project.PushDown.startActivity(t, w, w.lordBody, w.targetBody);
					w.append(t, "\n\n");
					Project.PullDown.activityTalk(t, w, w.targetBody, w.lordBody);
					Continue(t, p, f, w, s);
				}
			});
			p.add(PushDown);
		}
		if (Project.PullDown.valid(this, w.targetBody)) {
			JButton PullDown = new JButton("Pull Down");
			PullDown.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					w.append(t, "\n\n" + w.getSeparator() + "\n\n");
					Project.PullDown.startActivity(t, w, w.lordBody, w.targetBody);
					w.append(t, "\n\n");
					Project.PushDown.activityTalk(t, w, w.targetBody, w.lordBody);
					Continue(t, p, f, w, s);
				}
			});
			p.add(PullDown);
		}
		if (Project.TieUp.isInProgress(this, w.targetBody) == false) {
			JButton TieUp = new JButton("Tie Up");
			TieUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					w.append(t, "\n\n" + w.getSeparator() + "\n\n");
					Project.TieUp.startActivity(t, w, w.lordBody, w.targetBody);
					if (w.targetBody.forsakenOwner == null && w.targetBody.chosenOwner != null) {
						w.append(t, "\n\n");
						Project.BeTied.activityTalk(t, w, w.targetBody, w.lordBody);
					}
					Continue(t, p, f, w, s);
				}
			});
			p.add(TieUp);
		}
		if (Project.Supine.valid(this, null)) {
			JButton LayDown = new JButton("Lay Self Down");
			LayDown.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					w.append(t, "\n\n" + w.getSeparator() + "\n\n");
					Project.Supine.startActivity(t, w, w.lordBody, null);
					Continue(t, p, f, w, s);
				}
			});
			p.add(LayDown);
		}
		if (Project.Supine.valid(w.targetBody, null)) {
			JButton LayDown = new JButton("Lay " + w.targetBody.ownerName() + " Down");
			LayDown.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					w.append(t, "\n\n" + w.getSeparator() + "\n\n");
					Project.Supine.startActivity(t, w, w.targetBody, null);
					Continue(t, p, f, w, s);
				}
			});
			p.add(LayDown);
		}
		if (Project.PullUp.valid(this, w.targetBody)) {
			JButton PullUp = new JButton("Pull " + w.targetBody.ownerName() + " Up");
			PullUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					w.append(t, "\n\n" + w.getSeparator() + "\n\n");
					Project.PullUp.startActivity(t, w, w.lordBody, w.targetBody);
					Continue(t, p, f, w, s);
				}
			});
			p.add(PullUp);
		}
		JButton Back = new JButton("Back");
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				w.append(t, "\n\n" + w.getSeparator());
				PickActivity(t, p, f, w, s);
			}
		});
		p.add(Back);
		p.validate();
		p.repaint();
	}
	
	public void SexMenu(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s) {
		p.removeAll();
		if (Project.VaginalPenetrate.valid(w.lordBody, w.targetBody)) {
			if (Project.PenetratedVaginally.weight(w, w.targetBody, w.lordBody) >= 0 || w.targetBody.getHATELevel() >= 3) {
				JButton Missionary = new JButton("Missionary Vaginal");
				Missionary.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						w.append(t, "\n\n" + w.getSeparator() + "\n\n");
						Project.VaginalPenetrate.startActivity(t, w, w.lordBody, w.targetBody);
						w.append(t, "\n\n");
						Project.PenetratedVaginally.activityTalk(t, w, w.targetBody, w.lordBody);
						Continue(t, p, f, w, s);
					}
				});
				p.add(Missionary);
			} else {
				w.append(t, "\n\n" + w.getSeparator() + "\n\n" + w.targetBody.OwnerName() + "'s Sexual Barrier prevents vaginal penetration.");
			}
		}
		if (Project.PenetratedVaginally.valid(w.lordBody, w.targetBody)) {
			JButton Cowgirl = new JButton("Cowgirl Vaginal");
			Cowgirl.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					w.append(t, "\n\n" + w.getSeparator() + "\n\n");
					Project.PenetratedVaginally.startActivity(t, w, w.lordBody, w.targetBody);
					w.append(t, "\n\n");
					Project.VaginalPenetrate.activityTalk(t, w, w.targetBody, w.lordBody);
					Continue(t, p, f, w, s);
				}
			});
			p.add(Cowgirl);
		}
		if (Project.AnalPenetrate.valid(w.lordBody, w.targetBody)) {
			if (Project.PenetratedAnally.weight(w, w.targetBody, w.lordBody) >= 0 || w.targetBody.getHATELevel() >= 3 || w.targetBody.parts[PUSSY] > 0) {
				JButton Missionary = new JButton("Missionary Anal");
				if (Project.BeLubricated.isInProgress(w.targetBody, null) == false) {
					Missionary.setText("Unlubricated Missionary Anal");
				}
				Missionary.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						w.append(t, "\n\n" + w.getSeparator() + "\n\n");
						Project.AnalPenetrate.startActivity(t, w, w.lordBody, w.targetBody);
						w.append(t, "\n\n");
						Project.PenetratedAnally.activityTalk(t, w, w.targetBody, w.lordBody);
						Continue(t, p, f, w, s);
					}
				});
				p.add(Missionary);
			} else {
				w.append(t, "\n\n" + w.getSeparator() + "\n\n" + w.targetBody.OwnerName() + "'s Sexual Barrier prevents anal penetration.");
			}
		}
		if (Project.PenetratedAnally.valid(w.lordBody, w.targetBody)) {
			JButton Cowgirl = new JButton("Cowgirl Anal");
			if (appearanceGender(bodyType) == Forsaken.Gender.MALE) {
				Cowgirl.setText("Cowgirl (Male) Anal");
			}
			Cowgirl.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					w.append(t, "\n\n" + w.getSeparator() + "\n\n");
					Project.PenetratedAnally.startActivity(t, w, w.lordBody, w.targetBody);
					w.append(t, "\n\n");
					Project.AnalPenetrate.activityTalk(t, w, w.targetBody, w.lordBody);
					Continue(t, p, f, w, s);
				}
			});
			p.add(Cowgirl);
		}
		if (Project.Lubricate.valid(w.lordBody, w.targetBody)) {
			JButton Lubricate = new JButton("Lubricate Anus");
			Lubricate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					w.append(t, "\n\n" + w.getSeparator() + "\n\n");
					Project.Lubricate.startActivity(t, w, w.lordBody, w.targetBody);
					Continue(t, p, f, w, s);
				}
			});
			p.add(Lubricate);
		}
		JButton Back = new JButton("Back");
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				w.append(t, "\n\n" + w.getSeparator());
				PickActivity(t, p, f, w, s);
			}
		});
		p.add(Back);
		p.validate();
		p.repaint();
	}
	
	public void PickActivity(final JTextPane t, final JPanel p, final JFrame f, final WorldState w, final SaveData s) {
		w.actingBody = this;
		for (int i = 0; i < inProgress.length; i++) {
			if (inProgress[i].endsSelf) {
				removeActivity(inProgress[i], targets[i]);
			}
		}
		w.append(t, "\n\n");
		if (w.sceneDuration == 0) {
			w.append(t, w.getSeparator() + "\n\n");
			InitializeActivities();
		}
		if (this == w.lordBody) {
			w.targetBody = w.sceneParticipants[1];
			final String[] shownNames = new String[]{w.targetBody.portraitName(), null, null, null, null};
			p.removeAll();
			if (w.sceneDuration == 0) {
				w.sceneDuration++;
				//intro description
				if (w.sceneLocation == Activity.Location.CHAMBER) {
					if (w.targetBody.getObedience() > 66) {
						w.append(t, w.targetBody.OwnerName() + " is waiting eagerly in " + w.targetBody.hisHer() + " room when " + w.lordBody.ownerName() + " arrives, ");
						if (w.targetBody.getDeviancy() > 66) {
							w.append(t, "already naked and unable to stop " + w.targetBody.himHer() + "self from masturbating as " + w.targetBody.heShe() + " anticipates what's to come.  When " + w.targetBody.heShe() + " sees " + w.lordBody.ownerName() + ", " + w.targetBody.heShe() + " scrambles to " + w.targetBody.hisHer() + " feet, panting with unrestrained desire.");
							w.targetBody.currentEXPO = Project.million;
						} else if (w.targetBody.getDeviancy() > 33) {
							w.append(t, "wearing nothing but a thin robe which " + w.targetBody.heShe() + " quickly drops to the floor as " + w.targetBody.heShe() + " welcomes " + w.lordBody.himHer() + ".  An eager smile is on " + w.targetBody.ownerName() + "'s face, and " + w.targetBody.hisHer() + " cheeks are flushed with arousal.");
							w.targetBody.currentEXPO = Project.million;
						} else {
							w.append(t, "shifting nervously from foot to foot.  " + w.targetBody.HeShe() + " knows roughly what will be expected of " + w.targetBody.himHer() + ", but " + w.targetBody.heShe() + "'s unsure whether " + w.targetBody.heShe() + "'ll be able to perform.");
						}
						w.targetBody.currentPLEA = w.targetBody.getObedience()*w.targetBody.getDeviancy()/10;
					} else if (w.targetBody.getObedience() > 33) {
						if (w.targetBody.getDeviancy() > 66) {
							w.append(t, w.targetBody.capitalizedOwnerName() + " is masturbating when " + w.lordBody.ownerName() + " arrives, and " + w.targetBody.heShe() + " looks almost annoyed at being interrupted.  But as " + w.targetBody.heShe() + " remembers the possibilities offered by a partner, " + w.targetBody.heShe() + " cheers up and sheds " + w.targetBody.hisHer() + " clothes, offering " + w.targetBody.himHer() + "self to " + w.lordBody.ownerName() + ".");
							w.targetBody.currentPLEA = w.targetBody.getDeviancy()*w.targetBody.getDeviancy()/20;
							w.targetBody.currentEXPO = Project.million;
						} else if (w.targetBody.getDeviancy() > 33) {
							w.append(t, w.targetBody.capitalizedOwnerName() + " welcomes " + w.lordBody.ownerName() + " into " + w.targetBody.hisHer() + " room, then stands obediently at attention.  Only the slight flush in " + w.targetBody.hisHer() + " cheeks betrays the fact that " + w.targetBody.heShe() + " might be hoping for this encounter to turn intimate.");
							w.targetBody.currentPLEA = w.targetBody.getDeviancy()*w.targetBody.getDeviancy()/100;
						} else {
							w.append(t, w.targetBody.OwnerName() + " reluctantly allows " + w.lordBody.ownerName() + " into " + w.targetBody.hisHer() + " room.  " + w.targetBody.HeShe() + "'s clearly resigned to what " + w.targetBody.heShe() + "'s going to be forced to do.");
							w.targetBody.currentFEAR = (100-w.targetBody.getConfidence());
						}
						w.targetBody.currentHATE = (100-w.targetBody.getDeviancy())*(100-w.targetBody.getObedience())/100;
					} else {
						if (w.targetBody.getDeviancy() > 66) {
							w.append(t, w.targetBody.OwnerName() + " is trembling with arousal as " + w.lordBody.ownerName() + " enters " + w.targetBody.hisHer() + " room, but " + w.targetBody.heShe() + " still tries to glare at " + w.lordBody.himHer() + ", denying how turned on " + w.targetBody.heShe() + " is at being at the mercy of " + w.targetBody.hisHer() + " hated enemy.");
							w.targetBody.currentPLEA = w.targetBody.getDeviancy()*w.targetBody.getDeviancy()/40;
						} else if (w.targetBody.getDeviancy() > 33) {
							w.append(t, "When " + w.lordBody.ownerName() + " enters " + w.targetBody.hisHer() + " room, " + w.targetBody.ownerName() + " dares " + w.lordBody.himHer() + " to do " + w.lordBody.hisHer() + " worst.  The growing perverted side of " + w.targetBody.ownerName() + " is secretly looking forward to it.");
							w.targetBody.currentPLEA = w.targetBody.getDeviancy()*w.targetBody.getObedience()/40;
						} else {
							w.append(t, w.targetBody.OwnerName() + " waits in " + w.targetBody.hisHer() + " room with " + w.targetBody.hisHer() + " arms crossed.  " + w.targetBody.HeShe() + " doesn't even acknowledge " + w.lordBody.ownerName() + " entering the room.");
						}
						w.targetBody.currentFEAR = (100-w.targetBody.getConfidence());
						w.targetBody.currentHATE = (100-w.targetBody.getObedience())*(100-w.targetBody.getObedience())/50;
					}
				} else if (w.sceneLocation == Activity.Location.STAGE) {
					if (w.targetBody.getObedience() > 66) {
						if (w.targetBody.getDeviancy() > 66) {
							w.append(t, w.targetBody.OwnerName() + " is already trembling with arousal as " + w.targetBody.heShe() + " joins " + w.lordBody.ownerName() + " on stage in front of a cheering crowd of Thralls, ");
							if (w.targetBody.parts[PENIS] > 0) {
								w.append(t, "struggling with the urge to undress and start stroking " + w.targetBody.hisHer() + " straining penis right there.");
							} else {
								w.append(t, w.targetBody.hisHer() + " wetness dripping down " + w.targetBody.hisHer() + " thighs.");
							}
							w.append(t, "  The prospect of being used right there in public is incredibly arousing to " + w.targetBody.himHer() + ".");
						} else if (w.targetBody.getDeviancy() > 33) {
							w.append(t, w.targetBody.OwnerName() + " waves happily to the surrounding Thralls as " + w.targetBody.heShe() + " joins " + w.lordBody.ownerName() + " on the stage.  " + w.targetBody.HeShe() + "'s proud to have the chance to show everyone how deeply " + w.targetBody.heShe() + " belongs to " + w.lordBody.ownerName() + ".");
						} else {
							w.append(t, w.targetBody.OwnerName() + " looks nervous as " + w.targetBody.heShe() + " makes " + w.targetBody.hisHer() + " way through the Thralls in order to take the stage with " + w.lordBody.ownerName() + ".  " + w.targetBody.HeShe() + "'s willing to be humiliated for " + w.lordBody.himHer() + ", but " + w.targetBody.heShe() + "'s ashamed to show " + w.targetBody.hisHer() + " unfamiliarity with the sexual activities " + w.targetBody.heShe() + "'ll surely be asked to perform.");
						}
						w.targetBody.currentPLEA += w.targetBody.getObedience()*w.targetBody.getDeviancy()*(100+w.targetBody.getDignity())/1000;
						w.targetBody.currentSHAM += w.targetBody.getDignity()*(100-w.targetBody.getDeviancy())/50;
					} else if (w.targetBody.getObedience() > 33) {
						if (w.targetBody.getHostility() > 66) {
							w.append(t, w.targetBody.OwnerName() + " stands on the stage, glaring angrily at the surrounding Thralls who jeer and takes pictures of " + w.targetBody.himHer() + ", but " + w.targetBody.heShe() + " still bows " + w.targetBody.hisHer() + " head respectfully as " + w.lordBody.ownerName() + " joins " + w.targetBody.himHer() + ".");
						} else if (w.targetBody.getHostility() > 33) {
							w.append(t, w.targetBody.OwnerName() + " obediently joins " + w.lordBody.ownerName() + " on stage, sparing only the occasional annoyed glance at the crowd of Thralls who shout out suggestions for ways " + w.targetBody.heShe() + " should be humiliated.  " + w.targetBody.HeShe() + " waits to see what will be done with " + w.targetBody.himHer() + ".");
						} else {
							w.append(t, w.targetBody.OwnerName() + " seems a bit intimidated by the crowd of Thralls, but " + w.targetBody.heShe() + " still willingly joins " + w.lordBody.ownerName() + " on the stage and tells everyone that " + w.targetBody.heShe() + " hopes they enjoy the show.");
						}
						w.targetBody.currentHATE += 50 + w.targetBody.getHostility() - w.targetBody.getObedience();
						if (w.targetBody.currentHATE < 0) {
							w.targetBody.currentHATE = 0;
						}
						w.targetBody.currentFEAR = (100-w.targetBody.getConfidence());
						w.targetBody.currentPLEA += w.targetBody.getDeviancy()*w.targetBody.getDeviancy()/100;
					} else {
						if (w.targetBody.getConfidence() > 66) {
							w.append(t, w.targetBody.OwnerName() + " has to be dragged onto the stage by force, kicking and shouting, and when " + w.targetBody.heShe() + "'s finally thrown in front of " + w.lordBody.ownerName() + ", " + w.targetBody.heShe() + " glares up in angry defiance.  ");
						} else if (w.targetBody.getConfidence() > 33) {
							w.append(t, "On a stage with " + w.lordBody.ownerName() + " in front of a crowd that roars with eagerness to see " + w.targetBody.himHer() + " raped, " + w.targetBody.ownerName() + " tries to remain calm, but " + w.targetBody.heShe() + " feels " + w.targetBody.hisHer() + " heart pounding in " + w.targetBody.hisHer() + " chest.  ");
						} else {
							w.append(t, w.targetBody.OwnerName() + " is trembling with fear as " + w.targetBody.heShe() + " allows a pair of Thralls to escort " + w.targetBody.himHer() + " onto the stage with " + w.lordBody.ownerName() + ", flinching at every crude comment and threat shouted from the crowd.  ");
						}
						w.targetBody.currentFEAR = (100 - w.targetBody.getConfidence())*(100 - w.targetBody.getDignity())/50;
						w.targetBody.currentHATE = (100 - w.targetBody.getObedience())*(100 - w.targetBody.getDignity())/50;
						w.targetBody.currentPLEA = (w.targetBody.getDeviancy())*(100 - w.targetBody.getDignity())/50;
						if (w.targetBody.hasBeenBroadcasted() == false) {
							w.targetBody.currentFEAR = w.targetBody.currentFEAR*3;
							w.append(t, w.targetBody.HeShe() + " sees filming phones in the crowd, and " + w.targetBody.heShe() + " knows that what happens here could change how people see " + w.targetBody.himHer() + " forever.");
						} else {
							if (w.targetBody.getDeviancy() > 66) {
								w.append(t, "And yet, as always, " + w.targetBody.hisHer() + " body aches to be savagely fucked right there in front of everyone.");
							} else if (w.targetBody.getDeviancy() > 33) {
								w.append(t, w.targetBody.HeShe() + " tries not to admit to " + w.targetBody.himHer() + "self just how much this is turning " + w.targetBody.himHer() + " on.");
							} else {
								w.append(t, w.targetBody.HeShe() + " hates everything about this situation.");
							}
						}
					}
					for (int i = 0; i < w.sceneParticipants.length && w.sceneLocation == Activity.Location.STAGE; i++) {
						if (w.sceneParticipants[i].forsakenOwner != null && w.sceneParticipants[i].getEXPOLevel() >= 3) {
							int audience = 300000 + (int)(Math.random()*300000);
							w.sceneParticipants[i].forsakenOwner.timesExposed  += audience;
							w.sceneParticipants[i].forsakenOwner.timesExposedSelf += audience;
						}
					}
				} else if (w.sceneLocation == Activity.Location.ALLEY) {
					if (w.targetBody.forsakenOwner == null && w.targetBody.chosenOwner != null) {
						if (w.targetBody.chosenOwner.rememberedBodies.length == 1) {
							if (w.targetBody.getInnocence() > 66) {
								w.append(t, "When you arrive at an alleyway not far from " + w.targetBody.ownerName() + "'s home, " + w.targetBody.heShe() + " senses the Demonic presence and flies over to you, prepared for battle.  ");
								if (w.targetBody.isDrained()) {
									Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
									w.append(t, "However, as soon as " + w.targetBody.heShe() + " recognizes that this is the Demon Lord " + w.lordBody.himHer() + "self, " + w.targetBody.heShe() + " flinches and loses " + w.targetBody.hisHer() + " will to fight.\n\n");
									w.targetBody.say(t, "\"");
									if (w.targetBody.getMorality() > 66) {
										w.targetBody.say(t, "I-I haven't fallen far enough to let you drain me while I'm on duty!");
									} else if (w.targetBody.getMorality() > 33) {
										w.targetBody.say(t, "If you want more energy... just wait until tonight, okay?");
									} else {
										w.targetBody.say(t, "Are you just here to make fun of me...?");
									}
									w.targetBody.say(t, "\"");
								} else {
									w.append(t, "It takes " + w.targetBody.himHer() + " a moment to realize that this is the Demon Lord " + w.lordBody.himHer() + "self, but once " + w.targetBody.heShe() + " does, " + w.targetBody.heShe() + " gets even more excited.\n\n");
									w.targetBody.say(t, "\"");
									if (w.targetBody.getConfidence() > 66) {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
										w.targetBody.say(t, "Come to beg for your life, Demon Lord!?  Hah!  I might blow you up right here!");
									} else if (w.targetBody.getConfidence() > 33) {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.ANGER);
										w.targetBody.say(t, "The Demon Lord is right here... but I guess blowing " + w.lordBody.himHer() + " up wouldn't actually do anything... so it can't hurt to see what " + w.lordBody.heShe() + " wants...");
									} else {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.ANGER);
										w.targetBody.say(t, "I-It's really the Demon Lord!?  If you do anything weird, I'll call the others here right away!");
									}
									w.targetBody.say(t, "\"");
								}
							} else if (w.targetBody.getInnocence() > 33) {
								w.append(t, "While " + w.targetBody.ownerName() + " is out on patrol, you deliberately leave a trail of Demonic influence that " + w.targetBody.heShe() + " can follow to find you.  When " + w.targetBody.heShe() + " senses that the person before " + w.targetBody.himHer() + " is actually a body being directly controlled by the Demon Lord, ");
								if (w.targetBody.isDrained()) {
									w.append(t, w.targetBody.heShe() + " gasps in surprise and hesitates, uncertain of how " + w.targetBody.heShe() + " should react.\n\n");
									w.targetBody.say(t, "\"");
									if (w.targetBody.getMorality() > 66) {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
										w.targetBody.say(t, "Have you come to gloat, Demon Lord?  Ugh, I guess I deserve it...");
									} else if (w.targetBody.getMorality() > 33) {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
										w.targetBody.say(t, "I knew I never should have given you my energy.  Now you're just harassing me...");
									} else {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.ANGER);
										w.targetBody.say(t, "Listen, Demon Lord.  If you want me to give you my energy in broad daylight... you'll have to make it worth my while, alright?");
									}
									w.targetBody.say(t, "\"");
								} else {
									w.append(t, w.targetBody.heShe() + " narrows " + w.targetBody.hisHer() + " eyes and adjusts " + w.targetBody.hisHer() + " stance, ready for combat.\n\n");
									w.targetBody.say(t, "\"");
									if (w.targetBody.getConfidence() > 66) {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
										w.targetBody.say(t, "What do you want, Demon Lord!?  Do you think I'll hold back just because you came here in a body that looks human?");
									} else if (w.targetBody.getConfidence() > 33) {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
										w.targetBody.say(t, "You came here alone?  Why?  If you want to talk, you'd better make it quick.");
									} else {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.ANGER);
										w.targetBody.say(t, "A-Are you trying to scare me, Demon Lord!?  I'm not going to quit my patrol just because you sent a decoy body to distract me!");
									}
									w.targetBody.say(t, "\"");
								}
							} else {
								w.append(t, "You leave a note for " + w.targetBody.ownerName() + ", inviting " + w.targetBody.himHer() + " to a private meeting in a secluded alleyway.  " + w.targetBody.HeShe() + " arrives promptly on time, ");
								if (w.targetBody.isDrained()) {
									w.append(t, "looking harried and nervous.\n\n");
									w.targetBody.say(t, "\"");
									if (w.targetBody.getDignity() > 66) {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.ANGER);
										w.targetBody.say(t, "Wh-What is the meaning of this?  If you insist on harassing me, I'll break off our... our energy-related arrangement.  I will!");
									} else if (w.targetBody.getDignity() > 33) {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.ANGER);
										w.targetBody.say(t, "Do you realize what will happen if I'm seen talking with the Demon Lord in public?  Tell me what you want, quickly, quickly!");
									} else {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
										w.targetBody.say(t, "What do you want from me, Demon Lord?  I'm already compromising my principles by giving you my energy.  Whatever it is, just be quick about it.");
									}
									w.targetBody.say(t, "\"");
								} else {
									w.append(t, "studying your body with an expression of detached curiosity.\n\n");
									w.targetBody.say(t, "\"");
									Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FOCUS);
									if (w.targetBody.getMorality() > 66) {
										w.targetBody.say(t, "I almost didn't come... but it's against my principles to ignore an offer of parley, even from a Demon.  If you use that body to attack me, however, I have no qualms about destroying it.");
									} else if (w.targetBody.getMorality() > 33) {
										w.targetBody.say(t, "A chance to hear the Demonic perspective...  Well, I expect that we'll still end up fighting to the death in the end, but maybe we can learn something in the meantime.");
									} else {
										w.targetBody.say(t, "Have you finally decided that you'd rather have me on your side?  I doubt that you can offer terms better than the ones I'm getting from the government, but... I'm listening.");
									}
									w.targetBody.say(t, "\"");
								}
							}
						} else if (w.targetBody.chosenOwner.negotiations < 3 || w.targetBody.chosenOwner.ANGST < Project.billion) {
							if (w.targetBody.getInnocence() > 66) {
								w.append(t, "You use the same trick of using your Demonic presence to lure " + w.targetBody.ownerName() + " from her home again.  " + w.targetBody.HeShe() + " frowns as soon as " + w.targetBody.heShe() + " sees you.\n\n");
								if (w.targetBody.chosenOwner.negotiations > 0 || w.targetBody.chosenOwner.drained) {
									w.targetBody.say(t, "\"I wish you'd just leave us alone, Demon Lord...\"");
									Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
								} else {
									w.targetBody.say(t, "\"Leave me alone, Demon Lord!\"");
									Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FEAR);
								}
							} else if (w.targetBody.getInnocence() > 33) {
								w.append(t, "You send out another body to meet " + w.targetBody.ownerName() + " in the middle of " + w.targetBody.hisHer() + " patrol.  " + w.targetBody.HeShe() + " lands just inside the alleyway where you're waiting.\n\n");
								if (w.targetBody.chosenOwner.negotiations > 0 || w.targetBody.chosenOwner.drained) {
									w.targetBody.say(t, "\"I'm here...  What do you want, Demon Lord?\"");
									Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
								} else {
									w.targetBody.say(t, "\"The only reason I haven't blown you up already is because you aren't using a combat-type body.  So, what do you want?\"");
									Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
								}
							} else {
								w.append(t, "You deliver another note to " + w.targetBody.ownerName() + ", offering a meeting, and " + w.targetBody.heShe() + " arrives at the designated time and place.\n\n");
								if (w.targetBody.chosenOwner.negotiations > 0 || w.targetBody.chosenOwner.drained) {
									w.targetBody.say(t, "\"As you requested, I came.  What do you require of me?");
									Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
								} else {
									w.targetBody.say(t, "\"I only came because I was able to ensure that you haven't set a trap for me here.  I hope you aren't expecting me to let my guard down, Demon Lord.\"");
									Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.ANGER);
								}
							}
						} else {
							if (w.targetBody.getDignity() > 66) {
								w.append(t, w.targetBody.ownerName() + " responds to your summons quickly, but with " + w.targetBody.hisHer() + " face blushing bright red as " + w.targetBody.heShe() + " constantly looks over " + w.targetBody.hisHer() + " shoulder to make sure " + w.targetBody.heShe() + " wasn't seen entering the alleyway.\n\n");
								w.targetBody.say(t, "\"Wanting to do this in public even though I offered to let you into my room...  Ugh, I should have known the Demon Lord would be a pervert...\"");
								Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.LEWD);
							} else if (w.targetBody.getDignity() > 33) {
								w.append(t, w.targetBody.ownerName() + " arrives to the meeting place early, blushing slightly and shifting uncomfortably from foot to foot.  " + w.targetBody.HeShe() + " has trouble meeting your gaze.\n\n");
								w.targetBody.say(t, "\"I know why you called me here, so let's...  Let's just hurry up and do it, okay?\"");
								Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.SHAME);
							} else {
								w.append(t, w.targetBody.ownerName() + " seems to be in a good mood as " + w.targetBody.heShe() + " arrives at the alleyway.\n\n");
								w.targetBody.say(t, "\"I think I'm starting to enjoy this.  But if you want me to go along with it, I'll still need your promise not to attack today.\"");
								Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
							}
						}
					}
					w.targetBody.currentFEAR = w.targetBody.getObedience()*5;
					w.targetBody.currentDISG = (100-w.targetBody.getDeviancy())*3;
					w.targetBody.currentHATE = 2000 + w.targetBody.getHostility()*20;
					int counter = w.targetBody.chosenOwner.negotiations;
					while (counter > 0) {
						w.targetBody.currentFEAR = w.targetBody.currentFEAR*2/3;
						w.targetBody.currentDISG = w.targetBody.currentDISG/2;
						w.targetBody.currentSHAM += w.targetBody.getDignity();
						w.targetBody.currentHATE = w.targetBody.currentHATE*100/(200 + w.targetBody.getObedience());
						w.targetBody.currentPLEA += w.targetBody.getDeviancy()*2/3;
						counter--;
					}
				} else if (w.sceneLocation == Activity.Location.ROOM) {
					w.sceneParticipants[1].transformed = false;
					if (w.targetBody.getInnocence() > 66) {
						w.append(t, w.targetBody.OwnerName() + " eagerly ushers you into " + w.targetBody.hisHer() + " room, offering you drinks, snacks, and whatever else " + w.targetBody.heShe() + " thinks might make you happy and comfortable.  Then " + w.targetBody.heShe() + " sits on the edge of " + w.targetBody.hisHer() + " bed, blushing and fidgeting nervously.  It's plain to see that " + w.targetBody.heShe() + "'s been anticipating this.\n\n");
						w.targetBody.say(t, "\"");
						if (w.day % 3 == 0) {
							w.targetBody.say(t, "Do you want some candy?  Um, do Demons even like candy...?");
							Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.JOY);
						} else if (w.day % 3 == 1) {
							w.targetBody.say(t, "Hey, have you ever thought about becoming one of the good guys?  I think that would make everybody happy.");
							Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
						} else {
							w.targetBody.say(t, "It's weird.  Even though we're enemies, sometimes I... think I kinda like you...");
							Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
						}
						w.targetBody.say(t, "\"");
					} else if (w.targetBody.getInnocence() > 33) {
						w.append(t, w.targetBody.OwnerName() + " welcomes you at " + w.targetBody.hisHer() + " door, already wearing a set of skimpy lingerie that presents " + w.targetBody.hisHer() + " body quite nicely.  " + w.targetBody.HeShe() + " sways " + w.targetBody.hisHer() + " hips as " + w.targetBody.heShe() + " turns to lead you inside, obviously attempting to entice you.\n\n");
						w.targetBody.say(t, "\"");
						if (w.day % 3 == 0) {
							w.targetBody.say(t, "Do you like what you see?  You know the price by now.");
						} else if (w.day % 3 == 1) {
							w.targetBody.say(t, "If you want to take my virginity...  Well, no, it would have to be more than a one day truce...");
						} else {
							w.targetBody.say(t, "Even if you aren't willing to agree on a truce today...  No, never mind!  I have to stay firm...");
						}
						w.targetBody.say(t, "\"");
						Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
					} else {
						w.append(t, w.targetBody.OwnerName() + " hesitantly lets you into " + w.targetBody.hisHer() + " room, awkwardly trying to make conversation.  From time to time, " + w.targetBody.heShe() + " loses " + w.targetBody.hisHer() + " train of thought, staring into your eyes for several moments before coming back to the present.\n\n");
						w.targetBody.say(t, "\"");
						if (w.day % 3 == 0) {
							w.targetBody.say(t, "Have I ever told you that you're... not at all what I was expecting from a Demon Lord...?");
						} else if (w.day % 3 == 1) {
							w.targetBody.say(t, "My apologies.  I'm having trouble sorting out my own feelings...");
						} else {
							w.targetBody.say(t, "To think that I'd be so willing to let the Demon Lord into my personal life...");
						}
						w.targetBody.say(t, "\"");
						Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.SHAME);
					}
					w.targetBody.currentFEAR = w.targetBody.getObedience()*8;
					w.targetBody.currentDISG = (100-w.targetBody.getDeviancy())*2;
					w.targetBody.currentHATE = 1000;
					w.targetBody.currentPLEA = w.targetBody.getDeviancy()*2/3;
					int counter = w.targetBody.chosenOwner.negotiations;
					while (counter > 0) {
						w.targetBody.currentFEAR = w.targetBody.currentFEAR*2/3;
						w.targetBody.currentDISG = w.targetBody.currentDISG/2;
						w.targetBody.currentSHAM += w.targetBody.getDignity()/2;
						w.targetBody.currentHATE = w.targetBody.currentHATE*100/(200 + w.targetBody.getObedience());
						w.targetBody.currentPLEA += w.targetBody.getDeviancy()/2;
						counter--;
					}
				}
				w.append(t, "\n\n");
			} else {
				Boolean descriptionStarted = false;
				Activity[] mentioned = new Activity[0];
				Body[] targeted = new Body[0];
				for (int i = 0; i < w.sceneParticipants.length; i++) {
					for (int j = 0; j < w.sceneParticipants[i].inProgress.length; j++) {
						if (w.sceneParticipants[i].inProgress[j].endsSelf == false) {
							if (descriptionStarted == false) {
								descriptionStarted = true;
								w.append(t, "In Progress:");
							}
							Boolean found = false;
							for (int k = 0; k < mentioned.length; k++) {
								if (mentioned[k].counterpart == w.sceneParticipants[i].inProgress[j] && targeted[k] == w.sceneParticipants[i]) {
									found = true;
								}
							}
							if (found == false) {
								w.sceneParticipants[i].inProgress[j].shortDescription(t, w, w.sceneParticipants[i], w.sceneParticipants[i].targets[j]);
								Activity[] newMentioned = new Activity[mentioned.length+1];
								Body[] newTargeted = new Body[targeted.length+1];
								for (int k = 0; k < mentioned.length; k++) {
									newMentioned[k] = mentioned[k];
									newTargeted[k] = targeted[k];
								}
								newMentioned[mentioned.length] = w.sceneParticipants[i].inProgress[j];
								newTargeted[targeted.length] = w.sceneParticipants[i].targets[j];
								mentioned = newMentioned;
								targeted = newTargeted;
							}
						}
					}
				}
				if (descriptionStarted) {
					w.append(t, "\n\n");
				}
			}
			w.append(t, "How will " + ownerName() + " act?");
			JButton Touch = new JButton("Pleasure");
			Touch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TouchMenu(t, p, f, w, s);
				}
			});
			p.add(Touch);
			if (Project.PushDown.isInProgress(w.lordBody, w.targetBody) == false && Project.PullDown.isInProgress(w.lordBody, w.targetBody) == false ) {
				JButton Position = new JButton("Position");
				Position.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						PositionMenu(t, p, f, w, s);
					}
				});
				p.add(Position);
			} else if (Project.PushDown.isInProgress(w.lordBody, w.targetBody)) {
				JButton Sex = new JButton("Sex");
				Sex.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SexMenu(t, p, f, w, s);
					}
				});
				p.add(Sex);
			}
			if (Project.StripOther.valid(this, w.targetBody)) {
				JButton StripOther = new JButton("Strip");
				StripOther.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						w.append(t, "\n\n" + w.getSeparator() + "\n\n");
						Project.StripOther.startActivity(t, w, w.lordBody, w.targetBody);
						Continue(t, p, f, w, s);
					}
				});
				p.add(StripOther);
			}
			if (w.lordBody.inProgress.length > 0) {
				JButton Cancel = new JButton("Stop Action");
				Cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						w.append(t, "\n\n" + w.getSeparator() + "\n\nWhich action will you cancel?");
						CancelActivities(t, p, f, w, s, 0);
					}
				});
				p.add(Cancel);
			}
			JButton Wait = new JButton("Wait");
			Wait.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Boolean summaryNeeded = false;
					if (summaryNeeded) {
						Continue(t, p, f, w, s);
					} else {
						advanceAction(t, p, f, w, s);
					}
				}
			});
			if (inProgress.length > 0) {
				Wait.setText("Continue");
			}
			p.add(Wait);
			if (w.targetBody.forsakenOwner == null && w.targetBody.chosenOwner != null && w.targetBody.chosenOwner.truce == false && w.getCast()[2] != null) {
				if (w.day > 1 && w.day < 50 - w.eventOffset*3 && w.targetBody.chosenOwner.cVirg == false && w.targetBody.chosenOwner.aVirg == false && w.targetBody.chosenOwner.vVirg && w.targetBody.chosenOwner.modest && w.targetBody.chosenOwner.debased == false && w.targetBody.chosenOwner.timesSlaughtered() == 0 && w.targetBody.chosenOwner.timesStripped() == 0 && w.getCast()[0].ANGST >= Project.billion && w.getCast()[1].ANGST >= Project.billion && w.getCast()[2].ANGST >= Project.billion) {
					JButton Negotiate = new JButton("Negotiate");
					Negotiate.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							p.removeAll();
							w.append(t, "\n\n" + w.getSeparator() + "\n\n");
							if (w.targetBody.chosenOwner.negotiations > 0) {
								w.append(t, "Once again, you offer to spare the city for a day in exchange for " + w.targetBody.ownerName() + "'s body.\n\n");
								w.targetBody.say(t, "\"");
								if (w.targetBody.getMorality() > 66) {
									if (w.targetBody.chosenOwner.negotiations % 3 == 1) {
										if (w.targetBody.getInnocence() > 66) {
											Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
											w.targetBody.say(t, "Um, I guess I basically have to go along with this now...");
										} else if (w.targetBody.getInnocence() > 33) {
											Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.NEUTRAL);
											w.targetBody.say(t, "Alright.  In order to protect the city, I'm willing to do this again.");
										} else {
											Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FOCUS);
											w.targetBody.say(t, "If this is the course of action that minimizes the harm you do, I am still obligated to continue.");
										}
									} else if (w.targetBody.chosenOwner.negotiations % 3 == 2) {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
										if (w.targetBody.getConfidence() > 66) {
											w.targetBody.say(t, "You've proven surprisingly trustworthy for a Demon Lord.  Very well.");
										} else if (w.targetBody.getConfidence() > 33) {
											w.targetBody.say(t, "I was expecting this to be harder.  Alright, let's do it.");
										} else {
											w.targetBody.say(t, "I'm glad that you haven't gotten bored of me yet... U-Um, I mean, I accept, of course!");
										}
									} else {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.SHAME);
										if (w.targetBody.getDignity() > 66) {
											w.targetBody.say(t, "I-It's not like I'm enjoying this!  That would make me some sort of... pervert...  Right, I'm doing this for everyone else's sake, so let's hurry up and do it!");
										} else if (w.targetBody.getDignity() > 33) {
											w.targetBody.say(t, "You don't have to go easy on me, you know.  I'm prepare to endure anything for everyone's sake, so... you can punish me harder if you want.  Ugh, that doesn't sound right...");
										} else {
											w.targetBody.say(t, "I feel guilty for enjoying this.  But refusing your offer would just make things harder on everyone...");
										}
									}
								} else if (w.targetBody.getMorality() > 33) {
									if (w.targetBody.chosenOwner.negotiations % 3 == 1) {
										if (w.targetBody.getInnocence() > 66) {
											Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
											w.targetBody.say(t, "You wanna do it again?  Well, last time wasn't too bad... so okay!");
										} else if (w.targetBody.getInnocence() > 33) {
											Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
											w.targetBody.say(t, "Is it worth that much to you, using my body?  Fine, go ahead, I can take it.");
										} else {
											Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FOCUS);
											w.targetBody.say(t, "I remain suspicious of your motivations... but very well.  I accept.");
										}
									} else if (w.targetBody.chosenOwner.negotiations % 3 == 2) {
										if (w.targetBody.getConfidence() > 66) {
											Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
											w.targetBody.say(t, "Even the Demon Lord can't resist my charms.  Alright, I accept.");
										} else if (w.targetBody.getConfidence() > 33) {
											Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
											w.targetBody.say(t, "Am I really so irresistable?  I suppose I am.");
										} else {
											Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
											w.targetBody.say(t, "Why are you interested in someone like me?  N-Not that I'm refusing, but I... I don't understand it.");
										}
									} else {
										if (w.targetBody.getDignity() > 66) {
											Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
											w.targetBody.say(t, "Yes!  Ah, I mean, um, if it's for the sake of the city, I can't refuse, can I?");
										} else if (w.targetBody.getDignity() > 33) {
											Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FOCUS);
											w.targetBody.say(t, "What would the others say if they knew how I...?  No, that doesn't matter.  Let's just do this.");
										} else {
											Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
											w.targetBody.say(t, "I was hoping you'd ask.  It seems like this works out to make everyone happy, doesn't it?");
										}
									}
								} else {
									if (w.targetBody.chosenOwner.negotiations % 3 == 1) {
										if (w.targetBody.getInnocence() > 66) {
											Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
											w.targetBody.say(t, "Well, I don't feel like fighting you right now, so okay!");
										} else if (w.targetBody.getInnocence() > 33) {
											Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FOCUS);
											w.targetBody.say(t, "I still don't completely trust you, Demon Lord.  But that doesn't mean we can't have some fun.");
										} else {
											Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
											w.targetBody.say(t, "To think that a Demon Lord's lust would be so easy to manipulate...  Let's continue where we left off, shall we?");
										}
									} else if (w.targetBody.chosenOwner.negotiations % 3 == 2) {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
										if (w.targetBody.getConfidence() > 66) {
											w.targetBody.say(t, "I should add some extra conditions.  Maybe have you send me some Thralls as personal servants?  For now, we can just do the same old agreement.");
										} else if (w.targetBody.getConfidence() > 33) {
											w.targetBody.say(t, "I'm your favorite, aren't I?  Well, alright, I'll do it for you.");
										} else {
											w.targetBody.say(t, "Heh, I might not be strong... but now I've got the Demon Lord wrapped around my finger.  I'm better than all those heroes...");
										}
									} else {
										if (w.targetBody.getDignity() > 66) {
											Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.LEWD);
											w.targetBody.say(t, "Don't take me for granted.  I still haven't forgiven you for what your minions have done to me.  But... you're in luck, because I feel like agreeing this time.");
										} else if (w.targetBody.getDignity() > 33) {
											Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.JOY);
											w.targetBody.say(t, "You should have offered to do this in the first place, before we ever fought.  I would have accepted... maybe...");
										} else {
											Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
											w.targetBody.say(t, "Fighting hasn't been as much fun lately.  But sex is more fun than ever!");
										}
									}
								}
								w.targetBody.say(t, "\"\n\n");
								w.append(t, "Agreeing will prevent you from battling and from using the Assist Raid training action today.  However, it will increase the Resolve damage from Appeal by " + (w.achievementHeld(8)[0]+1)*(w.achievementHeld(7)[0]+1) + "% (to a total of " + (w.achievementHeld(8)[0]+w.targetBody.chosenOwner.negotiations+1)*(w.achievementHeld(7)[0]+1) + "%).");
							} else {
								w.append(t, "You make a proposal to " + w.targetBody.ownerName() + ".  In exchange for letting you do whatever you want with " + w.targetBody.hisHer() + " body for awhile, you'll promise not to attack the city today.\n\n");
								w.targetBody.say(t, "\"");
								if (w.targetBody.getMorality() > 66) {
									if (w.targetBody.getConfidence() > 66) {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
										w.targetBody.say(t, "Hmph.  This is demeaning, and I still suspect a trap.  But... I suppose I'm obligated to see whether you're being sincere.");
									} else if (w.targetBody.getConfidence() > 33) {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FEAR);
										if (w.targetBody.getGender() == Forsaken.Gender.MALE) {
											w.targetBody.say(t, "I can't do any... butt stuff, because that would be... wrong.  Just wrong.  I'll do anything else you want, though.  Is that alright?");
										} else {
											w.targetBody.say(t, "I can't give you my virginity, because it doesn't belong to me.  It belongs to my future husband.  But aside from that... you can do what you want with me.");
										}
									} else {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
										w.targetBody.say(t, "I guess... this is the only way someone like me can save everyone...");
									}
								} else if (w.targetBody.getMorality() > 33) {
									if (w.targetBody.getConfidence() > 66) {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
										w.targetBody.say(t, "Ergh...  I've tried everything else.  Maybe this is the only way to win...");
									} else if (w.targetBody.getConfidence() > 33) {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FEAR);
										w.targetBody.say(t, "I suppose my powers will keep you from doing anything too extreme to me...  Alright.");
									} else {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.SHAME);
										if (w.targetBody.getGender() == Forsaken.Gender.MALE) {
											w.targetBody.say(t, "I... I won't let you use my b-butt!  ");
										} else {
											w.targetBody.say(t, "I... I won't give you my v-virginity!  ");
										}
										w.append(t, "If that's okay with you, though... then I accept.");
									}
								} else {
									if (w.targetBody.getConfidence() > 66) {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.NEUTRAL);
										if (w.targetBody.getGender() == Forsaken.Gender.MALE) {
											w.targetBody.say(t, "I'd never let some disgusting Demon use my ass ");
										} else {
											w.targetBody.say(t, "I'd never give up my virginity ");
										}
										w.targetBody.say(t, "for the sake of this worthless city.  That's where I'm drawing the line, take it or leave it.");
									} else if (w.targetBody.getConfidence() > 33) {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.SHAME);
										w.targetBody.say(t, "I suppose... I could give it a try.  As long as what you do to me isn't any worse than what you do when we fight.");
									} else {
										Project.changePortrait(w.targetBody.getGender(), w.targetBody.getType(), w.targetBody.civilianPortrait(), w.targetBody.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FEAR);
										w.targetBody.say(t, "I-It's not like I was hoping for you to offer something like this!  Just... don't do anything that hurts, or I'll call the deal off right away!");
									}
								}
								w.targetBody.say(t, "\"\n\n");
								w.append(t, "Agreeing will prevent you from battling and from using the Assist Raid training action today.  However, it will open the new 'Appeal' option during the final battle, which deals " + (w.achievementHeld(8)[0]+1)*(w.achievementHeld(7)[0]+1) + "% Resolve damage and can be upgraded through Negotiating more on subsequent days.");
							}
							JButton Accept = new JButton("Agree");
							Accept.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									p.removeAll();
									w.append(t, "\n\n" + w.getSeparator() + "\n\n");
									w.targetBody.chosenOwner.truce = true;
									w.targetBody.currentHATE = w.targetBody.currentHATE/5;
									if (w.targetBody.chosenOwner.negotiations == 0) {
										w.underlineAppend(t, "Morality/Dignity Distortion");
										w.append(t, "\n\n");
										if (w.targetBody.chosenOwner.morality > 66 || w.targetBody.chosenOwner.dignity > 66) {
											w.addBreak(18);
										}
										if (w.targetBody.getMorality() > 66) {
											if (w.targetBody.getConfidence() > 66) {
												w.append(t, w.targetBody.OwnerName() + "'s sense of responsibility demands that " + w.targetBody.heShe() + " use every tool at " + w.targetBody.hisHer() + " disposal to protect the city.  ");
											} else if (w.targetBody.getConfidence() > 33) {
												w.append(t, w.targetBody.OwnerName() + " has always believed in putting other people's happiness ahead of " + w.targetBody.hisHer() + " own.  ");
											} else {
												w.append(t, w.targetBody.OwnerName() + "'s low self-esteem has always caused " + w.targetBody.himHer() + " to view " + w.targetBody.hisHer() + " body as a disposable asset to be spent for the sake of other people.  ");
											}
											if (w.targetBody.getInnocence() > 66) {
												w.append(t, "Every time the Demons kidnap another civilian or torment one of " + w.targetBody.hisHer() + " allies, " + w.targetBody.heShe() + " blames " + w.targetBody.himHer() + "self for not being smarter and stronger, ");
											} else if (w.targetBody.getInnocence() > 33) {
												w.append(t, w.targetBody.HisHer() + " constant defeats have made " + w.targetBody.himHer() + " desperate, ");
											} else {
												w.append(t, w.targetBody.HeShe() + "'s been forced to conclude that " + w.targetBody.heShe() + " simply isn't strong enough to fulfill " + w.targetBody.hisHer() + " obligations through straightforward combat alone, ");
											}
											if (w.targetBody.getDignity() > 66) {
												w.append(t, "and even though it shames " + w.targetBody.himHer() + " to the core to willingly engage in depraved acts with the Demon Lord, " + w.targetBody.heShe() + "'s begun to develop a fetish for that kind of shame.");
											} else if (w.targetBody.getDignity() > 33) {
												w.append(t, "but as" + w.targetBody.heShe() + "'s grown addicted to " + w.targetBody.hisHer() + " sexual encounters with the Demons, " + w.targetBody.heShe() + "'s subconsciously started to look for excuses to expose " + w.targetBody.himHer() + "self to assault, and this one is perfect.");
											} else {
												w.append(t, "but " + w.targetBody.heShe() + " actually feels some pride at finding an alternative way to stop the Demons' attacks.  " + w.targetBody.HeShe() + " sees no reason at all to be ashamed of using " + w.targetBody.hisHer() + " sex appeal like this.");
											}
										} else if (w.targetBody.getDignity() > 66) {
											if (w.targetBody.getMorality() > 33) {
												w.append(t, w.targetBody.OwnerName() + " has grown addicted to the feeling of being praised by the public as an unbeatable hero.  ");
											} else {
												w.append(t, w.targetBody.OwnerName() + " wants to build a celebrity identity founded on " + w.targetBody.hisHer() + " successes against the Demons.  ");
											}
											if (w.targetBody.getConfidence() > 66) {
												w.append(t, w.targetBody.HeShe() + " isn't used to failing, and the thought of being seen as a failure by others is even more foreign and terrifying.  " + w.targetBody.HeShe() + "'s completely unprepared to deal with it.  ");
											} else if (w.targetBody.getConfidence() > 33) {
												w.append(t, w.targetBody.HeShe() + " knows that it's only a matter of time until everyone else finds out how weak " + w.targetBody.heShe() + " is, and " + w.targetBody.heShe() + "'s willing to do anything to push that day further into the future.  ");
											} else {
												w.append(t, "Lacking a sense of self-worth, " + w.targetBody.heShe() + "'s become completely dependent on the esteem of others, and " + w.targetBody.heShe() + " knows that it's only a matter of time until everyone finds out about " + w.targetBody.hisHer() + " failures.  ");
											}
											if (w.targetBody.getInnocence() > 66) {
												w.append(t, w.targetBody.HeShe() + " jumps on the chance to put a stop to the daily defeats, even if only briefly, and it doesn't even occur to " + w.targetBody.himHer() + " that " + w.targetBody.hisHer() + " willingness to use sexual methods is a sign that " + w.targetBody.heShe() + "'s already lost " + w.targetBody.hisHer() + " grip on the cute, innocent image " + w.targetBody.heShe() + " always presented to the public.");
											} else if (w.targetBody.getInnocence() > 33) {
												w.append(t, w.targetBody.HeShe() + " isn't happy to be forced into such a humiliating situation, but " + w.targetBody.heShe() + "'s always been willing to do whatever was necessary for " + w.targetBody.hisHer() + " image, and a part of " + w.targetBody.himHer() + " takes a sexual satisfaction in continuing to present " + w.targetBody.himHer() + "self as a pure-hearted hero even while " + w.targetBody.heShe() + " engages in depraved acts with the Demon Lord.");
											} else {
												w.append(t, w.targetBody.HeShe() + " knows that allowing the Demon Lord to personally train " + w.targetBody.hisHer() + " body will only accelerate what's already happening to " + w.targetBody.himHer() + ", but " + w.targetBody.heShe() + "'s come to the decision that being able to claim credit for the lack of Demonic attacks is worth it.");
											}
										} else if (w.targetBody.getConfidence() > 66) {
											if (w.targetBody.getInnocence() > 66) {
												w.append(t, w.targetBody.OwnerName() + "'s ego has been utterly shattered by the sexual tortures " + w.targetBody.heShe() + "'s been unable to prevent.  ");
											} else if (w.targetBody.getInnocence() > 33) {
												w.append(t, w.targetBody.OwnerName() + " is still partially in denial about " + w.targetBody.hisHer() + " utter defeat and sexual training at the hands of the Demons.  ");
											} else {
												w.append(t, w.targetBody.OwnerName() + " has been forced to re-evaluate " + w.targetBody.himHer() + "self after finding that " + w.targetBody.heShe() + " was actually neither strong enough nor smart enough to avoid being used as a sexual plaything.  ");
											}
											if (w.targetBody.getDignity() > 33) {
												w.append(t, "The continued support of the public is the only thing holding " + w.targetBody.hisHer() + " self-esteem together, and " + w.targetBody.heShe() + "'ll do anything to keep it.  ");
											} else {
												w.append(t, "The only thing " + w.targetBody.heShe() + " can be proud of is " + w.targetBody.hisHer() + " sexual prowess, and " + w.targetBody.heShe() + "'s decided to embrace that fact.  ");
											}
											if (w.targetBody.getMorality() > 33) {
												w.append(t, "The idea of being responsible for the city's safety again is like a soothing balm for " + w.targetBody.hisHer() + " pride.  " + w.targetBody.HeShe() + " doesn't even care that " + w.targetBody.heShe() + " has to sell " + w.targetBody.hisHer() + " body to buy that safety.");
											} else {
												w.append(t, w.targetBody.HeShe() + " doesn't actually care about protecting the people, but " + w.targetBody.heShe() + "'s so desperate to 'win' at something that " + w.targetBody.heShe() + "'s even willing to serve " + w.targetBody.hisHer() + " hated enemy.");
											}
										} else {
											if (w.targetBody.getConfidence() > 33) {
												w.append(t, w.targetBody.OwnerName() + " used to enjoy fighting, but that was back when " + w.targetBody.hisHer() + " foes were much weaker.  ");
											} else {
												w.append(t, w.targetBody.OwnerName() + " never enjoyed fighting, and lately it's become even more unpleasant than usual.  ");
											}
											if (w.targetBody.getMorality() > 33) {
												w.append(t, w.targetBody.HeShe() + " kept fighting because " + w.targetBody.heShe() + " felt like " + w.targetBody.heShe() + " had to, but now that there's a way to help everyone without doing all that work, " + w.targetBody.heShe() + "'s happy for the excuse to stop.  ");
											} else {
												w.append(t, w.targetBody.HeShe() + "'s started to look for any excuse at all to avoid showing up to battle, and as far as " + w.targetBody.heShe() + "'s concerned, the one that's just been offered by the Demon Lord is perfect.  ");
											}
											if (w.targetBody.getDignity() > 33) {
												w.append(t, w.targetBody.HeShe() + "'s worried about everyone else finding out that " + w.targetBody.heShe() + "'s doing naughty things, but at the same time, the fear of being discovered has also started to give " + w.targetBody.himHer() + " a strange sense of shameful pleasure.");
											} else {
												w.append(t, "The fact that it also involves doing more of the sexual acts " + w.targetBody.heShe() + "'s started to enjoy is an added bonus.");
											}
										}
										w.append(t, "\n\nRelaxing " + w.targetBody.hisHer() + " posture very slightly, " + w.targetBody.ownerName() + " allows the Demon Lord to get closer.");
									} else {
										if (w.targetBody.chosenOwner.negotiations < 3) {
											w.append(t, w.targetBody.OwnerName() + " lets " + w.targetBody.hisHer() + " guard down slightly, allowing the Demon Lord to get closer.");
										} else {
											w.append(t, w.targetBody.OwnerName() + " smiles with satisfaction.  " + w.targetBody.HeShe() + " had already let " + w.targetBody.hisHer() + " guard down, but now " + w.targetBody.heShe() + " actively encourages the Demon Lord to get closer.");
										}
									}
									w.targetBody.chosenOwner.negotiations++;
									JButton Continue = new JButton("Continue");
									Continue.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											w.append(t, "\n\n" + w.getSeparator());
											PickActivity(t, p, f, w, s);
										}
									});
									p.add(Continue);
									p.validate();
									p.repaint();
								}
							});
							Accept.setBackground(Project.PURPLISH);
							p.add(Accept);
							JButton Refuse = new JButton("Refuse");
							Refuse.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									PickActivity(t, p, f, w, s);
								}
							});
							p.add(Refuse);
							p.validate();
							p.repaint();
						}
					});
					Negotiate.setBackground(Project.PURPLISH);
					p.add(Negotiate);
				}
			}
			JButton Done = new JButton("End");
			Done.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					p.removeAll();
					w.append(t, "\n\n" + w.getSeparator() + "\n\n");
					if (w.targetBody.getPLEALevel() >= 4 && w.targetBody.orgasms > 1) {
						if (w.targetBody.getObedience() > 66) {
							w.append(t, "As " + w.lordBody.ownerName() + " leaves, " + w.targetBody.ownerName() + " lays sprawled out on the floor, an expression of complete contentment on " + w.targetBody.hisHer() + " face in the afterglow of " + w.targetBody.hisHer() + " multiple orgasms.");
						} else if (w.targetBody.getObedience() > 33) {
							w.append(t, w.targetBody.OwnerName() + " wears a conflicted expression on " + w.targetBody.hisHer() + " face as " + w.targetBody.heShe() + " watches " + w.lordBody.ownerName() + " leave.  On one hand, " + w.targetBody.heShe() + "'s not sure that " + w.targetBody.hisHer() + " body could have endured any more orgasms in such a short time period.  But on the other hand, the part of " + w.targetBody.himHer() + " that's getting attached to " + w.lordBody.ownerName() + " wants to be broken even harder...");
						} else {
							w.append(t, w.targetBody.OwnerName() + " looks relieved to see " + w.lordBody.ownerName() + " go.  " + w.targetBody.HeShe() + "'s humiliated by how easily " + w.lordBody.ownerName() + " was able to make " + w.targetBody.himHer() + " cum, and the sooner " + w.targetBody.heShe() + " can forget it, the better.");
						}
					} else if (w.targetBody.getHATELevel() >= 3) {
						if (w.targetBody.getDeviancy() > 66) {
							w.append(t, w.targetBody.OwnerName() + " glares at the leaving " + w.lordBody.ownerName() + ", but as soon as " + w.lordBody.heShe() + "'s out of sight, ");
							if (w.sceneLocation == Activity.Location.STAGE) {
								w.append(t, w.targetBody.ownerName() + " runs off to find a place " + w.targetBody.heShe() + " won't be seen ");
							} else {
								w.append(t, w.targetBody.ownerName() + " turns around ");
							}
							w.append(t, "and starts masturbating furiously, hating " + w.targetBody.himHer() + "self for lusting after " + w.lordBody.ownerName() + " even now.");
						} else if (w.targetBody.getDeviancy() > 33) {
							w.append(t, w.targetBody.OwnerName() + " is huffing and scowling, face bright red as " + w.lordBody.ownerName() + " leaves, but it's only partly from anger.  Being treated in such a demeaning way by " + w.lordBody.ownerName() + " has started to turn " + w.targetBody.himHer() + " on, and a part of " + w.targetBody.himHer() + " wishes it would continue.");
						} else {
							w.append(t, w.targetBody.OwnerName() + " glares off after " + w.lordBody.ownerName() + ", even after " + w.lordBody.ownerName() + ", is gone.  An expression of cold anger remains on " + w.targetBody.hisHer() + " face, mixed with the despair of knowing there's nothing " + w.targetBody.heShe() + " can do.");
						}
					} else if (w.targetBody.getINJULevel() >= 3) {
						if (w.targetBody.getObedience() > 66) {
							w.append(t, w.targetBody.OwnerName() + " sleepily apologizes to " + w.lordBody.ownerName() + " for not being able to keep going, then heads off to get some rest.");
						} else if (w.targetBody.getObedience() > 33) {
							w.append(t, w.targetBody.OwnerName() + " sighs with relief at seeing that " + w.lordBody.ownerName() + " is done with " + w.targetBody.himHer() + ", then heads off to get some rest.");
						} else {
							w.append(t, w.targetBody.OwnerName() + " looks angry at having been worn out by " + w.lordBody.ownerName() + ".  " + w.targetBody.HeShe() + " struggles to stay upright and ready to defend " + w.targetBody.himHer() + "self until " + w.lordBody.ownerName() + " is out of sight.");
						}
					} else if (w.targetBody.getObedience() > 66) {
						if (w.targetBody.getPLEALevel() == 3 && w.targetBody.orgasms > 0) {
							w.append(t, w.targetBody.OwnerName() + " sighs with satisfaction, thanking " + w.lordBody.ownerName() + " for taking the time to come see " + w.targetBody.himHer() + ".");
						} else if (w.targetBody.getHATELevel() == 2) {
							w.append(t, "As " + w.lordBody.ownerName() + " starts to leave, " + w.targetBody.ownerName() + " realizes that " + w.targetBody.heShe() + " might have offended " + w.lordBody.himHer() + ", and " + w.targetBody.heShe() + " gets down on " + w.targetBody.hisHer() + " knees, tearfully apologizing and promising to do better for " + w.lordBody.ownerName() + " next time.");
						} else if (w.targetBody.getINJULevel() == 2) {
							w.append(t, w.targetBody.OwnerName() + " insists that " + w.targetBody.heShe() + " can keep going, but it's clear that " + w.targetBody.heShe() + "'s becoming fatigued.  Finally, " + w.targetBody.heShe() + " bows " + w.targetBody.hisHer() + " head thanks " + w.lordBody.ownerName() + " for visiting " + w.targetBody.himHer() + ".");
						} else {
							if (w.targetBody.getGender() != Forsaken.Gender.MALE || w.targetBody.isVVirg() == false) {
								w.append(t, w.targetBody.OwnerName() + " curtsies ");
							} else {
								w.append(t, w.targetBody.OwnerName() + " bows ");
							}
							w.append(t, "deeply, thanking " + w.lordBody.ownerName() + " for visiting " + w.targetBody.himHer() + ".");
						}
					} else if (w.targetBody.getDeviancy() > 66) {
						if (w.targetBody.getPLEALevel() == 3) {
							w.append(t, w.targetBody.OwnerName() + " is incredibly turned on and not even close to being satisfied with this much pleasure.  " + w.targetBody.HeShe() + " tries to stop " + w.lordBody.ownerName() + " from leaving, and only after it becomes clear that it's hopeless does " + w.targetBody.heShe() + " go back into " + w.targetBody.hisHer() + " room to masturbate until " + w.targetBody.heShe() + " can barely even move " + w.targetBody.hisHer() + " hands.");
						} else if (w.targetBody.getHATELevel() == 2) {
							w.append(t, "Even before " + w.lordBody.ownerName() + " is out of sight, " + w.targetBody.ownerName() + " has started masturbating to the memory of the degrading things " + w.lordBody.heShe() + " has put " + w.targetBody.himHer() + " through.  " + w.targetBody.HisHer() + " angry expression fades away into pure lewdness.");
						} else if (w.targetBody.getINJULevel() == 2) {
							w.append(t, "When " + w.lordBody.ownerName() + " leaves, " + w.targetBody.ownerName() + " tries to get some rest, but " + w.targetBody.heShe() + " ends up playing with " + w.targetBody.himHer() + "self in bed, masturbating to the point of orgasm several times before " + w.targetBody.heShe() + "'s finally tired enough to sleep.");
						} else {
							w.append(t, w.targetBody.OwnerName() + " had been hoping on some level to have " + w.targetBody.hisHer() + " sensitive body tormented by " + w.lordBody.ownerName() + ".  " + w.targetBody.HeShe() + " sighs, then heads off to masturbate some more.");
						}
					} else if (w.targetBody.forsakenOwner == null && w.targetBody.chosenOwner != null) {
						if (w.targetBody.chosenOwner.negotiations > 0) {
							if (w.targetBody.getPLEALevel() == 3) {
								w.append(t, w.targetBody.OwnerName() + " can't meet " + w.lordBody.ownerName() + "'s gaze as they part ways.  " + w.targetBody.HeShe() + " is still conflicted about feeling so much pleasure from " + w.lordBody.ownerName() + "'s touch.");
							} else if (w.targetBody.getHATELevel() == 2) {
								w.append(t, w.targetBody.OwnerName() + " glares at " + w.lordBody.ownerName() + ", but " + w.targetBody.heShe() + " still manages to remain civil as " + w.targetBody.heShe() + " says goodbye.  " + w.targetBody.HeShe() + "'s proud of " + w.targetBody.himHer() + "self for maintaining " + w.targetBody.hisHer() + " self-control.");
							} else if (w.targetBody.getINJULevel() == 2) {
								w.append(t, w.targetBody.OwnerName() + " slumps down onto " + w.targetBody.hisHer() + " bottom as soon as " + w.lordBody.ownerName() + " leaves.  " + w.targetBody.HeShe() + "'s glad that " + w.targetBody.heShe() + " was able to last long enough to satisfy " + w.lordBody.ownerName() + ".");
							} else if (w.targetBody.chosenOwner.truce) {
								if (w.targetBody.getInnocence() > 66) {
									w.append(t, w.targetBody.OwnerName() + " briefly wonders why " + w.lordBody.ownerName() + " would go so easy on " + w.targetBody.himHer() + ", but " + w.targetBody.heShe() + " soon loses " + w.targetBody.hisHer() + " train of thought, shrugs to " + w.targetBody.himHer() + "self, and then goes about enjoying " + w.targetBody.hisHer() + " sudden day off.");
								} else if (w.targetBody.getInnocence() > 33) {
									w.append(t, w.targetBody.OwnerName() + " repeatedly asks " + w.lordBody.ownerName() + " to make sure that their encounter here was good enough to meet the terms of the truce, but when " + w.lordBody.ownerName() + " confirms that it is, " + w.targetBody.ownerName() + " nods in satisfaction and lets " + w.lordBody.ownerName() + " go.");
								} else {
									w.append(t, w.targetBody.OwnerName() + "'s eyes narrow with suspicion at " + w.lordBody.ownerName() + " being satisfied so easily, but there's nothing " + w.targetBody.heShe() + " can complain about or object to, so there's nothing " + w.targetBody.heShe() + " can do but watch " + w.lordBody.ownerName() + " go.");
								}
							} else if (w.targetBody.chosenOwner.ANGST >= Project.billion) {
								if (w.targetBody.getConfidence() > 66) {
									w.append(t, w.targetBody.OwnerName() + " looks annoyed at being disturbed without even getting a truce out of it, but " + w.targetBody.heShe() + " remains civil as " + w.lordBody.ownerName() + " leaves.");
								} else if (w.targetBody.getConfidence() > 33) {
									w.append(t, w.targetBody.OwnerName() + " is clearly disappointed that " + w.lordBody.ownerName() + " didn't offer " + w.targetBody.himHer() + " a truce this time, but " + w.targetBody.heShe() + " just starts to prepare for the day's battle as soon as " + w.lordBody.ownerName() + " leaves.");
								} else {
									w.append(t, "As soon as " + w.lordBody.ownerName() + " starts to leave, " + w.targetBody.ownerName() + "'s eyes widen with fear, and " + w.targetBody.heShe() + " spends the rest of the day wondering if " + w.targetBody.heShe() + " did something wrong, and if there won't be any more truces as a result.");
								}
							} else {
								if (w.targetBody.getMorality() > 66) {
									w.append(t, "As " + w.targetBody.ownerName() + " watches " + w.lordBody.ownerName() + " leave, " + w.targetBody.heShe() + " feels proud of " + w.targetBody.himHer() + "self for resisting the urge to offer another truce.  " + w.targetBody.HeShe() + " feels like " + w.targetBody.heShe() + " might be able to win in a straight fight now.");
								} else if (w.targetBody.getMorality() > 33) {
									w.append(t, "As soon as " + w.lordBody.ownerName() + " leaves, " + w.targetBody.ownerName() + " starts to prepare for battle, feeling only a little regretful about not making another truce.");
								} else {
									w.append(t, w.targetBody.OwnerName() + " smirks after the leaving " + w.lordBody.ownerName() + ", happy to have played hard-to-get by resisting the temptation of making another truce.  " + w.targetBody.HeShe() + " daydreams about getting some even better terms from " + w.lordBody.ownerName() + ".");
								}
							}
						} else {
							if (w.targetBody.getPLEALevel() == 3) {
								w.append(t, w.targetBody.OwnerName() + " feels violated after having " + w.targetBody.hisHer() + " body so suddenly toyed with, and " + w.targetBody.heShe() + " tries to give chase, but the pleasure makes " + w.targetBody.hisHer() + " legs wobble, and " + w.targetBody.heShe() + " falls to " + w.targetBody.hisHer() + " knees, unable to stop " + w.lordBody.ownerName() + " from leaving.");
							} else if (w.targetBody.getHATELevel() == 2) {
								w.append(t, "As soon as it looks like " + w.lordBody.ownerName() + " is about to leave, " + w.targetBody.ownerName() + " attacks with " + w.targetBody.hisHer() + " " + w.targetBody.chosenOwner.weapon + ".  Killing " + w.lordBody.ownerName() + "'s body is pointless, but it does make " + w.targetBody.ownerName() + " feel better.");
							} else if (w.targetBody.getINJULevel() == 2) {
								w.append(t, "When " + w.lordBody.ownerName() + " starts to leave, " + w.targetBody.ownerName() + " steps back as well, holding " + w.targetBody.himHer() + "self cautiously.  The encounter has been taxing on " + w.targetBody.hisHer() + " body and mind, and that gives " + w.lordBody.ownerName() + " the chance to slip away.");
							} else if (w.targetBody.getMorality() > 66) {
								w.append(t, w.targetBody.OwnerName() + " still isn't sure why " + w.lordBody.ownerName() + " approached " + w.targetBody.himHer() + ", but " + w.targetBody.heShe() + " respects " + w.targetBody.hisHer() + " opponent's seemingly peaceful intentions and allows " + w.lordBody.ownerName() + " to escape.");
							} else if (w.targetBody.getMorality() > 33) {
								w.append(t, "When " + w.lordBody.ownerName() + " moves to leave, " + w.targetBody.ownerName() + " hesitates, considering whether " + w.targetBody.heShe() + " should kill this body just to hamper the Demon Lord, even if only a little bit.  But " + w.targetBody.heShe() + " ultimately decides that there's no point and lets " + w.lordBody.ownerName() + " go.");
							} else {
								w.append(t, w.targetBody.OwnerName() + " doesn't feel any need to pursue " + w.lordBody.ownerName() + ", so " + w.targetBody.heShe() + " just shrugs and continues about " + w.targetBody.hisHer() + " daily routine.");
							}
						}
					} else {
						if (w.targetBody.getPLEALevel() == 3 && w.targetBody.orgasms > 0) {
							w.append(t, "Caught up in the afterglow, " + w.targetBody.ownerName() + " almost doesn't notice " + w.lordBody.ownerName() + " leaving.  " + w.targetBody.HeShe() + " hesitates, feeling awkward about what just happened, and then " + w.lordBody.ownerName() + " is gone.");
						} else if (w.targetBody.getHATELevel() == 2) {
							w.append(t, w.targetBody.OwnerName() + " glares after " + w.lordBody.ownerName() + ", tempted to try to shout some insult after " + w.lordBody.ownerName() + ".  But ultimately, " + w.targetBody.heShe() + " decides to just be grateful that it's over.");
						} else if (w.targetBody.getINJULevel() == 2) {
							w.append(t, w.targetBody.OwnerName() + " slumps over, glad that " + w.lordBody.ownerName() + " is satisfied.  As soon as " + w.lordBody.ownerName() + " is gone, " + w.targetBody.ownerName() + " goes to get some rest.");
						} else if (w.targetBody.getObedience() > w.targetBody.getDeviancy()) {
							if (w.targetBody.getConfidence() > 66) {
								w.append(t, w.targetBody.OwnerName() + " huffs in mild annoyance at being disturbed for no reason, but " + w.targetBody.heShe() + "'s still careful to be courteous to " + w.lordBody.ownerName() + " as " + w.lordBody.heShe() + " leaves.");
							} else if (w.targetBody.getConfidence() > 33) {
								w.append(t, w.targetBody.OwnerName() + " gives " + w.lordBody.ownerName() + " ");
								if (w.targetBody.getGender() != Forsaken.Gender.MALE || w.targetBody.isVVirg() == false) {
									w.append(t, "a small cursty ");
								} else {
									w.append(t, "a small bow ");
								}
								w.append(t, "as " + w.lordBody.heShe() + " leaves.");
							} else {
								w.append(t, w.targetBody.OwnerName() + " is startled by " + w.lordBody.ownerName() + "'s sudden departure, and " + w.targetBody.heShe() + " spends the rest of the day nervously wondering if " + w.targetBody.heShe() + " displeased " + w.lordBody.ownerName() + " somehow.");
							}
						} else {
							if (w.targetBody.getInnocence() > 66) {
								w.append(t, w.targetBody.OwnerName() + " is happy to see " + w.lordBody.ownerName() + " leave, but " + w.targetBody.heShe() + " also feels a strange sadness.  " + w.targetBody.HeShe() + "'s unable to consciously acknowledge that " + w.targetBody.heShe() + "'s come to enjoy being sexually trained, and so " + w.targetBody.heShe() + " continues to wonder about it until something finally distracts " + w.targetBody.himHer() + ".");
							} else if (w.targetBody.getInnocence() > 33) {
								w.append(t, w.targetBody.OwnerName() + " doesn't feel anything unusual as " + w.lordBody.ownerName() + " leaves, but that evening, when " + w.targetBody.heShe() + "'s masturbating, fantasies of the things " + w.lordBody.ownerName() + " might do to " + w.targetBody.himHer() + " next time will continually intrude on " + w.targetBody.hisHer() + " thoughts.");
							} else {
								w.append(t, w.targetBody.OwnerName() + " feels a sense of regret at seeing " + w.lordBody.ownerName() + " leave, and when " + w.targetBody.heShe() + " realizes that it's because " + w.targetBody.heShe() + " was looking forward to being sexually trained, " + w.targetBody.heShe() + " gets angry at " + w.targetBody.himHer() + "self for being so weak.");
							}
						}
					}
					JButton Continue = new JButton("Continue");
					Continue.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (w.active) {
								Project.Shop(t, p, f, w);
							} else {
								WriteObject wobj = new WriteObject();
								wobj.serializeSaveData(s);
								Project.IntroOne(t, p, f, w);
							}
						}
					});
					p.add(Continue);
					p.validate();
					p.repaint();
				}
			});
			p.add(Done);
			p.validate();
			p.repaint();
		} else {
			p.removeAll();
			Activity pickedActivity = Project.SpreadLegs;
			Body target = null;
			Body consideredTarget = w.lordBody;
			int[] activityWeights = new int[Project.allActivities.length];
			int extreme = 0;
			Boolean ending = false;
			for (int i = 0; i < inProgress.length; i++) {
				int value = inProgress[i].weight(w, this, targets[i]);
				int validEnderIndex = -1;
				for (int j = 0; j < inProgress[i].enders.length; j++) {
					if (inProgress[i].enders[j].valid(this, targets[i])) {
						validEnderIndex = j;
						j = inProgress[i].enders.length;
					}
				}
				if (value < extreme && validEnderIndex >= 0) {
					ending = true;
					extreme = value;
					pickedActivity = inProgress[i].enders[validEnderIndex];
					if (pickedActivity.targeted) {
						target = consideredTarget;
					} else {
						target = null;
					}
				}
			}
			for (int i = 0; i < activityWeights.length && ending == false; i++) {
				if (Project.allActivities[i].pickable && Project.allActivities[i].valid(this, w.lordBody)) {
					int value = Project.allActivities[i].weight(w, this, w.lordBody);
					if (value > extreme) {
						extreme = value;
						pickedActivity = Project.allActivities[i];
						if (pickedActivity.targeted) {
							target = consideredTarget;
						} else {
							target = null;
						}
					}
				}
			}
			pickedActivity.startActivity(t, w, this, target);
			w.append(t, "\n\n");
			CharacterDamage(t, w);
			w.append(t, "\n\n");
			pickedActivity.activityTalk(t, w, this, target);
			Continue(t, p, f, w, s);
		}
	}
	
	public void LordDamage(JTextPane t, WorldState w) {
		w.append(t, OwnerName());
		long[] damageTaken = new long[8];
		for (int i = 0; i < inProgress.length; i++) {
			damageTaken = inProgress[i].damageContribution(w, this, damageTaken);
		}
		long[] actualDamage = InflictDamage(damageTaken);
		for (int i = 5; i < 6; i++) {
			if (i == 0) {
				w.append(t, "\nFEAR [");
			} else if (i == 1) {
				w.append(t, "\nDISG [");
			} else if (i == 2) {
				w.append(t, "\nPAIN [");
			} else if (i == 3) {
				w.append(t, "\nSHAM [");
			} else if (i == 4) {
				w.append(t, "\n\nHATE [");
			} else if (i == 5) {
				w.append(t, "\nPLEA [");
			} else if (i == 6) {
				w.append(t, "\nTIRE [");
			} else {
				w.append(t, "\nEXPO [");
			}
			char fillChar = '\'';
			char remainChar = ' ';
			int tier = 0;
			long totalDamage = currentDamage()[i] + actualDamage[i];
			int increment = 10;
			while (totalDamage >= 10000000) {
				tier++;
				totalDamage = totalDamage/1000000;
			}
			Boolean mixed = false;
			if (tier > 0) {
				remainChar = '#';
				mixed = true;
			}
			if (totalDamage >= 100) {
				remainChar = fillChar;
				fillChar = '-';
				increment = 100;
				mixed = false;
			}
			if (totalDamage >= 1000) {
				remainChar = fillChar;
				fillChar = '~';
				increment = 1000;
			}
			if (totalDamage >= 10000) {
				remainChar = fillChar;
				fillChar = '=';
				increment = 10000;
			}
			if (totalDamage >= 100000) {
				remainChar = fillChar;
				fillChar = 'X';
				increment = 100000;
			}
			if (totalDamage >= 1000000) {
				remainChar = fillChar;
				fillChar = '#';
				increment = 1000000;
			}
			long incrementsRemaining = (totalDamage)/increment;
			for (int j = 0; j < 10; j++) {
				if (incrementsRemaining-- > 0) {
					if (tier == 0) {
						w.append(t, fillChar + "");
					} else if (tier == 1) {
						w.inverseAppend(t, fillChar + "");
					} else {
						w.tierTwoAppend(t, fillChar + "");
					}
				} else {
					if (tier == 0 || (tier == 1 && mixed)) {
						w.append(t, remainChar + "");
					} else if (tier == 1 || (tier == 2 && mixed)) {
						w.inverseAppend(t, remainChar + "");
					} else {
						w.tierTwoAppend(t, remainChar + "");
					}
				}
			}
			w.append(t, "] (" + new Chosen().fixedFormat(currentDamage()[i]));
			if (actualDamage[i] == 0) {
				w.append(t, "        ");
			} else {
				w.append(t, " + " + new Chosen().fixedFormat(actualDamage[i]));
			}
			if (i == 0) {
				currentFEAR += actualDamage[i];
			} else if (i == 1) {
				currentDISG += actualDamage[i];
			} else if (i == 2) {
				currentPAIN += actualDamage[i];
			} else if (i == 3) {
				currentSHAM += actualDamage[i];
			} else if (i == 4) {
				currentHATE += actualDamage[i];
			} else if (i == 5) {
				currentPLEA += actualDamage[i];
			} else if (i == 6) {
				currentINJU += actualDamage[i];
			} else if (i == 7) {
				currentEXPO += actualDamage[i];
			}
			w.append(t, " = " + new Chosen().fixedFormat(currentDamage()[i]) + ") ");
			if (currentDamage()[i] > 0) {
				if (i == 0) {
					if (currentDamage()[i] < 100) {
						w.append(t, "feeling in control");
					} else if (currentDamage()[i] < 1000) {
						w.append(t, "slightly intimidated");
					} else if (currentDamage()[i] < 10000) {
						w.append(t, "terrified");
					} else if (currentDamage()[i] < 100000) {
						w.append(t, "desperate to please");
					} else if (currentDamage()[i] < 1000000) {
						w.append(t, "blind panic");
					} else {
						w.append(t, "consumed by fear");
					}
				} else if (i == 1) {
					if (currentDamage()[i] < 100) {
						w.append(t, "comfortable with own actions");
					} else if (currentDamage()[i] < 1000) {
						w.append(t, "uncomfortable");
					} else if (currentDamage()[i] < 10000) {
						w.append(t, "disgusted with self");
					} else if (currentDamage()[i] < 100000) {
						w.append(t, "struggling not to be sick");
					} else if (currentDamage()[i] < 1000000) {
						w.append(t, "trying to ignore everything");
					} else {
						w.append(t, "overwhelming disgust");
					}
				} else if (i == 2) {
					if (currentDamage()[i] < 100) {
						w.append(t, "no notable pain");
					} else if (currentDamage()[i] < 1000) {
						w.append(t, "conscious of pain");
					} else if (currentDamage()[i] < 10000) {
						w.append(t, "distracted by pain");
					} else if (currentDamage()[i] < 100000) {
						w.append(t, "reeling from the pain");
					} else if (currentDamage()[i] < 1000000) {
						w.append(t, "overwhelmed by pain");
					} else {
						w.append(t, "feels nothing but pain");
					}
				} else if (i == 3) {
					if (currentDamage()[i] < 100) {
						w.append(t, "not self-conscious");
					} else if (currentDamage()[i] < 1000) {
						w.append(t, "realizes how " + heShe() + " must look");
					} else if (currentDamage()[i] < 10000) {
						w.append(t, "feels watching eyes");
					} else if (currentDamage()[i] < 100000) {
						w.append(t, "can't stop thinking about how " + heShe() + " looks");
					} else if (currentDamage()[i] < 1000000) {
						w.append(t, "tears of shame");
					} else {
						w.append(t, "mind paralyzed by shame");
					}
				} else if (i == 4) {
					if (currentDamage()[i] < 100) {
						w.append(t, "doesn't mind what's happening");
					} else if (currentDamage()[i] < 1000) {
						w.append(t, "annoyed");
					} else if (currentDamage()[i] < 10000) {
						w.append(t, "strong anger");
					} else if (currentDamage()[i] < 100000) {
						w.append(t, "too angry to cooperate");
					} else if (currentDamage()[i] < 1000000) {
						w.append(t, "overpowering hatred");
					} else {
						w.append(t, "all-consuming hatred");
					}
				} else if (i == 5) {
					if (currentDamage()[i] < 100) {
						w.append(t, "not really turned on");
					} else if (currentDamage()[i] < 1000) {
						w.append(t, "sexually aroused");
					} else if (currentDamage()[i] < 10000) {
						w.append(t, "orgasm approaching");
					} else if (currentDamage()[i] < 100000) {
						w.append(t, "orgasmic pleasure");
					} else if (currentDamage()[i] < 1000000) {
						w.append(t, "experiencing multiple orgasms");
					} else {
						w.append(t, "cumming nonstop");
					}
				} else if (i == 6) {
					if (currentDamage()[i] < 100) {
						w.append(t, "full of energy");
					} else if (currentDamage()[i] < 1000) {
						w.append(t, "starting to get tired");
					} else if (currentDamage()[i] < 10000) {
						w.append(t, "too tired to continue");
					} else if (currentDamage()[i] < 100000) {
						w.append(t, "completely spent");
					} else if (currentDamage()[i] < 1000000) {
						w.append(t, "unconscious");
					} else {
						w.append(t, "body failing under the strain");
					}
				} else if (i == 7) {
					if (isParasitized()) {
						if (currentDamage()[i] < 100) {
							w.append(t, "no more exposed than usual");
						} else if (currentDamage()[i] < 1000) {
							w.append(t, "more exposed than usual");
						} else if (currentDamage()[i] < 10000) {
							if (getGender().equals(Forsaken.Gender.MALE)) {
								w.append(t, "chest");
							} else {
								w.append(t, "breasts");
							}
							w.append(t, " on full display");
						} else if (currentDamage()[i] < 100000) {
							if (getGender().equals(Forsaken.Gender.MALE)) {
								w.append(t, "asshole");
							} else {
								w.append(t, "pussy");
							}
							w.append(t, " uncovered for easy access");
						} else if (currentDamage()[i] < 1000000) {
							w.append(t, "essentially naked");
						} else {
							w.append(t, "worse than naked");
						}
					} else {
						if (currentDamage()[i] < 100) {
							w.append(t, "clothes slightly disheveled");
						} else if (currentDamage()[i] < 1000) {
							w.append(t, "clothes have some small tears");
						} else if (currentDamage()[i] < 10000) {
							w.append(t, "clothes torn across " + hisHer() + " hips and chest");
						} else if (currentDamage()[i] < 100000) {
							w.append(t, "shredded clothes sometimes expose everything");
						} else if (currentDamage()[i] < 1000000) {
							w.append(t, "essentially naked");
						} else {
							w.append(t, "every inch of skin exposed");
						}
					}
				}
			}
		}
		if (crossedThreshold(actualDamage[5], currentDamage()[5]) > 0 || orgasmLevel(actualDamage[5], currentDamage()[5]) > 0) {
			if (getPLEALevel() < 3) {
				w.purpleAppend(t, "\n\nPLEA up!  ");
				if (getPLEALevel() == 1) {
					w.append(t, OwnerName() + "'s body is beginning to experience sexual arousal.");
				} else if (getPLEALevel() == 2) {
					w.append(t, OwnerName() + "'s ");
					if (parts[PENIS] > 0) {
						w.append(t, "penis is twitching, straining as " + hisHer() + " body approaches orgasm.");
					} else {
						w.append(t, "whole body feels sensitive, pleasure pulsing in " + hisHer() + " loins as orgasm approaches.");
					}
				}
			} else if (orgasmPossible() == false) {
				w.purpleAppend(t, "\n\nPLEA up!  " + OwnerName() + " wildly bucks " + hisHer() + " hips in search of orgasm, but " + heShe() + " can't give " + himHer() + "self the necessary stimulation, and " + heShe() + " comes back from the brink of climax.");
			} else {
				Body[] givers = new Body[0];
				for (int i = 0; i < inProgress.length; i++ ){
					if (inProgress[i].causesOrgasm && inProgress[i].counterpart != null && targets[i] != null && targets[i].forsakenOwner != null) {
						Boolean found = false;
						for (int j = 0; j < givers.length; j++ ) {
							if (givers[j] == targets[i]) {
								found = true;
							}
						}
						if (found == false) {
							Body[] newGivers = new Body[givers.length+1];
							for (int j = 0; j < givers.length; j++) {
								newGivers[j] = givers[j];
							}
							newGivers[givers.length] = targets[i];
							givers = newGivers;
						}
					}
				}
				for (int i = 0; i < givers.length; i++) {
					if (givers[i] != this && givers[i].forsakenOwner != null) {
						givers[i].forsakenOwner.orgasmsGiven++;
					}
				}
				w.purpleAppend(t, "\n\nOrgasm!  ");
				if (orgasms == 0) {
					if (currentDamage()[5]-actualDamage[5] >= 10000 && currentDamage()[5] >= 30000) {
						w.append(t, "The pent-up climax comes all at once, and " + ownerName());
						if (parts[PENIS] > 0) {
							w.append(t, "'s cock spurts a huge load in all directions.");
						} else {
							w.append(t, " cums so hard that " + heShe() + " feels weak and unsteady afterwards.");
						}
					} else {
						w.append(t, OwnerName() + "'s whole body shudders shudders as ");
						if (parts[PENIS] > 0) {
							w.append(t, hisHer() + " cock shoots its load.");
						} else {
							w.append(t, heShe() + " climaxes.");
						}
					}
				} else {
					if (orgasms == 1) {
						w.append(t, OwnerName() + " cums again, ");
						if (parts[PENIS] > 0) {
							w.append(t, "shooting a smaller load this time, but it feels even better.");
						} else {
							w.append(t, hisHer() + " second climax proving even more intense than the first.");
						}
					} else {
						w.append(t, OwnerName() + " cums yet again, ");
						if (parts[PENIS] > 0) {
							w.append(t, hisHer() + " exhausted penis managing only a trickle of cum, but even that much feels incredibly good.");
						} else {
							w.append(t, "body spasming wildly, completely outside " + hisHer() + " control.");
						}
					}
				}
				orgasms++;
			}
		}
	}
	
	public void CharacterDamage(JTextPane t, WorldState w) {
		w.append(t, OwnerName());
		long[] damageTaken = new long[8];
		if (w.sceneLocation == Activity.Location.STAGE) {
			damageTaken[3] = 10;
		}
		if (getObedience() < 67) {
			damageTaken[4] = (67 - getObedience())/11;
		}
		damageTaken[6] = 4;
		for (int i = 0; i < inProgress.length; i++) {
			damageTaken = inProgress[i].damageContribution(w, this, damageTaken);
		}
		if (w.sceneLocation == Activity.Location.STAGE) {
			damageTaken[3] = damageTaken[3]*3;
		}
		long[] actualDamage = InflictDamage(damageTaken);
		for (int i = 0; i < 8; i++) {
			if (i == 7 && currentDamage()[i] + actualDamage[i] > 1000000) {
				actualDamage[i] = 1000000 - currentDamage()[i];
				removeActivity(Project.Stripped, null);
			}
			if (i == 0) {
				w.append(t, "\nFEAR [");
			} else if (i == 1) {
				w.append(t, "\nDISG [");
			} else if (i == 2) {
				w.append(t, "\nPAIN [");
			} else if (i == 3) {
				w.append(t, "\nSHAM [");
			} else if (i == 4) {
				w.append(t, "\n\nHATE [");
			} else if (i == 5) {
				w.append(t, "\nPLEA [");
			} else if (i == 6) {
				w.append(t, "\nTIRE [");
			} else {
				w.append(t, "\nEXPO [");
			}
			char fillChar = '\'';
			char remainChar = ' ';
			int tier = 0;
			long totalDamage = currentDamage()[i] + actualDamage[i];
			int increment = 10;
			while (totalDamage >= 10000000) {
				tier++;
				totalDamage = totalDamage/1000000;
			}
			Boolean mixed = false;
			if (tier > 0) {
				remainChar = '#';
				mixed = true;
			}
			if (totalDamage >= 100) {
				remainChar = fillChar;
				fillChar = '-';
				increment = 100;
				mixed = false;
			}
			if (totalDamage >= 1000) {
				remainChar = fillChar;
				fillChar = '~';
				increment = 1000;
			}
			if (totalDamage >= 10000) {
				remainChar = fillChar;
				fillChar = '=';
				increment = 10000;
			}
			if (totalDamage >= 100000) {
				remainChar = fillChar;
				fillChar = 'X';
				increment = 100000;
			}
			if (totalDamage >= 1000000) {
				remainChar = fillChar;
				fillChar = '#';
				increment = 1000000;
			}
			long incrementsRemaining = (totalDamage)/increment;
			for (int j = 0; j < 10; j++) {
				if (incrementsRemaining-- > 0) {
					if (tier == 0) {
						w.append(t, fillChar + "");
					} else if (tier == 1) {
						w.inverseAppend(t, fillChar + "");
					} else {
						w.tierTwoAppend(t, fillChar + "");
					}
				} else {
					if (tier == 0 || (tier == 1 && mixed)) {
						w.append(t, remainChar + "");
					} else if (tier == 1 || (tier == 2 && mixed)) {
						w.inverseAppend(t, remainChar + "");
					} else {
						w.tierTwoAppend(t, remainChar + "");
					}
				}
			}
			w.append(t, "] (" + new Chosen().fixedFormat(currentDamage()[i]));
			if (actualDamage[i] == 0) {
				w.append(t, "        ");
			} else {
				w.append(t, " + " + new Chosen().fixedFormat(actualDamage[i]));
			}
			if (i == 0) {
				currentFEAR += actualDamage[i];
			} else if (i == 1) {
				currentDISG += actualDamage[i];
			} else if (i == 2) {
				currentPAIN += actualDamage[i];
			} else if (i == 3) {
				currentSHAM += actualDamage[i];
			} else if (i == 4) {
				currentHATE += actualDamage[i];
			} else if (i == 5) {
				currentPLEA += actualDamage[i];
			} else if (i == 6) {
				currentINJU += actualDamage[i];
			} else if (i == 7) {
				currentEXPO += actualDamage[i];
			}
			w.append(t, " = " + new Chosen().fixedFormat(currentDamage()[i]) + ") ");
			if (currentDamage()[i] > 0) {
				if (i == 0) {
					if (currentDamage()[i] < 100) {
						w.append(t, "feeling in control");
					} else if (currentDamage()[i] < 1000) {
						w.append(t, "slightly intimidated");
					} else if (currentDamage()[i] < 10000) {
						w.append(t, "terrified");
					} else if (currentDamage()[i] < 100000) {
						w.append(t, "desperate to please");
					} else if (currentDamage()[i] < 1000000) {
						w.append(t, "blind panic");
					} else {
						w.append(t, "consumed by fear");
					}
				} else if (i == 1) {
					if (currentDamage()[i] < 100) {
						w.append(t, "comfortable with own actions");
					} else if (currentDamage()[i] < 1000) {
						w.append(t, "uncomfortable");
					} else if (currentDamage()[i] < 10000) {
						w.append(t, "disgusted with self");
					} else if (currentDamage()[i] < 100000) {
						w.append(t, "struggling not to be sick");
					} else if (currentDamage()[i] < 1000000) {
						w.append(t, "trying to ignore everything");
					} else {
						w.append(t, "overwhelming disgust");
					}
				} else if (i == 2) {
					if (currentDamage()[i] < 100) {
						w.append(t, "no notable pain");
					} else if (currentDamage()[i] < 1000) {
						w.append(t, "conscious of pain");
					} else if (currentDamage()[i] < 10000) {
						w.append(t, "distracted by pain");
					} else if (currentDamage()[i] < 100000) {
						w.append(t, "reeling from the pain");
					} else if (currentDamage()[i] < 1000000) {
						w.append(t, "overwhelmed by pain");
					} else {
						w.append(t, "feels nothing but pain");
					}
				} else if (i == 3) {
					if (currentDamage()[i] < 100) {
						w.append(t, "not self-conscious");
					} else if (currentDamage()[i] < 1000) {
						w.append(t, "realizes how " + heShe() + " must look");
					} else if (currentDamage()[i] < 10000) {
						w.append(t, "feels watching eyes");
					} else if (currentDamage()[i] < 100000) {
						w.append(t, "can't stop thinking about how " + heShe() + " looks");
					} else if (currentDamage()[i] < 1000000) {
						w.append(t, "tears of shame");
					} else {
						w.append(t, "mind paralyzed by shame");
					}
				} else if (i == 4) {
					if (currentDamage()[i] < 100) {
						w.append(t, "doesn't mind what's happening");
					} else if (currentDamage()[i] < 1000) {
						w.append(t, "annoyed");
					} else if (currentDamage()[i] < 10000) {
						w.append(t, "strong anger");
					} else if (currentDamage()[i] < 100000) {
						w.append(t, "too angry to cooperate");
					} else if (currentDamage()[i] < 1000000) {
						w.append(t, "overpowering hatred");
					} else {
						w.append(t, "all-consuming hatred");
					}
				} else if (i == 5) {
					if (currentDamage()[i] < 100) {
						w.append(t, "not really turned on");
					} else if (currentDamage()[i] < 1000) {
						w.append(t, "blushing and distracted");
					} else if (currentDamage()[i] < 10000) {
						w.append(t, "orgasm approaching");
					} else if (currentDamage()[i] < 100000) {
						w.append(t, "oragsmic pleasure");
					} else if (currentDamage()[i] < 1000000) {
						w.append(t, "experiencing multiple orgasms");
					} else {
						w.append(t, "cumming nonstop");
					}
				} else if (i == 6) {
					if (currentDamage()[i] < 100) {
						w.append(t, "full of energy");
					} else if (currentDamage()[i] < 1000) {
						w.append(t, "starting to get tired");
					} else if (currentDamage()[i] < 10000) {
						w.append(t, "too tired to continue");
					} else if (currentDamage()[i] < 100000) {
						w.append(t, "completely spent");
					} else if (currentDamage()[i] < 1000000) {
						w.append(t, "consciousness fading");
					} else {
						w.append(t, "body failing under the strain");
					}
				} else if (i == 7) {
					if (isParasitized()) {
						if (currentDamage()[i] < 100) {
							w.append(t, "no more exposed than usual");
						} else if (currentDamage()[i] < 1000) {
							w.append(t, "more exposed than usual");
						} else if (currentDamage()[i] < 10000) {
							if (getGender().equals(Forsaken.Gender.MALE)) {
								w.append(t, "chest");
							} else {
								w.append(t, "breasts");
							}
							w.append(t, " on full display");
						} else if (currentDamage()[i] < 100000) {
							if (getGender().equals(Forsaken.Gender.MALE)) {
								w.append(t, "asshole");
							} else {
								w.append(t, "pussy");
							}
							w.append(t, " uncovered for easy access");
						} else if (currentDamage()[i] < 1000000) {
							w.append(t, "essentially naked");
						} else {
							w.append(t, "completely naked");
						}
					} else {
						if (currentDamage()[i] < 100) {
							w.append(t, "clothes slightly disheveled");
						} else if (currentDamage()[i] < 1000) {
							w.append(t, "clothes have some small tears");
						} else if (currentDamage()[i] < 10000) {
							w.append(t, "clothes torn across " + hisHer() + " hips and chest");
						} else if (currentDamage()[i] < 100000) {
							w.append(t, "shredded clothes sometimes expose everything");
						} else if (currentDamage()[i] < 1000000) {
							w.append(t, "essentially naked");
						} else {
							w.append(t, "completely naked");
						}
					}
				}
			}
		}
		if (crossedThreshold(actualDamage[0], currentDamage()[0]) > 0) {
			w.purpleAppend(t, "\n\nFEAR up!  ");
			if (crossedThreshold(actualDamage[0], currentDamage()[0]) == 1) {
				w.append(t, OwnerName() + " can't help but think of the consquences of displeasing " + w.lordBody.ownerName() + ".");
			} else if (crossedThreshold(actualDamage[0], currentDamage()[0]) == 2) {
				w.append(t, OwnerName() + " is desperate to please " + w.lordBody.ownerName() + ".");
			} else {
				w.append(t, OwnerName() + " is overcome by " + hisHer() + " terror of " + w.lordBody.ownerName() + ".");
			}
		}
		if (crossedThreshold(actualDamage[1], currentDamage()[1]) > 0) {
			w.purpleAppend(t, "\n\nDISG up!  ");
			if (crossedThreshold(actualDamage[1], currentDamage()[1]) == 1) {
				w.append(t, OwnerName() + " has started to feel bad about what " + heShe() + "'s doing.");
			} else if (crossedThreshold(actualDamage[1], currentDamage()[1]) == 2) {
				w.append(t, OwnerName() + " has firmly crossed the line into activities " + heShe() + "'s not comfortable with.");
			} else {
				w.append(t, "It's a struggle for " + ownerName() + " to force " + himHer() + "self to continue.");
			}
		}
		if (crossedThreshold(actualDamage[2], currentDamage()[2]) > 0) {
			w.purpleAppend(t, "\n\nPAIN up!  ");
			if (crossedThreshold(actualDamage[2], currentDamage()[2]) == 1) {
				w.append(t, OwnerName() + " consciously notices that " + heShe() + "'s feeling pain.");
			} else if (crossedThreshold(actualDamage[2], currentDamage()[2]) == 2) {
				w.append(t, OwnerName() + " can't stop " + himHer() + "self from flinching away from the pain.");
			} else {
				w.append(t, OwnerName() + " is in so much pain that " + heShe() + " can hardly focus.");
			}
		}
		if (crossedThreshold(actualDamage[3], currentDamage()[3]) > 0) {
			w.purpleAppend(t, "\n\nSHAM up!  ");
			if (crossedThreshold(actualDamage[3], currentDamage()[3]) == 1) {
				w.append(t, OwnerName() + " has realized how lascivious " + hisHer() + " appearance has become.");
			} else if (crossedThreshold(actualDamage[3], currentDamage()[3]) == 2) {
				w.append(t, OwnerName() + " is becoming increasingly self-conscious.");
			} else {
				w.append(t, OwnerName() + " can't stop thinking about how " + heShe() + " must look.");
			}
		}
		if (crossedThreshold(actualDamage[4], currentDamage()[4]) > 0) {
			w.purpleAppend(t, "\n\nHATE up!  ");
			if (crossedThreshold(actualDamage[4], currentDamage()[4]) == 1) {
				w.append(t, OwnerName() + " is growing annoyed with the situation.");
			} else if (crossedThreshold(actualDamage[4], currentDamage()[4]) == 2) {
				w.append(t, OwnerName() + " is angry at " + w.lordBody.ownerName() + ".");
			} else {
				w.append(t, OwnerName() + " is consumed by hatred.");
				if (currentDamage()[4] - actualDamage[4] < 10000) {
					w.append(t, "  " + HisHer() + " Sexual Barrier fades away.");
				}
			}
		}
		if (crossedThreshold(actualDamage[5], currentDamage()[5]) > 0 || orgasmLevel(actualDamage[5], currentDamage()[5]) > 0) {
			if (getPLEALevel() < 3) {
				w.purpleAppend(t, "\n\nPLEA up!  ");
				if (getPLEALevel() == 1) {
					w.append(t, OwnerName() + " is starting to get turned on.");
				} else if (getPLEALevel() == 2) {
					w.append(t, OwnerName() + "'s ");
					if (parts[PENIS] > 0) {
						w.append(t, "penis is twitching, straining as " + heShe() + " approaches orgasm.");
					} else {
						w.append(t, "whole body feels sensitive, pleasure pulsing in " + hisHer() + " loins as orgasm approaches.");
					}
				}
			} else if (forsakenOwner == null && chosenOwner != null && chosenOwner.vVirg && getInnocence() > 66) {
				w.purpleAppend(t, "\n\nPLEA up!  ");
				w.append(t, "The warm feelings surging through " + ownerName() + "'s lower body are reaching a level " + heShe() + " never thought possible.  " + HeShe() + " feels something amazing coming, but the sensations are so unfamiliar and overpowering that " + heShe() + " starts to panic.");
			} else if (orgasmPossible() == false) {
				w.purpleAppend(t, "\n\nPLEA up!  ");
				w.append(t, OwnerName() + " wildly bucks " + hisHer() + " hips in search of orgasm, but " + heShe() + " can't give " + himHer() + "self the necessary stimulation, and " + heShe() + " comes back from the brink of climax.");
			} else {
				Body[] givers = new Body[0];
				for (int i = 0; i < inProgress.length; i++ ){
					if (inProgress[i].causesOrgasm && inProgress[i].counterpart != null && targets[i] != null && targets[i].forsakenOwner != null) {
						Boolean found = false;
						for (int j = 0; j < givers.length; j++ ) {
							if (givers[j] == targets[i]) {
								found = true;
							}
						}
						if (found == false) {
							Body[] newGivers = new Body[givers.length+1];
							for (int j = 0; j < givers.length; j++) {
								newGivers[j] = givers[j];
							}
							newGivers[givers.length] = targets[i];
							givers = newGivers;
						}
					}
				}
				for (int i = 0; i < givers.length; i++) {
					if (givers[i] != this && givers[i].forsakenOwner != null) {
						givers[i].forsakenOwner.orgasmsGiven++;
					}
				}
				w.purpleAppend(t, "\n\nOrgasm!  ");
				int intensity = 200 + (int)(Math.random()*200);
				if (orgasms == 0) {
					if (currentDamage()[5]-actualDamage[5] >= 10000 && currentDamage()[5] >= 30000) {
						w.append(t, "The pent-up climax comes all at once, and " + ownerName() + " squeals incoherently as ");
						if (parts[PENIS] > 0) {
							w.append(t, hisHer() + " cock spurts a huge load.");
						} else {
							w.append(t, heShe() + " cums almost hard enough to make " + himHer() + " pass out.");
						}
					} else {
						intensity = 450*(int)(Math.random()*450);
						w.append(t, OwnerName() + " shudders as ");
						if (parts[PENIS] > 0) {
							w.append(t, hisHer() + " cock shoots its load.");
						} else {
							w.append(t, heShe() + " climaxes.");
						}
					}
				} else {
					intensity = 300 + (int)(Math.random()*300);
					if (orgasms == 1) {
						w.append(t, OwnerName() + " cums again, ");
						if (parts[PENIS] > 0) {
							w.append(t, "shooting a smaller load this time, but it feels even better.");
						} else {
							w.append(t, "moaning and gasping for breath as the second climax proves even more intense than the first.");
						}
					} else {
						w.append(t, OwnerName() + " cums yet again, ");
						if (parts[PENIS] > 0) {
							w.append(t, hisHer() + " exhausted penis managing only a trickle of cum, but even that much is enough to make " + himHer() + " groan with unbearable pleasure.");
						} else {
							w.append(t, "body spasming wildly, " + hisHer() + " eyes wide and staring off into the distance as " + hisHer() + " vision flashes white.");
						}
					}
				}
				orgasms++;
				if (forsakenOwner != null) {
					if (forsakenOwner.timesOrgasmed == 0) {
						specialLine = 2;
						w.append(t, "  It's " + hisHer() + " first time experiencing orgasm.");
					} else if (forsakenOwner.strongestOrgasm < 200) {
						specialLine = 2;
						w.append(t, "  It's by far the strongest orgasm " + heShe() + "'s had in " + hisHer() + " life.");
					}
					forsakenOwner.timesOrgasmed++;
					if (intensity > forsakenOwner.strongestOrgasm) {
						forsakenOwner.strongestOrgasm = intensity;
					}
				}
			}
		}
		if (crossedThreshold(actualDamage[6], currentDamage()[6]) > 0) {
			w.purpleAppend(t, "\n\nTIRE up!  ");
			if (crossedThreshold(actualDamage[6], currentDamage()[6]) == 1) {
				w.append(t, OwnerName() + " is starting to get tired, but it's not slowing " + himHer() + " down yet.");
			} else if (crossedThreshold(actualDamage[6], currentDamage()[6]) == 2) {
				w.append(t, OwnerName() + "'s fatigue is catching up with " + himHer() + ", and " + heShe() + "'s losing focus.");
			} else if (crossedThreshold(actualDamage[6], currentDamage()[6]) == 3) {
				w.append(t, OwnerName() + " is practically passing out.  " + HeShe() + " can't do much like this.");
			} else {
				w.append(t, OwnerName() + " fades in and out of consciousness.");
			}
		}
		if (crossedThreshold(actualDamage[7], currentDamage()[7]) > 0) {
			w.purpleAppend(t, "\n\nEXPO up!  ");
			if (crossedThreshold(actualDamage[7], currentDamage()[7]) == 1) {
				w.append(t, OwnerName() + "'s clothes are partially undone.");
			} else if (crossedThreshold(actualDamage[7], currentDamage()[7]) == 2) {
				w.append(t, OwnerName() + "'s " + breasts() + " can clearly be seen.");
			} else if (crossedThreshold(actualDamage[7], currentDamage()[7]) == 3) {
				w.append(t, OwnerName() + "'s clothes have been shifted out of the way to show " + hisHer() + " most private places.");
			} else if (crossedThreshold(actualDamage[7], currentDamage()[7]) == 4) {
				w.append(t, "Only a few scraps of " + OwnerName() + "'s clothes remain.");
			} else {
				w.append(t, OwnerName() + " is now completely naked.");
			}
			if (w.sceneLocation == Activity.Location.STAGE) {
				if (currentDamage()[7] - actualDamage[7] < 10000 && currentDamage()[7] >= 10000) {
					if (forsakenOwner != null) {
						int added = 300000 + (int)(Math.random()*300000);
						if (forsakenOwner.timesExposed < 300000) {
							specialLine = 4;
							w.append(t, "  Flashing cameras from the crowd immortalize the moment forever.");
						}
						forsakenOwner.timesExposed += added;
						if (getObedience() + getDeviancy() >= 133) {
							forsakenOwner.timesExposedSelf += added;
						}
					}
				}
			}
		}
	}
	
	public int crossedThreshold(long change, long currentValue) {
		int result = 0;
		if (currentValue > 0 && (currentValue == change || (int)(Math.log10(currentValue-change)) < (int)(Math.log10(currentValue)))) {
			result = (int)(Math.log10(currentValue))-1;
			if (result < 0) {
				result = 0;
			}
		}
		return result;
	}
	
	public int orgasmLevel(long change, long currentValue) {
		int previousLevel = 0;
		int currentLevel = 0;
		long previousPLEA = currentValue - change;
		if (previousPLEA >= 100000) {
			previousLevel = (int)(previousPLEA/100000 + 2);
		} else if (previousPLEA >= 30000) {
			previousLevel = 2;
		} else if (previousPLEA >= 10000) {
			previousLevel = 1;
		}
		if (currentValue >= 100000) {
			currentLevel = (int)(currentValue/100000 + 2);
		} else if (currentValue >= 30000) {
			currentLevel = 2;
		} else if (currentValue >= 10000) {
			currentLevel = 1;
		}
		if (currentLevel > previousLevel) {
			return currentLevel;
		} else {
			return 0;
		}
	}
	
	public Forsaken.Gender ownAppearanceGender() {
		if (bodyType == Appearance.CUTEGIRL) {
			return Forsaken.Gender.FEMALE;
		} else {
			return Forsaken.Gender.MALE;
		}
	}
	
	public Forsaken.Gender appearanceGender(Appearance seen) {
		if (seen == Appearance.CUTEGIRL) {
			return Forsaken.Gender.FEMALE;
		} else if (seen == Appearance.CUTEBOY) {
			return Forsaken.Gender.MALE;
		} else {
			return null;
		}
	}
	
	public String heShe() {
		if (appearanceGender(bodyType) == Forsaken.Gender.FEMALE) {
			return "she";
		} else if (appearanceGender(bodyType) == Forsaken.Gender.MALE) {
			return "he";
		} else {
			return "it";
		}
	}
	
	public String hisHer() {
		if (appearanceGender(bodyType) == Forsaken.Gender.FEMALE) {
			return "her";
		} else if (appearanceGender(bodyType) == Forsaken.Gender.MALE) {
			return "his";
		} else {
			return "its";
		}
	}
	
	public String himHer() {
		if (appearanceGender(bodyType) == Forsaken.Gender.FEMALE) {
			return "her";
		} else if (appearanceGender(bodyType) == Forsaken.Gender.MALE) {
			return "him";
		} else {
			return "it";
		}
	}
	
	public String HeShe() {
		if (appearanceGender(bodyType) == Forsaken.Gender.FEMALE) {
			return "She";
		} else if (appearanceGender(bodyType) == Forsaken.Gender.MALE) {
			return "He";
		} else {
			return "It";
		}
	}
	
	public String HisHer() {
		if (appearanceGender(bodyType) == Forsaken.Gender.FEMALE) {
			return "Her";
		} else if (appearanceGender(bodyType) == Forsaken.Gender.MALE) {
			return "His";
		} else {
			return "Its";
		}
	}
	
	public String HimHer() {
		if (appearanceGender(bodyType) == Forsaken.Gender.FEMALE) {
			return "Her";
		} else if (appearanceGender(bodyType) == Forsaken.Gender.MALE) {
			return "Him";
		} else {
			return "It";
		}
	}
	
	public Forsaken.Gender getGender() {
		if (forsakenOwner != null) {
			return forsakenOwner.gender;
		} else if (chosenOwner != null) {
			if (chosenOwner.gender.equals("male")) {
				return Forsaken.Gender.MALE;
			} else if (chosenOwner.gender.equals("female")) {
				return Forsaken.Gender.FEMALE;
			} else {
				return Forsaken.Gender.FUTANARI;
			}
		} else {
			if (appearanceGender(bodyType) == Forsaken.Gender.FEMALE) {
				if (parts[PENIS] > 0) {
					return Forsaken.Gender.FUTANARI;
				} else {
					return Forsaken.Gender.FEMALE;
				}
			} else {
				return Forsaken.Gender.MALE;
			}
		}
	}
	
	public Chosen.Species getType() {
		if (forsakenOwner != null) {
			return forsakenOwner.type;
		} else if (chosenOwner != null) {
			return chosenOwner.type;
		} else {
			return null;
		}
	}
	
	public Boolean isForsaken() {
		if (forsakenOwner != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void addActivity(Activity added, Body partner) {
		Activity[] newInProgress = new Activity[inProgress.length+1];
		Body[] newTargets = new Body[targets.length+1];
		for (int i = 0; i < inProgress.length; i++) {
			newInProgress[i] = inProgress[i];
			newTargets[i] = targets[i];
		}
		newInProgress[newInProgress.length-1] = added;
		newTargets[newTargets.length-1] = partner;
		inProgress = newInProgress;
		targets = newTargets;
	}
	
	public void removeActivity(Activity removed, Body partner) {
		Activity[] prunedList = new Activity[inProgress.length];
		Body[] prunedPartners = new Body[targets.length];
		int index = 0;
		Activity[] counterparts = new Activity[0];
		Body[] counterPartners = new Body[0];
		for (int i = 0; i < prunedList.length; i++) {
			if (inProgress[i] != removed || (partner != targets[i] && partner != null)) {
				prunedList[index] = inProgress[i];
				prunedPartners[index] = targets[i];
				index++;
			} else if (removed.counterpart != null && removed.counterpart.counterpart == removed) {
				Activity[] newCounterparts = new Activity[counterparts.length+1];
				Body[] newCounterPartners = new Body[counterPartners.length+1];
				for (int j = 0; j < counterparts.length; j++) {
					newCounterparts[j] = counterparts[j];
					newCounterPartners[j] = counterPartners[j];
				}
				newCounterparts[newCounterparts.length-1] = inProgress[i].counterpart;
				newCounterPartners[newCounterPartners.length-1] = targets[i];
				counterparts = newCounterparts;
				counterPartners = newCounterPartners;
			}
		}
		Activity[] newInProgress = new Activity[index];
		Body[] newTargets = new Body[index];
		for (int i = 0; i < index; i++) {
			newInProgress[i] = prunedList[i];
			newTargets[i] = prunedPartners[i];
		}
		inProgress = newInProgress;
		targets = newTargets;
		if (removed == Project.PushDown || removed == Project.PullDown) {
			removeActivity(Project.VaginalPenetrate, partner);
			removeActivity(Project.PenetratedVaginally, partner);
			removeActivity(Project.AnalPenetrate, partner);
			removeActivity(Project.PenetratedAnally, partner);
		}
		if (removed == Project.Supine) {
			removeActivity(Project.CockSteppedOn, partner);
			removeActivity(Project.ClitSteppedOn, partner);
		}
		for (int i = 0; i < counterparts.length; i++) {
			if (removed == Project.PushDown && counterparts[i] == Project.PullDown) {
				if (Project.PullDown.isInProgress(counterPartners[i], null)) {
					counterPartners[i].addActivity(Project.Supine, null);
				}
			}
			counterPartners[i].removeActivity(counterparts[i], this);
		}
	}
	
	public void freeBodyPart(int freedPart) {
		for (int i = 0; i < inProgress.length; i++) {
			if (inProgress[i].sendReqs[freedPart] > 0) {
				removeActivity(inProgress[i], null);
			}
		}
	}
	
	public int getMorality() {
		if (forsakenOwner != null) {
			return forsakenOwner.morality;
		} else if (chosenOwner != null) {
			return chosenOwner.morality;
		} else {
			return 50;
		}
	}
	
	public int getInnocence() {
		if (forsakenOwner != null) {
			return forsakenOwner.innocence;
		} else if (chosenOwner != null) {
			return chosenOwner.innocence;
		} else {
			return 50;
		}
	}
	
	public int getConfidence() {
		if (forsakenOwner != null) {
			return forsakenOwner.confidence;
		} else if (chosenOwner != null) {
			return chosenOwner.confidence;
		} else {
			return 50;
		}
	}
	
	public int getDignity() {
		if (forsakenOwner != null) {
			return forsakenOwner.dignity;
		} else if (chosenOwner != null) {
			return chosenOwner.dignity;
		} else {
			return 50;
		}
	}
	
	public int getHostility() {
		if (forsakenOwner != null) {
			return forsakenOwner.flavorHostility();
		} else if (chosenOwner != null) {
			int value = (100-chosenOwner.morality)/3;
			if (chosenOwner.impregnated) {
				value += 40;
			} else if (chosenOwner.timesSlaughtered() > 0) {
				value += 30;
			} else if (chosenOwner.vVirg == false) {
				value += 20;
			} else if (chosenOwner.ruthless) {
				value += 10;
			}
			return value;
		} else {
			return 50;
		}
	}
	
	public int getDeviancy() {
		if (forsakenOwner != null) {
			return forsakenOwner.flavorDeviancy();
		} else if (chosenOwner != null) {
			int value = (100-chosenOwner.innocence)/3;
			if (chosenOwner.hypnotized) {
				value += 40;
			} else if (chosenOwner.timesFantasized() > 0) {
				value += 30;
			} else if (chosenOwner.cVirg == false) {
				value += 20;
			} else if (chosenOwner.lustful) {
				value += 10;
			}
			return value;
		} else {
			return 50;
		}
	}
	
	public int getObedience() {
		if (forsakenOwner != null) {
			return forsakenOwner.flavorObedience();
		} else if (chosenOwner != null) {
			int value = (100-chosenOwner.confidence)/3;
			if (chosenOwner.drained) {
				value += 40;
			} else if (chosenOwner.timesDetonated() > 0) {
				value += 30;
			} else if (chosenOwner.aVirg == false) {
				value += 20;
			} else if (chosenOwner.meek) {
				value += 10;
			}
			value += chosenOwner.negotiations;
			if (chosenOwner.drained == false && chosenOwner.negotiations == 0) {
				value = value/2;
			} else if (chosenOwner.negotiations > 0) {
				value += 10;
			}
			if (value > 100) {
				value = 100;
			}
			return value;
		} else {
			return 50;
		}
	}
	
	public int getDisgrace() {
		if (forsakenOwner != null) {
			return forsakenOwner.disgrace;
		} else if (chosenOwner != null) {
			int value = (100-chosenOwner.dignity)/3;
			if (chosenOwner.parasitized) {
				value += 40;
			} else if (chosenOwner.timesStripped() > 0) {
				value += 30;
			} else if (chosenOwner.modest == false) {
				value += 20;
			} else if (chosenOwner.debased) {
				value += 10;
			}
			return value;
		} else {
			return 50;
		}
	}
	
	public int getFEARLevel() {
		int value = 0;
		if (currentFEAR > 0) {
			value = (int)(Math.log10(currentFEAR)) - 1;
		}
		if (value > 0) {
			return value;
		} else {
			return 0;
		}
	}
	
	public int getDISGLevel() {
		int value = 0;
		if (currentDISG > 0) {
			value = (int)(Math.log10(currentDISG)) - 1;
		}
		if (value > 0) {
			return value;
		} else {
			return 0;
		}
	}
	
	public int getPAINLevel() {
		int value = 0;
		if (currentPAIN > 0) {
			value = (int)(Math.log10(currentPAIN)) - 1;
		}
		if (value > 0) {
			return value;
		} else {
			return 0;
		}
	}
	
	public int getSHAMLevel() {
		int value = 0;
		if (currentSHAM > 0) {
			value = (int)(Math.log10(currentSHAM)) - 1;
		}
		if (value > 0) {
			return value;
		} else {
			return 0;
		}
	}
	
	public int getHATELevel() {
		int value = 0;
		if (currentHATE > 0) {
			value = (int)(Math.log10(currentHATE)) - 1;
		}
		if (value > 0) {
			return value;
		} else {
			return 0;
		}
	}
	
	public int getPLEALevel() {
		int value = 0;
		if (currentPLEA > 0) {
			value = (int)(Math.log10(currentPLEA)) - 1;
		}
		if (value > 0) {
			return value;
		} else {
			return 0;
		}
	}
	
	public int getINJULevel() {
		int value = 0;
		if (currentINJU > 0) {
			value = (int)(Math.log10(currentINJU)) - 1;
		}
		if (value > 0) {
			return value;
		} else {
			return 0;
		}
	}
	
	public int getEXPOLevel() {
		int value = 0;
		if (currentEXPO > 0) {
			value = (int)(Math.log10(currentEXPO)) - 1;
		}
		if (value > 0) {
			return value;
		} else {
			return 0;
		}
	}
	
	public Boolean isParasitized() {
		if (forsakenOwner != null) {
			return forsakenOwner.parasitized;
		} else if (chosenOwner != null) {
			return chosenOwner.parasitized;
		} else {
			return false;
		}
	}
	
	public Boolean isDrained() {
		if (forsakenOwner != null) {
			return forsakenOwner.drained;
		} else if (chosenOwner != null) {
			return chosenOwner.drained;
		} else {
			return false;
		}
	}
	
	public Boolean hasBeenBroadcasted() {
		if (forsakenOwner != null) {
			return (forsakenOwner.timesExposed >= 300000);
		} else if (chosenOwner != null) {
			return (chosenOwner.modest == false);
		} else {
			return false;
		}
	}
	
	public Boolean isVVirg() {
		if (forsakenOwner != null) {
			if (forsakenOwner.timesHadSex > 0) {
				return false;
			} else {
				return true;
			}
		} else if (chosenOwner != null) {
			return chosenOwner.vVirg;
		} else {
			return false;
		}
	}
	
	public Boolean isDemonLord() {
		return (chosenOwner == null && forsakenOwner == null);
	}
	
	public String ownerName() {
		if (forsakenOwner != null) {
			return forsakenOwner.mainName;
		} else if (chosenOwner != null) {
			if (transformed) {
				return chosenOwner.mainName;
			} else {
				return chosenOwner.givenName;
			}
		} else {
			return "the Demon Lord";
		}
	}
	
	public Boolean civilianPortrait() {
		return (forsakenOwner != null || transformed == false);
	}
	
	public String portraitName() {
		if (forsakenOwner != null) {
			return forsakenOwner.mainName;
		} else if (chosenOwner != null) {
			return chosenOwner.mainName;
		} else {
			return "the Demon Lord";
		}
	}
	
	public String OwnerName() {
		return capitalizedOwnerName();
	}
	
	public String capitalizedOwnerName() {
		if (forsakenOwner != null || chosenOwner != null) {
			return ownerName();
		} else {
			return "The Demon Lord";
		}
	}
	
	public String breasts() {
		if (parts[CLEAVAGE] > 0) {
			return "breasts";
		} else {
			return "chest";
		}
	}
	
	public void say (JTextPane t, String s) {
		if (forsakenOwner != null) {
			forsakenOwner.say(t, s);
		} else if (chosenOwner != null) {
			chosenOwner.say(t, s);
		}
	}
	
	public int obedienceMod(WorldState w, Body subject) {
		int result = 0;
		if (forsakenOwner != null) {
			result += forsakenOwner.consentModifier();
			if (forsakenOwner.defeatType == 5 && forsakenOwner.obedience < 40) {
				result += 50;
			} else if (forsakenOwner.defeatType == 6) {
				result += 40;
			}
		} else if (chosenOwner != null) {
			int obedience = getObedience();
			if (obedience < 10) {
				result = -50 + (5*obedience)/2;
			} else if (obedience < 20) {
				result = -45 + 2*obedience;
			} else if (obedience < 30) {
				result = -35 + (3*obedience)/2;
			} else if (obedience < 40) {
				result = -20 + obedience;
			} else if (obedience < 50) {
				result = obedience/2;
			} else if (obedience < 60) {
				result = obedience/2;
			} else if (obedience < 70) {
				result = -30 + obedience;
			} else if (obedience < 80) {
				result = -65 + (3*obedience)/2;
			} else if (obedience < 90) {
				result = -105 + 2*obedience;
			} else {
				result = -150 + (5*obedience)/2;
			}
		}
		return result;
	}
	
	public String theDemonLord() {
		if (forsakenOwner != null) {
			return forsakenOwner.theDemonLord();
		} else {
			return "the Demon Lord";
		}
	}
	
	public String TheDemonLord() {
		if (forsakenOwner != null) {
			return forsakenOwner.TheDemonLord();
		} else {
			return "The Demon Lord";
		}
	}
	
	public String demonLord() {
		if (forsakenOwner != null) {
			return forsakenOwner.demonLord;
		} else {
			return "Demon Lord";
		}
	}
	
	public int friendsMod(WorldState w, Body subject) {
		int result = 0;
		if (forsakenOwner != null) {
			for (int i = 0; i < forsakenOwner.others.length; i++) {
				if (forsakenOwner.forsakenRelations[i] == Forsaken.Relationship.PARTNER) {
					if (forsakenOwner.opinion(forsakenOwner.others[i]) > -100) {
						Boolean found = false;
						int opinionClamp = forsakenOwner.opinion(forsakenOwner.others[i]) + 100;
						if (opinionClamp > 500) {
							opinionClamp = 500;
						}
						for (int j = 0; j < w.getHarem().length; j++) {
							if (forsakenOwner.others[i].equals(w.getHarem()[j])) {
								found = true;
								if (forsakenOwner.others[i].obedience > forsakenOwner.obedience) {
									opinionClamp = opinionClamp*(20 + forsakenOwner.obedience/2 - forsakenOwner.others[i].obedience/2)/500;
								} else {
									opinionClamp = opinionClamp*20/500;
								}
								if (opinionClamp < 0) {
									if (forsakenOwner.obedience < 80) {
										opinionClamp = opinionClamp*(80-forsakenOwner.obedience)/80;
									} else {
										opinionClamp = 0;
									}
								}
							}
							if (found == false) {
								opinionClamp = -30*opinionClamp/500;
								if (forsakenOwner.obedience > 80) {
									opinionClamp = 0;
								} else {
									opinionClamp = opinionClamp*(80 - forsakenOwner.obedience)/80;
								}
							}
						}
						result += opinionClamp;
					}
				}
			}
		}
		return result;
	}
	
	public String mainOrgan() {
		if (parts[PENIS] > 0) {
			return "cock";
		} else if (parts[PUSSY] > 0) {
			return "pussy";
		} else {
			return "crotch";
		}
	}
	
	public Body(Chosen c) {
		chosenOwner = c;
		parts[MOUTH] = 1;
		parts[HAND] = 2;
		parts[ASS] = 1;
		parts[FOOT] = 2;
		parts[ARMPIT] = 2;
		parts[KNEEPIT] = 2;
		parts[HAIR] = 1;
		parts[HIPS] = 1;
		parts[BACK] = 1;
		if (c.gender.equals("male")) {
			bodyType = Appearance.CUTEBOY;
			parts[PENIS] = 1;
			parts[BALLS] = 1;
		} else if (c.gender.equals("female")) {
			bodyType = Appearance.CUTEGIRL;
			parts[PUSSY] = 1;
			parts[CLIT] = 1;
			parts[CLEAVAGE] = 1;
		} else if (c.gender.equals("futanari")) {
			bodyType = Appearance.CUTEGIRL;
			parts[PUSSY] = 1;
			parts[PENIS] = 1;
			parts[CLEAVAGE] = 1;
		}
	}
	
	public Body(Forsaken x) {
		forsakenOwner = x;
		parts[MOUTH] = 1;
		parts[HAND] = 2;
		parts[ASS] = 1;
		parts[FOOT] = 2;
		parts[ARMPIT] = 2;
		parts[KNEEPIT] = 2;
		parts[HAIR] = 1;
		parts[HIPS] = 1;
		parts[BACK] = 1;
		if (x.gender == Forsaken.Gender.MALE) {
			bodyType = Appearance.CUTEBOY;
			parts[PENIS] = 1;
			parts[BALLS] = 1;
		} else if (x.gender == Forsaken.Gender.FEMALE) {
			bodyType = Appearance.CUTEGIRL;
			parts[PUSSY] = 1;
			parts[CLIT] = 1;
			parts[CLEAVAGE] = 1;
		} else if (x.gender == Forsaken.Gender.FUTANARI) {
			bodyType = Appearance.CUTEGIRL;
			parts[PUSSY] = 1;
			parts[PENIS] = 1;
			parts[CLEAVAGE] = 1;
		}
	}
	
	public Body(WorldState w) {
		
	}

	public Body() {
		// TODO Auto-generated constructor stub
	}

}
