package domein;

import exceptions.MoeilijkSpelToegangException;
import exceptions.MoeilijkUitdagingToegangException;
import exceptions.NormaalSpelToegangException;
import exceptions.NormaalUitdagingToegangException;
import exceptions.SpelersnaamWachtwoordCombinatieException;
import exceptions.WachtwoordBevestigingFoutException;
import exceptions.SpelnaamBestaatNietException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Bevat de methodes die zullen worden aangeroepn in de startApplicatie. *
 */
public class DomeinController {

    private final SpelerRepository spelerRepository;
    private final SpelRepository spelRepository;
    private final UitdagingRepository uitdagingRepository;
    private Speler deSpeler;
    private Spel spel;

    //constructors
    /**
     * Class constructor. Creërt een object van SpelerRepository, een object van
     * SpelRepository en een object van uitdagingRepository.
     *
     *
     */
    public DomeinController() {
        spelerRepository = new SpelerRepository();
        spelRepository = new SpelRepository();
        uitdagingRepository = new UitdagingRepository();
    }

    //UC1   
    //operaties
    /**
     * Meld de speler (krijgt het spelerobjectje van spelerRepository) aan
     * indien spelersnaam en wachtwoord correct zijn.
     *
     * @param spelersnaam de spelersnaam van de gebruiker
     * @param wachtwoord het wachtwoord van de gebruiker
     */
    public void meldAan(String spelersnaam, String wachtwoord) {
        try {
            Speler gevondenSpeler = spelerRepository.geefSpeler(spelersnaam, wachtwoord);
            if (gevondenSpeler != null) {
                setDeSpeler(gevondenSpeler);
            } else {
                throw new SpelersnaamWachtwoordCombinatieException();
            }
        } catch (NullPointerException e) {
            throw new SpelersnaamWachtwoordCombinatieException();
        }
    }

    /**
     * Maakt een nieuw object van Speler aan met de spelersnaam en wachtwoord
     * als parameter. Daarna voegt men dit spelerobjectje toe aan de
     * spelerRepository
     *
     * @param spelersnaam spelersnaam die de gebruiker wil gebruiken
     * @param wachtwoord wachtwoord van de speler
     * @param bevestiging bevestiging van het wachtwoord (moet identiek zijn aan
     * wachtwoord)
     */
    public void registreer(String spelersnaam, String wachtwoord, String bevestiging) {
        Speler nieuweSpeler = new Speler(spelersnaam, wachtwoord);
        //wachtwoord moet gelijk zijn aan bevestiging
        if (wachtwoord.equals(bevestiging)) {
            spelerRepository.voegSpelerToe(nieuweSpeler);
        } else {
            throw new WachtwoordBevestigingFoutException();
        }
        deSpeler = nieuweSpeler;
    }

    /**
     * Geeft de spelersnaam van de aangemelde speler terug.
     *
     * @return het attribuut spelersnaam van Speler
     */
    public String geefSpelersnaam() {
        return deSpeler.getSpelersnaam();
    }

    //UC2
    /**
     * Maakt een object van Spel aan afhankelijk van de gekozen
     * moeilijkheidsgraad.
     *
     * @param moeilijkheidsgraad moeilijkheidsgraad van het spel
     */
    public void kiesMoeilijkheidsgraad(int moeilijkheidsgraad) {
        switch (moeilijkheidsgraad) {
            case 1:
                spel = new MakkelijkSpel();
                break;
            case 2:
                if (deSpeler.getAantalGewonnen()[0] < 20) {
                    throw new NormaalSpelToegangException();
                }
                spel = new NormaalSpel();
                break;
            case 3:
                if (deSpeler.getAantalGewonnen()[1] < 20) {
                    throw new MoeilijkSpelToegangException();
                }
                spel = new MoeilijkSpel();
                break;
        }
        deSpeler.setSpel(spel);
    }

    /**
     * Maakt een nieuwe uitdaging aan met de uitdaging als parameter indien de
     * speler genoeg uitdagingen van een bepaalde moeilijkheidsgraad heeft
     * gewonnen.
     *
     * @param moeilijkheidsgraad moeilijkheidsgraad van de uitdaging
     */
    public void kiesMoeilijkheidsgraadUitdagingen(int moeilijkheidsgraad) {
        switch (moeilijkheidsgraad) {
            case 1:
                spel = new MakkelijkSpel();
                break;
            case 2:
                if (deSpeler.getAantalGewonnenUitdagingen()[0] < 20) {
                    throw new NormaalUitdagingToegangException();
                }
                spel = new NormaalSpel();
                break;
            case 3:
                if (deSpeler.getAantalGewonnenUitdagingen()[1] < 20) {
                    throw new MoeilijkUitdagingToegangException();
                }
                spel = new MoeilijkSpel();
                break;
        }
        deSpeler.setSpel(spel);
    }

    /**
     * Geeft een overzicht van alle mogelijke moeilijkheidsgraden en het aantal
     * overwinningen per moeilijkheidsgraad.
     *
     * @return een String[][] die de mogelijke moeilijkheidsgraden bevat met het
     * aantal gewonnen spellen per moeilijkheidsgraad.
     */
    public String[][] startMasterMind() {
        String[][] overzicht;
        String[] moeilijkheidsgraden = {"makkelijk", "normaal", "moeilijk"};
        String x = String.format("%d %d %d", deSpeler.getAantalGewonnen()[0], deSpeler.getAantalGewonnen()[1], deSpeler.getAantalGewonnen()[2]);
        String[] aantal = x.split(" ");
        if (Integer.parseInt(aantal[0]) < 20) {
            overzicht = new String[1][2];

        } else if (Integer.parseInt(aantal[1]) < 20) {
            overzicht = new String[2][2];

        } else {
            overzicht = new String[3][2];

        }
        for (int i = 0; i < overzicht.length; i++) {
            overzicht[i][0] = String.format("%-10s", moeilijkheidsgraden[i]);
            overzicht[i][1] = String.format("%5s %5s", aantal[i], "WINS");
        }
        return overzicht;
    }

    /**
     * Geeft een overzicht van het spelbord terug.
     *
     * @return een int[][] die het spelbord representeert.
     */
    public int[][] geefSpelbord() {
        return spel.getSpelbord().geefOverzichtMetPinnen();
    }

    //UC3
    /**
     * Geeft een poging door.
     *
     * @param poging de ingevoerde poging van de speler.
     */
    public void geefPoging(int[] poging) {
        spel.getSpelbord().geefPoging(poging);
        if (Arrays.toString(spel.getSpelbord().getWillekeurigeCode()).equals(Arrays.toString(poging))) {
            if (spel.getId() == 0) {
                deSpeler.verhoogAantalGewonnen();
            }
        }
    }

    /**
     * Geeft de code van het spel terug .
     *
     * @return een int array die de willekeurige code bevat.
     */
    public int[] geefCode() {
        return spel.getSpelbord().getWillekeurigeCode();
    }

    /**
     * Deze methode retourneert een overzicht met de juiste code, het aantal
     * pogingen, het aantal spellen dat de speler al gewonnen heeft en het
     * aantal sterren van de speler. Het aantal sterren wordt berekent aan de
     * hand van het aantal gewonnen spellen per moeilijkheidsgraad.
     *
     * @return een String array die de verschillende componenten van het
     * overzicht bevat.
     */
    public String[] geefOverzicht() {
        String[] overzicht = new String[4];
        overzicht[0] = Arrays.toString(spel.getSpelbord().getWillekeurigeCode());
        overzicht[1] = String.format("%d", spel.getSpelbord().getAantalPogingen());
        int[] aantalGewonnen = deSpeler.getAantalGewonnen();
        int getal = 0;
        switch (spel.getClass().getSimpleName()) {
            case "MakkelijkSpel":
                getal = aantalGewonnen[0];
                break;
            case "NormaalSpel":
                getal = aantalGewonnen[1];
                break;
            case "MoeilijkSpel":
                getal = aantalGewonnen[2];
                break;
        }

        if (getal >= 250) {
            overzicht[2] = "✩✩✩✩✩";
            overzicht[3] = "/";
        } else if (getal >= 100) {
            overzicht[2] = "✩✩✩✩";
            overzicht[3] = String.format("%d", 250 - getal);
        } else if (getal >= 50) {
            overzicht[2] = "✩✩✩";
            overzicht[3] = String.format("%d", 100 - getal);
        } else if (getal >= 20) {
            overzicht[2] = "✩✩";
            overzicht[3] = String.format("%d", 50 - getal);
        } else if (getal >= 10) {
            overzicht[2] = "✩";
            overzicht[3] = String.format("%d", 20 - getal);
        } else {
            overzicht[2] = "0";
            overzicht[3] = String.format("%d", 10 - getal);
        }
        return overzicht;
    }

    /**
     * Deze methode zorgt ervoor dat de spelRepository het spel zal opslaan
     * onder de naam die hij meekrijgt als parameter.
     *
     * @param spelnaam naam van het spel.
     */
    public void registreerSpel(String spelnaam) {
        spelRepository.registreerSpel(spelnaam, deSpeler.getSpelersnaam(), spel);
    }

    /**
     * Roept de spelRepository aan en krijgt een lijst met alle spelnamen +
     * moeilijkheidsgraad van de opgeslagen spellen van de huidige speler weer.
     *
     * @return String[][] die de spelnaam en moeilijkheidsgraad van elk
     * opgeslagen spel bevat.
     */
    public String[][] geefSpellen() {
        return spelRepository.geefSpellen(deSpeler.getSpelersnaam());
    }

    /**
     * Roept de spelRepository aan die ervoor zal zorgen dat het spel met de
     * juiste spelnaam geladen wordt. Het spelerObject krijgt het juiste spel
     * mee.
     *
     * @param spelnaam naam van het spel.
     */
    public void laadSpel(String spelnaam) {
        try {
            spel = spelRepository.geefSpel(deSpeler.getSpelersnaam(), spelnaam);
            if (spel != null) {
                deSpeler.setSpel(spel);
            } else {
                throw new SpelnaamBestaatNietException();
            }
        } catch (NullPointerException e) {
            throw new SpelnaamBestaatNietException();
        }
    }

    /**
     * Roept de spelRepository die ervoor zal zorgen dat het spel van de huidige
     * speler met de juiste spelnaam verwijdert wordt uit de databank.
     *
     * @param spelnaam naam van het spel.
     */
    public void verwijderSpel(String spelnaam) {
        spelRepository.verwijderSpel(spelnaam, deSpeler.getSpelersnaam());
    }

    //-------------------///
    //-------UC5---------///
    //-------------------///
    /**
     * Geeft de onafgewerkte uitdaging van de huidige speler terug.
     *
     *
     * @return String naam van de onafgewerkte uitdaging.
     */
    public String geefOpenUitdagingen() {
        return uitdagingRepository.geefOpenUitdaging(deSpeler.getSpelersnaam());
    }

    /**
     * Geeft een overzicht met het aantal gewonnen spellen per
     * moeilijkheidsgraad terug.
     *
     * @return een int[][] met het aantal gewonne spellen per
     * moeilijkheidsgraad.
     */
    public int[][] startUitdaging() {
        int[][] aantalGewonnenPerMoeilijkheid = new int[3][3];

        int teller = 0;
        int teller2 = 0;

        for (int graad : spel.geefMoeilijkheidsgraden()) {
            aantalGewonnenPerMoeilijkheid[teller][3] = graad;

            teller++;
        }

        for (int score : deSpeler.getAantalGewonnen()) {
            aantalGewonnenPerMoeilijkheid[3][teller2] = score;

            teller2++;
        }

        return aantalGewonnenPerMoeilijkheid;
    }

    /**
     * Geeft de namen van de tegenspelers terug die de gekozen
     * moeilijkheidsgraad kunnen spelen.
     *
     * @param naamUitdagingenCategorie moeilijkheidsgraad van het spel
     * @param aantalGewonnenCategorie aantal spellen die de tegenspeler moet
     * gewonnen hebben.
     *
     * @return De mogelijke tegenspelers.
     */
    public String[] geefTegenSpelers(String naamUitdagingenCategorie, int aantalGewonnenCategorie) {
        return spelerRepository.geefTegenspelers(naamUitdagingenCategorie, aantalGewonnenCategorie, deSpeler.getSpelersnaam());
    }

    /**
     * Roept de uitdagingRepository aan die ervoor zal zorgen dat de uitdaging
     * geregistreerd wordt in de databank.
     *
     * @param tegenspeler naam van de tegenspeler.
     */
    public void registreerUitdaging(String tegenspeler) {
        uitdagingRepository.registreerUitdaging(deSpeler.getSpelersnaam(), tegenspeler, spel);
    }

//---------------------//
//---------UC6---------//
//---------------------//
    /**
     * Roept de uitdagingRepository aan die alle nog niet aanvaarde uitdagingen
     * van de huidige speler zal teruggeven.
     *
     * @return String[][] die de spelersnaam en moeilijkheidsgraad zal bevatten
     * van elke uitdaging.
     */
    public String[][] geefUitdaging() {
        return uitdagingRepository.geefUitdagingen(deSpeler.getSpelersnaam());
    }

    /**
     * Roept de uitdagingRepository aan die ervoor zal zorgen dat de juiste
     * uitdaging geladen wordt. Dit aan de hand van de naam van de uitdager en
     * de huidige spelersnaam.
     *
     * @param uitdager naam van de uitdager.
     */
    public void laadUitdaging(String uitdager) {
        spel = uitdagingRepository.laadUitdaging(uitdager, deSpeler.getSpelersnaam());
        //spel = uitdaging.getSpel();
        deSpeler.setSpel(spel);
        if (!deSpeler.getSpelersnaam().equals(uitdager)) {
            uitdagingRepository.aanvaardUitdaging(spel.getId());       //isAanvaard op 1 zetten in de db
        }
    }

//---------------------//
//---------UC7---------//
//---------------------// 
    /**
     * Deze methode kijkt eerst of het afgeronde spel een uitdaging is. Indien
     * dit zo is wordt er gekeken of de tegenspeler de uitdaging al afgerond
     * had. Indien de andere speler de uitdaging al had afgerond zal het aantal
     * gewonnen uitdagingen / aantal gespeelde uitdagingen correct aangepast
     * worden. Indien dit niet zo is wordt het aantalPogingen meegegeven aan de
     * databank en het aantal gespeelde uitdagingen aangepast Als het spel geen
     * uitdaging is wordt het aantalGewonnen aangepast indien hij gewonnen is.
     * @param isGekraakt parameter die meegeeft of de code gekraakt is of niet.
     */
    public void berekenScore(boolean isGekraakt) {        
       
        if (spel.getId() != 0) {    //controleert of het spel een uitdaging is                 
            String[] uitdagingInfo = uitdagingRepository.geefUitdagingInfo(spel.getId(), deSpeler.getSpelersnaam());    // { speler1, speler2, aantalP } 
            int aantalPogingenHuidig = isGekraakt ? spel.getSpelbord().getAantalPogingen() : spel.getSpelbord().getAantalPogingen()+1;
            Speler tegenspeler;
            int aantalP = Integer.parseInt(uitdagingInfo[2]);
            if (deSpeler.getSpelersnaam().equals(uitdagingInfo[0])) {
                tegenspeler = spelerRepository.geefTegenSpeler(uitdagingInfo[1]);
            } else {
                tegenspeler = spelerRepository.geefTegenSpeler(uitdagingInfo[0]);
            }
            tegenspeler.setSpel(spel);

            if (aantalP != 0) {     //ALS AANTAL POGINGEN 0 IS IN DE DB WIL DIT ZEGGEN DAT DE ANDERE SPELER ZIJN SPEL NOG NIET HEEFT AFGEROND, DE SCORE ZAL BEREKEND WORDEN ZODRA DEZE DIT WEL GDN HEEFT.
                //IK VERHOOG AANTAL GESPEELDE UITDAGINGEN ENKEL ALS DE UITDAGING DOOR BEIDE SPELERS IS AFGEROND OMDAT DE SCORE ANDERS FOUT BEREKENT WORDT.
                deSpeler.verhoogAantalGespeeldeUitdagingen();
                tegenspeler.verhoogAantalGespeeldeUitdagingen();
                spelerRepository.updateAantalGespeeldeUitdagingen(deSpeler.getSpelersnaam(), deSpeler.getAantalGespeeldUitdagingen()[0], deSpeler.getAantalGespeeldUitdagingen()[1], deSpeler.getAantalGespeeldUitdagingen()[2]);
                spelerRepository.updateAantalGespeeldeUitdagingen(tegenspeler.getSpelersnaam(), tegenspeler.getAantalGespeeldUitdagingen()[0], tegenspeler.getAantalGespeeldUitdagingen()[1], tegenspeler.getAantalGespeeldUitdagingen()[2]);
                
                if (aantalP > aantalPogingenHuidig) {
                    deSpeler.verhoogAantalGewonnenUitdagingen();
                    spelerRepository.updateAantalGewonnenUitdagingen(deSpeler.getSpelersnaam(), deSpeler.getAantalGewonnenUitdagingen()[0], deSpeler.getAantalGewonnenUitdagingen()[1], deSpeler.getAantalGewonnenUitdagingen()[2]);
                } else if (aantalP < aantalPogingenHuidig) {
                    tegenspeler.verhoogAantalGewonnenUitdagingen();
                    spelerRepository.updateAantalGewonnenUitdagingen(tegenspeler.getSpelersnaam(), tegenspeler.getAantalGewonnenUitdagingen()[0], tegenspeler.getAantalGewonnenUitdagingen()[1], tegenspeler.getAantalGewonnenUitdagingen()[2]);
                } else {
                    if (uitdagingInfo[0].equals(deSpeler.getSpelersnaam())) {
                        deSpeler.verhoogAantalGewonnenUitdagingen();
                        spelerRepository.updateAantalGewonnenUitdagingen(deSpeler.getSpelersnaam(), deSpeler.getAantalGewonnenUitdagingen()[0], deSpeler.getAantalGewonnenUitdagingen()[1], deSpeler.getAantalGewonnenUitdagingen()[2]);
                    } else {
                        tegenspeler.verhoogAantalGewonnenUitdagingen();
                        spelerRepository.updateAantalGewonnenUitdagingen(tegenspeler.getSpelersnaam(), tegenspeler.getAantalGewonnenUitdagingen()[0], tegenspeler.getAantalGewonnenUitdagingen()[1], tegenspeler.getAantalGewonnenUitdagingen()[2]);
                    }
                }
            }// else {      //OF MEN VOEGT DIT SWS TOE OF MEN VERWIJDERT HET SPEL ALS HET AFGELOPEN IS. BEIDEN ZIJN BEWERKING NR DB.
            if (uitdagingInfo[0].equals(deSpeler.getSpelersnaam())) {
                uitdagingRepository.voegAantalPogingenToeS1(aantalPogingenHuidig, spel.getId());
            } else {
                uitdagingRepository.voegAantalPogingenToeS2(aantalPogingenHuidig, spel.getId());
            }
            //}

        } else if(isGekraakt){
            //bij een gewoon spel
            spelerRepository.updateSpelerAantalGewonnen(deSpeler.getSpelersnaam(), deSpeler.getAantalGewonnen()[0], deSpeler.getAantalGewonnen()[1], deSpeler.getAantalGewonnen()[2]);
        }
    }

    //KLASSEMENT
    /**
     * Deze methode creëert een lijst die het klassement voorstelt per
     * moeilijkheidsgraad. De spelersnaam en het aantal punten per speler die al
     * een uitdaging gespeeld heeft wordt getoond. Dit gerankschikt per aantal
     * gewonnen, indien er meerdere spelers een gelijk aantal spellen heeft
     * gewonnen komt de persoon met het beste percentage aantal gewonnen/aantal
     * gespeeld bovenaan.
     *
     * @return een List (voor elke moeilijkheidsgraad) van een list met String
     * arrays die de spelersnaam een bevat.
     */
    public List<List<String[]>> geefKlassement() {
        List<List<String[]>> klassementen = new ArrayList<>();
        klassementen.add(spelerRepository.geefKlassementMakkelijk());
        klassementen.add(spelerRepository.geefKlassementNormaal());
        klassementen.add(spelerRepository.geefKlassementMoeilijk());
        return klassementen;
    }

    //setters
    /**
     * Setter. Zorgt ervoor dat het attribuut deSpeler de waarde krijgt van de
     * parameter.
     *
     * @param deSpeler een object van Speler
     */
    private void setDeSpeler(Speler deSpeler) {          //Als deSpeler final is mag setter verdwijnen, maar geen zekerheid tot nu toe, == als uitloggen moet kunnen -> niet final, enkel aanmelden -> final
        this.deSpeler = deSpeler;
    }

    /**
     * Setter. Zorgt ervoor dat het attribuut spel de waarde krijgt van de
     * parameter.
     *
     * @param spel een object van Spel
     */
    private void setSpel(Spel spel) {
        this.spel = spel;
    }

}
