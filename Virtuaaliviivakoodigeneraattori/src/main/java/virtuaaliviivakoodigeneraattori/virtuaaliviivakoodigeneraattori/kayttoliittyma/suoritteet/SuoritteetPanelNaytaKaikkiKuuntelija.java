/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.suoritteet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class SuoritteetPanelNaytaKaikkiKuuntelija implements ActionListener {
    private final Lataaja lataaja;
    private final SuoritteetTaulukko taulukko;

    public SuoritteetPanelNaytaKaikkiKuuntelija(Lataaja lataaja, SuoritteetTaulukko taulukko) {
        this.lataaja = lataaja;
        this.taulukko = taulukko;        
    }   
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        taulukko.getSorter().setRowFilter(null);
    }
}
