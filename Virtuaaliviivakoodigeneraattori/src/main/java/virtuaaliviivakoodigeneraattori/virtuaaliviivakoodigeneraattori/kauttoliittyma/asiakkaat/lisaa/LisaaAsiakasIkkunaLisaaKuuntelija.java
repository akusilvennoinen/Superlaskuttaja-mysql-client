/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.lisaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.AsiakkaatTaulukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Asiakas;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class LisaaAsiakasIkkunaLisaaKuuntelija implements ActionListener {

    private final JTextField nimiKentta;
    private final JTextField katuosoiteKentta;
    private final JTextField postinumeroKentta;
    private final JTextField kaupunkiKentta;
    private final JTextField asiakasnumeroKentta;
    private final JTextField laskujaLahetettyKentta;
    private final Lataaja lataaja;
    private final AsiakkaatTaulukko taulukko;
    private final JFrame frame;

    public LisaaAsiakasIkkunaLisaaKuuntelija(JTextField nimiKentta, JTextField katuosoiteKentta, JTextField postinumeroKentta, JTextField kaupunkiKentta, JTextField asiakasnumeroKentta, JTextField laskujaLahetettyKentta, Lataaja lataaja, AsiakkaatTaulukko taulukko, JFrame frame) {
        this.nimiKentta = nimiKentta;
        this.katuosoiteKentta = katuosoiteKentta;
        this.postinumeroKentta = postinumeroKentta;
        this.asiakasnumeroKentta = asiakasnumeroKentta;
        this.laskujaLahetettyKentta = laskujaLahetettyKentta;
        this.kaupunkiKentta = kaupunkiKentta;
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Asiakas asiakas = new Asiakas(asiakasnumeroKentta.getText(), nimiKentta.getText(), katuosoiteKentta.getText(), postinumeroKentta.getText(), kaupunkiKentta.getText(), Integer.parseInt(laskujaLahetettyKentta.getText()));
        lataaja.getLadattuTietovarasto().getAsiakkaat().add(asiakas);
        taulukko.addAsiakkaatTaulukkoRivi(asiakas);
        suljeIkkuna();
        
    }
    
    private void suljeIkkuna() {
        frame.dispose();
    }
}
