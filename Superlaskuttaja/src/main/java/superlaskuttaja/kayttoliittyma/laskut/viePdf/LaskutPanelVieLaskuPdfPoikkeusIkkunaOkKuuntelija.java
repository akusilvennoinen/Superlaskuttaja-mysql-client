/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskut.viePdf;

import superlaskuttaja.kayttoliittyma.laskut.poista.*;
import superlaskuttaja.kayttoliittyma.suoritteet.poista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

/**
 *
 * @author Augustus58
 */
public class LaskutPanelVieLaskuPdfPoikkeusIkkunaOkKuuntelija implements ActionListener {
    private final JDialog dialog;

    public LaskutPanelVieLaskuPdfPoikkeusIkkunaOkKuuntelija(JDialog dialog) {
        this.dialog = dialog;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        dialog.dispose();
        
    }
}
