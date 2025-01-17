
public class BreedmodOverlordGenerator {

	//variables here are set by player input
	Boolean keepChosenBody;
	BreedmodVariables overlordBreedmodVariables;

	Boolean overlordIsGaijin;
	String overlordGivenName;
	String overlordFamilyName;
	Forsaken.Gender overlordOriginalGender;
	Forsaken.Gender overlordGender;
	String overlordIncantation;
	String overlordAdjectiveName;
	String overlordNounName;
	String overlordMainName;
	String overlordOriginalName;
	String overlordTopCover;
	String overlordTopAccess;
	String overlordBottomCover;
	String overlordBottomAccess;
	String overlordUnderType;
	String overlordColor;
	String overlordAccessory;
	String overlordWeapon;
	String overlordCustomWeaponType;
	String overlordFeetType;
	Boolean overlordIsRuthless;
	Boolean overlordIsLustful;
	int overlordHostility;
	int overlordDeviancy;
	int overlordCombatStyle;

	public Forsaken generateOverlord (Forsaken sacrifice) {

		Forsaken overlord = new Forsaken();

		overlord.textSize = sacrifice.textSize;
		overlord.givenName = this.overlordGivenName;
		overlord.familyName = this.overlordFamilyName;
		overlord.filthyGaijin = this.overlordIsGaijin;
		overlord.textColor = sacrifice.textColor;
		overlord.darkColor = sacrifice.textColor;
		overlord.originalGender = this.overlordOriginalGender;
		overlord.gender = this.overlordGender;
		overlord.incantation = this.overlordIncantation;
		overlord.adjectiveName = this.overlordAdjectiveName;
		overlord.nounName = this.overlordNounName;
		overlord.mainName = this.overlordMainName;
		overlord.originalName = this.overlordOriginalName;
		overlord.topCover = this.overlordTopCover;
		overlord.topAccess = this.overlordTopAccess;
		overlord.bottomCover = this.overlordBottomCover;
		overlord.bottomAccess = this.overlordBottomAccess;
		overlord.underType = this.overlordUnderType;
		overlord.color = this.overlordColor;
		overlord.accessory = this.overlordAccessory;
		overlord.weapon = this.overlordWeapon;
		overlord.customWeaponType = this.overlordCustomWeaponType;
		overlord.feetType = this.overlordFeetType;
		
		overlord.number = 0; //number of killed chosen
		overlord.kills = sacrifice.kills;

		overlord.morality = 0; //how guilty they feel
		overlord.innocence = 0;  //perversity
		overlord.confidence = 100; //devotion? I think?
		overlord.dignity = 0; //amount they attack other forsaken?
		overlord.hostility = this.overlordHostility;  //needless pain caused
		overlord.deviancy = this.overlordDeviancy;   //how kinky you are
		overlord.obedience = 100; //to your will
		overlord.disgrace = 0; //humility
		overlord.stamina = 2000;
		overlord.motivation = 10000;


		overlord.takers[0] = Forsaken.Taker.NONE;
		overlord.takers[1] = Forsaken.Taker.NONE;
		overlord.takers[2] = Forsaken.Taker.NONE;
		overlord.takers[3] = Forsaken.Taker.NONE;

		overlord.takerIDs = new int[4];

		//forsaken.killRelationships;
		overlord.defeatType = sacrifice.defeatType; //how they were taken
		overlord.formerSelf = sacrifice.formerSelf; //who they were before
		overlord.formerRelationships = sacrifice.formerRelationships;
		overlord.firstPartner = sacrifice.firstPartner;
		overlord.secondPartner = sacrifice.secondPartner;
		overlord.firstFormerPartner = sacrifice.firstFormerPartner;
		overlord.secondFormerPartner = sacrifice.secondFormerPartner;
		overlord.firstOriginalRelationship = sacrifice.firstOriginalRelationship;
		overlord.secondOriginalRelationship = sacrifice.secondOriginalRelationship;
		overlord.others = sacrifice.others;
		overlord.otherChosen = sacrifice.otherChosen;
		overlord.troublemaker = sacrifice.troublemaker;     //should be set by player at some point
		overlord.forsakenRelations = sacrifice.forsakenRelations;
		overlord.chosenRelations = sacrifice.chosenRelations;
		overlord.forsakenID = sacrifice.forsakenID;
		//Chosen[] formerPartners;
		//int[] formerFriendships;
		overlord.hateExp = 0;
		overlord.pleaExp = 0;
		overlord.injuExp = 0;
		overlord.expoExp = 0;
		overlord.combatStyle = this.overlordCombatStyle;
		overlord.injured = 0;


		overlord.ruthless = this.overlordIsRuthless;
		overlord.lustful = this.overlordIsLustful;

		overlord.peopleInjured = 0;
		overlord.timesHadSex = 0;
		overlord.timesKilled = 0;
		overlord.demonicBirths = 0;
		overlord.orgasmsGiven = 0;
		overlord.timesOrgasmed = 0;
		overlord.strongestOrgasm = 0;
		overlord.timesTortured = 0;
		overlord.timesHarmedSelf = 0;
		overlord.timesExposed = 0;
		overlord.timesExposedSelf = 0;
		overlord.enjoyedAnal = 0;
		overlord.hypnotized = false;
		overlord.meek = false;
		overlord.drained = false;
		overlord.debased = false;
		overlord.parasitized = false;



		return overlord;

	}






}