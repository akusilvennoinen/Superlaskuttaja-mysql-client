/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskut.muokkaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.laskut.LaskutTaulukko;
import superlaskuttaja.logiikka.Lasku;
import superlaskuttaja.logiikka.LaskunSumma;
import superlaskuttaja.logiikka.Lataaja;
import superlaskuttaja.logiikka.MerkkiJaMerkkijonoTarkistin;
import superlaskuttaja.logiikka.Pankkiviivakoodi;
import superlaskuttaja.logiikka.Suorite;
import superlaskuttaja.logiikka.Viite;

/**
 *
 * @author Augustus58
 */
public class MuokkaaLaskuaMuokkaaKuuntelija implements ActionListener {

    private MuokkaaLaskuaIkkunaComboBoxKuuntelija comboBoxkuuntelija;
    private MuokkaaLaskuaIkkunaSuoritteetList suoritteetLista;
    private JTextField paivaysKentta;
    private JTextField maksuaikaKentta;
    private JTextField erapaivaKentta;
    private JTextField viivastyskorkoKentta;
    private JTextField maksuehtoKentta;
    private JTextField lisatiedotKentta;
    private JCheckBox onkoMaksettu;

    private final Lataaja lataaja;
    private final LaskutTaulukko taulukko;
    private final JFrame frame;
    private final NappulaLukko lukko;
    private final MerkkiJaMerkkijonoTarkistin tarkistin;
    private final DateFormat pvmFormaatti;

    public MuokkaaLaskuaMuokkaaKuuntelija(MuokkaaLaskuaIkkunaComboBoxKuuntelija comboBoxkuuntelija, MuokkaaLaskuaIkkunaSuoritteetList suoritteetLista, JTextField paivaysKentta, JTextField maksuaikaKentta, JTextField erapaivaKentta, JTextField viivastyskorkoKentta, JTextField maksuehtoKentta, JTextField lisatiedotKentta, JCheckBox onkoMaksettu, Lataaja lataaja, LaskutTaulukko taulukko, JFrame frame, NappulaLukko lukko, DateFormat pvmFormaatti) {
        this.comboBoxkuuntelija = comboBoxkuuntelija;
        this.suoritteetLista = suoritteetLista;
        this.paivaysKentta = paivaysKentta;
        this.maksuaikaKentta = maksuaikaKentta;
        this.erapaivaKentta = erapaivaKentta;
        this.viivastyskorkoKentta = viivastyskorkoKentta;
        this.maksuehtoKentta = maksuehtoKentta;
        this.lisatiedotKentta = lisatiedotKentta;
        this.onkoMaksettu = onkoMaksettu;
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.frame = frame;
        this.lukko = lukko;
        this.tarkistin = new MerkkiJaMerkkijonoTarkistin();
        this.pvmFormaatti = pvmFormaatti;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (!tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn(paivaysKentta.getText())) {
                throw new IllegalArgumentException("Syöte päiväys on virheellinen.");
            }
            if (!tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi(paivaysKentta.getText())) {
                throw new IllegalArgumentException("Syöte päiväys on virheellinen.");
            }
            GregorianCalendar paivays = new GregorianCalendar();
            paivays.setTime(pvmFormaatti.parse(paivaysKentta.getText()));

            Integer maksuaika = Integer.parseInt(maksuaikaKentta.getText());

            if (maksuaika < 0 || maksuaika > 365) {
                throw new IllegalArgumentException("Syöte maksuaika on virheellinen.");
            }

            if (!tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn(erapaivaKentta.getText())) {
                throw new IllegalArgumentException("Syöte eräpäivä on virheellinen.");
            }
            if (!tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi(erapaivaKentta.getText())) {
                throw new IllegalArgumentException("Syöte eräpäivä virheellinen.");
            }
            GregorianCalendar erapaiva = new GregorianCalendar();
            erapaiva.setTime(pvmFormaatti.parse(erapaivaKentta.getText()));

            Viite viite = new Viite(lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxkuuntelija.getValinta()).getAsiakasnumero() + lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxkuuntelija.getValinta()).annaLaskujaLahetettyPlusYksi());

            ArrayList<Suorite> suoritteet = new ArrayList<>();
            for (int i = 0; i < suoritteetLista.valitutRivit().length; i++) {
                suoritteet.add(lataaja.getLadattuTietovarasto().asiakkaanLaskuttamattomatSuoritteetArrayList(lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxkuuntelija.getValinta())).get(suoritteetLista.valitutRivit()[i]));
            }
            if (suoritteet.isEmpty()) {
                throw new IllegalArgumentException("Ei valittuja suoritteita.");
            }

            Double summa = 0.0;
            for (int i = 0; i < suoritteet.size(); i++) {
                summa = summa + suoritteet.get(i).getYht();
            }
            Integer eurot = summa.intValue();
            if (!LaskunSumma.tarkistaEurot(eurot)) {
                throw new IllegalArgumentException("Laskun summa on epäkelpo.");
            }
            Integer sentit = (int) Math.round((summa - summa.intValue()) * 100.0);
            if (!LaskunSumma.tarkistaSentit(sentit)) {
                throw new IllegalArgumentException("Laskun summa on epäkelpo.");
            }
            LaskunSumma laskunSumma = new LaskunSumma(eurot, sentit);

            Pankkiviivakoodi pankkiviivakoodi = new Pankkiviivakoodi(lataaja.getLadattuTietovarasto().getLaskuttaja().getTilinumero(), laskunSumma, viite, erapaiva);

            Lasku lasku = new Lasku(lataaja.getLadattuTietovarasto().getLaskuttaja(),
                    lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxkuuntelija.getValinta()),
                    paivays,
                    lataaja.getLadattuTietovarasto().getLaskuttaja().annaUusiLaskunNumero(),
                    erapaiva,
                    Integer.parseInt(viivastyskorkoKentta.getText()),
                    viite,
                    maksuehtoKentta.getText(),
                    suoritteet,
                    lisatiedotKentta.getText(),
                    laskunSumma,
                    pankkiviivakoodi);

            if (!lasku.onkoViivastyskorkoOikeanlainen()) {
                throw new IllegalArgumentException("Syöte viivästyskorko on virheellinen.");
            }
            if (!lasku.onkoMaksuehtoOikeanlainen()) {
                throw new IllegalArgumentException("Syöte maksuehto on virheellinen.");
            }

            for (int i = 0; i < suoritteet.size(); i++) {
                suoritteet.get(i).setLasku(lasku);
            }
            lataaja.getLadattuTietovarasto().getLaskuttaja().kasvataLahetettyjenLaskujenMaaraaYhdella();
            lataaja.getLadattuTietovarasto().getAsiakkaat().get(comboBoxkuuntelija.getValinta()).kasvataLahetettyjenLaskujenMaaraaYhdella();
            lataaja.getLadattuTietovarasto().getLaskut().add(lasku);
            taulukko.lisaaLaskutTaulukkoRivi(lasku);

            suljeIkkuna();
        } catch (Exception e) {
            MuokkaaLaskuaIkkunaMuokkaaLaskuaPoikkeusIkkuna poikkeusIkkuna = new MuokkaaLaskuaIkkunaMuokkaaLaskuaPoikkeusIkkuna();
            SwingUtilities.invokeLater(poikkeusIkkuna);
        }

    }

    private void suljeIkkuna() {
        frame.dispose();
        lukko.avaa();
    }
}
