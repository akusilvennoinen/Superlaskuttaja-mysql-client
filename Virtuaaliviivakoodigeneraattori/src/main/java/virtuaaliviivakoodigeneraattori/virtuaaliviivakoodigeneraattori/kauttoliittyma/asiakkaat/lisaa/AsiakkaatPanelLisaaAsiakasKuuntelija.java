/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.lisaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.asiakkaat.AsiakkaatTaulukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class AsiakkaatPanelLisaaAsiakasKuuntelija implements ActionListener {
    private final Lataaja lataaja;
    private final AsiakkaatTaulukko taulukko;

    public AsiakkaatPanelLisaaAsiakasKuuntelija(Lataaja lataaja, AsiakkaatTaulukko taulukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        LisaaAsiakasIkkuna lisaaAsiakas = new LisaaAsiakasIkkuna(lataaja, taulukko);
        SwingUtilities.invokeLater(lisaaAsiakas);
    }
}
