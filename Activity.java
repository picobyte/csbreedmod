import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Activity {
	
	public int[] sendReqs = new int[14];
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
	
	public Boolean shares = false;
	public Boolean nonBlocking = false;
	public Boolean targeted = false;
	public Activity counterpart;
	public Activity[] enders = new Activity[0];
	public Boolean endsSelf = false;
	public Boolean pickable = true;
	public Boolean hostile = false;
	
	public Boolean causesOrgasm = false;
	
	public static enum Location {
		CHAMBER,
		STAGE,
		ALLEY,
		ROOM
	}
	
	public String activityName(Body partner) {
		if (this == Project.TweakClit) {
			return "Stroke " + partner.ownerName() + "'s Clit";
		} else if (this == Project.ClitTweaked) {
			return "Clit Stroked by " + partner.ownerName();
		} else if (this == Project.SpreadLegs) {
			return "Spread Legs";
		} else if (this == Project.Praise) {
			return "Praise " + partner.ownerName();
		} else if (this == Project.Insult) {
			return "Insult " + partner.ownerName();
		} else if (this == Project.PushDown) {
			return "Atop " + partner.ownerName();
		} else if (this == Project.PullDown) {
			return "Beneath " + partner.ownerName();
		} else if (this == Project.Escape) {
			return "Escape " + partner.ownerName();
		} else if (this == Project.StopActing) {
			return "Stop Activities";
		} else if (this == Project.TieUp) {
			return "Tie Up " + partner.ownerName();
		} else if (this == Project.BeTied) {
			return "Tied Up By " + partner.ownerName();
		} else if (this == Project.StrokeCock) {
			return "Stroke " + partner.ownerName() + "'s Cock";
		} else if (this == Project.CockStroked) {
			return "Cock Stroked by " + partner.ownerName();
		} else if (this == Project.Lubricate) {
			return "Lubricate " + partner.ownerName() + "'s Anus";
		} else if (this == Project.BeLubricated) {
			return "Anus Lubricated";
		} else if (this == Project.VaginalPenetrate) {
			return "Fuck " + partner.ownerName() + "'s Pussy";
		} else if (this == Project.PenetratedVaginally) {
			return "Pussy Fucked By " + partner.ownerName();
		} else if (this == Project.AnalPenetrate) {
			return "Fuck " + partner.ownerName() + "'s Ass";
		} else if (this == Project.PenetratedAnally) {
			return "Ass Fucked By " + partner.ownerName();
		} else if (this == Project.StripOther) {
			return "Strip " + partner.ownerName();
		} else if (this == Project.Stripped) {
			return "Stripped by " + partner.ownerName();
		} else if (this == Project.LickCock) {
			return "Lick " + partner.ownerName() + "'s Cock";
		} else if (this == Project.CockLicked) {
			return "Cock Licked By " + partner.ownerName();
		} else if (this == Project.LickPussy) {
			return "Lick " + partner.ownerName() + "'s Pussy";
		} else if (this == Project.PussyLicked) {
			return "Pussy Licked By " + partner.ownerName();
		} else if (this == Project.Supine) {
			return "Lying on Back";
		} else if (this == Project.PullUp) {
			return "Pull " + partner.ownerName() + " Up";
		} else if (this == Project.StepOnCock) {
			return "Step On " + partner.ownerName() + "'s Cock";
		} else if (this == Project.CockSteppedOn) {
			return "Cock Stepped On By " + partner.ownerName();
		} else if (this == Project.StepOnClit) {
			return "Step On " + partner.ownerName() + "'s Clit";
		} else if (this == Project.ClitSteppedOn) {
			return "Clit Stepped On By " + partner.ownerName();
		} else if (this == Project.DirtyTalk) {
			return "Talk Dirty To " + partner.ownerName();
		} else {
			return "";
		}
	}
	
	public long[] damageContribution(WorldState w, Body subject, long[] input) {
		//think in terms of "an average Forsaken," corruption ~45 to 50
		//versus "an average person," corruption ~15 to 20
		if (this == Project.ClitTweaked) {
			input[1] += 5;
			input[3] += 5;
			input[5] += 100;
			input[6] += 2;
		} else if (this == Project.TweakClit) {
			input[1] += 10;
			input[3] += 10;
			input[4] += 2;
			input[5] += 1;
			input[6] += 4;
		} else if (this == Project.Praise) {
			input[1] += 2;
			input[3] += 3;
			input[6] += 1;
		} else if (this == Project.Insult) {
			input[0] += 2;
			input[4] += 4;
			input[6] += 1;
		} else if (this == Project.PushDown) {
			input[5] += 5;
			input[6] += 10;
		} else if (this == Project.PullDown) {
			input[0] += 2;
			input[2] += 2;
			input[3] += 1;
			input[4] += 3;
			input[5] += 10;
			input[6] += 3;
		} else if (this == Project.Escape) {
			input[0] += 2;
			input[3] += 1;
			input[6] += 10;
		} else if (this == Project.BeTied) {
			input[0] += 20;
			input[1] += 2;
			input[2] += 10;
			input[3] += 5;
			input[4] += 50;
			input[6] += 3;
		} else if (this == Project.CockStroked) {
			input[1] += 5;
			input[3] += 5;
			input[5] += 140;
			input[6] += 2;
		} else if (this == Project.StrokeCock) {
			input[1] += 10;
			input[3] += 10;
			input[4] += 2;
			input[5] += 1;
			input[6] += 4;
		} else if (this == Project.Lubricate) {
			input[1] += 20;
			input[3] += 20;
			input[6] += 4;
		} else if (this == Project.BeLubricated) {
			input[1] += 5;
			input[3] += 10;
			input[4] += 5;
		} else if (this == Project.VaginalPenetrate) {
			input[1] += 2;
			input[3] += 2;
			input[5] += 300;
			input[6] += 6;
		} else if (this == Project.PenetratedVaginally) {
			input[1] += 4;
			input[2] += 3;
			input[3] += 3;
			input[4] += 5;
			if (subject.getPLEALevel() == 0) {
				input[2] += 20;
				input[4] += 10;
			}
			if (subject.isVVirg()) {
				input[2] += 200;
				input[4] += 30;
			}
			input[5] += 200;
			input[6] += 5;
		} else if (this == Project.AnalPenetrate) {
			input[1] += 3;
			input[3] += 2;
			input[5] += 350;
			input[6] += 6;
		} else if (this == Project.PenetratedAnally) {
			input[1] += 5;
			input[2] += 8;
			input[3] += 10;
			input[4] += 10;
			if (subject.parts[PUSSY] == 0 && subject.isVVirg()) {
				input[4] += 30;
			}
			if (Project.BeLubricated.isInProgress(subject, null) == false) {
				input[4] += 20;
				input[2] += 200;
			}
			input[5] += 100;
			input[6] += 6;
		} else if (this == Project.Stripped) {
			input[0] += 3;
			if (subject.forsakenOwner != null && subject.forsakenOwner.timesExposed < 300000) {
				input[0] += subject.getDignity()/6;
			}
			input[1] += 2;
			input[3] += 20;
			if (w.sceneLocation == Location.STAGE) {
				input[4] += 10;
			} else {
				input[4] += 2;
			}
			input[5] += 5;
			input[6] += 1;
			input[7] += 100;
		} else if (this == Project.LickCock) {
			input[1] += 20;
			input[3] += 20;
			input[4] += 3;
			input[5] += 2;
			input[6] += 5;
		} else if (this == Project.CockLicked) {
			input[1] += 6;
			input[3] += 3;
			input[5] += 200;
			input[6] += 2;
		} else if (this == Project.LickPussy) {
			input[1] += 20;
			input[3] += 20;
			input[4] += 3;
			input[5] += 2;
			input[6] += 5;
		} else if (this == Project.PussyLicked) {
			input[1] += 8;
			input[3] += 4;
			input[5] += 140;
			input[6] += 2;
		} else if (this == Project.Supine) {
			input[6]--;
		} else if (this == Project.StepOnCock) {
			input[1] += 5;
			input[3] += 2;
			input[5] += 2;
			input[6] += 4;
		} else if (this == Project.CockSteppedOn) {
			input[0] += 5;
			input[1] += 8;
			input[2] += 10;
			input[3] += 15;
			input[4] += 10;
			input[5] += 100;
			input[6] += 4;
		} else if (this == Project.StepOnClit) {
			input[1] += 5;
			input[3] += 2;
			input[5] += 2;
			input[6] += 4;
		} else if (this == Project.ClitSteppedOn) {
			input[0] += 4;
			input[1] += 8;
			input[2] += 6;
			input[3] += 15;
			input[4] += 10;
			input[5] += 50;
			input[6] += 3;
		} else if (this == Project.DirtyTalk) {
			input[1] += 8;
			input[3] += 2;
			input[4] += 2;
			input[5] += 5;
			input[6] += 1;
		}
		return input;
	}
	
	public void activityTalk(JTextPane t, WorldState w, Body sender, Body receiver) {
		String[] shownNames = new String[]{sender.portraitName(), null, null, null, null};
		if (Project.PenetratedVaginally.isInProgress(sender, receiver)) {
			if ((sender.forsakenOwner != null && sender.forsakenOwner.timesHadSex == 0) || (sender.chosenOwner != null && sender.chosenOwner.vVirg)) {
				sender.specialLine = 1;
			}
		}
		if (Project.PenetratedAnally.isInProgress(sender, receiver)) {
			if ((sender.forsakenOwner != null && (sender.forsakenOwner.gender == Forsaken.Gender.MALE && sender.forsakenOwner.timesHadSex == 0)) || (sender.chosenOwner != null && sender.chosenOwner.gender.equals("male") && sender.chosenOwner.vVirg)) {
				sender.specialLine = 1;
			} else if (Project.BeLubricated.isInProgress(sender, null) == false && (sender.forsakenOwner != null && sender.forsakenOwner.timesTortured == 0)) {
				sender.specialLine = 3;
			}
			if (sender.forsakenOwner != null) {
				if (sender.forsakenOwner.gender == Forsaken.Gender.MALE) {
					sender.forsakenOwner.timesHadSex++;
					if (Project.BeLubricated.isInProgress(sender, null) == false) {
						sender.forsakenOwner.timesTortured++;
					}
				} else if (Project.BeLubricated.isInProgress(sender, null)) {
					sender.forsakenOwner.enjoyedAnal++;
				} else {
					sender.forsakenOwner.timesTortured++;
				}
			}
		}
		if (true /*sender.length == 1*/) {
			sender.say(t, "\"");
			if (sender.specialLine == 1) {
				if (sender.getGender() == Forsaken.Gender.MALE) {
					if (Project.BeLubricated.isInProgress(sender, null)) {
						if (sender.getObedience() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.JOY);
							if (sender.getMorality() > 66) {
								sender.say(t, "With this...  Even though I'm a boy, I feel like I'm... your wife...  Is that okay?");
							} else if (sender.getMorality() > 33) {
								sender.say(t, "I'm so happy that I was able to save myself for you!");
							} else {
								sender.say(t, "I always felt like sex was just about feeling good, but... I managed to give you my first time.  That makes me really happy.");
							}
						} else if (sender.getObedience() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.SHAME);
							if (sender.getInnocence() > 66) {
								sender.say(t, "It kinda hurts...");
							} else if (sender.getInnocence() > 33) {
								sender.say(t, "I'm surprised you took so long to do this...");
							} else {
								sender.say(t, "I suppose... I had already come to accept that you'd eventually use me back there as well...");
							}
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.ANGER);
							if (sender.getInnocence() > 66) {
								sender.say(t, "I'll never forgive you for this!  Never!  Never!");
							} else if (sender.getInnocence() > 33) {
								sender.say(t, "Even if you defile my body, I'll never give you my heart!");
							} else {
								sender.say(t, "All that effort to keep the Thralls from raping me, only to do it yourself?  How... ngh... pointless...");
							}
						}
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.FEAR);
						if (sender.getConfidence() > 66) {
							if (sender.getObedience() > 66) {
								sender.say(t, "I'm... guh... f-fine, " + sender.demonLord() + "!");
							} else if (sender.getObedience() > 33) {
								sender.say(t, "Why are you doing this!?  I'm serving you now!  Gaaah, pleeease!");
							} else {
								sender.say(t, "I'll stop fighting, I'll stop talking back, I'll do anything!");
							}
						} else if (sender.getConfidence() > 33) {
							if (sender.getDignity() > 66) {
								sender.say(t, "Please, please!  Stooop!");
							} else if (sender.getDignity() > 33) {
								sender.say(t, "Mgh...  guh... I-I can't...");
							} else {
								sender.say(t, "AAAGH, NOOO!");
							}
						} else {
							if (sender.getDeviancy() > 66) {
								sender.say(t, "Nnnaaah~!");
							} else if (sender.getDeviancy() > 33) {
								sender.say(t, "Aah, nnooo, oooh!");
							} else {
								sender.say(t, "Agh!  Mph, guh, n-no...!");
							}
						}
					}
				} else {
					if (sender.getObedience() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.JOY);
						if (sender.getMorality() > 66) {
							sender.say(t, "With this...  It's like I'm your wife now, isn't it...?  Ah, wonderful...");
						} else if (sender.getMorality() > 33) {
							sender.say(t, "I'm so happy that I was able to save myself for you!");
						} else {
							sender.say(t, "I never cared much about who my first would be... but now, I'm really glad that it's you...");
						}
					} else if (sender.getObedience() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.SHAME);
						if (sender.getInnocence() > 66) {
							sender.say(t, "It kinda hurts...");
						} else if (sender.getInnocence() > 33) {
							sender.say(t, "I'm surprised you took so long to do this...");
						} else {
							sender.say(t, "I suppose... I had already come to accept that you'd be my first...");
						}
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.ANGER);
						if (sender.getInnocence() > 66) {
							sender.say(t, "I'll never forgive you for this!  Never!  Never!");
						} else if (sender.getInnocence() > 33) {
							sender.say(t, "Even if you defile my body, I'll never give you my heart!");
						} else {
							sender.say(t, "All that effort to preserve my virginity, only to take it now?  How... ngh... pointless...");
						}
					}
				}
				if (sender.forsakenOwner != null) {
					sender.forsakenOwner.timesHadSex++;
				} else if (sender.chosenOwner != null) {
					sender.chosenOwner.vVirg = false;
				}
			} else if (sender.specialLine == 2) {
				Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.LEWD);
				if (sender.getInnocence() > 66) {
					if (sender.getObedience() > 66) {
						sender.say(t, "That was... amazing!  It's like all my love for " + sender.theDemonLord() + "... came out at once...!");
					} else if (sender.getObedience() > 33) {
						sender.say(t, "Aaah, what's happening...!?  I feel good!  I feel too good!  I'm going crazy...!");
					} else {
						sender.say(t, "Whuh?  What was... that feeling...?  What did you do to me...?");
					}
				} else if (sender.getInnocence() > 33) {
					if (sender.getObedience() > 66) {
						sender.say(t, "When " + sender.theDemonLord() + " makes me cum... it feels so much better than when I do it myself...!");
					} else if (sender.getObedience() > 33) {
						sender.say(t, "Wow!  That was... intense!");
					} else {
						sender.say(t, "Ugh, you made me cum... even though I didn't want to...  Not with you...");
					}
				} else {
					if (sender.getObedience() > 66) {
						sender.say(t, "Such an... overwhelmingly intense orgasm!  Ah, " + sender.demonLord() + ", my body has come to desire your touch above all else!");
					} else if (sender.getObedience() > 33) {
						sender.say(t, "Your... nn... technique... certainly does not disappoint...");
					} else {
						sender.say(t, "Nnngh...  Pathetic... to feel such pleasure from the Demon Lord...");
					}
				}
			} else if (sender.specialLine == 3) {
				Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.STRUGGLE);
				if (sender.getObedience() > 66) {
					if (sender.getDeviancy() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.LEWD);
						sender.say(t, "AAAGH!  YES!  YES!  PUNISH ME HARDER!");
					} else if (sender.getDeviancy() > 33) {
						sender.say(t, "Too much!  It's too much!  Aaagh!");
					} else {
						sender.say(t, "NNNOOOGH!  S-Stop, please!  I'm serving you, so why...!?");
					}
				} else if (sender.getObedience() > 33) {
					if (sender.getConfidence() > 66) {
						sender.say(t, "I'M SORRY!  I'M SORRY FOR DEFYING YOU!  AAARGH!");
					} else if (sender.getConfidence() > 33) {
						sender.say(t, "I'm breaking!  You're breaking me!");
					} else {
						sender.say(t, "P-Please...!  No more!  NoooOOOGH!");
					}
				} else {
					if (sender.getConfidence() > 66) {
						sender.say(t, "NO! NOOO!  AAAGH!");
					} else if (sender.getConfidence() > 33) {
						sender.say(t, "You win!  You win!  You don't have to- GRAAAH!");
					} else {
						sender.say(t, "I-I give up!  I'm sorry, I'm sorryyy!");
					}
				}
			} else if (sender.specialLine == 4) {
				if (sender.getObedience() > 66) {
					Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.LEWD);
					if (sender.getDignity() > 66) {
						sender.say(t, "Such an... incredible feeling of shame...!  Ngh...!  Thank you, " + sender.demonLord() + ", thank you for letting me feel this...!");
					} else if (sender.getDignity() > 33) {
						sender.say(t, "Aaah, yes!  Now everyone will know that I belong to " + sender.theDemonLord() + "!");
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.FOCUS);
						sender.say(t, "Is there anything else you want me to show them, " + sender.demonLord() + "?");
					}
				} else if (sender.getObedience() > 33) {
					Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.SHAME);
					if (sender.getDignity() > 66) {
						sender.say(t, "You've taken... everything... from me...  Ngh...");
					} else if (sender.getDignity() > 33) {
						sender.say(t, "Maybe... if I look like I'm enjoying it... they won't think I'm weak...");
					} else {
						sender.say(t, "I never cared how much they saw, so... it's fine.");
					}
				} else {
					Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.ANGER);
					if (sender.getDignity() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.FEAR);
						sender.say(t, "No!  No!  Let me cover myself!");
					} else if (sender.getDignity() > 33) {
						sender.say(t, "Even if you humiliate me...  I won't give up!");
					} else {
						sender.say(t, "They can laugh all they like!  I'll still beat you in the end!");
					}
				}
			} else if (sender.getINJULevel() > 3) {
				Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SWOON, Project.Emotion.SWOON);
				sender.say(t, "...");
			} else if (this == Project.TweakClit) {
				if (receiver == w.lordBody) {
					if (sender.getObedience() > 66) {
						if (sender.getInnocence() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
							sender.say(t, "I'm so happy I get to make " + sender.theDemonLord() + " feel good!");
						} else if (sender.getInnocence() > 33) {
							sender.say(t, "Does this feel good, " + sender.demonLord() + "?  I just want to make you happy...");
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
						} else {
							sender.say(t, "I need to impress " + sender.theDemonLord() + " with my technique...!");
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.NEUTRAL);
						}
					} else if (sender.getObedience() > 33) {
						if (sender.currentHATE > 1000) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
							if (sender.imprisoned) {
								sender.say(t, "Ugh, making me touch you like this...!");
							} else {
								sender.say(t, "I'll touch you here, but that's it!");
							}
						} else if (sender.currentHATE > 100) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
							sender.say(t, "Why do you need me to do this...?");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FOCUS);
							sender.say(t, "Is this alright?");
						}
					} else {
						if (sender.getDeviancy() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
							sender.say(t, "Ngh...  You knew... I wouldn't be able to resist this beautiful body you made...");
						} else if (sender.getDeviancy() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.ANGER);
							sender.say(t, "Fine.  I'll make you feel good.  But you'd better return the favor...");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
							sender.say(t, "Disgusting... I don't want to touch the Demon Lord...");
						}
					}
				}
			} else if (this == Project.Praise) {
				if (w.sceneDuration % 3 == 0) {
					if (sender.getObedience() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
						if (sender.getInnocence() > 66) {
							sender.say(t, "I'm so happy that I get to spend time with " + sender.theDemonLord() + "!");
						} else if (sender.getInnocence() > 33) {
							sender.say(t, "Use me up until nothing is left, " + sender.demonLord() + ".");
						} else {
							sender.say(t, "I am unworthy to serve " + sender.theDemonLord() + "... but I shall try my best!");
						}
					} else if (sender.getObedience() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.JOY);
						if (sender.getInnocence() > 66) {
							sender.say(t, "This is weird, but... kinda fun, too.");
						} else if (sender.getInnocence() > 33) {
							sender.say(t, "You're amazing, " + sender.demonLord() + ".");
						} else {
							sender.say(t, "Let me prove my value to you.");
						}
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
						if (sender.getInnocence() > 66) {
							sender.say(t, sender.TheDemonLord() + " isn't so bad...  Wait, what am I saying!?");
						} else if (sender.getInnocence() > 33) {
							if (sender.imprisoned) {
								sender.say(t, "Ugh, I'm completely under your control...");
							} else {
								sender.say(t, "Ugh, you've got me completely wrapped around your finger...");
							}
						} else {
							sender.say(t, "I'm... aware that I stand no chance of defeating you, " + sender.demonLord() + ".");
						}
					}
				} else if (w.sceneDuration % 3 == 1) {
					if (sender.getObedience() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
						if (sender.getDeviancy() > 66) {
							sender.say(t, "I-Increidble...!  Being with " + sender.theDemonLord() + " feels even better...!");
						} else if (sender.getDeviancy() > 33) {
							sender.say(t, "I love you, " + sender.demonLord() + "!");
						} else {
							sender.say(t, "You're remaking me... into your own personal toy...!");
						}
					} else if (sender.getObedience() > 33) {
						if (sender.getDeviancy() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
							sender.say(t, "I want you to make me feel even better, " + sender.demonLord() + "!");
						} else if (sender.getDeviancy() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.JOY);
							sender.say(t, "My body is all yours, " + sender.demonLord() + ".");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
							sender.say(t, "Your touch is... unforgettable, " + sender.demonLord() + "...");
						}
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
						if (sender.getDeviancy() > 66) {
							sender.say(t, "Ugh... I... I can't resist you, " + sender.demonLord() + "...");
						} else if (sender.getDeviancy() > 33) {
							sender.say(t, "You're almost making me forget that you're the Demon Lord.");
						} else {
							sender.say(t, "If you can even make someone like me feel like this...");
						}
					}
				} else if (w.sceneDuration % 3 == 2) {
					if (sender.getObedience() > 66) {
						if (sender.getConfidence() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
							sender.say(t, "I want to make you forget about all your other servants...");
						} else if (sender.getConfidence() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
							sender.say(t, "You're just perfect, " + sender.demonLord() + "!  I don't know why I ever fought you...");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
							sender.say(t, "I know it's wrong to want you to care about me... but...");
						}
					} else if (sender.getObedience() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
						if (sender.getConfidence() > 66) {
							sender.say(t, "I've never feared anyone but you...");
						} else if (sender.getConfidence() > 33) {
							if (sender.imprisoned) {
								sender.say(t, "I'll never oppose you again.  I understand that now.");
							} else {
								sender.say(t, "I can't oppose you without being punished.  I understand that now.");
							}
						} else {
							if (sender.imprisoned) {
								sender.say(t, "I can't even think about fighting you anymore...");
							} else {
								sender.say(t, "I-It's hard to even think about fighting you right now...");
							}
						}
					} else {
						if (sender.getConfidence() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.NEUTRAL);
							sender.say(t, "You're... a worthy opponent.  I wouldn't even feel bad about losing to you.  Not that I'm giving up!");
						} else if (sender.getConfidence() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.ANGER);
							if (sender.imprisoned) {
								sender.say(t, "I... never stood a chance against you.  Ugh, I hate saying that...");
							} else {
								sender.say(t, "I... probably don't stand a chance against you.  Ugh, I hate saying that...");
							}
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
							sender.say(t, "I, um...  I don't actually think I'm going to be able to resist you for that long.  I just don't want to give up right away...");
						}
					}
				}
			} else if (this == Project.Insult) {
				if (w.sceneDuration % 3 == 0) {
					if (sender.getObedience() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.SHAME);
						if (sender.getINJULevel() > 3) {
							sender.say(t, "P-Please... " + sender.demonLord() + "...  I'm passing out...");
						} else if (sender.getINJULevel() == 2) {
							sender.say(t, "I can't keep up with you, " + sender.demonLord() + "...");
						} else {
							sender.say(t, "Are you trying to punish me, " + sender.demonLord() + "?");
						}
					} else if (sender.getObedience() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
						if (sender.getINJULevel() > 3) {
							sender.say(t, "Let... Let me go...!");
						} else if (sender.getINJULevel() == 2) {
							sender.say(t, "Can't you tell I'm getting tired?  Let's just stop here.");
						} else {
							sender.say(t, "I can't take this anymore...!");
						}
					} else {
						if (sender.getINJULevel() > 3) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.ANGER);
							if (sender.imprisoned) {
								sender.say(t, "Heh...  I'll keep resisting... until I pass out...");
							} else {
								sender.say(t, "Heh...  I've still got plenty of fight in me...");
							}
						} else if (sender.getINJULevel() == 2) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
							sender.say(t, "I'm not going to do what you want, so just go away!");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
							if (sender.forsakenOwner != null) {
								if (sender.getMorality() > 66) {
									sender.say(t, "I'll never stop resisting you!");
								} else if (sender.getMorality() > 33) {
									sender.say(t, "This is pointless, just stop!");
								} else {
									sender.say(t, "I'll get my revenge on you!");
								}
							} else {
								if (sender.getMorality() > 66) {
									sender.say(t, "You'd better have a good reason for this!");
								} else if (sender.getMorality() > 33) {
									sender.say(t, "Stop this!");
								} else {
									sender.say(t, "You're already getting on my nerves.");
								}
							}
						}
					}
				} else if (w.sceneDuration % 3 == 1) {
					if (sender.getObedience() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
						if (sender.getHATELevel() > 1) {
							sender.say(t, "I don't want to be angry with " + sender.theDemonLord() + ", but...!");
						} else if (sender.getHATELevel() == 1) {
							sender.say(t, "Just... Just give me a moment to calm down...!");
						} else {
							sender.say(t, "I'm sorry, " + sender.demonLord() + "...");
						}
					} else if (sender.getObedience() > 33) {
						if (sender.getHATELevel() > 1) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
							sender.say(t, "I should have known better than to trust you...!");
						} else if (sender.getHATELevel() == 1) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
							sender.say(t, "I'm not happy with you right now, " + sender.demonLord() + ".");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.STRUGGLE);
							sender.say(t, "Can't we do this another time?");
						}
					} else {
						if (sender.getHATELevel() > 1) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
							sender.say(t, "I swear I'll kill you, Demon Lord!");
						} else if (sender.getHATELevel() == 1) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
							sender.say(t, "Are you just trying to provoke me!?");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.ANGER);
							if (sender.forsakenOwner != null) {
								if (sender.getConfidence() > 66) {
									sender.say(t, "Well!?  Aren't you going to torture me!?");
								} else if (sender.getConfidence() > 33) {
									sender.say(t, "I still don't want to serve you.");
								} else {
									sender.say(t, "J-Just leave me alone...");
								}
							} else {
								if (sender.getConfidence() > 66) {
									sender.say(t, "You're wasting my time!");
								} else if (sender.getConfidence() > 33) {
									sender.say(t, "If you have something to say, make it quick!");
								} else {
									sender.say(t, "Why am I even listening to you...?");
								}
							}
						}
					}
				} else {
					if (sender.getObedience() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
						if (sender.getDeviancy() > 66) {
							sender.say(t, "I'll just... dream about a nicer " + sender.demonLord() + "...");
						} else if (sender.getDeviancy() > 33) {
							sender.say(t, "How can I hate this, when it's with " + sender.theDemonLord() + "...?");
						} else {
							sender.say(t, "Even if it's with " + sender.theDemonLord() + ", I... I don't like this...");
						}
					} else if (sender.getObedience() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.ANGER);
						if (sender.getDeviancy() > 66) {
							sender.say(t, "This is too much, even for me!");
						} else if (sender.getDeviancy() > 33) {
							sender.say(t, "This is too much for me!");
						} else {
							sender.say(t, "You know I don't like this sort of thing...");
						}
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
						if (sender.getDeviancy() > 66) {
							sender.say(t, "Just go away and let me play with myself!");
						} else if (sender.getDeviancy() > 33) {
							sender.say(t, "I don't want to do this!  At least not with you!");
						} else {
							if (sender.forsakenOwner != null) {
								if (sender.getInnocence() > 66) {
									sender.say(t, "Just do your pervy stuff and get out of here already.");
								} else if (sender.getInnocence() > 33) {
									sender.say(t, "You're... disgusting.");
								} else {
									sender.say(t, "I expect that you're here to abuse my body.  How predictable...");
								}
							} else {
								if (sender.getInnocence() > 66) {
									sender.say(t, "Are you just here to perv on me?  Gross.");
								} else if (sender.getInnocence() > 33) {
									sender.say(t, "If you're trying to seduce me, then you're wasting your time.");
								} else {
									sender.say(t, "Disgusting creature.  State your purpose or get out of my sight.");
								}
							}
						}
					}
				}
			} else if (this == Project.SpreadLegs) {
				//placeholder
				sender.say(t, "Please, touch me more, " + sender.demonLord() + "!");
				Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
			} else if (this == Project.PushDown) {
				if (sender.getDeviancy() > 66) {
					if (sender.getConfidence() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
						sender.say(t, "Aaah... I wanna rape " + sender.theDemonLord() + " a lot...!");
					} else if (sender.getConfidence() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
						sender.say(t, "Hurry, hurry...!");
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
						sender.say(t, "If we don't do this now... I-I'm gonna go crazy...!");
					}
				} else if (sender.getDeviancy() > 33) {
					Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
					if (sender.getInnocence() > 66) {
						sender.say(t, "Mm...!  Mm...");
					} else if (sender.getInnocence() > 33) {
						sender.say(t, "Let's just do it.");
					} else {
						sender.say(t, "Such an... impressive specimen...");
					}
				} else {
					if (sender.getObedience() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
						sender.say(t, "Are you sure you don't want to lead, " + sender.demonLord() + "?");
					} else if (sender.getObedience() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
						sender.say(t, "I have no idea what I'm doing...");
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
						sender.say(t, "Let's get this over with.");
					}
				}
			} else if (this == Project.PullDown) {
				if (sender.getDeviancy() > 66) {
					Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
					if (sender.getConfidence() > 66) {
						sender.say(t, "Hurry up and take me!");
					} else if (sender.getConfidence() > 33) {
						sender.say(t, "I'm ready for you...!");
					} else {
						sender.say(t, "P-Please... do whatever you want with me...!");
					}
				} else if (sender.getDeviancy() > 33) {
					if (sender.coerced()) {
						if (sender.getDignity() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.LEWD);
							sender.say(t, "I-I guess I'm ready when you are...");
						} else if (sender.getDignity() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.LEWD);
							sender.say(t, "This is going to feel good...");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
							sender.say(t, "I can't take it any more...!");
						}
					} else {
						if (sender.getConfidence() > 66) {
							sender.say(t, "Get... back...!");
						} else if (sender.getConfidence() > 33) {
							sender.say(t, "Wha- What!?");
						} else {
							sender.say(t, "N-No...!");
						}
					}
				} else {
					if (sender.getObedience() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
						sender.say(t, "I hope you enjoy my body...");
					} else if (sender.getObedience() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
						sender.say(t, "I never thought I'd be doing this...");
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.SHAME);
						if (sender.imprisoned) {
							sender.say(t, "Go on, finish satisfying yourself so I can get out of here!");
						} else {
							sender.say(t, "What do you think you're trying to do with me!?");
						}
					}
				}
			} else if (this == Project.Escape) {
				if (sender.getObedience() > 66) {
					if (sender.getMorality() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
						sender.say(t, "I-I'm so sorry, " + sender.demonLord() + "...  I'm worthless...");
					} else if (sender.getMorality() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.SHAME);
						sender.say(t, "Are you going to punish me...?");
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.SHAME);
						sender.say(t, "I can't!  Even if you are " + sender.theDemonLord() + "!");
					}
				} else if (sender.getObedience() > 33) {
					if (sender.getInnocence() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.SHAME);
						sender.say(t, "Oh no!  I- I didn't mean to do that!");
					} else if (sender.getInnocence() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
						sender.say(t, "I'm sorry, but I really need a break...");
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
						sender.say(t, "I cannot continue.");
					}
				} else {
					if (sender.getConfidence() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
						sender.say(t, "Whew...  Heh, I couldn't hold myself back anymore.");
					} else if (sender.getConfidence() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
						sender.say(t, "I'm not afraid of you!");
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
						sender.say(t, "I c-can't take this anymore!");
					}
				}
			} else if (this == Project.StopActing) {
				if (sender.getObedience() > 66) {
					if (sender.getMorality() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.STRUGGLE);
						sender.say(t, "Maybe... I need more training...");
					} else if (sender.getMorality() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.SHAME);
						sender.say(t, "Sorry, I'm... at my limit...");
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.JOY);
						sender.say(t, "How about I lay here while you rub up against me?  That will feel good too, right?");
					}
				} else if (sender.getObedience() > 33) {
					if (sender.getDeviancy() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
						sender.say(t, "Well, I enjoyed that.  Let's do it again sometime.");
					} else if (sender.getDeviancy() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
						sender.say(t, "That was good enough, right?");
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.NEUTRAL);
						sender.say(t, "I should go... do some combat training.  I can't stay here doing this all day.");
					}
				} else {
					Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
					if (sender.getInnocence() > 66) {
						sender.say(t, "I'm not gonna make you feel good anymore!");
					} else if (sender.getInnocence() > 33) {
						sender.say(t, "This is over.");
					} else {
						if (sender.imprisoned) {
							sender.say(t, "Continuing to service you is worse than any punishment you can inflict on me.");
						} else {
							sender.say(t, "I have no interest whatsoever in satisfying you.");
						}
					}
				}
			} else if (this == Project.StrokeCock) {
				if (receiver == w.lordBody) {
					if (sender.getObedience() > 66) {
						if (sender.getInnocence() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
							sender.say(t, "I'm so happy I get to make " + sender.theDemonLord() + " feel good!");
						} else if (sender.getInnocence() > 33) {
							sender.say(t, "Does this feel good, " + sender.demonLord() + "?  I just want to make you happy...");
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
						} else {
							sender.say(t, "I need to impress " + sender.theDemonLord() + " with my technique...!");
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.NEUTRAL);
						}
					} else if (sender.getObedience() > 33) {
						if (sender.currentHATE > 1000) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
							if (sender.imprisoned) {
								sender.say(t, "Ugh, making me touch you like this...!");
							} else {
								sender.say(t, "I should just rip it off...!");
							}
						} else if (sender.currentHATE > 100) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
							sender.say(t, "Why do you need me to do this...?");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FOCUS);
							sender.say(t, "Is this alright?");
						}
					} else {
						if (sender.getDeviancy() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
							sender.say(t, "Ngh...  You knew... I wouldn't be able to resist this beautiful body you made...");
						} else if (sender.getDeviancy() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.ANGER);
							sender.say(t, "Fine.  I'll make you feel good.  But you'd better return the favor...");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
							if (sender.imprisoned) {
								sender.say(t, "Disgusting... I don't want to touch the Demon Lord...");
							} else {
								sender.say(t, "It's... twitching.  What a disgusting thing.");
							}
						}
					}
				}
			} else if (this == Project.VaginalPenetrate) {
				if (Project.PushDown.isInProgress(sender, receiver)) {
					if (sender.getDeviancy() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
						if (sender.getConfidence() > 66) {
							sender.say(t, "Ngh...!  Fuck...!  Yes...!");
						} else if (sender.getConfidence() > 33) {
							sender.say(t, "Your... pussy... feels... too... good...!");
						} else {
							sender.say(t, "I-I'm gonna... fill you up...!");
						}
					} else if (sender.getDeviancy() > 33) {
						if (sender.getInnocence() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
							sender.say(t, "Aaah...!  Wow...!");
						} else if (sender.getInnocence() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
							sender.say(t, "That's... tight...!");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
							sender.say(t, "Ah, yes...  Here we go...");
						}
					} else {
						if (sender.getObedience() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
							sender.say(t, "I love you, " + sender.demonLord() + "!  I love you so much!");
						} else if (sender.getObedience() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FEAR);
							sender.say(t, "This is... really intense...");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
							sender.say(t, "How dare you... tempt me...!?  Take this!  And this!");
						}
					}
				} else {
					if (sender.getDeviancy() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
						if (sender.getInnocence() > 66) {
							sender.say(t, "Aaah...  Wooow...");
						} else if (sender.getInnocence() > 33) {
							sender.say(t, "Yes!  Yes!  More!");
						} else {
							sender.say(t, "I-I'm- Ah!  Going to climax alreadyyy!");
						}
					} else if (sender.getDeviancy() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
						if (sender.getDignity() > 66) {
							sender.say(t, "Ngh!  Ah!  Aaah!");
						} else if (sender.getDignity() > 33) {
							sender.say(t, "This is... amazing...!");
						} else {
							sender.say(t, "Ooogh!  It's squeezing meee!");
						}
					} else {
						if (sender.getObedience() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.LEWD);
							sender.say(t, "I'll hold out... as long as I can...!");
						} else if (sender.getObedience() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.LEWD);
							sender.say(t, "Why... are you making me feel so good...!?");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
							sender.say(t, "J-Just... have to calm down...!");
						}
					}
				}
			} else if (this == Project.PenetratedVaginally) {
				if (Project.PushDown.isInProgress(sender, receiver)) {
					if (sender.getDeviancy() > 66) {
						if (sender.getObedience() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
							sender.say(t, "Yes!  Yes!  Cum inside me!.");
						} else if (sender.getObedience() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
							sender.say(t, "Aaah...  Your cock is amazing, " + sender.demonLord() + "!");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
							sender.say(t, "You... t-tricked me into doing this...!");
						}
					} else if (sender.getDeviancy() > 33) {
						if (sender.getConfidence() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
							sender.say(t, "Ngh...!  I know you like it... rough...!");
						} else if (sender.getConfidence() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
							sender.say(t, "It's...  Ah!  Inside!");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
							sender.say(t, "Th-This... actually feels really good...!");
						}
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.LEWD);
						if (sender.getDignity() > 66) {
							sender.say(t, "I can't believe... I'm doing this...!");
						} else if (sender.getDignity() > 33) {
							sender.say(t, "Let me... catch my breath...");
						} else {
							sender.say(t, "Ow...");
						}
					}
				} else {
					if (sender.getObedience() > 66) {
						if (sender.getDeviancy() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
							sender.say(t, "Yes!  Yes!  Take me!  Take me!");
						} else if (sender.getDeviancy() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
							sender.say(t, "I'm sorry, " + sender.demonLord() + "!  It feels too good with you inside me!");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
							sender.say(t, "Does it feel good when I... ngh... squeeze?  Like this?");
						}
					} else if (sender.getObedience() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
						if (sender.getConfidence() > 66) {
							sender.say(t, "Aaagh...!  This shouldn't... feel so good...!");
						} else if (sender.getConfidence() > 33) {
							sender.say(t, "Ah!  Ah, wow...");
						} else {
							sender.say(t, "Mm!  Nn...!  Ah, I-I'm...!");
						}
					} else {
						if (sender.getInnocence() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
							sender.say(t, "Aaagh, gaaah!  Stop it, nnnaaah!");
						} else if (sender.getInnocence() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.LEWD);
							sender.say(t, "Mph!  Gh!  Agh!");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
							sender.say(t, "Just... climax already, you brute...!");
						}
					}
				}
			} else if (this == Project.AnalPenetrate) {
				if (Project.PushDown.isInProgress(sender, receiver)) {
					if (sender.getDeviancy() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
						if (sender.getConfidence() > 66) {
							sender.say(t, "Ngh...!  Fuck...!  Yes...!");
						} else if (sender.getConfidence() > 33) {
							sender.say(t, "Your... ass... feels... too... good...!");
						} else {
							sender.say(t, "I-I'm gonna... fill you up...!");
						}
					} else if (sender.getDeviancy() > 33) {
						if (sender.getInnocence() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
							sender.say(t, "Aaah...!  Wow...!");
						} else if (sender.getInnocence() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
							sender.say(t, "That's... tight...!");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
							sender.say(t, "Ah, yes...  Here we go...");
						}
					} else {
						if (sender.getObedience() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
							sender.say(t, "I love you, " + sender.demonLord() + "!  I love you so much!");
						} else if (sender.getObedience() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FEAR);
							sender.say(t, "This is... really intense...");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
							sender.say(t, "How dare you... tempt me...!?  Take this!  And this!");
						}
					}
				} else {
					if (sender.getDeviancy() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
						if (sender.getInnocence() > 66) {
							sender.say(t, "Aaah...  Wooow...");
						} else if (sender.getInnocence() > 33) {
							sender.say(t, "Yes!  Yes!  More!");
						} else {
							sender.say(t, "I-I'm- Ah!  Going to climax alreadyyy!");
						}
					} else if (sender.getDeviancy() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
						if (sender.getDignity() > 66) {
							sender.say(t, "Ngh!  Ah!  Aaah!");
						} else if (sender.getDignity() > 33) {
							sender.say(t, "This is... amazing...!");
						} else {
							sender.say(t, "Ooogh!  It's squeezing meee!");
						}
					} else {
						if (sender.getObedience() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.LEWD);
							sender.say(t, "I'll hold out... as long as I can...!");
						} else if (sender.getObedience() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.LEWD);
							sender.say(t, "Why... are you making me feel so good...!?");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
							sender.say(t, "J-Just... have to calm down...!");
						}
					}
				}
			} else if (this == Project.PenetratedAnally) {
				if (Project.PushDown.isInProgress(sender, receiver)) {
					if (sender.getDeviancy() > 66) {
						if (sender.getObedience() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
							sender.say(t, "Yes!  Yes!  Cum inside me!.");
						} else if (sender.getObedience() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
							sender.say(t, "Aaah...  Your cock is amazing, " + sender.demonLord() + "!");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
							sender.say(t, "You... t-tricked me into doing this...!");
						}
					} else if (sender.getDeviancy() > 33) {
						if (sender.getConfidence() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
							sender.say(t, "Ngh...!  I know you like it... rough...!");
						} else if (sender.getConfidence() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
							sender.say(t, "It's...  Ah!  Inside!");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
							sender.say(t, "Th-This... actually feels really good...!");
						}
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.LEWD);
						if (sender.getDignity() > 66) {
							sender.say(t, "I can't believe... I'm doing this...!");
						} else if (sender.getDignity() > 33) {
							sender.say(t, "Let me... catch my breath...");
						} else {
							sender.say(t, "Ow...");
						}
					}
				} else if (Project.BeLubricated.isInProgress(sender, null)) {
					if (sender.getObedience() > 66) {
						if (sender.getDeviancy() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
							sender.say(t, "Yes!  Yes!  Take me!  Take me!");
						} else if (sender.getDeviancy() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
							sender.say(t, "I'm sorry, " + sender.demonLord() + "!  It feels too good with you inside me!");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
							sender.say(t, "Does it feel good when I... ngh... squeeze?  Like this?");
						}
					} else if (sender.getObedience() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
						if (sender.getConfidence() > 66) {
							sender.say(t, "Aaagh...!  This shouldn't... feel so good...!");
						} else if (sender.getConfidence() > 33) {
							sender.say(t, "Ah!  Ah, wow...");
						} else {
							sender.say(t, "Mm!  Nn...!  Ah, I-I'm...!");
						}
					} else {
						if (sender.getInnocence() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
							sender.say(t, "Aaagh, gaaah!  Stop it, nnnaaah!");
						} else if (sender.getInnocence() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.LEWD);
							sender.say(t, "Mph!  Gh!  Agh!");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
							sender.say(t, "Just... climax already, you brute...!");
						}
					}
				} else {
					if (sender.forsakenOwner == null && sender.chosenOwner != null && sender.chosenOwner.aVirg) {
						if (sender.getConfidence() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
							sender.say(t, "That's it!  We're done here!");
						} else if (sender.getConfidence() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
							sender.say(t, "You... You tried to... Ugh, I never should have come here!");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
							sender.say(t, "I-IT HURTS!  STOOOP!");
						}
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.FEAR);
						if (sender.getConfidence() > 66) {
							if (sender.getObedience() > 66) {
								sender.say(t, "I'm... guh... f-fine, " + sender.demonLord() + "!");
							} else if (sender.getObedience() > 33) {
								sender.say(t, "Why are you doing this!?  I'm serving you now!  Gaaah, pleeease!");
							} else {
								sender.say(t, "I'll stop fighting, I'll stop talking back, I'll do anything!");
							}
						} else if (sender.getConfidence() > 33) {
							if (sender.getDignity() > 66) {
								sender.say(t, "Please, please!  Stooop!");
							} else if (sender.getDignity() > 33) {
								sender.say(t, "Mgh...  guh... I-I can't...");
							} else {
								sender.say(t, "AAAGH, NOOO!");
							}
						} else {
							if (sender.getDeviancy() > 66) {
								sender.say(t, "Nnnaaah~!");
							} else if (sender.getDeviancy() > 33) {
								sender.say(t, "Aah, nnooo, oooh!");
							} else {
								sender.say(t, "Agh!  Mph, guh, n-no...!");
							}
						}
					}
				}
			} else if (this == Project.BeTied) {
				if (sender.forsakenOwner == null && sender.chosenOwner != null) {
					if (sender.chosenOwner.truce || sender.chosenOwner.drained) {
						if (sender.getConfidence() > 66) {
							if (sender.getInnocence() > 66) {
								Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.FEAR);
								sender.say(t, "H-Heh, this is actually a little bit scary...");
							} else if (sender.getInnocence() > 33) {
								Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
								sender.say(t, "You'll have to try harder than this if you want to make me act meek and submissive.");
							} else {
								Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.ANGER);
								sender.say(t, "Bondage play?  Hm, if you insist.");
							}
						} else if (sender.getConfidence() > 33) {
							if (sender.getInnocence() > 66) {
								Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
								sender.say(t, "I guess I can't refuse to let you tie me up...  B-But I can break out anytime I want!  Probably!");
							} else if (sender.getInnocence() > 33) {
								Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
								sender.say(t, "You'd better not be trying to trick me into doing something I don't want to do, Demon Lord...");
							} else {
								Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
								sender.say(t, "I see that you're testing the limits of my trust...");
							}
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.SHAME, Project.Emotion.FEAR);
							if (sender.getInnocence() > 66) {
								sender.say(t, "I-I said I'd do whatever you want!  You don't have to tie me up...  But I guess you can if you want to...");
							} else if (sender.getInnocence() > 33) {
								sender.say(t, "Nn...  P-Please... be gentle with me...");
							} else {
								sender.say(t, "I... I can endure this... I must not cry...");
							}
						}
					} else {
						if (sender.getConfidence() > 66) {
							if (sender.getInnocence() > 66) {
								Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
								sender.say(t, "Hey!  What are you trying to do!?  I'm gonna bust out of here right away!");
							} else if (sender.getInnocence() > 33) {
								Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
								sender.say(t, "Are you seriously trying to take me down alone!?  Looks like you need to be taught a lesson!");
							} else {
								Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.NEUTRAL);
								sender.say(t, "I'm sure that you aren't stupid enough to think that physical restraints of this level are meaningful to one of the Chosen.");
							}
						} else if (sender.getConfidence() > 33) {
							if (sender.getInnocence() > 66) {
								Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.ANGER);
								sender.say(t, "I'm...!  Nnngh...!  Not gonna let you tie me up!");
							} else if (sender.getInnocence() > 33) {
								Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.FEAR);
								sender.say(t, "Stop that!  Stop, or I'll stop you myself!");
							} else {
								Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
								sender.say(t, "Ridiculous!  I offer you a chance at good-faith negotiation, and this is how you respond!?");
							}
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FEAR, Project.Emotion.STRUGGLE);
							if (sender.getInnocence() > 66) {
								sender.say(t, "S-Stop!  I'm gonna start fighting back!  I-I really am!");
							} else if (sender.getInnocence() > 33) {
								sender.say(t, "Was this some sort of trap!?  No!  I-I have to escape!");
							} else {
								sender.say(t, "I... I know that I can escape these restraints!  I must not panic!");
							}
						}
					}
				}
			} else if (this == Project.LickCock) {
				if (sender.getObedience() > 66) {
					Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
					if (sender.getInnocence() > 66) {
						sender.say(t, "Aaah, yes...!  It's " + sender.theDemonLord() + "'s cock!  " + sender.TheDemonLord() + "'s cock!");
					} else if (sender.getInnocence() > 33) {
						sender.say(t, "Does this feel good, " + sender.demonLord() + "?  Mm... I'm happy...");
					} else {
						sender.say(t, "This... is my purpose now...  To give " + sender.theDemonLord() + " pleasure...");
					}
				} else if (sender.getObedience() > 33) {
					Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.LEWD);
					if (sender.getConfidence() > 66) {
						sender.say(t, "I'll make you cum right away!");
					} else if (sender.getConfidence() > 33) {
						sender.say(t, "Mm...");
					} else {
						sender.say(t, "A-Am I doing it right?  Does my mouth feel good?");
					}
				} else {
					if (sender.getDeviancy() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
					}
					if (sender.getDignity() > 66) {
						w.append(t, "Ugh...  It just... smells disgusting...");
					} else if (sender.getDignity() > 33) {
						w.append(t, "Fine... but if you keep acting smug... I swear I'll bite it...");
					} else {
						w.append(t, "I can do this.  It doesn't even taste that bad.");
					}
				}
			} else if (this == Project.LickPussy) {
				if (sender.getObedience() > 66) {
					Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
					if (sender.getInnocence() > 66) {
						sender.say(t, "Aaah, yes...!  It's " + sender.theDemonLord() + "'s pussy!  " + sender.TheDemonLord() + "'s pussy!");
					} else if (sender.getInnocence() > 33) {
						sender.say(t, "Does this feel good, " + sender.demonLord() + "?  Mm... I'm happy...");
					} else {
						sender.say(t, "This... is my purpose now...  To give " + sender.theDemonLord() + " pleasure...");
					}
				} else if (sender.getObedience() > 33) {
					Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.LEWD);
					if (sender.getConfidence() > 66) {
						sender.say(t, "I'll make you cum right away!");
					} else if (sender.getConfidence() > 33) {
						sender.say(t, "Mm...");
					} else {
						sender.say(t, "A-Am I doing it right?  Does my tongue feel good?");
					}
				} else {
					if (sender.getDeviancy() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
					}
					if (sender.getDignity() > 66) {
						w.append(t, "Ugh...  It just... smells disgusting...");
					} else if (sender.getDignity() > 33) {
						w.append(t, "Do you think I'm enjoying this?  Ugh...");
					} else {
						w.append(t, "I can do this.  It doesn't even taste that bad.");
					}
				}
			} else if (this == Project.StepOnCock) {
				if (sender.getObedience() > 66) {
					if (sender.getInnocence() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
						sender.say(t, "Doing it with my feet is fun!");
					} else if (sender.getInnocence() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.FEAR);
						sender.say(t, "If I do it too hard, it will hurt... but if I don't do it hard enough, it won't feel good.");
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
						sender.say(t, "I've trained every single part of my body for serving " + sender.theDemonLord() + ".");
					}
				} else if (sender.getObedience() > 33) {
					Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
					if (sender.getConfidence() > 66) {
						sender.say(t, "I always knew I'd be able to find a way to grind the Demon Lord under my heel...");
					} else if (sender.getConfidence() > 33) {
						sender.say(t, "This is really all you want from me?");
					} else {
						sender.say(t, "I-Is this really alright?  You aren't going to suddenly get angry?");
					}
				} else {
					if (sender.getDeviancy() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
					}
					if (sender.getMorality() > 66) {
						sender.say(t, "Stop enjoying this!  You're making me look like some sort of pervert!");
					} else if (sender.getMorality() > 33) {
						sender.say(t, "Don't move, or I'll crush it!");
					} else {
						sender.say(t, "Still doesn't hurt!?  Then I'll just do it harder!");
					}
				}
			} else if (this == Project.CockSteppedOn) {
				if (sender.getDeviancy() > 66) {
					Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
					if (sender.getInnocence() > 66) {
						sender.say(t, "Ah!  Aaah!  " + sender.TheDemonLord() + "'s foot is driving me crazyyy!");
					} else if (sender.getInnocence() > 33) {
						sender.say(t, "Aaah, ooough, wooow!");
					} else {
						sender.say(t, "Yes!  Yeesss!  Stomp on me harder, " + sender.demonLord() + ", pleeease!");
					}
				} else if (sender.getDeviancy() > 33) {
					Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
					if (sender.getDignity() > 66) {
						sender.say(t, "Enjoying something like this...!  I'm- nnnaaah!  Pathetiiic!");
					} else if (sender.getDignity() > 33) {
						sender.say(t, "Guh!  I c-can't... resist...!");
					} else {
						sender.say(t, "Aaah, nooo!  This shouldn't feel goood!");
					}
				} else {
					if (sender.getObedience() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.SHAME);
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
					}
					if (sender.getConfidence() > 66) {
						sender.say(t, "Ngh!  Agh...  I can take this...!");
					} else if (sender.getConfidence() > 33) {
						sender.say(t, "Gah!  Wh-Where are you- Gh!");
					} else {
						sender.say(t, "Nn!  It... hurts...");
					}
				}
			} else if (this == Project.StepOnClit) {
				if (sender.getObedience() > 66) {
					if (sender.getInnocence() > 66) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.JOY, Project.Emotion.FOCUS);
						sender.say(t, "Doing it with my feet is fun!");
					} else if (sender.getInnocence() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.FEAR);
						sender.say(t, "If I do it too hard, it will hurt... but if I don't do it hard enough, it won't feel good.");
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
						sender.say(t, "I've trained every single part of my body for serving " + sender.theDemonLord() + ".");
					}
				} else if (sender.getObedience() > 33) {
					Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
					if (sender.getConfidence() > 66) {
						sender.say(t, "I always knew I'd be able to find a way to grind the Demon Lord under my heel...");
					} else if (sender.getConfidence() > 33) {
						sender.say(t, "This is really all you want from me?");
					} else {
						sender.say(t, "I-Is this really alright?  You aren't going to suddenly get angry?");
					}
				} else {
					if (sender.getDeviancy() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.ANGER);
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
					}
					if (sender.getMorality() > 66) {
						sender.say(t, "Stop enjoying this!  You're making me look like some sort of pervert!");
					} else if (sender.getMorality() > 33) {
						sender.say(t, "Don't move, or I'll shove my whole foot inside!");
					} else {
						sender.say(t, "Still doesn't hurt!?  Then I'll just do it harder!");
					}
				}
			} else if (this == Project.ClitSteppedOn) {
				if (sender.getDeviancy() > 66) {
					Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.JOY);
					if (sender.getInnocence() > 66) {
						sender.say(t, "Ah!  Aaah!  " + sender.TheDemonLord() + "'s foot is driving me crazyyy!");
					} else if (sender.getInnocence() > 33) {
						sender.say(t, "Aaah, ooough, wooow!");
					} else {
						sender.say(t, "Yes!  Yeesss!  Stomp on me harder, " + sender.demonLord() + ", pleeease!");
					}
				} else if (sender.getDeviancy() > 33) {
					Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.STRUGGLE);
					if (sender.getDignity() > 66) {
						sender.say(t, "Enjoying something like this...!  I'm- nnnaaah!  Pathetiiic!");
					} else if (sender.getDignity() > 33) {
						sender.say(t, "Guh!  I c-can't... resist...!");
					} else {
						sender.say(t, "Aaah, nooo!  This shouldn't feel goood!");
					}
				} else {
					if (sender.getObedience() > 33) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.STRUGGLE, Project.Emotion.SHAME);
					} else {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.STRUGGLE);
					}
					if (sender.getConfidence() > 66) {
						sender.say(t, "Ngh!  Agh...  I can take this...!");
					} else if (sender.getConfidence() > 33) {
						sender.say(t, "Gah!  Wh-Where are you- Gh!");
					} else {
						sender.say(t, "Nn!  It... hurts...");
					}
				}
			} else if (this == Project.DirtyTalk) {
				if (sender.getObedience() > 66) {
					Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
					if (w.sceneDuration % 3 == 0) {
						if (sender.getMorality() > 66) {
							sender.say(t, "Please don't hold back.  You can use my body to cum as many times as you like...");
						} else if (sender.getMorality() > 33) {
							sender.say(t, "I'm so happy that you're enjoying this!");
						} else {
							sender.say(t, "I'm your favorite, right?  Go on, tell me that I'm your favorite...!");
						}
					} else if (w.sceneDuration % 3 == 1) {
						if (sender.getConfidence() > 66) {
							sender.say(t, "I can... nnh... give you more pleasure than anyone else...!");
						} else if (sender.getConfidence() > 33) {
							sender.say(t, "I can tell that you're feeling good.");
						} else {
							sender.say(t, "E-Even someone like me can make " + sender.theDemonLord() + " feel good, huh?");
						}
					} else {
						if (sender.getDeviancy() > 66) {
							sender.say(t, "Aaah, " + sender.demonLord() + "!  I love you!  I love making you cum!");
						} else if (sender.getDeviancy() > 33) {
							sender.say(t, "Being able to make you cum... feels incredible...!");
						} else {
							sender.say(t, "Just knowing that you're happy... is what makes me feel good.");
						}
					}
				} else if (sender.getObedience() > 33) {
					Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
					if (w.sceneDuration % 3 == 0) {
						if (sender.getMorality() > 66) {
							sender.say(t, "It almost feels like I'm raping you, " + sender.demonLord() + ".");
						} else if (sender.getMorality() > 33) {
							sender.say(t, "You aren't holding back, are you?");
						} else {
							sender.say(t, "I'll make you cum, but you'd better do something nice for me in return.");
						}
					} else if (w.sceneDuration % 3 == 1) {
						if (sender.getConfidence() > 66) {
							sender.say(t, "Go on!  Cum!");
						} else if (sender.getConfidence() > 33) {
							sender.say(t, "Are you cumming already?");
						} else {
							sender.say(t, "Right now, I have... the p-power... to decide whether you cum...");
						}
					} else {
						if (sender.getDeviancy() > 66) {
							sender.say(t, "The face you make when you want to cum... is pretty sexy too...");
						} else if (sender.getDeviancy() > 33) {
							sender.say(t, "Tell me how good it feels.");
						} else {
							sender.say(t, "You're a real pervert, " + sender.demonLord() + ".");
						}
					}
				} else {
					if (w.sceneDuration % 3 == 0) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
						if (sender.getMorality() > 66) {
							sender.say(t, "You're nothing but a pitiful pervert.");
						} else if (sender.getMorality() > 33) {
							sender.say(t, "I can't believe you're enjoying this.");
						} else {
							sender.say(t, "I'm going to do it harder.  Let's see if you can feel pain.");
						}
					} else if (w.sceneDuration % 3 == 1) {
						Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.ANGER, Project.Emotion.FOCUS);
						if (sender.getConfidence() > 66) {
							sender.say(t, "What a worthless Demon Lord.");
						} else if (sender.getConfidence() > 33) {
							sender.say(t, "Disgusting!");
						} else {
							sender.say(t, "A-Are you really this desperate to have someone touch you...?");
						}
					} else {
						if (sender.getDeviancy() > 66) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.LEWD, Project.Emotion.FOCUS);
							sender.say(t, "Hurry up and cum!  Cum and cum until you can't anymore!");
						} else if (sender.getDeviancy() > 33) {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.FOCUS, Project.Emotion.JOY);
							sender.say(t, "How does it feel to be dominated!?");
						} else {
							Project.changePortrait(sender.getGender(), sender.getType(), sender.civilianPortrait(), sender.isForsaken(), w, shownNames, 0, Project.Emotion.NEUTRAL, Project.Emotion.FOCUS);
							sender.say(t, "This is all it takes?  Your body is so weak.");
						}
					}
				}
			}
			sender.say(t, "\"");
			sender.specialLine = 0;
		}
	}
	
	public void shortDescription(JTextPane t, WorldState w, Body sender, Body receiver) {
		if (this == Project.TweakClit) {
			w.append(t, "\n" + sender.capitalizedOwnerName() + " is stroking " + receiver.ownerName() + "'s clit");
		} else if (this == Project.ClitTweaked) {
			w.append(t, "\n" + receiver.capitalizedOwnerName() + " is stroking " + sender.ownerName() + "'s clit");
		} else if (this == Project.SpreadLegs) {
			w.append(t, "\n" + sender.capitalizedOwnerName() + " is spreading " + sender.hisHer() + " legs wide apart");
		} else if (this == Project.PushDown) {
			w.append(t, "\n" + sender.OwnerName() + " is atop " + receiver.ownerName());
		} else if (this == Project.PullDown) {
			w.append(t, "\n" + receiver.OwnerName() + " is atop " + sender.ownerName());
		} else if (this == Project.TieUp) {
			w.append(t, "\n" + receiver.OwnerName() + " has been tied up by " + sender.ownerName());
		} else if (this == Project.BeTied) {
			w.append(t, "\n" + sender.OwnerName() + " has been tied up by " + receiver.ownerName());
		} else if (this == Project.StrokeCock) {
			w.append(t, "\n" + sender.OwnerName() + " strokes " + receiver.ownerName() + "'s cock");
		} else if (this == Project.CockStroked) {
			w.append(t, "\n" + receiver.OwnerName() + " strokes " + sender.ownerName() + "'s cock");
		} else if (this == Project.BeLubricated) {
			w.append(t, "\n" + sender.OwnerName() + "'s anus is coated with slick lubricant");
		} else if (this == Project.VaginalPenetrate) {
			w.append(t, "\n" + sender.OwnerName() + " fucks " + receiver.ownerName() + " vaginally");
		} else if (this == Project.PenetratedVaginally) {
			w.append(t, "\n" + receiver.OwnerName() + " fucks " + sender.ownerName() + " vaginally");
		} else if (this == Project.AnalPenetrate) {
			w.append(t, "\n" + sender.OwnerName() + " fucks " + receiver.ownerName() + "'s ass");
		} else if (this == Project.PenetratedAnally) {
			w.append(t, "\n" + receiver.OwnerName() + " fucks " + sender.ownerName() + "'s ass");
		} else if (this == Project.StripOther) {
			w.append(t, "\n" + sender.OwnerName() + " strips off " + receiver.ownerName() + "'s clothes.");
		} else if (this == Project.Stripped) {
			w.append(t, "\n" + receiver.OwnerName() + " strips off " + sender.ownerName() + "'s clothes.");
		} else if (this == Project.LickCock) {
			w.append(t, "\n" + sender.OwnerName() + " licks " + receiver.ownerName() + "'s cock.");
		} else if (this == Project.CockLicked) {
			w.append(t, "\n" + receiver.OwnerName() + " licks " + sender.ownerName() + "'s cock.");
		} else if (this == Project.LickPussy) {
			w.append(t, "\n" + sender.OwnerName() + " licks " + receiver.ownerName() + "'s pussy.");
		} else if (this == Project.PussyLicked) {
			w.append(t, "\n" + receiver.OwnerName() + " licks " + sender.ownerName() + "'s pussy.");
		} else if (this == Project.Supine) {
			w.append(t, "\n" + sender.OwnerName() + " lies on " + sender.hisHer() + " back.");
		} else if (this == Project.StepOnCock) {
			w.append(t, "\n" + sender.OwnerName() + " rubs " + receiver.ownerName() + "'s cock with " + sender.hisHer() + " foot.");
		} else if (this == Project.CockSteppedOn) {
			w.append(t, "\n" + receiver.OwnerName() + " rubs " + sender.ownerName() + "'s cock with " + receiver.hisHer() + " foot.");
		} else if (this == Project.StepOnClit) {
			w.append(t, "\n" + sender.OwnerName() + " rubs " + receiver.ownerName() + "'s clit with " + sender.hisHer() + " foot.");
		} else if (this == Project.ClitSteppedOn) {
			w.append(t, "\n" + receiver.OwnerName() + " rubs " + sender.ownerName() + "'s clit with " + receiver.hisHer() + " foot.");
		}
	}
	
	public void endActivityFlavor(JTextPane t, WorldState w, Body sender, Body receiver) {
		if (this == Project.TweakClit) {
			w.append(t, "\n\n" + sender.capitalizedOwnerName() + " takes " + sender.hisHer() + " hand from " + receiver.ownerName() + "'s clit.");
			/*if (receiver == w.lordBody) {
				if (sender.getObedience() > 66) {
					w.append(t, ", apparently having something else in mind.");
				} else if (sender.getObedience() > 33) {
					w.append(t, " hesitantly.");
				} else {
					w.append(t, ", unable to bear touching " + receiver.ownerName() + " any longer.");
				}
			} else if (sender == w.lordBody) {
				w.append(t, ".");
			} else {
				//group stuff
			}*/
		} else if (this == Project.ClitTweaked) {
			w.append(t, "\n\n" + sender.capitalizedOwnerName() + " moves " + sender.hisHer() + " hips away from " + receiver.ownerName() + "'s hand.");
			/*if (receiver == w.lordBody) {
				if (sender.getObedience() > 66) {
					w.append(t, ", then looks ashamed of doing so.");
				} else if (sender.getObedience() > 33) {
					w.append(t, ", discomfort clear on " + sender.hisHer() + " face.");
				} else {
					w.append(t, ", glaring angrily at " + sender.himHer() + ".");
				}
			} else if (sender == w.lordBody) {
				w.append(t, ".");
			} else {
				//fill in with group stuff
			}*/
		} else if (this == Project.PushDown) {
			w.append(t, "\n\n" + sender.capitalizedOwnerName() + " gets off " + receiver.ownerName() + ", standing up.");
		} else if (this == Project.PullDown) {
			w.append(t, "\n\n" + sender.OwnerName() + " gets out from under " + receiver.ownerName() + ".");
		} else if (this == Project.TieUp) {
			w.append(t, "\n\n" + sender.OwnerName() + " unties " + receiver.ownerName() + "'s bindings.");
		} else if (this == Project.StrokeCock) {
			w.append(t, "\n\n" + sender.capitalizedOwnerName() + " takes " + sender.hisHer() + " hand from " + receiver.ownerName() + "'s cock.");
		} else if (this == Project.CockStroked) {
			w.append(t, "\n\n" + sender.capitalizedOwnerName() + " moves " + sender.hisHer() + " hips away from " + receiver.ownerName() + "'s hand.");
		} else if (this == Project.VaginalPenetrate) {
			w.append(t, "\n\n" + sender.capitalizedOwnerName() + " pulls " + sender.himHer() + "self out of " + receiver.ownerName() + "'s pussy.");
		} else if (this == Project.PenetratedVaginally) {
			w.append(t, "\n\n" + sender.capitalizedOwnerName() + " lifts " + sender.himHer() + "self until " + receiver.ownerName() + "'s cock slides out of " + sender.hisHer() + " pussy.");
		} else if (this == Project.AnalPenetrate) {
			w.append(t, "\n\n" + sender.OwnerName() + " pulls " + sender.himHer() + "self out of " + receiver.ownerName() + "'s ass.");
		} else if (this == Project.PenetratedAnally) {
			w.append(t, "\n\n" + sender.OwnerName() + " lifts " + sender.himHer() + "self until " + receiver.ownerName() + "'s cock slides out of " + sender.hisHer() + " ass.");
		} else if (this == Project.StripOther) {
			w.append(t, "\n\n" + sender.OwnerName() + " stops removing " + receiver.ownerName() + "'s clothes.");
		} else if (this == Project.LickCock) {
			w.append(t, "\n\n" + sender.OwnerName() + " stops licking " + receiver.ownerName() + "'s cock.");
		} else if (this == Project.CockLicked) {
			w.append(t, "\n\n" + sender.OwnerName() + " pulls " + sender.hisHer() + " cock away from " + receiver.ownerName() + "'s mouth.");
		} else if (this == Project.LickPussy) {
			w.append(t, "\n\n" + sender.OwnerName() + " stops licking " + receiver.ownerName() + "'s pussy.");
		} else if (this == Project.PussyLicked) {
			w.append(t, "\n\n" + sender.OwnerName() + " pulls " + sender.hisHer() + " hips away from " + receiver.ownerName() + "'s tongue.");
		} else if (this == Project.Supine) {
			w.append(t, "\n\n" + sender.OwnerName() + " stands up.");
		} else if (this == Project.StepOnCock) {
			w.append(t, "\n\n" + sender.OwnerName() + " takes " + sender.hisHer() + " foot off " + receiver.ownerName() + "'s cock.");
		} else if (this == Project.CockSteppedOn) {
			w.append(t, "\n\n" + sender.OwnerName() + " moves " + sender.hisHer() + " crotch away from " + receiver.ownerName() + "'s foot.");
		} else if (this == Project.StepOnClit) {
			w.append(t, "\n\n" + sender.OwnerName() + " takes " + sender.hisHer() + " foot off " + receiver.ownerName() + "'s crotch.");
		} else if (this == Project.ClitSteppedOn) {
			w.append(t, "\n\n" + sender.OwnerName() + " moves " + sender.hisHer() + " crotch away from " + receiver.ownerName() + "'s foot.");
		}
	}
	
	public void startActivity(JTextPane t, WorldState w, Body sender, Body receiver) {
		if (this == Project.BeTied) {
			sender.freeBodyPart(HAND);
			sender.freeBodyPart(FOOT);
		}
		Activity[] newInProgress = new Activity[sender.inProgress.length+1];
		Body[] newTargets = new Body[sender.targets.length+1];
		for (int i = 0; i < sender.inProgress.length; i++) {
			newInProgress[i] = sender.inProgress[i];
			newTargets[i] = sender.targets[i];
		}
		newInProgress[newInProgress.length-1] = this;
		newTargets[newTargets.length-1] = receiver;
		sender.inProgress = newInProgress;
		sender.targets = newTargets;
		if (this == Project.TweakClit) {
			if (sender == w.lordBody) {
				w.append(t, sender.capitalizedOwnerName() + " begins to caress " + receiver.ownerName() + "'s clit.");
			} else if (receiver == w.lordBody) {
				w.append(t, sender.capitalizedOwnerName() + " ");
				if (sender.getDeviancy() > 66) {
					if (sender.getInnocence() > 66) {
						w.append(t, "loses " + sender.himHer() + "self in the act of rubbing " + receiver.ownerName() + "'s clit, eyes glazed over and drooling with desire.");
					} else if (sender.getInnocence() > 33) {
						w.append(t, "reaches eagerly for " + receiver.ownerName() + "'s clit, stroking it with an aggressive firmness that comes just short of being painful.");
					} else {
						w.append(t, "abruptly puts " + sender.hisHer() + " hand against " + receiver.ownerName() + "'s clit and starts rubbing up and down, then fondling it with " + sender.hisHer() + " fingers, then gently tugging on it, using every technique at " + sender.hisHer() + " disposal.");
					}
				} else if (sender.getDeviancy() > 33) {
					if (sender.getInnocence() > 66) {
						w.append(t, "pokes and prods " + receiver.ownerName() + "'s clit, then gradually gets into a rhythm of stroking it as " + receiver.heShe() + " grows more confident in what " + receiver.heShe() + "'s doing.");
					} else if (sender.getInnocence() > 33) {
						w.append(t, "brushes " + sender.hisHer() + " fingers against " + receiver.ownerName() + "'s clit, trailing them up and down, then growing more firm, stroking it in earnest.");
					} else {
						w.append(t, "carefully starts to stroke " + receiver.ownerName() + "'s clit, staring into " + receiver.hisHer() + " eyes to gauge " + receiver.hisHer() + " reaction.");
					}
				} else {
					if (sender.getInnocence() > 66) {
						w.append(t, "hesitantly touches " + receiver.ownerName() + "'s clit, then begins to clumsily stroke it, averting " + sender.hisHer() + " eyes as " + sender.heShe() + " does so.  ");
					} else if (sender.getInnocence() > 33) {
						w.append(t, "begins to stroke " + receiver.ownerName() + "'s clit with stiff, mechanical movements.  ");
					} else {
						w.append(t, "brings " + sender.hisHer() + " fingers to " + receiver.ownerName() + "'s clit and begins to stroke it with precise, methodical movements.  ");
					}
					if (sender.getObedience() > 66) {
						w.append(t, "It's clear that " + sender.heShe() + "'s unfamiliar with this sort of thing, but " + sender.heShe() + "'s doing " + sender.hisHer() + " best for " + receiver.ownerName() + ".");
					} else if (sender.getObedience() > 33) {
						w.append(t, sender.HisHer() + " heart clearly isn't in it.");
					} else {
						w.append(t, sender.HeShe() + " looks sickened by what " + sender.heShe() + "'s doing.");
					}
				}
			}
		} else if (this == Project.ClitTweaked) {
			if (sender == w.lordBody) {
				w.append(t, sender.capitalizedOwnerName() + "'s clit is caressed by " + receiver.ownerName() + ".");
			} else {
				w.append(t, sender.capitalizedOwnerName());
				if (sender.getInnocence() > 66) {
					w.append(t, " gasps at the sudden intensity of the stimulation to " + sender.hisHer() + " most sensitive part, and ");
					if (sender.getDeviancy() > 66) {
						w.append(t, sender.hisHer() + " eyes glaze over as " + sender.heShe() + " loses the ability to think of anything other than feeling even better.  ");
					} else if (sender.getDeviancy() > 33) {
						w.append(t, sender.hisHer() + " hips jerk wildly, seemingly caught between jerking away and pushing themselves against " + receiver.ownerName() + "'s fingers.  ");
					} else {
						w.append(t, sender.heShe() + " reflexively tries to jerk " + sender.hisHer() + " hips away.  ");
					}
				} else if (sender.getInnocence() > 33) {
					w.append(t, " feels jolts of pleasure shooting into " + sender.hisHer() + " lower tummy");
					if (sender.getDeviancy() > 66) {
						w.append(t, ", and " + sender.heShe() + " moans helplessly, " + sender.hisHer() + " well-trained body eagerly submitting itself to " + receiver.ownerName() + "'s touch.  ");
					} else if (sender.getDeviancy() > 33) {
						w.append(t, ", and it's a struggle for " + sender.himHer() + " to hold onto " + sender.hisHer() + " reason.  ");
					} else {
						w.append(t, ", even as " + sender.heShe() + " tries to ignore them.  ");
					}
				} else {
					w.append(t, " knew this was coming, but " + sender.heShe() + " still ");
					if (sender.getDeviancy() > 66) {
						w.append(t, "squirms and cries out, unable to maintain any self-control in the face of the pleasure " + sender.heShe() + "'s come to crave.  ");
					} else if (sender.getDeviancy() > 33) {
						w.append(t, "gasps softly at the waves of pleasure that begin to wash over " + sender.hisHer() + " lower body.  ");
					} else {
						w.append(t, "flinches and grimaces, uncomfortable with the intensity of the sensations.  ");
					}
				}
				if (receiver == w.lordBody) {
					if (sender.getDeviancy() > 66) {
						if (sender.getObedience() > 66) {
							w.append(t, sender.HisHer() + " body has grown quite sensitive, but it's even more sensitive than usual when " + sender.heShe() + " knows " + sender.heShe() + "'s being touched by " + receiver.ownerName() + ".");
						} else if (sender.getObedience() > 33) {
							w.append(t, "Soon, " + sender.heShe() + "'s completely consumed in " + sender.hisHer() + " efforts to hump " + receiver.ownerName() + "'s hand.");
						} else {
							w.append(t, sender.HeShe() + " tries to resist, but " + sender.hisHer() + " body has grown so sensitive that the lightest touch to " + sender.hisHer() + " weak spots is enough to subdue " + sender.himHer() + ".");
						}
					} else if (sender.getDeviancy() > 33) {
						if (sender.getObedience() > 66) {
							w.append(t, "Even more than the physical stimulation itself, " + sender.ownerName() + " is ecstatic that " + receiver.ownerName() + " is pleasuring " + sender.himHer() + " directly.");
						} else if (sender.getObedience() > 33) {
							w.append(t, sender.HeShe() + "'s content to let " + receiver.ownerName() + " pleasure " + sender.himHer() + ".");
						} else {
							w.append(t, sender.HeShe() + " has a hard time remembering that " + sender.heShe() + "'s supposed to hate the Demon Lord.");
						}
					} else {
						if (sender.getObedience() > 66) {
							w.append(t, sender.HeShe() + " endures this for " + receiver.ownerName() + "'s sake, hoping that " + receiver.ownerName() + " will enjoy playing with " + sender.hisHer() + " body.");
						} else if (sender.getObedience() > 33) {
							w.append(t, sender.HeShe() + " doesn't make eye contact with " + receiver.ownerName() + ", glancing off to the side.");
						} else {
							w.append(t, sender.HeShe() + " glares at " + receiver.ownerName() + ", not appreciating it in the slightest.");
						}
					}
				}
			}
		} else if (this == Project.SpreadLegs) {
			if (sender == w.lordBody) {
				//currently impossible
			} else {
				//placeholder
				w.append(t, sender.ownerName() + " spreads " + sender.hisHer() + " legs wide apart, eager to be pleasured.");
			}
		} else if (this == Project.Praise) {
			if (sender == w.lordBody) {
				//currently impossible
			} else if (receiver == w.lordBody) {
				if (sender.getObedience() > 66) {
					if (sender.getConfidence() > 66) {
						w.append(t, sender.OwnerName() + " repeatedly, insistently offers to do anything at all that " + receiver.ownerName() + " desires of " + sender.himHer() + ".");
					} else if (sender.getConfidence() > 33) {
						w.append(t, sender.OwnerName() + " voices " + sender.hisHer() + " admiration for " + receiver.ownerName() + ", describing all " + receiver.hisHer() + " great qualities at length.");
					} else {
						w.append(t, sender.OwnerName() + " viciously insults " + sender.himHer() + "self, offering " + sender.hisHer() + " body to " + receiver.ownerName() + " in order to make up for " + sender.hisHer() + " own failures.");
					}
				} else if (sender.getObedience() > 33) {
					if (sender.getDeviancy() > 66) {
						w.append(t, sender.OwnerName() + " eagerly lavishes praise and encouragement on " + receiver.ownerName() + ", hoping to be rewarded with pleasure.");
					} else if (sender.getDeviancy() > 33) {
						w.append(t, sender.OwnerName() + " encourages " + receiver.ownerName() + " to do whatever " + receiver.heShe() + "'d like with " + sender.himHer() + ".");
					} else {
						w.append(t, sender.OwnerName() + " humbly debases " + sender.himHer() + "self and praises " + receiver.ownerName() + ", but the quaver in " + sender.hisHer() + " voice betrays the fact that " + sender.heShe() + "'s doing so out of fear rather than because " + sender.heShe() + " actually believes " + sender.hisHer() + " own words.");
					}
				} else {
					if (sender.getInnocence() > 66) {
						w.append(t, sender.OwnerName() + " starts to praise " + receiver.ownerName() + " before " + sender.heShe() + " remembers that they're supposed to be enemies.");
					} else if (sender.getInnocence() > 33) {
						w.append(t, sender.OwnerName() + " grudgingly compliments " + receiver.ownerName() + ", though " + sender.heShe() + " isn't happy about it.");
					} else {
						w.append(t, sender.OwnerName() + " acts like " + sender.heShe() + "'s coming around to " + receiver.ownerName() + "'s side, giving voice to some compliments, but " + sender.heShe() + "'s actually just hoping to manipulate " + receiver.himHer() + ".");
					}
				}
			}
		} else if (this == Project.Insult) {
			if (sender == w.lordBody) {
				//currently impossible
			} else if (receiver == w.lordBody) {
				if (sender.getObedience() > 66) {
					if (sender.getINJULevel() >= 2) {
						w.append(t, sender.OwnerName() + " is growing too tired to serve " + receiver.ownerName() + ", and " + sender.heShe() + " desperately tries to justify " + sender.hisHer() + " own weakness.");
					} else if (sender.getHATELevel() >= 1) {
						w.append(t, sender.OwnerName() + " is becoming agitated, and while " + sender.heShe() + " adores " + receiver.ownerName() + " too much to confront " + receiver.himHer() + " directly, " + sender.hisHer() + " feelings still come through.");
					} else {
						w.append(t, sender.OwnerName() + "'s psyche, fragile after being trained so thoroughly by the Demon Lord, begins to crack under the strain.");
					}
				} else if (sender.getObedience() > 33) {
					if (sender.getINJULevel() >= 2) {
						w.append(t, sender.OwnerName() + " is getting tired, and " + sender.heShe() + " lets " + receiver.ownerName() + " knows that " + sender.heShe() + " wants to stop this.");
					} else if (sender.getHATELevel() >= 1) {
						w.append(t, sender.OwnerName() + " is growing annoyed with " + receiver.ownerName() + ", and " + sender.heShe() + " lets " + receiver.himHer() + " know it in no uncertain terms.");
					} else {
						w.append(t, sender.OwnerName() + " insults " + receiver.ownerName() + ", hoping to get a reaction.");
					}
				} else {
					if (sender.getINJULevel() >= 2) {
						if (sender.imprisoned) {
							w.append(t, sender.OwnerName() + " is too tired to resist physically, but " + sender.heShe() + " still verbally attacks " + receiver.ownerName() + ".");
						} else {
							w.append(t, sender.OwnerName() + " is too tired to feel like fighting, but " + sender.heShe() + " still verbally attacks " + receiver.ownerName() + ".");
						}
					} else if (sender.getHATELevel() >= 1) {
						if (sender.imprisoned) {
							w.append(t, sender.OwnerName() + " already hates " + receiver.ownerName() + ", but " + sender.heShe() + "'s even angrier than usual at being forced into this, and " + sender.heShe() + " vents " + sender.hisHer() + " frustration with a stream of insults");
						} else {
							w.append(t, sender.OwnerName() + " already hates " + receiver.ownerName() + ", but " + sender.heShe() + "'s even angrier about being suddenly approached and provoked like this.  " + sender.HeShe() + " vents " + sender.hisHer() + " feelings with a stream of insults.");
						}
					} else {
						w.append(t, sender.OwnerName() + " takes the chance to insult " + receiver.ownerName() + " to " + receiver.hisHer() + " face.");
					}
				}
			}
		} else if (this == Project.PushDown) {
			if (sender == w.lordBody) {
				if (w.actingBody == sender) {
					w.append(t, sender.OwnerName() + " pushes " + receiver.ownerName() + " down onto " + receiver.hisHer() + " back.");
				} else {
					w.append(t, sender.OwnerName() + " ends up atop " + receiver.ownerName() + ".");
				}
			} else {
				if (w.actingBody == sender) {
					if (sender.getDeviancy() > 66) {
						if (sender.getConfidence() > 66) {
							w.append(t, sender.OwnerName() + " roughly pushes " + receiver.ownerName() + " down onto " + receiver.hisHer() + " back, panting with barely-restrained desire as " + sender.heShe() + " takes " + sender.hisHer() + " place atop " + receiver.himHer() + ".");
						} else if (sender.getConfidence() > 33) {
							w.append(t, sender.OwnerName() + " practically tackles " + receiver.ownerName() + " down onto the floor, overflowing with lust.");
						} else {
							w.append(t, sender.OwnerName() + "'s lust has made " + sender.himHer() + " far more bold than usual, and " + sender.heShe() + " eagerly pushes " + receiver.ownerName() + " down onto " + receiver.hisHer() + " back.");
						}
					} else if (sender.getDeviancy() > 33) {
						if (sender.getInnocence() > 66) {
							w.append(t, "Overcome by " + sender.hisHer() + " growing lust, " + sender.ownerName() + " wraps " + sender.hisHer() + " arms around " + receiver.ownerName() + " and kisses " + receiver.himHer() + " repeatedly, then seems surprised to realize that " + sender.heShe() + "'s pushed " + receiver.himHer() + " down to the floor in the process.");
						} else if (sender.getInnocence() > 33) {
							w.append(t, sender.OwnerName() + " embraces " + receiver.ownerName() + ", guiding " + receiver.himHer() + " down onto " + receiver.hisHer() + " back.");
						} else {
							w.append(t, "With " + sender.hisHer() + " eyes locked onto " + receiver.ownerName() + "'s " + receiver.mainOrgan() + ", " + sender.ownerName() + " urges " + receiver.himHer() + " to lay down on " + receiver.hisHer() + " back, then climbs atop " + receiver.himHer() + ".");
						}
					} else {
						if (sender.getObedience() > 66) {
							w.append(t, sender.OwnerName() + " gently guides " + receiver.ownerName() + " onto " + receiver.hisHer() + " back, then lays atop " + receiver.himHer() + ", taking deep breaths and trying to ready " + sender.himHer() + "self to serve " + sender.theDemonLord() + ".");
						} else if (sender.getObedience() > 33) {
							w.append(t, sender.OwnerName() + " pushes " + receiver.ownerName() + " down onto " + receiver.hisHer() + " back, but hesitates before going further, uncertain about how to proceed.");
						} else {
							w.append(t, "Wearing an annoyed expression on " + sender.hisHer() + " face, " + sender.ownerName() + " pushes " + receiver.ownerName() + " down onto " + receiver.hisHer() + " back without a hint of sensuality.");
						}
					}
				} else {
					if (sender.getDeviancy() > 66) {
						if (sender.getConfidence() > 66) {
							w.append(t, sender.OwnerName() + " hungrily pushes " + receiver.ownerName() + " the rest of the way down, taking " + sender.hisHer() + " place atop " + receiver.himHer() + ".");
						} else if (sender.getConfidence() > 33) {
							w.append(t, sender.OwnerName() + " is eager to let " + sender.himHer() + "self be pulled atop " + receiver.ownerName() + ", looking down at " + receiver.himHer() + " with clear hunger in " + sender.hisHer() + " eyes.");
						} else {
							w.append(t, "As soon as it's clear that " + receiver.ownerName() + " wants to be on the bottom, " + sender.ownerName() + " loses all traces of hesitation, clambering atop " + receiver.himHer() + " with enthusiasm.");
						}
					} else if (sender.getDeviancy() > 33) {
						if (sender.getInnocence() > 66) {
							w.append(t, sender.OwnerName() + " seems surprised to suddenly find " + sender.himHer() + "self atop " + receiver.ownerName() + ".  " + sender.HeShe() + " blinks down at " + receiver.himHer() + ", trying to decide what to do with " + sender.hisHer() + " new dominant position.");
						} else if (sender.getInnocence() > 33) {
							w.append(t, sender.OwnerName() + " allows " + sender.himHer() + "self to be pulled atop " + receiver.ownerName() + ", and " + sender.heShe() + " finds that " + sender.heShe() + " enjoys the feeling of their bodies against each other.");
						} else {
							w.append(t, sender.OwnerName() + " sees no reason to resist.  " + sender.HeShe() + " takes " + sender.hisHer() + " time deciding what to do next, looking down at " + receiver.ownerName() + " with thoughtful eyes.");
						}
					} else {
						if (sender.getObedience() > 66) {
							w.append(t, sender.OwnerName() + " obediently lays atop " + receiver.ownerName() + ", but " + sender.heShe() + " clearly isn't sure what to do next.");
						} else if (sender.getObedience() > 33) {
							w.append(t, sender.OwnerName() + " hesitates, resisting slightly as " + sender.heShe() + "'s pulled down.  " + sender.HeShe() + " looks at " + receiver.ownerName() + " suspiciously.");
						} else {
							w.append(t, sender.OwnerName() + " takes the opportunity to pin " + receiver.ownerName() + " down, clearly uninterested in using this position for sexual purposes.");
						}
					}
				}
			}
		} else if (this == Project.PullDown) {
			if (sender == w.lordBody) {
				if (w.actingBody == sender) {
					w.append(t, sender.OwnerName() + " lays down on " + sender.hisHer() + " back, pulling " + receiver.ownerName() + " down on top of " + sender.himHer() + ".");
				} else {
					w.append(t, sender.OwnerName() + " lays on " + sender.hisHer() + " back under " + receiver.ownerName() + ".");
				}
			} else {
				if (w.actingBody == sender) {
					if (sender.getDeviancy() > 66) {
						if (sender.getConfidence() > 66) {
							w.append(t, sender.OwnerName() + " forcefully pulls " + receiver.ownerName() + " atop " + sender.himHer() + ", aggressive even in " + sender.hisHer() + " submission.");
						} else if (sender.getConfidence() > 33) {
							w.append(t, sender.OwnerName() + " tangles " + sender.hisHer() + " limbs around " + receiver.ownerName() + ", bringing them both down to the floor together with " + receiver.ownerName() + " atop " + sender.himHer() + ".");
						} else {
							w.append(t, sender.OwnerName() + " lays down on " + sender.hisHer() + " back, weakly trying to pull " + receiver.ownerName() + " down with " + sender.himHer() + ".  " + sender.HeShe() + " trembles with desire as " + sender.heShe() + " looks up at " + receiver.ownerName() + ".");
						}
					} else if (sender.getDeviancy() > 33) {
						if (sender.getDignity() > 66) {
							w.append(t, sender.OwnerName() + " lays down under " + receiver.ownerName() + ", trying to act as though " + sender.heShe() + "'s doing " + receiver.himHer() + " a favor, but " + sender.ownerName() + "'s lustful panting betrays how much " + sender.heShe() + " wants to be taken.");
						} else if (sender.getDignity() > 33) {
							w.append(t, sender.OwnerName() + " eagerly pulls " + receiver.ownerName() + " down atop " + sender.himHer() + ", smiling in anticipation of what's to come.");
						} else {
							w.append(t, sender.OwnerName() + " begs " + receiver.ownerName() + " to take " + sender.himHer() + ", positioning " + sender.himHer() + "self underneath " + receiver.himHer() + ".");
						}
					} else {
						if (sender.getObedience() > 66) {
							w.append(t, sender.OwnerName() + " submissively presents " + sender.himHer() + "self to " + receiver.ownerName() + ", laying on " + sender.hisHer() + " back and squirming nervously as " + sender.heShe() + " anticipates how " + sender.heShe() + "'ll be used.");
						} else if (sender.getObedience() > 33) {
							w.append(t, sender.OwnerName() + " lays down for " + receiver.ownerName() + ", but " + sender.heShe() + " refuses to meet " + receiver.hisHer() + " eyes, still uncertain how " + sender.heShe() + " feels about sex with the Demon Lord.");
						} else {
							w.append(t, sender.OwnerName() + " angrily lays down, glaring up at " + receiver.ownerName() + ".");
						}
					}
				} else {
					if (sender.getObedience() > 66) {
						if (sender.getDignity() > 66) {
							w.append(t, sender.OwnerName() + " tries to hide " + sender.hisHer() + " excitement, but " + sender.heShe() + "'s trembling with eagerness to be of use to " + receiver.ownerName() + ".");
						} else if (sender.getDignity() > 33) {
							w.append(t, sender.OwnerName() + " smiles up at " + receiver.ownerName() + ", warm affection in " + sender.hisHer() + " eyes.");
						} else {
							w.append(t, sender.OwnerName() + " laughs, overjoyed that " + receiver.ownerName() + " is getting ready to use " + sender.himHer() + ".");
						}
					} else if (sender.getObedience() > 33) {
						if (sender.coerced()) {
							if (sender.getConfidence() > 66) {
								w.append(t, sender.OwnerName() + " pulls " + receiver.ownerName() + " the rest of the way atop " + sender.himHer() + ", maintaining control of the situation in " + sender.hisHer() + " own way.  " + sender.HeShe() + " looks up at " + receiver.ownerName() + " expectantly.");
							} else if (sender.getConfidence() > 33) {
								w.append(t, sender.OwnerName() + " obediently plays along, positioning " + sender.himHer() + "self however " + receiver.ownerName() + " directs " + sender.himHer() + ".");
							} else {
								w.append(t, sender.OwnerName() + " meekly allows " + sender.himHer() + "self to be pushed down, fidgeting in anticipation of what will happen to " + sender.himHer() + " next.");
							}
						} else {
							if (sender.getConfidence() > 66) {
								w.append(t, "Once, " + sender.ownerName() + " would have immediately fought " + sender.hisHer() + " way free.  But now " + sender.heShe() + " hesitates, memories of " + sender.hisHer() + " past defeats filling " + sender.himHer() + " with self-doubt.");
							} else if (sender.getConfidence() > 33) {
								w.append(t, sender.OwnerName() + " is too startled to fight back, and " + sender.heShe() + " ends up laying down under " + receiver.ownerName() + ".");
							} else {
								w.append(t, sender.OwnerName() + " squeaks in alarm and freezes up, unable to fight back for the few crucial moments it takes " + receiver.ownerName() + " to get atop " + sender.himHer() + ".");
							}
						}
					} else {
						if (sender.getDeviancy() > 66) {
							w.append(t, sender.OwnerName() + " tries to resist, but " + sender.hisHer() + " well-trained body betrays " + sender.himHer() + ", and " + sender.heShe() + " can only moan in anticipation of how " + sender.hisHer() + " hated enemy will treat " + sender.himHer() + ".");
						} else if (sender.getDeviancy() > 33) {
							w.append(t, sender.OwnerName() + " immediately starts squirming and trying to push " + receiver.ownerName() + " back, but there's less strength in " + sender.hisHer() + " arms than usual, and " + sender.hisHer() + " cheeks are flushing with arousal.");
						} else {
							w.append(t, sender.OwnerName() + " shouts in anger and immediately starts trying to get free.");
						}
					}
				}
			}
			sender.removeActivity(Project.Supine, null);
		} else if (this == Project.Escape) {
			if (sender == w.lordBody) {
				//currently impossible
			} else {
				if (sender.getObedience() > 66) {
					if (sender.getMorality() > 66) {
						w.append(t, sender.OwnerName() + " cries out in despair as " + sender.heShe() + " turns and crawls away from " + receiver.ownerName() + ", already consumed by self-loathing for " + sender.hisHer() + " failure to properly serve " + sender.theDemonLord() + ".");
					} else if (sender.getMorality() > 33) {
						w.append(t, sender.OwnerName() + " abruptly starts struggling to escape " + receiver.ownerName() + ", and it's only after " + sender.heShe() + "'s free that " + sender.heShe() + " hangs " + sender.hisHer() + " head in shame for being so disobedient.");
					} else {
						w.append(t, sender.OwnerName() + " shoves " + receiver.ownerName() + " and scrambles away, briefly forgetting " + sender.hisHer() + " devotion in the face of " + sender.hisHer() + " intense emotions.");
					}
				} else if (sender.getObedience() > 33) {
					if (sender.getInnocence() > 66) {
						w.append(t, sender.OwnerName() + " cries out in panic and shoves " + receiver.ownerName() + " away.  Only a moment later does " + sender.heShe() + " realize what " + sender.heShe() + "'s done, and " + sender.heShe() + " cowers in fear of punishment.");
					} else if (sender.getInnocence() > 33) {
						w.append(t, "Apologizing profusely, " + sender.ownerName() + " disentangles " + sender.himHer() + "self from " + receiver.ownerName() + " and takes a few steps backward.");
					} else {
						w.append(t, sender.OwnerName() + " clearly states that " + sender.heShe() + "'s done with this and moves to free " + sender.himHer() + "self.  The quaver in " + sender.hisHer() + " voice is the only thing that betrays " + sender.hisHer() + " worries about the consequences of rejecting " + sender.theDemonLord() + ".");
					}
				} else {
					if (sender.getConfidence() > 66) {
						w.append(t, sender.OwnerName() + " starts to viciously beat " + receiver.ownerName() + ", remaining hostile even after " + sender.heShe() + " frees " + sender.himHer() + "self.");
					} else if (sender.getConfidence() > 33) {
						if (sender.imprisoned) {
							w.append(t, sender.OwnerName() + ", overwhelmed by " + sender.hisHer() + " hatred for the Demon Lord, starts struggling to get away before " + sender.heShe() + " knows what " + sender.heShe() + "'s doing.  However, " + sender.heShe() + " stands tall afterward, regaining " + sender.hisHer() + " poise as if daring " + receiver.ownerName() + " to punish " + sender.himHer() + ".");
						} else {
							w.append(t, sender.OwnerName() + ", overwhelmed by " + sender.hisHer() + " hatred for the Demon Lord, starts struggling to get away before " + sender.heShe() + " knows what " + sender.heShe() + "'s doing.  However, " + sender.heShe() + " stands tall afterward, regaining " + sender.hisHer() + " poise as if daring " + receiver.ownerName() + " to do " + receiver.hisHer() + " worst.");
						}
					} else {
						w.append(t, sender.OwnerName() + " flails in panic, then makes an effort to run away.");
					}
				}
			}
		} else if (this == Project.StopActing) {
			if (sender == w.lordBody) {
				//currently impossible
			} else {
				if (sender.getObedience() > 66) {
					if (sender.getMorality() > 66) {
						w.append(t, sender.OwnerName() + " confesses that " + sender.heShe() + "'s grown too tired to keep servicing " + receiver.ownerName() + ", then eagerly proposes that " + receiver.ownerName() + " punish " + sender.himHer() + " for " + sender.hisHer() + " weakness.");
					} else if (sender.getMorality() > 33) {
						w.append(t, sender.OwnerName() + " keeps trying to service " + receiver.ownerName() + " until " + sender.hisHer() + " own body starts to grow too exhausted to move.  Eventually, " + sender.heShe() + " realizes that " + sender.heShe() + "'s too tired to effectively please " + receiver.ownerName() + " anymore.");
					} else {
						w.append(t, sender.OwnerName() + " is sincere about wanting to service " + receiver.ownerName() + ", but " + sender.heShe() + " begins getting lazier and lazier with " + sender.hisHer() + " movements until " + sender.heShe() + "'s not actually making " + receiver.ownerName() + " feel good at all.");
					}
				} else if (sender.getObedience() > 33) {
					if (sender.getDeviancy() > 66) {
						w.append(t, sender.OwnerName() + " heaves a satisfied sigh, " + sender.hisHer() + " tremendous lust finally satisfied.  " + sender.HeShe() + " doesn't seem interested in continuing for now.");
					} else if (sender.getDeviancy() > 33) {
						w.append(t, sender.OwnerName() + " gradually starts to grow less and less interested in pleasuring " + receiver.ownerName() + ", and finally " + sender.heShe() + " stops completely.");
					} else {
						if (sender.imprisoned) {
							w.append(t, sender.OwnerName() + " starts to get distracted and make excuses for why " + sender.heShe() + " should be allowed to leave, and " + sender.heShe() + " stops trying to pleasure " + receiver.ownerName() + ".");
						} else {
							w.append(t, sender.OwnerName() + " starts to get distracted by thoughts of other things " + sender.heShe() + " could be doing right now, and " + sender.heShe() + " stops trying to pleasure " + receiver.ownerName() + ".");
						}
					}
				} else {
					if (sender.getInnocence() > 66) {
						w.append(t, sender.OwnerName() + " abruptly seems to remember that " + receiver.ownerName() + " is the enemy, and " + sender.heShe() + " refuses to continue this any longer.");
					} else if (sender.getInnocence() > 33) {
						w.append(t, sender.OwnerName() + " gets angry and refuses to cooperate further.  " + sender.HeShe() + " turns " + sender.hisHer() + " head away, refusing to even look at " + receiver.ownerName() + ".");
					} else {
						w.append(t, sender.OwnerName() + ", deciding that " + sender.heShe() + "'s cooperated long enough, halts " + sender.hisHer() + " movements and glares at " + receiver.ownerName() + " as if daring " + receiver.himHer() + " to punish " + sender.himHer() + ".");
					}
				}
			}
		} else if (this == Project.TieUp) {
			if (sender == w.lordBody) {
				w.append(t, sender.OwnerName() + " ties down " + receiver.ownerName() + "'s arms and legs, leaving " + receiver.himHer() + " helpless.");
			} else {
				//currently impossible
			}
		} else if (this == Project.BeTied) {
			if (sender == w.lordBody) {
				//currently impossible
			} else {
				if (sender.getObedience() > 66) {
					if (sender.getInnocence() > 66) {
						w.append(t, sender.OwnerName() + "'s eyes glaze over with pure bliss as " + sender.heShe() + " enjoys entrusting " + sender.hisHer() + " body to " + receiver.ownerName() + ".");
					} else if (sender.getInnocence() > 33) {
						w.append(t, sender.OwnerName() + " doesn't resist in the slightest, happy to let " + receiver.ownerName() + " enjoy " + sender.hisHer() + " body however " + receiver.heShe() + " wishes.");
					} else {
						w.append(t, sender.OwnerName() + " happily cooperates, holding " + sender.hisHer() + " limbs as " + sender.heShe() + "'s directed in order to help speed the process.");
					}
				} else if (sender.coerced() == false) {
					if (sender.getInnocence() > 66) {
						w.append(t, sender.OwnerName() + " briefly panics as " + sender.heShe() + "'s tied up, but " + sender.heShe() + " soon remembers that " + sender.heShe() + "'s one of the Chosen and begins to draw on " + sender.hisHer() + " powers to escape.");
					} else if (sender.getInnocence() > 33) {
						w.append(t, sender.OwnerName() + " is caught by surprise and unable to avoid getting tangled up in " + receiver.ownerName() + "'s bindings, but " + sender.hisHer() + " Chosen powers will let " + sender.himHer() + " escape in a matter of moments.");
					} else {
						w.append(t, sender.OwnerName() + " seems more curious than angry as " + receiver.ownerName() + " starts to tie " + sender.himHer() + " up, but " + sender.heShe() + " still begins drawing on " + sender.hisHer() + " Chosen powers so that " + sender.heShe() + "'ll be able to escape on a moment's notice.");
					}
				} else if (sender.getObedience() > 33) {
					if (sender.getConfidence() > 66) {
						w.append(t, sender.OwnerName() + " holds " + sender.hisHer() + " head high as " + sender.heShe() + "'s tied up, refusing to let " + sender.himHer() + "self show fear.");
					} else if (sender.getConfidence() > 33) {
						w.append(t, sender.OwnerName() + " flinches in trepidation, imagining what " + receiver.ownerName() + " will be doing to " + sender.himHer() + " next.");
					} else {
						w.append(t, sender.OwnerName() + " starts to panic, but " + sender.heShe() + "'s too frightened to actively resist.");
					}
				} else {
					if (sender.getDeviancy() > 66) {
						w.append(t, sender.OwnerName() + " tries to resist, but " + sender.hisHer() + " lustful body betrays " + sender.himHer() + ", and by the time " + sender.heShe() + "'s stopped daydreaming about what kind of sexual punishment awaits " + sender.himHer() + ", " + sender.heShe() + "'s already been tied up.");
					} else if (sender.getDeviancy() > 33) {
						w.append(t, sender.OwnerName() + " puts up a bit of resistance.  However, " + sender.heShe() + "'s weaker than " + sender.heShe() + " should be, and ");
						if (sender.parts[PENIS] > 0) {
							w.append(t, "the stiffness between " + sender.hisHer() + " legs");
						} else {
							w.append(t, "the wetness on " + sender.hisHer() + " thighs");
						}
						w.append(t, " shows that a part of " + sender.himHer() + " is eager to be punished.");
					} else {
						w.append(t, sender.OwnerName() + " fights back with all " + sender.hisHer() + " might, but " + sender.heShe() + " can't win against " + receiver.ownerName() + ".  Soon, there's nothing " + sender.heShe() + " can do but rock back and forth while growling at " + sender.hisHer() + " captor.");
					}
				}
			}
		} else if (this == Project.StrokeCock) {
			if (sender == w.lordBody) {
				w.append(t, sender.OwnerName() + " begins to pump " + sender.hisHer() + " hand up and down " + receiver.ownerName() + "'s cock.");
			} else {
				w.append(t, sender.capitalizedOwnerName() + " ");
				if (sender.getDeviancy() > 66) {
					if (sender.getInnocence() > 66) {
						w.append(t, "loses " + sender.himHer() + "self in the act of running " + sender.hisHer() + " hand up and down " + receiver.ownerName() + "'s cock, eyes glazed over and drooling with desire.");
					} else if (sender.getInnocence() > 33) {
						w.append(t, "reaches eagerly for " + receiver.ownerName() + "'s cock, pumping it up and down with an aggressive firmness that comes just short of being painful.");
					} else {
						w.append(t, "abruptly wraps " + sender.hisHer() + " fingers around " + receiver.ownerName() + "'s cock and starts stroking it gently, then fondling the tip, then gently tugging on it, using every technique at " + sender.hisHer() + " disposal.");
					}
				} else if (sender.getDeviancy() > 33) {
					if (sender.getInnocence() > 66) {
						w.append(t, "tugs and jerks " + receiver.ownerName() + "'s cock, then gradually gets into a rhythm of stroking it as " + receiver.heShe() + " grows more confident in what " + receiver.heShe() + "'s doing.");
					} else if (sender.getInnocence() > 33) {
						w.append(t, "brushes " + sender.hisHer() + " fingers against " + receiver.ownerName() + "'s cock, trailing them up and down, then growing more firm, stroking it in earnest.");
					} else {
						w.append(t, "carefully starts to stroke " + receiver.ownerName() + "'s cock, staring into " + receiver.hisHer() + " eyes to gauge " + receiver.hisHer() + " reaction.");
					}
				} else {
					if (sender.getInnocence() > 66) {
						w.append(t, "hesitantly touches " + receiver.ownerName() + "'s cock, then begins to clumsily stroke it, averting " + sender.hisHer() + " eyes as " + sender.heShe() + " does so.  ");
					} else if (sender.getInnocence() > 33) {
						w.append(t, "begins to stroke " + receiver.ownerName() + "'s cock with stiff, mechanical movements.  ");
					} else {
						w.append(t, "brings " + sender.hisHer() + " hand to " + receiver.ownerName() + "'s cock and begins to stroke it with precise, methodical movements.  ");
					}
					if (sender.getObedience() > 66) {
						w.append(t, "It's clear that " + sender.heShe() + "'s unfamiliar with this sort of thing, but " + sender.heShe() + "'s doing " + sender.hisHer() + " best for " + receiver.ownerName() + ".");
					} else if (sender.getObedience() > 33) {
						w.append(t, sender.HisHer() + " heart clearly isn't in it.");
					} else {
						w.append(t, sender.HeShe() + " looks sickened by what " + sender.heShe() + "'s doing.");
					}
				}
			}
		} else if (this == Project.CockStroked) {
			if (sender == w.lordBody) {
				w.append(t, sender.OwnerName() + "'s cock pulses under " + receiver.ownerName() + "'s fingers.");
			} else {
				w.append(t, sender.capitalizedOwnerName());
				if (sender.getInnocence() > 66) {
					w.append(t, " gasps at the sudden intensity of the stimulation to " + sender.hisHer() + " most sensitive part, and ");
					if (sender.getDeviancy() > 66) {
						w.append(t, sender.hisHer() + " eyes glaze over as " + sender.heShe() + " loses the ability to think of anything other than feeling even better.  ");
					} else if (sender.getDeviancy() > 33) {
						w.append(t, sender.hisHer() + " hips jerk wildly, seemingly caught between jerking away and pushing further into " + receiver.ownerName() + "'s hand.  ");
					} else {
						w.append(t, sender.heShe() + " reflexively tries to jerk " + sender.hisHer() + " hips away.  ");
					}
				} else if (sender.getInnocence() > 33) {
					w.append(t, " feels jolts of pleasure shooting through " + sender.hisHer() + " shaft");
					if (sender.getDeviancy() > 66) {
						w.append(t, ", and " + sender.heShe() + " moans helplessly, " + sender.hisHer() + " well-trained body eagerly submitting itself to " + receiver.ownerName() + "'s touch.  ");
					} else if (sender.getDeviancy() > 33) {
						w.append(t, ", and it's a struggle for " + sender.himHer() + " to hold onto " + sender.hisHer() + " reason.  ");
					} else {
						w.append(t, ", even as " + sender.heShe() + " tries to ignore them.  ");
					}
				} else {
					w.append(t, " knew this was coming, but " + sender.heShe() + " still ");
					if (sender.getDeviancy() > 66) {
						w.append(t, "squirms and cries out, unable to maintain any self-control in the face of the pleasure " + sender.heShe() + "'s come to crave.  ");
					} else if (sender.getDeviancy() > 33) {
						w.append(t, "gasps softly at the waves of pleasure that begin to wash over " + sender.hisHer() + " lower body.  ");
					} else {
						w.append(t, "flinches and grimaces, uncomfortable with the intensity of the sensations.  ");
					}
				}
				if (receiver == w.lordBody) {
					if (sender.getDeviancy() > 66) {
						if (sender.getObedience() > 66) {
							w.append(t, sender.HisHer() + " body has grown quite sensitive, but it's even more sensitive than usual when " + sender.heShe() + " knows " + sender.heShe() + "'s being touched by " + receiver.ownerName() + ".");
						} else if (sender.getObedience() > 33) {
							w.append(t, "Soon, " + sender.heShe() + "'s completely consumed in " + sender.hisHer() + " efforts to hump " + receiver.ownerName() + "'s hand.");
						} else {
							w.append(t, sender.HeShe() + " tries to resist, but " + sender.hisHer() + " body has grown so sensitive that the lightest touch to " + sender.hisHer() + " weak spots is enough to subdue " + sender.himHer() + ".");
						}
					} else if (sender.getDeviancy() > 33) {
						if (sender.getObedience() > 66) {
							w.append(t, "Even more than the physical stimulation itself, " + sender.ownerName() + " is ecstatic that " + receiver.ownerName() + " is pleasuring " + sender.himHer() + " directly.");
						} else if (sender.getObedience() > 33) {
							w.append(t, sender.HeShe() + "'s content to let " + receiver.ownerName() + " pleasure " + sender.himHer() + ".");
						} else {
							w.append(t, sender.HeShe() + " has a hard time remembering that " + sender.heShe() + "'s supposed to hate the Demon Lord.");
						}
					} else {
						if (sender.getObedience() > 66) {
							w.append(t, sender.HeShe() + " endures this for " + receiver.ownerName() + "'s sake, hoping that " + receiver.ownerName() + " will enjoy playing with " + sender.hisHer() + " body.");
						} else if (sender.getObedience() > 33) {
							w.append(t, sender.HeShe() + " doesn't make eye contact with " + receiver.ownerName() + ", glancing off to the side.");
						} else {
							w.append(t, sender.HeShe() + " glares at " + receiver.ownerName() + ", not appreciating it in the slightest.");
						}
					}
				}
			}
		} else if (this == Project.Lubricate) {
			if (sender == w.lordBody) {
				w.append(t, sender.OwnerName() + " gathers a generous amount of slippery lubricant on one finger, then uses it to coat " + receiver.ownerName() + "'s anus.");
			} else {
				//currently impossible
			}
		} else if (this == Project.BeLubricated) {
			if (sender == w.lordBody) {
				//currently impossible
			} else {
				if (sender.getDeviancy() > 66) {
					if (sender.getObedience() > 66) {
						w.append(t, sender.OwnerName() + " moans and spreads " + sender.hisHer() + " legs as wide as " + sender.heShe() + " can, eager for more than just a finger.");
					} else if (sender.getObedience() > 33) {
						w.append(t, sender.OwnerName() + " looks nervous, but also eager.  " + sender.HeShe() + " grins down at " + sender.himHer() + "self, imagining what will be done to " + sender.himHer() + " next.");
					} else {
						w.append(t, sender.OwnerName() + " tries to keep acting tough, but " + sender.heShe() + " breaks down into a stream of pitiful moans and halfhearted protests as the light stimulation is enough to break " + sender.hisHer() + " concentration.");
					}
				} else if (sender.getDeviancy() > 33) {
					if (sender.getDignity() > 66) {
						w.append(t, sender.OwnerName() + " inhales sharply, but doesn't show any other sign that " + sender.heShe() + "'s feeling anything from this.");
					} else if (sender.getDignity() > 33) {
						w.append(t, sender.OwnerName() + " squirms and blushes, clearly a bit ashamed at being excited by the stimulation back there.");
					} else {
						w.append(t, sender.OwnerName() + " groans and reflexively tries to buck " + sender.hisHer() + " hips down onto the finger.");
					}
				} else {
					if (sender.getInnocence() > 66) {
						w.append(t, sender.OwnerName() + " squirms in discomfort at the strange feeling in a place " + sender.heShe() + " doesn't even mentally associate with sex.");
					} else if (sender.getInnocence() > 33) {
						w.append(t, sender.OwnerName() + "'s eyes go wide and " + sender.heShe() + " tries to look down at what's happening down there.");
					} else {
						w.append(t, sender.OwnerName() + " shudders at the unpleasant sensation, then tries to relax and accept it.");
					}
				}
			}
		} else if (this == Project.VaginalPenetrate) {
			if (Project.PushDown.isInProgress(sender, receiver)) {
				if (sender == w.lordBody) {
					w.append(t, sender.OwnerName() + " puts the tip of " + sender.hisHer() + " cock against " + receiver.ownerName() + "'s pussy, then thrusts inside.");
				} else if (sender.getDeviancy() > 66) {
					if (sender.getConfidence() > 66) {
						w.append(t, sender.OwnerName() + " eagerly pins " + receiver.ownerName() + " down and shoves " + sender.hisHer() + " shaft into " + receiver.ownerName() + "'s pussy, immediately setting a rapid pace of thrusting in and out.  It's a struggle to keep moving steadily with the flashes of ecstasy shooting through " + sender.himHer() + ", but " + sender.ownerName() + " is determined to keep going.");
					} else if (sender.getConfidence() > 33) {
						w.append(t, sender.OwnerName() + " starts to slowly push " + sender.hisHer() + " cock into " + receiver.ownerName() + ", but the surge of pleasure from having just the tip inside overwhelms " + sender.hisHer() + " reason.  " + sender.HeShe() + " jerks " + sender.hisHer() + " hips forward, burying " + sender.himHer() + "self inside " + receiver.ownerName() + "'s pussy, then pulls halfway out, then pushes back in, hips jerking wildly as intense pleasure surges through " + sender.himHer() + " with every movement.");
					} else {
						w.append(t, sender.OwnerName() + "'s timid nature seems to vanish entirely, and " + sender.heShe() + " starts to ram " + sender.hisHer() + " hips forward with wild abandon, hammering in and out of " + receiver.ownerName() + "'s pussy.");
					}
				} else if (sender.getDeviancy() > 33) {
					if (sender.getInnocence() > 66) {
						w.append(t, sender.OwnerName() + " pushes " + sender.hisHer() + " cock into " + receiver.ownerName() + "'s pussy, and the moment " + sender.heShe() + " feels " + receiver.hisHer() + " folds squeezing down on " + sender.himHer() + ", " + sender.heShe() + " loses the ability to think of anything but the pleasure.  Moaning softly, " + sender.heShe() + " starts thrusting in and out, acting on pure instinct.");
					} else if (sender.getInnocence() > 33) {
						w.append(t, sender.OwnerName() + " starts to fuck " + receiver.ownerName() + "'s pussy, but the pleasure of " + receiver.hisHer() + " folds squeezing down on " + sender.himHer() + " cock gives " + sender.ownerName() + " pause.  " + sender.HeShe() + " gasps, taking a moment to catch " + sender.hisHer() + " breath, then continues more slowly, enjoying the warm wetness around " + sender.hisHer() + " shaft.");
					} else {
						w.append(t, sender.OwnerName() + " gradually pushes " + sender.hisHer() + " cock into " + receiver.ownerName() + "'s pussy, inch-by-inch, then withdraws it just as carefully.  " + sender.HeShe() + " moves slowly, feeling out which spots feel better for " + sender.himHer() + " and which seem to get more of a reaction from " + receiver.ownerName() + ", and only then starts to move more quickly, getting into a rhythm.");
					}
				} else {
					if (sender.getObedience() > 66) {
						w.append(t, sender.OwnerName() + " eagerly thrusts " + sender.hisHer() + " cock inside " + receiver.ownerName() + "'s pussy, ecstatic to be joined with " + receiver.himHer() + ".  " + sender.HeShe() + " moves unselfishly, trying to hit all of " + receiver.ownerName() + "'s sensitive parts without any regard for " + sender.hisHer() + " own pleasure.");
					} else if (sender.getObedience() > 33) {
						w.append(t, sender.OwnerName() + " presses the tip of " + sender.hisHer() + " cock against " + receiver.ownerName() + "'s pussy, hesitates for a moment, and then finally pushes it inside.  " + sender.HeShe() + " gasps and shudders at the pleasure, far beyond what " + sender.heShe() + " was prepared for, and then cautiously starts to move " + sender.hisHer() + " hips forward and back.");
					} else {
						w.append(t, sender.OwnerName() + "'s angry demeanor fades for just a moment as " + sender.heShe() + " thrusts into " + receiver.ownerName() + " and feels the intense pleasure of " + receiver.hisHer() + " folds squeezing " + sender.himHer() + ".  But then " + sender.heShe() + " recovers and starts to move violently in and out, as if stabbing " + receiver.ownerName() + " with a weapon.");
					}
				}
			} else {
				if (sender == w.lordBody) {
					w.append(t, sender.OwnerName() + "'s cock pushes all the way into " + receiver.ownerName() + "'s depths.");
				} else if (sender.getDeviancy() > 66) {
					if (sender.getInnocence() > 66) {
						w.append(t, sender.OwnerName() + "'s whole body goes slack except for " + sender.hisHer() + " hips desperately trying to thrust deeper into " + receiver.himHer() + ".  " + sender.HisHer() + " eyes roll and " + sender.hisHer() + " tongue hangs out.");
					} else if (sender.getInnocence() > 33) {
						w.append(t, "The feeling of " + receiver.ownerName() + " sliding down to the base of " + sender.hisHer() + " shaft is enough to make " + sender.ownerName() + " cry out and lose " + sender.hisHer() + " sense of reason.  " + sender.HeShe() + " desperately clings to " + receiver.ownerName() + ", hips jerking wildly up and down as " + sender.heShe() + " tries to thrust even deeper into " + receiver.himHer() + ".");
					} else {
						w.append(t, "At first, " + sender.ownerName() + " tries to reciprocate with " + sender.hisHer() + " own thrusts.  But it soon becomes clear that " + sender.heShe() + "'s completely at " + receiver.ownerName() + "'s mercy, and all " + sender.heShe() + " can do is moan and jerk " + sender.hisHer() + " hips as " + receiver.ownerName() + " slides up and down " + sender.hisHer() + " shaft.");
					}
				} else if (sender.getDeviancy() > 33) {
					if (sender.getDignity() > 66) {
						w.append(t, sender.OwnerName() + " tries to hide just how good it feels, but when " + receiver.ownerName() + " clenches around " + sender.himHer() + ", " + sender.heShe() + " gasps and reflexively bucks " + sender.hisHer() + " hips.  After that, " + sender.heShe() + " has a harder and harder time suppressing " + sender.hisHer() + " moans.");
					} else if (sender.getDignity() > 33) {
						w.append(t, sender.OwnerName() + " lays back and loses " + sender.himHer() + "self in the feeling of " + receiver.ownerName() + " sliding up and down " + sender.hisHer() + " length, overwhelmed by the intensity of the sensations.");
					} else {
						w.append(t, sender.OwnerName() + " gasps with pleasure, reflexively embracing " + receiver.ownerName() + " and thrusting " + sender.hisHer() + " own hips in time with " + receiver.ownerName() + "'s movements.");
					}
				} else {
					if (sender.getObedience() > 66) {
						w.append(t, sender.OwnerName() + " winces and struggles not to cum right away, wanting to ensure that " + sender.heShe() + " stays hard for " + receiver.ownerName() + " as long as possible.");
					} else if (sender.getObedience() > 33) {
						w.append(t, sender.OwnerName() + " tries to relax and enjoy it, but even as " + receiver.ownerName() + " slides up and down " + sender.hisHer() + " shaft, " + sender.ownerName() + " can't quite shake off the fear that this pleasure is just meant to make " + sender.himHer() + " let " + sender.hisHer() + " guard down.");
					} else {
						w.append(t, sender.OwnerName() + " struggles to keep glaring at " + receiver.ownerName() + ", wincing slightly every time " + receiver.ownerName() + " squeezes the base of " + sender.hisHer() + " shaft and breaks " + sender.hisHer() + " concentration.");
					}
				}
			}
		} else if (this == Project.PenetratedVaginally) {
			if (Project.PushDown.isInProgress(sender, receiver)) {
				if (sender == w.lordBody) {
					w.append(t, sender.OwnerName() + " mounts " + receiver.ownerName() + " and lowers " + sender.himHer() + "self until " + sender.hisHer() + " pussy envelops " + receiver.ownerName() + "'s cock.");
				} else {
					if (sender.getDeviancy() > 66) {
						if (sender.getObedience() > 66) {
							w.append(t, sender.OwnerName() + " eagerly impales " + sender.hisHer() + " pussy on " + receiver.ownerName() + "'s upright cock, sliding " + sender.himHer() + "self up and down with manic energy while grinning at " + receiver.himHer() + ".");
						} else if (sender.getObedience() > 33) {
							w.append(t, sender.OwnerName() + " quickly lowers " + sender.himHer() + "self onto " + receiver.ownerName() + "'s cock, then immediately begins to bounce on " + receiver.hisHer() + " lap, searching for the precise angle where it pokes just the right spot inside " + sender.hisHer() + " pussy.");
						} else {
							w.append(t, sender.OwnerName() + " tries to resist " + sender.hisHer() + " urges, but this close to " + receiver.ownerName() + "'s cock, " + sender.heShe() + " can't stop " + sender.himHer() + "self from grinding against it.  Then, before " + sender.heShe() + " realizes it, " + sender.heShe() + "'s taken it into " + sender.hisHer() + " pussy.  The surge of pleasure destroys the last of " + sender.hisHer() + " reason, and " + sender.heShe() + " starts moving in earnest.");
						}
					} else if (sender.getDeviancy() > 33) {
						if (sender.getConfidence() > 66) {
							w.append(t, sender.OwnerName() + " pins " + receiver.ownerName() + " down and takes " + receiver.ownerName() + "'s cock into " + sender.hisHer() + " pussy.  " + sender.HeShe() + " grimaces slightly at the sudden insertion, but wastes no time in bucking " + sender.hisHer() + " hips with savage force, causing the discomfort to melt away into pleasure.");
						} else if (sender.getConfidence() > 33) {
							w.append(t, sender.OwnerName() + " straddles " + receiver.ownerName() + ", touching " + sender.hisHer() + " pussy against the tip of " + receiver.ownerName() + "'s cock, then slowly lowering " + sender.himHer() + "self downward onto it, bit by bit.  " + sender.HeShe() + " gasps and twitches as it bottoms out inside " + sender.himHer() + ".");
						} else {
							w.append(t, sender.OwnerName() + " is nervous about lowering " + sender.himHer() + "self onto " + receiver.ownerName() + "'s cock, but " + sender.heShe() + " gasps with pleasure as it goes inside " + sender.hisHer() + " pussy and immediately hits a sensitive spot.  " + sender.HeShe() + " moans and begins moving " + sender.himHer() + "self up and down with genuine enthusiasm.");
						}
					} else {
						if (sender.getDignity() > 66) {
							w.append(t, sender.OwnerName() + " is blushing bright red at having to be the one to lower " + sender.himHer() + "self onto " + receiver.ownerName() + "'s cock, but " + sender.heShe() + " pretends that it doesn't bother " + sender.himHer() + ".  " + sender.HeShe() + " just flinches as it spreads " + sender.hisHer() + " pussy open, then stifles a moan when it goes all the way inside.");
						} else if (sender.getDignity() > 33) {
							w.append(t, sender.OwnerName() + " delays as long as possible before taking " + receiver.ownerName() + "'s cock into " + sender.hisHer() + " pussy.  " + sender.HeShe() + " tries various angles, slides " + sender.himHer() + "self against it several times, and only then does " + sender.heShe() + " finally lower " + sender.himHer() + "self onto it.  When " + sender.heShe() + " does, " + sender.heShe() + " lets out a little moan, uncomfortable with just how intense the pleasure is.");
						} else {
							w.append(t, sender.OwnerName() + " doesn't waste any time before slamming " + sender.hisHer() + " hips down onto " + receiver.ownerName() + "'s cock.  However, " + sender.heShe() + " groans with discomfort at the sudden insertion, and " + sender.heShe() + " needs a few moments to recover before " + sender.heShe() + " can start moving.");
						}
					}
				}
				if (sender.isVVirg()) {
					if (sender.getObedience() > 66) {
						w.append(t, "  " + sender.HeShe() + " embraces the pain of " + sender.hisHer() + " first vaginal penetration, ecstatic to have received it from " + receiver.ownerName() + ".");
					} else if (sender.getObedience() > 33) {
						w.append(t, "  " + sender.HeShe() + " looks down at the trickle of blood on " + sender.hisHer() + " thigh with an expression of resignation.");
					} else {
						w.append(t, "  " + sender.HeShe() + " lifts " + sender.himHer() + "self up enough to look down and see the blood on " + receiver.ownerName() + "'s cock, then grits " + sender.hisHer() + " teeth.");
					}
				}
			} else {
				if (sender == w.lordBody) {
					w.append(t, sender.OwnerName() + "'s pussy tightens around " + receiver.ownerName() + "'s cock as though trying to milk it dry.");
				} else {
					if (sender.getObedience() > 66) {
						if (sender.getDeviancy() > 66) {
							w.append(t, sender.OwnerName() + " goes wild with joyful lust, bucking " + sender.hisHer() + " hips wildly and enjoying every moment of being taken by " + receiver.ownerName() + ".");
						} else if (sender.getDeviancy() > 33) {
							w.append(t, sender.OwnerName() + " tries to help " + receiver.ownerName() + " feel good as well, moving " + sender.hisHer() + " hips and urging " + receiver.himHer() + " deeper inside, but soon the pleasure overwhelms " + sender.himHer() + " and " + sender.heShe() + " can't think of anything but wanting to cum.");
						} else {
							w.append(t, sender.OwnerName() + " bites " + sender.hisHer() + " lip in concentration, trying to buck " + sender.hisHer() + " hips in turn and squeeze down with " + sender.hisHer() + " pussy in order to make it feel as good as possible for " + receiver.ownerName() + ".");
						}
					} else if (sender.getObedience() > 33) {
						if (sender.getConfidence() > 66) {
							w.append(t, sender.OwnerName() + " doesn't look entirely happy to be on the bottom, but the pleasure of each thrust hitting " + sender.hisHer() + " deepest places soon makes " + sender.himHer() + " forget all about that and start crying out in pleasure.");
						} else if (sender.getConfidence() > 33) {
							w.append(t, "At first, " + sender.ownerName() + " tolerates it with an expression of resignation.  However, as the pleasure builds, " + sender.heShe() + " starts to gasp with passion and then actively move " + sender.hisHer() + " hips.");
						} else {
							w.append(t, sender.OwnerName() + " is too nervous to move much, worried about doing something to displease " + receiver.ownerName() + ".  Whimpering moans of pleasure begin to leak out of " + sender.hisHer() + " throat.");
						}
					} else {
						if (sender.getInnocence() > 66) {
							w.append(t, sender.OwnerName() + " tries to kick and scream, but " + sender.hisHer() + " movements only drive " + receiver.ownerName() + " deeper inside " + sender.himHer() + ", and " + sender.hisHer() + " voice takes on a passionate quality.");
						} else if (sender.getInnocence() > 33) {
							w.append(t, sender.OwnerName() + " attempts to deny " + receiver.ownerName() + " the satisfaction of seeing " + sender.hisHer() + " reactions, but the longer the fucking continues, the more difficult it is.  Soon " + sender.heShe() + "'s reduced to covering " + sender.hisHer() + " face and biting " + sender.hisHer() + " lip to stifle " + sender.hisHer() + " moans.");
						} else {
							w.append(t, sender.OwnerName() + " glares up at " + receiver.ownerName() + ", and " + sender.hisHer() + "  hatred comes through clearly even as " + sender.heShe() + " winces and groans at the stimulation to " + sender.hisHer() + " most sensitive inner places.");
						}
					}
				}
				if (sender.isVVirg()) {
					if (sender.getObedience() > 66) {
						w.append(t, "  " + sender.HeShe() + " smiles broadly up at " + receiver.ownerName() + ", tears of joy leaking from " + sender.hisHer() + " eyes at being able to give " + sender.hisHer() + " first time to the one " + sender.heShe() + " loves.");
					} else if (sender.getObedience() > 33) {
						w.append(t, "  When " + sender.heShe() + " sees the blood on " + receiver.ownerName() + "'s cock, " + sender.heShe() + " flinches away, as if trying to ignore it.");
					} else {
						w.append(t, "  " + sender.HisHer() + " anger drowns out the pain of having " + sender.hisHer() + " hymen torn.");
					}
				}
			}
		} else if (this == Project.AnalPenetrate) {
			if (Project.PushDown.isInProgress(sender, receiver)) {
				if (sender == w.lordBody) {
					w.append(t, sender.OwnerName() + " puts the tip of " + sender.hisHer() + " cock against " + receiver.ownerName() + "'s anus, then thrusts inside.");
				} else if (sender.getDeviancy() > 66) {
					if (sender.getConfidence() > 66) {
						w.append(t, sender.OwnerName() + " eagerly pins " + receiver.ownerName() + " down and shoves " + sender.hisHer() + " shaft all the way up " + receiver.ownerName() + "'s ass, immediately setting a rapid pace of thrusting in and out.  It's a struggle to keep moving steadily with the flashes of pleasure shooting through " + sender.himHer() + ", but " + sender.ownerName() + " is determined to keep going.");
					} else if (sender.getConfidence() > 33) {
						w.append(t, sender.OwnerName() + " starts to slowly push " + sender.hisHer() + " cock up " + receiver.ownerName() + "'s ass, but the surge of pleasure from the sphincter squeezing " + sender.hisHer() + " tip overwhelms " + sender.hisHer() + " reason.  " + sender.HeShe() + " jerks " + sender.hisHer() + " hips forward, burying " + sender.himHer() + "self all the way inside, then pulls halfway out, then pushes back in, hips jerking wildly as intense ecstasy surges through " + sender.himHer() + " with every movement.");
					} else {
						w.append(t, sender.OwnerName() + "'s timid nature seems to vanish entirely, and " + sender.heShe() + " starts to ram " + sender.hisHer() + " hips forward with wild abandon, hammering in and out of " + receiver.ownerName() + "'s asshole.");
					}
				} else if (sender.getDeviancy() > 33) {
					if (sender.getInnocence() > 66) {
						w.append(t, sender.OwnerName() + " pushes " + sender.hisHer() + " cock up " + receiver.ownerName() + "'s ass, and the moment " + sender.heShe() + " feels " + receiver.hisHer() + " rear entrance squeezing down on " + sender.himHer() + ", " + sender.heShe() + " loses the ability to think of anything but the pleasure.  Moaning softly, " + sender.heShe() + " starts thrusting in and out, acting on pure instinct.");
					} else if (sender.getInnocence() > 33) {
						w.append(t, sender.OwnerName() + " starts to fuck " + receiver.ownerName() + "'s asshole, but the pleasure of " + receiver.hisHer() + " sphincter squeezing down on " + sender.himHer() + " cock gives " + sender.ownerName() + " pause.  " + sender.HeShe() + " gasps, taking a moment to catch " + sender.hisHer() + " breath, then continues more slowly, enjoying the incredible tightness around " + sender.hisHer() + " shaft.");
					} else {
						w.append(t, sender.OwnerName() + " gradually pushes " + sender.hisHer() + " cock up " + receiver.ownerName() + "'s ass, inch-by-inch, then withdraws it just as carefully.  " + sender.HeShe() + " moves slowly, feeling out which spots feel better for " + sender.himHer() + " and which seem to get more of a reaction from " + receiver.ownerName() + ", and only then starts to move more quickly, getting into a rhythm.");
					}
				} else {
					if (sender.getObedience() > 66) {
						w.append(t, sender.OwnerName() + " eagerly thrusts " + sender.hisHer() + " cock up " + receiver.ownerName() + "'s ass, ecstatic to be joined with " + receiver.himHer() + ".  " + sender.HeShe() + " moves unselfishly, trying to hit all of " + receiver.ownerName() + "'s sensitive parts without any regard for " + sender.hisHer() + " own pleasure.");
					} else if (sender.getObedience() > 33) {
						w.append(t, sender.OwnerName() + " presses the tip of " + sender.hisHer() + " cock against " + receiver.ownerName() + "'s anus, hesitates for a moment, and then finally pushes it inside.  " + sender.HeShe() + " gasps and shudders at the pleasure, far beyond what " + sender.heShe() + " was prepared for, and then cautiously starts to move " + sender.hisHer() + " hips forward and back.");
					} else {
						w.append(t, sender.OwnerName() + "'s angry demeanor fades for just a moment as " + sender.heShe() + " thrusts into " + receiver.ownerName() + " and feels the intense pleasure of " + receiver.hisHer() + " asshole squeezing " + sender.himHer() + ".  But then " + sender.heShe() + " recovers and starts to move violently in and out, as if stabbing " + receiver.ownerName() + " with a weapon.");
					}
				}
			} else {
				if (sender == w.lordBody) {
					w.append(t, sender.OwnerName() + "'s cock pushes all the way into " + receiver.ownerName() + "'s bowels.");
				} else if (sender.getDeviancy() > 66) {
					if (sender.getInnocence() > 66) {
						w.append(t, sender.OwnerName() + "'s whole body goes slack except for " + sender.hisHer() + " hips desperately trying to thrust deeper into " + receiver.himHer() + ".  " + sender.HisHer() + " eyes roll and " + sender.hisHer() + " tongue hangs out.");
					} else if (sender.getInnocence() > 33) {
						w.append(t, "The feeling of " + receiver.ownerName() + " sliding down to the base of " + sender.hisHer() + " shaft is enough to make " + sender.ownerName() + " cry out and lose " + sender.hisHer() + " sense of reason.  " + sender.HeShe() + " desperately clings to " + receiver.ownerName() + ", hips jerking wildly up and down as " + sender.heShe() + " tries to thrust even deeper into " + receiver.himHer() + ".");
					} else {
						w.append(t, "At first, " + sender.ownerName() + " tries to reciprocate with " + sender.hisHer() + " own thrusts.  But it soon becomes clear that " + sender.heShe() + "'s completely at " + receiver.ownerName() + "'s mercy, and all " + sender.heShe() + " can do is moan and jerk " + sender.hisHer() + " hips as " + receiver.ownerName() + " slides up and down " + sender.hisHer() + " shaft.");
					}
				} else if (sender.getDeviancy() > 33) {
					if (sender.getDignity() > 66) {
						w.append(t, sender.OwnerName() + " tries to hide just how good it feels, but when " + receiver.ownerName() + " clenches around " + sender.himHer() + ", " + sender.heShe() + " gasps and reflexively bucks " + sender.hisHer() + " hips.  After that, " + sender.heShe() + " has a harder and harder time suppressing " + sender.hisHer() + " moans.");
					} else if (sender.getDignity() > 33) {
						w.append(t, sender.OwnerName() + " lays back and loses " + sender.himHer() + "self in the feeling of " + receiver.ownerName() + " sliding up and down " + sender.hisHer() + " length, overwhelmed by the intensity of the sensations.");
					} else {
						w.append(t, sender.OwnerName() + " gasps with pleasure, reflexively embracing " + receiver.ownerName() + " and thrusting " + sender.hisHer() + " own hips in time with " + receiver.ownerName() + "'s movements.");
					}
				} else {
					if (sender.getObedience() > 66) {
						w.append(t, sender.OwnerName() + " winces and struggles not to cum right away, wanting to ensure that " + sender.heShe() + " stays hard for " + receiver.ownerName() + " as long as possible.");
					} else if (sender.getObedience() > 33) {
						w.append(t, sender.OwnerName() + " tries to relax and enjoy it, but even as " + receiver.ownerName() + " slides up and down " + sender.hisHer() + " shaft, " + sender.ownerName() + " can't quite shake off the fear that this pleasure is just meant to make " + sender.himHer() + " let " + sender.hisHer() + " guard down.");
					} else {
						w.append(t, sender.OwnerName() + " struggles to keep glaring at " + receiver.ownerName() + ", wincing slightly every time " + receiver.ownerName() + "'s anus squeezes the base of " + sender.hisHer() + " shaft and breaks " + sender.hisHer() + " concentration.");
					}
				}
			}
		} else if (this == Project.PenetratedAnally) {
			if (Project.PushDown.isInProgress(sender, receiver)) {
				if (sender == w.lordBody) {
					w.append(t, sender.OwnerName() + " mounts " + receiver.ownerName() + " and lowers " + sender.himHer() + "self until " + sender.hisHer() + " anus envelops " + receiver.ownerName() + "'s cock.");
				} else {
					if (Project.BeLubricated.isInProgress(sender, null) == false) {
						if (sender.getInnocence() > 66) {
							w.append(t, "Full of eager lust, " + sender.ownerName() + " almost forgets to lubricate " + sender.himHer() + "self back there before continuing.");
						} else if (sender.getInnocence() > 33) {
							w.append(t, "As " + sender.heShe() + " lays atop " + receiver.ownerName() + ", " + sender.ownerName() + " reaches back with one finger covered in lubricant to prepare for what comes next.");
						} else {
							w.append(t, "Before continuing, " + sender.ownerName() + " carefully applies some slippery lubricant to " + sender.hisHer() + " rear entrance.");
						}
						w.append(t, "\n\n");
						sender.addActivity(Project.BeLubricated, null);
					}
					if (sender.getDeviancy() > 66) {
						if (sender.getObedience() > 66) {
							w.append(t, sender.OwnerName() + " eagerly impales " + sender.hisHer() + " asshole on " + receiver.ownerName() + "'s upright cock, sliding " + sender.himHer() + "self up and down with manic energy while grinning at " + receiver.himHer() + ".");
						} else if (sender.getObedience() > 33) {
							w.append(t, sender.OwnerName() + " quickly lowers " + sender.himHer() + "self onto " + receiver.ownerName() + "'s cock, then immediately begins to bounce on " + receiver.hisHer() + " lap, searching for the precise angle where it pokes just the right spot inside " + sender.hisHer() + " bowels.");
						} else {
							w.append(t, sender.OwnerName() + " tries to resist " + sender.hisHer() + " urges, but this close to " + receiver.ownerName() + "'s cock, " + sender.heShe() + " can't stop " + sender.himHer() + "self from grinding against it.  Then, before " + sender.heShe() + " realizes it, " + sender.heShe() + "'s taken it up " + sender.hisHer() + " ass.  The surge of pleasure destroys the last of " + sender.hisHer() + " reason, and " + sender.heShe() + " starts moving in earnest.");
						}
					} else if (sender.getDeviancy() > 33) {
						if (sender.getConfidence() > 66) {
							w.append(t, sender.OwnerName() + " pins " + receiver.ownerName() + " down and takes " + receiver.ownerName() + "'s cock up " + sender.hisHer() + " ass.  " + sender.HeShe() + " grimaces slightly at the sensation of being spread wide open, but wastes no time in bucking " + sender.hisHer() + " hips with savage force, causing the discomfort to melt away into pleasure.");
						} else if (sender.getConfidence() > 33) {
							w.append(t, sender.OwnerName() + " straddles " + receiver.ownerName() + ", touching " + sender.hisHer() + " anus against the tip of " + receiver.ownerName() + "'s cock, then slowly lowering " + sender.himHer() + "self downward onto it, bit by bit.  " + sender.HeShe() + " gasps and twitches as it bottoms out inside " + sender.himHer() + ".");
						} else {
							w.append(t, sender.OwnerName() + " is nervous about lowering " + sender.himHer() + "self onto " + receiver.ownerName() + "'s cock, but " + sender.heShe() + " gasps with pleasure as it goes up " + sender.hisHer() + " ass and immediately hits a sensitive spot.  " + sender.HeShe() + " moans and begins moving " + sender.himHer() + "self up and down with genuine enthusiasm.");
						}
					} else {
						if (sender.getDignity() > 66) {
							w.append(t, sender.OwnerName() + " is blushing bright red at having to be the one to lower " + sender.himHer() + "self onto " + receiver.ownerName() + "'s cock, but " + sender.heShe() + " pretends that it doesn't bother " + sender.himHer() + ".  " + sender.HeShe() + " just flinches as it spreads " + sender.hisHer() + " anus open, then stifles a moan when it goes all the way inside.");
						} else if (sender.getDignity() > 33) {
							w.append(t, sender.OwnerName() + " delays as long as possible before taking " + receiver.ownerName() + "'s cock up " + sender.hisHer() + " ass.  " + sender.HeShe() + " tries various angles, grinds the tip against " + sender.hisHer() + " anus, and only then does " + sender.heShe() + " finally lower " + sender.himHer() + "self onto it.  When " + sender.heShe() + " does, " + sender.heShe() + " lets out a little moan, uncomfortable with just how intense the pleasure is.");
						} else {
							w.append(t, sender.OwnerName() + " doesn't waste any time before slamming " + sender.hisHer() + " ass down onto " + receiver.ownerName() + "'s cock.  However, " + sender.heShe() + " groans with discomfort at the sudden insertion, and " + sender.heShe() + " needs a few moments to recover before " + sender.heShe() + " can start moving.");
						}
					}
				}
				if (sender.isVVirg()) {
					if (sender.getObedience() > 66) {
						w.append(t, "  " + sender.HeShe() + " embraces the pain of " + sender.hisHer() + " first vaginal penetration, ecstatic to have received it from " + receiver.ownerName() + ".");
					} else if (sender.getObedience() > 33) {
						w.append(t, "  " + sender.HeShe() + " looks down at the trickle of blood on " + sender.hisHer() + " thigh with an expression of resignation.");
					} else {
						w.append(t, "  " + sender.HeShe() + " lifts " + sender.himHer() + "self up enough to look down and see the blood on " + receiver.ownerName() + "'s cock, then grits " + sender.hisHer() + " teeth.");
					}
				}
			} else {
				if (Project.BeLubricated.isInProgress(sender, null) || sender == w.lordBody) {
					if (sender == w.lordBody) {
						w.append(t, sender.OwnerName() + "'s asshole tightens around " + receiver.ownerName() + "'s cock as though trying to milk it dry.");
					} else {
						if (sender.getObedience() > 66) {
							if (sender.getDeviancy() > 66) {
								w.append(t, sender.OwnerName() + " goes wild with joyful lust, bucking " + sender.hisHer() + " hips wildly and enjoying every moment of being taken by " + receiver.ownerName() + ".");
							} else if (sender.getDeviancy() > 33) {
								w.append(t, sender.OwnerName() + " tries to help " + receiver.ownerName() + " feel good as well, moving " + sender.hisHer() + " hips and urging " + receiver.himHer() + " deeper inside, but soon the pleasure overwhelms " + sender.himHer() + " and " + sender.heShe() + " can't think of anything but wanting to cum.");
							} else {
								w.append(t, sender.OwnerName() + " bites " + sender.hisHer() + " lip in concentration, trying to buck " + sender.hisHer() + " hips in turn and squeeze down with " + sender.hisHer() + " anus in order to make it feel as good as possible for " + receiver.ownerName() + ".");
							}
						} else if (sender.getObedience() > 33) {
							if (sender.getConfidence() > 66) {
								w.append(t, sender.OwnerName() + " doesn't look entirely happy to be taking it up the ass, but the pleasure of each thrust hitting " + sender.hisHer() + " deepest places soon makes " + sender.himHer() + " forget all about that and start crying out in pleasure.");
							} else if (sender.getConfidence() > 33) {
								w.append(t, "At first, " + sender.ownerName() + " tolerates it with an expression of resignation.  However, as " + sender.hisHer() + " sensitive places start to get stimulated through " + sender.hisHer() + " anal walls, " + sender.heShe() + " starts to gasp with passion and then actively move " + sender.hisHer() + " hips.");
							} else {
								w.append(t, sender.OwnerName() + " is too nervous to move much, worried about doing something to displease " + receiver.ownerName() + ".  Whimpering moans of pleasure begin to leak out of " + sender.hisHer() + " throat.");
							}
						} else {
							if (sender.getInnocence() > 66) {
								w.append(t, sender.OwnerName() + " tries to kick and scream, but " + sender.hisHer() + " movements only drive " + receiver.ownerName() + " deeper inside " + sender.himHer() + ", and " + sender.hisHer() + " voice takes on a passionate quality.");
							} else if (sender.getInnocence() > 33) {
								w.append(t, sender.OwnerName() + " attempts to deny " + receiver.ownerName() + " the satisfaction of seeing " + sender.hisHer() + " reactions, but the longer the fucking continues, the more difficult it is.  Soon " + sender.heShe() + "'s reduced to covering " + sender.hisHer() + " face and biting " + sender.hisHer() + " lip to stifle " + sender.hisHer() + " moans.");
							} else {
								w.append(t, sender.OwnerName() + " glares up at " + receiver.ownerName() + ", and " + sender.hisHer() + " hatred comes through clearly even as " + sender.heShe() + " winces and groans at the stimulation through " + sender.hisHer() + " anal walls.");
							}
						}
					}
					if (sender.getGender() == Forsaken.Gender.MALE && sender.isVVirg()) {
						if (sender.getObedience() > 66) {
							w.append(t, "  " + sender.HeShe() + " smiles broadly up at " + receiver.ownerName() + ", tears of joy leaking from " + sender.hisHer() + " eyes at receiving " + sender.hisHer() + " first anal penetration from the one " + sender.heShe() + " loves.");
						} else if (sender.getObedience() > 33) {
							w.append(t, "  To " + sender.hisHer() + " horror, the pleasure keeps building further and further, until " + sender.heShe() + " can't deny that " + sender.heShe() + " enjoys being fucked like a girl.");
						} else {
							w.append(t, "  " + sender.HisHer() + " anger drowns out the pain of having " + sender.hisHer() + " inexperienced asshole stretched so wide for the first time.");
						}
					}
				} else if (sender.forsakenOwner == null && sender.chosenOwner != null && sender.chosenOwner.aVirg) {
					if (sender.getConfidence() > 66) {
						w.append(t, sender.OwnerName() + "'s superhuman durability prevents " + receiver.ownerName() + " from getting more than the tip inside, but even that much is enough to make " + sender.himHer() + " grit " + sender.hisHer() + " teeth in anger, surprise, and pain.");
					} else if (sender.getConfidence() > 33) {
						w.append(t, sender.OwnerName() + "'s whole body lurches, more from surprise than from any conscious attempt at defiance, but " + sender.heShe() + "'s strong enough to stop " + receiver.ownerName() + " from getting more than the tip inside " + sender.himHer() + ".  As " + sender.heShe() + " realizes what just almost happened, " + sender.heShe() + " glares at " + receiver.ownerName() + ".");
					} else {
						w.append(t, sender.OwnerName() + " screams in pain and starts to panic even before " + receiver.ownerName() + " can get more than the tip inside " + sender.himHer() + ".");
					}
				} else {
					if (sender.getConfidence() > 66) {
						if (sender.getObedience() > 66) {
							w.append(t, sender.OwnerName() + " grits " + sender.hisHer() + " teeth into a forced smile, encouraging " + receiver.ownerName() + " to keep fucking " + sender.himHer() + " even though " + sender.heShe() + "'s in agony from having " + sender.hisHer() + " ass forced open without any lubrication.");
						} else if (sender.getObedience() > 33) {
							w.append(t, "The agony of being anally penetrated without any lubrication is almost enough to paralyze " + sender.ownerName() + "'s thoughts, but " + sender.heShe() + " has just enough willpower to beg for mercy, offering to do anything in hopes of ending the torture.");
						} else {
							w.append(t, sender.OwnerName() + "'s determined resolve lasts only a moment before cracking, and " + sender.heShe() + " starts begging for mercy in a shrill voice, apologizing for " + sender.hisHer() + " defiance.");
						}
					} else if (sender.getConfidence() > 33) {
						if (sender.getDignity() > 66) {
							w.append(t, "At first, " + sender.ownerName() + " tries to pretend that " + sender.heShe() + " can take it.  But the painful friction against " + sender.hisHer() + " unlubricated anal walls is too much for " + sender.himHer() + ", and soon " + sender.heShe() + "'s crying and begging without restraint.");
						} else if (sender.getDignity() > 33) {
							w.append(t, "Without any lubrication, the insertion is more painful than anything, and " + sender.ownerName() + " is ashamed with " + sender.himHer() + "self as " + sender.heShe() + " starts uncontrollably sobbing.");
						} else {
							w.append(t, sender.OwnerName() + " kicks wildly and screams at the top of " + sender.hisHer() + " lungs, heedless of the fact that " + sender.heShe() + "'s only scraping " + sender.himHer() + "self even more against the cruel invading shaft.");
						}
					} else {
						if (sender.getDeviancy() > 66) {
							w.append(t, sender.OwnerName() + "'s eyes shoot wide open, a scream caught in " + sender.hisHer() + " throat.  But when it finally comes out, there's a lewd quality to it, a sign of " + sender.hisHer() + " insatiable masochism.");
						} else if (sender.getDeviancy() > 33) {
							w.append(t, sender.OwnerName() + " whimpers, trying to curl up and protect " + sender.himHer() + "self, but " + sender.heShe() + " can't hide from the painful friction of the shaft invading " + sender.hisHer() + " unprepared hole.  " + sender.HisHer() + " moans aren't entirely from pain, as " + sender.heShe() + " feels a hint of shameful pleasure as well.");
						} else {
							w.append(t, sender.OwnerName() + " screams, crying and sobbing at the explosion of agony " + sender.heShe() + " feels from the unlubricated insertion.  The pain far outweighs the pleasure.");
						}
					}
					if (sender.getGender() == Forsaken.Gender.MALE && sender.isVVirg()) {
						if (sender.getObedience() > 66) {
							w.append(t, "  Afterward, " + sender.heShe() + "'ll be full of joyful pride that " + receiver.ownerName() + " saw fit to break " + sender.himHer() + " in with such a memorable fucking, but for now, " + sender.heShe() + "'s in too much pain to think about it in those terms.");
						} else if (sender.getObedience() > 33) {
							w.append(t, "  " + sender.HeShe() + " realizes that it was a mistake to ever take pride in managing to protect " + sender.hisHer() + " anal virginity.");
						} else {
							w.append(t, "  A part of " + sender.himHer() + " is horrified that " + sender.heShe() + " was broken so easily, and " + sender.hisHer() + " confidence in " + sender.hisHer() + " own masculinity will never recover.");
						}
					}
				}
			}
		} else if (this == Project.StripOther) {
			if (sender == w.lordBody) {
				w.append(t, sender.OwnerName() + " begins to strip off " + receiver.ownerName() + "'s clothes.");
				if (w.sceneLocation == Location.STAGE) {
					w.append(t, "  The crowd goes wild, eager to see more skin.");
				}
			} else {
				//currently impossible
			}
		} else if (this == Project.Stripped) {
			if (sender == w.lordBody) {
				//currently impossible
			} else {
				if (w.sceneLocation == Location.STAGE) {
					if (sender.getObedience() > 66) {
						if (sender.getDisgrace() > 66) {
							w.append(t, sender.OwnerName() + " doesn't even seem to notice all the spectators.  " + sender.HeShe() + " only has eyes for " + receiver.ownerName() + ".");
						} else if (sender.getDisgrace() > 33) {
							w.append(t, sender.OwnerName() + " smiles, eager to help " + receiver.ownerName() + " put on a show for everyone.");
						} else {
							w.append(t, sender.OwnerName() + " looks afraid, but also eager, wanting to show everyone that " + sender.heShe() + " really is nothing more than " + receiver.ownerName() + "'s toy.");
						}
					} else if (sender.getObedience() > 33) {
						if (sender.forsakenOwner != null && sender.forsakenOwner.timesExposed < 300000) {
							if (sender.getDignity() > 66) {
								w.append(t, sender.OwnerName() + " is clearly terrified of the prospect of being forced to show everything, but " + sender.heShe() + "'s come too far to back out now.");
							} else if (sender.getDignity() > 33) {
								w.append(t, sender.OwnerName() + " looks resigned to the fact that " + sender.heShe() + "'ll shortly be showing off more than " + sender.heShe() + " ever has before.");
							} else {
								w.append(t, sender.OwnerName() + " just ignores all the shouts and jeers.");
							}
						} else {
							if (sender.getDignity() > 66) {
								w.append(t, sender.OwnerName() + " tries to make it look like it was " + sender.hisHer() + " idea to undress, moving to help " + receiver.ownerName() + " strip " + sender.himHer() + ".");
							} else if (sender.getDignity() > 33) {
								w.append(t, sender.OwnerName() + " tries to make the best of it, smiling at the crowd.");
							} else {
								w.append(t, sender.OwnerName() + " just ignores all the shouts and jeers.");
							}
						}
					} else {
						if (sender.getDeviancy() > 66) {
							w.append(t, sender.OwnerName() + "'s eyes dart back and forth among all the people watching, and despite " + sender.hisHer() + " anger, " + sender.heShe() + "'s panting wth barely-restrained desire.");
						} else if (sender.getDeviancy() > 33) {
							w.append(t, sender.OwnerName() + " huffs angrily, but the heat rising to " + sender.hisHer() + " cheeks is partly from arousal.");
						} else {
							w.append(t, sender.OwnerName() + " clutches at " + sender.hisHer() + " clothes and glares at " + receiver.ownerName() + ".");
						}
					}
				} else {
					if (sender.getDeviancy() > 66) {
						if (sender.getInnocence() > 66) {
							w.append(t, "The act of being stripped immediately causes " + sender.ownerName() + "'s mind to wander off into erotic fantasy, and " + sender.heShe() + " starts to drool with a silly expression on " + sender.hisHer() + " face.");
						} else if (sender.getInnocence() > 33) {
							w.append(t, "As " + sender.hisHer() + " clothes are taken off, " + sender.ownerName() + " gets more and more turned on, and soon " + sender.heShe() + " can't think of anything but sex.");
						} else {
							w.append(t, sender.OwnerName() + " starts to breathe more quickly, imagining what might come next.");
						}
					} else if (sender.coerced() == false) {
						if (sender.getDignity() > 66) {
							w.append(t, sender.OwnerName() + " lets out an outraged squeal and clutches at " + sender.hisHer() + " clothes as best " + sender.heShe() + " can.");
						} else if (sender.getDignity() > 33) {
							w.append(t, sender.OwnerName() + " is caught by surprise, but quickly recovers, attempting to squirm away.");
						} else {
							w.append(t, sender.OwnerName() + " tries to step backward, frowning at " + receiver.ownerName() + " in confusion.");
						}
					} else if (sender.getDeviancy() > 33) {
						if (sender.getConfidence() > 66) {
							w.append(t, sender.OwnerName() + " proudly shows off, refusing to be ashamed of " + sender.hisHer() + " body.");
						} else if (sender.getConfidence() > 33) {
							w.append(t, sender.OwnerName() + " blushes, anticipating what's to come.");
						} else {
							w.append(t, sender.OwnerName() + " glances off to the side and reflexively tries to cover " + sender.himHer() + "self.");
						}
					} else {
						if (sender.getObedience() > 66) {
							w.append(t, sender.OwnerName() + " smiles, happy that " + receiver.ownerName() + " wants to see " + sender.hisHer() + " body.");
						} else if (sender.getObedience() > 33) {
							w.append(t, sender.OwnerName() + " sighs, accepting that this was inevitable.");
						} else {
							w.append(t, sender.OwnerName() + " clutches at " + sender.hisHer() + " clothes and glares at " + receiver.ownerName() + ".");
						}
					}
				}
			}
		} else if (this == Project.LickCock) {
			if (sender == w.lordBody) {
				w.append(t, sender.OwnerName() + " lowers " + sender.hisHer() + " face to " + receiver.ownerName() + "'s cock and begins to run " + sender.hisHer() + " tongue up and down its length.");
			} else {
				if (sender.getObedience() > 66) {
					if (sender.getInnocence() > 66) {
						w.append(t, sender.OwnerName() + " buries " + sender.hisHer() + " face in " + receiver.ownerName() + "'s crotch, kissing and licking " + receiver.ownerName() + "'s cock all over and moaning in satisfaction as though " + sender.heShe() + " were enjoying a delicious piece of candy.");
					} else if (sender.getInnocence() > 33) {
						w.append(t, sender.OwnerName() + " rubs " + sender.hisHer() + " face against " + receiver.ownerName() + "'s cock, sighing with happiness at being able to service " + receiver.himHer() + ".  " + sender.HeShe() + " gives it an open-mouthed kiss, slathering it in saliva while gazing dreamily into " + receiver.ownerName() + "'s eyes.");
					} else {
						w.append(t, sender.OwnerName() + " brings " + sender.hisHer() + " face to " + receiver.ownerName() + "'s cock, and suddenly " + sender.hisHer() + " tongue is dancing from the tip to the base, finding all of " + receiver.ownerName() + "'s most sensitive spots and then moving on before the stimulation can become too monotonous.");
					}
				} else if (sender.getObedience() > 33) {
					if (sender.getConfidence() > 66) {
						w.append(t, sender.OwnerName() + " begins to aggressively lick every inch of " + receiver.ownerName() + "'s cock, occasionally sucking it and swirling " + sender.hisHer() + " tongue around the tip, completely focused on making " + receiver.ownerName() + " cum.");
					} else if (sender.getConfidence() > 33) {
						w.append(t, sender.OwnerName() + " moves " + sender.hisHer() + " face downward until " + receiver.ownerName() + "'s cock is resting against it.  Then, " + sender.heShe() + " starts licking " + sender.hisHer() + " way from the base up to the tip, suckling it gently with " + sender.hisHer() + " eyes closed.");
					} else {
						w.append(t, sender.OwnerName() + " lowers " + sender.hisHer() + " head to " + receiver.ownerName() + "'s cock and begins licking it without a word of complaint.  " + sender.HeShe() + " constantly looks up to make sure that " + receiver.ownerName() + " is satisfied.");
					}
				} else {
					if (sender.getDeviancy() > 66) {
						w.append(t, "At first, " + sender.ownerName() + " is murmuring angry protests as " + sender.heShe() + " brings " + sender.hisHer() + " face to " + receiver.ownerName() + "'s cock, but " + sender.hisHer() + " words trail off as " + sender.heShe() + " begins compulsively licking the shaft.");
					} else if (sender.getDeviancy() > 33) {
						w.append(t, sender.OwnerName() + " huffs with irritation before starting to lick " + receiver.ownerName() + "'s cock.  " + sender.HisHer() + " movements are skillful, but " + sender.heShe() + " doesn't stop glaring up at " + receiver.ownerName() + ".");
					} else {
						w.append(t, sender.OwnerName() + " bares " + sender.hisHer() + " teeth at " + receiver.ownerName() + "'s cock, clearly thinking about biting it, but " + sender.heShe() + " eventually sticks " + sender.hisHer() + " tongue halfway out and begins reluctantly licking it instead.");
					}
				}
			}
		} else if (this == Project.CockLicked) {
			if (sender == w.lordBody) {
				w.append(t, "The warm wetness of " + receiver.ownerName() + "'s tongue sends pleasure shooting through " + sender.ownerName() + "'s lower body.");
			} else {
				if (sender.getDeviancy() > 66) {
					if (sender.getDignity() > 66) {
						w.append(t, sender.OwnerName() + " tries to hide " + sender.hisHer() + " reaction to the pleasure, but within a matter of moments " + sender.heShe() + "'s squealing and bucking " + sender.hisHer() + " hips, " + sender.hisHer() + " face bright red with shame at being subdued so easily.");
					} else if (sender.getDignity() > 33) {
						w.append(t, sender.OwnerName() + "'s eyes roll and " + sender.hisHer() + " tongue hangs out, " + sender.hisHer() + " well-trained penis utterly helpless against " + receiver.ownerName() + "'s mouth.");
					} else {
						w.append(t, sender.OwnerName() + " gasps and moans, wildly bucking " + sender.hisHer() + " hips against " + receiver.ownerName() + "'s face, as if trying to fuck " + receiver.hisHer() + " mouth.");
					}
				} else if (sender.getDeviancy() > 33) {
					if (sender.getObedience() > 66) {
						w.append(t, sender.OwnerName() + " tries to shake off the pleasure and focus on serving " + receiver.ownerName() + ", but " + sender.hisHer() + " happiness at being rewarded by " + receiver.ownerName() + " drives all other thoughts out of " + sender.hisHer() + " mind.");
					} else if (sender.getObedience() > 33) {
						w.append(t, sender.OwnerName() + " sighs and relaxes " + sender.hisHer() + " body, closing " + sender.hisHer() + " eyes and losing " + sender.himHer() + "self in the pleasure.");
					} else {
						w.append(t, sender.OwnerName() + " attempts to push " + receiver.ownerName() + " away, but the sudden pleasure shooting through " + sender.hisHer() + " lower body makes " + sender.himHer() + " momentarily weaker.");
					}
				} else {
					if (sender.getConfidence() > 66) {
						w.append(t, sender.OwnerName() + " frowns down at " + receiver.ownerName() + " and tries to mentally shut out the pleasure in order to stay focused.");
					} else if (sender.getConfidence() > 33) {
						w.append(t, sender.OwnerName() + " leans away from " + receiver.ownerName() + ", feeling uncertain about being pleasured like this.");
					} else {
						w.append(t, sender.OwnerName() + " stammers incoherently, clearly unsure about how to deal with this.");
					}
				}
			}
		} else if (this == Project.LickPussy) {
			if (sender == w.lordBody) {
				w.append(t, sender.OwnerName() + " lowers " + sender.hisHer() + " face to " + receiver.ownerName() + "'s lower lips and begins to run " + sender.hisHer() + " tongue up and down between them.");
			} else {
				if (sender.getObedience() > 66) {
					if (sender.getInnocence() > 66) {
						w.append(t, sender.OwnerName() + " buries " + sender.hisHer() + " face in " + receiver.ownerName() + "'s crotch, kissing and licking " + receiver.ownerName() + "'s pussy all over and moaning in satisfaction as though " + sender.heShe() + " were slurping up a bowl of something delicious");
					} else if (sender.getInnocence() > 33) {
						w.append(t, sender.OwnerName() + " rubs " + sender.hisHer() + " face against " + receiver.ownerName() + "'s crotch, sighing with happiness at being able to service " + receiver.himHer() + ".  " + sender.HeShe() + " starts licking it, " + sender.hisHer() + " tongue slightly pushing into " + receiver.ownerName() + "'s pussy and lapping up the juices while " + sender.heShe() + " gazes dreamily into " + receiver.ownerName() + "'s eyes.");
					} else {
						w.append(t, sender.OwnerName() + " brings " + sender.hisHer() + " face to " + receiver.ownerName() + "'s pussy and begins to lick it, " + sender.hisHer() + " tongue darting deeper and deeper each time.   Then, " + sender.heShe() + " begins to expertly move " + sender.hisHer() + " tongue, curling it upward and exploring around, stimulating ");
						if (receiver.parts[PENIS] > 0) {
							w.append(t, "the base of " + receiver.ownerName() + "'s cock from the inside.");
						} else {
							w.append(t, receiver.ownerName() + "'s clitoris from the inside.");
						}
					}
				} else if (sender.getObedience() > 33) {
					if (sender.getConfidence() > 66) {
						w.append(t, sender.OwnerName() + " begins to aggressively push " + sender.hisHer() + " tongue between " + receiver.ownerName() + "'s lower lips, swirling " + sender.hisHer() + " it around inside, completely focused on making " + receiver.ownerName() + " cum.");
					} else if (sender.getConfidence() > 33) {
						w.append(t, sender.OwnerName() + " moves " + sender.hisHer() + " face downward until " + sender.heShe() + "'s kissing " + receiver.ownerName() + "'s pussy.  Then, " + sender.heShe() + " starts licking " + sender.hisHer() + " way from the bottom to the top, over and over again, closing " + sender.hisHer() + " eyes in concentration.");
					} else {
						w.append(t, sender.OwnerName() + " lowers " + sender.hisHer() + " head to " + receiver.ownerName() + "'s pussy and begins licking it without a word of complaint.  " + sender.HeShe() + " constantly looks up to make sure that " + receiver.ownerName() + " is satisfied.");
					}
				} else {
					if (sender.getDeviancy() > 66) {
						w.append(t, "At first, " + sender.ownerName() + " is murmuring angry protests as " + sender.heShe() + " brings " + sender.hisHer() + " face to " + receiver.ownerName() + "'s pussy, but " + sender.hisHer() + " words trail off as " + sender.heShe() + " begins compulsively licking it, entranced by the taste of the juices flowing out.");
					} else if (sender.getDeviancy() > 33) {
						w.append(t, sender.OwnerName() + " huffs with irritation before starting to lick " + receiver.ownerName() + "'s pussy.  " + sender.HisHer() + " movements are skillful, but " + sender.heShe() + " doesn't stop glaring up at " + receiver.ownerName() + ".");
					} else {
						w.append(t, sender.OwnerName() + " turns " + sender.hisHer() + " nose up at " + receiver.ownerName() + "'s pussy, almost turning away and refusing to service " + sender.himHer() + ".  But in the end, " + sender.heShe() + " gets to work, licking it reluctantly and looking annoyed at the juices and saliva that end up dribbling down " + sender.hisHer() + " chin.");
					}
				}
			}
		} else if (this == Project.PussyLicked) {
			if (sender == w.lordBody) {
				w.append(t, receiver.ownerName() + "'s smooth tongue sends jolts of pleasure shooting all the way to " + sender.ownerName() + "'s womb.");
			} else {
				if (sender.getDeviancy() > 66) {
					if (sender.getDignity() > 66) {
						w.append(t, sender.OwnerName() + " tries to hide " + sender.hisHer() + " reaction to the pleasure, but within a matter of moments " + sender.heShe() + "'s squealing and bucking " + sender.hisHer() + " hips, " + sender.hisHer() + " face bright red with shame at being subdued so easily.");
					} else if (sender.getDignity() > 33) {
						w.append(t, sender.OwnerName() + "'s eyes roll and " + sender.hisHer() + " tongue hangs out, " + sender.hisHer() + " well-trained hole utterly helpless against " + receiver.ownerName() + "'s tongue.");
					} else {
						w.append(t, sender.OwnerName() + " gasps and moans, wildly bucking " + sender.hisHer() + " hips against " + receiver.ownerName() + "'s face, as if trying to impale " + sender.himHer() + "self on " + receiver.ownerName() + "'s tongue.");
					}
				} else if (sender.getDeviancy() > 33) {
					if (sender.getObedience() > 66) {
						w.append(t, sender.OwnerName() + " tries to shake off the pleasure and focus on serving " + receiver.ownerName() + ", but " + sender.hisHer() + " happiness at being rewarded by " + receiver.ownerName() + " drives all other thoughts out of " + sender.hisHer() + " mind.");
					} else if (sender.getObedience() > 33) {
						w.append(t, sender.OwnerName() + " sighs and relaxes " + sender.hisHer() + " body, closing " + sender.hisHer() + " eyes and losing " + sender.himHer() + "self in the pleasure.");
					} else {
						w.append(t, sender.OwnerName() + " attempts to push " + receiver.ownerName() + " away, but the sudden pleasure shooting through " + sender.hisHer() + " lower body makes " + sender.himHer() + " momentarily weaker.");
					}
				} else {
					if (sender.getConfidence() > 66) {
						w.append(t, sender.OwnerName() + " frowns down at " + receiver.ownerName() + " and tries to mentally shut out the pleasure in order to stay focused.");
					} else if (sender.getConfidence() > 33) {
						w.append(t, sender.OwnerName() + " leans away from " + receiver.ownerName() + ", feeling uncertain about being pleasured like this.");
					} else {
						w.append(t, sender.OwnerName() + " stammers incoherently, clearly unsure about how to deal with this.");
					}
				}
			}
		} else if (this == Project.Supine) {
			if (sender == w.actingBody) {
				w.append(t, sender.OwnerName() + " lays down on " + sender.hisHer() + " back.");
			} else {
				if (Project.BeTied.isInProgress(sender, null)) {
					w.append(t, w.actingBody.OwnerName() + " places the helpless " + sender.ownerName() + " on the floor, rolling " + sender.himHer() + " over onto " + sender.hisHer() + " back.");
				} else if (sender.getObedience() > 66) {
					w.append(t, "At " + w.actingBody.ownerName() + "'s command, " + sender.ownerName() + " obediently lays down on " + sender.hisHer() + " back.");
				} else if (sender.coerced()) {
					w.append(t, "When " + w.actingBody.ownerName() + " orders " + sender.himHer() + " to lay down, " + sender.ownerName() + " hesitates only a moment before " + sender.heShe() + " complies.");
				} else if (sender.getObedience() > 33) {
					w.append(t, w.actingBody.OwnerName() + " forces " + sender.ownerName() + " down onto " + sender.hisHer() + " back, and " + sender.ownerName() + " is momentarily too intimidated to resist.");
				} else {
					w.append(t, w.actingBody.OwnerName() + " trips " + sender.ownerName() + " up, and after a brief scuffle, " + sender.ownerName() + " lands on the ground.");
				}
			}
		} else if (this == Project.PullUp) {
			if (Project.BeTied.isInProgress(receiver, null)) {
				w.append(t, sender.OwnerName() + " pulls the helpless " + receiver.ownerName() + " up onto " + receiver.hisHer() + " feet so that " + receiver.heShe() + " can stand on " + receiver.hisHer() + " own.");
			} else {
				w.append(t, sender.OwnerName() + " helps " + receiver.ownerName() + " up onto " + receiver.hisHer() + " feet.");
			}
			receiver.removeActivity(Project.Supine, null);
		} else if (this == Project.StepOnCock) {
			if (sender == w.lordBody) {
				w.append(t, sender.OwnerName() + " steps on " + receiver.ownerName() + "'s penis, then begins moving " + sender.hisHer() + " foot up and down to stimualte it.");
			} else {
				if (sender.getObedience() > 66) {
					if (sender.getInnocence() > 66) {
						w.append(t, sender.OwnerName() + " giggles at " + receiver.ownerName() + "'s penis, poking it with a toe, but " + sender.heShe() + " quickly grows more serious about pleasuring it, rubbing " + sender.hisHer() + " foot up and down its length with such singleminded intensity that the stimulation causes almost as much pain as pleasure.");
					} else if (sender.getInnocence() > 33) {
						w.append(t, sender.OwnerName() + " feels strange about doing something so disrespectful to " + receiver.ownerName() + ", but at the same time, " + sender.heShe() + " wants to give " + receiver.himHer() + " pleasure, and so " + sender.heShe() + " starts using " + sender.hisHer() + " foot to stimulate " + receiver.ownerName() + "'s penis.  " + sender.HeShe() + "'s as gentle as " + sender.heShe() + " can be, but " + sender.hisHer() + " stroking movements up and down the shaft still cause some pain as well as pleasure.");
					} else {
						w.append(t, sender.OwnerName() + " brings " + sender.hisHer() + " foot to " + receiver.ownerName() + "'s penis, then uses it to service " + receiver.ownerName() + " as skillfully as most other people could with a hand.  " + sender.HeShe() + " strokes it up and down, uses a toe to toy with the tip, puts enough pressure on it to be borderline painful, then caresses it gently to prepare it for more stimulation.");
					}
				} else if (sender.getObedience() > 33) {
					if (sender.getConfidence() > 66) {
						w.append(t, "When " + sender.heShe() + " realizes that " + receiver.ownerName() + " won't stop " + sender.himHer() + ", " + sender.ownerName() + " gleefully starts stepping on " + receiver.ownerName() + "'s penis, putting a painful amount of force into it as " + sender.heShe() + " enjoys the feeling of being in control again.");
					} else if (sender.getConfidence() > 33) {
						w.append(t, sender.OwnerName() + " puts " + sender.hisHer() + " foot on " + receiver.ownerName() + "'s crotch, then starts grinding it against " + receiver.ownerName() + "'s penis.  " + sender.HeShe() + " keeps a wary eye on " + receiver.ownerName() + " the whole while, prepared for " + receiver.himHer() + " to try to turn the tables.");
					} else {
						w.append(t, sender.OwnerName() + "'s fear of being punished causes " + sender.himHer() + " to hesitate before lowering " + sender.hisHer() + " foot to " + receiver.ownerName() + "'s penis, and at first " + sender.hisHer() + " movements are too soft to do much of anything, but " + sender.heShe() + " soon starts to enjoy " + sender.himHer() + "self, smiling down as " + sender.heShe() + " gives " + receiver.ownerName() + " a footjob.");
					}
				} else {
					if (sender.getDeviancy() > 66) {
						w.append(t, sender.OwnerName() + " tries to stomp on " + receiver.ownerName() + "'s penis, but " + sender.hisHer() + " movements begin to change without " + sender.himHer() + " realizing it, and " + sender.heShe() + " subconsciously falls into the familiar rhythm of giving a footjob.");
					} else if (sender.getDeviancy() > 33) {
						w.append(t, sender.OwnerName() + " steps on " + receiver.ownerName() + "'s penis, smirking with satisfaction at the way " + receiver.hisHer() + " body reflexively jerks.  " + sender.HeShe() + " starts putting more weight on " + receiver.ownerName() + ", causing both pleasure and pain.");
					} else {
						w.append(t, sender.OwnerName() + " stomps hard on " + receiver.ownerName() + "'s penis.  " + sender.HeShe() + " looks disgusted that " + receiver.ownerName() + " seems to enjoy the stimulation, but " + sender.heShe() + " doesn't stop.");
					}
				}
			}
		} else if (this == Project.CockSteppedOn) {
			if (sender == w.lordBody) {
				w.append(t, sender.OwnerName() + " twitches and strains under " + receiver.ownerName() + "'s foot.");
			} else {
				if (sender.getDeviancy() > 66) {
					if (sender.getInnocence() > 66) {
						w.append(t, sender.OwnerName() + " squeals and squirms wildly, but " + sender.hisHer() + " penis remains firmly pinned under " + receiver.ownerName() + "'s foot.");
					} else if (sender.getInnocence() > 33) {
						w.append(t, sender.OwnerName() + " moans and thrashes, " + sender.hisHer() + " body spasming with pleasure even as " + sender.heShe() + " instinctively tries to avoid the pain.  " + sender.HeShe() + "'s helpless under " + receiver.ownerName() + "'s foot.");
					} else {
						w.append(t, sender.OwnerName() + " cries out incoherently with pleasure, but " + sender.heShe() + " still has the presence of mind to try to grind " + sender.himHer() + "self against " + receiver.ownerName() + "'s foot in turn, seeking even greater stimulation.");
					}
				} else if (sender.getDeviancy() > 33) {
					if (sender.getDignity() > 66) {
						w.append(t, sender.OwnerName() + " blushes deeply, ashamed at responding to such a humiliating form of stimulation, but that only turns " + sender.himHer() + " on even more.");
					} else if (sender.getDignity() > 33) {
						w.append(t, sender.OwnerName() + " gasps and shudders, the combined pleasure and pain breaking down " + sender.hisHer() + " resistance and leaving " + sender.himHer() + " helpless.");
					} else {
						w.append(t, sender.OwnerName() + " cries out and bucks " + sender.hisHer() + " hips into " + receiver.ownerName() + "'s foot.");
					}
				} else {
					if (sender.getObedience() > 66) {
						w.append(t, sender.OwnerName() + " hides " + sender.hisHer() + " winces of pain as best " + sender.heShe() + " can, not wanting to seem ungrateful for " + receiver.ownerName() + "'s service.");
					} else if (sender.coerced()) {
						w.append(t, sender.OwnerName() + " sighs and averts " + sender.hisHer() + " eyes, trying to ignore the slightly painful stimulation.");
					} else if (sender.getObedience() > 33) {
						w.append(t, sender.OwnerName() + " freezes up, the pain to " + sender.hisHer() + " most sensitive part bringing traumatic flashbacks.");
					} else {
						w.append(t, sender.OwnerName() + " shouts in anger and pain, trying to get up, but " + receiver.ownerName() + "'s foot on " + sender.hisHer() + " penis holds " + sender.himHer() + " down.");
					}
				}
			}
		} else if (this == Project.StepOnClit) {
			if (sender == w.lordBody) {
				w.append(t, sender.OwnerName() + " steps on " + receiver.ownerName() + "'s crotch, then begins moving " + sender.hisHer() + " foot forward and back to stimualte " + receiver.ownerName() + "'s clitoris.");
			} else {
				if (sender.getObedience() > 66) {
					if (sender.getInnocence() > 66) {
						w.append(t, sender.OwnerName() + " giggles playfully as " + sender.heShe() + " pokes " + receiver.ownerName() + "'s clit with " + sender.hisHer() + " toe, but " + sender.heShe() + " quickly grows more serious about pleasuring it, rubbing " + sender.hisHer() + " foot forward and back with such singleminded intensity that the stimulation causes almost as much pain as pleasure.");
					} else if (sender.getInnocence() > 33) {
						w.append(t, sender.OwnerName() + " feels strange about doing something so disrespectful to " + receiver.ownerName() + ", but at the same time, " + sender.heShe() + " wants to give " + receiver.himHer() + " pleasure, and so " + sender.heShe() + " starts using " + sender.hisHer() + " foot to stimulate " + receiver.ownerName() + "'s clit.  " + sender.HeShe() + "'s as gentle as " + sender.heShe() + " can be, but the firm pressure " + sender.heShe() + " places on " + receiver.ownerName() + "'s most sensitive part still causes some pain as well as pleasure.");
					} else {
						w.append(t, sender.OwnerName() + " brings " + sender.hisHer() + " foot to " + receiver.ownerName() + "'s clit, then uses it to service " + receiver.ownerName() + " as skillfully as most other people could with their fingers.  " + sender.HeShe() + " rubs forward and back, uses a toe to rub the surrounding region, puts enough pressure on it to stimulate the parts beneath the skin, then caresses it gently to prepare it for more stimulation.");
					}
				} else if (sender.getObedience() > 33) {
					if (sender.getConfidence() > 66) {
						w.append(t, "When " + sender.heShe() + " realizes that " + receiver.ownerName() + " won't stop " + sender.himHer() + ", " + sender.ownerName() + " gleefully starts stepping on " + receiver.ownerName() + "'s crotch,  putting a painful amount of force into it as " + sender.heShe() + " enjoys the feeling of being in control again.");
					} else if (sender.getConfidence() > 33) {
						w.append(t, sender.OwnerName() + " puts " + sender.hisHer() + " foot on " + receiver.ownerName() + "'s crotch, then starts grinding it against " + receiver.ownerName() + "'s clit.  " + sender.HeShe() + " keeps a wary eye on " + receiver.ownerName() + " the whole while, prepared for " + receiver.himHer() + " to try to turn the tables.");
					} else {
						w.append(t, sender.OwnerName() + "'s fear of being punished causes " + sender.himHer() + " to hesitate before lowering " + sender.hisHer() + " foot to " + receiver.ownerName() + "'s crotch, and at first " + sender.hisHer() + " movements are too soft to do much of anything, but " + sender.heShe() + " soon starts to enjoy " + sender.himHer() + "self, smiling down as " + sender.heShe() + " gives " + receiver.ownerName() + " a footjob.");
					}
				} else {
					if (sender.getDeviancy() > 66) {
						w.append(t, sender.OwnerName() + " tries to stomp on " + receiver.ownerName() + "'s crotch, but " + sender.hisHer() + " movements begin to change without " + sender.himHer() + " realizing it, and " + sender.heShe() + " subconsciously falls into the familiar rhythm of stimulating " + sender.hisHer() + " partner's clitoris.");
					} else if (sender.getDeviancy() > 33) {
						w.append(t, sender.OwnerName() + " steps on " + receiver.ownerName() + "'s crotch, smirking with satisfaction at the way " + receiver.hisHer() + " body reflexively jerks.  " + sender.HeShe() + " starts putting more weight directly on " + receiver.ownerName() + " clit, causing both pleasure and pain.");
					} else {
						w.append(t, sender.OwnerName() + " stomps hard on " + receiver.ownerName() + "'s crotch.  " + sender.HeShe() + " looks disgusted that " + receiver.ownerName() + " seems to enjoy the stimulation to " + receiver.hisHer() + " clit, but " + sender.heShe() + " doesn't stop.");
					}
				}
			}
		} else if (this == Project.ClitSteppedOn) {
			if (sender == w.lordBody) {
				w.append(t, sender.OwnerName() + " twitches and strains under " + receiver.ownerName() + "'s foot.");
			} else {
				if (sender.getDeviancy() > 66) {
					if (sender.getInnocence() > 66) {
						w.append(t, sender.OwnerName() + " squeals and squirms wildly, but " + sender.hisHer() + " crotch and sensitive clit remain firmly pinned under " + receiver.ownerName() + "'s foot.");
					} else if (sender.getInnocence() > 33) {
						w.append(t, sender.OwnerName() + " moans and thrashes, " + sender.hisHer() + " body spasming with pleasure even as " + sender.heShe() + " instinctively tries to avoid the pain.  " + sender.HeShe() + "'s helpless under " + receiver.ownerName() + "'s foot.");
					} else {
						w.append(t, sender.OwnerName() + " cries out incoherently with pleasure, but " + sender.heShe() + " still has the presence of mind to try to grind " + sender.himHer() + "self against " + receiver.ownerName() + "'s foot in turn, seeking even greater stimulation.");
					}
				} else if (sender.getDeviancy() > 33) {
					if (sender.getDignity() > 66) {
						w.append(t, sender.OwnerName() + " blushes deeply, ashamed at responding to such a humiliating form of stimulation, but that only turns " + sender.himHer() + " on even more.");
					} else if (sender.getDignity() > 33) {
						w.append(t, sender.OwnerName() + " gasps and shudders, the combined pleasure and pain breaking down " + sender.hisHer() + " resistance and leaving " + sender.himHer() + " helpless.");
					} else {
						w.append(t, sender.OwnerName() + " cries out and bucks " + sender.hisHer() + " hips into " + receiver.ownerName() + "'s foot.");
					}
				} else {
					if (sender.getObedience() > 66) {
						w.append(t, sender.OwnerName() + " hides " + sender.hisHer() + " winces of pain as best " + sender.heShe() + " can, not wanting to seem ungrateful for " + receiver.ownerName() + "'s service.");
					} else if (sender.coerced()) {
						w.append(t, sender.OwnerName() + " sighs and averts " + sender.hisHer() + " eyes, trying to ignore the slightly painful stimulation.");
					} else if (sender.getObedience() > 33) {
						w.append(t, sender.OwnerName() + " freezes up, the pain between " + sender.hisHer() + " legs bringing traumatic flashbacks.");
					} else {
						w.append(t, sender.OwnerName() + " shouts in anger and pain, trying to get up, but " + receiver.ownerName() + "'s foot between " + sender.hisHer() + " legs holds " + sender.himHer() + " down.");
					}
				}
			}
		} else if (this == Project.DirtyTalk) {
			if (sender.getObedience() > 66) {
				if (sender.getMorality() > 66) {
					w.append(t, sender.OwnerName() + " gently encourages " + receiver.ownerName() + " to enjoy " + receiver.himHer() + "self, wanting to give " + receiver.himHer() + " as much pleasure as possible.");
				} else if (sender.getMorality() > 33) {
					w.append(t, sender.OwnerName() + " can't contain " + sender.hisHer() + " joy over being able to pleasure " + receiver.ownerName() + ", making cheerful conversation even as " + receiver.ownerName() + " continues to enjoy " + sender.hisHer() + " body.");
				} else {
					w.append(t, sender.OwnerName() + " is smug about the fact that " + sender.heShe() + "'s the one " + receiver.ownerName() + " came to for pleasure, and " + sender.heShe() + " uses the opportunity to try to convince " + receiver.ownerName() + " to pay less attention to the others vying for" + receiver.hisHer() + " affection.");
				}
			} else if (sender.getObedience() > 33) {
				if (sender.getConfidence() > 66) {
					w.append(t, sender.OwnerName() + " tries to take control of the situation and dominate " + receiver.ownerName() + " using some slightly demeaning dirty talk.");
				} else if (sender.getConfidence() > 33) {
					w.append(t, sender.OwnerName() + " is mostly enjoying this, and " + sender.heShe() + "'s feeling comfortable enough with the situation to verbally tease " + receiver.ownerName() + ".");
				} else {
					w.append(t, sender.OwnerName() + " feels a thrill at being in control of " + receiver.ownerName() + "'s pleasure, and " + sender.heShe() + " lets it get to " + sender.hisHer() + " head, making comments that " + sender.heShe() + " otherwise wouldn't dare say out loud.");
				}
			} else {
				if (sender.getDeviancy() > 66) {
					w.append(t, "Even though " + sender.heShe() + "'s pleasuring an enemy, " + sender.ownerName() + " finds " + sender.himHer() + "self gleefully making lewd, abusive comments in anticipation of making " + receiver.ownerName() + " cum");
					if (receiver.orgasms > 0) {
						w.append(t, "again.");
					} else {
						w.append(t, ".");
					}
				} else if (sender.getDeviancy() > 33) {
					w.append(t, sender.OwnerName() + " is getting turned on by the situation, and as " + sender.heShe() + " verbally demeans and degrades " + receiver.ownerName() + ", the act of doing so turns " + sender.himHer() + " on even more.");
				} else {
					w.append(t, sender.OwnerName() + " is disgusted by how much " + receiver.ownerName() + " is enjoying this, and " + sender.heShe() + " tells " + receiver.himHer() + " so in great detail.");
				}
			}
		}
		if (counterpart != null) {
			Boolean found = false;
			for (int i = 0; i < receiver.inProgress.length; i++) {
				if (receiver.inProgress[i] == counterpart && receiver.targets[i] == sender) {
					found = true;
				}
			}
			if (found == false) {
				w.append(t, "\n\n");
				Body actualTarget = sender;
				if (counterpart.targeted == false) {
					actualTarget = null;
				}
				counterpart.startActivity(t, w, receiver, actualTarget);
			}
		}
		for (int i = 0; i < sender.inProgress.length; i++) {
			for (int j = 0; j < sender.inProgress[i].enders.length; j++) {
				if (sender.inProgress[i].enders[j] == this) {
					sender.removeActivity(sender.inProgress[i], null);
					i = -1;
					break;
				}
			}
		}
	}
	
	public int weight(WorldState w, Body sender, Body receiver) {
		int result = 0;
		if (this == Project.TweakClit) {
			result -= sender.getFEARLevel()*5;
			result -= sender.getDISGLevel()*10;
			result -= sender.getSHAMLevel()*3;
			result -= sender.getHATELevel()*15;
			result += sender.getPLEALevel()*10;
			result += (sender.getDeviancy()-30)/2;
			result += (sender.getObedience()-50)/2;
			result -= (sender.getInnocence()-50)/3;
		} else if (this == Project.ClitTweaked) {
			result -= sender.getDISGLevel()*10;
			result -= sender.getSHAMLevel()*5;
			result -= sender.getHATELevel()*10;
			result += sender.getPLEALevel()*25;
			result += (sender.getDeviancy()-20)/2;
		} else if (this == Project.SpreadLegs) {
			result -= sender.getFEARLevel()*5;
			result -= sender.getDISGLevel()*10;
			result -= sender.getPAINLevel()*5;
			result -= sender.getSHAMLevel()*15;
			result -= sender.getHATELevel()*10;
			result += sender.getPLEALevel()*20;
			result += (sender.getDeviancy()-50)/2;
			result -= (sender.getDignity()-50)/3;
		} else if (this == Project.Praise) {
			result += sender.getFEARLevel()*10;
			result -= sender.getHATELevel()*25;
			result += sender.getPLEALevel()*5;
			result += (sender.getObedience()-65)/2;
			result -= (sender.getConfidence()-50)/3;
		} else if (this == Project.Insult) {
			//always inverse of Praise so that one or the other is always possible
			result -= sender.getFEARLevel()*10;
			result += sender.getHATELevel()*25;
			result -= sender.getPLEALevel()*5;
			result -= (sender.getObedience()-65)/2;
			result += (sender.getConfidence()-50)/3;
			if (sender.getINJULevel() == 2) {
				result += 20;
			} else if (sender.getINJULevel() == 3) {
				result += 40;
			} else if (sender.getINJULevel() == 4) {
				result += 100;
			}
			result++;
		} else if (this == Project.PushDown) {
			result -= sender.getFEARLevel()*10;
			result -= sender.getDISGLevel()*15;
			result -= sender.getSHAMLevel()*5;
			result -= sender.getHATELevel()*10;
			result += sender.getPLEALevel()*25;
			result += (sender.getDeviancy()-60)/2;
			result += (sender.getConfidence()-50)/3;
		} else if (this == Project.PullDown) {
			result -= sender.getFEARLevel()*5;
			result -= sender.getDISGLevel()*15;
			result -= sender.getSHAMLevel()*3;
			result -= sender.getHATELevel()*10;
			result += sender.getPLEALevel()*20;
			result += (sender.getDeviancy()-40)/2;
			result += (sender.getObedience()-30)/2;
			result += (sender.getConfidence()-50)/3;
			if (sender.isVVirg()) {
				result -= 30;
			}
		} else if (this == Project.Escape) {
			/* Uses the ender system instead
			result += sender.getFEARLevel()*3;
			result += sender.getDISGLevel()*10;
			result += sender.getSHAMLevel()*5;
			result += sender.getHATELevel()*25;
			result -= sender.getPLEALevel()*15;
			result -= (sender.getDeviancy()-60)/2;
			result -= (sender.getObedience()-30)/2;
			result += (sender.getInnocence()-50)/3;*/
		} else if (this == Project.StrokeCock) {
			result -= sender.getFEARLevel()*5;
			result -= sender.getDISGLevel()*10;
			result -= sender.getSHAMLevel()*3;
			result -= sender.getHATELevel()*15;
			result += sender.getPLEALevel()*10;
			result += (sender.getDeviancy()-30)/2;
			result += (sender.getObedience()-50)/2;
			result -= (sender.getInnocence()-50)/3;
		} else if (this == Project.CockStroked) {
			result -= sender.getDISGLevel()*10;
			result -= sender.getSHAMLevel()*5;
			result -= sender.getHATELevel()*10;
			result += sender.getPLEALevel()*25;
			result += (sender.getDeviancy()-20)/2;
		} else if (this == Project.VaginalPenetrate) {
			result -= sender.getFEARLevel()*5;
			result -= sender.getDISGLevel()*15;
			result -= sender.getHATELevel()*15;
			result += sender.getPLEALevel()*30;
			result += (sender.getDeviancy()-40)/2;
			result += (sender.getObedience()-60)/2;
			result -= (sender.getMorality()-50)/3;
			result += (sender.getConfidence()-50)/3;
		} else if (this == Project.PenetratedVaginally) {
			if (Project.PullDown.isInProgress(sender, receiver)) {
				result += sender.getFEARLevel()*20;
			}
			result -= sender.getDISGLevel()*20;
			result -= sender.getHATELevel()*20;
			result += sender.getPLEALevel()*30;
			result += (sender.getDeviancy()-50)/2;
			result += (sender.getObedience()-70)/2;
			result -= (sender.getMorality()-50)/3;
			if (sender.isVVirg()) {
				result += sender.getObedience()-60;
			}
			if (sender.forsakenOwner == null && sender.chosenOwner != null && sender.chosenOwner.vVirg) {
				result = -10000;
			}
		} else if (this == Project.AnalPenetrate) {
			result -= sender.getFEARLevel()*5;
			result -= sender.getDISGLevel()*20;
			result -= sender.getHATELevel()*15;
			result += sender.getPLEALevel()*25;
			result += (sender.getDeviancy()-50)/2;
			result += (sender.getObedience()-60)/2;
			result += (sender.getConfidence()-50)/3;
		} else if (this == Project.PenetratedAnally) {
			if (Project.PullDown.isInProgress(sender, receiver)) {
				result += sender.getFEARLevel()*20;
			}
			if (Project.BeLubricated.isInProgress(sender, null) == false && Project.PushDown.isInProgress(sender, receiver) == false) {
				result -= 30;
				if (sender.forsakenOwner == null && sender.chosenOwner != null && sender.chosenOwner.aVirg) {
					result = -10000;
				}
			}
			result -= sender.getDISGLevel()*30;
			result -= sender.getHATELevel()*20;
			result += sender.getPLEALevel()*20;
			result += (sender.getDeviancy()-50)/2;
			result += (sender.getObedience()-70)/2;
			if (sender.parts[PUSSY] == 0 && sender.isVVirg()) {
				result += sender.getObedience()-60;
				result -= (sender.getMorality()-50)/3;
			}
		} else if (this == Project.Stripped) {
			result -= sender.getDISGLevel()*10;
			result -= sender.getSHAMLevel()*5;
			if (w.sceneLocation == Location.STAGE) {
				result -= sender.getSHAMLevel()*10;
				if ((sender.forsakenOwner != null && sender.forsakenOwner.timesExposed < 300000) || (sender.chosenOwner != null && sender.chosenOwner.modest)) {
					result -= 30;
				}
			}
			result -= sender.getHATELevel()*10;
			result += sender.getPLEALevel()*10;
			result += (sender.getDeviancy()-30)/2;
			result += (sender.getObedience()-30)/2;
			result += (sender.getDisgrace()-30)/2;
			result += (sender.getConfidence()-60)/2;
			result -= (sender.getDignity()-30)/2;
		} else if (this == Project.LickCock) {
			result -= sender.getFEARLevel()*5;
			result -= sender.getDISGLevel()*15;
			result -= sender.getSHAMLevel()*5;
			result -= sender.getHATELevel()*25;
			result += sender.getPLEALevel()*15;
			result += (sender.getDeviancy()-40)*3/2;
			result += (sender.getObedience()-50)/2;
			result -= (sender.getInnocence()-50)/3;
		} else if (this == Project.CockLicked) {
			result -= sender.getDISGLevel()*10;
			result -= sender.getSHAMLevel()*3;
			result -= sender.getHATELevel()*10;
			result += sender.getPLEALevel()*30;
			result += (sender.getDeviancy()-30)/2;
		} else if (this == Project.LickPussy) {
			result -= sender.getFEARLevel()*5;
			result -= sender.getDISGLevel()*15;
			result -= sender.getSHAMLevel()*5;
			result -= sender.getHATELevel()*25;
			result += sender.getPLEALevel()*15;
			result += (sender.getDeviancy()-40)*3/2;
			result += (sender.getObedience()-50)/2;
			result -= (sender.getInnocence()-50)/3;
		} else if (this == Project.PussyLicked) {
			result -= sender.getDISGLevel()*15;
			result -= sender.getSHAMLevel()*5;
			result -= sender.getHATELevel()*15;
			result += sender.getPLEALevel()*25;
			result += (sender.getDeviancy()-30)/2;
		} else if (this == Project.StepOnCock) {
			result -= sender.getFEARLevel()*10;
			result -= sender.getDISGLevel()*5;
			result -= sender.getHATELevel()*5;
			result += sender.getPLEALevel()*10;
			result += (sender.getDeviancy()-30)/2;
			result -= (sender.getObedience()-30)/3;
			result += (sender.getConfidence()-50)/3;
		} else if (this == Project.CockSteppedOn) {
			result -= sender.getDISGLevel()*10;
			result -= sender.getPAINLevel()*10;
			result -= sender.getSHAMLevel()*10;
			result -= sender.getHATELevel()*15;
			result += sender.getPLEALevel()*20;
			result += (sender.getDeviancy()-40)/2;
			result += (sender.getObedience()-50)/2;
		} else if (this == Project.StepOnClit) {
			result -= sender.getFEARLevel()*10;
			result -= sender.getDISGLevel()*5;
			result -= sender.getHATELevel()*5;
			result += sender.getPLEALevel()*10;
			result += (sender.getDeviancy()-30)/2;
			result -= (sender.getObedience()-30)/3;
			result += (sender.getConfidence()-50)/3;
		} else if (this == Project.ClitSteppedOn) {
			result -= sender.getDISGLevel()*10;
			result -= sender.getPAINLevel()*10;
			result -= sender.getSHAMLevel()*10;
			result -= sender.getHATELevel()*15;
			result += sender.getPLEALevel()*20;
			result += (sender.getDeviancy()-40)/2;
			result += (sender.getObedience()-50)/2;
		} else if (this == Project.DirtyTalk) {
			result -= sender.getFEARLevel()*15;
			result -= sender.getDISGLevel()*15;
			result -= sender.getSHAMLevel()*5;
			result += sender.getPLEALevel()*5;
			result += (sender.getDeviancy()-30)/2;
			result -= (sender.getObedience()-30)/3;
		}
		if (this.isInProgress(sender, receiver)) {
			result += sender.getFEARLevel()*15;
			result += sender.getObedience()/2;
		}
		if (hostile) {
			result -= sender.obedienceMod(w, receiver);
			result -= sender.friendsMod(w, receiver);
		} else {
			result += sender.obedienceMod(w, receiver);
			result += sender.friendsMod(w, receiver);
			if (sender.forsakenOwner == null && sender.chosenOwner != null && sender.chosenOwner.truce) {
				result += 30;
			}
			if (sender.getINJULevel() == 2) {
				result -= 20;
			} else if (sender.getINJULevel() == 3) {
				result -= 40;
			} else if (sender.getINJULevel() > 3) {
				result -= 100;
			}
		}
		return result;
	}
	
	public Boolean isInProgress(Body sender, Body receiver) {
		if (sender == null) {
			return false;
		}
		for (int i = 0; i < sender.inProgress.length; i++) {
			if (sender.inProgress[i] == this && (receiver == null || sender.targets[i] == receiver)) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean valid(Body sender, Body receiver) {
		if (Project.BeTied.isInProgress(sender, null)) {
			if (this == Project.PullDown) {
				return false;
			}
		}
		if (Project.Supine.isInProgress(sender, null)) {
			Boolean ender = false;
			for (int i = 0; i < Project.Supine.enders.length; i++) {
				if (this == Project.Supine.enders[i]) {
					ender = true;
				}
			}
			if (ender == false) {
				if (sendReqs[HAND] > 0 || sendReqs[FOOT] > 0) {
					return false;
				}
			}
		}
		if (this == Project.PullDown) {
			if (Project.Supine.isInProgress(receiver, null)) {
				return false;
			}
		}
		if (this == Project.PullUp) {
			if (Project.Supine.isInProgress(receiver, null) == false) {
				return false;
			}
		}
		if (this == Project.StepOnCock || this == Project.StepOnClit) {
			if (Project.Supine.isInProgress(receiver, null) == false) {
				return false;
			}
		}
		if (this == Project.Lubricate) {
			if (Project.PushDown.isInProgress(sender, receiver) == false) {
				return false;
			}
		}
		if (this == Project.DirtyTalk) {
			Boolean pleasuring = false;
			for (int i = 0; i < receiver.inProgress.length; i++) {
				if (receiver.inProgress[i].causesOrgasm && receiver.targets[i] == sender && receiver.inProgress[i].counterpart.causesOrgasm == false) {
					pleasuring = true;
				}
			}
			if (pleasuring == false) {
				return false;
			}
		}
		if (sendReqs[MOUTH] > 0 && counterpart != null && (counterpart.sendReqs[PENIS] > 0 || counterpart.sendReqs[BALLS] > 0 || counterpart.sendReqs[CLIT] > 0 || counterpart.sendReqs[PUSSY] > 0)) {
			if (Project.PushDown.isInProgress(sender, receiver) || Project.PushDown.isInProgress(receiver, sender)) {
				return false;
			}
		}
		if (this == Project.VaginalPenetrate || this == Project.PenetratedVaginally || this == Project.AnalPenetrate || this == Project.PenetratedAnally) {
			if (Project.PushDown.isInProgress(sender, receiver) == false) {
				return false;
			}
			if (Project.VaginalPenetrate.isInProgress(receiver, sender) || Project.PenetratedVaginally.isInProgress(receiver, sender) || Project.AnalPenetrate.isInProgress(receiver, sender) || Project.PenetratedAnally.isInProgress(receiver, sender)) {
				return false;
			}
		}
		for (int i = 0; i < sender.inProgress.length; i++) {
			if (sender.inProgress[i] == this && sender.targets[i] == receiver) {
				return false;
			}
		}
		for (int i = 0; receiver != null && i < receiver.inProgress.length && counterpart != null; i++) {
			if (receiver.inProgress[i] == counterpart && receiver.targets[i] == sender) {
				return false;
			}
		}
		if (this == Project.StripOther && receiver.getEXPOLevel() > 4) {
			return false;
		}
		for (int i = 0; i < sendReqs.length; i++) {
			if (sendReqs[i] > 0) {
				if (sender.parts[i] < sendReqs[i]) {
					return false;
				} else {
					int remaining = sender.parts[i];
					for (int j = 0; j < sender.inProgress.length; j++) {
						if (sender.inProgress[j].sendReqs[i] > 0) {
							if (sender.inProgress[j].nonBlocking == false && (shares == false || sender.inProgress[j] != this)) {
								remaining -= sender.inProgress[j].sendReqs[i];
							}
						}
					}
					if (remaining < sendReqs[i]) {
						return false;
					}
				}
			}
		}
		if (counterpart != null) {
			for (int i = 0; i < counterpart.sendReqs.length; i++) {
				if (counterpart.sendReqs[i] > 0) {
					if (receiver.parts[i] < counterpart.sendReqs[i]) {
						return false;
					} else {
						int remaining = receiver.parts[i];
						for (int j = 0; j < receiver.inProgress.length; j++) {
							if (receiver.inProgress[j].sendReqs[i] > 0) {
								if (receiver.inProgress[j].nonBlocking == false && (counterpart.shares == false || receiver.inProgress[j] != counterpart)) {
									remaining -= receiver.inProgress[j].sendReqs[i];
								}
							}
						}
						if (remaining < counterpart.sendReqs[i]) {
							return false;
						}
					}
				}
			}
			Body actualTarget = sender;
			if (counterpart.targeted == false) {
				actualTarget = null;
			}
			if (counterpart.isInProgress(receiver, actualTarget)) {
				return false;
			}
		}
		return true;
	}
	
	public Activity() {
		
	}

}
